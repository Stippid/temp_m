package com.controller.psg.Commissioning;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_CADET;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COURSE;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_GENDER;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_UNIT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Update_Comm_Controller {
	
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Search_UpdateComm_Dao UCO;
	
	@Autowired
	Search_UpdateComm_Dao scldao;
	
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;
	
  	 @RequestMapping(value = "/admin/Update_Comm_Letter", method = RequestMethod.GET)
  	 public ModelAndView Update_Comm_Letter(ModelMap Mmap, HttpSession session,
  			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
  		 
  		 Boolean val = roledao.ScreenRedirect("Update_Comm_Letter", session.getAttribute("roleid").toString());
           if(val == false) {
                   return new ModelAndView("AccessTiles");
           }

  		if(request.getHeader("Referer") == null ) {
  			msg = "";
  			return new ModelAndView("redirect:bodyParameterNotAllow");
  		}
  		
  		 String roleAccess = session.getAttribute("roleAccess").toString();
  		 Mmap.put("msg", msg);
  		 Mmap.put("getCourseNameList", p_comm.getCourseNameList());          
  		 Mmap.put("getGenderList", p_comm.getGenderList());
  		 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());       
  		 Mmap.put("getRegtList", p_comm.getRegtList(""));
  		 Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
  		 Mmap.put("getPersonalType", p_comm.getPersonalType());
  		 Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
  		 Mmap.put("getParentArmList", p_comm.getParentArmList());
  		Mmap.put("roleAccess",roleAccess);
  		

  		 return new ModelAndView("Update_Comm_Letter_Tiles");
  	 }
  	 
  	 
  	 @RequestMapping(value = "/admin/Update_Comm_MnsLetter", method = RequestMethod.GET)
  	 public ModelAndView Update_Comm_MnsLetter(ModelMap Mmap, HttpSession session,
  			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
  		 
  		 Boolean val = roledao.ScreenRedirect("Update_Comm_MnsLetter", session.getAttribute("roleid").toString());
           if(val == false) {
                   return new ModelAndView("AccessTiles");
          }

 		if(request.getHeader("Referer") == null ) {
  			msg = "";
  			return new ModelAndView("redirect:bodyParameterNotAllow");
  		}
  		
  		 String roleAccess = session.getAttribute("roleAccess").toString();
  		 Mmap.put("msg", msg);
  		 Mmap.put("getCourseNameList", p_comm.getCourseNameList());          
  		 Mmap.put("getGenderList", p_comm.getGenderList());
  		 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());       
  		 Mmap.put("getRegtList", p_comm.getRegtList(""));
  		 /*Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());*/
  		 Mmap.put("getTypeofRankList", p_comm.getrank_list("mns"));
  		 Mmap.put("getPersonalType", p_comm.getPersonalType());
  		 Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
  		 Mmap.put("getParentArmList", p_comm.getParentArmList());
  		Mmap.put("roleAccess",roleAccess);
  		

  		 return new ModelAndView("Update_Comm_Letter_MnsTiles");
  	 }
  	 ///----Start------Personnel No
  	 
  	@RequestMapping(value = "/admin/Personal_no_Action", method = RequestMethod.POST)
 	public @ResponseBody String Personal_no_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
 			throws ParseException {
  		
 		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
 		Transaction tx = sessionhql.beginTransaction();
 		
 		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
 		String username = session.getAttribute("username").toString();
 		
 		Date Date_authority = null;
        String authority = request.getParameter("p_authority");
     	String date_of_authority = request.getParameter("p_date_of_authority");

     	String persnl_no1 = request.getParameter("persnl_no1");
        String persnl_no2 = request.getParameter("persnl_no2");
        String persnl_no3 = request.getParameter("persnl_no3");
         
        String persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
        BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
 		String p_r_id = request.getParameter("p_r_id");	
 		String msg = "";
 		 
 		if(authority == null || authority.equals("null") || authority.equals("")) {
 			msg = "Please Enter Authority ";
 			return msg;
 		}
 		if (!validation.isValidAuth(authority)) {
	 		return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}	
 		if (persnl_no.equals("") || persnl_no == null || persnl_no.equals("null") ) {
 			msg = "Please Enter New Personal No";
 			return msg;
 		}
 		if (persnl_no2.equals("") || persnl_no2 == null || persnl_no2.equals("null") ) {
 			msg = "Please Enter New Personal No";
 			return msg;
 		}
 		if (validation.isOnlyNumer(persnl_no2) == true) {
 			return validation.isOnlyNumerMSG + " Personal No";	
		} 
 		if (persnl_no2.length() > 5) {
 			msg = "Personal No Should be Maximum 5 Characters";
 			return msg;
		}
 		String persuffix = p_comm.getPersonnelNuSuffix(persnl_no2);
		if (!persnl_no3.equals(persuffix)) {
			msg = "Please Enter Valid Personal No";
			return msg;
		}
 		
 		String Count =  CountOfPersonalNumberPendingUpdateOfficer(comm_id) ;
     	if(Integer.parseInt(Count) > 0) {
 			return "Data is Already Exists in Pending Status of SSC To Permanent(Update Data Officer) Please Approve that Data First.";
 		}
     	BigInteger c_id = BigInteger.ZERO;
     	if(request.getParameter("p_r_id") != null && !request.getParameter("p_r_id").equals(""))
     	{
     		c_id = new BigInteger(request.getParameter("p_r_id"));
     	}
     	new BigInteger(request.getParameter("p_r_id"));
		Boolean d = SLDAO.getPersonnelNoAlreadyExist(persnl_no,c_id);
		
 		try {
 			if (Integer.parseInt(p_r_id) == 0) {
 					if(Integer.parseInt(Count) == 0) {
 						TB_PSG_CHANGE_OF_COMISSION per = new TB_PSG_CHANGE_OF_COMISSION();
 						per.setNew_personal_no(persnl_no);
 						per.setComm_id(comm_id);
 						per.setAuthority(authority.trim());
 						per.setDate_of_authority(Date_authority);
 		                per.setCreated_by(username);
 		                per.setCreated_date(new Date());
 						per.setStatus(0);
 						
 						
 						if(d == false) {
 							return "Personal No Already Exist.";
 						}
 						if(d == true) {
 							BigInteger id = (BigInteger) sessionhql.save(per);
 	 						msg = id.toString();
 						}
 						
 					}
 			} else {
 			
 				String hql = " update TB_PSG_CHANGE_OF_COMISSION set authority=:authority,date_of_authority=:date_of_authority,new_personal_no=:persnl_no,"
 						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
 						+ " where  id=:id";
 				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority).setString("persnl_no",persnl_no)
 						.setString("modified_by", username)
 						.setTimestamp("modified_date", new Date())
 						.setInteger("status", 0)
 						.setBigInteger("id",new BigInteger(request.getParameter("p_r_id")));
 									
 				
 				    if(d == false) {
						return "Personal No Already Exist.";
					}
					if(d == true) {
						msg = query.executeUpdate() > 0 ? "update" : "0";
					}
 			}
 			String approoval_status = request.getParameter("app_status");
 			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
 			}
 			else
 			{
 				p_comm.update_comm_status(comm_id, username);
 			}
 			tx.commit();
 			}catch (RuntimeException e) {
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
 	
 	//----CHANDANI 16-7

 	public String CountOfPersonalNumberPendingUpdateOfficer(BigInteger comm_id ) {
 		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
 		Transaction tx = sessionHQL.beginTransaction();
 		String count = null;
 		try {
 			Query q1 = sessionHQL.createQuery("select count(comm_id) from TB_PSG_CHANGE_OF_COMISSION where status = '0' and comm_id=:comm_id and census_id > 0 ");			
 			q1.setParameter("comm_id", comm_id);
 			@SuppressWarnings("unchecked")
 			List<String> list = (List<String>) q1.list();			
 			if(list.size() > 0) {
 				count = String.valueOf(list.get(0));
 			}
 			tx.commit();
 		} catch (RuntimeException e) {			
 		} finally {
 			if (sessionHQL != null) {
 				sessionHQL.close();
 			}
 		}
 		return count;
 	}
  
	public String CountOfPersonalNumber(BigInteger comm_id ) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery("select count(comm_id) from TB_PSG_CHANGE_OF_COMISSION where status = '0' and comm_id=:comm_id");			
			q1.setParameter("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();			
			if(list.size() > 0) {
				count = String.valueOf(list.get(0));
			}
			tx.commit();
		} catch (RuntimeException e) {			
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}
	
	
	@RequestMapping(value = "/admin/get_PersonalNo1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> get_PersonalNo1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where comm_id=:comm_id and status='0' and census_id=0 ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 ///----End------Personnel No
	
	/* ----------- Start CadetNo ---------------*/
	@RequestMapping(value = "/admin/Cadet_no_Action", method = RequestMethod.POST)
	public @ResponseBody String Cadet_no_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
        String authority = request.getParameter("c_authority");
     	String date_of_authority = request.getParameter("c_date_of_authority");
   	    String cadet_no = request.getParameter("cadet_no");
		
		String c_id = request.getParameter("c_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		
		
		if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		}		 		 		
		if (!validation.isValidAuth(authority)) {
	 		return validation.isValidAuthMSG + "Authority ";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}	
		if (cadet_no.equals("") || cadet_no == null || cadet_no.equals("null")) {
			msg = "Please Enter Cadet No";
			return msg;
		}
		if (!validation.validateSlash(cadet_no)) {
	 		return validation.validateSlashMSG + "Cadet No";	
		}
		if (!validation.isvalidLength(cadet_no,validation.cadet_noMax,validation.cadet_noMin)) {
 			return "Cadet No " + validation.isValidLengthMSG;	
		}
		try {
			if (Integer.parseInt(c_id) == 0) {

				TB_PSG_UPDATE_COMM_CADET per = new TB_PSG_UPDATE_COMM_CADET();
				per.setComm_id(comm_id);
				per.setAuthority(authority.trim());
				per.setDate_of_authority(Date_authority);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);
				per.setCadet_no(cadet_no);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_COMM_CADET set authority=:authority,date_of_authority=:date_of_authority,cadet_no=:cadet_no,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority).setString("cadet_no",cadet_no)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("c_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
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
	@RequestMapping(value = "/admin/get_CadetNo1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_CADET> get_CadetNo1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_COMM_CADET where comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_CADET> list = (List<TB_PSG_UPDATE_COMM_CADET>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/* ----------- Start Course ---------------*/
	@RequestMapping(value = "/admin/Course_Action", method = RequestMethod.POST)
	public @ResponseBody String Course_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
        String authority = request.getParameter("co_authority");
     	String date_of_authority = request.getParameter("co_date_of_authority");
   	    String batch_no = request.getParameter("batch_no");
        String course = request.getParameter("course");
		String co_id = request.getParameter("co_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		
		
		 if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (!validation.isValidAuth(authority)) {
		 		return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if (batch_no == null || batch_no.equals("null") || batch_no.equals("")) {
			msg = "Please Enter Course No";
			return msg;
		}
		if (validation.isOnlyNumer(batch_no) == true) {
	 		return " Course No" + validation.isOnlyNumerMSG;	
		}
		if (!validation.isvalidLength(batch_no,validation.nameMax,validation.nameMin)) {
	 		return "Course No " + validation.isValidLengthMSG ;	
		}
		if (course == null || course.equals("null") || course.equals("") || course.equals("0")) {
			msg = "Please Select Course Comm";
			return msg;
		}
		
		try {
			if (Integer.parseInt(co_id) == 0) {

				TB_PSG_UPDATE_COMM_COURSE per = new TB_PSG_UPDATE_COMM_COURSE();
				per.setComm_id(comm_id);
				per.setAuthority(authority.trim());
				per.setDate_of_authority(Date_authority);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);
				per.setBatch_no(batch_no);
				per.setCourse(Integer.parseInt(course));
				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_COMM_COURSE set authority=:authority,date_of_authority=:date_of_authority,batch_no=:batch_no,course=:course,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setString("batch_no",batch_no).setInteger("course",Integer.parseInt(course))
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("co_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
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
	@RequestMapping(value = "/admin/get_CourseNo1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COURSE> get_CourseNo1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COURSE where comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COURSE> list = (List<TB_PSG_UPDATE_COMM_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/* ----------- Start Gender ---------------*/ 
	@RequestMapping(value = "/admin/Comm_Gender_Action", method = RequestMethod.POST)
	public @ResponseBody String Comm_Gender_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
       String authority = request.getParameter("g_authority");
    	String date_of_authority = request.getParameter("g_date_of_authority");
  	    String gender = request.getParameter("gender");
		
		String g_id = request.getParameter("g_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		
		
		 if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (!validation.isValidAuth(authority)) {
		 		return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if (gender.equals("") || gender == null || gender.equals("null") || gender.equals("0") ) {
			msg = "Please Select Gender";
			return msg;
		}
	
		try {
			if (Integer.parseInt(g_id) == 0) {

				TB_PSG_UPDATE_COMM_GENDER GN = new TB_PSG_UPDATE_COMM_GENDER();
				GN.setComm_id(comm_id);
				GN.setAuthority(authority);
				GN.setDate_of_authority(Date_authority);
				GN.setCreated_by(username);
				GN.setCreated_date(new Date());
				GN.setStatus(0);
				GN.setGender(Integer.parseInt(gender));

				int id = (int) sessionHQL.save(GN);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_COMM_GENDER set authority=:authority,date_of_authority=:date_of_authority,gender=:gender,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionHQL.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setInteger("gender", Integer.parseInt(gender))
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("g_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
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
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/get_Gender1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_GENDER> get_Gender1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_GENDER where comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_GENDER> list = (List<TB_PSG_UPDATE_COMM_GENDER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/* ----------- Start Commission ---------------*/
	@RequestMapping(value = "/admin/COMMISSION_Action", method = RequestMethod.POST)
	public @ResponseBody String COMMISSION_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		Date date_commission_dt = null;
		Date Date_authority = null;
		String authority = request.getParameter("com_authority");
     	String date_of_authority = request.getParameter("com_date_of_authority");

   	    String type_of_comm_granted = request.getParameter("type_of_comm_granted");
        String date_of_commission = request.getParameter("date_of_commission");
		
		String com_id = request.getParameter("com_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		
		
		 if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (!validation.isValidAuth(authority)) {
		 		return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if(type_of_comm_granted.equals("0") || type_of_comm_granted == null || type_of_comm_granted.equals("null") || type_of_comm_granted.equals("")) {
			msg = "Please Select Type of Commission Granted";
			return msg;
		}
		if (date_of_commission == null || date_of_commission.equals("null") ||date_of_commission.equals("") || date_of_commission.equals("DD/MM/YYYY")) {
			msg = "Please Enter Date of Commission ";
			return msg;
			
		}
		else if (!validation.isValidDate(date_of_commission)) {
 			return validation.isValidDateMSG + " of Commission";	
		}
		else {
			date_commission_dt = format.parse(date_of_commission);
		}
	
		try {
			if (Integer.parseInt(com_id) == 0) {

				TB_PSG_UPDATE_COMM_COMMISSION per = new TB_PSG_UPDATE_COMM_COMMISSION();
				per.setComm_id(comm_id);
				per.setType_of_comm_granted(Integer.parseInt(type_of_comm_granted));
				per.setDate_of_commission(date_commission_dt);
				per.setAuthority(authority);
				per.setDate_of_authority(Date_authority);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_COMM_COMMISSION set authority=:authority,date_of_authority=:date_of_authority,type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setInteger("type_of_comm_granted",Integer.parseInt(type_of_comm_granted))
						.setTimestamp("date_of_commission",date_commission_dt)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("com_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
			}
			
			tx.commit();
			}catch (RuntimeException e) {
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
	
	@RequestMapping(value = "/admin/get_Commission1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COMMISSION> get_Commission1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COMMISSION where comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COMMISSION> list = (List<TB_PSG_UPDATE_COMM_COMMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/* ----------- Start Birth ---------------*/
	
	@RequestMapping(value = "/admin/Birth_Action", method = RequestMethod.POST)
	public @ResponseBody String Birth_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		Date Date_birth = null;
		String authority = request.getParameter("authority");
     	String date_of_authority = request.getParameter("b_date_of_authority");
    	String date_of_birth = request.getParameter("date_of_birth");
		String birth_id = request.getParameter("birth_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		
		
		 if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (!validation.isValidAuth(authority)) {
		 		return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if (date_of_birth == null || date_of_birth.equals("") || date_of_birth.equals("DD/MM/YYYY") || date_of_birth.equals("")) {
			msg = "Please Enter Date of Birth ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_birth)) {
 			return validation.isValidDateMSG + " of Birth";	
		}
		else {
			Date_birth = format.parse(date_of_birth);
		}

		try {
			if (Integer.parseInt(birth_id) == 0) {

				TB_PSG_UPDATE_COMM_BIRTH per = new TB_PSG_UPDATE_COMM_BIRTH();
				per.setComm_id(comm_id);
				per.setAuthority(authority.trim());
				per.setDate_of_authority(Date_authority);
				per.setDate_of_birth(Date_birth);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_COMM_BIRTH set authority=:authority,date_of_authority=:date_of_authority,date_of_birth=:date_of_birth,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setString("modified_by", username)
						.setDate("date_of_birth", Date_birth)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("birth_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
			}
			
			tx.commit();
			}catch (RuntimeException e) {
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
  
	@RequestMapping(value = "/admin/get_Birth1", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_BIRTH> get_Birth1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_COMM_BIRTH where comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_BIRTH> list = (List<TB_PSG_UPDATE_COMM_BIRTH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/* ----------- Start Regiment ---------------*/
	
	@RequestMapping(value = "/admin/Regiment_Action", method = RequestMethod.POST)
	public @ResponseBody String Regiment_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
	
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		String authority = request.getParameter("authority");
	 	String date_of_authority = request.getParameter("arm_date_of_authority");
	
	    String parent_arm = request.getParameter("parent_arm");
	
	    String regiment="0";
	    if(request.getParameter("regiment") != null)
	    {
	    	regiment = request.getParameter("regiment");
	    }
		
		String reg_id = request.getParameter("reg_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";
		List<TB_TRANS_PROPOSED_COMM_LETTER> nn = p_comm.getcoomisionbyID(comm_id);
		
		
		if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		}
		if (!validation.isValidAuth(authority)) {
		 	return validation.isValidAuthMSG + "Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority" + validation.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}
		else {
			Date_authority = format.parse(date_of_authority);
		}
		if (parent_arm == null || parent_arm.equals("null") || parent_arm.equals("0") || parent_arm.equals("")) {
			msg = "Please Select Parent Arm ";
			return msg;
		} 
		if ((parent_arm.equals("0800") || parent_arm.equals("0700") ) && (regiment == null || regiment.equals("null") || regiment.equals("0") || regiment.equals(""))) {
			msg = "Please Select Regiment ";
			return msg;
		} 
		String Count= CountOfInterArmPendingUpdateOfficer(comm_id);
    	if(Integer.parseInt(Count) > 0) {
    		return "Data is Already Exists in Pending Status of Inter Arm Transfer(Update Data Officer) Please Approve that Data First.";
		}
		try {
			if (Integer.parseInt(reg_id) == 0) {
				
			
				
					if (Integer.parseInt(Count) == 0) {
						TB_INTER_ARM_TRANSFER per = new TB_INTER_ARM_TRANSFER();
						per.setComm_id(comm_id);
						per.setAuthority(authority.trim());
						per.setDate_of_authority(Date_authority);
						per.setParent_arm_service(parent_arm);
						per.setRegt(regiment);
						per.setWith_effect_from(nn.get(0).getDate_of_commission());
			            per.setCreated_by(username);
			            per.setCreated_date(new Date());
						per.setStatus(0);
			
						int id = (int) sessionhql.save(per);
						msg = Integer.toString(id);
			   }
					
			} else {
			
				String hql = " update TB_INTER_ARM_TRANSFER set authority=:authority,date_of_authority=:date_of_authority,parent_arm_service=:parent_arm,regt=:regiment,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setString("parent_arm",parent_arm)
						.setString("regiment",regiment)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("reg_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");
	
			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				p_comm.update_comm_status(comm_id, username);
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
	//----CHANDANI 16-7

	public String CountOfInterArmPendingUpdateOfficer(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery("select count(comm_id) from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and status='0' and census_id > 0");			
			q1.setParameter("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();			
			if(list.size() > 0) {
				count = String.valueOf(list.get(0));
			}
			tx.commit();
		} catch (RuntimeException e) {			
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}
	
	@RequestMapping(value = "/admin/get_Regiment", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER> get_Regiment(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and status='0' and census_id=0 ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
/* ----------- Start Unit ---------------*/
	@RequestMapping(value = "/admin/Unit_Action", method = RequestMethod.POST)
	public @ResponseBody String Unit_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
		throws ParseException {
	
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String username = session.getAttribute("username").toString();
			
			Date Date_authority = null;
			Date Tos_dt = null;
			String authority = request.getParameter("authority");
			String date_of_authority = request.getParameter("date_of_authority");
			
			String unit_sus_no = request.getParameter("unit_sus_no");
			String unit_posted_to = request.getParameter("unit_posted_to");
			String tos_date = request.getParameter("date_of_tos");  
			String unit_id = request.getParameter("unit_id");
			BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
			String msg = "";
			
			
			ArrayList<ArrayList<String>> list = scldao.gettenuredate(comm_id);
			
			if(list.size() > 0) {
				String tenure_dt = list.get(0).get(1);
				if (tenure_dt != null && format.parse(tos_date).compareTo(format.parse(tenure_dt)) > 0) {
					Mmap.put("msg", "Date of Tenure can be Less than Date of TOS or equal to.");
					return msg;
				}
			}							
			if(authority == null || authority.equals("null") || authority.equals("")) {
				 msg = "Please Enter Authority ";
				 return msg;
			}
			if (!validation.isValidAuth(authority)) {
		 		return validation.isValidAuthMSG + "Authority";	
			}
	 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
	 			return "Authority" + validation.isValidLengthMSG;	
			}
			if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				msg = "Please Enter Date of Authority ";
				return msg;
			} 
			else if (!validation.isValidDate(date_of_authority)) {
	 			return validation.isValidDateMSG + " of Authority";	
			}
			else {
				Date_authority = format.parse(date_of_authority);
			}
			 if(unit_sus_no == null || unit_sus_no.equals("null") || unit_sus_no.equals("")) {
				 msg = "Please Enter Unit Sus No ";
				 return msg;
			 }
			 if (!validation.isOnlyAlphabetNumeric(unit_sus_no)) {
			 	return validation.isOnlyAlphabetNumericMSG + "Unit Sus No";	
			 }
			 if (!validation.SusNoLength(unit_sus_no)) {
				 return validation.SusNoMSG;	
			 }
			 if(unit_posted_to == null || unit_posted_to.equals("null") || unit_posted_to.equals("")) {
				 msg = "Please Enter Unit Posted To ";
				 return msg;
			 }
			
			if (tos_date == null || tos_date.equals("null") || tos_date.equals("DD/MM/YYYY") || tos_date.equals("")) {
				msg = "Please Enter Date of Tos ";
				return msg;
			}
			else if (!validation.isValidDate(tos_date)) {
	 			return validation.isValidDateMSG + " of Tos";	
			}
			else {
				Tos_dt = format.parse(tos_date);
			}
			try {
				if (Integer.parseInt(unit_id) == 0) {
					/*Query q01 = sessionhql.createQuery("SELECT  CASE WHEN COUNT(id) > 1 THEN 1 ELSE 0 END AS count_id FROM TB_POSTING_IN_OUT where comm_id =:com_id" ); */
						
					/*q01.setBigInteger("com_id", comm_id); */
					/*Integer c1 = (Integer) q01.uniqueResult();*/
					TB_PSG_UPDATE_COMM_UNIT per = new TB_PSG_UPDATE_COMM_UNIT();
					per.setComm_id(comm_id);
					per.setAuthority(authority.trim());
					per.setDate_of_authority(Date_authority);
					per.setUnit_post_to(unit_posted_to);
					per.setDate_of_tos(Tos_dt);
					per.setUnit_sus_no(unit_sus_no);
			        per.setCreated_by(username);
			        per.setCreated_date(new Date());
					per.setStatus(0);
					int id = (int) sessionhql.save(per);
					msg = Integer.toString(id);

					/*if(c1==0)
			{
							}else {
				msg="Already Posting in/out is Done ";
				return msg;
			} */
					
				} else {
				
					String hql = " update TB_PSG_UPDATE_COMM_UNIT set authority=:authority,date_of_authority=:date_of_authority,unit_sus_no=:unit_sus_no,unit_post_to=:unit_post_to,date_of_tos=:date_of_tos,"
							+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
							+ " where  id=:id";
					Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
							.setString("unit_sus_no",unit_sus_no).setString("unit_post_to",unit_posted_to)
							.setDate("date_of_tos", Tos_dt)
							.setString("modified_by", username)
							.setTimestamp("modified_date", new Date())
							.setInteger("status", 0)
							.setInteger("id",Integer.parseInt(request.getParameter("unit_id")));
										
					msg = query.executeUpdate() > 0 ? "update" : "0";
					Mmap.put("msg", "Data Updated Successfully");
			
				}
				
				String approoval_status = request.getParameter("app_status");
				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
					
				}
				else
				{
					p_comm.update_comm_status(comm_id, username);
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
				
		@RequestMapping(value = "/admin/get_Unit1", method = RequestMethod.POST)
		public @ResponseBody List<TB_PSG_UPDATE_COMM_UNIT> get_Unit1(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		
			String hqlUpdate = " from TB_PSG_UPDATE_COMM_UNIT where comm_id=:comm_id and status='0'";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_UPDATE_COMM_UNIT> list = (List<TB_PSG_UPDATE_COMM_UNIT>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/GetCommissionDataApprove", method = RequestMethod.POST)

		public @ResponseBody ArrayList<ArrayList<String>> GetCommissionDataApprove(BigInteger comm_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			ArrayList<ArrayList<String>> list = UCO.GetCommissionDataApprove(comm_id);
			tx.commit();
			return list;

		}
		

		//bisag 290323 v2(new enhancement)
		

@RequestMapping(value = "/admin/RANK_Action", method = RequestMethod.POST)
		public @ResponseBody String RANK_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {
	
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String username = session.getAttribute("username").toString();
			Date date_commission_dt = null;
			Date Date_authority = null;
			String authority = request.getParameter("r_authority");
	   	        String type_of_rank =request.getParameter("rank");
	   	    	String date_of_authority = request.getParameter("rdate_of_authority");
	       	String date_of_rank = request.getParameter("dt_rk");
			String dateOfComission=request.getParameter("dt_commission");
			String rank_id = request.getParameter("rank_id");
			BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
			String msg = "";
			
			
			
			
			 if(authority == null || authority.equals("null") || authority.equals("")) {
			 msg = "Please Enter Authority "; return msg; }
			 
			 if (!validation.isValidAuth(authority)) { return validation.isValidAuthMSG +
			 "Authority"; } if
			 (!validation.isvalidLength(authority,validation.authorityMax,validation.
			 authorityMin)) { return "Authority" + validation.isValidLengthMSG; }
			 
			if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
				msg = "Please Enter Date of Authority ";
				return msg;
			} 
			else if (!validation.isValidDate(date_of_authority)) {
	 			return validation.isValidDateMSG + " of Authority";	
			}
			else {
				Date_authority = format.parse(date_of_authority);
			}
			if(type_of_rank.equals("0") || type_of_rank == null || type_of_rank.equals("null") || type_of_rank.equals("")) {
				msg = "Please Select Type of Commission Granted";
				return msg;
			}
			if (date_of_rank == null || date_of_rank.equals("null") ||date_of_rank.equals("") || date_of_rank.equals("DD/MM/YYYY")) {
				msg = "Please Enter Date of Rank ";
				return msg;
				
			}
			else if (!validation.isValidDate(date_of_rank)) {
	 			return validation.isValidDateMSG + " of Rank";	
			}
			else {
				date_commission_dt = format.parse(date_of_rank);
			}
			
			
		/*	else {
				String dt_commission1=null;
		        String dt_commission2=null;
		        String dt_commission3=null;
		        String dt_rank1 =null;
				String dt_rank2 = null;
				String dt_rank3 = null;
			
				if (dateOfComission!=null && !dateOfComission.trim().equals("") && !dateOfComission.equals("DD/MM/YYYY") ) {

					 dt_commission1 = dateOfComission.substring(6, 10);
					 dt_commission2 = dateOfComission.substring(3, 5);
					 dt_commission3 = dateOfComission.substring(0, 2);
					 dt_rank1 = date_of_rank.substring(6, 10);
					 dt_rank2 = date_of_rank.substring(3, 5);
					 dt_rank3= date_of_rank.substring(0, 2);
						if (Integer.parseInt(dt_rank1) <Integer.parseInt(dt_commission1) ||   Integer.parseInt(dt_rank2) <Integer.parseInt(dt_commission2)||Integer.parseInt(dt_rank3) <Integer.parseInt(dt_commission3)) {
							msg = "Date of Rank should be greater/equals to Date of Commission";
							return msg;
						}
						else {
							date_commission_dt = format.parse(date_of_rank);
						}
				}
			
			}*/
		
			
			try {
				if (Integer.parseInt(rank_id) == 0) {
					
					TB_CHANGE_OF_RANK per = new TB_CHANGE_OF_RANK();
					per.setDate_of_authority(Date_authority);
					per.setRank(Integer.parseInt(type_of_rank));
					per.setComm_id(comm_id);
					per.setModified_by(username);
					per.setModified_date(new Date());
					per.setDate_of_rank(date_commission_dt);
					per.setAuthority(authority);
					
	                per.setCreated_by(username);
	                per.setCreated_date(new Date());
	                
					per.setStatus(0);

					int id = (int) sessionhql.save(per);
					msg = Integer.toString(id);
				} 
			
				else {
				
					String hql = " update TB_CHANGE_OF_RANK set  authority=:authority, date_of_authority=:date_of_authority,rank=:rank,"
							+"date_of_rank=:date_of_rank,"
							+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
							+ " where  id=:id";
					Query query = sessionhql.createQuery(hql).setString("authority",authority).setDate("date_of_authority", Date_authority)
							.setInteger("rank",Integer.parseInt(type_of_rank))
							.setTimestamp("date_of_rank",date_commission_dt)
							.setString("modified_by", username)
							.setTimestamp("modified_date", new Date())
							.setInteger("status", 0)
							.setInteger("id",Integer.parseInt(request.getParameter("rank_id")));
										
					msg = query.executeUpdate() > 0 ? "update" : "0";
					Mmap.put("msg", "Data Updated Successfully");

				}
				String approoval_status = request.getParameter("app_status");
				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				}
				else
				{
					p_comm.update_comm_status(comm_id, username);
				}
				
				tx.commit();
				}catch (RuntimeException e) {
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

@RequestMapping(value = "/admin/get_cda_account_no", method = RequestMethod.POST)
public @ResponseBody List<TB_CDA_ACC_NO> get_cda_account_no(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

	String hqlUpdate = " from TB_CDA_ACC_NO where comm_id=:comm_id and status='0'";
	Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
	@SuppressWarnings("unchecked")
	List<TB_CDA_ACC_NO> list = (List<TB_CDA_ACC_NO>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}


@RequestMapping(value = "/admin/get_seniortiy", method = RequestMethod.POST)
public @ResponseBody List<TB_PSG_CHANGE_OF_SENIORITY> get_seniortiy(ModelMap Mmap, HttpSession session,
		HttpServletRequest request) throws ParseException {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

	String hqlUpdate = " from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status='0'";
	Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
	@SuppressWarnings("unchecked")
	List<TB_PSG_CHANGE_OF_SENIORITY> list = (List<TB_PSG_CHANGE_OF_SENIORITY>) query.list();
	tx.commit();
	sessionHQL.close();
	return list;
}

		
}
