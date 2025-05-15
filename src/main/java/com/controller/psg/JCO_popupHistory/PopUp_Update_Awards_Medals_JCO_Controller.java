package com.controller.psg.JCO_popupHistory;

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
import com.dao.psg.JCO_Popup.Update_Awards_Medals_Popup_JCO_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class PopUp_Update_Awards_Medals_JCO_Controller {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Update_Awards_Medals_Popup_JCO_DAO updateawardsmedalsDao;

	 @RequestMapping(value = "/admin/Update_Awards_MedalsJCOUrl", method = RequestMethod.POST)
	 public ModelAndView Update_Awards_MedalsJCOUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "update_awards_medals_jco_id", required = false) String update_awards_medals_jco_id) {
		 
		 Boolean val = roledao.ScreenRedirect("update_jco_data_url", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", updateawardsmedalsDao.Update_Awards_Medals_JCO(Integer.parseInt(update_awards_medals_jco_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_Awards_Medals_Popup_JCOTiles");
	 }


}
