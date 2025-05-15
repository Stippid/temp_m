package com.controller.Civilian_Master;

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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Master.Pay_Level_DAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Civilian_Master.TB_PSG_MSTR_PAY_LEVEL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Pay_Level_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Pay_Level_DAO pl_dao;
	Psg_CommonController pcommon = new Psg_CommonController();

	@RequestMapping(value = "/admin/pay_level_url", method = RequestMethod.GET)
	public ModelAndView pay_level_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<Map<String, Object>> list = pl_dao.search_pay_level("","active");
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("Pay_LevelTiles");
	}

	@RequestMapping(value = "/Pay_LevelAction", method = RequestMethod.POST)
	public ModelAndView Pay_LevelAction(@ModelAttribute("Pay_LevelCmd") TB_PSG_MSTR_PAY_LEVEL pl,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = pl.getId() > 0 ? pl.getId() : 0;

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String pay_level = request.getParameter("pay_level");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (pay_level.equals("") || pay_level.trim().equals("null") || pay_level.equals(null)) {
			model.put("msg", "Please Enter Pay Level");
			return new ModelAndView("redirect:pay_level_url");
		}
		if (pay_level.length() > 50) {
			model.put("msg", "Pay Level Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:pay_level_url");
		}
		if (pl.getStatus() == "inactive" || pl.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("redirect:pay_level_url");
		}

		try {

			Query q0 = sessionHQL
					.createQuery("select count(id) from TB_PSG_MSTR_PAY_LEVEL where pay_level=:pay_level and status=:status and id !=:id");
			q0.setParameter("pay_level", pl.getPay_level());
			q0.setParameter("status", pl.getStatus());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {

				pl.setCreated_by(username);
				pl.setCreated_date(date);
				pl.setPay_level(pay_level.trim());
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
		return new ModelAndView("redirect:pay_level_url");
	}

	@RequestMapping(value = "/Search_Pay_level", method = RequestMethod.POST)
	public ModelAndView Search_Pay_level(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("pay_level2") String pay_level,
			@ModelAttribute("status1") String status) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<Map<String, Object>> list = pl_dao.search_pay_level(pay_level,status);
		model.put("list", list);
		model.put("size", list.size());
		model.put("pay_level1", pay_level);
		model.put("status1", status);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("Pay_LevelTiles");
			
	}

	@RequestMapping(value = "/Edit_Pay_level", method = RequestMethod.POST)
	public ModelAndView Edit_Pay_level(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		TB_PSG_MSTR_PAY_LEVEL PLDetails = pl_dao.getPay_levelByid(Integer.parseInt(updateid));
		model.put("Edit_Pay_LevelCMD", PLDetails);
		model.put("msg", msg);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());

		return new ModelAndView("Edit_Pay_LevelTiles");
	}

	@RequestMapping(value = "/Edit_Pay_LevelAction", method = RequestMethod.POST)
	public ModelAndView Edit_Pay_LevelAction(@ModelAttribute("Edit_Pay_LevelCMD") TB_PSG_MSTR_PAY_LEVEL b,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String pay_level = request.getParameter("pay_level");
		String status = request.getParameter("status");

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (pay_level.equals("") || pay_level.trim().equals("null") || pay_level.equals(null)) {
			TB_PSG_MSTR_PAY_LEVEL PLDetails = pl_dao.getPay_levelByid((id));
			model.put("Edit_Pay_LevelCMD", PLDetails);
			model.put("msg", "Please Enter Pay Level");

			return new ModelAndView("Edit_Pay_LevelTiles");
		}
		if (pay_level.length() > 50) {
			model.put("msg", "Pay Level Length Should Be Less Than 50 Characters");
			return new ModelAndView("Edit_Pay_LevelTiles");
		}
		/*if (b.getStatus() == "inactive" || b.getStatus().equals("inactive")) {
			TB_PSG_MSTR_PAY_LEVEL PLDetails = pl_dao.getPay_levelByid((id));
			model.put("Edit_Pay_LevelCMD", PLDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Pay_LevelTiles");

		}*/

		try {
			Query q0 = session1
					.createQuery("select count(id) from TB_PSG_MSTR_PAY_LEVEL where pay_level=:pay_level and status=:status and id !=:id");
			q0.setParameter("pay_level", pay_level);
			q0.setParameter("status", status); 
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_PSG_MSTR_PAY_LEVEL set pay_level=:pay_level,modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("pay_level", pay_level.trim())
						.setString("modified_by", username).setDate("modified_date", new Date())
						.setString("status",status).setInteger("id", id);
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

		}
		return new ModelAndView("redirect:pay_level_url");
	}

	@RequestMapping(value = "/Delete_Pay_level", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Pay_level(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		List<String> liststr = new ArrayList<String>();

		String username = session.getAttribute("username").toString();
		
		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "update TB_PSG_MSTR_PAY_LEVEL set modified_by=:modified_by,modified_date=:modified_date,status=:status"
					+ " where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setString("modified_by", username).setString("status","inactive")
					.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
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
		return new ModelAndView("redirect:pay_level_url");
	}

}
