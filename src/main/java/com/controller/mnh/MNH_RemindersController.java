package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.ExportFile.Excel_Age_Service_Wise_Report;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_RemindersDAO;
import com.models.mnh.Scrutiny_Search_Model;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class MNH_RemindersController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	MNH_RemindersDAO mnh6_Dao;
	
	@Autowired
	MNH_Common_DAO mnh1_Dao;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	//REMINDER MODULE (1)-> (INPUT REMINDERS MODULE SCREEN START)
	@RequestMapping(value = "/admin/mnh_input_reminder", method = RequestMethod.GET)
	public ModelAndView mnh_input_reminder(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		
		
		Boolean val = roledao.ScreenRedirect("mnh_input_reminder", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_reminder_inputTiles");
	}
	//REMINDER MODULE (1)-> (INPUT REMINDERS MODULE SCREEN END)
		
		
	//REMINDER MODULE (2)-> (DISCHARGE REMINDERS MODULE SCREEN START)
	@RequestMapping(value = "/admin/mnh_discharge_remider", method = RequestMethod.GET)
	public ModelAndView mnh_discharge_remider(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		
		Boolean val = roledao.ScreenRedirect("mnh_discharge_remider", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_reminder_dischargeTiles");
	}
	//REMINDER MODULE (2)-> (DISCHARGE REMINDERS MODULE SCREEN END)
	
	//(1)-> (INPUT REMINDERS MODULE SCREEN METHODS START)
	@RequestMapping(value = "/search_reminder_input", method = RequestMethod.POST)
	public ModelAndView search_reminder_input(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("cmd1") String cmd1,String dt_frm,String dt_to,String qtr,String yr1,String tbl_name,HttpServletRequest request){
		
		
		
		Boolean val = roledao.ScreenRedirect("mnh_input_reminder", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		 if (tbl_name.equals("-1") || tbl_name == "-1") {
				model.put("msg", "Please Select Input Type");
				return new ModelAndView("redirect:mnh_input_reminder");
			}
	if (tbl_name.equals("A&D") || tbl_name == "A&D") {
			if (dt_frm.equals("") || dt_frm == "") {
				model.put("msg", "Please Select From Date");
				return new ModelAndView("redirect:mnh_input_reminder");
			}
			if(validation.RankCodeLength(dt_frm)  == false){
				model.put("msg",validation.from_dateMSG);
	             return new ModelAndView("redirect:mnh_input_reminder");
	         }
			if (dt_to.equals("") || dt_to == "") {
				model.put("msg", "Please Select To Date");
				return new ModelAndView("redirect:mnh_input_reminder");
			}
			if(validation.RankCodeLength(dt_to)  == false){
				model.put("msg",validation.to_dateMSG);
	             return new ModelAndView("redirect:mnh_input_reminder");
	         }
		 	
	}
	
	if (tbl_name.equals("OPD") || tbl_name == "OPD" || tbl_name.equals("BO") || tbl_name == "BO" || tbl_name.equals("OSP") || tbl_name == "OSP" 
		  || tbl_name.equals("Mortality") || tbl_name == "Mortality" || tbl_name.equals("IBM") || tbl_name == "IBM") {
		
		if( qtr == null || qtr.equals("-1")){ 
			model.put("msg", "Please Select the Quarter");
			return new ModelAndView("redirect:mnh_input_reminder");
		}
		if (yr1.equals("") || yr1 == null) {
			model.put("msg", "Please Enter the Year");
			return new ModelAndView("redirect:mnh_input_reminder");
		}
		if (request.getParameter("yr1").length() < 4) {
			model.put("msg", "Please Enter the Valid Year");
			return new ModelAndView("redirect:mnh_input_reminder");
		}
		
		if (validation.checkYearLength(yr1) == false) {
			model.put("msg", validation.yearMSG);
			return new ModelAndView("redirect:mnh_input_reminder");
		}
		
	}	
		
		
		List<Map<String, Object>> list= mnh6_Dao.search_reminder_input(dt_frm,dt_to,qtr,tbl_name,cmd1,yr1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("cmd1", cmd1);
		model.put("yr1", yr1);
		model.put("dt_frm", dt_frm);
		model.put("dt_to",dt_to);
		model.put("qtr", qtr);
		model.put("tbl_name",tbl_name);
		return new ModelAndView("mnh_reminder_inputTiles");
	}
	//(1)-> (INPUT REMINDERS MODULE SCREEN METHODS END)
	
	//(2)-> (DISCHARGE REMINDERS MODULE SCREEN METHODS START)
	@RequestMapping(value = "/search_reminder_discharge", method = RequestMethod.POST)
	public ModelAndView search_reminder_discharge(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("unit1") String unit1,String sus1,String cmd1,String dt_frm,String dt_to,HttpServletRequest request){
		
		
		
		Boolean val = roledao.ScreenRedirect("mnh_discharge_remider", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 if (dt_frm.equals("") || dt_frm == "") {
				model.put("msg", "Please Select From Date");
				return new ModelAndView("redirect:mnh_discharge_remider");
			}
		
		 if (dt_to.equals("") || dt_to == "") {
			 model.put("msg", "Please Select To Date");
				return new ModelAndView("redirect:mnh_discharge_remider");
			}
		
		
		if(validation.RankCodeLength(dt_frm)  == false){
			model.put("msg",validation.from_dateMSG);
	             return new ModelAndView("redirect:mnh_discharge_remider");
	         }
		 	
	 	 if(validation.RankCodeLength(dt_to)  == false){
	 		model.put("msg",validation.to_dateMSG);
	             return new ModelAndView("redirect:mnh_discharge_remider");
	         }
		List<Map<String, Object>> list= mnh6_Dao.search_reminder_discharge(sus1,cmd1,dt_frm,dt_to);
		model.put("list", list);
		model.put("size", list.size());
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("unit1", unit1);
		model.put("sus1", sus1);
		model.put("cmd1", cmd1);
		model.put("dt_frm",dt_frm);
		model.put("dt_to", dt_to);
		return new ModelAndView("mnh_reminder_dischargeTiles");
	}
	//(2)-> (DISCHARGE REMINDERS MODULE SCREEN METHODS END)
	
	@RequestMapping(value = "/admin/mnh_scrutiny_dischrge_reminder", method = RequestMethod.GET)
	public ModelAndView mnh_scrutiny_dischrge_reminder(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		
		Boolean val = roledao.ScreenRedirect("mnh_scrutiny_dischrge_reminder", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_scrutiny_dischargeTiles");
	}
	
	
	@RequestMapping(value = "/search_scrutiny_dischargeList", method = RequestMethod.POST)
    public ModelAndView search_scrutiny_dischargeList(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
    		@ModelAttribute("dschrg_date1") String dschrg_date1,String disposal1,String diagnosis_code1a1,String icd_cause_code1a1,String discharge_remarks1){
		            
        
		Boolean val = roledao.ScreenRedirect("mnh_scrutiny_dischrge_reminder", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
        if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 
		
		if (dschrg_date1.equals("") || dschrg_date1 == "") {
				model.put("msg", "Please Select Discharge Date");
				return new ModelAndView("redirect:mnh_scrutiny_dischrge_reminder");
			}
		
		if( disposal1 == null || disposal1.equals("")){ 
			model.put("msg", "Please Enter the Disposal");
			return new ModelAndView("redirect:mnh_scrutiny_dischrge_reminder");
		}
		
		
		
        List<Map<String, Object>> list= mnh6_Dao.search_scrutiny_discharge(dschrg_date1,disposal1,diagnosis_code1a1,icd_cause_code1a1,discharge_remarks1);
        model.put("list", list);
        model.put("size", list.size());
        
        model.put("dschrg_date1",dschrg_date1);
        model.put("disposal1", disposal1);
        model.put("diagnosis_code1a1",diagnosis_code1a1);
        model.put("icd_cause_code1a1",icd_cause_code1a1);
        model.put("discharge_remarks1",discharge_remarks1);
        
        return new ModelAndView("mnh_scrutiny_dischargeTiles");   
    }
	
	@RequestMapping(value = "/Scrutiny_DischargeAction", method = RequestMethod.POST)
	public ModelAndView Scrutiny_DischargeAction(@ModelAttribute("Scrutiny_DischargeCMD") Scrutiny_Search_Model rs1,
			HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException{
		Boolean val = roledao.ScreenRedirect("mnh_scrutiny_dischrge_reminder", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		String username = session.getAttribute("username").toString();	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
	
		Date dschrg_dt_i=null;
		String dschrg_dt = request.getParameter("dschrg_dt");
		if (!dschrg_dt.equals("")) {
			dschrg_dt_i = df.parse(dschrg_dt);
		}
		String disposal = request.getParameter("disposal");			
		String diagnosis_code1a = request.getParameter("diagnosis_code1a");
		String icd_cause_code1a = request.getParameter("icd_cause_code1a");		
		String discharge_remarks = request.getParameter("discharge_remarks");		
		String id = request.getParameter("id_hid");
		
        Session session1 = HibernateUtilNA.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        String hql = "update Scrutiny_Search_Model set dschrg_date=:dschrg_date,disposal=:disposal,diagnosis_code1a=:diagnosis_code1a,"
            		+ "icd_cause_code1a=:icd_cause_code1a,discharge_remarks=:discharge_remarks where id=:id"; 
        Query query = session1.createQuery(hql).setDate("dschrg_date", dschrg_dt_i).setString("disposal",disposal)
        		.setString("diagnosis_code1a",diagnosis_code1a).setString("icd_cause_code1a",icd_cause_code1a)
        		.setString("discharge_remarks",discharge_remarks).setInteger("id",Integer.parseInt(id));
        int rowCount = query.executeUpdate();
        tx.commit();
        session1.close();
        if(rowCount > 0){
        	model.put("msg", "Data Updated Successfully");
        }else{
            model.put("msg", "Data Not Updated Successfully");
        }
            
        List<Map<String, Object>> list = mnh6_Dao.search_scrutiny_discharge(request.getParameter("dschrg_datehid"),request.getParameter("disposalhid"),
        		request.getParameter("diagnosis_code1ahid"),request.getParameter("icd_cause_code1ahid"),request.getParameter("discharge_remarkshid"));
        model.put("list", list);
   		model.put("list.size()", list.size());
		model.put("dschrg_date1", request.getParameter("dschrg_datehid"));
   		model.put("disposal1", request.getParameter("disposalhid"));
   		model.put("diagnosis_code1a1", request.getParameter("diagnosis_code1ahid"));
   		model.put("icd_cause_code1a1", request.getParameter("discharge_remarkshid"));
   		model.put("discharge_remarks1", request.getParameter("discharge_remarkshid"));
   		model.put("dschrg_datehid", dschrg_dt);
   		model.put("disposalhid", disposal);
	   		
        return new ModelAndView("mnh_scrutiny_dischargeTiles");    
	}
	@RequestMapping(value = "/Excel_mnh_discharge_reminders", method = RequestMethod.POST)
	public ModelAndView Excel_mnh_discharge_reminders(HttpServletRequest request,ModelMap model,HttpSession session,
			@ModelAttribute("unit2") String unit2,String sus2,String cmd2,String dt_frm2,String dt_to2,String typeReport1) {

	 
	 ArrayList<ArrayList<String>> ageprint = mnh6_Dao.Search_discharge(sus2,cmd2,dt_frm2,dt_to2);
		
		List<String> TH = new ArrayList<String>();
		TH.add("A & D Number");
		TH.add("Patient Name");
		TH.add("Admission Date");
		TH.add("Personnel No");
		TH.add("Personnel Name");
		TH.add("Rank");
		TH.add("Discharge Remarks");
		TH.add("Diagnosis Code");
		TH.add("Diagnosis Cause");
		TH.add("Discharge Date");
		TH.add("Disposal");
		

				
		String Heading = "\nHELD STR OF OFFRS AGE WISE";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", ageprint);
		
	}
}
