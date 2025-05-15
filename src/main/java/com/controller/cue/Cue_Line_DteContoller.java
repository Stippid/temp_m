package com.controller.cue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.HelpDeskController.helpController;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.Personnel_EntitlementDAO;
import com.dao.cue.cueStandardEntitlementTransportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Cue_Line_DteContoller {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private Cue_wepe_conditionDAO masterDAO;
	@Autowired
	RoleBaseMenuDAO roledao;
	@Autowired
	private cueStandardEntitlementTransportDAO cueTransport;
	
	@Autowired
	private Personnel_EntitlementDAO pdao;
	
	

	cueContoller M = new cueContoller();
	
	ValidationController validation = new ValidationController();
	
	
	helpController help = new helpController();
  
	
	@RequestMapping(value = "/WePecondition_data", method = RequestMethod.GET)
	public ModelAndView WePecondition_data(String subModule3, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("WePecondition_data", roleid);
		/*if (val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
            if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("wepe", "3"); // for WEAPON value is 3
		Mmap.put("roleid", roleid);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("WePecondition_dataTile");
	}
	
	//30-12-2024 For Personnel Line Dte
	@RequestMapping(value = "/WePecondition_data_pers", method = RequestMethod.GET)
	public ModelAndView WePecondition_Data_Pers(String subModule3, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("WePecondition_data_pers", roleid);
		/*if (val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
            if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("wepe", "1"); // for Personnel value is 1
		Mmap.put("roleid", roleid);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("WePecondition_dataTile");
	}
	
	//30-12-2024 For Transport Line Dte
		@RequestMapping(value = "/WePecondition_data_trans", method = RequestMethod.GET)
		public ModelAndView WePecondition_Data_Trans(String subModule3, ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("WePecondition_data_trans", roleid);
			/*if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			  String roleSusNo = session.getAttribute("roleSusNo").toString();
			   String roleAccess = session.getAttribute("roleAccess").toString();
	            if(roleAccess.equals("Line_dte")){	
				
				Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectLine_dte",select);
			    Mmap.put("selectArmNameList",select);
				Mmap.put("getArmNameList", M.getArmNameList());
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
			}
			
			Mmap.put("msg", msg);
			Mmap.put("wepe", "2"); // for Transport value is 2
			Mmap.put("roleid", roleid);
//			Mmap.put("getArmNameList", M.getArmNameList());
//			Mmap.put("getsponserListCue", M.getsponserListCue());
			return new ModelAndView("WePecondition_dataTile");
		}
	
	
	@RequestMapping(value = "/admin/WePecondition_data1", method = RequestMethod.POST)
	public ModelAndView WePecondition_data1(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "arm_desc1", required = false) String arm,
			@RequestParam(value = "setTypeOnclick1", required = false) String setTypeOnclick) {
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("wepe", setTypeOnclick);
		Mmap.put("we_pe01", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);

		List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition(we_pe, we_pe_no, "",
				sponsor_dire, arm, "", setTypeOnclick, "", roleType,roleAccess);
		
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
           if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("WePecondition_dataTile");
	}
	
	@RequestMapping(value = "/add_moreUrl_line_dte", method = RequestMethod.POST)
	public ModelAndView add_moreUrl_line_dte(@ModelAttribute("add_moreType") String add_moreType, HttpServletRequest request, HttpSession session, 
			@ModelAttribute("add_moreid") int add_moreid, ModelMap Mmap,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "arm", required = false) String arm,
			@RequestParam(value = "spdir", required = false) String spdir,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		Mmap.put("WePeconditionAddMoreCMD", masterDAO.getcue_wepeByid(add_moreid));
		Mmap.put("add_moreType", add_moreType);
		Mmap.put("we_pe_no01", we_pe_no);
		Mmap.put("spdir", spdir);
		Mmap.put("arm", arm);
		
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
// For Transport Tables
		List<Map<String, Object>> list = cueTransport.getViewTransEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		List<Map<String, Object>> list2 = cueTransport.getViewStdlnkTransModEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		List<Map<String, Object>> list3 = cueTransport.getViewStdlnkTransfotnoteEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		List<Map<String, Object>> list13 = cueTransport.getViewStdlnkTransInlieuEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		
//		for Weapon Tables
		List<Map<String, Object>> list4 = cueTransport.getViewWeaponEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		List<Map<String, Object>> list6 = cueTransport.getViewStdlnkWeaponModEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		List<Map<String, Object>> list7 = cueTransport.getViewStdlnkWeaponfotnoteEntitlementDtladdMore(we_pe_no, "1",roleType,roleAccess);
		
//		for Personnel Tbales
		List<Map<String, Object>> list1 =pdao.getSummaryPersonnelEntitlementReport1(we_pe_no,"1");
		List<Map<String, Object>> list9 = cueTransport.getViewPersonEntitlementDtladdMore(we_pe_no,"1",roleType,roleAccess);
		List<Map<String, Object>> list11 = cueTransport.getViewStdlnkPersonModEntitlementDtladdMore(we_pe_no,"1",roleType,roleAccess);
		List<Map<String, Object>> list12 = cueTransport.getViewStdlnkPersonfotnoteEntitlementDtladdMore(we_pe_no,"1",roleType,roleAccess);
	

		Mmap.put("list", list);
		Mmap.put("list2", list2);
		Mmap.put("list3", list3);
		Mmap.put("list4", list4);
		Mmap.put("list6", list6);
		Mmap.put("list7", list7);
		Mmap.put("list1", list1);
		Mmap.put("list9", list9);
		Mmap.put("list11", list11);
		Mmap.put("list12", list12);
		Mmap.put("list13", list13);
		Mmap.put("list.size()", list.size());
		Mmap.put("list2.size()", list2.size());
		Mmap.put("list3.size()", list3.size());
		Mmap.put("list4.size()", list4.size());
		Mmap.put("list6.size()", list6.size());
		Mmap.put("list7.size()", list7.size());
		Mmap.put("list9.size()", list9.size());
		Mmap.put("list11.size()", list11.size());
		Mmap.put("list12.size()", list12.size());
		Mmap.put("list13.size()", list13.size());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("add_moreUrl_line_dteTile");
	}
	
	@RequestMapping(value = "/rejectWePeByLineDteaction", method = RequestMethod.POST)
	@ResponseBody  // Add this annotation to return JSON response
	public Map<String, String> rejectWePeByLineDteaction(
	    @RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
	    @RequestParam(value = "description1", required = false) String description,
	    @RequestParam(value = "submodule", required = false) String submodule,
	    @RequestParam(value = "summary1", required = false) String issue_summary,
	    @RequestParam(value = "typecue", required = false) String typecue,
	    @RequestParam(value = "ct_part", required = false) String ct_part,
	    HttpServletRequest request, ModelMap model, HttpSession session) {
	    
	    String username = session.getAttribute("username").toString();
	    String userId = session.getAttribute("userId").toString();
	    
	    HD_TB_BISAG_TICKET_GENERATE N = new HD_TB_BISAG_TICKET_GENERATE();
	    int ticket = help.getMaxticket();
	    N.setTicket(ticket);
	    
	    String roleAccess = session.getAttribute("roleAccess").toString();
	    String roleSusNo = session.getAttribute("roleSusNo").toString();
	    if (roleAccess.equals("Unit")) {
	        N.setSus_no(roleSusNo);
	    }
	    if(typecue.equals("1")) {
	    	  N.setSd_vetting(ct_part);
	    }
//	    String summary = "";
//	    boolean hasModification = modification != null 
//	            && !modification.trim().isEmpty() 
//	            && !"undefined".equalsIgnoreCase(modification.trim());
//	            
//	    boolean hasCondition = condition != null 
//	            && !condition.trim().isEmpty() 
//	            && !"undefined".equalsIgnoreCase(condition.trim());

//	    if (hasModification && !hasCondition) {
//	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "   + item_type + " is " + auth_no + " and Mod is " + modification;
//	    } else if (hasCondition && !hasModification) {
//	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "  + item_type + " is " + auth_no + " and Condition is " + condition;
//	    } else {
//	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "   + item_type + " is " + auth_no;
//	    }
	    N.setCreated_by(username);
	    N.setCreated_on(new Date());
	    N.setTicket_status("0");
	    N.setUserid(Integer.parseInt(userId));
	    N.setDescription(description);
	    N.setHelp_topic("15");
	    N.setIssue_summary(issue_summary);
	    N.setModule(3);
	    N.setSub_module(Integer.parseInt(submodule));
	    N.setUsername(username);
	    N.setSus_no(roleSusNo);
	    if(typecue.equals("1")) {
	    	N.setScreen_name(1127);
		   } else if (typecue.equals("2")) {
			   N.setScreen_name(1128);
		   } else if (typecue.equals("3")) {
			   N.setScreen_name(1129);
		   }

	    Session session1 = HibernateUtil.getSessionFactory().openSession();
	    session1.beginTransaction();
	    int N_id = (int)session1.save(N);
	    session1.getTransaction().commit();
	    session1.close();

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
	   // response.put("ticket", String.valueOf(ticket));
	    return response;
	}
	
	/*
	 * For description with File
	 * @RequestMapping(value = "/rejectWePeByLineDteaction", method = RequestMethod.POST)
	@ResponseBody  // Add this annotation to return JSON response
	public Map<String, String> rejectWePeByLineDteaction(
	    @RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
	    @RequestParam(value = "description1", required = false) String description,
	    @RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
	    @RequestParam(value = "item_type1", required = false) String item_type,
	    @RequestParam(value = "id", required = false) String id,
	    @RequestParam(value = "auth_no", required = false) String auth_no,
	    @RequestParam(value = "modification1", required = false) String modification,
	    @RequestParam(value = "amt_inc_dec1", required = false) String amt_inc_dec,
	    @RequestParam(value = "condition1", required = false) String condition,
	   // @RequestParam("doc_path1") MultipartFile file,
	    HttpServletRequest request, ModelMap model, HttpSession session) {
	    
	    String username = session.getAttribute("username").toString();
	    String roleid = session.getAttribute("roleid").toString();
	    String userId = session.getAttribute("userId").toString();
	    
	    HD_TB_BISAG_TICKET_GENERATE N = new HD_TB_BISAG_TICKET_GENERATE();
	    int ticket = help.getMaxticket();
	    N.setTicket(ticket);
	    
	    String roleAccess = session.getAttribute("roleAccess").toString();
	    String roleSusNo = session.getAttribute("roleSusNo").toString();
	    if (!roleSusNo.equals("") && roleSusNo != null && roleSusNo != "") {
	        N.setSus_no(roleSusNo);
	    }
	    String summary = "";
	    boolean hasModification = modification != null 
	            && !modification.trim().isEmpty() 
	            && !"undefined".equalsIgnoreCase(modification.trim());
	            
	    boolean hasCondition = condition != null 
	            && !condition.trim().isEmpty() 
	            && !"undefined".equalsIgnoreCase(condition.trim());

	    if (hasModification && !hasCondition) {
	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "   + item_type + " is " + auth_no + " and Mod is " + modification;
	    } else if (hasCondition && !hasModification) {
	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "  + item_type + " is " + auth_no + " and Condition is " + condition;
	    } else {
	        summary = "WE/PE No " + we_pe_no + " , UE of " + item_seq_no + " , "   + item_type + " is " + auth_no;
	    }
	    Map<String, String> response = new HashMap<>();
	    if (!file.isEmpty()) {
			String doc_path1Ext = FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();
			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (file.getSize() > fileSizeLimit) {
				response.put("message", "File size should be less then 20 MB");
			}
			if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")) {
				response.put("message", "Only *.pdf or *.jpeg  file extension allowed");
			}
			String fname = "";
			try {
				String extension = "";
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				byte[] bytes = file.getBytes();
				String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
				File dir = new File(helpdeskFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String filename = file.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() +"_"+ userId + "_HELPDESK." + extension;
				if (validation.checkImageAnmlLength(fname) == false){
					model.put("msg", validation.image_MSG);
//					return new ModelAndView("redirect:help");
				}
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				N.setscreen_shot(fname);
			} catch (Exception e) {
			}
		}
	    
	    N.setCreated_by(username);
	    N.setCreated_on(new Date());
	    N.setTicket_status("0");
	    N.setUserid(Integer.parseInt(userId));
	    N.setDescription(description);
	    N.setHelp_topic("15");
	    N.setIssue_summary(summary);
	    N.setModule(3);
	    N.setSub_module(14);
	    N.setUsername(username);

	    Session session1 = HibernateUtil.getSessionFactory().openSession();
	    session1.beginTransaction();
	    int N_id = (int)session1.save(N);
	    session1.getTransaction().commit();
	    session1.close();

	    
	    response.put("message", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
//	    response.put("ticket", String.valueOf(ticket));
	    return response;
	}*/
	
	//24-12-2024
	
	@RequestMapping(value = "/rejectWePeNoByLineDteAction", method = RequestMethod.POST)
	@ResponseBody  // Add this annotation to return JSON response
	public Map<String, String> rejectWePeNoByLineDteAction(
	    @RequestParam(value = "description1", required = false) String description,
	    @RequestParam(value = "issue_summary", required = false) String issue_summary,
	    @RequestParam("doc_path") MultipartFile file,
	    @ModelAttribute("wepe") String wepe,
	    HttpServletRequest request, ModelMap model, HttpSession session) {
	    
	    String username = session.getAttribute("username").toString();
	    String userId = session.getAttribute("userId").toString();
	    
	    HD_TB_BISAG_TICKET_GENERATE N = new HD_TB_BISAG_TICKET_GENERATE();
	    int ticket = help.getMaxticket();
	    N.setTicket(ticket);
	    
	    String roleSusNo = session.getAttribute("roleSusNo").toString();
	    if (!roleSusNo.equals("")) {
	        N.setSus_no(roleSusNo);
	    }
	   String  sub_module = "";
	   if(wepe.equals("1")) {
		   sub_module= "13";
	   } else if (wepe.equals("2")) {
		   sub_module="15";
	   } else if (wepe.equals("3")) {
		   sub_module="14";
	   }
	    Map<String, String> response = new HashMap<>();
	    if (!file.isEmpty()) {
			String doc_path1Ext = FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();
			
			if (file.getSize() >  20 * 1024 * 1024) {
				response.put("message", "File size should be less then 20 MB");
			}
			
			if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")) {
				response.put("message", "Only *.pdf or *.jpeg  file extension allowed");
			}
			String fname = "";
			try {
				String extension = "";
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				byte[] bytes = file.getBytes();
				String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
				File dir = new File(helpdeskFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String filename = file.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() +"_"+ userId + "_HELPDESK." + extension;
				if (validation.checkImageAnmlLength(fname) == false){
					model.put("msg", validation.image_MSG);
//					return new ModelAndView("redirect:help");
				}
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				N.setscreen_shot(fname);
			} catch (Exception e) {
			}
		}
	    
	    N.setCreated_by(username);
	    N.setCreated_on(new Date());
	    N.setTicket_status("0");
	    N.setUserid(Integer.parseInt(userId));
	    N.setDescription(description);
	    N.setHelp_topic("15");
	    N.setIssue_summary(issue_summary);
	    N.setModule(3);
	    N.setSub_module(Integer.parseInt(sub_module));
	    N.setUsername(username);
	    if(wepe.equals("1")) {
	    	N.setScreen_name(1127);
		   } else if (wepe.equals("2")) {
			   N.setScreen_name(1128);
		   } else if (wepe.equals("3")) {
			   N.setScreen_name(1129);
		   }

	    Session session1 = HibernateUtil.getSessionFactory().openSession();
	    session1.beginTransaction();
	    int N_id = (int)session1.save(N);
	    session1.getTransaction().commit();
	    session1.close();

	    
	    response.put("message", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
//	    response.put("ticket", String.valueOf(ticket));
	    return response;
	}

	
	public static String Get_button_info2(ResultSetMetaData metaData, ResultSet rs) throws SQLException
	{
		String a="";
		int columnCount = metaData.getColumnCount();
			
			 
			 for (int i = 1; i <= columnCount; i++) {

		                if (metaData.getColumnLabel(i).equalsIgnoreCase("vetted_by")) { 
		                    if (rs.getObject(i) != null) {
		                        a += "\nVetted By :" + rs.getObject(i);
		                    } else {
		                        a += "\nVetted By :";
		                    }
		                }
		                if (metaData.getColumnLabel(i).equalsIgnoreCase("vetted_on")) { 
		                    if (rs.getObject(i) != null) {
		                        a += "\nVetted On :" + rs.getObject(i);
		                    } else {
		                        a += "\nVetted On :";
		                    }
		                }
		           
		        }


		String LogButton = "<i class='action_icons action_approved' " + " title=' " + a + " '></i>";
                      return LogButton;
	}
	
	@RequestMapping(value = "/submitDAtaLineDteaction", method = RequestMethod.POST)
	public ModelAndView submitDAtaLineDteaction(
			ModelMap Mmap,HttpSession session,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
		    @RequestParam(value = "type1", required = false) String type,
		    @RequestParam(value = "we_pe12", required = false) String  we_pe,
		    @RequestParam(value = "arm1", required = false) String arm,
		    @RequestParam(value = "spdir1", required = false) String  spdir){		
	    
	    String username = session.getAttribute("username").toString();
	    String userId = session.getAttribute("userId").toString();
	    String roleType = session.getAttribute("roleType").toString();
	    String roleAccess = session.getAttribute("roleAccess").toString();
	    Mmap.put("wepe", type);
		Mmap.put("we_pe01", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("sponsor_dire1", spdir);
		Mmap.put("arm1", arm);
	    
	    Mmap.put("msg", masterDAO.updatePersDetails(we_pe_no,type,username,we_pe));	
	    	
	    	 List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition(we_pe, we_pe_no, "",
	    			 spdir, arm, "", type, "", roleType,roleAccess);
	    	 
	    	 String roleSusNo = session.getAttribute("roleSusNo").toString();
	           if(roleAccess.equals("Line_dte")){	
				
	        	   Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
	        	   Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectLine_dte",select);
				Mmap.put("selectArmNameList",select);
				Mmap.put("getArmNameList", M.getArmNameList());
				Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
			}
	           Mmap.put("list", list);
	           Mmap.put("list.size()", list.size());
	           Mmap.put("roleType", roleType);
			return new ModelAndView("WePecondition_dataTile");
			
	}
	
}

