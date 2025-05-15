package com.controller.mms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_ADMIN_DAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.CUE_GS_POOL;
import com.models.MMS_Domain_Values;
import com.models.MMS_TB_OBSN_DETL;
import com.models.MMS_TB_UNIT_UPLOAD_DOCUMENT;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_ADMINController {

	@Autowired
	private MMS_ADMIN_DAO m7DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	ReportsController rcont = new ReportsController();
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	ValidationController validation = new ValidationController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();

	// MMS ADMIN MODULE (1)-> (UNIT MCR STATUS SCREEN START)
	@RequestMapping(value = "/mms_unit_mcr_status", method = RequestMethod.GET)
	public ModelAndView mms_unit_mcr_status(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
  		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
  		

  		 if(roleAccess.equals("Unit")){
  				model.put("sus_no",roleSusNo);
  				model.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
  			}
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				model.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				model.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				model.put("selectcorps",select);
				model.put("selectDiv",select);
				model.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",formation_code,"","","","","",sessionA);		
				model.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				model.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				model.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				model.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				model.put("selectDiv",select);
				model.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				model.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				model.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				model.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				model.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				model.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				model.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				model.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				model.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				model.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				model.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				model.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				model.put("list_serv", list_serv);*/
			}
		}
		 if(roleAccess.equals("Unit")){
			 //String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				model.put("unit_sus_no",roleSusNo);
				model.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
				roleFormationNo = formation.get(0);
				
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				model.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				model.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				model.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				model.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				model.put("list_serv", list_serv);*/
		 }
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			model.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			model.put("selectcomd", selectComd);
			model.put("selectcorps",select);
			model.put("selectDiv",select);
			model.put("selectBde",select);
		}
		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));
		model.put("ml_2", m1.getDomainListingData("OBSMSTATUS"));
		List<String> j1 = m1.getDData("MMSDEO");
		int a1 = j1.size();
		// model.put("ml_1", j1);
		model.put("a1", a1);
		return new ModelAndView("mms_unit_mcr_statusTiles");
	}
	
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	// MMS ADMIN MODULE (1)-> (UNIT MCR STATUS SCREEN END)

	// MMS ADMIN MODULE (2)-> (UNIT OBSN STATUS SCREEN START)
	@RequestMapping(value = "/mms_unit_obsn_status", method = RequestMethod.GET)
	public ModelAndView mms_unit_obsn_status(ModelMap mMap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		/*ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		mMap.put("r_1", l1);*/
		
		mMap.put("msg", msg);
		/*mMap.put("ml_1", m1.getDomainListingData("MMSDEO"));
		mMap.put("ml_2", m1.getDomainListingData("OBSNSTATUS"));*/
		return new ModelAndView("mms_unit_obsn_statusTiles");
	}
	
	@RequestMapping(value = "/mms_unit_all_obsn_statusList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> mms_unit_all_obsn_statusList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String sus_no,String month_yr,String status,HttpSession sessionUserId){
		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return null;
		}else {
			
			String month = "";
			String yr = "";
			if(!month_yr.equals("")) {
				String[] m = month_yr.split("-");
				month = m[1];
				yr = m[0];
			}
			return m7DAO.mms_unit_all_obsn_statusList(startPage,pageLength,Search,orderColunm,orderType,sus_no,month,yr,status);
		}
	}
	@RequestMapping(value = "/mms_unit_all_obsn_statusCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalCount_SQL(HttpSession sessionUserId,String Search,String sus_no,String month_yr,String status){
		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return 0;
		}else {
			
			String month = "";
			String yr = "";
			if(!month_yr.equals("")) {
				String[] m = month_yr.split("-");
				month = m[1];
				yr = m[0];
			}
			return m7DAO.mms_unit_all_obsn_statusCount(Search,sus_no,month,yr,status);
		}
	}
	// MMS ADMIN MODULE (2)-> (UNIT OBSN STATUS SCREEN END)

	// MMS ADMIN MODULE (3)-> (UNIT ALLOT TO DEO SCREEN START)
	@RequestMapping(value = "/mms_allot_deo", method = RequestMethod.GET)
	public ModelAndView mms_allot_deo(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_allot_deo", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));
		return new ModelAndView("mms_allot_deoTiles");
	}
	// MMS ADMIN MODULE (3)-> (UNIT ALLOT TO DEO SCREEN END)

	// MMS ADMIN MODULE (4)-> (MMS DOMAIN MASTER SCREEN START)
	@RequestMapping(value = "/mms_domain_value", method = RequestMethod.GET)
	public ModelAndView mms_domain_value(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_domain_value", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		model.put("ml_1", m1.getMMSDistDomain(session));
		return new ModelAndView("mms_domain_valTiles");
	}
	// MMS ADMIN MODULE (4)-> (MMS DOMAIN MASTER SCREEN END)

	// (1)-> (UNIT MCR STATUS SCREEN METHODS START)
	@RequestMapping(value = "/admin/UnitMstatList", method = RequestMethod.POST)
	public ModelAndView UnitMstatList(@ModelAttribute("m7_domid") String m7_domid, String m7_statid, String m7_mthyr,
			ModelMap model, HttpSession session, String unit_name1,String unit_sus_no1,String cont_comd1,String cont_corps1,String cont_div1,String cont_bde1) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		//String sus_no12= sus_no1;
		//String unit_name12= unit_name1;
		String cont_comd12 = cont_comd1;
		String cont_corps12 = cont_corps1;
		String cont_div12 = cont_div1;
		String	cont_bde12= cont_bde1;
	
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		  if(roleAccess.equals("Formation")){
              if(roleSubAccess.equals("Command")){
                      String formation_code = String.valueOf(roleFormationNo.charAt(0));
                      cont_comd12 = formation_code;
                      model.put("cont_comd1",cont_comd12);
                      
                      List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);
                      model.put("getCommandList",comd);
                      List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
                      model.put("getCorpsList",corps);
                      
                      String select="<option value='0'>--Select--</option>";
                      model.put("selectcorps",select);
                      model.put("selectDiv",select);
                      model.put("selectBde",select);
                      model.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Corps")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
                      model.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      cont_corps12 = cor;
                      model.put("cont_corps1",cont_corps12);
                      
                      List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
                      model.put("getCorpsList",corps);
                      
                      List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
                      model.put("getDivList",Bn);
                      
                      String select="<option value='0'>--Select--</option>";
                      model.put("selectDiv",select);
                      model.put("selectBde",select);
                      model.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Division")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
                      model.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
                      model.put("getCorpsList",corps);
                      
                      String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                      cont_div12 = div;
                      model.put("cont_div1",cont_div12);
                      
                      List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
                      model.put("getDivList",Bn);
                      
                      List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
                      model.put("getBdeList",bde);
                      
                      String select="<option value='0'>--Select--</option>";
                      model.put("selectBde",select);
                      
                      model.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Brigade")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
                      model.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
                      model.put("getCorpsList",corps);
                      
                      String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                      List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
                      model.put("getDivList",Bn);
                      
                      cont_bde12 = roleFormationNo;
                      model.put("cont_bde1",cont_bde12);
                      
                      String bde_code = roleFormationNo;
                      List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
                      model.put("getBdeList",bde);
                      
                      String select="<option value='0'>--Select--</option>";
                      model.put("selectLine_dte",select);
              }
      }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
              List<Tbl_CodesForm> comd=m.getCommandDetailsList();
              model.put("getCommandList",comd);
              String selectComd ="<option value=''>--Select--</option>";
              String select="<option value='0'>--Select--</option>";
              model.put("selectcomd", selectComd);
              model.put("selectcorps",select);
              model.put("selectDiv",select);
              model.put("selectBde",select);
              model.put("selectLine_dte",select);
              model.put("getLine_DteList",roledao.getLine_DteList(""));
      }else if(roleAccess.equals("Line_dte")){
              List<Tbl_CodesForm> comd=m.getCommandDetailsList();
              model.put("getCommandList",comd);
              String selectComd ="<option value=''>--Select--</option>";
              String select="<option value='0'>--Select--</option>";
              model.put("selectcomd", selectComd);
              model.put("selectcorps",select);
              model.put("selectDiv",select);
              model.put("selectBde",select);
              model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
      } else if(roleAccess.equals("Unit")){
			model.put("sus_no",roleSusNo);
			model.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			 List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			model.put("getCommandList",comd);
			 
		}
      
      
      else {
              return new ModelAndView("AccessTiles");
      }
      
		  	String formCode ="";
		  		if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
		  				formCode = cont_bde12;
		  			}else {
		  					if(!cont_div12.equals("0") && !cont_div12.equals("")){
		  							formCode = cont_div12;
		  						}else {
		  								if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		  									formCode = cont_corps12;
		  									}else {
		  										if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		  											formCode = cont_comd12;
		  											}
		  										}
		  								}
		  					}
       model.put("cont_comd1",cont_comd12);
       model.put("cont_corps1",cont_corps12);
       model.put("cont_div1",cont_div12);
       model.put("cont_bde1",cont_bde12);
              
       	model.put("m_1", m7DAO.UnitMcrStatusList(m7_domid, m7_statid, m7_mthyr, unit_name1, unit_sus_no1, cont_comd1, cont_corps1, cont_div1, cont_bde1));
		model.put("m_2", m7_domid);
		model.put("m_3", m7_statid);
		model.put("m_4", m7_mthyr);
		model.put("m_5", unit_name1);
		model.put("m_6", unit_sus_no1);		
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));
		/*model.put("ml_2", m1.getDomainListingData("OBSMSTATUS"));*/
		return new ModelAndView("mms_unit_mcr_statusTiles");
	}
	// (1)-> (UNIT MCR STATUS SCREEN METHODS END)

	// (2)-> (UNIT OBSN STATUS SCREEN METHODS START)
	@RequestMapping(value = "/admin/UnitObsnstat", method = RequestMethod.POST)
	public ModelAndView UnitObsnstat(@ModelAttribute("m7_domid") String m7_domid, String m7_statid, String m7_mthyr,
			String m7_sus, String m7_unit, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (m7_unit != "") {
			if (validation.checkUnit_nameLength(m7_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_unit_obsn_status");
			}
			// model.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		model.put("m_1", m7DAO.UnitObsnStatusList(m7_domid, m7_statid, m7_mthyr, m7_sus));
		model.put("m_2", m7_domid);
		model.put("m_3", m7_statid);
		model.put("m_4", m7_mthyr);
		model.put("sus_1", m7_sus);
		model.put("unit_1", m7_unit);
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));
		model.put("ml_2", m1.getDomainListingData("OBSNSTATUS"));
		return new ModelAndView("mms_unit_obsn_statusTiles");
	}

	@RequestMapping(value = "/admin/UnitObsnstatlist", method = RequestMethod.POST)
	public ModelAndView UnitObsnstatlist(@ModelAttribute("m7_id") Integer m7_id, String m7_domid2, String m7_statid2,
			String m7_mthyr2, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("m_11", m7DAO.GetObsnData(m7_id));
		model.put("m_2", m7_domid2);
		model.put("m_3", m7_statid2);
		model.put("m_4", m7_mthyr2);
		model.put("m_5", m7_id);
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));
		model.put("ml_2", m1.getDomainListingData("OBSNSTATUS"));
		return new ModelAndView("mms_unit_obsn_statusTiles");
	}

	@RequestMapping(value = "/unit_obsn_action", method = RequestMethod.POST)
	public ModelAndView unit_obsn_action(HttpServletRequest request, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_unit_obsn_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int tr_id = Integer.parseInt(request.getParameter("tr_id"));

		if (validation.checkUnit_nameLength(request.getParameter("unit_name")) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		else if (validation.sus_noLength(request.getParameter("sus_no")) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		else if (validation.check255Length(request.getParameter("nomen")) == false) {
			model.put("msg", validation.nomenMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		else if (validation.checkCenSusnoLength(request.getParameter("census_no")) == false) {
			model.put("msg", validation.census_noMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		else if (validation.checklabelLength(request.getParameter("type_of_hldg")) == false) {
			model.put("msg", validation.typeofhldgMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		else if (validation.checklabelLength(request.getParameter("type_of_eqpt")) == false) {
			model.put("msg", validation.typeofeqptMSG);
			return new ModelAndView("redirect:mms_unit_obsn_status");
		}

		Session session2 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session2.beginTransaction();

		String hqlUpdate = "update MMS_TB_OBSN_DETL c set c.obsn1_res=:obsn1_res,c.obsn1_act=:obsn1_act where c.tr_id = :tr_id";

		int app = session2.createQuery(hqlUpdate).setString("obsn1_res", request.getParameter("res_1"))
				.setString("obsn1_act", request.getParameter("act_1")).setInteger("tr_id", tr_id).executeUpdate();

		tx.commit();
		session2.close();

		model.put("msg", "Unit Observation Data Update Successfully");
		return new ModelAndView("mms_unit_obsn_statusTiles");
	}
	// (2)-> (UNIT OBSN STATUS SCREEN METHODS END)

	// (3)-> (UNIT ALLOT TO DEO SCREEN METHODS START)
	@RequestMapping(value = "/admin/AllotDeo", method = RequestMethod.POST)
	public ModelAndView AllotDeo(@ModelAttribute("m7_deo") String m7_deo, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_allot_deo", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("m_1", m7DAO.getallotedDEOList(m7_deo));
		model.put("m_2", m7_deo);
		model.put("ml_1", m1.getDomainListingData("MMSDEO"));

		return new ModelAndView("mms_allot_deoTiles");
	}

	@RequestMapping(value = "/mms_update_allotDeo", method = RequestMethod.POST)
	public @ResponseBody String mms_update_allotDeo(String b_reqd, String oper, String b_sus_no, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_allot_deo", session.getAttribute("roleid").toString());
		if (val == false) {
			return "Data Not Updated";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			b_sus_no = roleSusNo;
		}
		return m7DAO.mms_update_deoandeqptreqd(b_reqd, oper, b_sus_no);
	}
	// (3)-> (UNIT ALLOT TO DEO SCREEN METHODS END)

	// (4)-> (MMS DOMAIN MASTER SCREEN METHODS START)
	/*
	 * @RequestMapping(value = "/mms_dom_valAction", method = RequestMethod.POST)
	 * public ModelAndView mms_dom_valAction(HttpServletRequest request,ModelMap
	 * model,HttpSession session) {
	 * 
	 * Boolean val = roledao.ScreenRedirect("mms_domain_value",
	 * session.getAttribute("roleid").toString()); if(val == false) { return new
	 * ModelAndView("AccessTiles"); }
	 * 
	 * MMS_Domain_Values rs = new MMS_Domain_Values(); String username =
	 * session.getAttribute("username").toString();
	 * 
	 * 
	 * //String codevalue = request.getParameter("codevalue");
	 * 
	 * if(validation.checkCodeValueLength(codevalue) == false){
	 * model.put("msg",validation.CodeValueMSG); return new
	 * ModelAndView("redirect:mms_domain_value"); }
	 * 
	 * if(validation.checklabelLength(rs.getLabel()) == false){
	 * model.put("msg",validation.labelMSG); return new
	 * ModelAndView("redirect:mms_domain_value"); }
	 * 
	 * 
	 * rs.setDomainid(request.getParameter("domainid"));
	 * rs.setCodevalue(request.getParameter("codevalue"));
	 * rs.setLabel(request.getParameter("label"));
	 * rs.setLabel_s(request.getParameter("label_s"));
	 * rs.setDisp_order(request.getParameter("disp_order"));
	 * 
	 * Session s1=HibernateUtilNA.getSessionFactory().openSession();
	 * s1.beginTransaction(); s1.save(rs); s1.getTransaction().commit(); s1.close();
	 * 
	 * model.put("msg", "Data Added Successfully"); return new
	 * ModelAndView("redirect:mms_domain_value"); }
	 */
	
	@RequestMapping(value = "/mms_dom_valAction", method = RequestMethod.POST)
	public ModelAndView mms_dom_valAction(HttpServletRequest request,ModelMap model,HttpSession session) {
		
		Boolean val = roledao.ScreenRedirect("mms_domain_value", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		MMS_Domain_Values rs = new MMS_Domain_Values();
		String username = session.getAttribute("username").toString();
		
		String codevalue = request.getParameter("codevalue");
		String label = request.getParameter("label");
		
		String domainid = request.getParameter("domainid");
		String label_s = request.getParameter("label_s");
		String disp_order = request.getParameter("disp_order");

		/*if(domainid.equals("") || domainid == "null"){
			 model.put("msg", "Please Enter Domain Name.");
			 return new ModelAndView("redirect:mms_domain_value"); 
		 }*/
		
		  if(validation.checkdomainnameLength(domainid) == false){
		  model.put("msg",validation.domainMSG); 
		  return new   ModelAndView("redirect:mms_domain_value"); 
		  }
		  
	
		  if(validation.checklabelLength(label) == false){
		  model.put("msg",validation.labelMSG); 
		  return new  ModelAndView("redirect:mms_domain_value"); 
		  }
		  
		  
	       if(validation.checkCodeValueLength(codevalue) == false){
		  model.put("msg",validation.CodeValueMSG); 
		  return new  ModelAndView("redirect:mms_domain_value"); 
	       }
			  
	       if(validation.checklabel_sLength(label_s) == false){
	 		  model.put("msg",validation.label_sMSG); 
	 		  return new  ModelAndView("redirect:mms_domain_value"); 
	 	       }
	 			  
	       if(validation.checkdisporderLength(disp_order) == false){
	 		  model.put("msg",validation.disp_orderMSG); 
	 		  return new  ModelAndView("redirect:mms_domain_value"); 
	 	       }
	 			  

	       String c_dt = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
	       
		rs.setDomainid(request.getParameter("domainid")); 
		rs.setCodevalue(request.getParameter("codevalue"));
		rs.setLabel(request.getParameter("label"));
		rs.setLabel_s(request.getParameter("label_s"));
		rs.setDisp_order(request.getParameter("disp_order"));
		rs.setCreatedby(username);
		rs.setCreateddatetime(c_dt);
		rs.setLastupdatedby(username);
		rs.setLastupdateddatetime(c_dt);
		
		Session s1=HibernateUtilNA.getSessionFactory().openSession();
		s1.beginTransaction();
		s1.save(rs);
		s1.getTransaction().commit();
		s1.close();
		
		model.put("msg", "Data Added Successfully");
		return new ModelAndView("redirect:mms_domain_value");
	}

	@RequestMapping(value = "/MMSDomainList", method = RequestMethod.POST)
	public ModelAndView MMSDomainList(@ModelAttribute("m7_domid") String m7_domid, ModelMap model,
			HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_domain_value", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		model.put("ml_1", m1.getMMSDistDomain(session));
		model.put("dom_id", m7_domid);
		model.put("m_1", m1.getDomainValues(m7_domid, session));
		return new ModelAndView("mms_domain_valTiles");
	}

	@RequestMapping(value = "/MMSDomainUpdateList", method = RequestMethod.POST)
	public ModelAndView MMSDomainUpdateList(@ModelAttribute("domainid1") String domainid1, String codevalue1,
			String label1, String label_s1, String disp_order1, Integer id1, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_domain_value", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String c_dt = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
		Session s1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s1.beginTransaction();
		String hqlUpdate = "update MMS_Domain_Values set domainid=:a1,codevalue=:a2,label=:a3,label_s=:a4,disp_order=:a5,lastupdatedby=:lastupdatedby,lastupdateddatetime=:lastupdateddatetime where id=:id";
		int app = s1.createQuery(hqlUpdate).setParameter("a1", domainid1).setParameter("a2", codevalue1)
				.setParameter("a3", label1).setParameter("a4", label_s1).setParameter("a5", disp_order1)
				.setParameter("lastupdatedby", username)
				.setParameter("lastupdateddatetime", c_dt)
				.setInteger("id", id1).executeUpdate();

		tx.commit();
		s1.close();
		if (app > 0) {
			model.put("msg", "Data Updated Successfully");
		} else {
			model.put("msg", "Data Not Updated Successfully");
		}
		return new ModelAndView("redirect:mms_domain_value");
	}
	// (4)-> (MMS DOMAIN MASTER SCREEN METHODS END)
	
	
	// Download Unit Obsn stat Document 
	@RequestMapping(value = "/admin/getDownloadFileOBSNState", method = RequestMethod.POST)
	public ModelAndView getDownloadFileOBSNState(@ModelAttribute("miso_reply_id") int miso_reply_id,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String EXTERNAL_FILE_PATH = "";
		if(miso_reply_id != 0) {
			MMS_TB_UNIT_UPLOAD_DOCUMENT obsn = getMMS_TB_OBSN_DETL_Document(miso_reply_id);
			if(obsn.getDoc() == null) {
				return new ModelAndView("redirect:mms_unit_correctness_certificate?msg=Sorry. The file you are looking for does not exist");
			}else {
				EXTERNAL_FILE_PATH = obsn.getDoc();
			}
		}
		File file = null;
	    file = new File(EXTERNAL_FILE_PATH);
	    try{
	    	if(!file.exists()){
	    		String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_doc.pdf";
	    		file = new File(fullPath);
	    	}
	    }
	    catch(Exception exception){
	    	
	    }
	    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	    if(mimeType==null){
	    	mimeType = "application/octet-stream";
	    }
	    response.setContentType(mimeType);
	    response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	    response.setContentLength((int)file.length());
	    InputStream inputStream = null;
	    try {
	    	inputStream = new BufferedInputStream(new FileInputStream(file));
	    	FileCopyUtils.copy(inputStream, response.getOutputStream());
	    	return new ModelAndView("redirect:mms_unit_correctness_certificate?msg=Download Successfully");
	    } catch (FileNotFoundException e) {
	    	
		}
	    return new ModelAndView("redirect:mms_unit_correctness_certificate?msg=Download Successfully");
	}
	
	@SuppressWarnings("unchecked")
	public MMS_TB_UNIT_UPLOAD_DOCUMENT getMMS_TB_OBSN_DETL_Document(int miso_reply_id){
		String qry = "from MMS_TB_UNIT_UPLOAD_DOCUMENT where id=:id ";

		List<MMS_TB_UNIT_UPLOAD_DOCUMENT> obsn_dtl = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(qry);
			q.setParameter("id", miso_reply_id);
			obsn_dtl = (List<MMS_TB_UNIT_UPLOAD_DOCUMENT>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (obsn_dtl.size() > 0) {
			return obsn_dtl.get(0);
		}else {
			return null;
		}
	}
}
