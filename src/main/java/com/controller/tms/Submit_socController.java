package com.controller.tms;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Tbl_CodesForm;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Submit_socController {

	
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/Submit_socUrl", method = RequestMethod.GET)
	public ModelAndView Submit_socUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Submit_socUrl", roleid);	
		/*if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			
		}*/
			Mmap.put("msg", msg);
		return new ModelAndView("submit_soctile");
	}
	
	
}
