package com.controller.mkyong;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	public static void main(String[] args) {
		String password = "123Bisag#";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		
		
		if(!passwordEncoder.matches("123Bisag#", hashedPassword)){
			
		}else{
			
		}
		
		
	}
	
	/*public static List<Map<String, Object>> commandList(){
   		List<Map<String, Object>> commandlist = new ArrayList<Map<String, Object>>();
   		Map<String, Object> list = new LinkedHashMap<String, Object>();
   		list.put("form_code", "0");
   		list.put("short_form", "MOD");
   		list.put("full_name", "MIN OF DEFENCE");
        commandlist.add(list);
        
   		return commandlist;
   	}*/
}