package com.controller.Reports;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.Reports.CUE_ReportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CUE_ReportController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController validation = new ValidationController();

	@Autowired
	CUE_ReportDAO ReportsDao;

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/1_APPT_RANK_WISEUrl", method = RequestMethod.GET)
	public ModelAndView APPT_RANK_WISEUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_APPT_RANK_WISEUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("1_APPT_RANK_WISEtiles");
	}

	@RequestMapping(value = "/ue_persnl_appntrnkwise", method = RequestMethod.POST)
	public ModelAndView ue_persnl_appntrnkwise(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "sus_no1", required = false) String sus) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_APPT_RANK_WISEUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.sus_noLength(sus) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:1_APPT_RANK_WISEUrl");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ue_persnl_appntrnkwiserprt(from, to, sus);
		Mmap.put("list", list);
		Mmap.put("sus_no1", sus);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("1_APPT_RANK_WISEtiles");
	}

	@RequestMapping(value = "/2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl", method = RequestMethod.GET)
	public ModelAndView DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("2_DATASHEET_FOR_OFFRS_JCO_OR_CIVtiles");
	}

	@RequestMapping(value = "/ue_pers_data_officers", method = RequestMethod.POST)
	public ModelAndView ue_pers_data_officers(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "sus_no1", required = false) String sus) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkRemarksLength(rank_cat) == false) {
			Mmap.put("msg", "Rank Category Should Contain Atleast 255 Characters");
			return new ModelAndView("redirect:2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl");
		}

		if (validation.sus_noLength(sus) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ue_pers_data_officersreport(from, to, rank_cat, sus);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("sus_no1", sus);

		return new ModelAndView("2_DATASHEET_FOR_OFFRS_JCO_OR_CIVtiles");
	}

	@RequestMapping(value = "/3_DSGN_CAPACTY_OF_TRG_CENTRESUrl", method = RequestMethod.GET)
	public ModelAndView DSGN_CAPACTY_OF_TRG_CENTREStiles(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_DATASHEET_FOR_OFFRS_JCO_OR_CIVUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("3_DSGN_CAPACTY_OF_TRG_CENTREStiles");
	}

	@RequestMapping(value = "/design_training_cap", method = RequestMethod.POST)
	public ModelAndView design_training_cap(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("3_DSGN_CAPACTY_OF_TRG_CENTRESUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.design_training_capreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("3_DSGN_CAPACTY_OF_TRG_CENTREStiles");
	}

	@RequestMapping(value = "/4_USR_ARM_PRNT_ARM_WISE_STRENGTHUrl", method = RequestMethod.GET)
	public ModelAndView USR_ARM_PRNT_ARM_WISE_STRENGTHUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_USR_ARM_PRNT_ARM_WISE_STRENGTHUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("4_USR_ARM_PRNT_ARM_WISE_STRENGTHtiles");
	}

	@RequestMapping(value = "/auth_str_allunt", method = RequestMethod.POST)
	public ModelAndView auth_str_allunt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_USR_ARM_PRNT_ARM_WISE_STRENGTHUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.auth_str_alluntreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("4_USR_ARM_PRNT_ARM_WISE_STRENGTHtiles");
	}

	@RequestMapping(value = "/5_USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOUrl", method = RequestMethod.GET)
	public ModelAndView USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOtiles(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("5_USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOtiles");
	}

	@RequestMapping(value = "/allontmnt_of_parntarm", method = RequestMethod.POST)
	public ModelAndView allontmnt_of_parntarm(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.allontmnt_of_parntarmreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("5_USR_ARM_PRNT_ARM_WISE_STRENGTH_JCOtiles");
	}

	@RequestMapping(value = "/6_USR_ARM_PRNT_ARM_WISE_STRENGTH_ORUrl", method = RequestMethod.GET)
	public ModelAndView USR_ARM_PRNT_ARM_WISE_STRENGTH_ORUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("6_USR_ARM_PRNT_ARM_WISE_STRENGTH_ORUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("6_USR_ARM_PRNT_ARM_WISE_STRENGTH_ORtiles");
	}

	@RequestMapping(value = "/allontmnt_of_vanc", method = RequestMethod.POST)
	public ModelAndView allontmnt_of_vanc(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("6_USR_ARM_PRNT_ARM_WISE_STRENGTH_ORUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.allontmnt_of_vancreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("6_USR_ARM_PRNT_ARM_WISE_STRENGTH_ORtiles");
	}

	@RequestMapping(value = "7_TOTAL_AUTH_RANK_WISEURL", method = RequestMethod.GET)
	public ModelAndView TOTAL_AUTH_RANK_WISEeURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("7_TOTAL_AUTH_RANK_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("7_TOTAL_AUTH_RANK_WISEiles");
	}

	@RequestMapping(value = "/TOTAL_AUTH_RANK_WISEE", method = RequestMethod.POST)
	public ModelAndView TOTAL_AUTH_RANK_WISEe(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("7_TOTAL_AUTH_RANK_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:7_TOTAL_AUTH_RANK_WISEURL");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.TOTAL_AUTH_RANK_WISEreport(from, to, sus_no);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("sus_no1", sus_no);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("7_TOTAL_AUTH_RANK_WISEiles");
	}

	@RequestMapping(value = "/8_AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMURL", method = RequestMethod.GET)
	public ModelAndView AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMURL(ModelMap Mmap,HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("8_AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMURL",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("8_AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARM", method = RequestMethod.POST)
	public ModelAndView AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARM(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("8_AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMURL",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao
				.AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("8_AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/9_AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPURL", method = RequestMethod.GET)
	public ModelAndView AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("9_AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("9_AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPtiles");
	}

	@RequestMapping(value = "/AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSP", method = RequestMethod.POST)
	public ModelAndView AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSP(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("9_AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPreport(from,
				to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("9_AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPtiles");
	}

	@RequestMapping(value = "/10_AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMURL", method = RequestMethod.GET)
	public ModelAndView AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("10_AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("10_AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARM", method = RequestMethod.POST)
	public ModelAndView AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARM(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("10_AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao
				.AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("10_AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/11_TOTAL_AUTH_RANK_WISEURL", method = RequestMethod.GET)
	public ModelAndView TOTAL_AUTH_RANK_WISEURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("11_TOTAL_AUTH_RANK_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("11_TOTAL_AUTH_RANK_WISEtiles");
	}

	@RequestMapping(value = "/TOTAL_AUTH_RANK", method = RequestMethod.POST)
	public ModelAndView TOTAL_AUTH_RANK_WISE(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("11_TOTAL_AUTH_RANK_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:11_TOTAL_AUTH_RANK_WISEURL");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.TOTAL_AUTH_RANK_WISE_REPORT1(from, to, sus_no);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("sus_no1", sus_no);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("11_TOTAL_AUTH_RANK_WISEtiles");
	}

	@RequestMapping(value = "/12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEURL", method = RequestMethod.GET)
	public ModelAndView TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEtiles");
	}

	@RequestMapping(value = "/12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISE", method = RequestMethod.POST)
	public ModelAndView TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISE(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEURL");
		}
		ArrayList<ArrayList<String>> list = ReportsDao.TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEreport(from, to, sus_no);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("sus_no1", sus_no);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEtiles");
	}

	@RequestMapping(value = "/13_AUTH_USER_ARM_WISEURL", method = RequestMethod.GET)
	public ModelAndView AUTH_USER_ARM_WISEURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("13_AUTH_USER_ARM_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("13_AUTH_USER_ARM_WISEtiles");
	}

	@RequestMapping(value = "/13_AUTH_USER_ARM_WISE", method = RequestMethod.POST)
	public ModelAndView AUTH_USER_ARM_WISE(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("13_AUTH_USER_ARM_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.AUTH_USER_ARM_WISEreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("13_AUTH_USER_ARM_WISEtiles");
	}

	@RequestMapping(value = "/14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONURL", method = RequestMethod.GET)
	public ModelAndView AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONtiles");
	}

	@RequestMapping(value = "/14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATION", method = RequestMethod.POST)
	public ModelAndView AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATION(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("14_AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONtiles");
	}

	@RequestMapping(value = "/15_AUTH_ESTB_JCO_ORURL", method = RequestMethod.GET)
	public ModelAndView AUTH_ESTB_JCO_ORURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("15_AUTH_ESTB_JCO_ORURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("15_AUTH_ESTB_JCO_ORtiles");
	}

	@RequestMapping(value = "/15_AUTH_ESTB_JCO_OR", method = RequestMethod.POST)
	public ModelAndView AUTH_ESTB_JCO_OR(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("15_AUTH_ESTB_JCO_ORURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.AUTH_ESTB_JCO_OR(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("15_AUTH_ESTB_JCO_ORtiles");
	}

	@RequestMapping(value = "/16_AUTH_OF_OFF_UNIT_WISEURL", method = RequestMethod.GET)
	public ModelAndView AUTH_OF_OFF_UNIT_WISEURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("16_AUTH_OF_OFF_UNIT_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("16_AUTH_OF_OFF_UNIT_WISEtiles");
	}

	@RequestMapping(value = "/16_AUTH_OF_OFF_UNIT_WISE", method = RequestMethod.POST)
	public ModelAndView AUTH_OF_OFF_UNIT_WISE(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("16_AUTH_OF_OFF_UNIT_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.AUTH_OF_OFF_UNIT_WISEreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("16_AUTH_OF_OFF_UNIT_WISEtiles");
	}

	@RequestMapping(value = "/17_MAJ_MIN_UNIT_CMD_WISEURL", method = RequestMethod.GET)
	public ModelAndView MAJ_MIN_UNIT_CMD_WISEURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("17_MAJ_MIN_UNIT_CMD_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("17_MAJ_MIN_UNIT_CMD_WISEtiles");
	}

	@RequestMapping(value = "/17_MAJ_MIN_UNIT_CMD_WISE", method = RequestMethod.POST)
	public ModelAndView MAJ_MIN_UNIT_CMD_WISE(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("17_MAJ_MIN_UNIT_CMD_WISEURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", "Please Enter 8 Number SUS No");
			return new ModelAndView("redirect:17_MAJ_MIN_UNIT_CMD_WISEURL");
		}
		ArrayList<ArrayList<String>> list = ReportsDao.MAJ_MIN_UNIT_CMD_WISEreport(from, to, sus_no);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("sus_no1", sus_no);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("17_MAJ_MIN_UNIT_CMD_WISEtiles");
	}

	@RequestMapping(value = "/18_MAJ_MIN_UNITSURL", method = RequestMethod.GET)
	public ModelAndView MAJ_MIN_UNITSURL(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("18_MAJ_MIN_UNITSURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("18_MAJ_MIN_UNITStiles");
	}

	@RequestMapping(value = "/18_MAJ_MIN_UNITS", method = RequestMethod.POST)
	public ModelAndView MAJ_MIN_UNITS(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("18_MAJ_MIN_UNITSURL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.MAJ_MIN_UNITSreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("18_MAJ_MIN_UNITStiles");
	}

	@RequestMapping(value = "/20_TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSUrl", method = RequestMethod.GET)
	public ModelAndView TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("20_TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("20_TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMStiles");
	}

	@RequestMapping(value = "/TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMS", method = RequestMethod.POST)
	public ModelAndView TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMS(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {
		// @RequestParam(value = "item_type1", required = false) String item_type) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("20_TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		// Mmap.put("item_type1", item_type);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("20_TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMStiles");
	}

	@RequestMapping(value = "/ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMUrl", method = RequestMethod.GET)
	public ModelAndView ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMcurl", method = RequestMethod.POST)
	public ModelAndView ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMcurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMtiles");
	}

	@RequestMapping(value = "/all_india_auth_B_vehurl", method = RequestMethod.GET)
	public ModelAndView all_india_auth_B_vehurl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_auth_B_vehurl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_auth_B_vehtiles");
	}

	@RequestMapping(value = "/all_india_auth_B", method = RequestMethod.POST)
	public ModelAndView all_india_auth_B(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_auth_B_vehurl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.all_india_auth_B_veh_rprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_auth_B_vehtiles");
	}

	@RequestMapping(value = "/all_india_auth_A_vehurl", method = RequestMethod.GET)
	public ModelAndView all_india_auth_A_vehurl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_auth_A_vehurl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_auth_A_vehtiles");
	}

	@RequestMapping(value = "/all_india_auth_A", method = RequestMethod.POST)
	public ModelAndView all_india_auth_A(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_auth_A_vehurl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.all_india_auth_A_veh_rprt(from, to);

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_auth_A_vehtiles");
	}

	@RequestMapping(value = "all_india_ff_nff_B_veh", method = RequestMethod.GET)
	public ModelAndView all_india_ff_nff_B_veh(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_ff_nff_B_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_ff_nff_B_veh_tiles");
	}

	@RequestMapping(value = "/all_india_ff_nff_B_vehurl", method = RequestMethod.POST)
	public ModelAndView all_india_ff_nff_B_vehurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_ff_nff_B_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.all_india_ff_nff_B_vehreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_ff_nff_B_veh_tiles");
	}

	@RequestMapping(value = "/all_india_ff_nff_A_veh", method = RequestMethod.GET)
	public ModelAndView all_india_ff_nff_A_veh(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_ff_nff_A_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_ff_nff_A_veh_tiles");
	}

	@RequestMapping(value = "/all_india_ff_nff_A_vehurl", method = RequestMethod.POST)
	public ModelAndView all_india_ff_nff_A_vehurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_india_ff_nff_A_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.all_india_ff_nff_A_vehreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_ff_nff_A_veh_tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENT_OF_B_VEH_W_O_RESERVEUrl", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_UNIT_ENT_OF_B_VEH_W_O_RESERVEUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENT_OF_B_VEH_W_O_RESERVEUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_unit_ent_of_b_veh_w_o_reservetiles");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENT_OF_B_VEH_RES", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_UNIT_ENT_OF_B_VEH_RES(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENT_OF_B_VEH_W_O_RESERVEUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALL_INDIA_UNIT_ENT_OF_B_VEH_RESERVErprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_unit_ent_of_b_veh_w_o_reservetiles");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_RSV_FF_NFF", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_RSV_FF_NFF(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_RSV_FF_NFF", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITHurl", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITHurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_RSV_FF_NFF", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITHreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITH_tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC_TILES");
	}

	@RequestMapping(value = "/ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DECURL", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DECURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC_TILES");
	}

	@RequestMapping(value = "/DATA_VERIFICATION_REPORT", method = RequestMethod.GET)
	public ModelAndView DATA_VERIFICATION_REPORT(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("DATA_VERIFICATION_REPORT", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("DATA_VERIFICATION_REPORT_TILES");
	}

	@RequestMapping(value = "/DATA_VERIFICATION_REPORTURL", method = RequestMethod.POST)
	public ModelAndView DATA_VERIFICATION_REPORTURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("DATA_VERIFICATION_REPORT", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.DATA_VERIFICATIONreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("DATA_VERIFICATION_REPORT_TILES");
	}

	@RequestMapping(value = "/ALL_INDIA_AUTH_A_VEH", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_AUTH_A_VEH(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_AUTH_A_VEH", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALL_INDIA_AUTH_A_VEH_tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_AUTH_A_VEHurl", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_AUTH_A_VEHurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_AUTH_A_VEH", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALL_INDIA_AUTH_A_VEHreport(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALL_INDIA_AUTH_A_VEH_tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_AUTH_A_VEH_COMD", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_AUTH_A_VEH_COMD(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_AUTH_A_VEH_COMD", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALL_INDIA_AUTH_A_VEH_COMD_TILES");
	}

	@RequestMapping(value = "/ALL_INDIA_AUTH_A_VEH_COMDURL", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_AUTH_A_VEH_COMDURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ALL_INDIA_AUTH_A_VEH_COMD", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.ALL_INDIA_AUTH_A_VEHCOMD(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALL_INDIA_AUTH_A_VEH_COMD_TILES");
	}

	@RequestMapping(value = "/item_card_rpUrl")
	public ModelAndView item_card_rpUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("item_card_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("item_cardTiles");
	}

	@RequestMapping(value = "/item_card_rp", method = RequestMethod.POST)
	public ModelAndView item_card_rp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("item_card_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.item_card_report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("item_cardTiles");
	}

	@RequestMapping(value = "/unit_card_rpUrl")
	public ModelAndView unit_card_rpUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_card_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("unit_card_tiles");
	}

	@RequestMapping(value = "/unit_card_rp", method = RequestMethod.POST)
	public ModelAndView unit_card_rp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_card_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.unit_card_report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("unit_card_tiles");
	}

	@RequestMapping(value = "/all_ind_ue_rpUrl")
	public ModelAndView all_ind_ue_rpUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_ind_ue_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("all_india_ue_tiles");
	}

	@RequestMapping(value = "/all_ind_ue_rp", method = RequestMethod.POST)
	public ModelAndView all_ind_ue_rp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("all_ind_ue_rpUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.all_india_ue_report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("all_india_ue_tiles");
	}

	@RequestMapping(value = "/comd_wise_ueUrl", method = RequestMethod.GET)
	public ModelAndView comd_wise_ueUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("comd_wise_ueUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("msg", msg);
		return new ModelAndView("comd_wise_ue_tiles");
	}

	@RequestMapping(value = "/comd_wise_ue_rp", method = RequestMethod.POST)
	public ModelAndView comd_wise_ue_tiles_rp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "comd_name1", required = false) String comd_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("comd_wise_ueUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.comd_wise_report(from, to, comd_name);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("comd_name1", comd_name);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("comd_wise_ue_tiles");
	}

	@RequestMapping(value = "/comd_wise_ue_reportUrl", method = RequestMethod.GET)
	public ModelAndView comd_wise_ue_reportUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("comd_wise_ue_reportUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("comd_wise_ue_reporttiles");
	}

	@RequestMapping(value = "/comd_wise_ue_reportrp", method = RequestMethod.POST)
	public ModelAndView comd_wise_ue_reportrp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("comd_wise_ue_reportUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.comd_wise_ue_report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("comd_wise_ue_reporttiles");
	}

	@RequestMapping(value = "/item_card_comdUrl", method = RequestMethod.GET)
	public ModelAndView item_card_comdUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("item_card_comdUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("item_card_comdtiles");
	}

	@RequestMapping(value = "/item_card_comdrp", method = RequestMethod.POST)
	public ModelAndView item_card_comdrp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "item_type1", required = false) String item_type) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("item_card_comdUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkRemarksLength(item_type) == false) {
			Mmap.put("msg", "Item Type Should Contain Atleast 255 Characters");
			return new ModelAndView("redirect:item_card_comdUrl");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.item_card_comd_report(from, to, item_type);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("item_type1", item_type);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("item_card_comdtiles");
	}

	@RequestMapping(value = "/unit_card_comdUrl", method = RequestMethod.GET)
	public ModelAndView unit_card_comdUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_card_comdUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("unit_card_comdtiles");
	}

	@RequestMapping(value = "/unit_card_comdrp", method = RequestMethod.POST)
	public ModelAndView unit_card_comdrp(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "unit_name1", required = false) String unit_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_card_comdUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:unit_card_comdUrl");
		}

		ArrayList<ArrayList<String>> list = ReportsDao.unit_card_comd_report(from, to, unit_name);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("unit_name1", unit_name);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("unit_card_comdtiles");
	}

	@RequestMapping(value = "/getcomd_name_weap_report")
	public @ResponseBody List<String> getcomd_name_weap_report(String comd_name, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select unit_name \r\n" + " from Miso_Orbat_Unt_Dtl \r\n"
				+ " where sus_no in(select tbl_codesf0_.sus_no as col_0_0_ from \r\n"
				+ " Tbl_CodesForm tbl_codesf0_ where tbl_codesf0_.level_in_hierarchy='Command') and upper(unit_name) like '%"
				+ comd_name.toUpperCase() + "%'  and status_sus_no='Active'").setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/getitemtype_report", method = RequestMethod.POST)
	public @ResponseBody List<String> getitemtype_report(String item_type, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code !='' and status='1' and  status_active='Active' and item_type like '%"
						+ item_type.toUpperCase() + "%' order by item_type")
				.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/get_unit_name_report", method = RequestMethod.POST)
	public @ResponseBody List<String> get_unit_name_report(String unit_name, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and unit_name like '%"
						+ unit_name.toUpperCase() + "%' order by unit_name")
				.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/get_rank_cat_report", method = RequestMethod.POST)
	public @ResponseBody List<String> get_rank_cat_report(String rank_cat, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("SELECT label FROM T_Domain_Value WHERE domainid='RANKCATEGORY' AND upper(label) like '%"
						+ rank_cat.toUpperCase() + "%' ")
				.setMaxResults(20);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {

			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

}