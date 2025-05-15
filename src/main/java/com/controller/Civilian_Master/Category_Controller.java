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
import com.dao.psg.Civilian_Master.CategoryDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Civilian_Master.TB_CATEGORY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Category_Controller {
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private CategoryDAO cat_dao;
	Psg_CommonController pcommon = new Psg_CommonController();
	
	@RequestMapping(value = "/admin/category_url", method = RequestMethod.GET)
	public ModelAndView category_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<Map<String, Object>> list = cat_dao.search_category("","active");
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("CategoryTiles");
	}
	
	@RequestMapping(value = "/CategoryAction", method = RequestMethod.POST)
	public ModelAndView CategoryAction(@ModelAttribute("CategoryCmd") TB_CATEGORY cat,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = cat.getId() > 0 ? cat.getId() : 0;

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String category = request.getParameter("category");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (category.equals("") || category.trim().equals("null") || category.equals(null)) {
			model.put("msg", "Please Enter Category");
			return new ModelAndView("redirect:category_url");
		}
		if (category.length() > 50) {
			model.put("msg", "Category Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:category_url");
		}
		if (cat.getStatus() == "inactive" || cat.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("redirect:category_url");
		}

		try {

			Query q0 = sessionHQL
					.createQuery("select count(id) from TB_CATEGORY where category=:category and status=:status and id !=:id");
			q0.setParameter("category", cat.getCategory());
			q0.setParameter("status", cat.getStatus());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {

				cat.setCreated_by(username);
				cat.setCreated_date(date);
				cat.setCategory(category.trim());
				if (c == 0) {
					sessionHQL.save(cat);
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
		return new ModelAndView("redirect:category_url");
	}
	
	@RequestMapping(value = "/Search_Category", method = RequestMethod.POST)
	public ModelAndView Search_Category(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("category1") String category,
			@ModelAttribute("status1") String status) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = cat_dao.search_category(category,status);
		model.put("list", list);
		model.put("size", list.size());
		model.put("category1", category);
		model.put("status1", status);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("CategoryTiles");
			
	}
	
	@RequestMapping(value = "/Edit_Category", method = RequestMethod.POST)
	public ModelAndView Edit_Category(@ModelAttribute("updateid") String updateid, ModelMap model,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_CATEGORY CatDetails = cat_dao.getCategortByid(Integer.parseInt(updateid));
		model.put("Edit_CategoryCMD", CatDetails);
		model.put("msg", msg);
		model.put("getStatusMasterList", pcommon.getStatusMasterList());

		return new ModelAndView("Edit_CategoryTiles");
	}

	@RequestMapping(value = "/Edit_CategoryAction", method = RequestMethod.POST)
	public ModelAndView Edit_CategoryAction(@ModelAttribute("Edit_CategoryCMD") TB_CATEGORY b,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String category = request.getParameter("category");
		String status = request.getParameter("status");

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (category.equals("") || category.trim().equals("null") || category.equals(null)) {
			TB_CATEGORY PLDetails = cat_dao.getCategortByid((id));
			model.put("Edit_CategoryCMD", PLDetails);
			model.put("msg", "Please Enter Category");

			return new ModelAndView("Edit_CategoryTiles");
		}
		if (category.length() > 50) {
			model.put("msg", "Category Length Should Be Less Than 50 Characters");
			return new ModelAndView("Edit_CategoryTiles");
		}
		/*if (b.getStatus() == "inactive" || b.getStatus().equals("inactive")) {
			TB_CATEGORY PLDetails = cat_dao.getCategortByid((id));
			model.put("Edit_CategoryCMD", PLDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_CategoryTiles");

		}*/

		try {
			Query q0 = session1
					.createQuery("select count(id) from TB_CATEGORY where category=:category and status=:status and id !=:id");
			q0.setParameter("category", category);
			q0.setParameter("status", status); 
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_CATEGORY set category=:category,modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("category", category.trim())
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
		return new ModelAndView("redirect:category_url");
	}
	
	@RequestMapping(value = "/Delete_Category", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Category(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("category_url", roleid);
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

			String hqlUpdate = "update TB_CATEGORY set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
		return new ModelAndView("redirect:category_url");
	}
}
