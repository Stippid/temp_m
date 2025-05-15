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
import com.dao.psg.popup_history.Promotional_Exam_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Promotional_Exam_Controller {
	@Autowired
	private Promotional_Exam_History_DAO pedao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Promotional_ExamUrl", method = RequestMethod.POST)
	 public ModelAndView Promotional_ExamUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "pe_comm_id", required = false) BigInteger pe_comm_id,
			 @RequestParam(value = "pe_census_id", required = false) String pe_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", pedao.promotional_exam(pe_comm_id,Integer.parseInt(pe_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Promotional_ExamTiles");
		
		
		
	 }
	







}
