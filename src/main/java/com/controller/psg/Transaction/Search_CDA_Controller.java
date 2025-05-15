package com.controller.psg.Transaction;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
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
import com.dao.psg.update_census_data.Search_cdaDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Search_CDA_Controller {
	@Autowired
	Search_cdaDAO CD;
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	PsgDashboardController das = new PsgDashboardController();
	
	
	/*
	 * @Autowired private Cda_Acc_No_DAO cda_dao;
	 */

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Search_CDAUrl", method = RequestMethod.GET)
	public ModelAndView Search_CDAUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
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
		Mmap.put("list", CD.Search_CDA("", "0", "", "", "","","", roleSusNo, roleType));
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 
		return new ModelAndView("search_CDATiles");
	}

	/************** Search for Search_cda *******************/
	@RequestMapping(value = "/admin/GetSearch_CDA", method = RequestMethod.POST)
	public ModelAndView GetSearch_CDA(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
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
		
		
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		
		
		 if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_CDAUrl");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Search_CDAUrl");
				}
	      }
		 
		 if(unit_name!="") {
			  if (!valid.isOnlyAlphabetNumeric(unit_name)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");
					return new ModelAndView("redirect:Search_CDAUrl");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_CDAUrl");
//				}
	      }
		 
		 if(personnel_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:Search_CDAUrl");
				}
			  
			  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
					return new ModelAndView("redirect:Search_CDAUrl");
				}
	      }
		 
		 
		 

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		ArrayList<ArrayList<String>> list = CD.Search_CDA(personnel_no, status, unit_sus_no, unit_name, rank,cr_by,cr_date, roleSusNo,
				roleType);
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("personnel_no1", personnel_no);
		Mmap.put("status1", status);
		Mmap.put("rank1", rank);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 
		   Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		return new ModelAndView("search_CDATiles");
	}

	/**************** Approve for Search_cda ***************************/
	@RequestMapping(value = "/Approve_Search_CDA", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Search_CDA(@ModelAttribute("idA") BigInteger id, HttpServletRequest request,
			HttpSession sessionA, ModelMap model, @RequestParam(value = "msg", required = false) String msg) {
		
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		String username = sessionA.getAttribute("username").toString();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate0 = "update TB_CDA_ACC_NO set status=:status,modified_by=:modified_by where comm_id=:id and  (status != '0' and status != '-1')";
			int app0 = sessionHQL.createQuery(hqlUpdate0).setInteger("status", 2).setString("modified_by", username)
					.setBigInteger("id", id).executeUpdate();
			String hqlUpdate = "update TB_CDA_ACC_NO set status=:status,modified_by=:modified_by,modified_date=:modified_date  where comm_id=:id and status='0'";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setDate("modified_date", new Date()).setBigInteger("id", id).executeUpdate();
			p_comm.update_miso_role_hdr_status(id, 1, username, "cda_status");
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
		return new ModelAndView("redirect:Search_CDAUrl");
	}

	/***************** Reject for Search_cda ******************/
	@RequestMapping(value = "/Reject_Search_CDA", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Search_CDA(@ModelAttribute("idR") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
			Authentication authentication) {
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = sessionA.getAttribute("username").toString();
		String hqlUpdate = "update TB_CDA_ACC_NO set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks)
				.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id)
				.executeUpdate();
		TB_CDA_ACC_NO com = (TB_CDA_ACC_NO) sessionHQL.get(TB_CDA_ACC_NO.class, id);
		p_comm.update_miso_role_hdr_status(com.getComm_id(), 0, username, "cda_status");
		if (app > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}
		model.put("msg", liststr.get(0));
		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:Search_CDAUrl");
	}

	/******************************
	 * Delete For Search_cda
	 ***********************************/
	@RequestMapping(value = "/Delete_Search_CDA", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Search_CDA(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
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
			String hqlUpdate = "delete from TB_CDA_ACC_NO where id=:id";
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
		return new ModelAndView("redirect:Search_CDAUrl");
	}

	/*********** insert/save page open cda_acct *******************/
	@RequestMapping(value = "/admin/cda_acc_no_url", method = RequestMethod.GET)
	public ModelAndView cda_acc_no_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {


		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cda_acc_no_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Insert_CDATiles");
	}

	@RequestMapping(value = "/admin/cda_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CDA_ACC_NO> cda_GetData(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CDA_ACC_NO where  comm_id=:comm_id and status=0 order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CDA_ACC_NO> list = (List<TB_CDA_ACC_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/******* insert+update cda *********************/
	@RequestMapping(value = "/admin/Change_of_cda_action", method = RequestMethod.POST)
	public @ResponseBody String Change_of_cda_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String personnel_no = request.getParameter("personnel_no");
		String cda_acc_no = request.getParameter("cda_acc_no");
		
		
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}
		
		
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}

		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			return "Personal No Should Contain Maximum 9 Character";
		}
	      
		if (cda_acc_no == null || cda_acc_no.equals("0")) {
			return "Please Select CDA Account No.";
		}
		
		if (!valid.validateSlash(cda_acc_no)) {
			return valid.validateSlashMSG + " CDA Account No";
		}

		if (cda_acc_no.length() > 20) {
			return "CDA Account No Should Contain Maximum 20 Character";
		}
		
		
		String cda_acc_id = request.getParameter("cda_acc_id");
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		String msg = "";
		try {
		
			//Date today = new Date();
		//	Date todayMorning = DateUtils.truncate(today, Calendar.DATE);
		//	Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59),
		//			59);
			
			if (Integer.parseInt(cda_acc_id) == 0) {
				
				Query q0 = sessionhql.createQuery(
						"select count(id) from TB_CDA_ACC_NO where comm_id=:comm_id and cda_acc_no=:cda_acc_no");
				q0.setParameter("comm_id", comm_id);
				q0.setParameter("cda_acc_no", cda_acc_no);
				Long c1 = (Long) q0.uniqueResult();
				if (c1 > 0) {
					return "Data already Exist.";
				}
				else {

					Query q01 = sessionhql.createQuery(
							"select count(id) from TB_CDA_ACC_NO where comm_id=:comm_id and status=0")
					         .setParameter("comm_id", comm_id);
					Long c = (Long) q01.uniqueResult();
					if (c > 0) {
						return "The data already exists, please approve it in the pending list";
					}
					else {
						TB_CDA_ACC_NO cd = new TB_CDA_ACC_NO();
						cd.setCda_acc_no(cda_acc_no);
						cd.setCensus_id(census_id);
						cd.setComm_id(comm_id);
						cd.setCreated_by(username);
						cd.setCreated_date(date);
						cd.setStatus(0);
						int id = (int) sessionhql.save(cd);
						msg = Integer.toString(id);
					}
					
				}
				
			
			
			}
			if ( Integer.parseInt(cda_acc_id) > 0) {
				String hql = "update TB_CDA_ACC_NO set cda_acc_no=:cda_acc_no,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  " + " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("cda_acc_no", cda_acc_no)
						.setInteger("id", Integer.parseInt(cda_acc_id)).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0);
				msg = query.executeUpdate() > 0 ? "update" : "0";
			}
			p_comm.update_miso_role_hdr_status(comm_id, 0, username, "cda_status");
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

	///// pratiksha///
	@RequestMapping(value = "/Edit_Search_CDA", method = RequestMethod.POST)
	public ModelAndView Edit_Search_CDA(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no_e") String personnel_no,
			@ModelAttribute("personnel_no5") String personnel_no5, @ModelAttribute("rank5") String rank,
			@ModelAttribute("status5") String status, @ModelAttribute("unit_name5") String unit_name,
			@ModelAttribute("unit_sus_no5") String unit_sus_no,
			@ModelAttribute("cr_by5") String cr_by5,
			@ModelAttribute("cr_date5") String cr_date5,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_CDA_ACC_NO authDetails = CD.getSearch_CDAByid(Integer.parseInt(updateid));
		Mmap.put("msg", msg);
		Mmap.put("rank5", rank);
		Mmap.put("status5", status);
		Mmap.put("unit_name5", unit_name);
		Mmap.put("unit_sus_no5", unit_sus_no);
		Mmap.put("personnel_no5", personnel_no5);
		Mmap.put("authDetails", authDetails);
		Mmap.put("cr_by5", cr_by5);
		Mmap.put("cr_date5", cr_date5);	
		return new ModelAndView("Insert_CDATiles");
	}

	@RequestMapping(value = "/Popup_CDAUrl", method = RequestMethod.POST)
	public ModelAndView Popup_CDAUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_id1", required = false) BigInteger comm_id) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_CDAUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		
		List<ArrayList<String>> list = CD.Popup_Change_of_CDA(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_CDA_tiles");
	}

	@RequestMapping(value = "/admin/ViewCancelHistoryCDA", method = RequestMethod.POST)
	public ModelAndView Change_In_Identity_CardUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "can_comm_id", required = false) String comm_id,
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
		Mmap.put("msg", msg);
		Mmap.put("comm_id", comm_id);
		Mmap.put("personal_no", personal_no);
		Mmap.put("status", status);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleType", roleType);
		Mmap.put("roleAccess", roleAccess);
		return new ModelAndView("Change_Of_CDA_History_Tiles");
	}

	@RequestMapping(value = "/getCDAHistoryData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCDAHistoryData(HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		int status = Integer.parseInt(request.getParameter("status"));
	
		return CD.getHisChangeOfCDA(comm_id, status, sessionA);
	}

	@RequestMapping(value = "/admin/CancelHisCDAData", method = RequestMethod.POST)
	public @ResponseBody String CancelHisCDAData(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id").toString());
		int status = Integer.parseInt(request.getParameter("set_status").toString());
		try {
			String hql_n = "update TB_CDA_ACC_NO set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
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

	@RequestMapping(value = "/ApproveCDAHistoryData", method = RequestMethod.POST)
	public @ResponseBody String ApproveCDAHistoryData(HttpServletRequest request, ModelMap Mmap, HttpSession session)
			throws ParseException {
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String comm_id = request.getParameter("comm_id");
		List<Map<String, Object>> ChangeOfCDA = CD.getHisChangeOfCDA(new BigInteger(comm_id), 0, session);
		Date modified_date = new Date();
		String msg = "";
		try {
			if (ChangeOfCDA.size() > 0) {
				msg = CD.approveHisCDA(new BigInteger(comm_id), ChangeOfCDA, username, modified_date);
			}
		} catch (RuntimeException e) {
			msg = "Couldn?t roll back transaction " + e;
		} finally {
		}
		return msg;
	}
}
