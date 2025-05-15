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

import com.dao.psg.popup_history.Update_Child_Details_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_Child_DetailsController {
	
	@Autowired
	private Update_Child_Details_DAO UCdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
		
	 @RequestMapping(value = "/admin/Update_Child_dtlUrl", method = RequestMethod.POST)
	 public ModelAndView Update_Child_dtlUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "uc_comm_id", required = false) BigInteger uc_comm_id,
			 @RequestParam(value = "uc_census_id", required = false) String uc_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
            Mmap.put("list", UCdao.update_child_dtl(uc_comm_id,Integer.parseInt(uc_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_Child_Details_Tiles");
		
	 }

}
