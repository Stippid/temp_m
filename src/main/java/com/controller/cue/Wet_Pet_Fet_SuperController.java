package com.controller.cue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_MasterDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.cue.Wet_Pet_Fet_SuperDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Wet_Pet_Fet_SuperController {
	@Autowired
	private Wet_Pet_Fet_SuperDAO wetdao;

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Cue_MasterDAO masterDAO;

	cueContoller M = new cueContoller();

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/upload_WET_PETSuper", method = RequestMethod.GET)
	public ModelAndView upload_WET_PETSuper(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_WET_PETSuper", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
	 if(roleAccess.equals("Line_dte"))	{
		
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("msg", msg);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WET_PETSuperTiles", "upload_wet_pet_fetSuperCmd",
				new CUE_TB_MISO_MMS_WET_PET_MAST_DET());
	}

	@RequestMapping(value = "/admin/upload_WET_PETSuper1", method = RequestMethod.POST)
	public ModelAndView upload_WET_PETSuper1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "unit_type1", required = false) String unit_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "wet_pet1", required = false) String wet_pet,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "uploaded_wetpet1", required = false) String uploaded_wetpet,
			@RequestParam(value = "arm1", required = false) String arm) {
		String roleType = session.getAttribute("roleType").toString();

		List<Map<String, Object>> list = wetdao.getWetPetSuperReportDetailUrl1(wet_pet, wet_pet_no, unit_type, arm,
				sponsor_dire, uploaded_wetpet, roleType);
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
	 if(roleAccess.equals("Line_dte"))	{
		
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("list", list);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("uploaded_wetpet1", uploaded_wetpet);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("roleType", roleType);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WET_PETSuperTiles");
	}

	@RequestMapping(value = "/upload_wet_pet_fetSuperAct", method = RequestMethod.POST)
	public ModelAndView upload_wet_pet_fetSuperAct(
			@ModelAttribute("upload_wet_pet_fetSuperCmd") CUE_TB_MISO_MMS_WET_PET_MAST_DET rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		int roleid = (Integer) session.getAttribute("roleid");
		String wet_pet_no = request.getParameter("wet_pet_no");
		String wet_pet = request.getParameter("wet_pet"); 
		String arm = request.getParameter("arm");
		String sponsor_dire = request.getParameter("sponsor_dire");
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();

		try {
			if (wet_pet == "" || wet_pet == null || wet_pet == "null" || wet_pet.equals(null)) {
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (validation.checkWETypeLength(wet_pet) == false) {
				model.put("msg", validation.wetpetTypeMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getUploaded_wetpet().equals("")) {
				model.put("msg", "Please Enter Approved Uploaded Document No");
				return new ModelAndView("redirect:upload_WET_PETSuper");

			}
			if (validation.checkWetPetLength(rs.getUploaded_wetpet()) == false) {
				model.put("msg", validation.appUpWetpetDocMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getWet_pet_no().equals("")) {
				model.put("msg", "Please enter WET/PET/FET Document No");
				return new ModelAndView("redirect:upload_WET_PETSuper");

			}
			if (validation.checkWetPetLength(rs.getWet_pet_no()) == false) {
				model.put("msg", validation.wetpetnoMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			String answer = request.getParameter("answer");
			if (answer == null) {
				model.put("msg", "Please select New Document");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (answer.equals("No")) {
				if (rs.getSuperseeded_wet_pet().equals("")) {
					model.put("msg", "Please Select Superseeded Document No");
					return new ModelAndView("redirect:upload_WET_PETSuper");
				}
			}
			if (validation.checkWetPetLength(rs.getSuperseeded_wet_pet()) == false) {
				model.put("msg", validation.supWetpetnoMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getUnit_type().equals("")) {
				model.put("msg", "Please Enter Document Title");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (validation.checkWepeLength(rs.getUnit_type()) == false) {
				model.put("msg", validation.wetPetTitleMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getDoc_type().equals("")) {
				model.put("msg", "Please Select Document Type");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getArm() == null || rs.getArm() == "0") {
				model.put("msg", "Please Select Arm");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (rs.getSponsor_dire() == null) {
				model.put("msg", "Please Select Sponsor Directorate");
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:upload_WET_PETSuper");
			}
			Boolean e = checkDetailsOfWetPetfet(wet_pet_no);
			if (e.equals(false)) {
				rs.setRoleid(roleid);
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				rs.setStatus("0");

				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int did = (Integer) sessionHQL1.save(rs);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
				model.put("msg", "Data saved Successfully");
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();

		} finally {

			tx0.commit();
			sessionHQL.close();
		}

		List<Map<String, Object>> list = wetdao.getWetPetSuperReportDetailUrl1(wet_pet, "", "", arm, sponsor_dire, "", "");
		model.put("list", list);
		model.put("wet_pet1", wet_pet);
		model.put("uploaded_wetpet1", rs.getUploaded_wetpet());
		model.put("wet_pet_no1", wet_pet_no);
		model.put("listsize", list.size());
		 if(roleAccess.equals("Line_dte"))	{
				
			 model.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				model.put("selectLine_dte",select);
				model.put("selectArmNameList",select);
				model.put("getArmNameList", M.getArmNameList());
				model.put("getLine_DteList",roledao.getLine_DteList(""));
			}
		
		return new ModelAndView("upload_WET_PETSuperTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfWetPetfet(String wet_pet_no) {
		String hql = " select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no=:wet_pet_no order by wet_pet_no ";
		List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			users = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////
	@RequestMapping(value = "/upload_WET_PET", method = RequestMethod.GET)
	public ModelAndView upload_WET_PET(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "id", required = false) String id,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_WET_PET", roleid);
      String roleAccess = session.getAttribute("roleAccess").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("id", id);
		if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		//Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WET_PETTiles", "upload_wet_pet_fetCmd", new cue_wet_pet());
	}

	@RequestMapping(value = "/upload_wet_pet_fetAct", method = RequestMethod.POST)
	public ModelAndView upload_wet_pet_fetAct(@ModelAttribute("upload_wet_pet_fetCmd") cue_wet_pet rs,
			@RequestParam(value = "file_wet", required = false) MultipartFile file_wet, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int roleid = (Integer) session.getAttribute("roleid");
		String wet_pet = request.getParameter("wet_pet");
		String roleType = session.getAttribute("roleType").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		String answer = request.getParameter("answer1");
		String wet_pet_no = request.getParameter("wet_pet_no");
		String doc_type = request.getParameter("doc_type");
		String doc_path1Ext = FilenameUtils.getExtension(file_wet.getOriginalFilename());
		String roleAccess = session.getAttribute("roleAccess").toString();
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
		try {
			
			if (wet_pet == "" || wet_pet == null || wet_pet == "null" || wet_pet.equals(null)) {
				model.put("msg", "Please Select Document ");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkWETypeLength(wet_pet) == false) {
				model.put("msg", validation.wetpetTypeMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getWet_pet_no().equals("")) {
				model.put("msg", "Please Enter WET/PET/FET Document No");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkWetPetLength(rs.getWet_pet_no()) == false) {
				model.put("msg", validation.wetpetnoMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkWepeLength(rs.getUnit_type()) == false) {
				model.put("msg", validation.wepetitleMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (answer == null) {
				model.put("msg", "Please select New Document");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (answer.equals("No")) {
				if (rs.getSuperseeded_wet_pet().equals("0")) {
					model.put("msg", "Please Select Superseeded Document No");
					return new ModelAndView("redirect:upload_WET_PET");
				}

				if (validation.checkWetPetLength(rs.getSuperseeded_wet_pet()) == false) {
					model.put("msg", validation.supWetpetnoMSG);
					return new ModelAndView("redirect:upload_WET_PET");
				}
			}

			if (rs.getValid_from().equals("")) {
				model.put("msg", "Please Select Effective From Date");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkDateLength(rs.getValid_from()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getValid_till().equals("")) {
				model.put("msg", "Please Select Effective To Date");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkDateLength(rs.getValid_till()) == false) {
				model.put("msg", validation.dateMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getDoc_type().equals("")) {
				model.put("msg", "Please Select Security Classification");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkModificationLength(rs.getDoc_type()) == false) {
				model.put("msg", validation.secclassMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getArm().equals("0")) {
				model.put("msg", "Please Select Arm");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			String arm_code = String.format("%04d", Integer.parseInt(rs.getArm()));
			if (validation.checkArmCodeLength(arm_code) == false) {
				model.put("msg", validation.arm_codeMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getSponsor_dire().equals("0")) {
				model.put("msg", "Please Select Sponsor Directorate");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkSponsorDireLength(rs.getSponsor_dire()) == false) {
				model.put("msg", validation.spodireMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (rs.getDoc_path() == "null") {
				model.put("msg", "Please Enter Document Path");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:upload_WET_PET");
			}
			
			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (file_wet.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:upload_WET_PET");
			}

			if (!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_WET_PET");
			}


			Boolean e1 = checkDetailsOfWetPet1(wet_pet_no);
			if (e1.equals(false)) {
				rs.setRoleid(roleid);
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				rs.setStatus("0");

				String fname = "";
				if (!file_wet.isEmpty()) {
					// code modify by Paresh on 05/05/20202
					try {
						DateWithTimeStampController timestamp = new DateWithTimeStampController();
						byte[] bytes = file_wet.getBytes();
						CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						if(fileValidation.check_PDF_File(bytes)) {
							String cueFilePath = session.getAttribute("cueFilePath").toString();
							File dir = new File(cueFilePath);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
									+ "_" + userid + "_CUEDOC.PDF";
							File serverFile = new File(fname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();
							rs.setDoc_path(fname);
						}else {
							model.put("msg", "Only *.pdf file extension allowed");
							return new ModelAndView("redirect:upload_WET_PET");
						}
					} catch (Exception e) {
					}
				}
				Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL2.beginTransaction();
				int did = (Integer) sessionHQL2.save(rs);
				sessionHQL2.getTransaction().commit();
				sessionHQL2.close();
				model.put("msg", "Data saved Successfully");
				model.put("id", did);
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}

		List<Map<String, Object>> list = masterDAO.upload_WET_PET1("", wet_pet, wet_pet_no, "", "", doc_type, "",roleAccess);
		model.put("wet_pet01", wet_pet);
	//	model.put("wet_pet_no1", wet_pet_no);
		model.put("doc_type1", "Restricted");
		model.put("list", list);
		model.put("listsize", list.size());
		if(roleAccess.equals("Line_dte")){	
			
			model.put("getArmNameList",M.getArmNameLine(roleSusNo));
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			model.put("selectLine_dte",select);
			model.put("selectArmNameList",select);
			model.put("getArmNameList", M.getArmNameList());
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		model.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WET_PETTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfWetPet1(String wet_pet_no) {
		String hql = " select distinct wet_pet_no from cue_wet_pet where wet_pet_no=:wet_pet_no ";
		List<cue_wet_pet> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			users = (List<cue_wet_pet>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	///////////////////////////////////

	@RequestMapping(value = "/getWetPetFetNo", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetNo(String val, HttpSession htpsession,
			HttpSession sessionA, String wet_pet_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer) htpsession.getAttribute("roleid");
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = htpsession.getAttribute("roleAccess").toString();
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		Query q = null;

		String qry = "";
		if (val != "" && val != null) {
			if (roleAccess.equals("Line_dte")) {
				qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and status='1' and sponsor_dire=:roleSusNo order by wet_pet_no";
				q = session.createQuery(qry).setMaxResults(10);
				q.setParameter("val", val);
				q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
				q.setParameter("roleSusNo", roleSusNo);
			}else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and status='1' order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("val", val);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
			}
		} else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and upper(wet_pet_no) like:wet_pet_no and status='1' order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionA, list1);
	}

	@RequestMapping(value = "/getWetPetFetNoAll", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetNoAll(String val, HttpSession htpsession,
			HttpSession sessionA, String wet_pet_no, String sponsor_dire)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String roleAccess = htpsession.getAttribute("roleAccess").toString();
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		String qry = "";
		if (val != "" && val != null) {
			if (roleAccess.equals("Line_dte")) {			
				qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and sponsor_dire=:roleSusNo order by wet_pet_no";
				q = session.createQuery(qry).setMaxResults(10);
				q.setParameter("val", val);
				q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
				q.setParameter("roleSusNo", roleSusNo);
			}
			else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no  order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("val", val);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
			}
		} else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionA, list1);
	}

	@RequestMapping(value = "/getWetPetFetUploadNo", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetUploadNo(String val, HttpSession htpsession,
			String newRoleid, String wet_pet_no, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer) htpsession.getAttribute("roleid");
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry = "";

		qry = "select distinct wet_pet_no from cue_wet_pet where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from cue_wet_pet where superseeded_wet_pet is not null)  and wet_pet=:wet_pet and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no";
		q = session.createQuery(qry).setMaxResults(10);
		q.setParameter("wet_pet", val);
		q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/getWetPetFetNoOnlySuperPage", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetNoOnlySuperPage(String val, HttpSession sessionUserId,
			String wet_pet_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Query q = null;
		String qry = "";
		if (roleAccess.equals("Line_dte")) {
			
			qry = "select distinct wet_pet_no from cue_wet_pet where wet_pet_no is not null and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and sponsor_dire=:roleSusNo order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("val", val);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
			q.setParameter("roleSusNo", roleSusNo);
		
			
		} else {
			qry = "select distinct wet_pet_no from cue_wet_pet where wet_pet_no is not null and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("val", val);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		}
	
//		Query q = session.createQuery(
//				"select distinct wet_pet_no from cue_wet_pet where wet_pet_no is not null and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no")
//				.setMaxResults(10);
//		q.setParameter("val", val);
//		q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/getDetailsBySuperseedNo", method = RequestMethod.POST)
	public @ResponseBody List<cue_wet_pet> getDetailsBySuperseedNo(String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select valid_from,valid_till,doc_type,sponsor_dire,arm from cue_wet_pet where wet_pet_no=:wet_pet_no ");
		q.setParameter("wet_pet_no", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/wet_pet_amendDetail", method = RequestMethod.GET)
	public ModelAndView wet_pet_amendDetail(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("wet_pet_amendDetail", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("wet_pet_amendDetailTile", "wet_pet_amendDetailCmd", new CUE_TB_MISO_MMS_WET_PET_DET());
	}

	@RequestMapping(value = "/admin/wet_pet_amendDetail1", method = RequestMethod.POST)
	public ModelAndView wet_pet_amendDetail1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "authorization1", required = false) String authorization,
			@RequestParam(value = "wet_type1", required = false) String wet_type) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("wet_type1", wet_type);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("authorization1", authorization);

		List<Map<String, Object>> list = wetdao.getWetPetEquipmentReportDetail1(wet_pet_no, item_seq_no, authorization,
				roleType);

		Mmap.put("list", list);
		Mmap.put("listsize", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("wet_pet_amendDetailTile");
	}

	@RequestMapping(value = "/wet_pet_amendDetailAct", method = RequestMethod.POST)
	public ModelAndView wet_pet_amendDetailAct(@ModelAttribute("wet_pet_amendDetailCmd") CUE_TB_MISO_MMS_WET_PET_DET rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		int roleid = (Integer) session.getAttribute("roleid");
		String wet_pet_no = request.getParameter("wet_pet_no");
		String item_seq_no = request.getParameter("item_seq_no");
		String we_pe = request.getParameter("wet_type");

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();

		try {
			if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			if (rs.getWet_pet_no() == "") {
				model.put("msg", "Please Enter WET/PET/FET No");
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}

			if (rs.getItem_seq_no() == "") {
				model.put("msg", "Please Enter Item/Eqpt Name");
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			if (rs.getAuthorization() == 0) {
				model.put("msg", "Please Enter Authorization");
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}

			if (validation.checkWETypeLength(rs.getWet_type()) == false) {
				model.put("msg", validation.wetpetTypeMSG);
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			if (validation.checkWetPetLength(rs.getWet_pet_no()) == false) {
				model.put("msg", validation.wetpetnoMSG);
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			if (validation.checkFormationLength(rs.getItem_seq_no()) == false) {
				model.put("msg", validation.itemcodeMSG);
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			String auth_amt = Double.toString(rs.getAuthorization());
			if (validation.checkAuth_amtLength(auth_amt) == false) {
				model.put("msg", validation.auth_amtMSG);
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:wet_pet_amendDetail");
			}
			Boolean e = checkDetailsOfWetPetExist(wet_pet_no, item_seq_no);
			if (e.equals(false)) {
				rs.setRoleid(roleid);
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				rs.setStatus("0");
				Session sessionHQL3 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL3.beginTransaction();
				int did = (Integer) sessionHQL3.save(rs);
				sessionHQL3.getTransaction().commit();
				sessionHQL3.close();
				model.put("msg", "Data saved Successfully");
			} else {
				model.put("msg", "Data Already Exists!");
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		} finally {

		}

		List<Map<String, Object>> list = wetdao.getWetPetEquipmentReportDetail1(wet_pet_no, "", "", "");
		model.put("wet_type1", we_pe);
		model.put("wet_pet_no1", wet_pet_no);
		model.put("list", list);
		model.put("listsize", list.size());
		return new ModelAndView("wet_pet_amendDetailTile");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfWetPetExist(String wet_pet_no, String item_seq_no) {

		String hql = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_DET where wet_pet_no=:wet_pet_no and item_seq_no=:item_seq_no order by wet_pet_no";
		List<CUE_TB_MISO_MMS_WET_PET_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			query.setParameter("item_seq_no", item_seq_no);
			users = (List<CUE_TB_MISO_MMS_WET_PET_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	/////////////////// search
	@RequestMapping(value = "/searchWetPetSuper", method = RequestMethod.GET)
	public ModelAndView searchWetPetSuper(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchWetPetSuper", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
          if(roleAccess.equals("Line_dte")){	
			
        	  model.put("getArmNameList",M.getArmNameLine(roleSusNo));
        	  model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			model.put("selectLine_dte",select);
			model.put("selectArmNameList",select);
			model.put("getArmNameList", M.getArmNameList());
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		model.put("msg", msg);
//		model.put("getArmNameList", M.getArmNameList());
//		model.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWetPetSuperTile");
	}

	@RequestMapping(value = "/admin/searchWetPetSuper1", method = RequestMethod.POST)
	public ModelAndView searchWetPetSuper1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "unit_type1", required = false) String unit_type,
			@RequestParam(value = "wet_pet1", required = false) String wet_pet,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "uploaded_wetpet1", required = false) String uploaded_wetpet,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "arm1", required = false) String arm) {
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("unit_type1", unit_type);
		Mmap.put("status1", status);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		String roleAccess = session.getAttribute("roleAccess").toString();
		List<Map<String, Object>> list = wetdao.getWetPetSuperReportDetailUrl(status, wet_pet, wet_pet_no, unit_type,
				arm, sponsor_dire, uploaded_wetpet, roleType,roleAccess);
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		   
       if(roleAccess.equals("Line_dte")){	
			
    	   Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
    	   Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		Mmap.put("roleType", roleType);
		return new ModelAndView("searchWetPetSuperTile");
	}

	@RequestMapping(value = "/updaterejectuploadwetpetUrl", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectuploadwetpetUrl(HttpSession session,String remarks, String letter_no, int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers1("cue_tb_miso_mms_wet_pet_mast_det", remarks, id,
				letter_no,username);
		return list2;
	}

	@RequestMapping(value = "/admin/ApproveduploadwetpetUrl", method = RequestMethod.POST)
	public ModelAndView ApproveduploadwetpetUrl(@ModelAttribute("appType") String appType, HttpServletRequest request,
			@ModelAttribute("appid") int appid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, @RequestParam(value = "wet_pet_no2", required = false) String wet_pet_no,
			@RequestParam(value = "unit_type2", required = false) String unit_type,
			@RequestParam(value = "wet_pet2", required = false) String wet_pet,
			@RequestParam(value = "sponsor_dire2", required = false) String sponsor_dire,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "uploaded_wetpet2", required = false) String uploaded_wetpet,
			@RequestParam(value = "superno2", required = false) String superno,
//			String superseeded_wet_pet = request.getParameter("superseeded_wet_pet"); 
			@RequestParam(value = "arm2", required = false) String arm,
			@RequestParam(value = "statusp", required = false) String pstatus) {

		String roleType = session.getAttribute("roleType").toString();
		int roleid = (Integer) session.getAttribute("roleid");
		String username = session.getAttribute("username").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		
		 if(!(pstatus.equals("2"))) {
			Mmap.put("msg", wetdao.updatecapdatawetpet(wet_pet_no,superno,roleid, username, roleType,pstatus));
		 }
		Mmap.put("msg", wetdao.setApproveduploadwetpet(appid, roleid, username, roleType));
		
	
		
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("unit_type1", unit_type);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("status1", status);

		List<Map<String, Object>> list = wetdao.getWetPetSuperReportDetailUrl(status, wet_pet, wet_pet_no, unit_type,
				arm, sponsor_dire, uploaded_wetpet, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWetPetSuperTile");
	}

	@RequestMapping(value = "/deleteWetPetSuperUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteWetPetSuperUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(wetdao.setDeleteWetPetSuper(deleteid));
		return list2;
	}

	@RequestMapping(value = "/updateWetPetSuperUrl")
	public ModelAndView updateWetPetSuperUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();   
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
		
	       if(roleAccess.equals("Line_dte")){	
				
	    	   model.put("getArmNameList",M.getArmNameLine(roleSusNo));
	    	   model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				model.put("selectLine_dte",select);
				model.put("selectArmNameList",select);
				model.put("getArmNameList", M.getArmNameList());
				model.put("getLine_DteList",roledao.getLine_DteList(""));
			}
		model.put("editWetPetSuperCmd", wetdao.getWetPetSuperDetailsByid(updateid));
//		model.put("getArmNameList", M.getArmNameList());
//		model.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("editupload_WET_PETSuperTiles");
	}

	@RequestMapping(value = "/WetPetSuperEditAct", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView WetPetSuperEditAct(@ModelAttribute("editWetPetSuperCmd") CUE_TB_MISO_MMS_WET_PET_MAST_DET updateid, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session,HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		
		
		if (updateid.getWet_pet_no().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please enter WET/PET/FET Document No");
			return new ModelAndView("redirect:updateWetPetSuperUrl");

		}
		if (validation.checkWetPetLength(updateid.getWet_pet_no()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.wetpetnoMSG);
			return new ModelAndView("redirect:updateWetPetSuperUrl");
		}
		
		String answer = request.getParameter("answer");
		if (answer == null) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please select New Document");
			return new ModelAndView("redirect:updateWetPetSuperUrl");
		}
		if (answer.equals("No")) {
			if (updateid.getSuperseeded_wet_pet().equals("")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Select Superseeded Document No");
				return new ModelAndView("redirect:updateWetPetSuperUrl");
			}
			if (validation.checkWetPetLength(updateid.getSuperseeded_wet_pet()) == false) {
				model.put("updateid", updateid.getId());
				model.put("msg", validation.supWetpetnoMSG);
				return new ModelAndView("redirect:updateWetPetSuperUrl");
			}
		}
		
		if (updateid.getUnit_type().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Enter Document Title");
			return new ModelAndView("redirect:upload_WET_PETSuper");
		}
		if (validation.checkWepeLength(updateid.getUnit_type()) == false) {
			model.put("updateid", updateid.getId());
			model.put("msg", validation.wetPetTitleMSG);
			return new ModelAndView("redirect:upload_WET_PETSuper");
		}
		if (updateid.getDoc_type().equals("")) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Document Type");
			return new ModelAndView("redirect:upload_WET_PETSuper");
		}
		if (updateid.getArm() == null || updateid.getArm() == "0") {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Arm");
			return new ModelAndView("redirect:upload_WET_PETSuper");
		}
		if (updateid.getSponsor_dire() == null) {
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Sponsor Directorate");
			return new ModelAndView("redirect:upload_WET_PETSuper");
		}
//		if (validation.checkRemarksLength(updateid.getRemarks()) == false) {
//			model.put("updateid", updateid.getId());
//			model.put("msg", validation.remarksMSG);
//			return new ModelAndView("redirect:upload_WET_PETSuper");
//		}
		
		
		String username = session.getAttribute("username").toString();
		wetdao.UpdateWetPetSuperDetails(updateid, username);
		model.put("msg", "Updated Successfully");
		return new ModelAndView("redirect:upload_WET_PETSuper");
	}

	@RequestMapping(value = "/searchWetPetAmendmentsDetails", method = RequestMethod.GET)
	public ModelAndView searchWetPetAmendmentsDetails(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchWetPetAmendmentsDetails", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("searchWetPetAmendmentsDetailsTile");
	}

	@RequestMapping(value = "/admin/searchWetPetAmendmentsDetails1", method = RequestMethod.POST)
	public ModelAndView searchWetPetAmendmentsDetails1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "authorization1", required = false) String authorization,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "wet_pet1", required = false) String wet_pet) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("status1", status);
		List<Map<String, Object>> list = wetdao.getWetPetEquipmentReportDetail(wet_pet_no, item_seq_no, authorization,
				status, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("searchWetPetAmendmentsDetailsTile");
	}

	@RequestMapping(value = "/admin/Approved_WetPetAmendmentsDetails", method = RequestMethod.POST)
	public ModelAndView Approved_WetPetAmendmentsDetails(@ModelAttribute("appid") int appid, HttpSession session,
			ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "wet_pet_no2", required = false) String wet_pet_no,
			@RequestParam(value = "item_seq_no2", required = false) String item_seq_no,
			@RequestParam(value = "authorization2", required = false) String authorization,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "wet_pet2", required = false) String wet_pet) {
		Mmap.put("msg", wetdao.setApprovedWet_pet_amendmentdetails(appid));
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		List<Map<String, Object>> list = wetdao.getWetPetEquipmentReportDetail(wet_pet_no, item_seq_no, authorization,
				status, roleType, roleAccess);
		Mmap.put("wet_pet1", wet_pet);
		Mmap.put("wet_pet_no1", wet_pet_no);
		Mmap.put("status1", status);
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("searchWetPetAmendmentsDetailsTile");
	}

	@RequestMapping(value = "/deleteWetPetAmendmentsUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteWetPetAmendmentsUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(wetdao.setDeleteWetPetAmendments(deleteid));
		return list2;
	}

	////////////////////////////// edit////////////////////////////////
	@RequestMapping(value = "/updateWetPetAmendmentsUrl")
	public ModelAndView updateWetPetAmendmentsUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();

		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("editWetPetAmendmentsCmd", wetdao.getWetPetAmendmentsDetailsByEditId(updateid));
		return new ModelAndView("editwet_pet_amendmentsDetailsTiles");
	}

	@RequestMapping(value = "/WetPetAmendmentsEditAct", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView CUE_TB_MISO_MMS_WET_PET_DET(
			@ModelAttribute("editWetPetAmendmentsCmd") CUE_TB_MISO_MMS_WET_PET_DET updateid, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session11,
			HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkFormationLength(updateid.getItem_seq_no()) == false) {
			model.put("msg", validation.itemEqpNoMSG);
			return new ModelAndView("redirect:updateWetPetAmendmentsUrl");
		}

		String auth_amt = Double.toString(updateid.getAuthorization());
		if (validation.checkAuth_amtLength(auth_amt) == false) {
			model.put("msg", validation.auth_amtMSG);
			return new ModelAndView("redirect:updateWetPetAmendmentsUrl");
		}

		if (validation.checkRemarksLength(updateid.getRemarks()) == false) {
			model.put("msg", validation.remarksMSG);
			return new ModelAndView("redirect:updateWetPetAmendmentsUrl");
		}

		String username = session11.getAttribute("username").toString();
		Session sessioncount = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = sessioncount.beginTransaction();
		Query q1 = sessioncount.createQuery(
				"select count(*) from CUE_TB_MISO_MMS_WET_PET_DET where wet_pet_no=:wet_pet_no and item_seq_no=:item_seq_no and id !=:id");
		q1.setParameter("wet_pet_no", updateid.getWet_pet_no());
		q1.setParameter("item_seq_no", updateid.getItem_seq_no());
		q1.setParameter("id", updateid.getId());
		Long count2 = (Long) q1.uniqueResult();
		model.put("count", count2);
		transaction.commit();
		sessioncount.close();

		if (count2 == 0) {
			wetdao.UpdateWetPetAmendmentsDetails(updateid, username);
			model.put("msg", "Updated Successfully");
			return new ModelAndView("redirect:wet_pet_amendDetail");
		} else {
			model.put("msg", "Data already exist !");
			model.put("editWetPetAmendmentsCmd", wetdao.getWetPetAmendmentsDetailsByEditId(updateid.getId()));
			return new ModelAndView("editwet_pet_amendmentsDetailsTiles");
		}

	}

	@RequestMapping(value = "/getWetPetsupercddList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetsupercddList(HttpSession htpsession, String we_r,
			HttpSession sessionUserId, String wet_pet_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer) htpsession.getAttribute("roleid");
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry = "";
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte") ){
		qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE wet_pet_no is not null and wet_pet_no NOT IN (SELECT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet IS NOT NULL) and wet_pet =:we_r and upper(wet_pet_no) like:wet_pet_no and sponsor_dire=:roleSusNo order by wet_pet_no";
		}
		else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE wet_pet_no is not null and wet_pet_no NOT IN (SELECT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet IS NOT NULL) and wet_pet =:we_r and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no";

		}
		Query q = session.createQuery(qry).setMaxResults(10);
		q.setParameter("we_r", we_r);
		q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte")){
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/getWetPetsupercddList12", method = RequestMethod.POST)
	public @ResponseBody List<String> getWetPetsupercddList12(HttpSession sessionUserId, String we_r) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte") ){

		 qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE wet_pet_no is not null and wet_pet_no NOT IN (SELECT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet IS NOT NULL) and wet_pet =:we_r and sponsor_dire=:roleSusNo order by wet_pet_no";
		}
		else {
			 qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE wet_pet_no is not null and wet_pet_no NOT IN (SELECT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet IS NOT NULL) and wet_pet =:we_r  order by wet_pet_no";

		}
		Query q = session.createQuery(qry);
		q.setParameter("we_r", we_r);
		
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte")){
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getupload_table_title", method = RequestMethod.POST)
	public @ResponseBody List<cue_wet_pet> getupload_table_title(String val, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where wet_pet_no=:wet_pet_no ");
		q.setParameter("wet_pet_no", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/gettable_titleUpload", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> gettable_titleUpload(String val, HttpSession sessionUserId,
			String unit_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		String qry ="";
		if(roleAccess.equals("Line_dte")){	
			qry = "select distinct unit_type from cue_wet_pet where wet_pet =:val and upper(unit_type) like :unit_type and sponsor_dire=:roleSusNo";
					
		}
		else {
			qry = "select distinct unit_type from cue_wet_pet where wet_pet =:val and upper(unit_type) like :unit_type";
		}
		Query q = session.createQuery(qry)
				.setMaxResults(10);
		q.setParameter("val", val);
		q.setParameter("unit_type", unit_type.toUpperCase() + "%");
		if(roleAccess.equals("Line_dte")){	
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/gettable_title_wet_noUpload", method = RequestMethod.POST)
	public @ResponseBody List<cue_wet_pet> gettable_title_wet_noUpload(String val, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where unit_type=:val ");
		q.setParameter("val", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/gettable_title", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> gettable_title(String val, HttpSession sessionUserId,
			String unit_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry ="";
		if(roleAccess.equals("Line_dte")){	
			qry ="select distinct unit_type from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet =:wet_pet and upper(unit_type) like :unit_type and sponsor_dire=:roleSusNo";

		}else {
			qry ="select distinct unit_type from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet =:wet_pet and upper(unit_type) like :unit_type";

		}
		Query q = session.createQuery(qry).setMaxResults(10);
		q.setParameter("wet_pet", val);
		q.setParameter("unit_type", unit_type.toUpperCase() + "%");
		if(roleAccess.equals("Line_dte")){	
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/gettable_title_wet_no", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> gettable_title_wet_no(String val,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_MMS_WET_PET_MAST_DET where unit_type=:unit_type ");
		q.setParameter("unit_type", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> list = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/updaterejectactionWetPetAmendment", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectactionWetPetAmendment(HttpSession session,String remarks, String letter_no, int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers1("cue_tb_miso_mms_wet_pet_det", remarks, id,
				letter_no,username);
		return list2;
	}
	
	@RequestMapping(value = "/getWetPetFetNodata", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetNodata(String val, HttpSession htpsession,
			HttpSession sessionA, String wet_pet_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer) htpsession.getAttribute("roleid");
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		String qry = "";
		if (val != "" && val != null) {
			if(htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm")) {
				qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and status='1' and sponsor_dire=:roleSusNo order by wet_pet_no";
	
			}
			else {

			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no and status='1' order by wet_pet_no";
			}q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("val", val);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		
		}else {
			qry = "select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet_no NOT IN (select superseeded_wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where superseeded_wet_pet is not null) and upper(wet_pet_no) like:wet_pet_no and status='1' order by wet_pet_no";
			q = session.createQuery(qry).setMaxResults(10);
			q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		}
		if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm") ){
//			q.setParameter("arm",arm);
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionA, list1);
	}
}
