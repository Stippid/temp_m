package com.controller.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import com.dao.cue.AppTradesDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class AppTradesController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;

	@Autowired
	private AppTradesDAO aptDAO;// = new AppTradesDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;

	cueContoller M = new cueContoller();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/app_trades", method = RequestMethod.GET)
	public ModelAndView app_trades(ModelMap Mmap, HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("app_trades", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("getcategoryListFinal", M.getcategoryListFinal());
		Mmap.put("msg", msg);
		return new ModelAndView("app_tradesTiles", "app_tradesActionCMD", new CUE_TB_PSG_RANK_APP_MASTER());
	}

	@RequestMapping(value = "/admin/app_trades1", method = RequestMethod.POST)
	public ModelAndView app_trades1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "parent_code1", required = false) String parent_code,
			@RequestParam(value = "level_in_hierarchy1", required = false) String level_in_hierarchy,
			@RequestParam(value = "description1", required = false) String description,
			@RequestParam(value = "status1", required = false) String status)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status);
		Mmap.put("parent_code1", parent_code);
		Mmap.put("level_in_hierarchy1", level_in_hierarchy);
		Mmap.put("description1", description);
		List<Map<String, Object>> list = aptDAO.AttributeReportSearchRANKCategory1(status, parent_code,
				level_in_hierarchy, description, roleType);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size", list.size());
		Mmap.put("getcategoryListFinal", M.getcategoryListFinal());
		return new ModelAndView("app_tradesTiles");
	}

	@RequestMapping(value = "/app_tradesAction", method = RequestMethod.POST)
	public ModelAndView app_tradesAction(@ModelAttribute("app_tradesActionCMD") CUE_TB_PSG_RANK_APP_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int roleid = (Integer) session.getAttribute("roleid");
		String parent_code = request.getParameter("parent_code");
		String description = request.getParameter("description");
		String level_in_hierarchy = request.getParameter("level_in_hierarchy");

		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();

		try {
			if (rs.getParent_code().equals("Select")) {
				model.put("msg", "Please Select Category");
				return new ModelAndView("redirect:app_trades");
			}
			if (validation.checkParent_codeLength(rs.getParent_code()) == false) {
				model.put("msg", validation.catMSG);
				return new ModelAndView("redirect:app_trades");
			}
			if (rs.getLevel_in_hierarchy() == null) {
				model.put("msg", "Please Select Type of Category");
				return new ModelAndView("redirect:app_trades");
			}
			if (validation.checkLevel_in_hierarchyLength(rs.getLevel_in_hierarchy()) == false) {
				model.put("msg", validation.typeofcatMSG);
				return new ModelAndView("redirect:app_trades");
			}
			if (rs.getLevel_in_hierarchy().equals("APPOINTMENT")) {
				if (rs.getDescription().equals("")) {
					model.put("msg", "Please Enter Description of APPT/ Trades");
					return new ModelAndView("redirect:app_trades");

				}
			}
			if (rs.getLevel_in_hierarchy().equals("RANK")) {

				if (rs.getDescription().equals("")) {
					model.put("msg", "Please Enter Description of RANK");
					return new ModelAndView("redirect:app_trades");
				}
			}
			if (validation.checkWetPetLength(rs.getDescription()) == false) {
				model.put("msg", validation.descMSG);
				return new ModelAndView("redirect:app_trades");
			}
			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:app_trades");

			}
			Boolean e = checkDetailsAppTrades(parent_code, description, level_in_hierarchy);

			if (e.equals(false)) {
				rs.setRoleid(roleid);
				rs.setCreation_by(username);
				rs.setCreation_date(date);
				rs.setStatus("0");
				rs.setStatus_active("Active");

				Query q = session0.createQuery("select max(id) from CUE_TB_PSG_RANK_APP_MASTER");
				@SuppressWarnings("unchecked")
				List<Integer> list = (List<Integer>) q.list();
				Integer retid = list.get(0) + 1;
				String code = retid.toString();
				int len = code.length();
				if (len == 1) {
					code = code + "1234";
				} else if (len == 2) {
					code = code + "123";
				} else if (len == 3) {
					code = code + "12";
				} else if (len == 4) {
					code = code + "1";
				}
				rs.setCode(code);
				
				session0.save(rs);
				tx0.commit();
				model.put("msg", "Data saved Successfully");
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			tx0.rollback();
		}finally {
			session0.close();
		}

		List<Map<String, Object>> list = aptDAO.AttributeReportSearchRANKCategory1("", parent_code, level_in_hierarchy,
				description, "");
		model.put("parent_code1", parent_code);
		model.put("level_in_hierarchy1", level_in_hierarchy);
		model.put("description1", description);
		model.put("list", list);
		model.put("list.size", list.size());
		model.put("getcategoryListFinal", M.getcategoryListFinal());
		return new ModelAndView("app_tradesTiles");
	}

	@RequestMapping(value = "/getdesciption", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getnomenClature(String val, String parent_code, String description,
			HttpSession sessionA)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct description from CUE_TB_PSG_RANK_APP_MASTER where description is not null and upper(level_in_hierarchy) =:level_in_hierarchy and parent_code=:parent_code and upper(description) like :description order by description")
				.setMaxResults(10);
		if (val.equals("APPOINTMENT")) {
			q.setParameter("level_in_hierarchy", "APPOINTMENT");
		} else {
			q.setParameter("level_in_hierarchy", "RANK");
		}
		q.setParameter("parent_code", parent_code);
		q.setParameter("description", description.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionA, list1);

	}

	@RequestMapping(value = "/search_appt_trades", method = RequestMethod.GET)
	public ModelAndView Search_appt_trades(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_appt_trades", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("getcategoryListFinal", M.getcategoryListFinal());
		Mmap.put("msg", msg);
		return new ModelAndView("search_appt_tradesTiles");
	}

	@RequestMapping(value = "/ApprovedAPPTUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedAPPTUrl(@ModelAttribute("appid") int appid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "parent_code2", required = false) String parent_code,
			@RequestParam(value = "level_in_hierarchy2", required = false) String level_in_hierarchy,
			@RequestParam(value = "description2", required = false) String description,
			@RequestParam(value = "status2", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		String username = session.getAttribute("username").toString();
		Mmap.put("msg", aptDAO.setApprovedAPPT(appid,username));
		Mmap.put("status1", status);
		Mmap.put("parent_code1", parent_code);
		Mmap.put("level_in_hierarchy1", level_in_hierarchy);
		Mmap.put("description1", description);
		List<Map<String, Object>> list = aptDAO.AttributeReportSearchRANKCategory(status, parent_code,
				level_in_hierarchy, description, roleType);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("getcategoryListFinal", M.getcategoryListFinal());
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_appt_tradesTiles");
	}

	@RequestMapping(value = "/deleteAPPTUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteAPPTUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(aptDAO.setDeleteAPPT(deleteid));
		return list2;
	}

	@RequestMapping(value = "/updateAPPTUrl")
	public ModelAndView updateAPPTUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("apptTradesEditCMD", aptDAO.getCUE_TB_PSG_RANK_APP_MASTERByid(updateid));
		model.put("msg", msg);
		model.put("getcategoryListFinal", M.getcategoryListFinal());
		return new ModelAndView("edit_appt_tradesTiles");
	}

	@RequestMapping(value = "/app_tradesEditAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView CUE_TB_PSG_RANK_APP_MASTER(
			@ModelAttribute("apptTradesEditCMD") CUE_TB_PSG_RANK_APP_MASTER updateid, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session11,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {

			String username = session11.getAttribute("username").toString();
			String parent_code = request.getParameter("parent_code");
			String description = request.getParameter("description");
			String level_in_hierarchy = request.getParameter("level_in_hierarchy");

			Session sessioncount = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx1 = sessioncount.beginTransaction();

			Query q = sessioncount.createQuery(
					"select count(*) from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy)=:level_in_hierarchy and description=:description and parent_code=:parent_code and id !=:id ");
			q.setParameter("level_in_hierarchy", level_in_hierarchy);
			q.setParameter("description", description);
			q.setParameter("parent_code", parent_code);
			q.setParameter("id", updateid.getId());
			Long count1 = (Long) q.uniqueResult();
			model.put("count", count1);
			tx1.commit();
			sessioncount.close();
			if (count1 == 0) {
				int id = updateid.getId();
				if (updateid.getLevel_in_hierarchy().equals("APPOINTMENT")) {

					if (updateid.getDescription().equals("") || updateid.getDescription() == "") {
						model.put("updateid", id);
						model.put("msg", "Please Enter Description of APPT/ Trades");
						return new ModelAndView("redirect:updateAPPTUrl");
					}
				}
				if (updateid.getLevel_in_hierarchy().equals("RANK")) {

					if (updateid.getDescription().equals("") || updateid.getDescription() == "") {
						model.put("updateid", id);
						model.put("msg", "Please Enter Description of RANK");
						return new ModelAndView("redirect:updateAPPTUrl");

					}
				}
				if (validation.checkWetPetLength(updateid.getDescription()) == false) {
					model.put("msg", validation.descMSG);
					return new ModelAndView("redirect:updateAPPTUrl");
				}
				Session session = HibernateUtilNA.getSessionFactory().openSession();
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				Transaction tx = session.beginTransaction();
				String hql = "update CUE_TB_PSG_RANK_APP_MASTER  set level_in_hierarchy=:level_in_hierarchy ,description=:description,modify_by=:modify_by,modify_date=:modify_date,status='0' where id=:id";
				Query query = session.createQuery(hql).setString("level_in_hierarchy", level_in_hierarchy)
						.setString("description", description).setString("modify_by", username)
						.setString("modify_date", modifydate).setInteger("id", updateid.getId());
				int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if (rowCount > 0) {
					model.put("msg", "Updated Successfully");
				} else {
					model.put("msg", "Updated not Successfully");
				}
				return new ModelAndView("redirect:app_trades");
			}

			else {
				model.put("msg", "Data already exist !");
				model.put("edit_Inc_dec_footnotesActionCMD",aptDAO.getCUE_TB_PSG_RANK_APP_MASTERByid(updateid.getId()));
				model.put("getcategoryListFinal", M.getcategoryListFinal());
				return new ModelAndView("edit_appt_tradesTiles");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("redirect:app_trades");

	}

	@RequestMapping(value = "/admin/search_appt_trades1", method = RequestMethod.POST)
	public ModelAndView search_appt_trades1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "parent_code1", required = false) String parent_code,
			@RequestParam(value = "level_in_hierarchy1", required = false) String level_in_hierarchy,
			@RequestParam(value = "description1", required = false) String description,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();

		Mmap.put("status1", status);
		Mmap.put("parent_code1", parent_code);
		Mmap.put("level_in_hierarchy1", level_in_hierarchy);
		Mmap.put("description1", description);
		List<Map<String, Object>> list = aptDAO.AttributeReportSearchRANKCategory(status, parent_code,
				level_in_hierarchy, description, roleType);
		Mmap.put("getcategoryListFinal", M.getcategoryListFinal());
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_appt_tradesTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsAppTrades(String parent_code, String description, String level_in_hierarchy) {
		String hql = "select distinct level_in_hierarchy from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy)=:level_in_hierarchy and description=:description and parent_code=:parent_code  order by level_in_hierarchy ";
		List<CUE_TB_PSG_RANK_APP_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(hql);
			q.setParameter("level_in_hierarchy", level_in_hierarchy);
			q.setParameter("description", description);
			q.setParameter("parent_code", parent_code);
			users = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
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

	@RequestMapping(value = "/updaterejecttrade", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejecttrade(String remarks, String letter_no, int id) {
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers("cue_tb_psg_rank_app_master", remarks, id,
				letter_no);
		return list2;
	}
}
