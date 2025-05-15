package com.controller.orbat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.models.Tb_Miso_Orbat_Sus_Dtl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ReorganisationController {

	MisoOrbatShdulDetlDAO misoOrbatShdulDetlDAO = new MisoOrbatShdulDetlDAOImpl();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	AllMethodsDAO allMethodsDAO;
	
	@RequestMapping(value = "/admin/Reorganisation_Schedule", method = RequestMethod.GET)
	public ModelAndView Reorganisation_Schedule(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Reorganisation_Schedule", roleid);	
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
		Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("msg", msg);
		return new ModelAndView("Reorganisation_ScheduleTiles");
	}
	
	@RequestMapping(value = "/misoReOrganisationDtlAction",method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("misoOrbatUntDtlCMD") Miso_Orbat_Unt_Dtl ud,
			@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
			@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
			HttpServletRequest request,ModelMap model,HttpSession sessionA) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Reorganisation_Schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String sus_No ="";
		String unit_name ="";
		
		String goi_letter_no = request.getParameter("goi_letter_no");
		String letter_no = request.getParameter("letter_no");
		
		if(goi_letter_no.equals(""))
		{
			model.put("msg", "Please Enter GOI Letter No.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(validation.checkgoi_Letter_noLength(goi_letter_no)  == false){
	 		model.put("msg",validation.goi_Letter_noMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(letter_no.equals(""))
		{
			model.put("msg", "Please Enter Auth Letter No.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(validation.checkLetter_noLength(letter_no)  == false){
	 		model.put("msg",validation.Letter_noMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String upload_goi_letterExt = FilenameUtils.getExtension(upload_goi_letter.getOriginalFilename());
		String upload_auth_letterExt = FilenameUtils.getExtension(upload_auth_letter.getOriginalFilename());
		if(!upload_goi_letterExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(!upload_auth_letterExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(!misoOrbatShdulDetlDAO.checkPDFFileValidationOrbat(upload_goi_letter,upload_auth_letter)) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		String date_goi_letter1 = request.getParameter("date_goi_letter");
		Date date_goi_letter = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			if(date_goi_letter1.equals(""))
			{
				model.put("msg", "Please Enter GOI Letter Date.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
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
				model.put("msg", "Please Enter Letter Date.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}else {
				
				sanction_date = formatter1.parse(request.getParameter("sanction_date"));
			}
		} catch (ParseException e) {         
			
		}
		
		String re_org_type = request.getParameter("re_org_type");
		if(re_org_type.equals("0"))
		{
			model.put("msg", "Please Select Re-Org type.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		String count = request.getParameter("count");
		if(re_org_type.equals("2")) {
			String parent_arm = request.getParameter("parent_arm");
			String type_of_arm = request.getParameter("type_of_arm");
			if(parent_arm.equals("0"))
			{
				model.put("msg", "Please Select Parent Arm.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			if(type_of_arm.equals("0"))
			{
				model.put("msg", "Please Select Type of Arm.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}			
			sus_No = misoOrbatShdulDetlDAO.susNoGeneration(parent_arm,type_of_arm);
			
			unit_name = request.getParameter("unit_name");
			if(unit_name.equals(""))
			{
				model.put("msg", "Please Enter Unit name.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}	
			
		}
		else {
			sus_No =  request.getParameter("target_sus_no");
			unit_name = request.getParameter("target_unit_name");
			
			if(sus_No.equals(""))
			{
				model.put("msg", "Please Enter Target SUS No.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			if(validation.sus_noLength(sus_No)  == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			
			if(unit_name.equals(""))
			{
				model.put("msg", "Please Enter Unit name.");
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			if(validation.checkUnit_nameLength(unit_name)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
		}
		
		unit_name = unit_name.replace("&#40;","(");
		unit_name = unit_name.replace("&#41;",")");
		
		if(all.getSusNoActiveList(sessionA,sus_No).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String arm_name = request.getParameter("arm_name");
		if(arm_name.equals(""))
		{
			model.put("msg", "Please Select Arm name.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String arm_code = request.getParameter("arm_code");
		if(validation.checkArmCodeLength(arm_code)  == false){
	 		model.put("msg",validation.arm_codeMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		String code = request.getParameter("code");
		if(validation.LocCodeLength(code)  == false){
	 		model.put("msg",validation.loc_codeMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String type_of_location = request.getParameter("type_of_location");
		if(validation.TypeLocLength(type_of_location)  == false){
	 		model.put("msg",validation.TypelocMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String nrs_code = request.getParameter("nrs_code");
		if(validation.nrs_codeLength(nrs_code)  == false){
	 		model.put("msg",validation.nrs_codeMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String modification = request.getParameter("modification");
		String address = request.getParameter("address");
		if(validation.mod_descLength(modification)  == false){
	 		model.put("msg",validation.mod_descMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		if(validation.checkAddressLength(address)  == false){
	 		model.put("msg",validation.addressMSG);
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		String op_comd = request.getParameter("op_comd");
		String op_corps = request.getParameter("op_corps");
		String op_div = request.getParameter("op_div");
		String op_bde = request.getParameter("op_bde");
		
		if(op_comd.equals(""))
		{
			model.put("msg", "Please Select Op Comd.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		String cont_comd = request.getParameter("cont_comd");
		String cont_corps = request.getParameter("cont_corps");
		String cont_div = request.getParameter("cont_div");
		String cont_bde = request.getParameter("cont_bde");
		
		if(cont_comd.equals(""))
		{
			model.put("msg", "Please Select Cont Comd.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		String adm_comd = request.getParameter("adm_comd");
		String adm_corps = request.getParameter("adm_corps");
		String adm_div = request.getParameter("adm_div");
		String adm_bde = request.getParameter("adm_bde");
		
		if(adm_comd.equals(""))
		{
			model.put("msg", "Please Select Adm Comd.");
			return new ModelAndView("redirect:Reorganisation_Schedule");
		}
		
		

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

		ud.setCreation_by(date);
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
    		
    		ud.setSus_version(allMethodsDAO.getSusVersion(sus_No));
			ud.setLetter_no(letter_no);
			ud.setSus_no(sus_No);
			ud.setUnit_name(unit_name);
			ud.setCreation_by(username);
			ud.setCreation_on(new Date());
			ud.setStatus_sus_no("Pending");
			ud.setCode_type("Location");
			ud.setEntity("UNIT");
			ud.setIs_unit_pending("Y");
			
			int uid = (Integer) sessionHQL.save(ud);
			sessionHQL.flush();
			sessionHQL.clear();
			
			int countlength = Integer.parseInt(count);
			
			for(int j=0; j < countlength; j++) {
				String source_sus_no = request.getParameter("sus_no_"+countlength);
				
				if(validation.sus_noLength(source_sus_no)  == false){
			 		model.put("msg",validation.sus_noMSG);
					return new ModelAndView("redirect:Reorganisation_Schedule");
				}
				
				String hqlUpdate = "update Miso_Orbat_Unt_Dtl c set c.sus_no_for_comb_disint =:sus_no where c.sus_no =:sus_no";
				sessionHQL.createQuery( hqlUpdate ).setString( "sus_no", ud.getSus_no()).setString( "sus_no", source_sus_no ).executeUpdate();
				sessionHQL.flush();
				sessionHQL.clear();
			}
		
			//Schedule Details Tbl
			Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
			
			shdul.setGoi_letter_no(goi_letter_no);
			shdul.setLetter_no(letter_no);
			shdul.setDate_goi_letter(date_goi_letter);
			shdul.setSanction_date(sanction_date);
			shdul.setCreated_by(username);
			shdul.setCreated_on(new Date());
			shdul.setStatus("0");
			String  orbatFilePath = sessionA.getAttribute("orbatFilePath").toString();
			misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
			
			if(validation.checkUpload_goiLetterLength(shdul.getUpload_goi_letter())  == false){
		 		model.put("msg",validation.Upload_goiLetterMSG);
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
		 		model.put("msg",validation.Upload_authLetterMSG);
				return new ModelAndView("redirect:Reorganisation_Schedule");
			}
			
			shdul.setLetter_id(uid);
			shdul.setType_of_letter("3");
			
			sessionHQL.save(shdul);
			sessionHQL.flush();
			sessionHQL.clear();
		
			if(re_org_type.equals("2")) {
				
				//Sus Details Tbl
				Tb_Miso_Orbat_Sus_Dtl sus_dtl = new Tb_Miso_Orbat_Sus_Dtl();
				sus_dtl.setSus_no(sus_No);
				sus_dtl.setCreated_by(username);
				sus_dtl.setCreated_on(new Date());
				sus_dtl.setVersion_no(1);
				sessionHQL.save(sus_dtl);
				sessionHQL.flush();
				sessionHQL.clear();
				
				//CodeForm Details Tbl
				String level_in_hierarchy = request.getParameter("level_in_hierarchy"); 
				if(level_in_hierarchy.equals("0"))
				{
					model.put("msg", "Please Select Entity.");
					return new ModelAndView("redirect:Reorganisation_Schedule");
				}
	
				Tbl_CodesForm codeform =new Tbl_CodesForm();
				codeform.setLevel_in_hierarchy(level_in_hierarchy);
				codeform.setCreate_by(username);
				codeform.setCreated_on(new Date());
				codeform.setSus_no(sus_No);
				codeform.setStatus_record("0");
				codeform.setVersion_on(1);
				sessionHQL.save(codeform);
				sessionHQL.flush();
				sessionHQL.clear();
			}
			tx.commit();
			model.put("msg", "Action Completed Successfully");
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
		return new ModelAndView("redirect:Reorganisation_Schedule");
	}	
}