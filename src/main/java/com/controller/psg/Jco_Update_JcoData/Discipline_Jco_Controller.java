package com.controller.psg.Jco_Update_JcoData;

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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_DISCIPLINE_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Discipline_Jco_Controller {

	@Autowired
	Psg_CommanDAO psg_com;

	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
	
	@RequestMapping(value = "/admin/getdisciplineData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DISCIPLINE_JCO> getdisciplineData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CENSUS_DISCIPLINE_JCO where  jco_id=:jco_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id",jco_id);
		List<TB_CENSUS_DISCIPLINE_JCO> list = (List<TB_CENSUS_DISCIPLINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}

	@RequestMapping(value = "/admin/Change_of_Discipline_action_jco", method = RequestMethod.POST)
	public @ResponseBody String Change_of_Discipline_action_jco(ModelMap Mmap, HttpSession session,
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
		String unit_name1 = request.getParameter("unit_name");
		String award_dtStr = request.getParameter("award_dt");
		String disi_authority = request.getParameter("disi_authority");
		String disi_authority_date = request.getParameter("disi_authority_date");
		String type_of_entry_other = request.getParameter("type_of_entry_other");
		Date a_dt = null;
		String disi_id = request.getParameter("disi_id");

		String jco_id = request.getParameter("jco_id");
		
		String trialed_by = request.getParameter("trialed_by");
		String army_act_sec = request.getParameter("army_act_sec");
		String sub_clause = request.getParameter("sub_clause");
		
		String msg = "";
			
			if (disi_authority == null || disi_authority.equals("") || disi_authority == "") {
				return "Please Enter Authority";
			}
			if (!valid.isValidAuth(disi_authority)) {
				return valid.isValidAuthMSG + " Authority ";
			}	
			if (!valid.isvalidLength(disi_authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority " + valid.isValidLengthMSG;	
			}
		 
			if (disi_authority_date == null || disi_authority_date.equals("null") || disi_authority_date.equals("DD/MM/YYYY") || disi_authority_date.equals("")) {
				return "Please Select Authority Date";
			}
			
			 else if (!valid.isValidDate(disi_authority_date)) {
		 			return valid.isValidDateMSG + " of Authority";	
				}
				else {
					disi_authority_dt = format.parse(disi_authority_date);
				}
			
			if (army_act_sec == null || army_act_sec.equals("0") || army_act_sec == "") {
				return "Please Select Army Act Sec";
			}
			if (sub_clause == null || sub_clause.equals("0") || sub_clause == "") {
				return "Please Select Sub Clause ";
			}
			if (trialed_by == null || trialed_by.equals("0") || trialed_by =="" ) {
				return "Please Select  Discipline Trialed";
			}
			
			if (description == null || description.equals("") || description == "") {
				return "Please Enter  Description(cas code/punishment awarded) ";
			}
			if (!valid.isOnlyAlphabetNumeric(description)) {
				return " Description(cas code/punishment awarded) " + valid.isOnlyAlphabetNumericMSG;
			}
			if (!valid.isvalidLength(description, valid.remarkMax, valid.remarkMin)) {
				return " Description(cas code/punishment awarded) " + valid.isValidLengthMSG;
			}
	
			if (type_of_entry == null || type_of_entry.equals("0") || type_of_entry == "0") {
				return "Please Select  Type of Entry ";
			}
			if (type_of_entry_other != null && !type_of_entry_other.trim().equals("")) {
				if (!valid.isOnlyAlphabet(type_of_entry_other)) {
					return "  Type of Entry Other " + valid.isOnlyAlphabetMSG;
				}
			    if (!valid.isvalidLength(type_of_entry_other, valid.nameMax, valid.nameMin)) {
			    	return "  Type of Entry Other " + valid.isValidLengthMSG;
				}
			} 
			if (award_dtStr == null || award_dtStr.equals("null") || award_dtStr.equals("DD/MM/YYYY") || award_dtStr.equals("")) {
				return "Please Select Award Date";
			}
		    else if (!valid.isValidDate(award_dtStr)) {
	 			return valid.isValidDateMSG + " of Award";	
			}
			else {
				a_dt = format.parse(award_dtStr);
			}
			if (unit_name1 == null || unit_name1.equals("") || unit_name1 == "") {
				return "Please Enter  Unit Name ";
			}
			if (!valid.isUnit(unit_name1)) {
	            return " Unit Name " + valid.isUnitMSG;
	         }

//	        if (!valid.isvalidLength(unit_name1, valid.nameMax, valid.nameMin)) {
//	            return " Unit Name " + valid.isValidLengthMSG;
//	       
//	         }
			
			
		try {
			// save
			if (Integer.parseInt(disi_id) == 0) {
				TB_CENSUS_DISCIPLINE_JCO chil = new TB_CENSUS_DISCIPLINE_JCO();
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
				chil.setJco_id(Integer.parseInt(jco_id));
                chil.setDisi_authority(disi_authority);
                chil.setDisi_authority_date(disi_authority_dt);
                chil.setSub_clause(Integer.parseInt(sub_clause));
				chil.setCreated_by(username);
				chil.setCreated_date(date);
				chil.setStatus(0);
				chil.setInitiated_from("u");
				int id = (int) sessionhql.save(chil);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_DISCIPLINE_JCO set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,"
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
				pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
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

	public @ResponseBody List<TB_CENSUS_DISCIPLINE_JCO> get_Discipline1( int jco_id) throws ParseException {

	

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE_JCO where  jco_id=:jco_id and status='0' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_DISCIPLINE_JCO> list = (List<TB_CENSUS_DISCIPLINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_Discipline(TB_CENSUS_DISCIPLINE_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {

			String hql = "update TB_CENSUS_DISCIPLINE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());

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

	@RequestMapping(value = "/admin/get_DisciplineReject_jco", method = RequestMethod.POST)
	public @ResponseBody String get_DisciplineReject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();
		TB_CENSUS_DISCIPLINE_JCO ChangeDis = new TB_CENSUS_DISCIPLINE_JCO();
		ChangeDis.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeDis.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeDis.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_DisciplineReject(ChangeDis, username);

		return msg1;

	}

	public String Update_Change_of_DisciplineReject(TB_CENSUS_DISCIPLINE_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";

			String hql = "update TB_CENSUS_DISCIPLINE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setString("reject_remarks", obj.getReject_remarks())
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

//		} catch (Exception e) {
//			msg = "Data Not Updated.";
//			tx.rollback();
//		} finally {
//			sessionHQL.close();
//		}
		return msg;

	}

	public @ResponseBody List<TB_CENSUS_DISCIPLINE_JCO> getChangeOfDiscipline2(int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE_JCO> list = (List<TB_CENSUS_DISCIPLINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_Discipline3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DISCIPLINE_JCO> get_Discipline3_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE_JCO> list = (List<TB_CENSUS_DISCIPLINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/admin/Discipline_delete_action_jco", method = RequestMethod.POST)
		public @ResponseBody String Discipline_delete_action_jco(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_DISCIPLINE_JCO where id=:id";
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
