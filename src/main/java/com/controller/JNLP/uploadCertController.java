package com.controller.JNLP;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.x500.X500Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
  
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dao.login.UserLoginDAO;
import com.dao.login.UserLoginDAOImpl;
import com.models.UserLogin;
import com.models.Helpdesk.HD_TB_BISAG_USER_LOGIN_COUNT_INFO;
import com.models.login.Tb_Miso_OCSP_Verify_Users;
//import com.sun.el.parser.ParseException;
import com.persistance.util.HibernateUtil;

@Controller
public class uploadCertController {
	
	@RequestMapping(value = "/checkTest",method = RequestMethod.POST)
	public @ResponseBody String checkTest(HttpSession session,HttpServletRequest request) throws CertificateException, IOException {
		String username = session.getAttribute("username").toString();
		setSessionDetails(username,request);
		request.getSession().setAttribute("successValue", "MISO");
		
		//Save OCSP Verify Details
		int SessionUserId = Integer.parseInt(session.getAttribute("userId_for_jnlp").toString());
		String SessionArmy_no = session.getAttribute("army_no").toString();
		String SessionRole = session.getAttribute("role").toString();
		saveOCSPVerifyUser(SessionUserId,SessionArmy_no,SessionRole,"OCSP By PASS");
		return "Success";
	}
	
	@RequestMapping(value = "/uploadDoc",method = RequestMethod.POST)
	public @ResponseBody String saveUploadDocs(@RequestParam(value = "doc", required = true) MultipartFile doc,HttpSession session,HttpServletRequest request) throws CertificateException, IOException {
		int SessionUserId = Integer.parseInt(session.getAttribute("userId_for_jnlp").toString());
		String SessionRole = session.getAttribute("role").toString();
		String SessionArmy_no = session.getAttribute("army_no").toString();
		String ksp = "123Bisag#";
		
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		byte[] bytes = null;
		X509Certificate cert = null;
		try {
			if(doc != null) {
				
				bytes = Base64.decodeBase64(doc.getBytes());
				InputStream in = new ByteArrayInputStream(bytes);
				cert = (X509Certificate)certFactory.generateCertificate(in);
			}else {
				request.getSession().setAttribute("successValue", "Fail");
			    return "Certificate not found";
			}
		}catch (java.security.cert.CertificateException e) {
		    request.getSession().setAttribute("successValue", "Fail");
		    //Save Invalid Certificate Format Details
			saveOCSPVerifyUser(SessionUserId,SessionArmy_no,SessionRole,"Invalid Certificate Format");
			//Save Invalid Certificate Format Details
		    return "Invalid Certificate Format";
		}
		
		if(cert != null) {
			try {
				String army_no = "";
				
				X500Principal subject = cert.getSubjectX500Principal();
				
				if (subject.toString().contains("SERIALNUMBER")) {
					//checking ocsp only if the certificate contains a serial number, i.e. it is a user certificate
					String[] details = subject.toString().split(",");
					String army_name=details[2].replace("T=","")+" "+details[0].replace("CN=","");
					for (int k = 0; k < details.length; k++) {
						if (details[k].contains("SERIALNUMBER")) {
							
							String[] ic = details[k].toString().trim().split("=");
							army_no = ic[1];
						}
					}
					String flag = "";
					if(SessionArmy_no.trim().equals(army_no.trim())){
						java.security.Security.setProperty("ocsp.enable", "true");
						KeyStore keystore = KeyStore.getInstance("JKS");
					    //InputStream is = new FileInputStream(System.getProperty("java.home") + "/lib/security/" + "cacerts");
						InputStream is = new FileInputStream("/opt/tomcat/cacerts");
					    keystore.load(is, ksp.toCharArray());

					    PKIXParameters params = new PKIXParameters(keystore);
						params.setRevocationEnabled(true);
						CertPathValidator certValidator = CertPathValidator.getInstance(CertPathValidator.getDefaultType()); //PKIX
						ArrayList<X509Certificate> start = new ArrayList<>();
						start.add(cert);
						CertPath certPath = certFactory.generateCertPath(start);
						try{
							
							certValidator.validate(certPath, params);
							
							flag = "1";
							//Set Session Details
							String username = session.getAttribute("username").toString();
							setSessionDetails(username,request);
							
							saveOCSPVerifyUser(SessionUserId,SessionArmy_no,SessionRole,"OCSP Verified");
							//Save OCSP Verify Details
							
						}catch(Exception e){
							
							flag = "0";
							//Save OCSP error Details
							saveOCSPVerifyUser(SessionUserId,SessionArmy_no,SessionRole,"OCSP Error");
							//Save OCSP error Details
						}
					}else {
						request.getSession().setAttribute("successValue", "Fail");
						return "Token Army No Not Match";
					}
					if(flag.equals("1")) {
						
						request.getSession().setAttribute("successValue", "MISO");
						return "Success";
					}else {
						request.getSession().setAttribute("successValue", "Fail");
						return "An error has occurred";//"failure1";
					}
				}else {
					// serial no not available in certificate
					request.getSession().setAttribute("successValue", "Fail");
					return "Serial No not Available in Certificate";//"failure2";
				}
				//Check complete
			}
			catch (FileNotFoundException e) {
				request.getSession().setAttribute("successValue", "Fail");
				return "Certificate KeyStore Not Found";//"failure3";
			}
			catch (Exception e) {
				request.getSession().setAttribute("successValue", "Fail");
				return "An Error occured while verifying the Certificate";//"failure3";
				
			}
		} else {
			request.getSession().setAttribute("successValue", "Fail");
			return "Certificate is null";//"failure3";
		}
	}
	
	//Save OCSP Verify User List
	public void saveOCSPVerifyUser(int userId,String army_no,String role,String ocsp_status) {
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		Tb_Miso_OCSP_Verify_Users users = new Tb_Miso_OCSP_Verify_Users();
    		users.setArmy_no(army_no);
    		users.setDashboard_role(role.toUpperCase());
    		users.setUserid(userId);
    		users.setCreated_on(new Date());
    		users.setOcsp_status(ocsp_status);
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();	
    		sessionHQL.save(users);
    		tx.commit();
    	}catch (Exception e) {
			tx.rollback();
		}finally {
			sessionHQL.close();
		}
	}
	
	private UserLoginDAO userLoginDAO = new UserLoginDAOImpl();
	public boolean setSessionDetails(String username,HttpServletRequest request) {
		try {
			int userId = userLoginDAO.getUserId(username);
			UserLogin  addData = userLoginDAO.findByRoleId(userId);
			String sus_no_role = "";
			if(addData.getUser_sus_no() != null) {
				sus_no_role = addData.getUser_sus_no();
			}
			String arm_code = "";
			if( addData.getUser_Arm_code() != null) {
				arm_code = addData.getUser_Arm_code();
			}
			String formation_code = "";
			if(addData.getUser_formation_no() != null) {
				formation_code = addData.getUser_formation_no();
			}
			String login_name = "";
			if(addData.getLogin_name() != null) {
				login_name = addData.getLogin_name();
			}
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("username", addData.getUserName());
			request.getSession().setAttribute("army_no", addData.getArmy_no());
			
			request.getSession().setAttribute("roleSusNo", sus_no_role);
			request.getSession().setAttribute("roleArmCode", arm_code);
			request.getSession().setAttribute("roleFormationNo", formation_code);
			request.getSession().setAttribute("roleloginName", login_name);
			
			// User Login Status
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {
				HD_TB_BISAG_USER_LOGIN_COUNT_INFO N = new HD_TB_BISAG_USER_LOGIN_COUNT_INFO();
				N.setCreatedDate(new Date());
				N.setLoginstatus("Active");
				N.setStatus("1");
				N.setUserid(userId);
				session1.save(N);
				tx.commit();
			}catch (Exception e) {
				
				tx.rollback();
				return false;
			}finally {
				session1.close();
			}
			// User Login Status
			
			////
			String layout= "";
			List<String> msg = userLoginDAO.getLayoutlist();
			layout += "<h3>";
			for(int m=0;m<msg.size();m++){
				if(m==0) {
					layout += msg.get(m) ;
				}else {
					layout += " | " + msg.get(m) ;
				}
			}
			layout += "</h3>";
			request.getSession().setAttribute("layout", layout);
			///
			return true;
		}catch (Exception e) {
	
			return false;
		}
	}
	
	@RequestMapping(value = "/ocspCheckRegistrationDetails",method = RequestMethod.POST)
	public @ResponseBody String ocspCheckRegistrationDetails(@RequestParam(value = "doc", required = true) MultipartFile doc,String Army_no_field,
			HttpSession session,HttpServletRequest request) throws CertificateException, IOException {
		String ksp = "123Bisag#";
		String SessionArmy_no = Army_no_field;
		
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		byte[] bytes = null;
		X509Certificate cert = null;
		try {
			if(doc != null) {
				
				bytes = Base64.decodeBase64(doc.getBytes());
				InputStream in = new ByteArrayInputStream(bytes);
				cert = (X509Certificate)certFactory.generateCertificate(in);
			}else {
			    return "Certificate not found";
			}
		}catch (java.security.cert.CertificateException e) {
		    return "Invalid Certificate Format";
		}
		
		if(cert != null) {
			try {
				String army_no = "";
				
				X500Principal subject = cert.getSubjectX500Principal();
				
				if (subject.toString().contains("SERIALNUMBER")) {
					//checking ocsp only if the certificate contains a serial number, i.e. it is a user certificate
					String[] details = subject.toString().split(",");
					for (int k = 0; k < details.length; k++) {
						if (details[k].contains("SERIALNUMBER")) {
							
							String[] ic = details[k].toString().trim().split("=");
							army_no = ic[1];
						}
					}
					String flag = "";
					
					if(SessionArmy_no.trim().equals(army_no.trim())) {
					
						java.security.Security.setProperty("ocsp.enable", "true");
						KeyStore keystore = KeyStore.getInstance("JKS");
					    //InputStream is = new FileInputStream(System.getProperty("java.home") + "/lib/security/" + "cacerts"); // for bisag testing
						InputStream is = new FileInputStream("/opt/tomcat/cacerts"); // for ADN 
					    keystore.load(is, ksp.toCharArray());

					  
					    PKIXParameters params = new PKIXParameters(keystore);
						params.setRevocationEnabled(true);
						CertPathValidator certValidator = CertPathValidator.getInstance(CertPathValidator.getDefaultType()); // PKIX
						ArrayList<X509Certificate> start = new ArrayList<>();
						start.add(cert);
						CertPath certPath = certFactory.generateCertPath(start);
					
						try {
							
							certValidator.validate(certPath, params);
							
							flag = "1";
							
						} catch (Exception e) {
							
							flag = "0";
						}
					}else {
						request.getSession().setAttribute("successValue", "Fail");
						return "Token Army No Not Match";
					}
					if(flag.equals("1")) {
					
						return "Success";
					}else {
						return "An error has occurred";//"failure1";
					}
				}else {
					// serial no not available in certificate
					return "Serial No not Available in Certificate";//"failure2";
				}
				//Check complete
			}
			catch (FileNotFoundException e) {
				return "Certificate KeyStore Not Found";//"failure3";
			}
			catch (Exception e) {
				return "An Error occured while verifying the Certificate";//"failure3";
			}
		} else {
			return "Certificate is null";//"failure3";
		}
	}
}
