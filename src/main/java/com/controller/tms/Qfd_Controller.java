package com.controller.tms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.controller.ExportFile.ExcelUserListReportViewsc_st;
import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Oh_Qfd_DAO;
import com.dao.tms.Qfd_DAO;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_VEH_CAT_LINK;
import com.models.Tbl_CodesForm;
import com.models.Tms_Banum_Req_Child;

import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Qfd_Controller {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private  Qfd_DAO Q_dao;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@RequestMapping(value = "/Qfd_url")
	public ModelAndView Qfd_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Qfd_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String select="<option value='0'>-- Select --</option>";
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		if(roleAccess.equals("Line_dte")) {
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			model.put("selectLine_dte", select);
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		List<Tbl_CodesForm> comd=all.getCommandDetailsList();
		model.put("getCommandList",comd);
		model.put("getTypeOfUnitList", all.getTypeOfUnitList());
		model.put("getLine_DteList2", roledao.getLine_DteList(""));
		return new ModelAndView("Qfd_Tiles");
	}
	
	
	@RequestMapping(value = "/getSearch_qfd_report" , method = RequestMethod.POST)
	public ModelAndView getSearch_qfd_report(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "type_veh1", required = false) String type_veh,
			//@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "mct_main1", required = false) String mct_main,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus,
			@RequestParam(value = "kms1", required = false) int kms,
			@RequestParam(value = "vintag1", required = false) int vintag,
			@RequestParam(value = "py1", required = false) int py,
			@RequestParam(value = "CheckVal1", required = false) String checkVal,
			@RequestParam(value = "CheckVal3", required = false) String cmd,
			@RequestParam(value = "type_force1", required = false) String type_force
			,@RequestParam(value = "ddlYears1", required = false) String ddlYears1,
			@RequestParam(value = "line_dte_sus_main1", required = false) String line_dte_sus_main,
			@RequestParam(value = "type_of_intervention1", required = false) String type_of_intervention1
			) {
		if(type_veh.equals("")){ 
			model.put("msg","please select Veh Cat");
		}
		/*else if(prf_code.equals("0")){
			model.put("msg","please select Veh Type");
		}*/
		else if(line_dte_sus.equals("0")){
			model.put("msg","please select Line Dte");
		}else{
			ArrayList<ArrayList<String>> list = Q_dao.search_Qfd(type_veh,
					//prf_code,
					mct_main,line_dte_sus,kms,vintag,py,type_force, checkVal, cmd,line_dte_sus_main);
			model.put("list", list);
			model.put("size", list.size());
			model.put("type_veh1", type_veh);
			model.put("CheckVal1", checkVal);
			model.put("mct_main1", mct_main);
			model.put("line_dte_sus1", line_dte_sus);
			model.put("line_dte_sus_main1", line_dte_sus_main);
			
			model.put("kms1", kms);
			model.put("vintag1", vintag);
			model.put("py1", py);
			model.put("type_force1", type_force);
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			model.put("getCommandList",comd);
			model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
			model.put("msg", msg);
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String select="<option value='0'>-- Select --</option>";
			if(roleAccess.equals("Line_dte")) {
				model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				model.put("selectLine_dte", select);
				model.put("getLine_DteList",roledao.getLine_DteList(""));
			}
			model.put("getTypeOfUnitList", all.getTypeOfUnitList());
		}
		
		List<String> selectedGetCommandList = new ArrayList<>();
		if (cmd != "") {
			selectedGetCommandList = Arrays.asList(cmd.split(","));
		}
		model.put("selectedGetCommandList", selectedGetCommandList);
		model.put("getLine_DteList2", roledao.getLine_DteList(""));
		return new ModelAndView("Qfd_Tiles");
	}
				
			
		
		
	

	
	
	
	
	
	 @RequestMapping(value = "/Excel_qfd_report", method = RequestMethod.POST)
		public ModelAndView Excel_qfd_report(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@RequestParam(value = "type_vehex", required = false) String type_veh,
				//@RequestParam(value = "prf_code1", required = false) String prf_code,
				@RequestParam(value = "mct_mainex", required = false) String mct_main,
				@RequestParam(value = "line_dte_susex", required = false) String line_dte_sus,
				@RequestParam(value = "kmsex", required = false) int kms,
				@RequestParam(value = "vintagex", required = false) int vintag,
				@RequestParam(value = "pyex", required = false) int py,
				@RequestParam(value = "type_forceex", required = false) String type_force,
				@RequestParam(value = "msg", required = false) String msg) {

		   String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Qfd_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			 ArrayList<ArrayList<String>> Excel = Q_dao.search_Qfd(type_veh,
						//prf_code,
						mct_main,line_dte_sus,kms,vintag,py,type_force,"","","");
				
		
		 List<String> TH = new ArrayList<String>();
			
		 	
			TH.add("Comd");
			TH.add("Corps");
			TH.add("Division");
			TH.add("Brigade");
			TH.add("Unit Name");
			TH.add("SUS No");
			TH.add("BA No");
			TH.add("Kms Run");
			TH.add("Vintage");
			TH.add("Classification");
			TH.add("Line dte");
			TH.add("OH1 due");
			TH.add("OH2 due");
			TH.add("OH3 due");
			TH.add("OH1 done");
			TH.add("OH2 done");
			TH.add("OH3 done");
			TH.add("MR1 due");
			TH.add("MR2 due");
			TH.add("MR3 due");
			TH.add("MR1 done");
			TH.add("MR2 done");
			TH.add("MR3 done");
			TH.add("Nomenclature");
		
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_QFD("L", TH, username,Excel), "userList", Excel );
		}
	

}
