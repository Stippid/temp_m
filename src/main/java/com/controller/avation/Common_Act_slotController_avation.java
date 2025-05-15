package com.controller.avation;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.avation.ActSlotDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_AVIATION_ACT_SLOT_MASTER;
//import com.models.TB_TMS_MCT_SLOT_MASTER;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Common_Act_slotController_avation {
	@Autowired
	ActSlotDAO makeMasretDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	ValidationController validation = new ValidationController();
		
	@RequestMapping(value="/admin/actslot_avation" , method = RequestMethod.GET)
	public ModelAndView actslot_avation(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("actslot_avation", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		
		return new ModelAndView("actslotTiles_avation","tms_slot_masterCmd_avation", new TB_AVIATION_ACT_SLOT_MASTER());
	} 
	
	@RequestMapping(value="/admin/add_abc_RPAS" , method = RequestMethod.GET)
	public ModelAndView add_abc_RPAS(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_abc_RPAS", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		
		return new ModelAndView("addabcRPASTiles","tms_slot_masterCmd_avation", new TB_AVIATION_ACT_SLOT_MASTER());
	} 

	@RequestMapping(value = "/actSlotAction_avation" , method = RequestMethod.POST)
	public ModelAndView actSlotAction_avation(@ModelAttribute("tms_slot_masterCmd_avation") TB_AVIATION_ACT_SLOT_MASTER mct,HttpServletRequest request,ModelMap model,HttpSession session) {		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("actslot_avation", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int CodeFrom = 0;
		Session session1=HibernateUtil.getSessionFactory().openSession();
		
		Pattern pattern = Pattern.compile(".*[^0-9].*");
		String Strrange = request.getParameter("range").toString();
		
		if(Strrange != "" && !pattern.matcher(Strrange).matches() == false) 
		{	
			  model.put("msg", "Please Enter valid Range.");
			  return new ModelAndView("redirect:actslot_avation");	  
		}
		String Strcode_no_from1 = request.getParameter("code_no_from1").toString();
		if(Strcode_no_from1 != "" && !pattern.matcher(Strcode_no_from1).matches() == false) 
		{
			  model.put("msg", "Please Enter valid ACT No From.");
			  return new ModelAndView("redirect:actslot_avation");
		}
		String StrPrf_code = request.getParameter("abc_code").toString();
		if(StrPrf_code != "" && !pattern.matcher(StrPrf_code).matches() == false) 
		{
			  model.put("msg", "Please Enter valid ABC Gp.");
			  return new ModelAndView("redirect:actslot_avation");	  
		}
		if(request.getParameter("code_no_from1").toString().equals("") || request.getParameter("code_no_from1").toString().equals("null") || request.getParameter("code_no_from1").toString().equals(null))
		{
			CodeFrom = 0;
			model.put("msg", "Please Enter Code Number From");
			return new ModelAndView("redirect:actslot_avation");
		}
		else
		{
			if(validation.code_no_fromLength(request.getParameter("code_no_from1").toString()) == false){
		 		model.put("msg",validation.code_no_fromMSG);
				return new ModelAndView("redirect:actslot_avation");
			}
			CodeFrom = Integer.parseInt(request.getParameter("code_no_from1").toString());
			mct.setCode_no_from(CodeFrom);
		}
		 if(mct.getGroup_name().toString().equals("") || mct.getGroup_name().toString().equals("null") || mct.getGroup_name().toString().equals(null))
		{
			model.put("msg", "Please Enter Group.");
			return new ModelAndView("redirect:actslot_avation");
		}
		else if(validation.group_nameLength(mct.getGroup_name().toString().trim()) == false)
		{
		 		model.put("msg",validation.group_nameMSG);
				return new ModelAndView("redirect:actslot_avation");
		}

		else if(request.getParameter("range") != "" && Integer.parseInt(request.getParameter("range"))< 0 )
		{
			model.put("msg", "Please Enter Range.");
			return new ModelAndView("redirect:actslot_avation");
		}
		else if(validation.rangeLength(request.getParameter("range")) == false)
		{
	 		model.put("msg",validation.rangeMSG);
			return new ModelAndView("redirect:actslot_avation");
		}
		else if(request.getParameter("abc_code") != "" && Integer.parseInt(request.getParameter("abc_code"))< 0 )
		{
			model.put("msg", "Please Enter ABC Gp.");
			return new ModelAndView("redirect:actslot_avation");
		}
		else if(validation.prf_codeLength(request.getParameter("abc_code")) == false)
		{
	 		model.put("msg",validation.abc_codeMSG);
			return new ModelAndView("redirect:actslot_avation");
		}

		else
		{
			int range = Integer.parseInt(request.getParameter("range"));
			int code_to = mct.getCode_no_from() + (range-1) ;
			String type_of_aircraft = request.getParameter("type_of_aircraft").trim();
			if(String.valueOf(code_to).length()  > 4)
			{
				 model.put("msg", "Maximum 4 digit is allowed in ACT.");
				 return new ModelAndView("redirect:actslot_avation");
			}
			mct.setCode_no_to(code_to);
			mct.setCreated_by(username);
			mct.setCreated_on(date);
			mct.setType_of_aircraft(type_of_aircraft.toUpperCase());

				try 
				{
					session1.beginTransaction();
					session1.save(mct);
					session1.getTransaction().commit();
					model.put("msg", " ACT Slot Successfully Created.");
					return new ModelAndView("redirect:actslot_avation");
				}catch (Exception e){
					session1.getTransaction().rollback();
					return null;
				} 
				finally
				{
					session1.close();
				}
		}
	}
	
	@RequestMapping(value = "/actabcAction_rpas" , method = RequestMethod.POST)
	public ModelAndView actabcAction_rpas(@ModelAttribute("tms_slot_masterCmd_avation") TB_AVIATION_ACT_SLOT_MASTER mct,HttpServletRequest request,ModelMap model,HttpSession session) {		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_abc_RPAS", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int CodeFrom = 0;
		Session session1=HibernateUtil.getSessionFactory().openSession();
		
		Pattern pattern = Pattern.compile(".*[^0-9].*");
		String Strrange = request.getParameter("range").toString();
		
		if(Strrange != "" && !pattern.matcher(Strrange).matches() == false) 
		{	
			  model.put("msg", "Please Enter valid Range.");
			  return new ModelAndView("redirect:add_abc_RPAS");	  
		}
		String Strcode_no_from1 = request.getParameter("code_no_from1").toString();
		if(Strcode_no_from1 != "" && !pattern.matcher(Strcode_no_from1).matches() == false) 
		{
			  model.put("msg", "Please Enter valid ACT No From.");
			  return new ModelAndView("redirect:add_abc_RPAS");
		}
		String StrPrf_code = request.getParameter("abc_code").toString();
		if(StrPrf_code != "" && !pattern.matcher(StrPrf_code).matches() == false) 
		{
			  model.put("msg", "Please Enter valid ABC Gp.");
			  return new ModelAndView("redirect:add_abc_RPAS");	  
		}
		if(request.getParameter("code_no_from1").toString().equals("") || request.getParameter("code_no_from1").toString().equals("null") || request.getParameter("code_no_from1").toString().equals(null))
		{
			CodeFrom = 0;
			model.put("msg", "Please Enter Code Number From");
			return new ModelAndView("redirect:add_abc_RPAS");
		}
		else
		{
			if(validation.code_no_fromLength(request.getParameter("code_no_from1").toString()) == false){
		 		model.put("msg",validation.code_no_fromMSG);
				return new ModelAndView("redirect:add_abc_RPAS");
			}
			CodeFrom = Integer.parseInt(request.getParameter("code_no_from1").toString());
			mct.setCode_no_from(CodeFrom);
		}
		 if(mct.getGroup_name().toString().equals("") || mct.getGroup_name().toString().equals("null") || mct.getGroup_name().toString().equals(null))
		{
			model.put("msg", "Please Enter Group.");
			return new ModelAndView("redirect:add_abc_RPAS");
		}
		else if(validation.group_nameLength(mct.getGroup_name().toString().trim()) == false)
		{
		 		model.put("msg",validation.group_nameMSG);
				return new ModelAndView("redirect:add_abc_RPAS");
		}

		else if(request.getParameter("range") != "" && Integer.parseInt(request.getParameter("range"))< 0 )
		{
			model.put("msg", "Please Enter Range.");
			return new ModelAndView("redirect:add_abc_RPAS");
		}
		else if(validation.rangeLength(request.getParameter("range")) == false)
		{
	 		model.put("msg",validation.rangeMSG);
			return new ModelAndView("redirect:add_abc_RPAS");
		}
		else if(request.getParameter("abc_code") != "" && Integer.parseInt(request.getParameter("abc_code"))< 0 )
		{
			model.put("msg", "Please Enter ABC Gp.");
			return new ModelAndView("redirect:add_abc_RPAS");
		}
		else if(validation.prf_codeLength(request.getParameter("abc_code")) == false)
		{
	 		model.put("msg",validation.abc_codeMSG);
			return new ModelAndView("redirect:add_abc_RPAS");
		}

		else
		{
			int range = Integer.parseInt(request.getParameter("range"));
			int code_to = mct.getCode_no_from() + (range-1) ;
			String type_of_aircraft = request.getParameter("type_of_aircraft").trim();
			if(String.valueOf(code_to).length()  > 4)
			{
				 model.put("msg", "Maximum 4 digit is allowed in ACT.");
				 return new ModelAndView("redirect:add_abc_RPAS");
			}
			mct.setCode_no_to(code_to);
			mct.setCreated_by(username);
			mct.setCreated_on(date);
			mct.setType_of_aircraft(type_of_aircraft.toUpperCase());

				try 
				{
					session1.beginTransaction();
					session1.save(mct);
					session1.getTransaction().commit();
					model.put("msg", " ACT Slot Successfully Created.");
					return new ModelAndView("redirect:add_abc_RPAS");
				}catch (Exception e){
					session1.getTransaction().rollback();
					return null;
				} 
				finally
				{
					session1.close();
				}
		}
	}
	
	
	@RequestMapping(value = "/getMaxToCode_avation", method = RequestMethod.POST)
	public @ResponseBody List<String> getMaxToCode_avation(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select max(code_no_to) from TB_AVIATION_ACT_SLOT_MASTER ");
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
				String code_no_to = String.valueOf(list.get(i));
				encCode = c.doFinal(code_no_to.getBytes());
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
//	
	@RequestMapping(value = "/getGroupNamelist_avation")
	public @ResponseBody List<TB_AVIATION_ACT_SLOT_MASTER> getGroupNamelist_avation(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_AVIATION_ACT_SLOT_MASTER");
		@SuppressWarnings("unchecked")
		List<TB_AVIATION_ACT_SLOT_MASTER> list = (List<TB_AVIATION_ACT_SLOT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getmaxPRFCodeID_avation", method = RequestMethod.POST)
	public @ResponseBody String getmaxPRFCodeID_avation() {			
		return makeMasretDAO.getmaxABCCodeID();
	}
	
	@RequestMapping(value = "/getAviationGroupNamelist")
	public @ResponseBody List<TB_AVIATION_ACT_SLOT_MASTER> getAviationGroupNamelist(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_AVIATION_ACT_SLOT_MASTER where type_of_aircraft='ROTARY WING'");
		@SuppressWarnings("unchecked")
		List<TB_AVIATION_ACT_SLOT_MASTER> list = (List<TB_AVIATION_ACT_SLOT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getAviationGroupRPASNamelist")
	public @ResponseBody List<TB_AVIATION_ACT_SLOT_MASTER> getAviationGroupRPASNamelist(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_AVIATION_ACT_SLOT_MASTER where type_of_aircraft='RPAS'");
		@SuppressWarnings("unchecked")
		List<TB_AVIATION_ACT_SLOT_MASTER> list = (List<TB_AVIATION_ACT_SLOT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
}

