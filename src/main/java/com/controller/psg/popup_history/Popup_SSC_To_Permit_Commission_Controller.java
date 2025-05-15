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
import com.dao.psg.popup_history.SSC_To_Permit_Commission_History_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Popup_SSC_To_Permit_Commission_Controller {
	@Autowired
	private SSC_To_Permit_Commission_History_DAO SSCdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
		
	 @RequestMapping(value = "/admin/Ssc_To_Permit_CommissionUrl", method = RequestMethod.POST)
	 public ModelAndView Ssc_To_Permit_CommissionUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "ssc_comm_id", required = false) BigInteger ssc_comm_id,
			 @RequestParam(value = "ssc_census_id", required = false) String ssc_census_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		Mmap.put("list", SSCdao.ssc_to_permit_commission_dtl(ssc_comm_id,Integer.parseInt(ssc_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Ssc_To_Permit_Commission_History_Tiles");
		
	 }
}
