package com.controller.Dashboard;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.Dashboard.CIV_DashboardDAO;
import com.dao.Dashboard.PSGDashboardDAO;
import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.AllMethodsDAOImp;
import com.models.Role;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CivDashboardController {
	@Autowired
	IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;

	@Autowired
	AllMethodsDAOImp all;

	@Autowired
	private RoleBaseMenuDAO roledao;

	public String pageType = "";
	public String pageTypeU = "";

	@Autowired
	PSGDashboardDAO adminDash;
	@Autowired
	CIV_DashboardDAO civDash;
	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	RoleController ab = new RoleController();

	//// bisag v2 190922(new screen)

	@RequestMapping(value = "/admin/civDashboard", method = RequestMethod.GET)
	public ModelAndView civDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) throws SQLException {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		// Mmap.put("Getrk_gazelist", adminDash.Getgaze_grouplist("", "", "", "", "",
		// "", "", "", "", "", "", "","",""));

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
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("Getrk_gen_regular",
				civDash.Getrk_gen_regular("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		Mmap.put("Getrk_gazelist", civDash.Getrk_gazelist("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		Mmap.put("Getrk_cmdlist", civDash.Getrk_cmdlist2("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		Mmap.put("msg", msg);

		return new ModelAndView("civDashboard", Mmap);
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

	@RequestMapping(value = "/admin/civ_dashboard_datalist", method = RequestMethod.POST)
	public ModelAndView civ_dashboard_datalist(@ModelAttribute("cont_comd1") String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
			String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1,String unit_view, ModelMap Mmap, HttpSession session) throws SQLException {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");



		Mmap.put("Getrk_cmdlist", civDash.Getrk_cmdlist2(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		Mmap.put("Getrk_gazelist", civDash.Getrk_gazelist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
		Mmap.put("Getrk_gen_regular", civDash.Getrk_gen_regular(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
	
		
		
		ArrayList<ArrayList<String>> list = adminDash.Getcount_no_unitData_para(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
		
		Mmap.put("listunit", list);
		ArrayList<ArrayList<String>> listunitformfilledunit1 = adminDash.Getcount_no_unitFilled_para(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
		ArrayList<ArrayList<String>> listformfilledoff1 = adminDash.Getcount_no_offFilled_para(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
		
		
		ArrayList<ArrayList<String>> list1 = adminDash.Getcount_no_offData_para(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
				arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
		Mmap.put("listunitformfilledunit", listunitformfilledunit1);
		Mmap.put("listformfilledoff", listformfilledoff1);
		Mmap.put("listoff", list1);
		
		// Mmap.put("cont_comd1", cont_comd1);

		List<Tbl_CodesForm> comd = m.getCommandDetailsList();
		Mmap.put("getCommandList", comd);
		Mmap.put("cont_comd1", cont_comd1);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("getUserarm_codeList", p_comm.getUserarm_code_dasboardList());
	
//if you want to selected data after edit mode

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
			selectedGetCommandList = Arrays.asList(cmd1.split(","));
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

//List<String> selectedGetTypeofRankList = new ArrayList<>();
//selectedGetTypeofRankList.add("1102"); 
//selectedGetTypeofRankList.add("1103");
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
		Mmap.put("getDesignationList", p_comm.getDesignationList());
		Mmap.put("corps", corps1);
		Mmap.put("div1", div1);
		Mmap.put("bdes", bdes1);
		if(unit_view.equals("YES")) {
			Mmap.put("unit_view", "YES");
		}else {
			Mmap.put("unit_view", "No");
		}
		return new ModelAndView("civDashboard", Mmap);
	}


}
