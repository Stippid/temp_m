package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.IUTDAO;
import com.models.TB_PSG_UPLOAD;
import com.models.TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER;
import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Iut_Track_Status_Upload_File_Controller {
	
	@Autowired
	RoleBaseMenuDAO roledao; 
	
	@Autowired
	private IUTDAO iutDAO;
	
	@RequestMapping(value = "/UploadFile_Voucher")
	public ModelAndView UploadFile_Voucher(
   @ModelAttribute("ba_nop") String updateid,
   @ModelAttribute("target_unit_sus") String target_unit_sus_no,@ModelAttribute("type_of_veh") String type_of_veh,
   ModelMap Mmap,
    @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		int userid = Integer.parseInt(sessionEdit.getAttribute("userId").toString());
	
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("type_of_veh", type_of_veh);
 		Mmap.put("msg", msg);
		Mmap.put("ba_nop", updateid);
		Mmap.put("target_unit_sus_no", target_unit_sus_no);
		return new ModelAndView("UploadFileTiles");
	}
	
//	@RequestMapping(value = "admin/IutUploadFile",method=RequestMethod.POST)
//	public ModelAndView IutUploadFile(@ModelAttribute("UploadFileCMD") TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER rs,
//	        @RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1,
//	        @RequestParam(value = "doc_upload2", required = false) MultipartFile doc_upload2,
//	        @RequestParam(value = "u_file_hidden1", required = false) String u_file_hidden1,
//	        @RequestParam(value = "u_file_hidden2", required = false) String u_file_hidden2,
//	        @RequestParam(value = "update_document", required = false) String update_document,
//	        String msg,
//	        HttpServletRequest request, ModelMap model, HttpSession session
//			) throws IOException {
//		String roleid = session.getAttribute("roleid").toString();
//		DateWithTimeStampController timestamp = new DateWithTimeStampController();
//		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
//
//		Transaction txn = sessionhql.beginTransaction();
//		String extension = "";
//		String fname = "";
//		String extension1 = "";
//		String fname1 = "";
//		
//		String ba_no = request.getParameter("ba_no");
//		String target_sus_no = request.getParameter("target_sus_no");
//		
//		String upload_imgEXt1 = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
//		String upload_imgEXt2 = FilenameUtils.getExtension(doc_upload2.getOriginalFilename());
//		
//
//	
//		int userid = Integer.parseInt(session.getAttribute("userId").toString());
//	
//		Query q0=sessionhql.createQuery("select count(id) from TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER where ba_no=:ba_no and target_sus_no=:target_sus_no");
//		
//		q0.setString("ba_no", ba_no);
//		
//		q0.setString("target_sus_no", target_sus_no);
//		
//		Long c = (Long) q0.uniqueResult();
//		
//		
//		
//			if(u_file_hidden1.equals(""))	
//			{
//				
//			if (!doc_upload1.isEmpty()) {
//				if (!upload_imgEXt1.toUpperCase().equals("pdf".toUpperCase())) {
//			        model.put("msg", "Only *.pdf file extensions allowed");
//					return new ModelAndView("redirect:UploadFile");
//						}
//				
//			
//				byte[] bytes = doc_upload1.getBytes();
//				String tmsFilePath = session.getAttribute("tmsFilePath").toString();
//				File dir = new File(tmsFilePath);
//			
//				if (!dir.exists())
//					dir.mkdirs();
//				String filename = doc_upload1.getOriginalFilename();
//				int i = filename.lastIndexOf('.');
//				if (i >= 0) {
//					extension = filename.substring(i + 1);
//				}
//
//			
//				fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
//						+ userid + "_TMSDOC." + extension;
//
//
//				File serverFile = new File(fname);
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//					stream.write(bytes);
//					stream.close();
//					 rs.setUpload_voucher1(fname.replace("\\","/"));
//			}
//			}
//		
//			if(u_file_hidden2.equals(""))	
//			{
//			
//			if (!doc_upload2.isEmpty()) {
//				
//				if (!upload_imgEXt2.toUpperCase().equals("pdf".toUpperCase())) {
//			        model.put("msg", "Only *.pdf file extensions allowed");
//					return new ModelAndView("redirect:UploadFile");
//						}
//				DateWithTimeStampController timestamp2 = new DateWithTimeStampController();
//				byte[] bytes = doc_upload2.getBytes();
//				String tmsFilePath1 = session.getAttribute("tmsFilePath").toString();
//				
//				File dir = new File(tmsFilePath1);
//				if (!dir.exists())
//					dir.mkdirs();
//				String filename = doc_upload2.getOriginalFilename();
//				int i = filename.lastIndexOf('.');
//				if (i >= 0) {
//					extension = filename.substring(i + 1);
//				}
//				fname1 = dir.getAbsolutePath() + File.separator + timestamp2.currentDateWithTimeStampString() + "_2_"
//						+ userid + "_TMSDOC." + extension;
//
//
//				File serverFile = new File(fname1);
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//					stream.write(bytes);
//					stream.close();
//					 rs.setUpload_voucher2(fname1.replace("\\","/"));
//			}
//			}
//			 Session sessionHQL = null;
//				Transaction tx = null;
//				if(c==0)
//				{
//				try {
//					sessionHQL = HibernateUtil.getSessionFactory().openSession();
//					tx = sessionHQL.beginTransaction();
//					rs.setBa_no(ba_no);
//					rs.setTarget_sus_no(target_sus_no);
//					sessionHQL.save(rs);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					tx.commit();
//					model.put("msg", "Document Uploaded Successfully.");
//				} 
//				catch (Exception e) {
//					try {
//						tx.rollback();
//						model.put("msg", "roll back transaction");
//					} catch (RuntimeException rbe) {
//						model.put("msg", "Couldnot roll back transaction " + rbe.getMessage());
//					}
//					throw e;
//				} finally {
//					if (sessionHQL != null) {
//						sessionHQL.close();
//					}
//				}
//				}
//				else {
//					
//					  if (update_document != null && !update_document.isEmpty()) {
//					    
//					        try {
//					            sessionHQL = HibernateUtil.getSessionFactory().openSession();
//					            tx = sessionHQL.beginTransaction();
//
//					            if (update_document.equals("update_document1")) {
//					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
//					            	int id = list.get(0).getId();
//					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
//					                if (existingRecord != null) {
//					                   
//					                    existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
//					               
//					                    sessionHQL.update(existingRecord);
//					            } 
//					                }else if (update_document.equals("update_document2")) {
//					                	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
//						            	int id = list.get(0).getId();
//					                	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
//					                	 if (existingRecord != null) {
//					                         
//					                         existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
//					                       
//					                         sessionHQL.update(existingRecord);
//					                     }
//					            } else if (update_document.equals("update_documents")) {
//					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
//					            	int id = list.get(0).getId();
//
//					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
//					            	 if (existingRecord != null) {
//					                    
//					                     existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
//					                     existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
//					                     sessionHQL.update(existingRecord);
//					                 }
//					            }
//
//					            sessionHQL.flush();
//					            sessionHQL.clear();
//					            tx.commit();
//					            model.put("msg", "Document/s Updated Successfully.");
//					        } catch (Exception e) {
//					            // Handle exceptions
//					        } finally {
//					            if (sessionHQL != null) {
//					                sessionHQL.close();
//					            }
//					        }
//					    }
//
//				}
//			return new ModelAndView("redirect:track_iut_status");
//	
//}	
//	
	
	@RequestMapping(value = "/admin/getDownloadVoucher", method = RequestMethod.POST)
	public ModelAndView getDownloadVoucher(@ModelAttribute("ba_num") String ba_num,
			@ModelAttribute("target_unit_sus_no") String target_unit_sus_no,
			@ModelAttribute("file_name") String file_name1,@ModelAttribute("type_of_veh") String type_of_veh,
			ModelMap model, HttpSession session,String msg,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		String EXTERNAL_FILE_PATH = "";
		
	    List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_num,target_unit_sus_no ,session);
	    
	    if(list.size() ==0) {
	    	
	    	model.put("ba_nop", ba_num);
	    	model.put("msg", "Sorry. The file you are looking for does not exist");
	    	return new ModelAndView("UploadFileTiles");
	    
	    }
	    
	    else {
	
		if(file_name1.equals("doc_upload1")) {
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no, session).get(0).getUpload_voucher1();
			
		}
	
		if(file_name1.equals("doc_upload2")) {	
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no,session).get(0).getUpload_voucher2();
			
		}
		if(EXTERNAL_FILE_PATH == null)
		{	model.put("ba_nop", ba_num);
		  	model.put("msg", "Sorry. The file you are looking for does not exist");
	    	return new ModelAndView("UploadFileTiles");
		}
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (file == null || !file.exists() ) {
				model.put("ba_nop", ba_num);
			  	model.put("msg", "Sorry. The file you are looking for does not exist");
		    	return new ModelAndView("UploadFileTiles");
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
			    } 
		//model.put("ba_nop", ba_num);
//	    model.put("msg", "Download Successfully.");
//    	return new ModelAndView("UploadFileTilesC");
		return new ModelAndView("redirect:UploadFile_Voucher?msg=Download Successfully."); 
	    }
	
	
	
	@RequestMapping(value = "/admin/track_iut_status_a_b_veh", method = RequestMethod.GET)
	public ModelAndView track_iut_status_a_b_veh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status", roleid);
		String username = session.getAttribute("username").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		ArrayList<ArrayList<String>> list = iutDAO.trackiutstatus(username,"","","","","","","",roleSubAccess,roleSusNo,roleAccess);
		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		
		return new ModelAndView("trackiutstatusTiles");
	}
	
	
	@RequestMapping(value = "admin/IutUploadFile",method=RequestMethod.POST)
	public ModelAndView IutUploadFile(@ModelAttribute("UploadFileCMD") TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER rs,
	        @RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1,
	        @RequestParam(value = "doc_upload2", required = false) MultipartFile doc_upload2,
	        @RequestParam(value = "u_file_hidden1", required = false) String u_file_hidden1,
	        @RequestParam(value = "u_file_hidden2", required = false) String u_file_hidden2,
	        @RequestParam(value = "update_document", required = false) String update_document,
	        String msg,
	        HttpServletRequest request, ModelMap model, HttpSession session
			) throws IOException {
		String roleid = session.getAttribute("roleid").toString();
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = sessionhql.beginTransaction();
		String extension = "";
		String fname = "";
		String extension1 = "";
		String fname1 = "";
		
		String ba_no = request.getParameter("ba_no");
		String target_sus_no = request.getParameter("target_sus_no");
		
		String upload_imgEXt1 = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
		String upload_imgEXt2 = FilenameUtils.getExtension(doc_upload2.getOriginalFilename());
		

	
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
	
		Query q0=sessionhql.createQuery("select count(id) from TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER where ba_no=:ba_no and target_sus_no=:target_sus_no");
		
		q0.setString("ba_no", ba_no);
		
		q0.setString("target_sus_no", target_sus_no);
		
		Long c = (Long) q0.uniqueResult();
		
	
		
			if(u_file_hidden1.equals(""))	
			{
				
			if (!doc_upload1.isEmpty()) {
				if (!upload_imgEXt1.toUpperCase().equals("pdf".toUpperCase())) {
			        model.put("msg", "Only *.pdf file extensions allowed");
					return new ModelAndView("redirect:UploadFileC");
						}
				
			
				byte[] bytes = doc_upload1.getBytes();
				String tmsFilePath = session.getAttribute("tmsFilePath").toString();
				File dir = new File(tmsFilePath);
			
				if (!dir.exists())
					dir.mkdirs();
				String filename = doc_upload1.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}

			
				fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
						+ userid + "_TMSDOC." + extension;


				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					 rs.setUpload_voucher1(fname.replace("\\","/"));
			}
			}
		
			if(u_file_hidden2.equals(""))	
			{
			
			if (!doc_upload2.isEmpty()) {
				
				if (!upload_imgEXt2.toUpperCase().equals("pdf".toUpperCase())) {
			        model.put("msg", "Only *.pdf file extensions allowed");
					return new ModelAndView("redirect:UploadFileC");
						}
				DateWithTimeStampController timestamp2 = new DateWithTimeStampController();
				
				byte[] bytes = doc_upload2.getBytes();
				String tmsFilePath1 = session.getAttribute("tmsFilePath").toString();
				
				File dir = new File(tmsFilePath1);
				if (!dir.exists())
					dir.mkdirs();
				String filename = doc_upload2.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				fname1 = dir.getAbsolutePath() + File.separator + timestamp2.currentDateWithTimeStampString() + "_2_"
						+ userid + "_TMSDOC." + extension;


				File serverFile = new File(fname1);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					 rs.setUpload_voucher2(fname1.replace("\\","/"));
			}
			}
			 Session sessionHQL = null;
				Transaction tx = null;
				if(c==0)
				{
				try {
					sessionHQL = HibernateUtil.getSessionFactory().openSession();
					tx = sessionHQL.beginTransaction();
					rs.setBa_no(ba_no);
					rs.setTarget_sus_no(target_sus_no);
					sessionHQL.save(rs);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					model.put("msg", "Document Uploaded Successfully.");
				} 
				catch (Exception e) {
					try {
						tx.rollback();
						model.put("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						model.put("msg", "Couldnot roll back transaction " + rbe.getMessage());
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				}
				else {
					
					  if (update_document != null && !update_document.isEmpty()) {
					    
					        try {
					            sessionHQL = HibernateUtil.getSessionFactory().openSession();
					            tx = sessionHQL.beginTransaction();

					            if (update_document.equals("update_document1")) {
					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
					            	int id = list.get(0).getId();
					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					                if (existingRecord != null) {
					                   
					                    existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
					               
					                    sessionHQL.update(existingRecord);
					            } 
					                }else if (update_document.equals("update_document2")) {
					                	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
						            	int id = list.get(0).getId();
					                	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					                	 if (existingRecord != null) {
					                         
					                         existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
					                       
					                         sessionHQL.update(existingRecord);
					                     }
					            } else if (update_document.equals("update_documents")) {
					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
					            	int id = list.get(0).getId();

					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					            	 if (existingRecord != null) {
					                    
					                     existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
					                     existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
					                     sessionHQL.update(existingRecord);
					                 }
					            }

					            sessionHQL.flush();
					            sessionHQL.clear();
					            tx.commit();
					            model.put("msg", "Document/s Updated Successfully.");
					        } catch (Exception e) {
					            // Handle exceptions
					        } finally {
					            if (sessionHQL != null) {
					                sessionHQL.close();
					            }
					        }
					    }

				}
			return new ModelAndView("redirect:track_iut_status_a_b_veh");
	}
	
	@RequestMapping(value = "/admin/getDownloadVouchera_b", method = RequestMethod.POST)
	public ModelAndView getDownloadVoucher2(@ModelAttribute("ba_num") String ba_num,
			@ModelAttribute("target_unit_sus_no") String target_unit_sus_no,
			@ModelAttribute("file_name") String file_name1,@ModelAttribute("type_of_veh") String type_of_veh,
			ModelMap model, HttpSession session,String msg,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		String EXTERNAL_FILE_PATH = "";
		
	    List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_num,target_unit_sus_no ,session);
	    
	    if(list.size() ==0) {
	    	return new ModelAndView("redirect:UploadFile_Voucher?msg=Sorry. The file you are looking for does not exist");
	    }
	    
	    else {
	
		if(file_name1.equals("doc_upload1")) {
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no, session).get(0).getUpload_voucher1();
			
		}
	
		if(file_name1.equals("doc_upload2")) {	
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no,session).get(0).getUpload_voucher2();
			
		}
		if(EXTERNAL_FILE_PATH == null)
		{
			return new ModelAndView("redirect:UploadFile_Voucher?msg=Sorry. The file you are looking for does not exist");
		}
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (file == null || !file.exists() ) {
				return new ModelAndView("redirect:UploadFile_Voucher?msg=Sorry. The file you are looking for does not exist");
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
	    }
		return new ModelAndView("redirect:UploadFile_Voucher?msg=Download Successfully."); 
	    }
	
	
//	@RequestMapping(value = "/admin/getviewVouchera_b", method = RequestMethod.POST)
//	public String getviewVouchera_b(@ModelAttribute("ba_num") String ba_num,
//			@ModelAttribute("target_unit_sus_no") String target_unit_sus_no,
//			@ModelAttribute("file_name") String file_name1,@ModelAttribute("type_of_veh") String type_of_veh,
//			ModelMap model, HttpSession session,String msg,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		String EXTERNAL_FILE_PATH = "";
//		
//	    List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_num,target_unit_sus_no ,session);
//	
//	
//		if(file_name1.equals("doc_upload1")) {
//			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no, session).get(0).getUpload_voucher1();
//			
//		}
//	
//		if(file_name1.equals("doc_upload2")) {	
//			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no,session).get(0).getUpload_voucher2();
//			
//		}
//		
//		return  EXTERNAL_FILE_PATH; 
//	    }
//	
	
	@RequestMapping(value = "/getviewVouchera_b", method = RequestMethod.POST)
	public @ResponseBody String  getviewVouchera_b(String ba_num, String target_unit_sus_no,String file_name1,
			HttpSession sessionUserId
		) {
		String roleType = sessionUserId.getAttribute("roleType").toString();
	    String EXTERNAL_FILE_PATH = "";		
	   // List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_num,target_unit_sus_no ,sessionUserId);	
		if(file_name1.equals("doc_upload1")) {
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no, sessionUserId).get(0).getUpload_voucher1();
			
		}
	
		if(file_name1.equals("doc_upload2")) {	
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no,sessionUserId).get(0).getUpload_voucher2();
			
		}
		return  EXTERNAL_FILE_PATH; 
	}
}
