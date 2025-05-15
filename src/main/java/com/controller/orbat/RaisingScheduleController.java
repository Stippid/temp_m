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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.MisoOrbatShdulDetlDAO;
import com.dao.orbat.MisoOrbatShdulDetlDAOImpl;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tb_Miso_Orbat_Sus_Dtl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class RaisingScheduleController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	MisoOrbatShdulDetlDAO misoOrbatShdulDetlDAO = new MisoOrbatShdulDetlDAOImpl();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	
	// CREATE NEW RAISING DETAILS
	@RequestMapping(value = "/admin/Rising_Schedule", method = RequestMethod.GET)
	public ModelAndView Rising_Schedule(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Rising_Schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getPrantArmList", all.getPrantArmList(session));
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("Rising_ScheduleTiles" ,"misoOrbatUntDtlCMD",new Miso_Orbat_Unt_Dtl());
	}
	
	static String str(int i) {
	    return i < 0 ? "" : str((i / 26) - 1) + (char)(65 + i % 26);
	}
	
	@RequestMapping(value = "/misoOrbatUntDtlAction",method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("misoOrbatUntDtlCMD") Miso_Orbat_Unt_Dtl ud,
		@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
		@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
		HttpServletRequest request,ModelMap model,HttpSession session) {
		
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Rising_Schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		//Validations
		
		String goi_letter_no = request.getParameter("goi_letter_no");
		String letter_no = request.getParameter("letter_no");
		
		if(goi_letter_no.equals(""))
		{
			model.put("msg", "Please Enter GOI Letter No");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(validation.checkgoi_Letter_noLength(goi_letter_no)  == false){
	 		model.put("msg",validation.goi_Letter_noMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(letter_no.equals(""))
		{
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(validation.checkLetter_noLength(letter_no)  == false){
	 		model.put("msg",validation.Letter_noMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String upload_goi_letterExt = FilenameUtils.getExtension(upload_goi_letter.getOriginalFilename());
		String upload_auth_letterExt = FilenameUtils.getExtension(upload_auth_letter.getOriginalFilename());
		if(!upload_goi_letterExt.toUpperCase().equals("PDF")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(!upload_auth_letterExt.toUpperCase().equals("PDF")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Rising_Schedule");
		}
 
		if(!misoOrbatShdulDetlDAO.checkPDFFileValidationOrbat(upload_goi_letter,upload_auth_letter)) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		
		String date_goi_letter1 = request.getParameter("date_goi_letter");
		Date date_goi_letter = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			if(date_goi_letter1.equals(""))
			{
				model.put("msg", "Please Enter GOI Letter Date");
				return new ModelAndView("redirect:Rising_Schedule");
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
				return new ModelAndView("redirect:Rising_Schedule");
			}else {
				
				sanction_date = formatter1.parse(request.getParameter("sanction_date"));
			}
		} catch (ParseException e) {         
			
		}
		String level_in_hierarchy = request.getParameter("level_in_hierarchy"); 
		if(level_in_hierarchy.equals("0"))
		{
			model.put("msg", "Please Select Entity");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String u_name = request.getParameter("unit_name");
		if(u_name.equals(""))
		{
			model.put("msg", "Please Enter Unit Name");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		u_name = u_name.replace("&#40;","(");
		u_name = u_name.replace("&#41;",")");
		
		Tbl_CodesForm codeform = new Tbl_CodesForm();
		int ver = 0;
		String comndver = "";
		
		if(checkifExistUnitName(u_name,"",session) == true) {
			model.put("msg", "Already Exists Unit Name");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		if(validation.checkUnit_nameLength(u_name)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		if(level_in_hierarchy.equals("Command")) {
			//Command Serial No
			Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = sessionVersion.beginTransaction();
			Query q1 = sessionVersion.createQuery("select formation_code from Tbl_CodesForm where level_in_hierarchy =:level_in_hierarchy order by formation_code desc").setMaxResults(0);
			q1.setParameter("level_in_hierarchy", level_in_hierarchy);
			String list1 = (String) q1.list().get(0);
			tx1.commit();
			sessionVersion.close();
			if(list1 != null) {
				for (int i = 0; i < 25; ++i) {
					 if(str(i).equals(list1)) {
						 comndver = str(i+1).toUpperCase();
					 }
				 }
			}else {
				comndver =  "A";
			}
		}
		if(level_in_hierarchy.equals("Corps")) {
			//Corps Serial No
			Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = sessionVersion.beginTransaction();
			Query q1 = sessionVersion.createQuery("select max(formation_code) from Tbl_CodesForm where level_in_hierarchy =:level_in_hierarchy and formation_code not in ('A0','B0','C0')");
			q1.setParameter("level_in_hierarchy", level_in_hierarchy);
			String list1 = (String) q1.list().get(0);
			tx1.commit();
			sessionVersion.close();
			if(list1 != null) {
				 ver = Integer.parseInt(list1) + 1;
			}else {
				ver =  1;
			}
		}
		
		if(level_in_hierarchy.equals("Division")) {
			//Division Serial No
			Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = sessionVersion.beginTransaction();
			Query q1 = sessionVersion.createQuery("select max(formation_code) from Tbl_CodesForm where level_in_hierarchy=:level_in_hierarchy and formation_code like '1%'");
			q1.setParameter("level_in_hierarchy", level_in_hierarchy);
			String list1 = (String) q1.list().get(0);
			tx1.commit();
			sessionVersion.close();
			if(list1 != null) {
				 ver = Integer.parseInt(list1) + 1;
			}else {
				ver =  100;
			}
		}
		
		if(level_in_hierarchy.equals("Brigade")) {
			//Brigade Serial No
			Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = sessionVersion.beginTransaction();
			Query q1 = sessionVersion.createQuery("select max(formation_code) from Tbl_CodesForm where level_in_hierarchy=:level_in_hierarchy and formation_code like '1%'");
			q1.setParameter("level_in_hierarchy", level_in_hierarchy);
			String list1 = (String) q1.list().get(0);
			tx1.commit();
			sessionVersion.close();
			if(list1 != null) {
				 ver = Integer.parseInt(list1) + 1;
			}else {
				ver =  1000;
			}
		}
		// New Code command,corps Div Breged
		String op_comd = request.getParameter("op_comd");
		String op_corps = request.getParameter("op_corps");
		String op_div = request.getParameter("op_div");
		String op_bde = request.getParameter("op_bde");
		String form_code_Operation = "";
		
		if(op_comd.equals("") & !level_in_hierarchy.equals("Command"))
		{
			model.put("msg", "Please Select Op Comd");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String cont_comd = request.getParameter("cont_comd");
		String cont_corps = request.getParameter("cont_corps");
		String cont_div = request.getParameter("cont_div");
		String cont_bde = request.getParameter("cont_bde");
		String form_code_cont = "";
		
				
		if(cont_comd.equals("") & !level_in_hierarchy.equals("Command"))
		{
			model.put("msg", "Please Select Cont Comd");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String adm_comd = request.getParameter("adm_comd");
		String adm_corps = request.getParameter("adm_corps");
		String adm_div = request.getParameter("adm_div");
		String adm_bde = request.getParameter("adm_bde");
		String form_code_adm = "";
		
		if(adm_comd.equals("") & !level_in_hierarchy.equals("Command"))
		{
			model.put("msg", "Please Select Adm Comd");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(level_in_hierarchy.equals("Command")) {
			form_code_Operation = comndver+"000000000";
			form_code_cont = comndver+"000000000";
			form_code_adm =  comndver+ "000000000";
			codeform.setFormation_code(comndver);
		}
		else if(level_in_hierarchy.equals("Corps")) 
		{
			form_code_Operation = op_comd + String.format("%02d", ver) + "0000000";
			form_code_cont = cont_comd + String.format("%02d", ver) + "0000000";
			form_code_adm = adm_comd + String.format("%02d", ver) + "0000000";
			codeform.setFormation_code(String.format("%02d", ver));	
		}
		else if(level_in_hierarchy.equals("Division")) 
		{
			form_code_Operation = op_comd;
			if(op_corps.equals("0")) {
				form_code_Operation += "00"  + String.format("%03d", ver) + "0000";
			}else {
				form_code_Operation += String.valueOf(op_corps.charAt(1)) + String.valueOf(op_corps.charAt(2)) + String.format("%03d", ver) + "0000";
			}
			
			form_code_cont = cont_comd;
			codeform.setParent_code(cont_comd);
			if(cont_corps.equals("0")) {
				form_code_cont += "00" + String.format("%03d", ver) + "0000";
			}else {
				codeform.setParent_code(String.valueOf(cont_corps.charAt(1)) + String.valueOf(cont_corps.charAt(2)));
				form_code_cont += String.valueOf(cont_corps.charAt(1)) + String.valueOf(cont_corps.charAt(2)) + String.format("%03d", ver) + "0000";
			}
			
			form_code_adm = adm_comd;
			if(adm_corps.equals("0")) {
				form_code_adm += "00"  + String.format("%03d", ver) + "0000";
			}else {
				form_code_adm += String.valueOf(adm_corps.charAt(1)) + String.valueOf(adm_corps.charAt(2))  + String.format("%03d", ver) + "0000";
			}
			codeform.setFormation_code(String.format("%03d", ver));
		}
		else if(level_in_hierarchy.equals("Brigade")) {
			
			form_code_Operation = op_comd;
			if(op_corps.equals("0")) {
				form_code_Operation += "00" ;
			}else {
				form_code_Operation += String.valueOf(op_corps.charAt(1)) + String.valueOf(op_corps.charAt(2)); 
			}
			
			if(op_div.equals("0")) {
				form_code_Operation += "000" + String.format("%04d", ver);
			}else {
				form_code_Operation += String.valueOf(op_div.charAt(3)) + String.valueOf(op_div.charAt(4)) + String.valueOf(op_div.charAt(5)) + String.format("%04d", ver);
			}
			
			form_code_cont = cont_comd;
			codeform.setParent_code(cont_comd);
			if(cont_corps.equals("0")) {
				form_code_cont += "00" ;
			}else {
				codeform.setParent_code(String.valueOf(cont_corps.charAt(1)) + String.valueOf(cont_corps.charAt(2)));
				form_code_cont += String.valueOf(cont_corps.charAt(1)) + String.valueOf(cont_corps.charAt(2)) ;
			}
			
			if(cont_div.equals("0")) {
				form_code_cont += "000"  + String.format("%04d", ver);
			}else {
				codeform.setParent_code(String.valueOf(cont_div.charAt(3)) + String.valueOf(cont_div.charAt(4)) + String.valueOf(cont_div.charAt(5)));
				form_code_cont += String.valueOf(cont_div.charAt(3)) + String.valueOf(cont_div.charAt(4)) + String.valueOf(cont_div.charAt(5))  + String.format("%04d", ver);
			}
			
			form_code_adm = adm_comd;
			if(adm_corps.equals("0")) {
				form_code_adm += "00" ;
			}else {
				form_code_adm += String.valueOf(adm_corps.charAt(1)) + String.valueOf(adm_corps.charAt(2)) ;
			}
			if(adm_div.equals("0")) {
				form_code_adm += "000"  + String.format("%04d", ver);
			}else {
				form_code_adm += String.valueOf(adm_div.charAt(3)) + String.valueOf(adm_div.charAt(4)) + String.valueOf(adm_div.charAt(5))  + String.format("%04d", ver);
			}
			codeform.setFormation_code(String.format("%04d", ver));
		}
		else if(level_in_hierarchy.equals("Unit")) {
			// OP Cont 
			form_code_Operation = op_comd;
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
			
			// Unit Cont 
			form_code_cont = cont_comd;
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
			codeform.setParent_code(form_code_cont);
			
			// Unit ADM 
			form_code_adm = adm_comd;
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
		} 
		
		// New Code command,corps Div Breged
		String parent_arm = request.getParameter("parent_arm");
		String type_of_arm = request.getParameter("type_of_arm");
		if(parent_arm.equals("0"))
		{
			model.put("msg", "Please Select Parent Arm");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(type_of_arm.equals("0"))
		{
			model.put("msg", "Please Select Type of Arm");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		
		String sus_No = misoOrbatShdulDetlDAO.susNoGeneration(parent_arm,type_of_arm);
		String arm_name = request.getParameter("arm_name");
		
		if(sus_No.equals(""))
		{
			model.put("msg", "Please Enter SUS No");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		if(validation.sus_noLength(sus_No)  == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		if(arm_name.equals("0"))
		{
			model.put("msg", "Please Select Arm Name");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String arm_code = request.getParameter("arm_code");
		if(validation.checkArmCodeLength(arm_code)  == false){
	 		model.put("msg",validation.arm_codeMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String type_force = request.getParameter("type_force");
		if(type_force.equals(""))
		{
			model.put("msg", "Please Select Type Of Force");
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String code = request.getParameter("code");
		if(validation.LocCodeLength(code)  == false){
	 		model.put("msg",validation.loc_codeMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String nrs_code = request.getParameter("nrs_code");
		String type_of_location = request.getParameter("type_of_location");
		String modification = request.getParameter("modification");
		String address = request.getParameter("address");
		if(validation.nrs_codeLength(nrs_code)  == false){
	 		model.put("msg",validation.nrs_codeMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(validation.TypeLocLength(type_of_location)  == false){
	 		model.put("msg",validation.TypelocMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(validation.mod_descLength(modification)  == false){
	 		model.put("msg",validation.mod_descMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		if(validation.checkAddressLength(address)  == false){
	 		model.put("msg",validation.addressMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		
		String cont_aname = request.getParameter("cont_aname");
		if(validation.checkUnit_nameLength(cont_aname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String cont_bname = request.getParameter("cont_bname");
		if(validation.checkUnit_nameLength(cont_bname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String cont_cname = request.getParameter("cont_cname");
		if(validation.checkUnit_nameLength(cont_cname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}
		String cont_dname = request.getParameter("cont_dname");
		if(validation.checkUnit_nameLength(cont_dname)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:Rising_Schedule");
		}

		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
		
    		//Start Save Miso_Orbat_Unt_Dtl
			ud.setLevel_c(cont_cname);
			ud.setLevel_d(cont_dname);
			ud.setLetter_no(letter_no);
			ud.setForm_code_operation(form_code_Operation);
			ud.setForm_code_control(form_code_cont);
			ud.setForm_code_admin(form_code_adm);
			ud.setCreation_on(new Date());
			ud.setSus_no(sus_No);
			ud.setCreation_by(username);
			ud.setStatus_sus_no("Pending");
			ud.setCode_type("Location");
			ud.setEntity("UNIT");
			ud.setSus_version(1);
			ud.setIs_unit_pending("Y");
			int uid = (Integer) sessionHQL.save(ud);
			sessionHQL.flush();
			sessionHQL.clear();
			
			//End Save Miso_Orbat_Unt_Dtl 
		
		
			//Save Start Tb_Miso_Orbat_Sus_Dtl
			Tb_Miso_Orbat_Sus_Dtl sus_dtl = new Tb_Miso_Orbat_Sus_Dtl();
			
			sus_dtl.setSus_no(sus_No);
			sus_dtl.setCreated_by(username);
			sus_dtl.setCreated_on(new Date());
			sus_dtl.setVersion_no(1);
			sessionHQL.save(sus_dtl);
			sessionHQL.flush();
			sessionHQL.clear();
			//Save End Tb_Miso_Orbat_Sus_Dtl
		
			//Start Schedule Details Tbl
			Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
			shdul.setLevel_a(cont_aname);
			shdul.setLevel_b(cont_bname);
			shdul.setGoi_letter_no(goi_letter_no);
			shdul.setLetter_no(letter_no);
			shdul.setDate_goi_letter(date_goi_letter);
			shdul.setSanction_date(sanction_date);	
			shdul.setCreated_by(username);
			shdul.setCreated_on(new Date());
			shdul.setStatus("0");
			shdul.setType_of_letter("0");
			String  orbatFilePath = session.getAttribute("orbatFilePath").toString();
			
			
			misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
			
			if(validation.checkUpload_goiLetterLength(shdul.getUpload_goi_letter())  == false){
		 		model.put("msg",validation.Upload_goiLetterMSG);
				return new ModelAndView("redirect:Rising_Schedule");
			}
			if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
		 		model.put("msg",validation.Upload_authLetterMSG);
				return new ModelAndView("redirect:Rising_Schedule");
			}
			
			shdul.setLetter_id(uid);
			sessionHQL.save(shdul);
			sessionHQL.flush();
			sessionHQL.clear();
			//End Schedule Details Tbl
		
			//Start CodeForm Details Tbl
			codeform.setLevel_in_hierarchy(level_in_hierarchy);
			codeform.setCreate_by(username);
			codeform.setCreated_on(new Date());
			codeform.setSus_no(sus_No);
			codeform.setStatus_record("0");
			codeform.setVersion_on(1);
			sessionHQL.save(codeform);
			sessionHQL.flush();
			sessionHQL.clear();
			//End CodeForm Details Tbl
			
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
		return new ModelAndView("redirect:Rising_Schedule");
	}
	
	// Check if Exist Unitname
	@RequestMapping(value = "/checkifExistUnitName",method = RequestMethod.POST)
	public @ResponseBody Boolean checkifExistUnitName(String unit_name,String sus_no,HttpSession sessionUserId) {
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		unit_name = unit_name.replace("&#40;","(");
		unit_name = unit_name.replace("&#41;",")");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query= null;
		if(sus_no.equals("")) {
			query = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name)=:unitname");
		}else {
			query = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name)=:unitname and upper(sus_no) !=:sus_no");
			query.setParameter("sus_no", sus_no.toUpperCase());
		}
		query.setParameter("unitname",unit_name.toUpperCase());
		
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> users = (List<Miso_Orbat_Unt_Dtl>) query.list();
		session.close();
		if(users.size()>0){
			return true;
		}else {
			return false;
		}
	}
}