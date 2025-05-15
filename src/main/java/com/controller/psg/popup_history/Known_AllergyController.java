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
import com.dao.psg.popup_history.Known_AllergyDAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Known_AllergyController {
	
	@Autowired
	private Known_AllergyDAO KADAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	 
	 @RequestMapping(value = "/admin/Known_Allergy_HistoryUrl", method = RequestMethod.POST)
	 public ModelAndView Known_Allergy_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "ka_comm_id", required = false) BigInteger ka_comm_id,
			 @RequestParam(value = "ka_census_id", required = false) String ka_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", KADAO.known_allergy_history(ka_comm_id,Integer.parseInt(ka_census_id)));			 
			Mmap.put("msg", msg);
			 return new ModelAndView("Change_Allergy_History_Tiles");
		
		
		
	 }
	
	

	 
	 
}






