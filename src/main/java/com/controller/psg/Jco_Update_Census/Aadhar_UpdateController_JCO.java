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
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_AADHARNO_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Aadhar_UpdateController_JCO {
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	
	
	
	
	@RequestMapping(value = "/admin/Aadhar_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Aadhar_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		String adhar_number = "";
		String authority = request.getParameter("aa_authority").trim();
     	String date_of_authority = request.getParameter("aa_date_of_authority").trim();
		String aa_id = request.getParameter("aa_id");
		String jco_id = request.getParameter("jco_id");
		
		String adhar_number1 = request.getParameter("adhar_number1");
		String adhar_number2 = request.getParameter("adhar_number2");
		String adhar_number3 = request.getParameter("adhar_number3");					
		String Aadhar = adhar_number1 + adhar_number2 + adhar_number3;					
		if (Aadhar != null && !Aadhar.equals("")) {
			adhar_number = Aadhar;
		}
		String msg = "";
		if (authority == null || authority.equals("") || authority.equals("null")) {
			return "Please Enter Authority No.";
		}
		if (!valid.isValidAuth(authority)) {
			return valid.isValidAuthMSG + " Authority No";
		}	
		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return "Authority No " + valid.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		else if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		} else {
			Date_authority = format.parse(date_of_authority);
		}
		if (adhar_number1 == null || adhar_number1.trim().equals("")) {
			msg = "Please Enter Aadhaar No1";
			return msg;
		}
		if (adhar_number2 == null || adhar_number2.trim().equals("")) {
			msg = "Please Enter Aadhaar No2";
			return msg;
		}
		if (adhar_number3 == null || adhar_number3.trim().equals("")) {
			msg = "Please Enter Aadhaar No3";
			return msg;
		}
		
		if (valid.isValidAadhar(Aadhar) == false) {
			return valid.isValidAadharMSG ;
		
	     }
		
		try {
			if (Integer.parseInt(aa_id) == 0) {

				TB_PSG_UPDATE_CENSUS_AADHARNO_JCO per = new TB_PSG_UPDATE_CENSUS_AADHARNO_JCO();
				per.setJco_id(Integer.parseInt(jco_id));
				per.setAuthority(authority);
				per.setDate_of_authority(Date_authority);
				per.setAadhar_no(adhar_number);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_AADHARNO_JCO set authority=:authority,date_of_authority=:date_of_authority,"
						+ "aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setParameter("aadhar_no", adhar_number)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("aa_id")));
									
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
  
	@RequestMapping(value = "/admin/get_AadharNo1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> get_AadharNo1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> list = (List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	@RequestMapping(value = "/admin/getupdate_census_AadharData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> getupdate_census_AadharData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> list = (List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Aadhar_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:jco_id and status in ('1','5')";
			Query query = sessionHQL.createQuery(hql).setParameter("aadhar_no", obj.getAadhar_no())
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
	public String Update_AadharJCO(TB_PSG_UPDATE_CENSUS_AADHARNO_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_AADHARNO_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_AADHARNO_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
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
	@RequestMapping(value = "/admin/getAadhar_No_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getAadhar_No_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		TB_PSG_UPDATE_CENSUS_AADHARNO_JCO BG = new TB_PSG_UPDATE_CENSUS_AADHARNO_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("aa_id")));
		BG.setReject_remarks(reject_remarks);
		String msg1 = Update_Add_No_Reject(BG, username);

		return msg1;
		

	}

	public String Update_Add_No_Reject(TB_PSG_UPDATE_CENSUS_AADHARNO_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_AADHARNO_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status, reject_remarks=:reject_remarks  "
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
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> getAdd_NoDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> list = (List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
		
		
	@RequestMapping(value = "/admin/getAadharData_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> getAadharData_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> list = (List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/Add_No_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Add_No_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where id=:id";
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
	public List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> View_AAdhar_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_AADHARNO_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO> list = (List<TB_PSG_UPDATE_CENSUS_AADHARNO_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/

}
