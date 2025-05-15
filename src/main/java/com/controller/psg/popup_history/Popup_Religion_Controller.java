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
import com.dao.psg.popup_history.Change_Religion_History_DAO;
import com.dao.psg.popup_history.Change_Identity_History_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Popup_Religion_Controller {

	@Autowired
	private Change_Religion_History_DAO Religiondao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Change_In_ReligionUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_ReligionUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "rel_comm_id", required = false) BigInteger rel_comm_id,
			 @RequestParam(value = "rel_census_id", required = false) String rel_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("list", Religiondao.change_in_religion_dtl(rel_comm_id,Integer.parseInt(rel_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Change_Religion_History_Tiles");
		
	 }
}
