package com.controller.mms;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.mms.MMS_ADMIN_DAO;
import com.dao.mms.MMS_Adhoc_DAO;
import com.dao.mms.MMS_ReportsDAO;
import com.dao.mms.Mms_Common_DAO;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MMS_Adhoc_Query_Controller {
	
	
	@Autowired
    MMS_Adhoc_DAO adao;
	
	@Autowired
	private MMS_ADMIN_DAO m7DAO;
	
	@Autowired
	private MMS_ReportsDAO m6DAO;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	ValidationController validation = new ValidationController();
	
    @RequestMapping(value = "/mms_adhoc_query", method = RequestMethod.GET)
	public ModelAndView mms_adhoc_query(ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
    	
    	if(request.getHeader("Referer") == null ) {
    		msg = "";
    	}
    	model.put("msg", msg);
    	model.put("AdhocUList", AdhocgetMMSUnitList());
        model.put("ml_2", m1.getDomainListingData("TYPEOFHOLDING"));
		model.put("ml_6", m1.getMMSArmCodeList("ALL", "ALL", session));
		model.put("ml_1", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
		model.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
		model.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		model.put("r_1", l1);
		return new ModelAndView("mms_adhoc_query_viewTiles");
	}
	
	//@RequestMapping(value = "/AdhocgetMMSUnitList")
	public @ResponseBody ArrayList<ArrayList<String>> AdhocgetMMSUnitList() {
		ArrayList<ArrayList<String>> list = adao.mms_adhoc_query();
		return list;
	}

	@RequestMapping(value = "/AdhocList", method = RequestMethod.POST)
	public ModelAndView AdhocList(@ModelAttribute("p_c_code1") String p_c_code1,String p_q_code1,String p_d_code1,String p_b_code1,String p_p_code1,String p_hldg1,String p_sus12,String p_unit12,String p_d_from1,String p_d_to1,ModelMap model,HttpServletRequest request){
		String formcode="";
		String formcodeType="";
		if(p_sus12 != "") {
			if(validation.sus_noLength(p_sus12) == false){
				model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mms_adhoc_query");
			}else {
				formcode=p_sus12;
				formcodeType="UNIT";
			}
		}
		else {
			if (!p_c_code1.equalsIgnoreCase("ALL")) {
				formcode=p_c_code1;
				formcodeType="COMMAND";
			}
			if (!p_q_code1.equalsIgnoreCase("ALL")) {
				formcode=p_q_code1;
				formcodeType="CORPS";
			}
			if (!p_d_code1.equalsIgnoreCase("ALL")) {
				formcode=p_d_code1;
				formcodeType="DIV";
			}
			if (!p_b_code1.equalsIgnoreCase("ALL")) {
				formcode=p_b_code1;
				formcodeType="BDE";
			}
		}
		
		
		ArrayList<ArrayList<String>> list = adao.mms_ue_uh_Hirarlist(p_hldg1,formcodeType,formcode,p_p_code1,p_d_from1,p_d_to1);
		model.put("m_12", list);
		return new ModelAndView("mms_adhoc_print_tile");
	}
	
	@RequestMapping(value = "/ObsList", method = RequestMethod.POST)
	public ModelAndView ObsList(@ModelAttribute("p_c_code1") String p_c_code,String p_q_code,String p_d_code,String p_b_code,String p_p_code,String p_hldg,String p_sus14,String p_unit14,String p_d_from,String p_d_to,ModelMap model){
		String formcode="";
		String formcodeType="";
		
		if(p_sus14 != "") {
		if(validation.sus_noLength(p_sus14) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		if(p_unit14 != "") {
		if(validation.checkUnit_nameLength(p_unit14) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		ArrayList<ArrayList<String>> list = adao.ObsList(p_hldg,formcodeType,formcode,p_p_code,p_d_from,p_d_to);
		model.put("m_12", list);
		return new ModelAndView("mms_obs_print_tile");
	}
	
	@RequestMapping(value = "/BarrList", method = RequestMethod.POST)
	public ModelAndView BarrList(@ModelAttribute("p_c_code1") String p_c_code,String p_q_code,String p_d_code,String p_b_code,String p_p_code,String p_hldg,String p_sus15,String p_unit15,String p_d_from,String p_d_to,ModelMap model){
		String formcode="";
		String formcodeType="";
	
		if(p_sus15 != "") {
		if(validation.sus_noLength(p_sus15) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		if(p_unit15 != "") {
		if(validation.checkUnit_nameLength(p_unit15) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		ArrayList<ArrayList<String>> list = adao.BarrList(p_hldg,formcodeType,formcode,p_p_code,p_d_from,p_d_to);
		model.put("m_12", list);
		return new ModelAndView("mms_barr_print_tile");
	}
	
	@RequestMapping(value = "/Hirarueuh", method = RequestMethod.POST)
	public ModelAndView Hirarueuh(@ModelAttribute("np_c_code") String np_c_code,String np_q_code,String np_d_code,String np_b_code,String np_u_code,String np_p_code,String np_i_code,String np_hldg,String p_sus13,String p_unit13,String np_d_from,String np_d_to,String nrflowcontrol,ModelMap model){
		String formcode="";
		String formcodeType="";
		
		if(p_sus13 != "") {
		if(validation.sus_noLength(p_sus13) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		if(p_unit13 != "") {
		if(validation.checkUnit_nameLength(p_unit13) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
		}
		}
		
		if (!np_c_code.equalsIgnoreCase("ALL")) {
			formcode=np_c_code;
			formcodeType="COMMAND";
		}
		
		if (!np_q_code.equalsIgnoreCase("ALL")) {
			
			formcode=np_q_code;
			formcodeType="CORPS";
		}
		if (!np_d_code.equalsIgnoreCase("ALL")) {
			formcode=np_d_code;
			formcodeType="DIV";
		}
		if (!np_b_code.equalsIgnoreCase("ALL")) {
			formcode=np_b_code;
			formcodeType="BDE";
		}
		if (!np_u_code.equalsIgnoreCase("ALL")) {        
			formcode=np_u_code;
			formcodeType="UNIT";
		}
		
		ArrayList<ArrayList<String>> list = adao.Hirarueuh(np_hldg,formcodeType,formcode,np_p_code,np_i_code,np_d_from,np_d_to,nrflowcontrol);
		model.put("m_12", list);
		return new ModelAndView("mms_db_wpn_eqpt_ue_uh_tiles");
	}
	
	@RequestMapping(value = "/CmdAih", method = RequestMethod.POST)
	public ModelAndView CmdAih(@ModelAttribute("np_c_code1") String np_c_code1,String np_q_code1,String np_d_code1,String np_b_code1,String np_u_code1,String np_p_code1,String np_i_code1,String np_hldg1,String np_sus16,String np_unit16,String np_d_from1,String np_d_to1,String nrflowcontrol1,ModelMap model){
		String formcode="";
		String formcodeType="";
		
		if(np_sus16 != "") {
			if(validation.sus_noLength(np_sus16) == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		if(np_unit16 != "") {
			if(validation.checkUnit_nameLength(np_unit16) == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		
		if (!np_c_code1.equalsIgnoreCase("ALL")) {
			formcode=np_c_code1;
			formcodeType="COMMAND";
		}
		
		if (!np_q_code1.equalsIgnoreCase("ALL")) {
			
			formcode=np_q_code1;
			formcodeType="CORPS";
		}
		if (!np_d_code1.equalsIgnoreCase("ALL")) {
			formcode=np_d_code1;
			formcodeType="DIV";
		}
		if (!np_b_code1.equalsIgnoreCase("ALL")) {
			formcode=np_b_code1;
			formcodeType="BDE";
		}
		if (!np_u_code1.equalsIgnoreCase("ALL")) {        
			formcode=np_u_code1;
			formcodeType="UNIT";
		}
		ArrayList<ArrayList<String>> list = adao.CmdAih(np_hldg1,formcodeType,formcode,np_p_code1,np_i_code1,np_d_from1,np_d_to1,nrflowcontrol1);
		model.put("m_12", list);
		return new ModelAndView("mms_rpt_comd_aih_tiles");
	}

	@RequestMapping(value = "/Ustatuslist", method = RequestMethod.POST)
	public ModelAndView Ustatuslist(String np_sus18,String np_unit18,ModelMap model) {	
		String deo="ALL";
		String stat="ALL";
		if(np_sus18 != "") {
			if(validation.sus_noLength(np_sus18) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		if(np_unit18 != "") {
			if(validation.checkUnit_nameLength(np_unit18) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH)+1;
		int y = cal.get(Calendar.YEAR);
		String mnth= y+"-"+String.format("%02d", m);
		ArrayList<ArrayList<String>> list = m7DAO.UnitMcrStatusList(deo,stat,mnth,"","","","","","");
		model.put("m_12", list);
		return new ModelAndView("mms_unit_status_tiles");
	}
	
	@RequestMapping(value = "/wastreportlist", method = RequestMethod.POST)
	public ModelAndView wastreportlist(String p_sus19,String p_unit19,ModelMap model) {	
		
		if(p_sus19 != "") {
			if(validation.sus_noLength(p_sus19) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		if(p_unit19 != "") {
			if(validation.checkUnit_nameLength(p_unit19) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		
		ArrayList<ArrayList<String>> list = adao.wastreportlist();
	    model.put("m_12", list);
		return new ModelAndView("mms_wast_report_tiles");
	}
	
	@RequestMapping(value = "/CmdTypewiseHld", method = RequestMethod.POST)
	public ModelAndView CmdTypewiseHld(@ModelAttribute("np_c_code3") String np_c_code3,String np_q_code3,String np_d_code3,String np_b_code3,String np_u_code3,String np_p_code3,String np_i_code3,String np_hldg3,String np_sus17,String np_unit17,String np_d_from3,String np_d_to3,String nrflowcontrol3,ModelMap model){
		String formcode="";
		String formcodeType="";
		
	
		if(np_sus17 != "") {
			if(validation.sus_noLength(np_sus17) == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		if(np_unit17 != "") {
			if(validation.checkUnit_nameLength(np_unit17) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_adhoc_query");
			}
		}
		
		if (!np_c_code3.equalsIgnoreCase("ALL")) {
			formcode=np_c_code3;
			formcodeType="COMMAND";
		}
		if (!np_q_code3.equalsIgnoreCase("ALL")) {
			
			formcode=np_q_code3;
			formcodeType="CORPS";
		}
		if (!np_d_code3.equalsIgnoreCase("ALL")) {
			formcode=np_d_code3;
			formcodeType="DIV";
		}
		if (!np_b_code3.equalsIgnoreCase("ALL")) {
			formcode=np_b_code3;
			formcodeType="BDE";
		}
		if (!np_u_code3.equalsIgnoreCase("ALL")) {        
			formcode=np_u_code3;
			formcodeType="UNIT";
		}
		
		ArrayList<ArrayList<String>> list = adao.CmdTypewiseHld(np_hldg3,formcodeType,formcode,np_p_code3,np_i_code3,np_d_from3,np_d_to3,nrflowcontrol3);
		model.put("m_12", list);
		return new ModelAndView("mms_rpt_comd_type_wise_tiles");
	}
}