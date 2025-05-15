package com.controller.cue;

import java.sql.SQLException;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.controller.tms.Common_Mct_MainController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.vehicleDetailsDAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TransportController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private vehicleDetailsDAO vehiclestatusDAO;

	ReportsController rcont = new ReportsController();

	ValidationController validation = new ValidationController();

	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();

	Common_Mct_MainController force = new Common_Mct_MainController();

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
	String type_force12 = "";
	String eff_from12 = "";
	String eff_to12 = "";
	String modification12 = "";
	String we_pe_no12 = "";
	String table_title12 = "";
	String std_nomclature12 = "";

	@RequestMapping(value = "/admin/ue_of_transport", method = RequestMethod.GET)
	public ModelAndView ue_of_transport(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ue_of_transport", roleid);
		/*if(val == false) {
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

				List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",formation_code+"00");
				Mmap.put("getDivList",Bn);

				List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",formation_code+"00000");
				Mmap.put("getBdeList",bde);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectForce_type",select);



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
			//Mmap.put("getForceTypeList",force.getForceTypeList());
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
			//Mmap.put("getForceTypeList",force.getForceTypeList());
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
		//		if(msg != null && msg.equals("ok")){
		//			Mmap.put("type_veh", type_veh2);
		//			Mmap.put("prf_code", prf_code2);
		//			Mmap.put("sus_no1",sus_no12);
		//			Mmap.put("unit_name1",unit_name12);
		//			Mmap.put("cont_comd1",cont_comd12);
		//			Mmap.put("cont_corps1",cont_corps12);
		//			Mmap.put("cont_div1",cont_div12);
		//			Mmap.put("cont_bde1",cont_bde12);
		//			Mmap.put("line_dte_sus1",line_dte_sus12);
		//			Mmap.put("mct_main_list1",mct_main_list12);
		//			Mmap.put("type_force1",type_force12);
		//
		//			Mmap.put("eff_from1",eff_from12);
		//			Mmap.put("eff_to1",eff_to12);
		//			Mmap.put("modification1",modification12);
		//			Mmap.put("we_pe_no1",we_pe_no12);
		//			Mmap.put("table_title1",table_title12);
		//			Mmap.put("std_nomclature",std_nomclature12);
		//		}else{
		//			type_veh2 = 0;
		//			prf_code2 = "";
		//			sus_no12= "";
		//			unit_name12="";
		//			cont_comd12 = "";
		//			cont_corps12 = "";
		//			cont_div12 ="";
		//			cont_bde12="";
		//			line_dte_sus12 = "";
		//			mct_main_list12 = "";
		//			type_force12 = "";
		//			eff_from12 = "";
		//			eff_to12 = "";
		//			modification12 = "";
		//			we_pe_no12 = "";
		//			table_title12 = "";
		//			std_nomclature12 = "";
		//		}
		if(msg != null && msg.equals("ok")) {
			msg = "";
		}
		Mmap.put("msg", msg);

		//		ArrayList<ArrayList<String>> list = null;
		//			list = vehiclestatusDAO.transport_ue(formCode,mct_main_list12,prf_code2,sus_no12,line_dte_sus12,type_force12,
		//					eff_from12, eff_to12, modification12, we_pe_no12, table_title12,std_nomclature12);
		//			Mmap.put("list_B", list);
		//
		//
		//	 	if(list.size() > 0){
		//	 		int sumUE = 0;
		//			for(int i=0;i<list.size();i++) {
		//				String sumUE1 = list.get(i).get(3);
		//				if(sumUE1 == null) {
		//					sumUE1 = "0";
		//				}
		//				sumUE = sumUE + Integer.parseInt(sumUE1);
		//
		//
		//			}
		//			Mmap.put("sumUE",sumUE);
		//		}
		return new ModelAndView("ue_of_transportTile");
	}









	@RequestMapping(value = "/ue_of_transportTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> ue_of_transportTable(@RequestParam Map<String, String> formData,@RequestParam(value = "std_nomclature[]", required = false) String[] std_nomclature,
			HttpSession sessionUserId, ModelMap Mmap, HttpSession session, HttpServletRequest request) throws SQLException {

		String msg = "";
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		// Extract values from formData
		Integer startPage = Integer.parseInt(formData.get("startPage"));
		String pageLength = formData.get("pageLength");
		String search = formData.get("Search");
		String orderColumn = formData.get("orderColunm");
		String orderType = formData.get("orderType");

		// Get form-specific fields
		String prf_code = formData.get("prf_code");
		String sus_no = formData.get("sus_no");
		String line_dte_sus = formData.get("line_dte_sus");
		String type_force = formData.get("type_force");
		String eff_from = formData.get("eff_from");
		String eff_to = formData.get("eff_to");
		String modification = formData.get("modification");
		String we_pe_no = formData.get("we_pe_no");
		String table_title = formData.get("table_title");
		String std_nomclatureStr = std_nomclature != null ? String.join(",", std_nomclature) : "";

		// Get formation-related fields
		String cont_bde = formData.get("cont_bde");
		String cont_div = formData.get("cont_div");
		String cont_corps = formData.get("cont_corps");
		String cont_comd = formData.get("cont_comd");

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

				List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",formation_code+"00");
				Mmap.put("getDivList",Bn);

				List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",formation_code+"00000");
				Mmap.put("getBdeList",bde);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectForce_type",select);



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
		return vehiclestatusDAO.transport_ue(startPage, pageLength, search, orderColumn, orderType, cont_comd, cont_corps, cont_div, cont_bde,
				prf_code,sus_no,line_dte_sus,type_force,eff_from,eff_to,
				modification,we_pe_no,table_title,std_nomclatureStr);
	}

	@RequestMapping(value = "/ue_of_transportCount", method = RequestMethod.POST)
	public @ResponseBody long ue_of_transportCount(@RequestParam Map<String, String> formData, HttpSession sessionUserId, ModelMap Mmap,
			@RequestParam(value = "std_nomclature[]", required = false) String[] std_nomclature,String Search,
			HttpSession session, HttpServletRequest request) throws SQLException {

		// Get form-specific fields
		String prf_code = formData.get("prf_code");
		String sus_no = formData.get("sus_no");
		String line_dte_sus = formData.get("line_dte_sus");
		String type_force = formData.get("type_force");
		String eff_from = formData.get("eff_from");
		String eff_to = formData.get("eff_to");
		String modification = formData.get("modification");
		String we_pe_no = formData.get("we_pe_no");
		String table_title = formData.get("table_title");
		String std_nomclatureStr = std_nomclature != null ? String.join(",", std_nomclature) : "";

		// Get formation-related fields
		String cont_bde = formData.get("cont_bde");
		String cont_div = formData.get("cont_div");
		String cont_corps = formData.get("cont_corps");
		String cont_comd = formData.get("cont_comd");

		String roleType = sessionUserId.getAttribute("roleType").toString();

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

		return vehiclestatusDAO.transport_ue_count(Search,cont_comd, cont_corps, cont_div, cont_bde, prf_code,sus_no,line_dte_sus,type_force,
				eff_from, eff_to, modification, we_pe_no, table_title, std_nomclatureStr);
	}




	@RequestMapping(value = "/ue_of_transport_summaryTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> ue_of_transport_summaryTable(@RequestParam Map<String, String> formData,@RequestParam(value = "std_nomclature[]", required = false) String[] std_nomclature,
			HttpSession sessionUserId, ModelMap Mmap, HttpSession session, HttpServletRequest request) throws SQLException {

		String msg = "";
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		// Extract values from formData
		Integer startPage = Integer.parseInt(formData.get("startPage"));
		String pageLength = formData.get("pageLength");
		String search = formData.get("Search");
		String orderColumn = formData.get("orderColunm");
		String orderType = formData.get("orderType");

		// Get form-specific fields
		String prf_code = formData.get("prf_code");
		String sus_no = formData.get("sus_no");
		String line_dte_sus = formData.get("line_dte_sus");
		String type_force = formData.get("type_force");
		String eff_from = formData.get("eff_from");
		String eff_to = formData.get("eff_to");
		String modification = formData.get("modification");
		String we_pe_no = formData.get("we_pe_no");
		String table_title = formData.get("table_title");
		String std_nomclatureStr = std_nomclature != null ? String.join(",", std_nomclature) : "";

		// Get formation-related fields
		String cont_bde = formData.get("cont_bde");
		String cont_div = formData.get("cont_div");
		String cont_corps = formData.get("cont_corps");
		String cont_comd = formData.get("cont_comd");

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

				List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",formation_code+"00");
				Mmap.put("getDivList",Bn);

				List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",formation_code+"00000");
				Mmap.put("getBdeList",bde);

				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectForce_type",select);



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

		return vehiclestatusDAO.transport_ue_summary(startPage, pageLength, search, orderColumn, orderType,
				cont_comd,cont_corps,cont_div,cont_bde,prf_code,sus_no,line_dte_sus,type_force,eff_from,eff_to,
				modification,we_pe_no,table_title,std_nomclatureStr);
	}

	@RequestMapping(value = "/ue_of_transport_summaryCount", method = RequestMethod.POST)
	public @ResponseBody long ue_of_transport_summaryCount(@RequestParam Map<String, String> formData, HttpSession sessionUserId, ModelMap Mmap,
			@RequestParam(value = "std_nomclature[]", required = false) String[] std_nomclature,String Search,
			HttpSession session, HttpServletRequest request) throws SQLException {

		// Get form-specific fields
		String prf_code = formData.get("prf_code");
		String sus_no = formData.get("sus_no");
		String line_dte_sus = formData.get("line_dte_sus");
		String type_force = formData.get("type_force");
		String eff_from = formData.get("eff_from");
		String eff_to = formData.get("eff_to");
		String modification = formData.get("modification");
		String we_pe_no = formData.get("we_pe_no");
		String table_title = formData.get("table_title");
		String std_nomclatureStr = std_nomclature != null ? String.join(",", std_nomclature) : "";

		// Get formation-related fields
		String cont_bde = formData.get("cont_bde");
		String cont_div = formData.get("cont_div");
		String cont_corps = formData.get("cont_corps");
		String cont_comd = formData.get("cont_comd");

		String roleType = sessionUserId.getAttribute("roleType").toString();

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

		return vehiclestatusDAO.transport_ue_summary_count(Search,cont_comd,cont_corps,cont_div,cont_bde,prf_code,sus_no,line_dte_sus,type_force,eff_from,eff_to,modification,we_pe_no,table_title,std_nomclatureStr);
	}

}
