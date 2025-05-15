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
import com.dao.psg.popup_history.Change_Rank_History_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Change_Rank_Controller {
	@Autowired
	private Change_Rank_History_DAO Rankdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/admin/Change_Of_RankUrl", method = RequestMethod.POST)
	 public ModelAndView Change_Of_RankUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "rank_comm_id", required = false) BigInteger rank_comm_id,
			 @RequestParam(value = "rank_census_id", required = false) String rank_census_id) {
		 
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("list", Rankdao.change_rank(rank_comm_id,Integer.parseInt(rank_census_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Change_RankTiles");
		
		
		
	 }
	
	

}
