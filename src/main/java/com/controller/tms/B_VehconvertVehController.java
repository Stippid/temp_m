package com.controller.tms;

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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
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
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.tmsConversionRequestGstoSplVehDAO;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CONVERT_REQ;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class B_VehconvertVehController {
	
	@Autowired
	tmsConversionRequestGstoSplVehDAO conVeh;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/convert_veh_1", method = RequestMethod.GET)
		public ModelAndView convert_veh_1(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("convert_veh_1", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("convert_veh_1Tile");
	}
	
	@RequestMapping(value = "/search_convert_veh_1", method = RequestMethod.GET)
	public ModelAndView search_conv_gst_to_splveh(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_convert_veh_1", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_convert_veh_1Tile");
	}
	
	//changes by Mitesh(26-11-2024)
	@RequestMapping(value = "/getBA_NumberList",method = RequestMethod.POST)
    public @ResponseBody List<String> getBA_NumberList(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
       // Query q = session.createQuery("select ba_no FROM TB_TMS_MVCR_PARTA_DTL WHERE (ba_no LIKE ('__C%') or ba_no LIKE ('__B%') or ba_no LIKE ('__D%')) and sus_no=:sus_no and status='1' order by substring(ba_no,3,1),substring(ba_no,1,2)");
        Query q = session.createQuery("select a.ba_no FROM TB_TMS_MVCR_PARTA_DTL a,TB_TMS_BANUM_DIRCTRY d WHERE a.ba_no=d.ba_no and d.status='Active' and (a.ba_no LIKE ('__C%') or a.ba_no LIKE ('__B%') or a.ba_no LIKE ('__D%') or a.ba_no LIKE ('__E%') ) and a.sus_no=:sus_no and a.status='1' order by substring(a.ba_no,3,1),substring(a.ba_no,1,2)");
        q.setParameter("sus_no", sus_no);
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) q.list();
        tx.commit();
        session.close();
        /*String enckey = hex_asciiDao.getAlphaNumericString();
        Cipher c = null;
        try {
                c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
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
        	FinalList.add(enckey+"6fsjyg==");
        }
        return FinalList;*/
        return list;
    }
			
	@RequestMapping(value = "/getEngineChasisFromBANoList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getEngineChasisFromBANoList(String ba_no,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct d.ba_no,d.chasis_no,d.engine_no,d.mct,m.std_nomclature from TB_TMS_BANUM_DIRCTRY d,TB_TMS_MCT_MASTER m where d.mct = m.mct and d.ba_no=:ba_no and d.status='Active'");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		ArrayList<List<String>> FinalList = new ArrayList<List<String>>();

		for(Object[] listObject: list){
			String chasis_no = String.valueOf(listObject[1]);
	   		String engine_no = String.valueOf(listObject[2]);
	   		String mct = String.valueOf(listObject[3]);
	   		String std_nomclature = String.valueOf(listObject[4]);
		    String base64EncodedEncryptedchasis_no = new String(Base64.encodeBase64(c.doFinal(chasis_no.getBytes())));
		    String base64EncodedEncryptedengine_no= new String(Base64.encodeBase64(c.doFinal(engine_no.getBytes())));
		    String base64EncodedEncryptedmct = new String(Base64.encodeBase64(c.doFinal(mct.getBytes())));
		    String base64EncodedEncryptedstd_nomclature = new String(Base64.encodeBase64(c.doFinal(std_nomclature.getBytes())));
	   		List<String> EncList = new ArrayList<String>();
		    EncList.add(base64EncodedEncryptedchasis_no);
		    EncList.add(base64EncodedEncryptedengine_no);
		    EncList.add(base64EncodedEncryptedmct);
		    EncList.add(base64EncodedEncryptedstd_nomclature);
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

	@RequestMapping(value = "/getstdNomenclatureOfTmsList", method = RequestMethod.POST)
	public @ResponseBody List<String> getstdNomenclatureOfTmsList(BigInteger mct) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select std_nomclature from TB_TMS_MCT_MASTER where mct = :mct and status = 'ACTIVE'");
		q.setParameter("mct", mct);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getChk_ba_no_dublication", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getChk_ba_no_dublication(String old_ba_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select count(old_ba_no) from TB_TMS_CONVERT_REQ where old_ba_no = :old_ba_no");
		q.setParameter("old_ba_no", old_ba_no);
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getImageOfVehFromBa_nochildreq", method = RequestMethod.POST)
	public @ResponseBody List<Tms_Banum_Req_Child> getImageOfVehFromBa_nochildreq(String ba_no) {
		return conVeh.getImageFromBaNoChildReq(ba_no);
	}  

	@RequestMapping(value = "/ConvertRequestGstosplVehDTLAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("ConvertRequestGstosplVehInsertDTLCMD") TB_TMS_CONVERT_REQ rs,
			HttpServletRequest request,ModelMap model,HttpSession session) throws ParseException {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("convert_veh_1", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy"); 
		Date creadtedate1  = null;
		try {
			creadtedate1 = formatter.parse(creadtedate);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}
		
		rs.setCreation_date(creadtedate1);
		rs.setCreation_by(username);
		rs.setStatus("0");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		sessionHQL.save(rs);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		model.put("msg", "Your Request To Convert GS To Spl Veh Send Successfully");
		return new ModelAndView("redirect:convert_veh_1");
	}
	
	@RequestMapping(value = "/admin/addConTmsData",method = RequestMethod.POST)
	public @ResponseBody String addConTmsData(ModelMap model,HttpServletRequest request,HttpSession sessionA,String sus_no,String engine_no,String chasis_no,String old_ba_no,BigInteger old_mct_number,String remarks,String newstdnomencltr_veh_aftrmod_as_auth_we,String conv_modCarriedOut,String justification,String financial_impact,String dte_of_reqst,String old_vcode ) throws HibernateException, ParseException {
		TB_TMS_CONVERT_REQ dtl = new TB_TMS_CONVERT_REQ();
		String username = sessionA.getAttribute("username").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		if(sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null))
		{
             return "Please Enter Sus No.";
		}
		else if(validation.sus_noLength(request.getParameter("sus_no")) == false)
		{
			return validation.sus_noMSG;
		}
		else if(dte_of_reqst.equals("") || dte_of_reqst.equals("null") || dte_of_reqst.equals(null))
		{
             return "Please Enter Date of Request.";
		}
		else if(format.parse(request.getParameter("dte_of_reqst")).compareTo(today_dt) > 0 &&  format.parse(request.getParameter("dte_of_reqst")).compareTo(today_dt) != 0)
		{
			return "Please Enter Valid Date Of Request.";
			
		}
		else if(old_ba_no.equals("0") || old_ba_no.equals("") || old_ba_no.equals("null") || old_ba_no.equals(null))
		{
            return "Please Enter Old Ba No.";
		}
		else if(engine_no.equals("") || engine_no.equals("null") || engine_no.equals(null))
		{
            return "Please Enter Engine Number.";
		}
		else if(validation.initiating_authTMSLength(request.getParameter("engine_no")) == false)
		{
		 		return validation.engine_noMSG;
		}
		else if(chasis_no.equals("") || chasis_no.equals("null") || chasis_no.equals(null))
		{
            return "Please Enter Chasis Number.";
		}
		else if(validation.initiating_authTMSLength(request.getParameter("chasis_no")) == false)
		{
		 		return validation.chasis_noMSG;
		}
		else if(old_vcode.equals("") || old_vcode.equals("null") || old_vcode.equals(null))
		{
            return "Please Enter Vehicle Class Code.";
		}
		else if(remarks.equals("") || remarks.equals("null") || remarks.equals(null))
		{
			 return "Please Enter Remarks.";
		}
		else if(validation.check255Length(request.getParameter("remarks")) == false)
		{
		 		return validation.remarksMSGTMS;
		}
		else if(validation.mctLength(request.getParameter("old_mct_number")) == false)
		{
		 		return validation.mctMSG;
		}
		else
		{
			Session sessionHql = HibernateUtil.getSessionFactory().openSession();
			sessionHql.beginTransaction();
			try
			{
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
				Date dte_of_reqst1 = null;
				try {
					dte_of_reqst1 = formatter1.parse(dte_of_reqst);
		        }  catch (java.text.ParseException e) {
						e.printStackTrace();
				}
				dtl.setDte_of_reqst(dte_of_reqst1);
				DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd"); 
				Date received_from1 = null;
				dtl.setReceived_from(null);
				dtl.setCreation_by(username);
				dtl.setCreation_date(new Date());
				dtl.setStatus("0");
				dtl.setSus_no(sus_no);
				dtl.setVehicle_class_code(old_vcode);
				dtl.setEngine_no(engine_no);
				dtl.setChasis_no(chasis_no);
				dtl.setOld_mct_number(old_mct_number);
				dtl.setOld_ba_no(old_ba_no);
				dtl.setConv_modCarriedOut(conv_modCarriedOut);
				dtl.setNewstdnomencltr_veh_aftrmod_as_auth_we(newstdnomencltr_veh_aftrmod_as_auth_we);
				dtl.setJustification(justification);
				dtl.setFinancial_impact(financial_impact);
				dtl.setRemarks(remarks);
				dtl.setVersion_no(0);
				if(conVeh.ifExistBaNo(old_ba_no) == true) {
					return "Failure dtl";
				}else {
					sessionHql.save(dtl);
					sessionHql.getTransaction().commit();
				}
				return "Success";
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


	@RequestMapping(value = "/admin/search_ReqToConvert_GsToSpl", method = RequestMethod.POST)
	public ModelAndView search_ReqToConvert_GsToSpl(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "dte_of_reqst1", required = false) String dte_of_reqst,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "received_from1", required = false) String received_from,
			@RequestParam(value = "old_ba_no1", required = false) String old_ba_no){
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_convert_veh_1", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(status != "") {
			Mmap.put("status", status);
		}
		if(sus_no != "")
		{
			if(validation.sus_noLength(sus_no) == false)
		 	 {
					Mmap.put("msg",validation.sus_noMSG);
					return new ModelAndView("redirect:search_convert_veh_1");
			 }
			Mmap.put("sus_no", sus_no);
		}
		if(dte_of_reqst != "" && received_from != "") {
			Mmap.put("dte_of_reqst", dte_of_reqst);
			Mmap.put("received_from", received_from);
		}
		if(old_ba_no != "" && old_ba_no != "") {
			Mmap.put("ba_no", old_ba_no);
		}
		else if(dte_of_reqst != "" && received_from == "")
		{
			Mmap.put("dte_of_reqst", dte_of_reqst);
		}
		else if(received_from != "" && dte_of_reqst == "")
		{
			Mmap.put("received_from", received_from);
		}
		ArrayList<List<String>> list =	conVeh.getReqToCovGsToSpcl(status,sus_no,dte_of_reqst,received_from,old_ba_no, roleType);
			Mmap.put("list", list);
			Mmap.put("msg", msg);
		return new ModelAndView("search_convert_veh_1Tile");
	}
	
	@RequestMapping(value = "/admin/updateConvReqOfVehDtl")
	public ModelAndView updateConvReqOfVehDtl(@ModelAttribute("updateid") int updateid,HttpSession sessionA,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpServletRequest request){
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_convert_veh_1", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		model.put("editconvertRequestGstosplVehDTLCMD", conVeh.getTB_TMS_CONVERT_REQid(updateid));	
		model.put("msg", msg);
		String sus_no = conVeh.getTB_TMS_CONVERT_REQid(updateid).getSus_no();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size() > 0)
		{
			model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
		}
		return new ModelAndView("convert_vehTile");
	}
	
	@RequestMapping(value = "/editconvertRequestGstosplUpdateAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editconvertRequestGstosplUpdateAction(@ModelAttribute("editconvertRequestGstosplVehDTLCMD") TB_TMS_CONVERT_REQ updateid,@RequestParam(value = "front_view_image1", required = false) MultipartFile front_view_image1,
			@RequestParam(value = "back_view_image1", required = false) MultipartFile back_view_image1,
			@RequestParam(value = "side_view_image1", required = false) MultipartFile side_view_image1,
			@RequestParam(value = "top_view_image1", required = false) MultipartFile top_view_image1,HttpServletRequest request,HttpSession session,ModelMap model,@RequestParam(value = "msg", required = false) String msg) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
	
		if(updateid.getNew_mct_number() != null & !request.getParameter("new_mct_number").equals("0"))
		{
			if(validation.mctLength(request.getParameter("new_mct_number")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.mctMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			if((updateid.getNew_mct_number() == updateid.getOld_mct_number()) || (updateid.getNew_mct_number().equals(updateid.getOld_mct_number())))
			{
				model.put("updateid", updateid.getId());
				model.put("msg", "Old and New Mct Number can't be same , Please Change New Mct No. !!");
				return new ModelAndView("redirect:search_convert_veh_1");
			}
		}
			if(updateid.getRemarks().equals("") || updateid.getRemarks().equals(null) || updateid.getRemarks() == null)
			{
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Remarks.");
				return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.check255Length(request.getParameter("remarks")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.remarksMSGTMS);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			
			else if(validation.sus_noLength(request.getParameter("sus_no")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.sus_noMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.initiating_authTMSLength(request.getParameter("engine_no")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.engine_noMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.initiating_authTMSLength(request.getParameter("chasis_no")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.chasis_noMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.ba_noLength(request.getParameter("old_ba_no")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.ba_noMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.mctLength(request.getParameter("old_mct_number")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.mctMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.km_ffLength(request.getParameter("auth_letter_no")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.auth_letter_noReqMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.mct_nomenLength(request.getParameter("new_nomencatre")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.mct_nomenMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.convrsn_doneLength(request.getParameter("convrsn_done")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.convrsn_doneMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			else if(validation.vehicle_class_codeLength(request.getParameter("vehicle_class_code")) == false)
			{
					model.put("updateid", updateid.getId());
			 		model.put("msg",validation.vehicle_class_codeMSG);
					return new ModelAndView("redirect:updateConvReqOfVehDtl");
			}
			
		Session sessioncon = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessioncon.beginTransaction();
		try
		{
			String ba_no =updateid.getOld_ba_no();
			BigInteger old_mct = updateid.getOld_mct_number();
			String new_ba_no_first_2 = ba_no.substring(0, 2);
			String VehCode =new_ba_no_first_2.concat(request.getParameter("vehicle_class_code")); 		
			String New_Veh_Code =request.getParameter("vehicle_class_code");
			updateid.setApproved_by(username);
			updateid.setApprove_date(new Date());;
			String fname1 = "";
			String fname2 = "";
			String fname3 = "";
			String fname4 = "";	
		    String extension1 = "";
		    String extension2 = "";
		    String extension3 = "";
		    String extension4 = "";
		    final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
	    	// code modify by Paresh on 05/05/2020
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
		    if (!front_view_image1.isEmpty()) {
		    	if (front_view_image1.getSize() > fileSizeLimit) {
		    		model.put("msg", "File size should be less then 2 MB");
		    		return new ModelAndView("redirect:search_convert_veh_1");
				}
		    	String front_view_image1Ext = FilenameUtils.getExtension(front_view_image1.getOriginalFilename());	
		    	if(!front_view_image1Ext.toUpperCase().equals("jpg".toUpperCase()) && !front_view_image1Ext.equals("jpeg".toUpperCase()) && !front_view_image1Ext.equals("png".toUpperCase())) {
					model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:search_convert_veh_1");
				}
		    	String tmsFilePath = session.getAttribute("tmsFilePath").toString();
		    	try {
		    		byte[] bytes = front_view_image1.getBytes();
		    		CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
					if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
			    		File dir = new File(tmsFilePath);
		                if (!dir.exists()) {
		                        dir.mkdirs();
		                }
		                String filename =front_view_image1.getOriginalFilename();
		                int i = filename.lastIndexOf('.');
		                if (i >= 0) {
		                        extension1 = filename.substring(i+1);
		                }
						fname1 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_FRONT_TMSDOC."+extension1;
		                if(validation.checkImageAnmlLength(fname1)  == false){
					 		model.put("msg",validation.front_view_imageMSG);
							return new ModelAndView("redirect:search_convert_veh_1");
						}
		                
		                File serverFile = new File(fname1);                       
		                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		                stream.write(bytes);                        
		                stream.close();
					}else {
						model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
						return new ModelAndView("redirect:search_convert_veh_1");
					}
	            }
	            catch (Exception e) {
	            }
		    } 
	    	if (!back_view_image1.isEmpty()) {
	    		if (back_view_image1.getSize() > fileSizeLimit) {
		    		model.put("msg", "File size should be less then 2 MB");
		    		return new ModelAndView("redirect:search_convert_veh_1");
				}
	    		String back_view_image1Ext = FilenameUtils.getExtension(back_view_image1.getOriginalFilename());
	    		 if(!back_view_image1Ext.toUpperCase().equals("jpg".toUpperCase()) && !back_view_image1Ext.equals("jpeg".toUpperCase()) && !back_view_image1Ext.equals("png".toUpperCase())) {
	    				model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
	    				return new ModelAndView("redirect:search_convert_veh_1");
	    			}
	            try {
	                    byte[] bytes = back_view_image1.getBytes();
	                    CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
		                    String tmsFilePath = session.getAttribute("tmsFilePath").toString();
		                    File dir = new File(tmsFilePath);
		                    if (!dir.exists()) {
		                            dir.mkdirs();
		                    }
		                    String filename =back_view_image1.getOriginalFilename();
		                    int i = filename.lastIndexOf('.');
		                    if (i >= 0) {
		                            extension2 = filename.substring(i+1);
		                    }
		                    fname2 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_BACK_TMSDOC."+extension2;
		                    if(validation.checkImageAnmlLength(fname2)  == false){
						 		model.put("msg",validation.back_view_imageMSG);
								return new ModelAndView("redirect:search_convert_veh_1");
							}
		                    
		                    File serverFile = new File(fname2);                       
		                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		                    stream.write(bytes);                        
		                    stream.close();
						}else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
		    				return new ModelAndView("redirect:search_convert_veh_1");
						}
	            }
	            catch (Exception e) {
	            }
	    	} 
	    	if (!side_view_image1.isEmpty()) {
	    		if (side_view_image1.getSize() > fileSizeLimit) {
		    		model.put("msg", "File size should be less then 2 MB");
		    		return new ModelAndView("redirect:search_convert_veh_1");
				}
	    		String side_view_image1Ext = FilenameUtils.getExtension(side_view_image1.getOriginalFilename());
	    		if(!side_view_image1Ext.toUpperCase().equals("jpg".toUpperCase()) && !side_view_image1Ext.equals("jpeg".toUpperCase()) && !side_view_image1Ext.equals("png".toUpperCase())) {	
	    			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
	    			return new ModelAndView("redirect:search_convert_veh_1");
	    		}
	            try {
	                    byte[] bytes = side_view_image1.getBytes();
	                    CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
		                    String tmsFilePath = session.getAttribute("tmsFilePath").toString();
		                    File dir = new File(tmsFilePath);
		                    if (!dir.exists()) {
		                            dir.mkdirs();
		                    }
		                    String filename =side_view_image1.getOriginalFilename();
		                    int i = filename.lastIndexOf('.');
		                    if (i >= 0) {
		                            extension3 = filename.substring(i+1);
		                    }
		                    java.util.Date date1= new java.util.Date();
		                    fname3 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_SIDE_TMSDOC."+extension3;
		                    
		                    if(validation.checkImageAnmlLength(fname3)  == false){
						 		model.put("msg",validation.side_view_imageMSG);
								return new ModelAndView("redirect:search_convert_veh_1");
							}
		                    
		                    File serverFile = new File(fname3);                       
		                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		                    stream.write(bytes);                        
		                    stream.close();
						}else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
			    			return new ModelAndView("redirect:search_convert_veh_1");
						}
	            }
	            catch (Exception e) {
	            }
	    	} 
	    	if (!top_view_image1.isEmpty()) {
	    		if (top_view_image1.getSize() > fileSizeLimit) {
		    		model.put("msg", "File size should be less then 2 MB");
		    		return new ModelAndView("redirect:search_convert_veh_1");
				}
	    		String top_view_image1Ext = FilenameUtils.getExtension(top_view_image1.getOriginalFilename());
	    		if(!top_view_image1Ext.toUpperCase().equals("jpg".toUpperCase()) && !top_view_image1Ext.equals("jpeg".toUpperCase()) && !top_view_image1Ext.equals("png".toUpperCase())) {		
	    			model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
	    			return new ModelAndView("redirect:search_convert_veh_1");
	    		}
	            try {
	                    byte[] bytes = top_view_image1.getBytes();
	                    CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
						if(fileValidation.check_JPEG_File(bytes) || fileValidation.check_PNG_File(bytes)) {
		                    String tmsFilePath = session.getAttribute("tmsFilePath").toString();
		                    File dir = new File(tmsFilePath);
		                    if (!dir.exists()) {
		                            dir.mkdirs();
		                    }
		                    String filename =top_view_image1.getOriginalFilename();
		                    int i = filename.lastIndexOf('.');
		                    if (i >= 0) {
		                            extension4 = filename.substring(i+1);
		                    }
		                    fname4 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_TOP_TMSDOC."+extension4;
		                    
		                   if(validation.checkImageAnmlLength(fname4)  == false){
						 		model.put("msg",validation.top_view_imageMSG);
								return new ModelAndView("redirect:search_convert_veh_1");
							}
		                    
		                    File serverFile = new File(fname4);                       
		                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		                    stream.write(bytes);                        
		                    stream.close();
						}else {
							model.put("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
			    			return new ModelAndView("redirect:search_convert_veh_1");
						}
	            }
	            catch (Exception e) {
	            }
	    	} 
	        if(fname1.equals("") || fname2.equals("") || fname3.equals("") || fname4.equals("") )   
	        {
	    	    int id= updateid.getId();
				Query q = sessioncon.createQuery("from TB_TMS_CONVERT_REQ where id =:id");
				q.setParameter("id", id);
				@SuppressWarnings("unchecked")
				List<TB_TMS_CONVERT_REQ> list = (List<TB_TMS_CONVERT_REQ>) q.list();
				if(fname1.equals(""))
				{
					fname1 =list.get(0).getFront_view_image();
				}
				if(fname2.equals(""))
				{
					fname2 = list.get(0).getBack_view_image();
				}
				if(fname3.equals(""))
				{
					fname3 = list.get(0).getSide_view_image();
				}
				if(fname4.equals(""))
				{
					fname4 = list.get(0).getTop_view_image();
				}
			}
	        conVeh.UpdateConvertms(updateid,fname1,fname2,fname3,fname4);		
	        model.put("msg", "Data Updated Successfully.");
	        return new ModelAndView("redirect:search_convert_veh_1");
		}
		catch(Exception e)
		{
			sessioncon.getTransaction().rollback();
			return null;
		}
		finally
		{
			tx.commit();
			sessioncon.close();
		}
	}
	
	@RequestMapping(value = "/kmlFileDownloadConvertBA")
	public void kmlFileDownloadConvertBA(@ModelAttribute("kmlFileId4") String old_ba_no,@ModelAttribute("fildname") String fildname,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		  final int BUFFER_SIZE = 4096;
		  String ba_no = old_ba_no;
		  String filePath = conVeh.getImagePath(ba_no,fildname);		   
		  model.put("filePath",filePath);
		  ServletContext context = request.getSession().getServletContext();
	      try{
	    	  if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
	    	  {
	    		  String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_Image.jpg";
		          File downloadFile = new File(fullPath);
		          FileInputStream inputStream = new FileInputStream(downloadFile);
		               
		          String mimeType = context.getMimeType(fullPath);
		          if (mimeType == null) {
		        	  mimeType = "application/octet-stream";
		          }
		          response.setContentType(mimeType);
		          response.setContentLength((int) downloadFile.length());
		         /* String headerKey = "Content-Disposition";
		          String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
		          response.setHeader(headerKey, headerValue);*/
		          OutputStream outStream = response.getOutputStream();
		          byte[] buffer = new byte[BUFFER_SIZE];
		          int bytesRead = -1;
		          while ((bytesRead = inputStream.read(buffer)) != -1) {
		        	  outStream.write(buffer, 0, bytesRead);
		          }
		          inputStream.close();
		          outStream.close();
	    	  }
	    	  else
	    	  {
	    		  String fullPath =  filePath;      
			      File downloadFile = new File(fullPath);
			      FileInputStream inputStream = new FileInputStream(downloadFile);
			      String mimeType = context.getMimeType(fullPath);
			      if (mimeType == null) {
			    	  mimeType = "application/octet-stream";
			      }
			      response.setContentType(mimeType);
			      response.setContentLength((int) downloadFile.length());
			     /* String headerKey = "Content-Disposition";
			      String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
			        response.setHeader(headerKey, headerValue);*/
			        OutputStream outStream = response.getOutputStream();
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        inputStream.close();
			        outStream.close();
	              }
	        }catch(Exception ex)
	        {
	        	String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_Image.jpg";
	              File downloadFile = new File(fullPath);
	              FileInputStream inputStream = new FileInputStream(downloadFile);
	              String mimeType = context.getMimeType(fullPath);
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	              response.setContentType(mimeType);
	              response.setContentLength((int) downloadFile.length());
	              /*String headerKey = "Content-Disposition";
	              String headerValue = String.format("attachment; filename=\"%s\"",
	                      downloadFile.getName());
	              response.setHeader(headerKey, headerValue);*/
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/ApprovedConvReqOfVeh", method = RequestMethod.POST)
	public ModelAndView ApprovedConvReqOfVeh(@ModelAttribute("appid") int appid,
			HttpSession sessionA,
			ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			HttpSession session,
			Authentication authentication){
						
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("APP") & !roleType.equals("ALL"))
		{
				return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
		try {	
			
			Session session4 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx4 = session4.beginTransaction();
	        Query q4 = session4.createQuery("from TB_TMS_CONVERT_REQ where id = :id");
	        q4.setParameter("id", appid);
	        List<TB_TMS_CONVERT_REQ> listCon = (List<TB_TMS_CONVERT_REQ>) q4.list();
	        tx4.commit();
			
			String ba_no = listCon.get(0).getOld_ba_no();
			//BigInteger old_mct = listCon.get(0).getOld_mct_number();
			String new_ba_no_first_2 = ba_no.substring(0, 2);
			String New_Veh_Code =listCon.get(0).getVehicle_class_code();
			String list="";
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			//Query q1 = session1.createQuery("SELECT max(SUBSTRING(ba_no, 4,6)) FROM TB_TMS_BANUM_DIRCTRY where SUBSTR(ba_no,4,1) NOT IN ('A','B','C','D','E','F','G','H','P','R','S') and SUBSTR(ba_no,5,1) NOT IN ('-') and ba_no like '__"+New_Veh_Code+"%'");
			Query q1 = session1.createQuery("SELECT max(SUBSTRING(ba_no, 4,6)) FROM TB_TMS_BANUM_DIRCTRY where SUBSTR(ba_no,4,1) NOT IN ('A','B','C','D','E','F','G','H','P','R','S') and SUBSTR(ba_no,5,1) NOT IN ('-') and ba_no like :New_Veh_Code");
	        q1.setParameter("New_Veh_Code", "__"+New_Veh_Code+"%");
			list = (String) q1.list().get(0);
			tx1.commit();
			session1.close();
			int serialNo =  0;
			if(list != null) {
				serialNo = Integer.parseInt(list) + 1;
			}else {
				serialNo = 1;
			}
			
			System.err.println(list);
			System.err.println("--"+q1.toString());
			System.err.println("Serialno:-"+serialNo);
			Common_Search_BA_No_AllocController ba_noGen = new Common_Search_BA_No_AllocController();
			String NewBa_No = ba_noGen.baNoConvert(serialNo, New_Veh_Code,new_ba_no_first_2);
			String ba_no1 = listCon.get(0).getOld_ba_no();
					 
	        Session session3 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx3 = session3.beginTransaction();
	        Query q3 = session3.createQuery("from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no");
	        q3.setParameter("ba_no", ba_no1);
	        List<TB_TMS_BANUM_DIRCTRY> list1 = (List<TB_TMS_BANUM_DIRCTRY>) q3.list();
	        tx3.commit();
	        
	               
	        TB_TMS_BANUM_DIRCTRY ba_num_dir = new TB_TMS_BANUM_DIRCTRY();
	        ba_num_dir.setBa_no(NewBa_No);
	        ba_num_dir.setMct(listCon.get(0).getNew_mct_number());
	        ba_num_dir.setEngine_no(list1.get(0).getEngine_no());
	        ba_num_dir.setChasis_no(list1.get(0).getChasis_no());
	        ba_num_dir.setStatus("Active");
	        ba_num_dir.setOld_ba_no(ba_no1);
	        ba_num_dir.setCreation_by(username);
	        ba_num_dir.setCreation_date(new Date());
	        ba_num_dir.setVersion_no(list1.get(0).getVersion_no());
	        ba_num_dir.setParent_req_id(list1.get(0).getParent_req_id());
	        ba_num_dir.setVeh_cat(list1.get(0).getVeh_cat());
	        ba_num_dir.setApprove_date(new Date());
	        ba_num_dir.setApproved_by(username);
	        ba_num_dir.setYear_of_entry(list1.get(0).getYear_of_entry());
	     
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			sessionHQL.save(ba_num_dir);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			session3.close();
			        
			Session sessionHQL3 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction txHQL3 = sessionHQL3.beginTransaction();
			String hqlHQL1 = "update TB_TMS_BANUM_DIRCTRY  set status='Converted' where ba_no=:ba_no";
			Query updatedEntities = sessionHQL3.createQuery(hqlHQL1);
			updatedEntities.setParameter("ba_no", ba_no);
			updatedEntities.executeUpdate();
			txHQL3.commit();
			sessionHQL3.close();
					 
			//*************************************upload veh images in ba_num_child tbl *****************************
			
		 	Tms_Banum_Req_Child tb_child = new Tms_Banum_Req_Child();
			
		 	//*******************************END***********************************************************  
	        
		 	
	      	Session sessionchild = HibernateUtil.getSessionFactory().openSession();
	        Transaction txchild = sessionchild.beginTransaction();
	        Query qchild = sessionchild.createQuery("from Tms_Banum_Req_Child where ba_no=:ba_no");
	        qchild.setParameter("ba_no", ba_no);
	        List<Tms_Banum_Req_Child> list2 = (List<Tms_Banum_Req_Child>) qchild.list();
	        txchild.commit();
	               
	        if(list2.size() != 0) 
	        {
	        	if(listCon.get(0).getNew_mct_number() != null)
	        	{
	        		BigInteger mct = listCon.get(0).getNew_mct_number(); 
	        		tb_child.setMct_number(mct);  
	        	}
	        	
	        	///////////////Parent Request Id
	        	Tms_Banum_Req_Prnt tb_prnt = new Tms_Banum_Req_Prnt();
				tb_prnt.setRequesting_agency("OTHERS");
				tb_prnt.setSus_no("");
				tb_prnt.setDte_of_reqst(new Date());
				tb_prnt.setBa_no_type(0);
				tb_prnt.setCreation_date(new Date());
				tb_prnt.setCreation_by(username);
				tb_prnt.setVersion_no(1);
				tb_prnt.setStatus("6");
				tb_prnt.setType_of_request("0");
				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int parent_id = (Integer) sessionHQL1.save(tb_prnt);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
	        	
	        	//////////////
	         
	            tb_child.setEngine_no(list2.get(0).getEngine_no());
	            tb_child.setChasis_no(list2.get(0).getChasis_no());
	            tb_child.setRequest_status(list2.get(0).getRequest_status());
	            tb_child.setOld_ba_no(ba_no);
	            tb_child.setBa_no(NewBa_No);
	            tb_child.setCreation_by(username);
	            tb_child.setCreation_date(new Date());
	            tb_child.setVersion_no(list2.get(0).getVersion_no());
	            tb_child.setParent_req_id(parent_id);  
	            tb_child.setApprove_date(new Date());
	            tb_child.setApproved_by(username);
	            tb_child.setAuth_letter_no(list2.get(0).getAuth_letter_no());
	            tb_child.setAxle_wts(list2.get(0).getAxle_wts());
	            tb_child.setNo_of_wheels(list2.get(0).getNo_of_wheels());
	            tb_child.setDrive(list2.get(0).getDrive());
	            tb_child.setLift_capcty(list2.get(0).getLift_capcty());
	            tb_child.setSponsoring_dte(list2.get(0).getSponsoring_dte());
	            tb_child.setRemarks(list2.get(0).getRemarks());
	            tb_child.setAppr_rej_remarks(list2.get(0).getAppr_rej_remarks());
	            tb_child.setQuantity(list2.get(0).getQuantity());
	            tb_child.setInitiating_auth(list2.get(0).getInitiating_auth());
	            tb_child.setConvrsn_done(list2.get(0).getConvrsn_done());
	            tb_child.setCountry_of_origin(list2.get(0).getCountry_of_origin());
	            tb_child.setCreation_by(username);
	            tb_child.setCreation_date(new Date());
	            tb_child.setDate_of_auth_letter(list2.get(0).getDate_of_auth_letter());
	            tb_child.setTech_spec(list2.get(0).getTech_spec());
	            tb_child.setTonnage(list2.get(0).getTonnage());
	            tb_child.setTowing_capcty(list2.get(0).getTowing_capcty());
	            tb_child.setTyp_of_spl_eqpt_role(list2.get(0).getTyp_of_spl_eqpt_role());
	            tb_child.setType_of_fuel(list2.get(0).getType_of_fuel());
	            tb_child.setNew_nomencatre(listCon.get(0).getNew_nomencatre());
	            tb_child.setNo_of_axles(list2.get(0).getNo_of_axles());
	            tb_child.setPresent_cost(list2.get(0).getPresent_cost());
	            tb_child.setPurchas_cost(list2.get(0).getPurchas_cost());
	            tb_child.setVeh_cat(list2.get(0).getVeh_cat());
	            tb_child.setVeh_class(list2.get(0).getVeh_class());
	            tb_child.setVersion_no(list2.get(0).getVersion_no());
	            tb_child.setYear_of_entry(list2.get(0).getYear_of_entry());
	            tb_child.setSpl_eqpmnt_fitd(list2.get(0).getSpl_eqpmnt_fitd());
	            tb_child.setTyp_of_spl_eqpt_role(list2.get(0).getTyp_of_spl_eqpt_role());
	            tb_child.setVehicle_clas_code(list2.get(0).getVehicle_clas_code());
	            tb_child.setEngine_image(list2.get(0).getEngine_image());
	            tb_child.setChasis_img(list2.get(0).getChasis_img());
	            
	            if(listCon.get(0).getFront_view_image() != null) {
	            	tb_child.setFront_view_image(listCon.get(0).getFront_view_image());
	            }else {
	            	tb_child.setFront_view_image(list2.get(0).getFront_view_image());
	            }
	            
	            if(listCon.get(0).getBack_view_image() != null) {
	            	tb_child.setBack_view_image(listCon.get(0).getBack_view_image());
	            }else {
	            	 tb_child.setBack_view_image(list2.get(0).getBack_view_image());
	            }
	            
	            if(listCon.get(0).getSide_view_image() != null) {
	            	tb_child.setSide_view_image(listCon.get(0).getSide_view_image());
	            }else {
	            	tb_child.setSide_view_image(list2.get(0).getSide_view_image());
	            }
	            
	            if(listCon.get(0).getTop_view_image() != null) {
	            	tb_child.setTop_view_image(listCon.get(0).getTop_view_image());
	            }else {
	            	tb_child.setTop_view_image(list2.get(0).getTop_view_image());
	            }
	        	Session sessionchildHQL = HibernateUtil.getSessionFactory().openSession();
	        	sessionchildHQL.beginTransaction();
	        	sessionchildHQL.save(tb_child);
	        	sessionchildHQL.getTransaction().commit();
	        	sessionchildHQL.close();
	        }
	
        	model.put("msg", conVeh.setApprovedConvReqOfVeh(appid,NewBa_No,ba_no,username,date));
		}catch (Exception e) {
			model.put("msg", "Data Not Approved");
		}
		return new ModelAndView("redirect:search_convert_veh_1");
	}
	
	@RequestMapping(value = "/admin/rejectConvReqOfVeh", method = RequestMethod.POST)
	public ModelAndView rejectConvReqOfVeh(@ModelAttribute("rejectid") int rejectid,HttpSession sessionA,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("APP") && !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}

		model.put("msg", conVeh.setRejectConvReqOfVeh(rejectid));	
		return new ModelAndView("redirect:search_convert_veh_1");
	}
	
	@RequestMapping(value = "/admin/deleterejectConvReqOfVeh",method = RequestMethod.POST)
	public ModelAndView deleterejectConvReqOfVeh(@ModelAttribute("deleteid") int deleteid,HttpSession sessionA,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
		model.put("msg", conVeh.setDeleteConvReqOfVeh(deleteid));	
		return new ModelAndView("redirect:search_convert_veh_1");
	}
}
