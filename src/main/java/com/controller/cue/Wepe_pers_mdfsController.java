package com.controller.cue;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.models.Miso_Orbat_Unt_Dtl;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Wepe_pers_mdfsController {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Cue_wepe_conditionDAO vetting;	
	
	cueContoller M = new cueContoller();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/Wepe_pers_mdfsUrl", method = RequestMethod.GET)
	public ModelAndView Wepe_pers_mdfsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		
		Boolean val = roledao.ScreenRedirect("Wepe_pers_mdfsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("msg", msg);
		return new ModelAndView("Wepe_pers_mdfsTiles", "Wepe_pers_mdfsCmd", new CUE_TB_MISO_WEPE_PERS_MDFS());
	}

	@RequestMapping(value = "/admin/Wepe_pers_mdfsUrl1", method = RequestMethod.POST)
	public ModelAndView Wepe_pers_mdfsUrl1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "modification1", required = false) String modification,
			@RequestParam(value = "cat_per1", required = false) String cat_per,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "appt_trade1", required = false) String appt_trade,
			@RequestParam(value = "arm_code1", required = false) String arm_code,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,
			@RequestParam(value = "unit1", required = false) String unit,

			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "we_pe1", required = false) String we_pe) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		
		Mmap.put("we_pe1", we_pe);
		Mmap.put("scenario1", scenario);
		Mmap.put("location1", location);
		Mmap.put("formation1", formation);
		Mmap.put("unit1", unit);
		Mmap.put("location1_hid", location_code);
		Mmap.put("formation1_hid", formation_code);
		Mmap.put("unit1_hid", unit_code);
		Mmap.put("we_pe_no2", we_pe_no);
		Mmap.put("modification1", modification);
		Mmap.put("cat_per1", cat_per);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("appt_trade1", appt_trade);
		Mmap.put("arm_code1", arm_code);
		Mmap.put("rank1", rank);
		Mmap.put("status1", status);

		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());

		List<Map<String, Object>> list = wepepersmdfs.getSearchWEPEReportDetail1(we_pe_no, status, modification,
				cat_per, rank_cat, appt_trade, arm_code, rank, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());

		Mmap.put("roleType", roleType);
		return new ModelAndView("Wepe_pers_mdfsTiles");
	}

	@RequestMapping(value = "/Wepe_pers_mdfsAct", method = RequestMethod.POST)
	public ModelAndView Wepe_pers_mdfsAct(@ModelAttribute("Wepe_pers_mdfsCmd") CUE_TB_MISO_WEPE_PERS_MDFS rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String r = request.getParameter("radio_status");
		String amt = request.getParameter("amt_inc_dec");

		int roleid = (Integer) session.getAttribute("roleid");
		String radio_status = request.getParameter("radio_status");
		String we_pe = request.getParameter("we_pe");
		String location = request.getParameter("location_name");
		String scenario3 = request.getParameter("scenario");
		String formation = request.getParameter("formation_name");
		String unit = request.getParameter("scenario_unit_name");

		String location_code = request.getParameter("location");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("scenario_unit");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();

		String base_au = request.getParameter("base_autho_hidden");
		String we_pe_no = request.getParameter("we_pe_no");
		String cat_per = request.getParameter("cat_per");
		String rank_cat = request.getParameter("rank_cat");
		String rank = request.getParameter("rank");
		String arm_code = request.getParameter("arm_code");
		String appt_trade_code = request.getParameter("appt_trade");

		String appt_trade = request.getParameter("appt_trade_name");
		String mod = request.getParameter("modification");

		try {

			if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}

			if (rs.getWe_pe_no() == "") {
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (validation.checkWepeLength(rs.getWe_pe_no()) == false) {
				model.put("msg", validation.wepenoMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (rs.getScenario() == "") {
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (validation.checkScenarioLength(rs.getScenario()) == false) {
				model.put("msg", validation.senarioMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			String scenario2 = rs.getScenario();
			if (scenario2.equals("Location")) {
				if (rs.getLocation().equals("") || rs.getLocation() == "" || rs.getLocation() == null
						|| rs.getLocation().equals(null) || rs.getLocation().isEmpty()) {
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

				}
				if (validation.checkLocationLength(rs.getLocation()) == false) {
					model.put("msg", validation.locMSG);
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
				}

			}
			if (scenario2.equals("Formation")) {
				if (rs.getFormation().equals("") || rs.getFormation() == "" || rs.getFormation() == null
						|| rs.getFormation().equals(null) || rs.getFormation().isEmpty()) {
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
				}
				if (validation.checkFormationLength(rs.getFormation()) == false) {
					model.put("msg", validation.formMSG);
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
				}
			}

			if (scenario2.equals("Unit")) {
				if (rs.getScenario_unit().equals("") || rs.getScenario_unit() == "" || rs.getScenario_unit() == null
						|| rs.getScenario_unit().equals(null) || rs.getScenario_unit().isEmpty()) {
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
				}
				if (validation.sus_noLength(rs.getScenario_unit()) == false) {
					model.put("msg", validation.unitMSG);
					return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
				}
			}

			if (rs.getModification() == "") {
				model.put("msg", "Please Enter Modification");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (validation.checkModificationLength(rs.getModification()) == false) {
				model.put("msg", validation.modMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (rs.getCat_per() == "") {
				model.put("msg", "Please Select Category of Personnel");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (validation.checkCategory_of_personnelLength(rs.getCat_per()) == false) {
				model.put("msg", validation.catpersMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (rs.getCat_per() != "") {
				if (rs.getCat_per().equals("1")) {
					if (rs.getArm_code().equals("")) {
						model.put("msg", "Please Select Parent Arm");
						return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

					}
					String arm_codevalid = String.format("%04d", Integer.parseInt(rs.getArm_code()));
					if (validation.checkArmCodeLength(arm_codevalid) == false) {
						model.put("msg", validation.arm_codeMSG);
						return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
					}
				}
			}
			if (rs.getRank_cat().equals(" ")) {
				model.put("msg", "Please Select Category");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

			}
			if (validation.checkMonthLength(rs.getRank_cat()) == false) {
				model.put("msg", validation.rankcatMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (rs.getAppt_trade() == "") {
				model.put("msg", "Please Enter Common Appt/Trade");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

			}
		/*	if (validation.checkFormationLength(rs.getAppt_trade()) == false) {
				model.put("msg", validation.apptradeMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}*/
			//sana280623bisag
			if (validation.check20Length(rs.getAppt_trade()) == false) {
				model.put("msg", validation.apptradeMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			
			if (rs.getRank() == "" || rs.getRank() == null) {
				model.put("msg", "Please Select Rank");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

			}
			if (validation.checkFormationLength(rs.getRank()) == false) {
				model.put("msg", validation.rankMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (radio_status == "" || radio_status == null || radio_status == "null" || radio_status.equals(null)) {
				model.put("msg", "Please Select Increment/Decrement");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			if (rs.getAmt_inc_dec() == 0.0) {
				model.put("msg", "Please Enter Amount of Increment/Decrement");
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}
			int lenval = 0;
			if (request.getParameter("radio_status").equals("Increment"))
				lenval = 8;
			else if (request.getParameter("radio_status").equals("Decrement"))
				lenval = 9;

			String amt_inc_dec = Double.toString(rs.getAmt_inc_dec());
			if (validation.checkAmt_inc_decLength(amt_inc_dec, lenval) == false) {
				model.put("msg", validation.amt_inc_decMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
			}

			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:Wepe_pers_mdfsUrl");

			}
			rs.setRoleid(roleid);

			CUE_TB_MISO_WEPE_PERS_DET cue_per_det = new CUE_TB_MISO_WEPE_PERS_DET();
			if (base_au.equals("") || base_au.equals("undefined")) {
				cue_per_det.setCategory_of_persn(cat_per);
				cue_per_det.setRank_cat(rank_cat);
				cue_per_det.setRank(rank);
				cue_per_det.setArm_code(arm_code);
				cue_per_det.setApp_trd_code(appt_trade_code);
				cue_per_det.setAppt_trade(appt_trade);
				cue_per_det.setWe_pe_no(we_pe_no);
				cue_per_det.setStatus("1");
				cue_per_det.setRoleid(roleid);

				cue_per_det.setAuth_amt(0.0);
				cue_per_det.setCreated_by(username);
				cue_per_det.setCreated_on(creadtedate);
				Session sessionUD = HibernateUtil.getSessionFactory().openSession();
				sessionUD.beginTransaction();
				int uid = (Integer) sessionUD.save(cue_per_det);
				sessionUD.getTransaction().commit();
				sessionUD.close();

			}
			if (r.equals("Decrement")) {
				amt = "-" + amt;

				double sum = Double.parseDouble(amt);
				rs.setAmt_inc_dec(sum);
			}
			Boolean e = isdetailWepe_rank_exits(we_pe_no, rank, appt_trade_code, arm_code, rank_cat, cat_per, mod, 0);
			if (e.equals(false)) {
				String scenario = rs.getScenario();
				if (scenario.equals("Location")) {
					rs.setLocation(request.getParameter("location"));
					rs.setFormation(null);
					rs.setScenario_unit(null);
					rs.setScenario(scenario);
				} else if (scenario.equals("Formation")) {
					rs.setFormation(request.getParameter("formation"));
					rs.setLocation(null);
					rs.setScenario_unit(null);
					rs.setScenario(scenario);
				} else if (scenario.equals("Unit")) {
					rs.setFormation(null);
					rs.setLocation(null);
					rs.setScenario_unit(request.getParameter("scenario_unit"));
					rs.setScenario(scenario);
				} else if (scenario.equals("Others")) {
					rs.setFormation(null);
					rs.setLocation(null);
					rs.setScenario_unit(null);
					rs.setScenario("Others");
				} else {
					rs.setScenario(null);
					rs.setFormation(null);
					rs.setLocation(null);
					rs.setScenario_unit(null);
				}

				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				rs.setStatus("0");
				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int did = (Integer) sessionHQL1.save(rs);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
				model.put("msg", "Data saved Successfully");
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		} finally {

		}

		List<Map<String, Object>> list = wepepersmdfs.getSearchWEPEReportDetail1(we_pe_no, "", "", "", "", "", "", "",
				"","");
		model.put("getPersonCatListFinal", M.getPersonCatListFinal());
		model.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		model.put("list", list);
		model.put("we_pe1", we_pe);
		model.put("we_pe_no2", we_pe_no);
		model.put("scenario1", scenario3);
		model.put("location1", location);
		model.put("formation1", formation);
		model.put("unit1", unit);
		model.put("location1_hid", location_code);
		model.put("formation1_hid", formation_code);
		model.put("unit1_hid", unit_code);
		model.put("list.size()", list.size());
		return new ModelAndView("Wepe_pers_mdfsTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_rank_exits(String we_pe_no, String rank, String appt_trade_code, String arm_code,
			String rank_cat, String cat_per, String mod, int id) {
		String hql = "";
		if (id != 0) {
			hql = " FROM CUE_TB_MISO_WEPE_PERS_MDFS m where m.appt_trade=:appt_trade_code and m.we_pe_no=:wepe_pers_no and m.rank = :rank and m.arm_code = :arm_code and rank_cat=:rank_cat and cat_per =:cat_per and modification=:mod and id !=:id";
		} else {
			hql = " FROM CUE_TB_MISO_WEPE_PERS_MDFS m where m.appt_trade=:appt_trade_code and m.we_pe_no=:wepe_pers_no and m.rank = :rank and m.arm_code = :arm_code and rank_cat=:rank_cat and cat_per =:cat_per and modification=:mod ";
		}
		List<CUE_TB_MISO_WEPE_PERS_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("appt_trade_code", appt_trade_code);
			query.setParameter("wepe_pers_no", we_pe_no);
			query.setParameter("rank", rank);
			query.setParameter("arm_code", arm_code);
			query.setParameter("rank_cat", rank_cat);
			query.setParameter("cat_per", cat_per);
			query.setParameter("mod", mod);
			if (id != 0) {
				query.setParameter("id", id);
			}
			users = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/getEditCommonAppTrade", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getEditCommonAppTrade(String app_code,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select description from CUE_TB_PSG_RANK_APP_MASTER where code =:app_code order by description");
		q.setParameter("app_code", app_code);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/editgetFormationlist", method = RequestMethod.POST)
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> editgetFormationlist(String loc_code, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:loc_code order by unit_name");
		q.setParameter("loc_code", loc_code);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getrank_des1", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getrank_des1(String rnk, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct description from CUE_TB_PSG_RANK_APP_MASTER where code =:rnk order by description ");
		q.setParameter("rnk", rnk);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/editTypeofRankList_enti_wepe", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getTypeofRankList_enti(String rnk,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = :level_in_hierarchy and code = :code order by description ");
		q.setParameter("level_in_hierarchy", "RANK");
		q.setParameter("code", rnk);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/editParentArmList", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> editParentArmList(String pm, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code =:pm order by arm_desc ");
		q.setParameter("pm", pm);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	////////////////////////////////////// report////////////////////////////

	@RequestMapping(value = "/search_personnel_auth", method = RequestMethod.GET)
	public ModelAndView search_personnel_auth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_personnel_auth", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("Search_Wepe_pers_mdfsTiles");
	}

	@RequestMapping(value = "/admin/ApprovedWEPEUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedWEPEUrl(@ModelAttribute("appid") int appid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,

			@RequestParam(value = "we_pe_no3", required = false) String we_pe_no,
			@RequestParam(value = "modification2", required = false) String modification,
			@RequestParam(value = "cat_per2", required = false) String cat_per,
			@RequestParam(value = "rank_cat2", required = false) String rank_cat,
			@RequestParam(value = "appt_trade2", required = false) String appt_trade,
			@RequestParam(value = "arm_code2", required = false) String arm_code,
			@RequestParam(value = "rank2", required = false) String rank,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "we_pe2", required = false) String we_pe) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String username = session.getAttribute("username").toString();
		String mst = wepepersmdfs.setApprovedWepe(appid,username);
		if(mst.equals("Approved Successfully")) {
			vetting.updateVettingDtl( we_pe_no, "1");
		}
		Mmap.put("msg", mst);
		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no2", we_pe_no);
		Mmap.put("modification1", modification);
		Mmap.put("cat_per1", cat_per);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("appt_trade1", appt_trade);
		Mmap.put("arm_code1", arm_code);
		Mmap.put("rank1", rank);
		List<Map<String, Object>> list = wepepersmdfs.getSearchWEPEReportDetail(status, we_pe_no, modification, cat_per,
				rank_cat, appt_trade, arm_code, rank, roleType,roleAccess);
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("Search_Wepe_pers_mdfsTiles");
	}

	@RequestMapping(value = "/updaterejectactionqrywepers", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectactionqrywepers(String remarks, String letter_no, int id) {
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wepe_pers_mdfs", remarks, id,
				letter_no);
		return list2;
	}

	@RequestMapping(value = "/deleteWEPEUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteWEPEUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(wepepersmdfs.setDeleteWepe(deleteid));
		return list2;
	}

	@RequestMapping(value = "/admin/updateWEPEUrl")
	public ModelAndView updateWEPEUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("editwepeCmd", wepepersmdfs.getCUE_TB_MISO_WEPE_PERS_MDFSid(updateid));
		model.put("msg", msg);
		return new ModelAndView("edit_search_personnel_authTile");
	}

	@RequestMapping(value = "/editwepeAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editwepeAction(@ModelAttribute("editwepeCmd") CUE_TB_MISO_WEPE_PERS_MDFS updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session11, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}

		String username = session11.getAttribute("username").toString();

		String rank_cat = request.getParameter("rank_cat");
		updateid.setRank_cat(rank_cat);
		String parent_arm1 = request.getParameter("arm_code");
		String parent_arm = request.getParameter("arm_code");
		String r = request.getParameter("radio_status");
		String amt = request.getParameter("amt_inc_dec1");
		String amt_inc_dec1 = request.getParameter("amt_inc_dec1");
		if (amt_inc_dec1.equals("")) {
			amt_inc_dec1 = "0.0";
			amt = "0.0";
		}

		Double amt_inc_dec = Double.parseDouble(amt_inc_dec1);

		if (r.equals("Decrement")) {
			amt = "-" + amt;
			amt_inc_dec = Double.parseDouble(amt);
		} else {
			amt_inc_dec = Double.parseDouble(amt_inc_dec1);
		}

		CUE_TB_MISO_WEPE_PERS_MDFS ctm = new CUE_TB_MISO_WEPE_PERS_MDFS();
		if (parent_arm1.equals("ERE")) {
			ctm.setArm_code("arm_code");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String we_pe_no = request.getParameter("we_pe_no");
		String cat_per = request.getParameter("cat_per");
		String rank_cat1 = request.getParameter("rank_cat");
		String rank = request.getParameter("rank");
		String arm_code = request.getParameter("arm_code");
		String appt_trade_code = request.getParameter("appt_trade");
		String mod = request.getParameter("modification");

		String location = request.getParameter("location");
		String formation = request.getParameter("formation");
		String scenario1 = request.getParameter("scenario");
		String scenario_unit = request.getParameter("scenario_unit");
		String remarks = request.getParameter("remarks");
		String radio_status = request.getParameter("radio_status");
		String amt_inc_dec_hid = request.getParameter("amt_inc_dec_hid");

		if (updateid.getScenario() == "") {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Scenario");
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		if (validation.checkScenarioLength(updateid.getScenario()) == false) {
			model.put("msg", validation.senarioMSG);
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		String scenario2 = updateid.getScenario();
		if (scenario2.equals("Location")) {
			if (updateid.getLocation().equals("")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Location");
				return new ModelAndView("redirect:updateWEPEUrl");
			}
			if (validation.checkLocationLength(updateid.getLocation()) == false) {
				model.put("msg", validation.locMSG);
				return new ModelAndView("redirect:updateWEPEUrl");
			}
		}
		if (scenario2.equals("Formation")) {
			if (updateid.getFormation().equals("")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Formation");
				return new ModelAndView("redirect:updateWEPEUrl");

			}
			if (validation.checkFormationLength(updateid.getFormation()) == false) {
				model.put("msg", validation.formMSG);
				return new ModelAndView("redirect:updateWEPEUrl");
			}
		}
		if (scenario2.equals("Unit")) {
			if (updateid.getScenario_unit().equals("")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Unit");
				return new ModelAndView("redirect:updateWEPEUrl");

			}
			if (validation.sus_noLength(updateid.getScenario_unit()) == false) {
				model.put("msg", validation.unitMSG);
				return new ModelAndView("redirect:updateWEPEUrl");
			}
		}
		if (mod.equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Enter Modification");
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		if (validation.checkModificationLength(updateid.getModification()) == false) {
			model.put("msg", validation.modMSG);
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		if (radio_status == "" || radio_status == null || radio_status == "null" || radio_status.equals(null)) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Increment/Decrement");
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		if (amt_inc_dec.equals("") || amt_inc_dec.equals("0.0") || amt_inc_dec == 0.0) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Enter Amount of Increment/Decrement");
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		int lenval = 0;
		if (request.getParameter("radio_status").equals("Increment"))
			lenval = 8;
		else if (request.getParameter("radio_status").equals("Decrement"))
			lenval = 9;
		String amt_inc_decvalid = Double.toString(updateid.getAmt_inc_dec());
		if (validation.checkAmt_inc_decLength(amt_inc_decvalid, lenval) == false) {
			model.put("msg", validation.amt_inc_decMSG);
			return new ModelAndView("redirect:updateWEPEUrl");
		}
		Boolean e = isdetailWepe_rank_exits(we_pe_no, rank, appt_trade_code, arm_code, rank_cat1, cat_per, mod, id);
		if (e.equals(false)) {
			String scenario = request.getParameter("scenario");
			if (scenario.equals("Location")) {
				scenario1 = scenario;
				location = request.getParameter("location");
				formation = "";
				scenario_unit = "";
			} else if (scenario.equals("Formation")) {
				scenario1 = scenario;
				location = "";
				formation = request.getParameter("formation");
				scenario_unit = "";
			} else if (scenario.equals("Unit")) {
				scenario1 = scenario;
				location = "";
				formation = "";
				scenario_unit = request.getParameter("scenario_unit");
			} else if (scenario.equals("Others")) {
				scenario1 = scenario;
				location = "";
				formation = "";
				scenario_unit = "";
			} else {
				scenario1 = "";
				location = "";
				formation = "";
				scenario_unit = "";
			}
			updateid.setAmt_inc_dec(Double.parseDouble(amt_inc_dec1));
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Transaction tx = session.beginTransaction();
			String hql = "update CUE_TB_MISO_WEPE_PERS_MDFS  set modification=:modification,amt_inc_dec=:amt_inc_dec,location=:location,formation=:formation,scenario_unit=:scenario_unit,scenario=:scenario1, remarks=:remarks,modified_by=:modified_by,"
					+ "modified_on=:modified_on,status ='0',vettedby_dte1=:vettedby_dte1, vettedby_dte2=:vettedby_dte2 where id=:id";
			Query query = session.createQuery(hql).setString("modification", mod).setDouble("amt_inc_dec", amt_inc_dec)
					.setString("location", location).setString("formation", formation)
					.setString("scenario_unit", scenario_unit).setString("scenario1", scenario1)
					.setString("modified_by", username).setString("modified_on", modifydate)
					.setString("remarks", remarks).setString("vettedby_dte1", "")
			        .setString("vettedby_dte2", "").setInteger("id", updateid.getId());
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if (rowCount > 0) {
				model.put("msg", "Updated Successfully");
			} else {
				model.put("msg", "Updated not Successfully");
			}
		} else {
			model.put("msg", "Data already exist !");
			model.put("editwepeCmd", wepepersmdfs.getCUE_TB_MISO_WEPE_PERS_MDFSid(updateid.getId()));
			return new ModelAndView("edit_search_personnel_authTile");

		}
		return new ModelAndView("redirect:Wepe_pers_mdfsUrl");
	}

	//////////////////////

	@RequestMapping(value = "/admin/search_personnel_auth1", method = RequestMethod.POST)
	public ModelAndView search_personnel_auth1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "modification1", required = false) String modification,
			@RequestParam(value = "cat_per1", required = false) String cat_per,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "appt_trade1", required = false) String appt_trade,
			@RequestParam(value = "arm_code1", required = false) String arm_code,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe1", required = false) String we_pe) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no2", we_pe_no);
		Mmap.put("modification1", modification);
		Mmap.put("cat_per1", cat_per);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("appt_trade1", appt_trade);
		Mmap.put("arm_code1", arm_code);
		Mmap.put("rank1", rank);
		List<Map<String, Object>> list = wepepersmdfs.getSearchWEPEReportDetail(status, we_pe_no, modification, cat_per,
				rank_cat, appt_trade, arm_code, rank, roleType,roleAccess);

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("Search_Wepe_pers_mdfsTiles");
	}

}
