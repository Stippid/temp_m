package com.controller.psg.update_3008;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.Search_cdaDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_of_cda_accountno_3008 {
	@Autowired
	Search_cdaDAO CD;
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	PsgDashboardController das = new PsgDashboardController();
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;

		@RequestMapping(value = "/admin/Change_of_cda_action_3008", method = RequestMethod.POST)
	public @ResponseBody String Change_of_cda_action_3008(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException, java.text.ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date dt_authority = null;
        String authority = request.getParameter("co_authority");
     	String date_of_authority = request.getParameter("co_date_of_authority");
     	String co_id = request.getParameter("co_id");
     	String cda_acc_no = request.getParameter("cda_acc_no");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String msg = "";
		
		//int census_id = Integer.parseInt(request.getParameter("census_id"));	
		

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
			dt_authority = format.parse(date_of_authority);
		}
		
		if (p_comm.CompareDate(dt_authority, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		
		if (cda_acc_no == null || cda_acc_no.equals("0")) {
			return "Please Select CDA Account No.";
		}
		
		if (!valid.validateSlash(cda_acc_no)) {
			return valid.validateSlashMSG + " CDA Account No";
		}

		if (cda_acc_no.length() > 20) {
		    return "CDA Account No Should Contain Maximum 20 Characters";
		}

		try {
			if (Integer.parseInt(co_id) == 0) {
				Query q0 = sessionhql.createQuery(
						"select count(id) from TB_CDA_ACC_NO where comm_id=:comm_id and cda_acc_no=:cda_acc_no and status <> -1");
				q0.setParameter("comm_id", comm_id);
				q0.setParameter("cda_acc_no", cda_acc_no);
				Long c1 = (Long) q0.uniqueResult();
				if (c1 > 0) {
					return "Data already Exist.";
				}else {
					
//					pranay (18.09)
					
					String hqlUpdate0 = "update TB_CDA_ACC_NO set status=:status,modified_by=:modified_by where comm_id=:id and  (status != '0' and status != '-1')";
					int app0 = sessionhql.createQuery(hqlUpdate0).setInteger("status", 2).setString("modified_by", username)
							.setBigInteger("id", comm_id).executeUpdate();
					
					
					
					TB_CDA_ACC_NO cd = new TB_CDA_ACC_NO();
					cd.setCda_acc_no(cda_acc_no);				
					cd.setComm_id(comm_id);
					cd.setCreated_by(username);
					cd.setCreated_date(date);
					cd.setModified_by(username);
					cd.setModified_date(date);
					cd.setStatus(1);	
					cd.setAuthority(authority);
					cd.setDate_of_authority(dt_authority);
					int id = (int) sessionhql.save(cd);
					msg = Integer.toString(id);	
				}
				
			} else {
			
				String hql = "update TB_CDA_ACC_NO set cda_acc_no=:cda_acc_no,"
						+ "modified_by=:modified_by,modified_date=:modified_date,authority=:authority,date_of_authority=:date_of_authority,status=:status  " + " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("cda_acc_no", cda_acc_no)
						.setInteger("id", Integer.parseInt(co_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 1).setTimestamp("date_of_authority",dt_authority).setString("authority", authority);
				msg = query.executeUpdate() > 0 ? "update" : "Data Approved Not Successfully";		

			}
			
			p_comm.update_miso_role_hdr_status(comm_id, 1, username, "cda_status");
			
			
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
}
