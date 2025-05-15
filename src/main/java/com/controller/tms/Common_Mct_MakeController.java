package com.controller.tms;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MakeMasterDAO;
import com.dao.tms.MakeMasterDAOImp;
import com.models.TB_TMS_MAKE_MASTER;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Common_Mct_MakeController {

	@Autowired
	MakeMasterDAO makeMasretDAO = new MakeMasterDAOImp();
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController validation = new ValidationController();
	
	 @RequestMapping(value = "/admin/make_master_transport", method = RequestMethod.GET)
	 public ModelAndView Make_master_transport(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("make_master_transport", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 Mmap.put("msg", msg);
		 return new ModelAndView("make_master_transportTiles");
	 }

	 @RequestMapping(value = "/make_master_transportAction", method = RequestMethod.POST)
	 public ModelAndView Make_master_transportAction(@ModelAttribute("make_master_transportCMD") TB_TMS_MAKE_MASTER rs, HttpServletRequest request, ModelMap model, HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_master_transport", roleid);	
		if(val == false) {
				return new ModelAndView("AccessTiles");
			}	
		
		 String username = session.getAttribute("username").toString();
		 String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		 rs.setCreated_by(username);
		 rs.setCreated_on(date);
		 String mct_slot_id = request.getParameter("mct_slot_id").toString();
		 String make_no = request.getParameter("make_no").toString();
		 String description = request.getParameter("description").toString();
		 Pattern pattern = Pattern.compile(".*[^0-9].*");
		 String strmake_no = request.getParameter("make_no").toString();
		if(!pattern.matcher(strmake_no).matches() == false) 
		{
			  model.put("msg", "Please Enter Valid Make No.");
			  return new ModelAndView("redirect:make_master_transport");
		}
		 if(mct_slot_id.equals("") || mct_slot_id.equals("null")  || mct_slot_id.equals(null))
		 {
			 model.put("msg", "Please Select the MCT Slot ID.");
			 return new ModelAndView("redirect:make_master_transport");
		 }
		 else  if(make_no.equals("") || make_no.equals("null")  || make_no.equals(null))
		 {
			 model.put("msg", "Please Enter the Make No.");
			 return new ModelAndView("redirect:make_master_transport");
		 }
		 else if(validation.make_noLength(make_no) == false)
		 {
		 		model.put("msg",validation.make_noMSG);
				return new ModelAndView("redirect:make_master_transport");
		 }
		 else  if(description.equals("") || description.equals("null")  || description.equals(null))
		 {
			 model.put("msg", "Please Enter the Description.");
			 return new ModelAndView("redirect:make_master_transport");
		 }
		 else if(validation.descriptionLength(description) == false)
		 {
		 		model.put("msg",validation.descriptionMakeMSG);
				return new ModelAndView("redirect:make_master_transport");
		 }
		 else
		 {
			 Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			 try
			 {
				 sessionHql.beginTransaction();
				 if(makeMasretDAO.ifExistMakeNo(mct_slot_id, make_no) != false) {
					 model.put("msg", "Make No Already Exist for Current MCT MAIN.");
					 return new ModelAndView("redirect:make_master_transport");
				 }else {
					 sessionHql.save(rs);
					 sessionHql.getTransaction().commit();
					 
					 model.put("msg", "MAKE Successfully Added.");
					 return new ModelAndView("redirect:make_master_transport");		 
				 }
			 }
			 catch (Exception e)
			 {
				 	sessionHql.getTransaction().rollback();
					return null;
			 }
			 finally
			 {
				 sessionHql.close();
			 }
		 }
	 }
	 
	@RequestMapping(value = "/getmaxMakeNo")
	public @ResponseBody String getmaxMakeNo(String mctSlotId,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		return makeMasretDAO.getmaxMakeNo(mctSlotId);
	}
}