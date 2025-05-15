package com.controller.psg.popup_history;

import java.math.BigInteger;

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
import com.dao.psg.popup_history.Inter_Arm_Service_Transfer_History_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Popup_Inter_Arm_Service_Transfer_Controller {

	@Autowired
	private Inter_Arm_Service_Transfer_History_DAO IASTdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Inter_Arm_Service_TransferUrl", method = RequestMethod.POST)
	 public ModelAndView Inter_Arm_Service_TransferUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "as_comm_id", required = false) BigInteger as_comm_id,
			 @RequestParam(value = "as_census_id", required = false) String as_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("list", IASTdao.Inter_Arm_Service_Transfer(as_comm_id,Integer.parseInt(as_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Inter_Arm_Service_Transfer_Tiles");
		
	 }
}
