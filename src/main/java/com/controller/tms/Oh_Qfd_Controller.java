package com.controller.tms;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.controller.ExportFile.ExcelUserListReportViewsc_st;
import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.HelpDeskController.helpController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.Notification.notificatioDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Oh_Qfd_DAO;

import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_CIN;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_VEH_CAT_LINK;
import com.models.Tbl_CodesForm;
import com.models.Tms_Banum_Req_Child;
import com.models.UserLogin;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Oh_Qfd_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Oh_Qfd_DAO mari_dao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
		@Autowired
	    private notificatioDAO   notif;
	    helpController helpcntl=new helpController();
	    NotificationController notification = new NotificationController();
	    Psg_CommonController comm = new Psg_CommonController();
	
	
	    @RequestMapping(value = "/oh_qfd_url")
		public ModelAndView oh_qfd_url(ModelMap model, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("oh_qfd_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String select = "<option value='0'>-- Select --</option>";
			model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
			model.put("msg", msg);
			if (roleAccess.equals("Line_dte")) {

				model.put("getLine_DteList", roledao.getLine_DteList(roleSusNo));
			} else {
				model.put("selectLine_dte", select);
				model.put("getLine_DteList", roledao.getLine_DteList(""));
			}
			model.put("getLine_DteList2", roledao.getLine_DteList(""));
			model.put("getTypeOfUnitList", all.getTypeOfUnitList());
			model.put("base_work_shopList", mari_dao.base_work_shopList());
			model.put("roleAccess", roleAccess);
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			model.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			model.put("selectcomd", selectComd);
			return new ModelAndView("oh_qfd_Tiles");
		}


		@RequestMapping(value = "/getSearch_oh_qfd_report", method = RequestMethod.POST)
		public ModelAndView getSearch_oh_qfd_report(ModelMap model, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "type_veh1", required = false) String type_veh,
				@RequestParam(value = "mct_main1", required = false) String mct_main,
				@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus,
				@RequestParam(value = "kms1", required = false) int kms,
				@RequestParam(value = "vintag1", required = false) int vintag,
				@RequestParam(value = "py1", required = false) int py,
				@RequestParam(value = "CheckVal1", required = false) String checkVal,
				@RequestParam(value = "CheckVal3", required = false) String cmd,
				@RequestParam(value = "type_force1", required = false) String type_force,
				@RequestParam(value = "ddlYears1", required = false) String ddlYears1,
				@RequestParam(value = "line_dte_sus_main1", required = false) String line_dte_sus_main,
				@RequestParam(value = "type_of_intervention1", required = false) String type_of_intervention1
				
				
				) {
			if (type_veh.equals("")) {
				model.put("msg", "Please Select Veh Cat");
			}
			else if (line_dte_sus.equals("0")) {
				model.put("msg", "Please Select Nodal Dte");
			} else {
				
			
				ArrayList<ArrayList<String>> list = mari_dao.search_veh_name(type_veh,
						
						mct_main, line_dte_sus, kms, vintag, py, type_force,checkVal,cmd,line_dte_sus_main);
				model.put("list", list);
				model.put("size", list.size());
				model.put("type_veh1", type_veh);
				model.put("CheckVal1", checkVal);
				model.put("mct_main1", mct_main);
				model.put("line_dte_sus1", line_dte_sus);
				model.put("kms1", kms);
				model.put("vintag1", vintag);
				model.put("py1", py);
				model.put("type_force1", type_force);
				model.put("base_work_shopList", mari_dao.base_work_shopList());
				model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
				model.put("msg", msg);
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String select = "<option value='0'>-- Select --</option>";
				if (roleAccess.equals("Line_dte")) {
					model.put("getLine_DteList", roledao.getLine_DteList(roleSusNo));
				} else {
					model.put("selectLine_dte", select);
					model.put("getLine_DteList", roledao.getLine_DteList(""));
				}
				model.put("getTypeOfUnitList", all.getTypeOfUnitList());
			}
			List<Tbl_CodesForm> comd=all.getCommandDetailsList();
			model.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			model.put("selectcomd", selectComd);
			model.put("CheckVal1", checkVal);
			List<String> selectedGetCommandList = new ArrayList<>();
			if (cmd != "") {
				selectedGetCommandList = Arrays.asList(cmd.split(","));
			}

			model.put("getLine_DteList2", roledao.getLine_DteList(""));
			model.put("selectedGetCommandList", selectedGetCommandList);

			return new ModelAndView("oh_qfd_Tiles");
		}





	@RequestMapping(value = "/Line_directory_report_url")
	public ModelAndView Line_directory_report_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		// String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("banum_dirctry", roleid); if (val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 * if(request.getHeader("Referer") == null ) { msg = ""; }
		 */
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		return new ModelAndView("Line_directory_report_Tiles", "veh_cat_linkCmd", new TB_TMS_VEH_CAT_LINK());
	}

	@RequestMapping(value = "/type_veh_link_Action", method = RequestMethod.POST)
	public ModelAndView type_veh_link_Action(@ModelAttribute("veh_cat_linkCmd") TB_TMS_VEH_CAT_LINK rr,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, String msg)
			throws ParseException {

		int id = rr.getId() > 0 ? rr.getId() : 0;

		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		String sus_no = request.getParameter("sus_no").trim();
		String type_veh = request.getParameter("type_veh");
		String prf_code = request.getParameter("prf_code");
		String total_count = request.getParameter("reservation_count");
		// try{
		for (int i = 1; i <= Integer.parseInt(total_count); i++) {
			String mct_number = request.getParameter("mct_number" + i);

			

			if (id == 0) {

				rr.setCreation_by(username);
				rr.setCreation_date(new Date());
				rr.setSus_no(sus_no);
				rr.setPrf_code(prf_code);
				rr.setType_veh(type_veh);
				rr.setMct_code(mct_number);

				sessionHQL.save(rr);
				sessionHQL.flush();
				sessionHQL.clear();
				model.put("msg", "Data Saved Successfully.");

			}
		}

		tx.commit();

		return new ModelAndView("redirect:Line_directory_report_url");
	}

	@RequestMapping(value = "/Approval_ba_chasis_url")
	public ModelAndView Approval_ba_chasis_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("banum_dirctry", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		return new ModelAndView("Approval_ba_chasis_Tiles", "tmsbanumdirCmd", new Tms_Banum_Req_Child());
	}

	// KAJAL DATA 2
	@RequestMapping(value = "/getMctNo4DigitNomenDetailList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNo4DigitNomenDetailList(String mct, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		q = session.createQuery("select mct_nomen from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:mct")
				.setMaxResults(10);

		q.setParameter("mct", mct);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MAIN_MASTER> list = (List<TB_TMS_MCT_MAIN_MASTER>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				String mct_number = String.valueOf(list.get(i));
				encCode = c.doFinal(mct_number.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getMctNo4DigitDetailList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNo4DigitDetailList(String mct, String prf_code, HttpSession sessionUserId) {

		

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (prf_code == null || prf_code.equals("0") || prf_code == "0") {
			q = session.createQuery(
					"select distinct mct_main_id from TB_TMS_MCT_MAIN_MASTER where  cast(mct_main_id as string) like :mct ")
					.setMaxResults(10);
		} else {
			q = session.createQuery(
					"select distinct mct_main_id from TB_TMS_MCT_MAIN_MASTER where prf_code=:prf_code and cast(mct_main_id as string) like :mct   ")
					.setMaxResults(10);
			q.setParameter("prf_code", prf_code);
		}
		q.setParameter("mct", mct + "%");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MAIN_MASTER> list = (List<TB_TMS_MCT_MAIN_MASTER>) q.list();
		tx.commit();
		session.close();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				String mct_number = String.valueOf(list.get(i));
				encCode = c.doFinal(mct_number.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getMctNo4DigitDetailListforqfd", method = RequestMethod.POST)
	public @ResponseBody List<TB_TMS_MCT_MAIN_MASTER> getMctNo4DigitDetailListforqfd(String line_dte_sus,
			String type_veh, HttpSession sessionUserId) {
		if (type_veh.equals("0")) {
			type_veh = "A";
		}
		if (type_veh.equals("1")) {
			type_veh = "B";
		}
		if (type_veh.equals("2")) {
			type_veh = "C";
		}

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery(" from TB_TMS_MCT_MAIN_MASTER where sus_no=:line_dte_sus and type_of_veh=:type_veh");
		q.setParameter("line_dte_sus", line_dte_sus);
		q.setParameter("type_veh", type_veh);

		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MAIN_MASTER> list = (List<TB_TMS_MCT_MAIN_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/Excel_oh_qfd_report", method = RequestMethod.POST)
	public ModelAndView Excel_oh_qfd_report(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "type_vehex", required = false) String type_veh,
			// @RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "mct_mainex", required = false) String mct_main,
			@RequestParam(value = "line_dte_susex", required = false) String line_dte_sus,
			@RequestParam(value = "kmsex", required = false) int kms,
			@RequestParam(value = "vintagex", required = false) int vintag,
			@RequestParam(value = "pyex", required = false) int py,
			@RequestParam(value = "type_forceex", required = false) String type_force,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("oh_qfd_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> Excel = mari_dao.search_veh_name(type_veh,
				// prf_code,
				mct_main, line_dte_sus, kms, vintag, py, type_force,"","","");

		List<String> TH = new ArrayList<String>();

		TH.add("Comd");
		TH.add("Corps");
		TH.add("Division");
		TH.add("Brigade");
		TH.add("Unit Name");
		TH.add("SUS No");
		TH.add("Nomenclature");
		TH.add("BA No");
		TH.add("Kms Run");
		TH.add("Vintage");

		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Excel_To_Export_1_QFD("L", TH, username, Excel), "userList", Excel);
	}






@RequestMapping(value = "/admin/save_base_action", method = RequestMethod.POST)
public @ResponseBody String save_base_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
		throws ParseException {
	String msg = "";
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String username = session.getAttribute("username").toString();
//	String sus_no = session.getAttribute("sus_no").toString();
	String type_veh = request.getParameter("type_veh");
	String base_wp = request.getParameter("base_wp");
	String vintage = request.getParameter("vintage");
	String ba_no_t = request.getParameter("ba_no_t");
	String km_t = request.getParameter("km_t");
	String cin_id = request.getParameter("cin_id");
	String line_dte_sus = request.getParameter("line_dte_sus");
	String mode = request.getParameter("mode");
	String py = request.getParameter("py");
	String type_of_intervention = request.getParameter("type_of_intervention");
	String type_of_veh;
	TB_TMS_CIN cin = new TB_TMS_CIN();
if(mode.equals("0") ||mode.equals(""))
{
msg = "Please Select Mode";
return msg;
}
if(py.equals("0") ||py.equals(""))
{
msg = "Please Select PY";
return msg;
}


	if (type_veh.equals("0")) {
		type_of_veh = "A";
		cin.setType_of_veh(type_of_veh);
	}
	if (type_veh.equals("1")) {
		type_of_veh = "B";
		cin.setType_of_veh(type_of_veh);
	}
	if (type_veh.equals("2")) {

		type_of_veh = "C";
		cin.setType_of_veh(type_of_veh);
	}
	try {
		cin.setStatus("0");
		cin.setQr_status("0");
		cin.setOh_status("No");
		cin.setDate(new Date());
		cin.setCreated_by(username);
		cin.setBa_no(ba_no_t);
		cin.setKms(Integer.parseInt(km_t));
		cin.setVintage(Integer.parseInt(vintage));
		cin.setBase_workshop(base_wp);
		cin.setSus_no(line_dte_sus);
		cin.setMode(mode);
		cin.setType_of_intervention(type_of_intervention);
		
			cin.setPy(py);
		sessionhql.save(cin);
		msg = "Data Submitted Successfully";
		tx.commit();
	} catch (RuntimeException e) {
		try {
			tx.rollback();

			msg = "Data Not Updated";

		} catch (RuntimeException rbe) {

			msg = "Data Not Updated";

		}
	} finally {

		if (sessionhql != null) {

			sessionhql.close();
		}
	}
	return msg;
}




@RequestMapping(value = "/admin/update_cin_qr_action", method = RequestMethod.POST)
public @ResponseBody String update_cin_qr_action(ModelMap Mmap, HttpSession session, HttpServletRequest request) {
	String msg = "";
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String username = session.getAttribute("username").toString();
	String cin_no = request.getParameter("cin_id");
	try {
		
		Query	q2 = sessionhql.createQuery(" from TB_TMS_CIN  where cin_id=:cin_id ");
		q2.setParameter("cin_id", Integer.parseInt(cin_no));
		@SuppressWarnings("unchecked")
		List<TB_TMS_CIN> list2 = (List<TB_TMS_CIN>) q2.list();
		if(!list2.isEmpty())
		{

			if(list2.get(0).getBase_workshop() != null)
			{
				String hqlHQL1 = "update TB_TMS_CIN  set qr_status=:qr_status where  cin_id=:cin_id ";
				Query updatedEntities = sessionhql.createQuery(hqlHQL1);
				updatedEntities.setParameter("qr_status", "1");
				updatedEntities.setParameter("cin_id", Integer.parseInt(cin_no));
				updatedEntities.executeUpdate();
				msg = "CIN Generated";
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
				Query q = null;
				q = sessionhql.createQuery(" from TB_TMS_CIN  where cin_id=:cin_id ");
				q.setParameter("cin_id", Integer.parseInt(cin_no));
				@SuppressWarnings("unchecked")
				List<TB_TMS_CIN> list = (List<TB_TMS_CIN>) q.list();
				if(!list.isEmpty())
				{		    List<UserLogin> userlist = comm.getUseridlist(list.get(0).getSus_no());
		        String user_id = "";
		                             for (int i = 0; i < userlist.size(); i++) {
		                             	if (i == 0) {
		                                             user_id += userlist.get(i).getUserId();
		                                     }

		                                     else {
		                                             user_id += "," + userlist.get(i).getUserId();
		                                     }


		                             }
		                             if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
		                                 String title = "CIN Genertated";
		                                 String description =  " CIN Genertated";
		                                 Boolean d = notification.sendNotification(title, description, user_id, username);
		                         }
				
				}
			}
			else {
				String mode =list2.get(0).getMode().toString();
				if(mode.equals("2"))
				{
					msg = "Please Submit Base Workshop";
				}
				if(mode.equals("1"))
				{
					msg = "Please Submit Repair Agency";
				}
				
			}
			
		}
		
	
		tx.commit();
	} catch (RuntimeException e) {
		tx.rollback();
		msg = "CIN Not Generated";
	} finally {
		sessionhql.close();
	}
	return msg;
}




	@RequestMapping(value = "/admin/update_base_action", method = RequestMethod.POST)
public @ResponseBody String update_base_action(ModelMap Mmap, HttpSession session, HttpServletRequest request) {
	String msg = "";
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String username = session.getAttribute("username").toString();
	String base_wp = request.getParameter("base_wp");
	String cin_no = request.getParameter("cin_no");
	try {
		String hqlHQL1 = "update TB_TMS_CIN  set base_workshop=:base_workshop where cin_id=:cin_id";
		Query updatedEntities = sessionhql.createQuery(hqlHQL1);
		updatedEntities.setParameter("base_workshop", base_wp);
		updatedEntities.setParameter("cin_id", Integer.parseInt(cin_no));
		updatedEntities.executeUpdate();
		tx.commit();
		msg = "Data updated";
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();			
	
		//session1.save(tic_gen_noti);

	} catch (RuntimeException e) {
		tx.rollback();
		msg = "Data Not Updated";
	} finally {
		sessionhql.close();
	}
	return msg;
}







	@RequestMapping(value = "/admin/Insert_b_veh_cin", method = RequestMethod.POST)
	public @ResponseBody String Insert_b_veh_cin(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws SQLException {
		String msg = "";
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String type_veh = request.getParameter("type_veh");
		String mct_main = request.getParameter("mct_main");
		String line_dte_sus = request.getParameter("line_dte_sus");
		int kms = Integer.parseInt(request.getParameter("kms"));
		int vintag = Integer.parseInt(request.getParameter("vintag"));
		int py = Integer.parseInt(request.getParameter("py"));
		String type_force = request.getParameter("type_force");
		if (type_veh.equals("")) {
			return "please select Veh Cat";
		} else if (line_dte_sus.equals("0")) {
			return "please select Line Dte";
		} else if (!type_veh.equals("1")) {
			return "please select Veh Type B";
		} else {
			try {
				ArrayList<ArrayList<String>> list = mari_dao.search_veh_name(type_veh,mct_main, line_dte_sus, kms, vintag, py, type_force,"","","");
			
				for(int i=0;i<list.size();i++) {
					TB_TMS_CIN cin = new TB_TMS_CIN();
					cin.setType_of_veh("B");
					cin.setDate(new Date());
					cin.setStatus("0");
					cin.setOh_status("No");
					cin.setCreated_by(username);
					cin.setBa_no(list.get(i).get(7));
					cin.setKms(Integer.parseInt(list.get(i).get(8)));
					cin.setVintage(Integer.parseInt(list.get(i).get(9)));
					cin.setBase_workshop("");
					sessionhql.save(cin);
					sessionhql.flush();
					sessionhql.clear();
				}
				tx.commit();
				msg = "Data Submitted";
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					msg = "An Error Occured";
				} catch (RuntimeException rbe) {
					msg = "An Error Occured";
				}
			} finally {
				if (sessionhql != null) {
					sessionhql.close();
				}
			}
		}
		return msg;
	}
	
	
	@RequestMapping(value = "/getMctNoDigitDetailListforqfd4", method = RequestMethod.POST)
public @ResponseBody List<Map<String,Object>> getMctNoDigitDetailListforqfd4(String line_dte_sus,
String type_veh, HttpSession sessionUserId,HttpSession session) {
return  mari_dao.getMCtMain_from_prf_codenodal_dte4( type_veh, session,
	line_dte_sus);
}
	
	@RequestMapping(value = "/getMctNoDigitDetailListfordiscard4", method = RequestMethod.POST)
public @ResponseBody List<Map<String,Object>> getMctNoDigitDetailListfordiscard4(
String type_veh, HttpSession sessionUserId,HttpSession session) {
return  mari_dao.getMCtMain_discard( type_veh, session);
}
}