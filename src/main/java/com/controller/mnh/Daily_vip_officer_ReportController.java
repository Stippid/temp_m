package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.controller.mnh.MNH_CommonController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.Daily_vip_officer_ReportDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Daily_vip_officer_ReportController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private Daily_vip_officer_ReportDAO vf;
	
	MNH_CommonController mcommon = new MNH_CommonController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	
	@RequestMapping(value = "/mnh_DR_adm1report", method = RequestMethod.GET)
	public ModelAndView mnh_DR_adm1report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	      Boolean val = roledao.ScreenRedirect("mnh_DR_adm1report", session.getAttribute("roleid").toString());
          if(val == false) {
                  return new ModelAndView("AccessTiles");
          }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy+"-01-01";
		Mmap.put("getCommandList", all.getCommandDetailsList());

		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		List<Map<String, Object>> list = vf.getsearch_Daily_vip_Report("","","ALL",to_date, date1, "ALL");
		Mmap.put("list",list);
		
		return new ModelAndView("Report_Daily_vip_officerTiles");
	}
	
	
	@RequestMapping(value = "/getsearch_Daily_vip_Report",method=RequestMethod.POST)
	public ModelAndView getsearch_Daily_vip_Report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "sus1", required = false) String sus1,
		@RequestParam(value = "cmd1", required = false) String cmd1,
		@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
		@RequestParam(value = "serv1", required = false) String serv1,
		@RequestParam(value = "to_dt1", required = false) String to_dt1,HttpServletRequest request){
			
		Boolean val = roledao.ScreenRedirect("mnh_DR_adm1report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = vf.getsearch_Daily_vip_Report(sus1,unit1, cmd1,frm_dt1, to_dt1,serv1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
	    //Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("sus1", sus1);
	 	Mmap.put("unit1", unit1);
	    Mmap.put("cmd1", cmd1);
	 	Mmap.put("frm_dt1", frm_dt1);
	 	Mmap.put("to_dt1", to_dt1);
	 	Mmap.put("serv1", serv1);
	 	Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		return new ModelAndView("Report_Daily_vip_officerTiles");
	}
	
}
