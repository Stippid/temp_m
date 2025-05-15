package com.controller.psg.update_3008;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Inter_Arm_Tranfer_Controller;
import com.controller.validation.ValidationController;
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Inter_Arm_Service_3008 {
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	@Autowired
	CensusAprovedDAO censusAprovedDAO;
	Inter_Arm_Tranfer_Controller IntArmTran = new Inter_Arm_Tranfer_Controller();

	@RequestMapping(value = "/admin/Inter_arm__3008_action", method = RequestMethod.POST)
	public @ResponseBody String Inter_arm__3008_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
		Date comm_date = format.parse(request.getParameter("comm_date"));
		
		
		BigInteger comm_id = BigInteger.ZERO;

		comm_id = new BigInteger(request.getParameter("comm_id"));

		if (authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!validation.isValidAuth(authority)) {
			return validation.isValidAuthMSG + " Authority";
		}
		if (!validation.isvalidLength(authority, validation.authorityMax, validation.authorityMin)) {
			return "Authority " + validation.isValidLengthMSG;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!validation.isValidDate(date_of_authority)) {
			return validation.isValidDateMSG + " of Authority";
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			fr_dt = format.parse(date_of_authority);
		}
		if (mcommon.CompareDate(fr_dt, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		if (parent_arm_service.equals("0")) {
			return "Please Select Parent Arm Service";
		}
		if ((parent_arm_service.equals("0700") || parent_arm_service.equals("0800"))
				&& (regt == null || regt.equals("0"))) {
			return "Please Select Inter Arm Regt";
		} else if (!parent_arm_service.equals("0700") && !parent_arm_service.equals("0800")) {
			regt = "0";
		}
		if (with_effect_from == null || with_effect_from.equals("null") || with_effect_from.equals("DD/MM/YYYY")
				|| with_effect_from.equals("")) {
			return "Please Enter With Effect From";
		}
		if (!validation.isValidDate(with_effect_from)) {
			return validation.isValidDateMSG + " of With Effect From";
		}
		
		if (!with_effect_from.equals("") && !with_effect_from.equals("DD/MM/YYYY")) {
			with_effect_from_d = format.parse(with_effect_from);
		}

		String pre_ch_id = request.getParameter("pre_ch_id");

		String msg = "";

		String Count = CountOfInterArmPendingUpdateCommissioning(comm_id);

		if (Integer.parseInt(Count) > 0) {
			return "Data is Already Exists in Pending Status of Arm/Service(Update Commissioning) Please Approve that Data First.";
		}
		try {
			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_INTER_ARM_TRANSFER where with_effect_from=:with_effect_from and comm_id=:comm_id and  id!=:id and status!=-1")
					.setTimestamp("with_effect_from", with_effect_from_d)
					.setParameter("id", Integer.parseInt(pre_ch_id)).setParameter("comm_id", comm_id);
			Long c = (Long) q0.uniqueResult();
			if (c > 0) {
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
					inter_arm_obj.setModified_by(username);
					inter_arm_obj.setModified_date(date);
					inter_arm_obj.setCreated_by(username);
					inter_arm_obj.setCreated_date(date);

					int id = (int) sessionhql.save(inter_arm_obj);
					msg = Integer.toString(id);
				}

			} else {

				String hql = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by ,modified_date=:modified_date , "
						+ "authority=:authority, date_of_authority=:fr_dt , "
						+ "parent_arm_service=:parent_arm_service,	\n" + "with_effect_from=:with_effect_from,"
						+ "regt=:regt,comm_id=:comm_id,census_id=:census_id,status=:status where  id=:id";
				Query query = sessionhql.createQuery(hql).setParameter("status", 1).setString("authority", authority)
						.setTimestamp("fr_dt", fr_dt).setTimestamp("with_effect_from", with_effect_from_d)
						.setString("parent_arm_service", parent_arm_service)
						.setParameter("census_id", Integer.parseInt(census_id)).setParameter("comm_id", comm_id)
						.setString("regt", regt).setInteger("id", Integer.parseInt(pre_ch_id))
						.setString("modified_by", username).setTimestamp("modified_date", new Date());

				msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";

			}

			String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and status='0' and comm_id=:comm_id order by id";
			Query query5 = sessionhql.createQuery(hqlUpdate).setInteger("census_id", Integer.parseInt(census_id))
					.setBigInteger("comm_id", comm_id);
			List<TB_INTER_ARM_TRANSFER> InterArmTransfer = (List<TB_INTER_ARM_TRANSFER>) query5.list();

			TB_TRANS_PROPOSED_COMM_LETTER Comm = new TB_TRANS_PROPOSED_COMM_LETTER();

			Comm.setId(comm_id);
			if (InterArmTransfer.size() > 0) {

				TB_INTER_ARM_TRANSFER InArmTran = new TB_INTER_ARM_TRANSFER();

				InArmTran.setCensus_id(Integer.parseInt(request.getParameter("census_id")));

				InArmTran.setComm_id(new BigInteger(request.getParameter("comm_id")));

				InArmTran.setModified_date(new Date());

				String hql1 = "update TB_INTER_ARM_TRANSFER set status=:status  "
						+ " where  comm_id=:comm_id and (status != '0' and status != '-1') ";

				Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", comm_id);

				msg = query1.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";

				String hql3 = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

				Query query3 = sessionhql.createQuery(hql3)

						.setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("status", 1).setInteger("census_id", Integer.parseInt(census_id))
						.setBigInteger("comm_id", comm_id);

				msg = query3.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";

				Comm.setParent_arm(InterArmTransfer.get(0).getParent_arm_service());

				Comm.setRegiment(InterArmTransfer.get(0).getRegt());

				String hql4 = "update TB_TRANS_PROPOSED_COMM_LETTER set parent_arm=:parent_arm,regiment=:regiment where id=:comm_id ";

				Query query4 = sessionhql.createQuery(hql4).setBigInteger("comm_id", comm_id)
						.setString("parent_arm", parent_arm_service).setString("regiment", regt);

				msg = query4.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully.";

			}
			
		    int censusid = Integer.parseInt(census_id);
			if(censusid>0) {
				  int count = censusAprovedDAO.checkdatapendingintable(comm_id,"inter_arm_transfer");
			      if(count == 0) {

			    	  String hql = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
								+ " where comm_id=:comm_id ";
		 	        	 Query query1 =sessionhql.createQuery(hql).setBigInteger("comm_id", comm_id).setInteger("update_ofr_status", 1);			    		
		 	           	 msg = query1.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
			      }
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

	public String CountOfInterArmPendingUpdateCommissioning(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"select count(comm_id) from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and status='0' and census_id = 0 ");
			q1.setParameter("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			if (list.size() > 0) {
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
}
