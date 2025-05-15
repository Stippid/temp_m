package com.controller.psg.popup_history;
import java.math.BigInteger;
import java.util.ArrayList;

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
import com.dao.psg.popup_history.Update_Firing_Standard_History_DAO;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_FiringStandard_Controller {
	@Autowired
	private Update_Firing_Standard_History_DAO fsdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Update_Firing_StandardUrl", method = RequestMethod.POST)
	 public ModelAndView Update_Firing_StandardUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "fs_comm_id", required = false) BigInteger fs_comm_id,
			 @RequestParam(value = "fs_census_id", required = false) String fs_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", fsdao.update_firing_standard_details(fs_comm_id,Integer.parseInt(fs_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_Firing_StandardTiles");
		
		
		
	 }


}
