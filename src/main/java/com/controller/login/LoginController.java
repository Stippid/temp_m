package com.controller.login;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.models.login.TB_MISO_WHATS_NEW;
import com.persistance.util.HibernateUtil;

@Controller
public class LoginController {

	@Autowired
	RoleBaseMenuDAO roleBaseDAO;

	
	 private DataSource dataSource;
		
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
	
	@RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminHomePage");
		return model;
	}

	@RequestMapping(value = "/user/userDashboard")
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("userHomePage");
		return model;
	}
	@RequestMapping(value = {"/misoIAM" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView misoIAM(@RequestParam(value = "SAMLResponse", required = false) String SAMLResponse,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap Mmap){
		
		return new ModelAndView("redirect:/");
	} 
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		ModelAndView model = new ModelAndView();
		if(error != null){
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if(logout != null) {
			if (request.getHeader("Referer") != null) {
				model.addObject("msg", "You are logged out successfully.");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Role = "";
		if (!authentication.getName().equals("anonymousUser")){
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String role1 = "";
			for (String role : roles){
				role1 = role;
			}
			Role = role1;
		}
		 if (!Role.equals("")){
			 System.err.println("hre not in samaa "+Role);
			return new ModelAndView("redirect:/admin/commonDashboard");
		} 
		 if(Role.equals("sama")) {
			 System.err.println("hre in samaa "+Role);
				return new ModelAndView("redirect:/admin/samaDashboard");
			}
		
		else {
			String layout = "";
			List<String> msgLayout = roleBaseDAO.getLayoutlist();			
			layout += "<h3>";
			for (int m = 0; m < msgLayout.size(); m++) {
				if (m == 0) {
					layout += msgLayout.get(m);
				} else {
					layout += " | " + msgLayout.get(m);
				}
			}
			layout += "</h3>";
			model.addObject("layout", layout);
			model.addObject("yearlyLogin", roleBaseDAO.getYearlyLogin());
			model.addObject("server",getServerIP());
			model.addObject("todayslogin",roleBaseDAO.getTodaysLogin());
			model.addObject("todayshit",roleBaseDAO.dailyVisitorCounter());
			model.addObject("montlyLogin",roleBaseDAO.getMonthlyLogin());
			model.addObject("whatsnew",generateWhatsNew());
			model.setViewName("login");
		}
		return model;
	}
	
	public String generateWhatsNew() {
		List<TB_MISO_WHATS_NEW> l = getTB_MISO_WHATS_NEWList();
		String whatsnew = "";
		for(int i = 0;i<l.size();i++) {
			whatsnew += "<li style='color:yellow;' class=\"tooltip\" ><a >"+l.get(i).getHeading()+"</a>\r\n"+
					"	<span class=\"tooltiptextaa\">"+l.get(i).getDescription();
			if(l.get(i).getFile_upload() != null){
				whatsnew +=	"<a class=\"tooltiplinkaa\" href=\"#\"><img alt=\"download\" src=\"login_file/download.png\" onclick=\"downloadDoc("+l.get(i).getId()+")\" title=\"Download File\"></a>";
			}
			whatsnew += "</span></li>";
		}
		return whatsnew;
	}	
	
	@RequestMapping(value = "/generateWhatsNew", method = RequestMethod.POST)
	public @ResponseBody String generateNWhatsNew() {
		List<TB_MISO_WHATS_NEW> l = getTB_MISO_WHATS_NEWList();
		String whatsnew = "";
		for(int i = 0;i<l.size();i++) {
			whatsnew += "<li style='color:yellow;' class=\"tooltip\" ><a >"+l.get(i).getHeading()+"</a>\r\n"+
					"	<span class=\"tooltiptext\">"+l.get(i).getDescription();
			if(l.get(i).getFile_upload() != null){
				whatsnew +=	"<a class=\"tooltiplink\" href=\"#\"><img alt=\"download\" src=\"login_file/download.png\" onclick=\"downloadDoc("+l.get(i).getId()+")\" title=\"Download File\"></a>";
			}
			whatsnew += "</span><span style='float:right;margin-right:10px;'>"+l.get(i).getCreated_dt();
			whatsnew += "</span></li>";
		}
		return whatsnew;
	}
	
	@SuppressWarnings("unchecked")
	public List<TB_MISO_WHATS_NEW> getTB_MISO_WHATS_NEWList(){
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String lastMonthDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH))+"-"+cal.get(Calendar.DATE);*/
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, -1);
		String lastMonthDate  = format.format(cal1.getTime());
		
		String qry = " from TB_MISO_WHATS_NEW where created_dt > '"+lastMonthDate+"' order by id desc ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			Query q = session.createQuery(qry);
			List<TB_MISO_WHATS_NEW> p = (List<TB_MISO_WHATS_NEW>) q.list();
			tx.commit();
			session.close();
			return p;
		}catch(Exception e){
			session.getTransaction().rollback();
			return null;
		}
	}

	
	// customize the error message
	public static String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof SessionAuthenticationException) {
			error = "User Already logged in";// exception.getMessage();
		}else if (exception instanceof DisabledException) {
			error = "User is disabled";
		}else {
			error = "Invalid username and password!";
		}
		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/user/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}

	// Create Capcha Code
	@RequestMapping(value = "/capchaCode", method = RequestMethod.POST)
	public @ResponseBody List<String> capchaCode(HttpServletRequest request) {
		List<String> capchaList = new ArrayList<String>();
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomCharacter());

		String capcha = "";
		for (int i = 0; i < capchaList.size(); i++) {
			capcha += capchaList.get(i);
		}
		HttpSession session = request.getSession();
		session.setAttribute("capcha", capcha);
		return capchaList;
	}

	public static String getRandomInteger(int maximum, int minimum) {
		return String.valueOf(((int) (Math.random() * (maximum - minimum))) + minimum);
	}

	public static String getRandomCharacter() {
		String AlphaNumericString = "ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnopqrstuvxyz";
		int index = (int) (AlphaNumericString.length() * Math.random());
		return String.valueOf(AlphaNumericString.charAt(index));
	}
	
	@RequestMapping(value = "/JnlpDashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		Mmap.put("msg", msg);
		return new ModelAndView("JnlpDashboardTiles");
	}
	
	public String getServerIP() {
		try(final  DatagramSocket s = new DatagramSocket()){
			try {
				s.connect(InetAddress.getByName("8.8.8.8"),10002);
				String hadd = s.getLocalAddress().getHostAddress();
				if(hadd.equals("152.1.13.51")) {
					return "1 |";
				}else if(hadd.equals("152.1.13.52")) {
					return "2 |";
				}else if(hadd.equals("152.1.13.53")) {
					return "3 |";
				}else {
					return "Staging Envt |";
				}
			} catch (UnknownHostException e) {
				return "Unknown Server |";
			}
		} catch (SocketException e1) {
			return "Unknown Server |";
		}
	}
	
	
	// leaderboard 
	@RequestMapping(value = "/get_comnd_wise_count_list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_comnd_wise_count_list(HttpSession sessionUserId	) throws SQLException {
		return roleBaseDAO.get_comnd_wise_count_list(sessionUserId);
	}

	
}
	
