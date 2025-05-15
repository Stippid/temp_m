package com.controller.psg.JCO_Report;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Excel_Cause_of_Wastage_Report;
import com.controller.JCO_ExportFile.JCO_PDF_Cause_of_Wastage;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Report.JCO_Cause_of_wastage_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class JCO_Cause_of_Wastage_Controller {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	JCO_Cause_of_wastage_DAO wastage;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Cause_of_Wastage_Url_jco", method = RequestMethod.GET)
	public ModelAndView Cause_of_Wastage_Url_jco(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cause_of_Wastage_Url_jco", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		 Mmap.put("msg", msg);
		 ArrayList<ArrayList<String>> list =wastage.getCause_of_wastage_jco(); 
		  Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			Mmap.put("date", date1);
			Mmap.put("list", list);
		return new ModelAndView("Cause_of_Wastage_Tiles_jco");
	}
	

	 @RequestMapping(value = "/Download_Cause_of_wastage_query_jco",method = RequestMethod.POST)
		public ModelAndView Download_Cause_of_wastage_query_jco(
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, 
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {	
		
		 	ArrayList<ArrayList<String>> pdfprint = wastage.getCause_of_wastage_jco();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

			return new ModelAndView(new JCO_PDF_Cause_of_Wastage(Heading,foot,username),"userList",pdfprint);
		}

	 @RequestMapping(value = "/Excel_Cause_of_wastage_query_jco", method = RequestMethod.POST)
		public ModelAndView Excel_Cause_of_wastage_query_jco(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		 
		 ArrayList<ArrayList<String>> CauseExcel = wastage.getCause_of_wastage_jco();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Cause of Wastage");
			TH.add("No of Offers");
					
			List<String> CauseList = new ArrayList<String>();
			CauseList.add("Died");
			CauseList.add("Medical Invalidment");
			CauseList.add("Premature Retirement");
			CauseList.add("Relinquished Commission/Release SSC");
			CauseList.add("Retired");
			CauseList.add("Total");
			
						
			String Heading = "\nWASTAGE OF OFFICERS BY CAUSE FOR THE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_Cause_of_Wastage_Report("L", TH,CauseList, Heading, username,CauseExcel), "userList", CauseList );
		}
}
