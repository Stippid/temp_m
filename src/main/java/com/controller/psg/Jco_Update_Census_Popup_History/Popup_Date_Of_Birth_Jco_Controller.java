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

import com.dao.psg.JCO_Update_Census_Popup_History.Date_Of_Birth_Pop_History_DAO;


@Controller
@RequestMapping(value = {"admin","/","user"})


public class Popup_Date_Of_Birth_Jco_Controller {

	@Autowired
	private Date_Of_Birth_Pop_History_DAO dob_dao;
	
	
	
	 @RequestMapping(value = "/admin/Preview_Date_Of_Birth_Url", method = RequestMethod.POST)
	 public ModelAndView Date_Of_Birth_HistoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "dob_jco_id", required = false) String dob_jco_id) {
		 
		 Mmap.put("list", dob_dao.Date_Of_Birth_history(Integer.parseInt(dob_jco_id)));			 
			Mmap.put("msg", msg);
			 return new ModelAndView("DOB_JCO_Tiles");
		}
}
