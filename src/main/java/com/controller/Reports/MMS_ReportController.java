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

import com.dao.Reports.MMS_ReportDAO;

import com.dao.login.RoleBaseMenuDAO;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class MMS_ReportController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	MMS_ReportDAO MmsReportDao;
	
	@RequestMapping(value = "/WAREHOUSE_REPORTUrl", method = RequestMethod.GET)
	public ModelAndView WAREHOUSE_REPORTUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("WAREHOUSE_REPORTUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_warehouse_reportTiles");
	}

	@RequestMapping(value = "/WAREHOUSE_REPORT", method = RequestMethod.POST)
	public ModelAndView WAREHOUSE_REPORT(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("WAREHOUSE_REPORTUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = MmsReportDao.WAREHOUSE_REPORT(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("mms_warehouse_reportTiles");
	}
	
	
	@RequestMapping(value = "/AUTHORIZED_ALL_INDIA_HOLDINGUrl", method = RequestMethod.GET)
	public ModelAndView AUTHORIZED_ALL_INDIA_HOLDINGUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORIZED_ALL_INDIA_HOLDINGUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_authorized_all_india_holding_reportTiles");
	}

	@RequestMapping(value = "/AUTHORIZED_ALL_INDIA_HOLDING", method = RequestMethod.POST)
	public ModelAndView AUTHORIZED_ALL_INDIA_HOLDING(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORIZED_ALL_INDIA_HOLDINGUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = MmsReportDao.ALL_INDIA_HOLDING(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("mms_authorized_all_india_holding_reportTiles");
	}
	
}
