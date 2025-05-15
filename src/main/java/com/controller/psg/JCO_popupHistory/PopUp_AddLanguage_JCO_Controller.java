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
import com.dao.psg.JCO_Popup.Add_Language_Popup_JCO_DAO;;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class PopUp_AddLanguage_JCO_Controller {
	
	@Autowired
	Add_Language_Popup_JCO_DAO addlanguageDao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/admin/Add_LanguageJCOUrl", method = RequestMethod.POST)
	 public ModelAndView Add_LanguageJCOUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "add_language_jco_id", required = false) String add_language_jco_id,
			 @RequestParam(value = "add_foriegn_language_jco_id", required = false) String add_foriegn_language_jco_id) {
		 

		 Boolean val = roledao.ScreenRedirect("update_jco_data_url", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("list_l", addlanguageDao.Add_Language_JCO(Integer.parseInt(add_language_jco_id)));
			Mmap.put("list_f", addlanguageDao.Add_Foreign_Language_JCO(Integer.parseInt(add_foriegn_language_jco_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Add_Language_Popup_JCOTiles");
	 }

}
