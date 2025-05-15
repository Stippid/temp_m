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
import com.dao.psg.popup_history.Update_BPETDetails_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_BPETDetails_Controller {
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Update_BPETDetails_History_DAO bpetdao;
	@RequestMapping(value = "/admin/Update_BPET_DetailsUrl", method = RequestMethod.POST)
	 public ModelAndView Update_BPET_DetailsUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "bpet_comm_id", required = false) BigInteger bpet_comm_id,
			 @RequestParam(value = "bpet_census_id", required = false) String bpet_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", bpetdao.update_bpet_details(bpet_comm_id,Integer.parseInt(bpet_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_BPETDetailsTiles");
		
		
		
	 }

}
