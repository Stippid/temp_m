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

import com.dao.cue.Cue_WE_PE_UploadDAO;
import com.dao.cue.Cue_WE_PE_UploadDAOImpl;
import com.dao.cue.Cue_wepe_validuptoDAO;
import com.dao.login.RoleBaseMenuDAO;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class We_pe_validcontroller {

	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	cueContoller M = new cueContoller();
	
	//@Autowired
	//private RoleBaseMenuDAO roledao ;
	//Cue_WE_PE_UploadDAO masterDAO = new Cue_WE_PE_UploadDAOImpl();
	
	
	@Autowired
	private Cue_wepe_validuptoDAO wetpetamdDAO3;
	
	@RequestMapping(value = "/we_pe_valid_report", method = RequestMethod.GET)
	public ModelAndView we_pe_valid_report(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("we_pe_valid_report", roleid);	
		/*if(val == false) {
			return new ModelAndView("AccessTiles");
		}*/
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}	
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		
		
		Mmap.put("msg",msg);
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
		
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WE_PE_validTiles" );
	}
	

@RequestMapping(value = "/admin/we_pe_valid_report1", method = RequestMethod.POST)
	public ModelAndView we_pe_valid_report1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date
			){
			
		
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("we_pe01", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
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
					 
		List<Map<String, Object>> list =wetpetamdDAO3.getvaliduptowepefegsl(we_pe,we_pe_no,doc_type,arm,sponsor_dire,status,roleType,from_date,to_date, roleAccess);
			
		Mmap.put("list", list);
		
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_WE_PE_validTiles");
	}


	@RequestMapping(value = "/admin/getDownloadImageWePeUploadlist", method = RequestMethod.POST)
	public ModelAndView getDownloadImageWePeUploadlist(@ModelAttribute("id1") int id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = wetpetamdDAO3.getWe_Pe_DownloadByid(id).get(0).getDoc_path();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView("redirect:we_pe_valid_report?msg=Sorry. The file you are looking for does not exist");
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
			return new ModelAndView("redirect:we_pe_valid_report?msg=Download Successfully");
		} catch (FileNotFoundException e) {

		}
		return new ModelAndView("redirect:we_pe_valid_report?msg=Download Successfully");
}	 
	
	
	
}
