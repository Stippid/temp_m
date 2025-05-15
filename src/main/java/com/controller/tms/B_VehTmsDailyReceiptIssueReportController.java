package com.controller.tms;

import java.math.BigInteger;
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
import com.dao.tms.B_VehTmsDailyRecieptDao;
import com.dao.tms.DailyReceiptIssuereportDAO;
import com.dao.tms.DailyReceiptIssuereportDAOimpl;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_DRR_DIR_MAIN;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class B_VehTmsDailyReceiptIssueReportController {
	@Autowired
	DailyReceiptIssuereportDAO dDao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ReportsDAO  UOD;
	@Autowired
	B_VehTmsDailyRecieptDao  b_dao;
	NotificationController notification = new NotificationController();
	Psg_CommonController comm = new Psg_CommonController();
	
	@RequestMapping(value = "/admin/bveh_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView Bveh_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("bveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("year", year);
		Mmap.put("msg", msg);
		return new ModelAndView("bveh_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/getBaNoListFreeStockCase1", method = RequestMethod.POST)
	public @ResponseBody List<String> getBaNoListFreeStockCase1(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select " + "	distinct ba.ba_no  " + "	from  " + "	TB_TMS_BANUM_DIRCTRY ba  "
				+ "	where  " + "	ba.ba_no not in (select distinct b.ba_no  "
				+ "				from TB_TMS_BANUM_DIRCTRY b  " + "				where   "
				+ "				ba_no in(select distinct d.ba_no  " + "					from TB_TMS_DRR_DIR_DTL d  "
				+ "					where upper(d.ba_no) like :ba_no " + "					order by d.ba_no)  "
				+ "					and b.status = 'Active' and b.veh_cat='B' and upper(b.ba_no) like :ba_no order by b.ba_no) and ba.status ='Active' and ba.veh_cat='B' and upper(ba.ba_no) like :ba_no order by ba.ba_no")
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

	@RequestMapping(value = "/getBaNoListIssueUnitCase2", method = RequestMethod.POST)
	public @ResponseBody List<String> getBaNoListIssueUnitCase2(String sus_no, String ba_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and typ_of_retrn='0' and type_of_issue='1' and status='1' and ba_no not in(select distinct ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and (typ_of_retrn='1' and type_of_issue='0') or (typ_of_retrn='0' and type_of_issue='0') or (typ_of_retrn='1' and type_of_issue='1') or type_of_stock='3') and upper(ba_no) like :ba_no and ba_no in (select ba_no from TB_TMS_BANUM_DIRCTRY where status != 'NonArmy'  and veh_cat='B') order by ba_no")
				.setMaxResults(10);
		q.setParameter("sus_no", sus_no);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
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
			FinalList.add(enckey + "9rjdwz==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getcase3BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getcase3BaNoDtlList(String unit_sus_no, String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select ba_no from TB_TMS_MVCR_PARTA_DTL where sus_no=:unit_sus_no and upper(ba_no) like :ba_no order by ba_no")
				.setMaxResults(10);
		q.setParameter("unit_sus_no", unit_sus_no);
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

	@RequestMapping(value = "/getcase4BaNoDtlList", method = RequestMethod.POST)
	public @ResponseBody List<String> getcase4BaNoDtlList(String sus_no, String ba_no, HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and typ_of_retrn='0' and type_of_issue='0' and status='1' and ba_no not in(select distinct ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and (typ_of_retrn='1' and type_of_issue='1') or type_of_stock = '5' ) and upper(ba_no) like :ba_no order by ba_no")
				.setMaxResults(10);
		q.setParameter("sus_no", sus_no);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
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

	@RequestMapping(value = "/getcase3KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getcase3KmList(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct kms_run from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getcase4KmList", method = RequestMethod.POST)
	public @ResponseBody List<String> getcase4KmList(String ba_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct kms_run from TB_TMS_DRR_DIR_DTL where ba_no=:ba_no and typ_of_retrn='0' and type_of_issue='0' and status='1'");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getBaDetailsList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getBaDetailsList(String ba_no, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select engine_no, chasis_no, mct from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

		for (Object[] listObject : list) {
			String engine_no = String.valueOf(listObject[0]);
			String chasis_no = String.valueOf(listObject[1]);
			String mct = String.valueOf(listObject[2]);

			String base64EncodedEncryptedengine_no = new String(Base64.encodeBase64(c.doFinal(engine_no.getBytes())));
			String base64EncodedEncryptedchasis_no = new String(Base64.encodeBase64(c.doFinal(chasis_no.getBytes())));
			String base64EncodedEncryptedmct = new String(Base64.encodeBase64(c.doFinal(mct.getBytes())));

			List<String> EncList = new ArrayList<String>();
			EncList.add(base64EncodedEncryptedengine_no);
			EncList.add(base64EncodedEncryptedchasis_no);
			EncList.add(base64EncodedEncryptedmct);
			FinalList.add(EncList);
		}
		List<String> EncKeyList = new ArrayList<String>();
		if (list.size() != 0) {
			EncKeyList.add(enckey + "YbFjyB==");
			EncKeyList.add(enckey + "HNTrgS==");
		}
		FinalList.add(EncKeyList);
		return FinalList;

	}

	@RequestMapping(value = "/getVehSusNoToNameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getVehSusNoToNameList(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Updated for optimization of methods
	@RequestMapping(value = "/getVehSusNoToNameList1")
	public @ResponseBody List<String> getVehSusNoToNameList1(String y, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Updated for optimization of methods
	@RequestMapping(value = "/getVehSusNameToNoList1")
	public @ResponseBody List<String> getVehSusNameToNoList1(String y, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no = 'Active'");
		q.setParameter("unit_name", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getStdNomenList", method = RequestMethod.POST)
	public @ResponseBody List<String> getStdNomenList(BigInteger mct, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct std_nomclature from TB_TMS_MCT_MASTER  where status = 'ACTIVE' and mct=:mct");
		q.setParameter("mct", mct);
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

	public @ResponseBody List<String> getStdNomenListWithoutEncrypt(BigInteger mct, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct std_nomclature from TB_TMS_MCT_MASTER  where status = 'ACTIVE' and mct=:mct");
		q.setParameter("mct", mct);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/admin/addDailyReceiveIssueReport", method = RequestMethod.POST)
	public @ResponseBody String addDailyReceiveIssueReport(ModelMap model, HttpServletRequest request,
			HttpSession sessionA, String drr_dir_ser_no, String sus_no, String unit_name, String a,
			String issue_against_rio, String other_agency, String unit_sus_no, String unit_sus_name, String ba_no,
			int kms_run, String cl, String remarks, String authority, String date_report,String qty)
			throws HibernateException, ParseException {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("bveh_daily_receipt_issue_report", roleid);
		if (val == false) {
			return "Access Denied.";
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		if (roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}
		if (roleAccess.equals("Unit")) {
			unit_sus_no = roleSusNo;
		}
		TB_TMS_DRR_DIR_DTL dtl = new TB_TMS_DRR_DIR_DTL();
		TB_TMS_DRR_DIR_MAIN main = new TB_TMS_DRR_DIR_MAIN();
		String username = sessionA.getAttribute("username").toString();
		String msg = null;
		if (!a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4")) {
			return "Please Choose Receive / Issue";
		} else if (drr_dir_ser_no.equals("") || drr_dir_ser_no.equals("null") || drr_dir_ser_no.equals(null)) {
			return "Please Enter the DRR/DIR Ser No";
		} else if (drr_dir_ser_no != null
				& validation.authority_noLength(request.getParameter("drr_dir_ser_no")) == false) {
			return validation.drr_dir_ser_noMSG;
		} else if (sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null)) {
			return "Please Enter the SUS NO.";
		} else if (sus_no != null & validation.sus_noLength(request.getParameter("sus_no")) == false) {
			return validation.depot_sus_noMSG;
		} else if (ba_no.equals("") || ba_no.equals("null") || ba_no.equals(null)) {
			return "Please Enter the BA No";
		} else if (ba_no != null & validation.ba_noLength(request.getParameter("ba_no")) == false) {
			return validation.ba_noMSG;
		} else if (authority.equals("") || authority.equals("null") || authority.equals(null)) {
			return "Please Enter the Authority No";
		} else if (authority != null & validation.authority_noLength(request.getParameter("authority")) == false) {
			return validation.authority_noMSG;
		} else if (request.getParameter("unit_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_name")) == false) {
			return validation.depotunit_nameMSG;
		} else if (request.getParameter("issue_against_rio") != null
				& validation.country_of_originTMSLength(request.getParameter("issue_against_rio")) == false) {
			return validation.issue_against_rioMSG;
		} else if (request.getParameter("other_agency") != null
				& validation.checkAnimalMasterLength(request.getParameter("other_agency")) == false) {
			return validation.other_agencyMSG;
		} else if (unit_sus_no != "" & unit_sus_no != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			return validation.sus_noMSG;
		} else if (request.getParameter("unit_sus_name") != "" & request.getParameter("unit_sus_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_sus_name")) == false) {
			return validation.unit_nameMSG;
		} else if (validation.km_ffLength(request.getParameter("kms_run")) == false) {
			return validation.kms_runMSG;
		} else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			return validation.remarksMSGTMS;
		} else if (request.getParameter("authority") != "" & request.getParameter("authority") != null
				& validation.authority_noLength(request.getParameter("authority")) == false) {
			return validation.authority_noMSG;
		} else {
			Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHql.beginTransaction();
			try {
				main.setCreation_by(username);
				main.setCreation_date(new Date());
				main.setDt_of_retrn(new Date());
				main.setStatus("0");
				main.setDrr_dir_ser_no(drr_dir_ser_no);
				main.setSus_no(sus_no);
				main.setVersion_no(0);
				dtl.setCreation_by(username);
				dtl.setCreation_date(new Date());
				dtl.setStatus("0");
				dtl.setDrr_dir_ser_no(drr_dir_ser_no);
				dtl.setSus_no(sus_no);
				if (a.equals("1")) {
					dtl.setTyp_of_retrn("0");
					dtl.setType_of_issue("1");
					dtl.setType_of_stock("0");
					main.setType_of_stock("0");
				} else if (a.equals("2")) {
					dtl.setTyp_of_retrn("1");
					dtl.setType_of_issue("0");
					dtl.setType_of_stock("3");
					main.setType_of_stock("3");
				} else if (a.equals("3")) {
					dtl.setTyp_of_retrn("0");
					dtl.setType_of_issue("0");
					dtl.setType_of_stock("4");
					main.setType_of_stock("4");
				} else if (a.equals("4")) {
					dtl.setTyp_of_retrn("1");
					dtl.setType_of_issue("1");
					dtl.setType_of_stock("5");
					main.setType_of_stock("5");
				}
				dtl.setIssue_against_rio(issue_against_rio);
				dtl.setFiller_1(other_agency);
				dtl.setUnit_sus_no(unit_sus_no);
				dtl.setBa_no(ba_no);
				dtl.setKms_run(kms_run);
				dtl.setClassification(cl);
				dtl.setRemarks(remarks);
				dtl.setAuthority(authority);
				if (a.equals("1")) {
					if (dDao.ifExistSusAndSer(sus_no, drr_dir_ser_no) == false) {
						sessionHql.save(main);
						// sessionHql.getTransaction().commit();
					}
					if (dDao.ifExistSusAndSerDtl(a, ba_no) == true) {
						return "Failure dtl";
					} else {
						sessionHql.save(dtl);
						// sessionHql.getTransaction().commit();
					}
				} else {
					if (dDao.ifExistSusAndSerDtl(a, ba_no) == true) {
						return "Failure dtl";
					} else {
						sessionHql.save(dtl);
								// sessionHql.getTransaction().commit();
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
			    	                              String description = "1* DRR Initiated, pending for approval" ;
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
			    	      }
			    	 }
			    	      if (cmd_sus_no != null   && !cmd_sus_no.trim().equals("")) {
			    	      if( cmd_sus_no != unit_sus_no && !cmd_sus_no.trim().equals(unit_sus_no)) {
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
			    	                              String description = "1* DRR Initiated, pending for approval" ;
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
			    	      }
			    	      }
			    	      }
			    	      if (div_sus_no != null   && !div_sus_no.trim().equals("")) { 
			    	      if( div_sus_no != unit_sus_no && !div_sus_no.trim().equals(unit_sus_no)) {
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
			    	                              String description = "1* DRR Initiated, pending for approval" ;
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
			    	      }
			    	      }
			    	      }
			    	      if (bde_sus_no != null   && !bde_sus_no.trim().equals("")) {
			    	      if( bde_sus_no != unit_sus_no && !bde_sus_no.trim().equals(unit_sus_no)) {
			    	      
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
			    	      if( corps_sus_no != unit_sus_no && !corps_sus_no.trim().equals(unit_sus_no)) {
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
			    	      List<String> userlist = dDao.getUseridBysusfordepo(sus_no);
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
			    	      List<String> userlist = dDao.getUseridBysusforlinedte(unit_sus_no);
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

			    	tx.commit();


			    	}

			    	catch (Exception e) {
			    	sessionHql.getTransaction().rollback();
			    	} finally {
			    	sessionHql.close();
			    	}
			    	return msg;
			    	}
			    	}


	// Search Screen Methods
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@RequestMapping(value = "/admin/search_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView Search_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String sus_no_aprove,
			HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_daily_receipt_issue_reportTiles");
	}

	/*NITIN V4 15/11/2022*/
	@RequestMapping(value = "/admin/search_drr_b_Veh", method = RequestMethod.POST)
	public ModelAndView search_drr_b_Veh(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "frm_date1", required = false) String from_date,
			@RequestParam(value = "curr_date", required = false) String curr_date,
			@RequestParam(value = "ba_no2", required = false) String ba_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();

		if (from_date != "" && curr_date != "") {
			if (alltms.CompareDate(from_date, curr_date) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("search_daily_receipt_issue_reportTiles");
			}
		}

		if (!status.equals("")) {
			Mmap.put("status", status);
		}
		if (!sus_no.equals("")) {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.depot_sus_noMSG);
				return new ModelAndView("redirect:search_daily_receipt_issue_report");
			}
			Mmap.put("sus_no", sus_no);
		}
		if (!from_date.equals("")) {
			Mmap.put("from_date", from_date);
		}
		if (!curr_date.equals("")) {
			Mmap.put("curr_date", curr_date);
		}
		
		if (!ba_no.equals("")) {
			Mmap.put("ba_no", ba_no);
		}
		
		ArrayList<List<String>> list = dDao.getsearch_drr_b_Veh(status, sus_no, from_date, curr_date, roleType,ba_no);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("search_status", "0");
		return new ModelAndView("search_daily_receipt_issue_reportTiles");
	}
	/*NITIN V4 15/11/2022*/


	@RequestMapping(value = "/getDtlDepoSusNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getDtlDepoSusNo(String sus_no, String status, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (status.equals("3")) {
			q = session.createQuery("select distinct sus_no from TB_TMS_DRR_DIR_DTL where upper(sus_no) like :sus_no")
					.setMaxResults(10);
		} else {
			q = session.createQuery(
					"select distinct sus_no from TB_TMS_DRR_DIR_DTL where upper(sus_no) like :sus_no and status= :status")
					.setMaxResults(10);
		}
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		q.setParameter("status", status);
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
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getDtlDepoSusName", method = RequestMethod.POST)
	public @ResponseBody List<String> getDtlDepoSusName(String unit_name, String status, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			if (status.equals("3")) {
				q = session.createQuery(
						"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and upper(unit_name) like :unit_name and sus_no in \r\n"
								+ "	(select sus_no from TB_TMS_DRR_DIR_DTL)")
						.setMaxResults(10);
			} else {
				q = session.createQuery(
						"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and upper(unit_name) like :unit_name and sus_no in \r\n"
								+ "	(select sus_no from TB_TMS_DRR_DIR_DTL where status= :status)")
						.setMaxResults(10);
				q.setParameter("status", status);
			}
			q.setParameter("sus_no", roleSusNo);
		} else {
			if (status.equals("3")) {
				q = session.createQuery(
						"select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and sus_no in \r\n"
								+ "	(select sus_no from TB_TMS_DRR_DIR_DTL)")
						.setMaxResults(10);
			} else {
				q = session.createQuery(
						"select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and sus_no in \r\n"
								+ "	(select sus_no from TB_TMS_DRR_DIR_DTL where status= :status)")
						.setMaxResults(10);
				q.setParameter("status", status);
			}
		}
		q.setParameter("unit_name", "%" + unit_name.toUpperCase() + "%");

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
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}


	
	@RequestMapping(value = "/admin/ApprovedReceiveIssue", method = RequestMethod.POST)
    public ModelAndView setApprovedFootTransport(@ModelAttribute("sus_no_aprove") String sus_no_aprove,
            String ser_no_approve, int type_of_rec, int type_of_issue, String bano,String viewStatus11,String viewSus1,
            String viewfrom_dt1,String viewto_dt1,String sus1,ModelMap model,
            @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
            HttpSession sessionA,HttpServletRequest request) {
        String roleType = sessionA.getAttribute("roleType").toString();
        if (!roleType.equals("APP") && !roleType.equals("ALL")) {
            return new ModelAndView("AccessTiles");
        }
        //String hidden_sus = request.getParameter("hidden_sus");
        String roleAccess = sessionA.getAttribute("roleAccess").toString();
        String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
        if (roleAccess.equals("Unit")) {
            sus_no_aprove = roleSusNo;
        }
        String username = sessionA.getAttribute("username").toString();
        if (type_of_rec == 0 && type_of_issue == 1) {
            Session s5 = HibernateUtilNA.getSessionFactory().openSession();
            Transaction tx5 = s5.beginTransaction();
            String h5 = "update TB_TMS_DRR_DIR_MAIN c set c.status =:status where c.sus_no =:sus_no and c.drr_dir_ser_no =:drr_dir_ser_no and c.status ='0'";
            s5.createQuery(h5).setString("status", "1").setString("sus_no", sus_no_aprove).setString("drr_dir_ser_no", ser_no_approve).executeUpdate();
            tx5.commit();
            s5.close();
        }

        if (type_of_rec == 1 && type_of_issue == 0) {
            Session sessionGet = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionGet.beginTransaction();
            Query q = sessionGet.createQuery("from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no");
            q.setParameter("sus_no", sus_no_aprove).setParameter("drr_dir_ser_no", ser_no_approve);
            @SuppressWarnings("unchecked")
            List<TB_TMS_DRR_DIR_DTL> list = (List<TB_TMS_DRR_DIR_DTL>) q.list();
            tx.commit();
            sessionGet.close();
            
            for (int i = 0; i < list.size(); i++) {
                String bano_new = list.get(i).getBa_no();
                if (dDao.ifExistMVCRBaNo(bano_new) == false) {
                    TB_TMS_MVCR_PARTA_DTL para_dtl = new TB_TMS_MVCR_PARTA_DTL();
                    para_dtl.setSus_no(list.get(i).getUnit_sus_no());
                    para_dtl.setDate_of_mvcr(new Date());
                    para_dtl.setIssue_type("5");
                    para_dtl.setBa_no(list.get(i).getBa_no());
                    para_dtl.setClassification(list.get(i).getClassification());
                    para_dtl.setKms_run(list.get(i).getKms_run());
                    para_dtl.setAuthrty_letter_no(list.get(i).getAuthority());
                    para_dtl.setRemarks(list.get(i).getRemarks());
                    para_dtl.setCreation_by(username);
                    para_dtl.setCreation_date(new Date());
                    para_dtl.setStatus("1");
                    para_dtl.setInlieu_mct(new BigInteger("0"));
                    Session mvcrba = HibernateUtilNA.getSessionFactory().openSession();
                    mvcrba.flush();
                    Transaction mvcrtx = mvcrba.beginTransaction();
                    mvcrba.save(para_dtl);
                    mvcrtx.commit();
                    mvcrba.close();
                }else {
                    Session sessionGet3 = HibernateUtilNA.getSessionFactory().openSession();
                    Transaction tx3 = sessionGet3.beginTransaction();
                    Query query = sessionGet3.createQuery("update TB_TMS_MVCR_PARTA_DTL set issue_type =other_issue_type,remarks='' where ba_no=:ba_no");
                    query.setString("ba_no", list.get(i).getBa_no());
                    query.executeUpdate();
                    tx3.commit();
                    sessionGet3.close();
                }
            }
        }

        if (type_of_rec == 0 && type_of_issue == 0) {
            Session sessionGet2 = HibernateUtilNA.getSessionFactory().openSession();
            Transaction tx2 = sessionGet2.beginTransaction();
            Query q = sessionGet2.createQuery(
                    "delete from TB_TMS_MVCR_PARTA_DTL where ba_no in(select ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no)");
            q.setParameter("sus_no", sus_no_aprove).setParameter("drr_dir_ser_no", ser_no_approve);
            q.executeUpdate();
            tx2.commit();
            sessionGet2.close();
        }

        if (type_of_rec == 1 && type_of_issue == 1) {
            Session sessionGet3 = HibernateUtilNA.getSessionFactory().openSession();
            Transaction tx3 = sessionGet3.beginTransaction();
            Query query = sessionGet3.createQuery(
                    "update TB_TMS_BANUM_DIRCTRY c set c.status = :status where   c.ba_no in(select ba_no from TB_TMS_DRR_DIR_DTL where sus_no=:sus_no and drr_dir_ser_no=:drr_dir_ser_no)");
            query.setString("status", "Auctioned").setString("sus_no", sus_no_aprove).setString("drr_dir_ser_no",
                    ser_no_approve);
            query.executeUpdate();
            tx3.commit();
            sessionGet3.close();
        }

        Session session = HibernateUtilNA.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hqlUpdate = "update TB_TMS_DRR_DIR_DTL c set c.status = :status, c.approved_by = :approved_by, c.approve_date = :approve_date where c.sus_no = :sus_no and c.drr_dir_ser_no = :drr_dir_ser_no and c.status ='0'";
        int app = session.createQuery(hqlUpdate).setString("status", "1").setString("approved_by", username)
                .setDate("approve_date", new Date()).setString("sus_no", sus_no_aprove)
                .setString("drr_dir_ser_no", ser_no_approve).executeUpdate();
        tx.commit();
        session.close();
        if (app > 0) {
            model.put("msg", "Approved Successfully.");

            if (type_of_rec == 0 && type_of_issue == 0) {

                    List<Map<String, String>> list2 = b_dao.get_B_vech_daily(sus_no_aprove, ser_no_approve);
                    
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
//                            String user_id = "";
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

                            // for depo

                            if (sus_no_aprove != null && !sus_no_aprove.trim().equals("")) {
                                    List<UserLogin> userlist = comm.getUseridlist(sus_no_aprove);
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
                                        String title = "B Veh DRR Approved";
                                        String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                        + " DRR Approved";
                                        Boolean d = notification.sendNotification(title, description, user_id, username);
                                }

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
                                        String title = "B Veh DRR Approved";
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
                                                String title = "B Veh DRR Approved";
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
                                                String title = "B Veh DRR Approved";
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
                                                String title = "B Veh DRR Approved";
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
                                                String title = "B Veh DRR Approved";
                                                String description = "" + count + " *" + list2.get(k).get("std_nomclature")
                                                                + " DRR Approved";
                                                Boolean d = notification.sendNotification(title, description, user_id, username);
                                        }

                                    }
                            }
//                            if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
//                                    String title = "B Veh DRR Approved";
//                                    String description = "" + count + " *" + list2.get(k).get("std_nomclature")
//                                                    + " DRR Approved";
//                                    Boolean d = notification.sendNotification(title, description, user_id, username);
//                            }
                            
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
                                    String title = "B Veh DRR Received";
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
        //kajal 123
           model.put("b_viewStatus1", viewStatus11);
           model.put("sus_no", viewSus1);
           model.put("from_date", viewfrom_dt1);
           model.put("curr_date", viewto_dt1);
           model.put("search_status", "1");
        return new ModelAndView("search_daily_receipt_issue_reportTiles");
    }
    
	@RequestMapping(value = "/admin/UpdateReceiveIssue")
	public ModelAndView UpdateReceiveIssue(@ModelAttribute("updateid") int updateid, ModelMap model,
			Authentication authentication, HttpSession sessionUserId) {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_DRR_DIR_DTL  where id=:id");
		q.setParameter("id", updateid);
		TB_TMS_DRR_DIR_DTL upid = (TB_TMS_DRR_DIR_DTL) q.list().get(0);
		tx.commit();

		model.put("edit_daily_ReceiptIssue_ReportCMD", upid);
		return new ModelAndView("edit_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/admin/BackReceiveIssue", method = RequestMethod.POST)
	public ModelAndView BackReceiveIssue(@ModelAttribute("viewStatus1") String viewStatus1, String viewSus,
			String viewfrom_dt, String viewto_dt, String nPara, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		model.put("b_viewStatus1", viewStatus1);
		model.put("b_viewfrom_dt", viewfrom_dt);
		model.put("b_viewto_dt", viewto_dt);
		if (nPara.equals("BVeh")) {
			return new ModelAndView("search_daily_receipt_issue_reportTiles");
		}
		if (nPara.equals("CVeh")) {
			return new ModelAndView("cveh_daily_receipt_issue_report_SearchTiles");
		}
		if (nPara.equals("AVeh")) {
			return new ModelAndView("search_aveh_daily_receipt_issue_reportTiles");
		}
		return null;
	}

	@RequestMapping(value = "/edit_daily_ReceiptIssue_ReportAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Edit_daily_ReceiptIssue_ReportAction(
			@ModelAttribute("edit_daily_ReceiptIssue_ReportCMD") TB_TMS_DRR_DIR_DTL updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session11) {
		model.put("updateid", updateid.getId());
		String roleType = session11.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getParameter("sus_no") != null & validation.sus_noLength(request.getParameter("sus_no")) == false) {
			model.put("msg", validation.depot_sus_noMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("ba_no") != null
				& validation.ba_noLength(request.getParameter("ba_no")) == false) {
			model.put("msg", validation.ba_noMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("authority") != null
				& validation.authority_noLength(request.getParameter("authority")) == false) {
			model.put("msg", validation.authority_noMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("unit_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_name")) == false) {
			model.put("msg", validation.depotunit_nameMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("issue_against_rio") != null
				& validation.country_of_originTMSLength(request.getParameter("issue_against_rio")) == false) {
			model.put("msg", validation.issue_against_rioMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("filler_1") != null
				& validation.checkAnimalMasterLength(request.getParameter("filler_1")) == false) {
			model.put("msg", validation.other_agencyMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("unit_sus_no") != "" & request.getParameter("unit_sus_no") != null
				& validation.sus_noLength(request.getParameter("unit_sus_no")) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("unit_sus_name") != "" & request.getParameter("unit_sus_name") != null
				& validation.checkUnit_nameLength(request.getParameter("unit_sus_name")) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (validation.km_ffLength(request.getParameter("kms_run")) == false) {
			model.put("msg", validation.kms_runMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("remarks") != "" & request.getParameter("remarks") != null
				& validation.check255Length(request.getParameter("remarks")) == false) {
			model.put("msg", validation.remarksMSGTMS);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		} else if (request.getParameter("authority") != null
				& validation.authority_noLength(request.getParameter("authority")) == false) {
			model.put("msg", validation.authority_noMSG);
			return new ModelAndView("redirect:UpdateReceiveIssue");
		}

		String username = session11.getAttribute("username").toString();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update TB_TMS_DRR_DIR_DTL set sus_no=:sus_no, filler_1=:filler_1, unit_sus_no=:unit_sus_no, ba_no=:ba_no, remarks=:remarks, authority=:authority, modify_by=:modify_by, modify_date=:modify_date, status ='0' where id=:id";
		String sus_no = updateid.getSus_no();
		String drr_dir_ser_no = updateid.getDrr_dir_ser_no();
		String ba_no = request.getParameter("ba_no_curr");
		String ba_no1 = updateid.getBa_no();
		if (ba_no.equals(ba_no1)) {
			Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
					.setString("filler_1", updateid.getFiller_1()).setString("unit_sus_no", updateid.getUnit_sus_no())
					.setString("ba_no", updateid.getBa_no()).setString("remarks", updateid.getRemarks())
					.setString("authority", updateid.getAuthority()).setString("modify_by", username)
					.setDate("modify_date", new Date()).setInteger("id", updateid.getId());
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if (rowCount > 0) {
				model.put("msg", "Data Updated Successfully.");
			} else {
				model.put("msg", "Data not  Updated.");
			}
		} else {
			if (dDao.ifExistSusAndSerAndBANo(sus_no, drr_dir_ser_no, ba_no) == true) {
				model.put("msg", "Current BA No Already Exists");
				return new ModelAndView("redirect:UpdateReceiveIssue?updateid=" + updateid.getId());
			} else {
				Query query = session.createQuery(hql).setString("sus_no", updateid.getSus_no())
						.setString("filler_1", updateid.getFiller_1())
						.setString("unit_sus_no", updateid.getUnit_sus_no()).setString("ba_no", updateid.getBa_no())
						.setString("remarks", updateid.getRemarks()).setString("authority", updateid.getAuthority())
						.setString("modify_by", username).setDate("modify_date", new Date())
						.setInteger("id", updateid.getId());
				int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if (rowCount > 0) {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data not  Updated.");
				}
			}
		}
		return new ModelAndView("redirect:search_daily_receipt_issue_report");
	}

	@RequestMapping(value = "/admin/view_daily_receipt_issue_report", method = RequestMethod.GET)
	public ModelAndView View_daily_receipt_issue_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("view_daily_receipt_issue_reportTiles");
	}

	@RequestMapping(value = "/admin/ViewReceiveIssue", method = RequestMethod.POST)
	public ModelAndView ViewReceiveIssue(@ModelAttribute("viewSerNo") String viewSerNo, HttpSession sessionA,
			String viewStatus, String viewDate, String viewStatus1, String viewSus, String viewfrom_dt,
			String viewto_dt,String search_sus_no, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_daily_receipt_issue_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleType = sessionA.getAttribute("roleType").toString();
		ArrayList<List<String>> list = dDao.getPendingBA_NoFromDRR(viewSerNo, viewStatus, viewDate);
		AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
		if (list.size() != 0) {
			model.put("view_daily_ReceiptIssue_ReportCMD", list);
			model.put("ba_no", list.get(0).get(1));
			model.put("sus_no", list.get(0).get(2));
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(list.get(0).get(2), sessionA).get(0));
			model.put("type_of_recv", list.get(0).get(5));
			model.put("type_of_issue", list.get(0).get(6));
			model.put("roleType", roleType);
		}
		model.put("viewSerNo", viewSerNo);
		model.put("s_viewStatus1", viewStatus1);
		model.put("s_viewSus", viewSus);
		model.put("s_viewfrom_dt", viewfrom_dt);
		model.put("s_viewto_dt", viewto_dt);
		model.put("search_sus_no", search_sus_no);
		return new ModelAndView("view_daily_receipt_issue_reportTiles");
	}
	//KAJAL V4 12/11/2022
}