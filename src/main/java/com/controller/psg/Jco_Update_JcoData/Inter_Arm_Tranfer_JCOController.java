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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_INTER_ARM_TRANSFER_JCO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Inter_Arm_Tranfer_JCOController {
	Psg_CommonController mcommon = new Psg_CommonController();

	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	ValidationController valid = new ValidationController();
	@RequestMapping(value = "/admin/Inter_arm_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String Inter_arm_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date fr_dt = null;
		Date with_effect_from_d = null;
		String username = session.getAttribute("username").toString();

		String authority = request.getParameter("authority1");
		String date_of_authority = request.getParameter("date_of_authority1");
		String record_office_unit = request.getParameter("record_office_unit");
		String record_office_sus = request.getParameter("record_office_sus");
		String parent_arm_service = request.getParameter("parent_arm_service1");
		String with_effect_from = request.getParameter("with_effect_from1");
		String regt = request.getParameter("regt1");
		
		String jco_id = request.getParameter("jco_id");

		 if (authority.trim().equals("")) {
		 return "Please Enter Authority";
		
		 } 
		 if (!valid.isValidAuth(authority)) {
				return valid.isValidAuthMSG + " Authority ";
			}	
			if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority " + valid.isValidLengthMSG;	
			}
		 
		 
		 if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
		
		 return "Please Select Date of Authority";
		 }
		
		 else if (!valid.isValidDate(date_of_authority)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			else {
				fr_dt = format.parse(date_of_authority);
			}
			
		if (parent_arm_service.equals("0")) {

			return "Please Select Parent Arm Service";
		}
		if ((parent_arm_service.equals("0700") || parent_arm_service.equals("0800"))  && (regt==null || regt.equals("0"))) {
			return "Please Select Inter Arm Regt";
		}
		else if(!parent_arm_service.equals("0700") && !parent_arm_service.equals("0800")) {
			regt="0";
		}
		if (record_office_sus.trim().equals("")) {

			return "Please Enter Record Office SUS";
		}
		 if (!valid.SusNoLength(record_office_sus)) {
				return valid.SusNoMSG;
			}
	  
	    if (!valid.isOnlyAlphabetNumericSpaceNot(record_office_sus)) {
				return valid.isOnlyAlphabetNumericSpaceNotMSG + " Record Office SUS";
			}
	  
	  
		if (record_office_unit.trim().equals("")) {

			return "Please Enter Record Office Unit";
		}
	
		if (!valid.isUnit(record_office_unit)) {
            return " Unit Name " + valid.isUnitMSG;
         }

        if (!valid.isvalidLength(record_office_unit, valid.nameMax, valid.nameMin)) {
            return " Record Office Unit Name " + valid.isValidLengthMSG;
       
         }
		if (with_effect_from == null || with_effect_from.equals("") || with_effect_from.equals("DD/MM/YYYY")) {
			 return "Please Select With Effect From";
		}
	   else if (!valid.isValidDate(with_effect_from)) {
 			return valid.isValidDateMSG + " of With Effect From";	
		}
		else {
			with_effect_from_d = format.parse(with_effect_from);
		}
		
		String pre_ch_id = request.getParameter("pre_ch_id");
		
		String msg = "";
		
		try {
			 Query q0 = sessionhql.createQuery("select count(id) from TB_INTER_ARM_TRANSFER_JCO where with_effect_from=:with_effect_from and jco_id=:jco_id and  id!=:id and status!=-1").setTimestamp("with_effect_from", with_effect_from_d)
					 .setParameter("id", Integer.parseInt(pre_ch_id)).setParameter("jco_id", Integer.parseInt(jco_id));
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "With Effect From Already Exists";
				}
				
			if (Integer.parseInt(pre_ch_id) == 0) {
				TB_INTER_ARM_TRANSFER_JCO inter_arm_obj = new TB_INTER_ARM_TRANSFER_JCO();
				inter_arm_obj.setAuthority(authority);
				inter_arm_obj.setDate_of_authority(fr_dt);
				inter_arm_obj.setStatus(0);
				inter_arm_obj.setRecord_office_sus(record_office_sus);
				inter_arm_obj.setWith_effect_from(with_effect_from_d);
				inter_arm_obj.setRegt(regt);
				inter_arm_obj.setParent_arm_service(parent_arm_service);
				inter_arm_obj.setRecord_office_unit(record_office_unit);
				
				inter_arm_obj.setJco_id(Integer.parseInt(jco_id));

				inter_arm_obj.setCreated_by(username);
				inter_arm_obj.setCreated_date(date);
				inter_arm_obj.setInitiated_from("u");
				int id = (int) sessionhql.save(inter_arm_obj);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_INTER_ARM_TRANSFER_JCO set modified_by=:modified_by ,modified_date=:modified_date , authority=:authority, date_of_authority=:fr_dt , "
						+ "record_office_unit=:record_office_unit,record_office_sus=:record_office_sus ,parent_arm_service=:parent_arm_service,	\n"
						+ "with_effect_from=:with_effect_from,"
						+ "regt=:regt,jco_id=:jco_id,status=:status where  id=:id";
				Query query = sessionhql.createQuery(hql).setParameter("status", 0).setString("authority", authority)
						.setString("record_office_sus", record_office_sus)
						// .setString("gazetted", isgazetted).setString("civil_service", isCivil)
						.setTimestamp("fr_dt", fr_dt).setTimestamp("with_effect_from", with_effect_from_d)
						.setString("record_office_unit", record_office_unit).setString("parent_arm_service", parent_arm_service)
						
						.setParameter("jco_id", Integer.parseInt(jco_id)).setString("regt", regt)
						.setInteger("id", Integer.parseInt(pre_ch_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date());

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

	// @RequestMapping(value = "/admin/Inter_arm_delete_action", method =
	// RequestMethod.POST)
	// public @ResponseBody String pre_cadet_delete_action(ModelMap Mmap,
	// HttpSession session, HttpServletRequest request)
	// throws ParseException {
	// String msg = "";
	// Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	// Transaction tx = sessionHQL.beginTransaction();
	// int id = Integer.parseInt(request.getParameter("pre_ch_id"));
	// try {
	// String hqlUpdate = "delete from TB_INTER_ARM_TRANSFER_JCO where id=:id";
	// int app = sessionHQL.createQuery(hqlUpdate).setInteger("id",
	// id).executeUpdate();
	// tx.commit();
	// sessionHQL.close();
	// if (app > 0) {
	// msg = "1";
	// } else {
	// msg = "0";
	// }
	// } catch (Exception e) {
	//
	// }
	// return msg;
	// }

	@RequestMapping(value = "/admin/getInterArmJCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER_JCO> getInterArm(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_INTER_ARM_TRANSFER_JCO where  jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_INTER_ARM_TRANSFER_JCO> list = (List<TB_INTER_ARM_TRANSFER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_INTER_ARM_TRANSFER_JCO> getInterArm1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER_JCO where  status='0' and jco_id=:jco_id order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_INTER_ARM_TRANSFER_JCO> list = (List<TB_INTER_ARM_TRANSFER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_InterArmTransfer(TB_INTER_ARM_TRANSFER_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql1 = "update TB_INTER_ARM_TRANSFER_JCO set status=:status  "
					+ " where  jco_id=:jco_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_INTER_ARM_TRANSFER_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1)
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

	public String Update_Census_InterArm(TB_CENSUS_JCO_OR_PARENT obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_JCO_OR_PARENT set record_office=:record_office,record_office_sus=:record_office_sus,arm_service=:arm_service,regiment=:regiment where id=:jco_id ";

			Query query = sessionHQL.createQuery(hql).setInteger("jco_id", obj.getId())
					.setString("arm_service", obj.getArm_service()).setString("regiment", obj.getRegiment())
					.setString("record_office", obj.getRecord_office()).setString("record_office_sus", obj.getRecord_office_sus());
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

	@RequestMapping(value = "/admin/get_InterArmJCOReject", method = RequestMethod.POST)
	public @ResponseBody String get_InterArmReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String reject_remarks = request.getParameter("reject_remarks");

		String username = session.getAttribute("username").toString();
		TB_INTER_ARM_TRANSFER_JCO ChangeInter = new TB_INTER_ARM_TRANSFER_JCO();
		
		ChangeInter.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeInter.setId(Integer.parseInt(request.getParameter("ch_r_id")));
        ChangeInter.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_InterArmReject(ChangeInter, username);

		return msg1;

	}

	public String Update_Change_of_InterArmReject(TB_INTER_ARM_TRANSFER_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {


			String hql = "update TB_INTER_ARM_TRANSFER_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id()).setString("reject_remarks", obj.getReject_remarks())
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

	public @ResponseBody List<TB_INTER_ARM_TRANSFER_JCO> getChangeOfInterArm2( int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER_JCO> list = (List<TB_INTER_ARM_TRANSFER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_InterArmJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER_JCO> get_InterArm3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER_JCO> list = (List<TB_INTER_ARM_TRANSFER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/inter_arm_transfer_delete_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String inter_arm_transfer_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_INTER_ARM_TRANSFER_JCO where id=:id";
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
