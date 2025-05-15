package com.controller.tms;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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

import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.Tms_Banum_Req_Child;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Common_Update_Engine_ChassisController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/update_eng_chaUrl", method = RequestMethod.GET)
	public ModelAndView search_prf_mstUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "ba_no", required = false) String ba_no,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("update_eng_chaUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ba_no", ba_no);
		return new ModelAndView("update_eng_chaTile");
	}
	
	 @RequestMapping(value = "/geteng_chas",method = RequestMethod.POST)
	 public @ResponseBody ArrayList<List<String>> geteng_chas(String ba_no,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		 Query q = session.createQuery("select engine_no,chasis_no from TB_TMS_BANUM_DIRCTRY where ba_no =:ba_no");
		 q.setParameter("ba_no", ba_no);
		 @SuppressWarnings("unchecked")
		 List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String engine_no = String.valueOf(listObject[0]);
		   		String chasis_no = String.valueOf(listObject[1]);
			    String base64EncodedEncryptedengine_no = new String(Base64.encodeBase64(c.doFinal(engine_no.getBytes())));
			    String base64EncodedEncryptedchasis_no = new String(Base64.encodeBase64(c.doFinal(chasis_no.getBytes())));
		   		List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedengine_no);
			    EncList.add(base64EncodedEncryptedchasis_no);
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
	 
	 @RequestMapping(value = "/eng_chass_noUrl", method = RequestMethod.POST)
		public ModelAndView ApprovedItemArmUrl(@ModelAttribute("eng_chass_noCmd") TB_TMS_BANUM_DIRCTRY tb_badir,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("update_eng_chaUrl", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String ba_no=request.getParameter("ba_no_hide");
			String engin_no=request.getParameter("engine_no");
			String chasis_no=request.getParameter("chasis_no");
			Common_Banum_DirctryController ba_dir = new Common_Banum_DirctryController();
			if(ba_no.equals("") || ba_no.equals("null") || ba_no.equals(null))
			{
				model.put("msg", "Please Enter BA No.");
				return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else if(validation.ba_noLength(request.getParameter("ba_no")) == false)
			{
			 		model.put("msg",validation.ba_noMSG);
					return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else if(engin_no.equals("") || engin_no.equals("null") || engin_no.equals(null))
			{
				model.put("msg", "Please Select Engine No.");
				return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else if(validation.initiating_authTMSLength(engin_no) == false)
			{
			 		model.put("msg",validation.engine_noMSG);
					return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else if(chasis_no.equals("") || chasis_no.equals("null") || chasis_no.equals(null))
			{
				model.put("msg", "Please Select Chasis No.");
				return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else if(validation.initiating_authTMSLength(chasis_no) == false)
			{
			 		model.put("msg",validation.chasis_noMSG);
					return new ModelAndView("redirect:update_eng_chaUrl");
			}
			else
			{
				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				Session sessionU = HibernateUtilNA.getSessionFactory().openSession();
				Transaction txU = sessionU.beginTransaction();
				try
				{
					Query q = session1.createQuery("from TB_TMS_BANUM_DIRCTRY where ba_no !=:ba_no and (engine_no=:engine_no or chasis_no=:chasis_no)");
					q.setParameter("ba_no", ba_no);
				    q.setParameter("engine_no", engin_no);
					q.setParameter("chasis_no", chasis_no);
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q.list();
					tx.commit();
					if(list.size() == 0)
					{
						String hqlUpdate = "update TB_TMS_BANUM_DIRCTRY c set c.engine_no=:engine_no,c.chasis_no=:chasis_no where c.ba_no = :ba_no";
						int app = sessionU.createQuery( hqlUpdate ).setString( "engine_no", engin_no).setString( "chasis_no", chasis_no).setString( "ba_no", ba_no ).executeUpdate();
						
						String hqlUpdate1 = "update Tms_Banum_Req_Child c set c.engine_no=:engine_no,c.chasis_no=:chasis_no where c.ba_no = :ba_no";
						int app1 = sessionU.createQuery( hqlUpdate1 ).setString( "engine_no", engin_no).setString( "chasis_no", chasis_no).setString( "ba_no", ba_no ).executeUpdate();
						
						txU.commit();
						if(app > 0 && app1 > 0) {
							model.put("msg", "Updated Successfully.");
						}else {
							model.put("msg", "NOT Update Successfully.");
						}
					}
					else
					{
						model.put("ba_no", ba_no);
						model.put("engin_no", engin_no);
						model.put("chasis_no", chasis_no);
						model.put("msg", "Data Already Exist.");
					}
				}
				catch(Exception e)
				{
					session1.getTransaction().rollback();
				}
				finally
				{
					session1.close();
					sessionU.close();
				}
				return new ModelAndView("redirect:update_eng_chaUrl");
			}
		}
}