package com.controller.psg.Report;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.ParseInt;

import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.ReportSearch_3008_mnsDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Report.Report_Serving_mnsDAO;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN_DETAILS_MNS;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN_MNS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Report_Serving_mnsController {
	@Autowired
	private Report_3008DAO report_3008DAO;
	
	@Autowired
	private ReportSearch_3008_mnsDAO search_3008DAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	helpController hp = new helpController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController valid = new ValidationController();
	@Autowired private Report_Serving_mnsDAO RP;
	
	@RequestMapping(value = "/admin/Report_SevingUrl_mns", method = RequestMethod.GET)
	public ModelAndView Report_SevingUrl_mns(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Report_SevingUrl_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		/*LocalDate currentDate = LocalDate.now();
		int daysInMonth = currentDate.lengthOfMonth();
		int dayOfMonth = currentDate.getDayOfMonth();
		//int month = (dayOfMonth == daysInMonth) ? currentDate.getMonthValue() : currentDate.getMonthValue() - 1;
		int month = currentDate.getMonthValue() - 1;
		int year = currentDate.getYear();*/
		
		LocalDate currentDate = LocalDate.now();
		LocalDate previousMonth = currentDate.minusMonths(1);
		int month = previousMonth.getMonthValue();
		int year = previousMonth.getYear();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}	
		Mmap.put("totalAuth","0");
		Mmap.put("totalHeld","0");
		Mmap.put("defi","0");
		Mmap.put("sur","0");
		Mmap.put("roleType", roleType);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		return new ModelAndView("Report_Seving_mns_Tiles");
	}
	
	//// datatable ///
	////////////Serving //////////////////
	@RequestMapping(value = "/getServing3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getServing3008_mns(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String month,String year,String unit_sus_no) throws SQLException {
		
		String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		
		return search_3008DAO.getServing3008(startPage, pageLength, Search, orderColunm, orderType,	 sessionUserId, FDate, LDate,sus_no);
	}
	
	
	@RequestMapping(value = "/getServing3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getServing3008Count_mns(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String month,String year,String unit_sus_no) throws SQLException {
		
		String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		return search_3008DAO.getServing3008CountList(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	
	////////// SuperNum ////////////////
	@RequestMapping(value = "/getSupernum3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSupernum3008_mns(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String month,String year,String unit_sus_no) throws SQLException {
		
		String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		
		return search_3008DAO.getSupernum3008(startPage, pageLength, Search, orderColunm, orderType,	 sessionUserId, FDate, LDate,sus_no);
	}
	
	
	@RequestMapping(value = "/getSupernum3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getSupernum3008Count_mns(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String month,String year,String unit_sus_no) throws SQLException {
		
		String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		return search_3008DAO.getSupernum3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	
	////////// ReEmployee ////////////////
	@RequestMapping(value = "/getReEmployee3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getReEmployee3008_mns(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}

		return search_3008DAO.getReEmployee3008(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				FDate, LDate, sus_no);
	}

	@RequestMapping(value = "/getReEmployee3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getReEmployee3008Count_mns(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		return search_3008DAO.getReEmployee3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}

	////////// Deserter ////////////////
	@RequestMapping(value = "/getDeserter3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDeserter3008_mns(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}

		return search_3008DAO.getDeserter3008(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				FDate, LDate, sus_no);
	}

	@RequestMapping(value = "/getDeserter3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getDeserter3008Count_mns(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		return search_3008DAO.getDeserter3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	
	////////// AuthHeld ////////////////
	@RequestMapping(value = "/getAuthHeld3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAuthHeld3008_mns(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		
		
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} 
		
		
		else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}

		return search_3008DAO.getAuthHeld3008(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				FDate, LDate, sus_no);
	}

	@RequestMapping(value = "/getAuthHeld3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getAuthHeld3008Count_mns(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		return search_3008DAO.getAuthHeld3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	// bisag v2 290822 (split auth and held)
//////////Auth ////////////////
	@RequestMapping(value = "/getAuth3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAuth3008_mns(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";

		
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}

		else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3008DAO.getAuth3008(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				FDate, LDate, sus_no);
	}

	@RequestMapping(value = "/getAuth3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getAuth3008Count_mns(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}
		return search_3008DAO.getAuth3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	
	
//////////Held ////////////////
	@RequestMapping(value = "/getHeld3008_mns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getHeld3008_mns(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String month, String year,
			String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";

	
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}

		else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}

		return search_3008DAO.getHeld3008(startPage, pageLength, Search, orderColunm, orderType, sessionUserId, FDate,
				LDate, sus_no);
	}

	@RequestMapping(value = "/getHeld3008Count_mns", method = RequestMethod.POST)
	public @ResponseBody long getHeld3008Count_mns(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String month, String year, String unit_sus_no) throws SQLException {

		String Month1 = "00";

		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		} else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";

		String LDate = year + Month1;

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		if (unit_sus_no == null || unit_sus_no.equals("") || unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		} else {
			sus_no = unit_sus_no;
		}
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
		if (list_date.size() > 0) {
			LDate = String.valueOf(list_date.get(0).get(0));
		} else {
			LDate = FDate;
		}
		return search_3008DAO.getHeld3008Count(Search, orderColunm, orderType, sessionUserId, FDate, LDate, sus_no);
	}
	
	// bisag v2 290822 (split auth and held) Ends
	////------- datatable ///


@RequestMapping(value = "/admin/GetSearch_Report_Serving_mns", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_Serving_mns(ModelMap Mmap, HttpSession session,HttpSession sessionUserId,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			@RequestParam(value = "present_return_no1", required = false) String present_return_no,
			@RequestParam(value = "present_return_dt1", required = false) String present_return_dt
			) throws ServletException, IOException, ParseException {
	
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Report_SevingUrl_mns", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 if (year!= "") {
			 if (valid.isOnlyNumer(year) == true) {
				Mmap.put("msg", " Year " + valid.isOnlyNumerMSG);
				return new ModelAndView("redirect:Report_SevingUrl_mns");
			}
			if (!valid.YearLength(year)) {
				Mmap.put("msg", valid.YearMSG );
				return new ModelAndView("redirect:Report_SevingUrl_mns");
			}
		}
		 
		 if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Report_SevingUrl_mns");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Report_SevingUrl_mns");
				}
	      }
		 
		 if(present_return_dt!="") {
		   if (!valid.isValidDate(present_return_dt)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Present Return No");
				return new ModelAndView("redirect:Report_SevingUrl_mns");
			}
		 }
		 
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		//----CHANDANI
		String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;
		
		 
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}	
		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
	
		Mmap.put("list", list_date);
		Mmap.put("size", list_date.size());	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
			
		
	    if (roleType.equals("DEO")) {
				ArrayList<ArrayList<String>> list_sup = RP.Search_Report_Serving(sus_no,FDate,LDate,session);		
				Mmap.put("list", list_sup);
				Mmap.put("size", list_sup.size());	
				
				ArrayList<ArrayList<String>> list_sup1 = RP.Search_Report_3008_Super(sus_no,FDate,LDate,session);		
				Mmap.put("list2", list_sup1);
				Mmap.put("size2", list_sup1.size());	
				
				ArrayList<ArrayList<String>> list_re_employ = RP.Search_Report_3008_ReEmployed(sus_no,FDate,LDate,session);	
				Mmap.put("list4", list_re_employ);
				Mmap.put("size3", list_re_employ.size());	
				
				ArrayList<ArrayList<String>> list6 = RP.Search_Report_3008_Deserter(sus_no,FDate,LDate,session);	
				Mmap.put("list6", list6);	
				Mmap.put("size6", list6.size());	

			 ArrayList<ArrayList<String>> list_auth_held =	RP.Search_Report_3008_Auth_Held(sus_no,FDate,LDate);
			 Mmap.put("list5", list_auth_held);	
			 Mmap.put("size4", list_auth_held.size());
			 
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
					
					int sur = sum_held - sum_auth;
					int defi=0;
					if(sur >= 0){
						defi = sur;
						sur = 0;
					}else{
						sur = sur;
						defi = 0;			
					}		
					Mmap.put("totalAuth",sum_auth);
					Mmap.put("totalHeld",sum_held);
					Mmap.put("defi",Math.abs(sur));
					Mmap.put("sur",defi);	
	    }	

		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}		
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("month1", month);
		Mmap.put("year1", year);
		Mmap.put("unit_sus_no2", unit_sus_no);
		Mmap.put("present_return_no1", present_return_no);
		Mmap.put("present_return_dt1", present_return_dt);
	    //-----Chandani
	    List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> MainD =  Get_3008_DetailsData1_mns(month,year,sus_no);
	    
	    if(MainD.size() > 0 && !MainD.get(0).getVersion().equals("1") ) {
  			Mmap.put("last_return_no1",MainD.get(0).getPresent_return_no());
  			Mmap.put("last_return_dt1",MainD.get(0).getPresent_return_dt());
		}
	    if(MainD.size() > 0 && String.valueOf(MainD.get(0).getStatus()).equals("1") && MainD.get(0).getVersion().equals("1") ) {
  			Mmap.put("last_return_no1",MainD.get(0).getPresent_return_no());
  			Mmap.put("last_return_dt1",MainD.get(0).getPresent_return_dt());
		}
	    if( MainD.size() > 0  && String.valueOf(MainD.get(0).getStatus()).equals("0") && MainD.get(0).getVersion().equals("1")) {
			Mmap.put("last_return_no1", "");
			Mmap.put("last_return_dt1", "");
		}
		return new ModelAndView("Report_Seving_mns_Tiles");
	}
	 //-----Chandani
		public  List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> Get_3008_DetailsData1_mns(String month, String year ,String roleSusNo) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_PSG_IAFF_3008_MAIN_DETAILS_MNS where month=:month and year=:year and sus_no=:roleSusNo and status=1 order by id desc ";
			Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year).setString("roleSusNo", roleSusNo);
			@SuppressWarnings("unchecked")
			List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> list = (List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
	@RequestMapping(value = "/admin/Insert_3008_mns", method = RequestMethod.POST)
	public @ResponseBody String Insert_3008_mns(ModelMap Mmap, HttpSession session, HttpServletRequest request) throws SQLException {
		String msg = "";
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String userId = session.getAttribute("userId").toString();
		
		// changes utk
		/// bisag v2 260822 (date parse added)
				String p_dt = request.getParameter("present_return_dt");

				String[] act = p_dt.split("/");

				String p_dt_act = act[2] + "/" + act[1] + "/" + act[0];

				Date date = new Date(p_dt_act);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String present_rt_date = formatter.format(date);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

        String Month1 = "00";
		
		if (Integer.parseInt(request.getParameter("month")) > 9) {
			Month1 = request.getParameter("month");
		}else {
			Month1 = "0" + request.getParameter("month");
		}
		
		
		 if (request.getParameter("year") != "") {
			 if (valid.isOnlyNumer(request.getParameter("year")) == true) {
				msg =  " Year " + valid.isOnlyNumerMSG ;
				return msg;
			}
			if (!valid.YearLength(request.getParameter("year"))) {
				msg =  valid.YearMSG ;
				return msg;
			}
		}
		 
		String FDate = request.getParameter("year") + "/" + Month1 + "/" + "01";
		String LDate = request.getParameter("year") + Month1 ;


		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
	
		Mmap.put("list", list_date);
		Mmap.put("size", list_date.size());	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		String sus_no = "";
		if (request.getParameter("unit_sus_no")==null || request.getParameter("unit_sus_no").equals("") ||
				request.getParameter("unit_sus_no").equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = request.getParameter("unit_sus_no");
		}	
		
		List<TB_PSG_IAFF_3008_MAIN_MNS> version =  RP.Get_3008_VersionData(request.getParameter("month"),request.getParameter("year") ,roleSusNo);

		if (request.getParameter("present_return_no").equals("") || request.getParameter("present_return_no").equals(null) ||
				request.getParameter("present_return_no").equals("null")) {
			
			msg =  "Please Enter Present Return No.";
			return msg;
		} 
		if (!request.getParameter("present_return_no").equals("") || !request.getParameter("present_return_no").equals(null) ||
				!request.getParameter("present_return_no").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("present_return_no"), valid.id_card_noMax, valid.id_card_noMin)) {
				msg =  "Present Return No " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (request.getParameter("present_return_dt").equals("") || request.getParameter("present_return_dt").equals(null) ||
				request.getParameter("present_return_dt").equals("null") || request.getParameter("present_return_dt").equals("DD/MM/YYYY")) {
			msg =  "Please Enter Present Return Dt.";
			return msg;
		} 
		
		  if (!request.getParameter("present_return_dt").equals("") || !request.getParameter("present_return_dt").equals(null) ||
				!request.getParameter("present_return_dt").equals("null") || !request.getParameter("present_return_dt").equals("DD/MM/YYYY")) {
			   if (!valid.isValidDate(request.getParameter("present_return_dt"))) {
					msg =  valid.isValidDateMSG + " of Present Return No.";
					return msg;
				}
		  }
	/*	if (request.getParameter("we_pe_no").equals("") || request.getParameter("we_pe_no").equals(null) ||
				request.getParameter("we_pe_no").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("we_pe_no"), valid.authorityMax, valid.authorityMin)) {
				msg =  "Authority " + valid.isValidLengthMSG;
				return msg;
			}
		}*/
		if (!request.getParameter("remarks").equals("") || !request.getParameter("remarks").equals(null) ||
				!request.getParameter("remarks").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarks"), valid.remarkMax, valid.remarkMin)) {
				msg =  "Observation " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksfornursingoffrstos").equals("") || !request.getParameter("remarksfornursingoffrstos").equals(null) ||
				!request.getParameter("remarksfornursingoffrstos").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksfornursingoffrstos"), valid.remarkMax, valid.remarkMin)) {
				msg =  " Nursing Offr TOS since submission of last report" + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksfornursingoffrssos").equals("") || !request.getParameter("remarksfornursingoffrssos").equals(null) ||
				!request.getParameter("remarksfornursingoffrssos").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksfornursingoffrssos"), valid.remarkMax, valid.remarkMin)) {
				msg =  " Nursing Offr SOS since submission of last report  " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksfornursingoffrspostout").equals("") || !request.getParameter("remarksfornursingoffrspostout").equals(null) ||
				!request.getParameter("remarksfornursingoffrspostout").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksfornursingoffrspostout"), valid.remarkMax, valid.remarkMin)) {
				msg =  " Nursing Offrs under order of posting out" + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksfornursingoffrspostin").equals("") || !request.getParameter("remarksfornursingoffrspostin").equals(null) ||
				!request.getParameter("remarksfornursingoffrspostin").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksfornursingoffrspostin"), valid.remarkMax, valid.remarkMin)) {
				msg =  " Nursing Offrs under order of posting in " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksfornursingoffrsunderretire").equals("") || !request.getParameter("remarksfornursingoffrsunderretire").equals(null) ||
				!request.getParameter("remarksfornursingoffrsunderretire").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksfornursingoffrsunderretire"), valid.remarkMax, valid.remarkMin)) {
				msg =  " Nursing Offrs under order of Retirement / Release/SSC Release " + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (!request.getParameter("remarksdistr").equals("") || !request.getParameter("remarksdistr").equals(null) ||
				!request.getParameter("remarksdistr").equals("null")) {
			if (!valid.isvalidLength(request.getParameter("remarksdistr"), valid.remarkMax, valid.remarkMin)) {
				msg =  "Distr" + valid.isValidLengthMSG;
				return msg;
			}
		}
		if (version.size() != 0) {
			
		    int Status = version.get(0).getStatus();
		   
			if (Status == 0 ) {
				msg = "Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.";
				return msg;
			}
		}
	try {

				Boolean Insert = RP.Insert_Report_mns_3008(username,sus_no,FDate ,request.getParameter("month"), request.getParameter("year") ,userId,
						request.getParameter("present_return_no"),
//						request.getParameter("present_return_dt"),
						present_rt_date,
						request.getParameter("remarks"),LDate,request.getParameter("remarksfornursingoffrstos"),
						request.getParameter("remarksfornursingoffrssos"),request.getParameter("remarksfornursingoffrspostout"),
						request.getParameter("remarksfornursingoffrspostin"),request.getParameter("remarksfornursingoffrsunderretire"),
						request.getParameter("remarksdistr"));
				
				tx.commit();
				if (Insert == true) {
					msg = "Data Save/Updated Successfully";
					return msg;
				}else {
					msg = "Data Not Updated Or Saved";
					return msg;
				}
				
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "Data Not Updated Or Saved";
			} catch (RuntimeException rbe) {
				msg = "Data Not Updated Or Saved";
			}
		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}
	


	
	//////////-----------------------Search_Report_Seving
	@RequestMapping(value = "/admin/Search_Report_Url_mns", method = RequestMethod.GET)
	public ModelAndView Search_Report_Url_mns(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url_mns", roleid);
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
		Mmap.put("roleType", roleType);		
		return new ModelAndView("Search_Report_Serving_mns_Tiles");
	}
	
	@RequestMapping(value = "/admin/GetSearch_Report_3008_mns", method = RequestMethod.POST)
	public ModelAndView GetSearch_Report_3008_mns(ModelMap Mmap, HttpSession session,HttpSession sessionUserId,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no) throws ServletException, IOException, ParseException {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url_mns", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
			 if (year != "") {
				 if (valid.isOnlyNumer(year) == true) {
					Mmap.put("msg", " Year " + valid.isOnlyNumerMSG);
					return new ModelAndView("redirect:Search_Report_Url_mns");
				}
				if (!valid.YearLength(year)) {
					Mmap.put("msg", valid.YearMSG );
					return new ModelAndView("redirect:Search_Report_Url_mns");
				}
			}
			 if(unit_sus_no!="") {
		    	  if (!valid.SusNoLength(unit_sus_no)) {
						Mmap.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_Report_Url_mns");
					}
		    	  
		    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
						return new ModelAndView("redirect:Search_Report_Url_mns");
					}
		      }
		 
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> list  = RP.Search_Report_Version( month,  year , session ,status ,unit_sus_no);
		Mmap.put("list", list);
		Mmap.put("size", list.size());	
		
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}		
		//--- Chandani
				Mmap.put("roleSusNo", roleSusNo);
				Mmap.put("month1", month);
				Mmap.put("year1", year);
				Mmap.put("status1", status);
				Mmap.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("Search_Report_Serving_mns_Tiles");
	}

	
	////////---------------APPROVE--------
	
	
	////////---------------APPROVE--------
	public  List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> Get_3008_DetailsData_mns(String month, String year ,String roleSusNo,String version,Integer status) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_IAFF_3008_MAIN_DETAILS_MNS where month=:month and year=:year and sus_no=:roleSusNo and version=:version and status =:status order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year).setString("roleSusNo", roleSusNo).setString("version", version).setInteger("status", status);
		@SuppressWarnings("unchecked")
		List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> list = (List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/Approve_Report_Serving_Url_mns",method = RequestMethod.POST)
	public ModelAndView Approve_Report_Serving_Url_mns(@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year5", required = false) String year,
			@RequestParam(value = "month5", required = false) String month,
			@RequestParam(value = "version5", required = false) String version,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
			@RequestParam(value = "status5", required = false) String status,
			@RequestParam(value = "pagename", required = false) String pagename,
			Authentication authentication,HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		
		
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("IAFF_3008_Query_mns", roleid);
		
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
          return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
	
	String Month1 = "00";
		
		if (Integer.parseInt(month) > 9) {
			Month1 = month;
		}else {
			Month1 = "0" + month;
		}
		String FDate = year + "/" + Month1 + "/" + "01";
	
		String LDate = year + Month1 ;


		ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);	
	
		Mmap.put("list", list_date);
		Mmap.put("size", list_date.size());	
		if(list_date.size() > 0)
		{
			LDate = String.valueOf(list_date.get(0).get(0));
		}
		else
		{
			LDate = FDate;
		}
		String sus_no = "";
		if (unit_sus_no==null || unit_sus_no.equals("") ||
				unit_sus_no.equals("null")) {
			sus_no = roleSusNo;
		}else
		{
			sus_no = unit_sus_no;
		}	
		/*bisag 120423 v2 ( roleType.equals("ALL") changed by poonem mam)*/
          if (roleType.equals("APP") || roleType.equals("ALL") || roleType.equals("DEO") || roleType.equals("VIEW")) {
	    	
		    	ArrayList<ArrayList<String>> list_sup = RP.Search_Report_Serving_Approval(month,year,session,version,sus_no);	
				Mmap.put("list", list_sup);
				Mmap.put("size", list_sup.size());	
				
				ArrayList<ArrayList<String>> list_sup1 = RP.Search_Report_3008_Super_Approval(month,year,session,version,sus_no);		
				Mmap.put("list2", list_sup1);
				Mmap.put("size2", list_sup1.size());					
			
				ArrayList<ArrayList<String>> list6 = RP.Search_Report_3008_Deserter_Approval(month,year,session,version,sus_no);	
				Mmap.put("list6", list6);	
				Mmap.put("size6", list6.size());					

				ArrayList<ArrayList<String>> list_held =	RP.Search_Report_3008_Held(sus_no,FDate,LDate);
				 

				 Mmap.put("list7", list_held);	
				 Mmap.put("size7", list_held.size());		
				
	      }
          
          //-----Chandani
    	    List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> MainDD =  Get_3008_DetailsData1_mns(month,year,sus_no);
    	    
    	    if(MainDD.size() > 0 && !MainDD.get(0).getVersion().equals("1")) {
  	  			Mmap.put("last_return_no1",MainDD.get(0).getPresent_return_no());
  	  			Mmap.put("last_return_dt1",MainDD.get(0).getPresent_return_dt());
	  		}
    	    if(MainDD.size() > 0 && String.valueOf(MainDD.get(0).getStatus()).equals("1") && MainDD.get(0).getVersion().equals("1") ) {
      			Mmap.put("last_return_no1",MainDD.get(0).getPresent_return_no());
      			Mmap.put("last_return_dt1",MainDD.get(0).getPresent_return_dt());
    		}
    	    if(MainDD.size() > 0  && String.valueOf(MainDD.get(0).getStatus()).equals("0") && MainDD.get(0).getVersion().equals("1")) {
    			Mmap.put("last_return_no1", "");
    			Mmap.put("last_return_dt1", "");
    		}
    		
    		
    		ArrayList<ArrayList<String>> wepe = report_3008DAO.getEstablishNo(sus_no);
    		String we_pe_no = "";
    		if(wepe.size() > 0) {
    			we_pe_no = wepe.get(0).get(0);    		
    		}
    		
    	   Mmap.put("we_pe_no1",we_pe_no);
    	
    		
          List<TB_PSG_IAFF_3008_MAIN_DETAILS_MNS> cc =  Get_3008_DetailsData_mns(month,year,sus_no,version,Integer.parseInt(status));
          
		Mmap.put("msg", msg);
		Mmap.put("month5", month);
		Mmap.put("year5", year);
		Mmap.put("version5", version);
		Mmap.put("unit_sus_no5", unit_sus_no);
		
		
		if(cc.size() > 0  ) {
			Mmap.put("present_return_no5", cc.get(0).getPresent_return_no());
			Mmap.put("present_return_dt5", cc.get(0).getPresent_return_dt());
			Mmap.put("remarks", cc.get(0).getRemarks());
			Mmap.put("distr", cc.get(0).getRemarksdistr());
			Mmap.put("remarksfornursingoffrstos", cc.get(0).getRemarksfornursingoffrstos());
			Mmap.put("remarksfornursingoffrssos", cc.get(0).getRemarksfornursingoffrssos());
			Mmap.put("remarksfornursingoffrspostout", cc.get(0).getRemarksfornursingoffrspostout());
			Mmap.put("remarksfornursingoffrspostin", cc.get(0).getRemarksfornursingoffrspostin());
			Mmap.put("remarksfornursingoffrsunderretire", cc.get(0).getRemarksfornursingoffrsunderretire());
			
			
		}

		
		Mmap.put("roleSusNo", roleSusNo);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}		
		Mmap.put("roleType", roleType);	
		Mmap.put("status", status);
		Mmap.put("pagename", pagename);
		return new ModelAndView("Approve_Report_Serving_mns_Tiles");
	}
	

	
	@RequestMapping(value = "/Approve_3008_mns" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_3008_mns(HttpServletRequest request,
			HttpSession session, ModelMap model,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "month1", required = false) String month,
			@RequestParam(value = "year1", required = false) String year,
			@RequestParam(value = "version1", required = false) String version,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
			Authentication authentication) throws SQLException {
		
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Report_Url_mns", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
          return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		//CronSchedulingService dn = new CronSchedulingService();
		//dn.saveIAFF3008_MainDetails_olap();
	 String roleSusNo = session.getAttribute("roleSusNo").toString();
		     List<String> liststr = new ArrayList<String>();
		     String sus_no = "";
				if (unit_sus_no==null || unit_sus_no.equals("") ||
						unit_sus_no.equals("null")) {
					sus_no = roleSusNo;
				}else
				{
					sus_no = unit_sus_no;
				}	
		     String username = session.getAttribute("username").toString();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			//Chandani----
			 Boolean Approve = RP.Approve_Report_3008(username,sus_no,month,year,version);
			
			
			tx.commit();
			sessionHQL.close();
			

			if (Approve == true) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
			model.put("msg",liststr.get(0));

			
		return new ModelAndView("redirect:Search_Report_Url_mns");
	}
	 @RequestMapping(value = "/delete_Report_3008_mns" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Report_3008_mns(HttpServletRequest request,
				HttpSession session, ModelMap model,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "month6", required = false) String month,
				@RequestParam(value = "year6", required = false) String year,
				@RequestParam(value = "version6", required = false) String version,
				@RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no,HttpSession sessionA,
				Authentication authentication) throws SQLException {
		 
		 String roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Report_Url_mns", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String username = session.getAttribute("username").toString();
		 String userId = session.getAttribute("userId").toString();
		 
			     List<String> liststr = new ArrayList<String>();
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String sus_no = "";
					if (unit_sus_no==null || unit_sus_no.equals("") ||
							unit_sus_no.equals("null")) {
						sus_no = roleSusNo;
					}else
					{
						sus_no = unit_sus_no;
					}	
				 Boolean Delete = RP.Delete_Report_3008(username,sus_no,month,year,version);
				 
				 tx.commit();
				sessionHQL.close();
				
				if (Delete == true) {
					liststr.add("Deleted Successfully.");
				}else {
					liststr.add("Deleted Not Successfully.");
				}
				model.put("msg",liststr.get(0));

				
			return new ModelAndView("redirect:Search_Report_Url_mns");
		}
	 
	 
	
	 
	 
}
