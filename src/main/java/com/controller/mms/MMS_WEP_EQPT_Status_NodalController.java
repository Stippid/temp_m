package com.controller.mms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Excel_To_Export_2_Table_Report;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_ReportsDAO;
import com.dao.mms.wpncatstatus_nodaldteDAO;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_WEP_EQPT_Status_NodalController {

	
	@Autowired
	private RoleBaseMenuDAO roledao;
	

	@Autowired
	private wpncatstatus_nodaldteDAO wpndao;
	
	
	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@Autowired
	MMS_ReportsDAO mms_report;
	
	ReportsController rcont = new ReportsController();
	
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
	
	
	@RequestMapping(value = "/admin/wep_eqpt_status_nodal_dte", method = RequestMethod.GET)
	public ModelAndView wep_eqpt_status_nodal_dte(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status_nodal_dte", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(!roleAccess.equals("HeadQuarter") & !roleAccess.equals("MISO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getItemGroupList", getItemGroupList());
		
		if(roleAccess.equals("Line_dte")) {
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
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
		return new ModelAndView("wep_eqpt_status_nodal_dteTiles");
	}
	
	@RequestMapping(value = "/admin/wep_eqpt_status_details_nodal_dte", method = RequestMethod.POST)
	public ModelAndView wep_eqpt_status_details_nodal_dte(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cmd1", required = false) String cmd,
			@RequestParam(value = "type_wep_eqpt1", required = false) String type_wep_eqpt,
			@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus)
	{	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		if(!roleAccess.equals("HeadQuarter") & !roleAccess.equals("MISO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		
		if(roleAccess.equals("Line_dte")) {
			line_dte_sus = roleSusNo;
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getItemGroupList", getItemGroupList());
		Mmap.put("cmd", cmd);
		Mmap.put("type_wep_eqpt", type_wep_eqpt);
		Mmap.put("prf_code", prf_code);
		Mmap.put("line_dte_sus1", line_dte_sus);
		
		if(!type_wep_eqpt.equals("")) {
			ArrayList<ArrayList<String>> list = mms_report.wep_eqpt_status_details(cmd,type_wep_eqpt,prf_code,line_dte_sus);
			if(list.size() == 0)
			{
		 		Mmap.put("list", list);
			}
			else
			{
				double sumUE = 0;
				long uh = 0;
				long loan = 0;
				long sector = 0;
				long acsfp = 0;
				long wwr_unit = 0;
				long wwr_depot = 0;
				long total_uh = 0;
				for(int i=0;i<list.size();i++) {
					String sumUE1 = list.get(i).get(6);
					if(sumUE1 == null) {
						sumUE1 = "0";
					}
					sumUE = sumUE + Double.parseDouble(sumUE1);
					uh = uh + Long.parseLong(list.get(i).get(7));
					loan = loan +  Long.parseLong(list.get(i).get(8));
					sector = sector +  Long.parseLong(list.get(i).get(9));
					acsfp = acsfp +  Long.parseLong(list.get(i).get(10));
					wwr_unit = wwr_unit +  Long.parseLong(list.get(i).get(11));
					wwr_depot = wwr_depot +  Long.parseLong(list.get(i).get(12));
					total_uh = total_uh +  Long.parseLong(list.get(i).get(13));
				}
				Mmap.put("sumUE",sumUE);
				Mmap.put("uh",uh);
				Mmap.put("loan",loan);
				Mmap.put("sector",sector);
				Mmap.put("acsfp",acsfp);
				Mmap.put("wwr_unit",wwr_unit);
				Mmap.put("wwr_depot",wwr_depot);
				Mmap.put("total_uh",total_uh);
				Mmap.put("list", list);
			}
		}
	 	return new ModelAndView("wep_eqpt_statusTile");
	}
	
	public List<CUE_TB_MISO_MMS_ITEM_MSTR> getItemGroupList() {
		Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionComnd.beginTransaction();
		Query q = sessionComnd.createQuery("select codevalue,label from T_Domain_Value where domainid = 'ITEMGROUP' order by label");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
		tx.commit();
		sessionComnd.close();
		return list;
	}
	
	
	
	//code by prasanth pgmr//
		int count_nodal_dte =0;
		@RequestMapping(value = "/wep_eqpt_statusList_nodal_dte", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> wep_eqpt_statusList_nodal_dte(int startPage,String pageLength,String orderColunm,String orderType,
				String Search,
				String line_dte_sus,
				String type_wep_eqpt,
				String item_no_list,
				String comd,
				String corps,
				String div,
				String bde,
				String sus_no,HttpSession session){
			
			Boolean val = roledao.ScreenRedirect("wep_eqpt_status_nodal_dte", session.getAttribute("roleid").toString());
			
			if (val == false) {
				return null;
			}else {
				
				if(line_dte_sus.equals("")) {
					return null;
				}else if(type_wep_eqpt.equals("")) {
					return null;
				}else{
					String roleAccess = session.getAttribute("roleAccess").toString();
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					if(roleAccess.equals("Line_dte")) {
						line_dte_sus = roleSusNo;
					}
					List<Map<String, Object>> list = wpndao.wep_eqpt_statusList_nodal_dte(startPage,pageLength,orderColunm,orderType,Search,line_dte_sus,type_wep_eqpt,item_no_list,comd,corps,div,bde,sus_no);
					count_nodal_dte = list.size();
					return list;
				}
			}
		}
		
		@RequestMapping(value = "/wep_eqpt_statusCount_nodal_dte", method = RequestMethod.POST)
		public @ResponseBody long wep_eqpt_statusCount_nodal_dte(
				String Search,
				String line_dte_sus,
				String type_wep_eqpt,
				String item_no_list,
				String comd,
				String corps,
				String div,
				String bde,
				String sus_no,HttpSession session){
			Boolean val = roledao.ScreenRedirect("wep_eqpt_status_nodal_dte", session.getAttribute("roleid").toString());
			
			if (val == false) {
				return 0;
			}else {
				
				if(type_wep_eqpt.equals("")) {
					return 0;
				}else{
					String roleAccess = session.getAttribute("roleAccess").toString();
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					if(roleAccess.equals("Line_dte")){
						line_dte_sus = roleSusNo;
					}
					return count_nodal_dte;//wpndao.wep_eqpt_statusList_nodal_dte(cmd,type_wep_eqpt,line_dte_sus,item_no_list).size();
				}
				
			}
			
		}
	
	@RequestMapping(value = "/getwpncatnodal_dte", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getwpncatnodal_dte(HttpSession sessionA,
			@RequestParam String line_dte_sus) {
		if( !line_dte_sus.equals("0")) {
			return wpndao.getwpncatnodal_dte(sessionA,line_dte_sus);
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getPRFListbyItemGroup_nodal_dte", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPRFListbyItemGroup_nodal_dte(HttpSession sessionA,
			@RequestParam String type) {
		if(type.equals("")) {
			return null;
		}else {
			List<Map<String, Object>> list = wpndao.getPRFListbyItemGroup_nodal_dte(type,sessionA);
			return list;
		}
	}

}
