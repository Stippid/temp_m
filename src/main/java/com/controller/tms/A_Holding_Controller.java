package com.controller.tms;


import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Oh_Qfd_DAO;
import com.dao.tms.Track_status_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class A_Holding_Controller {



	@RequestMapping(value = "/admin/A_holding_url")
	public ModelAndView A_holding_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("A_holding_url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
//		String roleAccess = session.getAttribute("roleAccess").toString();
//		ArrayList<ArrayList<String>> list = t_dao.search_track_status(session);
		
//		model.put("list", list);
//		model.put("size", list.size());
//		model.put("roleAccess", roleAccess);
//		model.put("base_work_shopList", mari_dao.base_work_shopList());
//		model.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		model.put("date", date1);
		
//		 int currentYear = Year.now().getValue();
//	     int previousYear = currentYear-1;
//	     model.put("previousYear", previousYear);  
//		
//	     
//	     int year = Calendar.getInstance().get(Calendar.YEAR);
//	     
//	     model.put("year", year);

				return new ModelAndView("a_holdingTiles");
	}
}