package com.controller.avation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.controller.validation.ValidationController;
import com.dao.avation.DACRDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.abc_vehicleDetailsDAO;
import com.dao.tms.vehicleDetailsDAO;
import com.models.TB_AVIATION_DAILY_BASIS;
import com.models.TB_AVIATION_DAILY_BASIS_HISTORY;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.Tbl_CodesForm;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class SearchDacrConroller {

	@Autowired
	private DACRDAO dcrDAO; 
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	

	
	 
	
	@Autowired
	private vehicleDetailsDAO vehiclestatusDAO; 

	
	ReportsController rcont = new ReportsController();
	
	@Autowired
	private abc_vehicleDetailsDAO abc_vehiclestatusDAO; 
	
	@RequestMapping(value = "/admin/search_dacr", method = RequestMethod.GET)
	public ModelAndView search_dacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	/*	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_dacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("Unit")){
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",allOrbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		}
		if(request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_dacrTile");
	}
	
	@RequestMapping(value = "/admin/SearchAttributeReportDacr", method = RequestMethod.POST)
	public ModelAndView SearchAttributeReportDacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noSer", required = false) String sus_no,
			@RequestParam(value = "statusSer", required = false) String status,
			@RequestParam(value = "unit_nameSer", required = false) String unit_name,
			@RequestParam(value = "issue_dateSer", required = false) String issue_date)
	{
		
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_dacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(status != "") {
			Mmap.put("status", status);
		}
		if(!sus_no.equals("") & sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			
			if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size()>0)
			{
				Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			}
			
			
			ArrayList<List<String>> list =	dcrDAO.getSearchAttributeReportDacr(status,sus_no,roleType,issue_date);	
			if(list.size() == 0)
			{
				Mmap.put("list", list);
			}
			else
			{
				Mmap.put("roleType", roleType);
				Mmap.put("list", list);
				Mmap.put("unit_name12", list.get(0).get(1));
				Mmap.put("issue_date12", list.get(0).get(3));
				Mmap.put("ap_date12", list.get(0).get(2));
				System.err.println( list.get(0).get(3));
			}
			
		}
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
	    } 
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:searchdacr");
		}	
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:searchdacr");
		}
		return new ModelAndView("search_dacrTile");
	}
	
	@RequestMapping(value = "/admin/ViewDACRUrl",method = RequestMethod.POST)
	public ModelAndView ViewDACRUrl(@ModelAttribute("sus_noV") String sus_no,String unit_nameV, @ModelAttribute("issue_date1") String issue_date,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameV.equals("")) {
			model.put("unit_name", unit_nameV);
		}
		
		if(!issue_date.equals("")) {
			model.put("issue_date", issue_date);
		}
		System.err.println("ason_dateV--"+issue_date);
		ArrayList<List<String>> getDACRReportList = dcrDAO.getDACRReportListForApproval1(sus_no, roleType, issue_date);
		ArrayList<List<String>> getDACRReportMRList = dcrDAO.getDACRReportMRList(sus_no, roleType, issue_date);
		 if (!getDACRReportList.isEmpty()) {
		        String ason = "";
		        
		        
		        for (List<String> record : getDACRReportList) {
		          
		            String asonDate = record.get(21);

		           
		            if (asonDate != null && !asonDate.isEmpty()) {
		            	ason = asonDate;
		            }
		        }
		        model.put("asonDate", ason);
		    }
		 model.put("getDACRReporMRtList", getDACRReportMRList);
		model.put("getDACRReportList", getDACRReportList);
		return new ModelAndView("view_search_dacrTile");
	}
	

	@RequestMapping(value = "/admin/SeenDACRUrl",method = RequestMethod.POST)
	public ModelAndView SeenDACRUrl(@ModelAttribute("sus_noM") String sus_no, @ModelAttribute("issue_dateM") String issue_date, @ModelAttribute("ason_dateM") String ason_date,String unit_nameM,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameM.equals("")) {
			model.put("unit_name", unit_nameM);
		}
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		if(!ason_date.equals("")) {
			model.put("ap_date12", ason_date);
			model.addAttribute("ap_date12", ason_date);
			
			Date asonDate = null;
			String timeZone = "Asia/Kolkata";
			
			 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			 
			   Date asonDate1 = null;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(asonDate); 
				
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			    Date validUpto = new Date(calendar.getTimeInMillis());
				 asonDate1 = validUpto;
				 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
				 model.put("ap_date", date1);
					model.addAttribute("ap_date", date1);
				 System.err.println("dateM--"+date1); 
			
		}
		
		List<Map<String, Object>> formation = dcrDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
		}
		List<String> wepe = dcrDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", dcrDAO.getwepeno(sus_no).get(0));
		}
		
		System.err.println("ason"+ason_date);
		ArrayList<List<String>> getDACRReportList = dcrDAO.getDACRReportListForApproval(sus_no, roleType,issue_date);
		ArrayList<List<String>> getDACRReportMRList1 = dcrDAO.getDACRReportMRListForApproval(sus_no, roleType, issue_date);
		ArrayList<List<String>> getDACRReportOTHERList1 = dcrDAO.getDACRReportOTHERListForApproval(sus_no, roleType, issue_date);
		
	    if (!getDACRReportList.isEmpty()) {
	        String sumDay = "0";
	        String sumNight = "0";
	        String sumDN = "0";
	        String sumM = "0";
	        String sumQ = "0";
	        String sumHY = "0";
	        String sumY = "0";
	        String ason = "";
	        
	        
	        for (List<String> record : getDACRReportList) {
	            String dayHours = record.get(22);
	            String nightHours = record.get(23);
	            String MHours = record.get(24);
	            String QHours = record.get(25);
	            String HYHours = record.get(26);
	            String YHours = record.get(27);
	            String DNHours = record.get(28);
	            String asonDate = record.get(29);

	          
	            if (dayHours != null && !dayHours.isEmpty()) {
	                sumDay = dayHours;
	            }
	            if (nightHours != null && !nightHours.isEmpty()) {
	                sumNight = nightHours;
	            }
	            if (MHours != null && !MHours.isEmpty()) {
	                sumM = MHours;
	            }
	            if (QHours != null && !QHours.isEmpty()) {
	                sumQ = QHours;
	            }
	            if (HYHours != null && !HYHours.isEmpty()) {
	                sumHY = HYHours;
	            }
	            if (YHours != null && !YHours.isEmpty()) {
	                sumY = YHours;
	            }
	            if (DNHours != null && !DNHours.isEmpty()) {
	                sumDN = DNHours;
	            }
	            if (asonDate != null && !asonDate.isEmpty()) {
	            	ason = asonDate;
	            }
	        }
	        
	        model.put("sumDay", sumDay);
	        model.put("sumNight", sumNight);
	        model.put("sumM", sumM);
	        model.put("sumQ", sumQ);
	        model.put("sumHY", sumHY);
	        model.put("sumY", sumY);
	        model.put("sumDN", sumDN);
	        model.put("asonDate", ason);
	    }
	    
		model.put("getDACRReportList", getDACRReportList);
		model.put("getDACRReportOTHERList1", getDACRReportOTHERList1);
		model.put("getDACRReportMRList1", getDACRReportMRList1);
		return new ModelAndView("seen_search_dacrTile");
	}
	
	
	@RequestMapping(value = "/admin/ApproveddacrUrl",method = RequestMethod.POST)
	public ModelAndView ApproveddacrUrl(@ModelAttribute("sus_no2") String sus_no,@ModelAttribute("unit_name1") String unit_name,@ModelAttribute("issue_date1") String issue_date,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
        if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", dcrDAO.setApproveddacr(sus_no,username,issue_date));	
		return new ModelAndView("search_dacrTile");
	}
	
	@RequestMapping(value = "/admin/rejectdacrUrl",method = RequestMethod.POST)
	public ModelAndView rejectdacrUrl(@ModelAttribute("sus_no3") String sus_no,@ModelAttribute("unit_name1") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", dcrDAO.setRejectdacr(sus_no,username));	
		return new ModelAndView("search_dacrTile");
	}
	
	//NEW ADDED BY MITESH(04-12-2024)
	
	@RequestMapping(value = "/admin/search_chtldacr", method = RequestMethod.GET)
	public ModelAndView search_chtldacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	/*	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_chtldacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("Unit")){
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",allOrbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		}
		if(request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_chtldacrTile");
		
}
	
	@RequestMapping(value = "/admin/SearchChtlReportDacr", method = RequestMethod.POST)
	public ModelAndView SearchChtlReportDacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noSer", required = false) String sus_no,
			@RequestParam(value = "statusSer", required = false) String status,
			@RequestParam(value = "unit_nameSer", required = false) String unit_name,
			@RequestParam(value = "issue_dateSer", required = false) String issue_date)
	{
		
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_chtldacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(status != "") {
			Mmap.put("status", status);
		}
		if(!sus_no.equals("") & sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			
			if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size()>0)
			{
				Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			}
			
			
			ArrayList<List<String>> list =	dcrDAO.getSearchChtlReportDacr(status,sus_no,roleType,issue_date);	
			if(list.size() == 0)
			{
				Mmap.put("list", list);
			}
			else
			{
				Mmap.put("roleType", roleType);
				Mmap.put("list", list);
				Mmap.put("unit_name12", list.get(0).get(1));
				Mmap.put("issue_date12", list.get(0).get(3));
				Mmap.put("ap_date12",list.get(0).get(2));
				System.err.println( list.get(0).get(2));
			}
			
		}	
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
	    } 
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:search_chtldacr");
		}	
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:search_chtldacr");
		}
		return new ModelAndView("search_chtldacrTile");
	}
	
	@RequestMapping(value = "/admin/ViewchtlDACRUrl",method = RequestMethod.POST)
	public ModelAndView ViewchtlDACRUrl(@ModelAttribute("sus_noV") String sus_no,String unit_nameV, @ModelAttribute("issue_date1") String issue_date,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameV.equals("")) {
			model.put("unit_name", unit_nameV);
		}
		if(!issue_date.equals("")) {
			model.put("issue_date", issue_date);
		}
		ArrayList<List<String>> getDACRReportList = dcrDAO.getChtlDACRReportListForApproval1(sus_no, roleType,issue_date);
		if (!getDACRReportList.isEmpty()) {
	        String ason = "";
	        
	        
	        for (List<String> record : getDACRReportList) {
	          
	            String asonDate = record.get(20);

	            
	            if (asonDate != null && !asonDate.isEmpty()) {
	            	ason = asonDate;
	            }
	        }
	        model.put("asonDate", ason);
	    }
		model.put("getDACRReportList", getDACRReportList);
		return new ModelAndView("view_chtl_dacrTile");
		
}
	
	@RequestMapping(value = "/admin/Approvedchtldacr",method = RequestMethod.POST)
	public ModelAndView ApprovedchtldacrUrl(@ModelAttribute("sus_no2") String sus_no,@ModelAttribute("unit_name1") String unit_name, @ModelAttribute("issue_date1") String issue_date,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
        if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		System.err.println("issue_date--"+issue_date);
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", dcrDAO.setApprovedchtldacr(sus_no,username,issue_date));	
		return new ModelAndView("search_chtldacrTile");
	}
	
	@RequestMapping(value = "/admin/rejectchtldacr",method = RequestMethod.POST)
	public ModelAndView rejectchtldacrUrl(@ModelAttribute("sus_no3") String sus_no,@ModelAttribute("unit_name1") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", dcrDAO.setRejectchtldacr(sus_no,username));	
		return new ModelAndView("search_chtldacrTile");
	}
	
	@RequestMapping(value = "/admin/SeenchtlDACRUrl",method = RequestMethod.POST)
	public ModelAndView SeenchtlDACRUrl(@ModelAttribute("sus_noM") String sus_no,@ModelAttribute("issue_dateM") String issue_date, @ModelAttribute("ason_dateM") String ason_date,String unit_nameM,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
//		String  roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameM.equals("")) {
			model.put("unit_name", unit_nameM);
		}
		if(!issue_date.equals("")) {
			model.put("issue_date12", issue_date);
			model.addAttribute("issue_date12", issue_date);
		}
		if(!ason_date.equals("")) {
			model.put("ap_date12", ason_date);
			model.addAttribute("ap_date12", ason_date);
			
			Date asonDate = null;
			String timeZone = "Asia/Kolkata";
			
			 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			 
			   Date asonDate1 = null;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(asonDate); 
				
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			    Date validUpto = new Date(calendar.getTimeInMillis());
				 asonDate1 = validUpto;
				 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
				 model.put("ap_date", date1);
					model.addAttribute("ap_date", date1);
					
		}
		
		List<Map<String, Object>> formation = dcrDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
		}
		List<String> wepe = dcrDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", dcrDAO.getwepeno(sus_no).get(0));
		}
		
		System.err.println(issue_date);
		ArrayList<List<String>> getDACRReportList = dcrDAO.getchtlDACRReportListForApproval(sus_no, roleType,issue_date);
		  
	    if (!getDACRReportList.isEmpty()) {
	        String sumDay = "0";
	        String sumNight = "0";
	        String sumDN = "0";
	        String sumM = "0";
	        String sumQ = "0";
	        String sumHY = "0";
	        String sumY = "0";
	        
	        
	        for (List<String> record : getDACRReportList) {
	            String dayHours = record.get(21);
	            String nightHours = record.get(22);
	            String MHours = record.get(23);
	            String QHours = record.get(24);
	            String HYHours = record.get(25);
	            String YHours = record.get(26);
	            String DNHours = record.get(27);

	          
	            if (dayHours != null && !dayHours.isEmpty()) {
	                sumDay = dayHours;
	            }
	            if (nightHours != null && !nightHours.isEmpty()) {
	                sumNight = nightHours;
	            }
	            if (MHours != null && !MHours.isEmpty()) {
	                sumM = MHours;
	            }
	            if (QHours != null && !QHours.isEmpty()) {
	                sumQ = QHours;
	            }
	            if (HYHours != null && !HYHours.isEmpty()) {
	                sumHY = HYHours;
	            }
	            if (YHours != null && !YHours.isEmpty()) {
	                sumY = YHours;
	            }
	            if (DNHours != null && !DNHours.isEmpty()) {
	                sumDN = DNHours;
	            }
	        }
	        
	        model.put("sumDay", sumDay);
	        model.put("sumNight", sumNight);
	        model.put("sumM", sumM);
	        model.put("sumQ", sumQ);
	        model.put("sumHY", sumHY);
	        model.put("sumY", sumY);
	        model.put("sumDN", sumDN);
	    }
		
		model.put("getDACRReportList", getDACRReportList);
		return new ModelAndView("seen_chtl_dacrTile");
	}
	
	
	//NEW ADDED BY MITESH RPAS(16-12-2024)
	
		@RequestMapping(value = "/admin/search_rpasdacr", method = RequestMethod.GET)
		public ModelAndView search_rpasdacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		/*	String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_rpasdacr", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			if(roleAccess.equals("Unit")){
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",allOrbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
			}
			if(request.getHeader("Referer") == null){
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("search_rpasdacrTile");
			
	}
		
		@RequestMapping(value = "/admin/SearchRpasReportDacr", method = RequestMethod.POST)
		public ModelAndView SearchRpasReportDacr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "sus_noSer", required = false) String sus_no,
				@RequestParam(value = "statusSer", required = false) String status,
				@RequestParam(value = "unit_nameSer", required = false) String unit_name,
				@RequestParam(value = "issue_dateSer", required = false) String issue_date)
		{
			
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_rpasdacr", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			if(status != "") {
				Mmap.put("status", status);
			}
			if(!sus_no.equals("") & sus_no.length() == 8) {
				Mmap.put("sus_no", sus_no);
				
				if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size()>0)
				{
					Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
				}
				
				
				ArrayList<List<String>> list =	dcrDAO.getSearchRpasReportDacr(status,sus_no,roleType,issue_date);	
				if(list.size() == 0)
				{
					Mmap.put("list", list);
				}
				else
				{
					Mmap.put("roleType", roleType);
					Mmap.put("list", list);
					Mmap.put("unit_name12", list.get(0).get(1));
					Mmap.put("issue_date12", list.get(0).get(3));
					Mmap.put("ap_date12", list.get(0).get(2));
					
				}
				
			}	
			else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
				Mmap.put("msg", "Please Select SUS No.");
		    } 
			else if(validation.sus_noLength(sus_no) == false){
				Mmap.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:search_rpasdacr");
			}	
			if(validation.checkUnit_nameLength(unit_name) == false){
				Mmap.put("msg",validation.unit_nameMSG);
				return new ModelAndView("redirect:search_rpasdacr");
			}
			return new ModelAndView("search_rpasdacrTile");
		}
		
		@RequestMapping(value = "/admin/ViewrpasDACRUrl",method = RequestMethod.POST)
		public ModelAndView ViewrpasDACRUrl(@ModelAttribute("sus_noV") String sus_no,String unit_nameV, @ModelAttribute("issue_date1") String issue_date, ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
		
//			String  roleid = sessionA.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			String roleType = sessionA.getAttribute("roleType").toString();
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			if(!sus_no.equals("")) {
				model.put("sus_no", sus_no);
			}
			if(!unit_nameV.equals("")) {
				model.put("unit_name", unit_nameV);
			}
			if(!issue_date.equals("")) {
				model.put("issue_date", issue_date);
			}
			ArrayList<List<String>> getDACRReportList = dcrDAO.getRpasDACRReportListForApproval1(sus_no, roleType, issue_date);
			
			if (!getDACRReportList.isEmpty()) {
		        String ason = "";
		        
		        
		        for (List<String> record : getDACRReportList) {
		          
		            String asonDate = record.get(20);

		            
		            if (asonDate != null && !asonDate.isEmpty()) {
		            	ason = asonDate;
		            }
		        }
		        model.put("asonDate", ason);
		    }
			model.put("getDACRReportList", getDACRReportList);
			return new ModelAndView("view_rpas_dacrTile");
			
	}
		
		@RequestMapping(value = "/admin/Approvedrpasdacr",method = RequestMethod.POST)
		public ModelAndView ApprovedrpasdacrUrl(@ModelAttribute("sus_no2") String sus_no,@ModelAttribute("unit_name1") String unit_name, @ModelAttribute("issue_date1") String issue_date,ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			/*String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
	        if(!roleType.equals("APP") & !roleType.equals("ALL")) {
	                return new ModelAndView("AccessTiles");
	        }
			model.put("sus_no", sus_no);
			model.put("unit_name", unit_name);
			if(!issue_date.equals("")) {
				model.put("issue_date12", issue_date);
				model.addAttribute("issue_date12", issue_date);
			}
			String  username = sessionA.getAttribute("username").toString();
			model.put("msg", dcrDAO.setApprovedrpasdacr(sus_no,username,issue_date));	
			return new ModelAndView("search_rpasdacrTile");
		}
		
		@RequestMapping(value = "/admin/rejectrpasdacr",method = RequestMethod.POST)
		public ModelAndView rejectrpasdacrUrl(@ModelAttribute("sus_no3") String sus_no,@ModelAttribute("unit_name1") String unit_name,ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			/*String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("APP") & !roleType.equals("ALL")) {
	                return new ModelAndView("AccessTiles");
	        }
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			model.put("sus_no", sus_no);
			model.put("unit_name", unit_name);
			String  username = sessionA.getAttribute("username").toString();
			model.put("msg", dcrDAO.setRejectrpasdacr(sus_no,username));	
			return new ModelAndView("search_rpasdacrTile");
		}
		
		@RequestMapping(value = "/admin/SeenrpasDACRUrl",method = RequestMethod.POST)
		public ModelAndView SeenrpasDACRUrl(@ModelAttribute("sus_noM") String sus_no,@ModelAttribute("issue_dateM") String issue_date, @ModelAttribute("ason_dateM") String ason_date, String unit_nameM,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
		
//			String  roleid = sessionA.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("searchdacr", roleid);	
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			if(!sus_no.equals("")) {
				model.put("sus_no", sus_no);
			}
			if(!unit_nameM.equals("")) {
				model.put("unit_name", unit_nameM);
			}
			if(!issue_date.equals("")) {
				model.put("issue_date12", issue_date);
				model.addAttribute("issue_date12", issue_date);
			}
			if(!ason_date.equals("")) {
				model.put("ap_date12", ason_date);
				model.addAttribute("ap_date12", ason_date);
				
				Date asonDate = null;
				String timeZone = "Asia/Kolkata";
				
				 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
				 
				   Date asonDate1 = null;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(asonDate); 
					
					calendar.add(Calendar.DAY_OF_YEAR, 1);
				    Date validUpto = new Date(calendar.getTimeInMillis());
					 asonDate1 = validUpto;
					 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
					 model.put("ap_date", date1);
						model.addAttribute("ap_date", date1);
					 System.err.println("dateM--"+date1); 	
			}
			
			List<Map<String, Object>> formation = dcrDAO.getFormationDetailsFromSusNo(sus_no);
			if(formation.size() > 0) {
				model.put("unit_name",formation.get(0).get("unit_name"));
				model.put("modification",formation.get(0).get("mod"));
				model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
				model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			}
			List<String> wepe = dcrDAO.getwepeno(sus_no);
			if(wepe.size() != 0) {
				model.put("wep", dcrDAO.getwepeno(sus_no).get(0));
			}
		
			System.err.println(issue_date);
			ArrayList<List<String>> getDACRReportList = dcrDAO.getrpasDACRReportListForApproval(sus_no, roleType,issue_date);
			 
		    if (!getDACRReportList.isEmpty()) {
		        String sumDay = "0";
		        String sumNight = "0";
		        String sumDN = "0";
		        String sumM = "0";
		        String sumQ = "0";
		        String sumHY = "0";
		        String sumY = "0";
		        
		        
		        for (List<String> record : getDACRReportList) {
		            String dayHours = record.get(21);
		            String nightHours = record.get(22);
		            String MHours = record.get(23);
		            String QHours = record.get(24);
		            String HYHours = record.get(25);
		            String YHours = record.get(26);
		            String DNHours = record.get(27);

		           
		            if (dayHours != null && !dayHours.isEmpty()) {
		                sumDay = dayHours;
		            }
		            if (nightHours != null && !nightHours.isEmpty()) {
		                sumNight = nightHours;
		            }
		            if (MHours != null && !MHours.isEmpty()) {
		                sumM = MHours;
		            }
		            if (QHours != null && !QHours.isEmpty()) {
		                sumQ = QHours;
		            }
		            if (HYHours != null && !HYHours.isEmpty()) {
		                sumHY = HYHours;
		            }
		            if (YHours != null && !YHours.isEmpty()) {
		                sumY = YHours;
		            }
		            if (DNHours != null && !DNHours.isEmpty()) {
		                sumDN = DNHours;
		            }
		        }
		        
		        model.put("sumDay", sumDay);
		        model.put("sumNight", sumNight);
		        model.put("sumM", sumM);
		        model.put("sumQ", sumQ);
		        model.put("sumHY", sumHY);
		        model.put("sumY", sumY);
		        model.put("sumDN", sumDN);
		    }
			
			
			model.put("getDACRReportList", getDACRReportList);
			return new ModelAndView("seen_rpas_dacrTile");
		}
		
		
		 private Date parseDateWithTimeZone(String dateString, String format, String timeZone) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone)); 
	                return dateFormat.parse(dateString); 
	            } catch (ParseException e) {
	                e.printStackTrace();
	                return null; 
	            }
	        }
		
		 //Added by Mitesh 10-01-2025
		 @RequestMapping(value = "/admin/getdetails_ue_uhdacr", method = RequestMethod.POST)
			public ModelAndView getdetails_ue_uhdacr(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "sus_nob", required = false) String sus_no,
				@RequestParam(value = "unit_nameb", required = false) String unit_name,
				@RequestParam(value = "issue_date2", required = false) String issue_date,
				@RequestParam(value = "ason_date2", required = false) String ason_date,
				HttpServletRequest request){
				String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
					sus_no = roleSusNo;
				}
				
				String roleType = session.getAttribute("roleType").toString();
				Mmap.put("roleType", roleType);
				Mmap.put("sus_no", sus_no);
				if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,session).size()>0)
				{
					Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
				}
				if(!issue_date.equals("")) {
					Mmap.put("issue_date12", issue_date);
					Mmap.addAttribute("issue_date12", issue_date);
					System.err.println("issue1--"+issue_date);
				}
				if(!ason_date.equals("")) {
					Mmap.put("ap_date12", ason_date);
					Mmap.addAttribute("ap_date12", ason_date);
					
					Date asonDate = null;
					String timeZone = "Asia/Kolkata";
					
					 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
					 
					   Date asonDate1 = null;
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(asonDate); 
						
						calendar.add(Calendar.DAY_OF_YEAR, 1);
					    Date validUpto = new Date(calendar.getTimeInMillis());
						 asonDate1 = validUpto;
						 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
						 Mmap.put("ap_date", date1);
						 Mmap.addAttribute("ap_date", date1);
						 System.err.println("dateM--"+date1); 	
				}
				System.err.println("date1"+issue_date);
				System.err.println("date2"+ason_date);
				if(!sus_no.equals("")) {
					ArrayList<ArrayList<String>> getdetails_ue_uhdacr =	dcrDAO.getdetails_ue_uhDacrlist(sus_no,issue_date);
					Mmap.put("getdetails_ue_uhdacr", getdetails_ue_uhdacr);
					int totalUE = 0;
					int totalSUH = 0;
					int totalIUH = 0;
					int totalRIUH = 0;
					int totalREUH = 0;
					int totalAOGUH = 0;
					int totalACCIUH = 0;
					int totalOHUH = 0;
					int totalUH = 0;
					int totalDEFI = 0;
					int totalSUR = 0;
					
					
					for(int i=0;i<getdetails_ue_uhdacr.size();i++) {
						String totalUE1= getdetails_ue_uhdacr.get(i).get(2);
						if(totalUE1 == null || totalUE1.equals(null)) {
							totalUE =0;
						}
						String totalSUH1= getdetails_ue_uhdacr.get(i).get(3);
						if(totalSUH1 == null || totalSUH1.equals(null)) {
							totalSUH =0;
						}
						String totalIUH1= getdetails_ue_uhdacr.get(i).get(4);
						if(totalIUH1 == null || totalIUH1.equals(null)) {
							totalIUH =0;
						}
						String totalRIUH1= getdetails_ue_uhdacr.get(i).get(5);
						if(totalRIUH1 == null || totalRIUH1.equals(null)) {
							totalRIUH =0;
						}
						String totalREUH1= getdetails_ue_uhdacr.get(i).get(6);
						if(totalREUH1 == null || totalREUH1.equals(null)) {
							totalREUH =0;
						}
						String totalAOGUH1= getdetails_ue_uhdacr.get(i).get(7);
						if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
							totalAOGUH =0;
						}
						String totalACCIUH1= getdetails_ue_uhdacr.get(i).get(8);
						if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
							totalACCIUH =0;
						}
						String totalOHUH1= getdetails_ue_uhdacr.get(i).get(9);
						if(totalOHUH1 == null || totalOHUH1.equals(null)) {
							totalOHUH =0;
						}
						String totalUH1= getdetails_ue_uhdacr.get(i).get(10);
						if(totalUH1 == null || totalUH1.equals(null)) {
							totalUH =0;
						}
						
						String totalSUR1= getdetails_ue_uhdacr.get(i).get(11);
						if(totalSUR1 == null || totalSUR1.equals(null)) {
							totalSUR =0;
						}
						String totalDEFI1= getdetails_ue_uhdacr.get(i).get(12);
						if(totalDEFI1 == null || totalDEFI1.equals(null)) {
							totalDEFI =0;
						}
						
						
					
						totalUE = totalUE + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(2));
						totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(3));
						totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(4));
						totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(5));
						totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(6));
						totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(7));
						totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(8));
						totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(9));
						totalUH = totalUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(10));
						totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(11));
						totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(12));
						
					}
					
					
					Mmap.put("totalUE",totalUE);
					Mmap.put("totalSUH",totalSUH);
					Mmap.put("totalIUH",totalIUH);
					Mmap.put("totalRIUH",totalRIUH);
					Mmap.put("totalREUH",totalREUH);
					Mmap.put("totalAOGUH",totalAOGUH);
					Mmap.put("totalACCIUH",totalACCIUH);
					Mmap.put("totalOHUH",totalOHUH);
					Mmap.put("totalUH",totalUH);
					Mmap.put("totalSUR",totalSUR);
					Mmap.put("totalDEFI",totalDEFI);
				}
				List<String> wepe = dcrDAO.getwepeno(sus_no);
				if(wepe.size() != 0) {
					Mmap.put("we_pe_no", dcrDAO.getwepeno(sus_no).get(0));
				}
				return new ModelAndView("DACR_partB_UE_Tile");
			}
		 
		 //Added by mitesh 15-01-2025
		 @RequestMapping(value = "/admin/getdetails_ue_uhchtl", method = RequestMethod.POST)
			public ModelAndView getdetails_ue_uhchtl(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "sus_nob", required = false) String sus_no,
				@RequestParam(value = "unit_nameb", required = false) String unit_name,
				@RequestParam(value = "issue_date2", required = false) String issue_date,
				@RequestParam(value = "ason_date2", required = false) String ason_date,
				HttpServletRequest request){
				String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
					sus_no = roleSusNo;
				}
				
				String roleType = session.getAttribute("roleType").toString();
				Mmap.put("roleType", roleType);
				Mmap.put("sus_no", sus_no);
				if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,session).size()>0)
				{
					Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
				}
				if(!issue_date.equals("")) {
					Mmap.put("issue_date12", issue_date);
					Mmap.addAttribute("issue_date12", issue_date);
				}
				if(!ason_date.equals("")) {
					Mmap.put("ap_date12", ason_date);
					Mmap.addAttribute("ap_date12", ason_date);
					
					Date asonDate = null;
					String timeZone = "Asia/Kolkata";
					
					 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
					 
					   Date asonDate1 = null;
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(asonDate); 
						
						calendar.add(Calendar.DAY_OF_YEAR, 1);
					    Date validUpto = new Date(calendar.getTimeInMillis());
						 asonDate1 = validUpto;
						 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
						 Mmap.put("ap_date", date1);
						 Mmap.addAttribute("ap_date", date1);
						 System.err.println("dateM--"+date1); 	
				}
				System.err.println("date1"+issue_date);
				System.err.println("date2"+ason_date);
				if(!sus_no.equals("")) {
					ArrayList<ArrayList<String>> getdetails_ue_uhchtldacr =	dcrDAO.getdetails_ue_uhchtlDacrlist(sus_no,issue_date);
					Mmap.put("getdetails_ue_uhchtldacr", getdetails_ue_uhchtldacr);
					
					int totalUE = 0;
					int totalSUH = 0;
					int totalIUH = 0;
					int totalRIUH = 0;
					int totalREUH = 0;
					int totalAOGUH = 0;
					int totalACCIUH = 0;
					int totalOHUH = 0;
					int totalUH = 0;
					int totalDEFI = 0;
					int totalSUR = 0;
					
				
						for(int i=0;i<getdetails_ue_uhchtldacr.size();i++) {
							String totalUE1= getdetails_ue_uhchtldacr.get(i).get(2);
							if(totalUE1 == null || totalUE1.equals(null)) {
								totalUE =0;
							}
							String totalSUH1= getdetails_ue_uhchtldacr.get(i).get(3);
							if(totalSUH1 == null || totalSUH1.equals(null)) {
								totalSUH =0;
							}
							String totalIUH1= getdetails_ue_uhchtldacr.get(i).get(4);
							if(totalIUH1 == null || totalIUH1.equals(null)) {
								totalIUH =0;
							}
							String totalRIUH1= getdetails_ue_uhchtldacr.get(i).get(5);
							if(totalRIUH1 == null || totalRIUH1.equals(null)) {
								totalRIUH =0;
							}
							String totalREUH1= getdetails_ue_uhchtldacr.get(i).get(6);
							if(totalREUH1 == null || totalREUH1.equals(null)) {
								totalREUH =0;
							}
							String totalAOGUH1= getdetails_ue_uhchtldacr.get(i).get(7);
							if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
								totalAOGUH =0;
							}
							String totalACCIUH1= getdetails_ue_uhchtldacr.get(i).get(8);
							if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
								totalACCIUH =0;
							}
							String totalOHUH1= getdetails_ue_uhchtldacr.get(i).get(9);
							if(totalOHUH1 == null || totalOHUH1.equals(null)) {
								totalOHUH =0;
							}
							String totalUH1= getdetails_ue_uhchtldacr.get(i).get(10);
							if(totalUH1 == null || totalUH1.equals(null)) {
								totalUH =0;
							}
							
							String totalSUR1= getdetails_ue_uhchtldacr.get(i).get(11);
							if(totalSUR1 == null || totalSUR1.equals(null)) {
								totalSUR =0;
							}
							String totalDEFI1= getdetails_ue_uhchtldacr.get(i).get(12);
							if(totalDEFI1 == null || totalDEFI1.equals(null)) {
								totalDEFI =0;
							}
							
							
						
							totalUE = totalUE + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(2));
							totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(3));
							totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(4));
							totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(5));
							totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(6));
							totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(7));
							totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(8));
							totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(9));
							totalUH = totalUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(10));
							totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(11));
							totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(12));	
						}
				
					
					Mmap.put("totalUE",totalUE);
					Mmap.put("totalSUH",totalSUH);
					Mmap.put("totalIUH",totalIUH);
					Mmap.put("totalRIUH",totalRIUH);
					Mmap.put("totalREUH",totalREUH);
					Mmap.put("totalAOGUH",totalAOGUH);
					Mmap.put("totalACCIUH",totalACCIUH);
					Mmap.put("totalOHUH",totalOHUH);
					Mmap.put("totalUH",totalUH);
					Mmap.put("totalSUR",totalSUR);
					Mmap.put("totalDEFI",totalDEFI);
				}
				List<String> wepe = dcrDAO.getwepeno(sus_no);
				if(wepe.size() != 0) {
					Mmap.put("we_pe_no", dcrDAO.getwepeno(sus_no).get(0));
				}
				return new ModelAndView("CHTL_partB_UE_Tile");
			}
			

		 @RequestMapping(value = "/admin/getdetails_ue_uhrpas", method = RequestMethod.POST)
			public ModelAndView getdetails_ue_uhrpas(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "sus_nob", required = false) String sus_no,
				@RequestParam(value = "unit_nameb", required = false) String unit_name,
				@RequestParam(value = "issue_date2", required = false) String issue_date,
				@RequestParam(value = "ason_date2", required = false) String ason_date,
				HttpServletRequest request){
				String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
					sus_no = roleSusNo;
				}
				
				String roleType = session.getAttribute("roleType").toString();
				Mmap.put("roleType", roleType);
				Mmap.put("sus_no", sus_no);
				if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,session).size()>0)
				{
					Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
				}
				if(!issue_date.equals("")) {
					Mmap.put("issue_date12", issue_date);
					Mmap.addAttribute("issue_date12", issue_date);
				}
				if(!ason_date.equals("")) {
					Mmap.put("ap_date12", ason_date);
					Mmap.addAttribute("ap_date12", ason_date);
					
					Date asonDate = null;
					String timeZone = "Asia/Kolkata";
					
					 asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
					 
					   Date asonDate1 = null;
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(asonDate); 
						
						calendar.add(Calendar.DAY_OF_YEAR, 1);
					    Date validUpto = new Date(calendar.getTimeInMillis());
						 asonDate1 = validUpto;
						 String date1 = new SimpleDateFormat("dd-MM-yyyy").format(asonDate1);
						 Mmap.put("ap_date", date1);
						 Mmap.addAttribute("ap_date", date1);
						 System.err.println("dateM--"+date1); 	
				}
				
				if(!sus_no.equals("")) {
					ArrayList<ArrayList<String>> getdetails_ue_uhrpasdacr =	dcrDAO.getdetails_ue_uhrpasDacrlist(sus_no,issue_date);
					Mmap.put("getdetails_ue_uhrpasdacr", getdetails_ue_uhrpasdacr);
					int totalUE = 0;
					int totalSUH = 0;
					int totalIUH = 0;
					int totalRIUH = 0;
					int totalREUH = 0;
					int totalAOGUH = 0;
					int totalACCIUH = 0;
					int totalOHUH = 0;
					int totalUH = 0;
					int totalDEFI = 0;
					int totalSUR = 0;
					
				
					for(int i=0;i<getdetails_ue_uhrpasdacr.size();i++) {
						String totalUE1= getdetails_ue_uhrpasdacr.get(i).get(2);
						if(totalUE1 == null || totalUE1.equals(null)) {
							totalUE =0;
						}
						String totalSUH1= getdetails_ue_uhrpasdacr.get(i).get(3);
						if(totalSUH1 == null || totalSUH1.equals(null)) {
							totalSUH =0;
						}
						String totalIUH1= getdetails_ue_uhrpasdacr.get(i).get(4);
						if(totalIUH1 == null || totalIUH1.equals(null)) {
							totalIUH =0;
						}
						String totalRIUH1= getdetails_ue_uhrpasdacr.get(i).get(5);
						if(totalRIUH1 == null || totalRIUH1.equals(null)) {
							totalRIUH =0;
						}
						String totalREUH1= getdetails_ue_uhrpasdacr.get(i).get(6);
						if(totalREUH1 == null || totalREUH1.equals(null)) {
							totalREUH =0;
						}
						String totalAOGUH1= getdetails_ue_uhrpasdacr.get(i).get(7);
						if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
							totalAOGUH =0;
						}
						String totalACCIUH1= getdetails_ue_uhrpasdacr.get(i).get(8);
						if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
							totalACCIUH =0;
						}
						String totalOHUH1= getdetails_ue_uhrpasdacr.get(i).get(9);
						if(totalOHUH1 == null || totalOHUH1.equals(null)) {
							totalOHUH =0;
						}
						String totalUH1= getdetails_ue_uhrpasdacr.get(i).get(10);
						if(totalUH1 == null || totalUH1.equals(null)) {
							totalUH =0;
						}
						
						String totalSUR1= getdetails_ue_uhrpasdacr.get(i).get(11);
						if(totalSUR1 == null || totalSUR1.equals(null)) {
							totalSUR =0;
						}
						String totalDEFI1= getdetails_ue_uhrpasdacr.get(i).get(12);
						if(totalDEFI1 == null || totalDEFI1.equals(null)) {
							totalDEFI =0;
						}
						
						
					
						totalUE = totalUE + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(2));
						totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(3));
						totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(4));
						totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(5));
						totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(6));
						totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(7));
						totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(8));
						totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(9));
						totalUH = totalUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(10));
						totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(11));
						totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(12));	
					}
					
					Mmap.put("totalUE",totalUE);
					Mmap.put("totalSUH",totalSUH);
					Mmap.put("totalIUH",totalIUH);
					Mmap.put("totalRIUH",totalRIUH);
					Mmap.put("totalREUH",totalREUH);
					Mmap.put("totalAOGUH",totalAOGUH);
					Mmap.put("totalACCIUH",totalACCIUH);
					Mmap.put("totalOHUH",totalOHUH);
					Mmap.put("totalUH",totalUH);
					Mmap.put("totalSUR",totalSUR);
					Mmap.put("totalDEFI",totalDEFI);
				}
				List<String> wepe = dcrDAO.getwepeno(sus_no);
				if(wepe.size() != 0) {
					Mmap.put("we_pe_no", dcrDAO.getwepeno(sus_no).get(0));
				}
				return new ModelAndView("RPAS_partB_UE_Tile");
			}
			
			
		 @RequestMapping(value = "/generate_tail_no", method = RequestMethod.POST)
			public @ResponseBody List<String> gettail_noFromStatus(String sus_no,String ason_date1,String issue_states,HttpSession sessionA) {
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Unit")){
					sus_no = roleSusNo;
				}
				
				return dcrDAO.gettail_noFromStatus(sus_no,ason_date1,issue_states);
			}
		 
		 @RequestMapping(value = "/generate_chtltail_no", method = RequestMethod.POST)
			public @ResponseBody List<String> getchtlTail_noFromStatus(String sus_no,String asonDate,String issue_type,HttpSession sessionA) {
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Unit")){
					sus_no = roleSusNo;
				}
				return dcrDAO.getchtltail_no(sus_no,asonDate,issue_type);
			}
		 
		 @RequestMapping(value = "/generate_rpastail_no", method = RequestMethod.POST)
			public @ResponseBody List<String> getrpasTail_noFromStatus(String sus_no,String asonDate,String issue_type,HttpSession sessionA) {
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Unit")){
					sus_no = roleSusNo;
				}
				return dcrDAO.getrpastail_no(sus_no,asonDate,issue_type);
			}
		 
		 int type_veh2 = 0;
			String prf_code2 = "";
			String sus_no12= "";
			String unit_name12="";
			String cont_comd12 = "";
			String cont_corps12 = "";
			String cont_div12 ="";
			String cont_bde12="";
			String line_dte_sus12 = "";
			String mct_main_list12 = "";
			String status2 = "";
			String ason_date12 = "";
			 int kms2 = 0;
				int vintage2 = 0;

		 
		 @RequestMapping(value = "/admin/tailno_wise_detailsforRW", method = RequestMethod.GET)
		    public ModelAndView tailno_wise_detailsforRW(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		            String  roleid = session.getAttribute("roleid").toString();
		            /*Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte", roleid);        
		            if(val == false) {
		                    return new ModelAndView("AccessTiles");
		            }*/
		            String roleAccess = session.getAttribute("roleAccess").toString();
		            String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		            String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		            String roleSusNo = session.getAttribute("roleSusNo").toString();
		            if(request.getHeader("Referer") == null ) {
		                    msg = "";
		            }
		           
		            if(roleAccess.equals("Formation")){
		                    if(roleSubAccess.equals("Command")){
		                            String formation_code = String.valueOf(roleFormationNo.charAt(0));
		                            cont_comd12 = formation_code;
		                            Mmap.put("cont_comd1",cont_comd12);
		                            
		                            List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);
		                            Mmap.put("getCommandList",comd);
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectcorps",select);
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Corps")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            cont_corps12 = cor;
		                            Mmap.put("cont_corps1",cont_corps12);
		                            
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
		                            Mmap.put("getDivList",Bn);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Division")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            cont_div12 = div;
		                            Mmap.put("cont_div1",cont_div12);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectBde",select);
		                            
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Brigade")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            cont_bde12 = roleFormationNo;
		                            Mmap.put("cont_bde1",cont_bde12);
		                            
		                            String bde_code = roleFormationNo;
		                            List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectLine_dte",select);
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }
		            }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("selectLine_dte",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else if(roleAccess.equals("Line_dte")){
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else {
		                    return new ModelAndView("AccessTiles");
		            }
		            
		            String formCode ="";
		            if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
		                    formCode = cont_bde12;
		    }else {
		            if(!cont_div12.equals("0") && !cont_div12.equals("")){
		                    formCode = cont_div12;
		                }else {
		                        if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		                                formCode = cont_corps12;
		                        }else {
		                                if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		                                        formCode = cont_comd12;
		                                }
		                        }
		                }
		        }
		            if(msg != null && msg.equals("ok")){
		                    Mmap.put("type_veh", type_veh2);
		                    Mmap.put("prf_code", prf_code2);
		                    Mmap.put("sus_no1",sus_no12);
		                    Mmap.put("unit_name1",unit_name12);
		                    Mmap.put("cont_comd1",cont_comd12);
		                    Mmap.put("cont_corps1",cont_corps12);
		                   
		                    Mmap.put("cont_div1",cont_div12);
		                    Mmap.put("cont_bde1",cont_bde12);
		                    Mmap.put("line_dte_sus1",line_dte_sus12);
		                    Mmap.put("mct_main_list1",mct_main_list12);
		                    Mmap.put("status",status2);
		                    Mmap.put("ason_date", ason_date12);
		            }else{
		                    type_veh2 = 0;
		                    prf_code2 = "";
		                   
		                    sus_no12= "";
		                    unit_name12="";
		                    cont_comd12 = "";
		                    cont_corps12 = "";
		                    
		                    cont_div12 ="";
		                    cont_bde12="";
		                    line_dte_sus12 = "";
		                    mct_main_list12 = "";
		                    status2="";
		                    ason_date12 = "";
		                    
		                  
		            }
		            if(msg != null && msg.equals("ok")) {
		                    msg = "";
		            }
		            Mmap.put("msg", msg);        
		            
		            ArrayList<ArrayList<String>>  list = null;
		            ArrayList<ArrayList<String>>  list1 = null;
		              if(type_veh2 == 0) {  //RW 
		               list = dcrDAO.avn_assetstatusDetails_linedte(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,status2,ason_date12); 
		               Mmap.put("list_A", list); 
		               list1 = dcrDAO.avn_assetstatusDetails_linedtechtl(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,status2,ason_date12); 
		               Mmap.put("list_B", list1); 
		              }
		            
		            return new ModelAndView("tailno_wise_detailsforRWTile");
		    }
		    
		@RequestMapping(value = "/admin/tailno_wise_status_detailsforRW", method = RequestMethod.POST)
		    public ModelAndView tailno_wise_status_detailsforRW(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
		                    @RequestParam(value = "type_veh1", required = false) int type_of_aircraft,
		                    @RequestParam(value = "prf_code1", required = false) String abc_code,
		                    @RequestParam(value = "sus_no1", required = false) String sus_no1,
		                    @RequestParam(value = "unit_name1", required = false) String unit_name1,
		                    @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
		                    @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
		                    @RequestParam(value = "cont_div1", required = false) String cont_div1,
		                    @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
		                    @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
		                    @RequestParam(value = "mct_main_list1", required = false) String act_main_list1,
		        			@RequestParam(value = "type_of_force1", required = false) String status1,
		        			@RequestParam(value = "issue_date1", required = false) String ason_date1)
		    
		    
		    
		    {        
		            type_veh2 = type_of_aircraft;
		            System.out.println("hello type of vehicle   "+ type_of_aircraft);
		            prf_code2 = abc_code ;
		            sus_no12= sus_no1;
		            unit_name12= unit_name1;
		            cont_comd12 = cont_comd1;
		            cont_corps12 = cont_corps1;
		            cont_div12 = cont_div1;
		            cont_bde12= cont_bde1;
		            line_dte_sus12 = line_dte_sus1;
		            mct_main_list12 = act_main_list1;
		            status2 = status1;
		            ason_date12 = ason_date1;
		            System.out.println("asonDate"+ason_date1);
		            System.out.println("abc  "+ abc_code);
		            Mmap.put("msg", "ok");
		            
		             return new ModelAndView("redirect:tailno_wise_detailsforRW");
		    }
		
		@RequestMapping(value = "/getTptSummaryInABCList", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getTptSummaryInABCList(HttpSession sessionA,
				@RequestParam int type) {
			List<Map<String, Object>> list = dcrDAO.getTptSummaryInABCList(type,sessionA);
			return list;
		}
		
		@RequestMapping(value = "/getactMain_from_abc_code", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getACTMain_from_Type_of_aircraft(HttpSession sessionA,
				@RequestParam String abc_code) {
			if(!abc_code.equals("")) {
				return dcrDAO.getactMain_from_Type_of_aircraft(abc_code,sessionA);
			}else {
				return null;
			}
		}
		
		
		 @RequestMapping(value = "/admin/tailno_wise_detailsforRPAS", method = RequestMethod.GET)
		    public ModelAndView tailno_wise_detailsforRPAS(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		            String  roleid = session.getAttribute("roleid").toString();
		            /*Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte", roleid);        
		            if(val == false) {
		                    return new ModelAndView("AccessTiles");
		            }*/
		            String roleAccess = session.getAttribute("roleAccess").toString();
		            String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		            String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		            String roleSusNo = session.getAttribute("roleSusNo").toString();
		            if(request.getHeader("Referer") == null ) {
		                    msg = "";
		            }
		           
		            if(roleAccess.equals("Formation")){
		                    if(roleSubAccess.equals("Command")){
		                            String formation_code = String.valueOf(roleFormationNo.charAt(0));
		                            cont_comd12 = formation_code;
		                            Mmap.put("cont_comd1",cont_comd12);
		                            
		                            List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);
		                            Mmap.put("getCommandList",comd);
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectcorps",select);
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Corps")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            cont_corps12 = cor;
		                            Mmap.put("cont_corps1",cont_corps12);
		                            
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
		                            Mmap.put("getDivList",Bn);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Division")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            cont_div12 = div;
		                            Mmap.put("cont_div1",cont_div12);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectBde",select);
		                            
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Brigade")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            cont_bde12 = roleFormationNo;
		                            Mmap.put("cont_bde1",cont_bde12);
		                            
		                            String bde_code = roleFormationNo;
		                            List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectLine_dte",select);
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }
		            }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("selectLine_dte",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else if(roleAccess.equals("Line_dte")){
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else {
		                    return new ModelAndView("AccessTiles");
		            }
		            
		            String formCode ="";
		            if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
		                    formCode = cont_bde12;
		    }else {
		            if(!cont_div12.equals("0") && !cont_div12.equals("")){
		                    formCode = cont_div12;
		                }else {
		                        if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		                                formCode = cont_corps12;
		                        }else {
		                                if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		                                        formCode = cont_comd12;
		                                }
		                        }
		                }
		        }
		            if(msg != null && msg.equals("ok")){
		                    Mmap.put("type_veh", type_veh2);
		                    Mmap.put("prf_code", prf_code2);
		                    Mmap.put("sus_no1",sus_no12);
		                    Mmap.put("unit_name1",unit_name12);
		                    Mmap.put("cont_comd1",cont_comd12);
		                    Mmap.put("cont_corps1",cont_corps12);
		                   
		                    Mmap.put("cont_div1",cont_div12);
		                    Mmap.put("cont_bde1",cont_bde12);
		                    Mmap.put("line_dte_sus1",line_dte_sus12);
		                    Mmap.put("mct_main_list1",mct_main_list12);
		                    Mmap.put("status",status2);
		                    Mmap.put("ason_date", ason_date12);
		            }else{
		                    type_veh2 = 0;
		                    prf_code2 = "";
		                   
		                    sus_no12= "";
		                    unit_name12="";
		                    cont_comd12 = "";
		                    cont_corps12 = "";
		                    
		                    cont_div12 ="";
		                    cont_bde12="";
		                    line_dte_sus12 = "";
		                    mct_main_list12 = "";
		                    status2="";
		                    ason_date12 = "";
		                    
		                  
		            }
		            if(msg != null && msg.equals("ok")) {
		                    msg = "";
		            }
		            Mmap.put("msg", msg);        
		            
		            ArrayList<ArrayList<String>>  list = null;
		            ArrayList<ArrayList<String>>  list1 = null;
		              if(type_veh2 == 1) {  //RW 
		               list = dcrDAO.avn_assetstatusDetails_linedterpas(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,status2,ason_date12); 
		               Mmap.put("list_C", list); 
		               
		              }
		            
		            return new ModelAndView("tailno_wise_detailsforRPASTile");
		    }
		    
		@RequestMapping(value = "/admin/tailno_wise_status_detailsforRPAS", method = RequestMethod.POST)
		    public ModelAndView tailno_wise_status_detailsforRPAS(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
		                    @RequestParam(value = "type_veh1", required = false) int type_of_aircraft,
		                    @RequestParam(value = "prf_code1", required = false) String abc_code,
		                    @RequestParam(value = "sus_no1", required = false) String sus_no1,
		                    @RequestParam(value = "unit_name1", required = false) String unit_name1,
		                    @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
		                    @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
		                    @RequestParam(value = "cont_div1", required = false) String cont_div1,
		                    @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
		                    @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
		                    @RequestParam(value = "mct_main_list1", required = false) String act_main_list1,
		        			@RequestParam(value = "type_of_force1", required = false) String status1,
		        			@RequestParam(value = "issue_date1", required = false) String ason_date1)
		    
		    
		    
		    {        
		            type_veh2 = type_of_aircraft;
		            System.out.println("hello type of vehicle   "+ type_of_aircraft);
		            prf_code2 = abc_code ;
		            sus_no12= sus_no1;
		            unit_name12= unit_name1;
		            cont_comd12 = cont_comd1;
		            cont_corps12 = cont_corps1;
		            cont_div12 = cont_div1;
		            cont_bde12= cont_bde1;
		            line_dte_sus12 = line_dte_sus1;
		            mct_main_list12 = act_main_list1;
		            status2 = status1;
		            ason_date12 = ason_date1;
		            System.out.println("asonDate"+ason_date1);
		            System.out.println("abc  "+ abc_code);
		            Mmap.put("msg", "ok");
		            
		             return new ModelAndView("redirect:tailno_wise_detailsforRPAS");
		    }
		
		@RequestMapping(value = "/getactMain_from_abc_codeRPAS", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getACTMain_from_Type_of_aircraftRPAS(HttpSession sessionA,
				@RequestParam String abc_code) {
			if(!abc_code.equals("")) {
				return dcrDAO.getactMain_from_Type_of_aircraftRPAS(abc_code,sessionA);
			}else {
				return null;
			}
		}
		
		 @RequestMapping(value = "/admin/unit_wise_detailsforRW", method = RequestMethod.GET)
		    public ModelAndView unit_wise_detailsforRW(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		            String  roleid = session.getAttribute("roleid").toString();
		            /*Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte", roleid);        
		            if(val == false) {
		                    return new ModelAndView("AccessTiles");
		            }*/
		            String roleAccess = session.getAttribute("roleAccess").toString();
		            String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		            String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		            String roleSusNo = session.getAttribute("roleSusNo").toString();
		            if(request.getHeader("Referer") == null ) {
		                    msg = "";
		            }
		            ////////////////////
		            if(roleAccess.equals("Formation")){
		                    if(roleSubAccess.equals("Command")){
		                            String formation_code = String.valueOf(roleFormationNo.charAt(0));
		                            cont_comd12 = formation_code;
		                            Mmap.put("cont_comd1",cont_comd12);
		                            
		                            List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);
		                            Mmap.put("getCommandList",comd);
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectcorps",select);
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Corps")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            cont_corps12 = cor;
		                            Mmap.put("cont_corps1",cont_corps12);
		                            
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
		                            Mmap.put("getDivList",Bn);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectDiv",select);
		                            Mmap.put("selectBde",select);
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Division")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            cont_div12 = div;
		                            Mmap.put("cont_div1",cont_div12);
		                            
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectBde",select);
		                            
		                            Mmap.put("selectLine_dte",select);
		                            
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }if(roleSubAccess.equals("Brigade")){
		                            String command = String.valueOf(roleFormationNo.charAt(0));
		                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
		                            Mmap.put("getCommandList",comd);
		                            
		                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
		                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
		                            Mmap.put("getCorpsList",corps);
		                            
		                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
		                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
		                            Mmap.put("getDivList",Bn);
		                            
		                            cont_bde12 = roleFormationNo;
		                            Mmap.put("cont_bde1",cont_bde12);
		                            
		                            String bde_code = roleFormationNo;
		                            List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
		                            Mmap.put("getBdeList",bde);
		                            
		                            String select="<option value='0'>--Select--</option>";
		                            Mmap.put("selectLine_dte",select);
		                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		                    }
		            }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("selectLine_dte",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else if(roleAccess.equals("Line_dte")){
		                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
		                    Mmap.put("getCommandList",comd);
		                    String selectComd ="<option value=''>--Select--</option>";
		                    String select="<option value='0'>--Select--</option>";
		                    Mmap.put("selectcomd", selectComd);
		                    Mmap.put("selectcorps",select);
		                    Mmap.put("selectDiv",select);
		                    Mmap.put("selectBde",select);
		                    Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
		            }else {
		                    return new ModelAndView("AccessTiles");
		            }
		            
		            String formCode ="";
		            if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
		                    formCode = cont_bde12;
		    }else {
		            if(!cont_div12.equals("0") && !cont_div12.equals("")){
		                    formCode = cont_div12;
		                }else {
		                        if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		                                formCode = cont_corps12;
		                        }else {
		                                if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		                                        formCode = cont_comd12;
		                                }
		                        }
		                }
		        }
		            if(msg != null && msg.equals("ok")){
		                    Mmap.put("type_veh", type_veh2);
		                    Mmap.put("prf_code", prf_code2);
		                    Mmap.put("sus_no1",sus_no12);
		                    Mmap.put("unit_name1",unit_name12);
		                    Mmap.put("cont_comd1",cont_comd12);
		                    Mmap.put("cont_corps1",cont_corps12);
		                   
		                    Mmap.put("cont_div1",cont_div12);
		                    Mmap.put("cont_bde1",cont_bde12);
		                    Mmap.put("line_dte_sus1",line_dte_sus12);
		                    Mmap.put("mct_main_list1",mct_main_list12);
		                    Mmap.put("status",status2);
		                    Mmap.put("ason_date", ason_date12);
		            }else{
		                    type_veh2 = 0;
		                    prf_code2 = "";
		                   
		                    sus_no12= "";
		                    unit_name12="";
		                    cont_comd12 = "";
		                    cont_corps12 = "";
		                    
		                    cont_div12 ="";
		                    cont_bde12="";
		                    line_dte_sus12 = "";
		                    mct_main_list12 = "";
		                    status2="";
		                    ason_date12 = "";
		                    
		                  
		            }
		            if(msg != null && msg.equals("ok")) {
		                    msg = "";
		            }
		            Mmap.put("msg", msg);        
		            
		            ArrayList<ArrayList<String>> getdetails_ue_uhdacr  = null;
		            ArrayList<ArrayList<String>>  getdetails_ue_uhchtldacr = null;
		              if(type_veh2 == 0) {  //RW 
		            	  getdetails_ue_uhdacr = dcrDAO.avn_assetstatus_linedte(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,ason_date12); 
		               Mmap.put("getdetails_ue_uhdacr", getdetails_ue_uhdacr);
		               getdetails_ue_uhchtldacr = dcrDAO.avn_assetstatus_linedtechtl(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,ason_date12); 
		               Mmap.put("getdetails_ue_uhchtldacr", getdetails_ue_uhchtldacr);
						int totalUE = 0;
						int totalSUH = 0;
						int totalIUH = 0;
						int totalRIUH = 0;
						int totalREUH = 0;
						int totalAOGUH = 0;
						int totalACCIUH = 0;
						int totalOHUH = 0;
						int totalUH = 0;
						int totalDEFI = 0;
						int totalSUR = 0;
						
						if(getdetails_ue_uhdacr.size()>0) {
						for(int i=0;i<getdetails_ue_uhdacr.size();i++) {
							String totalUE1= getdetails_ue_uhdacr.get(i).get(9);
							if(totalUE1 == null || totalUE1.equals(null)) {
								totalUE =0;
							}
							String totalSUH1= getdetails_ue_uhdacr.get(i).get(11);
							if(totalSUH1 == null || totalSUH1.equals(null)) {
								totalSUH =0;
							}
							String totalIUH1= getdetails_ue_uhdacr.get(i).get(12);
							if(totalIUH1 == null || totalIUH1.equals(null)) {
								totalIUH =0;
							}
							String totalRIUH1= getdetails_ue_uhdacr.get(i).get(13);
							if(totalRIUH1 == null || totalRIUH1.equals(null)) {
								totalRIUH =0;
							}
							String totalREUH1= getdetails_ue_uhdacr.get(i).get(14);
							if(totalREUH1 == null || totalREUH1.equals(null)) {
								totalREUH =0;
							}
							String totalAOGUH1= getdetails_ue_uhdacr.get(i).get(15);
							if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
								totalAOGUH =0;
							}
							String totalACCIUH1= getdetails_ue_uhdacr.get(i).get(16);
							if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
								totalACCIUH =0;
							}
							String totalOHUH1= getdetails_ue_uhdacr.get(i).get(17);
							if(totalOHUH1 == null || totalOHUH1.equals(null)) {
								totalOHUH =0;
							}
							String totalUH1= getdetails_ue_uhdacr.get(i).get(10);
							if(totalUH1 == null || totalUH1.equals(null)) {
								totalUH =0;
							}
							
							String totalSUR1= getdetails_ue_uhdacr.get(i).get(18);
							if(totalSUR1 == null || totalSUR1.equals(null)) {
								totalSUR =0;
							}
							String totalDEFI1= getdetails_ue_uhdacr.get(i).get(19);
							if(totalDEFI1 == null || totalDEFI1.equals(null)) {
								totalDEFI =0;
							}
							
							
						
							totalUE = totalUE + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(9));
							totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(11));
							totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(12));
							totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(13));
							totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(14));
							totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(15));
							totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(16));
							totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(17));
							totalUH = totalUH + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(10));
							totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(18));
							totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhdacr.get(i).get(19));
							
						}
						}
						
						if(getdetails_ue_uhchtldacr.size()>0) {
							for(int i=0;i<getdetails_ue_uhchtldacr.size();i++) {
								String totalUE1= getdetails_ue_uhchtldacr.get(i).get(9);
								if(totalUE1 == null || totalUE1.equals(null)) {
									totalUE =0;
								}
								String totalSUH1= getdetails_ue_uhchtldacr.get(i).get(11);
								if(totalSUH1 == null || totalSUH1.equals(null)) {
									totalSUH =0;
								}
								String totalIUH1= getdetails_ue_uhchtldacr.get(i).get(12);
								if(totalIUH1 == null || totalIUH1.equals(null)) {
									totalIUH =0;
								}
								String totalRIUH1= getdetails_ue_uhchtldacr.get(i).get(13);
								if(totalRIUH1 == null || totalRIUH1.equals(null)) {
									totalRIUH =0;
								}
								String totalREUH1= getdetails_ue_uhchtldacr.get(i).get(14);
								if(totalREUH1 == null || totalREUH1.equals(null)) {
									totalREUH =0;
								}
								String totalAOGUH1= getdetails_ue_uhchtldacr.get(i).get(15);
								if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
									totalAOGUH =0;
								}
								String totalACCIUH1= getdetails_ue_uhchtldacr.get(i).get(16);
								if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
									totalACCIUH =0;
								}
								String totalOHUH1= getdetails_ue_uhchtldacr.get(i).get(17);
								if(totalOHUH1 == null || totalOHUH1.equals(null)) {
									totalOHUH =0;
								}
								String totalUH1= getdetails_ue_uhchtldacr.get(i).get(10);
								if(totalUH1 == null || totalUH1.equals(null)) {
									totalUH =0;
								}
								
								String totalSUR1= getdetails_ue_uhchtldacr.get(i).get(18);
								if(totalSUR1 == null || totalSUR1.equals(null)) {
									totalSUR =0;
								}
								String totalDEFI1= getdetails_ue_uhchtldacr.get(i).get(19);
								if(totalDEFI1 == null || totalDEFI1.equals(null)) {
									totalDEFI =0;
								}
								
								
							
								totalUE = totalUE + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(9));
								totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(11));
								totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(12));
								totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(13));
								totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(14));
								totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(15));
								totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(16));
								totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(17));
								totalUH = totalUH + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(10));
								totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(18));
								totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhchtldacr.get(i).get(19));
								
							}
						}
						
						
						Mmap.put("totalUE",totalUE);
						Mmap.put("totalSUH",totalSUH);
						Mmap.put("totalIUH",totalIUH);
						Mmap.put("totalRIUH",totalRIUH);
						Mmap.put("totalREUH",totalREUH);
						Mmap.put("totalAOGUH",totalAOGUH);
						Mmap.put("totalACCIUH",totalACCIUH);
						Mmap.put("totalOHUH",totalOHUH);
						Mmap.put("totalUH",totalUH);
						Mmap.put("totalSUR",totalSUR);
						Mmap.put("totalDEFI",totalDEFI);
		              }
		            
		            return new ModelAndView("unit_wise_detailsforRWTile");
		    }
		    
		@RequestMapping(value = "/admin/unit_wise_status_detailsforRW", method = RequestMethod.POST)
		    public ModelAndView unit_wise_status_detailsforRW(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
		                    @RequestParam(value = "type_veh1", required = false) int type_of_aircraft,
		                    @RequestParam(value = "prf_code1", required = false) String abc_code,
		                    @RequestParam(value = "sus_no1", required = false) String sus_no1,
		                    @RequestParam(value = "unit_name1", required = false) String unit_name1,
		                    @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
		                    @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
		                    @RequestParam(value = "cont_div1", required = false) String cont_div1,
		                    @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
		                    @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
		                    @RequestParam(value = "mct_main_list1", required = false) String act_main_list1,
		        			@RequestParam(value = "type_of_force1", required = false) String status1,
		        			@RequestParam(value = "issue_date1", required = false) String ason_date1)
		    
		    
		    
		    {        
		            type_veh2 = type_of_aircraft;
		            System.out.println("hello type of vehicle   "+ type_of_aircraft);
		            prf_code2 = abc_code ;
		            sus_no12= sus_no1;
		            unit_name12= unit_name1;
		            cont_comd12 = cont_comd1;
		            cont_corps12 = cont_corps1;
		            cont_div12 = cont_div1;
		            cont_bde12= cont_bde1;
		            line_dte_sus12 = line_dte_sus1;
		            mct_main_list12 = act_main_list1;
		            status2 = status1;
		            ason_date12 = ason_date1;
		            System.out.println("asonDate"+ason_date1);
		            System.out.println("abc  "+ abc_code);
		            Mmap.put("msg", "ok");
		            
		             return new ModelAndView("redirect:unit_wise_detailsforRW");
		    }
		
		@RequestMapping(value = "/admin/unit_wise_detailsforRPAS", method = RequestMethod.GET)
	    public ModelAndView unit_wise_detailsforRPAS(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
	            String  roleid = session.getAttribute("roleid").toString();
	            /*Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte", roleid);        
	            if(val == false) {
	                    return new ModelAndView("AccessTiles");
	            }*/
	            String roleAccess = session.getAttribute("roleAccess").toString();
	            String roleSubAccess = session.getAttribute("roleSubAccess").toString();
	            String roleFormationNo = session.getAttribute("roleFormationNo").toString();
	            String roleSusNo = session.getAttribute("roleSusNo").toString();
	            if(request.getHeader("Referer") == null ) {
	                    msg = "";
	            }
	           
	            if(roleAccess.equals("Formation")){
	                    if(roleSubAccess.equals("Command")){
	                            String formation_code = String.valueOf(roleFormationNo.charAt(0));
	                            cont_comd12 = formation_code;
	                            Mmap.put("cont_comd1",cont_comd12);
	                            
	                            List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);
	                            Mmap.put("getCommandList",comd);
	                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
	                            Mmap.put("getCorpsList",corps);
	                            
	                            String select="<option value='0'>--Select--</option>";
	                            Mmap.put("selectcorps",select);
	                            Mmap.put("selectDiv",select);
	                            Mmap.put("selectBde",select);
	                            Mmap.put("selectLine_dte",select);
	                            
	                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	                    }if(roleSubAccess.equals("Corps")){
	                            String command = String.valueOf(roleFormationNo.charAt(0));
	                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
	                            Mmap.put("getCommandList",comd);
	                            
	                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
	                            cont_corps12 = cor;
	                            Mmap.put("cont_corps1",cont_corps12);
	                            
	                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
	                            Mmap.put("getCorpsList",corps);
	                            
	                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
	                            Mmap.put("getDivList",Bn);
	                            
	                            String select="<option value='0'>--Select--</option>";
	                            Mmap.put("selectDiv",select);
	                            Mmap.put("selectBde",select);
	                            Mmap.put("selectLine_dte",select);
	                            
	                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	                    }if(roleSubAccess.equals("Division")){
	                            String command = String.valueOf(roleFormationNo.charAt(0));
	                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
	                            Mmap.put("getCommandList",comd);
	                            
	                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
	                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
	                            Mmap.put("getCorpsList",corps);
	                            
	                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	                            cont_div12 = div;
	                            Mmap.put("cont_div1",cont_div12);
	                            
	                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
	                            Mmap.put("getDivList",Bn);
	                            
	                            List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
	                            Mmap.put("getBdeList",bde);
	                            
	                            String select="<option value='0'>--Select--</option>";
	                            Mmap.put("selectBde",select);
	                            
	                            Mmap.put("selectLine_dte",select);
	                            
	                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	                    }if(roleSubAccess.equals("Brigade")){
	                            String command = String.valueOf(roleFormationNo.charAt(0));
	                            List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
	                            Mmap.put("getCommandList",comd);
	                            
	                            String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
	                            List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
	                            Mmap.put("getCorpsList",corps);
	                            
	                            String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	                            List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
	                            Mmap.put("getDivList",Bn);
	                            
	                            cont_bde12 = roleFormationNo;
	                            Mmap.put("cont_bde1",cont_bde12);
	                            
	                            String bde_code = roleFormationNo;
	                            List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
	                            Mmap.put("getBdeList",bde);
	                            
	                            String select="<option value='0'>--Select--</option>";
	                            Mmap.put("selectLine_dte",select);
	                            Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	                    }
	            }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
	                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
	                    Mmap.put("getCommandList",comd);
	                    String selectComd ="<option value=''>--Select--</option>";
	                    String select="<option value='0'>--Select--</option>";
	                    Mmap.put("selectcomd", selectComd);
	                    Mmap.put("selectcorps",select);
	                    Mmap.put("selectDiv",select);
	                    Mmap.put("selectBde",select);
	                    Mmap.put("selectLine_dte",select);
	                    Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
	                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	            }else if(roleAccess.equals("Line_dte")){
	                    List<Tbl_CodesForm> comd=allOrbat.getCommandDetailsList();
	                    Mmap.put("getCommandList",comd);
	                    String selectComd ="<option value=''>--Select--</option>";
	                    String select="<option value='0'>--Select--</option>";
	                    Mmap.put("selectcomd", selectComd);
	                    Mmap.put("selectcorps",select);
	                    Mmap.put("selectDiv",select);
	                    Mmap.put("selectBde",select);
	                    Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
	                    Mmap.put("getTypeOfUnitList", allOrbat.getTypeOfUnitList());
	            }else {
	                    return new ModelAndView("AccessTiles");
	            }
	            
	            String formCode ="";
	            if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
	                    formCode = cont_bde12;
	    }else {
	            if(!cont_div12.equals("0") && !cont_div12.equals("")){
	                    formCode = cont_div12;
	                }else {
	                        if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
	                                formCode = cont_corps12;
	                        }else {
	                                if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
	                                        formCode = cont_comd12;
	                                }
	                        }
	                }
	        }
	            if(msg != null && msg.equals("ok")){
	                    Mmap.put("type_veh", type_veh2);
	                    Mmap.put("prf_code", prf_code2);
	                    Mmap.put("sus_no1",sus_no12);
	                    Mmap.put("unit_name1",unit_name12);
	                    Mmap.put("cont_comd1",cont_comd12);
	                    Mmap.put("cont_corps1",cont_corps12);
	                   
	                    Mmap.put("cont_div1",cont_div12);
	                    Mmap.put("cont_bde1",cont_bde12);
	                    Mmap.put("line_dte_sus1",line_dte_sus12);
	                    Mmap.put("mct_main_list1",mct_main_list12);
	                    Mmap.put("status",status2);
	                    Mmap.put("ason_date", ason_date12);
	            }else{
	                    type_veh2 = 0;
	                    prf_code2 = "";
	                   
	                    sus_no12= "";
	                    unit_name12="";
	                    cont_comd12 = "";
	                    cont_corps12 = "";
	                    
	                    cont_div12 ="";
	                    cont_bde12="";
	                    line_dte_sus12 = "";
	                    mct_main_list12 = "";
	                    status2="";
	                    ason_date12 = "";
	                    
	                  
	            }
	            if(msg != null && msg.equals("ok")) {
	                    msg = "";
	            }
	            Mmap.put("msg", msg);        
	            
	        
	            ArrayList<ArrayList<String>>  getdetails_ue_uhrpasdacr = null;
	              if(type_veh2 == 1) {  //RW 
	            	
	            	  getdetails_ue_uhrpasdacr = dcrDAO.avn_assetstatus_linedterpas(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,ason_date12); 
	               Mmap.put("getdetails_ue_uhrpasdacr", getdetails_ue_uhrpasdacr);
					int totalUE = 0;
					int totalSUH = 0;
					int totalIUH = 0;
					int totalRIUH = 0;
					int totalREUH = 0;
					int totalAOGUH = 0;
					int totalACCIUH = 0;
					int totalOHUH = 0;
					int totalUH = 0;
					int totalDEFI = 0;
					int totalSUR = 0;
					
					
					if(getdetails_ue_uhrpasdacr.size()>0) {
						for(int i=0;i<getdetails_ue_uhrpasdacr.size();i++) {
							String totalUE1= getdetails_ue_uhrpasdacr.get(i).get(9);
							if(totalUE1 == null || totalUE1.equals(null)) {
								totalUE =0;
							}
							String totalSUH1= getdetails_ue_uhrpasdacr.get(i).get(11);
							if(totalSUH1 == null || totalSUH1.equals(null)) {
								totalSUH =0;
							}
							String totalIUH1= getdetails_ue_uhrpasdacr.get(i).get(12);
							if(totalIUH1 == null || totalIUH1.equals(null)) {
								totalIUH =0;
							}
							String totalRIUH1= getdetails_ue_uhrpasdacr.get(i).get(13);
							if(totalRIUH1 == null || totalRIUH1.equals(null)) {
								totalRIUH =0;
							}
							String totalREUH1= getdetails_ue_uhrpasdacr.get(i).get(14);
							if(totalREUH1 == null || totalREUH1.equals(null)) {
								totalREUH =0;
							}
							String totalAOGUH1= getdetails_ue_uhrpasdacr.get(i).get(15);
							if(totalAOGUH1 == null || totalAOGUH1.equals(null)) {
								totalAOGUH =0;
							}
							String totalACCIUH1= getdetails_ue_uhrpasdacr.get(i).get(16);
							if(totalACCIUH1 == null || totalACCIUH1.equals(null)) {
								totalACCIUH =0;
							}
							String totalOHUH1= getdetails_ue_uhrpasdacr.get(i).get(17);
							if(totalOHUH1 == null || totalOHUH1.equals(null)) {
								totalOHUH =0;
							}
							String totalUH1= getdetails_ue_uhrpasdacr.get(i).get(10);
							if(totalUH1 == null || totalUH1.equals(null)) {
								totalUH =0;
							}
							
							String totalSUR1= getdetails_ue_uhrpasdacr.get(i).get(18);
							if(totalSUR1 == null || totalSUR1.equals(null)) {
								totalSUR =0;
							}
							String totalDEFI1= getdetails_ue_uhrpasdacr.get(i).get(19);
							if(totalDEFI1 == null || totalDEFI1.equals(null)) {
								totalDEFI =0;
							}
							
							
						
							totalUE = totalUE + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(9));
							totalSUH = totalSUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(11));
							totalIUH = totalIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(12));
							totalRIUH = totalRIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(13));
							totalREUH = totalREUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(14));
							totalAOGUH = totalAOGUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(15));
							totalACCIUH = totalACCIUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(16));
							totalOHUH = totalOHUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(17));
							totalUH = totalUH + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(10));
							totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(18));
							totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhrpasdacr.get(i).get(19));
							
						}
					}
					
					
					Mmap.put("totalUE",totalUE);
					Mmap.put("totalSUH",totalSUH);
					Mmap.put("totalIUH",totalIUH);
					Mmap.put("totalRIUH",totalRIUH);
					Mmap.put("totalREUH",totalREUH);
					Mmap.put("totalAOGUH",totalAOGUH);
					Mmap.put("totalACCIUH",totalACCIUH);
					Mmap.put("totalOHUH",totalOHUH);
					Mmap.put("totalUH",totalUH);
					Mmap.put("totalSUR",totalSUR);
					Mmap.put("totalDEFI",totalDEFI);
	              }
	            
	            return new ModelAndView("unit_wise_detailsforRPASTile");
	    }
	    
	@RequestMapping(value = "/admin/unit_wise_status_detailsforRPAS", method = RequestMethod.POST)
	    public ModelAndView unit_wise_status_detailsforRPAS(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
	                    @RequestParam(value = "type_veh1", required = false) int type_of_aircraft,
	                    @RequestParam(value = "prf_code1", required = false) String abc_code,
	                    @RequestParam(value = "sus_no1", required = false) String sus_no1,
	                    @RequestParam(value = "unit_name1", required = false) String unit_name1,
	                    @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
	                    @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
	                    @RequestParam(value = "cont_div1", required = false) String cont_div1,
	                    @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
	                    @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
	                    @RequestParam(value = "mct_main_list1", required = false) String act_main_list1,
	        			@RequestParam(value = "type_of_force1", required = false) String status1,
	        			@RequestParam(value = "issue_date1", required = false) String ason_date1)
	    
	    
	    
	    {        
	            type_veh2 = type_of_aircraft;
	            System.out.println("hello type of vehicle   "+ type_of_aircraft);
	            prf_code2 = abc_code ;
	            sus_no12= sus_no1;
	            unit_name12= unit_name1;
	            cont_comd12 = cont_comd1;
	            cont_corps12 = cont_corps1;
	            cont_div12 = cont_div1;
	            cont_bde12= cont_bde1;
	            line_dte_sus12 = line_dte_sus1;
	            mct_main_list12 = act_main_list1;
	            status2 = status1;
	            ason_date12 = ason_date1;
	            System.out.println("asonDate"+ason_date1);
	            System.out.println("abc  "+ abc_code);
	            Mmap.put("msg", "ok");
	            
	             return new ModelAndView("redirect:unit_wise_detailsforRPAS");
	    }
		

	
}
