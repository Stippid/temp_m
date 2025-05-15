package com.controller.cue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.FlushMode;
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

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.WEPEamendmentDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class upload_amendment_to_WE_PE {

	@Autowired 
	WEPEamendmentDAO masterDAO;	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/upload_amendment_to_document", method = RequestMethod.GET)
	public ModelAndView upload_amendment_to_document(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_amendment_to_document", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("upload_amendment_to_documentTiles" ,"upload_amendment_to_documentCMD",new UploadAamendmentToDocument());
	}
	
	@RequestMapping(value = "/admin/upload_WE_Amend", method = RequestMethod.POST)
	public ModelAndView upload_WE_Amend(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "amdt_title_wet_pet1", required = false) String amdt_title_wet_pet,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "date_upload1", required = false) String date_upload,
			@RequestParam(value = "date_upload3", required = false) String date_upload3,
			@RequestParam(value = "letter_no1", required = false) String letter_no,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("we_pe01", we_pe);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("status1", status); 
			 
			List<Map<String, Object>> list =masterDAO.searchinuploadamednment(we_pe_no,status,roleType);
				
					Mmap.put("list", list);
					Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("upload_amendment_to_documentTiles");
	}
	
		@RequestMapping(value = "/upload_amendment_to_documentAction1", method = RequestMethod.POST)
		public ModelAndView upload_amendment_to_documentAction1(@ModelAttribute("upload_amendment_to_documentCMD") UploadAamendmentToDocument rs,
				@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1,
				HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();	
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());
		
		String we_pe_no = request.getParameter("we_pe_no");
		String we_pe = request.getParameter("we_pe");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		
		try
		{
			final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (doc_path1.getSize() > fileSizeLimit) {
				 model.put("msg", "File size should be less then 2 MB");
				 return new ModelAndView("redirect:upload_amendment_to_document");
			}
			if(!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_amendment_to_document");
			}
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document ");
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		 if(rs.getWe_pe_no().equals(""))
		{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:upload_amendment_to_document");
		}
		if(rs.getDoc_path()=="null")
			{
				model.put("msg", "Please Enter Document Path");
				return new ModelAndView("redirect:upload_amendment_to_document");
			}
		String fname = "";
		if (!doc_path1.isEmpty()) { 
			// code modify by Paresh on 05/05/20202
			try {
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				byte[] bytes = doc_path1.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					String cueFilePath = session.getAttribute("cueFilePath").toString();
					File dir = new File(cueFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_CUEDOC.PDF";
					File serverFile = new File(fname);	               
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();
					rs.setDoc_path(fname);
				}else {
					model.put("msg", "Only *.pdf file extension allowed");
					return new ModelAndView("redirect:upload_amendment_to_document");
				}
			}
			catch (Exception e) {
	       }
		}
		if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
			model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		
		if(validation.checkRemarksLength(rs.getDoc_path())  == false){
			model.put("msg",validation.amendDocMSG);
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		if(validation.checkWetPetLength(rs.getLetter_no())  == false){
			model.put("msg",validation.authLtrnoMSG);
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		
		List<cue_wepe> newamd  = masterDAO.getAmendmentDetails_We_PeByid(rs.getWe_pe_no());
		String amdt_title_we_pe=newamd.get(0).getTable_title();
		
//		Boolean e = checkDetailsOfExistingDatawe_pe_no(we_pe_no) ;
//		if(e.equals(false)) {	
		
		rs.setAmdt_title_we_pe(amdt_title_we_pe);
		rs.setStatus("0");
		rs.setCreated_by(username);
		rs.setCreated_on(creadtedate);
		sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		int did = (Integer) sessionHQL.save(rs);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		model.put("msg", "Data saved Successfully");
		
//		}
//		else {
//		model.put("msg", "Data Already Exists!");
//		}
		}
		catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}
		
		List<Map<String, Object>> list =masterDAO.searchinuploadamednment(we_pe_no,"","");
		model.put("list", list);
		model.put("we_pe01", we_pe);
		model.put("we_pe_no1", we_pe_no);
		model.put("list.size()", list.size());
		return new ModelAndView("upload_amendment_to_documentTiles");
	}

	
	
	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfExistingDatawe_pe_no(String we_pe_no)  {
		String qry="";
		
			 qry="from UploadAamendmentToDocument where we_pe_no=:we_pe_no" ;
		
		List<UploadAamendmentToDocument> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q= session.createQuery(qry);
			q.setParameter("we_pe_no", we_pe_no);
			users = (List<UploadAamendmentToDocument>) q.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked") 
	public Boolean checkDetailsOfWetPet(String wet_pet_no) {
		
		String hql=" select distinct wet_pet_no from cue_wet_pet where wet_pet_no=:wet_pet_no ";
		List<cue_wet_pet> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			users = (List<cue_wet_pet>) query.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
		
	@RequestMapping(value = "/admin/search_uploded_amendment_to_WEPEdocument1", method = RequestMethod.POST)
	public ModelAndView search_uploded_amendment_to_WEPEdocument1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "amdt_title_we_pe1", required = false) String amdt_title_we_pe,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			Mmap.put("we_pe01", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);	
			Mmap.put("from_date1", from_date);
			Mmap.put("to_date1", to_date);	
			List<Map<String, Object>> list =masterDAO.search_uploded_amendment_to_WEPEdocument1(we_pe_no,status,roleType,from_date,to_date,roleAccess);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_uploded_amendment_to_WE_PE_documentTiles");
	}

////////////////////////////////////////////////////////////


}
