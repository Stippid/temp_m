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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_DESERTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class DeserterController {
 
	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	
	 
	@Autowired
	private RoleBaseMenuDAO roledao;
 
	// -------------------------------Deserter save -------------------------------------
	
	
	@RequestMapping(value = "/admin/deserterAction", method = RequestMethod.POST)
	public @ResponseBody String deserterAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_desertion_dt = null;
		Date dt_recovered_dt = null;
		String msg = "";
		String username = session.getAttribute("username").toString();
		String deserter = request.getParameter("deserter"); 
		String arm_type = request.getParameter("arm_type"); 
		String arm_type_weapon = request.getParameter("arm_type_weapon"); 
		String desertion_cause = request.getParameter("desertion_cause"); 
		String indl_class = request.getParameter("indl_class"); 
		String ot_desertion_cause = request.getParameter("ot_desertion_cause"); 
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String dt_desertion = request.getParameter("dt_desertion");
		String recovered_arms = request.getParameter("recovered_arms");
		if (!dt_desertion.equals("") && !dt_desertion.equals("DD/MM/YYYY")) {
			dt_desertion_dt = format.parse(dt_desertion);
		}

		String dt_recovered = request.getParameter("dt_recovered");
		if (!dt_recovered.equals("") && !dt_recovered.equals("DD/MM/YYYY")) {
			dt_recovered_dt = format.parse(dt_recovered);
		}
		
		if (arm_type == null || arm_type.equals("0") || arm_type == "null") {
			msg = "Please Select Arms Type";
			return msg;
		}
		if (arm_type.equals("1")){
			if (arm_type_weapon == null || arm_type_weapon.trim().equals("") || arm_type_weapon == "null") {
				msg = "Please Enter Weapon ";
				return msg;
			}
			/*if (!valid.isOnlyAlphabet(arm_type_weapon)) {
				return " Weapon " + valid.isOnlyAlphabetMSG;
			}*/
			if (!valid.isvalidLength(arm_type_weapon, valid.authorityMax, valid.authorityMin)) {
				return " Weapon " + valid.isValidLengthMSG;
			}
		}
		if (dt_desertion == null || dt_desertion.equals("null")
				|| dt_desertion.equals("DD/MM/YYYY") || dt_desertion.equals("")) {
			msg = "Please Enter Date of Desertion"
					+ " ";
			return msg;
		}
		if (mcommon.CompareDate(dt_desertion_dt,comm_date) == 0) {
			return "Desertion Date should be Greater than Commission Date";
		}
		if (desertion_cause == null || desertion_cause.equals("0") || desertion_cause == "null") {
			msg = "Please Enter Cause of Desertation";
			return msg;
		}
		
		if (desertion_cause.equals("3")){
			if (ot_desertion_cause == null  || ot_desertion_cause.trim().equals("")|| ot_desertion_cause == "null") {
				msg = "Please Enter Others";
				return msg;
			}
			if (!valid.isOnlyAlphabet(ot_desertion_cause)) {
				return " Others " + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(ot_desertion_cause, valid.nameMax, valid.nameMin)) {
				return " Others " + valid.isValidLengthMSG;

			}
		}
		String census_id = request.getParameter("census_id"); 
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id")); 
		String des_ch_id = request.getParameter("des_ch_id");
		//String des_ch_id =  "5" ;
	
		 

		try {
			if (Integer.parseInt(des_ch_id) == 0) {
				TB_DESERTER d = new TB_DESERTER();
				d.setDeserter("0");
				d.setDt_desertion(dt_desertion_dt);
				d.setDt_recovered(dt_recovered_dt);
				d.setArm_type(arm_type);
				d.setArm_type_weapon(arm_type_weapon);
				d.setDesertion_cause(desertion_cause);
				d.setIndl_class(indl_class);
				d.setOt_desertion_cause(ot_desertion_cause);
				d.setRecovered_arms(recovered_arms);
				d.setCensus_id(Integer.parseInt(census_id));
				d.setComm_id(comm_id);
				d.setCreated_by(username);
				d.setCreated_date(date);
                d.setStatus(0);
				int id = (int) sessionhql.save(d);
				msg = Integer.toString(id);
			} else {
				
				String hql = "update TB_DESERTER set  approved_by=:approved_by,approved_date=:approved_date,"
						+ "deserter=:deserter, dt_desertion=:dt_desertion,dt_recovered=:dt_recovered, "
						+ "arm_type=:arm_type,arm_type_weapon=:arm_type_weapon,desertion_cause=:desertion_cause,"
						+ "indl_class=:indl_class,ot_desertion_cause=:ot_desertion_cause,recovered_arms=:recovered_arms,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql)
						.setString("deserter", "0")
						.setTimestamp("dt_desertion", dt_desertion_dt)
						.setTimestamp("dt_recovered", dt_recovered_dt)
						.setString("arm_type", arm_type)
						.setString("arm_type_weapon", arm_type_weapon)
						.setString("desertion_cause", desertion_cause)
						.setString("recovered_arms", recovered_arms)
						.setString("indl_class", indl_class)
						.setString("ot_desertion_cause", ot_desertion_cause)
						.setInteger("status", 0)
						.setInteger("id", Integer.parseInt(des_ch_id))
						.setString("approved_by", username)
						.setTimestamp("approved_date", new Date());

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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


	
	@RequestMapping(value = "/admin/deserter_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_DESERTER> deserter_GetData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_DESERTER where comm_id=:comm_id and census_id=:census_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate) .setBigInteger("comm_id", comm_id).setInteger("census_id", census_id);
		List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 
	@RequestMapping(value = "/admin/deserter_GetDataA", method = RequestMethod.POST)
	public @ResponseBody List<TB_DESERTER> deserter_GetDataA(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		//int census_id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_DESERTER where comm_id=:comm_id and dt_recovered is null and status = '1'";
		Query query = sessionHQL.createQuery(hqlUpdate) .setBigInteger("comm_id", comm_id);
		List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/*--------------------- For Approval ---------------------------------*/

	public @ResponseBody List<TB_DESERTER> deserter_GetData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_DESERTER where census_id=:census_id and comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Comm_deserterStatusForOut(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where id=:comm_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", obj.getId())
					.setInteger("status", 5);

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	
	public String Update_Comm_deserterStatusForIn(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where id=:comm_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", obj.getId())
					.setInteger("status", 1);

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_Deserter(TB_DESERTER obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  String msg1 = "";
		  try{
			   String hql1 = "update TB_DESERTER set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') ";
			   
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCensus_id())
						.setBigInteger("comm_id",obj.getComm_id());
						
				msg1 = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				 
				 
			
				  
			    String hql = "update TB_DESERTER set approved_by=:approved_by,approved_date=:approved_date,status=:status  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
			  
				 Query query = sessionHQL.createQuery(hql).setString("approved_by", username).setTimestamp("approved_date",obj.getApproved_date())
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
	
    @RequestMapping(value = "/admin/GetDeserter_Reject", method = RequestMethod.POST)
    public @ResponseBody String GetDeserter_Reject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
    		@RequestParam(value = "msg", required = false) String msg) throws ParseException {
 	   
    	     String username = session.getAttribute("username").toString();
    	     TB_DESERTER DE = new TB_DESERTER();

    	     DE.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
    	     DE.setComm_id(new BigInteger(request.getParameter("comm_id")));
    	     DE.setId(Integer.parseInt(request.getParameter("des_ch_id")));
    	     DE.setReject_remarks(request.getParameter("reject_remarks"));
    			String msg1 =Deserter_Reject(DE, username);
    			
    		  return msg1;
          
    }

public String Deserter_Reject(TB_DESERTER obj,String username){
	
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
			 
	    
	  	 
		      String hql = "update TB_DESERTER set approved_by=:approved_by,approved_date=:approved_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";
		  
			  Query query = sessionHQL.createQuery(hql).setString("approved_by", username).setTimestamp("approved_date", new Date())
					  .setString("reject_remarks", obj.getReject_remarks())
					.setInteger("status", 3).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id())
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
public @ResponseBody List<TB_DESERTER> deserter_GetData2(int id,BigInteger comm_id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	String hqlUpdate = " from TB_DESERTER where census_id=:census_id and comm_id=:comm_id and status = '3'";
	Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
	List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}


@RequestMapping(value = "/admin/deserter_GetData3", method = RequestMethod.POST)
public @ResponseBody List<TB_DESERTER> deserter_GetData3(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	int id = Integer.parseInt(request.getParameter("census_id"));
	BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

	String hqlUpdate = " from TB_DESERTER where census_id=:census_id and comm_id=:comm_id and status = '3' ";
	Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
	List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}


@RequestMapping(value = "/admin/deserter_delete_action", method = RequestMethod.POST)
	public @ResponseBody String deserter_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_DESERTER where id=:id";
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
