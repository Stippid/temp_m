package com.controller.Animal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.ParseInt;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.tms.Animal_Status_Controller_Search;
import com.controller.tms.Animals_DetailsController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Animal.Animal_Census_Dao;
import com.dao.Animal.Animal_Census_DaoImpl;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Tms_AnimalDao;
import com.dao.tms.Tms_AnimalDaoImpl;
import com.model.Animal.TB_ANIMAL_CENSUS_DTLS;
import com.model.Animal.TB_ANIMAL_STR_INCR_DECR;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_ANIMAL_HISTORY_DETAILS;
import com.models.Tms_animals_details;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Animal_Census_Controller {

	ValidationController validation = new ValidationController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	Animals_DetailsController ani = new Animals_DetailsController();
	AllMethodsControllerOrbat orbat = new AllMethodsControllerOrbat();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	private Animal_Census_Dao anmlDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// Enter census for animal --------  Added by Pranay 21-03-25
	
	@RequestMapping(value = "/animal_census_dtl", method = RequestMethod.GET)
	public ModelAndView animal_census_dtl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String anml_type, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("animal_census_dtl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", ani.getAnimalTypeList(session));
		Mmap.put("getTypeOfDogList", ani.getTypeOfDogList(session));
		Mmap.put("getsplzList", ani.getsplzList1(session));
		Mmap.put("msg", msg);
		return new ModelAndView("animal_census_Tiles");
	}
	
	@RequestMapping(value = "/animal_census_dtl1", method = RequestMethod.POST)
	public ModelAndView animal_census_dtl1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type8", required = false) String anml_type) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("animal_census_dtl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("anml_type8", anml_type);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getTypeOfDogList", ani.getTypeOfDogList(session));
		Mmap.put("getsplzList", ani.getsplzList1(session));
		Mmap.put("msg", msg);
		Mmap.put("getclrList", ani.getclrList(anml_type, session));
		Mmap.put("getbreedList", ani.getbreedList(anml_type, session));
		Mmap.put("getFitnessStatusList", ani.getFitnessStatusList(anml_type, session));
		Mmap.put("getSourceList", ani.getSourceList(anml_type, session));
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
	 if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", orbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		
		return new ModelAndView("animal_census_Tiles");
	}
	
	@RequestMapping(value = "/add_animal_census_act", method = RequestMethod.POST)
	public ModelAndView add_animal_census_act(
			@RequestParam(value = "front_image_anml", required = false) MultipartFile front_image_anml,
			@RequestParam(value = "left_image_anml", required = false) MultipartFile left_image_anml,
			@RequestParam(value = "right_image_anml", required = false) MultipartFile right_image_anml,
			HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, ModelMap model, HttpSession session)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("animal_census_dtl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		TB_ANIMAL_CENSUS_DTLS td =new TB_ANIMAL_CENSUS_DTLS();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		String fname = "";
		String extension = "";

		String anml_type = request.getParameter("anml_type").trim();
		String animal_purchase_cost = request.getParameter("animal_purchase_cost").trim();
		String army_num = request.getParameter("army_num").toString().trim();
		String type_dog = request.getParameter("type_dog").trim();
		String name_of_dog = request.getParameter("name_of_dog").toString().trim();
		String specialization = request.getParameter("specialization").toString().trim();
		String breed = request.getParameter("breed").toString().trim();
		String colour = request.getParameter("colour").toString().trim();
		String source = request.getParameter("source").toString().trim();
		String sex = request.getParameter("sex").toString().trim();
		String date_of_birth = request.getParameter("date_of_birth").toString().trim();
		String details_of_sire = request.getParameter("details_of_sire").toString().trim();
		String details_of_dam = request.getParameter("details_of_dam").toString().trim();
		String armyno_of_sire = request.getParameter("armyno_of_sire").toString().trim();
		String armyno_of_dam = request.getParameter("armyno_of_dam").toString().trim();
		String microchip_no = request.getParameter("microchip_no").toString().trim();
		String fitness_status = request.getParameter("fitness_status").toString().trim();
		String date_admitted = request.getParameter("date_admitted").toString().trim();
		String fitness_deployment = request.getParameter("fitness_deployment").toString().trim();
		String tos = request.getParameter("tos").toString().trim();
		String date_of_auth = request.getParameter("date_of_auth").toString().trim();
		String auth_no = request.getParameter("auth_no").toString().trim();
		String roleSusNo = session.getAttribute("roleSusNo").toString().trim();
		String unit_name = request.getParameter("unit_name");
		String sus_no = request.getParameter("sus_no").trim();

		String roleAccess = session.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("Unit")) {

			if(sus_no == null || sus_no.equals("null") || sus_no.equals("")) {
				model.put("msg", "Please Enter Unit Sus No.");
				return new ModelAndView("redirect:animal_census_dtl");
			}
			if (!validation.isOnlyAlphabetNumeric(sus_no)) {
				model.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
				return new ModelAndView("redirect:animal_census_dtl");
			}
			if (!validation.SusNoLength(sus_no)) {
				model.put("msg", validation.SusNoMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}
			else {
				roleSusNo = sus_no;
			}

			if(unit_name == null || unit_name.equals("null") || unit_name.equals("")) {
				model.put("msg", "Please Enter Unit Name.");
				return new ModelAndView("redirect:animal_census_dtlL");
			}

		}

		String cost = "";
		String value = "";

		cost = animal_purchase_cost.replaceAll(",", "");
		

		Pattern pattern = Pattern.compile(".*[^0-9].*");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString()); // 2 MB
		if (front_image_anml.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:animal_census_dtl");
		}
		if (left_image_anml.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:animal_census_dtl");
		}
		if (right_image_anml.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:animal_census_dtl");
		}
		

		else if (fitness_status == "3" ||  fitness_status.equals("3")) {
			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				return new ModelAndView("redirect:animal_census_dtl");
			} else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:animal_census_dtl");
				}
			}
		}

		else if (fitness_status == "7" ||  fitness_status.equals("7")) {

			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				return new ModelAndView("redirect:animal_census_dtl");
			} else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:animal_census_dtl");
				}
			}

		}


		else if (tos.equals("")) {
			if (format.parse(request.getParameter("tos")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("tos")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:animal_census_dtl");
			}
		}

		/*if (!pattern.matcher(cost).matches() == false) {
			model.put("msg", "Please Enter valid Purchase Cost");
			return new ModelAndView("redirect:animal_census_dtl");
		}*/


		if (request.getParameter("anml_type") == null) {
			model.put("msg", "Please Select Animal Type");
			return new ModelAndView("redirect:animal_census_dtl");

		} else {

			if (anml_type.equals("Army Dog")) {
				
				if (army_num.equals("")) {
					model.put("msg", "Please Enter Army No");
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (validation.check20Length(army_num) == false) {
					model.put("msg", validation.army_numMSG);
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (checkDetailsOfanimaldogarmy(army_num).size() != 0) {
					model.put("msg", "Army Number is Already Exist");
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (type_dog== "0" || type_dog.equals("0")) {
					model.put("msg", "Please Select Type of Dog");
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (name_of_dog.equals("")) {
					model.put("msg", "Please Enter Name of Dog");
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (validation.check35Length(name_of_dog) == false) {
					model.put("msg", validation.name_of_dogMSG);
					return new ModelAndView("redirect:animal_census_dtl");
				}

				else if (specialization == "0" || specialization.equals("0")) {
					model.put("msg", "Please Select Specialisation");
					return new ModelAndView("redirect:animal_census_dtl");
				}

			} 

			if (breed == "0" || breed.equals("0") ) {
				model.put("msg", "Please Select Breed");
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (colour == "0" || colour.equals("0")) {
				model.put("msg", "Please Select Colour");
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (request.getParameter("sex") == null) {
				model.put("msg", "Please Select Sex");
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (source == "0" || source.equals("0")) {
				model.put("msg", "Please Select Source");
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (date_of_birth.equals("")) {
				model.put("msg", "Please Select  Date of Birth");
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:animal_census_dtl");
			}


			else if (validation.check35Length(details_of_sire) == false) {
				model.put("msg", validation.details_of_sireMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (validation.check35Length(details_of_dam) == false) {
				model.put("msg", validation.details_of_damMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}

			else if (validation.check20Length(microchip_no) == false) {
				model.put("msg", validation.microchip_noMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}


			else if (validation.check255Length(fitness_deployment) == false) {
				model.put("msg", validation.fitness_deploymentMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}

			/*else if (cost.equals("")) {
				model.put("msg", "Please Enter Either Animal Purchase Cost ");
				return new ModelAndView("redirect:animal_census_dtl");
			}*/

			else if (validation.check10Length(cost) == false) {
				model.put("msg", validation.animal_purchase_costMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}


			else if (validation.checkAnimalMasterLength(auth_no) == false) {
				model.put("msg", validation.authorityMSG);
				return new ModelAndView("redirect:animal_census_dtl");
			}


			else {

				

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				sessionHQL.beginTransaction();

				try {

					if (!front_image_anml.isEmpty()) {
						String image_anml1Ext = FilenameUtils.getExtension(front_image_anml.getOriginalFilename());
						if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
							return new ModelAndView("redirect:animal_census_dtl");
						}
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						
						try {
							byte[] bytes = front_image_anml.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("animalFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = front_image_anml.getOriginalFilename();
								int i = filename.lastIndexOf('.');
								if (i >= 0) {
									extension = filename.substring(i + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_FRONTFACE_ANIMALDOC." + extension;
	
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.image_anmlMSG);
									return new ModelAndView("redirect:animal_census_dtl");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								td.setFront_img_path(fname);
							}else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:animal_census_dtl");
							}

						} catch (Exception e) {
						}
					}
					
					if (!left_image_anml.isEmpty()) {
						String image_anml1Ext = FilenameUtils.getExtension(left_image_anml.getOriginalFilename());
						if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
							return new ModelAndView("redirect:animal_census_dtl");
						}
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						
						try {
							byte[] bytes = left_image_anml.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("animalFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = left_image_anml.getOriginalFilename();
								int i = filename.lastIndexOf('.');
								if (i >= 0) {
									extension = filename.substring(i + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_LEFTFACE_ANIMALDOC." + extension;
	
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.image_anmlMSG);
									return new ModelAndView("redirect:animal_census_dtl");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								td.setLeft_img_path(fname);
							}else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:animal_census_dtl");
							}

						} catch (Exception e) {
						}
					}
					
					if (!right_image_anml.isEmpty()) {
						String image_anml1Ext = FilenameUtils.getExtension(right_image_anml.getOriginalFilename());
						if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
							return new ModelAndView("redirect:animal_census_dtl");
						}
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						
						try {
							byte[] bytes = right_image_anml.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("animalFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = right_image_anml.getOriginalFilename();
								int i = filename.lastIndexOf('.');
								if (i >= 0) {
									extension = filename.substring(i + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_RIGHTFACE_ANIMALDOC." + extension;
	
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.image_anmlMSG);
									return new ModelAndView("redirect:animal_census_dtl");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								td.setRight_img_path(fname);
							}else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:animal_census_dtl");
							}

						} catch (Exception e) {
						}
					}
					

				
					td.setStatus(0);
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setAnimal_purchase_cost(cost);
					td.setAnimal_type(anml_type);
					td.setArmy_no(army_num);
					td.setAuth_letter_no(auth_no);
					td.setBreed(Integer.parseInt(breed));
					td.setColor(Integer.parseInt(colour));
					td.setDam_armyno(armyno_of_dam);
					td.setDam_name(details_of_dam);
					td.setSire_armyno(armyno_of_sire);
					td.setSire_name(details_of_sire);
					
					if (fitness_status == "3" ||  fitness_status.equals("3") || fitness_status == "7" ||  fitness_status.equals("7")) {
					td.setDate_of_admitted(format.parse(date_admitted));
					}
					
					td.setDate_of_auth(format.parse(date_of_auth));
					td.setDate_of_birth(format.parse(date_of_birth));
					td.setDate_of_tos(format.parse(tos));
					td.setFitness_deployment(fitness_deployment);
					td.setFitness_status(Integer.parseInt(fitness_status));
					td.setGender(sex);
					td.setMicrochip_no(microchip_no);
					td.setName(name_of_dog);
					td.setSource(Integer.parseInt(source));
					td.setSpecialization(Integer.parseInt(specialization));
					td.setType_of_dog(Integer.parseInt(type_dog));
					td.setUnit_sus_no(roleSusNo);
					
					

					int did = (Integer) sessionHQL.save(td);

					model.put("msg", "Data Saved Successfully.");
					model.put("id", did);
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				catch (Exception e) {
					sessionHQL.getTransaction().rollback();
					return null;
				} finally {
					sessionHQL.close();
				}
			}
		}
	}
	
// Search Animal ----------------------------------------- Pranay 21-03-25

	@RequestMapping(value = "/Search_Animal_Census", method = RequestMethod.GET)
	public ModelAndView Search_Animal_Census(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("status", "0");
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
	 if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", orbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		return new ModelAndView("search_animal_censusTiles");
	}

	 @RequestMapping(value = "/admin/getSearch_animal_census", method = RequestMethod.POST)
		public ModelAndView getSearch_animal_census(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "army_no1", required = false) String army_no,
				@RequestParam(value = "status1", required = false) String status,
				@RequestParam(value = "unit_name1", required = false) String unit_name,
			    @RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no){

		 	unit_name = unit_name.replace("&#40;", "(");
			unit_name = unit_name.replace("&#41;", ")");

			 String roleid = session.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				  if(unit_sus_no!="") {
               if (!validation.SusNoLength(unit_sus_no)) {
                                 Mmap.put("msg", validation.SusNoMSG);
                                 return new ModelAndView("redirect:Search_Animal_Census");
                         }
         	  if (!validation.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", validation.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
       }
       
       if(unit_name!="") {
     	  if (!validation.isUnit(unit_name)) {
               Mmap.put("msg", " Unit Name " + validation.isUnitMSG);
               return new ModelAndView("redirect:Search_Animal_Census");
             }
}
       
          if(army_no!="") {
          if (!validation.ArmyNoLength(army_no)) {
                                 Mmap.put("msg", validation.ArmyNoMSG);
                                 return new ModelAndView("redirect:Search_Animal_Census");
                         }
          if (!validation.isOnlyAlphabetNumericSpaceNot(army_no)) {
					Mmap.put("msg", validation.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
			  
               if (army_no.length() < 2 || army_no.length() > 5) {
                                 Mmap.put("msg", "Army No Should Contain Maximum 5 Character");
                                 return new ModelAndView("redirect:Search_Animal_Census");
                         }
          } 

				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",orbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					
					
				}
									
				Mmap.put("army_no1", army_no);
				Mmap.put("status1", status);
				Mmap.put("unit_name1", unit_name);
				Mmap.put("unit_sus_no1", unit_sus_no);
				return new ModelAndView("search_animal_censusTiles");
			
		}
	 
	// update census data ----------------------------------------- Pranay 21-03-25
	 @RequestMapping(value = "/Edit_animal_censusUrl", method = RequestMethod.POST)
		public ModelAndView Edit_animal_censusUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "army_no9", required = false) String army_no,
				@RequestParam(value = "status9", required = false) String status,
				@RequestParam(value = "unit_name9", required = false) String unit_name,
			    @RequestParam(value = "unit_sus_no9", required = false) String unit_sus_no,
			    @RequestParam(value = "anml_type9", required = false) String anml_type,
				Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit,HttpSession sessionUserId) {	
		 
			TB_ANIMAL_CENSUS_DTLS animaldetails = anmlDao.getdogByid(Integer.parseInt(updateid));
	
			 String roleid = sessionEdit.getAttribute("roleid").toString();
			 
			    Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			   
				String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
				String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
				if (roleAccess.equals("Unit")) {
					Mmap.put("unit_name", orbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionEdit).get(0));
				}
			
			
			Mmap.put("edit_animal_census_cmnd", animaldetails);
			Mmap.put("anml_type8", anml_type);
			Mmap.put("roleSusNo", roleSusNo);
			Mmap.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			Mmap.put("getTypeOfDogList", ani.getTypeOfDogList(sessionEdit));
			Mmap.put("getsplzList", ani.getsplzList1(sessionEdit));
			Mmap.put("msg", msg);
			Mmap.put("getclrList", ani.getclrList(anml_type, sessionEdit));
			Mmap.put("getbreedList", ani.getbreedList(anml_type, sessionEdit));
			Mmap.put("getFitnessStatusList", ani.getFitnessStatusList(anml_type, sessionEdit));
			Mmap.put("getSourceList", ani.getSourceList(anml_type, sessionEdit));
			if (roleAccess.equals("Unit")) {
				Mmap.put("unit_name", orbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
			}
			Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
			return new ModelAndView("edit_animal_censusTiles");
		}
	 
	 @RequestMapping(value = "/edit_animal_census_act", method = RequestMethod.POST)
		public ModelAndView edit_animal_census_act(MultipartHttpServletRequest mul,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			String username = session.getAttribute("username").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        int id = Integer.parseInt(request.getParameter("dog_id"));
	      
	        String dog_id=request.getParameter("dog_id");
	        
	        TB_ANIMAL_CENSUS_DTLS td =new TB_ANIMAL_CENSUS_DTLS();
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			

			String fname1 = "";
			String fname2 = "";
			String fname3 = "";
			String extension = "";

			String anml_type = request.getParameter("anml_type").trim();
			
			String animal_purchase_cost = request.getParameter("animal_purchase_cost").trim();
			String army_num = request.getParameter("army_num").toString().trim();
			String type_dog = request.getParameter("type_dog").trim();
			String name_of_dog = request.getParameter("name_of_dog").toString().trim();
			String specialization = request.getParameter("specialization").toString().trim();
			String breed = request.getParameter("breed").toString().trim();
			String colour = request.getParameter("colour").toString().trim();
			String source = request.getParameter("source").toString().trim();
			String sex = request.getParameter("sex").toString().trim();
			String date_of_birth = request.getParameter("date_of_birth").toString().trim();
			String details_of_sire = request.getParameter("details_of_sire").toString().trim();
			String details_of_dam = request.getParameter("details_of_dam").toString().trim();
			String armyno_of_sire = request.getParameter("armyno_of_sire").toString().trim();
			String armyno_of_dam = request.getParameter("armyno_of_dam").toString().trim();
			String microchip_no = request.getParameter("microchip_no").toString().trim();
			String fitness_status = request.getParameter("fitness_status").toString().trim();
			String date_admitted = request.getParameter("date_admitted").toString().trim();
			String fitness_deployment = request.getParameter("fitness_deployment").toString().trim();
			String tos = request.getParameter("tos").toString().trim();
			String date_of_auth = request.getParameter("date_of_auth").toString().trim();
			String auth_no = request.getParameter("auth_no").toString().trim();
			
			String unit_name = request.getParameter("unit_name");
			String sus_no = request.getParameter("sus_no").trim();

			String roleAccess = session.getAttribute("roleAccess").toString();
			if (!roleAccess.equals("Unit")) {

				if(sus_no == null || sus_no.equals("null") || sus_no.equals("")) {
					model.put("msg", "Please Enter Unit Sus No.");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
				if (!validation.isOnlyAlphabetNumeric(sus_no)) {
					model.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
				if (!validation.SusNoLength(sus_no)) {
					model.put("msg", validation.SusNoMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}
				else {
					roleSusNo = sus_no;
				}

				if(unit_name == null || unit_name.equals("null") || unit_name.equals("")) {
					model.put("msg", "Please Enter Unit Name.");
					return new ModelAndView("redirect:Search_Animal_CensusL");
				}

			}

			String cost = "";
			String value = "";

			cost = animal_purchase_cost.replaceAll(",", "");
			

			Pattern pattern = Pattern.compile(".*[^0-9].*");
			Date today_dt = new Date();
			

			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString()); // 2 MB
			MultipartFile front_image_anml = mul.getFile("front_image_anml");
			MultipartFile left_image_anml = mul.getFile("left_image_anml");
			MultipartFile right_image_anml = mul.getFile("right_image_anml");
			if (front_image_anml.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:Search_Animal_Census");
			}
			if (left_image_anml.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:Search_Animal_Census");
			}
			if (right_image_anml.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:Search_Animal_Census");
			}
			

			else if (fitness_status == "3" ||  fitness_status.equals("3")) {
				if (request.getParameter("date_admitted").equals("")) {
					model.put("msg", "Please Select Date Admitted.");
					return new ModelAndView("redirect:Search_Animal_Census");
				} else {
					if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						return new ModelAndView("redirect:Search_Animal_Census");
					}
				}
			}

			else if (fitness_status == "7" ||  fitness_status.equals("7")) {

				if (request.getParameter("date_admitted").equals("")) {
					model.put("msg", "Please Select Date Admitted.");
					return new ModelAndView("redirect:Search_Animal_Census");
				} else {
					if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						return new ModelAndView("redirect:Search_Animal_Census");
					}
				}

			}


			else if (tos.equals("")) {
				if (format.parse(request.getParameter("tos")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("tos")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
			}

			/*if (!pattern.matcher(cost).matches() == false) {
				model.put("msg", "Please Enter valid Purchase Cost");
				return new ModelAndView("redirect:Search_Animal_Census");
			}*/


			if (request.getParameter("anml_type") == null) {
				model.put("msg", "Please Select Animal Type");
				return new ModelAndView("redirect:Search_Animal_Census");

			} else {

				if (anml_type.equals("Army Dog")) {
					
					if (army_num.equals("")) {
						model.put("msg", "Please Enter Army No");
						return new ModelAndView("redirect:Search_Animal_Census");
					}

					else if (validation.check20Length(army_num) == false) {
						model.put("msg", validation.army_numMSG);
						return new ModelAndView("redirect:Search_Animal_Census");
					}

					else if (type_dog== "0" || type_dog.equals("0")) {
						model.put("msg", "Please Select Type of Dog");
						return new ModelAndView("redirect:Search_Animal_Census");
					}

					else if (name_of_dog.equals("")) {
						model.put("msg", "Please Enter Name of Dog");
						return new ModelAndView("redirect:Search_Animal_Census");
					}

					else if (validation.check35Length(name_of_dog) == false) {
						model.put("msg", validation.name_of_dogMSG);
						return new ModelAndView("redirect:Search_Animal_Census");
					}

					else if (specialization == "0" || specialization.equals("0")) {
						model.put("msg", "Please Select Specialisation");
						return new ModelAndView("redirect:Search_Animal_Census");
					}

				} 

				if (breed == "0" || breed.equals("0") ) {
					model.put("msg", "Please Select Breed");
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (colour == "0" || colour.equals("0")) {
					model.put("msg", "Please Select Colour");
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (request.getParameter("sex") == null) {
					model.put("msg", "Please Select Sex");
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (source == "0" || source.equals("0")) {
					model.put("msg", "Please Select Source");
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (date_of_birth.equals("")) {
					model.put("msg", "Please Select  Date of Birth");
					return new ModelAndView("redirect:Search_Animal_Census");
				}
				
			
				 if (format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:Search_Animal_Census");
				}


				else if (validation.check35Length(details_of_sire) == false) {
					model.put("msg", validation.details_of_sireMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (validation.check35Length(details_of_dam) == false) {
					model.put("msg", validation.details_of_damMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				else if (validation.check20Length(microchip_no) == false) {
					model.put("msg", validation.microchip_noMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}


				else if (validation.check255Length(fitness_deployment) == false) {
					model.put("msg", validation.fitness_deploymentMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}

				/*else if (cost.equals("")) {
					model.put("msg", "Please Enter Either Animal Purchase Cost ");
					return new ModelAndView("redirect:Search_Animal_Census");
				}*/

				else if (validation.check10Length(cost) == false) {
					model.put("msg", validation.animal_purchase_costMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}


				else if (validation.checkAnimalMasterLength(auth_no) == false) {
					model.put("msg", validation.authorityMSG);
					return new ModelAndView("redirect:Search_Animal_Census");
				}


				else {

					try {

						if (!front_image_anml.isEmpty()) {
							String image_anml1Ext = FilenameUtils.getExtension(front_image_anml.getOriginalFilename());
							if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:Search_Animal_Census");
							}
							DateWithTimeStampController timestamp = new DateWithTimeStampController();
							
							try {
								byte[] bytes1 = front_image_anml.getBytes();
								CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
								if(fileValidation.check_JPEG_File(bytes1) || fileValidation.check_PNG_File(bytes1)) {
									String tmsFilePath = session.getAttribute("animalFilePath").toString();
									File dir = new File(tmsFilePath);
									if (!dir.exists()) {
										dir.mkdirs();
									}
									String filename1 = front_image_anml.getOriginalFilename();
									int i = filename1.lastIndexOf('.');
									if (i >= 0) {
										extension = filename1.substring(i + 1);
									}
									fname1 = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_FRONTFACE_ANIMALDOC." + extension;
									System.err.println("fname1:"+fname1);
									if (validation.checkImageAnmlLength(fname1) == false) {
										model.put("msg", validation.image_anmlMSG);
										return new ModelAndView("redirect:Search_Animal_Census");
									}
									File serverFile1 = new File(fname1);
									BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(serverFile1));
									stream1.write(bytes1);
									stream1.close();
									/*td.setUpload_img_path(fname);*/
								}else {
									model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
									return new ModelAndView("redirect:Search_Animal_Census");
								}

							} catch (Exception e) {
							}
						}
						if (!left_image_anml.isEmpty()) {
							String image_anml1Ext = FilenameUtils.getExtension(left_image_anml.getOriginalFilename());
							if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:Search_Animal_Census");
							}
							DateWithTimeStampController timestamp = new DateWithTimeStampController();
							
							try {
								byte[] bytes2 = left_image_anml.getBytes();
								CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
								if(fileValidation.check_JPEG_File(bytes2) || fileValidation.check_PNG_File(bytes2)) {
									String tmsFilePath = session.getAttribute("animalFilePath").toString();
									File dir = new File(tmsFilePath);
									if (!dir.exists()) {
										dir.mkdirs();
									}
									String filename2 = left_image_anml.getOriginalFilename();
									int i = filename2.lastIndexOf('.');
									if (i >= 0) {
										extension = filename2.substring(i + 1);
									}
									fname2 = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_LEFTFACE_ANIMALDOC." + extension;
									System.err.println("fname2:"+fname2);
									if (validation.checkImageAnmlLength(fname2) == false) {
										model.put("msg", validation.image_anmlMSG);
										return new ModelAndView("redirect:Search_Animal_Census");
									}
									File serverFile2 = new File(fname2);
									BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(serverFile2));
									stream2.write(bytes2);
									stream2.close();
									/*td.setUpload_img_path(fname);*/
								}else {
									model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
									return new ModelAndView("redirect:Search_Animal_Census");
								}

							} catch (Exception e) {
							}
						}
						if (!right_image_anml.isEmpty()) {
							String image_anml1Ext = FilenameUtils.getExtension(right_image_anml.getOriginalFilename());
							if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("jpeg".toUpperCase()) && !image_anml1Ext.toUpperCase().equals("png".toUpperCase())) {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:Search_Animal_Census");
							}
							DateWithTimeStampController timestamp = new DateWithTimeStampController();
							
							try {
								byte[] bytes3 = right_image_anml.getBytes();
								CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
								if(fileValidation.check_JPEG_File(bytes3) || fileValidation.check_PNG_File(bytes3)) {
									String tmsFilePath = session.getAttribute("animalFilePath").toString();
									File dir = new File(tmsFilePath);
									if (!dir.exists()) {
										dir.mkdirs();
									}
									String filename3 = right_image_anml.getOriginalFilename();
									int i = filename3.lastIndexOf('.');
									if (i >= 0) {
										extension = filename3.substring(i + 1);
									}
									fname3 = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_RIGHTFACE_ANIMALDOC." + extension;
									System.err.println("fname3:"+fname3);
									if (validation.checkImageAnmlLength(fname3) == false) {
										model.put("msg", validation.image_anmlMSG);
										return new ModelAndView("redirect:Search_Animal_Census");
									}
									File serverFile3 = new File(fname3);
									BufferedOutputStream stream3 = new BufferedOutputStream(new FileOutputStream(serverFile3));
									stream3.write(bytes3);
									stream3.close();
									/*td.setUpload_img_path(fname);*/
								}else {
									model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
									return new ModelAndView("redirect:Search_Animal_Census");
								}

							} catch (Exception e) {
							}
						}
						
						try {
							
							Query q0 = session1.createQuery("select count(id) from TB_ANIMAL_CENSUS_DTLS where army_no=:army_no and id !=:id");
							q0.setParameter("army_no", army_num);  
							q0.setParameter("id", id); 
							
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								
								String q="";
								
								/*if (fitness_status == "3" ||  fitness_status.equals("3") || fitness_status == "7" ||  fitness_status.equals("7")) {
									q+=", date_of_admitted=:date_of_admitted";
								}*/
								
								
								 String hql ="UPDATE TB_ANIMAL_CENSUS_DTLS\r\n" + 
								 		"	SET  animal_purchase_cost=:animal_purchase_cost, animal_type=:animal_type, army_no=:army_no, auth_letter_no=:auth_letter_no, breed=:breed, color=:color, created_by=:created_by, created_date=:created_date, dam_armyno=:dam_armyno, "
								 		+ "dam_name=:dam_name, date_of_auth=:date_of_auth, date_of_birth=:date_of_birth, date_of_tos=:date_of_tos, fitness_deployment=:fitness_deployment, fitness_status=:fitness_status, gender=:gender,"
								 		+ " microchip_no=:microchip_no, reject_remarks=:reject_remarks, date_of_admitted=:date_of_admitted, name=:name, sire_armyno=:sire_armyno, sire_name=:sire_name, source=:source, specialization=:specialization, status=:status, type_of_dog=:type_of_dog, unit_sus_no=:unit_sus_no, "
								 		+ "front_img_path=:front_img_path, left_img_path=:left_img_path, right_img_path=:right_img_path\r\n"  
								 		+ q+"	where id=:id";
		
						    	  Query query = session1.createQuery(hql)
						    			  .setString("army_no", army_num)
						    			  .setInteger("status",0)
						    	  		.setString("created_by",username)
						    	  		.setTimestamp("created_date", date)
						    	  		.setString("animal_purchase_cost",cost)
						    	  		.setString("animal_type",anml_type)
						    	  		.setString("auth_letter_no",auth_no)
						    	  		.setInteger("breed",Integer.parseInt(breed))
						    	  		.setInteger("color",Integer.parseInt(colour))
						    	  		.setString("dam_armyno",armyno_of_dam)
						    	  		.setString("dam_name",details_of_dam)
						    	  		.setString("sire_armyno",armyno_of_sire)
						    	  		.setString("sire_name",details_of_sire)
						    	  		.setTimestamp("date_of_auth",format.parse(date_of_auth))
						    	  		.setTimestamp("date_of_birth",format.parse(date_of_birth))
						    	  		.setTimestamp("date_of_tos",format.parse(tos))
						    	  		.setString("fitness_deployment",fitness_deployment)
						    	  		.setInteger("fitness_status",Integer.parseInt(fitness_status))
						    	  		.setString("gender",sex)
						    	  		.setString("microchip_no",microchip_no)
						    	  		.setString("name",name_of_dog)
						    	  		.setInteger("source",Integer.parseInt(source))
						    	  		.setInteger("specialization",Integer.parseInt(specialization))
						    	  		.setInteger("type_of_dog",Integer.parseInt(type_dog))
						    	  		.setString("name",name_of_dog)
						    	  		.setString("unit_sus_no",roleSusNo)
						    	  		.setString("reject_remarks", null)
										.setInteger("id",id);
										if (fitness_status == "3" ||  fitness_status.equals("3") || fitness_status == "7" ||  fitness_status.equals("7")) {
						    	  			query.setTimestamp("date_of_admitted",format.parse(date_admitted));
										}else {
											query.setTimestamp("date_of_admitted",null);
										}
										if (front_image_anml.isEmpty()) {
											String filePath = anmlDao.getanimalIdentityImagePath(dog_id,"front_img_path");
											query.setString("front_img_path",filePath);
										}else {
											query.setString("front_img_path",fname1);
										}
										if (left_image_anml.isEmpty()) {
											String filePath = anmlDao.getanimalIdentityImagePath(dog_id,"left_img_path");
											query.setString("left_img_path",filePath);
										}else {
											query.setString("left_img_path",fname2);
										}
										if (right_image_anml.isEmpty()) {
											String filePath = anmlDao.getanimalIdentityImagePath(dog_id,"right_img_path");
											query.setString("right_img_path",filePath);
										}else {
											query.setString("right_img_path",fname3);
										}
						    	  
						                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
							}
							model.put("msg", msg);
						}catch (Exception e) {
						}
					}

					catch (Exception e) {
						session1.getTransaction().rollback();
						return null;
					} finally {
						tx.commit();
						session1.close();
					}
				}
			}
			return new ModelAndView("redirect:Search_Animal_Census");
		}
	 
	// Delete Animal ----------------------------------------- Pranay 21-03-25
	 @RequestMapping(value = "/admin/Delete_census_Url" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_census_Url(@ModelAttribute("id3") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String roleid = sessionA.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			List<String> liststr = new ArrayList<String>();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_ANIMAL_CENSUS_DTLS where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				
				tx.commit();
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:Search_Animal_Census");
		}
	 
	 @RequestMapping(value = "/Get_census_Data", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Get_census_Data(@ModelAttribute("idj") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model, Authentication authentication) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			ArrayList<ArrayList<String>> list = anmlDao.GetAllcensus_Details(id);
			model.put("list", list);

			tx.commit();
			return new ModelAndView("view_animal_censusTiles");
		}
	 
	 @RequestMapping(value = "/View_census_Data", method = RequestMethod.POST)
		public @ResponseBody ModelAndView View_census_Data(@ModelAttribute("idv") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model, Authentication authentication) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			ArrayList<ArrayList<String>> list = anmlDao.GetAllcensus_Details(id);
			model.put("list", list);

			tx.commit();
			return new ModelAndView("view_animal_censusTiles");
		}
	 
	// Approve Animal ----------------------------------------- Pranay 24-03-25
	 
	 @RequestMapping(value = "/Approve_animal_census", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Approve_animal_census(@ModelAttribute("id3") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "status", required = false) int status,
				@RequestParam(value = "modified_by", required = false) String modified_by,
				@RequestParam(value = "modified_date", required = false) String modified_date,
				Authentication authentication) {
			 String roleid = sessionA.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
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
			
			
			TB_ANIMAL_CENSUS_DTLS BAN ;
			
			BAN = (TB_ANIMAL_CENSUS_DTLS)sessionHQL.get(TB_ANIMAL_CENSUS_DTLS.class, id); 
			
			/*TB_ANIMAL_STR_INCR_DECR po = new TB_ANIMAL_STR_INCR_DECR();
			
		    po.setTo_sus_no(BAN.getUnit_sus_no());
			po.setDt_of_sos(BAN.getDate_of_tos());
			po.setDt_of_tos(BAN.getDate_of_tos());
			po.setCreated_by(BAN.getCreated_by());
			po.setCreated_date(BAN.getCreated_date());
			po.setModified_by(username);
            po.setModified_date(new Date());
			po.setCensus_id(BAN.getId());
			po.setStatus(1);
			po.setScenario("1");*/
			
			String Count_str = CountOfCensus_idApprove(String.valueOf(BAN.getId()) , "TB_ANIMAL_STR_INCR_DECR") ;
			
			 if(Integer.parseInt(Count_str) == 0) {
				 
				 TB_ANIMAL_STR_INCR_DECR po = new TB_ANIMAL_STR_INCR_DECR();
					
				    po.setTo_sus_no(BAN.getUnit_sus_no());
					po.setDt_of_sos(BAN.getDate_of_tos());
					po.setDt_of_tos(BAN.getDate_of_tos());
					po.setCreated_by(BAN.getCreated_by());
					po.setCreated_date(BAN.getCreated_date());
					po.setModified_by(username);
		            po.setModified_date(new Date());
					po.setCensus_id(BAN.getId());
					po.setStatus(1);
					po.setScenario("1");
					
					sessionHQL.save(po);
					sessionHQL.flush();
					sessionHQL.clear();
					
			 }
			 else if (Integer.parseInt(Count_str) == 1 ) {
				 
				 
				 String hql_post = "update TB_ANIMAL_STR_INCR_DECR set to_sus_no=:unit_sus_no,dt_of_tos=:date_of_tos,dt_of_sos=:date_of_tos,modified_by=:modified_by,modified_date=:modified_date "
							+ "where census_id=:census_id and from_sus_no is null ";
					Query query_post = sessionHQL.createQuery(hql_post).setParameter("unit_sus_no", BAN.getUnit_sus_no()).setTimestamp("date_of_tos", BAN.getDate_of_tos())
							.setString("modified_by", username).setTimestamp("modified_date", BAN.getModified_date())
							.setInteger("census_id", BAN.getId());
					msg = query_post.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
					
					
			 }
			 
			
			String hqlUpdate = "update TB_ANIMAL_CENSUS_DTLS set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

			
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
		model.put("msg", liststr.get(0));
			String roleType = sessionA.getAttribute("roleType").toString();
			return new ModelAndView("redirect:Search_Animal_Census");
		}
	
	// Reject Animal ----------------------------------------- Pranay 24-03-25
	 @RequestMapping(value = "/Reject_animal_census", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Reject_animal_census(@ModelAttribute("id4") int id, BindingResult result,
				HttpServletRequest request, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, 
				@RequestParam(value = "reject_remarks_jc", required = false) String reject_remarks,Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			String roleid = sessionA.getAttribute("roleid").toString();
		    Boolean val = roledao.ScreenRedirect("Search_Animal_Census", roleid);
			if (val == false) {
			return new ModelAndView("AccessTiles");
		    }

			if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String username = sessionA.getAttribute("username").toString();

			String hqlUpdate = "update TB_ANIMAL_CENSUS_DTLS set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks  where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("modified_by", username).setString("reject_remarks", reject_remarks)
					.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

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
			return new ModelAndView("redirect:Search_Animal_Census");
		}
	
	@RequestMapping(value = "/GetSearch_censusCount_animal", method = RequestMethod.POST)
	public @ResponseBody long GetSearch_censusCount_animal(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String unit_sus_no,String unit_name,String personnel_no,
			String status) throws SQLException {
		
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		
		return anmlDao.GetSearch_censusCount_animal(Search, orderColunm, orderType, sessionUserId, unit_sus_no, unit_name,personnel_no,
				 status,roleType);
	}
	

	
	@RequestMapping(value = "/GetSearch_censusdata_animal", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetSearch_censusdata_animal(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String unit_sus_no,String unit_name,String personnel_no,
			String status) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		
		return anmlDao.GetSearch_censusdata_animal(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_sus_no, unit_name,personnel_no,
				 status,roleType);
	}
	
	
	
	
	@RequestMapping(value = "/checkDetailsOfanimaldogarmy", method = RequestMethod.POST)
	public @ResponseBody List<TB_ANIMAL_CENSUS_DTLS> checkDetailsOfanimaldogarmy(String army_num) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct army_no from TB_ANIMAL_CENSUS_DTLS where UPPER(army_no)=:army_num");
		q.setParameter("army_num", army_num);
		@SuppressWarnings("unchecked")
		List<TB_ANIMAL_CENSUS_DTLS> list = (List<TB_ANIMAL_CENSUS_DTLS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getArmy_noListAnimal", method = RequestMethod.POST)
	public @ResponseBody List<String> getArmy_noListApproved(String army_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q= null;
		
		if(roleAccess.equals("Unit")){
			
			 q = sessionHQL.createQuery("select distinct army_no from TB_ANIMAL_CENSUS_DTLS  where unit_sus_no=:roleSusNo and upper(army_no)  like :army_no  order by army_no").setMaxResults(10);
			  q.setParameter("roleSusNo", roleSusNo);    
		}
		else
		{
			 
			 q = sessionHQL.createQuery("select distinct army_no from TB_ANIMAL_CENSUS_DTLS  where upper(army_no)  like :army_no  order by army_no").setMaxResults(10);
						
		}
		q.setParameter("army_no", army_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")	
		List<String> list = (List<String>) q.list();
		tx.commit();
		

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
					encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
			return FinalList;
			
	}
	
	@RequestMapping(value = "/animalimgConvertpath", method = RequestMethod.GET)
	public void animalimgConvertpath(@ModelAttribute("i_id") String i_ch_id,@ModelAttribute("fildname") String fildname, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String i_id = i_ch_id;		

		String filePath = anmlDao.getanimalIdentityImagePath(i_id,fildname);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {
				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}

		} catch (Exception ex) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();

		}

	}
	public String CountOfCensus_idApprove(String census_id ,String TB_MODEL) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery("select count(census_id) from "+ TB_MODEL +" where census_id=:census_id");		
			
		
			q1.setParameter("census_id", new Integer(census_id));
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
	
	
	


	
	
}
