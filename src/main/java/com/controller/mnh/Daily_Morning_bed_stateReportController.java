package com.controller.mnh;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Daily_Morning_bed_state_ReportDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.models.mnh.Tb_Med_Imb;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Daily_Morning_bed_stateReportController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private Daily_Morning_bed_state_ReportDAO bs;
	
	
	MNH_CommonController mcommon = new MNH_CommonController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/mnh_daily_bedstateDR", method = RequestMethod.GET)
	public ModelAndView mnh_daily_bedstateDR(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	      Boolean val = roledao.ScreenRedirect("mnh_daily_bedstateDR", session.getAttribute("roleid").toString());
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
		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		
		ArrayList<ArrayList<String>> list = bs.getsearch_morning_bed_Report("","","ALL",to_date, date1);
		Mmap.put("list",list);
		
		return new ModelAndView("Report_Daily_Morning_bed_stateTiles");
	}



	@RequestMapping(value = "/getsearch_morning_bed_Report",method=RequestMethod.POST)
	public ModelAndView getsearch_morning_bed_Report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "sus1", required = false) String sus1,
		@RequestParam(value = "cmd1", required = false) String cmd1,
		@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
		@RequestParam(value = "to_dt1", required = false) String to_dt1,HttpServletRequest request){
			
		Boolean val = roledao.ScreenRedirect("mnh_daily_bedstateDR", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 ArrayList<ArrayList<String>> list = bs.getsearch_morning_bed_Report(sus1,unit1, cmd1,frm_dt1, to_dt1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
	    //Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("sus1", sus1);
	 	Mmap.put("unit1", unit1);
	    Mmap.put("cmd1", cmd1);
	 	Mmap.put("frm_dt1", frm_dt1);
	 	Mmap.put("to_dt1", to_dt1);
	 	
		
		return new ModelAndView("Report_Daily_Morning_bed_stateTiles");
	}

///////////////////////----------view report morning bed state-------------///////////////////
	
	@RequestMapping(value = "/viewdailymorningbedstatedata",method=RequestMethod.POST)
	public ModelAndView viewdailymorningbedstatedata(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit)  {
		
		Boolean val = roledao.ScreenRedirect("mnh_daily_bedstateDR", sessionEdit.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }
		
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
	    String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy+"-01-01";
		model.put("date", date1);
		model.put("msg", msg);
		ArrayList<ArrayList<String>> list = bs.getview_morning_bed_Report(updateid);
		model.put("list",list);
		model.put("date", date1);
		model.put("to_date",to_date );
	
		return new ModelAndView("view_Daily_Morning_bed_stateTiles");
	}
	

	@RequestMapping(value = "/getview_morning_bed_Report",method=RequestMethod.POST)
	public ModelAndView getview_morning_bed_Report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "sus1", required = false) String sus1,
		@RequestParam(value = "cmd1", required = false) String cmd1,
		@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
		@RequestParam(value = "to_dt1", required = false) String to_dt1) throws SQLException{
			
		Boolean val = roledao.ScreenRedirect("viewdailymorningbedstatedata", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		 ArrayList<ArrayList<String>> list = bs.getview_morning_bed_Report(sus1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
	    Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("sus1", sus1);
	 	Mmap.put("unit1", unit1);
	    Mmap.put("cmd1", cmd1);
	 	Mmap.put("frm_dt1", frm_dt1);
	 	Mmap.put("to_dt1", to_dt1);
	 	
		
		return new ModelAndView("view_Daily_Morning_bed_stateTiles");
	}

	
///////////////////////----------End-------------///////////////////
}
