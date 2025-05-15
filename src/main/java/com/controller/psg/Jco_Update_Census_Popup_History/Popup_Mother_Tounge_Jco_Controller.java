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

import com.dao.psg.JCO_Update_Census_Popup_History.Mother_Tounge_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Mother_Tounge_Jco_Controller {

	@Autowired
	private Mother_Tounge_History_DAO mt_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Mother_Tounge_Url", method = RequestMethod.POST)
	 public ModelAndView Mother_tounge_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "mt_jco_id", required = false) String mt_jco_id) {
		 
		
			Mmap.put("list", mt_dao.Mother_Tounge_history(Integer.parseInt(mt_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Mother_Tounge_JCO_Tiles");
	}
}
