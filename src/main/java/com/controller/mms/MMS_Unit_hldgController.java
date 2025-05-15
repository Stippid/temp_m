package com.controller.mms;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
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
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_Unit_hldgDAO;
import com.dao.mms.Mms_Common_DAO;
import com.dao.psg.popup_history.Update_Awards_Medal_History_DAO;
import com.dao.tms.MVCRDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.log.SysoLogger;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.MMS_TB_BARREL_CHILD_DTL;
import com.models.MMS_TB_CHILD_REGN_MSTR_DETL;
import com.models.MMS_TB_OBSN_DETL;
import com.models.MMS_TB_OH_CHILD_DTL;
import com.models.MMS_TB_REGN_MSTR;
import com.models.MMS_TB_REGN_MSTR_DETL;
import com.models.MMS_TB_STRIP_INSP_RECOIL;
import com.models.MMS_TB_UNIT_UPLOAD_DOCUMENT;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_Unit_hldgController {

	@Autowired
	private MVCRDAO orbatDtl;
	ValidationController valid = new ValidationController();

	@Autowired
	private MMS_Unit_hldgDAO m2DAO;

	@Autowired
	private Mms_Common_DAO mmsCommonDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();

	ValidationController validation = new ValidationController();

	// UNIT HLDG MODULE (1)-> (UNIT MCR SCREEN START)
	@RequestMapping(value = "/admin/mms_unit_mcr", method = RequestMethod.GET)
	public ModelAndView UnitMcr(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", roleSusNo);
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m2DAO.UnitMcrList(roleSusNo));
				Mmap.put("getTransction", m2DAO.getTrasctionUnitWiseList(roleSusNo));
				Mmap.put("update_date", m2DAO.getUnitLastUpdatedOn(roleSusNo));
				// Get Orbat
				List<Map<String, Object>> formation = orbatDtl.getFormationDetailsFromSusNo(roleSusNo);
				Mmap.put("fmn", formation.get(0).get("cmd_name") + "/" + formation.get(0).get("coprs_name") + "/"
						+ formation.get(0).get("div_name") + "/" + formation.get(0).get("bde_name"));
				Mmap.put("location", formation.get(0).get("loc") + " (" + formation.get(0).get("nrs") + ")");
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_unit_mcrTiles");
	}

	@RequestMapping(value = "/unit_update_mcr", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> unit_update_mcr(HttpServletRequest request, HttpSession session) {

		List<Map<String, Object>> errorlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> columns = new LinkedHashMap<String, Object>();
		try {
			String sus_no = request.getParameter("sus_no");
			if (sus_no.equals("")) {
				columns.put("error", "Please Select SUS No");
				errorlist.add(columns);
				return errorlist;
			}
			String v = m2DAO.veryfyTransaction_MCR(sus_no);
			if (v.equals("0")) {
				columns.put("error", "An Error Occured");
				errorlist.add(columns);
				return errorlist;
			} else {
				// Upload Doc
				String username = session.getAttribute("username").toString();
				MMS_TB_UNIT_UPLOAD_DOCUMENT u = new MMS_TB_UNIT_UPLOAD_DOCUMENT();
				u.setSus_no(sus_no);
				u.setCreated_by(username);
				u.setCreated_on(new Date());
				return unitUploadDocument(errorlist, u);
				// Upload Doc
			}
		} catch (Exception e) {

			columns.put("error", "An Error Occured");
			errorlist.add(columns);
			return errorlist;
		}
	}

	@RequestMapping(value = "/miso_replay_to_unit", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> miso_replay_to_unit(HttpServletRequest request,
			HttpSession session) {

		List<Map<String, Object>> errorlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> columns = new LinkedHashMap<String, Object>();
		try {
			String miso_reply_id = request.getParameter("miso_reply_id");
			String miso_reply = request.getParameter("miso_reply");
			if (miso_reply.equals("")) {
				columns.put("error", "please enter MISO Reply.");
				errorlist.add(columns);
				return errorlist;
			}
			if (miso_reply_id.equals("")) {
				columns.put("error", "please enter valid Data.");
				errorlist.add(columns);
				return errorlist;
			}

			// SANA 4 17-11-2022
			String username = session.getAttribute("username").toString();

			columns.put("error", m2DAO.updateMisoReply(miso_reply, Integer.parseInt(miso_reply_id), username));
			errorlist.add(columns);
			return errorlist;
		} catch (Exception e) {

			columns.put("error", "An Error Occured");
			errorlist.add(columns);
			return errorlist;
		}
	}

	@RequestMapping(value = "/admin/unit_upload_doc_mcr", method = RequestMethod.POST)
	public @ResponseBody List<String> unit_upload_doc_mcr(
			@RequestParam(value = "unit_upload_document_byte", required = false) MultipartFile uploadMcr,
			HttpServletRequest request, ModelMap model, HttpSession session, String sus_no1, String unit_remarks) {
		List<String> list = new ArrayList<>();
		if (sus_no1.equals("")) {
			list.add("please select SUS No");
			return list;
		}
		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
		if (uploadMcr.getSize() > fileSizeLimit) {
			list.add("File size should be less then 2 MB");
			return list;
		}
		String uploadMcrExt = FilenameUtils.getExtension(uploadMcr.getOriginalFilename()).toLowerCase();
		if (!uploadMcrExt.equals("zip") & !uploadMcrExt.equals("rar") & !uploadMcrExt.equals("pdf")) {
			list.add("Only *.zip or *.rar or *.pdf file extensions allowed");
			return list;
		}
		if (unit_remarks.equals("")) {
			list.add("please select SUS No");
			return list;
		}

		String username = session.getAttribute("username").toString();
		MMS_TB_UNIT_UPLOAD_DOCUMENT upload = new MMS_TB_UNIT_UPLOAD_DOCUMENT();
		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		// code modify by Paresh on 05/05/2020
		if (!uploadMcr.isEmpty()) {
			try {
				byte[] bytes = uploadMcr.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)
						|| fileValidation.check_PDF_File(bytes)) {
					String tmsFilePath = session.getAttribute("mmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = uploadMcr.getOriginalFilename();
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ username + "_MMSDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					upload.setSus_no(sus_no1);
					upload.setDoc(fname);
					upload.setRemark(unit_remarks);
					upload.setCreated_by(username);
					upload.setCreated_on(new Date());

					Session session1 = HibernateUtil.getSessionFactory().openSession();
					session1.beginTransaction();
					session1.save(upload);
					session1.getTransaction().commit();
					session1.close();
					list.add("Document Uploaded Successfully.");
				} else {
					list.add("Invalid File Format.");
				}
			} catch (Exception e) {
				list.add("an Error occured file saving.");
			}
		}
		return list;
	}

	public List<Map<String, Object>> unitUploadDocument(List<Map<String, Object>> errorlist,
			MMS_TB_UNIT_UPLOAD_DOCUMENT u) {
		Map<String, Object> columns = new LinkedHashMap<String, Object>();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			session1.save(u);
			tx.commit();
			columns.put("error", "Data Updated");
			errorlist.add(columns);
			return errorlist;
		} catch (Exception e) {

			columns.put("error", "An Error Occured");
			errorlist.add(columns);
			return errorlist;
		} finally {
			session1.close();
		}
	}
	// UNIT HLDG MODULE (1)-> (UNIT MCR SCREEN END)

	// UNIT HLDG MODULE (2)-> (UNIT CC SCREEN START)
	@RequestMapping(value = "/admin/mms_unit_correctness_certificate", method = RequestMethod.GET)
	public ModelAndView mms_unit_correctness_certificate(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_unit_correctness_certificate",
				sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = (c.get(Calendar.MONTH) + 1);

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);

		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", roleSusNo);
				Mmap.put("m_3", l1.get(0).get(3));
				Mmap.put("m_1", m2DAO.unitCorrectCertilist(roleSusNo, String.valueOf(month), String.valueOf(year)));
				Mmap.put("getUnitUploadedDocDetails", m2DAO.getUnitUploadedDocDetails(roleSusNo, String.valueOf(month),
						String.valueOf(year), roleAccess));
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_unit_correctness_certificateTiles");
	}
	// UNIT HLDG MODULE (2)-> (UNIT CC SCREEN END)

	// UNIT HLDG MODULE (3)-> (ADD NEW EQPT SCREEN START)
	@RequestMapping(value = "/admin/mms_new_eqpt_details", method = RequestMethod.GET)
	public ModelAndView mms_new_eqpt_details(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);

		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", roleSusNo);
				Mmap.put("m_3", l1.get(0).get(3));
			}
		}

		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		Mmap.put("ml_2", m1.getDomainListingData("SERVICIABILITY"));
		// Mmap.put("ml_3", m1.getDomainListingValues("TYPEOFHOLDING", "A"));
		Mmap.put("ml_3", m1.getTypeOfHold("1"));
		Mmap.put("ml_4", m1.getMMSDepotList(sessionA));
		return new ModelAndView("mms_new_eqpt_detailsTiles");
	}
	// UNIT HLDG MODULE (3)-> (ADD NEW EQPT SCREEN END)

	// UNIT HLDG MODULE (4)-> (APPROVE NEW EQPT SCREEN START)
	@RequestMapping(value = "/admin/mms_new_eqpt_details_search", method = RequestMethod.GET)
	public ModelAndView new_eqpt_details_search(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);

		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("m_2", roleSusNo);
				Mmap.put("m_3", l1.get(0).get(3));
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_new_eqpt_details_searchTiles");
	}
	// UNIT HLDG MODULE (4)-> (APPROVE NEW EQPT SCREEN END)

	@RequestMapping(value = "/admin/mms_new_eqpt_details_view", method = RequestMethod.GET)
	public ModelAndView new_eqpt_details_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_new_eqpt_details_viewTiles");
	}

	// UNIT HLDG MODULE (5)-> (UPDATE EQPT DATA SCREEN START)
	@RequestMapping(value = "/admin/mms_eqpt_modify_update", method = RequestMethod.GET)
	public ModelAndView eqpt_modify_update(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("mms_eqpt_modify_update", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);
		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("sus_n", roleSusNo);
				Mmap.put("Unit_n", l1.get(0).get(3));
			}
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingValues("TYPEOFHOLDING", "A"));
		Mmap.put("ml_2", m1.getDomainListingData("SERVICIABILITY"));
		return new ModelAndView("mms_eqpt_modify_updateTiles");
	}
	// UNIT HLDG MODULE (5)-> (UPDATE EQPT DATA SCREEN END)

	// UNIT HLDG MODULE (6)-> (UPDATE REGD NO SCREEN START)
	@RequestMapping(value = "/admin/mms_regno_update", method = RequestMethod.GET)
	public ModelAndView mms_change_regn_nomen(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("mms_regno_update", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);

		if (!roleType.equalsIgnoreCase("ALL")) {
			if (roleAccess.equalsIgnoreCase("UNIT")) {
				Mmap.put("sus_n", roleSusNo);
				Mmap.put("Unit_n", l1.get(0).get(3));
			}
		}

		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingValues("TYPEOFHOLDING", "A"));
		return new ModelAndView("mms_regno_updateTiles");
	}
	// UNIT HLDG MODULE (6)-> (UPDATE REGD NO SCREEN END)

	// UNIT HLDG MODULE (7)-> (INTER UNIT TRANSFER - (UNIT TO UNIT) SCREEN START)
	@RequestMapping(value = "/mms_chg_of_itfrstk", method = RequestMethod.GET)
	public ModelAndView mms_chg_of_itfrstk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_chg_of_itfrstk", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("MISO")) {
			Mmap.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "A:R", session));
		}
		Mmap.put("roleAccess", roleAccess);
		return new ModelAndView("mms_chg_of_itfrstkTiles");
	}
	// UNIT HLDG MODULE (7)-> (INTER UNIT TRANSFER - (UNIT TO UNIT) SCREEN END)

	// UNIT HLDG MODULE (8)-> (EQPT DEPOSIT - (UNIT TO DEPOT) SCREEN START)
	@RequestMapping(value = "/mms_chg_of_idepstk", method = RequestMethod.GET)
	public ModelAndView mms_chg_of_idepstk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_chg_of_idepstk", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		Mmap.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "G", session));
		return new ModelAndView("mms_chg_of_idepstkTiles");
	}
	// UNIT HLDG MODULE (8)-> (EQPT DEPOSIT - (UNIT TO DEPOT) SCREEN END)

	// UNIT HLDG MODULE (9)-> (EQPT TRANSFER - (DEPOT TO DEPOT) SCREEN START)
	@RequestMapping(value = "/mms_chg_of_ddepstk", method = RequestMethod.GET)
	public ModelAndView mms_chg_of_ddepstk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_chg_of_ddepstk", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		Mmap.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "D:R", session));
		return new ModelAndView("mms_chg_of_ddepstkTiles");
	}
	// UNIT HLDG MODULE (9)-> (EQPT TRANSFER - (DEPOT TO DEPOT) SCREEN END)

	// UNIT HLDG MODULE (10)-> (EQPT TRANSFER - (CHANGE HLDG TYPE) SCREEN START)
	@RequestMapping(value = "/mms_chg_of_stk", method = RequestMethod.GET)
	public ModelAndView mms_chg_of_stk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_chg_of_stk", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		Mmap.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "D:R", session));
		return new ModelAndView("mms_chg_of_stkTiles");
	}
	// UNIT HLDG MODULE (10)-> (EQPT TRANSFER - (CHANGE HLDG TYPE) SCREEN END)

	// (1)-> (UNIT MCR SCREEN METHODS START)
	@RequestMapping(value = "/admin/UnitMList", method = RequestMethod.POST)
	public ModelAndView UnitMcrList(@ModelAttribute("m1_sus") String m1_sus, String m1_unit, ModelMap model,
			HttpSession sessionA) {
		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			m1_sus = roleSusNo;
			m1_unit = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}

		if (m1_sus.equals("")) {
			model.put("msg", "Please Select the To SUS.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (m1_sus != "") {
			if (validation.sus_noLength(m1_sus) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (m1_unit.equals("")) {
			model.put("msg", "Please Select the To Unit.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (m1_unit != "") {
			if (validation.checkUnit_nameLength(m1_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);
		model.put("m_1", m2DAO.UnitMcrList(m1_sus));
		model.put("m_2", m1_sus);
		model.put("m_3", m1_unit);
		model.put("getTransction", m2DAO.getTrasctionUnitWiseList(m1_sus));
		model.put("update_date", m2DAO.getUnitLastUpdatedOn(m1_sus));
		List<Map<String, Object>> formation = orbatDtl.getFormationDetailsFromSusNo(m1_sus);
		if (formation.size() > 0) {

			model.put("fmn", formation.get(0).get("cmd_name") + "/" + formation.get(0).get("coprs_name") + "/"
					+ formation.get(0).get("div_name") + "/" + formation.get(0).get("bde_name"));
			model.put("location", formation.get(0).get("loc") + " (" + formation.get(0).get("nrs") + ")");
		}
		return new ModelAndView("mms_unit_mcrTiles");
	}

	@RequestMapping(value = "/admin/UnitRList", method = RequestMethod.POST)
	public ModelAndView UnitMcrRegnList(@ModelAttribute("mr_sus") String mr_sus, String mr_unit, ModelMap model,
			HttpSession sessionA) {
		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			mr_sus = roleSusNo;
			mr_unit = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}
		if (mr_sus.equals("")) {
			model.put("msg", "Please Select the To SUS.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (mr_sus != "") {
			if (validation.sus_noLength(mr_sus) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}
		if (mr_unit.equals("")) {
			model.put("msg", "Please Select the To Unit.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (mr_unit != "") {
			if (validation.checkUnit_nameLength(mr_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);
		model.put("m_r", m2DAO.UnitRegnMcrList(mr_sus));
		model.put("m_2", mr_sus);
		model.put("m_3", mr_unit);
		model.put("getTransction", m2DAO.getTrasctionUnitWiseList(mr_sus));
		model.put("update_date", m2DAO.getUnitLastUpdatedOn(mr_sus));
		List<Map<String, Object>> formation = orbatDtl.getFormationDetailsFromSusNo(mr_sus);
		if (formation.size() > 0) {
			model.put("fmn", formation.get(0).get("cmd_name") + "/" + formation.get(0).get("coprs_name") + "/"
					+ formation.get(0).get("div_name") + "/" + formation.get(0).get("bde_name"));
			model.put("location", formation.get(0).get("loc") + " (" + formation.get(0).get("nrs") + ")");
		}
		return new ModelAndView("mms_unit_mcrTiles");
	}

	@RequestMapping(value = "/mms_print_unit_mcr", method = RequestMethod.POST)
	public ModelAndView mms_print_unit_mcr(@Valid @Validated @ModelAttribute("printMCRId") String printMCRId,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			printMCRId = roleSusNo;
		}

		model.put("m_1", m2DAO.UnitMcrList(printMCRId));
		model.put("m_2", printMCRId);

		List<String> a1 = mmsCommonDAO.getMMSHirarNameBySUS("BRIGADE", printMCRId);
		String[] pa1 = a1.get(0).split(":");
		List<String> a2 = mmsCommonDAO.getMMSHirarNameBySUS("DIVISION", printMCRId);
		String[] pa2 = a2.get(0).split(":");
		List<String> a3 = mmsCommonDAO.getMMSHirarNameBySUS("CORPS", printMCRId);
		String[] pa3 = a3.get(0).split(":");
		List<String> a4 = mmsCommonDAO.getMMSHirarNameBySUS("COMMAND", printMCRId);
		String[] pa4 = a4.get(0).split(":");

		String hqv = "";
		if (pa1[1].length() > 0) {
			hqv = hqv + pa1[1];
		}
		if (pa2[1].length() > 0) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa2[1];
		}

		if (a3.size() > 0 && (!a3.equals(null))) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa3[1];
		}
		if (pa4[1].length() > 0) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa4[1];
		}

		model.put("m_5", hqv);
		model.put("m_3", mmsCommonDAO.getMMSHirarNameBySUS("HQ", printMCRId));
		// model.put("m_dt", mmsCommonDAO.getMMSMaxDt(printMCRId));
		model.put("m_dt", m2DAO.getUnitLastUpdatedOn(printMCRId));
		return new ModelAndView("mms_print_unit_mcrTiles");
	}

	@RequestMapping(value = "/mms_print_ue_uh_summ", method = RequestMethod.POST)
	public ModelAndView mms_print_ue_uh_summ(@ModelAttribute("p_sus") String p_sus, String p_para, ModelMap model,
			HttpSession sessionA) {
		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			p_sus = roleSusNo;
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);
		model.put("m_1", m2DAO.mms_ue_uh_summ(p_sus, p_para));

		List<String> a1 = mmsCommonDAO.getMMSHirarNameBySUS("BRIGADE", p_sus);
		String[] pa1 = a1.get(0).split(":");
		List<String> a2 = mmsCommonDAO.getMMSHirarNameBySUS("DIVISION", p_sus);
		String[] pa2 = a2.get(0).split(":");
		List<String> a3 = mmsCommonDAO.getMMSHirarNameBySUS("CORPS", p_sus);
		String[] pa3 = a3.get(0).split(":");
		List<String> a4 = mmsCommonDAO.getMMSHirarNameBySUS("COMMAND", p_sus);
		String[] pa4 = a4.get(0).split(":");

		String hqv = "";
		if (pa1[1].length() > 0) {
			hqv = hqv + pa1[1];
		}
		if (pa2[1].length() > 0) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa2[1];
		}

		if (a3.size() > 0 && (!a3.equals(null))) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa3[1];
		}
		if (pa4[1].length() > 0) {
			if (hqv.length() > 0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa4[1];
		}

		model.put("m_5", hqv);

		model.put("m_2", p_sus);
		model.put("m_3", p_para);
		model.put("m_4", mmsCommonDAO.getMMSHirarNameBySUS("HQ", p_sus));
		model.put("m_dt", m2DAO.getUnitLastUpdatedOn(p_sus));
		return new ModelAndView("mms_print_ue_uh_summTiles");
	}

	@RequestMapping(value = "/admin/mms_print_ue_uh_summ_data", method = RequestMethod.POST)
	public ModelAndView mms_print_ue_uh_summ_data(@ModelAttribute("m2_sus") String m2_sus, String m2_unit,
			ModelMap model, HttpSession sessionA) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		if (m2_sus.equals("")) {
			model.put("msg", "Please Select the To SUS.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (m2_sus != "") {
			if (validation.sus_noLength(m2_sus) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (m2_unit.equals("")) {
			model.put("msg", "Please Select the To Unit.");
			return new ModelAndView("mms_unit_mcr");
		}
		if (m2_unit != "") {
			if (validation.checkUnit_nameLength(m2_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_unit_mcr");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			m2_sus = roleSusNo;
			m2_unit = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);
		model.put("a_1", m2DAO.mms_ue_uh_summ(m2_sus, "ALL"));
		model.put("m_2", m2_sus);
		model.put("m_3", m2_unit);
		model.put("getTransction", m2DAO.getTrasctionUnitWiseList(m2_sus));
		model.put("update_date", m2DAO.getUnitLastUpdatedOn(m2_sus));
		List<Map<String, Object>> formation = orbatDtl.getFormationDetailsFromSusNo(m2_sus);
		if (formation.size() > 0) {
			model.put("fmn", formation.get(0).get("cmd_name") + "/" + formation.get(0).get("coprs_name") + "/"
					+ formation.get(0).get("div_name") + "/" + formation.get(0).get("bde_name"));
			model.put("location", formation.get(0).get("loc") + " (" + formation.get(0).get("nrs") + ")");
		}
		return new ModelAndView("mms_unit_mcrTiles");
	}
	// (1)-> (UNIT MCR SCREEN METHODS END)

	// (2)-> (UNIT CC SCREEN METHODS START)
	@RequestMapping(value = "/mms_cc_view", method = RequestMethod.POST)
	public ModelAndView mms_cc_view(@ModelAttribute("c_id") Integer c_id, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_unit_correctness_certificate",
				session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> lt = m2DAO.UnitccReglist(c_id);
		String[] s1 = lt.get(0).get(0).split(":");
		model.put("m_1", s1);
		return new ModelAndView("mms_print_reg_noTiles");
	}

	@RequestMapping(value = "/admin/unitCClist", method = RequestMethod.POST)
	public ModelAndView unitCClist(@ModelAttribute("m2_sus") String m2_sus, String m2_mnth, String m2_unit,
			ModelMap model, HttpSession sessionA) {

		Boolean val = roledao.ScreenRedirect("mms_unit_correctness_certificate",
				sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			m2_sus = roleSusNo;
			m2_unit = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}

		if (m2_unit.equals("")) {
			model.put("msg", "Please Select the To Unit.");
			return new ModelAndView("mms_unit_correctness_certificate");
		}
		if (m2_unit != "") {
			if (validation.checkUnit_nameLength(m2_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_unit_correctness_certificate");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);

		String[] parts = m2_mnth.split("-");
		String mth = parts[1];
		String yr = parts[0];
		ArrayList<ArrayList<String>> t1 = m2DAO.unitCorrectCertilist(m2_sus, mth, yr);
		model.put("m_1", t1);
		model.put("m_2", m2_sus);
		model.put("m_3", m2_unit);
		model.put("mt_y", m2_mnth);
		model.put("s_1", t1.size());
		model.put("getUnitUploadedDocDetails", m2DAO.getUnitUploadedDocDetails(m2_sus, mth, yr, roleAccess));
		return new ModelAndView("mms_unit_correctness_certificateTiles");
	}

	@RequestMapping(value = "/mms_unit_correctness_certificateAction", method = RequestMethod.POST)
	public ModelAndView mms_unit_correctness_certificateAction(
			@ModelAttribute("mms_unit_correctness_certificateCMD") MMS_TB_OBSN_DETL tod,
			@RequestParam(value = "unit_upload_document_byte", required = false) MultipartFile unit_upload_document,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_unit_correctness_certificate",
				session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String mnth = request.getParameter("mnth_year");
		String[] m = mnth.split("-");
		String sus = request.getParameter("sus_no");

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus = roleSusNo;
		}

		int dsize = Integer.parseInt(request.getParameter("dsize"));

		// new 21/06/2021
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		if (!unit_upload_document.isEmpty()) {
			try {
				final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
				if (unit_upload_document.getSize() > fileSizeLimit) {
					model.put("msg", "File size should be less then 2 MB");
					return new ModelAndView("redirect:mms_unit_correctness_certificate");
				}
				byte[] bytes = unit_upload_document.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)
						|| fileValidation.check_PNG_File(bytes) || fileValidation.check_JPEG_File(bytes)) {
					String tmsFilePath = session.getAttribute("mmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = unit_upload_document.getOriginalFilename();
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					String fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
							+ "_" + userid + "_MMSDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					tod.setUnit_upload_document(fname);
				} else {
					model.put("msg", "Only *.jpg, *.jpeg *.png *.rar and *.zip  file extensions allowed");
					return new ModelAndView("redirect:mms_unit_correctness_certificate");
				}
			} catch (Exception e) {
				model.put("msg", "an Error ocurre file saving.");
				return new ModelAndView("redirect:mms_unit_correctness_certificate");
			}
		}
		// new 21/06/2021

		tod.setUnit_remarks(tod.getUnit_remarks());
		tod.setMth(m[1]);
		tod.setYr(m[0]);
		tod.setData_cr_by(username);
		tod.setData_cr_date(creadtedate);
		tod.setData_upd_by(username);
		tod.setData_upd_date(creadtedate);
		tod.setSus_no(sus);

		String firstPart = m[0] + m[1];
		tod.setLatest(firstPart);
		String st = request.getParameter("data1");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		if (st.equalsIgnoreCase("correct")) {
			tod.setCert_opt1("Y");
			tod.setCert_opt2("N");
			tod.setObsn1("");
			tod.setCensus_no("");
			tod.setType_of_hldg("");
			tod.setType_of_eqpt("");

			sessionHQL.beginTransaction();
			sessionHQL.save(tod);
			sessionHQL.getTransaction().commit();
		} else {
			tod.setCert_opt1("N");
			tod.setCert_opt2("Y");
			tod.setType_of_eqpt("1");

			for (int i = 0; i < dsize; i++) {
				String q1_obs = request.getParameter("ob1_" + i);
				String q1_cens = request.getParameter("cens_" + i);
				String q1_hldg = request.getParameter("hldg_" + i);
				String old_obs = request.getParameter("old_" + i);
				String old_id = request.getParameter("oldid_" + i);

				if (q1_obs.equalsIgnoreCase("")) {
					continue;
				} else {
					if (old_obs.equalsIgnoreCase("")) {
						tod.setObsn1(q1_obs);
						tod.setCensus_no(q1_cens);
						tod.setType_of_hldg(q1_hldg);
						sessionHQL.beginTransaction();
						sessionHQL.save(tod);
						sessionHQL.getTransaction().commit();
						sessionHQL.clear();
					} else {
						// new 21/06/2021
						String whr = "";
						if (!tod.getUnit_upload_document().equals("")) {
							whr += " , unit_upload_document=:unit_upload_document ";
						}
						if (!tod.getUnit_remarks().equals("")) {
							whr += " , unit_remarks=:unit_remarks ";
						}
						// new 21/06/2021

						sessionHQL.beginTransaction();
						String hql1 = "update MMS_TB_OBSN_DETL set obsn1=:obs,data_upd_by=:m1,data_upd_date=:m2 " + whr
								+ " where tr_id=:id";
						Query query1 = sessionHQL.createQuery(hql1);

						query1.setString("obs", q1_obs).setString("m1", username).setString("m2", creadtedate)
								.setInteger("id", Integer.parseInt(old_id));

						// new 21/06/2021
						if (!tod.getUnit_upload_document().equals("")) {
							query1.setString("unit_upload_document", tod.getUnit_upload_document());
						}
						if (!tod.getUnit_remarks().equals("")) {
							query1.setString("unit_remarks", tod.getUnit_remarks());
						}
						// new 21/06/2021

						query1.executeUpdate();
						sessionHQL.getTransaction().commit();
					}
				}
			}
		}
		sessionHQL.close();
		model.put("msg", "Certificate Submitted Successfully");
		return new ModelAndView("redirect:mms_unit_correctness_certificate");
	}

	// (2)-> (UNIT CC SCREEN METHODS END)

	// (3)-> (ADD NEW EQPT SCREEN METHODS START)
	@RequestMapping(value = "/getSearchRegno", method = RequestMethod.POST)
	public @ResponseBody String getSearchRegno(HttpServletRequest request, HttpSession session, String e) {

		if (m2DAO.ifExistEqptRegNo(e, session) == true) {
			return "Success dtl";
		} else {
			return "Failure dtl";
		}

	}

	@RequestMapping(value = "/CheckmmsRegNoList", method = RequestMethod.POST) // Used In to check exist census No
	public @ResponseBody List<String> CheckmmsRegNoList(String enc, String b, HttpSession s1) {
		String[] reg = b.split(":");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select eqpt_regn_no from MMS_TB_REGN_MSTR_DETL where eqpt_regn_no in (:reg)");
		q.setParameterList("reg", reg);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/new_eqpt_detailsActions", method = RequestMethod.POST)
	public ModelAndView new_eqpt_detailsActions(@ModelAttribute("new_eqpt_detailsCMD") MMS_TB_REGN_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession sessionA,
			@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		MMS_TB_REGN_MSTR_DETL mms_detl = new MMS_TB_REGN_MSTR_DETL();
		String fname = "";
		String username = sessionA.getAttribute("username").toString();

		String from_sus_no = request.getParameter("from_sus_no");

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String to_sus_no = "";
		String to_sus_name = "";
		if (roleAccess.equals("Unit")) {
			to_sus_no = roleSusNo;
			to_sus_name = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		} else {
			to_sus_no = request.getParameter("to_sus_no");
			to_sus_name = request.getParameter("to_sus_name");
		}
		String from_form_code = request.getParameter("from_code");

		List<String> t_code = m1.getMMSUnitCodeList(to_sus_no);
		String to_form_code = t_code.get(0);
		String census_no = request.getParameter("census_no");
		String type_of_hldg = request.getParameter("type_of_hldg");
		String type_of_eqpt = request.getParameter("type_of_eqpt");
		String rv_no = request.getParameter("rv_no");
		String rv_date = request.getParameter("rv_date");
		String issued_qty = request.getParameter("issued_qty");
		String prf_code = request.getParameter("prf_code");
		int countlength = Integer.parseInt(request.getParameter("count"));
		String[] service_status = request.getParameterValues("service_status_t");
		String[] b_1 = request.getParameterValues("barrel1_detl_t");
		String[] b_2 = request.getParameterValues("barrel2_detl_t");
		String[] b_3 = request.getParameterValues("barrel3_detl_t");
		String[] b_4 = request.getParameterValues("barrel4_detl_t");
		String[] re = request.getParameterValues("remarks_t");
		String[] reg_no_t = request.getParameterValues("eqpt_regn_no_t");

		if (rv_no.equals("")) {
			model.put("msg", "Please Enter the IV Number");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (validation.checkRv_NoLength(rs.getRv_no()) == false) {
			model.put("msg", validation.rv_noMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		Date rv_date_i = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (rv_date.equals("")) {
				model.put("msg", "Please Select the IV Date");
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else {
				rv_date_i = formatter1.parse(request.getParameter("rv_date"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (from_sus_no.equals("-1")) {
			model.put("msg", "Please Select the Issuing Depot");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (to_sus_no.equals("")) {
			model.put("msg", "Please Select the To SUS.");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (validation.sus_noLength(rs.getTo_sus_no()) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (to_sus_name.equals("")) {
			model.put("msg", "Please Select the To Unit");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		} else if (validation.checkUnit_nameLength(request.getParameter("to_sus_name")) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		} else if (type_of_hldg.equals("-1")) {
			model.put("msg", "Please Select the Type of Holding");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (type_of_eqpt.equals("-1")) {
			model.put("msg", "Please Select the Type of Equipment");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (prf_code.equals("-1")) {
			model.put("msg", "Please Select the Equipment PRF Group");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (census_no.equals("-1")) {
			model.put("msg", "Please Select the census_no");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (issued_qty.equals("")) {
			model.put("msg", "Please Enter the Issued Quantity");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (validation.checkIssued_QtyLength(String.valueOf((rs.getIssued_qty()))) == false) {
			model.put("msg", validation.issued_qtyMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (validation.checkEqpt_MakeLength(rs.getEqpt_make()) == false) {
			model.put("msg", validation.eqpt_makeMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		else if (validation.checkEqpt_ModelLength(rs.getEqpt_model()) == false) {
			model.put("msg", validation.eqpt_modelMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		int Unit_price_v;
		if (request.getParameter("unit_price") == "" || request.getParameter("unit_price").equals("")
				|| request.getParameter("unit_price") == null || request.getParameter("unit_price").equals("null")) {
			Unit_price_v = 0;
		}

		else {
			Unit_price_v = Integer.parseInt(request.getParameter("unit_price"));
			if (validation.checkUnit_PriceLength(String.valueOf(Unit_price_v)) == false) {
				model.put("msg", validation.Unit_PriceMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			}
		}

		int Depres_rate_v;
		if (request.getParameter("depres_rate") == "" || request.getParameter("depres_rate").equals("")
				|| request.getParameter("depres_rate") == null || request.getParameter("depres_rate").equals("null")) {
			Depres_rate_v = 0;
		} else {
			Depres_rate_v = Integer.parseInt(request.getParameter("depres_rate"));
			if (validation.checkDepres_RateLength(String.valueOf(Depres_rate_v)) == false) {
				model.put("msg", validation.Depres_RateMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			}
		}

		rs.setUnit_price(Unit_price_v);
		rs.setDepres_rate(Depres_rate_v);

		if (validation.checkLife_Of_AssetsLength(rs.getLife_of_assets()) == false) {
			model.put("msg", validation.life_of_assetsMSG);
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		for (int i = 0; i <= countlength; i++) {

			if (reg_no_t[i].equals("")) {
				model.put("msg", "Please Enter the New Regn No");
				return new ModelAndView("redirect:mms_new_eqpt_details");
			}

			/*
			 * if (!valid.isUnit(reg_no_t[i])) { model.put("msg",
			 * " Eqpt Regn No Must Be Contain Only Numbers & Alphabet"); return new
			 * ModelAndView("redirect:mms_new_eqpt_details"); }
			 */

			/*
			 * if (!valid.isOnlyAlphabetNumericSpaceNot(reg_no_t[i])) { model.put("msg",
			 * "Special Charater and Space is Not Allowed"); return new
			 * ModelAndView("redirect:mms_new_eqpt_details"); }
			 */

			if (!valid.validateunderscore(reg_no_t[i])) {
				model.put("msg", "Only Underscore(_) and dash(-) allowed in Eqpt Regn No.");
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else if (validation.checkEqpt_Regn_NoLength(reg_no_t[i]) == false) {
				model.put("msg", validation.eqpt_regn_noMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			}
		}

		if (service_status.equals("-1")) {
			model.put("msg", "Please Select the Serviceable Status");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}

		String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
		if (!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase())
				&& !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
				&& !upload_imgEXt.toUpperCase().equals("png".toUpperCase())
				&& !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())
				&& !upload_imgEXt.toUpperCase().equals("".toUpperCase())) {
			model.put("msg", "Only *.jpg, *.jpeg *.png adn *.pdf file extensions allowed");
			return new ModelAndView("redirect:mms_new_eqpt_details");
		}
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		if (!doc_upload1.isEmpty()) {
			// code modify by Paresh on 05/05/2020
			try {
				byte[] bytes = doc_upload1.getBytes();
				String mmsFilePath = sessionA.getAttribute("mmsFilePath").toString();
				File dir = new File(mmsFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = doc_upload1.getOriginalFilename();
				String extension1 = "";
				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension1 = filename.substring(j + 1);
				}
				fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
						+ userid + "_MMSDOC." + extension1;
				if (validation.checkImageAnmlLength(fname) == false) {
					model.put("msg", validation.image_anmlMSG);
					return new ModelAndView("redirect:mms_new_eqpt_details");
				}
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				rs.setUpload_file_name(fname);
			} catch (Exception e) {
			}
		}

		rs.setRv_date(rv_date_i);
		rs.setIssue_sus_no(to_sus_no);
		rs.setFrom_form_code(from_form_code);
		rs.setFrom_tr_date(new Date());
		rs.setTo_form_code(to_form_code);
		rs.setTo_tr_date(new Date());
		rs.setData_cr_by(username);
		rs.setData_cr_date(new Date());
		rs.setOp_status("0");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		sessionHQL.save(rs);
		sessionHQL.getTransaction().commit();

		Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();

		mms_detl.setSus_no(to_sus_no);
		mms_detl.setCensus_no(census_no);
		mms_detl.setType_of_hldg(type_of_hldg);
		mms_detl.setType_of_eqpt(type_of_eqpt);
		mms_detl.setFrom_sus_no(from_sus_no);
		mms_detl.setFrom_form_code(from_form_code);
		mms_detl.setFrom_tr_date(new Date());
		mms_detl.setTo_sus_no(to_sus_no);
		mms_detl.setTo_form_code(to_form_code);
		mms_detl.setTo_tr_date(new Date());
		mms_detl.setData_cr_by(username);
		mms_detl.setData_cr_date(new Date());
		mms_detl.setOp_status("0");
		mms_detl.setTfr_status("N");
		mms_detl.setRv_no(rv_no);
		mms_detl.setRv_date(rv_date_i);
		mms_detl.setPrf_code(prf_code);

		// KAJAL V4 16/11/2022
		String Regn_seq_no_max = m2DAO.RegnNoGeneration1_UNIT(prf_code, "");
		String[] split_reg = Regn_seq_no_max.split("N");
		String Regn_seq_no = "";
		int x = 0;
		for (int i = 0; i <= countlength; i++) {
			sessionHQL2.flush();

			Regn_seq_no = split_reg[0] + "N" + String.format("%08d", (Integer.parseInt(split_reg[1]) + i));

			// checkIfExisteqpt_regn_no
			check_reg_no(Regn_seq_no, split_reg, i);

			mms_detl.setEqpt_regn_no(reg_no_t[i]);
			mms_detl.setRegn_seq_no(Regn_seq_no);
			mms_detl.setService_status(service_status[i]);

			if (validation.checkBarrel1_DetlLength(b_1[i]) == false) {
				model.put("msg", validation.Barrel1_DetlMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else if (validation.checkBarrel2_DetlLength(b_2[i]) == false) {
				model.put("msg", validation.Barrel2_DetlMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else if (validation.checkBarrel3_DetlLength(b_3[i]) == false) {
				model.put("msg", validation.Barrel3_DetlMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else if (validation.checkBarrel4_DetlLength(b_4[i]) == false) {
				model.put("msg", validation.Barrel4_DetlMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			} else if (validation.checkRemarksLength(re[i]) == false) {
				model.put("msg", validation.remarksMSGTMS);
				return new ModelAndView("redirect:mms_new_eqpt_details");
			}

			mms_detl.setBarrel1_detl(b_1[i]);
			mms_detl.setBarrel2_detl(b_2[i]);
			mms_detl.setBarrel3_detl(b_3[i]);
			mms_detl.setBarrel4_detl(b_4[i]);
			mms_detl.setSpl_remarks(re[i]);
			sessionHQL2.beginTransaction();
			sessionHQL2.save(mms_detl);
			sessionHQL2.getTransaction().commit();
			sessionHQL2.clear();
		}

		sessionHQL2.close();
		sessionHQL.close();

		model.put("msg", "New Equipment Details Send Successfully");
		return new ModelAndView("redirect:mms_new_eqpt_details");
	}

	public void check_reg_no(String Regn_seq_no, String[] split_reg, int i) {
		if (m2DAO.checkIfExistUnitHoldingeqpt_regn_no(Regn_seq_no).get(0) != null
				&& Integer.parseInt(m2DAO.checkIfExistUnitHoldingeqpt_regn_no(Regn_seq_no).get(0)) > 0) {
			i++;
			Regn_seq_no = split_reg[0] + "N" + String.format("%08d", (Integer.parseInt(split_reg[1]) + i));
			check_reg_no(Regn_seq_no, split_reg, i);

		}

	}

	/*
	 * @RequestMapping(value = "/new_eqpt_detailsActions",method=RequestMethod.POST)
	 * public ModelAndView
	 * new_eqpt_detailsActions(@ModelAttribute("new_eqpt_detailsCMD")
	 * MMS_TB_REGN_MSTR rs,HttpServletRequest request,ModelMap model,HttpSession
	 * sessionA,@RequestParam(value = "doc_upload1", required = false) MultipartFile
	 * doc_upload1) {
	 * 
	 * Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details",
	 * sessionA.getAttribute("roleid").toString()); if(val == false) { return new
	 * ModelAndView("AccessTiles"); }
	 * 
	 * MMS_TB_REGN_MSTR_DETL mms_detl = new MMS_TB_REGN_MSTR_DETL(); String
	 * extension=""; String fname = ""; String username =
	 * sessionA.getAttribute("username").toString();
	 * 
	 * String from_sus_no = request.getParameter("from_sus_no");
	 * 
	 * String roleAccess = sessionA.getAttribute("roleAccess").toString(); String
	 * roleSusNo = sessionA.getAttribute("roleSusNo").toString(); String to_sus_no =
	 * ""; String to_sus_name = ""; if(roleAccess.equals("Unit")){ to_sus_no =
	 * roleSusNo; to_sus_name =
	 * mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0
	 * ); }else { to_sus_no = request.getParameter("to_sus_no"); to_sus_name =
	 * request.getParameter("to_sus_name"); } String from_form_code =
	 * request.getParameter("from_code");
	 * 
	 * List<String> t_code = m1.getMMSUnitCodeList(to_sus_no); String to_form_code =
	 * t_code.get(0); String census_no = request.getParameter("census_no"); String
	 * type_of_hldg = request.getParameter("type_of_hldg"); String type_of_eqpt =
	 * request.getParameter("type_of_eqpt"); String rv_no =
	 * request.getParameter("rv_no"); String rv_date =
	 * request.getParameter("rv_date"); String issued_qty =
	 * request.getParameter("issued_qty"); String prf_code =
	 * request.getParameter("prf_code"); int countlength =
	 * Integer.parseInt(request.getParameter("count")); String[] service_status =
	 * request.getParameterValues("service_status"); String[] b_1 =
	 * request.getParameterValues("barrel1_detl_t"); String[] b_2 =
	 * request.getParameterValues("barrel2_detl_t"); String[] b_3 =
	 * request.getParameterValues("barrel3_detl_t"); String[] b_4 =
	 * request.getParameterValues("barrel4_detl_t"); String[] re =
	 * request.getParameterValues("remarks_t"); String[] reg_no_t =
	 * request.getParameterValues("eqpt_regn_no_t");
	 * 
	 * 
	 * if(rv_no.equals("")){ model.put("msg", "Please Enter the IV Number"); return
	 * new ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * Date rv_date_i = null; DateFormat formatter1 = new
	 * SimpleDateFormat("yyyy-MM-dd"); try{ if(rv_date.equals("")){ model.put("msg",
	 * "Please Select the IV Date"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }else{ rv_date_i =
	 * formatter1.parse(request.getParameter("rv_date")); } }catch(ParseException
	 * e){ e.printStackTrace(); }
	 * 
	 * if(from_sus_no.equals("-1")){ model.put("msg",
	 * "Please Select the Issuing Depot"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(to_sus_no.equals("")){ model.put("msg", "Please Select the To Unit");
	 * return new ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(to_sus_name.equals("")){ model.put("msg", "Please Select the To Unit");
	 * return new ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(type_of_hldg.equals("-1")){ model.put("msg",
	 * "Please Select the Type of Holding"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(type_of_eqpt.equals("-1")){ model.put("msg",
	 * "Please Select the Type of Equipment"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(prf_code.equals("-1")){ model.put("msg",
	 * "Please Select the Equipment PRF Group"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(census_no.equals("-1")){ model.put("msg", "Please Select the census_no");
	 * return new ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * if(issued_qty.equals("")){ model.put("msg",
	 * "Please Enter the Issued Quantity"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * for(int i=0;i<=countlength;i++) { if(reg_no_t[i].equals("")) {
	 * model.put("msg", "Please Enter the New Regn No"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); } }
	 * 
	 * if(service_status.equals("-1")) { model.put("msg",
	 * "Please Select the Serviceable Status"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 * 
	 * String upload_imgEXt =
	 * FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
	 * if(!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase()) &&
	 * !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase()) &&
	 * !upload_imgEXt.toUpperCase().equals("png".toUpperCase()) &&
	 * !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) { model.put("msg",
	 * "Only *.jpg, *.jpeg *.png adn *.pdf file extensions allowed"); return new
	 * ModelAndView("redirect:Imguploadurl"); }
	 * 
	 * if (!doc_upload1.isEmpty()) { try { byte[] bytes = doc_upload1.getBytes();
	 * String mmsFilePath = sessionA.getAttribute("mmsFilePath").toString(); File
	 * dir = new File(mmsFilePath); if (!dir.exists()) dir.mkdirs(); String filename
	 * = doc_upload1.getOriginalFilename(); int i = filename.lastIndexOf('.'); if (i
	 * >= 0) { extension = filename.substring(i+1); } java.util.Date date1= new
	 * java.util.Date(); fname = dir.getAbsolutePath() + File.separator + (new
	 * Timestamp(date1.getTime())).toString().replace(":",
	 * "").toString().replace(".",
	 * ".").toString().replace(" ","").toString().replace("-","").toString()+
	 * "MMS_Doc."+extension; File serverFile = new File(fname); BufferedOutputStream
	 * stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	 * stream.write(bytes); stream.close(); rs.setUpload_file_name(fname); } catch
	 * (Exception e) { } }
	 * 
	 * rs.setRv_date(rv_date_i); rs.setIssue_sus_no(to_sus_no);
	 * rs.setFrom_form_code(from_form_code); rs.setFrom_tr_date(new Date());
	 * rs.setTo_form_code(to_form_code); rs.setTo_tr_date(new Date());
	 * rs.setData_cr_by(username); rs.setData_cr_date(new Date());
	 * rs.setOp_status("0");
	 * 
	 * Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 * sessionHQL.beginTransaction(); sessionHQL.save(rs);
	 * sessionHQL.getTransaction().commit();
	 * 
	 * Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * mms_detl.setSus_no(to_sus_no); mms_detl.setCensus_no(census_no);
	 * mms_detl.setType_of_hldg(type_of_hldg);
	 * mms_detl.setType_of_eqpt(type_of_eqpt); mms_detl.setFrom_sus_no(from_sus_no);
	 * mms_detl.setFrom_form_code(from_form_code); mms_detl.setFrom_tr_date(new
	 * Date()); mms_detl.setTo_sus_no(to_sus_no);
	 * mms_detl.setTo_form_code(to_form_code); mms_detl.setTo_tr_date(new Date());
	 * mms_detl.setData_cr_by(username); mms_detl.setData_cr_date(new Date());
	 * mms_detl.setOp_status("0"); mms_detl.setTfr_status("N");
	 * mms_detl.setRv_no(rv_no); mms_detl.setRv_date(rv_date_i);
	 * mms_detl.setPrf_code(prf_code);
	 * 
	 * String Regn_seq_no_max = m2DAO.RegnNoGeneration(prf_code, ""); String[]
	 * split_reg = Regn_seq_no_max.split("N"); String Regn_seq_no="";
	 * 
	 * for(int i=0;i<=countlength;i++) { sessionHQL2.flush(); Regn_seq_no =
	 * split_reg[0]+"N"+String.format("%08d", (Integer.parseInt(split_reg[1])+i));
	 * mms_detl.setEqpt_regn_no(reg_no_t[i]); mms_detl.setRegn_seq_no(Regn_seq_no);
	 * mms_detl.setService_status(service_status[i]);
	 * mms_detl.setBarrel1_detl(b_1[i]); mms_detl.setBarrel2_detl(b_2[i]);
	 * mms_detl.setBarrel3_detl(b_3[i]); mms_detl.setBarrel4_detl(b_4[i]);
	 * mms_detl.setSpl_remarks(re[i]); sessionHQL2.beginTransaction();
	 * sessionHQL2.save(mms_detl); sessionHQL2.getTransaction().commit();
	 * sessionHQL2.clear(); }
	 * 
	 * sessionHQL2.close(); sessionHQL.close();
	 * 
	 * model.put("msg", "New Equipment Details Send Successfully"); return new
	 * ModelAndView("redirect:mms_new_eqpt_details"); }
	 */

	// (3)-> (ADD NEW EQPT SCREEN METHODS END)

	// (4)-> (SEARCH NEW EQPT SCREEN METHODS START)
	@RequestMapping(value = "/unitneweqptlist", method = RequestMethod.POST) // (will Optimized)
	public ModelAndView unitneweqptlist(@ModelAttribute("m2_sus") String m2_sus, String m2_stat, String m2_frmdt,
			String m2_todt, String m2_unit, ModelMap model, HttpSession sessionA) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		if (m2_sus.equals("")) {
			model.put("msg", "Please Select the To SUS.");
			return new ModelAndView("mms_new_eqpt_details_search");
		}
		if (m2_sus != "") {
			if (validation.sus_noLength(m2_sus) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details_search");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (m2_unit.equals("")) {
			model.put("msg", "Please Select the To Unit.");
			return new ModelAndView("mms_new_eqpt_details_search");
		}
		if (m2_unit != "") {
			if (validation.checkUnit_nameLength(m2_unit) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mms_new_eqpt_details_search");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (roleAccess.equals("Unit")) {
			m2_sus = roleSusNo;
			m2_unit = mmsCommonDAO.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		if (!m2_sus.equals("")) {
			qry += " r.issue_sus_no =:issue_sus_no";
		}

		if (!m2_stat.equals("") && (m2_stat.equals("0") || m2_stat.equals("1"))) {
			qry += " and r.op_status =:op_status";
		}

		if (!m2_frmdt.equals("")) {
			qry += " and r.data_cr_date BETWEEN DATE(:from_date)";
		}

		if (!m2_todt.equals("")) {
			qry += " and DATE(:curr_date) + 1";
		} else {
			qry += " and DATE('" + date + "') + 1";
		}

		q = session.createQuery(
				"select distinct r.issue_sus_no,r.rv_no,r.census_no,r.type_of_hldg,cast(r.data_cr_date as date),r.op_status,"
						+ "(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.issue_sus_no and status_sus_no ='Active'),"
						+ "(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
						+ "(select label from MMS_Domain_Values where domainid='TYPEOFHOLDING' and codevalue=r.type_of_hldg),"
						+ "(select count(regn_seq_no) from MMS_TB_REGN_MSTR_DETL where sus_no=r.issue_sus_no and op_status=r.op_status and rv_no=r.rv_no) "
						+ "from MMS_TB_REGN_MSTR r where " + qry + " ");

		if (m2_stat.equals("0") || m2_stat.equals("1")) {
			q.setParameter("issue_sus_no", m2_sus).setParameter("op_status", m2_stat)
					.setParameter("from_date", m2_frmdt).setParameter("curr_date", m2_todt);
		}
		if (m2_stat.equals("3")) {
			q.setParameter("issue_sus_no", m2_sus).setParameter("from_date", m2_frmdt).setParameter("curr_date",
					m2_todt);
		}
		@SuppressWarnings("unchecked")
		List<MMS_TB_REGN_MSTR> list = (List<MMS_TB_REGN_MSTR>) q.list();
		tx.commit();
		session.close();

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		model.put("r_1", l1);
		model.put("m_1", list);
		model.put("m_2", m2_sus);
		model.put("m_3", m2_stat);
		model.put("m_4", m2_frmdt);
		model.put("m_5", m2_todt);
		model.put("m_6", m2_unit);
		return new ModelAndView("mms_new_eqpt_details_searchTiles");
	}

	@RequestMapping(value = "/admin/NewEqptDetails", method = RequestMethod.POST)
	public ModelAndView NewEqptDetails(@ModelAttribute("issue_sus_no") String issue_sus_no, String census_no,
			String type_of_hldg, String rv_no, String op_status, String reg_cnt, String eqpt_frdt, String eqpt_todt,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession s1) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", s1.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();

		Query q = session.createQuery(
				"select distinct r.sus_no,r.census_no,r.type_of_hldg,r.rv_no,r.eqpt_regn_no,r.service_status,r.barrel1_detl,"
						+ "r.op_status,r.id,(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no ='Active'),"
						+ "(select nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=r.census_no),"
						+ "(select label from MMS_Domain_Values where domainid='TYPEOFHOLDING' and codevalue=r.type_of_hldg),"
						+ "(select label from MMS_Domain_Values where domainid='SERVICIABILITY' and codevalue=r.service_status),r.rv_date,r.regn_seq_no "
						+ "from MMS_TB_REGN_MSTR_DETL r where r.sus_no=:issue_sus_no and r.census_no=:census_no and r.type_of_hldg=:type_of_hldg and "
						+ "r.rv_no=:rv_no and op_status=:op_status");

		q.setParameter("issue_sus_no", issue_sus_no).setParameter("census_no", census_no)
				.setParameter("type_of_hldg", type_of_hldg).setParameter("rv_no", rv_no)
				.setParameter("op_status", op_status);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		session.getTransaction().commit();
		session.close();

		model.put("reg_cnt", reg_cnt);
		model.put("new_eqpt_details_viewCMD", list);
		model.put("eqpt_frdt", eqpt_frdt);
		model.put("eqpt_todt", eqpt_todt);
		model.put("op_status", op_status);

		return new ModelAndView("mms_new_eqpt_details_viewTiles");
	}

	@RequestMapping(value = "/admin/BackEqptDetails", method = RequestMethod.GET)
	public ModelAndView BackEqptDetails(@ModelAttribute("eqpt_status") String eqpt_status, String eqpt_sus,
			String eqpt_frdt, String eqpt_todt, String eqpt_nPara, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpSession s1,
			HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", s1.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("eqpt_status", eqpt_status);
		model.put("eqpt_sus", eqpt_sus);
		model.put("eqpt_frdt", eqpt_frdt);
		model.put("eqpt_todt", eqpt_todt);
		model.put("eqpt_unit", eqpt_nPara);
		return new ModelAndView("mms_new_eqpt_details_searchTiles");
	}

	@RequestMapping(value = "/admin/ApproveNewEqptDetails", method = RequestMethod.POST)
	public ModelAndView ApproveNewEqptDetails(@ModelAttribute("issue_sus_no") String issue_sus_no, String census_no,
			String type_of_hldg, String iv_no, String reg_count, String reg_no_cmb, String reg_no_ser, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session1) {

		Boolean val = roledao.ScreenRedirect("mms_new_eqpt_details_search", session1.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String username = session1.getAttribute("username").toString();

		// MSTR & MSTR_DETL START
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		String hqlUpdate = "update MMS_TB_REGN_MSTR c set c.op_status = :op_status, c.data_app_by = :data_app_by, c.data_app_date = :data_app_date,c.data_upd_by=:data_upd_by,c.data_upd_date=:data_upd_date where c.issue_sus_no = :issue_sus_no and c.census_no = :census_no and c.type_of_hldg = :type_of_hldg and c.rv_no = :rv_no and c.op_status = :op_statusPending";
		int app = session.createQuery(hqlUpdate).setString("op_status", "1").setString("data_app_by", username)
				.setTimestamp("data_app_date", new Date()).setString("data_upd_by", username)
				.setTimestamp("data_upd_date", new Date()).setString("issue_sus_no", issue_sus_no)
				.setString("census_no", census_no).setString("type_of_hldg", type_of_hldg).setString("rv_no", iv_no)
				.setString("op_statusPending", "0").executeUpdate();

		String hqlUpdate2 = "update MMS_TB_REGN_MSTR_DETL c set c.op_status = :op_status, c.data_app_by = :data_app_by, c.data_app_date = :data_app_date,c.data_upd_by=:data_upd_by,c.data_upd_date=:data_upd_date where c.sus_no = :sus_no and c.census_no = :census_no and c.type_of_hldg = :type_of_hldg and c.rv_no = :rv_no and c.op_status = :op_statusPending";
		session.createQuery(hqlUpdate2).setString("op_status", "1").setString("data_app_by", username)
				.setTimestamp("data_app_date", new Date()).setString("data_upd_by", username)
				.setTimestamp("data_upd_date", new Date()).setString("sus_no", issue_sus_no)
				.setString("census_no", census_no).setString("type_of_hldg", type_of_hldg).setString("rv_no", iv_no)
				.setString("op_statusPending", "0").executeUpdate();

		tx.commit();
		// MSTR & MSTR_DETL END

		// FETCH DATA
		Session session2 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();
		Query q = session2.createQuery(
				"from MMS_TB_REGN_MSTR where issue_sus_no=:issue_sus_no and census_no=:census_no and type_of_hldg=:type_of_hldg and rv_no=:rv_no");
		q.setParameter("issue_sus_no", issue_sus_no).setParameter("census_no", census_no)
				.setParameter("type_of_hldg", type_of_hldg).setParameter("rv_no", iv_no);
		@SuppressWarnings("unchecked")
		List<MMS_TB_REGN_MSTR> list = (List<MMS_TB_REGN_MSTR>) q.list();

		String nFMNo = "";
		String nOldval = "";
		String nTMNo = "";
		String nNewV = "";
		String AuthType = "RV";
		String OStatus = "1";
		String nPara = "NEW";
		String reg_no_ser1 = reg_no_ser.trim();
		String reg_no_cmb1 = reg_no_cmb.trim();
		String fname = "";
		m2DAO.RegdTransLog(reg_no_ser1, reg_no_cmb1, list.get(0).getFrom_sus_no(), list.get(0).getPrf_code(), census_no,
				nFMNo, type_of_hldg, list.get(0).getType_of_eqpt(), nOldval, list.get(0).getTo_sus_no(), nTMNo,
				type_of_hldg, list.get(0).getType_of_eqpt(), nNewV, iv_no, list.get(0).getRv_date().toString(),
				AuthType, OStatus, nPara, fname);
		tx2.commit();
		// session.close();
		session2.close();
		if (app > 0) {
			model.put("msg", "Modification Approved Successfully");
		} else {
			model.put("msg", "Modification Approved not Successfully");
		}

		return new ModelAndView("mms_new_eqpt_details_searchTiles");
	}
	// (4)-> (SEARCH NEW EQPT SCREEN METHODS END)

	// (5)-> (UPDATE EQPT DATA SCREEN METHODS START)
	@RequestMapping(value = "/admin/mms_list_regn_no", method = RequestMethod.POST)
	public ModelAndView mms_list_regn_no(@ModelAttribute("m3_sus") String m3_sus, String m3_cen, String m3_hldg,
			String m3_regnseq, String m3_prf, String m3_unit, ModelMap model, HttpSession session) {

		Boolean val = roledao.ScreenRedirect("mms_eqpt_modify_update", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (m3_regnseq != "") {
			if (validation.checkRegn_Seq_NoLength(m3_regnseq) == false) {
				model.put("msg", validation.Regn_Seq_NoMSG);
				return new ModelAndView("redirect:mms_eqpt_modify_update");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			m3_sus = roleSusNo;
		}

		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		model.put("r_1", l1);
		model.put("ml_1", m1.getDomainListingValues("TYPEOFHOLDING", "A"));
		model.put("ml_2", m1.getDomainListingData("SERVICIABILITY"));
		model.put("m_1", m2DAO.mms_list_regn_no(m3_sus, m3_cen, m3_hldg, m3_regnseq));
		model.put("m_2", m3_sus);
		model.put("m_3", m3_cen);
		model.put("m_4", m3_hldg);
		model.put("m_5", m3_regnseq);
		model.put("m_6", m3_prf);
		model.put("m_7", m3_unit);
		return new ModelAndView("mms_eqpt_modify_updateTiles");
	}

	@RequestMapping(value = "/editdata_eqpt", method = RequestMethod.POST)
	@ResponseBody
	public List<String> editdata_eqpt(String r1, String r2, HttpSession s1) {

		List<String> list = m2DAO.getEqptData(r1, r2);

		if (list.get(0) != "") {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "//ChangeEqptReg", method = RequestMethod.POST)
	public @ResponseBody String ChangeEqptReg(String regn_seq_no, String eqpt_regn_no, String service_status,
			String barrel1_detl, String barrel2_detl, String barrel3_detl, String barrel4_detl, String spl_remarks,
			HttpSession session1) {

		if (eqpt_regn_no != "") {
			if (validation.checkEqpt_Regn_NoLength(eqpt_regn_no) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.eqpt_regn_noMSG;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (barrel1_detl != "") {
			if (validation.checkBarrel1_DetlLength(barrel1_detl) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.Barrel1_DetlMSG;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (barrel2_detl != "") {
			if (validation.checkBarrel2_DetlLength(barrel2_detl) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.Barrel2_DetlMSG;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (barrel3_detl != "") {
			if (validation.checkBarrel3_DetlLength(barrel3_detl) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.Barrel3_DetlMSG;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (barrel4_detl != "") {
			if (validation.checkBarrel4_DetlLength(barrel4_detl) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.Barrel4_DetlMSG;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		if (spl_remarks != "") {
			if (validation.checkRemarksLength(spl_remarks) == false) {
				// model.put("msg",validation.Regn_Seq_NoMSG);
				return validation.remarksMSGTMS;
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}

		Boolean val = roledao.ScreenRedirect("mms_eqpt_modify_update", session1.getAttribute("roleid").toString());
		if (val == false) {
			// return new ModelAndView("AccessTiles");
			return "Eqpt Data Successfully Not Updated";
		}

		// FETCH DATA
		Session session2 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();
		Query q = session2.createQuery("from MMS_TB_REGN_MSTR_DETL where regn_seq_no=:regn_seq_no");
		q.setParameter("regn_seq_no", regn_seq_no);
		@SuppressWarnings("unchecked")
		List<MMS_TB_REGN_MSTR_DETL> list = (List<MMS_TB_REGN_MSTR_DETL>) q.list();

		String nFSUSNo = list.get(0).getFrom_sus_no();
		String nFPRF = list.get(0).getPrf_code();
		String nFCensus = list.get(0).getCensus_no();
		String nFHldType = list.get(0).getType_of_hldg();
		String nFEqptType = list.get(0).getType_of_eqpt();
		String nTSUSNo = list.get(0).getTo_sus_no();
		String nTHldType = nFHldType;
		String nTEqptType = nFEqptType;
		String nAuthNo = list.get(0).getRv_no();
		String nAuthType = "RV";
		String nOStatus = "1";

		String nFMNo = "";
		String nTMNo = "";
		String nPara = "MOD";
		String nNew;
		String nOldval;
		String fname = "";

		String service_status_old = list.get(0).getService_status();
		String barrel1_detl_old = list.get(0).getBarrel1_detl();
		String barrel2_detl_old = list.get(0).getBarrel2_detl();
		String barrel3_detl_old = list.get(0).getBarrel3_detl();
		String barrel4_detl_old = list.get(0).getBarrel4_detl();

		if (service_status_old != service_status) {
			nOldval = service_status_old;
			nNew = service_status;
			m2DAO.RegdTransLog(regn_seq_no, eqpt_regn_no, nFSUSNo, nFPRF, nFCensus, nFMNo, nFHldType, nFEqptType,
					nOldval, nTSUSNo, nTMNo, nTHldType, nTEqptType, nNew, nAuthNo, list.get(0).getRv_date().toString(),
					nAuthType, nOStatus, nPara, fname);
		}

		ArrayList<String> fnew = new ArrayList<String>();
		fnew.add(barrel1_detl);
		fnew.add(barrel2_detl);
		fnew.add(barrel3_detl);
		fnew.add(barrel4_detl);
		nNew = String.join(":", fnew);

		ArrayList<String> fold = new ArrayList<String>();

		if (barrel1_detl_old != barrel1_detl) {
			fold.add(barrel1_detl_old);
		}

		if (barrel2_detl_old != barrel2_detl) {
			fold.add(barrel2_detl_old);
		}

		if (barrel3_detl_old != barrel3_detl) {
			fold.add(barrel3_detl_old);
		}

		if (barrel4_detl_old != barrel4_detl) {
			fold.add(barrel4_detl_old);
		}

		if (fold != null) {
			nOldval = String.join(":", fold);
			m2DAO.RegdTransLog(regn_seq_no, eqpt_regn_no, nFSUSNo, nFPRF, nFCensus, nFMNo, nFHldType, nFEqptType,
					nOldval, nTSUSNo, nTMNo, nTHldType, nTEqptType, nNew, nAuthNo, list.get(0).getRv_date().toString(),
					nAuthType, nOStatus, nPara, fname);
		}

		tx2.commit();
		session2.close();

		String username = session1.getAttribute("username").toString();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		String hqlUpdate2 = "update MMS_TB_REGN_MSTR_DETL c set c.service_status = :service_status, c.barrel1_detl = :barrel1_detl, c.barrel2_detl = :barrel2_detl,c.barrel3_detl = :barrel3_detl,c.barrel4_detl = :barrel4_detl,c.spl_remarks = :spl_remarks,c.data_upd_by=:data_upd_by,c.data_upd_date=:data_upd_date where c.regn_seq_no = :regn_seq_no";
		session.createQuery(hqlUpdate2).setString("service_status", service_status)
				.setString("barrel1_detl", barrel1_detl).setString("barrel2_detl", barrel2_detl)
				.setString("barrel3_detl", barrel3_detl).setString("barrel4_detl", barrel4_detl)
				.setString("spl_remarks", spl_remarks).setString("data_upd_by", username)
				.setTimestamp("data_upd_date", new Date()).setString("regn_seq_no", regn_seq_no).executeUpdate();

		tx.commit();
		session.close();

		return "Eqpt Data Successfully Updated";
	}

	@RequestMapping(value = "/strip_inspAction", method = RequestMethod.POST)
	public @ResponseBody String strip_inspAction(HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			MMS_TB_STRIP_INSP_RECOIL s = new MMS_TB_STRIP_INSP_RECOIL();
			int id = Integer.parseInt(request.getParameter("si_id"));
			String eqpt_regn_no = request.getParameter("eqpt_regn_no2");
			String recoil_sys_regd_no = request.getParameter("recoil_sys_regd_no" + id);
			String periodicity = request.getParameter("periodicity" + id);
			Date done_dt = convertStringtoDate(request.getParameter("done_dt" + id));
			Date due_dt = convertStringtoDate(request.getParameter("due_dt" + id));

			s.setEqpt_regn_no(eqpt_regn_no);
			s.setRecoil_sys_regd_no(recoil_sys_regd_no);
			s.setDone_dt(done_dt);
			s.setDue_dt(due_dt);
			s.setPeriodicity(periodicity);
			s.setCreated_by(username);
			s.setCreated_on(new Date());

			sessionHQL.save(s);
			tx.commit();
			return "Data Submitted";
		} catch (RuntimeException e) {
			tx.rollback();

			return "Server side Error";
		} finally {
			sessionHQL.close();
		}
	}

	@RequestMapping(value = "/Barrel_detailsAction", method = RequestMethod.POST)
	public @ResponseBody String Barrel_detailsAction(HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			int id = Integer.parseInt(request.getParameter("b_id"));
			String barrel_regn_no = request.getParameter("barrel_regn_no");
			String eqpt_regn_no1 = request.getParameter("eqpt_regn_no1");
			String op_clear = request.getParameter("op_clear");
			Date op_clear_dt = convertStringtoDate(request.getParameter("op_clear_dt"));
			String wksp_name = request.getParameter("wksp_name");
			Date wksp_in_dt = convertStringtoDate(request.getParameter("wksp_in_dt"));
			String rifling_vertical = request.getParameter("rifling_vertical");
			String rifling_horizontal = request.getParameter("rifling_horizontal");
			String efc = request.getParameter("efc");
			String total_rds = request.getParameter("total_rds");
			String qtr_of_life = request.getParameter("qtr_of_life");
			Date dt_last_fired = convertStringtoDate(request.getParameter("dt_last_fired"));

			MMS_TB_BARREL_CHILD_DTL b = new MMS_TB_BARREL_CHILD_DTL();
			b.setId(id);
			b.setBarrel_regn_no(barrel_regn_no);
			b.setEqpt_regn_no(eqpt_regn_no1);
			b.setOp_clear(op_clear);
			b.setOp_clear_dt(op_clear_dt);
			b.setWksp_name(wksp_name);
			b.setWksp_in_dt(wksp_in_dt);
			b.setRifling_vertical(rifling_vertical);
			b.setRifling_horizontal(rifling_horizontal);
			b.setEfc(efc);
			b.setQtr_of_life(qtr_of_life);
			b.setTotal_rds(total_rds);
			b.setDt_last_fired(dt_last_fired);
			b.setSus_no(roleSusNo);
			b.setCreated_by(username);
			b.setCreated_on(new Date());

			sessionHQL.saveOrUpdate(b);
			tx.commit();
			return "Data Submitted";
		} catch (RuntimeException e) {
			tx.rollback();

			return "Server side Error";
		} finally {
			sessionHQL.close();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOH_details", method = RequestMethod.POST)
	public @ResponseBody MMS_TB_OH_CHILD_DTL getOH_details(HttpServletRequest request) {
		String oh_type = request.getParameter("oh_type");
		String eqpt_regn_no = request.getParameter("eqpt_regn_no");
		String hql = "";
		hql = " from MMS_TB_OH_CHILD_DTL where oh_type=:oh_type and eqpt_regn_no =:eqpt_regn_no order by id desc";
		List<MMS_TB_OH_CHILD_DTL> b = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(hql);
			q.setParameter("oh_type", oh_type);
			q.setParameter("eqpt_regn_no", eqpt_regn_no);
			b = (List<MMS_TB_OH_CHILD_DTL>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();

			return null;
		}
		if (b.size() > 0) {
			return b.get(0);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/oh_detailsAction", method = RequestMethod.POST)
	public @ResponseBody String oh_detailsAction(HttpServletRequest request, HttpSession session) {
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			int id = Integer.parseInt(request.getParameter("oh_id"));
			String oh_type = request.getParameter("oh_type");
			Date oh_due_dt = convertStringtoDate(request.getParameter("oh_due_dt"));
			Date oh_done_dt = convertStringtoDate(request.getParameter("oh_done_dt"));
			String eqpt_regn_no = request.getParameter("eqpt_regn_no3");
			Date dispatch_dt = convertStringtoDate(request.getParameter("dispatch_dt"));
			String wksp_name = request.getParameter("wksp_name_oh");
			Date wksp_in_dt = convertStringtoDate(request.getParameter("wksp_in_dt_oh"));
			Date boh_compl_dt = convertStringtoDate(request.getParameter("boh_compl_dt"));
			Date gun_recd_dt = convertStringtoDate(request.getParameter("gun_recd_dt"));
			Date dt_of_intro = convertStringtoDate(request.getParameter("dt_of_intro"));

			MMS_TB_OH_CHILD_DTL b = new MMS_TB_OH_CHILD_DTL();
			b.setId(id);
			b.setOh_type(oh_type);
			b.setOh_due_dt(oh_due_dt);
			b.setOh_done_dt(oh_done_dt);
			b.setEqpt_regn_no(eqpt_regn_no);
			b.setWksp_name(wksp_name);
			b.setWksp_in_dt(wksp_in_dt);
			b.setDt_of_intro(dt_of_intro);
			b.setDispatch_dt(dispatch_dt);
			b.setBoh_compl_dt(boh_compl_dt);
			b.setGun_recd_dt(gun_recd_dt);
			b.setSus_no(roleSusNo);
			b.setCreated_by(username);
			b.setCreated_on(new Date());

			sessionHQL.saveOrUpdate(b);
			tx.commit();
			return "Data Submitted";
		} catch (RuntimeException e) {
			tx.rollback();

			return "Server side Error";
		} finally {
			sessionHQL.close();
		}
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(value = "/getBarrel_details", method = RequestMethod.POST)
	 * public @ResponseBody MMS_TB_BARREL_CHILD_DTL
	 * getBarrel_details(HttpServletRequest request){ String eqpt_regn_no
	 * =request.getParameter("eqpt_regn_no");
	 * 
	 * String hql="";
	 * hql=" from MMS_TB_BARREL_CHILD_DTL where eqpt_regn_no =:eqpt_regn_no order by id desc"
	 * ; List<MMS_TB_BARREL_CHILD_DTL> b = null; Session session =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx =
	 * session.beginTransaction(); try { Query q= session.createQuery(hql);
	 * q.setParameter("eqpt_regn_no", eqpt_regn_no); b =
	 * (List<MMS_TB_BARREL_CHILD_DTL>) q.list(); tx.commit(); session.close(); }
	 * catch (Exception e) { tx.rollback();
	 * 
	 * return null; } if(b.size()>0) { return b.get(0); }else { return null; } }
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getstrip_insp_details", method = RequestMethod.POST)
	public @ResponseBody List<MMS_TB_STRIP_INSP_RECOIL> getstrip_insp_details(HttpServletRequest request) {
		String eqpt_regn_no = request.getParameter("eqpt_regn_no");
		String hql = "";
		hql = " from MMS_TB_STRIP_INSP_RECOIL where eqpt_regn_no=:eqpt_regn_no";
		List<MMS_TB_STRIP_INSP_RECOIL> s = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(hql);
			q.setParameter("eqpt_regn_no", eqpt_regn_no);
			s = (List<MMS_TB_STRIP_INSP_RECOIL>) q.list();
			tx.commit();
			session.close();
			return s;
		} catch (Exception e) {
			session.getTransaction().rollback();

			return null;
		}
	}

	public Date convertStringtoDate(String date_val) {

		SimpleDateFormat formatter_dash = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		SimpleDateFormat formatter_slash = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		Date date_date = null;
		try {
			if (date_val.contains("/")) {
				date_date = formatter_slash.parse(date_val);
			} else if (date_val.contains("-")) {
				date_date = formatter_dash.parse(date_val);
			} else {
				date_date = null;
			}
		} catch (Exception e) {
			date_date = null;
		}
		return date_date;
	}
	// (5)-> (UPDATE EQPT DATA SCREEN METHODS END)

	// (6)-> (UPDATE REGD NO SCREEN METHODS START)
	@RequestMapping(value = "/mms_update_regn_no", method = RequestMethod.POST)
	public @ResponseBody String mms_update_regn_no(String regn_no, String regn_seq_no, String type_of_hldg,
			HttpSession session) {
		Boolean val = roledao.ScreenRedirect("mms_regno_update", session.getAttribute("roleid").toString());

		if (val == false) {
			return "update not Successfully";
		}
		String nFileName = mmsCommonDAO.getRegdTransName(type_of_hldg);
		return m2DAO.mms_update_regnno(regn_no, regn_seq_no, nFileName);
	}

	// will replaced in optimization......
	@RequestMapping(value = "/admin/mms_list_regn_no2", method = RequestMethod.POST)
	public ModelAndView mms_list_regn_no2(@ModelAttribute("m3_sus") String m3_sus, String m3_cen, String m3_hldg,
			String m3_regnseq, String m3_prf, String m3_unit, ModelMap model, HttpSession session) {
		Boolean val = roledao.ScreenRedirect("mms_regno_update", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (m3_regnseq != "") {
			if (validation.checkRegn_Seq_NoLength(m3_regnseq) == false) {
				model.put("msg", validation.Regn_Seq_NoMSG);
				return new ModelAndView("redirect:mms_regno_update");
			}
			// Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no,
			// sessionA).get(0));
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		model.put("r_1", l1);

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			m3_sus = roleSusNo;
		}
		model.put("ml_1", m1.getDomainListingValues("TYPEOFHOLDING", "A"));
		model.put("m_1", m2DAO.mms_list_regn_no(m3_sus, m3_cen, m3_hldg, m3_regnseq));
		model.put("m_2", m3_sus);
		model.put("m_3", m3_cen);
		model.put("m_4", m3_hldg);
		model.put("m_5", m3_regnseq);
		model.put("m_6", m3_prf);
		model.put("m_7", m3_unit);
		return new ModelAndView("mms_regno_updateTiles");
	}

	// (6)-> (UPDATE REGD NO SCREEN METHODS END)

	// UNIT TRANSFER SCREENS START (7)(8)(9)(10)

	@RequestMapping(value = "/mms_list_regn_no_tfr", method = RequestMethod.POST)
	@ResponseBody
	public List<String> mms_list_regn_no_tfr(String sus_no, String census_no, String type_of_hldg, String regn_seq_no,
			HttpSession s1) {
		String roleAccess = s1.getAttribute("roleAccess").toString();
		String roleSusNo = s1.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = m2DAO.mms_list_regn_no(sus_no, census_no, type_of_hldg, regn_seq_no);
		if (list.get(0) != "") {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/mms_inter_unit_tfrAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mms_inter_unit_tfrAction(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session1,
			@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1) {
		String nParaPage = request.getParameter("Para");
		Boolean val = null;

		String rv_no = request.getParameter("rv_no");

		if (nParaPage.equals("CSTK")) {
			val = roledao.ScreenRedirect("mms_chg_of_stk", session1.getAttribute("roleid").toString());
		}

		if (nParaPage.equals("ITFR")) {
			val = roledao.ScreenRedirect("mms_chg_of_itfrstk", session1.getAttribute("roleid").toString());

			if (rv_no.isEmpty()) {
				model.put("msg", "Please Enter the RV No.");
				return new ModelAndView("redirect:mms_chg_of_itfrstk");
			}

			if (validation.checkRv_NoLength(rv_no) == false) {
				model.put("msg", validation.rv_noMSG);
				return new ModelAndView("redirect:mms_chg_of_itfrstk");
			}
		}

		if (nParaPage.equals("IDEP")) {
			val = roledao.ScreenRedirect("mms_chg_of_idepstk", session1.getAttribute("roleid").toString());

			if (rv_no.isEmpty()) {
				model.put("msg", "Please Enter the RV No.");
				return new ModelAndView("redirect:mms_chg_of_idepstk");
			}

			if (validation.checkRv_NoLength(rv_no) == false) {
				model.put("msg", validation.rv_noMSG);
				return new ModelAndView("redirect:mms_chg_of_idepstk");
			}
		}

		if (nParaPage.equals("DDEP")) {
			val = roledao.ScreenRedirect("mms_chg_of_ddepstk", session1.getAttribute("roleid").toString());
		}

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String extension = "";
		String fname = "";
		String username = session1.getAttribute("username").toString();
		String eqpt_regn_no = request.getParameter("eqpt_regn_no");
		String regn_seq_no = request.getParameter("regn_seq_no");
		String from_sus_no = request.getParameter("from_sus_no");
		String prf_code = request.getParameter("prf_code");
		String census_no = request.getParameter("census_no");

		String type_of_hldg = request.getParameter("type_of_hldg");
		String type_of_eqpt = request.getParameter("type_of_eqpt");
		String to_sus_no = request.getParameter("to_sus_no");
		String to_hld_type = request.getParameter("to_hld_type");
		String to_eqpt_type = request.getParameter("to_eqpt_type");

		String roleAccess = session1.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("MISO")) {
			to_hld_type = type_of_hldg;
			to_eqpt_type = type_of_eqpt;
		}

		String rv_date = request.getParameter("rv_date");
		String nF = request.getParameter("doc_up");

		if (!nF.equalsIgnoreCase("NIL")) {

			String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
			if (!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("png".toUpperCase())
					&& !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {

				model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
				if (nParaPage.equals("ITFR")) {
					return new ModelAndView("redirect:mms_chg_of_itfrstk");
				}
				if (nParaPage.equals("IDEP")) {
					return new ModelAndView("redirect:mms_chg_of_idepstk");
				}
			}
			if (!doc_upload1.isEmpty()) {
				// code modify by Paresh on 05/05/2020
				try {
					int userid = Integer.parseInt(session1.getAttribute("userId").toString());
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = doc_upload1.getBytes();
					String mmsFilePath = session1.getAttribute("mmsFilePath").toString();
					File dir = new File(mmsFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = doc_upload1.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userid + "_MMSDOC." + extension;

					if (validation.checkImageAnmlLength(fname) == false) {
						model.put("msg", validation.image_anmlMSG);
						if (nParaPage.equals("ITFR")) {
							return new ModelAndView("redirect:mms_chg_of_itfrstk");
						}
						if (nParaPage.equals("IDEP")) {
							return new ModelAndView("redirect:mms_chg_of_idepstk");
						}

					}

					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					// rs.setUpload_file_name(fname);
				} catch (Exception e) {
				}
			}

		}

		String nFMNo = "";
		String nOldval = "";
		String nTMNo = "";
		String nNewV = "";
		String AuthType = "RV";
		String OStatus = "1";
		String nTfrStatus = "C";
		String nPara = nParaPage.toUpperCase();

		String nFileName = mmsCommonDAO.getRegdTransName(type_of_hldg);

		String TFileName = mmsCommonDAO.getRegdTransName(to_hld_type);

		m2DAO.RegdTransUpdate(regn_seq_no, eqpt_regn_no, from_sus_no, to_sus_no, to_hld_type, to_eqpt_type, OStatus,
				nTfrStatus, username, nPara, nFileName);

		if (!nFileName.equals(TFileName)) {
			if (nParaPage.equals("IDEP") || nParaPage.equals("CSTK") || nParaPage.equals("ITFR")) {
				if (!type_of_hldg.equalsIgnoreCase(to_hld_type)) {
					mmsCommonDAO.RegdDataTransfer(regn_seq_no, from_sus_no, type_of_hldg, to_sus_no, to_hld_type, nPara,
							nFileName, TFileName);
				}
			}
		}

		m2DAO.RegdTransLog(regn_seq_no, eqpt_regn_no, from_sus_no, prf_code, census_no, nFMNo, type_of_hldg,
				type_of_eqpt, nOldval, to_sus_no, nTMNo, to_hld_type, to_eqpt_type, nNewV, rv_no, rv_date, AuthType,
				OStatus, nPara, fname);

		if (nParaPage.equals("CSTK")) {
			model.put("msg", "Change of Type of Stock Successful");
			return new ModelAndView("redirect:mms_chg_of_stk");
		}

		if (nParaPage.equals("ITFR")) {
			model.put("msg", "Inter Unit Transfer Successful");
			return new ModelAndView("redirect:mms_chg_of_itfrstk");
		}

		if (nParaPage.equals("IDEP")) {
			model.put("msg", "Deposit to Depot Successful");
			return new ModelAndView("redirect:mms_chg_of_idepstk");
		}

		if (nParaPage.equals("DDEP")) {
			model.put("msg", "Eqpt Transfer Successful");
			return new ModelAndView("redirect:mms_chg_of_ddepstk");
		}
		return null;
	}
	// UNIT TRANSFER SCREENS END (7)(8)(9)(10)

	@RequestMapping(value = "/mms_chg_of_itfrstk_mms", method = RequestMethod.GET)
	public ModelAndView mms_chg_of_itfrstk_mms(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_chg_of_itfrstk_mms", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		String roleAccess = session.getAttribute("roleAccess").toString();
		// if (roleAccess.equals("MISO")) {
		Mmap.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "A:R", session));
		// }
		Mmap.put("roleAccess", roleAccess);
		return new ModelAndView("mms_chg_of_itfr_mmsstkTiles");
	}
	//

	@RequestMapping(value = "/mms_inter_unit_tfr_mmsAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mms_inter_unit_tfr_mmsAction(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session1,
			@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1) {
		String nParaPage = request.getParameter("Para");
		Boolean val = null;

		String rv_no = request.getParameter("hto_no");
		int update_id = Integer.parseInt(request.getParameter("update_id"));

		if (nParaPage.equals("CSTK")) {
			val = roledao.ScreenRedirect("mms_chg_of_stk", session1.getAttribute("roleid").toString());
		}

		if (nParaPage.equals("ITFR")) {
			val = roledao.ScreenRedirect("mms_chg_of_itfrstk", session1.getAttribute("roleid").toString());

			if (rv_no.isEmpty()) {
				model.put("msg", "Please Enter the RV No.");
				return new ModelAndView("redirect:mms_chg_of_itfrstk");
			}

			if (validation.checkRv_NoLength(rv_no) == false) {
				model.put("msg", validation.rv_noMSG);
				return new ModelAndView("redirect:mms_chg_of_itfrstk");
			}
		}

		if (nParaPage.equals("IDEP")) {
			val = roledao.ScreenRedirect("mms_chg_of_idepstk", session1.getAttribute("roleid").toString());

			if (rv_no.isEmpty()) {
				model.put("msg", "Please Enter the RV No.");
				return new ModelAndView("redirect:mms_chg_of_idepstk");
			}

			if (validation.checkRv_NoLength(rv_no) == false) {
				model.put("msg", validation.rv_noMSG);
				return new ModelAndView("redirect:mms_chg_of_idepstk");
			}
		}

		if (nParaPage.equals("DDEP")) {
			val = roledao.ScreenRedirect("mms_chg_of_ddepstk", session1.getAttribute("roleid").toString());
		}

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String extension = "";
		String fname = "";
		String username = session1.getAttribute("username").toString();
		String eqpt_regn_no = request.getParameter("eqpt_regn_no");
		String regn_seq_no = request.getParameter("regn_seq_no");
		String from_sus_no = request.getParameter("from_sus_no");
		String prf_code = request.getParameter("prf_code");
		String census_no = request.getParameter("census_no");

		String type_of_hldg = request.getParameter("type_of_hldg");
		String type_of_eqpt = request.getParameter("type_of_eqpt");
		String to_sus_no = request.getParameter("to_sus_no");
		String to_hld_type = request.getParameter("to_hld_type");
		String to_eqpt_type = request.getParameter("to_eqpt_type");

		String imagepath = request.getParameter("file_info");

		String roleAccess = session1.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("MISO")) {
			to_hld_type = type_of_hldg;
			to_eqpt_type = type_of_eqpt;
		}

		String rv_date = request.getParameter("hto_date");

		String nF = request.getParameter("doc_up");
		if (!doc_upload1.isEmpty()) {

			if (!nF.equalsIgnoreCase("NIL")) {

				String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
				if (!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("png".toUpperCase())
						&& !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {

					model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
					if (nParaPage.equals("ITFR")) {
						return new ModelAndView("redirect:mms_chg_of_itfrstk");
					}
					if (nParaPage.equals("IDEP")) {
						return new ModelAndView("redirect:mms_chg_of_idepstk");
					}
				}
				if (!doc_upload1.isEmpty()) {
					// code modify by Paresh on 05/05/2020
					try {
						int userid = Integer.parseInt(session1.getAttribute("userId").toString());
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						byte[] bytes = doc_upload1.getBytes();
						String mmsFilePath = session1.getAttribute("mmsFilePath").toString();
						File dir = new File(mmsFilePath);
						if (!dir.exists())
							dir.mkdirs();
						String filename = doc_upload1.getOriginalFilename();
						int i = filename.lastIndexOf('.');
						if (i >= 0) {
							extension = filename.substring(i + 1);
						}
						fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
								+ "_" + userid + "_MMSDOC." + extension;

						if (validation.checkImageAnmlLength(fname) == false) {
							model.put("msg", validation.image_anmlMSG);
							if (nParaPage.equals("ITFR")) {
								return new ModelAndView("redirect:mms_chg_of_itfrstk");
							}
							if (nParaPage.equals("IDEP")) {
								return new ModelAndView("redirect:mms_chg_of_idepstk");
							}

						}

						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						// rs.setUpload_file_name(fname);
					} catch (Exception e) {
					}
				}

			}
		} else {
			fname = request.getParameter("file_info");
		}

		String nFMNo = "";
		String nOldval = "";
		String nTMNo = "";
		String nNewV = "";
		String AuthType = "RV";
		String OStatus = "1";
		String nTfrStatus = "C";
		String nPara = nParaPage.toUpperCase();

		String nFileName = mmsCommonDAO.getRegdTransName(type_of_hldg);

		String TFileName = mmsCommonDAO.getRegdTransName(to_hld_type);

		String level_in_hierarchy = m2DAO.getsus_no_list(to_sus_no);

		Session sessionHql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHql.beginTransaction();
		try {

			if (update_id == 0) {
				Query qc = sessionHql.createQuery(
						"select count(id) from MMS_TB_CHILD_REGN_MSTR_DETL where from_sus_no=:from_sus_no and to_sus_no=:to_sus_no and regn_seq_no=:regn_seq_no and eqpt_regn_no=:eqpt_regn_no and status=:status");

				qc.setString("from_sus_no", from_sus_no).setString("to_sus_no", to_sus_no)
						.setString("regn_seq_no", regn_seq_no).setString("eqpt_regn_no", eqpt_regn_no)
						.setInteger("status", 0);
				long c = (Long) qc.uniqueResult();

				if (c > 0) {
					msg = "Data Already Exists !!";
					model.put("msg", msg);
					return new ModelAndView("redirect:mms_chg_of_itfrstk");
				}
			}

			if (update_id == 0) {
				MMS_TB_CHILD_REGN_MSTR_DETL dtl = new MMS_TB_CHILD_REGN_MSTR_DETL();
				dtl.setRegn_seq_no(regn_seq_no);
				dtl.setEqpt_regn_no(eqpt_regn_no);
				dtl.setFrom_sus_no(from_sus_no);
				dtl.setTo_sus_no(to_sus_no);
				dtl.setTo_hld_type(to_hld_type);
				dtl.setTo_eqpt_type(to_eqpt_type);
				dtl.setOp_status(OStatus);
				dtl.setTfr_status(nTfrStatus);
				dtl.setnPara(nPara);
				dtl.setnFileName(nFileName);
				dtl.setType_of_hldg(type_of_hldg);
				dtl.setTFileName(TFileName);
				dtl.setPrf_code(prf_code);
				dtl.setCensus_no(census_no);
				dtl.setnFMNo(nFMNo);
				dtl.setType_of_eqpt(type_of_eqpt);
				dtl.setnOldval(nOldval);
				dtl.setnTMNo(nTMNo);
				dtl.setnNewV(nNewV);
				dtl.setRv_no(rv_no);
				dtl.setAuthType(AuthType);
				dtl.setOp_status(OStatus);
				dtl.setFname(fname);
				dtl.setRv_date(rv_date);
				dtl.setUsername(username);
				dtl.setNxt_level_in_hierarchy_sus_no(level_in_hierarchy);
				dtl.setStatus(0);
				sessionHql.save(dtl);
				sessionHql.getTransaction().commit();
			} else {
				String hql = "update MMS_TB_CHILD_REGN_MSTR_DETL set  " + "regn_seq_no=:regn_seq_no, "
						+ "type_of_hldg=:type_of_hldg, " + "from_sus_no=:from_sus_no, " + "to_sus_no=:to_sus_no, "
						+ "op_status=:op_status, " + "tfr_status=:tfr_status, " + "data_upd_by=:data_upd_by, "
						+ "data_upd_date=:data_upd_date, " + "tfilename=:tfilename, " + "prf_code=:prf_code, "
						+ "census_no=:census_no, " + "nfmno=:nfmno, " + "noldval=:noldval, " + "ntmno=:ntmno, "
						+ "nnewv=:nnewv, " + "rv_no=:rv_no, " + "rv_date=:rv_date, " + "authtype=:authtype, "
						+ "fname=:fname, " + "eqpt_regn_no=:eqpt_regn_no, " + "to_hld_type=:to_hld_type, "
						+ "to_eqpt_type=:to_eqpt_type, " + "npara=:npara, " + "nfilename=:nfilename, "
						+ "username=:username, " + "nxt_level_in_hierarchy_sus_no=:nxt_level_in_hierarchy_sus_no "
						+ "where id=:update_id";
				Query query = sessionHql.createQuery(hql).setString("regn_seq_no", regn_seq_no)
						.setString("type_of_hldg", type_of_hldg).setString("from_sus_no", from_sus_no)
						.setString("to_sus_no", to_sus_no).setString("op_status", OStatus)
						.setString("tfr_status", nTfrStatus).setString("data_upd_by", username)
						.setDate("data_upd_date", new Date()).setString("tfilename", TFileName)
						.setString("prf_code", prf_code).setString("census_no", census_no).setString("nfmno", nFMNo)
						.setString("noldval", nOldval).setString("ntmno", nTMNo).setString("nnewv", nNewV)
						.setString("rv_no", rv_no).setString("rv_date", rv_date).setString("authtype", AuthType)
						.setString("fname", fname).setString("eqpt_regn_no", eqpt_regn_no)
						.setString("to_hld_type", to_hld_type).setString("to_eqpt_type", to_eqpt_type)
						.setString("npara", nPara).setString("nfilename", nFileName).setString("username", username)
						.setString("nxt_level_in_hierarchy_sus_no", level_in_hierarchy)
						.setInteger("update_id", update_id);

				msg = query.executeUpdate() > 0 ? "update" : "0";
				if (msg.equals("0")) {
					model.put("msg", "Inter Unit Transfer Not Updated Successfully.");
					return new ModelAndView("redirect:track_iut_status_mms");
				} else {
					
					model.put("msg", "Inter Unit Transfer Updated Successfully.");				
					return new ModelAndView("redirect:track_iut_status_mms");
				}

			}

		} catch (Exception e) {
			sessionHql.getTransaction().rollback();
			return null;
		} finally {
			sessionHql.close();
		}

		if (nParaPage.equals("ITFR")) {
			model.put("msg", "Inter Unit Transfer Save  Successful");
			return new ModelAndView("redirect:mms_chg_of_itfrstk");
		}

		return null;
	}

	@RequestMapping(value = "/admin/track_iut_status_mms", method = RequestMethod.GET)
	public ModelAndView track_iut_status_mms(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleName = session.getAttribute("role").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status_mms", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		ArrayList<ArrayList<String>> list = m2DAO.trackiutstatus_mms(roleSusNo, roleType, roleAccess,0);
		Mmap.put("list", list);

		Mmap.put("date", date1);
		Mmap.put("msg", msg);

		return new ModelAndView("mms_search_chg_of_itfrstkTiles");
	}

	@RequestMapping(value = "/admin/approve_iut_mms", method = RequestMethod.POST)
	public ModelAndView approve_iut_mms(@ModelAttribute("id2") int id, HttpSession sessionA, HttpServletRequest request,
			ModelMap Mmap, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("approve_iut_mms", roleid);
		/*
		 * if (val == false) { return new ModelAndView("AccessTiles"); }
		 */
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		MMS_TB_CHILD_REGN_MSTR_DETL child_reg_mst_dtl = (MMS_TB_CHILD_REGN_MSTR_DETL) sessionHQL
				.get(MMS_TB_CHILD_REGN_MSTR_DETL.class, id);

		String regn_seq_no = child_reg_mst_dtl.getRegn_seq_no();
		String eqpt_regn_no = child_reg_mst_dtl.getEqpt_regn_no();
		String from_sus_no = child_reg_mst_dtl.getFrom_sus_no();
		String to_sus_no = child_reg_mst_dtl.getTo_sus_no();
		String to_hld_type = child_reg_mst_dtl.getTo_hld_type();
		String to_eqpt_type = child_reg_mst_dtl.getTo_eqpt_type();
		String type_of_hldg = child_reg_mst_dtl.getType_of_hldg();
		String type_of_eqpt = child_reg_mst_dtl.getType_of_eqpt();
		String prf_code = child_reg_mst_dtl.getPrf_code();
		String census_no = child_reg_mst_dtl.getCensus_no();
		String nFileName = child_reg_mst_dtl.getnFileName();
		String nFMNo = child_reg_mst_dtl.getnFMNo();
		String nOldval = child_reg_mst_dtl.getnOldval();
		String nTMNo = child_reg_mst_dtl.getnTMNo();
		String nNewV = child_reg_mst_dtl.getnNewV();
		String AuthType = child_reg_mst_dtl.getAuthType();
		String OStatus = child_reg_mst_dtl.getOp_status();
		String nTfrStatus = child_reg_mst_dtl.getTfr_status();
		String TFileName = child_reg_mst_dtl.getTFileName();
		String nPara = child_reg_mst_dtl.getnPara();
		String nParaPage = child_reg_mst_dtl.getnPara();
		String rv_no = child_reg_mst_dtl.getRv_no();
		String rv_date = child_reg_mst_dtl.getRv_date();
		String fname = child_reg_mst_dtl.getFname();

		m2DAO.RegdTransUpdate(regn_seq_no, eqpt_regn_no, from_sus_no, to_sus_no, to_hld_type, to_eqpt_type, OStatus,
				nTfrStatus, username, nPara, nFileName);

		if (!nFileName.equals(TFileName)) {
			if (nParaPage.equals("IDEP") || nParaPage.equals("CSTK") || nParaPage.equals("ITFR")) {
				if (!type_of_hldg.equalsIgnoreCase(to_hld_type)) {
					mmsCommonDAO.RegdDataTransfer(regn_seq_no, from_sus_no, type_of_hldg, to_sus_no, to_hld_type, nPara,
							nFileName, TFileName);
				}
			}
		}

		m2DAO.RegdTransLog(regn_seq_no, eqpt_regn_no, from_sus_no, prf_code, census_no, nFMNo, type_of_hldg,
				type_of_eqpt, nOldval, to_sus_no, nTMNo, to_hld_type, to_eqpt_type, nNewV, rv_no, rv_date, AuthType,
				OStatus, nPara, fname);

		String hqlUpdate = "update MMS_TB_CHILD_REGN_MSTR_DETL set status=:status" + " where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("id", id).executeUpdate();
		if (app > 0) {
			msg = "Inter Unit Transfer Approved Successfully";
		}

		tx.commit();
		sessionHQL.close();
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:track_iut_status_mms");
	}

	@RequestMapping(value = "/admin/reject_iut_mms", method = RequestMethod.POST)
	public ModelAndView reject_iut_mms(@ModelAttribute("id1") int id, HttpSession sessionA, HttpServletRequest request,
			ModelMap Mmap, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("reject_iut_mms", roleid);
		/*
		 * if (val == false) { return new ModelAndView("AccessTiles"); }
		 */
		/*
		 * if (request.getHeader("Referer") == null) { msg = ""; }
		 */
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "update MMS_TB_CHILD_REGN_MSTR_DETL set status=:status" + " where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setInteger("id", id).executeUpdate();
		if (app > 0) {
			msg = "Data Reject Successfully";
		}
		tx.commit();
		sessionHQL.close();
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:track_iut_status_mms");
	}

	@RequestMapping(value = "/admin/update_iut")
	public ModelAndView update_iut(@ModelAttribute("id3") int updateid, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request) throws JsonProcessingException {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("update_iut", roleid);
		/*
		 * if (val == false) { return new ModelAndView("AccessTiles"); } if
		 * (request.getHeader("Referer") == null) { msg = ""; }
		 */
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("mms_inter_unit_tfr_editCMD", getMMS_TB_CHILD_REGN_MSTR_DETLid(updateid));
		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("TYPEOFEQPT"));
		model.put("ml_2", m1.getDomainDetailsValues("TYPEOFHOLDING", "A:R", sessionA));
		model.put("roleAccess", roleAccess);

		// new code as per guru
		
		
		String roleType = sessionA.getAttribute("roleType").toString();
		ArrayList<ArrayList<String>> list = m2DAO.trackiutstatus_mms(roleSusNo, roleType, roleAccess,updateid);
		model.put("list", list.get(0).get(3));

		// upto

		return new ModelAndView("mms_chg_of_itfr_editTiles");
	}

	public MMS_TB_CHILD_REGN_MSTR_DETL getMMS_TB_CHILD_REGN_MSTR_DETLid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from MMS_TB_CHILD_REGN_MSTR_DETL where id=:id");
		q.setParameter("id", id);
		MMS_TB_CHILD_REGN_MSTR_DETL list = (MMS_TB_CHILD_REGN_MSTR_DETL) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

}
