package com.controller.orbat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.AllMethodsDAO;
import com.dao.orbat.MisoOrbatShdulDetlDAO;
import com.dao.orbat.MisoOrbatShdulDetlDAOImpl;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Amend_Peroid_Of_RaisingController {

	MisoOrbatShdulDetlDAO misoOrbatShdulDetlDAO = new MisoOrbatShdulDetlDAOImpl();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	AllMethodsDAO allMethodsDAO;
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/amend_peroid_of_raising", method = RequestMethod.GET)
	public ModelAndView AmendPeriodOfRaisingForm(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("amend_peroid_of_raising", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getPrantArmList", all.getPrantArmList(session));
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("msg", msg);
		return new ModelAndView("amend_peroid_of_raisingTiles");
	}
	
	@RequestMapping(value = "/AmendPeriodOfRaisingFormAction",method = RequestMethod.POST)	
	public ModelAndView addItemEntryForm1(@ModelAttribute("AmendPeriodOfRaisingFormCMD") Miso_Orbat_Unt_Dtl ud,
		@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
		@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
		HttpServletRequest request,ModelMap model,HttpSession session){
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("amend_peroid_of_raising", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		
		String letter_no = request.getParameter("letter_no");
		if(letter_no.equals(""))
		{
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(validation.checkLetter_noLength(letter_no)  == false){
	 		model.put("msg",validation.Letter_noMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		
		String upload_auth_letterExt = FilenameUtils.getExtension(upload_auth_letter.getOriginalFilename());
		if(!upload_auth_letterExt.toUpperCase().equals("PDF")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		
		if(!misoOrbatShdulDetlDAO.checkPDFFileValidationOrbat_AUTH_LETTER(upload_auth_letter)) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}

		String sanction_date1 = request.getParameter("sanction_date");
		Date sanction_date = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(sanction_date1.equals(""))
			{
				model.put("msg", "Please Enter Letter Date");
				return new ModelAndView("redirect:amend_peroid_of_raising");
			}else {
				sanction_date = formatter1.parse(request.getParameter("sanction_date"));
			}
		} catch (ParseException e) {         
		
		}
		
		String unit_name = request.getParameter("unit_name");
		if(unit_name.equals(""))
		{
			model.put("msg", "Please Enter Unit Name");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(validation.checkUnit_nameLength(unit_name)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		String sus_no = request.getParameter("sus_no");
		if(sus_no.equals(""))
		{
			model.put("msg", "Please Enter Unit Name/SUS No");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(all.getSusNoActiveList(session,sus_no).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(validation.sus_noLength(sus_no)  == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		} 
		String comm_depart_date = request.getParameter("comm_depart_date");
		String compltn_arrv_date = request.getParameter("compltn_arrv_date");
		if(comm_depart_date.equals(""))
		{
			model.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(validation.checkDateLength(comm_depart_date)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(compltn_arrv_date.equals(""))
		{
			model.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		if(validation.checkDateLength(compltn_arrv_date)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		
		String address = request.getParameter("address");
		if(validation.checkAddressLength(address)  == false){
	 		model.put("msg",validation.addressMSG);
			return new ModelAndView("redirect:amend_peroid_of_raising");
		} 
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction txUnt = session1.beginTransaction();
		Query q = session1.createQuery("from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		txUnt.commit();
		session1.close();
		
		if(list.size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:amend_peroid_of_raising");
		}
		
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();	
    		
			ud.setForm_code_admin(list.get(0).getForm_code_admin());
			ud.setForm_code_control(list.get(0).getForm_code_control());
			ud.setForm_code_operation(list.get(0).getForm_code_operation());  
			ud.setAddress(address);
			ud.setRemarks(list.get(0).getRemarks());   
			ud.setCode( list.get(0).getCode()) ;
			ud.setCode_type(list.get(0).getCode_type());	
			ud.setModification(list.get(0).getModification());
			ud.setType_force(list.get(0).getType_force());
			ud.setCt_part_i_ii(list.get(0).getCt_part_i_ii());
			ud.setSus_no_for_comb_disint(ud.getSus_no());
			ud.setArm_code(list.get(0).getArm_code());
			ud.setType_of_location(list.get(0).getType_of_location()); 
			ud.setUnit_army_hq(list.get(0).getUnit_army_hq());
			ud.setLetter_id(list.get(0).getLetter_id());
			ud.setIs_unit_pending(list.get(0).getIs_unit_pending());
			ud.setNrs_code(list.get(0).getNrs_code());
			ud.setCondition(list.get(0).getCondition());
			//ud.setComm_depart_date(new Date());
			//ud.setCompltn_arrv_date(new Date());
			ud.setCreation_on(new Date());
			ud.setCreation_by(username);
			ud.setStatus_sus_no("Pending");
			ud.setCode_type("Location");
			ud.setEntity("UNIT");
			ud.setIs_unit_pending("Y");
			ud.setSus_no_for_comb_disint(sus_no); 
			ud.setLevel_c(list.get(0).getLevel_c());
			ud.setLevel_d(list.get(0).getLevel_d());
			ud.setSus_version(allMethodsDAO.getSusVersion(sus_no));
			
			int uid = (Integer) sessionHQL.save(ud);
			sessionHQL.flush();
			sessionHQL.clear();
				
			Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
			String  orbatFilePath = session.getAttribute("orbatFilePath").toString();
			misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
			
			if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
		 		model.put("msg",validation.Upload_authLetterMSG);
				return new ModelAndView("redirect:amend_peroid_of_raising");
			}
		
			Session session12 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx12 = session12.beginTransaction();
			Query q2 = session12.createQuery("from Miso_Orbat_Shdul_Detl where letter_id=:letter_id");
			q2.setParameter("letter_id", list.get(0).getId());
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Shdul_Detl> listShdule = (List<Miso_Orbat_Shdul_Detl>) q2.list();
			tx12.commit();
			session12.close();
			
			shdul.setLevel_a(listShdule.get(0).getLevel_a());
			shdul.setLevel_b(listShdule.get(0).getLevel_b());
			shdul.setLetter_no(letter_no);
			shdul.setSanction_date(sanction_date);
			shdul.setLetter_id(uid);
			shdul.setCreated_by(username);
			shdul.setCreated_on(new Date());
			shdul.setStatus("0");
			shdul.setType_of_letter("10");
			sessionHQL.save(shdul);
			sessionHQL.flush();
			sessionHQL.clear();
			
			Tbl_CodesForm codeform =new Tbl_CodesForm();
			codeform.setLevel_in_hierarchy("Unit");
			codeform.setCreate_by(username);
			codeform.setCreated_on(new Date());
			codeform.setSus_no(ud.getSus_no());
			codeform.setStatus_record("0");
			codeform.setVersion_on(1);
			sessionHQL.save(codeform);
			sessionHQL.flush();
			sessionHQL.clear();
			
			tx.commit();
			model.put("msg", "Data Saved Successfully");
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    			model.put("msg", "roll back transaction");
    		}catch(RuntimeException rbe){
    			model.put("msg", "Couldn’t roll back transaction " + rbe);
    		}
    		throw e;
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}
		return new ModelAndView("redirect:amend_peroid_of_raising");
	}
}