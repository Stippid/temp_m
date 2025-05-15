package com.controller.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.models.UploadDocumentsFormation;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;*/
//import com.sun.el.parser.ParseException;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class UploadDocumentsController {

	//------------------------------- Common save function for return int value ---------------------------	
	/*public int saveFunction(Object obj)
	{
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int id=(Integer)session1.save(obj);
		session1.getTransaction().commit();
		session1.close();
		return id;
	}*/
		
	//-------------------------------  For page Open -------------------------------------			
	/* @RequestMapping(value = "/UploadDocsFormUrl", method = RequestMethod.GET)
	public ModelAndView UploadDocsFormUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		Mmap.put("msg", msg);
		return new ModelAndView("UploadDocsTiles","UploadDocsCMD", new UploadDocumentsFormation());
	} */
	
	//-------------------------------  For SAVE UPLOAD DOCUMENTS -------------------------------------	
	/* @RequestMapping(value = "/saveUploadDocs")
	public ModelAndView saveUploadDocs(@ModelAttribute("UploadDocsCMD") UploadDocumentsFormation ud,
			@RequestParam(value = "auth_esta_str_regarmy_half", required = false) MultipartFile auth_esta_str_regarmy_half,
			@RequestParam(value = "stati_digest_manpwr_restr_half", required = false) MultipartFile stati_digest_manpwr_restr_half,
			@RequestParam(value = "stati_digest_a_b_veh_conf_four", required = false) MultipartFile stati_digest_a_b_veh_conf_four,
			@RequestParam(value = "stati_digest_arm_equi_conf_four", required = false) MultipartFile stati_digest_arm_equi_conf_four,
			@RequestParam(value = "stati_digest_fol_ration_losses_etc_restr_half", required = false) MultipartFile stati_digest_fol_ration_losses_etc_restr_half,
			@RequestParam(value = "stre_indian_army_conf_annual", required = false) MultipartFile stre_indian_army_conf_annual,
			@RequestParam(value = "mt_accid_army_bveh_restr_annual", required = false) MultipartFile mt_accid_army_bveh_restr_annual,
			@RequestParam(value = "studrpt_court_inquiry_restr_fiveyear", required = false) MultipartFile studrpt_court_inquiry_restr_fiveyear,
			HttpServletRequest request,ModelMap model,HttpSession session) throws ParseException {
		
		String username = session.getAttribute("username").toString();		
		ud.setCreated_by(username);
		ud.setCreateddate(new Date());
		
		if(!FilenameUtils.getExtension(auth_esta_str_regarmy_half.getOriginalFilename()).equals("pdf") ||
			!FilenameUtils.getExtension(stati_digest_manpwr_restr_half.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(stati_digest_a_b_veh_conf_four.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(stati_digest_arm_equi_conf_four.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(stati_digest_fol_ration_losses_etc_restr_half.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(stre_indian_army_conf_annual.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(mt_accid_army_bveh_restr_annual.getOriginalFilename()).equals("pdf") || 
			!FilenameUtils.getExtension(studrpt_court_inquiry_restr_fiveyear.getOriginalFilename()).equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:UploadDocsFormUrl");
		}
		
		final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
		if (auth_esta_str_regarmy_half.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:UploadDocsFormUrl");
		}	
		
		try{
			if (!auth_esta_str_regarmy_half.isEmpty()){ 
	       		String name=fileupload(auth_esta_str_regarmy_half.getBytes(),auth_esta_str_regarmy_half.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("auth_esta_str_regarmy_half");
	       			ud.setUpload_docs_fullname("Authorized Establishment and Held Strength of Regular Army");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		try{
			if (!stati_digest_manpwr_restr_half.isEmpty()){ 
	       		String name=fileupload(stati_digest_manpwr_restr_half.getBytes(),stati_digest_manpwr_restr_half.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("stati_digest_manpwr_restr_half");
	       			ud.setUpload_docs_fullname("Statistical Digest on Manpower");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		try{
			if (!stati_digest_a_b_veh_conf_four.isEmpty()){ 
	       		String name=fileupload(stati_digest_a_b_veh_conf_four.getBytes(),stati_digest_a_b_veh_conf_four.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("stati_digest_a_b_veh_conf_four");
	       			ud.setUpload_docs_fullname("Statistical Digest on ‘A’ & ‘B’ Vehicles");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		try{
			if (!stati_digest_arm_equi_conf_four.isEmpty()){ 
	       		String name=fileupload(stati_digest_arm_equi_conf_four.getBytes(),stati_digest_arm_equi_conf_four.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("stati_digest_arm_equi_conf_four");
	       			ud.setUpload_docs_fullname("Statistical Digest on Armament and Equipment");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		
		try{
			if (!stati_digest_fol_ration_losses_etc_restr_half.isEmpty()){ 
	       		String name=fileupload(stati_digest_fol_ration_losses_etc_restr_half.getBytes(),stati_digest_fol_ration_losses_etc_restr_half.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("stati_digest_fol_ration_losses_etc_restr_half");
	       			ud.setUpload_docs_fullname("Statistical Digest on FOL, Ration, Losses, Animals, Labour etc.");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		
		try{
			if (!stre_indian_army_conf_annual.isEmpty()){ 
	       		String name=fileupload(stre_indian_army_conf_annual.getBytes(),stre_indian_army_conf_annual.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("stre_indian_army_conf_annual");
	       			ud.setUpload_docs_fullname("Strength of the Indian Army");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
		try{
			if (!mt_accid_army_bveh_restr_annual.isEmpty()){ 
	       		String name=fileupload(mt_accid_army_bveh_restr_annual.getBytes(),mt_accid_army_bveh_restr_annual.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("mt_accid_army_bveh_restr_annual");
	       			ud.setUpload_docs_fullname("MT Accidents (WIR) on Army ‘B’ Vehicles");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}	
		
		try{
			if (!studrpt_court_inquiry_restr_fiveyear.isEmpty()){ 
	       		String name=fileupload(studrpt_court_inquiry_restr_fiveyear.getBytes(),studrpt_court_inquiry_restr_fiveyear.getOriginalFilename(),session);
	       		if (name != "") {
	       			ud.setUpload_docs_path(name);
	       			ud.setUpload_docs_name("studrpt_court_inquiry_restr_fiveyear");
	       			ud.setUpload_docs_fullname("Study Report on Closed Court of Inquiry");
	       			saveFunction(ud);
	       			model.put("msg", "Documents Uploaded Successfully");
	       		}
	    	}
		}
		catch (Exception e) {}
	   
		return new ModelAndView("redirect:UploadDocsFormUrl");
	} */
	
	//------------------------------- Download Uploaded Files ---------------------------	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/downloadDocFormUrl", method = RequestMethod.POST)
    public ModelAndView downloadDocFormUrl(@ModelAttribute("colname") String colname,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
       String EXTERNAL_FILE_PATH = "";	
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from UploadDocumentsFormation where id in (SELECT max(id) FROM UploadDocumentsFormation where upload_docs_name=:upload_docs_name)");
       q.setParameter("upload_docs_name", colname);
       List<UploadDocumentsFormation> list = (List<UploadDocumentsFormation>) q.list();
       tx.commit();
       session.close();
      
       if(list.size() != 0) {
       EXTERNAL_FILE_PATH = list.get(0).getUpload_docs_path();
       
       File file = null;
       file = new File(EXTERNAL_FILE_PATH);
       try{
    	   if(!file.exists()){
    		   model.put("msg", "Sorry.The file you are looking for does not exist");	
    		   return new ModelAndView("redirect:formationDashboard");
    	   }
       }
       catch(Exception exception){ }
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
            
            return new ModelAndView("redirect:formationDashboard?msg=Download Successfully");
       } catch (FileNotFoundException e) {
           
       }  
       }
       else
    	   model.put("msg", "Sorry.The file you are looking for does not exist");
       return new ModelAndView("redirect:formationDashboard");
    }
	*/
	//------------------------------- Used For File Upload function ---------------------------
	/* public String fileupload(byte[] b,String name,HttpSession session)
	{
	// code modify by Paresh on 05/05/20202
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String fname ="";
       	String  formationFilePath = session.getAttribute("formationFilePath").toString();
		try{
			byte[] bytes =b;
            File dir = new File(formationFilePath);
        	if (!dir.exists())
        	    dir.mkdirs();
        	fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_DASHBOARD_DOC.PDF";
       		File serverFile = new File(fname);	    
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);	                
            stream.close();			
		}catch (Exception e) {}
		return fname;
	}
	*/
}
