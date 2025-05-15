package com.controller.validate_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

//kajal
 
@Controller
public class ServerValidation_Controller {
	
	public boolean isValidated(String appointment_no, String army_no) throws IOException {
		//System.setProperty("https.protocols", "TLSv1.2");
		boolean validated =false;
		URL url = new URL ("https://iaapdgis.army.mil/IAAP/validate/redirect");
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		con.setRequestProperty("Accept", "application/json");
		//String pt =System.getProperty("https.protocols");
		
		con.setDoOutput(true);
		
		//JSON String need to be constructed for the specific resource. 
		//We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
		String jsonInputString = "appnox="+appointment_no+"&"+"army_nox="+army_no;
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}

		int code = con.getResponseCode();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			 validated =Boolean.parseBoolean(response.toString());
			if(validated) {
			}
		}
//		catch (Exception e) {
//			// TODO: handle exception
//			validated = false;
//		}
		return validated;
	}
	
	

	
   
 
}