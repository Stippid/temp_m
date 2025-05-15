package com.controller.Reports;

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
import com.dao.Reports.CustomQueryDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CustomQueryController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@Autowired
	private CustomQueryDAO cdao;
	
	
	@RequestMapping(value = "/admin/custom_query_report", method = RequestMethod.GET)
	public ModelAndView custom_query_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("custom_query_report", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(!roleAccess.equals("MISO")) {
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
		return new ModelAndView("custom_query_reportTile");
	}
	
	@RequestMapping(value = "/CustomQueryTPTList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> CustomQueryTPTList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			int type_veh,String prf_code,String mct_main,String cont_comd,String cont_corps,String cont_div,String cont_bde,String sus_no,String line_dte_sus,HttpSession session){
		Boolean val = roledao.ScreenRedirect("custom_query_report", session.getAttribute("roleid").toString());
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
					return cdao.CustomQueryTPTList(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"A",sus_no,line_dte_sus);
				}
				else if(type_veh == 1) {  // B Veh
					return cdao.CustomQueryTPTList(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"B",sus_no,line_dte_sus);
				}
				else if(type_veh == 2) {  // C Veh
					return cdao.CustomQueryTPTList(startPage,pageLength,Search,orderColunm,orderType,formCode,mct_main,prf_code,"C",sus_no,line_dte_sus);
				}else {
					return null;
				}
			}
		}
	}
	@RequestMapping(value = "/CustomQueryTPTCount", method = RequestMethod.POST)
	public @ResponseBody long CustomQueryTPTCount(HttpSession sessionUserId,String Search,int type_veh,String prf_code,String mct_main,String cont_comd,String cont_corps,String cont_div,String cont_bde,String sus_no,String line_dte_sus){
		Boolean val = roledao.ScreenRedirect("custom_query_report", sessionUserId.getAttribute("roleid").toString());
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
					return cdao.CustomQueryTPTCount(Search,formCode,mct_main,prf_code,"A",sus_no,line_dte_sus);
				}
				else if(type_veh == 1) {  // B Veh
					return cdao.CustomQueryTPTCount(Search,formCode,mct_main,prf_code,"B",sus_no,line_dte_sus);
				}
				else if(type_veh == 2) {  // C Veh
					return cdao.CustomQueryTPTCount(Search,formCode,mct_main,prf_code,"C",sus_no,line_dte_sus);
				}else {
					return 0;
				}
			}
		}
	}
}