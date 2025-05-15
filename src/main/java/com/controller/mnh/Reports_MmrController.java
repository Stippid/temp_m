package com.controller.mnh;

import java.util.ArrayList;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Reports_MmrController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private MNH_Common_DAO mnh1_Dao;

	@Autowired
	private MNH_ReportsDAO mnh7_Dao;

	@Autowired
	private MNH_CommonController mcommon;

	MNH_ValidationController validation = new MNH_ValidationController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	// REPORTS MMR
	@RequestMapping(value = "/admin/mnh_mmr_report", method = RequestMethod.GET)
	public ModelAndView mnh_mmr_report(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mnh_mmr_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
	        
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		Mmap.put("r_1", l1);
		Mmap.put("getCommandList", all.getCommandDetailsList());


		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
		Mmap.put("ml_6", mcommon.getDiseaseMMR("", "", session));
		Mmap.put("ml_7", mcommon.getDiseaseASO("", "", session));
		Mmap.put("ml_8", mcommon.getBlockDis("", "", session));

		Mmap.put("msg", msg);
		return new ModelAndView("mnh_mmr_reportTiles");
	}

	// seacrh mmr
	@RequestMapping(value = "/search_mmr_report", method = RequestMethod.POST)
	public ModelAndView search_mmr_report(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("cmd1") String cmd1,
			String mth_yr1, String disease_principal1, String disease_mmr1, String disease_aso1,
			String block_description1, String hdicd_code) {
		int userid = (Integer) session.getAttribute("userId");
		Boolean val = roledao.ScreenRedirect("mnh_mmr_report", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);

		model.put("getCommandList", all.getCommandDetailsList());
		//model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("ml_5", mcommon.getPrincipalCause("", "", session));
		model.put("ml_6", mcommon.getDiseaseMMR("", "", session));
		model.put("ml_7", mcommon.getDiseaseASO("", "", session));
		model.put("ml_8", mcommon.getBlockDis("", "", session));

		model.put("r_1", l1);
		model.put("cmd1", cmd1);
		model.put("mth_yr1", mth_yr1);
		model.put("disease_principal1", disease_principal1);
		model.put("disease_mmr1", disease_mmr1);
		model.put("disease_aso1", disease_aso1);
		model.put("block_description1", block_description1);
		model.put("getCommandList", all.getCommandDetailsList());
		//model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		List<Map<String, Object>> list = mnh7_Dao.getsearch_mmr_report(cmd1, mth_yr1, disease_principal1, disease_mmr1,
				disease_aso1, block_description1, hdicd_code);
		model.put("list", list);
		model.put("size", list.size());
		return new ModelAndView("mnh_mmr_reportTiles");

	}

	@RequestMapping(value = "/getdisease_principal_mmr", method = RequestMethod.POST)
	public @ResponseBody List<String> getdisease_principal_mmr(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}
		q = session.createQuery("select distinct disease_principal from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getdisease_mmr", method = RequestMethod.POST)
	public @ResponseBody List<String> getdisease_mmr(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}
		q = session.createQuery("select distinct disease_mmr from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getdisease_aso", method = RequestMethod.POST)
	public @ResponseBody List<String> getdisease_aso(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}
		q = session.createQuery("select distinct disease_aso from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getdiseaseblock_description", method = RequestMethod.POST)
	public @ResponseBody List<String> getdiseaseblock_description(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}
		q = session.createQuery("select distinct block_description from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;

	}

	@RequestMapping(value = "/getdisease_type", method = RequestMethod.POST)
	public @ResponseBody List<String> getdisease_type(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}
		q = session.createQuery("select distinct disease_type from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getdisease_subtype", method = RequestMethod.POST)
	public @ResponseBody List<String> getdisease_subtype(
			@RequestParam(value = "col_value", required = false) String data_value, String col_name, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		if (!col_name.equals("")) {
			qry += "where " + col_name + "=:data_value";
		}

		q = session.createQuery("select distinct disease_subtype from Tb_Med_Disease_Cause " + qry);
		q.setParameter("data_value", data_value);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
}
