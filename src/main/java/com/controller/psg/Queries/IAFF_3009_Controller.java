package com.controller.psg.Queries;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

import com.controller.ExportFile.Excel_To_Export_4_Table_Report;
import com.controller.ExportFile.Excel_To_Export_IAFF_3009_Report;

import com.controller.ExportFile.PDF_blood_group_query;
import com.controller.ExportFile.Report_3009_Pdf;
import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.IUT_Controller;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.commisionDao;
import com.dao.psg.Queries.IAFF_3008DAO;
import com.dao.psg.Queries.IAFF_3008_REPORT_DAO;
import com.dao.psg.Queries.IAFF_3009_REPORT_DAO;
import com.dao.psg.Report.ReportSearch_3009DAO;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tbl_CodesForm;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN_DETAILS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class IAFF_3009_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	IUT_Controller iut = new IUT_Controller();
	@Autowired
	private IAFF_3009_REPORT_DAO IDAO;

	@Autowired
	private IAFF_3008DAO IAFFDAO;
	
	@Autowired
	private ReportSearch_3009DAO search_3009DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/IAFF_3009_Query", method = RequestMethod.GET)
	public ModelAndView IAFF_3008_Query_mns(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3009_Query", roleid);
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
		return new ModelAndView("IAFF_3009_Query_Tiles");
	}

	//// -----------TABLE REPORT
//it is old logic logic change by poonam ma'am 02/09/24
//	@RequestMapping(value = "/admin/GetI3009_Serving_old", method = RequestMethod.POST)
//	public ModelAndView GetI3009_Serving_old(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
//			HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(value = "msg", required = false) String msg,
//			@RequestParam(value = "month1", required = false) String month,
//			@RequestParam(value = "year1", required = false) String year,
//			@RequestParam(value = "hd_cmd_sus1", required = false) String hd_cmd_sus,
//			@RequestParam(value = "hd_corp_sus1", required = false) String hd_corp_sus,
//			@RequestParam(value = "hd_div_sus1", required = false) String hd_div_sus,
//			@RequestParam(value = "hd_bde_sus1", required = false) String hd_bde_sus,
//			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
//			@RequestParam(value = "rank1", required = false) String rank)
//			throws ServletException, IOException, ParseException {
//
//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("IAFF_3009_Query", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
//
//		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
//
//		String for_code = "";
//		String cmd_sus = "";
//		String corp_sus = "";
//		String div_sus = "";
//		String bde_sus = "";
//		String rank_des = "";
//		if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
//			for_code = hd_cmd_sus;
//			List<String> comd = getFormationListSus_no("Command", for_code);
//			if (comd.size() > 0)
//				cmd_sus = comd.get(0);
//		}
//		if (!hd_corp_sus.equals("0") && !hd_corp_sus.equals("")) {
//			for_code = hd_corp_sus;
//			List<String> cor = getFormationListSus_no("Corps", for_code);
//			if (cor.size() > 0)
//				corp_sus = cor.get(0);
//		}
//		if (!hd_div_sus.equals("0") && !hd_div_sus.equals("")) {
//			for_code = hd_div_sus;
//			List<String> div = getFormationListSus_no("Division", for_code);
//			if (div.size() > 0)
//				div_sus = div.get(0);
//		}
//		if (!hd_bde_sus.equals("0") && !hd_bde_sus.equals("")) {
//			for_code = hd_bde_sus;
//			List<String> bde = getFormationListSus_no("Brigade", for_code);
//			if (bde.size() > 0)
//				bde_sus = bde.get(0);
//		}
//
//		if (!rank.equals("0") && !rank.equals("") && !rank.equals("null") && !rank.equals(null)) {
//			List<String> rank_val = getRankDescription(Integer.parseInt(rank));
//			rank_des = rank_val.get(0);
//
//		}
//
//
//		ArrayList<ArrayList<String>> list_auth_str_jco_or = IDAO.iaff3009_Report_auth_str_jco_or(month, year, cmd_sus,
//				corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list", list_auth_str_jco_or);
//		Mmap.put("size", list_auth_str_jco_or.size());
//		
//		//---column total iaff3009_Report_auth_str_jco_or---//
//		
//				int offrstotal = 0;
//				int mnsoffrstotal = 0;
//				int jcostotal = 0;
//				int ortotal = 0;
//
//				for (ArrayList<String> item : list_auth_str_jco_or) {
//				  int offrsValue = Integer.parseInt(item.get(1));
//				  int mnsoffrsValue = Integer.parseInt(item.get(2));
//				  int jcoValue = Integer.parseInt(item.get(3));
//				  int orValue = Integer.parseInt(item.get(4));
//				  
//				  offrstotal += offrsValue;
//				  mnsoffrstotal += mnsoffrsValue;
//				  jcostotal += jcoValue;
//				  ortotal += orValue;
//				}
//
//				
//				Mmap.put("offrstotal", offrstotal);
//				Mmap.put("mnsoffrstotal", mnsoffrstotal);
//				Mmap.put("jcostotal", jcostotal);
//				Mmap.put("ortotal", ortotal);
//				
//				//--------end-----------//
//
//		ArrayList<ArrayList<String>> list_auth_civ = IDAO.iaff3009_Report_auth_civ(month, year, cmd_sus, corp_sus,
//				div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list2", list_auth_civ);
//		Mmap.put("size2", list_auth_civ);
//
//		ArrayList<ArrayList<String>> list_posted_offrs_jco_or = IDAO.iaff3009_Report_posted_offrs_jco_or(month, year,
//				cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list3", list_posted_offrs_jco_or);
//		Mmap.put("size3", list_posted_offrs_jco_or);
//		
//		//---column total  iaff3009_Report_posted_offrs_jco_or---//
//		
//				int brig_and_above_offrs = 0;
//				int col_offrs = 0;
//				int col_ts_offrs = 0;
//				int lt_col_offrs = 0;
//				int maj_offrs = 0;
//				int capt_lt_offrs = 0;
//				int brig_and_above_mns_offrs = 0;
//				int col_mns_offrs = 0;
//				int lt_col_mns_offrs = 0;
//				int maj_mns_offrs = 0;
//				int capt_lt_mns_offrs = 0;
//				int sub_major_jco = 0;
//				int sub_jco = 0;
//				int nb_sub_jco = 0;
//				int warrant_officer_jco = 0;
//				int hav_or = 0;
//				int nk_or = 0;
//				int lnk_sep_or = 0;
//				int rects_or = 0;
//
//				for (ArrayList<String> item : list_posted_offrs_jco_or) {
//				       int brig_and_above_offrsval = Integer.parseInt(item.get(1));
//						int col_offrsval = Integer.parseInt(item.get(2));
//						int col_ts_offrsval = Integer.parseInt(item.get(3));
//						int lt_col_offrsval = Integer.parseInt(item.get(4));
//						int maj_offrsval = Integer.parseInt(item.get(5));
//						int capt_lt_offrsval = Integer.parseInt(item.get(6));
//						int brig_and_above_mns_offrsval = Integer.parseInt(item.get(7));
//						int col_mns_offrsval = Integer.parseInt(item.get(8));
//						int lt_col_mns_offrsval = Integer.parseInt(item.get(9));
//						int maj_mns_offrsval = Integer.parseInt(item.get(10));
//						int capt_lt_mns_offrsval = Integer.parseInt(item.get(11));
//						int sub_major_jcoval = Integer.parseInt(item.get(12));
//						int sub_jcoval = Integer.parseInt(item.get(13));
//						int nb_sub_jcoval = Integer.parseInt(item.get(14));
//						int warrant_officer_jcoval = Integer.parseInt(item.get(15));
//						int hav_orval = Integer.parseInt(item.get(16));
//						int nk_orval = Integer.parseInt(item.get(17));
//						int lnk_sep_orval = Integer.parseInt(item.get(18));
//						int rects_orval = Integer.parseInt(item.get(19));
//				  
//						     brig_and_above_offrs += brig_and_above_offrsval ;
//							 col_offrs += col_offrsval ;
//							 col_ts_offrs += col_ts_offrsval ;
//							 lt_col_offrs += lt_col_offrsval ;
//							 maj_offrs += maj_offrsval ;
//							 capt_lt_offrs += capt_lt_offrsval ;
//							 brig_and_above_mns_offrs += brig_and_above_mns_offrsval ;
//							 col_mns_offrs += col_mns_offrsval ;
//							 lt_col_mns_offrs += lt_col_mns_offrsval ;
//							 maj_mns_offrs += maj_mns_offrsval ;
//							 capt_lt_mns_offrs += capt_lt_mns_offrsval ;
//							 sub_major_jco += sub_major_jcoval ;
//							 sub_jco += sub_jcoval ;
//							 nb_sub_jco += nb_sub_jcoval ;
//							 warrant_officer_jco += warrant_officer_jcoval ;
//							 hav_or +=hav_orval  ;
//							 nk_or += nk_orval ;
//							 lnk_sep_or += lnk_sep_orval ;
//							 rects_or += rects_orval ;
//				}
//
//				
//				   Mmap.put("brig_and_above_offrs",brig_and_above_offrs);
//					Mmap.put("col_offrs",col_offrs);
//					Mmap.put("col_ts_offrs",col_ts_offrs);
//					Mmap.put("lt_col_offrs",lt_col_offrs);
//					Mmap.put("maj_offrs",maj_offrs);
//					Mmap.put("capt_lt_offrs",capt_lt_offrs);
//					Mmap.put("brig_and_above_mns_offrs",brig_and_above_mns_offrs);
//					Mmap.put("col_mns_offrs",col_mns_offrs);
//					Mmap.put("lt_col_mns_offrs",lt_col_mns_offrs);
//					Mmap.put("maj_mns_offrs",maj_mns_offrs);
//					Mmap.put("capt_lt_mns_offrs",capt_lt_mns_offrs);
//					Mmap.put("sub_major_jco",sub_major_jco);
//					Mmap.put("sub_jco",sub_jco);
//					Mmap.put("nb_sub_jco",nb_sub_jco);
//					Mmap.put("warrant_officer_jco",warrant_officer_jco);
//					Mmap.put("hav_or",hav_or);
//					Mmap.put("nk_or",nk_or);
//					Mmap.put("lnk_sep_or",lnk_sep_or);
//					Mmap.put("rects_or",rects_or);
//				
//				//--------end-----------//
//
//		ArrayList<ArrayList<String>> list_posted_civ = IDAO.iaff3009_Report_posted_civ(month, year, cmd_sus, corp_sus,
//				div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list4", list_posted_civ);
//		Mmap.put("size4", list_posted_civ);
//
//		// Mmap.put("month1", month);
//		// Mmap.put("year1", year);
//		ArrayList<ArrayList<String>> list_rank_and_trade_wise_jco_or = IDAO.iaff3009_Report_rank_and_trade_wise_jco_or(
//				month, year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list5", list_rank_and_trade_wise_jco_or);
//		Mmap.put("size5", list_rank_and_trade_wise_jco_or.size());
//
//		ArrayList<ArrayList<String>> list_religious_denomination = IDAO.iaff3009_Report_religious_denomination(month,
//				year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		Mmap.put("list6", list_religious_denomination);
//		Mmap.put("size6", list_religious_denomination.size());
//		
//		//---column total  iaff3009_Report_religious_denomination---//
//		
//				int jcos_posted_str_incl_ere_pers = 0;
//				int or_posted_str_incl_ere_pers = 0;
//				int held_religious_teacher = 0;
//				int auth_religious_teacher = 0;
//				
//
//				for (ArrayList<String> item : list_religious_denomination) {
//				       int jcos_posted_str_incl_ere_persval = Integer.parseInt(item.get(2));
//						int or_posted_str_incl_ere_persval = Integer.parseInt(item.get(3));
//						int held_religious_teacherval = Integer.parseInt(item.get(4));
//						int auth_religious_teacherval = Integer.parseInt(item.get(5));
//						
//				  
//						jcos_posted_str_incl_ere_pers += jcos_posted_str_incl_ere_persval ;
//						or_posted_str_incl_ere_pers += or_posted_str_incl_ere_persval ;
//						held_religious_teacher += held_religious_teacherval ;
//						auth_religious_teacher += auth_religious_teacherval ;
//							
//				}
//
//				
//				   Mmap.put("jcos_posted_str_incl_ere_pers",jcos_posted_str_incl_ere_pers);
//					Mmap.put("or_posted_str_incl_ere_pers",or_posted_str_incl_ere_pers);
//					Mmap.put("held_religious_teacher",held_religious_teacher);
//					Mmap.put("auth_religious_teacher",auth_religious_teacher);
//					
//				
//		ArrayList<ArrayList<String>> list_summary = IDAO.iaff3009_Report_summary(month, year, cmd_sus, corp_sus,
//				div_sus, bde_sus, unit_sus_no);
//		Mmap.put("list7", list_summary);
//		Mmap.put("size7", list_summary.size());
//
//		// ----
//		Mmap.put("getCommandList", m.getCommandDetailsList());
//
//		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
//		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
//
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
//
//		if (roleAccess.equals("Formation")) {
//			if (roleSubAccess.equals("Command")) {
//				String formation_code = String.valueOf(roleFormationNo.charAt(0));
//
//				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", formation_code);
//				Mmap.put("getCommandList", comd);
//				Mmap.put("cmd_sus", formation_code);
//				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", formation_code);
//				Mmap.put("getCorpsList", corps);
//				// Mmap.put("getCommandList",comd);
//				String select = "<option value='0'>--Select--</option>";
//				Mmap.put("selectcorps", select);
//				Mmap.put("selectDiv", select);
//				Mmap.put("selectBde", select);
//			}
//			if (roleSubAccess.equals("Corps")) {
//
//				String command = String.valueOf(roleFormationNo.charAt(0));
//				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
//				Mmap.put("getCommandList", comd);
//				Mmap.put("cmd_sus", command);
//
//				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//						+ String.valueOf(roleFormationNo.charAt(2));
//				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
//				Mmap.put("getCorpsList", corps);
//				Mmap.put("corp_sus", cor);
//
//				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", cor);
//				Mmap.put("getDivList", Bn);
//
//				String select = "<option value='0'>--Select--</option>";
//				Mmap.put("selectDiv", select);
//				Mmap.put("selectBde", select);
//			}
//			if (roleSubAccess.equals("Division")) {
//				String command = String.valueOf(roleFormationNo.charAt(0));
//				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
//				Mmap.put("getCommandList", comd);
//				Mmap.put("cmd_sus", command);
//
//				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//						+ String.valueOf(roleFormationNo.charAt(2));
//				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
//				Mmap.put("getCorpsList", corps);
//				Mmap.put("corp_sus", cor);
//
//				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
//						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
//				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
//				Mmap.put("getDivList", Bn);
//				Mmap.put("div_sus", div);
//
//				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", div);
//				Mmap.put("getBdeList", bde);
//
//				String select = "<option value='0'>--Select--</option>";
//				Mmap.put("selectBde", select);
//			}
//			if (roleSubAccess.equals("Brigade")) {
//				String command = String.valueOf(roleFormationNo.charAt(0));
//				List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
//				Mmap.put("getCommandList", comd);
//				Mmap.put("cmd_sus", command);
//
//				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//						+ String.valueOf(roleFormationNo.charAt(2));
//				List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
//				Mmap.put("getCorpsList", corps);
//				Mmap.put("corp_sus", cor);
//
//				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
//						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
//				List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
//				Mmap.put("getDivList", Bn);
//				Mmap.put("div_sus", div);
//
//				String bde_code = roleFormationNo;
//				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
//				Mmap.put("getBdeList", bde);
//				Mmap.put("bde_sus", bde_code);
//			}
//		}
//
//		if (roleAccess.equals("Unit")) {
//			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
//			Mmap.put("sus_no", roleSusNo);
//			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
//
//			List<String> formation = mcommon.getformationfromSus_NOList(roleSusNo);
//			roleFormationNo = formation.get(0);
//
//			String command = String.valueOf(roleFormationNo.charAt(0));
//			List<Tbl_CodesForm> comd = bloodGP.getFormationList("Command", command);
//			Mmap.put("getCommandList", comd);
//			Mmap.put("cmd_sus", command);
//			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//					+ String.valueOf(roleFormationNo.charAt(2));
//			List<Tbl_CodesForm> corps = bloodGP.getFormationList("Corps", cor);
//			Mmap.put("getCorpsList", corps);
//			Mmap.put("corp_sus", cor);
//			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
//					+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
//					+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
//			List<Tbl_CodesForm> Bn = bloodGP.getFormationList("Division", div);
//			Mmap.put("getDivList", Bn);
//			Mmap.put("div_sus", div);
//			String bde_code = roleFormationNo;
//			List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade", bde_code);
//			Mmap.put("getBdeList", bde);
//			Mmap.put("bde_sus", bde_code);
//
//		}
//		if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
//			List<Tbl_CodesForm> comd = m.getCommandDetailsList();
//			Mmap.put("getCommandList", comd);
//			String selectComd = "<option value='0'>--Select--</option>";
//			String select = "<option value='0'>--Select--</option>";
//			Mmap.put("selectcomd", selectComd);
//			Mmap.put("selectcorps", select);
//			Mmap.put("selectDiv", select);
//			Mmap.put("selectBde", select);
//		}
//		Mmap.put("sus_no", unit_sus_no);
//		Mmap.put("cmd_sus", hd_cmd_sus);
//		Mmap.put("corp_sus", hd_corp_sus);
//		Mmap.put("div_sus", hd_div_sus);
//		Mmap.put("bde_sus", hd_bde_sus);
//		Mmap.put("msg", msg);
//		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
//		return new ModelAndView("IAFF_3009_Query_Tiles");
//	}
	
	
	@RequestMapping(value = "/admin/GetI3009_Serving", method = RequestMethod.POST)
	public ModelAndView GetI3009_Serving(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
		Boolean val = roledao.ScreenRedirect("IAFF_3009_Query", roleid);
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

		/*
		 * if (!rank.equals("0") && !rank.equals("") && !rank.equals("null") &&
		 * !rank.equals(null)) { List<String> rank_val =
		 * getRankDescription(Integer.parseInt(rank)); rank_des = rank_val.get(0);
		 * 
		 * }
		 */

					
				
		ArrayList<ArrayList<String>> list = IDAO.get3009Report(month, year, hd_cmd_sus, hd_corp_sus,
				hd_div_sus, hd_bde_sus, unit_sus_no,line_dte1);
		Mmap.put("list", list);

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
		
	      if (roleAccess.equals("Line_dte")) {
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					Mmap.put("line_dte_sus_no", roleSusNo);
				}
		       if (!line_dte1.equals("") && !line_dte1.equals("0")) {
		    		Mmap.put("line_dte_sus_no", line_dte1);
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
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("cmd_sus", hd_cmd_sus);
		Mmap.put("corp_sus", hd_corp_sus);
		Mmap.put("div_sus", hd_div_sus);
		Mmap.put("bde_sus", hd_bde_sus);
		Mmap.put("line_dte", line_dte1);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("getLineDteList", iut.getLineDteList());
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		return new ModelAndView("IAFF_3009_Query_Tiles");
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

//	 //------------------PRINT DOWNLOAD  
//	 

@RequestMapping(value = "/Download_IAFF3009_query", method = RequestMethod.POST)
	public ModelAndView Download_IAFF3009_query(@ModelAttribute("cont_comd_tx") String cont_comd_tx,
			@ModelAttribute("cont_corps_tx") String cont_corps_tx, @ModelAttribute("cont_div_tx") String cont_div_tx,
			@ModelAttribute("cont_bde_tx") String cont_bde_tx,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "hd_cmd_sus2", required = false) String hd_cmd_sus,
			@RequestParam(value = "hd_corp_sus2", required = false) String hd_corp_sus,
			@RequestParam(value = "hd_div_sus2", required = false) String hd_div_sus,
			@RequestParam(value = "hd_bde_sus2", required = false) String hd_bde_sus,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,	
			@RequestParam(value = "version2", required = false) String version, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		/*String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3009_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}*/

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
		ArrayList<ArrayList<String>> list_auth_str_jco_or = search_3009DAO.Search_Report_auth_str_jco_or_3009(month,
				year, sessionEdit, version, unit_sus_no);

		ArrayList<ArrayList<String>> list_auth_civ = search_3009DAO.Search_Report_auth_civ_3009(month, year,
				sessionEdit, version, unit_sus_no);

		ArrayList<ArrayList<String>> list_posted_offrs_jco_or = search_3009DAO
				.Search_Report_posted_offrs_jco_or_3009(month, year, sessionEdit, version, unit_sus_no);

		ArrayList<ArrayList<String>> list_posted_civ = search_3009DAO.Search_Report_posted_civ_300(month, year,
				sessionEdit, version, unit_sus_no);

		ArrayList<ArrayList<String>> list_rank_and_trade_wise_jco_or = search_3009DAO
				.Search_Report_rank_and_trade_wise_jco_or_3009(month, year, sessionEdit, version, unit_sus_no);

//		ArrayList<ArrayList<String>> list_religious_denomination = search_3009DAO
//				.Search_Report_religious_denomination_3009(month, year, sessionEdit, version, unit_sus_no);
		
		
//		pranay 26.09 (total in pdf)
		
		ArrayList<ArrayList<String>> list_religious_denomination = search_3009DAO
		        .Search_Report_religious_denomination_3009(month, year, sessionEdit, version, unit_sus_no);

		
		ArrayList<ArrayList<String>> combinedList = new ArrayList<>();

		
		int jcos_posted_str_incl_ere_pers = 0;
		int or_posted_str_incl_ere_pers = 0;
		int held_religious_teacher = 0;
		int auth_religious_teacher = 0;

		
		for (ArrayList<String> item : list_religious_denomination) {
		    
		    if (item.size() >= 6) {
		        
		        combinedList.add(new ArrayList<>(item)); 

		        // Parse values and accumulate sums
		        int jcos_posted_str_incl_ere_persval = Integer.parseInt(item.get(2));
		        int or_posted_str_incl_ere_persval = Integer.parseInt(item.get(3));
		        int auth_religious_teacherval = Integer.parseInt(item.get(4));
		        int held_religious_teacherval = Integer.parseInt(item.get(5));

		        jcos_posted_str_incl_ere_pers += jcos_posted_str_incl_ere_persval;
		        or_posted_str_incl_ere_pers += or_posted_str_incl_ere_persval;
		        held_religious_teacher += held_religious_teacherval;
		        auth_religious_teacher += auth_religious_teacherval;
		    }
		}

		
		ArrayList<String> combinedRow = new ArrayList<>();
		combinedRow.add(""); 
		combinedRow.add("Total"); 
		combinedRow.add(String.valueOf(jcos_posted_str_incl_ere_pers));
		combinedRow.add(String.valueOf(or_posted_str_incl_ere_pers));
		combinedRow.add(String.valueOf(auth_religious_teacher));
		combinedRow.add(String.valueOf(held_religious_teacher));

		
		combinedList.add(combinedRow);

//		end pranay 26.09 (total in pdf)
		
		
		

		ArrayList<ArrayList<String>> list_Summary = search_3009DAO.Search_Report_summary(month, year, sessionEdit,
				version, unit_sus_no);
	
		
	      ArrayList<ArrayList<String>> list_Main = IDAO.iaff3009_Report_MainDetails(month,year,version,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no);
	      ArrayList<ArrayList<String>> remarks = IDAO.iaff3009_Report_getremarks(month,year,version,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no);
		
	      String hd_remarks1 = "";
			
			String hd_remarks2 ="";
	      if (remarks != null) {
	    	    ArrayList<String> remarks1List = remarks.get(0);	    	  
	    	     hd_remarks1 = remarks1List.get(0);
	    	    hd_remarks2 = remarks1List.get(1); 	    	    
	    	}
        
//	  	ArrayList<ArrayList<String>> list_auth_str_jco_or = IDAO.iaff3009_Report_auth_str_jco_or(month, year, cmd_sus,corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_auth_civ = IDAO.iaff3009_Report_auth_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_posted_offrs_jco_or = IDAO.iaff3009_Report_posted_offrs_jco_or(month, year,cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_posted_civ = IDAO.iaff3009_Report_posted_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_rank_and_trade_wise_jco_or = IDAO.iaff3009_Report_rank_and_trade_wise_jco_or(	month, year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_religious_denomination = IDAO.iaff3009_Report_religious_denomination(month,year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
//		ArrayList<ArrayList<String>> list_Main = IDAO.iaff3009_Report_MainDetails(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no);
//		ArrayList<ArrayList<String>> list_Summary = IDAO.iaff3009_Report_summary(month,year,cmd_sus,corp_sus,div_sus,bde_sus,unit_sus_no);
        


		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);

		Date date = new Date();
		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		// if(pdfprint.size() > 0) {
		List<String> TH = new ArrayList<String>();
		TH.add("Arm/ Service");
		TH.add("Offrs");
		TH.add("MNS Offrs");
		TH.add("JCOs");
		TH.add("ORs");		
		

		List<String> TH1 = new ArrayList<String>();
		TH1.add("GP 'A' Gazetted");
		TH1.add("GP 'B' Gazetted");
		TH1.add("GP 'B' Non Gazetted");
		TH1.add("GP 'C' Non Gazetted");
		TH1.add("Remarks");
		
		List<String> TH2 = new ArrayList<String>();
		TH2.add("Arm/ Service");
		TH2.add("Brig and Above");
		TH2.add("Col");
		TH2.add("Col (TS)");
		TH2.add("Lt Col");
		TH2.add("Maj");
		TH2.add("Capt/Lt");
		TH2.add("Brig and Above (MNS)");
		TH2.add("Col (MNS)");
		TH2.add("Lt Col (S) (MNS)");
		TH2.add("Lt Col (TS) (MNS)");
		TH2.add("Maj (MNS)");
		TH2.add("Capt/Lt (MNS)");
		TH2.add("Sub Maj");
		TH2.add("Sub");
		TH2.add("Nb Sub");
		TH2.add("Warrant Offr");
		TH2.add("Hav");
		TH2.add("Nk");
		TH2.add("Sep");
		TH2.add("Rects");

		List<String> TH3 = new ArrayList<String>();
		TH3.add("GP 'A' Gazetted");
		TH3.add("GP 'B' Gazetted");
		TH3.add("GP 'B' Non Gazetted");
		TH3.add("GP 'C' Non Gazetted");
		TH3.add("Remarks");

		List<String> TH4 = new ArrayList<String>();
		TH4.add("Arm/ Service");
		TH4.add("Trade");
		TH4.add("Sub Maj ");
		TH4.add("Sub");
		TH4.add("Nb Sub");
		TH4.add("Warrant Offr");
		TH4.add("Hav");
		TH4.add("Nk");
		TH4.add("Sep");
		TH4.add("Rects");
		TH4.add("Total");
//			TH4.add("Total Held");


		List<String> TH5 = new ArrayList<String>();
		TH5.add("Arm/ Service");
		TH5.add("Religion");
		TH5.add("JCO Posted Incl ERE ");
		TH5.add("OR Posted Incl ERE");
		TH5.add("Auth Religious Teacher");
		TH5.add("Held Religious Teacher");
		
		
		
		List<String> TH6 = new ArrayList<String>();
		TH6.add("Offrs Auth");
		TH6.add("Offrs Over And Above");
		TH6.add("Offrs Posted");	
		TH6.add("Offrs Sur");
		TH6.add("Offrs Def");
		TH6.add("Offrs Auth No");
		TH6.add("Offrs MNS Auth");
		TH6.add("Offrs MNS Over And Above");
		TH6.add("Offrs MNS Posted");	
		TH6.add("Offrs MNS Sur");
		TH6.add("Offrs MNS Def");
		TH6.add("Offrs MNS Auth No");
		TH6.add("JCO Auth");
		TH6.add("JCO Over And Above");
		TH6.add("JCO Posted");
		TH6.add("JCO Sur");
		TH6.add("JCO Def");
		TH6.add("JCO Auth No");
		TH6.add("OR Auth");
		TH6.add("OR Over And Above");
		TH6.add("OR Posted");
		TH6.add("OR Sur");
		TH6.add("OR Def");
		TH6.add("OR Auth No");
		TH6.add("Civ Auth");
		TH6.add("Civ Over And Above");
		TH6.add("Civ Posted");
		TH6.add("Civ Sur");
		TH6.add("Civ Def");
		TH6.add("Civ Auth No");
		
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
				new Report_3009_Pdf(TH, TH1, TH2,TH3,TH4,TH5,TH6, foot, username, list_auth_str_jco_or, list_auth_civ, list_posted_offrs_jco_or, list_posted_civ,
						list_rank_and_trade_wise_jco_or,combinedList,list_Summary,list_Main, cont_comd_tx, cont_corps_tx, cont_div_tx, cont_bde_tx, 
						hd_remarks1, hd_remarks2),
				"userList", list_auth_str_jco_or);
	}

	@RequestMapping(value = "/Excel_IAFF_3009_query", method = RequestMethod.POST)
	public ModelAndView Excel_IAFF_3009_query(HttpServletRequest request, ModelMap model, HttpSession session,
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
		Boolean val = roledao.ScreenRedirect("IAFF_3009_Query", roleid);
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
		ArrayList<ArrayList<String>> Excel = IDAO.iaff3009_Report_auth_str_jco_or(month, year, cmd_sus,corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel1 = IDAO.iaff3009_Report_auth_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel2 = IDAO.iaff3009_Report_posted_offrs_jco_or(month, year,cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel3 = IDAO.iaff3009_Report_posted_civ(month, year, cmd_sus, corp_sus,div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel4 = IDAO.iaff3009_Report_rank_and_trade_wise_jco_or(month, year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel5 = IDAO.iaff3009_Report_religious_denomination(month,year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no, rank_des);
		ArrayList<ArrayList<String>> Excel6 = IDAO.iaff3009_Report_summary(month,year, cmd_sus, corp_sus, div_sus, bde_sus, unit_sus_no);

		

		List<String> TH = new ArrayList<String>();
		TH.add("arm_services");
		TH.add("offrs");
		TH.add("mns_offrs");
		TH.add("jcos");
		TH.add("ors");
		TH.add("remark");
		

		List<String> TH1 = new ArrayList<String>();
		TH1.add("gp_a_gazetted");
		TH1.add("gp_b_gazetted");
		TH1.add("gp_b_non_gazetted");
		TH1.add("gp_c_non_gazetted");
		TH1.add("remark");
		
		List<String> TH2 = new ArrayList<String>();
		TH2.add("arms_services");
		TH2.add("brig_and_above_offrs");
		TH2.add("col_offrs");
		TH2.add("col_ts_offrs");
		TH2.add("lt_col_offrs");
		TH2.add("maj_offrs");
		TH2.add("capt_lt_offrs");
		TH2.add("brig_and_above_mns_offrs");
		TH2.add("col_mns_offrs");
		TH2.add("lt_col_mns_offrs");
		TH2.add("maj_mns_offrs");
		TH2.add("capt_lt_mns_offrs");
		TH2.add("sub_major_jco");
		TH2.add("sub_jco");
		TH2.add("nb_sub_jco");
		TH2.add("warrant_officer_jco");
		TH2.add("hav_or");
		TH2.add("nk_or");
		TH2.add("lnk_sep_or");
		TH2.add("rects_or");

		List<String> TH3 = new ArrayList<String>();
		TH3.add("gp_a_gazetted");
		TH3.add("gp_b_gazetted");
		TH3.add("gp_b_non_gazetted");
		TH3.add("gp_c_non_gazetted");
		TH2.add("remark");

		List<String> TH4 = new ArrayList<String>();
		TH4.add("arms_services");
		TH4.add("trade");
		TH4.add("sub_maj_jco");
		TH4.add("sub_jco");
		TH4.add("nb_sub_jco");
		TH4.add("warrant_officer_jco");
		TH4.add("hav_or");
		TH4.add("nk_or");
		TH4.add("lnk_sep_or");
		TH4.add("rects_or");
		TH4.add("total");
//			TH4.add("Total Held");


		List<String> TH5 = new ArrayList<String>();
		TH5.add("religion");
		TH5.add("arms_services");
		TH5.add("jcos_posted_str_incl_ere_pers");
		TH5.add("or_posted_str_incl_ere_pers");
		TH5.add("held_religious_teacher");
		TH5.add("auth_religious_teacher");
		
		
		List<String> TH6 = new ArrayList<String>();
		TH6.add("offrs_auth");
		TH6.add("offrs_posted");
		TH6.add("offrs_sur");
		TH6.add("offrs_defi");
		TH6.add("jco_auth");
		TH6.add("jco_posted");
		TH6.add("jco_sur");
		TH6.add("jco_defi");
		TH6.add("or_auth");
		TH6.add("or_posted");
		TH6.add("or_sur");
		TH6.add("or_defi");
		TH6.add("civ_auth");
		TH6.add("civ_posted");
		TH6.add("civ_sur");
		TH6.add("civ_defi");

	
		String Heading = "\nNUMBERS OF PERS AGE/RELIGION/STATE WISE";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_IAFF_3009_Report("L", TH,TH1,TH2,TH3,TH4,TH5,TH6,Heading, username,Excel,Excel1,Excel2,Excel3,Excel4,Excel5,Excel6), "userList", Excel );	
		
	}

}
