package com.controller.psg.Commissioning;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.hibernate.HibernateException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.models.psg.Transaction.TB_TREEPLANTAION;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.joran.conditional.ElseAction;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Proposed_Com_Letter_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	Psg_CommonController p_comm = new Psg_CommonController();
	
	ValidationController valid = new ValidationController();

	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;

	@RequestMapping(value = "/admin/Prop_Comm_letter_new", method = RequestMethod.GET)
	public ModelAndView Prop_Comm_letter_new(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("Prop_Comm_letter_new", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("msg", msg);
		Mmap.put("getCourseNameList", p_comm.getCourseNameList());
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());		
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getPersonalType", p_comm.getPersonalType());
		Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("roleAccess",roleAccess);
		
		return new ModelAndView("Prop_Comm_Letter_New_Tiles");
	}
	
	@RequestMapping(value = "/admin/Prop_Comm_letter_Mns", method = RequestMethod.GET)
	public ModelAndView Prop_Comm_letter_Mns(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("Prop_Comm_letter_Mns", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

	if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("msg", msg);
		Mmap.put("getCourseNameList", p_comm.getCourseNameListmns());
		Mmap.put("getGenderList", p_comm.getGenderList());
		Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionListmns());		
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getPersonalType", p_comm.getPersonalType());
		Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
		Mmap.put("getParentArmList", p_comm.getParentArmList());
		Mmap.put("roleAccess",roleAccess);
		
		return new ModelAndView("Prop_Comm_Letter_Mns_Tiles");
	}




	@RequestMapping(value = "/prop_comm_letter_action", method = RequestMethod.POST)
	public ModelAndView prop_comm_letter_action(
			@ModelAttribute("prop_comm_letter_cmd") TB_TRANS_PROPOSED_COMM_LETTER BAN,
			TB_TRANS_PROPOSED_COMM_LETTER_HISTORY his, BindingResult result, HttpServletRequest request, ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,
			HttpSession session) throws ParseException {
		
		Boolean val = roledao.ScreenRedirect("Prop_Comm_letter_new", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

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
		String tos_date = request.getParameter("date_of_tos");
		String rank_date = request.getParameter("date_of_rank");
		String dt_rank1 =null;
		String dt_rank2 = null;

		String birth_date = request.getParameter("date_of_birth");
		String parent_arm = request.getParameter("parent_arm");
		String regiment = request.getParameter("regiment");
		String parent_armLb = request.getParameter("parent_armLb");
		String rank = request.getParameter("rank");
		String persnl_no1 = request.getParameter("persnl_no1");
		String persnl_no2 = request.getParameter("persnl_no2");
		String persnl_no3 = request.getParameter("persnl_no3");
		String tnai_no = request.getParameter("tnai_no");

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
		 
		 if (request.getParameter("persnl_no1").equals("NR")
					|| request.getParameter("persnl_no1").equals("NS")) {
			 if (!valid.validateSlash(request.getParameter("tnai_no"))) {
					Mmap.put("msg", valid.validateSlashMSG + "TNAI No");
					return new ModelAndView("redirect:Prop_Comm_letter_new");
				
			}
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
		if (request.getParameter("name") == null || request.getParameter("name").trim().equals("")) {
			Mmap.put("msg", "Please Enter Name");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		if(Pattern.matches("^[ A-Za-z]+$", request.getParameter("name").toString())) {
			
			BAN.setName(p_comm.StringValidationForName(request.getParameter("name").toString()));
		}
		else {
			Mmap.put("msg", "Name Should Only Contain Alphabet And Only One Space B/W Words");
			return new ModelAndView("redirect:Prop_Comm_letter_new");
		}
		
		 if (!valid.isvalidLength(request.getParameter("name"), valid.nameMax, valid.nameMin)) {
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
		
		if (request.getParameter("rank") == null || request.getParameter("rank").equals("0")) {
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
				BAN.setStatus(0);
				BAN.setParent_sus_no(parent_sus_no);
				BAN.setParent_unit(parent_unit);
				BAN.setTnai_no(tnai_no);

				sessionHQL.save(BAN);
				sessionHQL.flush();
				sessionHQL.clear();

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
				Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		String redirect = "";
		if (request.getParameter("persnl_no1").equals("NR") || request.getParameter("persnl_no1").equals("NS")) {
		    redirect = "Prop_Comm_letter_Mns";
		} else {
		    redirect = "Prop_Comm_letter_new";
		}
		return new ModelAndView("redirect:"+redirect+"");
	}

	
	@RequestMapping(value = "/getPersonnelNoAlreadyExist", method = RequestMethod.POST)
	public @ResponseBody Boolean getPersonnelNoAlreadyExist(HttpServletRequest request, HttpSession sessionA,
			String personnel_no) throws HibernateException, ParseException {
		
		BigInteger id = new BigInteger(request.getParameter("id"));
		return SLDAO.getPersonnelNoAlreadyExist(personnel_no,id);

	}
	
	@RequestMapping(value = "/admin/treeplantaion", method = RequestMethod.GET)
	public ModelAndView treeplantaiontreeplantaion(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		Boolean val = roledao.ScreenRedirect("Prop_Comm_letter_new", session.getAttribute("roleid").toString());
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}

	
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
	
		return new ModelAndView("treeplantaionTiles");
	}
	
	
	@RequestMapping(value = "/treeplantaionaction", method = RequestMethod.POST)
	public ModelAndView treeplantaionaction(
			@ModelAttribute("treeplantaion_cmd") TB_TREEPLANTAION BAN,
		    BindingResult result, HttpServletRequest request, ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "uploadtree", required = false) MultipartFile uploadtree,
			HttpSession session) throws ParseException, IOException, DecoderException {
		
	
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date date = new Date();
		Date auth_dt = null;
		
		
		
		String username = session.getAttribute("username").toString();
		String sus = session.getAttribute("roleSusNo").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();		
		

		String authority_date = request.getParameter("date_of_authority");		
		String sus_no = request.getParameter("unit_posted_to");
		String tree1qnty = request.getParameter("tree1");
		String tree2qnty = request.getParameter("tree2");
		String tree3qnty = request.getParameter("tree3");
		String tree4qnty = request.getParameter("tree4");
		String doc_type = request.getParameter("uploadtree");

		if (authority_date!=null && !authority_date.trim().equals("") && !authority_date.equals("DD/MM/YYYY")) {
			auth_dt = format.parse(authority_date);
		}
		
		if (request.getParameter("date_of_authority") == null || request.getParameter("date_of_authority").trim().equals("") || request.getParameter("date_of_authority").trim().isEmpty() || request.getParameter("date_of_authority").trim().equals("DD/MM/YYYY") ) {
			Mmap.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:treeplantaion");
		}
		
		if (request.getParameter("tree1") == null || request.getParameter("tree1").trim().equals("") || request.getParameter("tree1").trim().isEmpty()) {
			Mmap.put("msg", "Quantity Field can not be Empty. Please Enter Quantity of all Trees otherwise Enter 0 (Zero).");
			return new ModelAndView("redirect:treeplantaion");
		}
		
		if (request.getParameter("tree2") == null || request.getParameter("tree2").trim().equals("") || request.getParameter("tree2").trim().isEmpty()) {
			Mmap.put("msg", "Quantity Field can not be Empty. Please Enter Quantity of all Trees otherwise Enter 0 (Zero).");
			return new ModelAndView("redirect:treeplantaion");
		}
		
		if (request.getParameter("tree3") == null || request.getParameter("tree3").trim().equals("") || request.getParameter("tree3").trim().isEmpty()) {
			Mmap.put("msg", "Quantity Field can not be Empty. Please Enter Quantity of all Trees otherwise Enter 0 (Zero).");
			return new ModelAndView("redirect:treeplantaion");
		}
		
		if (request.getParameter("tree4") == null || request.getParameter("tree4").trim().equals("") || request.getParameter("tree4").trim().isEmpty()) {
			Mmap.put("msg", "Quantity Field can not be Empty. Please Enter Quantity of all Trees otherwise Enter 0 (Zero).");
			return new ModelAndView("redirect:treeplantaion");
		}
		
		
		if (uploadtree.getSize() >  5 * 1024 * 1024) {
			 Mmap.put("msg", "File size should be less then 5 MB.");
			 return new ModelAndView("redirect:treeplantaion");
		}
		
		
		String fname = "";
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		
		try {	
			
			byte[] bytes = uploadtree.getBytes();
			CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
			if (fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
				String treeFilePath = session.getAttribute("treeFilePath").toString();
				File dir = new File(treeFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String filename = uploadtree.getOriginalFilename();
				String extension = "";
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				
				
				 String sus_no_name = sus_no.replace("/","-");
			
				
				
				fname = dir.getAbsolutePath() + File.separator + sus_no_name +  "__"+  sus +  "___" + timestamp.currentDateWithTimeStampString() + "." +  extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				BAN.setDoc_path_tree(fname);
			
			
				BAN.setCreated_by(username);
				BAN.setCreated_date(date);
				BAN.setDate(auth_dt);
				BAN.setUnit_sus_no(roleSusNo);				
				BAN.setTree1qnty(tree1qnty);				
				BAN.setTree2qnty(tree2qnty);			
				BAN.setTree3qnty(tree3qnty);				
				BAN.setTree4qnty(tree4qnty);
			//	BAN.setDoc_path_tree(fname);

				sessionHQL.save(BAN);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				Mmap.put("msg", "Data Saved Successfully.");
		}
		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:treeplantaion");
	}


}
