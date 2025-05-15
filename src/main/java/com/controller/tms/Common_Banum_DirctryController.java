package com.controller.tms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_TMS_MCT_MASTER;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Common_Banum_DirctryController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Date date1 = new Date();
	String curdate = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(date1);

	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController validation = new ValidationController();

	// Start REQUEST TO ALLOCATE BA / EM NUMBER
	@RequestMapping(value = "/banum_dirctry")
	public ModelAndView banum_dirctry(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("banum_dirctry", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		return new ModelAndView("banum_dirctryTile", "tmsbanumdirCmd", new Tms_Banum_Req_Child());
	}

	@RequestMapping(value = "/tmsbanumdirAct", method = RequestMethod.POST)
	public ModelAndView tmsbanumdirAct(@ModelAttribute("tmsbanumdirCmd") Tms_Banum_Req_Child tb_child,
			@RequestParam(value = "front_view_image1", required = false) MultipartFile front_view_image1,
			@RequestParam(value = "back_view_image1", required = false) MultipartFile back_view_image1,
			@RequestParam(value = "side_view_image1", required = false) MultipartFile side_view_image1,
			@RequestParam(value = "top_view_image1", required = false) MultipartFile top_view_image1,
			@RequestParam(value = "engine_image1", required = false) MultipartFile engine_image1,
			@RequestParam(value = "chasis_image1", required = false) MultipartFile chasis_image1,
			HttpServletRequest request, ModelMap model, HttpSession session, MultipartHttpServletRequest req)
			throws NumberFormatException, HibernateException, ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("banum_dirctry", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		Date date = new Date();

		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString()); // 2 MB
		if (front_view_image1.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:banum_dirctry");
		}
		if (back_view_image1.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:banum_dirctry");
		}
		if (side_view_image1.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:banum_dirctry");
		}
		if (top_view_image1.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:banum_dirctry");
		}

		String front_view_image1Ext = FilenameUtils.getExtension(front_view_image1.getOriginalFilename()).toUpperCase();
		String back_view_image1Ext = FilenameUtils.getExtension(back_view_image1.getOriginalFilename()).toUpperCase();
		String side_view_image1Ext = FilenameUtils.getExtension(side_view_image1.getOriginalFilename()).toUpperCase();
		String top_view_image1Ext = FilenameUtils.getExtension(top_view_image1.getOriginalFilename()).toUpperCase();
		

		String Strpurchas_cost = request.getParameter("purchas_cost").toString();
		if ((request.getParameter("ba_no_type").toString().equals("0"))) {
			if (request.getParameter("mct_number1") != null
					& validation.mctLength(request.getParameter("mct_number1")) == false) {
				model.put("msg", validation.mctMSG);
				return new ModelAndView("redirect:banum_dirctry");
			}
		}
		if (Strpurchas_cost.equals("0") || Strpurchas_cost.equals("") || Strpurchas_cost.equals("null")
				|| Strpurchas_cost.equals(null)) {
			model.put("msg", "Please Enter Purchase Cost.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.purchas_costLength(request.getParameter("purchas_cost")) == false) {
			model.put("msg", validation.purchas_costMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (Double.parseDouble(Strpurchas_cost) < 0) {
			model.put("msg", "Negative value is not allowed for Purchas Cost.");
			return new ModelAndView("redirect:banum_dirctry");
		}
		if (request.getParameter("ba_no_type") == null) {
			model.put("msg", "Please Select Army/Non-Army.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("requesting_agency").equals("")
				|| request.getParameter("requesting_agency").equals("null")
				|| request.getParameter("requesting_agency").equals(null)) {
			model.put("msg", "Please Enter Requesting Agency.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.checkUnit_nameLength(request.getParameter("requesting_agency")) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("sus_no").equals("") || request.getParameter("sus_no").equals("null")
				|| request.getParameter("sus_no").equals(null)) {
			model.put("msg", "Please Select Sus No.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.sus_noLength(request.getParameter("sus_no")) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("dte_of_reqst").equals("")
				|| request.getParameter("dte_of_reqst").equals("null")
				|| request.getParameter("dte_of_reqst").equals(null)) {
			model.put("msg", "Please Select Date Of Request.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (format.parse(request.getParameter("dte_of_reqst")).compareTo(today_dt) > 0
				&& format.parse(request.getParameter("dte_of_reqst")).compareTo(today_dt) != 0) {
			model.put("msg", "Please Enter Valid Date Of Request.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("date_of_auth_letter").equals("")
				|| request.getParameter("date_of_auth_letter").equals("null")
				|| request.getParameter("date_of_auth_letter").equals(null)) {
			model.put("msg", "Please Select Auth Letter Date.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (format.parse(request.getParameter("date_of_auth_letter")).compareTo(today_dt) > 0
				&& format.parse(request.getParameter("date_of_auth_letter")).compareTo(today_dt) != 0) {
			model.put("msg", "Please Enter Valid Auth Letter Date.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("veh_cat").equals("0") || request.getParameter("veh_cat").equals("")
				|| request.getParameter("veh_cat").equals("null") || request.getParameter("veh_cat").equals(null)) {
			model.put("msg", "Please Enter Type of Vehicle.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if ((request.getParameter("ba_no_type").toString().equals("0"))
				&& (request.getParameter("mct_number1").toString().equals("0")
						|| request.getParameter("mct_number1").toString().equals("")
						|| request.getParameter("mct_number1").toString().equals("null")
						|| request.getParameter("mct_number1").toString().equals(null))) {
			model.put("msg", "Please Enter MCT Number.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.std_nomclatureTMSLength(request.getParameter("new_nomencatre")) == false) {
			model.put("msg", validation.std_nomclatureTMSMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("vehicle_clas_code").equals("")
				|| request.getParameter("vehicle_clas_code").equals("null")
				|| request.getParameter("vehicle_clas_code").equals(null)) {
			model.put("msg", "Please Enter Vehicle Class Code.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.vehicle_class_codeLength(request.getParameter("vehicle_clas_code")) == false) {
			model.put("msg", validation.vehicle_class_codeMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("veh_class").equals("") || request.getParameter("veh_class").equals("null")
				|| request.getParameter("veh_class").equals(null)) {
			model.put("msg", "Please Enter Class of Vehicle.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.vehicle_class_codeLength(request.getParameter("veh_class")) == false) {
			model.put("msg", validation.class_of_vehicleMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.country_of_originTMSLength(request.getParameter("country_of_origin")) == false) {
			model.put("msg", validation.country_of_originTMSMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.axle_wtsLength(request.getParameter("axle_wts")) == false) {
			model.put("msg", validation.axle_wtsMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("tonnage"))) == false) {
			model.put("msg", validation.tonnageMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("towing_capcty"))) == false) {
			model.put("msg", validation.towing_capacityMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("lift_capcty"))) == false) {
			model.put("msg", validation.lift_capacityMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.checkUnit_nameLength(request.getParameter("sponsoring_dte")) == false) {
			model.put("msg", validation.sponsoring_dteMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("year_of_entry").equals("")
				|| request.getParameter("year_of_entry").equals("null")
				|| request.getParameter("year_of_entry").equals(null)) {
			model.put("msg", "Please Enter Yr of Entry into Service.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.mct_main_idLength(request.getParameter("year_of_entry")) == false) {
			model.put("msg", validation.year_of_entryMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("auth_letter_no").equals("")
				|| request.getParameter("auth_letter_no").equals("null")
				|| request.getParameter("auth_letter_no").equals(null)) {
			model.put("msg", "Please Enter Auth letter No.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.auth_letter_noTMSLength(request.getParameter("auth_letter_no")) == false) {
			model.put("msg", validation.auth_letter_noTMSMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("initiating_auth").equals("")
				|| request.getParameter("initiating_auth").equals("null")
				|| request.getParameter("initiating_auth").equals(null)) {
			model.put("msg", "Please Enter Initial Auth.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.initiating_authTMSLength(request.getParameter("initiating_auth")) == false) {
			model.put("msg", validation.initiating_authTMSMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.spl_eqpmnt_fitdLength(request.getParameter("spl_eqpmnt_fitd")) == false) {
			model.put("msg", validation.spl_eqpmnt_fitdMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (front_view_image1.isEmpty()) {
			model.put("msg", "Please Enter Front View Image.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (back_view_image1.isEmpty()) {
			model.put("msg", "Please Enter Back View Image.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (side_view_image1.isEmpty()) {
			model.put("msg", "Please Enter Side View Image.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (top_view_image1.isEmpty()) {
			model.put("msg", "Please Enter Top View Image.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (!front_view_image1Ext.equals("JPG") && !front_view_image1Ext.equals("JPEG")
				&& !front_view_image1Ext.equals("PNG")) {
			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (!back_view_image1Ext.equals("JPG") && !back_view_image1Ext.equals("JPEG")
				&& !back_view_image1Ext.equals("PNG")) {
			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (!side_view_image1Ext.equals("JPG") && !side_view_image1Ext.equals("JPEG")
				&& !side_view_image1Ext.equals("PNG")) {
			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (!top_view_image1Ext.equals("JPG") && !top_view_image1Ext.equals("JPEG")
				&& !top_view_image1Ext.equals("PNG")) {
			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.checkUnit_nameLength(request.getParameter("typ_of_spl_eqpt_role")) == false) {
			model.put("msg", validation.typ_of_spl_eqpt_roleMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("quantity").equals("0") || request.getParameter("quantity").equals("")
				|| request.getParameter("quantity").equals("null") || request.getParameter("quantity").equals(null)) {
			model.put("msg", "Please Enter Quantity.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (validation.code_no_fromLength(request.getParameter("quantity")) == false) {
			model.put("msg", validation.quantityMSG);
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("engine_no1").equals("") || request.getParameter("engine_no1").equals("null")
				|| request.getParameter("engine_no1").equals(null)) {
			model.put("msg", "Please Enter Engine.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("chasis_no1").equals("") || request.getParameter("chasis_no1").equals("null")
				|| request.getParameter("chasis_no1").equals(null)) {
			model.put("msg", "Please Enter Chasis No.");
			return new ModelAndView("redirect:banum_dirctry");
		} else if (request.getParameter("remarks").equals("") || request.getParameter("remarks").equals("null")
				|| request.getParameter("remarks").equals(null)) {
			model.put("msg", "Please Enter Remarks.");
			return new ModelAndView("redirect:banum_dirctry");
		} 
		else {
			Session sessionHQL = null;
			Transaction tx = null;
			try {
				sessionHQL = HibernateUtil.getSessionFactory().openSession();
				tx = sessionHQL.beginTransaction();
				String requesting_agency = request.getParameter("requesting_agency");
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String sus_no = "";
				if(roleAccess.equals("Unit")  || roleAccess.equals("Depot")){ 
					sus_no = roleSusNo;
				}else {
					 sus_no = request.getParameter("sus_no");
				}
				String year_of_entry = request.getParameter("year_of_entry");
				String dte_of_reqst = request.getParameter("dte_of_reqst");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat formatter1 = new SimpleDateFormat("yyyy");
				Date dte_of_reqst1 = null;
				Date year_of_entry1 = null;
				String remarks = request.getParameter("remarks");
				try {
					dte_of_reqst1 = (Date) formatter.parse(dte_of_reqst);
					year_of_entry1 = (Date) formatter1.parse(year_of_entry);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int ba_no_type = Integer.parseInt(request.getParameter("ba_no_type"));

				Tms_Banum_Req_Prnt tb_prnt = new Tms_Banum_Req_Prnt();
				tb_prnt.setRequesting_agency(requesting_agency);
				tb_prnt.setSus_no(sus_no);
				tb_prnt.setDte_of_reqst(dte_of_reqst1);
				tb_prnt.setBa_no_type(ba_no_type);
				tb_prnt.setCreation_date(date);
				tb_prnt.setCreation_by(username);
				tb_prnt.setVersion_no(1);
				tb_prnt.setStatus("0");
				tb_prnt.setType_of_request("0");

				int parent_id = (Integer) sessionHQL.save(tb_prnt);
				sessionHQL.flush();
				sessionHQL.clear();

				String present = request.getParameter("purchas_cost");
				String fname = "";
				DateWithTimeStampController timestamp = new DateWithTimeStampController();
				
				if (!front_view_image1.isEmpty()) {
					try {
						byte[] bytes = front_view_image1.getBytes();
						 CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						 if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
							 String tmsFilePath = session.getAttribute("tmsFilePath").toString();
							 File dir = new File(tmsFilePath);
							 if (!dir.exists()) {
								dir.mkdirs();
							 }
							 String filename = front_view_image1.getOriginalFilename();
							 String extension = "";
							 int i = filename.lastIndexOf('.');
							 if (i >= 0) {
								 extension = filename.substring(i + 1);
							 }
							 fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_FRONT_TMSDOC." + extension;
							 if (validation.checkImageAnmlLength(fname) == false) {
								 model.put("msg", validation.front_view_imageMSG);
								 return new ModelAndView("redirect:banum_dirctry");
							 }
							 File serverFile = new File(fname);
							 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							tb_child.setFront_view_image(fname);
						 }else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
							return new ModelAndView("redirect:banum_dirctry");
						 }
					} catch (Exception e) {
						
					}
				}
				if (!back_view_image1.isEmpty()) {
					try {
						byte[] bytes = back_view_image1.getBytes();
						 CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						 if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
							String tmsFilePath = session.getAttribute("tmsFilePath").toString();
							File dir = new File(tmsFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							String filename = back_view_image1.getOriginalFilename();
							String extension = "";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i + 1);
							}
							fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
									+ "_" + userid + "_BACK_TMSDOC." + extension;
							if (validation.checkImageAnmlLength(fname) == false) {
								model.put("msg", validation.back_view_imageMSG);
								return new ModelAndView("redirect:banum_dirctry");
							}
							File serverFile = new File(fname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							tb_child.setBack_view_image(fname);
						 }else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
							return new ModelAndView("redirect:banum_dirctry");
						 }
					} catch (Exception e) {
					}
				}
				if (!side_view_image1.isEmpty()) {
					try {
						byte[] bytes = side_view_image1.getBytes();
						 CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						 if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
							String tmsFilePath = session.getAttribute("tmsFilePath").toString();
							File dir = new File(tmsFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							String filename = side_view_image1.getOriginalFilename();
							String extension = "";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i + 1);
							}
							fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
									+ "_" + userid + "_SIDE_TMSDOC." + extension;
							if (validation.checkImageAnmlLength(fname) == false) {
								model.put("msg", validation.side_view_imageMSG);
								return new ModelAndView("redirect:banum_dirctry");
							}
							File serverFile = new File(fname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							tb_child.setSide_view_image(fname);
						 }else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
							return new ModelAndView("redirect:banum_dirctry");
						 }
					} catch (Exception e) {
					}
				}

				if (!top_view_image1.isEmpty()) {
					try {
						byte[] bytes = top_view_image1.getBytes();
						CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
							String tmsFilePath = session.getAttribute("tmsFilePath").toString();
							File dir = new File(tmsFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							String filename = top_view_image1.getOriginalFilename();
							String extension = "";
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i + 1);
							}
							fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
									+ "_" + userid + "_TOP_TMSDOC." + extension;
							if (validation.checkImageAnmlLength(fname) == false) {
								model.put("msg", validation.top_view_imageMSG);
								return new ModelAndView("redirect:banum_dirctry");
							}
							File serverFile = new File(fname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							tb_child.setTop_view_image(fname);
						}else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
							return new ModelAndView("redirect:banum_dirctry");
						}
					} catch (Exception e) {
					}
				}

				tb_child.setCreation_by(username);
				tb_child.setCreation_date(date);
				tb_child.setParent_req_id(parent_id);
				tb_child.setYear_of_entry(year_of_entry1);
				tb_child.setPresent_cost(present);
				tb_child.setRemarks(remarks);
				if (!request.getParameter("mct_number1").equals("") & request.getParameter("mct_number1") != null) {
					tb_child.setMct_number(new BigInteger(request.getParameter("mct_number1")));
				} else {
					tb_child.setMct_number(new BigInteger("0"));
				}

				int countlength = 0;
				countlength = Integer.parseInt(request.getParameter("count"));
				for (int i = 1; i <= countlength; i++) {
					String engine_no = request.getParameter("engine_no" + i).toUpperCase();
					String chasis_no = request.getParameter("chasis_no" + i).toUpperCase();
					if (validation.initiating_authTMSLength(request.getParameter("engine_no" + i)) == false) {
						model.put("msg", validation.engine_noMSG);
						return new ModelAndView("redirect:banum_dirctry");
					}
					if (validation.initiating_authTMSLength(request.getParameter("chasis_no" + i)) == false) {
						model.put("msg", validation.chasis_noMSG);
						return new ModelAndView("redirect:banum_dirctry");
					}
					MultipartFile engine_image = req.getFile("engine_image" + i);
					MultipartFile chasis_image = req.getFile("chasis_image" + i);
					if (engine_image.getSize() > fileSizeLimit) {
						model.put("msg", "File size should be less then 2 MB.");
						return new ModelAndView("redirect:banum_dirctry");
					}
					if (chasis_image.getSize() > fileSizeLimit) {
						model.put("msg", "File size should be less then 2 MB.");
						return new ModelAndView("redirect:banum_dirctry");
					}
					String engine_imageExt = FilenameUtils.getExtension(engine_image.getOriginalFilename()).toUpperCase();
					String chasis_imageExt = FilenameUtils.getExtension(chasis_image.getOriginalFilename()).toUpperCase();
					if (!engine_imageExt.equals("JPG") && !engine_imageExt.equals("JPEG") && !engine_imageExt.equals("PNG")) {
						model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
						return new ModelAndView("redirect:banum_dirctry");
					}
					if (!chasis_imageExt.equals("JPG") && !chasis_imageExt.equals("JPEG") && !chasis_imageExt.equals("PNG")) {
						model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
						return new ModelAndView("redirect:banum_dirctry");
					}
					if (!engine_image.isEmpty()) {
						try {
							byte[] bytes = engine_image.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							 if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("tmsFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = engine_image.getOriginalFilename();
								String extension = "";
								int j = filename.lastIndexOf('.');
								if (j >= 0) {
									extension = filename.substring(j + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
										+ "_" + userid + "_ENGINE_TMSDOC." + extension;
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.engine_imageMSG);
									return new ModelAndView("redirect:banum_dirctry");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								tb_child.setEngine_image(fname);
							 }else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
								return new ModelAndView("redirect:banum_dirctry");
							 }
						} catch (Exception e) {
						}
					}
					if (!chasis_image.isEmpty()) {
						try {
							byte[] bytes = chasis_image.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							 if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("tmsFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = chasis_image.getOriginalFilename();
								String extension = "";
								int j = filename.lastIndexOf('.');
								if (j >= 0) {
									extension = filename.substring(j + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
										+ "_" + userid + "_CHASIS_TMSDOC." + extension;
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.chasis_imageMSG);
									return new ModelAndView("redirect:banum_dirctry");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								tb_child.setChasis_img(fname);
							 }else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed.");
								return new ModelAndView("redirect:banum_dirctry");
							 }
						} catch (Exception e) {
						}
					}
					tb_child.setChasis_no(chasis_no);
					tb_child.setEngine_no(engine_no);
					if (checkIfExistEngineNO("'" + engine_no + "'") != false) {
						model.put("msg", "Engine Number is Already exists ("+engine_no+")");
						return new ModelAndView("redirect:banum_dirctry");
					}
					if (checkIfExistChasisNO("'" + chasis_no + "'") != false) {
						model.put("msg", "Chasis Number is Already exists ("+chasis_no+ ")");
						return new ModelAndView("redirect:banum_dirctry");
					}
					sessionHQL.save(tb_child);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				tx.commit();
				model.put("msg", "BA No Request Submitted Successfully.");
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
		}
		return new ModelAndView("redirect:banum_dirctry");
	}

	// End REQUEST TO ALLOCATE BA / EM NUMBER

	@RequestMapping(value = "/getMCTNOList")
	public @ResponseBody List<String> getMCTNOList(HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct mct from TB_TMS_MCT_MASTER where status = 'ACTIVE'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getMctNotoStdNomenclatureList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNotoStdNomenclatureList(BigInteger mct, HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct std_nomclature from TB_TMS_MCT_MASTER where status = 'ACTIVE' and mct=:mct");
		q.setParameter("mct", mct);
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

	@RequestMapping(value = "/getStdNomenclaturetoMctNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getStdNomenclaturetoMctNoList1(String std_nomclature, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct mct from TB_TMS_MCT_MASTER where status = 'ACTIVE' and std_nomclature=:std_nomclature");
		q.setParameter("std_nomclature", std_nomclature);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
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
				String mct_no = String.valueOf(list.get(i));
				encCode = c.doFinal(mct_no.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@SuppressWarnings("unchecked")
	public Boolean checkIfExistEngineNO(String EngineNO) {
		EngineNO = EngineNO.replace("&#39;", "'");
		String hql = "select id from Tms_Banum_Req_Child where upper(engine_no) in :EngineNO ";
		List<Tms_Banum_Req_Child> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("EngineNO", "(" + EngineNO.toUpperCase() + ")");
	
		users = (List<Tms_Banum_Req_Child>) query.list();
		tx.commit();
		session.close();
		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean checkIfExistChasisNO(String ChasisNO) {
		ChasisNO = ChasisNO.replace("&#39;", "'");
		String hql = "select id from Tms_Banum_Req_Child where upper(chasis_no) in :ChasisNO ";
		List<Tms_Banum_Req_Child> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("ChasisNO", "(" + ChasisNO.toUpperCase() + ")");
		users = (List<Tms_Banum_Req_Child>) query.list();
		tx.commit();
		session.close();
		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkIfExistEngineNO", method = RequestMethod.POST)
	public @ResponseBody List<Tms_Banum_Req_Child> checkIfExistEngineNO_BA_Allocation(String EngineNO,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		EngineNO = EngineNO.replace("&#39;", "'");

		StringBuilder builder = new StringBuilder();
		String[] EngArray = EngineNO.split(",");
		builder.append(" (");
		for (int i = 1; i <= EngArray.length; i++) {
			builder.append(" upper(engine_no) = :eng" + i);
			if (EngArray.length > i) {
				builder.append(" or ");
			}
		}
		builder.append(")");

		String hql = "select distinct engine_no from Tms_Banum_Req_Child where " + builder;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 1; i <= EngArray.length; i++) {
			query.setParameter("eng" + i, EngArray[i - 1].toUpperCase());
		}
		List<Tms_Banum_Req_Child> engine_nos = (List<Tms_Banum_Req_Child>) query.list();
		tx.commit();
		session.close();
		return engine_nos;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkIfExistChasisNO", method = RequestMethod.POST)
	public @ResponseBody List<Tms_Banum_Req_Child> checkIfExistChasisNO_BA_Allocation(String ChasisNO,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		ChasisNO = ChasisNO.replace("&#39;", "'");

		StringBuilder builder = new StringBuilder();
		String[] chaArray = ChasisNO.split(",");
		builder.append(" (");
		for (int i = 1; i <= chaArray.length; i++) {
			builder.append(" upper(chasis_no) = :cha" + i);
			if (chaArray.length > i) {
				builder.append(" or ");
			}
		}
		builder.append(")");

		String hql = "select chasis_no from Tms_Banum_Req_Child where " + builder;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 1; i <= chaArray.length; i++) {
			query.setParameter("cha" + i, chaArray[i - 1].toUpperCase());
		}
		List<Tms_Banum_Req_Child> chasis_nos = (List<Tms_Banum_Req_Child>) query.list();
		tx.commit();
		session.close();
		return chasis_nos;
	}

	@RequestMapping(value = "/getMCTREQBANOAll", method = RequestMethod.POST)
	public @ResponseBody List<String> getMCTREQBANOAll(String mct, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		BigInteger mct_no = new BigInteger(mct);
		Query q = session.createQuery(" from TB_TMS_MCT_MASTER where status = 'ACTIVE' and mct=:mct");
		q.setParameter("mct", mct_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		List<String> FinalList = new ArrayList<>();
		String std_nomclature = list.get(0).getStd_nomclature();
		String veh_class_code = list.get(0).getVeh_class_code();
		String axle_wts = list.get(0).getAxle_wts();
		String drive = list.get(0).getDrive();
		String type_fuel = list.get(0).getType_fuel();
		String no_of_wheels = String.valueOf(list.get(0).getNo_of_wheels());
		String no_of_axles = String.valueOf(list.get(0).getNo_of_axles());
		String tonnage = String.valueOf(list.get(0).getTonnage());
		String towing_capacity = String.valueOf(list.get(0).getTowing_capacity());
		String lift_capacity = String.valueOf(list.get(0).getLift_capacity());
		String wheel_track = String.valueOf(list.get(0).getWheel_track());
		String sponsoring_dte = String.valueOf(list.get(0).getSponsoring_dte());

		String base64EncodedEncryptedstd_nomclature = new String(Base64.encodeBase64(c.doFinal(std_nomclature.getBytes())));
		String base64EncodedEncryptedveh_class_code = new String(Base64.encodeBase64(c.doFinal(veh_class_code.getBytes())));
		String base64EncodedEncryptedaxle_wts = "";
		if (axle_wts != null) {
			base64EncodedEncryptedaxle_wts = new String(Base64.encodeBase64(c.doFinal(axle_wts.getBytes())));
		} else {
			base64EncodedEncryptedaxle_wts = "";
		}
		String base64EncodedEncrypteddrive = "";
		if (drive != null) {
			base64EncodedEncrypteddrive = new String(Base64.encodeBase64(c.doFinal(drive.getBytes())));
		} else {
			base64EncodedEncrypteddrive = "";
		}
		String base64EncodedEncryptedtype_fuel = "";
		if (type_fuel != null) {
			base64EncodedEncryptedtype_fuel = new String(Base64.encodeBase64(c.doFinal(type_fuel.getBytes())));
		} else {
			base64EncodedEncryptedtype_fuel = "";
		}
		String base64EncodedEncryptedno_of_wheels = new String(Base64.encodeBase64(c.doFinal(no_of_wheels.getBytes())));
		String base64EncodedEncryptedno_of_axles = new String(Base64.encodeBase64(c.doFinal(no_of_axles.getBytes())));
		String base64EncodedEncryptedtonnage = new String(Base64.encodeBase64(c.doFinal(tonnage.getBytes())));
		String base64EncodedEncryptedtowing_capacity = new String(Base64.encodeBase64(c.doFinal(towing_capacity.getBytes())));
		String base64EncodedEncryptedlift_capacity = new String(Base64.encodeBase64(c.doFinal(lift_capacity.getBytes())));
		String base64EncodedEncryptedwheel_track = new String(Base64.encodeBase64(c.doFinal(wheel_track.getBytes())));
		String base64EncodedEncryptedsponsoring_dte = new String(Base64.encodeBase64(c.doFinal(sponsoring_dte.getBytes())));

		FinalList.add(base64EncodedEncryptedstd_nomclature);
		FinalList.add(base64EncodedEncryptedveh_class_code);
		FinalList.add(base64EncodedEncryptedaxle_wts);
		FinalList.add(base64EncodedEncrypteddrive);
		FinalList.add(base64EncodedEncryptedtype_fuel);
		FinalList.add(base64EncodedEncryptedno_of_wheels);
		FinalList.add(base64EncodedEncryptedno_of_axles);
		FinalList.add(base64EncodedEncryptedtonnage);
		FinalList.add(base64EncodedEncryptedtowing_capacity);
		FinalList.add(base64EncodedEncryptedlift_capacity);
		FinalList.add(base64EncodedEncryptedwheel_track);
		FinalList.add(base64EncodedEncryptedsponsoring_dte);
		if (list.size() != 0) {
			FinalList.add(enckey + "YbFjyB==");
		}
		return FinalList;
	}
}
