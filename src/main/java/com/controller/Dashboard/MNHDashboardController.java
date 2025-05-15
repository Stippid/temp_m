package com.controller.Dashboard;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.Dashboard.MNHDashboardFinalDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.mnh.Medical_Cmd_Corp_Disease_Details_View;

@Controller
@RequestMapping(value = {"admin","/"}) 
public class MNHDashboardController {
	
	@Autowired
	MNHDashboardFinalDAO MnhFinal;

	@Autowired
	MNH_Common_DAO mnhCommonDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/mnhDashboard", method = RequestMethod.GET)
	public ModelAndView MnhDashboard5(ModelMap Mmap,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("mnhDashboard", roleid);
/*		if (val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String year1 =(String.valueOf(year)).substring(2,4);
		Mmap.put("getCountAllImportantDis",  MnhFinal.getCountAllImportantDis(year));
		
		Mmap.put("getCommand_wise_count_List", MnhFinal.getCommand_wise_count_List(year));
	//	Mmap.put("getBED_STATE_ARMY_List", MnhFinal.getBED_STATE_ARMY_List());
		
		Mmap.put("getChart1List", MnhFinal.getChart1List());
		Mmap.put("getChart1ExList", MnhFinal.getChart1ExList());
		Mmap.put("getTop10PCList", MnhFinal.getTop10PCList());
	//	Mmap.put("getLMCList", MnhFinal.getLMCList());
		
		Mmap.put("UnitInfoTblList", MnhFinal.UnitInfoTblList());
		
		//Mmap.put("getlowmadicalchart", MnhFinal.getlowmadicalchart());
		//Mmap.put("hospital_datastatus", MnhFinal.hospital_datastatus());

		Mmap.put("notifiable_diseaselist", MnhFinal.notifiable_diseaselist(year1));
		
		Mmap.put("suicides_list", MnhFinal.suicides_list());;
		//Mmap.put("daily_unusual_occurence", MnhFinal.daily_unusual_occurence());
		 Mmap.put("Getsuicide_caseslist",MnhFinal.Getsuicide_caseslist("",year1));
		return new ModelAndView("mnhDashboardTiles");
	}
	
	
	@RequestMapping(value = "/admin/getdataViewMore", method = RequestMethod.GET)
	public ModelAndView getdataViewMore(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
    	int userid = (Integer)session.getAttribute("userId");
    	Mmap.put("getmnhPrincipalList",MnhFinal.getmnhPrincipalList());
    	ArrayList<ArrayList<String>> l1 = mnhCommonDAO.getMNHRData(userid);
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnhCommonDAO.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("getViewMoreTiles");
	}
	
	@RequestMapping(value = "/getCorpsWise_Count_List",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getCorpsWise_Count_List(String Command,HttpSession session) {
		return MnhFinal.getCorpsWise_Count_List(Command);
	}
	
	@RequestMapping(value = "/getDivsWise_Count_List",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getDivsWise_Count_List(String Command,HttpSession session) {
		return MnhFinal.getDivsWise_Count_List(Command);
	}
	
	@RequestMapping(value = "/getBdesWise_Count_List",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getBdesWise_Count_List(String Command,HttpSession session) {
		return MnhFinal.getBdesWise_Count_List(Command);
	}
	
	// Start OnClick Refresh Graph Button
	@RequestMapping(value = "/getChartRelationship",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getChartRelationship(String relationship, String yr,HttpSession session) {
		return MnhFinal.getChartRelationship(relationship,yr);
	}
	//test by guru
	
	//upto
	
	@RequestMapping(value = "/getdiseaseRelationship",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getdiseaseRelationship(String relationship, String yr1,HttpSession session) {
		return MnhFinal.getdiseaseRelationship(relationship,yr1);
	}
	
	@RequestMapping(value = "/getChartESMRelationship",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getChartESMRelationship(String relationship, String yr,HttpSession session) {
		return MnhFinal.getChartESMRelationship(relationship,yr);
	}
	@RequestMapping(value = "/getChartCOMMANDRelationship",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getChartCOMMANDRelationship(int yr,HttpSession session) {
		return  MnhFinal.getCommand_wise_count_List1(yr);
	}
	///Corps graph refresh button
	@RequestMapping(value = "/getCorpsWise_Count_List1",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getCorpsWise_Count_List1(String command,String relationship, String yr,HttpSession session) {
		return MnhFinal.getCorpsWise_Count_List1(command,relationship,yr);
	}
	//Div graph refresh button
	@RequestMapping(value = "/getDivsWise_Count_List1",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getDivsWise_Count_List1(String command,String relationship, String yr,HttpSession session) {
		return MnhFinal.getDivsWise_Count_List1(command,relationship,yr);
	}
	//Bde graph refresh button
	@RequestMapping(value = "/getBdesWise_Count_List1",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>>  getBdesWise_Count_List1(String command,String relationship, String yr,HttpSession session) {
		return MnhFinal.getBdesWise_Count_List1(command,relationship,yr);
	}
	@RequestMapping(value = "/getChartTopPrincipal",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getChartTopPrincipal(String relationship, String yr,HttpSession session) {
		return MnhFinal.getChartTopPrincipal(relationship,yr);
	}
	
	@RequestMapping(value = "/getCountAllImportantDis",method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getihdCount(int yr) {
		int c_year = Calendar.getInstance().get(Calendar.YEAR);
		if(yr > 1901 & yr <= c_year) {
			ArrayList<List<String>> list = MnhFinal.getCountAllImportantDis(yr);
			return list;
		}else {
			return null;
		}
	}
	
	String whr = "";
	int year = 0;
	@RequestMapping(value = "/admin/getihddesh", method = RequestMethod.GET)
	public ModelAndView getihddesh(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		whr="where disease_type='ISCHAEMIC HEART DISEASES' and yr ="+yr;
		year = yr;
		Mmap.put("pdetails", "IHD DETAILS");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	@RequestMapping(value = "/admin/getcmdihddeshrpt")
	public @ResponseBody DatatablesResponse<Medical_Cmd_Corp_Disease_Details_View> getcmdihddeshrpt(@DatatablesParams DatatablesCriterias criterias,HttpSession session, HttpServletRequest request){
		if(!whr.equals("") && year > 0) {
			DataSet<Medical_Cmd_Corp_Disease_Details_View> dataSet = MnhFinal.DatatablesCriteriasCmdCorps(criterias ,whr);
			return DatatablesResponse.build(dataSet, criterias);
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/admin/gethypertenstion", method = RequestMethod.GET)
	public ModelAndView gethypertenstion(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		whr="where disease_principal='HYPERTENSIVE DISEASES' and yr ="+yr;
		Mmap.put("pdetails", "HYPERTENSIVE DETAILS");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	@RequestMapping(value = "/admin/getdiabetes", method = RequestMethod.GET)
	public ModelAndView getdiabetes(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		whr="where disease_principal='DIABETES MELLITUS' and yr ="+yr;
		Mmap.put("pdetails", "DIABETES DETAILS");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	@RequestMapping(value = "/admin/getobesity", method = RequestMethod.GET)
	public ModelAndView getobesity(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		whr="where disease_principal='OBESITY' and yr ="+yr;
		Mmap.put("pdetails", "OBESITY DETAILS");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	@RequestMapping(value = "/admin/getADS", method = RequestMethod.GET)
	public ModelAndView getADS(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		whr="where block_description='ADS' and yr ="+yr;
		Mmap.put("pdetails", "PSYCHIATRIC DISORDERS (INCL SUBSTANCE ABUSE)");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	@RequestMapping(value = "/admin/getinjuries", method = RequestMethod.GET)
	public ModelAndView getinjuries(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,int yr) {
		 whr="where disease_principal='INJURIES NEA' and yr ="+yr;
		 Mmap.put("pdetails", "INJURIES NEA DETAILS");
		Mmap.put("msg", msg);
		return new ModelAndView("getdiabetesTiles");
	}
	
	// End OnClick Refresh Graph Button
	
	
	@RequestMapping(value = "/BlockDescCatTblList",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> BlockDescCatTblList(String principal_cause, String yr, String command) {
		String qry="";
		principal_cause = principal_cause.replace("&#40;", "(");
		principal_cause = principal_cause.replace("&#41;", ")");
		if(principal_cause.equals("0") && command.equals("ALL") ) {
			//qry="select distinct block_description as b_desc,category as cat,sum(count) as count from med_cmd_mn_count where yr = "+yr+" group by block_description,category order by block_description";
			qry = "select distinct (case when block_description = null or block_description = '' or block_description is null then '' else block_description end) as b_desc,sum(case when category = 'OFFICER' then count else 0 end) as off_value,sum(case when category = 'MNS' then count else 0 end) as mns_value,sum(case when category = 'JCO' then count else 0 end) as jco_value,sum(case when category = 'OR' then count else 0 end) as or_value from med_cmd_mn_count where yr = "+yr+" and relationship = 'SELF' group by block_description order by b_desc";
		}
		else if(!principal_cause.equals("0") && command.equals("ALL")) {
			qry="select distinct (case when block_description = null or block_description = '' or block_description is null then '' else block_description end) as b_desc,sum(case when category = 'OFFICER' then count else 0 end) as off_value,sum(case when category = 'MNS' then count else 0 end) as mns_value,sum(case when category = 'JCO' then count else 0 end) as jco_value,sum(case when category = 'OR' then count else 0 end) as or_value from med_cmd_mn_count where disease_principal ='"+principal_cause+"' and yr= '"+yr+"' and relationship = 'SELF'  group by block_description order by b_desc";	
		}
		else if(principal_cause.equals("0") && !command.equals("ALL")) {
			qry="select distinct (case when block_description = null or block_description = '' or block_description is null then '' else block_description end) as b_desc,sum(case when category = 'OFFICER' then count else 0 end) as off_value,sum(case when category = 'MNS' then count else 0 end) as mns_value,sum(case when category = 'JCO' then count else 0 end) as jco_value,sum(case when category = 'OR' then count else 0 end) as or_value from med_cmd_mn_count where  yr= '"+yr+"' and command= '"+command+"' and relationship = 'SELF'  group by block_description order by b_desc";	
		}
		else {
			qry="select distinct (case when block_description = null or block_description = '' or block_description is null then '' else block_description end) as b_desc,sum(case when category = 'OFFICER' then count else 0 end) as off_value,sum(case when category = 'MNS' then count else 0 end) as mns_value,sum(case when category = 'JCO' then count else 0 end) as jco_value,sum(case when category = 'OR' then count else 0 end) as or_value from med_cmd_mn_count where disease_principal ='"+principal_cause+"' and yr= '"+yr+"' and command= '"+command+"' and relationship = 'SELF' group by block_description order by b_desc"; 
		}
		
		List<Map<String, Object>> list = MnhFinal.getAllReportListJdbc(qry);	
		return list;
	}
	
	@RequestMapping(value = "/getBedCorpsWise_Count_List")
	public @ResponseBody ArrayList<List<String>>  getBedCorpsWise_Count_List(String Command,HttpSession session) {
		return MnhFinal.getBedCorpsWise_Count_List(Command);
	}
	
	@RequestMapping(value = "/getLMCChart1List")
	public @ResponseBody ArrayList<List<String>> getLMCChart1List(String from_date, String to_date,HttpSession session) {
		return MnhFinal.getLMCChart1ListWithParameter(from_date,to_date);
	}
	
	
	@RequestMapping(value = "/admin/DgMSDashboard", method = RequestMethod.GET)
	public ModelAndView DgMSDashboard(ModelMap Mmap,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		
		Mmap.put("daily_unusual_occurence", MnhFinal.daily_unusual_occurence());
		Mmap.put("daily_vip_admission", MnhFinal.daily_vip_admission());
		Mmap.put("daily_amc_adm_admission", MnhFinal.daily_amc_adm_admission());
		Mmap.put("daily_notifiable_disease", MnhFinal.daily_notifiable_disease());
		Mmap.put("daily_defaulterlist", MnhFinal.daily_bed_defaulterlist());
		Mmap.put("daily_bed_occupied", MnhFinal.daily_bed_occupied());
		
		Mmap.put("hospital_datastatus", MnhFinal.hospital_datastatus(year));
		Mmap.put("daily_disease_surve", MnhFinal.daily_disease_surve());
		Mmap.put("daily_bed_state", MnhFinal.daily_bed_statelist());
		

		Mmap.put("daily_vip_admission_army_dgms", MnhFinal.daily_vip_admission_army_dgms());
		Mmap.put("daily_adm_amc_army_dgms", MnhFinal.daily_adm_amc_army_dgms());
		Mmap.put("daily_unusual_occur_dgms", MnhFinal.daily_unusual_occur_dgms());
		Mmap.put("daily_disease_surv_dtls_dgms", MnhFinal.daily_disease_surv_dtls_dgms());
		

		return new ModelAndView("DgMSDashboardTiles");
	}
	@RequestMapping(value = "/admin/DgMS_ArmyDashboard", method = RequestMethod.GET)
	public ModelAndView DgMS_ArmyDashboard(ModelMap Mmap,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Mmap.put("getBED_STATE_ARMY_List", MnhFinal.getBED_STATE_ARMY_List());
		Mmap.put("daily_vip_admission_army", MnhFinal.daily_vip_admission_army());
		Mmap.put("daily_vip_admission_army_bar", MnhFinal.daily_vip_admission_army_bar());
		Mmap.put("daily_bed_occupied", MnhFinal.daily_bed_occupied_army());
		Mmap.put("daily_infection_disease_army", MnhFinal.daily_infection_disease_army());
		Mmap.put("daily_bed_statelist_army", MnhFinal.daily_bed_statelist_army());
		return new ModelAndView("DGMS_ArmyDashboardTiles");
	}

	
	
/*	@RequestMapping(value = "/getnotifiable_diseaseList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getnotifiable_diseaseList() {

		List<Map<String, Object>> list_nom = MnhFinal.daily_notifiable_disease();
		return list_nom;
	}*/
}