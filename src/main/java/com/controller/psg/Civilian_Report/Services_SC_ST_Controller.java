package com.controller.psg.Civilian_Report;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.controller.ExportFile.ExcelUserListReportViewsc_st;
import com.controller.ExportFile.PDF_Civilian_Services_SC_ST_Wise;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Report.Services_SC_ST_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Services_SC_ST_Controller {

	@Autowired
	private Services_SC_ST_DAO SC_ST_DAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Civilian_Services_SC_ST_Url", method = RequestMethod.GET)
	public ModelAndView Civilian_Services_SC_ST_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Civilian_Services_SC_ST_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}		
    	
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> list = SC_ST_DAO.Search_Services_SC_ST();
		int grand_total1 = 0;
		int grand_total2 = 0;
		int grand_total3 = 0;
		int grand_total4 = 0;
		int grand_total5 = 0;
		int grand_total6 = 0;

		for(int h=0;h<list.size();h++) {
			grand_total1 += Integer.parseInt(list.get(h).get(9)) + Integer.parseInt(list.get(h).get(21));
			grand_total2 += Integer.parseInt(list.get(h).get(10)) + Integer.parseInt(list.get(h).get(22));
			grand_total3 += Integer.parseInt(list.get(h).get(11)) + Integer.parseInt(list.get(h).get(23));
			grand_total4 += Integer.parseInt(list.get(h).get(36)) + Integer.parseInt(list.get(h).get(48));
			grand_total5 += Integer.parseInt(list.get(h).get(37)) + Integer.parseInt(list.get(h).get(49));
			grand_total6 += Integer.parseInt(list.get(h).get(38)) + Integer.parseInt(list.get(h).get(50));
		}
		
		Mmap.put("grand_total1", grand_total1);
		Mmap.put("grand_total2", grand_total2);
		Mmap.put("grand_total3", grand_total3);
		Mmap.put("grand_total4", grand_total4);
		Mmap.put("grand_total5", grand_total5);
		Mmap.put("grand_total6", grand_total6);
		
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		
		 int currentYear = Year.now().getValue();
	     int previousYear = currentYear-1;
	     Mmap.put("previousYear", previousYear);  
		
	     
	     int year = Calendar.getInstance().get(Calendar.YEAR);
	     
	     Mmap.put("year", year);
		
		
//		int year = Calendar.getInstance().get(Calendar.YEAR);  
		return new ModelAndView("Civilian_Services_SC_ST_Tiles");
	}

	 @RequestMapping(value = "/Download_Services_SC_ST",method = RequestMethod.POST)
		public ModelAndView Download_Services_SC_ST(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
		
			ArrayList<ArrayList<String>> pdfprint = SC_ST_DAO.PDF_Services_SC_ST();
			ArrayList<ArrayList<String>> pdfall = new ArrayList<ArrayList<String>>();			
			
			int split=0;
			while(split<pdfprint.get(0).size())
				 {
				ArrayList<String> pdfrow = new ArrayList<String>();
					for(int index=split;index<split+8;index++) {
						if(pdfprint.get(0).size()==index)
							break;
						pdfrow.add(pdfprint.get(0).get(index));						
					}	
					split=split+8;
					pdfall.add(pdfrow);					
				}
				
			
			int currentYear = Year.now().getValue();
		     int previousYear = currentYear-1;



		     int year = Calendar.getInstance().get(Calendar.YEAR);
			
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			List<String> TH = new ArrayList<String>();
			TH.add("STATUS");//0
			TH.add("GROUP");//1
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+previousYear);//2
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+year);//3
			TH.add("TOTAL GOVT SERVANTS");//4
			TH.add("SC");//5
			TH.add("ST");//6
			TH.add("NON EFECTIVE");//7
			TH.add("PERMANENT");//8
			TH.add("TEMPORARY");//9
			TH.add("GROUP 'A'");//10
			TH.add("GROUP 'B'");//11
			TH.add("GROUP 'C'");//12
			TH.add("TOTAL");//13
			
			return new ModelAndView(new PDF_Civilian_Services_SC_ST_Wise(Heading,TH,foot,username),"userList",pdfall);
		}
	 
	 @RequestMapping(value = "/Excel_represc_stService_Regular", method = RequestMethod.POST)
	 
		public ModelAndView Excel_represc_stService_Regular(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		 
		 ArrayList<ArrayList<String>> Excel_P = SC_ST_DAO.Search_Services_SC_ST();
					
		 
		 int currentYear = Year.now().getValue();
	     int previousYear = currentYear-1;



	     int year = Calendar.getInstance().get(Calendar.YEAR);
		 
			ArrayList<ArrayList<String>> pdfall = new ArrayList<ArrayList<String>>();	
			int split=0;
			while(split<Excel_P.get(0).size())
				 {
				ArrayList<String> pdfrow = new ArrayList<String>();
					for(int index=split;index<split+3;index++) {
						if(Excel_P.get(0).size()==index)
							break;
						pdfrow.add(Excel_P.get(0).get(index));						
					}	
					
					
					split=split+3;
					pdfall.add(pdfrow);	
					
				}
			
	
		
		 List<String> TH = new ArrayList<String>();
		 //TH.add("Status");//0
		  // TH.add("Status");//0
			TH.add("Group");//1
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+previousYear+"_Total Govt Servants");//4
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+previousYear+"_SC");//5
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+previousYear+"_ST");//6
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+previousYear+"_NON EFECTIVE ");//7
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+year+"_Total Govt Servants");//4
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+year+"_SC");//5
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+year+"_ST");//6
			TH.add("NO. OF EMPLOYEES AS ON 01 JAN "+year+"_NON EFECTIVE");//7
		/*
		 * List<String> CauseList = new ArrayList<String>(); CauseList.add("PERMANENT");
		 * CauseList.add("TEMPORARY"); CauseList.add("Grand Total");
		 */
			
			List<String> CauseList1 = new ArrayList<String>();
			CauseList1.add("PERMANENT Group A");
			CauseList1.add("PERMANENT Group B");
			CauseList1.add("PERMANENT Group C");
			CauseList1.add("PERMANENT Total");
			CauseList1.add("TEMPORARY Group A");
			CauseList1.add("TEMPORARY Group B");
			CauseList1.add("TEMPORARY Group C");
			CauseList1.add("TEMPORARY Total");
			CauseList1.add("GRAND TOTAL");
			
			
			
			String Heading = "\nSPECIAL REPRSENTATION IN SERVICES OF SC/ST(CIVILIANS)";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportViewsc_st("L", TH,CauseList1, Heading, username,Excel_P), "userList", Excel_P);
		}
	 
	 
}
