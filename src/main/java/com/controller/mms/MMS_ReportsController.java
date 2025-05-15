package com.controller.mms;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.cue.cueContoller;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_ReportsDAO;
import com.dao.mms.Mms_Common_DAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MMS_ReportsController {
	
	@Autowired
	private MMS_ReportsDAO m6DAO;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	
	cueContoller cu = new cueContoller();
	
	
	//REPORTS MODULE (1)-> (ALL INDIA HLDG SCREEN START)
	@RequestMapping(value = "/admin/mms_all_india_holding_report", method = RequestMethod.GET)
    public ModelAndView mms_print_aih(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_all_india_holding_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
        return new ModelAndView("mms_all_india_holding_reportTiles");
    }
	//REPORTS MODULE (1)-> (ALL INDIA HLDG SCREEN END)
	
	//REPORTS MODULE (2)-> (EQPT E-ASSETS REGISTER SCREEN START)
	@RequestMapping(value = "/mms_e_assets_register", method = RequestMethod.GET)
	public ModelAndView mms_e_assets_register(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_e_assets_register", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		Mmap.put("r_1", l1);
		if(accssubLvl.equalsIgnoreCase("MISO") || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
			//Mmap.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		}else{
			if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
	        	Mmap.put("m_2", l1.get(0).get(2)); 
				Mmap.put("m_3", l1.get(0).get(3)); 
				
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")){
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")){
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
		return new ModelAndView("mms_e_assets_registerTiles");
	}
	//REPORTS MODULE (2)-> (EQPT E-ASSETS REGISTER SCREEN END)
	
	//REPORTS MODULE (3)-> (WPNS & EQPT STATUS SCREEN START)
	@RequestMapping(value = "/mms_wpn_eqpt_status_view", method = RequestMethod.GET)
	public ModelAndView mms_wpn_eqpt_status_view(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
		Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		Mmap.put("r_1", l1);
		if(accsLvl.equalsIgnoreCase("MISO") || accsLvl.equalsIgnoreCase("HeadQuarter")) {
			Mmap.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
			Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
			Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		}else{
			if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
	        	Mmap.put("m_2", l1.get(0).get(2)); 
				Mmap.put("m_3", l1.get(0).get(3)); 
				
				Mmap.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")){
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")){
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				Mmap.put("ml_6", m1.getMMSArmCodeList("",l1.get(0).get(5),session));
			}
		}
		Mmap.put("ml_nodal", m1.getDomainListingData("SPONSERDTE"));
		Mmap.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
		Mmap.put("msg", msg);
		return new ModelAndView("mms_wpn_eqpt_status_viewTiles");
	}
	
	@RequestMapping(value = "/mms_adhoc_query_view", method = RequestMethod.GET)
	public ModelAndView mms_adhoc_query_view(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
		
	
		Mmap.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
		Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
		Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
		Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		return new ModelAndView("mms_adhoc_query_viewTiles");
	}
	//REPORTS MODULE (3)-> (WPNS & EQPT STATUS SCREEN END)

	//(1)-> (ALL INDIA HLDG SCREEN METHODS START)
	@RequestMapping(value = "/admin/AllIndiaHldglist", method = RequestMethod.POST)
	public ModelAndView AllIndiaHldglist(@ModelAttribute("m6_prf") String m6_prf,String m6_hldg,String m6_mthyr,String m6_prfse,
			ModelMap model,HttpSession session){
	   
		Boolean val = roledao.ScreenRedirect("mms_all_india_holding_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("m_1", m6DAO.mmsaih(m6_prf,m6_hldg,m6_mthyr));
		model.put("m_2", m6_prf);
		model.put("m_3", m6_hldg);
		model.put("m_4", m6_mthyr);
		model.put("m_5", m6_prfse);
		model.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
	
		return new ModelAndView("mms_all_india_holding_reportTiles");	
	}
	
	@RequestMapping(value = "/PrintAllIndiaHldglist", method = RequestMethod.POST)
	public ModelAndView PrintAllIndiaHldglist(@ModelAttribute("p1_prf") String p1_prf,String p1_hldg,String p1_mthyr,
			ModelMap model,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_all_india_holding_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("m_dt",mmsCommonDAO.getMMSMaxDt(""));
		model.put("m_1", m6DAO.mmsaih(p1_prf,p1_hldg,p1_mthyr));
		return new ModelAndView("mms_print_all_india_hldgTiles");
	}
	//(1)-> (ALL INDIA HLDG SCREEN METHODS END)
	
	//(2)-> (EQPT E-ASSETS REGISTER SCREEN METHODS START)
	@RequestMapping(value = "/admin/EAssetReglist", method = RequestMethod.POST)
	public ModelAndView EAssetReglist(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,String m4_b_code,
			String m4_p_code,String m4_d_from,String m4_d_to,String m4_hldg,String m4_prfs,String m4_susno,String m4_unitname,
			ModelMap model,HttpSession session){
		
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_e_assets_register", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String formcode="";
		String formcodeType="";
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		if(!roleType.equalsIgnoreCase("ALL")) {
			if (m4_c_code.equalsIgnoreCase(null)) {
				m4_c_code="ALL";
			}
			if (m4_q_code.equalsIgnoreCase(null)) {
				m4_q_code="ALL";
			}
			if (m4_d_code.equalsIgnoreCase(null)) {
				m4_d_code="ALL";
			}
			if (m4_b_code.equalsIgnoreCase(null)) {
				m4_b_code="ALL";
			}					
			if(accssubLvl.equalsIgnoreCase("UNIT")) {
				if (m4_susno.equalsIgnoreCase(null) || m4_susno.equalsIgnoreCase("ALL")) {
					m4_susno=l1.get(0).get(2);
				}
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (m4_b_code.equalsIgnoreCase(null) || m4_b_code.equalsIgnoreCase("ALL")) {
					m4_b_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (m4_d_code.equalsIgnoreCase(null) || m4_d_code.equalsIgnoreCase("ALL")) {
					m4_d_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")) {
				if (m4_q_code.equalsIgnoreCase(null) || m4_q_code.equalsIgnoreCase("ALL")) {
					m4_q_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (m4_c_code.equalsIgnoreCase(null) || m4_c_code.equalsIgnoreCase("ALL")) {
					m4_c_code=l1.get(0).get(4);
				}
			}
		}
		
		if(!m4_c_code.equalsIgnoreCase("ALL")){
			formcode=m4_c_code;
			formcodeType="COMMAND";				
		}
		if(!m4_q_code.equalsIgnoreCase("ALL")){
			formcode=m4_q_code;
			formcodeType="CORPS";
		}
		if(!m4_d_code.equalsIgnoreCase("ALL")){
			formcode=m4_d_code;
			formcodeType="DIV";
		}	
		if(!m4_b_code.equalsIgnoreCase("ALL")){
			formcode=m4_b_code;
			formcodeType="BDE";
		}
		if(!m4_susno.equalsIgnoreCase("")){
			formcode=m4_susno;
			formcodeType="UNIT";
		}
		
		model.put("r_1", l1);
		model.put("m_1", m6DAO.mms_list_e_assets_reg(m4_hldg,formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);
		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_hldg);
		model.put("m_10", m4_prfs);
		model.put("a_sus",m4_susno);
		model.put("a_unit",m4_unitname);
		model.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
		accssubLvl = l1.get(0).get(1);
		
		String formcode2="";
        String formcodeType2 ="";
        
		if(accssubLvl.equalsIgnoreCase("MISO") || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			
			if(m4_c_code.equalsIgnoreCase("ALL")) {	
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			}else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code, "COMMAND"));
			}
			
			if(m4_q_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;				
				}
				
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code, "CORPS"));
			}
			
			if(m4_d_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;	
					formcodeType2="COMMAND";
				}
				
				if(!m4_q_code.equalsIgnoreCase("ALL")){
					formcode2=m4_q_code;	
					formcodeType2="CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code, "CORPS"));
			}
		}
		else{
			if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
				model.put("m_2", l1.get(0).get(2)); 
				model.put("m_3", l1.get(0).get(3)); 
				//model.put("m_1", m4DAO.mms_holdings_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")){
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")){
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			
		}

		model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
		return new ModelAndView("mms_e_assets_registerTiles");		
	}
	
	@RequestMapping(value = "/PrinEAssetReglist",method = RequestMethod.POST)
	public ModelAndView PrinEAssetReglist(@ModelAttribute("p_c_code") String p_c_code,String p_q_code,String p_d_code,String p_b_code,
			String p_p_code,String p_hldg,String p_d_from,String p_d_to,String p_susno, String p_unitname,String printMCRId,ModelMap model,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_e_assets_register", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String formcode="";
		String formcodeType="";
		if (!p_c_code.equalsIgnoreCase("ALL")) {
			formcode=p_c_code;
			formcodeType="COMMAND";
		}
		if (!p_q_code.equalsIgnoreCase("ALL")) {
			formcode=p_q_code;
			formcodeType="CORPS";
		}
		if (!p_d_code.equalsIgnoreCase("ALL")) {
			formcode=p_d_code;
			formcodeType="DIV";
		}
		if (!p_b_code.equalsIgnoreCase("ALL")) {
			formcode=p_b_code;
			formcodeType="BDE";
		}
		model.put("sus_no",p_susno);
		model.put("unit_name",p_unitname);
		model.put("m_dt",mmsCommonDAO.getMMSMaxDt(printMCRId));
		model.put("m_1", m6DAO.mms_list_e_assets_reg(p_hldg,formcodeType,formcode,p_p_code,p_d_from,p_d_to));
		
		return new ModelAndView("mms_print_e_assetsTiles");
	}
	//(2)-> (EQPT E-ASSETS REGISTER SCREEN METHODS END)
	
	//(3)-> (WPNS & EQPT STATUS SCREEN METHODS START)
	@RequestMapping(value = "/printWpnEqptList",method = RequestMethod.POST)
	public ModelAndView printWpnEqptUrl(@ModelAttribute("p_c_code") String printMCRId,String p_c_code,String p_q_code,String p_d_code,String p_b_code,String p_p_code,
			String p_d_from,String p_d_to,String p_hldg,String p_susno,String p_unitname,String p_arm,ModelMap model,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		String formcode="";
		String formcodeType="";
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		if(!roleType.equalsIgnoreCase("ALL")) {
			if (p_c_code.equalsIgnoreCase(null)) {
				p_c_code="ALL";
			}
			if (p_q_code.equalsIgnoreCase(null)) {
				p_q_code="ALL";
			}
			if (p_d_code.equalsIgnoreCase(null)) {
				p_d_code="ALL";
			}
			if (p_b_code.equalsIgnoreCase(null)) {
				p_b_code="ALL";
			}					
			if(accssubLvl.equalsIgnoreCase("UNIT")) {
				if (p_susno.equalsIgnoreCase(null) || p_susno.equalsIgnoreCase("ALL")) {
					p_susno=l1.get(0).get(2);
				}
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (p_b_code.equalsIgnoreCase(null) || p_b_code.equalsIgnoreCase("ALL")) {
					p_b_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (p_d_code.equalsIgnoreCase(null) || p_d_code.equalsIgnoreCase("ALL")) {
					p_d_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")) {
				if (p_q_code.equalsIgnoreCase(null) || p_q_code.equalsIgnoreCase("ALL")) {
					p_q_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (p_c_code.equalsIgnoreCase(null) || p_c_code.equalsIgnoreCase("ALL")) {
					p_c_code=l1.get(0).get(4);
				}
			}
		}
		
		if(!p_arm.equalsIgnoreCase("ALL")){
			formcode=p_arm;
			formcodeType="LINE";				
		}
		if(!p_c_code.equalsIgnoreCase("ALL")){
			formcode=p_c_code;
			formcodeType="COMMAND";				
		}
		if(!p_q_code.equalsIgnoreCase("ALL")){
			formcode=p_q_code;
			formcodeType="CORPS";
		}
		if(!p_d_code.equalsIgnoreCase("ALL")){
			formcode=p_d_code;
			formcodeType="DIV";
		}	
		if(!p_b_code.equalsIgnoreCase("ALL")){
			formcode=p_b_code;
			formcodeType="BDE";
		}
		
		if(!p_susno.equalsIgnoreCase("")){
			formcode=p_susno;
			formcodeType="UNIT";
		}
		model.put("m_dt",mmsCommonDAO.getMMSMaxDt(""));
		model.put("m_1", m6DAO.mms_ue_uh_list(p_hldg,formcodeType,formcode,p_p_code,p_d_from,p_d_to));
		return new ModelAndView("mms_print_wpn_eqpt_status_viewTiles");
	}
	
	@RequestMapping(value = "/admin/WpnEqptdatalist",method = RequestMethod.POST)
	public ModelAndView WpnEqptdatalist(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,String m4_b_code,
			String m4_p_code,String m4_d_from,String m4_d_to,String m4_hldg,String m4_prfs,
			String m4_susno,String m4_unitname,String m4_arm,ModelMap model,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
	
		
		String formcode="";
		String formcodeType="";
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		if(!roleType.equalsIgnoreCase("ALL")) {
			if (m4_c_code.equalsIgnoreCase(null)) {
				m4_c_code="ALL";
			}
			if (m4_q_code.equalsIgnoreCase(null)) {
				m4_q_code="ALL";
			}
			if (m4_d_code.equalsIgnoreCase(null)) {
				m4_d_code="ALL";
			}
			if (m4_b_code.equalsIgnoreCase(null)) {
				m4_b_code="ALL";
			}					
			if(accssubLvl.equalsIgnoreCase("UNIT")) {
				if (m4_susno.equalsIgnoreCase(null) || m4_susno.equalsIgnoreCase("ALL")) {
					m4_susno=l1.get(0).get(2);
				}
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (m4_b_code.equalsIgnoreCase(null) || m4_b_code.equalsIgnoreCase("ALL")) {
					m4_b_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (m4_d_code.equalsIgnoreCase(null) || m4_d_code.equalsIgnoreCase("ALL")) {
					m4_d_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")) {
				if (m4_q_code.equalsIgnoreCase(null) || m4_q_code.equalsIgnoreCase("ALL")) {
					m4_q_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (m4_c_code.equalsIgnoreCase(null) || m4_c_code.equalsIgnoreCase("ALL")) {
					m4_c_code=l1.get(0).get(4);
				}
			}
		}
		
		if(!m4_arm.equalsIgnoreCase("ALL")){
			formcode=m4_arm;
			formcodeType="LINE";				
		}
		if(!m4_c_code.equalsIgnoreCase("ALL")){
			formcode=m4_c_code;
			formcodeType="COMMAND";				
		}
		if(!m4_q_code.equalsIgnoreCase("ALL")){
			formcode=m4_q_code;
			formcodeType="CORPS";
		}
		if(!m4_d_code.equalsIgnoreCase("ALL")){
			formcode=m4_d_code;
			formcodeType="DIV";
		}	
		if(!m4_b_code.equalsIgnoreCase("ALL")){
			formcode=m4_b_code;
			formcodeType="BDE";
		}
		
		if(!m4_susno.equalsIgnoreCase("")){
			formcode=m4_susno;
			formcodeType="UNIT";
		}
		
		if(accssubLvl.equalsIgnoreCase("UNIT")) {	
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			formcode = roleSusNo;
			formcodeType="UNIT";
		}
		
		model.put("r_1", l1);
		model.put("m_1", m6DAO.mms_ue_uh_list(m4_hldg,formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);
		
		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_hldg);
		model.put("m_10", m4_prfs);
		model.put("a_sus",m4_susno);
		model.put("a_unit",m4_unitname);
		model.put("a_arm",m4_arm);
		
		model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
        
        accssubLvl = l1.get(0).get(1);
		
        String formcode2="";
        String formcodeType2 ="";
        
        /*if(roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {*/
        if(accsLvl.equalsIgnoreCase("MISO") || accsLvl.equalsIgnoreCase("HeadQuarter")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			
			if(m4_c_code.equalsIgnoreCase("ALL")) {	
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			}else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code, "COMMAND"));
			}
			
			if(m4_q_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;				
				}
				
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code, "CORPS"));
			}
			
			if(m4_d_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;	
					formcodeType2="COMMAND";
				}
				
				if(!m4_q_code.equalsIgnoreCase("ALL")){
					formcode2=m4_q_code;	
					formcodeType2="CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code, "CORPS"));
			}	
		}else {
			if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
				model.put("m_2", l1.get(0).get(2)); 
				model.put("m_3", l1.get(0).get(3)); 
				//model.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")){
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")){
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("",l1.get(0).get(5),session));
			}
		}
		
		
	    return new ModelAndView("mms_wpn_eqpt_status_viewTiles");		
	}

	
	@RequestMapping(value = "/admin/WpnEqptdataSumm",method = RequestMethod.POST)
	public ModelAndView WpnEqptdataSumm(@ModelAttribute("sm4_c_code") String sm4_c_code,
			String sm4_q_code,
			String sm4_d_code,
			String sm4_b_code,
			String sm4_p_code,
			String sm4_d_from,
			String sm4_d_to,
			String sm4_hldg,
			String sm4_prfs,
			String sm4_susno,
			String sm4_unitname,
			String sm4_arm,ModelMap model,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		
		
		String formcode="";
		String formcodeType="";
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		if(!accsLvl.equalsIgnoreCase("MISO")) {
			if (sm4_c_code.equalsIgnoreCase(null)) {
				sm4_c_code="ALL";
			}
			if (sm4_q_code.equalsIgnoreCase(null)) {
				sm4_q_code="ALL";
			}
			if (sm4_d_code.equalsIgnoreCase(null)) {
				sm4_d_code="ALL";
			}
			if (sm4_b_code.equalsIgnoreCase(null)) {
				sm4_b_code="ALL";
			}					
			if(accssubLvl.equalsIgnoreCase("UNIT")) {
				if (sm4_susno.equalsIgnoreCase(null) || sm4_susno.equalsIgnoreCase("ALL")) {
					sm4_susno=l1.get(0).get(2);
				}
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				if (sm4_b_code.equalsIgnoreCase(null) || sm4_b_code.equalsIgnoreCase("ALL")) {
					sm4_b_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				if (sm4_d_code.equalsIgnoreCase(null) || sm4_d_code.equalsIgnoreCase("ALL")) {
					sm4_d_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")) {
				if (sm4_q_code.equalsIgnoreCase(null) || sm4_q_code.equalsIgnoreCase("ALL")) {
					sm4_q_code=l1.get(0).get(4);
				}
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")) {
				if (sm4_c_code.equalsIgnoreCase(null) || sm4_c_code.equalsIgnoreCase("ALL")) {
					sm4_c_code=l1.get(0).get(4);
				}
			}
		}
		
		if(!sm4_arm.equalsIgnoreCase("ALL")){
			formcode=sm4_arm;
			formcodeType="LINE";				
		}
		if(!sm4_c_code.equalsIgnoreCase("ALL")){
			formcode=sm4_c_code;
			formcodeType="COMMAND";				
		}
		if(!sm4_q_code.equalsIgnoreCase("ALL")){
			formcode=sm4_q_code;
			formcodeType="CORPS";
		}
		if(!sm4_d_code.equalsIgnoreCase("ALL")){
			formcode=sm4_d_code;
			formcodeType="DIV";
		}	
		if(!sm4_b_code.equalsIgnoreCase("ALL")){
			formcode=sm4_b_code;
			formcodeType="BDE";
		}
		
		if(!sm4_susno.equalsIgnoreCase("")){
			formcode=sm4_susno;
			formcodeType="UNIT";
		}
		
		model.put("r_1", l1);
		model.put("b_1", m6DAO.mms_ue_uh_summ(sm4_hldg,formcodeType,formcode,sm4_p_code,sm4_d_from,sm4_d_to));
		model.put("m_2", sm4_c_code);
		model.put("m_3", sm4_q_code);
		model.put("m_4", sm4_d_code);
		model.put("m_5", sm4_b_code);
		
		model.put("m_6", sm4_p_code);
		model.put("m_7", sm4_d_from);
		model.put("m_8", sm4_d_to);
		model.put("m_9", sm4_hldg);
		model.put("m_10", sm4_prfs);
		model.put("a_sus",sm4_susno);
		model.put("a_unit",sm4_unitname);
		model.put("a_arm",sm4_arm);
		
		model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
        
        accssubLvl = l1.get(0).get(1);
		
        String formcode2="";
        String formcodeType2 ="";
        
        /*if(roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {*/
        if(accssubLvl.equalsIgnoreCase("MISO") || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			
			if(sm4_c_code.equalsIgnoreCase("ALL")) {	
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			}else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", sm4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_c_code, "COMMAND"));
			}
			
			if(sm4_q_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!sm4_c_code.equalsIgnoreCase("ALL")){
					formcode2=sm4_c_code;				
				}
				
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_q_code, "CORPS"));
			}
			
			if(sm4_d_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!sm4_c_code.equalsIgnoreCase("ALL")){
					formcode2=sm4_c_code;	
					formcodeType2="COMMAND";
				}
				
				if(!sm4_q_code.equalsIgnoreCase("ALL")){
					formcode2=sm4_q_code;	
					formcodeType2="CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_d_code, "CORPS"));
			}	
		}else {
			if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
				model.put("m_2", l1.get(0).get(2)); 
				model.put("m_3", l1.get(0).get(3)); 
				model.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
			}
			if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
			}
			if(accssubLvl.equalsIgnoreCase("DIVISION")) {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
			}
			if(accssubLvl.equalsIgnoreCase("CORPS")){
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
			}
			if(accssubLvl.equalsIgnoreCase("COMMAND")){
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
			}
			if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(5), "LINE"));
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(5), "LINE"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(5), "LINE"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(5), "LINE"));
				model.put("ml_6", m1.getMMSArmCodeList("",l1.get(0).get(5),session));
			}
		}		
	    return new ModelAndView("mms_wpn_eqpt_status_viewTiles");		
	}
	
	@RequestMapping(value = "/admin/AdhocReport",method = RequestMethod.POST)
	public ModelAndView AdhocReport(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,String m4_b_code,
			String m4_p_code,String m4_d_from,String m4_d_to,String m4_hldg,String m4_prfs,ModelMap model,HttpSession session){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		String formcode="";
		String formcodeType="";
		if (!m4_c_code.equalsIgnoreCase("ALL")) {
			formcode=m4_c_code;
			formcodeType="COMMAND";
		}
		if (!m4_q_code.equalsIgnoreCase("ALL")) {
			formcode=m4_q_code;
			formcodeType="CORPS";
		}
		if (!m4_d_code.equalsIgnoreCase("ALL")) {
			formcode=m4_d_code;
			formcodeType="DIV";
		}
		if (!m4_b_code.equalsIgnoreCase("ALL")) {
			formcode=m4_b_code;
			formcodeType="BDE";
		}	
		
		model.put("m_1", m6DAO.mms_ue_uh_list(m4_hldg,formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);
		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_hldg);
		model.put("m_10", m4_prfs);
		
		model.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
		
		accssubLvl = l1.get(0).get(1);
		
        String formcode2="";
        String formcodeType2 ="";
		
		if(roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {
			model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
			model.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			
			if(m4_c_code.equalsIgnoreCase("ALL")) {	
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			}else {
				model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code, "COMMAND"));
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code, "COMMAND"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code, "COMMAND"));
			}
			
			if(m4_q_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;				
				}
				
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code, "CORPS"));
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code, "CORPS"));
			}
			
			if(m4_d_code.equalsIgnoreCase("ALL")) {
				formcode2="ALL";
				formcodeType2="COMMAND";
				
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode2=m4_c_code;	
					formcodeType2="COMMAND";
				}
				
				if(!m4_q_code.equalsIgnoreCase("ALL")){
					formcode2=m4_q_code;	
					formcodeType2="CORPS";
				}
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
			}else {
				model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code, "CORPS"));
			}
			
		}

	    return new ModelAndView("mms_adhoc_query_viewTiles");		
	}
	//(3)-> (WPNS & EQPT STATUS SCREEN METHODS END)
	
	//EXTRA PRINT START 
	@RequestMapping(value = "/admin/mms_print_ereg", method = RequestMethod.GET)
    public ModelAndView mms_print_ereg(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
        return new ModelAndView("mms_print_eregTiles");
    }
	
	@RequestMapping(value = "/mmsereg")
	public @ResponseBody List<String> mmsereg(String nParaValue) {
		List<String> list =m6DAO.mmsereg(nParaValue);
		return list;		
	}
	//EXTRA PRINT END 
	
	
	//RAJ 	//28.06.2024
			@RequestMapping(value = "/admin/WpnEqptUEdatalist",method = RequestMethod.POST)
			public ModelAndView WpnEqptUEdatalist(@ModelAttribute("m4_c_code") String m4_c_code,					
					String m4_q_code,String m4_d_code,String m4_b_code,
					String m4_p_code,String m4_susno,String m4_unitname,
					@ModelAttribute("m4_arm") String m4_arm,String m4_item_type,String m4_prf_code,
					ModelMap model,HttpSession session){
				
				/*Boolean val = roledao.ScreenRedirect("cue_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}*/
				
				String roleType = (String) session.getAttribute("roleType");
				String accsLvl = (String) session.getAttribute("roleAccess");
				String accssubLvl=(String) session.getAttribute("roleAccess");
			
				
				String formcode="";
				String formcodeType="";
				
				ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
				accssubLvl = l1.get(0).get(1);
				accsLvl = l1.get(0).get(7);
				roleType = l1.get(0).get(8);
				
				if(!roleType.equalsIgnoreCase("ALL")) {
					if (m4_c_code.equalsIgnoreCase(null)) {
						m4_c_code="ALL";
					}
					if (m4_q_code.equalsIgnoreCase(null)) {
						m4_q_code="ALL";
					}
					if (m4_d_code.equalsIgnoreCase(null)) {
						m4_d_code="ALL";
					}
					if (m4_b_code.equalsIgnoreCase(null)) {
						m4_b_code="ALL";
					}					
					if(accssubLvl.equalsIgnoreCase("UNIT")) {
						if (m4_susno.equalsIgnoreCase(null) || m4_susno.equalsIgnoreCase("ALL")) {
							m4_susno=l1.get(0).get(2);
						}
					}
					if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
						if (m4_b_code.equalsIgnoreCase(null) || m4_b_code.equalsIgnoreCase("ALL")) {
							m4_b_code=l1.get(0).get(4);
						}
					}
					if(accssubLvl.equalsIgnoreCase("DIVISION")) {
						if (m4_d_code.equalsIgnoreCase(null) || m4_d_code.equalsIgnoreCase("ALL")) {
							m4_d_code=l1.get(0).get(4);
						}
					}
					if(accssubLvl.equalsIgnoreCase("CORPS")) {
						if (m4_q_code.equalsIgnoreCase(null) || m4_q_code.equalsIgnoreCase("ALL")) {
							m4_q_code=l1.get(0).get(4);
						}
					}
					if(accssubLvl.equalsIgnoreCase("COMMAND")) {
						if (m4_c_code.equalsIgnoreCase(null) || m4_c_code.equalsIgnoreCase("ALL")) {
							m4_c_code=l1.get(0).get(4);
						}
					}
				}
				
				if(!m4_arm.equalsIgnoreCase("ALL")){
					formcode=m4_arm;
					formcodeType="LINE";				
				}
				if(!m4_c_code.equalsIgnoreCase("ALL")){
					formcode=m4_c_code;
					formcodeType="COMMAND";				
				}
				if(!m4_q_code.equalsIgnoreCase("ALL")){
					formcode=m4_q_code;
					formcodeType="CORPS";
				}
				if(!m4_d_code.equalsIgnoreCase("ALL")){
					formcode=m4_d_code;
					formcodeType="DIV";
				}	
				if(!m4_b_code.equalsIgnoreCase("ALL")){
					formcode=m4_b_code;
					formcodeType="BDE";
				}
				
				if(!m4_susno.equalsIgnoreCase("")){
					formcode=m4_susno;
					formcodeType="UNIT";
				}
				
				if(accssubLvl.equalsIgnoreCase("UNIT")) {	
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					formcode = roleSusNo;
					formcodeType="UNIT";
				}
				
				model.put("r_1", l1);
				model.put("m_1", m6DAO.cue_ue_list(formcodeType,formcode,m4_item_type, m4_p_code,m4_arm));
				  String roleAccess = session.getAttribute("roleAccess").toString();
					
					String roleSusNo = session.getAttribute("roleSusNo").toString();
		         if(roleAccess.equals("Line_dte")){	
					
			model.put("getArmNameList",cu.getArmNameLine(roleSusNo));
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
				}else {
					String select="<option value='ALL'>--All Arms--</option>";
					model.put("selectLine_dte",select);
					model.put("selectArmNameList",select);
					model.put("getArmNameList", cu.getArmNameList());
					model.put("getLine_DteList",roledao.getLine_DteList(""));
				}
				
				
				
				model.put("m_2", m4_c_code);
				model.put("m_3", m4_q_code);
				model.put("m_4", m4_d_code);
				model.put("m_5", m4_b_code);		
				model.put("m_6", m4_p_code);
				/*model.put("m_7", m4_d_from);
				model.put("m_8", m4_d_to);
				model.put("m_9", m4_hldg);
				model.put("m_10", m4_prfs);*/
				model.put("a_sus",m4_susno);
				model.put("a_unit",m4_unitname);
				model.put("a_arm",m4_arm);
				model.put("a_item_type",m4_item_type);
				model.put("m_10", m4_prf_code);
		/*		model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));*/
		        
		        accssubLvl = l1.get(0).get(1);
				
		        String formcode2="";
		        String formcodeType2 ="";
		        
		        /*if(roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {*/
		        if(accsLvl.equalsIgnoreCase("MISO") || accsLvl.equalsIgnoreCase("HeadQuarter")) {
					model.put("ml_6", m1.getMMSTwoArmCodeList("ALL","ALL",session));
					model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
					
					if(m4_c_code.equalsIgnoreCase("ALL")) {	
						model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
					}else {
						model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", m4_c_code, "COMMAND"));
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_c_code, "COMMAND"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_c_code, "COMMAND"));
					}
					
					if(m4_q_code.equalsIgnoreCase("ALL")) {
						formcode2="ALL";
						formcodeType2="COMMAND";
						
						if(!m4_c_code.equalsIgnoreCase("ALL")){
							formcode2=m4_c_code;				
						}
						
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
					}else {
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", m4_q_code, "CORPS"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_q_code, "CORPS"));
					}
					
					if(m4_d_code.equalsIgnoreCase("ALL")) {
						formcode2="ALL";
						formcodeType2="COMMAND";
						
						if(!m4_c_code.equalsIgnoreCase("ALL")){
							formcode2=m4_c_code;	
							formcodeType2="COMMAND";
						}
						
						if(!m4_q_code.equalsIgnoreCase("ALL")){
							formcode2=m4_q_code;	
							formcodeType2="CORPS";
						}
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
					}else {
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", m4_d_code, "CORPS"));
					}	
				}else {
					if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
						model.put("m_2", l1.get(0).get(2)); 
						model.put("m_3", l1.get(0).get(3)); 
						model.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
					}
					if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
					}
					if(accssubLvl.equalsIgnoreCase("DIVISION")) {
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
					}
					if(accssubLvl.equalsIgnoreCase("CORPS")){
						model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
					}
					if(accssubLvl.equalsIgnoreCase("COMMAND")){
						model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
						model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
					}
					if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
						model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
						model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
						model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
						model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
						//model.put("ml_6", m1.getMMSTwoArmCodeList("",l1.get(0).get(5),session));
					}
				}
				
				
			    return new ModelAndView("cue_wpn_eqpt_status_viewTiles");		
			}
		
		@RequestMapping(value = "/cue_wpn_eqpt_status_view", method = RequestMethod.GET)
		public ModelAndView cue_wpn_eqpt_status_view(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			/*Boolean val = roledao.ScreenRedirect("cue_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			
			Mmap.put("msg", msg);
	/*		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));*/
			Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			Mmap.put("r_1", l1);
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			
	if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
				
				//Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
			
				Mmap.put("getArmNameList",cu.getArmNameLine(roleSusNo));
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				//Mmap.put("getArmNameList", getArmNameList());
				String select="<option value='ALL'>--All Arms--</option>";
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectArmNameList",select);
				Mmap.put("getArmNameList", cu.getArmNameList());
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
			}
			if(accsLvl.equalsIgnoreCase("MISO") || accsLvl.equalsIgnoreCase("HeadQuarter")) {
				Mmap.put("ml_6", m1.getMMSTwoArmCodeList("ALL","ALL",session));
				Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
				Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
				Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
				Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
			}else{
				if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
		        	Mmap.put("m_2", l1.get(0).get(2)); 
					Mmap.put("m_3", l1.get(0).get(3)); 
					
					Mmap.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
				}
				if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
					Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
				}
				if(accssubLvl.equalsIgnoreCase("DIVISION")) {
					Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
					Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
				}
				if(accssubLvl.equalsIgnoreCase("CORPS")){
					Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
					Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
					Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
				}
				if(accssubLvl.equalsIgnoreCase("COMMAND")){
					Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
					Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
					Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
					Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
				}
				if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
					//Mmap.put("ml_6", m1.getMMSTwoArmCodeList("ALL","ALL",session));
					Mmap.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
					Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
					Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
					Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
				}
			}
	/*		Mmap.put("ml_nodal", m1.getDomainListingData("SPONSERDTE"));
			Mmap.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));*/
			Mmap.put("msg", msg);
			return new ModelAndView("cue_wpn_eqpt_status_viewTiles");
		}
		

		

		@RequestMapping(value = "/admin/WpnEqptdataSummCue",method = RequestMethod.POST)
		public ModelAndView WpnEqptdataSummCue(@ModelAttribute("sm4_c_code") String sm4_c_code,
				String sm4_q_code,
				String sm4_d_code,
				String sm4_b_code,
				String sm4_p_code,
				String sm4_prfs,
				String sm4_susno,
				String sm4_unitname,
				String sm4_arm,String sm4_item_type,ModelMap model,HttpSession session){
			
			/*Boolean val = roledao.ScreenRedirect("cue_wpn_eqpt_status_view", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			
			
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			
			
			String formcode="";
			String formcodeType="";
			
			
			
			ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			
			
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			
			if(roleAccess.equals("Line_dte")){	
				
				model.put("getArmNameList",cu.getArmNameLine(roleSusNo));
				model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
					}else {
						String select="<option value='ALL'>--All Arms--</option>";
						model.put("selectLine_dte",select);
						model.put("selectArmNameList",select);
						model.put("getArmNameList", cu.getArmNameList());
						model.put("getLine_DteList",roledao.getLine_DteList(""));
					}
			
			
			if(!accsLvl.equalsIgnoreCase("MISO")) {
				if (sm4_c_code.equalsIgnoreCase(null)) {
					sm4_c_code="ALL";
				}
				if (sm4_q_code.equalsIgnoreCase(null)) {
					sm4_q_code="ALL";
				}
				if (sm4_d_code.equalsIgnoreCase(null)) {
					sm4_d_code="ALL";
				}
				if (sm4_b_code.equalsIgnoreCase(null)) {
					sm4_b_code="ALL";
				}					
				if(accssubLvl.equalsIgnoreCase("UNIT")) {
					if (sm4_susno.equalsIgnoreCase(null) || sm4_susno.equalsIgnoreCase("ALL")) {
						sm4_susno=l1.get(0).get(2);
					}
				}
				if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
					if (sm4_b_code.equalsIgnoreCase(null) || sm4_b_code.equalsIgnoreCase("ALL")) {
						sm4_b_code=l1.get(0).get(4);
					}
				}
				if(accssubLvl.equalsIgnoreCase("DIVISION")) {
					if (sm4_d_code.equalsIgnoreCase(null) || sm4_d_code.equalsIgnoreCase("ALL")) {
						sm4_d_code=l1.get(0).get(4);
					}
				}
				if(accssubLvl.equalsIgnoreCase("CORPS")) {
					if (sm4_q_code.equalsIgnoreCase(null) || sm4_q_code.equalsIgnoreCase("ALL")) {
						sm4_q_code=l1.get(0).get(4);
					}
				}
				if(accssubLvl.equalsIgnoreCase("COMMAND")) {
					if (sm4_c_code.equalsIgnoreCase(null) || sm4_c_code.equalsIgnoreCase("ALL")) {
						sm4_c_code=l1.get(0).get(4);
					}
				}
			}
			
			if(!sm4_arm.equalsIgnoreCase("ALL")){
				formcode=sm4_arm;
				formcodeType="LINE";				
			}
			if(!sm4_c_code.equalsIgnoreCase("ALL")){
				formcode=sm4_c_code;
				formcodeType="COMMAND";				
			}
			if(!sm4_q_code.equalsIgnoreCase("ALL")){
				formcode=sm4_q_code;
				formcodeType="CORPS";
			}
			if(!sm4_d_code.equalsIgnoreCase("ALL")){
				formcode=sm4_d_code;
				formcodeType="DIV";
			}	
			if(!sm4_b_code.equalsIgnoreCase("ALL")){
				formcode=sm4_b_code;
				formcodeType="BDE";
			}
			
			if(!sm4_susno.equalsIgnoreCase("")){
				formcode=sm4_susno;
				formcodeType="UNIT";
			}
			
			model.put("r_1", l1);
			//model.put("b_1", m6DAO.cue_summ(formcodeType,formcode,sm4_p_code,sm4_item_type));
			model.put("b_1", m6DAO.cue_summ(formcodeType,formcode,sm4_item_type, sm4_p_code,sm4_arm));
			model.put("m_2", sm4_c_code);
			model.put("m_3", sm4_q_code);
			model.put("m_4", sm4_d_code);
			model.put("m_5", sm4_b_code);
			
			model.put("m_6", sm4_p_code);
			model.put("m_9", sm4_item_type);
			model.put("m_10", sm4_prfs);
			model.put("a_sus",sm4_susno);
			model.put("a_unit",sm4_unitname);
			model.put("a_arm",sm4_arm);
			model.put("a_item_type",sm4_item_type);
			
			System.out.println("the value of  arm code is  " + sm4_arm);
			System.out.println("the value of  Item type is  " + sm4_item_type);
			System.out.println("the value of  prf group is  " + sm4_prfs);
			model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
	        
	        accssubLvl = l1.get(0).get(1);
			
	        String formcode2="";
	        String formcodeType2 ="";
	        
	        /*if(roleType.equalsIgnoreCase("ALL") && accssubLvl.equalsIgnoreCase("MISO")) {*/
	        if(accssubLvl.equalsIgnoreCase("MISO") || accssubLvl.equalsIgnoreCase("HeadQuarter")) {
				model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
				model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
				
				if(sm4_c_code.equalsIgnoreCase("ALL")) {	
					model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
				}else {
					model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", sm4_c_code, "COMMAND"));
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_c_code, "COMMAND"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_c_code, "COMMAND"));
				}
				
				if(sm4_q_code.equalsIgnoreCase("ALL")) {
					formcode2="ALL";
					formcodeType2="COMMAND";
					
					if(!sm4_c_code.equalsIgnoreCase("ALL")){
						formcode2=sm4_c_code;				
					}
					
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", formcode2, formcodeType2));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
				}else {
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", sm4_q_code, "CORPS"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_q_code, "CORPS"));
				}
				
				if(sm4_d_code.equalsIgnoreCase("ALL")) {
					formcode2="ALL";
					formcodeType2="COMMAND";
					
					if(!sm4_c_code.equalsIgnoreCase("ALL")){
						formcode2=sm4_c_code;	
						formcodeType2="COMMAND";
					}
					
					if(!sm4_q_code.equalsIgnoreCase("ALL")){
						formcode2=sm4_q_code;	
						formcodeType2="CORPS";
					}
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", formcode2, formcodeType2));	
				}else {
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", sm4_d_code, "CORPS"));
				}	
			}else {
				if(accssubLvl.equalsIgnoreCase("UNIT")) {	        	 
					model.put("m_2", l1.get(0).get(2)); 
					model.put("m_3", l1.get(0).get(3)); 
					model.put("m_1", m6DAO.mms_ue_uh_list("ALL","UNIT",l1.get(0).get(2),"ALL","",""));
				}
				if(accssubLvl.equalsIgnoreCase("BRIGADE")) {
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "BRIGADE"));
				}
				if(accssubLvl.equalsIgnoreCase("DIVISION")) {
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "DIVISION"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "DIVISION"));
				}
				if(accssubLvl.equalsIgnoreCase("CORPS")){
					model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "CORPS"));
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "CORPS"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "CORPS"));
				}
				if(accssubLvl.equalsIgnoreCase("COMMAND")){
					model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", l1.get(0).get(4), "COMMAND"));
					model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", l1.get(0).get(4), "COMMAND"));
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", l1.get(0).get(4), "COMMAND"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", l1.get(0).get(4), "COMMAND"));
				}
				if(accssubLvl.equalsIgnoreCase("LINE_DTE")) {
					//model.put("ml_6", m1.getMMSArmCodeList("ALL","ALL",session));
					model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
					model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
					model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
					model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
				}
			}		
		    return new ModelAndView("cue_wpn_eqpt_status_viewTiles");		
		}
	
		

}
