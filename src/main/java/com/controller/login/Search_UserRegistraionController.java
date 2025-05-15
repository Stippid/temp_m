package com.controller.login;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.login.User_RegistraionDAO;
import com.models.Role;
import com.models.UserLogin;
import com.models.UserRole;
import com.models.login.TB_MISO_USER_REGISTRATION;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})

public class Search_UserRegistraionController {

	@Autowired
	private User_RegistraionDAO cat;
	ValidationController validation = new ValidationController();
	RoleController rolecontroller = new RoleController();
	
	@RequestMapping(value = "/Search_User_regUrl", method = RequestMethod.GET)
	public ModelAndView Search_User_regUrl(ModelMap Mmap,HttpSession session,
        @RequestParam(value = "msg", required = false) String msg){
			Mmap.put("msg",msg);
			
	  	return new ModelAndView("Search_UserRegTiles");
		}
	
	@RequestMapping(value = "/getFilteredUserList_SQL", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredUserList_SQL(int startPage,String pageLength,
			String Search,String orderColunm,String orderType,String ic_no,String status,HttpSession sessionUserId){
	
		return cat.DataTable_Cat_List(startPage,pageLength,Search,orderColunm,orderType,ic_no,status);
	}
	
	@RequestMapping(value = "/getUserTotalCount_SQL", method = RequestMethod.POST)
	public @ResponseBody long getUserTotalCount_SQL(HttpSession sessionUserId,String Search,String ic_no,String status){
	
		return cat.DataTable_Cat_TotalCount(Search,ic_no,status);
		
	}	
	
	@SuppressWarnings("unchecked")
	public TB_MISO_USER_REGISTRATION getTB_MISO_USER_REGISTRATION(int id) {
		String qry = "from TB_MISO_USER_REGISTRATION where id=:id ";
		List<TB_MISO_USER_REGISTRATION> meeting = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(qry);
			q.setParameter("id", id);
			meeting = (List<TB_MISO_USER_REGISTRATION>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}
		if (meeting.size() > 0) {
			return meeting.get(0);
		} else {
			return null;
		}
		
		
	}
	
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
	@RequestMapping(value = "/view_user_registration", method = RequestMethod.POST)
	public ModelAndView view_user_registration(@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "id1", required = false) int id, HttpServletRequest request, HttpSession session,
			ModelMap Mmap) {
		Mmap.put("msg", msg);
		Mmap.put("l", getTB_MISO_USER_REGISTRATION(id));
		Mmap.put("getRoleNameList", getRoleNameList());
		return new ModelAndView("user_registration_viewTiles");
	}
	
	@RequestMapping(value = "/admin/getDownloadAttachmentURl", method = RequestMethod.POST)
	public void getDownloadAttachmentURl(@ModelAttribute("id") int id,ModelMap model ,HttpServletRequest request,HttpServletResponse response){
		String EXTERNAL_FILE_PATH = "";
		if(id != 0) {
			TB_MISO_USER_REGISTRATION m = getTB_MISO_USER_REGISTRATION(id);
			if(m.getDoc() != null) {
				EXTERNAL_FILE_PATH = m.getDoc();
			}
		}
		File file = null;
	    file = new File(EXTERNAL_FILE_PATH);
	    try{
	    	if(!file.exists()){
	    		String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_doc.pdf";
	    		file = new File(fullPath);
	    	}
	    }
	    catch(Exception exception){
	    }
	    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	    if(mimeType==null){
	    	mimeType = "application/octet-stream";
	    }
	    response.setContentType(mimeType);
	    response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	    response.setContentLength((int)file.length());
	    InputStream inputStream = null;
    
	    try {
    		inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TB_MISO_USER_REGISTRATION> getUserRegistrationData(String id) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q = session1.createQuery("from TB_MISO_USER_REGISTRATION  where id=:id order by id desc");
		q.setParameter("id", Integer.parseInt(id));
		List<TB_MISO_USER_REGISTRATION> olist = (List<TB_MISO_USER_REGISTRATION>) q.list();
		tx1.commit();
		session1.close();
		return olist;
	}
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/user_regitrastionUpdate_action", method = RequestMethod.POST)
	public @ResponseBody String user_regitrastionUpdate_action(ModelMap Mmap, HttpServletRequest request,HttpSession session){
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_User_regUrl", roleid);
		if (val == false) {
			return "You do not have permission to access This Operation";
		}
		try {
			String id = request.getParameter("id");
			List<TB_MISO_USER_REGISTRATION> olist = getUserRegistrationData(id);
			
			
			String pass = request.getParameter("user_password");
			String user_id = request.getParameter("user_id");
			String re_pass = request.getParameter("user_re_password");
			int roll = Integer.parseInt(request.getParameter("user_role_id"));
			String army_no = "";
			String role = request.getParameter("user_role_id");
			String Login_name = ""; 
			String user_sus_no = ""; 
			if(olist.size() > 0) {
				army_no = olist.get(0).getArmy_no();
				Login_name = olist.get(0).getUser_name(); 
				user_sus_no = olist.get(0).getSus_no();
			}
			
			boolean pass_valid = rolecontroller.validate(pass);
			if (pass_valid == false) {
				msg ="Password pattern doesn't match.";
			}

			if (Login_name.equals("")) {
				msg = "Please Enter User Name.";
			} else if (validation.LoginNameLength(Login_name) == false) {
				 msg = validation.LoginNameMSG;
			}
			if (user_id == "" || user_id.equals("")) {
				msg = "Please Enter User ID.";
			} else if (validation.UserNameLength(user_id) == false) {
				msg = validation.UserNameMSG;
			}
			if (army_no == "" || army_no.equals("")) {
				msg = "Please Enter Army No.";
			} else if (validation.ArmyNoLength(army_no) == false) {
				msg = validation.ArmyNoMSG;
			}
			if (pass == "" || pass.equals("")) {
				msg = "Please Enter User ID.";
			} else if (validation.PasswordLength(pass) == false) {
				msg = validation.PasswordMSG;
			}
			else if(!PasswordValidator.validate(pass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
				msg = validation.PasswordPatternMSG;
			}
			if (!pass.trim().equals(re_pass.trim())) {
				msg = "Password and Re-Password didn't match";
			}
			if (role == "" || role.equals("")) {
				msg = "Please Select Role";
			}
			if (!roledao.getuserExist(user_id).equals(false)) {
				msg = "Data Already Exist";
			} else {
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(pass);
				UserLogin p = new UserLogin();
				p.setLogin_name(Login_name);
				p.setPassword(hashedPassword);
				p.setUserName(user_id);
				p.setUser_Arm_code("");
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
				p.setAc_dc_date(modifydate);
				p.setArmy_no(army_no);
				p.setUser_sus_no(user_sus_no);

				if (request.getParameter("access_lve1").equals("Formation")) {
					p.setUser_formation_no(rolecontroller.getsusnobyfromation(user_sus_no).toString().replace("[", "").replace("]", ""));
				} else {
					p.setUser_formation_no("null");
				}
				UserRole role_tbl = new UserRole();
				sessionHQL.beginTransaction();
				
				int did = (Integer) sessionHQL.save(p); //Save logininformation
				
				role_tbl.setRoleId(roll);
				role_tbl.setUserId(did);
				sessionHQL.save(role_tbl); // save userroleinformation
				
				TB_MISO_USER_REGISTRATION sum = new TB_MISO_USER_REGISTRATION();
				sum = olist.get(0);
				sum.setUser_id(user_id);
				sessionHQL.saveOrUpdate(sum); // update registration
				
				sessionHQL.getTransaction().commit();
				
				roledao.userinsertdata("insert", did, roll);
				msg = "Data updated Successfully";
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();
			msg = "No Transaction Made";
		}finally {
			sessionHQL.close();
		}
		return msg;
	}
}