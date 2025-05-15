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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_Language_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/admin/update_offr_language_detail_action", method = RequestMethod.POST)
    public @ResponseBody String update_offr_language_detail_action(ModelMap Mmap, HttpSession session,
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
            String p_id = request.getParameter("p_id");
            String com_id = request.getParameter("com_id");
            String lang_authority = request.getParameter("lang_authority");
            String lang_doa = request.getParameter("lang_doa");
            Date comm_date = format.parse(request.getParameter("comm_date"));
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
	        if (mcommon.CompareDate(lan_doa,comm_date) == 0) {
	                return "Authority Date should be Greater than Commission Date";
	        }
	        if (language == null || language.equals("0")) {
	                return "Please Select the Language";
	        }
	        if (other_lan != null && !other_lan.trim().equals("")) {

				if (!validation.isOnlyAlphabet(other_lan)) {
					return " language Other " + validation.isOnlyAlphabetMSG;
				}	
				
				if (!validation.isvalidLength(other_lan, validation.nameMax, validation.nameMin)) {
					return " language Other " + validation.isValidLengthMSG;
				}
			}
	        if (lang_std == null || lang_std.equals("0")) {
	                return "Please Select the Language Std";
	        }
	        if (other_lan_std != null && !other_lan_std.trim().equals("")) {

				if (!validation.isOnlyAlphabet(other_lan_std)) {
					return " language Std Other " + validation.isOnlyAlphabetMSG;
				}	
				
				if (!validation.isvalidLength(other_lan_std, validation.nameMax, validation.nameMin)) {
					return " language Std Other " + validation.isValidLengthMSG;
				}
			}

            try {
                    if (Integer.parseInt(lang_ch_id) == 0) {
                            TB_CENSUS_LANGUAGE lang = new TB_CENSUS_LANGUAGE();
                            lang.setCen_id(Integer.parseInt(p_id));
                            lang.setComm_id(new BigInteger(com_id));
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

                            int id = (int) sessionhql.save(lang);
                            msg = Integer.toString(id);
                    } else {
                            /*--S---REJECT----*/
                            /*TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
                            CC.setUpdate_ofr_status(0);
                            sessionhql.save(CC);*/
                            /*---E--REJECT----*/
                            String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modify_by ,modify_on=:modify_on ,language=:language,lang_std=:lang_std, "
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
                            mcommon.update_offr_status(Integer.parseInt(p_id),username);
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
	@RequestMapping(value = "/admin/update_offr_foreginlanguage_detail_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_foreginlanguage_detail_action(ModelMap Mmap, HttpSession session,
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
		String p_id = request.getParameter("p_id");
		String com_id = request.getParameter("com_id");

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
     
		if (language == null || language.equals("0")) {
			return "Please Select the Language";
		}
	    if (f_ot_language != null && !f_ot_language.trim().equals("")) {

			if (!validation.isOnlyAlphabet(f_ot_language)) {
				return " language Other " + validation.isOnlyAlphabetMSG;
			}	
			
			if (!validation.isvalidLength(f_ot_language, validation.nameMax, validation.nameMin)) {
				return " language Other " + validation.isValidLengthMSG;
			}
		}
		if (lang_std == null || lang_std.equals("0")) {
			return "Please Select the Language Std";
		}
	   if (f_ot_std != null && !f_ot_std.trim().equals("")) {

			if (!validation.isOnlyAlphabet(f_ot_std)) {
				return " language Std Other " + validation.isOnlyAlphabetMSG;
			}	
			
			if (!validation.isvalidLength(f_ot_std, validation.nameMax, validation.nameMin)) {
				return " language Std Other " + validation.isValidLengthMSG;
			}
		}
		if (lang_prof == null || lang_prof.equals("0")) {
			return "Please Select the Language Prof";
		}
	   if (f_ot_prof != null && !f_ot_prof.trim().equals("")) {

			if (!validation.isOnlyAlphabet(f_ot_prof)) {
				return " language Prof Other " + validation.isOnlyAlphabetMSG;
			}	
			
			if (!validation.isvalidLength(f_ot_prof, validation.nameMax, validation.nameMin)) {
				return " language Prof Other " + validation.isValidLengthMSG;
			}
		}
		if (exam_pass == null || exam_pass.equals("")) {
			return "Please Enter the Exam Passed";
		}

		/*try {*/
			if (Integer.parseInt(lang_ch_id) == 0) {
				TB_CENSUS_LANGUAGE lang = new TB_CENSUS_LANGUAGE();
				lang.setCen_id(Integer.parseInt(p_id));
				lang.setComm_id(new BigInteger(com_id));

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

				int id = (int) sessionhql.save(lang);
				msg = Integer.toString(id);
			} else {
				/*--S---REJECT----*/
			/*	TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();
				CC.setUpdate_ofr_status(0);
				sessionhql.save(CC);*/
				/*---E--REJECT----*/
				String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modify_by ,modify_on=:modify_on ,foreign_language=:foreign_language,f_lang_prof=:f_lang_prof,"
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
			mcommon.update_offr_status(Integer.parseInt(p_id),username);
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

	@RequestMapping(value = "/admin/update_offr_getlanguagedetailsData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_offr_getlanguagedetailsData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();

	
		return list;
	}

	@RequestMapping(value = "/admin/update_offr_language_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_offr_language_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("lang_ch_id"));
		try {
			String hqlUpdate = "delete from TB_CENSUS_LANGUAGE where id=:id";
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

	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData1(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id and comm_id=:comm_id and status=0 order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Language(TB_CENSUS_LANGUAGE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {

			/*
			 * String hql1 =
			 * "update TB_CENSUS_LANGUAGE set status=:status where cen_id=:cen_id and comm_id=:comm_id and status != '0' "
			 * ;
			 * 
			 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",
			 * 2).setInteger("cen_id", obj.getCen_id())
			 * .setInteger("comm_id",obj.getComm_id());
			 * 
			 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."
			 * :"Data Not Updated.";
			 */

			String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modified_by,modify_on=:modified_date,status=:status  "
					+ " where cen_id=:cen_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModify_on()).setInteger("status", 1)
					.setInteger("cen_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	@RequestMapping(value = "/admin/getlanguage_Reject", method = RequestMethod.POST)
	public @ResponseBody String getlanguage_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_CENSUS_LANGUAGE lan = new TB_CENSUS_LANGUAGE();
		lan.setCen_id(Integer.parseInt(request.getParameter("ch_id")));
		lan.setComm_id(new BigInteger(request.getParameter("comm_id")));
		lan.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Children_Reject(lan, username);

		return msg1;

	}

	public String Children_Reject(TB_CENSUS_LANGUAGE obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		String msg1 = "";
		try {
//			String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//					+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//
//			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
//					.setTimestamp("modified_date", new Date()).setInteger("update_ofr_status", 3)
//					.setInteger("census_id", obj.getCen_id()).setInteger("comm_id", obj.getComm_id());
//
//			msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			String hql = "update TB_CENSUS_LANGUAGE set modify_by=:modified_by,modify_on=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData2(int id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id and comm_id=:comm_id and status='3' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/update_offr_getlanguagedetailsData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_LANGUAGE> update_offr_getlanguagedetailsData3(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id and comm_id=:comm_id and status='3' order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
