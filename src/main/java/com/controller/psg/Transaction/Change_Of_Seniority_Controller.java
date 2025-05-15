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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.SeniorityDAO;
import com.models.psg.update_census_data.TB_CENSUS_BPET;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_Of_Seniority_Controller {
	@Autowired
	SeniorityDAO SD;
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	PsgDashboardController das = new PsgDashboardController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Seniority_url", method = RequestMethod.GET)
	public ModelAndView Seniority_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("Seniority_url", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("msg", msg);
		Mmap.put("maxDate", new Date());
		return new ModelAndView("Insert_SeniorityTiles");
	}

	@RequestMapping(value = "/admin/getseniorityData", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getseniorityData(ModelMap Mmap, HttpSession session, BigInteger comm_id,
			HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = SD.getseniorityData(comm_id);
		return list;
	}

	@RequestMapping(value = "/admin/getoldseniorityDate", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getoldseniorityDate(ModelMap Mmap, HttpSession session,
			BigInteger comm_id, HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = SD.getoldseniorityDate(comm_id);
		return list;
	}

	@RequestMapping(value = "/admin/Seniority_Details_Action", method = RequestMethod.POST)
	public @ResponseBody String Seniority_Details_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String personnel_no = request.getParameter("personnel_no");
		String authority = request.getParameter("authority");
		String reason = request.getParameter("reason");
		String date_of_authority = request.getParameter("date_of_authority");
		String date_of_seniority = request.getParameter("date_of_seniority");
		String comm_date = request.getParameter("comm_date");
		String date_of_applicability = request.getParameter("date_of_applicability");
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}
		Date authority_date = null;
		Date seniority_date = null;
		Date applicability_date = null;
		String Sen_id = request.getParameter("Sen_id");
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		
		if (personnel_no.equals("")) {
			return "Please Enter Personnel No";
		}
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
		    return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}		    	  
		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
		  return "Personal No Should Contain Maximum 9 Character";
		}				   
		if (date_of_seniority == null || date_of_seniority.equals("null") || date_of_seniority.equals("DD/MM/YYYY")
				|| date_of_seniority.equals("")) {
			return "Please Enter New Seniority Date";
		}
		if (!valid.isValidDate(date_of_seniority)) {
 			return valid.isValidDateMSG + " of New Seniority";	
		}
		if (!date_of_seniority.equals("") && !date_of_seniority.equals("DD/MM/YYYY")) {
			seniority_date = format.parse(date_of_seniority);
		}
		if (reason == null || reason.equals("")) {
			return "Please Enter Reason";
		}
		if (!valid.isValidAuth(reason)) {
	 		return valid.isValidAuthMSG + " Reason ";	
		}
 		if (!valid.isvalidLength(reason,valid.authorityMax,valid.authorityMin)) {
 			return " Reason " + valid.isValidLengthMSG;	
		}
		if (date_of_applicability == null || date_of_applicability.equals("null")
				|| date_of_applicability.equals("DD/MM/YYYY") || date_of_applicability.equals("")) {
			return "Please Select Effective Date of Seniority";
		} 
		else if (!valid.isValidDate(date_of_applicability)) {
 			return valid.isValidDateMSG + " From Which Change in Seniority is Effective";	
		}
		else {
			applicability_date = format.parse(date_of_applicability);
		}
		if (authority.equals("")) {
			return "Please Enter Authority";
		}
		if (!valid.isValidAuth(authority)) {
	 		return valid.isValidAuthMSG + " Authority ";	
		}
 		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return " Authority " + valid.isValidLengthMSG;	
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			authority_date = format.parse(date_of_authority);
		}
		String hqlUpdate_s2 = "select count(id) from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status in (1,2) and applicablity_date=:eff_date_of_seniority";
		Query query_s2 = sessionhql.createQuery(hqlUpdate_s2).setBigInteger("comm_id", comm_id)
				.setTimestamp("eff_date_of_seniority", format.parse(date_of_applicability)).setMaxResults(1);
		Long c22 = (Long) query_s2.uniqueResult();
		if (c22 != null && c22 > 0) {
			return " Effective Date of Seniority Is Already Exist. ";
		}
		String msg = "";
		try {
			if (Integer.parseInt(Sen_id) == 0) {
				TB_PSG_CHANGE_OF_SENIORITY cs = new TB_PSG_CHANGE_OF_SENIORITY();
				cs.setCensus_id(census_id);
				cs.setComm_id(comm_id);
				cs.setAuthority(authority);
				cs.setReason(reason);
				cs.setDate_of_authority(format.parse(date_of_authority));
				cs.setDate_of_seniority(format.parse(date_of_seniority));
				cs.setApplicablity_date(applicability_date);
				cs.setCreated_by(username);
				cs.setCreated_date(new Date());
				cs.setStatus(0);
				cs.setModified_by(username);
				cs.setModified_date(new Date());
				int id = (int) sessionhql.save(cs);
				msg = String.valueOf(id);
			} else {
				String hql = "update TB_PSG_CHANGE_OF_SENIORITY set authority=:authority,date_of_authority=:date_of_authority,applicablity_date=:applicablity_date,"
						+ "date_of_seniority=:date_of_seniority,reason=:reason,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority)
						.setDate("date_of_authority", authority_date).setDate("applicablity_date", applicability_date)
						.setDate("date_of_seniority", seniority_date).setInteger("id", Integer.parseInt(Sen_id))
						.setString("reason", reason).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			p_comm.update_miso_role_hdr_status(comm_id, 0, username, "seniority_status");
			tx.commit();
		} catch (RuntimeException | java.text.ParseException e) {
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

	@RequestMapping(value = "/admin/Search_SeniorityUrl", method = RequestMethod.GET)
	public ModelAndView Search_SeniorityUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("list", SD.Search_Seniority("", "0", "", "", "","","", roleSusNo, roleType));
		
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("search_SeniorityTiles");
	}

	@RequestMapping(value = "/admin/GetSearch_Seniority", method = RequestMethod.POST)
	public ModelAndView GetSearch_Seniority(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
			@RequestParam(value = "cr_date1", required = false) String cr_date) {
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:Search_SeniorityUrl");
			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Search_SeniorityUrl");
			}
		}

		if (unit_name != "") {
			if (!valid.isUnit(unit_name)) {
				Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
				return new ModelAndView("redirect:Search_SeniorityUrl");
			}

//			if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//				Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//				return new ModelAndView("redirect:Search_SeniorityUrl");
//			}
		}

		if (personnel_no != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Search_SeniorityUrl");
			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {
				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:Search_SeniorityUrl");
			}
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		ArrayList<ArrayList<String>> list = SD.Search_Seniority(personnel_no, status, unit_sus_no, unit_name, rank,
				cr_by,cr_date,roleSusNo, roleType);
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("personnel_no1", personnel_no);
		Mmap.put("status1", status);
		Mmap.put("rank1", rank);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("cr_by1", cr_by);
		Mmap.put("cr_date1", cr_date);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("search_SeniorityTiles");
	}

	@RequestMapping(value = "/Approve_Search_Seniority", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Search_Seniority(@ModelAttribute("idA") BigInteger id,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", sessionA.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		String username = sessionA.getAttribute("username").toString();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate0 = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status,modified_by=:modified_by,modified_date=:modified_date where comm_id=:id and (status != '0' and  status != '-1')";
			int app0 = sessionHQL.createQuery(hqlUpdate0).setInteger("status", 2).setString("modified_by", username)
					.setDate("modified_date", new Date()).setBigInteger("id", id).executeUpdate();
			String hqlUpdate = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status,modified_by=:modified_by,modified_date=:modified_date  where comm_id=:id and status='0'";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setDate("modified_date", new Date()).setBigInteger("id", id).executeUpdate();
			
			List<TB_PSG_CHANGE_OF_SENIORITY> list =  getDateofseniority(id);
			p_comm.update_miso_role_hdr_status(id, 1, username, "seniority_status");
			
			String hqlUpdate1 = "update TB_TRANS_PROPOSED_COMM_LETTER set date_of_seniority=:date_of_seniority where id=:id ";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setTimestamp("date_of_seniority", list.get(0).getDate_of_seniority())
					.setBigInteger("id", id).executeUpdate();
			
			if (app > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
			model.put("msg", liststr.get(0));
			tx.commit();
			sessionHQL.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return new ModelAndView("redirect:Search_SeniorityUrl");
	}

	/***************** Reject for Search_cda ******************/
	@RequestMapping(value = "/Reject_Search_Seniority", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Search_Seniority(@ModelAttribute("idR") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
			Authentication authentication) {
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", sessionA.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = sessionA.getAttribute("username").toString();
		String hqlUpdate = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks)
				.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id)
				.executeUpdate();
		TB_PSG_CHANGE_OF_SENIORITY com = (TB_PSG_CHANGE_OF_SENIORITY) sessionHQL.get(TB_PSG_CHANGE_OF_SENIORITY.class,
				id);
		p_comm.update_miso_role_hdr_status(com.getComm_id(), 0, username, "seniority_status");
		if (app > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}
		model.put("msg", liststr.get(0));
		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:Search_SeniorityUrl");
	}

	@RequestMapping(value = "/Edit_Search_Seniority", method = RequestMethod.POST)
	public ModelAndView Edit_Search_Seniority(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no_e") String personnel_no,
			@ModelAttribute("personnel_no5") String personnel_no5, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
	
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", sessionEdit.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		TB_PSG_CHANGE_OF_SENIORITY authDetails = SD.getSearch_SeniorityByid(Integer.parseInt(updateid));
		Mmap.put("msg", msg);
		Mmap.put("Edit_SeniorityCMD", authDetails);
		Mmap.put("personnel_no5", personnel_no);
		return new ModelAndView("Edit_SeniorityTiles");
	}

	/******************************
	 * Delete For Search_cda
	 ***********************************/
	@RequestMapping(value = "/Delete_Search_Seniority", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Search_Seniority(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		Boolean val = roledao.ScreenRedirect("Search_SeniorityUrl", sessionA.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = "delete from TB_PSG_CHANGE_OF_SENIORITY where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
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
		return new ModelAndView("redirect:Search_SeniorityUrl");
	}

	@RequestMapping(value = "/Popup_SeniorityUrl", method = RequestMethod.POST)
	public ModelAndView Popup_SeniorityUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_id1", required = false) BigInteger comm_id) {
		List<ArrayList<String>> list = SD.Popup_Change_of_seniority(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_Seniority_tiles");
	}

	@RequestMapping(value = "/getDateofseniorityAlreadyExistCount", method = RequestMethod.POST)
	@ResponseBody
	public int getDateofseniorityAlreadyExistCount(String comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int count = 0;
		try {
			Query q1 = sessionHQL.createQuery(
					"SELECT COUNT(id) FROM tb_psg_change_of_comission where comm_id =:comm_id and status='0' ");
			q1.setParameter("comm_id", new BigInteger(comm_id));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			if (list.size() > 0) {
				if (list.get(0) != null && !list.get(0).equals("")) {
					count = Integer.parseInt(list.get(0));
				}
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

	@RequestMapping(value = "/admin/ViewCancelHistorySeniority", method = RequestMethod.POST)
	public ModelAndView Change_In_Identity_CardUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "can_comm_id", required = false) String comm_id1,
			@RequestParam(value = "can_pers_no", required = false) String personal_no,
			@RequestParam(value = "can_status", required = false) String status, HttpServletRequest request) {
		/*
		 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("Bank", roleid); if(val == false) { return new
		 * ModelAndView("AccessTiles"); }
		 */
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		BigInteger comm_id = BigInteger.ZERO;
		if(!comm_id1.equals("")) {
			comm_id = new BigInteger(comm_id1);
		}
		Mmap.put("msg", msg);
		Mmap.put("comm_id", comm_id);
		Mmap.put("personal_no", personal_no);
		Mmap.put("status", status);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleType", roleType);
		Mmap.put("roleAccess", roleAccess);
		return new ModelAndView("Change_Of_Seniority_History_Tiles");
	}

	@RequestMapping(value = "/getSeniorityHistoryData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSeniorityHistoryData(HttpServletRequest request,
			HttpSession sessionA, Authentication authentication) {
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		return SD.getHisChangeOfSeniority(comm_id, status, sessionA);
	}

	@RequestMapping(value = "/admin/CancelHisSeniorityData", method = RequestMethod.POST)
	public @ResponseBody String CancelHisSeniorityData(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id").toString());
		int status = Integer.parseInt(request.getParameter("set_status").toString());
		try {
			String hql_n = "update TB_PSG_CHANGE_OF_SENIORITY set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
					+ " where  id=:id";
			Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status).setInteger("id", id)
					.setString("modified_by", username).setTimestamp("modified_date", new Date());
			msg = query_n.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return "0";
		}
		return msg;
	}

	@RequestMapping(value = "/ApproveSeniorityHistoryData", method = RequestMethod.POST)
	public @ResponseBody String ApproveSeniorityHistoryData(HttpServletRequest request, ModelMap Mmap,
			HttpSession session) throws ParseException {
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String comm_id = request.getParameter("comm_id");
		List<Map<String, Object>> ChangeOfseniority = SD.getHisChangeOfSeniority(new BigInteger(comm_id), 0, session);
		Date modified_date = new Date();
		String msg = "";
		try {
			if (ChangeOfseniority.size() > 0) {
				msg = SD.approveHisseniority(new BigInteger(comm_id), ChangeOfseniority, username, modified_date);
			}
		} catch (RuntimeException e) {
			msg = "Couldn?t roll back transaction " + e;
		} finally {
		}
		return msg;
	}
	
	
	public List<TB_PSG_CHANGE_OF_SENIORITY> getDateofseniority(BigInteger comm_id) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status = 0");
		q1.setParameter("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_SENIORITY> list = (List<TB_PSG_CHANGE_OF_SENIORITY>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
}
