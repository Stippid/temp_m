package com.controller.login;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Role;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_ROLEMASTER;
import com.models.TB_LDAP_ROLE_TYPE;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.UserLogin;
import com.models.UserRole;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class RoleController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	Psg_CommonController p_comm = new Psg_CommonController();

	String access_lvl1 = "";
	String subaccess_lvl1 = "";
	String user_sus_no1 = "";
	String user_name1 = "";
	String role_name = "";
	String status1 = "";
	String login_name1 = "";

	// open Role master page
	@RequestMapping(value = "/role_mstUrl", method = RequestMethod.GET)
	public ModelAndView role_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("role_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.RoleSearchReport();
			Mmap.put("list", list);
			Mmap.put("getRoleType", getRoleType());
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("role_mstTiles");
		}
	}

	// save and update role master
	@RequestMapping(value = "/role_mstAction", method = RequestMethod.POST)
	public ModelAndView role_mstAction(@ModelAttribute("role_mstCMD") Role p, TB_LDAP_ROLE_TYPE p1,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("role_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			if (p.getRole().equals("")) {
				model.put("msg", "Please Enter Role Name");
				return new ModelAndView("redirect:role_mstUrl");
			} else if (validation.RoleLength(p.getRole()) == false) {
				model.put("msg", validation.RoleMSG);
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("role_type").equals("0")) {
				model.put("msg", "Please Select Role Type");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("access_lvl").equals("")) {
				model.put("msg", "Please Select Access Level");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("access_lvl").equals("")) {
				model.put("msg", "Please Select Access Level");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if ((!p.getAccess_lvl().equals("Unit") && p.getAccess_lvl().toString() != "Unit") && (!p.getAccess_lvl().equals("MISO") && p.getAccess_lvl().toString() != "MISO")
					&& !p.getAccess_lvl().equals("CTPartI") && !p.getAccess_lvl().equals("CTPartII") && !p.getAccess_lvl().equals("DG") && !p.getAccess_lvl().equals("HeadQuarter") && !p.getAccess_lvl().equals("CIN") && !p.getAccess_lvl().equals("DGMS")){
				if (request.getParameter("sub_access_lvl").equals("")) {
					model.put("msg", "Please Select Sub Access Level");
					return new ModelAndView("redirect:role_mstUrl");
				}
			}

		
			if (request.getParameter("sub_access_lvl").toString().equals("Staff")) {
				if (request.getParameter("staff_lvl").equals("")) {
					model.put("msg", "Please Select Sub Access Level");
					return new ModelAndView("redirect:role_mstUrl");
				}
			}
			if (!roledao.getroleExist(p.getRole()).equals(false)) {
				model.put("msg", "Data Already Exist");
				return new ModelAndView("redirect:role_mstUrl");
			}
			p.setRole_url("commonDashboard");
			sessionHQL.beginTransaction();
			sessionHQL.save(p);
			sessionHQL.getTransaction().commit();
			model.put("msg", "Data saved Successfully");
		} catch (Exception e) {
			model.put("msg", "Data Save not Successfully");
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:role_mstUrl");
	}

	// open Module Master page
	@RequestMapping(value = "/module_mstUrl", method = RequestMethod.GET)
	public ModelAndView module_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("module_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.ModuleSearchReport();
			Mmap.put("list", list);
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("module_mstTiles");
		}
	}

	// save and update Module Master
	@RequestMapping(value = "/module_mstAction", method = RequestMethod.POST)
	public ModelAndView module_mstAction(@ModelAttribute("module_mstCMD") TB_LDAP_MODULE_MASTER p,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("module_mstUrl", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}

		String module1 = request.getParameter("module_name");
		String module2 = request.getParameter("module_old_name");
		// Boolean val = true;
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		try {
			if (module1 == "" || module1.equals("")) {
				model.put("msg", "Please Enter Module Name");
				return new ModelAndView("redirect:module_mstUrl");
			} else if (validation.ModuleNameLength(module1) == false) {
				model.put("msg", validation.ModuleNameMSG);
				return new ModelAndView("redirect:module_mstUrl");
			}
			if (!roledao.getmoduleExist(module1).equals(false)) {
				model.put("msg", "Data Already Exist");
				return new ModelAndView("redirect:module_mstUrl");
			}
			if (module2 == "") {
				p.setModule_name(module1.trim());
				sessionHQL.beginTransaction();
				sessionHQL.save(p);
				sessionHQL.getTransaction().commit();
				model.put("msg", "Data saved Successfully");
			} else {
				Transaction tx = sessionHQL.beginTransaction();
				String hql = "UPDATE TB_LDAP_MODULE_MASTER SET module_name=:module_name  WHERE id=:module_name2 ";
				Query query = sessionHQL.createQuery(hql).setString("module_name", module1).setInteger("module_name2",
						Integer.parseInt(module2));
				int rowCount = query.executeUpdate();
				tx.commit();
				if (rowCount > 0) {
					model.put("msg", "Data Updated Successfully");
				} else {
					model.put("msg", "Data Not Updated Successfully");
				}
			}
		} catch (Exception e) {
			model.put("msg", "Data Save not Successfully");
		} finally {
			sessionHQL.close();
		}

		return new ModelAndView("redirect:module_mstUrl");
	}

	// open Screen Master page
	@RequestMapping(value = "/screen_mstUrl", method = RequestMethod.GET)
	public ModelAndView screen_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.ScreenSearchReport("", "");
			Mmap.put("list", list);
			Mmap.put("getModuleNameList", getModuleNameList());
			Mmap.put("getSubModuleNameList", getSubModuleNameList());
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("screen_mstTiles");
		}
	}

	// save and update Screen Master
	@RequestMapping(value = "/screen_mstAction", method = RequestMethod.POST)
	public ModelAndView screen_mstAction(@ModelAttribute("screen_mstCMD") TB_LDAP_SCREEN_MASTER p,HttpServletRequest request, ModelMap model, HttpSession session) {
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("screen_mstUrl", roleid1);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		int sc_id = 0;
		if (request.getParameter("screen_id") == "") {
			sc_id = 0;
		} else {
			sc_id = Integer.parseInt(request.getParameter("screen_id"));
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
		    if (p.getScreen_name() == "") { // || p.getScreen_name().equals("")
				model.put("msg", "Please Enter Screen Name");
				return new ModelAndView("redirect:screen_mstUrl");
			} else if (validation.ScreenNameLength(p.getScreen_name()) == false) {
				model.put("msg", validation.ScreenNameMSG);
				return new ModelAndView("redirect:screen_mstUrl");
			}
			if (p.getScreen_url() == "") { // || p.getScreen_url().equals("")
				model.put("msg", "Please Enter Screen Url");
				return new ModelAndView("redirect:screen_mstUrl");
			} else if (validation.ScreenURLLength(p.getScreen_url()) == false) {
				model.put("msg", validation.ScreenURLMSG);
				return new ModelAndView("redirect:screen_mstUrl");
			}
			if (p.getModule().getId() == 0) { // || p.getScreen_module_id().equals("") getScreen_module_id()
				model.put("msg", "Please Select Module ");
				return new ModelAndView("redirect:screen_mstUrl");
			}
			if (p.getSub_module().getId() == 0) { // || p.getScreen_submodule_id().equals("") getScreen_submodule_id()
				model.put("msg", "Please Select Sub Module ");
				return new ModelAndView("redirect:screen_mstUrl");
			}
			if (!roledao.getscreenExist(p.getScreen_name(), p.getScreen_url(), p.getModule().getId(),
					p.getSub_module().getId(), request.getParameter("screen_id")).equals(false)) {
				model.put("msg", "Data Already Exist");
				return new ModelAndView("redirect:screen_mstUrl");
			}
			if (sc_id == 0) {
				p.setScreen_name(p.getScreen_name().trim());
				sessionHQL.beginTransaction();
				sessionHQL.save(p);
				sessionHQL.getTransaction().commit();
				model.put("msg", "Data Save Successfully");
			} else {
				Transaction tx = sessionHQL.beginTransaction();
				String hql = "UPDATE TB_LDAP_SCREEN_MASTER SET screen_name=:screen_name,screen_module_id=:screen_module_id,screen_submodule_id=:screen_submodule_id WHERE id=:id ";
				Query query = sessionHQL.createQuery(hql).setString("screen_name", p.getScreen_name())
						.setInteger("screen_module_id", p.getModule().getId())
						.setInteger("screen_submodule_id", p.getSub_module().getId())
						.setInteger("id", sc_id);
				int rowCount = query.executeUpdate();
				
				if (rowCount > 0) {
					//// update when change screen Module and sub module 
					String hql1 = "UPDATE TB_LDAP_ROLEMASTER SET moduleid=:moduleid,submoduleid=:submoduleid WHERE screenid=:screenid ";
					Query query1 = sessionHQL.createQuery(hql1)
							.setInteger("moduleid", p.getModule().getId())
							.setInteger("submoduleid", p.getSub_module().getId())
							.setInteger("screenid", sc_id);
					int rowCount1 = query1.executeUpdate();
					//// update when change screen Module and sub module 
					if (rowCount1 > 0) {
						model.put("msg", "Data Updated Successfully");
					}else {
						model.put("msg", "Data Not Updated Successfully");
					}
					
				} else {
					model.put("msg", "Data Not Updated Successfully");
				}
				tx.commit();
			}
		} catch (Exception e) {
			model.put("msg", "Data Save not Successfully");
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:screen_mstUrl");
	}

	// report for Screen Master
	@RequestMapping(value = "/screen_report")
	public ModelAndView screen_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "module_id1", required = false) String module_id,
			@RequestParam(value = "sub_module_id1", required = false) String sub_module_id,
			HttpServletRequest request) {

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("screen_mstUrl", roleid1);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Mmap.put("module_id1", module_id);
		Mmap.put("sub_module_id1", sub_module_id);
		Mmap.put("getModuleNameList", getModuleNameList());
		Mmap.put("getSubModuleNameList", getSubModuleNameList());
		List<Map<String, Object>> list = roledao.ScreenSearchReport(module_id, sub_module_id);
		if (list.size() == 0) {
			Mmap.put("msg", "Data Not Available");
		} else {
			Mmap.put("list", list);
		}
		Mmap.put("list.size()", list.size());
		return new ModelAndView("screen_mstTiles");
	}

	// open User Master page
	@RequestMapping(value = "/user_mstUrl", method = RequestMethod.GET)
	public ModelAndView user_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			if (request.getHeader("Referer") == null){
				msg = "";
			}
			Mmap.put("msg", msg);
			Mmap.put("getRoleNameList", getRoleNameList());
			/*Mmap.put("getUserarm_codeList", getUserarm_codeList());*/
		}
		return new ModelAndView("user_mstTiles");
	}

	// save and update User Master page
	@RequestMapping(value = "/user_mstAction", method = RequestMethod.POST)
	public ModelAndView user_mstAction(@ModelAttribute("user_mstCMD") UserLogin p, HttpServletRequest request,
			ModelMap model, HttpSession session) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		String roleid = session.getAttribute("roleid").toString();
		String username = session.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			String pass = request.getParameter("user_password");
			String name = request.getParameter("user_name");
			String re_pass = request.getParameter("user_re_password");
			int roll = Integer.parseInt(request.getParameter("user_role_id"));
			String army_no = request.getParameter("army_no");
			String role = request.getParameter("user_role_id");

			boolean pass_valid = validate(pass);
			if (pass_valid == false) {
				model.put("msg", "Password pattern doesn't match.");
				return new ModelAndView("redirect:user_mstUrl");
			}

			if (p.getLogin_name() == "") {
				model.put("msg", "Please Enter User Name.");
				return new ModelAndView("redirect:user_mstUrl");
			} else if (validation.LoginNameLength(p.getLogin_name()) == false) {
				model.put("msg", validation.LoginNameMSG);
				return new ModelAndView("redirect:user_mstUrl");
			}
			if (name == "" || name.equals("")) {
				model.put("msg", "Please Enter User ID.");
				return new ModelAndView("redirect:user_mstUrl");
			} else if (validation.UserNameLength(name) == false) {
				model.put("msg", validation.UserNameMSG);
				return new ModelAndView("redirect:user_mstUrl");
			}
			if (army_no == "" || army_no.equals("")) {
				model.put("msg", "Please Enter Army No.");
				return new ModelAndView("redirect:user_mstUrl");
			} else if (validation.ArmyNoLength(army_no) == false) {
				model.put("msg", validation.ArmyNoMSG);
				return new ModelAndView("redirect:user_mstUrl");
			}
			if (pass == "" || pass.equals("")) {
				model.put("msg", "Please Enter User ID.");
				return new ModelAndView("redirect:user_mstUrl");
			} else if (validation.PasswordLength(pass) == false) {
				model.put("msg", validation.PasswordMSG);
				return new ModelAndView("redirect:user_mstUrl");
			}
			else if(!PasswordValidator.validate(pass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
				model.put("msg",validation.PasswordPatternMSG);
				return new ModelAndView("redirect:changePassword");
			}
			
			if (!pass.trim().equals(re_pass.trim())) {
				model.put("msg", "Password and Re-Password didn't match");
				return new ModelAndView("redirect:user_mstUrl");
			}
			if (role == "" || role.equals("")) {
				model.put("msg", "Please Select Role");
				return new ModelAndView("redirect:user_mstUrl");
			}
			if (!roledao.getuserExist(name).equals(false)) {
				model.put("msg", "Data Already Exist");
				return new ModelAndView("redirect:user_mstUrl");
			} else {
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(pass);
				p.setPassword(hashedPassword);
				p.setUserName(name);
				p.setCreated_by(username);
				p.setUser_Arm_code(request.getParameter("user_arm_code"));
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
				
				
				p.setAc_dc_date(modifydate);
				p.setArmy_no(army_no);

				if (request.getParameter("access_lve1").equals("Formation")) {
					p.setUser_formation_no(getsusnobyfromation(request.getParameter("user_sus_no")).toString().replace("[", "").replace("]", ""));
				} else {
					p.setUser_formation_no("null");
				}
				UserRole role_tbl = new UserRole();
				sessionHQL.beginTransaction();

				int did = (Integer) sessionHQL.save(p);
				role_tbl.setRoleId(roll);
				role_tbl.setUserId(did);
				sessionHQL.save(role_tbl);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();

				roledao.userinsertdata("insert", did, roll);
				model.put("msg", "Data saved Successfully");
			}
		} catch (Exception e) {
			model.put("msg", "Data Save not Successfully");
		}
		return new ModelAndView("redirect:user_mstUrl");
	}

	// open Sub Module Master page
	@RequestMapping(value = "/sub_module_mstUrl", method = RequestMethod.GET)
	public ModelAndView sub_module_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("sub_module_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			Mmap.put("getModuleNameList", getModuleNameList());
			List<Map<String, Object>> list = roledao.SubModuleSearchReport();
			Mmap.put("list", list);
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("sub_module_mstTiles");
		}
	}

	// save and update Sub Module Master
	@RequestMapping(value = "/sub_module_mstAction", method = RequestMethod.POST)
	public ModelAndView sub_module_mstAction(@ModelAttribute("sub_module_mstCMD") TB_LDAP_SUBMODULE_MASTER p,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("sub_module_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			if (p.getSubmodule_name() == "") {
				model.put("msg", "Please Enter Sub Module Name");
				return new ModelAndView("redirect:sub_module_mstUrl");
			} else if (validation.SubModuleNameLength(p.getSubmodule_name()) == false) {
				model.put("msg", validation.SubModuleNameMSG);
				return new ModelAndView("redirect:sub_module_mstUrl");
			}

			if (p.getModule().getId() == 0) {
				model.put("msg", "Please Enter Module Name");
				return new ModelAndView("redirect:sub_module_mstUrl");
			}
			int sm_id = 0;

			if (request.getParameter("submodule_id") == "") {
				sm_id = 0;
			} else {
				sm_id = Integer.parseInt(request.getParameter("submodule_id"));
			}

			if (sm_id == 0) {

				if (!roledao.getsubmoduleExist(p.getSubmodule_name(), p.getModule().getId(), sm_id).equals(false)) {
					model.put("msg", "Data Already Exist");
					return new ModelAndView("redirect:sub_module_mstUrl");
				}

				// String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				sessionHQL.beginTransaction();
				p.setSubmodule_name(p.getSubmodule_name().trim());
				sessionHQL.save(p);
				sessionHQL.getTransaction().commit();
				model.put("msg", "Data saved Successfully");
			} else {

				Transaction tx = sessionHQL.beginTransaction();
				String hql = "UPDATE TB_LDAP_SUBMODULE_MASTER SET submodule_name=:submodule_name_n , module_id=:module_id_n  WHERE id =:id ";
				Query query = sessionHQL.createQuery(hql).setString("submodule_name_n", p.getSubmodule_name())
						.setInteger("module_id_n", p.getModule().getId()).setInteger("id", sm_id);
				int rowCount = query.executeUpdate();
				
				if (rowCount > 0) {
					//when change module 
						String hql1 = "UPDATE TB_LDAP_ROLEMASTER SET moduleid=:moduleid  WHERE submoduleid =:submoduleid ";
						Query query1 = sessionHQL.createQuery(hql1)
								.setInteger("moduleid", p.getModule().getId()).setInteger("submoduleid", sm_id);
						int rowCount1 = query1.executeUpdate();
					//when change module 
					if (rowCount1 > 0) {
						model.put("msg", "Updated Successfully");
					}else {
						model.put("msg", "Updated not Successfully");
					}
				} else {
					model.put("msg", "Updated not Successfully");
				}
				tx.commit();
			}

		} catch (Exception e) {
			model.put("msg", "Data Not Saved Successfully");
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:sub_module_mstUrl");
	}

	// open Link Role Master page
	@RequestMapping(value = "/rolemstnewUrl", method = RequestMethod.GET)
	public ModelAndView rolemstnewUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("rolemstnewUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList", getRoleNameList());
		return new ModelAndView("roleTiles");
	}

	// save and update Link Role Master
	@RequestMapping(value = "/roleAction", method = RequestMethod.POST)
	public ModelAndView roleAction(@ModelAttribute("roleCMD") TB_LDAP_ROLEMASTER p, HttpServletRequest request,
			ModelMap model, HttpSession session) throws ParseException {
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("rolemstnewUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			String username = session.getAttribute("username").toString();
			DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Date creadtedate1 = null;
			creadtedate1 = formatter.parse(creadtedate);
			String roleid = request.getParameter("roleid");
			String module = request.getParameter("modulehid");
			String submodule = request.getParameter("submodulehid");
			String screen = request.getParameter("screenhid");
			String screen_old = request.getParameter("screenhid_old");
			String module1 = null;
			String submodule1 = null;
			String screen1 = null;

			// String[] module_s = module.split(",");
			// String[] submodule_s = submodule.split(",");
			String[] screen_s = screen.split(",");
			String[] screen_s_old = screen_old.split(",");

			TB_LDAP_MODULE_MASTER md = new TB_LDAP_MODULE_MASTER();
			TB_LDAP_SUBMODULE_MASTER smd = new TB_LDAP_SUBMODULE_MASTER();
			TB_LDAP_SCREEN_MASTER sc = new TB_LDAP_SCREEN_MASTER();

			if (roleid == "0" || roleid.equals("0")) {
				model.put("msg", "Please Select Role.");
				return new ModelAndView("redirect:rolemstnewUrl");
			}
			if (module == "" || module.equals("")) {
				model.put("msg", "Please Select Module.");
				return new ModelAndView("redirect:rolemstnewUrl");
			}
			if (submodule == "" || submodule.equals("")) {
				model.put("msg", "Please Select Sub Module.");
				return new ModelAndView("redirect:rolemstnewUrl");
			}
			if (screen == "" || screen.equals("")) { // && screen_old.equals("")
				model.put("msg", "Please Select Screen.");
				return new ModelAndView("redirect:rolemstnewUrl");
			}
			if (screen_old.equals("")) {
				for (int i = 0; i < screen_s.length; i++) {
					String[] screen_s1 = screen_s[i].split("_");
					for (int j = 0; j < screen_s1.length; j++) {

						if (j == 0) {
							module1 = screen_s1[j];
						} else if (j == 1) {
							submodule1 = screen_s1[j];
						} else
							screen1 = screen_s1[j];
					}

					Boolean v = roledao.getlinkroleExist(Integer.parseInt(module1), Integer.parseInt(submodule1),
							Integer.parseInt(screen1), Integer.parseInt(roleid));
					if (v.equals(false)) {

						/*
						 * p.setModuleid(Integer.parseInt(module1));
						 * p.setSubmoduleid(Integer.parseInt(submodule1));
						 * p.setScreenid(Integer.parseInt(screen1));
						 */

						// TB_LDAP_MODULE_MASTER md = new TB_LDAP_MODULE_MASTER();
						md.setId(Integer.parseInt(module1.trim()));
						p.setModule(md);

						// TB_LDAP_SUBMODULE_MASTER smd = new TB_LDAP_SUBMODULE_MASTER();
						smd.setId(Integer.parseInt(submodule1.trim()));
						p.setSub_module(smd);

						// TB_LDAP_SCREEN_MASTER sc = new TB_LDAP_SCREEN_MASTER();
						sc.setId(Integer.parseInt(screen1.trim()));
						p.setScreen(sc);

						p.setRoleid(Integer.parseInt(roleid.trim()));
						p.setCreation_by(username);
						p.setCreation_date(creadtedate1);

						Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						sessionHQL.beginTransaction();
						sessionHQL.save(p);
						sessionHQL.getTransaction().commit();
						sessionHQL.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			} else {
				ArrayList<String> al1 = new ArrayList<String>();
				ArrayList<String> al2 = new ArrayList<String>();

				for (int i = 0; i < screen_s.length; i++) {
					al1.add(screen_s[i]);
				}
				for (int i = 0; i < screen_s_old.length; i++) {
					al2.add(screen_s_old[i]);
				}

				if (al1.equals(al2)) {
					model.put("msg", "Data Already Exist");
				} else {
					for (String newval : screen_s) {
						if (al2.contains(newval)) {
							al2.remove(newval);
							al1.remove(newval);
						}
					}

					String add_list = al1.toString();
					String del_list = al2.toString();

					add_list = add_list.replace("[", "").replace("]", "").trim();
					del_list = del_list.replace("[", "").replace("]", "").trim();

					if (!add_list.equals("")) {

						String[] s3 = add_list.split(",");

						for (int i = 0; i < s3.length; i++) {

							String[] screen_s1 = s3[i].split("_");

							for (int j = 0; j < screen_s1.length; j++) {

								if (j == 0) {
									module1 = screen_s1[j];
								} else if (j == 1) {
									submodule1 = screen_s1[j];
								} else
									screen1 = screen_s1[j];

							}
							Boolean v = roledao.getlinkroleExist(Integer.parseInt(module1.trim()),
									Integer.parseInt(submodule1.trim()), Integer.parseInt(screen1.trim()),
									Integer.parseInt(roleid.trim()));
							if (v.equals(false)) {
								md.setId(Integer.parseInt(module1.trim()));
								p.setModule(md);

								smd.setId(Integer.parseInt(submodule1.trim()));
								p.setSub_module(smd);

								sc.setId(Integer.parseInt(screen1.trim()));
								p.setScreen(sc);

								p.setRoleid(Integer.parseInt(roleid.trim()));
								p.setCreation_by(username);
								p.setCreation_date(creadtedate1);

								Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
								sessionHQL.beginTransaction();
								sessionHQL.save(p);
								sessionHQL.getTransaction().commit();
								sessionHQL.close();

							}
						}
					}

					if (!del_list.equals("")) {

						String[] s3 = del_list.split(",");

						for (int i = 0; i < s3.length; i++) {

							String[] screen_s1 = s3[i].split("_");

							for (int j = 0; j < screen_s1.length; j++) {

								if (j == 0) {
									module1 = screen_s1[j];
								} else if (j == 1) {
									submodule1 = screen_s1[j];
								} else
									screen1 = screen_s1[j];

							}
							Integer.parseInt(roleid);

							Session session1 = HibernateUtilNA.getSessionFactory().openSession();
							Transaction tx = session1.beginTransaction();
							String hql = "DELETE FROM TB_LDAP_ROLEMASTER where moduleid=:moduleid  and submoduleid=:submoduleid and  screenid=:screenid and roleid=:roleid";
							Query query = session1.createQuery(hql)
									.setInteger("moduleid", Integer.parseInt(module1.trim()))
									.setInteger("submoduleid", Integer.parseInt(submodule1.trim()))
									.setInteger("screenid", Integer.parseInt(screen1.trim()))
									.setInteger("roleid", Integer.parseInt(roleid.trim()));
							query.executeUpdate();
							tx.commit();
							session1.close();
						}
					}
					model.put("msg", "Updated Successfully");
				}

			}
		} catch (Exception e) {
			model.put("msg", e);
		}
		return new ModelAndView("redirect:rolemstnewUrl");
	}

	// Role list
	public List<Role> getRoleNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Role order by role");
			@SuppressWarnings("unchecked")
			List<Role> list = (List<Role>) q.list();
			tx.commit();
			session.close();
			return list;
		}catch (Exception e) {
			session.close();
			return null;
		}
	}

	// Sub Module list
	public List<TB_LDAP_SUBMODULE_MASTER> getSubModuleNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_SUBMODULE_MASTER order by id");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SUBMODULE_MASTER> list = (List<TB_LDAP_SUBMODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Module list
	public List<TB_LDAP_MODULE_MASTER> getModuleNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_MODULE_MASTER  where id > 0 order by id  ");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Role Type list
	public @ResponseBody List<TB_LDAP_ROLE_TYPE> getRoleType() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_ROLE_TYPE  ");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_ROLE_TYPE> list = (List<TB_LDAP_ROLE_TYPE>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// list for Arm
	public @ResponseBody List<Tb_Miso_Orabt_Arm_Code> getUserarm_codeList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(" from Tb_Miso_Orabt_Arm_Code");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orabt_Arm_Code> list = (List<Tb_Miso_Orabt_Arm_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}catch (Exception e) {
			session.close();
			return null;
		}
	}

	// For role by module in rolemaster table
	@RequestMapping(value = "/getrolebymodule", method = RequestMethod.POST)
	public @ResponseBody List<String> getrolebymodule(int roleid, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String nrQry = "select distinct module.id from TB_LDAP_ROLEMASTER where roleid=:roleid and module.id != 0  order by module.id";
		Query q = session.createQuery(nrQry);
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// For role and module by sub_module in rolemaster table
	@RequestMapping(value = "/getrolemodulebysubmod", method = RequestMethod.POST)
	public @ResponseBody List<String> getrolemodulebysubmod(int roleid, int moduleid, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String nrQry = "select distinct sub_module.id from TB_LDAP_ROLEMASTER where roleid=:roleid and module.id != 0 and sub_module.id != 0 and module.id=:moduleid  order by sub_module.id";
		Query q = session.createQuery(nrQry);
		q.setParameter("roleid", roleid);
		q.setParameter("moduleid", moduleid);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// For role and module by sub_module in rolemaster table
	@RequestMapping(value = "/getrolemodulesubmodbyscreen", method = RequestMethod.POST)
	public @ResponseBody List<String> getrolemodulesubmodbyscreen(int moduleid, int submoduleid, int role_id,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String nrQry = "";
		nrQry = "select distinct screen.id from TB_LDAP_ROLEMASTER where roleid=:role_id and module.id != 0 and sub_module.id != 0 and module.id=:moduleid and sub_module.id =:submoduleid order by screenid ";
		Query q = session.createQuery(nrQry);
		q.setParameter("role_id", role_id);
		q.setParameter("moduleid", moduleid);
		q.setParameter("submoduleid", submoduleid);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	// report Submodule list
	/*
	 * @RequestMapping(value = "/getReportSubmoduleList") public @ResponseBody
	 * List<Map<String, Object>> getReportSubmoduleList() { return
	 * roledao.getReportSubmoduleList(); }
	 */

	// module list for link role master
	@RequestMapping(value = "/getModuleNameListlink", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getModuleNameListlink(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_MODULE_MASTER where id > 0");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();

		List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> l2 = new LinkedHashMap<String, Object>();
			l2.put("id", String.valueOf(list.get(i).getId()));
			l2.put("module_name", list.get(i).getModule_name());
			l1.add(l2);
		}
		session.close();
		return l1;

	}

	// sub module list for link role master
	@RequestMapping(value = "/getSubModuleNameListlink", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSubModuleNameListlink(@RequestParam String valu1,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_SUBMODULE_MASTER where module.id=:module_id order by id");
		q.setParameter("module_id", Integer.parseInt(valu1));
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SUBMODULE_MASTER> list = (List<TB_LDAP_SUBMODULE_MASTER>) q.list();
		tx.commit();
		List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> l2 = new LinkedHashMap<String, Object>();
			l2.put("id", String.valueOf(list.get(i).getId()));
			l2.put("module_id", String.valueOf(list.get(i).getModule().getId()));
			l2.put("submodule_name", list.get(i).getSubmodule_name());
			l1.add(l2);
		}
		session.close();
		return l1;
	}

	/// screen list based sub module id and module id
	@RequestMapping(value = "/getScreenName_mod_submodList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getScreenName_mod_submodList(HttpServletRequest request) {
		Query q;
		// String qry="";
		int moduleid = Integer.parseInt(request.getParameter("moduleid"));
		int submoduleid = Integer.parseInt(request.getParameter("submoduleid"));

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		q = session.createQuery(
				"from TB_LDAP_SCREEN_MASTER where module.id=:moduleid and sub_module.id=:submoduleid  order by id");
		q.setParameter("moduleid", moduleid);
		q.setParameter("submoduleid", submoduleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SCREEN_MASTER> list = (List<TB_LDAP_SCREEN_MASTER>) q.list();
		tx.commit();
		List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> l2 = new LinkedHashMap<String, Object>();
			l2.put("id", String.valueOf(list.get(i).getId()));
			l2.put("screen_module_id", String.valueOf(list.get(i).getModule().getId()));
			l2.put("screen_submodule_id", list.get(i).getSub_module().getId());
			l2.put("screen_name", list.get(i).getScreen_name());
			l1.add(l2);
		}
		session.close();
		return l1;
	}

	// autocomplete SUS No from access level and subaccess level
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getsearchsusnoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getsearchsusnoList(String access_lvl, String subaccess_lvl,
			HttpSession sessionUserId, String sus_no) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String access_lve = hex_asciiDao.hexToAscii(access_lvl);
		String Sub_access_lve = hex_asciiDao.hexToAscii(subaccess_lvl);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (access_lve.equals("Unit")) {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no in(select sus_no  from Tbl_CodesForm where level_in_hierarchy='Unit') and upper(sus_no) like :sus_no and status_sus_no='Active'").setMaxResults(10);
		} else if (access_lve.equals("Formation")) {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no in(select sus_no from Tbl_CodesForm where level_in_hierarchy=:Sub_access_lve ) and upper(sus_no) like :sus_no and status_sus_no='Active'").setMaxResults(10);
			q.setParameter("Sub_access_lve", Sub_access_lve);
		} else if (access_lve.equals("Depot")) {
			if (Sub_access_lve.equals("TMS")) {
				q = session.createQuery("select distinct sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Depot' and upper(sus_no) like :sus_no ").setMaxResults(10);
			} else if (Sub_access_lve.equals("MMS")) {
				q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no in (select distinct sus_no from Tb_Miso_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') and upper(sus_no) like :sus_no and status_sus_no='Active'").setMaxResults(10);
			}
		} else {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and upper(sus_no) like :sus_no ").setMaxResults(10);
		}

		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getArmyNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getArmyNoList(HttpSession sessionUserId, String army_no) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("select distinct army_no from UserLogin where upper(army_no) like :army_no and enabled='1'").setMaxResults(10);
		q.setParameter("army_no","%"+army_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
	public @ResponseBody List<String> getRoleList(HttpSession sessionUserId, String role) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		q = session.createQuery("select distinct role from Roleinformation where upper(role) like :role").setMaxResults(10);
		q.setParameter("role","%"+role.toUpperCase()+"%");
		
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	
	

	// autocomplete SUS No from role_id //based access level and subaccess level
	@RequestMapping(value = "/getusersusnoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getusersusnoList(String role_id, HttpSession sessionUserId, String sus_no) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		int rol_id1 = Integer.parseInt(role_id);
		Role roleList = roledao.getRoleNameListbyid(rol_id1);
		String access_lve = roleList.getAccess_lvl();
		String Sub_access_lve = roleList.getSub_access_lvl();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (access_lve.equals("Unit")) {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no like :sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no + "%");
		} else if (access_lve.equals("Depot")) {
			if (Sub_access_lve.equals("TMS")) {
				q = session.createQuery("select distinct sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Depot' and sus_no like :sus_no ").setMaxResults(10);
				q.setParameter("sus_no", sus_no + "%");
			}
			if (Sub_access_lve.equals("MMS")) {
				q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in (select distinct sus_no from Tb_Miso_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') and sus_no like :sus_no").setMaxResults(10);
				q.setParameter("sus_no", sus_no + "%");
			}
		} else if (access_lve.equals("Formation")) {
			q = session.createQuery(
					"select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no in(select sus_no from Tbl_CodesForm where level_in_hierarchy=:Sub_access_lve) and status_sus_no='Active' and sus_no like :sus_no")
					.setMaxResults(10);
			q.setParameter("Sub_access_lve", Sub_access_lve);
			q.setParameter("sus_no", sus_no + "%");
		} else {
			q = session.createQuery(
					"select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no like :sus_no ");
			q.setParameter("sus_no", sus_no + "%");
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getuserunit_nameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getuserunit_nameList(String role_id, HttpSession sessionUserId, String unit_name) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		int rol_id1 = Integer.parseInt(role_id);
		Role roleList = roledao.getRoleNameListbyid(rol_id1);
		String access_lve = roleList.getAccess_lvl();
		String Sub_access_lve = roleList.getSub_access_lvl();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (access_lve.equals("Unit")) {
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and lower(unit_name) like :unit_name").setMaxResults(10);
			q.setParameter("unit_name", "%"+unit_name.toLowerCase()+"%");
		} else if (access_lve.equals("Depot")) {
			if (Sub_access_lve.equals("TMS")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl \r\n" + 
						"where sus_no in (select distinct sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Depot') \r\n" + 
						"and lower(unit_name) like :unit_name and status_sus_no ='Active'").setMaxResults(10);
				q.setParameter("unit_name", "%"+unit_name.toLowerCase()+"%");
			}
			if (Sub_access_lve.equals("MMS")) {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in (select distinct sus_no from Tb_Miso_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') and lower(unit_name) like :unit_name").setMaxResults(10);
				q.setParameter("sus_no", "%"+unit_name.toLowerCase()+"%");
			}
		} else if (access_lve.equals("Formation")) {
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no in(select sus_no from Tbl_CodesForm where level_in_hierarchy=:Sub_access_lve) and status_sus_no='Active' and lower(unit_name) like :unit_name").setMaxResults(10);
			q.setParameter("Sub_access_lve", Sub_access_lve);
			q.setParameter("unit_name","%"+unit_name.toLowerCase()+"%");
		}else if (access_lve.equalsIgnoreCase("Line_dte") && Sub_access_lve.equalsIgnoreCase("Arm")) {
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and lower(unit_name) like :unit_name and sus_no in (select distinct line_dte_sus from Tb_Miso_Orbat_Line_Dte) ");
			q.setParameter("unit_name", "%"+unit_name.toLowerCase()+"%");
		}else {
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and lower(unit_name) like :unit_name ");
			q.setParameter("unit_name", "%"+unit_name.toLowerCase() + "%");
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getsusnobyfromation(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct form_code_control from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no=:sus_no ");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/search_user_by_role", method = RequestMethod.POST)
	public ModelAndView search_user_by_role(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "access_lvl1", required = false) String access_lvl,
			@RequestParam(value = "subaccess_lvl1", required = false) String subaccess_lvl,
			@RequestParam(value = "user_sus_no1", required = false) String user_sus_no,
			@RequestParam(value = "user_name", required = false) String user_name,
			@RequestParam(value = "role_name", required = false) String role_name) {
		
		
		access_lvl1 = access_lvl;
		subaccess_lvl1 = subaccess_lvl;
		user_sus_no1 = user_sus_no;
		user_name1 = user_name;
	
		role_name = role_name;
		
		return new ModelAndView("redirect:search_user_mstUrl");
	}

	// autocomplete for User Name
	@RequestMapping(value = "/getUsernameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getUsernameList(String userName, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct userName from UserLogin where enabled='1' and lower(userName) like :userName order by userName ").setMaxResults(10);
		q.setParameter("userName", "%"+userName.toLowerCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		// Enc Key Append Last value of List
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	// open User Master edit page
	@RequestMapping(value = "/update_user_mstUrl")
	public ModelAndView update_user_mstUrl(ModelMap Mmap, @ModelAttribute("updateid") int updateid,String username1,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession session) {
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("AccessTiles");
		}
		
		List<Map<String, Object>> list = roledao.getRole(updateid);
		Mmap.put("getRole", list);
		Mmap.put("getRoleNameList", getRoleNameList());
		Mmap.put("getUserarm_codeList", getUserarm_codeList());
		Mmap.put("edit_User_MstCMD", roledao.getUserLoginbyid(updateid));
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("username1", username1);
		
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_user_mstTiles");
	}

	// update User Master
	@RequestMapping(value = "/edit_User_Mst_Action")
	@ResponseBody
	public ModelAndView edit_User_Mst_Action(@ModelAttribute("edit_User_MstCMD") UserLogin updateid,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		int roll = Integer.parseInt(request.getParameter("user_role_id"));
		String user_arm_code = request.getParameter("user_arm_code");
		String access_lve1 = request.getParameter("access_lve1");
		String user_sus_no = request.getParameter("user_sus_no");
		String sub_access_lve1 = request.getParameter("sub_access_lve1");
		String name = request.getParameter("name");
		String rank = request.getParameter("rank");
		String formation_code = "";
		String username = session.getAttribute("username").toString();
		
		if (request.getParameter("access_lve1").equals("Formation")) {
			formation_code = String.valueOf(getsusnobyfromation(request.getParameter("user_sus_no")).toString()
					.replace("[", "").replace("]", ""));
		} else {
			formation_code = "null";
		}

		String roll1 = request.getParameter("user_role_id");
		try {
			if (updateid.getLogin_name() == "") {
				model.put("msg", "Please Enter User Name.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}

			boolean pass_valid = validate(request.getParameter("password"));
			if (pass_valid == false) {
				model.put("msg", "Password pattern doesn't match.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}

			if (request.getParameter("password") == "" || request.getParameter("password").equals("")) {
				model.put("msg", "Please Enter Password.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			if (!request.getParameter("password").trim().equals(request.getParameter("re_password").trim())) {
				model.put("msg", "Password and Re-Password didn't match");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			if (roll1 == "" || roll1.equals("")) {
				model.put("msg", "Please Select Role");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(updateid.getPassword());

			updateid.setPassword(hashedPassword);
			model.put("updateid", updateid.getUserId());
			model.put("msg", roledao.UpdateUserMst(updateid, roll, user_arm_code, user_sus_no, formation_code,access_lve1, sub_access_lve1,name,rank,username));

		} catch (Exception e) {

		}
		return new ModelAndView("redirect:search_user_mstUrl");
	}

	// open User Active/Deactive Status page
	@RequestMapping(value = "/user_statusUrl", method = RequestMethod.GET)
	public ModelAndView user_statusUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_statusUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			Mmap.put("getRoleType", getRoleType());
			Mmap.put("status1", status1);
			Mmap.put("login_name1", login_name1);
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("user_statusTiles");
		}
	}

	@RequestMapping(value = "/search_user_statusReport")
	public ModelAndView search_user_statusReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "login_name1", required = false) String login_name, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_statusUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			return new ModelAndView("AccessTiles");
		}
		status1 = status;
		login_name1 = login_name;
		return new ModelAndView("redirect:user_statusUrl");
	}

	@RequestMapping(value = "/getUserReportactiveList1")
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportactiveList1(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		String qry = "";
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		qry += "";
		if (status1 != "") {
			qry += "d.enabled = '" + status1 + "'";
		}
		if (login_name1 != "") {
			qry += "and d.userName = '" + login_name1 + "'";
		}
		DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasActiveUserreport(criterias, qry,
				roleSubAccess, status1);
		return DatatablesResponse.build(dataSet, criterias);
	}

	// for Active
	@RequestMapping(value = "/ActiveDataURl",method =RequestMethod.POST)
	public ModelAndView ActiveDataURl(ModelMap Mmap, @RequestParam(value = "acid1", required = false) String Activeid,
			@RequestParam(value = "status1", required = false) String status, HttpServletRequest request) {
		Mmap.put("status1", status);
		Mmap.put("login_name1", login_name1);
		Mmap.put("msg", roledao.getActiveData(Activeid));
		return new ModelAndView("redirect:search_user_statusReport");
	}

	// for Deactive
	@RequestMapping(value = "/DeactiveDataURl",method=RequestMethod.POST)
	public ModelAndView DeactiveDataURl(ModelMap Mmap,
			@RequestParam(value = "dcid1", required = false) String Deactiveid,
			@RequestParam(value = "status1", required = false) String status) {
		Mmap.put("status1", status);
		Mmap.put("login_name1", login_name1);
		Mmap.put("msg", roledao.getDeactiveData(Deactiveid));
		return new ModelAndView("redirect:search_user_statusReport");
	}

	// Open for status of inactive user Page
	@RequestMapping(value = "/status_of_inactive_user_reportUrl", method = RequestMethod.GET)
	public ModelAndView status_of_inactive_user_reportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
			throws ParseException {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("status_of_inactive_user_reportUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.getReportStatusOfInactiveUserList();
			Mmap.put("list", list);
			return new ModelAndView("status_of_inactive_user_reportTiles");
		}
	}

	//////////////// PASSWORD_PATTERN //////////
	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*])(?!.*\\s).{8,28})";

	public RoleController() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean validate(final String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	// open Search User page Controller
	@RequestMapping(value = "/search_user_mstUrl", method = RequestMethod.GET)
	public ModelAndView search_user_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_user_mstUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			
			Mmap.put("msg", msg);
			Mmap.put("getRoleNameList", getRoleNameList());
			Mmap.put("getUserarm_codeList", getUserarm_codeList());
			Mmap.put("access_lvl1", access_lvl1);
			Mmap.put("subaccess_lvl1", subaccess_lvl1);
			Mmap.put("user_sus_no1", user_sus_no1);
			Mmap.put("user_name", user_name1);
			Mmap.put("role_name", role_name);
			return new ModelAndView("search_user_mstTiles");
			
		}
	}
	// open search report
	@RequestMapping(value = "/getUserReportList",method=RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportList(@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_user_mstUrl", roleid);
		if(request.getHeader("Referer") == null || val == false){
			return null;
		}else {
			String qry = "";
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			qry = "";
			if (access_lvl1.equals("All") && !access_lvl1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ARMY_NO") && !access_lvl1.equals("ROLE")){
				qry += "";
			}
			if (!access_lvl1.equals("All") && access_lvl1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ARMY_NO") && !access_lvl1.equals("ROLE")){
				qry += " and l.username='" + user_name1 + "' ";
			}			
			if (access_lvl1 != "" && access_lvl1 != null && !access_lvl1.equals("All") && !access_lvl1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ARMY_NO") && !access_lvl1.equals("ROLE")){
				qry += " and r.access_lvl = '" + access_lvl1 + "' ";
			}
			if (subaccess_lvl1 != "" && subaccess_lvl1 != null && !subaccess_lvl1.equals("All") && !subaccess_lvl1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ROLE")){
				qry += " and r.sub_access_lvl  = '" + subaccess_lvl1 + "'";
			}
			if (user_sus_no1 != "" && user_sus_no1 != null && !user_sus_no1.equals("All") && !user_sus_no1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ROLE")){
				if (access_lvl1.equals("Line_dte")){
					if(!user_sus_no1.equals("0")){
						qry += " and l.user_arm_code  = '" + user_sus_no1 + "'";
					}
				}
				else if (access_lvl1.equals("ARMY_NO")) {
					qry += " and l.army_no='"+user_sus_no1+"'";
				}else {
					qry += " and l.user_sus_no  = '" + user_sus_no1 + "'";
				}
			}
			if (access_lvl1.equals("SUS_No") && !access_lvl1.equals("All") && !access_lvl1.equals("Username") &&!access_lvl1.equals("ROLE")) {
				qry += " and l.user_sus_no='" + user_sus_no1 + "' ";
			}
			if (!access_lvl1.equals("All") && !access_lvl1.equals("Username") && !access_lvl1.equals("SUS_No") && !access_lvl1.equals("ARMY_NO") && access_lvl1.equals("ROLE")){
				qry += " and r.role_id='" + user_sus_no1 + "' ";
			}
			DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasUserreport(criterias, qry, roleSubAccess);
			return DatatablesResponse.build(dataSet, criterias);
		}
	}
	@RequestMapping (value = "/admin/GetUsernameandRank", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GetUsernameandRank(ModelMap Mmap, HttpSession session,
			 String username1,HttpServletRequest request){
		ArrayList<ArrayList<String>> list = roledao.GetUsernameandRank(username1);
		return list;
	 }
	
	// New for View User History 
		@RequestMapping(value = "/Popup_UserHistoryUrl", method = RequestMethod.POST)
			public ModelAndView Popup_UserHistoryUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
					@RequestParam(value = "user_id1", required = false) BigInteger user_id) {
				
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("search_user_mstUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}

					System.err.println("val of user id1 " + user_id);
					
					List<ArrayList<String>> list = roledao.Popup_User_History(user_id);
					Mmap.put("list", list);
			
				Mmap.put("msg", msg);
				return new ModelAndView("Popup_UserHistory_tiles");
			}


}