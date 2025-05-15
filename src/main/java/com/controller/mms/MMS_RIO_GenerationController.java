package com.controller.mms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_RO_RIO_GenerationDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.MMS_RIO_Generation;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MMS_RIO_GenerationController {
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	private MMS_RO_RIO_GenerationDAO m5DAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	ValidationController validation = new ValidationController();
	//RIO MODULE (1)-> (RIO STATUS SCREEN START)
	@RequestMapping(value = "/mms_rio_allocation_view", method = RequestMethod.GET)
	public ModelAndView mms_rio_rqst_screen_view(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_rio_allocation_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("m_1", m5DAO.RioRequestScreen());
		return new ModelAndView("mms_rio_allocation_viewTiles");
	}
	
	@RequestMapping(value = "/mms_rio_generation", method = RequestMethod.GET)
	public ModelAndView rio_generationUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_rio_allocation_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_rio_generationTiles");
	}
	//RIO MODULE (1)-> (RIO STATUS SCREEN END)
	
	//RIO MODULE (2)-> (RIO APPROVER SCREEN START)
	@RequestMapping(value = "/mms_rio_approver_screen_view", method = RequestMethod.GET)
	public ModelAndView mms_rio_approver_screen_view(@ModelAttribute("mms_rio_approver_screen_viewCMD") MMS_RIO_Generation rio ,
			HttpServletRequest request,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
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
		
		String c_dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		Mmap.put("m_1", m5DAO.RioApproverScreen("","","ALL",c_dt,c_dt,"0"));
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
		return new ModelAndView("mms_rio_approver_screen_viewTiles");
	}
	//RIO MODULE (2)-> (RIO APPROVER SCREEN END)
	
	//(1)-> (RIO STATUS SCREEN METHODS START)
	@RequestMapping(value = "/admin/rio_new", method = RequestMethod.POST)
	public ModelAndView RioGenerationFromRO(@ModelAttribute("ForRio1_id") int ForRio1_id,String type_issue,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_rio_allocation_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String t_name;
		if(type_issue.equals("c_ro_mst")){
			t_name = "mms_tb_comd_ro_mstr";
		}else if(type_issue.equals("ro_mst")) {
			t_name = "mms_tb_ro_mstr";
		}else {
			model.put("msg", "Data Not Found");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		List<String> list1 = m5DAO.GetRioGenerationFromRO(ForRio1_id, t_name);	
		String[] list2 = mmsCommonDAO.getMMSDistinctMlccsList(list1.get(10), "PRFCODE").get(0).split(",");
		
		ArrayList<ArrayList<String>> list3 = new ArrayList<ArrayList<String>>();
		
		for(int i=0;i<list2.length;i++) {
			list3.add(new ArrayList<String>(Arrays.asList(list2[i].split(":"))));
		}

		String ro_no = list1.get(0);
		List<String> rio_no_n =  getRio_Nocode();
		
		model.put("l_1",m5DAO.ifExistRORIONo(ro_no, list1.get(8)));
	    model.put("ForRio1_id", ForRio1_id);
	    model.put("rio_data", list1);
	    model.put("ml_1",rio_no_n);
	    model.put("ml_2", mmsCommonDAO.getUEValue(list1.get(8),list1.get(10),"ALL","UE"));
	    model.put("ml_3", mmsCommonDAO.getUHValue(list1.get(8), list1.get(10), "A0", "1", "SUMM"));
	    
	    model.put("ml_4", list3);
		return new ModelAndView("mms_rio_generationTiles");
	}
	
	public @ResponseBody List<String> getRio_Nocode(){
		String list = m5DAO.getmaxrioCode();
		String serial = "";
		if(list == null || list.equals("[null]")  ) {
			serial = "00000";
		}else{
			serial = String.valueOf(list);
		}
		int serialNo = Integer.parseInt(serial) + 1;
		
		serial = String.format("%05d", serialNo);
	
		DateFormat df = new SimpleDateFormat("MM/yyyy");
	
		String formattedDate = df.format(Calendar.getInstance().getTime());	
		String Rio_no_gen = "RIO/"+formattedDate+"/"+serial;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> listRioCode = new ArrayList();
		listRioCode.add(Rio_no_gen);
		return listRioCode;
	}
	
	@RequestMapping(value = "rio_generationNewAction", method = RequestMethod.POST )
	public ModelAndView rio_generationNewAction(HttpServletRequest request,ModelMap model,HttpSession session) {
		
		Boolean val = roledao.ScreenRedirect("mms_rio_allocation_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		MMS_RIO_Generation o =new MMS_RIO_Generation();
		String username = session.getAttribute("username").toString();
		
		String ro_no = request.getParameter("ro_no");
		ro_no = ro_no.replace("&#40;","(");
		ro_no = ro_no.replace("&#41;",")");
		
		String stk_code = request.getParameter("stk_code");
		String ro_agency = request.getParameter("ro_agency");
		ro_agency = ro_agency.replace("&#40;","(");
		ro_agency = ro_agency.replace("&#41;",")");
		String type_of_stk = request.getParameter("type_of_stk");
		String ro_type = request.getParameter("ro_type");
		String ro_for = request.getParameter("ro_for_c");
		String ro_type_of_issue = request.getParameter("ro_type_of_issue_c");
		String rio_no = request.getParameter("rio_no");
		String rio_date = request.getParameter("rio_date");
		String ro_unit = request.getParameter("to_sus_no");
		String ro_ue = request.getParameter("ro_ue");
		String ro_uh = request.getParameter("ro_uh");
		String ro_qty1 = request.getParameter("ro_qty");
		String rel_depot_sus = request.getParameter("rel_depot_sus");
		String ro_type_of_hldg = request.getParameter("ro_type_of_hldg_c");
		String remarks = request.getParameter("remarks");
		
		if(ro_no.equals("")) {
			model.put("msg", "RO No Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		if(validation.checkfrom_comd_sus_noLength(ro_no) == false){
	 		model.put("msg",validation.ro_noMSG);
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		
		
		if(stk_code.equals("")) {
			model.put("msg", "Stock Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		

		
		if(ro_agency.equals("")) {
			model.put("msg", "RO Agency Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		if(validation.checkfrom_comd_sus_noLength(ro_agency) == false){
	 		model.put("msg",validation.ro_agencyMSG);
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		
		if(ro_type.equals("")) {
			model.put("msg", "RO Type Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}

		
		
		
		if(ro_for.equals("")) {
			model.put("msg", "RO Against Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		if(validation.checkRoForLength(ro_for) == false){
	 		model.put("msg",validation.ro_forMSG);
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		
		if(ro_type_of_issue.equals("")) {
			model.put("msg", "RO Type of Issue Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
	
		

		
		if(rio_no.equals("")) {
			model.put("msg", "RIO No Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		if(validation.checkfrom_comd_sus_noLength(rio_no) == false){
	 		model.put("msg",validation.rio_noMSG);
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
	
		
		Date rio_date_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(rio_date.equals("")){
				model.put("msg", "RIO Date Should Not be Null");
				return new ModelAndView("redirect:mms_rio_allocation_view");
			}else{
				rio_date_i = formatter1.parse(request.getParameter("rio_date"));
			}
		}catch(ParseException e){         
			e.printStackTrace();
		}
		
		if(ro_unit.equals("")) {
			model.put("msg", "Unit / Comd Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		if(validation.checkUnit_nameLength(request.getParameter("ro_unit")) == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		
		if(validation.checkPrf_CodeLength(request.getParameter("prf_code")) == false){
		 		model.put("msg",validation.prfMSG );
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		
		if(ro_ue.equals("")) {
			model.put("msg", "UE Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		
		if(validation.checkRoUeLength(ro_ue) == false){
	 		model.put("msg",validation.ro_ueMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		
		if(ro_uh.equals("")) {
			model.put("msg", "UH Should Not be Null");
			return new ModelAndView("redirect:mms_rio_allocation_view");
		}
		if(validation.checkRoUeLength(ro_uh) == false){
	 		model.put("msg",validation.ro_uhMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		if(validation.check255Length(remarks) == false){
	 		model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:mms_ro_generation");
		}
		

		
	
		Date v_date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(v_date);
		cl.add(Calendar.DATE, 365);
		Date d_t = cl.getTime();
		
		int roleid = (Integer)session.getAttribute("roleid");	
		int ro_qty = Integer.parseInt(request.getParameter("ro_qty"));

		o.setRo_agency(ro_agency);
		o.setRo_no(ro_no);
		o.setRo_qty(ro_qty);
		o.setRio_no(request.getParameter("rio_no"));
		o.setRemarks(request.getParameter("remarks"));
		o.setCensus_no(request.getParameter("census_no"));
		o.setRo_ue(request.getParameter("ro_ue"));
		o.setRo_uh(request.getParameter("ro_uh"));
		o.setRo_type(request.getParameter("ro_type_c"));
		o.setRo_for(request.getParameter("ro_for_c"));
		o.setType_of_issue(request.getParameter("ro_type_of_issue_c"));
		o.setType_of_hldg(request.getParameter("ro_type_of_hldg_c"));
		o.setRo_date(request.getParameter("ro_date"));
		o.setRio_date(new Date());
		o.setTo_sus_no(request.getParameter("to_sus_no"));
		o.setPrf_code(request.getParameter("prf_code"));
		o.setRel_depot_sus(request.getParameter("rel_depot_sus_code"));
		o.setTo_comd_sus(request.getParameter("comd_sus"));
		o.setRole_id(roleid);
		o.setData_cr_by(username);
		o.setData_upd_by(username);
		o.setData_cr_date(new Date());
		o.setData_upd_date(new Date());
		o.setOp_status("0");
		o.setRio_valid_upto(d_t);
		o.setRo_valid_upto(d_t);
		o.setRo_id(Integer.parseInt(request.getParameter("ro_id")));
		o.setType_of_stk(stk_code);
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		int did = (Integer) sessionHQL.save(o);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		
		model.put("msg", "Data saved Successfully !");
		return new ModelAndView("redirect:mms_rio_allocation_view");
	}
	//(1)-> (RIO STATUS SCREEN METHODS END)
	
	//(2)-> (RIO APPROVER SCREEN METHODS START)
	@RequestMapping(value = "/admin/RIOApproverList", method =RequestMethod.POST)
	public ModelAndView RIOApproverList(@ModelAttribute("m4_c_code") String m4_c_code,String m4_q_code,String m4_d_code,
			String m4_b_code,String m4_p_code,String m4_d_from,String m4_d_to,String m4_stat,String m4_prfs,
			ModelMap model,HttpSession session){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
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
		
		model.put("m_1", m5DAO.RioApproverScreen(formcodeType,formcode,m4_p_code,m4_d_from,m4_d_to,m4_stat));
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
		
	    return new ModelAndView("mms_rio_approver_screen_viewTiles");		
	}
	
	@RequestMapping(value = "/printMMSRIOUrl",method = RequestMethod.POST)
	public ModelAndView printMMSRIOUrl(@ModelAttribute("printMMSId") String printMMSId,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_rio_approver_screen_view", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String rio_no = printMMSId.substring(6,printMMSId.length());
		model.put("m_1", m5DAO.RioPrintScreen(rio_no));	
		return new ModelAndView("printMMSRIOTiles");
	}
	//(2)-> (RIO APPROVER SCREEN METHODS END)
}
