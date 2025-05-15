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
import com.dao.psg.popup_history.ChangeAddress_DAO;



@Controller
@RequestMapping(value = {"admin","/","user"})

public class Change_In_AddressController {
	
	@Autowired
	private ChangeAddress_DAO ChangeAddress;
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/Change_In_AddressUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_AddressUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "ca_comm_id", required = false) BigInteger ca_comm_id,
			 @RequestParam(value = "ca_census_id", required = false) String ca_census_id) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list",ChangeAddress.change_address_details(ca_comm_id,Integer.parseInt(ca_census_id)));
			 Mmap.put("msg", msg);
		return new ModelAndView("Change_In_Address_Tiles");
	 }

}
