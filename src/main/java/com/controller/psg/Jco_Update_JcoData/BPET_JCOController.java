package com.controller.psg.Jco_Update_JcoData;

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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_BPET_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class BPET_JCOController {

	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();

	ValidationController valid = new ValidationController();
//////////////////BPET START SAVE //////////
			
		@RequestMapping(value = "/admin/BPET_JCOaction", method = RequestMethod.POST)
		public @ResponseBody String BPET_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
		throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		
		String BPET_result = request.getParameter("BPET_result");
		String bept_result_others = request.getParameter("bept_result_others");
		
		String BPET_qe = request.getParameter("BPET_qe");
		String year = request.getParameter("year");
		String unit_name = request.getParameter("unit_name");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String status = request.getParameter("status");
		
		String id = request.getParameter("id");
			
		String jco_id = request.getParameter("jco_id");	
		
		Date date_year_i = null;
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");      
        date_year_i = formatter2.parse(date1);
	    SimpleDateFormat YY = new SimpleDateFormat("yyyy");
		int year2 = Integer.parseInt(YY.format(date_year_i));
	
		
		
		if(BPET_result == null || BPET_result.equals("-1")){ 
			return "Please Select BPET Result";
		}
		
		if (BPET_result.equals("OTHERS") && bept_result_others.equals("") || BPET_result == "OTHERS" && bept_result_others == "") {
			return "Please Enter Others BPET Results";
		}
		if(BPET_qe == null || BPET_qe.equals("-1")){ 
			return "Please Select BPET QTR";
		}
		if(year == null || year.equals("")){ 
			return "Please Enter Year";
		}
		if (year != null || !year.trim().equals("")) {
		 if (valid.isOnlyNumer(year) == true) {
				return " Year " + valid.isOnlyNumerMSG ;
			}
			if (!valid.YearLength(year)) {
				return valid.YearMSG;
			}
		}
		if (Integer.parseInt(year) > year2) {
			return  "Future Year cannot be allowed";
		}
		
		if(unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no == ""){ 
			return "Please Enter Conducted at Unit";
		}
		if (unit_sus_no != "") {
			if (!valid.isUnit(unit_sus_no)) {
				return "Conducted at Unit " + valid.isUnitMSG;

			}
			if (!valid.isvalidLength(unit_sus_no, valid.nameMax, valid.nameMin)) {
				return " Conducted at Unit " + valid.isValidLengthMSG;

			}
		}
		String msg = "";
		try {
		
		if (Integer.parseInt(id) == 0) {
			TB_CENSUS_BPET_JCO bpet = new TB_CENSUS_BPET_JCO();
			bpet.setBpet_result(BPET_result);
			bpet.setBept_result_others(bept_result_others);
			bpet.setBpet_qe(BPET_qe);
			bpet.setYear(Integer.parseInt(year));
			bpet.setUnit_sus_no(unit_sus_no);
		
			bpet.setJco_id(Integer.parseInt(jco_id));
			bpet.setCreated_by(username);
			bpet.setCreated_date(date);
			bpet.setStatus(0);
			bpet.setInitiated_from("u");
			int id1 = (int) sessionhql.save(bpet);
			msg = Integer.toString(id1);
		} else {
			/*--S---REJECT----*/
			/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
			CC.setUpdate_ofr_status(0);
			sessionhql.save(CC);*/
			/*---E--REJECT----*/
			String hql = "update TB_CENSUS_BPET_JCO set bpet_result=:BPET_result,bept_result_others=:bept_result_others, bpet_qe=:BPET_qe,status=:status,"
					+ " year=:year,unit_sus_no=:unit_sus_no, modified_by=:modified_by,modified_date=:modified_date where  id=:id ";
			Query query = sessionhql.createQuery(hql).setInteger("status", 0)
					.setString("BPET_result", BPET_result)
					.setString("bept_result_others", bept_result_others)
					.setString("BPET_qe", BPET_qe)
					.setInteger("year", Integer.parseInt(year))
					.setString("unit_sus_no", unit_sus_no)
					.setString("modified_by", username).setTimestamp("modified_date",date)
					.setInteger("id", Integer.parseInt(id));
				
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

		// sessionhql.close();

		return msg;
	}
		
		
		//fire Details  GET  
		@RequestMapping(value = "/admin/getbpet_JCOData", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_BPET_JCO> getbpet_Data(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		
		
		String hqlUpdate = "from TB_CENSUS_BPET_JCO where  jco_id=:jco_id and status = '0' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BPET_JCO> list = (List<TB_CENSUS_BPET_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}
		
		@RequestMapping(value = "/admin/bpet_delete_JCOaction", method = RequestMethod.POST)
		public @ResponseBody String bpet_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_BPET_JCO where id=:id";
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

		/*--------------------- For Approval ----------------------------------*/
		
		public @ResponseBody List<TB_CENSUS_BPET_JCO> getbpet_Data1(int jco_id){
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			String hqlUpdate = "from TB_CENSUS_BPET_JCO where  jco_id=:jco_id and status = '0' ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_BPET_JCO> list = (List<TB_CENSUS_BPET_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			
			return list;
			}
		
		
		
		public String Update_BEPT(TB_CENSUS_BPET_JCO obj,String username){
		    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = sessionHQL.beginTransaction();
		  
			  String msg = "";
			  //String msg1 = "";
			  try{
				    /*String hql1 = "update TB_CENSUS_BPET_JCO set status=:status where  jco_id=:jco_id and status != '0' ";
				  
					Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
							.setInteger("jco_id",obj.getJco_id());
							
					msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";*/
					 
				
					  
				       String hql = "update TB_CENSUS_BPET_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
							+ " where  jco_id=:jco_id and status = '0'";
				  
					   Query query = sessionHQL.createQuery(hql)
							
							.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
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
		
	     @RequestMapping(value = "/admin/getBPET_JCOReject", method = RequestMethod.POST)
	     public @ResponseBody String getBPET_Reject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
	     		@RequestParam(value = "msg", required = false) String msg) throws ParseException {
	  	   
	    	 String reject_remarks = request.getParameter("reject_remarks");

	    	 
	     	     String username = session.getAttribute("username").toString();
	     	    TB_CENSUS_BPET_JCO BPET = new TB_CENSUS_BPET_JCO();
		     	 
		     	 BPET.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		     	 BPET.setReject_remarks(reject_remarks);
	     			String msg1 = BPET_Reject(BPET, username);
	     			
	     		  return msg1;
	           
	     }

	public String BPET_Reject(TB_CENSUS_BPET_JCO obj,String username){
	   Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	 
		  String msg = "";
		  
		 try{
			 

			      String hql = "update TB_CENSUS_BPET_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where  jco_id=:jco_id and status = '0' ";
			  
			      
				  Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks()).setInteger("jco_id",obj.getJco_id());
			
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
	public @ResponseBody List<TB_CENSUS_BPET_JCO> getbpet_Data2(int jco_id){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = "from TB_CENSUS_BPET_JCO where  jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BPET_JCO> list = (List<TB_CENSUS_BPET_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
		}
		@RequestMapping(value = "/admin/getbpet_DataJCO3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_BPET_JCO> getbpet_Data3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				int jco_id = Integer.parseInt(request.getParameter("jco_id"));
				String hqlUpdate = "from TB_CENSUS_BPET_JCO where  jco_id=:jco_id and status = '3' ";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
				@SuppressWarnings("unchecked")
				List<TB_CENSUS_BPET_JCO> list = (List<TB_CENSUS_BPET_JCO>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
		}
	
}
