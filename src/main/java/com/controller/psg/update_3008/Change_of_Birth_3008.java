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
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Change_of_Birth_3008 {
	
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
	

	@RequestMapping(value = "/admin/Birth_Action_3008", method = RequestMethod.POST)
	public @ResponseBody String Birth_Action_3008(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
				per.setStatus(1);
                per.setModified_by(username);
				per.setModified_date(new Date());
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
						.setInteger("status", 1)
						.setInteger("id",Integer.parseInt(request.getParameter("birth_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				//Mmap.put("msg", "Data Approved Successfully");

			}
			
			int count = censusAprovedDAO.checkdatapendingintablecomm(comm_id, "comm_birth");
			String hql;		
			if (count == 0) {

				hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,date_of_birth=:date_of_birth,update_comm_status =:update_comm_status  "
						+ " where id=:comm_id ";		

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
						.setDate("date_of_birth", Date_birth)
						.setInteger("update_comm_status", 1);

				msg = query.executeUpdate() > 0 ? "update" : "0";
			} else {
				hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,date_of_birth=:date_of_birth"
                        + " where id=:comm_id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
						.setDate("date_of_birth", Date_birth);
					
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
