package com.controller.psg.Jco_Update_JcoData;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.models.psg.Jco_Update_JcoData.TB_IDENTITY_CARD_JCO;
import com.models.psg.Transaction.*;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Identity_Card_JCOController {
	ValidationController validation = new ValidationController();
	private Psg_CommonController com = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;
	// ====================================save/edit================================//

	@RequestMapping(value = "/admin/Identity_Card_JcoOr_Action", method = RequestMethod.POST)
	public @ResponseBody String Identity_Card_JcoOr_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			MultipartHttpServletRequest mul) throws IOException, ParseException {

		String fname = "";
		String extension = "";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		Date issue_dt = null;
		String username = session.getAttribute("username").toString();
		String is_dt = request.getParameter("issue_dt");
		
		
	
			Date enroll_date = format.parse(request.getParameter("enroll_date"));
		String id_card_no = request.getParameter("id_card_no");
		String issue_authority = request.getParameter("issue_authority_sus");
		String id_marks = request.getParameter("id_marks");
		int hair_colour = Integer.parseInt(request.getParameter("hair_colour"));
		int eye_colour = Integer.parseInt(request.getParameter("eye_colour"));
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		
		String unit_sus_no = request.getParameter("unit_sus_noidcard");	
		
		String hair_other = request.getParameter("hair_other");	
		String eye_other = request.getParameter("eye_other");	
		int card_id = Integer.parseInt(request.getParameter("card_id"));
		String msg = "";
		
		if (id_card_no == null || id_card_no.equals("") || id_card_no.equals("null")) {
			return "Please Enter ID Card No";
		}
		if (!validation.isOnlyAlphabetNumeric(id_card_no)) {
	 		return validation.isOnlyAlphabetNumericMSG + " Id Card No";	
		}
		if (!validation.isvalidLength(id_card_no,validation.id_card_noMax,validation.id_card_noMin)) {
 			return "Id Card No " + validation.isValidLengthMSG;	
		}	
		
		if (issue_authority == null || issue_authority.equals("") || issue_authority.equals("null")) {
			return "Please Enter Issue Authority";
		}
		
			if (is_dt == null || is_dt.equals("null") || is_dt.equals("DD/MM/YYYY") || is_dt.equals("")) {
			return "Please Select Issue Date";
		   }
			else if (!validation.isValidDate(is_dt)) {
	 			return validation.isValidDateMSG + " of Issue";	
			}
			else
			{
				issue_dt = format.parse(is_dt);
			}
			if (com.CompareDate(issue_dt,enroll_date) == 0) {
				return "Issue Date should be Greater than Enrollment Date";
			}
		if (id_marks == null || id_marks.equals("") || id_marks.equals("null")) {
			return "Please Enter  Visible Identification Marks";
		}
		if (!validation.isOnlyAlphabetNumeric(id_marks)) {
	 		return validation.isOnlyAlphabetNumericMSG + " Visible Identification Marks";	
		}
		if (!validation.isvalidLength(id_marks,validation.nameMax,validation.nameMin)) {
 			return "Visible Identification Marks " + validation.isValidLengthMSG;	
		}
		if (hair_colour == 0) {
			return "Please Select Hair Colour";
		}
		
		if (eye_colour == 0) {
			return "Please Select Eye Colour";
		}
		

		try {
			MultipartFile file = mul.getFile("identity_image");

			if (!file.getOriginalFilename().isEmpty()) {
				
				
				String upload_imgEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();	
		    	if(!upload_imgEXt.equals("jpg") && !upload_imgEXt.equals("jpeg") && !upload_imgEXt.equals("png")) {
		    		msg= "Only *.jpg, *.jpeg and *.png file extensions allowed";
					
					return msg;
					
				}
		    	
		    	long filesize=file.getSize()/1024;
		    	if(filesize>20) {
		    		msg= "Image Size Should 20KB Or Less";
					
					return msg;
		    	}
		    	
				// try {
				byte[] bytes = file.getBytes();
				String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/JCO_OR/Identity";
				File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();

				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j + 1);
				}
				java.util.Date date1 = new java.util.Date();
				fname = dir.getAbsolutePath() + File.separator
						+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
								.toString().replace(" ", "").toString().replace("-", "").toString()
						+ "PSG_Doc." + extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			}
			if (card_id == 0) {
				TB_IDENTITY_CARD_JCO icard = new TB_IDENTITY_CARD_JCO();
				
				if (!fname.equals("")) {
					icard.setIdentity_image(fname);
					icard.setImage_updated_date(date);
				} else {
					if(request.getParameter("pre_identity_id")!=null && !request.getParameter("pre_identity_id").equals("")
							&& !request.getParameter("pre_identity_id").equals("0")) {
					TB_IDENTITY_CARD_JCO preid = (TB_IDENTITY_CARD_JCO) sessionhql.get(
							TB_IDENTITY_CARD_JCO.class, Integer.parseInt(request.getParameter("pre_identity_id")));
					icard.setIdentity_image(preid.getIdentity_image());
					icard.setImage_updated_date(preid.getImage_updated_date());
					}
					else {
						return "Please Upload Image";
					}
				}
				icard.setCreated_by(username);
				icard.setCreated_date(date);
				icard.setId_card_no(id_card_no);
				icard.setIssue_dt(issue_dt);
				icard.setIssue_authority(issue_authority);
				icard.setId_marks(id_marks);
				icard.setHair_colour(hair_colour);
				icard.setEye_colour(eye_colour);
				icard.setHair_other(hair_other);
				icard.setEye_other(eye_other);
				
				icard.setJco_id(jco_id);
				icard.setUnit_sus_no(unit_sus_no);
	
				icard.setStatus(0);
				icard.setCreated_by(username);
				icard.setCreated_date(date);
				icard.setInitiated_from("u");
				int id = (int) sessionhql.save(icard);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_IDENTITY_CARD_JCO set id_card_no=:id_card_no, issue_dt=:issue_dt, issue_authority=:issue_authority, "
						+ "id_marks=:id_marks, hair_colour=:hair_colour,eye_other=:eye_other,unit_sus_no=:unit_sus_no,hair_other=:hair_other, eye_colour=:eye_colour,status=:status,"
						+ "modified_by=:modified_by, modified_date=:modified_date ";
				if (!fname.equals("")) {
					hql += ", identity_image=:identity_image, image_updated_date=:image_updated_date ";
				}
				hql += " where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0).setString("id_card_no", id_card_no)
						.setTimestamp("issue_dt", issue_dt).setString("issue_authority", issue_authority)
						.setString("id_marks", id_marks).setInteger("hair_colour", hair_colour)
						.setString("hair_other", hair_other)
						.setString("unit_sus_no", unit_sus_no)
						.setString("eye_other", eye_other)
						.setInteger("eye_colour", eye_colour).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("id", card_id);

				if (!fname.equals("")) {
					query.setString("identity_image", fname).setTimestamp("image_updated_date", date);
				}

				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");
			}

			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				pjc.update_JcoOr_status(jco_id,username);
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		// sessionhql.close();
		return msg;
	}

	// ========================== Get Method =============================//

	@RequestMapping(value = "/admin/getIdentity_CardJCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_IDENTITY_CARD_JCO> getIdentity_CardJCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_IDENTITY_CARD_JCO where  jco_id=:jco_id order by id desc ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		query.setMaxResults(1);

		List<TB_IDENTITY_CARD_JCO> list = (List<TB_IDENTITY_CARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_IDENTITY_CARD_JCO> Ide_card_getDataJCO1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_IDENTITY_CARD_JCO where  jco_id=:jco_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_IDENTITY_CARD_JCO> list = (List<TB_IDENTITY_CARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Ide_cardData(TB_IDENTITY_CARD_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		try {
			String hql1 = "update TB_IDENTITY_CARD_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_IDENTITY_CARD_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getIdentitycardJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getIdentitycardReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_IDENTITY_CARD_JCO IdeCardApp = new TB_IDENTITY_CARD_JCO();
		
		IdeCardApp.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		IdeCardApp.setId(Integer.parseInt(request.getParameter("card_id")));
		IdeCardApp.setReject_remarks(reject_remarks);
		String msg1 = Update_Identity_Card_Reject(IdeCardApp, username);

		return msg1;

	}

	public String Update_Identity_Card_Reject(TB_IDENTITY_CARD_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql = "update TB_IDENTITY_CARD_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_IDENTITY_CARD_JCO> Ide_card_getDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_IDENTITY_CARD_JCO where  jco_id=:jco_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_IDENTITY_CARD_JCO> list = (List<TB_IDENTITY_CARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getIdentity_CardJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_IDENTITY_CARD_JCO> getIdentity_Card3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_IDENTITY_CARD_JCO where  jco_id=:jco_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_IDENTITY_CARD_JCO> list = (List<TB_IDENTITY_CARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Change_identity_card_JCOdeleteaction", method = RequestMethod.POST)
		public @ResponseBody String Change_identity_card_JCOdeleteaction(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_IDENTITY_CARD_JCO where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				sessionHQL.close();
				if (app > 0) {
					msg = "1";
				} else {
					msg = "0";
				}
			} catch (Exception e) {
			}
			return msg;
		}
	 
	 @RequestMapping(value = "/censusIdentityJCOConvertpath", method = RequestMethod.GET)
		public void censusIdentityJCOConvertpath( ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws IOException {
			final int BUFFER_SIZE = 4096;
			
			String filePath ="";
			if(request.getParameter("i_id")!=null && !request.getParameter("i_id").equals(""))
				filePath=UJD.getJcocensusIdentityImagePath(request.getParameter("i_id"));
			
			
			model.put("filePath", filePath);
			ServletContext context = request.getSession().getServletContext();
			try {
				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
					String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
					File downloadFile = new File(fullPath);
					FileInputStream inputStream = new FileInputStream(downloadFile);
					String mimeType = context.getMimeType(fullPath);
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());
					OutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outStream.close();
				} else {
					String fullPath = filePath;
					File downloadFile = new File(fullPath);
					FileInputStream inputStream = new FileInputStream(downloadFile);
					String mimeType = context.getMimeType(fullPath);
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());
					OutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outStream.close();
				}
			} catch (Exception ex) {
				
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		}
	 
}
