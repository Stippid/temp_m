package com.controller.tms;

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
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MctSlotDAO;
import com.models.TB_TMS_MCT_SLOT_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Common_Mct_slotController {
	@Autowired
	MctSlotDAO makeMasretDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	ValidationController validation = new ValidationController();
		
	@RequestMapping(value="/admin/mctslot" , method = RequestMethod.GET)
	public ModelAndView mctslot(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctslot", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		return new ModelAndView("mctslotTiles","tms_slot_masterCmd", new TB_TMS_MCT_SLOT_MASTER());
	}

	@RequestMapping(value = "/mctSlotAction" , method = RequestMethod.POST)
	public ModelAndView mctSlotAction(@ModelAttribute("tms_slot_masterCmd") TB_TMS_MCT_SLOT_MASTER mct,HttpServletRequest request,ModelMap model,HttpSession session) {		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctslot", roleid);	
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
			  return new ModelAndView("redirect:mctslot");	  
		}
		String Strcode_no_from1 = request.getParameter("code_no_from1").toString();
		if(Strcode_no_from1 != "" && !pattern.matcher(Strcode_no_from1).matches() == false) 
		{
			  model.put("msg", "Please Enter valid MCT No From.");
			  return new ModelAndView("redirect:mctslot");
		}
		String StrPrf_code = request.getParameter("prf_code").toString();
		if(StrPrf_code != "" && !pattern.matcher(StrPrf_code).matches() == false) 
		{
			  model.put("msg", "Please Enter valid PRF Gp.");
			  return new ModelAndView("redirect:mctslot");	  
		}
		if(request.getParameter("code_no_from1").toString().equals("") || request.getParameter("code_no_from1").toString().equals("null") || request.getParameter("code_no_from1").toString().equals(null))
		{
			CodeFrom = 0;
			model.put("msg", "Please Enter Code Number From");
			return new ModelAndView("redirect:mctslot");
		}
		else
		{
			if(validation.code_no_fromLength(request.getParameter("code_no_from1").toString()) == false){
		 		model.put("msg",validation.code_no_fromMSG);
				return new ModelAndView("redirect:mctslot");
			}
			CodeFrom = Integer.parseInt(request.getParameter("code_no_from1").toString());
			mct.setCode_no_from(CodeFrom);
		}
		 if(mct.getGroup_name().toString().equals("") || mct.getGroup_name().toString().equals("null") || mct.getGroup_name().toString().equals(null))
		{
			model.put("msg", "Please Enter Group.");
			return new ModelAndView("redirect:mctslot");
		}
		else if(validation.group_nameLength(mct.getGroup_name().toString()) == false)
		{
		 		model.put("msg",validation.group_nameMSG);
				return new ModelAndView("redirect:mctslot");
		}
		else if(mct.getVehicle_class_code().equals("") || mct.getVehicle_class_code().equals("null") || mct.getVehicle_class_code().equals(null) )
		{
			model.put("msg", "Please Enter Vehicle Class Code.");
			return new ModelAndView("redirect:mctslot");
		}
		else if(request.getParameter("range") != "" && Integer.parseInt(request.getParameter("range"))< 0 )
		{
			model.put("msg", "Please Enter Range.");
			return new ModelAndView("redirect:mctslot");
		}
		else if(validation.rangeLength(request.getParameter("range")) == false)
		{
	 		model.put("msg",validation.rangeMSG);
			return new ModelAndView("redirect:mctslot");
		}
		else if(request.getParameter("prf_code") != "" && Integer.parseInt(request.getParameter("prf_code"))< 0 )
		{
			model.put("msg", "Please Enter PRF Gp.");
			return new ModelAndView("redirect:mctslot");
		}
		else if(validation.prf_codeLength(request.getParameter("prf_code")) == false)
		{
	 		model.put("msg",validation.prf_codeMSG);
			return new ModelAndView("redirect:mctslot");
		}
		else if(validation.vehicle_class_codeLength(request.getParameter("vehicle_class_code")) == false)
		{
	 		model.put("msg",validation.vehicle_class_codeMSG);
			return new ModelAndView("redirect:mctslot");
		}
		else
		{
			int range = Integer.parseInt(request.getParameter("range"));
			int code_to = mct.getCode_no_from() + (range-1) ;
			String veh_class_code = request.getParameter("vehicle_class_code");
			if(String.valueOf(code_to).length()  > 4)
			{
				 model.put("msg", "Maximum 4 digit is allowed in MCT.");
				 return new ModelAndView("redirect:mctslot");
			}
			mct.setCode_no_to(code_to);
			mct.setCreated_by(username);
			mct.setCreated_on(date);
			mct.setVehicle_class_code(veh_class_code.toUpperCase());

				try 
				{
					session1.beginTransaction();
					session1.save(mct);
					session1.getTransaction().commit();
					model.put("msg", " MCT Slot Successfully Created.");
					return new ModelAndView("redirect:mctslot");
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
	
	@RequestMapping(value = "/getMaxToCode", method = RequestMethod.POST)
	public @ResponseBody List<String> getMaxToCode(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select max(code_no_to) from TB_TMS_MCT_SLOT_MASTER ");
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
	
	@RequestMapping(value = "/getGroupNamelist")
	public @ResponseBody List<TB_TMS_MCT_SLOT_MASTER> getGroupNamelist(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_MCT_SLOT_MASTER");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_SLOT_MASTER> list = (List<TB_TMS_MCT_SLOT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getmaxPRFCodeID", method = RequestMethod.POST)
	public @ResponseBody String getmaxPRFCodeID() {			
		return makeMasretDAO.getmaxPRFCodeID();
	}
}