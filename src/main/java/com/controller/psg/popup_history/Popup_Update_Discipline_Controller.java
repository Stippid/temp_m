package com.controller.psg.popup_history;

import java.math.BigInteger;

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
import com.dao.psg.popup_history.Update_Discipline_History_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Popup_Update_Discipline_Controller {
	
	@Autowired
	private Update_Discipline_History_DAO desDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Update_DisciplineUrl", method = RequestMethod.POST)
	 public ModelAndView Update_DisciplineUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "ud_comm_id", required = false) BigInteger ud_comm_id,
			 @RequestParam(value = "ud_census_id", required = false) String ud_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("list", desDAO.update_discipline(ud_comm_id,Integer.parseInt(ud_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_Discipline_Tiles");
		
	 }
	
}
