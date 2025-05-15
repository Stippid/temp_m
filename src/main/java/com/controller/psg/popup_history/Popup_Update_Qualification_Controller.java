package com.controller.psg.popup_history;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.popup_history.Update_Qualification_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Update_Qualification_Controller {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Update_Qualification_History_DAO uqdao;
	@RequestMapping(value = "/admin/Update_QualificationUrl", method = RequestMethod.POST)
	 public ModelAndView Update_QualificationUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "uq_comm_id", required = false) BigInteger uq_comm_id,
			 @RequestParam(value = "uq_census_id", required = false) String uq_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", uqdao.update_qualification(uq_comm_id,Integer.parseInt(uq_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_QualificationTiles");
		
		
		
	 }
	







}






















