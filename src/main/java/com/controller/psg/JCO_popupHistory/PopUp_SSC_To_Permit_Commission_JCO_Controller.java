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
import com.dao.psg.JCO_Popup.Ssc_To_Permit_Commission_Popup_JCO_DAO;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class PopUp_SSC_To_Permit_Commission_JCO_Controller {
	@Autowired
	Ssc_To_Permit_Commission_Popup_JCO_DAO sscDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Ssc_To_Permit_CommissionUrl", method = RequestMethod.GET)
	 public ModelAndView Ssc_To_Permit_CommissionUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "ssc_jco_id", required = false) String ssc_jco_id) {
		 
		 Boolean val = roledao.ScreenRedirect("update_jco_data_url", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			 Mmap.put("list", sscDao.Ssc_To_Permit_Commission_JCO(Integer.parseInt(ssc_jco_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Ssc_To_Permit_Commission_Popup_JCOTiles");
	 }
}
