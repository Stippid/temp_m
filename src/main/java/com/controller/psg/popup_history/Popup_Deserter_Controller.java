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
import com.dao.psg.popup_history.Deserter_History_DAO;
//import com.dao.psg.popup_history.Secondment_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Deserter_Controller {
	
	@Autowired
	private Deserter_History_DAO changeDeserter;
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
	 @RequestMapping(value = "/DeserterUrl", method = RequestMethod.POST)
	 public ModelAndView DeserterUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "deserter_comm_id", required = false) BigInteger deserter_comm_id,
			 @RequestParam(value = "deserter_census_id", required = false) int deserter_census_id) {
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			 
			List<ArrayList<String>> list = changeDeserter.change_Deserter(deserter_comm_id,deserter_census_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("Deserter_Tiles");
	 }

	
		
	}


