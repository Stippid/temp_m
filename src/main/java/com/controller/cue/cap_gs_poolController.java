
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
import com.dao.cue.Cap_gs_poolDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_GS_POOL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class cap_gs_poolController {
	@Autowired
	private Cap_gs_poolDAO poolDAO;// = new Cap_gs_poolDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;

	private cueContoller M = new cueContoller();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/cap_gs_pool", method = RequestMethod.GET)
	public ModelAndView cap_gs_pool(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cap_gs_pool", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Mmap.put("msg", msg);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("cap_gs_poolTiles", "Cap_gs_poolActionCMD", new CUE_GS_POOL());
	}

	@RequestMapping(value = "/admin/cap_gs_pool1", method = RequestMethod.POST)
	public ModelAndView cap_gs_pool1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "scale1", required = false) String scale,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("arm1", arm);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("rank1", rank);
		Mmap.put("scale1", scale);

		List<Map<String, Object>> list = poolDAO.AttributeReportSearchRankCategory1(month, status, year, arm, rank_cat,
				rank, scale, roleType);
		Mmap.put("list", list);
		Mmap.put("listsize", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("cap_gs_poolTiles");
	}

	@RequestMapping(value = "/cap_gs_poolAction", method = RequestMethod.POST)
	public ModelAndView cap_gs_poolAction(@ModelAttribute("Cap_gs_poolActionCMD") CUE_GS_POOL rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		rs.setCreated_by(username);
		rs.setCreated_on(creadtedate);

		int roleid = (Integer) session.getAttribute("roleid");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String rank_cat = request.getParameter("rank_cat");
		String arm = request.getParameter("arm");
		String rank = request.getParameter("rank");
		
		String[] arm_parts = arm.split("\\|"); // split the value using | as delimiter

		String  arm_value = arm_parts[0]; //extract value
		String  arm_name = arm_parts[1]; //extract name

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();

		try {
			if (rs.getMonth() == "") {
				model.put("msg", "Please Enter Month");
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (validation.checkMonthLength(rs.getMonth()) == false) {
				model.put("msg", validation.monthMSG);
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (rs.getYear() == "") {
				model.put("msg", "Please Enter Year");
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (validation.checkWETypeLength(rs.getYear()) == false) {
				model.put("msg", validation.yearMSG);
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (rs.getArm().equals("0")) {
				model.put("msg", "Please Select Arm");
				return new ModelAndView("redirect:cap_gs_pool");
			}
//			String arm_code = String.format("%04d", Integer.parseInt(rs.getArm()));
//			if (validation.checkArmCodeLength(arm_code) == false) {
//				model.put("msg", validation.arm_codeMSG);
//				return new ModelAndView("redirect:cap_gs_pool");
//			}
			if (rs.getRank_cat() == "") {
				model.put("msg", "Please Enter Category");
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (validation.checkMonthLength(rs.getRank_cat()) == false) {
				model.put("msg", validation.rankcatMSG);
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (rs.getRank() == "") {
				model.put("msg", "Please Enter Rank");
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (validation.checkFormationLength(rs.getRank()) == false) {
				model.put("msg", validation.rankMSG);
				return new ModelAndView("redirect:cap_gs_pool");
			}
			if (rs.getAuth_amt() == 0) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:cap_gs_pool");
			}
			String scale_valid = Double.toString(rs.getAuth_amt());
			if (validation.checkAuth_amtLength(scale_valid) == false) {
				model.put("msg", validation.auth_amtMSG);
				return new ModelAndView("redirect:cap_gs_pool");
			}

			Boolean e = checkDetailsOfExistingData(month, year, arm_value, rank_cat, rank);
			if (e.equals(false)) {
				rs.setRoleid(roleid);
				rs.setYear(year);
				rs.setMonth(month);
				rs.setArm(arm_value);
				rs.setArm_desc(arm_name);
				rs.setStatus("0");
				sessionHQL.save(rs);
				tx0.commit();
				
				model.put("msg", "Data saved Successfully");
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			tx0.rollback();
		}finally {
			sessionHQL.close();
		}

		List<Map<String, Object>> list = poolDAO.AttributeReportSearchRankCategory1(month,"", year,"","","","","");
		model.put("month1", month);
		model.put("year1", year);
		model.put("list", list);
		model.put("list.size", list.size());
		model.put("getArmNameList", M.getArmNameList());
		model.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("cap_gs_poolTiles");
	}

	@RequestMapping(value = "/search_cap_gs_pool", method = RequestMethod.GET)
	public ModelAndView search_cap_gs_pool(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_cap_gs_pool", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);

		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("search_cap_gs_poolTiles");
	}

	@RequestMapping(value = "/ApprovedGsPoolUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedGsPoolUrl(@ModelAttribute("appid") int appid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "month2", required = false) String month,
			@RequestParam(value = "year2", required = false) String year,
			@RequestParam(value = "arm2", required = false) String arm,
			@RequestParam(value = "scale2", required = false) String scale,
			@RequestParam(value = "rank_cat2", required = false) String rank_cat,
			@RequestParam(value = "rank2", required = false) String rank,
			@RequestParam(value = "status2", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", poolDAO.setApprovedGsPool(appid));
		Mmap.put("scale1", scale);
		Mmap.put("rank1", rank);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("arm1", arm);
		Mmap.put("year1", year);
		Mmap.put("month1", month);
		Mmap.put("status1", status);

		List<Map<String, Object>> list = poolDAO.AttributeReportSearchRankCategory(status, month, year, arm, rank_cat,
				rank, roleType);

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("search_cap_gs_poolTiles");
	}

	@RequestMapping(value = "/deleteGsPoolUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteGsPoolUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(poolDAO.setDeleteGsPool(deleteid));
		return list2;
	}

	@RequestMapping(value = "/updateGsPoolUrl")
	public ModelAndView updatePArmUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("CapGsPoolEditCMD", poolDAO.getCUE_GS_POOLByid(updateid));
		model.put("msg", msg);
		model.put("getArmNameList", M.getArmNameList());
		model.put("getTypeofRankcategory", M.getTypeofRankcategory());
		return new ModelAndView("edit_cap_gs_poolTiles");
	}

	@RequestMapping(value = "/updateGsPoolUrlAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView CUE_GS_POOL(@ModelAttribute("CapGsPoolEditCMD") CUE_GS_POOL updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {

			if (updateid.getAuth_amt() == 0) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Authorision");
				return new ModelAndView("redirect:updateGsPoolUrl");
			}
			String scale_valid = Double.toString(updateid.getAuth_amt());
			if (validation.checkAuth_amtLength(scale_valid) == false) {
				model.put("msg", validation.auth_amtMSG);
				return new ModelAndView("redirect:updateGsPoolUrl");
			}
			poolDAO.UpdateGsPoolValue(updateid);
			model.put("msg", "Updated Successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("redirect:cap_gs_pool");
	}

	@RequestMapping(value = "/admin/search_cap_gs_pool1", method = RequestMethod.POST)
	public ModelAndView search_cap_gs_pool1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();

		Mmap.put("rank1", rank);
		Mmap.put("rank_cat1", rank_cat);
		Mmap.put("arm1", arm);
		Mmap.put("year1", year);
		Mmap.put("month1", month);
		Mmap.put("status1", status);

		List<Map<String, Object>> list = poolDAO.AttributeReportSearchRankCategory(status, month, year, arm, rank_cat,rank, roleType);
		Mmap.put("list", list);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getTypeofRankcategory", M.getTypeofRankcategory());
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_cap_gs_poolTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfExistingData(String month, String year, String arm, String rank_cat, String rank) {
		String qry = "from CUE_GS_POOL where month=:month and year=:year and arm=:arm and rank_cat=:rank_cat and rank=:rank ";

		List<CUE_GS_POOL> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(qry);
			q.setParameter("month", month);
			q.setParameter("year", year);
			q.setParameter("arm", arm);
			q.setParameter("rank_cat", rank_cat);
			q.setParameter("rank", rank);
			users = (List<CUE_GS_POOL>) q.list();
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

	@RequestMapping(value = "/updaterejectgspool", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectgspool(String remarks, String letter_no, int id) {
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers("cue_gs_pool", remarks, id, letter_no);
		return list2;
	}
}