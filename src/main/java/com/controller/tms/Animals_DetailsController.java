package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
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
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Tms_AnimalDao;
import com.dao.tms.Tms_AnimalDaoImpl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_ANIMAL_HISTORY_DETAILS;
import com.models.TMS_TB_MISO_SOURCE_MASTER;
import com.models.TMS_TB_UE_MASTER;
import com.models.Tbl_CodesForm;
import com.models.Tms_animals_details;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Animals_DetailsController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	Blood_Group_Controller b = new Blood_Group_Controller();
	
	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired
	Tms_AnimalDao AnimalDao = new Tms_AnimalDaoImpl();
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat orbt = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	ValidationController validation = new ValidationController();


	/////////// Add Animal Details /////////

	@RequestMapping(value = "/add_animal_details", method = RequestMethod.GET)
	public ModelAndView adnimal_details(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String anml_type, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_animal_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(session));
		Mmap.put("getsplzList", getsplzList(session));
		Mmap.put("msg", msg);
		return new ModelAndView("animal_detailsTile", "add_animal_details_cmnd", new Tms_animals_details());
	}

	@RequestMapping(value = "/add_animal_details1", method = RequestMethod.POST)
	public ModelAndView add_animal_details1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type8", required = false) String anml_type) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_animal_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("anml_type8", anml_type);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(session));
		Mmap.put("getsplzList", getsplzList(session));
		Mmap.put("msg", msg);
		Mmap.put("getclrList", getclrList(anml_type, session));
		Mmap.put("getbreedList", getbreedList(anml_type, session));
		Mmap.put("getFitnessStatusList", getFitnessStatusList(anml_type, session));
		Mmap.put("getSourceList", getSourceList(anml_type, session));
		return new ModelAndView("animal_detailsTile");
	}

	@RequestMapping(value = "/add_animal_details_act", method = RequestMethod.POST)
	public ModelAndView add_animal_details_act(@ModelAttribute("add_animal_details_cmnd") Tms_animals_details td,
			@RequestParam(value = "image_anml", required = false) MultipartFile image_anml, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, ModelMap model, HttpSession session)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_animal_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		String fname = "";
		String extension = "";

		String an = request.getParameter("anml_type");

		String pcost = request.getParameter("animal_purchase_cost");
		String bvalue = request.getParameter("animal_present_cost");

		String cost = "";
		String value = "";

		cost = pcost.replaceAll(",", "");
		value = bvalue.replaceAll(",", "");

		Pattern pattern = Pattern.compile(".*[^0-9].*");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString()); // 2 MB
		if (image_anml.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			return new ModelAndView("redirect:add_animal_details");
		}
		if (td.getDisptrans() != null) {
			if (td.getDisptrans().equals("disp")) {
				if (request.getParameter("dis_date").equals("")) {
					model.put("msg", "Please Select Disposal Date.");
					return new ModelAndView("redirect:add_animal_details");
				} else {
					if (format.parse(request.getParameter("dis_date")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("dis_date")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						return new ModelAndView("redirect:add_animal_details");
					}
				}

				if (request.getParameter("disposal").equals("0")) {
					model.put("msg", "Please Select Disposal.");
					return new ModelAndView("redirect:add_animal_details");
				}
			} else {
				if (request.getParameter("issue_date").equals("")) {
					model.put("msg", "Please Select Issue Date.");
					return new ModelAndView("redirect:add_animal_details");
				}

				else {
					if (format.parse(request.getParameter("issue_date")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("issue_date")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						return new ModelAndView("redirect:add_animal_details");
					}
				}

				if (request.getParameter("issue_to_unit_name").equals("")) {
					model.put("msg", "Please Enter Issue to Unit.");
					return new ModelAndView("redirect:add_animal_details");
				}
			}
		}

		else if (td.getFitness_status() == 3) {
			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				return new ModelAndView("redirect:add_animal_details");
			} else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:add_animal_details");
				}
			}
		}

		else if (td.getFitness_status() == 5) {

			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				return new ModelAndView("redirect:add_animal_details");
			} else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:add_animal_details");
				}
			}

		}

		else if (!td.getAward_date().equals("")) {
			if (format.parse(request.getParameter("award_date")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("award_date")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}
		}

		else if (!td.getTos().equals("")) {
			if (format.parse(request.getParameter("tos")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("tos")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}
		}

		else if (!td.getTors().equals("")) {
			if (format.parse(request.getParameter("tors")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("tors")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}
		}

		else if (!td.getSos().equals("")) {
			if (format.parse(request.getParameter("sos")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("sos")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}
		}

		else if (!td.getSors().equals("")) {
			if (format.parse(request.getParameter("sors")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("sors")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}
		}

		if (!pattern.matcher(cost).matches() == false) {
			model.put("msg", "Please Enter valid Purchase Cost");
			return new ModelAndView("redirect:add_animal_details");
		}

		else if (!pattern.matcher(value).matches() == false) {
			model.put("msg", "Please Enter valid Book Value");
			return new ModelAndView("redirect:add_animal_details");
		}

		if (request.getParameter("anml_type") == null) {
			model.put("msg", "Please Select Animal Type");
			return new ModelAndView("redirect:add_animal_details");

		} else {

			if (td.getSus_no().equals("")) {
				model.put("msg", "Please Select Sus No");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.sus_noLength(td.getSus_no()) == false) {
				model.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			/*
			 * else if (td.getUnit_name().equals("")) { model.put("msg",
			 * "Please Select Unit Name"); return new
			 * ModelAndView("redirect:add_animal_details"); }
			 * 
			 * else if(validation.checkUnit_nameLength(td.getUnit_name()) == false){
			 * model.put("msg",validation.unit_nameMSG); return new
			 * ModelAndView("redirect:add_animal_details"); }
			 */

			if (td.getAnml_type().equals("Army Dog")) {
				String army_num = request.getParameter("army_num").toString();
				if (army_num.equals("")) {
					model.put("msg", "Please Enter Army No");
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (validation.check20Length(td.getArmy_num()) == false) {
					model.put("msg", validation.army_numMSG);
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (checkDetailsOfanmlarmy(army_num).size() != 0) {
					model.put("msg", "Army Number is Already Exist");
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (td.getType_dog() == 0) {
					model.put("msg", "Please Select Type of Dog");
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (td.getName_of_dog().equals("")) {
					model.put("msg", "Please Enter Name of Dog");
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (validation.check35Length(td.getName_of_dog()) == false) {
					model.put("msg", validation.name_of_dogMSG);
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (td.getSpecialization() == 0) {
					model.put("msg", "Please Select Specialisation");
					return new ModelAndView("redirect:add_animal_details");
				}

			} else {

				String type_equines1 = request.getParameter("type_equines1").toString();

				String remount_no = request.getParameter("remount_no").toString();

				if (remount_no.equals("")) {
					model.put("msg", "Please Enter Remount No");
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (validation.checkRemountNoLength(td.getRemount_no()) == false) {
					model.put("msg", validation.remount_noMSG);
					return new ModelAndView("redirect:add_animal_details");
				}

				else if (checkDetailsOfanmlremount(remount_no).size() != 0) {
					model.put("msg", "Remount Number is Already Exist");
					return new ModelAndView("redirect:adnimal_details");
				}

				else if (type_equines1.equals("0")) {
					model.put("msg", "Please Select Type of Equines");
					return new ModelAndView("redirect:add_animal_details");
				}
			}

			if (td.getBreed() == 0) {
				model.put("msg", "Please Select Breed");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (td.getColour() == 0) {
				model.put("msg", "Please Select Colour");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (request.getParameter("sex") == null) {
				model.put("msg", "Please Select Sex");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (td.getSource() == 0) {
				model.put("msg", "Please Select Source");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (td.getDate_of_birth().equals("")) {
				model.put("msg", "Please Select  Date of Birth");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getDate_of_birth()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkageLength(td.getAge()) == false) {
				model.put("msg", validation.ageMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check35Length(td.getDetails_of_sire()) == false) {
				model.put("msg", validation.details_of_sireMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check35Length(td.getDetails_of_dam()) == false) {
				model.put("msg", validation.details_of_damMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check20Length(td.getMicrochip_no()) == false) {
				model.put("msg", validation.microchip_noMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getDate_admitted()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check255Length(td.getFitness_deployment()) == false) {
				model.put("msg", validation.fitness_deploymentMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (td.getAnimal_purchase_cost().equals("") && td.getAnimal_present_cost().equals("")) {
				model.put("msg", "Please Enter Either Animal Purchase Cost or Book Value");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (!td.getAnimal_purchase_cost().equals("") && !td.getAnimal_present_cost().equals("")) {
				model.put("msg", "Please Enter Either Animal Purchase Cost or Book Value");
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check10Length(td.getAnimal_purchase_cost()) == false) {
				model.put("msg", validation.animal_purchase_costMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check10Length(td.getAnimal_present_cost()) == false) {
				model.put("msg", validation.animal_present_costMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkUnit_nameLength(td.getIssue_to_unit_name()) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getDis_date()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getIssue_date()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check255Length(td.getDisposal_remarks()) == false) {
				model.put("msg", validation.disposal_remarksMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkUnit_nameLength(td.getUnit_where_awarded()) == false) {
				model.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkAnimalMasterLength(td.getAuthority()) == false) {
				model.put("msg", validation.authorityMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getAward_date()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.check255Length(td.getAwared_remarks()) == false) {
				model.put("msg", validation.awared_remarksMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getTos()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getTors()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getSos()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else if (validation.checkDateLength(td.getSors()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:add_animal_details");
			}

			else {

				if (request.getParameter("anml_type").toString().equals("Army Dog")) {
					td.setSpecialization(Integer.parseInt(request.getParameter("specialization").toString()));
					td.setType_dog(Integer.parseInt(request.getParameter("type_dog")));
					td.setType_equines(0);
					td.setSource(Integer.parseInt(request.getParameter("source")));

				} else {
					td.setSpecialization(0);
					td.setType_dog(0);
					td.setType_equines(Integer.parseInt(request.getParameter("type_equines1")));
					td.setSource(Integer.parseInt(request.getParameter("source")));
				}

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				sessionHQL.beginTransaction();

				try {

					if (!image_anml.isEmpty()) {
						String image_anml1Ext = FilenameUtils.getExtension(image_anml.getOriginalFilename());
						if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase())
								&& !image_anml1Ext.equals("jpeg".toUpperCase())
								&& !image_anml1Ext.equals("png".toUpperCase())) {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
							return new ModelAndView("redirect:add_animal_details");
						}
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						// code modify by Paresh on 05/05/2020
						try {
							byte[] bytes = image_anml.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
								String tmsFilePath = session.getAttribute("tmsFilePath").toString();
								File dir = new File(tmsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								String filename = image_anml.getOriginalFilename();
								int i = filename.lastIndexOf('.');
								if (i >= 0) {
									extension = filename.substring(i + 1);
								}
								fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()+ "_" + userid + "_TMSDOC." + extension;
	
								if (validation.checkImageAnmlLength(fname) == false) {
									model.put("msg", validation.image_anmlMSG);
									return new ModelAndView("redirect:add_animal_details");
								}
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								td.setImage(fname);
							}else {
								model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
								return new ModelAndView("redirect:add_animal_details");
							}

						} catch (Exception e) {
						}
					}
					String sus_no = request.getParameter("sus_no");

					Animal_Status_Controller_Search commsus = new Animal_Status_Controller_Search();
					List<String> comminfo = commsus.getComdSusNoFromSusWithoutEnc(sus_no, session);
					String comd_sus_no = String.valueOf(comminfo.get(0));

					List<Miso_Orbat_Unt_Dtl> comminfo1 = commsus.getUnitNameFromSusWithoutEnc(comd_sus_no, session);
					String cmd = String.valueOf(comminfo1.get(0));

					td.setStatus("0");
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setComd_sus_no(comd_sus_no);
					// td.setComd_unit_name(cmd);

					TB_TMS_ANIMAL_HISTORY_DETAILS a = new TB_TMS_ANIMAL_HISTORY_DETAILS();

					if (request.getParameter("anml_type").toString().equals("Army Dog")) {
						a.setType_dog(Integer.parseInt(request.getParameter("type_dog")));
						a.setArmy_num(request.getParameter("army_num"));
						a.setSpecialization(Integer.parseInt((request.getParameter("specialization"))));
						a.setName_of_dog(request.getParameter("name_of_dog"));
					}

					if (request.getParameter("anml_type").toString().equals("Army Equines")) {
						a.setRemount_no(request.getParameter("remount_no"));
						a.setType_equines(Integer.parseInt((request.getParameter("type_equines1"))));
					}

					a.setAnml_type(request.getParameter("anml_type"));
					a.setSus_no(request.getParameter("sus_no"));
					// a.setUnit_name(request.getParameter("unit_name"));
					a.setMedals_desc_details(request.getParameter("medals_desc_details"));
					a.setAuthority(request.getParameter("authority"));
					a.setAward_date(request.getParameter("award_date"));
					a.setUnit_where_awarded(request.getParameter("unit_where_awarded"));
					a.setDate_of_birth(request.getParameter("date_of_birth"));
					a.setSex(request.getParameter("sex"));
					a.setImage(request.getParameter("image"));
					a.setMicrochip_no(request.getParameter("microchip_no"));
					a.setDetails_of_sire(request.getParameter("details_of_sire"));
					a.setDetails_of_dam(request.getParameter("details_of_dam"));
					a.setAnimal_purchase_cost(request.getParameter("animal_purchase_cost"));
					a.setAnimal_present_cost(request.getParameter("animal_present_cost"));
					a.setAge(request.getParameter("age"));
					a.setTos(request.getParameter("tos"));
					a.setTors(request.getParameter("tors"));
					a.setSos(request.getParameter("sos"));
					a.setSors(request.getParameter("sors"));
					a.setDate_admitted(request.getParameter("date_admitted"));
					a.setAwared_remarks(request.getParameter("awared_remarks"));
					a.setDisposal_remarks(request.getParameter("disposal_remarks"));
					a.setFitness_deployment(request.getParameter("fitness_deployment"));
					a.setFitness_status(Integer.parseInt((request.getParameter("fitness_status"))));
					a.setBreed(Integer.parseInt((request.getParameter("breed"))));
					a.setColour(Integer.parseInt((request.getParameter("colour"))));
					a.setSource(Integer.parseInt(request.getParameter("source")));
					a.setDisposal(request.getParameter("disposal"));
					a.setIssue_to_unit_name(request.getParameter("issue_to_unit_name"));
					a.setDisptrans(request.getParameter("disptrans"));
					a.setDis_date(request.getParameter("dis_date"));
					a.setIssue_date(request.getParameter("issue_date"));
					a.setRecord_status("Insert");
					a.setCreated_by(username);
					a.setCreated_date(date);

					String age = request.getParameter("age");
					String year[] = age.split(" ");
					td.setYear(Integer.parseInt(year[0]));

					a.setYear(Integer.parseInt(year[0]));

					int did = (Integer) sessionHQL.save(td);
					int did1 = (Integer) sessionHQL.save(a);

					model.put("msg", "Data Saved Successfully.");
					model.put("id", did);
					return new ModelAndView("redirect:add_animal_details");
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

	////////////////// Search Edit Animal Deatils /////////////////

	@RequestMapping(value = "/seach_adnimal_details", method = RequestMethod.GET)
	public ModelAndView seach_adnimal_details(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("seach_adnimal_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(session));
		Mmap.put("msg", msg);
		return new ModelAndView("search_animal_detailsTile", "search_add_animal_details_cmnd",
				new Tms_animals_details());
	}

	@RequestMapping(value = "/search_edit_report", method = RequestMethod.POST)
	public ModelAndView search_edit_report(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type1", required = false) String anml_type,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "type_dog1", required = false) String type_dog,
			@RequestParam(value = "type_equines1", required = false) String type_equines,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "to_date1", required = false) String tdate) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("seach_adnimal_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String fdt = request.getParameter("from_date1");
		String tdt = request.getParameter("to_date1");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			if (fdt != "") {
				if (format.parse(fdt).compareTo(today_dt) > 0 && format.parse(fdt).compareTo(today_dt) != 0) {
					Mmap.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:seach_adnimal_details");
				}
			} else if (tdt != "") {
				if (format.parse(tdt).compareTo(today_dt) > 0 && format.parse(tdt).compareTo(today_dt) != 0) {
					Mmap.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:seach_adnimal_details");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:seach_adnimal_details");
			}
		}

		ArrayList<ArrayList<String>> list = AnimalDao.getanimalstatus(sus_no, anml_type, unit_name, type_dog,
				type_equines, fdate, tdate, roleType);

		Mmap.put("anml_type1", anml_type);
		Mmap.put("sus_no1", sus_no);

		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:seach_adnimal_details");
			}
			Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("type_dog1", type_dog);
		Mmap.put("type_equines1", type_equines);
		Mmap.put("date", date1);
		Mmap.put("from_date1", fdate);
		Mmap.put("to_date1", tdate);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(sessionA));
		return new ModelAndView("search_animal_detailsTile");
	}

	////////////////////////

	/////////// Check Army Number Already Exist /////////////////

	@RequestMapping(value = "/checkDetailsOfanmlarmy", method = RequestMethod.POST)
	public @ResponseBody List<Tms_animals_details> checkDetailsOfanmlarmy(String army_num) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct army_num from Tms_animals_details where UPPER(army_num)=:army_num");
		q.setParameter("army_num", army_num);
		@SuppressWarnings("unchecked")
		List<Tms_animals_details> list = (List<Tms_animals_details>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	////////////////////////

	/////////// Check Remount Number Already Exist /////////////////

	@RequestMapping(value = "/checkDetailsOfanmlremount", method = RequestMethod.POST)
	public @ResponseBody List<Tms_animals_details> checkDetailsOfanmlremount(String remount_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct remount_no from Tms_animals_details where UPPER(remount_no)=:remount_no");
		q.setParameter("remount_no", remount_no);
		@SuppressWarnings("unchecked")
		List<Tms_animals_details> list = (List<Tms_animals_details>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	/////////////////////////////////////
	/* <---end of insert-------> */

	/////////// Army Number AutoComplete ////////////////

	@RequestMapping(value = "/Getarmyno", method = RequestMethod.POST)
	public @ResponseBody List<String> Getarmyno(String army_num, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct army_num from Tms_animals_details where upper(army_num) like :army_num")
				.setMaxResults(10);
		q.setParameter("army_num", army_num.toUpperCase() + "%");
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

	////////////////////////////////

	////////////// Microchip Number AutoComplete where Animal Type Army Dog
	////////////// ////////////////

	@RequestMapping(value = "/microchiplist", method = RequestMethod.POST)
	public @ResponseBody List<String> microchiplist(String microchip_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct microchip_no from Tms_animals_details where upper(microchip_no) like :microchip_no and anml_type='Army Dog'")
				.setMaxResults(10);
		q.setParameter("microchip_no", microchip_no.toUpperCase() + "%");

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

	////////////////////////////////////////////

	////////////// Microchip Number AutoComplete where Animal Type Army Equines
	////////////// ////////////////

	@RequestMapping(value = "/microchiplistequ", method = RequestMethod.POST)
	public @ResponseBody List<String> microchiplistequ(String microchip_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct microchip_no from Tms_animals_details where upper(microchip_no) like :microchip_no and anml_type='Army Equines'")
				.setMaxResults(10);
		q.setParameter("microchip_no", microchip_no.toUpperCase() + "%");

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
	/////////////////////////////////////////

	/////////////// Remount Number AutoComplete //////////////////////

	@RequestMapping(value = "/Getremountno", method = RequestMethod.POST)
	public @ResponseBody List<String> Getremountno(String remount_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q = session
				.createQuery(
						"select distinct remount_no from Tms_animals_details where upper(remount_no) like :remount_no")
				.setMaxResults(10);
		q.setParameter("remount_no", remount_no.toUpperCase() + "%");
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
	///////////////////////////////////////

	///////////// Edit Animal Details ////////////////////////
	@RequestMapping(value = "/AnimalEditActionUpload", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView tms_animals_details(@ModelAttribute("updateid") Tms_animals_details updateid,
			@RequestParam(value = "image_anml", required = false) MultipartFile image_anml, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session)
			throws ParseException {

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		String fname3 = "";
		String extension3 = "";

		int id = updateid.getId();
		String AnimalType = updateid.getAnml_type();
		String r = updateid.getRemount_no();
		String remount_no = null;
		String pcost = request.getParameter("animal_purchase_cost");
		String bvalue = request.getParameter("animal_present_cost");

		String cost = "";
		String value = "";

		cost = pcost.replaceAll(",", "");
		value = bvalue.replaceAll(",", "");

		Pattern pattern = Pattern.compile(".*[^0-9].*");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date today_dt = new Date();

		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString()); // 2 MB
		if (image_anml.getSize() > fileSizeLimit) {
			model.put("msg", "File size should be less then 2 MB");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		if (Integer.parseInt(request.getParameter("fitness_status")) == 3) {
			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				}
			}

		}

		else if (Integer.parseInt(request.getParameter("fitness_status")) == 5) {
			if (request.getParameter("date_admitted").equals("")) {
				model.put("msg", "Please Select Date Admitted.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			} else {
				if (format.parse(request.getParameter("date_admitted")).compareTo(today_dt) > 0
						&& format.parse(request.getParameter("date_admitted")).compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				}
			}

		}

		if (updateid.getDisptrans() != null) {
			if (updateid.getDisptrans().equals("disp")) {

				if (request.getParameter("dis_date").equals("")) {
					model.put("msg", "Please Select Disposal Date.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				} else {
					if (format.parse(request.getParameter("dis_date")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("dis_date")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						model.put("updateid", id);
						return new ModelAndView("redirect:update_Animal_URL");
					}
				}

				if (request.getParameter("disposal").equals("0")) {
					model.put("msg", "Please Select Disposal.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				}
			} else {

				if (request.getParameter("issue_to_unit_name").equals("")) {
					model.put("msg", "Please Enter Issue to Unit.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				}

				else if (request.getParameter("issue_date").equals("")) {
					model.put("msg", "Please Select Issue Date.");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				} else {
					if (format.parse(request.getParameter("issue_date")).compareTo(today_dt) > 0
							&& format.parse(request.getParameter("issue_date")).compareTo(today_dt) != 0) {
						model.put("msg", "Please Enter Valid Date Of Request.");
						model.put("updateid", id);
						return new ModelAndView("redirect:update_Animal_URL");
					}
				}
			}
		}

		/*
		 * else if(Integer.parseInt(request.getParameter("fitness_status")) == 3) {
		 * if(request.getParameter("date_admitted").equals("")) { model.put("msg",
		 * "Please Select Date Admitted."); model.put("updateid", id); return new
		 * ModelAndView("redirect:update_Animal_URL"); }
		 * 
		 * else {
		 * if(format.parse(request.getParameter("date_admitted")).compareTo(today_dt) >
		 * 0 && format.parse(request.getParameter("date_admitted")).compareTo(today_dt)
		 * != 0) { model.put("msg", "Please Enter Valid Date Of Request.");
		 * model.put("updateid", id); return new
		 * ModelAndView("redirect:update_Animal_URL"); } }
		 * 
		 * }
		 * 
		 * else if(Integer.parseInt(request.getParameter("fitness_status")) == 5) {
		 * if(request.getParameter("date_admitted").equals("")) { model.put("msg",
		 * "Please Select Date Admitted."); model.put("updateid", id); return new
		 * ModelAndView("redirect:update_Animal_URL"); } else {
		 * if(format.parse(request.getParameter("date_admitted")).compareTo(today_dt) >
		 * 0 && format.parse(request.getParameter("date_admitted")).compareTo(today_dt)
		 * != 0) { model.put("msg", "Please Enter Valid Date Of Request.");
		 * model.put("updateid", id); return new
		 * ModelAndView("redirect:update_Animal_URL"); } }
		 * 
		 * 
		 * }
		 */

		else if (!updateid.getDate_of_death().equals("")) {
			if (format.parse(request.getParameter("date_of_death")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("date_of_death")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		else if (!updateid.getAward_date().equals("")) {
			if (format.parse(request.getParameter("award_date")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("award_date")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		else if (!updateid.getTos().equals("")) {
			if (format.parse(request.getParameter("tos")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("tos")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		else if (!updateid.getTors().equals("")) {
			if (format.parse(request.getParameter("tors")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("tors")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		else if (!updateid.getSos().equals("")) {
			if (format.parse(request.getParameter("sos")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("sos")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		else if (!updateid.getSors().equals("")) {
			if (format.parse(request.getParameter("sors")).compareTo(today_dt) > 0
					&& format.parse(request.getParameter("sors")).compareTo(today_dt) != 0) {
				model.put("msg", "Please Enter Valid Date Of Request.");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		if (!pattern.matcher(cost).matches() == false) {
			model.put("msg", "Please Enter valid Purchase Cost");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		if (!pattern.matcher(value).matches() == false) {
			model.put("msg", "Please Enter valid Book Value");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		if (updateid.getSus_no().equals("") || updateid.getSus_no().equals("null")
				|| updateid.getSus_no().equals(null)) {
			model.put("msg", "Please Select Sus No");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.sus_noLength(updateid.getSus_no()) == false) {
			model.put("msg", validation.sus_noMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		/*
		 * else if (updateid.getUnit_name().equals("") ||
		 * updateid.getUnit_name().equals("null") ||
		 * updateid.getUnit_name().equals(null)) { model.put("msg",
		 * "Please Select Unit Name"); model.put("updateid", id); return new
		 * ModelAndView("redirect:update_Animal_URL"); }
		 * 
		 * else if(validation.checkUnit_nameLength(updateid.getUnit_name()) == false){
		 * model.put("msg",validation.unit_nameMSG); model.put("updateid", id); return
		 * new ModelAndView("redirect:update_Animal_URL"); }
		 */

		if (updateid.getAnml_type().equals("Army Dog")) {

			String army_num = updateid.getArmy_num();

			if (updateid.getArmy_num().equals("") || updateid.getArmy_num().equals("null")
					|| updateid.getArmy_num().equals(null)) {
				model.put("msg", "Please Enter Army No");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (validation.check20Length(updateid.getArmy_num()) == false) {
				model.put("msg", validation.army_numMSG);
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (updateid.getType_dog() == 0) {
				model.put("msg", "Please Select Type of Dog");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (updateid.getName_of_dog().equals("") || updateid.getName_of_dog().equals("null")
					|| updateid.getName_of_dog().equals(null)) {
				model.put("msg", "Please Enter Name of Dog");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (validation.check35Length(updateid.getName_of_dog()) == false) {
				model.put("msg", validation.name_of_dogMSG);
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (updateid.getSpecialization() == 0) {
				model.put("msg", "Please Select Specialisation");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

		} else {

			remount_no = updateid.getRemount_no();

			if (updateid.getRemount_no().equals("") || updateid.getRemount_no().equals("null")
					|| updateid.getRemount_no().equals(null)) {
				model.put("msg", "Please Enter Remount No");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (validation.checkRemountNoLength(updateid.getRemount_no()) == false) {
				model.put("msg", validation.remount_noMSG);
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			else if (updateid.getType_equines() == 0) {
				model.put("msg", "Please Select Type of Equines");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}
		}

		if (updateid.getBreed() == 0) {
			model.put("msg", "Please Select Breed");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (updateid.getColour() == 0) {
			model.put("msg", "Please Select Colour");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (updateid.getSex().equals("") || updateid.getSex().equals("undefined")) {
			model.put("msg", "Please Select Sex");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (updateid.getSource() == 0) {
			model.put("msg", "Please Select Source");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (updateid.getDate_of_birth().equals("") || updateid.getDate_of_birth().equals("null")
				|| updateid.getDate_of_birth().equals(null)) {
			model.put("msg", "Please Select Date of Birth");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getDate_of_birth()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) > 0
				&& format.parse(request.getParameter("date_of_birth")).compareTo(today_dt) != 0) {
			model.put("msg", "Please Enter Valid Date Of Request.");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkageLength(updateid.getAge()) == false) {
			model.put("msg", validation.ageMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check35Length(updateid.getDetails_of_sire()) == false) {
			model.put("msg", validation.details_of_sireMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check35Length(updateid.getDetails_of_dam()) == false) {
			model.put("msg", validation.details_of_damMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check20Length(updateid.getMicrochip_no()) == false) {
			model.put("msg", validation.microchip_noMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getDate_admitted()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check255Length(updateid.getFitness_deployment()) == false) {
			model.put("msg", validation.fitness_deploymentMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (updateid.getAnimal_purchase_cost().equals("") && updateid.getAnimal_present_cost().equals("")
				|| updateid.getAnimal_purchase_cost().equals("null") && updateid.getAnimal_present_cost().equals("null")
				|| updateid.getAnimal_purchase_cost().equals(null) && updateid.getAnimal_present_cost().equals(null)) {
			model.put("msg", "Please Enter Either Animal Purchase Cost or Book Value");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (!updateid.getAnimal_purchase_cost().equals("") && !updateid.getAnimal_present_cost().equals("")) {
			model.put("msg", "Please Enter Either Animal Purchase Cost or Book Value");
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check10Length(updateid.getAnimal_purchase_cost()) == false) {
			model.put("msg", validation.animal_purchase_costMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check10Length(updateid.getAnimal_present_cost()) == false) {
			model.put("msg", validation.animal_present_costMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkUnit_nameLength(updateid.getIssue_to_unit_name()) == false) {
			model.put("msg", validation.unit_nameMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getDis_date()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getIssue_date()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check255Length(updateid.getDisposal_remarks()) == false) {
			model.put("msg", validation.disposal_remarksMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkUnit_nameLength(updateid.getUnit_where_awarded()) == false) {
			model.put("msg", validation.unit_nameMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkAnimalMasterLength(updateid.getAuthority()) == false) {
			model.put("msg", validation.authorityMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getAward_date()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.check255Length(updateid.getAwared_remarks()) == false) {
			model.put("msg", validation.awared_remarksMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getTos()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getTors()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getSos()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		else if (validation.checkDateLength(updateid.getSors()) == false) {
			model.put("msg", validation.dateMSG);
			model.put("updateid", id);
			return new ModelAndView("redirect:update_Animal_URL");
		}

		if (!image_anml.isEmpty()) {

			String image_anml1Ext = FilenameUtils.getExtension(image_anml.getOriginalFilename());
			if (!image_anml1Ext.toUpperCase().equals("jpg".toUpperCase())
					&& !image_anml1Ext.equals("jpeg".toUpperCase()) && !image_anml1Ext.equals("png".toUpperCase())) {
				model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
				model.put("updateid", id);
				return new ModelAndView("redirect:update_Animal_URL");
			}

			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			// code modify by Paresh on 05/05/2020
			try {
				byte[] bytes = image_anml.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
					String tmsFilePath = session.getAttribute("tmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = image_anml.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension3 = filename.substring(i + 1);
					}
					fname3 = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_" + userid + "_TMSDOC." + extension3;
					if (validation.checkImageAnmlLength(fname3) == false) {
						model.put("msg", validation.image_anmlMSG);
						model.put("updateid", id);
						return new ModelAndView("redirect:update_Animal_URL");
					}
					File serverFile = new File(fname3);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					updateid.setImage(fname3);
				}else {
					model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
					model.put("updateid", id);
					return new ModelAndView("redirect:update_Animal_URL");
				}
			} catch (Exception e) {
			}
		}

		else {

			List<Tms_animals_details> newamd = AnimalDao.getANIMAL_EDITid(updateid.getId());
			String oldamdtdocu = newamd.get(0).getImage();
			updateid.setImage(oldamdtdocu);
		}
		String msg1 = AnimalDao.UpdateAnimalAttValue(updateid, username);
		model.put("msg", msg1);
		return new ModelAndView("redirect:seach_adnimal_details");
	}

	@RequestMapping(value = "/admin/update_Animal_URL")
	public ModelAndView update_Animal_URL(@ModelAttribute("updateid") int updateid, HttpSession sessionA,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type5", required = false) String anml_type1, Authentication authentication,HttpServletRequest request) {

		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}

		Tms_animals_details animals_details = AnimalDao.gettms_animals_detailsByid(updateid);

		String anml_type = animals_details.getAnml_type();
		String sus_no = animals_details.getSus_no();
		if (sus_no != "" & orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).size() > 0) {
			model.put("unit_name", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}

		model.put("AnimalEditCMDUpload", AnimalDao.getANIMAL_EDITid(updateid));
		model.put("AnimalEditCMDUpload", AnimalDao.gettms_animals_detailsByid(updateid));
		model.put("AnimalEditCMDUpload", animals_details);

		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);

		if (anml_type.equals("Army Dog")) {
			model.put("getTypeOfDogList", getTypeOfDogList(sessionA));
			model.put("getsplzList", getsplzList(sessionA));
			model.put("getclrList", getclrList(anml_type, sessionA));
			model.put("getbreedList", getbreedList(anml_type, sessionA));
			model.put("getFitnessStatusList", getFitnessStatusList(anml_type, sessionA));
			model.put("getSourceList", getSourceList(anml_type, sessionA));
		}

		else {
			model.put("getAnimalTypeList", getAnimalTypeList(sessionA));
			model.put("getclrList", getclrList(anml_type, sessionA));
			model.put("getbreedList", getbreedList(anml_type, sessionA));
			model.put("getFitnessStatusList", getFitnessStatusList(anml_type, sessionA));
			model.put("getSourceList", getSourceList(anml_type, sessionA));
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("Edit_Animal_DetailsTile");
	}

	////////////////////////////////

	//////////////// Download Image //////////////////

	@RequestMapping(value = "/admin/getDownloadImageanml", method = RequestMethod.POST)
	public ModelAndView getDownloadImageanml(@ModelAttribute("updateid") int updateid, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String EXTERNAL_FILE_PATH = "";

		if (AnimalDao.getANIMAL_EDITid(updateid).get(0).getImage() == null) {
			return new ModelAndView(
					"redirect:update_Animal_URL?msg=Sorry. The file you are looking for does not exist");
		} else {
			EXTERNAL_FILE_PATH = AnimalDao.getANIMAL_EDITid(updateid).get(0).getImage();
		}

		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (!file.exists()) {
				return new ModelAndView(
						"redirect:update_Animal_URL?msg=Sorry. The file you are looking for does not exist");
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
			return new ModelAndView("redirect:update_Animal_URL?msg=Download Successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:update_Animal_URL?msg=Download Successfully");
	}
	//////////////////////////////////////////////

	////////////////////////////// UE Master ////////////////////////////
	@RequestMapping(value = "/Animal_Ue_Master", method = RequestMethod.GET)
	public ModelAndView Animal_Ue_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Ue_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getsplzList", getsplzList(session));
		Mmap.put("msg", msg);
		return new ModelAndView("Add_Animal_UE_MasterTiles", "add_animal_ue_details_cmnd", new TMS_TB_UE_MASTER());
	}

	@RequestMapping(value = "/add_animal_ue_details_act", method = RequestMethod.POST)
	public ModelAndView add_animal_ue_details_act(@ModelAttribute("add_animal_ue_details_cmnd") TMS_TB_UE_MASTER ue,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Ue_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getParameter("anml_type") == null) {
			model.put("msg", "Please Select Animal Type");
			return new ModelAndView("redirect:Animal_Ue_Master");
		} else if (ue.getSus_no().equals("") || ue.getSus_no().equals("null") || ue.getSus_no().equals(null)) {
			model.put("msg", "Please Select Sus No");
			return new ModelAndView("redirect:Animal_Ue_Master");
		} else if (validation.sus_noLength(ue.getSus_no()) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:Animal_Ue_Master");
		}

		if (ue.getAnml_type().equals("Army Dog")) {

			String uedog = request.getParameter("ue_of_dogs");

			Pattern pattern = Pattern.compile(".*[^0-9].*");

			if (!pattern.matcher(uedog).matches() == false) {
				model.put("msg", "Please Enter valid UE of Army Dogs");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}

			if (ue.getUe_of_dogs().equals("") || ue.getUe_of_dogs().equals("null") || ue.getUe_of_dogs().equals(null)) {
				model.put("msg", "Please Enter UE Of Army Dogs");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}

			else if (validation.checkUELength(ue.getUe_of_dogs()) == false) {
				model.put("msg", validation.ue_of_dogsMSG);
				return new ModelAndView("redirect:Animal_Ue_Master");
			} else if (ue.getSpecialization() == 0) {
				model.put("msg", "Please Select Specialisation");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}
		}

		else {

			String ueequ = request.getParameter("ue_of_equins");

			Pattern pattern = Pattern.compile(".*[^0-9].*");

			if (!pattern.matcher(ueequ).matches() == false) {
				model.put("msg", "Please Enter valid UE of Army Equines");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}

			if (ue.getUe_of_equins().equals("") || ue.getUe_of_equins().equals("null")
					|| ue.getUe_of_equins().equals(null)) {
				model.put("msg", "Please enter ue of Equeins");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}

			else if (validation.checkUELength(ue.getUe_of_equins()) == false) {
				model.put("msg", validation.ue_of_equinsMSG);
				return new ModelAndView("redirect:Animal_Ue_Master");
			}

			else if (ue.getType_equines() == 0) {
				model.put("msg", "Please Enter Type");
				return new ModelAndView("redirect:Animal_Ue_Master");
			}
		}

		int id = 0;

		if (ue.getId() > 0) {
			id = ue.getId();
		}

		Date date = new Date();
		String username = session.getAttribute("username").toString();

		if (request.getParameter("anml_type").toString().equals("Army Dog")) {
			ue.setSpecialization(Integer.parseInt(request.getParameter("specialization").toString()));
			ue.setType_equines(0);
		} else {
			ue.setType_equines(Integer.parseInt(request.getParameter("type_equines").toString()));
			ue.setSpecialization(0);
		}

		ue.setAnml_type(request.getParameter("anml_type"));

		if (id == 0) {
			ue.setCreated_by(username);
			ue.setCreated_date(date);

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();
			try {

				if (request.getParameter("anml_type").toString().equals("Army Dog")) {

					int specialization = Integer.parseInt(request.getParameter("specialization").toString());

					String sus_no = request.getParameter("sus_no").toString();
					String ue_of_dogs = request.getParameter("ue_of_dogs").toString();

					Query q0 = session0.createQuery(
							"select count(id) from TMS_TB_UE_MASTER where sus_no=:sus_no and specialization =:specialization and id !=:id");
					q0.setParameter("sus_no", sus_no);
					q0.setParameter("specialization", specialization);
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						int did = (Integer) session0.save(ue);
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				} else {

					int type_equines = Integer.parseInt(request.getParameter("type_equines").toString());

					String sus_no = request.getParameter("sus_no").toString();
					String ue_of_equins = request.getParameter("ue_of_equins").toString();

					Query q0 = session0.createQuery(
							"select count(id) from TMS_TB_UE_MASTER where sus_no=:sus_no and type_equines=:type_equines and id !=:id");
					q0.setParameter("sus_no", sus_no);
					q0.setParameter("type_equines", type_equines);
					q0.setParameter("id", id);
					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						int did = (Integer) session0.save(ue);
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				tx0.commit();
				session0.close();
			}
		}

		else {

			ue.setModify_by(username);
			ue.setModify_date(date);

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();

			if (request.getParameter("anml_type").toString().equals("Army Dog")) {

				String ue_of_dogs = request.getParameter("ue_of_dogs");
				int specialization = Integer.parseInt(request.getParameter("specialization").toString());
				String sus_no = request.getParameter("sus_no").toString();

				Query q0 = session1.createQuery(
						"select count(id) from TMS_TB_UE_MASTER where sus_no =:sus_no and specialization =:specialization and ue_of_dogs =:ue_of_dogs");
				q0.setParameter("sus_no", sus_no);
				q0.setParameter("specialization", specialization);
				q0.setParameter("ue_of_dogs", ue_of_dogs);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String msg1 = AnimalDao.updateUeDog(ue);
					model.put("msg", msg1);
				} else {
					model.put("msg", "Data already Exist.");
				}
			} else {

				int type_equines = Integer.parseInt(request.getParameter("type_equines").toString());
				String ue_of_equins = request.getParameter("ue_of_equins");
				String sus_no = request.getParameter("sus_no").toString();

				Query q0 = session1.createQuery(
						"select count(id) from TMS_TB_UE_MASTER where sus_no=:sus_no and type_equines=:type_equines and ue_of_equins=:ue_of_equins");
				q0.setParameter("sus_no", sus_no);
				q0.setParameter("type_equines", type_equines);
				q0.setParameter("ue_of_equins", ue_of_equins);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String msg1 = AnimalDao.updateUeEqu(ue);
					model.put("msg", msg1);
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
		}
		return new ModelAndView("redirect:Animal_Ue_Master");
	}

	@RequestMapping(value = "/search_ue_master_dog", method = RequestMethod.POST)
	public ModelAndView getAttributeDataSearchAnimalUE(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "ue_of_dogs1", required = false) String ue_of_dogs,
			@RequestParam(value = "specialization1", required = false) int specialization) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Ue_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Mmap.put("anml_type3", anml_type);
		Mmap.put("sus_no1", sus_no);
		Mmap.put("ue_of_dogs1", ue_of_dogs);
		Mmap.put("specialization1", specialization);
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:Animal_Ue_Master");
			}
			Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}

		ArrayList<ArrayList<String>> list = AnimalDao.getdetailsUEList(anml_type, sus_no, unit_name, ue_of_dogs,
				specialization, roleType);
		if (anml_type == null) {
			Mmap.put("msg", "Please Select Animal Type");
		}

		else {
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
		}
		Mmap.put("getsplzList", getsplzList(sessionA));
		return new ModelAndView("Add_Animal_UE_MasterTiles");

	}

	@RequestMapping(value = "/search_ue_master_equ", method = RequestMethod.POST)
	public ModelAndView search_ue_master_equ(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type4", required = false) String anml_type,
			@RequestParam(value = "sus_no3", required = false) String sus_no,
			@RequestParam(value = "unit_name3", required = false) String unit_name,
			@RequestParam(value = "ue_of_equins1", required = false) String ue_of_equins,
			@RequestParam(value = "type_equines1", required = false) int type_equines) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Ue_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Mmap.put("anml_type4", anml_type);
		Mmap.put("sus_no3", sus_no);

		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:Animal_Ue_Master");
			}
			Mmap.put("unit_name3", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}

		Mmap.put("ue_of_equins1", ue_of_equins);
		Mmap.put("type_equines1", type_equines);
		ArrayList<ArrayList<String>> listEq = AnimalDao.getAttributeDataSearchAnimalUEArmyEqn(anml_type, sus_no,
				unit_name, ue_of_equins, type_equines, roleType);
		if (anml_type == null) {
			Mmap.put("msg", "Please Select Animal Type");
		} else {
			Mmap.put("listEq", listEq);
			Mmap.put("roleType", roleType);
		}
		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		return new ModelAndView("Add_Animal_UE_MasterTiles");
	}

	////////////////// Microchip Number Army Number Wise /////////////////////
	@RequestMapping(value = "/getmicrochipno", method = RequestMethod.POST)
	public @ResponseBody List<String> getmicrochipno(String army_num, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct microchip_no from Tms_animals_details where army_num=:army_num");
		q.setParameter("army_num", army_num);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	/////////////////////////////////

	////////////////// Army Number Microchip Number Wise /////////////////////

	@RequestMapping(value = "/armynolist", method = RequestMethod.POST)
	public @ResponseBody List<String> armynolist(String microchip_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct army_num from Tms_animals_details where microchip_no=:microchip_no");
		q.setParameter("microchip_no", microchip_no);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	////////////////////////////

	////////////////// Remount Number Microchip Number Wise /////////////////////

	@RequestMapping(value = "/remountnolist", method = RequestMethod.POST)
	public @ResponseBody List<String> remountnolist(String microchip_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct remount_no from Tms_animals_details where microchip_no=:microchip_no");
		q.setParameter("microchip_no", microchip_no);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	////////////////////////////////////

	///////////// Microchip Number Remount Number wise /////////////////////

	@RequestMapping(value = "/getmicrochipnoequ", method = RequestMethod.POST)
	public @ResponseBody List<String> getmicrochipnoequ(String remount_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct microchip_no from Tms_animals_details where remount_no=:remount_no");
		q.setParameter("remount_no", remount_no);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	////////////////////////

	//////////////// Get Specialisation List //////////////////

	public @ResponseBody List<String> getsplzList(HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select type_splztn,id from TMS_TB_MISO_SPLZ_MASTER where id > 0 ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	/////////////////////////////////////////////////////

	//////////////// Get Color List //////////////////

	public @ResponseBody List<String> getclrList(Object anml_type, HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct type_color,id from TMS_TB_MISO_COLOR_MASTER where anml_type=:anml_type");
		q.setParameter("anml_type", anml_type);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	//////////////////////////////////////////

	//////////////// Get Breed List //////////////////

	public @ResponseBody List<String> getbreedList(Object anml_type, HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct type_breed,id from TMS_TB_MISO_BREED_MASTER where anml_type=:anml_type ");
		q.setParameter("anml_type", anml_type);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	/////////////////////////////////////////

	////////////////// Delete Breed //////////////////

	@RequestMapping(value = "/deleteBreed", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteBreed(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}
		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TMS_TB_MISO_BREED_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}
	////////////////////////////////////////////////

	///////////////////// Delete Color ///////////////////////

	@RequestMapping(value = "/deleteColor", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteColor(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}
		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TMS_TB_MISO_COLOR_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}
	/////////////////////////////////////////////////////

	/////////////////// Delete Specialisation /////////////////

	@RequestMapping(value = "/deleteSplz", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteSplz(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}

		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TMS_TB_MISO_SPLZ_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}
	///////////////////////////////////

	///////////////////// Delete UE //////////////////////

	@RequestMapping(value = "/deleteUE", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteUE(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}
		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TMS_TB_UE_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}
	////////////////////////////////////////////////

	///////////////// Source Master //////////////

	@RequestMapping(value = "/Animal_Source_Master", method = RequestMethod.GET)
	public ModelAndView Animal_Source_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Source_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Animal_Source_MasterTiles", "add_animal_source_details_cmnd",
				new TMS_TB_MISO_SOURCE_MASTER());
	}
	///////////////////////////////

	/////////////// Delete Source /////////////////

	@RequestMapping(value = "/deleteSource", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteSource(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}

		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TMS_TB_MISO_SOURCE_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}
	//////////////////////////////////////////

	////// zalak

	///////////////////// Get Equines list ///////////////////

	public @ResponseBody List<String> getAnimalTypeList(HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select type_of_animal,id from TB_TMS_TYPE_OF_ANIMAL_MASTER where id > 0");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	///////////////////////////////////////////

	//////////////////// Get Dog List ///////////////////////////////

	public @ResponseBody List<String> getTypeOfDogList(HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select type_dog,id from TB_TMS_TYPE_DOG where id > 0");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	///////////////////////////////////////////////

	////////////// Get Fitness Status List /////////////////////////////

	public @ResponseBody List<String> getFitnessStatusList(Object anml_type, HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct fitness_status,id from TB_TMS_ANIMAL_FITNESS_STATUS where anml_type=:anml_type ");
		q.setParameter("anml_type", anml_type);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	////////////////////////////////////////////////////

	//////////////// Get Source List ////////////////

	public @ResponseBody List<String> getSourceList(Object anml_type, HttpSession sessionA) {

		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct source,id from TMS_TB_MISO_SOURCE_MASTER where anml_type=:anml_type ");
		q.setParameter("anml_type", anml_type);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	//////////////////////////////////////////////////

	/////////////////////// Delete Type Dog ////////////////////

	@RequestMapping(value = "/deleteTypeDog", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteTypeDog(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}
		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TB_TMS_TYPE_DOG where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}
	//////////////////////////////////////////

	////////////// Delete Fitness Status ////////////////////////

	@RequestMapping(value = "/deleteFitnessStatus", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteFitnessStatus(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}

		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TB_TMS_ANIMAL_FITNESS_STATUS where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}
	/////////////////////////////////////////////////

	////////////////// Delete Equines /////////////////////

	@RequestMapping(value = "/deleteTypeOfAnimal", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteTypeOfAnimal(int id, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}

		try {

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from TB_TMS_TYPE_OF_ANIMAL_MASTER where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}
	/////////////////////////////////////////////

	// end zalak

	/////////////////// Animal Details //////////////////

	@RequestMapping(value = "/anml_data", method = RequestMethod.GET)
	public ModelAndView anml_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("anml_data", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(session));
		Mmap.put("msg", msg);
		return new ModelAndView("animal_dataTiles");
	}

	@RequestMapping(value = "/all_data_print", method = RequestMethod.POST)
	public ModelAndView all_data_print(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type1", required = false) String anml_type,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "type_dog1", required = false) String type_dog,
			@RequestParam(value = "type_equines1", required = false) String type_equines,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "to_date1", required = false) String tdate,
			@RequestParam(value = "comd_sus_no1", required = false) String comd_sus_no,
			@RequestParam(value = "comd_unit_name1", required = false) String comd_unit_name,
			@RequestParam(value = "hdtypeofdog1", required = false) String hdtypeofdog,
			@RequestParam(value = "hdtypeofequ1", required = false) String hdtypeofequ,
			@RequestParam(value = "animal_status1", required = false) String animal_status){

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("anml_data", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String fdt = request.getParameter("from_date1");
		String tdt = request.getParameter("to_date1");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			if (fdt != "") {
				if (format.parse(fdt).compareTo(today_dt) > 0 && format.parse(fdt).compareTo(today_dt) != 0) {
					Mmap.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:anml_data");
				}
			} else if (tdt != "") {
				if (format.parse(tdt).compareTo(today_dt) > 0 && format.parse(tdt).compareTo(today_dt) != 0) {
					Mmap.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:anml_data");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				Mmap.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:anml_data");
			}
		}

		ArrayList<ArrayList<String>> list = AnimalDao.getAnimalAllData(sus_no, anml_type, unit_name, type_dog,
				type_equines, fdate, tdate, comd_sus_no, comd_unit_name,animal_status);

		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(sessionA));
		Mmap.put("anml_type1", anml_type);
		Mmap.put("sus_no1", sus_no);
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:anml_data");
			}
			Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}

		else if (comd_sus_no != "") {
			if (validation.sus_noLength(comd_sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:anml_data");
			}
		}

		else if (comd_unit_name != "") {
			if (validation.checkUnit_nameLength(comd_unit_name) == false) {
				Mmap.put("msg", validation.unit_nameMSG);
				return new ModelAndView("redirect:anml_data");
			}
		}

		Mmap.put("type_dog1", type_dog);
		Mmap.put("type_equines1", type_equines);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("from_date1", fdate);
		Mmap.put("to_date1", tdate);
		Mmap.put("comd_sus_no1", comd_sus_no);
		Mmap.put("comd_unit_name1", comd_unit_name);
		Mmap.put("hdtypeofdog1", hdtypeofdog);
		Mmap.put("hdtypeofequ1", hdtypeofequ);
		Mmap.put("animal_status", animal_status);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(sessionA));
		return new ModelAndView("animal_dataTiles");
	}

	//////////////////// Cmd SUS No AutoComplete /////////////////////////
	@RequestMapping(value = "/getComdSusNolist", method = RequestMethod.POST)
	public @ResponseBody List<String> getComdSusNolist(String comd_sus_no, HttpSession sessionUserId) {

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct comd_sus_no from Tms_animals_details where upper(comd_sus_no) like :comd_sus_no")
				.setMaxResults(10);
		q.setParameter("comd_sus_no", comd_sus_no.toUpperCase() + "%");
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

	/////////////////// Cmd Unit Name AutoComplete ///////////////////////////////

	@RequestMapping(value = "/getcomdunitnamelist", method = RequestMethod.POST)
	public @ResponseBody List<String> getcomdunitnamelist(String comd_unit_name, HttpSession sessionUserId) {
		comd_unit_name = comd_unit_name.replace("&#40;", "(");
		comd_unit_name = comd_unit_name.replace("&#41;", ")");

		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and upper(unit_name) like :comd_unit_name and sus_no in (select distinct comd_sus_no from Tms_animals_details)")
				.setMaxResults(10);
		q.setParameter("comd_unit_name", comd_unit_name.toUpperCase() + "%");
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
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "3BsJyh==");
		return FinalList;
	}
	///////////////////////////////////////

	//////////// Cmd Unit Name Cmd Sus No Wise ////////////

	@RequestMapping(value = "/getcmdunitname", method = RequestMethod.POST)
	public @ResponseBody List<String> getcmdunitname(String comd_sus_no, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:comd_sus_no and status_sus_no='Active' ");
		q.setParameter("comd_sus_no", comd_sus_no);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	///////////////////////////////////////

	/////////////////// Cmd Sus No Cmd Unit Name wise ///////////////////////

	@RequestMapping(value = "/getcmdsus", method = RequestMethod.POST)
	public @ResponseBody List<String> getcmdsus(String comd_unit_name, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:comd_unit_name and status_sus_no='Active' ");
		q.setParameter("comd_unit_name", comd_unit_name);
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

		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(0).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);

		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	///////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/anml_data_ue_uh", method = RequestMethod.GET)
	public ModelAndView anml_data_ue_uh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("anml_data_ue_uh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		
		 if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String formation_code = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",formation_code);	
					Mmap.put("getCommandList",comd);
					List<Tbl_CodesForm> corps= b.getFormationList("Corps",formation_code);
					Mmap.put("getCorpsList",corps);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Corps")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Division")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					List<Tbl_CodesForm> bde=b.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Brigade")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
				}
			}
			 if(roleAccess.equals("Unit")){
				 String roleSusNo = session.getAttribute("roleSusNo").toString();
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",orbt.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					
					List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
					roleFormationNo = formation.get(0);
					
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
					
			 }
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
				List<Tbl_CodesForm> comd=orbt.getCommandDetailsList();
				Mmap.put("getCommandList",comd);
				String selectComd ="<option value=''>--Select--</option>";
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcomd", selectComd);
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
		
		
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(session));
		Mmap.put("getsplzList", getsplzList(session));
		Mmap.put("msg", msg);
		return new ModelAndView("animal_data_ue_uh_Tiles");
	}
	
	
	
	@RequestMapping(value = "/all_data_print_ue_uh", method = RequestMethod.POST)
	public ModelAndView all_data_print_ue_uh(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "anml_type1", required = false) String anml_type,
			
			@RequestParam(value = "type_dog1", required = false) String type_dog,
			@RequestParam(value = "type_equines1", required = false) String type_equines,
			
			@RequestParam(value = "hdtypeofdog1", required = false) String hdtypeofdog,
			@RequestParam(value = "hdtypeofequ1", required = false) String hdtypeofequ,
			@RequestParam(value = "comd_sus_no1", required = false) String comd_sus_no,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps,
			@RequestParam(value = "cont_div1", required = false) String cont_div,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name){

		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("anml_data_ue_uh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		
		 if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String formation_code = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",formation_code);	
					Mmap.put("getCommandList",comd);
					List<Tbl_CodesForm> corps= b.getFormationList("Corps",formation_code);
					Mmap.put("getCorpsList",corps);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Corps")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Division")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					List<Tbl_CodesForm> bde=b.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Brigade")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
				}
			}
			 if(roleAccess.equals("Unit")){
				 /*String roleSusNo = sessionA.getAttribute("roleSusNo").toString();*/
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",orbt.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
					
					List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
					roleFormationNo = formation.get(0);
					
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
					
			 }
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
				List<Tbl_CodesForm> comd=orbt.getCommandDetailsList();
				Mmap.put("getCommandList",comd);
				String selectComd ="<option value=''>--Select--</option>";
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcomd", selectComd);
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
		

		ArrayList<ArrayList<String>> list = AnimalDao.all_data_print_ue_uh(sus_no, anml_type, unit_name, type_dog,
				type_equines,comd_sus_no,cont_corps,cont_div,cont_bde);

		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		Mmap.put("getsplzList", getsplzList(sessionA));
		Mmap.put("anml_type1", anml_type);
		Mmap.put("sus_no1", sus_no);
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:anml_data_ue_uh");
			}
			Mmap.put("unit_name1", orbt.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}


		Mmap.put("type_dog1", type_dog);
		Mmap.put("type_equines1", type_equines);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("comd_sus_no1", comd_sus_no);
		Mmap.put("cont_corps1", cont_corps);
		Mmap.put("cont_div1", cont_div);
		Mmap.put("cont_bde1", cont_bde);
		Mmap.put("hdtypeofdog1", hdtypeofdog);
		Mmap.put("hdtypeofequ1", hdtypeofequ);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		Mmap.put("getAnimalTypeList", getAnimalTypeList(sessionA));
		Mmap.put("getTypeOfDogList", getTypeOfDogList(sessionA));
		return new ModelAndView("animal_data_ue_uh_Tiles");
	}
	
	/// Dog
	 @RequestMapping(value = "/Excel_data_dog", method = RequestMethod.POST)
		public ModelAndView Excel_data_dog(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("comd_sus_no_ex") String comd_sus_no,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
				@ModelAttribute("cont_bde_ex") String cont_bde,
				@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
				@ModelAttribute("cont_bde_txt") String cont_bde_txt,
				@ModelAttribute("unit_name_ex") String unit_name,
				@ModelAttribute("sus_no_ex") String sus_no,
				
				@ModelAttribute("anml_type_ex") String anml_type,
				@ModelAttribute("anml_type_txt") String anml_type_txt,
				
				@ModelAttribute("type_dog_ex") String type_dog,
				@ModelAttribute("type_equines_ex") String type_equines,
				
				@RequestParam(value = "msg", required = false) String msg) {

		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("anml_data_ue_uh", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		  ArrayList<ArrayList<String>> Excel =AnimalDao.all_data_print_ue_uh(sus_no, anml_type, unit_name, type_dog,
					type_equines,comd_sus_no,cont_corps,cont_div,cont_bde);
		  
		 List<String> TH = new ArrayList<String>();
			
			TH.add("Command Name");
			TH.add("Corps/Area Name");
			TH.add("Division name ");
			TH.add("Brigade  Name");
			TH.add("Unit Name");
			TH.add("Sus No");
			
			if (anml_type.equals("Army Equines")) {
				TH.add("Type of Animal");
			}else {
				TH.add("Specialization");
			}
			TH.add("UE");
			TH.add("UH");
			
			String Heading = "\nData Updated Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}
	 
	 @RequestMapping(value = "/deleteHospital", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteHospital(int id, HttpServletRequest request, HttpSession sessionA,
				Authentication authentication) {

			List<String> liststr = new ArrayList<String>();
			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				liststr.add("You do not have permission to access This Operation.");
				return liststr;
			}
			try {

				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				session.close();

				if (app > 0) {
					liststr.add("Deleted Successfully.");
				} else {
					liststr.add("Data not Delete.");
				}
				return liststr;

			} catch (Exception e) {

				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				return liststr;
			}

		}
	 
	//MITESH
			public @ResponseBody List<String> getAwardList(Object anml_type, HttpSession sessionA) {

				int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session
						.createQuery("select distinct award_type,id from TB_ANIMALS_AWARD_MASTER where anml_type=:anml_type ");
				q.setParameter("anml_type", anml_type);
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			
			public @ResponseBody List<String> getHospList(Object anml_type, HttpSession sessionA) {

				int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session
						.createQuery("select distinct type_hospital,id from TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER where anml_type=:anml_type ");
				q.setParameter("anml_type", anml_type);
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			
			public @ResponseBody List<String> getempList(Object anml_type, HttpSession sessionA) {

				int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session
						.createQuery("select distinct emp_type,id from TMS_TB_MISO_EMPLOYMENT_MASTER where anml_type=:anml_type ");
				q.setParameter("anml_type", anml_type);
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			
			public @ResponseBody List<String> getvaccineList(Object anml_type, HttpSession sessionA) {

				int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session
						.createQuery("select distinct vaccine_name,id from TMS_TB_MISO_VACCINE_MASTER where anml_type=:anml_type ");
				q.setParameter("anml_type", anml_type);
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			
////////////////Get Specialisation List army dog //////////////////

public @ResponseBody List<String> getsplzList1(HttpSession sessionA) {

	int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());

	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select type_splztn,id from TMS_TB_MISO_SPLZ_MASTER where anml_type='Army Dog' and id > 0 ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	return list;
}
/////////////////////////////////////////////////////



}
