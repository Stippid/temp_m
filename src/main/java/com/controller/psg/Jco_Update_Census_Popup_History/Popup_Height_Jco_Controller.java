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

import com.dao.psg.JCO_Update_Census_Popup_History.Height_History_DAO;;

//import com.dao.psg.popup_history.Canteen_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Height_Jco_Controller {

	@Autowired
	private Height_History_DAO h_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Height_Url", method = RequestMethod.POST)
	 public ModelAndView Nationality_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "h_jco_id", required = false) String h_jco_id) {
		 
		    Mmap.put("list", h_dao.Height_history(Integer.parseInt(h_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Height_JCO_Tiles");
	}
}
