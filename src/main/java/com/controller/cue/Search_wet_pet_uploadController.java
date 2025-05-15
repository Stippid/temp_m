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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
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
import com.dao.cue.Cue_Wet_Pet_Amdt_EditDAO;
import com.dao.cue.Cue_Wet_Pet_Amdt_EditDAOImpl;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UploadWetPetAamendmentToDocument;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Search_wet_pet_uploadController {

	@Autowired
	private Cue_wepe_conditionDAO wetpetamdDAO2;
	@Autowired
	private RoleBaseMenuDAO roledao;
	Cue_Wet_Pet_Amdt_EditDAO wetpetamdDAO = new Cue_Wet_Pet_Amdt_EditDAOImpl();
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/search_WET_PET_Amendment", method = RequestMethod.GET)
	public ModelAndView search_WET_PET_Amendment(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_WET_PET_Amendment", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_WET_PET_AmendmentTiles");
	}

	@RequestMapping(value = "/admin/search_WET_PET_Amendment1", method = RequestMethod.POST)
	public ModelAndView search_WET_PET_Amendment1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet01", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "table_title1", required = false) String table_title,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("table_title1", table_title);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		List<Map<String, Object>> list = wetpetamdDAO2.getAttributeFromCategorySearchWetPet(wet_pet, wet_pet_no, status,roleType,from_date,to_date,roleAccess,roleSusNo);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_WET_PET_AmendmentTiles");
	}

	@RequestMapping(value = "/updaterejectactionWetPetAmdt", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectactionWetPetAmdt(String remarks, String letter_no, int id) {
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wet_pet_amdt", remarks, id,
				letter_no);
		return list2;
	}

	@RequestMapping(value = "/Approved_WET_PET_amdt_Url", method = RequestMethod.POST)
	public ModelAndView Approved_WET_PET_amdt_Url(@ModelAttribute("appid") int appid, HttpSession session,
			ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "wet_pet02", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no2", required = false) String wet_pet_no,
			@RequestParam(value = "table_title2", required = false) String table_title,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "from_date2", required = false) String from_date,
			@RequestParam(value = "to_date2", required = false) String to_date) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Mmap.put("msg", wetpetamdDAO.setApprovedWetPetAdmtEdit(appid));
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("table_title1", table_title);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		List<Map<String, Object>> list = wetpetamdDAO2.getAttributeFromCategorySearchWetPet(wet_pet, wet_pet_no, status,roleType,from_date,to_date,roleAccess,roleSusNo);
		Mmap.put("list", list);

		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_WET_PET_AmendmentTiles");
	}

	@RequestMapping(value = "/delete_WET_PET_amdt_UrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> delete_WET_PET_amdt_UrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(wetpetamdDAO.setDeleteWetPetAdmtEdit(deleteid));
		return list2;
	}

	@RequestMapping(value = "/update_WET_PET_amdt_Url", method = RequestMethod.POST)
	public ModelAndView update_WET_PET_amdt_Url(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (roleType.equals("HeadQuarter") & roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("WetPetEditCMD", wetpetamdDAO.getUploadWetPetAamendmentToDocumentByid(updateid));
		model.put("msg", msg);
		return new ModelAndView("edit_search_WET_PET_amendment_Tiles");
	}

	@RequestMapping(value = "/WetPetUpdateAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView WetPetUpdateAction(@ModelAttribute("WetPetEditCMD") UploadWetPetAamendmentToDocument updateid,
			@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1, HttpServletRequest request,
			ModelMap model, HttpSession session, String msg, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (roleType.equals("HeadQuarter") & roleType.equals("ALL") & !roleType.equals("DEO")  & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String fname = "";
		String extension = "";

		if (!doc_path1.isEmpty()) {
			String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());

			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (doc_path1.getSize() > fileSizeLimit) {
				model.put("updateid", updateid.getId());
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}

			if (!doc_path1Ext.equals("pdf")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			// code modify by Paresh on 05/05/20202
			try {
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				byte[] bytes = doc_path1.getBytes();
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
					return new ModelAndView("redirect:upload_WET_PET_amendment");
				}
			} catch (Exception e) {
			}
		} else {

			List<UploadWetPetAamendmentToDocument> newamd = wetpetamdDAO.getWet_Pet_DownloadByid(updateid.getId());
			String oldamdtdocu = newamd.get(0).getDoc_path();
			updateid.setDoc_path(oldamdtdocu);
		}

		if (validation.checkRemarksLength(updateid.getDoc_path()) == false) {
			model.put("msg", validation.amendDocMSG);
			return new ModelAndView("redirect:upload_WET_PET_amendment");
		}
		if (validation.checkRemarksLength(updateid.getDoc_path()) == false) {
			model.put("msg", validation.remarksMSG);
			return new ModelAndView("redirect:upload_WET_PET_amendment");
		}
		if (validation.checkWetPetLength(updateid.getWet_pet_no()) == false) {
			model.put("msg", validation.authLtrnoMSG);
			return new ModelAndView("redirect:upload_WET_PET_amendment");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		wetpetamdDAO.UpdateWetPetAdmtEdit(updateid, username, modifydate);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		model.put("msg", "Updated Successfully");
		return new ModelAndView("redirect:upload_WET_PET_amendment");
	}
	// end search for complete equipment schedule

	// Download Document From ID in EDIT Page
	@RequestMapping(value = "/admin/getDownloadImageWetPetAmdt", method = RequestMethod.POST)
	public ModelAndView getDownloadImageWetPetAmdt(@ModelAttribute("id1") int id,
			@ModelAttribute("pageUrl") String pageUrl, @ModelAttribute("contraint") String contraint, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = wetpetamdDAO.getWet_Pet_DownloadByid(id).get(0).getDoc_path();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				model.put("msg", "Sorry.The file you are looking for does not exist");
				if (contraint.equals("Edit")) {
					model.put("WetPetEditCMD", wetpetamdDAO.getUploadWetPetAamendmentToDocumentByid(id));
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
				model.put("WetPetEditCMD", wetpetamdDAO.getUploadWetPetAamendmentToDocumentByid(id));
			}
			model.put("msg", "Download Successfully");
			return new ModelAndView(url);
		} catch (FileNotFoundException e) {
			
		}
		if (contraint.equals("Edit")) {
			model.put("WetPetEditCMD", wetpetamdDAO.getUploadWetPetAamendmentToDocumentByid(id));
		}
		return new ModelAndView(url);
	}

}
