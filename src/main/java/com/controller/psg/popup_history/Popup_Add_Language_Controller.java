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
import com.dao.psg.popup_history.Add_Language_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Add_Language_Controller {
	@Autowired
	private Add_Language_History_DAO aldao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Add_LanguageUrl", method = RequestMethod.POST)
	 public ModelAndView Add_LanguageUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "al_comm_id", required = false) BigInteger al_comm_id,
			 @RequestParam(value = "al_census_id", required = false) String al_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list_l", aldao.add_languages(al_comm_id,Integer.parseInt(al_census_id)));
			Mmap.put("list_f", aldao.add_forigen_languages(al_comm_id,Integer.parseInt(al_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Add_LanguageTiles");
		
		
		
	 }
}
