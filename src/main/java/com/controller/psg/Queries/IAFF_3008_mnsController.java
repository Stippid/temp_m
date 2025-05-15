package com.controller.psg.Queries;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionIdentifierAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Excel_To_Export_4_Table_Report_mns;
import com.controller.ExportFile.Report_3008_mns_Pdf;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.IUT_Controller;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.IAFF_3008DAO;
import com.dao.psg.Queries.IAFF_3008_REPORT_mns_DAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class IAFF_3008_mnsController {

	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	IUT_Controller iut = new IUT_Controller();
	@Autowired
	private IAFF_3008_REPORT_mns_DAO IDAO;

	@Autowired
	private IAFF_3008DAO IAFFDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/IAFF_3008_Query_mns", method = RequestMethod.GET)
	public ModelAndView IAFF_3008_Query_mns(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3008_Query_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getCommandList", m.getCommandDetailsList());

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();

		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));

				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", formation_code);

				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Corps")) {

				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);
				Mmap.put("bde_sus", bde_code);
			}
		}

		if (roleAccess.equals("Unit")) {
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
			Mmap.put("getCommandList", comd);
			Mmap.put("cmd_sus", command);

			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
			Mmap.put("getCorpsList", corps);
			Mmap.put("corp_sus", cor);

			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
					+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
			Mmap.put("getDivList", Bn);
			Mmap.put("div_sus", div);

			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
			Mmap.put("getBdeList", bde);
			Mmap.put("bde_sus", bde_code);

		}
		if (roleAccess.equals("Line_dte")) {
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("line_dte_sus_no", roleSusNo);
		}
		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd = m.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value='0'>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("getLineDteList", iut.getLineDteList());
		return new ModelAndView("IAFF_3008_Query_mns_Tiles");
	}

	public @ResponseBody List<String> getRankDescription(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"select distinct description from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' and parent_code ='0'\r\n"
						+ "and code not in ('R009','R099','R400','R110','R203','R207','R307','R1001','R1002','TQ284','210','R200','R115','R205','R116','R117',\r\n"
						+ "				 'R201','R208','R202','R128','R129','R306','R204','R114') and status_active = 'Active' and id =:id ");

		q.setParameter("id", id);

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		tx.commit();

		session.close();

		return list;

	}

	public List<String> getFormationListSus_no(String level_in_hierarchy, String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;

		if (level_in_hierarchy.equals("Command")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl " + "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);

		}
		if (level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl " + "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}
		if (level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl " + "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}
		if (level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select sus_no from Miso_Orbat_Unt_Dtl " + "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode + "%");

		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// ------------------PRINT DOWNLOAD

	@RequestMapping(value = "/Download_IAFF3008_query_mns", method = RequestMethod.POST)
	public ModelAndView Download_IAFF3008_query_mns(@ModelAttribute("cont_comd_tx") String cont_comd_tx,
			@ModelAttribute("cont_corps_tx") String cont_corps_tx, @ModelAttribute("cont_div_tx") String cont_div_tx,
			@ModelAttribute("cont_bde_tx") String cont_bde_tx,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "hd_cmd_sus2", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus2", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus2", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus2", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,
			@RequestParam(value = "rank2", required = false) String rank, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3008_Query_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";

		if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("") && !hd_cmd_sus.equals("null")
				&& !hd_cmd_sus.equals(null)) {
			for_code = hd_cmd_sus;
			List<String> comd = getFormationListSus_no("Command", for_code);
			if (comd.size() > 0)
				cmd_sus = comd.get(0);
		}
		if (!hd_corp_sus.equals("0") && !hd_corp_sus.equals("") && !hd_corp_sus.equals("null")
				&& !hd_corp_sus.equals(null)) {
			for_code = hd_corp_sus;
			List<String> cor = getFormationListSus_no("Corps", for_code);
			if (cor.size() > 0)
				corp_sus = cor.get(0);
		}
		if (!hd_div_sus.equals("0") && !hd_div_sus.equals("") && !hd_div_sus.equals("null")
				&& !hd_div_sus.equals(null)) {
			for_code = hd_div_sus;
			List<String> div = getFormationListSus_no("Division", for_code);
			if (div.size() > 0)
				div_sus = div.get(0);
		}
		if (!hd_bde_sus.equals("0") && !hd_bde_sus.equals("") && !hd_bde_sus.equals("null")
				&& !hd_bde_sus.equals(null)) {
			for_code = hd_bde_sus;
			List<String> bde = getFormationListSus_no("Brigade", for_code);
			if (bde.size() > 0)
				bde_sus = bde.get(0);
		}
		if (!rank.equals("0") && !rank.equals("") && !rank.equals("null") && !rank.equals(null)) {
			List<String> rank_val = getRankDescription(Integer.parseInt(rank));
			rank_des = rank_val.get(0);

		}

		ArrayList<ArrayList<String>> list_serving = IDAO.iaff3008_Report_Serving(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no, rank_des);

		ArrayList<ArrayList<String>> list_super = IDAO.iaff3008_Report_SuperNum(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

//         ArrayList<ArrayList<String>> list_ReEmp = IDAO.iaff3008_Report_Re_Empl(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);        

		ArrayList<ArrayList<String>> list_Des = IDAO.iaff3008_Report_Deserter(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

		ArrayList<ArrayList<String>> list_Main = IDAO.iaff3008_Report_MainDetails(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no);

//          ArrayList<ArrayList<String>> list_auth =    IDAO.iaff_Report_3008_Auth_Held(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);
//          Mmap.put("list5", list_auth);    
//          Mmap.put("size4", list_auth.size());
		// bisag v2 290822 (split auth and held)
		ArrayList<ArrayList<String>> list_auth = IDAO.iaff_Report_3008_Auth(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);
		Mmap.put("list5", list_auth);
		Mmap.put("size5", list_auth.size());

		ArrayList<ArrayList<String>> list_held = IDAO.iaff_Report_3008_Held(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);
		Mmap.put("list7", list_held);
		Mmap.put("size7", list_held.size());

		String hd_remarks1 = "";
		String hd_remarks2 = "";
		String hd_remarks3 = "";
		String hd_remarks4 = "";
		String hd_remarks5 = "";
		String hd_remarks6 = "";
		String hd_remarks7 = "";

		ArrayList<ArrayList<String>> getremarks = IDAO.getRemarks(month, year, unit_sus_no);
		if (!getremarks.isEmpty()) {
			ArrayList<String> remarkList = getremarks.get(0);
			hd_remarks1 = remarkList.get(0);
			hd_remarks2 = remarkList.get(1);
			hd_remarks3 = remarkList.get(2);
			hd_remarks4 = remarkList.get(3);
			hd_remarks5 = remarkList.get(4);
			hd_remarks6 = remarkList.get(5);
			hd_remarks7 = remarkList.get(6);
		}
		int totalAuth = 0;
		int totalHeld = 0;
		int sum_held = 0;
		int sum_auth = 0;

		for (int i = 0; i < list_auth.size(); i++) {
			String totalAuth1 = list_auth.get(i).get(5);

			if (totalAuth1 == null || totalAuth1.equals(null)) {
				totalAuth = 0;
			} else {
				totalAuth = Integer.parseInt(totalAuth1);
			}

			sum_auth += totalAuth;
		}
		for (int i = 0; i < list_held.size(); i++) {

			String totalHeld1 = list_held.get(i).get(2);

			if (totalHeld1 == null || totalHeld1.equals(null)) {
				totalHeld = 0;
			} else {
				totalHeld = Integer.parseInt(totalHeld1);
			}
			sum_held += totalHeld;
		}

		// bisag v2 290822 (split auth and held) end

		int sur = sum_held - sum_auth;
		int defi = 0;
		if (sur >= 0) {
			defi = sur;
			sur = 0;
		} else {
			sur = sur;
			defi = 0;
		}
		Mmap.put("totalAuth", sum_auth);

		Mmap.put("totalHeld", sum_held);
		Mmap.put("defi", Math.abs(sur));
		Mmap.put("sur", defi);

		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);

		Date date = new Date();
		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		// if(pdfprint.size() > 0) {
		List<String> TH = new ArrayList<String>();
		TH.add("Ser No");
		TH.add("Appt");
		TH.add("Rank");
		TH.add("Name");
		TH.add("Personal No");
		TH.add("CDA A/C No");
		TH.add("Medical Cat");
		TH.add("Date of TOS");
		TH.add("Date of Birth");
		TH.add("Date of Comm");
		TH.add("Date of SEN ");
		TH.add("Date Of Present Rank");
		TH.add("Tnai No");
		TH.add("Married/Single/Separated");
		TH.add("DOM");
		/* TH.add("Remarks/Sns/Deserter/Present/Leave"); */

		List<String> TH4 = new ArrayList<String>();
		TH4.add("Ser No");
		TH4.add("Rank");
		TH4.add("Base Auth");
		TH4.add("Mod Auth");
		TH4.add("Foot Auth");
		TH4.add("Total Auth");
//             TH4.add("Total Held");

		List<String> TH6 = new ArrayList<String>();
		TH6.add("Ser No");
		TH6.add("Rank");
//             TH4.add("Base Auth");
//             TH4.add("Mod Auth");
//             TH4.add("Foot Auth");
//             TH4.add("Total Auth");
		TH6.add("Total Held");

		List<String> TH5 = new ArrayList<String>();
		TH5.add("Auth Strength");
		TH5.add("Holding Strength");
		TH5.add("Surplus");
		TH5.add("Deficiency");

		if (cont_comd_tx.equals("--Select--")) {
			cont_comd_tx = "";
		}
		if (cont_corps_tx.equals("--Select--")) {
			cont_corps_tx = "";
		}
		if (cont_div_tx.equals("--Select--")) {
			cont_div_tx = "";
		}
		if (cont_bde_tx.equals("--Select--")) {
			cont_bde_tx = "";
		}

		return new ModelAndView(new Report_3008_mns_Pdf(TH, TH4, TH5, foot, username, list_super, list_Des, list_auth,
				list_Main, cont_comd_tx, cont_corps_tx, cont_div_tx, cont_bde_tx, sum_auth, sum_held, defi, sur,
				hd_remarks1, hd_remarks2, hd_remarks3, hd_remarks4, hd_remarks5, hd_remarks6, hd_remarks7, TH6,
				list_held), "userList", list_serving);
	}

	@RequestMapping(value = "/Excel_IAFF_3008_query_mns", method = RequestMethod.POST)
	public ModelAndView Excel_IAFF_3008_query_mns(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @ModelAttribute("cont_comd_txt") String cont_comd_tx,
			@ModelAttribute("cont_corps_txt") String cont_corps_tx, @ModelAttribute("cont_div_txt") String cont_div_tx,
			@ModelAttribute("cont_bde_txt") String cont_bde_tx,
			@RequestParam(value = "month3", required = false) String month,
			@RequestParam(value = "year3", required = false) String year,
			@RequestParam(value = "hd_cmd_sus3", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus3", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus3", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus3", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no4", required = false) String unit_sus_no,
			@RequestParam(value = "rank3", required = false) String rank, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3008_Query_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";

		if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("") && !hd_cmd_sus.equals("null")
				&& !hd_cmd_sus.equals(null)) {
			for_code = hd_cmd_sus;
			List<String> comd = getFormationListSus_no("Command", for_code);
			if (comd.size() > 0)
				cmd_sus = comd.get(0);
		}
		if (!hd_corp_sus.equals("0") && !hd_corp_sus.equals("") && !hd_corp_sus.equals("null")
				&& !hd_corp_sus.equals(null)) {
			for_code = hd_corp_sus;
			List<String> cor = getFormationListSus_no("Corps", for_code);
			if (cor.size() > 0)
				corp_sus = cor.get(0);
		}
		if (!hd_div_sus.equals("0") && !hd_div_sus.equals("") && !hd_div_sus.equals("null")
				&& !hd_div_sus.equals(null)) {
			for_code = hd_div_sus;
			List<String> div = getFormationListSus_no("Division", for_code);
			if (div.size() > 0)
				div_sus = div.get(0);
		}
		if (!hd_bde_sus.equals("0") && !hd_bde_sus.equals("") && !hd_bde_sus.equals("null")
				&& !hd_bde_sus.equals(null)) {
			for_code = hd_bde_sus;
			List<String> bde = getFormationListSus_no("Brigade", for_code);
			if (bde.size() > 0)
				bde_sus = bde.get(0);
		}
		if (!rank.equals("0") && !rank.equals("") && !rank.equals("null") && !rank.equals(null)) {
			List<String> rank_val = getRankDescription(Integer.parseInt(rank));
			rank_des = rank_val.get(0);

		}
		ArrayList<ArrayList<String>> Excel = IDAO.iaff3008_Report_Serving(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

		ArrayList<ArrayList<String>> Excel1 = IDAO.iaff3008_Report_SuperNum(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

//         ArrayList<ArrayList<String>> Excel2 = IDAO.iaff3008_Report_Re_Empl(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);        

		ArrayList<ArrayList<String>> Excel3 = IDAO.iaff3008_Report_Deserter(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

//          ArrayList<ArrayList<String>> Excel4 =    IDAO.iaff_Report_3008_Auth_Held(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);
		// bisag v2 290822 (split auth and held)
		ArrayList<ArrayList<String>> Excel4 = IDAO.iaff_Report_3008_Auth(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

		ArrayList<ArrayList<String>> list_held = IDAO.iaff_Report_3008_Held(month, year, cmd_sus, corp_sus, div_sus,
				bde_sus, unit_sus_no, rank_des);

		int totalAuth = 0;
		int totalHeld = 0;
		int sum_held = 0;
		int sum_auth = 0;

		for (int i = 0; i < Excel4.size(); i++) {
			String totalAuth1 = Excel4.get(i).get(5);

			if (totalAuth1 == null || totalAuth1.equals(null)) {
				totalAuth = 0;
			} else {
				totalAuth = Integer.parseInt(totalAuth1);
			}

			sum_auth += totalAuth;
		}
		for (int i = 0; i < list_held.size(); i++) {

			String totalHeld1 = list_held.get(i).get(2);

			if (totalHeld1 == null || totalHeld1.equals(null)) {
				totalHeld = 0;
			} else {
				totalHeld = Integer.parseInt(totalHeld1);
			}
			sum_held += totalHeld;
		}

		// bisag v2 290822 (split auth and held) end

		int sur = sum_held - sum_auth;
		int defi = 0;
		if (sur >= 0) {
			defi = sur;
			sur = 0;
		} else {
			sur = sur;
			defi = 0;
		}
		Mmap.put("totalAuth", sum_auth);
		Mmap.put("totalHeld", sum_held);
		Mmap.put("defi", defi);
		Mmap.put("sur", Math.abs(sur));

		List<String> TH = new ArrayList<String>();
		TH.add("Ser No");
		TH.add("Appt");
		TH.add("Rank");
		TH.add("Name");
		TH.add("Personal No");
		TH.add("CDA A/C No");
		TH.add("Arm/Service");
		TH.add("Medical Cat");
		TH.add("Date of TOS");
		TH.add("Date of Birth");
		TH.add("Date of Comm");
		TH.add("Date of SEN ");
		TH.add("Date of Appt");

		List<String> TH1 = new ArrayList<String>();
		TH1.add("Ser No");
		TH1.add("Appt");
		TH1.add("Rank");
		TH1.add("Name");
		TH1.add("Personal No");
		TH1.add("CDA A/C No");
		TH1.add("Arm/Service");
		TH1.add("Medical Cat");
		TH1.add("Date of TOS");
		TH1.add("Date of Birth");
		TH1.add("Date of Comm");
		TH1.add("Date of SEN ");
		TH1.add("Date of Appt");

		List<String> TH2 = new ArrayList<String>();
		TH2.add("Ser No");
		TH2.add("Appt");
		TH2.add("Rank");
		TH2.add("Name");
		TH2.add("Personal No");
		TH2.add("CDA A/C No");
		TH2.add("Arm/Service");
		TH2.add("Medical Cat");
		TH2.add("Date of TOS");
		TH2.add("Date of Birth");
		TH2.add("Date of Comm");
		TH2.add("Date of SEN ");
		TH2.add("Date of Appt");

		List<String> TH3 = new ArrayList<String>();
		TH3.add("Ser No");
		TH3.add("Appt");
		TH3.add("Rank");
		TH3.add("Name");
		TH3.add("Personal No");
		TH3.add("CDA A/C No");
		TH3.add("Arm/Service");
		TH3.add("Medical Cat");
		TH3.add("Date of TOS");
		TH3.add("Date of Birth");
		TH3.add("Date of Comm");
		TH3.add("Date of SEN ");
		TH3.add("Date of Appt");

		List<String> TH4 = new ArrayList<String>();
		TH4.add("Ser No");
		TH4.add("Rank");
		TH4.add("Base Auth");
		TH4.add("Mod Auth");
		TH4.add("Foot Auth");
		TH4.add("Total Auth");
//         TH4.add("Total Held");
		List<String> TH6 = new ArrayList<String>();
		TH6.add("Ser No");
		TH6.add("Rank");
		TH6.add("Total Held");

		List<String> TH5 = new ArrayList<String>();
		TH5.add("Auth Strength");
		TH5.add("Holding Strength");
		TH5.add("Surplus");
		TH5.add("Deficiency");

		String Heading = "\nNUMBERS OF PERS AGE/RELIGION/STATE WISE";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(
				new Excel_To_Export_4_Table_Report_mns("L", TH, TH1, TH2, TH3, TH4, Heading, username, Excel,
						Excel1, Excel3, Excel4, sum_auth, sum_held, defi, sur, TH6, list_held),
				"userList", Excel);
	}

	@RequestMapping(value = "/admin/GetI3008_Serving_mns", method = RequestMethod.POST)
	public ModelAndView GetI3008_Serving_mns(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "hd_cmd_sus1", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus1", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus1", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus1", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "line_dte1", required = false) String line_dte1,
			@RequestParam(value = "rank1", required = false) String rank)
			throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3008_Query_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		String for_code = "";
		String cmd_sus = "";
		String corp_sus = "";
		String div_sus = "";
		String bde_sus = "";
		String rank_des = "";
		if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
			for_code = hd_cmd_sus;
			List<String> comd = getFormationListSus_no("Command", for_code);
			if (comd.size() > 0)
				cmd_sus = comd.get(0);
		}
		if (!hd_corp_sus.equals("0") && !hd_corp_sus.equals("")) {
			for_code = hd_corp_sus;
			List<String> cor = getFormationListSus_no("Corps", for_code);
			if (cor.size() > 0)
				corp_sus = cor.get(0);
		}
		if (!hd_div_sus.equals("0") && !hd_div_sus.equals("")) {
			for_code = hd_div_sus;
			List<String> div = getFormationListSus_no("Division", for_code);
			if (div.size() > 0)
				div_sus = div.get(0);
		}
		if (!hd_bde_sus.equals("0") && !hd_bde_sus.equals("")) {
			for_code = hd_bde_sus;
			List<String> bde = getFormationListSus_no("Brigade", for_code);
			if (bde.size() > 0)
				bde_sus = bde.get(0);
		}

		if (!rank.equals("0") && !rank.equals("") && !rank.equals("null") && !rank.equals(null)) {
			List<String> rank_val = getRankDescription(Integer.parseInt(rank));
			rank_des = rank_val.get(0);

		}
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		ArrayList<ArrayList<String>> list_serving = IDAO.get3008Report_mns(month, year, hd_cmd_sus, hd_corp_sus,
				hd_div_sus, hd_bde_sus, unit_sus_no,line_dte1);
		Mmap.put("list", list_serving);

		Mmap.put("month1", month);
		Mmap.put("year1", year);
//			 ArrayList<ArrayList<String>> list_auth_held =	IDAO.iaff_Report_3008_Auth_Held(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,rank_des);
//			 Mmap.put("list5", list_auth_held);	
//			 Mmap.put("size4", list_auth_held.size());

		// ----
		Mmap.put("getCommandList", m.getCommandDetailsList());

		

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));

				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", formation_code);
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);
				// Mmap.put("getCommandList",comd);
				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Corps")) {

				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);
				Mmap.put("cmd_sus", command);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);
				Mmap.put("corp_sus", cor);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);
				Mmap.put("div_sus", div);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);
				Mmap.put("bde_sus", bde_code);
			}
		}

		if (roleAccess.equals("Unit")) {
			 roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));

			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);

			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
			Mmap.put("getCommandList", comd);
			Mmap.put("cmd_sus", command);
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
			Mmap.put("getCorpsList", corps);
			Mmap.put("corp_sus", cor);
			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
					+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
					+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
			Mmap.put("getDivList", Bn);
			Mmap.put("div_sus", div);
			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
			Mmap.put("getBdeList", bde);
			Mmap.put("bde_sus", bde_code);

		}

       if (roleAccess.equals("Line_dte")) {
			roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("line_dte_sus_no", roleSusNo);
		}
       if (!line_dte1.equals("") && !line_dte1.equals("0")) {
    		Mmap.put("line_dte_sus_no", line_dte1);
   		}
		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter") || roleAccess.equals("DGMS")) {
			List<Tbl_CodesForm> comd = m.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value='0'>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
		}
		Mmap.put("sus_no", unit_sus_no);
		Mmap.put("cmd_sus", hd_cmd_sus);
		Mmap.put("corp_sus", hd_corp_sus);
		Mmap.put("div_sus", hd_div_sus);
		Mmap.put("bde_sus", hd_bde_sus);
		Mmap.put("getLineDteList", iut.getLineDteList());
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		return new ModelAndView("IAFF_3008_Query_mns_Tiles");
	}

}
