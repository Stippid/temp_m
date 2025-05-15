package com.controller.orbat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.models.Tb_Miso_Orbat_Sus_Dtl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ConversionController {

	MisoOrbatShdulDetlDAO misoOrbatShdulDetlDAO = new MisoOrbatShdulDetlDAOImpl();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	AllMethodsDAO allMethodsDAO;
	
	
	@RequestMapping(value = "/admin/conversion", method = RequestMethod.GET)
	public ModelAndView Conversion(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("conversion", roleid);	
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
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("msg", msg);
		return new ModelAndView("conversionTiles");
	}
	
	//Conversion Insert
		@RequestMapping(value = "/misoOrbatConversionAction",method = RequestMethod.POST)
		public ModelAndView misoOrbatConversionAction(@ModelAttribute("misoOrbatUntDtlCMD") Miso_Orbat_Unt_Dtl ud,
				@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
				@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("conversion", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			String username = session.getAttribute("username").toString();
			
			String goi_letter_no = request.getParameter("goi_letter_no");
			String letter_no = request.getParameter("letter_no");
			if(goi_letter_no.equals(""))
			{
				model.put("msg", "Please Enter GoI Letter No");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkgoi_Letter_noLength(goi_letter_no)  == false){
		 		model.put("msg",validation.goi_Letter_noMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(letter_no.equals(""))
			{
				model.put("msg", "Please Enter Auth Letter No");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkLetter_noLength(letter_no)  == false){
		 		model.put("msg",validation.Letter_noMSG);
				return new ModelAndView("redirect:conversion");
			}
			
			String date_goi_letter1 = request.getParameter("date_goi_letter");
			Date date_goi_letter = null;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(date_goi_letter1.equals(""))
				{
					model.put("msg", "Please Enter GoI Letter Date");
					return new ModelAndView("redirect:conversion");
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
					return new ModelAndView("redirect:conversion");
				}else {
					sanction_date = formatter1.parse(request.getParameter("sanction_date"));
				}
			} catch (ParseException e) {         
				
			}
			String upload_goi_letterExt = FilenameUtils.getExtension(upload_goi_letter.getOriginalFilename());
			String upload_auth_letterExt = FilenameUtils.getExtension(upload_auth_letter.getOriginalFilename());
			if(!upload_goi_letterExt.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:conversion");
			}
			if(!upload_auth_letterExt.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:conversion");
			}
			if(!misoOrbatShdulDetlDAO.checkPDFFileValidationOrbat(upload_goi_letter,upload_auth_letter)) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:conversion");
			}
			String type_of_letter = request.getParameter("type_of_letter");                         
			if(type_of_letter.equals("0"))
			{
				model.put("msg", "Please Select Type of Letter");
				return new ModelAndView("redirect:conversion");
			}
			String u_name = request.getParameter("unit_name");
			u_name = u_name.replace("&#40;","(");
			u_name = u_name.replace("&#41;",")");
			if(u_name.equals(""))
			{
				model.put("msg", "Please Enter Unit Name");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkUnit_nameLength(u_name)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			String source_unit_name =request.getParameter("source_unit_name");
			if(source_unit_name.equals(""))
			{
				model.put("msg", "Please Enter Source Unit Name");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkUnit_nameLength(source_unit_name)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			String source_sus_no =request.getParameter("source_sus_no");
			if(source_sus_no.equals(""))
			{
				model.put("msg", "Please Enter Source SUS No");
				return new ModelAndView("redirect:conversion");
			}
			if(all.getSusNoActiveList(session,source_sus_no).size() == 0) {
				model.put("msg", "Please Enter Active Source Sus No");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.sus_noLength(source_sus_no)  == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:conversion");
			}
			
			
			
			String arm_name = request.getParameter("arm_name");
			
			if(arm_name.equals("0"))
			{
				model.put("msg", "Please Select Arm Name");
				return new ModelAndView("redirect:conversion");
			}
			
			String arm_code = request.getParameter("arm_code");
			String type_force = request.getParameter("type_force");
			String code = request.getParameter("code");
			String nrs_code = request.getParameter("nrs_code");
			String type_of_location = request.getParameter("type_of_location");
			String modification = request.getParameter("modification");
			String address = request.getParameter("address");
			if(arm_code.equals("0"))
			{
				model.put("msg", "Please Select Arm Code");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkArmCodeLength(arm_code)  == false){
		 		model.put("msg",validation.arm_codeMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(type_force.equals(""))
			{
				model.put("msg", "Please Select Type Force");
				return new ModelAndView("redirect:conversion");
			}
			if(validation.LocCodeLength(code)  == false){
		 		model.put("msg",validation.loc_codeMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(validation.nrs_codeLength(nrs_code)  == false){
		 		model.put("msg",validation.nrs_codeMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(validation.TypeLocLength(type_of_location)  == false){
		 		model.put("msg",validation.TypelocMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(validation.mod_descLength(modification)  == false){
		 		model.put("msg",validation.mod_descMSG);
				return new ModelAndView("redirect:conversion");
			}
			if(validation.checkAddressLength(address)  == false){
		 		model.put("msg",validation.addressMSG);
				return new ModelAndView("redirect:conversion");
			}
			
			RaisingScheduleController rController = new RaisingScheduleController();
			if(type_of_letter.equals("1") && rController.checkifExistUnitName(ud.getUnit_name(),ud.getSus_no(),session) == true) {
				model.put("msg", "Already exists Unit Name!");
				return new ModelAndView("redirect:conversion");
			}
			
			
			Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = sessionVersion.beginTransaction();
			Query q1 = sessionVersion.createQuery("select count(sus_no) from Miso_Orbat_Unt_Dtl where sus_no=:target_sus_no");
			q1.setParameter("target_sus_no", ud.getSus_no());
			Long list1 = (Long) q1.list().get(0);
			int ver;
			tx1.commit();
			sessionVersion.close();
			if(list1 != null) {
				 ver = (int) (list1 + 1);
			}else {
				ver = (int) 1;
			}
			
		
			String op_comd = request.getParameter("op_comd");
			String op_corps = request.getParameter("op_corps");
			String op_div = request.getParameter("op_div");
			String op_bde = request.getParameter("op_bde");
			if(op_comd.equals(""))
			{
				model.put("msg", "Please Select Op Comd");
				return new ModelAndView("redirect:conversion");
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
				form_code_Operation += String.valueOf(op_bde.charAt(6)) + String.valueOf(op_bde.charAt(7)) + String.valueOf(op_bde.charAt(8))+ String.valueOf(op_bde.charAt(9));
			}
			
			
			String cont_comd = request.getParameter("cont_comd");
			String cont_corps = request.getParameter("cont_corps");
			String cont_div = request.getParameter("cont_div");
			String cont_bde = request.getParameter("cont_bde");
			
			if(cont_comd.equals(""))
			{
				model.put("msg", "Please Select Cont Comd");
				return new ModelAndView("redirect:conversion");
			}
			
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
				form_code_cont += String.valueOf(cont_bde.charAt(6)) + String.valueOf(cont_bde.charAt(7)) + String.valueOf(cont_bde.charAt(8))+ String.valueOf(cont_bde.charAt(9));
			}
			
			String adm_comd = request.getParameter("adm_comd");
			String adm_corps = request.getParameter("adm_corps");
			String adm_div = request.getParameter("adm_div");
			String adm_bde = request.getParameter("adm_bde");
			
			if(adm_comd.equals(""))
			{
				model.put("msg", "Please Select Adm Comd");
				return new ModelAndView("redirect:conversion");
			}
			
			String comm_depart_date1 = request.getParameter("comm_depart_date");
			Date comm_depart_date = null;
			DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(comm_depart_date1.equals(""))
				{
					model.put("msg", "Please Enter Comm Depart Date");
					return new ModelAndView("redirect:conversion");
				}else {
					comm_depart_date = formatter2.parse(request.getParameter("comm_depart_date"));
				}
			} catch (ParseException e) {
			
			}
			
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
			
			String cont_aname = request.getParameter("cont_aname");
			if(validation.checkUnit_nameLength(cont_aname)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			String cont_bname = request.getParameter("cont_bname");
			if(validation.checkUnit_nameLength(cont_bname)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			String cont_cname = request.getParameter("cont_cname");
			if(validation.checkUnit_nameLength(cont_cname)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			String cont_dname = request.getParameter("cont_dname");
			if(validation.checkUnit_nameLength(cont_dname)  == false){
		 		model.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:conversion");
			}
			
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
			
				if(type_of_letter.equals("11"))
				{
					
					String parent_arm = request.getParameter("parent_arm");
					String type_of_arm= request.getParameter("type_of_arm");
					String sus_No = misoOrbatShdulDetlDAO.susNoGeneration(parent_arm,type_of_arm);
					ud.setSus_no(sus_No);
					ud.setCreation_on(new Date());
					
					Tb_Miso_Orbat_Sus_Dtl sus_dtl = new Tb_Miso_Orbat_Sus_Dtl();
					sus_dtl.setSus_no(sus_No);
					sus_dtl.setCreated_by(username);
					sus_dtl.setCreated_on(new Date());
					sus_dtl.setVersion_no(allMethodsDAO.getSusVersion(ud.getSus_no()));
					sessionHQL.save(sus_dtl);
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
				}else {
					String sus_no =request.getParameter("sus_no");
					if(sus_no.equals(""))
					{
						model.put("msg", "Please Enter SUS No");
						return new ModelAndView("redirect:conversion");
					}
					if(validation.sus_noLength(sus_no)  == false){
				 		model.put("msg",validation.sus_noMSG);
						return new ModelAndView("redirect:conversion");
					}
				}
			
			
				ud.setForm_code_operation(form_code_Operation);
				ud.setForm_code_control(form_code_cont);
				ud.setForm_code_admin(form_code_adm);
				ud.setLevel_c(cont_cname);
				ud.setLevel_d(cont_dname);
				ud.setCreation_on(new Date());
				ud.setLetter_no(letter_no);
				ud.setCreation_by(username);
				ud.setStatus_sus_no("Pending");
				ud.setCode_type("Location");
				ud.setEntity("UNIT");
				ud.setSus_version(allMethodsDAO.getSusVersion(ud.getSus_no()));
				ud.setIs_unit_pending("Y");
				ud.setNrs_code(nrs_code);
				ud.setSus_no_for_comb_disint(source_sus_no); 
				ud.setComm_depart_date(comm_depart_date);
				ud.setArm_code(arm_code);
				ud.setType_force(type_force);
				int uid = (Integer) sessionHQL.save(ud);
				sessionHQL.flush();
				sessionHQL.clear();
				
			
				Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
				String  orbatFilePath = session.getAttribute("orbatFilePath").toString();
				misoOrbatShdulDetlDAO.saveMiso_Orbat_Shdul_Detl(upload_goi_letter,upload_auth_letter,userid,shdul,orbatFilePath);
				
				if(validation.checkUpload_goiLetterLength(shdul.getUpload_goi_letter())  == false){
			 		model.put("msg",validation.Upload_goiLetterMSG);
					return new ModelAndView("redirect:conversion");
				}
				if(validation.checkUpload_authLetterLength(shdul.getUpload_auth_letter())  == false){
			 		model.put("msg",validation.Upload_authLetterMSG);
					return new ModelAndView("redirect:conversion");
				}
				
				shdul.setGoi_letter_no(goi_letter_no);
				shdul.setLetter_no(letter_no);
				shdul.setDate_goi_letter(date_goi_letter);
				shdul.setSanction_date(sanction_date);
				shdul.setType_of_letter(type_of_letter); 
				shdul.setLevel_a(cont_aname);
				shdul.setLevel_b(cont_bname);
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
	    			model.put("msg", "Couldn’t roll back transaction " + rbe);
	    		}
	    		throw e;
	    	}finally{
	    		if(sessionHQL!=null){
	    			sessionHQL.close();
	    		}
	    	}
			return new ModelAndView("redirect:conversion");
		}	
}