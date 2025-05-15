package com.controller.tms;

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
import com.dao.tms.vehicleDetailsDAO;
import com.dao.tms.vehiclestatus_nodaldteDAO;
import com.models.Tbl_CodesForm;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Vehicle_Status_Nodal_DteController {

	@Autowired
	private vehiclestatus_nodaldteDAO vehiclestatusDAO; 

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	ReportsController rcont = new ReportsController();
	
	@RequestMapping(value = "/admin/vehicle_status_nodal_dte", method = RequestMethod.GET)
	public ModelAndView vehicle_status_nodal_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("vehicle_status_nodal_dte", roleid);	
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
			list = vehiclestatusDAO.vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,prf_code2,"A",sus_no12,line_dte_sus12);
			Mmap.put("list_A", list);	
		}
		if(type_veh2 == 1) {  // B Veh
			list = vehiclestatusDAO.vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,prf_code2,"B",sus_no12,line_dte_sus12);
			Mmap.put("list_B", list);	
		}
		if(type_veh2 == 2) {  // C Veh
			list = vehiclestatusDAO.vehiclestatusDetails_nodal_dte(formCode,mct_main_list12,prf_code2,"C",sus_no12,line_dte_sus12);
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
		return new ModelAndView("vehicle_statusNodalDteTile");
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
	
	
	@RequestMapping(value = "/admin/vehicle_status_details_nodal_dte", method = RequestMethod.POST)
	public ModelAndView vehicle_status_details_nodal_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
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
		
	 	return new ModelAndView("redirect:vehicle_status_nodal_dte");
	}
	
	@RequestMapping(value = "/getMCtMain_from_prf_codenodal_dte", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMCtMain_from_prf_codenodal_dte(HttpSession sessionA,
			@RequestParam  String type_veh,@RequestParam String line_dte_sus) {
		if(!type_veh.equals("") && !line_dte_sus.equals("0")) {
			return vehiclestatusDAO.getMCtMain_from_prf_codenodal_dte(type_veh,sessionA,line_dte_sus);
		}else {
			return null;
		}
	}
}