package com.controller.psg.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.CompanyDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_COMPANY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Company_Contoller {

	@Autowired
	private CompanyDAO COM;
	@Autowired
	private RoleBaseMenuDAO roledao;

	Psg_CommonController mcommon = new Psg_CommonController();

	// -------------------------------Company For page Open
	// -------------------------------------
	@RequestMapping(value = "/admin/Company", method = RequestMethod.GET)
	public ModelAndView Company(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Company", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> list = COM.search_Company("", 0,"active");
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		Mmap.put("getBattalionList", mcommon.getBattalionList());
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		return new ModelAndView("CompanyTiles");
	}

	// -------------------------------Company save
	// -------------------------------------

	@RequestMapping(value = "/CompanyAction", method = RequestMethod.POST)
	public ModelAndView CompanyAction(@ModelAttribute("CompanyCmd") TB_COMPANY co, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Company", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		
		
		int id = co.getId() > 0 ? co.getId() : 0;

		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String company_name = request.getParameter("company_name").trim();
		
		if (co.getBat_id() == 0  ) {
			model.put("msg", "Please Select Battalion ");

			return new ModelAndView("redirect:Company");
		}
		if (company_name.equals("") || company_name.equals("null") || company_name.equals(null)) {
			model.put("msg", "Please Enter Company Name");
			return new ModelAndView("redirect:Company");
		}
		if (co.getStatus() == "inactive" || co.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Company");
		}
		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_COMPANY where company_name=:company_name and bat_id=:bat_id and status=:status and id !=:id");
			q0.setParameter("company_name", co.getCompany_name());
			q0.setParameter("status", co.getStatus());
			q0.setParameter("bat_id", co.getBat_id());
			q0.setParameter("id", id);
			
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {

				co.setCreated_by(username);
				co.setCreated_date(date);
				co.setCompany_name(company_name);
				if (c == 0) {
					sessionHQL.save(co);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Company");
	}

	// -------------------------SEARCH Company -------------------------------------

	@RequestMapping(value = "/search_Company", method = RequestMethod.POST)
	public ModelAndView search_Company(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("company_name1") String company_name, 
			@ModelAttribute("bat_id1") int bat_id,
			@ModelAttribute("status1") String status) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Company", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> list = COM.search_Company(company_name, bat_id, status);
		model.put("list", list);
		model.put("size", list.size());
		model.put("company_name1", company_name);
		model.put("getBattalionList", mcommon.getBattalionList());
		model.put("getStatusMasterList", mcommon.getStatusMasterList());
		model.put("bat_id1", bat_id);
		model.put("status1", status);

		model.put("list", list);
		return new ModelAndView("CompanyTiles");
	}

	// -------------------------Edit Company For page Open
	// -------------------------------------
	@RequestMapping(value = "/edit_Company", method = RequestMethod.POST)
	public ModelAndView edit_Company(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {
		
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Company", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		TB_COMPANY ComDetails = COM.getCompanyByid(Integer.parseInt(updateid));
		model.put("EditCompanyCMD", ComDetails);

		model.put("msg", msg);
		model.put("getBattalionList", mcommon.getBattalionList());
		model.put("getStatusMasterList", mcommon.getStatusMasterList());
		return new ModelAndView("EditCompanyTiles");
	}

	// -------------------------Edit Company Action
	// -------------------------------------

	@RequestMapping(value = "/EditCompanyAction", method = RequestMethod.POST)
	public ModelAndView EditCompanyAction(@ModelAttribute("EditCompanyCMD") TB_COMPANY CO, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Company", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String company_name = request.getParameter("company_name").trim();
		String status = request.getParameter("status");
		int bat_id = Integer.parseInt(request.getParameter("bat_id"));

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (CO.getBat_id() == 0 || CO.getStatus().equals("0")) {
			TB_COMPANY ComDetails = COM.getCompanyByid((id));
			model.put("EditCompanyCMD", ComDetails);
			model.put("getBattalionList", mcommon.getBattalionList());
			model.put("getStatusMasterList", mcommon.getStatusMasterList());
			model.put("msg", "Please Select Battalion ");
			return new ModelAndView("EditCompanyTiles");
		}
		
		if (company_name.equals("") || company_name.equals("null") || company_name.equals(null)) {
			TB_COMPANY ComDetails = COM.getCompanyByid((id));
			model.put("EditCompanyCMD", ComDetails);
			model.put("getBattalionList", mcommon.getBattalionList());
			model.put("getStatusMasterList", mcommon.getStatusMasterList());
			model.put("msg", "Please Enter Company Name");

			return new ModelAndView("EditCompanyTiles");
		}
		/*if (CO.getStatus() == "inactive" || CO.getStatus().equals("inactive")) {
			TB_COMPANY ComDetails = COM.getCompanyByid((id));
			model.put("EditCompanyCMD", ComDetails);
			model.put("getBattalionList", mcommon.getBattalionList());
			model.put("getStatusMasterList", mcommon.getStatusMasterList());
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("EditCompanyTiles");
		}*/
		try {
			Query q0 = session1
					.createQuery("select count(id) from TB_COMPANY where company_name=:company_name and bat_id=:bat_id and status=:status and id !=:id");
			q0.setParameter("company_name", company_name);
			q0.setParameter("bat_id", bat_id);
			q0.setParameter("status", status); 
			
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_COMPANY set company_name=:company_name,bat_id=:bat_id,status=:status,modify_by=:modify_by,modify_date=:modify_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("company_name", company_name)
						.setInteger("bat_id", bat_id)
						.setString("status",status)
						.setString("modify_by", username).setDate("modify_date", new Date()).setInteger("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}
			} else {
				model.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Company");
	}

	// -------------------------Delete Company -------------------------------------
/*	@RequestMapping(value = "/Company_delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Company_delete(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "delete from TB_COMPANY where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Company");
	}*/
	
	@RequestMapping(value = "/Company_delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Company_delete(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Company", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		List<String> liststr = new ArrayList<String>();

		String username = session.getAttribute("username").toString();

		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "update TB_COMPANY set modify_by=:modify_by,modify_date=:modify_date,status=:status"
					+ " where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
					.setString("modify_by", username).setDate("modify_date", new Date()).setInteger("id", id)
					.executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Company");
	}

}
