package com.controller.psg.update_3008;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Change_of_Commission_3008 {
	
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
	
	
	@Autowired

	CensusAprovedDAO censusAprovedDAO;
	
	@RequestMapping(value = "/admin/COMMISSION_Action_3008", method = RequestMethod.POST)
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
		Date comm_date = format.parse(request.getParameter("comm_date"));
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
		
		if (p_comm.CompareDate(Date_authority, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
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
				per.setStatus(1);
				per.setModified_by(username);
                per.setModified_date(new Date());
                
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
						.setInteger("status", 1)
						.setInteger("id",Integer.parseInt(request.getParameter("com_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				//Mmap.put("msg", "Data Approve Successfully");

			}
			int count = censusAprovedDAO.checkdatapendingintablecomm(comm_id, "comm_commission");
			String hql;		
			if (count == 0) {
				 hql = "update TB_TRANS_PROPOSED_COMM_LETTER set type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,modified_by=:modified_by,modified_date=:modified_date,update_comm_status=:update_comm_status "
							+ "where id=:comm_id and status = '1'  ";
					Query query = sessionhql.createQuery(hql).setInteger("type_of_comm_granted", Integer.parseInt(type_of_comm_granted)).setTimestamp("date_of_commission",date_commission_dt)
							.setString("modified_by", username).setTimestamp("modified_date", new Date())
							.setBigInteger("comm_id", comm_id).setInteger("update_comm_status", 1);
					msg = query.executeUpdate() > 0 ? "update" : "0";
			} else {
				 hql = "update TB_TRANS_PROPOSED_COMM_LETTER set type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,modified_by=:modified_by,modified_date=:modified_date "
						+ "where id=:comm_id and status = '1'  ";
				Query query = sessionhql.createQuery(hql).setInteger("type_of_comm_granted", Integer.parseInt(type_of_comm_granted)).setTimestamp("date_of_commission",date_commission_dt)
						.setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setBigInteger("comm_id", comm_id);
				msg = query.executeUpdate() > 0 ? "update" : "0";
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

}
