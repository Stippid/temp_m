package com.controller.psg.update_offr_data;


import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.Search_Canteen_DAOImpl;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})

public class Search_Canteen_CardController {
	
	@Autowired	
	private Search_Canteen_DAOImpl card_dao;
	Psg_CommonController p_comm = new Psg_CommonController();
	
	 @Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Search_Canteen_DetailUrl" , method = RequestMethod.GET)
	 public ModelAndView Search_Canteen_DetailUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Canteen_DetailUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		 
		 Mmap.put("msg", msg);	
		 Mmap.put("getExservicemenList", p_comm.getExservicemenList());
		 Mmap.put("list", card_dao.Search_canteen("","0",""));
		// Mmap.put("status1", "0");
		 return new ModelAndView("Search_canteen_DetailsTiles");
	 }
	
	 @RequestMapping(value = "/admin/getSearch_Canteen_Detail", method = RequestMethod.POST)
		public ModelAndView getSearch_Canteen_Detail(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "card_no1", required = false) String card_no,
				@RequestParam(value = "status1", required = false) String status,
				@RequestParam(value = "service1", required = false) String service){
		   String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Canteen_DetailUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 		String roleSusNo = session.getAttribute("roleSusNo").toString();
		 
				ArrayList<ArrayList<String>> list = card_dao.Search_canteen(card_no,status,service);
				//Mmap.put("name_val4", status);
				Mmap.put("list", list);
				Mmap.put("size", list.size());					
				Mmap.put("card_no1", card_no);
				Mmap.put("service1", service);
				Mmap.put("status1", status);
				Mmap.put("getExservicemenList", p_comm.getExservicemenList());
				return new ModelAndView("Search_canteen_DetailsTiles");
		}

	 
}
