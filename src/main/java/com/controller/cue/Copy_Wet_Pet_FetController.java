package com.controller.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.dao.cue.Wet_Pet_Fet_SuperDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Copy_Wet_Pet_FetController {
	@Autowired
	private RoleBaseMenuDAO roledao ;
	@Autowired
	private Wet_Pet_Fet_SuperDAO wetdao;
	cueContoller M = new cueContoller();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/copy_wetpe_url", method = RequestMethod.GET)
	public ModelAndView copy_wetpe_url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("copy_wetpe_url", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Copy_Wet_Pet_Fet_Tile");			
	}
		
	@RequestMapping(value = "/getWetPetFetByNo", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWetPetFetByNo(String val,HttpSession sessionUserId,String wet_pet_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no is not null and wet_pet=:val and upper(wet_pet_no) like:wet_pet_no order by wet_pet_no").setMaxResults(10) ;
		q.setParameter("val", val);
		q.setParameter("wet_pet_no", wet_pet_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Boolean getwet_pet_fetList(String wet_pet_no) {
		String hql="from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no =:wet_pet_no";
		List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			users = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;
	
}
	
	@SuppressWarnings("unchecked")	
	public Boolean getwet_pet_fetTypeList(String unit_type) {		
		String hql="from CUE_TB_MISO_MMS_WET_PET_MAST_DET where unit_type =:unit_type";
		List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("unit_type", unit_type);
			users = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;	
	}
	

	@SuppressWarnings("unchecked")
	public Boolean getwet_pet_fet_detTypeList(String wet_pet_no) {		
		String hql="from CUE_TB_MISO_MMS_WET_PET_DET where wet_pet_no =:wet_pet_no";
		List<CUE_TB_MISO_MMS_WET_PET_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("wet_pet_no", wet_pet_no);
			users = (List<CUE_TB_MISO_MMS_WET_PET_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
		} 
		if(users.size()>0)
		{
			return true;
		}
		else
			return false;	
	}	
		
	@RequestMapping(value = "/copy_wet_pet_fetAction", method = RequestMethod.POST)
	public ModelAndView copy_wet_pet_fetAction(@ModelAttribute("copy_wet_pet_fetCmd") CUE_TB_MISO_MMS_WET_PET_MAST_DET rs ,HttpServletRequest request,ModelMap model,HttpSession session) throws SQLException {
		int roleid = (Integer)session.getAttribute("roleid");
		String wet_pet_no_copy =request.getParameter("wet_pet_no1");
		String unit_type =request.getParameter("unit_type");
		String unit_type1 = request.getParameter("unit_type1");
		String wet_pet_no =request.getParameter("wet_pet_no");
		  String we_pe=request.getParameter("wet_pet");
		  if(we_pe == ""  || we_pe == null || we_pe == "null" || we_pe.equals(null) )
			{
				model.put("msg", "Please Select WET/PET/FET Radio");
				return new ModelAndView("redirect:copy_wetpe_url");
			}
			 String wet_pet_no1 = request.getParameter("wet_pet_no1");
			 if(wet_pet_no1.equals(""))
			 { 
				 model.put("msg", "Please Enter WET/PET/FET");
				 return new ModelAndView("redirect:copy_wetpe_url");
			 }
			 if(validation.checkWetPetLength(wet_pet_no1)  == false){
			 		model.put("msg",validation.wetpetnoMSG);
					return new ModelAndView("redirect:copy_wetpe_url");
				}
			 if(rs.getWet_pet_no().equals(""))
			 {
				 model.put("msg", "Please Enter Copy WET/PET/FET");
				 return new ModelAndView("redirect:copy_wetpe_url");
			 }
			 if(validation.checkWetPetLength(rs.getWet_pet_no())  == false){
		 		model.put("msg",validation.copywetpetMSG);
				return new ModelAndView("redirect:copy_wetpe_url");
			 }
			 if(rs.getUnit_type().equals(""))
			 {
				 model.put("msg", "Please Enter Unit Type");
				 return new ModelAndView("redirect:copy_wetpe_url");
			 }
			 if(validation.checkWepeLength(rs.getUnit_type())  == false){
			 		model.put("msg",validation.unittypeMSG);
					return new ModelAndView("redirect:copy_wetpe_url");
				 }
			 if(validation.checkRemarksLength(rs.getRemarks())  == false){
			 		model.put("msg",validation.remarksMSG);
					return new ModelAndView("redirect:copy_wetpe_url");
				 }
		  if(getwet_pet_fet_detTypeList(wet_pet_no_copy) == true) {
			  	if(getwet_pet_fetList(wet_pet_no) != false) {
		  			model.put("msg", "WET/PET/FET Number Already exists!");
		  			return new ModelAndView("redirect:copy_wetpe_url");
			  	}
	        	if(getwet_pet_fetTypeList(unit_type) != false) {
		  			model.put("msg", "Unit Type Already exists!");
		  			return new ModelAndView("redirect:copy_wetpe_url");
		        }
	        	wetdao.COPYWetPetFetDetail(wet_pet_no_copy,wet_pet_no,unit_type,roleid);
	        	 model.put("msg", "Data Copy Successfully");
	        	 model.put("wet_pet01", we_pe);
				 model.put("wet_pet_no01", wet_pet_no_copy);
				 model.put("unit_type01", unit_type1);
	        	return new ModelAndView("Copy_Wet_Pet_Fet_Tile");
		  }
		  else{
		  		 model.put("msg", "Data not Exists!");
		  		 model.put("wet_pet01", we_pe);
				 model.put("wet_pet_no01", wet_pet_no_copy);
				 model.put("unit_type01", unit_type1);
		  		 return new ModelAndView("Copy_Wet_Pet_Fet_Tile");
		 	}
	}
		
	@RequestMapping(value = "/getWetPetFetByUnit_type", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> getWetPetFetByUnit_type(String val,HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no=:wet_pet_no");
		q.setParameter("wet_pet_no", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> list = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}
		
}
