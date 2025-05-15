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

import com.dao.psg.JCO_Update_Census_Popup_History.Date_Of_Attestation_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Date_Of_Attestation_Jco_Controller {

	@Autowired
	private Date_Of_Attestation_History_DAO dtatte_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Date_Of_Attestation_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Dt_Of_Enroll_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "da_jco_id", required = false) String da_jco_id) {
		 
		    Mmap.put("list", dtatte_dao.Date_Of_Attestation_history(Integer.parseInt(da_jco_id)));
		    Mmap.put("msg", msg);
			 return new ModelAndView("Date_Of_Attestation_JCO_Tiles");
	}
}
