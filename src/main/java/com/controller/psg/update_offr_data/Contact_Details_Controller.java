package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Contact_Details_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	///////////********************************change address*****************************
	
	// ************************************START CDA ACCOUNT NO & CONTACT DETAIL*******************************************
	/*@RequestMapping(value = "/admin/cda_account_url", method = RequestMethod.GET)
	public ModelAndView cda_account_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		Mmap.put("msg", msg);
		return new ModelAndView("cda_account_Tiles");
	}*/
	@RequestMapping(value = "/admin/cda_acnt_noaction", method = RequestMethod.POST)
	public @ResponseBody String cda_acnt_noaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
        String gmail = request.getParameter("gmail");
		 //BigInteger cda_account_no =BigInteger.ZERO;            
        /* if(!request.getParameter("cda_account_no") .equals("")) {
        cda_account_no =new BigInteger(request.getParameter("cda_account_no"));
		  }*/
		  /*BigInteger mobile_no = BigInteger.ZERO;
		 if(!request.getParameter("mobile_no") .equals("")) 
         {
		mobile_no =new BigInteger(request.getParameter("mobile_no"));
		     }*/
        
       
		 String mobile_no = request.getParameter("mobile_no");
		String nic_mail = request.getParameter("nic_mail");
		
		
		String cda_id = request.getParameter("cda_id");
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String census_id = request.getParameter("census_id");
		String msg = "";

		if (gmail == null || gmail.equals("")) {
			return "Please Enter the Gmail";
		}
		if (!validation.isValidEmail(gmail)) {
	 		return validation.isValidEmailMSG + " Gmail";	
		}
		if (!validation.isvalidLength(gmail,validation.gmail_Max,validation.gmail_Min)) {
 			return "Gmail " + validation.isValidLengthMSG;	
		}
		if (nic_mail == null || nic_mail.equals("")) {
			return "Please Enter the Nic Mail ";
		}
		if (!validation.isValidEmail(nic_mail)) {
	 		return validation.isValidEmailMSG + " Nic Mail";	
		}
		if (!validation.isvalidLength(gmail,validation.gmail_Max,validation.gmail_Min)) {
 			return "Nic Mail " + validation.isValidLengthMSG;	
		}
		if (mobile_no == null || mobile_no.equals("")) {
			return "Please Enter the Mobile No";
		}
		if (mobile_no != null && !mobile_no.trim().equals("")) {
			if (validation.isOnlyNumer(mobile_no) == true) {
				return validation.isOnlyNumerMSG + " Mobile No";
			}
			
		    if (!validation.isValidMobileNo(mobile_no)) {
		    	return validation.isValidMobileNoMSG;
			}
		}
		try {
			if (Integer.parseInt(cda_id) == 0) {

				TB_CENSUS_CDA_ACCOUNT_NO census_p = new TB_CENSUS_CDA_ACCOUNT_NO();
				    //census_p.setCda_account_no(cda_account_no);
                    census_p.setGmail(gmail);
					census_p.setNic_mail(nic_mail);
					census_p.setMobile_no(mobile_no);
					census_p.setComm_id(comm_id);
					census_p.setCensus_id(Integer.parseInt(census_id));
					census_p.setCreated_by(username);
					census_p.setCreated_date(date);
					census_p.setStatus(0);

				int id = (int) sessionhql.save(census_p);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = " update TB_CENSUS_CDA_ACCOUNT_NO set gmail=pgp_sym_encrypt(:gmail,current_setting('miso.version')),nic_mail=pgp_sym_encrypt(:nic_mail,current_setting('miso.version')),mobile_no=pgp_sym_encrypt(:mobile_no,current_setting('miso.version')),"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("gmail",gmail)
						.setString("nic_mail", nic_mail)
						.setString("mobile_no",mobile_no)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("cda_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
			}
			
			tx.commit();
			}catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}
	
	
	@RequestMapping(value = "/admin/cda_acnt_no_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
	
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}
	

	// ************************************END CDA ACCOUNT NO & CONTACT DETAIL*******************************************
	
	
/*--------------------- For Approval ----------------------------------*/
	
	public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData1(int id,BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_CDA_Account_No(TB_CENSUS_CDA_ACCOUNT_NO obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  String msg1 = "";
		  try{
			   String hql1 = "update TB_CENSUS_CDA_ACCOUNT_NO set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1')";
			   
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCensus_id())
						.setBigInteger("comm_id",obj.getComm_id());
						
				msg1 = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				 
				 
			
				  
			    String hql = "update TB_CENSUS_CDA_ACCOUNT_NO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
			  
				 Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
						.setInteger("status", 1).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id());
			
				 msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			     
		          tx.commit();
		  }
		  catch (Exception e) {
		          msg = "Data Not Approve Successfully.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;
	
	}

    /*--------------------- For REJECT ----------------------------------*/
	
     @RequestMapping(value = "/admin/GetCDA_Reject", method = RequestMethod.POST)
     public @ResponseBody String GetCDA_Reject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
     		@RequestParam(value = "msg", required = false) String msg) throws ParseException {
  	   
     	     String username = session.getAttribute("username").toString();
     	    TB_CENSUS_CDA_ACCOUNT_NO CDAacc = new TB_CENSUS_CDA_ACCOUNT_NO();

		     	   CDAacc.setCensus_id(Integer.parseInt(request.getParameter("census_id")));
		     	  CDAacc.setComm_id(new BigInteger(request.getParameter("comm_id")));
		     	 CDAacc.setId(Integer.parseInt(request.getParameter("cda_id")));
		     	CDAacc.setReject_remarks(request.getParameter("reject_remarks"));
     			String msg1 = CDAAccount_Reject(CDAacc, username);
     			
     		  return msg1;
           
     }

public String CDAAccount_Reject(TB_CENSUS_CDA_ACCOUNT_NO obj,String username){
	
   Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = sessionHQL.beginTransaction();
 
	  String msg = "";
	  String msg1= "";
	 try{
//		      String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//		  
//			   Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username).setTimestamp("modified_date", new Date())
//					.setInteger("update_ofr_status", 3).setInteger("census_id", obj.getCensus_id()).setInteger("comm_id",obj.getComm_id());
//					
//			  msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			 
	    
	  	 
		      String hql = "update TB_CENSUS_CDA_ACCOUNT_NO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";
		  
			  Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("status", 3).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id())
					.setString("reject_remarks", obj.getReject_remarks())
					.setInteger("id", obj.getId());
		
			   msg = query.executeUpdate() > 0 ? "1" :"0";
	          
	         
	          tx.commit();
	  
	  }catch (Exception e) {
	          msg = "Data Not Rejected.";
	          tx.rollback();
	  }
	  finally {
	          sessionHQL.close();
	  }
	  return msg;

}


			public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData2(int id,BigInteger comm_id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where census_id=:census_id and comm_id=:comm_id and status = '3'";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
				List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}
			
			
			@RequestMapping(value = "/admin/cda_acnt_no_GetData3", method = RequestMethod.POST)
			public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData3(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("census_id"));
				BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
				
				String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where census_id=:census_id and comm_id=:comm_id and status = '3'";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
				List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}
			 @RequestMapping(value = "/admin/Cda_acnt_no_delete_action", method = RequestMethod.POST)
				public @ResponseBody String Cda_acnt_no_delete_action(ModelMap Mmap, HttpSession session,
						HttpServletRequest request) throws ParseException {
					String msg = "";
					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					int id = Integer.parseInt(request.getParameter("id"));
					try {
						String hqlUpdate = "delete from TB_CENSUS_CDA_ACCOUNT_NO where id=:id";
						int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
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
}
