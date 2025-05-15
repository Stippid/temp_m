package com.controller.tms;


import java.text.SimpleDateFormat;
import java.time.Year;
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

public class C_Holding_Controller {



	@RequestMapping(value = "/admin/C_holding_url")
	public ModelAndView C_holding_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("C_holding_url", roleid);
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

		int currentYear = Year.now().getValue();
	    int previousYear = currentYear - 1;
	    int lastYear = currentYear % 100 ;
	    model.put("previousYear", previousYear);
	    model.put("currentYear", currentYear);
	    model.put("lastYear", lastYear);
	    
	    int futureYear2 = currentYear + 1;
	    int lastYear2 = currentYear % 100 + 1;
	    model.put("futureYear2", futureYear2);
	    model.put("lastYear2", lastYear2);

	    int futureYear3 = currentYear + 2;
	    int lastYear3 = currentYear % 100 + 2;
	    model.put("futureYear3", futureYear3);
	    model.put("lastYear3", lastYear3);
		
	    int futureYear4 = currentYear + 3;
	    int lastYear4 = currentYear % 100 + 3;
	    model.put("futureYear4", futureYear4);
	    model.put("lastYear4", lastYear4);
	    

	    int lastYear5 = currentYear % 100 + 4;
	    model.put("lastYear5", lastYear5);
	    
	    
	     
			return new ModelAndView("c_holdingTiles");
	}
}