package com.controller.psg.Queries;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.ExportFile.Excel_To_Export_2_Table_Report;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.Dashboard.PSGDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.PSG_non_entriesDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.Tbl_CodesForm;
import com.models.UserLogin;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Psgnon_entrtiesController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public String pageType = "";
	public String pageTypeU = "";
	
	@Autowired
	PSG_non_entriesDAO adminDash;
    AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	////bisag v2 190922(new screen)
	@RequestMapping(value = "/admin/psgQueries_non", method = RequestMethod.GET)
	public ModelAndView psgQueries_non(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("psgQueries_non", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
      Mmap.put("msg", msg);
		
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		
   		 if(roleAccess.equals("Unit")){
   				Mmap.put("sus_no",roleSusNo);
   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
   			}
   		 
   		 
   		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
   		//String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",formation_code,"","","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
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
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
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
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
		 if(roleAccess.equals("Unit")){
			 //String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				Mmap.put("unit_sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
				roleFormationNo = formation.get(0);
				
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
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
		 }
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		
    	/*String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
	
		Mmap.put("getRoleNameList", getRoleNameList());
		Mmap.put("msg", msg);
   		 
		Mmap.put("date", date1);	
		Mmap.put("msg", msg);
		
		return new ModelAndView("psg_non_entriesTiles");
	}
	
	public List<UserLogin> getRoleNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from UserLogin order by userName");
			@SuppressWarnings("unchecked")
			List<UserLogin> list = (List<UserLogin>) q.list();
			tx.commit();
			session.close();
			return list;
		}catch (Exception e) {
			session.close();
			return null;
		}
	}
	
	
	
	 @RequestMapping(value = "/Excel_data_updated2_non", method = RequestMethod.POST)
		public ModelAndView Excel_data_updated2_non(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("cont_comd_ex1") String cont_comd,@ModelAttribute("cont_corps_ex1") String cont_corps,@ModelAttribute("cont_div_ex1") String cont_div,
				@ModelAttribute("cont_bde_ex1") String cont_bde,
				@ModelAttribute("cont_comd_txt1") String cont_comd_txt,@ModelAttribute("cont_corps_txt1") String cont_corps_txt,@ModelAttribute("cont_div_txt1") String cont_div_txt,
				@ModelAttribute("cont_bde_txt1") String cont_bde_txt,
				@ModelAttribute("unit_name_ex1") String unit_name,@ModelAttribute("sus_no_ex1") String sus_no,
				@RequestParam(value = "msg", required = false) String msg) {

		   String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 ArrayList<ArrayList<String>> Excel = adminDash.search_jco_en_table( cont_comd, cont_corps, cont_div, cont_bde, unit_name, sus_no);
			
		 List<String> TH = new ArrayList<String>();
			
			TH.add("Command Name");
			TH.add("Corps/Area Name");
			TH.add("Division name ");
			TH.add("Brigade  Name");
			TH.add("Unit Name");
			TH.add("Approved");
			TH.add("Pending");
			TH.add("TOTAL ");
			
		
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}
	
	 @RequestMapping(value = "/Excel_data_updated3_non", method = RequestMethod.POST)
		public ModelAndView Excel_data_updated3_non(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("cont_comd_ex2") String cont_comd,@ModelAttribute("cont_corps_ex2") String cont_corps,@ModelAttribute("cont_div_ex2") String cont_div,
				@ModelAttribute("cont_bde_ex2") String cont_bde,
				@ModelAttribute("cont_comd_txt2") String cont_comd_txt,@ModelAttribute("cont_corps_txt2") String cont_corps_txt,@ModelAttribute("cont_div_txt2") String cont_div_txt,
				@ModelAttribute("cont_bde_txt2") String cont_bde_txt,
				@ModelAttribute("unit_name_ex2") String unit_name,@ModelAttribute("sus_no_ex2") String sus_no) {

		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 ArrayList<ArrayList<String>> Excel = adminDash.search_civilian_en_table( cont_comd, cont_corps, cont_div, cont_bde, unit_name, sus_no);
		 List<String> TH = new ArrayList<String>();
			
			TH.add("Command Name");
			TH.add("Corps/Area Name");
			TH.add("Division name ");
			TH.add("Brigade  Name");
			TH.add("Unit Name");
			TH.add("Approved");
			TH.add("Pending");
			TH.add("TOTAL ");
			
		
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}
	
	
	 @RequestMapping(value = "/Excel_data_updated_non", method = RequestMethod.POST)
		public ModelAndView Excel_data_updated_non(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("cont_comd_ex") String cont_comd,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
				@ModelAttribute("cont_bde_ex") String cont_bde,
				@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
				@ModelAttribute("cont_bde_txt") String cont_bde_txt,
				@ModelAttribute("unit_name_ex") String unit_name,@ModelAttribute("sus_no_ex") String sus_no,
				@RequestParam(value = "msg", required = false) String msg) {

		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("psgDashboard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		  ArrayList<ArrayList<String>> Excel = adminDash.search_officer_en_table(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		
			
		 List<String> TH = new ArrayList<String>();
			
			TH.add("Command Name");
			TH.add("Corps/Area Name");
			TH.add("Division name ");
			TH.add("Brigade  Name");
			TH.add("Unit Name");
			TH.add("Approved");
			TH.add("Pending");
			TH.add("TOTAL ");
			
			String Heading = "\nData Updated Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}
	 ///officer
	 

		////bisag v2 200922(converted in data table )
	 @RequestMapping(value = "/search_officer_en_tablecount_non", method = RequestMethod.POST)
	 public @ResponseBody long search_officer_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 	
	 	return adminDash.getsearch_officer_en_tablecount_non(Search,orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
	 			 unit_name, unit_sus_no, user_role_id, from_date, to_date);
	 }
	 @RequestMapping(value = "/search_officer_en_table_non", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> search_officer_en_table_non(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	 		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
	 		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
	 		
	 		if(roleAccess.equals("Formation")) {
	 			if(roleSubAccess.equals("Command")) {
	 				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
	 				cont_comd = fcode_comd;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_comd+cont_bde.substring(1, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_comd+cont_div.substring(1, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
	 			    		}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_comd;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Corps")) {
	 				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
	 				cont_corps = fcode_corps;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_corps+cont_bde.substring(3, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_corps+cont_div.substring(3, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_corps;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_corps;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Division")) {
	 				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	 				cont_div = fcode_div;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_div+cont_bde.substring(6, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_div;
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_div;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_div;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Brigade")) {
	 				cont_bde = roleFormationNo;
	 			}
	 		}
	 	return adminDash.getsearch_officer_en_table_non(startPage, pageLength,Search,orderColunm, orderType,sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
	 			 unit_name, unit_sus_no, user_role_id, from_date, to_date);
	 }
	 		/////jco

	 ////bisag v2 200922(converted in data table )
	  @RequestMapping(value = "/search_jco_en_tablecount_non", method = RequestMethod.POST)
	 public @ResponseBody long search_jco_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 	
	 	return adminDash.getsearch_jco_en_tablecount_non(Search,orderColunm, orderType, sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
	 			unit_name, unit_sus_no, user_role_id, from_date, to_date);
	 }
	 @RequestMapping(value = "/search_jco_en_table_non", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> search_jco_en_table_non(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	 		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
	 		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
	 		
	 		if(roleAccess.equals("Formation")) {
	 			if(roleSubAccess.equals("Command")) {
	 				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
	 				cont_comd = fcode_comd;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_comd+cont_bde.substring(1, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_comd+cont_div.substring(1, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
	 			    		}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_comd;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Corps")) {
	 				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
	 				cont_corps = fcode_corps;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_corps+cont_bde.substring(3, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_corps+cont_div.substring(3, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_corps;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_corps;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Division")) {
	 				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	 				cont_div = fcode_div;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_div+cont_bde.substring(6, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_div;
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_div;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_div;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Brigade")) {
	 				cont_bde = roleFormationNo;
	 			}
	 		}
	 	return adminDash.getsearch_jco_en_table_non(startPage, pageLength, Search,orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
	 			unit_name, unit_sus_no, user_role_id, from_date, to_date);
	 }
	 			//////civ
	 ////bisag v2 200922(converted in data table )
	 @RequestMapping(value = "/search_civilian_en_tablecount_non", method = RequestMethod.POST)
	 public @ResponseBody long search_civilian_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 	
	 	return adminDash.getsearch_civilian_en_tablecount_non(Search,orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
	 			unit_name, unit_sus_no, user_role_id, from_date, to_date);
	 }
	 				
	 @RequestMapping(value = "/search_civilian_en_table_non", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> search_civilian_en_table_non(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
	 		,String cont_comd,String cont_corps,String cont_div,String cont_bde,
	 		String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) throws SQLException {
	 	 String roleType = sessionUserId.getAttribute("roleType").toString();
	 		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	 		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
	 		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
	 		
	 		if(roleAccess.equals("Formation")) {
	 			if(roleSubAccess.equals("Command")) {
	 				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
	 				cont_comd = fcode_comd;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_comd+cont_bde.substring(1, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_comd+cont_div.substring(1, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
	 			    		}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_comd;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Corps")) {
	 				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
	 				cont_corps = fcode_corps;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_corps+cont_bde.substring(3, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_corps+cont_div.substring(3, 6);
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_corps;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_corps;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Division")) {
	 				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	 				cont_div = fcode_div;
	 				
	 				if(!cont_bde.equals("0") && !cont_bde.equals("")){
	 					cont_bde = fcode_div+cont_bde.substring(6, 10);
	 		    	}else {
	 		    		if(!cont_div.equals("0") && !cont_div.equals("")){
	 		    			cont_div = fcode_div;
	 			    	}else {
	 			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	 			    			cont_corps = fcode_div;
	 				    	}else {
	 				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	 				    			cont_comd = fcode_div;
	 					    	}
	 				    	}
	 				    }
	 			    }
	 			}
	 			if(roleSubAccess.equals("Brigade")) {
	 				cont_bde = roleFormationNo;
	 			}
	 		}
	 	return adminDash.getsearch_civilian_en_table_non(startPage, pageLength,Search, orderColunm, orderType,sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
	 			unit_name, unit_sus_no, user_role_id, from_date, to_date);
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


}
