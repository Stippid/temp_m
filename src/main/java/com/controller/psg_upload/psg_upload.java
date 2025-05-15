			
package com.controller.psg_upload;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.PSGDAO;
import com.dao.tms.MctSlotDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.TB_PSG_UPLOAD;
import com.models.TB_TMS_UPLOAD_DOC_MCR;
import com.persistance.util.HibernateUtil;
				
@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class psg_upload {
	
		@Autowired
		MctSlotDAO makeMasretDAO;
		
		@Autowired
		PSGDAO psgdao;
		
		@Autowired
		private RoleBaseMenuDAO roledao;
		HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
		
		AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
		
		ValidationController validation = new ValidationController();
		
		@RequestMapping(value="/psgUploadurl")
		public ModelAndView psgUploadurl(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){	
	
		
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("psgUploadurl", roleid);
				/*if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}*/

	
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		if(roleAccess.equals("Unit")){
			model.put("sus_no",roleSusNo);
			model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		
		model.put("msg",msg);
		return new ModelAndView("upload_psg_docTiles");
	}
	
	
	/////////// Upload Document MCR ////////////////////////

				
		@RequestMapping(value = "/upload_psg_docAction", method =RequestMethod.POST)
		public ModelAndView upload_psg_docAction(@Valid @ModelAttribute("upload_psg_docCMD") TB_PSG_UPLOAD scu,@RequestParam(value = "msg", required = false) String msg,
			  @RequestParam(value = "up_offrs_do_2_iaff_30101", required = false) MultipartFile up_offrs_do_2_iaff_30101_1,
			  @RequestParam(value = "up_str_return_iaff_30081", required = false) MultipartFile up_str_return_iaff_300812,
			  @RequestParam(value = "up_offrs__arrival_rp1", required = false) MultipartFile up_offrs__arrival_rp12,
			  @RequestParam(value = "up_jco_do_21", required = false) MultipartFile up_jco_do_212,
			  @RequestParam(value = "up_str_return_iaff_3009_a_b1", required = false) MultipartFile up_str_return_iaff_3009_a_b12,
			 BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,MultipartHttpServletRequest  req) throws IOException, HibernateException, DecoderException {
			 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("psgUploadurl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
		
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String sus_no = request.getParameter("sus_no");
			String date1 = request.getParameter("date");
			
			
			
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
	    	String fname = "";
	    	String fname_up_str_return_iaff_3008="";
	    	
	    	String fname_up_offrs__arrival_rp1="";
	    	String fname_up_jco_do_21="";
	    	String fname_up_str_return_iaff_3009_a_b1="";
	    	String fname_up_statics_pers_cns1="";
	    		    	
	    	
	    	if(sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null))
			{
	    		model.put("msg","Please Enter the Sus No.");
				return new ModelAndView("redirect:psgUploadurl");
			}
			if(date1.equals("null") || date1.equals("") || date1.equals(null))
			{
				model.put("msg","Please Select  Date.");
				return new ModelAndView("redirect:psgUploadurl");
				
			}
	    	
			try {
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				MultipartFile upload_img= req.getFile("up_offrs_do_2_iaff_30101");
				MultipartFile fup_str_return_iaff_3008= req.getFile("up_str_return_iaff_30081");
				MultipartFile fup_offrs__arrival_rp1= req.getFile("up_offrs__arrival_rp1");
				MultipartFile fup_jco_do_21= req.getFile("up_jco_do_21");
				MultipartFile fup_str_return_iaff_3009_a_b1= req.getFile("up_str_return_iaff_3009_a_b1");
				
				
				
				
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (!upload_img.isEmpty()) {
					
					
				 byte[] bytes = up_offrs_do_2_iaff_30101_1.getBytes();
					
				
				if(fileValidation.check_PDF_File(bytes) ) {
				
						String psgFilePath = session.getAttribute("psgFilePath").toString();
						File dir = new File(psgFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						
						String filename = up_offrs_do_2_iaff_30101_1.getOriginalFilename();
						
						String extension="";
						int i = filename.lastIndexOf('.');
						if (i >= 0) {
							extension = filename.substring(i+1);
						}
						fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_PSGDOC."+extension;
						File serverFile = new File(fname);	               
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);	                
						stream.close();
						scu.setUp_offrs_do_2_iaff_3010(fname);
						
					}
				else {
						model.put("msg","Invalid File Format of OFFRS DO-II [IAFF-3010]");
					}
				}
				
				
				if (!fup_str_return_iaff_3008.isEmpty()) {
					
				
						
				 byte[] bytes = up_str_return_iaff_300812.getBytes();
					
				
				if(fileValidation.check_PDF_File(bytes) ) {
					
						String psgFilePath = session.getAttribute("psgFilePath").toString();
						File dir = new File(psgFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						
						String filename = up_str_return_iaff_300812.getOriginalFilename();
						
						String extension="";
						int i = filename.lastIndexOf('.');
						if (i >= 0) {
							extension = filename.substring(i+1);
						}
						fname_up_str_return_iaff_3008 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_PSGDOC."+extension;
						File serverFile = new File(fname_up_str_return_iaff_3008);	               
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);	                
						stream.close();
						scu.setUp_str_return_iaff_3008(fname_up_str_return_iaff_3008);
						
					}
				else {
						model.put("msg","Invalid File Format of STR Return [IAFF-3008]");
					}
				}
				
				
				if (!fup_offrs__arrival_rp1.isEmpty()) {
					
					
					
					 byte[] bytes = up_offrs__arrival_rp12.getBytes();
						
					
					if(fileValidation.check_PDF_File(bytes) ) {
						
							String psgFilePath = session.getAttribute("psgFilePath").toString();
							File dir = new File(psgFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							
							String filename = up_offrs__arrival_rp12.getOriginalFilename();
							
							String extension="";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i+1);
							}
							fname_up_offrs__arrival_rp1 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_PSGDOC."+extension;
							File serverFile = new File(fname_up_offrs__arrival_rp1);	               
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);	                
							stream.close();
							scu.setUp_offrs__arrival_rp(fname_up_offrs__arrival_rp1);
							
						}
					else {
							model.put("msg","Invalid File Format of OFFRS ARRIVAL REPORT");
						}
					}
			
				if (!fup_jco_do_21.isEmpty()) {
					
					
					
					 byte[] bytes = up_jco_do_212.getBytes();
						
					
					if(fileValidation.check_PDF_File(bytes) ) {
						
							String psgFilePath = session.getAttribute("psgFilePath").toString();
							File dir = new File(psgFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							
							String filename = up_jco_do_212.getOriginalFilename();
							
							String extension="";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i+1);
							}
							fname_up_jco_do_21 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_PSGDOC."+extension;
							File serverFile = new File(fname_up_jco_do_21);	               
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);	                
							stream.close();
							scu.setUp_jco_do_2(fname_up_jco_do_21);
							
						}
					else {
							model.put("msg","Invalid File Format of JCO/OR DO-II [IAFF-3010]");
						}
					}
				if (!fup_str_return_iaff_3009_a_b1.isEmpty()) {
					
					
					
					 byte[] bytes = up_str_return_iaff_3009_a_b12.getBytes();
						
					
					if(fileValidation.check_PDF_File(bytes) ) {
					
							String psgFilePath = session.getAttribute("psgFilePath").toString();
							File dir = new File(psgFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							
							String filename = up_str_return_iaff_3009_a_b12.getOriginalFilename();
							
							String extension="";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i+1);
							}
							fname_up_str_return_iaff_3009_a_b1 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_PSGDOC."+extension;
							File serverFile = new File(fname_up_str_return_iaff_3009_a_b1);	               
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);	                
							stream.close();
							scu.setUp_str_return_iaff_3009_a_b (fname_up_str_return_iaff_3009_a_b1);
							
						}
					else {
							model.put("msg","Invalid File Format of STR Return [IAFF-3009] [APPX 'A' & 'B'] ");
						}
					}
				
			
				
				
				
				scu.setDate(date1);
				scu.setSus_no(sus_no);
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				session1.beginTransaction();
				
				int id=0;
				List<TB_PSG_UPLOAD> list = getChk_psg_upload_monthly(sus_no,date1);
				if(list.size() > 0)
				{
					id= Integer.parseInt(String.valueOf(list.get(0).getId())) ;
					
					
					if (upload_img.isEmpty())
					{
						scu.setUp_offrs_do_2_iaff_3010(list.get(0).getUp_offrs_do_2_iaff_3010());
					}
					if (fup_str_return_iaff_3008.isEmpty())
					{
						scu.setUp_str_return_iaff_3008(list.get(0).getUp_str_return_iaff_3008());
					}
					
					if (fup_offrs__arrival_rp1.isEmpty())
					{
						scu.setUp_offrs__arrival_rp(list.get(0).getUp_offrs__arrival_rp());
					}
					if (fup_jco_do_21.isEmpty())
					{
						scu.setUp_jco_do_2(list.get(0).getUp_jco_do_2());
					}
					
					
					if (fup_str_return_iaff_3009_a_b1.isEmpty())
					{
						scu.setUp_str_return_iaff_3009_a_b(list.get(0).getUp_str_return_iaff_3009_a_b());
					}
					
					
					scu.setModified_by(username);
					scu.setModified_date(date);
					
					
					scu.setCreated_by(list.get(0).getCreated_by());
					scu.setCreated_date(list.get(0).getCreated_date());
					
					scu.setId(id);
				}
				
				if(id == 0)
				{
					scu.setCreated_by(username);
					scu.setCreated_date(date);
					session1.save(scu);
				}
				else
				{
					session1.update(scu);
				}
			
				
				model.put("msg","Document Uploaded Successfully");
				session1.getTransaction().commit();
				session1.close();
				
					
				}
				catch (Exception e) {
					model.put("msg","an Error ocurre file saving.");
				}
			finally{
						if(sessionHQL!=null){
							
							   sessionHQL.close();
						}
				}	
						
					
	
				
				return new ModelAndView("redirect:psgUploadurl");
			
	}
		
		
		
		public List<TB_PSG_UPLOAD> getChk_psg_upload_monthly(String sus_no,String month) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_PSG_UPLOAD where sus_no = :sus_no and date = :month");
			q.setParameter("sus_no", sus_no);
			q.setParameter("month", month);
			@SuppressWarnings("unchecked")
			List<TB_PSG_UPLOAD> list = (List<TB_PSG_UPLOAD>) q.list();
			tx.commit();
			session.close();
			
			
			
			return list;
		}
		
		
		
		
		
		
		@RequestMapping(value="/search_psgUploadurl",method = RequestMethod.GET)
		public ModelAndView search_psgUploadurl(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){	

			
				String roleid = session.getAttribute("roleid").toString();
				/*Boolean val = roledao.ScreenRedirect("search_psgUploadurl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}*/
			
			model.put("msg",msg);
			model.put("list", psgdao.search_psg_upload());
			
			return new ModelAndView("search_psgUploadTiles");
		}
		
		
		@RequestMapping(value = "/admin/getDownloadImagePSG", method = RequestMethod.POST)
		public ModelAndView getDownloadImagePSG(@ModelAttribute("id") int id,@ModelAttribute("file_name") String file_name1, ModelMap model, HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws IOException {
			String EXTERNAL_FILE_PATH = "";
			
			List<TB_PSG_UPLOAD> list = psgdao.getDocumentPSGImg(id,file_name1, session);
			
			if(file_name1.equals("up_offrs_do_2_iaff_3010")) {
				EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_offrs_do_2_iaff_3010();
			}
			
			if(file_name1.equals("up_str_return_iaff_3008")) {
				EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_str_return_iaff_3008();
			}
			
			if(file_name1.equals("up_offrs__arrival_rp")) {
				EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_offrs__arrival_rp();
			}
			
			if(file_name1.equals("up_jco_do_2")) {
				EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_jco_do_2();
			}
			
			if(file_name1.equals("up_str_return_iaff_3009_a_b")) {
				EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_str_return_iaff_3009_a_b();
			}
			
			
			
			
			
			//EXTERNAL_FILE_PATH =psgdao.getDocumentPSGImg(id,file_name1, session).get(0).getUp_offrs_do_2_iaff_3010();
			
			
			if(EXTERNAL_FILE_PATH == null)
			{
				return new ModelAndView("redirect:search_psgUploadurl?msg=Sorry. The file you are looking for does not exist");
			}
			File file = null;
			file = new File(EXTERNAL_FILE_PATH);
			try {
				if (file == null || !file.exists() ) {
					return new ModelAndView("redirect:search_psgUploadurl?msg=Sorry. The file you are looking for does not exist");
				}
			} catch (Exception exception) {
			}
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = null;
			try {
			    	inputStream = new BufferedInputStream(new FileInputStream(file));
			    	FileCopyUtils.copy(inputStream, response.getOutputStream());
			    	
			    } catch (FileNotFoundException e) {
				
				}
			
			return new ModelAndView("redirect:search_psgUploadurl?msg=Download Successfully.");
		}
		
		
		@RequestMapping(value = "/getPSG_UPLOADReportList",method=RequestMethod.GET)
		public @ResponseBody DatatablesResponse<Map<String, Object>> getPSG_UPLOADReportList(
				@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
			

			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			String qry="";
			
			DataSet<Map<String, Object>> dataSet = psgdao.DatatablesCriteriasPSGreport(criterias,qry, roleSubAccess);
			return DatatablesResponse.build(dataSet, criterias);
		}
}
				
				
