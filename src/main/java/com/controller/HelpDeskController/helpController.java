
package com.controller.HelpDeskController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.login.RoleController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.Create_Unit_Mov_DetailsController;
import com.controller.validation.ValidationController;
import com.dao.Notification.notificatioDAO;
import com.dao.helpDesk.HelpDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.T_Domain_Value;
import com.models.Tbl_CodesForm;
import com.models.Helpdesk.HD_TB_BISAG_FAQ;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_TRANSFER_HISTORY;
import com.models.Helpdesk.HD_TB_BISAG_USER_INFO;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class helpController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Create_Unit_Mov_DetailsController mvmntcnt = new Create_Unit_Mov_DetailsController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	DateWithTimeStampController d = new DateWithTimeStampController();
	NotificationController notification = new NotificationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private notificatioDAO  notif;
	@Autowired
	private HelpDAO help;

	RoleController role = new RoleController();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/myticket", method = RequestMethod.GET)
	public ModelAndView myticket(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(session));
		Mmap.put("label", "My Tickets");
		Mmap.put("msg", msg);
		Mmap.put("GetHelpTopic", GetHelpTopic(session));
		return new ModelAndView("myticketTiles");
	}
	@RequestMapping(value = "/admin/myticketList", method = RequestMethod.POST)
	public ModelAndView myticketList(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ticket1", required = false) String ticket,
			@RequestParam(value = "ticket_status1", required = false) String ticket_status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "help_topic1", required = false) String help_topic,
			@RequestParam(value = "type1", required = false) String type,
			@RequestParam(value = "module1", required = false) String module,
			@RequestParam(value = "label", required = false) String label1) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_date);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(from_date);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("myticketTiles");
			}
		} catch (ParseException e) {}
		
		Mmap.put("ticket1", ticket);
		Mmap.put("ticket_status1", ticket_status);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("help_topic1", help_topic);
		Mmap.put("type1", type);
		Mmap.put("module1", module);
		Mmap.put("label", label1);
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(sessionA));
		String userId = sessionA.getAttribute("userId").toString();
		List<Map<String, Object>> list = help.myTicketList(ticket,ticket_status,from_date,to_date,help_topic,userId,roleid,module,label1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("myticketTiles");
	}
	

	@RequestMapping(value = "/manageticket", method = RequestMethod.GET)
	public ModelAndView manageticket(ModelMap Mmap, HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	
		String roleid = sessionA.getAttribute("roleid").toString();
		String role = sessionA.getAttribute("role").toString();
		Boolean val = roledao.ScreenRedirect("manageticket", roleid);
		String moduleValidate =	getManageTicketRole(sessionA);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		String typeWhr =  getRoleTypeForManageTicket(sessionA);
		if(typeWhr.equals("ALL")) {
			Mmap.put("typeWhr","SHOW");
		}else if(role.equals("sd_orbat_entitlement")){
			Mmap.put("typeWhr","sd9");
		}else{
			Mmap.put("typeWhr","HIDE");
		}
		
		Mmap.put("getModuleNameList", getModuleNameHelpDeskListWithAll(sessionA));
		Mmap.put("label", "Manage User Tickets");
		Mmap.put("msg", msg);
		List<Tbl_CodesForm> comd=all.getCommandDetailsList();
		Mmap.put("getCommandList",comd);
		String selectComd ="<option value=''>--Select--</option>";
		Mmap.put("selectcomd", selectComd);
		Mmap.put("GetHelpTopic", GetHelpTopic(sessionA));
		return new ModelAndView("manageticketTiles");
	}
	
	
	@RequestMapping(value = "/admin/manageticketList", method = RequestMethod.POST)
	public ModelAndView manageticketList(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ticket1", required = false) String ticket,
			@RequestParam(value = "ticket_status1", required = false) String ticket_status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "help_topic1", required = false) String help_topic,
			@RequestParam(value = "type1", required = false) String type,
			@RequestParam(value = "module1", required = false) String module,
			@RequestParam(value = "label", required = false) String label1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String role = sessionA.getAttribute("role").toString();

		Boolean val = roledao.ScreenRedirect("myticket", roleid);
//		if (!val) {
//			return new ModelAndView("AccessTiles");
//		}
		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_date);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(from_date);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("manageticketTiles");
			}
		} catch (ParseException e) {}
		Mmap.put("ticket1", ticket);
		Mmap.put("ticket_status1", ticket_status);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("help_topic1", help_topic);
		Mmap.put("type1", type);
		Mmap.put("module1", module);
		Mmap.put("label", label1);
		Mmap.put("unit_name1", unit_name1);
		Mmap.put("cont_comd1", cont_comd1);
		Mmap.put("getModuleNameList", getModuleNameHelpDeskListWithAll(sessionA));
		String username = sessionA.getAttribute("username").toString();
		String moduleWhr =  getManageTicketRole(sessionA);
		String typeWhr =  getRoleTypeForManageTicket(sessionA);
		List<Map<String, Object>> list = help.manageTicketList(ticket,ticket_status,from_date,to_date,help_topic,username,roleid,module,label1,moduleWhr,typeWhr,unit_name1,cont_comd1,role);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		List<Tbl_CodesForm> comd=all.getCommandDetailsList();
		Mmap.put("getCommandList",comd);
		
		if(typeWhr.equals("ALL")) {
			Mmap.put("typeWhr","SHOW");
		}else {
			Mmap.put("typeWhr","HIDE");
		}
		return new ModelAndView("manageticketTiles");
	}

	private String getManageTicketRole(HttpSession sessionA) {
		String role = sessionA.getAttribute("role").toString();
		String moduleName = "";
		if(role.equalsIgnoreCase("cue_orbat") || role.equalsIgnoreCase("cue")) {
			moduleName =" ('ORBAT','UNIT ENTITLEMENT') ";
		}
		if(role.equalsIgnoreCase("orbat") || role.equalsIgnoreCase("orbat_deo") || role.equalsIgnoreCase("orbat_app")) {
			moduleName =" ('ORBAT') ";
		}
		if(role.equalsIgnoreCase("cue_deo") || role.equalsIgnoreCase("cue_app") || role.equalsIgnoreCase("cue_pers_app") || role.equalsIgnoreCase("cue_pers_deo") 
				|| role.equalsIgnoreCase("cuetrans_app_deo") || role.equalsIgnoreCase("cue_trans_deo") 
				|| role.equalsIgnoreCase("cue_wep_app") || role.equalsIgnoreCase("cue_wep_deo") || role.equalsIgnoreCase("cue_pers_app_deo") || role.equalsIgnoreCase("cue_mms") ) {
			moduleName =" ('UNIT ENTITLEMENT') ";
		}
		if(role.equalsIgnoreCase("tms") || role.equalsIgnoreCase("tms_deo") || role.equalsIgnoreCase("tms_app") || role.equalsIgnoreCase("tms_aveh")) {
			moduleName =" ('TRANSPORT') ";
		}
		if(role.equalsIgnoreCase("mms") || role.equalsIgnoreCase("mms_deo") || role.equalsIgnoreCase("mms_app")) {
			moduleName =" ('WEAPON','IT ASSET') ";
		}
		if(role.equalsIgnoreCase("IT_Admin")) {
			moduleName =" ('IT ASSET') ";
		}
		if(role.equalsIgnoreCase("mnh") || role.equalsIgnoreCase("mnh_app") || role.equalsIgnoreCase("mnh_deo")) {
			moduleName =" ('MEDICAL') ";
		}
		if(role.equalsIgnoreCase("psg") || role.equalsIgnoreCase("psg_app") || role.equalsIgnoreCase("psg_deo")) {
			moduleName =" ('PERSONNEL') ";
		}
		if(role.equalsIgnoreCase("super")) {
			moduleName =" ('ADMIN','ORBAT','UNIT ENTITLEMENT','TRANSPORT','WEAPON','MEDICAL','PERSONNEL','IT_Admin') ";
		}
		return moduleName;
	}
		
	private String getRoleTypeForManageTicket(HttpSession sessionA) {
		String role = sessionA.getAttribute("role").toString();
		String type = "";
		if(role.equalsIgnoreCase("cue_orbat")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("orbat")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("orbat_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("orbat_app")) {
			type ="APP";
		}
		
		if(role.equalsIgnoreCase("cue_mms")) {
			type ="ALL";
		}
		
		if(role.equalsIgnoreCase("cue")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("cue_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("cue_app")) {
			type ="APP";
		}
		if(role.equalsIgnoreCase("cue_pers_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("cue_pers_app_deo")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("cue_pers_app")) {
			type ="APP";
		}
		if(role.equalsIgnoreCase("cue_trans_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("cuetrans_app_deo")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("cue_wep_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("cue_wep_app")) {
			type ="APP";
		}
		
		
		if(role.equalsIgnoreCase("tms") ||role.equalsIgnoreCase("tms_aveh")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("tms_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("tms_app")) {
			type ="APP";
		}
		
		if(role.equalsIgnoreCase("mms")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("IT_Admin")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("mms_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("mms_app")) {
			type ="APP";
		}
		

		if(role.equalsIgnoreCase("mnh")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("mnh_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("mnh_app")) {
			type ="APP";
		}
		
		if(role.equalsIgnoreCase("psg")) {
			type ="ALL";
		}
		if(role.equalsIgnoreCase("psg_deo")) {
			type ="DEO";
		}
		if(role.equalsIgnoreCase("psg_app")) {
			type ="APP";
		}
		
		if(role.equalsIgnoreCase("super")) {
			type ="ALL";
		}
		return type;
	}
	
	@RequestMapping(value = "/transferTicketDetails")
	public ModelAndView transferTicketDetails(HttpSession sessionA,@ModelAttribute("transferid") int transferid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg){
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("manageticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(transferid);
		model.put("viewticketCMD", h);
		if (h.getModule() == -1) {
			model.put("moduleId", "others");
			model.put("moduleName", "others");
			List<HD_TB_BISAG_USER_INFO> user = getUserNameList(0);
			if(user.size() > 0) {
				model.put("agent_name", user.get(0));
			}
		} else {
			model.put("moduleId", h.getModule());
			model.put("moduleName", getModuleNameList(h.getModule()).get(0).getModule_name());
			model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0).getSubmodule_name());
			model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0).getScreen_name());
			List<HD_TB_BISAG_USER_INFO> user = getUserNameList(h.getModule());
			if(user.size() > 0) {
				model.put("agent_name", user.get(0));
			}
		}
		String help_topic = h.getHelp_topic();
		if(help_topic.equals("1")){model.put("help_topic", "Feedback");}
		if(help_topic.equals("2")){model.put("help_topic", "General Inquiry");}
		if(help_topic.equals("3")){model.put("help_topic", "Report a problem");}
		if(help_topic.equals("4")){model.put("help_topic", "Report a problem/Access Issues");}
		if(help_topic.equals("5")){model.put("help_topic", "Feature Request");}
		
		if(h.getSus_no().equals(null)){
			model.put("sus_no", "");
		}else{
			model.put("sus_no", h.getSus_no());
			if (all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).size() > 0) {
				model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).get(0));
			}
		}
		
		model.put("id", h.getId());
		model.put("ticket", h.getTicket());
		model.put("username", h.getUsername());
		model.put("created_on", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCreated_on()));
		model.put("completed_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCompleted_dt()));
		model.put("assigned_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getAssigned_dt()));
		model.put("screen_shot", h.getscreen_shot());
		model.put("issue_summary", h.getIssue_summary());
		model.put("description", h.getDescription());
		model.put("ticket_status", h.getTicket_status());
		model.put("assigned_to", h.getAssigned_to());
		
		model.put("getModuleNameList", getModuleNameList(-1));
		model.put("getSubModuleNameList", getSubModuleNameList(-1));
		model.put("getScreenList", getScreenNamebyCodeList(-1));
		
		return new ModelAndView("transferTicketTiles");
	}
	
	@RequestMapping(value = "/admin/transferTicketAction", method = RequestMethod.POST)
	public @ResponseBody List<String> transferTicketAction(HttpServletRequest request,ModelMap model, HttpSession session) {
		List<String> list = new ArrayList<>();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int module = Integer.parseInt(request.getParameter("module"));
			int sub_module = Integer.parseInt(request.getParameter("sub_module"));
			int screen_name = Integer.parseInt(request.getParameter("screen_name"));
			if(module == 0){
				list.add("Please select Module");
				return list;
			}if(sub_module  == 0){
				list.add("Please select Sub Module");
				return list;
			}if(screen_name == 0){
				list.add("Please select Screen");
				return list;
			}else {
				String username = session.getAttribute("username").toString();
				HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(id);
				
				HD_TB_BISAG_TICKET_TRANSFER_HISTORY t = new HD_TB_BISAG_TICKET_TRANSFER_HISTORY();
				t.setUserid(h.getUserid());
				t.setUsername(h.getUsername());
				t.setSus_no(h.getSus_no());
				t.setHelp_topic(h.getHelp_topic());
				t.setModule(h.getModule());
				t.setSub_module(h.getSub_module());
				t.setScreen_name(h.getScreen_name());
				t.setscreen_shot(h.getscreen_shot());
				t.setDescription(h.getDescription());
				t.setIssue_summary(h.getIssue_summary());
				t.setTicket_status(h.getTicket_status());
				t.setAssigned_to(h.getAssigned_to());
				t.setAssigned_dt(h.getAssigned_dt());
				t.setTicket(h.getTicket());
				t.setCreated_by(username);
				t.setCreated_on(new Date());
				t.setCompleted_dt(h.getCompleted_dt());
				t.setMiso_reply(h.getMiso_reply());
				session1.save(t);
				
				String hql = "update HD_TB_BISAG_TICKET_GENERATE set module=:module,sub_module=:sub_module,screen_name=:screen_name where id=:id";
				Query query = session1.createQuery(hql).setInteger("module", module).setInteger("sub_module",sub_module).setInteger("screen_name",screen_name).setInteger("id",id);
				query.executeUpdate();
				
				tx.commit();
				list.add("Data Saved Successfully.");
			}
		} catch (Exception e) {
			tx.rollback();
			list.add("an Error ocurre data saving. " + e.getMessage());
		}finally {
			session1.close();
		}
		return list;
	}
	
	@RequestMapping(value = "/viewticketDetails")
	public ModelAndView viewticketDetails(HttpSession sessionA, @ModelAttribute("updateid") int updateid,
			@ModelAttribute("label3") String label3, HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication){
		String roleid = sessionA.getAttribute("roleid").toString();
		String role = sessionA.getAttribute("role").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg1", msg);
		String moduleRoleValidate =	getManageTicketRole(sessionA);
		if (!moduleRoleValidate.equals("") && label3.equalsIgnoreCase("Manage User Tickets")) {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("viewticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
				
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(0);
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0).getModule_name());
				model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0).getSubmodule_name());
				model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0).getScreen_name());
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(h.getModule());
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			}
			String help_topic = h.getHelp_topic();
			if(help_topic.equals("1")){
				model.put("help_topic", "Feedback");
			}
			if(help_topic.equals("2")){
				model.put("help_topic", "General Inquiry");
			}
			if(help_topic.equals("3")){
				model.put("help_topic", "Report a problem");
			}
			if(help_topic.equals("4")){
				model.put("help_topic", "Report a problem/Access Issues");
			}
			if(help_topic.equals("5")){
				model.put("help_topic", "Feature Request");
			}
			if (help_topic.equals("6")) {
				model.put("help_topic", "Km Amdt");
			}
			if (help_topic.equals("7")) {
				model.put("help_topic", "Cl Amdt");
			}
			if (help_topic.equals("8")) {
				model.put("help_topic", "Nomenclature Amdt");
			}
			if (help_topic.equals("9")) {
				model.put("help_topic", "Addn of Veh");
			}
			if (help_topic.equals("10")) {
				model.put("help_topic", "Engine & Chassis Updation");
			}
			if (help_topic.equals("11")) {
				model.put("help_topic", "Veh Deposition");
			}
			if (help_topic.equals("12")) {
				model.put("help_topic", "Handing Taking Over");
			}
			if (help_topic.equals("13")) {
				model.put("help_topic", "IUT");
			}
			if (help_topic.equals("14")) {
				model.put("help_topic", "Report Issues");
			}
			if (help_topic.equals("15")) {
				model.put("help_topic", "LINE DTE OBSR");
			}
			
			if (help_topic.equals("15")) {
				model.put("help_topic", "Line Dte Obsr");
			}
			
			String susNo1 = h.getSus_no();
			if (susNo1 == null) {

				model.put("sus_no", "");
			}else{
				model.put("sus_no", h.getSus_no());
				if (all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).size() > 0) {
					model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).get(0));
				}
			}
			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("userid", h.getUserid());
			model.put("created_on", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCreated_on()));
			model.put("completed_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCompleted_dt()));
			model.put("assigned_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getAssigned_dt()));
			model.put("screen_shot", h.getscreen_shot());
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			model.put("assigned_to", h.getAssigned_to());
			
			String typeWhr =  getRoleTypeForManageTicket(sessionA);
			if(typeWhr.equals("ALL")){
				model.put("typeWhr","SHOW");
			}else {
				model.put("typeWhr","HIDE");
			}
			return new ModelAndView("viewticketTiles");
		} else if (!moduleRoleValidate.equals("") && label3.equalsIgnoreCase("My Tickets")) {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("userticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(0);
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0).getModule_name());
				model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0).getSubmodule_name());
				model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0).getScreen_name());
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(h.getModule());
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			}
			String help_topic = h.getHelp_topic();
			if (help_topic.equals("1")) {
				model.put("help_topic", "Feedback");
			}
			if (help_topic.equals("2")) {
				model.put("help_topic", "General Inquiry");
			}
			if (help_topic.equals("3")) {
				model.put("help_topic", "Report a problem");
			}
			if (help_topic.equals("4")) {
				model.put("help_topic", "Report a problem/Access Issues");
			}
			if (help_topic.equals("5")) {
				model.put("help_topic", "Feature Request");
			}
			if (help_topic.equals("6")) {
				model.put("help_topic", "Km Amdt");
			}
			if (help_topic.equals("7")) {
				model.put("help_topic", "Cl Amdt");
			}
			if (help_topic.equals("8")) {
				model.put("help_topic", "Nomenclature Amdt");
			}
			if (help_topic.equals("9")) {
				model.put("help_topic", "Addn of Veh");
			}
			if (help_topic.equals("10")) {
				model.put("help_topic", "Engine & Chassis Updation");
			}
			if (help_topic.equals("11")) {
				model.put("help_topic", "Veh Deposition");
			}
			if (help_topic.equals("12")) {
				model.put("help_topic", "Handing Taking Over");
			}
			if (help_topic.equals("13")) {
				model.put("help_topic", "IUT");
			}
			if (help_topic.equals("14")) {
				model.put("help_topic", "Report Issues");
			}
			
			if (help_topic.equals("15")) {
				model.put("help_topic", "Line Dte Obsr");
			}
			
			String susNo1 = h.getSus_no();
			if (susNo1 == null) {

				model.put("sus_no", "");
			} else {
				model.put("sus_no", h.getSus_no());
				if (all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).size() > 0 ) {
					model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).get(0));
				}
				
			}
			
			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("created_on", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCreated_on()));
			model.put("completed_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCompleted_dt()));
			model.put("assigned_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getAssigned_dt()));
			model.put("screen_shot", h.getscreen_shot());
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			return new ModelAndView("user_ticketTiles");
		} else if(role.equals("sd_orbat_entitlement")) {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("viewticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
				String help_topic = h.getHelp_topic();
				if (help_topic.equals("15")) {
					model.put("help_topic", "LINE DTE OBSR");
				}
				
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(0);
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0).getModule_name());
				model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0).getSubmodule_name());
				model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0).getScreen_name());
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(h.getModule());
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			}
			String help_topic = h.getHelp_topic();
			if(help_topic.equals("15")){
				model.put("help_topic", "");
			}
			
			String susNo1 = h.getSus_no();
			if (susNo1 == null) {

				model.put("sus_no", "");
			}else{
				model.put("sus_no", h.getSus_no());
				if (all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).size() > 0) {
					model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).get(0));
				}
			}
			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("userid", h.getUserid());
			model.put("created_on", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCreated_on()));
			model.put("completed_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCompleted_dt()));
			model.put("assigned_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getAssigned_dt()));
			model.put("screen_shot", h.getscreen_shot());
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			model.put("assigned_to", h.getAssigned_to());
			
				model.put("typeWhr","sd9");
			return new ModelAndView("viewticketTiles");
		}
		
		else {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("userticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(0);
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0).getModule_name());
				model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0).getSubmodule_name());
				model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0).getScreen_name());
				
				List<HD_TB_BISAG_USER_INFO> user = getUserNameList(h.getModule());
				if(user.size() > 0) {
					model.put("agent_name", user.get(0));
				}
			}
			String help_topic = h.getHelp_topic();
			if (help_topic.equals("1")) {
				model.put("help_topic", "Feedback");
			}
			if (help_topic.equals("2")) {
				model.put("help_topic", "General Inquiry");
			}
			if (help_topic.equals("3")) {
				model.put("help_topic", "Report a problem");
			}
			if (help_topic.equals("4")) {
				model.put("help_topic", "Report a problem/Access Issues");
			}
			if (help_topic.equals("5")) {
				model.put("help_topic", "Feature Request");
			}
			if (help_topic.equals("6")) {
				model.put("help_topic", "Km Amdt");
			}
			if (help_topic.equals("7")) {
				model.put("help_topic", "Cl Amdt");
			}
			if (help_topic.equals("8")) {
				model.put("help_topic", "Nomenclature Amdt");
			}
			if (help_topic.equals("9")) {
				model.put("help_topic", "Addn of Veh");
			}
			if (help_topic.equals("10")) {
				model.put("help_topic", "Engine & Chassis Updation");
			}
			if (help_topic.equals("11")) {
				model.put("help_topic", "Veh Deposition");
			}
			if (help_topic.equals("12")) {
				model.put("help_topic", "Handing Taking Over");
			}
			if (help_topic.equals("13")) {
				model.put("help_topic", "IUT");
			}
			if (help_topic.equals("14")) {
				model.put("help_topic", "Report Issues");
			}
			
			if (help_topic.equals("15")) {
				model.put("help_topic", "Line Dte Obsr");
			}
			
			String susNo1 = h.getSus_no();
			if (susNo1 == null) {
             model.put("sus_no", "");
			} else {
				model.put("sus_no", h.getSus_no());
				if (all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).size() > 0 ) {
			    	model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(h.getSus_no(), sessionA).get(0));
				}
			}

			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("created_on", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCreated_on()));
			model.put("completed_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getCompleted_dt()));
			model.put("assigned_dt", d.ConvertYYYY_MM_DDtoDD_MM_YYYY(h.getAssigned_dt()));
			model.put("screen_shot", h.getscreen_shot());
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			return new ModelAndView("user_ticketTiles");
		}
	}

	@RequestMapping(value = "/viewticketAction", method = RequestMethod.POST)
	public ModelAndView viewticketAction(@ModelAttribute("viewticketCMD") HD_TB_BISAG_TICKET_GENERATE uh,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		String role = session.getAttribute("role").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if(role.equals("sd_orbat_entitlement")) {
			
			model.put("msg", help.UpdateViewDetailsSD(uh));
			return new ModelAndView("redirect:manageticket");
		}else {
		String assigned_to = request.getParameter("agent_name");
		String usernamenew = request.getParameter("username");
		String useridnew = request.getParameter("userid");
		String ticket = request.getParameter("ticket");
		if(assigned_to == "0"){
			model.put("updateid", uh.getId());
			model.put("msg", "Please Select Agent name");
			return new ModelAndView("redirect:viewticketDetails");
		}
		if(uh.getTicket_status().equals("0")){
			model.put("updateid", uh.getId());
			model.put("msg", "Please Select status");
			return new ModelAndView("redirect:viewticketDetails");
		}
		Session sessionw = HibernateUtil.getSessionFactory().openSession();
		Transaction txw = sessionw.beginTransaction();
		Query qw = sessionw.createQuery("select count(id) from HD_TB_BISAG_TICKET_GENERATE where assigned_to =:assigned_to and ticket_status='1'");
		qw.setParameter("assigned_to", assigned_to);
		Long count = (Long) qw.list().get(0);
		txw.commit();
		sessionw.close();
		 
        String title = "Tikect Status" ;
         String description = " Your Ticket No Is "+ticket +" Reply from MISO. " ;
		         Boolean d = notification.sendNotification_tms(title, description,useridnew, usernamenew,uh.getId());
		if (count > 30) {
			model.put("msg","The selected agent already has 30 tickets in progress, please assign his ticket to another agent");
			return new ModelAndView("redirect:viewticketDetails?updateid=" + uh.getId());
		} else {
			model.put("msg", help.UpdateViewDetailsValue(uh, assigned_to));
		}
		return new ModelAndView("redirect:manageticket");
	}
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public ModelAndView help(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("help", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(sessionA));
		Mmap.put("getSubModuleNameList", getSubModuleList(sessionA));
		Mmap.put("getScreenList", getScreenList(sessionA));
		Mmap.put("GetHelpTopic", GetHelpTopicold());
		return new ModelAndView("helpTiles");
	}

	public @ResponseBody List<TB_LDAP_MODULE_MASTER> getModuleNameHelpDeskList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_MODULE_MASTER where id in (select distinct module.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id DESC ");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<TB_LDAP_SUBMODULE_MASTER> getSubModuleList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_SUBMODULE_MASTER where id in (select distinct sub_module.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SUBMODULE_MASTER> list = (List<TB_LDAP_SUBMODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<TB_LDAP_SCREEN_MASTER> getScreenList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_SCREEN_MASTER where id in (select distinct screen.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SCREEN_MASTER> list = (List<TB_LDAP_SCREEN_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}


	/*@RequestMapping(value = "/helpAction", method = RequestMethod.POST)
	public ModelAndView helpAction(@ModelAttribute("helpAction") HD_TB_BISAG_TICKET_GENERATE N,
			@RequestParam(value = "filedoc", required = false) MultipartFile filedoc, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("help", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String userId = session.getAttribute("userId").toString();
		int id = Integer.parseInt(userId);
		String user_id = "";
		try {
			if (N.getModule() == 0) {
				model.put("msg", "Please Select Module");
				return new ModelAndView("redirect:help");
			}
			if (N.getSub_module() == 0 && N.getModule() != -1) {
				model.put("msg", "Please Select Sub Module");
				return new ModelAndView("redirect:help");
			}
			if (N.getScreen_name() == 0 && N.getModule() != -1) {
				model.put("msg", "Please Select Screen Name");
				return new ModelAndView("redirect:help");
			}
			if (N.getHelp_topic().equals("0")) {
				model.put("msg", "Please Select Help Topic");
				return new ModelAndView("redirect:help");
			}
			if (N.getIssue_summary().equals("")) {
				model.put("msg", "Please Enter Issue Summary");
				return new ModelAndView("redirect:help");
			} else if (validation.checkIssue_summaryHelpdeskLength(N.getIssue_summary()) == false) {
				model.put("msg", validation.Issue_summaryHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			if (N.getDescription().equals("")) {
				model.put("msg", "Please Enter Description");
				return new ModelAndView("redirect:help");
			} else if (validation.checkDescriptionLengthHelpdeskLength(N.getDescription()) == false) {
				model.put("msg", validation.DescriptionLengthHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			if (!filedoc.isEmpty()) {
				String doc_path1Ext = FilenameUtils.getExtension(filedoc.getOriginalFilename()).toUpperCase();
				final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
				if (filedoc.getSize() > fileSizeLimit) {
					model.put("msg", "File size should be less then 2 MB");
					return new ModelAndView("redirect:help");
				}
				if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")) {
					model.put("msg", "Only *.pdf or *.jpeg  file extension allowed");
					return new ModelAndView("redirect:help");
				}
				String fname = "";
				// code modify by Paresh on 05/05/20202
				try {
					String extension = "";
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = filedoc.getBytes();
					String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
					File dir = new File(helpdeskFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = filedoc.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() +"_"+ userId + "_HELPDESK." + extension;
					if (validation.checkImageAnmlLength(fname) == false){
						model.put("msg", validation.image_MSG);
						return new ModelAndView("redirect:help");
					}
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					N.setscreen_shot(fname);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		int ticket = getMaxticket();
		N.setTicket(ticket);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String year1[] = String.valueOf(year).split("");
		int year2 = Integer.parseInt(year1[2]);
		int year3 = Integer.parseInt(year1[3]);
		String splityear = year2 + "" + year3;

		int month;
		GregorianCalendar date = new GregorianCalendar();
		month = date.get(Calendar.MONTH);
		month = month + 1;
		String s = String.valueOf(month);
		String month1 = StringUtils.leftPad(s, 2, "0");
		String ticket = String.valueOf(getMaxticket());
		String t[] = String.valueOf(ticket).split("");
		String FirstDigit = Integer.parseInt(t[0]) + "" + Integer.parseInt(t[1]);
		String splitT = Integer.parseInt(t[2]) + "" + Integer.parseInt(t[3]);
		String lastdigit = Integer.parseInt(t[4]) + "" + Integer.parseInt(t[5]) + "" + Integer.parseInt(t[6]) + ""
				+ Integer.parseInt(t[7]);

		if (splityear.equals(FirstDigit)) {
			if (month1.equals(splitT)) {
				// String ticket1 = StringUtils.leftPad(ticket,4,"0");
				String str = StringUtils.leftPad(lastdigit, 6, month1);
				String str1 = StringUtils.leftPad(str, 8, splityear);
				int ticketcode1 = Integer.parseInt(str1);
				N.setTicket(ticketcode1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcode1);
			} else {
				String ticket2 = StringUtils.leftPad("1", 4, "0");
				String strnew = StringUtils.leftPad(ticket2, 6, month1);
				String strnew1 = StringUtils.leftPad(strnew, 8, splityear);
				int ticketcodenew1 = Integer.parseInt(strnew1);
				N.setTicket(ticketcodenew1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcodenew1);
			}
		} else {
			if (month1.equals(splitT)) {
				// String ticket1 = StringUtils.leftPad(ticket,4,"0");
				String str = StringUtils.leftPad(lastdigit, 6, month1);
				String str1 = StringUtils.leftPad(str, 8, splityear);
				int ticketcode1 = Integer.parseInt(str1);
				N.setTicket(ticketcode1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcode1);
			} else {
				String ticket2 = StringUtils.leftPad("1", 4, "0");
				String strnew = StringUtils.leftPad(ticket2, 6, month1);
				String strnew1 = StringUtils.leftPad(strnew, 8, splityear);
				int ticketcodenew1 = Integer.parseInt(strnew1);
				N.setTicket(ticketcodenew1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcodenew1);
			}
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			N.setSus_no(roleSusNo);
		} 

		N.setCreated_by(username);
		N.setCreated_on(new Date());
		N.setTicket_status("0");
		N.setUserid(Integer.parseInt(userId));
		String module_name = request.getParameter("module_name");
		String sub_module = request.getParameter("sub_module_name");
		String screen = request.getParameter("screen_name_text");
		//String description= module_name +" : " + sub_module + " : " + screen + "\n" + " (i) "+N.getIssue_summary() + "\n" + " (ii) "+N.getDescription();
		String unit_name=notif.getUnitNameFromUserId(id);
		String description="Please Check the Ticket in Manage Ticket Screen";
		Integer module_id = N.getModule();
		List<String> list = notif.getUserIdForNotif(module_id);
		for(int i=0;i<list.size();i++) {
			user_id+=list.get(i);
			if(i<list.size()-1)
				user_id+=",";
		}
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int N_id=(int)session1.save(N);
		
		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		Boolean d = notification.sendNotification_tms("TICKET GENERATED for " +  module_name +" : " + sub_module + " : " + screen + " by " + unit_name,description,user_id, username,N_id);
		}
		//session1.save(tic_gen_noti);
		session1.getTransaction().commit();
		session1.close();
		model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
		return new ModelAndView("redirect:help");
	}*/
	
	
	@RequestMapping(value = "/helpAction", method = RequestMethod.POST)
	public ModelAndView helpAction(@ModelAttribute("helpAction") HD_TB_BISAG_TICKET_GENERATE N,
			@RequestParam(value = "filedoc", required = false) MultipartFile filedoc, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("help", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String userId = session.getAttribute("userId").toString();
		int id = Integer.parseInt(userId);
		String user_id = "";
		String description1 = "";
		
		try {
		    description1 = request.getParameter("description");
			System.err.println("val of description --" + description1 + " Size of description " + description1.length());
			if (N.getModule() == 0) {
				model.put("msg", "Please Select Module");
				return new ModelAndView("redirect:help");
			}
			if (N.getSub_module() == 0 && N.getModule() != -1) {
				model.put("msg", "Please Select Sub Module");
				return new ModelAndView("redirect:help");
			}
			if (N.getScreen_name() == 0 && N.getModule() != -1) {
				model.put("msg", "Please Select Screen Name");
				return new ModelAndView("redirect:help");
			}
			if (N.getHelp_topic().equals("0")) {
				model.put("msg", "Please Select Help Topic");
				return new ModelAndView("redirect:help");
			}
			if (N.getIssue_summary().equals("")) {
				model.put("msg", "Please Enter Issue Summary");
				return new ModelAndView("redirect:help");
			} else if (validation.checkIssue_summaryHelpdeskLength(N.getIssue_summary()) == false) {
				model.put("msg", validation.Issue_summaryHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			if (N.getDescription().equals("")) {
				model.put("msg", "Please Enter Description");
				return new ModelAndView("redirect:help");
			} else if (validation.checkDescriptionLengthHelpdeskLength(N.getDescription()) == false) {
				model.put("msg", validation.DescriptionLengthHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			
			if (!description1.equals("") && description1 != null) {
				description1 =description1.replaceAll("\\s+", " ").trim();
				System.err.println("2..val of description --" + description1 + " Size of description " + description1.length());
			}
			
			/*if (!description.equals("") && description != null) {
				description =MULTI_SPACE_PATTERN.matcher;
				System.err.println("2..val of description --" + description + " Size of description " + description.length());
			}*/
			
			if (!filedoc.isEmpty()) {
				String doc_path1Ext = FilenameUtils.getExtension(filedoc.getOriginalFilename()).toUpperCase();
				final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
				if (filedoc.getSize() > fileSizeLimit) {
					model.put("msg", "File size should be less then 2 MB");
					return new ModelAndView("redirect:help");
				}
				if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")) {
					model.put("msg", "Only *.pdf or *.jpeg  file extension allowed");
					return new ModelAndView("redirect:help");
				}
				String fname = "";
				// code modify by Paresh on 05/05/20202
				try {
					String extension = "";
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = filedoc.getBytes();
					String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
					File dir = new File(helpdeskFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = filedoc.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() +"_"+ userId + "_HELPDESK." + extension;
					if (validation.checkImageAnmlLength(fname) == false){
						model.put("msg", validation.image_MSG);
						return new ModelAndView("redirect:help");
					}
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					N.setscreen_shot(fname);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		int ticket = getMaxticket();
		N.setTicket(ticket);
		N.setDescription(description1);
		/*int year = Calendar.getInstance().get(Calendar.YEAR);
		String year1[] = String.valueOf(year).split("");
		int year2 = Integer.parseInt(year1[2]);
		int year3 = Integer.parseInt(year1[3]);
		String splityear = year2 + "" + year3;

		int month;
		GregorianCalendar date = new GregorianCalendar();
		month = date.get(Calendar.MONTH);
		month = month + 1;
		String s = String.valueOf(month);
		String month1 = StringUtils.leftPad(s, 2, "0");
		String ticket = String.valueOf(getMaxticket());
		String t[] = String.valueOf(ticket).split("");
		String FirstDigit = Integer.parseInt(t[0]) + "" + Integer.parseInt(t[1]);
		String splitT = Integer.parseInt(t[2]) + "" + Integer.parseInt(t[3]);
		String lastdigit = Integer.parseInt(t[4]) + "" + Integer.parseInt(t[5]) + "" + Integer.parseInt(t[6]) + ""
				+ Integer.parseInt(t[7]);

		if (splityear.equals(FirstDigit)) {
			if (month1.equals(splitT)) {
				// String ticket1 = StringUtils.leftPad(ticket,4,"0");
				String str = StringUtils.leftPad(lastdigit, 6, month1);
				String str1 = StringUtils.leftPad(str, 8, splityear);
				int ticketcode1 = Integer.parseInt(str1);
				N.setTicket(ticketcode1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcode1);
			} else {
				String ticket2 = StringUtils.leftPad("1", 4, "0");
				String strnew = StringUtils.leftPad(ticket2, 6, month1);
				String strnew1 = StringUtils.leftPad(strnew, 8, splityear);
				int ticketcodenew1 = Integer.parseInt(strnew1);
				N.setTicket(ticketcodenew1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcodenew1);
			}
		} else {
			if (month1.equals(splitT)) {
				// String ticket1 = StringUtils.leftPad(ticket,4,"0");
				String str = StringUtils.leftPad(lastdigit, 6, month1);
				String str1 = StringUtils.leftPad(str, 8, splityear);
				int ticketcode1 = Integer.parseInt(str1);
				N.setTicket(ticketcode1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcode1);
			} else {
				String ticket2 = StringUtils.leftPad("1", 4, "0");
				String strnew = StringUtils.leftPad(ticket2, 6, month1);
				String strnew1 = StringUtils.leftPad(strnew, 8, splityear);
				int ticketcodenew1 = Integer.parseInt(strnew1);
				N.setTicket(ticketcodenew1);
				model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticketcodenew1);
			}
		}*/
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			N.setSus_no(roleSusNo);
		} 

		N.setCreated_by(username);
		N.setCreated_on(new Date());
		N.setTicket_status("0");
		N.setUserid(Integer.parseInt(userId));
		String module_name = request.getParameter("module_name");
		String sub_module = request.getParameter("sub_module_name");
		String screen = request.getParameter("screen_name_text");
		//String description= module_name +" : " + sub_module + " : " + screen + "\n" + " (i) "+N.getIssue_summary() + "\n" + " (ii) "+N.getDescription();
		String unit_name=notif.getUnitNameFromUserId(id);
		String description="Please Check the Ticket in Manage Ticket Screen";
		Integer module_id = N.getModule();
		List<String> list = notif.getUserIdForNotif(module_id);
		for(int i=0;i<list.size();i++) {
			user_id+=list.get(i);
			if(i<list.size()-1)
				user_id+=",";
		}
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int N_id=(int)session1.save(N);
		
		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
		Boolean d = notification.sendNotification_tms("TICKET GENERATED for " +  module_name +" : " + sub_module + " : " + screen + " by " + unit_name,description,user_id, username,N_id);
		}
		//session1.save(tic_gen_noti);
		session1.getTransaction().commit();
		session1.close();
		model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
		return new ModelAndView("redirect:help");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserNameList", method = RequestMethod.POST)
	public @ResponseBody List<HD_TB_BISAG_USER_INFO> getUserNameList(int id3) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (id3 == 0) {
			q = session.createQuery("select id,agent_name from HD_TB_BISAG_USER_INFO ");
		} else {
			q = session.createQuery("select id,agent_name from HD_TB_BISAG_USER_INFO WHERE moduleid=:id3");
			q.setParameter("id3", id3);
		}
		List<HD_TB_BISAG_USER_INFO> list = (List<HD_TB_BISAG_USER_INFO>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/user_management", method = RequestMethod.GET)
	public ModelAndView User_management(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_management", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		if(UserManagementRoleAccess(sessionA) == true) {
			Mmap.put("getModuleNameList", getModuleNameHelpDeskListWithAll(sessionA));
			Mmap.put("msg", msg);
			Mmap.put("list.size()", 0);
			return new ModelAndView("user_managementTiles");
		}else {
			return new ModelAndView("AccessTiles");
		}
	}
	
	private Boolean UserManagementRoleAccess(HttpSession sessionA) {
		String role = sessionA.getAttribute("role").toString();
		if(role.equalsIgnoreCase("super")) {
			return true;
		}
		if(role.equalsIgnoreCase("cue_orbat")) {
			return true;
		}
		if(role.equalsIgnoreCase("orbat")) {
			return true;
		}
		if(role.equalsIgnoreCase("cue")) {
			return true;
		}
		if(role.equalsIgnoreCase("tms")) {
			return true;
		}
		if(role.equalsIgnoreCase("mms")) {
			return true;
		}
		if(role.equalsIgnoreCase("mnh")) {
			return true;
		}
		if(role.equalsIgnoreCase("psg")) { 
			return true;
		}
		
		if(role.equalsIgnoreCase("IT_Admin")) {  
			return true;
		}
		if(role.equalsIgnoreCase("cuetrans_app_deo")) {
			return true;
		}
		if(role.equalsIgnoreCase("tms_aveh")) {
			return true;
		}
		return false;
	}
	
	/*private String getModuleNameWhr(HttpSession sessionA) {
		String role = sessionA.getAttribute("role").toString();
		String moduleName = "";
		if(role.equalsIgnoreCase("cue_orbat")) {
			moduleName =" where module_name in ('ORBAT','UNIT ENTITLEMENT') ";
		}
		if(role.equalsIgnoreCase("orbat")) {
			moduleName =" where module_name in ('ORBAT') ";
		}
		if(role.equalsIgnoreCase("cue")) {
			moduleName =" where module_name in ('UNIT ENTITLEMENT') ";
		}
		if(role.equalsIgnoreCase("tms")) {
			moduleName =" where module_name in ('TRANSPORT') ";
		}
		if(role.equalsIgnoreCase("mms")) {
			moduleName =" where  module_name in ('WEAPON') ";
		}
		if(role.equalsIgnoreCase("mnh")) {
			moduleName =" where  module_name in ('MEDICAL') ";
		}
		if(role.equalsIgnoreCase("psg")) {
			moduleName =" where  module_name in ('PSG') ";
		}
		if(role.equalsIgnoreCase("super")) {
			moduleName =" where  module_name in ('ADMIN','ORBAT','UNIT ENTITLEMENT','TRANSPORT','WEAPON','MEDICAL','PSG') ";
		}
		return moduleName;
	}*/
	
	public List<TB_LDAP_MODULE_MASTER> getModuleNameHelpDeskListWithAll(HttpSession sessionA) {
		String moduleName = getManageTicketRole(sessionA);
		if(!moduleName.equals("")) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_LDAP_MODULE_MASTER  where  module_name in "+moduleName+" order by module_name");
			@SuppressWarnings("unchecked")
			List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
			tx.commit();
			session.close();
			return list;
		}else {
			return null;
		}
	}

	@RequestMapping(value = "/user_managementAction", method = RequestMethod.POST)
	public ModelAndView user_managementAction(@ModelAttribute("user_managementCMD") HD_TB_BISAG_USER_INFO user,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, ModelMap model,
			HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_management", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (user.getModuleid() == 0) {
			model.put("msg", "Please Select Module");
			return new ModelAndView("redirect:user_management");
		}
		if (user.getAgent_name().equals("")) {
			model.put("msg", "Please Enter Agent name");
			return new ModelAndView("redirect:user_management");
		} else if (validation.checkagent_nameHelpdeskLength(user.getAgent_name()) == false) {
			model.put("msg", validation.agent_nameHelpdeskMSG);
			return new ModelAndView("redirect:user_management");
		}

		String username = session.getAttribute("username").toString();
		int module1 = Integer.parseInt(request.getParameter("moduleid"));
		String module = request.getParameter("moduleid");
		String agent_name1 = request.getParameter("agent_name");
		String id = request.getParameter("id_hid");
		if (id == "") {
			Long users = help.checkExitstingUserManagement(module1, agent_name1);
			if (users > 0) {
				model.put("module_name1", module1);
				model.put("agent_name1", agent_name1);
				List<Map<String, Object>> list = help.getHelpMngtListJdbc(String.valueOf(module), "");
				model.put("list", list);
				model.put("list.size()", list.size());
				model.put("msg", "Data already exist");
			} else {
				user.setCreated_by(username);
				user.setCreated_on(new Date());
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				sessionHQL.beginTransaction();
				sessionHQL.save(user);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();
				model.put("module_name1", module1);
				model.put("agent_name1", agent_name1);
				List<Map<String, Object>> list = help.getHelpMngtListJdbc(String.valueOf(module), "");
				model.put("list", list);
				model.put("list.size()", list.size());
				model.put("msg", "Data saved Successfully");
			}
		} else {
			Long users = checkExitstingUserManagementUpdate(module1, agent_name1, id);
			if (users > 0) {
				model.put("module_name1", module1);
				model.put("agent_name1", agent_name1);
				List<Map<String, Object>> list = help.getHelpMngtListJdbc(String.valueOf(module), "");
				model.put("list", list);
				model.put("list.size()", list.size());
				model.put("msg", "Data already exist");
			} else {
				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				String hql = "update HD_TB_BISAG_USER_INFO set moduleid=:module1,agent_name=:agent_name1 where cast(id as text)=:id";
				Query query = session1.createQuery(hql).setInteger("module1", module1)
						.setString("agent_name1", agent_name1).setString("id", id);
				int rowCount = query.executeUpdate();
				tx.commit();
				session1.close();
				if (rowCount > 0) {
					model.put("msg", "Data Updated Successfully");
				} else {
					model.put("msg", "Data Not Updated Successfully");
				}
				model.put("module_name1", module1);
				model.put("agent_name1", agent_name1);
				List<Map<String, Object>> list = help.getHelpMngtListJdbc(String.valueOf(module), "");
				model.put("list", list);
				model.put("list.size()", list.size());
			}
		}
		model.put("getModuleNameList",getModuleNameHelpDeskListWithAll(session));
		return new ModelAndView("user_managementTiles");
	}

	public Long checkExitstingUserManagementUpdate(int module, String agent_name, String id) {
		String hql = "select count(*) from HD_TB_BISAG_USER_INFO where moduleid=:moduleid and LOWER(agent_name)=:agent_name and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setParameter("moduleid", module);
		q.setParameter("agent_name", agent_name.toLowerCase());
		q.setParameter("id", Integer.parseInt(id));
		Long users = (Long) q.uniqueResult();
		tx.commit();
		session.close();
		return users;
	}

	@RequestMapping(value = "/deleteUsermngURL", method = RequestMethod.POST)
	@ResponseBody
	public List<String> deleteUsermngURL(int deleteid, HttpSession sessionA) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from HD_TB_BISAG_USER_INFO where id = :id";
			int app = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}

	@RequestMapping(value = "/getHelpMngtList1", method = RequestMethod.POST)
	public ModelAndView getHelpMngtList1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "module_name1", required = false) String module,
			@RequestParam(value = "agent_name1", required = false) String agent_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_management", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (module == "0") {
			Mmap.put("msg", "Please Select Module");
			return new ModelAndView("redirect:user_management");
		}
		if (validation.checkagent_nameHelpdeskLength(agent_name) == false) {
			Mmap.put("msg", validation.agent_nameHelpdeskMSG);
			return new ModelAndView("redirect:user_management");
		}
		Mmap.put("module_name1", module);
		Mmap.put("agent_name1", agent_name);
		Mmap.put("getModuleNameList",getModuleNameHelpDeskListWithAll(session)); // getModuleNameHelpDeskList(session)
		List<Map<String, Object>> list = help.getHelpMngtListJdbc(module, agent_name);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("user_managementTiles");
	}
	
	@RequestMapping(value = "/getUserNameAutoComplateList",method = RequestMethod.POST)
	public @ResponseBody List<String> getUserNameAutoComplateList(String agent_name,String moduleName,HttpSession sessionA) {
		if(!agent_name.equals("")) {
			String role = sessionA.getAttribute("role").toString();
			String whr = "";
			if(moduleName.equalsIgnoreCase("ORBAT")) {
				whr = " and r.role in ('orbat_deo','orbat_app') ";
			}
			if(moduleName.equalsIgnoreCase("UNIT ENTITLEMENT")) {
				whr = " and r.role in ('cue_deo','cue_app','cue_pers_app','cue_pers_deo','cuetrans_app_deo','cue_trans_deo','cue_wep_app','cue_wep_deo','cue_pers_app_deo','cue_mms') ";
			}
			if(moduleName.equalsIgnoreCase("TRANSPORT")) {
				whr = " and r.role in ('tms_deo','tms_app','tms_aveh') ";
			}
			if(moduleName.equalsIgnoreCase("WEAPON")) {
				whr = " and r.role in ('mms_deo','mms_app') ";
			}
			if(moduleName.equalsIgnoreCase("MEDICAL")) {
				whr = " and r.role in ('mnh_deo','mnh_app') ";
			}
			if(moduleName.equalsIgnoreCase("PSG")) {
				whr = " and r.role in ('psg_deo','psg_app') ";
			}
			if(moduleName.equalsIgnoreCase("ADMIN")) {
				whr = " and r.role in ('super') ";
			}
			if(moduleName.equalsIgnoreCase("IT ASSET")) {
				whr = " and r.role in ('IT_Admin','it_deo','it_app','mms_deo','mms_app') ";
			}
			if(!whr.equals("")) {
				return help.getUserNameAutoComplateList(agent_name,whr);
			}else {
				System.err.println("In Else getUserNameAutoComplateList"  );
				return null;
			}
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getUseridByUserName",method = RequestMethod.POST)
	public @ResponseBody List<String> getUseridByUserName(String userName,HttpSession sessionUserId) {
		return help.getUseridByUserName(userName);
	}
	
	
	@RequestMapping(value = "/faqinsert", method = RequestMethod.GET)
	public ModelAndView faqinsert(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("faqinsert", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("faqinsertTiles");
	}

	@RequestMapping(value = "/faqAction", method = RequestMethod.POST)
	public ModelAndView faqAction(@ModelAttribute("faqCMD") HD_TB_BISAG_FAQ al, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("faqinsert", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (al.getSection().equals("")) {
			model.put("msg", "Please enter section");
			return new ModelAndView("redirect:faqinsert");
		} else if (validation.checksectionHelpdeskLength(al.getSection()) == false) {
			model.put("msg", validation.sectionHelpdeskMSG);
			return new ModelAndView("redirect:faqinsert");
		}
		if (al.getQuestion().equals("")) {
			model.put("msg", "Please enter question");
			return new ModelAndView("redirect:faqinsert");
		} else if (validation.checkquestionHelpdeskLength(al.getQuestion()) == false) {
			model.put("msg", validation.questionHelpdeskMSG);
			return new ModelAndView("redirect:faqinsert");
		}
		if (al.getAnswer().equals("")) {
			model.put("msg", "Please enter answer");
			return new ModelAndView("redirect:faqinsert");
		}
		String username = session.getAttribute("username").toString();
		Date created_on = new Date();
		String sec = request.getParameter("section").toUpperCase();
		al.setSection(sec.toUpperCase());
		al.setCreated_by(username);
		al.setCreated_on(created_on);
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		session1.save(al);
		session1.getTransaction().commit();
		session1.close();
		model.put("msg", "Data saved Successfully");
		return new ModelAndView("redirect:faqinsert");
	}

	@RequestMapping(value = "/getsectionlist", method = RequestMethod.POST)
	public @ResponseBody List<String> getsectionlist(String section, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct section from HD_TB_BISAG_FAQ where upper(section) like :section").setMaxResults(10);
		q.setParameter("section", section.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
		
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		// Enc Key Append Last value of List
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/searchfaq", method = RequestMethod.GET)
	public ModelAndView Searchfaq(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchfaq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("searchfaqTiles");
	}

	@RequestMapping(value = "/admin/TicketSearchList1", method = RequestMethod.POST)
	public ModelAndView TicketSearchList1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "section1", required = false) String section) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchfaq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checksectionHelpdeskLength(section) == false) {
			Mmap.put("msg", validation.sectionHelpdeskMSG);
			return new ModelAndView("redirect:searchfaq");
		}

		Mmap.put("section1", section);
		List<Map<String, Object>> list = help.TicketSearchList(section);
		Mmap.put("list", list);
		Mmap.put("list.size", list.size());
		return new ModelAndView("searchfaqTiles");
	}

	@RequestMapping(value = "/UpdateFAQ", method = RequestMethod.POST)
	public ModelAndView UpdateFAQ(@ModelAttribute("updateid") int updateid, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchfaq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("Edit_faqCMD", help.geteditfaqbyId(updateid));
		model.put("msg", msg);
		return new ModelAndView("Edit_faqTiles");
	}

	@RequestMapping(value = "/Edit_faqAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Edit_faqAction(@ModelAttribute("Edit_faqCMD") HD_TB_BISAG_FAQ updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchfaq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", help.UpdateFAQDetailsValue(updateid));
		return new ModelAndView("redirect:searchfaq");
	}

	@RequestMapping(value = "/deleteFAQdetailsUrlAdd", method = RequestMethod.POST)
	@ResponseBody
	public List<String> deleteFAQdetailsUrlAdd(int deleteid, HttpSession sessionA) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from HD_TB_BISAG_FAQ where id =:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}

	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public ModelAndView faq(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String section,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("faq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		ArrayList<ArrayList<String>> list = help.getNewfaqdetail1();
		List<String> secList = help.getsecList();
		List<String> queList = help.getqueList();
		String faq = "";
		for (int i = 0; i < secList.size(); i++) {
			faq += "<div class='panel panel-default'>" + "<div class='panel-heading' role='tab' id='heading" + i + "'>"
					+ "<h3 class='panel-title'>"
					+ "<a class='collapsed' role='button' data-toggle='collapse' data-parent='#accordion' href='#collapse"
					+ i + "' aria-expanded='true' aria-controls='collapse" + i + "'>" + secList.get(i) + "</a>"
					+ "</h3>" + "</div>";

			faq += "<div id='collapse" + i
					+ "' class='panel-collapse collapse' role='tabpanel' aria-labelledby='heading" + i + "'>"
					+ "<div class='panel-body px-3 mb-4'>";

			for (int j = 0; j < list.size(); j++) {
				if (secList.get(i).equals(list.get(j).get(2))) {
					faq += "<div class='panel-group' id='accordionab' role='tablist' aria-multiselectable='true'>"
							+ "<div class='panel panel-default-que'>"
							+ "<div class='panel-heading-que' role='tab' id='headingab" + j + "'>"
							+ "<h3 class='panel-title-que'>"
							+ "<a class='collapsed' role='button' data-toggle='collapse' data-parent='#accordionab' href='#collapseab"
							+ j + "' aria-expanded='true' aria-controls='collapseab" + j + "'>" + list.get(j).get(0)
							+ "</a>" + "</h3>" + "</div>";
					faq += "<div id='collapseab" + j
							+ "' class='panel-collapse collapse' role='tabpanel' aria-labelledby='headingab" + j + "'>"
							+ "<div class='panel-body-que px-3 mb-4'>";

					for (int k = 0; k < list.size(); k++) {
						if (queList.get(j).equals(list.get(k).get(0))) {
							faq += "<p>" + list.get(k).get(1) + "</p>";
						}
					}
					faq += "</div>" + "</div>" + "</div>" + "</div>";
				}
			}
			faq += "</div>" + "</div>";
			faq += "</div>";
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("faq", faq);
		Mmap.put("msg", msg);
		return new ModelAndView("faqTiles", "faqCmd", new HD_TB_BISAG_FAQ());
	}

	public int getMaxticket() {
		// Current Year last two digits
		String currentyear = String.format("%ty", Year.now());
		// Current Month two digits		
		GregorianCalendar date = new GregorianCalendar();
		String currentmonth = StringUtils.leftPad(String.valueOf(date.get(Calendar.MONTH) + 1), 2, "0");
		String currentyearmonth = currentyear+currentmonth;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select cast(ticket as string) from HD_TB_BISAG_TICKET_GENERATE where cast(ticket as string) like '"+currentyearmonth+"%' order by id DESC").setMaxResults(1);
		int Ticket = 0;
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		if(list.size() > 0) {
			String PreviousTicket = String.valueOf(list.get(0));
			Ticket = Integer.parseInt(PreviousTicket) +  1;
		}else {
			Ticket =  Integer.parseInt(currentyear+currentmonth+"0001");
		}
		tx.commit();
		session.close();
		return Ticket;
	}

	@SuppressWarnings("unchecked")
	//@RequestMapping(value = "/getModuleNameList")
	public List<TB_LDAP_MODULE_MASTER> getModuleNameList(int module_hid) {
		String whr = "";
		if(module_hid > 0) {
			whr = " WHERE id=:id ";
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" FROM TB_LDAP_MODULE_MASTER " + whr);
		if(module_hid > 0) {
			q.setParameter("id", module_hid);
		}
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	//@RequestMapping(value = "/getSubModuleNameList")
	public @ResponseBody List<TB_LDAP_SUBMODULE_MASTER> getSubModuleNameList(int sub_module_hid) {
		String whr = "";
		if(sub_module_hid > 0) {
			whr = " WHERE id=:id ";
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" FROM TB_LDAP_SUBMODULE_MASTER " + whr);
		if(sub_module_hid > 0) {
			q.setParameter("id", sub_module_hid);
		}
		List<TB_LDAP_SUBMODULE_MASTER> list = (List<TB_LDAP_SUBMODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	//@RequestMapping(value = "/getScreenNamebyCodeList")
	public @ResponseBody List<TB_LDAP_SCREEN_MASTER> getScreenNamebyCodeList(int screen_name_hid) {
		String whr = "";
		if(screen_name_hid > 0) {
			whr = " WHERE id=:id ";
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" FROM TB_LDAP_SCREEN_MASTER "+ whr);
		if(screen_name_hid > 0) {
			q.setParameter("id", screen_name_hid);
		}
		List<TB_LDAP_SCREEN_MASTER> list = (List<TB_LDAP_SCREEN_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/admin/getmodule_help", method = RequestMethod.POST)
	public ModelAndView getDownloadImageWetPetAmdt(@ModelAttribute("id1") int id,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("pageUrl") String pageUrl,
			@ModelAttribute("contraint") String contraint, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = help.getmodule_help(id).get(0).getscreen_shot();
		File file = null;

		try {
			file = new File(EXTERNAL_FILE_PATH);
			if (!file.exists()) {
				model.put("msg", "Sorry.The file you are looking for does not exist");
				if (contraint.equals("Edit")) {
					model.put("viewticketCMD", help.getUploadScreenShotByid(id));
				}
				if (contraint.equals("")) {
					model.put("userticketCMD", help.getUploadScreenShotByid(id));
				}
				return new ModelAndView(url);
			}
		} catch (Exception exception) {
			
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			if (contraint.equals("Edit")) {
				model.put("viewticketCMD", help.getUploadScreenShotByid(id));
			}
			model.put("msg", "Download Successfully");
			return new ModelAndView(url);
		} catch (FileNotFoundException e) {
			
		}
		if (contraint.equals("Edit")) {
			model.put("viewticketCMD", help.getUploadScreenShotByid(id));
		}
		return new ModelAndView(url);
	}

	public @ResponseBody List<Map<String, Object>> getDropDownHelp(HttpSession sessionUserId, List<Object[]> list)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		List<Map<String, Object>> FinalList = new ArrayList<Map<String, Object>>();

		for (Object[] listObject : list) {
			String columnName = (String) listObject[1];
			String columnCode = (String) listObject[0];

			byte[] enccolumnName = c.doFinal(columnName.getBytes());
			String base64EncodedEncryptedcolumnName = new String(Base64.encodeBase64(enccolumnName));

			byte[] enccolumnCode = c.doFinal(columnCode.getBytes());
			String base64EncodedEncryptedcolumnCode = new String(Base64.encodeBase64(enccolumnCode));

			Map<String, Object> EncList = new LinkedHashMap<String, Object>();
			EncList.put("columnName", base64EncodedEncryptedcolumnName);
			EncList.put("columnCode", base64EncodedEncryptedcolumnCode);
			FinalList.add(EncList);
		}
		Map<String, Object> EncKeyList = new LinkedHashMap<String, Object>();
		String a1 = enckey + "4bsjyg==";
		if (list.size() != 0) {
			EncKeyList.put("columnName1", a1);
			EncKeyList.put("columnCode1", "gDKfjjU+/PZ6k4WWTJB1IA==");
		}
		FinalList.add(EncKeyList);
		return FinalList;
	}

	/* getUserReportList_dashbored */
	@RequestMapping(value = "/getUserReportList_dashbored",method=RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportList1(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		String qry = "";
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		qry += " and l.army_no is not null and l.army_no != '' ";
		DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasUserreport(criterias, qry, roleSubAccess);
		return DatatablesResponse.build(dataSet, criterias);
	}
	
	public List<String> GetHelpTopic(HttpSession sessionA) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='HELPTOPIC' ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	public List<String> GetHelpTopicold() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='HELPTOPIC'  and codevalue in('1','2','3','4','5') ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	
	@RequestMapping(value = "/GetHelpTopicT", method = RequestMethod.POST)
	public @ResponseBody List<T_Domain_Value> GetHelpTopicT() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q = session.createQuery(
				"from T_Domain_Value where domainid='HELPTOPIC'  and codevalue not in('1','2','3','4','5')");
		@SuppressWarnings("unchecked")
		List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
		
		tx.commit();
		session.close();
		return list;
	}
	
	//----- aviation added by Mitesh
	
		@RequestMapping(value = "/GetHelpTopicAVN", method = RequestMethod.POST)
		public @ResponseBody List<T_Domain_Value> GetHelpTopicAVN() {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createQuery(
					"from T_Domain_Value where domainid='AVNHELPTOPIC' ORDER BY ID ASC");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			
			tx.commit();
			session.close();
			return list;
		}
		
		//---------- Animal(dog) added by Mitesh
		
				@RequestMapping(value = "/GetHelpTopicARMDOG", method = RequestMethod.POST)
				public @ResponseBody List<T_Domain_Value> GetHelpTopicARMDOG() {
					
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();

					Query q = session.createQuery(
							"from T_Domain_Value where domainid='ARMDOGHELPTOPIC' ORDER BY ID ASC");
					@SuppressWarnings("unchecked")
					List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
					
					tx.commit();
					session.close();
					return list;
				}
	
}