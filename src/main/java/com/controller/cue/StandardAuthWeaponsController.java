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
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.StandardAuthWeaponDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class StandardAuthWeaponsController {
	@Autowired
	private StandardAuthWeaponDAO sawDAO;

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;
	@Autowired
	private RoleBaseMenuDAO roledao;


	@Autowired
	private Cue_wepe_conditionDAO vetting;	
	
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/standard_auth_for_weapons", method = RequestMethod.GET)
	public ModelAndView standard_auth_for_weapons(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("standard_auth_for_weapons", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("standard_auth_for_weaponsTiles", "standard_auth_for_weaponsActionCMD",
				new CUE_TB_MISO_WEPE_WEAPON_DET());
	}

	@RequestMapping(value = "/admin/standard_auth_for_weapons1", method = RequestMethod.POST)
	public ModelAndView standard_auth_for_weapons1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "auth_weapon1", required = false) String auth_weapon,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		
		
		Mmap.put("we_pe1", we_pe);
		Mmap.put("auth_weapon1", auth_weapon);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("status1", status);

		List<Map<String, Object>> list = sawDAO.AttributeReportSearchStdAuthWpn1(we_pe, auth_weapon, we_pe_no,
				item_seq_no, status, roleType,roleAccess);

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("standard_auth_for_weaponsTiles");
	}

	@RequestMapping(value = "/standard_auth_for_weaponsAction", method = RequestMethod.POST)
	public ModelAndView standard_auth_for_weaponsAction(
			@ModelAttribute("standard_auth_for_weaponsActionCMD") CUE_TB_MISO_WEPE_WEAPON_DET rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String item_seq_no = request.getParameter("item_seq_no");
		String we_pe_no = request.getParameter("we_pe_no");
		String we_pe = request.getParameter("we_pe");

		if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null) || we_pe.equals("undefined")) {
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (validation.checkWETypeLength(we_pe) == false) {
			model.put("msg", validation.wetypeMSG);
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (rs.getWe_pe_no().equals("") || rs.getWe_pe_no() == null || rs.getWe_pe_no().equals(null)
				|| rs.getWe_pe_no().isEmpty()) {
			model.put("msg", "Please Enter WE/PE No");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (validation.checkWepeLength(rs.getWe_pe_no()) == false) {
			model.put("msg", validation.wepenoMSG);
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		String nomenClature = request.getParameter("nomenClature");
		if (nomenClature.equals("") || nomenClature == null || nomenClature.equals(null) || nomenClature.isEmpty()) {
			model.put("msg", "Please enter Nomenclatue(Item Type)");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (rs.getItem_seq_no().equals("") || rs.getItem_seq_no() == null || rs.getItem_seq_no().equals(null)
				|| rs.getItem_seq_no().isEmpty()) {
			model.put("msg", "Please Enter Item Code");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (validation.checkFormationLength(rs.getItem_seq_no()) == false) {
			model.put("msg", validation.itemcodeMSG);
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (rs.getAuth_weapon() == 0.0) {
			model.put("msg", "Please Enter Authorization Code");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		String auth_amt = Double.toString(rs.getAuth_weapon());
		if (validation.checkAuth_amtLength(auth_amt) == false) {
			model.put("msg", validation.auth_amtMSG);
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		if (validation.checkRemarksLength(rs.getRemarks()) == false) {
			model.put("msg", validation.remarksMSG);
			return new ModelAndView("redirect:standard_auth_for_weapons");
		}

		Boolean e = checkDetailsOfWePeNo(we_pe_no, item_seq_no);
		if (e.equals(false)) {
			rs.setCreated_by(username);
			rs.setCreated_on(date);

			int roleid = (Integer) session.getAttribute("roleid");
			rs.setRoleid(roleid);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			rs.setStatus("0");
			int did = (Integer) sessionHQL.save(rs);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			model.put("msg", "Data saved Successfully");
		} else {
			model.put("msg", "Data Already Exists!");
		}

		List<Map<String, Object>> list = sawDAO.AttributeReportSearchStdAuthWpn1("", "", we_pe_no, "", "", "","");

		model.put("list", list);
		model.put("we_pe_no1", we_pe_no);
		model.put("we_pe1", we_pe);
		model.put("item_seq_no1", item_seq_no);
		model.put("list.size()", list.size());
		return new ModelAndView("standard_auth_for_weaponsTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfWePeNo(String we_pe_no, String item_seq_no) {

		String hql = "select distinct we_pe_no from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no ";
		List<CUE_TB_MISO_WEPE_WEAPON_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("item_seq_no", item_seq_no);

			users = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) query.list();
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

	@RequestMapping(value = "/getFootnoteData", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> getFootnoteData(String we_pe_no, String item_seq_no,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no");
		q.setParameter("we_pe_no", we_pe_no);
		q.setParameter("item_seq_no", item_seq_no);

		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> list = (List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getMdfsData", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> getMdfsData(String we_pe_no, String item_seq_no,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no order by we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		q.setParameter("item_seq_no", item_seq_no);

		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> list = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/admin/search_std_auth_weapon1", method = RequestMethod.POST)
	public ModelAndView search_std_auth_weapon1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "auth_weapon1", required = false) Double auth_weapon,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "nomenClature1", required = false) String nomenClature,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("auth_weapon1", auth_weapon);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("nomenClature1", nomenClature);

		List<Map<String, Object>> list = sawDAO.AttributeReportSearchStdAuthWpn(auth_weapon, we_pe_no, item_seq_no,
				status, roleType, roleAccess);

		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());

		return new ModelAndView("search_std_auth_weaponTiles");
	}

	@RequestMapping(value = "/search_std_auth_weapon", method = RequestMethod.GET)
	public ModelAndView Search_std_auth_weapon(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_std_auth_weapon", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_std_auth_weaponTiles");
	}

	@RequestMapping(value = "/ApprovedPSawUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedSawUrl(@ModelAttribute("appid") int appid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "we_pe2", required = false) String we_pe,
			@RequestParam(value = "auth_weapon2", required = false) Double auth_weapon,
			@RequestParam(value = "item_seq_no2", required = false) String item_seq_no,
			@RequestParam(value = "nomenClature2", required = false) String nomenClature,
			@RequestParam(value = "status2", required = false) String status) {

		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String username = session.getAttribute("username").toString();
		String mst = sawDAO.setApprovedSAW(appid, username,we_pe_no);
		if(mst.equals("Approved Successfully")) {
			vetting.updateVettingDtl( we_pe_no, "3");
		}
		Mmap.put("msg",mst );
		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("auth_weapon1", auth_weapon);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("nomenClature1", nomenClature);

		List<Map<String, Object>> list = sawDAO.AttributeReportSearchStdAuthWpn(auth_weapon, we_pe_no, item_seq_no,
				status, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_std_auth_weaponTiles");
	}

	@RequestMapping(value = "/deletePSawUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deletePSawUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(sawDAO.setDeleteSAW(deleteid));
		return list2;
	}

	@RequestMapping(value = "/updatePSawUrl")
	public ModelAndView updatePSawUrl(@ModelAttribute("updateid") int updateid, ModelMap model,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", msg);
		model.put("stAuthWeaponsEditCMD", sawDAO.getCUE_TB_MISO_WEPE_WEAPON_DETByid(updateid));
		return new ModelAndView("edit_std_auth_weaponsTiles");
	}

	@RequestMapping(value = "/standard_auth_for_weaponsEditAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView CUE_TB_MISO_WEPE_WEAPON_DET(
			@ModelAttribute("stAuthWeaponsEditCMD") CUE_TB_MISO_WEPE_WEAPON_DET updateid, HttpServletRequest request,ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session,HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		Session sessioncount = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = sessioncount.beginTransaction();
		Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no and id !=:id");
		q1.setParameter("we_pe_no", updateid.getWe_pe_no());
		q1.setParameter("item_seq_no", updateid.getItem_seq_no());
		q1.setParameter("id", updateid.getId());
		@SuppressWarnings("unchecked")
		Long count2 = (Long) q1.uniqueResult();
		model.put("count", count2);
		transaction.commit();
		sessioncount.close();
		String username = session.getAttribute("username").toString();
		if (count2 == 0) {
			String nomenClature = request.getParameter("nomenClature");
			if (nomenClature.equals("") || nomenClature == null || nomenClature.equals(null) || nomenClature.isEmpty()) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please enter Nomenclatue(Item Type)");
				return new ModelAndView("redirect:updatePSawUrl");
			}

			if (updateid.getItem_seq_no().equals("") || updateid.getItem_seq_no() == null || updateid.getItem_seq_no().equals(null)
					|| updateid.getItem_seq_no().isEmpty()) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Item Code");
				return new ModelAndView("redirect:updatePSawUrl");
			}
			
			if (validation.checkFormationLength(updateid.getItem_seq_no()) == false) {
				model.put("updateid", updateid.getId());
				model.put("msg", validation.itemcodeMSG);
				return new ModelAndView("redirect:updatePSawUrl");
			}

			String auth_amt = Double.toString(updateid.getAuth_weapon());
			if (validation.checkAuth_amtLength(auth_amt) == false) {
				model.put("updateid", updateid.getId());
				model.put("msg", validation.auth_amtMSG);
				return new ModelAndView("redirect:updatePSawUrl");
			}
			
			if (updateid.getAuth_weapon() == 0.0 || updateid.getAuth_weapon() == 0) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Authorization");
				return new ModelAndView("redirect:updatePSawUrl");
			}
			
			if (validation.checkRemarksLength(updateid.getRemarks()) == false) {
				model.put("updateid", updateid.getId());
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:updatePSawUrl");
			}
			sawDAO.UpdateAllValue(updateid, username);
			model.put("msg", "Updated Successfully");
			return new ModelAndView("redirect:standard_auth_for_weapons");
		} else {
			model.put("msg", "Data already exist !");
			model.put("stAuthWeaponsEditCMD", sawDAO.getCUE_TB_MISO_WEPE_WEAPON_DETByid(updateid.getId()));
			return new ModelAndView("edit_std_auth_weaponsTiles");
		}
	}

	@RequestMapping(value = "/updaterejectweapon", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectweapon(HttpSession session,String remarks, String letter_no, int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers2("cue_tb_miso_wepe_weapon_det", remarks, id,
				letter_no,username);
		return list2;
	}

}