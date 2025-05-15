package com.controller.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
import com.dao.psg.popup_history.Change_Appointment_History_DAO;
import com.dao.psg.popup_history.Change_Contact_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})


public class Popup_Contact_Details_Controller {
	
	@Autowired
	private Change_Contact_History_DAO changeContact;
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
	 @RequestMapping(value = "/Change_In_Contact_DetailsUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_Contact_DetailsUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "cd_comm_id", required = false) BigInteger cd_comm_id,
			 @RequestParam(value = "cd_census_id", required = false) int cd_census_id) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			List<ArrayList<String>> list = changeContact.change_Contact(cd_comm_id,cd_census_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("Change_Contact_Details_History_Tiles");
	 }
}
