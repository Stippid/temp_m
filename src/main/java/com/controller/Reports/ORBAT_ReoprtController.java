package com.controller.Reports;

import java.util.ArrayList;

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
import com.dao.Reports.ORBAT_ReportDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ORBAT_ReoprtController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;

//private static final HashMap<String, Object> Mmap = null;
	
	@Autowired
	private ORBAT_ReportDAO reportDAO;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/search_conver_restruc_reorga_redes", method = RequestMethod.GET)
	public ModelAndView search_conver_restruc_reorga_redes(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_conver_restruc_reorga_redes", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("type_of_report1", "0");
		Mmap.put("ddlYears1", "0");
		Mmap.put("month1", "00");
		return new ModelAndView("search_conver_restruc_reorga_redesTiles");
	}
	
	@RequestMapping(value = "/search_conver_restruc_reorga_redes_actionlist1", method = RequestMethod.POST)
	public ModelAndView search_conver_restruc_reorga_redes_actionlist1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_of_report1", required = false) String report_type,
			@RequestParam(value = "ddlYears1", required = false) String ddlYears,
			@RequestParam(value = "month1", required = false) String month) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_conver_restruc_reorga_redes", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> list = reportDAO.search_conver_restruc_reorga_redes_actionlist(report_type,ddlYears,month);
		/*if(list.size() == 0)
		{
			//Mmap.put("msg", "Data Not Available.");
		}
		else
		{*/
			Mmap.put("list", list);
		//}
		Mmap.put("list.size()", list.size());
		Mmap.put("type_of_report1", report_type);
		Mmap.put("ddlYears1", ddlYears);
		Mmap.put("month1", month);
		return new ModelAndView("search_conver_restruc_reorga_redesTiles");
	}
	
	@RequestMapping(value = "/disbandmentReport", method = RequestMethod.GET)
	public ModelAndView disbandmentReport(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("disbandmentReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("type_of_report1", "0");
		Mmap.put("ddlYears1", "0");
		return new ModelAndView("disbandmentReportTiles");
	}
	
	@RequestMapping(value = "/disbandmentactionlist1",method = RequestMethod.POST)
	public ModelAndView disbandmentactionlist1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_of_report1", required = false) String report_type,
			@RequestParam(value = "ddlYears1", required = false) String ddlYears) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("disbandmentReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> list = reportDAO.disbandmentReportaction(report_type,ddlYears);
		/*if(list.size() == 0)
		{
			Mmap.put("msg", "Data Not Available.");
		}
		else
		{*/
			Mmap.put("list", list);
		//}
		Mmap.put("list.size()", list.size());
		Mmap.put("type_of_report1", report_type);
		Mmap.put("ddlYears1", ddlYears);
		
		return new ModelAndView("disbandmentReportTiles");
	}
	
	
	
	@RequestMapping(value = "/admin/movement_in_out_command_report", method = RequestMethod.GET)
	public ModelAndView movement_in_out_command_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("movement_in_out_command_report", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("type_of_report", "0");
		Mmap.put("duration", "0");
		return new ModelAndView("movement_in_out_command_reportTiles");
	}
	
	@RequestMapping(value = "/movement_in_out_command_reportAction1",method = RequestMethod.POST)
	public ModelAndView movement_in_out_command_reportAction1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_of_report", required = false) String report_type,
			@RequestParam(value = "from_date", required = false) String from_date,
			@RequestParam(value = "to_date", required = false) String to_date) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("movement_in_out_command_report", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		ArrayList<ArrayList<String>> list = reportDAO.search_movement_in_out_command_actionList(report_type,from_date,to_date);
		/*if(list.size() == 0)
		{
			Mmap.put("msg", "Data Not Available.");
		}
		else
		{*/
			Mmap.put("list", list);
		//}
		Mmap.put("list.size()", list.size());
		Mmap.put("type_of_report", report_type);
		Mmap.put("from_date", from_date);
		Mmap.put("to_date", to_date);
		
		return new ModelAndView("movement_in_out_command_reportTiles");
	}
}
