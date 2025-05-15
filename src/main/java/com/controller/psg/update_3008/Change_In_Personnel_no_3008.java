package com.controller.psg.update_3008;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_Personnel_no_3008 {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Report_3008DAO report_3008DAO;

	@Autowired
	private Search_PostOutDao pod;	
		

	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	Psg_CommonController common = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;
	
	 @RequestMapping(value = "/admin/change_in_perosonnel_no_3008", method = RequestMethod.POST)
	 public ModelAndView change_in_perosonnel_no_3008(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id2", required = false) BigInteger comm_id,		
			 HttpServletRequest request) {
		 
		 if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 Mmap.put("getPersonalType", common.getPersonalType());
  		 Mmap.put("getPersonalRemainder", common.getPersonalRemainder());
		     Mmap.put("comm_id",comm_id);
		     Mmap.put("msg", msg);
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
try {
		     String hql6 = "select TO_CHAR(comm.date_of_commission, 'DD/MM/YYYY') AS date_of_commission   from TB_TRANS_PROPOSED_COMM_LETTER comm "	    			
	    				+ "where  comm.id=:comm_id and comm.status in('1','5')";
				Query query6 = sessionhql.createQuery(hql6);
				query6.setBigInteger("comm_id",comm_id);
				List <String>list6 = query6.list();
				if(!list6.isEmpty())
				{
					   Mmap.put("comm_date",list6.get(0));
				}
}
	catch (RuntimeException e) {
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

			 return new ModelAndView("change_in_perosonnel_no_3008_Tiles");
		
	 }
	 @RequestMapping(value = "/admin/Change_in_Personal_no_Action_3008", method = RequestMethod.POST)
	 	public @ResponseBody String Change_in_Personal_no_Action_3008(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
	 		String persuffix = common.getPersonnelNuSuffix(persnl_no2);
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
	 			String hql = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status,cancel_status=:cancel_status  "
						+ " where comm_id=:comm_id and status = '1'";
				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", -1).setParameter("cancel_status", 1)
						.setBigInteger("comm_id", comm_id);
				msg = query.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
	 			if (Integer.parseInt(p_r_id) == 0) {
	 					if(Integer.parseInt(Count) == 0) {
	 						TB_PSG_CHANGE_OF_COMISSION per = new TB_PSG_CHANGE_OF_COMISSION();
	 						per.setNew_personal_no(persnl_no);
	 						per.setComm_id(comm_id);
	 						per.setAuthority(authority.trim());
	 						per.setDate_of_authority(Date_authority);
	 		                per.setCreated_by(username);
	 		                per.setCreated_date(new Date());
	 						per.setStatus(1);
	 						
	 						
	 						if(d == false) {
	 							return "Personal No Already Exist.";
	 						}
	 						if(d == true) {
	 							BigInteger id = (BigInteger) sessionhql.save(per);
	 	 						msg = id.toString();
	 						}
	 						
	 					}
	 			} else {
	 			
	 				String hql2= " update TB_PSG_CHANGE_OF_COMISSION set authority=:authority,date_of_authority=:date_of_authority,new_personal_no=:persnl_no,"
	 						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
	 						+ " where  id=:id";
	 				Query query2 = sessionhql.createQuery(hql2).setString("authority", authority).setDate("date_of_authority", Date_authority).setString("persnl_no",persnl_no)
	 						.setString("modified_by", username)
	 						.setTimestamp("modified_date", new Date())
	 						.setInteger("status", 1)
	 						.setBigInteger("id",new BigInteger(request.getParameter("p_r_id")));
	 									
	 				
	 				    if(d == false) {
							return "Personal No Already Exist.";
						}
						if(d == true) {
							msg = query2.executeUpdate() > 0 ? "update" : "0";
						}
	 			}
//	 			String approoval_status = request.getParameter("app_status");
//	 			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
//	 			}
//	 			else
//	 			{
//	 				p_comm.update_comm_status(comm_id, username);
//	 			}
	 			String hql3 = "update TB_TRANS_PROPOSED_COMM_LETTER set personnel_no=:personnel_no,modified_by=:modified_by,modified_date=:modified_date "
						+ "where id=:comm_id and status = '1'  ";
				Query query3 = sessionhql.createQuery(hql3).setString("personnel_no", persnl_no)
						.setString("modified_by", username).setTimestamp("modified_date",new Date())
						.setBigInteger("comm_id", comm_id);
				msg = query3.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
	 			
	 			
	 			
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
		
	
}
