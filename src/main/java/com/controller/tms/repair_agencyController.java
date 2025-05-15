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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

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
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import com.dao.tms.TMS_base_work_mstrDAO;
import com.dao.tms.tmsConversionRequestGstoSplVehDAO;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_BASE_WORKSHOP_MASTER;
import com.models.TB_TMS_CONVERT_REQ;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_REPAIR_AGENCY_MASTER;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;
import com.models.Tms_animals_details;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class repair_agencyController {
	
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private TMS_base_work_mstrDAO base_workdao;
	
	ValidationController valid = new ValidationController();

	
	@RequestMapping(value = "/admin/repair_agency_URL", method = RequestMethod.GET)
		public ModelAndView repair_agency_URL(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("repair_agency_URL", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		ArrayList<ArrayList<String>> list = base_workdao.repair_agency_search_REPORT("", "");
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("repair_agency_Tile");
	}
	@RequestMapping(value = "/re_agencyAction", method = RequestMethod.POST)
	public ModelAndView re_agencyAction(@ModelAttribute("re_agencyCMD") TB_TMS_REPAIR_AGENCY_MASTER mctmain,HttpServletRequest request,ModelMap model,HttpSession session) {	
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("repair_agency_URL", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		String sermct_main_nomen = request.getParameter("sermct_main_nomen").toString();
		String mct_main_id = request.getParameter("mct_main_id").toString();
		String re_agency = request.getParameter("re_agency").toString();
		String username = session.getAttribute("username").toString();
		int id = mctmain.getId() > 0 ? mctmain.getId(): 0;
		Date date = new Date(); 
		mctmain.setCreated_by(username);
		mctmain.setCreated_on(date);
	
			Session session1=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			if (request.getParameter("sermct_main_nomen") == null || request.getParameter("sermct_main_nomen").trim().equals("")) {
				model.put("msg", "Please Enter  MCT  (4 Digit)");
				return new ModelAndView("redirect:repair_agency_URL");
			}
			
			 
	  	
	  	  
	  	if (request.getParameter("re_agency") == null || request.getParameter("re_agency").trim().equals("")) {
	  		model.put("msg", "Please Enter Repair Agency ");
			return new ModelAndView("redirect:repair_agency_URL");
		}
		

  	  
  	   try
			{
				Query q0 = session1.createQuery("select count(id) from TB_TMS_REPAIR_AGENCY_MASTER where mct=:mct and repair=:repair AND id!=:id");
				q0.setParameter("mct", mct_main_id);  
				q0.setParameter("repair", re_agency);  
				 
				q0.setParameter("id", id);  
				
				Long c = (Long) q0.uniqueResult();
				
				if (id == 0) {
					mctmain.setCreated_by(username);
					mctmain.setCreated_on(new Date());
					mctmain.setMct(mct_main_id);
					mctmain.setRepair(re_agency);
					
					if (c == 0) {
						session1.save(mctmain);
						session1.flush();
						session1.clear();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
			
				tx.commit();
				}catch(RuntimeException e){
				try{
					tx.rollback();
					model.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					model.put("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(session1!=null){
				   session1.close();
				}
			}
			return new ModelAndView("redirect:repair_agency_URL");
		}
	
	
	
	@RequestMapping(value = "/repair_agency_search_REPORT", method = RequestMethod.POST)
	public ModelAndView repair_agency_search_REPORT(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sermct_main_nomen1", required = false) String sermct_main_nomen,
			@RequestParam(value = "re_agency1", required = false) String re_agency,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("repair_agency_URL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getParameter("sermct_main_nomen") == null || request.getParameter("sermct_main_nomen").trim().equals("")) {
			Mmap.put("msg", "Please Enter  MCT  (4 Digit)");
			return new ModelAndView("redirect:repair_agency_URL");
		}
		
		 
  	
  	  
  	if (request.getParameter("re_agency") == null || request.getParameter("re_agency").trim().equals("")) {
  		Mmap.put("msg", "Please Enter Repair Agency ");
		return new ModelAndView("redirect:repair_agency_URL");
	}
		ArrayList<ArrayList<String>> list = base_workdao.repair_agency_search_REPORT(sermct_main_nomen, re_agency);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("sermct_main_nomen1", re_agency);
		Mmap.put("re_agency1", re_agency);

		return new ModelAndView("repair_agency_Tile");
	}
	
	
	
	@RequestMapping(value = "/admin/repair_DeleteURL", method = RequestMethod.POST)
	public ModelAndView repair_DeleteURL(@ModelAttribute("cdeleteid") String cdeleteid, HttpSession sessionURL,
			String cissue_condition_delete, String cemno_delete, String csus_no_delete, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionURL.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("repair_agency_URL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionURL.getAttribute("roleType").toString();
	

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from TB_TMS_REPAIR_AGENCY_MASTER where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", Integer.parseInt(cdeleteid));
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			model.put("msg", "Deleted Successfully.");
		} else {
			model.put("msg", " Delete not Successfully");
		}
		return new ModelAndView("redirect:repair_agency_URL");
	}

	
	@RequestMapping(value = "/repair_agency_updateURL")
	public ModelAndView repair_agency_updateURL(
			@ModelAttribute("cupdateid") Integer updateid,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("repair_agency_URL", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			TB_TMS_REPAIR_AGENCY_MASTER authDetails = base_workdao.getrepairByid(updateid);
		 Mmap.put("edit_repairCMD", authDetails);
		
		Mmap.put("msg", msg);
		
		return new ModelAndView("edit_repair_agency_Tile");
	}
	
	@RequestMapping(value = "/edit_repairAction", method = RequestMethod.POST)
	public ModelAndView edit_repairAction(@ModelAttribute("edit_repairCMD") TB_TMS_REPAIR_AGENCY_MASTER BAN,BindingResult result,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		 Boolean val = roledao.ScreenRedirect("base_workshop_URL", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		String username = session.getAttribute("username").toString();	
		Integer id =Integer.parseInt(request.getParameter("id"));
		

		String sermct_main_nomen = request.getParameter("sermct_main_nomen").toString();
		String mct_main_id = request.getParameter("mct_main_id").toString();
		String re_agency = request.getParameter("re_agency").toString();
 		
	
		
		
		Mmap.put("id2",id);
		
		if (request.getParameter("sermct_main_nomen") == null || request.getParameter("sermct_main_nomen").trim().equals("")) {
			Mmap.put("msg", "Please Enter  MCT  (4 Digit)");
			return new ModelAndView("redirect:repair_agency_URL");
		}
		
		 
  	
  	  
  	if (request.getParameter("re_agency") == null || request.getParameter("re_agency").trim().equals("")) {
  		Mmap.put("msg", "Please Enter Repair Agency ");
		return new ModelAndView("redirect:repair_agency_URL");
	}
		
	try {		
		//Boolean d = SLDAO.getPersonnelNoAlreadyExist(persnl_no);
				 BAN.setModified_by(username);
				 BAN.setModified_on(new Date());
				
				 BAN.setMct(mct_main_id);
				 BAN.setRepair(re_agency);
				 Query q0 = session1.createQuery("select count(id) from TB_TMS_REPAIR_AGENCY_MASTER where mct=:mct and repair=:repair AND id!=:id");
					q0.setParameter("mct", mct_main_id);  
					q0.setParameter("repair", re_agency);  
					 
					q0.setParameter("id", id);  
					
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
				 
								
					 String msg1= base_workdao.GetUpdaterepairshop(BAN,username);
					
					 if(msg1== "Data Updated Successfully.") {
						 Mmap.put("msg", "Data Updated Successfully.");
					 }else {
						 Mmap.put("msg", "Data Not Updated111aa.");
					 }
						}
						else {
							Mmap.put("msg", "Data already Exist.");
						}
					
				
			  }catch(RuntimeException e){
	              try{
	                      tx.rollback();
	                      Mmap.put("msg", "roll back transaction");
	              }catch(RuntimeException rbe){
	            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
	              }
	              throw e;
	             
			}finally{
				if(session1!=null){
					session1.close();
				}
			}
		return new ModelAndView("redirect:repair_agency_URL");
	}
	
	
}




