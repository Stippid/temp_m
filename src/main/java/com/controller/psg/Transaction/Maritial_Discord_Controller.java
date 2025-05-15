package com.controller.psg.Transaction;

import java.math.BigInteger;
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
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Transaction.TB_MARITIAL_DISCORD;
import com.models.psg.Transaction.TB_Maritial_Discord_Status_Child;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Maritial_DiscordDAO;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Maritial_Discord_Controller {
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	PsgDashboardController das = new PsgDashboardController();
	
	 
	 
	@Autowired
	private Maritial_DiscordDAO tm_dao;

	@RequestMapping(value = "/admin/Maritial", method = RequestMethod.GET)
	public ModelAndView Maritial(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();
		
		
		
		/*Mmap.put("list", tm_dao.search_maritial_table("1", "", "1", "",
				"", roleAccess, roleSusNo, ""));*/
		Mmap.put("msg", msg);
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		return new ModelAndView("Maritial_DiscordTiles");
	}

	// ========================== Get Method =============================//
	@RequestMapping(value = "/admin/CheckStatus", method = RequestMethod.POST)
	public @ResponseBody List<TB_MARITIAL_DISCORD> CheckStatus(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_MARITIAL_DISCORD where census_id=:census_id and comm_id=:comm_id and status in (0,1) ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id",
				comm_id);
		@SuppressWarnings("unchecked")
		List<TB_MARITIAL_DISCORD> list = (List<TB_MARITIAL_DISCORD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/admin/GetStatusCase0", method = RequestMethod.POST)
	public @ResponseBody List<TB_Maritial_Discord_Status_Child> GetStatusCase0(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int maritial_id = Integer.parseInt(request.getParameter("id"));
		String hqlUpdate = " from TB_Maritial_Discord_Status_Child where maritial_id=:maritial_id and status ='0' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("maritial_id", maritial_id);
		@SuppressWarnings("unchecked")
		List<TB_Maritial_Discord_Status_Child> list = (List<TB_Maritial_Discord_Status_Child>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/Maritial_Discord_action",method = RequestMethod.POST)
	public ModelAndView Maritial_Discord_action(@ModelAttribute("Maritial_Discord_CMD") TB_MARITIAL_DISCORD BAN,
			 BindingResult result, HttpServletRequest request, ModelMap Mmap,
			 @RequestParam(value = "msg", required = false) String msg,
			HttpSession session) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		TB_Maritial_Discord_Status_Child m_child = new TB_Maritial_Discord_Status_Child();
		Date date = new Date();
		Date dt_comp = null;
		Date dt_st = null;
		Date dt_app = null;
		BigInteger comm_id = BigInteger.ZERO;
		
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String dt_of_comp = request.getParameter("dt_of_comp");
		String dt_of_status = request.getParameter("dt_of_status");
		String complaint = request.getParameter("complaint");
		String personnel_no = request.getParameter("personnel_no");
		String name_lady = request.getParameter("name_lady");
		
		if (!dt_of_comp.equals("") && !dt_of_comp.equals("DD/MM/YYYY")) {
			dt_comp = format.parse(dt_of_comp);
		}
		
		
		if (!dt_of_status.equals("") && !dt_of_status.equals("DD/MM/YYYY")) {
			dt_st = format.parse(dt_of_status);
		}
		
		if (request.getParameter("service_category") == null || request.getParameter("service_category").equals("0")
				|| request.getParameter("service_category").equals("null")) {
			Mmap.put("msg", "Please Select Category.");
			return new ModelAndView("redirect:Maritial");
		}
		if (request.getParameter("service_category") == "1" || request.getParameter("service_category").equals("1")
				|| request.getParameter("service_category").equals(1)) {
			if (request.getParameter("personnel_no") == null || request.getParameter("personnel_no").equals("")
					|| request.getParameter("personnel_no").equals("null")) {
				Mmap.put("msg", "Please Enter Personnel No");
				return new ModelAndView("redirect:Maritial");
			}
			
			if (!valid.isOnlyAlphabetNumericSpaceNot(request.getParameter("personnel_no"))) {
			    Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Maritial");
			}		    	  
			if (request.getParameter("personnel_no").length() < 7 || request.getParameter("personnel_no").length() > 9) {
			  Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:Maritial");
			}
			
		}
		if (request.getParameter("name_lady").trim() == null || request.getParameter("name_lady").trim().equals("")
				|| request.getParameter("name_lady").trim().equals("null")) {
			Mmap.put("msg", "Please Enter Name of Complainant.");
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isOnlyAlphabet(request.getParameter("name_lady"))) {
			Mmap.put("msg", " Name of Complainant " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isvalidLength(request.getParameter("name_lady"), valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Name of Complainant " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		if (request.getParameter("dt_of_comp").trim() == null || request.getParameter("dt_of_comp").trim().equals("")
				|| request.getParameter("dt_of_comp").trim().equals("null")
				|| request.getParameter("dt_of_comp").trim().equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of Complaint/Allegations.");
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isValidDate(request.getParameter("dt_of_comp"))) {
			Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations");
			return new ModelAndView("redirect:Maritial");
		}
		
		if (request.getParameter("complaint").trim() == null || request.getParameter("complaint").trim().equals("")
				|| request.getParameter("complaint").trim().equals("null")) {
			Mmap.put("msg", "Please Enter Complaint/Allegations.");
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isOnlyAlphabetNumeric(request.getParameter("complaint"))) {
			Mmap.put("msg", "Complaint/Allegations " + valid.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isvalidLength(request.getParameter("status_of_case"), valid.remarkMax, valid.remarkMin)) {
			Mmap.put("msg", "Complaint/Allegations " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		if (request.getParameter("status_of_case").trim() == null
				|| request.getParameter("status_of_case").trim().equals("")
				|| request.getParameter("status_of_case").trim().equals("null")) {
			Mmap.put("msg", "Please Enter Status of the Case.");
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isOnlyAlphabetNumeric(request.getParameter("status_of_case"))) {
			Mmap.put("msg", " Status of the Case " + valid.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		if (!valid.isvalidLength(request.getParameter("status_of_case"), valid.remarkMax, valid.remarkMin)) {
			Mmap.put("msg", "Status of the Case " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Maritial");
		}
		
		//int id = Integer.parseInt(request.getParameter("Hid").trim());
		
		//String val2 = getMaritalAlreadyExistCount(request.getParameter("personnel_no"),request.getParameter("name_lady"),dt_comp,request.getParameter("complaint"));
		
		if (request.getParameter("comm_id") != "") {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		try {

			Query q1 = sessionHQL.createQuery(
				
					"select count(personnel_no) From TB_MARITIAL_DISCORD where personnel_no=:personnel_no and name_lady=:name_lady and dt_of_comp=:dt_of_comp and complaint=:complaint and status='0' ");
			
				
			q1.setParameter("personnel_no", personnel_no);
			q1.setParameter("name_lady", name_lady);
			q1.setParameter("dt_of_comp", dt_comp);
			q1.setParameter("complaint", complaint);

			Long c = (Long) q1.uniqueResult();
		
	 
				if (c == 0) {
					BAN.setDt_of_comp(dt_comp);
					
				    BAN.setStatus(0);
					BAN.setCreated_by(username);
					BAN.setCreated_date(date);
					BAN.setComm_id(comm_id);
					sessionHQL.save(BAN);
					m_child.setStatus(0);
					m_child.setDt_of_status(dt_st);
					m_child.setStatus_of_case(request.getParameter("status_of_case").trim());
					m_child.setCreated_by(username);
					m_child.setCreated_date(date);
					m_child.setMaritial_id(BAN.getId());
					sessionHQL.save(m_child);
					//tx.commit();
					
					Mmap.put("msg", "Data Saved Successfully.");
				}
				if (c > 0)

				{
					
					Mmap.put("msg", "You can Not Enter Data Of Same Complaint/Allegations On Same Date.");
				}
			
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				msg = "Data Not Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Saved";
			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Maritial");
	}

	
	
	
	
	



	public String getPersonnelNoAlreadyExistCount(String comm_id, String census_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"select count(id) From TB_MARITIAL_DISCORD where census_id=:census_id and comm_id=:comm_id ");
			q1.setParameter("comm_id", new BigInteger(comm_id));
			q1.setParameter("census_id", new BigInteger(census_id));
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

	public String getMaritalAlreadyExistCount(String personnel_no,String name_lady,Date dt_comp,String complaint) {
		
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"select count(personnel_no) From TB_MARITIAL_DISCORD where personnel_no=:personnel_no and name_lady=:name_lady and dt_of_comp=:dt_of_comp and complaint=:complaint and status='0' ");
			
			
		
			q1.setParameter("personnel_no", personnel_no);
			q1.setParameter("name_lady", name_lady);
			q1.setParameter("dt_of_comp", dt_comp);
			q1.setParameter("complaint", complaint);
			
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

	@RequestMapping(value = "/admin/Search_Maritial", method = RequestMethod.GET)
	public ModelAndView Search_Maritial(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		
		Mmap.put("roleType", roleType);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 
		  
		return new ModelAndView("search_maritialTiles");
	}

	// ...........search...................
	@RequestMapping(value = "/admin/getSearch_maritial", method = RequestMethod.POST)
	public ModelAndView getSearch_maritial(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "service_category1", required = false) String service_category1,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no1,
			@RequestParam(value = "status1", required = false) String status1,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "rank1", required = false) String rank1,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "close_status1", required = false) String close_status1,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
			@RequestParam(value = "cr_date1", required = false) String cr_date) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if (personnel_no1 != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no1)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Search_Maritial");
			}

			if (personnel_no1.length() < 7 || personnel_no1.length() > 9) {
				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:Search_Maritial");
			}
		}
		
		if (!from_date.equals("") || from_date !="") {
			
			if (!valid.isValidDate(from_date)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations From Date ");
				return new ModelAndView("redirect:Search_Maritial");
			}
		}
		
		if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_Maritial");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Search_Maritial");
				}
	      }
		
		if(unit_name!="") {
			  if (!valid.isUnit(unit_name)) {
				  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:Search_Maritial");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_Maritial");
//				}
	      }

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("roleSusNo", roleSusNo);
	//	String afom1[] = from_date.split("/");
		//from_date = afom1[2] + "/" + afom1[1] + "/" + afom1[0];
		ArrayList<ArrayList<String>> list = tm_dao.search_maritial(service_category1, personnel_no1, status1, roleType,
				from_date, roleAccess, roleSusNo, rank1,close_status1,cr_by,cr_date);
		Mmap.put("service_category1", service_category1);
		Mmap.put("personnel_no1", personnel_no1);
		Mmap.put("status1", status1);
		Mmap.put("close_status1", close_status1);
		Mmap.put("roleType", roleType);
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("cr_date1", cr_date);
			Mmap.put("cr_by1", cr_by);
		Mmap.put("list", list);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		return new ModelAndView("search_maritialTiles");
	}

	@RequestMapping(value = "/Popup_Marital_DiscordUrl", method = RequestMethod.POST)
	public ModelAndView Popup_Marital_DiscordUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "m_comm_id", required = false) BigInteger m_comm_id,
			@RequestParam(value = "m_census_id", required = false) int m_census_id) {
		List<ArrayList<String>> list = tm_dao.Popup_Marital_Discord_History(m_comm_id, m_census_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_Marital_Discord_tiles");
	}

	// .................edit........
	@RequestMapping(value = "/Edit_Maritial1")
	public ModelAndView Edit_Maritial1(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
		Mmap.put("Edit_Maritial_Discord_CMD", authDetails);
		List<TB_Maritial_Discord_Status_Child> Status_case = get_StatusOfCase(Integer.parseInt(updateid));
		Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
		Mmap.put("Child_Hid", Status_case.get(0).getId());
		Mmap.put("dt_of_status", Status_case.get(0).getDt_of_status());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Maritial_DiscordTiles");
		
	}
		
		@RequestMapping(value = "/Edit_Maritiallatest")
		public ModelAndView Edit_Maritiallatest(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
			String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();

			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
					
		TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
		Mmap.put("Maritial_Discord_CMD", authDetails);
		List<TB_Maritial_Discord_Status_Child> Status_case = get_StatusOfCaselatest(Integer.parseInt(updateid));
	
		Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
		Mmap.put("Child_Hid", Status_case.get(0).getId());
		Mmap.put("dt_of_status", Status_case.get(0).getDt_of_status());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("list", tm_dao.search_maritial_table("1", "", 
				"", roleAccess, roleSusNo));
		
	
		Mmap.put("msg", msg);
		return new ModelAndView("Maritial_DiscordTiles");
	}

	public @ResponseBody List<TB_Maritial_Discord_Status_Child> get_StatusOfCase(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_Maritial_Discord_Status_Child where maritial_id=:maritial_id and status in ('0','3') ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("maritial_id", id);
		@SuppressWarnings("unchecked")
		List<TB_Maritial_Discord_Status_Child> list = (List<TB_Maritial_Discord_Status_Child>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	
	public @ResponseBody List<TB_Maritial_Discord_Status_Child> get_StatusOfCaselatest(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_Maritial_Discord_Status_Child where maritial_id=:maritial_id and status in ('0','1') ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("maritial_id", id);
		@SuppressWarnings("unchecked")
		List<TB_Maritial_Discord_Status_Child> list = (List<TB_Maritial_Discord_Status_Child>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/Edit_Maritial_Discord_action", method = RequestMethod.POST)
	public ModelAndView Edit_Maritial_Discord_action(
			@ModelAttribute("Edit_Maritial_Discord_CMD") TB_MARITIAL_DISCORD rs,
			TB_Maritial_Discord_Status_Child m_child, HttpServletRequest request, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date dt_comp = null;
		Date dt_st = null;
		String dt_of_comp = request.getParameter("dt_of_comp");
		String dt_of_status = request.getParameter("dt_of_status");
		
		Mmap.put("id2", id);
		
		if (!dt_of_comp.equals("") && !dt_of_comp.equals("DD/MM/YYYY")) {
			dt_comp = format.parse(dt_of_comp);
		}
		if (!dt_of_status.equals("") && !dt_of_status.equals("DD/MM/YYYY")) {
			dt_st = format.parse(dt_of_status);
		}
		
		
		if (request.getParameter("service_category") == null || request.getParameter("service_category").equals("0")
				|| request.getParameter("service_category").equals("null")) {
			Mmap.put("msg", "Please Select Category.");
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		if (request.getParameter("service_category") == "1" || request.getParameter("service_category").equals("1")
				|| request.getParameter("service_category").equals(1)) {
			if (request.getParameter("personnel_no") == null || request.getParameter("personnel_no").equals("")
					|| request.getParameter("personnel_no").equals("null")) {
				Mmap.put("msg", "Please Enter Personnel No");
				return new ModelAndView("redirect:Edit_Maritial1");
			}
			
			if (!valid.isOnlyAlphabetNumericSpaceNot(request.getParameter("personnel_no"))) {
			    Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Edit_Maritial1");
			}		    	  
			if (request.getParameter("personnel_no").length() < 7 || request.getParameter("personnel_no").length() > 9) {
			  Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:Edit_Maritial1");
			}
			
		}
		if (request.getParameter("name_lady") == null || request.getParameter("name_lady").equals("")
				|| request.getParameter("name_lady").equals("null")) {
			Mmap.put("msg", "Please Enter Name of Complainant.");
			return new ModelAndView("redirect:Edit_Maritial1");
		}

		if (!valid.isOnlyAlphabet(request.getParameter("name_lady"))) {
			Mmap.put("msg", " Name of Complainant " + valid.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isvalidLength(request.getParameter("name_lady"), valid.nameMax, valid.nameMin)) {
			Mmap.put("msg", "Name of Complainant " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (request.getParameter("dt_of_comp") == null || request.getParameter("dt_of_comp").equals("")
				|| request.getParameter("dt_of_comp").equals("null")
				|| request.getParameter("dt_of_comp").equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of Complaint/Allegations.");
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isValidDate(request.getParameter("dt_of_comp"))) {
			Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations");
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		
		if (request.getParameter("complaint") == null || request.getParameter("complaint").equals("")
				|| request.getParameter("complaint").equals("null")) {
			Mmap.put("msg", "Please Enter Complaint/Allegations.");
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isOnlyAlphabetNumeric(request.getParameter("complaint"))) {
			Mmap.put("msg", "Complaint/Allegations " + valid.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isvalidLength(request.getParameter("status_of_case"), valid.remarkMax, valid.remarkMin)) {
			Mmap.put("msg", "Complaint/Allegations " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
	
		if (request.getParameter("status_of_case") == null || request.getParameter("status_of_case").equals("")
				|| request.getParameter("status_of_case").equals("null")) {
			Mmap.put("msg", "Please Enter Status of the Case.");
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isOnlyAlphabetNumeric(request.getParameter("status_of_case"))) {
			Mmap.put("msg", " Status of the Case " + valid.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		
		if (!valid.isvalidLength(request.getParameter("status_of_case"), valid.remarkMax, valid.remarkMin)) {
			Mmap.put("msg", "Status of the Case " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Edit_Maritial1");
		}
		try {
			rs.setId(id);
			rs.setDt_of_comp(dt_comp);
			
			String Parent = tm_dao.GetUpdateMarital_DiscordParent(rs, username);// ,
			if (Parent == "Data Updated Successfully.") {
				m_child.setId(Integer.parseInt(request.getParameter("Child_Hid")));
				m_child.setMaritial_id(id);
				m_child.setDt_of_status(dt_st);
				m_child.setStatus_of_case(request.getParameter("status_of_case"));
				Mmap.put("msg", tm_dao.GetUpdateMarital_DiscordChild(m_child, username));// ,
			}
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Search_Maritial");
	}

	@RequestMapping(value = "/Appove_Maritial_Discord", method = RequestMethod.POST)
	public ModelAndView Appove_Maritial_Discord(@ModelAttribute("id3") String updateid, ModelMap Mmap,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
		Mmap.put("App_Maritial_Discord_CMD", authDetails);
		List<TB_Maritial_Discord_Status_Child> Status_case = get_StatusOfCase(Integer.parseInt(updateid));
		Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
		Mmap.put("dt_of_status", Status_case.get(0).getDt_of_status());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Approve_Marital_Discord_tiles");
	}

	@RequestMapping(value = "/approve_Maritial1", method = RequestMethod.POST)
	public @ResponseBody ModelAndView approve_Maritial1(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String username = session.getAttribute("username").toString();
			String hqlUpdate2 = "update from TB_Maritial_Discord_Status_Child set status='2',close_status='2',modified_by=:modified_by,modified_date=:modified_date"
					+ " where maritial_id=:id and status='1' ";
			int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
					.setDate("modified_date", new Date()).executeUpdate();
			String hqlUpdate = "update from TB_MARITIAL_DISCORD set status='1', close_status='2' where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			String hqlUpdate1 = "update from TB_Maritial_Discord_Status_Child set status='1', close_status='2',modified_by=:modified_by,modified_date=:modified_date"
					+ " where maritial_id=:id and status='0' ";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).setString("modified_by", username)
					.setDate("modified_date", new Date()).executeUpdate();
			TB_MARITIAL_DISCORD com = (TB_MARITIAL_DISCORD) sessionHQL.get(TB_MARITIAL_DISCORD.class, id);
			p_comm.update_miso_role_hdr_status(com.getComm_id(), 1, username, "marital_dis_status");
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app1 > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Not Approved.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT APPROVED THIS DATA BECAUSE IT IS ALREADY APPROEVED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Maritial");
	}

	@RequestMapping(value = "/Reject_Marital_Discord", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Marital_Discord(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "reject_remarks4", required = false) String reject_remarks,Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String username = session.getAttribute("username").toString();
			
			String hqlUpdate = "update from TB_MARITIAL_DISCORD set status='3',reject_remarks=:reject_remarks where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setString("reject_remarks", reject_remarks).executeUpdate();
			String hqlUpdate1 = "update from TB_Maritial_Discord_Status_Child set status='3',modified_by=:modified_by,modified_date=:modified_date"
					+ " where maritial_id=:id and status='0' ";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).setString("modified_by", username)
					.setDate("modified_date", new Date()).executeUpdate();
			TB_MARITIAL_DISCORD com = (TB_MARITIAL_DISCORD) sessionHQL.get(TB_MARITIAL_DISCORD.class, id);
			p_comm.update_miso_role_hdr_status(com.getComm_id(), 0, username, "marital_dis_status");
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app1 > 0) {
				liststr.add("Rejected Successfully.");
			} else {
				liststr.add("Not Rejected.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT REJECT THIS DATA BECAUSE IT IS ALREADY REJECTED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Maritial");
	}

	@RequestMapping(value = "/delete_Maritial1", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Maritial1(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = "delete from TB_MARITIAL_DISCORD where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			String hqlUpdate1 = "delete from TB_Maritial_Discord_Status_Child where maritial_id=:id";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Maritial");
	}

	/// view approve
	@RequestMapping(value = "/View_Appove_Maritial_Discord", method = RequestMethod.POST)
	public ModelAndView View_Appove_Maritial_Discord(@ModelAttribute("id5") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
		Mmap.put("View_App_Maritial_Discord_CMD", authDetails);
		List<ArrayList<String>> list = tm_dao.Marital_Discord_History(updateid);
		Mmap.put("list", list);
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("ViewApprove_Marital_Discord_tiles");
	}
	
	
	@RequestMapping(value = "/Close_Appove_Maritial_Discord", method = RequestMethod.POST)
	public ModelAndView Close_Appove_Maritial_Discord(@ModelAttribute("id_close") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
		Mmap.put("View_App_Maritial_Discord_CMD", authDetails);
		List<ArrayList<String>> list = tm_dao.Marital_Discord_History(updateid);
		Mmap.put("list", list);
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("CloseMaritial_Discord_historyTiles");
	}

	@RequestMapping(value = "/Maritial_discord", method = RequestMethod.POST)
	public @ResponseBody String Maritial_discord(String comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"select count(p.id) from TB_MARITIAL_DISCORD p\r\n" + ", TB_Maritial_Discord_Status_Child c  \r\n"
							+ "where p.comm_id=:id and p.id=c.maritial_id and c.status='1'");
			q1.setParameter("id", new BigInteger(comm_id));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			if (list.size() > 0) {
				count = String.valueOf(list.get(0));
			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	//// VIEW AND CANCEL HISTORY //
	// PARENT //
	@RequestMapping(value = "/View_Maritial_Discord_history", method = RequestMethod.POST)
	public ModelAndView View_Maritial_Discord_history(@ModelAttribute("id7") String id, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		Mmap.put("listp", tm_dao.getMaritial_DiscordByid(Integer.parseInt(id)));
		Mmap.put("listch", tm_dao.getMaritial_DiscordChByid(Integer.parseInt(id)));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("View_Maritial_Discord_historyTiles");
	}

	@RequestMapping(value = "/Cancel_Maritial_Discord_dat", method = RequestMethod.POST)
	public ModelAndView Cancel_field_service_dat(@ModelAttribute("id7") String id,
			@RequestParam(value = "cat7", required = false) String category, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
	
		try {
			String hql = "update TB_MARITIAL_DISCORD set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  id=:id";
			Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id))
					.setString("modified_by", username).setParameter("cancel_status", 0)
					.setTimestamp("modified_date", new Date());
			query.executeUpdate();
			/// child
			String hql2 = "update TB_Maritial_Discord_Status_Child set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  maritial_id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id))
					.setString("modified_by", username).setParameter("cancel_status", 0)
					.setTimestamp("modified_date", new Date());
			query2.executeUpdate();
			tx.commit();
			msg = "Data Cancelled Successfully";
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:Search_Maritial");
	}

	@RequestMapping(value = "/Appove_Maritial_Discord_Cancel", method = RequestMethod.POST)
	public ModelAndView Appove_Maritial_Discord_Cancel(@RequestParam(value = "id_ac", required = false) String id,
			ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		Mmap.put("listp", tm_dao.getMaritial_DiscordByid(Integer.parseInt(id)));
		Mmap.put("listch", tm_dao.getMaritial_DiscordChByid(Integer.parseInt(id)));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Appr_Maritial_Discord_historyTiles");
	}

	@RequestMapping(value = "/Appr_Cancel_Maritial_Discord_dat", method = RequestMethod.POST)
	public ModelAndView Cancel_Maritial_Discord_dat(@ModelAttribute("id7") String id,
			@RequestParam(value = "cat7", required = false) String category, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_Maritial_Discord_Status_Child where cancel_status in (-1,2) and maritial_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));
			Long c = (Long) q0.uniqueResult();
			if (c == 0) {
				String hql = "update TB_MARITIAL_DISCORD set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
						+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id))
						.setString("modified_by", username).setParameter("cancel_status", 1).setParameter("status", -1)
						.setTimestamp("modified_date", new Date()).setString("cancel_by", username)
						.setTimestamp("cancel_date", new Date());
				query.executeUpdate();
			}
			/// child
			String hql2 = "update TB_Maritial_Discord_Status_Child set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
					+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by where  maritial_id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id))
					.setString("modified_by", username).setParameter("cancel_status", 1).setParameter("status", -1)
					.setTimestamp("modified_date", new Date()).setString("cancel_by", username)
					.setTimestamp("cancel_date", new Date());
			query2.executeUpdate();
			tx.commit();
			msg = "Data Approved Successfully";
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:Search_Maritial");
	}

	@RequestMapping(value = "/Reject_Cancel_Maritial_Discord_dat", method = RequestMethod.POST)
	public ModelAndView Reject_Cancel_Maritial_Discord_dat(@ModelAttribute("id_rj") String id,
			@RequestParam(value = "cat_rj", required = false) String category, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			String hql = "update TB_MARITIAL_DISCORD set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  id=:id";
			Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id))
					.setString("modified_by", username).setParameter("cancel_status", 2)
					.setTimestamp("modified_date", new Date());
			query.executeUpdate();
			/// child
			String hql2 = "update TB_Maritial_Discord_Status_Child set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  maritial_id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id))
					.setString("modified_by", username).setParameter("cancel_status", 2)
					.setTimestamp("modified_date", new Date());
			query2.executeUpdate();
			tx.commit();
			msg = "Data Rejected Successfully";
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:Search_Maritial");
	}

	//////// CHILD /////////////
	@RequestMapping(value = "/Cancel_Maritial_Discord_Child", method = RequestMethod.POST)
	public ModelAndView Cancel_field_service_Child(@RequestParam(value = "id_ch", required = false) String ch_id,
			@RequestParam(value = "id_p", required = false) String id,
			@RequestParam(value = "cat_ch", required = false) String category, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession session) {
		String redirr = "View_Maritial_Discord_historyTiles";
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_Maritial_Discord_Status_Child where cancel_status in (-1,2) and maritial_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));
			Long c = (Long) q0.uniqueResult();
			Query q01 = sessionhql.createQuery(
					"select count(id) from TB_Maritial_Discord_Status_Child where cancel_status in (-1,2) and status=1 and maritial_id =:p_id");
			q01.setParameter("p_id", Integer.parseInt(id));
			Long c1 = (Long) q01.uniqueResult();
			if (c1 <= 1) {
				redirr = "redirect:Search_Maritial";
			}
			if (c <= 1) {
				redirr = "redirect:Search_Maritial";
				String hql = "update TB_MARITIAL_DISCORD set modified_by=:modified_by ,modified_date=:modified_date, "
						+ "cancel_status=:cancel_status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id))
						.setString("modified_by", username).setParameter("cancel_status", 0)
						.setTimestamp("modified_date", new Date());
				query.executeUpdate();
			}
			/// child
			String hql2 = "update TB_Maritial_Discord_Status_Child set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(ch_id))
					.setString("modified_by", username).setParameter("cancel_status", 0)
					.setTimestamp("modified_date", new Date());
			query2.executeUpdate();
			tx.commit();
			msg = "Data Cancelled Successfully";
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		Mmap.put("listp", tm_dao.getMaritial_DiscordByid(Integer.parseInt(id)));
		Mmap.put("listch", tm_dao.getMaritial_DiscordChByid(Integer.parseInt(id)));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView(redirr);
	}

	@RequestMapping(value = "/RejectCh_Cancel_Maritial_Discord_dat", method = RequestMethod.POST)
	public ModelAndView RejectCh_Cancel_Maritial_Discord_dat(
			@RequestParam(value = "id_rjCh", required = false) String ch_id,
			@RequestParam(value = "id_rjP", required = false) String id,
			@RequestParam(value = "cat_rjCh", required = false) String category, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession session) {
		String redirr = "Appr_Maritial_Discord_historyTiles";
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery(
					"select count(id) from TB_Maritial_Discord_Status_Child where cancel_status in (-1,2) and maritial_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));
			Long c = (Long) q0.uniqueResult();
			Query q01 = sessionhql.createQuery(
					"select count(id) from TB_Maritial_Discord_Status_Child where cancel_status=0 and maritial_id =:p_id");
			q01.setParameter("p_id", Integer.parseInt(id));
			Long c1 = (Long) q01.uniqueResult();
			if (c1 <= 1) {
				redirr = "redirect:Search_Maritial";
			}
			if (c == 0 && c1 <= 1) {
				redirr = "Search_Field_ServicelTiles";
				String hql = "update TB_MARITIAL_DISCORD set modified_by=:modified_by ,modified_date=:modified_date, "
						+ "cancel_status=:cancel_status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id))
						.setString("modified_by", username).setParameter("cancel_status", 2)
						.setTimestamp("modified_date", new Date());
				query.executeUpdate();
			}
			/// child
			String hql2 = "update TB_Maritial_Discord_Status_Child set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(ch_id))
					.setString("modified_by", username).setParameter("cancel_status", 2)
					.setTimestamp("modified_date", new Date());
			query2.executeUpdate();
			tx.commit();
			msg = "Data Rejected Successfully";
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		Mmap.put("listp", tm_dao.getMaritial_DiscordByid(Integer.parseInt(id)));
		Mmap.put("listch", tm_dao.getMaritial_DiscordChByid(Integer.parseInt(id)));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView(redirr);
	}
	//// VIEW AND CANCEL HISTORY ENDS//
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/admin/ne_child_tableaction", method = RequestMethod.POST)
	public @ResponseBody String ne_child_tableaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		
		String username = session.getAttribute("username").toString();

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date dt_st = null;
		
		String Child_Hid = request.getParameter("Child_Hid");
	
		String mari_id = request.getParameter("id");
		String dt_of_status = request.getParameter("dt_of_status");
		String status_of_case = request.getParameter("status_of_case");
		if (!dt_of_status.equals("") && !dt_of_status.equals("DD/MM/YYYY")) {
			dt_st = format.parse(dt_of_status);
		}
		
		
		String msg = "";
		try {
			

	
			TB_Maritial_Discord_Status_Child cr = new TB_Maritial_Discord_Status_Child();

            Query q1 = sessionhql.createQuery(
					"select count(id) From TB_Maritial_Discord_Status_Child where maritial_id=:maritial_id and dt_of_status=:dt_of_status and status_of_case=:status_of_case  and  id=:id and status='1' ");
                q1.setParameter("maritial_id", Integer.parseInt(mari_id));
	            q1.setParameter("dt_of_status", dt_st);
	            q1.setParameter("status_of_case", status_of_case);
	            q1.setParameter("id", Integer.parseInt(Child_Hid));
		          	Long c = (Long) q1.uniqueResult();
		          	if (c > 0)

					{
		          		return "You can Not Enter Data Of Same Complaint/Allegations On Same Date.";
					}

				if (c == 0) {
					cr.setMaritial_id(Integer.parseInt(mari_id));
					cr.setStatus(0);
					cr.setStatus_of_case(status_of_case);
					cr.setDt_of_status(dt_st);
					cr.setCreated_by(username);
					cr.setCreated_date(date);
					cr.setStatus(0);
					int id = (int) sessionhql.save(cr);
					//tx.commit();
					msg = Integer.toString(id);
					//Mmap.put("msg", "Data Saved Successfully.");
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


	
	
	@RequestMapping(value = "/close_Maritial_case", method = RequestMethod.POST)
	public @ResponseBody ModelAndView close_Maritial_case(@ModelAttribute("id6") int id, 
			@ModelAttribute("status_of_case6") String status_of_case,
			@ModelAttribute("dt_of_status6") Date dt_of_status,BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		//try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String username = session.getAttribute("username").toString();
			String hqlUpdate2 = "update from TB_Maritial_Discord_Status_Child set close_status='0',status_of_case=:status_of_case,dt_of_status=:dt_of_status ,modified_by=:modified_by,modified_date=:modified_date"
					+ " where maritial_id=:maritial_id  ";
			int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("maritial_id", id).setString("status_of_case",status_of_case )
					.setTimestamp( "dt_of_status", dt_of_status).setString("modified_by", username)
					.setDate("modified_date", new Date()).executeUpdate();
			String hqlUpdate = "update from TB_MARITIAL_DISCORD set close_status='0'where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			
			TB_MARITIAL_DISCORD com = (TB_MARITIAL_DISCORD) sessionHQL.get(TB_MARITIAL_DISCORD.class, id);
			
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app2 > 0) {
				liststr.add("Requesting For Closing This Case Has been Send Successfully.");
			} else {
				liststr.add("Not Approved.");
			}
			model.put("msg", liststr.get(0));
		/*} catch (Exception e) {
			liststr.add("CAN NOT APPROVED THIS DATA BECAUSE IT IS ALREADY APPROEVED.");
			model.put("msg", liststr.get(0));
		}*/
		return new ModelAndView("redirect:Maritial");
	}
	
	
	@RequestMapping(value = "/closeapprove_Maritial1", method = RequestMethod.POST)
	public @ResponseBody ModelAndView closeapprove_Maritial1(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String username = session.getAttribute("username").toString();
			String hqlUpdate2 = "update from TB_Maritial_Discord_Status_Child set close_status='1',modified_by=:modified_by,modified_date=:modified_date"
					+ " where maritial_id=:maritial_id  ";
			int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("maritial_id", id).setString("modified_by", username)
					.setDate("modified_date", new Date()).executeUpdate();
			String hqlUpdate = "update from TB_MARITIAL_DISCORD set close_status='1' where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			
			TB_MARITIAL_DISCORD com = (TB_MARITIAL_DISCORD) sessionHQL.get(TB_MARITIAL_DISCORD.class, id);
			
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app2 > 0) {
				liststr.add("Case Closed.");
			} else {
				liststr.add("Not Approved.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT APPROVED THIS DATA BECAUSE IT IS ALREADY APPROEVED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Maritial");
	}
	
	@RequestMapping(value = "/admin/getSearch_maritial_app", method = RequestMethod.POST)
	public ModelAndView getSearch_maritial_app(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "service_category1", required = false) String service_category1,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no1,
			@RequestParam(value = "status1", required = false) String status1,
			@RequestParam(value = "p_rank1", required = false) String p_rank1,
			@RequestParam(value = "app_unit_name1", required = false) String app_unit_name1,
			@RequestParam(value = "app_sus_no1", required = false) String app_sus_no1,
			@RequestParam(value = "cadet_name1", required = false) String cadet_name1,
			@RequestParam(value = "comm_id1", required = false) String comm_id1,
			@RequestParam(value = "census_id1", required = false) String census_id1) {
		
	
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if (personnel_no1 != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no1)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Search_Maritial");
			}

			if (personnel_no1.length() < 7 || personnel_no1.length() > 9) {
				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:Search_Maritial");
			}
		}
		
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("roleSusNo", roleSusNo);
	//	String afom1[] = from_date.split("/");
		//from_date = afom1[2] + "/" + afom1[1] + "/" + afom1[0];
		ArrayList<ArrayList<String>> list = tm_dao.search_maritial_table(service_category1, personnel_no1, roleType,
			 roleAccess, roleSusNo);
		
		
		Mmap.put("service_category1", service_category1);
		Mmap.put("personnel_no1", personnel_no1);
		Mmap.put("status1", status1);
		Mmap.put("cadet_name1", cadet_name1);
		Mmap.put("p_rank1", p_rank1);
		Mmap.put("app_unit_name1", app_unit_name1);
		Mmap.put("app_sus_no1", app_sus_no1);
		Mmap.put("comm_id1", comm_id1);
		Mmap.put("census_id1", census_id1);
		Mmap.put("roleType", roleType);
	
		Mmap.put("list", list);
		
	
		
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		return new ModelAndView("Maritial_DiscordTiles");
	}

	@RequestMapping(value = "/close_Maritial1")
	public ModelAndView close_Maritial1(@ModelAttribute("id6") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Maritial", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();

		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
				
	TB_MARITIAL_DISCORD authDetails = tm_dao.getmaritialByid(Integer.parseInt(updateid));
	Mmap.put("Maritial_Discord_close_CMD", authDetails);
	List<TB_Maritial_Discord_Status_Child> Status_case = get_StatusOfCaselatest(Integer.parseInt(updateid));
	
	Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
	Mmap.put("Child_Hid", Status_case.get(0).getId());
	Mmap.put("dt_of_status", Status_case.get(0).getDt_of_status());
	Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
	Mmap.put("list", tm_dao.search_maritial_table("1", "", 
			"", roleAccess, roleSusNo));
	

	Mmap.put("msg", msg);
	return new ModelAndView("Maritial_Discord_CloseTiles");
}
}



