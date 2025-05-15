package com.controller.mnh;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;
import org.hibernate.HibernateException;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MNH_CommonController {

	@Autowired
	private MNH_Common_DAO mnh1_Dao;

	@Autowired
	private MNH_ReportsDAO mnh7_Dao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	// MNH_CommonController mcommon = new MNH_CommonController();

	@RequestMapping(value = "/getMNHAutoList", method = RequestMethod.POST) // For Autocomplete -
																			// a(type),b(url),c(textbox),d(paravalue)
	public @ResponseBody List<String> getMNHAutoList(String a, String b, String c, String d, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String qw = "";
		String qr = "";
		String qcnd = "";

		String[] medicalUnitPrifix = (String[]) s1.getAttribute("medicalUnitPrifix");

		if (c.equalsIgnoreCase("SUS")) {
			qw = "select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no like :sus_no and status_sus_no='Active' and substring(sus_no, 1,4) in (:med)";
		}
		if (c.equalsIgnoreCase("NAME")) {
			qw = "select distinct unit_name from Miso_Orbat_Unt_Dtl where (lower(unit_name) like :sus_no or Upper(unit_name) like :sus_no) and "
					+ "status_sus_no='Active' and substring(sus_no, 1,4) in (:med)";
		}
		if (c.equalsIgnoreCase("DSUS")) {
			qw = "select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in "
					+ "(select distinct sus_no from Tb_Miso_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') "
					+ "and (sus_no like :sus_no) order by sus_no";
		}
		if (c.equalsIgnoreCase("DNAME")) {
			qw = "select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in "
					+ "(select distinct sus_no from Tb_Miso_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') "
					+ "and (lower(unit_name) like :sus_no or Upper(unit_name) like :sus_no) order by unit_name";
		}

		if (b.equalsIgnoreCase("MISO")) {
			qr = qw;
		}
		if (b.equalsIgnoreCase("UNIT")) { // ok
			qcnd = "and sus_no=:susval";
			qr = qw + qcnd;
		}
		if (b.equalsIgnoreCase("BRIGADE")) { // ok
			qcnd = "and form_code_control=:susval";
			qr = qw + qcnd;
		}
		if (b.equalsIgnoreCase("DIVISION")) {
			qcnd = "and substring(form_code_control,1,6)=:susval";
			qr = qw + qcnd;
		}
		if (b.equalsIgnoreCase("CORPS")) {
			qcnd = "and substring(form_code_control,1,3)=:susval";
			qr = qw + qcnd;
		}
		if (b.equalsIgnoreCase("COMMAND")) {
			qcnd = "and substring(form_code_control,1,1)=:susval";
			qr = qw + qcnd;
		}
		if (b.equalsIgnoreCase("LINE_DTE")) {
			qcnd = "and arm_code=:susval";
			qr = qw + qcnd;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(qr);
		if (c.equalsIgnoreCase("SUS") || c.equalsIgnoreCase("DSUS")) {
			q.setParameter("sus_no", a + "%");
			q.setParameterList("med", medicalUnitPrifix);
		}
		if (c.equalsIgnoreCase("NAME") || c.equalsIgnoreCase("DNAME")) {
			q.setParameter("sus_no", '%' + a.toLowerCase() + '%');
			q.setParameter("sus_no", '%' + a.toUpperCase() + '%');
			q.setParameterList("med", medicalUnitPrifix);
		}
		if (b.equalsIgnoreCase("UNIT")) {
			q.setParameter("susval", d);
		}
		if (b.equalsIgnoreCase("BRIGADE")) { // ok
			q.setParameter("susval", d);
		}
		if (b.equalsIgnoreCase("DIVISION")) {
			q.setParameter("susval", d.substring(0, 6));
		}
		if (b.equalsIgnoreCase("CORPS")) {
			q.setParameter("susval", d.substring(0, 3));
		}
		if (b.equalsIgnoreCase("COMMAND")) {
			q.setParameter("susval", d.substring(0, 1));
		}
		if (b.equalsIgnoreCase("LINE_DTE")) {
			q.setParameter("susval", d);
		}
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	public String getMNHUnitNameBySUSNoMethod(String y, HttpSession s1) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return "";
		}

	}

	@RequestMapping(value = "/getMNHSUSNoByUnitName", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHSUSNoByUnitName(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no = 'Active'");
		q.setParameter("unit_name", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHCommandNameBySUSorUnit", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHCommandNameBySUSorUnit(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select q.unit_name as hq_name from(select concat(substring(a.form_code_control,1,1),'000000000') as hq from Miso_Orbat_Unt_Dtl a "
						+ "where a.sus_no=:y or a.unit_name=:y1 and a.status_sus_no='Active') p,nrv_hq q where p.hq=q.form_code_control");
		// q.setInteger("a1", 1);
		// q.setInteger("a2", 1);
		// q.setParameter("a3", "000000000");
		q.setParameter("y", y);
		q.setParameter("y1", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHUserList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHUserList(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select concat(a.userName,':',a.userId) from UserLogin a, UserRole b, Role c "
				+ "where a.userId=b.userId and b.roleId=c.roleId and c.role_type='DEO' and c.access_lvl='MISO' and c.sub_access_lvl = 'Medical'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMNHHirarNameBySUS", method = RequestMethod.POST) // For Fetching Single Hirar. Name By
																					// SUS No
	public @ResponseBody List<String> getMNHHirarNameBySUS(String FindWhat, String a, HttpSession s1) {

		List<String> list = mnh1_Dao.getMNHHirarNameBySUS(FindWhat, a);
		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMedSystemCode", method = RequestMethod.POST) // For Encryption Based System Code Fetch
	public @ResponseBody List<String> getMedSystemCode(String code, String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select sys_code_value from Tb_Med_System_Code where sys_code=:code order by order_index");
		q.setParameter("code", code);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedSystemCodeAndDecs", method = RequestMethod.POST)
	public @ResponseBody List<String> getMedSystemCodeAndDecs(String code, String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select concat(sys_code_value,':',sys_code_desc) from Tb_Med_System_Code where sys_code=:code order by order_index");
		q.setParameter("code", code);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedDepCode", method = RequestMethod.POST) // For Department id and name fetch
	public @ResponseBody List<String> getMedDepCode(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct concat(id,':',dept_name) from Tb_Med_Opd_Sp_Department where status='ACTIVE'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedAllDepName", method = RequestMethod.POST) // For Department Autocomplete
	public @ResponseBody List<String> getMedAllDepName(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct dept_name from Tb_Med_Opd_Sp_Department where "
				+ "(lower(dept_name) like :a or Upper(dept_name) like :a) and status='ACTIVE'");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedProceCode", method = RequestMethod.POST) // For Procedure id and name fetch based on
																				// department id
	public @ResponseBody List<String> getMedProceCode(Integer d_id, String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct concat(id,':',proc_name) from Tb_Med_Opd_Sp_Procedure_master where dept_id=:d_id and status='ACTIVE'");
		q.setInteger("d_id", d_id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}

	}

	@RequestMapping(value = "/getMedDistinctProcName", method = RequestMethod.POST) // For Department Based Procedure
																					// Autocomplete
	public @ResponseBody List<String> getMedDistinctProcName(String a, Integer b, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"Select distinct proc_name from Tb_Med_Opd_Sp_Procedure_master where dept_id=:b and status='ACTIVE' and "
						+ "(lower(proc_name) like :a or Upper(proc_name) like :a)");
		q.setInteger("b", b);
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedDistinctSubProcName", method = RequestMethod.POST) // For Department & Procedure
																						// based sub procedure
																						// Autocomplete
	public @ResponseBody List<String> getMedDistinctSubProcName(String a, Integer b, Integer c, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"Select distinct subproc_name from Tb_Med_Opd_Sp_Subprocedure where dept_id=:b and proc_id=:c and status='ACTIVE' and "
						+ "(lower(subproc_name) like :a or Upper(subproc_name) like :a)");
		q.setInteger("b", b);
		q.setInteger("c", c);
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedDiseaseName", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch
	public @ResponseBody List<String> getMedDiseaseName(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct disease_name,id from Tb_Med_Daily_Surv_Disease_Mstr where disease_type='NOTIFY' order by disease_name");
		} else {
			q = session.createQuery("select distinct disease_name from Tb_Med_Daily_Surv_Disease_Mstr where "
					+ "(lower(disease_name) like :a or Upper(disease_name) like :a) order by disease_name");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedDiseaseMOSQUITOName", method = RequestMethod.POST) // For Encryption with & without
																						// Disease Name Fetch
	public @ResponseBody List<String> getMedDiseaseMOSQUITOName(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct id,disease_name from Tb_Med_Daily_Surv_Disease_Mstr where disease_type='MOSQUITO' order by disease_name");
		} else {
			q = session.createQuery("select distinct disease_name from Tb_Med_Daily_Surv_Disease_Mstr where "
					+ "(lower(disease_name) like :a or Upper(disease_name) like :a) order by disease_name");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMNHDistinctICDList", method = RequestMethod.POST) // For Distinct ICD
																					// code,description,short_form,short_desciption
																					// based Autocomplete
	public @ResponseBody List<String> getMNHDistinctICDList(String a, String b, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String col = null;
		if (b.equalsIgnoreCase("1")) {
			col = "icd_code";
		}
		if (b.equalsIgnoreCase("2")) {
			col = "icd_description";
		}
		if (b.equalsIgnoreCase("3")) {
			col = "short_form";
		}
		if (b.equalsIgnoreCase("4")) {
			col = "short_desc";
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (b.equalsIgnoreCase("1") || b.equalsIgnoreCase("2") || b.equalsIgnoreCase("3") || b.equalsIgnoreCase("4")) {
			q = session.createQuery("select distinct " + col + " from Tb_Med_Disease_Cause where (lower(" + col
					+ ") like :a or Upper(" + col + ") like :a)");
		}

		if (b.equalsIgnoreCase("ALL")) {
			q = session
					.createQuery("select distinct concat(icd_code,'-',icd_description) from Tb_Med_Disease_Cause where "
							+ "(lower(icd_code) like :a or Upper(icd_code) like :a or lower(icd_description) like :a or Upper(icd_description) like :a or "
							+ "lower(short_form) like :a or Upper(short_form) like :a or lower(short_desc) like :a or Upper(short_desc) like :a)");
		}

		if (b.equalsIgnoreCase("ALL2")) {
			q = session
					.createQuery("select distinct concat(icd_code,'-',icd_description) from Tb_Med_Disease_Cause where "
							+ "(upper(icd_code) like 'V%' or upper(icd_code) like 'W%' or upper(icd_code) like 'X%' or upper(icd_code) like 'Y%') and "
							+ "(lower(icd_code) like :a or Upper(icd_code) like :a or lower(icd_description) like :a or Upper(icd_description) like :a or "
							+ "lower(short_form) like :a or Upper(short_form) like :a or lower(short_desc) like :a or Upper(short_desc) like :a)");
		}

		q.setParameter("a", a.toLowerCase() + '%');
		q.setParameter("a", a.toUpperCase() + '%');
		if (b.equalsIgnoreCase("ALL")) {
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
		}
		q.setMaxResults(30);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHICDCodeToCauseVi", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHICDCodeToCauseVi(String a, String b, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		String col1 = null;
		String col2 = null;
		if (b.equalsIgnoreCase("1")) {
			col1 = "icd_code";
			col2 = "icd_description";
		}
		if (b.equalsIgnoreCase("2")) {
			col1 = "icd_description";
			col2 = "icd_code";
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct " + col1 + " from Tb_Med_Disease_Cause where (lower(" + col2
				+ ") like :a or Upper(" + col2 + ") like :a)");
		q.setParameter("a", a.toLowerCase() + '%');
		q.setParameter("a", a.toUpperCase() + '%');
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMedAuthBedsTotal", method = RequestMethod.POST) // For Department id and name fetch
	public @ResponseBody List<String> getMedAuthBedsTotal(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct cast(total_all as text) from TB_Med_Authorisation_All where sus_no=:y ");
		q.setParameter("y", y);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getTradeList", method = RequestMethod.POST) // For Encryption Based Trade List Fetch
																			// (Optimization Require)
	public @ResponseBody List<String> getTradeList(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct id,sys_code_value_desc from Tb_Med_System_Code where sys_code='TRADE'");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedrelationList", method = RequestMethod.POST) // For Encryption Based System Code
																				// Fetch (Optimization Require)
	public @ResponseBody List<String> getMedrelationList(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sys_code_value from Tb_Med_System_Code where sys_code='RELATION' order by order_index");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedTargetDiseases", method = RequestMethod.POST) // For Target Diseases Based
																					// Autocomplete
	public @ResponseBody List<String> getMedTargetDiseases(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct target_diseases from Tb_Med_Surv_Master where "
				+ "(lower(target_diseases) like :a or Upper(target_diseases) like :a) order by target_diseases");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedAllSystemCode", method = RequestMethod.POST) // For Target Diseases Based
																					// Autocomplete
	public @ResponseBody List<String> getMedAllSystemCode(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct sys_code from Tb_Med_System_Code where "
				+ "(lower(sys_code) like :a or Upper(sys_code) like :a) order by sys_code");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedDiseaseType", method = RequestMethod.POST) // For Target Diseases Based Autocomplete
	public @ResponseBody List<String> getMedDiseaseType(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct disease_type from Tb_Med_Daily_Surv_Disease_Mstr where "
				+ "(lower(disease_type) like :a or Upper(disease_type) like :a) order by disease_type");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedrankList", method = RequestMethod.POST) // For Encryption Based System Code Fetch
																			// (Optimization Require)
	public @ResponseBody List<String> getMedrankList(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct rank_code from Tb_Med_RankCode where "
				+ "(lower(rank_code) like :a or Upper(rank_code) like :a) order by rank_code");

		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedDistinctRankList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMedDistinctRankList(String enc, String a, String b, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct rank_code from Tb_Med_RankCode where service=:a and category_code=:b order by rank_code");
		q.setParameter("a", a);
		q.setParameter("b", b);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedAllPrincipleList", method = RequestMethod.POST) // For Target Diseases Based
																					// Autocomplete
	public @ResponseBody List<String> getMedAllPrincipleList(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct disease_principal as principal_cause from Tb_Med_Disease_Cause order by disease_principal");
		// Query q = session.createQuery("select distinct disease_principal as
		// principal_cause from Med_principle_dis order by disease_principal");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedDiseaseAIRName", method = RequestMethod.POST) // For Encryption with & without
																					// Disease Name Fetch
	public @ResponseBody List<String> getMedDiseaseAIRName(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct id,disease_name from Tb_Med_Daily_Surv_Disease_Mstr where disease_type='AIR' order by disease_name");
		} else {
			q = session.createQuery("select distinct disease_name from Tb_Med_Daily_Surv_Disease_Mstr where "
					+ "(lower(disease_name) like :a or Upper(disease_name) like :a) order by disease_name");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedDiseasefecoName", method = RequestMethod.POST) // For Encryption with & without
																					// Disease Name Fetch
	public @ResponseBody List<String> getMedDiseasefecoName(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct id,disease_name from Tb_Med_Daily_Surv_Disease_Mstr where disease_type='FECO-ORAL' order by disease_name");
		} else {
			q = session.createQuery("select distinct disease_name from Tb_Med_Daily_Surv_Disease_Mstr where "
					+ "(lower(disease_name) like :a or Upper(disease_name) like :a) order by disease_name");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	///// LMC
	@RequestMapping(value = "/getArmssrvice", method = RequestMethod.POST) // For Encryption Based System Code Fetch
	public @ResponseBody List<String> getArmssrvice(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code order by arm_desc");
		// q.setParameter("code", code);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMNHRank", method = RequestMethod.POST) // For Fetch Rank based on Service and Category
	public @ResponseBody List<String> getMNHRank(String a1, String a2, HttpSession s1) {
		List<String> list = mnh1_Dao.getMNHRank(a1, a2);

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHUnitNameBySUSNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHUnitNameBySUSNo(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMedSystemCodeQua", method = RequestMethod.POST) // For Encryption Based System Code
																					// Fetch
	public @ResponseBody List<String> getMedSystemCodeQua(String code, String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sys_code_value,sys_code_desc from Tb_Med_System_Code where sys_code=:code order by order_index");
		q.setParameter("code", code);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getPrincipalCause", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch
	public @ResponseBody List<String> getPrincipalCause(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct disease_principal from Tb_Med_Disease_Cause  order by disease_principal");
		} else {
			q = session.createQuery("select distinct disease_principal from Tb_Med_Disease_Cause where \"\r\n"
					+ "(lower(disease_principal) like :a or Upper(disease_principal) like :a");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getDiseaseMMR", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody List<String> getDiseaseMMR(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery("select distinct disease_mmr from Tb_Med_Disease_Cause  order by disease_mmr");
		} else {
			q = session.createQuery("select distinct disease_mmr from Tb_Med_Disease_Cause where "
					+ "(lower(disease_mmr) like :a or Upper(disease_mmr) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getDiseaseASO", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody List<String> getDiseaseASO(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery("select distinct disease_aso from Tb_Med_Disease_Cause  order by disease_aso");
		} else {
			q = session.createQuery("select distinct disease_aso from Tb_Med_Disease_Cause where "
					+ "(lower(disease_aso) like :a or Upper(disease_aso) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getBlockDis", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody List<String> getBlockDis(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery(
					"select distinct block_description from Tb_Med_Disease_Cause  order by block_description");
		} else {
			q = session.createQuery("select distinct block_description from Tb_Med_Disease_Cause where "
					+ "(lower(block_description) like :a or Upper(block_description) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	// END YASH METHOD //

	// Note: New Methods for ICD CODE fk relationship in transaction process of
	// INPUT FOR DGMS /DATE:13-07-2020/
	public String getMNHDistinctICD_Diagnosis(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		q = session.createQuery(
				"select distinct concat(icd_code,'-',icd_description) from Tb_Med_Disease_Cause where id=:id");

		q.setParameter("id", id);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String icd_code_desc = "";
		if (list.size() > 0) {
			icd_code_desc = list.get(0);
		}

		return icd_code_desc;

	}

	public int getID_ICD_Diagnosis(String icd_code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("select id from Tb_Med_Disease_Cause where icd_code=:icd_code");
		q.setParameter("icd_code", icd_code);
		@SuppressWarnings("unchecked")
		int count_list1 = (int) q.uniqueResult();
		tx.commit();
		session.close();
		return count_list1;
	}

	@RequestMapping(value = "/getReason_Elisa", method = RequestMethod.POST)
	public @ResponseBody List<String> getReason_Elisa() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"SELECT sys_code_value,sys_code_desc FROM Tb_Med_System_Code where sys_code ='REASON_ELISA_SCREENING'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getSource_Infection", method = RequestMethod.POST)
	public @ResponseBody List<String> getSource_Infection() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"SELECT sys_code_value,sys_code_desc FROM Tb_Med_System_Code where sys_code ='SOURCE_INFECTION'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	// ******************************END********************************************************/
	@RequestMapping(value = "/getMNHUnitAndSusNoList_daily_dsurve", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHUnitAndSusNoList_daily_dsurve(HttpSession s1, HttpServletRequest request) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String[] medicalUnitPrifix = (String[]) s1.getAttribute("medicalUnitPrifix");
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct concat(unit_name,':',sus_no) as unit from Miso_Orbat_Unt_Dtl where status_sus_no='Active' "
						+ "and substring(sus_no, 1,4) in (:med) and sus_no not in(select sus_no from TB_MED_DAILY_DISEASE_HOSP_ASSIGN)");
		q.setParameterList("med", medicalUnitPrifix);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHASSIGNList_daily_dsurve", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHASSIGNList_daily_dsurve(
			@RequestParam(value = "user_id", required = false) String user_id, HttpSession s1) {

		String[] medicalUnitPrifix = (String[]) s1.getAttribute("medicalUnitPrifix");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q = session.createQuery("select p.unit_name,a.sus_no from TB_MED_DAILY_DISEASE_HOSP_ASSIGN a,UserLogin l ,Miso_Orbat_Unt_Dtl p "
						+ "		 where a.user_id=l.userId and  a.user_id =:user_id1 and p.sus_no=a.sus_no and p.status_sus_no='Active' "
						+ "	 and substring(p.sus_no,1,4) in (:med))");

		q.setParameterList("med", medicalUnitPrifix);
		if (user_id != null && !user_id.equals("")) {
			q.setParameter("user_id1", Integer.parseInt(user_id));
		} else {
			q.setParameter("user_id1", 0);
		}

		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;

	}
	@RequestMapping(value = "/getMNHUnitAndSusNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHUnitAndSusNoList(HttpSession s1, HttpServletRequest request) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String[] medicalUnitPrifix = (String[]) s1.getAttribute("medicalUnitPrifix");
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct concat(unit_name,':',sus_no) as unit from Miso_Orbat_Unt_Dtl where status_sus_no='Active' "
						+ "and substring(sus_no, 1,4) in (:med) and sus_no not in(select sus_no from Tb_Med_Hosp_Assign)");
		q.setParameterList("med", medicalUnitPrifix);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMNHASSIGNList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMNHASSIGNList(
			@RequestParam(value = "user_id", required = false) String user_id, HttpSession s1) {

		String[] medicalUnitPrifix = (String[]) s1.getAttribute("medicalUnitPrifix");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q = session.createQuery("select p.unit_name,a.sus_no from Tb_Med_Hosp_Assign a,UserLogin l ,Miso_Orbat_Unt_Dtl p "
						+ "		 where a.user_id=l.userId and  a.user_id =:user_id1 and p.sus_no=a.sus_no and p.status_sus_no='Active' "
						+ "	 and substring(p.sus_no,1,4) in (:med))");

		q.setParameterList("med", medicalUnitPrifix);
		if (user_id != null && !user_id.equals("")) {
			q.setParameter("user_id1", Integer.parseInt(user_id));
		} else {
			q.setParameter("user_id1", 0);
		}

		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;

	}

	@RequestMapping(value = "/getMNHDistinctICDList_ID", method = RequestMethod.POST) // For Distinct ICD
																						// code,description,short_form,short_desciption
																						// based Autocomplete
	public @ResponseBody List<String> getMNHDistinctICDList_ID(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		List<String> list = new ArrayList<>();
		list.add(String.valueOf(getID_ICD_Diagnosis(y)));

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getDisease_type", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch
	public @ResponseBody List<String> getDisease_type(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery("select distinct disease_type from Tb_Med_Disease_Cause  order by disease_type");
		} else {
			q = session.createQuery("select distinct disease_type from Tb_Med_Disease_Cause where "
					+ "(lower(disease_type) like :a or Upper(disease_type) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getDisease_subtype", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch
	public @ResponseBody List<String> getDisease_subtype(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session
					.createQuery("select distinct disease_subtype from Tb_Med_Disease_Cause  order by disease_subtype");
		} else {
			q = session.createQuery("select distinct disease_subtype from Tb_Med_Disease_Cause where "
					+ "(lower(disease_subtype) like :a or Upper(disease_subtype) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	// NEED TO MERGE THIS METHOD WITH AUTH METHOD
	@RequestMapping(value = "/getMedBedslaid_outTotal", method = RequestMethod.POST) // For Department id and name fetch
	public @ResponseBody List<String> getMedBedslaid_outTotal(String enc, String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct cast(laid_out as text) from TB_Med_Authorisation_All where sus_no=:y");
		q.setParameter("y", y);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getAllIcdCauselist", method = RequestMethod.POST) // For Encryption Based System Code
																				// Fetch
	public @ResponseBody List<String> getAllIcdCauselist(String enc, HttpSession s1) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"SELECT distinct icd_code,disease_principal,disease_mmr,disease_aso,block_description from Tb_Med_Disease_Cause order by icd_code");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getdis_principal_mmr", method = RequestMethod.POST)
	public @ResponseBody ArrayList<String> getdis_principal_mmr(String value, String col_name) {

		ArrayList<String> list = mnh7_Dao.getdis_principal_mmr_dao(value, col_name);
		return list;
	}

	public int CompareDate(String fdt, String tdt) {
		if (fdt.compareTo(tdt) > 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@RequestMapping(value = "/getDis_subtype", method = RequestMethod.POST) // For Encryption with & without Disease
																			// Name Fetch
	public @ResponseBody List<String> getDis_subtype(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session
					.createQuery("select distinct disease_subtype from Tb_Med_Disease_Cause  order by disease_subtype");
		} else {
			q = session.createQuery("select distinct disease_subtype from Tb_Med_Disease_Cause where "
					+ "(lower(disease_subtype) like :a or Upper(disease_subtype) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getDis_type", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody List<String> getDis_type(String enc, String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (a.equalsIgnoreCase("")) {
			q = session.createQuery("select distinct disease_type from Tb_Med_Disease_Cause  order by disease_type");
		} else {
			q = session.createQuery("select distinct disease_type from Tb_Med_Disease_Cause where "
					+ "(lower(disease_type) like :a or Upper(disease_type) like :a)");
			q.setParameter("a", '%' + a.toLowerCase() + '%');
			q.setParameter("a", '%' + a.toUpperCase() + '%');
			q.setMaxResults(10);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMedAllSystemCodevalue", method = RequestMethod.POST)
	public @ResponseBody List<String> getMedAllSystemCodevalue(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct sys_code_value from Tb_Med_System_Code where "
				+ "(lower(sys_code_value) like :a or Upper(sys_code_value) like :a) order by sys_code_value");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMedAllSystemCodedesc", method = RequestMethod.POST)
	public @ResponseBody List<String> getMedAllSystemCodedesc(String a, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select distinct sys_code_desc from Tb_Med_System_Code where "
				+ "(lower(sys_code_desc) like :a or Upper(sys_code_desc) like :a) order by sys_code_desc");
		q.setParameter("a", '%' + a.toLowerCase() + '%');
		q.setParameter("a", '%' + a.toUpperCase() + '%');
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mnh1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}
	 @RequestMapping(value = "/getaction_bySystem", method = RequestMethod.POST)
     public @ResponseBody List<String> getaction_bySystem() {
             Session session1 = HibernateUtil.getSessionFactory().openSession();
             Transaction tx1 = session1.beginTransaction();
             Query q1 = session1.createQuery(
                             "SELECT sys_code_value,sys_code_desc FROM Tb_Med_System_Code where sys_code ='ACTION_BY_SHO' order by id");
             @SuppressWarnings("unchecked")
             List<String> list = (List<String>) q1.list();
             tx1.commit();
             session1.close();
             return list;
     }
	
	@RequestMapping(value = "/admin/GetPersonnelNoDatamnh", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> GetPersonnelNoDatamnh(String personnel_no) throws ParseException {	
		//String  personnel_no = request.getParameter("personnel_no");
		return mnh1_Dao.GetPersonnelNoDatamnh(personnel_no);
	}
////orbat 
	@RequestMapping(value = "/getTargetUnitsNameActiveListnew", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveListnew(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
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
	
	@RequestMapping(value = "/getfood_bySystem", method = RequestMethod.POST)
	public @ResponseBody List<String> getfood_bySystem() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"Select distinct id,food from Tb_Med_Food_Master order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
	
		tx1.commit();
		session1.close();
		return list;
	}
    public List<String> getMedDisease_Type() {

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session1.beginTransaction();
        Query q1 = session1
                        .createQuery("select  distinct id,upper(disease_type)  as disease_type from Tb_Med_Daily_Surv_Disease_Mstr   order by disease_type ");
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) q1.list();
        tx1.commit();
        session1.close();
        return list;
}
	
}
