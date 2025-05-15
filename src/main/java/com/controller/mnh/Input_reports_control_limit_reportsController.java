package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_ReportsDAO;
import com.dao.mnh.mnh_input_controllimitDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class Input_reports_control_limit_reportsController {
	
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private MNH_ReportsDAO mnh7_Dao;
	 
	@Autowired
	private MNH_CommonController mcommon;
	
	@Autowired	
	private mnh_input_controllimitDAO cs;

	
	MNH_ValidationController validation = new MNH_ValidationController();
    AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	//REPORTS  IMB REPORT 
	
		@SuppressWarnings("deprecation")
		@RequestMapping(value = "/admin/mnh_report_control_limit", method = RequestMethod.GET)
		public ModelAndView mnh_report_control_limit(ModelMap Mmap,HttpSession session,
				HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			Date date = new Date();
	        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
	       
			Boolean val = roledao.ScreenRedirect("mnh_report_control_limit", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			date.setDate(1);
			date.setMonth(0);
			String startdate = new SimpleDateFormat("yyyy-MM").format(date);
			Mmap.put("from_month", startdate);
			date.setDate(1);
			date.setMonth(11);
			String enddate = new SimpleDateFormat("yyyy-MM").format(date);
			Mmap.put("to_month", enddate);
			
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			 
			Mmap.put("date", date1);
			Mmap.put("r_1", l1);
			Mmap.put("msg", msg);
			Mmap.put("getCommandList", all.getCommandDetailsList());

			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		    Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "",session)); 
		    Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
			Mmap.put("ml_6", mcommon.getDiseaseMMR("", "", session));
			Mmap.put("ml_7", mcommon.getDisease_type("", "", session));
			Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
			Mmap.put("ml_9", mcommon.getDisease_subtype("", "", session));
			return new ModelAndView("control_limit_reportsTiles");
		}
		
		
		
		
		
		@RequestMapping(value = "/getControlReport",method = RequestMethod.POST)
		public ModelAndView addLeaveUrl(ModelMap Mmap,HttpSession session,String from_month1, String to_month1,
				
				HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				String command1,String category1,String disease_type1,String disease_principal1,String disease_subtype1,String disease_mmr1,String block_description1) {
			
			Boolean val = roledao.ScreenRedirect("mnh_report_control_limit", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("getCommandList", all.getCommandDetailsList());
			//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		    Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "",session)); 
		    Mmap.put("ml_5", mcommon.getPrincipalCause("", "", session));
			Mmap.put("ml_6", mcommon.getDiseaseMMR("", "", session));
			Mmap.put("ml_7", mcommon.getDisease_type("", "", session));
			Mmap.put("ml_8", mcommon.getBlockDis("", "", session));
			Mmap.put("ml_9", mcommon.getDisease_subtype("", "", session));
			Mmap.put("list",cs.contol_limit_report(command1,category1,disease_type1,disease_principal1,disease_subtype1,disease_mmr1,block_description1));
			Mmap.put("list2",cs.contol_limit_report_cal(command1,category1,disease_type1,disease_principal1,disease_subtype1,disease_mmr1,block_description1));
			Mmap.put("command1",command1);
			Mmap.put("category1",category1);
			Mmap.put("disease_type1",disease_type1);
			Mmap.put("disease_principal1",disease_principal1);
			Mmap.put("disease_subtype1",disease_subtype1);
			Mmap.put("disease_mmr1",disease_mmr1);
			Mmap.put("block_description1",block_description1);
			
			if(from_month1=="" && to_month1=="")
			{
				Mmap.put("from_month",1);
				Mmap.put("to_month",12);
				Mmap.put("from_month_withyear",1);
				Mmap.put("to_month_withyear",12);
			}
			else {
			Mmap.put("from_month",from_month1.substring(5, 7));
			Mmap.put("to_month",to_month1.substring(5, 7));
			Mmap.put("from_month_withyear",from_month1);
			Mmap.put("to_month_withyear",to_month1);
			}
			
			return new ModelAndView("control_limit_reportsTiles");
		}
	
}