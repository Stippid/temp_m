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
import com.dao.psg.popup_history.Change_Appointment_History_DAO;
import com.dao.psg.popup_history.Change_Name_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Change_Appointment_Controller {

	@Autowired
	private Change_Appointment_History_DAO changeAppointment;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	//-----------------------------------Page open----------------------------------------//
	
		 @RequestMapping(value = "/Change_In_AppointmentUrl", method = RequestMethod.POST)
		 public ModelAndView Change_In_AppointmentUrl(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				 @RequestParam(value = "appointment_comm_id", required = false) BigInteger appointment_comm_id,
				 @RequestParam(value = "appointment_census_id", required = false) int appointment_census_id) {
			
			 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
		        if(val == false) {
		                return new ModelAndView("AccessTiles");
		        }

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				List<ArrayList<String>> list = changeAppointment.change_Appointment(appointment_comm_id,appointment_census_id);
				 Mmap.put("list", list);
			 
			 Mmap.put("msg", msg);
			
			return new ModelAndView("appointment_history_tiles");
		 }
}
