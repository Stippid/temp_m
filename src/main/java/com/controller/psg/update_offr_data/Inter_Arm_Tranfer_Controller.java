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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Inter_Arm_Tranfer_Controller {
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/Inter_arm_URL", method = RequestMethod.GET)
	public ModelAndView Inter_arm_URL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	      
		Boolean val = roledao.ScreenRedirect("Inter_arm_URL", session.getAttribute("roleid").toString());
          if(val == false) {
                  return new ModelAndView("AccessTiles");
          }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		return new ModelAndView("Inter_arm_transferTiles");
	}

	@RequestMapping(value = "/admin/Inter_arm_action", method = RequestMethod.POST)
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
		String parent_arm_service = request.getParameter("parent_arm_service1");
		String with_effect_from = request.getParameter("with_effect_from1");
		String regt = request.getParameter("regt1");
		String census_id = request.getParameter("census_id");
		
		BigInteger comm_id = BigInteger.ZERO;
		
		comm_id = new BigInteger(request.getParameter("comm_id"));

		 if (authority.equals("")) {
			 return "Please Enter Authority";
		 } 
	     if (!validation.isValidAuth(authority)) {
			return validation.isValidAuthMSG + " Authority";	
		 }
 		 if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			return "Authority " + validation.isValidLengthMSG;	
		 }	
		 if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			 return "Please Select Date of Authority";
		 }
		 if (!validation.isValidDate(date_of_authority)) {
		  	return  validation.isValidDateMSG + " of Authority";	
		 }
		//
		// if (old_arm_service.equals("")) {
		//
		// return "Please Enter Old Arm Services";
		// } if (old_regt.equals("")) {
		//
		// return "Please Enter Old Regt";
		// }
		if (parent_arm_service.equals("0")) {
			return "Please Select Parent Arm Service";
		}
		if ((parent_arm_service.equals("0700") || parent_arm_service.equals("0800"))  && (regt==null || regt.equals("0"))) {
			return "Please Select Inter Arm Regt";
		}
		else if(!parent_arm_service.equals("0700") && !parent_arm_service.equals("0800")) {
			regt="0";
		}
		// if (regt.equals("0")) {
		//
		// return "Please Select Regt";
		// } if (with_effect_from.equals("")) {
		//
		// return "Please Select Date of With Effect From";
		// }
		if (with_effect_from == null || with_effect_from.equals("null") || with_effect_from.equals("DD/MM/YYYY") || with_effect_from.equals("")) {
			 return "Please Enter With Effect From";
		}
	    if (!validation.isValidDate(with_effect_from)) {
			return  validation.isValidDateMSG + " of With Effect From";	
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			fr_dt = format.parse(date_of_authority);
		}
		if (!with_effect_from.equals("") && !with_effect_from.equals("DD/MM/YYYY")) {
		   with_effect_from_d = format.parse(with_effect_from);
		}

		String pre_ch_id = request.getParameter("pre_ch_id");
		// String p_id = request.getParameter("p_id");
		String msg = "";
		
		String Count= CountOfInterArmPendingUpdateCommissioning(comm_id);
		
		if(Integer.parseInt(Count) > 0) {
			 return "Data is Already Exists in Pending Status of Arm/Service(Update Commissioning) Please Approve that Data First.";
		}
		//try {
			 Query q0 = sessionhql.createQuery("select count(id) from TB_INTER_ARM_TRANSFER where with_effect_from=:with_effect_from and comm_id=:comm_id and  id!=:id and status!=-1").setTimestamp("with_effect_from", with_effect_from_d)
					 .setParameter("id", Integer.parseInt(pre_ch_id)).setParameter("comm_id", comm_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					return "With Effect From Already Exists";
				}
				
			if (Integer.parseInt(pre_ch_id) == 0) {
				
				if (Integer.parseInt(Count) == 0) {
					TB_INTER_ARM_TRANSFER inter_arm_obj = new TB_INTER_ARM_TRANSFER();
					inter_arm_obj.setAuthority(authority);
					inter_arm_obj.setDate_of_authority(fr_dt);
					inter_arm_obj.setStatus(0);
					inter_arm_obj.setWith_effect_from(with_effect_from_d);
					inter_arm_obj.setRegt(regt);
					inter_arm_obj.setParent_arm_service(parent_arm_service);
					inter_arm_obj.setCensus_id(Integer.parseInt(census_id));
					inter_arm_obj.setComm_id(comm_id);

					inter_arm_obj.setCreated_by(username);
					inter_arm_obj.setCreated_date(date);

					int id = (int) sessionhql.save(inter_arm_obj);
					msg = Integer.toString(id);
				}
				
			} else {
				/*--S---REJECT----*/
				/*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by ,modified_date=:modified_date , "
						+ "authority=:authority, date_of_authority=:fr_dt , "
						+ "parent_arm_service=:parent_arm_service,	\n"
						+ "with_effect_from=:with_effect_from,"
						+ "regt=:regt,comm_id=:comm_id,census_id=:census_id,status=:status where  id=:id";
				Query query = sessionhql.createQuery(hql).setParameter("status", 0).setString("authority", authority)
						// .setString("gazetted", isgazetted).setString("civil_service", isCivil)
						.setTimestamp("fr_dt", fr_dt).setTimestamp("with_effect_from", with_effect_from_d)
						.setString("parent_arm_service", parent_arm_service)
						.setParameter("census_id", Integer.parseInt(census_id))
						.setParameter("comm_id", comm_id).setString("regt", regt)
						.setInteger("id", Integer.parseInt(pre_ch_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date());

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
		/*} catch (RuntimeException e) {
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
*/
		return msg;
	}
	//----CHANDANI 16-7

	public String CountOfInterArmPendingUpdateCommissioning(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery("select count(comm_id) from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and status='0' and census_id = 0 ");			
			q1.setParameter("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();			
			if(list.size() > 0) {
				count = String.valueOf(list.get(0));
			}
			tx.commit();
		} catch (RuntimeException e) {			
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
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
	// String hqlUpdate = "delete from TB_INTER_ARM_TRANSFER where id=:id";
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

	@RequestMapping(value = "/admin/getInterArm", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER> getInterArm(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;
	}

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_INTER_ARM_TRANSFER> getInterArm1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and status='0' and comm_id=:comm_id order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public String Update_InterArmTransfer(TB_INTER_ARM_TRANSFER obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			String hql1 = "update TB_INTER_ARM_TRANSFER set status=:status  "
					+ " where  comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql)

					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1).setInteger("census_id", obj.getCensus_id())
					.setBigInteger("comm_id", obj.getComm_id());

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

	public String Update_Comm_InterArm(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set parent_arm=:parent_arm,regiment=:regiment where id=:comm_id ";

			Query query = sessionHQL.createQuery(hql).setBigInteger("comm_id", obj.getId())
					.setString("parent_arm", obj.getParent_arm()).setString("regiment", obj.getRegiment());

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

	@RequestMapping(value = "/admin/get_InterArmReject", method = RequestMethod.POST)
	public @ResponseBody String get_InterArmReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_INTER_ARM_TRANSFER ChangeInter = new TB_INTER_ARM_TRANSFER();
		ChangeInter.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		ChangeInter.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ChangeInter.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeInter.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Update_Change_of_InterArmReject(ChangeInter, username);

		return msg1;

	}

	public String Update_Change_of_InterArmReject(TB_INTER_ARM_TRANSFER obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		// String msg1= "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCensus_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
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

	public @ResponseBody List<TB_INTER_ARM_TRANSFER> getChangeOfInterArm2(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/get_InterArm3", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER> get_InterArm3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/inter_arm_transfer_delete_action", method = RequestMethod.POST)
	public @ResponseBody String inter_arm_transfer_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_INTER_ARM_TRANSFER where id=:id";
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
