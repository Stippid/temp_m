package com.controller.HTO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.HTO.sdReliefProgramDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.HTO.TB_ORBAT_OC_VOUCHER;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Voucher_OC {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@Autowired
	RoleBaseMenuDAO roleDAO;

	@Autowired
	sdReliefProgramDAO sd;


	@RequestMapping(value = "/voucher_oc", method = RequestMethod.GET)
	public ModelAndView voucher_oc(String subModule1,ModelMap Mmap,HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {

		String  roleid = sessionA.getAttribute("roleid").toString();
		String  role = sessionA.getAttribute("role").toString();
		Boolean val = roleDAO.ScreenRedirect("voucher_oc", roleid);
		//		if(val == false) {
		//			return new ModelAndView("AccessTiles");
		//		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct rplc_by_unit_sus_no from Miso_Orbat_Relief_Prgm where sus_no=:sus_no and (miso_status is null or miso_status!='1')  and sd_status='1' ");
		q.setParameter("sus_no", roleSusNo);

		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String> ) q.list();
		tx.commit();
		session.close();

		String rplc_by_unit_sus_no= "";
		if(list.get(0) != null){
			rplc_by_unit_sus_no= list.get(0);
		}else {
			rplc_by_unit_sus_no = "";
		}

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(rplc_by_unit_sus_no, sessionA).get(0));
			Mmap.put("to_sus_no", rplc_by_unit_sus_no);
		}
		Mmap.put("listVoucherUnits", sd.getViewVoucherUnits(roleSusNo, sessionA));
		Mmap.put("role", role);

		return new ModelAndView("Voucher_OC_Tiles");
	}


	@RequestMapping(value = "/OC_Prepare_Action", method = RequestMethod.POST)
	public ModelAndView OC_Prepare_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String from_sus = request.getParameter("from_sus");
		String to_sus = request.getParameter("to_sus");
		String ba_no = request.getParameter("ba_no");

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		TB_ORBAT_OC_VOUCHER OC = new TB_ORBAT_OC_VOUCHER();

		try {

			String form_code_control = getFormCodeControl(sessionHQL, from_sus);
			String imdt_higher_fmn = determineImdtHigherFmn(form_code_control);

			OC.setSus_from(from_sus);
			OC.setSus_to(to_sus);
			OC.setBa_no(ba_no);
			OC.setVoucher_status("0");
			OC.setStatus("0");
			OC.setCreated_by(username);
			OC.setCreated_date(new Date());
			OC.setImdt_higher_fmn(imdt_higher_fmn);

			sessionHQL.save(OC);
			sessionHQL.flush();
			sessionHQL.clear();

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:voucher_oc");
	}


	private String getFormCodeControl(Session session, String from_sus) {
		String formCodeControl = null;
		try {
			String hql = "SELECT distinct d.form_code_control FROM Miso_Orbat_Unt_Dtl d WHERE d.sus_no = :susNo";
			formCodeControl = (String) session.createQuery(hql)
					.setParameter("susNo", from_sus)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			formCodeControl = null;
		}
		return formCodeControl;
	}

	private String determineImdtHigherFmn(String formCodeControl) {
		if (formCodeControl != null && formCodeControl.length() == 10) {
			if (formCodeControl.endsWith("0000")) {
				return "div";
			} else if (formCodeControl.endsWith("0000000")) {
				return "corps";
			} else if (formCodeControl.endsWith("000000000")) {
				return "command";
			} else {
				return "bde";
			}
		} else {
			return "bde";
		}
	}

	@RequestMapping(value = "/OC_Approve_Action", method = RequestMethod.POST)
	public ModelAndView OC_Approve_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String from_sus = request.getParameter("from_sus");
		String to_sus = request.getParameter("to_sus");
		String ba_no = request.getParameter("ba_no");

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<String> liststr = new ArrayList<String>();

		try {

			String hqlUpdate = "UPDATE TB_ORBAT_OC_VOUCHER set voucher_status=:voucher_status,approve_by=:approve_by,approve_date=:approve_date where ba_no=:ba_no and voucher_status = '0' ";
			int app = sessionHQL.createQuery(hqlUpdate).setString("voucher_status", "1").setString("approve_by", username).setDate("approve_date", new Date())
					.setString("ba_no", ba_no).executeUpdate();

			tx.commit();
			if (app > 0) {
				liststr.add("Approved Successfully.");
			}
			model.put("msg",liststr.get(0));
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:voucher_oc");
	}


	@RequestMapping(value = "/OC_Reject_Action", method = RequestMethod.POST)
	public ModelAndView OC_Reject_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String from_sus = request.getParameter("from_sus");
		String to_sus = request.getParameter("to_sus");
		String ba_no = request.getParameter("ba_no");

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<String> liststr = new ArrayList<String>();

		try {

			String hqlUpdate = "UPDATE TB_ORBAT_OC_VOUCHER set voucher_status=:voucher_status,approve_by=:approve_by,approve_date=:approve_date where ba_no=:ba_no and voucher_status = '0' ";
			int app = sessionHQL.createQuery(hqlUpdate).setString("voucher_status", "2").setString("approve_by", username).setDate("approve_date", new Date())
					.setString("ba_no", ba_no).executeUpdate();

			tx.commit();
			if (app > 0) {
				liststr.add("Approved Successfully.");
			}
			model.put("msg",liststr.get(0));
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:voucher_oc");
	}


	@RequestMapping(value = "/getOCPrepareTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getOCPrepareTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType,String sus_no,String veh_type, String voucher_status, HttpSession sessionUserId) throws SQLException {
		return sd.getOCPrepareTable(startPage, pageLength, Search, orderColunm, orderType, sus_no, veh_type, voucher_status, sessionUserId);
	}

	@RequestMapping(value = "/getOCPrepareCount", method = RequestMethod.POST)
	public @ResponseBody long getOCPrepareCount(String Search,String orderColunm,String orderType,String sus_no,String veh_type,
			String voucher_status, HttpSession sessionUserId,String msg) throws SQLException {
		return sd.getOCPrepareCount(Search, orderColunm, orderType,sus_no, veh_type, voucher_status,sessionUserId);
	}

	@RequestMapping(value = "/getViewVoucherUnits", method = RequestMethod.GET)
	public @ResponseBody ArrayList<ArrayList<String>> getViewVoucherUnits(String sus_no, HttpSession sessionUserId) throws SQLException {
		return sd.getViewVoucherUnits(sus_no, sessionUserId);
	}

	@RequestMapping(value = "/getViewVoucherData", method = RequestMethod.GET)
	public @ResponseBody ArrayList<ArrayList<String>> getViewVoucherData(String sus_no, String ba_no, HttpSession sessionUserId) throws SQLException {
		return sd.getViewVoucherData(sus_no, ba_no, sessionUserId);
	}



	@RequestMapping(value = "/downloadVoucherPDF", method = RequestMethod.POST)
	public ModelAndView downloadVoucherPDF(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "baNoV", required = false) String baNoV,
			@RequestParam(value = "susNoV", required = false) String susNoV,
			Authentication authentication, HttpServletRequest request, HttpSession session) {


		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roleDAO.ScreenRedirect("PersonalAssertsUrl", roleid);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		//		String unit_name = all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0);
		if (val == false) {
			// return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = formatter.format(date);
		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);
		ArrayList<ArrayList<String>> listUnits = sd.getViewVoucherUnits(susNoV, session);
		ArrayList<ArrayList<String>> listData = sd.getViewVoucherData(susNoV, baNoV, session);

		return new ModelAndView(new Voucher_PDF_Controller(foot, username, baNoV, ""), "userList", listUnits);
	}


}
