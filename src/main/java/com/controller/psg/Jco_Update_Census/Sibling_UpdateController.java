package com.controller.psg.Jco_Update_Census;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_SIBLINGS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Sibling_UpdateController {
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	ValidationController valid = new ValidationController();


	
	@RequestMapping(value = "/admin/Sibling_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> Sibling_Action_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Map<String, String> data = new HashMap<>();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String sib_name = request.getParameter("sib_name");
		String sib_date_of_birth = request.getParameter("sib_date_of_birth");
		String sib_relationship = request.getParameter("sib_relationship");
		String adhar_number = request.getParameter("adhar_number");
		String pan_no = request.getParameter("pan_no");

		String sib_ch_id = request.getParameter("sib_ch_id");
		String jco_id = request.getParameter("jco_id");
		String ser_ex = request.getParameter("ser_ex");
		String other_sib_ser = request.getParameter("other_sib_ser");
		String sib_ser_text = request.getParameter("sibling");
		int sibling_service = Integer.parseInt(request.getParameter("sib_ser"));
		String sibling_personal_no = request.getParameter("sib_pers_no");
		
		Date Date_of_authority = null;
		String ds_authority = request.getParameter("ds_authority").trim();
     	String date_of_authority = request.getParameter("date_of_authority").trim();
     	
     	if (ds_authority == null || ds_authority.equals("") || ds_authority.equals("null")) {
     		data.put("error", "Please Enter Authority No");
			return data;
			
		}
		if (!valid.isValidAuth(ds_authority)) {
		   data.put("error", valid.isValidAuthMSG + " Authority No");
			return data;
		}	
		if (!valid.isvalidLength(ds_authority,valid.authorityMax,valid.authorityMin)) {
			   data.put("error"," Authority No"+ valid.isValidAuthMSG  );
 				
		}
		
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			data.put("error", "Please Select Authority Date");
		}
		else if (!valid.isValidDate(date_of_authority)) {
			data.put("error",valid.isValidDateMSG + " of Authority");	
		}
		else {
			Date_of_authority = format.parse(date_of_authority);
		}
		
		if (sib_name.trim().equals("") || sib_name == "" || sib_name == null) {
			data.put("error", "Please Enter Sibling Name");
			return data;
		}
		if (valid.isOnlyAlphabet(sib_name) == false) {
			data.put("error", "Name "+ valid.isOnlyAlphabetMSG);
			return data;
	     }
		if (!valid.isvalidLength(sib_name,valid.nameMax,valid.nameMin)) {
			data.put("error","Name " + valid.isValidLengthMSG);
			return data;
		}
		if (sib_date_of_birth == null || sib_date_of_birth.trim().equals("")) {
			data.put("error", "Please Select Sibling Date of Birth");
			return data;
		}
		if (!valid.isValidDate(sib_date_of_birth)){
			data.put("error", valid.isValidDateMSG  + " of Birth");
			return data;
		}
		if (sib_relationship == null || sib_relationship.equals("0")) {
			data.put("error", "Please Select Sibling Relation");
			return data;
		}
		if (adhar_number == null || adhar_number.trim().equals("")) {
			data.put("error", "Please Enter Aadhar Number");
			return data;
		}
		if (valid.isValidAadhar(adhar_number) == false) {
			data.put("error", valid.isValidAadharMSG);
			return data;
	     }
		/* if (valid.isValidPan(pan_no) == false) {
			 data.put("error", valid.isValidPanMSG);
			    return data;
		     }*/
		if (ser_ex.equals("Yes")) {
			if (sibling_service == 0) {
				data.put("error", "Please Select Sibling Service");
				return data;
			}
			if (sibling_service == 1) {
				if (sibling_personal_no == null || sibling_personal_no.trim().equals("")) {
					data.put("error", "Please Enter Sibling Personal No");
					return data;
				}
			}
			if (!sibling_personal_no.trim().equals("")) {
				if (sibling_personal_no.trim().length() < 5 || sibling_personal_no.trim().length() > 15) {
					data.put("error", "Please Enter Valid Sibling Personal No");
					return data;
				}
			}
			if (sibling_personal_no.trim().equals("")) {
				sibling_personal_no = "";
			}
			if (sib_ser_text.equals("OTHER")) {
				if (other_sib_ser.trim().equals("")) {
					data.put("error", "Please Enter Other Sibling Service");
					return data;
				}
			} else {
				other_sib_ser = null;
			}
		}
		
		try {
			if (Integer.parseInt(sib_ch_id) == 0) {
				TB_CENSUS_JCO_OR_SIBLINGS fam_sib = new TB_CENSUS_JCO_OR_SIBLINGS();
				fam_sib.setName(sib_name);
				fam_sib.setDate_of_birth(format.parse(sib_date_of_birth));
				fam_sib.setRelationship(Integer.parseInt(sib_relationship));
				fam_sib.setJco_id(Integer.parseInt(jco_id));
				fam_sib.setAadhar_no(adhar_number);
			
				if (pan_no != null && !pan_no.trim().equals("")) {
					fam_sib.setPan_no(pan_no);
				}
				
				fam_sib.setIf_sibling_ser(ser_ex);
				fam_sib.setSibling_service(sibling_service);
				fam_sib.setSibling_personal_no(sibling_personal_no);
				fam_sib.setOther_sibling_ser(other_sib_ser);
				fam_sib.setAuthority(ds_authority);
				fam_sib.setDate_of_authority(Date_of_authority);
				fam_sib.setCreated_by(username);
				fam_sib.setCreated_date(date);
				int id = (int) sessionhql.save(fam_sib);
				data.put("sibId", String.valueOf(id));
				data.put("saved", "Data Saved Successfully");
			} else {
				String hql = "update TB_CENSUS_JCO_OR_SIBLINGS set modified_by=:modified_by ,modified_date=:modified_date ,name=:name, date_of_birth=:date_of_birth,"
						+ " relationship=:relationship,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),authority=:authority,date_of_authority=:date_of_authority";
				hql += ",pan_no=pgp_sym_encrypt(:pan_no,current_setting('miso.version')) ";
				hql += ",sibling_service=:sib_ser,sibling_personal_no=:sib_pers_no,if_sibling_ser=:if_sib_ser,other_sibling_ser=:other_sib_ser ";
				hql += " where  id=:id";
				Query query = sessionhql.createQuery(hql).setTimestamp("date_of_birth", format.parse(sib_date_of_birth))
						.setString("name", sib_name).setInteger("relationship", Integer.parseInt(sib_relationship))
						.setString("authority", ds_authority).setTimestamp("date_of_authority", Date_of_authority)
						.setInteger("id", Integer.parseInt(sib_ch_id)).setString("modified_by", username)
						.setTimestamp("modified_date", date).setString("aadhar_no", adhar_number);
				if (pan_no != null && !pan_no.trim().equals("")) {
					query.setString("pan_no", pan_no);
				} else
					query.setString("pan_no", null);
				query.setInteger("sib_ser", sibling_service).setString("sib_pers_no", sibling_personal_no)
						.setString("if_sib_ser", ser_ex).setString("other_sib_ser", other_sib_ser);
				int update = query.executeUpdate() > 0 ? 1 : 0;
				if (update == 1)
					data.put("updated", "Data Updated Successfully");
				else
					data.put("error", "Data Not Updated");
			}
			
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			}
			else
			{
				p_comm.update_JcoOr_Census_status(Integer.parseInt(jco_id), username);
			}
			
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				data.put("error", "Data Not Updated");
			} catch (RuntimeException rbe) {
				data.put("error", "Data Not Updated");
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/admin/get_sib1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_JCO_OR_SIBLINGS> get_sib1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	//***************************** Start Approve***************************************//
	
		@RequestMapping(value = "/admin/getupdate_census_SiblingData2", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_JCO_OR_SIBLINGS> getupdate_census_SiblingData2(int jco_id) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where  status = '0' and jco_id=:jco_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		public String Update_sibling_details_JCO(TB_CENSUS_JCO_OR_SIBLINGS obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
			try {
				String hql = "update TB_CENSUS_JCO_OR_SIBLINGS set name=:name,date_of_birth=:date_of_birth,relationship=:relationship,"
						+ "aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),pan_no=pgp_sym_encrypt(:pan_no,current_setting('miso.version')),sibling_service=:sibling_service,sibling_personal_no=:sibling_personal_no,"
						+ "if_sibling_ser=:if_sibling_ser,other_sibling_ser=:other_sibling_ser,modified_by=:modified_by,modified_date=:modified_date "
						+ "where id=:jco_id and status in ('1','5')  ";
				Query query = sessionHQL.createQuery(hql).setParameter("name", obj.getName()).setDate("date_of_birth", obj.getDate_of_birth())
						.setInteger("relationship", obj.getRelationship()).setString("aadhar_no", obj.getAadhar_no())
						.setString("pan_no", obj.getPan_no()).setInteger("sibling_service", obj.getSibling_service()).setString("sibling_personal_no", obj.getSibling_personal_no())
						.setString("if_sibling_ser", obj.getIf_sibling_ser()).setString("other_sibling_ser", obj.getOther_sibling_ser())
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
		public String Update_SiblingJCO(TB_CENSUS_JCO_OR_SIBLINGS obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
			try {
				String hql1 = "update TB_CENSUS_JCO_OR_SIBLINGS set status=:status where jco_id=:jco_id and status != '0' ";
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				String hql = "update TB_CENSUS_JCO_OR_SIBLINGS set modified_by=:modified_by,modified_date=:modified_date,status=:status "
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
		
		//***************************** End Approve***************************************//
	
		//***************************** Start Reject***************************************//
		@RequestMapping(value = "/admin/getSibling_JCOReject", method = RequestMethod.POST)
		public @ResponseBody String getSibling_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String username = session.getAttribute("username").toString();
			String reject_remarks = request.getParameter("reject_remarks");
			
			TB_CENSUS_JCO_OR_SIBLINGS BG = new TB_CENSUS_JCO_OR_SIBLINGS();
			
			BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
			BG.setId(Integer.parseInt(request.getParameter("ds_id")));
			BG.setReject_remarks(reject_remarks);
			String msg1 = Update_Sibling_Reject(BG, username);

			return msg1;
			

		}

		public String Update_Sibling_Reject(TB_CENSUS_JCO_OR_SIBLINGS obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			try {

				String hql = "update TB_CENSUS_JCO_OR_SIBLINGS set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
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
		
		
		public @ResponseBody List<TB_CENSUS_JCO_OR_SIBLINGS> getSiblingJCO2( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where jco_id=:jco_id and status = '3' ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/getSibling_JCO3", method = RequestMethod.POST)
		public @ResponseBody List<TB_CENSUS_JCO_OR_SIBLINGS> getSibling_JCO3(int jco_id) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where  status = '3' and jco_id=:jco_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/Sibling_JCOdelete_action", method = RequestMethod.POST)
		public @ResponseBody String Sibling_JCOdelete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("sib_ch_id"));
			try {
				String hqlUpdate = "delete from TB_CENSUS_JCO_OR_SIBLINGS where id=:id";
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
		
		//***************************** END Reject***************************************//
		
		/*******************************************Approve view Start*********************************************************/
		public List<TB_CENSUS_JCO_OR_SIBLINGS> View_Sibling_Details_DataCensus( int jco_id,Date modifiedDate) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		/*******************************************Approve view End*********************************************************/
}
