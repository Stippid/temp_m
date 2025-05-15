package com.controller.psg.Census;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Census_madicalCatController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.Transaction.Search_CensusDao;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_MEDICAL_CATEGORY_HISTORY;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = { "admin", "/", "user" })

public class Search_Census_Controller {

	@Autowired

	Search_CensusDao cen;

	Psg_CommonController p_comm = new Psg_CommonController();

	//CommanController all = new CommanController();
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	PsgDashboardController das = new PsgDashboardController();

	@Autowired

	Psg_CommanDAO psg_com;

	@Autowired

	private RoleBaseMenuDAO roledao;
	
	
	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/search_censusUrl", method = RequestMethod.GET)
	public ModelAndView search_censusUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
//      Mmap.put("list", cen.Search_census(roleSusNo, "", "0", "","","", session));
		Mmap.put("status1", "0");
		//Mmap.put("status1", "0");
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());		
		return new ModelAndView("search_censusTiles");

	}
	
	@RequestMapping(value = "/admin/search_censusMnsUrl", method = RequestMethod.GET)
	public ModelAndView search_censusMnsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_censusMnsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
//      Mmap.put("list", cen.Search_census(roleSusNo, "", "0", "","","", session));
		Mmap.put("status1", "0");
		//Mmap.put("status1", "0");
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());		
		return new ModelAndView("search_censusMnsTiles");

	}
	@RequestMapping(value = "/Approve_CensusData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_CommissioningData(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(cen.approve_censusData(a,sus_no,status,username));
		return list2;
	}

	/// bisag v2 230822  (changes made while converting to Datatable)
	@RequestMapping(value = "/admin/GetSearch_Census", method = RequestMethod.POST)
	public ModelAndView GetSearch_Census(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
			@RequestParam(value = "cr_date1", required = false) String cr_date,
			@RequestParam(value = "IsMns", required = false) String IsMns) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);
		String redirect = "search_censusUrl";
		String tiles = "search_censusTiles";
		if (IsMns.equals("True")) {
			val = roledao.ScreenRedirect("search_censusMnsUrl", roleid);
			redirect = "search_censusMnsUrl";
			tiles = "search_censusMnsTiles";
		}
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if (unit_sus_no != "") {
			if (!valid.SusNoLength(unit_sus_no)) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:" + redirect + "");
			}
			if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:" + redirect + "");
			}
		}
		if (unit_name != "") {
			if (!valid.isUnit(unit_name)) {
				Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
				return new ModelAndView("redirect:" + redirect + "");
			}
		}
		if (personnel_no != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:" + redirect + "");
			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {
				Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
				return new ModelAndView("redirect:" + redirect + "");
			}
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		Mmap.put("personnel_no1", personnel_no);
		Mmap.put("rank1", rank);
		Mmap.put("status1", status);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);

		return new ModelAndView(tiles, "Search_Census_letterCMD", new TB_TRANS_PROPOSED_COMM_LETTER());

	}
	

	@RequestMapping(value = "/admin/edit_CensusDetails", method = RequestMethod.POST)

	public ModelAndView edit_CensusDetails(ModelMap Mmap, HttpSession session,HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "personnel_no_edit", required = false) String personnel_no,

			@RequestParam(value = "personnel_no2", required = false) String personnel_no_return,

			@RequestParam(value = "status2", required = false) String status_return,

			@RequestParam(value = "rank2", required = false) String rank_return,

	       @RequestParam(value = "unit_name2", required = false) String unit_name_return,

	       @RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no_return,
	       @RequestParam(value = "IsMns", required = false) String IsMns) {

		Mmap.put("personnel_no", personnel_no);

		Mmap.put("status_return", status_return);

		Mmap.put("rank_return", rank_return);

		Mmap.put("unit_name_return", unit_name_return);

		Mmap.put("personnel_no_return", personnel_no_return);

		Mmap.put("unit_sus_no_return", unit_sus_no_return);

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);
		String tiles="formTiles";
		if(IsMns.equals("True")) {
			val = roledao.ScreenRedirect("search_censusMnsUrl", roleid);
			tiles="formCensusMnsTiles";
		}

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}

		Mmap.put("getReligionList", p_comm.getReligionList());

		Mmap.put("getMarital_statusList", p_comm.getMarital_statusList());

		Mmap.put("getBloodList", p_comm.getBloodList());

		Mmap.put("getRelationList", p_comm.getRelationList());

		Mmap.put("getPreCadetStatusList", p_comm.getPreCadetStatusList());

		Mmap.put("getNCCTypeList", p_comm.getNCCTypeList());

		Mmap.put("getLanguageSTDList", p_comm.getLanguageSTDList());

		Mmap.put("getLanguagePROOFList", p_comm.getLanguagePROOFList());

		Mmap.put("getForiegnLangugeList", p_comm.getForiegnLangugeList());

		Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));

		Mmap.put("getMedStateName", p_comm.getMedStateName("", session));

		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));

		Mmap.put("getNationalityList", p_comm.getNationalityList());

		Mmap.put("getMedCatList", p_comm.getMedCatList());

		// Mmap.put("getRHList", p_comm.getRHList());

		// Mmap.put("getVillageList", p_comm.getVillageList());

		Mmap.put("getQualificationTypeList", p_comm.getQualificationTypeList());

//		Mmap.put("getInstituteNameList", p_comm.getInstituteNameList());

		Mmap.put("getLanguageList", p_comm.getLanguageList());

		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());

		 Mmap.put("getSpecializationList", p_comm.getSpecializationList());

		Mmap.put("getHair_ColourList", p_comm.getHair_ColourList());

		Mmap.put("getEye_ColourList", p_comm.getEye_ColourList());

		Mmap.put("getDivclass", p_comm.getDivclass());

		Mmap.put("getSubject", p_comm.getSubject());

//		Mmap.put("getExamination", p_comm.getExamination());

//		Mmap.put("getStreamList", p_comm.getStreamList());

//		Mmap.put("getExaminationTypeList", p_comm.getExaminationTypeList());

//		Mmap.put("getCourseName", p_comm.getCourseName());

		Mmap.put("getCommInstitute", p_comm.getInstitute("1"));

		Mmap.put("getTrainingInstitute", p_comm.getInstitute("2"));

		Mmap.put("getHeight", p_comm.getHeight());

		Mmap.put("getBattalionList", p_comm.getBattalionList());

		Mmap.put("getCompanyList", p_comm.getCompanyList());

		Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());

		Mmap.put("getForeign_country", p_comm.getForeign_country());

		Mmap.put("getForiegnCountryList", psg_com.getforiegnCountry());

		Mmap.put("getIndianLanguageList", p_comm.getIndianLanguageList());

		Mmap.put("getForeignLanguageList", p_comm.getForeignLanguageList());

		Mmap.put("getMedCityName", p_comm.getMedCityName("", session));

		Mmap.put("getProfession", p_comm.getProfession());

		Mmap.put("getShapeStatusList", p_comm.getShapeStatusList());

		Mmap.put("getcCopeList", p_comm.getCopeValueList("C"));

		Mmap.put("getoCopeList", p_comm.getCopeValueList("O"));

		Mmap.put("getpCopeList", p_comm.getCopeValueList("P"));

		Mmap.put("geteCopeList", p_comm.getCopeValueList("E"));

		Mmap.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));

		Mmap.put("getExservicemenList", p_comm.getExservicemenList());

		Mmap.put("msg", msg);

		LocalDate date_without_time = LocalDate.now();

		Mmap.put("date_without_time", date_without_time);

		return new ModelAndView(tiles);

	}

	@RequestMapping(value = "/view_censusUrl" , method = RequestMethod.POST)

	public ModelAndView view_censusUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "personnel_no_view", required = false) String personnel_no,

			@RequestParam(value = "app_status", required = false) String app_status,

			@RequestParam(value = "personnel_no3", required = false) String personnel_no_return,

			@RequestParam(value = "status3", required = false) String status_return,

			@RequestParam(value = "rank3", required = false) String rank_return,

	       @RequestParam(value = "unit_name3", required = false) String unit_name_return,

	       @RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no_return) {



		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);

		if (val == false) {

			return new ModelAndView("AccessTiles");

		}

		if (request.getHeader("Referer") == null) {

			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");

		}



		Mmap.put("personnel_no", personnel_no);

		Mmap.put("app_status", app_status);

		Mmap.put("status_return", status_return);

		Mmap.put("rank_return", rank_return);

		Mmap.put("unit_name_return", unit_name_return);

		Mmap.put("personnel_no_return", personnel_no_return);

		Mmap.put("unit_sus_no_return", unit_sus_no_return);

		String roleType = session.getAttribute("roleType").toString();

		Mmap.put("roleType", roleType);

		Mmap.put("getReligionList", p_comm.getReligionList());

		Mmap.put("getMarital_statusList", p_comm.getMarital_statusList());

		Mmap.put("getBloodList", p_comm.getBloodList());

		Mmap.put("getRelationList", p_comm.getRelationList());

		Mmap.put("getPreCadetStatusList", p_comm.getPreCadetStatusList());

		Mmap.put("getExservicemenList", p_comm.getExservicemenList());

		Mmap.put("getNCCTypeList", p_comm.getNCCTypeList());

		Mmap.put("getLanguageSTDList", p_comm.getLanguageSTDList());

		Mmap.put("getLanguagePROOFList", p_comm.getLanguagePROOFList());

		Mmap.put("getForiegnLangugeList", p_comm.getForiegnLangugeList());

		Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));

		Mmap.put("getMedStateName", p_comm.getMedStateName("", session));

		Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));

		Mmap.put("getNationalityList", p_comm.getNationalityList());

		Mmap.put("getMedCatList", p_comm.getMedCatList());

		// Mmap.put("getRHList", p_comm.getRHList());

		// Mmap.put("getVillageList", p_comm.getVillageList());

		Mmap.put("getQualificationTypeList", p_comm.getQualificationTypeList());

//		Mmap.put("getInstituteNameList", p_comm.getInstituteNameList());

		Mmap.put("getLanguageList", p_comm.getLanguageList());

		Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());

		 Mmap.put("getSpecializationList", p_comm.getSpecializationList());

		Mmap.put("getHair_ColourList", p_comm.getHair_ColourList());

		Mmap.put("getEye_ColourList", p_comm.getEye_ColourList());

		Mmap.put("getDivclass", p_comm.getDivclass());

		Mmap.put("getSubject", p_comm.getSubject());

//		Mmap.put("getExamination", p_comm.getExamination());

//		Mmap.put("getStreamList", p_comm.getStreamList());

//		Mmap.put("getExaminationTypeList", p_comm.getExaminationTypeList());

//		Mmap.put("getCourseName", p_comm.getCourseName());

		
		Mmap.put("getCommInstitute", p_comm.getInstitute_psg("1"));

		Mmap.put("getTrainingInstitute", p_comm.getInstitute_psg("2"));

		Mmap.put("getHeight", p_comm.getHeight());

		Mmap.put("getBattalionList", p_comm.getBattalionList());

		Mmap.put("getCompanyList", p_comm.getCompanyList());

		Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());

		Mmap.put("getForeign_country", p_comm.getForeign_country());

		Mmap.put("getForiegnCountryList", psg_com.getforiegnCountry());

		Mmap.put("getIndianLanguageList", p_comm.getIndianLanguageList());

		Mmap.put("getForeignLanguageList", p_comm.getForeignLanguageList());

		Mmap.put("getMedCityName", p_comm.getMedCityName("", session));

		Mmap.put("getProfession", p_comm.getProfession());

		Mmap.put("getShapeStatusList", p_comm.getShapeStatusList());

		Mmap.put("getcCopeList", p_comm.getCopeValueList("C"));

		Mmap.put("getoCopeList", p_comm.getCopeValueList("O"));

		Mmap.put("getpCopeList", p_comm.getCopeValueList("P"));

		Mmap.put("geteCopeList", p_comm.getCopeValueList("E"));

		Mmap.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));

		Mmap.put("getExservicemenList", p_comm.getExservicemenList());

		Mmap.put("msg", msg);

		LocalDate date_without_time = LocalDate.now();

		Mmap.put("date_without_time", date_without_time);

		return new ModelAndView("census_viewTiles");

	}
//--------------- pranay 07.06
	
	@RequestMapping(value = "/Approve_Census", method = RequestMethod.POST)
	public @ResponseBody String Approve_Census( HttpServletRequest request,
			HttpSession sessionA, ModelMap model) {

		/*DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);*/
		String[] id_list = request.getParameter("census_id").split(":"); 
		List<String> liststr = new ArrayList<String>();
		String username = sessionA.getAttribute("username").toString();
		String modify_authority = request.getParameter("modify_authority");
		String modify_date_of_authority = request.getParameter("modify_date_of_authority");
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		 for (int ik = 0; ik < id_list.length; ik++) {
			 Integer id = new Integer((id_list[ik]));
			 
		String hqlUpdate = "update TB_CENSUS_DETAIL_Parent set status=:status,modified_by=:modified_by,modified_date=:modified_date,update_ofr_status=:update_ofr_status  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("update_ofr_status", 1)
				.setString("modified_by", username).setTimestamp("modified_date", new Date()).setInteger("id", id)
				.executeUpdate();

		TB_CENSUS_DETAIL_Parent census_parent = (TB_CENSUS_DETAIL_Parent) sessionHQL.get(TB_CENSUS_DETAIL_Parent.class, id);
		
		
		//RELIGION
		TB_CHANGE_RELIGION n = new TB_CHANGE_RELIGION();
		n.setReligion(census_parent.getReligion());
		n.setOther(census_parent.getReligion_other());
		n.setComm_id(census_parent.getComm_id());
		n.setCensus_id(census_parent.getId());
		
			try {
				if(checkHistoryStatus(census_parent.getComm_id())) {
					String ReligionUpdate = "update TB_CHANGE_RELIGION set status=:status,modified_by=:modified_by,modified_date=:modified_date where comm_id=:comm_id and status = 1";
					int ru = sessionHQL.createQuery(ReligionUpdate).setInteger("status", 2).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", census_parent.getComm_id()).executeUpdate();
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		n.setAuthority(username);
		n.setDate_of_authority(new Date());
		n.setStatus(1);
		n.setCreated_by(username);
		n.setCreated_date(new Date());
		n.setModified_by(username);
		n.setModified_date(new Date());
		sessionHQL.save(n);

		
		
		//NAME
		TB_CHANGE_NAME c_name = new TB_CHANGE_NAME();
		c_name.setComm_id(census_parent.getComm_id());
		c_name.setCensus_id(census_parent.getId());
		c_name.setName(census_parent.getFirst_name()+" "+ census_parent.getMiddle_name() +" "+ census_parent.getLast_name());
		
		try {
			if(checkHistoryStatus(census_parent.getComm_id())) {
				String NameUpdate = "update TB_CHANGE_NAME set status=:status,modified_by=:modified_by,modified_date=:modified_date where comm_id=:comm_id and status = 1";
				int nu = sessionHQL.createQuery(NameUpdate).setInteger("status", 2).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", census_parent.getComm_id()).executeUpdate();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c_name.setAuthority(username);
		c_name.setDate_of_authority(new Date());
		c_name.setStatus(1);
		c_name.setCreated_by(username);
		c_name.setCreated_date(new Date());
		c_name.setModified_by(username);
		c_name.setModified_date(new Date());
		sessionHQL.save(c_name);

		
		
		//COMM LETTER
		hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set name=:name,modified_by=:modified_by,modified_date=:modified_date  where id=:comm_id";
	     String Name = census_parent.getFirst_name() + " " + census_parent.getMiddle_name() + " " + census_parent.getLast_name();
		int comm_name_change = sessionHQL.createQuery(hqlUpdate).setString("name", Name) .setString("modified_by", username).
				setTimestamp("modified_date", new Date()).setParameter("comm_id", census_parent.getComm_id()).executeUpdate();

		
		
		hqlUpdate = "update TB_CENSUS_IDENTITY_CARD set status=:status,modified_by=:modified_by,modified_date=:modified_date  where census_id=:id";
		int idapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		
		try {
			if(checkHistoryStatus(census_parent.getComm_id())) {
				String AddressUpdate = "update TB_CENSUS_ADDRESS set status=:status,modified_by=:modified_by,modified_date=:modified_date where comm_id=:comm_id and status = 1";
				int au = sessionHQL.createQuery(AddressUpdate).setInteger("status", 2).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", census_parent.getComm_id()).executeUpdate();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		hqlUpdate = "update TB_CENSUS_ADDRESS set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id";
		int addapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		
		
		
		hqlUpdate = "update TB_CENSUS_NOK set status=:status,modified_by=:modified_by,modified_date=:modified_date  where census_id=:id";
		int nokapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		
		
		
		hqlUpdate = "update TB_CENSUS_FAMILY_SIBLINGS set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id";
		int sibapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		
		
		Query qm = null;
		
		qm = sessionHQL.createQuery(" from TB_CENSUS_FAMILY_MRG where  cen_id=:id order by marriage_date desc").setMaxResults(1);
		qm.setParameter("id", id);

		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FAMILY_MRG> listmrg = (List<TB_CENSUS_FAMILY_MRG>) qm.list();
		int mrgid = 0;
		if (listmrg.size()>0) {
			 mrgid = listmrg.get(0).getId();
		}
		
		hqlUpdate = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id and id=:mid ";
		int marrappt = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).setInteger("mid", (mrgid)).executeUpdate();
		
		
		
		hqlUpdate = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id and id!=:mid ";
		int marrapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 2).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).setInteger("mid", (mrgid)).executeUpdate();
		

		hqlUpdate = "update TB_CENSUS_FOREIGN_COUNTRY set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id";
		int fcapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();

		
		
		hqlUpdate = "update TB_CENSUS_QUALIFICATION set status=:status,modify_by=:modify_by,modify_on=:modify_on  where cen_id=:id";
		int qualiapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modify_by", username).setTimestamp("modify_on", new Date())
				.setInteger("id", id).executeUpdate();
		

		Census_madicalCatController c_mad=new Census_madicalCatController();
		List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = c_mad.getUpdatedmadicalData(id,census_parent.getComm_id());	

		hqlUpdate = "update TB_CENSUS_MEDICAL_CATEGORY set status=:status,modify_by=:modify_by,modify_on=:modify_on  where cen_id=:id";
		int medapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modify_by", username).setTimestamp("modify_on", new Date())
				.setInteger("id", id).executeUpdate();
		

		String sShape="S ";
		String hShape="H ";
		String aShape="A ";
		String pShape="P ";
		String eShape="E ";
		
		String cCope="C ";
		String oCope="O ";
		String pCope="P ";
		String eCope="E ";

		if(MedDetails.size() > 0) {
			
		int lmc=0;
		Date med_auth_date=MedDetails.get(0).getDate_of_authority();

		for(int i=0;i<MedDetails.size();i++) {			
			int newLmc=MedDetails.get(i).getShape_status();
			if(newLmc>lmc) {
				lmc=newLmc;
			}	

			String shape=MedDetails.get(i).getShape();
			if(shape.equals("S")) {
				 if(!sShape.equals("S "))
					 sShape+=",";						 
				 sShape+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("H"))
			{
				 if(!hShape.equals("H "))
					 hShape+=",";						 
				 hShape+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("A"))
			{
				 if(!aShape.equals("A "))
					 aShape+=",";						 
				 aShape+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("P"))
			{
				 if(!pShape.equals("P "))
					 pShape+=",";						 
				 pShape+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("E"))
			{
				 if(!eShape.equals("E "))
					 eShape+=",";						 
				 eShape+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("C_C"))
			{
				 if(!cCope.equals("C "))
					 cCope+=",";						 
				 cCope+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("C_O"))
			{
				 if(!oCope.equals("O "))
					 oCope+=",";						 
				 oCope+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("C_P"))
			{
				 if(!pCope.equals("P "))
					 pCope+=",";						 
				 pCope+=MedDetails.get(i).getShape_value();
			}
			else if(shape.equals("C_E"))
			{
				 if(!eCope.equals("E "))
					 eCope+=",";						 
				 eCope+=MedDetails.get(i).getShape_value();
			}
		}
		

	String	Fshape=sShape+";"+hShape+";"+aShape+";"+pShape+";"+eShape;
	String	Fcope=cCope+";"+oCope+";"+pCope+";"+eCope;

	TB_MEDICAL_CATEGORY_HISTORY Mobj=new TB_MEDICAL_CATEGORY_HISTORY();

	Mobj.setComm_id(census_parent.getComm_id());
	Mobj.setShape(Fshape);
	Mobj.setCope(Fcope);
	Mobj.setStatus(1);
	Mobj.setDate_of_authority(med_auth_date);		
	if(lmc==1) {
		Mobj.setMed_classification_lmc("FIT");
	}
	else if(lmc==2) {
		Mobj.setMed_classification_lmc("PERMANENT");
	}
	else if(lmc==3) {
		Mobj.setMed_classification_lmc("TEMPORARY");
	}
	String msg =  c_mad.save_MedicalHistory(Mobj);
		}
		

		hqlUpdate = "update TB_CENSUS_LANGUAGE set status=:status,modify_by=:modify_by,modify_on=:modify_on  where cen_id=:id";
		int langapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modify_by", username).setTimestamp("modify_on", new Date())
				.setInteger("id", id).executeUpdate();

		
		hqlUpdate = "update TB_CENSUS_NCC_EXP set status=:status,modify_by=:modify_by,modify_on=:modify_on  where cen_id=:id";
		int nccapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modify_by", username).setTimestamp("modify_on", new Date())
				.setInteger("id", id).executeUpdate();

		
		hqlUpdate = "update TB_CENSUS_CADET set status=:status,modified_date=:modified_date,modified_by=:modified_by  where census_id=:id";
		int preapp = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();

		
		hqlUpdate = "update TB_CENSUS_SPOUSE_QUALIFICATION set status=:status,modified_by=:modified_by,modified_date=:modified_date  where cen_id=:id";
		int sp_quali = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();

		
		hqlUpdate = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set status=:status,modified_date=:modified_date,modified_by=:modified_by  where cen_id=:id";
		int aller = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username).setTimestamp("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		 
		if (app > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		 }
		tx.commit();
		sessionHQL.close();
		return liststr.get(0);
	}

	@RequestMapping(value = "/Reject_Census", method = RequestMethod.POST)

	public @ResponseBody String Reject_Census(HttpServletRequest request, HttpSession sessionA, ModelMap model) {

		
		
		int id=Integer.parseInt(request.getParameter("census_id"));

		String reject_remarks=request.getParameter("reject_remarks");

		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String username = sessionA.getAttribute("username").toString();

		String hqlUpdate = "update TB_CENSUS_DETAIL_Parent set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks  where id=:id";

	

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)

				.setString("modified_by", username).setTimestamp("modified_date", new Date()).setInteger("id", id).setString("reject_remarks", reject_remarks)

				.executeUpdate();

		if (app > 0) {

			liststr.add("Data Rejected Successfully.");

		} else {

			liststr.add("Data Not Rejected.");

		}

		model.put("msg", liststr.get(0));

		tx.commit();

		sessionHQL.close();

		return  liststr.get(0);

	}
	
	
	/// bisag v2 230822  (converted to Datatable)
	@RequestMapping(value = "/GetSearch_censusCount", method = RequestMethod.POST)
		public @ResponseBody long GetSearch_censusCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String msg,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String cr_by,String cr_date,String IsMns) throws SQLException {
			
			 String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
			return cen.GetSearch_censusCount(Search, orderColunm, orderType, sessionUserId, unit_sus_no, unit_name,personnel_no,
					 rank, status,cr_by,cr_date,roleType,Boolean.parseBoolean(IsMns));
		}
		

		
		@RequestMapping(value = "/GetSearch_censusdata", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> GetSearch_censusdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String cr_by,String cr_date,String IsMns) throws SQLException {
			 String roleType = sessionUserId.getAttribute("roleType").toString();
			
			return cen.GetSearch_censusdata(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_sus_no, unit_name,personnel_no,
					 rank, status,cr_by,cr_date,roleType,Boolean.parseBoolean(IsMns));
		}

		
//		----- pranay 07.06
		
		@RequestMapping(value = "/admin/modificationAfterApproval_CensusDetails", method = RequestMethod.POST)

		public ModelAndView modificationAfterApproval_CensusDetails(ModelMap Mmap, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "personnel_no_modification", required = false) String personnel_no,
				@RequestParam(value = "personnel_no4", required = false) String personnel_no_return,
				@RequestParam(value = "status4", required = false) String status_return,
				@RequestParam(value = "rank4", required = false) String rank_return,
		       @RequestParam(value = "unit_name4", required = false) String unit_name_return,
		       @RequestParam(value = "unit_sus_no4", required = false) String unit_sus_no_return) {

			Mmap.put("personnel_no", personnel_no);
			Mmap.put("status_return", status_return);
			Mmap.put("rank_return", rank_return);
			Mmap.put("unit_name_return", unit_name_return);
			Mmap.put("personnel_no_return", personnel_no_return);
			Mmap.put("unit_sus_no_return", unit_sus_no_return);

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_censusUrl", roleid);

			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			Mmap.put("getReligionList", p_comm.getReligionList());
			Mmap.put("getMarital_statusList", p_comm.getMarital_statusList());
			Mmap.put("getBloodList", p_comm.getBloodList());
			Mmap.put("getRelationList", p_comm.getRelationList());
			Mmap.put("getPreCadetStatusList", p_comm.getPreCadetStatusList());
			Mmap.put("getNCCTypeList", p_comm.getNCCTypeList());
			Mmap.put("getLanguageSTDList", p_comm.getLanguageSTDList());
			Mmap.put("getLanguagePROOFList", p_comm.getLanguagePROOFList());
			Mmap.put("getForiegnLangugeList", p_comm.getForiegnLangugeList());
			Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));
			Mmap.put("getMedStateName", p_comm.getMedStateName("", session));
			Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));
			Mmap.put("getNationalityList", p_comm.getNationalityList());
			Mmap.put("getMedCatList", p_comm.getMedCatList());
			Mmap.put("getQualificationTypeList", p_comm.getQualificationTypeList());
			Mmap.put("getLanguageList", p_comm.getLanguageList());
			Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
			Mmap.put("getSpecializationList", p_comm.getSpecializationList());
			Mmap.put("getHair_ColourList", p_comm.getHair_ColourList());
			Mmap.put("getEye_ColourList", p_comm.getEye_ColourList());
			Mmap.put("getDivclass", p_comm.getDivclass());
			Mmap.put("getSubject", p_comm.getSubject());
			Mmap.put("getCommInstitute", p_comm.getInstitute("1"));
			Mmap.put("getTrainingInstitute", p_comm.getInstitute("2"));
			Mmap.put("getHeight", p_comm.getHeight());
			Mmap.put("getBattalionList", p_comm.getBattalionList());
			Mmap.put("getCompanyList", p_comm.getCompanyList());
			Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());
			Mmap.put("getForeign_country", p_comm.getForeign_country());
			Mmap.put("getForiegnCountryList", psg_com.getforiegnCountry());
			Mmap.put("getIndianLanguageList", p_comm.getIndianLanguageList());
			Mmap.put("getForeignLanguageList", p_comm.getForeignLanguageList());
			Mmap.put("getMedCityName", p_comm.getMedCityName("", session));
			Mmap.put("getProfession", p_comm.getProfession());
			Mmap.put("getShapeStatusList", p_comm.getShapeStatusList());
			Mmap.put("getcCopeList", p_comm.getCopeValueList("C"));
			Mmap.put("getoCopeList", p_comm.getCopeValueList("O"));
			Mmap.put("getpCopeList", p_comm.getCopeValueList("P"));
			Mmap.put("geteCopeList", p_comm.getCopeValueList("E"));
			Mmap.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));
			Mmap.put("getExservicemenList", p_comm.getExservicemenList());
			Mmap.put("msg", msg);

			LocalDate date_without_time = LocalDate.now();
			Mmap.put("date_without_time", date_without_time);
			return new ModelAndView("modifyCensusAfterApprovalTiles");
		}
		
		public Boolean checkHistoryStatus(BigInteger comm_id) throws ParseException{
			
			List<TB_CHANGE_RELIGION> ChangeOfReligion = getChangeReligion(comm_id);
			List<TB_CHANGE_NAME> ChangeOfName = getChangeName(comm_id);

			 if(ChangeOfReligion.size()>0) {
				 return true;
			 }

		    if(ChangeOfName.size()>0) {
				 return true;
			 }
		    
		    return false;
		}
		
		
		

		@RequestMapping(value = "/admin/getChangeReligion", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_RELIGION> getChangeReligion(BigInteger comm_id) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_RELIGION where status = '1' and comm_id=:comm_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_RELIGION> list = (List<TB_CHANGE_RELIGION>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		@RequestMapping(value = "/admin/getChangeName", method = RequestMethod.POST)
		public @ResponseBody List<TB_CHANGE_NAME> getChangeName(BigInteger comm_id) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_CHANGE_NAME where status = '1' and comm_id=:comm_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
//		-----end pranay 07.06
}

