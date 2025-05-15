package com.controller.tms;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.FlushMode;
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
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_REPORT_MAIN;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_RELIEF_PROG_HISTORY;
import com.models.TB_TMS_RELIEF_PROG_HISTORY_A;
import com.models.TB_TMS_RELIEF_PROG_HISTORY_C;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class Common_PartialSwapPartXCOntroller {
	@Autowired
	private RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	
	
	@RequestMapping(value = "/partial_swap", method = RequestMethod.GET)
	public ModelAndView partial_swap(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("partial_swapTiles","partial_swapCMD", new TB_TMS_MVCR_PARTA_DTL());
	}
	
	@RequestMapping(value = "/partial_swapAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("partial_swapCMD") TB_TMS_MVCR_PARTA_DTL rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String source_sus_no = request.getParameter("source_sus_no");
		String source_unit_name = request.getParameter("source_unit_name");
		String target_sus_no = request.getParameter("target_sus_no");
		String target_unit_name = request.getParameter("target_unit_name");
		String authority_no = request.getParameter("authority_no");
		String ba_no = request.getParameter("app");
		
		
		if(source_sus_no.equals("") || source_sus_no == "" || source_sus_no == null ||  source_sus_no.equals(null) || source_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Source SUS No.");
			return new ModelAndView("redirect:partial_swap");	
		}
		else if(source_sus_no !="" & source_sus_no !=null & !source_sus_no.equals("") & !source_sus_no.equals(null) & validation.sus_noLength(source_sus_no) == false)
		{
			model.put("msg",validation.source_sus_noMSG);
			return new ModelAndView("redirect:partial_swap");
		}
		else if(target_sus_no.equals("") || target_sus_no == "" || target_sus_no == null ||  target_sus_no.equals(null) || target_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Target SUS No.");
			return new ModelAndView("redirect:partial_swap");	
		}
		else if(target_sus_no !="" & target_sus_no !=null & !target_sus_no.equals("") & !target_sus_no.equals(null) & validation.sus_noLength(target_sus_no) == false)
		{
			model.put("msg",validation.target_sus_noMSG);
			return new ModelAndView("redirect:partial_swap");
		}
		else if(authority_no.equals("") || authority_no == "" || authority_no == null ||  authority_no.equals(null) || authority_no.equals("null"))
		{
			model.put("msg", "Please Enter the Authority No.");
			return new ModelAndView("redirect:partial_swap");	
		}
		else if(authority_no !="" & authority_no !=null & !authority_no.equals("") & !authority_no.equals(null) & validation.authority_noLength(authority_no) == false)
		{
			model.put("msg",validation.authority_noMSG);
			return new ModelAndView("redirect:partial_swap");
		}
		else if(target_unit_name.equals("") || target_unit_name == "" || target_unit_name == null ||  target_unit_name.equals(null) || target_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Target Unit Name.");
			return new ModelAndView("redirect:partial_swap");	
		}
		else if(target_unit_name !="" & target_unit_name !=null & !target_unit_name.equals("") & !target_unit_name.equals(null) & validation.checkUnit_nameLength(target_unit_name) == false){
			model.put("msg",validation.target_unit_nameMSG);
			return new ModelAndView("redirect:partial_swap");
		}
		else if(source_unit_name.equals("") || source_unit_name == "" || source_unit_name == null ||  source_unit_name.equals(null) || source_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Source Unit Name.");
			return new ModelAndView("redirect:partial_swap");	
		}
		else if(source_unit_name !="" & source_unit_name !=null & !source_unit_name.equals("") & !source_unit_name.equals(null) & validation.checkUnit_nameLength(source_unit_name) == false){
			model.put("msg",validation.source_unit_nameMSG);
			return new ModelAndView("redirect:partial_swap");
		}
		else if(ba_no.equals("") || ba_no == "" || ba_no == null ||  ba_no.equals(null) || ba_no.equals("null"))
		{
			model.put("msg", "Please Select the Ba No.");
			return new ModelAndView("redirect:partial_swap");	

		}
		else
		{
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
				
	    		String values[] = ba_no.split(",");
				///------ checking of existence of pair source_sus_no and ba_no------------------------------------------------------------------------------//
				for(int i=0; i<values.length;i++)
				{
					String ba = values[i];
					
					
					if(ba !="" & ba !=null & !ba.equals("") & !ba.equals(null) & validation.ba_noLength(ba) == false){
						model.put("msg",validation.ba_noMSG);
						return new ModelAndView("redirect:partial_swap");
					}
					 
					
					Query qce = null;
					qce = sessionHQL.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.classification,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_MVCR_PARTA_DTL e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.ba_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :ba " );
					qce.setParameter("ba", ba);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list(); 
					sessionHQL.flush();
					sessionHQL.clear();
					if(result.size() == 0) {
						model.put("msg", ba+" does not exits in BA No Directory.");
						return new ModelAndView("redirect:partial_swap");		
					} 
					//----------------------------------------- Check into PARTA main for existing data-----------------------------------------------//
					Query q1 = sessionHQL.createQuery("select count(id) from TB_TMS_MVCR_PARTA_MAIN where sus_no=:target_sus_no");
					q1.setParameter("target_sus_no", target_sus_no);
					Long count2 = (Long)q1.uniqueResult();
					sessionHQL.flush();
					sessionHQL.clear();
					//--------------------------------- Insert Into TB TMS PARTA MAIN ------------------------------------------------------------------------//
					if(count2 == 0)
					{
						TB_TMS_MVCR_PARTA_MAIN er = new TB_TMS_MVCR_PARTA_MAIN();	
						er.setSus_no(target_sus_no);
						er.setCreation_by(username);
						er.setCreation_date(new Date());
						er.setVersion_no(1);
						er.setStatus("1");
						er.setApprove_date(new Date());
						er.setApproved_by(username);
						er.setDate_of_mvcr(new Date());
						er.setDt_of_submsn(new Date());
						sessionHQL.save(er);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					String ba1 = values[i];
					String hqlUpdate = "update TB_TMS_MVCR_PARTA_DTL c set c.sus_no=:target_sus_no,authrty_letter_no=:authrty_letter_no where c.ba_no=:ba_no";
					Query updatedEntities = sessionHQL.createQuery( hqlUpdate );
					updatedEntities.setParameter("ba_no", ba1);
					updatedEntities.setParameter("target_sus_no", target_sus_no);
					updatedEntities.setParameter("authrty_letter_no", authority_no);
					updatedEntities.executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
				//----------------- Insert into TB TMS Relief Program History  -----------------------------------------------//
					TB_TMS_RELIEF_PROG_HISTORY rc = new TB_TMS_RELIEF_PROG_HISTORY();
					rc.setFrom_unit_sus_no(source_sus_no);
					rc.setTo_unit_sus_no(target_sus_no);
					rc.setApprove_date(new Date());
					rc.setDepot_name(target_unit_name);
					rc.setBa_no(ba);
					rc.setAuth(authority_no);
					rc.setCreated_by(username);
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					sessionHQL.save(rc);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				tx.commit();
				model.put("msg", "Transferred Successfully.");
			}
			catch(Exception e)
			{
				try{
	    			tx.rollback();
	    			model.put("msg", "roll back transaction");
	    		}catch(RuntimeException rbe){
	    			model.put("msg", "Couldn’t roll back transaction " + rbe);
	    		}
	    		throw e;
	    	}finally{
	    		if(sessionHQL!=null){
	    			sessionHQL.close();
	    		}
	    	}
			return new ModelAndView("redirect:partial_swap");		
		 }	
	}
	
	// Part X order
	@RequestMapping(value = "/Part_X_Order", method = RequestMethod.GET)
	public ModelAndView Part_X_Order(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Part_X_Order", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Part_X_OrderTiles","Part_X_OrderCMD", new TB_TMS_CENSUS_RETURN());
	}
	
	@RequestMapping(value = "/Part_X_OrderAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("Part_X_OrderCMD") TB_TMS_CENSUS_RETURN st,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Part_X_Order", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String ba_no = request.getParameter("app");
		String source_sus_no = request.getParameter("source_sus_no");
		String target_sus_no = request.getParameter("target_sus_no");
		String target_unit_name = request.getParameter("target_unit_name");
		String authority_no = request.getParameter("authority_no");
		String source_unit_name = request.getParameter("source_unit_name");
		
		if(source_sus_no.equals("") || source_sus_no == "" || source_sus_no == null ||  source_sus_no.equals(null) || source_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Source SUS No.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		else if(source_sus_no !="" & source_sus_no !=null & !source_sus_no.equals("") & !source_sus_no.equals(null) & validation.sus_noLength(source_sus_no) == false)
		{
			model.put("msg",validation.source_sus_noMSG);
			return new ModelAndView("redirect:Part_X_Order");
		}
		else if(target_sus_no.equals("") || target_sus_no == "" || target_sus_no == null ||  target_sus_no.equals(null) || target_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Target SUS No.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		else if(target_sus_no !="" & target_sus_no !=null & !target_sus_no.equals("") & !target_sus_no.equals(null) & validation.sus_noLength(target_sus_no) == false)
		{
			model.put("msg",validation.target_sus_noMSG);
			return new ModelAndView("redirect:Part_X_Order");
		}
		else if(authority_no.equals("") || authority_no == "" || authority_no == null ||  authority_no.equals(null) || authority_no.equals("null"))
		{
			model.put("msg", "Please Enter the Authority No.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		else if(authority_no !="" & authority_no !=null & !authority_no.equals("") & !authority_no.equals(null) & validation.authority_noLength(authority_no) == false)
		{
			model.put("msg",validation.authority_noMSG);
			return new ModelAndView("redirect:Part_X_Order");
		}
		else if(target_unit_name.equals("") || target_unit_name == "" || target_unit_name == null ||  target_unit_name.equals(null) || target_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Target Unit Name.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		else if(target_unit_name !="" & target_unit_name !=null & !target_unit_name.equals("") & !target_unit_name.equals(null) & validation.checkUnit_nameLength(target_unit_name) == false){
			model.put("msg",validation.target_unit_nameMSG);
			return new ModelAndView("redirect:Part_X_Order");
		}
		else if(source_unit_name.equals("") || source_unit_name == "" || source_unit_name == null ||  source_unit_name.equals(null) || source_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Source Unit Name.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		else if(source_unit_name !="" & source_unit_name !=null & !source_unit_name.equals("") & !source_unit_name.equals(null) & validation.checkUnit_nameLength(source_unit_name) == false){
			model.put("msg",validation.source_unit_nameMSG);
			return new ModelAndView("redirect:Part_X_Order");
		}
		else if(ba_no.equals("") || ba_no == "" || ba_no == null ||  ba_no.equals(null) || ba_no.equals("null"))
		{
			model.put("msg", "Please Enter the Ba No.");
			return new ModelAndView("redirect:Part_X_Order");	
		}
		
		else
		{
			 Session session18 = HibernateUtil.getSessionFactory().openSession();
			 Session session4 = HibernateUtil.getSessionFactory().openSession();
			 Session sessionce= HibernateUtilNA.getSessionFactory().openSession();
			 Session sessionRD = HibernateUtil.getSessionFactory().openSession();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				String values[] = ba_no.split(",");
				Transaction tx18 = session18.beginTransaction();	
				///------ checking of existence of pair source_sus_no and ba_no------------------------------------------------------------------------------//
				for(int i=0; i<values.length;i++)
				{
					String ba = values[i];
					
					 if(ba !="" & ba !=null & !ba.equals("") & !ba.equals(null) & validation.ba_noLength(ba) == false){
						model.put("msg",validation.ba_noMSG);
						return new ModelAndView("redirect:Part_X_Order");
					}
					
					sessionce.setFlushMode(FlushMode.ALWAYS);
					Transaction txce = sessionce.beginTransaction();
					Query qce = null;
					qce = sessionce.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.vehcl_classfctn,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_CENSUS_RETURN e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.ba_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :ba " );
					qce.setParameter("ba", ba);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list(); 
					if(result.size() == 0) {
						model.put("msg", ba+" does not exits in BA No Directory.");
						return new ModelAndView("redirect:Part_X_Order");		
					} 
					//----------------------------------------- Check into EMAR main for existing data-----------------------------------------------//
					Query q1 = session18.createQuery("select count(id) from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:target_sus_no");
					q1.setParameter("target_sus_no", target_sus_no);
					Long count2 = (Long)q1.uniqueResult();
					//--------------------------------- Insert Into TB TMS EMAR REPORT MAIN ------------------------------------------------------------------------//
					if(count2 == 0)
					{
						TB_TMS_CENSUS_RETURN_MAIN er = new TB_TMS_CENSUS_RETURN_MAIN();	
						er.setSus_no(target_sus_no);
						er.setCreation_by(username);
						er.setCreation_date(new Date());
						er.setVersion_no(1);
						er.setStatus("1");
						er.setApprove_date(new Date());
						er.setApproved_by(username);
						er.setDate_of_cens_retrn(new Date());
						er.setDt_of_submsn(new Date());
						sessionHQL.beginTransaction();
						sessionHQL.save(er);
						sessionHQL.getTransaction().commit();
					}
					String ba1 = values[i];
					String hqlUpdate = "update TB_TMS_CENSUS_RETURN c set c.sus_no=:target_sus_no where c.ba_no=:ba_no";
					Transaction tx4 = session4.beginTransaction();
					Query updatedEntities = session4.createQuery( hqlUpdate );
					updatedEntities.setParameter("ba_no", ba1);
					updatedEntities.setParameter("target_sus_no", target_sus_no);
					updatedEntities.executeUpdate();
					tx4.commit();
				//----------------- Insert into TB TMS Relief Program History A -----------------------------------------------//
					TB_TMS_RELIEF_PROG_HISTORY_A rc = new TB_TMS_RELIEF_PROG_HISTORY_A();
					sessionRD.beginTransaction();
					rc.setFrom_unit_sus_no(source_sus_no);
					rc.setTo_unit_sus_no(target_sus_no);
					rc.setApprove_date(new Date());
					rc.setDepot_name(target_unit_name);
					rc.setBa_no(ba);
					rc.setAuth(authority_no);
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					txce.commit();
					sessionRD.save(rc);
					sessionRD.getTransaction().commit(); 
				}
				tx18.commit();
				model.put("msg", "Transferred Successfully.");
				sessionRD.close();	
				sessionce.close();
				session18.close();
				session4.close();	
				sessionHQL.close();
			return new ModelAndView("redirect:Part_X_Order");		
		 }	
	}
	
	@RequestMapping(value = "/getBANoDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> getBANoDetails(String ba_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select l.engine_no,l.chasis_no,l.veh_cat,(select ba_no from TB_TMS_BANUM_DIRCTRY where old_ba_no=:new_ba_no) as new_ba_no from TB_TMS_BANUM_DIRCTRY l where l.ba_no=:ba_no"); 
		q.setParameter("ba_no", ba_no);
		q.setParameter("new_ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		for(Object[] listObject: list){
	    	String engine_no = (String)listObject[0];
	   		String chasis_no = (String)listObject[1];
	   		String veh_cat = (String)listObject[2];
	   		String new_ba_no = (String)listObject[3];
	   		
		    String base64EncodedEncryptedengine_no = "";
		    if(listObject[0] != null) {
		    	base64EncodedEncryptedengine_no = new String(Base64.encodeBase64(c.doFinal(engine_no.getBytes())));
		    }else {
		    	base64EncodedEncryptedengine_no ="";
		    }
		    String base64EncodedEncryptedchasis_no = "";
		    if(listObject[1] != null) {
		    	base64EncodedEncryptedchasis_no = new String(Base64.encodeBase64(c.doFinal(chasis_no.getBytes())));
		    }else {
		    	base64EncodedEncryptedchasis_no ="";
		    }
		    
		    String base64EncodedEncryptedveh_cat = new String(Base64.encodeBase64(c.doFinal(veh_cat.getBytes())));
		    
		    String base64EncodedEncryptedNew_ba_no = "";
		    if(listObject[3] != null) {
		    	base64EncodedEncryptedNew_ba_no = new String(Base64.encodeBase64(c.doFinal(new_ba_no.getBytes())));
		    }else {
		    	base64EncodedEncryptedNew_ba_no ="";
		    }
		    
		    
	   		List<String> EncList = new ArrayList<String>();
		    EncList.add(base64EncodedEncryptedengine_no);
		    EncList.add(base64EncodedEncryptedchasis_no);
		    EncList.add(base64EncodedEncryptedveh_cat);
		    EncList.add(base64EncodedEncryptedNew_ba_no);
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
	
	@RequestMapping(value = "/getArmyNonArmyDetails", method = RequestMethod.POST)
	public @ResponseBody List<String> getArmyNonArmyDetails(String ba_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select ba_no_type from Tms_Banum_Req_Prnt where parent_req_id in (select parent_req_id from Tms_Banum_Req_Child where ba_no=:ba_no)");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
				String army_non = String.valueOf(list.get(i));
				encCode = c.doFinal(army_non.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
	    if(list.size() != 0) {
	    	FinalList.add(enckey+"0fsjyg==");
	    }
	    return FinalList;
	}

	@RequestMapping(value = "/getSourceSUSNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getSourceSUSNoList(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where upper(sus_no) like :sus_no ").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase()+"%" );
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}

	@RequestMapping(value = "/getSourceUNITNAME", method = RequestMethod.POST)
	public @ResponseBody List<String> getSourceUNITNAME(String unit_name,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name ").setMaxResults(10); 
		q.setParameter("unit_name", unit_name.toUpperCase()+"%" );
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
	
	@RequestMapping(value = "/getSourceUnitNameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getSourceUnitNameList(String source_sus_no,HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:source_sus_no ORDER BY id desc").setMaxResults(1);
		q.setParameter("source_sus_no", source_sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}

	@RequestMapping(value = "/getTargetSUSFromUNITNAME", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSFromUNITNAME(String target_unit_name,HttpSession sessionA) {
		target_unit_name = target_unit_name.replace("&#40;","(");
		target_unit_name = target_unit_name.replace("&#41;",")");
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		
		
		Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:target_unit_name and status_sus_no = 'Active'  ORDER BY id desc");
		q.setParameter("target_unit_name", target_unit_name);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}
	
	@RequestMapping(value = "/getPartialBANoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getPartialBANoList(String source_sus_no,HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap", roleid);	
		if(val == false) {
		    return null;
		}else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select ba_no from TB_TMS_MVCR_PARTA_DTL where sus_no=:source_sus_no ORDER BY SUBSTRING(ba_no, 1,2) ,SUBSTRING(ba_no, 3,1)");
			q.setParameter("source_sus_no", source_sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
		    return list;
		}
	}
	
	@RequestMapping(value = "/getPartialBANoListfromUnit", method = RequestMethod.POST)
	public @ResponseBody List<String> getPartialBANoListfromUnit(String unit_name,HttpSession sessionA) {	
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap", roleid);	
		if(val == false) {
		    return null;
		}else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select ba_no from TB_TMS_MVCR_PARTA_DTL where sus_no in (select sus_no from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name) order by SUBSTRING(ba_no, 3,10) ");
			q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%" );
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	}
	
	@RequestMapping(value = "/getPartialBANoList1", method = RequestMethod.POST)
	public @ResponseBody List<String> getPartialBANoList1(String source_sus_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Part_X_Order", roleid);	
		if(val == false) {
		    return null;
		}else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select ba_no from TB_TMS_CENSUS_RETURN where sus_no=:source_sus_no ORDER BY SUBSTRING(ba_no, 1,2) ,SUBSTRING(ba_no, 3,1)");
			q.setParameter("source_sus_no", source_sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	}
	
	

	@RequestMapping(value = "/getSUSStatus", method = RequestMethod.POST)
	public @ResponseBody Long getSUSStatus(String sus,HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select count(*) from Miso_Orbat_Unt_Dtl where sus_no=:source_sus_no and status_sus_no = 'Inactive'");
		q.setParameter("source_sus_no", sus);
		Long count = (Long)q.uniqueResult();
		tx.commit();
		session.close();
		return count;
	}
	
	@RequestMapping(value = "/getUnitNameStatus", method = RequestMethod.POST)
	public @ResponseBody Long getUnitNameStatus(String unitName,HttpSession sessionA) {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select count(*) from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no = 'Inactive'");
		q.setParameter("unit_name", unitName);
		Long count = (Long)q.uniqueResult();
		tx.commit();
		session.close();
		return count;
	}
	///////////////////////////////////////partial_swap_for_c_veh////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/partial_swap_for_c_veh", method = RequestMethod.GET)
		public ModelAndView partial_swap_for_c_veh(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap_for_c_veh", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("partial_swap_for_c_vehTiles","partial_swap_for_c_vehCMD", new TB_TMS_MVCR_PARTA_DTL());
	}

	@RequestMapping(value = "/getSourceSUSNoList1", method = RequestMethod.POST)
	public @ResponseBody List<String> getSourceSUSNoList1(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct a.sus_no from TB_TMS_EMAR_REPORT a,Miso_Orbat_Unt_Dtl b where a.sus_no = b.sus_no and b.status_sus_no= 'Active' and upper(a.sus_no) like :sus_no").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase()+"%" );
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}

	@RequestMapping(value = "/getPartialBANoListFromUnitname", method = RequestMethod.POST)
	public @ResponseBody List<String> getPartialBANoListFromUnitname(String unit_name,HttpSession sessionA) {	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap_for_c_veh", roleid);	
		if(val == false) {
			return null;
		}else {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select em_no from TB_TMS_EMAR_REPORT where sus_no in (select sus_no from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name) order by SUBSTRING(em_no, 3,10) ");
			q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%" );
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	}
	
	@RequestMapping(value = "/getPartialEMnoList", method = RequestMethod.POST)
		public @ResponseBody List<String> getPartialEM_noList(String source_sus_no,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap_for_c_veh", roleid);	
		if(val == false) {
			return null;
		}else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select em_no from TB_TMS_EMAR_REPORT where sus_no=:source_sus_no ORDER BY SUBSTRING(em_no, 1,2) ,SUBSTRING(em_no, 3,1)");
			q.setParameter("source_sus_no", source_sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	}

	
	@RequestMapping(value = "/partial_swap_for_c_vehAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryFormincveh(@ModelAttribute("partial_swap_for_c_vehCMD") TB_TMS_EMAR_DRR_DIR_DTL rd,
			HttpServletRequest request,ModelMap model,HttpSession session)
	{
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("partial_swap_for_c_veh", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String source_sus_no = request.getParameter("source_sus_no");
	 	String source_unit_name = request.getParameter("source_unit_name");
		String target_sus_no = request.getParameter("target_sus_no");
		String target_unit_name = request.getParameter("target_unit_name");
		String em_no = request.getParameter("app");
		String values[] = em_no.split(",");
		String authority_no = request.getParameter("authority_no");
		
		if(source_sus_no.equals("") || source_sus_no == "" || source_sus_no == null ||  source_sus_no.equals(null) || source_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Source SUS No.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		else if(source_sus_no !="" & source_sus_no !=null & !source_sus_no.equals("") & !source_sus_no.equals(null) & validation.sus_noLength(source_sus_no) == false)
		{
			model.put("msg",validation.source_sus_noMSG);
			return new ModelAndView("redirect:partial_swap_for_c_veh");
		}
		else if(target_sus_no.equals("") || target_sus_no == "" || target_sus_no == null ||  target_sus_no.equals(null) || target_sus_no.equals("null"))
		{
			model.put("msg", "Please Enter the Target SUS No.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		else if(target_sus_no !="" & target_sus_no !=null & !target_sus_no.equals("") & !target_sus_no.equals(null) & validation.sus_noLength(target_sus_no) == false)
		{
			model.put("msg",validation.target_sus_noMSG);
			return new ModelAndView("redirect:partial_swap_for_c_veh");
		}
		else if(authority_no.equals("") || authority_no == "" || authority_no == null ||  authority_no.equals(null) || authority_no.equals("null"))
		{
			model.put("msg", "Please Enter the Authority No.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		else if(authority_no !="" & authority_no !=null & !authority_no.equals("") & !authority_no.equals(null) & validation.authority_noLength(authority_no) == false)
		{
			model.put("msg",validation.authority_noMSG);
			return new ModelAndView("redirect:partial_swap_for_c_veh");
		}
		else if(target_unit_name.equals("") || target_unit_name == "" || target_unit_name == null ||  target_unit_name.equals(null) || target_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Target Unit Name.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		else if(target_unit_name !="" & target_unit_name !=null & !target_unit_name.equals("") & !target_unit_name.equals(null) & validation.checkUnit_nameLength(target_unit_name) == false){
			model.put("msg",validation.target_unit_nameMSG);
			return new ModelAndView("redirect:partial_swap_for_c_veh");
		}
		else if(source_unit_name.equals("") || source_unit_name == "" || source_unit_name == null ||  source_unit_name.equals(null) || source_unit_name.equals("null"))
		{
			model.put("msg", "Please Enter the Source Unit Name.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		else if(source_unit_name !="" & source_unit_name !=null & !source_unit_name.equals("") & !source_unit_name.equals(null) & validation.checkUnit_nameLength(source_unit_name) == false){
			model.put("msg",validation.source_unit_nameMSG);
			return new ModelAndView("redirect:partial_swap_for_c_veh");
		}
		else if(em_no.equals("") || em_no == "" || em_no == null ||  em_no.equals(null) || em_no.equals("null")){
			model.put("msg", "Please Enter the EM No.");
			return new ModelAndView("redirect:partial_swap_for_c_veh");	
		}
		
		 else
		 {
				///------ checking of existence of pair source_sus_no and em_no------------------------------------------------------------------------------//
			 Session session18 = HibernateUtil.getSessionFactory().openSession();
			 Session session4 = HibernateUtil.getSessionFactory().openSession();
			 Session sessionce= HibernateUtilNA.getSessionFactory().openSession();
			 Session sessionRD = HibernateUtil.getSessionFactory().openSession();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			try
			{
				Transaction tx18 = session18.beginTransaction();	
				for(int i=0; i<values.length;i++)
				{
					String em = values[i];
					if(em !="" & em !=null & !em.equals("") & !em.equals(null) & validation.ba_noLength(em) == false){
						model.put("msg",validation.em_noMSG);
						return new ModelAndView("redirect:partial_swap_for_c_veh");
					}
					sessionce.setFlushMode(FlushMode.ALWAYS);
					Transaction txce = sessionce.beginTransaction();
					Query qce = null;
					qce = sessionce.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.classification,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_EMAR_REPORT e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.em_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :em " );
					qce.setParameter("em", em);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list(); 
					
					if(result.size() == 0) {
						model.put("msg", em+" does not exits in BA No Directory.");
						return new ModelAndView("redirect:partial_swap_for_c_veh");		
					} 
					//----------------------------------------- Check into EMAR main for existing data-----------------------------------------------//
					Query q1 = session18.createQuery("select count(id) from TB_TMS_EMAR_REPORT_MAIN where sus_no=:target_sus_no");
					q1.setParameter("target_sus_no", target_sus_no);
					Long count2 = (Long)q1.uniqueResult();
					//--------------------------------- Insert Into TB TMS EMAR REPORT MAIN ------------------------------------------------------------------------//
					if(count2 == 0)
					{
						TB_TMS_EMAR_REPORT_MAIN er = new TB_TMS_EMAR_REPORT_MAIN();	
						er.setSus_no(target_sus_no);
						er.setCreation_by(username);
						er.setCreation_date(new Date());
						er.setVersion_no(1);
						er.setStatus("1");
						er.setApprove_date(new Date());
						er.setApproved_by(username);
						er.setDate_of_mvcr(new Date());
						er.setDt_of_submsn(new Date());
						sessionHQL.beginTransaction();
						sessionHQL.save(er);
						sessionHQL.getTransaction().commit();
					}
					String em1 = values[i];
					String hqlUpdate = "update TB_TMS_EMAR_REPORT c set c.sus_no=:target_sus_no where c.em_no=:em_no";
					Transaction tx4 = session4.beginTransaction();
					Query updatedEntities = session4.createQuery( hqlUpdate );
					updatedEntities.setParameter("em_no", em1);
					updatedEntities.setParameter("target_sus_no", target_sus_no);
					updatedEntities.executeUpdate();
					tx4.commit();
				//----------------- Insert into TB TMS Relief Program History C -----------------------------------------------//
					TB_TMS_RELIEF_PROG_HISTORY_C rc = new TB_TMS_RELIEF_PROG_HISTORY_C();
					sessionRD.beginTransaction();
					rc.setFrom_unit_sus_no(source_sus_no);
					rc.setTo_unit_sus_no(target_sus_no);
					rc.setApprove_date(new Date());
					rc.setDepot_name(target_unit_name);
					rc.setBa_no(em);
					rc.setAuth(authority_no);
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						//rc.setTo_unit_name(target_unit_name);
						//rc.setFrom_unit_name(source_unit_name);
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					txce.commit();
					sessionRD.save(rc);
					sessionRD.getTransaction().commit(); 
				}
				tx18.commit();
				model.put("msg", "Transferred Successfully.");
				
			}
			catch(Exception e)
			{
				sessionRD.getTransaction().rollback();
				sessionHQL.getTransaction().rollback();
			}
			finally
			{
				sessionRD.close();	
				sessionce.close();
				session18.close();
				session4.close();	
				sessionHQL.close();
			}
			return new ModelAndView("redirect:partial_swap_for_c_veh");		
		 }	
	}
// --------------------- end  partial swap for cVeh --------------------------
	
	@RequestMapping(value = "/SUSFromUNITNAMEActive_or_InactiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> SUSFromUNITNAMEActive_or_InactiveList(String target_unit_name,HttpSession sessionA) {
		target_unit_name = target_unit_name.replace("&#40;","(");
		target_unit_name = target_unit_name.replace("&#41;",")");
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:target_unit_name  and UPPER(status_sus_no) in ('INACTIVE','ACTIVE') ORDER BY id desc");
		q.setParameter("target_unit_name", target_unit_name);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}
	@RequestMapping(value = "/SUSFromUNITNAME_InactiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> SUSFromUNITNAME_InactiveList(String target_unit_name,HttpSession sessionA) {
		target_unit_name = target_unit_name.replace("&#40;","(");
		target_unit_name = target_unit_name.replace("&#41;",")");
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:target_unit_name  and UPPER(status_sus_no) = UPPER('INACTIVE') ORDER BY id desc");
		q.setParameter("target_unit_name", target_unit_name);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
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
	    	FinalList.add(enckey+"4bsjyg==");
	    }
	    return FinalList;
	}
	
}
