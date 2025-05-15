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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.mnh.MNH_CommonController;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Reports_MNHController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private MNH_ReportsDAO mnh7_Dao;
	 
	@Autowired
	private MNH_CommonController mcommon;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	
	
	
	
	//REPORTS MODULE (5)-> (SURVEILLANCE DETAILS SCREEN START)
	@RequestMapping(value = "/admin/mnh_surveillance_details ", method = RequestMethod.GET)
	public ModelAndView mnh_surveillance_details(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_surveillance_details", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	       
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_surveillance_detailsTiles");
	}				
	//REPORTS MODULE (5)-> (SURVEILLANCE DETAILS SCREEN END)
	
	
	
		

	//REPORTS MODULE -> (DAILY USER REPORT  SCREEN START)
		
		@RequestMapping(value = "/admin/daily_user_report_url", method = RequestMethod.GET)
		public ModelAndView daily_user_report_url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Date date = new Date();
	        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
           
			Boolean val = roledao.ScreenRedirect("daily_user_report_url", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		       
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("date", date1);
			Mmap.put("r_1", l1);
			Mmap.put("ml_1",mcommon.getMNHUserList("", session));
			Mmap.put("msg", msg);
			Mmap.put("date", date1);
			return new ModelAndView("daily_user_reportTiles");
		}	
		
		@RequestMapping(value = "/Daily_user_rept", method = RequestMethod.POST)
		public ModelAndView Daily_user_rept(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "username1", required = false) String username1,String from_dt1,String to_dt1,String unit1,String sus1){
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			Date date = new Date();
	        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		 	
			
		 	ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			 	
			 if(validation.RankCodeLength(to_dt1)  == false){
			 		Mmap.put("msg",validation.to_dateMSG);
		             return new ModelAndView("redirect:daily_user_report_url");
		         }
			
			Mmap.put("r_1", l1);
			Mmap.put("ml_1",mcommon.getMNHUserList("", session));
		 	Mmap.put("username1", username1);		 	
		 	Mmap.put("unit1", unit1);
		 	Mmap.put("sus1", sus1);
		 	Mmap.put("from_dt1", from_dt1);
		 	Mmap.put("to_dt1", to_dt1);	
		 	Mmap.put("date", date1);
		 	List<Map<String, Object>> list = mnh7_Dao.getdailyuserreport(username1,from_dt1,to_dt1,unit1,sus1); 
		 	Mmap.put("ml_1",mcommon.getMNHUserList("", session));
			Mmap.put("list", list);
			Mmap.put("size", list.size());
			
			return new ModelAndView("daily_user_reportTiles");    
		}
		
		//REPORTS MODULE -> (DAILY USER REPORT  SCREEN END)
	
}
