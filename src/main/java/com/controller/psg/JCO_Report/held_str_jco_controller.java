package com.controller.psg.JCO_Report;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.JCO_ExportFile.Excel_To_Export_Download_Search_Report_jco_Table_Report;
import com.controller.JCO_ExportFile.pdf_Download_Search_Report_jco_holding1111_query;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Report.Held_str_jco_JCO_DAO;
import com.dao.psg.JCO_Report.JCO_Regular_ArmyPersonnel_DAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class held_str_jco_controller {
	
	
	Psg_CommonController mcommon = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	@Autowired
	JCO_Regular_ArmyPersonnel_DAO reg;
	
	@Autowired
	Held_str_jco_JCO_DAO held;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	
	 /// bisag v2 050922  (converted to Datatable)
	@RequestMapping(value = "/admin/Held_str_jco_JCO_Url", method = RequestMethod.GET)
		public ModelAndView Held_str_jco_JCO_Url(ModelMap Mmap, HttpSession sessionA,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Held_str_jco_JCO_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				 String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				 String roleAccess = sessionA.getAttribute("roleAccess").toString();
				
		   		 if(roleAccess.equals("Unit")){
		   				Mmap.put("sus_no",roleSusNo);
		   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		   			}
		   		 
		   		 
		   		Date date = new Date();
				String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		   		//String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
				String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
				
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
						Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
						
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
			
				
				Mmap.put("msg", msg);
		   		 
				Mmap.put("date", date1);	
				Mmap.put("msg", msg);
			return new ModelAndView("held_str_jco_Tiles_jco");
		}
	
	@RequestMapping(value = "/admin/GetSearch_Report_jco_holding", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_Serving(ModelMap Mmap, HttpSession session,HttpSession sessionUserId,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year
			) throws ServletException, IOException, ParseException {

	
		 if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Held_str_jco_JCO_Url");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Held_str_jco_JCO_Url");
				}
	      }
		 
		 if (unit_sus_no==null || unit_sus_no.trim().equals("")) { 
				Mmap.put("msg","Please Enter Sus No");
				return new ModelAndView("redirect:Held_str_jco_JCO_Url"); 
		 }
		 if (unit_name==null || unit_name.trim().equals("")) { 
				Mmap.put("msg","Please Enter Unit Name");
				return new ModelAndView("redirect:Held_str_jco_JCO_Url"); 
		 }
		 if (month==null || month.trim().equals("")) { 
				Mmap.put("msg","Please Enter Month");
				return new ModelAndView("redirect:Held_str_jco_JCO_Url"); 
		 }
		 if (year==null || year.trim().equals("")) { 
				Mmap.put("msg","Please Enter Year");
				return new ModelAndView("redirect:Held_str_jco_JCO_Url"); 
		 }
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		String Month1 = "00";
		
	
		
		
		
		String FDate = year+"-"+month+ "-" + "01";
		
			
	
				ArrayList<ArrayList<String>> list_sup = held.Search_Report_auth(unit_sus_no,unit_name,"",session);		
				Mmap.put("list", list_sup);
				Mmap.put("size", list_sup.size());	
				
				ArrayList<ArrayList<String>> list_sup1 = held.Search_Report_held_str(unit_sus_no,unit_name,FDate,session);		
				Mmap.put("list2", list_sup1);
				Mmap.put("size2", list_sup1.size());	
				
				ArrayList<ArrayList<String>> list_re_employ = held.Search_Report_Deserter(unit_sus_no,unit_name,FDate,session);	
				Mmap.put("list4", list_re_employ);
				Mmap.put("size3", list_re_employ.size());	
				
				ArrayList<ArrayList<String>> list6 = held.Search_Report_attachment(unit_sus_no,unit_name,FDate,session);	
				Mmap.put("list6", list6);	
				Mmap.put("size6", list6.size());	

			
			 
				   
					
					
					
						
	  		
		Mmap.put("sus_no", roleSusNo);
		
		Mmap.put("unit_name", unit_name);
		Mmap.put("month1", month);
		Mmap.put("month1", month);
		
	    
		return new ModelAndView("held_str_jco_Tiles_jco");
	}
	
/*	@RequestMapping(value = "/getheld_str_jco_reporttblA_ReportDataList_JCO", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getheld_str_jco_reporttblA_ReportDataList_JCO(int startPage,String pageLength,String Search,String orderColunm,String orderType,			
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 return held.getheld_str_jco_reporttblA_ReportData_JCO(startPage,pageLength,Search,orderColunm,orderType,
				 sessionUserId);
	}
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblA_TotalCount_jco", method = RequestMethod.POST)
	 public @ResponseBody long getheld_str_jco_reporttblA_TotalCount_jco(HttpSession sessionUserId,String Search){
	 	return held.getheld_str_jco_reporttblA_TotalCount_jco(Search);
	 }
	 
	 
	
	 @RequestMapping(value = "/getheld_str_jco_reporttblB_ReportDataList_JCO", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getheld_str_jco_reporttblB_ReportDataList_JCO(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		 
		
		 return held.getheld_str_jco_reporttblB_ReportDataList_JCO(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblB_TotalCount_jco", method = RequestMethod.POST)
	 public @ResponseBody long getheld_str_jco_reporttblB_TotalCount_jco(HttpSession sessionUserId,String Search){
	 	return held.getheld_str_jco_reporttblB_TotalCount_jco(Search);
	 }
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblC_ReportDataList_JCO", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getheld_str_jco_reporttblC_ReportDataList_JCO(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		 
		
		 return held.getheld_str_jco_reporttblC_ReportDataList_JCO(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblC_TotalCount_jco", method = RequestMethod.POST)
	 public @ResponseBody long getheld_str_jco_reporttblC_TotalCount_jco(HttpSession sessionUserId,String Search){
	 	return held.getheld_str_jco_reporttblC_TotalCount_jco(Search);
	 }
	 
	 
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblD_ReportDataList_jco", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getheld_str_jco_reporttblD_ReportDataList_jco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		 
		
		 return held.getheld_str_jco_reporttblD_ReportDataList_jco(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getheld_str_jco_reporttblD_TotalCount_jco", method = RequestMethod.POST)
	 public @ResponseBody long getheld_str_jco_reporttblD_TotalCount_jco(HttpSession sessionUserId,String Search){
	 	return held.getheld_str_jco_reporttblD_TotalCount_jco(Search);
	 }
	 
	 @RequestMapping(value = "/Download_held_str_query_jco",method = RequestMethod.POST)
		public ModelAndView Download_held_str_query_jco(				
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
			ArrayList<ArrayList<String>> pdfprintA = held.pdf_getheld_str_jco_reportA_jco();
			ArrayList<ArrayList<String>> pdfprintB = held.pdf_getheld_str_jco_reportB_jco();
			ArrayList<ArrayList<String>> pdfprintC = held.pdf_getheld_str_jco_reportC_jco();
			ArrayList<ArrayList<String>> pdfprintD = held.pdf_getheld_str_jco_reportD_jco();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("SER NO");
				TH.add("ARM_DESCRIPTION");
				TH.add("AUTH");
				TH.add("TOTAL");
				
				
				List<String> TH1 = new ArrayList<String>();
				TH1.add("SER NO");
				TH1.add("ARM_DESCRIPTION");
				TH1.add("AUTH");
				TH1.add("TOTAL");
								
			return new ModelAndView(new JCO_PDF_held_str_query(Heading,TH,TH1,foot,username,pdfprintA,pdfprintB),"userList",pdfprintA);
		}

	 @RequestMapping(value = "/Excel_held_str_query_jco", method = RequestMethod.POST)
		public ModelAndView Excel_held_str_query_jco(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		 		
		 ArrayList<ArrayList<String>> Excel = reg.pdf_getRegular_ArmyGorkha_ReportDataList_jco();
		 ArrayList<ArrayList<String>> Excel1 = reg.pdf_getRegular_ArmyInfantary_ReportDataList_pers_jco();
			
		 List<String> TH = new ArrayList<String>();
			TH.add("SER NO");
			TH.add("ARM_DESCRIPTION");
			TH.add("Auth");
			TH.add("TOTAL");
					
			List<String> TH1 = new ArrayList<String>();
			TH1.add("SER NO");
			TH1.add("ARM_DESCRIPTION");
			TH1.add("Auth");
			TH1.add("TOTAL");
			
			String Heading = "\nSTR OF REGULAR ARMY PERSONNEL";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_2_Table_Report("L", TH,TH1, Heading, username,Excel,Excel1), "userList", Excel );
		}*/
	
	/// bisag 180822 v1 (formation wise data)
	
	//bisag v2 2208022 (formation wise get data with datatable)		

	@RequestMapping(value = "/admin/Mon_str_jcos_or_Url", method = RequestMethod.GET)
			public ModelAndView Mon_str_jcos_or_Url(ModelMap Mmap, HttpSession sessionA,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				String roleid = sessionA.getAttribute("roleid").toString();
				String unit_sus_no = sessionA.getAttribute("roleSusNo").toString();
				
				Boolean val = roledao.ScreenRedirect("Mon_str_jcos_or_Url", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}

				Date date = new Date();
				String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
				
				 if(request.getHeader("Referer") == null ) { msg = ""; }
					
				 String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				 String roleAccess = sessionA.getAttribute("roleAccess").toString();
				
		   		 if(roleAccess.equals("Unit")){
		   				Mmap.put("sus_no",roleSusNo);
		   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		   			}
		   		 
		   		 
		   		 
		   		//String roleAccess = sessionA.getAttribute("roleAccess").toString();
				String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
				String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
				
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
						Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
						
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
			
				
				Mmap.put("msg", msg);
		   		 
				Mmap.put("date", date1);	
				Mmap.put("msg", msg);
				
				return new ModelAndView("mon_str_jcos_or_Tiles");
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
			
			
			

		
		 @RequestMapping(value = "/Download_Search_Report_jco_holding1111",method = RequestMethod.POST)
			public ModelAndView Download_Search_Report_jco_holding1111(
					@ModelAttribute("cont_comd_dn") String cont_comd,@ModelAttribute("cont_corps_dn") String cont_corps,@ModelAttribute("cont_div_dn") String cont_div,
					@ModelAttribute("cont_bde_dn") String cont_bde,
					@ModelAttribute("cont_comd_tx") String cont_comd_tx,@ModelAttribute("cont_corps_tx") String cont_corps_tx,@ModelAttribute("cont_div_tx") String cont_div_tx,
					@ModelAttribute("cont_bde_tx") String cont_bde_tx,
					@ModelAttribute("unit_name_dn") String unit_name,@ModelAttribute("sus_no_dn") String sus_no,
					ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {	
			
			 String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Mon_str_jcos_or_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
			 
				ArrayList<ArrayList<String>> pdfprint = held.pdf_Download_Search_Report_jco_holding1111(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,sessionEdit);
				
				
				String username =  sessionEdit.getAttribute("username").toString();
				Mmap.put("msg", msg);
				String Heading ="";
				Date date = new Date();
				String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
				
				//if(pdfprint.size() > 0) {
					List<String> TH = new ArrayList<String>();
					
					TH.add("Ser No");	                         
					TH.add(" Present Rank");                     
					TH.add("Name");
					TH.add("Army No");			                       
					TH.add("Trade");
					TH.add("Regt/Corps");
					TH.add("Shape");
					TH.add("Dt TOS");
					TH.add("Dt of Birth ");
					TH.add(" Dt of Enrollment");
					TH.add("Dt of Attestation ");
					TH.add("Dt of Seniority ");
					TH.add(" Remarks ");
					
					
					
				if(cont_comd_tx.equals("--Select--")) {
					cont_comd_tx = "";
				}
				if(cont_corps_tx.equals("--Select--")) {
					cont_corps_tx = "";		
				}
				if(cont_div_tx.equals("--Select--")) {
					cont_div_tx = "";
				}
				if(cont_bde_tx.equals("--Select--")) {
					cont_bde_tx = "";
				}
				
				return new ModelAndView(new pdf_Download_Search_Report_jco_holding1111_query(Heading,TH,foot,username,pdfprint,cont_comd_tx,cont_corps_tx,cont_div_tx,cont_bde_tx,unit_name,sus_no),"userList",pdfprint);
			}
		 
		 @RequestMapping(value = "/Excel_Search_Report_jco_holding1111", method = RequestMethod.POST)
			public ModelAndView Excel_Search_Report_jco_holding1111(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
					@ModelAttribute("cont_comd_ex") String cont_comd,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
					@ModelAttribute("cont_bde_ex") String cont_bde,@ModelAttribute("blood_group_txt") String blood_group_txt,
					@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
					@ModelAttribute("cont_bde_txt") String cont_bde_txt,
					@ModelAttribute("unit_name_ex") String unit_name,@ModelAttribute("sus_no_ex") String sus_no,
					@RequestParam(value = "msg", required = false) String msg) {

			    String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Mon_str_jcos_or_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
				ArrayList<ArrayList<String>> Excel = held.pdf_Download_Search_Report_jco_holding1111(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,session);
			
			 List<String> TH = new ArrayList<String>();
			 TH.add("Ser No");	                         
				TH.add(" Present Rank");                     
				TH.add("Name");
				TH.add("Army No");			                       
				TH.add("Trade");
				TH.add("Regt/Corps");
				TH.add("Shape");
				TH.add("Dt TOS");
				TH.add("Dt of Birth ");
				TH.add(" Dt of Enrollment");
				TH.add("Dt of Attestation ");
				TH.add("Dt of Seniority ");
				TH.add(" Remarks ");
				
				String username = session.getAttribute("username").toString();
				return new ModelAndView(new Excel_To_Export_Download_Search_Report_jco_Table_Report("L", TH, username,Excel), "userList", Excel );
			}

		 
		 
		 
		 
		
		 
		 @RequestMapping(value = "/Getsearch_heldthstrCount", method = RequestMethod.POST)
			public @ResponseBody long Getsearch_heldthstrCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
					,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no) throws SQLException {
				// String roleType = sessionUserId.getAttribute("roleType").toString();
				
				return held.Getsearch_heldthstrCountCount(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
						unit_name, unit_sus_no);
			}
			

			
			@RequestMapping(value = "/Getsearch_heldthstrdata", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> Getsearch_healthstrdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
					,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no) throws SQLException {
				// String roleType = sessionUserId.getAttribute("roleType").toString();
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
		
				return held.Getsearch_heldthstrdata(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
						unit_name, unit_sus_no);
			}
			
			 /// bisag v2 050922  (converted to Datatable)

			 @RequestMapping(value = "/Getsearch_heldthstrCount_a", method = RequestMethod.POST)
					public @ResponseBody long Getsearch_heldthstrCount_a(String Search,String orderColunm,String orderType,HttpSession sessionUserId
							,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
							String unit_name,String unit_sus_no,String month,String year) throws SQLException {
						// String roleType = sessionUserId.getAttribute("roleType").toString();
					 String FDate = year+"-"+month+ "-" + "01";
						return held.Search_Report_auth_count_a(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
								unit_name, unit_sus_no,FDate);
					}
					

					
					@RequestMapping(value = "/Getsearch_heldthstrdata_a", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>> Getsearch_heldthstrdata_a(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
							,String cont_comd,String cont_corps,String cont_div,String cont_bde,
							String unit_name,String unit_sus_no,String month,String year) throws SQLException {
						// String roleType = sessionUserId.getAttribute("roleType").toString();
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
						 String FDate = year+"-"+month+ "-" + "01";
						return held.Search_Report_auth_a(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
								unit_name, unit_sus_no,FDate);
					}			
	////////b
					 @RequestMapping(value = "/Getsearch_heldthstrCount_b", method = RequestMethod.POST)
						public @ResponseBody long Getsearch_heldthstrCount_b(String Search,String orderColunm,String orderType,HttpSession sessionUserId
								,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
								String unit_name,String unit_sus_no,String month,String year) throws SQLException {
							// String roleType = sessionUserId.getAttribute("roleType").toString();
						 String FDate = year+"-"+month+ "-" + "01";
							return held.Search_Report_held_strCount_b(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
									unit_name, unit_sus_no,FDate);
						}

					 

						
						@RequestMapping(value = "/Getsearch_heldthstrdata_b", method = RequestMethod.POST)
						public @ResponseBody List<Map<String, Object>> Getsearch_heldthstrdata_b(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
								,String cont_comd,String cont_corps,String cont_div,String cont_bde,
								String unit_name,String unit_sus_no,String month,String year) throws SQLException {
							// String roleType = sessionUserId.getAttribute("roleType").toString();
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
							 String FDate = year+"-"+month+ "-" + "01";
							return held.Search_Report_held_strdata_b(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
									unit_name, unit_sus_no,FDate);
						}			
					
						////////////////c
						
						
						
						
						
						 @RequestMapping(value = "/Getsearch_heldthstrCount_c", method = RequestMethod.POST)
							public @ResponseBody long Getsearch_heldthstrCount_c(String Search,String orderColunm,String orderType,HttpSession sessionUserId
									,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
									String unit_name,String unit_sus_no,String month,String year) throws SQLException {
								// String roleType = sessionUserId.getAttribute("roleType").toString();
							 String FDate = year+"-"+month+ "-" + "01";
								return held.Search_Report_Desertercount_c(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
										unit_name, unit_sus_no,FDate);
							}
							

							
							@RequestMapping(value = "/Getsearch_heldthstrdata_c", method = RequestMethod.POST)
							public @ResponseBody List<Map<String, Object>> Getsearch_heldthstrdata_c(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
									,String cont_comd,String cont_corps,String cont_div,String cont_bde,
									String unit_name,String unit_sus_no,String month,String year) throws SQLException {
								// String roleType = sessionUserId.getAttribute("roleType").toString();
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
								 String FDate = year+"-"+month+ "-" + "01";
								return held.Search_Report_Deserter_c(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
										unit_name, unit_sus_no,FDate);
							}			
						
											
							///d 
							 @RequestMapping(value = "/Getsearch_heldthstrCount_d", method = RequestMethod.POST)
								public @ResponseBody long Getsearch_heldthstrCount_d(String Search,String orderColunm,String orderType,HttpSession sessionUserId
										,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde,
										String unit_name,String unit_sus_no,String month,String year) throws SQLException {
									// String roleType = sessionUserId.getAttribute("roleType").toString();
								 String FDate = year+"-"+month+ "-" + "01";
									return held.Search_Report_attachmentCount_d(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
											unit_name, unit_sus_no,FDate);
								}
								

								
								@RequestMapping(value = "/Getsearch_heldthstrdata_d", method = RequestMethod.POST)
								public @ResponseBody List<Map<String, Object>> Getsearch_heldthstrdata_d(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
										,String cont_comd,String cont_corps,String cont_div,String cont_bde,
										String unit_name,String unit_sus_no,String month,String year) throws SQLException {
									// String roleType = sessionUserId.getAttribute("roleType").toString();
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
									 String FDate = year+"-"+month+ "-" + "01";
									return held.Search_Report_attachment_d(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,cont_comd, cont_corps, cont_div, cont_bde,
											unit_name, unit_sus_no,FDate);
								}	
			
}
