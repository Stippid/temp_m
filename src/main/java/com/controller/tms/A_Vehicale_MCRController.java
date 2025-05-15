package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.HelpDeskController.helpController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Notification.notificatioDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_UploadExcelDAO;
import com.dao.tms.MCRDAO;
import com.dao.tms.MVCRDAO;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_FMCR_HISTORY_TABLE;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_UPLOAD_DOC_MCR;
import com.models.T_Domain_Value;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class A_Vehicale_MCRController {

	@Autowired
	MCRDAO mcrdao;

	@Autowired
	public MMS_UploadExcelDAO m8DAO;

	@Autowired
	MVCRDAO addmctDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();

	@Autowired
	private notificatioDAO notif;
	helpController helpcntl = new helpController();
	NotificationController notification = new NotificationController();

	@RequestMapping(value = "/admin/mcr", method = RequestMethod.GET)
	public ModelAndView mcr(ModelMap Mmap, HttpSession session, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			ArrayList<List<String>> getmcrReportList = mcrdao.getMCRReportList(roleSusNo, roleType);
			if (getmcrReportList.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("getmcrReportList", getmcrReportList);
			} else {
				Mmap.put("list", getmcrReportList);
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mcrTiles");
	}

	@RequestMapping(value = "/admin/mcrSerach", method = RequestMethod.GET)
	public ModelAndView mcrSerach(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("roleAccess", roleAccess);
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mcrSerachTiles");
	}

	@RequestMapping(value = "/Viewmcr", method = RequestMethod.POST)
	public ModelAndView Viewmcr(@Valid @Validated @ModelAttribute("editId") int entryId, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		model.put("ml_2", getDomainListingData("A_VEH_UNSV"));
		model.put("MCRCMD", mcrdao.editmcrA(entryId));
		return new ModelAndView("MCRViewtile");
	}

	public List<T_Domain_Value> getDomainListingData(String domainid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid order by label");
		q.setParameter("domainid", domainid);
		@SuppressWarnings("unchecked")
		List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/updateMCR", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateMCR(TB_TMS_CENSUS_RETURN cp, HttpServletRequest request, HttpSession session,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date_of_cens_retrn = null;
		try {
			date_of_cens_retrn = format.parse(request.getParameter("date_of_cens_retrn1"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String sus_no = request.getParameter("sus_no");
		String ba_no = request.getParameter("ba_no");
		String vehcl_classfctn = request.getParameter("vehcl_classfctn");
		String veh_km_run_period = request.getParameter("veh_km_run_period");
		String issued_type = request.getParameter("issued_type");
		String ser_status = request.getParameter("ser_status");
		String engine_type = request.getParameter("engine_type");
		String engine_hrs_run = request.getParameter("engine_hrs_run");
		String engine_kms_run = request.getParameter("engine_kms_run");
		String aux_type = request.getParameter("aux_type");
		String aux_engn_hrs_run = request.getParameter("aux_engn_hrs_run");
		String aux_engn_clasfctn = request.getParameter("aux_engn_clasfctn");
		String main_gun_type = request.getParameter("main_gun_type");
		String main_gun_qr = request.getParameter("main_gun_qr");
		String main_radio_set_nomcltr = request.getParameter("main_radio_set_nomcltr");
		String main_radio_set_condn = request.getParameter("main_radio_set_condn");
		String main_radio_set = request.getParameter("main_radio_set");
		String mr1_due = request.getParameter("mr1_due");
		String mr1_done = request.getParameter("mr1_done");
		String oh1_due = request.getParameter("oh1_due");
		String oh1_done = request.getParameter("oh1_done");
		String oh1_detl = request.getParameter("oh1_detl");
		String mr2_due = request.getParameter("mr2_due");
		String mr2_done = request.getParameter("mr2_done");
		String oh2_done = request.getParameter("oh2_done");
		String oh2_due = request.getParameter("oh2_due");
		String mr3_due = request.getParameter("mr3_due");
		String mr3_done = request.getParameter("mr3_done");
		String oh3_done = request.getParameter("oh3_done");
		String oh3_due = request.getParameter("oh3_due");
		String unit_remarks = request.getParameter("unit_remarks");
		String main_gun_efc = request.getParameter("main_gun_efc");
		String sec_gun_type = request.getParameter("sec_gun_type");

		Session sessionMvcr = HibernateUtil.getSessionFactory().openSession();
		Transaction txMvcr = sessionMvcr.beginTransaction();
		Query qMvcr = sessionMvcr.createQuery("select count(*) from TB_TMS_CENSUS_RETURN "
				+ "where ba_no = :ba_no and sus_no = :sus_no " + "and vehcl_classfctn = :vehcl_classfctn "
				+ "and veh_km_run_period = :veh_km_run_period " + "and issued_type = :issued_type "
				+ "and ser_status = :ser_status " + "and engine_type = :engine_type "
				+ "and aux_engn_hrs_run = :aux_engn_hrs_run " + "and engine_kms_run = :engine_kms_run "
				+ "and aux_type = :aux_type " + "and engine_hrs_run = :engine_hrs_run "
				+ "and (aux_engn_clasfctn = :aux_engn_clasfctn or aux_engn_clasfctn is null) "
				+ "and main_gun_type = :main_gun_type " + "and main_gun_qr = :main_gun_qr "
				+ "and main_gun_efc = :main_gun_efc " + "and sec_gun_type = :sec_gun_type "
				+ "and main_radio_set_nomcltr = :main_radio_set_nomcltr "
				+ "and (main_radio_set_condn = :main_radio_set_condn or main_radio_set_condn is null)"
				+ "and main_radio_set = :main_radio_set " + "and mr1_due = :mr1_due " + "and mr1_done = :mr1_done "
				+ "and oh1_due = :oh1_due " + "and oh1_done = :oh1_done " + "and oh1_detl = :oh1_detl "
				+ "and mr2_due = :mr2_due " + "and mr2_done = :mr2_done " + "and oh2_due = :oh2_due "
				+ "and oh2_done = :oh2_done " + "and mr3_due = :mr3_due " + "and mr3_done = :mr3_done "
				+ "and oh3_done = :oh3_done " + "and oh3_due = :oh3_due " + "and unit_remarks = :unit_remarks");

		qMvcr.setParameter("ba_no", ba_no);
		qMvcr.setParameter("sus_no", sus_no);
		qMvcr.setParameter("vehcl_classfctn", vehcl_classfctn);
		qMvcr.setParameter("veh_km_run_period", veh_km_run_period);
		qMvcr.setParameter("issued_type", issued_type);
		qMvcr.setParameter("ser_status", ser_status);
		qMvcr.setParameter("engine_type", engine_type);
		qMvcr.setParameter("aux_engn_hrs_run", Integer.parseInt(aux_engn_hrs_run));
		qMvcr.setParameter("engine_kms_run", engine_kms_run);
		qMvcr.setParameter("aux_type", aux_type);
		qMvcr.setParameter("engine_hrs_run", engine_hrs_run);
		qMvcr.setParameter("aux_engn_clasfctn", aux_engn_clasfctn);
		qMvcr.setParameter("main_gun_type", main_gun_type);
		qMvcr.setParameter("main_gun_qr", main_gun_qr);
		qMvcr.setParameter("main_gun_efc", main_gun_efc);
		qMvcr.setParameter("sec_gun_type", sec_gun_type);
		qMvcr.setParameter("main_radio_set_nomcltr", main_radio_set_nomcltr);
		qMvcr.setParameter("main_radio_set_condn", main_radio_set_condn);
		qMvcr.setParameter("main_radio_set", main_radio_set);
		qMvcr.setParameter("mr1_due", mr1_due);
		qMvcr.setParameter("mr1_done", mr1_done);
		qMvcr.setParameter("oh1_due", oh1_due);
		qMvcr.setParameter("oh1_done", oh1_done);
		qMvcr.setParameter("oh1_detl", oh1_detl);
		qMvcr.setParameter("mr2_due", mr2_due);
		qMvcr.setParameter("mr2_done", mr2_done);
		qMvcr.setParameter("oh2_due", oh2_due);
		qMvcr.setParameter("oh2_done", oh2_done);
		qMvcr.setParameter("mr3_due", mr3_due);
		qMvcr.setParameter("mr3_done", mr3_done);
		qMvcr.setParameter("oh3_done", oh3_done);
		qMvcr.setParameter("oh3_due", oh3_due);
		qMvcr.setParameter("unit_remarks", unit_remarks);

		@SuppressWarnings("unchecked")
		Long count = (Long) qMvcr.uniqueResult();

		if (count > 0) {
			model.put("msg", "Data already exists");
			model.put("sus_no1", cp.getSus_no());
			return new ModelAndView("redirect:getMCRReport");
		}

		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = ses.beginTransaction();
		Query qry = ses.createQuery("from TB_TMS_CENSUS_RETURN where ba_no =:ba_no and sus_no=:sus_no");
		qry.setParameter("ba_no", ba_no);
		qry.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_CENSUS_RETURN> ListMvcrA = (List<TB_TMS_CENSUS_RETURN>) qry.list();
		txMvcr.commit();
		int version = ListMvcrA.get(0).getVersion();
		int updateversion = version + 1;
		ses.close();

		Date date = new Date();
		cp.setStatus("0");
		cp.setDt_of_submsn(date);
		cp.setDate_of_cens_retrn(date_of_cens_retrn);
		cp.setVersion(updateversion);

		mcrdao.UpdateMCR(cp);

		// logic for whole data updation in TB_TMS_CENSUS_RETURN_MAIN 31-08-2020
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<List<String>> list = mcrdao.getMCRList(cp.getSus_no(), roleAccess);
		if (list.size() > 0) {
			int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(16));
			if (list.size() == UPDATE) {
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				String hqlUpdate = "update TB_TMS_CENSUS_RETURN_MAIN c set c.status = :status  where c.status != '0' and c.sus_no = :sus_no";
				int app = session1.createQuery(hqlUpdate).setString("status", "0").setString("sus_no", cp.getSus_no())
						.executeUpdate();
				tx.commit();
				session1.close();
				if (app > 0) {
					msg = "FMCR Updated Successfully.";
				}
				model.put("msg", msg);
			} else {
				model.put("msg", "Data Updated Successfully.");
			}
		}
		model.put("sus_no1", cp.getSus_no());
		return new ModelAndView("redirect:getMCRReport");
	}

	@RequestMapping(value = "/admin/getAttributeDataSearchMCR", method = RequestMethod.POST)
	public ModelAndView getAttributeDataSearchMCR(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "unit_name1", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (status != "") {
			Mmap.put("status", status);
		}

		if (!sus_no.equals("") & sus_no.length() == 8) {

			Mmap.put("sus_no", sus_no);

			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
			}
			ArrayList<List<String>> list = mcrdao.getReportMcr(status, sus_no, unit_name, roleType);
			Mmap.put("roleType", roleType);
			Mmap.put("list", list);
		} else if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Select SUS No.");
		} else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:mcrSerach");
		}
		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:mcrSerach");
		}
		return new ModelAndView("mcrSerachTiles");
	}

	@RequestMapping(value = "/admin/ViewSerachmcr", method = RequestMethod.POST)
	public ModelAndView ViewSerachmcr(@ModelAttribute("sus_no2") String sus_no,
			@ModelAttribute("unit_name2") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, HttpSession session,
			Authentication authentication) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if (sus_no != "") {
			model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}
		ArrayList<List<String>> getmcrReportList = mcrdao.getmcrReportList(sus_no, roleType);
		model.put("getmcrReportList", getmcrReportList);
		return new ModelAndView("MCRSerachViewtile");
	}

	@RequestMapping(value = "/mcredit", method = RequestMethod.POST)
	public ModelAndView mcredit(@Valid @Validated @ModelAttribute("editId") int entryId, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("MCRSearchCMD", mcrdao.mcrSearchedit(entryId));
		return new ModelAndView("MCRSearchViewtile");
	}

	@RequestMapping(value = "/rejectmcrUrl", method = RequestMethod.POST)
	public ModelAndView rejectmcrUrl(@ModelAttribute("sus") String sus_no, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") & !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String row = mcrdao.setRejectmcr(sus_no);
		String msg1 = "";
		if (row != null && row != "" || !row.equals("")) {
			if (Integer.parseInt(row) > 0) {
				msg1 = "Data Rejected.";
			} else {
				msg1 = "Data Not Rejected.";
			}
		}
		model.put("msg", msg1);
		return new ModelAndView("mcrSerachTiles");
	}

	@RequestMapping(value = "/ApprovedmcrUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedmcrUrl(@ModelAttribute("sus3") String sus_no,
			@ModelAttribute("unit_name1") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		if (!roleType.equals("APP") & !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		ArrayList<List<String>> getmcrReportList = mcrdao.getmcrReportList(sus_no, roleType);
		// List<String> mct= getmcrReportList.get(1);
		String mct = "";
		int i = 0;
		for (List<String> s : getmcrReportList) {
			if (i == 0) {
				mct += s.get(1);
			} else {
				mct += "," + s.get(1);
			}
			i++;

		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		model.put("msg", mcrdao.setApprovedmcr(sus_no, username, mct, roleType));
		return new ModelAndView("further_mcr_detailsTile");
	}

	@RequestMapping(value = "/MCR_partA")
	public ModelAndView MVCR_partA(@ModelAttribute("sus_noa") String sus_no, ModelMap model,
			@ModelAttribute("unit_namea") String unit_name, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionA, HttpServletRequest request) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("unit_name", unit_name);
		model.put("sus_no", sus_no);
		return new ModelAndView("MCR_partATile");
	}

	@RequestMapping(value = "/fmcr_repair_sch")
	public ModelAndView fmcr_repair_sch(@ModelAttribute("sus_nor") String sus_no, ModelMap model,
			@ModelAttribute("unit_namer") String unit_name, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, Authentication authentication, HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("unit_name", unit_name);
		model.put("sus_no", sus_no);
		return new ModelAndView("fmcr_repairTile");
	}

	////// part B////////
	@RequestMapping(value = "/MCR_partB")
	public ModelAndView MCR_partB(@ModelAttribute("sus_noB") String sus_no,
			@ModelAttribute("unit_nameB") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA, HttpServletRequest request) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		return new ModelAndView("MCR_partBTile");
	}

	@RequestMapping(value = "/getApproveDatemcr")
	public @ResponseBody List getApproveDate(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from TB_TMS_CENSUS_RETURN where sus_no = :sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("rawtypes")
		List list = (List) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getwepenoList")
	public @ResponseBody List<CUE_TB_WEPE_link_sus_perstransweapon> getwepenoList(String sus_no, HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_WEPE_link_sus_perstransweapon> list = (List<CUE_TB_WEPE_link_sus_perstransweapon>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	/////////// Upload Document MCR ////////////////////////
	@RequestMapping(value = "/admin/uploadDocMCR")
	public @ResponseBody List<String> uploadDocMCR(
			@RequestParam(value = "uploadMvcr", required = false) MultipartFile uploadMvcr, HttpServletRequest request,
			ModelMap model, HttpSession session, String sus) {

		List<String> list = new ArrayList<>();
		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
		if (uploadMvcr.getSize() > fileSizeLimit) {
			list.add("File size should be less then 2 MB");
			return list;
		}

		String uploadMvcrExt = FilenameUtils.getExtension(uploadMvcr.getOriginalFilename());
		if (!uploadMvcrExt.equals("zip") & !uploadMvcrExt.equals("rar")) {
			list.add("Only *.zip or *.rar file extensions allowed");
			return list;
		}

		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		String issue_summary = request.getParameter("issue_summary");
		String description_help = request.getParameter("description");
		String report_obsn = request.getParameter("report_obsn");

		TB_TMS_UPLOAD_DOC_MCR upload = new TB_TMS_UPLOAD_DOC_MCR();

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String fname = "";
		if (!uploadMvcr.isEmpty()) {
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			// code modify by Paresh on 05/05/2020
			try {
				byte[] bytes = uploadMvcr.getBytes();

				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
					String tmsFilePath = session.getAttribute("tmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					String filename = uploadMvcr.getOriginalFilename();
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userid + "_TMSDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					upload.setDocument(fname);
					upload.setCreatedby(username);
					upload.setCreateddatetime(date);
					upload.setSus_no(sus);
					upload.setStatus("0");

					Session session1 = HibernateUtil.getSessionFactory().openSession();
					session1.beginTransaction();
					session1.save(upload);
					String s = create_ticket_tms_a(upload, session, issue_summary, description_help, report_obsn);
					session1.getTransaction().commit();
					session1.close();
					list.add("Document Uploaded Successfully");
				} else {
					list.add("Invalid File Format.");
				}
			} catch (Exception e) {
				list.add("an Error ocurre file saving.");
			}
		}
		return list;
	}

	private String create_ticket_tms_a(TB_TMS_UPLOAD_DOC_MCR upload, HttpSession session, String issue_summary,
			String description_help, String report_obsn) {
		String msg = "";
		String user_id = "";
		String module_name = "TRANSPORT";
		String sub_module = "A VEHICLE";
		String screenname = "FMCR DETAILS";
		List<TB_LDAP_MODULE_MASTER> module = helpcntl.getModuleNameHelpDeskList(session);
		List<TB_LDAP_SUBMODULE_MASTER> submodule = helpcntl.getSubModuleList(session);
		List<TB_LDAP_SCREEN_MASTER> screen = helpcntl.getScreenList(session);
		HD_TB_BISAG_TICKET_GENERATE tickect = new HD_TB_BISAG_TICKET_GENERATE();
		for (TB_LDAP_MODULE_MASTER a : module) {
			if (a.getModule_name().equals("TRANSPORT")) {
				tickect.setModule(a.getId());
			}
		}
		for (TB_LDAP_SUBMODULE_MASTER b : submodule) {

			if (b.getSubmodule_name().equals("A VEHICLE")) {
				tickect.setSub_module(b.getId());
			}

		}
		for (TB_LDAP_SCREEN_MASTER c : screen) {
			if (c.getScreen_name().equals("FMCR DETAILS")) {
				tickect.setScreen_name(c.getId());
			}
		}
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		tickect.setCreated_by(upload.getCreatedby());
		tickect.setCreated_on(new Date());
		// tickect.setDescription();
		tickect.setHelp_topic(report_obsn);
		tickect.setIssue_summary(issue_summary);
		tickect.setDescription(description_help);
		tickect.setUserid(userId);
		tickect.setTicket_status("0");
		int ticket = helpcntl.getMaxticket();
		tickect.setTicket(ticket);
		tickect.setscreen_shot(upload.getDocument());
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		if (roleAccess.equals("Unit")) {
			tickect.setSus_no(roleSusNo);
		}
		tickect.setCreated_on(new Date());
		// String description= module_name +" : " + sub_module + " : " + screen + "\n" +
		// " (i) "+N.getIssue_summary() + "\n" + " (ii) "+N.getDescription();
		String unit_name = notif.getUnitNameFromUserId(userId);
		String description = "Please Check the Ticket in Manage Ticket Screen";
		Integer module_id = tickect.getModule();
		List<String> list = notif.getUserIdForNotif(module_id);
		for (int i = 0; i < list.size(); i++) {
			user_id += list.get(i);
			if (i < list.size() - 1)
				user_id += ",";
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int N_id = (int) session1.save(tickect);

		if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
			Boolean d = notification.sendNotification_tms("TICKET GENERATED for " + module_name + " : " + sub_module
					+ " : " + screenname + " by " + unit_name, description, user_id, username, N_id);
		}
		// session1.save(tic_gen_noti);
		session1.getTransaction().commit();
		session1.close();
		return msg;
	}

	@RequestMapping(value = "/getDocumentMCR", method = RequestMethod.POST)
	public @ResponseBody List<TB_TMS_UPLOAD_DOC_MCR> getDocumentMCR(String sus_no, HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_UPLOAD_DOC_MCR where sus_no =:sus_no and status='0'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_UPLOAD_DOC_MCR> list = (List<TB_TMS_UPLOAD_DOC_MCR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	//////// Download Document From sus_no//////////////
	@RequestMapping(value = "/getDownloadImagemcr", method = RequestMethod.POST)
	public ModelAndView getDownloadImage(@ModelAttribute("sus_no3") String sus_no, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession sessionA) throws IOException {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String EXTERNAL_FILE_PATH = "";
		EXTERNAL_FILE_PATH = mcrdao.getdownload(sus_no).get(0).getDocument();

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView("redirect:mcredit?msg=Sorry. The file you are looking for does not exist");
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
			return new ModelAndView("redirect:mcredit?msg=Download Successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:mcredit?msg=Download Successfully");
	}

	@RequestMapping(value = "/PartB_UE_UH_MCR")
	public ModelAndView Detalis_UE_UH(@ModelAttribute("sus_nob") String sus_no, ModelMap model,
			@ModelAttribute("unit_nameb") String unit_name, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionA, HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		model.put("unit_name", unit_name);
		model.put("sus_no", sus_no);
		return new ModelAndView("MCR_partB_UE_Tile");
	}

	@RequestMapping(value = "/A_E_AssetDetail")
	public ModelAndView A_E_AssetDetail(@ModelAttribute("sus_noe") String sus_no, ModelMap model,
			@ModelAttribute("unit_namee") String unit_name, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpSession sessionA, HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		model.put("unit_name", unit_name);
		model.put("sus_no", sus_no);
		return new ModelAndView("A_E_AssetDetail_Tile");
	}

	@RequestMapping(value = "/admin/getdetails_ue_uhMvcr", method = RequestMethod.POST)
	public ModelAndView getdetails_ue_uhMvcr(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "sus_nob", required = false) String sus_no,
			@RequestParam(value = "unit_nameb", required = false) String unit_name, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}

		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("roleType", roleType);
		Mmap.put("sus_no", sus_no);
		if (orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
			Mmap.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}

		if (!sus_no.equals("")) {
			ArrayList<ArrayList<String>> getdetails_ue_uhMvcr = mcrdao.getdetails_ue_uhMvcrlist(sus_no);
			Mmap.put("getdetails_ue_uhMvcr", getdetails_ue_uhMvcr);
			int totalUE = 0;
			int totalOPUH = 0;
			int totalTRGUH = 0;
			int totalWWRUH = 0;
			int totalUH = 0;
			int totalDEFI = 0;
			int totalSUR = 0;

			int totalloan = 0;
			int totalsector = 0;

			for (int i = 0; i < getdetails_ue_uhMvcr.size(); i++) {
				String totalUE1 = getdetails_ue_uhMvcr.get(i).get(2);
				if (totalUE1 == null || totalUE1.equals(null)) {
					totalUE = 0;
				}
				String totalOPUH1 = getdetails_ue_uhMvcr.get(i).get(3);
				if (totalOPUH1 == null || totalOPUH1.equals(null)) {
					totalOPUH = 0;
				}
				String totalTRGUH1 = getdetails_ue_uhMvcr.get(i).get(4);
				if (totalTRGUH1 == null || totalTRGUH1.equals(null)) {
					totalTRGUH = 0;
				}
				String totalWWRUH1 = getdetails_ue_uhMvcr.get(i).get(5);
				if (totalWWRUH1 == null || totalWWRUH1.equals(null)) {
					totalWWRUH = 0;
				}
				String totalUH1 = getdetails_ue_uhMvcr.get(i).get(6);
				if (totalUH1 == null || totalUH1.equals(null)) {
					totalUH = 0;
				}

				String totalSUR1 = getdetails_ue_uhMvcr.get(i).get(7);
				if (totalSUR1 == null || totalSUR1.equals(null)) {
					totalSUR = 0;
				}
				String totalDEFI1 = getdetails_ue_uhMvcr.get(i).get(8);
				if (totalDEFI1 == null || totalDEFI1.equals(null)) {
					totalDEFI = 0;
				}

				String totalloan1 = getdetails_ue_uhMvcr.get(i).get(9);
				if (totalloan1 == null || totalloan1.equals(null)) {
					totalloan = 0;
				}
				String totalsector1 = getdetails_ue_uhMvcr.get(i).get(10);
				if (totalsector1 == null || totalsector1.equals(null)) {
					totalsector = 0;
				}

				totalUE = totalUE + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(2));
				totalOPUH = totalOPUH + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(3));
				totalTRGUH = totalTRGUH + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(4));
				totalWWRUH = totalWWRUH + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(5));
				totalUH = totalUH + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(6));
				totalDEFI = totalDEFI + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(7));
				totalSUR = totalSUR + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(8));

				totalloan = totalloan + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(9));
				totalsector = totalsector + Integer.parseInt(getdetails_ue_uhMvcr.get(i).get(10));

			}
			Mmap.put("totalUE", totalUE);
			Mmap.put("totalOPUH", totalOPUH);
			Mmap.put("totalTRGUH", totalTRGUH);
			Mmap.put("totalWWRUH", totalWWRUH);

			Mmap.put("totalUH", totalUH);
			Mmap.put("totalSUR", totalSUR);
			Mmap.put("totalDEFI", totalDEFI);
			Mmap.put("totalloan", totalloan);
			Mmap.put("totalsector", totalsector);
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		Mmap.put("date", date1);

		List<String> list2 = getApproveDateMCR(sus_no, session);
		String approveDate = new SimpleDateFormat("dd-MM-yyyy").format(list2.get(0));
		Mmap.put("approve_date", approveDate);
		
		//Added By Mitesh
				List<String> list3 = getModifiedDateMCR(sus_no, session);

				String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(list3.get(0));

				Mmap.put("modify_date", modifieddate);

		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if (wepe.size() != 0) {
			Mmap.put("we_pe_no", addmctDAO.getwepeno(sus_no).get(0));
		}
		return new ModelAndView("MCR_partB_UE_Tile");
	}

	@RequestMapping(value = "/admin/getdetailsE_AssetsMvc", method = RequestMethod.POST)
	public ModelAndView getdetailsE_AssetsMvc(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noe", required = false) String sus_no,
			@RequestParam(value = "unit_namee", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		ArrayList<ArrayList<String>> E_AssetsMvclist = mcrdao.getdetailsE_AssetsMvclist(sus_no);
		Mmap.put("roleType", roleType);
		Mmap.put("sus_no", sus_no);
		Mmap.put("E_AssetsMvclist", E_AssetsMvclist);
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		Mmap.put("date", date1);
		List<String> list2 = getApproveDateMCR(sus_no, session);
		String approveDate = new SimpleDateFormat("dd-MM-yyyy").format(list2.get(0));
		Mmap.put("approve_date", approveDate);
		//Added By Mitesh
				List<String> list3 = getModifiedDateMCR(sus_no, session);

				String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(list3.get(0));

				Mmap.put("modify_date", modifieddate);
		Mmap.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if (wepe.size() != 0) {
			Mmap.put("we_pe_no", addmctDAO.getwepeno(sus_no).get(0));
		}
		return new ModelAndView("A_E_AssetDetail_Tile");
	}

	public List<String> getApproveDateMCR(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select approve_date  from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	//Added By Mitesh 10-07-24
	public List<String> getModifiedDateMCR(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("Select modify_date  from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		System.err.println(q);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	AllMethodsControllerOrbat orbt = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/admin/getmcrReportListpartA", method = RequestMethod.POST)
	public ModelAndView getmcrReportListpartA(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noa", required = false) String sus_no,
			@RequestParam(value = "unit_namea", required = false) String unit_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		
		
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		Mmap.put("date", date1);
		List<String> list2 = getApproveDateMCR(sus_no, session);

		String approveDate = new SimpleDateFormat("dd-MM-yyyy").format(list2.get(0));

		Mmap.put("approve_date", approveDate);
		
		//Added By Mitesh
		List<String> list3 = getModifiedDateMCR(sus_no, session);

		String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(list3.get(0));

		Mmap.put("modify_date", modifieddate);
		
		
		
		
		ArrayList<List<String>> ReportListpartA = mcrdao.getmcrReportListpartA(sus_no);
		Mmap.put("roleType", roleType);
		Mmap.put("sus_no", sus_no);
		if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}
		Mmap.put("ReportListpartA", ReportListpartA);
	/*	
	
		//Mitesh
		List<TB_TMS_CENSUS_RETURN_MAIN> dateList = mcrdao.getApprove_date(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			Mmap.put("app", app);
		}
		if(modi != null) {
			Mmap.put("modi", modi);
		}
		//
*/
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if (wepe.size() != 0) {
			Mmap.put("we_pe_no", addmctDAO.getwepeno(sus_no).get(0));
		}

		return new ModelAndView("MCR_partATile");
	}

	@RequestMapping(value = "/getmctList", method = RequestMethod.POST)
	public @ResponseBody List<String> getmctList(String ba_no, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				String mct = String.valueOf(list.get(i).getMct());
				encCode = c.doFinal(mct.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getcountryList", method = RequestMethod.POST)
	public @ResponseBody List<String> getcountryList(String ba_no, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select country_of_origin from Tms_Banum_Req_Child where ba_no=:ba_no and country_of_origin is not null");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/admin/getRepairReport", method = RequestMethod.POST)
	public ModelAndView getRepairReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_nor", required = false) String sus_no,
			@RequestParam(value = "unit_namer", required = false) String unit_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcrSerach", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		ArrayList<List<String>> RepairReport = mcrdao.getRepairReport(sus_no);
		Mmap.put("roleType", roleType);
		Mmap.put("sus_no", sus_no);

		if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
			Mmap.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}

		Mmap.put("RepairReport", RepairReport);

		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		Mmap.put("date", date1);
		List<String> list2 = getApproveDateMCR(sus_no, session);
		String approveDate = new SimpleDateFormat("dd-MM-yyyy").format(list2.get(0));

		Mmap.put("approve_date", approveDate);
		
		//Added By Mitesh
				List<String> list3 = getModifiedDateMCR(sus_no, session);

				String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(list3.get(0));

				Mmap.put("modify_date", modifieddate);
				
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if (wepe.size() != 0) {
			Mmap.put("we_pe_no", addmctDAO.getwepeno(sus_no).get(0));
		}
		return new ModelAndView("fmcr_repairTile");
	}

	@RequestMapping(value = "/admin/getMCRReportList", method = RequestMethod.POST)
	public ModelAndView getMCRReportList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no3", required = false) String sus_no,
			@RequestParam(value = "unit_name3", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Enter SUS No or Unit Name.");

			return new ModelAndView("mcrTiles");
		} else {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:mcr");
			}
			if (validation.checkUnit_nameLength(unit_name) == false) {
				Mmap.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:mcr");
			}

			Mmap.put("sus_no", sus_no);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		}

		String roleType = session.getAttribute("roleType").toString();
		ArrayList<List<String>> list = mcrdao.getMCRReportList(sus_no, roleType);

		Mmap.put("roleType", roleType);
		Mmap.put("sus_no", sus_no);
		Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		Mmap.put("list", list);

		return new ModelAndView("mcrTiles");
	}

	@RequestMapping(value = "/admin/getMCRReport")
	public ModelAndView getMCRReport(@ModelAttribute("sus_no1") String sus_no,
			@ModelAttribute("unit_name1") String unit_name, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		model.put("sus_no", sus_no);
		model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		model.put("msg", msg);
		model.put("GetHelpTopic", helpcntl.GetHelpTopicT());
		ArrayList<List<String>> list = mcrdao.getMCRList(sus_no, roleAccess);
		if (list.size() == 0) {
			model.put("msg", "Data Not Available.");
		} else {
			model.put("list", list);
			int TOTAL = Integer.parseInt(list.get(list.size() - 1).get(15));
			int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(16));
			int NotUpdated = TOTAL - UPDATE;

			model.put("TOTAL", TOTAL);
			model.put("UPDATE", UPDATE);
			model.put("NotUpdated", NotUpdated);
			/*
			 * if(list.size() == UPDATE) { List<String> list1 = new ArrayList<String>();
			 * Session session = HibernateUtil.getSessionFactory().openSession();
			 * Transaction tx = session.beginTransaction(); String hqlUpdate =
			 * "update TB_TMS_CENSUS_RETURN_MAIN c set c.status = :status  where c.status != '0' and c.sus_no = :sus_no"
			 * ; int app = session.createQuery(hqlUpdate).setString("status",
			 * "0").setString("sus_no", sus_no).executeUpdate(); tx.commit();
			 * session.close(); if (app > 0) { msg = "FMCR Updated Successfully."; }
			 * model.put("msg", msg); }
			 */
		}

		return new ModelAndView("reTiles");
	}

	@RequestMapping(value = "/admin/getDownloadImageMCR_Report", method = RequestMethod.POST)
	public ModelAndView getDownloadImageMCR_Report(@ModelAttribute("id1") int id1, ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		List<TB_TMS_UPLOAD_DOC_MCR> a = getDocumentMCRImg(id1, session);

		EXTERNAL_FILE_PATH = a.get(0).getDocument();
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				// model.put("sus_no1", sus_no1);
				return new ModelAndView("redirect:mcr?msg=Sorry. The file you are looking for does not exist");
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

			// Update status UploadDocumentMVCR Table
			String username = session.getAttribute("username").toString();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionUpdate.beginTransaction();
			String hqlUpdate = "update TB_TMS_UPLOAD_DOC_MCR u set u.status=:status,downloadby=:downloadby,downloadon=:downloadon where u.id =:id";
			sessionUpdate.createQuery(hqlUpdate).setString("status", "1").setInteger("id", id1)
					.setString("downloadby", username).setString("downloadon", date).executeUpdate();
			tx.commit();
			sessionUpdate.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:mvcr?msg=Download Successfully.");
	}

	public List<TB_TMS_UPLOAD_DOC_MCR> getDocumentMCRImg(int id1, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("Unit")) {
			q = session.createQuery("from TB_TMS_UPLOAD_DOC_MCR where id=:id and sus_no=:sus_no");
			q.setParameter("id", id1);
			q.setParameter("sus_no", roleSusNo);
		} else {
			q = session.createQuery("from TB_TMS_UPLOAD_DOC_MCR where id=:id ");
			q.setParameter("id", id1);
		}
		@SuppressWarnings("unchecked")
		List<TB_TMS_UPLOAD_DOC_MCR> list = (List<TB_TMS_UPLOAD_DOC_MCR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getExcelFMCR", method = RequestMethod.POST)
	public ModelAndView getExcelFMCR(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "sus_noex", required = false) String sus_no,
			@RequestParam(value = "unit_nameex", required = false) String unit_name,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<List<String>> Excel = mcrdao.getFMCRList(sus_no, roleAccess);

		List<String> TH = new ArrayList<String>();

		/*
		 * TH.add("COMD"); TH.add("CORPS"); TH.add("DIV"); TH.add("BDE");
		 * TH.add("Unit Name"); TH.add("Line Dte"); TH.add("SUS No");
		 * TH.add("MCT Nomenclature");
		 */

		TH.add("BA No");
		// TH.add("MCT Nomenclature");
		TH.add("Kms run");
		TH.add("Classification");

		TH.add("OH 1 Km");
		TH.add("OH 1 Vintage");
		TH.add("OH 2 Km");
		TH.add("OH 2 Vintage");
		TH.add("OH 3 Km");
		TH.add("OH 3 Vintage");
		TH.add("MR 1 Km");
		TH.add("MR 1 Vintage");
		TH.add("MR 2 Km");
		TH.add("MR 2 Vintage");
		TH.add("MR 3 Km");
		TH.add("MR 3 Vintage");
		TH.add("OH 1 Due");
		TH.add("OH 2 Due");
		TH.add("OH 3 Due");
		TH.add("MR 1 Due");
		TH.add("MR 2 Due");
		TH.add("MR 3 Due");


		TH.add("OH 1 Done (dd-mm-yyyy)");
		TH.add("OH 2 Done (dd-mm-yyyy)");
		TH.add("OH 3 Done  (dd-mm-yyyy)");
				
		TH.add("MR 1 Done (dd-mm-yyyy)");
		TH.add("MR 2 Done (dd-mm-yyyy)");
		TH.add("MR 3 Done  (dd-mm-yyyy)");
		

		TH.add("Engine No");
		TH.add("Engine Type");

		TH.add("Engine Hrs Current");
		TH.add("Engine Hr Total");
		TH.add("Engine Failure at");
		TH.add("Engine Submission Dt (dd-mm-yyyy)");
		TH.add("Engine Repair Completion Dt (dd-mm-yyyy)");

		TH.add("Air Compressor No");
		TH.add("Air Compressor Type");
		TH.add("Air Compressor Total Hrs");
		TH.add("Air Compressor Failure at");
		TH.add("Air Compressor Submission Dt (dd-mm-yyyy)");
		TH.add("Air Compressor Repair Completion Dt (dd-mm-yyyy)");

		TH.add("SGB No");
		TH.add("SGB Type");
		TH.add("SGB Total Km");
		TH.add("SGB Failure at");
		TH.add("SGB Submission Dt (dd-mm-yyyy)");
		TH.add("SGB Repair Completion Dt (dd-mm-yyyy)");

		TH.add("IGB No");
		TH.add("IGB Type");
		TH.add("IGB Total Km");
		TH.add("IGB Failure at");
		TH.add("IGB Submission Dt (dd-mm-yyyy)");
		TH.add("IGB Repair Completion Dt (dd-mm-yyyy)");

		TH.add("Track Assy total Km");
		TH.add("Track Assy Serviceability (S/ UNSV)");
		TH.add("Track Assy Failure at");

		TH.add("Sprocket Assy Total Km");
		TH.add("Sprocket Assy Failure at");
		TH.add("Sprocket Assy Serviceability (S/ UNSV)");

		TH.add("Pump Drive Motor Hr");
		TH.add("Pump Drive Motor Serviceability (S/ UNSV)");
		TH.add("Pump Drive Motor Failure at");

		TH.add("Starter Genr Total Hrs");
		TH.add("Starter Genr Serviceability (S/ UNSV)");
		TH.add("Starter Genr Failure at");

		TH.add("Turbo Charger Type");
		TH.add("Turbo Charger Defect at");
		TH.add("Turbo Charger Submission Dt (dd-mm-yyyy)");
		TH.add("Turbo Charger Repair Completion Dt (dd-mm-yyyy)");

		TH.add("Engine Demand Dt (dd-mm-yyyy)");
		TH.add("Engine Rel Dt (dd-mm-yyyy)");

		TH.add("Fire Fighting Sys Cylinders Auth");
		TH.add("Fire Fighting Sys Cylinders Held");
		TH.add("Fire Fighting Sys Cylinders Empty");
		TH.add("Fire Fighting Cylinders Demand Dt (dd-mm-yyyy)");

		TH.add("CBRN Sys Over Presssure (Yes/ No)");
		TH.add("CBRN Filter Qty");
		TH.add("CBRN Filter Serviceability (S/ UNSV)");
		TH.add("PKUZ1A Serviceability (S/ UNSV)");
		TH.add("BAS 6A Serviecability (S/ UNSV)");
		TH.add("CBRN Spl Blower Serviceability (S/ UNSV)");

		TH.add("Mine Plough Serviceability (S/ UNSV)");
		TH.add("EMP Serviceability (S/ UNSV)");
		TH.add("Main RS Serviceability (S/ UNSV)");
		TH.add("Main RS Type");
		TH.add("Main RS Qty");
		TH.add("DCH Installed (Y/ N)");

		TH.add("DCH Type");
		TH.add("DCH Installed Dt (dd-mm-yyyyy)");
		TH.add("DCH Vintage");
		TH.add("DCH Serviceability (S/ UNSV)");
		TH.add("CTI Installation Dt (dd-mm-yyyy)");
		TH.add("CTI Vintage");
		TH.add("CTI Serviceability (S/ UNSV)");
		TH.add("Main Gun Article No");
		TH.add("Type of Main Gun Article");
		TH.add("Main Gun Article Qtr (I /II/ III/ IV)");

		TH.add("Main Gun Article Serviceability (S/ UNSV)");
		TH.add("Main Gun Article EFC");
		TH.add("No of Msls Fired");
		TH.add("No of Msls Failed");
		TH.add("Sec Gun Type");
		TH.add("Sec Gun Regn No");
		TH.add("Sec Gun Serviceability (S/ UNSV)");
		TH.add("AA Gun Type");
		TH.add("AA Gun Regn No");
		TH.add("AA Gun Serviceability (S/ UNSV)");

		TH.add("Msl Launcher Regn No");
		TH.add("Msl Launcher Serviceability (S/ UNSV)");
		TH.add("Msl launcher OH Due");
		TH.add("Msl Lr OH Done Dt (dd-mm-yyyy)");
		TH.add("TISAS (Old) Instln Date");
		TH.add("TISAS (Old) Vintage");
		TH.add("TISAS (Old) Serviceability (S/ UNSV)");
		TH.add("TISAS (New) Installation Dt (dd-mm-yyyy)");
		TH.add("TISAS (New) Vintage");
		TH.add("TISAS (New) Serviceability (S/ UNSV)");

		TH.add("TIFCS Type (1000/ 418/ 96)");
		TH.add("TIFCS Regn No");
		TH.add("TIFCS Installation Dt (dd-mm-yyyy)");
		TH.add("TIFCS Vintage");
		TH.add("Relay KR 175 Hr");
		TH.add("Relay KR 175 Serviceability (S/ UNSV)");
		TH.add("Relay KR 175 Failure at");
		TH.add("LRF Hr");
		TH.add("LRF Serviceability (S/ UNSV)");
		TH.add("LRF Failure at");

		TH.add("Gyro Direction Indicator Hr");
		TH.add("Gyro Direction Indicator Serviceability (S/ UNSV)");
		TH.add("Gyro Direction Failure at");
		TH.add("Distribution Box Hr");
		TH.add("Distribution Box Serviceability (S/ UNSV)");
		TH.add("Distribution Box Failure at");
		TH.add("TIM Fitted (Y/ N)");
		TH.add("TIM Instln (dd-mm-yyyy)");
		TH.add("TIM Vintage");
		TH.add("TIS Fitted (Y/ N)");

		TH.add("TIS Instln dt (dd-mm-yyyy)");
		TH.add("TIS Vintage");
		TH.add("U-TIM Fitted (Y/ N)");
		TH.add("U-TIM Insln Dt (dd-mm-yyyy)");
		TH.add("U-TIM Vintage");
		TH.add("M-TIM Fitted (Y/ N)");
		TH.add("M-TIM Insln Dt (dd-mm-yyyy)");
		TH.add("M-TIM Vintage");
		TH.add("M-TISK Fitted (Y/ N)");
		TH.add("M-TISK Insln Dt (dd-mm-yyyy)");

		TH.add("M-TISK Vintage");
		TH.add("DNS Fitted (Y/ N)");
		TH.add("DNS Instln Dt (dd-mm-yyyy)");
		TH.add("DNS Serviceability (S/ UNSV)");
		TH.add("CTI Fitted (Y/ N)");
		TH.add("CTI Instln Dt (dd-mm-yyyy)");
		TH.add("ALNS Fitted (Y/ N)");
		TH.add("ALNS Serviceability (S/ UNSV)");
		TH.add("GPS Fitted (Y/ N)");
		TH.add("GPS Serviceability (S/ UNSV)");

		TH.add("ECU Fitted (Y/ N)");
		TH.add("ECU Serviceability (S/ UNSV)");
		TH.add("SPTA Fitted (Y/ N)");
		TH.add("SPTA Serviceability (S/ UNSV)");

		TH.add("Powerpack Serviceability (S/ UNSV)");
		TH.add("Powerpack Demand Dt (dd-mm-yyyy)");

		TH.add("STAB Serviceability (S/ UNSV)");
		TH.add("STAB Demand Dt (dd-mm-yyyy)");

		TH.add("IFDSS Serviceability (S/ UNSV)");
		TH.add("IFDSS Demand Dt (dd-mm-yyyy)");

		TH.add("GMS Serviceability (S/ UNSV)");
		/* TH.add("GMS Demand Dt"); */

		TH.add("CPS Mk I Serviceability (S/ UNSV)");
		TH.add("CPS Mk I Demand Dt (dd-mm-yyyy)");

		TH.add("Dvr PNVD Serviceability (S/ UNSV)");
		TH.add("Dvr PNVD Demand Dt (dd-mm-yyyy)");

		TH.add("FCC Serviceability (S/ UNSV)");
		TH.add("FCC Demand Dt (dd-mm-yyyy)");

		TH.add("Bogie Wheels Held (on rd)");
		TH.add("Bogie Wheels Held (off rd)");
		TH.add("Bogie Wheels Demand Dt (dd-mm-yyyy)");

		TH.add("Top Roll Serviceability (S/ UNSV)");
		TH.add("Top Roll Demand Dt (dd-mm-yyyy)");

		TH.add("Track Pads Held (on rd)");
		TH.add("Track Pads Held (off rd)");
		TH.add("Track Pads Demand Dt (dd-mm-yyyy)");

		TH.add("Cdr Cont Stn Serviceability (S/ UNSV)");
		TH.add("Cdr Cont Stn Demand Dt (dd-mm-yyyy)");

		TH.add("GCDU Serviceability (S/ UNSV)");
		TH.add("GCDU Demand Dt (dd-mm-yyyy)");
		TH.add("Low Tempered Brl (Y/N)");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Export_To_Excel_TMS("L", TH, username, Excel), "userList", Excel);
	}

	@RequestMapping(value = "/exportTMSExcelAction", method = RequestMethod.POST)
	public ModelAndView exportTMSExcelAction(
			@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		String table_name = request.getParameter("table_name");
		String numberOfColumns = request.getParameter("numberOfColumns");
		String sus_no = request.getParameter("sus_no3");
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		if (numberOfColumns.equals("186")) {

			int countrow = Integer.parseInt(request.getParameter("countrow"));
			String username = session.getAttribute("username").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			SimpleDateFormat formatter_dash = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			SimpleDateFormat formatter_slash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String roleAccess = session.getAttribute("roleAccess").toString();
			ArrayList<List<String>> Excel = mcrdao.getFullFMCRList(sus_no, roleAccess);

			for (int i = 0; i < countrow; i++) {

				String ba_no = request.getParameter("BA No_" + i);
				String month = new SimpleDateFormat("MM-yyyy").format(new Date());
				// if(mcrdao.checkDataExists(ba_no,month,roleSusNo,table_name) > 0) {

				String engine_type = request.getParameter("Engine Type_" + i);
				if (engine_type == "" || engine_type == null || engine_type == "undefined"
						|| engine_type.trim().equals("undefined")) {
					engine_type = null;
				}

				String engineHrsCurrentStr = request.getParameter("Engine Hrs Current_" + i);
				int engineHrsCurrentAt = 0;

				try {
					if (engineHrsCurrentStr != null && !engineHrsCurrentStr.isEmpty()) {
						engineHrsCurrentAt = Integer.parseInt(engineHrsCurrentStr);
						if (engineHrsCurrentAt < 0) {
							model.put("msg", "Engine Hrs Current Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Engine Hrs Current Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String engine_hrs_current = request.getParameter("Engine Hrs Current_" + i);
				if (engine_hrs_current == "" || engine_hrs_current == null) {
					engine_hrs_current = "0";
				}

				String engineHrsTotalStr = request.getParameter("Engine Hr Total_" + i);
				int engineHrsTotalAt = 0;

				try {
					if (engineHrsTotalStr != null && !engineHrsTotalStr.isEmpty()) {
						engineHrsTotalAt = Integer.parseInt(engineHrsTotalStr);
						if (engineHrsTotalAt < 0) {
							model.put("msg", "Engine Hrs Total Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Engine Hrs Total Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String engine_hr_total = request.getParameter("Engine Hr Total_" + i);
				if (engine_hr_total == "" || engine_hr_total == null) {
					engine_hr_total = "0";
				}

				String engineFailureAtStr = request.getParameter("Engine Failure at_" + i);
				int engineFailureAtAt = 0;

				try {
					if (engineFailureAtStr != null && !engineFailureAtStr.isEmpty()) {
						engineFailureAtAt = Integer.parseInt(engineFailureAtStr);
						if (engineFailureAtAt < 0) {
							model.put("msg", "Engine Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Engine Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String engine_failure_at = request.getParameter("Engine Failure at_" + i);
				if (engine_failure_at == "" || engine_failure_at == null) {
					engine_failure_at = "0";
				}

				Date engine_sub_dt = null;
				String engine_submission_dt1 = request.getParameter("Engine Submission Dt (dd-mm-yyyy)_" + i);

				if (engine_submission_dt1 != null && engine_submission_dt1 != "undefined"
						&& !engine_submission_dt1.trim().equals("undefined") && !engine_submission_dt1.trim().equals("")
						&& !engine_submission_dt1.trim().isEmpty()) {
					engine_sub_dt = formatter_dash.parse(engine_submission_dt1);
				} else {
					engine_sub_dt = null;
				}

				String engine_repair_comp_dt1 = request.getParameter("Engine Repair Completion Dt (dd-mm-yyyy)_" + i);
				Date engine_repair_comp_dt = null;
				if (engine_repair_comp_dt1 != null && engine_repair_comp_dt1 != "undefined"
						&& !engine_repair_comp_dt1.trim().equals("undefined")
						&& !engine_repair_comp_dt1.trim().isEmpty()) {
					engine_repair_comp_dt = formatter_dash.parse(engine_repair_comp_dt1);
				} else {
					engine_repair_comp_dt = null;
				}

				String air_compressor_no = request.getParameter("Air Compressor No_" + i);
				if (air_compressor_no == "" || air_compressor_no == null || air_compressor_no == "undefined"
						|| air_compressor_no.trim().equals("undefined")) {
					air_compressor_no = null;
				}

				String air_compressor_type = request.getParameter("Air Compressor Type_" + i);
				if (air_compressor_type == "" || air_compressor_type == null || air_compressor_type == "undefined"
						|| air_compressor_type.trim().equals("undefined")) {
					air_compressor_type = null;
				}

				String airCompressorTotalHrsStr = request.getParameter("Air Compressor Total Hrs_" + i);
				int airCompressorTotalHrsAt = 0;

				try {
					if (airCompressorTotalHrsStr != null && !airCompressorTotalHrsStr.isEmpty()) {
						airCompressorTotalHrsAt = Integer.parseInt(airCompressorTotalHrsStr);
						if (airCompressorTotalHrsAt < 0) {
							model.put("msg", "Air Compressor Total Hrs Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Air Compressor Total Hrs Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String air_compressor_total_hrs = request.getParameter("Air Compressor Total Hrs_" + i);
				if (air_compressor_total_hrs == "" || air_compressor_total_hrs == null) {
					air_compressor_total_hrs = "0";
				}

				String airCompressorFailureAtStr = request.getParameter("Air Compressor Failure at_" + i);
				int airCompressorFailureAtAt = 0;

				try {
					if (airCompressorFailureAtStr != null && !airCompressorFailureAtStr.isEmpty()) {
						airCompressorFailureAtAt = Integer.parseInt(airCompressorFailureAtStr);
						if (airCompressorFailureAtAt < 0) {
							model.put("msg", "Air Compressor Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Air Compressor Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String air_compressor_failure_at = request.getParameter("Air Compressor Failure at_" + i);
				if (air_compressor_failure_at == "" || air_compressor_failure_at == null) {
					air_compressor_failure_at = "0";
				}
				String air_compressor_submission_dt1 = request.getParameter("Air Compressor Submission Dt (dd-mm-yyyy)_" + i);

				Date air_compressor_submission_dt = null;
				if (air_compressor_submission_dt1 != null && air_compressor_submission_dt1 != "undefined"
						&& !air_compressor_submission_dt1.trim().equals("undefined")
						&& !air_compressor_submission_dt1.trim().isEmpty()) {
					air_compressor_submission_dt = formatter_dash.parse(air_compressor_submission_dt1);
				} else {
					air_compressor_submission_dt = null;
				}

				String air_compressor_repair_comp_dt1 = request
						.getParameter("Air Compressor Repair Completion Dt (dd-mm-yyyy)_" + i);

				Date air_compressor_repair_comp_dt = null;
				if (air_compressor_repair_comp_dt1 != null && air_compressor_repair_comp_dt1 != "undefined"
						&& !air_compressor_repair_comp_dt1.trim().equals("undefined")
						&& !air_compressor_repair_comp_dt1.trim().isEmpty()) {
					air_compressor_repair_comp_dt = formatter_dash.parse(air_compressor_repair_comp_dt1);
				} else {
					air_compressor_repair_comp_dt = null;
				}

				String sgb_no = request.getParameter("SGB No_" + i);
				if (sgb_no == "" || sgb_no == null || sgb_no == "undefined" || sgb_no.trim().equals("undefined")) {
					sgb_no = null;
				}

				String sgb_type = request.getParameter("SGB Type_" + i);
				if (sgb_type == "" || sgb_type == null || sgb_type == "undefined"
						|| sgb_type.trim().equals("undefined")) {
					sgb_type = null;
				}

				String SGBTotalKmStr = request.getParameter("SGB Total Km_" + i);
				int SGBTotalKmAt = 0;

				try {
					if (SGBTotalKmStr != null && !SGBTotalKmStr.isEmpty()) {
						SGBTotalKmAt = Integer.parseInt(SGBTotalKmStr);
						if (SGBTotalKmAt < 0) {
							model.put("msg", "SGB Total Km Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "SGB Total Km Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String sgb_total_km = request.getParameter("SGB Total Km_" + i);
				if (sgb_total_km == "" || sgb_total_km == null) {
					sgb_total_km = "0";
				}

				String SGBFailureatStr = request.getParameter("SGB Failure at_" + i);
				int SGBFailureatAt = 0;

				try {
					if (SGBFailureatStr != null && !SGBFailureatStr.isEmpty()) {
						SGBFailureatAt = Integer.parseInt(SGBFailureatStr);
						if (SGBFailureatAt < 0) {
							model.put("msg", "SGB Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "SGB Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String sgb_failure_at = request.getParameter("SGB Failure at_" + i);
				if (sgb_failure_at == "" || sgb_failure_at == null) {
					sgb_failure_at = "0";
				}

				String sgb_submission_dt1 = request.getParameter("SGB Submission Dt (dd-mm-yyyy)_" + i);

				Date sgb_sub_dt = null;
				if (sgb_submission_dt1 != null && sgb_submission_dt1 != "undefined"
						&& !sgb_submission_dt1.trim().equals("undefined") && !sgb_submission_dt1.trim().isEmpty()) {
					sgb_sub_dt = formatter_dash.parse(sgb_submission_dt1);
				} else {
					sgb_sub_dt = null;
				}

				String sgb_repair_comp_dt1 = request.getParameter("SGB Repair Completion Dt (dd-mm-yyyy)_" + i);

				Date sgb_repair_comp_dt = null;
				if (sgb_repair_comp_dt1 != null && sgb_repair_comp_dt1 != "undefined"
						&& !sgb_repair_comp_dt1.trim().equals("undefined") && !sgb_repair_comp_dt1.trim().isEmpty()) {
					sgb_repair_comp_dt = formatter_dash.parse(sgb_repair_comp_dt1);
				} else {
					sgb_repair_comp_dt = null;
				}

				String igb_no = request.getParameter("IGB No_" + i);
				if (igb_no == "" || igb_no == null || igb_no == "undefined" || igb_no.trim().equals("undefined")) {
					igb_no = null;
				}

				String igb_type = request.getParameter("IGB Type_" + i);
				if (igb_type == "" || igb_type == null || igb_type == "undefined"
						|| igb_type.trim().equals("undefined")) {
					igb_type = null;
				}

				String IGBTotalKmStr = request.getParameter("IGB Total Km_" + i);
				int IGBTotalKmAt = 0;

				try {
					if (IGBTotalKmStr != null && !IGBTotalKmStr.isEmpty()) {
						IGBTotalKmAt = Integer.parseInt(IGBTotalKmStr);
						if (IGBTotalKmAt < 0) {
							model.put("msg", "IGB Total Km Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "IGB Total Km Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String igb_total_km = request.getParameter("IGB Total Km_" + i);
				if (igb_total_km == "" || igb_total_km == null) {
					igb_total_km = "0";
				}

				String IGBFailureatStr = request.getParameter("IGB Failure at_" + i);
				int IGBFailureatAt = 0;

				try {
					if (IGBFailureatStr != null && !IGBFailureatStr.isEmpty()) {
						IGBFailureatAt = Integer.parseInt(IGBFailureatStr);
						if (IGBFailureatAt < 0) {
							model.put("msg", "IGB Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "SGB Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String igb_failure_at = request.getParameter("IGB Failure at_" + i);
				if (igb_failure_at == "" || igb_failure_at == null) {
					igb_failure_at = "0";
				}

				String igb_submission_dt1 = request.getParameter("IGB Submission Dt (dd-mm-yyyy)_" + i);

				Date igb_submission_dt = null;
				if (igb_submission_dt1 != null && igb_submission_dt1 != "undefined"
						&& !igb_submission_dt1.trim().equals("undefined") && !igb_submission_dt1.trim().isEmpty()) {
					igb_submission_dt = formatter_dash.parse(igb_submission_dt1);
				} else {
					igb_submission_dt = null;
				}

				String igb_repair_comp_dt1 = request.getParameter("IGB Repair Completion Dt (dd-mm-yyyy)_" + i);

				Date igb_repair_comp_dt = null;
				if (igb_repair_comp_dt1 != null && igb_repair_comp_dt1 != "undefined"
						&& !igb_repair_comp_dt1.trim().equals("undefined") && !igb_repair_comp_dt1.trim().isEmpty()) {
					igb_repair_comp_dt = formatter_dash.parse(igb_repair_comp_dt1);
				} else {
					igb_repair_comp_dt = null;
				}

				String trackKmsStr = request.getParameter("Track Assy total Km_" + i);
				int trackKmsAt = 0;

				try {
					if (trackKmsStr != null && !trackKmsStr.isEmpty()) {
						trackKmsAt = Integer.parseInt(trackKmsStr);
						if (trackKmsAt < 0) {
							model.put("msg", "Track Kms Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Track Kms Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String track_kms = request.getParameter("Track Assy total Km_" + i);
				if (track_kms == "" || track_kms == null) {
					track_kms = "0";
				}

				String track_assy_ser = request.getParameter("Track Assy Serviceability (S/ UNSV)_" + i);
				if (track_assy_ser == "" || track_assy_ser == null || track_assy_ser == "undefined"
						|| track_assy_ser.trim().equals("undefined")) {
					track_assy_ser = null;
				}

				String TrackAssyFailureatStr = request.getParameter("Track Assy Failure at_" + i);
				int TrackAssyFailureatAt = 0;

				try {
					if (TrackAssyFailureatStr != null && !TrackAssyFailureatStr.isEmpty()) {
						TrackAssyFailureatAt = Integer.parseInt(TrackAssyFailureatStr);
						if (TrackAssyFailureatAt < 0) {
							model.put("msg", "Track Assy Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Track Assy Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String track_assy_failure_at = request.getParameter("Track Assy Failure at_" + i);
				if (track_assy_failure_at == "" || track_assy_failure_at == null) {
					track_assy_failure_at = "0";
				}

				String sprocketAssyTotalKmStr = request.getParameter("Sprocket Assy Total Km_" + i);
				int sprocketAssyTotalKmAt = 0;

				try {
					if (sprocketAssyTotalKmStr != null && !sprocketAssyTotalKmStr.isEmpty()) {
						sprocketAssyTotalKmAt = Integer.parseInt(sprocketAssyTotalKmStr);
						if (sprocketAssyTotalKmAt < 0) {
							model.put("msg", "Sprocket Assy Total Km Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Sprocket Assy Total Km Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String sprocket_assy_total_km = request.getParameter("Sprocket Assy Total Km_" + i);
				if (sprocket_assy_total_km == "" || sprocket_assy_total_km == null) {
					sprocket_assy_total_km = "0";
				}

				String SprocketAssyFailureatStr = request.getParameter("Sprocket Assy Failure at_" + i);
				int SprocketAssyFailureatAt = 0;

				try {
					if (SprocketAssyFailureatStr != null && !SprocketAssyFailureatStr.isEmpty()) {
						SprocketAssyFailureatAt = Integer.parseInt(SprocketAssyFailureatStr);
						if (SprocketAssyFailureatAt < 0) {
							model.put("msg", "Sprocket Assy Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Sprocket Assy Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String sprocket_assy_failure_at = request.getParameter("Sprocket Assy Failure at_" + i);
				if (sprocket_assy_failure_at == "" || sprocket_assy_failure_at == null) {
					sprocket_assy_failure_at = "0";
				}

				String sprocket_assy_ser = request.getParameter("Sprocket Assy Serviceability (S/ UNSV)_" + i);
				if (sprocket_assy_ser == "" || sprocket_assy_ser == null || sprocket_assy_ser == "undefined"
						|| sprocket_assy_ser.trim().equals("undefined")) {
					sprocket_assy_ser = null;
				}

				String PumpDriveMotorHrStr = request.getParameter("Pump Drive Motor Hr_" + i);
				int PumpDriveMotorHrAt = 0;

				try {
					if (PumpDriveMotorHrStr != null && !PumpDriveMotorHrStr.isEmpty()) {
						PumpDriveMotorHrAt = Integer.parseInt(PumpDriveMotorHrStr);
						if (PumpDriveMotorHrAt < 0) {
							model.put("msg", "Pump Drive Motor Hr Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Pump Drive Motor Hr Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String pump_drive_motor_hr = request.getParameter("Pump Drive Motor Hr_" + i);
				if (pump_drive_motor_hr == "" || pump_drive_motor_hr == null) {
					pump_drive_motor_hr = "0";
				}

				String pump_drive_motor_ser = request.getParameter("Pump Drive Motor Serviceability (S/ UNSV)_" + i);
				if (pump_drive_motor_ser == "" || pump_drive_motor_ser == null || pump_drive_motor_ser == "undefined"
						|| pump_drive_motor_ser.trim().equals("undefined")) {
					pump_drive_motor_ser = null;
				}

				String PumpDriveMotorFailureatStr = request.getParameter("Pump Drive Motor Failure at_" + i);
				int PumpDriveMotorFailureatAt = 0;

				try {
					if (PumpDriveMotorFailureatStr != null && !PumpDriveMotorFailureatStr.isEmpty()) {
						PumpDriveMotorFailureatAt = Integer.parseInt(PumpDriveMotorFailureatStr);
						if (PumpDriveMotorFailureatAt < 0) {
							model.put("msg", "Pump Drive Motor Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Pump Drive Motor Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String pump_drive_motor_failure_at = request.getParameter("Pump Drive Motor Failure at_" + i);
				if (pump_drive_motor_failure_at == "" || pump_drive_motor_failure_at == null) {
					pump_drive_motor_failure_at = "0";
				}

				String StarterGenrTotalHrsStr = request.getParameter("Starter Genr Total Hrs_" + i);
				int StarterGenrTotalHrsAt = 0;

				try {
					if (StarterGenrTotalHrsStr != null && !StarterGenrTotalHrsStr.isEmpty()) {
						StarterGenrTotalHrsAt = Integer.parseInt(StarterGenrTotalHrsStr);
						if (StarterGenrTotalHrsAt < 0) {
							model.put("msg", "Starter Genr Total Hrs Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Starter Genr Total Hrs Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String starter_genr_total_hr = request.getParameter("Starter Genr Total Hrs_" + i);
				if (starter_genr_total_hr == "" || starter_genr_total_hr == null
						|| starter_genr_total_hr.equals("undefined")) {
					starter_genr_total_hr = "0";
				}

				String starter_genr_ser = request.getParameter("Starter Genr Serviceability (S/ UNSV)_" + i);
				if (starter_genr_ser == "" || starter_genr_ser == null || starter_genr_ser == "undefined"
						|| starter_genr_ser.trim().equals("undefined")) {
					starter_genr_ser = null;
				}

				String StarterGenrFailureatStr = request.getParameter("Starter Genr Failure at_" + i);
				int StarterGenrFailureatAt = 0;

				try {
					if (StarterGenrFailureatStr != null && !StarterGenrFailureatStr.isEmpty()) {
						StarterGenrFailureatAt = Integer.parseInt(StarterGenrFailureatStr);
						if (StarterGenrFailureatAt < 0) {
							model.put("msg", "Starter Genr Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Starter Genr Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String starter_genr_failure_at = request.getParameter("Starter Genr Failure at_" + i);
				if (starter_genr_failure_at == "" || starter_genr_failure_at == null
						|| starter_genr_failure_at.equals("undefined")) {
					starter_genr_failure_at = "0";
				}

				String turbo_charger_type = request.getParameter("Turbo Charger Type_" + i);
				if (turbo_charger_type == "" || turbo_charger_type == null || turbo_charger_type == "undefined"
						|| turbo_charger_type.trim().equals("undefined")) {
					turbo_charger_type = null;
				}

				String TurboChargerDefectatStr = request.getParameter("Turbo Charger Defect at_" + i);
				int TurboChargerDefectatAt = 0;

				try {
					if (TurboChargerDefectatStr != null && !TurboChargerDefectatStr.isEmpty()) {
						TurboChargerDefectatAt = Integer.parseInt(TurboChargerDefectatStr);
						if (TurboChargerDefectatAt < 0) {
							model.put("msg", "Turbo Charger Defect At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Turbo Charger Defect At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String turbo_charger_defect_at = request.getParameter("Turbo Charger Defect at_" + i);
				if (turbo_charger_defect_at == "" || turbo_charger_defect_at == null
						|| turbo_charger_defect_at.equals("undefined")) {
					turbo_charger_defect_at = "0";
				}

				String turbo_charger_submission_dt1 = request.getParameter("Turbo Charger Submission Dt (dd-mm-yyyy)_" + i);
				Date turbo_charger_submission_dt = null;
				if (turbo_charger_submission_dt1 != null && turbo_charger_submission_dt1 != "undefined"
						&& !turbo_charger_submission_dt1.trim().equals("undefined")
						&& !turbo_charger_submission_dt1.trim().isEmpty()) {
					turbo_charger_submission_dt = formatter_dash.parse(turbo_charger_submission_dt1);
				} else {
					turbo_charger_submission_dt = null;
				}

				String turbo_charger_repair_comp_dt1 = request.getParameter("Turbo Charger Repair Completion Dt (dd-mm-yyyy)_" + i);
				Date turbo_charger_repair_comp_dt = null;
				if (turbo_charger_repair_comp_dt1 != null && turbo_charger_repair_comp_dt1 != "undefined"
						&& !turbo_charger_repair_comp_dt1.trim().equals("undefined")
						&& !turbo_charger_repair_comp_dt1.trim().isEmpty()) {
					turbo_charger_repair_comp_dt = formatter_dash.parse(turbo_charger_repair_comp_dt1);
				} else {
					turbo_charger_repair_comp_dt = null;
				}

				String engine_demand_dt1 = request.getParameter("Engine Demand Dt (dd-mm-yyyy)_" + i);
				Date engine_demand_dt = null;
				if (engine_demand_dt1 != null && engine_demand_dt1 != "undefined"
						&& !engine_demand_dt1.trim().equals("undefined") && !engine_demand_dt1.trim().isEmpty()) {
					engine_demand_dt = formatter_dash.parse(engine_demand_dt1);
				} else {
					engine_demand_dt = null;
				}

				Date engine_rel_dt = null;
				String engine_rel_dt1 = request.getParameter("Engine Rel Dt (dd-mm-yyyy)_" + i);
				if (engine_rel_dt1 != null && engine_rel_dt1 != "undefined"
						&& !engine_rel_dt1.trim().equals("undefined") && !engine_rel_dt1.trim().isEmpty()) {
					engine_rel_dt = formatter_dash.parse(engine_rel_dt1);
				} else {
					engine_rel_dt = null;
				}

				String FireFightingSysCylindersAuthStr = request.getParameter("Fire Fighting Sys Cylinders Auth_" + i);
				int FireFightingSysCylindersAuthAt = 0;

				try {
					if (FireFightingSysCylindersAuthStr != null && !FireFightingSysCylindersAuthStr.isEmpty()) {
						FireFightingSysCylindersAuthAt = Integer.parseInt(FireFightingSysCylindersAuthStr);
						if (FireFightingSysCylindersAuthAt < 0) {
							model.put("msg", "Fire Fighting Sys Cylinders Auth Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Fire Fighting Sys Cylinders Auth Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String fire_fight_sys_cyl_auth = request.getParameter("Fire Fighting Sys Cylinders Auth_" + i);
				if (fire_fight_sys_cyl_auth == "" || fire_fight_sys_cyl_auth == null
						|| fire_fight_sys_cyl_auth.equals("undefined")) {
					fire_fight_sys_cyl_auth = "0";
				}

				String FireFightingSysCylindersHeldStr = request.getParameter("Fire Fighting Sys Cylinders Held_" + i);
				int FireFightingSysCylindersHeldAt = 0;

				try {
					if (FireFightingSysCylindersHeldStr != null && !FireFightingSysCylindersHeldStr.isEmpty()) {
						FireFightingSysCylindersHeldAt = Integer.parseInt(FireFightingSysCylindersHeldStr);
						if (FireFightingSysCylindersHeldAt < 0) {
							model.put("msg", "Fire Fighting Sys Cylinders Held Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Fire Fighting Sys Cylinders Held Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String fire_fight_sys_cyl_held = request.getParameter("Fire Fighting Sys Cylinders Held_" + i);
				if (fire_fight_sys_cyl_held == "" || fire_fight_sys_cyl_held == null
						|| fire_fight_sys_cyl_held.equals("undefined")) {
					fire_fight_sys_cyl_held = "0";
				}

				String FireFightingSysCylindersEmptyStr = request
						.getParameter("Fire Fighting Sys Cylinders Empty_" + i);
				int FireFightingSysCylindersEmptyAt = 0;

				try {
					if (FireFightingSysCylindersEmptyStr != null && !FireFightingSysCylindersEmptyStr.isEmpty()) {
						FireFightingSysCylindersEmptyAt = Integer.parseInt(FireFightingSysCylindersEmptyStr);
						if (FireFightingSysCylindersEmptyAt < 0) {
							model.put("msg", "Fire Fighting Sys Cylinders Empty Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Fire Fighting Sys Cylinders Empty Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String fire_fight_sys_cyl_empty = request.getParameter("Fire Fighting Sys Cylinders Empty_" + i);
				if (fire_fight_sys_cyl_empty == "" || fire_fight_sys_cyl_empty == null
						|| fire_fight_sys_cyl_empty.equals("undefined")) {
					fire_fight_sys_cyl_empty = "0";
				}

				Date fire_fight_cyl_demand_dt = null;
				String fire_fight_cyl_demand_dt1 = request.getParameter("Fire Fighting Cylinders Demand Dt_" + i);
				if (fire_fight_cyl_demand_dt1 != null && fire_fight_cyl_demand_dt1 != "undefined"
						&& !fire_fight_cyl_demand_dt1.trim().equals("undefined")
						&& !fire_fight_cyl_demand_dt1.trim().isEmpty()) {
					fire_fight_cyl_demand_dt = formatter_dash.parse(fire_fight_cyl_demand_dt1);
				} else {
					fire_fight_cyl_demand_dt = null;
				}

				String cbrn_sys_over_pressure = request.getParameter("CBRN Sys Over Presssure (Yes/ No)_" + i);

				String CBRNFilterQtyStr = request.getParameter("CBRN Filter Qty_" + i);
				int CBRNFilterQtyAt = 0;

				try {
					if (CBRNFilterQtyStr != null && !CBRNFilterQtyStr.isEmpty()) {
						CBRNFilterQtyAt = Integer.parseInt(CBRNFilterQtyStr);
						if (CBRNFilterQtyAt < 0) {
							model.put("msg", "CBRN Filter Qty Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "CBRN Filter Qty Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String cbrn_filter_qty = request.getParameter("CBRN Filter Qty_" + i);
				if (cbrn_filter_qty == "" || cbrn_filter_qty == null || cbrn_filter_qty.equals("undefined")) {
					cbrn_filter_qty = "0";
				}

				String cbrn_filter_ser = request.getParameter("CBRN Filter Serviceability (S/ UNSV)_" + i);
				if (turbo_charger_type == "" || turbo_charger_type == null || turbo_charger_type == "undefined"
						|| turbo_charger_type.trim().equals("undefined")) {
					turbo_charger_type = null;
				}

				String pkuza_ser = request.getParameter("PKUZ1A Serviceability (S/ UNSV)_" + i);
				if (pkuza_ser == "" || pkuza_ser == null || pkuza_ser == "undefined"
						|| pkuza_ser.trim().equals("undefined")) {
					pkuza_ser = null;
				}

				String bas_a_ser = request.getParameter("BAS 6A Serviecability_" + i);
				if (bas_a_ser == "" || bas_a_ser == null || bas_a_ser == "undefined"
						|| bas_a_ser.trim().equals("undefined")) {
					bas_a_ser = null;
				}

				String cbrn_spl_blower_ser = request.getParameter("CBRN Spl Blower Serviceability (S/ UNSV)_" + i);
				if (cbrn_spl_blower_ser == "" || cbrn_spl_blower_ser == null || cbrn_spl_blower_ser == "undefined"
						|| cbrn_spl_blower_ser.trim().equals("undefined")) {
					cbrn_spl_blower_ser = null;
				}

				String mine_plough_ser = request.getParameter("Mine Plough Serviceability (S/ UNSV)_" + i);
				if (mine_plough_ser == "" || mine_plough_ser == null || mine_plough_ser == "undefined"
						|| mine_plough_ser.trim().equals("undefined")) {
					mine_plough_ser = null;
				}

				String emp_ser = request.getParameter("EMP Serviceability (S/ UNSV)_" + i);
				if (emp_ser == "" || emp_ser == null || emp_ser == "undefined" || emp_ser.trim().equals("undefined")) {
					emp_ser = null;
				}

				String mrs_ser = request.getParameter("MRS Serviceability (S/ UNSV)_" + i);
				if (mrs_ser == "" || mrs_ser == null || mrs_ser == "undefined" || mrs_ser.trim().equals("undefined")) {
					mrs_ser = null;
				}

				String mrs_type = request.getParameter("Main RS Type_" + i);
				if (mrs_type == "" || mrs_type == null || mrs_type == "undefined"
						|| mrs_type.trim().equals("undefined")) {
					mrs_type = null;
				}

				String mrs_qty = request.getParameter("Main RS Qty_" + i);
				if (mrs_qty == "" || mrs_qty == null || mrs_qty.equals("undefined")) {
					mrs_qty = "0";
				}

				String dch_installed = request.getParameter("DCH Installed (Y/ N)_" + i);
				if (dch_installed == "" || dch_installed == null || dch_installed == "undefined"
						|| dch_installed.trim().equals("undefined")) {
					dch_installed = null;
				}

				String dch_type = request.getParameter("DCH Type_" + i);
				if (dch_type == "" || dch_type == null || dch_type == "undefined"
						|| dch_type.trim().equals("undefined")) {
					dch_type = null;
				}

				Date dch_installed_dt = null;
				String dch_installed_dt1 = request.getParameter("DCH Installed Dt (dd-mm-yyyyy)_" + i);
				if (dch_installed_dt1 != null && !dch_installed_dt1.trim().isEmpty()) {
					try {
						dch_installed_dt = formatter_dash.parse(dch_installed_dt1);
					} catch (ParseException e) {
					}
				} else {
					dch_installed_dt = null;
				}

				String dch_vintage1 = request.getParameter("DCH Vintage_" + i);
				Date dch_vintage = null;
				if (dch_vintage1 != null && dch_vintage1 != "undefined" && !dch_vintage1.trim().equals("undefined")
						&& !dch_vintage1.trim().isEmpty()) {
					dch_vintage = formatter_dash.parse(dch_vintage1);
				} else {
					dch_vintage = null;
				}

				String dch_ser = request.getParameter("DCH Serviceability (S/ UNSV)_" + i);
				if (dch_ser == "" || dch_ser == null || dch_ser == "undefined" || dch_ser.trim().equals("undefined")) {
					dch_ser = null;
				}

				Date cti_install_dt = null;
				String cti_install_dt1 = request.getParameter("CTI Installation Dt (dd-mm-yyyy)_" + i);
				if (cti_install_dt1 != null && cti_install_dt1 != "undefined"
						&& !cti_install_dt1.trim().equals("undefined") && !cti_install_dt1.trim().isEmpty()) {
					cti_install_dt = formatter_dash.parse(cti_install_dt1);
				} else {
					cti_install_dt = null;
				}

				String cti_vintage1 = request.getParameter("CTI Vintage_" + i);
				Date cti_vintage = null;
				if (cti_vintage1 != null && cti_vintage1 != "undefined" && !cti_vintage1.trim().equals("undefined")
						&& !cti_vintage1.trim().isEmpty()) {
					cti_vintage = formatter_dash.parse(cti_vintage1);
				} else {
					cti_vintage = null;
				}

				String cti_ser = request.getParameter("CTI Serviceability (S/ UNSV)_" + i);
				if (cti_ser == "" || cti_ser == null || cti_ser == "undefined" || cti_ser.trim().equals("undefined")) {
					cti_ser = null;
				}

				String main_gun_article_no = request.getParameter("Main Gun Article No_" + i);
				if (main_gun_article_no == "" || main_gun_article_no == null || main_gun_article_no == "undefined"
						|| main_gun_article_no.trim().equals("undefined")) {
					main_gun_article_no = null;
				}

				String type_gun_article = request.getParameter("Type of Main Gun Article_" + i);
				if (type_gun_article == "" || type_gun_article == null || type_gun_article == "undefined"
						|| type_gun_article.trim().equals("undefined")) {
					type_gun_article = null;
				}

				String main_gun_article_qtr = request.getParameter("Main Gun Article Qtr  (I /II/ III/ IV)_" + i);
				if (main_gun_article_qtr == "" || main_gun_article_qtr == null || main_gun_article_qtr == "undefined"
						|| main_gun_article_qtr.trim().equals("undefined")) {
					main_gun_article_qtr = null;
				}

				String main_gun_article_ser = request.getParameter("Main Gun Article Serviceability (S/ UNSV)_" + i);
				if (main_gun_article_ser == "" || main_gun_article_ser == null || main_gun_article_ser == "undefined"
						|| main_gun_article_ser.trim().equals("undefined")) {
					main_gun_article_ser = null;
				}

				String MainGunArticleEFCStr = request.getParameter("Main Gun Article EFC_" + i);
				int MainGunArticleEFCAt = 0;

				try {
					if (MainGunArticleEFCStr != null && !MainGunArticleEFCStr.isEmpty()) {
						MainGunArticleEFCAt = Integer.parseInt(MainGunArticleEFCStr);
						if (MainGunArticleEFCAt < 0) {
							model.put("msg", "Main Gun Article EFC Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Main Gun Article EFC Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String main_gun_article_efc = request.getParameter("Main Gun Article EFC_" + i);
				if (main_gun_article_efc == "" || main_gun_article_efc == null
						|| main_gun_article_efc.equals("undefined")) {
					main_gun_article_efc = "0";
				}

				String NoofMslsFiredStr = request.getParameter("No of Msls Fired_" + i);
				int NoofMslsFiredAt = 0;

				try {
					if (NoofMslsFiredStr != null && !NoofMslsFiredStr.isEmpty()) {
						NoofMslsFiredAt = Integer.parseInt(NoofMslsFiredStr);
						if (NoofMslsFiredAt < 0) {
							model.put("msg", "No of Msls Fired Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "No of Msls Fired Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String no_of_msls_fired = request.getParameter("No of Msls Fired_" + i);
				if (no_of_msls_fired == "" || no_of_msls_fired == null || no_of_msls_fired.equals("undefined")) {
					no_of_msls_fired = "0";
				}

				String NoofMslsFailedStr = request.getParameter("No of Msls Failed_" + i);
				int NoofMslsFailedAt = 0;

				try {
					if (NoofMslsFailedStr != null && !NoofMslsFailedStr.isEmpty()) {
						NoofMslsFailedAt = Integer.parseInt(NoofMslsFailedStr);
						if (NoofMslsFailedAt < 0) {
							model.put("msg", "No of Msls Failed Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "No of Msls Failed Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String no_of_msls_failed = request.getParameter("No of Msls Failed_" + i);
				if (no_of_msls_failed == "" || no_of_msls_failed == null || no_of_msls_failed.equals("undefined")) {
					no_of_msls_failed = "0";
				}

				String sec_gun_type = request.getParameter("Secy Gun Type_" + i);
				if (sec_gun_type == "" || sec_gun_type == null || sec_gun_type == "undefined"
						|| sec_gun_type.trim().equals("undefined")) {
					sec_gun_type = null;
				}

				String sec_gun_regn_no = request.getParameter("Secy Gun Regn No_" + i);
				if (sec_gun_regn_no == "" || sec_gun_regn_no == null || sec_gun_regn_no == "undefined"
						|| sec_gun_regn_no.trim().equals("undefined")) {
					sec_gun_regn_no = null;
				}

				String sec_gun_ser = request.getParameter("Secy Gun Serviceability (S/ UNSV)_" + i);
				if (sec_gun_ser == "" || sec_gun_ser == null || sec_gun_ser == "undefined"
						|| sec_gun_ser.trim().equals("undefined")) {
					sec_gun_ser = null;
				}

				String aa_gun_type = request.getParameter("AA Gun Type_" + i);
				if (aa_gun_type == "" || aa_gun_type == null || aa_gun_type == "undefined"
						|| aa_gun_type.trim().equals("undefined")) {
					aa_gun_type = null;
				}

				String aa_gun_regn_no = request.getParameter("AA Gun Regn No_" + i);
				if (aa_gun_regn_no == "" || aa_gun_regn_no == null || aa_gun_regn_no == "undefined"
						|| aa_gun_regn_no.trim().equals("undefined")) {
					aa_gun_regn_no = null;
				}

				String aa_gun_ser = request.getParameter("AA Gun Serviceability (S/ UNSV)_" + i);
				if (aa_gun_ser == "" || aa_gun_ser == null || aa_gun_ser == "undefined"
						|| aa_gun_ser.trim().equals("undefined")) {
					aa_gun_ser = null;
				}

				String msl_launcher_regn_no = request.getParameter("Msl Launcher Regn No_" + i);
				if (msl_launcher_regn_no == "" || msl_launcher_regn_no == null || msl_launcher_regn_no == "undefined"
						|| msl_launcher_regn_no.trim().equals("undefined")) {
					msl_launcher_regn_no = null;
				}

				String msl_launcher_ser = request.getParameter("Msl Launcher Serviceability (S/ UNSV)_" + i);
				if (msl_launcher_ser == "" || msl_launcher_ser == null || msl_launcher_ser == "undefined"
						|| msl_launcher_ser.trim().equals("undefined")) {
					msl_launcher_ser = null;
				}

				String msl_launcher_oh_due = request.getParameter("Msl launcher OH Due_" + i);
				if (msl_launcher_oh_due == "" || msl_launcher_oh_due == null || msl_launcher_oh_due == "undefined"
						|| msl_launcher_oh_due.trim().equals("undefined")) {
					msl_launcher_oh_due = null;
				}

				Date msl_lr_oh_done_dt = null;
				String msl_lr_oh_done_dt1 = request.getParameter("Msl Lr OH Done Dt (dd-mm-yyyy)_" + i);

				if (msl_lr_oh_done_dt1 != null && msl_lr_oh_done_dt1 != "undefined"
						&& !msl_lr_oh_done_dt1.trim().equals("undefined") && !msl_lr_oh_done_dt1.trim().isEmpty()) {
					msl_lr_oh_done_dt = formatter_dash.parse(msl_lr_oh_done_dt1);
				} else {
					msl_lr_oh_done_dt = null;
				}

				Date tisas_old_instln_dt = null;
				String tisas_old_instln_dt1 = request.getParameter("TISAS (Old) Instln Date_" + i);

				if (tisas_old_instln_dt1 != null && tisas_old_instln_dt1 != "undefined"
						&& !tisas_old_instln_dt1.trim().equals("undefined") && !tisas_old_instln_dt1.trim().equals("")
						&& !tisas_old_instln_dt1.trim().isEmpty()) {
					tisas_old_instln_dt = formatter_dash.parse(tisas_old_instln_dt1);
				} else {
					tisas_old_instln_dt = null;
				}

				String tisas_old_vintage1 = request.getParameter("TISAS (Old) Vintage_" + i);
				Date tisas_old_vintage = null;
				if (tisas_old_vintage1 != null && !tisas_old_vintage1.trim().equals("")
						&& tisas_old_vintage1 != "undefined" && !tisas_old_vintage1.trim().equals("undefined")
						&& !tisas_old_vintage1.equals("DD/MM/YYYY") && !tisas_old_vintage1.trim().isEmpty()) {
					tisas_old_vintage = formatter_dash.parse(tisas_old_vintage1);
				} else {
					tisas_old_vintage = null;
				}

				String tisas_old_ser = request.getParameter("TISAS (Old) Serviceability (S/ UNSV)_" + i);
				if (tisas_old_ser == "" || tisas_old_ser == null || tisas_old_ser == "undefined"
						|| tisas_old_ser.trim().equals("undefined")) {
					tisas_old_ser = null;
				}

				Date tisas_new_instln_dt = null;
				String tisas_new_instln_dt1 = request.getParameter("TISAS (New) Installation Dt (dd-mm-yyyy)_" + i);
				if (tisas_new_instln_dt1 != null && !tisas_new_instln_dt1.trim().isEmpty()
						&& tisas_new_instln_dt1 != "undefined" && !tisas_new_instln_dt1.trim().equals("undefined")) {
					tisas_new_instln_dt = formatter_dash.parse(tisas_new_instln_dt1);
				} else {
					tisas_new_instln_dt = null;
				}

				String tisas_new_vintage1 = request.getParameter("TISAS (New) Vintage_" + i);
				Date tisas_new_vintage = null;
				if (tisas_new_vintage1 != null && !tisas_new_vintage1.trim().isEmpty()
						&& tisas_new_vintage1 != "undefined" && !tisas_new_vintage1.trim().equals("undefined")) {
					tisas_new_vintage = formatter_dash.parse(tisas_new_vintage1);
				} else {
					tisas_new_vintage = null;
				}

				String tisas_new_ser = request.getParameter("TISAS (New) Serviceability (S/ UNSV)_" + i);
				if (tisas_new_ser == "" || tisas_new_ser == null || tisas_new_ser == "undefined"
						|| tisas_new_ser.trim().equals("undefined")) {
					tisas_new_ser = null;
				}

				String tifcs_type = request.getParameter("TIFCS Type (1000/ 418/ 96)_" + i);
				if (tifcs_type == "" || tifcs_type == null || tifcs_type == "undefined"
						|| tifcs_type.trim().equals("undefined")) {
					tifcs_type = null;
				}

				String tifcs_regn_no = request.getParameter("TIFCS Regn No_" + i);
				if (tifcs_regn_no == "" || tifcs_regn_no == null || tifcs_regn_no == "undefined"
						|| tifcs_regn_no.trim().equals("undefined")) {
					tifcs_regn_no = null;
				}

				String tifcs_instln_dt1 = request.getParameter("TIFCS Installation Dt (dd-mm-yyyy)_" + i);
				Date tifcs_instln_dt = null;
				if (tifcs_instln_dt1 != null && !tifcs_instln_dt1.trim().isEmpty() && tifcs_instln_dt1 != "undefined"
						&& !tifcs_instln_dt1.trim().equals("undefined")) {
					tifcs_instln_dt = formatter_dash.parse(tifcs_instln_dt1);
				} else {
					tifcs_instln_dt = null;
				}

				String tifcs_vintage1 = request.getParameter("TIFCS Vintage_" + i);
				Date tifcs_vintage = null;
				if (tifcs_vintage1 != null && !tifcs_vintage1.trim().isEmpty() && tifcs_vintage1 != "undefined"
						&& !tifcs_vintage1.trim().equals("undefined")) {
					tifcs_vintage = formatter_dash.parse(tifcs_vintage1);
				} else {
					tifcs_vintage = null;
				}

				String RelayKRHrStr = request.getParameter("Relay KR 175 Hr_" + i);
				int RelayKRHrAt = 0;

				try {
					if (RelayKRHrStr != null && !RelayKRHrStr.isEmpty()) {
						RelayKRHrAt = Integer.parseInt(RelayKRHrStr);
						if (RelayKRHrAt < 0) {
							model.put("msg", "Relay KR 175 Hr Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Relay KR 175 Hr Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String relay_kr_hr = request.getParameter("Relay KR 175 Hr_" + i);
				if (relay_kr_hr == "" || relay_kr_hr == null) {
					relay_kr_hr = "0";
				}

				String relay_kr_ser = request.getParameter("Relay KR 175 Serviceability (S/ UNSV)_" + i);

				String RelayKRFailureatStr = request.getParameter("Relay KR 175 Failure at_" + i);
				int RelayKRFailureatAt = 0;

				try {
					if (RelayKRFailureatStr != null && !RelayKRFailureatStr.isEmpty()) {
						RelayKRFailureatAt = Integer.parseInt(RelayKRFailureatStr);
						if (RelayKRFailureatAt < 0) {
							model.put("msg", "Relay KR 175 Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Relay KR 175 Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String relay_kr_failure_at = request.getParameter("Relay KR 175 Failure at_" + i);
				if (relay_kr_failure_at == "" || relay_kr_failure_at == null) {
					relay_kr_failure_at = "0";
				}

				String LRFHrStr = request.getParameter("LRF Hr_" + i);
				int LRFHrAt = 0;

				try {
					if (LRFHrStr != null && !LRFHrStr.isEmpty()) {
						LRFHrAt = Integer.parseInt(LRFHrStr);
						if (LRFHrAt < 0) {
							model.put("msg", "LRF Hr Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "LRF Hr Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String lrf_hr = request.getParameter("LRF Hr_" + i);
				if (lrf_hr == "" || lrf_hr == null) {
					lrf_hr = "0";
				}

				String lrf_ser = request.getParameter("LRF Serviceability (S/ UNSV)_" + i);

				String LRFFailureatStr = request.getParameter("LRF Failure at_" + i);
				int LRFFailureatAt = 0;

				try {
					if (LRFFailureatStr != null && !LRFFailureatStr.isEmpty()) {
						LRFFailureatAt = Integer.parseInt(LRFFailureatStr);
						if (LRFFailureatAt < 0) {
							model.put("msg", "LRF Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "LRF Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String lrf_failure_at = request.getParameter("LRF Failure at_" + i);
				if (lrf_failure_at == "" || lrf_failure_at == null) {
					lrf_failure_at = "0";
				}

				String GyroDirectionIndicatorHrStr = request.getParameter("Gyro Direction Indicator Hr_" + i);
				int GyroDirectionIndicatorHrAt = 0;

				try {
					if (GyroDirectionIndicatorHrStr != null && !GyroDirectionIndicatorHrStr.isEmpty()) {
						GyroDirectionIndicatorHrAt = Integer.parseInt(GyroDirectionIndicatorHrStr);
						if (GyroDirectionIndicatorHrAt < 0) {
							model.put("msg", "Gyro Direction Indicator Hr Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Gyro Direction Indicator Hr Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String gyro_direc_indi_hr = request.getParameter("Gyro Direction Indicator Hr_" + i);
				if (gyro_direc_indi_hr == "" || gyro_direc_indi_hr == null) {
					gyro_direc_indi_hr = "0";
				}

				String gyro_direc_indi_ser = request
						.getParameter("Gyro Direction Indicator Serviceability (S/ UNSV)_" + i);
				if (gyro_direc_indi_ser == "" || gyro_direc_indi_ser == null || gyro_direc_indi_ser == "undefined"
						|| gyro_direc_indi_ser.trim().equals("undefined")) {
					gyro_direc_indi_ser = null;
				}

				String GyroDirectionFailureatStr = request.getParameter("Gyro Direction Failure at_" + i);
				int GyroDirectionFailureatAt = 0;

				try {
					if (GyroDirectionFailureatStr != null && !GyroDirectionFailureatStr.isEmpty()) {
						GyroDirectionFailureatAt = Integer.parseInt(GyroDirectionFailureatStr);
						if (GyroDirectionFailureatAt < 0) {
							model.put("msg", "Gyro Direction Failure at Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Gyro Direction Failure at Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String gyro_direc_failure_at = request.getParameter("Gyro Direction Failure at_" + i);
				if (gyro_direc_failure_at == "" || gyro_direc_failure_at == null
						|| gyro_direc_failure_at.equals("undefined")) {
					gyro_direc_failure_at = "0";
				}

				String DistributionBoxHrStr = request.getParameter("Distribution Box Hr_" + i);
				int DistributionBoxHrAt = 0;

				try {
					if (DistributionBoxHrStr != null && !DistributionBoxHrStr.isEmpty()) {
						DistributionBoxHrAt = Integer.parseInt(DistributionBoxHrStr);
						if (DistributionBoxHrAt < 0) {
							model.put("msg", "Distribution Box Hr Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Distribution Box Hr Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String dist_box_hr = request.getParameter("Distribution Box Hr_" + i);
				if (dist_box_hr == "" || dist_box_hr == null || dist_box_hr.equals("undefined")) {
					dist_box_hr = "0";
				}

				String dist_box_ser = request.getParameter("Distribution Box Serviceability (S/ UNSV)_" + i);
				if (dist_box_ser == "" || dist_box_ser == null || dist_box_ser == "undefined"
						|| dist_box_ser.trim().equals("undefined")) {
					dist_box_ser = null;
				}

				String DistributionBoxFailureatStr = request.getParameter("Distribution Box Failure at_" + i);
				int DistributionBoxFailureatAt = 0;

				try {
					if (DistributionBoxFailureatStr != null && !DistributionBoxFailureatStr.isEmpty()) {
						DistributionBoxFailureatAt = Integer.parseInt(DistributionBoxFailureatStr);
						if (DistributionBoxFailureatAt < 0) {
							model.put("msg", "Distribution Box Failure At Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Distribution Box Failure At Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String dist_box_failure_at = request.getParameter("Distribution Box Failure at_" + i);
				if (dist_box_failure_at == "" || dist_box_failure_at == null
						|| dist_box_failure_at.equals("undefined")) {
					dist_box_failure_at = "0";
				}

				String tim_fitted = request.getParameter("TIM Fitted (Y/ N)_" + i);
				if (tim_fitted == "" || tim_fitted == null || tim_fitted == "undefined"
						|| tim_fitted.trim().equals("undefined")) {
					tim_fitted = null;
				}

				String tim_intls_dt1 = request.getParameter("TIM Instln (dd-mm-yyyy)_" + i);
				Date tim_intls_dt = null;
				if (tim_intls_dt1 != null && !tim_intls_dt1.trim().isEmpty() && tim_intls_dt1 != "undefined"
						&& !tim_intls_dt1.trim().equals("undefined")) {
					tim_intls_dt = formatter_dash.parse(tim_intls_dt1);
				} else {
					tim_intls_dt = null;
				}

				String tim_vintage1 = request.getParameter("TIM Vintage_" + i);
				Date tim_vintage = null;
				if (tim_vintage1 != null && !tim_vintage1.trim().isEmpty()) {
					try {
						tim_vintage = formatter_dash.parse(tim_vintage1);
					} catch (ParseException e) {
					}
				} else {
					tim_vintage = null;
				}

				String tis_fitted = request.getParameter("TIS Fitted (Y/ N)_" + i);
				if (tis_fitted == "" || tis_fitted == null || tis_fitted == "undefined"
						|| tis_fitted.trim().equals("undefined")) {
					tis_fitted = null;
				}

				String tis_instaln_dt1 = request.getParameter("TIS Instln dt (dd-mm-yyyy)_" + i);
				Date tis_instaln_dt = null;
				if (tis_instaln_dt1 != null && !tis_instaln_dt1.trim().isEmpty() && tis_instaln_dt1 != "undefined"
						&& !tis_instaln_dt1.trim().equals("undefined")) {
					tis_instaln_dt = formatter_dash.parse(tis_instaln_dt1);
				} else {
					tis_instaln_dt = null;
				}

				String tis_vintage1 = request.getParameter("TIS Vintage_" + i);
				Date tis_vintage = null;
				if (tis_vintage1 != null && !tis_vintage1.trim().isEmpty() && tis_vintage1 != "undefined"
						&& !tis_vintage1.trim().equals("undefined")) {
					tis_vintage = formatter_dash.parse(tis_vintage1);
				} else {
					tis_vintage = null;
				}

				String u_tim_fitted = request.getParameter("U-TIM Fitted (Y/ N)_" + i);
				if (u_tim_fitted == "" || u_tim_fitted == null || u_tim_fitted == "undefined"
						|| u_tim_fitted.trim().equals("undefined")) {
					u_tim_fitted = null;
				}

				String u_tim_intsln_dt1 = request.getParameter("U-TIM Insln Dt (dd-mm-yyyy)_" + i);
				Date u_tim_intsln_dt = null;
				if (u_tim_intsln_dt1 != null && !u_tim_intsln_dt1.trim().isEmpty() && u_tim_intsln_dt1 != "undefined"
						&& !u_tim_intsln_dt1.trim().equals("undefined")) {
					u_tim_intsln_dt = formatter_dash.parse(u_tim_intsln_dt1);
				} else {
					u_tim_intsln_dt = null;
				}

				String u_tim_vintage1 = request.getParameter("U-TIM Vintage_" + i);
				Date u_tim_vintage = null;
				if (u_tim_vintage1 != null && !u_tim_vintage1.trim().isEmpty() && u_tim_vintage1 != "undefined"
						&& !u_tim_vintage1.trim().equals("undefined")) {
					u_tim_vintage = formatter_dash.parse(u_tim_vintage1);
				} else {
					u_tim_vintage = null;
				}

				String m_tim_fitted = request.getParameter("M-TIM Fitted (Y/ N)_" + i);
				if (m_tim_fitted == "" || m_tim_fitted == null || m_tim_fitted == "undefined"
						|| m_tim_fitted.trim().equals("undefined")) {
					m_tim_fitted = null;
				}

				String m_tim_instln_dt1 = request.getParameter("M-TIM Insln Dt (dd-mm-yyyy)_" + i);
				Date m_tim_instln_dt = null;
				if (m_tim_instln_dt1 != null && !m_tim_instln_dt1.trim().isEmpty() && m_tim_instln_dt1 != "undefined"
						&& !m_tim_instln_dt1.trim().equals("undefined")) {
					m_tim_instln_dt = formatter_dash.parse(m_tim_instln_dt1);
				} else {
					m_tim_instln_dt = null;
				}

				String m_tim_vintage1 = request.getParameter("M-TIM Vintage_" + i);
				Date m_tim_vintage = null;
				if (m_tim_vintage1 != null && !m_tim_vintage1.trim().isEmpty() && m_tim_vintage1 != "undefined"
						&& !m_tim_vintage1.trim().equals("undefined")) {
					m_tim_vintage = formatter_dash.parse(m_tim_vintage1);
				} else {
					m_tim_vintage = null;
				}

				String m_tisk_fitted = request.getParameter("M-TISK Fitted (Y/ N)_" + i);
				if (m_tisk_fitted == "" || m_tisk_fitted == null || m_tisk_fitted == "undefined"
						|| m_tisk_fitted.trim().equals("undefined")) {
					m_tisk_fitted = null;
				}

				String m_tisk_instals_dt1 = request.getParameter("M-TISK Insln Dt (dd-mm-yyyy)_" + i);
				Date m_tisk_instals_dt = null;
				if (m_tisk_instals_dt1 != null && !m_tisk_instals_dt1.trim().isEmpty()
						&& m_tisk_instals_dt1 != "undefined" && !m_tisk_instals_dt1.trim().equals("undefined")) {
					m_tisk_instals_dt = formatter_dash.parse(m_tisk_instals_dt1);
				} else {
					m_tisk_instals_dt = null;
				}

				String m_tisk_vintage1 = request.getParameter("M-TISK Vintage_" + i);
				Date m_tisk_vintage = null;
				if (m_tisk_vintage1 != null && !m_tisk_vintage1.trim().isEmpty() && m_tisk_vintage1 != "undefined"
						&& !m_tisk_vintage1.trim().equals("undefined")) {
					m_tisk_vintage = formatter_dash.parse(m_tisk_vintage1);
				} else {
					m_tisk_vintage = null;
				}

				String dns_fitted = request.getParameter("DNS Fitted (Y/ N)_" + i);
				if (dns_fitted == "" || dns_fitted == null || dns_fitted == "undefined"
						|| dns_fitted.trim().equals("undefined")) {
					dns_fitted = null;
				}

				String dns_instln_dt1 = request.getParameter("DNS Instln Dt (dd-mm-yyyy)_" + i);
				Date dns_instln_dt = null;
				if (dns_instln_dt1 != null && !dns_instln_dt1.trim().isEmpty() && dns_instln_dt1 != "undefined"
						&& !dns_instln_dt1.trim().equals("undefined")) {
					dns_instln_dt = formatter_dash.parse(dns_instln_dt1);
				} else {
					dns_instln_dt = null;
				}

				String dns_ser = request.getParameter("DNS Serviceability (S/ UNSV)_" + i);
				if (dns_ser == "" || dns_ser == null || dns_ser == "undefined" || dns_ser.trim().equals("undefined")) {
					dns_ser = null;
				}

				String cti_fitted = request.getParameter("CTI Fitted (Y/ N)_" + i);
				if (cti_fitted == "" || cti_fitted == null || cti_fitted == "undefined"
						|| cti_fitted.trim().equals("undefined")) {
					cti_fitted = null;
				}

				String cti_instln_dt1 = request.getParameter("CTI Instln Dt (dd-mm-yyyy)_" + i);
				Date cti_instln_dt = null;
				if (cti_instln_dt1 != null && !cti_instln_dt1.trim().isEmpty() && cti_instln_dt1 != "undefined"
						&& !cti_instln_dt1.trim().equals("undefined")) {
					cti_instln_dt = formatter_dash.parse(cti_instln_dt1);
				} else {
					cti_instln_dt = null;
				}

				String alns_fitted = request.getParameter("ALNS Fitted (Y/ N)_" + i);
				if (alns_fitted == "" || alns_fitted == null || alns_fitted == "undefined"
						|| alns_fitted.trim().equals("undefined")) {
					alns_fitted = null;
				}

				String alns_ser = request.getParameter("ALNS Serviceability (S/ UNSV)_" + i);
				if (alns_ser == "" || alns_ser == null || alns_ser == "undefined"
						|| alns_ser.trim().equals("undefined")) {
					alns_ser = null;
				}

				String gps_fitted = request.getParameter("GPS Fitted (Y/ N)_" + i);
				if (gps_fitted == "" || gps_fitted == null || gps_fitted == "undefined"
						|| gps_fitted.trim().equals("undefined")) {
					gps_fitted = null;
				}

				String gps_ser = request.getParameter("GPS Serviceability (S/ UNSV)_" + i);
				if (gps_ser == "" || gps_ser == null || gps_ser == "undefined" || gps_ser.trim().equals("undefined")) {
					gps_ser = null;
				}

				String ecu_fitted = request.getParameter("ECU Fitted (Y/ N)_" + i);
				if (ecu_fitted == "" || ecu_fitted == null || ecu_fitted == "undefined"
						|| ecu_fitted.trim().equals("undefined")) {
					ecu_fitted = null;
				}

				String ecu_ser = request.getParameter("ECU Serviceability (S/ UNSV)_" + i);
				if (ecu_ser == "" || ecu_ser == null || ecu_ser == "undefined" || ecu_ser.trim().equals("undefined")) {
					ecu_ser = null;
				}

				String spta_fitted = request.getParameter("SPTA Fitted (Y/ N)_" + i);
				if (spta_fitted == "" || spta_fitted == null || spta_fitted == "undefined"
						|| spta_fitted.trim().equals("undefined")) {
					spta_fitted = null;
				}

				String spta_ser = request.getParameter("SPTA Serviceability (S/ UNSV)_" + i);
				if (spta_ser == "" || spta_ser == null || spta_ser == "undefined"
						|| spta_ser.trim().equals("undefined")) {
					spta_ser = null;
				}

				String powerpack_ser = request.getParameter("Powerpack Serviceability (S/ UNSV)_" + i);
				if (powerpack_ser == "" || powerpack_ser == null || powerpack_ser == "undefined"
						|| powerpack_ser.trim().equals("undefined")) {
					powerpack_ser = null;
				}

				String powerpack_demand_dt1 = request.getParameter("Powerpack Demand Dt (dd-mm-yyyy)_" + i);
				Date powerpack_demand_dt = null;
				if (powerpack_demand_dt1 != null && !powerpack_demand_dt1.trim().isEmpty()
						&& powerpack_demand_dt1 != "undefined" && !powerpack_demand_dt1.trim().equals("undefined")) {
					powerpack_demand_dt = formatter_dash.parse(powerpack_demand_dt1);
				} else {
					powerpack_demand_dt = null;
				}

				String stab_ser = request.getParameter("STAB Serviceability (S/ UNSV)_" + i);
				if (stab_ser == "" || stab_ser == null || stab_ser == "undefined"
						|| stab_ser.trim().equals("undefined")) {
					stab_ser = null;
				}

				String stab_demand_dt1 = request.getParameter("STAB Demand Dt (dd-mm-yyyy)_" + i);
				Date stab_demand_dt = null;
				if (stab_demand_dt1 != null && !stab_demand_dt1.trim().isEmpty() && stab_demand_dt1 != "undefined"
						&& !stab_demand_dt1.trim().equals("undefined")) {
					stab_demand_dt = formatter_dash.parse(stab_demand_dt1);
				} else {
					stab_demand_dt = null;
				}

				String ifdss_ser = request.getParameter("IFDSS Serviceability (S/ UNSV)_" + i);
				if (ifdss_ser == "" || ifdss_ser == null || ifdss_ser == "undefined"
						|| ifdss_ser.trim().equals("undefined")) {
					ifdss_ser = null;
				}

				String ifdss_demand_dt1 = request.getParameter("IFDSS Demand Dt (dd-mm-yyyy)_" + i);
				Date ifdss_demand_dt = null;
				if (ifdss_demand_dt1 != null && !ifdss_demand_dt1.trim().isEmpty() && ifdss_demand_dt1 != "undefined"
						&& !ifdss_demand_dt1.trim().equals("undefined")) {
					ifdss_demand_dt = formatter_dash.parse(ifdss_demand_dt1);
				} else {
					ifdss_demand_dt = null;
				}

				String gms_ser = request.getParameter("GMS Serviceability (S/ UNSV)_" + i);
				if (gms_ser == "" || gms_ser == null || gms_ser == "undefined" || gms_ser.trim().equals("undefined")) {
					gms_ser = null;
				}

				Date gms_demand_dt = null;
				String gms_demand_dt1 = request.getParameter("GMS Demand Dt_" + i);
				if (gms_demand_dt1 != null && gms_demand_dt1 != "undefined"
						&& !gms_demand_dt1.trim().equals("undefined") && !gms_demand_dt1.trim().equals("")
						&& !gms_demand_dt1.trim().isEmpty()) {
					gms_demand_dt = formatter_dash.parse(gms_demand_dt1);
				} else {
					gms_demand_dt = null;
				}

				String cps_mk_ser = request.getParameter("CPS Mk I Serviceability (S/ UNSV)_" + i);
				if (cps_mk_ser == "" || cps_mk_ser == null || cps_mk_ser == "undefined"
						|| cps_mk_ser.trim().equals("undefined")) {
					cps_mk_ser = null;
				}

				String cps_mk_demand_dt1 = request.getParameter("CPS Mk I Demand Dt (dd-mm-yyyy)_" + i);
				Date cps_mk_demand_dt = null;
				if (cps_mk_demand_dt1 != null && !cps_mk_demand_dt1.trim().isEmpty() && cps_mk_demand_dt1 != "undefined"
						&& !cps_mk_demand_dt1.trim().equals("undefined")) {
					cps_mk_demand_dt = formatter_dash.parse(cps_mk_demand_dt1);
				} else {
					cps_mk_demand_dt = null;
				}

				String dvr_pnvd_ser = request.getParameter("Dvr PNVD Serviceability (S/ UNSV)_" + i);
				if (dvr_pnvd_ser == "" || dvr_pnvd_ser == null || dvr_pnvd_ser == "undefined"
						|| dvr_pnvd_ser.trim().equals("undefined")) {
					dvr_pnvd_ser = null;
				}

				String dvr_pnvd_demand_dt1 = request.getParameter("Dvr PNVD Demand Dt (dd-mm-yyyy)_" + i);
				Date dvr_pnvd_demand_dt = null;
				if (dvr_pnvd_demand_dt1 != null && !dvr_pnvd_demand_dt1.trim().isEmpty()
						&& dvr_pnvd_demand_dt1 != "undefined" && !dvr_pnvd_demand_dt1.trim().equals("undefined")) {
					dvr_pnvd_demand_dt = formatter_dash.parse(dvr_pnvd_demand_dt1);
				} else {
					dvr_pnvd_demand_dt = null;
				}

				String fcc_ser = request.getParameter("FCC Serviceability (S/ UNSV)_" + i);
				if (fcc_ser == "" || fcc_ser == null || fcc_ser == "undefined" || fcc_ser.trim().equals("undefined")) {
					fcc_ser = null;
				}

				String fcc_demand_dt1 = request.getParameter("FCC Demand Dt (dd-mm-yyyy)_" + i);
				Date fcc_demand_dt = null;
				if (fcc_demand_dt1 != null && !fcc_demand_dt1.trim().isEmpty() && fcc_demand_dt1 != "undefined"
						&& !fcc_demand_dt1.trim().equals("undefined")) {
					fcc_demand_dt = formatter_dash.parse(fcc_demand_dt1);
				} else {
					fcc_demand_dt = null;
				}

				String BogieWheelsHeldonrdStr = request.getParameter("Bogie Wheels Held (on rd)_" + i);
				int BogieWheelsHeldonrdAt = 0;

				try {
					if (BogieWheelsHeldonrdStr != null && !BogieWheelsHeldonrdStr.isEmpty()) {
						BogieWheelsHeldonrdAt = Integer.parseInt(BogieWheelsHeldonrdStr);
						if (BogieWheelsHeldonrdAt < 0) {
							model.put("msg", "Bogie Wheels Held (on rd) Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Bogie Wheels Held (on rd) Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String bogie_wheels_held_onrd = request.getParameter("Bogie Wheels Held (on rd)_" + i);

				String BogieWheelsHeldoffrdStr = request.getParameter("Bogie Wheels Held (off rd)_" + i);
				int BogieWheelsHeldoffrdAt = 0;

				try {
					if (BogieWheelsHeldoffrdStr != null && !BogieWheelsHeldoffrdStr.isEmpty()) {
						BogieWheelsHeldoffrdAt = Integer.parseInt(BogieWheelsHeldoffrdStr);
						if (BogieWheelsHeldoffrdAt < 0) {
							model.put("msg", "Bogie Wheels Held (off rd) Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Bogie Wheels Held (off rd) Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String bogie_wheels_held_offrd = request.getParameter("Bogie Wheels Held (off rd)_" + i);

				String bogie_wheels_demand_dt1 = request.getParameter("Bogie Wheels Demand Dt (dd-mm-yyyy)_" + i);
				Date bogie_wheels_demand_dt = null;
				if (bogie_wheels_demand_dt1 != null && !bogie_wheels_demand_dt1.trim().isEmpty()
						&& bogie_wheels_demand_dt1 != "undefined"
						&& !bogie_wheels_demand_dt1.trim().equals("undefined")) {
					bogie_wheels_demand_dt = formatter_dash.parse(bogie_wheels_demand_dt1);
				} else {
					bogie_wheels_demand_dt = null;
				}

				String top_roll_ser = request.getParameter("Top Roll Serviceability (S/ UNSV)_" + i);
				if (top_roll_ser == "" || top_roll_ser == null || top_roll_ser == "undefined"
						|| top_roll_ser.trim().equals("undefined")) {
					top_roll_ser = null;
				}

				String top_roll_demand_dt1 = request.getParameter("Top Roll Demand Dt (dd-mm-yyyy)_" + i);
				Date top_roll_demand_dt = null;
				if (top_roll_demand_dt1 != null && !top_roll_demand_dt1.trim().isEmpty()
						&& top_roll_demand_dt1 != "undefined" && !top_roll_demand_dt1.trim().equals("undefined")) {
					top_roll_demand_dt = formatter_dash.parse(top_roll_demand_dt1);
				} else {
					top_roll_demand_dt = null;
				}

				String TrackPadsHeldonrdStr = request.getParameter("Track Pads Held (on rd)_" + i);
				int TrackPadsHeldonrdAt = 0;

				try {
					if (TrackPadsHeldonrdStr != null && !TrackPadsHeldonrdStr.isEmpty()) {
						TrackPadsHeldonrdAt = Integer.parseInt(TrackPadsHeldonrdStr);
						if (TrackPadsHeldonrdAt < 0) {
							model.put("msg", "Track Pads Held (on rd) Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Track Pads Held (on rd) Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String track_pads_held_onrd = request.getParameter("Track Pads Held (on rd)_" + i);
				if (track_pads_held_onrd == "" || track_pads_held_onrd == null
						|| track_pads_held_onrd.equals("undefined")) {
					track_pads_held_onrd = "0";
				}

				String TrackPadsHeldoffrdStr = request.getParameter("Track Pads Held (off rd)_" + i);
				int TrackPadsHeldoffrdAt = 0;

				try {
					if (TrackPadsHeldoffrdStr != null && !TrackPadsHeldoffrdStr.isEmpty()) {
						TrackPadsHeldoffrdAt = Integer.parseInt(TrackPadsHeldoffrdStr);
						if (TrackPadsHeldoffrdAt < 0) {
							model.put("msg", "Track Pads Held (off rd) Cannot Be Negative");
							return new ModelAndView("redirect:mcr");
						}
					}
				} catch (NumberFormatException e) {
					model.put("msg", "Track Pads Held (off rd) Can Only Be Numeric");
					return new ModelAndView("redirect:mcr");
				}

				String track_pads_held_offrd = request.getParameter("Track Pads Held (off rd)_" + i);
				if (track_pads_held_offrd == "" || track_pads_held_offrd == null
						|| track_pads_held_offrd.equals("undefined")) {
					track_pads_held_offrd = "0";
				}

				String track_pads_demand_dt1 = request.getParameter("Track Pads Demand Dt (dd-mm-yyyy)_" + i);
				Date track_pads_demand_dt = null;
				if (track_pads_demand_dt1 != null && !track_pads_demand_dt1.trim().isEmpty()
						&& track_pads_demand_dt1 != "undefined" && !track_pads_demand_dt1.trim().equals("undefined")) {
					track_pads_demand_dt = formatter_dash.parse(track_pads_demand_dt1);
				} else {
					track_pads_demand_dt = null;
				}

				String cdr_cont_stn_ser = request.getParameter("Cdr Cont Stn Serviceability (S/ UNSV)_" + i);
				if (cdr_cont_stn_ser == "" || cdr_cont_stn_ser == null || cdr_cont_stn_ser == "undefined"
						|| cdr_cont_stn_ser.trim().equals("undefined")) {
					cdr_cont_stn_ser = null;
				}

				String cdr_cont_stn_demmand_dt1 = request.getParameter("Cdr Cont Stn Demand Dt (dd-mm-yyyy)_" + i);
				Date cdr_cont_stn_demmand_dt = null;
				if (cdr_cont_stn_demmand_dt1 != null && !cdr_cont_stn_demmand_dt1.trim().isEmpty()
						&& cdr_cont_stn_demmand_dt1 != "undefined"
						&& !cdr_cont_stn_demmand_dt1.trim().equals("undefined")) {
					cdr_cont_stn_demmand_dt = formatter_dash.parse(cdr_cont_stn_demmand_dt1);
				} else {
					cdr_cont_stn_demmand_dt = null;
				}

				String gcdu_ser = request.getParameter("GCDU Serviceability (S/ UNSV)_" + i);
				if (gcdu_ser == "" || gcdu_ser == null || gcdu_ser == "undefined"
						|| gcdu_ser.trim().equals("undefined")) {
					gcdu_ser = null;
				}

				String gcdu_demand_dt1 = request.getParameter("GCDU Demand Dt (dd-mm-yyyy)_" + i);
				Date gcdu_demand_dt = null;
				if (gcdu_demand_dt1 != null && !gcdu_demand_dt1.trim().isEmpty() && gcdu_demand_dt1 != "undefined"
						&& !gcdu_demand_dt1.trim().equals("undefined")) {
					gcdu_demand_dt = formatter_dash.parse(gcdu_demand_dt1);
				} else {
					gcdu_demand_dt = null;
				}
				String engine_no = request.getParameter("Engine No_" + i);
				String low_tempered_bal = request.getParameter("Low Tempered Bal_" + i);
				if (low_tempered_bal == null || low_tempered_bal.trim().isEmpty() || low_tempered_bal == "undefined"
						|| low_tempered_bal.trim().equals("undefined")) {
					low_tempered_bal = "";
				}

				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				Query qry = session1.createQuery("update TB_TMS_CENSUS_RETURN set \r\n"
						+ "ba_no=:a1,engine_type=:a2,engine_hrs_current=:a3,engine_hr_total=:a4,engine_failure_at=:a5,engine_submission_dt=:a6,engine_repair_comp_dt=:a7,\r\n"
						+ "air_compressor_no=:a8,air_compressor_type=:a9,air_compressor_total_hrs=:a10,air_compressor_failure_at=:a11,air_compressor_submission_dt=:a12,\r\n"
						+ "air_compressor_repair_comp_dt=:a13,\r\n"
						+ "sgb_no=:a14,sgb_type=:a15,sgb_total_km=:a16,sgb_failure_at=:a17,sgb_submission_dt=:a18,sgb_repair_comp_dt=:a19,\r\n"
						+ "igb_no=:a20,igb_type=:a21,igb_total_km=:a22,igb_failure_at=:a23,igb_submission_dt=:a24,igb_repair_comp_dt=:a25,\r\n"
						+ "track_kms=:a26,track_assy_ser=:a27,track_assy_failure_at=:a28,sprocket_assy_total_km=:a29,sprocket_assy_failure_at=:a30,sprocket_assy_ser=:a31,\r\n"
						+ "pump_drive_motor_hr=:a32,pump_drive_motor_ser=:a33,pump_drive_motor_failure_at=:a34,starter_genr_total_hr=:a35,starter_genr_ser=:a36,starter_genr_failure_at=:a37,\r\n"
						+ "turbo_charger_type=:a38,turbo_charger_defect_at=:a39,turbo_charger_submission_dt=:a40,turbo_charger_repair_comp_dt=:a41,engine_demand_dt=:a42,engine_rel_dt=:a43,\r\n"
						+ "fire_fight_sys_cyl_auth=:a44,fire_fight_sys_cyl_held=:a45,fire_fight_sys_cyl_empty=:a46,fire_fight_cyl_demand_dt=:a47,\r\n"
						+ "cbrn_sys_over_pressure=:a48,cbrn_filter_qty=:a49,cbrn_filter_ser=:a50,pkuza_ser=:a51,bas_a_ser=:a52,cbrn_spl_blower_ser=:a53,mine_plough_ser=:a54,\r\n"
						+ "emp_ser=:a55,mrs_ser=:a56,mrs_type=:a57,mrs_qty=:a58,dch_installed=:a59,dch_type=:a60,dch_installed_dt=:a61,dch_vintage=:a62,dch_ser=:a63,\r\n"
						+ "cti_install_dt=:a64,cti_vintage=:a65,cti_ser=:a66,main_gun_article_no=:a67,type_gun_article=:a68,main_gun_article_qtr=:a69,\r\n"
						+ "main_gun_article_ser=:a70,main_gun_article_efc=:a71,no_of_msls_fired=:a72,no_of_msls_failed=:a73,\r\n"
						+ "sec_gun_type=:a74,sec_gun_regn_no=:a75,sec_gun_ser=:a76,aa_gun_type=:a77,aa_gun_regn_no=:a78,aa_gun_ser=:a79,\r\n"
						+ "msl_launcher_regn_no=:a80,msl_launcher_ser=:a81,msl_launcher_oh_due=:a82,msl_lr_oh_done_dt=:a83,\r\n"
						+ "tisas_old_instln_dt=:a84,tisas_old_vintage=:a85,tisas_old_ser=:a86,tisas_new_instln_dt=:a87,tisas_new_vintage=:a88,tisas_new_ser=:a89,\r\n"
						+ "tifcs_type=:a90,tifcs_regn_no=:a91,tifcs_instln_dt=:a92,tifcs_vintage=:a93,relay_kr_hr=:a94,relay_kr_ser=:a95,relay_kr_failure_at=:a96,lrf_hr=:a97,lrf_ser=:a98,\r\n"
						+ "lrf_failure_at=:a99,\r\n"
						+ "gyro_direc_indi_hr=:a100,gyro_direc_indi_ser=:a101,gyro_direc_failure_at=:a102,dist_box_hr=:a103,dist_box_ser=:a104,dist_box_failure_at=:a105,\r\n"
						+ "tim_fitted=:a106,tim_intls_dt=:a107,tim_vintage=:a108,tis_fitted=:a109,tis_instaln_dt=:a110,tis_vintage=:a111,\r\n"
						+ "u_tim_fitted=:a112,u_tim_intsln_dt=:a113,u_tim_vintage=:a114,m_tim_fitted=:a115,m_tim_instln_dt=:a116,m_tim_vintage=:a117,\r\n"
						+ "m_tisk_fitted=:a118,m_tisk_instals_dt=:a119,m_tisk_vintage=:a120,\r\n"
						+ "dns_fitted=:a121,dns_instln_dt=:a122,dns_ser=:a123,cti_fitted=:a124,cti_instln_dt=:a125,alns_fitted=:a126,alns_ser=:a127,\r\n"
						+ "gps_fitted=:a128,gps_ser=:a129,ecu_fitted=:a130,ecu_ser=:a131,spta_fitted=:a132,spta_ser=:a133,powerpack_ser=:a134,powerpack_demand_dt=:a135,\r\n"
						+ "stab_ser=:a136,stab_demand_dt=:a137,ifdss_ser=:a138,ifdss_demand_dt=:a139,gms_ser=:a140,cps_mk_ser=:a142,cps_mk_demand_dt=:a143,dvr_pnvd_ser=:a144,\r\n"
						+ "dvr_pnvd_demand_dt=:a145,\r\n"
						+ "fcc_ser=:a146,fcc_demand_dt=:a147,bogie_wheels_held_onrd=:a148,bogie_wheels_held_offrd=:a149,bogie_wheels_demand_dt=:a150,top_roll_ser=:a151,top_roll_demand_dt=:a152,\r\n"
						+ "track_pads_held_onrd=:a153,track_pads_held_offrd=:a154,\r\n"
						+ "track_pads_demand_dt=:a155,cdr_cont_stn_ser=:a156,cdr_cont_stn_demmand_dt=:a157,gcdu_ser=:a158,gcdu_demand_dt=:a159,engine_no=:a160,low_tempered_bal=:a161 where ba_no=:a1");

				qry.setParameter("a1", ba_no);
				qry.setParameter("a2", engine_type);
				qry.setParameter("a3", Integer.parseInt(engine_hrs_current));
				qry.setParameter("a4", Integer.parseInt(engine_hr_total));
				qry.setParameter("a5", Integer.parseInt(engine_failure_at));
				qry.setParameter("a6", engine_sub_dt);
				qry.setParameter("a7", engine_repair_comp_dt);
				qry.setParameter("a8", air_compressor_no);
				qry.setParameter("a9", air_compressor_type);
				qry.setParameter("a10", Integer.parseInt(air_compressor_total_hrs));
				qry.setParameter("a11", Integer.parseInt(air_compressor_failure_at));
				qry.setParameter("a12", air_compressor_submission_dt);
				qry.setParameter("a13", air_compressor_repair_comp_dt);
				qry.setParameter("a14", sgb_no);
				qry.setParameter("a15", sgb_type);
				qry.setParameter("a16", Integer.parseInt(sgb_total_km));
				qry.setParameter("a17", Integer.parseInt(sgb_failure_at));
				qry.setParameter("a18", sgb_sub_dt);
				qry.setParameter("a19", sgb_repair_comp_dt);
				qry.setParameter("a20", igb_no);

				qry.setParameter("a21", igb_type);
				qry.setParameter("a22", Integer.parseInt(igb_total_km));
				qry.setParameter("a23", Integer.parseInt(igb_failure_at));
				qry.setParameter("a24", igb_submission_dt);
				qry.setParameter("a25", igb_repair_comp_dt);
				qry.setParameter("a26", Integer.parseInt(track_kms));
				qry.setParameter("a27", track_assy_ser);
				qry.setParameter("a28", Integer.parseInt(track_assy_failure_at));
				qry.setParameter("a29", Integer.parseInt(sprocket_assy_total_km));
				qry.setParameter("a30", Integer.parseInt(sprocket_assy_failure_at));
				qry.setParameter("a31", sprocket_assy_ser);
				qry.setParameter("a32", Integer.parseInt(pump_drive_motor_hr));
				qry.setParameter("a33", pump_drive_motor_ser);
				qry.setParameter("a34", Integer.parseInt(pump_drive_motor_failure_at));
				qry.setParameter("a35", Integer.parseInt(starter_genr_total_hr));
				qry.setParameter("a36", starter_genr_ser);
				qry.setParameter("a37", Integer.parseInt(starter_genr_failure_at));
				qry.setParameter("a38", turbo_charger_type);
				qry.setParameter("a39", Integer.parseInt(turbo_charger_defect_at));
				qry.setParameter("a40", turbo_charger_submission_dt);

				qry.setParameter("a41", turbo_charger_repair_comp_dt);
				qry.setParameter("a42", engine_demand_dt);
				qry.setParameter("a43", engine_rel_dt);
				qry.setParameter("a44", Integer.parseInt(fire_fight_sys_cyl_auth));
				qry.setParameter("a45", Integer.parseInt(fire_fight_sys_cyl_held));
				qry.setParameter("a46", Integer.parseInt(fire_fight_sys_cyl_empty));
				qry.setParameter("a47", fire_fight_cyl_demand_dt);
				qry.setParameter("a48", cbrn_sys_over_pressure);
				qry.setParameter("a49", Integer.parseInt(cbrn_filter_qty));
				qry.setParameter("a50", cbrn_filter_ser);
				qry.setParameter("a51", pkuza_ser);
				qry.setParameter("a52", bas_a_ser);
				qry.setParameter("a53", cbrn_spl_blower_ser);
				qry.setParameter("a54", mine_plough_ser);
				qry.setParameter("a55", emp_ser);
				qry.setParameter("a56", mrs_ser);
				qry.setParameter("a57", mrs_type);
				qry.setParameter("a58", Integer.parseInt(mrs_qty));
				qry.setParameter("a59", dch_installed);
				qry.setParameter("a60", dch_type);

				qry.setParameter("a61", dch_installed_dt);
				qry.setParameter("a62", dch_vintage);
				qry.setParameter("a63", dch_ser);
				qry.setParameter("a64", cti_install_dt);
				qry.setParameter("a65", cti_vintage);
				qry.setParameter("a66", cti_ser);
				qry.setParameter("a67", main_gun_article_no);
				qry.setParameter("a68", type_gun_article);
				qry.setParameter("a69", main_gun_article_qtr);
				qry.setParameter("a70", main_gun_article_ser);
				qry.setParameter("a71", Integer.parseInt(main_gun_article_efc));
				qry.setParameter("a72", Integer.parseInt(no_of_msls_fired));
				qry.setParameter("a73", Integer.parseInt(no_of_msls_failed));
				qry.setParameter("a74", sec_gun_type);
				qry.setParameter("a75", sec_gun_regn_no);
				qry.setParameter("a76", sec_gun_ser);
				qry.setParameter("a77", aa_gun_type);
				qry.setParameter("a78", aa_gun_regn_no);
				qry.setParameter("a79", aa_gun_ser);
				qry.setParameter("a80", msl_launcher_regn_no);

				qry.setParameter("a81", msl_launcher_ser);
				qry.setParameter("a82", msl_launcher_oh_due);
				qry.setParameter("a83", msl_lr_oh_done_dt);
				qry.setParameter("a84", tisas_old_instln_dt);
				qry.setParameter("a85", tisas_old_vintage);
				qry.setParameter("a86", tisas_old_ser);
				qry.setParameter("a87", tisas_new_instln_dt);
				qry.setParameter("a88", tisas_new_vintage);
				qry.setParameter("a89", tisas_new_ser);
				qry.setParameter("a90", tifcs_type);
				qry.setParameter("a91", tifcs_regn_no);
				qry.setParameter("a92", tifcs_instln_dt);
				qry.setParameter("a93", tifcs_vintage);
				qry.setParameter("a94", Integer.parseInt(relay_kr_hr));
				qry.setParameter("a95", relay_kr_ser);
				qry.setParameter("a96", Integer.parseInt(relay_kr_failure_at));
				qry.setParameter("a97", Integer.parseInt(lrf_hr));
				qry.setParameter("a98", lrf_ser);
				qry.setParameter("a99", Integer.parseInt(lrf_failure_at));
				qry.setParameter("a100", Integer.parseInt(gyro_direc_indi_hr));

				qry.setParameter("a101", gyro_direc_indi_ser);
				qry.setParameter("a102", Integer.parseInt(gyro_direc_failure_at));
				qry.setParameter("a103", Integer.parseInt(dist_box_hr));
				qry.setParameter("a104", dist_box_ser);
				qry.setParameter("a105", Integer.parseInt(dist_box_failure_at));
				qry.setParameter("a106", tim_fitted);
				qry.setParameter("a107", tim_intls_dt);
				qry.setParameter("a108", tim_vintage);
				qry.setParameter("a109", tis_fitted);
				qry.setParameter("a110", tis_instaln_dt);
				qry.setParameter("a111", tis_vintage);
				qry.setParameter("a112", u_tim_fitted);
				qry.setParameter("a113", u_tim_intsln_dt);
				qry.setParameter("a114", u_tim_vintage);
				qry.setParameter("a115", m_tim_fitted);
				qry.setParameter("a116", m_tim_instln_dt);
				qry.setParameter("a117", m_tim_vintage);
				qry.setParameter("a118", m_tisk_fitted);
				qry.setParameter("a119", m_tisk_instals_dt);
				qry.setParameter("a120", m_tisk_vintage);

				qry.setParameter("a121", dns_fitted);
				qry.setParameter("a122", dns_instln_dt);
				qry.setParameter("a123", dns_ser);
				qry.setParameter("a124", cti_fitted);
				qry.setParameter("a125", cti_instln_dt);
				qry.setParameter("a126", alns_fitted);
				qry.setParameter("a127", alns_ser);
				qry.setParameter("a128", gps_fitted);
				qry.setParameter("a129", gps_ser);
				qry.setParameter("a130", ecu_fitted);
				qry.setParameter("a131", ecu_ser);
				qry.setParameter("a132", spta_fitted);
				qry.setParameter("a133", spta_ser);
				qry.setParameter("a134", powerpack_ser);
				qry.setParameter("a135", powerpack_demand_dt);
				qry.setParameter("a136", stab_ser);
				qry.setParameter("a137", stab_demand_dt);
				qry.setParameter("a138", ifdss_ser);
				qry.setParameter("a139", ifdss_demand_dt);
				qry.setParameter("a140", gms_ser);

				/* qry.setParameter("a141", gms_demand_dt); */
				qry.setParameter("a142", cps_mk_ser);
				qry.setParameter("a143", cps_mk_demand_dt);
				qry.setParameter("a144", dvr_pnvd_ser);
				qry.setParameter("a145", dvr_pnvd_demand_dt);
				qry.setParameter("a146", fcc_ser);
				qry.setParameter("a147", fcc_demand_dt);
				qry.setParameter("a148", Integer.parseInt(bogie_wheels_held_onrd));
				qry.setParameter("a149", Integer.parseInt(bogie_wheels_held_offrd));
				qry.setParameter("a150", bogie_wheels_demand_dt);
				qry.setParameter("a151", top_roll_ser);
				qry.setParameter("a152", top_roll_demand_dt);
				qry.setParameter("a153", Integer.parseInt(track_pads_held_onrd));
				qry.setParameter("a154", Integer.parseInt(track_pads_held_offrd));
				qry.setParameter("a155", track_pads_demand_dt);
				qry.setParameter("a156", cdr_cont_stn_ser);
				qry.setParameter("a157", cdr_cont_stn_demmand_dt);
				qry.setParameter("a158", gcdu_ser);
				qry.setParameter("a159", gcdu_demand_dt);
				qry.setParameter("a160", engine_no);
				qry.setParameter("a161", low_tempered_bal);

				TB_TMS_FMCR_HISTORY_TABLE fmcrh = new TB_TMS_FMCR_HISTORY_TABLE();

				String id = Excel.get(i).get(0);
				String date_of_cens_retrn = Excel.get(i).get(2);
				String mct = Excel.get(i).get(3);
				String dt_of_submsn = Excel.get(i).get(4);
				String vehcl_classfctn = Excel.get(i).get(5);
				String vehcl_kms_run = Excel.get(i).get(6);
				String engine_kms_run = Excel.get(i).get(7);
				String engine_hrs_run = Excel.get(i).get(8);
				String aux_engn_clasfctn = Excel.get(i).get(9);
				String aux_engn_hrs_run = Excel.get(i).get(10);
				String main_gun_type = Excel.get(i).get(11);
				String main_gun_efc = Excel.get(i).get(12);
				String main_gun_qr = Excel.get(i).get(13);
				String main_radio_set_nomcltr = Excel.get(i).get(14);
				String main_radio_set = Excel.get(i).get(15);
				String main_radio_set_condn = Excel.get(i).get(16);
				String unit_remarks = Excel.get(i).get(17);
				String status = Excel.get(i).get(18);
				String aprv_rejec_remarks = Excel.get(i).get(19);
				String approved_by = Excel.get(i).get(20);
				String approve_date = Excel.get(i).get(21);
				String creation_by = Excel.get(i).get(22);
				String creation_date = Excel.get(i).get(23);
				String modify_by = Excel.get(i).get(24);
				String modify_date = Excel.get(i).get(25);
				String deleted_by = Excel.get(i).get(26);
				String deleted_date = Excel.get(i).get(27);
				String version_no = Excel.get(i).get(28);
				String softdelete = Excel.get(i).get(29);

				fmcrh.setFmcr_id(Integer.parseInt(id));
				fmcrh.setBa_no(ba_no);
				fmcrh.setSus_no(sus_no);
				Date dt_of_cens_retrn = format.parse(date_of_cens_retrn);
				fmcrh.setDate_of_cens_retrn(dt_of_cens_retrn);
				BigInteger bigIntMct = new BigInteger(mct);
				fmcrh.setMct(bigIntMct);
				Date dt_of_submsn1 = format.parse(dt_of_submsn);
				fmcrh.setDt_of_submsn(dt_of_submsn1);
				fmcrh.setVehcl_classfctn(vehcl_classfctn);
				fmcrh.setVehcl_kms_run(Integer.parseInt(vehcl_kms_run));
				fmcrh.setEngine_kms_run(Integer.parseInt(engine_kms_run));
				fmcrh.setEngine_hrs_run(Integer.parseInt(engine_hrs_run));
				fmcrh.setAux_engn_clasfctn(aux_engn_clasfctn);
				fmcrh.setAux_engn_hrs_run(Integer.parseInt(aux_engn_hrs_run));
				fmcrh.setMain_gun_type(main_gun_type);
				fmcrh.setMain_gun_efc(main_gun_efc);
				fmcrh.setMain_gun_qr(main_gun_qr);
				fmcrh.setMain_radio_set_nomcltr(main_radio_set_nomcltr);
				fmcrh.setMain_radio_set(main_radio_set);
				fmcrh.setMain_radio_set_condn(main_radio_set_condn);
				fmcrh.setUnit_remarks(unit_remarks);
				fmcrh.setStatus(status);
				fmcrh.setAprv_rejec_remarks(aprv_rejec_remarks);
				fmcrh.setApproved_by(approved_by);

				if (approve_date != null) {
					Date approve_dt = format.parse(approve_date);
					fmcrh.setApprove_date(approve_dt);
				} else {
					Date approve_dt = null;
					fmcrh.setApprove_date(approve_dt);
				}

				fmcrh.setCreation_by(creation_by);

				if (creation_date != null) {
					Date creation_dt = format.parse(creation_date);
					fmcrh.setCreation_date(creation_dt);
				} else {
					Date creation_dt = null;
					fmcrh.setCreation_date(creation_dt);
				}

				fmcrh.setModify_by(modify_by);

				if (modify_date != null) {
					Date modify_dt = formatter_dash.parse(modify_date);
					fmcrh.setModify_date(modify_dt);
				} else {
					Date modify_dt = null;
					fmcrh.setModify_date(modify_dt);
				}

				fmcrh.setDeleted_by(deleted_by);

				if (modify_date != null) {
					Date deleted_dt = formatter_dash.parse(deleted_date);
					fmcrh.setModify_date(deleted_dt);
				} else {
					Date deleted_dt = null;
					fmcrh.setModify_date(deleted_dt);
				}

				fmcrh.setVersion_no(Integer.parseInt(version_no));
				fmcrh.setSoftdelete(softdelete);

				fmcrh.setEngine_type(engine_type);
				fmcrh.setEngine_submission_dt(engine_sub_dt);
				fmcrh.setEngine_repair_comp_dt(engine_repair_comp_dt);
				fmcrh.setAir_compressor_no(air_compressor_no);
				fmcrh.setAir_compressor_type(air_compressor_type);
				fmcrh.setAir_compressor_submission_dt(air_compressor_submission_dt);
				fmcrh.setAir_compressor_repair_comp_dt(air_compressor_repair_comp_dt);
				fmcrh.setSgb_no(sgb_no);
				fmcrh.setSgb_type(sgb_type);
				fmcrh.setSgb_submission_dt(igb_submission_dt);
				fmcrh.setSgb_repair_comp_dt(sgb_repair_comp_dt);
				fmcrh.setIgb_no(igb_no);
				fmcrh.setIgb_type(igb_type);
				fmcrh.setIgb_submission_dt(igb_submission_dt);
				fmcrh.setTrack_assy_ser(track_assy_ser);
				fmcrh.setSprocket_assy_ser(sprocket_assy_ser);
				fmcrh.setPump_drive_motor_ser(pump_drive_motor_ser);
				fmcrh.setStarter_genr_ser(starter_genr_ser);
				fmcrh.setTurbo_charger_type(turbo_charger_type);
				fmcrh.setTurbo_charger_submission_dt(turbo_charger_submission_dt);
				fmcrh.setTurbo_charger_repair_comp_dt(turbo_charger_repair_comp_dt);
				fmcrh.setEngine_demand_dt(engine_demand_dt);
				fmcrh.setEngine_rel_dt(engine_rel_dt);
				fmcrh.setFire_fight_cyl_demand_dt(fire_fight_cyl_demand_dt);
				fmcrh.setCbrn_sys_over_pressure(cbrn_sys_over_pressure);
				fmcrh.setCbrn_filter_ser(cbrn_filter_ser);
				fmcrh.setPkuza_ser(pkuza_ser);
				fmcrh.setBas_a_ser(bas_a_ser);
				fmcrh.setCbrn_spl_blower_ser(cbrn_spl_blower_ser);
				fmcrh.setMine_plough_ser(mine_plough_ser);
				fmcrh.setEmp_ser(emp_ser);
				fmcrh.setMrs_ser(mrs_ser);
				fmcrh.setDch_installed(dch_installed);
				fmcrh.setDch_type(dch_type);
				fmcrh.setDch_installed_dt(dch_installed_dt);
				fmcrh.setDch_vintage(dch_vintage);
				fmcrh.setDch_ser(dch_ser);
				fmcrh.setCti_install_dt(cti_install_dt);
				fmcrh.setCti_vintage(cti_vintage);
				fmcrh.setCti_ser(cti_ser);
				fmcrh.setMain_gun_article_no(main_gun_article_no);
				fmcrh.setMain_gun_article_ser(main_gun_article_ser);
				fmcrh.setSec_gun_regn_no(sec_gun_regn_no);
				fmcrh.setSec_gun_ser(sec_gun_ser);
				fmcrh.setAa_gun_regn_no(aa_gun_regn_no);
				fmcrh.setAa_gun_type(aa_gun_type);
				fmcrh.setAa_gun_ser(aa_gun_ser);
				fmcrh.setMsl_launcher_regn_no(msl_launcher_regn_no);
				fmcrh.setMsl_launcher_ser(msl_launcher_ser);
				fmcrh.setMsl_launcher_oh_due(msl_launcher_oh_due);
				fmcrh.setMsl_lr_oh_done_dt(msl_lr_oh_done_dt);
				fmcrh.setTisas_old_instln_dt(tisas_old_instln_dt);
				fmcrh.setTisas_old_vintage(tisas_old_vintage);
				fmcrh.setTisas_old_ser(tisas_old_ser);

				fmcrh.setTisas_new_instln_dt(tisas_new_instln_dt);
				fmcrh.setTisas_new_vintage(tisas_new_vintage);
				fmcrh.setTisas_new_ser(tisas_new_ser);
				fmcrh.setTifcs_type(tifcs_type);
				fmcrh.setTifcs_regn_no(tifcs_regn_no);
				fmcrh.setTifcs_instln_dt(tifcs_instln_dt);
				fmcrh.setTifcs_vintage(tifcs_vintage);

				fmcrh.setRelay_kr_ser(relay_kr_ser);
				fmcrh.setLrf_ser(lrf_ser);
				fmcrh.setGyro_direc_indi_ser(gyro_direc_indi_ser);
				fmcrh.setDist_box_ser(dist_box_ser);
				fmcrh.setTim_fitted(tim_fitted);
				fmcrh.setTim_intls_dt(tim_intls_dt);
				fmcrh.setTim_vintage(tim_vintage);
				fmcrh.setTis_fitted(tis_fitted);
				fmcrh.setTis_instaln_dt(tis_instaln_dt);
				fmcrh.setTis_vintage(tis_vintage);
				fmcrh.setU_tim_fitted(u_tim_fitted);
				fmcrh.setU_tim_intsln_dt(u_tim_intsln_dt);
				fmcrh.setU_tim_vintage(u_tim_vintage);
				fmcrh.setM_tim_fitted(m_tim_fitted);
				fmcrh.setM_tim_instln_dt(m_tim_instln_dt);
				fmcrh.setM_tim_vintage(m_tim_vintage);

				fmcrh.setM_tisk_fitted(m_tisk_fitted);
				fmcrh.setM_tisk_instals_dt(m_tisk_instals_dt);
				fmcrh.setM_tisk_vintage(m_tisk_vintage);
				fmcrh.setDns_fitted(dns_fitted);
				fmcrh.setDns_instln_dt(dns_instln_dt);
				fmcrh.setDns_ser(dns_ser);
				fmcrh.setCti_fitted(cti_fitted);
				fmcrh.setCti_instln_dt(cti_instln_dt);
				fmcrh.setAlns_fitted(alns_fitted);
				fmcrh.setAlns_ser(alns_ser);
				fmcrh.setGps_fitted(gps_fitted);
				fmcrh.setGps_ser(gps_ser);
				fmcrh.setEcu_fitted(ecu_fitted);
				fmcrh.setEcu_ser(ecu_ser);
				fmcrh.setSpta_fitted(spta_fitted);
				fmcrh.setSpta_ser(spta_ser);
				fmcrh.setMrs_type(mrs_type);

				fmcrh.setType_gun_article(type_gun_article);
				fmcrh.setMain_gun_article_qtr(main_gun_article_qtr);
				fmcrh.setPowerpack_ser(powerpack_ser);
				fmcrh.setPowerpack_demand_dt(powerpack_demand_dt);
				fmcrh.setStab_ser(stab_ser);
				fmcrh.setStab_demand_dt(stab_demand_dt);
				fmcrh.setIfdss_ser(ifdss_ser);
				fmcrh.setIfdss_demand_dt(ifdss_demand_dt);
				fmcrh.setGms_ser(gms_ser);
				fmcrh.setCps_mk_demand_dt(cps_mk_demand_dt);
				fmcrh.setDvr_pnvd_ser(dvr_pnvd_ser);
				fmcrh.setDvr_pnvd_demand_dt(dvr_pnvd_demand_dt);
				fmcrh.setFcc_ser(fcc_ser);
				fmcrh.setFcc_demand_dt(fcc_demand_dt);

				fmcrh.setBogie_wheels_demand_dt(bogie_wheels_demand_dt);
				fmcrh.setTop_roll_ser(top_roll_ser);
				fmcrh.setTop_roll_demand_dt(top_roll_demand_dt);
				fmcrh.setTrack_pads_demand_dt(track_pads_demand_dt);
				fmcrh.setCdr_cont_stn_ser(cdr_cont_stn_ser);
				fmcrh.setCdr_cont_stn_demmand_dt(cdr_cont_stn_demmand_dt);
				fmcrh.setGcdu_ser(gcdu_ser);
				fmcrh.setGcdu_demand_dt(gcdu_demand_dt);
				fmcrh.setGms_demand_dt(gms_demand_dt);
				fmcrh.setLow_tempered_bal(low_tempered_bal);

				fmcrh.setEngine_hrs_current(engineHrsCurrentAt);
				fmcrh.setEngine_hr_total(engineHrsTotalAt);
				fmcrh.setEngine_failure_at(engineFailureAtAt);
				fmcrh.setAir_compressor_total_hrs(airCompressorTotalHrsAt);
				fmcrh.setAir_compressor_failure_at(airCompressorFailureAtAt);
				fmcrh.setSgb_total_km(SGBTotalKmAt);
				fmcrh.setIgb_total_km(IGBTotalKmAt);
				fmcrh.setSprocket_assy_total_km(sprocketAssyTotalKmAt);
				fmcrh.setPump_drive_motor_hr(PumpDriveMotorHrAt);
				fmcrh.setStarter_genr_total_hr(StarterGenrTotalHrsAt);
				fmcrh.setTurbo_charger_defect_at(TurboChargerDefectatAt);
				fmcrh.setFire_fight_sys_cyl_auth(FireFightingSysCylindersAuthAt);
				fmcrh.setFire_fight_sys_cyl_held(FireFightingSysCylindersHeldAt);
				fmcrh.setFire_fight_sys_cyl_empty(FireFightingSysCylindersEmptyAt);
				fmcrh.setCbrn_filter_qty(CBRNFilterQtyAt);
				fmcrh.setNo_of_msls_fired(NoofMslsFiredAt);
				fmcrh.setNo_of_msls_failed(NoofMslsFailedAt);
				fmcrh.setRelay_kr_hr(RelayKRHrAt);
				fmcrh.setLrf_hr(LRFHrAt);
				fmcrh.setGyro_direc_indi_hr(GyroDirectionIndicatorHrAt);
				fmcrh.setDist_box_hr(DistributionBoxHrAt);
				fmcrh.setDist_box_failure_at(DistributionBoxFailureatAt);
				fmcrh.setSgb_failure_at(SGBFailureatAt);
				fmcrh.setIgb_failure_at(IGBFailureatAt);
				fmcrh.setTrack_assy_failure_at(TrackAssyFailureatAt);
				fmcrh.setSprocket_assy_failure_at(SprocketAssyFailureatAt);
				fmcrh.setPump_drive_motor_failure_at(PumpDriveMotorFailureatAt);
				fmcrh.setStarter_genr_failure_at(StarterGenrFailureatAt);
				fmcrh.setRelay_kr_failure_at(RelayKRFailureatAt);
				fmcrh.setLrf_failure_at(LRFFailureatAt);
				fmcrh.setGyro_direc_failure_at(GyroDirectionFailureatAt);
				fmcrh.setMrs_qty(Integer.parseInt(mrs_qty));
				fmcrh.setMain_gun_article_efc(MainGunArticleEFCAt);
				fmcrh.setBogie_wheels_held_onrd(BogieWheelsHeldonrdAt);
				fmcrh.setBogie_wheels_held_offrd(BogieWheelsHeldoffrdAt);
				fmcrh.setTrack_pads_held_onrd(TrackPadsHeldonrdAt);
				fmcrh.setTrack_pads_held_offrd(TrackPadsHeldoffrdAt);
				fmcrh.setSave_date(timestamp);
				session1.save(fmcrh);

				qry.executeUpdate();
				tx.commit();
				session1.close();
			}

			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mcr");
		} else {
			model.put("msg", "Please Enter Valid Excel File For A Vehicle");
			return new ModelAndView("redirect:mcr");
		}
	}
}