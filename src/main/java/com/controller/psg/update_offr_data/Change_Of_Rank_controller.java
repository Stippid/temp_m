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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_Of_Rank_controller {

	Psg_CommonController pcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	/*--------------------- Start Change of Rank----------------------------------*/
	
	@RequestMapping(value = "/admin/Change_of_Rank_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Rank_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Date dt_authority = null;
		Date dt_rank = null;
		String username = session.getAttribute("username").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String authority = request.getParameter("authority");
		String date_of_authority = request.getParameter("date_of_authority");
		String rank_type = request.getParameter("rank_type");
		String rank = request.getParameter("rank");
		String date_of_rank = request.getParameter("date_of_rank");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String ch_r_id = request.getParameter("ch_r_id");
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id_cr")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id_cr"));
		}

		BigInteger comm_id = BigInteger.ZERO;
		/// bisag 241122 V2 (change comm_id int to big int)
		
		if (!request.getParameter("comm_id_cr").equals("0") && !request.getParameter("comm_id_cr").equals("null") && !request.getParameter("comm_id_cr").equals("")) {
			comm_id = new BigInteger(request.getParameter("comm_id_cr"));
		}
		if (authority == null || authority.equals("") || authority.equals("null")) {
			return "Please Enter Authority.";
		}
		if (!validation.isValidAuth(authority)) {
	 		return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}		
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			return "Please Select Date Of Authority.";
		}		
		if (!validation.isValidDate(date_of_authority)) {
 			return validation.isValidDateMSG + " of Authority";	
		}		
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			dt_authority = format.parse(date_of_authority);
		}
		if (pcommon.CompareDate(dt_authority,comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		if (rank_type == null || rank_type.equals("0") || rank_type.equals("null")) {
			return "Please Select Rank Type.";
		}
		if (rank == null || rank.equals("0") || rank.equals("null")) {
			return "Please Select Rank.";
		}
		if (date_of_rank == null || date_of_rank.equals("null") || date_of_rank.equals("DD/MM/YYYY") || date_of_rank.equals("")) {
			return "Please Select Date Of Rank.";
		}
		if (!validation.isValidDate(date_of_rank)) {
 			return validation.isValidDateMSG + " of Rank";	
		}
		if (!date_of_rank.equals("") && !date_of_rank.equals("DD/MM/YYYY")) {
			dt_rank = format.parse(date_of_rank);
		}

		String msg = "";
		try {
			 Query q0 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK where date_of_rank=:date_of_rank and comm_id=:comm_id and  id!=:id and status!=-1").setTimestamp("date_of_rank", dt_rank)
					 .setParameter("id", Integer.parseInt(ch_r_id)).setParameter("comm_id", comm_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "Date Of Rank Already Exists";
				}

			if (Integer.parseInt(ch_r_id) == 0) {
				TB_CHANGE_OF_RANK cr = new TB_CHANGE_OF_RANK();

				cr.setAuthority(authority);
				cr.setDate_of_authority(dt_authority);
				cr.setRank_type(Integer.parseInt(rank_type));
				cr.setRank(Integer.parseInt(rank));
				cr.setDate_of_rank(dt_rank);
				cr.setCensus_id(census_id);
				cr.setComm_id(comm_id);
				cr.setCreated_by(username);
				cr.setCreated_date(date);
				cr.setStatus(0);

				int id = (int) sessionhql.save(cr);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
						+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority)
						.setDate("date_of_authority", dt_authority).setInteger("rank_type", Integer.parseInt(rank_type))
						.setInteger("rank", Integer.parseInt(rank)).setDate("date_of_rank", dt_rank)
						.setInteger("id", Integer.parseInt(ch_r_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
				pcommon.update_offr_status(census_id,username);
			
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

		return msg;
	}

	
	
	
	@RequestMapping(value = "/admin/getChangeOfRankData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK> getChangeOfRankData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("ch_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	/*--------------------- End Change of Rank----------------------------------*/

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CHANGE_OF_RANK> getChangeOfRankData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Change_of_Rank(TB_CHANGE_OF_RANK obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql1 = "update TB_CHANGE_OF_RANK set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CHANGE_OF_RANK set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public String Update_Comm_Rank(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank  "
					+ " where id=:comm_id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setBigInteger("comm_id", obj.getId())
					.setInteger("rank", obj.getRank()).setDate("date_of_rank", obj.getDate_of_rank());

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

	/*--------------------- For REJECT ----------------------------------*/
	
	@RequestMapping(value = "/admin/getChangeOfRankReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfRankReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CHANGE_OF_RANK ChangeRank = new TB_CHANGE_OF_RANK();
		ChangeRank.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeRank.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeRank.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeRank.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_Rank_Reject(ChangeRank, username);

		return msg1;

	}
	


	public String Update_Change_of_Rank_Reject(TB_CHANGE_OF_RANK obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		//String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CHANGE_OF_RANK set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CHANGE_OF_RANK> getChangeOfRankData2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getChangeOfRankData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK> getChangeOfRankData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("ch_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/ChngofRank_delete_action", method = RequestMethod.POST)
	public @ResponseBody String ChngofRank_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_OF_RANK where id=:id";
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
