package com.controller.tms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExportCSVFileController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MVCRDAO;
import com.dao.tms.abc_vehicleDetailsDAO;
import com.dao.tms.vehicleDetailsDAO;
import com.dao.tms.vehiclestatus_nodaldteDAO;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Vehicle_StatusController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 
	
	@Autowired
	private vehicleDetailsDAO vehiclestatusDAO; 
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	ReportsController rcont = new ReportsController();
	
	@Autowired
	private abc_vehicleDetailsDAO abc_vehiclestatusDAO; 
	
	@RequestMapping(value = "/admin/vehicle_status_line_dte", method = RequestMethod.GET)
	public ModelAndView vehicle_status_line_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
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
				
				List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",formation_code+"00");
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",formation_code+"00000");
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
				
				
				
				
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
			}
		}else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("selectLine_dte",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}else if(roleAccess.equals("Line_dte")){
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
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
		}else{
			type_veh2 = 0;
			prf_code2 = "";
			/*mct_main2 = "";*/
			sus_no12= "";
			unit_name12="";
			cont_comd12 = "";
			cont_corps12 = "";
			cont_div12 ="";
			cont_bde12="";
			line_dte_sus12 = "";
			mct_main_list12 = "";
		}
		if(msg != null && msg.equals("ok")) {
			msg = "";
		}
		Mmap.put("msg", msg);	
		
		ArrayList<ArrayList<String>> list = null;
		if(type_veh2 == 0) { // A Veh
			list = vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"A",sus_no12,line_dte_sus12);
			Mmap.put("list_A", list);	
		}
		if(type_veh2 == 1) {  // B Veh
			list = vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"B",sus_no12,line_dte_sus12);
			Mmap.put("list_B", list);	
		}
		if(type_veh2 == 2) {  // C Veh
			list = vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"C",sus_no12,line_dte_sus12);
			Mmap.put("list_C", list);	
		}
		
	 	if(list.size() > 0){
	 		int sumUE = 0;
			int againUE = 0;
			int overUE = 0;
			int loan = 0;
			int sector = 0;
			int acsfp = 0;
			int pbd = 0;
			int fresh_release = 0;
			int gift = 0;
			int totalUH = 0;
			int defi = 0;
			int surplus = 0;
			int op = 0;
			int trg = 0;
			int wwr = 0;
			int opwks = 0;
			int other = 0;
			for(int i=0;i<list.size();i++) {
				String sumUE1 = list.get(i).get(6);
				if(sumUE1 == null) {
					sumUE1 = "0";
				}
				sumUE = sumUE + Integer.parseInt(sumUE1);
				againUE = againUE + Integer.parseInt( list.get(i).get(7)) + Integer.parseInt( list.get(i).get(8));
				overUE = overUE + Integer.parseInt(list.get(i).get(9)) + Integer.parseInt( list.get(i).get(10));
				loan = loan + Integer.parseInt(list.get(i).get(11)) + Integer.parseInt( list.get(i).get(12));
				sector = sector + Integer.parseInt(list.get(i).get(13)) +Integer.parseInt( list.get(i).get(14));
				acsfp = acsfp + Integer.parseInt(list.get(i).get(15))+Integer.parseInt( list.get(i).get(16));
				pbd = pbd + Integer.parseInt(list.get(i).get(17))+Integer.parseInt( list.get(i).get(18));
				fresh_release = fresh_release + Integer.parseInt(list.get(i).get(19));
				gift = gift + Integer.parseInt(list.get(i).get(20)) + Integer.parseInt(list.get(i).get(21));
				totalUH = totalUH + Integer.parseInt(list.get(i).get(24));
				defi = defi + Integer.parseInt(list.get(i).get(25));
				surplus = surplus + Integer.parseInt(list.get(i).get(26));
				op = op + Integer.parseInt(list.get(i).get(27))+Integer.parseInt( list.get(i).get(28));
				trg = trg + Integer.parseInt(list.get(i).get(29))+Integer.parseInt( list.get(i).get(30));
				wwr = wwr + Integer.parseInt(list.get(i).get(31))+Integer.parseInt( list.get(i).get(32));
				opwks = opwks + Integer.parseInt(list.get(i).get(33))+Integer.parseInt( list.get(i).get(34));
				other = other + Integer.parseInt(list.get(i).get(35))+Integer.parseInt( list.get(i).get(36));
			}
			Mmap.put("sumUE",sumUE);
			Mmap.put("againUE",againUE);
			Mmap.put("overUE",overUE);
			Mmap.put("loan",loan);
			Mmap.put("sector",sector);
			Mmap.put("acsfp",acsfp);
			Mmap.put("pbd",pbd);
			Mmap.put("fresh_release",fresh_release);
			Mmap.put("gift",gift);
			Mmap.put("totalUH",totalUH);
			Mmap.put("defi",defi);
			Mmap.put("surplus",surplus);
			Mmap.put("op",op);
			Mmap.put("trg",trg);
			Mmap.put("wwr",wwr);
			Mmap.put("opwks",opwks);
			Mmap.put("other",other);
		}
		return new ModelAndView("vehicle_status_line_dteTile");
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
	String type_of_force2 = "";
	
	@RequestMapping(value = "/admin/vehicle_status_details_line_dte", method = RequestMethod.POST)
	public ModelAndView vehicle_status_details_line_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_veh1", required = false) int type_veh,
			@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
			@RequestParam(value = "cont_div1", required = false) String cont_div1,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
			@RequestParam(value = "mct_main_list1", required = false) String mct_main_list1)
	
	
	{	
		type_veh2 = type_veh;
		prf_code2 = prf_code;
		sus_no12= sus_no1;
		unit_name12= unit_name1;
		cont_comd12 = cont_comd1;
		cont_corps12 = cont_corps1;
		cont_div12 = cont_div1;
		cont_bde12= cont_bde1;
		line_dte_sus12 = line_dte_sus1;
		mct_main_list12 = mct_main_list1;
		Mmap.put("msg", "ok");
		
	 	return new ModelAndView("redirect:vehicle_status_line_dte");
	}
	
	@RequestMapping(value = "/getMCtMain_from_prf_code", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMCtMain_from_Type_of_Veh(HttpSession sessionA,
			@RequestParam String prf_code) {
		if(!prf_code.equals("")) {
			return vehiclestatusDAO.getMCtMain_from_Type_of_Veh(prf_code,sessionA);
		}else {
			return null;
		}
	}
	
	
	// For Line Dte coas
	@RequestMapping(value = "/admin/vehicle_status_line_dte_coas", method = RequestMethod.GET)
	public ModelAndView vehicle_status_line_dte_coas(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte_coas", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(!roleAccess.equals("MISO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());
		String selectComd ="<option value=''>-- Select --</option>";
		String select="<option value='0'>-- Select --</option>";
		Mmap.put("selectcomd", selectComd);
		Mmap.put("selectcorps",select);
		Mmap.put("selectDiv",select);
		Mmap.put("selectBde",select);
		if(roleAccess.equals("Line_dte")) {
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		return new ModelAndView("vehicle_statusLineDte_coasTile");
	}
	@RequestMapping(value = "/vehicle_status_line_dte_coasList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> vehicle_status_line_dte_coasList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			int type_veh,String prf_code,String mct_main,String cont_comd,String cont_corps,String cont_div,String cont_bde,String sus_no_list,String line_dte_sus,HttpSession session){
		Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte_coas", session.getAttribute("roleid").toString());
		if (val == false) {
			return null;
		}else {
			if(type_veh != 0 & type_veh != 1 & type_veh != 2) {
				return null;
			}else if(prf_code.equals("")){
				return null;
			}else{
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Line_dte")) {
					line_dte_sus = roleSusNo;
				}
				
				String formCode ="";
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					formCode = cont_bde;
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			formCode = cont_div;
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			formCode = cont_corps;
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			formCode = cont_comd;
					    	}
				    	}
				    }
			    }
				if(type_veh == 0) { // A Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"A",sus_no_list,line_dte_sus);
				}
				else if(type_veh == 1) {  // B Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"B",sus_no_list,line_dte_sus);
				}
				else if(type_veh == 2) {  // C Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"C",sus_no_list,line_dte_sus);
				}else {
					return null;
				}
			}
		}
	}
	@RequestMapping(value = "/vehicle_status_line_dte_coasCount", method = RequestMethod.POST)
	public @ResponseBody long vehicle_status_line_dte_coasCount(HttpSession sessionUserId,String Search,int type_veh,String prf_code,String mct_main,String cont_comd,String cont_corps,String cont_div,String cont_bde,String sus_no_list,String line_dte_sus){
		Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte_coas", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return 0;
		}else {
			if(type_veh != 0 & type_veh != 1 & type_veh != 2) {
				return 0;
			}else if(prf_code.equals("")){
				return 0;
			}else{
				
				String formCode ="";
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					formCode = cont_bde;
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			formCode = cont_div;
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			formCode = cont_corps;
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			formCode = cont_comd;
					    	}
				    	}
				    }
			    }
				if(type_veh == 0) { // A Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas_Count(Search,formCode,mct_main,prf_code,"A",sus_no_list,line_dte_sus);
				}
				else if(type_veh == 1) {  // B Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas_Count(Search,formCode,mct_main,prf_code,"B",sus_no_list,line_dte_sus);
				}
				else if(type_veh == 2) {  // C Veh
					return vehiclestatusDAO.vehiclestatusDetails_line_dte_coas_Count(Search,formCode,mct_main,prf_code,"C",sus_no_list,line_dte_sus);
				}else {
					return 0;
				}
			}
		}
	}
	
	private ExportCSVFileController csv = new ExportCSVFileController();
	
	@RequestMapping(value = "/vehicle_status_line_dte_coasExport", method = RequestMethod.POST)
    public void vehicle_status_line_dte_coasExport(HttpServletResponse response,HttpSession session,
    		int type_veh1,String prf_code1,String mct_main1,String cont_comd1,String cont_corps1,
    		String cont_div1,String cont_bde1,String sus_no_list1,String line_dte_sus1) {
		Boolean val = roledao.ScreenRedirect("vehicle_status_line_dte_coas", session.getAttribute("roleid").toString());
		if (val == false) {
			
		}else {
			if(type_veh1 != 0 & type_veh1 != 1 & type_veh1 != 2) {
			
			}else if(prf_code1.equals("")){
			
			}else{
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Line_dte")) {
					line_dte_sus1 = roleSusNo;
				}
				
				String formCode ="";
				if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
					formCode = cont_bde1;
		    	}else {
		    		if(!cont_div1.equals("0") && !cont_div1.equals("")){
		    			formCode = cont_div1;
			    	}else {
			    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
			    			formCode = cont_corps1;
				    	}else {
				    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
				    			formCode = cont_comd1;
					    	}
				    	}
				    }
			    }
				List<Map<String, Object>> list = null;
				if(type_veh1 == 0) { // A Veh
					list = vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(0,"ALL","","1","asc",formCode,mct_main1,prf_code1,"A",sus_no_list1,line_dte_sus1);
				}
				else if(type_veh1 == 1) { // B Veh
					list = vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(0,"ALL","","1","asc",formCode,mct_main1,prf_code1,"B",sus_no_list1,line_dte_sus1);
				}
				else if(type_veh1 == 2) {  // C Veh
					list = vehiclestatusDAO.vehiclestatusDetails_line_dte_coas(0,"ALL","","1","asc",formCode,mct_main1,prf_code1,"C",sus_no_list1,line_dte_sus1);
				}
				String[] csvHeader1 = {"COMMAND", "CORPS", "DIVISION", "BRIGADE", "UNIT NAME", "MCT NOMEN", "UE", "AGAINST UE", "OVER UE", "LOAN", "SECTOR STORE", "ACSFP", "TOTAL UH", "GIFT"};
				String[] nameMapping = {"comd", "corps", "div","bde","unit_name","mct_nomen","ue","against_ue","over_ue","loan","sec_store","acsfp","total_uh","gift"};
				csv.exportToCSV_Map_String(response,list,csvHeader1,nameMapping);
			}
		}
	}
	//for Line Dte
	
	
	
	
	////bisag 230523 v2 (seprate c veh new enhacement)
	
	@RequestMapping(value = "/admin/c_vehicle_status_line_dte", method = RequestMethod.GET)
	public ModelAndView c_vehicle_status_line_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
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
			}
		}else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("selectLine_dte",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}else if(roleAccess.equals("Line_dte")){
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
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
		}else{
			type_veh2 = 0;
			prf_code2 = "";
			/*mct_main2 = "";*/
			sus_no12= "";
			unit_name12="";
			cont_comd12 = "";
			cont_corps12 = "";
			cont_div12 ="";
			cont_bde12="";
			line_dte_sus12 = "";
			mct_main_list12 = "";
		}
		if(msg != null && msg.equals("ok")) {
			msg = "";
		}
		Mmap.put("msg", msg);	
		
		ArrayList<ArrayList<String>> list = null;
		
		  if(type_veh2 == 0) {  //A Veh 
		   list = vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12, prf_code2,"A",sus_no12,line_dte_sus12); 
		   Mmap.put("list_A", list); }
		  if(type_veh2 == 1) { // B Veh 
		 list =vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"B",sus_no12,line_dte_sus12); 
		 Mmap.put("list_B", list); 
		 }
		 
		if(type_veh2 == 2) {  // C Veh
			list = vehiclestatusDAO.vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"C",sus_no12,line_dte_sus12);
			Mmap.put("list_C", list);	
		}
		
	 	if(list.size() > 0){
	 		int sumUE = 0;
			int againUE = 0;
			int overUE = 0;
			int loan = 0;
			int sector = 0;
			int acsfp = 0;
			int pbd = 0;
			int fresh_release = 0;
			int gift = 0;
			int totalUH = 0;
			int defi = 0;
			int surplus = 0;
			int op = 0;
			int trg = 0;
			int wwr = 0;
			int opwks = 0;
			int other = 0;
			for(int i=0;i<list.size();i++) {
				String sumUE1 = list.get(i).get(6);
				if(sumUE1 == null) {
					sumUE1 = "0";
				}
				sumUE = sumUE + Integer.parseInt(sumUE1);
				againUE = againUE + Integer.parseInt( list.get(i).get(7)) + Integer.parseInt( list.get(i).get(8));
				overUE = overUE + Integer.parseInt(list.get(i).get(9)) + Integer.parseInt( list.get(i).get(10));
				loan = loan + Integer.parseInt(list.get(i).get(11)) + Integer.parseInt( list.get(i).get(12));
				sector = sector + Integer.parseInt(list.get(i).get(13)) +Integer.parseInt( list.get(i).get(14));
				acsfp = acsfp + Integer.parseInt(list.get(i).get(15))+Integer.parseInt( list.get(i).get(16));
				pbd = pbd + Integer.parseInt(list.get(i).get(17))+Integer.parseInt( list.get(i).get(18));
				fresh_release = fresh_release + Integer.parseInt(list.get(i).get(19));
				gift = gift + Integer.parseInt(list.get(i).get(20)) + Integer.parseInt(list.get(i).get(21));
				totalUH = totalUH + Integer.parseInt(list.get(i).get(24));
				defi = defi + Integer.parseInt(list.get(i).get(25));
				surplus = surplus + Integer.parseInt(list.get(i).get(26));
				op = op + Integer.parseInt(list.get(i).get(27))+Integer.parseInt( list.get(i).get(28));
				trg = trg + Integer.parseInt(list.get(i).get(29))+Integer.parseInt( list.get(i).get(30));
				wwr = wwr + Integer.parseInt(list.get(i).get(31))+Integer.parseInt( list.get(i).get(32));
				opwks = opwks + Integer.parseInt(list.get(i).get(33))+Integer.parseInt( list.get(i).get(34));
				other = other + Integer.parseInt(list.get(i).get(35))+Integer.parseInt( list.get(i).get(36));
			}
			Mmap.put("sumUE",sumUE);
			Mmap.put("againUE",againUE);
			Mmap.put("overUE",overUE);
			Mmap.put("loan",loan);
			Mmap.put("sector",sector);
			Mmap.put("acsfp",acsfp);
			Mmap.put("pbd",pbd);
			Mmap.put("fresh_release",fresh_release);
			Mmap.put("gift",gift);
			Mmap.put("totalUH",totalUH);
			Mmap.put("defi",defi);
			Mmap.put("surplus",surplus);
			Mmap.put("op",op);
			Mmap.put("trg",trg);
			Mmap.put("wwr",wwr);
			Mmap.put("opwks",opwks);
			Mmap.put("other",other);
		}
		return new ModelAndView("c_vehicle_status_line_dteTile");
	}
	
 @RequestMapping(value = "/admin/c_vehicle_status_details_line_dte", method = RequestMethod.POST)
	public ModelAndView c_vehicle_status_details_line_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_veh1", required = false) int type_veh,
			@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
			@RequestParam(value = "cont_div1", required = false) String cont_div1,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
			@RequestParam(value = "mct_main_list1", required = false) String mct_main_list1)
		
	
	{	
		type_veh2 = type_veh;
		prf_code2 = prf_code;
		sus_no12= sus_no1;
		unit_name12= unit_name1;
		cont_comd12 = cont_comd1;
		cont_corps12 = cont_corps1;
		cont_div12 = cont_div1;
		cont_bde12= cont_bde1;
		line_dte_sus12 = line_dte_sus1;
		mct_main_list12 = mct_main_list1;
		Mmap.put("msg", "ok");
		
	 	return new ModelAndView("redirect:c_vehicle_status_line_dte");
	}
		
		
		
 int kms2 = 0;
	int vintage2 = 0;





	@RequestMapping(value = "/admin/abc_vehicle_details_line_dte", method = RequestMethod.GET)
    public ModelAndView abc_vehicle_status_line_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
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
                            
                            Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                            
                            Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                            
                            Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                            Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
                    }
            }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
                    List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                    Mmap.put("getCommandList",comd);
                    String selectComd ="<option value=''>--Select--</option>";
                    String select="<option value='0'>--Select--</option>";
                    Mmap.put("selectcomd", selectComd);
                    Mmap.put("selectcorps",select);
                    Mmap.put("selectDiv",select);
                    Mmap.put("selectBde",select);
                    Mmap.put("selectLine_dte",select);
                    Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
                    Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
            }else if(roleAccess.equals("Line_dte")){
                    List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                    Mmap.put("getCommandList",comd);
                    String selectComd ="<option value=''>--Select--</option>";
                    String select="<option value='0'>--Select--</option>";
                    Mmap.put("selectcomd", selectComd);
                    Mmap.put("selectcorps",select);
                    Mmap.put("selectDiv",select);
                    Mmap.put("selectBde",select);
                    Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
                    Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                    Mmap.put("kms1",kms2);
                    Mmap.put("vintag1",vintage2);
                    Mmap.put("cont_div1",cont_div12);
                    Mmap.put("cont_bde1",cont_bde12);
                    Mmap.put("line_dte_sus1",line_dte_sus12);
                    Mmap.put("mct_main_list1",mct_main_list12);
                    Mmap.put("type_of_force",type_of_force2);
            }else{
                    type_veh2 = 0;
                    prf_code2 = "";
                    /*mct_main2 = "";*/
                    sus_no12= "";
                    unit_name12="";
                    cont_comd12 = "";
                    cont_corps12 = "";
                    kms2 = 0; 
                    vintage2 = 0; 
                    cont_div12 ="";
                    cont_bde12="";
                    line_dte_sus12 = "";
                    mct_main_list12 = "";
                    type_of_force2="";
            }
            if(msg != null && msg.equals("ok")) {
                    msg = "";
            }
            Mmap.put("msg", msg);        
            
            ArrayList<ArrayList<String>>  list = null;
            
              if(type_veh2 == 0) {  //A Veh 
               list = abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"A",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
               Mmap.put("list_A", list); 
              
              }
              if(type_veh2 == 1) { // B Veh 
             list =abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"B",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
             Mmap.put("list_B", list); 
             
             }
             
            if(type_veh2 == 2) {  // C Veh
                    list = abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"C",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2);
                    Mmap.put("list_C", list);        
                    
            }
            
            
            return new ModelAndView("abc_vehicle_status_line_dteTile");
    }
    
@RequestMapping(value = "/admin/abc_vehicle_status_details_line_dte", method = RequestMethod.POST)
    public ModelAndView abc_vehicle_status_details_line_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
                    @RequestParam(value = "type_veh1", required = false) int type_veh,
                    @RequestParam(value = "prf_code1", required = false) String prf_code,
                    @RequestParam(value = "sus_no1", required = false) String sus_no1,
                    @RequestParam(value = "unit_name1", required = false) String unit_name1,
                    @RequestParam(value = "kms1", required = false) int kms1,
                    @RequestParam(value = "vintag1", required = false) int vintage1,

                    @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
                    @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
                    @RequestParam(value = "cont_div1", required = false) String cont_div1,
                    @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
                    @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
                    @RequestParam(value = "mct_main_list1", required = false) String mct_main_list1,
        			@RequestParam(value = "type_of_force1", required = false) String type_of_force1)
    
    
    
    {        
            type_veh2 = type_veh;
            System.out.println("hello type of vehicle   "+ type_veh);
            prf_code2 = prf_code;
            sus_no12= sus_no1;
            unit_name12= unit_name1;
            cont_comd12 = cont_comd1;
            cont_corps12 = cont_corps1;
            kms2 = kms1;
            vintage2 = vintage1;

            cont_div12 = cont_div1;
            cont_bde12= cont_bde1;
            line_dte_sus12 = line_dte_sus1;
            mct_main_list12 = mct_main_list1;
            type_of_force2 = type_of_force1;
            System.out.println("hello type of"+type_of_force1);
            System.out.println("hello type of"+type_of_force1);
            Mmap.put("msg", "ok");
            
             return new ModelAndView("redirect:abc_vehicle_details_line_dte");
    }


//added by Mitesh 02-08-24

@RequestMapping(value = "/admin/abc_vehicle_status_nodal_dte", method = RequestMethod.GET)
public ModelAndView abc_vehicle_status_nodal_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
                }
        }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
                List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                Mmap.put("getCommandList",comd);
                String selectComd ="<option value=''>--Select--</option>";
                String select="<option value='0'>--Select--</option>";
                Mmap.put("selectcomd", selectComd);
                Mmap.put("selectcorps",select);
                Mmap.put("selectDiv",select);
                Mmap.put("selectBde",select);
                Mmap.put("selectLine_dte",select);
                Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
                Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
        }else if(roleAccess.equals("Line_dte")){
                List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                Mmap.put("getCommandList",comd);
                String selectComd ="<option value=''>--Select--</option>";
                String select="<option value='0'>--Select--</option>";
                Mmap.put("selectcomd", selectComd);
                Mmap.put("selectcorps",select);
                Mmap.put("selectDiv",select);
                Mmap.put("selectBde",select);
                Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
                Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                Mmap.put("kms1",kms2);
                Mmap.put("vintag1",vintage2);
                Mmap.put("cont_div1",cont_div12);
                Mmap.put("cont_bde1",cont_bde12);
                Mmap.put("line_dte_sus1",line_dte_sus12);
                Mmap.put("mct_main_list1",mct_main_list12);
                Mmap.put("type_of_force",type_of_force2);
        }else{
                type_veh2 = 0;
                prf_code2 = "";
                /*mct_main2 = "";*/
                sus_no12= "";
                unit_name12="";
                cont_comd12 = "";
                cont_corps12 = "";
                kms2 = 0; 
                vintage2 = 0; 
                cont_div12 ="";
                cont_bde12="";
                line_dte_sus12 = "";
                mct_main_list12 = "";
                type_of_force2="";
        }
        if(msg != null && msg.equals("ok")) {
                msg = "";
        }
        Mmap.put("msg", msg);        
        
        ArrayList<ArrayList<String>>  list11 = null;
        
          if(type_veh2 == 0) {  //A Veh 
           list11 = abc_vehiclestatusDAO.abc_vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,"A",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
           Mmap.put("list_A", list11); 
          
          }
          if(type_veh2 == 1) { // B Veh 
         list11 =abc_vehiclestatusDAO.abc_vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,"B",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
         Mmap.put("list_B", list11); 
         
         }
         
        if(type_veh2 == 2) {  // C Veh
                list11 = abc_vehiclestatusDAO.abc_vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,"C",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2);
                Mmap.put("list_C", list11);        
                
        }
        
        
        return new ModelAndView("abc_vehicle_status_nodal_dteTile");
}

@RequestMapping(value = "/admin/abc_vehicle_status_details_nodal_dte", method = RequestMethod.POST)
public ModelAndView abc_vehicle_status_details_nodal_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
                @RequestParam(value = "type_veh1", required = false) int type_veh,
                @RequestParam(value = "prf_code1", required = false) String prf_code,
                @RequestParam(value = "sus_no1", required = false) String sus_no1,
                @RequestParam(value = "unit_name1", required = false) String unit_name1,
                @RequestParam(value = "kms1", required = false) int kms1,
                @RequestParam(value = "vintag1", required = false) int vintage1,

                @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
                @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
                @RequestParam(value = "cont_div1", required = false) String cont_div1,
                @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
                @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
                @RequestParam(value = "mct_main_list1", required = false) String mct_main_list1,
    			@RequestParam(value = "type_of_force1", required = false) String type_of_force1)



{        
        type_veh2 = type_veh;
        System.out.println("hello type of vehicle   "+ type_veh);
        prf_code2 = prf_code;
        sus_no12= sus_no1;
        unit_name12= unit_name1;
        cont_comd12 = cont_comd1;
        cont_corps12 = cont_corps1;
        kms2 = kms1;
        vintage2 = vintage1;

        cont_div12 = cont_div1;
        cont_bde12= cont_bde1;
        line_dte_sus12 = line_dte_sus1;
        mct_main_list12 = mct_main_list1;
        type_of_force2 = type_of_force1;
        System.out.println("hello type of"+type_of_force1);
        System.out.println("hello type of"+type_of_force1);
        Mmap.put("msg", "ok");
        
         return new ModelAndView("redirect:abc_vehicle_status_nodal_dte");
}



///new enhancement for c vehicle created by mitesh (25-09-2024) 

@RequestMapping(value = "/admin/c_veh_details_line_dte", method = RequestMethod.GET)
public ModelAndView c_veh_status_line_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                        Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
                }
        }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
                List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                Mmap.put("getCommandList",comd);
                String selectComd ="<option value=''>--Select--</option>";
                String select="<option value='0'>--Select--</option>";
                Mmap.put("selectcomd", selectComd);
                Mmap.put("selectcorps",select);
                Mmap.put("selectDiv",select);
                Mmap.put("selectBde",select);
                Mmap.put("selectLine_dte",select);
                Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
                Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
        }else if(roleAccess.equals("Line_dte")){
                List<Tbl_CodesForm> comd=all.getCommandDetailsList();
                Mmap.put("getCommandList",comd);
                String selectComd ="<option value=''>--Select--</option>";
                String select="<option value='0'>--Select--</option>";
                Mmap.put("selectcomd", selectComd);
                Mmap.put("selectcorps",select);
                Mmap.put("selectDiv",select);
                Mmap.put("selectBde",select);
                Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
                Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
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
                Mmap.put("kms1",kms2);
                Mmap.put("vintag1",vintage2);
                Mmap.put("cont_div1",cont_div12);
                Mmap.put("cont_bde1",cont_bde12);
                Mmap.put("line_dte_sus1",line_dte_sus12);
                Mmap.put("mct_main_list1",mct_main_list12);
                Mmap.put("type_of_force",type_of_force2);
        }else{
                type_veh2 = 0;
                prf_code2 = "";
                /*mct_main2 = "";*/
                sus_no12= "";
                unit_name12="";
                cont_comd12 = "";
                cont_corps12 = "";
                kms2 = 0; 
                vintage2 = 0; 
                cont_div12 ="";
                cont_bde12="";
                line_dte_sus12 = "";
                mct_main_list12 = "";
                type_of_force2="";
        }
        if(msg != null && msg.equals("ok")) {
                msg = "";
        }
        Mmap.put("msg", msg);        
        
        ArrayList<ArrayList<String>>  list = null;
        
          if(type_veh2 == 0) {  //A Veh 
           list = abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"A",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
           Mmap.put("list_A", list); 
          
          }
          if(type_veh2 == 1) { // B Veh 
         list =abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"B",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2); 
         Mmap.put("list_B", list); 
         
         }
         
        if(type_veh2 == 2) {  // C Veh
                list = abc_vehiclestatusDAO.abc_vehiclestatusDetails_line_dte(formCode,mct_main_list12,prf_code2,"C",sus_no12,line_dte_sus12,kms2,vintage2,type_of_force2);
                Mmap.put("list_C", list);        
                
        }
        
        
        return new ModelAndView("c_veh_status_line_dteTile");
}

@RequestMapping(value = "/admin/c_veh_status_details_line_dte", method = RequestMethod.POST)
public ModelAndView c_veh_status_details_line_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
                @RequestParam(value = "type_veh1", required = false) int type_veh,
                @RequestParam(value = "prf_code1", required = false) String prf_code,
                @RequestParam(value = "sus_no1", required = false) String sus_no1,
                @RequestParam(value = "unit_name1", required = false) String unit_name1,
                @RequestParam(value = "kms1", required = false) int kms1,
                @RequestParam(value = "vintag1", required = false) int vintage1,

                @RequestParam(value = "cont_comd1", required = false) String cont_comd1,
                @RequestParam(value = "cont_corps1", required = false) String cont_corps1,
                @RequestParam(value = "cont_div1", required = false) String cont_div1,
                @RequestParam(value = "cont_bde1", required = false) String cont_bde1,
                @RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
                @RequestParam(value = "mct_main_list1", required = false) String mct_main_list1,
    			@RequestParam(value = "type_of_force1", required = false) String type_of_force1)



{        
        type_veh2 = type_veh;
        System.out.println("hello type of vehicle   "+ type_veh);
        prf_code2 = prf_code;
        sus_no12= sus_no1;
        unit_name12= unit_name1;
        cont_comd12 = cont_comd1;
        cont_corps12 = cont_corps1;
        kms2 = kms1;
        vintage2 = vintage1;

        cont_div12 = cont_div1;
        cont_bde12= cont_bde1;
        line_dte_sus12 = line_dte_sus1;
        mct_main_list12 = mct_main_list1;
        type_of_force2 = type_of_force1;
        System.out.println("hello type of"+type_of_force1);
        System.out.println("hello type of"+type_of_force1);
        Mmap.put("msg", "ok");
        
         return new ModelAndView("redirect:c_veh_details_line_dte");
}




}



	
	 
	 
	