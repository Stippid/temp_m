package com.BisagN.MISO;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
	
	/*@RequestMapping(value = "/getTargetSUSNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList(HttpSession sessionA, String sus_no) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");

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
	}*/
	
	
	@RequestMapping(value = "/getTargetUnitsNameActiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveList(HttpSession sessionA, String unit_name) {

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
	
	
//	@RequestMapping(value = "/getTargetUnitNameList", method = RequestMethod.POST)
//	public @ResponseBody List<String> getTargetUnitNameList(String sus_no, HttpSession sessionUserId) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		Query q = session.createQuery(
//				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
//		q.setParameter("sus_no", sus_no);
//		@SuppressWarnings("unchecked")
//		List<String> list = (List<String>) q.list();
//		tx.commit();
//		session.close();
//		String enckey = hex_asciiDao.getAlphaNumericString();
//		Cipher c = null;
//		try {
//			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
//		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
//				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
//			e.printStackTrace();
//		}
//		List<String> FinalList = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			byte[] encCode = null;
//			try {
//				encCode = c.doFinal(list.get(i).getBytes());
//			} catch (IllegalBlockSizeException | BadPaddingException e) {
//				e.printStackTrace();
//			}
//			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
//			FinalList.add(base64EncodedEncryptedCode);
//		}
//		FinalList.add(enckey + "4bsjyg==");
//		return FinalList;
//	}
//	
//	@RequestMapping(value = "/getTargetUnitNameListStnHQ", method = RequestMethod.POST)
//	public @ResponseBody List<String> getTargetUnitNameListStnHQ(String sus_no, HttpSession sessionUserId) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		Query q = session.createQuery(
//				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no =:sus_no and status_sus_no='Active'");
//		q.setParameter("sus_no", "0444"+sus_no.substring(4) );
//		@SuppressWarnings("unchecked")
//		List<String> list = (List<String>) q.list();
//		tx.commit();
//		session.close();
//		String enckey = hex_asciiDao.getAlphaNumericString();
//		Cipher c = null;
//		try {
//			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
//		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
//				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
//			e.printStackTrace();
//		}
//		List<String> FinalList = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			byte[] encCode = null;
//			try {
//				encCode = c.doFinal(list.get(i).getBytes());
//			} catch (IllegalBlockSizeException | BadPaddingException e) {
//				e.printStackTrace();
//			}
//			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
//			FinalList.add(base64EncodedEncryptedCode);
//		}
//		FinalList.add(enckey + "4bsjyg==");
//		return FinalList;
//	}
	
	
	
	
	// pooja
	@RequestMapping(value = "/getTargetSUSFromUNITNAME", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSFromUNITNAME(String unit_name, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery(
				"select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no='Active'");
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
	
	//****************************************START CENSUS DETAIL***********************************************//
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
	
	@RequestMapping(value = "/getpersonnel_noListApproved", method = RequestMethod.POST)
    public @ResponseBody List<String> getpersonnel_noListApproved(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

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
                    
                     q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "+
                    "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");
                     
                     q.setParameter("roleSusNo", roleSusNo);  

            }
            else
            {
                     
                     q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where (p.status='1' or p.status='5') and p.personnel_no  in "+
                    "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");
                                            
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
	

	@RequestMapping(value = "/Search_getpersonnel_no", method = RequestMethod.POST)
    public @ResponseBody List<String> Search_getpersonnel_no(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionHQL.beginTransaction();
    //try{
            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
            String roleType = sessionUserId.getAttribute("roleType").toString();
            Query q= null;
            String roleSusNoMiso = request.getParameter("roleSusNo");
            if(roleAccess.equals("Unit")){
                    
                     q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "+
                                  "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3)) and upper(personnel_no)  like :personnel_no  order by personnel_no");
                     
                     q.setParameter("roleSusNo", roleSusNo);  

            }
            else if(roleSusNoMiso!=null && !roleSusNoMiso.equals(""))
            {
            	  q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and personnel_no  in "+
                          "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3)) and upper(personnel_no)  like :personnel_no  order by personnel_no");
             
             q.setParameter("roleSusNo", roleSusNoMiso); 
            }
            else 
            {
                     
                     q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and  personnel_no  in "+
                    "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status =1 and (c.update_ofr_status=0 or c.update_ofr_status=1 or c.update_ofr_status=3)) and upper(personnel_no)  like :personnel_no  order by personnel_no");
                                            
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
	
	
	
	
	
		//****************************************END CENSUS DETAIL***********************************************//
	
	
	
	//****************************************already methods in miso ***********************************************//
	
	
	/*@RequestMapping(value = "/getCorpsDetailsList",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getOPCorpsDetailsList(String fcode,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		ArrayList<List<String>> list = psg_com.getCorpsList(fcode);
		
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
	}*/
	
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
	
	 
	 @RequestMapping(value = "/getpersonnel_noListRe_Emp", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_noListRe_Emp(String personel_no, HttpSession sessionUserId) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	    //try{
	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	            String roleType = sessionUserId.getAttribute("roleType").toString();
	            Query q= null;
	             
	           
	          
	               	 q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p," + 
	                     		" TB_NON_EFFECTIVE ne " + 
	                     		" where  p.status='4' and  p.id=ne.comm_id and p.id not in ( select comm_id  from TB_REEMPLOYMENT where re_emp_select ='1' and status ='0')" + 
	                     		" and p.id not in (select comm_id from TB_DESERTER ds where status=1 and  ds.dt_recovered is null ) and upper(p.personnel_no)  like :personnel_no and ne.status='1'   order by p.personnel_no "  ).setMaxResults(10);
	            	    
	              
	          
	              
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


	 @RequestMapping(value = "/getpersonnel_noListRe_Call", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_noListRe_Call(String personel_no, HttpSession sessionUserId) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	    //try{
	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	            String roleType = sessionUserId.getAttribute("roleType").toString();
	            Query q= null;
	           
	             q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,\r\n" + 
	            	   		"TB_NON_EFFECTIVE ne " + 
	            	   		"where  p.status='4'  and  p.id=ne.comm_id and p.id not in ( select comm_id  from TB_REEMPLOYMENT where re_emp_select ='2' and status in (0,1))\r\n" + 
	            	   		"and upper(p.personnel_no)  like :personnel_no and ne.status='1' order by p.personnel_no");
	            
	          
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


	 @RequestMapping(value = "/getpersonnel_noListEXT", method = RequestMethod.POST)
     public @ResponseBody List<String> getpersonnel_noListEXT(String personel_no, HttpSession sessionUserId) {

             Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
             Transaction tx = sessionHQL.beginTransaction();
    
             String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
             String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
             String roleType = sessionUserId.getAttribute("roleType").toString();
             Query q= null;
             
             if(roleAccess.equals("Unit")){
          
            q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ," + 
            		" TB_REEMPLOYMENT ne " + 
            		" where  p.status='1' and ne.re_emp_select='2' and p.unit_sus_no = :unit_sus_no " + 
            		" and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='1' order by p.personnel_no");            
            q.setParameter("unit_sus_no", roleSusNo);
 
             }
             else
             {

            	 q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ," + 
                 		" TB_REEMPLOYMENT ne " + 
                 		" where  p.status='1' and ne.re_emp_select='2' " + 
                 		" and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='1' order by p.personnel_no");            
                 
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
     @RequestMapping(value = "/getpersonnel_noListSerEXT", method = RequestMethod.POST)
     public @ResponseBody List<String> getpersonnel_noListSerEXT(String personel_no, HttpSession sessionUserId) {

             Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
             Transaction tx = sessionHQL.beginTransaction();
    
             String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
             String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
             String roleType = sessionUserId.getAttribute("roleType").toString();
             Query q= null;
             
             if(roleAccess.equals("Unit")){
          
            q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ," + 
            		" TB_REEMPLOYMENT ne " + 
            		" where  p.status='1' and ne.re_emp_select='2' " + 
            		" and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='0' order by p.personnel_no");            
         
 
             }
             else
             {

            	 q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p ," + 
                 		" TB_REEMPLOYMENT ne " + 
                 		" where  p.status='1' and ne.re_emp_select='2' " + 
                 		" and p.id=ne.comm_id and upper(p.personnel_no)  like :personnel_no  and ne.status='0' order by p.personnel_no");            
                 
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


	/*
	 * 
	 * 
	 * nmk
	 * 
	 * 
	 * 
	
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
	
	*
	*
	*
	*/


     @RequestMapping(value = "/getpersonnel_noListFORComm", method = RequestMethod.POST)
     public @ResponseBody List<String> getpersonnel_noListFORComm(String personel_no, HttpSession sessionUserId) {           

             Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
             Transaction tx = sessionHQL.beginTransaction();
             
             String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
             String qry="";
             if(roleAccess.equals("DGMS")) {
          	   qry= "and substr(personnel_no,1,2) in ('NR','NS')";
             }
     //try{
             Query q = sessionHQL.createQuery("select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER where status in ('1','5') "+qry+" and "+
                     "  upper(personnel_no) like :personnel_no order by personnel_no").setMaxResults(10);
             
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
    public @ResponseBody List<String> getpersonnel_noList_BA_PY_CA(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionHQL.beginTransaction();
    //try{
            
            Query q= null;
           
         
          q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p,TB_CENSUS_BATT_PHY_CASUALITY n where p.id=n.comm_id and n.status=1 and (p.status='1' or p.status='5') and p.personnel_no  in (select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");
                                            
           
            
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
  	
  	
  //****************************************already methods in miso ***********************************************//
	 
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
 			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
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
 			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
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
 		 
 		 
 		 
 		 
 		@RequestMapping(value = "/getCorpsDetailsList",method = RequestMethod.POST)
 		public @ResponseBody ArrayList<List<String>> getOPCorpsDetailsList(String fcode,HttpSession sessionUserId) 
 			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
 			ArrayList<List<String>> list = SER_DAO.getCorpsList(fcode);
 			
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
 		
 		
 		
}
