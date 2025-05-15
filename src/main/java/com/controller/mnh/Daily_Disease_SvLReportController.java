package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.dao.mnh.Daily_Disease_ReportDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Daily_Disease_SvLReportController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private Daily_Disease_ReportDAO dr;
	
	MNH_CommonController mcommon = new MNH_CommonController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();


	@RequestMapping(value = "/mnh_DR_dd_Surve", method = RequestMethod.GET)
	public ModelAndView mnh_DR_dd_Surve(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	     
		Boolean val = roledao.ScreenRedirect("mnh_DR_dd_Surve", session.getAttribute("roleid").toString());
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
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_5", mcommon.getMedDiseaseName("","", session));
		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		List<Map<String, Object>> list = dr.getsearch_Daily_desease_Report("","", "ALL", "",to_date, date1, "ALL", "ALL");
		Mmap.put("list",list);
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
		    sum=sum + (int) (long) list.get(i).get("count_diag");
		}
		Mmap.put("total",sum);
		return new ModelAndView("mnh_Daily_disease_reportTiles");
	}
	
	
	@RequestMapping(value = "/getsearch_Daily_desease_Report",method=RequestMethod.POST)
	public ModelAndView getsearch_Daily_desease_Report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "sus1", required = false) String sus1,
		@RequestParam(value = "cmd1", required = false) String cmd1,
		@RequestParam(value = "diag1", required = false) String diag1,
		@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
		@RequestParam(value = "to_dt1", required = false) String to_dt1,
		@RequestParam(value = "serv1", required = false) String serv1,
		@RequestParam(value = "cat1", required = false) String cat1,HttpServletRequest  request){
			
		Boolean val = roledao.ScreenRedirect("mnh_DR_dd_Surve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		

		List<Map<String, Object>> list = dr.getsearch_Daily_desease_Report(sus1,unit1, cmd1, diag1, frm_dt1, to_dt1,serv1, cat1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
	
		Mmap.put("list",list);
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
		    sum=sum + (int) (long) list.get(i).get("count_diag");
		}
		Mmap.put("total",sum);
		Mmap.put("getCommandList", all.getCommandDetailsList());
       //Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "", session));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_5", mcommon.getMedDiseaseName("","", session));
	 	Mmap.put("sus1", sus1);
	 	 Mmap.put("unit1", unit1);
	    Mmap.put("cmd1", cmd1);
	 	Mmap.put("diag1", diag1);
	 	Mmap.put("frm_dt1", frm_dt1);
	 	Mmap.put("to_dt1", to_dt1);
	 	Mmap.put("serv1", serv1);
	 	Mmap.put("cat1", cat1);
		
		return new ModelAndView("mnh_Daily_disease_reportTiles");
	}
}
