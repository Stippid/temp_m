package com.controller.cue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_MasterDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.cue_wet_pet;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class search_wet_pet_fetController {

	@Autowired
	Cue_MasterDAO masterDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;

	private cueContoller M = new cueContoller();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/upload_WET_PET_SEARCH", method = RequestMethod.GET)
	public ModelAndView upload_WET_PET_SEARCH(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_WET_PET_SEARCH", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
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
		return new ModelAndView("search_WET_PETTiles");
	}

	@RequestMapping(value = "/admin/upload_WET_PET_SEARCH1", method = RequestMethod.POST)
	public ModelAndView upload_WET_PET_SEARCH1(ModelMap Mmap, HttpSession session,
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
		
		List<Map<String, Object>> list = masterDAO.getAttributeFromCategory1(wet_pet, wet_pet_no, sponsor_dire, arm,doc_type, status, roleType,from_date,to_date,roleAccess);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		   
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WET_PETTiles");
	}

	@RequestMapping(value = "/admin/upload_WET_PET1", method = RequestMethod.POST)
	public ModelAndView upload_WET_PET1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet01", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "unit_type1", required = false) String unit_type,
			@RequestParam(value = "valid_from1", required = false) String valid_from,
			@RequestParam(value = "valid_till1", required = false) String valid_till,
			@RequestParam(value = "arm1", required = false) String arm,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "remarks1", required = false) String remarks) {
		  String roleType = session.getAttribute("roleType").toString();
		  String roleAccess = session.getAttribute("roleAccess").toString();
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);

		List<Map<String, Object>> list = masterDAO.upload_WET_PET1(status, wet_pet, wet_pet_no, sponsor_dire, arm,
				doc_type, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("listsize", list.size());
		Mmap.put("roleType", roleType);
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
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WET_PETTiles");
	}

	@RequestMapping(value = "/admin/Approved_WET_PET_Url", method = RequestMethod.POST)
	public ModelAndView Approved_WET_PET_Url(@ModelAttribute("appid") int appid, HttpSession session, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "wet_pet02", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no2", required = false) String wet_pet_no,
			@RequestParam(value = "table_title2", required = false) String table_title,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "sponsor_dire2", required = false) String sponsor_dire,
			@RequestParam(value = "arm2", required = false) String arm,
			@RequestParam(value = "doc_type2", required = false) String doc_type,
			@RequestParam(value = "from_date2", required = false) String from_date,
			@RequestParam(value = "to_date2", required = false) String to_date) {
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String username = session.getAttribute("username").toString();
		Mmap.put("msg", masterDAO.setApprovedDocument(appid,username));
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);

		List<Map<String, Object>> list = masterDAO.getAttributeFromCategory1(wet_pet, wet_pet_no, sponsor_dire, arm,doc_type, status, roleType,from_date,to_date,roleAccess);

		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WET_PETTiles");
	}

	@RequestMapping(value = "/admin/reject_WET_PET_Url", method = RequestMethod.POST)
	public ModelAndView reject_WET_PET_Url(@ModelAttribute("rejectid") int rejectid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "wet_pet03", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no3", required = false) String wet_pet_no,
			@RequestParam(value = "table_title3", required = false) String table_title,
			@RequestParam(value = "status3", required = false) String status,
			@RequestParam(value = "sponsor_dire3", required = false) String sponsor_dire,
			@RequestParam(value = "arm3", required = false) String arm,
			@RequestParam(value = "doc_type3", required = false) String doc_type,
			@RequestParam(value = "from_date3", required = false) String from_date,
			@RequestParam(value = "to_date3", required = false) String to_date) {
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String username = session.getAttribute("username").toString();
		Mmap.put("msg", masterDAO.setRejectDocument(rejectid, username));
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		

		List<Map<String, Object>> list = masterDAO.getAttributeFromCategory1(wet_pet, wet_pet_no, sponsor_dire, arm,doc_type, status, roleType,from_date,to_date,roleAccess);

		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("search_WET_PETTiles");
	}

	@RequestMapping(value = "/delete_WET_PET_UrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> delete_WET_PET_UrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(masterDAO.setDeleteDocument(deleteid));
		return list2;
	}

	@RequestMapping(value = "/admin/update_WET_PET_Url")
	public ModelAndView update_WET_PET_Url(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (roleType.equals("ALL") &  roleType.equals("HeadQuarter") &  !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}

		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Line_dte")){	
			
			model.put("getArmNameList",M.getArmNameLine(roleSusNo));
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			model.put("selectLine_dte",select);
			model.put("selectArmNameList",select);
			model.put("getArmNameList", M.getArmNameList());
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		model.put("WetPetEditCMDUpload", masterDAO.getcue_wet_petByid(updateid));
		model.put("msg", msg);
//		model.put("getArmNameList", M.getArmNameList());
//		model.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("create_wet_pet_docEditTiles");
	}

	@RequestMapping(value = "/WetPetEditActionUpload", method = RequestMethod.POST)
	public ModelAndView WetPetEditActionUpload(@ModelAttribute("WetPetEditCMDUpload") cue_wet_pet updateid,
			@RequestParam(value = "file_wet", required = false) MultipartFile file_wet, HttpServletRequest request,
			ModelMap model, HttpSession session, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (roleType.equals("ALL") & roleType.equals("HeadQuarter") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String answer = request.getParameter("answer1");
		String fname = "";

		if (answer == null) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please select New Document");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (answer.equals("No")) {
			if (updateid.getSuperseeded_wet_pet().equals("0")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Select Superseeded Document No");
				return new ModelAndView("redirect:update_WET_PET_Url");
			}

			if (validation.checkWetPetLength(updateid.getSuperseeded_wet_pet()) == false) {
				model.put("updateid", updateid.getId());
				model.put("msg", validation.supWetpetnoMSG);
				return new ModelAndView("redirect:update_WET_PET_Url");
			}
		}

		if (updateid.getValid_from().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Effective From Date");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (validation.checkDateLength(updateid.getValid_from()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Enter Valid Length Of Effective From Date");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (updateid.getValid_till().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Effective To Date");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (validation.checkDateLength(updateid.getValid_till()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.dateMSG);
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (updateid.getDoc_type().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Security Classification");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (validation.checkModificationLength(updateid.getDoc_type()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.secclassMSG);
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (updateid.getArm().equals("0")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Arm");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		String arm_code = String.format("%04d", Integer.parseInt(updateid.getArm()));
		if (validation.checkArmCodeLength(arm_code) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.arm_codeMSG);
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (updateid.getSponsor_dire().equals("0")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Sponsor Directorate");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (validation.checkSponsorDireLength(updateid.getSponsor_dire()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.spodireMSG);
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (updateid.getDoc_path() == "null") {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Enter Document Path");
			return new ModelAndView("redirect:update_WET_PET_Url");
		}
		

		if (validation.checkWepeLength(updateid.getUnit_type()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.docTitleMSG);
			return new ModelAndView("redirect:update_WET_PET_Url");
		}

		if (!file_wet.isEmpty()) {
			String doc_path1Ext = FilenameUtils.getExtension(file_wet.getOriginalFilename());
			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (file_wet.getSize() > fileSizeLimit) {
				model.put("updateid", updateid.getId());
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:update_WET_PET_Url");
			}

			if (!doc_path1Ext.equals("pdf")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:update_WET_PET_Url");
			}
			try {
				// code modify by Paresh on 05/05/20202
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				byte[] bytes = file_wet.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					String cueFilePath = session.getAttribute("cueFilePath").toString();
					File dir = new File(cueFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userid + "_CUEDOC.PDF";
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					updateid.setDoc_path(fname);
				}else {
					model.put("updateid", updateid.getId());
					model.put("msg", "Only *.pdf file extension allowed");
					return new ModelAndView("redirect:update_WET_PET_Url");
				}
			} catch (Exception e) {
			}
		} else {
			List<cue_wet_pet> newamd = masterDAO.getWET_PET_FET_DOCid(updateid.getId());
			String oldamdtdocu = newamd.get(0).getDoc_path();
			updateid.setDoc_path(oldamdtdocu);
		}

		masterDAO.UpdateDocAttValue(updateid, username, modifydate);
		model.put("msg", "Updated Successfully");
		return new ModelAndView("redirect:upload_WET_PET");
	}

	@RequestMapping(value = "/admin/getDownloadImagewetpet", method = RequestMethod.POST)
	public ModelAndView getDownloadImagewetpet(@ModelAttribute("id1") int id, @ModelAttribute("pageUrl") String pageUrl,
			@ModelAttribute("contraint") String contraint, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = masterDAO.getWET_PET_FET_DOCid(id).get(0).getDoc_path();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				model.put("msg", "Sorry. The file you are looking for does not exist");
				if (contraint.equals("Edit")) {
					model.put("getArmNameList", M.getArmNameList());
					model.put("getsponserListCue", M.getsponserListCue());
					model.put("WetPetEditCMDUpload", masterDAO.getcue_wet_petByid(id));
				}
				return new ModelAndView(url);
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

			if (contraint.equals("Edit")) {
				model.put("getArmNameList", M.getArmNameList());
				model.put("getsponserListCue", M.getsponserListCue());
				model.put("WetPetEditCMDUpload", masterDAO.getcue_wet_petByid(id));
			}
			model.put("msg", "Download Successfully");
			return new ModelAndView(url);
		} catch (FileNotFoundException e) {

			
		}

		if (contraint.equals("Edit")) {
			model.put("WetPetEditCMD", masterDAO.getcue_wet_petByid(id));
		}
		return new ModelAndView(url);
	}

	@RequestMapping(value = "/admin/getDownloadImageWetPetUpload", method = RequestMethod.POST)
	public ModelAndView getDownloadImageWetPetUpload(@ModelAttribute("id1") int id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = masterDAO.getWET_PET_FET_DOCid(id).get(0).getDoc_path();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView(
						"redirect:upload_WET_PET?msg=Sorry. The file you are looking for does not exist");
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
			return new ModelAndView("redirect:upload_WET_PET?msg=Download Successfully");
		} catch (FileNotFoundException e) {
		
		}
		return new ModelAndView("redirect:upload_WET_PET?msg=Download Successfully");
	}

}
