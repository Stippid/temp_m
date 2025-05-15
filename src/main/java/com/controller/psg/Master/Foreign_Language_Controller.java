package com.controller.psg.Master;

import java.text.ParseException;
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
import com.dao.psg.Master.Foreign_language_DAO;
import com.models.psg.Master.TB_FOREIGN_LANGUAGE;
import com.persistance.util.HibernateUtil;

//-------------------------------Foreign_language For page Open --------------------------------

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Foreign_Language_Controller {

	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private Foreign_language_DAO fldao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Foreign_Language_Url", method = RequestMethod.GET)
	public ModelAndView City(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("msg", msg);
		ArrayList<ArrayList<String>> list = fldao.GetSearch_Foreign_Language("","active");
		Mmap.put("list", list);
		Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("Foreign_Langauge_Tiles");
	}

	// -------------------------------Foreign_Language save ----------------------------//

	@RequestMapping(value = "/Foreign_LanguageAction", method = RequestMethod.POST)
	public ModelAndView Hair_ColorAction(@ModelAttribute("Foreign_LanguageCMD") TB_FOREIGN_LANGUAGE FL,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		int id = FL.getId() > 0 ? FL.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String foreign_lang = request.getParameter("foreign_language_name").trim();
		
		if (foreign_lang.equals("") || foreign_lang.equals("null") || foreign_lang.equals(null)) {
			model.put("msg", "Please Enter Foreign Language Name");
			return new ModelAndView("redirect:Foreign_Language_Url");
		}
		if (FL.getStatus() == "inactive" || FL.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Foreign_Language_Url");
		}

		try {
			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_FOREIGN_LANGUAGE where foreign_language_name=:foreign_language_name and status=:status and id !=:id");
			q0.setParameter("foreign_language_name", FL.getForeign_language_name());
			q0.setParameter("id", id);
			q0.setParameter("status", FL.getStatus());

			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				FL.setCreated_by(username);
				FL.setCreated_date(date);
				FL.setForeign_language_name(foreign_lang);
				if (c == 0) {
					sessionHQL.save(FL);
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
		return new ModelAndView("redirect:Foreign_Language_Url");
	}

	// -------------------------SEARCH Foreign_Language -------------------------------

	@RequestMapping(value = "GetSearch_Foreign_Language", method = RequestMethod.POST)
	public ModelAndView GetSearch_Foreign_Language(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "foreign_language_name2", required = false) String foreign_language_name,
			@RequestParam("status2") String status) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> list = fldao.GetSearch_Foreign_Language(foreign_language_name,status);
		
		if (!foreign_language_name.equals("")) {
			Mmap.put("foreign_language_name2", foreign_language_name);
		}
			Mmap.put("status2", status);
			Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		
		Mmap.put("list", list);
		return new ModelAndView("Foreign_Langauge_Tiles", "Foreign_LanguageCMD", new TB_FOREIGN_LANGUAGE());
	}

	// -------------------------DELETE Foreign_Language -------------------------------//
	@RequestMapping(value = "/Delete_Foreign_Language", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Foreign_Language(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
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

			String hqlUpdate = "update TB_FOREIGN_LANGUAGE set modified_by=:modified_by,modified_date=:modified_date,status=:status"
					+ " where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
					.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id)
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
		return new ModelAndView("redirect:Foreign_Language_Url");
	}
	
	// -------------------------EDIT Foreign_Language -------------------------------//

	@RequestMapping(value = "/Edit_Foreign_Language_Url",method=RequestMethod.POST)
	public ModelAndView Edit_Foreign_Language_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		TB_FOREIGN_LANGUAGE authDetails = fldao.getforeign_languageByid(Integer.parseInt(updateid));
		Mmap.put("Edit_Foreign_LanguageCMD", authDetails);
		Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Foreign_Langauge_Tiles");
	}

	// -------------------------Edit Foreign_Language Action-----------------------------//

	@RequestMapping(value = "/Edit_Foreign_LanguageAction", method = RequestMethod.POST)
	public ModelAndView Edit_Foreign_LanguageAction(@ModelAttribute("Edit_Foreign_LanguageCMD") TB_FOREIGN_LANGUAGE rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String foreign_language_name = request.getParameter("foreign_language_name").trim();
		String status = request.getParameter("status");

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (foreign_language_name.equals("") || foreign_language_name.equals("null") || foreign_language_name.equals(null)) {
			TB_FOREIGN_LANGUAGE authDetails = fldao.getforeign_languageByid((id));
			model.put("Edit_Foreign_LanguageCMD", authDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Please Enter Foreign Language.");
			return new ModelAndView("Edit_Foreign_Langauge_Tiles");
		}
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_FOREIGN_LANGUAGE authDetails = fldao.getforeign_languageByid((id));
			model.put("Edit_Foreign_LanguageCMD", authDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("Edit_Foreign_Langauge_Tiles");
		}*/
		try {
			Query q0 = session1.createQuery(
					"select count(id) from TB_FOREIGN_LANGUAGE where foreign_language_name=:foreign_language_name  and id !=:id and status=:status");
			q0.setParameter("foreign_language_name", foreign_language_name);
			q0.setParameter("id", id);
			q0.setParameter("status", status);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_FOREIGN_LANGUAGE set foreign_language_name=:foreign_language_name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("foreign_language_name", foreign_language_name)
						.setString("modified_by", username).setString("status", status)
						.setDate("modified_date", new Date()).setInteger("id", id);
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
		return new ModelAndView("redirect:Foreign_Language_Url");
	}

}
