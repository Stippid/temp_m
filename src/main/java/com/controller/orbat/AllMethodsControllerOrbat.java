package com.controller.orbat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.orbat.AllMethodsDAO;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.T_Domain_Value;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class AllMethodsControllerOrbat {

		HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
		@Autowired
		AllMethodsDAO allMethodDao;
	
		public List<Tbl_CodesForm> getCommandDetailsList() {
			Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionComnd.beginTransaction();
			Query q = sessionComnd.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no as col_0_0_ from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and status_sus_no='Active'");
		
			@SuppressWarnings("unchecked")
		
			List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
			tx.commit();
			sessionComnd.close();
			return list;
		}
		
		public List<Tbl_CodesForm> getCorpsDetailsList() {
			Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionComnd.beginTransaction();
			Query q = sessionComnd.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no as col_0_0_ from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and status_sus_no='Active'");
			@SuppressWarnings("unchecked")
			List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
			tx.commit();
			sessionComnd.close();
			return list;
		}
		public List<Tbl_CodesForm> getDivDetailsList() {
			Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionComnd.beginTransaction();
			Query q = sessionComnd.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no as col_0_0_ from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division') and status_sus_no='Active'");
			@SuppressWarnings("unchecked")
			List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
			tx.commit();
			sessionComnd.close();
			return list;
		}
		public List<Tbl_CodesForm> getBdeDetailsList() {
			Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionComnd.beginTransaction();
			Query q = sessionComnd.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no as col_0_0_ from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade') and status_sus_no='Active'");
			@SuppressWarnings("unchecked")
			List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
			tx.commit();
			sessionComnd.close();
			return list;
		}
		
		// Operation Details
		
		@RequestMapping(value = "/getCorpsDetailsList",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPCorpsDetailsList(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			ArrayList<List<String>> list = allMethodDao.getCorpsList(fcode);
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = c.doFinal(list.get(i).get(0).getBytes());
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    
			    byte[] encCodeVal = c.doFinal(list.get(i).get(1).getBytes());
			    String base64EncodedEncryptedCodeValue = new String(Base64.encodeBase64(encCodeVal));
			    
			    List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedCode);
			    EncList.add(base64EncodedEncryptedCodeValue);
			    FinalList.add(EncList);
		    }
		    // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
		    return FinalList;
		}
		
		@RequestMapping(value = "/getDivDetailsList",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPDivDetailsList(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select SUBSTR(form_code_control,1,6),unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Division') and "
					+ "form_code_control like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add("gDKfjjU+/PZ6k4WWTJB1IA==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@RequestMapping(value = "/getBdeDetailsList",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPBdeDetailsList(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select form_code_control,unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Brigade') and "
					+ "form_code_control like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			//return list;
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		// Get Command,Corps,Div,Bde From Formation and Formation Code
		public List<String> getCommandCorpsDivBdeList(String level_in_hierarchy,String fcode) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy =:level_in_hierarchy and formation_code=:fcode ) and status_sus_no = 'Active'");
			
			q.setParameter("level_in_hierarchy", level_in_hierarchy);
			q.setParameter("fcode", fcode);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			if(list.size() > 0) {
				return list;
			}
			else {
				list.add("");
				return list;
			}
		}
		
		//Parent Arm List Method 
		public List<Tb_Miso_Orbat_Code> getPrantArmList(HttpSession session){
			Session sessionCode = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionCode.beginTransaction();
			Query q = sessionCode.createQuery("from Tb_Miso_Orbat_Code where code_type=:code_type and status_record=:status_record order by code");
			q.setParameter("code_type", "Parent Arm");
			q.setParameter("status_record", "1");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			sessionCode.close();
			return list;
		}
		
		//Type of Arm List Method from Selected Parent Arm
		
		@RequestMapping(value = "/getTypeOfArmArmList", method = RequestMethod.POST)
		public @ResponseBody  ArrayList<List<String>> getTypeOfArmArmList(String code,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session sessionCode = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionCode.beginTransaction();
			Query q = sessionCode.createQuery(" from Tb_Miso_Orbat_Code where code_type='Type of Arm' and status_record='1' and code like :code order by cast(code as int)");
			q.setParameter("code", code+"%");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			sessionCode.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = c.doFinal(list.get(i).getCode().getBytes());
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    
			    byte[] encCodeVal = c.doFinal(list.get(i).getCode_value().getBytes());
			    String base64EncodedEncryptedCodeValue = new String(Base64.encodeBase64(encCodeVal));
			    
			    List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedCode);
			    EncList.add(base64EncodedEncryptedCodeValue);
			    FinalList.add(EncList);
		    }
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	 EncKeyList.add(enckey);
				 EncKeyList.add(enckey);
		    }
		    FinalList.add(EncKeyList);
		    return FinalList;
		}
		
		//Get Type of Arm Name from code  
		public String getTypeOfArmNameBYCode(String typeOfArm) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Tb_Miso_Orbat_Code where code_type='Type of Arm' and status_record='1' and code=:code order by cast(code as int)");
			q.setParameter("code", typeOfArm);
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			String typeOfArmName = list.get(0).getCode_value();
			tx.commit();
			session.close();
			return typeOfArmName;
		}
		
		// ARM Name List
		public List<Tb_Miso_Orabt_Arm_Code> getArmNameList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Tb_Miso_Orabt_Arm_Code Where  status='1' and arm_desc is not null order by arm_code");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orabt_Arm_Code> list = (List<Tb_Miso_Orabt_Arm_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		// LOC List
		public List<Tb_Miso_Orbat_Code> getLOCList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select code,code_value from Tb_Miso_Orbat_Code where code_type='Location' and status_record='1' order by code_value");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getLOC_NRS_TypeLOC_TrnType",method = RequestMethod.POST)
		public @ResponseBody List<String> getLOC_NRS_TypeLOC_TrnType(String locCode,HttpSession sessionUserId) {
			Session session= HibernateUtil.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct a.code_value as location,"
					+ "b.code_value as nrs,"
					+ "a.code as loc_code,"
					+ "a.mod_desc as trn_type,"
					+ "c.label as type_of_loc,"
					+ "a.nrs_code "
					+ "from "
					+ "Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value  c "
					+ "where a.code_type='Location' and b.code_type = 'Location' and a.nrs_code = b.code and "
					+ "a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and "
					+ "c.domainid='TYPEOFLOCATION' and a.code=:locCode order by a.code_value");
			q.setParameter("locCode", locCode);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getTypeOfUnitList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
			q.setParameter("domainid", "TYPEOFFORCE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
				
		// get ArmName From ArmCode 
		public List<Tb_Miso_Orabt_Arm_Code> getArmNameFromArmCodeList(String arm_code) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code=:arm_code ");
			q.setParameter("arm_code", arm_code);
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orabt_Arm_Code> list = (List<Tb_Miso_Orabt_Arm_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getTypeOfUnitFromUnitNoList(String type_force) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid=:domainid and codevalue=:type_force");
			q.setParameter("type_force", type_force);
			q.setParameter("domainid", "TYPEOFFORCE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getType_Of_LetterList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid=:domainid order by codevalue");
			q.setParameter("domainid", "SCHEDULETYPE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	public List<T_Domain_Value> getType_Of_LetterList_final() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid=:domainid and codevalue='9' order by codevalue");
			q.setParameter("domainid", "SCHEDULETYPE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			System.err.println("value in list is "+list);
			tx.commit();
			session.close();
			return list;
		}
		
		// Active Unit Name Autocomplate
		@RequestMapping(value = "/getUnitsNameActiveList",method = RequestMethod.POST)
		public @ResponseBody List<String> getUnitsNameActiveList(HttpSession sessionA,String unit_name) {
			unit_name = unit_name.replace("&#40;","(");
			unit_name = unit_name.replace("&#41;",")");
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter") || roleAccess.equals("DGMS")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active'").setMaxResults(10);
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Corps")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Division")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Brigade")) {
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
			}
			if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
				unit_name = getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0);
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' ").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("Line_dte")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and arm_code=:roleArmCode ").setMaxResults(10);
				q.setParameter("roleArmCode", roleArmCode);
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartII")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and ct_part_i_ii=:CTPartII").setMaxResults(10);
				q.setParameter("CTPartII", "CTPartII");
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartI")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and ct_part_i_ii=:CTPartI").setMaxResults(10);
				q.setParameter("CTPartI", "CTPartI");
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			//return list;
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
				e1.printStackTrace();
			}
			
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		public List<String> getSusNoActiveValidation(String sus_no)  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) =:sus_no and status_sus_no  = 'Active'");
			q.setParameter("sus_no", sus_no.toUpperCase());
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		// Active Sus No Autocomplate
		@RequestMapping(value = "/getSusNoActiveList",method = RequestMethod.POST)
		public @ResponseBody List<String> getSusNoActiveList(HttpSession sessionA,String sus_no)  {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null; 
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter") || roleAccess.equals("DGMS")) {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
					q.setParameter("roleFormationNo", roleFormationNo+"%");
					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				}
				if(roleSubAccess.equals("Corps")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
					q.setParameter("roleFormationNo", roleFormationNo+"%");
					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				}
				if(roleSubAccess.equals("Division")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
					q.setParameter("roleFormationNo", roleFormationNo+"%");
					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				}
				if(roleSubAccess.equals("Brigade")) {
					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
					q.setParameter("roleFormationNo", roleFormationNo+"%");
					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				}
			}
			if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
				sus_no = roleSusNo;
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(1);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			if(roleAccess.equals("Line_dte")) {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and arm_code=:roleArmCode and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleArmCode", roleArmCode);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartII")) {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and ct_part_i_ii=:CTPartII and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("CTPartII", "CTPartII");
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartI")) {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and ct_part_i_ii=:CTPartI and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("CTPartI", "CTPartI");
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		// All Unit Name for Autocomplate
		@RequestMapping(value = "/getUnitsNameAllList",method = RequestMethod.POST)
		public @ResponseBody List<String> getUnitsNameALLList(String unit_name,HttpSession sessionA) {
			unit_name = unit_name.replace("&#40;","(");
			unit_name = unit_name.replace("&#41;",")");
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name").setMaxResults(10);
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
					q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Corps")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Division")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
				if(roleSubAccess.equals("Brigade")) {
					q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", roleFormationNo+"%");
				}
			}
			if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
				unit_name = getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0);
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name ").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("Line_dte")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code=:roleArmCode ").setMaxResults(10);
				q.setParameter("roleArmCode", roleArmCode);
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartII")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and ct_part_i_ii=:CTPartII").setMaxResults(10);
				q.setParameter("CTPartII", "CTPartII");
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			if(roleAccess.equals("CTPartI")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and ct_part_i_ii=:CTPartI").setMaxResults(10);
				q.setParameter("CTPartI", "CTPartI");
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
			}
			
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		// Inactive Unit Name Autocomplate
		@RequestMapping(value = "/getUnitsNameInactiveList",method = RequestMethod.POST)
		public @ResponseBody List<String> getUnitsNameInActiveList(String unit_name,HttpSession sessionUserId) {
			unit_name = unit_name.replace("&#40;","(");
			unit_name = unit_name.replace("&#41;",")");
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(!unit_name.equals("")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where status_sus_no =:status_sus_no1 and upper(unit_name) like :unit_name and sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  =:status_sus_no2 )");
				q.setParameter("status_sus_no1", "Inactive");
				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
				q.setParameter("status_sus_no2", "Pending");
			}else {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where status_sus_no =:status_sus_no1 and sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  =:status_sus_no2 )");
				q.setParameter("status_sus_no1", "Inactive");
				q.setParameter("status_sus_no2", "Pending");
			}
			q.setMaxResults(20);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			//return list;
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    // Enc Key Append Last value of List
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		// Inactive Sus No Autocomplate
		@RequestMapping(value = "/getSusNoInactiveList",method = RequestMethod.POST)
		public @ResponseBody List<String> getSusNoInActiveList(String sus_no,HttpSession sessionUserId) {
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(!sus_no.equals("")) {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where status_sus_no ='Inactive' and upper(sus_no) like :sus_no and sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') order by SUBSTRING(sus_no,1,7)");
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}else {
				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where status_sus_no ='Inactive' and sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')");
			}
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			//return list;
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		@RequestMapping(value = "/getUnitDetailsList",method = RequestMethod.POST)
		public @ResponseBody List<String> getUnitDetailsList(String unitName,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			unitName = unitName.replace("&#40;","(");
			unitName = unitName.replace("&#41;",")");
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Miso_Orbat_Unt_Dtl where unit_name=:unitName and status_sus_no='Active'");
			q.setParameter("unitName", unitName);
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			List<String> Finallist = new ArrayList<String>();
			
			if(list.size() > 0) {
					
				String id= new String(Base64.encodeBase64(c.doFinal(String.valueOf(list.get(0).getId()).getBytes())));
				String sus_no= new String(Base64.encodeBase64(c.doFinal(list.get(0).getSus_no().getBytes())));
				String unit_name= new String(Base64.encodeBase64(c.doFinal(list.get(0).getUnit_name().getBytes())));
				
				String unit_army_hq= "";
				if(list.get(0).getUnit_army_hq() != null){
					unit_army_hq= new String(Base64.encodeBase64(c.doFinal(list.get(0).getUnit_army_hq().getBytes())));
				}else {
					unit_army_hq= "";
				}
				
				String form_code_operation= new String(Base64.encodeBase64(c.doFinal(list.get(0).getForm_code_operation().getBytes())));
				String form_code_control= new String(Base64.encodeBase64(c.doFinal(list.get(0).getForm_code_control().getBytes())));
				String form_code_admin= new String(Base64.encodeBase64(c.doFinal(list.get(0).getForm_code_admin().getBytes())));
				String arm_code= new String(Base64.encodeBase64(c.doFinal(list.get(0).getArm_code().getBytes())));
				
				ArrayList<ArrayList<String>> LOC_NRS_TypeLOC_TrnType = allMethodDao.getLOC_NRS_TypeLOC_TrnType(list.get(0).getCode());
				String loc_code= new String(Base64.encodeBase64(c.doFinal(list.get(0).getCode().getBytes())));
				String loc_name= new String(Base64.encodeBase64(c.doFinal(LOC_NRS_TypeLOC_TrnType.get(0).get(0).getBytes())));
				String nrs_code= new String(Base64.encodeBase64(c.doFinal(LOC_NRS_TypeLOC_TrnType.get(0).get(5).getBytes())));
				String nrs_name= new String(Base64.encodeBase64(c.doFinal(LOC_NRS_TypeLOC_TrnType.get(0).get(1).getBytes())));
				String type_of_loc= new String(Base64.encodeBase64(c.doFinal(LOC_NRS_TypeLOC_TrnType.get(0).get(4).getBytes())));
				String modification= new String(Base64.encodeBase64(c.doFinal(LOC_NRS_TypeLOC_TrnType.get(0).get(3).getBytes())));
				
			    String type_force= new String(Base64.encodeBase64(c.doFinal(list.get(0).getType_force().getBytes())));
			    String ct_part_i_ii= new String(Base64.encodeBase64(c.doFinal(list.get(0).getCt_part_i_ii().getBytes())));
			    
			    String address= "";
			    if(list.get(0).getAddress() != null) {
			    	address= new String(Base64.encodeBase64(c.doFinal(list.get(0).getAddress().getBytes())));
			    }else {
			    	address = "";
			    }
			  
			    String state = list.get(0).getState();
			    String district= list.get(0).getDistrict();
			    
			    String depart_date = "";
			    if(list.get(0).getComm_depart_date() != null){
			    	String comm_date = new SimpleDateFormat("yyyy-MM-dd").format(list.get(0).getComm_depart_date());
			    	depart_date = new String(Base64.encodeBase64(c.doFinal(comm_date.getBytes())));
			    }else {
			    	 depart_date = "";
			    }
			   
			    
			    String compltn_arrv_date = "";
			    if(list.get(0).getCompltn_arrv_date() != null){
			    	String arrv_date = new SimpleDateFormat("yyyy-MM-dd").format(list.get(0).getCompltn_arrv_date());
			    	compltn_arrv_date = new String(Base64.encodeBase64(c.doFinal(arrv_date.getBytes())));
			    }else {
			    	compltn_arrv_date = "";
			    }
			    
			   // String parentArm = String.valueOf(list.get(0).getSus_no().charAt(0)) + String.valueOf(list.get(0).getSus_no().charAt(1));
			   // String typeOfArm = String.valueOf(list.get(0).getSus_no().charAt(0)) + String.valueOf(list.get(0).getSus_no().charAt(1)) + String.valueOf(list.get(0).getSus_no().charAt(2)) + String.valueOf(list.get(0).getSus_no().charAt(3));
			    		
			    //String parentArmEnc = new String(Base64.encodeBase64(c.doFinal(parentArm.getBytes())));
			   // String typeOfArmEnc = new String(Base64.encodeBase64(c.doFinal(typeOfArm.getBytes())));
			  //  String typeOfArmName =  new String(Base64.encodeBase64(c.doFinal(getTypeOfArmNameBYCode(typeOfArm).getBytes())));
			    
			    
			    
			    Finallist.add(id); 					// 0
				Finallist.add(sus_no);				// 1	
				Finallist.add(unit_name);			// 2
				Finallist.add(unit_army_hq);		// 3
				Finallist.add(form_code_operation);	// 4
				Finallist.add(form_code_control);	// 5
				Finallist.add(form_code_admin);		// 6
				Finallist.add(arm_code);			// 7
				Finallist.add(loc_code); 			// 8 location code
				Finallist.add(loc_name);			// 9 location Name			
				Finallist.add(nrs_code);  			// 10 NRS CODE		
				Finallist.add(nrs_name); 			// 11 NRS Name		
				Finallist.add(type_of_loc); 		// 12 Type of Location	
				Finallist.add(modification); 		// 13 Modification (Trn Type)
				Finallist.add(type_force);			// 14 Type of force
				Finallist.add(ct_part_i_ii);		// 15
			    Finallist.add(address);				// 16
				Finallist.add(state);				// 17
				Finallist.add(district);			// 18
				Finallist.add(depart_date);			// 19
				Finallist.add(compltn_arrv_date);   // 20
				
				Finallist.add(enckey+"4bsjyg==");
			}
		    return Finallist;
		}

			
		// Inactive Unit Details List by Unit Name
		@RequestMapping(value = "/getUnitDetailsInactiveList",method = RequestMethod.POST)
		public @ResponseBody List<Miso_Orbat_Unt_Dtl> getUnitDetailsInactiveList(String unitName,HttpSession sessionUserId) {
			unitName = unitName.replace("&#40;","(");
			unitName = unitName.replace("&#41;",")");
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Miso_Orbat_Unt_Dtl where unit_name=:unitName and status_sus_no='Inactive' ");
			q.setParameter("unitName", unitName);
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		// InActive SUS No Details List by Unit Name
		@RequestMapping(value = "/getInactiveSusNoList",method = RequestMethod.POST)
		public @ResponseBody List<Miso_Orbat_Unt_Dtl> getInactiveSusNoList(String sus_no,HttpSession sessionA) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Inactive'");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		// Active SUS No Details List by Sus No
		@RequestMapping(value = "/getSusNoDetailsList",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getSusNoDetailsList(String sus_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct unit_name,arm_code from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String getsus_no = (String)listObject[0];
		   		String getarm_code = (String)listObject[1];

		   		byte[] encsusNo = c.doFinal(getsus_no.getBytes());
			    String base64EncodedEncryptedSusNo = new String(Base64.encodeBase64(encsusNo));
			    
			    byte[] encArmCode = c.doFinal(getarm_code.getBytes());
			    String base64EncodedEncryptedArmCode = new String(Base64.encodeBase64(encArmCode));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedSusNo);
			    EncList.add(base64EncodedEncryptedArmCode);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"YbFjyB==");
		    	EncKeyList.add(enckey+"HNTrgS==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		// All Unit Details List by Unit Name
		@RequestMapping(value = "/getAllUnitDetailsListPartialSwap", method = RequestMethod.POST)
		public @ResponseBody List<String> getAllUnitDetailsListPartialSwap(String unitName,HttpSession sessionUserId) {
			unitName = unitName.replace("&#40;","(");
			unitName = unitName.replace("&#41;",")");
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unitName order by id desc").setMaxResults(1);
			q.setParameter("unitName", unitName);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
				
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
		    if(list.size() != 0) {
		    	FinalList.add(enckey+"0fsjyg==");
		    }
		    return FinalList;
		}
		
		public List<String> getAllUnitNameFromSusNo_Without_Enc(String sus_no,HttpSession sessionA) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no!='Pending' and id < (select MAX(id) from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no!='Pending') order by id desc").setMaxResults(1);
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		
		public List<String> getAllUnitNameSus_no_With_scenario_status_sus_no(String status_sus_no,String sus_no,HttpSession sessionA) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no=:status_sus_no order by id desc").setMaxResults(1);
			q.setParameter("sus_no", sus_no);
			q.setParameter("status_sus_no", status_sus_no);
			
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		
		public List<String> getActiveUnitNameFromSusNo_Without_Enc(String sus_no,HttpSession sessionA) {
			/*String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();*/
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
				
		@RequestMapping(value = "/getActiveUnitNameFromSusNo", method = RequestMethod.POST)
		public @ResponseBody List<String> getActiveUnitNameFromSusNo1(String sus_no,HttpSession sessionA) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			//return list;
			List<String> FinalList = new ArrayList<String>();
			if(list.size() != 0) {
				String enckey = hex_asciiDao.getAlphaNumericString();
				Cipher c = null;
				try {
					c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
						| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
				}
				String base64UnitName = null;
				try {
					base64UnitName = new String(Base64.encodeBase64(c.doFinal(list.get(0).getBytes())));
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					
				}
				
				FinalList.add(base64UnitName);
				FinalList.add(enckey+"GQFjyB==");
		    }
		    return FinalList;
		}
		@RequestMapping(value = "/getActiveSusNoFromUnitName",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getActiveSusNoFromUnitName(String unit_name,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			unit_name = unit_name.replace("&#40;","(");
			unit_name = unit_name.replace("&#41;",")");
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct sus_no,arm_code from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no='Active'");
			q.setParameter("unit_name", unit_name);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String sus_no = (String)listObject[0];
		   		String arm_code = (String)listObject[1];

		   		byte[] encsusNo = c.doFinal(sus_no.getBytes());
			    String base64EncodedEncryptedSusNo = new String(Base64.encodeBase64(encsusNo));
			    
			    byte[] encArmCode = c.doFinal(arm_code.getBytes());
			    String base64EncodedEncryptedArmCode = new String(Base64.encodeBase64(encArmCode));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedSusNo);
			    EncList.add(base64EncodedEncryptedArmCode);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"YbFjyB==");
		    	EncKeyList.add(enckey+"HNTrgS==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		public String EncMSG(String msg,HttpSession sessionEnc) {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionEnc,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			
		    byte[] encMSG = null;
			try {
				encMSG = c.doFinal(msg.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				
			}
		    String base64EncodedEncryptedMSG = new String(Base64.encodeBase64(encMSG));
			base64EncodedEncryptedMSG +=":"+enckey;
		    return base64EncodedEncryptedMSG;
		}
		
		@RequestMapping(value = "/getArmCodeFromSus_no",method = RequestMethod.POST)
		public @ResponseBody List<String> getArmCodeFromSus_no(String sus_no,HttpSession sessionUserId) {
			Session session= HibernateUtil.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select arm_code from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active' ");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getSusNoActiveListorbat",method = RequestMethod.POST)
 		public @ResponseBody List<String> getSusNoActiveListorbat(HttpSession sessionA,String sus_no,String scnario)  {
 			String roleAccess = sessionA.getAttribute("roleAccess").toString();
 			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
 			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
 			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
 			String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
 			System.out.println("sc: " + scnario)  ;
 			Session session = HibernateUtil.getSessionFactory().openSession();
 			Transaction tx = session.beginTransaction();
 			Query q = null; 
 			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
 				
 				if(scnario.equals("12")) {
 					System.out.println("misooooo");
 					q = session.createQuery("select utl.sus_no from Miso_Orbat_Unt_Dtl utl,\r\n"
 							+ "	Tbl_CodesForm c\r\n"
 							+ "	where utl.sus_no not in \r\n"
 							+ "	(select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') \r\n"
 							+ "	and c.sus_no=utl.sus_no and c.level_in_hierarchy != 'Unit'\r\n"
 							+ "	and upper(utl.sus_no) like :sus_no and utl.status_sus_no  = 'Active' \r\n"
 							+ "	order by SUBSTRING(utl.sus_no,1,7)").setMaxResults(10);
 				}else {
 				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 				
 				}
 				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("Formation")) {
 				if(roleSubAccess.equals("Command")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
 					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 				}
 				if(roleSubAccess.equals("Corps")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
 					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 				}
 				if(roleSubAccess.equals("Division")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
 					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 				}
 				if(roleSubAccess.equals("Brigade")) {
 					q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 					q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 				}
 			}
 			if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
 				sus_no = roleSusNo;
 				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(1);
 				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("Line_dte")) {
 				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and arm_code=:roleArmCode and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 				q.setParameter("roleArmCode", roleArmCode);
 				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("CTPartII")) {
 				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and ct_part_i_ii=:CTPartII and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 				q.setParameter("CTPartII", "CTPartII");
 				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("CTPartI")) {
 				q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') and upper(sus_no) like :sus_no and ct_part_i_ii=:CTPartI and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
 				q.setParameter("CTPartI", "CTPartI");
 				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
 			}
 			@SuppressWarnings("unchecked")
 			List<String> list = (List<String>) q.list();
 			tx.commit();
 			session.close();
 			
 			String enckey = hex_asciiDao.getAlphaNumericString();
 			Cipher c = null;
 			try {
 				c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
 			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
 					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
 				
 			}
 			List<String> FinalList = new ArrayList<String>();
 		    for(int i=0; i<list.size();i++) {
 		    	byte[] encCode = null;
 				try {
 					encCode = c.doFinal(list.get(i).getBytes());
 				} catch (IllegalBlockSizeException | BadPaddingException e) {
 					
 				}
 			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
 			    FinalList.add(base64EncodedEncryptedCode);
 			}
 		    if(list.size() != 0) {
 		    	FinalList.add(enckey+"0fsjyg==");
 		    }
 		    return FinalList;
 		}
		
		@RequestMapping(value = "/getUnitsNameActiveListOrbat",method = RequestMethod.POST)
 		public @ResponseBody List<String> getUnitsNameActiveListOrbat(HttpSession sessionA,String unit_name, String scnario) {
 			unit_name = unit_name.replace("&#40;","(");
 			unit_name = unit_name.replace("&#41;",")");
 			String roleAccess = sessionA.getAttribute("roleAccess").toString();
 			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
 			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
 			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
 			String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
 			
 			Session session = HibernateUtil.getSessionFactory().openSession();
 			Transaction tx = session.beginTransaction();
 			Query q = null;
 			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
 				if(scnario.equals("12")) {
 					System.out.println("misooooo");
 					q = session.createQuery("select utl.unit_name from Miso_Orbat_Unt_Dtl utl,\r\n"
 							+ "	Tbl_CodesForm c\r\n"
 							+ "	where utl.sus_no not in \r\n"
 							+ "	(select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending') \r\n"
 							+ "	and c.sus_no=utl.sus_no and c.level_in_hierarchy != 'Unit'\r\n"
 							+ "	and upper(utl.unit_name) like :unit_name and utl.status_sus_no  = 'Active' \r\n"
 							+ "	order by SUBSTRING(utl.sus_no,1,7)").setMaxResults(10);
 				}else {
 				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active'").setMaxResults(10);
 				}
 				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("Formation")) {
 				if(roleSubAccess.equals("Command")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
 					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
 					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 				}
 				if(roleSubAccess.equals("Corps")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
 					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
 					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 				}
 				if(roleSubAccess.equals("Division")) {
 					roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
 					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
 					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 				}
 				if(roleSubAccess.equals("Brigade")) {
 					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
 					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 					q.setParameter("roleFormationNo", roleFormationNo+"%");
 				}
 			}
 			if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
 				unit_name = getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0);
 				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' ").setMaxResults(10);
 				q.setParameter("unit_name", unit_name.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("Line_dte")) {
 				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and arm_code=:roleArmCode ").setMaxResults(10);
 				q.setParameter("roleArmCode", roleArmCode);
 				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("CTPartII")) {
 				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and ct_part_i_ii=:CTPartII").setMaxResults(10);
 				q.setParameter("CTPartII", "CTPartII");
 				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 			}
 			if(roleAccess.equals("CTPartI")) {
 				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no not in (select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no  = 'Pending')  and upper(unit_name) like :unit_name and status_sus_no = 'Active' and ct_part_i_ii=:CTPartI").setMaxResults(10);
 				q.setParameter("CTPartI", "CTPartI");
 				q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
 			}
 			@SuppressWarnings("unchecked")
 			List<String> list = (List<String>) q.list();
 			tx.commit();
 			session.close();
 			//return list;
 			String enckey = hex_asciiDao.getAlphaNumericString();
 			Cipher c = null;
 			
 			try {
 				c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
 			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
 					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
 				e1.printStackTrace();
 			}
 			
 			List<String> FinalList = new ArrayList<String>();
 		    for(int i=0; i<list.size();i++) {
 		    	byte[] encCode = null;
 				try {
 					encCode = c.doFinal(list.get(i).getBytes());
 				} catch (IllegalBlockSizeException | BadPaddingException e) {
 					
 				}
 			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
 			    FinalList.add(base64EncodedEncryptedCode);
 			}
 		    if(list.size() != 0) {
 		    	FinalList.add(enckey+"0fsjyg==");
 		    }
 		    return FinalList;
 		}
 		
 			@RequestMapping(value = "/getBdeDetailsListop",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getBdeDetailsListop(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select form_code_operation,unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Brigade') and "
					+ "form_code_operation like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			//return list;
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		@RequestMapping(value = "/getBdeDetailsListadm",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getBdeDetailsListadm(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select form_code_admin,unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Brigade') and "
					+ "form_code_admin like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			//return list;
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		@RequestMapping(value = "/getDivDetailsListop",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPDivDetailsListop(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select SUBSTR(form_code_operation,1,6),unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Division') and "
					+ "form_code_operation like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add("gDKfjjU+/PZ6k4WWTJB1IA==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@RequestMapping(value = "/getDivDetailsListadm",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getDivDetailsListadm(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select SUBSTR(form_code_admin,1,6),unit_name FROM Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy ='Division') and "
					+ "form_code_admin like :fcode and status_sus_no = 'Active'");
			q.setParameter("fcode", fcode+"%");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];

		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
			 // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add("gDKfjjU+/PZ6k4WWTJB1IA==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@RequestMapping(value = "/getCorpsDetailsListop",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPCorpsDetailsListop(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			ArrayList<List<String>> list = allMethodDao.getCorpsListop(fcode);
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = c.doFinal(list.get(i).get(0).getBytes());
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    
			    byte[] encCodeVal = c.doFinal(list.get(i).get(1).getBytes());
			    String base64EncodedEncryptedCodeValue = new String(Base64.encodeBase64(encCodeVal));
			    
			    List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedCode);
			    EncList.add(base64EncodedEncryptedCodeValue);
			    FinalList.add(EncList);
		    }
		    // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
		    return FinalList;
		}
		@RequestMapping(value = "/getCorpsDetailsListadm",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getOPCorpsDetailsListadm(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			ArrayList<List<String>> list = allMethodDao.getCorpsListadm(fcode);
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = c.doFinal(list.get(i).get(0).getBytes());
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    
			    byte[] encCodeVal = c.doFinal(list.get(i).get(1).getBytes());
			    String base64EncodedEncryptedCodeValue = new String(Base64.encodeBase64(encCodeVal));
			    
			    List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedCode);
			    EncList.add(base64EncodedEncryptedCodeValue);
			    FinalList.add(EncList);
		    }
		    // Enc Key Append Last value of List
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
		    return FinalList;
		}
		
		//raj 28.10.2024
		

		// AUTOFILL
 		
		@RequestMapping(value = "/getFmnName", method = RequestMethod.POST)
		public @ResponseBody List<String> getFmnName(HttpSession sessionA, String fmn_name) {

			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			
			q = session.createQuery("select fmn_name from Tb_Miso_Orbat_Mast_Fmn where fmn_name like :fmn_name "
					+ "and status_record = '1' ");
			q.setParameter("fmn_name","%" +  fmn_name.toUpperCase() + "%");

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
				
				
				
				
				@RequestMapping(value = "/getAllDataByUN",method = RequestMethod.POST)
				public @ResponseBody ArrayList<List<String>> getAllDataByUN(String unit_name,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = session.createQuery("select distinct arm_code,fmn_code,loc_code,nrs_code,from_date,to_date"
							+ " from Tb_Miso_Orbat_Cii_Unt_Dtl where unit_name = :unit_name and status_sus_no = 'Active");
					q.setParameter("unit_name", unit_name);
					@SuppressWarnings("unchecked")
					List<Object[]>  list = (List<Object[]> ) q.list();
					tx.commit();
					session.close();
					
					String enckey = hex_asciiDao.getAlphaNumericString();
					Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
					ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

					for(Object[] listObject: list){
				    	String arm_code = (String)listObject[0];
				   		String fmn_code = (String)listObject[1];
				   		String loc_code = (String)listObject[2];
				   		String nrs_code = (String)listObject[3];
				   		Timestamp from_date = (Timestamp)listObject[4];
				   		Timestamp to_date = (Timestamp)listObject[5];
				   		
				   		byte[] encArmCode = c.doFinal(arm_code.getBytes());
					    String base64EncodedEncryptedArmCode = new String(Base64.encodeBase64(encArmCode));
					    
					    byte[] encFmnCode = c.doFinal(fmn_code.getBytes());
					    String base64EncodedEncryptedFmnCode = new String(Base64.encodeBase64(encFmnCode));
					    
					    byte[] encLocCode = c.doFinal(loc_code.getBytes());
					    String base64EncodedEncryptedLocCode = new String(Base64.encodeBase64(encLocCode));
					    
					    byte[] encNrsCode = c.doFinal(nrs_code.getBytes());
					    String base64EncodedEncryptedNrsCode = new String(Base64.encodeBase64(encNrsCode));
					    
					    byte[] encFromDate = c.doFinal(from_date.toString().getBytes());
					    String base64EncodedEncryptedFromDate = new String(Base64.encodeBase64(encFromDate));
					    
					    byte[] encToDate = c.doFinal(to_date.toString().getBytes());
					    String base64EncodedEncryptedToDate = new String(Base64.encodeBase64(encToDate));
				   		
				   		List<String> EncList = new ArrayList<String>();
					    EncList.add(base64EncodedEncryptedArmCode);
					    EncList.add(base64EncodedEncryptedFmnCode);
					    EncList.add(base64EncodedEncryptedLocCode);
					    EncList.add(base64EncodedEncryptedNrsCode);
					    EncList.add(base64EncodedEncryptedFromDate);
					    EncList.add(base64EncodedEncryptedToDate);
					    FinalList.add(EncList);
				   	}
					 // Enc Key Append Last value of List
				    List<String> EncKeyList = new ArrayList<String>();
				    if(list.size() != 0) {
				    	EncKeyList.add(enckey+"4bsjyg==");
				    	EncKeyList.add("gDKfjjU+/PZ6k4WWTJB1IA==");
				    }
				    FinalList.add(EncKeyList);
					return FinalList;
				}
				
				
				@RequestMapping(value = "/getSusNoActiveListForComdAndCont",method = RequestMethod.POST)
				public @ResponseBody List<String> getSusNoActiveListForComdAndCont(HttpSession sessionA,String sus_no)  {			
					
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = null;  				
							
							q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from  Tbl_CodesForm\r\n"
									+ "where level_in_hierarchy not in  ('Depot', 'Unit'))  and upper(sus_no) like :sus_no and status_sus_no = 'Active'").setMaxResults(10);
							q.setParameter("sus_no", sus_no.toUpperCase()+"%");
							
				
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();
					session.close();
					
					String enckey = hex_asciiDao.getAlphaNumericString();
					Cipher c = null;
					try {
						c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
							| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
						
					}
					List<String> FinalList = new ArrayList<String>();
				    for(int i=0; i<list.size();i++) {
				    	byte[] encCode = null;
						try {
							encCode = c.doFinal(list.get(i).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							
						}
					    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
					    FinalList.add(base64EncodedEncryptedCode);
					}
				    if(list.size() != 0) {
				    	FinalList.add(enckey+"0fsjyg==");
				    }
				    return FinalList;
				}
				
			
				@RequestMapping(value = "/getUnitsNameActiveListForComdAndCont",method = RequestMethod.POST)
				public @ResponseBody List<String> getUnitsNameActiveListForComdAndCont(HttpSession sessionA,String unit_name) {
					unit_name = unit_name.replace("&#40;","(");
					unit_name = unit_name.replace("&#41;",")");
					
					
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = null;
					
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from  Tbl_CodesForm\r\n"
							+ "where level_in_hierarchy not in  ('Depot', 'Unit'))  and upper(unit_name) like :unit_name and status_sus_no = 'Active'").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();
					session.close();
					//return list;
					String enckey = hex_asciiDao.getAlphaNumericString();
					Cipher c = null;
					
					try {
						c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
							| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
						e1.printStackTrace();
					}
					
					List<String> FinalList = new ArrayList<String>();
				    for(int i=0; i<list.size();i++) {
				    	byte[] encCode = null;
						try {
							encCode = c.doFinal(list.get(i).getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							
						}
					    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
					    FinalList.add(base64EncodedEncryptedCode);
					}
				    if(list.size() != 0) {
				    	FinalList.add(enckey+"0fsjyg==");
				    }
				    return FinalList;
				}

}