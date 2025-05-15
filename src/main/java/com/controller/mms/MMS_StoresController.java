package com.controller.mms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_StoresDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.MMS_TB_REGN_ENGR_MSTR;
import com.models.Mms_tb_regn_oth_mstr;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_StoresController {

	@Autowired
	private MMS_StoresDAO m4DAO;

	@Autowired
	private Mms_Common_DAO mmsCommonDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	ValidationController validation = new ValidationController();

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (1)-> (UNIT WISE HLDG DATA SCREEN
	// START)
	@RequestMapping(value = "/mms_unit_wise_holding_data", method = RequestMethod.GET)
	public ModelAndView mms_unit_wise_holding_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_unit_wise_holding_data", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		if (/*roleType.equalsIgnoreCase("ALL") &&*/ accssubLvl.equalsIgnoreCase("MISO") || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", l1.get(0).get(2));
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m4DAO.mms_holdings_list("ALL", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		Mmap.put("msg", msg);
		Mmap.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));

		return new ModelAndView("mms_unit_wise_holding_dataTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (1)-> (UNIT WISE HLDG DATA SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (2)-> (VIEW LOAN STORES SCREEN START)
	@RequestMapping(value = "/mms_loan_wise_holding_data", method = RequestMethod.GET)
	public ModelAndView mms_loan_wise_holding_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);

		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", l1.get(0).get(2));
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m4DAO.mms_holdings_list("A14", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		Mmap.put("msg", msg);
		return new ModelAndView("mms_loan_wise_holding_dataTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (2)-> (VIEW LOAN STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (3)-> (VIEW SECTOR STORES SCREEN START)
	@RequestMapping(value = "/mms_sector_wise_holding_data", method = RequestMethod.GET)
	public ModelAndView mms_sector_wise_holding_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", l1.get(0).get(2));
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m4DAO.mms_holdings_list("A5", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_sector_wise_holding_dataTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (3)-> (VIEW SECTOR STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (4)-> (VIEW ACSFP STORES SCREEN START)
	@RequestMapping(value = "/mms_acsfp_wise_holding_data", method = RequestMethod.GET)
	public ModelAndView mms_acsfp_wise_holding_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_acsfp_wise_holding_data", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", l1.get(0).get(2));
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m4DAO.mms_holdings_list("A16", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		Mmap.put("msg", msg);
		return new ModelAndView("mms_acsfp_wise_holding_dataTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (4)-> (VIEW ACSFP STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (5)-> (VIEW ENGR STORES SCREEN START)
	@RequestMapping(value = "/mms_eng_wise_holding_data", method = RequestMethod.GET)
	public ModelAndView mms_eng_wise_holding_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_eng_wise_holding_data", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", l1.get(0).get(2));
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m4DAO.mms_holdings_list("ENGO", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		Mmap.put("msg", msg);
		return new ModelAndView("mms_eng_wise_holding_dataTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (5)-> (VIEW ENGR STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (6)-> (LOAN STORES SCREEN START)
	@RequestMapping(value = "/mms_loan_store", method = RequestMethod.GET)
	public ModelAndView mms_loan_store(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_loan_store", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		model.put("r_1", l1);

		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("LOANAUTHORITY"));
		model.put("ml_2", mmsCommonDAO.getExtendDate());
		return new ModelAndView("mms_loan_storesTiles", "Mms_Holding_Ls_CaptureCMD", new Mms_tb_regn_oth_mstr());
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (6)-> (LOAN STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (7)-> (SECTOR STORES SCREEN START)
	@RequestMapping(value = "/mms_sector_store", method = RequestMethod.GET)
	public ModelAndView mms_sector_store(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_sector_store", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		model.put("r_1", l1);

		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("SECTORAUTHORITY"));
		model.put("ml_2", mmsCommonDAO.getExtendDate());
		return new ModelAndView("mms_sector_storesTiles", "Mms_Holding_Ls_CaptureCMD", new Mms_tb_regn_oth_mstr());
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (7)-> (SECTOR STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (8)-> (ACSFP STORES SCREEN START)
	@RequestMapping(value = "/mms_army_store", method = RequestMethod.GET)
	public ModelAndView mms_army_store(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_army_store", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		model.put("r_1", l1);
		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("ACSFP"));
		return new ModelAndView("mms_army_storesTiles", "Mms_Holding_Ls_CaptureCMD", new Mms_tb_regn_oth_mstr());
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (8)-> (ACSFP STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (9)-> (ENGR STORES SCREEN START)
	@RequestMapping(value = "/mms_eng_store", method = RequestMethod.GET)
	public ModelAndView mms_eng_stores(ModelMap mMap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_eng_store", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		mMap.put("r_1", l1);

		mMap.put("msg", msg);
		mMap.put("ml_1", m1.getDomainListingData("ENGAUTHORITY"));
		return new ModelAndView("mms_eng_storesTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (9)-> (ENGR STORES SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (10)-> (SEARCH & VIEW LOAN STORES
	// SCREEN START)
	@RequestMapping(value = "/mms_loan_store_search", method = RequestMethod.GET)
	public ModelAndView search_Loan_stores(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_loan_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		model.put("r_1", l1);

		model.put("msg", msg);
		return new ModelAndView("mms_loan_stores_searchTiles");
	}

	@RequestMapping(value = "/mms_loan_store_view", method = RequestMethod.GET)
	public ModelAndView mms_loan_store_view(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_loan_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("mms_loan_stores_viewTiles");
	}

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (10)-> (SEARCH & VIEW LOAN STORES
	// SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (11)-> (SEARCH & VIEW SECTOR STORES
	// SCREEN START)
	@RequestMapping(value = "/mms_sector_store_search", method = RequestMethod.GET)
	public ModelAndView SearchSectorStores(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_sector_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		model.put("r_1", l1);

		model.put("msg", msg);
		return new ModelAndView("mms_sector_stores_searchTiles");
	}

	@RequestMapping(value = "/mms_sector_store_view", method = RequestMethod.GET)
	public ModelAndView mms_sector_store_view(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_sector_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("mms_sector_stores_viewTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (11)-> (SEARCH & VIEW SECTOR STORES
	// SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (12)-> (SEARCH & VIEW ACSFP STORES
	// SCREEN START)
	@RequestMapping(value = "/mms_army_store_search", method = RequestMethod.GET)
	public ModelAndView search_loan_stores(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_army_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		Mmap.put("r_1", l1);

		Mmap.put("msg", msg);
		return new ModelAndView("mms_army_stores_searchTiles");
	}

	@RequestMapping(value = "/mms_army_store_view", method = RequestMethod.GET)
	public ModelAndView mms_army_store_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_army_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_army_stores_viewTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (12)-> (SEARCH & VIEW ACSFP STORES
	// SCREEN END)

	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (13)-> (SEARCH & VIEW ENG STORES SCREEN
	// START)
	@RequestMapping(value = "/mms_eng_store_search", method = RequestMethod.GET)
	public ModelAndView mms_eng_stores_search(ModelMap mMap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mms_eng_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		mMap.put("r_1", l1);

		mMap.put("msg", msg);
		return new ModelAndView("mms_eng_stores_searchTiles");
	}

	@RequestMapping(value = "/mms_eng_store_view", method = RequestMethod.GET)
	public ModelAndView mms_eng_store_view(ModelMap mMap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest  request) {

		Boolean val = roledao.ScreenRedirect("mms_eng_store_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		mMap.put("msg", msg);
		return new ModelAndView("mms_eng_stores_viewTiles");
	}
	// LOAN, SECTOR, ACSFP, ENGR HLDG MODULE (13)-> (SEARCH & VIEW ENG STORES SCREEN
	// END)
//sana 18-11-2022
	// (1) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS START)
	@RequestMapping(value = "/Printhldgdatalist", method = RequestMethod.POST)
	public ModelAndView Printhldgdatalist(@ModelAttribute("p_c_code") String p_c_code, String p_q_code, String p_d_code,
			String p_b_code, String p_p_code, String p_d_from, String p_d_to, String p_hldg, String p_para,
			String p_susno, String p_unitname, String p_arm, ModelMap model, HttpSession session) {

		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = null;
		if (p_para.equals("UWHD")) {
			val = roledao.ScreenRedirect("mms_unit_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (p_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (p_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (p_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_acsfp_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (p_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String formcode = "";
		String formcodeType = "";

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
	
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (p_c_code.equalsIgnoreCase(null)) {
				p_c_code = "ALL";
			}
			if (p_q_code.equalsIgnoreCase(null)) {
				p_q_code = "ALL";
			}
			if (p_d_code.equalsIgnoreCase(null)) {
				p_d_code = "ALL";
			}
			if (p_b_code.equalsIgnoreCase(null)) {
				p_b_code = "ALL";
			}
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				if (p_susno.equalsIgnoreCase(null) || p_susno.equalsIgnoreCase("ALL")) {
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					p_susno = roleSusNo;
				}
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (p_b_code.equalsIgnoreCase(null) || p_b_code.equalsIgnoreCase("ALL")) {
					p_b_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (p_d_code.equalsIgnoreCase(null) || p_d_code.equalsIgnoreCase("ALL")) {
					p_d_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				if (p_q_code.equalsIgnoreCase(null) || p_q_code.equalsIgnoreCase("ALL")) {
					p_q_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (p_c_code.equalsIgnoreCase(null) || p_c_code.equalsIgnoreCase("ALL")) {
					p_c_code = l1.get(0).get(4);
				}
			}
		}

		if (!p_arm.equalsIgnoreCase("ALL")) {
			formcode = p_arm;
			formcodeType = "LINE";
		}
		if (!p_c_code.equalsIgnoreCase("ALL")) {
			formcode = p_c_code;
			formcodeType = "COMMAND";
		}
		if (!p_q_code.equalsIgnoreCase("ALL")) {
			formcode = p_q_code;
			formcodeType = "CORPS";
		}
		if (!p_d_code.equalsIgnoreCase("ALL")) {
			formcode = p_d_code;
			formcodeType = "DIV";
		}
		if (!p_b_code.equalsIgnoreCase("ALL")) {
			formcode = p_b_code;
			formcodeType = "BDE";
		}
		if (!p_susno.equalsIgnoreCase("")) {
			formcode = p_susno;
			formcodeType = "UNIT";
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")) {
			formcode = roleSusNo;
			formcodeType = "UNIT";
		}
		
		model.put("m_1", m4DAO.mms_holdings_list_print(p_hldg, formcodeType, formcode, p_p_code, p_d_from, p_d_to));
		model.put("m_12", p_para);
		return new ModelAndView("mms_print_unit_wise_holding_dataTiles");
	}

	@RequestMapping(value = "/admin/hldgdatalist", method = RequestMethod.POST)
	public ModelAndView hldgdatalist(@ModelAttribute("m4_c_code") String m4_c_code, String m4_q_code, String m4_d_code,
			String m4_b_code, String m4_p_code, String m4_d_from, String m4_d_to, String m4_hldg, String m4_para,
			String m4_prfs, String m4_susno, String m4_unitname, String m4_arm, ModelMap model, HttpSession session) {

		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = null;
		if (m4_para.equals("UWHD")) {
			val = roledao.ScreenRedirect("mms_unit_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_acsfp_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		/*
		 * if(m4_susno.isEmpty()){ model.put("msg", "Please Select the To SUS.");
		 * if(m4_para.equals("VLS")){ return new
		 * ModelAndView("redirect:mms_loan_wise_holding_data"); }
		 * if(m4_para.equals("VSS")){ return new
		 * ModelAndView("redirect:mms_sector_wise_holding_data"); }
		 * if(m4_para.equals("VAS")){ return new
		 * ModelAndView("redirect:mms_acsfp_wise_holding_data"); }
		 * if(m4_para.equals("ENG")){ return new
		 * ModelAndView("redirect:mms_eng_wise_holding_data"); }
		 * 
		 * }
		 */
		if(!m4_susno.equals("") || m4_susno != "") {
		if(validation.sus_noLength(m4_susno) == false){
	 		model.put("msg",validation.sus_noMSG);
		 		if(m4_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_wise_holding_data"); 
				}
		 		if(m4_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_wise_holding_data"); 
				}
		 		if(m4_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_acsfp_wise_holding_data"); 
				}
		 		if(m4_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_wise_holding_data"); 
				}
			  
		 	 	
			}
		
		}
		
		/*
		 * if(m4_unitname.isEmpty()){ model.put("msg", "Please Select the To Unit.");
		 * if(m4_para.equals("VLS")){ return new
		 * ModelAndView("redirect:mms_loan_wise_holding_data"); }
		 * if(m4_para.equals("VSS")){ return new
		 * ModelAndView("redirect:mms_sector_wise_holding_data"); }
		 * if(m4_para.equals("VAS")){ return new
		 * ModelAndView("redirect:mms_acsfp_wise_holding_data"); }
		 * if(m4_para.equals("ENG")){ return new
		 * ModelAndView("redirect:mms_eng_wise_holding_data"); } }
		 */
		
		if(!m4_unitname.equals("") || m4_unitname != "")  {
		if(validation.checkUnit_nameLength(m4_unitname) == false){
	 		model.put("msg",validation.unit_nameMSG);
		 		if(m4_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_wise_holding_data"); 
				}
		 		if(m4_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_wise_holding_data"); 
				}
		 		if(m4_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_acsfp_wise_holding_data"); 
				}
		 		if(m4_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_wise_holding_data"); 
				}
		}
		}
		
		String formcode = "";
		String formcodeType = "";

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (m4_c_code.equalsIgnoreCase(null)) {
				m4_c_code = "ALL";
			}
			if (m4_q_code.equalsIgnoreCase(null)) {
				m4_q_code = "ALL";
			}
			if (m4_d_code.equalsIgnoreCase(null)) {
				m4_d_code = "ALL";
			}
			if (m4_b_code.equalsIgnoreCase(null)) {
				m4_b_code = "ALL";
			}
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				if (m4_susno.equalsIgnoreCase(null) || m4_susno.equalsIgnoreCase("ALL")) {
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					m4_susno = roleSusNo;
				}
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (m4_b_code.equalsIgnoreCase(null) || m4_b_code.equalsIgnoreCase("ALL")) {
					m4_b_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (m4_d_code.equalsIgnoreCase(null) || m4_d_code.equalsIgnoreCase("ALL")) {
					m4_d_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				if (m4_q_code.equalsIgnoreCase(null) || m4_q_code.equalsIgnoreCase("ALL")) {
					m4_q_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (m4_c_code.equalsIgnoreCase(null) || m4_c_code.equalsIgnoreCase("ALL")) {
					m4_c_code = l1.get(0).get(4);
				}
			}
		}

		if (!m4_arm.equalsIgnoreCase("ALL")) {
			formcode = m4_arm;
			formcodeType = "LINE";
		}
		if (!m4_c_code.equalsIgnoreCase("ALL")) {
			formcode = m4_c_code;
			formcodeType = "COMMAND";
		}
		if (!m4_q_code.equalsIgnoreCase("ALL")) {
			formcode = m4_q_code;
			formcodeType = "CORPS";
		}
		if (!m4_d_code.equalsIgnoreCase("ALL")) {
			formcode = m4_d_code;
			formcodeType = "DIV";
		}
		if (!m4_b_code.equalsIgnoreCase("ALL")) {
			formcode = m4_b_code;
			formcodeType = "BDE";
		}
		if (!m4_susno.equalsIgnoreCase("")) {
			formcode = m4_susno;
			formcodeType = "UNIT";
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")) {
			formcode = roleSusNo;
			formcodeType = "UNIT";
		}
		
		model.put("r_1", l1);
		model.put("m_1", m4DAO.mms_holdings_list(m4_hldg, formcodeType, formcode, m4_p_code, m4_d_from, m4_d_to));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);

		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_hldg);
		model.put("m_10", m4_prfs);
		model.put("a_sus", m4_susno);
		model.put("a_unit", m4_unitname);
		model.put("a_arm", m4_arm);

		accssubLvl = l1.get(0).get(1);
		
		String formcode2 = "";
		String formcodeType2 = "";

		if (/*roleType.equalsIgnoreCase("ALL") &&*/ accssubLvl.equalsIgnoreCase("MISO") || roleAccess.equalsIgnoreCase("HeadQuarter")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

			if (m4_c_code.equalsIgnoreCase("ALL")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			} else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code, "COMMAND"));
			}

			if (m4_q_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!m4_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = m4_c_code;
				}

				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code, "CORPS"));
			}

			if (m4_d_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!m4_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = m4_c_code;
					formcodeType2 = "COMMAND";
				}

				if (!m4_q_code.equalsIgnoreCase("ALL")) {
					formcode2 = m4_q_code;
					formcodeType2 = "CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code, "CORPS"));
			}

		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				model.put("m_2", l1.get(0).get(2));
				model.put("m_3", l1.get(0).get(3));
				//model.put("m_1", m4DAO.mms_holdings_list("ALL", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}
		
		

		if (m4_para.equals("UWHD")) {
			model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
			return new ModelAndView("mms_unit_wise_holding_dataTiles");
		}
		if (m4_para.equals("VLS")) {
			return new ModelAndView("mms_loan_wise_holding_dataTiles");
		}
		if (m4_para.equals("VSS")) {
			return new ModelAndView("mms_sector_wise_holding_dataTiles");
		}
		if (m4_para.equals("VAS")) {
			return new ModelAndView("mms_acsfp_wise_holding_dataTiles");
		}
		if (m4_para.equals("ENG")) {
			return new ModelAndView("mms_eng_wise_holding_dataTiles");
		}
		return null;
	}

	@RequestMapping(value = "/admin/hldgdataSumm", method = RequestMethod.POST)
	public ModelAndView hldgdataSumm(@ModelAttribute("sm4_c_code") String sm4_c_code, String sm4_q_code,
			String sm4_d_code, String sm4_b_code, String sm4_p_code, String sm4_d_from, String sm4_d_to,
			String sm4_hldg, String sm4_para, String sm4_prfs, String sm4_susno, String sm4_unitname, String sm4_arm,
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = null;

		String formcode = "";
		String formcodeType = "";

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
	
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (sm4_c_code.equalsIgnoreCase(null)) {
				sm4_c_code = "ALL";
			}
			if (sm4_q_code.equalsIgnoreCase(null)) {
				sm4_q_code = "ALL";
			}
			if (sm4_d_code.equalsIgnoreCase(null)) {
				sm4_d_code = "ALL";
			}
			if (sm4_b_code.equalsIgnoreCase(null)) {
				sm4_b_code = "ALL";
			}
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				if (sm4_susno.equalsIgnoreCase(null) || sm4_susno.equalsIgnoreCase("ALL")) {
					sm4_susno = l1.get(0).get(2);
				}
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (sm4_b_code.equalsIgnoreCase(null) || sm4_b_code.equalsIgnoreCase("ALL")) {
					sm4_b_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (sm4_d_code.equalsIgnoreCase(null) || sm4_d_code.equalsIgnoreCase("ALL")) {
					sm4_d_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				if (sm4_q_code.equalsIgnoreCase(null) || sm4_q_code.equalsIgnoreCase("ALL")) {
					sm4_q_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (sm4_c_code.equalsIgnoreCase(null) || sm4_c_code.equalsIgnoreCase("ALL")) {
					sm4_c_code = l1.get(0).get(4);
				}
			}
		}

		if (!sm4_arm.equalsIgnoreCase("ALL")) {
			formcode = sm4_arm;
			formcodeType = "LINE";
		}
		if (!sm4_c_code.equalsIgnoreCase("ALL")) {
			formcode = sm4_c_code;
			formcodeType = "COMMAND";
		}
		if (!sm4_q_code.equalsIgnoreCase("ALL")) {
			formcode = sm4_q_code;
			formcodeType = "CORPS";
		}
		if (!sm4_d_code.equalsIgnoreCase("ALL")) {
			formcode = sm4_d_code;
			formcodeType = "DIV";
		}
		if (!sm4_b_code.equalsIgnoreCase("ALL")) {
			formcode = sm4_b_code;
			formcodeType = "BDE";
		}

		if (!sm4_susno.equalsIgnoreCase("")) {
			formcode = sm4_susno;
			formcodeType = "UNIT";
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			formcode = roleSusNo;
		}
		
		model.put("r_1", l1);
		model.put("b_1", m4DAO.mms_holdings_summ(sm4_hldg, formcodeType, formcode, sm4_p_code, sm4_d_from, sm4_d_to));
		model.put("m_2", sm4_c_code);
		model.put("m_3", sm4_q_code);
		model.put("m_4", sm4_d_code);
		model.put("m_5", sm4_b_code);

		model.put("m_6", sm4_p_code);
		model.put("m_7", sm4_d_from);
		model.put("m_8", sm4_d_to);
		model.put("m_9", sm4_hldg);
		model.put("m_10", sm4_prfs);
		model.put("a_sus", sm4_susno);
		model.put("a_unit", sm4_unitname);
		model.put("a_arm", sm4_arm);

		accssubLvl = l1.get(0).get(1);
	
		String formcode2 = "";
		String formcodeType2 = "";

		if (/*roleType.equalsIgnoreCase("ALL") && */accssubLvl.equalsIgnoreCase("MISO")  || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

			if (sm4_c_code.equalsIgnoreCase("ALL")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			} else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", sm4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_c_code, "COMMAND"));
			}

			if (sm4_q_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!sm4_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = sm4_c_code;
				}

				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_q_code, "CORPS"));
			}

			if (sm4_d_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!sm4_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = sm4_c_code;
					formcodeType2 = "COMMAND";
				}

				if (!sm4_q_code.equalsIgnoreCase("ALL")) {
					formcode2 = sm4_q_code;
					formcodeType2 = "CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_d_code, "CORPS"));
			}

		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				model.put("m_2", l1.get(0).get(2));
				model.put("m_3", l1.get(0).get(3));
				model.put("m_1", m4DAO.mms_holdings_list("ALL", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		if (sm4_para.equals("UWHD")) {
			model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
			return new ModelAndView("mms_unit_wise_holding_dataTiles");
		}
		if (sm4_para.equals("VLS")) {
			return new ModelAndView("mms_loan_wise_holding_dataTiles");
		}
		if (sm4_para.equals("VSS")) {
			return new ModelAndView("mms_sector_wise_holding_dataTiles");
		}
		if (sm4_para.equals("VAS")) {
			return new ModelAndView("mms_acsfp_wise_holding_dataTiles");
		}
		if (sm4_para.equals("ENG")) {
			return new ModelAndView("mms_eng_wise_holding_dataTiles");
		}
		return null;

	}

	@RequestMapping(value = "/admin/hldgExpirdatalist", method = RequestMethod.POST)
	public ModelAndView hldgExpirdatalist(@ModelAttribute("m4_c_code2") String m4_c_code2, String m4_q_code2,
			String m4_d_code2, String m4_b_code2, String m4_p_code2, String m4_d_from2, String m4_d_to2,
			String m4_hldg2, String m4_para2, String m4_prfs2, String m4_susno2, String m4_unitname2, String m4_arm2,
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = null;
		if (m4_para2.equals("UWHD")) {
			val = roledao.ScreenRedirect("mms_unit_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para2.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para2.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para2.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_acsfp_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (m4_para2.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		/*
		 * if(m4_susno2.isEmpty()){ model.put("msg", "Please Select the To SUS.");
		 * if(m4_para2.equals("VLS")){ return new
		 * ModelAndView("redirect:mms_loan_wise_holding_data"); }
		 * if(m4_para2.equals("VSS")){ return new
		 * ModelAndView("redirect:mms_sector_wise_holding_data"); }
		 * if(m4_para2.equals("VAS")){ return new
		 * ModelAndView("redirect:mms_acsfp_wise_holding_data"); }
		 * if(m4_para2.equals("ENG")){ return new
		 * ModelAndView("redirect:mms_eng_wise_holding_data"); }
		 * 
		 * }
		 */
		
		if(!m4_susno2.equals("") || m4_susno2 != "") {
		if(validation.sus_noLength(m4_susno2) == false){
	 		model.put("msg",validation.sus_noMSG);
		 		if(m4_para2.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_wise_holding_data"); 
				}
		 		if(m4_para2.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_wise_holding_data"); 
				}
		 		if(m4_para2.equals("VAS")){	
					return new ModelAndView("redirect:mms_acsfp_wise_holding_data"); 
				}
		 		 if(m4_para2.equals("ENG")){	
						return new ModelAndView("redirect:mms_eng_wise_holding_data"); 
					}
				  
		 	 	
			}
		}
		
		/*
		 * if(m4_unitname2.isEmpty()){ model.put("msg", "Please Select the To Unit.");
		 * if(m4_para2.equals("VLS")){ return new
		 * ModelAndView("redirect:mms_loan_wise_holding_data"); }
		 * if(m4_para2.equals("VSS")){ return new
		 * ModelAndView("redirect:mms_sector_wise_holding_data"); }
		 * if(m4_para2.equals("VAS")){ return new
		 * ModelAndView("redirect:mms_acsfp_wise_holding_data"); }
		 * if(m4_para2.equals("ENG")){ return new
		 * ModelAndView("redirect:mms_eng_wise_holding_data"); }
		 * 
		 * 
		 * }
		 */
		if(!m4_unitname2.equals("") || m4_unitname2 != "") {
		if(validation.checkUnit_nameLength(m4_unitname2) == false){
	 		model.put("msg",validation.unit_nameMSG);
		 		if(m4_para2.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_wise_holding_data"); 
				}
		 		if(m4_para2.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_wise_holding_data"); 
				}
		 		if(m4_para2.equals("VAS")){	
					return new ModelAndView("redirect:mms_acsfp_wise_holding_data"); 
				}
		 		 if(m4_para2.equals("ENG")){	
						return new ModelAndView("redirect:mms_eng_wise_holding_data"); 
					}
				  
		 	 	
			}
		}
		String formcode = "";
		String formcodeType = "";

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		if (!roleType.equalsIgnoreCase("ALL")) {
			if (m4_c_code2.equalsIgnoreCase(null)) {
				m4_c_code2 = "ALL";
			}
			if (m4_q_code2.equalsIgnoreCase(null)) {
				m4_q_code2 = "ALL";
			}
			if (m4_d_code2.equalsIgnoreCase(null)) {
				m4_d_code2 = "ALL";
			}
			if (m4_b_code2.equalsIgnoreCase(null)) {
				m4_b_code2 = "ALL";
			}
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				if (m4_susno2.equalsIgnoreCase(null) || m4_susno2.equalsIgnoreCase("ALL")) {
					m4_susno2 = l1.get(0).get(2);
				}
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (m4_b_code2.equalsIgnoreCase(null) || m4_b_code2.equalsIgnoreCase("ALL")) {
					m4_b_code2 = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (m4_d_code2.equalsIgnoreCase(null) || m4_d_code2.equalsIgnoreCase("ALL")) {
					m4_d_code2 = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				if (m4_q_code2.equalsIgnoreCase(null) || m4_q_code2.equalsIgnoreCase("ALL")) {
					m4_q_code2 = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (m4_c_code2.equalsIgnoreCase(null) || m4_c_code2.equalsIgnoreCase("ALL")) {
					m4_c_code2 = l1.get(0).get(4);
				}
			}
		}

		if (!m4_arm2.equalsIgnoreCase("ALL")) {
			formcode = m4_arm2;
			formcodeType = "LINE";
		}
		if (!m4_c_code2.equalsIgnoreCase("ALL")) {
			formcode = m4_c_code2;
			formcodeType = "COMMAND";
		}
		if (!m4_q_code2.equalsIgnoreCase("ALL")) {
			formcode = m4_q_code2;
			formcodeType = "CORPS";
		}
		if (!m4_d_code2.equalsIgnoreCase("ALL")) {
			formcode = m4_d_code2;
			formcodeType = "DIV";
		}
		if (!m4_b_code2.equalsIgnoreCase("ALL")) {
			formcode = m4_b_code2;
			formcodeType = "BDE";
		}

		if (!m4_susno2.equalsIgnoreCase("")) {
			formcode = m4_susno2;
			formcodeType = "UNIT";
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")) {
			formcode = roleSusNo;
		}
		
		model.put("r_1", l1);
		model.put("a_1", m4DAO.mms_expire_list(m4_hldg2, formcodeType, formcode, m4_p_code2, m4_d_from2, m4_d_to2));
		model.put("m_2", m4_c_code2);
		model.put("m_3", m4_q_code2);
		model.put("m_4", m4_d_code2);
		model.put("m_5", m4_b_code2);

		model.put("m_6", m4_p_code2);
		model.put("m_7", m4_d_from2);
		model.put("m_8", m4_d_to2);
		model.put("m_9", m4_hldg2);
		model.put("m_10", m4_prfs2);
		model.put("a_sus", m4_susno2);
		model.put("a_unit", m4_unitname2);
		model.put("a_arm", m4_arm2);

		model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

		accssubLvl = l1.get(0).get(1);
	
		String formcode2 = "";
		String formcodeType2 = "";

		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

			if (m4_c_code2.equalsIgnoreCase("ALL")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			} else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code2, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code2, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code2, "COMMAND"));
			}

			if (m4_q_code2.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!m4_c_code2.equalsIgnoreCase("ALL")) {
					formcode2 = m4_c_code2;
				}

				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code2, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code2, "CORPS"));
			}

			if (m4_d_code2.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!m4_c_code2.equalsIgnoreCase("ALL")) {
					formcode2 = m4_c_code2;
					formcodeType2 = "COMMAND";
				}

				if (!m4_q_code2.equalsIgnoreCase("ALL")) {
					formcode2 = m4_q_code2;
					formcodeType2 = "CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code2, "CORPS"));
			}

		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				model.put("m_2", l1.get(0).get(2));
				model.put("m_3", l1.get(0).get(3));
				model.put("m_1", m4DAO.mms_holdings_list("ALL", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		if (m4_para2.equals("UWHD")) {
			model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
			return new ModelAndView("mms_unit_wise_holding_dataTiles");
		}
		if (m4_para2.equals("VLS")) {
			return new ModelAndView("mms_loan_wise_holding_dataTiles");
		}
		if (m4_para2.equals("VSS")) {
			return new ModelAndView("mms_sector_wise_holding_dataTiles");
		}
		if (m4_para2.equals("VAS")) {
			return new ModelAndView("mms_acsfp_wise_holding_dataTiles");
		}
		if (m4_para2.equals("ENG")) {
			return new ModelAndView("mms_eng_wise_holding_dataTiles");
		}
		return null;
	}

	@RequestMapping(value = "/admin/ExtendhldgExpir", method = RequestMethod.POST)
	public ModelAndView ExtendhldgExpir(@ModelAttribute("e_seq") String e_seq, String e_dt, String e_para,
			String e_c_code, String e_q_code, String e_d_code, String e_b_code, String e_p_code, String e_d_from,
			String e_d_to, String e_hldg, String e_prfs, String e_susno, String e_unitname, String e_arm,
			ModelMap model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		String formcode = "";
		String formcodeType = "";

		Boolean val = null;
		if (e_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (e_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Session s1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t1 = s1.beginTransaction();
		String c_name = null;
		if (e_para.equals("VLS")) {
			c_name = "loan_expiry_date";
		}
		if (e_para.equals("VSS")) {
			c_name = "sector_expiry_date";
		}

		String hql = "update Mms_tb_regn_oth_mstr m set m." + c_name
				+ " = :loan, m.data_upd_by = :username, m.data_upd_date = :dt where m.regn_seq_no = :seq";
		int qp = s1.createQuery(hql).setString("loan", e_dt).setString("username", username)
				.setTimestamp("dt", new Date()).setString("seq", e_seq).executeUpdate();
		t1.commit();
		s1.close();

		if (qp > 0) {
			model.put("msg", "Expiry Date Extend Successfully");
		} else {
			model.put("msg", "Expiry Date Not Extended");
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
	
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (e_c_code.equalsIgnoreCase(null)) {
				e_c_code = "ALL";
			}
			if (e_q_code.equalsIgnoreCase(null)) {
				e_q_code = "ALL";
			}
			if (e_d_code.equalsIgnoreCase(null)) {
				e_d_code = "ALL";
			}
			if (e_b_code.equalsIgnoreCase(null)) {
				e_b_code = "ALL";
			}
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				if (e_susno.equalsIgnoreCase(null) || e_susno.equalsIgnoreCase("ALL")) {
					e_susno = l1.get(0).get(2);
				}
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (e_b_code.equalsIgnoreCase(null) || e_b_code.equalsIgnoreCase("ALL")) {
					e_b_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (e_d_code.equalsIgnoreCase(null) || e_d_code.equalsIgnoreCase("ALL")) {
					e_d_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				if (e_q_code.equalsIgnoreCase(null) || e_q_code.equalsIgnoreCase("ALL")) {
					e_q_code = l1.get(0).get(4);
				}
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (e_c_code.equalsIgnoreCase(null) || e_c_code.equalsIgnoreCase("ALL")) {
					e_c_code = l1.get(0).get(4);
				}
			}
		}

		if (!e_arm.equalsIgnoreCase("ALL")) {
			formcode = e_arm;
			formcodeType = "LINE";
		}
		if (!e_c_code.equalsIgnoreCase("ALL")) {
			formcode = e_c_code;
			formcodeType = "COMMAND";
		}
		if (!e_q_code.equalsIgnoreCase("ALL")) {
			formcode = e_q_code;
			formcodeType = "CORPS";
		}
		if (!e_d_code.equalsIgnoreCase("ALL")) {
			formcode = e_d_code;
			formcodeType = "DIV";
		}
		if (!e_b_code.equalsIgnoreCase("ALL")) {
			formcode = e_b_code;
			formcodeType = "BDE";
		}

		if (!e_susno.equalsIgnoreCase("")) {
			formcode = e_susno;
			formcodeType = "UNIT";
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")) {
			formcode = roleSusNo;
			formcodeType = "UNIT";
		}
			
		model.put("r_1", l1);
		model.put("a_1", m4DAO.mms_expire_list(e_hldg, formcodeType, formcode, e_p_code, e_d_from, e_d_to));
		model.put("m_2", e_c_code);
		model.put("m_3", e_q_code);
		model.put("m_4", e_d_code);
		model.put("m_5", e_b_code);

		model.put("m_6", e_p_code);
		model.put("m_7", e_d_from);
		model.put("m_8", e_d_to);
		model.put("m_9", e_hldg);
		model.put("m_10", e_prfs);
		model.put("a_sus", e_susno);
		model.put("a_unit", e_unitname);
		model.put("a_arm", e_arm);
		model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

		accssubLvl = l1.get(0).get(1);

		String formcode2 = "";
		String formcodeType2 = "";

		if (roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));

			if (e_c_code.equalsIgnoreCase("ALL")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			} else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", e_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", e_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", e_c_code, "COMMAND"));
			}

			if (e_q_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!e_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = e_c_code;
				}

				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", e_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", e_q_code, "CORPS"));
			}

			if (e_d_code.equalsIgnoreCase("ALL")) {
				formcode2 = "ALL";
				formcodeType2 = "COMMAND";

				if (!e_c_code.equalsIgnoreCase("ALL")) {
					formcode2 = e_c_code;
					formcodeType2 = "COMMAND";
				}

				if (!e_q_code.equalsIgnoreCase("ALL")) {
					formcode2 = e_q_code;
					formcodeType2 = "CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));
			} else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", e_d_code, "CORPS"));
			}

		} else {
			if (accssubLvl.equalsIgnoreCase("UNIT")) {
				model.put("m_2", l1.get(0).get(2));
				model.put("m_3", l1.get(0).get(3));
				model.put("m_1", m4DAO.mms_holdings_list("ALL", "UNIT", l1.get(0).get(2), "ALL", "", ""));
			}
			if (accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if (accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if (accssubLvl.equalsIgnoreCase("CORPS")) {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if (accssubLvl.equalsIgnoreCase("COMMAND")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if (accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("", l1.get(0).get(5), session));
			}
		}

		if (e_para.equals("VLS")) {
			return new ModelAndView("mms_loan_wise_holding_dataTiles");
		}
		if (e_para.equals("VSS")) {
			return new ModelAndView("mms_sector_wise_holding_dataTiles");
		}

		return null;
	}

	@RequestMapping(value = "/admin/ListPrfBySearch", method = RequestMethod.POST)
	public ModelAndView ListPrfBySearch(@ModelAttribute("s_prfs") String s_prfs, String s_cmd, String s_cor,
			String s_div, String s_bde, String s_hldg, String s_mthyr, String s_para, ModelMap model,
			HttpSession session) {

		Boolean val = null;
		if (s_para.equals("UWHD")) {
			val = roledao.ScreenRedirect("mms_unit_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (s_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (s_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (s_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_acsfp_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (s_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_wise_holding_data", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("m_2", s_cmd);
		model.put("m_3", s_cor);
		model.put("m_4", s_div);
		model.put("m_5", s_bde);
		model.put("m_9", s_hldg);
		model.put("m_10", s_prfs);
		model.put("m_12", s_mthyr);
		model.put("m_13", "L1");
		model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		if (!s_cmd.equalsIgnoreCase("ALL")) {
			model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", s_cmd, "COMMAND"));
			model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", s_cmd, "COMMAND"));
			model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", s_cmd, "COMMAND"));
		}

		if (!s_cor.equalsIgnoreCase("ALL")) {
			model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", s_cor, "CORPS"));
			model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", s_cor, "CORPS"));
		}

		if (!s_div.equalsIgnoreCase("ALL")) {
			model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", s_div, "DIVISION"));
		}

		if (s_para.equals("UWHD")) {
			model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
			return new ModelAndView("redirect:mms_unit_wise_holding_data");
		}
		if (s_para.equals("VLS")) {
			return new ModelAndView("mms_loan_wise_holding_dataTiles");
		}
		if (s_para.equals("VSS")) {
			return new ModelAndView("mms_sector_wise_holding_dataTiles");
		}
		if (s_para.equals("VAS")) {
			return new ModelAndView("mms_acsfp_wise_holding_dataTiles");
		}
		if (s_para.equals("ENG")) {
			return new ModelAndView("mms_eng_wise_holding_dataTiles");
		}
		return null;
	}
	// (1) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS END)

	// (2) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS (TO ADD) START)
	@RequestMapping(value = "/Mms_Holding_Ls_CaptureAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("Mms_Holding_Ls_CaptureCMD") Mms_tb_regn_oth_mstr rm,
			@RequestParam(value = "upload_auth_letter1", required = false) MultipartFile upload_auth_letter1,
			@RequestParam(value = "upload_voucher1", required = false) MultipartFile upload_voucher1,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String Store_type = request.getParameter("store_type");

		Boolean val = null;
		if (Store_type.equals("LOANAUTHORITY")) {
			val = roledao.ScreenRedirect("mms_loan_store", session.getAttribute("roleid").toString());
		}
		if (Store_type.equals("SECTORAUTHORITY")) {
			val = roledao.ScreenRedirect("mms_sector_store", session.getAttribute("roleid").toString());
		}
		if (Store_type.equals("ACSFP")) {
			val = roledao.ScreenRedirect("mms_army_store", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int countlength = 0;
		countlength = Integer.parseInt(request.getParameter("count"));

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		Date date = new Date();

		String r = "";
		String eq = "";
		String loan_sanc_auth = request.getParameter("loan_sanc_auth");
		String iv_by_unit_name = request.getParameter("iv_by_unit_name");
		String iv_by_sus_no = request.getParameter("iv_by_sus_no");
		String auth_letter_no = request.getParameter("auth_letter_no");
		String auth_letter_date = request.getParameter("auth_letter_date");
		String loan_expiry_date = request.getParameter("loan_expiry_date");
		String sector_expiry_date = request.getParameter("sector_expiry_date");

		String govt_letter_no = request.getParameter("govt_letter_no");
		String unit_name1 = request.getParameter("unit_name1");
		String sus_no1 = request.getParameter("sus_no1");
		String iv_no = request.getParameter("iv_no");
		String iv_date = request.getParameter("iv_date");
		String prf_code = request.getParameter("prf_code");
		String qty = request.getParameter("qty");
		int qt = Integer.parseInt(qty);
		Date auth_letter_date_i = null;
		Date loan_expiry_date_i = null;
		Date sector_expiry_date_i = null;
		Date iv_date_i = null;

		if (loan_sanc_auth.equals("-1")) {
			model.put("msg", "Please Enter the Loan Sanction Auth.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (iv_by_unit_name.equals("")) {
			model.put("msg", "Please Enter the Issuing Auth Unit Name.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		/*
		 * if(validation.checkUnit_nameLength(request.getParameter("iv_by_unit_name") )
		 * == false){ model.put("msg",validation.unit_nameMSG);
		 * if(Store_type.equals("LOANAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_loan_store"); }
		 * if(Store_type.equals("SECTORAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_sector_store"); } if(Store_type.equals("ACSFP")){
		 * return new ModelAndView("redirect:mms_army_store"); } }
		 */

		if (iv_by_sus_no.equals("")) {
			model.put("msg", "Please Enter the Issuing Auth SUS No.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.sus_noLength(request.getParameter("iv_by_sus_no")) == false) {
			model.put("msg", validation.sus_noMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (auth_letter_no.equals("")) {
			model.put("msg", "Please Enter the Auth Letter No.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.checkCatPartNoLength(rm.getAuth_letter_no()) == false) {
			model.put("msg", validation.auth_lett_noMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		try {
			if (auth_letter_date.equals("")) {
				model.put("msg", "Please Enter the Auth Letter Date.");
				if (Store_type.equals("LOANAUTHORITY")) {
					return new ModelAndView("redirect:mms_loan_store");
				}
				if (Store_type.equals("SECTORAUTHORITY")) {
					return new ModelAndView("redirect:mms_sector_store");
				}
				if (Store_type.equals("ACSFP")) {
					return new ModelAndView("redirect:mms_army_store");
				}
			} else {
				auth_letter_date_i = formatter1.parse(request.getParameter("auth_letter_date"));
				if (validation.checkDateLength(request.getParameter("auth_letter_date")) == false) {
					model.put("msg", validation.dateMSG);
					if (Store_type.equals("LOANAUTHORITY")) {
						return new ModelAndView("redirect:mms_loan_store");
					}
					if (Store_type.equals("SECTORAUTHORITY")) {
						return new ModelAndView("redirect:mms_sector_store");
					}
					if (Store_type.equals("ACSFP")) {
						return new ModelAndView("redirect:mms_army_store");
					}
				}

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			if (Store_type.equals("LOANAUTHORITY")) {
				if (loan_expiry_date.equals("")) {
					model.put("msg", "Please Enter the Loan Expiry Date.");
					return new ModelAndView("redirect:mms_loan_store");
				} else {
					loan_expiry_date_i = formatter1.parse(request.getParameter("loan_expiry_date"));
					if (validation.checkDateLength(request.getParameter("loan_expiry_date")) == false) {
						model.put("msg", validation.dateMSG);
						if (Store_type.equals("LOANAUTHORITY")) {
							return new ModelAndView("redirect:mms_loan_store");
						}
					}
				}
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				if (sector_expiry_date.equals("")) {
					model.put("msg", "Please Enter the Sector Expiry Date.");
					return new ModelAndView("redirect:mms_sector_store");
				} else {
					sector_expiry_date_i = formatter1.parse(request.getParameter("sector_expiry_date"));
					if (validation.checkDateLength(request.getParameter("sector_expiry_date")) == false) {
						model.put("msg", validation.dateMSG);
						if (Store_type.equals("SECTORAUTHORITY")) {
							return new ModelAndView("redirect:mms_sector_store");
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (upload_auth_letter1.isEmpty()) {
			model.put("msg", "Please Upload the Auth Letter.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		/*
		 * if(validation.checkUpload_authLetterLength(rm.getUpload_auth_letter()) ==
		 * false){ model.put("msg",validation.Upload_authLetterMSG);
		 * if(Store_type.equals("LOANAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_loan_store"); }
		 * if(Store_type.equals("SECTORAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_sector_store"); } if(Store_type.equals("ACSFP")){
		 * return new ModelAndView("redirect:mms_army_store"); } }
		 */

		if (Store_type.equals("SECTORAUTHORITY")) {
			if (govt_letter_no.equals("")) {
				model.put("msg", "Please Enter Govt Letter No.");
				return new ModelAndView("redirect:mms_sector_store");
			} else if (validation.checkGovtLetterNoLength(govt_letter_no) == false) {
				model.put("msg", validation.govt_letter_noMSG);
				return new ModelAndView("redirect:mms_sector_store");
			}
		}

		if (Store_type.equals("ACSFP")) {
			if (govt_letter_no.equals("")) {
				model.put("msg", "Please Enter Govt Letter No.");
				return new ModelAndView("redirect:mms_army_store");
			} else if (validation.checkGovtLetterNoLength(govt_letter_no) == false) {
				model.put("msg", validation.govt_letter_noMSG);
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (unit_name1.equals("")) {
			model.put("msg", "Please Select the UNIT NAME");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}
		/*
		 * if(validation.checkUnit_nameLength(request.getParameter("unit_name1") ) ==
		 * false){ model.put("msg",validation.unit_nameMSG);
		 * if(Store_type.equals("LOANAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_loan_store"); }
		 * if(Store_type.equals("SECTORAUTHORITY")){ return new
		 * ModelAndView("redirect:mms_sector_store"); } if(Store_type.equals("ACSFP")){
		 * return new ModelAndView("redirect:mms_army_store"); } }
		 */

		if (sus_no1.equals("")) {
			model.put("msg", "Please Select the SUS No");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.sus_noLength(request.getParameter("sus_no1")) == false) {
			model.put("msg", validation.sus_noMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (iv_no.equals("")) {
			model.put("msg", "Please Enter the IV No.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.checkIvNoLength(rm.getIv_no()) == false) {
			model.put("msg", validation.iv_noMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		try {
			if (iv_date.equals("")) {
				model.put("msg", "Please Enter the IV Date.");
				if (Store_type.equals("LOANAUTHORITY")) {
					return new ModelAndView("redirect:mms_loan_store");
				}
				if (Store_type.equals("SECTORAUTHORITY")) {
					return new ModelAndView("redirect:mms_sector_store");
				}
				if (Store_type.equals("ACSFP")) {
					return new ModelAndView("redirect:mms_army_store");
				}
			} else {
				iv_date_i = formatter1.parse(request.getParameter("iv_date"));
				if (validation.checkDateLength(rm.getIv_date()) == false) {
					model.put("msg", validation.dateMSG);
					if (Store_type.equals("LOANAUTHORITY")) {
						return new ModelAndView("redirect:mms_loan_store");
					}
					if (Store_type.equals("SECTORAUTHORITY")) {
						return new ModelAndView("redirect:mms_sector_store");
					}
					if (Store_type.equals("ACSFP")) {
						return new ModelAndView("redirect:mms_army_store");
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (prf_code.equals("-1")) {
			model.put("msg", "Please Select the Equipment PRF Group.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (qty.equals("")) {
			model.put("msg", "Please Enter the Quantity.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.qtyLength(request.getParameter("qty")) == false) {
			model.put("msg", validation.qtyMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}
		  
		 
		if (upload_voucher1.isEmpty()) {
			model.put("msg", "Please Upload the Voucher.");
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		if (validation.checkSplRemarksLength(request.getParameter("rem")) == false) {
			model.put("msg", validation.spl_remarksMSG);
			if (Store_type.equals("LOANAUTHORITY")) {
				return new ModelAndView("redirect:mms_loan_store");
			}
			if (Store_type.equals("SECTORAUTHORITY")) {
				return new ModelAndView("redirect:mms_sector_store");
			}
			if (Store_type.equals("ACSFP")) {
				return new ModelAndView("redirect:mms_army_store");
			}
		}

		for (int k = 1; k <= countlength; k++) {
			String i = Integer.toString(k);
			String eqpt_regn_no = request.getParameter("eqpt_regd" + i);

			if (eq.equals("")) {
				eq = eqpt_regn_no;
			} else {
				eq = eq + ":" + eqpt_regn_no;
			}
			//KAJAL
			if (m4DAO.checkIfExisteqpt_regn_no(eqpt_regn_no) != false) {
				model.put("msg", "Equipment Register Number Already exists.");
				if (Store_type.equals("LOANAUTHORITY")) {
					return new ModelAndView("redirect:mms_loan_store");
				}
				if (Store_type.equals("SECTORAUTHORITY")) {
					return new ModelAndView("redirect:mms_sector_store");
				}
				if (Store_type.equals("ACSFP")) {
					return new ModelAndView("redirect:mms_army_store");
				}
			} else {
				String regn = m4DAO.RegnNoGeneration(prf_code, eqpt_regn_no);

				if (r.equals("")) {
					r = regn;
				} else {
					r = r + ":" + regn;
				}
				rm.setEqpt_regn_no(eqpt_regn_no);
				rm.setRegn_seq_no(regn);
				rm.setStores_type(request.getParameter("store_type"));
				rm.setSaction_auth(request.getParameter("loan_sanc_auth"));
				rm.setAuth_letter_no(request.getParameter("auth_letter_no"));
				rm.setAuth_date(request.getParameter("auth_letter_date"));
				rm.setIv_no(request.getParameter("iv_no"));
				rm.setIv_date(request.getParameter("iv_date"));

				if (Store_type.equals("LOANAUTHORITY")) {
					rm.setLoan_expiry_date(request.getParameter("loan_expiry_date"));
					rm.setSector_expiry_date(null);
					rm.setType_of_hldg("A14");
					rm.setGovt_sanction_no(null);
				} else if (Store_type.equals("SECTORAUTHORITY")) {
					rm.setSector_expiry_date(request.getParameter("sector_expiry_date"));
					rm.setType_of_hldg("A5");
					rm.setGovt_sanction_no(request.getParameter("govt_letter_no"));
				} else {
					rm.setLoan_expiry_date(null);
					rm.setSector_expiry_date(null);
					rm.setType_of_hldg("A16");
					rm.setGovt_sanction_no(request.getParameter("govt_letter_no"));
				}

				rm.setPrf_code(request.getParameter("prf_code"));
				rm.setCensus_no(request.getParameter("census_no"));
				rm.setType_of_eqpt("1");
				rm.setIssued_from(request.getParameter("iv_by_sus_no"));
				rm.setIv_sus_no(request.getParameter("iv_by_sus_no"));
				rm.setQty(qt);
				rm.setFrom_sus_no(request.getParameter("iv_by_sus_no"));

				List<String> f_code1 = m1.getMMSUnitCodeList(request.getParameter("iv_by_sus_no"));
				List<String> t_code1 = m1.getMMSUnitCodeList(request.getParameter("sus_no1"));
				String f_code_val = f_code1.get(0);
				String t_code_val = t_code1.get(0);
				rm.setFrom_form_code(f_code_val);
				rm.setTo_form_code(t_code_val);
				rm.setFrom_tr_date(date);
				rm.setTo_sus_no(request.getParameter("sus_no1"));
				rm.setTo_tr_date(date);
				rm.setService_status("1");
				rm.setSpl_remarks(request.getParameter("rem"));
				rm.setData_cr_by(username);
				rm.setData_cr_date(date);
				rm.setOp_status("0");
				rm.setTfr_status("0");

				String upload_imgEXt = FilenameUtils.getExtension(upload_auth_letter1.getOriginalFilename());
				if (!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("png".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {

						model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
											
											
											if (Store_type.equals("LOANAUTHORITY")) {
												
												return new ModelAndView("redirect:mms_loan_store");
											} else if (Store_type.equals("SECTORAUTHORITY")) {
												
												return new ModelAndView("redirect:mms_sector_store");
											} else {
												
												return new ModelAndView("redirect:mms_army_store");
											}
				}

				String upload_imgEXt1 = FilenameUtils.getExtension(upload_voucher1.getOriginalFilename());
				if (!upload_imgEXt1.toUpperCase().equals("jpg".toUpperCase())
						&& !upload_imgEXt1.toUpperCase().equals("jpeg".toUpperCase())
						&& !upload_imgEXt1.toUpperCase().equals("png".toUpperCase())
						&& !upload_imgEXt1.toUpperCase().equals("pdf".toUpperCase())) {

							model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
												
												
												if (Store_type.equals("LOANAUTHORITY")) {
													
													return new ModelAndView("redirect:mms_loan_store");
												} else if (Store_type.equals("SECTORAUTHORITY")) {
													
													return new ModelAndView("redirect:mms_sector_store");
												} else {
													
													return new ModelAndView("redirect:mms_army_store");
												}
				}

				String fname = "";
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				if (!upload_auth_letter1.isEmpty()) {
					// code modify by Paresh on 05/05/2020
					try {
						byte[] bytes = upload_auth_letter1.getBytes();
						String mmsFilePath = session.getAttribute("mmsFilePath").toString();
						File dir = new File(mmsFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String filename = upload_auth_letter1.getOriginalFilename();
						String extension ="";
						 int j = filename.lastIndexOf('.');
			             if (j >= 0) {
			                 extension = filename.substring(j+1);
			             }
						fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MMSDOC."+extension;
						if (validation.checkImageAnmlLength(fname) == false) {
							model.put("msg", validation.upload_auth_letterMSG);
							if (Store_type.equals("LOANAUTHORITY")) {
								return new ModelAndView("redirect:mms_loan_store");
							}
							if (Store_type.equals("SECTORAUTHORITY")) {
								return new ModelAndView("redirect:mms_sector_store");
							}
							if (Store_type.equals("ACSFP")) {
								return new ModelAndView("redirect:mms_army_store");
							}
						}
						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						rm.setUpload_auth_letter(fname);
						;
					} catch (Exception e) {
					}
				}

				if (!upload_voucher1.isEmpty()) {
					// code modify by Paresh on 05/05/2020
					try {
						byte[] bytes = upload_voucher1.getBytes();
						String mmsFilePath = session.getAttribute("mmsFilePath").toString();
						File dir = new File(mmsFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String filename = upload_voucher1.getOriginalFilename();
						String extension ="";
						 int j = filename.lastIndexOf('.');
			             if (j >= 0) {
			                 extension = filename.substring(j+1);
			             }
						fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MMSDOC."+extension;
						if (validation.checkImageAnmlLength(fname) == false) {
							model.put("msg", validation.upload_voucher1MSG);
							if (Store_type.equals("LOANAUTHORITY")) {
								return new ModelAndView("redirect:mms_loan_store");
							}
							if (Store_type.equals("SECTORAUTHORITY")) {
								return new ModelAndView("redirect:mms_sector_store");
							}
							if (Store_type.equals("ACSFP")) {
								return new ModelAndView("redirect:mms_army_store");
							}
						}
						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						rm.setUpload_voucher(fname);
					} catch (Exception e) {
					}
				}

				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
				session1.beginTransaction();
				session1.save(rm);
				session1.getTransaction().commit();
				session1.close();
			}
		}

		String fromSUS = request.getParameter("iv_by_sus_no");
		String currentSUS = request.getParameter("sus_no1");
		String from_prf = request.getParameter("prf_code");
		String current_prf = request.getParameter("prf_code");
		String fromCensusNo = request.getParameter("census_no");
		String currentCensusNo = request.getParameter("census_no");
		String eqpt_type = "1";
		String Tran_type = "NEW";
		String auth_type = "IV";

		String op_status = "0";

		if (Store_type.equals("LOANAUTHORITY")) {
			String Holding_type = "A14";
			m4DAO.SaveHldgChange(r, eq, fromSUS, currentSUS, from_prf, current_prf, fromCensusNo, currentCensusNo,
					Holding_type, eqpt_type, Tran_type, auth_letter_no, auth_letter_date, op_status, qt);
		} else if (Store_type.equals("SECTORAUTHORITY")) {
			String Holding_type = "A16";
			m4DAO.SaveHldgChange(r, eq, fromSUS, currentSUS, from_prf, current_prf, fromCensusNo, currentCensusNo,
					Holding_type, eqpt_type, Tran_type, auth_letter_no, auth_letter_date, op_status, qt);
		} else {
			String Holding_type = "A5";
			m4DAO.SaveHldgChange(r, eq, fromSUS, currentSUS, from_prf, current_prf, fromCensusNo, currentCensusNo,
					Holding_type, eqpt_type, Tran_type, auth_letter_no, auth_letter_date, op_status, qt);
		}

		if (Store_type.equals("LOANAUTHORITY")) {
			model.put("msg", "Your Loan Data Saved Successfully");
			return new ModelAndView("redirect:mms_loan_store");
		} else if (Store_type.equals("SECTORAUTHORITY")) {
			model.put("msg", "Your Sector Data Saved Successfully");
			return new ModelAndView("redirect:mms_sector_store");
		} else {
			model.put("msg", "Your ACSFP Data Saved Successfully");
			return new ModelAndView("redirect:mms_army_store");
		}
	}

	@RequestMapping(value = "/engr_storeAction", method = RequestMethod.POST)
	public ModelAndView engr_storeAction(@ModelAttribute("engr_storeCMD") MMS_TB_REGN_ENGR_MSTR rm,
			@RequestParam(value = "upload_auth_letter1", required = false) MultipartFile upload_auth_letter1,
			@RequestParam(value = "upload_voucher1", required = false) MultipartFile upload_voucher1,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_eng_store", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int countlength = 0;
		countlength = Integer.parseInt(request.getParameter("count"));

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		Date date = new Date();

		String qty = request.getParameter("qty");
		int qt = Integer.parseInt(qty);

		String r = "";
		String eq = "";

		for (int k = 1; k <= countlength; k++) {
			String i = Integer.toString(k);
			String eqpt_regn_no = request.getParameter("eqpt_regd" + i);

			if (eq.equals("")) {
				eq = eqpt_regn_no;
			} else {
				eq = eq + ":" + eqpt_regn_no;
			}

			String prf_code = request.getParameter("prf_code");
			String regn = m4DAO.RegnNoGeneration(prf_code, eqpt_regn_no);

			if (r.equals("")) {
				r = regn;
			} else {
				r = r + ":" + regn;
			}

			rm.setEqpt_regn_no(eqpt_regn_no);
			rm.setRegn_seq_no(regn);
			rm.setStores_type(request.getParameter("store_type"));
			rm.setSaction_auth(request.getParameter("eng_sanc_auth"));
			rm.setAuth_letter_no(request.getParameter("auth_letter_no"));
			rm.setAuth_date(request.getParameter("auth_letter_date"));
			rm.setIv_no(request.getParameter("iv_no"));
			rm.setIv_date(request.getParameter("iv_date"));

			rm.setLoan_expiry_date(request.getParameter("eng_expiry_date"));
			rm.setSector_expiry_date(null);
			rm.setType_of_hldg("A14");
			rm.setGovt_sanction_no(null);

			rm.setPrf_code(request.getParameter("prf_code"));
			rm.setCensus_no(request.getParameter("census_no"));
			rm.setType_of_eqpt("1");
			rm.setIssued_from(request.getParameter("iv_by_sus_no"));
			rm.setIv_sus_no(request.getParameter("iv_by_sus_no"));
			rm.setQty(qt);
			rm.setFrom_sus_no(request.getParameter("iv_by_sus_no"));
			rm.setFrom_tr_date(date);
			rm.setTo_sus_no(request.getParameter("sus_no1"));

			List<String> f_code1 = m1.getMMSUnitCodeList(request.getParameter("iv_by_sus_no"));
			List<String> t_code1 = m1.getMMSUnitCodeList(request.getParameter("sus_no1"));
			String f_code_val = f_code1.get(0);
			
			String t_code_val ="";
			if(t_code1.size() > 0)
			{
				t_code_val = t_code1.get(0);
			}
			
			rm.setFrom_form_code(f_code_val);
			rm.setTo_form_code(t_code_val);

			rm.setTo_tr_date(date);
			rm.setService_status("1");
			rm.setSpl_remarks(request.getParameter("rem"));
			rm.setData_cr_by(username);
			rm.setData_cr_date(date);
			rm.setOp_status("0");
			rm.setTfr_status("0");

			String fname = "";
			String extension = "";

			String upload_imgEXt = FilenameUtils.getExtension(upload_auth_letter1.getOriginalFilename());
			if (!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("png".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {

				model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
				return new ModelAndView("redirect:mms_eng_store");
			}

			String upload_imgEXt1 = FilenameUtils.getExtension(upload_voucher1.getOriginalFilename());
			if (!upload_imgEXt1.toUpperCase().equals("jpg".toUpperCase())
					&& !upload_imgEXt1.toUpperCase().equals("jpeg".toUpperCase())
					&& !upload_imgEXt1.toUpperCase().equals("png".toUpperCase())
					&& !upload_imgEXt1.toUpperCase().equals("pdf".toUpperCase())) {

				model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
				return new ModelAndView("redirect:mms_eng_store");
			}

			String iv_by_unit_name = request.getParameter("iv_by_unit_name");
			String iv_by_sus_no = request.getParameter("iv_by_sus_no");
			String auth_letter_no = request.getParameter("auth_letter_no");
			String auth_letter_date = request.getParameter("auth_letter_date");
			String eng_expiry_date = request.getParameter("eng_expiry_date");
			String unit_name1 = request.getParameter("unit_name1");
			String sus_no1 = request.getParameter("sus_no1");
			String iv_no = request.getParameter("iv_no");
			String iv_date = request.getParameter("iv_date");
			
			/*
			 * String prf_code = request.getParameter("prf_code"); String qty
			 * =request.getParameter("qty"); int qt = Integer.parseInt(qty);
			 */
			 

			Date auth_letter_date_i = null;
			Date eng_expiry_date_i = null;
			Date iv_date_i = null;

			if (iv_by_unit_name.equals("")) {
				model.put("msg", "Please Enter the Issuing Auth Unit Name.");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (iv_by_sus_no.equals("")) {
				model.put("msg", "Please Enter the Issuing Auth SUS No.");
				return new ModelAndView("redirect:mms_eng_store");
			}
			if (validation.sus_noLength(request.getParameter("iv_by_sus_no")) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (auth_letter_no.equals("")) {
				model.put("msg", "Please Enter the Auth Letter No.");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (validation.checkCatPartNoLength(rm.getAuth_letter_no()) == false) {
				model.put("msg", validation.auth_lett_noMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

			try {
				if (auth_letter_date.equals("")) {
					model.put("msg", "Please Enter the Auth Letter Date.");
					return new ModelAndView("redirect:mms_eng_store");
				} else {
					auth_letter_date_i = formatter1.parse(request.getParameter("auth_letter_date"));

					
					if (validation.checkDateLength(request.getParameter("auth_letter_date")) == false) {
						model.put("msg", validation.dateMSG);
						return new ModelAndView("redirect:mms_eng_store");
					}
					 
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {

				if (eng_expiry_date.equals("")) {
					model.put("msg", "Please Enter the Loan Expiry Date.");
					return new ModelAndView("redirect:mms_eng_store");
				} else {
					eng_expiry_date_i = formatter1.parse(request.getParameter("eng_expiry_date"));
					
					if (validation.checkDateLength(request.getParameter("eng_expiry_date")) == false) {
						model.put("msg", validation.eng_expiry_dateMSG);
						return new ModelAndView("redirect:mms_eng_store");
					}	 
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (upload_auth_letter1.isEmpty()) {
				model.put("msg", "Please Upload the Auth Letter.");
				return new ModelAndView("redirect:mms_eng_store");
			}


			if (unit_name1.equals("")) {
				model.put("msg", "Please Select the UNIT NAME");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (sus_no1.equals("")) {
				model.put("msg", "Please Select the SUS No");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (validation.sus_noLength(request.getParameter("sus_no1")) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (iv_no.equals("")) {
				model.put("msg", "Please Enter the IV No.");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (validation.checkIvNoLength(rm.getIv_no()) == false) {
				model.put("msg", validation.iv_noMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			try {
				if (iv_date.equals("")) {
					model.put("msg", "Please Enter the IV Date.");
					return new ModelAndView("redirect:mms_eng_store");
				} else {
					iv_date_i = formatter1.parse(request.getParameter("iv_date"));
					if (validation.checkDateLength(request.getParameter("iv_date")) == false) {
						model.put("msg", validation.iv_dateMSG);
						return new ModelAndView("redirect:mms_eng_store");
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (prf_code.equals("-1")) {
				model.put("msg", "Please Select the Equipment PRF Group.");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (qty.equals("")) {
				model.put("msg", "Please Enter the Quantity.");
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (validation.qtyLength(request.getParameter("qty")) == false) {
				model.put("msg", validation.qtyMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (upload_voucher1.isEmpty()) {
				model.put("msg", "Please Upload the Voucher.");
				return new ModelAndView("redirect:mms_eng_store");
			}
			

			if (validation.checkSplRemarksLength(rm.getSpl_remarks()) == false) {
				model.put("msg", validation.spl_remarksMSG);
				return new ModelAndView("redirect:mms_eng_store");
			}

			if (!upload_auth_letter1.isEmpty()) {
				// code modify by Paresh on 05/05/2020
				try {
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = upload_auth_letter1.getBytes();
					String mmsFilePath = session.getAttribute("mmsFilePath").toString();
					File dir = new File(mmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = upload_auth_letter1.getOriginalFilename();
					String extension1 ="";
					 int j = filename.lastIndexOf('.');
		             if (j >= 0) {
		                 extension1 = filename.substring(j+1);
		             }
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MMSDOC."+extension1;
					if (validation.checkImageAnmlLength(fname) == false) {
						model.put("msg", validation.Upload_authLetterMSG);
						return new ModelAndView("redirect:mms_eng_store");
					}
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					rm.setUpload_auth_letter(fname);
				} catch (Exception e) {
				}
			}

			if (!upload_voucher1.isEmpty()) {
				// code modify by Paresh on 05/05/2020
				try {
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = upload_voucher1.getBytes();
					String mmsFilePath = session.getAttribute("mmsFilePath").toString();
					File dir = new File(mmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = upload_voucher1.getOriginalFilename();
					String extension1 ="";
					 int j = filename.lastIndexOf('.');
		             if (j >= 0) {
		                 extension1 = filename.substring(j+1);
		             }
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MMSDOC."+extension1;
					
					if (validation.checkImageAnmlLength(fname) == false) {
						model.put("msg", validation.upload_voucher1MSG);
						return new ModelAndView("redirect:mms_eng_store");
					}
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					rm.setUpload_voucher(fname);
				} catch (Exception e) {
				}
			}

			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			session1.beginTransaction();
			session1.save(rm);
			session1.getTransaction().commit();
			session1.close();

		}

		String fromSUS = request.getParameter("iv_by_sus_no");
		String currentSUS = request.getParameter("sus_no1");
		String from_prf = request.getParameter("prf_code");
		String current_prf = request.getParameter("prf_code");
		String fromCensusNo = request.getParameter("census_no");
		String currentCensusNo = request.getParameter("census_no");
		String eqpt_type = "1";
		String Tran_type = "NEW";
		String auth_type = "IV";
		String auth_letter_no = request.getParameter("iv_no");
		String auth_letter_date = request.getParameter("auth_letter_date");
		String op_status = "0";

		String Holding_type = "A14";

		m4DAO.SaveHldgChange(r, eq, fromSUS, currentSUS, from_prf, current_prf, fromCensusNo, currentCensusNo,
				Holding_type, eqpt_type, Tran_type, auth_letter_no, auth_letter_date, op_status, qt);

		model.put("msg", "Your Engineer Store Data Saved Successfully");
		return new ModelAndView("redirect:mms_eng_store");
	}
	// (2) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS (TO ADD) END)

	// (3) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS (TO SEARCH & VIEW)
	// START)
	@RequestMapping(value = "/admin/search_store_data", method = RequestMethod.POST)
	public ModelAndView loan_data(@ModelAttribute("m2_sus") String m2_sus, String m2_stat, String m2_frmdt,
			String m2_todt, String m2_unit, String m2_para, ModelMap model, HttpSession s1) {

		String username = (String) s1.getAttribute("username");
		int roleid = (Integer) s1.getAttribute("roleid");
		int userid = (Integer) s1.getAttribute("userId");
		String roleType = (String) s1.getAttribute("roleType");
		String accsLvl = (String) s1.getAttribute("roleAccess");
		String accssubLvl = (String) s1.getAttribute("roleAccess");

		Boolean val = null;
		if (m2_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_store_search", s1.getAttribute("roleid").toString());
		}
		if (m2_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_store_search", s1.getAttribute("roleid").toString());
		}
		if (m2_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_army_store_search", s1.getAttribute("roleid").toString());
		}
		if (m2_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_store_search", s1.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(m2_sus.isEmpty()){
			 model.put("msg", "Please Select the To SUS.");
			 if(m2_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_store_search"); 
				}
			 if(m2_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_store_search"); 
				}
			 if(m2_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_army_store_search"); 
				}
			 if(m2_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_store_search"); 
				}
			  
		 }
		
		if(validation.sus_noLength(m2_sus) == false){
	 		model.put("msg",validation.sus_noMSG);
			 if(m2_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_store_search"); 
				}
			 if(m2_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_store_search"); 
				}
			 if(m2_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_army_store_search"); 
				}
			 if(m2_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_store_search"); 
				}
		 	 	
			}
		
	
		
		if(m2_unit.isEmpty()){
			 model.put("msg", "Please Select the To Unit.");
			 if(m2_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_store_search"); 
				}
			 if(m2_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_store_search"); 
				}
			 if(m2_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_army_store_search"); 
				}
			 if(m2_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_store_search"); 
				}
			  
		 }
		
		if(validation.checkUnit_nameLength(m2_unit) == false){
	 		model.put("msg",validation.unit_nameMSG);
			 if(m2_para.equals("VLS")){	
					return new ModelAndView("redirect:mms_loan_store_search"); 
				}
			 if(m2_para.equals("VSS")){	
					return new ModelAndView("redirect:mms_sector_store_search"); 
				}
			 if(m2_para.equals("VAS")){	
					return new ModelAndView("redirect:mms_army_store_search"); 
				}
			 if(m2_para.equals("ENG")){	
					return new ModelAndView("redirect:mms_eng_store_search"); 
				}
		 	 	
			}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(s1);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		model.put("r_1", l1);

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		if (!m2_stat.equals("3")) {
			qry += " and op_status =:op_status";
		}

		if (!(m2_sus.equals("") || m2_sus.equals(null))) {
			qry += " and to_sus_no =:to_sus_no";
		}

		if (!(m2_frmdt.equals("") || m2_frmdt.equals(null))) {
			qry += " and data_cr_date BETWEEN DATE(:from_date)";
		}

		if (!m2_todt.equals("")) {
			qry += " and DATE(:curr_date) + 1";
		} else {
			qry += " and DATE('" + date + "') + 1";
		}

		if (m2_para.equalsIgnoreCase("VLS")) {
			q = session.createQuery(
					"select distinct r.iv_no,r.iv_date,r.prf_code,(select prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=r.prf_code),"
							+ "r.census_no,(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
							+ "r.op_status,(select count(eqpt_regn_no) from Mms_tb_regn_oth_mstr where iv_no=r.iv_no and op_status=r.op_status)"
							+ " from Mms_tb_regn_oth_mstr r where stores_type='LOANAUTHORITY'" + qry + "");
		}

		else if (m2_para.equalsIgnoreCase("VSS")) {
			q = session.createQuery(
					"select distinct r.iv_no,r.iv_date,r.prf_code,(select prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=r.prf_code),"
							+ "r.census_no,(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
							+ "r.op_status,(select count(eqpt_regn_no) from Mms_tb_regn_oth_mstr where iv_no=r.iv_no and op_status=r.op_status)"
							+ " from Mms_tb_regn_oth_mstr r where stores_type='SECTORAUTHORITY'" + qry + "");
		}

		else if (m2_para.equalsIgnoreCase("VAS")) {
			q = session.createQuery(
					"select distinct r.iv_no,r.iv_date,r.prf_code,(select prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=r.prf_code),"
							+ "r.census_no,(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
							+ "r.op_status,(select count(eqpt_regn_no) from Mms_tb_regn_oth_mstr where iv_no=r.iv_no and op_status=r.op_status)"
							+ " from Mms_tb_regn_oth_mstr r where stores_type='ACSFP'" + qry + "");
		} else if (m2_para.equalsIgnoreCase("ENG")) {
			q = session.createQuery(
					"select distinct r.iv_no,r.iv_date,r.prf_code,(select prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=r.prf_code),"
							+ "r.census_no,(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
							+ "r.op_status,(select count(eqpt_regn_no) from Mms_tb_regn_oth_mstr where iv_no=r.iv_no and op_status=r.op_status)"
							+ " from Mms_tb_regn_oth_mstr r where stores_type='ENGAUTHORITY'" + qry + "");
		}
		if (!m2_stat.equals("3")) {
			q.setParameter("op_status", m2_stat);
		}

		if (!(m2_sus.equals("") || m2_sus.equals(null))) {
			q.setParameter("to_sus_no", m2_sus);
		}

		q.setParameter("from_date", m2_frmdt).setParameter("curr_date", m2_todt);

		@SuppressWarnings("unchecked")
		List<Mms_tb_regn_oth_mstr> list = (List<Mms_tb_regn_oth_mstr>) q.list();
		tx.commit();
		session.close();

		model.put("m_1", list);
		model.put("m_2", m2_sus);
		model.put("m_3", m2_stat);
		model.put("m_4", m2_frmdt);
		model.put("m_5", m2_todt);
		model.put("m_6", m2_unit);

		if (m2_para.equals("VLS")) {
			return new ModelAndView("mms_loan_stores_searchTiles");
		}
		if (m2_para.equals("VSS")) {
			return new ModelAndView("mms_sector_stores_searchTiles");
		}
		if (m2_para.equals("VAS")) {
			return new ModelAndView("mms_army_stores_searchTiles");
		}
		if (m2_para.equals("ENG")) {
			return new ModelAndView("mms_eng_stores_searchTiles");
		}
		return null;
	}

	@RequestMapping(value = "/admin/view_store_data", method = RequestMethod.POST)
	public ModelAndView view_store_data(@ModelAttribute("v_sus") String v_sus, String v_unit, String v_para,
			String v_iv, String v_ivdt, String v_cens, String v_nomen, String v_stat, String v_frm, String v_to,
			ModelMap model, HttpSession s1) {

		Boolean val = null;
		if (v_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_store_search", s1.getAttribute("roleid").toString());
		}
		if (v_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_store_search", s1.getAttribute("roleid").toString());
		}
		if (v_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_army_store_search", s1.getAttribute("roleid").toString());
		}
		if (v_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_store_search", s1.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String stores_type = null;
		if (v_para.equals("VLS")) {
			stores_type = "LOANAUTHORITY";
		}
		if (v_para.equals("VSS")) {
			stores_type = "SECTORAUTHORITY";
		}
		if (v_para.equals("VAS")) {
			stores_type = "ACSFP";
		}
		if (v_para.equals("ENG")) {
			stores_type = "ENGAUTHORITY";
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct r.type_of_hldg,r.type_of_eqpt,r.eqpt_regn_no,r.to_sus_no,"
				+ "(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.to_sus_no and status_sus_no ='Active'),"
				+ "(select label from MMS_Domain_Values where domainid='TYPEOFHOLDING' and codevalue=r.type_of_hldg),"
				+ "(select label from MMS_Domain_Values where domainid='TYPEOFEQPT' and codevalue=r.type_of_eqpt),r.op_status"
				+ " from Mms_tb_regn_oth_mstr r where op_status=:op_status and stores_type=:stores_type and census_no=:census_no"
				+ " and iv_no=:iv_no");
		q.setParameter("op_status", v_stat).setParameter("stores_type", stores_type).setParameter("census_no", v_cens)
				.setParameter("iv_no", v_iv);
		@SuppressWarnings("unchecked")
		List<Mms_tb_regn_oth_mstr> list = (List<Mms_tb_regn_oth_mstr>) q.list();
		tx.commit();
		session.close();

		model.put("m_1", list);
		model.put("v_1", v_sus);
		model.put("v_2", v_unit);
		model.put("v_3", v_para);
		model.put("v_4", v_iv);
		model.put("v_5", v_ivdt);
		model.put("v_6", v_cens);
		model.put("v_7", v_nomen);
		model.put("v_8", v_stat);
		model.put("v_9", v_frm);
		model.put("v_10", v_to);

		if (v_para.equals("VLS")) {
			return new ModelAndView("mms_loan_stores_viewTiles");
		}
		if (v_para.equals("VSS")) {
			return new ModelAndView("mms_sector_stores_viewTiles");
		}
		if (v_para.equals("VAS")) {
			return new ModelAndView("mms_army_stores_viewTiles");
		}
		if (v_para.equals("ENG")) {
			return new ModelAndView("mms_eng_stores_viewTiles");
		}
		return null;
	}

	@RequestMapping(value = "/admin/back_store", method = RequestMethod.POST)
	public ModelAndView back_store(@ModelAttribute("b_sus") String b_sus, String b_unit, String b_para, String b_stat,
			String b_frm, String b_to, ModelMap model, HttpSession session) {

		Boolean val = null;
		if (b_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_store_search", session.getAttribute("roleid").toString());
		}
		if (b_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_store_search", session.getAttribute("roleid").toString());
		}
		if (b_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_army_store_search", session.getAttribute("roleid").toString());
		}
		if (b_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_store_search", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("b_1", b_sus);
		model.put("b_2", b_unit);
		model.put("b_3", b_stat);
		model.put("b_4", b_frm);
		model.put("b_5", b_to);

		if (b_para.equals("VLS")) {
			return new ModelAndView("mms_loan_stores_searchTiles");
		}
		if (b_para.equals("VSS")) {
			return new ModelAndView("mms_sector_stores_searchTiles");
		}
		if (b_para.equals("VAS")) {
			return new ModelAndView("mms_army_stores_searchTiles");
		}
		if (b_para.equals("ENG")) {
			return new ModelAndView("mms_eng_stores_searchTiles");
		}
		return null;
	}

	@RequestMapping(value = "/admin/approve_store_data", method = RequestMethod.POST)
	public ModelAndView approve_store_data(@ModelAttribute("a_sus") String a_sus, String a_iv, String a_cen,
			String a_para, ModelMap model, HttpSession session) {

		Boolean val = null;
		if (a_para.equals("VLS")) {
			val = roledao.ScreenRedirect("mms_loan_store_search", session.getAttribute("roleid").toString());
		}
		if (a_para.equals("VSS")) {
			val = roledao.ScreenRedirect("mms_sector_store_search", session.getAttribute("roleid").toString());
		}
		if (a_para.equals("VAS")) {
			val = roledao.ScreenRedirect("mms_army_store_search", session.getAttribute("roleid").toString());
		}
		if (a_para.equals("ENG")) {
			val = roledao.ScreenRedirect("mms_eng_store_search", session.getAttribute("roleid").toString());
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String username = session.getAttribute("username").toString();
		String stores_type = null;
		if (a_para.equals("VLS")) {
			stores_type = "LOANAUTHORITY";
		}
		if (a_para.equals("VSS")) {
			stores_type = "SECTORAUTHORITY";
		}
		if (a_para.equals("VAS")) {
			stores_type = "ACSFP";
		}
		if (a_para.equals("ENG")) {
			stores_type = "ENGAUTHORITY";
		}

		String hqlUpdate = "update Mms_tb_regn_oth_mstr set op_status='1',data_app_by=:username,data_app_date=:dt where stores_type=:stores_type and iv_no=:iv_no and to_sus_no =:to_sus_no "
				+ "and census_no=:census_no and op_status='0'";
		Session s1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s1.beginTransaction();
		Query q1 = s1.createQuery(hqlUpdate);
		q1.setParameter("username", username).setParameter("dt", new Date()).setParameter("stores_type", stores_type)
				.setParameter("iv_no", a_iv).setParameter("to_sus_no", a_sus).setParameter("census_no", a_cen);
		q1.executeUpdate();
		tx.commit();
		s1.close();

		model.put("msg", "Your Data Approved Successfully");

		if (a_para.equals("VLS")) {
			return new ModelAndView("redirect:mms_loan_store_search");
		}
		if (a_para.equals("VSS")) {
			return new ModelAndView("redirect:mms_sector_store_search");
		}
		if (a_para.equals("VAS")) {
			return new ModelAndView("redirect:mms_army_store_search");
		}
		if (a_para.equals("ENG")) {
			return new ModelAndView("redirect:mms_eng_store_search");
		}
		return null;
	}
	// (3) -> (LOAN, SECTOR, ACSFP, ENGR MODULE SCREEN METHODS (TO SEARCH & VIEW)
	// END)
}