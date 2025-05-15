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
import com.dao.psg.popup_history.Extension_of_SSC_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Extension_of_SSC_Controller {

	@Autowired
	private Extension_of_SSC_History_DAO ExtensionofSSC;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
	 @RequestMapping(value = "/Extension_of_SSCUrl", method = RequestMethod.POST)
	 public ModelAndView Extension_of_SSCUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "eos_comm_id", required = false) BigInteger eos_comm_id,
			 @RequestParam(value = "eos_census_id", required = false) int eos_census_id) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<ArrayList<String>> list = ExtensionofSSC.Extension_of_SSC(eos_comm_id,eos_census_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("Extension_of_SSC_history_Tiles");
	 }
}
