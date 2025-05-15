package com.controller.orbat;

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
import com.dao.orbat.NMBReportDAO;
import com.dao.orbat.ReliefDAO;
import com.dao.orbat.ReliefDAOImpl;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class NMB_Rellief_ReportController {
	
	
	@Autowired
	private NMBReportDAO t ;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public ReliefDAO rdao = new ReliefDAOImpl();
	
	@RequestMapping(value="/admin/nmb_reliefReport", method = RequestMethod.GET)
	public ModelAndView approved_sd_reliefReport(ModelMap model,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("nmb_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		return new ModelAndView("nmb_report_reliefTile");
	}
	
	
	@RequestMapping(value="/admin/nmb_reliefReport1", method = RequestMethod.POST)
	public ModelAndView approved_sd_reliefReport1(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "nmb_date1", required = false) String period_from,
			@RequestParam(value = "nmb_date12", required = false) String period_to,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "auth_letter1", required = false) String  auth_letter,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("nmb_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		
		model.put("roleType", roleType);
		
		if(status.equals("")){
			model.put("msg", "Please Select * mendetory Fields!");
			return new ModelAndView("redirect:nmb_reliefReport");
		}else{

			model.put("getReliefReportList", t.getReliefReportList(period_from,period_to,status));
			model.put("nmb_date1",period_from);
			model.put("nmb_date12",period_to);
			model.put("status1",status);
		}
		return new ModelAndView("nmb_report_reliefTile");
	}

}
