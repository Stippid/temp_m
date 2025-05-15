package com.controller.adminPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class AdminController {	
	
	/*@RequestMapping(value = "/admin/Welcome", method = RequestMethod.GET)
	public ModelAndView Admin_Welcome(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		return new ModelAndView("redirect:commonDashboard");
	}*/
	
	/*@RequestMapping(value = "/runJarFile", method = RequestMethod.POST)
	public @ResponseBody String runJarFile() {
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("java -jar  d:\\miso_pki.jar");
			return "true";
		} catch (IOException e) {
			return "false";
		}
	}*/
}
