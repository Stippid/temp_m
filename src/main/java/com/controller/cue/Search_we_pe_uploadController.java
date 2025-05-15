package com.controller.cue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_WE_PE_UploadDAO;
import com.dao.cue.Cue_WE_PE_UploadDAOImpl;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Search_we_pe_uploadController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private Cue_wepe_conditionDAO wetpetamdDAO3;
	cueContoller M = new cueContoller();
	@Autowired
	private RoleBaseMenuDAO roledao ;
	Cue_WE_PE_UploadDAO masterDAO = new Cue_WE_PE_UploadDAOImpl();
	
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	

	ValidationController validation = new ValidationController();
	cueContoller m = new cueContoller();
	@RequestMapping(value = "/admin/upload_WE1", method = RequestMethod.POST)
	public ModelAndView upload_WE1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("we_pe01", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("sponsor_dire1", sponsor_dire);
			Mmap.put("arm1", arm);
			Mmap.put("doc_type1", doc_type);
			List<Map<String, Object>> list =wetpetamdDAO3.getAttributeFromCategorySearchWePe(we_pe,we_pe_no,sponsor_dire,arm,doc_type, status, roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Line_dte")){	
				
				Mmap.put("getArmNameList",m.getArmNameLine(roleSusNo));
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectArmNameList",select);
				Mmap.put("getArmNameList", m.getArmNameList());
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
			}
			Mmap.put("getsponserListCue", M.getsponserListCue());
			return new ModelAndView("cueTiles");
		}
	
	
	@RequestMapping(value = "/search_WE_PE", method = RequestMethod.GET)
	public ModelAndView search_WE_PE(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_WE_PE", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}	
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		
		
		Mmap.put("msg",msg);
		if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		//Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WE_PE_Tiles" );
	}
	

@RequestMapping(value = "/admin/search_WE_PE1", method = RequestMethod.POST)
	public ModelAndView search_WE_PE1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date
			){
			
		
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("we_pe01", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
       if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
					 
		List<Map<String, Object>> list =wetpetamdDAO3.getAttributeFromCategorySearchWePe1(we_pe,we_pe_no,doc_type,arm,sponsor_dire,status,roleType,from_date,to_date, roleAccess);
			
		Mmap.put("list", list);
		
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WE_PE_Tiles");
	}
	
	
	@RequestMapping(value = "/getSearchWePeNameDetailsList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSearchWePeNameDetailsList(String val,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("Line_dte")) {	
			 q = session.createQuery("select distinct we_pe_no from cue_wepe where we_pe=:we_pe and we_pe_no is not null and sponsor_dire=:roleSusNo order by we_pe_no");
			
		}else {
		 q = session.createQuery("select distinct we_pe_no from cue_wepe where we_pe=:we_pe and we_pe_no is not null order by we_pe_no");
		}
		q.setParameter("we_pe", val);
		if (roleAccess.equals("Line_dte")) {	
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);
	}

	
	@RequestMapping(value = "/Approved_WE_PE_Url", method=RequestMethod.POST)
	public ModelAndView Approved_WE_PE_Url(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe02", required = false) String we_pe,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "sponsor_dire2", required = false) String sponsor_dire,
		@RequestParam(value = "doc_type2", required = false) String doc_type,
		@RequestParam(value = "arm2", required = false) String arm,
		@RequestParam(value = "status2", required = false) String status,
		@RequestParam(value = "from_date2", required = false) String from_date,
		@RequestParam(value = "to_date2", required = false) String to_date){
		String username = session.getAttribute("username").toString();
		Mmap.put("msg", masterDAO.setApprovedARM(appid,username));
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("we_pe01", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
	
					 
		List<Map<String, Object>> list =wetpetamdDAO3.getAttributeFromCategorySearchWePe1(we_pe,we_pe_no,doc_type,arm,sponsor_dire,status,roleType,from_date,to_date, roleAccess);
		
		Mmap.put("list", list);	
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("redirect:search_WE_PE");
	}
	
	@RequestMapping(value = "/delete_WE_PE_UrlAdd", method=RequestMethod.POST)
	public @ResponseBody List<String> delete_WE_PE_UrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(masterDAO.setDeleteARM(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/update_WE_PE_Url")
	public ModelAndView update_WE_PE_Url(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(roleType.equals("ALL")& roleType.equals("VIEW")& !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Line_dte")){	
			
			model.put("getArmNameList",m.getArmNameLine(roleSusNo));
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			model.put("selectLine_dte",select);
			model.put("selectArmNameList",select);
			model.put("getArmNameList", m.getArmNameList());
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		//model.put("WePeEditCMD", masterDAO.getcue_wepeByid(updateid));	
		model.put("WePeEditCMD", masterDAO.getcue_wepeByid(updateid));
		model.put("msg",msg);
//		model.put("getArmNameList", M.getArmNameList());
//		model.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("edit_search_WE_PE_Tiles");
	}
	
	@RequestMapping(value = "/WePeEditAction", method=RequestMethod.POST)
	@ResponseBody	
	public ModelAndView WePeEditAction(@ModelAttribute("WePeEditCMD") cue_wepe updateid,@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1,HttpServletRequest request,ModelMap model,HttpSession session,HttpSession sessionEdit) {		
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(roleType.equals("ALL") & roleType.equals("VIEW") & !roleType.equals("DEO")  & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		
		String from_date = request.getParameter("eff_frm_date");
		String to_date = request.getParameter("eff_to_date");
		String table_title = request.getParameter("table_title");
		
		
		try {
			String answer = request.getParameter("answer");
			String suprcdd_we_pe_no = request.getParameter("suprcdd_we_pe_no");
			
			
			if(answer == "No" || answer.equals("No"))
			{
				if(suprcdd_we_pe_no == "" || suprcdd_we_pe_no.equals("")) {
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Enter Superseeded Document No");
					return new ModelAndView("redirect:update_WE_PE_Url");
				}
				
				if(validation.checkWepeLength(updateid.getSuprcdd_we_pe_no())  == false)
				 {
					model.put("msg",validation.supWepenoMSG);
					return new ModelAndView("redirect:update_WE_PE_Url");
				 }
			}

		   String arm = request.getParameter("arm");
		   if(arm.equals("0") || arm == "0" || arm.equals("") || arm == null || arm.isEmpty())
			{
			   model.put("updateid", updateid.getId());
				model.put("msg", "Please Select Arm.");
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
		   
		   String arm_code = String.format("%04d", Integer.parseInt(updateid.getArm()));
			if(validation.checkArmCodeLength(arm_code)  == false)
			{
		 		model.put("msg",validation.arm_codeMSG);
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
			
			String sponsor_dire = request.getParameter("sponsor_dire");
			if(sponsor_dire == "0" ||  sponsor_dire.equals("0") || sponsor_dire ==null ||  sponsor_dire.isEmpty())
			{
				model.put("updateid",updateid.getId() );
				model.put("msg", "Please Select Sponsor Directorate");
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
			
			if(validation.checkSponsorDireLength(updateid.getSponsor_dire())  == false)
			{
		 		model.put("msg",validation.spodireMSG);
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
			
			 String doc_type = request.getParameter("doc_type");
			if(doc_type == "--Select--" ||  doc_type.equals("--Select--") || doc_type ==null ||  doc_type.isEmpty())
			{
				model.put("updateid",updateid.getId() );
				model.put("msg", "Please Select Document Type");
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
			
			/*if(validation.checkRemarksLength(updateid.getDoc_path())  == false)
			{
		 		model.put("msg","Please Enter Valid Length Of Document Path");
				return new ModelAndView("redirect:update_WE_PE_Url"); 
			}*/
			
			if(validation.checkRemarksLength(updateid.getRemarks())  == false)
			{
		 		model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:update_WE_PE_Url");
			}
			
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		updateid.setModified_by(username);
		updateid.setModified_on(creadtedate);
		String fname = "";
		String extension = "";
		if (!doc_path1.isEmpty()) {
			
			String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());
			final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (doc_path1.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:upload_WE");
			}
			if(!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_WE");
			}
			
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
					updateid.setDoc_path(fname);
				}else {
					model.put("msg", "Only *.pdf file extension allowed");
					return new ModelAndView("redirect:upload_WE");
				}
			}
			catch (Exception e) {
			}
		}
		else {
			List<cue_wepe> newamd  = masterDAO.getWe_Pe_DownloadByid(updateid.getId());
			String  oldamdtdocu=newamd.get(0).getDoc_path();
			updateid.setDoc_path(oldamdtdocu);
		}
		
		masterDAO.UpdateArtAttValue(updateid,username,from_date,to_date,table_title);		
		model.put("msg", "Updated Successfully");
		}	
		catch (Exception e) {
			//return null;
		}
		return new ModelAndView("redirect:upload_WE");
	}  


@RequestMapping(value = "/getDetailsBySearchWePeCondiNo",method=RequestMethod.POST)
	public @ResponseBody List<cue_wepe> getDetailsBySearchWePeCondiNo(String val,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from cue_wepe where we_pe_no=:val ") ;
		q.setParameter("val", val);
		@SuppressWarnings("unchecked")
		List<cue_wepe> list = (List<cue_wepe>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
	
	@RequestMapping(value = "/updaterejectactionSearchWePe",method = RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectactionSearchWePe(HttpSession session,String remarks,String letter_no,int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers1("cue_tb_miso_wepe_upload_condition",remarks,id,letter_no,username);
		return list2;
	}	
	
	 @RequestMapping(value = "/admin/getDownloadImage", method = RequestMethod.POST)
	    public ModelAndView getDownloadImage(@ModelAttribute("id1") int id,@ModelAttribute("pageUrl") String pageUrl,@ModelAttribute("contraint") String contraint,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
	    	String url=pageUrl;    	    	
	    	String EXTERNAL_FILE_PATH = "";	            
	        EXTERNAL_FILE_PATH = masterDAO.getWe_Pe_DownloadByid(id).get(0).getDoc_path();
	        File file = null;
	        file = new File(EXTERNAL_FILE_PATH);    
	        try{
	            if(!file.exists()){
	           	 model.put("msg", "Sorry.The file you are looking for does not exist");	
	            	if(contraint.equals("Edit"))
	            	{
	            		model.put("getArmNameList", M.getArmNameList());
	            		model.put("getsponserListCue", M.getsponserListCue());
	            		model.put("WePeEditCMD", masterDAO.getcue_wepeByid(id));	
	            	}
	            		return new ModelAndView(url);
	               }
	       }
	        catch(Exception exception){
	                
	        }
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
	                
	                if(contraint.equals("Edit"))
	            	{
	                	model.put("getArmNameList", M.getArmNameList());
	            		model.put("getsponserListCue", M.getsponserListCue());
	            		model.put("WePeEditCMD", masterDAO.getcue_wepeByid(id));	
	            	}
	                model.put("msg", "Download Successfully");	
	            		return new ModelAndView(url);        
	                
	        } catch (FileNotFoundException e) {
	        
	        }	        
	        
	        if(contraint.equals("Edit"))
	    	{
	        	model.put("getArmNameList", M.getArmNameList());
	    		model.put("getsponserListCue", M.getsponserListCue());
	        	model.put("WePeEditCMD", masterDAO.getcue_wepeByid(id));	
	    	}
	    		return new ModelAndView(url);
	    }

	 @RequestMapping(value = "/admin/getDownloadImageWePeUpload", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageWePeUpload(@ModelAttribute("id1") int id,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
	            String EXTERNAL_FILE_PATH = "";	            
	           EXTERNAL_FILE_PATH = masterDAO.getWe_Pe_DownloadByid(id).get(0).getDoc_path();
	           
	        File file = null;
	        file = new File(EXTERNAL_FILE_PATH);
	        try{
	             if(!file.exists()){
	                return new ModelAndView("redirect:upload_WE?msg=Sorry. The file you are looking for does not exist");
	                }
	        }
	        catch(Exception exception){
	        }
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
	                return new ModelAndView("redirect:upload_WE?msg=Download Successfully");
	        } catch (FileNotFoundException e) {
	        	
	        }
	        return new ModelAndView("redirect:upload_WE?msg=Download Successfully");
	    }	 
	 
	 @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getWEPE_NOCount",method=RequestMethod.POST)
		public @ResponseBody Boolean getWEPE_NOCount(@RequestParam String we_pe_no) {
			String hql="select distinct we_pe_no from UploadAamendmentToDocument where  we_pe_no =:we_pe_no ";
			List<UploadAamendmentToDocument> users = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try {
				Query query= session.createQuery(hql);
				query.setParameter("we_pe_no", we_pe_no);
				users = (List<UploadAamendmentToDocument>) query.list();
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
}
	
