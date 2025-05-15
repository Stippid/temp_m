package com.controller.psg.Queries;

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
import com.controller.ExportFile.Excel_To_Export_inf_off_Table_Report;
import com.controller.ExportFile.PDF_Regular_ArmyPersonnel_query;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.Regular_ArmyPersonnel_DAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Regular_ArmyPersonnel_Controller {
	
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	Regular_ArmyPersonnel_DAO reg;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Regular_ArmyPersonnel_Url", method = RequestMethod.GET)
	public ModelAndView Regular_ArmyPersonnel_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Regular_ArmyPersonnel_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("Regular_ArmyPersonnel_Tiles");
	}
	
	// bisag 121222 v2 (perentarm changed report1 one)
	/*@RequestMapping(value = "/getRegular_ArmyGorkha_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getRegular_ArmyGorkha_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,			
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
				 
		 return reg.getRegular_ArmyGorkha_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,
				 sessionUserId);
	}
	 
	 @RequestMapping(value = "/getRegular_ArmyGorkha_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getRegular_ArmyGorkha_TotalCount(HttpSession sessionUserId,String Search){
	 	return reg.getRegular_ArmyGorkha_TotalCountDtl(Search);
	 }
	 */
	 
	
	 @RequestMapping(value = "/getRegular_ArmyInfantary_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getRegular_ArmyInfantary_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		 
		
		 return reg.getRegular_ArmyInfantary_ReportDataListDetails_pers(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getRegular_ArmyInfantary_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getRegular_ArmyInfantary_TotalCount(HttpSession sessionUserId,String Search){
	 	return reg.getRegular_ArmyInfantary_TotalCountDtl_pers(Search);
	 }
	 
	 @RequestMapping(value = "/Download_Regular_ArmyPersonnel_query",method = RequestMethod.POST)
		public ModelAndView Download_Regular_ArmyPersonnel_query(				
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
		   String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Regular_ArmyPersonnel_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 
			ArrayList<ArrayList<String>> pdfprint = reg.pdf_getRegular_ArmyGorkha_ReportDataList();
			ArrayList<ArrayList<String>> pdfprint_pers = reg.pdf_getRegular_ArmyInfantary_ReportDataList_pers();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
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
								
			return new ModelAndView(new PDF_Regular_ArmyPersonnel_query(Heading,TH,TH1,foot,username,pdfprint,pdfprint_pers),"userList",pdfprint);
		}

	 @RequestMapping(value = "/Excel_Regular_ArmyPersonnel_query", method = RequestMethod.POST)
		public ModelAndView Excel_Regular_ArmyPersonnel_query(HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				ModelMap model,HttpSession session,String typeReport1) {
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Regular_ArmyPersonnel_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 //ArrayList<ArrayList<String>> Excel = reg.pdf_getRegular_ArmyGorkha_ReportDataList();
		 ArrayList<ArrayList<String>> Excel1 = reg.pdf_getRegular_ArmyInfantary_ReportDataList_pers();
		/*	
		 List<String> TH = new ArrayList<String>();
			TH.add("SER NO");
			TH.add("ARM_DESCRIPTION");
			TH.add("Auth");
			TH.add("TOTAL");*/
					
			List<String> TH = new ArrayList<String>();
			TH.add("SER NO");
			TH.add("ARM_DESCRIPTION");
			TH.add("Auth");
			TH.add("TOTAL");
			
			String Heading = "\nSTR OF REGULAR ARMY PERSONNEL";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_inf_off_Table_Report("L" ,TH,/*TH1,*/ Heading, username,/*Excel,*/Excel1), "userList", Excel1 );
		}
}
