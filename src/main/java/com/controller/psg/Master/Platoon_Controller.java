package com.controller.psg.Master;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.PlatoonDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_PLATOON;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Platoon_Controller {

	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private PlatoonDAO PLA;
	@Autowired
	private RoleBaseMenuDAO roledao;

	// -------------------------------Platoon For page Open
	// -------------------------------------
	@RequestMapping(value = "/admin/Platoon", method = RequestMethod.GET)
	public ModelAndView Platoon(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Platoon", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<Map<String, Object>> list = PLA.search_Platoon("","active");
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("PlatoonTiles");
	}

	// -------------------------------Platoon save
	// -------------------------------------
		
		@RequestMapping(value = "/PlatoonAction", method = RequestMethod.POST)
		public ModelAndView PlatoonAction(@ModelAttribute("PlatoonCmd") TB_PLATOON pl, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Platoon", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
		int id = pl.getId() > 0 ? pl.getId() : 0;

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String platoon_name = request.getParameter("platoon_name").trim();
 		String status = request.getParameter("status");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (platoon_name.equals("") || platoon_name.equals("null") || platoon_name.equals(null)) {
			model.put("msg", "Please Enter Platoon Name");
			return new ModelAndView("redirect:Platoon");
		}
		if (pl.getStatus() == "inactive" || pl.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Platoon");
		}
		try {

			Query q0 = sessionHQL
					.createQuery("select count(id) from TB_PLATOON where platoon_name=:platoon_name and status=:status and id !=:id");
			q0.setParameter("platoon_name", platoon_name);
			q0.setParameter("status", status);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {

				pl.setCreated_by(username);
				pl.setCreated_date(date);
				pl.setPlatoon_name(platoon_name);
				if (c == 0) {
					sessionHQL.save(pl);
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
		 
		return new ModelAndView("redirect:Platoon");
	}

	// -------------------------SEARCH Platoon -------------------------------------

	@RequestMapping(value = "/search_Platoon", method = RequestMethod.POST)
	public ModelAndView search_Platoon(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "platoon_name1", required = false) String platoon_name,
			@RequestParam(value = "status1", required = false) String status){
			
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Platoon", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		List<Map<String, Object>> list = PLA.search_Platoon(platoon_name,status);
		model.put("list", list);
		model.put("size", list.size());
		model.put("platoon_name1", platoon_name);
		model.put("list", list);
		model.put("status1", status);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("PlatoonTiles");
		
	}

	// -------------------------Edit Platoon For page Open
	// -------------------------------------
	@RequestMapping(value = "/edit_Platoon", method = RequestMethod.POST)
	public ModelAndView edit_Platoon(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {
		
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Platoon", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		TB_PLATOON PlaDetails = PLA.getPlatoonByid(Integer.parseInt(updateid));
		model.put("EditPlatoonCMD", PlaDetails);
		model.put("msg", msg);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("EditPlatoonTiles");
	}

	// -------------------------Edit Platoon Action
	// -------------------------------------

	@RequestMapping(value = "/EditPlatoonAction", method = RequestMethod.POST)
	public ModelAndView EditPlatoonAction(@ModelAttribute("EditPlatoonCMD") TB_PLATOON PL, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Platoon", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String username = session.getAttribute("username").toString();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String platoon_name = request.getParameter("platoon_name").trim();
		String status = request.getParameter("status");
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (platoon_name.equals("") || platoon_name.equals("null") || platoon_name.equals(null)) {
			TB_PLATOON PlaDetails = PLA.getPlatoonByid((id));
			model.put("EditPlatoonCMD", PlaDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Please Enter Platoon Name");
			return new ModelAndView("EditPlatoonTiles");
		}
		/*if (PL.getStatus() == "inactive" || PL.getStatus().equals("inactive")) {
			TB_PLATOON PlaDetails = PLA.getPlatoonByid((id));
			model.put("EditPlatoonCMD", PlaDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("EditPlatoonTiles");
		}*/
		try {
			Query q0 = session1
					.createQuery("select count(id) from TB_PLATOON where platoon_name=:platoon_name and status=:status  and id !=:id");
			q0.setParameter("platoon_name", platoon_name);
			q0.setParameter("id", id);
			q0.setParameter("status", status);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_PLATOON set platoon_name=:platoon_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("platoon_name", platoon_name)
						.setString("status", status)
						.setString("modify_by", username)
						.setDate("modify_date", new Date())
						.setInteger("id", id);
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
		return new ModelAndView("redirect:Platoon");
	}

	// -------------------------Delete Platoon -------------------------------------
/*	@RequestMapping(value = "/Platoon_delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Platoon_delete(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "delete from TB_PLATOON where id=:id";
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
		return new ModelAndView("redirect:Platoon");
	}*/
	
	@RequestMapping(value = "/Platoon_delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Platoon_delete(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Platoon", roleid);
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

			String hqlUpdate = "update TB_PLATOON set modify_by=:modify_by,modify_date=:modify_date,status=:status"
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
		return new ModelAndView("redirect:Platoon");
	}

}
