package com.controller.orbat;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class Unit_Data_Controller {	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	Psg_CommonController p_comm = new Psg_CommonController();
	
	@RequestMapping(value = "/Unit_Datasd", method = RequestMethod.GET)
	public ModelAndView Unit_Datasd(ModelMap Mmap,HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		
		//Boolean val = roledao.ScreenRedirect("Disbandment_schedule", roleid);	
		/*
		 * if(val == false) { return new ModelAndView("AccessTiles"); }
		 */
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getCorpsList", all.getCorpsDetailsList());
		Mmap.put("getDivList", all.getDivDetailsList());
		Mmap.put("getBdeList", all.getBdeDetailsList());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		return new ModelAndView("Unit_data_Tiles");
	}
}
