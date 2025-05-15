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
import com.dao.psg.popup_history.Non_Effective_Status_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Non_Effective_Status_Controller {
	@Autowired
	private Non_Effective_Status_History_DAO nesdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Non_Effective_Status_DetailsUrl", method = RequestMethod.POST)
	 public ModelAndView Non_Effective_Status_DetailsUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "nes_comm_id", required = false) BigInteger nes_comm_id,
			 @RequestParam(value = "nes_census_id", required = false) String nes_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list",nesdao.update_non_effective_status_details(nes_comm_id,Integer.parseInt(nes_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Non_Effective_Status_DetailsTiles");
		
		
		
	 }
}
