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

import com.dao.psg.JCO_Update_Census_Popup_History.PanNo_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_PanNo_Jco_Controller {

	@Autowired
	private PanNo_History_DAO p_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Pan_No_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Pan_No_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "p_jco_id", required = false) String p_jco_id) {
		 
		    Mmap.put("list", p_dao.PanNo_history(Integer.parseInt(p_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Panno_JCO_Tiles");
	}
}
