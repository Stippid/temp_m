package com.controller.tms;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.FlushMode;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.ReportsDAO;
import com.dao.tms.B_VehTmsDailyRecieptDao;
import com.dao.tms.CVehDailyReceiptIssuereportDAO;
import com.dao.tms.CVehDailyReceiptIssuereportDAOImpl;
import com.dao.tms.DailyReceiptIssuereportDAO;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_EMAR_REPORT_MAIN;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
import com.controller.notification.NotificationController;
import com.controller.psg.Master.Psg_CommonController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class C_VehicleDailyReceiptIssueReportController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	Date date1 = new Date();
	String curdate = new SimpleDateFormat("dd-MM-yyyy").format(date1);
	@Autowired
	DailyReceiptIssuereportDAO notify;
	@Autowired
	CVehDailyReceiptIssuereportDAO cDao = new CVehDailyReceiptIssuereportDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	ReportsDAO  UOD;
	ValidationController validation = new ValidationController();
	Psg_CommonController comm = new Psg_CommonController();

	
	@Autowired
	B_VehTmsDailyRecieptDao  b_dao;
	
	C_VehicleController c = new C_VehicleController();
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	NotificationController notification = new NotificationController();

	@RequestMapping(value = "/admin/cveh_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView Cveh_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String type_of_veh,HttpServletRequest request)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("date", curdate);
		Mmap.put("getMotherDepoList", c.getMotherDepoList(type_of_veh, session));
		return new ModelAndView("cveh_daily_receipt_issue_reportTiles");
	}

	// FREE STOCK AUTOCOMPLETE FOR BA_NO CASE 1 CONDITION
	@RequestMapping(value = "/getCvehBaNoAutoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCvehBaNoAutoList(String ba_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select " + "	distinct ba.ba_no  " + "	from  " + "	TB_TMS_BANUM_DIRCTRY ba  "
				+ "	where  " + "	ba.ba_no not in (select distinct b.ba_no  "
				+ "				from TB_TMS_BANUM_DIRCTRY b  " + "				where   "
				+ "				ba_no in(select distinct d.em_no as ba_no "
				+ "					from TB_TMS_EMAR_DRR_DIR_DTL d  "
				+ "					where upper(d.em_no) like :ba_no " + "					order by d.em_no)  "
				+ "					and b.status = 'Active' and b.veh_cat='C' and upper(b.ba_no) like :ba_no order by b.ba_no) and ba.status ='Active' and ba.veh_cat='C' and upper(ba.ba_no) like :ba_no order by ba.ba_no")
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	// FREE STOCK AUTOCOMPLETE IN ISSUE TO UNIT CASE 2 CONDITION FOR BA_NO
	@RequestMapping(value = "/getCVehSusBaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehSusBaNoDtlList(String sus_no, String ba_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		String em_no = ba_no;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select " + "	distinct em_no " + "	from " + "TB_TMS_EMAR_DRR_DIR_DTL "
				+ "where "
				+ "em_no not in (select em_no from TB_TMS_EMAR_DRR_DIR_DTL where sus_no=:sus_no and (issue_condition ='2' or issue_condition ='3' or issue_condition ='4') ) and "
				+ "em_no not in (select em_no from TB_TMS_EMAR_REPORT ) and "
				+ "sus_no=:sus_no and issue_condition='1'  and status='1'  and upper(em_no) like :em_no "
				+ "and em_no in (select ba_no from TB_TMS_BANUM_DIRCTRY where status != 'NonArmy' and veh_cat='C') order by em_no")
				.setMaxResults(10);

		q.setParameter("sus_no", sus_no);
		q.setParameter("em_no", ba_no.toUpperCase() + "%");
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
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	// REAGAIN ISSUE TO UNIT AUTOCOMPLETE IN ISSUE TO UNIT CASE 2 CONDITION FOR
	// BA_NO
	@RequestMapping(value = "/getCVehSusBaNoDtlListCase2AgaiIssue", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehSusBaNoDtlListCase2AgaiIssue(String sus_no, String ba_no,
			HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = cDao.getCEmarVehSusBaNoDtlListCase2AgaiIssue(sus_no);
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
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	// CASE 3 CONDITION RECEIVE FROM UNIT AUTOCOMPLETE FOR BA_NO
	@RequestMapping(value = "/getCVehcase3BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehcase3BaNoDtlList(String unit_sus_no, String ba_no,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct em_no from TB_TMS_EMAR_REPORT where em_no not in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition='3' and status='0') and sus_no=:unit_sus_no  and upper(em_no) like :em_no order by em_no");

		q.setParameter("em_no", ba_no.toUpperCase() + "%");
		q.setParameter("unit_sus_no", unit_sus_no);
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

	// AUCTION CASE 4 CONDITION FOR THE AUTOCOMPLETE OF BA_NO
	@RequestMapping(value = "/getCVehcase4BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehcase4BaNoDtlList(String sus_no, String ba_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("	select " + "distinct em_no " + "from " + "TB_TMS_EMAR_DRR_DIR_DTL " + "where "
				+ "em_no not in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL  where (issue_condition ='2' and status = '0') ) and "
				+ "em_no not in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL  where issue_condition ='4' and (status='1' or status='0') ) and "
				+ "em_no not in(select em_no from TB_TMS_EMAR_REPORT ) and sus_no=:sus_no  and issue_condition='3' and "
				+ "status='1' and " + "upper(em_no) like :ba_no order by em_no").setMaxResults(10);
		q.setParameter("sus_no", sus_no);
		q.setParameter("ba_no", ba_no + "%");
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
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	// TO GET kms_run FROM BA_NO IN EDIT PAGE OF DRR FOR CASE 3
	@RequestMapping(value = "/getCVehcase3KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehcase3KmList(String em_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct total_km_run from TB_TMS_EMAR_REPORT where em_no=:em_no");
		q.setParameter("em_no", em_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// TO GET kms_run FROM BA_NO IN EDIT PAGE OF DRR FOR CASE 4
	@RequestMapping(value = "/getCVehcase4KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCVehcase4KmList(String em_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct kms_run from TB_TMS_EMAR_DRR_DIR_DTL where em_no=:em_no and issue_condition='3' and status='1'");
		q.setParameter("em_no", em_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// INSERTION OF DRR PAGE
	//26-01-1994
	@RequestMapping(value = "/admin/addVehCDailyReceiveIssueReport", method = RequestMethod.POST)
	public @ResponseBody String addDailyReceiveIssueReport(ModelMap model, HttpServletRequest request,
			HttpSession session, String drr_dir_ser_no, String sus_no, String a, String issue_against_rio,
			String other_agency, String unit_sus_no, String unit_sus_name, String em_no, BigInteger kms_run, String cl,
			String remarks, String authority, String auction_amount, String issue_type) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report", roleid);

		if (val == false) {
			return "Access Denied.";
		}
		TB_TMS_EMAR_DRR_DIR_DTL dtl = new TB_TMS_EMAR_DRR_DIR_DTL();
		TB_TMS_EMAR_DRR_DIR_MAIN main = new TB_TMS_EMAR_DRR_DIR_MAIN();
		TB_TMS_BANUM_DIRCTRY b = new TB_TMS_BANUM_DIRCTRY();

		String username = session.getAttribute("username").toString();
		String msg = null;
		if (!a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4")) {
			return "Please Choose Receive / Issue";
		}

		else if (request.getParameter("issue_agnst_rio") != "" & issue_against_rio != null
				& validation.country_of_originTMSLength(request.getParameter("issue_against_rio")) == false) {
			return validation.issue_against_rioMSG;
		}

		else if (other_agency != "" & other_agency != null
				& validation.authority_noLength(request.getParameter("other_agency")) == false) {
			return validation.other_agencyMSG;
		}

		else if (drr_dir_ser_no.equals("") || drr_dir_ser_no == "" || drr_dir_ser_no.equals(null)
				|| drr_dir_ser_no == null || drr_dir_ser_no.equals("")) {
			return "Please Enter the DRR/DIR Ser No";
		}

		else if (drr_dir_ser_no != null
				& validation.authority_noLength(request.getParameter("drr_dir_ser_no")) == false) {
			return validation.drr_dir_ser_noMSG;
		}

		else if (sus_no.equals("") || sus_no == "" || sus_no.equals("null") || sus_no == null || sus_no.equals(null)) {
			return "Please Enter the SUS NO";
		}

		else if (unit_sus_no != "" & unit_sus_no != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			return validation.sus_noMSG;
		}

		else if (unit_sus_name != "" & request.getParameter("unit_sus_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_sus_name")) == false) {
			return validation.unit_nameMSG;
		}

		else if (em_no.equals("") || em_no == "" || em_no.equals(null) || em_no == null || em_no.equals("null")) {
			return "Please Enter the EM No";
		}

		else if (em_no != null & validation.ba_noLength(request.getParameter("em_no")) == false) {
			return validation.em_noMSG;
		}

		else if (validation.checkCosSecLength(request.getParameter("kms_run")) == false) {
			return validation.kms_runMSG;
		}

		else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			return validation.remarksMSGTMS;
		}

		else if (authority.equals("") || authority == "" || authority.equals(null) || authority == null
				|| authority.equals("null")) {
			return "Please Enter the Authority No";
		}

		else if (authority != null & validation.authority_noLength(request.getParameter("authority")) == false) {
			return validation.authorityMSG;
		}

		else {
			Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			sessionHql.beginTransaction();
			try {
				main.setCreation_by(username);
				main.setCreation_date(new Date());
				main.setDt_of_retrn(new Date());
				main.setStatus("0");
				main.setDrr_dir_ser_no(drr_dir_ser_no);
				main.setSus_no(sus_no);
				
				dtl.setCreation_by(username);
				dtl.setCreation_date(new Date());
				dtl.setStatus("0");
				dtl.setDrr_dir_ser_no(drr_dir_ser_no);
				dtl.setSus_no(sus_no);
				dtl.setIssue_condition(a);
				dtl.setIssue_agnst_rio(issue_against_rio);
				dtl.setOther_agency(other_agency);
				dtl.setUnit_sus_no(unit_sus_no);
				dtl.setEm_no(em_no);
				dtl.setKms_run(kms_run);
				dtl.setRemarks(remarks);
				dtl.setAuthority(authority);
				dtl.setOther_agency(other_agency);
				dtl.setIssue_type(issue_type);

				if (a.equals("1")) {
					/* NITIN V4 15/11/2022 */

					/*
					 * if (cDao.ifExistSusAndSer(sus_no, drr_dir_ser_no) == true) { return
					 * "Failure dtl"; } else {
					 */

					sessionHql.save(main);
					sessionHql.save(dtl);
					sessionHql.getTransaction().commit();
					sessionHql.close();
					msg = "Success";

					// }

					/* NITIN V4 15/11/2022 */
				} else {
					if (a.equals("2")) {
						dtl.setClassification(cl);
						sessionHql.save(dtl);
						sessionHql.getTransaction().commit();
					}
					if (a.equals("3")) {
						if (cl != "" || !cl.equals("")) {
							dtl.setClassification(cl);
						} else {
							Session session1 = HibernateUtil.getSessionFactory().openSession();
							Transaction tx = session1.beginTransaction();
							Query q = session1.createQuery(
									"select classification  from TB_TMS_EMAR_REPORT where em_no=:em_no and sus_no=:sus_no");
							q.setParameter("em_no", em_no);
							q.setParameter("sus_no", unit_sus_no);
							@SuppressWarnings("unchecked")
							List<String> list = (List<String>) q.list();
							tx.commit();
							session1.close();
							if (list.size() != 0) {
								dtl.setClassification(list.get(0));
							}
						}
						sessionHql.save(dtl);
						sessionHql.getTransaction().commit();
					}
					if (a.equals("4")) {
						Session s = HibernateUtil.getSessionFactory().openSession();
						Transaction tx = s.beginTransaction();
						Query q = s.createQuery("select coalesce(purchas_cost,'0') from Tms_Banum_Req_Child where ba_no=:em_no");
						q.setParameter("em_no", em_no);
						String list = q.uniqueResult().toString();
						tx.commit();

						Long q1 = (long) Integer.parseInt(list);
						if (cDao.ifExistSusAndSerDtl(a, em_no) == true) {
							return "Failure dtl";
						} else {

							int amnt = 0;
							String amntstr = request.getParameter("auction_amount").toString().replaceAll(",", "");
							if (auction_amount != "" && !auction_amount.equals("")) {
								amnt = Integer.parseInt(amntstr);
							}

							if (amntstr != "0" & validation.checkCosSecLength(amntstr) == false) {
								return validation.auction_amountMSG;
							}
							//if (amnt < q1) {
								dtl.setApprove_date(new Date());
								dtl.setApprove_date(new Date());
								dtl.setApprove_date(new Date());

								Session s1 = HibernateUtil.getSessionFactory().openSession();
								Transaction tx1 = s1.beginTransaction();
								String q11 = "update TB_TMS_BANUM_DIRCTRY set auction_amount=:auction_amount,status='Active' where ba_no=:ba_no";
								int updatedEntities = s1.createQuery(q11)
										.setString("auction_amount", String.valueOf(amnt)).setString("ba_no", em_no).executeUpdate();
								sessionHql.save(dtl);
								sessionHql.getTransaction().commit();
								sessionHql.close();
								tx1.commit();
								s1.close();
								
								msg = "Success";
								/*
								 * } else { String msg1 = "AuctionAmount"; return msg1; }
								 */
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
			    	                      String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
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
			    	                String title = "DRR Initiated" ;
			    	                              String description = "1 * DRR Initiated, pending for approval" ;
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
			    	                }
			    	      }
			    	      
			    	      
			    	      
			    	}
				}
			} catch (Exception e) {
				
				sessionHql.getTransaction().rollback();

			}
			return msg;
		}
		// return msg;
	}

	// SEARCH PAGE FOR DRR
	@RequestMapping(value = "/admin/cveh_daily_receipt_issue_report_search", method = RequestMethod.GET)
	public ModelAndView Cveh_daily_receipt_issue_report_serach(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("cveh_daily_receipt_issue_report_SearchTiles");
	}

	// SEARCH METHOD OF DRR PAGE
	@RequestMapping(value = "/admin/search_drr_c_Veh", method = RequestMethod.POST)
	public ModelAndView search_drr_c_Veh(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "frm_date1", required = false) String from_date,
			@RequestParam(value = "curr_date", required = false) String curr_date) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String roleType = sessionA.getAttribute("roleType").toString();

		if (from_date != "" && curr_date != "") {
			if (alltms.CompareDate(from_date, curr_date) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
			}
		}
		if (!status.equals("")) {
			Mmap.put("status", status);
		}
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.depot_sus_noMSG);
				return new ModelAndView("redirect:cveh_daily_receipt_issue_report_search");
			}
			Mmap.put("sus_no", sus_no);
		}
		if (!from_date.equals("")) {
			Mmap.put("from_date", from_date);
		}
		if (!curr_date.equals("")) {
			Mmap.put("curr_date", curr_date);

		}
		ArrayList<List<String>> list = cDao.getsearch_drr_c_Veh(status, sus_no, from_date, curr_date, roleType);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);

		return new ModelAndView("cveh_daily_receipt_issue_report_SearchTiles");
	}

	// VIEW SCREEN OF DRR WHICH COMES AFTER DOING SEARCH OF DRR
	@RequestMapping(value = "/admin/cveh_daily_receipt_issue_report_view", method = RequestMethod.GET)
	public ModelAndView Cveh_daily_receipt_issue_report_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("cveh_daily_receipt_issue_report_ViewTiles");
	}

	// VIEW DATA OF DRR BA_NO WISE FOR APPROVAL/EDIT/DELETE
	@RequestMapping(value = "/admin/CVehViewReceiveIssue", method = RequestMethod.POST)
	public ModelAndView CVehViewReceiveIssue(@ModelAttribute("cviewSerNo") String cviewSerNo, String cviewStatus,
			String cviewDate, String viewStatus1, String viewSus, String viewfrom_dt, String viewto_dt, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session1) {

		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		String roleType = session1.getAttribute("roleType").toString();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(cviewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Query q = session.createQuery(
				"from TB_TMS_EMAR_DRR_DIR_DTL where drr_dir_ser_no=:drr_dir_ser_no and status=:status and CAST(creation_date as date) >= :viewDate and sus_no =:sus_no ");
		q.setParameter("drr_dir_ser_no", cviewSerNo).setParameter("status", cviewStatus).setParameter("viewDate", date)
				.setParameter("sus_no", viewSus);
		@SuppressWarnings("unchecked")
		List<TB_TMS_EMAR_DRR_DIR_DTL> list = (List<TB_TMS_EMAR_DRR_DIR_DTL>) q.list();
		ArrayList<List<String>> list1 = cDao.getApprovedBA_NoFromDRRCVehicle(cviewSerNo, cviewStatus, cviewDate,
				viewSus, roleType);
		AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
		if (list1.size() != 0) {
			model.put("cveh_daily_receipt_issue_report_viewCMD", list1);
			model.put("ba_no", list1.get(0).get(1));
			model.put("sus_no", list1.get(0).get(2));
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(list1.get(0).get(2), session1).get(0));
			model.put("issue_condition", list1.get(0).get(5));
			model.put("status", list1.get(0).get(6));
			model.put("roleType", roleType);
		}
		model.put("sus_no", viewSus);
		model.put("cviewSerNo", cviewSerNo);
		model.put("issue_condition", list.get(0).getIssue_condition());
		model.put("em_no", list.get(0).getEm_no());
		model.put("s_viewStatus1", viewStatus1);
		model.put("s_viewSus", viewSus);
		model.put("s_viewfrom_dt", viewfrom_dt);
		model.put("s_viewto_dt", viewto_dt);
		model.put("roleType", roleType);
		model.put("status", cviewStatus);
		session.getTransaction().commit();
		session.close();
		return new ModelAndView("cveh_daily_receipt_issue_report_ViewTiles");
	}

	// FOR GETTING DATA IN EDIT/UPDATE PAGE
	@RequestMapping(value = "/admin/CVehUpdateReceiveIssue", method = RequestMethod.POST)
	public ModelAndView CVehUpdateReceiveIssue(@ModelAttribute("cupdateid") int cupdateid, ModelMap model,
			HttpSession session1, String type_of_veh, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {

		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_EMAR_DRR_DIR_DTL  where id=:id");
		q.setParameter("id", cupdateid);
		TB_TMS_EMAR_DRR_DIR_DTL  upid = (TB_TMS_EMAR_DRR_DIR_DTL ) q.list().get(0);
		tx.commit();

		model.put("cveh_daily_receipt_issue_report_editCMD", upid);
		model.put("msg", msg);
		model.put("getMotherDepoList", c.getMotherDepoList(type_of_veh, session1));
		return new ModelAndView("cveh_daily_receipt_issue_report_EditTiles");
	}

	// EDIT/UPDATE ACTION IN DRR PAGE
	@RequestMapping(value = "/cveh_daily_receipt_issue_report_editAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Cveh_daily_receipt_issue_report_editAction(
			@ModelAttribute("cveh_daily_receipt_issue_report_editCMD") TB_TMS_EMAR_DRR_DIR_DTL updateid,
			HttpSession sessionURL, HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session11) {
		String roleType = sessionURL.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		String sus_no = updateid.getSus_no();
		String drr_dir_ser_no = updateid.getDrr_dir_ser_no();
		String em_no = request.getParameter("em_no_curr");
		String em_no1 = updateid.getEm_no();
		String iss_cond = request.getParameter("chkissuecond").toString();

		if (!iss_cond.equals("1") && !iss_cond.equals("2") && !iss_cond.equals("3") && !iss_cond.equals("4")) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Choose Receive / Issue");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (request.getParameter("issue_agnst_rio") != "" & request.getParameter("issue_agnst_rio") != null
				& validation.country_of_originTMSLength(request.getParameter("issue_agnst_rio")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.issue_against_rioMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (request.getParameter("other_agency") != "" & request.getParameter("other_agency") != null
				& validation.authority_noLength(request.getParameter("other_agency")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.other_agencyMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (drr_dir_ser_no.equals("") || drr_dir_ser_no == "" || drr_dir_ser_no == null
				|| drr_dir_ser_no.equals(null) || drr_dir_ser_no.equals("null")) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Enter the DRR/DIR Ser No.");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		} else if (drr_dir_ser_no != null && validation.authority_noLength(drr_dir_ser_no) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.drr_dir_ser_noMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		} else if (sus_no.equals("0") || sus_no == "0" || sus_no.equals("") || sus_no == "" || sus_no == null
				|| sus_no.equals(null) || sus_no.equals("null")) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Enter the Vehicle Depot Sus No.");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		} else if (request.getParameter("unit_sus_no") != "" & request.getParameter("unit_sus_no") != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (request.getParameter("unit_sus_name") != "" & request.getParameter("unit_sus_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_sus_name")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if ((request.getParameter("em_no").equals("") || request.getParameter("em_no") == ""
				|| request.getParameter("em_no") == null || request.getParameter("em_no").equals(null)
				|| request.getParameter("em_no").equals("null"))) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Enter the EM No.");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (em_no != null & validation.ba_noLength(request.getParameter("em_no")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.em_noMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (validation.checkCosSecLength(request.getParameter("kms_run")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.kms_runMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.remarksMSGTMS);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if (request.getParameter("authority").equals("") || request.getParameter("authority") == ""
				|| request.getParameter("authority") == null || request.getParameter("authority").equals(null)
				|| request.getParameter("authority").equals("null")) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Enter the Authority.");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		} else if (request.getParameter("authority") != null
				& validation.authority_noLength(request.getParameter("authority")) == false) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", validation.authorityMSG);
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		}

		else if ((iss_cond.equals("2") || iss_cond.equals("3")) && (request.getParameter("unit_sus_no").equals("")
				|| request.getParameter("unit_sus_no") == "" || request.getParameter("unit_sus_no").equals(null)
				|| request.getParameter("unit_sus_no") == null)) {
			model.put("cupdateid", updateid.getId());
			model.put("msg", "Please Enter the Unit SUS NO.");
			return new ModelAndView("redirect:CVehUpdateReceiveIssue");
		} else {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try {
				String username = session11.getAttribute("username").toString();
				String hql = "update TB_TMS_EMAR_DRR_DIR_DTL set sus_no=:sus_no, other_agency=:other_agency, unit_sus_no=:unit_sus_no, em_no=:em_no, remarks=:remarks, authority=:authority, modify_by=:modify_by, modify_date=:modify_date, status ='0',issue_type =:issue_type where id=:id";

				if (iss_cond.equals("4")) {
					Session s = HibernateUtil.getSessionFactory().openSession();
					Transaction t = s.beginTransaction();
					Query q = s.createQuery("select purchas_cost from Tms_Banum_Req_Child where ba_no=:em_no");
					q.setParameter("em_no", em_no);
					String list = q.uniqueResult().toString();
					t.commit();
					Long q1 = (long) Integer.parseInt(list);
					String amntstr = request.getParameter("auction_amount").toString().replaceAll(",", "");
					int amnt = Integer.parseInt(amntstr);

					if (amntstr != "0" & validation.checkCosSecLength(amntstr) == false) {
						model.put("cupdateid", updateid.getId());
						model.put("msg", validation.auction_amountMSG);
						return new ModelAndView("redirect:CVehUpdateReceiveIssue");
					}
					if (amnt < q1) {
						updateid.setModify_by(username);
						updateid.setModify_date(new Date());

						String q11 = "update TB_TMS_BANUM_DIRCTRY set auction_amount= :amnt,status='Active' where ba_no=:em_no";
						Query updatedEntities = session.createQuery(q11).setString("em_no", updateid.getEm_no())
								.setString("amnt", String.valueOf(amnt));
						updatedEntities.executeUpdate();

					} else {
						model.put("msg", "Auction Amount should be less than Purchase Cost");
						return new ModelAndView("redirect:CVehUpdateReceiveIssue?cupdateid=" + updateid.getId());
					}
				}

				if (em_no.equals(em_no1)) {
					Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
							.setString("other_agency", updateid.getOther_agency())
							.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("em_no", em_no1)
							.setString("remarks", updateid.getRemarks()).setString("authority", updateid.getAuthority())
							.setString("modify_by", username).setDate("modify_date", new Date())
							.setInteger("id", updateid.getId()).setString("issue_type", updateid.getIssue_type());
					int rowCount = query.executeUpdate();
					tx.commit();
					if (rowCount > 0) {
						model.put("msg", "Data Updated Successfully.");
					} else {
						model.put("msg", "Data not Update.");
					}
				} else {
					if (cDao.ifExistSusAndSerAndEMNo(sus_no, drr_dir_ser_no, em_no1, iss_cond) == true) {
						model.put("msg", "Current EM No Already Exists");
						return new ModelAndView("redirect:CVehUpdateReceiveIssue?cupdateid=" + updateid.getId());
					} else {
						Session session1 = HibernateUtilNA.getSessionFactory().openSession();
						Transaction tx1 = session1.beginTransaction();
						String hql1 = "update TB_TMS_EMAR_DRR_DIR_DTL set sus_no=:sus_no, other_agency=:other_agency, unit_sus_no=:unit_sus_no, em_no=:em_no, remarks=:remarks, authority=:authority, modify_by=:modify_by, modify_date=:modify_date, status ='0',issue_type =:issue_type where id=:id";

						Query query = session1.createQuery(hql1).setString("sus_no", updateid.getSus_no())
								.setString("other_agency", updateid.getOther_agency())
								.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("em_no", em_no1)
								.setString("remarks", updateid.getRemarks())
								.setString("authority", updateid.getAuthority()).setString("modify_by", username)
								.setDate("modify_date", new Date()).setInteger("id", updateid.getId())
								.setString("issue_type", updateid.getIssue_type());
						int rowCount = query.executeUpdate();
						tx1.commit();
						tx.commit();
						if (rowCount > 0) {
							model.put("msg", "Data Updated Successfully.");
						} else {
							model.put("msg", "Data not Update.");
						}
					}
				}
			} catch (Exception e) {
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			return new ModelAndView("redirect:cveh_daily_receipt_issue_report_search");
		}
	}

	// DELETE RECORD OF DRR IN VIEW PAGE OF DRR
	@RequestMapping(value = "/admin/CVehDeleteReceiveIssue", method = RequestMethod.POST)
	public ModelAndView setDeleteFootTransport(@ModelAttribute("cdeleteid") String cdeleteid, HttpSession sessionURL,
			String cissue_condition_delete, String cemno_delete, String csus_no_delete, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionURL.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionURL.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		Session case1 = HibernateUtilNA.getSessionFactory().openSession();
		TB_TMS_EMAR_DRR_DIR_DTL getDr2 = (TB_TMS_EMAR_DRR_DIR_DTL) case1.get(TB_TMS_EMAR_DRR_DIR_DTL.class,
				Integer.parseInt(cdeleteid));
		String drr_dir_ser_no = getDr2.getDrr_dir_ser_no().toString();
		int iss_cond_id = Integer.parseInt(cissue_condition_delete);
		if (iss_cond_id == 1) {
			Session sessioncase1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx2 = sessioncase1.beginTransaction();
			Query query = sessioncase1.createQuery(
					"select count(sus_no) AS ba_count from TB_TMS_EMAR_DRR_DIR_DTL where sus_no = :sus_no and drr_dir_ser_no = :drr_dir_ser_no and issue_condition='1'");
			query.setParameter("sus_no", csus_no_delete).setParameter("drr_dir_ser_no", drr_dir_ser_no);
			Long result = (Long) query.uniqueResult();
			tx2.commit();
			sessioncase1.close();

			if (result == 1) {
				Session sessioncase2 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = sessioncase2.beginTransaction();
				Query query3 = sessioncase2.createQuery(
						"delete from TB_TMS_EMAR_DRR_DIR_MAIN where sus_no = :sus_no and drr_dir_ser_no = :drr_dir_ser_no");
				query3.setParameter("sus_no", csus_no_delete).setParameter("drr_dir_ser_no", drr_dir_ser_no);
				query3.executeUpdate();
				tx3.commit();
				sessioncase2.close();
			}
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from TB_TMS_EMAR_DRR_DIR_DTL where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", Integer.parseInt(cdeleteid));
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			model.put("msg", "Deleted Successfully.");
		} else {
			model.put("msg", "Modification Delete not Successfully");
		}
		return new ModelAndView("redirect:cveh_daily_receipt_issue_report_search");
	}

	// APPROVE DRR CONDITIONS changes by mitesh (20-02-2025)
	@RequestMapping(value = "/admin/CVehApprovedReceiveIssue", method = RequestMethod.POST)
	public ModelAndView setApprovedFootTransport(@ModelAttribute("csus_no_aprove") String csus_no_aprove,
			HttpSession sessionURL, String cser_no_approve, int cissue_condition, String emno, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session1) {
		String roleid = sessionURL.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cveh_daily_receipt_issue_report_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionURL.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
	
		String username = session1.getAttribute("username").toString();
		if (cissue_condition == 1) {
			Session s5 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx5 = s5.beginTransaction();
			String h5 = "update TB_TMS_EMAR_DRR_DIR_MAIN c set c.status = :status, c.approved_by = :approved_by, c.approve_date =:approve_date where c.sus_no = :sus_no and c.drr_dir_ser_no = :drr_dir_ser_no and c.status = '0'";
			s5.createQuery(h5).setString("status", "1").setString("approved_by", username)
					.setTimestamp("approve_date", new Date()).setString("sus_no", csus_no_aprove)
					.setString("drr_dir_ser_no", cser_no_approve).executeUpdate();
			tx5.commit();
			s5.close();
		}
		if (cissue_condition == 2) {
			TB_TMS_EMAR_REPORT_MAIN emar_report_main = new TB_TMS_EMAR_REPORT_MAIN();
			Session sessionGet = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionGet.beginTransaction();
			Query q = sessionGet.createQuery(
					"from TB_TMS_EMAR_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no");
			q.setParameter("sus_no", csus_no_aprove).setParameter("drr_dir_ser_no", cser_no_approve);
			@SuppressWarnings("unchecked")
			List<TB_TMS_EMAR_DRR_DIR_DTL> list = (List<TB_TMS_EMAR_DRR_DIR_DTL>) q.list();
			tx.commit();
			for (int i = 0; i < list.size(); i++) {
				String bano_new = list.get(i).getEm_no();
				if (cDao.ifExistEmarEMNo(bano_new) == false) {
					TB_TMS_EMAR_REPORT emar_report = new TB_TMS_EMAR_REPORT();
					emar_report.setSus_no(list.get(i).getUnit_sus_no());
					emar_report.setEm_no(list.get(i).getEm_no());
					emar_report.setClassification(list.get(i).getClassification());
					emar_report.setCurrent_km_run(list.get(i).getKms_run());
					emar_report.setPrev_km_run(list.get(i).getKms_run());
					emar_report.setTotal_km_run(list.get(i).getKms_run());
					emar_report.setStatus("1");
					emar_report.setCreation_by(username);
					emar_report.setCreation_date(new Date());
					emar_report.setVersion_no(0);
					emar_report.setSeviceable("No");
					emar_report.setSer_reason("0");
					emar_report.setOh_state("No");
					Session mvcrba = HibernateUtilNA.getSessionFactory().openSession();
					mvcrba.flush();
					Transaction mvcrtx = mvcrba.beginTransaction();
					mvcrba.save(emar_report);
					mvcrtx.commit();
					mvcrba.close();

					Session sessionemar_main = HibernateUtil.getSessionFactory().openSession();
					Transaction txemar_main = sessionemar_main.beginTransaction();
					Query q_emar_main = sessionemar_main
							.createQuery("select id from TB_TMS_EMAR_REPORT_MAIN where sus_no=:sus_no");
					q_emar_main.setParameter("sus_no", list.get(i).getUnit_sus_no());
					@SuppressWarnings("unchecked")
					List<TB_TMS_EMAR_REPORT_MAIN> getlistexistsus_no = (List<TB_TMS_EMAR_REPORT_MAIN>) q_emar_main
							.list();
					txemar_main.commit();

					if (getlistexistsus_no.size() == 0) {
						emar_report_main.setSus_no(list.get(i).getUnit_sus_no());
						emar_report_main.setStatus("1");
						emar_report_main.setCreation_by(username);
						emar_report_main.setCreation_date(new Date());
						emar_report_main.setVersion_no(0);
						emar_report_main.setDate_of_mvcr(new Date());
						emar_report_main.setModify_date(new Date());
						emar_report_main.setApprove_date(new Date());
						emar_report_main.setDt_of_submsn(new Date());

						Session session = HibernateUtil.getSessionFactory().openSession();
						session.flush();
						Transaction txn = session.beginTransaction();
						session.save(emar_report_main);
						txn.commit();
						session.close();
					}
					sessionemar_main.close();
				}
			}
			sessionGet.close();
		}

		if (cissue_condition == 3) {
			Session sessionGet2 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx2 = sessionGet2.beginTransaction();
			Query q = sessionGet2.createQuery(
					"delete from TB_TMS_EMAR_REPORT where em_no in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no)");
			q.setParameter("sus_no", csus_no_aprove).setParameter("drr_dir_ser_no", cser_no_approve);
			q.executeUpdate();
			tx2.commit();
			sessionGet2.close();

			Session sessionemar_maindt = HibernateUtil.getSessionFactory().openSession();
			Transaction txemar_main = sessionemar_maindt.beginTransaction();
			Query q_emar_main = sessionemar_maindt
					.createQuery("select id from TB_TMS_EMAR_REPORT where sus_no=:sus_no and status = '1'");
			q_emar_main.setParameter("sus_no", csus_no_aprove);
			@SuppressWarnings("unchecked")
			List<TB_TMS_EMAR_REPORT> getlistexistsus_no = (List<TB_TMS_EMAR_REPORT>) q_emar_main.list();
			txemar_main.commit();
			sessionemar_maindt.close();

			if (getlistexistsus_no.size() == 0) {
				Session sessionGetdt = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = sessionGetdt.beginTransaction();
				Query qdt = sessionGetdt.createQuery("delete from TB_TMS_EMAR_REPORT_MAIN where sus_no =:sus_no");
				qdt.setParameter("sus_no", csus_no_aprove);
				qdt.executeUpdate();
				tx3.commit();
				sessionGetdt.close();
			}
		}
		if (cissue_condition == 4) {
			Session sessionGet3 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx3 = sessionGet3.beginTransaction();
			Query query = sessionGet3.createQuery(
					"update TB_TMS_BANUM_DIRCTRY c set c.status = :status where c.ba_no in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no)");
			query.setString("status", "Auctioned Out").setString("sus_no", csus_no_aprove).setString("drr_dir_ser_no",
					cser_no_approve);
			query.executeUpdate();
			tx3.commit();
			sessionGet3.close();
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_EMAR_DRR_DIR_DTL c set c.status = :status, c.approved_by = :approved_by, c.approve_date = :approve_date where c.sus_no = :sus_no and c.drr_dir_ser_no = :drr_dir_ser_no and c.status = '0'";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("approved_by", username)
				.setTimestamp("approve_date", new Date()).setString("sus_no", csus_no_aprove)
				.setString("drr_dir_ser_no", cser_no_approve).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			model.put("msg", "Data Approved Successfully.");
			 if (cissue_condition == 3) {

                 List<Map<String, String>> list2 = cDao.get_C_vech_daily(csus_no_aprove, cser_no_approve);
                 
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
//                         String user_id = "";
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
                                     String title = "C Veh DRR Approved";
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
                                             String title = "C Veh DRR Approved";
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
                                             String title = "C Veh DRR Approved";
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
                                             String title = "C Veh DRR Approved";
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
                                             String title = "C Veh DRR Approved";
                                             String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                             + " DRR Approved";
                                             Boolean d = notification.sendNotification(title, description, user_id, username);
                                     }

                                 }
                         }
//                         if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
//                                 String title = "C Veh DRR Approved";
//                                 String description = "" + count + " *" + list2.get(k).get("std_nomclature")
//                                                 + " DRR Approved";
//                                 Boolean d = notification.sendNotification(title, description, user_id, username);
//                         }
                         
                         //depo notification
                         if (csus_no_aprove != null && !csus_no_aprove.trim().equals("")) {
                         String userid = "";
                         List<UserLogin> userlist = comm.getUseridlist(csus_no_aprove);
                         for (int i = 0; i < userlist.size(); i++) {
                                 if (i == 0) {
                                         userid += userlist.get(i).getUserId();
                                 }

                                 else {
                                         userid += "," + userlist.get(i).getUserId();
                                 }

                         }
                         if (userid != "" && !userid.equals(null) && !userid.equals("")) {
                                 String title = "C Veh DRR Received";
                                 String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                 + " DRR Received";
                                 Boolean d = notification.sendNotification(title, description, userid, username);
                         }
                         }
                 }
                 
			 }
		} else {
			model.put("msg", "Data not Approved.");
		}

		return new ModelAndView("cveh_daily_receipt_issue_report_SearchTiles");
	}



	// GET PURCHASE COST OF BA_NO
	@RequestMapping(value = "/admin/getAuctionAmtfromBaNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getAuctionAmtfromBaNo(String em_no, HttpSession sesssionURL) {
		int userid = Integer.parseInt(sesssionURL.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select auction_amount from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no");
		q.setParameter("ba_no", em_no);
		@SuppressWarnings("unchecked")
		String getlist = (String) q.uniqueResult();
		tx.commit();
		session.close();
		List<String> list1 = new ArrayList<String>();
		list1.add(hex_asciiDao.asciiToHex(getlist));
		return list1;
	}
}
