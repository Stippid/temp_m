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
//import com.dao.psg.popup_history.ChangeRank_DAO;
//import com.dao.psg.popup_history.Change_NameDAO;
import com.dao.psg.popup_history.Change_Name_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Change_Name_Controller {
	
	@Autowired
	private Change_Name_History_DAO changeName;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
	 @RequestMapping(value = "/Change_In_NameUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_NameUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "name_comm_id", required = false) BigInteger name_comm_id,
			 @RequestParam(value = "name_census_id", required = false) int name_census_id) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			  List<ArrayList<String>> list = changeName.change_name(name_comm_id,name_census_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("Name_history_tiles");
	 }
	

}
