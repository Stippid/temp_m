package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Download_Record_Service;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;

import com.controller.validation.ValidationController;

import com.dao.login.RoleBaseMenuDAO;

import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;

import com.dao.psg.Master.Psg_CommanDAO;

import com.dao.psg.update_census_data.Search_UpdateOffDataDao;

import com.dao.psg.update_census_data.View_update_Dao;

import com.models.psg.Transaction.TB_CENSUS_ADDRESS;

import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;

import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;

import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;

import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;

import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;

import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;

import com.models.psg.Transaction.TB_CENSUS_NOK;

import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;

import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;

import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;

import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;

import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;

import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;

import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;

import com.models.psg.update_census_data.TB_CENSUS_BPET;

import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;

import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;

import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;

import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;

import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;

import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;

import com.models.psg.update_census_data.TB_CHANGE_NAME;

import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;

import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;

import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.models.psg.update_census_data.TB_CHANGE_TNAI_NO;
import com.models.psg.update_census_data.TB_DESERTER;

import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;

import com.models.psg.update_census_data.TB_NON_EFFECTIVE;

import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;

import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class View_UpdateOfficer_controller {

	@Autowired

	View_update_Dao UOD;

	@Autowired

	Search_UpdateOffDataDao SD;

	@Autowired

	Psg_CommanDAO psg_com;

	@Autowired

	private RoleBaseMenuDAO roledao;

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	Psg_CommonController mcommon = new Psg_CommonController();

	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/View_UpdateOfficerDataUrl", method = RequestMethod.GET)

	public ModelAndView View_UpdateOfficerDataUrl(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleType = session.getAttribute("roleType").toString();

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("View_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";

			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		Mmap.put("msg", msg);

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());

		Mmap.put("getParentArmList", mcommon.getParentArmList());

//		Mmap.put("list",
//				UOD.Search_UpdateOffData("", "", "", "0", "0", roleSusNo, roleType, "", "", "", "", "1", roleAccess));

		return new ModelAndView("Search_view_update_offTiles");

	}

	@RequestMapping(value = "/admin/GetSearch_UpdateOfficerData_view", method = RequestMethod.POST)

	public ModelAndView GetSearch_UpdateOfficerData_view(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "personnel_no1", required = false) String personnel_no,

			@RequestParam(value = "status1", required = false) String status,

			@RequestParam(value = "rank1", required = false) String rank,

			@RequestParam(value = "unit_name1", required = false) String unit_name,

			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,

			@RequestParam(value = "name1", required = false) String name,

			@RequestParam(value = "year_of_comm1", required = false) String year_of_comm,

			@RequestParam(value = "year_of_dob1", required = false) String year_of_dob,

			@RequestParam(value = "p_arm1", required = false) String p_arm,

			@RequestParam(value = "comm_status1", required = false) String comm_status,
			
			@RequestParam(value = "IsMns", required = false) String IsMns) {

		unit_name = unit_name.replace("&#40;", "(");

		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();
		String redirect = "Search_view_update_offTiles";
		if (IsMns.equals("true")) {
			redirect = "Search_view_update_off_mnsTiles";
		}

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		if (unit_sus_no != "") {

			if (!valid.SusNoLength(unit_sus_no)) {

				Mmap.put("msg", valid.SusNoMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {

				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

		}

		if (personnel_no != "") {

			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {

				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {

				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

		}

		if (unit_name != "") {

			if (!valid.isOnlyAlphabetNumeric(unit_name)) {

				Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//
//					return new ModelAndView("redirect:View_UpdateOfficerDataUrl");
//
//				}

		}

		if (name != "") {

			if (!valid.isOnlyAlphabet(name)) {

				Mmap.put("msg", "Name " + valid.isOnlyAlphabetMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

			if (!valid.isvalidLength(name, valid.nameMax, valid.nameMin)) {

				Mmap.put("msg", "Name " + valid.isValidLengthMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

		}

		if (year_of_comm != "") {

			if (valid.isOnlyNumer(year_of_comm) == true) {

				Mmap.put("msg", " Year of Commission  " + valid.isOnlyNumerMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

			if (!valid.YearLength(year_of_comm)) {

				Mmap.put("msg", valid.YearMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

		}

		if (year_of_dob != "") {

			if (valid.isOnlyNumer(year_of_dob) == true) {

				Mmap.put("msg", " Year of Commission  " + valid.isOnlyNumerMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

			if (!valid.YearLength(year_of_dob)) {

				Mmap.put("msg", valid.YearMSG);

				return new ModelAndView("redirect:View_UpdateOfficerDataUrl");

			}

		}
		ArrayList<ArrayList<String>> list = UOD.Search_UpdateOffData(unit_name, unit_sus_no, personnel_no, status, rank,

				roleSusNo, roleType, name, year_of_comm, year_of_dob, p_arm, comm_status, roleAccess,Boolean.parseBoolean(IsMns));

		Mmap.put("list", list);

		Mmap.put("size", list.size());

		Mmap.put("unit_name1", unit_name);

		Mmap.put("unit_sus_no1", unit_sus_no);

		Mmap.put("personnel_no1", personnel_no);

		Mmap.put("rank1", rank);

		Mmap.put("status1", status);

		Mmap.put("name1", name);

		Mmap.put("year_of_comm1", year_of_comm);

		Mmap.put("year_of_dob1", year_of_dob);

		Mmap.put("p_arm1", p_arm);

		Mmap.put("comm_status1", comm_status);

		Mmap.put("getParentArmList", mcommon.getParentArmList());

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		
		return new ModelAndView(redirect, "Search_view_update_off_CMD",

				new TB_CENSUS_DETAIL_Parent());

	}
	

	@RequestMapping(value = "/view_UpadteOfficerDataUrl", method = RequestMethod.POST)

	public ModelAndView view_UpadteOfficerDataUrl(@ModelAttribute("idV") int updateid, ModelMap model,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,

			HttpServletRequest request,

			HttpSession sessionEdit, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();

		/*
		 * Boolean val = roledao.ScreenRedirect("View_UpdateOfficerDataUrl", roleid);
		 * 
		 * 
		 * 
		 * if (val == false) {
		 * 
		 * 
		 * 
		 * return new ModelAndView("AccessTiles");
		 * 
		 * 
		 * 
		 * }
		 */

		if (request.getHeader("Referer") == null) {

			msg = "";

			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		BigInteger comm_id = new BigInteger(request.getParameter("comm_idV"));

		String sus_no = request.getParameter("sus_noV");

		String IsMns = request.getParameter("IsMnsv");
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		int comm_status = Integer.parseInt(request.getParameter("comm_statusV"));

		String whrToOpen = request.getParameter("whrToOpen");

		if (whrToOpen == null) {

			whrToOpen = "";

		}

		model.put("whrToOpen", whrToOpen);

		/*
		 * 
		 * Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);
		 * 
		 * 
		 * 
		 * if (val == false) {
		 * 
		 * 
		 * 
		 * return new ModelAndView("AccessTiles");
		 * 
		 * 
		 * 
		 * }
		 * 
		 */

		if (request.getHeader("Referer") == null) {

			msg = "";

		}

		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {

			model.put("sus_no", roleSusNo);

			model.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		} else {

			roleSusNo = sus_no;

		}

		model.put("comm_id", comm_id);

		// 1

		List<Map<String, Object>> list = UOD.View_UpadteOfficerDataByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("list", list);

		model.put("size", list.size());

		// 2

		List<Map<String, Object>> listSH = UOD.Sh_UpadteOfficerDataByid(updateid, comm_id,

				comm_status);

		model.put("listSH", listSH);

		model.put("sizeSH", listSH.size());

		// 3

		List<Map<String, Object>> listCO = UOD.Cop_UpadteOfficerDataByid(updateid, comm_id,

				comm_status);

		model.put("listCO", listCO);

		model.put("sizeCO", listCO.size());

		// 4

		List<Map<String, Object>> listTR = UOD.TR_UpadteOfficerDataByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listTR", listTR);

		model.put("sizeTR", listTR.size());

		// 5

		List<Map<String, Object>> listS = UOD.Spouce_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		if (listS.size() != 0) {

			String a = (String) listS.get(0).get("adhar_number");
			String adhar = a.substring(0, 4) + " " + a.substring(4, 8) + " " + a.substring(8);
			model.put("adhar", adhar);
		}

		model.put("listS", listS);

		model.put("sizeS", listS.size());

		// 6

		List<Map<String, Object>> listAM = UOD.AWARD_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listAM", listAM);

		model.put("sizeAM", listAM.size());

		// 7

		List<Map<String, Object>> listTENU = UOD.TENU_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listTENU", listTENU);

		model.put("sizeTENU", listTENU.size());

		List<Map<String, Object>> listTENU_T = UOD.TENU_Total_View_UpadteByid(updateid, comm_id,

				roleSusNo, comm_status);

		model.put("listTENU_T", listTENU_T);

		model.put("sizeTENU_T", listTENU_T.size());

		List<Map<String, Object>> listRegimental = UOD.Regimental_View_UpadteByid(updateid, comm_id,

				roleSusNo);

		model.put("listRegimental", listRegimental);

		model.put("sizelistRegimental", listRegimental.size());

		List<Map<String, Object>> listFIS = UOD.Field_View_UpadteByid(updateid, comm_id, roleSusNo);

		model.put("listFIS", listFIS);

		model.put("sizeFIS", listFIS.size());

		int monthc = 0;

		for (int i = 0; i < listTENU.size(); i++) {

			monthc += Integer.parseInt(String.valueOf(listTENU.get(i).get("month")));

		}

		if (listFIS.size() > 0) {

			monthc -= Integer.parseInt(String.valueOf(listFIS.get(0).get("peace")));

		}

		model.put("peace_month", monthc);

		// 8

		List<Map<String, Object>> listARM = UOD.ARM_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listARM", listARM);

		model.put("sizeARM", listARM.size());

		// 9

		List<Map<String, Object>> listPE = UOD.PE_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listPE", listPE);

		model.put("sizePE", listPE.size());

		// 10

		List<Map<String, Object>> listAQ = UOD.AQ_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listAQ", listAQ);

		model.put("sizeAQ", listAQ.size());

		// 11

		List<Map<String, Object>> listPTQ = UOD.PTQ_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listPTQ", listPTQ);

		model.put("sizePTQ", listPTQ.size());

		// 12

		List<Map<String, Object>> listIL = UOD.ILan_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listIL", listIL);

		model.put("sizeIL", listIL.size());

		// 13

		List<Map<String, Object>> listFL = UOD.FLan_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listFL", listFL);

		model.put("sizeFL", listFL.size());

		// 14

		List<Map<String, Object>> listF = UOD.F_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listF", listF);

		model.put("sizeF", listF.size());

		// 15

		List<Map<String, Object>> listB = UOD.B_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listB", listB);

		model.put("sizeB", listB.size());

		// 16

		List<Map<String, Object>> listFS = UOD.FS_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listFS", listFS);

		model.put("sizeFS", listFS.size());

		// 17

		List<Map<String, Object>> listBA = UOD.BA_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listBA", listBA);

		model.put("sizeBA", listBA.size());

		// 18

		List<Map<String, Object>> listSE = UOD.SC_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listSE", listSE);

		model.put("sizeSE", listSE.size());

		/// Address//

		// 19

		List<Map<String, Object>> listORM = UOD.ORM_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listORM", listORM);

		model.put("sizeORM", listORM.size());

		// 20

		List<Map<String, Object>> listPDO = UOD.PDO_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listPDO", listPDO);

		model.put("sizePDO", listPDO.size());

		// 21

		List<Map<String, Object>> listPM = UOD.PM_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listPM", listPM);

		model.put("sizePM", listPM.size());

		// 22

		List<Map<String, Object>> listPS = UOD.PS_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listPS", listPS);

		model.put("sizePS", listPS.size());

		// 23

		List<Map<String, Object>> listNOK = UOD.NOK_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listNOK", listNOK);

		model.put("sizeNOK", listNOK.size());

		// 24

		List<Map<String, Object>> listNA = UOD.NA_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listNA", listNA);

		model.put("sizeNA", listNA.size());

		// 25

		List<Map<String, Object>> listFM = UOD.FM_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listFM", listFM);

		model.put("sizeFM", listFM.size());

		// 26

		List<Map<String, Object>> listAR = UOD.AR_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listAR", listAR);

		model.put("sizeAR", listAR.size());

		// 27

		List<Map<String, Object>> listRD = UOD.RD_View_UpadteByid(updateid, comm_id, roleSusNo,

				comm_status);

		model.put("listRD", listRD);

		model.put("sizeAR", listRD.size());

		List<Map<String, Object>> listCHILD = UOD.CHILD_View_UpadteByid(updateid, comm_id, roleSusNo);

		model.put("listCHILD", listCHILD);

		model.put("sizeCHILD", listCHILD.size());

		model.put("sus_no", sus_no);

		model.put("comm_status", comm_status);
		
		model.put("IsMns", IsMns);

		model.put("serving_status", SD.GetServingStatus(comm_id).get(0).get(2));

		model.put("msg", msg);

		return new ModelAndView("View_Update_Officer_DataTiles");

	}

	@RequestMapping(value = "/view_ApproveUpadteOfficerDataUrl", method = RequestMethod.POST)

	public ModelAndView view_ApproveUpadteOfficerDataUrl(ModelMap Mmap,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,

			HttpServletRequest request, HttpSession sessionEdit, HttpSession session,

			@ModelAttribute("personnel_no5") String personnel_no5,

			@ModelAttribute("status5") String status5,

			@ModelAttribute("rank5") String rank,

			@ModelAttribute("comm_id5") String comm_id51,

			@ModelAttribute("unit_name5") String unit_name,

			@ModelAttribute("unit_sus_no5") String unit_sus_no)

			throws NumberFormatException, ParseException {

		BigInteger comm_id5 = BigInteger.ZERO;
		if (comm_id5 != BigInteger.ZERO) {
			comm_id5 = new BigInteger(comm_id51);
		}

		String updateid = request.getParameter("ap_id");

		BigInteger comm_id = new BigInteger(request.getParameter("ap_comm_id"));

		String personnel_no = request.getParameter("ap_personnel_no");

		String event = request.getParameter("ap_event");

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";

			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		Mmap.put("personnel_no5", personnel_no5);

		Mmap.put("status5", status5);

		Mmap.put("rank5", rank);

		Mmap.put("comm_id5", comm_id5);

		Mmap.put("unit_name5", unit_name);

		Mmap.put("unit_sus_no5", unit_sus_no);

		Date modifiedDate = UOD.getParentModifiedDate(Integer.parseInt(updateid), comm_id);

		List<TB_CHANGE_OF_RANK> ChangeOfRank = UOD.getChangeOfRankData(Integer.parseInt(updateid), comm_id,
				modifiedDate);

		List<TB_CHANGE_TNAI_NO> ChangeOfTnaiNo = UOD.getChangeOfTnaiNoData(Integer.parseInt(updateid), comm_id);

		List<TB_CHANGE_NAME> ChangeOfName = UOD.getChangeOfNameData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_NON_EFFECTIVE> NonEffective = UOD.getNon_effective(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CHANGE_TA_STATUS> ChangeOfTA = UOD.getChangeOfTAData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_CHILDREN> ChildDetails = UOD.getm_children_detailsData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_CDA_ACCOUNT_NO> CDAaccount = UOD.cda_acnt_no_GetData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CHANGE_RELIGION> religion = UOD.religion_GetData(Integer.parseInt(updateid), comm_id,

				modifiedDate);

		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = UOD.getcocData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_PSG_EXTENSION_OF_COMISSION> ExtenComm = UOD.geteocData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_INTER_ARM_TRANSFER> InterArmTrans = UOD.getInterArm(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_INTER_ARM_TRANSFER> InterArmTrans_old = UOD.getInterArm_old(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_AWARDSNMEDAL> AwardsMedal = UOD.getawardsNmedalData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_LANGUAGE> Language = UOD.update_census_getlanguagedetailsData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_QUALIFICATION> Qualification = UOD.update_census_getQualificationData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_SECONDMENT> Secondment = UOD.get_Secondment(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = UOD.get_Appointment(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_FOREIGN_COUNTRY> ForeignCountry = UOD.getUPForeginCountryData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_FAMILY_MRG> Marital = UOD.update_getfamily_marriageData(Integer.parseInt(updateid),

				comm_id, modifiedDate, Integer.parseInt(event));

		List<TB_CENSUS_SPOUSE_QUALIFICATION> Spouse_Quali = UOD.getUpdatedSpouseQualificationData(comm_id,
				modifiedDate);

		List<TB_CENSUS_FIRING_STANDARD> FiringStan = UOD.getfire_detailsData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_NOK> NOK = UOD.nok_details_GetData(Integer.parseInt(updateid), comm_id,

				modifiedDate);

		List<TB_CENSUS_BPET> BEPT = UOD.getbpet_Data(Integer.parseInt(updateid), comm_id,

				modifiedDate);

		List<TB_CENSUS_IDENTITY_CARD> IdentityCard = UOD.Ide_card_getData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_ADDRESS> ChangeAdd = UOD.address_details_GetData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_BATT_PHY_CASUALITY> btel_cas = UOD.Battle_and_Physical_Casuality_GetData(

				Integer.parseInt(updateid), comm_id, modifiedDate);

		List<TB_CENSUS_DISCIPLINE> Discipline = UOD.get_Discipline(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_PROMOTIONAL_EXAM> Promotional_Exam = UOD.update_census_promo_examData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_ARMY_COURSE> Army_Course = UOD.update_census_army_courseData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> Allergy = UOD.update_census_allergicData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = UOD.getUpdatedmadicalData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		List<TB_DESERTER> deserter = UOD.get_Deserter(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		// keval

		List<TB_PSG_CANTEEN_CARD_DETAIL1> CSDDetails = UOD.getCSD_detailsData(Integer.parseInt(updateid),

				comm_id, modifiedDate);

		Mmap.put("msg", msg);

		Mmap.put("census_id", updateid);

		Mmap.put("personnel_no2", personnel_no);

		Mmap.put("comm_id", comm_id);

		Mmap.put("ChangeOfTA", ChangeOfTA);

		Mmap.put("event", event);

		// bisag v2 03072023(Observation)
		Mmap.put("getProfession", mcommon.getProfession());

		Mmap.put("ChangeOfRank", ChangeOfRank);
		Mmap.put("changeTnaiNo", ChangeOfTnaiNo);
		Mmap.put("gettastatusList", mcommon.getstatusList());
		Mmap.put("ChangeOfName", ChangeOfName);
		Mmap.put("NonEffective", NonEffective);
		Mmap.put("ChildDetails", ChildDetails);
		Mmap.put("CDAaccount", CDAaccount);
		Mmap.put("religion", religion);
		Mmap.put("ChangeOfComm", ChangeOfComm);
		Mmap.put("ExtensionComm", ExtenComm);
		Mmap.put("InterArmTransfer", InterArmTrans);
		Mmap.put("InterArmTransfer_old", InterArmTrans_old);
		Mmap.put("AwardsMedal", AwardsMedal);
		Mmap.put("Language", Language);
		Mmap.put("Qualification", Qualification);
		Mmap.put("Secondment", Secondment);
		Mmap.put("ChngAppointment", ChngAppointment);
		Mmap.put("ForeignCountry", ForeignCountry);
		Mmap.put("FiringStan", FiringStan);
		Mmap.put("NOK", NOK);
		Mmap.put("BEPT", BEPT);
		Mmap.put("IdentityCard", IdentityCard);
		Mmap.put("ChangeAdd", ChangeAdd);
		Mmap.put("Marital", Marital);
		Mmap.put("Spouse_Quali", Spouse_Quali);

		Mmap.put("btel_cas", btel_cas);

		Mmap.put("Discipline", Discipline);

		Mmap.put("Promotional_Exam", Promotional_Exam);

		Mmap.put("Army_Course", Army_Course);

		Mmap.put("Allergy", Allergy);

		Mmap.put("MedDetails", MedDetails);

		Mmap.put("deserterV", deserter);

		Mmap.put("CSDDetails", CSDDetails);// kevalc

		Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());

		Mmap.put("getRelationList", mcommon.getRelationList());

		Mmap.put("getReligionList", mcommon.getReligionList());

		Mmap.put("getSeconded_To", mcommon.getSeconded_To());

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());

		Mmap.put("getNon_EffectiveList", mcommon.getNon_EffectiveList());

		Mmap.put("getLanguageList", mcommon.getLanguageList());

		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());

		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());

		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());

		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));

		Mmap.put("getParentArmList", mcommon.getParentArmList());

		Mmap.put("getRegtList", mcommon.getRegtList(""));

		Mmap.put("getForeign_country", psg_com.getforiegnCountry());

		Mmap.put("getForiegnCountryList", mcommon.getForeign_country());

		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());

		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());

		Mmap.put("getDivclass", mcommon.getDivclass());

		Mmap.put("getSubject", mcommon.getSubject());

		Mmap.put("getExamination", mcommon.getExamination());

		Mmap.put("getCommandDetailsList", m.getCommandDetailsList());

		Mmap.put("getMedalList", mcommon.getMedalList());

		Mmap.put("getTypeofAppointementList", mcommon.getTypeofAppointementList());

		Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());

		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());

		// Mmap.put("getVillageList", mcommon.getVillageList());

		Mmap.put("getNationalityList", mcommon.getNationalityList());

		Mmap.put("getFiring_event_qe", mcommon.getFiring_event_qe());

		Mmap.put("getBPET_event_qe", mcommon.getBPET_event_qe());

		Mmap.put("getFiring_grade", mcommon.getFiring_grade());

		Mmap.put("getBPET_result", mcommon.getBPET_result());

		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());

		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());

		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		// Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));

		// Mmap.put("getMedCityName", mcommon.getMedCityName("", session));

		// Mmap.put("getCountryList", mcommon.getCountryList());

		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));

		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());

		Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());

		Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());

		Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());

		Mmap.put("getOFFR_Non_EffectiveList", mcommon.getOFFR_Non_EffectiveList(""));

		Mmap.put("getPSG_CommandList", psg_com.getPSG_CommandList());

		Mmap.put("getPSG_CorpsList", psg_com.getPSG_CorpsList());

		Mmap.put("getPSG_DivList", psg_com.getPSG_DivList());

		Mmap.put("getPSG_BdeList", psg_com.getPSG_BdeList());

		Mmap.put("getPersonalType", mcommon.getPersonalType());

		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());

		Mmap.put("getOFFTypeofRankList", mcommon.getOFFTypeofRankList());

		Mmap.put("getChild_RelationshipList", mcommon.getChild_RelationshipList());

		Mmap.put("getChild_TypeList", mcommon.getChild_TypeList());

		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());

		Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));

		Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));

		Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));

		Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));

		Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));

		Mmap.put("getOprationList", mcommon.getOprationList());

		Mmap.put("getDivclass", mcommon.getDivclass());

		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());

		Mmap.put("getExaminationTypeList", mcommon.getExaminationTypeList());

		// Mmap.put("getStreamList", mcommon.getStreamList());

		Mmap.put("getCourseName", mcommon.getCourseName());

		Mmap.put("getFdService", mcommon.getFdService());

		Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());

		Mmap.put("getExamList", mcommon.getExamList());

		Mmap.put("getSpecializationList", mcommon.getSpecializationList());

		Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());

		Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());

		Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());

		Mmap.put("getExservicemenList", mcommon.getExservicemenList());

		Mmap.put("getMissingList", mcommon.getMissingList());

		Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
		Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));

		// -----smit--------//

		Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());

		Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());

		Mmap.put("getSub_Clause", mcommon.getSub_Clause());

//kajal
		// Mmap.put("getBattleDescList", mcommon.getBattleDescList());

		Mmap.put("msg", msg);

		return new ModelAndView("View_ApproveUpdateOfficerDataTiles");

	}

	@RequestMapping(value = "/Print_Record_Service", method = RequestMethod.POST)
	public ModelAndView Print_Record_Service(@ModelAttribute("comm_id1") BigInteger comm_id, ModelMap Mmap,

			@ModelAttribute("census_id1") String census_id, @ModelAttribute("comm_status1") String comm_status1,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) throws NumberFormatException, ParseException {

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		List<String> TH = new ArrayList<String>();
		int comm_status = Integer.parseInt(comm_status1);
		String sus_no = request.getParameter("sus_noV");
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		} else {
			roleSusNo = sus_no;
		}

		String serving_status = SD.GetServingStatus(comm_id).get(0).get(2);
		List<Map<String, Object>> listp = UOD.View_UpadteOfficerDataByid(Integer.parseInt(census_id), comm_id,
				roleSusNo, comm_status);

		List<Map<String, Object>> listSH = UOD.Sh_UpadteOfficerDataByid(Integer.parseInt(census_id), comm_id,
				comm_status);
		List<Map<String, Object>> listCO = UOD.Cop_UpadteOfficerDataByid(Integer.parseInt(census_id), comm_id,
				comm_status);

		// ---Traning Details- No TABLE

		List<Map<String, Object>> listTR = UOD.TR_UpadteOfficerDataByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ---Family Details - No TABLE
		List<Map<String, Object>> listFM = UOD.FM_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ---- Spouse Details

		List<Map<String, Object>> listS = UOD.Spouce_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH1 = new ArrayList<String>();
		TH1.add("Marital Status");
		TH1.add("Maiden Name");
		TH1.add("Date of Marriage");
		TH1.add("Date of Birth");
		TH1.add("Place of Birth");
		TH1.add("Nationality");
		TH1.add("Aadhaar No");
		TH1.add("PAN");
		TH1.add("Divorce Date");
		TH1.add("Service/Ex-Service");
		TH1.add("Personal No");

		// ---- Children Details
		List<Map<String, Object>> listCHILD = UOD.CHILD_View_UpadteByid(Integer.parseInt(census_id), comm_id,
				roleSusNo);

		List<String> TH2 = new ArrayList<String>();
		TH2.add("Name");
		TH2.add("Date of Birth");
		TH2.add("Relationship");

		// ---- AWARDS AND MEDAL
		List<Map<String, Object>> listAM = UOD.AWARD_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH3 = new ArrayList<String>();
		TH3.add("Award/Medal Type");
		TH3.add("Award/Medal");
		TH3.add("Date of Awards/Medal");
		TH3.add("Unit Concerned");

		// ----------TENURE DETAILS
		List<Map<String, Object>> listTENU = UOD.TENU_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<Map<String, Object>> listTENU_T = UOD.TENU_Total_View_UpadteByid(Integer.parseInt(census_id), comm_id,
				roleSusNo, comm_status);
		List<String> TH4 = new ArrayList<String>();

		TH4.add("Unit Name");
		TH4.add("Date of TOS");
		TH4.add("Date of SOS");
		TH4.add("Place");
		TH4.add("Unit Loc Type");
		TH4.add("Command");
		TH4.add("Tenure(Months)");

		// -------REGIMENTAL DUTIES

		List<Map<String, Object>> listRegim = UOD.Regimental_View_UpadteByid(Integer.parseInt(census_id), comm_id,
				roleSusNo);
		List<String> TH5 = new ArrayList<String>();
		TH5.add("UnitName");
		TH5.add("Rank");
		TH5.add("Appointment");
		TH5.add("Date");
		TH5.add("Place");
		TH5.add("Unit Loc Type");
		TH5.add("Command");

		// ---FIELD SERVICE - No TABLE

		List<Map<String, Object>> listFIS = UOD.Field_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo);
		int monthc = 0;
		for (int i = 0; i < listTENU.size(); i++) {
			monthc += Integer.parseInt(String.valueOf(listTENU.get(i).get("month")));
		}
		if (listFIS.size() > 0) {
			monthc -= Integer.parseInt(String.valueOf(listFIS.get(0).get("peace")));
		}

		String peace_month = String.valueOf(monthc);

		// ---ARMY COURSE ---
		List<Map<String, Object>> listARM = UOD.ARM_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH6 = new ArrayList<String>();
		TH6.add("Course Name");
		TH6.add("Grade");
		TH6.add("Name of Institute");
		TH6.add("From Date");
		TH6.add("To Date");

		// ------PROMOTIONAL EXAM

		List<Map<String, Object>> listPE = UOD.PE_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH7 = new ArrayList<String>();
		TH7.add("Exam");
		TH7.add("Month And Year of Exam");

		// ---ACADEMIC QUALIFICATIONS

		List<Map<String, Object>> listAQ = UOD.AQ_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH8 = new ArrayList<String>();
		TH8.add("Exam Passed");

		TH8.add("Degree Acquired");
		TH8.add("Specialization");
		TH8.add("Year of Passing");
		TH8.add("Div/Class");
		TH8.add("Subjects");
		TH8.add("Institute");

		// ----PROFESSIONAL/TECHNICAL QUALIFICATIONS

		List<Map<String, Object>> listPTQ = UOD.PTQ_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH9 = new ArrayList<String>();
		TH9.add("Exam Passed");
		TH9.add("Degree Acquired");
		TH9.add("Specialization");
		TH9.add("Year of Passing");
		TH9.add("Div/Class");
		TH9.add("Subjects");
		TH9.add("Institute");

		// -----------LANGUAGE DETAILS

		List<Map<String, Object>> listIL = UOD.ILan_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH10 = new ArrayList<String>();
		TH10.add("Indian");
		TH10.add("Language Std");

		// -----------FOREIGN DETAILS
		List<Map<String, Object>> listFL = UOD.FLan_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH11 = new ArrayList<String>();
		TH11.add("Foreign");
		TH11.add("Language Std");
		TH11.add("Language Prof");

		// ----------FOREIGN COUNTRIES VISITED
		List<Map<String, Object>> listF = UOD.F_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH12 = new ArrayList<String>();
		TH12.add("Country Visited");
		TH12.add("Purpose of Visit");
		TH12.add("From");
		TH12.add("To");
		TH12.add("Duration");

		// ----------BPET

		List<Map<String, Object>> listB = UOD.B_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH13 = new ArrayList<String>();
		TH13.add("BPET Result");
		TH13.add("BPET Qe");
		TH13.add("Year");

		// ----------FIRING STANDARD

		List<Map<String, Object>> listFS = UOD.FS_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH14 = new ArrayList<String>();
		TH14.add("Firing Grade");
		TH14.add("Firing Event Qe");
		TH14.add("Year");

		// ----------BATTLE AND PHYSICAL CASULTY

		List<Map<String, Object>> listBA = UOD.BA_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		List<String> TH15 = new ArrayList<String>();
		TH15.add("Classification of Casualty");
		TH15.add("Nature of Casualty");
		TH15.add("Name of Operation");
		TH15.add("Date of Casualty");
		TH15.add("Cause of Casualty");
		TH15.add("Exact Place/Area/Post");

		// ----------SECONDMENT - No TABLE

		List<Map<String, Object>> listSE = UOD.SC_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		// ------ADDRESS DETAILS - No TABLE
		// ---ORIGINAL DOMICILE OF
		List<Map<String, Object>> listORM = UOD.ORM_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		// ----PRESENTLY DOMICILE OF

		List<Map<String, Object>> listPDO = UOD.PDO_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ------PERMANENT ADDRESS

		List<Map<String, Object>> listPM = UOD.PM_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ---------PRESENT ADDRESS
		List<Map<String, Object>> listPS = UOD.PS_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// -----NOK DETAILS

		List<Map<String, Object>> listNOK = UOD.NOK_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		/// -----NOK ADDRESS

		List<Map<String, Object>> listNA = UOD.NA_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ---------ADDRESS RETIREMENT
		List<Map<String, Object>> listAR = UOD.AR_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);

		// ------RETIREMENT DETAILS
		List<Map<String, Object>> listRD = UOD.RD_View_UpadteByid(Integer.parseInt(census_id), comm_id, roleSusNo,
				comm_status);
		String Heading = "\nRecord Service";
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
		return new ModelAndView(new Download_Record_Service("L", Heading, username, foot, TH, TH1, TH2, TH3, TH4, TH5,
				TH6, TH7, TH8, TH9, TH10, TH11, TH12, TH13, TH14, TH15, listSH, listCO, listTR, listFM, listS,
				listCHILD, listAM, listTENU, listRegim, listFIS, listARM, listPE, listAQ, listPTQ, listIL, listFL,
				listF, listB, listFS, listBA, listSE, listORM, listPDO, listPM, listPS, listNOK, listNA, listAR,
				listTENU_T, peace_month, listRD, serving_status), "userList", listp);
	}
	
	
	@RequestMapping(value = "/admin/View_UpdateOfficerData_mnsUrl", method = RequestMethod.GET)

	public ModelAndView View_UpdateOfficerData_mnsUrl(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleType = session.getAttribute("roleType").toString();

		String roleid = session.getAttribute("roleid").toString();

//		Boolean val = roledao.ScreenRedirect("View_UpdateOfficerData_mnsUrl", roleid);

//		if (val == false) {
//
//			return new ModelAndView("AccessTiles");
//
//		}

//		if (request.getHeader("Referer") == null) {
//
//			msg = "";
//
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//
//		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

		Mmap.put("msg", msg);

		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());

		Mmap.put("getParentArmList", mcommon.getParentArmList());

//		Mmap.put("list",
//				UOD.Search_UpdateOffData("", "", "", "0", "0", roleSusNo, roleType, "", "", "", "", "1", roleAccess));

		return new ModelAndView("Search_view_update_off_mnsTiles");

	}
}