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
import com.dao.psg.popup_history.Change_Marital_Status_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Popup_Marital_Status_Controller {

	@Autowired
	private Change_Marital_Status_DAO MSdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
		
	 @RequestMapping(value = "/admin/Change_In_Marital_StatusUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_Marital_StatusUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "ms_comm_id", required = false) BigInteger ms_comm_id,
			 @RequestParam(value = "ms_census_id", required = false) String ms_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("list", MSdao.change_in_marital_status_dtl(ms_comm_id,Integer.parseInt(ms_census_id)));
			Mmap.put("qualilist", MSdao.getSpouseQualification(ms_comm_id));
			Mmap.put("msg", msg);
			 return new ModelAndView("Change_Marital_Status_Tiles");
		
	 }
}
