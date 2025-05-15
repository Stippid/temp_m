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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class DisciplineController {

	@Autowired
	Psg_CommanDAO psg_com;

	Psg_CommonController mcommon = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/getdisciplineData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DISCIPLINE> getdisciplineData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where census_id=:census_id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id",
				comm_id);
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}

	@RequestMapping(value = "/admin/Change_of_Discipline_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Discipline_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date disi_authority_dt = null;
		String username = session.getAttribute("username").toString();
	
		String description = request.getParameter("description");
		String type_of_entry = request.getParameter("type_of_entry");
		String unit_name = request.getParameter("dis_sus");
		String award_dtStr = request.getParameter("award_dt");
		String disi_authority = request.getParameter("disi_authority");
		String disi_authority_date = request.getParameter("disi_authority_date");
		String type_of_entry_other = request.getParameter("type_of_entry_other");
		Date a_dt = null;
//		Date comm_date = format.parse(request.getParameter("comm_date"));
		String disi_id = request.getParameter("disi_id");

		String census_id = request.getParameter("census_id");
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		
		String trialed_by = request.getParameter("trialed_by");
		String army_act_sec = request.getParameter("army_act_sec");
		String sub_clause = request.getParameter("sub_clause");
		
		String msg = "";
		if (disi_authority == null || disi_authority.equals("") || disi_authority == "") {
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(disi_authority)) {
			return validation.isValidAuthMSG + " Authority";	
		}
 		if (!validation.isvalidLength(disi_authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		}	
		if (disi_authority_date == null || disi_authority_date.equals("null") || disi_authority_date.equals("DD/MM/YYYY") || disi_authority_date.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!validation.isValidDate(disi_authority_date)) {
			return  validation.isValidDateMSG + " of Authority";	
		}
		if (!disi_authority_date.equals("") && !disi_authority_date.equals("DD/MM/YYYY")) {
			disi_authority_dt = format.parse(disi_authority_date);
		 }
//		if (mcommon.CompareDate(disi_authority_dt,comm_date) == 0) {
//			return "Authority Date should be Greater than Commission Date";
//		}
		if (army_act_sec == null || army_act_sec.equals("") || army_act_sec == "") {
			return "Please Select Army Act Sec";
		}
		if (sub_clause == null || sub_clause.equals("") || sub_clause == "") {
			return "Please Select Sub Clause ";
		}
		if (trialed_by == null || trialed_by.equals("") || trialed_by =="" ) {
			return "Please Select  Discipline Trialed";
		}
		
		if (description == null || description.equals("") || description == "") {
			return "Please Enter  Description(cas code/punishment awarded) ";
		}
		if (!validation.isOnlyAlphabetNumeric(description)) {
			return" Description(cas code/punishment awarded) " + validation.isOnlyAlphabetNumericMSG;
		}
		if (!validation.isvalidLength(description, validation.remarkMax, validation.remarkMin)) {
			return " Description(cas code/punishment awarded) " + validation.isValidLengthMSG;
		}
		
		if (type_of_entry == null || type_of_entry.equals("0") || type_of_entry == "0") {
			return "Please Select  Type of Entry ";
		}
	/*	if (type_of_entry_select.equals("OTHER") && !type_of_entry_select.equals("")){
			if (type_of_entry_other == null || type_of_entry_other.equals("") || type_of_entry_other == "") {
				return "Please Enter  Type of Entry Other.";
			}*/
			if (type_of_entry_other != null && !type_of_entry_other.trim().equals("")) {
				if (!validation.isOnlyAlphabet(type_of_entry_other)) {
					return "  Type of Entry Other " + validation.isOnlyAlphabetMSG;
				}
			    if (!validation.isvalidLength(type_of_entry_other, validation.nameMax, validation.nameMin)) {
			    	return "  Type of Entry Other " + validation.isValidLengthMSG;
				}
			} 
		///}
		if (award_dtStr == null || award_dtStr.equals("null") || award_dtStr.equals("DD/MM/YYYY") || award_dtStr.equals("")) {
			return "Please Select Award Date";
		}
		if (!award_dtStr.equals("") && !award_dtStr.equals("DD/MM/YYYY")) {
			a_dt = format.parse(award_dtStr);
		}
		if (unit_name == null || unit_name.equals("") || unit_name == "") {
			return "Please Enter  Unit Name ";
		}
			
		try {
			// save
			if (Integer.parseInt(disi_id) == 0) {
				TB_CENSUS_DISCIPLINE chil = new TB_CENSUS_DISCIPLINE();
				// chil.setArmy_no(army_no);
				chil.setAward_dt(a_dt);
				chil.setTrialed_by(Integer.parseInt(trialed_by));
				// chil.setName(name);
				chil.setArmy_act_sec(Integer.parseInt(army_act_sec));
				chil.setDescription(description);
				chil.setType_of_entry(Integer.parseInt(type_of_entry));
				chil.setType_of_entry_other(type_of_entry_other);
				chil.setUnit_name(unit_name);
				// chil.setRank(Integer.parseInt(rank));
				chil.setCensus_id(Integer.parseInt(census_id));
				chil.setComm_id(comm_id);
                chil.setDisi_authority(disi_authority);
                chil.setDisi_authority_date(disi_authority_dt);
                chil.setSub_clause(Integer.parseInt(sub_clause));
				chil.setCreated_by(username);
				chil.setCreated_date(date);
				chil.setStatus(0);
				int id = (int) sessionhql.save(chil);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_DISCIPLINE set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,"
						// + "name=:name, "
						// + "army_no=:army_no,"
						+ "army_act_sec=:army_act_sec," + "description=:description," + "type_of_entry=:type_of_entry,"
						+ "award_dt=:award_dt,disi_authority=:disi_authority,disi_authority_date=:disi_authority_date,type_of_entry_other=:type_of_entry_other,"
						// + "rank=:rank,"
						+ " unit_name=:unit_name," + " trialed_by=:trialed_by," + " sub_clause=:sub_clause" + " where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("status", 0).setString("modified_by", username)
						.setTimestamp("modified_date", date)
						// .setString("name", name)
						// .setString("army_no", army_no)
						.setInteger("army_act_sec",Integer.parseInt(army_act_sec)).setString("description", description)
						.setInteger("type_of_entry", Integer.parseInt(type_of_entry)).setDate("award_dt", a_dt)
						// .setInteger("rank", Integer.parseInt(rank))
						.setString("unit_name", unit_name).setInteger("trialed_by", Integer.parseInt(trialed_by))
						.setString("disi_authority", disi_authority)
						.setString("type_of_entry_other", type_of_entry_other)
						.setTimestamp("disi_authority_date", disi_authority_dt)
						.setInteger("sub_clause", Integer.parseInt(sub_clause))
						.setInteger("id", Integer.parseInt(disi_id));

				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
		
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
			{
				mcommon.update_offr_status(Integer.parseInt(census_id),username);
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

	public @ResponseBody List<TB_CENSUS_DISCIPLINE> get_Discipline1(int id, BigInteger comm_id) {

		

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where census_id=:census_id and comm_id=:comm_id and status='0' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	public String Update_Discipline(TB_CENSUS_DISCIPLINE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {

			String hql = "update TB_CENSUS_DISCIPLINE set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
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

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/get_DisciplineReject", method = RequestMethod.POST)
	public @ResponseBody String get_DisciplineReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_DISCIPLINE ChangeDis = new TB_CENSUS_DISCIPLINE();
		ChangeDis.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeDis.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeDis.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeDis.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_DisciplineReject(ChangeDis, username);

		return msg1;

	}

	public String Update_Change_of_DisciplineReject(TB_CENSUS_DISCIPLINE obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg1 = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected. ";

			String hql = "update TB_CENSUS_DISCIPLINE set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_DISCIPLINE> getChangeOfDiscipline2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Discipline3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DISCIPLINE> get_Discipline3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Discipline_delete_action", method = RequestMethod.POST)
		public @ResponseBody String Discipline_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_DISCIPLINE where id=:id";
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
