package com.controller.avation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;

import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.dao.avation.AVNDRRDAO;
import com.dao.avation.AVNDRRDAOImpl;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_AVIATION_DRR_DTL;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Avn_DailyReceiptReportController {
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	AVNDRRDAO avndao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	ValidationController validation = new ValidationController();
	NotificationController notification = new NotificationController();
	
	
	@RequestMapping(value = "/admin/avn_daily_receipt_report", method = RequestMethod.GET)
	public ModelAndView avn_daily_receipt_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("avn_daily_receipt_report", roleid);
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
		return new ModelAndView("avn_daily_receipt_reportTiles");
	}
	
	@RequestMapping(value = "/gettailNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> gettailNoList(HttpSession sessionA, String tail_no, String sus_no) {
	    Session session = null;
	    Transaction tx = null;
	    List<String> finalList = new ArrayList<>();
	    
	    try {
	        
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();

	       
	        String[] tableNames = {
	            "TB_AVIATION_TAILNO_DTL",
	            "TB_AVIATION_CHTL_TAILNO_DTL",
	            "TB_AVIATION_RPAS_TAILNO_DTL"
	        };

	        List<String> combinedList = new ArrayList<>();

	        
	        for (String tableName : tableNames) {
	            String queryStr = "select tail_no from " + tableName + 
	                              " where upper(tail_no) like :tail_no and upper(sus_no) like :sus_no order by tail_no asc";
	            Query query = session.createQuery(queryStr)
	                    .setMaxResults(10);
	            query.setParameter("tail_no", tail_no.toUpperCase() + "%");
	            query.setParameter("sus_no", sus_no.toUpperCase() + "%");

	            @SuppressWarnings("unchecked")
	            List<String> list = (List<String>) query.list();
	            combinedList.addAll(list);
	        }

	       
	        tx.commit();

	        
	        String enckey = hex_asciiDao.getAlphaNumericString();
	        Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);

	       
	        for (String tailNo : combinedList) {
	            try {
	                byte[] encCode = cipher.doFinal(tailNo.getBytes());
	                String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
	                finalList.add(base64EncodedEncryptedCode);
	            } catch (IllegalBlockSizeException | BadPaddingException e) {
	                e.printStackTrace();
	            }
	        }

	       
	        finalList.add(enckey + "4bsjyg==");

	    } catch (Exception e) {
	        if (tx != null) tx.rollback(); 
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	    
	    return finalList;
	}

	@RequestMapping(value = "/getAircraftDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getAircraftDetails(String tail_no, HttpSession sessionUserId) throws Exception {
	    int userId = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
	    ArrayList<List<String>> finalList = new ArrayList<>();
	    
	    String encKey = hex_asciiDao.getAlphaNumericString();
	    Cipher cipher = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, encKey);

	    Session session = null;
	    Transaction tx = null;

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();

	        
	        String[] queries = {
	            "select lh_eng_ser_no, rh_eng_ser_no, lh_eng_hrs, rh_eng_hrs, std_nomclature, eng_name from TB_AVIATION_TAILNO_DTL where tail_no= :tail_no",
	            "select eng_ser_no, eng_hrs, std_nomclature, eng_name from TB_AVIATION_CHTL_TAILNO_DTL where tail_no= :tail_no",
	            "select eng_ser_no, eng_hrs, std_nomclature, eng_name from TB_AVIATION_RPAS_TAILNO_DTL where tail_no= :tail_no"
	        };

	        List<Object[]> allResults = new ArrayList<>();
	        String sourceTable = ""; 

	    
	        for (String queryStr : queries) {
	            Query q = session.createQuery(queryStr);
	            q.setParameter("tail_no", tail_no);
	            List<Object[]> results = q.list();
	            if (!results.isEmpty()) {
	                allResults.addAll(results);
	                if (queryStr.contains("RPAS")) {
	                    sourceTable = "RPAS"; 
	                } else if (queryStr.contains("CHTL")) {
	                    sourceTable = "CHTL"; 
	                } else {
	                    sourceTable = "OTHER"; 
	                }
	               
	            }
	        }

	        tx.commit();

	       
	        for (Object[] record : allResults) {
	            List<String> encryptedData = new ArrayList<>();
	            int columnsToAdd = 6 - record.length;
	            for (int i = 0; i < columnsToAdd; i++) {
	                encryptedData.add(""); 
	            }

	         
	            for (Object value : record) {
	                String encryptedValue = encryptData(value.toString(), cipher);
	                encryptedData.add(encryptedValue);
	            }

	            
	            encryptedData.add(sourceTable);

	            finalList.add(encryptedData);
	        }

	       
	        if (!allResults.isEmpty()) {
	            List<String> encKeyList = new ArrayList<>();
	            encKeyList.add(encKey + "YbFjyB==");
	            encKeyList.add(encKey + "HNTrgS==");
	            finalList.add(encKeyList);
	        }

	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); 
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Error while processing aircraft details", e);
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }

	    return finalList; 
	}


	private String encryptData(String data, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
	    byte[] encryptedData = cipher.doFinal(data.getBytes());
	    return new String(Base64.encodeBase64(encryptedData));
	}

	 
	 @RequestMapping(value = "/getavnAgencyList", method = RequestMethod.POST)
		public @ResponseBody List<String> getavnAgencyList(HttpSession sessionA, String type_of_airtcraft) {
		System.err.println(type_of_airtcraft);
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select agency_name from TB_AVIATION_ROHAGENCY_MASTER where  std_nomclature= :std_nomclature").setMaxResults(10);
			q.setParameter("std_nomclature", type_of_airtcraft);
		
				
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
	 
	 @RequestMapping(value = "/saveAviationData", method = RequestMethod.POST)
	 public @ResponseBody String saveAviationData(@RequestParam String drr_ser_no,
	                                @RequestParam String tail_no,
	                                @RequestParam String authority,
	                                @RequestParam String remarks,
	                                @RequestParam String unit_sus_no,
	                                @RequestParam String agency_name,
	                                @RequestParam String std_nomen,
	                                ModelMap Mmap, HttpSession sessionA) {
	     Session session = null;
	     Transaction tx = null;
	     Date dt = new Date();

	    
	     String username = (String) sessionA.getAttribute("username");
	   
	     try {
	       
	         session = HibernateUtil.getSessionFactory().openSession();
	         
	         TB_AVIATION_DRR_DTL aviationData = new TB_AVIATION_DRR_DTL();
	         aviationData.setDrr_ser_no(drr_ser_no);
	         aviationData.setTail_no(tail_no);
	         aviationData.setAuthority(authority);
	         aviationData.setRemarks(remarks);
	         aviationData.setUnit_sus_no(unit_sus_no);
	         aviationData.setClassification("3");  
	         aviationData.setStatus("0");  
	         aviationData.setAgency_name(agency_name);
	         aviationData.setStd_nomen(std_nomen);
	         aviationData.setCreated_on(dt);
	         aviationData.setCreated_by(username);

	         
	            if (avndao.ifExistDRRNo(drr_ser_no)) {
	            	return  "DRR No Already Exists.";
	                
	            } else {
	         tx = session.beginTransaction();
	         session.save(aviationData);
	         session.flush(); 
	         tx.commit();  
	         return "Data Saved Successfully."; 
	            }
	     } catch (Exception e) {
	         
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace(); 
	         return "Error saving data: " + e.getMessage(); 
	     } finally {
	   
	         if (session != null) {
	             session.close();
	         }
	     }
	 }
	 
	 @RequestMapping(value = "/admin/search_avn_daily_receipt_report", method = RequestMethod.GET)
		public ModelAndView Search_avn_daily_receipt_report(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_avn_daily_receipt_report", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);
			return new ModelAndView("search_avn_daily_receipt_reportTiles");
		}

	 @RequestMapping(value = "/admin/search_drr_avn", method = RequestMethod.POST)
	 public ModelAndView search_drr_avn(ModelMap Mmap, HttpSession sessionA,
	         @RequestParam(value = "msg", required = false) String msg,
	         @RequestParam(value = "status1", required = false) String status,
	         @RequestParam(value = "frm_date1", required = false) String from_date,
	         @RequestParam(value = "curr_date", required = false) String curr_date) throws ParseException {
	     
	     String roleid = sessionA.getAttribute("roleid") != null ? sessionA.getAttribute("roleid").toString() : "null";
	     System.out.println("Role ID: " + roleid); 
	     
	     Boolean val = roledao.ScreenRedirect("search_avn_daily_receipt_report", roleid);
	     if (val == false) {
	         return new ModelAndView("AccessTiles");
	     }
	     
	     String roleType = sessionA.getAttribute("roleType") != null ? sessionA.getAttribute("roleType").toString() : "null";
	     System.out.println("Role Type: " + roleType); 
	     
	     if (from_date != null && !from_date.isEmpty() && curr_date != null && !curr_date.isEmpty()) {
	         if (alltms.CompareDate(from_date, curr_date) == 0) {
	             Mmap.put("msg", "To Date should not be less than From Date");
	             return new ModelAndView("search_avn_daily_receipt_reportTiles");
	         }
	     }
	     
	     
	     System.out.println("Status: " + status);
	     System.out.println("From Date: " + from_date);
	     System.out.println("To Date: " + curr_date);
	     
	     if (!status.equals("")) {
	         Mmap.put("status", status);
	     }
	     if (!from_date.equals("")) {
	         Mmap.put("from_date", from_date);
	     }
	     if (!curr_date.equals("")) {
	         Mmap.put("curr_date", curr_date);
	     }
	     
	    
	     ArrayList<List<String>> list = avndao.getsearch_drr_avn(status, from_date, curr_date, roleType);
	     
	    
	     System.out.println("List size: " + (list != null ? list.size() : "null"));
	     if (list != null) {
	         for (List<String> item : list) {
	             System.out.println("List item: " + item);
	         }
	     }
	     
	     Mmap.put("list", list);
	     Mmap.put("roleType", roleType);
	     Mmap.put("search_status", "0");
	     return new ModelAndView("search_avn_daily_receipt_reportTiles");
	 }
	 
	 @SuppressWarnings("unchecked")
		@RequestMapping(value = "/admin/AVNViewReceiveIssue", method = RequestMethod.POST)
		public ModelAndView AVNViewReceiveIssue(@ModelAttribute("viewSerNo") String viewSerNo, HttpSession sessionA,
				String viewStatus, String viewDate, String viewStatus1, String viewSus, String viewfrom_dt,
				String viewto_dt,String search_sus_no, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

			String roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_avn_daily_receipt_report", roleid);
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
					"from TB_AVIATION_DRR_DTL where drr_ser_no=:drr_ser_no  and status=:status and CAST(created_on as date)>=:viewDate");
			q.setParameter("drr_ser_no", viewSerNo).setParameter("status", viewStatus).setParameter("viewDate", date);
			List<TB_AVIATION_DRR_DTL> list = (List<TB_AVIATION_DRR_DTL>) q.list();
			session.getTransaction().commit();
			session.close();

			ArrayList<List<String>> list1 = avndao.getApprovedTail_NoFromDRRAVN(viewSerNo, viewStatus, viewDate, viewSus,
					roleType);
			AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
			if (list1.size() != 0) {
				model.put("view_avn_daily_Receipt_ReportCMD", list1);
				model.put("tail_no", list1.get(0).get(1));
				model.put("agency_name", list1.get(0).get(2));
				model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(list1.get(0).get(3), sessionA).get(0));
				model.put("classification", list1.get(0).get(5));
				model.put("status", list1.get(0).get(6));
				model.put("roleType", roleType);
				model.put("sus_no", list1.get(0).get(3));

			}
			model.put("viewSerNo", viewSerNo);
			model.put("s_viewStatus1", viewStatus1);
			model.put("s_viewSus", viewSus);
			model.put("s_viewfrom_dt", viewfrom_dt);
			model.put("s_viewto_dt", viewto_dt);
			model.put("search_sus_no", search_sus_no);
			
			return new ModelAndView("view_avn_daily_receipt_reportTiles");
		}

	 
	 @RequestMapping(value = "/admin/AVNApprovedReceiveIssue", method = RequestMethod.POST)
		public ModelAndView setApproveddrrAVN(@ModelAttribute("sus_no_aprove") String sus_no_aprove,
				String ser_no_approve, String tail_no,String agency_name ,  String viewStatus11,String viewSus1, String viewfrom_dt1,String viewto_dt1,String search_status, ModelMap model,
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

			
			Session sessionGet2 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx2 = sessionGet2.beginTransaction();
			Query q = sessionGet2.createQuery(
						"delete from TB_AVIATION_DAILY_BASIS where acc_no in(select tail_no from TB_AVIATION_DRR_DTL where unit_sus_no=:sus_no and drr_ser_no=:drr_ser_no)");
			q.setParameter("sus_no", sus_no_aprove).setParameter("drr_ser_no", ser_no_approve);
			q.executeUpdate();
			tx2.commit();
			sessionGet2.close();
			
			Session sessionGet3 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx3 = sessionGet3.beginTransaction();
			Query q1 = sessionGet3.createQuery(
						"delete from TB_AVIATION_CHTL_DAILY_BASIS where acc_no in(select tail_no from TB_AVIATION_DRR_DTL where unit_sus_no=:sus_no and drr_ser_no=:drr_ser_no)");
			q1.setParameter("sus_no", sus_no_aprove).setParameter("drr_ser_no", ser_no_approve);
			q1.executeUpdate();
			tx3.commit();
			sessionGet3.close();
			
			Session sessionGet4 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx4= sessionGet4.beginTransaction();
			Query q2 = sessionGet4.createQuery(
						"delete from TB_AVIATION_RPAS_DAILY_BASIS where acc_no in(select tail_no from TB_AVIATION_DRR_DTL where unit_sus_no=:sus_no and drr_ser_no=:drr_ser_no)");
			q2.setParameter("sus_no", sus_no_aprove).setParameter("drr_ser_no", ser_no_approve);
			q2.executeUpdate();
			tx4.commit();
			sessionGet4.close();
			
			 String m ="3";
	    	 Session sessionGet5 = HibernateUtilNA.getSessionFactory().openSession();
	    	 Transaction tx5= sessionGet5.beginTransaction();
				Query q3 = sessionGet5.createQuery("Update TB_AVIATION_TAILNO_DTL set Classifications=:Classifications where tail_no=:tail_no");
				q3.setParameter("tail_no", tail_no).setParameter("Classifications", m);
				q3.executeUpdate();
				tx5.commit();
				sessionGet5.close();
				
				 Session sessionGet6 = HibernateUtilNA.getSessionFactory().openSession();
		    	 Transaction tx6= sessionGet6.beginTransaction();
					Query q4 = sessionGet6.createQuery("Update TB_AVIATION_TAILNO_DTL set Classifications=:Classifications where tail_no=:tail_no");
					q4.setParameter("tail_no", tail_no).setParameter("Classifications", m);
					q4.executeUpdate();
					tx6.commit();
					sessionGet6.close();
					
					 Session sessionGet7 = HibernateUtilNA.getSessionFactory().openSession();
			    	 Transaction tx7= sessionGet7.beginTransaction();
						Query q5 = sessionGet7.createQuery("Update TB_AVIATION_RPAS_TAILNO_DTL set Classifications=:Classifications where tail_no=:tail_no");
						q5.setParameter("tail_no", tail_no).setParameter("Classifications", m);
						q5.executeUpdate();
						tx7.commit();
						sessionGet7.close();
			
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update TB_AVIATION_DRR_DTL c set c.status = :status, c.approved_by = :approved_by, c.approved_on = :approved_on where c.unit_sus_no =:sus_no and c.drr_ser_no =:drr_ser_no and c.status = '0'";
			int app = session.createQuery(hqlUpdate).setString("status", "1").setString("approved_by", username)
					.setTimestamp("approved_on", new Date()).setString("sus_no", sus_no_aprove)
					.setString("drr_ser_no", ser_no_approve).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				model.put("msg", "Approved Successfully.");
				
			} else {
				model.put("msg", "Approved Not Successfully.");
			}
			model.put("sus_no", viewSus1);
			model.put("b_viewStatus1", viewStatus11);
			model.put("from_date", viewfrom_dt1);
			model.put("curr_date", viewto_dt1);
			model.put("search_status", "1");
			return new ModelAndView("search_avn_daily_receipt_reportTiles");
		}    

	 @RequestMapping(value = "/admin/AVNUpdateReceiveIssue")
		public ModelAndView AVNUpdateReceiveIssue(@ModelAttribute("updateid") int updateid, ModelMap model,
				Authentication authentication, HttpSession sessionA) {
			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_AVIATION_DRR_DTL where id=:id");
			q.setParameter("id", updateid);
			TB_AVIATION_DRR_DTL upid = (TB_AVIATION_DRR_DTL) q.list().get(0);
			tx.commit();

			model.put("edit_avn_daily_Receipt_ReportCMD", upid);
			return new ModelAndView("edit_avn_daily_receipt_reportTiles");
		}
	 
	 @RequestMapping(value = "/updateAviationData", method = RequestMethod.POST)
	 public @ResponseBody String updateAviationData(@RequestParam String drr_ser_no,
	                                @RequestParam String tail_no,
	                                @RequestParam String authority,
	                                @RequestParam String remarks,
	                                @RequestParam String unit_sus_no,
	                                @RequestParam String agency_name,
	                                @RequestParam String std_nomen,
	                                ModelMap Mmap, HttpSession sessionA) {
	     Session sessionGet4 = null;
	     Transaction tx4 = null;
	    
	   
	     try {
	    	
				
	        sessionGet4 = HibernateUtilNA.getSessionFactory().openSession();
			 tx4= sessionGet4.beginTransaction();
				Query q2 = sessionGet4.createQuery("Update TB_AVIATION_DRR_DTL set tail_no=:tail_no, agency_name=:agency_name, std_nomen=:std_nomen, remarks=:remarks, authority=:authority where drr_ser_no=:drr_ser_no");
				q2.setParameter("tail_no", tail_no).setParameter("drr_ser_no", drr_ser_no).setParameter("agency_name", agency_name).setParameter("std_nomen", std_nomen).setParameter("remarks", remarks).setParameter("authority", authority);
				q2.executeUpdate();
				tx4.commit();
	         return "Data updated Successfully."; 
	     } catch (Exception e) {
	         
	         if (tx4 != null) {
	             tx4.rollback();
	         }
	         e.printStackTrace(); 
	         return "Error updating data: " + e.getMessage(); 
	     } finally {
	   
	         if (sessionGet4 != null) {
	        	 sessionGet4.close();
	         }
	     }
	 }
	 
	 @RequestMapping(value = "/admin/AVNDeleteReceiveReport", method = RequestMethod.POST)
		public ModelAndView AVNDeleteReceiveReport(@ModelAttribute("deleteid") String deleteid,
				String drr_ser_no, String tail_no, String unit_sus_no, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionA) {

			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "delete from TB_AVIATION_DRR_DTL where id = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", Integer.parseInt(deleteid));
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if (rowCount > 0) {
			} else {
			}
			return new ModelAndView("redirect:search_avn_daily_receipt_report");
		}
}
