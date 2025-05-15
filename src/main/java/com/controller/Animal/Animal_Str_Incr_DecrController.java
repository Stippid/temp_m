package com.controller.Animal;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.Animal.Animal_Str_incr_decrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

import com.model.Animal.TB_ANIMAL_STR_INCR_DECR;

import com.models.psg.Transaction.TB_POSTING_IN_OUT;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Animal_Str_Incr_DecrController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Animal_Str_incr_decrDAO anmlDao;
	
	Psg_CommonController commst = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@RequestMapping(value = "/admin/anm_str_incr_decrUrl", method = RequestMethod.GET)
	public ModelAndView anm_str_incr_decrUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("anm_str_incr_decrUrl", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("getPostINOutstatusList", getIncrDecrstatusList());
		Mmap.put("roleSusNo", roleSusNo);
		return new ModelAndView("Str_Incr_DecrTiles");
	}
	
	
	public List<String> getIncrDecrstatusList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1
				.createQuery("select codevalue,label from T_Domain_Value where domainid='ANIMALSTRINCRDECR'  order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	//post out (Decr) autocomplete Animal Army Number
	
	@RequestMapping(value = "/getarmy_noListApproved_Animal_Decr", method = RequestMethod.POST)
	public @ResponseBody List<String> getarmy_noListApproved_Animal(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

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
	                
	                 q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where unit_sus_no=:roleSusNo and animal_type='Army Dog' and p.status=1  "+
	                              " and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
	                 
	                 q.setParameter("roleSusNo", roleSusNo);  

	        }
	        else
	        {
	                 
	        	  q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where  p.status=1 and animal_type='Army Dog' "+
	                      " and upper(p.army_no)  like :army_no order by p.army_no").setMaxResults(10);
	                                    
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
	

	
	
	@RequestMapping(value = "/GetArmyNoDataAnimal", method = RequestMethod.POST)
	public @ResponseBody List<Object[]> GetArmyNoDataAnimal(String army_no) {
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	    List<Object[]> list = null;  

	    try {
	        Query q1 = sessionHQL.createQuery(
	            "SELECT c.id, c.gender, c.date_of_birth, c.name, c.status, " +
	            " c.microchip_no, c.date_of_tos, c.unit_sus_no" +
	            " FROM TB_ANIMAL_CENSUS_DTLS c " +
	            " WHERE upper(c.army_no) = :army_no " +
	            " AND c.status = '1' ORDER BY c.army_no"
	        );
	        q1.setParameter("army_no", army_no.toUpperCase());
	        System.err.println("Query GetArmyNoDataAnimal -->" + q1);
	        
	        list = q1.list();  // No need to cast it to List<String>

	        tx.commit();
	    } catch (RuntimeException e) {
	        e.printStackTrace();
	        return new ArrayList<>();  // Return an empty list instead of null
	    } finally {
	        if (sessionHQL != null) {
	            sessionHQL.close();
	        }
	    }
	    return list;
	}
	
	
	@RequestMapping(value = "/GetArmyNoDataAnimalApproved", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> GetCommDataApprove(int census_id) {
   Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sessionHQL.beginTransaction();
      ArrayList<ArrayList<String>> list = anmlDao.GetCommDataApprove(census_id);
         tx.commit();
       return list;
	}
	
	

	
	//method to get tenure date and tos and sos for 
	
	@RequestMapping (value = "/admin/GETPostTenure_Date_Animal", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GETPostTenure_Date_Animal(ModelMap Mmap, HttpSession session,
			 int census_id_animal,HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = anmlDao.getPostTenureDateAnimal(census_id_animal);
		return list;
	 }
	
	//incr army dog no autocomplte
	
	@RequestMapping(value = "/getPersonnelNoListAnimal_Incr", method = RequestMethod.POST)
	 public @ResponseBody List<String> getPersonnelNoListAnimal_Incr(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

	         Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	         Transaction tx = sessionHQL.beginTransaction();
	         
	         String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		        String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		        String roleType = sessionUserId.getAttribute("roleType").toString();
		        Query q= null;
		        String rsus=request.getParameter("roleSus");
		       if( rsus!=null && !rsus.equals("")){
		        	roleSusNo=rsus;
		       }
		       	
		       
		       if(roleSusNo!=null && !roleSusNo.equals("")){
	                
	                 q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where unit_sus_no=:roleSusNo and animal_type='Army Dog' and p.status=1  "+
	                              " and upper(p.army_no)  like :army_no  order by p.army_no").setMaxResults(10);
	                 
	                 q.setParameter("roleSusNo", roleSusNo);  

	        }
	        else
	        {
	                 
	        	  q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where  p.status=1 and animal_type='Army Dog' "+
	                      " and upper(p.army_no)  like :army_no order by p.army_no").setMaxResults(10);
	                                    
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
	
	//incr army dog no data
	
	@RequestMapping(value = "/CheckArmyNoDataAnimal", method = RequestMethod.POST)
	public @ResponseBody List<String> CheckArmyNoDataAnimal(String army_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select c.id,c.status FROM TB_ANIMAL_CENSUS_DTLS c where upper(c.army_no) = :army_no  order by army_no");
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
	
	@RequestMapping(value = "/admin/Animal_Str_Incr_DecrAction", method = RequestMethod.POST)
	public @ResponseBody String Animal_Str_Incr_DecrAction(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request) throws ParseException {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		if (roleSusNo == null || roleSusNo.equals("")) {
			roleSusNo = request.getParameter("from_sus_no");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		Date auth = null;
		Date sos = null;
		String username = session.getAttribute("username").toString();
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		
		TB_ANIMAL_STR_INCR_DECR POJ = new TB_ANIMAL_STR_INCR_DECR();
		String to_sus_no = request.getParameter("to_sus_no");
		String out_auth = request.getParameter("out_auth");
		String out_auth_dt = request.getParameter("out_auth_dt");
		String personnel_no = request.getParameter("personnel_no");
		String out_auth_dt1 = request.getParameter("out_auth_dt");
		String dt_of_sos1 = request.getParameter("dt_of_sos");
		String from_sus_no = request.getParameter("from_sus_no");
		String scenario = request.getParameter("scenario");
		int h_id = Integer.parseInt(request.getParameter("h_id"));
	
		System.err.println("val of h id " + h_id);
		System.err.println("val of to_sus_no: " + to_sus_no);
		System.err.println("val of out_auth: " + out_auth);
		System.err.println("val of out_auth_dt: " + out_auth_dt);
		System.err.println("val of personnel_no: " + personnel_no);
		System.err.println("val of out_auth_dt1: " + out_auth_dt1);
		System.err.println("val of dt_of_sos1: " + dt_of_sos1);
		System.err.println("val of from_sus_no: " + from_sus_no);
		System.err.println("val of scenario: " + scenario);
		System.err.println("val of h_id: " + h_id);
		
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Army  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ";
		}
		if (personnel_no.length() < 2 || personnel_no.length() > 12) {
			return "Army No Should Contain Maximum 12 Character";
		}
		if (out_auth.equals("") || out_auth == "" || out_auth == null) {
			return "Please Enter Auth No.";
		}		
		if (!valid.isValidAuth(out_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(out_auth,valid.authorityMax,valid.authorityMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}				
		if (out_auth_dt1 == null || out_auth_dt1.equals("null") || out_auth_dt1.equals("DD/MM/YYYY") || out_auth_dt1.equals("")) {
			return "Please Select Auth Date";
		}
		else if (!valid.isValidDate(out_auth_dt1)) {
			return valid.isValidDateMSG + " of Auth";
		}
		else {
			auth = format.parse(out_auth_dt1);
		}		
		if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
			return "Please Enter Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(to_sus_no)) {
		 	return valid.isOnlyAlphabetNumericMSG + " Unit Sus No";	
		}
		if (to_sus_no != "") {
			if (!valid.SusNoLength(to_sus_no)) {
				return valid.SusNoMSG;
			}
		}				
		if (dt_of_sos1 == null || dt_of_sos1.equals("null") || dt_of_sos1.equals("DD/MM/YYYY") || dt_of_sos1.equals("")) {
			return "Please Select SOS Date";
		} 
		else if (!valid.isValidDate(dt_of_sos1)) {
			return valid.isValidDateMSG + " of SOS";
		}	
		else {
			sos = format.parse(dt_of_sos1);
		}	
		if (dt_of_sos1 != null) {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 Date currentDate = new Date();
			 Date selectedDate = dateFormat.parse(dt_of_sos1);
		        if (selectedDate.after(currentDate)) {
		            return "Future dates are not allowed";
		    }		   
		}
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		String rvalue = "";
		int id1 = h_id > 0 ? POJ.getId() : 0;
	try {
		Boolean v = anmlDao.getAnimalpernoAlreadyExits(census_id);
		if (v == true)
		{
			rvalue = "Data already exists.";
			return rvalue;
		}
		if (v == false) {
			if (id1 == 0) {
				String roleAccess = session.getAttribute("roleAccess").toString();
				POJ.setTo_sus_no(to_sus_no);
				POJ.setAuth_no(out_auth);
				POJ.setAuth_dt(format.parse(out_auth_dt));
				POJ.setDt_of_sos(sos);
				POJ.setDt_of_tos(sos);
				POJ.setCreated_by(username);
				POJ.setCreated_date(new Date());
				POJ.setCensus_id(census_id);
				POJ.setFrom_sus_no(from_sus_no);
				POJ.setTo_sus_no(to_sus_no);
				POJ.setScenario(scenario);
				POJ.setStatus(0);
				sessionHQL.save(POJ);
				int id12 = (int) sessionHQL.save(POJ);
				tx.commit();
				rvalue = String.valueOf(POJ.getId());
				}
			}		
		} catch (RuntimeException e) {
		 try {
			 tx.rollback();
			 rvalue = "0";
		 } catch (RuntimeException rbe) {
			 rvalue = "0";
		 	}
		 } finally {
			 if (sessionHQL != null) {
				 sessionHQL.close();
			 }
		 }
		return rvalue;
	}

	
	@RequestMapping(value = "/getPersonnelNoListAnimal_Census", method = RequestMethod.POST)
	    public @ResponseBody List<String> getPersonnelNoListAnimal_Census(String personel_no,HttpSession sessionUserId,HttpServletRequest request) {
				System.err.println("INSIDE getPersonnelNoListAnimal_Census -- ");
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
	                    
	            	 q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where unit_sus_no=:roleSusNo and animal_type='Army Dog' and p.status=1  "+
                             " and upper(p.army_no)  like :personnel_no order by p.army_no").setMaxResults(10);
                
                q.setParameter("roleSusNo", roleSusNo);    

	            }
	            else
	            {
	                     
	            	q = sessionHQL.createQuery("select distinct p.army_no from TB_ANIMAL_CENSUS_DTLS p where p.status=1 "+
		                       "and upper(p.army_no)  like :personnel_no order by p.army_no").setMaxResults(10);
	                                            
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
	
	
	//Start search aprrove incr decr animal 
	
	@RequestMapping(value = "/admin/Search_Incr_DecrUrl")//, method = RequestMethod.GET
	public ModelAndView Search_Incr_DecrUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Incr_DecrUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleAccess", roleAccess);
		
		if (m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		
		Mmap.put("msg", msg);
		
		
		Mmap.put("getPostINOutstatusList", getIncrDecrstatusList());
		
		return new ModelAndView("Search_Str_Incr_DecrTiles");
	}
	
	
	@RequestMapping(value = "/GetSearch_Incr_Decr_count", method = RequestMethod.POST)
	public @ResponseBody long GetSearch_Comd_and_cont_count(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String type,String army_no,String from_sus_no,
			String to_sus_no, String status)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return anmlDao.GetSearch_Incr_Decr_count(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, type, army_no,from_sus_no,to_sus_no,status);
	}

	@RequestMapping(value = "/GetSearch_Incr_Decr_List", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetSearch_Com_letterdata(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String type,String army_no,String from_sus_no,
			String to_sus_no, String status) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return anmlDao.GetSearch_Incr_Decr_List(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId,type, army_no,from_sus_no,to_sus_no,status);
	}
	
	
	
	
	
	@RequestMapping(value = "/approveAnimalIncrDecr", method = RequestMethod.POST)
	@ResponseBody
	public String approveAnimalIncrDecr(HttpServletRequest request) {
	    String id = request.getParameter("id");
	    String census_id = request.getParameter("census_id");
	    String to_sus_no = request.getParameter("to_sus_no");

	    System.err.println("Census_id: " + census_id + ", ID: " + id + ", To_Sus_No: " + to_sus_no);

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    try {
	        String hqlCheck = "UPDATE TB_ANIMAL_STR_INCR_DECR SET status = 2 WHERE census_id = :census_id AND status = 1";
	        int checkUpdatedRows = session.createQuery(hqlCheck)
	                                      .setParameter("census_id", Integer.parseInt(census_id))
	                                      .executeUpdate();

	        if (checkUpdatedRows == 0) {
	            tx.rollback();
	            return "No records found with Approved. Approval not allowed.";
	        }

	        String hql1 = "UPDATE TB_ANIMAL_STR_INCR_DECR SET status = 1 WHERE id = :id AND census_id = :census_id";
	        int updatedRows1 = session.createQuery(hql1)
	                                  .setParameter("id", Integer.parseInt(id))
	                                  .setParameter("census_id", Integer.parseInt(census_id))
	                                  .executeUpdate();

	        String hql2 = "UPDATE TB_ANIMAL_CENSUS_DTLS SET unit_sus_no = :to_sus_no WHERE id = :census_id";
	        int updatedRows2 = session.createQuery(hql2)
	                                  .setParameter("to_sus_no", to_sus_no)
	                                  .setParameter("census_id", Integer.parseInt(census_id))
	                                  .executeUpdate();

	        if (updatedRows1 > 0 && updatedRows2 > 0) {
	            tx.commit();
	            return "Animal Approved Successfully!";
	        } else {
	            tx.rollback();
	            return "Approval Failed! Record may not exist.";
	        }

	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        return "Error while approving!";
	    } finally {
	        session.close();
	    }
	}



	    
	 
	 @RequestMapping(value = "/rejectAnimalIncrDecr", method = RequestMethod.POST)
	 @ResponseBody
	 public String rejectAnimalIncrDecr(@RequestParam("id") int id, 
	                            @RequestParam("census_id") int censusId, 
	                            @RequestParam("remarks") String remarks,
	                            HttpServletRequest request) {
	     Session session = null;
	     Transaction tx = null;
	     
	     try {
	         session = HibernateUtil.getSessionFactory().openSession();
	         tx = session.beginTransaction();
	         
	         HttpSession httpSession = request.getSession();
	         String username = (String) httpSession.getAttribute("username");

	        
	         TB_ANIMAL_STR_INCR_DECR animal = (TB_ANIMAL_STR_INCR_DECR) session.get(TB_ANIMAL_STR_INCR_DECR.class, id);
	         
	         if (animal != null) {
	             animal.setStatus(3); 
	             animal.setReject_remarks(remarks);
	             animal.setRejected_by(username);
	             animal.setRejected_date(new Date());
	             session.update(animal);
	             tx.commit();
	             return "Record Rejected Successfully!";
	         } else {
	             return "Record Not Found!";
	         }

	     } catch (Exception e) {
	         if (tx != null) tx.rollback();
	         e.printStackTrace();
	         return "Error while rejecting the record!";
	     } finally {
	         if (session != null) session.close();
	     }
	 }


	    @RequestMapping(value = "/deleteAnimalIncrDecr", method = RequestMethod.POST)
	    @ResponseBody
	    public String deleteAnimalIncrDecr(HttpServletRequest request) {
	        String id = request.getParameter("id");
	        String census_id = request.getParameter("census_id");

	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();

	        try {
	            String hql = "DELETE FROM TB_ANIMAL_STR_INCR_DECR WHERE id = :id AND census_id = :census_id";
	            int deletedRows = session.createQuery(hql)
	                                     .setParameter("id", Integer.parseInt(id))
	                                     .setParameter("census_id", Integer.parseInt(census_id))
	                                     .executeUpdate();
	            tx.commit();
	            return (deletedRows > 0) ? "Animal Deleted Successfully!" : "Deletion Failed! Record may not exist.";
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	            return "Error while deleting!";
	        } finally {
	            session.close();
	        }
	    }
	
	   
	   
	    
	    
	    @RequestMapping(value = "/Edit_Anm_Decr_Url", method = RequestMethod.POST)
		public ModelAndView Edit_Post_Out_Url( ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				@RequestParam(value = "edit_id1", required = false) String id,
				@RequestParam(value = "edit_census_id1", required = false) String census_id,
				@RequestParam(value = "edit_scenario1", required = false) String scenario,
				HttpSession sessionEdit, HttpServletRequest request) {
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Incr_DecrUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		    List<Map<String, Object>> list = anmlDao.getAnml_Str_DecrByid(Integer.parseInt(id));
			model.put("list", list);
			model.put("size", list.size());
			model.put("updateid", id);
			model.put("msg", msg);
			return new ModelAndView("Edit_Anm_Decr_Tiles");
		}
	    
	    //update Decr Animal
	    
	    @RequestMapping(value = "/admin/Edit_Anm_Decr_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Anm_Decr_Action( @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, ModelMap model, HttpSession session)
				throws ParseException {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Incr_DecrUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			try {
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = session.getAttribute("username").toString();			
				String to_sus_no = request.getParameter("to_sus_no");
				String out_auth = request.getParameter("out_auth");
				
				Date out_auth_dt1 = null;
				String out_auth_dt = request.getParameter("out_auth_dt");
				Date dt_of_sos1 = null;
				String dt_of_sos = request.getParameter("dt_of_sos");
				String dt_tos_pre = request.getParameter("dt_tos_pre");

				TB_POSTING_IN_OUT tb = new TB_POSTING_IN_OUT();
				
				
				if(out_auth==null || out_auth.trim().equals("") ) {
					model.put("msg", "Please Enter Auth");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}			
				if (!valid.isValidAuth(out_auth)) {
					model.put("msg", "Please Valid Auth");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
					model.put("msg", "Please Enter Auth");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}								
				if(out_auth_dt==null || out_auth_dt.trim().equals("") || out_auth_dt.trim().equals("DD/MM/YYYY")) {
					model.put("msg", "Please Enter Date of Auth");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				else if (!valid.isValidDate(out_auth_dt)) {
					model.put("msg", "Please Enter Date of Auth");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				else {
					out_auth_dt1 = format.parse(out_auth_dt);
				}			
				if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
					model.put("msg", "Please Enter To Unit SUS No");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}		
				if (to_sus_no.equals(roleSusNo))
				{
					model.put("msg", "Please check To SUS NO,it can't be same as FROM SUS NO");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}			
				if (to_sus_no != "") {
					if (!valid.SusNoLength(to_sus_no)) {
						model.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_Incr_DecrUrl");
					}
				}
				if (!valid.isOnlyAlphabetNumericSpaceNot(to_sus_no)) {
					model.put("msg",valid.isOnlyAlphabetNumericSpaceNotMSG + " To Sus No ");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");				
				}
				if(dt_of_sos==null || dt_of_sos.trim().equals("") || dt_of_sos.trim().equals("DD/MM/YYYY")) {
					model.put("msg", "Please Enter Date of SOS");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				else if (!valid.isValidDate(dt_of_sos)) {
					model.put("msg", valid.isValidDateMSG + " of SOS");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				else {
					dt_of_sos1 = format.parse(dt_of_sos);
				}
				if (dt_of_sos1 != null) {				
					 Date currentDate = new Date();
					 Date selectedDate = dt_of_sos1;
				        if (selectedDate.after(currentDate)) {
				        	model.put("msg","Future dates are not allowed");
				        	return new ModelAndView("redirect:Search_Incr_DecrUrl");
				    }		   
				}
				
				if(dt_of_sos!=null && dt_tos_pre!=null) {
					String regex = "^0+(?!$)";
					String newSos=request.getParameter("dt_of_sos");
					String preSos=dt_tos_pre;
					String newSosArr[]=newSos.split("/");
					String preSosArr[]=preSos.split("/");
					int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
					int newSosY=Integer.parseInt(newSosArr[2]);
					int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
					int preSosY=Integer.parseInt(preSosArr[2]);
					if(newSosY==preSosY){
						if(newSosM<=preSosM){
							model.put("msg", "Invalid Date of SOS");
							return new ModelAndView("redirect:Search_Incr_DecrUrl");
						}
					}
					else if(newSosY<preSosY){
						model.put("msg", "Invalid Date of SOS");
						return new ModelAndView("redirect:Search_Incr_DecrUrl");
					}
				}
				if (to_sus_no.equals(roleSusNo))
				{
					model.put("msg", "Please check To SUS NO,it can't be same as FROM SUS NO");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				int hh_id = Integer.parseInt(request.getParameter("id"));
				String rvalue = "";
				String hql = "";
				hql += "update TB_ANIMAL_STR_INCR_DECR";
				hql += " set auth_no=:auth_no,auth_dt=:auth_dt,to_sus_no=:to_sus_no, dt_of_sos=:dt_of_sos,dt_of_tos=:dt_of_tos,status=0,"
						+ "modified_by=:modified_by,modified_date=:modified_date where id=:h_id";
				Query query = sessionHQL.createQuery(hql)
						.setParameter("auth_no", out_auth)
						.setParameter("auth_dt", out_auth_dt1)
						.setParameter("to_sus_no", to_sus_no)
						.setParameter("dt_of_sos", dt_of_sos1)
						.setParameter("dt_of_tos", dt_of_sos1)
						.setParameter("h_id", hh_id)
						.setParameter("modified_date", new Date())
						.setParameter("modified_by", username);
				rvalue = query.executeUpdate() > 0 ? "update" : "0";
				model.put("msg", "Data Updated Successfully");
				tx.commit();
				sessionHQL.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return new ModelAndView("redirect:Search_Incr_DecrUrl");
		}
		 
	    //edit animal str incr decr start 
	    
	    @RequestMapping(value = "/Edit_Anm_Incr_Url", method = RequestMethod.POST)
		public ModelAndView Edit_Anm_Incr_Url( ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				@RequestParam(value = "edit_id", required = false) String id,
				@RequestParam(value = "edit_census_id", required = false) String census_id,
				@RequestParam(value = "edit_scenario", required = false) String scenario,
				HttpSession sessionEdit, HttpServletRequest request) {
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Incr_DecrUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<Map<String, Object>> list = anmlDao.getAnml_Str_IncrByid(Integer.parseInt(id));
			model.put("list", list);
			model.put("size", list.size());
			model.put("updateid", id);
			model.put("msg", msg);
			return new ModelAndView("Edit_Anm_Incr_Tiles");
		}
	    
	    
	    //edit animal decr action 
	    @RequestMapping(value = "/admin/Edit_Anm_Incr_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Anm_Incr_Action(HttpServletRequest request, ModelMap model, HttpSession session)
				throws ParseException {
			try {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String from_sus_no = request.getParameter("from_sus_no");
				Date dt_of_tos1 = null;
				String dt_of_tos = request.getParameter("dt_of_tos");
				String dt_tos_pre = request.getParameter("dt_tos_pre");
				
				if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {
					model.put("msg", "Please Enter Date of TOS");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				if (!valid.isValidDate(dt_of_tos)) {
					model.put("msg", valid.isValidDateMSG + " of TOS");
					return new ModelAndView("redirect:Search_Incr_DecrUrl");
				}
				if (dt_of_tos != "") {
					dt_of_tos1 = format.parse(dt_of_tos);
				}
				if (dt_of_tos1 != null) {				
					 Date currentDate = new Date();
					 Date selectedDate = dt_of_tos1;
				        if (selectedDate.after(currentDate)) {
				        	model.put("msg","Future dates are not allowed");
				        	return new ModelAndView("redirect:Search_Incr_DecrUrl");
				    }		   
				}
				if(dt_of_tos!=null && dt_tos_pre!=null) {
					String regex = "^0+(?!$)";
					String newSos=request.getParameter("dt_of_tos");
					String preSos=dt_tos_pre;
					String newSosArr[]=newSos.split("/");
					String preSosArr[]=preSos.split("/");
					int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
					int newSosY=Integer.parseInt(newSosArr[2]);
					int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
					int preSosY=Integer.parseInt(preSosArr[2]);
					if(newSosY==preSosY){
						if(newSosM<=preSosM){
							model.put("msg", "Invalid Date of TOS");
							return new ModelAndView("redirect:Search_Incr_DecrUrl");
						}
					}
					else if(newSosY<preSosY){
						model.put("msg", "Invalid Date of TOS");
						return new ModelAndView("redirect:Search_Incr_DecrUrl");
					}
				}
				int hh_id = Integer.parseInt(request.getParameter("id"));
				String rvalue = "";
				String hql = "";
				int service_category = Integer.parseInt(request.getParameter("service_category"));
				if (service_category == 1) {
					hql += "update TB_ANIMAL_STR_INCR_DECR";
				}
				
				hql += "  set  dt_of_tos=:dt_of_tos,dt_of_sos=:dt_of_sos,status=0 where id=:h_id";
				Query query = sessionHQL.createQuery(hql)
						.setParameter("dt_of_tos", dt_of_tos1).setParameter("dt_of_sos", dt_of_tos1).setParameter("h_id", hh_id);
				rvalue = query.executeUpdate() > 0 ? "update" : "0";
				model.put("msg", "Data Updated Successfully");
				tx.commit();
				sessionHQL.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return new ModelAndView("redirect:Search_Incr_DecrUrl");
		}
}
