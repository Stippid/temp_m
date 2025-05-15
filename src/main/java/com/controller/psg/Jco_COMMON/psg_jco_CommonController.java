package com.controller.psg.Jco_COMMON;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.psg.Jco_Transaction.TB_PSG_MISO_ROLE_HDR_STATUS_JCO;
import com.models.psg.Jco_Transaction.TB_RE_CALL;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class psg_jco_CommonController {
	
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@RequestMapping(value = "/getRecordofficeSUSNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getRecordofficeSUSNoList(HttpSession sessionA, String sus_no) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		q = session.createQuery("select sus_no from TB_PSG_MSTR_RECORD_OFFICE_JCO where upper(sus_no) like :sus_no and status='active' order by sus_no").setMaxResults(10);
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
	}


@RequestMapping(value = "/getRecordofficeUnitNameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getRecordofficeUnitNameList(String sus_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct record_office from TB_PSG_MSTR_RECORD_OFFICE_JCO where sus_no=:sus_no and status='active'");
		q.setParameter("sus_no", sus_no);
	
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


@RequestMapping(value = "/getRecordofficeSUSNoFromUnitName", method = RequestMethod.POST)
public @ResponseBody List<String> getRecordofficeSUSNoFromUnitName(HttpSession sessionA, String record_office) {

	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	
	
	
	Query q = session.createQuery(
			"select distinct sus_no from TB_PSG_MSTR_RECORD_OFFICE_JCO where upper(record_office)=:record_office and status='active'");
	q.setParameter("record_office", record_office.toUpperCase());
	
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


@RequestMapping(value = "/getRecordoffice_UnitNameList", method = RequestMethod.POST)
public @ResponseBody List<String> getRecordoffice_UnitNameList(String record_office, HttpSession sessionUserId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select record_office from TB_PSG_MSTR_RECORD_OFFICE_JCO where upper(record_office) like :record_office and status='active' order by sus_no").setMaxResults(10);
	q.setParameter("record_office", record_office.toUpperCase() + "%");

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


@RequestMapping(value = "/getpersonnel_noListApproved_JCO", method = RequestMethod.POST)
public @ResponseBody List<String> getpersonnel_noListApproved_JCO(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

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
                
                 q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p where unit_sus_no=:roleSusNo and (p.status=1 or p.status=5)  "+
                              " and (p.update_jco_status=0 or p.update_jco_status=1) and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
                 
                 q.setParameter("roleSusNo", roleSusNo);  

        }
        else
        {
                 
        	  q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p where   (p.status=1 or p.status=5)  "+
                      " and (p.update_jco_status=0 or p.update_jco_status=1) and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
                                    
        }
       
        q.setParameter("army_no", personel_no.toUpperCase()+"%");
        
     
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

/*@RequestMapping(value = "/GetArmyNoData", method = RequestMethod.POST)
public @ResponseBody List<String> GetPersonnelNoData(String army_no) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	try {
		Query q1 = sessionHQL.createQuery(
				"	select c.id,g.gender_name,c.date_of_birth ,c.name,c.arm_service,c.regiment,c.enroll_dt \r\n"
						+ "					 FROM TB_CENSUS_JCO_OR_PARENT c\r\n"
						+ "					  ,TB_GENDER g 					\r\n"
						+ "					where  upper(c.army_no) =:army_no and c.gender=g.id and \r\n"
						+ "					 (c.status='1' or c.status = '5')   order by c.army_no;"

		);
		q1.setParameter("army_no", army_no.toUpperCase());
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
}*/

@RequestMapping(value = "/Search_getArmy_no", method = RequestMethod.POST)
public @ResponseBody List<String> Search_getArmy_no(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
//try{
        String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
        String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
        String roleType = sessionUserId.getAttribute("roleType").toString();
        Query q= null;
        String rsus=request.getParameter("roleSusNo");
        if( rsus!=null && !rsus.equals("")){
        	roleSusNo=rsus;
        }
        
        if(roleSusNo!=null && !roleSusNo.equals("")){
                
                 q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p where unit_sus_no=:roleSusNo and (p.status=1 or p.status=5)  "+
                              " and (p.update_jco_status=0 or p.update_jco_status=1 or p.update_jco_status=3) and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
                 
                 q.setParameter("roleSusNo", roleSusNo);  

        }
        else
        {
                 
        	  q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p where   (p.status=1 or p.status=5)  "+
                      " and (p.update_jco_status=0 or p.update_jco_status=1 or p.update_jco_status=3) and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
                                    
        }
       
        q.setParameter("army_no", personel_no.toUpperCase()+"%");
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

@RequestMapping(value = "/GetArmyNoData", method = RequestMethod.POST)
public @ResponseBody List<String> GetArmyNoData(String army_no) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	List<String> list=null;
	
	try {
		Query q1 = sessionHQL.createQuery(
				"	select c.id,g.gender_name,c.date_of_birth ,c.full_name,c.arm_service,c.regiment,c.enroll_dt,r.rank,c.unit_sus_no,c.category \r\n"
						+ " FROM TB_CENSUS_JCO_OR_PARENT c \r\n"
						+ "	 ,TB_GENDER g ,TB_PSG_MSTR_RANK_JCO r	\r\n"
						+ "	where  upper(c.army_no) =:army_no and c.gender=g.id and  r.id = c.rank and \r\n"
						+ "	 (c.status='1' or c.status = '5')   order by c.army_no"
		);
		q1.setParameter("army_no", army_no.toUpperCase());
	
		 list = (List<String>) q1.list();
	
		tx.commit();
		
	} catch (RuntimeException e) {
		return null;
	} finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}
		return list;
}



   public String update_JcoOr_status(int jco_id,String username){

       

       String msg = "";

       Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

       Transaction tx = sessionHQL.beginTransaction();

       //int id = Integer.parseInt(request.getParameter("p_id"));
       //String username = session.getAttribute("username").toString();

      

       try {

               String hqlUpdate = "update TB_CENSUS_JCO_OR_PARENT set update_jco_status=:update_jco_status,modified_by=:modified_by,modified_date=:modified_date where id=:id";

              

               int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_jco_status", 0)
                               .setString("modified_by", username)
                               .setTimestamp("modified_date", new Date())
                               .setInteger("id", jco_id)
                               .executeUpdate();

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
	public List<String> getClass_payList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,class_pay from TB_PSG_MSTR_CLASS_PAY_JCO where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	public List<String> getPaygroupList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,pay_group from TB_PSG_MSTR_PAY_GROUP_JCO where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	public List<String> getCategory_jCOList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='CATEGORY_JCO'  order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}	
	public List<String> getClass_enrollList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,class_e from TB_PSG_MSTR_CLASS_JCO   where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}	
	public List<String> getRankjcoList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,rank from TB_PSG_MSTR_RANK_JCO where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}	
	@RequestMapping(value = "/getCategoryrankList", method = RequestMethod.POST)

	public @ResponseBody List<String> getCategoryrankList(String category) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery("select id,rank from TB_PSG_MSTR_RANK_JCO where category=:category and status='active'");
		
		q.setParameter("category", category);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	public List<String> getTradeJcoList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select id,trade from TB_PSG_MSTR_TRADE_JCO where status = 'active' order by id");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}	
	

public List<String> getTypeofAppointementListJCO() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select distinct id,appointment from TB_PSG_MSTR_APPOINTMENT_JCO where  status = 'active' order by id ");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	
	@RequestMapping(value = "/getExamList_jco", method = RequestMethod.POST)
    public @ResponseBody List<String> getExamList_jco() {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select id,promo_exam from TB_MSTR_PROMOTIONAL_EXAM_JCO where status='active' order by id");
            @SuppressWarnings("unchecked")
            List<String> list = (List<String>) q.list();
            tx.commit();
            session.close();
            return list;
    }
	
	@SuppressWarnings("unchecked")
    public String update_miso_role_hdr_status_jco(int jco_id,int status,String username,String col_name){

    String msg = "";

    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sessionHQL.beginTransaction();
    Date date = new Date();

     try {
                                             
              Query q0 = sessionHQL.createQuery("select  count(jco_id) from TB_PSG_MISO_ROLE_HDR_STATUS_JCO where jco_id =:jco_id ");
                             
              q0.setParameter("jco_id", jco_id);  
              Long c = (Long) q0.uniqueResult();
              
              
              
              if(col_name == "field_ser_data")
               {
                   Query q = sessionHQL.createQuery("select jco_id from TB_FIELD_SERVICE_CH_JCO where p_id=:p_id");
                
                         q.setParameter("p_id", jco_id); 
                         List<String> list = (List<String>) q.list(); 
                      
                            int new_comm_fd =0;
                            int j=0;
                        for (int i = 0; i < list.size(); i++) {
                                
                                        int count =0;
                                        new_comm_fd = Integer.parseInt(String.valueOf(list.get(i))) ;
                                                    //count = Integer.parseInt() ;
                                            
                                                    update_miso_role_hdr_status_jco(new_comm_fd,status,username,"field_ser_status");
                                                    j++;
                                                    if(j == list.size())
                                                    {
                                                            return msg;
                                                    }
                        }
               }
              
              if(c == 0) {
                        
               TB_PSG_MISO_ROLE_HDR_STATUS_JCO role_new = new  TB_PSG_MISO_ROLE_HDR_STATUS_JCO();                
               
               if(col_name.equals("re_call_status"))
               {
                        role_new.setRe_call_status(status);
                        role_new.setMarital_dis_status(1);
                        role_new.setField_ser_status(1);
               }
               if(col_name == "marital_dis_status")
               {
                        role_new.setMarital_dis_status(status);
                        role_new.setRe_call_status(1);
                        role_new.setField_ser_status(1);
               }
               if(col_name == "field_ser_status")
               {
                        role_new.setField_ser_status(status);
                        role_new.setRe_call_status(1);
                        role_new.setMarital_dis_status(1);
               }
               
               if(jco_id > 0 )
               {
                   role_new.setJco_id(jco_id);
                   role_new.setCreated_by(username);
                   role_new.setCreated_date(date);
                    sessionHQL.save(role_new);
               }
                    sessionHQL.flush();
                    sessionHQL.clear();
                           
               }
               else 
               {
                       List<TB_RE_CALL> re_emp=null;
                       if(col_name.equals("re_call_status")) {
                               Query qre=sessionHQL.createQuery("from TB_RE_CALL where jco_id=:jco_id order by id desc");
                               qre.setMaxResults(1).setInteger("jco_id",jco_id);
                               re_emp=(List<TB_RE_CALL>) qre.list();
                               
                       }
                String hqlUpdate = "update TB_PSG_MISO_ROLE_HDR_STATUS_JCO set "+col_name+"=:status,modified_by=:modified_by,modified_date=:modified_date where jco_id=:jco_id";
               
                Query qre = sessionHQL.createQuery(hqlUpdate).setInteger("status", status)
                      .setString("modified_by", username)
                     
                      .setInteger("jco_id", jco_id);
                      if(col_name.equals("re_call_status") && re_emp!=null && re_emp.size()>0) {
                             
                              qre.setTimestamp("modified_date", re_emp.get(0).getModified_date());
                                 }
                      else
                              qre.setTimestamp("modified_date", new Date());
                    int  app =  qre.executeUpdate();
                
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
	
	@RequestMapping(value = "/CheckArmyNoData", method = RequestMethod.POST)
	public @ResponseBody List<String> CheckArmyNoData(String army_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select c.id,c.status FROM TB_CENSUS_JCO_OR_PARENT c where upper(army_no) = :army_no  order by army_no");
			q1.setParameter("army_no", army_no.toUpperCase());
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
	
	public List<String> getRank_intakeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='RECT_INTAKE'  order by codevalue");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}	
	
	public @ResponseBody List<String> getTradeList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("select id,trade from TB_PSG_MSTR_TRADE_JCO where status='active' order by id");
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) q.list();
        tx.commit();
        session.close();
        return list;
	}


	public @ResponseBody List<String> getClassPayList() {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    Query q = session.createQuery("select id,class_pay from TB_PSG_MSTR_CLASS_PAY_JCO where status='active' order by id");
	    @SuppressWarnings("unchecked")
	    List<String> list = (List<String>) q.list();
	    tx.commit();
	    session.close();
	    return list;
	}
	
	
	public @ResponseBody List<String> getPayGroupList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("select id,pay_group from TB_PSG_MSTR_PAY_GROUP_JCO where status='active' order by id");
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) q.list();
        tx.commit();
        session.close();
        return list;
}
	
	@RequestMapping(value = "/getCategoryFromrankJCO", method = RequestMethod.POST)

	public @ResponseBody List<String> getCategoryFromrankJCO(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery("select category from TB_PSG_MSTR_RANK_JCO where id=:id and status='active'");
		
		q.setParameter("id", id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}
	
	 @RequestMapping(value = "/getarmy_noListRe_Call", method = RequestMethod.POST)
	    public @ResponseBody List<String> getarmy_noListRe_Call(String army_no, HttpSession sessionUserId) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	    //try{
	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	            String roleType = sessionUserId.getAttribute("roleType").toString();
	            Query q= null;
	           
	             q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p,\r\n" + 
	            	   		"TB_NON_EFFECTIVE_JCO ne " + 
	            	   		"where  p.status='4'  and  p.id=ne.jco_id " +
	            	   		"and upper(p.army_no)  like :army_no and ne.status='1' order by p.army_no");
	             q.setParameter("army_no", army_no.toUpperCase()+"%");
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
	 @RequestMapping(value = "/getpersonnel_noList_Certify_BCPC", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_noList_Certify_BCPC(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	            Query q= null;
	         
	          q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p, TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO n\r\n" + 
	          		"where p.id = n.jco_id and n.status = 1 and (p.status= 1 or p.status = 5) and \r\n" + 
	          		"upper(p.army_no) like :army_no order by p.army_no");
	            
	            q.setParameter("army_no", personel_no.toUpperCase()+"%");
	            
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
	 
	 @RequestMapping(value = "/getJcoOr_Non_EffectiveList", method = RequestMethod.POST)
     public @ResponseBody List<String> getJcoOr_Non_EffectiveList(String serving_status) {
    	 int type_of_officer=1;
    	 if(serving_status!=null && !serving_status.equals("")  &&   serving_status.equals("RE-CALL FROM RESERVE") )
    		 type_of_officer=2;
    	 
         Session session1 = HibernateUtil.getSessionFactory().openSession();
         Transaction tx1 = session1.beginTransaction();

         Query q1 = session1.createQuery("select id,causes_name from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_JCO where  status='active' order by id");
         //q1.setParameter("type_of_officer", type_of_officer);
         @SuppressWarnings("unchecked")
         List<String> list = (List<String>) q1.list();
         tx1.commit();
         session1.close();
         return list;
	 }
	 
	 public String update_JcoOr_Census_status(int jco_id,String username){

	       

	       String msg = "";

	       Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	       Transaction tx = sessionHQL.beginTransaction();


	       try {

	               String hqlUpdate = "update TB_CENSUS_JCO_OR_PARENT set update_census_status=:update_census_status,modified_by=:modified_by,modified_date=:modified_date where id=:id";

	             

	               int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_census_status", 0)
	                               .setString("modified_by", username)
	                               .setTimestamp("modified_date", new Date())
	                               .setInteger("id", jco_id)
	                               .executeUpdate();

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
	
	 @RequestMapping(value = "/getArmy_noListApproved", method = RequestMethod.POST)
		public @ResponseBody List<String> getArmy_noListApproved(String army_no, HttpSession sessionUserId) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
		//try{
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			String roleType = sessionUserId.getAttribute("roleType").toString();
			Query q= null;
			
			if(roleAccess.equals("Unit")){
				
				 q = sessionHQL.createQuery("select distinct army_no from TB_CENSUS_JCO_OR_PARENT  where unit_sus_no=:roleSusNo and upper(army_no)  like :army_no  order by army_no").setMaxResults(10);
				  q.setParameter("roleSusNo", roleSusNo);    
			}
			else
			{
				 
				 q = sessionHQL.createQuery("select distinct army_no from TB_CENSUS_JCO_OR_PARENT  where upper(army_no)  like :army_no  order by army_no").setMaxResults(10);
							
			}
			q.setParameter("army_no", army_no.toUpperCase()+"%");
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

		public List<String> getclass_domicileList() {

			Session session1 = HibernateUtil.getSessionFactory().openSession();

			Transaction tx1 = session1.beginTransaction();

			Query q1 = session1.createQuery("select id,domisile from TB_PSG_MSTR_GORKHA_DOMISILE_JCO where status = 'active' order by id");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx1.commit();

			session1.close();

			return list;

		}	

		public @ResponseBody List<String> gettype_of_postingList() {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        Query q = session.createQuery("select id,type_of_post from TB_Type_Of_Post where status='active' order by id");
	        @SuppressWarnings("unchecked")
	        List<String> list = (List<String>) q.list();
	        tx.commit();
	        session.close();
	        return list;
		}
		
		
		@RequestMapping(value = "/getpersonnel_no_JcoList_BA_PY_CA", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_no_JcoList_BA_PY_CA(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	    //try{
	            
	            Query q= null;
	           
	         
	          q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p,TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO n where p.id=n.jco_id "
	          		+ "and n.status=1 and (p.status='1' or p.status='5') and p.army_no  in (select p.army_no from TB_CENSUS_JCO_OR_PARENT c where c.id=p.id "
	          		+ "and c.status ='1' and (c.update_jco_status='0' or c.update_jco_status='1')) and upper(p.army_no)  like :army_no  order by p.army_no");
	                                            
	           
	            
	            q.setParameter("army_no", personel_no.toUpperCase()+"%");
	            
	           
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
		
		public List<String> getRankjcoListfordashboard(String sus_no, String type,String formationtype,String formCodeControl) {

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();	
			Query q1;
        if (formationtype.equals("Brigade")) {
         q1 = session1.createQuery("SELECT distinct r.id, r.rank,c.unit_sus_no FROM TB_CENSUS_JCO_OR_PARENT AS c,TB_PSG_MSTR_RANK_JCO AS r, Miso_Orbat_Unt_Dtl AS o\r\n"
         		+ "WHERE r.id =c.rank \r\n"
         		+ "and c.status=:status  \r\n"
         		+ "and c.category=:category \r\n"
         		+ "and c.unit_sus_no=:sus_no \r\n"
         		+ "and o.form_code_control=:form_code_control").setMaxResults(10);
			q1.setParameter("sus_no", sus_no);
			q1.setParameter("form_code_control", formCodeControl);
			q1.setParameter("status",1);
			if(type.equals("OR")) {
				  q1.setParameter("category","OR");
			  }
			  if(type.equals("JCO")) {
				  q1.setParameter("category","JCO");
			  }
        }else {
       q1 = session1.createQuery("SELECT distinct r.id, r.rank,c.unit_sus_no FROM TB_CENSUS_JCO_OR_PARENT AS c,TB_PSG_MSTR_RANK_JCO AS r, Miso_Orbat_Unt_Dtl AS o WHERE r.id =c.rank and c.unit_sus_no=:sus_no and c.status =:status  and  c.category=:category and o.form_code_control=:form_code_control").setMaxResults(10);
			q1.setParameter("sus_no", sus_no);
			q1.setParameter("form_code_control", formCodeControl);
			q1.setParameter("status",1);
			if(type.equals("OR")) {
				  q1.setParameter("category","OR");
			  }
			  if(type.equals("JCO")) {
				  q1.setParameter("category","JCO");
			  }
        }
			
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();			
		tx1.commit();

		session1.close();
		return list;
		}				
		
		

@RequestMapping(value = "/getJcoTradelist", method = RequestMethod.POST)
        public @ResponseBody List<String> getJcoTradelist(String trade,String type1, HttpSession sessionUserId) {
	        String formationtype = sessionUserId.getAttribute("roleSubAccess").toString();
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session1.beginTransaction();    
            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
            Query q1 = session1.createQuery("SELECT distinct p.trade FROM TB_CENSUS_JCO_OR_PARENT AS c,TB_PSG_MSTR_TRADE_JCO AS p,TB_PSG_MSTR_RANK_JCO AS r WHERE p.id = c.trade and c.unit_sus_no=:sus_no and c.status =:status and r.id =c.rank  and c.category=:category and upper(p.trade)   like :trade ").setMaxResults(10);
            q1.setParameter("sus_no", roleSusNo);         
            q1.setParameter("status",1);
            q1.setParameter("trade", trade.toUpperCase()+"%");
            if(type1.equals("OR")) {
				  q1.setParameter("category","OR");
			  }
			  if(type1.equals("JCO")) {
				  q1.setParameter("category","JCO");
			  }
            @SuppressWarnings("unchecked")
            List<String> list = (List<String>) q1.list();            
            tx1.commit();        
        

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
	
		 @RequestMapping(value = "/getArmy_noListApproved_forunit", method = RequestMethod.POST)
			public @ResponseBody List<String> getArmy_noListApproved_forunit(String army_no,String type1, HttpSession sessionUserId) {

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
			//try{
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
				String roleType = sessionUserId.getAttribute("roleType").toString();
				Query q= null;
				System.out.println("type1:- " + type1);
				if(roleAccess.equals("Unit")){
					
					 q = sessionHQL.createQuery("select distinct c.army_no from TB_CENSUS_JCO_OR_PARENT c,TB_PSG_MSTR_RANK_JCO AS r  where  r.id =c.rank and c.unit_sus_no=:roleSusNo and upper(army_no)  like :army_no and c.status=:status and c.category=:category order by c.army_no").setMaxResults(10);
					  q.setParameter("roleSusNo", roleSusNo); 
					  q.setParameter("status",1);
					  if(type1.equals("OR")) {
						  q.setParameter("category","OR");
					  }
					  if(type1.equals("JCO")) {
						  q.setParameter("category","JCO");
					  }
				}
				else
				{
					 
					 q = sessionHQL.createQuery("select distinct army_no from TB_CENSUS_JCO_OR_PARENT  where upper(army_no)  like :army_no  order by army_no").setMaxResults(10);
								
				}
				q.setParameter("army_no", army_no.toUpperCase()+"%");
				System.out.println("qqq: - " + q);
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
		 
		 //psg p1 changes v2 
		 @RequestMapping(value = "/getPersonnelNoListJco", method = RequestMethod.POST)
		 public @ResponseBody List<String> getPersonnelNoListJco(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

		         Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		         Transaction tx = sessionHQL.beginTransaction();	        //try{ 

		         Query q= null;  
		         
		  
		                  
		         	  q = sessionHQL.createQuery("select distinct p.army_no from TB_CENSUS_JCO_OR_PARENT p where   (p.status=1 or p.status=5)  "+
		                       " and (p.update_jco_status=0 or p.update_jco_status=1) and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
		                                     
		         
		        
		         q.setParameter("army_no", personel_no.toUpperCase()+"%");
		         
		      
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
}			  
			  		
		
		
		

