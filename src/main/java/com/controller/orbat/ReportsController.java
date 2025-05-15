package com.controller.orbat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.ReportsDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ReportsController {
	
	
	//private static final HashMap<String, Object> Mmap = null;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private ReportsDAO reportDAO;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
		
	private String sus_no12 ="";
	private String unit_name12="";
	private String cont_comd12 ="";
	private String cont_corps12 = "0";
	private String cont_div12 = "0";
	private String cont_bde12 = "0";
	private String status_sus_no12 = "";
	private String action12 = "";
	private String approved_rejected_on12 = "";
	private String approved_rejected_on22 = "";
	
	@RequestMapping(value="/admin/search_Formationwise_profileSetSession")
	public ModelAndView search_Formationwise_profileSetSession(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
			@RequestParam(value = "cont_div1", required = false) String cont_div1,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
			@RequestParam(value = "status_sus_no1", required = false) String status_sus_no1,
			@RequestParam(value = "action1", required = false) String action1,
			@RequestParam(value = "from1", required = false) String from1,
			@RequestParam(value = "to1", required = false) String to1,HttpSession sessionA){
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no1 = roleSusNo;
		}
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CommandCorSearch_Report", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(validation.checkUnit_nameLength(unit_name1)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:CommandCorSearch_Report");
		}
		
		unit_name1 = unit_name1.replace("&#40;","(");
		unit_name1 = unit_name1.replace("&#41;",")");
		
		action1 = action1.replace("&#40;","(");
		action1 = action1.replace("&#41;",")");
		
		sus_no12 = sus_no1;
		unit_name12=unit_name1;
		cont_comd12 = cont_comd1;
		cont_corps12 = cont_corps1;
		cont_div12 = cont_div1;
		cont_bde12 = cont_bde1;
		status_sus_no12 = status_sus_no1;
		action12 = action1;
		approved_rejected_on12 = from1;
		approved_rejected_on22 = to1;
		model.put("msg", "ok");
		return new ModelAndView("redirect:CommandCorSearch_Report");
	}
	
	
	@RequestMapping(value = "/CommandCorSearch_Report", method = RequestMethod.GET)
	public ModelAndView CommandCorSearch_Report(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		Mmap.put("getCommandList", all.getCommandDetailsList());
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CommandCorSearch_Report", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		if(msg != null && msg.equals("ok")) {
			Mmap.put("sus_no1",sus_no12);
			Mmap.put("unit_name1",unit_name12);
			Mmap.put("cont_comd1",cont_comd12);
			Mmap.put("cont_corps1",cont_corps12);
			Mmap.put("cont_div1",cont_div12);
			Mmap.put("cont_bde1",cont_bde12);
			Mmap.put("status_sus_no1",status_sus_no12);
			Mmap.put("action1",action12);
			Mmap.put("from1",approved_rejected_on12);
			Mmap.put("to1",approved_rejected_on22);
		}else {
			sus_no12 = "";
			unit_name12 = "";
			cont_comd12 = "";
			cont_corps12 = "";
			cont_div12 = "";
			cont_bde12 = "";
			status_sus_no12 = "";
			action12 = "";
			approved_rejected_on12 = "";
			approved_rejected_on22 = "";
			Mmap.put("msg", msg);
		}
		Mmap.put("getType_Of_LetterList", all.getType_Of_LetterList());
		return new ModelAndView("CommandCorSearch_ReportTiles");
	}
    
    @RequestMapping(value = "/admin/getAllOrbatDataSearchRpt")
    public @ResponseBody DatatablesResponse<Map<String, Object>> getAllOrbatDataSearchRpt(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
    	
    	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CommandCorSearch_Report", roleid);	
		if(val == false) {
			return null;
		}
		if(!status_sus_no12.equals("")){
    		DataSet<Map<String, Object>> dataSet = reportDAO.findFormationwiseReportWithDatatablesCriterias(criterias,status_sus_no12,sus_no12,unit_name12,cont_bde12,cont_div12,cont_corps12,action12,approved_rejected_on12,approved_rejected_on22,cont_comd12);
	    	return DatatablesResponse.build(dataSet, criterias);
    	}else {
    		return null;
    	}
    }
    
//**************************** START ORBAT ACTIVE DETAILS REPORT FORMATION WISE ****************************//
    
    private String sus_no121 ="";
	private String unit_name121="";
	private String cont_comd121 ="";
	private String cont_corps121 = "0";
	private String cont_div121 = "0";
	private String cont_bde121 = "0";
	private String line_dte_sus121 = "0";
	private String location121 = "0";
	
	@RequestMapping(value="/admin/search_OrbatDetails_profileSetSession")
	public ModelAndView search_Formationwise_profileSetSession(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
			@RequestParam(value = "cont_div1", required = false) String cont_div1,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
			@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no1 = roleSusNo;
		}
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);	
		/*if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		
		if(validation.checkUnit_nameLength(unit_name1)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:OrbatDetails_Report_Formation");
		}
		
		unit_name1 = unit_name1.replace("&#40;","(");
		unit_name1 = unit_name1.replace("&#41;",")");
		
		sus_no121 = sus_no1;
		unit_name121=unit_name1;
		cont_comd121 = cont_comd1;
		cont_corps121 = cont_corps1;
		cont_div121 = cont_div1;
		cont_bde121 = cont_bde1;
		line_dte_sus121 = line_dte_sus1;
		location121 = location1;
		model.put("msg", "ok");
		return new ModelAndView("redirect:OrbatDetails_Report_Formation");
	}
    
    @RequestMapping(value = "/OrbatDetails_Report_Formation", method = RequestMethod.GET)
	public ModelAndView OrbatDetails_Report(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		if(roleAccess.equals("Formation")){
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
			} if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				Mmap.put("selectLine_dte",select);
			} if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				
				Mmap.put("selectLine_dte",select);
			}if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
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
		}if(roleAccess.equals("Line_dte")){
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}
		
    	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);	
		/*if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		
		if(msg != null && msg.equals("ok")) {
			Mmap.put("sus_no1",sus_no121);
			Mmap.put("unit_name1",unit_name121);
			Mmap.put("cont_comd1",cont_comd121);
			Mmap.put("cont_corps1",cont_corps121);
			Mmap.put("cont_div1",cont_div121);
			Mmap.put("cont_bde1",cont_bde121);
			Mmap.put("line_dte_sus1",line_dte_sus121);
			Mmap.put("location1",location121);
		}else {
			sus_no121 = "";
			unit_name121 = "";
			cont_comd121 = "";
			cont_corps121 = "";
			cont_div121 = "";
			cont_bde121 = "";
			line_dte_sus121 = "";
			location121 = "";
			Mmap.put("msg", msg);
		}
		Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("OrbatDetails_ReportTiles");
	}
 
    @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt")
    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
    	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd121 = fcode_comd;
				
				if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
					cont_bde121 = fcode_comd+cont_bde121.substring(1, 10);
		    	}else {
		    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
		    			cont_div121 = fcode_comd+cont_div121.substring(1, 6);
			    	}else {
			    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
			    			cont_corps121 = fcode_comd+cont_corps121.substring(1, 3);
			    		}else {
				    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
				    			cont_comd121 = fcode_comd;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps121 = fcode_corps;
				
				if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
					cont_bde121 = fcode_corps+cont_bde121.substring(3, 10);
		    	}else {
		    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
		    			cont_div121 = fcode_corps+cont_div121.substring(3, 6);
			    	}else {
			    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
			    			cont_corps121 = fcode_corps;
				    	}else {
				    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
				    			cont_comd121 = fcode_corps;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Division")) {
				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div121 = fcode_div;
				
				if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
					cont_bde121 = fcode_div+cont_bde121.substring(6, 10);
		    	}else {
		    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
		    			cont_div121 = fcode_div;
			    	}else {
			    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
			    			cont_corps121 = fcode_div;
				    	}else {
				    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
				    			cont_comd121 = fcode_div;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Brigade")) {
				cont_bde121 = roleFormationNo;
			}
		}
		if(roleAccess.equals("Line_dte")){
			line_dte_sus121 = roleSusNo;
		}
		
			DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias(criterias,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
	    	return DatatablesResponse.build(dataSet, criterias);
		
    }
    
    
    public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
    	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

//**************************** END ORBAT ACTIVE DETAILS REPORT FORMATION WISE ****************************//
    @RequestMapping(value = "/klp_matrix_report", method = RequestMethod.GET)
	public ModelAndView klp_matrix_report(ModelMap Mmap,HttpSession sessionA,HttpServletRequest request) {
    	Mmap.put("formation",reportDAO.getAllFormationList());
    	return new ModelAndView("klp_matrix_reportTiles");
    }
    
    @RequestMapping(value = "/getKLP_MATRIXList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getKLP_MATRIXList(HttpServletRequest request,HttpSession session){
		List<Map<String, Object>> errorlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> columns = new LinkedHashMap<String, Object>();
		try {
			String formation = request.getParameter("formation");
			String station = request.getParameter("station");
			String level_in_hierarchy = request.getParameter("level_in_hierarchy");
			if(formation == null || formation.equals("")) {
				columns.put("error", "Please Select Formation");
				errorlist.add(columns);
				return errorlist;
			}else if(station == null || station.equals("")) {
				columns.put("error", "Please Enter Station");
				errorlist.add(columns);
				return errorlist;
			}else {
				String fmn = getFomationCode(level_in_hierarchy,formation);
				return reportDAO.getKLP_MATRIXList(fmn,station);
			}
		}catch (Exception e) {
			
			columns.put("error", "An Error Occured : " + e.getMessage());
			errorlist.add(columns);
			return errorlist;
		}
	}
    
    public String getFomationCode(String level_in_hierarchy,String formation) {
    	
    	String fmn = "0";
    	if(level_in_hierarchy.equalsIgnoreCase("Command")) {
    		fmn=String.valueOf(formation.charAt(0));
    	}else if(level_in_hierarchy.equalsIgnoreCase("Corps")) {
    		fmn=String.valueOf(formation.charAt(0)) + String.valueOf(formation.charAt(1)) + String.valueOf(formation.charAt(2));
    	}else if(level_in_hierarchy.equalsIgnoreCase("Division")) {
    		fmn=String.valueOf(formation.charAt(0)) + String.valueOf(formation.charAt(1)) + String.valueOf(formation.charAt(2)) + String.valueOf(formation.charAt(3)) + String.valueOf(formation.charAt(4)) + String.valueOf(formation.charAt(5));
    	}else if(level_in_hierarchy.equalsIgnoreCase("Brigade")) {
    		fmn = formation;
    	}else {
    		fmn = formation;
    	}
    	
    	return fmn;
    }
    
    
    
    //RAJ 28.10.2024
    
    
    //start cii unt dtl report  
    /*
	 * cii unit report
	 */
    private String sus_no1211 ="";
	private String unit_name1211="";
	private String line_dte_sus1211 = "0";
	private String location1211 = "0";
	
	/* for levels */
	private String level1_1211 ="";
	private String level2_1211 ="";
	private String level3_1211 ="";
	private String level4_1211 ="";
	private String level5_1211 ="";
	private String level6_1211 ="";
	private String level7_1211 ="";
	private String level8_1211 ="";
	private String level9_1211 ="";
	private String level10_1211 ="";
	
	
	
	//search session cii unit orbat detils view
	@RequestMapping(value="/search_OrbatDetails_profileSetSession_cii")
	public ModelAndView search_Formationwise_profileSetSession_cii(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "level1_1", required = false) String level1_1,
			@RequestParam(value = "level2_1", required = false) String level2_1,
			@RequestParam(value = "level3_1", required = false) String level3_1,
			@RequestParam(value = "level4_1", required = false) String level4_1,
			@RequestParam(value = "level5_1", required = false) String level5_1,
			@RequestParam(value = "level6_1", required = false) String level6_1,
			@RequestParam(value = "level7_1", required = false) String level7_1,
			@RequestParam(value = "level8_1", required = false) String level8_1,
			@RequestParam(value = "level9_1", required = false) String level9_1,
			@RequestParam(value = "level10_1", required = false) String level10_1,
			
			
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
			@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no1 = roleSusNo;
		}
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(validation.checkUnit_nameLength(unit_name1)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:OrbatDetails_Report_Formation");
		}
		
		unit_name1 = unit_name1.replace("&#40;","(");
		unit_name1 = unit_name1.replace("&#41;",")");
		
		sus_no1211 = sus_no1;
		unit_name1211=unit_name1;
		System.err.println("value of unit name in search method :----" + unit_name1);
		System.err.println("value of unit sus in search method :----" + sus_no1);
		level1_1211 = level1_1;
		level2_1211 = level2_1;
		level3_1211 = level3_1;
		level4_1211 = level4_1;
		level5_1211 = level5_1;
		level6_1211 = level6_1;
		level7_1211 = level7_1;
		level8_1211 = level8_1;
		level9_1211 = level9_1;
		level10_1211 = level10_1;
		line_dte_sus1211 = line_dte_sus1;
		location1211 = location1;
		model.put("msg", "ok");
		return new ModelAndView("redirect:OrbatDetails_Report_Formation_Cii");
	}
	
	
	/* JSP FOR ORBAT CII REPORT */
	@RequestMapping(value = "/OrbatDetails_Report_Formation_Cii", method = RequestMethod.GET)
	public ModelAndView OrbatDetails_Report_cii(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
    	
		/*if(request.getHeader("Referer") == null ) {
			msg = "";
		}*/
		//Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		Mmap.put("getLine_DteList",roledao.getLine_DteList_arm_code(""));
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("selectLine_dte",select);
		}if(roleAccess.equals("Line_dte")){
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}
		
    	String  roleid = sessionA.getAttribute("roleid").toString();
		/*Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Cii", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		
		if(msg != null && msg.equals("ok")) {
			Mmap.put("sus_no1",sus_no1211);
			Mmap.put("unit_name1",unit_name1211);
			
			Mmap.put("level1_1",level1_1211);
			Mmap.put("level2_1",level2_1211);
			Mmap.put("level3_1",level3_1211);
			Mmap.put("level4_1",level4_1211);
			Mmap.put("level5_1",level5_1211);
			Mmap.put("level6_1",level6_1211);
			Mmap.put("level7_1",level7_1211);
			Mmap.put("level8_1",level8_1211);
			Mmap.put("level9_1",level9_1211);
			Mmap.put("level10_1",level10_1211);
			Mmap.put("line_dte_sus1",line_dte_sus1211);
			Mmap.put("location1",location1211);
		}else {
			sus_no1211 = "";
			
			level1_1211 = "";
			level2_1211 = "";
			level3_1211 = "";
			level4_1211 = "";
			level5_1211 = "";
			level6_1211 = "";
			level7_1211 = "";
			level8_1211 = "";
			level9_1211 = "";
			level10_1211 = "";
			unit_name1211 = "";
			line_dte_sus1211 = "";
			location1211 = "";
			Mmap.put("msg", msg);
		}
		Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("OrbatDetails_Report_cii_Tiles");
	}
	
	
	 @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt_cii")
	    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt_cii(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
	    	String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);
			
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			
			
			if(val == false) {
				return null;
			}else {
				DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias_cii(criterias,sus_no1211,unit_name1211,level1_1211,level2_1211,level3_1211,level4_1211,level5_1211,level6_1211,level7_1211,level8_1211,level9_1211,level10_1211,line_dte_sus1211,location1211); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
		    	return DatatablesResponse.build(dataSet, criterias);
			}
	    }
	    
	
	/* end cii unt report */
	 
		/* ORDER OF BATTLE PUBLICATIONS REPORT */
		 /*jsp*/
		 @RequestMapping(value = "/OrbatDetails_Report_Formation_Pub", method = RequestMethod.GET)
			public ModelAndView OrbatDetails_Report_Formation_Pub(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
				String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		    	
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
				if(roleAccess.equals("Formation")){
					if(roleSubAccess.equals("Command")) {
						String formation_code = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
						Mmap.put("getCommandList",comd);
						List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
						Mmap.put("getCorpsList",corps);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						
						List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
						Mmap.put("getDivList",Bn);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
						Mmap.put("getBdeList",bde);
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectcorps",select);
						Mmap.put("selectDiv",select);
						Mmap.put("selectBde",select);
						Mmap.put("selectLine_dte",select);
					} if(roleSubAccess.equals("Corps")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
						Mmap.put("getDivList",Bn);						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
						Mmap.put("getBdeList",bde);
						
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectDiv",select);
						Mmap.put("selectBde",select);
						Mmap.put("selectLine_dte",select);
					} if(roleSubAccess.equals("Division")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> Bn=getFormationList("Division",div);
						Mmap.put("getDivList",Bn);
						
						List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
						Mmap.put("getBdeList",bde);
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectBde",select);
						
						Mmap.put("selectLine_dte",select);
					}if(roleSubAccess.equals("Brigade")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> Bn=getFormationList("Division",div);
						Mmap.put("getDivList",Bn);
						
						String bde_code = roleFormationNo;
						List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
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
				}if(roleAccess.equals("Line_dte")){
					List<Tbl_CodesForm> comd=all.getCommandDetailsList();
					Mmap.put("getCommandList",comd);
					String selectComd ="<option value=''>--Select--</option>";
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcomd", selectComd);
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
					Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
				}
				
		    	String  roleid = sessionA.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				
				if(msg != null && msg.equals("ok")) {
					Mmap.put("sus_no1",sus_no1213);
					Mmap.put("unit_name1",unit_name1213);
					Mmap.put("cont_comd1",cont_comd1213);
					Mmap.put("cont_corps1",cont_corps1213);
					Mmap.put("cont_div1",cont_div1213);
					Mmap.put("cont_bde1",cont_bde1213);
					Mmap.put("line_dte_sus1",line_dte_sus1213);
					Mmap.put("location1",location1213);
				}else {
					sus_no1213 = "";
					unit_name1213 = "";
					cont_comd1213 = "";
					cont_corps1213 = "";
					cont_div1213 = "";
					cont_bde1213 = "";
					line_dte_sus1213 = "";
					location1213 = "";
					Mmap.put("msg", msg);
				}
				Mmap.put("getlocList", all.getLOCList());
				return new ModelAndView("OrbatDetails_Report_PubTiles");
			}
		 
		 
		 	private String sus_no1213 ="";
			private String unit_name1213="";
			private String cont_comd1213 ="";
			private String cont_corps1213= "0";
			private String cont_div1213 = "0";
			private String cont_bde1213 = "0";
			private String line_dte_sus1213 = "0";
			private String location1213 = "0";
			
			@RequestMapping(value="/admin/search_OrbatDetails_profileSetSession_Publications")
			public ModelAndView search_OrbatDetails_profileSetSession_Publications(ModelMap model,HttpServletRequest request,
					@RequestParam(value = "sus_no1", required = false) String sus_no1,
					@RequestParam(value = "unit_name1", required = false) String unit_name1,
					@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
					@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
					@RequestParam(value = "cont_div1", required = false) String cont_div1,
					@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
					@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
					@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
				
				String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Unit")){
					sus_no1 = roleSusNo;
				}
				
				String  roleid = sessionA.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				
				if(validation.checkUnit_nameLength(unit_name1)  == false){
			 		model.put("msg",validation.unit_nameMSG);
					return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub");
				}
				
				unit_name1 = unit_name1.replace("&#40;","(");
				unit_name1 = unit_name1.replace("&#41;",")");
				
				sus_no1213 = sus_no1;
				unit_name1213=unit_name1;
				cont_comd1213 = cont_comd1;
				cont_corps1213 = cont_corps1;
				cont_div1213 = cont_div1;
				cont_bde1213 = cont_bde1;
				line_dte_sus1213 = line_dte_sus1;
				location1213 = location1;
				model.put("msg", "ok");
				return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub");
			}
			
			
			 @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt_Publications")
			    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt_Publicatins(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
			    	String  roleid = sessionA.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub", roleid);
					
					String roleAccess = sessionA.getAttribute("roleAccess").toString();
					String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
					String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
					String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
					
					if(roleAccess.equals("Formation")) {
						if(roleSubAccess.equals("Command")) {
							String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
							cont_comd1213 = fcode_comd;
							
							if(!cont_bde1213.equals("0") && !cont_bde1213.equals("")){
								cont_bde1213 = fcode_comd+cont_bde1213.substring(1, 10);
					    	}else {
					    		if(!cont_div1213.equals("0") && !cont_div1213.equals("")){
					    			cont_div1213 = fcode_comd+cont_div1213.substring(1, 6);
						    	}else {
						    		if(!cont_corps1213.equals("0") && !cont_corps1213.equals("")){
						    			cont_corps1213 = fcode_comd+cont_corps1213.substring(1, 3);
						    		}else {
							    		if(!cont_comd1213.equals("-1") && !cont_comd1213.equals("")){
							    			cont_comd1213 = fcode_comd;
								    	}
							    	}
							    }
						    }
						}
						if(roleSubAccess.equals("Corps")) {
							String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
							cont_corps1213 = fcode_corps;
							
							if(!cont_bde1213.equals("0") && !cont_bde1213.equals("")){
								cont_bde1213 = fcode_corps+cont_bde1213.substring(3, 10);
					    	}else {
					    		if(!cont_div1213.equals("0") && !cont_div1213.equals("")){
					    			cont_div1213 = fcode_corps+cont_div1213.substring(3, 6);
						    	}else {
						    		if(!cont_corps1213.equals("0") && !cont_corps1213.equals("")){
						    			cont_corps1213 = fcode_corps;
							    	}else {
							    		if(!cont_comd1213.equals("-1") && !cont_comd1213.equals("")){
							    			cont_comd1213 = fcode_corps;
								    	}
							    	}
							    }
						    }
						}
						if(roleSubAccess.equals("Division")) {
							String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
							cont_div1213 = fcode_div;
							
							if(!cont_bde1213.equals("0") && !cont_bde1213.equals("")){
								cont_bde1213 = fcode_div+cont_bde1213.substring(6, 10);
					    	}else {
					    		if(!cont_div1213.equals("0") && !cont_div1213.equals("")){
					    			cont_div1213 = fcode_div;
						    	}else {
						    		if(!cont_corps1213.equals("0") && !cont_corps1213.equals("")){
						    			cont_corps1213 = fcode_div;
							    	}else {
							    		if(!cont_comd1213.equals("-1") && !cont_comd1213.equals("")){
							    			cont_comd1213 = fcode_div;
								    	}
							    	}
							    }
						    }
						}
						if(roleSubAccess.equals("Brigade")) {
							cont_bde1213 = roleFormationNo;
						}
					}
					if(roleAccess.equals("Line_dte")){
						line_dte_sus1213 = roleSusNo;
					}
					if(val == false) {
						return null;
					}else {
						DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias_Pub(criterias,sus_no1213,unit_name1213,cont_bde1213,cont_div1213,cont_corps1213,cont_comd1213,line_dte_sus1213,location1213); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
				    	return DatatablesResponse.build(dataSet, criterias);
					}
			    }
			 
			 
			 /* ORDER OF BATTLE PUBLICATIONS REPORT */
			 /*jsp for publication CT part II*/
			 @RequestMapping(value = "/OrbatDetails_Report_Formation_Pub_CTPartII", method = RequestMethod.GET)
				public ModelAndView OrbatDetails_Report_Formation_Pub_CTPartII(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
					String roleAccess = sessionA.getAttribute("roleAccess").toString();
					String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
					String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
					String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			    	
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
					Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
					if(roleAccess.equals("Formation")){
						if(roleSubAccess.equals("Command")) {
							String formation_code = String.valueOf(roleFormationNo.charAt(0));
							List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
							Mmap.put("getCommandList",comd);
							List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
							Mmap.put("getCorpsList",corps);
							
							String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
							
							List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
							Mmap.put("getDivList",Bn);
							
							String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
							List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
							Mmap.put("getBdeList",bde);
							
							String select="<option value='0'>--Select--</option>";
							Mmap.put("selectcorps",select);
							Mmap.put("selectDiv",select);
							Mmap.put("selectBde",select);
							Mmap.put("selectLine_dte",select);
						} if(roleSubAccess.equals("Corps")) {
							String command = String.valueOf(roleFormationNo.charAt(0));
							List<Tbl_CodesForm> comd=getFormationList("Command",command);
							Mmap.put("getCommandList",comd);
							String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
							List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
							Mmap.put("getCorpsList",corps);
							List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
							Mmap.put("getDivList",Bn);
							
							String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
							List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
							Mmap.put("getBdeList",bde);
							
							String select="<option value='0'>--Select--</option>";
							Mmap.put("selectDiv",select);
							Mmap.put("selectBde",select);
							Mmap.put("selectLine_dte",select);
						} if(roleSubAccess.equals("Division")) {
							String command = String.valueOf(roleFormationNo.charAt(0));
							List<Tbl_CodesForm> comd=getFormationList("Command",command);
							Mmap.put("getCommandList",comd);
							
							String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
							List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
							Mmap.put("getCorpsList",corps);
							
							String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
							List<Tbl_CodesForm> Bn=getFormationList("Division",div);
							Mmap.put("getDivList",Bn);
							
							List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
							Mmap.put("getBdeList",bde);
							
							String select="<option value='0'>--Select--</option>";
							Mmap.put("selectBde",select);
							
							Mmap.put("selectLine_dte",select);
						}if(roleSubAccess.equals("Brigade")) {
							String command = String.valueOf(roleFormationNo.charAt(0));
							List<Tbl_CodesForm> comd=getFormationList("Command",command);
							Mmap.put("getCommandList",comd);
							
							String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
							List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
							Mmap.put("getCorpsList",corps);
							
							String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
							List<Tbl_CodesForm> Bn=getFormationList("Division",div);
							Mmap.put("getDivList",Bn);
							
							String bde_code = roleFormationNo;
							List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
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
					}if(roleAccess.equals("Line_dte")){
						List<Tbl_CodesForm> comd=all.getCommandDetailsList();
						Mmap.put("getCommandList",comd);
						String selectComd ="<option value=''>--Select--</option>";
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectcomd", selectComd);
						Mmap.put("selectcorps",select);
						Mmap.put("selectDiv",select);
						Mmap.put("selectBde",select);
						Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
					}
					
			    	String  roleid = sessionA.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartII", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					
					if(msg != null && msg.equals("ok")) {
						Mmap.put("sus_no1",sus_no12131);
						Mmap.put("unit_name1",unit_name12131);
						Mmap.put("cont_comd1",cont_comd12131);
						Mmap.put("cont_corps1",cont_corps12131);
						Mmap.put("cont_div1",cont_div12131);
						Mmap.put("cont_bde1",cont_bde12131);
						Mmap.put("line_dte_sus1",line_dte_sus12131);
						Mmap.put("location1",location12131);
					}else {
						sus_no12131 = "";
						unit_name12131 = "";
						cont_comd12131 = "";
						cont_corps12131 = "";
						cont_div12131 = "";
						cont_bde12131 = "";
						line_dte_sus12131 = "";
						location12131 = "";
						Mmap.put("msg", msg);
					}
					Mmap.put("getlocList", all.getLOCList());
					return new ModelAndView("OrbatDetails_Report_Pub_CTPARTIITiles");
				}
			 
			 
			 	private String sus_no12131 ="";
				private String unit_name12131="";
				private String cont_comd12131 ="";
				private String cont_corps12131= "0";
				private String cont_div12131 = "0";
				private String cont_bde12131 = "0";
				private String line_dte_sus12131 = "0";
				private String location12131= "0";
				
				@RequestMapping(value="/admin/search_OrbatDetails_profileSetSession_Publications_CTPARTII")
				public ModelAndView search_OrbatDetails_profileSetSession_Publications_CTPARTII(ModelMap model,HttpServletRequest request,
						@RequestParam(value = "sus_no1", required = false) String sus_no1,
						@RequestParam(value = "unit_name1", required = false) String unit_name1,
						@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
						@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
						@RequestParam(value = "cont_div1", required = false) String cont_div1,
						@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
						@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
						@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
					
					String roleAccess = sessionA.getAttribute("roleAccess").toString();
					String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
					if(roleAccess.equals("Unit")){
						sus_no1 = roleSusNo;
					}
					
					String  roleid = sessionA.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartII", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					
					if(validation.checkUnit_nameLength(unit_name1)  == false){
				 		model.put("msg",validation.unit_nameMSG);
						return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartII");
					}
					
					unit_name1 = unit_name1.replace("&#40;","(");
					unit_name1 = unit_name1.replace("&#41;",")");
					
					sus_no12131 = sus_no1;
					unit_name12131=unit_name1;
					cont_comd12131 = cont_comd1;
					cont_corps12131 = cont_corps1;
					cont_div12131 = cont_div1;
					cont_bde12131 = cont_bde1;
					line_dte_sus12131 = line_dte_sus1;
					location12131 = location1;
					model.put("msg", "ok");
					return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartII");
				}
				
				
				 @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt_Publications_CTPARTII")
				    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt_Publications_CTPARTII(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
				    	String  roleid = sessionA.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartII", roleid);
						
						String roleAccess = sessionA.getAttribute("roleAccess").toString();
						String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
						String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
						String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
						
						System.err.println(" Inside orbat publication part 2 getOrbatDetails_Formation_Rpt_Publications_CTPARTII " );
						if(roleAccess.equals("Formation")) {
							if(roleSubAccess.equals("Command")) {
								String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
								cont_comd12131 = fcode_comd;
								
								if(!cont_bde12131.equals("0") && !cont_bde12131.equals("")){
									cont_bde12131 = fcode_comd+cont_bde12131.substring(1, 10);
						    	}else {
						    		if(!cont_div12131.equals("0") && !cont_div12131.equals("")){
						    			cont_div12131 = fcode_comd+cont_div12131.substring(1, 6);
							    	}else {
							    		if(!cont_corps12131.equals("0") && !cont_corps12131.equals("")){
							    			cont_corps12131 = fcode_comd+cont_corps12131.substring(1, 3);
							    		}else {
								    		if(!cont_comd12131.equals("-1") && !cont_comd12131.equals("")){
								    			cont_comd12131 = fcode_comd;
									    	}
								    	}
								    }
							    }
							}
							if(roleSubAccess.equals("Corps")) {
								String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
								cont_corps12131 = fcode_corps;
								
								if(!cont_bde12131.equals("0") && !cont_bde12131.equals("")){
									cont_bde12131 = fcode_corps+cont_bde12131.substring(3, 10);
						    	}else {
						    		if(!cont_div12131.equals("0") && !cont_div12131.equals("")){
						    			cont_div12131 = fcode_corps+cont_div12131.substring(3, 6);
							    	}else {
							    		if(!cont_corps12131.equals("0") && !cont_corps12131.equals("")){
							    			cont_corps12131 = fcode_corps;
								    	}else {
								    		if(!cont_comd12131.equals("-1") && !cont_comd12131.equals("")){
								    			cont_comd12131 = fcode_corps;
									    	}
								    	}
								    }
							    }
							}
							if(roleSubAccess.equals("Division")) {
								String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
								cont_div12131= fcode_div;
								
								if(!cont_bde12131.equals("0") && !cont_bde12131.equals("")){
									cont_bde12131 = fcode_div+cont_bde12131.substring(6, 10);
						    	}else {
						    		if(!cont_div12131.equals("0") && !cont_div12131.equals("")){
						    			cont_div12131 = fcode_div;
							    	}else {
							    		if(!cont_corps12131.equals("0") && !cont_corps12131.equals("")){
							    			cont_corps12131 = fcode_div;
								    	}else {
								    		if(!cont_comd12131.equals("-1") && !cont_comd12131.equals("")){
								    			cont_comd12131 = fcode_div;
									    	}
								    	}
								    }
							    }
							}
							if(roleSubAccess.equals("Brigade")) {
								cont_bde12131 = roleFormationNo;
							}
						}
						if(roleAccess.equals("Line_dte")){
							line_dte_sus12131 = roleSusNo;
						}
						if(val == false) {
							return null;
						}else {
							DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTII(criterias,sus_no12131,unit_name12131,cont_bde12131,cont_div12131,cont_corps12131,cont_comd12131,line_dte_sus12131,location12131); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
					    	return DatatablesResponse.build(dataSet, criterias);
						}
				    }
				 
				 
				/* publication of orbats : part IA */
				 
				 
				 /* ORDER OF BATTLE PUBLICATIONS REPORT */
				 /*jsp for publication part I A*/
				 @RequestMapping(value = "/OrbatDetails_Report_Formation_Pub_CTPartIA", method = RequestMethod.GET)
					public ModelAndView OrbatDetails_Report_Formation_Pub_CTPartIA(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
						String roleAccess = sessionA.getAttribute("roleAccess").toString();
						String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
						String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
						String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				    	
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
						Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
						if(roleAccess.equals("Formation")){
							if(roleSubAccess.equals("Command")) {
								String formation_code = String.valueOf(roleFormationNo.charAt(0));
								List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
								Mmap.put("getCommandList",comd);
								List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
								Mmap.put("getCorpsList",corps);
								
								String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
								
								List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
								Mmap.put("getDivList",Bn);
								
								String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
								List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
								Mmap.put("getBdeList",bde);
								
								String select="<option value='0'>--Select--</option>";
								Mmap.put("selectcorps",select);
								Mmap.put("selectDiv",select);
								Mmap.put("selectBde",select);
								Mmap.put("selectLine_dte",select);
							} if(roleSubAccess.equals("Corps")) {
								String command = String.valueOf(roleFormationNo.charAt(0));
								List<Tbl_CodesForm> comd=getFormationList("Command",command);
								Mmap.put("getCommandList",comd);
								String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
								List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
								Mmap.put("getCorpsList",corps);
								List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
								Mmap.put("getDivList",Bn);
															
								String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
								List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
								Mmap.put("getBdeList",bde);
								
								String select="<option value='0'>--Select--</option>";
								Mmap.put("selectDiv",select);
								Mmap.put("selectBde",select);
								Mmap.put("selectLine_dte",select);
							} if(roleSubAccess.equals("Division")) {
								String command = String.valueOf(roleFormationNo.charAt(0));
								List<Tbl_CodesForm> comd=getFormationList("Command",command);
								Mmap.put("getCommandList",comd);
								
								String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
								List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
								Mmap.put("getCorpsList",corps);
								
								String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
								List<Tbl_CodesForm> Bn=getFormationList("Division",div);
								Mmap.put("getDivList",Bn);
								
								List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
								Mmap.put("getBdeList",bde);
								
								String select="<option value='0'>--Select--</option>";
								Mmap.put("selectBde",select);
								
								Mmap.put("selectLine_dte",select);
							}if(roleSubAccess.equals("Brigade")) {
								String command = String.valueOf(roleFormationNo.charAt(0));
								List<Tbl_CodesForm> comd=getFormationList("Command",command);
								Mmap.put("getCommandList",comd);
								
								String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
								List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
								Mmap.put("getCorpsList",corps);
								
								String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
								List<Tbl_CodesForm> Bn=getFormationList("Division",div);
								Mmap.put("getDivList",Bn);
								
								String bde_code = roleFormationNo;
								List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
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
						}if(roleAccess.equals("Line_dte")){
							List<Tbl_CodesForm> comd=all.getCommandDetailsList();
							Mmap.put("getCommandList",comd);
							String selectComd ="<option value=''>--Select--</option>";
							String select="<option value='0'>--Select--</option>";
							Mmap.put("selectcomd", selectComd);
							Mmap.put("selectcorps",select);
							Mmap.put("selectDiv",select);
							Mmap.put("selectBde",select);
							Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
						}
						
				    	String  roleid = sessionA.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIA", roleid);	
						if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						
						if(msg != null && msg.equals("ok")) {
							Mmap.put("sus_no1",sus_no12132);
							Mmap.put("unit_name1",unit_name12132);
							Mmap.put("cont_comd1",cont_comd12132);
							Mmap.put("cont_corps1",cont_corps12132);
							Mmap.put("cont_div1",cont_div12132);
							Mmap.put("cont_bde1",cont_bde12132);
							Mmap.put("line_dte_sus1",line_dte_sus12132);
							Mmap.put("location1",location12132);
						}else {
							sus_no12132 = "";
							unit_name12132 = "";
							cont_comd12132 = "";
							cont_corps12132 = "";
							cont_div12132 = "";
							cont_bde12132 = "";
							line_dte_sus12132 = "";
							location12132 = "";
							Mmap.put("msg", msg);
						}
						Mmap.put("getlocList", all.getLOCList());
						return new ModelAndView("OrbatDetails_Report_Pub_CTPART_IA_Tiles");
					}
				 
				 
				 	private String sus_no12132 ="";
					private String unit_name12132="";
					private String cont_comd12132 ="";
					private String cont_corps12132= "0";
					private String cont_div12132 = "0";
					private String cont_bde12132 = "0";
					private String line_dte_sus12132 = "0";
					private String location12132= "0";
					
					@RequestMapping(value="/admin/search_OrbatDetails_profileSetSession_Publications_CTPARTIA")
					public ModelAndView search_OrbatDetails_profileSetSession_Publications_CTPARTIA(ModelMap model,HttpServletRequest request,
							@RequestParam(value = "sus_no1", required = false) String sus_no1,
							@RequestParam(value = "unit_name1", required = false) String unit_name1,
							@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
							@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
							@RequestParam(value = "cont_div1", required = false) String cont_div1,
							@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
							@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
							@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
						
						String roleAccess = sessionA.getAttribute("roleAccess").toString();
						String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
						if(roleAccess.equals("Unit")){
							sus_no1 = roleSusNo;
						}
						
						String  roleid = sessionA.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIA", roleid);	
						if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						
						if(validation.checkUnit_nameLength(unit_name1)  == false){
					 		model.put("msg",validation.unit_nameMSG);
							return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartIA");
						}
						
						unit_name1 = unit_name1.replace("&#40;","(");
						unit_name1 = unit_name1.replace("&#41;",")");
						
						sus_no12132 = sus_no1;
						unit_name12132=unit_name1;
						cont_comd12132 = cont_comd1;
						cont_corps12132 = cont_corps1;
						cont_div12132 = cont_div1;
						cont_bde12132 = cont_bde1;
						line_dte_sus12132 = line_dte_sus1;
						location12132 = location1;
						model.put("msg", "ok");
						return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartIA");
					}
					
					
					 @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt_Publications_CTPART_I_A")
					    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt_Publications_CTPART_I_A(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
					    	String  roleid = sessionA.getAttribute("roleid").toString();
							Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIA", roleid);
							
							String roleAccess = sessionA.getAttribute("roleAccess").toString();
							String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
							String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
							String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
							
							System.err.println(" Inside orbat publication part 2 getOrbatDetails_Formation_Rpt_Publications_CTPARTII " );
							if(roleAccess.equals("Formation")) {
								if(roleSubAccess.equals("Command")) {
									String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
									cont_comd12132 = fcode_comd;
									
									if(!cont_bde12132.equals("0") && !cont_bde12132.equals("")){
										cont_bde12132 = fcode_comd+cont_bde12132.substring(1, 10);
							    	}else {
							    		if(!cont_div12132.equals("0") && !cont_div12132.equals("")){
							    			cont_div12132 = fcode_comd+cont_div12132.substring(1, 6);
								    	}else {
								    		if(!cont_corps12132.equals("0") && !cont_corps12132.equals("")){
								    			cont_corps12132 = fcode_comd+cont_corps12132.substring(1, 3);
								    		}else {
									    		if(!cont_comd12132.equals("-1") && !cont_comd12132.equals("")){
									    			cont_comd12132 = fcode_comd;
										    	}
									    	}
									    }
								    }
								}
								if(roleSubAccess.equals("Corps")) {
									String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
									cont_corps12132 = fcode_corps;
									
									if(!cont_bde12132.equals("0") && !cont_bde12132.equals("")){
										cont_bde12132 = fcode_corps+cont_bde12132.substring(3, 10);
							    	}else {
							    		if(!cont_div12132.equals("0") && !cont_div12132.equals("")){
							    			cont_div12132 = fcode_corps+cont_div12132.substring(3, 6);
								    	}else {
								    		if(!cont_corps12132.equals("0") && !cont_corps12132.equals("")){
								    			cont_corps12132 = fcode_corps;
									    	}else {
									    		if(!cont_comd12132.equals("-1") && !cont_comd12132.equals("")){
									    			cont_comd12132 = fcode_corps;
										    	}
									    	}
									    }
								    }
								}
								if(roleSubAccess.equals("Division")) {
									String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
									cont_div12132= fcode_div;
									
									if(!cont_bde12132.equals("0") && !cont_bde12132.equals("")){
										cont_bde12132 = fcode_div+cont_bde12132.substring(6, 10);
							    	}else {
							    		if(!cont_div12132.equals("0") && !cont_div12132.equals("")){
							    			cont_div12132 = fcode_div;
								    	}else {
								    		if(!cont_corps12132.equals("0") && !cont_corps12132.equals("")){
								    			cont_corps12132 = fcode_div;
									    	}else {
									    		if(!cont_comd12132.equals("-1") && !cont_comd12132.equals("")){
									    			cont_comd12132 = fcode_div;
										    	}
									    	}
									    }
								    }
								}
								if(roleSubAccess.equals("Brigade")) {
									cont_bde12132 = roleFormationNo;
								}
							}
							if(roleAccess.equals("Line_dte")){
								line_dte_sus12132 = roleSusNo;
							}
							if(val == false) {
								return null;
							}else {
								DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_A(criterias,sus_no12132,unit_name12132,cont_bde12132,cont_div12132,cont_corps12132,cont_comd12132,line_dte_sus12132,location12132); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
						    	return DatatablesResponse.build(dataSet, criterias);
							}
					    }
					 
					 
					 
			/*Orbat Publications Reports : - PArt I B */
					 
					    private String sus_no12133 ="";
						private String unit_name12133="";
						private String cont_comd12133 ="";
						private String cont_corps12133= "0";
						private String cont_div12133 = "0";
						private String cont_bde12133 = "0";
						private String line_dte_sus12133 = "0";
						private String location12133= "0";
			
						 /*jsp for publication part I B*/
						 @RequestMapping(value = "/OrbatDetails_Report_Formation_Pub_CTPartIB", method = RequestMethod.GET)
							public ModelAndView OrbatDetails_Report_Formation_Pub_CTPartIB(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
								String roleAccess = sessionA.getAttribute("roleAccess").toString();
								String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
								String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
								String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
						    	
								if(request.getHeader("Referer") == null ) {
									msg = "";
								}
								Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
								if(roleAccess.equals("Formation")){
									if(roleSubAccess.equals("Command")) {
										String formation_code = String.valueOf(roleFormationNo.charAt(0));
										List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
										Mmap.put("getCommandList",comd);
										List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
										Mmap.put("getCorpsList",corps);
										
										String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
										
										List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
										Mmap.put("getDivList",Bn);
										
										String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
										List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
										Mmap.put("getBdeList",bde);
										
										String select="<option value='0'>--Select--</option>";
										Mmap.put("selectcorps",select);
										Mmap.put("selectDiv",select);
										Mmap.put("selectBde",select);
										Mmap.put("selectLine_dte",select);
									} if(roleSubAccess.equals("Corps")) {
										String command = String.valueOf(roleFormationNo.charAt(0));
										List<Tbl_CodesForm> comd=getFormationList("Command",command);
										Mmap.put("getCommandList",comd);
										String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
										List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
										Mmap.put("getCorpsList",corps);
										List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
										Mmap.put("getDivList",Bn);
										
																				
										String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
										List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
										Mmap.put("getBdeList",bde);
										
										String select="<option value='0'>--Select--</option>";
										Mmap.put("selectDiv",select);
										Mmap.put("selectBde",select);
										Mmap.put("selectLine_dte",select);
									} if(roleSubAccess.equals("Division")) {
										String command = String.valueOf(roleFormationNo.charAt(0));
										List<Tbl_CodesForm> comd=getFormationList("Command",command);
										Mmap.put("getCommandList",comd);
										
										String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
										List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
										Mmap.put("getCorpsList",corps);
										
										String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
										List<Tbl_CodesForm> Bn=getFormationList("Division",div);
										Mmap.put("getDivList",Bn);
										
										List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
										Mmap.put("getBdeList",bde);
										
										String select="<option value='0'>--Select--</option>";
										Mmap.put("selectBde",select);
										
										Mmap.put("selectLine_dte",select);
									}if(roleSubAccess.equals("Brigade")) {
										String command = String.valueOf(roleFormationNo.charAt(0));
										List<Tbl_CodesForm> comd=getFormationList("Command",command);
										Mmap.put("getCommandList",comd);
										
										String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
										List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
										Mmap.put("getCorpsList",corps);
										
										String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
										List<Tbl_CodesForm> Bn=getFormationList("Division",div);
										Mmap.put("getDivList",Bn);
										
										String bde_code = roleFormationNo;
										List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
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
								}if(roleAccess.equals("Line_dte")){
									List<Tbl_CodesForm> comd=all.getCommandDetailsList();
									Mmap.put("getCommandList",comd);
									String selectComd ="<option value=''>--Select--</option>";
									String select="<option value='0'>--Select--</option>";
									Mmap.put("selectcomd", selectComd);
									Mmap.put("selectcorps",select);
									Mmap.put("selectDiv",select);
									Mmap.put("selectBde",select);
									Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
								}
								
						    	String  roleid = sessionA.getAttribute("roleid").toString();
								Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIB", roleid);	
								if(val == false) {
									return new ModelAndView("AccessTiles");
								}
								
								if(msg != null && msg.equals("ok")) {
									Mmap.put("sus_no1",sus_no12133);
									Mmap.put("unit_name1",unit_name12133);
									Mmap.put("cont_comd1",cont_comd12133);
									Mmap.put("cont_corps1",cont_corps12133);
									Mmap.put("cont_div1",cont_div12133);
									Mmap.put("cont_bde1",cont_bde12133);
									Mmap.put("line_dte_sus1",line_dte_sus12133);
									Mmap.put("location1",location12133);
								}else {
									sus_no12133 = "";
									unit_name12133 = "";
									cont_comd12133 = "";
									cont_corps12133 = "";
									cont_div12133= "";
									cont_bde12133 = "";
									line_dte_sus12133 = "";
									location12133 = "";
									Mmap.put("msg", msg);
								}
								Mmap.put("getlocList", all.getLOCList());
								return new ModelAndView("OrbatDetails_Report_Pub_CTPART_IB_Tiles");
							}
						 
						 
						 @RequestMapping(value="/admin/search_OrbatDetails_profileSetSession_Publications_CTPARTIB")
							public ModelAndView search_OrbatDetails_profileSetSession_Publications_CTPARTIB(ModelMap model,HttpServletRequest request,
									@RequestParam(value = "sus_no1", required = false) String sus_no1,
									@RequestParam(value = "unit_name1", required = false) String unit_name1,
									@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
									@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
									@RequestParam(value = "cont_div1", required = false) String cont_div1,
									@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
									@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus1,
									@RequestParam(value = "location1", required = false) String location1,HttpSession sessionA){
								
								String roleAccess = sessionA.getAttribute("roleAccess").toString();
								String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
								if(roleAccess.equals("Unit")){
									sus_no1 = roleSusNo;
								}
								
								String  roleid = sessionA.getAttribute("roleid").toString();
								Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIB", roleid);	
								if(val == false) {
									return new ModelAndView("AccessTiles");
								}
								
								if(validation.checkUnit_nameLength(unit_name1)  == false){
							 		model.put("msg",validation.unit_nameMSG);
									return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartIB");
								}
								
								unit_name1 = unit_name1.replace("&#40;","(");
								unit_name1 = unit_name1.replace("&#41;",")");
								
								sus_no12133 = sus_no1;
								unit_name12133=unit_name1;
								cont_comd12133 = cont_comd1;
								cont_corps12133 = cont_corps1;
								cont_div12133 = cont_div1;
								cont_bde12133 = cont_bde1;
								line_dte_sus12133 = line_dte_sus1;
								location12133 = location1;
								model.put("msg", "ok");
								return new ModelAndView("redirect:OrbatDetails_Report_Formation_Pub_CTPartIB");
							}
						 
						 
						 @RequestMapping(value = "/admin/getOrbatDetails_Formation_Rpt_Publications_CTPART_I_B")
						    public @ResponseBody DatatablesResponse<Map<String, Object>> getOrbatDetails_Formation_Rpt_Publications_CTPART_I_B(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
						    	String  roleid = sessionA.getAttribute("roleid").toString();
								Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation_Pub_CTPartIB", roleid);
								
								String roleAccess = sessionA.getAttribute("roleAccess").toString();
								String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
								String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
								String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
								
								System.err.println(" Inside orbat publication part 2 getOrbatDetails_Formation_Rpt_Publications_CTPARTII " );
								if(roleAccess.equals("Formation")) {
									if(roleSubAccess.equals("Command")) {
										String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
										cont_comd12133 = fcode_comd;
										
										if(!cont_bde12133.equals("0") && !cont_bde12133.equals("")){
											cont_bde12133 = fcode_comd+cont_bde12133.substring(1, 10);
								    	}else {
								    		if(!cont_div12133.equals("0") && !cont_div12133.equals("")){
								    			cont_div12133 = fcode_comd+cont_div12133.substring(1, 6);
									    	}else {
									    		if(!cont_corps12133.equals("0") && !cont_corps12133.equals("")){
									    			cont_corps12133 = fcode_comd+cont_corps12133.substring(1, 3);
									    		}else {
										    		if(!cont_comd12133.equals("-1") && !cont_comd12133.equals("")){
										    			cont_comd12133 = fcode_comd;
											    	}
										    	}
										    }
									    }
									}
									if(roleSubAccess.equals("Corps")) {
										String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
										cont_corps12133 = fcode_corps;
										
										if(!cont_bde12133.equals("0") && !cont_bde12133.equals("")){
											cont_bde12133 = fcode_corps+cont_bde12133.substring(3, 10);
								    	}else {
								    		if(!cont_div12133.equals("0") && !cont_div12133.equals("")){
								    			cont_div12133 = fcode_corps+cont_div12133.substring(3, 6);
									    	}else {
									    		if(!cont_corps12133.equals("0") && !cont_corps12133.equals("")){
									    			cont_corps12133 = fcode_corps;
										    	}else {
										    		if(!cont_comd12133.equals("-1") && !cont_comd12133.equals("")){
										    			cont_comd12133 = fcode_corps;
											    	}
										    	}
										    }
									    }
									}
									if(roleSubAccess.equals("Division")) {
										String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
										cont_div12133= fcode_div;
										
										if(!cont_bde12133.equals("0") && !cont_bde12133.equals("")){
											cont_bde12133 = fcode_div+cont_bde12133.substring(6, 10);
								    	}else {
								    		if(!cont_div12133.equals("0") && !cont_div12133.equals("")){
								    			cont_div12133 = fcode_div;
									    	}else {
									    		if(!cont_corps12133.equals("0") && !cont_corps12133.equals("")){
									    			cont_corps12133 = fcode_div;
										    	}else {
										    		if(!cont_comd12133.equals("-1") && !cont_comd12133.equals("")){
										    			cont_comd12133 = fcode_div;
											    	}
										    	}
										    }
									    }
									}
									if(roleSubAccess.equals("Brigade")) {
										cont_bde12133 = roleFormationNo;
									}
								}
								if(roleAccess.equals("Line_dte")){
									line_dte_sus12133 = roleSusNo;
								}
								if(val == false) {
									return null;
								}else {
									DataSet<Map<String, Object>> dataSet =  reportDAO.findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_B(criterias,sus_no12133,unit_name12133,cont_bde12133,cont_div12133,cont_corps12133,cont_comd12133,line_dte_sus12133,location12133); //,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121
							    	return DatatablesResponse.build(dataSet, criterias);
								}
						    }
						 /*AUTHORITY DOCUMENTS VIEW START*/
						 @RequestMapping(value = "/authorityDocument_View", method = RequestMethod.GET)
							public ModelAndView authorityDocument_View(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
								Mmap.put("getCommandList", all.getCommandDetailsList());
								
								String  roleid = sessionA.getAttribute("roleid").toString();
								/*Boolean val = roledao.ScreenRedirect("authorityDocument_View", roleid);	
								if(val == false) {
									return new ModelAndView("AccessTiles");
								}*/
								if(request.getHeader("Referer") == null ) {
									msg = "";
								}
								System.err.println("HERE IN THE JSP  OF AUTHORITY DOCUMENT VIEW");
								if(msg != null && msg.equals("ok")) {
									Mmap.put("sus_no1",sus_no11);
									Mmap.put("unit_name1",unit_name11);
									Mmap.put("cont_comd1",cont_comd11);
									Mmap.put("cont_corps1",cont_corps11);
									Mmap.put("cont_div1",cont_div11);
									Mmap.put("cont_bde1",cont_bde11);
									Mmap.put("status_sus_no1",status_sus_no11);
									Mmap.put("action1",action11);
									Mmap.put("from1",approved_rejected_on11);
									Mmap.put("to1",approved_rejected_on21);
									Mmap.put("letter_no",letter_no21);
								}else {
									sus_no11 = "";
									unit_name11 = "";
									cont_comd11 = "";
									cont_corps11 = "";
									cont_div11 = "";
									cont_bde11 = "";
									status_sus_no11 = "";
									action11 = "";
									approved_rejected_on11 = "";
									approved_rejected_on21 = "";
									letter_no21 = "";
									Mmap.put("msg", msg);
								}
								Mmap.put("getType_Of_LetterList", all.getType_Of_LetterList());
								return new ModelAndView("authorityDocument_ViewTiles");
							}
						 
			private String sus_no11 ="";
			private String unit_name11="";
			private String cont_comd11 ="";
			private String cont_corps11 = "0";
			private String cont_div11 = "0";
			private String cont_bde11 = "0";
			private String status_sus_no11 = "";
			private String action11 = "";
			private String approved_rejected_on11 = "";
			private String approved_rejected_on21 = "";
			private String letter_no21 = "";
							
							@RequestMapping(value="/admin/search_authDocument_profileSetSession")
							public ModelAndView search_authDocument_profileSetSession(ModelMap model,HttpServletRequest request,
									@RequestParam(value = "sus_no1", required = false) String sus_no1,
									@RequestParam(value = "unit_name1", required = false) String unit_name1,
									@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
									@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
									@RequestParam(value = "cont_div1", required = false) String cont_div1,
									@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
									@RequestParam(value = "status_sus_no1", required = false) String status_sus_no1,
									@RequestParam(value = "action1", required = false) String action1,
									@RequestParam(value = "from1", required = false) String from1,
									@RequestParam(value = "auth_letter1", required = false) String letter_no,
									@RequestParam(value = "to1", required = false) String to1,HttpSession sessionA){
								
								String roleAccess = sessionA.getAttribute("roleAccess").toString();
								String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
								if(roleAccess.equals("Unit")){
									sus_no1 = roleSusNo;
								}
								
								String  roleid = sessionA.getAttribute("roleid").toString();
								Boolean val = roledao.ScreenRedirect("authorityDocument_View", roleid);	
								if(val == false) {
									return new ModelAndView("AccessTiles");
								}
								if(validation.checkUnit_nameLength(unit_name1)  == false){
							 		model.put("msg",validation.unit_nameMSG);
									return new ModelAndView("redirect:authorityDocument_View");
								}
								
								unit_name1 = unit_name1.replace("&#40;","(");
								unit_name1 = unit_name1.replace("&#41;",")");
								
								action1 = action1.replace("&#40;","(");
								action1 = action1.replace("&#41;",")");
								
								sus_no11 = sus_no1;
								unit_name11=unit_name1;
								cont_comd11 = cont_comd1;
								cont_corps11 = cont_corps1;
								cont_div11 = cont_div1;
								cont_bde11 = cont_bde1;
								status_sus_no11 = status_sus_no1;
								action11 = action1;
								approved_rejected_on11 = from1;
								approved_rejected_on21 = to1;
								letter_no21 = letter_no;
								model.put("msg", "ok");
								return new ModelAndView("redirect:authorityDocument_View");
							}
							
							 @RequestMapping(value = "/admin/getAuthoritySearchRpt")
							    public @ResponseBody DatatablesResponse<Map<String, Object>> getAuthoritySearchRpt(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionA, HttpServletRequest request) {
							    	
							    	String  roleid = sessionA.getAttribute("roleid").toString();
									Boolean val = roledao.ScreenRedirect("authorityDocument_View", roleid);	
									if(val == false) {
										return null;
									}
									if(!status_sus_no11.equals("")){
							    		DataSet<Map<String, Object>> dataSet = reportDAO.findAuthorityReportWithDatatablesCriterias(criterias,status_sus_no11,sus_no11,unit_name11,cont_bde11,cont_div11,cont_corps11,action11,approved_rejected_on11,approved_rejected_on21,cont_comd11,letter_no21);
								    	return DatatablesResponse.build(dataSet, criterias);
							    	}else {
							    		return null;
							    	}
							    } 
}