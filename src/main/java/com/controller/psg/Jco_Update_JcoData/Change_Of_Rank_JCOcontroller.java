package com.controller.psg.Jco_Update_JcoData;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Jco_Census.Census_Jco_OR_Controller;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Jco_Census.Search_JCOsDao;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_ARMY_NO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_RANK_JCO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_Of_Rank_JCOcontroller {
	
	ValidationController validation = new ValidationController();
	Psg_CommonController pcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	@Autowired
	private Search_JCOsDao SJDAO;
	/*--------------------- Start Change of Rank----------------------------------*/
	@RequestMapping(value = "/Change_of_Rank_JCOaction", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> Change_of_Rank_JCOaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Map<String, String> data = new HashMap<>();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date dt_authority = null;
		Date dt_rank = null;
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String authority = request.getParameter("authority");
		String date_of_authority = request.getParameter("date_of_authority");
		String rank = request.getParameter("rank");
		String date_of_rank = request.getParameter("date_of_rank");
		String date_of_attestation = request.getParameter("date_of_attestation");
		Date enroll_date = format.parse(request.getParameter("enroll_date"));
		String ch_r_id = request.getParameter("ch_r_id");
		String pre_category = request.getParameter("preCategory");
		String change_category = request.getParameter("change_category");
		String pre_rank = request.getParameter("prerank");
		String army_no1 = request.getParameter("army_no1");
		String army_no2 = request.getParameter("army_no2");
		String army_no3 = request.getParameter("army_no3");
		String army_no_id = request.getParameter("army_no_id");
		String dateOfAttestaion = request.getParameter("dateOfAttestaion");
		int jco_id = 0;
		if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
			jco_id = Integer.parseInt(request.getParameter("jco_id"));
		}
      
		
		
		if (authority == null || authority.equals("") || authority.equals("null")) {
			data.put("error", "Please Enter Authority.");
			return data;
		}
	
		if (!validation.isValidAuth(authority)) {
			data.put("error",validation.isValidAuthMSG + " Authority");
	 		return data;
		}
 		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
 			data.put( "error","Authority "+ validation.isValidLengthMSG );	
 			return data;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			data.put("error", "Please Select Date Of Authority.");
			return data;
		}
		else if (!validation.isValidDate(date_of_authority)) {
 			data.put( "error",validation.isValidDateMSG + " of Authority");	
 			return data;
		}	
		else {
			dt_authority = format.parse(date_of_authority);
		}
		if (pcommon.CompareDate(dt_authority,enroll_date) == 0) {
			data.put("error", "Authority Date should be Greater than Enrollment Date");
			return data;
		}
		if (rank == null || rank.equals("0") || rank.equals("null")) {
			data.put("error", "Please Select Rank.");
			return data;
		}
	
		if(pre_rank.equals("RECTS")  && dateOfAttestaion!=null && dateOfAttestaion.equals("")) {
			if (date_of_attestation == null || date_of_attestation.equals("null") || date_of_attestation.equals("DD/MM/YYYY") || date_of_attestation.equals("")) {
				data.put("error", "Please Select Date Of Attestation.");
				return data;
			}
			if (!validation.isValidDate(date_of_attestation)) {	
	 			data.put("error",validation.isValidDateMSG + " of Attestation");
		 		return data;
			}
			
		}
		if (date_of_rank == null || date_of_rank.equals("null") || date_of_rank.equals("DD/MM/YYYY") || date_of_rank.equals("")) {
			data.put("error", "Please Select Date Of Rank.");
			return data;
		}
		else if (!validation.isValidDate(date_of_rank)) {	
 			data.put("error",validation.isValidDateMSG + " of Rank");
	 		return data;
		}
		else {
			dt_rank = format.parse(date_of_rank);
		}
		if(pre_category.equals("OR") && change_category.equals("JCO")) {
			if(army_no1.equals("0")) {
				data.put("error", "Please Select Army No prefix.");
				return data;
			}
			if(army_no2.equals("")) {
				data.put("error", "Please Enter Army No.");
				return data;
			}
			 
			if (validation.isOnlyNumer(army_no2) == true) {
			    data.put("error"," Army No " + validation.isOnlyNumerMSG);
			    return data;
		     }
			if(army_no2.length()<1 || army_no2.length()>9) {
				data.put("error", "Please Enter Valid Army No.");
				return data;
			}
			
			
			if(army_no3.equals("0") || army_no3.equals("")) {
				data.put("error", "Please Enter Valid Army No.");
				return data;
			}
		}
		boolean isarmyNo=false;
		String army_no_final="";
		if(change_category.equals("JCO")) {
			if(!army_no1.equals("0") && !army_no2.equals("") && army_no2.length()>=1 && army_no2.length()<=9  
					&& !army_no3.equals("0") && !army_no3.equals("")) {
				army_no_final=army_no1+army_no2+army_no3;
				isarmyNo=true;
			}
		}
		/*if(change_category.equals("JCO")) {
			if( !army_no2.equals("") && army_no2.length()>=1 && army_no2.length()<=9  
					&& !army_no3.equals("0") && !army_no3.equals("")) {
				army_no_final=army_no2+army_no3;
				isarmyNo=true;
			}
		}*/
		if(isarmyNo) {
			Census_Jco_OR_Controller cjc=new Census_Jco_OR_Controller();
			String armsuffix = cjc.generate_army_no(army_no2);
			if(!armsuffix.equals(army_no3)) {
				data.put("error", "Please Enter Valid Army No.");
				return data;
			}
			Boolean d = SJDAO.getArmyNoAlreadyExist(army_no_final,"",army_no_id);
			if(!d) {
				data.put("error", "Army No Already Exists");
				return data;
			}
		}
		String msg = "";
		try {
			 Query q0 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK_JCO where date_of_rank=:date_of_rank and jco_id=:jco_id and  id!=:id and status!=-1").setTimestamp("date_of_rank", dt_rank)
					 .setParameter("id", Integer.parseInt(ch_r_id)).setParameter("jco_id", jco_id);
				Long c = (Long) q0.uniqueResult();
				if(c>0) {
					data.put("error", "Date Of Rank Already Exists");
					return data;
				}
			if (Integer.parseInt(ch_r_id) == 0) {
				TB_CHANGE_OF_RANK_JCO cr = new TB_CHANGE_OF_RANK_JCO();
				cr.setAuthority(authority);
				cr.setDate_of_authority(dt_authority);
				cr.setCategory(change_category);
				if(pre_rank.equals("RECTS") && dateOfAttestaion!=null && dateOfAttestaion.equals("")) {
				cr.setDate_of_attestation(format.parse(date_of_attestation));
				}
				cr.setRank(Integer.parseInt(rank));
				cr.setDate_of_rank(dt_rank);
				cr.setInitiated_from("u");
				cr.setJco_id(jco_id);
				cr.setCreated_by(username);
				cr.setCreated_date(date);
				cr.setStatus(0);
				int id = (int) sessionhql.save(cr);
				msg = Integer.toString(id);
				data.put("rank_id", msg);
				if(isarmyNo && !army_no_final.equals("")) {
					TB_CHANGE_ARMY_NO armyM=new TB_CHANGE_ARMY_NO();
					armyM.setCreated_by(username);
					armyM.setCreated_date(date);
					armyM.setStatus(0);
					armyM.setRank_id(id);
					armyM.setJco_id(jco_id);
					armyM.setArmy_no(army_no_final.trim());
					armyM.setInitiated_from("u");
					int id2 = (int) sessionhql.save(armyM);
					msg = Integer.toString(id2);
					data.put("army_no_id", msg);
				}
				else {
					data.put("army_no_id", "0");
				}
			} else {
				String hql = "update TB_CHANGE_OF_RANK_JCO set category=:category , authority=:authority,date_of_authority=:date_of_authority,"
						+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  ";
				if(pre_rank.equals("RECTS") && dateOfAttestaion!=null && dateOfAttestaion.equals("")) {
					hql	+= " ,date_of_attestation=:date_of_attestation ";
					}
						hql	+= " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority)
						.setDate("date_of_authority", dt_authority).setString("category", change_category)						
						.setInteger("rank", Integer.parseInt(rank)).setDate("date_of_rank", dt_rank)
						.setInteger("id", Integer.parseInt(ch_r_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);
				if(pre_rank.equals("RECTS") && dateOfAttestaion!=null && dateOfAttestaion.equals("")) {
					query.setTimestamp("date_of_attestation", format.parse(date_of_attestation));
					}
				msg = query.executeUpdate() > 0 ? "1" : "0";
				if(msg.equals("1"))
					data.put("rank_id",ch_r_id);
				else
					data.put("error", "Data Not Updated");
				if(isarmyNo && !army_no_final.equals("")) {
					if(Integer.parseInt(army_no_id)==0) {
						TB_CHANGE_ARMY_NO armyM=new TB_CHANGE_ARMY_NO();
						armyM.setCreated_by(username);
						armyM.setCreated_date(date);
						armyM.setStatus(0);
						armyM.setJco_id(jco_id);
						armyM.setRank_id(Integer.parseInt(ch_r_id));
						armyM.setArmy_no(army_no_final.trim());
						armyM.setInitiated_from("u");
						int id2 = (int) sessionhql.save(armyM);
						msg = Integer.toString(id2);
						data.put("army_no_id", msg);
					}
					else {
						String hql2 = "update TB_CHANGE_ARMY_NO set "
								+ "modified_by=:modified_by,"
								+ "modified_date=:modified_date,status=:status,army_no=:army_no where id=:id";
						Query query2 = sessionhql.createQuery(hql2)
								.setInteger("id", Integer.parseInt(army_no_id)).setString("modified_by", username)
								.setTimestamp("modified_date", new Date()).setInteger("status", 0).setString("army_no", army_no_final.trim());
						msg = query2.executeUpdate() > 0 ? "1" : "0";
						if(msg.equals("1"))
							data.put("army_no_id",army_no_id);
						else
							data.put("error", "Data Not Updated");
					}
				}
				else {
					if(Integer.parseInt(army_no_id)>0) {
						String hql2 = "delete FROM TB_CHANGE_ARMY_NO where id=:id";
						Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(army_no_id));
						msg = query2.executeUpdate() > 0 ? "1" : "0";
						if(msg.equals("1"))
							data.put("army_no_id","0");
					}
					else {
						data.put("army_no_id", "0");
					}
				}
			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
				
			}
			else
				pjc.update_JcoOr_status(jco_id,username);
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
	@RequestMapping(value = "/admin/getChangeOfRankJCOData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_OF_RANK_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/getChangeOfArmy_NO_JCOData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfArmy_NO_JCOData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int rank_id = Integer.parseInt(request.getParameter("rank_id"));
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_ARMY_NO where  rank_id=:rank_id and  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setInteger("rank_id", rank_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/getChangeOfArmy_NO_JCODataAPP", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfArmy_NO_JCODataAPP(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int rank_id = Integer.parseInt(request.getParameter("rank_id"));
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_ARMY_NO where  rank_id=:rank_id and  status = '1' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setInteger("rank_id", rank_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/getChangeOfArmy_NO_JCOData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfArmy_NO_JCOData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int rank_id = Integer.parseInt(request.getParameter("rank_id"));
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_ARMY_NO where  rank_id=:rank_id and  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setInteger("rank_id", rank_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*--------------------- End Change of Rank----------------------------------*/
	/*--------------------- For Approval ----------------------------------*/
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankDataJCO1( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public @ResponseBody List<TB_CHANGE_ARMY_NO> getChangeOfArmyNoDataJCO1(int rank_id, int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_ARMY_NO where  rank_id=:rank_id and  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setInteger("rank_id", rank_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_ARMY_NO> list = (List<TB_CHANGE_ARMY_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public String Update_Change_of_Rank(TB_CHANGE_OF_RANK_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_OF_RANK_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_CHANGE_OF_RANK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql2 = "update TB_CHANGE_ARMY_NO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";
			Query query2 = sessionHQL.createQuery(hql2).setInteger("status", 2)
					.setInteger("jco_id", obj.getJco_id());
			msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql3 = "update TB_CHANGE_ARMY_NO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0'";
			Query query3 = sessionHQL.createQuery(hql3).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query3.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_Census_Rank(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set category=:category,modified_by=:modified_by,modified_date=:modified_date,rank=:rank ";
					//+ "enroll_dt=:enroll_dt  ";
				if(obj.getDate_of_attestation()!=null) {
					hql+=" , date_of_attestation=:date_of_attestation ";
				}
				if(obj.getArmy_no()!=null && !obj.getArmy_no().equals("")) {
					hql+=" , army_no=:army_no ";
				}
			hql+=" where id=:jco_id ";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("jco_id", obj.getId())
					.setInteger("rank", obj.getRank()).setString("category", obj.getCategory());
				//	.setDate("enroll_dt", obj.getEnroll_dt())
			if(obj.getDate_of_attestation()!=null) {
				query.setTimestamp("date_of_attestation",obj.getDate_of_attestation() );
			}
			if(obj.getArmy_no()!=null && !obj.getArmy_no().equals("")) {
				query.setString("army_no",obj.getArmy_no() );
			}
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
	@RequestMapping(value = "/admin/getChangeOfRankJCOReject", method = RequestMethod.POST)
	public @ResponseBody String getChangeOfRankReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		String reject_remarks = request.getParameter("reject_remarks");
		String username = session.getAttribute("username").toString();
		TB_CHANGE_OF_RANK_JCO ChangeRank = new TB_CHANGE_OF_RANK_JCO();
		ChangeRank.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		ChangeRank.setId(Integer.parseInt(request.getParameter("ch_r_id")));
		ChangeRank.setReject_remarks(reject_remarks);
		String msg1 = Update_Change_of_Rank_Reject(ChangeRank, username);
		return msg1;
	}
	public String Update_Change_of_Rank_Reject(TB_CHANGE_OF_RANK_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		//String msg1 = "";
		try {
			String hql1 = "update TB_CHANGE_ARMY_NO set modified_by=:modified_by,modified_date=:modified_date,"
					+ "status=:status where rank_id=:rank_id and jco_id=:jco_id and status = '0'  ";
			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("rank_id", obj.getId());
			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";
			String hql = "update TB_CHANGE_OF_RANK_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
					+ " where  jco_id=:jco_id and status = '0' and id=:id ";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
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
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/getChangeOfRankDataJCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankData3(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CHANGE_OF_RANK_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/ChngofRank_delete_JCOaction", method = RequestMethod.POST)
	public @ResponseBody String ChngofRank_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_CHANGE_OF_RANK_JCO where id=:id";
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
