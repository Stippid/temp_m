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

import com.dao.psg.JCO_Update_Census_Popup_History.Blood_Group_History_DAO;


@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Blood_Group_Jco_Controller {

	@Autowired
	private Blood_Group_History_DAO bg_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Blood_Group_Url", method = RequestMethod.POST)
	 public ModelAndView Blood_Group_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "bg_jco_id", required = false) String bg_jco_id) {
		 
		
			Mmap.put("list", bg_dao.Blood_Group_history(Integer.parseInt(bg_jco_id)));
			
		    Mmap.put("msg", msg);
			 return new ModelAndView("Blood_Group_JCO_Tiles");
	}
}
