package com.controller.psg.Jco_Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Transaction.Re_call_jco_DAO;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Transaction.TB_POSTING_IN_OUT_JCO;
import com.models.psg.Jco_Transaction.TB_RE_CALL;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Re_call_JCO_Controller {


	helpController hp = new helpController();
	
	@Autowired
	psg_jco_CommonController pcommonJc = new psg_jco_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	ValidationController valid = new ValidationController();
    AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	
	@Autowired
	private Re_call_jco_DAO redaoJco;
	@Autowired
	private RoleBaseMenuDAO roledao;
	//jco recall page open url
	@RequestMapping(value = "/admin/re_call_jcourl", method = RequestMethod.GET)
	public ModelAndView re_call_jcourl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("re_call_jcourl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		    if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("Re_call_jcoTiles");
	}
	/*--------------------- Start Re-call----------------------------------*/
	@RequestMapping(value = "/admin/Re_call_JCOAction", method = RequestMethod.POST)
	public @ResponseBody String Re_call_JCOAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		int jco_id = 0;
		
		
		String army_no = request.getParameter("army_no");
		String authority = request.getParameter("authority").trim();
		String date_of_authority = request.getParameter("auth_dt");
		String date_of_appt = request.getParameter("date_of_appt");
		String date_of_tos = request.getParameter("date_of_tos");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String unit_posted_to = request.getParameter("unit_posted_to");
		String appt = request.getParameter("appt");
		String x_sus_no = request.getParameter("x_sus_no");
		String x_list_loc = request.getParameter("x_list_loc");
		String re_emp_select = request.getParameter("re_emp_select");
		String granted_fr_dt = request.getParameter("granted_fr_dt");
			
		Date date = new Date();
		Date date_of_appt1 = null;
		Date date_of_tos1 = null;
		Date granted_fr_dt1 = null;
		Date ath_dt = null;
		Date E_fr_dt = null;
		Date E_t_dt = null;
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		   if (army_no == null || army_no.equals("") || army_no.equals("null")) {
			return "Please Enter Army No";
		   }
			 if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					return valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No";
				}
			 if (army_no.length() < 2 || army_no.length() > 12) {
				 return "Army No Should Contain Maximum 12 Character";
			 }
		
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
		}
		else {
			ath_dt = format1.parse(date_of_authority);
		}
		if (granted_fr_dt == null || granted_fr_dt.equals("") || granted_fr_dt.equals("DD/MM/YYYY")) {
			return "Please Select Granted From Date.";
		} 
		else if (!valid.isValidDate(granted_fr_dt)) {
 			return valid.isValidDateMSG + " of Granted From";	
		}
		else {
			granted_fr_dt1 = format1.parse(granted_fr_dt);
		}
		if (date_of_tos == null || date_of_tos.equals("") || date_of_tos.equals("DD/MM/YYYY")) {
			return "Please Select Date of TOS.";
		}
		else if (!valid.isValidDate(date_of_tos)) {
 			return valid.isValidDateMSG + " of TOS";	
		}
		else {
			date_of_tos1 = format1.parse(date_of_tos);
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("Unit")) {
			if (unit_sus_no.equals("")) {
				return "Please Enter Unit Sus No.";
			} else {
				roleSusNo = unit_sus_no;
			}
			if(unit_sus_no!="") {
			  	  if (!valid.SusNoLength(unit_sus_no)) {
							return valid.SusNoMSG;
						}
			  	  
			  	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
							return valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No";
						}
				     }
					 
					 if(unit_posted_to!="") {
						 
			       if (!valid.isUnit(unit_posted_to)) {
			               return "Unit Name " + valid.isUnitMSG;
			         }
			         
			  	  if (!valid.isvalidLength(unit_posted_to, valid.nameMax, valid.nameMin)) {
							return "Unit Name " + valid.isValidLengthMSG;
						}
				     }
		}
		int rank = Integer.parseInt(request.getParameter("rank_id"));
		
		if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
			jco_id = Integer.parseInt(request.getParameter("jco_id"));
		}
		String rc_id = request.getParameter("rc_id");
		
		String msg = "";
		try {
			if (Integer.parseInt(rc_id) == 0) {
				TB_RE_CALL re_emp = new TB_RE_CALL();
				re_emp.setAuthority(authority);
				re_emp.setAuth_dt(ath_dt);
				re_emp.setGranted_fr_dt(granted_fr_dt1);
				re_emp.setRe_emp_select(1);
				re_emp.setStatus(0);
				re_emp.setCreated_date(date);
				re_emp.setUnit_sus_no(roleSusNo);
				re_emp.setDate_of_tos(date_of_tos1);
				re_emp.setCreated_by(username);
				re_emp.setJco_id(jco_id);
				re_emp.setRank(rank);
				int id = (int) sessionHQL.save(re_emp);
				msg = Integer.toString(id);
			} else {
				String hql = "update TB_RE_CALL set authority=:authority,auth_dt=:auth_dt,"
						+ "granted_fr_dt=:granted_fr_dt,re_emp_select=:re_emp_select," + "date_of_tos=:date_of_tos1,"
						+ "unit_sus_no=:unit_sus_no,unit_posted_to=:unit_posted_to,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status,rank=:rank where id=:id";
				Query query = sessionHQL.createQuery(hql).setString("authority", authority)
						.setTimestamp("auth_dt", ath_dt).setTimestamp("granted_fr_dt", granted_fr_dt1)
						.setInteger("re_emp_select", (1)).setString("unit_posted_to", unit_posted_to)
						.setTimestamp("date_of_tos1", date_of_tos1).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0).setInteger("rank", rank)
						.setString("unit_sus_no", roleSusNo).setInteger("id", Integer.parseInt(rc_id));
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			pcommonJc.update_miso_role_hdr_status_jco(jco_id, 0, username, "re_emp_status");
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		// sessionHQL.close();
		return msg;
	}
	

 
	@RequestMapping(value = "/admin/getRe_CallDataJCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_RE_CALL> getRe_CallDataJCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		//int id = Integer.parseInt(request.getParameter("ch_id"));
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		String hqlUpdate = " from TB_RE_CALL where jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_RE_CALL> list = (List<TB_RE_CALL>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	///search page url
	
	@RequestMapping(value = "/admin/search_re_callurlJCO", method = RequestMethod.GET)
	public ModelAndView search_re_callurlJCO(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		Boolean val = roledao.ScreenRedirect("search_re_callurlJCO", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		  if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
		  }
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("list", redaoJco.search_re_callJCO("", "0", "", "","","", roleSusNo, roleType, roleAccess));
		return new ModelAndView("Search_Re_CallJCOTiles");
	}
	
	
	@RequestMapping(value = "/Search_Re_CallJCO", method = RequestMethod.POST)
	public ModelAndView Search_Re_CallJCO(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "army_no1", required = false) String army_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		
		
			String roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_re_callurlJCO", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		if(unit_sus_no!="") {
            if (!valid.SusNoLength(unit_sus_no)) {
                              Mmap.put("msg", valid.SusNoMSG);
                              return new ModelAndView("redirect:search_re_callurlJCO");
                      }
            if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:search_re_callurlJCO");
			}
           }
    
        if(unit_name!="") {
        	 if (!valid.isUnit(unit_name)) {
                 Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                 return new ModelAndView("redirect:search_re_callurlJCO");
         }
         
//                      if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//                              Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//                              return new ModelAndView("redirect:search_re_callurlJCO");
//                      }
    }
    
        if(army_no!="") {
        	 if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
					return new ModelAndView("redirect:search_re_callurlJCO");
				}
            if (!valid.ArmyNoLength(army_no)) {
                                   Mmap.put("msg", valid.ArmyNoMSG );
                                   return new ModelAndView("redirect:search_re_callurlJCO");
                           }
                 
                 if (army_no.length() < 2 || army_no.length() > 12) {
                                   Mmap.put("msg", "Army No No Should Contain Maximum 12 Character");
                                   return new ModelAndView("redirect:search_re_callurlJCO");
                           }
        } 


		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		ArrayList<ArrayList<String>> list = redaoJco.search_re_callJCO(army_no, status, unit_sus_no, unit_name ,cr_by,cr_date,
				roleSusNo, roleType, roleAccess);
		Mmap.put("list", list);
		Mmap.put("army_no1", army_no);
		Mmap.put("status1", status);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		if (com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		return new ModelAndView("Search_Re_CallJCOTiles");
	}

	@RequestMapping(value = "/Edit_Re_call_UrlJCO", method = RequestMethod.POST)
	public ModelAndView Edit_Re_call_UrlJCO(@ModelAttribute("updateid") String updateid,
			@ModelAttribute("jco_id_e") String jco_id_e, @ModelAttribute("army_no_e") String army_no_e,
			@ModelAttribute("army_no6") String army_no6, @ModelAttribute("unit_sus_no5") String unit_sus_no5,
			@ModelAttribute("unit_name5") String unit_name5, @ModelAttribute("statusA5") String statusA5,
			@ModelAttribute("cr_by5") String cr_by5,@ModelAttribute("cr_date5") String cr_date5,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_re_callurlJCO", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		model.put("rc_id", updateid);
		model.put("jco_id", jco_id_e);
		model.put("army_no_e", army_no_e);
		model.put("army_no6", army_no6);
		model.put("unit_sus_no", unit_sus_no5);
		model.put("unit_name", unit_name5);
		model.put("statusA", statusA5);
		model.put("cr_by5", cr_by5);
		model.put("cr_date5", cr_date5);	
		if (roleAccess.equals("Unit")) {
			model.put("sus_no", roleSusNo);
			model.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionEdit).get(0));
		}
		return new ModelAndView("Re_call_jcoTiles");
	}
	 
	  @RequestMapping(value = "/CancelRejectHistory_RecallJCO", method = RequestMethod.POST)
		public ModelAndView CancelRejectHistory_RecallJCO(@ModelAttribute("idcrh") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "jco_idcrh", required = false) String jco_id,
				@RequestParam(value = "army_nocrh", required = false) String army_no,
				@RequestParam(value = "statuschr", required = false) String status,
				@RequestParam(value = "unit_namechr", required = false) String unit_name,
				@RequestParam(value = "unit_sus_nochr", required = false) String unit_sus_no,
				Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String roleType = sessionA.getAttribute("roleType").toString();
			String username = sessionA.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate1 = "";
			hqlUpdate1 += " update TB_RE_CALL set cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date,cancel_by=:cancel_by,cancel_date=:cancel_date  where id=:id";
			try {
				Query query = sessionHQL.createQuery(hqlUpdate1);
				if (roleType.equals("DEO"))
					query.setInteger("cancel_status", 0).setString("cancel_by", username).setTimestamp("cancel_date",new Date());
				else
					query.setInteger("cancel_status", 2).setString("cancel_by", null).setTimestamp("cancel_date", null);
				int app = query.setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("id", id).executeUpdate();
				tx.commit();
				if (app > 0) {
					if (roleType.equals("DEO"))
						liststr.add("Cancelled Successfully.");
					else
						liststr.add("Rejected Successfully.");
				} else {
					tx.rollback();
					if (roleType.equals("DEO"))
						liststr.add("Cancelled Not Successfully.");
					else
						liststr.add("Rejected Not Successfully.");
				}
			} catch (Exception e) {
				tx.rollback();
				if (roleType.equals("DEO"))
					liststr.add("Cancelled Not Successfully.");
				else
					liststr.add("Rejected Not Successfully.");
			} finally {
				sessionHQL.close();
			}
			model.put("msg", liststr.get(0));
			model.put("army_no1", army_no);
			model.put("status1", status);
			model.put("unit_name1", unit_name);
			model.put("unit_sus_no1", unit_sus_no);
			return new ModelAndView("redirect:search_re_callurlJCO");
		}
	
	  @RequestMapping(value = "/Approve_re_call_urlJCO", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Approve_re_call_urlJCO(@ModelAttribute("appid") int id,
				BindingResult result, HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			TB_POSTING_IN_OUT_JCO po = new TB_POSTING_IN_OUT_JCO();
			int jco_id = Integer.parseInt(request.getParameter("jco_id_a"));
			String username = sessionA.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			TB_RE_CALL RE = (TB_RE_CALL) sessionHQL.get(TB_RE_CALL.class, id);
			String hqlUpdate = "update TB_RE_CALL set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("id", id).executeUpdate();
			sessionHQL.flush();
			sessionHQL.clear();
			if (app > 0) {
				String comm = " from TB_CENSUS_JCO_OR_PARENT where id=:id";
				Query query = sessionHQL.createQuery(comm).setParameter("id", jco_id);
				List<TB_CENSUS_JCO_OR_PARENT> listcomm = (List<TB_CENSUS_JCO_OR_PARENT>) query.list();
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				if (!roleAccess.equals("Unit")) {
					roleSusNo = RE.getUnit_sus_no();
				}
			//	ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
			//	ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
				po.setCreated_by(username);
				po.setCreated_date(new Date());
				//po.setCensus_id(RE.getCensus_id());
				po.setJco_id(jco_id);
			/*
			 * if (orbatlist.size() > 0) { po.setCmd_sus(orbatlist.get(0).get(1));
			 * po.setCorps_sus(orbatlist.get(0).get(2));
			 * po.setDiv_sus(orbatlist.get(0).get(3));
			 * po.setBde_sus(orbatlist.get(0).get(4)); } if (Location_Trnlist.size() > 0) {
			 * po.setLocation(Location_Trnlist.get(0).get(0));
			 * po.setTrn_type(Location_Trnlist.get(0).get(1)); }
			 */
				//260194
				String hqlUpdatepre = "from TB_POSTING_IN_OUT_JCO where jco_id=:jco_id and status=1 order by id desc ";
				Query querypre = sessionHQL.createQuery(hqlUpdatepre)
						.setInteger("jco_id",jco_id).setMaxResults(1);
				List<TB_POSTING_IN_OUT_JCO> curr = (List<TB_POSTING_IN_OUT_JCO>) querypre.list();
				//260194
				po.setFrom_sus_no(listcomm.get(0).getUnit_sus_no());
				po.setTo_sus_no(RE.getUnit_sus_no());
				po.setDt_of_sos(curr.get(0).getTenure_date());
				po.setIn_auth(RE.getAuthority());
				po.setIn_auth_dt(RE.getAuth_dt());
				po.setDt_of_tos(RE.getDate_of_tos());
				po.setRank(listcomm.get(0).getRank());
				po.setStatus(1);
				po.setNotification_status(0);
				sessionHQL.save(po);
				int app2 = (int) sessionHQL.save(po);
			}
			String hqlUpdate1 = "update TB_CENSUS_JCO_OR_PARENT set status=:status,modified_by=:modified_by,modified_date=:modified_date,unit_sus_no=:unit_sus_no   where id=:id";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setString("unit_sus_no", roleSusNo).setInteger("id", jco_id).executeUpdate();
			tx.commit();
			String msg2 = pcommonJc.update_miso_role_hdr_status_jco(jco_id, 0, username, "re_emp_status");
		/*
		 * if (!msg2.equals("1")) { tx.rollback(); }
		 */
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
			model.put("msg", liststr.get(0));
			return new ModelAndView("redirect:search_re_callurlJCO");
		}
	  /*---------------------------//Approve Cancel History  ----------------------------------------------------*/
		@RequestMapping(value = "/ApproveCancelHistory_RecallJCO", method = RequestMethod.POST)
		public ModelAndView ApproveCancelHistory_RecallJCO(@ModelAttribute("idach") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "jco_idach", required = false) String jco_id,
				@RequestParam(value = "army_noach", required = false) String army_no,
				@RequestParam(value = "statusach", required = false) String status,
				@RequestParam(value = "unit_nameach", required = false) String unit_name,
				@RequestParam(value = "unit_sus_noach", required = false) String unit_sus_no,
				Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String roleType = sessionA.getAttribute("roleType").toString();
			String username = sessionA.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate1 = "";
			try {
				int apppn = 0;
				hqlUpdate1 += "update  TB_RE_CALL set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
				Query query = sessionHQL.createQuery(hqlUpdate1);
				int app = query.setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("status", -1).setInteger("cancel_status", 1).setInteger("id", id).executeUpdate();
				sessionHQL.flush();
				sessionHQL.clear();
				
				String hqln = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"
						+ "status=:status where id=:id ";
				Query queryn = sessionHQL.createQuery(hqln).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 4).setInteger("id", Integer.parseInt(jco_id));
				apppn = queryn.executeUpdate() > 0 ? 1 : 0;
				sessionHQL.flush();
				sessionHQL.clear();
				
				String hqlUpdate2 = " from TB_RE_CALL where jco_id=:jco_id and id=:id ";
				Query query2 = sessionHQL.createQuery(hqlUpdate2).setInteger("jco_id", Integer.parseInt(jco_id))
						.setInteger("id", id).setMaxResults(1);
				List<TB_RE_CALL> re_dt_tos = (List<TB_RE_CALL>) query2.list();
				String hqlUpdatecurr = " from TB_POSTING_IN_OUT_JCO where jco_id=:jco_id and dt_of_tos=:dt_of_tos and status=1 order by id desc ";
				Query querycurr = sessionHQL.createQuery(hqlUpdatecurr).setInteger("jco_id", Integer.parseInt(jco_id))
						.setTimestamp("dt_of_tos", re_dt_tos.get(0).getDate_of_tos()).setMaxResults(1);
				List<TB_POSTING_IN_OUT_JCO> curr = (List<TB_POSTING_IN_OUT_JCO>) querycurr.list();
				int app2 = 0;
				int appp = 0;
				
				if (curr.size() > 0) {
					String hqlUpdate1p = "update TB_POSTING_IN_OUT_JCO set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
					Query queryp = sessionHQL.createQuery(hqlUpdate1p);
					appp = queryp.setString("modified_by", username).setTimestamp("modified_date", new Date())
							.setInteger("status", -1).setInteger("cancel_status", 1).setInteger("id", curr.get(0).getId())
							.executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
					if (curr.get(0).getStatus() == 1) {
						String hqlUpdatepre = "select id from TB_POSTING_IN_OUT_JCO where jco_id=:jco_id and status=1 order by id desc ";
						Query querypre = sessionHQL.createQuery(hqlUpdatepre)
								.setInteger("jco_id", Integer.parseInt(jco_id)).setMaxResults(1);
						Integer cpre = (Integer) querypre.uniqueResult();
						
						if (cpre != null && cpre > 0) {
							int chang_id = cpre.intValue();
						
							TB_POSTING_IN_OUT_JCO postin_out = (TB_POSTING_IN_OUT_JCO) sessionHQL.get(TB_POSTING_IN_OUT_JCO.class,
									chang_id);
							postin_out.setTenure_date(null);
							sessionHQL.update(postin_out);
							String hqlUpdate3 = "update TB_CENSUS_JCO_OR_PARENT set unit_sus_no=:unit_sus_no where id=:id";
							app2 = sessionHQL.createQuery(hqlUpdate3).setString("unit_sus_no", postin_out.getTo_sus_no())
									.setInteger("id", Integer.parseInt(jco_id)).executeUpdate();
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
				}
				if (app > 0 && app2 > 0 && appp > 0 && apppn>0) {
					tx.commit();
					liststr.add("Approved Successfully.");
				} else {
					tx.rollback();
					liststr.add("Approved Not Successfully.");
				}
			} catch (Exception e) {
				tx.rollback();
				liststr.add("Data Not Approved.");
			} finally {
				sessionHQL.close();
			}
			model.put("msg", liststr.get(0));
			model.put("army_no1", army_no);
			model.put("status1", status);
			model.put("unit_name1", unit_name);
			model.put("unit_sus_no1", unit_sus_no);
			return new ModelAndView("redirect:search_re_callurlJCO");
		}
		@RequestMapping(value = "/Reject_re_call_urlJCO", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Reject_re_call_urlJCO(@ModelAttribute("reid") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "rej_remarks_recall", required = false) String reject_remarks,
				Authentication authentication) {
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("search_re_callurlJCO", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
			    if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			    
			    
			List<String> liststr = new ArrayList<String>();
			String username = sessionA.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = "update TB_RE_CALL set status=:status,reject_remarks=:reject_remarks  where id=:id";
		 
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setInteger("id", id).setString("reject_remarks", reject_remarks).executeUpdate();
			TB_RE_CALL com = (TB_RE_CALL) sessionHQL.get(TB_RE_CALL.class, id);
			pcommonJc.update_miso_role_hdr_status_jco(com.getJco_id(), 0, username, "re_emp_status");
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Rejected Successfully.");
			} else {
				liststr.add("Rejected Not Successfully.");
			}
			model.put("msg", liststr.get(0));
			return new ModelAndView("redirect:search_re_callurlJCO");
		}
		 
		//get data for recall
		 @RequestMapping(value = "/GetArmyNoDataReCall", method = RequestMethod.POST)
		 public @ResponseBody List<String> GetArmyNoDataReCall(String army_no) {
		 	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	List<String> list=null;
		  
		 	try {
		 		Query q1 = sessionHQL.createQuery(
		 				"	select c.id,g.gender_name,c.date_of_birth ,c.full_name,c.arm_service,c.regiment,c.enroll_dt,r.rank,r.id as rank_id,c.unit_sus_no,c.category \r\n"
		 						+ " FROM TB_CENSUS_JCO_OR_PARENT c \r\n"
		 						+ "	 ,TB_GENDER g ,TB_PSG_MSTR_RANK_JCO r	\r\n"
		 						+ "	where  upper(c.army_no) =:army_no and c.gender=g.id and  r.id = c.rank and \r\n"
		 						+ "	 (c.status='1' or c.status = '4')   order by c.army_no"
		 		);
		 		q1.setParameter("army_no", army_no.toUpperCase());
		 	
		 		 list = (List<String>) q1.list();
		 	
		 		tx.commit();
		 		
		 	} catch (RuntimeException e) {
		 		return null;
		 	} finally {
		 		if (sessionHQL != null) {
		 			sessionHQL.close();
		 		}
		 	}
		 		return list;
		 }

	
}
