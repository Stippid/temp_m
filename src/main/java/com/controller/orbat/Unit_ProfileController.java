package com.controller.orbat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.AllMethodsDAO;
import com.dao.orbat.Orbat_MasterDAO;
import com.dao.orbat.Orbat_MasterDAOImpl;
import com.dao.orbat.UnitProfileDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_MISO_ORBAT_COMD_CONT;
import com.models.Tb_Miso_Orbat_Cii_Unt_Dtl;
import com.models.Tb_Miso_Orbat_Code;
import com.models.Tb_Miso_Orbat_Mast_Fmn;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Unit_ProfileController {

	Orbat_MasterDAO masterDAO = new Orbat_MasterDAOImpl();

	@Autowired
	private DataSource dataSource;

	@Autowired
	UnitProfileDAO unitProfileDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();

	@Autowired
	AllMethodsDAO allMethodsDAO;

	@RequestMapping(value = "/admin/create_unit_profile", method = RequestMethod.GET)
	public ModelAndView CreateUnitProfile(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("create_unit_profile", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("msg", msg);
		return new ModelAndView("create_unit_profileTiles", "createUnitProfileCMD", new Miso_Orbat_Unt_Dtl());
	}

	@RequestMapping(value = "/createUnitProfileAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("createUnitProfileCMD") Miso_Orbat_Unt_Dtl ud,
			@RequestParam(value = "upload_goi_letter", required = false) MultipartFile upload_goi_letter,
			@RequestParam(value = "upload_auth_letter", required = false) MultipartFile upload_auth_letter,
			HttpServletRequest request, ModelMap model, HttpSession sessionA) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("create_unit_profile", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String sus_No = request.getParameter("sus_no");
		if (all.getSusNoActiveList(sessionA, sus_No).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:create_unit_profile");
		}

		String username = sessionA.getAttribute("username").toString();
		String letter_no = request.getParameter("letter_no");

		if (letter_no.equals("")) {
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:create_unit_profile");
		}
		if (validation.checkLetter_noLength(letter_no) == false) {
			model.put("msg", validation.Letter_noMSG);
			return new ModelAndView("redirect:create_unit_profile");
		}

		String sanction_date1 = request.getParameter("sanction_date");
		Date sanction_date = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (sanction_date1.equals("")) {
				model.put("msg", "Please Enter Letter Date.");
				return new ModelAndView("redirect:create_unit_profile");
			} else {
				sanction_date = formatter1.parse(request.getParameter("sanction_date"));
			}
		} catch (ParseException e) {

		}

		String unit_name = request.getParameter("unit_name");
		if (unit_name.equals("")) {
			model.put("msg", "Please Enter Unit Name");
			return new ModelAndView("redirect:create_unit_profile");
		}
		if (validation.checkUnit_nameLength(unit_name) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:create_unit_profile");
		}

		if (sus_No.equals("")) {
			model.put("msg", "Please Enter Unit Name/SUS No.");
			return new ModelAndView("redirect:create_unit_profile");
		}
		if (validation.sus_noLength(sus_No) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:create_unit_profile");
		}

		Session sessionDetails = HibernateUtil.getSessionFactory().openSession();
		Transaction txUnt = sessionDetails.beginTransaction();
		Query q = sessionDetails.createQuery("from Miso_Orbat_Unt_Dtl where sus_No=:sus_No and status_sus_no='Active'");
		q.setParameter("sus_No", sus_No);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		txUnt.commit();
		sessionDetails.close();

		// *****************************ALL DATA BY
		// SUS_NO***************************************************************
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();

			ud.setForm_code_operation(list.get(0).getForm_code_operation());
			ud.setUnit_name(list.get(0).getUnit_name());
			ud.setCode(list.get(0).getCode());
			ud.setType_of_location(list.get(0).getType_of_location());
			ud.setUnit_army_hq(list.get(0).getUnit_army_hq());
			ud.setNrs_code(list.get(0).getNrs_code());
			ud.setComm_depart_date(list.get(0).getComm_depart_date());
			ud.setForm_code_admin(list.get(0).getForm_code_admin());
			ud.setForm_code_control(list.get(0).getForm_code_control());
			ud.setRemarks(list.get(0).getRemarks());
			ud.setCode_type(list.get(0).getCode_type());
			ud.setModification(list.get(0).getModification());
			ud.setLetter_id(list.get(0).getLetter_id());
			ud.setIs_unit_pending(list.get(0).getIs_unit_pending());
			ud.setCondition(list.get(0).getCondition());
			ud.setCompltn_arrv_date(list.get(0).getCompltn_arrv_date());
			ud.setLevel_c(list.get(0).getLevel_c());
			ud.setLevel_d(list.get(0).getLevel_d());

			String arm_name = request.getParameter("arm_name");
			String type_force = request.getParameter("type_force");
			String ct_part_i_ii = request.getParameter("ct_part_i_ii");
			String address = request.getParameter("address");

			if (arm_name.equals("")) {
				model.put("msg", "Please Enter Arm Name.");
				return new ModelAndView("redirect:create_unit_profile");
			}
			if (type_force.equals("")) {
				model.put("msg", "Please Enter Type Force.");
				return new ModelAndView("redirect:create_unit_profile");
			}
			if (ct_part_i_ii.equals("")) {
				model.put("msg", "Please Enter CT Part_i_ii.");
				return new ModelAndView("redirect:create_unit_profile");
			}

			if (validation.checkAddressLength(address) == false) {
				model.put("msg", validation.addressMSG);
				return new ModelAndView("redirect:create_unit_profile");
			}

			ud.setSus_no(sus_No);
			ud.setCreation_by(username);
			ud.setCreation_on(new Date());
			ud.setArm_code(arm_name);
			ud.setStatus_sus_no("Pending");
			ud.setCode_type("Location");
			ud.setEntity("UNIT");
			ud.setIs_unit_pending("Y");
			ud.setSus_no_for_comb_disint(ud.getSus_no());
			ud.setType_force(type_force);
			ud.setCt_part_i_ii(ct_part_i_ii);
			ud.setArm_code(arm_name);
			ud.setAddress(address);
			ud.setSus_version(allMethodsDAO.getSusVersion(sus_No));

			int uid = (Integer) sessionHQL.save(ud);
			sessionHQL.flush();
			sessionHQL.clear();

			Session session12 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx12 = session12.beginTransaction();
			Query q2 = session12.createQuery("from Miso_Orbat_Shdul_Detl where letter_id=:letter_id");
			q2.setParameter("letter_id", list.get(0).getId());
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Shdul_Detl> listShdule = (List<Miso_Orbat_Shdul_Detl>) q2.list();
			tx12.commit();
			session12.close();

			Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
			shdul.setLevel_a(listShdule.get(0).getLevel_a());
			shdul.setLevel_b(listShdule.get(0).getLevel_b());
			shdul.setLetter_no(letter_no);
			shdul.setSanction_date(sanction_date);
			shdul.setLetter_id(uid);
			shdul.setCreated_by(username);
			shdul.setCreated_on(new Date());
			shdul.setStatus("0");
			shdul.setType_of_letter("7");

			sessionHQL.save(shdul);
			sessionHQL.flush();
			sessionHQL.clear();

			tx.commit();
			model.put("msg", "Data Saved Successfully");
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:create_unit_profile");
	}

	private String status12 = "";
	private String unit_name12 = "";
	private String sus_no12 = "";
	private String parent_arm12 = "";
	private String fmn12 = "";
	private String arm_name12 = "";
	private String loc12 = "";
	private String code12 = "";
	private String type_force12 = "";
	private String ct_part_i_ii12 = "";
	private String comm_depart_date12 = "";
	private String compltn_arrv_date12 = "";

	private String fmn_name2 = "";

	@RequestMapping(value = "/admin/view_unit_profile", method = RequestMethod.GET)
	public ModelAndView ViewUnitProfile(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("view_unit_profile", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Create_Unit_Mov_DetailsController c = new Create_Unit_Mov_DetailsController();
		Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getImdtFmnList", c.getImdtFmnList());
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());

		if (msg != null && msg.equals("ok")) {
			Mmap.put("status1", status12);
			Mmap.put("unit_name1", unit_name12);
			Mmap.put("sus_no1", sus_no12);
			Mmap.put("parent_arm1", parent_arm12);
			Mmap.put("fmn1", fmn12);
			Mmap.put("arm_name1", arm_name12);
			Mmap.put("loc1", loc12);
			Mmap.put("code1", code12);
			Mmap.put("type_force1", type_force12);
			Mmap.put("ct_part_i_ii1", ct_part_i_ii12);
			Mmap.put("comm_depart_date1", comm_depart_date12);
			Mmap.put("compltn_arrv_date1", compltn_arrv_date12);
		} else {
			status12 = "";
			unit_name12 = "";
			sus_no12 = "";
			parent_arm12 = "";
			fmn12 = "";
			arm_name12 = "";
			loc12 = "";
			code12 = "";
			type_force12 = "";
			ct_part_i_ii12 = "";
			comm_depart_date12 = "";
			compltn_arrv_date12 = "";
			Mmap.put("msg", msg);
		}
		return new ModelAndView("view_unit_profileTiles");
	}

	@RequestMapping(value = "/admin/search_unit_profileSetSession")
	public ModelAndView search_unit_profileSetSession(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "status1", required = false) String status1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "parent_arm1", required = false) String parent_arm1,
			@RequestParam(value = "fmn1", required = false) String fmn1,
			@RequestParam(value = "arm_name1", required = false) String arm_name1,
			@RequestParam(value = "loc1", required = false) String loc1,
			@RequestParam(value = "code1", required = false) String code1,
			@RequestParam(value = "type_force1", required = false) String type_force1,
			@RequestParam(value = "ct_part_i_ii1", required = false) String ct_part_i_ii1,
			@RequestParam(value = "comm_depart_date1", required = false) String comm_depart_date1,
			@RequestParam(value = "compltn_arrv_date1", required = false) String compltn_arrv_date1,
			HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no1 = roleSusNo;
		}

		/*
		 * if(validation.sus_noLength(sus_no1) == false){
		 * model.put("msg",validation.sus_noMSG); return new
		 * ModelAndView("redirect:view_unit_profile"); }
		 */
		if (validation.checkUnit_nameLength(unit_name1) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:view_unit_profile");
		}
		unit_name1 = unit_name1.replace("&#40;", "(");
		unit_name1 = unit_name1.replace("&#41;", ")");

		parent_arm1 = parent_arm1.replace("&#40;", "(");
		parent_arm1 = parent_arm1.replace("&#41;", ")");

		loc1 = loc1.replace("&#40;", "(");
		loc1 = loc1.replace("&#41;", ")");

		fmn1 = fmn1.replace("&#40;", "(");
		fmn1 = fmn1.replace("&#41;", ")");

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("view_unit_profile", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		status12 = status1;
		unit_name12 = unit_name1;
		sus_no12 = sus_no1;
		parent_arm12 = parent_arm1;
		fmn12 = fmn1;
		arm_name12 = arm_name1;
		loc12 = loc1;
		code12 = code1;
		type_force12 = type_force1;
		ct_part_i_ii12 = ct_part_i_ii1;
		comm_depart_date12 = comm_depart_date1;
		compltn_arrv_date12 = compltn_arrv_date1;
		model.put("msg", "ok");
		return new ModelAndView("redirect:view_unit_profile");
	}

	@RequestMapping(value = "/admin/getUnitProfileRpt")
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUnitProfileRpt(
			@DatatablesParams DatatablesCriterias criterias, HttpSession sessionA, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("view_unit_profile", roleid);
		if (val == false) {
			return null;
		}
		if (!status12.equals("0") && !status12.equals("")) {
			DataSet<Map<String, Object>> dataSet = unitProfileDAO.findUnitProfileReportWithDatatablesCriterias(
					criterias, status12, unit_name12, sus_no12, parent_arm12, fmn12, arm_name12, loc12, type_force12,
					ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12);
			return DatatablesResponse.build(dataSet, criterias);
		} else {
			return null;
		}
	}

	/*
	 * @RequestMapping(value = "/getFormationUrlUntDetails") public @ResponseBody
	 * List<Tbl_CodesForm> getFormationUrlUntDetails() { return
	 * unitProfileDAO.getFormation(); }
	 */

	@RequestMapping(value = "/admin/viewUnitDetailsUrl")
	public ModelAndView viewUnitDetailsUrl(@ModelAttribute("id") int id,
			@ModelAttribute("statusSusDetails") String statusSusDetails, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("view_unit_profile", roleid);
		if (val == false) {
			return null;
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Miso_Orbat_Unt_Dtl unt = unitProfileDAO.getMiso_Orbat_Unt_DtlByid(id);
		List<Miso_Orbat_Shdul_Detl> shdule = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id);
		model.put("viewShdulCMD", shdule);
		model.put("viewUnitCMD", unt);

		if (shdule.get(0).getType_of_letter().equals("0")) {
			model.put("screenName", "New Raising");
		} else if (shdule.get(0).getType_of_letter().equals("1")) {
			model.put("screenName", "Re-designation");
		} else if (shdule.get(0).getType_of_letter().equals("2")) {
			model.put("screenName", "Conversion");
		} else if (shdule.get(0).getType_of_letter().equals("3")) {
			model.put("screenName", "Reorganization");
		} else if (shdule.get(0).getType_of_letter().equals("4")) {
			model.put("screenName", "Disbandment");
		} else if (shdule.get(0).getType_of_letter().equals("5")) {
			model.put("screenName", "Reactivation");
		} else if (shdule.get(0).getType_of_letter().equals("6")) {
			model.put("screenName", "Amendment");
		} else if (shdule.get(0).getType_of_letter().equals("7")) {
			model.put("screenName", "Unit Profile");
		} else if (shdule.get(0).getType_of_letter().equals("8")) {
			model.put("screenName", "Main Body Move(MISO)");
		} else if (shdule.get(0).getType_of_letter().equals("9")) {
			model.put("screenName", "Main Body Move(Relief)");
		} else if (shdule.get(0).getType_of_letter().equals("10")) {
			model.put("screenName", "Extend Raising/Disbandment");
		} else if (shdule.get(0).getType_of_letter().equals("11")) {
			model.put("screenName", "Re-Structuring");
		}

		String forcodeOperation = unt.getForm_code_operation();// '${viewUnitCMD.form_code_operation}';
		String forcodeControl = unt.getForm_code_control(); // '${viewUnitCMD.form_code_control}';
		String forcodeAdmin = unt.getForm_code_admin();// '${viewUnitCMD.form_code_admin}';

		String Opcom = String.valueOf(forcodeOperation.charAt(0));
		String Opcorps = String.valueOf(forcodeOperation.charAt(1)) + String.valueOf(forcodeOperation.charAt(2));
		String Opdiv = String.valueOf(forcodeOperation.charAt(3)) + String.valueOf(forcodeOperation.charAt(4))
				+ String.valueOf(forcodeOperation.charAt(5));
		String Opbde = String.valueOf(forcodeOperation.charAt(6)) + String.valueOf(forcodeOperation.charAt(7))
				+ String.valueOf(forcodeOperation.charAt(8)) + String.valueOf(forcodeOperation.charAt(9));

		String Contcom = String.valueOf(forcodeControl.charAt(0));
		String Contcorps = String.valueOf(forcodeControl.charAt(1)) + String.valueOf(forcodeControl.charAt(2));
		String Contdiv = String.valueOf(forcodeControl.charAt(3)) + String.valueOf(forcodeControl.charAt(4))
				+ String.valueOf(forcodeControl.charAt(5));
		String Contbde = String.valueOf(forcodeControl.charAt(6)) + String.valueOf(forcodeControl.charAt(7))
				+ String.valueOf(forcodeControl.charAt(8)) + String.valueOf(forcodeControl.charAt(9));

		String Admincom = String.valueOf(forcodeAdmin.charAt(0));
		String Admincorps = String.valueOf(forcodeAdmin.charAt(1)) + String.valueOf(forcodeAdmin.charAt(2));
		String Admindiv = String.valueOf(forcodeAdmin.charAt(3)) + String.valueOf(forcodeAdmin.charAt(4))
				+ String.valueOf(forcodeAdmin.charAt(5));
		String Adminbde = String.valueOf(forcodeAdmin.charAt(6)) + String.valueOf(forcodeAdmin.charAt(7))
				+ String.valueOf(forcodeAdmin.charAt(8)) + String.valueOf(forcodeAdmin.charAt(9));

		model.put("op_comd", all.getCommandCorpsDivBdeList("Command", Opcom).get(0));
		model.put("cont_comd", all.getCommandCorpsDivBdeList("Command", Contcom).get(0));
		model.put("adm_comd", all.getCommandCorpsDivBdeList("Command", Admincom).get(0));

		if (!Opcorps.equals("00")) {
			model.put("op_corps", all.getCommandCorpsDivBdeList("Corps", Opcorps).get(0));
		}
		if (!Contcorps.equals("00")) {
			model.put("cont_corps", all.getCommandCorpsDivBdeList("Corps", Contcorps).get(0));
		}
		if (!Admincorps.equals("00")) {
			model.put("adm_corps", all.getCommandCorpsDivBdeList("Corps", Admincorps).get(0));
		}

		if (!Opdiv.equals("000")) {
			model.put("op_div", all.getCommandCorpsDivBdeList("Division", Opdiv).get(0));
		}
		if (!Contdiv.equals("000")) {
			model.put("cont_div", all.getCommandCorpsDivBdeList("Division", Contdiv).get(0));
		}
		if (!Admindiv.equals("000")) {
			model.put("adm_div", all.getCommandCorpsDivBdeList("Division", Admindiv).get(0));
		}
		if (!Opbde.equals("0000")) {
			model.put("op_bde", all.getCommandCorpsDivBdeList("Brigade", Opbde).get(0));
		}
		if (!Contbde.equals("0000")) {
			model.put("cont_bde", all.getCommandCorpsDivBdeList("Brigade", Contbde).get(0));
		}
		if (!Adminbde.equals("0000")) {
			model.put("adm_bde", all.getCommandCorpsDivBdeList("Brigade", Adminbde).get(0));
		}

		String sus_no = unt.getSus_no();
		String parent_arm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1));
		List<Tb_Miso_Orbat_Code> list = all.getPrantArmList(sessionA);
		for (int i = 0; i < list.size(); i++) {
			if (parent_arm.equals(list.get(i).getCode())) {
				model.put("parent_arm", list.get(i).getCode_value());
			}
		}

		String typeOfArm = String.valueOf(sus_no.charAt(0)) + String.valueOf(sus_no.charAt(1))
				+ String.valueOf(sus_no.charAt(2)) + String.valueOf(sus_no.charAt(3));
		model.put("type_of_arm", all.getTypeOfArmNameBYCode(typeOfArm));
		if (unt.getCode() != null) {
			model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(unt.getCode(), sessionA).get(0));
		}
		if (unt.getArm_code() != null) {
			if (all.getArmNameFromArmCodeList(unt.getArm_code()).size() > 0) {
				model.put("arm_name", all.getArmNameFromArmCodeList(unt.getArm_code()).get(0));
			}
		}
		// Sus No Combdistinct
		if (unt.getSus_no_for_comb_disint() != null && !unt.getSus_no_for_comb_disint().equals("")) {
			model.put("source_sus_no", unt.getSus_no_for_comb_disint());
			model.put("source_unit_name",
					all.getActiveUnitNameFromSusNo_Without_Enc(unt.getSus_no_for_comb_disint(), sessionA));
		}

		model.put("type_force", all.getTypeOfUnitFromUnitNoList(unt.getType_force()).get(0).getLabel());

		String Auth_Doc = "NO";
		String Goi_Doc = "NO";

		String EXTERNAL_FILE_PATH = "";
		if (shdule.get(0).getUpload_auth_letter() != null) {
			EXTERNAL_FILE_PATH = shdule.get(0).getUpload_auth_letter();
			File file = new File(EXTERNAL_FILE_PATH);
			if (!file.exists()) {
				Auth_Doc = "NO";
			} else {
				Auth_Doc = "YES";
			}
		}
		if (shdule.get(0).getUpload_goi_letter() != null) {
			EXTERNAL_FILE_PATH = shdule.get(0).getUpload_goi_letter();
			File file = new File(EXTERNAL_FILE_PATH);
			if (!file.exists()) {
				Goi_Doc = "NO";
			} else {
				Goi_Doc = "YES";
			}
		}
		model.put("Auth_Doc", Auth_Doc);
		model.put("Goi_Doc", Goi_Doc);

		model.put("statusSusDetails", statusSusDetails);
		model.put("msg", msg);
		return new ModelAndView("viewUnitProfileDetailsTiles");
	}

	@RequestMapping(value = "/admin/getDownloadImageOrbatModify", method = RequestMethod.POST)
	public ModelAndView getDownloadImageOrbatModify(@ModelAttribute("mid") int id,
			@ModelAttribute("letter") String letter, @ModelAttribute("scenarioM") String scenarioM, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		if (letter.equals("auth")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter() == null) {
				return new ModelAndView(
						"redirect:modifyDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter();
			}
		}
		if (letter.equals("goi")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter() == null) {
				return new ModelAndView(
						"redirect:modifyDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter();
			}
		}

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
				file = new File(fullPath);
				// return new ModelAndView("redirect:modifyDetailsUrl?msg=Sorry. The file you
				// are looking for does not exist&scenarioM="+scenarioM);
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
			return new ModelAndView("redirect:modifyDetailsUrl?msg=Download Successfully");
		} catch (FileNotFoundException e) {

		}
		return new ModelAndView("redirect:modifyDetailsUrl?msg=Download Successfully");
	}

	@RequestMapping(value = "/admin/getDownloadImageOrbat", method = RequestMethod.POST)
	public ModelAndView getDownloadImageOrbat(@ModelAttribute("vid") int id, @ModelAttribute("letter") String letter,
			@ModelAttribute("scenarioV") String scenarioV, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		if (letter.equals("auth")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter() == null) {
				return new ModelAndView(
						"redirect:viewUnitRaisingDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter();
			}
		}
		if (letter.equals("goi")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter() == null) {
				return new ModelAndView(
						"redirect:viewUnitRaisingDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter();
			}
		}

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
				file = new File(fullPath);
				// return new ModelAndView("redirect:viewUnitRaisingDetailsUrl?msg=Sorry. The
				// file you are looking for does not exist&scenarioV="+scenarioV);
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
			return new ModelAndView("redirect:viewUnitRaisingDetailsUrl?msg=Download Successfully");
		} catch (FileNotFoundException e) {

		}
		return new ModelAndView("redirect:viewUnitRaisingDetailsUrl?msg=Download Successfully");
	}

	@RequestMapping(value = "/admin/getDownloadImageOrbatViewUnit", method = RequestMethod.POST)
	public ModelAndView getDownloadImageOrbatViewUnit(@ModelAttribute("id") int id,
			@ModelAttribute("letter") String letter, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";
		if (letter.equals("auth")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter() == null) {
				return new ModelAndView(
						"redirect:viewUnitDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_auth_letter();
			}
		}
		if (letter.equals("goi")) {
			if (unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter() == null) {
				return new ModelAndView(
						"redirect:viewUnitDetailsUrl?msg=Sorry. The file you are looking for does not exist");
			} else {
				EXTERNAL_FILE_PATH = unitProfileDAO.getMiso_Orbat_Shdul_DetlByid(id).get(0).getUpload_goi_letter();
			}
		}
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
				file = new File(fullPath);
				// return new ModelAndView("redirect:viewUnitDetailsUrl?msg=Sorry. The file you
				// are looking for does not exist");
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
			return new ModelAndView("redirect:viewUnitDetailsUrl?msg=Download Successfully");
		} catch (FileNotFoundException e) {

		}
		return new ModelAndView("redirect:viewUnitDetailsUrl?msg=Download Successfully");
	}

//	@RequestMapping(value = "/update_unit_name", method = RequestMethod.GET)
//	public ModelAndView update_unit_name(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "new_unit_name", required = false) String new_unit_name,HttpServletRequest request) {
//		String  roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("update_unit_name", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if(request.getHeader("Referer") == null ) {
//			msg = "";
//		}
//		Mmap.put("msg", msg);
//		Mmap.put("new_unit_name", new_unit_name);
//		return new ModelAndView("update_unit_nameTiles");
//	}
//	
//
//	
//	
//	 @RequestMapping(value = "/updateUnitNameAction", method = RequestMethod.POST)
//		public ModelAndView updateUnitNameAction(@ModelAttribute("updateUnitName") Miso_Orbat_Unt_Dtl tb_unit,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
//			String  roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("update_unit_name", roleid);	
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			String unit_name=request.getParameter("unit_name");
//			String new_unit_name=request.getParameter("new_unit_name");
//			Miso_Orbat_Unt_Dtl unt = new Miso_Orbat_Unt_Dtl();
//			if(unit_name.equals("") || unit_name.equals("null") || unit_name.equals(null))
//			{
//				model.put("msg", "Please Enter Unit Name.");
//				return new ModelAndView("redirect:update_unit_name");
//			}
//			
//			else if(new_unit_name.equals("") || new_unit_name.equals("null") || new_unit_name.equals(null))
//			{
//				model.put("msg", "Please Select New Unit Name.");
//				return new ModelAndView("redirect:update_unit_name");
//			}
//			
//			
//			
//			else
//			{
//				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
//				Transaction tx = session1.beginTransaction();
//				Session sessionU = HibernateUtilNA.getSessionFactory().openSession();
//				Transaction txU = sessionU.beginTransaction();
//				try
//				{
//					Query q = session1.createQuery("from Miso_Orbat_Unt_Dtl where unit_name =:unit_name");
//					q.setParameter("unit_name", new_unit_name);
//				   
//					@SuppressWarnings("unchecked")
//					List<String> list = (List<String>) q.list();
//					tx.commit();
//					if(list.size() == 0)
//					{
//						String hqlUpdate = "update Miso_Orbat_Unt_Dtl c set c.unit_name=:unit_name where c.unit_name = :old_unit_name";
//						int app = sessionU.createQuery( hqlUpdate ).setString( "unit_name", new_unit_name).setString( "old_unit_name", unit_name).executeUpdate();
//						
//						
//						txU.commit();
//						if(app > 0 && app > 0) {
//							model.put("msg", "Updated Successfully.");
//						}else {
//							model.put("msg", "NOT Update Successfully.");
//						}
//					}
//					else
//					{
//						model.put("unit_name", unit_name);
//						model.put("new_unit_name", new_unit_name);
//						
//						model.put("msg", "Data Already Exist.");
//					}
//				}
//				catch(Exception e)
//				{
//					session1.getTransaction().rollback();
//				}
//				finally
//				{
//					session1.close();
//					sessionU.close();
//				}
//				return new ModelAndView("redirect:update_unit_name");
//			}
//		}

	// fmn master entry form
	@RequestMapping(value = "/admin/fmnmaster", method = RequestMethod.GET)
	public ModelAndView fmnmaster(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		/*
		 * Boolean val = roledao.ScreenRedirect("Prop_Comm_letter_new",
		 * session.getAttribute("roleid").toString()); if (val == false) { return new
		 * ModelAndView("AccessTiles"); }
		 */

		/*
		 * if (request.getHeader("Referer") == null) { msg = ""; return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleType", roleType);
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("msg", msg);

		return new ModelAndView("fmnmasterTiles");
	}

	// fmn save action
	@RequestMapping(value = "/fmnmasterAction", method = RequestMethod.POST)
	public ModelAndView fmnmasterAction1(@ModelAttribute("fmnmasterCMD") Tb_Miso_Orbat_Mast_Fmn FMN,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		System.out.println("inside  fmn action");
		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("fmnmaster", roleid); if(val == false) {
		 * return new ModelAndView("AccessTiles"); }
		 */

		String username = session.getAttribute("username").toString();
		String sus = session.getAttribute("roleSusNo").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date date = new Date();

		String level_1 = request.getParameter("unit_name1").toUpperCase().trim();
		String level_2 = request.getParameter("unit_name2").toUpperCase().trim();
		String level_3 = request.getParameter("unit_name3").toUpperCase().trim();
		String level_4 = request.getParameter("unit_name4").toUpperCase().trim();
		String level_5 = request.getParameter("unit_name5").toUpperCase().trim();
		String level_6 = request.getParameter("unit_name6").toUpperCase().trim();
		String level_7 = request.getParameter("unit_name7").toUpperCase().trim();
		String level_8 = request.getParameter("unit_name8").toUpperCase().trim();
		String level_9 = request.getParameter("unit_name9").toUpperCase().trim();
		String level_10 = request.getParameter("unit_name10").toUpperCase().trim();
		String arm_code = request.getParameter("arm_name");
		String fmn_code = request.getParameter("form_code");
		String fmn_name = null;

		if (level_1.equals("") || level_1.equals("null") || level_1.equals(null)) {
			model.put("msg", "Please Enter level 1 Name.");
			return new ModelAndView("redirect:fmnmaster");
		}

		if (arm_code.equals("") || arm_code.equals("null") || arm_code.equals(null)) {
			model.put("msg", "Please select arm Name.");
			return new ModelAndView("redirect:fmnmaster");
		}

		if (fmn_code.equals("") || fmn_code.equals("null") || fmn_code.equals(null)) {
			model.put("msg", "Please Enter Formation  Name.");
			return new ModelAndView("redirect:fmnmaster");
		}

		if (!level_10.equals("") || !level_10.equals("null") || !level_10.equals(null)) {
			fmn_name = level_10;
		}

		if (level_10.equals("") || level_10.equals("null") || level_10.equals(null)) {
			fmn_name = level_9;
		}

		if (level_9.equals("") || level_9.equals("null") || level_9.equals(null)) {
			fmn_name = level_8;
		}

		if (level_8.equals("") || level_8.equals("null") || level_8.equals(null)) {
			fmn_name = level_7;
		}

		if (level_7.equals("") || level_7.equals("null") || level_7.equals(null)) {
			fmn_name = level_6;
		}

		if (level_6.equals("") || level_6.equals("null") || level_6.equals(null)) {
			fmn_name = level_5;
		}

		if (level_5.equals("") || level_5.equals("null") || level_5.equals(null)) {
			fmn_name = level_4;
		}

		if (level_4.equals("") || level_4.equals("null") || level_4.equals(null)) {
			fmn_name = level_3;
		}

		if (level_3.equals("") || level_3.equals("null") || level_3.equals(null)) {
			fmn_name = level_2;
		}

		if (level_2.equals("") || level_2.equals("null") || level_2.equals(null)) {
			fmn_name = level_1;
		}

		fmn_name.trim();

		if (masterDAO.ifExistfmncode(fmn_code) != false) {
			model.put("msg", "Formation Code  Already exists");
			return new ModelAndView("redirect:fmnmaster");
		}

		if (masterDAO.ifExistfmnname(fmn_name) != false) {
			model.put("msg", "Formation Name  Already exists");
			return new ModelAndView("redirect:fmnmaster");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			FMN.setLevel1(level_1);
			FMN.setLevel2(level_2);
			FMN.setLevel3(level_3);
			FMN.setLevel4(level_4);
			FMN.setLevel5(level_5);
			FMN.setLevel6(level_6);
			FMN.setLevel7(level_7);
			FMN.setLevel8(level_8);
			FMN.setLevel9(level_9);
			FMN.setLevel10(level_10);
			FMN.setFmn_code(fmn_code);
			FMN.setArm_code(arm_code);
			FMN.setCreated_by(username);
			FMN.setCreated_on(date);
			FMN.setStatus_record("0");
			FMN.setFmn_name(fmn_name);
			FMN.setVersion_no("1");

			sessionHQL.save(FMN);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			model.put("msg", "Data Saved Successfully.");
		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:fmnmaster");

	}

	// fmn search screen method

	@RequestMapping(value = "/admin/search_fmn_master", method = RequestMethod.GET)
	public ModelAndView Search_fmn_master(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("searchfmnmasterTiles");
	}

	// search method for pending fmn

	@RequestMapping(value = "/admin/search_fmn1", method = RequestMethod.POST)
	public ModelAndView search_fmn1(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "code_value1", required = false) String code_value,
			@RequestParam(value = "fmn_code1", required = false) String fmn_code,
			@RequestParam(value = "fmn_name1", required = false) String fmn_name,
			@RequestParam(value = "status_record1", required = false) String status_record) {
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		/*
		 * if(validation.checkParentArmNameLength(code_value) == false){
		 * Mmap.put("msg",validation.parent_arm_NameMSG); return new
		 * ModelAndView("redirect:search_fmn_master"); }
		 */

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();
		Query q = null;
		String qry = "";

		if (qry == "") {
			q = sessionPA.createQuery("from Tb_Miso_Orbat_Mast_Fmn");

			if (status_record != "") {
				qry += " status_record=:status_record ";
				Mmap.put("status_record1", status_record);
			}
			if (fmn_code != "") {
				qry += " and upper(fmn_code) like :fmn_code ";
				Mmap.put("fmn_code1", fmn_code);
			}

			if (fmn_name != "") {
				qry += " and upper(fmn_name) like :fmn_name ";
				Mmap.put("fmn_name1", fmn_name);
			}

			q = sessionPA.createQuery("from Tb_Miso_Orbat_Mast_Fmn where " + qry + " order by fmn_code");
			q.setString("status_record", status_record);

			if (fmn_code != "") {
				q.setString("fmn_code", fmn_code.toUpperCase() + "%");
			}

			if (fmn_name != "") {
				q.setString("fmn_name", "%" + fmn_name.toUpperCase() + "%");
			}

		}
		@SuppressWarnings("unchecked")
		List<Tb_Miso_Orbat_Mast_Fmn> list = (List<Tb_Miso_Orbat_Mast_Fmn>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {
			String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this FMN?') ){Approved("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String appButton = "<i class='action_icons action_approve' " + Approved + " title='Approve Data'></i>";

			String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this FMN?') ){Reject("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String rejectButton = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this FMN ?') ){Delete1("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this FMN ?') ){Update("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String f = "";
			if (status_record.equals("0")) {
				if (roleType.equals("ALL")) {
					f += appButton;
					f += rejectButton;
					f += deleteButton;
					f += updateButton;
				}
				if (roleType.equals("APP")) {
					f += appButton;
					f += rejectButton;
				}
				if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				}
			} else if (status_record.equals("1")) {
				f += "Approved";
			} else if (status_record.equals("2")) {
				if (roleType.equals("DEO") || roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}
			}
			list.get(i).setStatus_record(f);
		}

		if (list.size() == 0) {
			Mmap.put("msg", "Data Not Available");
		} else {
			Mmap.put("list", list);
		}
		return new ModelAndView("searchfmnmasterTiles");
	}

	// METHOD TO APPROVE FMN

	@RequestMapping(value = "/admin/ApprovedFMNUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedArmUrl(@ModelAttribute("appid") int appid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", masterDAO.setApprovedFMN(appid, username));
		return new ModelAndView("searchfmnmasterTiles");
	}

	// METHOD TO REJECT DATA

	@RequestMapping(value = "/admin/rejectFMNUrl", method = RequestMethod.POST)
	public ModelAndView rejectArmUrl(@ModelAttribute("rejectid") int rejectid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", masterDAO.setRejectFMN(rejectid, username));
		return new ModelAndView("searchfmnmasterTiles");
	}

	// method to delete pending fmn request

	@RequestMapping(value = "/admin/deleteFMNUrl", method = RequestMethod.POST)
	public ModelAndView deleteArmUrl(@ModelAttribute("deleteid") int deleteid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", masterDAO.setDeleteFMN(deleteid));
		return new ModelAndView("searchfmnmasterTiles");
	}

	// method to edit

	@RequestMapping(value = "/admin/updateFMNUrl", method = RequestMethod.POST)
	public ModelAndView updatePArmUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("fmnmasterEditCMD", masterDAO.getTb_Miso_Orbat_Mast_FmnByid(updateid));
		model.put("getArmNameList", all.getArmNameList());
		return new ModelAndView("create_fmnEditTiles");
	}

	@RequestMapping(value = "/fmnmasterEditAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Tb_Miso_Orbat_Mast_Fmn(@ModelAttribute("fmnmasterEditCMD") Tb_Miso_Orbat_Mast_Fmn updateid,
			HttpServletRequest request, ModelMap model, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();

		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		String level_1 = request.getParameter("unit_name1").toUpperCase();
		String level_2 = request.getParameter("unit_name2").toUpperCase();
		String level_3 = request.getParameter("unit_name3").toUpperCase();
		String level_4 = request.getParameter("unit_name4").toUpperCase();
		String level_5 = request.getParameter("unit_name5").toUpperCase();
		String level_6 = request.getParameter("unit_name6").toUpperCase();
		String level_7 = request.getParameter("unit_name7").toUpperCase();
		String level_8 = request.getParameter("unit_name8").toUpperCase();
		String level_9 = request.getParameter("unit_name9").toUpperCase();
		String level_10 = request.getParameter("unit_name10").toUpperCase();
		String arm_code = request.getParameter("arm_name");
		String fmn_code = request.getParameter("form_code");
		String fmn_name = "";

		if (level_1.equals("") || level_1.equals("null") || level_1.equals(null)) {
			model.put("msg", "Please Enter level 1 Name.");
			return new ModelAndView("redirect:search_fmn_master");
		}

		if (arm_code.equals("") || arm_code.equals("null") || arm_code.equals(null) || arm_code.equals("0")) {
			model.put("msg", "Please select arm Name.");
			return new ModelAndView("redirect:search_fmn_master");
		}

		if (fmn_code.equals("") || fmn_code.equals("null") || fmn_code.equals(null)) {
			model.put("msg", "Please Enter Formation  Name.");
			return new ModelAndView("redirect:search_fmn_master");
		}

		if (!level_10.equals("") || !level_10.equals("null") || !level_10.equals(null)) {
			fmn_name = level_10;
		}

		if (level_10.equals("") || level_10.equals("null") || level_10.equals(null)) {
			fmn_name = level_9;
		}

		if (level_9.equals("") || level_9.equals("null") || level_9.equals(null)) {
			fmn_name = level_8;
		}

		if (level_8.equals("") || level_8.equals("null") || level_8.equals(null)) {
			fmn_name = level_7;
		}

		if (level_7.equals("") || level_7.equals("null") || level_7.equals(null)) {
			fmn_name = level_6;
		}

		if (level_6.equals("") || level_6.equals("null") || level_6.equals(null)) {
			fmn_name = level_5;
		}

		if (level_5.equals("") || level_5.equals("null") || level_5.equals(null)) {
			fmn_name = level_4;
		}

		if (level_4.equals("") || level_4.equals("null") || level_4.equals(null)) {
			fmn_name = level_3;
		}

		if (level_3.equals("") || level_3.equals("null") || level_3.equals(null)) {
			fmn_name = level_2;
		}

		if (level_2.equals("") || level_2.equals("null") || level_2.equals(null)) {
			fmn_name = level_1;
		}

		fmn_name.trim();

		System.out.println("value of level1  " + level_1);

		masterDAO.UpdateFMN(updateid, username, level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8,
				level_9, level_10, arm_code, fmn_code, fmn_name);
		model.put("msg", "Data Updated Successfully");
		return new ModelAndView("redirect:search_fmn_master");
	}

	/*------FMN ENd-----*/

	// dg ncc part 2 unit entry form

	@RequestMapping(value = "/cii_unt_dtl", method = RequestMethod.GET)
	public ModelAndView cii_unt_dtl_entry_form(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("cii_unt_dtl", roleid); if(val == false)
		 * { return new ModelAndView("AccessTiles"); }
		 */
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getPrantArmList", all.getPrantArmList(session));
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("msg", msg);
		return new ModelAndView("cii_unt_dtlTiles");
	}

	// action for cii unit dtl

	@RequestMapping(value = "/cii_unt_dtlAction", method = RequestMethod.POST)
	public ModelAndView cii_unt_dtlAction(@ModelAttribute("cii_unt_dtlCMD") Tb_Miso_Orbat_Cii_Unt_Dtl UNT,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		System.out.println("inside  fmn action");
		String roleid = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("fmnmaster", roleid); if(val == false) {
		 * return new ModelAndView("AccessTiles"); }
		 */

		String username = session.getAttribute("username").toString();
		String sus = session.getAttribute("roleSusNo").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date date = new Date();

		String sus_no = request.getParameter("sus_no").toUpperCase();
		String unit_name = request.getParameter("unit_name").toUpperCase();
		String loc_code = request.getParameter("code").toUpperCase();
		String nrs_code = request.getParameter("nrs_code").toUpperCase();
		String arm_code = request.getParameter("arm_name");
		String fmn_code = request.getParameter("form_code");
		String fmn_name = request.getParameter("fmn_name").toUpperCase();
		String authority = request.getParameter("authority");
		System.out.println("value of authority  " + authority);

		String from_date1 = request.getParameter("date_from");
		Date from_date = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {

			if (from_date1.equals("")) {
				model.put("msg", "Please select from Date");
				return new ModelAndView("redirect:cii_unt_dtl");
			} else {

				from_date = formatter1.parse(request.getParameter("date_from"));
				System.out.println("FROM DATE + " + from_date);
			}
		} catch (ParseException e) {

		}

		String to_date1 = request.getParameter("date_to");
		Date to_date = null;

		try {

			if (to_date1.equals("")) {
				model.put("msg", "Please select to Date");
				return new ModelAndView("redirect:cii_unt_dtl");
			} else {

				to_date = formatter1.parse(request.getParameter("date_to"));
			}
		} catch (ParseException e) {

		}

		if (sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null)) {
			model.put("msg", "Please Enter SUS No.");
			return new ModelAndView("redirect:cii_unt_dtl");
		}

		if (unit_name.equals("") || unit_name.equals("null") || unit_name.equals(null)) {
			model.put("msg", "Please Enter Unit Name.");
			return new ModelAndView("redirect:cii_unt_dtl");
		}

		if (loc_code.equals("") || loc_code.equals("null") || loc_code.equals(null)) {
			model.put("msg", "Please Enter LOC.");
			return new ModelAndView("redirect:cii_unt_dtl");
		}

		if (nrs_code.equals("") || nrs_code.equals("null") || nrs_code.equals(null)) {
			model.put("msg", "Please Enter NRS CODE.");
			return new ModelAndView("redirect:cii_unt_dtl");
		}

		if (arm_code.equals("") || arm_code.equals("null") || arm_code.equals(null)) {
			model.put("msg", "Please select arm Name.");
			return new ModelAndView("redirect:cii_unt_dtl");
		}

		/*
		 * if(fmn_code.equals("") || fmn_code.equals("null") || fmn_code.equals(null)) {
		 * model.put("msg", "Please Enter Formation  Name."); return new
		 * ModelAndView("redirect:cii_unt_dtl"); }
		 */

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			UNT.setSus_no(sus_no);
			UNT.setUnit_name(unit_name);
			UNT.setFmn_code(fmn_code);
			UNT.setArm_code(arm_code);
			UNT.setCreated_by(username);
			UNT.setCreated_on(date);
			UNT.setStatus_sus_no("Pending");
			UNT.setLoc_code(loc_code);
			UNT.setNrs_code(nrs_code);
			UNT.setTo_date(to_date);
			UNT.setFrom_date(from_date);
			UNT.setFmn_name(fmn_name);
			UNT.setAuthority(authority);
			UNT.setVersion_no(1);

			sessionHQL.save(UNT);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			model.put("msg", "Data Saved Successfully.");
		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:cii_unt_dtl");
	}

	// search cii unt dtl
	@RequestMapping(value = "/search_cii_unt_dtl", method = RequestMethod.GET)
	public ModelAndView search_cii_unt_dtl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_cii_unt_dtlTiles");
	}

	// get cii search unit

	@RequestMapping(value = "/admin/GetSearch_CII", method = RequestMethod.POST)
	public ModelAndView GetSearch_CII(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		/*
		 * if(validation.checkParentArmNameLength(code_value) == false){
		 * Mmap.put("msg",validation.parent_arm_NameMSG); return new
		 * ModelAndView("redirect:search_fmn_master"); }
		 */

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();
		Query q = null;
		String qry = "";

		if (qry == "") {

			if (status != "") {
				qry += " status_sus_no = :status ";
				Mmap.put("status1", status);
			}

			if (unit_name != "" && unit_name != null) {
				qry += " and upper(unit_name) like :unit_name ";
				Mmap.put("unit_name1", unit_name);
			}

			if (sus_no != "" && sus_no != null) {
				qry += " and upper(sus_no) like :sus_no ";
				Mmap.put("sus_no1", sus_no);
			}

			q = sessionPA.createQuery(" from Tb_Miso_Orbat_Cii_Unt_Dtl where " + qry + " order by id desc");
			q.setString("status", status);

			if (unit_name != "" && unit_name != null) {
				q.setString("unit_name", unit_name.toUpperCase() + "%");
			}

			if (sus_no != "" && sus_no != null) {
				q.setString("sus_no", sus_no.toUpperCase() + "%");
			}

		}
		@SuppressWarnings("unchecked")
		List<Tb_Miso_Orbat_Cii_Unt_Dtl> list = (List<Tb_Miso_Orbat_Cii_Unt_Dtl>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {
			String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this CII?') ){Approved("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String appButton = "<i class='action_icons action_approve' " + Approved + " title='Approve Data'></i>";

			String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this CII?') ){Reject("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String rejectButton = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this CII ?') ){Delete1("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this CII ?') ){Update("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String f = "";
			if (status.equals("Pending")) {
				if (roleType.equals("ALL")) {
					f += appButton;
					f += rejectButton;
					f += deleteButton;
					f += updateButton;
				}
				if (roleType.equals("APP")) {
					f += appButton;
					f += rejectButton;
				}
				if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				}
			} else if (status.equals("Active")) {
				f += "Approved";
			} else if (status.equals("Reject")) {
				if (roleType.equals("DEO") || roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}
			}
			list.get(i).setStatus_sus_no(f);
		}

		if (list.size() == 0) {
			Mmap.put("msg", "Data Not Available");
		} else {
			Mmap.put("list", list);
		}
		return new ModelAndView("search_cii_unt_dtlTiles");
	}

	/// approved
	// METHOD TO APPROVE CII

	@RequestMapping(value = "/admin/ApprovedCIIUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedCIIUrl(@ModelAttribute("appid") int appid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", unitProfileDAO.setApprovedCII(appid, username));
		return new ModelAndView("search_cii_unt_dtlTiles");
	}

	// METHOD TO REJECT DATA

	@RequestMapping(value = "/admin/rejectCIIUrl", method = RequestMethod.POST)
	public ModelAndView rejectCIIUrl(@ModelAttribute("rejectid") int rejectid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", unitProfileDAO.setRejectCII(rejectid, username));
		return new ModelAndView("search_cii_unt_dtlTiles");
	}

	// method to delete pending cii request

	@RequestMapping(value = "/admin/deleteCIIUrl", method = RequestMethod.POST)
	public ModelAndView deleteCIIUrl(@ModelAttribute("deleteid") int deleteid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", unitProfileDAO.setDeleteCII(deleteid));
		return new ModelAndView("search_cii_unt_dtlTiles");
	}

	// method to edit

	@RequestMapping(value = "/admin/updateCIIUrl", method = RequestMethod.POST)
	public ModelAndView updateCIIUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			Authentication authentication, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		Tb_Miso_Orbat_Cii_Unt_Dtl unt = unitProfileDAO.getTb_Miso_Orbat_Cii_Unt_DtlByid(updateid);

		model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(unt.getLoc_code(), sessionA).get(0));

		model.put("ciiEditCMD", unitProfileDAO.getTb_Miso_Orbat_Cii_Unt_DtlByid(updateid));
		model.put("getArmNameList", all.getArmNameList());
		return new ModelAndView("edit_cii_unt_dtlTiles");
	}

	@RequestMapping(value = "/ciiEditAction", method = RequestMethod.POST)
	public ModelAndView ciiEditAction(@ModelAttribute("ciiEditCMD") Tb_Miso_Orbat_Cii_Unt_Dtl updateid,
			HttpServletRequest request, ModelMap model, HttpSession sessionA) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();

		/*
		 * Boolean val = roledao.ScreenRedirect("search_fmn_master", roleid); if(val ==
		 * false) { return new ModelAndView("AccessTiles"); }
		 */

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		String unit_name = request.getParameter("unit_name");
		String sus_no = request.getParameter("sus_no");
		String arm_code = request.getParameter("arm_name");
		String fmn_code = request.getParameter("form_code");
		String loc_code = request.getParameter("code");
		String nrs_code = request.getParameter("nrs_code");
		String fmn_name = request.getParameter("fmn_name");
		String authority = request.getParameter("authority");

		String from_date1 = request.getParameter("date_from");
		Date from_date = null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {

			if (from_date1.equals("")) {
				model.put("msg", "Please select from Date");
				return new ModelAndView("redirect:search_cii_unt_dtl");
			} else {

				from_date = formatter1.parse(request.getParameter("date_from"));
			}
		} catch (ParseException e) {

		}

		String to_date1 = request.getParameter("date_to");
		Date to_date = null;

		try {

			if (to_date1.equals("")) {
				model.put("msg", "Please select to Date");
				return new ModelAndView("redirect:search_cii_unt_dtl");
			} else {

				to_date = formatter1.parse(request.getParameter("date_from"));
			}
		} catch (ParseException e) {

		}

		if (sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null)) {
			model.put("msg", "Please Enter SUS No.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		if (unit_name.equals("") || unit_name.equals("null") || unit_name.equals(null)) {
			model.put("msg", "Please Enter Unit Name.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		if (loc_code.equals("") || loc_code.equals("null") || loc_code.equals(null)) {
			model.put("msg", "Please Enter LOC.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		if (nrs_code.equals("") || nrs_code.equals("null") || nrs_code.equals(null)) {
			model.put("msg", "Please Enter NRS CODE.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		if (arm_code.equals("") || arm_code.equals("null") || arm_code.equals(null)) {
			model.put("msg", "Please select arm Name.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		if (fmn_code.equals("") || fmn_code.equals("null") || fmn_code.equals(null)) {
			model.put("msg", "Please Enter Formation  Name.");
			return new ModelAndView("redirect:search_cii_unt_dtl");
		}

		unitProfileDAO.UpdateCII(updateid, username, sus_no, unit_name, arm_code, fmn_code, loc_code, nrs_code,
				fmn_name, from_date, to_date, authority);
		model.put("msg", "Data Updated Successfully");
		return new ModelAndView("redirect:search_cii_unt_dtl");
	}

	// method to create fmn code
	@RequestMapping(value = "/create_fmncode", method = RequestMethod.GET)
	@ResponseBody
	public String create_fmncode(@RequestParam("arm_name") String arm_name) {
		String maxFmnCode = null;
		String newFormCode = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			System.out.println("inside create fmn code method");
			connection = dataSource.getConnection();

			String sql = "SELECT MAX(fmn_code) FROM tb_miso_orbat_mast_fmn WHERE fmn_code LIKE ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, arm_name + "%");
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				maxFmnCode = resultSet.getString(1);
			}

			int nextSerialNumber = 1;
			if (maxFmnCode != null && maxFmnCode.startsWith(arm_name)) {
				nextSerialNumber = Integer.parseInt(maxFmnCode.substring(4)) + 1;
			}

			newFormCode = arm_name + String.format("%04d", nextSerialNumber);
			System.out.println("generated form code" + newFormCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		return newFormCode;

	}

	@RequestMapping(value = "/get_fmncode", method = RequestMethod.GET)
	@ResponseBody
	public String get_fmncode(@RequestParam("fmn_name") String fmn_name) {
		String FmnCode = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = dataSource.getConnection();

			String sql = "SELECT MAX(fmn_code) FROM tb_miso_orbat_mast_fmn WHERE fmn_name ILIKE ? AND status_record = '1' ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fmn_name);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				FmnCode = resultSet.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		return FmnCode;

	}

	// view unit cii details
	@RequestMapping(value = "/admin/view_unit_profile_cii", method = RequestMethod.GET)
	public ModelAndView ViewUnitProfile_cii(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("view_unit_profile", roleid);
		/*
		 * if(val == false) { return new ModelAndView("AccessTiles"); }
		 * if(request.getHeader("Referer") == null ) { msg = ""; }
		 */

		Create_Unit_Mov_DetailsController c = new Create_Unit_Mov_DetailsController();
		// Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
		Mmap.put("getArmNameList", all.getArmNameList());
		// Mmap.put("getImdtFmnList", c.getImdtFmnList());
		// Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());

		if (msg != null && msg.equals("ok")) {
			Mmap.put("status1", status12);
			Mmap.put("unit_name1", unit_name12);
			Mmap.put("sus_no1", sus_no12);
			Mmap.put("parent_arm1", parent_arm12);
			Mmap.put("fmn1", fmn12);
			Mmap.put("arm_name1", arm_name12);
			Mmap.put("loc1", loc12);
			Mmap.put("code1", code12);
			Mmap.put("type_force1", type_force12);
			Mmap.put("ct_part_i_ii1", ct_part_i_ii12);
			Mmap.put("comm_depart_date1", comm_depart_date12);
			Mmap.put("compltn_arrv_date1", compltn_arrv_date12);
			Mmap.put("fmn_name1", fmn_name2);

		} else {
			status12 = "";
			unit_name12 = "";
			sus_no12 = "";
			parent_arm12 = "";
			fmn12 = "";
			fmn_name2 = "";
			arm_name12 = "";
			loc12 = "";
			code12 = "";
			type_force12 = "";
			ct_part_i_ii12 = "";
			comm_depart_date12 = "";
			compltn_arrv_date12 = "";
			Mmap.put("msg", msg);
		}
		return new ModelAndView("view_unit_profile_ciiTiles");
	}

	@RequestMapping(value = "/admin/search_unit_profileSetSession_cii")
	public ModelAndView search_unit_profileSetSession_cii(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "status1", required = false) String status1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1,
			@RequestParam(value = "sus_no1", required = false) String sus_no1,
			// @RequestParam(value = "parent_arm1", required = false) String parent_arm1,
			// @RequestParam(value = "fmn1", required = false) String fmn1,
			@RequestParam(value = "arm_name1", required = false) String arm_name1,
			// @RequestParam(value = "loc1", required = false) String loc1,
			// @RequestParam(value = "code1", required = false) String code1,
			@RequestParam(value = "fmn_name1", required = false) String fmn_name1,
			// @RequestParam(value = "type_force1", required = false) String type_force1,
			// @RequestParam(value = "ct_part_i_ii1", required = false) String
			// ct_part_i_ii1,
			// @RequestParam(value = "comm_depart_date1", required = false) String
			// comm_depart_date1,
			// @RequestParam(value = "compltn_arrv_date1", required = false) String
			// compltn_arrv_date1,
			HttpSession sessionA) {

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no1 = roleSusNo;
		}

		/*
		 * if(validation.sus_noLength(sus_no1) == false){
		 * model.put("msg",validation.sus_noMSG); return new
		 * ModelAndView("redirect:view_unit_profile"); }
		 */
		if (validation.checkUnit_nameLength(unit_name1) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:view_unit_profile");
		}
		unit_name1 = unit_name1.replace("&#40;", "(");
		unit_name1 = unit_name1.replace("&#41;", ")");

		/*
		 * parent_arm1 = parent_arm1.replace("&#40;","("); parent_arm1 =
		 * parent_arm1.replace("&#41;",")");
		 * 
		 * loc1 = loc1.replace("&#40;","("); loc1 = loc1.replace("&#41;",")");
		 * 
		 * fmn1 = fmn1.replace("&#40;","("); fmn1 = fmn1.replace("&#41;",")");
		 */

		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("view_unit_profile_cii", roleid); if(val
		 * == false) { return new ModelAndView("AccessTiles"); }
		 */
		status12 = status1;
		unit_name12 = unit_name1;
		sus_no12 = sus_no1;
		// parent_arm12 = parent_arm1;
		// fmn12 = fmn1;
		arm_name12 = arm_name1;
		// loc12 = loc1;
		// code12 = code1;
		// type_force12 = type_force1;
		// ct_part_i_ii12 = ct_part_i_ii1;
		// comm_depart_date12 = comm_depart_date1;
		// compltn_arrv_date12 = compltn_arrv_date1;
		fmn_name2 = fmn_name1;
		model.put("msg", "ok");
		return new ModelAndView("redirect:view_unit_profile_cii");
	}

	@RequestMapping(value = "/admin/getUnitProfileRpt_cii")
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUnitProfileRpt_cii(
			@DatatablesParams DatatablesCriterias criterias, HttpSession sessionA, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("view_unit_profile_cii", roleid); if(val
		 * == false) { return null; }
		 */
		if (!status12.equals("0") && !status12.equals("")) {
			DataSet<Map<String, Object>> dataSet = unitProfileDAO.findUnitProfileReportWithDatatablesCriteriasCii(
					criterias, status12, unit_name12, sus_no12, parent_arm12, fmn12, arm_name12, loc12, type_force12,
					ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12, fmn_name2);
			return DatatablesResponse.build(dataSet, criterias);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/admin/viewUnitDetailsUrlCii")
	public ModelAndView viewUnitDetailsUrlCii(@ModelAttribute("id") int id,
			@ModelAttribute("statusSusDetails") String statusSusDetails, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionA, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("view_unit_profile_cii", roleid); if(val
		 * == false) { return null; }
		 */
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Tb_Miso_Orbat_Cii_Unt_Dtl unt = unitProfileDAO.getTb_Miso_Orbat_Cii_Unt_DtlByid(id);
		System.err.println("Value of id" + id);
		model.put("viewUnitCMD", unt);
		String fmn_code = unt.getFmn_code();
		System.out.println("value of fmn code view " + fmn_code);
		Tb_Miso_Orbat_Mast_Fmn fmn = unitProfileDAO.getTb_Miso_Orbat_Cii_Unt_DtlByFmn_code(fmn_code);

		String level1 = fmn.getLevel1().trim();
		String level2 = fmn.getLevel2().trim();
		String level3 = fmn.getLevel3().trim();
		String level4 = fmn.getLevel4().trim();
		String level5 = fmn.getLevel5().trim();
		String level6 = fmn.getLevel6().trim();
		String level7 = fmn.getLevel7().trim();
		String level8 = fmn.getLevel8().trim();
		String level9 = fmn.getLevel9().trim();
		String level10 = fmn.getLevel10().trim();
		String fmn_name = fmn.getFmn_name().trim();

		if (level1.trim() != null || level1.trim() == "") {
			model.put("level1", level1);
		}

		if (level2.trim() != null || level2.trim() == "") {
			model.put("level2", level2);
		}

		if (level3.trim() != null || level3.trim() == "") {
			model.put("level3", level3);
		}

		if (level4.trim() != null || level4.trim() == "") {
			model.put("level4", level4);
		}

		if (level5.trim() != null || level5.trim() == "") {
			model.put("level5", level5);
		}

		if (level6.trim() != null || level6.trim() == "") {
			model.put("level6", level6);
		}

		if (level7.trim() != null || level7.trim() == "") {
			model.put("level7", level7);
		}

		if (level8.trim() != null || level8.trim() == "") {
			model.put("level8", level8);
		}

		if (level9.trim() != null || level9.trim() == "") {
			model.put("level9", level9);
		}

		if (level10.trim() != null || level10.trim() == "") {
			model.put("level10", level10);
		}

		if (fmn_name.trim() != null || fmn_name.trim() == "") {
			model.put("fmn_name", fmn_name);
		}

		if (unt.getLoc_code() != null) {
			model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(unt.getLoc_code(), sessionA).get(0));
		}
		if (unt.getArm_code() != null) {
			if (all.getArmNameFromArmCodeList(unt.getArm_code()).size() > 0) {
				model.put("arm_name", all.getArmNameFromArmCodeList(unt.getArm_code()).get(0));
			}
		}

		model.put("statusSusDetails", statusSusDetails);
		model.put("msg", msg);
		return new ModelAndView("viewUnitProfileDetailsCiiTiles");
	}

	@RequestMapping(value = "/loadLevel", method = RequestMethod.GET)
	@ResponseBody
	public List<String> loadLevel(@RequestParam("level") int level,
			@RequestParam(value = "selectedValue", required = false) String selectedValue) {
		// Directly call the DAO methods based on the level
		switch (level) {
		case 0:
			return unitProfileDAO.findAllLevel1Options();
		case 1:
			return unitProfileDAO.findLevel2OptionsByLevel1(selectedValue);
		case 2:
			return unitProfileDAO.findLevel3OptionsByLevel2(selectedValue);
		case 3:
			return unitProfileDAO.findLevel4OptionsByLevel3(selectedValue);
		case 4:
			return unitProfileDAO.findLevel5OptionsByLevel4(selectedValue); // Fetch level3 based on level2
		case 5:
			return unitProfileDAO.findLevel6OptionsByLevel5(selectedValue); // Fetch level3 based on level2
		case 6:
			return unitProfileDAO.findLevel7OptionsByLevel6(selectedValue); // Fetch level3 based on level2
		case 7:
			return unitProfileDAO.findLevel8OptionsByLevel7(selectedValue); // Fetch level3 based on level2
		case 8:
			return unitProfileDAO.findLevel9OptionsByLevel8(selectedValue); // Fetch level3 based on level2
		case 9:
			return unitProfileDAO.findLevel10OptionsByLevel9(selectedValue); // Fetch level3 based on level2

		// Continue for level 4 to level 10
		default:
			return Collections.emptyList();
		}
	}

	// new conmmand control instructions create form
	@RequestMapping(value = "/comd_and_cont_inst_url", method = RequestMethod.GET)
	public ModelAndView comd_and_cont_inst_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("comd_and_cont_inst_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getLine_DteList", roledao.getLine_DteList(""));
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getTypeOfUnitList", all.getTypeOfUnitList());
		Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("mil_co_unit_detailsTiles");
	}

	@RequestMapping(value = "/admin/comd_cont_action", method = RequestMethod.POST)
	public @ResponseBody String comd_cont_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws SQLException, ParseException {
		String roleid = session.getAttribute("roleid").toString();

		String msg = "";
		String username = session.getAttribute("username").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date date = new Date();
		String auth_letter_no = request.getParameter("letter_no");
		String auth_letter_date = request.getParameter("letter_date");
		String unit_sus_no = request.getParameter("unit_sus_no");
		String ops_sus_no = request.getParameter("ops_sus_no");
		String is_sus_no = request.getParameter("is_sus_no");
		String mil_sus_no = request.getParameter("mil_sus_no");
		String tech_cont_sus_no = request.getParameter("tech_cont_sus_no");
		String discp_sus_no = request.getParameter("discp_sus_no");
		String local_adm_sus_no = request.getParameter("local_adm_sus_no");
		String loc_code = request.getParameter("loc_code");
		String nrs_code = request.getParameter("nrs_code");
		String editId = request.getParameter("editId");

		String from_date1 = request.getParameter("from_date");

		String to_date1 = request.getParameter("to_date");

		if (unit_sus_no.equals("") || unit_sus_no.equals("null") || unit_sus_no.equals(null)) {
			msg = "Please Enter SUS No.";
			return msg;

		}

		if (ops_sus_no.equals("") || ops_sus_no.equals("null") || ops_sus_no.equals(null)) {
			msg = "Please Enter OPS SUS No..";
			return msg;
		}

		if (is_sus_no.equals("") || is_sus_no.equals("null") || is_sus_no.equals(null)) {
			msg = "Please Enter IS SUS No.";
			return msg;
		}

		if (discp_sus_no.equals("") || discp_sus_no.equals("null") || discp_sus_no.equals(null)) {
			msg = "Please Enter DISP SUS No.";
			return msg;
		}

		if (local_adm_sus_no.equals("") || local_adm_sus_no.equals("null") || local_adm_sus_no.equals(null)) {
			msg = "Please Enter LOCAL ADM SUS No.";
			return msg;
		}

		if (mil_sus_no.equals("") || mil_sus_no.equals("null") || mil_sus_no.equals(null)) {
			msg = "Please Enter MIL  SUS No.";
			return msg;
		}

		if (tech_cont_sus_no.equals("") || tech_cont_sus_no.equals("null") || tech_cont_sus_no.equals(null)) {

			msg = "Please Enter SUS No.";
			return msg;
		}

		if (loc_code.equals("") || loc_code.equals("null") || loc_code.equals(null)) {
			msg = "Please Enter LOC.";
			return msg;
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Session ses1 = HibernateUtil.getSessionFactory().openSession();
			Transaction t2 = ses1.beginTransaction();
			Query q = ses1.createQuery(
					"select distinct arm_code from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
			q.setParameter("sus_no", unit_sus_no);
			String arm_code = (String) q.uniqueResult();

			TB_MISO_ORBAT_COMD_CONT UNT;

			if (editId != null && !editId.isEmpty() && Integer.parseInt(editId) > 0) {
				UNT = (TB_MISO_ORBAT_COMD_CONT) sessionHQL.get(TB_MISO_ORBAT_COMD_CONT.class, Integer.parseInt(editId));

				if (UNT != null) {
					UNT.setUnit_sus_no(unit_sus_no);
					UNT.setAuth_letter_no(auth_letter_no);
					UNT.setAuth_letter_date(auth_letter_date);
					UNT.setLoc_code(loc_code);
					UNT.setNrs_code(nrs_code);
					UNT.setOps_sus_no(ops_sus_no);
					UNT.setIs_sus_no(is_sus_no);
					UNT.setMil_sus_no(mil_sus_no);
					UNT.setTechcont_sus_no(tech_cont_sus_no);
					UNT.setDiscp_sus_no(discp_sus_no);
					UNT.setLocal_adm_sus_no(local_adm_sus_no);
					UNT.setModified_by(username);
					UNT.setModified_date(date);
					UNT.setFrom_date(from_date1);
					UNT.setTo_date(to_date1);
					UNT.setArm_code(arm_code);
					UNT.setCmd_status(0);
					UNT.setSd9_distribute_status(-1);
					UNT.setCurrent_status("cmd Pending");
					sessionHQL.update(UNT);
					msg = "Data Updated Successfully.";
				} else {
					msg = "Record not found for update.";
				}
			} else {
				UNT = new TB_MISO_ORBAT_COMD_CONT();
				UNT.setUnit_sus_no(unit_sus_no);
				UNT.setAuth_letter_no(auth_letter_no);
				UNT.setAuth_letter_date(auth_letter_date);
				UNT.setLoc_code(loc_code);
				UNT.setNrs_code(nrs_code);
				UNT.setOps_sus_no(ops_sus_no);
				UNT.setIs_sus_no(is_sus_no);
				UNT.setMil_sus_no(mil_sus_no);
				UNT.setTechcont_sus_no(tech_cont_sus_no);
				UNT.setDiscp_sus_no(discp_sus_no);
				UNT.setLocal_adm_sus_no(loc_code);
				UNT.setCreated_by(username);
				UNT.setCreated_date(date);
				UNT.setFrom_date(from_date1);
				UNT.setTo_date(to_date1);
				UNT.setArm_code(arm_code);
				UNT.setLocal_adm_sus_no(local_adm_sus_no);
				UNT.setCmd_status(0);
				UNT.setMo_status(-1);
				UNT.setSd4_status(-1);
				UNT.setSd5_status(-1);
				UNT.setSd9_status(-1);
				UNT.setSd9_distribute_status(-1);
				UNT.setCreated_cmd_susno(roleSusNo);
				UNT.setCurrent_status("cmd Pending");
				sessionHQL.save(UNT);
				msg = "Data Saved Successfully.";
			}
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();

		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg = "Couldn?t roll back transaction" + rbe;

			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return msg;

	}

	@RequestMapping(value = "/getsdMoveDtl", method = RequestMethod.POST)
	public @ResponseBody String getsdMoveDtl(String unit_name, HttpSession sessionUserId) {
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct form_code_control from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
		q.setParameter("sus_no", "sus_no");
		String sdMove = (String) q.uniqueResult();

		return sdMove;
	}

}