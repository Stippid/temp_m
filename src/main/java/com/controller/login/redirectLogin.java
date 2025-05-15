package com.controller.login;

import java.io.File;

import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Date;

import java.util.List;

import java.util.Map;

import java.util.Set;



import javax.crypto.BadPaddingException;

import javax.crypto.IllegalBlockSizeException;

import javax.crypto.NoSuchPaddingException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import javax.sql.DataSource;



import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;



import com.dao.login.RoleBaseMenuDAO;

import com.dao.login.UserLoginDAO;

import com.dao.login.UserLoginDAOImpl;

import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import com.models.Role;

import com.models.TB_LDAP_MODULE_MASTER;

import com.models.TB_LDAP_SCREEN_ANALYTICS;

import com.models.TB_LDAP_SCREEN_MASTER;

import com.models.TB_LDAP_SUBMODULE_MASTER;

import com.models.UserLogin;

import com.sun.jna.platform.unix.X11.Screen;




public class redirectLogin extends SavedRequestAwareAuthenticationSuccessHandler{
	
	 
	
	@Autowired
	private UserLoginDAO userLoginDAO = new UserLoginDAOImpl();
	
	private DataSource dataSource;
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
		String capcha =  request.getSession().getAttribute("capcha").toString();
		if(!txtInput.equals(capcha)){
			response.sendRedirect("/login");
		}else{
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
			String role1 = null;
			for(String role:roles)
			{
				request.getSession().setAttribute("role", role);
				role1 = role;
			}
			int userId = userLoginDAO.getUserId(authentication.getName());
			Role roleList = userLoginDAO.findRole_url(role1);
			
			request.getSession().setAttribute("userId_for_jnlp", userId);
			request.getSession().setAttribute("username", authentication.getName());
			
			String RoleUrl =  "";
			if(roleList.getRole_url() != null) {
				RoleUrl =  roleList.getRole_url();
			}
			String RoleType = "";		
			if(roleList.getRole_type() != null) {
				RoleType =  roleList.getRole_type();		
			}
			String Acces_lvl = "";
			if(roleList.getAccess_lvl() != null) {
				Acces_lvl = roleList.getAccess_lvl();
			}
			String subAcces_lvl = "";
			if(roleList.getSub_access_lvl() != null) {
				subAcces_lvl = roleList.getSub_access_lvl();
			}
						
			String staff_lvl = "";
			if(roleList.getStaff_lvl() != null) {
				staff_lvl = roleList.getStaff_lvl();
			}
			
			request.getSession().setAttribute("roleUrl", RoleUrl);
			request.getSession().setAttribute("roleType", RoleType);
			request.getSession().setAttribute("roleAccess", Acces_lvl);		
			request.getSession().setAttribute("roleSubAccess", subAcces_lvl);
			request.getSession().setAttribute("roleStaff_lvl", staff_lvl);
			
			int roleid =  roleList.getRoleId(); 
			// New 
			
			//Comment This
			/*uploadCertController u = new uploadCertController();
			u.setSessionDetails(authentication.getName(),request);*/
			//Comment This			
			
			UserLogin  addData = userLoginDAO.findByRoleId(userId);
			request.getSession().setAttribute("army_no", addData.getArmy_no());
			if(roleid !=0) {
				request.getSession().setAttribute("roleid", roleid);
			}
			request.getSession().setAttribute("successValue", "Fail");
			String sus_no_role = "";
			if(addData.getUser_sus_no() != null) {
				sus_no_role = addData.getUser_sus_no();
			}
			request.getSession().setAttribute("roleSusNo", sus_no_role);
			// New
			////////////////////
			
			
			String ip = getClientIp(request);
			request.getSession().setAttribute("ip", ip);
			
			String userAgent = request.getHeader("User-Agent");
		    request.getSession().setAttribute("user_agentWithIp", userAgent+"_"+ip);
			request.getSession().setAttribute("user_agent", userAgent);
			
			//request.getSession().setAttribute("otpKey", "commonPwdEncKeys");
			request.getSession().setAttribute("KeySpec", "dc0da04af8fee58593442bf834b30739");
			
			final long fileSizeLimit = 30 * 1024 * 1024; 
			request.getSession().setAttribute("fileSizeLimit", fileSizeLimit);
			
			String curDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			request.getSession().setAttribute("curDate", curDate);
			
			SimpleDateFormat  simpleformat = new SimpleDateFormat("MMMM");
		    String curMonth= simpleformat.format(new Date());
		    request.getSession().setAttribute("curMonth", curMonth.toUpperCase());
		    
			request.getSession().setAttribute("regScript", "^[a-zA-Z0-9\\\\[\\\\] \\\\+ \\\\* \\\\-.,/ ~!@#$^&%_]+$");
			
			request.getSession().setAttribute("orbatFilePath", "/srv"+ File.separator + "Orbat");
			request.getSession().setAttribute("cueFilePath", "/srv"+ File.separator + "Cue");
			request.getSession().setAttribute("tmsFilePath", "/srv"+ File.separator + "TMS");
			request.getSession().setAttribute("psgFilePath", "/srv"+ File.separator + "PSG");
			request.getSession().setAttribute("mmsFilePath", "/srv"+ File.separator + "MMS");
			request.getSession().setAttribute("mnhFilePath", "/srv"+ File.separator + "MNH");
			request.getSession().setAttribute("helpdeskFilePath", "/srv"+ File.separator + "HELP");
			request.getSession().setAttribute("formationFilePath", "/srv"+ File.separator + "Upload_DocumentsFRM");
			request.getSession().setAttribute("handingTakingOverPath", "/srv"+ File.separator + "handingTakingOver");
			request.getSession().setAttribute("userresgrationPath", "/srv"+ File.separator + "user_regitraion");
			request.getSession().setAttribute("itAssetsFilePath", "/srv"+ File.separator + "IT");
			request.getSession().setAttribute("treeFilePath", "/srv"+ File.separator + "tree");
			request.getSession().setAttribute("avnFilePath", "/srv"+ File.separator + "AVN");
			request.getSession().setAttribute("animalFilePath", "/srv"+ File.separator + "animal");

			
			String[] medicalUnitPrifix = {"9609","9709","3742","6323","3755","3738","3735","3747","1234"};
			
			request.getSession().setAttribute("medicalUnitPrifix",medicalUnitPrifix);
			
			Boolean medical = false;
			if(Acces_lvl.equals("Unit")) {
				String sus_no = addData.getUser_sus_no().substring(0, 4);
				if(Arrays.asList(medicalUnitPrifix).contains(sus_no)) {
					medical = true;
				}
			}else if(Acces_lvl.equals("MISO") && subAcces_lvl.equals("Medical")) {
				medical = true;
			}else if(Acces_lvl.equals("DG") && subAcces_lvl.equals("Medical")) {
				medical = true;
			}else if(Acces_lvl.equals("Formation")) {
				medical = true;
			}
			
			
			List<TB_LDAP_MODULE_MASTER> module = userLoginDAO.getModulelist(roleid,medical);
			
			
			String menu = "";
			String nMgtScr="0";			
			request.getSession().setAttribute("nSPara", nMgtScr);	
			
			List<TB_LDAP_SCREEN_ANALYTICS> analytics = userLoginDAO.getAnalytics(roleid,userId);
			int analyticssize=analytics.size();
		
	
		if(analytics.size()>0) {
			
			
			
			if(!RoleUrl.equals("")) {		
					 menu="<li><a href='"+RoleUrl+"' class='btn btn-danger btn-sm'><i class='fa fa-dashboard'></i> Dashboard</a></li>";
			}
		//	if(Acces_lvl.equals("HeadQuarter") || Acces_lvl.equals("Line_dte")) {
		//	if(Acces_lvl.equals("HeadQuarter")) {
				
				
			//	List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlistHQ(roleid);
				//for(int scr=0;scr<screen.size();scr++){
					//menu += "<li class='dropdown-item'><i class='fa fa-arrow-circle-o-right'></i><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> "+screen.get(scr).getScreen_name()+"</a></li>";
				//}
			//}
		//	else { 
				
				for(int mod=0;mod<module.size() ;mod++) {
					
					
					menu += "<li class='nav-item dropdown dropdown-item' id='"+module.get(mod).getModule_name() +"_menu'>";	
						menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' onclick='getmodule(\""+module.get(mod).getModule_name()+"\")' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module.get(mod).getModule_name()+"</a>";
						menu += "<ul class='dropdown-menu' id='Dropdown_"+module.get(mod).getModule_name()+"' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
							List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
							for(int submod=0;submod<submodule.size();submod++){
							
								menu += "<li class='dropdown-item dropdown create_search ' >";
								menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
							
								menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
									
								int module1=module.get(mod).getId();
								
								int submodule1=submodule.get(submod).getId();
								
							
								for(int anymod=0;anymod<analytics.size();anymod++){
									int moduleidan=analytics.get(anymod).getModule_id();
								
									int submoduleidan=analytics.get(anymod).getSubmodule_id();
									
								if(module1!= moduleidan && submodule1 != submoduleidan) {
									
									List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid);
										
										for(int scr=0;scr<screen.size();scr++){
											menu += "<li id='Dropdown_scr"+screen.get(scr).getId()+"' class='dropdown-item click-count' data-screen-id='"+screen.get(scr).getId()+"'><i class='fa fa-arrow-circle-o-right'></i><a class='link' href='"+screen.get(scr).getScreen_url()
													+"' onclick='getpagelink("+screen.get(scr).getId()+")'"
															+ "> "+screen.get(scr).getScreen_name()+"</a></li>";
										
										}
										//anymod=analytics.size();
										break;
									
									}
									
								if(module1== moduleidan && submodule1 == submoduleidan) {
										
											int screenid1=analytics.get(anymod).getScreen_id();
											
											List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid,screenid1);
												for(int scr=0;scr<screen.size();scr++){

													menu += "<li id='Dropdown_scr"+screen.get(scr).getId()+"' class='dropdown-item click-count' data-screen-id='"+screen.get(scr).getId()+"'><i class='fa fa-arrow-circle-o-right'></i><a class='link' href='"+screen.get(scr).getScreen_url()
															+"' onclick='getpagelink("+screen.get(scr).getId()+")'> "
															+screen.get(scr).getScreen_name()+"</a></li>";
										
										}
											
									}
										
								}
							
								
									menu += "</ul>";        
										menu += "</li>";
							}	

						menu += "</ul>";
					menu += "</li>";
				}				
			}
		
	//	}
			
		else {
			
			List<TB_LDAP_MODULE_MASTER> module1 = userLoginDAO.getModulelistanalytics(roleid,medical);
			if(!RoleUrl.equals("")) {
				/* if(Acces_lvl.equals("Formation")) {
					 menu="<li><a href='#' onclick='getCheckPIKValidation();' class='btn btn-danger btn-sm'>Dashboard</a></li><br><li style='display:none;'><button id='b1' onclick='run();' class='btn btn-danger btn-sm' >Dashboard</button></li>";
				 }else {*/				
					 menu="<li><a href='"+RoleUrl+"' class='btn btn-danger btn-sm'><i class='fa fa-dashboard'></i> Dashboard</a></li>";
				 //}
			}
			
			
			/*if(Acces_lvl.equals("HeadQuarter")) {
				List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlistHQ(roleid);
				for(int scr=0;scr<screen.size();scr++){
					menu += "<li class='dropdown-item'><i class='fa fa-arrow-circle-o-right'></i><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> "+screen.get(scr).getScreen_name()+"</a></li>";
				}
			}
			else { 
				*/
				  
				for(int mod=0;mod<module1.size() ;mod++) {
					menu += "<li class='nav-item dropdown dropdown-item' id='"+module1.get(mod).getModule_name() +"_menu'>";	
						menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module1.get(mod).getModule_name()+"' onclick='getmodule(\""+module1.get(mod).getModule_name()+"\")' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module1.get(mod).getModule_name()+"</a>";
						menu += "<ul class='dropdown-menu' id='Dropdown_"+module1.get(mod).getModule_name()+"' aria-labelledby='Dropdown_"+module1.get(mod).getModule_name()+"' >";
							List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module1.get(mod).getId(),roleid);
							  
							for(int submod=0;submod<submodule.size();submod++){
								menu += "<li class='dropdown-item dropdown create_search ' >";
									menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
									menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
										List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module1.get(mod).getId(),submodule.get(submod).getId(),roleid);
										for(int scr=0;scr<screen.size();scr++){
											menu += "<li id='Dropdown_scr"+screen.get(scr).getId()+"' class='dropdown-item click-count' data-screen-id='"+screen.get(scr).getId()+"'><i class='fa fa-arrow-circle-o-right'></i><a class='link' href='"+screen.get(scr).getScreen_url()
													+"' onclick='getpagelink("+screen.get(scr).getId()+")'> "
													+screen.get(scr).getScreen_name()+"</a></li>";
										
										}
									menu += "</ul>";        
								menu += "</li>";
							}
						menu += "</ul>";
					menu += "</li>";
				}				
			}				
			
	//	}
		
			request.getSession().setAttribute("menu", menu);
			response.sendRedirect("../JnlpDashboard");
		}

	}	

	
	private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
	 }	

	
			@RequestMapping(value = "/updatescreenhit", method = RequestMethod.GET)

			public @ResponseBody String updatescreenhit(HttpServletRequest request, HttpSession sessionA

					) throws HibernateException, ParseException {

				

			//	int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

				

				return "y";



			}	

	}
