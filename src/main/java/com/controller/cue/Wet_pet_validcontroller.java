package com.controller.cue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.cue.Cue_wepe_validuptoDAO;
import com.dao.cue.Cue_wetpet_validuptoDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Wet_pet_validcontroller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	cueContoller M = new cueContoller();

	// @Autowired
	// private RoleBaseMenuDAO roledao ;
	// Cue_WE_PE_UploadDAO masterDAO = new Cue_WE_PE_UploadDAOImpl();

	@Autowired
	private Cue_wetpet_validuptoDAO wetpetamdDAO3;

	@RequestMapping(value = "/wet_pet_valid_report", method = RequestMethod.GET)
	public ModelAndView wet_pet_valid_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_WET_PET_SEARCH", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
		   String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
             if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		//Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WET_PET_validTiles");
	}

	@RequestMapping(value = "/admin/list_WET_PET_SEARCH1", method = RequestMethod.POST)
	public ModelAndView list_WET_PET_SEARCH1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet01", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "table_title1", required = false) String table_title,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date) { 
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
          if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		List<Map<String, Object>> list = wetpetamdDAO3.getAttributeFromCategorylist1(wet_pet, wet_pet_no, sponsor_dire, arm,doc_type, status, roleType,from_date,to_date,roleAccess);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		   
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WET_PET_validTiles");
	}

	@RequestMapping(value = "/admin/getDownloadImageWetPetlist", method = RequestMethod.POST)
	public ModelAndView getDownloadImageWetPetlist(@ModelAttribute("id1") int id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
//		EXTERNAL_FILE_PATH = wetpetamdDAO3.getWet_Pet_DownloadByid(id).get(0).getDoc_path();
		EXTERNAL_FILE_PATH = wetpetamdDAO3.getWET_PET_FET_listid(id).get(0).getDoc_path();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView(
						"redirect:wet_pet_valid_report?msg=Sorry. The file you are looking for does not exist");
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
			return new ModelAndView("redirect:wet_pet_valid_report?msg=Download Successfully");
		} catch (FileNotFoundException e) {

		}
		return new ModelAndView("redirect:wet_pet_valid_report?msg=Download Successfully");
	}

}
