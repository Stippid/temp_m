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
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Inter_Arm_Tranfer_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.update_census_data.SeniorityDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_date_of_sen_3008 {
	@Autowired
	SeniorityDAO SD;
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	PsgDashboardController das = new PsgDashboardController();
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Seniority_Details_Action_3008", method = RequestMethod.POST)
	public @ResponseBody String Seniority_Details_Action_3008(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		String authority = request.getParameter("authority");
		String reason = request.getParameter("reason");
		String date_of_authority = request.getParameter("date_of_authority");
		String date_of_seniority = request.getParameter("date_of_seniority");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String date_of_applicability = request.getParameter("date_of_applicability");		
		Date authority_date = null;
		Date seniority_date = null;
		Date applicability_date = null;
		String Sen_id = request.getParameter("Sen_id");
		

        int census_id=0;
		String censusIdParameter = request.getParameter("census_id");
		if (censusIdParameter != null && !censusIdParameter.isEmpty()) {
		    census_id = Integer.parseInt(censusIdParameter);		    
		}



		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		
		
		if (authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!valid.isValidAuth(authority)) {
	 		return valid.isValidAuthMSG + " Authority ";	
		}
 		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return " Authority " + valid.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			authority_date = format.parse(date_of_authority);
		}
		
		if (p_comm.CompareDate(authority_date, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
					   
		if (date_of_seniority == null || date_of_seniority.equals("null") || date_of_seniority.equals("DD/MM/YYYY")
				|| date_of_seniority.equals("")) {
			return "Please Enter New Seniority Date";
		}
		if (!valid.isValidDate(date_of_seniority)) {
 			return valid.isValidDateMSG + " of New Seniority";	
		}
		if (!date_of_seniority.equals("") && !date_of_seniority.equals("DD/MM/YYYY")) {
			seniority_date = format.parse(date_of_seniority);
		}
		if (reason == null || reason.equals("")) {
			return "Please Enter Reason";
		}
		if (!valid.isValidAuth(reason)) {
	 		return valid.isValidAuthMSG + " Reason ";	
		}
 		if (!valid.isvalidLength(reason,valid.authorityMax,valid.authorityMin)) {
 			return " Reason " + valid.isValidLengthMSG;	
		}
		if (date_of_applicability == null || date_of_applicability.equals("null")
				|| date_of_applicability.equals("DD/MM/YYYY") || date_of_applicability.equals("")) {
			return "Please Select Effective Date of Seniority";
		} 
		else if (!valid.isValidDate(date_of_applicability)) {
 			return valid.isValidDateMSG + " From Which Change in Seniority is Effective";	
		}
		else {
			applicability_date = format.parse(date_of_applicability);
		}
	
		
	
		String hqlUpdate_s2 = "select count(id) from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status in (1,2) and applicablity_date=:eff_date_of_seniority";
		Query query_s2 = sessionhql.createQuery(hqlUpdate_s2).setBigInteger("comm_id", comm_id)
				.setTimestamp("eff_date_of_seniority", format.parse(date_of_applicability)).setMaxResults(1);
		Long c22 = (Long) query_s2.uniqueResult();
		if (c22 != null && c22 > 0) {
			return " Effective Date of Seniority Is Already Exist. ";
		}
		String msg = "";
		try {
			if (Integer.parseInt(Sen_id) == 0) {
				TB_PSG_CHANGE_OF_SENIORITY cs = new TB_PSG_CHANGE_OF_SENIORITY();
				cs.setCensus_id(census_id);
				cs.setComm_id(comm_id);
				cs.setAuthority(authority);
				cs.setReason(reason);
				cs.setDate_of_authority(format.parse(date_of_authority));
				cs.setDate_of_seniority(format.parse(date_of_seniority));
				cs.setApplicablity_date(applicability_date);
				cs.setCreated_by(username);
				cs.setCreated_date(new Date());
				cs.setStatus(1);
				cs.setModified_by(username);
				cs.setModified_date(new Date());
				int id = (int) sessionhql.save(cs);
				msg = String.valueOf(id);
			} else {
				String hql = "update TB_PSG_CHANGE_OF_SENIORITY set authority=:authority,date_of_authority=:date_of_authority,applicablity_date=:applicablity_date,"
						+ "date_of_seniority=:date_of_seniority,reason=:reason,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority)
						.setDate("date_of_authority", authority_date).setDate("applicablity_date", applicability_date)
						.setDate("date_of_seniority", seniority_date).setInteger("id", Integer.parseInt(Sen_id))
						.setString("reason", reason).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 1);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			p_comm.update_miso_role_hdr_status(comm_id, 1, username, "seniority_status");
			tx.commit();
		} catch (RuntimeException | java.text.ParseException e) {
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


}
