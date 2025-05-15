package com.controller.fpmis;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FileUploadController {

	@RequestMapping(value = "/admin/uploader", method = RequestMethod.GET)
	public ModelAndView rs_train_req(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String username = session.getAttribute("username").toString();
		if (username.equalsIgnoreCase("admin2") || username.equalsIgnoreCase("admin") ) {
			return new ModelAndView("fileuploaderTile");
		} else
			return new ModelAndView("AccessTiles");
	}

	@RequestMapping(value = "/admin/uploader", method = RequestMethod.POST)
	public ModelAndView uploader(@RequestParam(value = "file_up", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model, HttpSession session) throws IOException, DecoderException {
		String username = session.getAttribute("username").toString();
		if (username.equalsIgnoreCase("admin2") || username.equalsIgnoreCase("admin")) {

			String filePath = "/srv" + File.separator + "CUSTOM_UPLOAD";

			byte[] bytes = file.getBytes();

			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = file.getOriginalFilename();
			System.out.println("filename " + filename);
			/*
			 * String extension = ""; int i = filename.lastIndexOf('.'); if (i >= 0) {
			 * extension = filename.substring(i + 1); }
			 */
			filename = dir.getAbsolutePath() + File.separator + this.currentDateWithTimeStampString() + "_" + filename;
			File serverFile = new File(filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			return new ModelAndView("redirect:uploader");
		} else
			return new ModelAndView("AccessTiles");
	}

	public String currentDateWithTimeStampString() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
	}
}
