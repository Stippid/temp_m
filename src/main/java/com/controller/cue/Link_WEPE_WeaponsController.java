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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.cue.WEPELinkSusDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;

import com.models.CUE_TB_WEPE_link_sus_weapon_mdfs;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_WEPE_WeaponsController {

	@Autowired
	private WEPELinkSusDAO linkdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	cueContoller M = new cueContoller();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/link_WEPE_sus_Wep", method = RequestMethod.GET)
	public ModelAndView link_WEPE_sus_Wep(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("link_WEPE_sus_Wep", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_sus_no1", roleSusNo);
			Mmap.put("unit_name1", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

			List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail2("", roleSusNo, "", "");
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("msg", msg);
		return new ModelAndView("link_WEPE_sus_WepTiles", "link_WEPE_WepCmd", new CUE_TB_WEPE_link_sus_perstransweapon());
	}

	@RequestMapping(value = "/getMoreModFootCmdWep", method = RequestMethod.POST)
	public ModelAndView getMoreModFootCmdWep(@ModelAttribute("wepe_id") String wepe_wep_no_id,
			@ModelAttribute("sus_no_id") String sus_no_id, HttpServletRequest request, ModelMap model,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
		model.put("table_view_wep", linkdao.getwepepersModiDetailsReport(wepe_wep_no_id, "cue_tb_miso_wepe_weapons_mdfs"));
		model.put("table_view_wep_footnotes", linkdao.getwepeweaponModiDetailsFootnotesReport(wepe_wep_no_id));
		model.put("sus_no_w", sus_no_id);
		model.put("wepe_pers_no_w", wepe_wep_no_id);
		model.put("unit_name_w", request.getParameter("unit_id"));
		model.put("radio_doc_w", request.getParameter("radio_doc_id"));
		return new ModelAndView("link_WEPE_sus_WepTiles");
	}

//	@RequestMapping(value = "/link_WEPE_WepAct", method = RequestMethod.POST)
//	public ModelAndView link_WEPE_WepAct(@ModelAttribute("link_WEPE_WepCmd") CUE_TB_WEPE_link_sus_perstransweapon rs,
//			HttpServletRequest request, ModelMap model, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg)
//			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
//			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
//		int roleid = (Integer) session.getAttribute("roleid");
//		String username = session.getAttribute("username").toString();
//		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//		String unit_sus_no_wep = request.getParameter("sus_no");
//		String wepe_wep_no = request.getParameter("wepe_weapon_no");
//
//		if (M.getCUEUnitsSUSNoActiveList(session, unit_sus_no_wep).size() == 0) {
//			model.put("msg", "Please Enter Active SUS No");
//			return new ModelAndView("redirect:link_WE_PE_unit");
//		}
//
//		String we_pe = request.getParameter("radio_doc");
//		if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
//			model.put("msg", "Please Select Document");
//			return new ModelAndView("redirect:link_WEPE_sus_Wep");
//		}
//		if (rs.getWepe_weapon_no() == "") {
//			model.put("msg", "Please Enter WE/PE No");
//			return new ModelAndView("redirect:link_WEPE_sus_Wep");
//		}
//
//		Boolean b = linkdao.isSus_noExist(unit_sus_no_wep);
//		if (b.equals(false)) {
//			if (unit_sus_no_wep.length() > 8) {
//				model.put("msg", "Please Enter Maximum 8 charactors");
//			} else {
//				rs.setRoleid(roleid);
//				rs.setCreated_on_wepon(creadtedate);
//				rs.setCreated_by_wepon(username);
//				rs.setStatus_weap("0");
//				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
//				sessionHQL1.beginTransaction();
//				int did = (Integer) sessionHQL1.save(rs);
//				sessionHQL1.getTransaction().commit();
//				sessionHQL1.close();
//			}
//		} else {
//			Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
//			Transaction tx = sessionHQL2.beginTransaction();
//			String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.wepe_weapon_no = :wepe_weapon_no ,created_on_wepon =:created_on_wepon ,created_by_wepon = :created_by_wepon , status_weap =:status_weap,roleid=:roleid where c.sus_no =:unit_sus_no_wep ";
//			int up_id = sessionHQL2.createQuery(hqlUpdate).setString("wepe_weapon_no", wepe_wep_no)
//					.setString("created_on_wepon", creadtedate).setString("created_by_wepon", username)
//					.setString("status_weap", "0").setInteger("roleid", roleid)
//					.setString("unit_sus_no_wep", unit_sus_no_wep).executeUpdate();
//			tx.commit();
//			sessionHQL2.close();
//		}
//
//		String ch = request.getParameter("id_check_wep_hidden");
//
//		String ch_foot = request.getParameter("id_wep_foot_hidden");
//		Boolean f_k = isdetailFKlink_weapon(unit_sus_no_wep);
//		if (ch != null && !ch.toString().equals("")) {
//			ch = ch.replace("&#39;", "'");
//			if (f_k.equals(false)) {
//				rs.setCreated_by(username);
//				rs.setCreated_on(creadtedate);
//
//				String ch1[] = ch.split(",");
//				for (int i = 0; i < ch1.length; i++) {
//					Session session1 = HibernateUtil.getSessionFactory().openSession();
//					Transaction tx = session1.beginTransaction();
//					Query qry = session1.createSQLQuery(
//							"insert into CUE_TB_WEPE_link_sus_weapon_mdfs (sus_no ,created_by ,created_on , we_pe_no,status,modification)"
//									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
//					qry.setParameter("a1", unit_sus_no_wep);
//					qry.setParameter("a2", username);
//					qry.setParameter("a3", creadtedate);
//					qry.setParameter("a4", wepe_wep_no);
//					qry.setParameter("a5", "0");
//					qry.setParameter("a6", ch1[i].replace("'", ""));
//					qry.executeUpdate();
//					tx.commit();
//					session1.close();
//				}
//			}
//		}
//		Boolean f_k_foot = isdetailFKlink_Weaponfoot(unit_sus_no_wep);
//		if (ch_foot != null && !ch_foot.toString().equals("")) {
//
//			if (f_k_foot.equals(false)) {
//
//				String ch_f1[] = ch_foot.split(",");
//				for (int i = 0; i < ch_f1.length; i++) {
//					Session session1 = HibernateUtil.getSessionFactory().openSession();
//					Transaction tx = session1.beginTransaction();
//					Query qry = session1.createSQLQuery(
//							"insert into CUE_TB_WEPE_link_sus_weapon_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,fk_weapon_foot)"
//									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
//					qry.setParameter("a1", unit_sus_no_wep);
//					qry.setParameter("a2", username);
//					qry.setParameter("a3", creadtedate);
//					qry.setParameter("a4", wepe_wep_no);
//					qry.setParameter("a5", "0");
//					qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
//					qry.executeUpdate();
//					tx.commit();
//					session1.close();
//				}
//			}
//		}
//		model.put("msg", "Link WEPE Weapon Data Saved and Updated Successfully !");
//
//		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail2(wepe_wep_no, unit_sus_no_wep, "",
//				"");
//
//		model.put("list", list);
//		model.put("wepe_weapon_no1", wepe_wep_no);
//		model.put("unit_sus_no1", unit_sus_no_wep);
//		model.put("radio_doc01", we_pe);
//		model.put("id_check_hidden_txt", ch);
//		model.put("id_check_foot_hidden_txt", ch_foot);
//		return new ModelAndView("link_WEPE_sus_WepTiles");
//	}

	
	
	@RequestMapping(value = "/link_WEPE_WepAct", method = RequestMethod.POST)
	public ModelAndView link_WEPE_WepAct(@ModelAttribute("link_WEPE_WepCmd") CUE_TB_WEPE_link_sus_perstransweapon rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer) session.getAttribute("roleid");
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String unit_sus_no_wep = request.getParameter("sus_no");
		String location = request.getParameter("location1");
		String wepe_wep_no = request.getParameter("wepe_weapon_no");

		if (M.getCUEUnitsSUSNoActiveList(session, unit_sus_no_wep).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:link_WE_PE_unit");
		}

		String we_pe = request.getParameter("radio_doc");
		if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:link_WEPE_sus_Wep");
		}
		if (rs.getWepe_weapon_no() == "") {
			model.put("msg", "Please Enter WE/PE No");
			return new ModelAndView("redirect:link_WEPE_sus_Wep");
		}

//		List<String> list_loc = linkdao.check_locationWeap(wepe_wep_no,unit_sus_no_wep);
//	
//		if(!list_loc.isEmpty())
//		{
//			Boolean result_b=false;
//			for(String s:list_loc)
//			{
//				if(s.equals("t"))
//				{
//					result_b=true;
//					break;
//				}
//		}
//			if(result_b!=true)
//			{
//				model.put("msg", "Location Does Not Match");
//				return new ModelAndView("redirect:link_WEPE_sus_Wep");
//			}
//		}
		
	
		
		
		
		Boolean b = linkdao.isSus_noExist(unit_sus_no_wep);
		if (b.equals(false)) {
			if (unit_sus_no_wep.length() > 8) {
				model.put("msg", "Please Enter Maximum 8 charactors");
			} else {
				rs.setRoleid(roleid);
				rs.setCreated_on_wepon(creadtedate);
				rs.setCreated_by_wepon(username);
				rs.setStatus_weap("0");
				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int did = (Integer) sessionHQL1.save(rs);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
			}
		} else {
			Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL2.beginTransaction();
			String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.wepe_weapon_no = :wepe_weapon_no ,created_on_wepon =:created_on_wepon ,created_by_wepon = :created_by_wepon , status_weap =:status_weap,roleid=:roleid where c.sus_no =:unit_sus_no_wep ";
			int up_id = sessionHQL2.createQuery(hqlUpdate).setString("wepe_weapon_no", wepe_wep_no)
					.setString("created_on_wepon", creadtedate).setString("created_by_wepon", username)
					.setString("status_weap", "0").setInteger("roleid", roleid)
					.setString("unit_sus_no_wep", unit_sus_no_wep).executeUpdate();
			tx.commit();
			sessionHQL2.close();
		}

		String ch = request.getParameter("id_check_wep_hidden");

		String ch_foot = request.getParameter("id_wep_foot_hidden");
		Boolean f_k = isdetailFKlink_weapon(unit_sus_no_wep);
		if (ch != null && !ch.toString().equals("")) {
			ch = ch.replace("&#39;", "'");
			if (f_k.equals(false)) {
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);

				String ch1[] = ch.split(",");
				for (int i = 0; i < ch1.length; i++) {
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					Query qry = session1.createSQLQuery(
							"insert into CUE_TB_WEPE_link_sus_weapon_mdfs (sus_no ,created_by ,created_on , we_pe_no,status,modification)"
									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", unit_sus_no_wep);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", wepe_wep_no);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", ch1[i].replace("'", ""));
					qry.executeUpdate();
					tx.commit();
					session1.close();
				}
			}
		}
		Boolean f_k_foot = isdetailFKlink_Weaponfoot(unit_sus_no_wep);
		if (ch_foot != null && !ch_foot.toString().equals("")) {

			if (f_k_foot.equals(false)) {

				String ch_f1[] = ch_foot.split(",");
				for (int i = 0; i < ch_f1.length; i++) {
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					Query qry = session1.createSQLQuery(
							"insert into CUE_TB_WEPE_link_sus_weapon_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,fk_weapon_foot)"
									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", unit_sus_no_wep);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", wepe_wep_no);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
					qry.executeUpdate();
					tx.commit();
					session1.close();
				}
			}
		}
		model.put("msg", "Link WEPE Weapon Data Saved and Updated Successfully !");

		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail2(wepe_wep_no, unit_sus_no_wep, "",
				"");

		model.put("list", list);
		model.put("wepe_weapon_no1", wepe_wep_no);
		model.put("unit_sus_no1", unit_sus_no_wep);
		model.put("radio_doc01", we_pe);
		model.put("id_check_hidden_txt", ch);
		model.put("id_check_foot_hidden_txt", ch_foot);
		return new ModelAndView("link_WEPE_sus_WepTiles");
	}	

	public Boolean isdetailFKlink_weapon(String unit_sus_no) {
		String hql = "delete FROM CUE_TB_WEPE_link_sus_weapon_mdfs m where m.sus_no=:unit_sus_no  ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("unit_sus_no", unit_sus_no);

			query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		return false;
	}

	public Boolean isdetailFKlink_Weaponfoot(String unit_sus_no) {
		String hql = "delete FROM CUE_TB_WEPE_link_sus_weapon_footnotes m where m.sus_no=:unit_sus_no  ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("unit_sus_no", unit_sus_no);

			query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		}
		return false;
	}

	@RequestMapping(value = "/viewMoreDetailModiWeap", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> viewMoreDetailModiWeap(HttpSession session,
			HttpServletRequest request, @RequestParam String mod) {
		String wepe_wep_no = request.getParameter("wepe_pers_no");
		return linkdao.getViewmoreModiWepDetailsReport(wepe_wep_no, mod);
	}

	@RequestMapping(value = "/Search_link_WEPE_Wep", method = RequestMethod.GET)
	public ModelAndView Search_link_WEPE_Wep(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_link_WEPE_Wep", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/admin/linkWEPEWeap1", method = RequestMethod.POST)
	public ModelAndView linkWEPEWeap1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "radio_doc01", required = false) String radio_doc,
			@RequestParam(value = "wepe_weapon_no1", required = false) String wepe_weapon_no,
			@RequestParam(value = "status1", required = false) String status_weap,
			@RequestParam(value = "id_check_wep_hidden1", required = false) String ch,
			@RequestParam(value = "id_wep_foot_hidden1", required = false) String ch_foot) {
		ch = ch.replace("&#39;", "'");

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		if (roleAccess.equals("Unit")) {
			unit_sus_no_wep = roleSusNo;
			unit_name = all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0);
		}

		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status_weap);
		Mmap.put("wepe_weapon_no1", wepe_weapon_no);
		Mmap.put("unit_sus_no1", unit_sus_no_wep);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("radio_doc01", radio_doc);

		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail2(wepe_weapon_no, unit_sus_no_wep,status_weap, roleType);

		List<Map<String, Object>> flist  ;
		if (!unit_sus_no_wep.equals(""))
		{
			flist = linkdao.getSearchlinkWEPEWepfootnoteReportDetail(unit_sus_no_wep); 
			Mmap.put("flist", flist);  
			Mmap.put("flist.size()", flist.size());
		}
		
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("id_check_hidden_txt", ch);
		Mmap.put("id_check_foot_hidden_txt", ch_foot);
		return new ModelAndView("link_WEPE_sus_WepTiles"); 
	}

	@RequestMapping(value = "/admin/searchLinkWEPEWeap1", method = RequestMethod.POST)
	public ModelAndView searchLinkWEPEWeap1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "wepe_wep_no1", required = false) String wepe_wep_no,
			@RequestParam(value = "radio_doc01", required = false) String we_pe,
			@RequestParam(value = "status1", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("status1", status);
		Mmap.put("wepe_wep_no1", wepe_wep_no);
		Mmap.put("unit_sus_no1", unit_sus_no_wep);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("radio_doc01", we_pe);

		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail1(status, wepe_wep_no, unit_sus_no_wep,roleType,roleAccess,roleArmCode,roleSusNo);

		List<Map<String, Object>> flist ;
		if (!unit_sus_no_wep.equals(""))
		{
			flist = linkdao.getSearchlinkWEPEWepfootnoteReportDetail(unit_sus_no_wep); 
			Mmap.put("flist", flist);  
			Mmap.put("flist.size()", flist.size());
		}
		
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/ApprovedWEPELinkWepUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedWEPELinkTransUrl(@ModelAttribute("appid") int appid, HttpSession session,
			@ModelAttribute("sus_no") String sus_no, @ModelAttribute("wepe_no") String wepe_no, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name2", required = false) String unit_name,
			@RequestParam(value = "wepe_wep_no2", required = false) String wepe_wep_no,
			@RequestParam(value = "radio_doc01", required = false) String we_pe,
			@RequestParam(value = "status2", required = false) String status, Authentication authentication) {
		String roleType = session.getAttribute("roleType").toString();
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Mmap.put("msg", linkdao.setApprovedWepeLinkWep(appid, sus_no, wepe_no,username,creadtedate));
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("status1", status);
		Mmap.put("wepe_wep_no1", wepe_wep_no);
		Mmap.put("unit_sus_no1", unit_sus_no_wep);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("radio_doc01", we_pe);
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail1(status, wepe_wep_no, unit_sus_no_wep,
				roleType,roleAccess,roleArmCode,roleSusNo);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/rejectWEPELinkWepUrl", method = RequestMethod.POST)
	public ModelAndView rejectWEPELinkTransUrl(@ModelAttribute("rejectid") int rejectid, ModelMap Mmap,
			@ModelAttribute("sus_no_r") String sus_no_r, @ModelAttribute("wepe_no_r") String wepe_no_r,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, @RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name6", required = false) String unit_name,
			@RequestParam(value = "wepe_wep_no6", required = false) String wepe_wep_no,
			@RequestParam(value = "radio_doc06", required = false) String we_pe,
			@RequestParam(value = "status6", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", linkdao.setRejectWepeLinkWep(rejectid, sus_no_r, wepe_no_r));
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("status1", status);
		Mmap.put("wepe_wep_no1", wepe_wep_no);
		Mmap.put("unit_sus_no1", unit_sus_no_wep);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("radio_doc01", we_pe);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail1(status, wepe_wep_no, unit_sus_no_wep,
				roleType,roleAccess,roleArmCode,roleSusNo);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/deleteWEPELinkWeapUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteWEPELinkWeapUrlAdd(int deleteid, String sus, String wepe) {
		List<String> list2 = new ArrayList<String>();
		list2.add(linkdao.setDeleteWepeLinkWeap(deleteid, sus, wepe));
		return list2;
	}

	@RequestMapping(value = "/delinkapprvWEPELinkWeapUrl", method = RequestMethod.POST)
	public ModelAndView delinkapprvWEPELinkWeapUrl(@ModelAttribute("delinkapprvid") int delinkapprvid,
			HttpSession session, @ModelAttribute("sus_no_da") String sus_no_da,
			@ModelAttribute("wepe_no_da") String wepe_no_da, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name3", required = false) String unit_name,
			@RequestParam(value = "wepe_wep_no3", required = false) String wepe_wep_no,
			@RequestParam(value = "status3", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		model.put("msg", linkdao.setDelinkapprvWepeLinkWeap(delinkapprvid, sus_no_da, wepe_no_da));
		model.put("status1", status);
		model.put("wepe_wep_no1", wepe_wep_no);
		model.put("unit_sus_no1", unit_sus_no_wep);
		model.put("unit_name1", unit_name);
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail1(status, wepe_wep_no, unit_sus_no_wep,
				roleType,roleAccess,roleArmCode,roleSusNo);

		model.put("list", list);
		model.put("roleType", roleType);
		model.put("list.size()", list.size());

		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/delinkrejectWEPELinkWeapUrl", method = RequestMethod.POST)
	public ModelAndView delinkrejectWEPELinkWeapUrl(@ModelAttribute("delinkrejid") int delinkrejectid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, @RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no_wep,
			@RequestParam(value = "unit_name5", required = false) String unit_name,
			@RequestParam(value = "wepe_wep_no5", required = false) String wepe_wep_no,
			@RequestParam(value = "radio_doc05", required = false) String we_pe,
			@RequestParam(value = "status5", required = false) String status) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", linkdao.setDelinkrejectWepeLinkWeap(delinkrejectid));
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("status1", status);
		Mmap.put("wepe_wep_no1", wepe_wep_no);
		Mmap.put("unit_sus_no1", unit_sus_no_wep);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("radio_doc01", we_pe);
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPEWepReportDetail1(status, wepe_wep_no, unit_sus_no_wep,
				roleType,roleAccess,roleArmCode,roleSusNo);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("Search_link_WEPE_WepTiles");
	}

	@RequestMapping(value = "/updateWEPELinkWepUrl", method = RequestMethod.POST)
	public ModelAndView updateWEPELinkWepUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@ModelAttribute("wepe_no_e") String wepe_no_e, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("editLinkWepCmd", linkdao.getCUE_TB_MISO_WEPE_PERS_MDFSidLink(updateid));
		model.put("table_view_wep", linkdao.getwepepersModiDetailsReport(wepe_no_e, "cue_tb_miso_wepe_weapons_mdfs"));
		model.put("table_view_wep_footnotes", linkdao.getwepeweaponModiDetailsFootnotesReport(wepe_no_e));
		return new ModelAndView("editLink_WEPE_susWepTile");
	}

	@RequestMapping(value = "/edit_modification_link_wep_act", method = RequestMethod.POST)
	public ModelAndView edit_modification_link_wep_act(
			@ModelAttribute("editLinkWepCmd") CUE_TB_WEPE_link_sus_weapon_mdfs e, HttpServletRequest request,
			ModelMap model, HttpSession session, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String ch = request.getParameter("id_check_wep_hidden");

		String ch_foot = request.getParameter("id_wep_foot_hidden");
		String id_unit_hidden_wep = request.getParameter("id_unit_hidden_wep");
		String id_we_pe_no_hidden_wep = request.getParameter("id_we_pe_no_hidden_wep");
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap = :status,c.modified_on_weap=:modified_on_weap,c.modified_by_weap=:modified_by_weap  where c.sus_no = :sus_no";
		int app = session1.createQuery(hqlUpdate).setString("status", "0").setString("sus_no", id_unit_hidden_wep)
				.setString("modified_by_weap", username).setString("modified_on_weap", modifydate).executeUpdate();
		tx1.commit();
		session1.close();
		Boolean f_k = isdetailFKlink_weapon(id_unit_hidden_wep);
		Boolean f_k_foot = isdetailFKlink_Weaponfoot(id_unit_hidden_wep);
		if (ch != null && !ch.toString().equals("")) {
			ch = ch.replace("&#39;", "'");
			if (f_k.equals(false)) {
				e.setCreated_by(username);
				e.setCreated_on(creadtedate);

				String ch1[] = ch.split(",");
				for (int i = 0; i < ch1.length; i++) {
					Session session11 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session11.beginTransaction();
					Query qry = session11.createSQLQuery(
							"insert into CUE_TB_WEPE_link_sus_weapon_mdfs (sus_no ,created_by ,created_on , we_pe_no,status,modification)"
									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", id_unit_hidden_wep);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", id_we_pe_no_hidden_wep);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", ch1[i].replace("'", ""));
					qry.executeUpdate();
					tx.commit();
					session11.close();
				}
			}
		}

		if (ch_foot != null && !ch_foot.toString().equals("")) {
			ch_foot = ch_foot.replace("&#39;", "");
			if (f_k_foot.equals(false)) {

				String ch_f1[] = ch_foot.split(",");
				for (int i = 0; i < ch_f1.length; i++) {
					Session session11 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session11.beginTransaction();
					Query qry = session11.createSQLQuery(
							"insert into CUE_TB_WEPE_link_sus_weapon_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,fk_weapon_foot)"
									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", id_unit_hidden_wep);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", id_we_pe_no_hidden_wep);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
					qry.executeUpdate();
					tx.commit();
					session11.close();
				}
			}
		}
		model.put("msg", "Modification and General Notes Data Updated Successfully");
		return new ModelAndView("redirect:link_WEPE_sus_Wep");
	}

	@RequestMapping(value = "/getLink_susno_wepmdf", method = RequestMethod.POST)
	public @ResponseBody List<String> getLink_susno_wepmdf(String unit_sus_no, String wepe_wep_no) {
		return linkdao.getLink_sus_wepmdf(unit_sus_no, wepe_wep_no);
	}

	@RequestMapping(value = "/getLink_susno_wepfoot", method = RequestMethod.POST)
	public @ResponseBody List<String> getLink_susno_wepfoot(String unit_sus_no, String wepe_wep_no) {
		return linkdao.getLink_sus_wepfoot(unit_sus_no, wepe_wep_no);
	}
	@RequestMapping(value = "/getFootNotesWeaponDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFootNotesWeaponDetails(String sus_no,String wepe_no,HttpSession session) {
		String roleType = session.getAttribute("roleType").toString();
		return linkdao.getFootNotesWeaponDetails(sus_no,wepe_no);
	}

@RequestMapping(value = "/check_locationWeap", method = RequestMethod.POST)
	public @ResponseBody List<String> check_locationWeap(HttpSession session,
			HttpServletRequest request, @RequestParam String wepe_pers_no,@RequestParam String unit_sus_no_wep ) {
//		String wepe_wep_no = request.getParameter("wepe_pers_no");
		
		return linkdao.check_locationWeap(wepe_pers_no,unit_sus_no_wep);
	}
	
	
	
	
}
