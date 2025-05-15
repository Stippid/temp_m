package com.controller.orbat;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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

import com.controller.district.DistrictController;
import com.controller.notification.NotificationController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.state.StateController;
import com.controller.validation.ValidationController;
import com.dao.Notification.notificatioDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.AllMethodsDAO;
import com.dao.orbat.DisbandmentDAO;
import com.dao.orbat.DisbandmentDAOImpl;
import com.dao.orbat.MisoOrbatShdulDetlDAO;
import com.dao.orbat.MisoOrbatShdulDetlDAOImpl;
import com.dao.orbat.ReportsDAO;
import com.dao.orbat.UnitProfileDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.Tb_Miso_Orbat_Code;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class DisbandmentController {

	MisoOrbatShdulDetlDAO misoOrbatShdulDetlDAO = new MisoOrbatShdulDetlDAOImpl();
	DisbandmentDAO disbandmentDAO = new DisbandmentDAOImpl();
	@Autowired
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	ValidationController validation = new ValidationController();
	
	@Autowired
	UnitProfileDAO unitProfileDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	AllMethodsDAO allMethodsDAO;
	
	@Autowired
	ReportsDAO  UOD;
	
	@Autowired
	private notificatioDAO  notif;
		
	Psg_CommonController comm = new Psg_CommonController();
	///bisag v2 2508022(notification)
	NotificationController notification = new NotificationController();
	 
	
	@RequestMapping(value = "/Disbandment_schedule", method = RequestMethod.GET)
	public ModelAndView Disbandment_schedule(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Disbandment_schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		return new ModelAndView("disbandment_scheduleTiles");
	}
	
	@RequestMapping(value = "/misodisbandmentConversionAction",method = RequestMethod.POST)
	public ModelAndView addItemEntryForm1(@ModelAttribute("misoOrbatUntDtlCMD") Miso_Orbat_Unt_Dtl ud,
			@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
			@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
			HttpServletRequest request,ModelMap model,HttpSession sessionA) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Disbandment_schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String username = sessionA.getAttribute("username").toString();
		//String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
		String goi_letter_no = request.getParameter("goi_letter_no");
		String letter_no = request.getParameter("letter_no");
		
		if(goi_letter_no.equals(""))
		{
			model.put("msg", "Please Enter GoI Letter No");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(validation.checkgoi_Letter_noLength(goi_letter_no)  == false){
	 		model.put("msg",validation.goi_Letter_noMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(letter_no.equals(""))
		{
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(validation.checkLetter_noLength(letter_no)  == false){
	 		model.put("msg",validation.Letter_noMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		
		String date_goi_letter1 = request.getParameter("date_goi_letter");
		Date date_goi_letter = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			if(date_goi_letter1.equals(""))
			{
				model.put("msg", "Please Enter GoI Letter Date");
				return new ModelAndView("redirect:Disbandment_schedule");
			}else {
				
				date_goi_letter = formatter.parse(request.getParameter("date_goi_letter"));
			}
		} catch (ParseException e) {
		
		}
		
		String sanction_date1 = request.getParameter("sanction_date");
		Date sanction_date = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			if(sanction_date1.equals(""))
			{
				model.put("msg", "Please Enter Letter Date");
				return new ModelAndView("redirect:Disbandment_schedule");
			}else {
				
				sanction_date = formatter1.parse(request.getParameter("sanction_date"));
			}
		} catch (ParseException e) {         
			
		}
		
		String upload_goi_letterExt = FilenameUtils.getExtension(upload_goi_letter.getOriginalFilename());
		String upload_auth_letterExt = FilenameUtils.getExtension(upload_auth_letter.getOriginalFilename());
		if(!upload_goi_letterExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(!upload_auth_letterExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(!misoOrbatShdulDetlDAO.checkPDFFileValidationOrbat(upload_goi_letter,upload_auth_letter)) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		String unit_name = request.getParameter("unit_name");
		if(unit_name.equals(""))
		{
			model.put("msg", "Please Enter Unit Name");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(validation.checkUnit_nameLength(unit_name)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		
		String sus_no = request.getParameter("sus_no");
		if(sus_no.equals(""))
		{
			model.put("msg", "Please Enter SUS No");
			return new ModelAndView("redirect:Disbandment_schedule");
		}		
		if(all.getSusNoActiveList(sessionA,sus_no).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		if(validation.sus_noLength(sus_no)  == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		
		String address = request.getParameter("address");
		if(validation.checkAddressLength(address)  == false){
	 		model.put("msg",validation.addressMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String cont_aname = request.getParameter("cont_aname");
		if(validation.checkUnit_nameLength(cont_aname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		String cont_bname = request.getParameter("cont_bname");
		if(validation.checkUnit_nameLength(cont_bname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		String cont_cname = request.getParameter("cont_cname");
		if(validation.checkUnit_nameLength(cont_cname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		String cont_dname = request.getParameter("cont_dname");
		if(validation.checkUnit_nameLength(cont_dname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Disbandment_schedule");
		}
		
		String comm_depart_date1 = request.getParameter("comm_depart_date");
		Date comm_depart_date = null;
		try {	
			if(comm_depart_date1.equals(""))
			{
				model.put("msg", "Please Enter Comm Depart Date");
				return new ModelAndView("redirect:Disbandment_schedule");
			}else {
				
				comm_depart_date = formatter1.parse(request.getParameter("comm_depart_date"));
			}
		} catch (ParseException e) {         
			
		}
		
		String compltn_arrv_date1 = request.getParameter("compltn_arrv_date");
		Date compltn_arrv_date = null;
		try {	
			if(compltn_arrv_date1.equals(""))
			{
				model.put("msg", "Please Enter Compltn Arrv Date");
				return new ModelAndView("redirect:Disbandment_schedule");
			}else {
				
				compltn_arrv_date = formatter1.parse(request.getParameter("compltn_arrv_date"));
			}
		} catch (ParseException e) {         
			
		}
		
		//*****************************ALL DATA BY SUS_NO***************************************************************
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction txVer = session1.beginTransaction();
		Query q = session1.createQuery("from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		txVer.commit();
		session1.close();
		 
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
		
			ud.setForm_code_admin(list.get(0).getForm_code_admin());
			ud.setForm_code_control(list.get(0).getForm_code_control());
			ud.setForm_code_operation(list.get(0).getForm_code_operation());  
			ud.setRemarks(list.get(0).getRemarks());   
			ud.setCode( list.get(0).getCode()) ;
			ud.setCode_type(list.get(0).getCode_type());	
			ud.setModification(list.get(0).getModification());
			ud.setType_force(list.get(0).getType_force());
			ud.setCt_part_i_ii(list.get(0).getCt_part_i_ii());
			ud.setSus_no_for_comb_disint(list.get(0).getSus_no());
			ud.setType_of_location(list.get(0).getType_of_location()); 
			ud.setArm_code(list.get(0).getArm_code());  
			ud.setUnit_army_hq(list.get(0).getUnit_army_hq());
		   	ud.setLetter_id(list.get(0).getLetter_id());
			ud.setNrs_code(list.get(0).getNrs_code());
			ud.setCondition(list.get(0).getCondition());
			ud.setComm_depart_date(comm_depart_date);
			ud.setCompltn_arrv_date(compltn_arrv_date);
			ud.setStatus_sus_no("Pending");
			ud.setEntity("UNIT");
			ud.setIs_unit_pending("Y");
			ud.setAddress(request.getParameter("address"));
			ud.setCreation_by(username);
			ud.setCreation_on(new Date());
			ud.setLevel_c(list.get(0).getLevel_c());
			ud.setLevel_d(list.get(0).getLevel_d());
			ud.setSus_version(allMethodsDAO.getSusVersion(sus_no));

			int uid = (Integer) sessionHQL.save(ud);
			sessionHQL.flush();
			sessionHQL.clear();
		
		
			Session session12 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session12.beginTransaction();
			Query q2 = session12.createQuery("from Miso_Orbat_Shdul_Detl where letter_id=:letter_id");
			q2.setParameter("letter_id", list.get(0).getId());
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Shdul_Detl> listShdule = (List<Miso_Orbat_Shdul_Detl>) q2.list();
			tx2.commit();
			session12.close();
				
			Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
			
			String  orbatFilePath = sessionA.getAttribute("orbatFilePath").toString();
			misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
			
			if(validation.checkUpload_goiLetterLength(shdul.getUpload_goi_letter())  == false){
		 		model.put("msg",validation.Upload_goiLetterMSG);
				return new ModelAndView("redirect:Disbandment_schedule");
			}
			if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
		 		model.put("msg",validation.Upload_authLetterMSG);
				return new ModelAndView("redirect:Disbandment_schedule");
			}
			
			shdul.setLevel_a(listShdule.get(0).getLevel_a());
			shdul.setLevel_b(listShdule.get(0).getLevel_b());
			shdul.setGoi_letter_no(goi_letter_no);
			shdul.setLetter_no(letter_no);
			shdul.setDate_goi_letter(date_goi_letter);
			shdul.setSanction_date(sanction_date);
			shdul.setType_of_letter("4"); 		
			shdul.setLetter_id(uid);
			shdul.setCreated_by(username);
			shdul.setCreated_on(new Date());
			shdul.setStatus("0");
			
			sessionHQL.save(shdul);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			model.put("msg", "Data Saved Successfully");
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    			model.put("msg", "roll back transaction");
    		}catch(RuntimeException rbe){
    			model.put("msg", "Couldnt roll back transaction " + rbe);
    		}
    		throw e;
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}
		return new ModelAndView("redirect:Disbandment_schedule");
	}
	
	private String scenario ="";
	private String status_sus_no="";
	private String unit_name ="";
	private String comm_depart_date = "";
	private String compltn_arrv_date = "";
	
	@RequestMapping(value="/admin/search_rising_dis_profileSetSession")
	public ModelAndView search_rising_dis_profileSetSession(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "scenario1", required = false) String scenario1,
			@RequestParam(value = "status_sus_no1", required = false) String status_sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "comm_depart_date1", required = false) String comm_depart_date1,
			@RequestParam(value = "compltn_arrv_date1", required = false) String compltn_arrv_date1,HttpSession sessionA){
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		if (scenario1.equals("")) {
			model.put("msg", "Please Select Scenario");
			return new ModelAndView("redirect:SearchRaising_disbandment");
		}
		
		if(validation.checkUnit_nameLength(unit_name1)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:SearchRaising_disbandment");
		}
		
		unit_name1 = unit_name1.replace("&#40;","(");
		unit_name1 = unit_name1.replace("&#41;",")");
		
		 scenario = scenario1;
		 status_sus_no = status_sus_no1;
		 unit_name= unit_name1;
		 comm_depart_date = comm_depart_date1;
    	 compltn_arrv_date =  compltn_arrv_date1;
		
    	 model.put("msg", "ok");
		return new ModelAndView("redirect:SearchRaising_disbandment");
	}
	
	
	@RequestMapping(value = "/SearchRaising_disbandment" )
	public ModelAndView SearchRaising_disbandment(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleType = sessionA.getAttribute("roleType").toString();
		String  roleid = sessionA.getAttribute("roleid").toString();
		String  username = sessionA.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("roleType", roleType);
		
		if(msg != null && msg.equals("ok")) {
			Mmap.put("scenario1",scenario);
			Mmap.put("status_sus_no1",status_sus_no);
			Mmap.put("unit_name1",unit_name);
			Mmap.put("comm_depart_date1",comm_depart_date);
			Mmap.put("compltn_arrv_date1",compltn_arrv_date);
			Mmap.put("buttons", getbuttons(status_sus_no,roleType,username));
		}else {
			scenario = "";
			unit_name = "";
			comm_depart_date = "";
			compltn_arrv_date = "";
			Mmap.put("msg", msg);
		}
		
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getType_Of_LetterList", all.getType_Of_LetterList());
		Mmap.put("getType_Of_LetterList_final", all.getType_Of_LetterList_final());
		return new ModelAndView("searchRaising_disbandmentTiles");
	}
	
	@RequestMapping(value = "/getraisingdisbadmentRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getraisingdisbadmentRpt(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
			
			String roleType = sessionA.getAttribute("roleType").toString();
			String  roleid = sessionA.getAttribute("roleid").toString();
		String  username = sessionA.getAttribute("username").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return null;
			}
			if(scenario != null && !scenario.equals("")){
	    		DataSet<Map<String, Object>> dataSet = disbandmentDAO.findraisingdisbadmentWithDatatablesCriterias(criterias,status_sus_no,roleType,scenario,unit_name,comm_depart_date,compltn_arrv_date);
	        	return DatatablesResponse.build(dataSet, criterias);
	    	}else {
	    		return null;
	    	}
	    }
	
	
	public String getbuttons(String status_sus_no,String roleType,String username) {
		String mod ="function modifyDetails(id) {\r\n" + 
				"		document.getElementById(\"mid\").value=id;\r\n" + 
				"		document.getElementById(\"scenarioM\").value = $(\"#scenario\").find('option:selected').attr(\"name\");\r\n" + 
				"    	document.getElementById(\"modifyDetailsForm\").submit();\r\n" + 
				"	}\r\n";  
		
      	String rej ="function rejectDetails(id) {\r\n" + 
      			"		document.getElementById(\"rid\").value=id;\r\n" + 
      			"    	document.getElementById(\"rejectDetailsForm\").submit();\r\n" + 
      			"	}\r\n";  
      	String app ="function approvedDetails(id) {\r\n" + 
      			"		document.getElementById(\"aid\").value=id;\r\n" + 
      			"    	document.getElementById(\"approvedDetailsForm\").submit();\r\n" + 
      			"	}\r\n";  
      	String del ="function deleteDetails(id,sus_noD){\r\n" + 
      			"		document.getElementById(\"did\").value=id;\r\n" + 
      			"		document.getElementById(\"scenarioD\").value = $(\"#scenario\").find('option:selected').attr(\"name\");\r\n" + 
      			"		document.getElementById(\"sus_noD\").value = sus_noD;\r\n" + 
      			"    	document.getElementById(\"deleteDetailsForm\").submit();\r\n" + 
      			"	}\r\n";  
      	String view ="function view(id){\r\n" + 
      			"		document.getElementById(\"vid\").value=id;\r\n" + 
      			"		document.getElementById(\"scenarioV\").value = $(\"#scenario\").find('option:selected').attr(\"name\");\r\n" + 
      			"    	document.getElementById(\"viewDetailsForm\").submit();\r\n" + 
      			"	} \r\n";
		String f="";
		
		if(status_sus_no.equals("Pending")){
      		if(roleType.equals("ALL")){
	      		f += mod;  
	      		f += rej;  
	      		f += app;  
	      		f += del;  
	      		f += view;  
      		}
      		if(roleType.equals("APP")){
      		
      				if(username.equals("app_sd4")){
	      			System.err.println("isnide ussrname "+username);
	      			
		      		f += app;
		      		f += view;  
      				}
		  
      		}
      
      		if(roleType.equals("DEO")) {
      			f += mod; 
      			f += del;  
	      		f += view;
			}
      		if(roleType.equals("VIEW")) {
      			f += view;
			}
	  	}
		
      	if(status_sus_no.equals("Active") || status_sus_no.equals("Inactive")){
			f += view;
		}
      	
  		if(status_sus_no.equals("Reject")){
  			if(roleType.equals("DEO") || roleType.equals("ALL")) {
				f += del;
				f += mod;
				f += view;
			}
  			if(roleType.equals("APP")){
				f += view;
			}
  			if(roleType.equals("VIEW")) {
      			f += view;
			}
	  	}
  		System.err.println("ffffffffffff "+f);
		return f;
	}
		
	    @RequestMapping(value = "/admin/rejectDetailsUrl",method = RequestMethod.POST)
		public ModelAndView rejectDetailsUrl(@ModelAttribute("rid") int rid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
	    	
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = sessionA.getAttribute("username").toString();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    	model.put("msg", disbandmentDAO.rejectSearchDisbandmentDetails(rid,username,date));	
			return new ModelAndView("redirect:SearchRaising_disbandment");
		}
		
	    @RequestMapping(value = "/admin/approvedDetailsUrl",method = RequestMethod.POST)
		public ModelAndView approvedDetailsUrl(@ModelAttribute("aid") int aid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
	    	String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
	    	
	    	String username = sessionA.getAttribute("username").toString();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			String test = "";
					List<String> test_msg=disbandmentDAO.approvedSearchDisbandmentDetails(aid,username,date);
					if(!test_msg.isEmpty())
					{
						test=test_msg.get(0);
					}
			
	    	model.put("msg", test);	
	    	
	  
	    	
			if("Data Approved Successfully" == test) {
				
			
				
	    	List<Map<String, Object>> notit=UOD.getsus_noorbat(aid);
	    	String cmd_sus_no = "";
	    	String corps_sus_no  = ""; 
	    	String div_sus_no = "";
	    	String bde_sus_no = "";
	    	String unit_names = "" ;
	    	String type_of_letter ="" ;
	    	
	    	List<Map<String, Object>> tounitrisin=UOD.gettyp_ltr(aid);
	    
            if(tounitrisin.get(0).get("type_of_letter")!=null) {
            	
            	 type_of_letter = tounitrisin.get(0).get("type_of_letter").toString();	
            	
	    	}
	    	
            	
            
	    	if(notit.get(0).get("cmd")!=null) {
	    		cmd_sus_no = notit.get(0).get("cmd").toString();			
	    	}
	    	if(notit.get(0).get("corps")!=null) {
	    		corps_sus_no = notit.get(0).get("corps").toString();			
	    	}
	    	if(notit.get(0).get("div")!=null) {
	    		div_sus_no = notit.get(0).get("div").toString();			
	    	}
	    	if(notit.get(0).get("bde")!=null) {
	    		bde_sus_no = notit.get(0).get("bde").toString();			
	    	}
			
	    	if(notit.get(0).get("unit_name")!=null) {
	    		unit_names = notit.get(0).get("unit_name").toString();			
	    	}
		
	    	
	    	if (type_of_letter.equals("0")) {
	    
		if (cmd_sus_no != null  && !cmd_sus_no.trim().equals("")) {
				 List<UserLogin> userlist = comm.getUseridlist(cmd_sus_no);
		            String user_id = "";
		         		for (int i = 0; i < userlist.size(); i++) {
		         			if(i==0) {
		         				user_id += 	userlist.get(i).getUserId();
		         			}
		         			
		         			else {
		         				user_id += ","+userlist.get(i).getUserId();
		         			}
	         					
							}
		         		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		         		  String title = "Unit Raised" ;
			              String description = ""+unit_names+" Unit/Reft/Est Has Been Raised" ;
					    Boolean d = notification.sendNotification_tms(title, description,user_id, username,aid);
					    }
			}
			
			if (corps_sus_no != null && !corps_sus_no.trim().equals("") ) {
				 List<UserLogin> userlist = comm.getUseridlist(corps_sus_no);
		            String user_id = "";
		         		for (int i = 0; i < userlist.size(); i++) {
		         			if(i==0) {
		         				user_id += 	userlist.get(i).getUserId();
		         			}
		         			
		         			else {
		         				user_id += ","+userlist.get(i).getUserId();
		         			}
		         					
							}
		         		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
	         		    String title = "Unit Raised" ;
			                String description = ""+unit_names+" Unit/Reft/Est Has Been Raised" ;
					    Boolean d = notification.sendNotification_tms(title, description,user_id, username,aid);
		         		}
			}
			if (div_sus_no != null && !div_sus_no.trim().equals("") ) {
				 List<UserLogin> userlist = comm.getUseridlist(div_sus_no);
		            String user_id = "";
		         		for (int i = 0; i < userlist.size(); i++) {
		         			if(i==0) {
		         				user_id += 	userlist.get(i).getUserId();
	         			}
		         			
		         			else {
		         				user_id += ","+userlist.get(i).getUserId();
		         			}
		         					
							}
		         		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		         		  String title = "Unit Raised" ;
			                String description = ""+unit_names+" Unit/Reft/Est Has Been Raised" ;
					    Boolean d = notification.sendNotification_tms(title, description,user_id, username,aid);
		         		}
			}
			if (bde_sus_no != null && !bde_sus_no.trim().equals("") ) {
				 List<UserLogin> userlist = comm.getUseridlist(bde_sus_no);
		            String user_id = "";
		         		for (int i = 0; i < userlist.size(); i++) {
		         			if(i==0) {
		         				user_id += 	userlist.get(i).getUserId();
		         			}
		         			
		         			else {
		         				user_id += ","+userlist.get(i).getUserId();
		         			}
		         					
							}
		         		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		         		    String title = "Unit Raised" ;
			                String description = ""+unit_names+" Unit/Reft/Est Has Been Raised" ;
			                
			                
					    Boolean d = notification.sendNotification_tms(title, description,user_id, username,aid);
		         		}
		         		}
			}
	    	
	    	if (type_of_letter.equals("9")) {
	    		String user_id = "";
	 		int list = get_Moduleid_cue();
	    		List<String> list1 = notif.getUserIdForNotif(list);
	    		for(int i=0;i<list1.size();i++) {
	    			user_id+=list1.get(i);
	    			if(i<list1.size()-1)
	    				user_id+=",";
	    		}
	    		
	    		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
	    			 String title = "Main Body Move" ;
	    			 String sus_no=test_msg.get(1);
	    			 String description = ""+sus_no+" Location has been changed" ;
	    			 Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
	    			 }
	    		
	    			}
			}
			return new ModelAndView("redirect:SearchRaising_disbandment");
		}
	    
	    
		
	    	public int get_Moduleid_cue()
	    	{
	    	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	    	Transaction tx = sessionhql.beginTransaction();
	    	String hql1 = "   from TB_LDAP_MODULE_MASTER where module_name=:module_name";
	    	Query query1 = sessionhql.createQuery(hql1);
	    	query1.setParameter("module_name","UNIT ENTITLEMENT");
	    	List <TB_LDAP_MODULE_MASTER>list6 = query1.list();

	    	int id=0;
	    	if(!list6.isEmpty())
	    	{
	    	id=list6.get(0).getId();
	    	}

	    	return   id;
	    	}
	

		@RequestMapping(value = "/admin/deletedDetailsUrl",method = RequestMethod.POST)
		public ModelAndView deletedDetailsUrl(@ModelAttribute("did") int did,@ModelAttribute("scenarioD") String scenarioD,@ModelAttribute("sus_noD") String sus_noD,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
	    	String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_noD = roleSusNo;
			}
	    	
	    	String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
	    	model.put("msg", disbandmentDAO.deletedSearchDisbandmentDetails(did,scenarioD,sus_noD));	
			return new ModelAndView("redirect:SearchRaising_disbandment");
		}
	    
	    @RequestMapping(value = "/admin/viewUnitRaisingDetailsUrl")
		public ModelAndView viewUnitRaisingDetailsUrl(@ModelAttribute("vid") int vid,@ModelAttribute("scenarioV") String scenarioV,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA,HttpServletRequest request){
	    	scenarioV = scenarioV.replace("&#40;","(");
	    	scenarioV = scenarioV.replace("&#41;",")");
	    	String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Miso_Orbat_Unt_Dtl unt = unitProfileDAO.getMiso_Orbat_Unt_DtlByid(vid);
			List<Miso_Orbat_Shdul_Detl> shdule = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(vid);
			
			model.put("viewShdulCMD", shdule);	
			model.put("viewUnitCMD", unt);
			model.put("scenarioV", scenarioV);
			
			
			String forcodeOperation = unt.getForm_code_operation();// '${viewUnitCMD.form_code_operation}';
			String forcodeControl = unt.getForm_code_control() ; //'${viewUnitCMD.form_code_control}';
			String forcodeAdmin = unt.getForm_code_admin();//'${viewUnitCMD.form_code_admin}';
			
			String Opcom =  String.valueOf(forcodeOperation.charAt(0));
			String Opcorps = String.valueOf(forcodeOperation.charAt(1)) + String.valueOf(forcodeOperation.charAt(2));
			String Opdiv =  String.valueOf(forcodeOperation.charAt(3)) + String.valueOf(forcodeOperation.charAt(4)) + String.valueOf(forcodeOperation.charAt(5));
			String Opbde = String.valueOf(forcodeOperation.charAt(6)) + String.valueOf(forcodeOperation.charAt(7)) + String.valueOf(forcodeOperation.charAt(8)) + String.valueOf(forcodeOperation.charAt(9));

			String Contcom =String.valueOf(forcodeControl.charAt(0));
			String Contcorps=String.valueOf(forcodeControl.charAt(1)) + String.valueOf(forcodeControl.charAt(2));
			String Contdiv=  String.valueOf(forcodeControl.charAt(3)) + String.valueOf(forcodeControl.charAt(4)) + String.valueOf(forcodeControl.charAt(5));
			String Contbde= String.valueOf(forcodeControl.charAt(6)) + String.valueOf(forcodeControl.charAt(7)) + String.valueOf(forcodeControl.charAt(8)) + String.valueOf(forcodeControl.charAt(9));

			String Admincom = String.valueOf(forcodeAdmin.charAt(0));
			String Admincorps =  String.valueOf(forcodeAdmin.charAt(1)) + String.valueOf(forcodeAdmin.charAt(2));
			String Admindiv =  String.valueOf(forcodeAdmin.charAt(3)) + String.valueOf(forcodeAdmin.charAt(4)) + String.valueOf(forcodeAdmin.charAt(5));
			String Adminbde = String.valueOf(forcodeAdmin.charAt(6)) + String.valueOf(forcodeAdmin.charAt(7)) + String.valueOf(forcodeAdmin.charAt(8)) + String.valueOf(forcodeAdmin.charAt(9));
			
			model.put("op_comd", all.getCommandCorpsDivBdeList("Command",Opcom).get(0));
			model.put("cont_comd", all.getCommandCorpsDivBdeList("Command",Contcom).get(0));
			model.put("adm_comd", all.getCommandCorpsDivBdeList("Command",Admincom).get(0));
		
			if(!Opcorps.equals("00")){
				model.put("op_corps", all.getCommandCorpsDivBdeList("Corps",Opcorps).get(0));
			}
			if(!Contcorps.equals("00")){
				model.put("cont_corps", all.getCommandCorpsDivBdeList("Corps",Contcorps).get(0));
			}
			if(!Admincorps.equals("00")){
				model.put("adm_corps", all.getCommandCorpsDivBdeList("Corps",Admincorps).get(0));
			}
			
			if(!Opdiv.equals("000")){
				model.put("op_div", all.getCommandCorpsDivBdeList("Division",Opdiv).get(0));
			}
			if(!Contdiv.equals("000")){
				model.put("cont_div", all.getCommandCorpsDivBdeList("Division",Contdiv).get(0));
			}
			if(!Admindiv.equals("000")){
				model.put("adm_div", all.getCommandCorpsDivBdeList("Division",Admindiv).get(0));
			}
			
			if(!Opbde.equals("0000")){
				model.put("op_bde", all.getCommandCorpsDivBdeList("Brigade",Opbde).get(0));
			}
			if(!Contbde.equals("0000")){
				model.put("cont_bde", all.getCommandCorpsDivBdeList("Brigade",Contbde).get(0));
			}
			if(!Adminbde.equals("0000")){
				model.put("adm_bde", all.getCommandCorpsDivBdeList("Brigade",Adminbde).get(0));
			}
			
			if(unt.getAddress() != null){
				model.put("address", unt.getAddress());
			}else {
				model.put("address", "NA");
			}
			
			String sus_no = unt.getSus_no();
			String parent_arm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1));
			List<Tb_Miso_Orbat_Code> list = all.getPrantArmList(sessionA);
			for(int i=0;i<list.size();i++) {
				if(parent_arm.equals(list.get(i).getCode())) {
					model.put("parent_arm", list.get(i).getCode_value());
				}
			}
			
			String typeOfArm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1)) + String.valueOf(sus_no.charAt(2)) + String.valueOf(sus_no.charAt(3));
			model.put("type_of_arm", all.getTypeOfArmNameBYCode(typeOfArm));
			if(unt.getCode() != null & !unt.getCode().equals("")) {
				if(all.getLOC_NRS_TypeLOC_TrnType(unt.getCode(),sessionA).size() > 0) { 
					model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(unt.getCode(),sessionA).get(0));
				}
			}
			if(unt.getArm_code() != null & !unt.getArm_code().equals("")) {
				if(all.getArmNameFromArmCodeList(unt.getArm_code()).size() > 0){
					model.put("arm_name", all.getArmNameFromArmCodeList(unt.getArm_code()).get(0));
				}
			}
			
			//Sus No Combdistinct 
			if(unt.getSus_no_for_comb_disint() != null && !unt.getSus_no_for_comb_disint().equals("")){
				model.put("source_sus_no" , unt.getSus_no_for_comb_disint());
				
				if(scenarioV.equals("Re-designation")) {
					if(unt.getStatus_sus_no().equals("Pending")){
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("Active",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}else {
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("INVALID",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}
				}else if(scenarioV.equals("Conversion")) {
					if(unt.getStatus_sus_no().equals("Pending")){
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("Active",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}else {
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("Inactive",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}
				}else if(scenarioV.equals("Re-Structuring")) {
					if(unt.getStatus_sus_no().equals("Pending")){
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("Active",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}else {
						List<String> l = all.getAllUnitNameSus_no_With_scenario_status_sus_no("Inactive",unt.getSus_no_for_comb_disint(),sessionA);
						if(l.size() > 0) {
							model.put("source_unit_name",l.get(0));
						}
					}
				}else {
					if(all.getAllUnitNameFromSusNo_Without_Enc(unt.getSus_no_for_comb_disint(),sessionA).size() != 0) {
						model.put("source_unit_name" , all.getAllUnitNameFromSusNo_Without_Enc(unt.getSus_no_for_comb_disint(),sessionA).get(0));
					}
				}
			}
			
			model.put("type_force", all.getTypeOfUnitFromUnitNoList(unt.getType_force()).get(0).getLabel());
			StateController s = new StateController();
			DistrictController d = new DistrictController();
			
			if(scenarioV.equals("Unit Profile") || scenarioV.equals("Main Body Move(MISO)") || scenarioV.equals("Amendment")) {
				if(unt.getState() != null & !unt.getState().equals("")) {
					if(s.getStateNameFromSTCode(unt.getState()).size() != 0) {
						model.put("state", s.getStateNameFromSTCode(unt.getState()).get(0).getStname());
						model.put("district", d.getDistrictNamesFromDistCode(unt.getDistrict()).get(0).getDistName());
					}
				}
			}
			
			String Auth_Doc = "NO";
			String Goi_Doc = "NO";
			
			String EXTERNAL_FILE_PATH ="";
			if(shdule.get(0).getUpload_auth_letter() != null) {
				EXTERNAL_FILE_PATH = shdule.get(0).getUpload_auth_letter();
				File file = new File(EXTERNAL_FILE_PATH);
				if(!file.exists()){
					Auth_Doc = "NO";
			    }else {
			    	Auth_Doc = "YES";
			    }
			}
			if(shdule.get(0).getUpload_goi_letter() != null) {
				EXTERNAL_FILE_PATH = shdule.get(0).getUpload_goi_letter();
				File file = new File(EXTERNAL_FILE_PATH);
				if(!file.exists()){
					Goi_Doc = "NO";
			    }else {
			    	Goi_Doc = "YES";
			    }
			}
			model.put("Auth_Doc", Auth_Doc);
			model.put("Goi_Doc", Goi_Doc);
			
			
			model.put("msg", msg);
			return new ModelAndView("viewUnitRaisingDetailsTiles");
		}
	    
	   @RequestMapping(value = "/admin/modifyDetailsUrl")
		public ModelAndView modifyDetailsUrl(@ModelAttribute("mid") int mid,@ModelAttribute("scenarioM") String scenarioM,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA,HttpServletRequest request){
		   
		   scenarioM = scenarioM.replace("&#40;","(");
		   scenarioM = scenarioM.replace("&#41;",")");
		   
		   String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
		   	Date date= new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			model.put("date", date1);
		   
	    	Miso_Orbat_Unt_Dtl unt = unitProfileDAO.getMiso_Orbat_Unt_DtlByid(mid);
	    	List<Miso_Orbat_Shdul_Detl> shdul = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(mid);
	    	List<Tbl_CodesForm>  getFormation = unitProfileDAO.getFormationDetailsFromSusNo(unt.getSus_no());
	    	
	    	model.put("getFormation", getFormation.get(0));
	    	
			model.put("viewShdulCMD", shdul);	
			model.put("viewUnitCMD", unt);
			model.put("getPrantArmList", all.getPrantArmList(sessionA));
			model.put("getCommandList", all.getCommandDetailsList());
			model.put("getCorpsList", all.getCorpsDetailsList());
			model.put("getDivList", all.getDivDetailsList());
			model.put("getBdeList", all.getBdeDetailsList());
			model.put("getArmNameList", all.getArmNameList());
			model.put("getTypeOfUnitList", all.getTypeOfUnitList());
			
			if(!shdul.get(0).getType_of_letter().equals("5")){
				if(unt.getSus_no_for_comb_disint() != null && !unt.getSus_no_for_comb_disint().equals("")){
					model.put("source_sus_no" , unt.getSus_no_for_comb_disint());
					if(all.getActiveUnitNameFromSusNo_Without_Enc(unt.getSus_no_for_comb_disint(),sessionA).size() != 0 ){
						model.put("source_unit_name" , all.getActiveUnitNameFromSusNo_Without_Enc(unt.getSus_no_for_comb_disint(),sessionA).get(0));
					}else {
						model.put("source_unit_name" , "Source Unit Currently Not Avtive");
					}
				}
			}	
			
			String sus_no = unt.getSus_no();
			String parent_arm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1));
			List<Tb_Miso_Orbat_Code> list = all.getPrantArmList(sessionA);
			for(int i=0;i<list.size();i++) {
				if(parent_arm.equals(list.get(i).getCode())) {
					model.put("parent_arm", list.get(i).getCode_value());
				}
			}
			String typeOfArm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1)) + String.valueOf(sus_no.charAt(2)) + String.valueOf(sus_no.charAt(3));
			model.put("type_of_arm", all.getTypeOfArmNameBYCode(typeOfArm));
			
			if(unt.getCode() != null) {
				model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(unt.getCode(),sessionA).get(0));
			}
			
			String Auth_Doc = "NO";
			String Goi_Doc = "NO";
			
			String EXTERNAL_FILE_PATH ="";
			if(shdul.get(0).getUpload_auth_letter() != null) {
				EXTERNAL_FILE_PATH = shdul.get(0).getUpload_auth_letter();
				File file = new File(EXTERNAL_FILE_PATH);
				if(!file.exists()){
					Auth_Doc = "NO";
			    }else {
			    	Auth_Doc = "YES";
			    }
			}
			if(shdul.get(0).getUpload_goi_letter() != null) {
				EXTERNAL_FILE_PATH = shdul.get(0).getUpload_goi_letter();
				File file = new File(EXTERNAL_FILE_PATH);
				if(!file.exists()){
					Goi_Doc = "NO";
			    }else {
			    	Goi_Doc = "YES";
			    }
			}
			model.put("Auth_Doc", Auth_Doc);
			model.put("Goi_Doc", Goi_Doc);
			
			model.put("scenarioM", scenarioM);
			model.put("msg", msg);
			return new ModelAndView("modifyUnitDetailsTiles");
		}
	    
	   @RequestMapping(value = "/updateUnitDetailsAction",method = RequestMethod.POST)
	 		public ModelAndView addItemEntryForm(@ModelAttribute("updateUnitDetailsCMD") Miso_Orbat_Unt_Dtl ud,
	 				@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
	 				@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
	 				HttpServletRequest request,ModelMap model,HttpSession sessionA) {
	 	    	
	 	    	String  roleid = sessionA.getAttribute("roleid").toString();
	 			Boolean val = roledao.ScreenRedirect("SearchRaising_disbandment", roleid);	
	 			if(val == false) {
	 				return new ModelAndView("AccessTiles");
	 			}
	 			String roleType = sessionA.getAttribute("roleType").toString();
	 			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
	 				return new ModelAndView("AccessTiles");
	 			}
	 			
	 			int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
	 			String username = sessionA.getAttribute("username").toString();
	 			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	 			
	 			int unit_id = Integer.parseInt(request.getParameter("unit_id"));
	 			int shdul_id = Integer.parseInt(request.getParameter("shdul_id"));
	 			
	 			
	 			String scenarioMScreen = request.getParameter("scenarioMScreen");
	 			
	 			String letter_no = request.getParameter("letter_no");
	 			String goi_letter_no = request.getParameter("goi_letter_no");
	 			if(letter_no.equals(""))
	 			{
	 				model.put("msg", "Please Enter Auth Letter No");
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			if(validation.checkLetter_noLength(letter_no)  == false){
	 		 		model.put("msg",validation.Letter_noMSG);
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			
	 			if(!scenarioMScreen.equalsIgnoreCase("Amendment") && !scenarioMScreen.equalsIgnoreCase("Main Body Move(MISO)") && !scenarioMScreen.equalsIgnoreCase("Extend Raising/Disbandment") && !scenarioMScreen.equalsIgnoreCase("Unit Profile") && !scenarioMScreen.equalsIgnoreCase("Re-Orbatting")){
	 				if(goi_letter_no.equals("")){
	 					model.put("msg", "Please Enter GOI Letter No.");
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				if(validation.checkgoi_Letter_noLength(goi_letter_no)  == false){
	 			 		model.put("msg",validation.goi_Letter_noMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 			}
	 			String unit_name = request.getParameter("unit_name");
	 			if(unit_name.equals(""))
	 			{
	 				model.put("msg", "Please Enter Unit Name");
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			if(validation.checkUnit_nameLength(unit_name)  == false){
	 		 		model.put("msg",validation.unit_nameMSG);
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			
	 			String sus_no = request.getParameter("sus_no");
	 			if(sus_no.equals(""))
	 			{
	 				model.put("msg", "Please Enter SUS No");
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			if(validation.sus_noLength(sus_no)  == false){
	 		 		model.put("msg",validation.sus_noMSG);
	 				return new ModelAndView("redirect:SearchRaising_disbandment");
	 			}
	 			
	 			//Start Save Miso_Orbat_Unt_Dtl 
	 			
	 	
	 			
	 			String level_in_hierarchy =request.getParameter("level_in_hierarchy");
	 			String op =request.getParameter("op");
	 			String cont =request.getParameter("cont");
	 			String adm =request.getParameter("adm");
	 			
	 			Session sessionHQL = null;
	 	    	Transaction tx = null;
	 	    	try{
	 	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	    		tx = sessionHQL.beginTransaction();
	 				if(level_in_hierarchy.equals("Command") || level_in_hierarchy.equals("Corps") || level_in_hierarchy.equals("Division") || level_in_hierarchy.equals("Brigade")) {
	 					ud.setForm_code_operation(op);
	 					ud.setForm_code_control(cont);
	 					ud.setForm_code_admin(adm);
	 				}else {
	 					String op_comd = request.getParameter("op_comd");
	 					if(op_comd.equals(""))
	 					{
	 						model.put("msg", "Please Select Op Comd.");
	 						return new ModelAndView("redirect:SearchRaising_disbandment");
	 					}
	 					String op_corps = request.getParameter("op_corps");
	 					String op_div = request.getParameter("op_div");
	 					String op_bde = request.getParameter("op_bde");
	 					
	 					String form_code_Operation = op_comd;
	 					
	 					if(op_corps.equals("0")) {
	 						form_code_Operation += "00";
	 					}else {
	 						form_code_Operation += String.valueOf(op_corps.charAt(1)) + String.valueOf(op_corps.charAt(2));
	 					}
	 					
	 					if(op_div.equals("0")) {
	 						form_code_Operation += "000";
	 					}else {
	 						form_code_Operation += String.valueOf(op_div.charAt(3)) + String.valueOf(op_div.charAt(4)) + String.valueOf(op_div.charAt(5));
	 					}
	 					
	 					if(op_bde.equals("0")) {
	 						form_code_Operation +="0000";
	 					}else {
	 						form_code_Operation += String.valueOf(op_bde.charAt(6)) + String.valueOf(op_bde.charAt(7)) + String.valueOf(op_bde.charAt(8)) + String.valueOf(op_bde.charAt(9));
	 					}
	 					ud.setForm_code_operation(form_code_Operation);
	 					
	 					String cont_comd = request.getParameter("cont_comd");
	 					if(cont_comd.equals(""))
	 					{
	 						model.put("msg", "Please Select Cont Comd.");
	 						return new ModelAndView("redirect:SearchRaising_disbandment");
	 					}
	 					String cont_corps = request.getParameter("cont_corps");
	 					String cont_div = request.getParameter("cont_div");
	 					String cont_bde = request.getParameter("cont_bde");
	 					
	 					String form_code_cont = cont_comd;
	 					if(cont_corps.equals("0")) {
	 						form_code_cont += "00";
	 					}else {
	 						form_code_cont += String.valueOf(cont_corps.charAt(1)) + String.valueOf(cont_corps.charAt(2));
	 					}
	 					if(cont_div.equals("0")) {
	 						form_code_cont += "000";
	 					}else {
	 						form_code_cont += String.valueOf(cont_div.charAt(3)) + String.valueOf(cont_div.charAt(4)) + String.valueOf(cont_div.charAt(5));
	 					}
	 					
	 					if(cont_bde.equals("0")) {
	 						form_code_cont +="0000";
	 					}else {
	 						form_code_cont += String.valueOf(cont_bde.charAt(6)) + String.valueOf(cont_bde.charAt(7)) + String.valueOf(cont_bde.charAt(8)) + String.valueOf(cont_bde.charAt(9));
	 					}
	 					ud.setForm_code_control(form_code_cont);
	 					
	 					String adm_comd = request.getParameter("adm_comd");
	 					if(adm_comd.equals(""))
	 					{
	 						model.put("msg", "Please Select Adm Comd.");
	 						return new ModelAndView("redirect:SearchRaising_disbandment");
	 					}
	 					String adm_corps = request.getParameter("adm_corps");
	 					String adm_div = request.getParameter("adm_div");
	 					String adm_bde = request.getParameter("adm_bde");
	 					
	 					String form_code_adm = adm_comd;
	 					if(adm_corps.equals("0")) {
	 						form_code_adm += "00";
	 					}else {
	 						form_code_adm += String.valueOf(adm_corps.charAt(1)) + String.valueOf(adm_corps.charAt(2));
	 					}
	 					if(adm_div.equals("0")) {
	 						form_code_adm += "000";
	 					}else {
	 						form_code_adm += String.valueOf(adm_div.charAt(3)) + String.valueOf(adm_div.charAt(4)) + String.valueOf(adm_div.charAt(5));
	 					}
	 					if(adm_bde.equals("0")) {
	 						form_code_adm +="0000";
	 					}else {
	 						form_code_adm += String.valueOf(adm_bde.charAt(6)) + String.valueOf(adm_bde.charAt(7)) + String.valueOf(adm_bde.charAt(8)) + String.valueOf(adm_bde.charAt(9));
	 					}
	 					ud.setForm_code_admin(form_code_adm);
	 				
	 				}
	 				
	 				ud.setModified_on(new Date());
	 				
	 				String sus_No = request.getParameter("sus_no");
	 				String arm_name = request.getParameter("arm_name");
	 				String arm_code = request.getParameter("arm_code");
	 				
	 				if(validation.checkArmCodeLength(arm_code)  == false){
	 	    	 		model.put("msg",validation.arm_codeMSG);
	 	    			return new ModelAndView("redirect:SearchRaising_disbandment");
	 	    		}
	 				if(arm_name.equals(""))
	 				{
	 					model.put("msg", "Please Select Arm Name.");
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				
	 				String code = request.getParameter("code");
	 				String nrs_code = request.getParameter("nrs_code");
	 				String type_of_location = request.getParameter("type_of_location");
	 				String modification = request.getParameter("modification");
	 				String address = request.getParameter("address");
	 				
	 				if(validation.LocCodeLength(code)  == false){
	 			 		model.put("msg",validation.loc_codeMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				if(validation.nrs_codeLength(nrs_code)  == false){
	 			 		model.put("msg",validation.nrs_codeMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				if(validation.TypeLocLength(type_of_location)  == false){
	 			 		model.put("msg",validation.TypelocMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				if(validation.mod_descLength(modification)  == false){
	 			 		model.put("msg",validation.mod_descMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				if(validation.checkAddressLength(address)  == false){
	 			 		model.put("msg",validation.addressMSG);
	 					return new ModelAndView("redirect:SearchRaising_disbandment");
	 				}
	 				
	 				String cont_aname = request.getParameter("cont_aname");
	 	    		if(validation.checkUnit_nameLength(cont_aname)  == false){
	 	    	 		model.put("msg",validation.unit_nameMSG);
	 	    			return new ModelAndView("redirect:SearchRaising_disbandment");
	 	    		}
	 	    		String cont_bname = request.getParameter("cont_bname");
	 	    		if(validation.checkUnit_nameLength(cont_bname)  == false){
	 	    	 		model.put("msg",validation.unit_nameMSG);
	 	    			return new ModelAndView("redirect:SearchRaising_disbandment");
	 	    		}
	 	    		String cont_cname = request.getParameter("cont_cname");
	 	    		if(validation.checkUnit_nameLength(cont_cname)  == false){
	 	    	 		model.put("msg",validation.unit_nameMSG);
	 	    			return new ModelAndView("redirect:SearchRaising_disbandment");
	 	    		}
	 	    		String cont_dname = request.getParameter("cont_dname");
	 	    		if(validation.checkUnit_nameLength(cont_dname)  == false){
	 	    	 		model.put("msg",validation.unit_nameMSG);
	 	    			return new ModelAndView("redirect:SearchRaising_disbandment");
	 	    		}
	 				ud.setLevel_c(cont_cname);
	 				ud.setLevel_d(cont_dname);
	 				ud.setLetter_no(request.getParameter("letter_no"));
	 				ud.setSus_no(sus_No);
	 				ud.setModified_by(username);
	 				ud.setArm_code(arm_code);
	 				ud.setStatus_sus_no("Pending");
	 				ud.setCode_type("Location");
	 				ud.setEntity("UNIT");
	 				ud.setIs_unit_pending("Y");
	 				ud.setSus_no_for_comb_disint(ud.getSus_no_for_comb_disint()); 
	 				ud.setId(unit_id);
	 				ud.setSus_version(Integer.parseInt(request.getParameter("sus_version")));
	 				ud.setCreation_by(request.getParameter("creation_by"));
	 				ud.setVersion_no(1);
	 				try {
	 					ud.setCreation_on(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("creation_on")));
	 				} catch (ParseException e) {
	 					// TODO Auto-generated catch block
	 					
	 				}
	 				
	 				sessionHQL.saveOrUpdate(ud);
	 				sessionHQL.flush();
	 				sessionHQL.clear();
	 				
	 				//End Save Miso_Orbat_Unt_Dtl 
	 				
	 				
	 				//Start Schedule Details Tbl
	 				Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
	 				shdul.setLevel_a(cont_aname);
	 				shdul.setLevel_b(cont_bname);
	 				shdul.setGoi_letter_no(request.getParameter("goi_letter_no"));
	 				shdul.setLetter_no(request.getParameter("letter_no"));
	 				if(!request.getParameter("date_goi_letter").equals("")) {
	 					try {
	 						shdul.setDate_goi_letter(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_goi_letter")));
	 					} catch (ParseException e2) {
	 						// TODO Auto-generated catch block
	 						e2.printStackTrace();
	 					}
	 				}
	 				try {
	 					shdul.setSanction_date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("sanction_date")));
	 				} catch (ParseException e2) {
	 					// TODO Auto-generated catch block
	 					e2.printStackTrace();
	 				} 
	 				shdul.setType_of_letter(request.getParameter("type_of_letter")); 
	 				
	 				if (upload_goi_letter.isEmpty()) {
	 					shdul.setUpload_goi_letter(request.getParameter("upload_goi_letter"));
	 				}
	 				if (upload_auth_letter.isEmpty()) {
	 					shdul.setUpload_auth_letter(request.getParameter("upload_auth_letter"));
	 				}
	 				if(!upload_goi_letter.isEmpty() && !upload_auth_letter.isEmpty()){
	 					String  orbatFilePath = sessionA.getAttribute("orbatFilePath").toString();
	 					misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
	 					
	 					if(validation.checkUpload_goiLetterLength(shdul.getUpload_goi_letter())  == false){
	 				 		model.put("msg",validation.Upload_goiLetterMSG);
	 						return new ModelAndView("redirect:SearchRaising_disbandment");
	 					}
	 					if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
	 				 		model.put("msg",validation.Upload_authLetterMSG);
	 						return new ModelAndView("redirect:SearchRaising_disbandment");
	 					}
	 				}
	 				
	 				shdul.setLetter_id(unit_id);
	 				shdul.setModified_by(username);
	 				try {
	 					shdul.setModified_on(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	 				} catch (ParseException e1) {
	 					// TODO Auto-generated catch block
	 					e1.printStackTrace();
	 				}
	 				shdul.setStatus("0");
	 				shdul.setId(shdul_id);
	 				shdul.setCreated_by(request.getParameter("created_by"));
	 				try {
	 					shdul.setCreated_on(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("created_on")));
	 				} catch (ParseException e) {
	 					
	 				}
	 				
	 				
	 				sessionHQL.saveOrUpdate(shdul);
	 				sessionHQL.flush();
	 				sessionHQL.clear();
	 				
	 				tx.commit();
	 				model.put("msg", "Data Updated Successfully");
	 	    	}catch(RuntimeException e){
	 	    		try{
	 	    			tx.rollback();
	 	    			model.put("msg", "roll back transaction");
	 	    		}catch(RuntimeException rbe){
	 	    			model.put("msg", "Couldn�t roll back transaction " + rbe);
	 	    		}
	 	    		throw e;
	 	    	}finally{
	 	    		if(sessionHQL!=null){
	 	    			sessionHQL.close();
	 	    		}
	 	    	}
	 			return new ModelAndView("redirect:SearchRaising_disbandment");
	 		}
	 	    
	    
}