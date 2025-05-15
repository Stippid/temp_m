package com.controller.psg.Report;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
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

import com.controller.ExportFile.Report_3008_Pdf;
import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Report.Report_Serving_DAO;

import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Report_3008_Controller {

	@Autowired
	private Report_3008DAO RPDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	helpController hp = new helpController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired private Report_Serving_DAO RP;
	@RequestMapping(value = "/admin/Search_Report_3008Url", method = RequestMethod.GET)
	public ModelAndView Search_Report_3008Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, 			
			HttpServletRequest request,HttpSession sessionUserId) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_3008Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();

		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}		
		Mmap.put("totalAuth","0");
		Mmap.put("totalHeld","0");
		Mmap.put("defi","0");
		Mmap.put("sur","0");
		Mmap.put("roleType", roleType);		
		return new ModelAndView("Report_3008Tiles");
	}

	String[] Serving_personnel_no;
	
/*	@RequestMapping(value = "/admin/GetSearch_Report_3008_Serving", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_3008_Serving(ModelMap Mmap, HttpSession session,HttpSession sessionUserId,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year) throws ServletException, IOException {

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
        String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
		
		
		ArrayList<ArrayList<String>> list_sup = RPDAO.Search_Report_3008_Super(roleSusNo,FDate);		
		Mmap.put("list2", list_sup);
		Mmap.put("size2", list_sup.size());	
		
		for(int n = 0; n <list_sup.size();n++)
		{
			Mmap.put("super_id", list_sup.get(n).get(17));
			if(Integer.parseInt(list_sup.get(n).get(19)) == 0)
			{
				Mmap.put(""+n,n);	
			}
		}
		
		String[] arr_super = new String[list_sup.size()]  ;
		for(int k = 0; k <list_sup.size();k++)
		{
			arr_super[k] =list_sup.get(k).get(3);
		}		
		List<String> list_sp = new ArrayList(Arrays.asList(arr_super));
		Object[] sp =  list_sp.toArray();
		
		//ArrayList<ArrayList<String>> list_re_employ = RPDAO.Search_Report_3008_ReEmployed(roleSusNo,month,year,sp);	
		//ArrayList<ArrayList<String>> list_re_employ = RPDAO.Search_Report_3008_ReEmployed(roleSusNo,FDate);	
		Mmap.put("list4", list_re_employ);
		Mmap.put("size3", list_re_employ.size());	
	
		String[] arr_re_employ = new String[list_re_employ.size()]  ;		
		for(int j = 0; j <list_re_employ.size();j++)
		{
			arr_re_employ[j] =list_re_employ.get(j).get(3);
		}		
		 List<String> list_s_r = new ArrayList(Arrays.asList(arr_super));
		 list_s_r.addAll(Arrays.asList(arr_re_employ));
		 Object[] cp =  list_s_r.toArray();
		 	 
		 for(int r = 0; r <list_re_employ.size();r++)
			{
			  Mmap.put("re_employ_id", list_re_employ.get(r).get(17));	
			  if(Integer.parseInt(list_sup.get(r).get(20)) == 0)
				{
					Mmap.put(""+r,r);	
				}
			}
		 ArrayList<ArrayList<String>> list_auth_held =	RP.Search_Report_3008_Auth_Held(roleSusNo,month,year);
		 //ArrayList<ArrayList<String>> list_auth_held =	RPDAO.Search_Report_3008_Auth_Held(roleSusNo,month,year,cp);
		 Mmap.put("list5", list_auth_held);	
		 Mmap.put("size4", list_auth_held.size());
		 
		 String[] arr_auth_held = new String[list_auth_held.size()]  ;
		 for(int l = 0; l <list_auth_held.size();l++)
		 {
			arr_auth_held[l] =list_auth_held.get(l).get(5);			
		 }		
		 List<String> list_ah = new ArrayList(Arrays.asList(arr_auth_held));
		// list_ah.addAll(Arrays.asList(arr_super));
		 ////list_ah.addAll(Arrays.asList(arr_re_employ));
		 Object[] ah =  list_ah.toArray();
		
		 for(int s = 0; s <list_auth_held.size(); s++)
			{
			  Mmap.put("auth_held_id", list_auth_held.get(s).get(7));	
			  if(Integer.parseInt(list_sup.get(s).get(8)) == 0)
				{
					Mmap.put(""+s,s);	
				}
			}
		 ArrayList<ArrayList<String>> list = RPDAO.Search_Report_3008_Serving(roleSusNo,FDate);	
		//ArrayList<ArrayList<String>> list = RPDAO.Search_Report_3008_Serving(roleSusNo,month,year,ah);		
		Mmap.put("list", list);	
		Mmap.put("size", list.size());	
		
		
		 ArrayList<ArrayList<String>> list6 = RPDAO.Search_Report_3008_Deserter(roleSusNo,FDate);	
			Mmap.put("list6", list6);	
			Mmap.put("size6", list6.size());	
			
			
		for(int u = 0; u <list.size();u++)
		{
			Mmap.put("serving_id", list.get(u).get(17));
			if(Integer.parseInt(list.get(u).get(19)) == 0)
			{
				Mmap.put(""+u,u);	
			}			
		}
					
		int totalAuth = 0;
		int totalHeld = 0;
		int sum_held=0;
		int sum_auth=0;
		
		for(int i=0;i<list_auth_held.size();i++) {
			String totalAuth1= list_auth_held.get(i).get(4);
									
			if(totalAuth1 == null || totalAuth1.equals(null)) {
				totalAuth =0;
			}
			else
			{
				totalAuth = Integer.parseInt(totalAuth1);	
			}
			String totalHeld1= list_auth_held.get(i).get(5);
			
			if(totalHeld1 == null || totalHeld1.equals(null)) {
				totalHeld =0;
			}
			else
			{
				totalHeld = Integer.parseInt(totalHeld1);
			}						
			sum_auth += totalAuth;
			sum_held += totalHeld;		
			}	
		int sur= sum_held - sum_auth;
		int defi=0;
		if(sur >= 0)
		{
			defi = sur;
			sur = 0;
		}
		else
		{
			sur = sur;
			defi = 0;			
		}		
		Mmap.put("totalAuth",sum_auth);
		Mmap.put("totalHeld",sum_held);
		Mmap.put("defi",defi);
		Mmap.put("sur",Math.abs(sur));		
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		
		if (hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}		

		return new ModelAndView("Report_3008Tiles");
	}
 */
		
		
		/*---------------------------Cmd Unit Name AutoComplete----------------------------*/ 

						
		@RequestMapping(value = "/getEstablishment_No", method = RequestMethod.POST)
		@ResponseBody
		public ArrayList<ArrayList<String>> getEstablishment_No(ModelMap Mmap, HttpSession session,HttpSession sessionUserId) {			
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			ArrayList<ArrayList<String>> list_Establishment =RPDAO.getEstablishNo(roleSusNo);
			return list_Establishment;
		}
		
		/*---------------------------Print Report 3008----------------------------*/ 
		
	/*	@RequestMapping(value = "/Print_Report_3008_PDF")
		public ModelAndView Print_Report_3008_PDF(ModelMap model, @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				HttpSession session, @ModelAttribute("month3") String month,
				@ModelAttribute("year3") String year,String typeReport) throws ParseException {
			
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = session.getAttribute("username").toString();		
			String command ="";
			if (hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
				model.put("unit_name", hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
				command =  hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0);
			}	
								
				ArrayList<ArrayList<String>> pdf_list_sup = RPDAO.Pdf_Search_Report_3008_Super(roleSusNo,month,year);	
			
					List<String> TH1 = new ArrayList<String>();
							TH1.add("Ser No");
							TH1.add("Appt");
							TH1.add("Rank");
							TH1.add("Name");
							TH1.add("Personal No");
							TH1.add("CDA A/C No");
							TH1.add("Regt");
							TH1.add("Date of Tos");				
							TH1.add("Date of Birth");
							TH1.add("Date of Comm");
							TH1.add("Date of Sen ");
							TH1.add("Date of App");											
						 
				 	String[] pdf_arr_super = new String[pdf_list_sup.size()] ;  				 	 
						for(int a = 0; a <pdf_list_sup.size();a++)
						{
							pdf_arr_super[a] =pdf_list_sup.get(a).get(4);
						}		
					List<String> pdf_list_sp = new ArrayList(Arrays.asList(pdf_arr_super));
					Object[] pdf_sp =  pdf_list_sp.toArray();
									 
				 ArrayList<ArrayList<String>> pdf_list_re_employ = RPDAO.Pdf_Search_Report_3008_ReEmployed(roleSusNo,month,year,pdf_sp);	
					
						List<String> TH2 = new ArrayList<String>();
								TH2.add("Ser No");
								TH2.add("Appt");
								TH2.add("Rank");
								TH2.add("Name");
								TH2.add("Personal No");
								TH2.add("CDA A/C No");
								TH2.add("Regt");
								TH2.add("Date of Tos");				
								TH2.add("Date of Birth");
								TH2.add("Date of Comm");
								TH2.add("Date of Sen ");
								TH2.add("Date of App");													
						
						String[] pdf_arr_re_employ = new String[pdf_list_re_employ.size()]  ;		
						for(int c = 0; c <pdf_list_re_employ.size();c++)
						{
							pdf_arr_re_employ[c] =pdf_list_re_employ.get(c).get(4);
						}		
						 List<String> pdf_list_s_r = new ArrayList(Arrays.asList(pdf_arr_super));
						 pdf_list_s_r.addAll(Arrays.asList(pdf_arr_re_employ));
						 Object[] pdf_cp =  pdf_list_s_r.toArray();
						 
				ArrayList<ArrayList<String>> pdf_list_auth_held =	RPDAO.Pdf_Search_Report_3008_Auth_Held(roleSusNo,month,year,pdf_cp);
						 
						 List<String> TH3 = new ArrayList<String>();
								TH3.add("Ser No");
								TH3.add("Rank");
								TH3.add("Base Auth");
								TH3.add("Mod Auth");
								TH3.add("Foot Auth ");
								TH3.add("Total Auth");
								TH3.add("Total Held");																		
							
						 String[] pdf_arr_auth_held = new String[pdf_list_auth_held.size()]  ;						 
						 for(int l = 0; l <pdf_list_auth_held.size();l++)
						 {
							 pdf_arr_auth_held[l] =pdf_list_auth_held.get(l).get(6);			
						 }		
						 List<String> pdf_list_ah = new ArrayList(Arrays.asList(pdf_arr_auth_held));
						 pdf_list_ah.addAll(Arrays.asList(pdf_arr_super));
						 pdf_list_ah.addAll(Arrays.asList(pdf_arr_re_employ));
						 Object[] pdf_ah =  pdf_list_ah.toArray();
						
				ArrayList<ArrayList<String>> pdf_list = RPDAO.Pdf_Search_Report_3008_Serving(roleSusNo,month,year,pdf_ah);		
						
						List<String> TH = new ArrayList<String>();
							TH.add("Ser No");
							TH.add("Appt");
							TH.add("Rank");
							TH.add("Name");
							TH.add("Personal No");
							TH.add("CDA A/C No");
							TH.add("Regt");
							TH.add("Date of Tos");				
							TH.add("Date of Birth");
							TH.add("Date of Comm");
							TH.add("Date of Sen ");
							TH.add("Date of App");	
													
					int totalAuth = 0;
					int totalHeld = 0;
					int sum_held=0;
					int sum_auth=0;
					
					for(int i=0;i<pdf_list_auth_held.size();i++) {
						String totalAuth1= pdf_list_auth_held.get(i).get(5);
												
						if(totalAuth1 == null || totalAuth1.equals(null)) {
							totalAuth =0;
						}
						else
						{
							totalAuth = Integer.parseInt(totalAuth1);	
						}
						String totalHeld1= pdf_list_auth_held.get(i).get(6);
						
						if(totalHeld1 == null || totalHeld1.equals(null)) {
							totalHeld =0;
						}
						else
						{
							totalHeld = Integer.parseInt(totalHeld1);
						}						
						sum_auth += totalAuth;
						sum_held += totalHeld;		
						}	
					int sur= sum_held - sum_auth;
					int defi=0;
					if(sur >= 0)
					{
						defi = sur;
						sur = 0;
					}
					else
					{
						sur = sur;
						defi = 0;			
					}	
					List<String> pdf_list_summary  = new ArrayList<String>();
					pdf_list_summary.add(String.valueOf(totalAuth));
					pdf_list_summary.add(String.valueOf(totalHeld));
					pdf_list_summary.add(String.valueOf(sur));
					pdf_list_summary.add(String.valueOf(defi));
										
					List<String> TH4 = new ArrayList<String>();
						TH4.add("Auth Strength");
						TH4.add("Holding Strength");
						TH4.add("Surplus");
						TH4.add("Deficiency");
										
				    DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			 		String present_return_no = request.getParameter("present_return_no");
			 		String last_return_no = request.getParameter("last_return_no");
					String present_dt = request.getParameter("present_return_dt");
					String last_dt = request.getParameter("last_return_dt");
					
					
					ArrayList<ArrayList<String>> pdf_list_view_3008 =RPDAO.getReport_Data(roleSusNo);
					
					List<String> pdf_list_view_30081  = new ArrayList<String>();
					pdf_list_view_30081.add(roleSusNo);	
					pdf_list_view_30081.add(command);			
				  pdf_list_view_3008.add(month);
					pdf_list_view_3008.add(year);
					pdf_list_view_3008.add(request.getParameter("present_return_no"));
					pdf_list_view_3008.add(request.getParameter("present_return_dt"));
					pdf_list_view_3008.add(last_return_no);
					pdf_list_view_3008.add(last_dt);
					
						Date date = new Date();
						 String MainHead="\nView Old / Genr New IAFF - 3008";
						 String Heading1 = "\nSupernumarary";
						 String Heading2 = "\nReEmployment";
						 String Heading3 = "\nAuth/Held";
						 String Heading =  "\nServing";
						 String Heading4 =  "\nSummary";
						 String foot = " Report Generated On "+ new SimpleDateFormat("dd/MM/yyyy").format(date);
						 return new ModelAndView(new Report_3008_Pdf("L",TH,Heading,username,Heading1,MainHead,Heading2,Heading3,Heading4,
						TH1,TH2,TH3,TH4,pdf_list_sup,pdf_list_re_employ,pdf_list_auth_held,pdf_list_summary,pdf_list_view_3008,foot,pdf_list_view_30081),"userList",pdf_list);
			}*/
		
		@RequestMapping(value = "/getReport_Upper_Data", method = RequestMethod.POST)
		@ResponseBody
		public ArrayList<ArrayList<String>> getReport_Upper_Data(ModelMap Mmap, HttpSession session,HttpSession sessionUserId) {			
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();			
			ArrayList<ArrayList<String>> list_Report_data =RPDAO.getReport_Data(roleSusNo);
			return list_Report_data;
		}

}
