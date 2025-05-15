package com.controller.psg.Jco_Update_Census;

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
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
 

public class Rank_UpdateController {
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	
	@RequestMapping(value = "/admin/Rank_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Rank_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		String rank_authority = request.getParameter("rank_authority").trim();
     	String rank_date_of_authority = request.getParameter("rank_date_of_authority").trim();
     	String rank = request.getParameter("rank");
     	String r_id = request.getParameter("r_id");
		String jco_id = request.getParameter("jco_id");
		String rank_intake = request.getParameter("rank_intake");
		
		String msg = "";
		 if(rank_authority == null || rank_authority.equals("null") || rank_authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (rank_date_of_authority == null || rank_date_of_authority.equals("") || rank_date_of_authority.equals("DD/MM/YYYY") || rank_date_of_authority.equals("")) {
				msg = "Please Enter Date of Authority ";
				return msg;
			} else {
				Date_authority = format.parse(rank_date_of_authority);
			}
			if (rank.equals("0") || rank == null || rank.equals("null")) {
				msg = "Please Select Rank";
				return msg;
			}
			
			if (request.getParameter("rank").equals("17")) {
				if (request.getParameter("rank_intake") == null || request.getParameter("rank_intake").trim().equals("")) {
					Mmap.put("msg", "Please Select Rank Intake");
					return msg;
				}
			}
			
		try {
			if (Integer.parseInt(r_id) == 0) {

				TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO per = new TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO();
				per.setJco_id(Integer.parseInt(jco_id));
				per.setAuthority(rank_authority);
				per.setDate_of_authority(Date_authority);
				per.setRank(Integer.parseInt(rank));
				per.setRank_intake(rank_intake);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO set authority=:authority,date_of_authority=:date_of_authority,"
						+ "rank=:rank,rank_intake=:rank_intake,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", rank_authority).setDate("date_of_authority", Date_authority)
						.setInteger("rank", Integer.parseInt(rank)).setString("rank_intake", rank_intake)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("r_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			}
			else
			{
				p_comm.update_JcoOr_Census_status(Integer.parseInt(jco_id), username);
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
	
	
	@RequestMapping(value = "/admin/get_Rank1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> get_Rank1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getupdate_census_RankData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> getupdate_census_RankData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Rank_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set rank=:rank,rank_intake=:rank_intake,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:jco_id and status in('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("rank", obj.getRank()).setParameter("rank_intake", obj.getRank_intake())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("jco_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_RankJCO(TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
					+ " where  jco_id=:jco_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
	//***************************** Start Reject***************************************//
	@RequestMapping(value = "/admin/getRank_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getRank_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		
		TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO BG = new TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("r_id")));
		String msg1 = Update_Rank_Reject(BG, username);

		return msg1;
		

	}

	public String Update_Rank_Reject(TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> getRankDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getRankData_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> getRankData_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/Rank_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Rank_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where id=:id";
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
	
	/*******************************************Approve view Start*********************************************************/
	public List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> View_Rank_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO> list = (List<TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/
}
