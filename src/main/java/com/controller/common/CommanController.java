package com.controller.common;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CommanController {

	@Autowired
	IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 	

	
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
	

     
     public List<String> getActiveUnitNameFromSusNo_Without_Enc(String sus_no,HttpSession sessionA) {
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
			return list;
		}





  	
  	
  //****************************************already methods in miso ***********************************************//
	 

 		
 		/************get Formation from sus no for Reports***************/
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
 		 
 		@RequestMapping(value = "/getip", method = RequestMethod.POST)
 		public @ResponseBody String getip(HttpSession sessionUserId) throws SQLException {			
 			String userIp = sessionUserId.getAttribute("ip").toString();		
 			return userIp;
 		}
 		
 		
 		@RequestMapping(value = "/getCurrentDateWithTimeStamp", method = RequestMethod.POST)
 		public @ResponseBody String getCurrentDateWithTimeStamp(HttpSession sessionUserId) throws SQLException {			
			
 			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	        LocalDateTime now = LocalDateTime.now();
	        String formattedDate = now.format(formatter);

	        return formattedDate;
 		}
 		
 		@RequestMapping(value = "/getarmy_no1", method = RequestMethod.POST)
	 		public @ResponseBody String getarmy_no1(HttpSession sessionUserId) throws SQLException {			
	 			String army_no = sessionUserId.getAttribute("army_no").toString();		
	 			return army_no;
	 		}
 		 
 		 

 		
}
