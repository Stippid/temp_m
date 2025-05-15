

package com.controller.Dashboard;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.Dashboard.PSGDashboardDAO;
import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
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
import com.models.View_TMS_BVeh_Command_Wise_TrnsportUE_UH;
import com.models.View_Table;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class PsgDashboardController {
	@Autowired
	IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	AllMethodsDAOImp all;

	@Autowired
	private RoleBaseMenuDAO roledao;

	public String pageType = "";
	public String pageTypeU = "";

	@Autowired
	PSGDashboardDAO adminDash;
	Psg_CommonController p_comm = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	RoleController ab = new RoleController();

	//// bisag v2 190922(new screen)
	@RequestMapping(value = "/admin/psgDashboard", method = RequestMethod.GET)
	public ModelAndView psgDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		// String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();

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

		/*
		 * String roleid = sessionA.getAttribute("roleid").toString(); Boolean val =
		 * roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */

		Mmap.put("msg", msg);

		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList_dash", getRoleNameList_dash());
		Mmap.put("getRoleNameList", getRoleNameList());
		Mmap.put("getUserarm_codeList", ab.getUserarm_codeList());

		return new ModelAndView("psgDashboardTiles");
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

	@RequestMapping(value = "/Excel_data_updated2", method = RequestMethod.POST)
	public ModelAndView Excel_data_updated2(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @ModelAttribute("cont_comd_ex1") String cont_comd,
			@ModelAttribute("cont_corps_ex1") String cont_corps, @ModelAttribute("cont_div_ex1") String cont_div,
			@ModelAttribute("cont_bde_ex1") String cont_bde, @ModelAttribute("cont_comd_txt1") String cont_comd_txt,
			@ModelAttribute("cont_corps_txt1") String cont_corps_txt,
			@ModelAttribute("cont_div_txt1") String cont_div_txt, @ModelAttribute("cont_bde_txt1") String cont_bde_txt,
			@ModelAttribute("unit_name_ex1") String unit_name, @ModelAttribute("sus_no_ex1") String sus_no,
			@ModelAttribute("user_role1") String user_role, @ModelAttribute("sub_quali1") String sub_quali,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> Excel = adminDash.search_jco_en_table(cont_comd, cont_corps, cont_div, cont_bde,
				unit_name, sus_no, user_role, sub_quali);

		List<String> TH = new ArrayList<String>();

		TH.add("Command Name");
		TH.add("Corps/Area Name");
		TH.add("Division name ");
		TH.add("Brigade  Name");
		TH.add("Unit Name");
		TH.add("Approved");
		TH.add("Pending");
		TH.add("TOTAL");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username, Excel), "userList",
				Excel);
	}

	@RequestMapping(value = "/Excel_data_updated3", method = RequestMethod.POST)
	public ModelAndView Excel_data_updated3(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("cont_comd_ex2") String cont_comd, @ModelAttribute("cont_corps_ex2") String cont_corps,
			@ModelAttribute("cont_div_ex2") String cont_div, @ModelAttribute("cont_bde_ex2") String cont_bde,
			@ModelAttribute("cont_comd_txt2") String cont_comd_txt,
			@ModelAttribute("cont_corps_txt2") String cont_corps_txt,
			@ModelAttribute("cont_div_txt2") String cont_div_txt, @ModelAttribute("cont_bde_txt2") String cont_bde_txt,
			@ModelAttribute("unit_name_ex2") String unit_name, @ModelAttribute("sus_no_ex2") String sus_no,
			@ModelAttribute("user_role2") String user_role, @ModelAttribute("sub_quali2") String sub_quali) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> Excel = adminDash.search_civilian_en_table(cont_comd, cont_corps, cont_div,
				cont_bde, unit_name, sus_no, user_role, sub_quali);
		List<String> TH = new ArrayList<String>();

		TH.add("Command Name");
		TH.add("Corps/Area Name");
		TH.add("Division name ");
		TH.add("Brigade  Name");
		TH.add("Unit Name");
		TH.add("Approved");
		TH.add("Pending");
		TH.add("TOTAL ");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username, Excel), "userList",
				Excel);
	}

	@RequestMapping(value = "/Excel_data_updated", method = RequestMethod.POST)
	public ModelAndView Excel_data_updated(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @ModelAttribute("cont_comd_ex") String cont_comd,
			@ModelAttribute("cont_corps_ex") String cont_corps, @ModelAttribute("cont_div_ex") String cont_div,
			@ModelAttribute("cont_bde_ex") String cont_bde, @ModelAttribute("cont_comd_txt") String cont_comd_txt,
			@ModelAttribute("cont_corps_txt") String cont_corps_txt,
			@ModelAttribute("cont_div_txt") String cont_div_txt, @ModelAttribute("cont_bde_txt") String cont_bde_txt,
			@ModelAttribute("unit_name_ex") String unit_name, @ModelAttribute("sus_no_ex") String sus_no,
			@ModelAttribute("user_role1") String user_role, @ModelAttribute("sub_quali") String sub_quali,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> Excel = adminDash.search_officer_en_tablecen(cont_comd, cont_corps, cont_div,
				cont_bde, unit_name, sus_no, user_role, sub_quali);

		List<String> TH = new ArrayList<String>();

		TH.add("Command Name");
		TH.add("Corps/Area Name");
		TH.add("Division name ");
		TH.add("Brigade  Name");
		TH.add("Unit Name");
		TH.add("Approved");
		TH.add("Pending");
		TH.add("TOTAL ");

		String Heading = "\nData Updated Report";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username, Excel), "userList",
				Excel);
	}

	/// officer
	@RequestMapping(value = "/Excel_data_updatedk", method = RequestMethod.POST)
	public ModelAndView Excel_data_updatedk(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @ModelAttribute("cont_comd_exk") String cont_comd,
			@ModelAttribute("cont_corps_exk") String cont_corps, @ModelAttribute("cont_div_exk") String cont_div,
			@ModelAttribute("cont_bde_exk") String cont_bde, @ModelAttribute("cont_comd_txtk") String cont_comd_txt,
			@ModelAttribute("cont_corps_txtk") String cont_corps_txt,
			@ModelAttribute("cont_div_txtk") String cont_div_txt, @ModelAttribute("cont_bde_txtk") String cont_bde_txt,
			@ModelAttribute("unit_name_exk") String unit_name, @ModelAttribute("sus_no_exk") String sus_no,
			@ModelAttribute("user_role1k") String user_role, @ModelAttribute("sub_qualik") String sub_quali,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> Excel = adminDash.search_officer_en_table(cont_comd, cont_corps, cont_div,
				cont_bde, unit_name, sus_no, user_role, sub_quali);

		List<String> TH = new ArrayList<String>();

		TH.add("Command Name");
		TH.add("Corps/Area Name");
		TH.add("Division name ");
		TH.add("Brigade  Name");
		TH.add("Unit Name");
		TH.add("Approved");
		TH.add("Pending");
		TH.add("TOTAL ");

		String Heading = "\nData Updated Report";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username, Excel), "userList",
				Excel);
	}

	//// bisag v2 200922(converted in data table )
	@RequestMapping(value = "/search_officer_en_tablecount", method = RequestMethod.POST)
	public @ResponseBody long search_officer_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no/* ,String user_role_id */, String from_date,
			String to_date, String user_role, String sub_quali) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return adminDash.getsearch_officer_en_tablecount(Search, orderColunm, orderType, sessionUserId, cont_comd,
				cont_corps, cont_div, cont_bde, unit_name, unit_sus_no/* , user_role_id */, from_date, to_date,
				user_role, sub_quali);
	}

	@RequestMapping(value = "/search_officer_en_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> search_officer_en_table(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd = fcode_comd;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_comd + cont_bde.substring(1, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_comd + cont_div.substring(1, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_comd + cont_corps.substring(1, 3);
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_comd;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps = fcode_corps;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_corps + cont_bde.substring(3, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_corps + cont_div.substring(3, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_corps;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_corps;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Division")) {
				String fcode_div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div = fcode_div;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_div + cont_bde.substring(6, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_div;
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_div;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_div;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Brigade")) {
				cont_bde = roleFormationNo;
			}
		}
		return adminDash.getsearch_officer_en_table(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no,
				/* user_role_id, */ from_date, to_date, user_role, sub_quali);
	}
	///// jco

////bisag v2 200922(converted in data table )
	@RequestMapping(value = "/search_jco_en_tablecount", method = RequestMethod.POST)
	public @ResponseBody long search_jco_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no, /* String user_role_id, */String from_date,
			String to_date, String user_role, String sub_quali) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return adminDash.getsearch_jco_en_tablecount(Search, orderColunm, orderType, sessionUserId, cont_comd,
				cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, /* user_role_id, */ from_date, to_date,
				user_role, sub_quali);
	}

	@RequestMapping(value = "/search_jco_en_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> search_jco_en_table(int startPage, String Search, int pageLength,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd = fcode_comd;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_comd + cont_bde.substring(1, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_comd + cont_div.substring(1, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_comd + cont_corps.substring(1, 3);
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_comd;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps = fcode_corps;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_corps + cont_bde.substring(3, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_corps + cont_div.substring(3, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_corps;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_corps;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Division")) {
				String fcode_div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div = fcode_div;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_div + cont_bde.substring(6, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_div;
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_div;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_div;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Brigade")) {
				cont_bde = roleFormationNo;
			}
		}
		return adminDash.getsearch_jco_en_table(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, /* user_role_id, */ from_date,
				to_date, user_role, sub_quali);
	}

	////// civ
////bisag v2 200922(converted in data table )
	@RequestMapping(value = "/search_civilian_en_tablecount", method = RequestMethod.POST)
	public @ResponseBody long search_civilian_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no, /* String user_role_id, */String from_date,
			String to_date, String user_role, String sub_quali) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return adminDash.getsearch_civilian_en_tablecount(Search, orderColunm, orderType, sessionUserId, cont_comd,
				cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, /* user_role_id, */ from_date, to_date,
				user_role, sub_quali);
	}

	@RequestMapping(value = "/search_civilian_en_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> search_civilian_en_table(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd = fcode_comd;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_comd + cont_bde.substring(1, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_comd + cont_div.substring(1, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_comd + cont_corps.substring(1, 3);
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_comd;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps = fcode_corps;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_corps + cont_bde.substring(3, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_corps + cont_div.substring(3, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_corps;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_corps;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Division")) {
				String fcode_div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div = fcode_div;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_div + cont_bde.substring(6, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_div;
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_div;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_div;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Brigade")) {
				cont_bde = roleFormationNo;
			}
		}
		return adminDash.getsearch_civilian_en_table(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no,
				/* user_role_id, */from_date, to_date, user_role, sub_quali);
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

////////////cmd

///BISAG V2 071122///(observation new screen)

	@RequestMapping(value = "/getpsg_comndSUS", method = RequestMethod.POST) // For Fetching Single Hirar. Name By
// SUS No
	public @ResponseBody List<String> getpsg_comndSUS(String FindWhat, String a, HttpSession s1) {

		List<String> list = adminDash.getpsgirarNameBySUS(FindWhat, a);
		if (list.size() != 0) {
			List<String> FList = adminDash.getpsgEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

//@RequestMapping(value = "/Excel_Record_Service_querymain", method = RequestMethod.POST)
//public ModelAndView Excel_Record_Service_querymain(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {
// 
// String roleid = session.getAttribute("roleid").toString();
//
// ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report1();
// 
// List<String> TH = new ArrayList<String>();
// TH.add("personnel_no");
// TH.add("name");
// TH.add("date_of_birth");
// TH.add("rank");
// TH.add("rank_type");
// TH.add("gender");                                
// TH.add("status");
// TH.add("date_of_rank");
// TH.add("parent_arm");                                
// TH.add("regiment");
// TH.add("shape");
// TH.add("date_of_medical");
// TH.add("med_classification_lmc");
// TH.add("cope");
// TH.add("age");
// TH.add("service");
// TH.add("dt_of_seniority");                                
// TH.add("date_of_commission");
// TH.add("unit_sus_no");
// TH.add("unit_name");                                
// TH.add("cmd_unit");
// TH.add("corp_unit");
// TH.add("div_unit");
// TH.add("bde_unit");
// TH.add("ct_part_i_ii");
// TH.add("peace_field");
// TH.add("unit_loc_type");
// TH.add("user_arm");                                
// TH.add("religion_name");
// TH.add("mothertounge");
// TH.add("blood_group");                                
// TH.add("nationality_name");
// TH.add("state_name");
// TH.add("marital_name");                                
// TH.add("marriage_date");
// TH.add("maiden_name");
// TH.add("spouse_personal_no");                                
// TH.add("spouse_service");
// TH.add("spouse_qualification");
// TH.add("child_name");                                
// TH.add("child_gender");
// TH.add("adoted");                                
// TH.add("child_disable");
// TH.add("cause_of_non_effective");
// TH.add("date_of_non_effective");                                
// TH.add("desertion_date");
// TH.add("arm_type");
// TH.add("border_area");                                
// TH.add("CAUSE_OF_DESERTION");
// TH.add("DATE_OF_DESERTOR_RECOVERED");
// 
// 
// 
// String Heading = "\nService Record ";
// String username = session.getAttribute("username").toString();
// return new ModelAndView(new ExcelUserofficerReportView("L", TH, username,Excel), "userList", Excel);
//} 

	@RequestMapping(value = "/Excel_Record_Service_querymain1", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain10(HttpServletRequest request, ModelMap model,
			HttpSession session, String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report1();

		List<String> TH = new ArrayList<String>();

		TH.add("personnel_no");
		TH.add("name");
		TH.add("date_of_birth");
		TH.add("rank");
		TH.add("rank_type");
		TH.add("gender");
		TH.add("STATUS");
		TH.add("date_of_rank");
		TH.add("parent_arm");
		TH.add("regiment");
		TH.add("shape");
		TH.add("date_of_medical");
		TH.add("med_classification_lmc");
		TH.add("cope");
		TH.add("AGE");
		TH.add("service");
		TH.add("dt_of_seniority");
		TH.add("date_of_commission");
		TH.add("unit_sus_no");
		TH.add("unit_name");
		TH.add("cmd_unit");
		TH.add("corp_unit");
		TH.add("div_unit");
		TH.add("bde_unit");
		TH.add("ct_part_i_ii");
		TH.add("peace_field");
		TH.add("unit_loc_type");
		TH.add("user_arm");
		TH.add("religion_name");
		TH.add("mothertounge");
		TH.add("blood_group");
		TH.add("nationality_name");
		TH.add("state_name");
		TH.add("marital_name");
		TH.add("marriage_date");
		TH.add("maiden_name");
		TH.add("spouse_personal_no");
		TH.add("spouse_service");
		TH.add("spouse_qualification");
		TH.add("child_name");
		TH.add("child_gender");
		TH.add("adoted");
		TH.add("child_disable");
		TH.add("cause_of_non_effective");
		TH.add("date_of_non_effective");
		TH.add("desertion_date");
		TH.add("arm_type");
		TH.add("border_area");
		TH.add("CAUSE_OF_DESERTION");
		TH.add("DATE_OF_DESERTOR_RECOVERED");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

/////////////////////////////////////////////////// Excel 2 //////////////////////////////////////////////////////

	@RequestMapping(value = "/Excel_Record_Service_querymain2", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain11(HttpServletRequest request, ModelMap model,
			HttpSession session, String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report2();

		List<String> TH = new ArrayList<String>();

		TH.add("ACA_Examination_Passed");
		TH.add("ACA_Degree_Acquired");
		TH.add("ACA_Specialization");
		TH.add("pro_Examination_Passed");
		TH.add("pro_Degree_Acquired");
		TH.add("pro_Specialization");
		TH.add("foreign_language");
		TH.add("Indian_language");
		TH.add("Country_Visited");
		TH.add("Purpose_of_Visit");
		TH.add("Course_Name");
		TH.add("Course_Name_Abbreviation");
		TH.add("Div");
		TH.add("Category");
		TH.add("Honours_Awards_Meda");
		TH.add("Date_of_Award");
		TH.add("Unit");
		TH.add("BDE");
		TH.add("Sub_Area");
		TH.add("Corps");
		TH.add("award_command");
		TH.add("Date_of_Casualty");
		TH.add("Time_of_Casualty");
		TH.add("Recommended");
		TH.add("Nature_of_Casualty");
		TH.add("onduty");
		TH.add("Name_of_Operation");
		TH.add("Sector");
		TH.add("field_area");
		TH.add("Whether_on");
		TH.add("battle_BDE");
		TH.add("battle_div");
		TH.add("battle_corps");
		TH.add("battle_Command");
		TH.add("battle_Cause_of_Casualty");
		TH.add("battle_Category_of_Casualty");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

//////////////////////////////////////////////////////Excel 3 ////////////////////////////////////////////////////////////

	@RequestMapping(value = "/Excel_Record_Service_querymain3", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain12(HttpServletRequest request, ModelMap model,
			HttpSession session, String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report3();

		List<String> TH = new ArrayList<String>();

		TH.add("PERSONNEL_NO");
		TH.add("STATUS");
		TH.add("rank");
		TH.add("DATE_RANK");
		TH.add("TRADE");
		TH.add("Name");
		TH.add("GENDER");
		TH.add("ARMS_SERVICES");
		TH.add("REGT");
		TH.add("MEDICAL_CATEGORY");
		TH.add("DATE_OF_MEDICAL");
		TH.add("MED_CLASSIFICATION_LMC");
		TH.add("cope");
		TH.add("DATE_OF_BIRTH");
		TH.add("DATE_OF_ENROLLMENT");
		TH.add("DATE_OF_ATTESTATION");
		TH.add("DATE_OF_SENIORITY");
		TH.add("UNIT_SUS_NO");
		TH.add("unit_name");
		TH.add("cmd_unit");
		TH.add("corp_unit");
		TH.add("div_unit");
		TH.add("bde_unit");
		TH.add("ct_part_i_ii");
		TH.add("unit_loc_type");
		TH.add("user_arm");
		TH.add("peace_field");
		TH.add("RELIGION");
		TH.add("mothertounge");
		TH.add("nationality_name");
		TH.add("blood_group");
		TH.add("COUNTRY");
		TH.add("state_name");
		TH.add("border_area");
		TH.add("marital_name");
		TH.add("adoted");
		TH.add("child_disable");
		TH.add("cause_of_non_effective");
		TH.add("date_of_non_effective");
		TH.add("arm_type");
		TH.add("desertion_date");
		TH.add("CAUSE_OF_DESERTION");
		TH.add("DATE_OF_DESERTOR_RECOVERED");
		TH.add("class_pay");
		TH.add("pay_group");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

/////////////////////////////////////////////////////Excel 4///////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/Excel_Record_Service_querymain4", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain13(HttpServletRequest request, ModelMap model,
			HttpSession session, String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report4();

		List<String> TH = new ArrayList<String>();

		TH.add("ACA_Degree_Acquired");
		TH.add("ACA_Specialization");
		TH.add("Indian_language");
		TH.add("foreign_language");
		TH.add("course_name");
		TH.add("course_abbreviation");
		TH.add("div");
		TH.add("Category");
		TH.add("Honours_Awards_Meda");
		TH.add("Date_of_Award");
		TH.add("Unit");
		TH.add("BDE");
		TH.add("Sub_Area");
		TH.add("Corps");
		TH.add("award_command");
		TH.add("Date_of_Casualty");
		TH.add("Time_of_Casualty");
		TH.add("Recommended");
		TH.add("Nature_of_Casualty");
		TH.add("onduty");
		TH.add("Name_of_Operation");
		TH.add("Sector");
		TH.add("field_area");
		TH.add("Whether_on");
		TH.add("battle_BDE");
		TH.add("battle_div");
		TH.add("battle_corps");
		TH.add("battle_Command");
		TH.add("battle_Cause_of_Casualty");
		TH.add("battle_Category_of_Casualty");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

/////////////////////////////////////////////////////////////Excel 5 /////////////////////////////////////////////////

	@RequestMapping(value = "/Excel_Record_Service_querymain5", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain5(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report5();

		List<String> TH = new ArrayList<String>();

		TH.add("employee_no");
		TH.add("STATUS");
		TH.add("name");
		TH.add("gender_name");
		TH.add("classification_services");
		TH.add("Group");
		TH.add("civillan_trade");
		TH.add("Type");
		TH.add("cadre");
		TH.add("service_Status");
		TH.add("category");
		TH.add("date_joining_gov_ser");
		TH.add("designation");
		TH.add("pay_level");
		TH.add("state_name");
		TH.add("sus_no");
		TH.add("unit_name");
		TH.add("cmd_unit");
		TH.add("corp_unit");
		TH.add("div_unit");
		TH.add("bde_unit");
		TH.add("user_arm");
		TH.add("ct_part_i_ii");
		TH.add("cause_of_non_effective");
		TH.add("date_of_non_effective");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

////////////////////////////////////////////////////////////////////// Excel 6////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/Excel_Record_Service_querymain6", method = RequestMethod.POST)
	public ModelAndView Excel_Record_Service_querymain6(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		ArrayList<ArrayList<String>> Excel = adminDash.Excel_Record_Service_Report6();

		List<String> TH = new ArrayList<String>();

		TH.add("employee_no");
		TH.add("STATUS");
		TH.add("name");
		TH.add("gender_name");
		TH.add("classification_services");
		TH.add("Group");
		TH.add("Type");
		TH.add("pay_head");
		TH.add("category");
		TH.add("date_joining_gov_ser");
		TH.add("state_name");
		TH.add("sus_no");
		TH.add("unit_name");
		TH.add("cmd_unit");
		TH.add("corp_unit");
		TH.add("div_unit");
		TH.add("bde_unit");
		TH.add("user_arm");
		TH.add("ct_part_i_ii");
		TH.add("cause_of_non_effective");
		TH.add("date_of_non_effective");

		String Heading = "\nService Record ";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserofficerReportView("L", TH, username, Excel), "userList", Excel);
	}

//prx
	@RequestMapping(value = "/search_officer_en_tablecen", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> search_officer_en_tablecen(int startPage, String Search,
			int pageLength, String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali)
			throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd = fcode_comd;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_comd + cont_bde.substring(1, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_comd + cont_div.substring(1, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_comd + cont_corps.substring(1, 3);
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_comd;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0))
						+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps = fcode_corps;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_corps + cont_bde.substring(3, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_corps + cont_div.substring(3, 6);
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_corps;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_corps;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Division")) {
				String fcode_div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div = fcode_div;

				if (!cont_bde.equals("0") && !cont_bde.equals("")) {
					cont_bde = fcode_div + cont_bde.substring(6, 10);
				} else {
					if (!cont_div.equals("0") && !cont_div.equals("")) {
						cont_div = fcode_div;
					} else {
						if (!cont_corps.equals("0") && !cont_corps.equals("")) {
							cont_corps = fcode_div;
						} else {
							if (!cont_comd.equals("-1") && !cont_comd.equals("")) {
								cont_comd = fcode_div;
							}
						}
					}
				}
			}
			if (roleSubAccess.equals("Brigade")) {
				cont_bde = roleFormationNo;
			}
		}
		return adminDash.getsearch_officer_en_table1(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no,
				/* user_role_id, */ from_date, to_date, user_role, sub_quali);
	}

	@RequestMapping(value = "/search_officer_en_tablecountcen", method = RequestMethod.POST)
	public @ResponseBody long search_officer_en_tablecountcen(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String msg, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no, /* String user_role_id, */String from_date,
			String to_date, String user_role, String sub_quali) throws SQLException {
		String roleType = sessionUserId.getAttribute("roleType").toString();

		return adminDash.getsearch_officer_en_tablecount1(Search, orderColunm, orderType, sessionUserId, cont_comd,
				cont_corps, cont_div, cont_bde, unit_name, unit_sus_no, /* user_role_id, */ from_date, to_date,
				user_role, sub_quali);
	}

	@RequestMapping(value = "/Excel_data_updated7", method = RequestMethod.POST)
	public ModelAndView Excel_data_updated7(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @ModelAttribute("cont_comd_ex7") String cont_comd,
			@ModelAttribute("cont_corps_ex7") String cont_corps, @ModelAttribute("cont_div_ex7") String cont_div,
			@ModelAttribute("cont_bde_ex7") String cont_bde, @ModelAttribute("cont_comd_txt7") String cont_comd_txt,
			@ModelAttribute("cont_corps_txt7") String cont_corps_txt,
			@ModelAttribute("cont_div_txt7") String cont_div_txt, @ModelAttribute("cont_bde_txt7") String cont_bde_txt,
			@ModelAttribute("unit_name_ex7") String unit_name, @ModelAttribute("sus_no_ex7") String sus_no,
			@ModelAttribute("user_role7") String user_role, @ModelAttribute("sub_quali7") String sub_quali,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> Excel = adminDash.search_officer_en_table(cont_comd, cont_corps, cont_div,
				cont_bde, unit_name, sus_no, user_role, sub_quali);

		List<String> TH = new ArrayList<String>();

		TH.add("Command Name");
		TH.add("Corps/Area Name");
		TH.add("Division name ");
		TH.add("Brigade  Name");
		TH.add("Unit Name");
		TH.add("Approved");
		TH.add("Pending");
		TH.add("TOTAL ");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username, Excel), "userList",
				Excel);
	}

	@RequestMapping(value = "/admin/psgMainDashboard", method = RequestMethod.GET)
	public ModelAndView psgMainDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		
		 Boolean val = roledao.CheckDashboard("psgMainDashboard", roleid); 
		 if (val ==false) { 
			 return new ModelAndView("AccessTiles");
			 } 
		 if  (request.getHeader("Referer") == null) {
			 msg = ""; 
		 }
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		//Mmap.put("Getrk_medcatlist", adminDash.Getrk_medcatlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
	Mmap.put("Getrk_heldlist", adminDash.Getrk_heldlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_regimentlist", adminDash.Getrk_regimentlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_parent_armtlist",
//				adminDash.Getrk_parent_armtlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_state_armtlist",
//				adminDash.Getrk_state_armtlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_blood_grouplist",
//				adminDash.Getrk_blood_grouplist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_commandlist", adminDash.Getrk_commandlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_loclist", adminDash.Getrk_loclist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_mothertonguelist",
//				adminDash.Getrk_mothertonguelist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_userarmlist", adminDash.Getrk_userarmlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_religionlist", adminDash.Getrk_religionlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_genderlist", adminDash.Getrk_genderlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_marital_statuslist",
//				adminDash.Getrk_marital_statuslist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_borderlist", adminDash.Getrk_borderlist("", "", "", "", "", "", "", "", "", "", "", "","",""));
//		Mmap.put("Getrk_doslist", adminDash.Getrk_doslist("", "", "", "", "", "", "", "", "", "", "", "","",""));
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		// String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();

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
		Mmap.put("msg", msg);

		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("getUserarm_codeList", p_comm.getUserarm_code_dasboardList());
		Mmap.put("addedit", "0");
		Mmap.put("manpowerTable", adminDash.manpowerTable());
		/*
		 * if you want to selected data after add mode List<String>
		 * selectedGetTypeofRankList = new ArrayList<>();
		 * selectedGetTypeofRankList.add("1102"); selectedGetTypeofRankList.add("1103");
		 * Mmap.put("selectedGetTypeofRankList", selectedGetTypeofRankList);
		 */
		return new ModelAndView("psg_mainDashboardTiles", Mmap);
	}
	  @RequestMapping(value = "/data_table_off", method = RequestMethod.POST)
	  public @ResponseBody ModelAndView data_table_off(
		        ModelMap Mmap,
		        HttpSession session,
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
		        @RequestParam(value = "unit_name40", required = false) String unit_name4,
		        HttpServletRequest request) {
	

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
			return new ModelAndView("FormationwiseoffTiles");
	    }
	  @RequestMapping(value = "/data_table_unit", method = RequestMethod.POST)
	  public @ResponseBody ModelAndView data_table_unit(
	          ModelMap Mmap,
	          HttpSession session,
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
	          @RequestParam(value = "unit_name50", required = false) String unit_name50,
	          HttpServletRequest request) {

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
	      return new ModelAndView("UnitwiseDetailTiles");
	  }

	  @RequestMapping(value = "/data_table_form_filled_unit", method = RequestMethod.POST)
	  public @ResponseBody ModelAndView data_table_form_filled_unit(
	          ModelMap Mmap,
	          HttpSession session,
	          @RequestParam(value = "cont_comd60", required = false) String cont_comd50,
	          @RequestParam(value = "cont_corps60", required = false) String cont_corps50,
	          @RequestParam(value = "cont_div60", required = false) String cont_div50,
	          @RequestParam(value = "cont_bde60", required = false) String cont_bde50,
	          @RequestParam(value = "rank60", required = false) String rank50,
	          @RequestParam(value = "arm60", required = false) String arm50,
	          @RequestParam(value = "parm60", required = false) String parm50,
	          @RequestParam(value = "cmd60", required = false) String cmd50,
	          @RequestParam(value = "bdes60", required = false) String bdes50,
	          @RequestParam(value = "div60", required = false) String div50,
	          @RequestParam(value = "corps60", required = false) String corps50,
	          @RequestParam(value = "regs60", required = false) String regs50,
	          @RequestParam(value = "unit60", required = false) String unit50,
	          @RequestParam(value = "parent_arm60", required = false) String parent_arm50,
	          @RequestParam(value = "unit_name60", required = false) String unit_name50,
	          HttpServletRequest request) {

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
	      return new ModelAndView("FormFilledunitTiles");
	  }
	  
	  @RequestMapping(value = "/data_table_form_filled", method = RequestMethod.POST)
	  public @ResponseBody ModelAndView data_table_form_filled(
	          ModelMap Mmap,
	          HttpSession session,
	          @RequestParam(value = "cont_comd70", required = false) String cont_comd50,
	          @RequestParam(value = "cont_corps70", required = false) String cont_corps50,
	          @RequestParam(value = "cont_div70", required = false) String cont_div50,
	          @RequestParam(value = "cont_bde70", required = false) String cont_bde50,
	          @RequestParam(value = "rank70", required = false) String rank50,
	          @RequestParam(value = "arm70", required = false) String arm50,
	          @RequestParam(value = "parm70", required = false) String parm50,
	          @RequestParam(value = "cmd70", required = false) String cmd50,
	          @RequestParam(value = "bdes70", required = false) String bdes50,
	          @RequestParam(value = "div70", required = false) String div50,
	          @RequestParam(value = "corps70", required = false) String corps50,
	          @RequestParam(value = "regs70", required = false) String regs50,
	          @RequestParam(value = "unit70", required = false) String unit50,
	          @RequestParam(value = "parent_arm70", required = false) String parent_arm50,
	          @RequestParam(value = "unit_name70", required = false) String unit_name50,
	          HttpServletRequest request) {

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
	      return new ModelAndView("FormFilledoffTiles");
	  }

	  
	  
//	  @RequestMapping(value = "/data_table_form_filled_unit", method = RequestMethod.POST)
//	  public @ResponseBody ModelAndView data_table_form_filled_unit(
//	          ModelMap Mmap,
//	          HttpSession session,
//	          @RequestParam(value = "cont_comd50", required = false) String cont_comd50,
//	          @RequestParam(value = "cont_corps50", required = false) String cont_corps50,
//	          @RequestParam(value = "cont_div50", required = false) String cont_div50,
//	          @RequestParam(value = "cont_bde50", required = false) String cont_bde50,
//	          @RequestParam(value = "rank50", required = false) String rank50,
//	          @RequestParam(value = "arm50", required = false) String arm50,
//	          @RequestParam(value = "parm50", required = false) String parm50,
//	          @RequestParam(value = "cmd50", required = false) String cmd50,
//	          @RequestParam(value = "bdes50", required = false) String bdes50,
//	          @RequestParam(value = "div50", required = false) String div50,
//	          @RequestParam(value = "corps50", required = false) String corps50,
//	          @RequestParam(value = "regs50", required = false) String regs50,
//	          @RequestParam(value = "unit50", required = false) String unit50,
//	          @RequestParam(value = "parent_arm50", required = false) String parent_arm50,
//	          @RequestParam(value = "unit_name50", required = false) String unit_name50,
//	          HttpServletRequest request) {
//
//	      Mmap.addAttribute("cont_comd50", cont_comd50);
//	      Mmap.addAttribute("cont_corps50", cont_corps50);
//	      Mmap.addAttribute("cont_div50", cont_div50);
//	      Mmap.addAttribute("cont_bde50", cont_bde50);
//	      Mmap.addAttribute("rank50", rank50);
//	      Mmap.addAttribute("arm50", arm50);
//	      Mmap.addAttribute("parm50", parm50);
//	      Mmap.addAttribute("cmd50", cmd50);
//	      Mmap.addAttribute("bdes50", bdes50);
//	      Mmap.addAttribute("div50", div50);
//	      Mmap.addAttribute("corps50", corps50);
//	      Mmap.addAttribute("regs50", regs50);
//	      Mmap.addAttribute("unit50", unit50);
//	      Mmap.addAttribute("parent_arm50", parent_arm50);
//	      Mmap.addAttribute("unit_name50", unit_name50);
//	      return new ModelAndView("UnitwiseDetailTiles");
//	  }

	  
	  
	  
	  
	
	  @RequestMapping(value = "/Getrk_medcatlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_medcatlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {   	
	            
	            String list = adminDash.Getrk_medcatlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                            parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	    	// String list = adminDash.Getrk_medcatlist("", "", "", "", "", "", "", "", "", "", "", "","","");
	            
	            return list;
	    }
	  
	  @RequestMapping(value = "/getSearchtabledatacount", method = RequestMethod.POST)
		public  @ResponseBody int getSearchtabledatacount(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        HttpServletRequest request) {
		
	int  list = adminDash.FindOfficiercount(Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		
			return list ;
		}	
	  @RequestMapping(value = "/getSearchtabledataoff", method = RequestMethod.POST)
		public  @ResponseBody ArrayList<ArrayList<String>> getSearchtabledataoff(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        @RequestParam(value = "pageLength", required = false) String pageLength,
		        HttpServletRequest request) {
	
				ArrayList<ArrayList<String>> list = adminDash.FindOfficiertable(Integer.parseInt(startPage),Integer.parseInt(pageLength),Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		
			return list ;
		}	
		
	  
	  @RequestMapping(value = "/getSearchtabledataunit", method = RequestMethod.POST)
		public  @ResponseBody ArrayList<ArrayList<String>> getSearchtabledataunit(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        @RequestParam(value = "pageLength", required = false) String pageLength,
		        HttpServletRequest request) {
	
				ArrayList<ArrayList<String>> list = adminDash.Findunittable(Integer.parseInt(startPage),Integer.parseInt(pageLength),Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		
			return list ;
		}	
	  @RequestMapping(value = "/getSearchtabledata_form_filled", method = RequestMethod.POST)
		public  @ResponseBody ArrayList<ArrayList<String>> getSearchtabledata_form_filled(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        @RequestParam(value = "pageLength", required = false) String pageLength,
		        HttpServletRequest request) {
	
				ArrayList<ArrayList<String>> list = adminDash.FormFilledtable(Integer.parseInt(startPage),Integer.parseInt(pageLength),Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		
			return list ;
		}	
	  
	  @RequestMapping(value = "/getSearchtabledata_unitcount", method = RequestMethod.POST)
		public  @ResponseBody int getSearchtabledata_unitcount(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        HttpServletRequest request) {
		
	int  list = adminDash.Findunittablecount(Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		
			return list ;
		}	
	  
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/getSearchtabledata_form_filled_count", method = RequestMethod.POST)
			public  @ResponseBody int getSearchtabledata_form_filled_count(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
			        HttpServletRequest request) {
			
		int  list = adminDash.FindCensusFillOfftablecount(Search,cont_comd4, cont_corps4, cont_div4,
						cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
			
				return list ;
			}	
	  
	  
	  
	  @RequestMapping(value = "/getSearchtabledata_form_filled_unit_count", method = RequestMethod.POST)
		public  @ResponseBody int getSearchtabledata_form_filled_unit_count(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
		        HttpServletRequest request) {
		
	int  list = adminDash.FindCensusFill_unit_tablecount(Search,cont_comd4, cont_corps4, cont_div4,
					cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
	
			return list ;
		}	

	  
	  @RequestMapping(value = "/getSearchtabledata_form_filled_unit", method = RequestMethod.POST)
			public  @ResponseBody ArrayList<ArrayList<String>> getSearchtabledata_form_filled_unit(   @RequestParam(value = "cont_comd4", required = false) String cont_comd4,
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
			        @RequestParam(value = "pageLength", required = false) String pageLength,
			        HttpServletRequest request) {
		
					ArrayList<ArrayList<String>> list = adminDash.FormFilled_unit_table(Integer.parseInt(startPage),Integer.parseInt(pageLength),Search,cont_comd4, cont_corps4, cont_div4,
						cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
			
				return list ;
			}	
	  
	  
	  
	  @RequestMapping(value = "/Getrk_regimentlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_regimentlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_regimentlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_state_armtlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_state_armtlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	          
	            String list = adminDash.Getrk_state_armtlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            return list;
	    }
	    
	    
	    @RequestMapping(value = "/Getrk_parent_armtlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_parent_armtlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	    	  String list = adminDash.Getrk_parent_armtlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                  parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_blood_grouplist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_blood_grouplist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_blood_grouplist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_commandlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_commandlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_commandlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_genderlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_genderlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_genderlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    
	    @RequestMapping(value = "/Getrk_doslist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_doslist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_doslist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_mothertonguelist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_mothertonguelist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_mothertonguelist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_marital_statuslist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_marital_statuslist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_marital_statuslist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_userarmlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_userarmlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_userarmlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	    
	    @RequestMapping(value = "/Getrk_religionlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_religionlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_religionlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	
	
	    @RequestMapping(value = "/Getrk_borderlist_off", method = RequestMethod.POST)
	    public @ResponseBody String Getrk_borderlist_off(String cont_comd1, String cont_corps1,
	                    String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
	                    String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1) {
	            
	            String list = adminDash.Getrk_borderlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
	                    parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1);
	            
	            return list;
	    }
	
	

//		@RequestMapping(value = "/Getrk_borderlist_off", method = RequestMethod.POST)
//		public @ResponseBody List<Map<String, Object>> Getrk_borderlist_off() {
//			List<Map<String, Object>> list_nom = adminDash.Getrk_borderlist();
//			return list_nom;
//		}
//	
//	
	
	
//-----------------------------------------------refresh search---------------------------------------------------

	    @RequestMapping(value = "/admin/psg_dashboard_datalist", method = RequestMethod.POST)
		public ModelAndView psg_dashboard_datalist(@ModelAttribute("cont_comd1") String cont_comd1, String cont_corps1,
				String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String bdes1,
				String div1, String corps1, String regs1, String unit1,String parent_arm1,String unit_name1,String unit_view, ModelMap Mmap, HttpSession session) {

			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();

			
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl = (String) session.getAttribute("roleAccess");

			// Mmap.put("notifiable_diseaselist", MnhFinal.notifiable_diseaselist(year));

//			Mmap.put("Getrk_medcatlist", adminDash.Getrk_medcatlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
//					arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
			Mmap.put("Getrk_heldlist", adminDash.Getrk_heldlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
					parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_regimentlist", adminDash.Getrk_regimentlist(cont_comd1, cont_corps1, cont_div1, cont_bde1,
//					rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_parent_armtlist", adminDash.Getrk_parent_armtlist(cont_comd1, cont_corps1, cont_div1, cont_bde1,
//					rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_state_armtlist", adminDash.Getrk_state_armtlist(cont_comd1, cont_corps1, cont_div1, cont_bde1,
//					rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_blood_grouplist", adminDash.Getrk_blood_grouplist(cont_comd1, cont_corps1, cont_div1, cont_bde1,
//					rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_commandlist", adminDash.Getrk_commandlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
//					arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_loclist", adminDash.Getrk_loclist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
//					parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_mothertonguelist", adminDash.Getrk_mothertonguelist(cont_comd1, cont_corps1, cont_div1,
//					cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_userarmlist", adminDash.Getrk_userarmlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
//					arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_religionlist", adminDash.Getrk_religionlist(cont_comd1, cont_corps1, cont_div1, cont_bde1,
//					rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_genderlist", adminDash.Getrk_genderlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
//					arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_marital_statuslist", adminDash.Getrk_marital_statuslist(cont_comd1, cont_corps1, cont_div1,
//					cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_borderlist", adminDash.Getrk_borderlist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1,
//					arm1, parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
//			Mmap.put("Getrk_doslist", adminDash.Getrk_doslist(cont_comd1, cont_corps1, cont_div1, cont_bde1, rank1, arm1,
//					parm1, cmd1, div1, corps1, bdes1, regs1,parent_arm1,unit_name1));
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
	Mmap.put("corps", corps1);
	Mmap.put("div1", div1);
	Mmap.put("bdes", bdes1);
	Mmap.put("manpowerTable", adminDash.manpowerTable());
	if(unit_view.equals("YES")) {
		Mmap.put("unit_view", "YES");
	}else {
		Mmap.put("unit_view", "No");
	}
			return new ModelAndView("psg_mainDashboardTiles", Mmap);
		}

	@RequestMapping(value = "/getDashboardData", method = RequestMethod.POST)
	public String getDashboardData(String fcode, ModelMap Mmap) {
		ArrayList<List<String>> list = all.getCorpsList(fcode);

		return "";
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getrankwise", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getservice(String project) {

		List<Map<String, Object>> list_nom = adminDash.getrankwise();
		return list_nom;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/Getcount_no_offData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_no_offData() {
		List<Map<String, Object>> list_nom = adminDash.Getcount_no_offData();
		return list_nom;
	}

	@RequestMapping(value = "/Getcount_no_unitData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_no_unitData() {
		List<Map<String, Object>> list_nom = adminDash.Getcount_no_unitData();
		return list_nom;
	}
	
	@RequestMapping(value = "/Getcount_no_censusFormFilled", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_no_censusFormFilled() {
		List<Map<String, Object>> list_nom = adminDash.Getcount_no_censusFormFilled();
		return list_nom;
	}
	
	
	@RequestMapping(value = "/Getcount_no_censusFormFilled_unit", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Getcount_no_censusFormFilled_unit() {
		List<Map<String, Object>> list_nom = adminDash.Getcount_no_censusFormFilled_unit();
		return list_nom;
	}
	


	@RequestMapping(value = "/admin/Formationwiseoff", method = RequestMethod.GET)
	public ModelAndView Formationwiseoff(
			@RequestParam("cont_comd1") String cont_comd1,	@RequestParam("cont_corps1") String cont_corps1,
			@RequestParam("cont_div1") String cont_div1, @RequestParam("cont_bde1") String cont_bde1,@RequestParam("rank1") String rank1,
			@RequestParam("arm1") String arm1, 	@RequestParam("parm1") String parm1, @RequestParam("cmd1") String cmd1, 
			@RequestParam("bdes1") String bdes1,@RequestParam("div1") 	String div1, 	@RequestParam("corps1") String corps1, 
			@RequestParam("regs1") String regs1, 	@RequestParam("unit1") String unit1,	@RequestParam("parent_arm1") String parent_arm1,
			@RequestParam("unit_name1") String unit_name1, 
			ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	
	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
	
		
		ArrayList<ArrayList<String>> list=adminDash.FindoffDatatable(cont_comd1, cont_corps1, cont_div1,
				cont_bde1, rank1, arm1, parm1, cmd1, div1, corps1, bdes1, regs1);
		
		  if(list.size() == 0){ 
			  Mmap.put("msg", "Data Not Available."); 
		  }	  
		  else
		  { 
			  Mmap.put("list", list); 
			  Mmap.put("listsize", list.size()); 
			  
		  }
		for(ArrayList<String> s :list)
		{
			
		}
		
	
		return new ModelAndView("FormationwiseoffTiles");
	}

	@RequestMapping(value = "/admin/getformationwiseoff", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Table> getformationwiseoff(
			
			@DatatablesParams DatatablesCriterias criterias, 
		
			HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());

	
		DataSet<View_Table> dataSet = adminDash.DatatablesCriteriasFormationWiseoff(criterias);
		
		return DatatablesResponse.build(dataSet, criterias);
	}
	@RequestMapping(value = "/admin/UnitwiseDetail", method = RequestMethod.GET)
	public ModelAndView UnitwiseDetail(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	
	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("UnitwiseDetailTiles");
	}
	 @RequestMapping(value = "/admin/getunitwiseoff", method = RequestMethod.GET)
		public @ResponseBody DatatablesResponse<View_Table> getunitwiseoff(
				@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			DataSet<View_Table> dataSet = adminDash.DatatablesCriteriasUnitWiseoff(criterias);
			
			return DatatablesResponse.build(dataSet, criterias);
		}
	@RequestMapping(value = "/admin/FormFilledoff", method = RequestMethod.GET)
	public ModelAndView FormFilledoff(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("FormFilledoffTiles");
	}
	@RequestMapping(value = "/admin/getformfilledoff", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Table> getformfilledoff(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Table> dataSet = adminDash.DatatablesFormFilledoff(criterias);
		
		return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "/Excel_psg_off_filled", method = RequestMethod.POST)
	public ModelAndView Excel_psg_off_filled(@ModelAttribute("typeReportFilledoff")String typeReportFilledoff, String cont_comd4, String cont_corps4,
			String cont_div4, String cont_bde4, String rank4, String arm4, String parm4, String cmd4, String bdes4,
			String div4, String corps4, String regs4, String unit4,String parent_arm4,String unit_name4, ModelMap Mmap, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> Excel = adminDash.FindCensusFillOff(cont_comd4, cont_corps4, cont_div4,
				cont_bde4, rank4, arm4, parm4, cmd4, div4, corps4, bdes4, regs4,parent_arm4,unit_name4);
		List<String> TH = new ArrayList<String>();

		TH.add("COMMAND");
		TH.add("CORPS");
		TH.add("DIVISION");
		TH.add("BRIGADE");
		TH.add("UNIT NAME");
		TH.add("PERSONNEL NO");
		TH.add("NAME");
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_PSG("L", TH, username, Excel), "userList", Excel);
	}	
	
	
	@RequestMapping(value = "/admin/FormFilledunit", method = RequestMethod.GET)
	public ModelAndView FormFilledunit(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	{
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("FormFilledunitTiles");
	}
	
	@RequestMapping(value = "/admin/getformfilledunit", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<View_Table> getformfilledunit(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DataSet<View_Table> dataSet = adminDash.DatatablesFormFilledunit(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}
	//----------------------Export Excel
	
	@RequestMapping(value = "/Excel_psg", method = RequestMethod.POST)
	public ModelAndView Excel_psg
	(@ModelAttribute("typeReport1")String typeReport1, String cont_comd2, String cont_corps2,
			String cont_div2, String cont_bde2, String rank2, String arm2, String parm2, String cmd2, String bdes2,
			String div2, String corps2, String regs2, String unit2,String parent_arm2,String unit_name2, ModelMap Mmap, HttpSession session
//			HttpServletRequest request, ModelMap model, HttpSession session,

			) {

		String roleid = session.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> Excel = adminDash.FindOfficier(cont_comd2, cont_corps2, cont_div2,
				cont_bde2, rank2, arm2, parm2, cmd2, div2, corps2, bdes2, regs2,parent_arm2,unit_name2);
		List<String> TH = new ArrayList<String>();

		TH.add("PERSONNEL NO");
		TH.add("RANK");
		TH.add("NAME");
		TH.add("DATE OF BIRTH");
		TH.add("DATE OF COMMISSION");
		TH.add("DATE OF SENIORITY");
		TH.add("UNIT NAME");
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_PSG("L", TH, username, Excel), "userList", Excel);
	}	
	@RequestMapping(value = "/Excel_psg_unit", method = RequestMethod.POST)
	public ModelAndView Excel_psg_unit(@ModelAttribute("typeReport2")String typeReport2, String cont_comd3, String cont_corps3,
			String cont_div3, String cont_bde3, String rank3, String arm3, String parm3, String cmd3, String bdes3,
			String div3, String corps3, String regs3, String unit3,String parent_arm3,String unit_name3, ModelMap Mmap, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> Excel = adminDash.Findunit(cont_comd3, cont_corps3, cont_div3,
				cont_bde3, rank3, arm3, parm3, cmd3, div3, corps3, bdes3, regs3,parent_arm3, unit_name3);
		List<String> TH = new ArrayList<String>();

		TH.add("COMMAND");
		TH.add("CORPS");
		TH.add("DIVISION");
		TH.add("BRIGADE");
		TH.add("UNIT NAME");
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_PSG("L", TH, username, Excel), "userList", Excel);
	}
	
		@RequestMapping(value = "/DivDetailsListget",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> DivDetailsListget(String selectedcorps,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

			ArrayList<List<String>> list = adminDash.getDivList(selectedcorps);

			return list;
		}
		@RequestMapping(value = "/BdeDetailsListget",method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> BdeDetailsListget(String selecteddiv,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

			ArrayList<List<String>> list = adminDash.getBdeList(selecteddiv);
		
			return list;
		}
		@RequestMapping(value = "/CorpsDetailsListget",method = RequestMethod.POST)
 		public @ResponseBody ArrayList<List<String>> CorpsDetailsListget(String selectedcmd,HttpSession sessionUserId) 
 			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			ArrayList<List<String>> list = adminDash.getCorpsList(selectedcmd);

 		    return list;
 		}
		@RequestMapping(value = "/Excel_psg_unit_filled", method = RequestMethod.POST)
		public ModelAndView Excel_psg_unit_filled(@ModelAttribute("typeReportFilledunit")String typeReportFilledunit, String cont_comd5, String cont_corps5,
				String cont_div5, String cont_bde5, String rank5, String arm5, String parm5, String cmd5, String bdes5,
				String div5, String corps5, String regs5, String unit5,String parent_arm5,String unit_name5, ModelMap Mmap, HttpSession session) {
			String roleid = session.getAttribute("roleid").toString();
			ArrayList<ArrayList<String>> Excel = adminDash.FindCensusFillUnits(cont_comd5, cont_corps5, cont_div5,
					cont_bde5, rank5, arm5, parm5, cmd5, div5, corps5, bdes5, regs5,parent_arm5,unit_name5);
			List<String> TH = new ArrayList<String>();

			TH.add("COMMAND");
			TH.add("CORPS");
			TH.add("DIVISION");
			TH.add("BRIGADE");
			TH.add("UNIT NAME");
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_PSG("L", TH, username, Excel), "userList", Excel);
		}	

	

}
