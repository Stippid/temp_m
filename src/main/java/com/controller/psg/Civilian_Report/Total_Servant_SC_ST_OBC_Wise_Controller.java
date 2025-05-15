package com.controller.psg.Civilian_Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.ExportFile.Excel_To_Export_2_Table_Report;
import com.controller.ExportFile.PDF_Total_Servant_SC_ST_OBC_Wise;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Report.Total_Servant_SC_ST_OBC_Wise_DAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Total_Servant_SC_ST_OBC_Wise_Controller {

	//CommanController m = new CommanController();
	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
//	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	private Total_Servant_SC_ST_OBC_Wise_DAO TSDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Total_Servant_SC_ST_OBC_Wise_Url", method = RequestMethod.GET)
	public ModelAndView Total_Servant_SC_ST_OBC_Wise_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Total_Servant_SC_ST_OBC_Wise_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getCommandList",m.getCommandDetailsList());
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= mcommon.getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=mcommon.getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=mcommon.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=mcommon.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=mcommon.getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=mcommon.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=mcommon.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=mcommon.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=mcommon.getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=mcommon.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=mcommon.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=mcommon.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = mcommon.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
			}
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
			Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			Mmap.put("date", date1);
		}
		
    	
		Mmap.put("msg", msg);
		Mmap.put("getServiceStatusList", mcommon.getServiceStatusList());
		return new ModelAndView("Total_Servant_SC_ST_OBC_Wise_Tile");
	}
	
	 @RequestMapping(value = "/getPer_Temp_SC_ST_OBC_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getPer_Temp_SC_ST_OBC_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status,HttpSession sessionUserId) 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{		
		 
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
		 
		 return TSDAO.getPer_Temp_SC_ST_OBC_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,cont_comd,cont_corps,
				 cont_div,cont_bde,unit_name,sus_no,service_status,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getPer_Temp_SC_ST_OBC_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getPer_Temp_SC_ST_OBC_TotalCount(HttpSession sessionUserId,String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String sus_no,String service_status){
	 	return TSDAO.getPer_Temp_SC_ST_OBC_TotalCountDetail(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,service_status);
	 }
	 
	 @RequestMapping(value = "/getNon_Regular_SC_ST_OBC_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getNon_Regular_SC_ST_OBC_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
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
		 return TSDAO.getNon_Regular_SC_ST_OBC_ReportDataListDetail(startPage,pageLength,Search,orderColunm,orderType,cont_comd,cont_corps,
				 cont_div,cont_bde,unit_name,sus_no,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getNon_Regular_SC_ST_OBC_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getNon_Regular_SC_ST_OBC_TotalCount(HttpSession sessionUserId,String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String sus_no){
	 	return TSDAO.getNon_Regular_SC_ST_OBC_TotalCountDetail(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
	 }
	 
	 @RequestMapping(value = "/Download_Total_Servant_SC_ST_OBC_Wise",method = RequestMethod.POST)
		public ModelAndView Download_Total_Servant_SC_ST_OBC_Wise(
				@ModelAttribute("cont_comd_dn") String cont_comd,@ModelAttribute("cont_corps_dn") String cont_corps,@ModelAttribute("cont_div_dn") String cont_div,
				@ModelAttribute("cont_bde_dn") String cont_bde,
				@ModelAttribute("cont_comd_tx") String cont_comd_tx,@ModelAttribute("cont_corps_tx") String cont_corps_tx,@ModelAttribute("cont_div_tx") String cont_div_tx,
				@ModelAttribute("cont_bde_tx") String cont_bde_tx,
				@ModelAttribute("service_status_tx") String service_status_tx,
				@ModelAttribute("unit_name_dn") String unit_name,@ModelAttribute("sus_no_dn") String sus_no,
				@ModelAttribute("service_status_dn") String service_status,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
			ArrayList<ArrayList<String>> pdfprint = TSDAO.PDF_getPer_Temp_SC_ST_OBC_ReportDataList(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,service_status);
			ArrayList<ArrayList<String>> pdfprint_pers = TSDAO.PDF_getNon_Regular_SC_ST_OBC_ReportDataList(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("Arm/Service");
				TH.add("Total Govt Servant");
				TH.add("Group A");
				TH.add("Group B");
				TH.add("Group C");
				TH.add("SC");
				TH.add("ST");
				TH.add("OBC");
				
				List<String> TH1 = new ArrayList<String>();
				TH1.add("Ser No");
				TH1.add("Arm/Service");
				TH1.add("Total Govt Servant");
				TH1.add("SC");
				TH1.add("ST");
				TH1.add("OBC");
			
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
			if(service_status_tx.equals("--Select--")) {
				service_status_tx = "";
			}

			return new ModelAndView(new PDF_Total_Servant_SC_ST_OBC_Wise(Heading,TH,TH1,foot,username,pdfprint,pdfprint_pers,cont_comd_tx,cont_corps_tx,
					cont_div_tx,cont_bde_tx,unit_name,sus_no,service_status_tx),"userList",pdfprint);
		}
	 
	 @RequestMapping(value = "/Excel_Total_Servant_SC_ST_OBC_Wise", method = RequestMethod.POST)
		public ModelAndView Excel_Total_Servant_SC_ST_OBC_Wise(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("cont_comd_ex") String cont_comd,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
				@ModelAttribute("cont_bde_ex") String cont_bde,@ModelAttribute("service_status_ex") String service_status,
				@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
				@ModelAttribute("cont_bde_txt") String cont_bde_txt,@ModelAttribute("service_status_txt") String service_status_txt,
				@ModelAttribute("unit_name_ex") String unit_name,@ModelAttribute("sus_no_ex") String sus_no) {

		 
		 ArrayList<ArrayList<String>> Excel = TSDAO.PDF_getPer_Temp_SC_ST_OBC_ReportDataList(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,service_status);
		 ArrayList<ArrayList<String>> Excel1 = TSDAO.PDF_getNon_Regular_SC_ST_OBC_ReportDataList(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			
		 List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("Arm/Service");
			TH.add("Total Govt Servant");
			TH.add("Group A SC");
			TH.add("Group A ST");
			TH.add("Group A OBC");
			TH.add("Group B SC");
			TH.add("Group B ST");
			TH.add("Group B OBC");
			TH.add("Group C SC");
			TH.add("Group C ST");
			TH.add("Group C OBC");
			
			List<String> TH1 = new ArrayList<String>();
			TH1.add("Ser No");
			TH1.add("Arm/Service");
			TH1.add("Total Govt Servant");
			TH1.add("SC");
			TH1.add("ST");
			TH1.add("OBC");
			
			String Heading = "\nLMC STATE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_2_Table_Report("L", TH,TH1, Heading, username,Excel,Excel1), "userList", Excel );
		}
}
