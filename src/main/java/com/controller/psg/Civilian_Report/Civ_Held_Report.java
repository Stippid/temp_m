package com.controller.psg.Civilian_Report;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Excel_To_Export_civilian_Report;
import com.controller.ExportFile.Report_civilian_Pdf;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Report.held_civ_report_Dao;
import com.dao.psg.Civilian_Report.held_str_civ_report_nomi_Dao;
import com.models.Tbl_CodesForm;
import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_TRADE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Civ_Held_Report {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private held_civ_report_Dao IDAO;
	
	
	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller(); 
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	

	
	@RequestMapping(value = "/admin/held_civ_query_report", method = RequestMethod.GET)
	public ModelAndView held_civ_query_report(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("held_civ_query_report", roleid);
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
		return new ModelAndView("held_str_civ_Tiles");
	}
	
	
	@RequestMapping(value = "/admin/Getciv_Serving", method = RequestMethod.POST)
	public ModelAndView Getciv_Serving(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "hd_cmd_sus1", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus1", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus1", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus1", required = false) String hd_bde_sus,
			@RequestParam(value = "civilian_status1", required = false) String civilian_status,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "rank1", required = false) String rank)
			throws ServletException, IOException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("held_civ_query_report", roleid);
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

		ArrayList<ArrayList<String>> list_civ_nominalroll = IDAO.Report_civ_nominalroll(month, year, cmd_sus,
				corp_sus, div_sus, bde_sus, unit_sus_no,civilian_status);
		Mmap.put("list", list_civ_nominalroll);
		Mmap.put("size", list_civ_nominalroll.size());	
		
		if(civilian_status.equals("R")) {		
		ArrayList<ArrayList<String>> list_auth_civ = IDAO.Report_auth_civ(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no, civilian_status);
		Mmap.put("list2", list_auth_civ);
		Mmap.put("size2", list_auth_civ);

	
		ArrayList<ArrayList<String>> list_posted_civ = IDAO.Report_posted_civ(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no,civilian_status);
		Mmap.put("list4", list_posted_civ);
		Mmap.put("size4", list_posted_civ);
		
		
		ArrayList<ArrayList<String>> list_summary = IDAO.Report_summary_civ(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no,civilian_status);
		Mmap.put("list7", list_summary);
		Mmap.put("size7", list_summary.size());

		}
		else {		
		ArrayList<ArrayList<String>> list_non_regular_civ = IDAO.Report_non_regular_civ(month, year, cmd_sus, corp_sus,
				div_sus, bde_sus, unit_sus_no,civilian_status);
		Mmap.put("list8", list_non_regular_civ);
		Mmap.put("size8", list_non_regular_civ.size());
		}
		// ----
		Mmap.put("getCommandList", m.getCommandDetailsList());

		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

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
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
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
		Mmap.put("sus_no", unit_sus_no);
		Mmap.put("cmd_sus", hd_cmd_sus);
		Mmap.put("corp_sus", hd_corp_sus);
		Mmap.put("div_sus", hd_div_sus);
		Mmap.put("bde_sus", hd_bde_sus);
		Mmap.put("civilian_status",civilian_status);
		Mmap.put("msg", msg);		
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		return new ModelAndView("held_str_civ_Tiles");
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

	
	@RequestMapping(value = "/Download_civ_query", method = RequestMethod.POST)
	public ModelAndView Download_civ_query(@ModelAttribute("cont_comd_tx") String cont_comd_tx,
			@ModelAttribute("cont_corps_tx") String cont_corps_tx, @ModelAttribute("cont_div_tx") String cont_div_tx,
			@ModelAttribute("cont_bde_tx") String cont_bde_tx,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "hd_cmd_sus2", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus2", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus2", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus2", required = false) String hd_bde_sus,
			@RequestParam(value = "civilian_status2", required = false) String civilian_status,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,
			@RequestParam(value = "hd_remarks1", required = false) String hd_remarks1,
			@RequestParam(value = "hd_remarks2", required = false) String hd_remarks2,
			@RequestParam(value = "hd_remarks3", required = false) String hd_remarks3,
			@RequestParam(value = "hd_remarks4", required = false) String hd_remarks4,
			@RequestParam(value = "hd_remarks5", required = false) String hd_remarks5,
			@RequestParam(value = "hd_remarks6", required = false) String hd_remarks6,
			@RequestParam(value = "rank2", required = false) String rank, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("held_civ_query_report", roleid);
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

		ArrayList<ArrayList<String>> list_nominalroll = IDAO.Report_civ_nominalroll(month, year, cmd_sus,corp_sus, div_sus, bde_sus, unit_sus_no,civilian_status);
		ArrayList<ArrayList<String>> list_auth_civ = IDAO.Report_auth_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, civilian_status);
		ArrayList<ArrayList<String>> list_posted_civ = IDAO.Report_posted_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, civilian_status);
        ArrayList<ArrayList<String>> list_Summary = IDAO.Report_summary_civ(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,civilian_status);
        ArrayList<ArrayList<String>> list_Main = IDAO.Report_MainDetails_civ(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no);
        ArrayList<ArrayList<String>> list_non_regular_civ = IDAO.Report_non_regular_civ(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no,civilian_status);
        


		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);

		Date date = new Date();
		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		// if(pdfprint.size() > 0) {
		List<String> TH = new ArrayList<String>();
		TH.add("sr.no");
		TH.add("Employee No");
		TH.add("Full Name");
		TH.add("Designation");
		TH.add("DOB");
		TH.add("Classification OF Services");
		TH.add("Civ Group");
		TH.add("Civ Type");
		TH.add("Joining Date Of Govenment Service");
		TH.add("Date Of TOS");
		TH.add("Designation Date");
		TH.add("Gender");
		

		List<String> TH1 = new ArrayList<String>();
		TH1.add("group a gaz");
		TH1.add("group b gaz");
		TH1.add("group b non gaz non industrial");
		TH1.add("group c non gaz non industrial");
		TH1.add("group b non gaz industrial");
		TH1.add("group c non gaz industrial");
		TH1.add("Total");
		
		List<String> TH2 = new ArrayList<String>();
		TH2.add("group a gaz");
		TH2.add("group b gaz");
		TH2.add("group b non gaz non industrial");
		TH2.add("group c non gaz non industrial");
		TH2.add("group b non gaz industrial");
		TH2.add("group c non gaz industrial");
		TH2.add("Total");

		List<String> TH3 = new ArrayList<String>();
		TH3.add("Authenticate Civilian");
		TH3.add("Posted Civilian");
		TH3.add("Civ_Sur");
		TH3.add("Civ_Defi");
		
		List<String> TH4 = new ArrayList<String>();
		TH4.add("Industrial");
		TH4.add("Non Industrial");
		TH4.add("Total");
	

	
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
		
		return new ModelAndView(
				new Report_civilian_Pdf(TH, TH1, TH2,TH3,TH4, foot, username,civilian_status, list_nominalroll, list_auth_civ,list_posted_civ,list_Summary,list_non_regular_civ,list_Main, cont_comd_tx, cont_corps_tx, cont_div_tx, cont_bde_tx),
				"userList", list_nominalroll);
	}
	 

	
	@RequestMapping(value = "/Excel_civ_query", method = RequestMethod.POST)
	public ModelAndView Excel_civ_query(HttpServletRequest request, ModelMap model, HttpSession session,
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
			@RequestParam(value = "civilian_status3", required = false) String civilian_status,
			@RequestParam(value = "rank3", required = false) String rank, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("held_civ_query_report", roleid);
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
		ArrayList<ArrayList<String>> Excel = IDAO.Report_civ_nominalroll(month, year, cmd_sus,corp_sus, div_sus, bde_sus, unit_sus_no, civilian_status);
		ArrayList<ArrayList<String>> Excel1 = IDAO.Report_auth_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, civilian_status);		
		ArrayList<ArrayList<String>> Excel2 = IDAO.Report_posted_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, civilian_status);		
		ArrayList<ArrayList<String>> Excel3 = IDAO.Report_summary_civ(month,year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no,civilian_status);
		ArrayList<ArrayList<String>> Excel4 = IDAO.Report_non_regular_civ(month,year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no,civilian_status);

		

		List<String> TH = new ArrayList<String>();
		TH.add("sr.no");
		TH.add("Employee No");
		TH.add("Full Name");
		TH.add("Designation");
		TH.add("DOB");
		TH.add("Classification OF Services");
		TH.add("Civ Group");
		TH.add("Civ Type");
		TH.add("Joining Date Of Govenment Service");
		TH.add("Date Of TOS");
		TH.add("Designation Date");
		TH.add("Gender");	
		

		List<String> TH1 = new ArrayList<String>();
		TH1.add("group a gaz");
		TH1.add("group b gaz");
		TH1.add("group b non gaz non industrial");
		TH1.add("group c non gaz non industrial");
		TH1.add("group b non gaz industrial");
		TH1.add("group c non gaz industrial");
		TH1.add("Total");
		
	
		
		List<String> TH2 = new ArrayList<String>();
		TH2.add("group a gaz");
		TH2.add("group b gaz");
		TH2.add("group b non gaz non industrial");
		TH2.add("group c non gaz non industrial");
		TH2.add("group b non gaz industrial");
		TH2.add("group c non gaz industrial");
		TH2.add("Total");
		
		
		
		List<String> TH3 = new ArrayList<String>();
		TH3.add("Authenticate Civilian");
		TH3.add("Posted Civilian");
		TH3.add("civ_sur");
		TH3.add("civ_defi");	
		
		List<String> TH4 = new ArrayList<String>();
		TH4.add("Industrial");
		TH4.add("Non-Industrial");
		TH4.add("Total");
		
	
		String Heading = "\nNUMBERS OF PERS AGE/RELIGION/STATE WISE";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_civilian_Report("L", TH,TH1,TH2,TH3,TH4,Heading, username,civilian_status, Excel,Excel1,Excel2,Excel3,Excel4), "userList", Excel );	
		
	}

	
}

