package com.controller.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.psg.popup_history.Preview_PersonnelNo_DAO;
import com.dao.psg.update_census_data.SeniorityDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Popup_PersonnelNo_Controller {
	
	
	@Autowired
	Preview_PersonnelNo_DAO previewDao;
	
	@Autowired
	SeniorityDAO SD;
	
	@RequestMapping(value = "/Popup_SeniorityUrl_3008", method = RequestMethod.POST)
	public ModelAndView Popup_SeniorityUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_id11", required = false) BigInteger comm_id) {
		List<ArrayList<String>> list = SD.Popup_Change_of_seniority(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_Seniority_tiles");
	}

	@RequestMapping(value = "/Popup_TnaiNoUrl_3008", method = RequestMethod.POST)
	public ModelAndView Popup_TnaiNoUrl_3008(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_idTnai", required = false) BigInteger comm_id) {
		List<ArrayList<String>> list = SD.Popup_Change_of_tnaino(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_TnaiNo_tiles");
	}
	
	@RequestMapping(value = "/admin/Preview_PersonnelNo_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_PersonnelNo_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "personnel_no1", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_personnelNo(personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_PersonnelNo_Tiles");
		
	 }
	@RequestMapping(value = "/admin/Preview_UpdateRank_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_UpdateRank_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id1r", required = false) BigInteger comm_id1r,
			 @RequestParam(value = "personnel_no1r", required = false) String personnel_no1r,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_rankNo(comm_id1r));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_UpdateRank_Tiles");
		
	 }
	@RequestMapping(value = "/admin/Preview_CadetNo_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_CadetNo_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id1", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no11", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_cadetNo(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_CadetNo_Tiles");
		
	 }
	
	@RequestMapping(value = "/admin/Preview_CourseNo_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_CourseNo_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id2", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no2", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_courseNo(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_CourseNo_Tiles");
		
	 }
	@RequestMapping(value = "/admin/Preview_Gender_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Gender_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id3", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no3", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_gender(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_Gender_Tiles");
		
	 }
	@RequestMapping(value = "/admin/Preview_Commission_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Commission_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id4", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no4", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_commission(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_Commission_Tiles");
		
	 }
	@RequestMapping(value = "/admin/Preview_DateofBirth_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_DateofBirth_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id5", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no5", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_dateofbirth(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_DateofBirth_Tiles");
		
		
	 }
	@RequestMapping(value = "/admin/Preview_ArmService_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_ArmService_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id6", required = false) BigInteger comm_id1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("list", previewDao.preview_armservice(comm_id1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_ArmService_Tiles");
		
	 }
	
	
	@RequestMapping(value = "/admin/Preview_Unit_Url", method = RequestMethod.POST)
	 public ModelAndView Preview_Unit_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "comm_id7", required = false) BigInteger comm_id1,
			 @RequestParam(value = "personnel_no7", required = false) String personnel_no1,
			 HttpServletRequest request) {
		 
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("list", previewDao.preview_unit(comm_id1,personnel_no1));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Preview_Unit_Tiles");
		
	 }
	
	
}
