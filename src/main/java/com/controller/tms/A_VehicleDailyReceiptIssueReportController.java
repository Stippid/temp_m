package com.controller.tms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.ReportsDAO;
import com.dao.tms.AVehDailyReceiptIssuereportDAO;
import com.dao.tms.AVehDailyReceiptIssuereportDAOImpl;
import com.dao.tms.DailyReceiptIssuereportDAO;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_CENSUS_DRR_DIR_MAIN;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_EMAR_REPORT_MAIN;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class A_VehicleDailyReceiptIssueReportController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	DailyReceiptIssuereportDAO notify;
	@Autowired
	AVehDailyReceiptIssuereportDAO dDao;
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ReportsDAO  UOD;
	ValidationController validation = new ValidationController();
	Psg_CommonController comm = new Psg_CommonController();
	NotificationController notification = new NotificationController();
	@RequestMapping(value = "/admin/aveh_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView Aveh_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("aveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		String year = new SimpleDateFormat("yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("year", year);
		Mmap.put("msg", msg);
		return new ModelAndView("aveh_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/getACensusBaNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensusBaNoList(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select " 
				+ "	distinct ba.ba_no  " 
				+ "	from  " 
				+ "	TB_TMS_BANUM_DIRCTRY ba  "
				+ "	where  " 
				+ "	ba.ba_no not in (select distinct b.ba_no  "
				+ "				from TB_TMS_BANUM_DIRCTRY b  " 
				+ "				where   "
				+ "				ba_no in(select distinct d.ba_no  "
				+ "					from TB_TMS_CENSUS_DRR_DIR_DTL d  "
				+ "					where upper(d.ba_no) like :ba_no " 
				+ "					order by d.ba_no)  "
				+ "					and b.status = 'Active' and b.veh_cat='A' and upper(b.ba_no) like :ba_no order by b.ba_no) and ba.status ='Active' and ba.veh_cat='A' and upper(ba.ba_no) like :ba_no order by ba.ba_no")
				.setMaxResults(10);

		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getACensusVehSusBaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensusVehSusBaNoDtlList(String sus_no, String ba_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select " + "	distinct ba_no " + "	from " + "TB_TMS_CENSUS_DRR_DIR_DTL "
				+ "where "
				+ "ba_no not in (select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no=:sus_no and (issue_condition ='2' or issue_condition ='3' or issue_condition ='4') ) and "
				+ "sus_no=:sus_no and issue_condition='1'  and status='1'  and upper(ba_no) like :ba_no "
				+ "and ba_no in (select ba_no from TB_TMS_BANUM_DIRCTRY where status != 'NonArmy' and veh_cat='A') order by ba_no")
				.setMaxResults(10);
		q.setParameter("sus_no", sus_no);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
		
		System.err.println("ba1-"+q.toString());
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		if (list.size() != 0) {
			FinalList.add(enckey + "1bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getACensusVehSusBaNoDtlListCase2AgaiIssue", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensusVehSusBaNoDtlListCase2AgaiIssue(String sus_no, String ba_no,
			HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = dDao.getACensusVehSusBaNoDtlListCase2AgaiIssue(sus_no, ba_no);
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		if (list.size() != 0) {
			FinalList.add(enckey + "4btjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getACensuscase3BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensuscase3BaNoDtlList(String unit_sus_no, String ba_no,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct ba_no from TB_TMS_CENSUS_RETURN where ba_no not in(select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='3' and status='0') and sus_no=:unit_sus_no  and upper(ba_no) like :ba_no order by ba_no")
				.setMaxResults(10);
		q.setParameter("unit_sus_no", unit_sus_no);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
		System.err.println("ba2-"+q.toString());
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getACensuscase3KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensuscase3KmList(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct vehcl_kms_run from TB_TMS_CENSUS_RETURN where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getACensuscase4BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensuscase4BaNoDtlList(String sus_no, String ba_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"	select distinct ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where ba_no not in(select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL  where (issue_condition ='4' and (status = '0' or status = '1')) )  and ba_no not in (select ba_no from TB_TMS_CENSUS_RETURN  where upper(ba_no) like :ba_no) "
						+ " and ba_no not in(select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL  where (issue_condition ='2' and status = '0') )"
						+ " and sus_no=:sus_no and issue_condition='3' and status='1' and upper(ba_no) like :ba_no order by ba_no")
				.setMaxResults(10);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
		q.setParameter("sus_no", sus_no);
		System.err.println("ba3-"+q.toString());
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		if (list.size() != 0) {
			FinalList.add(enckey + "8drjug==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getACensuscase6KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getACensuscase6KmList(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct kms_run from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='5' and ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/admin/addVehADailyReceiveIssueReport", method = RequestMethod.POST)
	public @ResponseBody String AddVehADailyReceiveIssueReport(HttpServletRequest request, HttpSession sessionA,
			String drr_dir_ser_no, String sus_no, String unit_name, String a, String issue_against_rio,
			String other_agency, String unit_sus_no, String unit_sus_name, String ba_no, int kms_run, String cl,
			String remarks, String authority, String issue_type, String date_report)
			throws HibernateException, ParseException {
		String username = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("aveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return "Access Denied.";
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String msg = null;
		if (!a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4")) {
			return "Please Choose Receive / Issue";
		} else if (drr_dir_ser_no.equals("") || drr_dir_ser_no.equals("null") || drr_dir_ser_no.equals(null)) {
			return "Please Enter the DRR/DIR Ser No";
		} else if (drr_dir_ser_no != null
				& validation.authority_noLength(request.getParameter("drr_dir_ser_no")) == false) {
			return validation.drr_dir_ser_noMSG;
		} else if (sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null)) {
			return "Please Enter the SUS No";
		} else if (sus_no != null & validation.sus_noLength(request.getParameter("sus_no")) == false) {
			return validation.depot_sus_noMSG;
		} else if (unit_name != null & validation.checkUnit_nameLength(request.getParameter("unit_name")) == false) {
			return validation.depotunit_nameMSG;
		} else if (issue_against_rio != null
				& validation.country_of_originTMSLength(request.getParameter("issue_against_rio")) == false) {
			return validation.issue_against_rioMSG;
		} else if (other_agency != null
				& validation.checkAnimalMasterLength(request.getParameter("other_agency")) == false) {
			return validation.other_agencyMSG;
		} else if (unit_sus_no != "" & unit_sus_no != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			return validation.unit_sus_noMSG;
		} else if (unit_sus_name != null
				& validation.checkUnit_nameLength(request.getParameter("unit_sus_name")) == false) {
			return validation.unit_nameMSG;
		} else if (ba_no.equals("") || ba_no.equals("null") || ba_no.equals(null)) {
			return "Please Enter the BA No";
		} else if (ba_no != null & validation.ba_noLength(request.getParameter("ba_no")) == false) {
			return validation.ba_noMSG;
		} else if (validation.checkCosSecLength(request.getParameter("kms_run")) == false) {
			return validation.kms_runMSG;
		} else if (authority.equals("") || authority.equals("null") || authority.equals(null)) {
			return "Please Enter the Authority No";
		} else if (authority != null & validation.authority_noLength(request.getParameter("authority")) == false) {
			return validation.authority_noMSG;
		} else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			return validation.remarksMSGTMS;
		} else {
			TB_TMS_CENSUS_DRR_DIR_DTL census_dtl = new TB_TMS_CENSUS_DRR_DIR_DTL();
			TB_TMS_CENSUS_DRR_DIR_MAIN census_main = new TB_TMS_CENSUS_DRR_DIR_MAIN();
			census_main.setCreation_by(username);
			census_main.setCreation_date(new Date());
			census_main.setDt_of_retrn(new Date());
			census_main.setStatus("0");
			census_main.setDrr_dir_ser_no(drr_dir_ser_no);
			census_main.setSus_no(sus_no);
			census_main.setVersion_no(0);
			census_dtl.setCreation_by(username);
			census_dtl.setCreation_date(new Date());
			census_dtl.setStatus("0");
			census_dtl.setDrr_dir_ser_no(drr_dir_ser_no);
			census_dtl.setSus_no(sus_no);
			census_dtl.setIssue_condition(a);
			census_dtl.setIssue_agnst_rio(issue_against_rio);
			census_dtl.setUnit_sus_no(unit_sus_no);
			census_dtl.setBa_no(ba_no);
			census_dtl.setKms_run(kms_run);
			census_dtl.setClassification(cl);
			census_dtl.setRemarks(remarks);
			census_dtl.setAuthority(authority);
			census_dtl.setOther_agency(other_agency);
			census_dtl.setIssue_type(issue_type);

			Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			sessionHql.beginTransaction();
			if (a.equals("1")) {
				if (dDao.ifExistSusAndSerDtl(a, ba_no) == true) {
					return "Failure dtl";
				} else {
					sessionHql.save(census_main);
					sessionHql.save(census_dtl);
					sessionHql.getTransaction().commit();
				}
			}
			if (a.equals("2")) {
				sessionHql.save(census_dtl);
				sessionHql.getTransaction().commit();
			}
			if (a.equals("3")) {
				sessionHql.save(census_dtl);
				sessionHql.getTransaction().commit();
			}
			if (a.equals("4")) {
				if (dDao.ifExistSusAndSerDtl(a, ba_no) == true) {
					return "Failure dtl";
				} else {
					sessionHql.save(census_dtl);
					sessionHql.getTransaction().commit();
					sessionHql.close();
				}
			}
			msg = "Success";
			if (a.equals("3")) {
				List<Map<String, Object>> notit=UOD.getformationorbat(unit_sus_no);
		    	String cmd_sus_no = "";
		    	String corps_sus_no  = ""; 
		    	String div_sus_no = "";
		    	String bde_sus_no = "";
		    	
		    	if(notit.get(0).get("cmd")!=null) {
		    		cmd_sus_no = notit.get(0).get("cmd").toString();			
		    	}
		    	if(notit.get(0).get("corps")!=null) {
		    		corps_sus_no = notit.get(0).get("corps").toString();			
		    	}
		    	if(notit.get(0).get("div")!=null) {
		    		div_sus_no = notit.get(0).get("div").toString();			
		    	}
		    	if(notit.get(0).get("bde")!=null) {
		    		bde_sus_no = notit.get(0).get("bde").toString();			
		    	}
				////for unit
		    	
		    	 if (unit_sus_no != null   && !unit_sus_no.trim().equals("")) {
		    	      List<UserLogin> userlist = comm.getUseridlist(unit_sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i).getUserId();
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i).getUserId();
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                      String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	      }
		    	      if (cmd_sus_no != null   && !cmd_sus_no.trim().equals("")) {
		    	      if( cmd_sus_no != sus_no && !cmd_sus_no.trim().equals(unit_sus_no)) {
		    	      List<UserLogin> userlist = comm.getUseridlist(cmd_sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i).getUserId();
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i).getUserId();
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	      }
		    	      }
		    	      if (div_sus_no != null   && !div_sus_no.trim().equals("")) { 
		    	      if( div_sus_no != sus_no && !div_sus_no.trim().equals(unit_sus_no)) {
		    	      List<UserLogin> userlist = comm.getUseridlist(div_sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i).getUserId();
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i).getUserId();
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	      }
		    	      }
		    	      if (bde_sus_no != null   && !bde_sus_no.trim().equals("")) {
		    	      if( bde_sus_no != sus_no && !bde_sus_no.trim().equals(unit_sus_no)) {
		    	      
		    	      List<UserLogin> userlist = comm.getUseridlist(bde_sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i).getUserId();
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i).getUserId();
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	      }
		    	      }
		    	      if (corps_sus_no != null   && !corps_sus_no.trim().equals("")) {
		    	      if( corps_sus_no != sus_no && !corps_sus_no.trim().equals(unit_sus_no)) {
		    	                      List<UserLogin> userlist = comm.getUseridlist(corps_sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i).getUserId();
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i).getUserId();
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	      }
		    	      }
		    	      //for depo
		    	      if (sus_no != null   && !sus_no.trim().equals("")) {
		    	      List<String> userlist = notify.getUseridBysusfordepo(sus_no);
		    	                      String user_id = "";
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i);
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i);
		    	                }
		    	                
		    	}
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                }
		    	                }
		    	      
		    	  	//for line dte
		    	      if (unit_sus_no != null   && !unit_sus_no.trim().equals("")) {
		    	      List<String> userlist = notify.getUseridBysusforlinedte(unit_sus_no);
		    	                      String user_id = "";
		    	                      
		    	                for (int i = 0; i < userlist.size(); i++) {
		    	                if(i==0) {
		    	                user_id += userlist.get(i);
		    	                }
		    	                
		    	                else {
		    	                user_id += ","+userlist.get(i);
		    	                }
		    	                
		    	}
		    	               
		    	                
		    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		    	    //String per_no = notit.get(0).get("personnel_no").toString();
		    	                String title = "DRR Initiated" ;
		    	                              String description = "1* DRR Initiated, pending for approval" ;
		    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
		    	                	 }
		    	               
		    	      }   
		    	      
		    	      
		    	}
			return msg;
		}
	}

	@RequestMapping(value = "/admin/search_aveh_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView Search_aveh_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_aveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
	}
//111
	@RequestMapping(value = "/admin/search_drr_a_Veh", method = RequestMethod.POST)
	public ModelAndView search_drr_a_Veh(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "frm_date1", required = false) String from_date,
			@RequestParam(value = "curr_date", required = false) String curr_date) throws ParseException {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_aveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();

		if (from_date != "" && curr_date != "") {
			if (alltms.CompareDate(from_date, curr_date) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
			}
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (!status.equals("")) {
			Mmap.put("status", status);
		}
		if (!sus_no.equals("")) {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.depot_susMSG);
				return new ModelAndView("redirect:search_aveh_daily_receipt_issue_report");
			}
			Mmap.put("sus_no", sus_no);
		}
		if (!from_date.equals("")) {
			Mmap.put("from_date", from_date);

		}
		if (!curr_date.equals("")) {
			Mmap.put("curr_date", curr_date);
		}

		ArrayList<List<String>> list = dDao.getsearch_drr_a_Veh(status, sus_no, from_date, curr_date, roleType);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("search_status", "0");
		return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/admin/view_aveh_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView View_aveh_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("view_aveh_daily_receipt_issue_reportTiles");
	}
///111
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/AVehViewReceiveIssue", method = RequestMethod.POST)
	public ModelAndView AVehViewReceiveIssue(@ModelAttribute("viewSerNo") String viewSerNo, HttpSession sessionA,
			String viewStatus, String viewDate, String viewStatus1, String viewSus, String viewfrom_dt,
			String viewto_dt, String unit_name123,String search_sus_no, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_aveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(viewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery(
				"from TB_TMS_CENSUS_DRR_DIR_DTL where drr_dir_ser_no=:drr_dir_ser_no  and status=:status and CAST(creation_date as date)>=:viewDate");
		q.setParameter("drr_dir_ser_no", viewSerNo).setParameter("status", viewStatus).setParameter("viewDate", date);
		List<TB_TMS_CENSUS_DRR_DIR_DTL> list = (List<TB_TMS_CENSUS_DRR_DIR_DTL>) q.list();
		session.getTransaction().commit();
		session.close();

		ArrayList<List<String>> list1 = dDao.getApprovedBA_NoFromDRRAVehicle(viewSerNo, viewStatus, viewDate, viewSus,
				roleType);
		AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
		if (list1.size() != 0) {
			model.put("view_aveh_daily_ReceiptIssue_ReportCMD", list1);
			model.put("ba_no", list1.get(0).get(1));
			model.put("sus_no", list1.get(0).get(2));
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(list1.get(0).get(2), sessionA).get(0));
			model.put("issue_condition", list1.get(0).get(5));
			model.put("status", list1.get(0).get(6));
			model.put("roleType", roleType);

		}
		model.put("viewSerNo", viewSerNo);
		model.put("s_viewStatus1", viewStatus1);
		model.put("s_viewSus", viewSus);
		model.put("s_viewfrom_dt", viewfrom_dt);
		model.put("s_viewto_dt", viewto_dt);
		model.put("search_sus_no", search_sus_no);
		
		return new ModelAndView("view_aveh_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/admin/AVehUpdateReceiveIssue")
	public ModelAndView UpdateReceiveIssue(@ModelAttribute("updateid") int updateid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_CENSUS_DRR_DIR_DTL where id=:id");
		q.setParameter("id", updateid);
		TB_TMS_CENSUS_DRR_DIR_DTL upid = (TB_TMS_CENSUS_DRR_DIR_DTL) q.list().get(0);
		tx.commit();

		model.put("edit_aveh_daily_ReceiptIssue_ReportCMD", upid);
		return new ModelAndView("edit_aveh_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/edit_aveh_daily_ReceiptIssue_ReportAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Edit_aveh_daily_ReceiptIssue_ReportAction(
			@ModelAttribute("edit_aveh_daily_ReceiptIssue_ReportCMD") TB_TMS_CENSUS_DRR_DIR_DTL updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session11) {
		model.put("updateid", updateid.getId());
		String roleType = session11.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getParameter("sus_no") != null & validation.sus_noLength(request.getParameter("sus_no")) == false) {
			model.put("msg", validation.depot_sus_noMSG);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		} else if (request.getParameter("other_agency") != null
				& validation.checkAnimalMasterLength(request.getParameter("other_agency")) == false) {
			model.put("msg", validation.other_agencyMSG);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		} else if (request.getParameter("unit_sus_no") != "" & request.getParameter("unit_sus_no") != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			model.put("msg", validation.unit_sus_noMSG);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		} else if (request.getParameter("ba_no") != null
				& validation.ba_noLength(request.getParameter("ba_no")) == false) {
			model.put("msg", validation.ba_noMSG);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		} else if (request.getParameter("authority") != null
				& validation.authority_noLength(request.getParameter("authority")) == false) {
			model.put("msg", validation.authority_noMSG);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		} else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			model.put("msg", validation.remarksMSGTMS);
			return new ModelAndView("redirect:AVehUpdateReceiveIssue");
		}

		String username = session11.getAttribute("username").toString();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update TB_TMS_CENSUS_DRR_DIR_DTL set sus_no=:sus_no, other_agency=:other_agency, unit_sus_no=:unit_sus_no, ba_no=:ba_no, remarks=:remarks, authority=:authority, modify_by=:modify_by, modify_date=:modify_date,issue_type=:issue_type, status ='0' where id=:id";

		String rdissueCond = request.getParameter("hdrdissuecond");
		String sus_no = updateid.getSus_no();
		String drr_dir_ser_no = updateid.getDrr_dir_ser_no();
		String ba_no = request.getParameter("ba_no_curr");
		String issue_type = updateid.getIssue_type();
		String ba_no1 = updateid.getBa_no();
		if (ba_no.equals(ba_no1)) {
			Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
					.setString("other_agency", updateid.getOther_agency())
					.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("ba_no", updateid.getBa_no())
					.setString("remarks", updateid.getRemarks()).setString("authority", updateid.getAuthority())
					.setString("modify_by", username).setDate("modify_date", new Date())
					.setString("issue_type", issue_type).setInteger("id", updateid.getId());
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if (rowCount > 0) {
				model.put("msg", "Data Updated Successfully.");
			} else {
				model.put("msg", "Data not Updated.");
			}
		} else if (rdissueCond.equals("2")) {
			Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
					.setString("other_agency", updateid.getOther_agency())
					.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("ba_no", updateid.getBa_no())
					.setString("remarks", updateid.getRemarks()).setString("authority", updateid.getAuthority())
					.setString("modify_by", username).setDate("modify_date", new Date())
					.setString("issue_type", issue_type).setInteger("id", updateid.getId());
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if (rowCount > 0) {
				model.put("msg", "Data Updated Successfully.");
			} else {
				model.put("msg", "Data not Updated.");
			}
		} else {
			if (dDao.ifExistSusAndSerAndBANo(sus_no, drr_dir_ser_no, ba_no1, rdissueCond) == true) {
				model.put("msg", "Current BA No Already Exists");
				return new ModelAndView("redirect:AVehUpdateReceiveIssue?updateid=" + updateid.getId());
			} else {
				Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
						.setString("other_agency", updateid.getOther_agency())
						.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("ba_no", updateid.getBa_no())
						.setString("remarks", updateid.getRemarks()).setString("authority", updateid.getAuthority())
						.setString("modify_by", username).setDate("modify_date", new Date())
						.setString("issue_type", issue_type).setInteger("id", updateid.getId());
				int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if (rowCount > 0) {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data not Updated.");
				}
			}
		}

		return new ModelAndView("redirect:search_aveh_daily_receipt_issue_report");
	}

	@RequestMapping(value = "/admin/AVehDeleteReceiveIssue", method = RequestMethod.POST)
	public ModelAndView AVehDeleteReceiveIssue(@ModelAttribute("deleteid") String deleteid,
			String issue_condition_delete, String bano_delete, String sus_no_delete, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		Session case1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction C2 = case1.beginTransaction();
		TB_TMS_CENSUS_DRR_DIR_DTL getDr2 = (TB_TMS_CENSUS_DRR_DIR_DTL) case1.get(TB_TMS_CENSUS_DRR_DIR_DTL.class,
				Integer.parseInt(deleteid));
		String drr_dir_ser_no = getDr2.getDrr_dir_ser_no();

		if (issue_condition_delete.equals("1")) {
			Session sessioncase1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx2 = sessioncase1.beginTransaction();
			Query query = sessioncase1.createQuery(
					"select count(sus_no) AS ba_count from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no = :sus_no and drr_dir_ser_no = :drr_dir_ser_no and issue_condition='1'");
			query.setParameter("sus_no", sus_no_delete).setParameter("drr_dir_ser_no", drr_dir_ser_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) query.list();
			Long result = (Long) query.uniqueResult();
			tx2.commit();
			sessioncase1.close();

			if (result == 1) {
				Session sessioncase2 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = sessioncase2.beginTransaction();
				Query query3 = sessioncase2.createQuery(
						"delete from TB_TMS_CENSUS_DRR_DIR_MAIN where sus_no = :sus_no and drr_dir_ser_no = :drr_dir_ser_no");
				query3.setParameter("sus_no", sus_no_delete).setParameter("drr_dir_ser_no", drr_dir_ser_no);
				query3.executeUpdate();
				tx3.commit();
				sessioncase2.close();
			}
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from TB_TMS_CENSUS_DRR_DIR_DTL where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", Integer.parseInt(deleteid));
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
		} else {
		}
		return new ModelAndView("redirect:search_aveh_daily_receipt_issue_report");
	}
//111
	
	
	@RequestMapping(value = "/admin/AVehApprovedReceiveIssue", method = RequestMethod.POST)
	public ModelAndView setApprovedFootTransport(@ModelAttribute("sus_no_aprove") String sus_no_aprove,
			String ser_no_approve, int issue_condition, String viewStatus11,String viewSus1, String viewfrom_dt1,String viewto_dt1,String search_status, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA, HttpServletRequest request) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") && !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}
		String username = sessionA.getAttribute("username").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no_aprove = roleSusNo;
		}

		if (issue_condition == 1) {
			Session s5 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx5 = s5.beginTransaction();
			String h5 = "update TB_TMS_CENSUS_DRR_DIR_MAIN c set c.status = :status, c.approved_by = :approved_by, c.approve_date = :approve_date where c.sus_no = :sus_no and c.drr_dir_ser_no = :drr_dir_ser_no and c.status = '0'";
			s5.createQuery(h5).setString("status", "1").setString("approved_by", username)
					.setTimestamp("approve_date", new Date()).setString("sus_no", sus_no_aprove)
					.setString("drr_dir_ser_no", ser_no_approve).executeUpdate();
			tx5.commit();
			s5.close();
		}
		if (issue_condition == 2) {
			TB_TMS_CENSUS_RETURN_MAIN census_rtn_main = new TB_TMS_CENSUS_RETURN_MAIN();
			Session sessionGet = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionGet.beginTransaction();
			Query q = sessionGet.createQuery(
					"from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no and status=:status and issue_condition=:issue_condition");
			q.setParameter("sus_no", sus_no_aprove).setParameter("drr_dir_ser_no", ser_no_approve)
					.setParameter("status", "0").setParameter("issue_condition", String.valueOf(issue_condition));
			@SuppressWarnings("unchecked")
			List<TB_TMS_CENSUS_DRR_DIR_DTL> list = (List<TB_TMS_CENSUS_DRR_DIR_DTL>) q.list();
			
			tx.commit();
			if(!list.isEmpty()) {
				Session sessioncase2 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = sessioncase2.beginTransaction();
				Query query3 = sessioncase2.createQuery(
						"update TB_TMS_CENSUS_DRR_DIR_DTL set type_of_stock=:type_of_stock where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no  and issue_condition=:issue_condition");
				query3.setParameter("sus_no", sus_no_aprove).setParameter("type_of_stock", "3").setParameter("drr_dir_ser_no", ser_no_approve).setParameter("issue_condition", String.valueOf(issue_condition));
				query3.executeUpdate();
				tx3.commit();
				sessioncase2.close();
			}
			for (int i = 0; i < list.size(); i++) {
				String ba_no = list.get(i).getBa_no();
				Session sessionCheck = HibernateUtil.getSessionFactory().openSession();
				Transaction txCheck = sessionCheck.beginTransaction();
				Query qCheck = sessionCheck.createQuery("select id from TB_TMS_CENSUS_RETURN where ba_no=:ba_no");
				qCheck.setParameter("ba_no", ba_no);
				@SuppressWarnings("unchecked")
				List<TB_TMS_CENSUS_RETURN> CenRtnCount = (List<TB_TMS_CENSUS_RETURN>) qCheck.list();
				txCheck.commit();

				if (CenRtnCount.size() == 0) {
					TB_TMS_CENSUS_RETURN census_rtn = new TB_TMS_CENSUS_RETURN();
					census_rtn.setSus_no(list.get(i).getUnit_sus_no());
					census_rtn.setDate_of_cens_retrn(new Date());
					census_rtn.setDt_of_submsn(new Date());
					census_rtn.setBa_no(ba_no);
					census_rtn.setVehcl_classfctn("1");
					census_rtn.setVehcl_kms_run(list.get(i).getKms_run());
					census_rtn.setTrack_kms(0);
					census_rtn.setEngine_type("");
					census_rtn.setEngine_kms_run("0");
					census_rtn.setEngine_hrs_run("0");
					census_rtn.setAux_engine_run("");
					census_rtn.setAux_engn_clasfctn("");
					census_rtn.setAux_engn_hrs_run(0);
					census_rtn.setMain_gun_type("");
					census_rtn.setMain_gun_efc("");
					census_rtn.setMain_gun_qr("");
					census_rtn.setSec_gun_type("");
					census_rtn.setMain_radio_set_nomcltr("");
					census_rtn.setMain_radio_set("");
					census_rtn.setMain_radio_set_condn("");
					census_rtn.setUnit_remarks(list.get(i).getRemarks());
					census_rtn.setStatus("1");
					census_rtn.setCreation_by(username);
					census_rtn.setCreation_date(new Date());
					census_rtn.setVeh_km_run_period("");
					census_rtn.setAux_type("");
					census_rtn.setMain_radio_set_uh("");

					Session mvcrba = HibernateUtilNA.getSessionFactory().openSession();
					mvcrba.flush();
					Transaction mvcrtx = mvcrba.beginTransaction();
					mvcrba.save(census_rtn);
					mvcrtx.commit();
					mvcrba.close();

					Session sessionemar_main = HibernateUtil.getSessionFactory().openSession();
					Transaction txemar_main = sessionemar_main.beginTransaction();
					Query q_emar_main = sessionemar_main
							.createQuery("select id from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:sus_no");
					q_emar_main.setParameter("sus_no", list.get(i).getUnit_sus_no());
					@SuppressWarnings("unchecked")
					List<TB_TMS_CENSUS_RETURN_MAIN> getlistexistsus_no = (List<TB_TMS_CENSUS_RETURN_MAIN>) q_emar_main
							.list();
					txemar_main.commit();

					if (getlistexistsus_no.size() == 0) {
						census_rtn_main.setSus_no(list.get(i).getUnit_sus_no());
						census_rtn_main.setStatus("1");
						census_rtn_main.setCreation_by(username);
						census_rtn_main.setCreation_date(new Date());
						census_rtn_main.setVersion_no(0);
						census_rtn_main.setApprove_date(new Date());
						census_rtn_main.setDt_of_submsn(new Date());
						census_rtn_main.setDate_of_cens_retrn(new Date());

						Session session = HibernateUtil.getSessionFactory().openSession();
						session.flush();
						Transaction txn = session.beginTransaction();
						session.save(census_rtn_main);
						txn.commit();
						session.close();
					}
				}
			}
		}
		if (issue_condition == 3) {
			Session sessionGet2 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx2 = sessionGet2.beginTransaction();
			Query q = sessionGet2.createQuery(
					"delete from TB_TMS_CENSUS_RETURN where ba_no in(select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no)");
			q.setParameter("sus_no", sus_no_aprove).setParameter("drr_dir_ser_no", ser_no_approve);
			q.executeUpdate();
			tx2.commit();
			sessionGet2.close();

			Session sessionemar_maindt = HibernateUtil.getSessionFactory().openSession();
			Transaction txemar_main = sessionemar_maindt.beginTransaction();
			Query q_emar_main = sessionemar_maindt
					.createQuery("select id from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:sus_no and status = '1'");
			q_emar_main.setParameter("sus_no", sus_no_aprove);
			@SuppressWarnings("unchecked")
			List<TB_TMS_CENSUS_RETURN_MAIN> getlistexistsus_no = (List<TB_TMS_CENSUS_RETURN_MAIN>) q_emar_main.list();
			txemar_main.commit();
			sessionemar_maindt.close();

			if (getlistexistsus_no.size() == 0) {
				Session sessionGetdt = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = sessionGetdt.beginTransaction();
				Query qdt = sessionGetdt.createQuery("delete from TB_TMS_CENSUS_RETURN_MAIN where sus_no =:sus_no");
				qdt.setParameter("sus_no", sus_no_aprove);
				qdt.executeUpdate();
				tx3.commit();
				sessionGetdt.close();
				
				
				
			}
		}

		if (issue_condition == 4) {

			Session sessionGet3 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx3 = sessionGet3.beginTransaction();
			Query q = sessionGet3.createQuery(
					"from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no");
			q.setParameter("sus_no", sus_no_aprove).setParameter("drr_dir_ser_no", ser_no_approve);
			@SuppressWarnings("unchecked")
			List<TB_TMS_CENSUS_DRR_DIR_DTL> list = (List<TB_TMS_CENSUS_DRR_DIR_DTL>) q.list();
			tx3.commit();

			for (int i = 0; i < list.size(); i++) {
				String unit_sus_no = list.get(i).getUnit_sus_no();
				String n_ba_no = list.get(i).getBa_no();

				if (unit_sus_no.equals("")) {
					Session sessionGet5 = HibernateUtilNA.getSessionFactory().openSession();
					Transaction tx5 = sessionGet5.beginTransaction();
					Query query5 = sessionGet5
							.createQuery("update TB_TMS_BANUM_DIRCTRY c set c.status = :status where c.ba_no=:ba_no");
					query5.setString("status", "Auctioned Out").setString("ba_no", n_ba_no);
					query5.executeUpdate();
					tx5.commit();
					sessionGet5.close();
				} else {
					Session sessionGet5 = HibernateUtilNA.getSessionFactory().openSession();
					Transaction tx5 = sessionGet5.beginTransaction();
					Query query5 = sessionGet5
							.createQuery("update TB_TMS_BANUM_DIRCTRY c set c.status = :status where c.ba_no=:ba_no");
					query5.setString("status", "War Trophy").setString("ba_no", n_ba_no);
					query5.executeUpdate();
					tx5.commit();
					sessionGet5.close();
				}
			}
			sessionGet3.close();
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_CENSUS_DRR_DIR_DTL c set c.status = :status, c.approved_by = :approved_by, c.approved_date = :approved_date where c.sus_no =:sus_no and c.drr_dir_ser_no =:drr_dir_ser_no and c.status = '0'";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("approved_by", username)
				.setTimestamp("approved_date", new Date()).setString("sus_no", sus_no_aprove)
				.setString("drr_dir_ser_no", ser_no_approve).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			model.put("msg", "Approved Successfully.");
		/*	if (issue_condition == 3) {
				//List<TB_TMS_CENSUS_DRR_DIR_DTL> list = (List<TB_TMS_CENSUS_DRR_DIR_DTL>)app .list();
				///for depo  
			 List<UserLogin> userlist = comm.getUseridlist(sus_no_aprove);
	            String user_id = "";
	         		for (int i = 0; i < userlist.size(); i++) {
	         			if(i==0) {
	         				user_id += 	userlist.get(i).getUserId();
	         			}
	         			
	         			else {
	         				user_id += ","+userlist.get(i).getUserId();
	         			}
	         					
						}
	         		
				   //String per_no = notit.get(0).get("personnel_no").toString();
		            String title = "Release Issue Order Generated" ;
	                String description = "back roll of Vehical wise RIO issued" ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
			}*/
			if (issue_condition == 3) {

                List<Map<String, String>> list2 = dDao.get_A_vech_daily(sus_no_aprove, ser_no_approve);
                
                int k;
                int total_count=0;
                for (k = 0; k < list2.size(); k++) {
                        String unit_sus_no = list2.get(k).get("unit_sus_no");
                        String nomen = list2.get(k).get("std_nomclature");
                        int        count = Integer.parseInt(list2.get(k).get("counts").toString());
                        List<Map<String, Object>> notit = UOD.getformationorbat(unit_sus_no);
                        String cmd_sus_no = "";
                        String corps_sus_no = "";
                        String div_sus_no = "";
                        String bde_sus_no = "";        
//                        String user_id = "";
                        total_count += count;

                        if (notit.get(0).get("cmd") != null) {
                                cmd_sus_no = notit.get(0).get("cmd").toString();
                        }
                        if (notit.get(0).get("corps") != null) {
                                corps_sus_no = notit.get(0).get("corps").toString();
                        }
                        if (notit.get(0).get("div") != null) {
                                div_sus_no = notit.get(0).get("div").toString();
                        }
                        if (notit.get(0).get("bde") != null) {
                                bde_sus_no = notit.get(0).get("bde").toString();
                        }

                      
                       
                        // for unit
                        if (unit_sus_no != null && !unit_sus_no.trim().equals("")) {
                                List<UserLogin> userlist = comm.getUseridlist(unit_sus_no);
           String user_id = "";
                                for (int i = 0; i < userlist.size(); i++) {
                                	if (i == 0) {
                                                user_id += userlist.get(i).getUserId();
                                        }

                                        else {
                                                user_id += "," + userlist.get(i).getUserId();
                                        }


                                }
                                if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
                                    String title = "A Veh DRR Approved";
                                    String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                    + " DRR Approved";
                                    Boolean d = notification.sendNotification(title, description, user_id, username);
                            }

                        }

                        // for cmd
                        if (cmd_sus_no != null && !cmd_sus_no.trim().equals("")) {
                                if (cmd_sus_no != unit_sus_no && !cmd_sus_no.trim().equals(unit_sus_no)) {

                                        List<UserLogin> userlist = comm.getUseridlist(cmd_sus_no);
                                        String user_id = "";
                                        for (int i = 0; i < userlist.size(); i++) {
                                        	if (i == 0) {
                                                        user_id += userlist.get(i).getUserId();
                                                }

                                                else {
                                                        user_id += "," + userlist.get(i).getUserId();
                                                }

                                        }
                                        if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
                                            String title = "A Veh DRR Approved";
                                            String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                            + " DRR Approved";
                                            Boolean d = notification.sendNotification(title, description, user_id, username);
                                    }
                                }
                        }
                        if (div_sus_no != null && !div_sus_no.trim().equals("")) {
                                if (div_sus_no != unit_sus_no && !div_sus_no.trim().equals(unit_sus_no)) {
                                        List<UserLogin> userlist = comm.getUseridlist(div_sus_no);
                   String user_id = "";
                                        for (int i = 0; i < userlist.size(); i++) {
                                        	if (i == 0) {
                                                        user_id += userlist.get(i).getUserId();
                                                }

                                                else {
                                                        user_id += "," + userlist.get(i).getUserId();
                                                }

                                        }
                                        if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
                                            String title = "A Veh DRR Approved";
                                            String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                            + " DRR Approved";
                                            Boolean d = notification.sendNotification(title, description, user_id, username);
                                    }
//       
                                }
                        }
                        if (bde_sus_no != null && !bde_sus_no.trim().equals("")) {
                                if (bde_sus_no != unit_sus_no && !bde_sus_no.trim().equals(unit_sus_no)) {
                                        List<UserLogin> userlist = comm.getUseridlist(bde_sus_no);
                       String user_id = "";
                                        for (int i = 0; i < userlist.size(); i++) {
                                        	if (i == 0) {
                                                        user_id += userlist.get(i).getUserId();
                                                }

                                                else {
                                                        user_id += "," + userlist.get(i).getUserId();
                                                }

                                        }
                                        if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
                                            String title = "A Veh DRR Approved";
                                            String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                            + " DRR Approved";
                                            Boolean d = notification.sendNotification(title, description, user_id, username);
                                    }
//                
                                }
                        }
                        if (corps_sus_no != null && !corps_sus_no.trim().equals("")) {
                                if (corps_sus_no != unit_sus_no && !corps_sus_no.trim().equals(unit_sus_no)) {
                                        List<UserLogin> userlist = comm.getUseridlist(corps_sus_no);
        String user_id = "";
                                        for (int i = 0; i < userlist.size(); i++) {
                                                if (i == 0) {
                                                        user_id += userlist.get(i).getUserId();
                                                }

                                                else {
                                                        user_id += "," + userlist.get(i).getUserId();
                                                }

                                        }
                                        
                                        if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
                                            String title = "A Veh DRR Approved";
                                            String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                            + " DRR Approved";
                                            Boolean d = notification.sendNotification(title, description, user_id, username);
                                    }

                                }
                        }
//                        if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
//                                String title = "C Veh DRR Approved";
//                                String description = "" + count + " *" + list2.get(k).get("std_nomclature")
//                                                + " DRR Approved";
//                                Boolean d = notification.sendNotification(title, description, user_id, username);
//                        }
                        
                        //depo notification
                        if (sus_no_aprove != null && !sus_no_aprove.trim().equals("")) {
                        String userid = "";
                        List<UserLogin> userlist = comm.getUseridlist(sus_no_aprove);
                        for (int i = 0; i < userlist.size(); i++) {
                                if (i == 0) {
                                        userid += userlist.get(i).getUserId();
                                }

                                else {
                                        userid += "," + userlist.get(i).getUserId();
                                }

                        }
                        if (userid != "" && !userid.equals(null) && !userid.equals("")) {
                                String title = "A Veh DRR Received";
                                String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                + " DRR Received";
                                Boolean d = notification.sendNotification(title, description, userid, username);
                        }
                        }
                }
                
			 }
			
			
			
			
			
			
			
		} else {
			model.put("msg", "Approved Not Successfully.");
		}
		//KAJAL DATA 2
		
		model.put("sus_no", viewSus1);
		model.put("b_viewStatus1", viewStatus11);
		model.put("from_date", viewfrom_dt1);
		model.put("curr_date", viewto_dt1);
		model.put("search_status", "1");
		return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
	}    
    
    
    
    
}
