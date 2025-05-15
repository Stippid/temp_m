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
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MVCRDAO;
import com.dao.tms.unit_holding_details_AvehDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})

public class A_Vehicale_unit_holding_detailsController 
{
	
	@Autowired
	private unit_holding_details_AvehDAO unitAvehDAO; 
	@Autowired
	
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/search_unit_holding_detailsList_Aveh", method = RequestMethod.GET)
	public ModelAndView search_unit_holding_details(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_unit_holding_detailsList_Aveh", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_unit_holding_details_AvehTile");
	}
	
	@RequestMapping(value = "/admin/search_unit_holding_detailsListt_Aveh", method = RequestMethod.POST)
	public ModelAndView search_unit_holding_detailsListt_Aveh(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name)
	{
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_unit_holding_detailsList_Aveh", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		
		if(!sus_no.equals("") & sus_no.length() == 8) {
			ArrayList<ArrayList<String>> list = unitAvehDAO.search_unit_holding_detailsListt_Aveh(sus_no);		 	
				int  sumUE = 0;
				int total_uh = 0;
				for(int i=0;i<list.size();i++) {
					String sumUE1 = list.get(i).get(2);
					if(sumUE1 == null) {
						sumUE1 = "0";
					}
					sumUE = sumUE + Integer.parseInt(sumUE1);
					total_uh = total_uh + Integer.parseInt(list.get(i).get(3));
				}
				Mmap.put("sumUE",sumUE);
				Mmap.put("total_uh",total_uh);
				Mmap.put("list", list);
				Mmap.put("sus_no", sus_no);
				Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,session).get(0));
		}	
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
		}
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:search_unit_holding_detailsList_Aveh");
		}
		
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:search_unit_holding_detailsList_Aveh");
		}
	 	return new ModelAndView("search_unit_holding_details_AvehTile");
	
	}
}