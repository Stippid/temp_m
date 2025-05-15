package com.controller.psg.Jco_Update_JcoData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_DESERTER_JCO;
import com.models.psg.Master.TB_BLOOD_GROUP;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;
 
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Deserter_Jco_Controller {
 
	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
	 
 
/*	@RequestMapping(value = "/admin/deserterUrl")
	 public ModelAndView deserterUrl(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Bank", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
		 Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
		 Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
		 Mmap.put("msg", msg);
		 
		 return new ModelAndView("deserterTiles");
	 }*/
	
	// -------------------------------Deserter save -------------------------------------
	
	@RequestMapping(value = "/admin/deserterAction_jco", method = RequestMethod.POST)
    public @ResponseBody String deserterAction_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
            Date enroll_date = format.parse(request.getParameter("enroll_date"));
            String dt_desertion = request.getParameter("dt_desertion");
            String recovered_arms = request.getParameter("recovered_arms");

            String dt_recovered = request.getParameter("dt_recovered");
            if (!dt_recovered.equals("") && !dt_recovered.equals("DD/MM/YYYY")) {
                    dt_recovered_dt = format.parse(dt_recovered);
            }
            
            if (arm_type.equals("0") || arm_type == null || arm_type == "null") {
                    msg = "Please Enter Arm Type";
                    return msg;
            }
            if (arm_type.equals("1")){
                    if (arm_type_weapon.equals("") || arm_type_weapon == null || arm_type_weapon == "null") {
                            msg = "Please Enter Weapon";
                            return msg;
                    }
                	/*if (!valid.isOnlyAlphabet(arm_type_weapon)) {
        		 		return  " Weapon " + valid.isOnlyAlphabetMSG;	
        			}*/
                	if (!valid.isvalidLength(arm_type_weapon,valid.authorityMax,valid.authorityMin)) {
             			return " Weapon " + valid.isValidLengthMSG;	
            		}	
            }
            if (dt_desertion == null || dt_desertion.equals("null")
                            || dt_desertion.equals("DD/MM/YYYY") || dt_desertion.equals("")) {
                    msg = "Please Enter Date of Desertion.";
                    return msg;
            }
            else if (!valid.isValidDate(dt_desertion)) {
                     return valid.isValidDateMSG + " of Desertion";        
            }
            else {
                    dt_desertion_dt = format.parse(dt_desertion);
            }
            if (mcommon.CompareDate(dt_desertion_dt,enroll_date) == 0) {
                    return "Desertion Date should be Greater than Enroll Date";
            }
            if (desertion_cause.equals("0") || desertion_cause == null || desertion_cause == "null") {
                    msg = "Please Enter Cause of Desertion";
                    return msg;
            }
            
            if (desertion_cause.equals("3")){
                    if (ot_desertion_cause.equals("") || ot_desertion_cause == null || ot_desertion_cause == "null") {
                            msg = "Please Enter Others";
                            return msg;
                    }
            }
            String jco_id = request.getParameter("jco_id"); 
            String des_ch_id = request.getParameter("des_ch_id");


            try {
                    if (Integer.parseInt(des_ch_id) == 0) {
                            TB_DESERTER_JCO d = new TB_DESERTER_JCO();
                            d.setDeserter("0");
                            d.setDt_desertion(dt_desertion_dt);
                            d.setDt_recovered(dt_recovered_dt);
                            d.setArm_type(arm_type);
                            d.setArm_type_weapon(arm_type_weapon);
                            d.setDesertion_cause(desertion_cause);
                            d.setIndl_class(indl_class);
                            d.setOt_desertion_cause(ot_desertion_cause);
                            d.setRecovered_arms(recovered_arms);
                            d.setJco_id(Integer.parseInt(jco_id));
                            d.setCreated_by(username);
                            d.setCreated_date(date);
                            d.setStatus(0);
                            d.setInitiated_from("u");
                            int id = (int) sessionhql.save(d);
                            msg = Integer.toString(id);
                    } else {
                            
                            String hql = "update TB_DESERTER_JCO set  approved_by=:approved_by,approved_date=:approved_date,"
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
                            pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
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




	
	@RequestMapping(value = "/admin/deserter_GetData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_DESERTER_JCO> deserter_GetData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_DESERTER_JCO where jco_id=:jco_id and  status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate) .setInteger("jco_id", jco_id);
		List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 
	@RequestMapping(value = "/admin/deserter_GetDataA_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_DESERTER_JCO> deserter_GetDataA_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_DESERTER_JCO where jco_id=:jco_id and dt_recovered is null and  status = '1'";
		Query query = sessionHQL.createQuery(hqlUpdate) .setInteger("jco_id", jco_id);
		List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/*--------------------- For Approval ---------------------------------*/

	public @ResponseBody List<TB_DESERTER_JCO> deserter_GetData1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_DESERTER_JCO where  jco_id=:jco_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Census_deserterStatusForOut(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where id=:jco_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("jco_id", obj.getId())
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
	
	public String Update_Census_deserterStatusForIn(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where id=:jco_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("jco_id", obj.getId())
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
	public String Update_Deserter(TB_DESERTER_JCO obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  String msg1 = "";
		  try{
			   String hql1 = "update TB_DESERTER_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";
			   
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
						.setInteger("jco_id",obj.getJco_id());
						
				msg1 = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				 
				 
			
				  
			    String hql = "update TB_DESERTER_JCO set approved_by=:approved_by,approved_date=:approved_date,status=:status  "
						+ " where  jco_id=:jco_id and status = '0' ";
			  
				 Query query = sessionHQL.createQuery(hql).setString("approved_by", username).setTimestamp("approved_date",obj.getApproved_date())
						.setInteger("status", 1).setInteger("jco_id",obj.getJco_id());
			
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
	
    @RequestMapping(value = "/admin/GetDeserter_Reject_jco", method = RequestMethod.POST)
    public @ResponseBody String GetDeserter_Reject_jco(ModelMap Mmap, HttpSession session,HttpServletRequest request,
    		@RequestParam(value = "msg", required = false) String msg) throws ParseException {
    	
    	      String reject_remarks = request.getParameter("reject_remarks");

    	     String username = session.getAttribute("username").toString();
    	     TB_DESERTER_JCO DE = new TB_DESERTER_JCO();

    	     DE.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
    	     DE.setId(Integer.parseInt(request.getParameter("des_ch_id")));
    	     DE.setReject_remarks(reject_remarks);
    			String msg1 =Deserter_Reject(DE, username);
    			
    		  return msg1;
          
    }

public String Deserter_Reject(TB_DESERTER_JCO obj,String username){
	
  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  Transaction tx = sessionHQL.beginTransaction();

	  String msg = "";
	  String msg1= "";
	 try{
 
	  	 
		      String hql = "update TB_DESERTER_JCO set approved_by=:approved_by,approved_date=:approved_date,status=:status,reject_remarks=:reject_remarks "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";
		  
			  Query query = sessionHQL.createQuery(hql).setString("approved_by", username).setTimestamp("approved_date", new Date())
					.setInteger("status", 3).setInteger("jco_id",obj.getJco_id()).setString("reject_remarks", obj.getReject_remarks())
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
public @ResponseBody List<TB_DESERTER_JCO> deserter_GetData2(int jco_id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	String hqlUpdate = " from TB_DESERTER_JCO where  jco_id=:jco_id and status = '3'";
	Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
	List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}


@RequestMapping(value = "/admin/deserter_GetData3_jco", method = RequestMethod.POST)
public @ResponseBody List<TB_DESERTER_JCO> deserter_GetData3_jco(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	int jco_id = Integer.parseInt(request.getParameter("jco_id"));
	String hqlUpdate = " from TB_DESERTER_JCO where  jco_id=:jco_id and status = '3' ";
	Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
	List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}


@RequestMapping(value = "/admin/deserter_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String deserter_delete_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_DESERTER_JCO where id=:id";
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
