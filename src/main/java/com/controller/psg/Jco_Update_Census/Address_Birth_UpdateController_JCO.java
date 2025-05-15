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
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Address_Birth_UpdateController_JCO {
	
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	
	
	ValidationController valid = new ValidationController();
	
	@RequestMapping(value = "/admin/Address_Birth_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Address_Birth_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Date Date_authority = null;
		String authority = request.getParameter("addr_authority").trim();
     	String date_of_authority = request.getParameter("addr_date_authority").trim();
    	String place_of_birth = request.getParameter("place_of_birth").trim();
		String addr_ch_id = request.getParameter("addr_ch_id");
		String country_of_birth = request.getParameter("country_of_birth");
		String state_of_birth = request.getParameter("state_of_birth");
		String district_of_birth = request.getParameter("district_of_birth");
		String country_other = request.getParameter("country_other").trim();
		String state_other = request.getParameter("state_other").trim();
		String district_other = request.getParameter("district_other").trim();
		String jco_id = request.getParameter("jco_id");
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
		 if(place_of_birth == null || place_of_birth.equals("null") || place_of_birth.equals("")) {
			 msg = "Please Enter Place of Birth ";
			 return msg;
		 }
		 if (!valid.isValidAuth(place_of_birth)) {
				return valid.isValidAuthMSG + " Place of Birth";
			}	
			if (!valid.isvalidLength(place_of_birth,valid.nameMax,valid.nameMin)) {
	 			return "Place of Birth " + valid.isValidLengthMSG;	
			}
		 
		 if(country_of_birth.equals("0") || country_of_birth.equals("null") || country_of_birth == null) {
			 msg = "Please Select Country of Birth ";
			 return msg;
		 }
		 if(country_other.trim().equals("OTHERS") && country_other.trim().equals("") || country_other == null) {
			 msg = "Please Enter Country Other ";
			 return msg;
		 }
		 
		 if(state_of_birth.equals("0") || state_of_birth.equals("null") || state_of_birth == null) {
			 msg = "Please Select State of Birth ";
			 return msg;
		 }
		 
		 if(state_other.trim().equals("OTHERS") && state_other.equals("")) {
			 msg = "Please Enter State Other ";
			 return msg;
		 }
		 
		 if(district_of_birth.equals("0") || district_of_birth.equals("null") || district_of_birth == null) {
			 msg = "Please Select District of Birth ";
			 return msg;
		 }
		 
		 if(district_other.trim().equals("OTHERS") && district_other.trim().equals("null") ) {
			 msg = "Please Enter District Other ";
			 return msg;
		 }
		 
		 
		 
		try {
			if (Integer.parseInt(addr_ch_id) == 0) {

				TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO per = new TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO();
				per.setJco_id(Integer.parseInt(jco_id));
				per.setAuthority(authority);
				per.setDate_of_authority(Date_authority);
				per.setPlace_of_birth(place_of_birth);
				per.setCountry_of_birth(Integer.parseInt(country_of_birth));
				per.setState_of_birth(Integer.parseInt(state_of_birth));
				per.setDistrict_of_birth(Integer.parseInt(district_of_birth));
				per.setCountry_other(country_other);
				per.setState_other(state_other);
				per.setDistrict_other(district_other);
                per.setCreated_by(username);
                per.setCreated_date(new Date());
				per.setStatus(0);

				int id = (int) sessionhql.save(per);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO set authority=:authority,date_of_authority=:date_of_authority,place_of_birth=:place_of_birth,"
						+ "country_of_birth=:country_of_birth,state_of_birth=:state_of_birth,district_of_birth=:district_of_birth,"
						+ "country_other=:country_other,state_other=:state_other,district_other=:district_other,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						.setString("place_of_birth", place_of_birth).setInteger("country_of_birth", Integer.parseInt(country_of_birth))
						.setInteger("state_of_birth", Integer.parseInt(state_of_birth)).setInteger("district_of_birth", Integer.parseInt(district_of_birth))
						.setString("country_other", country_other).setString("state_other", state_other).setString("district_other", district_other)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("addr_ch_id")));
									
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
  
	@RequestMapping(value = "/admin/get_Birth_address1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> get_Birth_address1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	@RequestMapping(value = "/admin/getupdate_census_ADDBirData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> getupdate_census_ADDBirData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	//260194
	public String Update_addbirth_census_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			
			String hql = "update TB_CENSUS_JCO_OR_PARENT set place_of_birth=:place_of_birth,modified_by=:modified_by,modified_date=:modified_date, "
					+ "country_of_birth=:country_of_birth,state_of_birth=:state_of_birth,district_of_birth=:district_of_birth,country_other=:country_other "
					+ "state_other=:state_other,district_other=:district_other,"
					+ " where id=:jco_id and status in ('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("place_of_birth", obj.getPlace_of_birth())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("country_of_birth",obj.getCountry_of_birth())
					.setInteger("state_of_birth", obj.getState_of_birth()).setInteger("district_of_birth", obj.getDistrict_of_birth())
					.setString("country_other", obj.getCountry_other()).setString("state_other", obj.getState_other()).setString("district_other", obj.getDistrict_other())
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
	
	public String Update_addbirth_JCO(TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
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
		@RequestMapping(value = "/admin/getAdd_BirthJCOReject", method = RequestMethod.POST)
		public @ResponseBody String getAdd_BirthJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String username = session.getAttribute("username").toString();
			String reject_remarks = request.getParameter("reject_remarks");
			TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO ChangeOfGd = new TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO();
			
			ChangeOfGd.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
			ChangeOfGd.setId(Integer.parseInt(request.getParameter("addr_ch_id")));
			ChangeOfGd.setReject_remarks(reject_remarks);
			String msg1 = Update_Add_Birth_Reject(ChangeOfGd, username);

			return msg1;
			

		}

		public String Update_Add_Birth_Reject(TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			try {

				String hql = "update TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where jco_id=:jco_id and status = '0' and id=:id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 3)
						.setInteger("jco_id", obj.getJco_id())
						.setString("reject_remarks", obj.getReject_remarks())
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
		
		public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> getAddBirthDataJCO2( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where jco_id=:jco_id and status = '3' ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
			
	@RequestMapping(value = "/admin/getAddBirData_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> getAddBirData_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/ADD_Birth_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String ADD_Birth_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where id=:id";
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
	public List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> ViewAddressBirthDataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO> list = (List<TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/

}
