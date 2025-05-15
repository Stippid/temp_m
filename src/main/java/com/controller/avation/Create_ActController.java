package com.controller.avation;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Create_ActController {


	
	/*@RequestMapping(value = "/admin/add_act_noURL")
	public ModelAndView Add_act_noURL(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		
		Mmap.put("msg", msg);
		return new ModelAndView("add_act_noTiles");
	}
	
	*/
	
}
