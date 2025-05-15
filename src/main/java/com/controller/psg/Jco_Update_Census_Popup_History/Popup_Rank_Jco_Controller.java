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

import com.dao.psg.JCO_Update_Census_Popup_History.Rank_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Rank_Jco_Controller {

	@Autowired
	private Rank_History_DAO r_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Rank_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Rank_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "r_jco_id", required = false) String r_jco_id) {
		 
		    Mmap.put("list", r_dao.Rank_history(Integer.parseInt(r_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Rank_JCO_Tiles");
	}
}
