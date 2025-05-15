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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Master.TB_MEDAL_TYPE;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AwardsNmedal_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();

	/*	@RequestMapping(value = "/admin/AwardsNmedalUrl", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Mmap.put("getCommandDetailsList", m.getCommandDetailsList());
		Mmap.put("getMedalList", mcommon.getMedalList());
		
		return new ModelAndView("AwardsNmedalTiles");
	}*/
	
	@RequestMapping(value = "/admin/getawardsNmedalData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory(). openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("q_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where census_id=:census_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@RequestMapping(value = "/admin/awardsNmedal_action", method = RequestMethod.POST)
	public @ResponseBody String awardsNmedal_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date date_award = null;
		Date anm_dtoa = null;
		String username = session.getAttribute("username").toString();

		String Category_8 = request.getParameter("Category_8");
		
		String select_desc = request.getParameter("select_desc");
		String date_of_award = request.getParameter("date_of_award");
		
		String awardsNmedal_unit = request.getParameter("awardsNmedal_unit");
		String awardsNmedal_bde = request.getParameter("awardsNmedal_bde");
		String awardsNmedal_div_subarea = request.getParameter("awardsNmedal_div_subarea");
		String awardsNmedal_corps_area = request.getParameter("awardsNmedal_corps_area");
		String awardsNmedal_command = request.getParameter("awardsNmedal_command");
		String awardsNmedal_ch_id = request.getParameter("awardsNmedal_ch_id");
		String q_id = request.getParameter("q_id");
		String com_id = request.getParameter("com_id");
		String anm_authority = request.getParameter("anm_authority");
		String anm_doa = request.getParameter("anm_doa");
		
		
		String msg = "";
		
		if(anm_authority == null || anm_authority.equals("")){ 
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(anm_authority)) {
			return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(anm_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}	
		if (anm_doa == null || anm_doa.equals("null") || anm_doa.equals("DD/MM/YYYY") || anm_doa.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!validation.isValidDate(anm_doa)) {
			return  validation.isValidDateMSG + " of Authority";	
		}
		if (!anm_doa.equals("") && !anm_doa.equals("DD/MM/YYYY")) {
			anm_dtoa = format.parse(anm_doa);
		}
		if(Category_8 == null || Category_8.equals("0")){ 
			return "Please Select Category";
		}
		if(select_desc == null || select_desc.equals("0")){ 
			return "Please Select Award/Medal.";
		}
		if (date_of_award == null || date_of_award.equals("null") || date_of_award.equals("DD/MM/YYYY") || date_of_award.equals("")) {
			return "Please Select Date of Award";
		}
		if (!validation.isValidDate(date_of_award)) {
			return  validation.isValidDateMSG + " of Award";	
		}
		if (!date_of_award.equals("") && !date_of_award.equals("DD/MM/YYYY")) {
			date_award = format.parse(date_of_award);
		}
		
		if(awardsNmedal_unit == null || awardsNmedal_unit.equals("")){ 
			return "Please Enter Unit";
		}
		/*
		 * if(awardsNmedal_bde == null || awardsNmedal_bde.equals("0")){ return
		 * "Please Enter BDE"; } if(awardsNmedal_div_subarea == null ||
		 * awardsNmedal_div_subarea.equals("0")){ return "Please Enter DIV/Subarea"; }
		 * if(awardsNmedal_corps_area == null || awardsNmedal_corps_area.equals("0")){
		 * return "Please Enter Corps/Area"; }
		 */
		if(awardsNmedal_command == null || awardsNmedal_command.equals("0")){ 
			return "Please Enter Command";
		}

		TB_CENSUS_AWARDSNMEDAL anm = new TB_CENSUS_AWARDSNMEDAL();

		try {
			if (Integer.parseInt(awardsNmedal_ch_id) == 0) {

				

				anm.setCategory_8(Category_8);
				anm.setSelect_desc(select_desc);
				anm.setDate_of_award(date_award);
				anm.setUnit(awardsNmedal_unit);
				anm.setBde(awardsNmedal_bde);
				anm.setDiv_subarea(awardsNmedal_div_subarea);
				anm.setCorps_area(awardsNmedal_corps_area);
				anm.setCommand(awardsNmedal_command);
				anm.setCensus_id(Integer.parseInt(q_id));
				anm.setComm_id(new BigInteger(com_id));
				anm.setAuthority(anm_authority);
				anm.setDate_of_authority(anm_dtoa);
				anm.setCreated_by(username);
				anm.setCreated_on(date);

				int id = (int) sessionhql.save(anm);
				msg = Integer.toString(id);
				
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_AWARDSNMEDAL set modify_by=:modify_by ,modify_on=:modify_on ,category_8=:category_8, select_desc=:select_desc ,date_of_award=:date_of_award,unit=:unit,"
						+ " bde=:bde,div_subarea=:div_subarea,corps_area=:corps_area,command=:command,status=:status,"
						+" authority=:authority,date_of_authority=:date_of_authority where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("category_8", Category_8).setInteger("status", 0)
						.setString("select_desc", select_desc)
						.setTimestamp("date_of_award", date_award)
						.setString("unit", awardsNmedal_unit)
						.setString("bde", awardsNmedal_bde)
						.setString("div_subarea", awardsNmedal_div_subarea)
						.setString("corps_area", awardsNmedal_corps_area)
						.setString("command", awardsNmedal_command)
						.setInteger("id", Integer.parseInt(awardsNmedal_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date)
						.setTimestamp("date_of_authority", anm_dtoa)
						.setString("authority", anm_authority);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(q_id),username);
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
	
	@RequestMapping(value = "/admin/awardsNmedal_delete_action", method = RequestMethod.POST)
	public @ResponseBody String awardsNmedal_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("awardsNmedal_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_AWARDSNMEDAL where id=:id";
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
	
	@RequestMapping(value = "/admin/getMedalDescList", method = RequestMethod.POST)
	public @ResponseBody List<TB_MEDAL_TYPE> getMedalDescList(String cat, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_MEDAL_TYPE where medal_type=:cat";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("cat", cat);
		List<TB_MEDAL_TYPE> list = (List<TB_MEDAL_TYPE>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	/*--------------------- For Approval ----------------------------------*/
	
	
	public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData1(int id,BigInteger comm_id){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where census_id=:census_id and status='0' and comm_id=:comm_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}
	
	
	public String Update_Awards_Medals(TB_CENSUS_AWARDSNMEDAL obj,String username){
  	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  	   Transaction tx = sessionHQL.beginTransaction();
  	  
  		  String msg = "";
  		  //String msg1= "";
  		  try{
  			  
  			     /*String hql1 = "update TB_CENSUS_AWARDSNMEDAL set status=:status where census_id=:census_id and comm_id=:comm_id and status != '0' ";
  			   
  				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCensus_id())
  						.setInteger("comm_id",obj.getComm_id());
  						
  				 msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";*/
  				 
  		   
  		    	 
  			    String hql = "update TB_CENSUS_AWARDSNMEDAL set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
  						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
  			  
  				Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", obj.getModify_on())
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
	
	@RequestMapping(value = "/admin/getaward_Reject", method = RequestMethod.POST)
	public @ResponseBody String getaward_Reject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		     String username = session.getAttribute("username").toString();
		     TB_CENSUS_AWARDSNMEDAL ChangeAwa = new TB_CENSUS_AWARDSNMEDAL();
		     ChangeAwa.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		     ChangeAwa.setComm_id(new BigInteger(request.getParameter("comm_id")));
		     ChangeAwa.setReject_remarks(request.getParameter("reject_remarks"));
			 String msg1 = Update_Change_of_AwardReject(ChangeAwa, username);
				
			  return msg1;
	      
	}

	public String Update_Change_of_AwardReject(TB_CENSUS_AWARDSNMEDAL obj,String username){
		
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  String msg1= "";
		 try{
//			      String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//						+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//			  
//				   Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username).setTimestamp("modified_date", new Date())
//						.setInteger("update_ofr_status", 3).setInteger("census_id", obj.getCensus_id()).setInteger("comm_id",obj.getComm_id());
//						
//				   msg1 = query1.executeUpdate() > 0 ? "Data Rejected Successfully." :"Data Not Rejected. ";
		    
			      String hql = "update TB_CENSUS_AWARDSNMEDAL set modify_by=:modified_by,modify_on=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
			  
				  Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
						  .setString("reject_remarks", obj.getReject_remarks())
						.setInteger("status", 3).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id());
			
		          
		          msg = query.executeUpdate() > 0 ? "1" :"0";
					 
		          tx.commit();
		  
		  }catch (Exception e) {
		          msg = "Data Not Updated.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;

	}

	

		public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> getChangeOfAward2(int id,BigInteger comm_id){
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}

		@RequestMapping(value = "/admin/get_Award3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> get_Award3(ModelMap Mmap, HttpSession session,
		            HttpServletRequest request) throws ParseException {
		    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = sessionHQL.beginTransaction();
		    int id = Integer.parseInt(request.getParameter("p_id"));
		  /// bisag 241122 V2 (change comm_id int to big int)
			BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		    String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		    Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id",comm_id);
		    @SuppressWarnings("unchecked")
		    List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		     tx.commit();
		    sessionHQL.close();
		    return list;
		}
	
}
