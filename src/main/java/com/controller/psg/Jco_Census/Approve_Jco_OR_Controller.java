package com.controller.psg.Jco_Census;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.psg.Jco_Update_JcoData.Census_madicalCat_Jco_Controller;
//import com.controller.miso.CommanController;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Census.Search_JCOsDao;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT_HISTORY;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Jco_Transaction.TB_POSTING_IN_OUT_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_AADHARNO_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BIRTH_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_GENDER_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_HEIGHT_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_PAN_NO_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_ARMY_NO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_CLASS_PAY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_PAY_GROUP_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_TRADE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_RANK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_RELIGION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_INTER_ARM_TRANSFER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_HISTORY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Approve_Jco_OR_Controller {

	Psg_CommonController p_comm = new Psg_CommonController();
	//CommanController all = new CommanController();
	Census_madicalCat_Jco_Controller c_mad = new Census_madicalCat_Jco_Controller();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Search_JCOsDao jco;
	@Autowired
	private Search_PostOutDao pod;

	@RequestMapping(value = "/GetJCO_ORData", method = RequestMethod.POST)
	public @ResponseBody ModelAndView GetJCO_ORData(@ModelAttribute("idj") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model, Authentication authentication) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		ArrayList<ArrayList<String>> list = jco.GetAllJCO_OR_Details(id);
		model.put("list", list);
		
		model.put("getShapeStatusList", p_comm.getShapeStatusList());
		model.put("getcCopeList", p_comm.getCopeValueList("C"));
		model.put("getoCopeList", p_comm.getCopeValueList("O"));
		model.put("getpCopeList", p_comm.getCopeValueList("P"));
		model.put("geteCopeList", p_comm.getCopeValueList("E"));
		model.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));

		tx.commit();
		return new ModelAndView("Approve_JCOs_ORTiles");
	}

	@RequestMapping(value = "/Approve_JCOs", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_JCOs(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "status", required = false) int status,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			Authentication authentication) {
		 String roleid = sessionA.getAttribute("roleid").toString();
		    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
			if (val == false) {
			return new ModelAndView("AccessTiles");
		    }

			if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		List<String> liststr = new ArrayList<String>();

		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = sessionHQL1.beginTransaction();

		TB_CENSUS_JCO_OR_PARENT BAN = (TB_CENSUS_JCO_OR_PARENT) sessionHQL.get(TB_CENSUS_JCO_OR_PARENT.class, id);
		TB_CENSUS_JCO_OR_SIBLINGS SB = (TB_CENSUS_JCO_OR_SIBLINGS) sessionHQL.get(TB_CENSUS_JCO_OR_SIBLINGS.class, id);

		TB_CHANGE_NAME_JCO med = new TB_CHANGE_NAME_JCO();
		med.setFirst_name(BAN.getFirst_name());
		med.setMiddle_name(BAN.getMiddle_name());
		med.setLast_name(BAN.getLast_name());
		med.setJco_id(BAN.getId());
		med.setStatus(1);
		med.setInitiated_from("c");
		med.setCreated_by(BAN.getCreated_by());
		med.setCreated_date(BAN.getCreated_date());
		med.setModified_by(username);
		med.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO rnk = new TB_PSG_UPDATE_CENSUS_CLASS_OF_RANK_JCO();
		rnk.setRank_intake(BAN.getRank_intake());
		rnk.setRank(BAN.getRank());
		rnk.setJco_id(BAN.getId());
		rnk.setStatus(1);
		rnk.setCreated_by(BAN.getCreated_by());
		rnk.setCreated_date(BAN.getCreated_date());
		rnk.setModified_by(username);
		rnk.setModified_date(new Date());

		TB_CHANGE_OF_RANK_JCO c_rank = new TB_CHANGE_OF_RANK_JCO();
		c_rank.setRank(BAN.getRank());
		c_rank.setJco_id(BAN.getId());
		c_rank.setDate_of_rank(BAN.getDate_of_rank());
		c_rank.setStatus(1);
		c_rank.setInitiated_from("c");
		if(BAN.getDate_of_attestation() != null)
		{
			c_rank.setDate_of_attestation(BAN.getDate_of_attestation());
		}
		c_rank.setCreated_by(BAN.getCreated_by());
		c_rank.setCreated_date(BAN.getCreated_date());
		c_rank.setModified_by(username);
		c_rank.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_GENDER_JCO gen = new TB_PSG_UPDATE_CENSUS_GENDER_JCO();
		gen.setGender(BAN.getGender());
		gen.setJco_id(BAN.getId());
		gen.setStatus(1);
		gen.setCreated_by(BAN.getCreated_by());
		gen.setCreated_date(BAN.getCreated_date());
		gen.setModified_by(username);
		gen.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_BIRTH_JCO dob = new TB_PSG_UPDATE_CENSUS_BIRTH_JCO();
		dob.setDate_of_birth(BAN.getDate_of_birth());
		dob.setJco_id(BAN.getId());
		dob.setStatus(1);
		dob.setCreated_by(BAN.getCreated_by());
		dob.setCreated_date(BAN.getCreated_date());
		dob.setModified_by(username);
		dob.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO add_dob = new TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO();
		add_dob.setJco_id(BAN.getId());
		add_dob.setPlace_of_birth(BAN.getPlace_of_birth());
		add_dob.setCountry_of_birth(BAN.getCountry_of_birth());
		add_dob.setCountry_other(BAN.getCountry_other());
		add_dob.setState_of_birth(BAN.getState_of_birth());
		add_dob.setState_other(BAN.getState_other());
		add_dob.setDistrict_of_birth(BAN.getDistrict_of_birth());
		add_dob.setDistrict_other(BAN.getDistrict_other());
		add_dob.setStatus(1);
		add_dob.setCreated_by(BAN.getCreated_by());
		add_dob.setCreated_date(BAN.getCreated_date());
		add_dob.setModified_by(username);
		add_dob.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO nation = new TB_PSG_UPDATE_CENSUS_NATIONALITY_JCO();
		nation.setNationality(BAN.getNationality());
		nation.setNationality_other(BAN.getNationality_other());
		nation.setJco_id(BAN.getId());
		nation.setStatus(1);
		nation.setCreated_by(BAN.getCreated_by());
		nation.setCreated_date(BAN.getCreated_date());
		nation.setModified_by(username);
		nation.setModified_date(new Date());

		TB_CHANGE_RELIGION_JCO rel = new TB_CHANGE_RELIGION_JCO();
		rel.setReligion(BAN.getReligion());
		rel.setOther(BAN.getReligion_other());
		rel.setJco_id(BAN.getId());
		rel.setStatus(1);
		rel.setCreated_by(BAN.getCreated_by());
		rel.setOther(BAN.getReligion_other());
		rel.setCreated_date(BAN.getCreated_date());
		rel.setModified_by(username);
		rel.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO mt = new TB_PSG_UPDATE_CENSUS_MOTHER_TOUNGE_JCO();
		mt.setMother_tounge(BAN.getMother_tongue());
		mt.setMother_tongue_other(BAN.getMother_tongue_other());
		mt.setJco_id(BAN.getId());
		mt.setStatus(1);
		mt.setCreated_by(BAN.getCreated_by());
		mt.setCreated_date(BAN.getCreated_date());
		mt.setModified_by(username);
		mt.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO bg = new TB_PSG_UPDATE_CENSUS_BLOODGROUP_JCO();
		bg.setBlood_group(BAN.getBlood_group());
		bg.setJco_id(BAN.getId());
		bg.setStatus(1);
		bg.setCreated_by(BAN.getCreated_by());
		bg.setCreated_date(BAN.getCreated_date());
		bg.setModified_by(username);
		bg.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_HEIGHT_JCO h = new TB_PSG_UPDATE_CENSUS_HEIGHT_JCO();
		h.setHeight(BAN.getHeight());
		h.setJco_id(BAN.getId());
		h.setStatus(1);
		h.setCreated_by(BAN.getCreated_by());
		h.setCreated_date(BAN.getCreated_date());
		h.setModified_by(username);
		h.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_AADHARNO_JCO adhar = new TB_PSG_UPDATE_CENSUS_AADHARNO_JCO();
		adhar.setAadhar_no(BAN.getAadhar_no());
		adhar.setJco_id(BAN.getId());
		adhar.setStatus(1);
		adhar.setCreated_by(BAN.getCreated_by());
		adhar.setCreated_date(BAN.getCreated_date());
		adhar.setModified_by(username);
		adhar.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_PAN_NO_JCO p = new TB_PSG_UPDATE_CENSUS_PAN_NO_JCO();
		p.setPan_no(BAN.getPan_no());
		p.setJco_id(BAN.getId());
		p.setStatus(1);
		p.setCreated_by(BAN.getCreated_by());
		p.setCreated_date(BAN.getCreated_date());
		p.setModified_by(username);
		p.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO en = new TB_PSG_UPDATE_CENSUS_CLASS_OF_ENROL_JCO();
		en.setClass_enroll(BAN.getClass_enroll());
		en.setJco_id(BAN.getId());
		en.setStatus(1);
		en.setCreated_by(BAN.getCreated_by());
		en.setCreated_date(BAN.getCreated_date());
		en.setModified_by(username);
		en.setModified_date(new Date());
		
		TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO dt_en = new TB_PSG_UPDATE_CENSUS_ENROLMENT_JCO();
		dt_en.setDate_of_enrollment(BAN.getEnroll_dt());
		dt_en.setJco_id(BAN.getId());
		dt_en.setStatus(1);
		dt_en.setCreated_by(BAN.getCreated_by());
		dt_en.setCreated_date(BAN.getCreated_date());
		dt_en.setModified_by(username);
		dt_en.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO att = new TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO();
		att.setDate_of_attestation(BAN.getDate_of_attestation());
		att.setJco_id(BAN.getId());
		att.setStatus(1);
		att.setCreated_by(BAN.getCreated_by());
		att.setCreated_date(BAN.getCreated_date());
		att.setModified_by(username);
		att.setModified_date(new Date());

		TB_CHANGE_IN_DATE_OF_SENIORITY_JCO dt_sen = new TB_CHANGE_IN_DATE_OF_SENIORITY_JCO();
		dt_sen.setDate_of_seniority(BAN.getDate_of_seniority());
		dt_sen.setInitiated_from("c");
		dt_sen.setJco_id(BAN.getId());
		dt_sen.setStatus(1);
		dt_sen.setCreated_by(BAN.getCreated_by());
		dt_sen.setCreated_date(BAN.getCreated_date());
		dt_sen.setModified_by(username);
		dt_sen.setModified_date(new Date());

		TB_CHANGE_IN_CLASS_PAY_JCO pay_class = new TB_CHANGE_IN_CLASS_PAY_JCO();
		pay_class.setCla_class(BAN.getClass_pay());
		pay_class.setDate_of_class(BAN.getEnroll_dt());
		pay_class.setInitiated_from("c");
		pay_class.setStatus(1);
		pay_class.setJco_id(BAN.getId());
		pay_class.setCreated_by(BAN.getCreated_by());
		pay_class.setCreated_date(BAN.getCreated_date());
		pay_class.setModified_by(username);
		pay_class.setModified_date(new Date());

		TB_CHANGE_IN_PAY_GROUP_JCO pay_group = new TB_CHANGE_IN_PAY_GROUP_JCO();
		pay_group.setGp_group(BAN.getPay_group());
		pay_group.setDate_of_group(BAN.getEnroll_dt());
		pay_group.setInitiated_from("c");
		pay_group.setStatus(1);
		pay_group.setJco_id(BAN.getId());
		pay_group.setCreated_by(BAN.getCreated_by());
		pay_group.setCreated_date(BAN.getCreated_date());
		pay_group.setModified_by(username);
		pay_group.setModified_date(new Date());

		TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO family = new TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO();
		family.setFather_name(BAN.getFather_name());
		family.setFather_dob(BAN.getFather_dob());
		family.setFather_place_birth(BAN.getFather_place_birth());
		family.setFather_personal_no(BAN.getFather_personal_no());
		family.setFather_profession(BAN.getFather_profession());
		family.setFather_service(BAN.getFather_service());
		family.setFather_other(BAN.getFather_other());
		family.setFather_proffother(BAN.getFather_proffother());
		family.setMother_name(BAN.getMother_name());
		family.setMother_dob(BAN.getMother_dob());
		family.setMother_place_birth(BAN.getMother_place_birth());
		family.setMother_personal_no(BAN.getMother_personal_no());
		family.setMother_profession(BAN.getMother_profession());
		family.setMother_service(BAN.getMother_service());
		family.setMother_other(BAN.getMother_other());
		family.setMother_proffother(BAN.getMother_proffother());
		family.setStatus(1);
		family.setJco_id(BAN.getId());
		family.setCreated_by(BAN.getCreated_by());
		family.setCreated_date(BAN.getCreated_date());
		family.setModified_by(username);
		family.setModified_date(new Date());

		TB_INTER_ARM_TRANSFER_JCO arm = new TB_INTER_ARM_TRANSFER_JCO();
		arm.setParent_arm_service(BAN.getArm_service());
		arm.setInitiated_from("c");
		arm.setRecord_office_sus(BAN.getRecord_office_sus());
		arm.setRecord_office_unit(BAN.getRecord_office());
		arm.setStatus(1);
		arm.setJco_id(BAN.getId());
		arm.setCreated_by(BAN.getCreated_by());
		arm.setCreated_date(BAN.getCreated_date());
		arm.setModified_by(username);
		arm.setModified_date(new Date());

		TB_CHANGE_IN_TRADE_JCO trade = new TB_CHANGE_IN_TRADE_JCO();
		trade.setTrade(BAN.getTrade());
		trade.setStatus(1);
		trade.setInitiated_from("c");
		trade.setJco_id(BAN.getId());
		trade.setCreated_by(BAN.getCreated_by());
		trade.setCreated_date(BAN.getCreated_date());
		trade.setModified_by(username);
		trade.setModified_date(new Date());

		TB_CENSUS_JCO_OR_PARENT_HISTORY his = new TB_CENSUS_JCO_OR_PARENT_HISTORY();
		his.setFull_name(BAN.getFull_name());
		his.setAadhar_no(BAN.getAadhar_no());
		his.setArm_service(BAN.getArm_service());
		his.setArmy_no(BAN.getArmy_no());
		his.setBlood_group(BAN.getBlood_group());
		his.setCategory(BAN.getCategory());
		his.setClass_enroll(BAN.getClass_enroll());
		his.setClass_pay(BAN.getClass_pay());
		his.setCountry_of_birth(BAN.getCountry_of_birth());
		his.setDate_of_attestation(BAN.getDate_of_attestation());
		his.setDate_of_birth(BAN.getDate_of_birth());
		his.setDate_of_seniority(BAN.getDate_of_seniority());
		his.setDate_of_tos(BAN.getDate_of_tos());
		his.setDistrict_of_birth(BAN.getDistrict_of_birth());
		his.setEnroll_dt(BAN.getEnroll_dt());
		his.setFather_dob(BAN.getFather_dob());
		his.setFather_name(BAN.getFather_name());
		his.setFather_personal_no(BAN.getFather_personal_no());
		his.setFather_place_birth(BAN.getFather_place_birth());
		his.setFather_profession(BAN.getFather_profession());
		his.setFather_service(BAN.getFather_service());
		his.setFirst_name(BAN.getFirst_name());
		his.setGender(BAN.getGender());
		his.setHeight(BAN.getHeight());
		his.setJco_his_id(BAN.getId());
		his.setLast_name(BAN.getLast_name());
		his.setMarital_status(BAN.getMarital_status());
		his.setMiddle_name(BAN.getMiddle_name());
		his.setMother_dob(BAN.getMother_dob());
		his.setMother_name(BAN.getMother_name());
		his.setMother_personal_no(BAN.getMother_personal_no());
		his.setMother_place_birth(BAN.getMother_place_birth());
		his.setMother_profession(BAN.getMother_profession());
		his.setMother_service(BAN.getMother_service());
		his.setMother_tongue(BAN.getMother_tongue());
		his.setNationality(BAN.getNationality());
		his.setPan_no(BAN.getPan_no());
		his.setPay_group(BAN.getPay_group());
		his.setPlace_of_birth(BAN.getPlace_of_birth());
		his.setRank(BAN.getRank());
		his.setRecord_office(BAN.getRecord_office());
		his.setRecord_office_sus(BAN.getRecord_office_sus());
		his.setRegiment(BAN.getRegiment());
		his.setReligion(BAN.getReligion());
		his.setState_of_birth(BAN.getState_of_birth());
		his.setTrade(BAN.getTrade());
		his.setUnit_posted_to(BAN.getUnit_posted_to());
		his.setUnit_sus_no(BAN.getUnit_sus_no());
		his.setStatus(1);
		his.setCreated_by(BAN.getCreated_by());
		his.setCreated_date(BAN.getCreated_date());
		his.setModified_by(username);
		his.setModified_date(new Date());

		TB_POSTING_IN_OUT_JCO po = new TB_POSTING_IN_OUT_JCO();
		ArrayList<ArrayList<String>> orbatlist = jco.getcommand_JCO(BAN.getUnit_sus_no());
		ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(BAN.getUnit_sus_no());
		po.setTo_sus_no(BAN.getUnit_sus_no());
		po.setDt_of_sos(BAN.getDate_of_tos());
		po.setDt_of_tos(BAN.getDate_of_tos());
		po.setCreated_by(BAN.getCreated_by());
		po.setCreated_date(BAN.getCreated_date());
		po.setInitiated_from("c");
		po.setModified_by(username);
		po.setModified_date(new Date());
		po.setJco_id(BAN.getId());
		po.setStatus(1);
		
		if (orbatlist.size() > 0) {
			po.setCmd_sus(orbatlist.get(0).get(1));
			po.setCorps_sus(orbatlist.get(0).get(2));
			po.setDiv_sus(orbatlist.get(0).get(3));
			po.setBde_sus(orbatlist.get(0).get(4));
			po.setLocation(Location_Trnlist.get(0).get(0));
			po.setTrn_type(Location_Trnlist.get(0).get(1));
			po.setRank(BAN.getRank());

			sessionHQL.save(po);
			medicalDtlApproveUpdate(id,username);

			sessionHQL.save(med);
			sessionHQL.save(rnk);
			sessionHQL.save(c_rank);
			
			int rank_id = c_rank.getId();
			TB_CHANGE_ARMY_NO  personal_tb = new TB_CHANGE_ARMY_NO();
			personal_tb.setRank_id(rank_id);
			personal_tb.setArmy_no(BAN.getArmy_no());
			personal_tb.setStatus(1);
			personal_tb.setInitiated_from("c");
			personal_tb.setJco_id(BAN.getId());
			
			sessionHQL.save(personal_tb);
			sessionHQL.save(gen);
			sessionHQL.save(dob);
			sessionHQL.save(add_dob);
			sessionHQL.save(family);
			sessionHQL.save(nation);
			sessionHQL.save(rel);
			sessionHQL.save(mt);
			sessionHQL.save(h);
			sessionHQL.save(adhar);
			sessionHQL.save(p);
			sessionHQL.save(en);
			sessionHQL.save(dt_en);
			sessionHQL.save(att);
			sessionHQL.save(dt_sen);
			sessionHQL.save(pay_class);
			sessionHQL.save(pay_group);
			sessionHQL.save(arm);
			sessionHQL.save(trade);
			sessionHQL.save(his);
			sessionHQL.save(bg);
			sessionHQL.save(po);
			sessionHQL.flush();
			sessionHQL.clear();
			
		} else {
			model.put("msg", "Orbat Details are not Exist.");
			return new ModelAndView("search_JCOs_ORTiles");
		}

		String hqlUpdate = "update TB_CENSUS_JCO_OR_PARENT set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

		

		String hqlUpdate1 = "update TB_CENSUS_JCO_OR_SIBLINGS set status=:status,modified_by=:modified_by,modified_date=:modified_date  where jco_id=:jco_id";

		int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
		
		if(BAN.getMarital_status() != 10)
		{
			String marital_q = "update TB_CENSUS_FAMILY_MARRIED_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date, initiated_from=:initiated_from  where jco_id=:jco_id";

			int app2 = sessionHQL.createQuery(marital_q).setInteger("status", 1).setString("modified_by", username).setString("initiated_from", "c")
					.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
		}
	
		if(BAN.getMarital_status() != 10)
		{
			String Quali_Update = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set status=:status, initiated_from=:initiated_from, modified_by=:modified_by,modified_date=:modified_date  where jco_id=:jco_id";
				
			int app3 = sessionHQL.createQuery(Quali_Update).setInteger("status", 1).setString("modified_by", username).setString("initiated_from", "c")
				.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
	
		}
		
		tx.commit();
		sessionHQL.close();

		if (app > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
	model.put("msg", liststr.get(0));
		String roleType = sessionA.getAttribute("roleType").toString();

	//	model.put("status1", status);
		return new ModelAndView("redirect:search_jco_url");
	}

	@RequestMapping(value = "/Reject_JCOs", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_JCOs(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "reject_remarks_jc", required = false) String reject_remarks,Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		String roleid = sessionA.getAttribute("roleid").toString();
	    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
		if (val == false) {
		return new ModelAndView("AccessTiles");
	    }

		if (request.getHeader("Referer") == null) {
		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = sessionHQL1.beginTransaction();

		String username = sessionA.getAttribute("username").toString();

		String hqlUpdate = "update TB_CENSUS_JCO_OR_PARENT set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks  where id=:id";

		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("modified_by", username).setString("reject_remarks", reject_remarks)
				.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

		String hqlUpdate1 = "update TB_CENSUS_JCO_OR_SIBLINGS set status=:status,modified_by=:modified_by,modified_date=:modified_date  where jco_id=:jco_id";

		int app1 = sessionHQL1.createQuery(hqlUpdate1).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
		
		String hqlUpdate2 = "update TB_CENSUS_FAMILY_MARRIED_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date  where jco_id=:jco_id";

		int app2 = sessionHQL1.createQuery(hqlUpdate2).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
		
		TB_CENSUS_JCO_OR_PARENT BAN = (TB_CENSUS_JCO_OR_PARENT) sessionHQL.get(TB_CENSUS_JCO_OR_PARENT.class, id);
		
		if(BAN.getMarital_status() != 10)
		{
			String Quali_Update = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date  where jco_id=:jco_id";
				
			int app3 = sessionHQL.createQuery(Quali_Update).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("jco_id", id).executeUpdate();
	
		}
		
		tx1.commit();
		sessionHQL1.close();

		if (app > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}

		String roleType = sessionA.getAttribute("roleType").toString();

		model.put("msg", liststr.get(0));
		model.put("status", 0);

		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:search_jco_url");
	}

	@RequestMapping(value = "/getSiblingsValue", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getSiblingsValue(int jco_id) {
		List<ArrayList<String>> list = jco.getSiblings_Value(jco_id);
		return list;
	}
	@RequestMapping(value = "/getSpouseValue", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getSpouseValue(int jco_id) {
		List<ArrayList<String>> list = jco.getSpouse_Value(jco_id);
		return list;
	}
	@RequestMapping(value = "/getDIV_WIDValue", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getDIV_WIDValue(int jco_id) {
		List<ArrayList<String>> list = jco.getDIV_WID_Value(jco_id);
		return list;
	}
	
	@RequestMapping(value = "/admin/getSpouse_EducationData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> getSpouse_EducationData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {	
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		return jco.getQualificationDataSpouse(jco_id,status);
	}
	
	public void medicalDtlApproveUpdate(int id,String username ) {
		List<TB_MEDICAL_CATEGORY_JCO> MedDetails = c_mad.getUpdatedmadicalData(id);
		TB_MEDICAL_CATEGORY_JCO med1 = new TB_MEDICAL_CATEGORY_JCO();
			
		if(MedDetails.size()!=0 ) {
			
		med1.setJco_id(id);

		med1.setModify_on(new Date());
		c_mad.Update_medicalCategory(med1, username);
		
		String sShape="S ";
		String hShape="H ";
		String aShape="A ";
		String pShape="P ";
		String eShape="E ";
		
		
		String cCope="C ";
		String oCope="O ";
		String pCope="P ";
		String eCope="E ";
		
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
	TB_MEDICAL_CATEGORY_HISTORY_JCO Mobj=new TB_MEDICAL_CATEGORY_HISTORY_JCO();
	Mobj.setJco_id(id);
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
    c_mad.save_MedicalJCOHistory(Mobj);
	}
	}
		
	
}
