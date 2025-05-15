package com.controller.Dashboard;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserofficerReportView;
import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.ExportFile.Excel_To_Export_2_Table_Report;
import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.Dashboard.JCO_ORDashboardDAO;
import com.dao.Dashboard.PSGDashboardDAO;
import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.AllMethodsDAOImp;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.Role;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class JcoDashboardController {
	// @Autowired
	// IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;
	//
	// @Autowired
	// AllMethodsDAOImp all;
	//
	// @Autowired
	// private RoleBaseMenuDAO roledao;
	//
	// public String pageType = "";
	// public String pageTypeU = "";

	@Autowired
	JCO_ORDashboardDAO adminDash;
	Psg_CommonController p_comm = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	//
	// Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	// RoleController ab = new RoleController();
	psg_jco_CommonController jcom = new psg_jco_CommonController();

	//// bisag v2 190922(new screen)
	@RequestMapping(value = "/admin/jcoDashboard", method = RequestMethod.GET)
	public ModelAndView jcoDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		// String roleid = session.getAttribute("roleid").toString();
		// Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		// if (val == false) {
		// return new ModelAndView("AccessTiles");
		// }
		// if (request.getHeader("Referer") == null) {
		// msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
		// }
		Mmap.put("msg", msg);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				List<Tbl_CodesForm> corps = getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
				/*
				 * ArrayList<ArrayList<String>> list_serv =
				 * held.GetdaoSearch_Report_jco_holding1111("","",formation_code,"","","","","",
				 * sessionA); Mmap.put("list_serv", list_serv);
				 */
			}
			if (roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				List<Tbl_CodesForm> Bn = getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
				/*
				 * ArrayList<ArrayList<String>> list_serv =
				 * held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",
				 * sessionA); Mmap.put("list_serv", list_serv);
				 */
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = getFormationList("Division", div);
				Mmap.put("getDivList", Bn);

				List<Tbl_CodesForm> bde = getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);
				/*
				 * ArrayList<ArrayList<String>> list_serv =
				 * held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",
				 * sessionA); Mmap.put("list_serv", list_serv);
				 */
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = getFormationList("Division", div);
				Mmap.put("getDivList", Bn);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);
				/*
				 * ArrayList<ArrayList<String>> list_serv =
				 * held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"",""
				 * ,sessionA); Mmap.put("list_serv", list_serv);
				 */
			}
		}
		if (roleAccess.equals("Unit")) {
			// String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("unit_sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd = getFormationList("Command", command);
			Mmap.put("getCommandList", comd);

			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps = getFormationList("Corps", cor);
			Mmap.put("getCorpsList", corps);

			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
					+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn = getFormationList("Division", div);
			Mmap.put("getDivList", Bn);

			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = getFormationList("Brigade", bde_code);
			Mmap.put("getBdeList", bde);
			/*
			 * ArrayList<ArrayList<String>> list_serv =
			 * held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"",""
			 * ,sessionA); Mmap.put("list_serv", list_serv);
			 */
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd = m.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value=''>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
		}

		Mmap.put("Getrk_heldlist_jco_or",
				adminDash.Getrk_heldlist_jco_or("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		// Mmap.put("Getrk_state_armtlist_jco_or",adminDash.Getrk_state_armtlist_jco_or("",
		// "", "", "", "", "", "", "", "", "", "", "","",""));
		// Mmap.put("Getrk_loclist_jco_or", adminDash.Getrk_loclist_jco_or("", "", "",
		// "", "", "", "", "", "", "", "", "","",""));
		// Mmap.put("Getrk_userarmlist_jco_or", adminDash.Getrk_userarmlist_jco_or("",
		// "", "", "", "", "", "", "", "", "", "", "","",""));

		Mmap.put("getTypeofRankList_jCO", jcom.getRankjcoList());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("getUserarm_codeList", p_comm.getUserarm_code_dasboardList());
		Mmap.put("addedit", "0");
		// Mmap.put("list_no_jco", adminDash.Getcount_no_Jco_OrData());
		// Mmap.put("list_no_jco_unit",adminDash.Getcount_no_unitJcoData());

		return new ModelAndView("jcoDashboardTiles");
	}

	// ----------------------------------------------------------------------------------------

	@RequestMapping(value = "/Getcount_no_Jco_OrData", method = RequestMethod.POST)
	public @ResponseBody int Getcount_no_Jco_OrData() {
		int list_nom = adminDash.Getcount_no_Jco_OrData();
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_no_unitJco_OrData", method = RequestMethod.POST)
	public @ResponseBody int Getcount_no_unitJco_OrData() {
		int list_nom = adminDash.Getcount_no_unitJcoData();
		return list_nom;
	}

	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy, String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;

		if (level_in_hierarchy.equals("Command")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);

		}
		if (level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}
		if (level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}
		if (level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}

		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	//
	@RequestMapping(value = "/admin/jco_or_dashboard_datalist", method = RequestMethod.POST)
	public ModelAndView jco_or_dashboard_datalist(@ModelAttribute("cont_comd1") String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
			String div1, String corps1, String regs1, String unit1, String parent_arm1, String unit_name1,
			String unit_view, ModelMap Mmap, HttpSession session) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		// Mmap.put("Getrk_medcatlist_jco_or",
		// adminDash.Getrk_medcatlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1,
		// arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_regimentlist_jco_or",
		// adminDash.Getrk_regimentlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1,
		// rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_parent_armtlist_jco_or",
		// adminDash.Getrk_parent_armtlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1,
		// rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_state_armtlist_jco_or",
		// adminDash.Getrk_state_armtlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1,
		// rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_blood_grouplist_jco_or",
		// adminDash.Getrk_blood_grouplist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1,
		// rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_commandlist_jco_or",
		// adminDash.Getrk_commandlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1,
		// arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_loclist_jco_or", adminDash.Getrk_loclist_jco_or(cont_comd1,
		// cont_corps1, cont_div1, cont_bde1, rank1, arm1,
		// parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_mothertonguelist_jco_or",
		// adminDash.Getrk_mothertonguelist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_userarmlist_jco_or",
		// adminDash.Getrk_userarmlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1,
		// arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_religionlist_jco_or",
		// adminDash.Getrk_religionlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1,
		// rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_genderlist_jco_or",
		// adminDash.Getrk_genderlist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1,
		// arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getrk_marital_statuslist_jco_or",
		// adminDash.Getrk_marital_statuslist_jco_or(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1,
		// regs1,parent_arm1,unit_name1));

		// Mmap.put("Getrk_doslist_jco_or", adminDash.Getrk_doslist_jco_or(cont_comd1,
		// cont_corps1, cont_div1, cont_bde1, rank1, arm1,
		// parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		// Mmap.put("Getcount_no_unitData_para",
		// adminDash.Getcount_no_unitData_para(cont_comd1, cont_corps1, cont_div1,
		// cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1));
		Mmap.put("Getrk_heldlist_jco_or", adminDash.Getrk_heldlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1,
				rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1));

		ArrayList<ArrayList<String>> list = adminDash.Getcount_no_unitData_para(cont_comd1, cont_corps1, cont_div1,
				cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1, unit_name1);
		Mmap.put("listunit", list);
		ArrayList<ArrayList<String>> list1 = adminDash.Getcount_no_offData_para(cont_comd1, cont_corps1, cont_div1,
				cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1, unit_name1);
		Mmap.put("listoff", list1);

		List<Tbl_CodesForm> comd = m.getCommandDetailsList();
		Mmap.put("getCommandList", comd);
		Mmap.put("cont_comd1", cont_comd1);
		Mmap.put("getTypeofRankList_jCO", jcom.getRankjcoList());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("getUserarm_codeList", p_comm.getUserarm_code_dasboardList());

		// if you want to selected data after edit mode

		Mmap.put("addedit", "1");
		List<String> selectedGetTypeofRankList = new ArrayList<>();
		List<String> selectedGetUserarm_codeList = new ArrayList<>();
		List<String> selectedGetParentArmList = new ArrayList<>();
		String selectedStringGetParentArmList = null;
		List<String> selectedGetCommandList = new ArrayList<>();
		List<String> selectedCorpsList = new ArrayList<>();
		List<String> selectedDivsList = new ArrayList<>();
		List<String> selectedBdesList = new ArrayList<>();
		List<String> selectedRegsList = new ArrayList<>();
		String selectedStringRegsList = null;

		if (rank1 != "") {
			selectedGetTypeofRankList = Arrays.asList(rank1.split(","));
		}
		if (arm1 != "") {
			selectedGetUserarm_codeList = Arrays.asList(arm1.split(","));

		}
		if (parm1 != "") {
			selectedGetParentArmList = Arrays.asList(parm1.split(","));

			String result = selectedGetParentArmList.stream().map(reg -> "\"" + reg + "\"")
					.collect(Collectors.joining(","));

			selectedStringGetParentArmList = "[" + result + "]";

		}
		if (cmd1 != "") {
			selectedGetCommandList = Arrays.asList(cmd1.split("|"));
		}
		if (corps1 != "") {
			selectedCorpsList = Arrays.asList(corps1.split(","));
		}
		if (div1 != "") {
			selectedDivsList = Arrays.asList(div1.split(","));
		}
		if (bdes1 != "") {
			selectedBdesList = Arrays.asList(bdes1.split(","));
		}
		if (regs1 != "") {
			selectedRegsList = Arrays.asList(regs1.split(","));
			String result = selectedRegsList.stream().map(reg -> "\"" + reg + "\"").collect(Collectors.joining(","));

			selectedStringRegsList = "[" + result + "]";

		}
		String selectedUnit_name = "";
		if (unit1 != "") {
			selectedUnit_name = unit1;
		}

		Mmap.put("selectedGetTypeofRankList", selectedGetTypeofRankList);
		Mmap.put("selectedGetUserarm_codeList", selectedGetUserarm_codeList);
		Mmap.put("selectedGetParentArmList", selectedGetParentArmList);
		Mmap.put("selectedGetCommandList", selectedGetCommandList);
		Mmap.put("selectedCorpsList", selectedCorpsList);
		Mmap.put("selectedDivsList", selectedDivsList);
		Mmap.put("selectedBdesList", selectedBdesList);
		Mmap.put("selectedRegsList", selectedRegsList);
		Mmap.put("selectedStringRegsList", selectedStringRegsList);
		Mmap.put("selectedStringGetParentArmList", selectedStringGetParentArmList);
		Mmap.put("selectedUnit_name", selectedUnit_name);
		Mmap.put("corps", corps1);
		Mmap.put("div1", div1);
		Mmap.put("bdes", bdes1);
		if (unit_view.equals("YES")) {
			Mmap.put("unit_view", "YES");
		} else {
			Mmap.put("unit_view", "No");
		}
		return new ModelAndView("jcoDashboardTiles", Mmap);
	}

	public List<UserLogin> getRoleNameList_dash() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from UserLogin order by userName");
			@SuppressWarnings("unchecked")
			List<UserLogin> list = (List<UserLogin>) q.list();
			tx.commit();
			session.close();
			return list;
		} catch (Exception e) {
			session.close();
			return null;
		}
	}

	public List<Role> getRoleNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Role order by role");
			@SuppressWarnings("unchecked")
			List<Role> list = (List<Role>) q.list();
			tx.commit();
			session.close();
			return list;
		} catch (Exception e) {
			session.close();
			return null;
		}
	}

	@RequestMapping(value = "/admin/Formationwisejco", method = RequestMethod.GET)
	public ModelAndView Formationwisejco(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)

	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("FormationwisejcoTiles");
	}

	@RequestMapping(value = "/data_table_jco", method = RequestMethod.POST)
	public @ResponseBody ModelAndView data_table_jco(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "cont_comd40", required = false) String cont_comd4,
			@RequestParam(value = "cont_corps40", required = false) String cont_corps4,
			@RequestParam(value = "cont_div40", required = false) String cont_div4,
			@RequestParam(value = "cont_bde40", required = false) String cont_bde4,
			@RequestParam(value = "rank40", required = false) String rank4,
			@RequestParam(value = "arm40", required = false) String arm4,
			@RequestParam(value = "parm40", required = false) String parm4,
			@RequestParam(value = "cmd40", required = false) String cmd4,
			@RequestParam(value = "bdes40", required = false) String bdes4,
			@RequestParam(value = "div40", required = false) String div4,
			@RequestParam(value = "corps40", required = false) String corps4,
			@RequestParam(value = "regs40", required = false) String regs4,
			@RequestParam(value = "unit40", required = false) String unit4,
			@RequestParam(value = "parent_arm40", required = false) String parent_arm4,
			@RequestParam(value = "unit_name40", required = false) String unit_name4, HttpServletRequest request) {

		Mmap.addAttribute("cont_comd4", cont_comd4);
		Mmap.addAttribute("cont_corps4", cont_corps4);
		Mmap.addAttribute("cont_div4", cont_div4);
		Mmap.addAttribute("cont_bde4", cont_bde4);
		Mmap.addAttribute("rank4", rank4);
		Mmap.addAttribute("arm4", arm4);
		Mmap.addAttribute("parm4", parm4);
		Mmap.addAttribute("cmd4", cmd4);
		Mmap.addAttribute("bdes4", bdes4);
		Mmap.addAttribute("div4", div4);
		Mmap.addAttribute("corps4", corps4);
		Mmap.addAttribute("regs4", regs4);
		Mmap.addAttribute("unit4", unit4);
		Mmap.addAttribute("parent_arm4", parent_arm4);
		Mmap.addAttribute("unit_name4", unit_name4);
		return new ModelAndView("FormationwisejcoTiles");
	}

	@RequestMapping(value = "/getSearchtabledata_jco_count", method = RequestMethod.POST)
	public @ResponseBody int getSearchtabledata_jco_count(
			@RequestParam(value = "cont_comd4", required = false) String cont_comd4,
			@RequestParam(value = "cont_corps4", required = false) String cont_corps4,
			@RequestParam(value = "cont_div4", required = false) String cont_div4,
			@RequestParam(value = "cont_bde4", required = false) String cont_bde4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "arm4", required = false) String arm4,
			@RequestParam(value = "parm4", required = false) String parm4,
			@RequestParam(value = "cmd4", required = false) String cmd4,
			@RequestParam(value = "bdes4", required = false) String bdes4,
			@RequestParam(value = "div4", required = false) String div4,
			@RequestParam(value = "corps4", required = false) String corps4,
			@RequestParam(value = "regs4", required = false) String regs4,
			@RequestParam(value = "unit4", required = false) String unit4,
			@RequestParam(value = "parent_arm4", required = false) String parent_arm4,
			@RequestParam(value = "unit_name4", required = false) String unit_name4,
			@RequestParam(value = "Search", required = false) String Search, HttpServletRequest request) {

		int list = adminDash.FindJCOcount(Search, cont_comd4, cont_corps4, cont_div4, cont_bde4, rank4, arm4, parm4,
				cmd4, div4, corps4, bdes4, regs4, parent_arm4, unit_name4);

		return list;
	}

	@RequestMapping(value = "/getSearchtabledata_jco", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getSearchtabledata_jco(
			@RequestParam(value = "cont_comd4", required = false) String cont_comd4,
			@RequestParam(value = "cont_corps4", required = false) String cont_corps4,
			@RequestParam(value = "cont_div4", required = false) String cont_div4,
			@RequestParam(value = "cont_bde4", required = false) String cont_bde4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "arm4", required = false) String arm4,
			@RequestParam(value = "parm4", required = false) String parm4,
			@RequestParam(value = "cmd4", required = false) String cmd4,
			@RequestParam(value = "bdes4", required = false) String bdes4,
			@RequestParam(value = "div4", required = false) String div4,
			@RequestParam(value = "corps4", required = false) String corps4,
			@RequestParam(value = "regs4", required = false) String regs4,
			@RequestParam(value = "unit4", required = false) String unit4,
			@RequestParam(value = "parent_arm4", required = false) String parent_arm4,
			@RequestParam(value = "unit_name4", required = false) String unit_name4,
			@RequestParam(value = "Search", required = false) String Search,
			@RequestParam(value = "startPage", required = false) String startPage,
			@RequestParam(value = "pageLength", required = false) String pageLength, HttpServletRequest request) {

		ArrayList<ArrayList<String>> list = adminDash.FindJCOtable(Integer.parseInt(startPage),
				Integer.parseInt(pageLength), Search, cont_comd4, cont_corps4, cont_div4, cont_bde4, rank4, arm4, parm4,
				cmd4, div4, corps4, bdes4, regs4, parent_arm4, unit_name4);

		return list;
	}

	@RequestMapping(value = "/data_table_unit_jco", method = RequestMethod.POST)
	public @ResponseBody ModelAndView data_table_unit_jco(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "cont_comd50", required = false) String cont_comd50,
			@RequestParam(value = "cont_corps50", required = false) String cont_corps50,
			@RequestParam(value = "cont_div50", required = false) String cont_div50,
			@RequestParam(value = "cont_bde50", required = false) String cont_bde50,
			@RequestParam(value = "rank50", required = false) String rank50,
			@RequestParam(value = "arm50", required = false) String arm50,
			@RequestParam(value = "parm50", required = false) String parm50,
			@RequestParam(value = "cmd50", required = false) String cmd50,
			@RequestParam(value = "bdes50", required = false) String bdes50,
			@RequestParam(value = "div50", required = false) String div50,
			@RequestParam(value = "corps50", required = false) String corps50,
			@RequestParam(value = "regs50", required = false) String regs50,
			@RequestParam(value = "unit50", required = false) String unit50,
			@RequestParam(value = "parent_arm50", required = false) String parent_arm50,
			@RequestParam(value = "unit_name50", required = false) String unit_name50, HttpServletRequest request) {

		Mmap.addAttribute("cont_comd50", cont_comd50);
		Mmap.addAttribute("cont_corps50", cont_corps50);
		Mmap.addAttribute("cont_div50", cont_div50);
		Mmap.addAttribute("cont_bde50", cont_bde50);
		Mmap.addAttribute("rank50", rank50);
		Mmap.addAttribute("arm50", arm50);
		Mmap.addAttribute("parm50", parm50);
		Mmap.addAttribute("cmd50", cmd50);
		Mmap.addAttribute("bdes50", bdes50);
		Mmap.addAttribute("div50", div50);
		Mmap.addAttribute("corps50", corps50);
		Mmap.addAttribute("regs50", regs50);
		Mmap.addAttribute("unit50", unit50);
		Mmap.addAttribute("parent_arm50", parent_arm50);
		Mmap.addAttribute("unit_name50", unit_name50);
		return new ModelAndView("UnitwisejcoTiles");
	}

	@RequestMapping(value = "/getSearchtabledata_jco__unit_count", method = RequestMethod.POST)
	public @ResponseBody int getSearchtabledata_jco__unit_count(
			@RequestParam(value = "cont_comd4", required = false) String cont_comd4,
			@RequestParam(value = "cont_corps4", required = false) String cont_corps4,
			@RequestParam(value = "cont_div4", required = false) String cont_div4,
			@RequestParam(value = "cont_bde4", required = false) String cont_bde4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "arm4", required = false) String arm4,
			@RequestParam(value = "parm4", required = false) String parm4,
			@RequestParam(value = "cmd4", required = false) String cmd4,
			@RequestParam(value = "bdes4", required = false) String bdes4,
			@RequestParam(value = "div4", required = false) String div4,
			@RequestParam(value = "corps4", required = false) String corps4,
			@RequestParam(value = "regs4", required = false) String regs4,
			@RequestParam(value = "unit4", required = false) String unit4,
			@RequestParam(value = "parent_arm4", required = false) String parent_arm4,
			@RequestParam(value = "unit_name4", required = false) String unit_name4,
			@RequestParam(value = "Search", required = false) String Search, HttpServletRequest request) {

		int list = adminDash.Findunittable_joc_count(Search, cont_comd4, cont_corps4, cont_div4, cont_bde4, rank4, arm4,
				parm4, cmd4, div4, corps4, bdes4, regs4, parent_arm4, unit_name4);

		return list;
	}

	@RequestMapping(value = "/getSearchtabledata_unit_jco", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getSearchtabledata_unit_jco(
			@RequestParam(value = "cont_comd4", required = false) String cont_comd4,
			@RequestParam(value = "cont_corps4", required = false) String cont_corps4,
			@RequestParam(value = "cont_div4", required = false) String cont_div4,
			@RequestParam(value = "cont_bde4", required = false) String cont_bde4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "arm4", required = false) String arm4,
			@RequestParam(value = "parm4", required = false) String parm4,
			@RequestParam(value = "cmd4", required = false) String cmd4,
			@RequestParam(value = "bdes4", required = false) String bdes4,
			@RequestParam(value = "div4", required = false) String div4,
			@RequestParam(value = "corps4", required = false) String corps4,
			@RequestParam(value = "regs4", required = false) String regs4,
			@RequestParam(value = "unit4", required = false) String unit4,
			@RequestParam(value = "parent_arm4", required = false) String parent_arm4,
			@RequestParam(value = "unit_name4", required = false) String unit_name4,
			@RequestParam(value = "Search", required = false) String Search,
			@RequestParam(value = "startPage", required = false) String startPage,
			@RequestParam(value = "pageLength", required = false) String pageLength, HttpServletRequest request) {

		ArrayList<ArrayList<String>> list = adminDash.Findunittable_jco(Integer.parseInt(startPage),
				Integer.parseInt(pageLength), Search, cont_comd4, cont_corps4, cont_div4, cont_bde4, rank4, arm4, parm4,
				cmd4, div4, corps4, bdes4, regs4, parent_arm4, unit_name4);

		return list;
	}

	@RequestMapping(value = "/admin/getformationwisejco", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<TB_CENSUS_JCO_OR_PARENT> getformationwisejco(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request)
			throws SQLException {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<TB_CENSUS_JCO_OR_PARENT> dataSet = adminDash.DatatablesCriteriasFormationWisejco(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/admin/JcounitDetail", method = RequestMethod.GET)
	public ModelAndView JcounitDetail(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)

	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("UnitwisejcoTiles");
	}

	@RequestMapping(value = "/admin/getjcowiseDetail", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<TB_CENSUS_JCO_OR_PARENT> getjcounit(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<TB_CENSUS_JCO_OR_PARENT> dataSet = adminDash.DatatablesCriteriasUnitWiseoff(criterias);

		return DatatablesResponse.build(dataSet, criterias);
	}

	@RequestMapping(value = "/Getrk_medcatlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_medcatlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_medcatlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);
		// String list = adminDash.Getrk_medcatlist_jco_or("", "", "", "", "", "", "",
		// "", "", "", "", "","","");

		return list;
	}

	@RequestMapping(value = "/Getrk_regimentlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_regimentlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_regimentlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_state_armtlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_state_armtlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_state_armtlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);
		return list;
	}

	@RequestMapping(value = "/Getrk_parent_armtlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_parent_armtlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_parent_armtlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_blood_grouplist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_blood_grouplist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_blood_grouplist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_commandlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_commandlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_commandlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_genderlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_genderlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_genderlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_doslist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_doslist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1, String corps1,
			String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_doslist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1, parm1,
				cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_mothertonguelist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_mothertonguelist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_mothertonguelist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_marital_statuslist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_marital_statuslist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_marital_statuslist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_userarmlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_userarmlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_userarmlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

	@RequestMapping(value = "/Getrk_religionlist", method = RequestMethod.POST)
	public @ResponseBody String Getrk_religionlist(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1, String div1,
			String corps1, String regs1, String unit1, String parent_arm1, String unit_name1) {

		String list = adminDash.Getrk_religionlist_jco_or(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
				parm1, cmd1, div1, corps1, bdes1, regs1, parent_arm1, unit_name1);

		return list;
	}

}
