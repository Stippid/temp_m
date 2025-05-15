package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.controller.HelpDeskController.helpController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Notification.notificatioDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MCRDAO;
import com.dao.tms.MVCRDAO;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_DTL_HISTORY;
import com.models.UploadDocumentMVCR;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class B_VehMVCRController {

	@Autowired
	MCRDAO mcrdao;

	@Autowired
	MVCRDAO addmctDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	ValidationController validation = new ValidationController();

	String mvcr_sus_no12 = "";
	String mvcr_unit_name12 = "";

	@Autowired
	private notificatioDAO notif;
	helpController helpcntl = new helpController();
	NotificationController notification = new NotificationController();

	@RequestMapping(value = "/admin/mvcrSetSession", method = RequestMethod.POST)
	public ModelAndView search_rising_dis_profileSetSession(ModelMap model, HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1) {
		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (!sus_no1.equals("0") && !sus_no1.equals("")) {
			mvcr_sus_no12 = sus_no1;
		}
		if (!unit_name1.equals("0") && !unit_name1.equals("")) {
			unit_name1 = unit_name1.replace("&#40;", "(");
			unit_name1 = unit_name1.replace("&#41;", ")");
			mvcr_unit_name12 = unit_name1;
		}
		return new ModelAndView("redirect:mvcr");
	}

	@RequestMapping(value = "/admin/mvcr", method = RequestMethod.GET)
	public ModelAndView search_unit_profile(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		System.err.println(roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("roleAccess", roleAccess);
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			String qry = "";
			ArrayList<List<String>> list = addmctDAO.UpdategetMVCRReportListPending(qry, roleSusNo, roleType,
					roleAccess);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
			} else {
				Mmap.put("list", list);
				String con = "";
				String setser_reason = "";
				for (int i = 0; i < list.size(); i++) {
					con += "jQuery('select#classification" + list.get(i).get(0) + "').val(" + list.get(i).get(3)
							+ ") \n";
					if (list.get(i).get(17) != null) {
						setser_reason += "jQuery('select#ser_reason" + list.get(i).get(0) + "').val("
								+ list.get(i).get(17) + ") \n";
					}
				}
				int TOTAL = Integer.parseInt(list.get(list.size() - 1).get(14));
				int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(15));
				int NotUpdated = TOTAL - UPDATE;
				Mmap.put("con", con + setser_reason);
				Mmap.put("TOTAL", TOTAL);
				Mmap.put("UPDATE", UPDATE);
				Mmap.put("NotUpdated", NotUpdated);

			}
		} else {
			String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
					+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
					+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
					+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
					+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

			Mmap.put("Js_Css", Js_Css);
			Mmap.put("mvcr_sus_no1", mvcr_sus_no12);
			Mmap.put("mvcr_unit_name1", mvcr_unit_name12);
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicT());
		Mmap.put("msg", msg);
		return new ModelAndView("mvcr_ReportTiles");
	}

	@RequestMapping(value = "/admin/UpdategetMVCRReportList", method = RequestMethod.POST)
	public ModelAndView UpdategetMVCRReportList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String qry = "";
		if (!sus_no.equals("") & sus_no.length() == 8) {
			if (roleAccess.equals("Unit")) {
				sus_no = roleSusNo;
			} else {
				String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
						+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
						+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
						+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
						+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
						+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

				Mmap.put("Js_Css", Js_Css);
			}

			Mmap.put("sus_no", sus_no);
			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
			}

			ArrayList<List<String>> list = addmctDAO.UpdategetMVCRReportListPending(qry, sus_no, roleType, roleAccess);
			if (list.size() == 0) {
				Mmap.put("list", list);
				Mmap.put("TOTAL", 0);
				Mmap.put("UPDATE", 0);
				Mmap.put("NotUpdated", 0);
			} else {
				Mmap.put("list", list);
				String con = "";
				String setser_reason = "";
				for (int i = 0; i < list.size(); i++) {
					con += "jQuery('select#classification" + list.get(i).get(0) + "').val(" + list.get(i).get(3)
							+ ") \n";
					if (list.get(i).get(17) != null) {
						setser_reason += "jQuery('select#ser_reason" + list.get(i).get(0) + "').val("
								+ list.get(i).get(17) + ") \n";
					}
				}
				int TOTAL = Integer.parseInt(list.get(list.size() - 1).get(14));
				int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(15));
				int NotUpdated = TOTAL - UPDATE;
				Mmap.put("con", con + setser_reason);
				Mmap.put("TOTAL", TOTAL);
				Mmap.put("UPDATE", UPDATE);
				Mmap.put("NotUpdated", NotUpdated);
			}
		} else if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Select SUS No.");
		} else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mvcr");
		}

		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:mvcr");
		}
		return new ModelAndView("mvcr_ReportTiles");
	}

	@RequestMapping(value = "/admin/getMVCRReportList", method = RequestMethod.POST)
	public ModelAndView getMVCRReportList(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name) {
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (!sus_no.equals("")) {

			Mmap.put("sus_no", sus_no);
		}
		if (!unit_name.equals("")) {

			Mmap.put("unit_name", unit_name);
		}
		ArrayList<List<String>> list = addmctDAO.getMVCRReportListPending(sus_no, roleType);
		Mmap.put("list", list);
		return new ModelAndView("mvcr_ReportTiles");
	}

	@RequestMapping(value = "/updateMvcr", method = RequestMethod.POST)
	public @ResponseBody String updateMvcr(String sus_no, String ba_no, int kms_run, String authrty_letter_no,
			String classification, String misoRmk, String unitRmk, String ser_status, String ser_reason,
			HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		if (val == false) {
			return "Access Denied.";
		}

		if (kms_run > 300000) {
			return "exceeding ceiling Kms Run";
		}
		String username = sessionA.getAttribute("username").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session sessionMvcr = HibernateUtil.getSessionFactory().openSession();
		Transaction txMvcr = sessionMvcr.beginTransaction();
		Query qMvcr = sessionMvcr.createQuery("from TB_TMS_MVCR_PARTA_DTL where ba_no =:ba_no and sus_no=:sus_no");
		qMvcr.setParameter("ba_no", ba_no);
		qMvcr.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MVCR_PARTA_DTL> ListMvcr = (List<TB_TMS_MVCR_PARTA_DTL>) qMvcr.list();
		txMvcr.commit();
		int version = ListMvcr.get(0).getVersion();
		int updateversion = version + 1;

		if (ListMvcr.size() == 0) {
			return "Invalid Data Input";
		}

		Integer km = kms_run;
		Integer last_km = ListMvcr.get(0).getKms_run();

		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionMvcr.beginTransaction();
		Query qry = sessionMvcr
				.createQuery("select count(*) from TB_TMS_MVCR_PARTA_DTL where ba_no = :ba_no and sus_no = :sus_no "
						+ "and (classification = :classification or classification is null) "
						+ "and (kms_run = :kms_run or kms_run is null) "
						+ "and (ser_reason = :ser_reason or ser_reason is null) "
						+ "and (ser_status = :ser_status or ser_status is null) "
						+ "and (remarks = :remarks or remarks is null)");

		qry.setParameter("ba_no", ba_no);
		qry.setParameter("sus_no", sus_no);
		qry.setParameter("classification", classification);
		qry.setParameter("kms_run", km);
		qry.setParameter("ser_reason", ser_reason);
		qry.setParameter("ser_status", ser_status);
		qry.setParameter("remarks", unitRmk);

		@SuppressWarnings("unchecked")
		Long count = (Long) qry.uniqueResult();
		tx.commit();
		if (count > 0) {
			return "Data already exists";

		}

		if (classification.equals("") || classification == null || classification.equals("null")) {
			return "Please Enter Classification.";
		}

		// if (Integer.parseInt(ListMvcr.get(0).getClassification()) >
		// Integer.parseInt(classification)) {
		// return "Classification can`t be Decrease from Last Classification.";
		// } else
		if (Integer.parseInt(classification) >= 5 && (authrty_letter_no.equals("") || authrty_letter_no == ""
				|| authrty_letter_no.equals(null) || authrty_letter_no == null || authrty_letter_no.equals("null"))) {
			return "Please Enter Authority Details.";
		} else if (km == null || last_km == null) {
			return "Please Enter KM.";
		} else if (ser_status.equals("1") && ser_reason.equals("0")) {
			return "Please select Reason of UNSV.";
		}

		else {
			Session ses1 = HibernateUtil.getSessionFactory().openSession();
			Transaction t2 = ses1.beginTransaction();

			String hql = "UPDATE TB_TMS_MVCR_PARTA_DTL set kms_run=:kms_run,status=:status,authrty_letter_no=:authrty_letter_no,classification=:classification,modify_by=:modify_by,modify_date=:modify_date,remarks=:unitRmk,miso_rmk=:misoRmk,ser_status=:ser_status,ser_reason=:ser_reason,version=:version WHERE ba_no =:ba_no";
			Query query = ses1.createQuery(hql);
			query.setParameter("ba_no", ba_no);
			query.setParameter("kms_run", kms_run);
			query.setParameter("status", "0");
			query.setParameter("authrty_letter_no", authrty_letter_no);
			query.setParameter("classification", classification);
			query.setParameter("modify_by", username);
			query.setParameter("modify_date", new Date());
			query.setParameter("unitRmk", unitRmk);
			query.setParameter("misoRmk", misoRmk);
			query.setParameter("ser_status", ser_status);
			query.setParameter("ser_reason", ser_reason);
			query.setParameter("version", updateversion);

			int result = query.executeUpdate();

			if (result > 0) {
				String hqlMAIN = "UPDATE TB_TMS_MVCR_PARTA_MAIN set status=:status ,modify_by=:modify_by,modify_date=:modify_date WHERE sus_no = :sus_no";
				Query queryMain = ses1.createQuery(hqlMAIN);
				queryMain.setParameter("status", "0");
				queryMain.setParameter("modify_by", username);
				queryMain.setParameter("modify_date", new Date());
				queryMain.setParameter("sus_no", sus_no);
				queryMain.executeUpdate();

				/* NITIN V4 17/11/2022 */
				String hqlMAIN1 = "UPDATE TB_TMS_DRR_DIR_DTL set status=:status WHERE ba_no = :ba_no and type_of_stock = '3'";
				Query queryMain1 = ses1.createQuery(hqlMAIN1);
				queryMain1.setParameter("status", "1");
				queryMain1.setParameter("ba_no", ba_no);
				queryMain1.executeUpdate();

				Query q1 = ses1.createQuery("from TB_TMS_DRR_DIR_DTL where ba_no= :ba_no");
				q1.setParameter("ba_no", ba_no);
				@SuppressWarnings("unchecked")
				List<TB_TMS_DRR_DIR_DTL> listn = (List<TB_TMS_DRR_DIR_DTL>) q1.list();

				String isuue_type = "";
				if (listn.size() > 0) {
					if (listn.get(0).getIssuetype() != null && !listn.get(0).getIssuetype().equals("")) {
						isuue_type = listn.get(0).getIssuetype();
					}
				}

				String hqlMAIN2 = "UPDATE TB_TMS_MVCR_PARTA_DTL set issue_type=:issue_type WHERE ba_no = :ba_no and issue_type = 'F'";
				Query queryMain2 = ses1.createQuery(hqlMAIN2);
				queryMain2.setParameter("issue_type", isuue_type);
				queryMain2.setParameter("ba_no", ba_no);
				queryMain2.executeUpdate();
				/* NITIN V4 17/11/2022 */

			}
			int cls = Integer.parseInt(classification);
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Query q1 = ses1.createQuery("from TB_TMS_BANUM_DIRCTRY where ba_no= :ba_no");
			q1.setParameter("ba_no", ba_no);
			@SuppressWarnings("unchecked")
			List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q1.list();

			BigInteger mct = list.get(0).getMct();
			int entry_yr;
			if (list.get(0).getYear_of_entry() == null || list.get(0).getYear_of_entry() == "") {
				String b = ba_no.substring(0, 2);
				if (Integer.parseInt(b) > 50) {
					entry_yr = Integer.parseInt("19" + b);
				} else {
					entry_yr = Integer.parseInt("20" + b);
				}
			} else {
				String yr_of_entry = list.get(0).getYear_of_entry();
				String[] yr_entry = yr_of_entry.split("-");
				entry_yr = Integer.parseInt(yr_entry[0]);
			}
			Query q2 = ses1.createQuery("from TB_TMS_MCT_MASTER where status = 'ACTIVE' and mct=:mct");
			q2.setParameter("mct", mct);
			@SuppressWarnings("unchecked")
			List<TB_TMS_MCT_MASTER> list1 = (List<TB_TMS_MCT_MASTER>) q2.list();
			int yr_service_ff = 0;
			int yr_service_nff = 0;
			int km_ff = 0;
			int km_nff = 0;
			if (list1.size() > 0) {
				yr_service_ff = list1.get(0).getYr_of_svc_for_discard();
				yr_service_nff = list1.get(0).getYr_of_service_nff();
				km_ff = list1.get(0).getKms_run_for_discard();
				km_nff = list1.get(0).getKms_run_for_discard_nff();
			}

			if (yr_service_nff != 0 || km_nff != 0) {
				Query q = ses1.createQuery(
						"select distinct type_force from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
				q.setParameter("sus_no", sus_no);
				String force = (String) q.uniqueResult();
				int type_force = Integer.parseInt(force);
				if (cls < 5) {
					int year_duration = year - entry_yr;
					if (type_force == 0) {
						if (yr_service_ff == 0) {
							yr_service_ff = yr_service_nff;
						}
						if (km_ff == 0) {
							km_ff = km_nff;
						}
						if (year_duration >= yr_service_ff && kms_run >= km_ff) {
							String hql1 = "UPDATE TB_TMS_BANUM_DIRCTRY set qfd='D' WHERE ba_no= :ba_no";
							Query query1 = ses1.createQuery(hql1);
							query1.setParameter("ba_no", ba_no);
							query1.executeUpdate();
						}
					} else {
						if (year_duration > yr_service_nff && kms_run > km_nff) {
							String hql1 = "UPDATE TB_TMS_BANUM_DIRCTRY set qfd='D' WHERE ba_no=:ba_no";
							Query query1 = ses1.createQuery(hql1);
							query1.setParameter("ba_no", ba_no);
							query1.executeUpdate();
						}
					}
				} else {
					String hql1 = "UPDATE TB_TMS_BANUM_DIRCTRY set qfd='' WHERE ba_no=:ba_no";
					Query query1 = ses1.createQuery(hql1);
					query1.setParameter("ba_no", ba_no);
					query1.executeUpdate();
				}
			}

			// TB_TMS_MVCR_PARTA_DTL_HISTORY mvcr_dtl_htr = new
			// TB_TMS_MVCR_PARTA_DTL_HISTORY();
			// mvcr_dtl_htr.setMvcr_parta_id(ListMvcr.get(0).getId());
			// mvcr_dtl_htr.setSus_no(ListMvcr.get(0).getSus_no());
			// mvcr_dtl_htr.setBa_no(ListMvcr.get(0).getBa_no());
			// mvcr_dtl_htr.setIssue_type(ListMvcr.get(0).getIssue_type());
			// mvcr_dtl_htr.setClassification(ListMvcr.get(0).getClassification());
			// mvcr_dtl_htr.setEpmnt_clasfctn(ListMvcr.get(0).getEpmnt_clasfctn());
			// mvcr_dtl_htr.setKms_run(ListMvcr.get(0).getKms_run());
			// mvcr_dtl_htr.setAuthrty_letter_no(ListMvcr.get(0).getAuthrty_letter_no());
			// mvcr_dtl_htr.setRemarks(ListMvcr.get(0).getRemarks());
			// mvcr_dtl_htr.setApp_rej_remarks(ListMvcr.get(0).getRej_remarks());
			// mvcr_dtl_htr.setStatus(0);
			// mvcr_dtl_htr.setMiso_rmk(ListMvcr.get(0).getMiso_rmk());
			// mvcr_dtl_htr.setSer_reason(ListMvcr.get(0).getSer_reason());
			// mvcr_dtl_htr.setSer_status(ListMvcr.get(0).getSer_status());
			// mvcr_dtl_htr.setCreation_by(username);
			// mvcr_dtl_htr.setCreation_date(new Date());
			// sessionMvcr.save(mvcr_dtl_htr);
			sessionMvcr.close();
			t2.commit();
			return "Data Updated Successfully.";
			/*
			 * } catch(Exception e) { ses1.getTransaction().rollback(); return null; }
			 * finally { ses1.close(); }
			 */
		}
	}

	@RequestMapping(value = "/getDocumentMVCR", method = RequestMethod.POST)
	public @ResponseBody List<UploadDocumentMVCR> getDocumentMVCR(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from UploadDocumentMVCR where sus_no =:sus_no and status='0' order by id desc");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<UploadDocumentMVCR> list = (List<UploadDocumentMVCR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// @RequestMapping(value = "/admin/uploadDocMVCR", method = RequestMethod.POST)
	// public @ResponseBody List<String> uploadDocMVCR(@RequestParam(value =
	// "uploadMvcr", required = false) MultipartFile uploadMvcr, HttpServletRequest
	// request,ModelMap model, HttpSession session, String sus) {
	// List<String> list = new ArrayList<>();
	// final long fileSizeLimit =
	// Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
	// if (uploadMvcr.getSize() > fileSizeLimit) {
	// list.add("File size should be less then 2 MB");
	// return list;
	// }
	// String uploadMvcrExt =
	// FilenameUtils.getExtension(uploadMvcr.getOriginalFilename()).toLowerCase();
	// if (!uploadMvcrExt.equals("zip") & !uploadMvcrExt.equals("rar")) {
	// list.add("Only *.zip or *.rar file extensions allowed");
	// return list;
	// }
	// String username = session.getAttribute("username").toString();
	// String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	// UploadDocumentMVCR upload = new UploadDocumentMVCR();
	//
	// int userid = Integer.parseInt(session.getAttribute("userId").toString());
	// String fname = "";
	// DateWithTimeStampController timestamp = new DateWithTimeStampController();
	// // code modify by Paresh on 05/05/2020
	// if (!uploadMvcr.isEmpty()) {
	// try {
	// byte[] bytes = uploadMvcr.getBytes();
	// CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
	// if(fileValidation.check_RAR_File(bytes) ||
	// fileValidation.check_ZIP_File(bytes)) {
	// String tmsFilePath = session.getAttribute("tmsFilePath").toString();
	// File dir = new File(tmsFilePath);
	// if (!dir.exists()) {
	// dir.mkdirs();
	// }
	// String filename = uploadMvcr.getOriginalFilename();
	// String extension = "";
	// int i = filename.lastIndexOf('.');
	// if (i >= 0) {
	// extension = filename.substring(i + 1);
	// }
	// fname = dir.getAbsolutePath() + File.separator +
	// timestamp.currentDateWithTimeStampString() + "_"
	// + userid + "_TMSDOC." + extension;
	// File serverFile = new File(fname);
	// BufferedOutputStream stream = new BufferedOutputStream(new
	// FileOutputStream(serverFile));
	// stream.write(bytes);
	// stream.close();
	// upload.setDocument(fname);
	//
	// upload.setCreatedby(username);
	// upload.setCreateddatetime(date);
	// upload.setSus_no(sus);
	// upload.setStatus("0");
	// Session session1 = HibernateUtil.getSessionFactory().openSession();
	// session1.beginTransaction();
	// session1.save(upload);
	// session1.getTransaction().commit();
	// session1.close();
	// list.add("Document Uploaded Successfully.");
	// }else {
	// list.add("Invalid File Format.");
	// }
	// } catch (Exception e) {
	// list.add("an Error ocurre file saving.");
	// }
	// }
	// return list;
	// }

	@RequestMapping(value = "/admin/uploadDocMVCR", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadDocMVCR(
			@RequestParam(value = "uploadMvcr", required = false) MultipartFile uploadMvcr, HttpServletRequest request,
			ModelMap model, HttpSession session, String sus) {
		List<String> list = new ArrayList<>();
		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
		if (uploadMvcr.getSize() > fileSizeLimit) {
			list.add("File size should be less then 2 MB");
			return list;
		}
		String uploadMvcrExt = FilenameUtils.getExtension(uploadMvcr.getOriginalFilename()).toLowerCase();
		if (!uploadMvcrExt.equals("zip") & !uploadMvcrExt.equals("rar")) {
			list.add("Only *.zip or *.rar file extensions allowed");
			return list;
		}
		String username = session.getAttribute("username").toString();
		String issue_summary = request.getParameter("issue_summary");
		String description_help = request.getParameter("description");
		String report_obsn = request.getParameter("report_obsn");

		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		UploadDocumentMVCR upload = new UploadDocumentMVCR();

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		// code modify by Paresh on 05/05/2020
		if (!uploadMvcr.isEmpty()) {
			try {
				byte[] bytes = uploadMvcr.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
					String tmsFilePath = session.getAttribute("tmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = uploadMvcr.getOriginalFilename();
					String extension = "";
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
					upload.setDocument(fname);

					upload.setCreatedby(username);
					upload.setCreateddatetime(date);
					upload.setSus_no(sus);
					upload.setStatus("0");
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					session1.beginTransaction();
					session1.save(upload);
					session1.getTransaction().commit();
					session1.close();
					String s = create_ticket_tms_b(upload, session, issue_summary, description_help, report_obsn);
					list.add("Document Uploaded Successfully.");
				} else {
					list.add("Invalid File Format.");
				}
			} catch (Exception e) {
				list.add("an Error ocurre file saving.");
			}
		}
		return list;
	}

	private String create_ticket_tms_b(UploadDocumentMVCR upload, HttpSession session, String issue_summary,
			String description_help, String report_obsn) {
		String msg = "";
		String user_id = "";
		String module_name = "TRANSPORT";
		String sub_module = "B VEHICLE";
		String screenname = "MVCR DETAILS";
		List<TB_LDAP_MODULE_MASTER> module = helpcntl.getModuleNameHelpDeskList(session);
		List<TB_LDAP_SUBMODULE_MASTER> submodule = helpcntl.getSubModuleList(session);
		List<TB_LDAP_SCREEN_MASTER> screen = helpcntl.getScreenList(session);
		HD_TB_BISAG_TICKET_GENERATE tickect = new HD_TB_BISAG_TICKET_GENERATE();
		for (TB_LDAP_MODULE_MASTER a : module) {
			if (a.getModule_name().equals("TRANSPORT")) {
				tickect.setModule(a.getId());

			}
		}
		for (TB_LDAP_SUBMODULE_MASTER b : submodule) {

			if (b.getSubmodule_name().equals("B VEHICLE")) {
				tickect.setSub_module(b.getId());
			}

		}
		for (TB_LDAP_SCREEN_MASTER c : screen) {
			if (c.getScreen_name().equals("MVCR DETAILS")) {
				tickect.setScreen_name(c.getId());
			}

		}
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		tickect.setCreated_by(upload.getCreatedby());
		tickect.setCreated_on(new Date());
		// tickect.setDescription();
		tickect.setHelp_topic(report_obsn);
		tickect.setIssue_summary(issue_summary);
		tickect.setDescription(description_help);

		tickect.setUserid(userId);
		tickect.setTicket_status("0");
		int ticket = helpcntl.getMaxticket();
		tickect.setTicket(ticket);
		tickect.setscreen_shot(upload.getDocument());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		if (roleAccess.equals("Unit")) {
			tickect.setSus_no(roleSusNo);
		}
		tickect.setCreated_on(new Date());
		// String description= module_name +" : " + sub_module + " : " + screen + "\n" +
		// " (i) "+N.getIssue_summary() + "\n" + " (ii) "+N.getDescription();
		String unit_name = notif.getUnitNameFromUserId(userId);
		String description = "Please Check the Ticket in Manage Ticket Screen";
		Integer module_id = tickect.getModule();
		List<String> list = notif.getUserIdForNotif(module_id);
		for (int i = 0; i < list.size(); i++) {
			user_id += list.get(i);
			if (i < list.size() - 1)
				user_id += ",";
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int N_id = (int) session1.save(tickect);

		if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
			Boolean d = notification.sendNotification_tms("TICKET GENERATED for " + module_name + " : " + sub_module
					+ " : " + screenname + " by " + unit_name, description, user_id, username, N_id);
		}
		// session1.save(tic_gen_noti);
		session1.getTransaction().commit();
		session1.close();
		return msg;
	}

	@RequestMapping(value = "/admin/getDownloadImageMVCR", method = RequestMethod.POST)
	public ModelAndView getDownloadImageMVCR(@ModelAttribute("id") int id, ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = getDocumentMVCRImg(id, session).get(0).getDocument();
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView("redirect:mvcr?msg=Sorry. The file you are looking for does not exist");
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
			String username = session.getAttribute("username").toString();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionUpdate.beginTransaction();
			String hqlUpdate = "update UploadDocumentMVCR u set u.status=:status,downloadby=:downloadby,downloadon=:downloadon where u.id =:id";
			sessionUpdate.createQuery(hqlUpdate).setString("status", "1").setInteger("id", id)
					.setString("downloadby", username).setString("downloadon", date).executeUpdate();
			tx.commit();
			sessionUpdate.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:mvcr?msg=Download Successfully.");
	}

	public List<UploadDocumentMVCR> getDocumentMVCRImg(int id, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("Unit")) {
			q = session.createQuery("from UploadDocumentMVCR where id=:id and sus_no=:sus_no");
			q.setParameter("id", id);
			q.setParameter("sus_no", roleSusNo);
		} else {
			q = session.createQuery("from UploadDocumentMVCR where id=:id");
			q.setParameter("id", id);
		}

		@SuppressWarnings("unchecked")
		List<UploadDocumentMVCR> list = (List<UploadDocumentMVCR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getExcelMVCR", method = RequestMethod.POST)
	public ModelAndView getExcelMVCR(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "sus_noex", required = false) String sus_no,
			@RequestParam(value = "unit_nameex", required = false) String unit_name,
			@RequestParam(value = "msg", required = false) String msg) {
	 	
	 	String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<List<String>> Excel = addmctDAO.getMVCRList(sus_no, roleAccess);

		List<String> TH = new ArrayList<String>();		
	
		TH.add("BA No");
		TH.add("Classification");
		TH.add("Kms Run");
		TH.add("Remarks");
		TH.add("Engine No");
		TH.add("Chassis No");
		TH.add("OH 1 (Due)");
		TH.add("OH 1 (Done)(dd-mm-yyyy)");
		TH.add("OH 2 (Due)");
		TH.add("OH 2 (Done)(dd-mm-yyyy)");
		TH.add("OH 3 (Due)");
		TH.add("OH 3 (Done)(dd-mm-yyyy)");
		TH.add("MR 1 (Due)");
		TH.add("MR 1 (Done)(dd-mm-yyyy)");
		TH.add("MR 2 (Due)");
		TH.add("MR 2 (Done)(dd-mm-yyyy)");
		TH.add("MR 3 (Due)");
		TH.add("MR 3 (Done)(dd-mm-yyyy)");
		
		TH.add("Mtd LauncherType");
		TH.add("Launcher Regn No");
		TH.add("Type of Msls Fired (Combat/ Practise/ Technical)");
		TH.add("Qty of Msls Fired (IN NUMERIC)");
		TH.add("Launcher OH (Due)");
		TH.add("Launcher OH (Done) (dd-mm-yyyy)");
		TH.add("Qty of Rds Fired (IN NUMERIC)");
		TH.add("Qty of Rds Misfired (IN NUMERIC)");
		TH.add("Type of Br Sys");
		TH.add("Br Sys Serviceability (S/UNSV)");
		TH.add("No of Passes (IN NUMERIC)");
		TH.add("Total Passes (IN NUMERIC)");
		TH.add("Type of Rdr");
		TH.add("Rdr Serviceability (S/UNSV)");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Export_B_Veh_Excel("L", TH, username, Excel), "userList", Excel);
	}
 
 @RequestMapping(value = "/exportTMSExcelActionMVCR", method = RequestMethod.POST)
	public ModelAndView exportTMSExcelActionMVCR(
			@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
	 	
	 	String numberOfColumns = request.getParameter("numberOfColumns");
	 	String ColumnNameExists = request.getParameter("ColumnNameExists");
	 	LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
	 	
		if (numberOfColumns.equals("32") && ColumnNameExists.equals("true")) {

		String table_name = request.getParameter("table_name");
		String sus_no = request.getParameter("sus_no3");
		int countrow = Integer.parseInt(request.getParameter("countrow"));
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<List<String>> Excel = addmctDAO.getFullMVCRList(sus_no, roleAccess);
		
		SimpleDateFormat formatter_dash = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

			for (int i = 0; i < countrow; i++) {
				
				String ba_no = request.getParameter("BA No_" + i);
				String Remarks = request.getParameter("Remarks_" + i);
				String month = new SimpleDateFormat("MM-yyyy").format(new Date());
					
				String oh_i_due1 = request.getParameter("OH 1 (Due)_" + i);
				String oh_i_due = null;
				if (oh_i_due1 != null && !oh_i_due1.trim().isEmpty() && oh_i_due1!="undefined"  && !oh_i_due1.trim().equals("undefined")) {
					oh_i_due = oh_i_due1;
				} else {
					oh_i_due = null; 
				}
				
				String oh_i_done1 = request.getParameter("OH 1 (Done) (dd-mm-yyyy)_" + i);
				String oh_i_done = null;
				if (oh_i_done1 != null && !oh_i_done1.trim().isEmpty() && oh_i_done1!="undefined"  && !oh_i_done1.trim().equals("undefined")) {
					oh_i_done = oh_i_done1;
				} else {
					oh_i_done = null; 
				}
					
				String oh_ii_due1 = request.getParameter("OH 2 (Due)_" + i);
				String oh_ii_due = null;
				if (oh_ii_due1 != null && !oh_ii_due1.trim().isEmpty() && oh_ii_due1!="undefined"  && !oh_ii_due1.trim().equals("undefined")) {
					oh_ii_due = oh_ii_due1;
				} else {
					oh_ii_due = null; 
				}
					
				String oh_ii_done1 = request.getParameter("OH 2 (Done) (dd-mm-yyyy)_" + i);
				String oh_ii_done = null;
				if (oh_ii_done1 != null && !oh_ii_done1.trim().isEmpty() && oh_ii_done1!="undefined"  && !oh_ii_done1.trim().equals("undefined")) {
					oh_ii_done = oh_ii_done1;
				} else {
					oh_ii_done = null; 
				}
					
				String oh_iii_due1 = request.getParameter("OH 3 (Due)_" + i);
				String oh_iii_due = null;
				if (oh_iii_due1 != null && !oh_iii_due1.trim().isEmpty() && oh_iii_due1!="undefined"  && !oh_iii_due1.trim().equals("undefined")) {
					oh_iii_due = oh_iii_due1;
				} else {
					oh_iii_due = null; 
				}
				
				String oh_iii_done1 = request.getParameter("OH 3 (Done) (dd-mm-yyyy)_" + i);
				String oh_iii_done = null;
				if (oh_iii_done1 != null && !oh_iii_done1.trim().isEmpty() && oh_iii_done1!="undefined"  && !oh_iii_done1.trim().equals("undefined")) {
					oh_iii_done = oh_iii_done1;
				} else {
					oh_iii_done = null; 
				}
				
				String mr_i_due1 = request.getParameter("MR 1 (Due)_" + i);
				String mr_i_due = null;
				if (mr_i_due1 != null && !mr_i_due1.trim().isEmpty() && mr_i_due1!="undefined"  && !mr_i_due1.trim().equals("undefined")) {
					mr_i_due = mr_i_due1;
				} else {
					mr_i_due = null; 
				}
					
				String mr_i_done1 = request.getParameter("MR 1 (Done) (dd-mm-yyyy)_" + i);
				String mr_i_done = null;
				if (mr_i_done1 != null && !mr_i_done1.trim().isEmpty() && mr_i_done1!="undefined"  && !mr_i_done1.trim().equals("undefined")) {
					mr_i_done = mr_i_done1;
				} else {
					mr_i_done = null; 
				}
				
				String mr_ii_due1 = request.getParameter("MR 2 (Due)_" + i);
				String mr_ii_due = null;
				if (mr_ii_due1 != null && !mr_ii_due1.trim().isEmpty() && mr_ii_due1!="undefined"  && !mr_ii_due1.trim().equals("undefined")) {
					mr_ii_due = mr_ii_due1;
				} else {
					mr_ii_due = null; 
				}
				
				String mr_ii_done1 = request.getParameter("MR 2 (Done) (dd-mm-yyyy)_" + i);
				String mr_ii_done = null;
				if (mr_ii_done1 != null && !mr_ii_done1.trim().isEmpty() && mr_ii_done1!="undefined"  && !mr_ii_done1.trim().equals("undefined")) {
					mr_ii_done = mr_ii_done1;
				} else {
					mr_ii_done = null; 
				}
				
				String mr_iii_due1 = request.getParameter("MR 3 (Due)_" + i);
				String mr_iii_due = null;
				if (mr_iii_due1 != null && !mr_iii_due1.trim().isEmpty() && mr_iii_due1!="undefined"  && !mr_iii_due1.trim().equals("undefined")) {
					mr_iii_due = mr_iii_due1;
				} else {
					mr_iii_due = null; 
				}
				
				String mr_iii_done1 = request.getParameter("MR 3 (Done) (dd-mm-yyyy)_" + i);
				String mr_iii_done = null;
				if (mr_iii_done1 != null && !mr_iii_done1.trim().isEmpty() && mr_iii_done1!="undefined"  && !mr_iii_done1.trim().equals("undefined")) {
					mr_iii_done = mr_iii_done1;
				} else {
					mr_iii_done = null; 
				}
					
				String mtd_launcher_type = request.getParameter("Mtd LauncherType_" + i);
				if(mtd_launcher_type == "" || mtd_launcher_type == null || mtd_launcher_type == "undefined" || mtd_launcher_type.trim().equals("undefined")) {
					mtd_launcher_type = null;
				}
				
				String launcher_regn_no = request.getParameter("Launcher Regn No_" + i);
				if(launcher_regn_no == "" || launcher_regn_no == null || launcher_regn_no == "undefined" || launcher_regn_no.trim().equals("undefined")) {
					launcher_regn_no = null;
				}
				
				String type_of_msls_fired = request.getParameter("Type of Msls Fired (Combat/ Practise/ Technical)_" + i);
				if(type_of_msls_fired == "" || type_of_msls_fired == null || type_of_msls_fired == "undefined" || type_of_msls_fired.trim().equals("undefined")) {
					type_of_msls_fired = null;
				}
				
				String QtyofMslsFiredStr = request.getParameter("Qty of Msls Fired (IN NUMERIC)_" + i);
				int QtyofMslsFiredAt = 0;

				try {
				    if (QtyofMslsFiredStr != null && !QtyofMslsFiredStr.isEmpty()) {
				    	QtyofMslsFiredAt = Integer.parseInt(QtyofMslsFiredStr); 
				        if (QtyofMslsFiredAt < 0) {
				        	model.put("msg", "Qty of Msls Fired Cannot Be Negative");
				        	return new ModelAndView("redirect:mvcr");
				        }
				    } 
				} catch (NumberFormatException e) {
					model.put("msg", "Qty of Msls Fired Can Only Be Numeric");
					return new ModelAndView("redirect:mvcr");
				}
				
				Integer qty_of_msls_fired = Integer.parseInt(request.getParameter("Qty of Msls Fired (IN NUMERIC)_" + i));
				
				String launcher_due1 = request.getParameter("Launcher OH (Due)_" + i);
				Date launcher_due = null;
				if (launcher_due1 != null && !launcher_due1.trim().isEmpty() && launcher_due1!="undefined"  && !launcher_due1.trim().equals("undefined")) {
					launcher_due = formatter_dash.parse(launcher_due1);
				} else {
					launcher_due = null; 
				}
				
				String launcher_done1 = request.getParameter("Launcher OH (Done) (dd-mm-yyyy)_" + i);
				Date launcher_done = null;
				if (launcher_done1 != null && !launcher_done1.trim().isEmpty() && launcher_done1!="undefined"  && !launcher_done1.trim().equals("undefined")) {
					launcher_done = formatter_dash.parse(launcher_done1);
				} else {
					launcher_done = null; 
				}
				
				String QtyofRdsFiredStr = request.getParameter("Qty of Rds Fired (IN NUMERIC)_" + i);
				int QtyofRdsFiredAt = 0;

				try {
				    if (QtyofRdsFiredStr != null && !QtyofRdsFiredStr.isEmpty()) {
				    	QtyofRdsFiredAt = Integer.parseInt(QtyofRdsFiredStr); 
				        if (QtyofRdsFiredAt < 0) {
				        	model.put("msg", "Qty of Rds Fired Cannot Be Negative");
				        	return new ModelAndView("redirect:mvcr");
				        }
				    } 
				} catch (NumberFormatException e) {
					model.put("msg", "Qty of Rds Fired Can Only Be Numeric");
					return new ModelAndView("redirect:mvcr");
				}
				
				Integer qty_of_rds_fired = Integer.parseInt(request.getParameter("Qty of Rds Fired (IN NUMERIC)_" + i));
				
				String QtyofRdsMisfiredStr = request.getParameter("Qty of Rds Misfired_" + i);
				int QtyofRdsMisfiredAt = 0;

				try {
				    if (QtyofRdsMisfiredStr != null && !QtyofRdsMisfiredStr.isEmpty()) {
				    	QtyofRdsMisfiredAt = Integer.parseInt(QtyofRdsMisfiredStr); 
				        if (QtyofRdsMisfiredAt < 0) {
				        	model.put("msg", "Qty of Rds Misfired Cannot Be Negative");
				        	return new ModelAndView("redirect:mvcr");
				        }
				    } 
				} catch (NumberFormatException e) {
					model.put("msg", "Qty of Rds Misfired Can Only Be Numeric");
					return new ModelAndView("redirect:mvcr");
				}
				
				Integer qty_of_rds_misfired = Integer.parseInt(request.getParameter("Qty of Rds Misfired (IN NUMERIC)_" + i));
				
				String type_of_br_sys = request.getParameter("Type of Br Sys_" + i);
				if(type_of_br_sys == "" || type_of_br_sys == null || type_of_br_sys == "undefined" || type_of_br_sys.trim().equals("undefined")) {
					type_of_br_sys = null;
				}
				
				String br_sys_ser = request.getParameter("Br Sys Serviceability (S/ UNSV)_" + i);
				if(br_sys_ser == "" || br_sys_ser == null || br_sys_ser == "undefined" || br_sys_ser.trim().equals("undefined")) {
					br_sys_ser = null;
				}
				
				String NoofPassesStr = request.getParameter("No of Passes (IN NUMERIC)_" + i);
				int NoofPassesAt = 0;

				try {
				    if (NoofPassesStr != null && !NoofPassesStr.isEmpty()) {
				    	NoofPassesAt = Integer.parseInt(NoofPassesStr); 
				        if (NoofPassesAt < 0) {
				        	model.put("msg", "No of Passes Cannot Be Negative");
				        	return new ModelAndView("redirect:mvcr");
				        }
				    } 
				} catch (NumberFormatException e) {
					model.put("msg", "No of Passes Can Only Be Numeric");
					return new ModelAndView("redirect:mvcr");
				}
				
				Integer no_of_passes = Integer.parseInt(request.getParameter("No of Passes (IN NUMERIC)_" + i));
				
				String TotalPassesStr = request.getParameter("Total Passes_" + i);
				int TotalPassesAt = 0;

				try {
				    if (TotalPassesStr != null && !TotalPassesStr.isEmpty()) {
				    	TotalPassesAt = Integer.parseInt(TotalPassesStr); 
				        if (TotalPassesAt < 0) {
				        	model.put("msg", "Total Passes Cannot Be Negative");
				        	return new ModelAndView("redirect:mvcr");
				        }
				    } 
				} catch (NumberFormatException e) {
					model.put("msg", "Total Passes Can Only Be Numeric");
					return new ModelAndView("redirect:mvcr");
				}
				
				Integer total_passes = Integer.parseInt(request.getParameter("Total Passes (IN NUMERIC)_" + i));
				String type_of_rdr = request.getParameter("Type of Rdr_" + i);
				if(type_of_rdr == "" || type_of_rdr == null || type_of_rdr == "undefined" || type_of_rdr.trim().equals("undefined")) {
					type_of_rdr = null;
				}
				
				String rdr_ser = request.getParameter("Rdr Serviceability (S/ UNSV)_" + i);
				if(rdr_ser == "" || rdr_ser == null || rdr_ser == "undefined" || rdr_ser.trim().equals("undefined")) {
					rdr_ser = null;
				}
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				Query qry = session1.createQuery("update TB_TMS_MVCR_PARTA_DTL set \r\n" + 
						"ba_no=:a1,oh_i_done=:a3,oh_ii_done=:a5,oh_iii_done=:a7,mr_i_due=:a8,mr_i_done=:a9,mr_ii_due=:a10,mr_ii_done=:a11,mr_iii_due=:a12,\r\n" + 
						"mr_iii_done=:a13,mtd_launcher_type=:a14,launcher_regn_no=:a15,type_of_msls_fired=:a16,qty_of_msls_fired=:a17,launcher_due=:a18,launcher_done=:a19,\r\n" + 
						"qty_of_rds_fired=:a20,qty_of_rds_misfired=:a21,type_of_br_sys=:a22,br_sys_ser=:a23,no_of_passes=:a24,total_passes=:a25,type_of_rdr=:a26,rdr_ser=:a27,Remarks=:a28 where ba_no=:a1");
				
				qry.setParameter("a1", ba_no);
//				qry.setParameter("a2", oh_i_due);
				qry.setParameter("a3", oh_i_done);
//				qry.setParameter("a4", oh_ii_due);
				qry.setParameter("a5", oh_ii_done);
//				qry.setParameter("a6", oh_iii_due);
				qry.setParameter("a7", oh_iii_done);
				qry.setParameter("a8", mr_i_due);
				qry.setParameter("a9", mr_i_done);
				qry.setParameter("a10", mr_ii_due);
				qry.setParameter("a11", mr_ii_done);
				qry.setParameter("a12", mr_iii_due);
				qry.setParameter("a13", mr_iii_done);
				
				qry.setParameter("a14", mtd_launcher_type);
				qry.setParameter("a15", launcher_regn_no);
				qry.setParameter("a16", type_of_msls_fired);
				qry.setParameter("a17", qty_of_msls_fired);
				qry.setParameter("a18", launcher_due);
				qry.setParameter("a19", launcher_done);
				qry.setParameter("a20", qty_of_rds_fired);
				qry.setParameter("a21", qty_of_rds_misfired);
				qry.setParameter("a22", type_of_br_sys);
				qry.setParameter("a23", br_sys_ser);
				qry.setParameter("a24", no_of_passes);
				qry.setParameter("a25", total_passes);
				qry.setParameter("a26", type_of_rdr);
				qry.setParameter("a27", rdr_ser);
				qry.setParameter("a28", Remarks);
				
				
				
				TB_TMS_MVCR_PARTA_DTL_HISTORY mvcrh  =  new  TB_TMS_MVCR_PARTA_DTL_HISTORY();
				
				String id = Excel.get(0).get(0);
				String issue_type = Excel.get(0).get(2);
				String classification = Excel.get(0).get(3);
				String authrty_letter_no = Excel.get(0).get(4);
				String creation_by = Excel.get(0).get(5);
				String creation_date = Excel.get(0).get(6);
				String app_rej_remarks = Excel.get(0).get(7);
				String status = Excel.get(0).get(8);
				String miso_rmk = Excel.get(0).get(9);
				String ser_status = Excel.get(0).get(10);
				String ser_reason = Excel.get(0).get(11);
				String approved_by = Excel.get(0).get(12);
				String epmnt_clasfctn = Excel.get(0).get(15);
				String kms_run = Excel.get(0).get(16);
				String remarks = Excel.get(0).get(17);
				
				mvcrh.setMvcr_parta_id(Integer.parseInt(id));
				mvcrh.setSus_no(sus_no);
				mvcrh.setIssue_type(issue_type);
				mvcrh.setClassification(classification);
				mvcrh.setEpmnt_clasfctn(epmnt_clasfctn);
				mvcrh.setKms_run(Integer.parseInt(kms_run));
				mvcrh.setAuthrty_letter_no(authrty_letter_no);
				mvcrh.setRemarks(remarks);
				mvcrh.setCreation_by(creation_by);
				Date creation_dt = formatter_dash.parse(creation_date);
				mvcrh.setCreation_date(creation_dt);
				mvcrh.setApp_rej_remarks(app_rej_remarks);
				mvcrh.setStatus(Integer.parseInt(status));
				mvcrh.setMiso_rmk(miso_rmk);
				mvcrh.setSer_reason(ser_reason);
				mvcrh.setSer_status(ser_status);
				mvcrh.setApproved_by(approved_by);
				mvcrh.setBa_no(ba_no);
				mvcrh.setOh_i_due(oh_i_due);
				mvcrh.setOh_ii_due(oh_ii_due);
				mvcrh.setOh_iii_due(oh_iii_due);
				mvcrh.setOh_i_done(oh_i_done);
				mvcrh.setOh_ii_done(oh_ii_due);
				mvcrh.setOh_iii_done(oh_iii_due);
				
				mvcrh.setMr_i_due(mr_i_due);
				mvcrh.setMr_ii_due(mr_ii_due);
				mvcrh.setMr_iii_due(mr_iii_due);
				
				mvcrh.setMr_i_done(mr_i_done);
				mvcrh.setMr_ii_done(mr_ii_done);
				mvcrh.setMr_iii_done(mr_iii_done);
				
				mvcrh.setMtd_launcher_type(mtd_launcher_type);
				mvcrh.setLauncher_regn_no(launcher_regn_no);
				mvcrh.setType_of_msls_fired(type_of_msls_fired);
				mvcrh.setQty_of_msls_fired(qty_of_msls_fired);
				mvcrh.setLauncher_due(launcher_due);
				mvcrh.setLauncher_done(launcher_done);
				mvcrh.setQty_of_rds_fired(qty_of_rds_fired);
				mvcrh.setQty_of_rds_misfired(qty_of_rds_misfired);
				mvcrh.setType_of_br_sys(type_of_br_sys);
				mvcrh.setBr_sys_ser(br_sys_ser);
				mvcrh.setNo_of_passes(no_of_passes);
				mvcrh.setTotal_passes(total_passes);
				mvcrh.setType_of_rdr(type_of_rdr);
				mvcrh.setRdr_ser(rdr_ser);
				mvcrh.setSave_date(timestamp);
				session1.save(mvcrh);
				
				
			qry.executeUpdate();
			tx.commit();
			session1.close();
					
			}

			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mvcr");
		}
		else {
			model.put("msg", "Please Enter Valid Excel File For B Vehicle");
			return new ModelAndView("redirect:mvcr");
		}
	}


}