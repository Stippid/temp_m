package com.controller.psg.update_3008;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

import opennlp.tools.parser.Parse;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_Of_Rank_3008_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Report_3008DAO report_3008DAO;

	@Autowired
	private Search_PostOutDao pod;

	@Autowired

	CensusAprovedDAO censusAprovedDAO;

	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	Psg_CommonController common = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;

	@RequestMapping(value = "/admin/change_in_rank_3008", method = RequestMethod.POST)
	public ModelAndView change_in_rank_3008(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_id1", required = false) BigInteger comm_id, HttpServletRequest request) {

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getOFFTypeofRankList", common.getOFFTypeofRankList());
		Mmap.put("getTypeofRankList", common.getTypeofRankList());
		Mmap.put("comm_id", comm_id);
		Mmap.put("msg", msg);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			String hql6 = "select TO_CHAR(comm.date_of_commission, 'DD/MM/YYYY') AS date_of_commission   from TB_TRANS_PROPOSED_COMM_LETTER comm "
					+ "where  comm.id=:comm_id and comm.status in('1','5')";
			Query query6 = sessionhql.createQuery(hql6);
			query6.setBigInteger("comm_id", comm_id);
			List<String> list6 = query6.list();
			if (!list6.isEmpty()) {
				Mmap.put("comm_date", list6.get(0));
			}
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

		return new ModelAndView("change_in_rank_Tiles");

	}

	@RequestMapping(value = "/admin/Change_of_Rank_action_3008", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Rank_action_3008(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

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
		// Date comm_date = format.parse(request.getParameter("comm_date"));
		String ch_r_id = request.getParameter("ch_r_id");
		int census_id = 0;
		String censusIdParameter = request.getParameter("census_id_cr");
		if (censusIdParameter != null && !censusIdParameter.isEmpty()) {
			census_id = Integer.parseInt(censusIdParameter);
		}

		BigInteger comm_id = BigInteger.ZERO;
		/// bisag 241122 V2 (change comm_id int to big int)

		if (!request.getParameter("comm_id_cr").equals("0") && !request.getParameter("comm_id_cr").equals("null")
				&& !request.getParameter("comm_id_cr").equals("")) {
			comm_id = new BigInteger(request.getParameter("comm_id_cr"));
		}
		if (authority == null || authority.equals("") || authority.equals("null")) {
			return "Please Enter Authority.";
		}
		if (!validation.isValidAuth(authority)) {
			return validation.isValidAuthMSG + " Authority";
		}
		if (!validation.isvalidLength(authority, validation.authorityMax, validation.authorityMin)) {
			return "Authority " + validation.isValidLengthMSG;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date Of Authority.";
		}
		if (!validation.isValidDate(date_of_authority)) {
			return validation.isValidDateMSG + " of Authority";
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			dt_authority = format.parse(date_of_authority);
		}
		// if (common.CompareDate(dt_authority,comm_date) == 0) {
		// return "Authority Date should be Greater than Commission Date";
		// }
		if (rank_type == null || rank_type.equals("0") || rank_type.equals("null")) {
			return "Please Select Rank Type.";
		}
		if (rank == null || rank.equals("0") || rank.equals("null")) {
			return "Please Select Rank.";
		}
		if (date_of_rank == null || date_of_rank.equals("null") || date_of_rank.equals("DD/MM/YYYY")
				|| date_of_rank.equals("")) {
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
			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_CHANGE_OF_RANK where date_of_rank=:date_of_rank and comm_id=:comm_id and  id!=:id and status!=-1")
					.setTimestamp("date_of_rank", dt_rank).setParameter("id", Integer.parseInt(ch_r_id))
					.setParameter("comm_id", comm_id);
			Long c = (Long) q0.uniqueResult();
			if (c > 0) {
				return "Date Of Rank Already Exists";
			}

			String hql1 = "update TB_CHANGE_OF_RANK set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", comm_id);

			msg = query1.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";

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
				cr.setStatus(1);
				cr.setModified_by(username);
				cr.setModified_date(date);

				int id = (int) sessionhql.save(cr);
				msg = Integer.toString(id);
			} else {

				String hql = "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
						+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority)
						.setDate("date_of_authority", dt_authority).setInteger("rank_type", Integer.parseInt(rank_type))
						.setInteger("rank", Integer.parseInt(rank)).setDate("date_of_rank", dt_rank)
						.setInteger("id", Integer.parseInt(ch_r_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 1);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}

			int count = censusAprovedDAO.checkdatapendingintablecomm(comm_id, "rank");
			String hql;
			if (count == 0) {

				hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank,update_comm_status =:update_comm_status  "
						+ " where id=:comm_id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
						.setInteger("rank", Integer.parseInt(rank)).setDate("date_of_rank", dt_authority)
						.setInteger("update_comm_status", 1);

				msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
			} else {
				hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank  "
						+ " where id=:comm_id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
						.setInteger("rank", Integer.parseInt(rank)).setDate("date_of_rank", dt_authority);
				msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
			}

			if (census_id > 0) {
				int count1 = censusAprovedDAO.checkdatapendingintable(comm_id, "rank");
				if (count1 == 0) {

					String hql2 = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
							+ " where comm_id=:comm_id ";
					Query query = sessionhql.createQuery(hql2).setBigInteger("comm_id", comm_id)
							.setInteger("update_ofr_status", 1);
					query.executeUpdate();
				}
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

		return msg;
	}

	@RequestMapping(value = "/admin/getChangeOfRankData_3008", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK> getChangeOfRankData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// int id = Integer.parseInt(request.getParameter("ch_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}