package com.controller.Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.login.RoleController;
import com.dao.helpDesk.HelpDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class HelpDashboardController {

	RoleController roleCnt = new RoleController();

	@Autowired
	HelpDAO helpDash;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private SessionRegistry sessionRegistry;

	public List<String> getUsersFromSessionRegistry() {
		String userString = sessionRegistry.getAllPrincipals().stream()
				.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString)
				.collect(Collectors.toList()).toString();

		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("Username\\:.*?\\;").matcher(userString);
		while (m.find()) {
			allMatches.add(m.group().replace("Username: ", "").replace(";", ""));
		}
		return allMatches;
	}

	@RequestMapping(value = "/TicketStatus", method = RequestMethod.GET)
	public ModelAndView TicketStatus(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("TicketStatusTiles");
	}

	@RequestMapping(value = "/admin/helpdesk", method = RequestMethod.GET)
	public ModelAndView helpdesk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getSupportRequest", helpDash.getSupportRequest());
		Mmap.put("getActiveUserList", helpDash.getActiveUserList());
		Mmap.put("getUserLoginCount", helpDash.getUserLoginCount());
		Mmap.put("getActiveUserCount", getUsersFromSessionRegistry().size());
		Mmap.put("getNewTicketCount", helpDash.getNewTicketCount());
		Mmap.put("getPendigTicketCount", helpDash.getPendigTicketCount());
		Mmap.put("getCompletedTicketCount", helpDash.getCompletedTicketCount());
		Mmap.put("getfeedbackrecCount", helpDash.getfeedbackrecCount());
		Mmap.put("getUserCount", helpDash.getUserCount());
		Mmap.put("getRoleCount", helpDash.getRoleCount());
		Mmap.put("getFeatureReqCount", helpDash.getFeatureReqCount());
		return new ModelAndView("HelpDashTiles");
	}

	/*@RequestMapping(value = "/admin/getAllCodeListJdbc2")
	public ModelAndView getAllCodeListJdbc2(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ticket1", required = false) String ticket,
			@RequestParam(value = "ticket_status1", required = false) String ticket_status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "help_topic1", required = false) String help_topic,
			@RequestParam(value = "module1", required = false) String module,HttpServletRequest request) {

		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("ticket1", ticket);
		Mmap.put("ticket_status1", ticket_status);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("help_topic1", help_topic);
		Mmap.put("module1", module);
		String userId = session.getAttribute("userId").toString();

		List<Map<String, Object>> list = helpDash.getAllCodeListJdbc(ticket, ticket_status, from_date, to_date,help_topic, userId, roleid, module, "");
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("TicketStatusTiles");
	}
*/
	@RequestMapping(value = "/UserCreated", method = RequestMethod.GET)
	public ModelAndView UserCreated(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		//Mmap.put("list", helpDash.getUserReport());
		return new ModelAndView("UserCreatedTiles");
	}

	@RequestMapping(value = "/RoleUrl", method = RequestMethod.GET)
	public ModelAndView RoleUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.RoleSearchReport();
			Mmap.put("list", list);
			Mmap.put("getRoleType", roleCnt.getRoleType());
			Mmap.put("msg", msg);
			return new ModelAndView("RoleUrlTiles");
		}
	}

	@RequestMapping(value = "/LoggedInUsers", method = RequestMethod.GET)
	public ModelAndView LoggedInUsers(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.loggedin_report(getUsersFromSessionRegistry());
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("LoggedInUsersTiles");
	}

	@RequestMapping(value = "/TicketStatusNew", method = RequestMethod.GET)
	public ModelAndView TicketStatusNew(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.ticketStatusNew();
		Mmap.put("list", list);
		Mmap.put("ticket_status", "New");
		return new ModelAndView("TicketStatusNewTiles");
	}

	@RequestMapping(value = "/TicketStatusInProgress", method = RequestMethod.GET)
	public ModelAndView TicketStatusInProgress(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.ticketStatusInProgress();
		Mmap.put("list", list);
		Mmap.put("ticket_status", "Pending");
		return new ModelAndView("TicketStatusInProgressTiles");
	}

	@RequestMapping(value = "/TicketStatusCompleted", method = RequestMethod.GET)
	public ModelAndView TicketStatusCompleted(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.ticketStatusCompleted();
		Mmap.put("list", list);
		Mmap.put("ticket_status", "Completed");
		return new ModelAndView("TicketStatusCompletedTiles");
	}

	@RequestMapping(value = "/HelpTopicFeedback", method = RequestMethod.GET)
	public ModelAndView HelpTopicFeedback(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.helpTopicFeedback();
		Mmap.put("list", list);
		Mmap.put("help_topic", "Feedback Received");
		return new ModelAndView("HelpTopicFeedbackTiles");
	}

	@RequestMapping(value = "/HelpTopicFeatureRequest", method = RequestMethod.GET)
	public ModelAndView HelpTopicFeatureRequest(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		List<Map<String, Object>> list = helpDash.helpTopicFeatureRequest();
		Mmap.put("list", list);
		Mmap.put("help_topic", "Feature Requests");
		return new ModelAndView("HelpTopicFeatureRequestTiles");
	}
}