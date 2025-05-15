package com.controller.avation;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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

import com.controller.cue.ProvisionPolicyController;
import com.controller.tms.Common_Mct_MainController;
import com.controller.validation.ValidationController;
import com.dao.avation.AddActNoDAO;
import com.dao.avation.AddActNoDAOImpl;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.AddMctNoDAO;
import com.dao.tms.AddMctNoDAOImp;
import com.models.MMS_Domain_Values;
import com.models.TB_AVIATION_ACT_MASTER;
import com.models.TB_AVIATION_RPAS_ACT_MASTER;
import com.models.TB_TMS_MCT_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Common_ActNoController {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AddActNoDAO addactDAO = new AddActNoDAOImpl();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	Common_Act_MainController act_cm = new Common_Act_MainController();
	ProvisionPolicyController discard= new ProvisionPolicyController();
	
	@RequestMapping(value = "/admin/add_act_noURL")
	public ModelAndView Add_act_noURL(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		
		Mmap.put("msg", msg);
		return new ModelAndView("add_act_noTiles");
	}
	@RequestMapping(value = "/admin/add_act_noRPAS")
	public ModelAndView Add_act_noRPAS(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg" ,required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		
		Mmap.put("msg", msg);
		return new ModelAndView("add_act_noRPASTiles");
	}
	
@RequestMapping(value = "/getmakemasteraviationtMctNomenToNoList", method = RequestMethod.POST)
		public @ResponseBody List<String> getmakemasteraviationtMctNomenToNoList(String act_nomen,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select act_main_id from TB_AVIATION_ACT_MAIN_MASTER where act_nomen=:act_nomen");
			q.setParameter("act_nomen", act_nomen);
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
		
		
		@RequestMapping(value = "/getActNomenList", method = RequestMethod.POST)
		public @ResponseBody List<String> getActNomenList(String actMain,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select act_nomen from TB_AVIATION_ACT_MAIN_MASTER where type_of_aircraft='ROTARY WING' and upper(act_nomen) LIKE :actMain order by act_nomen").setMaxResults(10);
			q.setParameter("actMain",'%'+actMain.toUpperCase()+'%');
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
		
		@RequestMapping(value = "/getaddactnoactNomenToNoList", method = RequestMethod.POST)
		public @ResponseBody ArrayList<List<String>> getaddactnoactNomenToNoList(String act_nomen,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select act_main_id,abc_code,type_of_aircraft from TB_AVIATION_ACT_MAIN_MASTER where act_nomen=:act_nomen");
			q.setParameter("act_nomen", act_nomen);
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
			for(Object[] listObject: list){
		    	String act_main_id = String.valueOf(listObject[0]);
		   		String abc_code = String.valueOf(listObject[1]);
		   		String type_of_aircraft = String.valueOf(listObject[2]);
			    String base64EncodedEncryptedact_main_id = new String(Base64.encodeBase64(c.doFinal(act_main_id.getBytes())));
			    String base64EncodedEncryptedabc_code = new String(Base64.encodeBase64(c.doFinal(abc_code.getBytes())));
			    String base64EncodedEncryptedtype_of_aircaft = new String(Base64.encodeBase64(c.doFinal(type_of_aircraft.getBytes())));
			   	List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedact_main_id);
			    EncList.add(base64EncodedEncryptedabc_code);
			    EncList.add(base64EncodedEncryptedtype_of_aircaft);
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
		
		//Added by Mitesh(18-11-2024)
		@RequestMapping(value = "/getactNomenMakeList", method = RequestMethod.POST)
		public @ResponseBody List<String> getactNomenMakeList(String act_slot_id,String desc_make,HttpSession sessionUserId){
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select description from TB_AVIATION_MAKE_MASTER where act_slot_id=:act_slot_id and upper(description) like :desc_make").setMaxResults(10);
			q.setParameter("act_slot_id", act_slot_id);
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
		
		//Added by Mitesh(18-11-2024)
		@RequestMapping(value = "/getActNomenMakeToNoList", method = RequestMethod.POST)
		public @ResponseBody List<String> getActNomenMakeToNoList(String act_main_id,String description,HttpSession sessionUserId){
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();   
			Query q = session.createQuery("select make_no from TB_AVIATION_MAKE_MASTER where description=:description and  act_slot_id=:act_main_id");
			q.setParameter("description", description);
			q.setParameter("act_main_id", act_main_id);
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
		
		//Added by Mitesh(18-11-2024)
		 @RequestMapping(value = "/add_act_noAction", method = RequestMethod.POST)
		 public ModelAndView Add_act_noAction(@ModelAttribute("add_act_noCMD") TB_AVIATION_ACT_MASTER rs, HttpServletRequest request, ModelMap model, HttpSession session) {
				String  roleid = session.getAttribute("roleid").toString();
				/*Boolean val = roledao.ScreenRedirect("add_act_noURL", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}*/
			 String username = session.getAttribute("username").toString().trim();
			 String act_main_id =request.getParameter("act_main_id").toString().trim();
			 String  desc_make = request.getParameter("desc_make").toString().trim();
			 String  desc_model = request.getParameter("desc_model").toString().trim();
			 String  make_no = request.getParameter("make_no").toString().trim();
			 String  model_id = request.getParameter("model_id").toString().trim();
			 String  width = request.getParameter("width").toString().trim();
			 String  length = request.getParameter("length").toString().trim();
			 String  height = request.getParameter("height").toString().trim();
			 
			if(act_main_id.equals("") || act_main_id.equals("null")|| act_main_id.equals(null))
			 {
				 model.put("msg","Please Enter the ACT No.");
				 return new ModelAndView("redirect:add_act_noURL");
			 }
			else if(validation.act_main_idLength(act_main_id) == false){
		 		model.put("msg",validation.mct_main_idMSG);
				return new ModelAndView("redirect:add_act_noURL");
			}
			 else if(request.getParameter("abc_group").equals("") || request.getParameter("abc_group").equals("null") || request.getParameter("abc_group").equals(null)) {
				 model.put("msg","Please Select the ABC.");
				 return new ModelAndView("redirect:add_act_noURL");
			 }
			 else if(validation.abc_codeLength(request.getParameter("abc_group")) == false){
			 		model.put("msg",validation.abc_codeMSG);
					return new ModelAndView("redirect:add_act_noURL");
			}
			else if(request.getParameter("type_of_aircraft") == null)
			 {
				 model.put("msg","Please Select Type Of Aircraft.");
				 return new ModelAndView("redirect:add_act_noURL");
			 }
			 else if(rs.getAuth_letter_no().equals("")) {
				 model.put("msg","Please Select Auth Letter No.");
				 return new ModelAndView("redirect:add_act_noURL");
			 }
			 else if(validation.auth_letter_noTMSLength(rs.getAuth_letter_no()) == false){
			 		model.put("msg",validation.auth_letter_noTMSMSG);
					return new ModelAndView("redirect:add_act_noURL");
				}	
			 else if(validation.axle_wtsLength(String.valueOf(rs.getLift_capacity()) ) == false){
			 		model.put("msg",validation.lift_capacityMSG);
					return new ModelAndView("redirect:add_act_noURL");
				}
			 else if(validation.checkUnit_nameLength(request.getParameter("sponsoring_dte") ) == false){
			 		model.put("msg",validation.sponsoring_dteMSG);
					return new ModelAndView("redirect:add_act_noURL");
				}
			 else
			 {
				 Session sessionHql = HibernateUtil.getSessionFactory().openSession();
				 try
				 {
					 String std_nomclature = "";
					
						 std_nomclature = request.getParameter("act_nomen").trim() + " " + request.getParameter("desc_make").trim() + " "+ request.getParameter("desc_model").trim();
						 
				

					 if(std_nomclature.equals("")) {
						 model.put("msg","Please Select Proper Std Nomanclature.");
						 return new ModelAndView("redirect:add_act_noURL");
					 }
					 if(validation.std_nomclatureTMSLength(std_nomclature) == false){
					 		model.put("msg",validation.std_nomclatureTMSMSG);
							return new ModelAndView("redirect:add_act_noURL");
						}
					 Date date = new Date();
					 BigInteger act = new BigInteger(request.getParameter("act_main_id").trim() + request.getParameter("make_no").trim() + request.getParameter("model_id").trim());
					 if(act.equals("")) {
						 model.put("msg","Please Select Proper ACT No.");
						 return new ModelAndView("redirect:add_act_noURL");	 
					 }
					 
					 int crew =  Integer.parseInt(request.getParameter("crew_code").trim());
					 String fuel_capacity = request.getParameter("fuel_capacity").trim();
					 String type_of_fuel = request.getParameter("type_fuel").trim();
					 String carriage_capacity = request.getParameter("carriage_capacity").trim();
					 
					 rs.setCreation_by(username);
					 rs.setCreation_date(date);
					 rs.setAct(act);
					 rs.setStd_nomclature(std_nomclature);
					 rs.setFuel_tank_capacity(fuel_capacity);
					 rs.setType_of_fuel(type_of_fuel);
					 rs.setCrew(crew);
					 rs.setUnder_slung_carriage_capacity(carriage_capacity);
					 rs.setHeight(Double.parseDouble(height));
					 rs.setWidth(Double.parseDouble(width));
					 rs.setLength(Double.parseDouble(length));
					 
					 

					 sessionHql.beginTransaction();
					 
					 if(addactDAO.ifExistActNo(act) != false) {
						 model.put("msg", "ACT No Already Exist.");
						 return new ModelAndView("redirect:add_act_noURL");
					 }else {
						 sessionHql.save(rs);
						 sessionHql.getTransaction().commit();
						
				         model.put("msg", "ACT No Created Successfully.");		 
						 return new ModelAndView("redirect:add_act_noURL");	 
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
		 
		 	// Added By Mitesh(20-11-2024)
			public @ResponseBody List<TB_AVIATION_ACT_MASTER> getaircrafttypetList(HttpSession s1) {
				int userid = Integer.parseInt(s1.getAttribute("userId").toString());

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery("select distinct std_nomclature from TB_AVIATION_ACT_MASTER where std_nomclature NOT IN('CHEETAH','CHEETAL','CHETAK') ");
				@SuppressWarnings("unchecked")
				List<TB_AVIATION_ACT_MASTER> list = (List<TB_AVIATION_ACT_MASTER>) q.list();
				tx.commit();
				session.close();
				return list;
			}
		
			//Added by Mitesh(21-11-2024)
			 @RequestMapping(value = "/add_rpas_act_noAction", method = RequestMethod.POST)
			 public ModelAndView Add_rpas_act_noAction(@ModelAttribute("add_rpas_act_noCMD") TB_AVIATION_RPAS_ACT_MASTER rs, HttpServletRequest request, ModelMap model, HttpSession session) {
					String  roleid = session.getAttribute("roleid").toString();
					/*Boolean val = roledao.ScreenRedirect("add_act_noRPAS", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}*/
				 String username = session.getAttribute("username").toString().trim();
				 String act_main_id =request.getParameter("act_main_id").toString().trim();
				 String  desc_make = request.getParameter("desc_make").toString().trim();
				 String  desc_model = request.getParameter("desc_model").toString().trim();
				 String  make_no = request.getParameter("make_no").toString().trim();
				 String  model_id = request.getParameter("model_id").toString().trim();
				if(act_main_id.equals("") || act_main_id.equals("null")|| act_main_id.equals(null))
				 {
					 model.put("msg","Please Enter the ACT No.");
					 return new ModelAndView("redirect:add_act_noRPAS");
				 }
				else if(validation.act_main_idLength(act_main_id) == false){
			 		model.put("msg",validation.mct_main_idMSG);
					return new ModelAndView("redirect:add_act_noRPAS");
				}
				/* else if(desc_make.equals("") || desc_make.equals("null")|| desc_make.equals(null))
				 {
					 model.put("msg","Please Enter the MAKE Nomeclature.");
					 return new ModelAndView("redirect:add_act_noURL");
				 }
				 else if(make_no.equals("") || make_no.equals("null")|| make_no.equals(null))
				 {
					 model.put("msg","Please Enter the MAKE No.");
					 return new ModelAndView("redirect:add_act_noURL");
				 }
				 else if(validation.make_noLength(make_no) == false)
				 {
				 		model.put("msg",validation.make_noMSG);
						return new ModelAndView("redirect:add_act_noURL");
				 }*/
				 else if(request.getParameter("abc_group").equals("") || request.getParameter("abc_group").equals("null") || request.getParameter("abc_group").equals(null)) {
					 model.put("msg","Please Select the ABC.");
					 return new ModelAndView("redirect:add_act_noRPAS");
				 }
				 else if(validation.abc_codeLength(request.getParameter("abc_group")) == false){
				 		model.put("msg",validation.abc_codeMSG);
						return new ModelAndView("redirect:add_act_noRPAS");
				}
				else if(request.getParameter("type_of_aircraft") == null)
				 {
					 model.put("msg","Please Select Type Of Aircraft.");
					 return new ModelAndView("redirect:add_act_noRPAS");
				 }
				 else if(rs.getAuth_letter_no().equals("")) {
					 model.put("msg","Please Select Auth Letter No.");
					 return new ModelAndView("redirect:add_act_noRPAS");
				 }
				 else if(validation.auth_letter_noTMSLength(rs.getAuth_letter_no()) == false){
				 		model.put("msg",validation.auth_letter_noTMSMSG);
						return new ModelAndView("redirect:add_act_noRPAS");
					}	
				 else if(validation.checkUnit_nameLength(request.getParameter("sponsoring_dte") ) == false){
				 		model.put("msg",validation.sponsoring_dteMSG);
						return new ModelAndView("redirect:add_act_noRPAS");
					}
				 else
				 {
					 Session sessionHql = HibernateUtil.getSessionFactory().openSession();
					 try
					 {
						 String std_nomclature = "";
						
							 std_nomclature = request.getParameter("act_nomen").trim() + " " + request.getParameter("desc_make").trim() + " "+ request.getParameter("desc_model").trim();
							 
					

						 if(std_nomclature.equals("")) {
							 model.put("msg","Please Select Proper Std Nomanclature.");
							 return new ModelAndView("redirect:add_act_noRPAS");
						 }
						 if(validation.std_nomclatureTMSLength(std_nomclature) == false){
						 		model.put("msg",validation.std_nomclatureTMSMSG);
								return new ModelAndView("redirect:add_act_noRPAS");
							}
						 Date date = new Date();
						 BigInteger act = new BigInteger(request.getParameter("act_main_id") + request.getParameter("make_no") + request.getParameter("model_id"));
						 if(act.equals("")) {
							 model.put("msg","Please Select Proper ACT No.");
							 return new ModelAndView("redirect:add_act_noRPAS");	 
						 }
						 
						 int max_take_off_weight =Integer.parseInt(request.getParameter("take_off_weight")) ;
						 int max_payload_weight =Integer.parseInt(request.getParameter("payload_weight"));
						 
						 String fuel_capacity = request.getParameter("fuel_capacity");
						 String type_of_fuel = request.getParameter("type_fuel");
						
						 
						 rs.setCreation_by(username);
						 rs.setCreation_date(date);
						 rs.setAct(act);
						 rs.setStd_nomclature(std_nomclature);
						 rs.setFuel_tank_capacity(fuel_capacity);
						 rs.setType_of_fuel(type_of_fuel);
						 rs.setMax_payload_weight(max_payload_weight);
						 rs.setMax_take_off_weight(max_take_off_weight);
						
						 
						 

						 sessionHql.beginTransaction();
						 
						 if(addactDAO.ifExistRPASActNo(act) != false) {
							 model.put("msg", "ACT No Already Exist.");
							 return new ModelAndView("redirect:add_act_noRPAS");
						 }else {
							 sessionHql.save(rs);
							 sessionHql.getTransaction().commit();
							
					         model.put("msg", "ACT No Created Successfully.");		 
							 return new ModelAndView("redirect:add_act_noRPAS");	 
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
		 
			// Added By Mitesh(21-11-2024)
				public @ResponseBody List<TB_AVIATION_RPAS_ACT_MASTER> getaircrafttypetRPASList(HttpSession s1) {
					int userid = Integer.parseInt(s1.getAttribute("userId").toString());

					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = session.createQuery("select distinct std_nomclature from TB_AVIATION_RPAS_ACT_MASTER order by std_nomclature asc");
					@SuppressWarnings("unchecked")
					List<TB_AVIATION_RPAS_ACT_MASTER> list = (List<TB_AVIATION_RPAS_ACT_MASTER>) q.list();
					tx.commit();
					session.close();
					return list;
				}
				
				// Added By Mitesh(21-11-2024)
				public @ResponseBody List<TB_AVIATION_ACT_MASTER> getaircrafttypetCHTLList(HttpSession s1) {
					int userid = Integer.parseInt(s1.getAttribute("userId").toString());

					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = session.createQuery("select distinct std_nomclature from TB_AVIATION_ACT_MASTER where std_nomclature IN('CHEETAH','CHEETAL','CHETAK') ");
					@SuppressWarnings("unchecked")
					List<TB_AVIATION_ACT_MASTER> list = (List<TB_AVIATION_ACT_MASTER>) q.list();
					tx.commit();
					session.close();
					return list;
				}
				
				@RequestMapping(value = "/getRPASActNomenList", method = RequestMethod.POST)
				public @ResponseBody List<String> getRPASActNomenListRPAS(String actMain,HttpSession sessionUserId) {
					int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = session.createQuery("select act_nomen from TB_AVIATION_ACT_MAIN_MASTER where type_of_aircraft='RPAS' and upper(act_nomen) LIKE :actMain order by act_nomen").setMaxResults(10);
					q.setParameter("actMain",'%'+actMain.toUpperCase()+'%');
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
				
				// Added By Mitesh(03-02-2025)
				public @ResponseBody List<TB_AVIATION_ACT_MASTER> getaircrafttypetList1(HttpSession s1) {
					int userid = Integer.parseInt(s1.getAttribute("userId").toString());

					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q = session.createQuery("select distinct std_nomclature from TB_AVIATION_ACT_MASTER");
					@SuppressWarnings("unchecked")
					List<TB_AVIATION_ACT_MASTER> list = (List<TB_AVIATION_ACT_MASTER>) q.list();
					tx.commit();
					session.close();
					return list;
				}
					
}
