package com.controller.JNLP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JnlpDownloadController {

	
	
	
	@RequestMapping(value = "/getDownloadJAR", method = RequestMethod.POST)
	public void getDownloadImagePSG(ModelMap model, HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "/srv" + File.separator + "Download_JNLP" + File.separator + "miso3.jar";
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (file == null || !file.exists() ) {
				//return new ModelAndView("redirect:JnlpDashboard?msg=Sorry. The file you are looking for does not exist");
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
		 } catch (FileNotFoundException e) {
			
		 }
		
	}
}
