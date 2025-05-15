package com.controller.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.dao.psg.popup_history.Change_NOKDAO;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Change_NOKController {

	@Autowired
	private Change_NOKDAO exc1;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/Change_NOKUrl", method = RequestMethod.POST)
	 public ModelAndView Change_NOKUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "nok_comm_id", required = false) BigInteger comm_id,
			 @RequestParam(value = "nok_census_id", required = false) String census_id) {
		 
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
		
			
			  List<ArrayList<String>> list = exc1.change_nok_history_dtl(comm_id,Integer.parseInt(census_id));
			  Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
			/* Mmap.put("getStatusMasterList", pcommon.getStatusMasterList()); */
		return new ModelAndView("Change_Nok_History_Tiles");
	 }

	
	
}
