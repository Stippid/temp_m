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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Date_AttestationUpdateController {
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	
	@RequestMapping(value = "/admin/Attestation_Date_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Attestation_Date_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		Date Date_attest = null;
		String doa_authority = request.getParameter("doa_authority").trim();
     	String doa_date_of_authority = request.getParameter("doa_date_of_authority").trim();
     	String date_of_attestation = request.getParameter("date_of_attestation").trim();
     	String doa_id = request.getParameter("doa_id");
		String jco_id = request.getParameter("jco_id");
		String msg = "";
		 if(doa_authority == null || doa_authority.equals("null") || doa_authority.equals("")) {
			 msg = "Please Enter Authority ";
			 return msg;
		 }
		 if (!valid.isValidAuth(doa_authority)) {
				return valid.isValidAuthMSG + " Authority No";
			}	
			if (!valid.isvalidLength(doa_authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority No " + valid.isValidLengthMSG;	
			}
		if (doa_date_of_authority == null || doa_date_of_authority.equals("") || doa_date_of_authority.equals("DD/MM/YYYY") || doa_date_of_authority.equals("")) {
			msg = "Please Enter Date of Authority ";
			return msg;
		} 
		else if (!valid.isValidDate(doa_date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}else {
			Date_authority = format.parse(doa_date_of_authority);
		}
		if (date_of_attestation == null || date_of_attestation.equals("") || date_of_attestation.equals("DD/MM/YYYY") || date_of_attestation.equals("")) {
			msg = "Please Enter Date of Attestation ";
			return msg;
		} 
		else if (!valid.isValidDate(date_of_attestation)) {
 			return valid.isValidDateMSG + " of Attestation";	
		}
		else {
			Date_attest = format.parse(date_of_attestation);
		}
		try {
			if (Integer.parseInt(doa_id) == 0) {

				TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO per = new TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO();
				per.setJco_id(Integer.parseInt(jco_id));
				per.setAuthority(doa_authority);
				per.setDate_of_authority(Date_authority);
				per.setDate_of_attestation(Date_attest);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO set authority=:authority,date_of_authority=:date_of_authority,"
						+ "date_of_attestation=:date_of_attestation,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", doa_authority)
						.setDate("date_of_authority", Date_authority)
						.setDate("date_of_attestation", Date_attest)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("doa_id")));
									
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
	
  
	@RequestMapping(value = "/admin/get_Attestation_Date1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> get_Attestation_Date1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> list = (List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/getupdate_census_Dt_Of_AttesationData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> getupdate_census_Dt_Of_AttesationData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> list = (List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Dt_of_Attestation_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set date_of_attestation=:date_of_attestation,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:jco_id and status in('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("date_of_attestation", obj.getDate_of_attestation())
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
	public String Update_Dt_Of_AttestationJCO(TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
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
	@RequestMapping(value = "/admin/getDt_Of_Attes_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getDt_Of_Attes_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO BG = new TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("doa_id")));
		BG.setReject_remarks(reject_remarks);
		String msg1 = Update_Dt_Of_Atte_Reject(BG, username);

		return msg1;
		

	}

	public String Update_Dt_Of_Atte_Reject(TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
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
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> getDT_Of_AttesDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> list = (List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
		
	
	@RequestMapping(value = "/admin/getDt_of_Attestation_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> getDt_of_Attestation_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> list = (List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	@RequestMapping(value = "/admin/Dt_Of_Attes_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Dt_Of_Attes_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where id=:id";
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
	public List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> View_Date_Attestation_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO> list = (List<TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/
}
