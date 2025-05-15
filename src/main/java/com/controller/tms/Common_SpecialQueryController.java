package com.controller.tms;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.SpecialQueryDAO;
import com.dao.tms.SpecialQueryDAOImpl;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Common_SpecialQueryController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	SpecialQueryDAO sDao = new SpecialQueryDAOImpl();
	B_VehSearchMVCRController s = new B_VehSearchMVCRController();
	

@Autowired
	RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/ba_no_amendment", method = RequestMethod.GET)
	public ModelAndView ba_no_amendment(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		if(!roleAccess.equals("MISO")){
			return new ModelAndView("AccessTiles");
		}
		
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getMvcrpartCissuetypeList1", s.getMvcrpartCissuetypeList());
		Mmap.put("msg", msg);
		return new ModelAndView("ba_no_amendmentTiles");
	}

	@RequestMapping(value = "/admin/RevertDataBa_noWise", method = RequestMethod.POST)
	public @ResponseBody String RevertDataBa_noWise(HttpServletRequest request, HttpSession session, String a,String ba_no){
		if(sDao.RevertBaNoDetails(a, ba_no) == true){
			return "Reverted";
		}else{
			return "Not Reverted";
		}
	}
	
	@RequestMapping(value = "/admin/getSearchBaNoReqChild", method = RequestMethod.POST)
	public @ResponseBody String getSearchBaNoReqChild(HttpServletRequest request, HttpSession session, String a,String ba_no) {
		if (sDao.ifExistbano(a, ba_no) == true){
			return "Success dtl";
		} else {
			return "Failure dtl";
		}
	}

	@RequestMapping(value = "/ba_no_amendmentAction", method = RequestMethod.POST)
	public ModelAndView Ba_no_amendmentAction(@ModelAttribute("ba_no_amendmentCMD") TB_TMS_BANUM_DIRCTRY rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		String veh_cat = request.getParameter("hdveh_cat");
		String ba_no = request.getParameter("ba_no");
		String year_of_induction = request.getParameter("year_of_induction");
		String suffix = request.getParameter("suffix");
		String m_digit = request.getParameter("m_digit");
		String n_ba_no = year_of_induction + m_digit + suffix;

		int updatedEntities = 0;
		int updatedEntities2 = 0;
		Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionUpdate.beginTransaction();
		if (n_ba_no != "" && !n_ba_no.equals("")) {
			String hqlUpdate = "update TB_TMS_BANUM_DIRCTRY r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
			String hqlUpdate2 = "update Tms_Banum_Req_Child r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
			updatedEntities = sessionUpdate.createQuery(hqlUpdate).setString("n_ba_no", n_ba_no)
					.setString("ba_no", ba_no).executeUpdate();
			updatedEntities2 = sessionUpdate.createQuery(hqlUpdate2).setString("n_ba_no", n_ba_no)
					.setString("ba_no", ba_no).executeUpdate();
		}
		if (veh_cat.equals("A")) {
			String hqlcensus1 = "update TB_TMS_CENSUS_DRR_DIR_DTL r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
			String hqlcensus2 = "update TB_TMS_CENSUS_RETURN r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
			int updatedEntitie3 = sessionUpdate.createQuery(hqlcensus1).setString("n_ba_no", n_ba_no)
					.setString("ba_no", ba_no).executeUpdate();
			int updatedEntitie4 = sessionUpdate.createQuery(hqlcensus2).setString("n_ba_no", n_ba_no)
					.setString("ba_no", ba_no).executeUpdate();
			if (updatedEntities > 0 && updatedEntities2 > 0) {
				if (updatedEntitie3 > 0 && updatedEntitie4 > 0) {
				}
			}
			tx.commit();
			sessionUpdate.close();
		} else if (veh_cat.equals("B")) {
			String Type_of_issue = request.getParameter("type_of_issue");
			String hqldrr1 = "";
			int updatedEntitie5 = 0;
			if (n_ba_no != "" && !n_ba_no.equals("")) {
				hqldrr1 = "update TB_TMS_DRR_DIR_DTL r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
				updatedEntitie5 = sessionUpdate.createQuery(hqldrr1).setString("n_ba_no", n_ba_no)
						.setString("ba_no", ba_no).executeUpdate();
			}
			String hqldrr2 = "";
			int updatedEntitie6 = 0;
			if (Type_of_issue.equals("") && !n_ba_no.equals("")) {
				hqldrr2 = "update TB_TMS_MVCR_PARTA_DTL r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
				updatedEntitie6 = sessionUpdate.createQuery(hqldrr2).setString("n_ba_no", n_ba_no)
						.setString("ba_no", ba_no).executeUpdate();
			} else if (!n_ba_no.equals("") && !Type_of_issue.equals("")) {
				hqldrr2 = "update TB_TMS_MVCR_PARTA_DTL r set r.ba_no =:n_ba_no , r.issue_type =:Type_of_issue where r.ba_no = :ba_no";
				updatedEntitie6 = sessionUpdate.createQuery(hqldrr2).setString("n_ba_no", n_ba_no)
						.setString("ba_no", ba_no).setString("Type_of_issue", Type_of_issue).executeUpdate();
			} else {
				hqldrr2 = "update TB_TMS_MVCR_PARTA_DTL r set r.issue_type =:Type_of_issue where r.ba_no = :ba_no";
				updatedEntitie6 = sessionUpdate.createQuery(hqldrr2).setString("ba_no", ba_no)
						.setString("Type_of_issue", Type_of_issue).executeUpdate();
			}
			String hqldrr3 = "";
			int updatedEntitie7 = 0;
			if (n_ba_no != "" && !n_ba_no.equals("")) {
				hqldrr3 = "update TB_TMS_RELIEF_PROG_HISTORY r set r.ba_no =:n_ba_no where r.ba_no = :ba_no";
				updatedEntitie7 = sessionUpdate.createQuery(hqldrr3).setString("n_ba_no", n_ba_no)
						.setString("ba_no", ba_no).executeUpdate();
			}

			if (updatedEntities > 0 && updatedEntities2 > 0) {
				if (updatedEntitie5 > 0 && updatedEntitie6 > 0 && updatedEntitie7 > 0 ) {
				}
			}
			tx.commit();
			sessionUpdate.close();
		}
		model.put("msg", "BA No Updated Successfully.");
		return new ModelAndView("redirect:ba_no_amendment");
	}
	// Screen BA NO Amendment Controller End

	// Screen Accidental Vehicle Controller Start (2)
	@RequestMapping(value = "/admin/accidental_vehicle", method = RequestMethod.GET)
	public ModelAndView Accidental_vehicle(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("accidental_vehicleTiles");
	}

	// Screen Accidental Vehicle Controller End
	@RequestMapping(value = "/accidental_vehicleAction", method = RequestMethod.POST)
	public ModelAndView Accidental_vehicleAction(@ModelAttribute("accidental_vehicleCMD") TB_TMS_BANUM_DIRCTRY rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		rs.setCreation_by(username);
		String ba_no = request.getParameter("ba_no");
		String authority = request.getParameter("authority");
		if (ba_no == "" || ba_no.equals("") || ba_no == null || ba_no.equals(null) || ba_no.equals("null")) {
			model.put("msg", "Please Enter Ba No.");
			return new ModelAndView("redirect:accidental_vehicle");
		} else {
			Session s1 = HibernateUtilNA.getSessionFactory().openSession();
			try {
				Transaction tx1 = s1.beginTransaction();
				String h1 = "update TB_TMS_BANUM_DIRCTRY c set c.status=:status, c.authority=:authority where c.ba_no = :ba_no";
				String h2 = "delete from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no";
				s1.createQuery(h1).setString("status", "Accident").setString("authority", authority)
						.setString("ba_no", ba_no).executeUpdate();
				s1.createQuery(h2).setString("ba_no", ba_no).executeUpdate();
				tx1.commit();
			} catch (Exception e) {
				s1.getTransaction().rollback();
			} finally {
				s1.close();
			}
		}
		model.put("msg", "Data Successfully Updated.");
		return new ModelAndView("redirect:accidental_vehicle");
	}
	// Screen Accidental Vehicle Controller End

	// Screen Amendment of Nomenclature Controller Start (3)
	@RequestMapping(value = "/admin/amendment_of_nomenclatere", method = RequestMethod.GET)
	public ModelAndView Amendment_of_nomenclatere(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("amendment_of_nomenclatereTiles");
	}

	@RequestMapping(value = "/admin/getSearchBaNoDir", method = RequestMethod.POST)
	public @ResponseBody String GetSearchBaNoDir(HttpServletRequest request, HttpSession session, String ba_no) {
		if (sDao.ifExistbano(ba_no) == true) {
			return "Success dtl";
		} else {
			return "Failure dtl";
		}
	}

	@RequestMapping(value = "/getBaNoToMCT", method = RequestMethod.POST)
	public @ResponseBody List<String> getBaNoToMCT(String ba_no, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select mct from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
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
				String mct = String.valueOf(list.get(i));
				encCode = c.doFinal(mct.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/amendment_of_nomenclatereAction", method = RequestMethod.POST)
	public ModelAndView Amendment_of_nomenclatereAction(
			@ModelAttribute("amendment_of_nomenclatereCMD") TB_TMS_BANUM_DIRCTRY rs, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		String ba_no = request.getParameter("ba_no");
		BigInteger mct = new BigInteger(request.getParameter("mct_no"));
		Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionUpdate.beginTransaction();
		String hqlUpdate = "update TB_TMS_BANUM_DIRCTRY r set r.mct =:mct, r.modify_by=:modify_by, r.modify_date=:modify_date where r.ba_no = :ba_no";
		sessionUpdate.createQuery(hqlUpdate).setBigInteger("mct", mct).setString("modify_by", username)
				.setTimestamp("modify_date", new Date()).setString("ba_no", ba_no).executeUpdate();
		tx.commit();
		Session sessionUpdate2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx2 = sessionUpdate2.beginTransaction();
		String hqlUpdate2 = "update Tms_Banum_Req_Child r set r.mct_number =:mct_number, r.modify_by=:modify_by, r.modify_date=:modify_date where r.ba_no = :ba_no";
		sessionUpdate2.createQuery(hqlUpdate2).setBigInteger("mct_number", mct).setString("modify_by", username)
				.setTimestamp("modify_date", new Date()).setString("ba_no", ba_no).executeUpdate();
		tx2.commit();
		sessionUpdate.close();
		sessionUpdate2.close();
		model.put("msg", "Amendment of Nomenclature Completed.");
		return new ModelAndView("redirect:amendment_of_nomenclatere");
	}
	// Screen Amendment of Nomenclature Controller End

	// Screen Addition of ba_no Controller Start (4)
	@RequestMapping(value = "/admin/addition_of_ba_no", method = RequestMethod.GET)
	public ModelAndView Addition_of_ba_no(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("addition_of_ba_noTiles", "addition_of_ba_noCMD", new Tms_Banum_Req_Prnt());
	}

	@RequestMapping(value = "/admin/getBaNoGenerate", method = RequestMethod.POST)
	public @ResponseBody String getBaNoGenerate(String ba_no, int serialNo, String veh_code, String dt_year) {
		String up_ba_no = sDao.baNoGeneration(serialNo, veh_code);
		String n_ba_no = dt_year + up_ba_no;
		if (sDao.ifExistbanoMVCR(n_ba_no) != true && sDao.ifExistbanoDRRDIR(n_ba_no) != true
				&& sDao.ifExistbano(n_ba_no) != true && sDao.ifExistbanoChild(n_ba_no) != true) {
			return n_ba_no;
		} else {
			return "Already Exist BA No";
		}
	}

	@RequestMapping(value = "/addition_of_ba_noAction", method = RequestMethod.POST)
	public ModelAndView Addition_of_ba_noAction(HttpServletRequest request, ModelMap model, HttpSession session) {
		Tms_Banum_Req_Prnt req_prnt = new Tms_Banum_Req_Prnt();
		Tms_Banum_Req_Child req_child = new Tms_Banum_Req_Child();
		TB_TMS_BANUM_DIRCTRY ba_num = new TB_TMS_BANUM_DIRCTRY();
		String username = session.getAttribute("username").toString();
		String ba_no = request.getParameter("ba_no");
		String chesis_no = request.getParameter("chesis_no");
		String engine_no = request.getParameter("engine_no");
		String mct_no = request.getParameter("mct_no");
		String std_nomclature = request.getParameter("std_nomclature");
		String type_of_veh = request.getParameter("type_of_veh");
		String old_ba_no = request.getParameter("old_ba_no");
		
		if (ba_no == "" || ba_no.equals("") || ba_no == null || ba_no.equals("null") || ba_no.equals("null")) {
			model.put("msg", "Please Enter the BA No.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else if (type_of_veh == null || type_of_veh == "" || type_of_veh.equals("") || type_of_veh.equals("null")
				|| type_of_veh.equals("undefine")) {
			model.put("msg", "Please Select Type Of Vehicle.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else if (chesis_no == "" || chesis_no.equals("") || chesis_no == null || chesis_no.equals("null")
				|| chesis_no.equals("null")) {
			model.put("msg", "Please Enter the Chesis No.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else if (engine_no == "" || engine_no.equals("") || engine_no == null || engine_no.equals("null")
				|| engine_no.equals("null")) {
			model.put("msg", "Please Enter the Engine No.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else if (mct_no == "" || mct_no.equals("") || mct_no == null || mct_no.equals("null")
				|| mct_no.equals("null")) {
			model.put("msg", "Please Enter the MCT No.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else if (std_nomclature == "" || std_nomclature.equals("") || std_nomclature == null
				|| std_nomclature.equals("null") || std_nomclature.equals("null")) {
			model.put("msg", "Please Enter the Std Nomclature.");
			return new ModelAndView("redirect:addition_of_ba_no");
		} else {
			Session sessionpid = HibernateUtil.getSessionFactory().openSession();
			Session sessionbano = HibernateUtil.getSessionFactory().openSession();
			Session sessionChild = HibernateUtil.getSessionFactory().openSession();
			String type_of_request = request.getParameter("type_of_request");
			BigInteger mct = new BigInteger(request.getParameter("mct_no"));
			String authority = request.getParameter("auth_no");

			if (sDao.ifExistbanoChild(ba_no) == true) {
				if (sDao.ifExistbano(ba_no) == false) {
					Transaction txpid = sessionpid.beginTransaction();
					Query qpid = sessionpid.createQuery(" from Tms_Banum_Req_Child where ba_no=:ba_no");
					qpid.setParameter("ba_no", ba_no);
					@SuppressWarnings("unchecked")
					List<Tms_Banum_Req_Child> list1 = (List<Tms_Banum_Req_Child>) qpid.list();
					txpid.commit();
					
					int n_p_id = list1.get(0).getParent_req_id();
					ba_num.setBa_no(ba_no);
					ba_num.setOld_ba_no(old_ba_no);
					ba_num.setVeh_cat(type_of_veh);
					ba_num.setMct(mct);
					ba_num.setEngine_no(engine_no);
					ba_num.setChasis_no(chesis_no);
					ba_num.setStatus("Active");
					ba_num.setApproved_by(username);
					ba_num.setApprove_date(new Date());
					ba_num.setCreation_by(username);
					ba_num.setCreation_date(new Date());
					ba_num.setVersion_no(0);
					ba_num.setParent_req_id(n_p_id);
					ba_num.setAuthority(authority);
					sessionbano.beginTransaction();
					sessionbano.save(ba_num);
					sessionbano.getTransaction().commit();
				}
			} else {
				req_prnt.setRequesting_agency("OTHERS");
				req_prnt.setDte_of_reqst(new Date());
				req_prnt.setStatus("6");
				req_prnt.setVersion_no(0);
				req_prnt.setCreation_by(username);
				req_prnt.setCreation_date(new Date());
				req_prnt.setBa_no_type(0);
				req_prnt.setType_of_request(type_of_request);
				Session sessionPrnt = HibernateUtil.getSessionFactory().openSession();
				sessionPrnt.beginTransaction();
				int prnt_id = (Integer) sessionPrnt.save(req_prnt);
				sessionPrnt.getTransaction().commit();
				sessionPrnt.close();
				
				
				req_child.setParent_req_id(prnt_id);
				req_child.setMct_number(new BigInteger(request.getParameter("mct_no")));
				req_child.setEngine_no(engine_no);
				req_child.setChasis_no(chesis_no);
				req_child.setVehicle_clas_code("0");
				req_child.setYear_of_entry(new Date());
				req_child.setVeh_class("0");
				req_child.setCreation_by(username);
				req_child.setCreation_date(new Date());
				req_child.setBa_no(ba_no);
				req_child.setVeh_cat(type_of_veh);
				sessionChild.beginTransaction();
				sessionChild.save(req_child);
				sessionChild.getTransaction().commit();

				if (sDao.ifExistbano(ba_no) == false) {
					ba_num.setBa_no(ba_no);
					ba_num.setOld_ba_no(old_ba_no);
					ba_num.setVeh_cat(type_of_veh);
					ba_num.setMct(mct);
					ba_num.setEngine_no(engine_no);
					ba_num.setChasis_no(chesis_no);
					ba_num.setStatus("Active");
					ba_num.setApproved_by(username);
					ba_num.setApprove_date(new Date());
					ba_num.setCreation_by(username);
					ba_num.setCreation_date(new Date());
					ba_num.setVersion_no(0);
					ba_num.setParent_req_id(prnt_id);
					ba_num.setAuthority(authority);

					sessionbano.beginTransaction();
					sessionbano.save(ba_num);
					sessionbano.getTransaction().commit();
				}
			}
			sessionbano.close();
			sessionpid.close();
			sessionChild.close();
		}
		model.put("msg", "Addition of BA No Completed.");
		return new ModelAndView("redirect:addition_of_ba_no");
	}
	
	
	// Get KMS and Classification from Ban No
	@RequestMapping(value = "/getKms_classificationfromBaNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getKms_classificationfromBaNo(String ba_no,String veh_cat, HttpSession sessionA){
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("MISO")){
			if(veh_cat.equals("A")) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery(" from TB_TMS_CENSUS_RETURN where ba_no=:ba_no").setMaxResults(1);
				q.setParameter("ba_no", ba_no);
				@SuppressWarnings("unchecked")  
				List<String> list = (List<String>) q.list();
				tx.commit();
				return list;
			}
			if(veh_cat.equals("B")) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery(" from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no").setMaxResults(1);
				q.setParameter("ba_no", ba_no);
				@SuppressWarnings("unchecked")  
				List<String> list = (List<String>) q.list();
				tx.commit();
				return list;
			}
			if(veh_cat.equals("C")) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery(" from TB_TMS_EMAR_REPORT where em_no=:em_no").setMaxResults(1);
				q.setParameter("em_no", ba_no);
				@SuppressWarnings("unchecked")  
				List<String> list = (List<String>) q.list();
				tx.commit();
				return list;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/admin/updateKMSandClassificationFromBaNo", method = RequestMethod.POST)
	public @ResponseBody String updateKMSandClassificationFromBaNo(HttpServletRequest request, HttpSession sessionA, String vehcat,String ba_no,int new_kms,String new_classification) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("MISO")){
			Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionUpdate.beginTransaction();
			try {
				int flag = 0;
				if(vehcat.equals("A")) {
					String qry = "update TB_TMS_CENSUS_RETURN set vehcl_kms_run=:vehcl_kms_run,vehcl_classfctn=:vehcl_classfctn,track_kms=:track_kms where ba_no=:ba_no";
					flag = sessionUpdate.createQuery(qry).setInteger("vehcl_kms_run", new_kms).setInteger("track_kms", new_kms).setString("vehcl_classfctn", new_classification).setString("ba_no", ba_no).executeUpdate();
					tx.commit();
				}
				if(vehcat.equals("B")) {
					String qry = "update TB_TMS_MVCR_PARTA_DTL set kms_run=:kms_run,classification=:new_classification where ba_no=:ba_no";
					flag = sessionUpdate.createQuery(qry).setInteger("kms_run", new_kms).setString("new_classification", new_classification).setString("ba_no", ba_no).executeUpdate();
					tx.commit();
				}
				if(vehcat.equals("C")) {
					String qry = "update TB_TMS_EMAR_REPORT set total_km_run=:total_km_run,current_km_run=:current_km_run where em_no=:em_no";
					flag = sessionUpdate.createQuery(qry).setInteger("total_km_run", new_kms).setInteger("current_km_run", new_kms).setString("em_no", ba_no).executeUpdate();
					tx.commit();
				}
				if(flag > 0){
					return "EM/BA No Updated";
				}else {
					return "EM/BA NO Not Available";
				}
			}catch (Exception e) {
				tx.rollback();
			
				return "Data not Updated";
			}finally {
				sessionUpdate.close();
			}
		}else {
			return "You are not Authorised";
		}
	}
	
	
	
	/*NITIN V4 15/11/2022*/

@RequestMapping(value = "/admin/deletion_of_ba_no", method = RequestMethod.GET)
		public ModelAndView deletion_of_ba_no(ModelMap Mmap, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) {
			 if(request.getHeader("Referer") == null ) {
				msg = "";
			 }
			
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("deletion_of_ba_no", roleid);
				String roleType = session.getAttribute("roleType").toString();
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			
			Mmap.put("msg", msg);
			return new ModelAndView("deletion_of_ba_noTiles");
		}



		//Change by Mitesh (19-02-2025)
		@RequestMapping(value = "/deletebano", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteFitnessStatus(String ba_no, String em_no, HttpServletRequest request, HttpSession sessionA,
				Authentication authentication) {

			List<String> liststr = new ArrayList<String>();
			String roleType = sessionA.getAttribute("roleType").toString();
			
			int app = 0;
			
			try {//B-VEH
				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx1 = session1.beginTransaction();
				String hqlUpdate1 = "delete from TB_TMS_RELIEF_PROG_HISTORY where ba_no=:ba_no";
				app = session1.createQuery(hqlUpdate1).setString("ba_no", ba_no).executeUpdate();
				tx1.commit();
				session1.close();
				
				Session session2 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				String hqlUpdate2 = "delete from Tms_Banum_Req_Child where ba_no=:ba_no";
				app = session2.createQuery(hqlUpdate2).setString("ba_no", ba_no).executeUpdate();
				tx2.commit();
				session2.close();
				
				
				Session session3 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				String hqlUpdate3 = "delete from TB_TMS_DRR_DIR_DTL where ba_no=:ba_no";
				app = session3.createQuery(hqlUpdate3).setString("ba_no", ba_no).executeUpdate();
				tx3.commit();
				session3.close();
				
				Session session4 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				String hqlUpdate4 = "delete from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no";
				app = session4.createQuery(hqlUpdate4).setString("ba_no", ba_no).executeUpdate();
				tx4.commit();
				session4.close();
				
				Session session5 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx5 = session5.beginTransaction();
				String hqlUpdate5 = "delete from TB_TMS_MVCR_PARTA_DTL_HISTORY where ba_no=:ba_no";
				app = session5.createQuery(hqlUpdate5).setString("ba_no", ba_no).executeUpdate();
				tx5.commit();
				session5.close();
				
				//A-VEH
				Session session6 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx6 = session6.beginTransaction();
				String hqlUpdate6 = "delete from TB_TMS_RELIEF_PROG_HISTORY_A where ba_no=:ba_no";
				app = session6.createQuery(hqlUpdate6).setString("ba_no", ba_no).executeUpdate();
				tx6.commit();
				session6.close();
				
				Session session7 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx7 = session7.beginTransaction();
				String hqlUpdate7 = "delete from TB_TMS_CENSUS_DRR_DIR_DTL where ba_no=:ba_no";
				app = session7.createQuery(hqlUpdate7).setString("ba_no", ba_no).executeUpdate();
				tx7.commit();
				session7.close();
				

				Session session8 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx8 = session8.beginTransaction();
				String hqlUpdate8 = "delete from TB_TMS_CENSUS_RETURN where ba_no=:ba_no";
				app = session8.createQuery(hqlUpdate8).setString("ba_no", ba_no).executeUpdate();
				tx8.commit();
				session8.close();
				

				Session session9 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx9 = session9.beginTransaction();
				String hqlUpdate9 = "delete from TB_TMS_FMCR_HISTORY_TABLE where ba_no=:ba_no";
				app = session9.createQuery(hqlUpdate9).setString("ba_no", ba_no).executeUpdate();
				tx9.commit();
				session9.close();
				
				
				//c-VEH
				Session session11 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx11 = session11.beginTransaction();
				String hqlUpdate11 = "delete from tb_tms_emar_drr_dir_dtl where em_no=:em_no";
				app = session11.createSQLQuery(hqlUpdate11).setString("em_no", ba_no).executeUpdate();
				tx11.commit();
				session11.close();
			
				Session session12 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx12 = session12.beginTransaction();
				String hqlUpdate12 = "delete from tb_tms_emar_report where em_no=:em_no";
				app = session12.createSQLQuery(hqlUpdate12).setString("em_no", ba_no).executeUpdate();
				tx12.commit();
				session12.close();
				

				Session session13 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx13 = session13.beginTransaction();
				String hqlUpdate13 = "delete from tb_tms_emar_history_table where em_no=:em_no";
				app = session13.createSQLQuery(hqlUpdate13).setString("em_no", ba_no).executeUpdate();
				tx13.commit();
				session13.close();
				
				Session session10 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx10 = session10.beginTransaction();
				String hqlUpdate10= "delete from TB_TMS_RELIEF_PROG_HISTORY_C where ba_no=:ba_no";
				app = session10.createQuery(hqlUpdate10).setString("ba_no", ba_no).executeUpdate();
				tx10.commit();
				session10.close();
				
				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no";
				app = session.createQuery(hqlUpdate).setString("ba_no", ba_no).executeUpdate();
				tx.commit();
				session.close();
				

				if (app > 0) {
					liststr.add("Deleted Successfully.");
				} else {
					liststr.add("Data not Available");
				}
				return liststr;

			} catch (Exception e) {

				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				return liststr;
			}

		}
		@RequestMapping(value = "/getvehcatfromBANo")
		public @ResponseBody String getvehcatfromBANo(String ba_no,HttpSession session) {		
				return sDao.getvehcatfromBANo(ba_no, session);
			
		}
}