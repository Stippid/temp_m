package com.controller.psg.update_offr_data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Identity_Card_Controller {

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	// ====================================save/edit================================//

	@RequestMapping(value = "/admin/Identity_CardAction", method = RequestMethod.POST)
	public @ResponseBody String Identity_CardAction(ModelMap Mmap, HttpSession session, HttpServletRequest request,
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
	
		
		
	  Date comm_date = format.parse(request.getParameter("comm_date"));
		String id_card_no = request.getParameter("id_card_no");
		String issue_authority = request.getParameter("issue_authority_sus");
		String id_marks = request.getParameter("id_marks");
		int hair_colour = Integer.parseInt(request.getParameter("hair_colour"));
		int eye_colour = Integer.parseInt(request.getParameter("eye_colour"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int census_id = Integer.parseInt(request.getParameter("census_id"));
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
		if (is_dt == null || is_dt.equals("null") || is_dt.equals("DD/MM/YYYY") || is_dt.equals("")) {
			return "Please Select Issue Date";
		}
		if (!validation.isValidDate(is_dt)) {
 			return validation.isValidDateMSG + " of Issue";	
		}
		if (!is_dt.equals("") && !is_dt.equals("DD/MM/YYYY")) {
			issue_dt = format.parse(is_dt);
		}
		if (com.CompareDate(issue_dt,comm_date) == 0) {
			return "Issue Date should be Greater than Commission Date";
		}
		if (issue_authority == null || issue_authority.equals("") || issue_authority.equals("null")) {
			return "Please Enter Issue Authority";
		}	
		if (id_marks == null || id_marks.equals("") || id_marks.equals("null")) {
			return "Please Enter Visible Identification Marks";
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
		if (!validation.isOnlyAlphabet(hair_other)) {
	 		return validation.isOnlyAlphabetMSG + " Hair Other";	
		}
		if (!validation.isvalidLength(hair_other,validation.nameMax,validation.nameMin)) {
	 		return "Hair Other " + validation.isValidLengthMSG;	
		}
		if (!validation.isOnlyAlphabet(eye_other)) {
	 		return validation.isOnlyAlphabetMSG + " Eye Other";	
		}
		if (!validation.isvalidLength(eye_other,validation.nameMax,validation.nameMin)) {
	 		return " Eye Other " + validation.isValidLengthMSG;	
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
				String mnhFilePath = session.getAttribute("psgFilePath").toString() + "/Officer/Identity";
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
				TB_CENSUS_IDENTITY_CARD icard = new TB_CENSUS_IDENTITY_CARD();
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
				
				icard.setComm_id(comm_id);
				icard.setCensus_id(census_id);
				icard.setStatus(0);
				icard.setCreated_by(username);
				icard.setCreated_date(date);
				icard.setUnit_sus_no(unit_sus_no);
				if (!fname.equals("")) {
					icard.setIdentity_image(fname);
					icard.setImage_updated_date(date);
				} else {
					TB_CENSUS_IDENTITY_CARD preid = (TB_CENSUS_IDENTITY_CARD) sessionhql.get(
							TB_CENSUS_IDENTITY_CARD.class, Integer.parseInt(request.getParameter("pre_identity_id")));
					icard.setIdentity_image(preid.getIdentity_image());
					icard.setImage_updated_date(preid.getImage_updated_date());
				}
				int id = (int) sessionhql.save(icard);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_IDENTITY_CARD set id_card_no=:id_card_no, issue_dt=:issue_dt, issue_authority=:issue_authority, "
						+ "id_marks=:id_marks, hair_colour=:hair_colour,eye_other=:eye_other,hair_other=:hair_other, eye_colour=:eye_colour,unit_sus_no=:unit_sus_no,status=:status,"
						+ "modified_by=:modified_by, modified_date=:modified_date ";
				if (!fname.equals("")) {
					hql += ", identity_image=:identity_image, image_updated_date=:image_updated_date ";
				}
				hql += " where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0).setString("id_card_no", id_card_no)
						.setTimestamp("issue_dt", issue_dt).setString("issue_authority", issue_authority)
						.setString("id_marks", id_marks).setInteger("hair_colour", hair_colour)
						.setString("hair_other", hair_other)
						.setString("eye_other", eye_other)
						.setString("unit_sus_no", unit_sus_no)
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
				com.update_offr_status(census_id,username);
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

	@RequestMapping(value = "/admin/getIdentity_Card", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_IDENTITY_CARD> getIdentity_Card(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where census_id=:census_id and comm_id=:comm_id order by id desc ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		query.setMaxResults(1);

		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Ide_cardData(TB_CENSUS_IDENTITY_CARD obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1 = "";
		/*try {*/
			String hql1 = "update TB_CENSUS_IDENTITY_CARD set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CENSUS_IDENTITY_CARD set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		/*} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}*/
		return msg;

	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getIdentitycardReject", method = RequestMethod.POST)
	public @ResponseBody String getIdentitycardReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_IDENTITY_CARD IdeCardApp = new TB_CENSUS_IDENTITY_CARD();
		IdeCardApp.setCensus_id(Integer.parseInt(request.getParameter("p_id")));
		IdeCardApp.setComm_id(new BigInteger(request.getParameter("comm_id")));
		IdeCardApp.setId(Integer.parseInt(request.getParameter("card_id")));
		IdeCardApp.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Identity_Card_Reject(IdeCardApp, username);

		return msg1;

	}

	public String Update_Identity_Card_Reject(TB_CENSUS_IDENTITY_CARD obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CENSUS_IDENTITY_CARD set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id())
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

	public @ResponseBody List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where census_id=:census_id and comm_id=:comm_id and status = '3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getIdentity_Card3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_IDENTITY_CARD> getIdentity_Card3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where census_id=:census_id and comm_id=:comm_id and status='3'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Change_identity_card_action", method = RequestMethod.POST)
		public @ResponseBody String Change_identity_card_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_IDENTITY_CARD where id=:id";
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
}
