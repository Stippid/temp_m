package com.controller.Civilian_Master;

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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Master.Cause_Of_Non_Effective_CivilianDAO;
import com.models.psg.Civilian_Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Cause_of_Non_EffectiveController_Civilian {

	Psg_CommonController mcommon = new Psg_CommonController();
	@Autowired
	Cause_Of_Non_Effective_CivilianDAO CNECivilianDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Cause_of_non_eff_civilianUrl", method = RequestMethod.GET)
	public ModelAndView Cause_of_non_eff_civilianUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		

		Mmap.put("list", CNECivilianDao.search_Cause_Of_Non_Effe_civilian("", "0", "active", "REGULAR"));
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
		Mmap.put("msg", msg);
		return new ModelAndView("Cause_of_non_effective_CivilianTiles");
	}

	@RequestMapping(value = "/Cause_of_non_effective_CivilianAction", method = RequestMethod.POST)
	public ModelAndView Cause_of_non_effectiveJCOAction(
			@ModelAttribute("Cause_of_non_effectiveCMD") TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN com,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int Id = com.getId() > 0 ? com.getId() : 0;

		String username = session.getAttribute("username").toString();
		String causes_name = request.getParameter("causes_name").trim();
		int type_of_civilian = Integer.parseInt(request.getParameter("type_of_civilian"));
		String type_of_regular_or_nonregular = request.getParameter("type_of_reg");
		String status = request.getParameter("status");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (causes_name.equals("") || causes_name.equals("null") || causes_name.equals(null)) {
			model.put("msg", "Please Enter Causes Name");
			return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
		}
		if (causes_name.length() > 50) {
			model.put("msg", "Causes Name Length should be less than 50 characters");
			return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
		}
		if (type_of_civilian == 0) {
			model.put("msg", "Please Select Type of Civilian");
			return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
		}
		if (type_of_regular_or_nonregular.equals("0") || type_of_regular_or_nonregular == "0"
				|| type_of_regular_or_nonregular == null || type_of_regular_or_nonregular.equals(null)) {
			model.put("msg", "Please Select Type of Regular/Non-Regular");
			return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
		}
		if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
		}
		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN where upper(causes_name)=:causes_name and type_of_civilian=:type_of_civilian AND type_of_regular_or_nonregular=:type_of_regular_or_nonregular AND status=:status AND id!=:id");
			q0.setParameter("causes_name", causes_name.toUpperCase());
			q0.setParameter("type_of_civilian", type_of_civilian);
			q0.setParameter("type_of_regular_or_nonregular", type_of_regular_or_nonregular);
			q0.setParameter("status", status);
			q0.setParameter("id", Id);
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_date(new Date());
				com.setCauses_name(causes_name);
				com.setType_of_civilian(type_of_civilian);
				com.setType_of_regular_or_nonregular(type_of_regular_or_nonregular);
				com.setStatus(status);
				if (c == 0) {
					sessionHQL.save(com);
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
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
	}

	@RequestMapping(value = "/admin/Search_Causeof_non_eff_Civilian", method = RequestMethod.POST)
	public ModelAndView Search_Causeof_non_eff_Civilian(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "causes_name1", required = false) String causes_name1,
			@RequestParam(value = "type_of_civilian1", required = false) String type_of_civilian1,
			@RequestParam(value = "status1", required = false) String status1,
			@RequestParam(value = "type_of_reg1", required = false) String type_of_regular_or_nonregular1) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
		Mmap.put("causes_name1", causes_name1);
		Mmap.put("type_of_civilian1", type_of_civilian1);
		Mmap.put("status1", status1);
		Mmap.put("type_of_reg1", type_of_regular_or_nonregular1);
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
		ArrayList<ArrayList<String>> list = CNECivilianDao.search_Cause_Of_Non_Effe_civilian(causes_name1,
				type_of_civilian1, status1, type_of_regular_or_nonregular1);
		Mmap.put("list", list);
		return new ModelAndView("Cause_of_non_effective_CivilianTiles");
	}

	@RequestMapping(value = "/Edit_Causeof_non_eff_Civilian",method = RequestMethod.POST)
	public ModelAndView Edit_Causeof_non_eff_Civilian(@ModelAttribute("id2") String updateid, ModelMap Mmap,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}



		TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN CN = CNECivilianDao
				.getCauNonEffeCivilianByid(Integer.parseInt(updateid));
		Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", CN);
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
	}

	@RequestMapping(value = "/Edit_Cause_of_non_effective_CivilianAction", method = RequestMethod.POST)
	public ModelAndView Edit_Cause_of_non_effectiveJCOAction(
			@ModelAttribute("Edit_Cause_of_non_effective_CivilianCMD") TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN rs,
			HttpServletRequest request, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String causes_name = request.getParameter("causes_name").trim();
		int type_of_civilian = Integer.parseInt(request.getParameter("type_of_civilian"));
		String type_of_regular_or_nonregular = request.getParameter("type_of_reg");
		String status = request.getParameter("status");

		if (causes_name.equals("") || causes_name.equals("null") || causes_name.equals(null)) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN authDetails = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", authDetails);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Please Enter Causes Name");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}
		if (causes_name.length() > 50) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN authDetails = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", authDetails);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Causes Name Length should be less than 50 characters");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}
		if (type_of_civilian == 0) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN authDetails = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", authDetails);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Please Select Type of Civilian");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}
		if (type_of_regular_or_nonregular.equals("0") || type_of_regular_or_nonregular == "0"
				|| type_of_regular_or_nonregular == null || type_of_regular_or_nonregular.equals(null)) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN authDetails = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", authDetails);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Please Select Type of Regular/Non-Regular");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN authDetails = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effective_CivilianCMD", authDetails);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}*/

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		if (causes_name.equals("") || causes_name.equals("null") || causes_name.equals(null)) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN CN = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effectiveCMD", CN);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Please Enter Causes Name");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}

		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN CN = CNECivilianDao.getCauNonEffeCivilianByid(id);
			Mmap.put("Edit_Cause_of_non_effectiveCMD", CN);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCauseOfnoneffList", mcommon.getCauseOfnoneffList());
			Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}*/

		try {

			Query q0 = session1.createQuery(
					"select count(id) from TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN where upper(causes_name)=:causes_name and type_of_civilian=:type_of_civilian AND type_of_regular_or_nonregular=:type_of_regular_or_nonregular and status=:status AND id!=:id");
			q0.setParameter("causes_name", causes_name.toUpperCase());
			q0.setParameter("type_of_civilian", type_of_civilian);
			q0.setParameter("type_of_regular_or_nonregular", type_of_regular_or_nonregular);
			q0.setParameter("status", status);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN set causes_name=:causes_name,type_of_civilian=:type_of_civilian,status=:status,type_of_regular_or_nonregular=:type_of_regular_or_nonregular,"
						+ "modified_by=:modified_by,modified_date=:modified_date" + " where id=:id";

				Query query = session1.createQuery(hql).setString("causes_name", causes_name)
						.setInteger("type_of_civilian", type_of_civilian).setString("status", rs.getStatus())
						.setString("type_of_regular_or_nonregular", type_of_regular_or_nonregular)
						.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg == "1") {
					Mmap.put("msg", "Data Updated Successfully.");
				} else {
					Mmap.put("msg", "Data Not Updated.");
				}
			} else {
				Mmap.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
	}

	@RequestMapping(value = "/delete_Causeof_non_eff_Civilian", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Causeof_non_eff_Civilian(@ModelAttribute("id1") int id,
			BindingResult result, HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);
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

			String hqlUpdate = "update TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
		return new ModelAndView("redirect:Cause_of_non_eff_civilianUrl");
	}
}
