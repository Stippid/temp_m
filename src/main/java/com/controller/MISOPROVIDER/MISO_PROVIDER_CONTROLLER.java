package com.controller.MISOPROVIDER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

/*import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;*/
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

//import com.controller.MISOPROVIDER.TestSSLCertificateBypass.NoopHostnameVerifier;
import com.dao.MISO_PROVIDER_API.Miso_Provider_APIDAO;
//import com.iaap.admin.controller.TestSSLCertificateBypass.NoopHostnameVerifier;
@Controller
public class MISO_PROVIDER_CONTROLLER {
	@Autowired
	Miso_Provider_APIDAO miso_provideDAO;
	///Test API
	/*@RequestMapping(value = "/getManpowerOffrDtls",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getManpowerOffrDtls(HttpServletRequest request,String sus_no) {
		System.out.println("## call from IAAP Start getManpowerOffrDtls ## ");
		List<Map<String, Object>> list = miso_provideDAO.getManpowerOffrDtls(sus_no);
		return list;
	}
	@RequestMapping(value = "/getManpowerJCO_ORDtls",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getManpowerJCO_ORDtls(HttpServletRequest request,String sus_no) {
		System.out.println("## call from IAAP Start getManpowerJCO_ORDtls ## ");
		List<Map<String, Object>> list = miso_provideDAO.getManpowerJCO_ORDtls(sus_no);
		return list;
	}
	@RequestMapping(value = "/getVehiclesDtls",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getVehiclesDtls(HttpServletRequest request,String sus_no) {
		System.out.println("## call from IAAP Start getVehiclesDtls ## ");
		String ip = getClientIp(request);
		List<Map<String, Object>> list = miso_provideDAO.getVehiclesDtls(sus_no);
		return list;
	}
	@RequestMapping(value = "/getWeaponState",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWeaponState(HttpServletRequest request,String sus_no,String wpn_cat) {
		System.out.println("## call from IAAP Start getWeaponState ## ");
		List<Map<String, Object>> list = miso_provideDAO.getWeaponState(sus_no, wpn_cat);
		return list;
	}
	@RequestMapping(value = "/getWeaponCategory",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWeaponCategory(HttpServletRequest request,String sus_no) {
		System.out.println("## call from IAAP Start getWeaponCategory ## ");
		String ip = getClientIp(request);
		List<Map<String, Object>> list = miso_provideDAO.getWeaponCategory(sus_no);
		return list;
	}
	@RequestMapping(value = "/getOrbatUnitDetails",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getOrbatUnit(HttpServletRequest request,String last_created_date) {
		System.out.println("## call from IAAP Start getOrbatUnitDetails ## ");
		List<Map<String, Object>> list = miso_provideDAO.getOrbatUnitDtls(last_created_date);
		return list;
		}
		*/
	
	//////////////////New API Gateway/////////Added On 02/12/2024/////
	@RequestMapping(value = "/getManpowerOffrDtlsNew",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getManpowerOffrDtlsNew(HttpServletRequest request,@RequestParam String sus_no,
			@RequestHeader("Authorization") String authorizationHeader) {
		System.out.println("## call from IAAP Start getManpowerOffrDtlsNew ## " + sus_no);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		List<Map<String, Object>> list = null;
		try {
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.err.println("## Data ##"+data);
			String ip = getClientIp(request);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58") ) {
				list = miso_provideDAO.getManpowerOffrDtlsNew(sus_no);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;		
	}

	@RequestMapping(value = "/getManpowerJCO_ORDtlsNew",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getManpowerJCO_ORDtlsNew(HttpServletRequest request,@RequestParam String sus_no,
			@RequestHeader("Authorization") String authorizationHeader) {
		System.out.println("## call from IAAP Start getManpowerOffrDtlsNew ## " + sus_no);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		List<Map<String, Object>> list = null;
		try {
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.err.println("## Data ##"+data);
			String ip = getClientIp(request);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58")) {
				list = miso_provideDAO.getManpowerJCO_ORDtlsNew(sus_no);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;		
	}
	
	@RequestMapping(value = "/getVehiclesDtlsNew",method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>> getVehiclesDtlsNew(HttpServletRequest request,@RequestParam String sus_no,@RequestHeader("Authorization") String authorizationHeader) throws Exception {
		System.out.println("## call from IAAP Start getVehiclesDtlsNew ## " + sus_no);
		System.out.println("## call from IAAP Start  ## " + sus_no);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		
		List<Map<String, Object>> list =null;
		try {
			String ip = getClientIp(request);
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58")){
				list = miso_provideDAO.getVehiclesDtlsNew(sus_no);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getWeaponStateNew",method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>> getWeaponStateNew(HttpServletRequest request,@RequestParam String sus_no,@RequestParam String wpn_cat,@RequestHeader("Authorization") String authorizationHeader) {
		
		/*String bearerToken = extractBearerToken(authorizationHeader);
		String data = getTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
		List<Map<String, Object>> list =null;
		if(data.equalsIgnoreCase("false")) {
			list = miso_provideDAO.getWeaponStateNew(sus_no, wpn_cat);
		}
		return list;*/
		System.out.println("## call from IAAP Start getWeaponStateNew ## " + sus_no);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		List<Map<String, Object>> list = null;
		try {
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.err.println("## Data ##"+data);
			String ip = getClientIp(request);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58")) {
				list = miso_provideDAO.getWeaponStateNew(sus_no,wpn_cat);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;	
	}
	
	@RequestMapping(value = "/getWeaponCategoryNew",method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>> getWeaponCategoryNew(HttpServletRequest request,@RequestParam String sus_no,@RequestHeader("Authorization") String authorizationHeader) {
		/*String bearerToken = extractBearerToken(authorizationHeader);
		String data = getTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
		List<Map<String, Object>> list =null;
		if(data.equalsIgnoreCase("false")) {
			list = miso_provideDAO.getWeaponCategoryNew(sus_no);
		}
		return list;*/
		
		System.out.println("## call from IAAP Start getWeaponCategoryNew ## " + sus_no);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		List<Map<String, Object>> list = null;
		try {
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.err.println("## Data ##"+data);
			String ip = getClientIp(request);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58")) {
				list = miso_provideDAO.getWeaponCategoryNew(sus_no);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;
	}
	
	@RequestMapping(value = "/getOrbatUnitDetailsNew",method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>> getOrbatUnitNew(HttpServletRequest request,String last_created_date,@RequestHeader("Authorization") String authorizationHeader) {
		/*String bearerToken = extractBearerToken(authorizationHeader);
		String data = getTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
		List<Map<String, Object>> list = miso_provideDAO.getOrbatUnitDtlsNew(last_created_date);
		return list;*/
		System.out.println("## call from IAAP Start getOrbatUnitDetailsNew ## " + last_created_date);
		System.out.println("## AuthorizationHeader ## " + authorizationHeader);
		String bearerToken = extractBearerToken(authorizationHeader);
		System.out.println("## bearerToken ## " + bearerToken);
		List<Map<String, Object>> list = null;
		try {
			String data = validateTokenFromIAAP("https://iaapdgis.army.mil/API/ValidateToken", bearerToken);
			System.err.println("## Data ##"+data);
			String ip = getClientIp(request);
			System.out.println("## IP ##"+ ip);
			if (data.equalsIgnoreCase("false") || ip.trim().equals("131.1.19.58") || ip.trim().equals("152.1.26.58")) {
				list = miso_provideDAO.getOrbatUnitDtlsNew(last_created_date);
			}
		}catch (Exception e) {
			System.out.println("## isssue in validation ## "+e.getMessage());
			Map<String,Object> error=new HashMap<>();
			error.put("Error", e.getLocalizedMessage());
			return Arrays.asList(error);
		}
		return list;
	}
	
	public String validateTokenFromIAAP(String token_url,String username) {
		System.out.println("## token_url : "+ token_url +" :: "+ username);
		String token="";
		URL url;
		try {
			url = new URL (token_url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setDoOutput(true);
			String jsonInputString = "token="+username;
			try(OutputStream os = con.getOutputStream()){
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);			
			}
			int code = con.getResponseCode();
			System.out.println("## CODE : "+ code);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null){
					response.append(responseLine.trim());
				}
				System.out.println("response.toString() : "+ response.toString());
				token = response.toString();
				return token;
			}catch (Exception e) {
				System.out.println("CATCH INTERNAL : "+e.getMessage());
			}
			return token;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("CATCH INTERNAL : "+e.getMessage());
			e.printStackTrace();
			
			return token;
		}
	}
	
	private String extractBearerToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7); // 7 is the length of "Bearer "
		}
		return null;
	}
	/*public HttpComponentsClientHttpRequestFactory certificateBypass()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(
				sslContext,
				new String[] {"TLSv1.1","TLSv1.2","TLSv1.3"},
				null,
				new NoopHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		return requestFactory;
	}*/
  
	class NoopHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			return true;
		}

	}  
	/*public RestTemplate restTemplate() throws Exception {
	    // Load the trust store
		System.out.println("################################################################### ");
		  System.setProperty("javax.net.debug", "all");
	    File trustStoreFile = new File("/opt/iaappublickey/iaapkeystore.jks"); // Update this to your trust store path
	    char[] trustStorePassword = "123456".toCharArray(); // Replace with your trust store password
	
	    // Load the trust store
	    KeyStore trustStore = KeyStore.getInstance("JKS");
	    try (FileInputStream trustStoreStream = new FileInputStream(trustStoreFile)) {
	        trustStore.load(trustStoreStream, trustStorePassword);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	
	    // Create SSLContext using the trust store
	    SSLContext sslContext = SSLContexts.custom()
	            .loadTrustMaterial(trustStore, null) // You can specify a custom TrustManager if needed
	            .build();
	
	    // Specify the SSLConnectionFactory
	    SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(
	            sslContext, new String[]{"TLSv1.2"},
	            new String[] {"TLS_AES_256_GCM_SHA384"}, // Use default ciphers or specify as needed
	            new NoopHostnameVerifier() // Use a proper hostname verifier in production for security
	    );
	    // Create the HttpClient
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionFactory).build();
	    // Create the request factory
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	
	    return new RestTemplate(requestFactory);
	}*/
	
	private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
               /* System.out.println("## in remoteAddr : "+ remoteAddr);
                System.out.println("## in HOST : "+ request.getRemoteHost());
            	System.out.println("## in ADDR : "+ request.getRemoteAddr());
            	System.out.println("## in URI : "+ request.getRequestURI());
            	System.out.println("## in URL : "+ request.getRequestURL());
            	System.out.println("## in USER : "+ request.getRemoteUser());*/
            }
            /*System.out.println("## o HOST : "+ request.getRemoteHost());
        	System.out.println("## o ADDR : "+ request.getRemoteAddr());
        	System.out.println("## o URI : "+ request.getRequestURI());
        	System.out.println("## o URL : "+ request.getRequestURL());
        	System.out.println("## o USER : "+ request.getRemoteUser());*/
        }
        System.out.println("## remoteAddr : "+ remoteAddr);
        return remoteAddr;
    }
}