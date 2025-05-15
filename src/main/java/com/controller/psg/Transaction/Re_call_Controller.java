package com.controller.psg.Transaction;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.dao.psg.update_census_data.Re_EmploymentDAO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Re_call_Controller {
	@Autowired
	private Re_EmploymentDAO redao;
	helpController hp = new helpController();
	Psg_CommonController pcommon = new Psg_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@Autowired
	private Search_PostOutDao pod;
	@Autowired
	private Report_3008DAO report_3008DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/re_callurl", method = RequestMethod.GET)
	public ModelAndView re_callurl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		 String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("re_callurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("Re_callTiles");
	}

	@RequestMapping(value = "/GetRecallnoNoData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetRecallnoNoData(String personnel_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<Map<String, Object>> list = redao.getre_emp_call_data(personnel_no);
		tx.commit();
		return list;
	}

	@RequestMapping(value = "/GetRecallData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_DETAIL_Parent> GetRecallData(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery("FROM TB_CENSUS_DETAIL_Parent c where comm_id = :comm_id ");
		q1.setParameter("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DETAIL_Parent> list = (List<TB_CENSUS_DETAIL_Parent>) q1.list();
		
		tx.commit();
		return list;
	}

	/*--------------------- Start Re- Employment----------------------------------*/
	@RequestMapping(value = "/admin/Re_call_Action", method = RequestMethod.POST)
	public @ResponseBody String Re_call_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		String authority = request.getParameter("authority");
		String date_of_authority = request.getParameter("auth_dt");

		String date_of_tos = request.getParameter("date_of_tos");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String unit_posted_to = request.getParameter("unit_posted_to");
		
		String re_emp_select = request.getParameter("re_emp_select");
		String granted_fr_dt = request.getParameter("granted_fr_dt");
		String rc_id = request.getParameter("rc_id");
		String personnel_no = request.getParameter("personnel_no");
		
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
		
		if (personnel_no != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {
				return "Personal No Should Contain Maximum 9 Character";
			}
		}
		if (authority == null || authority.equals("") || authority.equals("null")) {
			return "Please Enter Authority No.";
		}
		if (!valid.isValidAuth(authority)) {
			return valid.isValidAuthMSG + " Authority No";
		}	
		if (!valid.isvalidLength(authority,valid.nameMax,valid.nameMin)) {
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
				return "Please Enter Unit SUS No.";
			} else {
				roleSusNo = unit_sus_no;
			}
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
				return " Unit Name " + valid.isUnitMSG;
			}
    	  
    	  if (!valid.isvalidLength(unit_posted_to, valid.nameMax, valid.nameMin)) {
				return "Unit Name " + valid.isValidLengthMSG;
			}
	     }
		 
		String msg = "";
		try {
			if (Integer.parseInt(rc_id) == 0) {
				TB_REEMPLOYMENT re_emp = new TB_REEMPLOYMENT();
				re_emp.setAuthority(authority);
				re_emp.setAuth_dt(ath_dt);
				re_emp.setGranted_fr_dt(granted_fr_dt1);
				re_emp.setRe_emp_select(1);
				re_emp.setStatus(0);
				re_emp.setCreated_date(date);
				re_emp.setUnit_sus_no(roleSusNo);
				re_emp.setUnit_posted_to(unit_posted_to);
				re_emp.setDate_of_tos(date_of_tos1);
				re_emp.setCreated_by(username);
				re_emp.setComm_id(comm_id);
				re_emp.setCensus_id(census_id);
				int id = (int) sessionHQL.save(re_emp);
				msg = Integer.toString(id);
			} else {
				String hql = "update TB_REEMPLOYMENT set authority=:authority,auth_dt=:auth_dt,"
						+ "granted_fr_dt=:granted_fr_dt,re_emp_select=:re_emp_select," + "date_of_tos=:date_of_tos1,"
						+ "unit_sus_no=:unit_sus_no,unit_posted_to=:unit_posted_to,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id";
				Query query = sessionHQL.createQuery(hql).setString("authority", authority)
						.setTimestamp("auth_dt", ath_dt).setTimestamp("granted_fr_dt", granted_fr_dt1)
						.setInteger("re_emp_select", (1)).setString("unit_posted_to", unit_posted_to)
						.setTimestamp("date_of_tos1", date_of_tos1).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0)
						.setString("unit_sus_no", roleSusNo).setInteger("id", Integer.parseInt(rc_id));
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			pcommon.update_miso_role_hdr_status(comm_id, 0, username, "re_emp_status");
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

	@RequestMapping(value = "/admin/search_re_callurl", method = RequestMethod.GET)
	public ModelAndView search_re_callurl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		

			 String  roleid = sessionUserId.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 
		 
		Mmap.put("list", redao.search_re_call("", "0", "", "", "","",roleSusNo, roleType, roleAccess));
		return new ModelAndView("Search_Re_CallTiles");
	}

	@RequestMapping(value = "/Search_Re_Call", method = RequestMethod.POST)
	public ModelAndView Search_Re_Call(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,  
		  	@RequestParam(value = "cr_by1", required = false) String cr_by,
		  	@RequestParam(value = "cr_date1", required = false) String cr_date) {
		
		 String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		
		if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:search_re_callurl");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:search_re_callurl");
				}
	      }
		 
		 if(personnel_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:search_re_callurl");
				}		  
	    	  
			  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
					return new ModelAndView("redirect:search_re_callurl");
				}
	      }
		 
		 if(unit_name!="") {
			  if (!valid.isOnlyAlphabetNumeric(unit_name)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");
					return new ModelAndView("redirect:search_re_callurl");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:search_re_callurl");
//				}
	      }
		
		ArrayList<ArrayList<String>> list = redao.search_re_call(personnel_no, status, unit_sus_no, unit_name
				,cr_by,cr_date,roleSusNo, roleType, roleAccess);
		Mmap.put("list", list);
		Mmap.put("personnel_no1", personnel_no);
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
		return new ModelAndView("Search_Re_CallTiles");
	}

	@RequestMapping(value = "/admin/getRe_CallData", method = RequestMethod.POST)
	public @ResponseBody List<TB_REEMPLOYMENT> getRe_EmployeementData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("ch_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_REEMPLOYMENT where census_id=:census_id  and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_REEMPLOYMENT> list = (List<TB_REEMPLOYMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/Edit_Re_call_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Re_call_Url(@ModelAttribute("updateid") String updateid,
			@ModelAttribute("census_id_e") String census_id_e, @ModelAttribute("comm_id_e") String comm_id_e,
			@ModelAttribute("personnel_no_e") String personnel_no_e,
			@ModelAttribute("personnel_no6") String personnel_no6, @ModelAttribute("unit_sus_no5") String unit_sus_no5,
			@ModelAttribute("unit_name5") String unit_name5, @ModelAttribute("statusA5") String statusA5,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit, HttpServletRequest request) {
	
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		model.put("rc_id", updateid);
		model.put("census_id", census_id_e);
		model.put("comm_id", comm_id_e);
		model.put("personnel_no", personnel_no_e);
		model.put("personnel_no6", personnel_no6);
		model.put("unit_sus_no", unit_sus_no5);
		model.put("unit_name", unit_name5);
		model.put("statusA", statusA5);
		return new ModelAndView("Re_callTiles");
	}

	@RequestMapping(value = "/Approve_re_call_url", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_re_call_url(@ModelAttribute("appid") int id,
			BindingResult result, HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		
		
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id_a"));
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_REEMPLOYMENT RE = (TB_REEMPLOYMENT) sessionHQL.get(TB_REEMPLOYMENT.class, id);
		String hqlUpdate = "update TB_REEMPLOYMENT set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
				.setTimestamp("modified_date", new Date()).setInteger("id", id).executeUpdate();
		sessionHQL.flush();
		sessionHQL.clear();
		if (app > 0) {
			String comm = " from TB_TRANS_PROPOSED_COMM_LETTER where id=:id";
			Query query = sessionHQL.createQuery(comm).setParameter("id", comm_id);
			List<TB_TRANS_PROPOSED_COMM_LETTER> listcomm = (List<TB_TRANS_PROPOSED_COMM_LETTER>) query.list();
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			if (!roleAccess.equals("Unit")) {
				roleSusNo = RE.getUnit_sus_no();
			}
			ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
			ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
			po.setCreated_by(username);
			po.setCreated_date(new Date());
			po.setCensus_id(RE.getCensus_id());
			po.setComm_id(comm_id);
			if (orbatlist.size() > 0) {
				po.setCmd_sus(orbatlist.get(0).get(1));
				po.setCorps_sus(orbatlist.get(0).get(2));
				po.setDiv_sus(orbatlist.get(0).get(3));
				po.setBde_sus(orbatlist.get(0).get(4));
			}
			if (Location_Trnlist.size() > 0) {
				po.setLocation(Location_Trnlist.get(0).get(0));
				po.setTrn_type(Location_Trnlist.get(0).get(1));
			}
			
			//260194
			String hqlUpdatepre = "from TB_POSTING_IN_OUT where comm_id=:comm_id and status=1 order by id desc ";
			Query querypre = sessionHQL.createQuery(hqlUpdatepre)
					.setBigInteger("comm_id",comm_id).setMaxResults(1);
			List<TB_POSTING_IN_OUT> curr = (List<TB_POSTING_IN_OUT>) querypre.list();
			//260194
			po.setFrom_sus_no(listcomm.get(0).getUnit_sus_no());
			po.setTo_sus_no(RE.getUnit_sus_no());
			po.setDt_of_sos(curr.get(0).getTenure_date());
			po.setIn_auth(RE.getAuthority());
			po.setIn_auth_dt(RE.getAuth_dt());
			po.setDt_of_tos(RE.getDate_of_tos());
			po.setRank(listcomm.get(0).getRank());
			po.setStatus(1);
//			po.setNotification_status(0);
			sessionHQL.save(po);
			int app2 = (int) sessionHQL.save(po);
		}
		String hqlUpdate1 = "update TB_TRANS_PROPOSED_COMM_LETTER set status=:status,modified_by=:modified_by,modified_date=:modified_date,unit_sus_no=:unit_sus_no  where id=:id";
		int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username).setString("unit_sus_no", roleSusNo)
				.setTimestamp("modified_date", new Date()).setBigInteger("id", comm_id).executeUpdate();
		tx.commit();
		String msg2 = pcommon.update_miso_role_hdr_status(comm_id, 1, username, "re_emp_status");
		if (!msg2.equals("1")) {
			tx.rollback();
		}
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		return new ModelAndView("redirect:search_re_callurl");
	}

	@RequestMapping(value = "/Reject_re_call_url", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_re_call_url(@ModelAttribute("reid") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
			Authentication authentication) {
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
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
		String hqlUpdate = "update TB_REEMPLOYMENT set status=:status,reject_remarks=:reject_remarks where id=:id";

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)
				.setString("reject_remarks", reject_remarks)
				.setInteger("id", id).executeUpdate();
		TB_REEMPLOYMENT com = (TB_REEMPLOYMENT) sessionHQL.get(TB_REEMPLOYMENT.class, id);
		pcommon.update_miso_role_hdr_status(com.getComm_id(), 0, username, "re_emp_status");
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Rejected Successfully.");
		} else {
			liststr.add("Rejected Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		return new ModelAndView("redirect:search_re_callurl");
	}

	@RequestMapping(value = "/admin/Edit_re_call_Action", method = RequestMethod.POST)
	public ModelAndView Edit_re_call_Action(ModelMap Mmap, HttpSession session, 
			 @RequestParam(value = "msg", required = false) String msg1,
			HttpServletRequest request)
			throws ParseException {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_re_callurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg1 = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		String authority = request.getParameter("authority");
		String date_of_authority = request.getParameter("auth_dt");
		String date_of_appt = request.getParameter("date_of_appt");
		String date_of_tos = request.getParameter("date_of_tos");
		String extension_fr_dt = request.getParameter("from_dt");
		String extension_to_dt = request.getParameter("to_dt");
		/* String date_of_release = request.getParameter("dt_of_release"); */
		String unit_sus_no = request.getParameter("unit_sus_no");
		String unit_posted_to = request.getParameter("unit_posted_to");
		String appt = request.getParameter("appt");
		String x_sus_no = request.getParameter("x_sus_no");
		String x_list_loc = request.getParameter("x_list_loc");
		String re_emp_select = request.getParameter("re_emp_select");
		String granted_fr_dt = request.getParameter("granted_fr_dt");
		String rc_id = request.getParameter("rc_id");
		// String r_id = "0";
		Date date = new Date();
		Date date_of_appt1 = null;
		Date date_of_tos1 = null;
		Date granted_fr_dt1 = null;
		Date ath_dt = null;
		Date E_fr_dt = null;
		Date E_t_dt = null;
		Date release_dt = null;
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		if (date_of_authority != "") {
			ath_dt = format.parse(date_of_authority);
		}
		if (date_of_appt != "") {
			date_of_appt1 = format.parse(date_of_appt);
		}
		if (date_of_tos != "") {
			date_of_tos1 = format.parse(date_of_tos);
		}
		if (extension_fr_dt != "") {
			E_fr_dt = format.parse(extension_fr_dt);
		}
		if (granted_fr_dt != "") {
			granted_fr_dt1 = format.parse(granted_fr_dt);
		}
		if (extension_to_dt != "") {
			E_t_dt = format.parse(extension_to_dt);
		}
		String msg = "";
		try {
			String hql = "update TB_REEMPLOYMENT set authority=:authority,auth_dt=:auth_dt,"
					+ "from_dt=:extension_fr_dt,to_dt=:extension_to_dt,granted_fr_dt=:granted_fr_dt,re_emp_select=:re_emp_select,"
					+ "unit_sus_no=:unit_sus_no,appt=:appt,date_of_appt=:date_of_appt1,date_of_tos=:date_of_tos1,"
					+ "x_sus_no=:x_sus_no,x_list_loc=:x_list_loc,"
					+ "modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id";
			Query query = sessionHQL.createQuery(hql).setString("authority", authority).setTimestamp("auth_dt", ath_dt)
					.setTimestamp("granted_fr_dt", granted_fr_dt1)
					.setInteger("re_emp_select", Integer.parseInt(re_emp_select))
					// .setDate("granted_to_dt",G_t_dt)
					.setTimestamp("extension_fr_dt", E_fr_dt).setTimestamp("extension_to_dt", E_t_dt)
					// .setInteger("cause_of_release", cause_of_release).setDate("date_of_release",
					// release_dt)
					.setString("unit_sus_no", unit_sus_no).setInteger("appt", Integer.parseInt(appt))
					.setTimestamp("date_of_appt1", date_of_appt1).setTimestamp("date_of_tos1", date_of_tos1)
					.setString("x_sus_no", x_sus_no).setString("x_list_loc", x_list_loc)
					.setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("status", 0).setInteger("id", Integer.parseInt(rc_id));
			msg = query.executeUpdate() > 0 ? "update" : "0";
			pcommon.update_offr_status(census_id, username);
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
		return new ModelAndView("redirect:search_re_callurl");
	}

///////////////////////////Cancel History//////////////
	/*---------------------------//Cancel Reject postout  ----------------------------------------------------*/
	@RequestMapping(value = "/CancelRejectHistory_Recall", method = RequestMethod.POST)
	public ModelAndView CancelRejectHistory_Recall(@ModelAttribute("idcrh") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idcrh", required = false) String comm_id,
			@RequestParam(value = "personnel_nochr", required = false) String personnel_no,
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
		hqlUpdate1 += " update TB_REEMPLOYMENT set cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date,cancel_by=:cancel_by,cancel_date=:cancel_date  where id=:id";
		try {
			Query query = sessionHQL.createQuery(hqlUpdate1);
			if (roleType.equals("DEO"))
				query.setInteger("cancel_status", 0).setString("cancel_by", username).setTimestamp("cancel_date",
						new Date());
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
		model.put("personnel_no1", personnel_no);
		model.put("status1", status);
		model.put("unit_name1", unit_name);
		model.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("redirect:search_re_callurl");
	}

	/*---------------------------//Approve Cancel History  ----------------------------------------------------*/
	@RequestMapping(value = "/ApproveCancelHistory_Recall", method = RequestMethod.POST)
	public ModelAndView ApproveCancelHistory_Recall(@ModelAttribute("idach") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idach", required = false) String comm_id,
			@RequestParam(value = "personnel_noach", required = false) String personnel_no,
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
			hqlUpdate1 += "update  TB_REEMPLOYMENT set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			Query query = sessionHQL.createQuery(hqlUpdate1);
			int app = query.setString("modified_by", username).setTimestamp("modified_date", new Date())
					.setInteger("status", -1).setInteger("cancel_status", 1).setInteger("id", id).executeUpdate();
			sessionHQL.flush();
			sessionHQL.clear();
			
			String hqln = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
					+ "status=:status where id=:id ";
			Query queryn = sessionHQL.createQuery(hqln).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 4).setBigInteger("id", new BigInteger(comm_id));
			apppn = queryn.executeUpdate() > 0 ? 1 : 0;
			sessionHQL.flush();
			sessionHQL.clear();
			
			String hqlUpdate2 = " from TB_REEMPLOYMENT where comm_id=:comm_id and id=:id ";
			Query query2 = sessionHQL.createQuery(hqlUpdate2).setBigInteger("comm_id", new BigInteger(comm_id))
					.setInteger("id", id).setMaxResults(1);
			List<TB_REEMPLOYMENT> re_dt_tos = (List<TB_REEMPLOYMENT>) query2.list();
			String hqlUpdatecurr = " from TB_POSTING_IN_OUT where comm_id=:comm_id and dt_of_tos=:dt_of_tos and status=1 order by id desc ";
			Query querycurr = sessionHQL.createQuery(hqlUpdatecurr).setBigInteger("comm_id", new BigInteger(comm_id))
					.setTimestamp("dt_of_tos", re_dt_tos.get(0).getDate_of_tos()).setMaxResults(1);
			List<TB_POSTING_IN_OUT> curr = (List<TB_POSTING_IN_OUT>) querycurr.list();
			int app2 = 0;
			int appp = 0;
			
			if (curr.size() > 0) {
				String hqlUpdate1p = "update TB_POSTING_IN_OUT set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
				Query queryp = sessionHQL.createQuery(hqlUpdate1p);
				appp = queryp.setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("status", -1).setInteger("cancel_status", 1).setInteger("id", curr.get(0).getId())
						.executeUpdate();
				sessionHQL.flush();
				sessionHQL.clear();
				if (curr.get(0).getStatus() == 1) {
					String hqlUpdatepre = "select id from TB_POSTING_IN_OUT where comm_id=:comm_id and status=1 order by id desc ";
					Query querypre = sessionHQL.createQuery(hqlUpdatepre)
							.setInteger("comm_id", Integer.parseInt(comm_id)).setMaxResults(1);
					Integer cpre = (Integer) querypre.uniqueResult();
					
					if (cpre != null && cpre > 0) {
						int chang_id = cpre.intValue();
					
						TB_POSTING_IN_OUT postin_out = (TB_POSTING_IN_OUT) sessionHQL.get(TB_POSTING_IN_OUT.class,
								chang_id);
						/*postin_out.setTenure_date(null);
						sessionHQL.update(postin_out);*/
						String hqlUpdate3 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
						app2 = sessionHQL.createQuery(hqlUpdate3).setString("unit_sus_no", postin_out.getTo_sus_no())
								.setBigInteger("id", new BigInteger(comm_id)).executeUpdate();
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
		model.put("personnel_no1", personnel_no);
		model.put("status1", status);
		model.put("unit_name1", unit_name);
		model.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("redirect:search_re_callurl");
	}
}
