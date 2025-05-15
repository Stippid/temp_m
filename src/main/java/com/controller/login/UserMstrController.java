package com.controller.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.controller.ExportFile.ExportCSVFileController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.dao.RBAC.UserMSTRDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class UserMstrController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private UserMSTRDAO userMstrDao;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	private ExportCSVFileController csv = new ExportCSVFileController();
	
	ReportsController rcont = new ReportsController();
	
	// start Formation wise Search User Master
	@RequestMapping(value = "/SEARCH_FMN_Wise_USER_ID", method = RequestMethod.GET)
	public ModelAndView SEARCH_FMN_Wise_USER_ID(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,HttpServletResponse response) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		Boolean val = roledao.ScreenRedirect("SEARCH_FMN_Wise_USER_ID", roleid);
		/*if (val == false) {
			return new ModelAndView("AccessTiles");
		} */
			System.err.println("MSG VALUE SESRCH FMN " + msg);
			
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			
			if(roleAccess.equals("Formation")){
				if(roleSubAccess.equals("Command")) {
					String formation_code = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= rcont.getFormationList("Command",formation_code);	
					Mmap.put("getCommandList",comd);
					List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",formation_code);
					Mmap.put("getCorpsList",corps);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					
					List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
					Mmap.put("selectLine_dte",select);
				} if(roleSubAccess.equals("Corps")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);				
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
					Mmap.put("selectLine_dte",select);
				} if(roleSubAccess.equals("Division")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					List<Tbl_CodesForm> bde=rcont.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectBde",select);
					
					Mmap.put("selectLine_dte",select);
				}if(roleSubAccess.equals("Brigade")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=rcont.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=rcont.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=rcont.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectLine_dte",select);
				}
			}if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
				List<Tbl_CodesForm> comd=all.getCommandDetailsList();
				Mmap.put("getCommandList",comd);
				String selectComd ="<option value=''>--Select--</option>";
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcomd", selectComd);
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
			}
				
			Mmap.put("msg", msg);
			Mmap.put("getCommandList", all.getCommandDetailsList());
			String selectComd ="<option value=''>-- Select --</option>";
			String select="<option value='0'>-- Select --</option>";
			
			/*Mmap.put("cont_comd1",cont_comd1);
			Mmap.put("cont_corps1",cont_corps1);
			Mmap.put("cont_div1",cont_div1);
			Mmap.put("cont_bde1",cont_bde1);
			Mmap.put("sus_no1",sus_no1);
			Mmap.put("unit_name1",unit_name1);
			Mmap.put("msg", msg);*/
			
			
			if(msg != null && msg.equals("ok")) {
				Mmap.put("sus_no1",sus_no1);
				Mmap.put("unit_name1",unit_name1);
				Mmap.put("cont_comd1",cont_comd1);
				Mmap.put("cont_corps1",cont_corps1);
				Mmap.put("cont_div1",cont_div1);
				Mmap.put("cont_bde1",cont_bde1);
			}else {
				sus_no1 = "";
				unit_name1 = "";
				cont_comd1 = "";
				cont_corps1 = "";
				cont_div1 = "";
				cont_bde1 = "";
				Mmap.put("msg", msg);
			}
			
			
			return new ModelAndView("SEARCH_FMN_Wise_USER_IDTile");
		
	}
	
	String cont_comd1 ="";
	String cont_corps1 ="";
	String cont_div1 ="";
	String cont_bde1 ="";
	String sus_no1="";
	String unit_name1="";
	
	@RequestMapping(value = "/search_user_by_formation", method = RequestMethod.POST)
	public ModelAndView search_user_by_formation(ModelMap Mmap, HttpSession session,HttpServletResponse response,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps,
			@RequestParam(value = "cont_div1", required = false) String cont_div,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde) {

		cont_comd1 = cont_comd;
		cont_corps1 = cont_corps;
		cont_div1 = cont_div;
		cont_bde1 = cont_bde;
		Mmap.put("msg", "ok");
		return new ModelAndView("redirect:SEARCH_FMN_Wise_USER_ID");
	}
	
	@RequestMapping(value = "/exportUserListForm", method = RequestMethod.POST)
    public void exportUserListForm(HttpServletResponse response,HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SEARCH_FMN_Wise_USER_ID", roleid);
		if (val == false) {
			
		} else {
			if(!formCode.equals("")) {
				List<UserLogin> alist = userMstrDao.getFMN_WISE_USER_ID_LIST(formCode);
				String[] csvHeader1 = {"SUS NO", "UNIT NAME", "USER ID", "ARMY NO", "USER ROLE"};
				String[] nameMapping = {"user_sus_no", "unit_name", "userName","army_no","role"};
				csv.exportToCSV(response,alist,csvHeader1,nameMapping);
			}
		}
    }
	
	
	String formCode ="";
	
	// open search report
	@RequestMapping(value = "/getUserReportListFormationWise",method=RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportListFormationWise(@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		formCode ="";
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SEARCH_FMN_Wise_USER_ID", roleid);
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd1 = fcode_comd;
				
				if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
					cont_bde1 = fcode_comd+cont_bde1.substring(1, 10);
		    	}else {
		    		if(!cont_div1.equals("0") && !cont_div1.equals("")){
		    			cont_div1 = fcode_comd+cont_div1.substring(1, 6);
			    	}else {
			    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
			    			cont_corps1 = fcode_comd+cont_corps1.substring(1, 3);
			    		}else {
				    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
				    			cont_comd1 = fcode_comd;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps1 = fcode_corps;
				
				if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
					cont_bde1 = fcode_corps+cont_bde1.substring(3, 10);
		    	}else {
		    		if(!cont_div1.equals("0") && !cont_div1.equals("")){
		    			cont_div1 = fcode_corps+cont_div1.substring(3, 6);
			    	}else {
			    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
			    			cont_corps1 = fcode_corps;
				    	}else {
				    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
				    			cont_comd1 = fcode_corps;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Division")) {
				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div1 = fcode_div;
				
				if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
					cont_bde1 = fcode_div+cont_bde1.substring(6, 10);
		    	}else {
		    		if(!cont_div1.equals("0") && !cont_div1.equals("")){
		    			cont_div1 = fcode_div;
			    	}else {
			    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
			    			cont_corps1 = fcode_div;
				    	}else {
				    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
				    			cont_comd1 = fcode_div;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Brigade")) {
				cont_bde1 = roleFormationNo;
			}
		}
		
		
		DataSet<Map<String, Object>> dataSet = userMstrDao.DatatablesCriteriasUserReportFormationWise(criterias, cont_comd1,cont_corps1,cont_div1,cont_bde1);
		return DatatablesResponse.build(dataSet, criterias);
	}
	// end Formation wise Search User Master
}
