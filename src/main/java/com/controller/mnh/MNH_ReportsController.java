/*package com.controller.mnh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class MNH_ReportsController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private MNH_ReportsDAO mnh7_Dao;
	 
	@Autowired
	private MNH_CommonController mcommon;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	//REPORTS MODULE  (1)-> (OPD REPORT SCREEN START)
	@RequestMapping(value = "/admin/mnh_opd_report", method = RequestMethod.GET)
	public ModelAndView mnh_opd_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_opd_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_opd_reportTiles");
	}
	
	 //REPORTS MODULE (1)-> (OPD REPORT SCREEN END)
	
	//REPORTS MODULE (2)-> (BED SERVING SCREEN START)
	@RequestMapping(value = "/admin/mnh_bed_serving", method = RequestMethod.GET)
	public ModelAndView mnh_bed_serving(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_bed_serving", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_bed_servingTiles");
	}
	//REPORTS MODULE (2)-> (BED SERVING SCREEN END)
	
	//REPORTS MODULE (3)-> (BED EX-SERVE SCREEN START)
	@RequestMapping(value = "/admin/mnh_bed_exserve", method = RequestMethod.GET)
	public ModelAndView mnh_bed_exserve(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_bed_exserve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_bed_exserveTiles");
	}
	//REPORTS MODULE (3)-> (BED EX-SERVE SCREEN END)
	
	//REPORTS MODULE (4)-> (STRENGTH REPORT SCREEN START)
	@RequestMapping(value = "/admin/mnh_strength_report", method = RequestMethod.GET)
	public ModelAndView mnh_strength_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_strength_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_strength_reportTiles");
	}
	//REPORTS MODULE (4)-> (STRENGTH REPORT SCREEN END)
	
	//REPORTS MODULE (5)-> (SURVEILLANCE DETAILS SCREEN START)
	@RequestMapping(value = "/admin/mnh_surveillance_details ", method = RequestMethod.GET)
	public ModelAndView mnh_surveillance_details(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
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
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_surveillance_detailsTiles");
	}				
	//REPORTS MODULE (5)-> (SURVEILLANCE DETAILS SCREEN END)
	
	//REPORTS MODULE (6)-> (MMR REPORT SCREEN START)
	@RequestMapping(value = "/admin/mnh_mmr_report", method = RequestMethod.GET)
	public ModelAndView mnh_mmr_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_mmr_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_mmr_reportTiles");
	}
	
	//REPORTS MODULE (6)-> (MMR REPORT SCREEN END)
	
	//REPORTS MODULE (7)-> (HIV/AIDS REPORT SCREEN START)
	@RequestMapping(value = "/admin/mnh_hivaids_report", method = RequestMethod.GET)
	public ModelAndView mnh_hivaids_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date); 
        
		Boolean val = roledao.ScreenRedirect("mnh_hivaids_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
        Mmap.put("date", date1);

		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "",session)); 
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "",session)); 
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		return new ModelAndView("mnh_hivaids_reportTiles");
	}
	
	//REPORTS MODULE (7)-> (HIV/AIDS REPORT SCREEN END)
	
	//(1)-> (OPD REPORT SCREEN METHODS START)
	@RequestMapping(value = "/search_opd_bed_strength_report", method = RequestMethod.POST)
	public ModelAndView search_opd_bed_strength_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "cmd1", required = false) String cmd1,String qtr1,String yr1,String para1){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
	 	
		if(para1.equalsIgnoreCase("R1")){
			Boolean val = roledao.ScreenRedirect("mnh_opd_report", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
	 	}
	 	
	 	if(para1.equalsIgnoreCase("R2")){
	 		Boolean val = roledao.ScreenRedirect("mnh_bed_serving", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
	 	}
	 	
	 	if(para1.equalsIgnoreCase("R3")){
	 		Boolean val = roledao.ScreenRedirect("mnh_bed_exserve", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
	 	}
	 	
	 	if(para1.equalsIgnoreCase("R4")){
	 		Boolean val = roledao.ScreenRedirect("mnh_strength_report", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
	 	}
	 	
	 	ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		if(para1.equalsIgnoreCase("R1")){
		 if(validation.checkYearLength(yr1)  == false){
		 		Mmap.put("msg",validation.yearMSG);
	             return new ModelAndView("redirect:mnh_opd_report");
	         }
		}
		if(para1.equalsIgnoreCase("R2")){
			 if(validation.checkYearLength(yr1)  == false){
			 		Mmap.put("msg",validation.yearMSG);
		             return new ModelAndView("redirect:mnh_bed_serving");
		         }
			}
		
		if(para1.equalsIgnoreCase("R3")){
			 if(validation.checkYearLength(yr1)  == false){
			 		Mmap.put("msg",validation.yearMSG);
		             return new ModelAndView("redirect:mnh_bed_exserve");
		         }
			}
		
		if(para1.equalsIgnoreCase("R4")){
			 if(validation.checkYearLength(yr1)  == false){
			 		Mmap.put("msg",validation.yearMSG);
		             return new ModelAndView("redirect:mnh_strength_report");
		         }
			}
		
		Mmap.put("r_1", l1);
	 	
	 	Mmap.put("cmd1", cmd1);
	 	Mmap.put("qtr1", qtr1);
	 	Mmap.put("yr1", yr1);
	 	
	 	List<Map<String, Object>> list = mnh7_Dao.search_opd_bed_strength_report(cmd1, qtr1, yr1, para1); 
	 	
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
	
		if(para1.equalsIgnoreCase("R1")){
			return new ModelAndView("mnh_opd_reportTiles");
		}
		if(para1.equalsIgnoreCase("R2")){
			return new ModelAndView("mnh_bed_servingTiles");
		}
		if(para1.equalsIgnoreCase("R3")){
			return new ModelAndView("mnh_bed_exserveTiles");
		}
        if(para1.equalsIgnoreCase("R4")){
        	return new ModelAndView("mnh_strength_reportTiles");
		}    
        return null;
	}
	
	@RequestMapping(value = "/search_mmr_report", method = RequestMethod.POST)
	public ModelAndView search_mmr_report(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("cmd1") String cmd1,String mth_yr1,String disease_principal1,String disease_mmr1,String disease_aso1,String block_description1){
		int userid = (Integer) session.getAttribute("userId");
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("r_1", l1);
		model.put("cmd1", cmd1);		
		model.put("mth_yr1",mth_yr1);
		model.put("disease_principal1", disease_principal1);
		model.put("disease_mmr1",disease_mmr1);
		model.put("disease_aso1", disease_aso1);
		model.put("block_description1",block_description1);
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		
		List<Map<String, Object>> list= mnh7_Dao.getsearch_mmr_report(cmd1,mth_yr1,disease_principal1,disease_mmr1,disease_aso1,block_description1);		
		model.put("list", list);
		model.put("size", list.size());
		return new ModelAndView("mnh_mmr_reportTiles");

	}
	
	
	//(1)-> (OPD REPORT SCREEN METHODS END)

	
	//REPORTS MODULE (8)-> (IMB REPORT SCREEN START)
		
	@RequestMapping(value = "/admin/imb_report", method = RequestMethod.GET)
	public ModelAndView imb_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
       
		Boolean val = roledao.ScreenRedirect("imb_report", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("date", date1);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_3", mcommon.getMedSystemCode("SERVICE", "",session)); 
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "",session)); 
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		return new ModelAndView("Imb_reportsTiles");
	}
		
	@RequestMapping(value = "/search_imb_reports", method = RequestMethod.POST)
	public ModelAndView search_imb_reports(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "cmd1", required = false) String cmd1,String category1,String from_date1,String to_date1){
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
	 	 if(validation.RankCodeLength(from_date1)  == false){
		 		Mmap.put("msg",validation.from_dateMSG);
	             return new ModelAndView("redirect:imb_report");
	         }
		 	
	 	 if(validation.RankCodeLength(to_date1)  == false){
		 		Mmap.put("msg",validation.to_dateMSG);
	             return new ModelAndView("redirect:imb_report");
	         }
		
	 	ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
	
		Mmap.put("r_1", l1);
	 	
	 	Mmap.put("cmd1", cmd1);
	 	Mmap.put("category1", category1);
	 	Mmap.put("from_date1", from_date1);
	 	Mmap.put("to_date1", to_date1);
	 	
	 	
	 	List<Map<String, Object>> list = mnh7_Dao.search_imb_reports(cmd1,category1,from_date1,to_date1); 
	 	//List<Map<String, Object>> list= mnh7_Dao.search_imb_reports(command,category,from_date,to_date);
	 	
		Mmap.put("list", list);
		Mmap.put("size", list.size());
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
		Mmap.put("ml_6", mcommon.getMedrelationList("", session));
		return new ModelAndView("Imb_reportsTiles");    
	}
		
		//REPORTS MODULE (7)-> (IMB REPORT SCREEN END)
		
		
		/////MORTALITY REPORT
	
		@RequestMapping(value = "/admin/mortality_report", method = RequestMethod.GET)
		public ModelAndView mortality_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			Date date = new Date();
            String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
           
			Boolean val = roledao.ScreenRedirect("mortality_report", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("date", date1);
			Mmap.put("r_1", l1);
			Mmap.put("msg", msg);
			Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "",session)); 
			Mmap.put("ml_6", mcommon.getMedrelationList("", session));
			return new ModelAndView("mortality_reportsTiles");
		}
	
		
		@RequestMapping(value = "/search_mortality_reports",method = RequestMethod.POST)
		public ModelAndView search_mortality_reports(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cmd1", required = false) String cmd1,String category1,String from_date1,String to_date1){
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
		 	
			
		 	ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			 if(validation.RankCodeLength(from_date1)  == false){
			 		Mmap.put("msg",validation.from_dateMSG);
		             return new ModelAndView("redirect:mortality_report");
		         }
			 	
			 if(validation.RankCodeLength(to_date1)  == false){
			 		Mmap.put("msg",validation.to_dateMSG);
		             return new ModelAndView("redirect:mortality_report");
		         }
			 
			Mmap.put("r_1", l1);
		 	
		 	Mmap.put("cmd1", cmd1);
		 	Mmap.put("category1", category1);
		 	Mmap.put("from_date1", from_date1);
		 	Mmap.put("to_date1", to_date1);
		 	
		 	List<Map<String, Object>> list = mnh7_Dao.search_mortality_reports(cmd1,category1,from_date1,to_date1); 
			//List<Map<String, Object>> list= mnh7_Dao.search_mortality_reports(command,category,from_date,to_date);		
		 	
			Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_4", mcommon.getMedSystemCode("CATEGORY", "", session));
			Mmap.put("ml_6", mcommon.getMedrelationList("", session));
			return new ModelAndView("mortality_reportsTiles");    
		}
		
			
		@RequestMapping(value = "/admin/daily_user_report_url", method = RequestMethod.GET)
		public ModelAndView daily_user_report_url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
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
		@RequestMapping(value = "/admin/opd_spl_proc", method = RequestMethod.GET)
		public ModelAndView opd_spl_proc(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("opd_spl_proc", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			Mmap.put("r_1", l1);
			Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			Mmap.put("msg", msg);
			return new ModelAndView("opd_spl_procTiles");
		}
		
		@RequestMapping(value = "/search_opd_spl_report", method = RequestMethod.POST)
		public ModelAndView search_opd_spl_report(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("cmd1") String cmd1,String qtr1,String yr1){
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
			Boolean val = roledao.ScreenRedirect("opd_spl_proc", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			
			 if(validation.checkYearLength(yr1)  == false){
				 model.put("msg",validation.yearMSG);
		             return new ModelAndView("redirect:opd_spl_proc");
		         }
			 
			model.put("r_1", l1);
		 	model.put("cmd1", cmd1);
			model.put("qtr1", qtr1);
			model.put("yr1", yr1);
		 	List<Map<String, Object>> list = mnh7_Dao.search_opd_spl_proc_report(cmd1, qtr1, yr1); 
		 	model.put("list", list);
		 	model.put("size", list.size());
		 	model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		 	model.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
			return new ModelAndView("opd_spl_procTiles");

		}
}
*/