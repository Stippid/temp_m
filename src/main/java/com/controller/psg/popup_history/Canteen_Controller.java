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
import com.dao.psg.popup_history.Canteen_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Canteen_Controller {
	
	@Autowired
	private Canteen_DAO c_dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/admin/Canteen_HistoryUrl", method = RequestMethod.POST)
	 public ModelAndView Canteen_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "canteen_comm_id", required = false) BigInteger canteen_comm_id,
			 @RequestParam(value = "canteen_census_id", required = false) String canteen_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", c_dao.Canteen_history(canteen_comm_id,Integer.parseInt(canteen_census_id)));			 
			Mmap.put("msg", msg);
			 return new ModelAndView("Canteen_History_Tiles");
		
		
		
	 }
	
	

	 
	 
}






