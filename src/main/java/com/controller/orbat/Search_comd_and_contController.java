package com.controller.orbat;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.Comd_and_cont_instDAOImpl;
import com.dao.orbat.Comd_and_cont_inst_DAO;
import com.dao.orbat.UnitProfileDAO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Search_comd_and_contController {

	@Autowired
	private UnitProfileDAO unitpro;

	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	@Autowired
	Comd_and_cont_inst_DAO captureDAO = new Comd_and_cont_instDAOImpl();

	@RequestMapping(value = "/admin/search_comd_and_contUrl", method = RequestMethod.GET)
	public ModelAndView search_comd_and_contUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("search_comd_and_contUrl", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		return new ModelAndView("Search_comd_and_contTiles");
	}

	@RequestMapping(value = "/GetSearch_Comd_and_cont_count", method = RequestMethod.POST)
	public @ResponseBody long GetSearch_Comd_and_cont_count(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return unitpro.GetSearch_comd_and_cont_count(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, unit_sus_no, status);
	}

	@RequestMapping(value = "/GetSearch_Comd_and_cont_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetSearch_Com_letterdata(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no,
			String status) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return unitpro.GetSearch_comd_and_cont_list(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, unit_sus_no, status);
	}

	@RequestMapping(value = "/searchDistributeComdAndCont", method = RequestMethod.GET)
	public ModelAndView searchDistributeComdAndCont(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchDistributeComdAndCont", roleid);
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		/*if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}*/

		Mmap.put("getPrantArmList", allOrbat.getPrantArmList(session));
		Mmap.put("getCommandList", allOrbat.getCommandDetailsList());
		Mmap.put("roleType", roleType);
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		return new ModelAndView("searchDistributeComdAndContTiles");
	}

	@RequestMapping(value = "/admin/getDistributeComdAndContDtl", method = RequestMethod.POST)
	public ModelAndView getDistributeComdAndContDtl(HttpSession sessionA, HttpServletRequest request,
			@ModelAttribute("sus_no2") String sus_no, @ModelAttribute("unit_name2") String unit_name,
			@ModelAttribute("issue_date2") String issue_date, @ModelAttribute("command_name2") String command_name,
			@ModelAttribute("to_date2") String to_date, @ModelAttribute("parent_arm2") String parent_arm,
			@ModelAttribute("status2") String status, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchDistributeComdAndCont", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String fdt = request.getParameter("issue_date2");
		String tdt = request.getParameter("to_date2");

		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				model.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:searchDistributeComdAndCont");
			}
		}
		if (!sus_no.equals("") && sus_no.length() == 8) {
			model.put("sus_no", sus_no);
			model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		} else if (sus_no != "" & sus_no != null & !sus_no.equals("") & !sus_no.equals(null)
				& valid.sus_noLength(sus_no) == false) {
			model.put("msg", valid.sus_noMSG);
			return new ModelAndView("redirect:searchDistributeComdAndCont");
		}

		if (valid.checkUnit_nameLength(unit_name) == false) {
			model.put("msg", valid.unit_nameMSG);
			return new ModelAndView("redirect:searchDistributeComdAndCont");
		}

		model.put("getPrantArmList", allOrbat.getPrantArmList(sessionA));
		model.put("getCommandList", allOrbat.getCommandDetailsList());
		model.put("issue_date", issue_date);
		model.put("parent_arm", parent_arm);
		if (status != "") {
			model.put("status", status);
		}
		if (!issue_date.equals("") && to_date.equals("")) {
			model.put("to_date", to_date);
		}
		ArrayList<ArrayList<String>> list = captureDAO.SearComdAndContInstDtlForDistribution(status, issue_date,
				to_date, parent_arm, sus_no, command_name, sessionA);
		String sendButton = "<a class='fa fa-send-o' onclick='return send_action();'>Send</a>";
		String rejectButton = "<a class='action_icons action_reject'  onclick='return reject_action();'>Reject</a>";

		if (status.equals("0")) {

			if (roleAccess.equals("MISO") && roleType.equals("APP")) {
				model.put("sendButton", sendButton);
				model.put("rejectButton", rejectButton);
			}

		}
		model.put("list", list);
		model.put("status", status);
		return new ModelAndView("searchDistributeComdAndContTiles");
	}

	@RequestMapping(value = "/getComdAndContInstDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getComdAndContInstDetails(HttpServletRequest request,
			HttpSession session, int id) {
		String username = session.getAttribute("username").toString();
		return captureDAO.getComdAndContInstDetails(id);
	}

	@RequestMapping(value = "/getSdMoveDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSdMoveDetails(HttpServletRequest request, HttpSession session,
			String sus_no) {
		return captureDAO.getSdMoveDtl(sus_no);
	}

	@RequestMapping(value = "/admin/distributeComdAndContAction", method = RequestMethod.POST)
	public @ResponseBody String distributeComdAndContAction(@RequestParam String selectedData,
			@RequestParam(required = false) String checkboxIds, @RequestParam(required = false) String statusValues) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String, String>> dataList = mapper.readValue(selectedData,
					new TypeReference<List<Map<String, String>>>() {
					});

			for (Map<String, String> item : dataList) {
				int id = Integer.parseInt(item.get("value"));
				String dropdownValue = item.get("dropdownValue");

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String status = "sd9_status";
				if (dropdownValue.equals("MO")) {
					status = "mo_status";
				} else if (dropdownValue.equals("SD4")) {
					status = "sd4_status";
				} else if (dropdownValue.equals("SD5")) {
					status = "sd5_status";
				}

				int app = 0;
				String hqlUpdate = "update  TB_MISO_ORBAT_COMD_CONT C set " + status
						+ "=:status ,distribute_date=:distribute_date,sd9_distribute_status=:sd9_distribute_status,current_status=:current_status where id=:id";
				app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 0).setDate("distribute_date", new Date())
						.setParameter("id", id).setString("current_status", "" + dropdownValue + " Pending")
						.setInteger("sd9_distribute_status", 1).executeUpdate();
				tx.commit();
				sessionHQL.close();

			}

			return "Data Approved Successfully";
		} catch (JsonParseException e) {
			return "Error: Invalid JSON data received. Please contact support.";
		} catch (IOException e) {
			return "Error: An error occurred while processing your request. Please try again later.";
		} catch (Exception e) {
			return "Error: An unexpected error occurred. Please contact support.";
		}
	}

	@RequestMapping(value = "/EditComdAndContInstUrl")
	public ModelAndView EditComdAndContInstUrl(@ModelAttribute("id2") BigInteger updateid,
			@ModelAttribute("status6") String status, @ModelAttribute("unit_sus_no6") String unit_sus_no, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_comd_and_contUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("getArmNameList", allOrbat.getArmNameList());
		Mmap.put("getCommandList", allOrbat.getCommandDetailsList());
		Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		Mmap.put("getlocList", allOrbat.getLOCList());
		Mmap.put("editId", updateid);
		return new ModelAndView("mil_co_unit_detailsTiles");
	}

	/*@RequestMapping(value = "/ApproveComdAndCont", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ApproveComdAndCont(@ModelAttribute("id1") Integer id1, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "sus_no1", required = false) String sus_no, Authentication authentication) {
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleName = sessionA.getAttribute("role").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String username = sessionA.getAttribute("username").toString();

		String Status = "cmd_status";
		String current_status = "SD9 Distribution is Pending.";
		String approve_by = "cmd_approve_by";
		String approved_date = "cmd_approved_date";
		if (roleName.equals("comd_cont_app_sd9")) {
			Status = "sd9_status";
			current_status = "Approve";
			approve_by = "sd9_approve_by";
			approved_date = "sd9_approved_date";
		} else if (roleName.equals("comd_cont_app_mo")) {
			Status = "mo_status";
			approve_by = "mo_approve_by";
			approved_date = "mo_approved_date";
		} else if (roleName.equals("o_sd4_app")) {
			Status = "sd4_status";
			approve_by = "mo_approve_by";
			approved_date = "sd4_approved_date";
		} else if (roleName.equals("comd_cont_app_sd5")) {
			Status = "sd5_status";
			approve_by = "mo_approve_by";
			approved_date = "sd5_approved_date";
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int app = 0;
		String hqlUpdate = "update  TB_MISO_ORBAT_COMD_CONT C set " + Status
				+ "=:status,current_status=:current_status," + approve_by + "=:approve_by," + approved_date
				+ "=:approved_date,sd9_distribute_status=:sd9_distribute_status  where id=:id";
		app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("current_status", current_status)
				.setString("approve_by", username).setDate("approved_date", new Date())
				.setInteger("sd9_distribute_status", 0).setInteger("id", id1).executeUpdate();
		tx.commit();
		sessionHQL.close();

		if (app > 0) {
			msg = "Approved Successfully.";
		} else {
			msg = "Approved Not Successfully.";

		}
		model.put("msg", msg);
		model.put("status", status);
		model.put("unit_sus_no", sus_no);

		return new ModelAndView("Search_comd_and_contTiles");
	}*/
	
	@RequestMapping(value = "/ApproveComdAndCont", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ApproveComdAndCont(@ModelAttribute("id1") Integer id1, BindingResult result,
	HttpServletRequest request, HttpSession sessionA, ModelMap model,
	@RequestParam(value = "msg", required = false) String msg,
	@RequestParam(value = "status1", required = false) String status,
	@RequestParam(value = "sus_no1", required = false) String sus_no, Authentication authentication) {
	String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	String roleName = sessionA.getAttribute("role").toString();
	String roleType = sessionA.getAttribute("roleType").toString();
	String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
	String username = sessionA.getAttribute("username").toString();

	String Status = "cmd_status";
	String current_status = "SD9 Distribution is Pending.";
	String approve_by = "cmd_approve_by";
	String approved_date = "cmd_approved_date";

	if (roleName.equals("comd_cont_app_sd9")) {
	   Status = "sd9_status";
	   current_status = "Approve";
	   approve_by = "sd9_approve_by";
	   approved_date = "sd9_approved_date";
	   current_status = "SD9 Approved.";
	} else if (roleName.equals("comd_cont_app_mo")) {
	   Status = "mo_status";
	   approve_by = "mo_approve_by";
	   approved_date = "mo_approved_date";
	   current_status = "SD9 Approval is Pending.";
	} else if (roleName.equals("o_sd4_app")) {
	   Status = "sd4_status";
	   approve_by = "mo_approve_by";
	   approved_date = "sd4_approved_date";
	   current_status = "SD9 Approval is Pending.";
	} else if (roleName.equals("comd_cont_app_sd5")) {
	   Status = "sd5_status";
	   approve_by = "mo_approve_by";
	   approved_date = "sd5_approved_date";
	   current_status = "SD9 Approval is Pending.";
	}

	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	int app = 0;


	String hqlUpdate = "update TB_MISO_ORBAT_COMD_CONT C set "
	       + Status + "=:status, current_status=:current_status, "
	       + approve_by + "=:approve_by, "
	       + approved_date + "=:approved_date";

	if (roleSubAccess.equals("Command")) {
	   hqlUpdate += ", sd9_distribute_status =:sd9_distribute_status";
	}

	if (!roleName.equals("comd_cont_app_sd9") && !roleSubAccess.equals("Command")) {
	   hqlUpdate += ", sd9_status=:sd9_status";
	}

	hqlUpdate += " where id=:id";

	Query query = sessionHQL.createQuery(hqlUpdate)
	   .setInteger("status", 1)
	   .setString("current_status", current_status)
	   .setString("approve_by", username)
	   .setDate("approved_date", new Date())
	   .setInteger("id", id1);



	if (roleSubAccess.equals("Command")) {
	   query.setInteger("sd9_distribute_status", 0);
	}

	if (!roleName.equals("comd_cont_app_sd9") && !roleSubAccess.equals("Command")) {
	   query.setInteger("sd9_status", 0);
	}

	app = query.executeUpdate();
	tx.commit();
	sessionHQL.close();

	if (app > 0) {
	   msg = "Approved Successfully.";
	} else {
	   msg = "Approved Not Successfully.";
	}

	model.put("msg", msg);
	model.put("status", status);
	model.put("unit_sus_no", sus_no);

	return new ModelAndView("Search_comd_and_contTiles");
	}

	@RequestMapping(value = "/rejectComdAndContInstAction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView rejectComdAndContInstAction(@ModelAttribute("id4") BigInteger id,
			BindingResult result, HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarks4", required = false) String reject_remarks,
			Authentication authentication) {

		Boolean val = roledao.ScreenRedirect("search_comd_and_contUrl", sessionA.getAttribute("roleid").toString());
		String redirect = "Search_comd_and_contTiles";

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();

		String roleName = sessionA.getAttribute("role").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String username = sessionA.getAttribute("username").toString();

		   String status, current_status;
		   String cmd_status = "";
		    switch (roleName) {
		        case "comd_cont_app_sd9":
		            status = "sd9_status";
		            current_status = "sd9 reject.";
		            cmd_status="cmd_status=3,";
		            break;
		        case "comd_cont_app_mo":
		            status = "mo_status";
		            current_status = "mo reject.";
		            cmd_status="cmd_status=3,";
		            break;
		        case "o_sd4_app":
		            status = "sd4_status";
		            current_status = "sd4 reject.";
		            cmd_status="cmd_status=3,";
		            break;
		        case "comd_cont_app_sd5":
		            status = "sd5_status";
		            current_status = "sd5 reject.";
		            cmd_status="cmd_status=3,";
		           
		            break;
		        default:
		            status = "cmd_status";
		            current_status = "cmd reject.";
		    }
		// try {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = "update TB_MISO_ORBAT_COMD_CONT set " + status + "=:status,"+cmd_status+" remarks=:reject_remarks,reject_by=:reject_by,rejected_date=:reject_date,sd9_distribute_status=:sd9_distribute_status,current_status=:current_status  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks)
				.setString("current_status", current_status).setString("reject_by", username)
				.setInteger("sd9_distribute_status", -1).setDate("reject_date", new Date()).setBigInteger("id", id)
				.executeUpdate();

		if (app > 0) {
			liststr.add("Data Rejected Successfully.");
		} else {
			liststr.add("Data not Rejected");
		}

		model.put("msg", liststr.get(0));
		model.put("status", 0);
		tx.commit();
		sessionHQL.close();
		return new ModelAndView(redirect);
	}

	@RequestMapping(value = "/admin/rejectDisributeComdAndContInstAction", method = RequestMethod.POST)
	public @ResponseBody String rejectDisributeComdAndContInstAction(@RequestParam String selectedData,
			@RequestParam(required = false) String checkboxIds, @RequestParam(required = false) String statusValues) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String, String>> dataList = mapper.readValue(selectedData,
					new TypeReference<List<Map<String, String>>>() {
					});

			for (Map<String, String> item : dataList) {
				int id = Integer.parseInt(item.get("value"));
				String dropdownValue = item.get("dropdownValue");

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String status = "sd9_status";
				if (dropdownValue.equals("MO")) {
					status = "mo_status";
				} else if (dropdownValue.equals("SD4")) {
					status = "sd4_status";
				} else if (dropdownValue.equals("SD5")) {
					status = "sd5_status";
				}

				int app = 0;
				String hqlUpdate = "update  TB_MISO_ORBAT_COMD_CONT C set " + status
						+ "=:status, cmd_status=:cmd_status,distribute_date=:distribute_date,sd9_distribute_status=:sd9_distribute_status,current_status=:current_status where id=:id";
				app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setInteger("cmd_status", 3)
						.setDate("distribute_date", new Date()).setParameter("id", id)
						.setInteger("sd9_distribute_status", -1).setString("current_status", "Reject By SD9")
						.executeUpdate();
				tx.commit();
				sessionHQL.close();

			}

			return "Data Rejected Successfully";
		} catch (JsonParseException e) {
			return "Error: Invalid JSON data received. Please contact support.";
		} catch (IOException e) {
			return "Error: An error occurred while processing your request. Please try again later.";
		} catch (Exception e) {
			return "Error: An unexpected error occurred. Please contact support.";
		}
	}

}
