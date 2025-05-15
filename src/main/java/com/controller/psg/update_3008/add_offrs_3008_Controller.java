package com.controller.psg.update_3008;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_CADET;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COURSE;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_GENDER;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_UNIT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class add_offrs_3008_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Report_3008DAO report_3008DAO;

	@Autowired
	private Search_PostOutDao pod;
	
	Psg_CommonController p_comm = new Psg_CommonController();	
	ValidationController valid = new ValidationController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;

	@RequestMapping(value = "/admin/add_offrs_3008", method = RequestMethod.GET)
	public ModelAndView add_offrs_3008(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("add_offrs_3008", session.getAttribute("roleid").toString());
	//	if (val == false) {
	//		return new ModelAndView("AccessTiles");
	//	}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("msg", msg);
		Mmap.put("getCourseNameList", p_comm.getCourseNameList());
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());		
		Mmap.put("getTypeofRankListc", p_comm.getTypeofRankList());
		Mmap.put("getPersonalType", p_comm.getPersonalType());
		Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("getTypeofAppointementList", p_comm.getTypeofAppointementList());	
		Mmap.put("getstatusList", p_comm.getstatusList());
		Mmap.put("getPostINOutstatusList", p_comm.getPostINOutstatusList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		//Mmap.put("getUnitNameList_Active_or_Inactive", allt.getUnitNameList_Active_or_Inactive(roleSusNo, sessionUserId));
	//	Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("add_offrs_3008_Tiles");
	}



	@RequestMapping(value = "/add_comm_offrs_3008_action", method = RequestMethod.POST)
	public ModelAndView add_comm_offrs_3008_action(
			@ModelAttribute("add_comm_offrs_3008_cmd") TB_TRANS_PROPOSED_COMM_LETTER BAN,
			TB_TRANS_PROPOSED_COMM_LETTER_HISTORY his, BindingResult result, HttpServletRequest request, ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,
			HttpSession session) throws ParseException {
		
		Boolean val = roledao.ScreenRedirect("add_offrs_3008", session.getAttribute("roleid").toString());
		//if (val == false) {
		//	return new ModelAndView("AccessTiles");
		//}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Date date = new Date();
		Date auth_dt = null;
		Date comm_dt = null;
		Date seniority_dt = null;
		Date rank_dt = null;
		Date birth_dt = null;
		Date Tos_dt = null;
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		BigInteger b = BigInteger.ZERO;
		//b = BAN.getId();
		//b = BAN.getId().intValue();
		int id = BAN.getId().intValue() > 0 ? BAN.getId().intValue() : 0;
		
		

		String authority_date = request.getParameter("date_of_authority");
		String commission_date = request.getParameter("date_of_commission");
		String parent_sus_no = request.getParameter("parent_sus_no");
		String parent_unit = request.getParameter("parent_unit");

		String seniority_date = request.getParameter("date_of_seniority");
		String tos_date = request.getParameter("date_of_tosc");
		String rank_date = request.getParameter("date_of_rank");
		String dt_rank1 =null;
		String dt_rank2 = null;

		String birth_date = request.getParameter("date_of_birth");
		String parent_arm = request.getParameter("parent_arm");
		String regiment = request.getParameter("regiment");
		String parent_armLb = request.getParameter("parent_armLb");
		String rank = request.getParameter("rankc");
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no2");
		String persnl_no3 = request.getParameter("persnl_no3");

		String persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
        String dt_commission1=null;
        String dt_commission2=null;       
     
        

		if (authority_date!=null && !authority_date.trim().equals("") && !authority_date.equals("DD/MM/YYYY")) {
			auth_dt = format.parse(authority_date);
		}

		if (commission_date!=null && !commission_date.trim().equals("") && !commission_date.equals("DD/MM/YYYY") ) {
			comm_dt = format.parse(commission_date);
			 dt_commission1 = commission_date.substring(6, 10);
			 dt_commission2 = commission_date.substring(3, 5);
		}

		if (seniority_date!=null && !seniority_date.trim().equals("") && !seniority_date.equals("DD/MM/YYYY") ) {
			seniority_dt = format.parse(seniority_date);
		}

		if (tos_date!=null && !tos_date.equals("") && !tos_date.equals("DD/MM/YYYY") ) {
			Tos_dt = format.parse(tos_date);
		}

		if (rank_date!=null && !rank_date.equals("") && !rank_date.equals("DD/MM/YYYY") ) {
			rank_dt = format.parse(rank_date);
			 dt_rank1 = rank_date.substring(6, 10);
			 dt_rank2 = rank_date.substring(3, 5);
		}
		Date date1=null;
		if (birth_date!=null && !birth_date.equals("") && !birth_date.equals("DD/MM/YYYY") ) {
			birth_dt = format.parse(birth_date);
			String is = birth_date + "-01";
			date1 = format.parse(is);
		}

		// date validation

		
		if (request.getParameter("authority") == null || request.getParameter("authority").trim().equals("")) {
			Mmap.put("msg", "Please Enter Authority");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		else {
			BAN.setAuthority(p_comm.StringValidationForName(request.getParameter("authority").toString()));
		}
		
		if (!valid.isValidAuth(request.getParameter("authority"))) {
			Mmap.put("msg", valid.isValidAuthMSG + "Authority");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}

		if (!valid.isvalidLength(request.getParameter("authority"), valid.authorityMax, valid.authorityMin)) {
			Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}

		if (!valid.isValidDate(authority_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Authority");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (authority_date == null || authority_date.trim().equals("") || authority_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Select Authority Date");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			auth_dt = format.parse(authority_date);
		}
		if (request.getParameter("persnl_no1") == null || request.getParameter("persnl_no1").equals("-1")
				|| request.getParameter("persnl_no1").equals("0")) {
			Mmap.put("msg", "Please Select Personal No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (request.getParameter("persnl_no2") == null || request.getParameter("persnl_no2").trim().equals("")) {
			Mmap.put("msg", "Please Enter Personal No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (valid.isOnlyNumer(request.getParameter("persnl_no2")) == true) {
			Mmap.put("msg", "Personal No" + valid.isOnlyNumerMSG);
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (request.getParameter("persnl_no2").length() != 5) {
			Mmap.put("msg", "Please Enter Valid Personal No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		String persuffix = p_comm.getPersonnelNuSuffix(persnl_no2);

		if (!persnl_no3.equals(persuffix)) {
			Mmap.put("msg", "Please Enter Valid Personal No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}

		if (persnl_no.length() < 7 || persnl_no.length() > 9) {
			Mmap.put("msg", "Please Enter Valid Personal No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (request.getParameter("cadet_no") == null || request.getParameter("cadet_no").trim().equals("")) {
			Mmap.put("msg", "Please Enter Cadet No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		else {
			BAN.setCadet_no(request.getParameter("cadet_no").trim());
		}
		
		 if (!valid.validateSlash(request.getParameter("cadet_no"))) {
				Mmap.put("msg", valid.validateSlashMSG + "Cadet No");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		 
		if (request.getParameter("batch_no") == null || request.getParameter("batch_no").trim().equals("")) {
			Mmap.put("msg", "Please Enter Course No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (valid.isOnlyNumer(request.getParameter("batch_no")) == true) {
			Mmap.put("msg", "Course No " + valid.isOnlyNumerMSG);
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (request.getParameter("course") == null || request.getParameter("course").equals("0")) {
			Mmap.put("msg", "Please Select Course");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (request.getParameter("namec") == null || request.getParameter("namec").trim().equals("")) {
			Mmap.put("msg", "Please Enter Name");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if(Pattern.matches("^[ A-Za-z]+$", request.getParameter("namec").toString())) {
			
			BAN.setName(p_comm.StringValidationForName(request.getParameter("namec").toString()));
		}
		else {
			Mmap.put("msg", "Name Should Only Contain Alphabet And Only One Space B/W Words");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		 if (!valid.isvalidLength(request.getParameter("namec"), valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Name " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
		
		if (request.getParameter("gender") == null || request.getParameter("gender").equals("0")) {
			Mmap.put("msg", "Please Select Gender");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (request.getParameter("type_of_comm_granted") == null
				|| request.getParameter("type_of_comm_granted").equals("0")) {
			Mmap.put("msg", "Please Select Type of Commission Granted");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (!valid.isValidDate(commission_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Commission ");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (commission_date == null || commission_date.trim().equals("") || commission_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of Commission");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			comm_dt = format.parse(commission_date);
		}
		
		if (!valid.isValidDate(seniority_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Seniority ");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (seniority_date == null || seniority_date.trim().equals("") || seniority_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of Seniority");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			seniority_dt = format.parse(seniority_date);
		}
		
		if (birth_date != null && BAN.getDate_of_seniority() != null) {
			if (date1.compareTo(BAN.getDate_of_seniority()) > 0) {
				Mmap.put("msg", "  Date of Seniority  should not be before dt of Birth ");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
		}
		//26-01-1994
		/*if (BAN.getDate_of_seniority().compareTo(format.parse(commission_date)) < 0) {
			Mmap.put("msg", "Date of Seniority can be greater than Date of Commission or equal to.");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}*/
		
		if (request.getParameter("rankc") == null || request.getParameter("rankc").equals("0")) {
			Mmap.put("msg", "Please Select Rank");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (rank_date == null || rank_date.trim().equals("")) {
			Mmap.put("msg", "Please Select Date of Rank");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			rank_dt = format.parse(rank_date);
		}
		
		
		/*
		 * if (format.parse(seniority_date).compareTo(format.parse(commission_date)) <=
		 * 0) { Mmap.put("msg",
		 * "Date of Seniority can be greater than Date of Commission or equal to.");
		 * return new ModelAndView("redirect:Prop_Comm_letter_new"); }
		 */

		

		/*
		 * if (format.parse(rank_date).compareTo(format.parse(commission_date)) <= 0) {
		 * Mmap.put("msg",
		 * "Date of Rank can be greater than Date of Commission or equal to."); return
		 * new ModelAndView("redirect:Prop_Comm_letter_new"); }
		 */

		/*if ((Integer.parseInt(dt_commission1) != Integer.parseInt(dt_rank1))
				|| (Integer.parseInt(dt_commission2) != Integer.parseInt(dt_rank2))) {
			Mmap.put("msg", " Date of Rank should be equals to Date of Commission ");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}*/
		if (birth_date != null && BAN.getDate_of_rank() != null) {
			if (date1.compareTo(BAN.getDate_of_rank()) > 0) {
				Mmap.put("msg", "Date of Rk  should not be before dt of Birth ");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
		}
		
		if (!valid.isValidDate(birth_date)) {
			Mmap.put("msg", valid.isValidDateMSG + " of Birth ");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		
		if (birth_date == null || birth_date.trim().equals("") || birth_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of Birth");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			birth_dt = format.parse(birth_date);
		}
		if (p_comm.calculate_age(birth_dt, comm_dt) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if (p_comm.calculate_age(birth_dt, new Date()) < 16) {
			Mmap.put("msg", "Please enter age above 16");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}

		if (request.getParameter("unit_sus_no") == null || request.getParameter("unit_sus_no").trim().equals("")) {
			Mmap.put("msg", "Please Enter Unit Sus No");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		  if (!valid.SusNoLength(request.getParameter("unit_sus_no"))) {
				Mmap.put("msg", valid.SusNoMSG);
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
  	  
  	  if (!valid.isOnlyAlphabetNumericSpaceNot(request.getParameter("unit_sus_no"))) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}


   	  String unit_posted_to =request.getParameter("unit_posted_to");
 	  unit_posted_to = unit_posted_to.replace("&#40;", "(");
	  unit_posted_to = unit_posted_to.replace("&#41;", ")");
		if (request.getParameter("unit_posted_to") == null || request.getParameter("unit_posted_to").trim().equals("")) {
			Mmap.put("msg", "Please Enter Unit Posted To");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
//		  if (!valid.isUnit(unit_posted_to)) {
//			  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
//				return new ModelAndView("redirect:Prop_Comm_letter_new");
//			}
  	  
  	     if (!valid.isvalidLength(unit_posted_to, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
    
  	  

   	if (!valid.isValidDate(tos_date)) {
		Mmap.put("msg", valid.isValidDateMSG + " of TOS ");
		return new ModelAndView("redirect:Prop_Comm_letter_new");
	}
		
		if (tos_date == null || tos_date.trim().equals("") || tos_date.equals("DD/MM/YYYY")) {
			Mmap.put("msg", "Please Enter Date of TOS.");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		} else {
			Tos_dt = format.parse(tos_date);
		}

		if (birth_date != null && BAN.getDate_of_tos() != null) {
			if (date1.compareTo(BAN.getDate_of_tos()) > 0) {
				Mmap.put("msg", "  Date of Tos  should not be before dt of Birth ");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
		}

		if (format.parse(tos_date).compareTo(format.parse(commission_date)) < 0) {
			Mmap.put("msg", "Date of TOS can be greater than Date of Commission or equal to.");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if (request.getParameter("parent_arm") == null || request.getParameter("parent_arm").equals("0")) {
			Mmap.put("msg", "Please Enter Parent Arm/Service");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		if( request.getParameter("parent_arm").equals("0700") || request.getParameter("parent_arm").equals("0800")) {
			if(regiment==null || regiment.trim().equals("") || regiment.equals("0")) {
				Mmap.put("msg", "Please Select Regiment");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
		}
		else {
			regiment="0";
		}

		try {
			Boolean d = SLDAO.getPersonnelNoAlreadyExist(persnl_no,BigInteger.ZERO);

			if (id == 0) {
				BAN.setCreated_by(username);
				BAN.setCreated_date(date);
				BAN.setDate_of_authority(auth_dt);
				BAN.setDate_of_commission(comm_dt);
				BAN.setDate_of_seniority(seniority_dt);
				BAN.setDate_of_rank(rank_dt);
				BAN.setDate_of_birth(birth_dt);
				BAN.setPersonnel_no(persnl_no);
				BAN.setDate_of_tos(Tos_dt);
				BAN.setRegiment(regiment);
				BAN.setRank(Integer.parseInt(rank));
				BAN.setStatus(1);
				BAN.setUpdate_comm_status(1);
				BAN.setParent_sus_no(parent_sus_no);
				BAN.setParent_unit(parent_unit);
				Serializable Id = sessionHQL.save(BAN);			
				BigInteger commId = BigInteger.valueOf(((Number) Id).longValue());				
			
				 TB_TRANS_PROPOSED_COMM_LETTER comm;
				 comm = (TB_TRANS_PROPOSED_COMM_LETTER)sessionHQL.get(TB_TRANS_PROPOSED_COMM_LETTER.class, commId); 
				
				 TB_CHANGE_NAME med = new TB_CHANGE_NAME();
				   med.setName(comm.getName());
				   med.setComm_id(comm.getId());
				  
				   med.setAuthority(comm.getAuthority());
				   med.setDate_of_authority(comm.getDate_of_authority());
				   med.setStatus(1);
				   med.setCreated_by(comm.getCreated_by());
	               med.setCreated_date(comm.getCreated_date());
	               med.setModified_by(username);
	               med.setModified_date(new Date());
				 
	               BigInteger i = BigInteger.ZERO;
                 i = comm.getId();
				   TB_CHANGE_OF_RANK rnk = new TB_CHANGE_OF_RANK();
			       rnk.setRank(comm.getRank());
			       rnk.setAuthority(comm.getAuthority());
			       rnk.setDate_of_authority(comm.getDate_of_authority());
			       rnk.setComm_id(i);
			      
			       rnk.setDate_of_rank(comm.getDate_of_rank());
			       rnk.setStatus(1);
			       rnk.setCreated_by(comm.getCreated_by());
			       rnk.setCreated_date(comm.getCreated_date());
			       rnk.setModified_by(username); 
			       rnk.setModified_date(new Date());
                 
				   TB_PSG_CHANGE_OF_COMISSION coc= new TB_PSG_CHANGE_OF_COMISSION();
				   
				 
				   coc.setNew_personal_no(comm.getPersonnel_no());
				   coc.setType_of_commission_granted(comm.getType_of_comm_granted());
				   coc.setDate_of_seniority(comm.getDate_of_seniority());
				   coc.setDate_of_permanent_commission(comm.getDate_of_commission());
				   coc.setComm_id(i);
				   coc.setStatus(1);
				   coc.setAuthority(comm.getAuthority());
				   coc.setDate_of_authority(comm.getDate_of_authority());
				   coc.setCreated_by(comm.getCreated_by());
				   coc.setCreated_date(comm.getCreated_date());
				   coc.setModified_by(username);
				   coc.setModified_date(new Date());
				   
				   TB_INTER_ARM_TRANSFER arm = new TB_INTER_ARM_TRANSFER();
				   arm.setParent_arm_service(comm.getParent_arm());
				   arm.setRegt(comm.getRegiment());
				   arm.setWith_effect_from(comm.getDate_of_commission());
				   arm.setComm_id(comm.getId());
				   arm.setAuthority(comm.getAuthority());
				   arm.setDate_of_authority(comm.getDate_of_authority());
				   arm.setStatus(1);
				   arm.setCreated_by(comm.getCreated_by());
				   arm.setCreated_date(comm.getCreated_date());
				   arm.setModified_by(username);
				   arm.setModified_date(new Date());
				  
				   TB_PSG_CHANGE_OF_SENIORITY sen = new TB_PSG_CHANGE_OF_SENIORITY();
				   sen.setDate_of_seniority(comm.getDate_of_seniority());
				   sen.setStatus(1);
				   sen.setAuthority(comm.getAuthority());
				   sen.setDate_of_authority(comm.getDate_of_authority());
				   sen.setCreated_by(comm.getCreated_by());
				   sen.setCreated_date(comm.getCreated_date());
				   sen.setModified_by(username);
				   sen.setModified_date(new Date());
				   sen.setComm_id(comm.getId());
				   sen.setApplicablity_date(comm.getDate_of_seniority());
				   

				
				//sen.setComm_id(comm_id);  
				   his.setAuthority(comm.getAuthority());
				   his.setDate_of_authority(comm.getDate_of_authority());
				   his.setName(comm.getName());
				   his.setCadet_no(comm.getCadet_no());
				   his.setBatch_no(comm.getBatch_no());
				   his.setCourse(comm.getCourse());
				   his.setGender(comm.getGender());
				   his.setType_of_comm_granted(comm.getType_of_comm_granted());
				   his.setUnit_sus_no(comm.getUnit_sus_no());
				   his.setParent_arm(comm.getParent_arm());
				   his.setRank(comm.getRank());
				   his.setRegiment(comm.getRegiment());
				   his.setDate_of_commission(comm.getDate_of_commission());
				   his.setDate_of_seniority(comm.getDate_of_seniority());
				   his.setDate_of_rank(comm.getDate_of_rank());
				   his.setDate_of_birth(comm.getDate_of_birth());
				   his.setPersonnel_no(comm.getPersonnel_no());
				   his.setComm_his_id(comm.getId());
				   his.setDate_of_tos(comm.getDate_of_tos());
				   his.setStatus(1);
				   his.setCreated_by(comm.getCreated_by());
				   his.setCreated_date(comm.getCreated_date());
				   his.setModified_by(username);
				   his.setModified_date(new Date());
				   
				   
				   
                      // SAVE BIRTH DATE 
				   
				   TB_PSG_UPDATE_COMM_BIRTH dob  = new TB_PSG_UPDATE_COMM_BIRTH();
				   
				   dob.setDate_of_birth(comm.getDate_of_birth());
				   dob.setAuthority(comm.getAuthority());
				   dob.setDate_of_authority(comm.getDate_of_authority());
				   dob.setComm_id(i);			       
			       dob.setStatus(1);
			       dob.setCreated_by(comm.getCreated_by());
			       dob.setCreated_date(comm.getCreated_date());
			       dob.setModified_by(username); 
			       dob.setModified_date(new Date());
			       
			       
	                   // SAVE commisiion 
				   
			       TB_PSG_UPDATE_COMM_COMMISSION comm1  = new TB_PSG_UPDATE_COMM_COMMISSION();
				   
			       comm1.setType_of_comm_granted(BAN.getType_of_comm_granted());
			       comm1.setDate_of_commission(BAN.getDate_of_commission());
			       comm1.setAuthority(BAN.getAuthority());
			       comm1.setDate_of_authority(BAN.getDate_of_authority());
			       comm1.setComm_id(i);			       
			       comm1.setStatus(1);
			       comm1.setCreated_by(BAN.getCreated_by());
			       comm1.setCreated_date(BAN.getCreated_date());
			       comm1.setModified_by(username); 
			       comm1.setModified_date(new Date());
			       
			       
			       
			       TB_PSG_UPDATE_COMM_CADET cadet_no=new TB_PSG_UPDATE_COMM_CADET();			    
			       cadet_no.setCadet_no(comm.getCadet_no());
			       cadet_no.setAuthority(comm.getAuthority());
			       cadet_no.setDate_of_authority(comm.getDate_of_authority());
			       cadet_no.setComm_id(i);			       
				   cadet_no.setStatus(1);
				   cadet_no.setCreated_by(comm.getCreated_by());
				   cadet_no.setCreated_date(comm.getCreated_date());
				   cadet_no.setModified_by(username); 
				   cadet_no.setModified_date(new Date());
				   
				   TB_PSG_UPDATE_COMM_GENDER gender=new TB_PSG_UPDATE_COMM_GENDER();			    
				   gender.setGender(comm.getGender());
				   gender.setAuthority(comm.getAuthority());
				   gender.setDate_of_authority(comm.getDate_of_authority());
				   gender.setComm_id(i);			       
				   gender.setStatus(1);
				   gender.setCreated_by(comm.getCreated_by());
				   gender.setCreated_date(comm.getCreated_date());
				   gender.setModified_by(username); 
				   gender.setModified_date(new Date());
				   
				   
				   TB_PSG_UPDATE_COMM_COURSE course=new TB_PSG_UPDATE_COMM_COURSE();
				   course.setCourse(comm.getCourse());
				   course.setBatch_no(comm.getBatch_no());
				   course.setAuthority(comm.getAuthority());
				   course.setDate_of_authority(comm.getDate_of_authority());
				   course.setComm_id(i);			       
				   course.setStatus(1);
				   course.setCreated_by(comm.getCreated_by());
				   course.setCreated_date(comm.getCreated_date());
				   course.setModified_by(username); 
				   course.setModified_date(new Date());
				   
				   Session ses1 = HibernateUtil.getSessionFactory().openSession();
					Transaction t2 = ses1.beginTransaction();		
					Query q = ses1.createQuery(
							"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
					q.setParameter("sus_no", BAN.getUnit_sus_no());
					String Unit_name = (String) q.uniqueResult();
					TB_PSG_UPDATE_COMM_UNIT unit_upd = new TB_PSG_UPDATE_COMM_UNIT();
					   unit_upd.setDate_of_tos(BAN.getDate_of_tos());
					   unit_upd.setUnit_sus_no(BAN.getUnit_sus_no());
					   unit_upd.setUnit_post_to(Unit_name);				   
					   unit_upd.setAuthority(BAN.getAuthority());
					   unit_upd.setDate_of_authority(BAN.getDate_of_authority());
					   unit_upd.setComm_id(i);			       
					   unit_upd.setStatus(1);
					   unit_upd.setCreated_by(BAN.getCreated_by());
					   unit_upd.setCreated_date(BAN.getCreated_date());
					   unit_upd.setModified_by(username); 
					   unit_upd.setModified_date(new Date());
				   
				   
				   
				   TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
					
					ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(comm.getUnit_sus_no());
				    ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(comm.getUnit_sus_no());
				    
				   
				    
				    po.setTo_sus_no(comm.getUnit_sus_no());
					po.setDt_of_sos(comm.getDate_of_tos());
					po.setDt_of_tos(comm.getDate_of_tos());
					po.setCreated_by(comm.getCreated_by());
					
					po.setCreated_date(comm.getCreated_date());
					po.setModified_by(username);
                   po.setModified_date(new Date());
					po.setComm_id(comm.getId());
					po.setStatus(1);
					
					if(orbatlist.size() > 0 && Location_Trnlist.size() > 0 )
					{
						
						
						po.setCmd_sus(orbatlist.get(0).get(1)); 
						po.setCorps_sus(orbatlist.get(0).get(2));
						po.setDiv_sus(orbatlist.get(0).get(3));
						po.setBde_sus(orbatlist.get(0).get(4));
						po.setLocation(Location_Trnlist.get(0).get(0));
						po.setTrn_type(Location_Trnlist.get(0).get(1));
						po.setRank(comm.getRank());
						
						
					String Count_Name = CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_CHANGE_NAME") ;
					 if (Integer.parseInt(Count_Name) == 0) {
							sessionHQL.save(med);
					    
				     }	
					 String Count_Rank = CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_CHANGE_OF_RANK") ;
					 if (Integer.parseInt(Count_Rank) == 0) {
						sessionHQL.save(rnk);
						
				     }	
					 String Count_comm = CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_PSG_CHANGE_OF_COMISSION") ;
					 if (Integer.parseInt(Count_comm) == 0) {
						 sessionHQL.save(coc);
						   
				     }	
					 String Count_Arm= CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_INTER_ARM_TRANSFER") ;
					 if (Integer.parseInt(Count_Arm) == 0) {
						 sessionHQL.save(arm);
						 
				     }
					 String Count_sen= CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_PSG_CHANGE_OF_SENIORITY") ;
					 if (Integer.parseInt(Count_sen) == 0) {
					sessionHQL.save(sen);
						 
				     }
					 String Count_Post = CountOfComm_idApprove(String.valueOf(comm.getId()) , "TB_POSTING_IN_OUT") ;
					
					 if(Integer.parseInt(Count_Post) == 0) {
								sessionHQL.save(po);
							
					 }	
					 String date_of_birth = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_BIRTH") ;
					 if (Integer.parseInt(date_of_birth) == 0) {
						sessionHQL.save(dob);
						
				     }	
					 
					 String commision = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_COMMISSION") ;
					 if (Integer.parseInt(commision) == 0) {
						sessionHQL.save(comm);
						
				     }	
					 
					 String cadet = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_CADET") ;
					 if (Integer.parseInt(cadet) == 0) {
						sessionHQL.save(cadet_no);
						
				     }	
					 String gen = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_GENDER") ;
					 if (Integer.parseInt(gen) == 0) {
						sessionHQL.save(gender);
						
				     }	
					 
					 String course_no = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_COURSE") ;
					 if (Integer.parseInt(course_no) == 0) {
						sessionHQL.save(course);
						
				     }
					 
					 String update_unit = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_UNIT") ;
					 if (Integer.parseInt(update_unit) == 0) {
						sessionHQL.save(unit_upd);
						
				     }
					 
					    sessionHQL.save(his);
					    sessionHQL.flush();
					    sessionHQL.clear(); 
					}
					else
					{						
						Mmap.put("msg", "Orbat Details are not Exist.."); 
						return new ModelAndView("add_offrs_3008_Tiles");
						
					}
			 

				if (d == true) {
					tx.commit();
					Mmap.put("msg", "Data Saved Successfully.");
				}
				if (d == false) {
					Mmap.put("msg", "Personal No already Exist.");
				}
			}

		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:add_offrs_3008");
	}
		
		public String CountOfComm_idApprove(String comm_id ,String TB_MODEL) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String count = null;
			try {
				Query q1 = sessionHQL.createQuery("select count(comm_id) from "+ TB_MODEL +" where comm_id=:comm_id");		
				
			
				q1.setParameter("comm_id", new BigInteger(comm_id));
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();			
				if(list.size() > 0) {
					count = String.valueOf(list.get(0));
				}
				tx.commit();
			} catch (RuntimeException e) {			
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return count;
		}
		
		
		
		
	@RequestMapping(value = "/getPersonnelNoAlreadyExistinpostinout", method = RequestMethod.POST)
	public @ResponseBody Boolean getPersonnelNoAlreadyExist(HttpServletRequest request, HttpSession sessionA,
		String personnel_no) throws HibernateException, ParseException {	
		return SLDAO.getPersonnelNoAlreadyExistinpostinout(personnel_no);

	}

}