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
import com.dao.psg.popup_history.Change_In_Medicle_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Change_In_Medicle_Controller {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
		@Autowired
		private Change_In_Medicle_History_DAO cmdao;
		@RequestMapping(value = "/admin/Change_In_MedicleUrl", method = RequestMethod.POST)
		 public ModelAndView Change_In_MedicleUrl(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				 @RequestParam(value = "cim_comm_id", required = false) BigInteger cim_comm_id,
				 @RequestParam(value = "cim_census_id", required = false) String cim_census_id) {
			 
			 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
		        if(val == false) {
		                return new ModelAndView("AccessTiles");
		        }

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				Mmap.put("list", cmdao.change_in_medicle(cim_comm_id,Integer.parseInt(cim_census_id)));
				 Mmap.put("msg", msg);
				 return new ModelAndView("Change_In_MedicleTiles");
		 }
		
	
}
