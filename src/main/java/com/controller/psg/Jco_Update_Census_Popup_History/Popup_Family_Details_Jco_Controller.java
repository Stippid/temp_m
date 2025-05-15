package com.controller.psg.Jco_Update_Census_Popup_History;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.psg.JCO_Update_Census_Popup_History.Family_Details_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Family_Details_Jco_Controller {

	@Autowired
	private Family_Details_History_DAO fd_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Family_Details_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Family_Details_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "fd_jco_id", required = false) String fd_jco_id) {
		 
		    Mmap.put("list", fd_dao.Family_Details_history(Integer.parseInt(fd_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Family_Details_JCO_Tiles");
	}
}
