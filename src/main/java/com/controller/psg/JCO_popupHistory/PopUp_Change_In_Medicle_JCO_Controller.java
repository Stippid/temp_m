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
import com.dao.psg.JCO_Popup.Change_In_Medicle_JCO_DAO;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class PopUp_Change_In_Medicle_JCO_Controller {
	
	
	@Autowired
	Change_In_Medicle_JCO_DAO changeinmedicleDao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	 @RequestMapping(value = "/admin/Change_In_MedicleJCOUrl", method = RequestMethod.POST)
	 public ModelAndView Change_In_MedicleJCOUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "change_in_medicle_jco_id", required = false) String change_in_medicle_jco_id) {
		 
		 Boolean val = roledao.ScreenRedirect("update_jco_data_url", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list", changeinmedicleDao.Change_In_Medicle_JCO(Integer.parseInt(change_in_medicle_jco_id)));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Change_In_Medicle_Popup_JCOTiles");
	 }

}
