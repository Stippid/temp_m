package com.controller.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//import com.dao.psg.popup_history.Change_Appointment_History_DAO;
import com.dao.psg.popup_history.Secondment_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Popup_Secondment_Controller {

	@Autowired
	private Secondment_History_DAO changeSecondment;
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
		 @RequestMapping(value = "/SecondmentUrl", method = RequestMethod.POST)
		 public ModelAndView SecondmentUrl(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				 @RequestParam(value = "Secondment_comm_id", required = false) BigInteger Secondment_comm_id,
				 @RequestParam(value = "Secondment_census_id", required = false) int Secondment_census_id) {
			
			 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
		        if(val == false) {
		                return new ModelAndView("AccessTiles");
		        }

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				List<ArrayList<String>> list = changeSecondment.change_Secondment(Secondment_comm_id,Secondment_census_id);
				 Mmap.put("list", list);
			 
			 Mmap.put("msg", msg);
			
			return new ModelAndView("Secondment_Tiles");
		 }
}
