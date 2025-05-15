package com.controller.psg.JCO_Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

import com.controller.ExportFile.Excel_Age_Service_Wise_Report;
import com.controller.JCO_ExportFile.PDF_Age_Service_Wise_Jco;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Report.Age_Service_Wise_Jco_DAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Age_Service_Wise_Controller_Jco {
	//CommanController m = new CommanController();
	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	private Age_Service_Wise_Jco_DAO age_serviceDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Age_Serive_Wise_Jco_Url", method = RequestMethod.GET)
	public ModelAndView Age_Serive_Wise_Jco_Url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Age_Serive_Wise_Jco_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		    if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}	
		ArrayList<ArrayList<String>> list = age_serviceDAO.Search_Age();
		Mmap.put("list", list);
		ArrayList<ArrayList<String>> list1 = age_serviceDAO.Search_Service();
		Mmap.put("list1", list1);
		Mmap.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Mmap.put("date", date1);
		return new ModelAndView("Age_Service_Wise_Tiles_Jco");
	}
	 
	 @RequestMapping(value = "/Download_Age_Service_query_Jco",method = RequestMethod.POST)
		public ModelAndView Download_Age_Service_query_Jco(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
		
			ArrayList<ArrayList<String>> pdfprint = age_serviceDAO.Search_Age();
			ArrayList<ArrayList<String>> sevicelist = age_serviceDAO.Search_Service();
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
			//if(pdfprint.size() > 0) {
	/*			List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("Age Group");
				TH.add("Nos. of Offers");
				
				
				List<String> TH1 = new ArrayList<String>();
				TH1.add("Ser No");
				TH1.add("Service Group");
				TH1.add("Nos. of Offers");*/
			return new ModelAndView(new PDF_Age_Service_Wise_Jco(Heading,foot,username,sevicelist),"userList",pdfprint);
		}

	 @RequestMapping(value = "/Excel_Age_Service_wise_query_Jco", method = RequestMethod.POST)
		public ModelAndView Excel_Age_Service_wise_query_Jco(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		 
		 ArrayList<ArrayList<String>> ageprint = age_serviceDAO.Search_Age();
		 ArrayList<ArrayList<String>> sevicePrint = age_serviceDAO.Search_Service();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Age Group");
			TH.add("No of Offers");
			
			List<String> TH1 = new ArrayList<String>();
			TH1.add("Service Group");
			TH1.add("No of Offers");
			
			List<String> ageList = new ArrayList<String>();
			ageList.add("YRS 20 ONWARDS LESS THAN 25YRS");
			ageList.add("YRS 25 ONWARDS LESS THAN 30RS");
			ageList.add("YRS 30 ONWARDS LESS THAN 35RS");
			ageList.add("YRS 35 ONWARDS LESS THAN 40RS");
			ageList.add("YRS 40 ONWARDS LESS THAN 45RS");
			ageList.add("YRS 45 ONWARDS LESS THAN 50RS");
			ageList.add("YRS 50 ONWARDS LESS THAN 55RS");
			ageList.add("YRS 55 ONWARDS LESS THAN 60RS");
			ageList.add("YEARS 60 ONWARDS");
			ageList.add("TOTAL");		
			
			List<String> serviceList = new ArrayList<String>();
			serviceList.add("LESS THAN 1YRS");
			serviceList.add("YRS 1 ONWARDS LESS THAN 3YRS");
			serviceList.add("YRS 3 ONWARDS LESS THAN 5YRS");
			serviceList.add("YRS 5 ONWARDS LESS THAN 7YRS");
			serviceList.add("YRS 7 ONWARDS LESS THAN 9YRS");
			serviceList.add("YRS 9 ONWARDS LESS THAN 11YRSS");
			serviceList.add("YRS 11 ONWARDS LESS THAN 13YRS");
			serviceList.add("YRS 13 ONWARDS LESS THAN 15YRS");
			serviceList.add("YRS 15 ONWARDS LESS THAN 20YRS");
			serviceList.add("YRS 20 ONWARDS LESS THAN 25YRS");
			serviceList.add("YRS 25 ONWARDS LESS THAN 30YRS");
			serviceList.add("YRS 30 ONWARDS");
			serviceList.add("TOTAL");
					
			String Heading = "\nHELD STR OF OFFRS AGE WISE";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_Age_Service_Wise_Report("L", TH,TH1,ageList,serviceList, Heading, username,ageprint,sevicePrint), "userList", ageList );
		}

}
