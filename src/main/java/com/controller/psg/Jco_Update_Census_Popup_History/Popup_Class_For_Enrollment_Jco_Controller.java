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

import com.dao.psg.JCO_Update_Census_Popup_History.Class_For_Enrollment_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Class_For_Enrollment_Jco_Controller {

	@Autowired
	private Class_For_Enrollment_History_DAO ce_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Class_Enroll_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Class_Of_Enrollement_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "ce_jco_id", required = false) String ce_jco_id) {
		 
		    Mmap.put("list", ce_dao.Class_For_Enrollment_history(Integer.parseInt(ce_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Class_For_Enrollment_JCO_Tiles");
	}
}
