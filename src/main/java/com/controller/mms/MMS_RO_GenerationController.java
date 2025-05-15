package com.controller.mms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_RO_RIO_GenerationDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.MMS_RO_Generation;
import com.models.MMS_TB_COMD_RO_MSTR;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MMS_RO_GenerationController {
	
	@Autowired
	private MMS_RO_RIO_GenerationDAO m5DAO;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	
	ValidationController validation = new ValidationController();
	
	
	
	//RO MODULE (1)-> (GEN RO SCREEN START)
	@RequestMapping(value = "/mms_ro_generation", method = RequestMethod.GET)
	public ModelAndView ro_generationUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_ro_generation", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_0", m1.getDomainListingData("MMSTYPEOFRO"));
		Mmap.put("ml_1", m1.getDomainValues("ROSECTION",session));
		Mmap.put("ml_2", m1.getDomainListingData("ROAGENCY"));
		Mmap.put("ml_3", m1.getDomainListingData("MMSRO"));
		Mmap.put("ml_4", m1.getDomainListingData("MMSTYPEOFISSUE"));
		Mmap.put("ml_5", m1.getDomainListingData("PBDACCOUNTS"));
		Mmap.put("ml_7", m1.getDomainListingData("MMSROSTOCK"));
		Mmap.put("ml_6", m1.getMMSDepotList(session));
		return new ModelAndView("mms_ro_generationTiles");
	}
	//RO MODULE (1)-> (GEN RO SCREEN END)
	
	//RO MODULE (2)-> (APPROVE RO SCREEN START)
	@RequestMapping(value = "/mms_ro_approver_screen_view", method = RequestMethod.GET)
	public ModelAndView mms_ro_approver_screen_view(HttpServletRequest request,ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("ROSTATUS"));
	
		Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
		Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
		Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		
		//Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		String c_dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		Mmap.put("m_1", m5DAO.RoApproverScreen("","","ALL",c_dt,c_dt,"0"));
		Mmap.put("m_2", "ALL");
		Mmap.put("m_3", "ALL");
		Mmap.put("m_4", "ALL");
		Mmap.put("m_5", "ALL");
		Mmap.put("m_6", "ALL");
		Mmap.put("m_7", c_dt);
		Mmap.put("m_8", c_dt);
		Mmap.put("m_9", "0");
		Mmap.put("m_10", "");
		Mmap.put("ex_dt", mmsCommonDAO.getExtendDate());
		return new ModelAndView("mms_ro_approver_screen_viewTiles");
	}
	//RO MODULE (2)-> (APPROVE RO SCREEN END)
	
	//RO MODULE (3)-> (BULK RO ALLOCATION BY COMD SCREEN START)
	@RequestMapping(value = "/admin/mms_bulk_ro_view", method = RequestMethod.GET)
	public ModelAndView ro_bulk(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,HttpServletRequest  request) {
		
		Boolean val = roledao.ScreenRedirect("mms_bulk_ro_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		String q = null ;
		Mmap.put("m_1", m5DAO.CmndGnrtScreen(q));
		return new ModelAndView("mms_bulk_ro_viewTiles");
	}
	//RO MODULE (3)-> (BULK RO ALLOCATION BY COMD SCREEN END)
	
	//RO MODULE (4)-> (BULK RO APPROVER SCREEN START)
	@RequestMapping(value = "/mms_bulk_ro_approver_screen_view", method = RequestMethod.GET)
	public ModelAndView mms_bulk_ro_approver_screen_view(HttpServletRequest request,ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("mms_bulk_ro_approver_screen_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		String c_dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Mmap.put("m_1", m5DAO.CmndRoApproverScreen("","","ALL",c_dt,c_dt,"0"));
		Mmap.put("ml_1", m1.getDomainListingData("ROSTATUS"));
		
		Mmap.put("ml_2", mmsCommonDAO.getMMSDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mmsCommonDAO.getMMSDistinctHirarName("CORPS", "ALL", "COMMAND"));
		Mmap.put("ml_4", mmsCommonDAO.getMMSDistinctHirarName("DIVISION", "ALL", "COMMAND"));
		Mmap.put("ml_5", mmsCommonDAO.getMMSDistinctHirarName("BRIGADE", "ALL", "COMMAND"));
		Mmap.put("m_2", "ALL");
		Mmap.put("m_3", "ALL");
		Mmap.put("m_4", "ALL");
		Mmap.put("m_5", "ALL");
		Mmap.put("m_6", "ALL");
		Mmap.put("m_7", c_dt);
		Mmap.put("m_8", c_dt);
		Mmap.put("m_9", "0");
		Mmap.put("m_10", "");
		Mmap.put("ex_dt", mmsCommonDAO.getExtendDate());
		return new ModelAndView("mms_bulk_ro_approver_screen_viewTiles");
	}
	//RO MODULE (4)-> (BULK RO APPROVER SCREEN END)
	
	//(1)-> (GEN RO SCREEN METHODS START)
	@RequestMapping(value = "/getRo_Nocode", method = RequestMethod.POST)
	public @ResponseBody List<String> getRo_Nocode(HttpSession s1){
		
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		
		String list = m5DAO.getmaxroCode();
		String serial = "";
		if(list == null || list.equals("[null]")){
			serial = "00000";
		}else{
			serial = String.valueOf(list);
		}
		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%05d", serialNo);
		DateFormat df = new SimpleDateFormat("MM/yyyy");
		String formattedDate = df.format(Calendar.getInstance().getTime());	
		String Ro_no_gen = "/"+formattedDate+"/"+serial;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		List<String> listRoCode = new ArrayList();
		listRoCode.add(Ro_no_gen);
		
		if(listRoCode.get(0) != "") {
			List<String> FList = mmsCommonDAO.getMMSEncList(listRoCode, s1);
			return FList;
		}else {
			return listRoCode;
		}
	}
	
	@RequestMapping(value = "ro_generationAction", method = RequestMethod.POST)
	public ModelAndView ro_generationAction(HttpServletRequest request,ModelMap model,HttpSession session) {	
		
		Boolean val = roledao.ScreenRedirect("mms_ro_generation", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		MMS_RO_Generation o =new MMS_RO_Generation();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		
		String section = request.getParameter("section");
		String ro_no = request.getParameter("ro_no");
		ro_no = ro_no.replace("&#40;","(");
		ro_no = ro_no.replace("&#41;",")");
		
		String ro_date = request.getParameter("ro_date");
		String ro_valid = request.getParameter("ro_valid");
		String ro_agency = request.getParameter("ro_agency");
		ro_agency = ro_agency.replace("&#40;","(");
		ro_agency = ro_agency.replace("&#41;",")");
		
		String ro_type = request.getParameter("ro_type");
		String ro_for = request.getParameter("ro_for");
		String type_of_issue = request.getParameter("type_of_issue");
		String type_of_stk = request.getParameter("type_of_stk");
		String[] pbd_head_val = request.getParameterValues("pbd_head_t");
		String[] re_ro_unit = request.getParameterValues("re_ro_unit_code_t");
		String[] re_ro_prf_code = request.getParameterValues("re_ro_prf_code_t");
		String[] re_ro_ue = request.getParameterValues("re_ro_ue_t");
		String[] re_ro_uh = request.getParameterValues("re_ro_uh_t");
		String[] re_ro_qty = request.getParameterValues("re_ro_qty_t");
		String[] re_ro_depot_sus = request.getParameterValues("re_ro_depot_sus_t");
		
		if(section.equals("0")){
			model.put("msg", "Please Select the Section");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(validation.checkCatPartNoLength(request.getParameter("ref_no")) == false){
	 		model.put("msg",validation.ref_noMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		
		if(ro_no.equals("")){
			model.put("msg", "RO No Should not be Null");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		if(validation.checkfrom_comd_sus_noLength(ro_no) == false){
	 		model.put("msg",validation.ro_noMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		Date ro_date_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(ro_date.equals("")){
				model.put("msg", "RO Dated Should not be Null");
				return new ModelAndView("redirect:mms_ro_generation");
			}else{
				ro_date_i = formatter1.parse(request.getParameter("ro_date"));
			}
		}catch(ParseException e){         
			e.printStackTrace();
		}
		
		if(validation.checkDateLength(ro_date) == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		
		
		if(ro_agency.equals("0")){
			model.put("msg", "Please Select the RO Agency");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		if(validation.checkfrom_comd_sus_noLength(ro_agency) == false){
	 		model.put("msg",validation.ro_agencyMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		
		
		if(ro_type.equals("0")){
			model.put("msg", "Please Select the RO Type");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(ro_for.equals("0")){
			model.put("msg", "Please Select the RO Against");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(type_of_stk.equals("0")){
			model.put("msg", "Please Select the Type of Stock");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(type_of_issue.equals("0")){
			model.put("msg", "Please Select the Type of Issue");
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(type_of_issue.equals("5")){
			for(int i=0;i<re_ro_unit.length;i++) {
				if(pbd_head_val[i].equals("")) {
					model.put("msg", "Please Select the Budget Head");
					return new ModelAndView("redirect:mms_ro_generation"); 
				}
			}
		}
		
		if(validation.checkNomenLength(request.getParameter("ro_command")) == false){
	 		model.put("msg",validation.ro_commandMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		String ro_ue = request.getParameter("ro_ue");
		
		if(validation.checkRoUeLength(ro_ue) == false){
	 		model.put("msg",validation.ro_ueMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		String ro_uh = request.getParameter("ro_uh");
		if(validation.checkRoUeLength(ro_uh) == false){
	 		model.put("msg",validation.ro_uhMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		//String remarks = request.getParameter("remarks");
		
		
		Date ro_valid_i = null;
		try{
			if(ro_valid.equals("")){
				model.put("msg", "RO Valid Upto Should not be Null");
				return new ModelAndView("redirect:mms_ro_generation");
			}else{
				ro_valid_i = formatter1.parse(request.getParameter("ro_valid"));
			}
		}catch(ParseException e){         
			e.printStackTrace();
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_unit[i].equals("")) {
				model.put("msg", "Please Select the Unit / Comd");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_prf_code[i].equals("")) {
				model.put("msg", "Please Select the Eqpt PRF Group");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_unit[i].equals("")) {
				model.put("msg", "Please Select the Unit / Comd");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_ue[i].equals("")) {
				model.put("msg", "UE Should not be Null");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_uh[i].equals("")) {
				model.put("msg", "UH Should not be Null");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_qty[i].equals("")) {
				model.put("msg", "Please Enter Qty to be Issued");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}
		
		for(int i=0;i<re_ro_unit.length;i++) {
			if(re_ro_depot_sus[i].equals("")) {
				model.put("msg", "Please Select RO Depot Name");
				return new ModelAndView("redirect:mms_ro_generation"); 
			}
		}

		Date v_date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(v_date);
		cl.add(Calendar.DATE, 90);
		Date d_t = cl.getTime();
		
		int roleid = (Integer)session.getAttribute("roleid");
		
		
		String[] re_ro_type_hld = request.getParameterValues("ro_type_hld_code_t");	
		String[] re_ro_command_sus = request.getParameterValues("ro_command_sus_t");
		String[] census_no_hid = request.getParameterValues("census_no_hid_t");
		String[] re_ro_remarks = request.getParameterValues("re_ro_remarks_t");
		String[] re_ro_inst_rem = request.getParameterValues("re_ro_inst_rem");
		
		//String re_ro_remarks
		
		
		for(int i=0;i<re_ro_unit.length;i++){	
			
			if(validation.check255Length(re_ro_remarks[i]) == false){
				model.put("msg",validation.remarksMSGTMS);
				return new ModelAndView("redirect:mms_ro_generation");
			}
			o.setTo_sus_no(re_ro_unit[i]);
			o.setRo_agency(ro_agency);
			o.setRo_no(ro_no);
			o.setRo_type(request.getParameter("ro_type"));
			o.setRo_for(request.getParameter("ro_for"));
			o.setRo_date(request.getParameter("ro_date"));
			o.setType_of_issue(request.getParameter("type_of_issue"));		
			o.setType_of_stk(type_of_stk);
			o.setCensus_no(request.getParameter("census_no"));
			o.setTo_sus_no(re_ro_unit[i]);
			o.setPrf_code(re_ro_prf_code[i]);
			o.setRo_for(re_ro_type_hld[i]);
			o.setTo_comd_sus(re_ro_command_sus[i]);
			o.setRo_qty(Integer.parseInt(re_ro_qty[i]));
			o.setRel_depot_sus(re_ro_depot_sus[i]);
			o.setRemarks(re_ro_remarks[i]);
			o.setSpl_remarks(re_ro_inst_rem[i]);
			o.setHead(pbd_head_val[i]);
			o.setRo_ue(re_ro_ue[i]);
			o.setRo_uh(re_ro_uh[i]);
			o.setOp_status("0");
			o.setRole_id(roleid);
			o.setData_cr_by(username);
			o.setData_cr_date(new Date());
			o.setData_upd_by(username);
			o.setData_upd_date(new Date());
			o.setRo_valid_upto(ro_valid_i);
			o.setSection(request.getParameter("section"));
			o.setRef_no(request.getParameter("ref_no"));
			o.setCensus_no(census_no_hid[i]);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			int did = (Integer) sessionHQL.save(o);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		
		
		
		model.put("msg", "Data saved Successfully !");
		return new ModelAndView("redirect:mms_ro_generation");
	}
	//(1)-> (GEN RO SCREEN METHODS END)
	
	//(2)-> (APPROVE RO SCREEN METHODS START)
	@RequestMapping(value = "/admin/ROApproverList", method = RequestMethod.POST)
	public ModelAndView ROApproverList(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,String m4_b_code,
			String m4_p_code,String m4_d_from,String m4_d_to,String m4_stat,String m4_prfs,ModelMap model,HttpSession session){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());
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
		
		model.put("m_1", m5DAO.RoApproverScreen(formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to,m4_stat));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);
		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_stat);
		model.put("m_10", m4_prfs);
		model.put("m_11", mmsCommonDAO.getMMSPRFtListBySearch2(m4_prfs.toUpperCase()));
		model.put("ml_1", m1.getDomainListingData("ROSTATUS"));
		model.put("ex_dt", mmsCommonDAO.getExtendDate());
		
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
		
	
	    return new ModelAndView("mms_ro_approver_screen_viewTiles");		
	}
	
	@RequestMapping(value = "/printMMSROUrl",method=RequestMethod.POST)
	public ModelAndView printMMSROUrl(@ModelAttribute("printMMSId") String printMMSId,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String ro_no = printMMSId.substring(6,printMMSId.length());
		ro_no = ro_no.replace("&#40;","(");
		ro_no = ro_no.replace("&#41;",")");
		
		model.put("m_1", m5DAO.RoPrintScreen(ro_no));
		return new ModelAndView("printmmsroTiles");	
	}
	//(2)-> (APPROVE RO SCREEN METHODS END)
	
	//(3)-> (BULK RO ALLOCATION BY COMD SCREEN METHODS START)
	@RequestMapping(value = "/admin/mms_bulk_rio_allocation", method = RequestMethod.POST )
	public ModelAndView cmd_ro(@ModelAttribute("editId") int entryId, String cqty,ModelMap model, 
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession s1) {
		
		int userid = (Integer)s1.getAttribute("userId");
		String roleType = (String) s1.getAttribute("roleType");
		String accsLvl = (String) s1.getAttribute("roleAccess");
		String accssubLvl=(String) s1.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_bulk_ro_view", s1.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(s1);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		model.put("r_1", l1);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select r.ro_no,r.ro_agency,ro_qty,"
				+ "to_comd_sus,(select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=r.to_comd_sus),"
				+ "prf_code,(select prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=r.prf_code and status_active='Active'),"
				+ "ro_for from MMS_RO_Generation r where id=:id");
		q.setParameter("id", entryId);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		
		model.put("entryId", entryId);
		model.put("RO_DATA", list);
		model.put("c_qty", cqty);
		model.put("ml_1", m1.getDomainListingData("TYPEOFHOLDING"));
		model.put("ml_2", m1.getDomainListingData("MMSRO"));
		

		return new ModelAndView("mms_bulk_rio_allocationTiles");
	}
	
	@RequestMapping(value = "/COMDROGENAction", method = RequestMethod.POST)
	public ModelAndView COMDROGENAction(HttpServletRequest request,ModelMap model,HttpSession session) {	
		
		Boolean val = roledao.ScreenRedirect("mms_bulk_ro_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		MMS_TB_COMD_RO_MSTR dtl = new MMS_TB_COMD_RO_MSTR();
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		int roleid = (Integer)session.getAttribute("roleid");	
		
		String from_comd_sus_no = request.getParameter("from_comd_sus_no");
		String ro_agency = request.getParameter("ro_agency");
		ro_agency = ro_agency.replace("&#40;","(");
		ro_agency = ro_agency.replace("&#41;",")");
		
		String ro_no = request.getParameter("ro_no");
		ro_no = ro_no.replace("&#40;","(");
		ro_no = ro_no.replace("&#41;",")");
		
		String comd_ro_date1 = request.getParameter("comd_ro_date1");
		String prf_code = request.getParameter("prf_code");
		String[] sus_no_t = request.getParameterValues("sus_no_t");
		String[] type_of_hldg_t = request.getParameterValues("type_of_hldg_t");
		String[] ro_type_t = request.getParameterValues("ro_type_t");
		String[] depot_sus_t = request.getParameterValues("depot_sus_t");
		String[] ro_ue_t = request.getParameterValues("ro_ue_t");
		String[] ro_uh_t = request.getParameterValues("ro_uh_t");
		String[] qty_t = request.getParameterValues("qty_t");
		
		if(from_comd_sus_no.equals("")) {
			model.put("msg", "Comd SUS No Should Not be Null");
			return new ModelAndView("redirect:mms_bulk_ro_view"); 
		}
		
		if(validation.sus_noLength(from_comd_sus_no) == false){
	 		model.put("msg",validation.from_comd_sus_noMSG);
			return new ModelAndView("redirect:mms_bulk_ro_view");
		}
		
		
		if(ro_agency.equals("")) {
			model.put("msg", "RO Agency Should Not be Null");
			return new ModelAndView("redirect:mms_bulk_ro_view"); 
		}
		if(validation.checkfrom_comd_sus_noLength(ro_agency) == false){
	 		model.put("msg",validation.ro_agencyMSG);
			return new ModelAndView("redirect:mms_bulk_ro_view");
		}
		
		
		if(ro_no.equals("")) {
			model.put("msg", "Comd RO No Should Not be Null");
			return new ModelAndView("redirect:mms_bulk_ro_view"); 
		}
		if(validation.checkfrom_comd_sus_noLength(ro_no) == false){
	 		model.put("msg",validation.ro_noMSG);
			return new ModelAndView("redirect:mms_bulk_ro_view");
		}
		
		
		
		Date comd_ro_d = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(comd_ro_date1.equals("")){
				model.put("msg", "Date Should Not be Null");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}else{
				comd_ro_d = formatter1.parse(request.getParameter("comd_ro_date1"));
			}
		}catch(ParseException e){         
			e.printStackTrace();
		}
		
		
		if(prf_code.equals("")) {
			model.put("msg", "PRF Code Should Not be Null");
			return new ModelAndView("redirect:mms_bulk_ro_view"); 
		}
		  
		
		if(validation.checkPrf_CodeLength(prf_code) == false){
	 		model.put("msg",validation.prfMSG );
			return new ModelAndView("redirect:mms_bulk_ro_view");
		}
		
		
		for(int i=0;i<qty_t.length;i++){	
			if(sus_no_t[i].equals("") && sus_no_t[i] != null) {
				model.put("msg", "Please Enter the SUS NO");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
			
			if(validation.sus_noLength(sus_no_t[i].toString()) == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mms_bulk_ro_view");
			}
		}
		
	
		
		
		for(int i=0;i<qty_t.length;i++){	
			if(type_of_hldg_t[i].equals("-1")) {
				model.put("msg", "Please Select the Type of Release");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		
		for(int i=0;i<qty_t.length;i++){	
			if(ro_type_t[i].equals("0")) {
				model.put("msg", "Please Select the Comd Ro Type");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		
		for(int i=0;i<qty_t.length;i++){	
			if(depot_sus_t[i].equals("")) {
				model.put("msg", "Please Enter the Depot SUS No");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		
		
		
		for(int i=0;i<qty_t.length;i++){	
			if(ro_ue_t[i].equals("")) {
				model.put("msg", "UE Should not be Null");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		
		for(int i=0;i<qty_t.length;i++){	

			if(validation.checkRoUeLength(ro_ue_t[i].toString()) == false){
		 		model.put("msg",validation.ro_ueMSG);
				return new ModelAndView("redirect:mms_bulk_ro_view");
			}
		}
		
		
		for(int i=0;i<qty_t.length;i++){	
			if(ro_uh_t[i].equals("")) {
				model.put("msg", "UH Should not be Null");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		

		
		for(int i=0;i<qty_t.length;i++){	

			if(validation.checkRoUeLength(ro_uh_t[i].toString()) == false){
		 		model.put("msg",validation.ro_uhMSG);
				return new ModelAndView("redirect:mms_bulk_ro_view");
			}
		}
		
		for(int i=0;i<qty_t.length;i++){	
			if(qty_t[i].equals("")) {
				model.put("msg", "Please Enter Qty to be Issued");
				return new ModelAndView("redirect:mms_bulk_ro_view"); 
			}
		}
		
		Date v_date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(v_date);
		cl.add(Calendar.DATE, 90);
		Date d_t = cl.getTime();
	
		for(int i=0;i<qty_t.length;i++){	
			dtl.setData_cr_by(username);
			dtl.setData_cr_date(new Date());
			dtl.setData_upd_by(username);
			dtl.setData_upd_date(new Date());
			dtl.setOp_status("0");
			dtl.setRole_id(roleid);
			dtl.setRo_valid_upto(d_t);
			
			dtl.setTo_sus_no(sus_no_t[i]);
			dtl.setRo_type(ro_type_t[i]);
			dtl.setType_of_hldg(type_of_hldg_t[i]);
			
			dtl.setRel_depot_sus(depot_sus_t[i]);
			dtl.setRo_qty(Integer.parseInt(qty_t[i]));
			dtl.setRo_ue(ro_ue_t[i]);
			dtl.setRo_uh(ro_uh_t[i]);
			dtl.setComd_ro_date(new Date());
			dtl.setRio_date(new Date());
			dtl.setPrf_code(request.getParameter("prf_code"));
			dtl.setRo_agency(ro_agency);
			dtl.setRo_no(ro_no);
			dtl.setRo_for(request.getParameter("ro_for"));
			
			String cmd = ro_no + "/C" + (i+1);
			dtl.setComd_ro_no(cmd);
			dtl.setFrom_comd_sus(request.getParameter("from_comd_sus_no"));
			dtl.setRo_date(request.getParameter("comd_ro_date1"));
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			int did = (Integer) sessionHQL.save(dtl);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		model.put("msg", "Your COMD RO Allocation has been submitted successfully.");
		return new ModelAndView("redirect:mms_bulk_ro_approver_screen_view");
	}
	//(3)-> (BULK RO ALLOCATION BY COMD SCREEN METHODS END)

	//(4)-> (BULK RO APPROVER SCREEN METHODS START)
	@RequestMapping(value = "/admin/BulkROApproverList", method = RequestMethod.POST)
	public ModelAndView BulkROApproverList(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,
			String m4_b_code,String m4_p_code,String m4_d_from,String m4_d_to,String m4_stat,String m4_prfs,ModelMap model,
			HttpSession session){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_bulk_ro_approver_screen_view", session.getAttribute("roleid").toString());
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
		
		model.put("m_1", m5DAO.CmndRoApproverScreen(formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to,m4_stat));
		model.put("m_2", m4_c_code);
		model.put("m_3", m4_q_code);
		model.put("m_4", m4_d_code);
		model.put("m_5", m4_b_code);
		model.put("m_6", m4_p_code);
		model.put("m_7", m4_d_from);
		model.put("m_8", m4_d_to);
		model.put("m_9", m4_stat);
		model.put("m_10", m4_prfs);
		model.put("m_11", mmsCommonDAO.getMMSPRFtListBySearch2(m4_prfs.toUpperCase()));
		model.put("ml_1", m1.getDomainListingData("ROSTATUS"));
		model.put("ex_dt", mmsCommonDAO.getExtendDate());
		
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
	    return new ModelAndView("mms_bulk_ro_approver_screen_viewTiles");		
	}
	
	@RequestMapping(value = "/printMMSCMNDROUrl")
	public ModelAndView printMMSCMNDROUrl(@Valid @Validated @ModelAttribute("pId") int a,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
		model.put("mms_cmnd_ro_approver_screen_viewCMD", m5DAO.viewMMSCMNDRo(a));	
		return new ModelAndView("printMMSCMNDROTiles");
	}
	
	//(4)-> (BULK RO APPROVER SCREEN METHODS END)
	
	//(5) -> (COMMON RO, BULK RO, RIO SCREEN METHODS START)
	@RequestMapping(value = "/admin/DataApproved",method=RequestMethod.POST)
	public ModelAndView DataApproved(@ModelAttribute("a_ro") String a_ro,String a_type,String a_para,ModelMap model,
			HttpSession session){
		
		a_ro = a_ro.replace("&#40;","(");
		a_ro = a_ro.replace("&#41;",")");
		
		Boolean val = null;
		if(a_para.equalsIgnoreCase("RO") || a_para.equalsIgnoreCase("RO-1")) {
			val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para.equalsIgnoreCase("CMDRO")) {
			val = roledao.ScreenRedirect("mms_bulk_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para.equalsIgnoreCase("RIO")) {
			val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
		}
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		model.put("msg", m5DAO.approveRORqst(a_ro,a_type,a_para,username));
		
		if(a_para.equalsIgnoreCase("RO") || a_para.equalsIgnoreCase("RO-1")) {
			return new ModelAndView("redirect:mms_ro_approver_screen_view");	
		}
		if(a_para.equalsIgnoreCase("CMDRO")) {
			return new ModelAndView("redirect:mms_bulk_ro_approver_screen_view");	
		}
		if(a_para.equalsIgnoreCase("RIO")) {
			return new ModelAndView("redirect:mms_rio_approver_screen_view");	
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/DataExtended",method=RequestMethod.POST)
	public ModelAndView DataExtended(@ModelAttribute("a_ro2") String a_ro2,String a_para2,Integer a_id2,ModelMap model,
			HttpSession session){
		
		a_ro2 = a_ro2.replace("&#40;","(");
		a_ro2 = a_ro2.replace("&#41;",")");
		
		Boolean val = null;
		if(a_para2.equalsIgnoreCase("RO")) {
			val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para2.equalsIgnoreCase("CMDRO")) {
			val = roledao.ScreenRedirect("mms_bulk_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para2.equalsIgnoreCase("RIO")) {
			val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
		}
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", m5DAO.Extendro_date(a_ro2,a_para2,a_id2));
		if(a_para2.equalsIgnoreCase("RO")) {
			return new ModelAndView("redirect:mms_ro_approver_screen_view");	
		}
		if(a_para2.equalsIgnoreCase("CMDRO")) {
			return new ModelAndView("redirect:mms_bulk_ro_approver_screen_view");	
		}
		if(a_para2.equalsIgnoreCase("RIO")) {
			return new ModelAndView("redirect:mms_rio_approver_screen_view");	
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/DataCancelled",method=RequestMethod.POST)
	public ModelAndView DataCancelled(@ModelAttribute("a_ro3") String a_ro3,String a_para3,Integer a_id3,ModelMap model,
			HttpSession session){
		
		a_ro3 = a_ro3.replace("&#40;","(");
		a_ro3 = a_ro3.replace("&#41;",")");
		
		Boolean val = null;
		if(a_para3.equalsIgnoreCase("RO")) {
			val = roledao.ScreenRedirect("mms_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para3.equalsIgnoreCase("CMDRO")) {
			val = roledao.ScreenRedirect("mms_bulk_ro_approver_screen_view", session.getAttribute("roleid").toString());	
		}
		if(a_para3.equalsIgnoreCase("RIO")) {
			val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
		}
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", m5DAO.ROCancelApprover(a_ro3,a_para3,a_id3));
		if(a_para3.equalsIgnoreCase("RO")) {
			return new ModelAndView("redirect:mms_ro_approver_screen_view");	
		}
		if(a_para3.equalsIgnoreCase("CMDRO")) {
			return new ModelAndView("redirect:mms_bulk_ro_approver_screen_view");	
		}
		if(a_para3.equalsIgnoreCase("RIO")) {
			return new ModelAndView("redirect:mms_rio_approver_screen_view");	
		}
		
		return null;
	}
	//(5) -> (COMMON RO, BULK RO, RIO SCREEN METHODS START)
}
