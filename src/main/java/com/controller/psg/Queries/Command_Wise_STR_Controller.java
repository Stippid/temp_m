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

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.ExportFile.PDF_Command_Wise_STR;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.Command_Wise_STR_DAO;
import com.models.Tbl_CodesForm;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Command_Wise_STR_Controller {

	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	Command_Wise_STR_DAO command_str;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@RequestMapping(value = "/admin/Command_Wise_STR_Url", method = RequestMethod.GET)
	public ModelAndView Command_Wise_STR_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		Mmap.put("getCommandList",m.getCommandDetailsList());
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Command_Wise_STR_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("msg", msg);
	
		return new ModelAndView("Command_Wise_STR_Tiles");
	}
	
	@RequestMapping(value = "/getCommand_Wise_STR_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getCommand_Wise_STR_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		 	
		 
		 return command_str.getCommand_Wise_STR_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
	}
	 
	 @RequestMapping(value = "/Command_Wise_STR_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long Command_Wise_STR_TotalCount(HttpSession sessionUserId,String Search){
	 	return command_str.Command_Wise_STR_TotalCountDtl(Search);
	 }
	 
	 @RequestMapping(value = "/Download_Command_Wise_STR_query",method = RequestMethod.POST)
		public ModelAndView Download_Command_Wise_STR_query(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, 
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {	
		
		 
		    String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Command_Wise_STR_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 	ArrayList<ArrayList<String>> pdfprint = command_str.pdf_Command_Wise_STR_ReportDataList();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("Command");
				TH.add("Auth");
				TH.add("CT - 1");
				TH.add("CT - 2");
				TH.add("Total");				
				TH.add("Female");
				TH.add("Male");
				
			return new ModelAndView(new PDF_Command_Wise_STR(Heading,TH,foot,username,pdfprint),"userList",pdfprint);
		}
	 
	 @RequestMapping(value = "/Excel_Command_Wise_STR_query", method = RequestMethod.POST)
		public ModelAndView Excel_Command_Wise_STR_query(HttpServletRequest request,ModelMap model,HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,String typeReport1) {
		 
		  String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Command_Wise_STR_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> Excel = command_str.pdf_Command_Wise_STR_ReportDataList();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("Command");
			TH.add("Auth");
			TH.add("CT - 1 Female");
			TH.add("CT - 1 Male");
			TH.add("CT - 2 Female");				
			TH.add("CT - 2 Male");
			TH.add("Total");
			
			String Heading = "\nCOMMAND WISE STR AS ON";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
}
