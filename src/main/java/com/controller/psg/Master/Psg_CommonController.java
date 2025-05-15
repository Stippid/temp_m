package com.controller.psg.Master;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.models.psg.Transaction.TB_PSG_MISO_ROLE_HDR_STATUS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.joran.conditional.IfAction;
import ch.qos.logback.core.net.SyslogOutputStream;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Psg_CommonController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired

	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;	
	// Name Fetch
	public List<UserLogin> getUseridlist(String to_sus_no) {

    	
        Session session1 = HibernateUtil.getSessionFactory().openSession();

        Transaction tx1 = session1.beginTransaction();

        Query q1 = session1.createQuery(
                        " from UserLogin where user_sus_no=:user_sus_no");
        q1.setString("user_sus_no", to_sus_no);
        @SuppressWarnings("unchecked")

        List<UserLogin> list = (List<UserLogin>) q1.list();

        tx1.commit();

        session1.close();

        return list;

}

	@RequestMapping(value = "/getMedCountryName", method = RequestMethod.POST) // For Encryption with & without Disease
	
	public @ResponseBody List<String> getMedCountryName(String a, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = null;

		if (a.equalsIgnoreCase("")) {

			q = session.createQuery(
					"select distinct id,upper(name) as name from TB_COUNTRY where status = 'active' and id!=0  order  by name");

		} else {

			q = session.createQuery("select distinct upper(name) as name from TB_COUNTRY where "

					+ "(lower(name) like :a or Upper(name) like :a) and status = 'active' and id!=0 order by name");

			q.setParameter("a", '%' + a.toLowerCase() + '%');

			q.setParameter("a", '%' + a.toUpperCase() + '%');

			q.setMaxResults(10);

		}

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	public List<String> getServiceCategoryList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='SERVICE_CATEGORY'  order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getMedStateName", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch

	public @ResponseBody List<String> getMedStateName(String a, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = null;

		if (a.equalsIgnoreCase("")) {

			q = session.createQuery(
					"select distinct id,upper(state_name) as state_name from TB_STATE where status='active' order  by state_name");

		} else {

			q = session.createQuery("select distinct uppere(state_name) as state_name from TB_STATE where "

					+ "(lower(state_name) like :a or Upper(state_name) like :a) and status='active' order by state_name");

			q.setParameter("a", '%' + a.toLowerCase() + '%');

			q.setParameter("a", '%' + a.toUpperCase() + '%');

			q.setMaxResults(10);

		}

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getMedDistrictName", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch

	public @ResponseBody List<String> getMedDistrictName(String a, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = null;

		if (a.equalsIgnoreCase("")) {

			q = session.createQuery(
					"select distinct id,upper(district_name) as district_name from TB_DISTRICT where status = 'active' order  by district_name");

		} else {

			q = session.createQuery("select distinct upper(district_name) as district_name from TB_DISTRICT where "

					+ "(lower(district_name) like :a or Upper(district_name) like :a) and status = 'active' order by district_name");

			q.setParameter("a", '%' + a.toLowerCase() + '%');

			q.setParameter("a", '%' + a.toUpperCase() + '%');

			q.setMaxResults(10);

		}

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getMedCityName", method = RequestMethod.POST) // For Encryption with & without Disease
																			// Name Fetch

	public @ResponseBody List<String> getMedCityName(String a, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = null;

		if (a.equalsIgnoreCase("")) {

			q = session.createQuery(
					"select distinct id,upper(city_name) as city_name  from TB_CITY  where status = 'active' order  by city_name");

		} else {

			q = session.createQuery("select distinct upper(city_name) as city_name  from TB_CITY where "

					+ "(lower(city_name) like :a or Upper(city_name) like :a) and status = 'active' order by city_name");

			q.setParameter("a", '%' + a.toLowerCase() + '%');

			q.setParameter("a", '%' + a.toUpperCase() + '%');

			q.setMaxResults(10);

		}

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	// -------------------------------Type Of
	// Commission-------------------------------------

	public List<String> getTypeOfCommissionList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(comn_name)  as comn_name from TB_COMMISSION_TYPE where status = 'active' order by comn_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Village-------------------------------------

	// -------------------------------Nationality-------------------------------------

	public List<String> getNationalityList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select nationality_id,upper(nationality_name) as nationality_name  from TB_NATIONALITY  where status='active' order by nationality_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Religion-------------------------------------

	public List<String> getReligionList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select religion_id,upper(religion_name)  as religion_name from TB_RELIGION  where status = 'active' order by religion_id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Marital
	// Status-------------------------------------

	public List<String> getMarital_statusList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select marital_id,upper(marital_name) as marital_name from TB_MARITAL_STATUS where status = 'active' order by marital_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Blood-------------------------------------

	public List<String> getBloodList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(blood_desc) as blood_desc from TB_BLOOD_GROUP  where status = 'active' order by blood_desc");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Hair
	// Colour-------------------------------------

	public List<String> getHair_ColourList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(hair_cl_name) as hair_cl_name from TB_HAIR_COLOUR where status='active' order by hair_cl_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Eye
	// Colour-------------------------------------

	public List<String> getEye_ColourList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(eye_cl_name) as eye_cl_name from TB_EYE_COLOUR where status='active' order by eye_cl_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Type_of_Employment
	// -------------------------------------

	public List<String> getType_of_EmploymentList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(name) as name from TB_TYPE_OF_EMPLOYMENT where status='active'  order by name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Medal
	// Type-------------------------------------

	public List<String> getMedalTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(medal_name)  as medal_name from TB_MEDAL_TYPE where status='active' order by medal_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------CourseType
	// -------------------------------------

	// -------------------------------Bank-------------------------------------

	public List<String> getBankList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(bank_name)  as bank_name from TB_BANK  where status='active' order by bank_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Relation-------------------------------------

	public List<String> getRelationList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(relation_name) as relation_name from TB_RELATION  where status='active' order by relation_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Gender-------------------------------------

	public List<String> getGenderList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(gender_name)  as gender_name from TB_GENDER Where status='active' order by gender_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Personnel
	// Type-------------------------------------

	public List<String> getPersonnel_typeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(personnel_name) as personnel_name from TB_PERSONNEL_TYPE  order by personnel_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// -------------------------------Country-------------------------------------

	public List<String> getCountryList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select id,upper(name)  as name from TB_COUNTRY  where status= 'active' order by name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getRegimentFromArmCode", method = RequestMethod.POST)
	public @ResponseBody List<String> getRegtList(String arm_code) {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Query q1 = null;
		Transaction tx1 = session1.beginTransaction();
		// 260194
		// if(arm_code!=null && !arm_code.equals("")){
		// q1= session1.createQuery("SELECT a.arm_code, a.arm_desc FROM
		// Tb_Miso_Orabt_Arm_Code a WHERE SUBSTR(a.arm_code,1,2)=:arm_code and
		// a.arm_code not in ('0700','0800') ORDER BY 1 ");
		// q1.setString("arm_code", arm_code.substring(0,2));
		// }
		// else
		q1 = session1.createQuery(
				"SELECT a.arm_code, a.arm_desc FROM Tb_Miso_Orabt_Arm_Code a WHERE SUBSTR(a.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and a.arm_code not in ('0700','0800') ORDER BY 1 ");

	

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getpersonnel_no", method = RequestMethod.POST)

	public @ResponseBody List<String> getpersonnel_no(String personel_no, HttpSession sessionUserId) {         

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		// try{

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String roleType = sessionUserId.getAttribute("roleType").toString();
		String qry="";
		if(roleAccess.equals("DGMS")) {
			qry= "and substr(personnel_no,1,2) in ('NR','NS')";
		}

		Query q = null;

		q = sessionHQL.createQuery(
				"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where Upper(personnel_no) like :personel_no "+qry+" ")
				.setMaxResults(10);
		// 26-01-1994
		q.setParameter("personel_no", personel_no.toUpperCase() + '%');

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

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

				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());

			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();

			}

			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));

			FinalList.add(base64EncodedEncryptedCode);

		}

		FinalList.add(enckey + "4bsjyg==");

		return FinalList;

	}



	///bisag  v2 080223(rank query change according to cue table as per change by miso ...codination with amit sir and arunsir too)
	public List<String> getTypeofRankList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' " + 
				"and parent_code ='0' and code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') and status_active = 'Active' order by code");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();
		
		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getPreCadetStatusList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,description from TB_PRE_CADET_STATUS where status='active' order by description");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getNCCTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,label,domain_id from TB_NCC_TYPE_ENTRY where status='active' order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getLanguageSTDList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select distinct id,upper(name) from TB_LANG_STD where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getLanguagePROOFList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select distinct id,name from TB_LANG_PROOF where status = 'active' order by name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getMedCatList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select domainid,label from T_Domain_Value where domainid='MEDCAT'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// public List<String> getRHList() {
	//
	// Session session1 = HibernateUtil.getSessionFactory().openSession();
	//
	// Transaction tx1 = session1.beginTransaction();
	//
	// Query q1 = session1.createQuery("select id,rh from TB_RH order by rh");
	//
	// @SuppressWarnings("unchecked")
	//
	// List<String> list = (List<String>) q1.list();
	//
	// tx1.commit();
	//
	// session1.close();
	//
	// return list;
	//
	// }

	public List<String> getQualificationTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='QUALIFICATION_TYPE'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getInstituteNameList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select  id from TB_QUALIFICATION  where status = 'active' order by qualification_type");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getLanguageList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,language from TB_LANGUAGE where status='active' order by language");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getMothertoungeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select id,mothertounge from TB_MOTHERTOUNGE where status='active' order by mothertounge");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getSelfMotFatName", method = RequestMethod.POST) // For Encryption with & without Disease
																				// Name Fetch
	public @ResponseBody ArrayList<ArrayList<String>> getSelfMotFatName(BigInteger comm_id, HttpSession s1) {

		return psg_com.getSelfMotFatName(comm_id);

	}

	@RequestMapping(value = "/getSpouseName", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody ArrayList<ArrayList<String>> getSpouseName(BigInteger comm_id, HttpSession s1) {

		return psg_com.getSpouseName(comm_id);

	}

	@RequestMapping(value = "/getChildName", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody ArrayList<ArrayList<String>> getChildName(BigInteger comm_id, String rela, HttpSession s1) {

		return psg_com.getChildName(comm_id, rela);

	}

	@RequestMapping(value = "/getChilddob", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch
	public @ResponseBody ArrayList<ArrayList<String>> getChilddob(String id, BigInteger comm_id, HttpSession s1) {

		return psg_com.getChilddob(id, comm_id);

	}

	/*
	 * public List<String> getTehsilList() {
	 * 
	 * Session session1 = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction tx1 = session1.beginTransaction();
	 * 
	 * Query q1 =
	 * session1.createQuery("select id,tehsil from TB_TEHSIL  order by tehsil");
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * List<String> list = (List<String>) q1.list();
	 * 
	 * tx1.commit();
	 * 
	 * session1.close();
	 * 
	 * return list;
	 * 
	 * }
	 */

	public List<String> getPersonalType() {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select label,codevalue from T_Domain_Value where domainid='PERSONAL_TYPE' order by codevalue ");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	public List<String> getPersonalRemainder() {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select label,codevalue from T_Domain_Value where domainid='PERSONAL_REMINDER' order by codevalue ");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	public List<String> getTypeofAppointementList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(description) as description from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' and status_active = 'Active' order by description ");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getNon_EffectiveList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='NON_EFFECTIVE' order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getParentArmList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"SELECT SUBSTR(a.arm_code,1,2)|| '00' AS arm, a.arm_desc FROM Tb_Miso_Orabt_Arm_Code a WHERE a.arm_code in ('0100','0120','0200','0300','0400','0500'," + 
				" '0600', '0800','0900','1000','1100','1200','1300','1400','1500','1600','1700','1800','1900'," + 
				" '2000','2100','2200','2300','2400','3300','3400','3700','4600','4700','4800') order by a.arm_desc");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	
	/*public List<String> getUserarm_codeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select arm_desc from Tb_Miso_Orabt_Arm_Code");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	*/
	
	public List<String> getUserarm_code_dasboardList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,arm_desc  from Tb_Miso_Orabt_Arm_Code  order by arm_desc");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	

	public List<String> getInstitute(String type) {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(institute_name) as institute_name from TB_INSTITUTE_LIST where institute_type=:institute_type and status='active' order by institute_name ");
		q1.setString("institute_type", type);
		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	
//	-------------- Pranay 31.05
	
	public List<String> getInstitute_psg(String type) {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(institute_name) as institute_name from TB_INSTITUTE_LIST where institute_type in ('1','2') and status='active' order by institute_name ");
		/*q1.setString("institute_type", type);*/
		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	
	

	
	public List<String> getHeight() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct height_id,centimeter from TB_Height where status='active' order by centimeter");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	//// Address dependency method

	@RequestMapping(value = "/getStateListFormcon1", method = RequestMethod.POST)

	public @ResponseBody List<String> getStateListFormcon1(int contry_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select state_id,upper(state_name)  as state_name from TB_STATE where country_id=:contry_id and status='active' order by state_name");

		q.setParameter("contry_id", contry_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getDistrictListFormState1", method = RequestMethod.POST)

	public @ResponseBody List<String> getDistrictListFormState1(int state_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select district_id,upper(district_name) as district_name from TB_DISTRICT where state_id=:state_id and status = 'active' order by district_name");

		q.setParameter("state_id", state_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getTehsilListFormDistrict1", method = RequestMethod.POST)

	public @ResponseBody List<String> getTehsilListFormDistrict1(int Dist_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select city_id,upper(city_name) as city_name from TB_CITY where district_id=:Dist_id and status = 'active' order by city_name");

		q.setParameter("Dist_id", Dist_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	public List<String> getShapeStatusList() {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select label,codevalue from T_Domain_Value where domainid='SHAPE_STATUS' order by codevalue ");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	// -------------------------Battalion for Institute master-------------------

	public List<String> getBattalionList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(battalion_name) as battalion_name from TB_BATTALION where status='active' order by battalion_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// ----------------------Company for Institute master---------------------------

	public List<String> getCompanyList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(company_name) as company_name from TB_COMPANY where status='active' order by company_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getInsatitute_btn", method = RequestMethod.POST)

	public @ResponseBody List<String> getInsatitute_btn(int inst_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select distinct  btn.id,upper(btn.battalion_name) as battalion_name from TB_BATTALION btn ,TB_INSTITUTE ins  where ins.bn_id=btn.id and  ins.institute_id=:inst_id and btn.status='active'");

		q.setParameter("inst_id", inst_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getInsatitute_company", method = RequestMethod.POST)

	public @ResponseBody List<String> getInsatitute_company(int btn_id, int inst_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select distinct co.id,upper(co.company_name) as company_name  from  TB_COMPANY  co,TB_INSTITUTE ins where bn_id=:bn_id and ins.company_id=co.id and  ins.institute_id=:inst_id and co.status='active'");

		q.setParameter("inst_id", inst_id);
		q.setParameter("bn_id", btn_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getInsatitute_platoon", method = RequestMethod.POST)

	public @ResponseBody List<String> getInsatitute_platoon(int inst_id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select pl.id,upper(pl.platoon_name) as platoon_name from  TB_PLATOON  pl,TB_INSTITUTE ins where ins.company_id=pl.id and  ins.institute_id=:inst_id");

		q.setParameter("inst_id", inst_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	public List<String> getstatusList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='TASTATUS'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// PSG CIVILIAN

	public List<String> getPayLevelList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(pay_level) as pay_level from TB_PSG_MSTR_PAY_LEVEL where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getCivNon_EffectiveList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CIV_NON_EFFECTIVE' order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getCategoryList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(category) as category from TB_CATEGORY where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getClassificationOfTradesList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CLASIFICATION_OF_TRADES'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	/* start update offr data */

	public String update_offr_status(int census_id, String username) {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		// int id = Integer.parseInt(request.getParameter("p_id"));
		// String username = session.getAttribute("username").toString();

		

		try {

			String hqlUpdate = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status,modified_by=:modified_by,modified_date=:modified_date where id=:id";

		

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_ofr_status", 0)
					.setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("id", census_id).executeUpdate();

			tx.commit();

			sessionHQL.close();

			if (app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {
			msg = "Data Not Updated";

			tx.rollback();
		}

		return msg;

	}

	public List<String> getSeconded_To() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select upper(seconded_to) as seconded_to,id from TB_PSG_MSTR_SECONDED_TO where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getForeign_country() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,Upper(visit_purpose) as visit_purpose from TB_PSG_MSTR_PURPOSEOF_VISIT  where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getExaminationTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(

				"select codevalue,label from T_Domain_Value where domainid='ACADEMIC'  order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/GetExaminatonPassed", method = RequestMethod.POST)
	public @ResponseBody List<String> GetExaminatonPassed(String q_type) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select id,upper(examination_passed) as examination_passed  from TB_EXAMINATION_PASSED where status='active' and qualification_type=:qualification_type order by id");
		q.setInteger("qualification_type", Integer.parseInt(q_type));
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/GetExaminatonPassedDegree", method = RequestMethod.POST)
	public @ResponseBody List<String> GetExaminatonPassedDegree(String e_pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select td.id,upper(td.degree) as degree  from TB_DEGREE td,TB_QUALIFICATION tq where tq.status='active' and td.id=tq.degree and tq.examination_passed=:e_pass  order by td.id");
		q.setInteger("e_pass", Integer.parseInt(e_pass));
//		q.setString("qualification", qualification);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/GetDegreeSpecialization", method = RequestMethod.POST)
	public @ResponseBody List<String> GetDegreeSpecialization(String degree) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct ts.id,upper(ts.specialization) as specialization  from TB_SPECIALIZATION ts,TB_LINK_DEGREE td where ts.status='active' and td.status='active' and ts.id=td.specialization and td.degree=:degree order by ts.id");
		q.setInteger("degree", Integer.parseInt(degree));
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getSpecialization", method = RequestMethod.POST)
	public @ResponseBody List<String> getSpecialization(HttpServletRequest request) {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();

		String category = request.getParameter("query");
		String hql = "select distinct upper(specialization) as specialization from TB_QUALIFICATION where status = 'active'";
		if (category != null && !category.equals("")) {
			if (category.equals("Science")) {
				hql += " and quali_stream='Science'";
			} else if (category.equals("Graduate")) {
				hql += " and qualification='Graduate'";
			} else if (category.equals("Postgraduate")) {
				hql += " and qualification='Postgraduate'";
			} else if (category.equals("phD")) {
				hql += " and qualification='phD'";
			} else if (category.equals("Tech")) {
				hql += " and qualification_type='Technical'";
			}

		}
		Query q1 = session1.createQuery(hql);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	//
	// @RequestMapping(value = "/GetExaminatonPassed", method = RequestMethod.POST)
	// public @ResponseBody List<String> GetExaminatonPassed(String q_type) {
	// Session session = HibernateUtil.getSessionFactory().openSession();
	// Transaction tx = session.beginTransaction();
	// Query q = session.createQuery("select from T_Domain_Value where
	// domainid='PROMOTIONAL_EXAM' order by id");
	// @SuppressWarnings("unchecked")
	// List<String> list = (List<String>) q.list();
	// tx.commit();
	// session.close();
	// return list;
	// }

	public List<String> getCourseName() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		String hql = "select distinct id from TB_QUALIFICATION where qualification_type='Technical' and status='active'";
		Query q1 = session1.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;

	}

	public List<String> getDivclass() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(div) as div from TB_DIV_GRADE where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getSubject() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(description) as description from TB_PSG_CENSUS_SUBJECT  order by description");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getExamination() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct code,upper(description) as description from TB_CENSUS_EXAM_PASS  order by description");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getMarital_eventList() {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select codevalue,label from T_Domain_Value where domainid='MARITAL_EVENT' order by codevalue ");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	/*
	 * public List<String> getBPET_result() {
	 * 
	 * Session session1 = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction tx1 = session1.beginTransaction();
	 * 
	 * Query q1 = session1.
	 * createQuery("select codevalue,label from T_Domain_Value where domainid='BPET_RESULT' order by id"
	 * );
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * List<String> list = (List<String>) q1.list();
	 * 
	 * tx1.commit();
	 * 
	 * session1.close();
	 * 
	 * return list;
	 * 
	 * }
	 */

	/* end update offr data */
	/*
	 * public List<String> getExservicemenList() {
	 * 
	 * Session session1 = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction tx1 = session1.beginTransaction();
	 * 
	 * Query q1 = session1.createQuery(
	 * 
	 * "select codevalue,label from T_Domain_Value where domainid='EX_SERVICEMAN'  order by label"
	 * );
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * List<String> list = (List<String>) q1.list();
	 * 
	 * tx1.commit();
	 * 
	 * session1.close();
	 * 
	 * return list;
	 * 
	 * }
	 */

	public List<String> getDesignationList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select distinct id,description \r\n"

				+ "      from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = upper('Appointment') and parent_code in ('4','5','6','7','8','9','10','11') and status_active = 'Active' order by description");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getPersonWithDisabilityList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(

				"select id,upper(disability) as disability from TB_DISABILITY where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getFamily_siblings() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='RELATIONSHIP' order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getForiegnCountryList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select distinct id,upper(country) as country from TB_FORIGEN_COUNTRY  order by country");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getIndianLanguageList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(ind_language) as ind_language from TB_INDIAN_LANGUAGE  where status = 'active' order by ind_language");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getForeignLanguageList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(foreign_language_name) as foreign_language_name from TB_FOREIGN_LANGUAGE where status = 'active' order by foreign_language_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	///////////////// STATUS MASTER////////////
	public List<String> getStatusMasterList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='STATUS_MSTR'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getProfession() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id,upper(profession_name) as profession_name from TB_PROFESSION where status='active' order by profession_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getCauseOfCasualityList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='CAUSE_OF_CASUALITY'  ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getPhysicalDescList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='PHYSICAL_DESC'  ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getForiegnLangugeList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select distinct id,upper(foreign_language_name) as foreign_language_name from TB_FOREIGN_LANGUAGE where status = 'active' order by foreign_language_name");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getPayHeadList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select id,upper(pay_head) as pay_head from TB_PAY_HEAD where status='active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getRe_EmploymentTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='RE_EMP'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getOFFR_Non_EffectiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> getOFFR_Non_EffectiveList(String serving_status) {
		int type_of_officer = 1;
		/*
		 * if(serving_status!=null && !serving_status.equals("") &&
		 * (serving_status.equals("RE-EMPLOYMENT") ||
		 * serving_status.equals("RE-CALL FROM RESERVE")) ) type_of_officer=2;
		 */
		// UPDATE ON 24-06-2022 AS PER MISO REQUIREMENT kajal
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = null;
		if (serving_status != null && !serving_status.equals("") && (serving_status.equals("RE-EMPLOYED"))) {
			type_of_officer = 2;
			q1 = session1.createQuery(
					"select id,upper(causes_name) as causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE where type_of_officer=:type_of_officer and status='active' order by id");
			q1.setParameter("type_of_officer", type_of_officer);

		} else {
			type_of_officer = 1;
			q1 = session1.createQuery(
					"select id,upper(causes_name) as causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE where type_of_officer=:type_of_officer and status='active' order by id");
			q1.setParameter("type_of_officer", type_of_officer);
		}

		if (serving_status.equals("NON EFFECTIVE")) {
			q1 = session1.createQuery(
					"select id,upper(causes_name) as causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE where type_of_officer in (1,2) and status='active' order by id");
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCauseOfCasualityList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCauseOfCasualityList(String idd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid order by id");
		q.setParameter("domainid", idd);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getNameOfOperationList", method = RequestMethod.POST)
	public @ResponseBody List<String> getNameOfOperationList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select id,upper(operation_name) as operation_name from TB_OPERATION where status='active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getBattleDescList", method = RequestMethod.POST)
	public @ResponseBody List<String> getBattleDescList(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid order by id");
		q.setParameter("domainid", id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getAccidentalList", method = RequestMethod.POST)
	public @ResponseBody List<String> getAccidentalList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid='ACCIDENTAL_PHY' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getMedalList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select id,upper(award_cat) as award_cat from TB_PSG_MSTR_AWARD_CAT where status='active'  order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getOFFTypeofRankList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='OFFR_RANK_TYPE'  order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getIssueingAuthList", method = RequestMethod.POST)
	public @ResponseBody List<String> getIssueingAuthList(HttpSession sessionA, String unit_name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<String> list = psg_com.getIssuingAuthorityList(unit_name);
		tx.commit();

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

	public List<String> getChild_TypeList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='CHILD_TYPE'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getShapeValueList(String shape_status) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,values from TB_MED_CAT_VALUES where status='active' and shape_status=:shape_status");
			q1.setString("shape_status", shape_status);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	// CHANGE REQUIRED
	public List<String> getCopeValueList(String cope) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q1 = sessionHQL.createQuery(
					"select concat(value, ' - ', description) as valueDescription,value from TB_PSG_MSTR_COPECODE where cope_code=:cope_code and status = 'active' order by id");
			q1.setString("cope_code", cope);
			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();
			tx.commit();

			return list;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getPostINOutstatusList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='POSTINOUT'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getChild_RelationshipList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CHILD_RELATIONSHIP'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getFdService() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select upper(field_area) as field_area,id from TB_PSG_MSTR_FIELD_AREA where status ='active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getNatureOfCasualityList", method = RequestMethod.POST)
	public @ResponseBody List<String> getNatureOfCasualityList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='NATURE_OF_CASUALITY' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getOprationList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select id,upper(operation_name) as operation_name from TB_OPERATION where status='active'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCourseTypeList", method = RequestMethod.POST)
	public @ResponseBody List<String> getCourseTypeList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid='COURSE_TYPE' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getCourseNameList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select id,upper(course_name) as course_name from TB_COURSE where status='active' order by course_name");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getInstituteTypeList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='INSTITUTE_TYPE'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCourseTypeLists", method = RequestMethod.POST)
	public @ResponseBody List<String> getCourseTypeLists() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid='ARMY_PROP_TYPE' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getExamList", method = RequestMethod.POST)
	public @ResponseBody List<String> getExamList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select id,upper(promo_exam) as promo_exam from TB_MSTR_PROMOTIONAL_EXAM where status='active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getArmyCourseNameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getArmyCourseNameList(String Course, String type, String category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		String qry = " rank_type=:rank_type ";
		if (type != null && type.equals("abbr")) {
			qry += "and upper(course_gp) like :course";
		} else {
			qry += "and upper(course_name) like :course";
		}

		Query q = session.createQuery("select id,course_name,course_gp from TB_COURSE_TYPE where " + qry
				+ "  and status='active' order by id").setMaxResults(10);
		q.setParameter("course", Course.toUpperCase() + "%");
		q.setString("rank_type", category);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public int CompareDate(Date date1, Date date2) {
		if (date1.compareTo(date2) < 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public List<String> getTypeofRankJcoList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select distinct id,description from CUE_TB_PSG_RANK_APP_MASTER  \n"
				+ " where upper(level_in_hierarchy) = 'RANK'  and parent_code in ('1','2','3') and status_active = 'Active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getTypeofTradeList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select distinct id,description from CUE_TB_PSG_RANK_APP_MASTER  \n"
				+ " where upper(level_in_hierarchy) = 'APPOINTMENT'  and parent_code in ('1','2','3') and status_active = 'Active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getArmyType() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select label,codevalue from T_Domain_Value where domainid='ARMY_TYPE' order by codevalue ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getdisciplinetypeentry() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='DISCIPLINE'  order by codevalue");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCard_no_Officer_List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCard_no_Officer_List(String card_no, HttpSession sessionUserId) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery("select distinct id_card_no\r\n" + "FROM TB_CENSUS_IDENTITY_CARD \r\n"
					+ "where status = '1' and upper(id_card_no) like :id_card_no").setMaxResults(10);

			q.setParameter("id_card_no", card_no.toUpperCase() + "%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getCard_no_Jcos_List", method = RequestMethod.POST)
	public @ResponseBody List<String> getCard_no_Jcos_List(String card_no, HttpSession sessionUserId) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery("select distinct id_card_no\r\n" + "FROM TB_CENSUS_IDENTITY_CARD_JCO \r\n"
					+ "where status = '1' and upper(id_card_no) like :id_card_no").setMaxResults(10);

			q.setParameter("id_card_no", card_no.toUpperCase() + "%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getCardNotoPersonalNo_Officer", method = RequestMethod.POST)
	public @ResponseBody List<String> getCardNotoPersonalNo_Officer(String id_card_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery(
					"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,TB_CENSUS_IDENTITY_CARD i \r\n"
							+ "where p.id=i.comm_id and i.id_card_no =:id_card_no")
					.setMaxResults(10);

			q.setParameter("id_card_no", id_card_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getCardNotoPersonalNo_JCOs", method = RequestMethod.POST)
	public @ResponseBody List<String> getCardNotoPersonalNo_JCOs(String id_card_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery(
					"select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p,TB_CENSUS_IDENTITY_CARD_JCO j\r\n"
							+ "where p.id=j.jco_id and j.id_card_no =:id_card_no")
					.setMaxResults(10);

			q.setParameter("id_card_no", id_card_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getPersonalNotoCardNo_Officer", method = RequestMethod.POST)
	public @ResponseBody List<String> getPersonalNotoCardNo(String personnel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery(
					"select distinct i.id_card_no from TB_CENSUS_IDENTITY_CARD i ,TB_TRANS_PROPOSED_COMM_LETTER p\r\n"
							+ "where p.id=i.comm_id and p.personnel_no =:personnel_no")
					.setMaxResults(10);

			q.setParameter("personnel_no", personnel_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getPersonalNotoCardNo_JCOs", method = RequestMethod.POST)
	public @ResponseBody List<String> getPersonalNotoCardNo_JCOs(String personnel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q = sessionHQL.createQuery(
					"select distinct j.id_card_no from TB_CENSUS_IDENTITY_CARD_JCO j,TB_CENSUS_JCO_OR_PARENT p\r\n"
							+ "where p.id=j.jco_id and p.army_no =:army_no")
					.setMaxResults(10);

			q.setParameter("army_no", personnel_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			// sessionHQL.close();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getpersonnel_noListApproved_JCOS", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListApproved_JCOS(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			String roleType = sessionUserId.getAttribute("roleType").toString();
			Query q = null;

			if (roleAccess.equals("Unit")) {

				q = sessionHQL.createQuery(
						"select distinct army_no FROM TB_CENSUS_JCO_OR_PARENT  where status='1' and unit_sus=:roleSusNo \r\n"
								+ "and upper(army_no) like :army_no order by army_no");
				q.setParameter("roleSusNo", roleSusNo);
			} else {
				q = sessionHQL.createQuery("select distinct army_no FROM TB_CENSUS_JCO_OR_PARENT  where status='1' \r\n"
						+ "and upper(army_no) like :army_no order by army_no");
			}

			q.setParameter("army_no", personel_no.toUpperCase() + "%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

	}
	 
	///bisag 231222  v2 (get non_effective per_no)
	
	@RequestMapping(value = "/getApprove_noListApproved_non_eff", method = RequestMethod.POST)
	public @ResponseBody List<String> getApprove_noListApproved_non_eff(String personnel_no, HttpSession sessionUserId,Boolean IsMns) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;
		String qry = null ;
		if(IsMns==true) {
        	qry="and substr(p.personnel_no,1,2) in ('NR','NS') ";
        }else if (IsMns==false) {
        	qry="and substr(p.personnel_no,1,2) Not in ('NR','NS') ";
        }
		if (!roleSusNo.equals("")) {

			q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p Where "
					+ " unit_sus_no=:roleSusNo  and p.status in ('1' ,'4')  and  personnel_no  in  (select p.personnel_no from TB_CENSUS_DETAIL_Parent "
					+ "c where c.comm_id=p.id and c.status ='1' and c.update_ofr_status = '1' ) and upper(personnel_no)  like :personnel_no "+qry+" order by personnel_no ").setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);

		} else {
			q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p Where "
					+ "  p.status  in ('1' ,'4')  and  personnel_no  in  (select p.personnel_no from TB_CENSUS_DETAIL_Parent "
					+ "c where c.comm_id=p.id and c.status ='1' and c.update_ofr_status = '1' ) and upper(personnel_no)  like :personnel_no "+qry+" order by personnel_no ").setMaxResults(10);

		}
		q.setParameter("personnel_no", personnel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}
	@RequestMapping(value = "/getApprove_noListApproved", method = RequestMethod.POST)
	public @ResponseBody List<String> getApprove_noListApproved(String personnel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		if (!roleSusNo.equals("")) {

			q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p Where "
					+ " unit_sus_no=:roleSusNo  and p.status = '1'  and  personnel_no  in  (select p.personnel_no from TB_CENSUS_DETAIL_Parent "
					+ "c where c.comm_id=p.id and c.status ='1' and c.update_ofr_status = '1' ) and upper(personnel_no)  like :personnel_no  order by personnel_no ");

			q.setParameter("roleSusNo", roleSusNo);

		} else {
			q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p Where "
					+ "  p.status = '1'  and  personnel_no  in  (select p.personnel_no from TB_CENSUS_DETAIL_Parent "
					+ "c where c.comm_id=p.id and c.status ='1' and c.update_ofr_status = '1' ) and upper(personnel_no)  like :personnel_no  order by personnel_no ");

		}
		q.setParameter("personnel_no", personnel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	public List<String> getSpecializationList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select id,upper(specialization) as specialization from TB_SPECIALIZATION where status='active'  order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getDegreeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select id,upper(degree) as degree from TB_DEGREE where status='active'  order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getDclredRcvrdList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='DESERTER'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getCauseOfDeserter() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CAUSE_OF_DESRTION'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getARM_TYPE() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='ARM_TYPE'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getCSDCategoryList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='CSD_CARD'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<TB_TRANS_PROPOSED_COMM_LETTER> getcoomisionbyID(BigInteger id) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("from  TB_TRANS_PROPOSED_COMM_LETTER where id=:id").setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<TB_TRANS_PROPOSED_COMM_LETTER> list = (List<TB_TRANS_PROPOSED_COMM_LETTER>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public String update_comm_status(BigInteger comm_id, String username) {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			String hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set update_comm_status=:update_comm_status,modified_by=:modified_by,modified_date=:modified_date where id=:id";

			

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_comm_status", 0)
					.setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setBigInteger("id", comm_id).executeUpdate();

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

	@RequestMapping(value = "/GetAppointmentFromMaster", method = RequestMethod.POST)
	public @ResponseBody List<String> GetAppointmentFromMaster(String appname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct count(id) from TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY where UPPER(appointment)=:appname");
		q.setParameter("appname", appname.toUpperCase());
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getCopeCodeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='COPE'  order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getFiring_event_qe() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(firing_qt) as firing_qt from TB_PSG_MSTR_FIRING_QT where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getBPET_event_qe() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(bpet_qt) as bpet_qt from TB_PSG_MSTR_BPET_QT where status='active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getFiring_grade() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(firing_result) as firing_result from TB_PSG_MSTR_FIRING_RESULT where status='active'  order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getBPET_result() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(bpet_result) as bpet_result from TB_PSG_MSTR_BPET_RESULT where status='active'  order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public String update_miso_role_hdr_status(BigInteger comm_id, int status, String username, String col_name) {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		//Long c= null;
		try {
			
			Query q0 = sessionHQL
					.createQuery("select  count(comm_id) from TB_PSG_MISO_ROLE_HDR_STATUS where comm_id =:comm_id ");

			q0.setParameter("comm_id", comm_id);
			Long c = (Long) q0.uniqueResult();
			
			if (col_name == "field_ser_data") {

				Query q = sessionHQL.createQuery("select comm_id from TB_FIELD_SERVICE_CH where p_id=:p_id");

				q.setParameter("p_id", comm_id.intValue());
				List<String> list = (List<String>) q.list();

				BigInteger new_comm_fd = BigInteger.ZERO;
				int j = 0;
				for (int i = 0; i < list.size(); i++) {

					int count = 0;
					new_comm_fd = new BigInteger(String.valueOf(list.get(i)));
					// count = Integer.parseInt() ;

					update_miso_role_hdr_status(new_comm_fd, status, username, "field_ser_status");
					j++;
					if (j == list.size()) {
						return msg;
					}

				}

			}

			if (c == 0) {

				TB_PSG_MISO_ROLE_HDR_STATUS role_new = new TB_PSG_MISO_ROLE_HDR_STATUS();

				if (col_name.equals("re_emp_status")) {
					role_new.setRe_emp_status(status);
					role_new.setCda_status(1);
					role_new.setMarital_dis_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);
				}
				if (col_name == "cda_status") {
					role_new.setCda_status(status);
					role_new.setArmy_course_status(1);
					/*role_new.setForeign_language_status(1);
					role_new.setLanguage_status(1);*/
					role_new.setRe_emp_status(1);
					role_new.setMarital_dis_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);

				}
				if (col_name == "army_course_status") {
					role_new.setArmy_course_status(status);
					/*role_new.setForeign_language_status(1);
					role_new.setLanguage_status(1);*/
					role_new.setCda_status(1);
					role_new.setRe_emp_status(1);
					role_new.setMarital_dis_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);

				}
				if (col_name == "foreign_language_status") {
					/*role_new.setForeign_language_status(status);
					role_new.setLanguage_status(1);*/
					role_new.setArmy_course_status(1);
					role_new.setCda_status(1);
					role_new.setRe_emp_status(1);
					role_new.setMarital_dis_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);

				}
				if (col_name == "language_status") {
					/*role_new.setLanguage_status(status);
					role_new.setForeign_language_status(1);*/
					role_new.setArmy_course_status(1);
					role_new.setCda_status(1);
					role_new.setRe_emp_status(1);
					role_new.setMarital_dis_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);

				}
				if (col_name == "marital_dis_status") {
					role_new.setMarital_dis_status(status);
					role_new.setArmy_course_status(1);
					/*role_new.setForeign_language_status(1);
					role_new.setLanguage_status(1);*/
					role_new.setRe_emp_status(1);
					role_new.setCda_status(1);
					role_new.setSeniority_status(1);
					role_new.setField_ser_status(1);
				}
				if (col_name == "field_ser_status") {
					role_new.setField_ser_status(status);
					role_new.setArmy_course_status(1);
					/*role_new.setForeign_language_status(1);
					role_new.setLanguage_status(1);*/
					role_new.setRe_emp_status(1);
					role_new.setCda_status(1);
					role_new.setSeniority_status(1);
					role_new.setMarital_dis_status(1);
				}
				if (col_name == "seniority_status") {
					role_new.setSeniority_status(status);
					role_new.setArmy_course_status(1);
					/*role_new.setForeign_language_status(1);
					role_new.setLanguage_status(1);*/
					role_new.setField_ser_status(1);
					role_new.setRe_emp_status(1);
					role_new.setCda_status(1);
					role_new.setMarital_dis_status(1);
				}

				if (comm_id.intValue() > 0) {
					role_new.setComm_id(comm_id);
					role_new.setCreated_by(username);
					role_new.setCreated_date(date);
					sessionHQL.save(role_new);
				}
				sessionHQL.flush();
				sessionHQL.clear();

			} else {
				List<TB_REEMPLOYMENT> re_emp = null;
				if (col_name.equals("re_emp_status")) {
					Query qre = sessionHQL.createQuery("from TB_REEMPLOYMENT where comm_id=:comm_id order by id desc");
					qre.setMaxResults(1).setBigInteger("comm_id", comm_id);
					re_emp = (List<TB_REEMPLOYMENT>) qre.list();

				}
				String hqlUpdate = "update TB_PSG_MISO_ROLE_HDR_STATUS set " + col_name
						+ "=:status,modified_by=:modified_by,modified_date=:modified_date where comm_id=:comm_id";

				Query qre = sessionHQL.createQuery(hqlUpdate).setInteger("status", status)
						.setString("modified_by", username)

						.setBigInteger("comm_id", comm_id);
				if (col_name.equals("re_emp_status") && re_emp != null && re_emp.size() > 0) {
				
					qre.setTimestamp("modified_date", re_emp.get(0).getModified_date());
				} else
					qre.setTimestamp("modified_date", new Date());
				int app = qre.executeUpdate();

				sessionHQL.close();

				if (app > 0) {

					msg = "1";

				}
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			msg = "0";
		}

		return msg;

	}

	@RequestMapping(value = "/getField_areaList", method = RequestMethod.POST)
	public @ResponseBody List<String> getField_areaList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select id,upper(field_area) as field_area from TB_PSG_MSTR_FIELD_AREA where status='active' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public int calculate_age(Date from_date, Date to_date) {
		if (from_date != null && to_date != null) {
			if (to_date.compareTo(from_date) > 0) {
				Instant frominstant = from_date.toInstant();
				ZonedDateTime fromzone = frominstant.atZone(ZoneId.systemDefault());
				LocalDate fromgivenDate = fromzone.toLocalDate();

				Instant toinstant = to_date.toInstant();
				ZonedDateTime tozone = toinstant.atZone(ZoneId.systemDefault());
				LocalDate togivenDate = tozone.toLocalDate();

				Period period = Period.between(fromgivenDate, togivenDate);
				return period.getYears();
			} else
				return 0;

		} else {
			return 0;
		}

	}

	public int calculate_age_Month(Date from_date, Date to_date) {
		if (to_date.compareTo(from_date) > 0) {
			Instant frominstant = from_date.toInstant();
			ZonedDateTime fromzone = frominstant.atZone(ZoneId.systemDefault());
			LocalDate fromgivenDate = fromzone.toLocalDate();

			Instant toinstant = to_date.toInstant();
			ZonedDateTime tozone = toinstant.atZone(ZoneId.systemDefault());
			LocalDate togivenDate = tozone.toLocalDate();

			Period period = Period.between(fromgivenDate, togivenDate);
			int months = period.getYears() * 12;
			months += period.getMonths();
			return months;
		} else
			return -1;
	}

	public String getPersonnelNuSuffix(String data) {
		String suffix = "";
		if (data.length() == 5) {

			int summation = 6 * (Integer.parseInt(data.substring(0, 1))) + 5 * (Integer.parseInt(data.substring(1, 2)))
					+ 4 * (Integer.parseInt(data.substring(2, 3))) + 3 * (Integer.parseInt(data.substring(3, 4)))
					+ 2 * (Integer.parseInt(data.substring(4, 5)));

			summation = summation % 11;

			if (summation == 0) {
				suffix = "A";
			} else if (summation == 1) {
				suffix = "F";
			} else if (summation == 2) {
				suffix = "H";
			} else if (summation == 3) {
				suffix = "K";
			} else if (summation == 4) {
				suffix = "L";
			} else if (summation == 5) {
				suffix = "M";
			} else if (summation == 6) {
				suffix = "N";
			} else if (summation == 7) {
				suffix = "P";
			} else if (summation == 8) {
				suffix = "W";
			} else if (summation == 9) {
				suffix = "X";
			} else if (summation == 10) {
				suffix = "Y";
			}
		}
		return suffix;
	}

	public String StringValidationForName(String name) {
	   return name.trim().replaceAll("\\s+", " ").toUpperCase();
		
	}

	/////////////// casuality-----------------CHANDANI-----

	public List<String> getCasuality1() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select distinct id,upper(casuality1) as casuality1 from TB_PSG_MSTR_CASUALITY1 where status='active' order by casuality1");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCasuality2", method = RequestMethod.POST)
	public @ResponseBody List<String> getCasuality2(int casuality1_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct id,upper(casuality2) as casuality2 from TB_PSG_MSTR_CASUALITY2 where casuality1_id=:casuality1_id and status='active'");
		q.setParameter("casuality1_id", casuality1_id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public String calculate_duration(Date from_date, Date to_date) {

		if (from_date != null && to_date != null) {
			if (to_date.compareTo(from_date) >= 0) {
				// Instant frominstant = from_date.toInstant();
				// ZonedDateTime fromzone = frominstant.atZone(ZoneId.systemDefault());
				// LocalDate fromgivenDate = fromzone.toLocalDate();
				//
				// Instant toinstant = to_date.toInstant();
				// ZonedDateTime tozone = toinstant.atZone(ZoneId.systemDefault());
				// LocalDate togivenDate = tozone.toLocalDate();
				//
				// Period period = Period.between(fromgivenDate, togivenDate);
				LocalDate start_date = from_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int birth_year = start_date.getYear();
				int birth_month = start_date.getMonthValue();
				int birth_date = start_date.getDayOfMonth();
				

				LocalDate end_date = to_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int current_year = end_date.getYear();
				int current_month = end_date.getMonthValue();
				int current_date = end_date.getDayOfMonth();
				
				int february = (birth_year % 4 == 0 && birth_year % 100 != 0) || birth_year % 400 == 0 ? 29 : 28;
				int month[] = { 31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

				// if birth date is greater then current
				// birth_month, then donot count this month
				// and add 30 to the date so as to subtract
				// the date and get the remaining days
				if (birth_date > current_date) {
					current_month = current_month - 1;
					current_date = current_date + month[birth_month - 1];
				}

				// if birth month exceeds current month,
				// then do not count this year and add
				// 12 to the month so that we can subtract
				// and find out the difference
				if (birth_month > current_month) {
					current_year = current_year - 1;
					current_month = current_month + 12;
				}

				// calculate date, month, year
				int calculated_date = current_date - birth_date;
				int calculated_month = current_month - birth_month;
				int calculated_year = current_year - birth_year;

				if (calculated_year == 0 && calculated_month == 0 && calculated_date == 0) {
					return "0 years 0 months 1 days";
				} else {
					String msg = calculated_year + " years " + calculated_month + " months " + calculated_date
							+ " days";
				
					return msg;
				}
			} else
				return "";

		} else {
			return "";
		}

	}

	public List<String> getMissingList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select label,codevalue from T_Domain_Value where domainid='Missing_Description'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getCausesOfCasuality() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select upper(casuality1) as casuality1,id from TB_PSG_MSTR_CASUALITY1  order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getCauses2Url", method = RequestMethod.POST)
	public @ResponseBody List<String> getCauses2Url(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select upper(casuality2) as casuality2,id from TB_PSG_MSTR_CASUALITY2 where casuality1_id=:casuality1_id order by id");
		q.setInteger("casuality1_id", id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getCauses3Url", method = RequestMethod.POST)
	public @ResponseBody List<String> getCauses3Url(int id1, int id2) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select upper(casuality3) as casuality3,id from TB_PSG_MSTR_CASUALITY3 where casuality1_id=:casuality1_id and casuality2_id=:casuality2_id order by id");
		q.setInteger("casuality1_id", id1).setInteger("casuality2_id", id2);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getArmyCourseInstitute(String category) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(course_institute) as course_institute from TB_PSG_MSTR_COURSE_INSTITUTE where category=:category and status='active' order by id ");
			q1.setString("category", category);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getCivilianTrade() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(civilian_trade) as civilian_trade from TB_PSG_MSTR_CIVILIAN_TRADE where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getCauseOfnoneffList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select label,codevalue from T_Domain_Value where domainid='TYPE_OF_NON_EFFECTIVE'  order by codevalue");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getclassification_of_casualitylist", method = RequestMethod.POST)
	public @ResponseBody List<String> getclassification_of_casualitylist() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CLASSIFICATION_OF_CASUALITY' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getDisc_Trialed() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(disc_trialed) as disc_trialed from TB_PSG_MSTR_DISC_TRIALED where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getArmyAct_Sec() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(army_act_sec) as army_act_sec from TB_PSG_MSTR_ARMY_ACT_SEC where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getSub_Clause() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(sub_clause) as sub_clause from TB_PSG_MSTR_SUB_CLAUSE where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getServiceCategoryListJCO() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='SERVICE_CATEGORY' and label != 'CIVILIAN'  order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	public List<String> getCadre() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(cadre) as cadre from TB_PSG_MSTR_CADRE_CIVILIAN where status='active' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getRegNonEff() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(causes_name) as causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN where status='active' and type_of_regular_or_nonregular = 'REGULAR' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getNonRegNonEff() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(causes_name) as causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN where status='active' and type_of_regular_or_nonregular = 'NON-REGULAR' order by id ");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/GetCiv_R_GroupList", method = RequestMethod.POST)
	@ResponseBody
	public List<String> GetCiv_R_GroupList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid='CIV_R_GROUP'  order by codevalue");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	@RequestMapping(value = "/getServiceStatusList", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getServiceStatusList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(

				"select codevalue,label from T_Domain_Value where domainid='SERVICE_STATUS'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getClassificationOfServiceList", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getClassificationOfServiceList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(

				"select codevalue,label from T_Domain_Value where domainid='CLASSIFICATION_OF_SERVICES'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getCivilianTypeList", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getCivilianTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(

				"select codevalue,label from T_Domain_Value where domainid='CIVILIAN_TYPE'  order by label");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	@RequestMapping(value = "/getTargetUnitNameListStnHQ", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitNameListStnHQ(String sus_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no like '04%' and sus_no like :sus_no and status_sus_no='Active'")
				.setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
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

	// ****************************************START CENSUS
	// DETAIL***********************************************//
	@RequestMapping(value = "/getpersonnel_noList", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noList(String personel_no, HttpSession sessionUserId) {     

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;
       String qry="";
       if(roleAccess.equals("DGMS")) {
    	   qry= " and substr(personnel_no,1,2) in ('NR','NS')";
       }
		
		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no not in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2')) and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no not in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2'))  "+qry+"  and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

		}
		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/psg_census_getpersonnel_noList", method = RequestMethod.POST)
	public @ResponseBody List<String> psg_census_getpersonnel_noList(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String qry="";
		if(roleAccess.equals("DGMS")) {
			qry= "and substr(personnel_no,1,2) in ('NR','NS')";
		}
		Query q = null;

		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('0','1','2')) and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('0','1','2'))  "+qry+"  and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

		}
		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/getpersonnel_noListApproved", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListApproved(String personel_no, HttpSession sessionUserId,   
			HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;
		String qry="";
		String rsus = request.getParameter("roleSus");
		if (rsus != null && !rsus.equals("")) {
			roleSusNo = rsus;
		}
		if(roleAccess.equals("DGMS")) {
		   qry= " and substr(personnel_no,1,2) in ('NR','NS')";
		}
       // new logic with dgms role
		if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and p.personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1'))  "+qry+" and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
					.setMaxResults(10);
		}else {
			q = sessionHQL.createQuery(
					"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);
		}
		
		  // old logic when dgms role not created
//		if (roleSusNo != null && !roleSusNo.equals("")) {
//
//			q = sessionHQL.createQuery(
//					"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "
//							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
//					.setMaxResults(10);
//
//			q.setParameter("roleSusNo", roleSusNo);
//
//		} else {
//			
//			q = sessionHQL.createQuery(
//					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and p.personnel_no  in "
//							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
//					.setMaxResults(10);
//		}

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}
	
	
	@RequestMapping(value = "/getpersonnel_noListNonEffApproved", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListNonEffApproved(String personel_no, HttpSession sessionUserId,   
			HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;
		String qry="";
		String rsus = request.getParameter("roleSus");
		if (rsus != null && !rsus.equals("")) {
			roleSusNo = rsus;
		}
		if(roleAccess.equals("DGMS")) {
		   qry= " and substr(personnel_no,1,2) in ('NR','NS')";
		}
       // new logic with dgms role
		if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') "+qry+" and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
					.setMaxResults(10);
		}else {
			q = sessionHQL.createQuery(
					"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);
		}
		
		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}
	

	@RequestMapping(value = "/Search_getpersonnel_no", method = RequestMethod.POST)
	public @ResponseBody List<String> Search_getpersonnel_no(String personel_no, HttpSession sessionUserId,
			HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;
		String roleSusNoMiso = request.getParameter("roleSusNo");
		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3))  and substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);

		} else if (roleSusNoMiso != null && !roleSusNoMiso.equals("")) {
			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3))   and substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNoMiso);
		} else {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no  in "
							+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3))   and substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

		}

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}


	// ****************************************END CENSUS
	// DETAIL***********************************************//

	// ****************************************already methods in miso
	// ***********************************************//

	@RequestMapping(value = "/getpersonnel_noListRe_Emp", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListRe_Emp(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,"
				+ " TB_NON_EFFECTIVE ne "
				+ " where  p.status='4' and  p.id=ne.comm_id and p.id not in ( select comm_id  from TB_REEMPLOYMENT where re_emp_select ='1' and status ='0')"
				+ " and p.id not in (select comm_id from TB_DESERTER ds where status=1 and  ds.dt_recovered is null ) and upper(p.personnel_no)  like :personnel_no and ne.status='1'   order by p.personnel_no ").setMaxResults(10);

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/getpersonnel_noListRe_Call", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListRe_Call(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,\r\n"
				+ "TB_NON_EFFECTIVE ne "
				+ "where  p.status='4'  and  p.id=ne.comm_id and p.id not in ( select CASE WHEN comm_id is null then 0 else comm_id end  from TB_REEMPLOYMENT where re_emp_select ='2' and status in (0,1))\r\n"
				+ "and upper(p.personnel_no)  like :personnel_no and ne.status='1' order by p.personnel_no");

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/getpersonnel_noListEXT", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListEXT(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ,"
					+ " TB_REEMPLOYMENT ne "
					+ " where  p.status='1' and ne.re_emp_select='2' and p.unit_sus_no = :unit_sus_no "
					+ " and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='1' order by p.personnel_no");
			q.setParameter("unit_sus_no", roleSusNo);

		} else {

			q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ,"
					+ " TB_REEMPLOYMENT ne " + " where  p.status='1' and ne.re_emp_select='2' "
					+ " and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='1' order by p.personnel_no");

		}

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/getpersonnel_noListSerEXT", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListSerEXT(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ,"
					+ " TB_REEMPLOYMENT ne " + " where  p.status='1' and ne.re_emp_select='2' "
					+ " and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='0' order by p.personnel_no");

		} else {

			q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ,"
					+ " TB_REEMPLOYMENT ne " + " where  p.status='1' and ne.re_emp_select='2' "
					+ " and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='0' order by p.personnel_no");

		}

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	/*
	 * 
	 * 
	 * nmk
	 * 
	 * 
	 * 
	 *
	 *
	 *
	 * 
	 * 
	 * 
	 */

	/// bisag 150423 v2 (personnel_no will not require in autocomplete which is already use census by amitsir observation )
	@RequestMapping(value = "/getpersonnel_noListFORComm", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noListFORComm(String personel_no, HttpSession sessionUserId) {            

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		  String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
          String qry="";
          if(roleAccess.equals("DGMS")) {
       	   qry= " and substr(personnel_no,1,2) in ('NR','NS')";
          }
		
		// try{
		Query q = sessionHQL.createQuery(
				"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER where status in ('1','5')  "+qry+" "
						+ " and  upper(personnel_no) like :personnel_no order by personnel_no")
				.setMaxResults(10);


		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	@RequestMapping(value = "/GetPersonnelNoDataForComm", method = RequestMethod.POST)
	public @ResponseBody List<String> GetPersonnelNoDataForComm(String personnel_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select c.id,c.cadet_no,c.batch_no,c.gender,g.gender_name,c.date_of_birth ,c.name,cu.description,"
							+ "ct.course_name,c.parent_arm,c.regiment,cu.code as rankcode,c.date_of_commission,c.personnel_no,c.type_of_comm_granted,"
							+ " c.unit_sus_no,c.date_of_tos,c.course  "
							+ " FROM TB_TRANS_PROPOSED_COMM_LETTER c ,TB_GENDER g ,"
							+ " TB_COURSE ct,CUE_TB_PSG_RANK_APP_MASTER cu where upper(c.personnel_no) =:personnel_no and c.gender=g.id and "
							+ " c.course=ct.id and cu.id=c.rank order by c.personnel_no");
			q1.setParameter("personnel_no", personnel_no.toUpperCase());
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getpersonnel_noList_BA_PY_CA", method = RequestMethod.POST)
	public @ResponseBody List<String> getpersonnel_noList_BA_PY_CA(String personel_no, HttpSession sessionUserId,
			HttpServletRequest request) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{

		Query q = null;

		q = sessionHQL.createQuery(
				"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,TB_CENSUS_BATT_PHY_CASUALITY n where p.id=n.comm_id and n.status=1 and (p.status='1' or p.status='5') and p.personnel_no  in (select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");

		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

	
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}

	// ****************************************already methods in miso
	// ***********************************************//

	/************ get Formation from sus no for Reports ***************/
	public List<String> getformationfromSus_NOList(String sus_no) {
		Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionComnd.beginTransaction();
		Query q = sessionComnd.createQuery("select form_code_control from Miso_Orbat_Unt_Dtl "
				+ "where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionComnd.close();
		return list;
	}

	/************ get personnel no from commissioning Id ***************/
	@RequestMapping(value = "/getPersonelNoFromComm", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> getPersonelNoFromComm(BigInteger comm_id, HttpSession sessionH) {   
		String roleAccess = sessionH.getAttribute("roleAccess").toString();
		String roleSusNo = sessionH.getAttribute("roleSusNo").toString();
		Map<String, String> data = new HashMap<>();
		ArrayList<List<String>> list = psg_com.getPersonnelNofromcomm(comm_id);
		boolean flag = false;
		if (list.size() > 0) {
			if (roleAccess.toUpperCase().equals("UNIT")) {
				if (list.get(0).get(3).toString().equals(roleSusNo)) {
					flag = true;
				}
			} else if (roleAccess.toUpperCase().equals("MISO") || roleAccess.equals("DGMS")) {
				flag = true;
			}
		}

		if (flag) {
			data.put("personnel_no", list.get(0).get(0).toString());
			data.put("census_id", list.get(0).get(1).toString());
			data.put("marital_status", list.get(0).get(2).toString());
		} else {
			data.put("error", "Data Not Available");
		}
		return data;
	}



	/************ get Army no from Jco Id ***************/
	@RequestMapping(value = "/getArmyNoFromJco", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> getArmyNoFromJco(String jco_id, HttpSession sessionH) {
		String roleAccess = sessionH.getAttribute("roleAccess").toString();
		String roleSusNo = sessionH.getAttribute("roleSusNo").toString();
		Map<String, String> data = new HashMap<>();
		ArrayList<List<String>> list = psg_com.getArmyNofromJco(Integer.parseInt(jco_id));
		boolean flag = false;
		if (list.size() > 0) {
			if (roleAccess.toUpperCase().equals("UNIT")) {
				if (list.get(0).get(2).toString().equals(roleSusNo)) {
					flag = true;
				}
			} else if (roleAccess.toUpperCase().equals("MISO")) {
				flag = true;
			}
		}

		if (flag) {
			data.put("army_no", list.get(0).get(0).toString());
			data.put("marital_status", list.get(0).get(1).toString());
		} else {
			data.put("error", "Data Not Available");
		}
		return data;
	}

	public List<String> getExservicemenList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,ex_servicemen from TB_EX_SERVICEMEN Where status='active' order by ex_servicemen");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// Agency Autocomplete

	public List<String> getAgencyList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,agency_name from TB_PSG_AGENCY_MASTER order by agency_name");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	// public List<String> getBenifitList() {
	//
	// Session session1 = HibernateUtil.getSessionFactory().openSession();
	// Transaction tx1 = session1.beginTransaction();
	// Query q1 = session1.createQuery("select id,benefits_name from
	// TB_PSG_BENEFITS_MASTER order by benefits_name");
	// @SuppressWarnings("unchecked")
	// List<String> list = (List<String>) q1.list();
	// tx1.commit();
	// session1.close();
	// return list;
	// }

	@RequestMapping(value = "/getAgencyList", method = RequestMethod.POST) // For Encryption with & without Disease Name
																			// Fetch

	public @ResponseBody List<String> getAgencyList(String a, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = null;

		if (a.equalsIgnoreCase("")) {

			q = session.createQuery(
					"select distinct id,Upper(agency_name) from TB_PSG_AGENCY_MASTER where status = 'active'  order  by agency_name");

		} else {

			q = session.createQuery("select distinct Upper(agency_name) from TB_PSG_AGENCY_MASTER where "

					+ "(lower(agency_name) like :a or Upper(agency_name) like :a) and status = 'active' order by agency_name");

			q.setParameter("a", '%' + a.toLowerCase() + '%');

			q.setParameter("a", '%' + a.toUpperCase() + '%');

			q.setMaxResults(10);

		}

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/geBenifitList", method = RequestMethod.POST)

	public @ResponseBody List<String> geBenifitList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		int agency_id = Integer.parseInt(request.getParameter("agency_id"));
		Query q = session.createQuery(
				"select id,upper(benefits_name) as benefits_name from TB_PSG_BENEFITS_MASTER where agency_id=:agency_id ");

		q.setParameter("agency_id", agency_id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	@RequestMapping(value = "/getTargetSUSNoList_postin", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList_postin(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no='Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			} else {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and status_sus_no='Active'  order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("HeadQuarter")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and status_sus_no='Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and status_sus_no='Active'  and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and status_sus_no='Active'  and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no   and status_sus_no='Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and status_sus_no='Active'  and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit")) {
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no   and status_sus_no='Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no='Active' and arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("DG")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no   and status_sus_no='Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}

		

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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

	@RequestMapping(value = "/getTargetUnitsNameActiveList_postin", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveList_postin(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		///bisag 181022 v2(remove status sus No active for coverted sus no)
		// sus_no = roleSusNo;
		q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where  upper(unit_name) like :unit_name    and status_sus_no='Active' order by unit_name")
				.setMaxResults(10);
		// q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		q.setParameter("unit_name","%" + unit_name.toUpperCase() + "%");

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

	/*
	 * @RequestMapping(value = "/getTargetSUSNoList_HQ1", method =
	 * RequestMethod.POST) public @ResponseBody List<String>
	 * getTargetSUSNoList_HQ1(HttpSession sessionA, String sus_no) {
	 * 
	 * Session session = HibernateUtil.getSessionFactory().openSession();
	 * Transaction tx = session.beginTransaction(); Query q = null;
	 * 
	 * q = session.
	 * createQuery("select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)"
	 * ).setMaxResults(10); q.setParameter("sus_no", sus_no.toUpperCase() + "%");
	 * 
	 * @SuppressWarnings("unchecked") List<String> list = (List<String>) q.list();
	 * tx.commit();
	 * 
	 * String enckey = hex_asciiDao.getAlphaNumericString(); Cipher c = null; try {
	 * c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey); } catch
	 * (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException |
	 * InvalidKeySpecException | InvalidAlgorithmParameterException |
	 * IllegalBlockSizeException | BadPaddingException e) { e.printStackTrace(); }
	 * List<String> FinalList = new ArrayList<String>(); for (int i = 0; i <
	 * list.size(); i++) { byte[] encCode = null; try { encCode =
	 * c.doFinal(list.get(i).getBytes()); } catch (IllegalBlockSizeException |
	 * BadPaddingException e) { e.printStackTrace(); } String
	 * base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
	 * FinalList.add(base64EncodedEncryptedCode); } FinalList.add(enckey +
	 * "4bsjyg=="); return FinalList; }
	 */

	@RequestMapping(value = "/HQ_getTargetSUSNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> HQ_getTargetSUSNoList(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO")) {

			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and sus_no like '0444%' and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");

		}

		if (roleAccess.equals("Unit")) {
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and sus_no like '0444%' and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}

		

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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

	// kajal
	@RequestMapping(value = "/getallUnitNameListStnHQ", method = RequestMethod.POST)
	public @ResponseBody List<String> getallUnitNameListStnHQ(String unit_name, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where unit_name like :unit_name and sus_no like '0444%' and status_sus_no='Active'");
		q.setParameter("unit_name", unit_name.toUpperCase() + "%");
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

	@RequestMapping(value = "/PSG_CADET_getTargetUnitsNameActiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> PSG_CADET_getTargetUnitsNameActiveList(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO")) {

			q = session.createQuery(
					"select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name  and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("unit_name", "%"+unit_name.toUpperCase() + "%");

		}

		if (roleAccess.equals("Unit")) {
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name  and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("unit_name", "%"+unit_name.toUpperCase() + "%");
		}

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
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy, String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;

		if (level_in_hierarchy.equals("Command")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if (level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");
		}
		if (level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");
		}
		if (level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");
		}

		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// -------------------------------Relation
	// JCO-------------------------------------
	public List<String> getRelationList_JCO() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select id,upper(relation_name) as relation_name from TB_RELATION  where status='active' and relation_name !='OTHERS'   order by relation_name");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}

	// civilian
	@RequestMapping(value = "/getemployee_noListApproved", method = RequestMethod.POST)

	public @ResponseBody List<String> getemployee_noListApproved(String personnel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		// try{

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String roleType = sessionUserId.getAttribute("roleType").toString();

		Query q = null;

		q = sessionHQL.createQuery(
				"select distinct employee_no from TB_CIVILIAN_DETAILS  where Upper(employee_no) like :employee_no and civilian_status='R' ")
				.setMaxResults(10);
		// 26-01-1994
		q.setParameter("employee_no", personnel_no.toUpperCase() + '%');

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

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

				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());

			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();

			}

			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));

			FinalList.add(base64EncodedEncryptedCode);

		}

		FinalList.add(enckey + "4bsjyg==");

		return FinalList;

	}

	@RequestMapping(value = "/getemployee_no_non_regularListApproved", method = RequestMethod.POST)

	public @ResponseBody List<String> getemployee_no_non_regularListApproved(String personnel_no,
			HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		// try{

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String roleType = sessionUserId.getAttribute("roleType").toString();

		Query q = null;

		q = sessionHQL.createQuery(
				"select distinct employee_no from TB_CIVILIAN_DETAILS  where Upper(employee_no) like :employee_no and civilian_status='NR' ")
				.setMaxResults(10);
		// 26-01-1994
		q.setParameter("employee_no", personnel_no.toUpperCase() + '%');

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

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

				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());

			} catch (IllegalBlockSizeException | BadPaddingException e) {

				e.printStackTrace();

			}

			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));

			FinalList.add(base64EncodedEncryptedCode);

		}

		FinalList.add(enckey + "4bsjyg==");

		return FinalList;

	}
////////////////////////////////////////////////////////////sus_no relegate
	@RequestMapping(value = "/Relegate_sus_no" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Relegate_sus_no(@ModelAttribute("sus_no") BigInteger sus_no,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "update  Miso_Orbat_Unt_Dtl set sus_no='sus_no' " + 
					" where sus_no=OLD.sus_no";
			int app = sessionHQL.createQuery(hqlUpdate).setBigInteger("sus_no", sus_no).executeUpdate();
			tx.commit();
			sessionHQL.close();
			
			

			if (app > 0  ) {
				liststr.add("Relegate Successfully.");
			} else {
				liststr.add("Relegate UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Commissioning_LetterUrl");
	}

@RequestMapping(value = "/UnitsNameList_active_or_inactive", method = RequestMethod.POST)
	public @ResponseBody List<String> UnitsNameList_active_or_inactive(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		///bisag 181022 v2(it use only for postinout in psg module)
		// sus_no = roleSusNo;
		q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where  upper(unit_name) like :unit_name  and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by unit_name")
				.setMaxResults(10);
		// q.setParameter("sus_no", sus_no.toUpperCase() + "%");
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


	@RequestMapping(value = "/getSUSNoList_Active_or_Inactive", method = RequestMethod.POST)
	public @ResponseBody List<String> getSUSNoList_Active_or_Inactive(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(it use only for postinout in psg module)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			} else {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("HeadQuarter")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit")) {
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE' )  order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) and arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("DG")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and upper(status_sus_no) in ('INACTIVE','ACTIVE' ) and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}

		

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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

	public List<String> getTypeofRankList_jCO() { 
		Session session1 = HibernateUtil.getSessionFactory().openSession(); 
		Transaction tx1 = session1.beginTransaction();  	
		Query q1 = session1.createQuery("select distinct id,description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' " +  				" and code in ('TR081','NWRK1','TR374','TR364','TR319','TR317','TR061','TR321') and status_active = 'Active' order by code");  		
		@SuppressWarnings("unchecked")  		
		List<String> list = (List<String>) q1.list(); 		 		
		tx1.commit();  		
		session1.close();  		
		return list;  
		}

	public List<String> getCivHigh_Qualification() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,upper(examination_passed) as examination_passed from TB_EXAMINATION_PASSED where status='active' order by id");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	
	

@RequestMapping(value = "/getTargetUnitsName_inactive_only", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsName_inactive_only(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		///bisag 181022 v2(remove status sus No active for coverted sus no)
		// sus_no = roleSusNo;
		q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where  upper(unit_name) like :unit_name    and UPPER(status_sus_no)=UPPER('Inactive')  order by unit_name")
				.setMaxResults(10);
		// q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		q.setParameter("unit_name", unit_name.toUpperCase() + "%");

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
	
	
		@RequestMapping(value = "/getTargetSUSNoList_inactive_only", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList_inactive_only(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();  
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and UPPER(status_sus_no)=UPPER('Inactive')  and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			} else {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and UPPER(status_sus_no)=UPPER('Inactive') order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("HeadQuarter")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and UPPER(status_sus_no)=UPPER('Inactive') order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and UPPER(status_sus_no)=UPPER('Inactive')  order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and UPPER(status_sus_no)=UPPER('Inactive') order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and UPPER(status_sus_no)=UPPER('Inactive')  order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and  upper(form_code_control) like :roleFormationNo and UPPER(status_sus_no)=UPPER('Inactive')  order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit")) {
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and UPPER(status_sus_no)=UPPER('Inactive')  order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery(
					"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and UPPER(status_sus_no)=UPPER('Inactive')  and arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus=:roleSusNo )  order by SUBSTRING(sus_no,1,7)")
					.setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("DG")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery(
						"select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no  and UPPER(status_sus_no)=UPPER('Inactive')  and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ")
						.setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}

		

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

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
		
		
		@RequestMapping(value = "/getallorbatunitlist", method = RequestMethod.POST)
		public @ResponseBody List<String> getallorbatunitlist(HttpSession sessionA, String unit_name) {

			unit_name = unit_name.replace("&#40;", "(");
			unit_name = unit_name.replace("&#41;", ")");

			String sus_no;
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String role = sessionA.getAttribute("role").toString();

			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			///bisag 181022 v2(it use only for postinout in psg module)
			// sus_no = roleSusNo;
			q = session.createQuery(
					"select unit_name from Miso_Orbat_Unt_Dtl where  upper(unit_name) like :unit_name  order by unit_name")
					.setMaxResults(10);
			// q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");

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
		
		
		@RequestMapping(value = "/getallorbatunit_sus_list", method = RequestMethod.POST)
 		public @ResponseBody List<String> getallorbatunit_sus_list(String unit_name, HttpSession sessionUserId) {
 			Session session = HibernateUtil.getSessionFactory().openSession();
 			Transaction tx = session.beginTransaction();
 			
 			Query q = session.createQuery(
 					"select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name ");
 			q.setParameter("unit_name", unit_name);
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
		
	
		
		@RequestMapping(value = "/getpersonnel_noListApprovedpostin", method = RequestMethod.POST)
 	    public @ResponseBody List<String> getpersonnel_noListApprovedpostin(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

 	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
 	            Transaction tx = sessionHQL.beginTransaction();
 	    //try{
 	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
 	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
 	            String roleType = sessionUserId.getAttribute("roleType").toString();
 	            Query q= null;
 	            String rsus=request.getParameter("roleSus");
 	            if( rsus!=null && !rsus.equals("")){
 	            	roleSusNo=rsus;
 	            }
 	            
 	            if(roleSusNo!=null && !roleSusNo.equals("")){
 	                    
 	                     q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and  substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS') and (p.status='1' or p.status='5')" +
 	                    "and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);
 	                     
 	                     q.setParameter("roleSusNo", roleSusNo);  

 	            }
 	            else
 	            {
 	                     
 	                     q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and  substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS')  "+
 	                    " and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);
 	                                            
 	            }
 	            
 	            q.setParameter("personnel_no", personel_no.toUpperCase()+"%");
 	            System.err.println(q);
 	            
 	            @SuppressWarnings("unchecked")        
 	            List<String> list = (List<String>) q.list();
 	            tx.commit();
 	            

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
 	                                    encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
 	                            } catch (IllegalBlockSizeException | BadPaddingException e) {
 	                                    e.printStackTrace();
 	                            }
 	                            String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
 	                            FinalList.add(base64EncodedEncryptedCode);
 	                    }
 	                    FinalList.add(enckey + "4bsjyg==");
 	                    return FinalList;
 	                    
 	    }
		
		@RequestMapping(value = "/getparent_arm", method = RequestMethod.POST)
		public @ResponseBody List<String> getparent_arm(String type) {

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Query q1 = null;
			Transaction tx1 = session1.beginTransaction();
		
			if(type.equals("mns")) {
				q1 = session1.createQuery(
						"SELECT SUBSTR(a.arm_code,1,2)|| '00' AS arm, a.arm_desc FROM Tb_Miso_Orabt_Arm_Code a WHERE a.arm_code in ('4800') order by a.arm_desc");
			}else {
				q1 = session1.createQuery(
						"SELECT SUBSTR(a.arm_code,1,2)|| '00' AS arm, a.arm_desc FROM Tb_Miso_Orabt_Arm_Code a WHERE a.arm_code in ('0100','0120','0200','0300','0400','0500'," + 
						" '0600', '0800','0900','1000','1100','1200','1300','1400','1500','1600','1700','1800','1900'," + 
						" '2000','2100','2200','2300','2400','3300','3400','3700','4600','4700') order by a.arm_desc");
				}
		

			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();

			tx1.commit();

			session1.close();
			System.out.println("get parent arm list  " + list);
			return list;

		}
		
		//------------------------------------------------ pranay 31.05
		
		@RequestMapping(value = "/commission_institute", method = RequestMethod.POST)
		public @ResponseBody List<String> commission_institute(String accesstype,String type) {

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Query q1 = null;
			Transaction tx1 = session1.beginTransaction();

			if(accesstype.equals("mns")) {
				 q1 = session1.createQuery(
							"select distinct id,upper(institute_name) as institute_name from TB_INSTITUTE_LIST where institute_type=:institute_type and status='active' and id in (96,98,99,100,101,102,103) order by institute_name ");
					
			}else {
				 q1 = session1.createQuery(
							"select distinct id,upper(institute_name) as institute_name from TB_INSTITUTE_LIST where institute_type=:institute_type and status='active' and id not in (96,98,99,100,101,102,103) order by institute_name ");
					
				}

			q1.setString("institute_type", type);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();

			tx1.commit();

			session1.close();

			return list;

		}
		
		//RAJ 08.10.2024
				@RequestMapping(value = "/getrank_list", method = RequestMethod.POST)
				public @ResponseBody List<String> getrank_list(String type) {

					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Query q1 = null;
					Transaction tx1 = session1.beginTransaction();
				
					if(type.equals("mns")) {
						q1 = session1.createQuery(
								"select distinct id,description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' " + 
										"and parent_code ='0' and code in ('R001','R002','R003','R004','R005','R006','R007','R008','26243','27180','26205') and status_active = 'Active' order by code");
					}else {
						q1 = session1.createQuery(
								"select distinct id,description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' " + 
								"and parent_code ='0' and code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') and status_active = 'Active' order by code");

						}
				

					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q1.list();

					tx1.commit();

					session1.close();

					return list;

				}
				
				@RequestMapping(value = "/getpersonnel_noListApprovedForMns", method = RequestMethod.POST)
				public @ResponseBody List<String> getpersonnel_noListApprovedForMns(String personel_no, HttpSession sessionUserId,   
						HttpServletRequest request) {

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					String roleType = sessionUserId.getAttribute("roleType").toString();
					Query q = null;
					String qry="";
					String rsus = request.getParameter("roleSus");
					if (rsus != null && !rsus.equals("")) {
						roleSusNo = rsus;
					}

				   // new logic with dgms role
					if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and p.personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1'))  and substr(personnel_no,1,2) in ('NR','NS') and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
								.setMaxResults(10);
					}else {
						q = sessionHQL.createQuery(
								"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and substr(personnel_no,1,2) in ('NR','NS') and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);
					}

					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

					
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				@RequestMapping(value = "/Search_getpersonnel_no_Mns", method = RequestMethod.POST)
				public @ResponseBody List<String> Search_getpersonnel_no_Mns(String personel_no, HttpSession sessionUserId,
						HttpServletRequest request) {

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					String roleType = sessionUserId.getAttribute("roleType").toString();
					Query q = null;
					String roleSusNoMiso = request.getParameter("roleSusNo");
					if (roleAccess.equals("Unit")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3)) and substring(p.personnel_no, 1, 2) IN ('NR', 'NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);

					} else if (roleSusNoMiso != null && !roleSusNoMiso.equals("")) {
						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3))and substring(p.personnel_no, 1, 2) IN ('NR', 'NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNoMiso);
					} else {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3)) and substring(p.personnel_no, 1, 2) IN ('NR', 'NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					}

					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}

				@RequestMapping(value = "/getpersonnel_noListApprovedpostinMns", method = RequestMethod.POST)
				 public @ResponseBody List<String> getpersonnel_noListApprovedpostinMns(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

				         Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				         Transaction tx = sessionHQL.beginTransaction();
				 //try{
				         String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				         String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
				         String roleType = sessionUserId.getAttribute("roleType").toString();
				         Query q= null;
				         String rsus=request.getParameter("roleSus");
				         if( rsus!=null && !rsus.equals("")){
				         	roleSusNo=rsus;
				         }
				         
				         if(roleSusNo!=null && !roleSusNo.equals("")){
				                 
				                  q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and  substring(p.personnel_no, 1, 2) IN ('NR', 'NS') and (p.status='1' or p.status='5')" +
				                 "and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);
				                  
				                  q.setParameter("roleSusNo", roleSusNo);  

				         }
				         else
				         {
				                  
				                  q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and  substring(p.personnel_no, 1, 2) IN ('NR', 'NS')  "+
				                 " and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);
				                                         
				         }
				         
				         q.setParameter("personnel_no", personel_no.toUpperCase()+"%");
				         System.err.println(q);
				         
				         @SuppressWarnings("unchecked")        
				         List<String> list = (List<String>) q.list();
				         tx.commit();
				         

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
				                                 encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				                         } catch (IllegalBlockSizeException | BadPaddingException e) {
				                                 e.printStackTrace();
				                         }
				                         String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				                         FinalList.add(base64EncodedEncryptedCode);
				                 }
				                 FinalList.add(enckey + "4bsjyg==");
				                 return FinalList;
				                 
				 }
				
				@RequestMapping(value = "/getpersonnel_noListApprovedWithoutMns", method = RequestMethod.POST)
				public @ResponseBody List<String> getpersonnel_noListApprovedWithoutMns(String personel_no, HttpSession sessionUserId,   
						HttpServletRequest request) {

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					String roleType = sessionUserId.getAttribute("roleType").toString();
					Query q = null;
					String qry="";
					String rsus = request.getParameter("roleSus");
					if (rsus != null && !rsus.equals("")) {
						roleSusNo = rsus;
					}

				   // new logic with dgms role
					if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and p.personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1'))  and substr(personnel_no,1,2) Not in ('NR','NS') and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
								.setMaxResults(10);
					}else {
						q = sessionHQL.createQuery(
								"select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and substr(personnel_no,1,2) Not in ('NR','NS') and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);
					}

					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

					
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				
				@RequestMapping(value = "/getpersonnel_noListFORCommMns", method = RequestMethod.POST)
				public @ResponseBody List<String> getpersonnel_noListFORCommMns(String personel_no, HttpSession sessionUserId) {            

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					  String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			          String qry="";
			          if(roleAccess.equals("DGMS")) {
			       	   qry= " and substr(personnel_no,1,2) in ('NR','NS')";
			          }
					
					// try{
					Query q = sessionHQL.createQuery(
							"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER where status in ('1','5')  "+qry+" "
									+ " and substring(personnel_no, 1, 2) IN ('NR', 'NS') and upper(personnel_no) like :personnel_no order by personnel_no")
							.setMaxResults(10);


					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");

					
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				
				@RequestMapping(value = "/getpersonnel_noMns", method = RequestMethod.POST)

				public @ResponseBody List<String> getpersonnel_noMns(String personel_no, HttpSession sessionUserId) {  
					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					 Query	q = sessionHQL.createQuery(
							"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where Upper(personnel_no) like :personel_no and substr(personnel_no,1,2) in ('NR','NS') ")
							.setMaxResults(10);			
					q.setParameter("personel_no", personel_no.toUpperCase() + '%');
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();
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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;
				}
				
				@RequestMapping(value = "/getpersonnel_noListMns", method = RequestMethod.POST)
				public @ResponseBody List<String> getpersonnel_noListMns(String personel_no, HttpSession sessionUserId) {     

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();				
					Query q = null;		
			   
					
					if (roleAccess.equals("Unit")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2')) and upper(personnel_no)  like :personnel_no and substr(personnel_no,1,2) in ('NR','NS')  order by personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);
					}
					if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2'))  and substr(personnel_no,1,2) in ('NR','NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					}
					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				@RequestMapping(value = "/psg_census_getpersonnel_noMnsList", method = RequestMethod.POST)
				public @ResponseBody List<String> psg_census_getpersonnel_noMnsList(String personel_no, HttpSession sessionUserId) {

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
								Query q = null;

					if (roleAccess.equals("Unit")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('0','1','2')) and substr(personnel_no,1,2) in ('NR','NS') and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);
					}
					if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no  in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('0','1','2'))  and substr(personnel_no,1,2) in ('NR','NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					}
					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				@RequestMapping(value = "/getPersonnelNumbersExcludingMns", method = RequestMethod.POST)
				public @ResponseBody List<String> getPersonnelNumbersExcludingMns(String personel_no, HttpSession sessionUserId) {     

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();				
					Query q = null;		
			   
					
					if (roleAccess.equals("Unit")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2')) and upper(personnel_no)  like :personnel_no and substr(personnel_no,1,2) Not in ('NR','NS')  order by personnel_no")
								.setMaxResults(10);

						q.setParameter("roleSusNo", roleSusNo);
					}
					if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2'))  and substr(personnel_no,1,2) Not in ('NR','NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					}
					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
		// mns appointments pranay 13.07
				
				public List<String> getTypeofAppointementListMns() {

					Session session1 = HibernateUtil.getSessionFactory().openSession();

					Transaction tx1 = session1.beginTransaction();

					Query q1 = session1.createQuery(
							"select distinct id,upper(description) as description from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' and status_active = 'Active' order by description ");

					@SuppressWarnings("unchecked")

					List<String> list = (List<String>) q1.list();

					tx1.commit();

					session1.close();

					return list;

				}
				
				
				@RequestMapping(value = "/checkPersonnelNo", method = RequestMethod.POST)
				public @ResponseBody String checkPersonnelNo(String personnelNo, HttpSession sessionUserId) {
				    Session session = HibernateUtil.getSessionFactory().openSession();
				    Transaction tx = session.beginTransaction();
				    String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				    try {			
				        Query commissionQuery = session.createQuery(
				            "SELECT COUNT(id) FROM TB_TRANS_PROPOSED_COMM_LETTER " +
				            "WHERE personnel_no like :personnelNo");
				        commissionQuery.setParameter("personnelNo", personnelNo.toUpperCase()+"%");
				        Long commissionCount = (Long) commissionQuery.uniqueResult();
				        
				      
				        Query unitQuery = session.createQuery(
				            "SELECT COUNT(id) FROM TB_TRANS_PROPOSED_COMM_LETTER " +
				            "WHERE personnel_no like :personnelNo AND unit_sus_no = :unitNo");
				        unitQuery.setParameter("personnelNo", personnelNo.toUpperCase()+"%");
				        unitQuery.setParameter("unitNo", roleSusNo);
				        Long unitCount = (Long) unitQuery.uniqueResult();				        
				      
				        String response;
				        if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {
				        	unitCount=(long) 1;
				        }
				        if (commissionCount == 0) {
				            response = "Commissioning dtls not updt in Appl. Contact MISO/PSG along with Commissining letter of the offr to updt the same.";
				        } else if (unitCount == 0) { 
				            response = "Pl Post In the offr using 'POSTING IN/OUT' screen.";
				        } else {
				            response = "True";
				        }
				        
				        tx.commit();
				        return response;
				        
				    } catch (Exception e) {
				        if (tx != null) tx.rollback();
				        throw e;
				    } finally {
				        session.close();
				    }
				}
				
				

				public List<String> getTypeOfCommissionListmns() {

					Session session1 = HibernateUtil.getSessionFactory().openSession();

					Transaction tx1 = session1.beginTransaction();

					Query q1 = session1.createQuery(
							"select id,upper(comn_name)  as comn_name from TB_COMMISSION_TYPE where id in ('3','81') and status = 'active' order by comn_name");

					@SuppressWarnings("unchecked")

					List<String> list = (List<String>) q1.list();

					tx1.commit();

					session1.close();

					return list;

				}
				
				
				@RequestMapping(value = "/getPersonnelNoOfficerList", method = RequestMethod.POST)
				public @ResponseBody List<String> getPersonnelNoOfficerList(String personel_no, HttpSession sessionUserId) {     

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
						
					Query q = null;			

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2'))  and substr(personnel_no,1,2) Not in ('NR','NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					
					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				
				
				@RequestMapping(value = "/getPersonnelNoMnsOfficerList", method = RequestMethod.POST)
				public @ResponseBody List<String> getPersonnelNoMnsOfficerList(String personel_no, HttpSession sessionUserId) {     

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					// try{
						
					Query q = null;			

						q = sessionHQL.createQuery(
								"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no not in "
										+ "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status in ('1','2'))  and substr(personnel_no,1,2) in ('NR','NS')  and upper(personnel_no)  like :personnel_no  order by personnel_no")
								.setMaxResults(10);

					
					q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();

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
							encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
						FinalList.add(base64EncodedEncryptedCode);
					}
					FinalList.add(enckey + "4bsjyg==");
					return FinalList;

				}
				
				@RequestMapping(value = "/getCommissionPersonnelNo", method = RequestMethod.POST)
		 	    public @ResponseBody List<String> getCommissionPersonnelNo(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

		 	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	            Transaction tx = sessionHQL.beginTransaction();
		 	    //try{
		 	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		 	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		 	            String roleType = sessionUserId.getAttribute("roleType").toString();
		 	            Query q= null;
		 	            String rsus=request.getParameter("roleSus");
		 	            if( rsus!=null && !rsus.equals("")){
		 	            	roleSusNo=rsus;
		 	            }
		 	            
		 	            if(roleSusNo!=null && !roleSusNo.equals("")){
		 	                    
		 	                     q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5')  and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);;
		 	                     
		 	                     q.setParameter("roleSusNo", roleSusNo);  

		 	            }
		 	            else
		 	            {
		 	                     
		 	                     q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5')  and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no").setMaxResults(10);;
		 	                                            
		 	            }
		 	            
		 	            q.setParameter("personnel_no", personel_no.toUpperCase()+"%");  
		 	            
		 	            @SuppressWarnings("unchecked")        
		 	            List<String> list = (List<String>) q.list();
		 	            tx.commit();
		 	            

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
		 	                                    encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
		 	                            } catch (IllegalBlockSizeException | BadPaddingException e) {
		 	                                    e.printStackTrace();
		 	                            }
		 	                            String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		 	                            FinalList.add(base64EncodedEncryptedCode);
		 	                    }
		 	                    FinalList.add(enckey + "4bsjyg==");
		 	                    return FinalList;
		 	                    
		 	    }
		 		public List<String> getCourseNameListmns() {
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx1 = session1.beginTransaction();
					Query q1 = session1.createQuery(
							"select id,upper(course_name) as course_name from TB_COURSE where status='active' and id in ('1') order by course_name");
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q1.list();
					tx1.commit();
					session1.close();
					return list;
				}
		 		@RequestMapping(value = "/getTargetSusNoFromUnitNameActiveInactiveInvalid", method = RequestMethod.POST)
		 		public @ResponseBody List<String> getTargetSusNoFromUnitNameActiveInactiveInvalid(String unit_name, HttpSession sessionUserId) {
		 			Session session = HibernateUtil.getSessionFactory().openSession();
		 			Transaction tx = session.beginTransaction();
		 			
		 			Query q = session.createQuery(
		 					"select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no in ('Active','Inactive','INVALID')");
		 			q.setParameter("unit_name", unit_name);
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
		 		

		 		@RequestMapping(value = "/getIssuingAuthorityListIdCard", method = RequestMethod.POST)
		 			 		public @ResponseBody List<String> getIssuingAuthorityListIdCard(HttpSession sessionA, String unit_name) {
		 			 			Session session = HibernateUtil.getSessionFactory().openSession();
		 			 			Transaction tx = session.beginTransaction();
		 			 			List<String> list = psg_com.getIssuingAuthorityListIdCard(unit_name);
		 			 			tx.commit();

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

		 		@RequestMapping(value = "/getTargetUnitNameListActiveInactiveInvalid", method = RequestMethod.POST)
		 		public @ResponseBody List<String> getTargetUnitNameListActiveInactiveInvalid(String sus_no, HttpSession sessionUserId) {
		 			Session session = HibernateUtil.getSessionFactory().openSession();
		 			Transaction tx = session.beginTransaction();
		 			Query q = session.createQuery(
		 					"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:target_sus_no and status_sus_no in ('Active','Inactive','INVALID')");	
		 			q.setParameter("target_sus_no", sus_no);
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
		 		
		 		@RequestMapping(value = "/getIssuingAuthoritySusListIdCard", method = RequestMethod.POST)
			 		public @ResponseBody List<String> getIssuingAuthoritySusListIdCard(HttpSession sessionA, String sus_no) {
			 			Session session = HibernateUtil.getSessionFactory().openSession();
			 			Transaction tx = session.beginTransaction();
			 			List<String> list = psg_com.getIssuingAuthoritySusListIdCard(sus_no);
			 			tx.commit();

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

}
