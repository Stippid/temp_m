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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_of_name_3008 {

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	@Autowired

	CensusAprovedDAO censusAprovedDAO;

	@RequestMapping(value = "/admin/change_of_name_GetData_3008", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME> change_of_name_GetData_3008(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_NAME where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// save and update change name
	@RequestMapping(value = "/admin/change_of_name_3008_action", method = RequestMethod.POST)
	public @ResponseBody String change_of_name_3008_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;

		String username = session.getAttribute("username").toString();
		String authority = request.getParameter("authority");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		Date change_in_name_dt = null;

		String change_in_name_date = request.getParameter("change_in_name_date");

		String date_of_authority = request.getParameter("date_of_authority");

		String name = request.getParameter("name");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		//int census_id = Integer.parseInt(request.getParameter("census_id"));
		int census_id=0;
		String censusIdParameter = request.getParameter("census_id");
		if (censusIdParameter != null && !censusIdParameter.isEmpty()) {
		    census_id = Integer.parseInt(censusIdParameter);		    
		}
		int name_id = Integer.parseInt(request.getParameter("name_id"));
		String msg = "";

		if (authority == null || authority.equals("")) {
			return "Please Enter Authority ";
		}
		if (!validation.isValidAuth(authority)) {
			return validation.isValidAuthMSG + " Authority";
		}
		if (!validation.isvalidLength(authority, validation.authorityMax, validation.authorityMin)) {
			return "Authority " + validation.isValidLengthMSG;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!validation.isValidDate(date_of_authority)) {
			return validation.isValidDateMSG + " of Authority";
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			dt_authority = format.parse(date_of_authority);
		}
		if (com.CompareDate(dt_authority, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		if (name == null || name.equals("")) {
			return "Please Enter Name";
		}
		if (!validation.isOnlyAlphabet(name)) {
			return validation.isOnlyAlphabetMSG + " Name";
		}
		if (!validation.isvalidLength(name, validation.nameMax, validation.nameMin)) {
			return "Name " + validation.isValidLengthMSG;
		}
		if (change_in_name_date == null || change_in_name_date.equals("null")
				|| change_in_name_date.equals("DD/MM/YYYY") || change_in_name_date.equals("")) {
			return "Please Select Date";
		}
		if (!change_in_name_date.equals("") && !change_in_name_date.equals("DD/MM/YYYY")) {
			change_in_name_dt = format.parse(change_in_name_date);
		}

		try {
			String hql1 = "update TB_CHANGE_NAME set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", comm_id);

			msg = query1.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";

			if (name_id == 0) {
				TB_CHANGE_NAME n = new TB_CHANGE_NAME();
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setAuthority(authority);
				n.setName(name);
				n.setDate_of_authority(dt_authority);
				n.setComm_id(comm_id);
				n.setCensus_id(census_id);
				n.setStatus(1);
				n.setCreated_by(username);
				n.setCreated_date(date);
				n.setChange_in_name_date(change_in_name_dt);
				n.setModified_by(username);
				n.setModified_date(date);
			
				int id = (int) sessionhql.save(n);
				msg = Integer.toString(id);
			} else {
				String hql = " update TB_CHANGE_NAME set authority=:authority ,date_of_authority=:date_of_authority"
						+ " ,name=:name,status=:status,modified_by=:modified_by,modified_date=:modified_date,"
						+ " change_in_name_date=:change_in_name_date" + " where  id=:id";
				Query query = sessionhql.createQuery(hql)

						.setString("authority", authority).setTimestamp("date_of_authority", dt_authority)
						.setString("name", name).setInteger("status", 1).setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setTimestamp("change_in_name_date", change_in_name_dt).setInteger("id", name_id);

				msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
				Mmap.put("msg", "Data Updated Successfully");
			}
			
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,name=:name "
					+ " where id=:comm_id ";

			Query query = sessionhql.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
					.setString("name", name);
			msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
			
			Query query3 = sessionhql.createQuery("update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by, modified_date=:modified_date, first_name=:name, last_name=:last_name, middle_name=:middle_name "
                    + "where comm_id=:comm_id")
                    .setString("modified_by", username)
                    .setTimestamp("modified_date", new Date())
                    .setBigInteger("comm_id",comm_id)
                    .setString("name", name)
                    .setString("last_name", "")
                    .setString("middle_name", "");
			query3.executeUpdate();
			
		
					
				int count1 = censusAprovedDAO.checkdatapendingintable(comm_id, "name");
				if (count1 == 0) {

					String hql2 = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
							+ " where comm_id=:comm_id ";
					Query query2 = sessionhql.createQuery(hql2).setBigInteger("comm_id", comm_id)
							.setInteger("update_ofr_status", 1);
				 query2.executeUpdate();
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
}
