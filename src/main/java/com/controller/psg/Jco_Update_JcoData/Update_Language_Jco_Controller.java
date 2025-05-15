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
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_LANGUAGE_JCO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_Language_Jco_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/admin/update_offr_language_detail_action_jco", method = RequestMethod.POST)
    public @ResponseBody String update_offr_language_detail_action_jco(ModelMap Mmap, HttpSession session,
                    HttpServletRequest request) throws ParseException {

            Session sessionhql = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionhql.beginTransaction();
            Date date = new Date();
            Date lan_doa = null;
            String username = session.getAttribute("username").toString();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String language = request.getParameter("language");
            String other_lan = request.getParameter("other_lan");
            String lang_std = request.getParameter("lang_std");
            String other_lan_std = request.getParameter("other_lan_std");
            String lang_ch_id = request.getParameter("lang_ch_id");
            String jco_id = request.getParameter("jco_id");
            String lang_authority = request.getParameter("lang_authority");
            String lang_doa = request.getParameter("lang_doa");
            Date enroll_date = format.parse(request.getParameter("enroll_date"));
            String msg = "";
            
            if (lang_authority == null || lang_authority.equals("")) {
	            return "Please Enter the Authority";
	        }
	        if (!validation.isValidAuth(lang_authority)) {
		 		return validation.isValidAuthMSG + " Authority";	
			}
	 		if (!validation.isvalidLength(lang_authority,validation.authorityMax,validation.authorityMin)) {
	 			return "Authority " + validation.isValidLengthMSG;	
			}
	        if (lang_doa == null || lang_doa.equals("null") || lang_doa.equals("DD/MM/YYYY") || lang_doa.equals("")) {
	                return "Please Select Date of Authority";
	        }
	        if (!validation.isValidDate(lang_doa)) {
	 			return validation.isValidDateMSG + " of Authority";	
			}
	        if (!lang_doa.equals("") && !lang_doa.equals("DD/MM/YYYY")) {
	            lan_doa = format.parse(lang_doa);
	        }
	        if (mcommon.CompareDate(lan_doa,enroll_date) == 0) {
	                return "Authority Date should be Greater than Commission Date";
	        }
	        if (language == null || language.equals("0")) {
	                return "Please Select the Language";
	        }
	        if (lang_std == null || lang_std.equals("0")) {
	                return "Please Select the Language Std";
	        }
	        if (lang_doa == null || lang_doa.equals("")) {
	                return "Please Enter Date of Authority";
	        }

            try {
                    if (Integer.parseInt(lang_ch_id) == 0) {
                            TB_CENSUS_LANGUAGE_JCO lang = new TB_CENSUS_LANGUAGE_JCO();
                            lang.setJco_id(Integer.parseInt(jco_id));
                            lang.setLanguage(Integer.parseInt(language));
                            lang.setOther_language(other_lan);
                            lang.setLang_std(Integer.parseInt(lang_std));
                            lang.setOther_lang_std(other_lan_std);
                            lang.setF_exam_pass("");
                            // lang.setF_mother_tougue(0);
                            lang.setForeign_language(0);
                            lang.setF_lang_prof(0);
                            lang.setAuthority(lang_authority);
                            lang.setDate_of_authority(lan_doa);
                            lang.setCreated_by(username);
                            lang.setCreated_on(date);
                            lang.setInitiated_from("u");

                            int id = (int) sessionhql.save(lang);
                            msg = Integer.toString(id);
                    } else {
                            /*--S---REJECT----*/
                            /*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
                            CC.setUpdate_ofr_status(0);
                            sessionhql.save(CC);*/
                            /*---E--REJECT----*/
                            String hql = "update TB_CENSUS_LANGUAGE_JCO set modify_by=:modify_by ,modify_on=:modify_on ,language=:language,lang_std=:lang_std, "
                                            + " authority=:authority,date_of_authority=:date_of_authority,status=:status,other_language=:other_language,"
                                            +"  other_lang_std=:other_lang_std where  id=:id";
                            Query query = sessionhql.createQuery(hql).setInteger("language", Integer.parseInt(language))
                                            .setInteger("lang_std", Integer.parseInt(lang_std))
                                            .setInteger("id", Integer.parseInt(lang_ch_id)).setString("modify_by", username)
                                            .setTimestamp("modify_on", date).setTimestamp("date_of_authority", lan_doa)
                                            .setString("authority", lang_authority).setInteger("status", 0)
                                            .setString("other_language", other_lan).setString("other_lang_std", other_lan_std);

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
	@RequestMapping(value = "/admin/update_offr_foreginlanguage_detail_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_foreginlanguage_detail_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date lan_doa = null;

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH); 
		String username = session.getAttribute("username").toString();
		String language = request.getParameter("language");
		String lang_prof = request.getParameter("lang_prof");
		String exam_pass = request.getParameter("exam_pass");
		String lang_std = request.getParameter("lang_std");
		String lang_authority = request.getParameter("lang_authority");
		String lang_doa = request.getParameter("lang_doa");
		String f_ot_language = request.getParameter("f_ot_language");
		String f_ot_prof = request.getParameter("f_ot_prof");
		String f_ot_std = request.getParameter("f_ot_std");
	
	   if (!lang_doa.equals("") && !lang_doa.equals("DD/MM/YYYY")) {
			lan_doa = format.parse(lang_doa);
		}
		
	
		String lang_ch_id = request.getParameter("lang_ch_id");
		String jco_id = request.getParameter("jco_id");

		String msg = "";
		
		if (lang_authority == null || lang_authority.equals("")) {
			return "Please Enter the Authority";
		}
		
		if (lang_doa == null || lang_doa.equals("null") || lang_doa.equals("DD/MM/YYYY") || lang_doa.equals("")) {
			return "Please Select Date of Authority";
		}

		if (language == null || language.equals("0")) {
			return "Please Select the Language";
		}
		if (lang_std == null || lang_std.equals("0")) {
			return "Please Select the Language Std";
		}
		if (lang_prof == null || lang_prof.equals("0")) {
			return "Please Select the Language Proof";
		}
		if (exam_pass == null || exam_pass.equals("")) {
			return "Please Enter the Exam Passed";
		}

		/*try {*/
			if (Integer.parseInt(lang_ch_id) == 0) {
				TB_CENSUS_LANGUAGE_JCO lang = new TB_CENSUS_LANGUAGE_JCO();
				lang.setJco_id(Integer.parseInt(jco_id));

				lang.setLanguage(0);
				lang.setF_exam_pass((exam_pass));
				lang.setLang_std(Integer.parseInt(lang_std));
				lang.setForeign_language(Integer.parseInt(language));
				lang.setF_lang_prof(Integer.parseInt(lang_prof));
				lang.setAuthority(lang_authority);
				lang.setDate_of_authority(lan_doa);
				lang.setCreated_by(username);
				lang.setCreated_on(date);
				lang.setStatus(0);
				lang.setF_other_prof(f_ot_prof);
				lang.setF_other_language(f_ot_language);
				lang.setF_other_lang_std(f_ot_std);
				lang.setInitiated_from("u");

				int id = (int) sessionhql.save(lang);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
			/*	TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_LANGUAGE_JCO set modify_by=:modify_by ,modify_on=:modify_on ,foreign_language=:foreign_language,f_lang_prof=:f_lang_prof,"
						+ " f_exam_pass=:f_exam_pass,authority=:authority,date_of_authority=:date_of_authority,lang_std=:lang_std,status=:status,"
					+" f_other_language=:f_other_language,f_other_lang_std=:f_other_lang_std,f_other_prof=:f_other_prof  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("foreign_language", Integer.parseInt(language))
						.setInteger("f_lang_prof", Integer.parseInt(lang_prof)).setString("f_exam_pass", exam_pass)
						.setString("authority", lang_authority).setTimestamp("date_of_authority", lan_doa)
						.setInteger("lang_std", Integer.parseInt(lang_std))
						.setInteger("id", Integer.parseInt(lang_ch_id)).setString("modify_by", username)
						.setTimestamp("modify_on", date).setInteger("status", 0)
						.setString("f_other_language", f_ot_language).setString("f_other_lang_std", f_ot_std)
						.setString("f_other_prof", f_ot_prof);

				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
			tx.commit();
		/*} catch (RuntimeException e) {
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
		}*/

		sessionhql.close();
		
		return msg;
	}

	@RequestMapping(value = "/admin/update_offr_getlanguagedetailsData_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE_JCO> update_offr_getlanguagedetailsData_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_CENSUS_LANGUAGE_JCO where jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_LANGUAGE_JCO> list = (List<TB_CENSUS_LANGUAGE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();

	
		return list;
	}

	@RequestMapping(value = "/admin/update_offr_language_delete_action_jco", method = RequestMethod.POST)
	public @ResponseBody String update_offr_language_delete_action_jco(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("lang_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_LANGUAGE_JCO where id=:id";
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

	/*--------------------- For Approval ----------------------------------*/

	public @ResponseBody List<TB_CENSUS_LANGUAGE_JCO> update_census_getlanguagedetailsData1(int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE_JCO where   jco_id=:jco_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_LANGUAGE_JCO> list = (List<TB_CENSUS_LANGUAGE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Language(TB_CENSUS_LANGUAGE_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			/*
			 * String hql1 =
			 * "update TB_CENSUS_LANGUAGE_JCO set status=:status where cen_id=:cen_id and comm_id=:comm_id and status != '0' "
			 * ;
			 * 
			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",
			 * 2).setInteger("cen_id", obj.getCen_id())
			 * .setInteger("comm_id",obj.getComm_id());
			 * 
			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."
			 * :"Data Not Updated.";
			 */

			String hql = "update TB_CENSUS_LANGUAGE_JCO set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
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

	@RequestMapping(value = "/admin/getlanguage_Reject_jco", method = RequestMethod.POST)
	public @ResponseBody String getlanguage_Reject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String reject_remarks = request.getParameter("reject_remarks");

		
		String username = session.getAttribute("username").toString();
		TB_CENSUS_LANGUAGE_JCO lan = new TB_CENSUS_LANGUAGE_JCO();
		lan.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		lan.setReject_remarks(reject_remarks);
		String msg1 = Children_Reject(lan, username);

		return msg1;

	}

	public String Children_Reject(TB_CENSUS_LANGUAGE_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {


			String hql = "update TB_CENSUS_LANGUAGE_JCO set modify_by=:modified_by,modify_on=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where  jco_id=:jco_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())

					.setInteger("jco_id", obj.getJco_id());

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

	public @ResponseBody List<TB_CENSUS_LANGUAGE_JCO> update_census_getlanguagedetailsData2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE_JCO where  jco_id=:jco_id and status='3' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_LANGUAGE_JCO> list = (List<TB_CENSUS_LANGUAGE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/update_offr_getlanguagedetailsData3_jco", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE_JCO> update_offr_getlanguagedetailsData3_jco(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_CENSUS_LANGUAGE_JCO where jco_id=:jco_id and status='3' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		List<TB_CENSUS_LANGUAGE_JCO> list = (List<TB_CENSUS_LANGUAGE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
