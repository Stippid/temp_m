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

import com.controller.cue.ProvisionPolicyController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.AddMctNoDAO;
import com.dao.tms.AddMctNoDAOImp;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.TB_TMS_MAKE_MASTER;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MCT_SLOT_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Common_MctNoController {
	
	AddMctNoDAO addmctDAO = new AddMctNoDAOImp();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();

	Common_Mct_MainController mct_cm = new Common_Mct_MainController();

ProvisionPolicyController discard= new ProvisionPolicyController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/add_mct_no")
	public ModelAndView Add_mct_no(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_mct_no", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("add_mct_noTiles");
	}
	
	@RequestMapping(value = "/getMctNomenList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNomenList(String mctMain,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select mct_nomen from TB_TMS_MCT_MAIN_MASTER where upper(mct_nomen) LIKE :mctMain order by mct_nomen").setMaxResults(10);
		q.setParameter("mctMain",'%'+mctMain.toUpperCase()+'%');
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
	    if(list.size() != 0) {
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}

	@RequestMapping(value = "/getMctNomenMakeList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNomenMakeList(String mct_slot_id,String desc_make,HttpSession sessionUserId){
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select description from TB_TMS_MAKE_MASTER where mct_slot_id=:mct_slot_id and upper(description) like :desc_make").setMaxResults(10);
		q.setParameter("mct_slot_id", mct_slot_id);
		q.setParameter("desc_make", '%'+desc_make.toUpperCase()+'%');
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;
	}
	
	@RequestMapping(value = "/getMctNomenMakeToNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNomenMakeToNoList(String mct_main_id,String description,HttpSession sessionUserId){
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();   
		Query q = session.createQuery("select make_no from TB_TMS_MAKE_MASTER where description=:description and  mct_slot_id=:mct_main_id");
		q.setParameter("description", description);
		q.setParameter("mct_main_id", mct_main_id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"9bSjYK==");
	    return FinalList;
	}
	
	@RequestMapping(value = "/getMctNomenModelList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNomenModelList(String mct_main_id, String make_id,String desc_model,HttpSession sessionUserId){
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select description from TB_TMS_MODEL_MASTER where mct_main_id=:mct_main_id and make_id=:make_id and upper(description) like :desc_model").setMaxResults(10);
		q.setParameter("mct_main_id", mct_main_id).setParameter("make_id", make_id);
		q.setParameter("desc_model", '%'+desc_model.toUpperCase()+'%');
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;
	}
	
	@RequestMapping(value = "/getMctNomenModelToNoList")
	public @ResponseBody List<String> getMctNomenModelToNoList(String mct_main_id,String make_no,String description,HttpSession sessionUserId){
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select model_id from TB_TMS_MODEL_MASTER where description=:description and make_id=:make_id and mct_main_id=:mct_main_id ");
		q.setParameter("description", description);
		q.setParameter("mct_main_id", mct_main_id);
		q.setParameter("make_id", make_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;
	}
	
	public @ResponseBody List<TB_TMS_MCT_SLOT_MASTER> getPrfGroupName(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_MCT_SLOT_MASTER");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_SLOT_MASTER> list = (List<TB_TMS_MCT_SLOT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	 
	 @RequestMapping(value = "/add_mct_noAction", method = RequestMethod.POST)
	 public ModelAndView Add_mct_noAction(@ModelAttribute("add_mct_noCMD") TB_TMS_MCT_MASTER rs, HttpServletRequest request, ModelMap model, HttpSession session) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("add_mct_no", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 String username = session.getAttribute("username").toString();
		 String mct_main_id =request.getParameter("mct_main_id").toString();
		 String  desc_make = request.getParameter("desc_make").toString();
		 String  desc_model = request.getParameter("desc_model").toString();
		 String  make_no = request.getParameter("make_no").toString();
		 String  model_id = request.getParameter("model_id").toString();
		if(mct_main_id.equals("") || mct_main_id.equals("null")|| mct_main_id.equals(null))
		 {
			 model.put("msg","Please Enter the MCT No.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		else if(validation.mct_main_idLength(mct_main_id) == false){
	 		model.put("msg",validation.mct_main_idMSG);
			return new ModelAndView("redirect:add_mct_no");
		}
		 else if(desc_make.equals("") || desc_make.equals("null")|| desc_make.equals(null))
		 {
			 model.put("msg","Please Enter the MAKE Nomeclature.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(desc_model.equals("") || desc_model.equals("null")|| desc_model.equals(null))
		 {
			 model.put("msg","Please Enter the MODEL Nomeclature.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(make_no.equals("") || make_no.equals("null")|| make_no.equals(null))
		 {
			 model.put("msg","Please Enter the MAKE No.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(validation.make_noLength(make_no) == false)
		 {
		 		model.put("msg",validation.make_noMSG);
				return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(model_id.equals("") || model_id.equals("null")|| model_id.equals(null))
		 {
			 model.put("msg","Please Enter the MODEL No.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(validation.make_noLength(model_id) == false)
		 {
		 		model.put("msg",validation.model_idMSG);
				return new ModelAndView("redirect:add_mct_no");
		}
		 else if(request.getParameter("prf_group").equals("") || request.getParameter("prf_group").equals("null") || request.getParameter("prf_group").equals(null)) {
			 model.put("msg","Please Select the PRF.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(validation.prf_codeLength(request.getParameter("prf_group")) == false){
		 		model.put("msg",validation.prf_codeMSG);
				return new ModelAndView("redirect:add_mct_no");
		}
		else if(request.getParameter("type_of_vehicle") == null)
		 {
			 model.put("msg","Please Select Type Of Vehicle.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		else if(validation.type_of_vehLength(request.getParameter("type_of_vehicle")) == false){
	 		model.put("msg",validation.type_of_vehMSG);
			return new ModelAndView("redirect:add_mct_no");
		}
		 else if(request.getParameter("purpose_of_vehicle") == null)
		 {
			 model.put("msg","Please Select Purpose of Vehicle.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(rs.getVeh_class_code().equals("")) {
			 model.put("msg","Please Select Vehicle Class Code.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(validation.vehicle_class_codeLength(rs.getVeh_class_code()) == false){
		 		model.put("msg",validation.vehicle_class_codeMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(rs.getAuth_letter_no().equals("")) {
			 model.put("msg","Please Select Auth Letter No.");
			 return new ModelAndView("redirect:add_mct_no");
		 }
		 else if(validation.auth_letter_noTMSLength(rs.getAuth_letter_no()) == false){
		 		model.put("msg",validation.auth_letter_noTMSMSG);
				return new ModelAndView("redirect:add_mct_no");
			}	
		 else if(validation.axle_wtsLength(rs.getAxle_wts()) == false){
		 		model.put("msg",validation.axle_wtsMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.axle_wtsLength(String.valueOf(rs.getTonnage()) ) == false){
		 		model.put("msg",validation.tonnageMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.axle_wtsLength(String.valueOf(rs.getTowing_capacity()) ) == false){
		 		model.put("msg",validation.towing_capacityMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.axle_wtsLength(String.valueOf(rs.getLift_capacity()) ) == false){
		 		model.put("msg",validation.lift_capacityMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.axle_wtsLength(request.getParameter("yr_service_nff") ) == false){
		 		model.put("msg",validation.yr_service_nffMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.axle_wtsLength(request.getParameter("yr_service_ff") ) == false){
		 		model.put("msg",validation.yr_service_ffMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.km_ffLength(request.getParameter("km_ff") ) == false){
		 		model.put("msg",validation.km_ffMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.km_ffLength(request.getParameter("km_nff") ) == false){
		 		model.put("msg",validation.km_ffMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else if(validation.checkUnit_nameLength(request.getParameter("sponsoring_dte") ) == false){
		 		model.put("msg",validation.sponsoring_dteMSG);
				return new ModelAndView("redirect:add_mct_no");
			}
		 else
		 {
			 Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			 try
			 {
				 String std_nomclature = "";
				 if(rs.getType_of_vehicle().equals("A")) {
					 std_nomclature = request.getParameter("mct_nomen"); 
				 }else {
					 std_nomclature = request.getParameter("mct_nomen") + " " + request.getParameter("desc_make") + " "+ request.getParameter("desc_model");
					 
				 }

				 if(std_nomclature.equals("")) {
					 model.put("msg","Please Select Proper Std Nomanclature.");
					 return new ModelAndView("redirect:add_mct_no");
				 }
				 if(validation.std_nomclatureTMSLength(std_nomclature) == false){
				 		model.put("msg",validation.std_nomclatureTMSMSG);
						return new ModelAndView("redirect:add_mct_no");
					}
				 Date date = new Date();
				 BigInteger mct = new BigInteger(request.getParameter("mct_main_id") + request.getParameter("make_no") + request.getParameter("model_id"));
				 if(mct.equals("")) {
					 model.put("msg","Please Select Proper MCT No.");
					 return new ModelAndView("redirect:add_mct_no");	 
				 }
				 
				 int yr_service_ff =  Integer.parseInt(request.getParameter("yr_service_ff"));
				 int yr_service_nff =  Integer.parseInt(request.getParameter("yr_service_nff"));
				 int km_ff =  Integer.parseInt(request.getParameter("km_ff"));
				 int km_nff =  Integer.parseInt(request.getParameter("km_nff"));
				 rs.setCreation_by(username);
				 rs.setCreation_date(date);
				 rs.setStatus("ACTIVE");
				 rs.setMct(mct);
				 rs.setStd_nomclature(std_nomclature);
				 rs.setCategory_of_vehicle("1");
				 rs.setYr_of_svc_for_discard(yr_service_ff);
				 rs.setKms_run_for_discard(km_ff);
				 rs.setYr_of_service_nff(yr_service_nff);
				 rs.setKms_run_for_discard_nff(km_nff);
				 
				
				 sessionHql.beginTransaction();
				 
				 if(addmctDAO.ifExistMctNo(mct) != false) {
					 model.put("msg", "MCT No Already Exist.");
					 return new ModelAndView("redirect:add_mct_no");
				 }else {
					 sessionHql.save(rs);
					 sessionHql.getTransaction().commit();
					
			         model.put("msg", "MCT No Created Successfully.");		 
					 return new ModelAndView("redirect:add_mct_no");	 
				 }  
			 }
			 catch(Exception e)
			 {
				 sessionHql.getTransaction().rollback();
				return null;

			 }
			 finally
			 {
				 sessionHql.close();
			 }
		 }

	 }
	 
	 	String type_of_vehicle1 = "0";
		String mct1= "";
		String new_nomencatre1 = "";
		String prf_group1= "";
		String mct_main_id1= "";
		String mct_main_nomen1= "";
	 
	 	@RequestMapping(value="/admin/search_MctNopofestional" ,method = RequestMethod.POST)
		public ModelAndView search_MctNopofestional(ModelMap model,HttpServletRequest request,
				@RequestParam(value = "type_of_vehicle1", required = false) String type_of_vehicle,
				@RequestParam(value = "mct1", required = false) String mct,
				@RequestParam(value = "new_nomencatre1", required = false) String new_nomencatre,
				@RequestParam(value = "prf_group1", required = false) String prf_group,
				@RequestParam(value = "mct_main_id1", required = false) String mct_main_id,
				@RequestParam(value = "sermct_main_nomen1", required = false) String mct_main_nomen,ModelMap Mmap){
			
	 		if(!mct.equals("0") && !mct.equals(""))
			{
				mct1= mct;
				if(validation.mctLength(mct) == false)
			 	 {
					 		model.put("msg",validation.mctMSG);
							return new ModelAndView("redirect:search_Mct_Report");
				 }
			}
	 		else 
	 		{
				mct1= "";
			}
		 	 
	 		 
			if(!type_of_vehicle.equals("0") && !type_of_vehicle.equals(""))
			{
				type_of_vehicle1 = type_of_vehicle;
			}else {
				type_of_vehicle1 = "0";
			}
			
			if(!new_nomencatre.equals("0") && !new_nomencatre.equals(""))
			{
				new_nomencatre1 = new_nomencatre;
				if(validation.std_nomclatureTMSLength(new_nomencatre) == false)
			 	 {
					 		model.put("msg",validation.std_nomclatureTMSMSG);
							return new ModelAndView("redirect:search_Mct_Report");
				 }
			}else {
				new_nomencatre1 ="";
			}
			
			if(!prf_group.equals("0") && !prf_group.equals(""))
			{
				prf_group1 = prf_group;
				if(validation.prf_codeLength(prf_group) == false)
			 	 {
					 		model.put("msg",validation.prf_codeMSG);
							return new ModelAndView("redirect:search_Mct_Report");
				 }
			}else {
				prf_group1 = "";
			}
			
			if(!mct_main_id.equals("0") && !mct_main_id.equals(""))
			{
				mct_main_id1 = mct_main_id;
				if(validation.mct_main_idLength(mct_main_id) == false)
			 	 {
					 		model.put("msg",validation.mct_main_idMSG);
							return new ModelAndView("redirect:search_Mct_Report");
				 }
			}else {
				mct_main_id1 = "";
			}
			
			if(!mct_main_nomen.equals("0") && !mct_main_nomen.equals(""))
			{
				mct_main_nomen1 = mct_main_nomen;
				if(validation.mct_nomenLength(mct_main_nomen) == false)
			 	 {
					 		model.put("msg",validation.mct_nomenMSG);
							return new ModelAndView("redirect:search_Mct_Report");
				 }
			}else {
				mct_main_nomen1 = "";
			}
			return new ModelAndView("redirect:search_Mct_Report");
		}
		
		@RequestMapping(value = "/search_Mct_Report", method = RequestMethod.GET)
		public ModelAndView SearchRaising_disbandment(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_Mct_Report", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("type_of_vehicle1",type_of_vehicle1);
			Mmap.put("mct1",mct1);
			Mmap.put("new_nomencatre1",new_nomencatre1);
			Mmap.put("prf_group1",prf_group1);
			Mmap.put("mct_main_id1",mct_main_id1);
			Mmap.put("mct_main_nomen1",mct_main_nomen1);
			Mmap.put("msg", msg);
			return new ModelAndView("search_Mct_ReportTiles");
		}

		@RequestMapping(value = "/admin/getMctNoReport")
		public @ResponseBody DatatablesResponse<TB_TMS_MCT_MASTER> getMctNoReport(@DatatablesParams DatatablesCriterias criterias,HttpSession session, HttpServletRequest request) {
		  	if(type_of_vehicle1.equals("A") || type_of_vehicle1.equals("B") || type_of_vehicle1.equals("C")) {
		  			DataSet<TB_TMS_MCT_MASTER> dataSet = addmctDAO.findMctNoReportWithDatatablesCriterias(criterias,mct1,type_of_vehicle1,prf_group1,mct_main_id1);
		  			return DatatablesResponse.build(dataSet, criterias);
		  	}else {
		  		return null;
		  	}
		}
		
		
		public @ResponseBody List<String> getMainNomenByMainList(String main) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_nomen from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:main ");
			q.setParameter("main",main);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public @ResponseBody List<String> getMakeNomanByMakeList(String main,String make) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select description from TB_TMS_MAKE_MASTER where mct_slot_id=:main and make_no=:make ");
			q.setParameter("main",main);
			q.setParameter("make",make);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public @ResponseBody List<String> getModelNomanByModelList(String main,String make,String modelId) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select description from TB_TMS_MODEL_MASTER where mct_main_id=:main and make_id=:make and model_id =:model ");
			q.setParameter("main",main);
			q.setParameter("make",make);
			q.setParameter("model",modelId);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	    
		  @RequestMapping(value = "/admin/Update_mct_no")
			public ModelAndView Update_mct_no(@ModelAttribute("locationId") int locationId,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication, HttpServletRequest request, HttpSession session){
		    	
		    	String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("search_Mct_Report", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			TB_TMS_MCT_MASTER Update_mct_noCMD = addmctDAO.getTB_TMS_MCT_MASTERByid(locationId);
			
			model.put("getPrfGroupName",getPrfGroupName());
			model.put("Update_mct_noCMD", Update_mct_noCMD);	
			String mct = String.valueOf(Update_mct_noCMD.getMct());
			String main = String.valueOf(mct.charAt(0)) + String.valueOf(mct.charAt(1)) + String.valueOf(mct.charAt(2)) + String.valueOf(mct.charAt(3));
			String make = String.valueOf(mct.charAt(4)) + String.valueOf(mct.charAt(5)) + String.valueOf(mct.charAt(6)) ;
			String modelId = String.valueOf(mct.charAt(7)) + String.valueOf(mct.charAt(8)) + String.valueOf(mct.charAt(9));
			if(getMainNomenByMainList(main).size()!=0) {
				model.put("mct", getMainNomenByMainList(main).get(0));
				model.put("mct_id",main);
			}
			if(getMakeNomanByMakeList(main,make).size() != 0) {
				model.put("make",getMakeNomanByMakeList(main,make).get(0));
				model.put("make_id",make);
			}
			if(getModelNomanByModelList(main,make,modelId).size() != 0){
				model.put("model",getModelNomanByModelList(main,make,modelId).get(0));
				model.put("model_id",modelId);
			}
			model.put("getForceTypeList", mct_cm.getForceTypeList());
			model.put("msg", msg);
			return new ModelAndView("Update_mct_noTiles");
		}
		
		  @RequestMapping(value = "/admin/updateMctNoAction")
		  @ResponseBody
		  public ModelAndView TB_TMS_MCT_MASTER(@ModelAttribute("Update_mct_noCMD") TB_TMS_MCT_MASTER mctmas,HttpServletRequest request,HttpSession session,ModelMap model,@RequestParam(value = "msg", required = false) String msg) {
		  String  roleid = session.getAttribute("roleid").toString();
		  Boolean val = roledao.ScreenRedirect("search_Mct_Report", roleid);	
		  if(val == false) {
					return new ModelAndView("AccessTiles");
		   }
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String username = session.getAttribute("username").toString();
			Date date = new Date();
			String  mct_main_id = request.getParameter("mct_main_id");
			String  model_id = request.getParameter("model_id");
			String  make_id = request.getParameter("make_no");
			String  mct_nomen = request.getParameter("mct_nomen");
			String  desc_make = request.getParameter("desc_make");
			String  desc_model = request.getParameter("desc_model");
			
			model.put("locationId", mctmas.getId());
			if(desc_make.equals("")) {
				model.put("msg", "Please Select Make");
				return new ModelAndView("redirect:Update_mct_no");
			}
			
			 if(validation.mct_main_idLength(mct_main_id) == false){
		 		model.put("msg",validation.mct_main_idMSG);
				return new ModelAndView("redirect:Update_mct_no");
			}
			 else if(validation.make_noLength(make_id) == false)
			 {
			 		model.put("msg",validation.make_noMSG);
					return new ModelAndView("redirect:Update_mct_no");
			 }
			 else if(validation.make_noLength(model_id) == false)
			 {
			 		model.put("msg",validation.model_idMSG);
					return new ModelAndView("redirect:Update_mct_no");
			}
			 else if(validation.vehicle_class_codeLength(mctmas.getVeh_class_code()) == false){
			 		model.put("msg",validation.vehicle_class_codeMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.auth_letter_noTMSLength(mctmas.getAuth_letter_no()) == false){
			 		model.put("msg",validation.auth_letter_noTMSMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.axle_wtsLength(mctmas.getAxle_wts()) == false){
			 		model.put("msg",validation.axle_wtsMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.axle_wtsLength(String.valueOf(mctmas.getTonnage()) ) == false){
			 		model.put("msg",validation.tonnageMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.axle_wtsLength(String.valueOf(mctmas.getTowing_capacity()) ) == false){
			 		model.put("msg",validation.towing_capacityMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.axle_wtsLength(String.valueOf(mctmas.getLift_capacity()) ) == false){
			 		model.put("msg",validation.lift_capacityMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			 else if(validation.checkUnit_nameLength(request.getParameter("sponsoring_dte") ) == false){
			 		model.put("msg",validation.sponsoring_dteMSG);
					return new ModelAndView("redirect:Update_mct_no");
				}
			
			
			model.put("msg", addmctDAO.UpdateMctNo(mctmas,mct_main_id,mct_nomen,make_id,desc_make,model_id,desc_model,username,date));
			return new ModelAndView("redirect:search_Mct_Report");
		}
		
		@RequestMapping(value = "/admin/Delete_mct_no")
		public ModelAndView Delete_mct_no(@ModelAttribute("DeleteId") int DeleteId,ModelMap model,HttpSession sessionA,Authentication authentication){
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			model.put("msg", addmctDAO.setDeleteMctNo(DeleteId));	
			return new ModelAndView("redirect:search_Mct_Report");
		}

		@RequestMapping(value = "/getserPrfgroupTmsList", method = RequestMethod.POST)
		public @ResponseBody List<String> getserPrfgroupTmsList(String prf_group,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select prf_code from TB_TMS_MCT_SLOT_MASTER where  prf_code like :prf_group ");
			q.setParameter("prf_group", "%"+ prf_group +"%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
            Cipher c = null;
             try {
                         c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
              } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
                                 | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                         e.printStackTrace();
               }
              List<String> FinalList = new ArrayList<String>();
             for(int i=0; i<list.size();i++) {
                     byte[] encCode = null;
                         try {
                                 encCode = c.doFinal(list.get(i).getBytes());
                         } catch (IllegalBlockSizeException | BadPaddingException e) {
                                 e.printStackTrace();
                         }
                     String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
                     FinalList.add(base64EncodedEncryptedCode);
                 }
             if(list.size() != 0) {
                     FinalList.add(enckey+"4bsjyg==");
             }
             return FinalList;
         }

		@RequestMapping(value = "/getserMctMainTmsList", method = RequestMethod.POST)
		public @ResponseBody List<String> getserMctMainTmsList(String mct_main_id,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_main_id from TB_TMS_MCT_MAIN_MASTER where   mct_main_id like :mct_main_id").setMaxResults(10);
			q.setParameter("mct_main_id", "%"+ mct_main_id +"%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
		    Cipher c = null;
		    try {
		                c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		     } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
		                        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		                e.printStackTrace();
		      }
		     List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		            byte[] encCode = null;
		                try {
		                        encCode = c.doFinal(list.get(i).getBytes());
		                } catch (IllegalBlockSizeException | BadPaddingException e) {
		                        e.printStackTrace();
		                }
		            String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		            FinalList.add(base64EncodedEncryptedCode);
		        }
		    if(list.size() != 0) {
		            FinalList.add(enckey+"4bsjyg==");
		    }
		    return FinalList;
		}

		@RequestMapping(value = "/getserMctMainnomainTmsList", method = RequestMethod.POST)
		public @ResponseBody List<String> getserMctMainnomainTmsList(String sermct_main_nomen,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_nomen from TB_TMS_MCT_MAIN_MASTER where UPPER(mct_nomen) like :sermct_main_nomen ").setMaxResults(10);
			q.setParameter("sermct_main_nomen", "%"+ sermct_main_nomen.toUpperCase() +"%");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
		    Cipher c = null;
		    try {
		                c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		     } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
		                        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		                e.printStackTrace();
		      }
		     List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		            byte[] encCode = null;
		                try {
		                        encCode = c.doFinal(list.get(i).getBytes());
		                } catch (IllegalBlockSizeException | BadPaddingException e) {
		                        e.printStackTrace();
		                }
		            String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		            FinalList.add(base64EncodedEncryptedCode);
		        }
		    if(list.size() != 0) {
		            FinalList.add(enckey+"4bsjyg==");
		    }
		    return FinalList;
		}
		
		@RequestMapping(value = "/getserMctMainTmsListcliknomen", method = RequestMethod.POST)
		public @ResponseBody List<String> getserMctMainTmsListcliknomen(String mct_main_id,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_nomen from TB_TMS_MCT_MAIN_MASTER where mct_main_id = :mct_main_id ");
			q.setParameter("mct_main_id", mct_main_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey+"4bsjyg==");
		    return FinalList;
		}
		@RequestMapping(value = "/getserMctMainTmsListclikid", method = RequestMethod.POST)
		public @ResponseBody List<String> getserMctMainTmsListclikid(String mct_nomen,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_main_id from TB_TMS_MCT_MAIN_MASTER where mct_nomen = :mct_nomen ");
			q.setParameter("mct_nomen", mct_nomen);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey+"4bsjyg==");
		    return FinalList;
		}
	
		@RequestMapping(value = "/getaddmctnoMctNomenToNoList", method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getaddmctnoMctNomenToNoList(String mct_nomen,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_main_id,prf_code,vehicle_class_code,type_of_veh from TB_TMS_MCT_MAIN_MASTER where mct_nomen=:mct_nomen");
			q.setParameter("mct_nomen", mct_nomen);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String mct_main_id = String.valueOf(listObject[0]);
		   		String prf_code = String.valueOf(listObject[1]);
		   		String vehicle_class_code = String.valueOf(listObject[2]);
		   		String type_of_veh = String.valueOf(listObject[3]);
			    String base64EncodedEncryptedmct_main_id = new String(Base64.encodeBase64(c.doFinal(mct_main_id.getBytes())));
			    String base64EncodedEncryptedprf_code = new String(Base64.encodeBase64(c.doFinal(prf_code.getBytes())));
			    String base64EncodedEncryptedvehicle_class_code = new String(Base64.encodeBase64(c.doFinal(vehicle_class_code.getBytes())));
			    String base64EncodedEncryptedtype_of_veh = new String(Base64.encodeBase64(c.doFinal(type_of_veh.getBytes())));
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedmct_main_id);
			    EncList.add(base64EncodedEncryptedprf_code);
			    EncList.add(base64EncodedEncryptedvehicle_class_code);
			    EncList.add(base64EncodedEncryptedtype_of_veh);
			    FinalList.add(EncList);
		   	}
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"YbFjyB==");
		    	EncKeyList.add(enckey+"HNTrgS==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@RequestMapping(value = "/getmakemastertransportMctNomenToNoList", method = RequestMethod.POST)
		public @ResponseBody List<String> getmakemastertransportMctNomenToNoList(String mct_nomen,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select mct_main_id from TB_TMS_MCT_MAIN_MASTER where mct_nomen=:mct_nomen");
			q.setParameter("mct_nomen", mct_nomen);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			List<String> FinalList = new ArrayList<String>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey+"4bsjyg==");
		    return FinalList;
		}
		@RequestMapping(value = "/getmodeltmsmakeid", method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getmodeltmsmakeid(int slotid,HttpSession sessionUserId) throws IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction(); 
			Query q = session.createQuery("select make_no,description from TB_TMS_MAKE_MASTER where mct_slot_id =:slotid" );
			q.setParameter("slotid", String.valueOf(slotid));
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String formation = (String)listObject[0];
		   		String unitName = (String)listObject[1];
		   		byte[] encFromation = c.doFinal(formation.getBytes());
			    String base64EncodedEncryptedFormation = new String(Base64.encodeBase64(encFromation));
			    byte[] encUnitName = c.doFinal(unitName.getBytes());
			    String base64EncodedEncryptedUnitName = new String(Base64.encodeBase64(encUnitName));
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedFormation);
			    EncList.add(base64EncodedEncryptedUnitName);
			    FinalList.add(EncList);
		   	}
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"4bsjyg==");
		    	EncKeyList.add(enckey+"jNYrGf==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}

		@RequestMapping(value = "/getMCTsearchmctnoData", method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getMCTsearchmctnoData(String mct_num,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			BigInteger sum = new BigInteger(mct_num);
			Query q = session.createQuery("select std_nomclature,prf_group from TB_TMS_MCT_MASTER where status = 'ACTIVE' and mct=:mct_num");
			q.setParameter("mct_num", sum);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String sus_no = (String)listObject[0];
		   		String arm_code = (String)listObject[1];
			    String base64EncodedEncryptedSusNo = new String(Base64.encodeBase64(c.doFinal(sus_no.getBytes())));
			    String base64EncodedEncryptedArmCode = new String(Base64.encodeBase64(c.doFinal(arm_code.getBytes())));
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedSusNo);
			    EncList.add(base64EncodedEncryptedArmCode);
			    FinalList.add(EncList);
		   	}
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"YbFjyB==");
		    	EncKeyList.add(enckey+"HNTrgS==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@RequestMapping(value = "/getaddsearchmctnoprfList", method = RequestMethod.POST)
		public @ResponseBody  ArrayList<List<String>> getaddsearchmctnoprfList(String prf_group,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select code_no_from,code_no_to,group_name from TB_TMS_MCT_SLOT_MASTER where prf_code=:prf_group");
			q.setParameter("prf_group", prf_group);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String code_no_from = String.valueOf(listObject[0]);
		   		String code_no_to = String.valueOf(listObject[1]);
		   		String group_name = String.valueOf(listObject[2]);
			    String base64EncodedEncryptedcode_no_from = new String(Base64.encodeBase64(c.doFinal(code_no_from.getBytes())));
			    String base64EncodedEncryptedcode_no_to = new String(Base64.encodeBase64(c.doFinal(code_no_to.getBytes())));
			    String base64EncodedEncryptedgroup_name = new String(Base64.encodeBase64(c.doFinal(group_name.getBytes())));
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedcode_no_from);
			    EncList.add(base64EncodedEncryptedcode_no_to);
			    EncList.add(base64EncodedEncryptedgroup_name);
			    FinalList.add(EncList);
		   	}
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	EncKeyList.add(enckey+"YbFjyB==");
		    	EncKeyList.add(enckey+"HNTrgS==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
}