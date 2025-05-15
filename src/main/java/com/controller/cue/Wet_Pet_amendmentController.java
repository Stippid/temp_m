package com.controller.cue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_Wet_Pet_Amdt_EditDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_CES;
import com.models.UploadWetPetAamendmentToDocument;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Wet_Pet_amendmentController {
	@Autowired
	private Cue_Wet_Pet_Amdt_EditDAO masterDAO;
	cueContoller M = new cueContoller();
	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController validation = new ValidationController();

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/upload_WET_PET_amendment", method = RequestMethod.GET)
	public ModelAndView upload_WET_PET_amendment(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("upload_WET_PET_amendment", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("upload_WET_PET_amendmentTiles", "upload_wet_pet_amendment_to_documentCMD",
				new UploadWetPetAamendmentToDocument());
	}

	@RequestMapping(value = "/getWetPetNumberforamed", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetNumberforamed(String we_r, HttpSession sessionUserId,
			String wet_pet_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct wet_pet_no from cue_wet_pet where wet_pet_no !='' and wet_pet_no is not null and wet_pet =:wet_pet and upper(wet_pet_no) like :wet_pet_no order by wet_pet_no")
				.setMaxResults(10);
		q.setParameter("wet_pet", we_r);
		q.setParameter("wet_pet_no", wet_pet_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);

	}

	@RequestMapping(value = "/upload_wet_pet_amendment_to_documentAction1", method = RequestMethod.POST)
	public ModelAndView upload_wet_pet_amendment_to_documentAction1(
			@ModelAttribute("upload_wet_pet_amendment_to_documentCMD") UploadWetPetAamendmentToDocument rs,
			@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());
		int roleid = (Integer) session.getAttribute("roleid");

		String we_pe = request.getParameter("wet_pet");
		String wet_pet_no = request.getParameter("wet_pet_no");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();

		try {
			final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (doc_path1.getSize() > fileSizeLimit) {
				model.put("msg", "File size should be less then 2 MB");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
				model.put("msg", "Please Select Document ");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (rs.getWet_pet_no().equals("")) {
				model.put("msg", "Please Enter WET/PET No");
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}

			String fname = "";
			String extension = "";
			if (!doc_path1.isEmpty()) {
				// code modify by Paresh on 05/05/20202
				try {
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = doc_path1.getBytes();
					CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
					if(fileValidation.check_PDF_File(bytes)) {
						String cueFilePath = session.getAttribute("cueFilePath").toString();
						File dir = new File(cueFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
								+ userid + "_CUEDOC.PDF";
						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						rs.setDoc_path(fname);
					}else {
						model.put("msg", "Only *.pdf file extension allowed");
						return new ModelAndView("redirect:upload_WET_PET_amendment");
					}
				} catch (Exception e) {
				}
			}
			if (validation.checkWetPetLength(rs.getWet_pet_no()) == false) {
				model.put("msg", validation.wetpetnoMSG);
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (validation.checkRemarksLength(rs.getDoc_path()) == false) {
				model.put("msg", validation.amendDocMSG);
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (validation.checkRemarksLength(rs.getRemark()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			if (validation.checkWetPetLength(rs.getLetter_no()) == false) {
				model.put("msg", validation.authLtrnoMSG);
				return new ModelAndView("redirect:upload_WET_PET_amendment");
			}
			List<cue_wet_pet> newamd = masterDAO.getAmendmentDetails_Wet_PetByid(rs.getWet_pet_no());
			String unit_type_wet_pet = newamd.get(0).getUnit_type();
			//Boolean e = checkDetailsOfExistingDatawet_pet_no(wet_pet_no);
			//if (e.equals(false)) {
				rs.setRoleid(roleid);
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				rs.setStatus("0");
				rs.setAmdt_title_wet_pet(unit_type_wet_pet);
				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int did = (Integer) sessionHQL1.save(rs);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
				model.put("msg", "Data saved Successfully");
			/*} else {
				model.put("msg", "Data Already Exists!");
			}*/
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}

		List<Map<String, Object>> list = masterDAO.getAttributeFromCategorySearchWetPetAmendment("", wet_pet_no, "");
		model.put("wet_pet01", we_pe);
		model.put("wet_pet_no01", wet_pet_no);
		model.put("list", list);
		return new ModelAndView("upload_WET_PET_amendmentTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfExistingDatawet_pet_no(String wet_pet_no) {
		String qry = "from UploadWetPetAamendmentToDocument where wet_pet_no=:wet_pet_no";
		List<UploadWetPetAamendmentToDocument> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(qry);
			q.setParameter("wet_pet_no", wet_pet_no);
			users = (List<UploadWetPetAamendmentToDocument>) q.list();
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

	@RequestMapping(value = "/getDetailsByWetPetCondiNo", method = RequestMethod.POST)
	public @ResponseBody List<cue_wet_pet> getDetailsByWetPetCondiNo(String val, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from cue_wet_pet where wet_pet_no=:wet_pet_no ");
		q.setParameter("wet_pet_no", val);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getCesnoByCesName", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_CES> getCesnoByCesName(String val, HttpSession sessionUserId) {
		val = val.replace("&#40;", "(");
		val = val.replace("&#41;", ")");
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q;
		if (val != "") {
			q = session.createQuery(" from CUE_TB_MISO_CES where ces_cces_name =:val ");
			q.setParameter("val", val);
		} else {
			q = session.createQuery(" from CUE_TB_MISO_CES ");
		}

		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_CES> list = (List<CUE_TB_MISO_CES>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/admin/upload_WET_PET_Amendment1", method = RequestMethod.POST)
	public ModelAndView upload_WET_PET_Amendment1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "wet_pet01", required = false) String wet_pet,
			@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
			@RequestParam(value = "arm_code1", required = false) String arm_code,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "date_upload1", required = false) String date_upload,
			@RequestParam(value = "date_upload3", required = false) String date_upload3,
			@RequestParam(value = "remark1", required = false) String remark,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "letter_no1", required = false) String letter_no) {

		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("wet_pet01", wet_pet);
		Mmap.put("status1", status);
		Mmap.put("wet_pet_no01", wet_pet_no);

		List<Map<String, Object>> list = masterDAO.getAttributeFromCategorySearchWetPetAmendment(status, wet_pet_no,
				roleType);
		Mmap.put("list", list);
		Mmap.put("listsize", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("upload_WET_PET_amendmentTiles");
	}
}
