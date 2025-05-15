package com.controller.psg.Civilian_Report;

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

import com.controller.ExportFile.ExcelnonUserListReportView;
import com.controller.ExportFile.PDF_Civilian_Arm_Service_NonRegular_Wise;
import com.controller.ExportFile.PDF_Civilian_Arm_Service_Regular_Wise;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Report.Arm_Service_Wise_NonRegularEst_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Arm_Service_Wise_NonRegularEst {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Arm_Service_Wise_NonRegularEst_Dao ArmNon;
	
	@RequestMapping(value = "/admin/Arm_Service_Wise_NonRegular_Url", method = RequestMethod.GET)
	public ModelAndView Arm_Service_Wise_NonRegular_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Arm_Service_Wise_NonRegular_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}		
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		ArrayList<ArrayList<String>> list = ArmNon.Search_Arm_Service_Wise_NonRegularEst();
		Mmap.put("list", list);
		
		int grand_total = 0;
		for(int h=0;h<list.size();h++) {
			grand_total += Integer.parseInt(list.get(h).get(8));
		}
		Mmap.put("grand_total", grand_total);
		
		Mmap.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		return new ModelAndView("Arm_Service_Wise_NonRegular_Tiles");
	}
	
	
	@RequestMapping(value = "/Download_Arm_Service_NonRegular",method = RequestMethod.POST)
	public ModelAndView Download_Arm_Service_NonRegular(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
	
		ArrayList<ArrayList<String>> pdfprint = ArmNon.Search_Arm_Service_Wise_NonRegularEst();
	
		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
		List<String> TH = new ArrayList<String>();
		
		TH.add("SER NO");
		TH.add("ARM/SERVICE");
		TH.add("NON INDUSTRIAL");
		TH.add("INDUSTRIAL");
		TH.add("NON INDUSTRIAL");
		TH.add("INDUSTRIAL");
		TH.add("NON INDUSTRIAL");
		TH.add("INDUSTRIAL");
		TH.add("TOTAL");
		
		TH.add("NON REGULAR EST");
		TH.add("PAID FROM REGULAR PAY HEADS");
		TH.add("PAID FROM CONTINGENCIES ");
		TH.add("WORK CHARGED PERSONNEL AND OTHER");
		
		return new ModelAndView(new PDF_Civilian_Arm_Service_NonRegular_Wise(Heading,TH,foot,username),"userList",pdfprint);
	}
	
	 @RequestMapping(value = "/Excel_Arm_Service_nonRegular", method = RequestMethod.POST)
		public ModelAndView Excel_Arm_Service_nonRegular(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		 
		 ArrayList<ArrayList<String>> Excel = ArmNon.Search_Arm_Service_Wise_NonRegularEst();
					
		 List<String> TH = new ArrayList<String>();
			TH.add("SER NO");
			TH.add("ARM/SERVICE");
			TH.add("PAID FROM REGULAR PAY HEADS_NON INDUSTRIAL");
			TH.add("PAID FROM REGULAR PAY HEADS_INDUSTRIAL");
			TH.add("PAID FROM CONTINGENCIES_NON INDUSTRIAL");
			TH.add("PAID FROM CONTINGENCIES_INDUSTRIAL");
			TH.add("WORK CHARGED PERSONNEL AND OTHER_NON INDUSTRIAL");
			TH.add("WORK CHARGED PERSONNEL AND OTHER_INDUSTRIAL");
			TH.add("TOTAL");
			
			String Heading = "\nACTUAL STRENGTH OF CIVILIANS IN THE ARMY - ARM/SERVICE  WISE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelnonUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
	
	
}
