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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.dao.cue.WEPEamendmentDAO;
import com.dao.cue.WEPEamendmentDAOImpl;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class search_uploded_amendment_to_WE_PE_documentController {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	cueContoller M =new cueContoller();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	WEPEamendmentDAO masterDAO = new WEPEamendmentDAOImpl();	
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/search_uploded_amendment_to_WEPEdocument", method = RequestMethod.GET)
	public ModelAndView search_uploded_document(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_uploded_amendment_to_WEPEdocument", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_uploded_amendment_to_WE_PE_documentTiles");
	}

	
	@RequestMapping(value = "/getuplodedWePeamdTabletitle", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getuplodedWePeamdTabletitle(String type,HttpSession sessionUserId,String table_title) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {		

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
	   
	    Query q ;
	     
		if(type != "" && type != null){	
			if (roleAccess.equals("Line_dte")) {
				q = session.createQuery("select distinct table_title from cue_wepe where table_title!='' and table_title is not null and status='1' and we_pe=:type and upper(table_title) like:table_title and sponsor_dire=:roleSusNo order by table_title").setMaxResults(10) ;
				q.setParameter("type", type);
				q.setParameter("table_title", table_title.toUpperCase()+"%");
				q.setParameter("roleSusNo", roleSusNo);
			}else {
			q = session.createQuery("select distinct table_title from cue_wepe where table_title!='' and table_title is not null and status='1' and we_pe=:type and upper(table_title) like:table_title order by table_title").setMaxResults(10) ;
			q.setParameter("type", type);
			q.setParameter("table_title", table_title.toUpperCase()+"%");
		}}
		else {
			q = session.createQuery("select distinct table_title from cue_wepe where table_title!='' and table_title is not null and status='1' and upper(table_title) like:table_title order by table_title").setMaxResults(10) ;
			q.setParameter("table_title", table_title.toUpperCase()+"%");
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);

	}
	
	@RequestMapping(value = "/updaterejectactionAmendment", method=RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectactionAmendment(String remarks,String letter_no,int id) {
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wepe_amdt",remarks,id,letter_no);
		return list2;
	}
	
	@RequestMapping(value = "/admin/Approved_WEPEamendment_Url", method=RequestMethod.POST)
	public ModelAndView Approved_WEPEamendment_Url(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe02", required = false) String we_pe,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "amdt_title_we_pe2", required = false) String amdt_title_we_pe,
		@RequestParam(value = "status2", required = false) String status,
		@RequestParam(value = "from_date1", required = false) String from_date,
		@RequestParam(value = "to_date1", required = false) String to_date){
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("msg", masterDAO.setApprovedWEPEamendment(appid));
		
		List<Map<String, Object>> list =masterDAO.search_uploded_amendment_to_WEPEdocument1(we_pe_no,status,roleType,from_date,to_date,roleAccess);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("redirect:search_uploded_amendment_to_WEPEdocument");
	}
	
	@RequestMapping(value = "/delete_WEPEamendment_UrlAdd", method=RequestMethod.POST)
	public @ResponseBody List<String> delete_WEPEamendment_UrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(masterDAO.setDeleteWEPEamendment(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/admin/update_WEPEamendment_Url", method=RequestMethod.POST)
	public ModelAndView update_WEPEamendment_Url(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(roleType.equals("VIEW") & roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("EditWEPEamendmentCMD", masterDAO.getUploadAamendmentToDocumentByid(updateid));		
		model.put("msg", msg);
		return new ModelAndView("EditWE_PE_AmendmentTiles");
	}	
	 
	@RequestMapping(value = "/EditWEPEamendmentAction", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView cue_wepe(@ModelAttribute("EditWEPEamendmentCMD") UploadAamendmentToDocument updateid,@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1,HttpSession session,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(roleType.equals("VIEW") & roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");    
		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
		String fname = "";
		String extension = "";
		
		if(validation.checkWepeLength(updateid.getWe_pe_no())  == false){
			model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		if(validation.checkWetPetLength(updateid.getLetter_no())  == false){
			model.put("msg",validation.authLtrnoMSG);
			return new ModelAndView("redirect:upload_amendment_to_document");
		}
		
		if (!doc_path1.isEmpty()) {
			String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());
	
			 if (doc_path1.getSize() > fileSizeLimit) {
				 model.put("msg", "File size should be less then 2 MB");
				 return new ModelAndView("redirect:upload_amendment_to_document");
			 }
			if(!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_amendment_to_document");
			} 
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			// code modify by Paresh on 05/05/20202
			try {
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
					return new ModelAndView("redirect:upload_amendment_to_document");
				}
			}
			catch (Exception e) {
			}			
		}
		else {
			List<UploadAamendmentToDocument> newamd  = masterDAO.getAmendment_We_PeByid(updateid.getId());
			String  oldamdtdocu=newamd.get(0).getDoc_path();
			updateid.setDoc_path(oldamdtdocu);
		}
		masterDAO.UpdateWEPEamendmentValue(updateid);		
		model.put("msg", "Updated Successfully");
		return new ModelAndView("redirect:upload_amendment_to_document");
	}
	
	// Download Document From ID in EDIT Page
	@RequestMapping(value = "/admin/getWEPE_Amdnment_Image", method = RequestMethod.POST)
    public ModelAndView getWEPE_Amdnment_Image(@ModelAttribute("id1") int id,@ModelAttribute("pageUrl") String pageUrl,@ModelAttribute("contraint") String contraint,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	String url=pageUrl;    	
    	String EXTERNAL_FILE_PATH = "";            
        EXTERNAL_FILE_PATH = masterDAO.getAmendment_We_PeByid(id).get(0).getDoc_path();           
            
        File file = null;
        file = new File(EXTERNAL_FILE_PATH);
        try{
                if(!file.exists()){                	
                	model.put("msg", "Sorry.The file you are looking for does not exist");	
                	if(contraint.equals("Edit"))
                	{
                		model.put("EditWEPEamendmentCMD", masterDAO.getUploadAamendmentToDocumentByid(id));	
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
            		model.put("EditWEPEamendmentCMD", masterDAO.getUploadAamendmentToDocumentByid(id));	
            	}
                model.put("msg", "Download Successfully");	
            		return new ModelAndView(url);
                
        } catch (FileNotFoundException e) {
                    
        
        }
        if(contraint.equals("Edit"))
    	{
    		model.put("EditWEPEamendmentCMD", masterDAO.getUploadAamendmentToDocumentByid(id));	
    	}
    		return new ModelAndView(url);
    }    
    
	@RequestMapping(value = "/getwepeamedno", method=RequestMethod.POST)
  	public @ResponseBody List<cue_wepe> getwepeamedno(String val,HttpSession sessionUserId) 
  	{	
  		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
  		Session session = HibernateUtil.getSessionFactory().openSession();
  		Transaction tx = session.beginTransaction();
  		Query q = session.createQuery("from UploadAamendmentToDocument where amdt_title_we_pe=:val") ;
  		q.setParameter("val", val);
  		@SuppressWarnings("unchecked")
  		List<cue_wepe> list = (List<cue_wepe>) q.list();
  		tx.commit();
  		session.close();
  		return list;
  	}      
    
  	@RequestMapping(value = "/gettabletitleamedfatch", method=RequestMethod.POST)
   	public @ResponseBody List<cue_wepe> gettabletitleamedfatch(String val,HttpSession sessionUserId) 
   	{	
   		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = session.beginTransaction();
   		Query q = session.createQuery("from UploadAamendmentToDocument where we_pe_no=:val") ;
   		q.setParameter("val", val);
   		@SuppressWarnings("unchecked")
   		List<cue_wepe> list = (List<cue_wepe>) q.list();
   		tx.commit();
   		session.close();
   		return list;
   	}
    
	
}
