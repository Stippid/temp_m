package com.controller.mnh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.MNH_ValidationController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.mstr_Icd_codeDAOImpl;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.mnh.Tb_Med_Disease_Cause;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_Icd_codeController {

	@Autowired 
	private mstr_Icd_codeDAOImpl ic;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	int flag = 0;
	String icd_code = "";
	String icd_description="";
	
	@RequestMapping(value = "/mnh_icd_code", method = RequestMethod.GET)
	public ModelAndView mnh_master_icd_code(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	     Boolean val = roledao.ScreenRedirect("mnh_icd_code", session.getAttribute("roleid").toString());

	     if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

         if(request.getHeader("Referer") == null ) {
                 msg = "";
                return new ModelAndView("redirect:bodyParameterNotAllow");
        }
       
		Mmap.put("msg", msg);
		Mmap.put("flag", flag);
		Mmap.put("icd_code1", icd_code);
		Mmap.put("icd_description1", icd_description);
	
		return new ModelAndView("mnh_icd_codeTiles");
	}	
	
	
	//search report
	@RequestMapping(value = "/admin/icd_codeRpt" , method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> icd_codeRpt(@DatatablesParams DatatablesCriterias criterias,HttpSession session, HttpServletRequest request) {
		if(flag == 0) {
			return null;
		}else {
			DataSet<Map<String, Object>> dataSet = ic.DatatablesCriteriasicdcode(criterias,icd_code,icd_description);
			return DatatablesResponse.build(dataSet, criterias);
		}
	}
	
	@RequestMapping(value = "/Search_mnh_icd_code", method = RequestMethod.POST)
	public ModelAndView Search_mnh_icd_code(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			String icd_code1,String icd_description1) {
		Boolean val = roledao.ScreenRedirect("mnh_icd_code", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		flag = 1;
		icd_code = icd_code1;
		icd_description = icd_description1;
		return new ModelAndView("redirect:mnh_icd_code");
	}
	
	
	// *******************Note::save icd code*****************************************//
	@RequestMapping(value = "/ICD_MasterAction",method = RequestMethod.POST)// Save disease data
    public ModelAndView ICD_MasterAction(@ModelAttribute("ICD_MasterFormCMD") Tb_Med_Disease_Cause l,
                    HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
            
            Boolean val = roledao.ScreenRedirect("mnh_icd_code", session.getAttribute("roleid").toString());
            if(val == false) {
                    return new ModelAndView("AccessTiles");
            }
          if(request.getHeader("Referer") == null ) {
                    msg = "";
                    return new ModelAndView("redirect:bodyParameterNotAllow");
            }
            
            String username = session.getAttribute("username").toString();
            String status = request.getParameter("status");
            int id = l.getId() > 0 ? l.getId() : 0;	
    		Date date = new Date();
    		String icd_code = request.getParameter("icd_code");
 	    	String icd_description = request.getParameter("icd_description");
 	    	String disease_mmr = request.getParameter("disease_mmr");
 	    	String disease_aso = request.getParameter("disease_aso");
 	    	String disease_principal = request.getParameter("disease_principal");
 	    	String disease_type = request.getParameter("disease_type");
 	    	String block_description = request.getParameter("block_description");
 	    	String disease_subtype = request.getParameter("disease_subtype");
 	    	String disease_family = request.getParameter("disease_family");
 	    	String disease_children = request.getParameter("disease_children");
 	    	String disease_cr_type = request.getParameter("disease_cr_type");
 	    	String disease_cr_block_description = request.getParameter("disease_cr_block_description");
 	    	String short_form = request.getParameter("short_form");
 	    	String short_desc = request.getParameter("short_desc");
 	    	String type = request.getParameter("type");
 			
 				if( icd_code == null || icd_code.equals("")){ 
 					model.put("msg", "Please enter ICD code");
 					return new ModelAndView("redirect:mnh_icd_code");
 			    }
 				 if(validation.RankCodeLength(icd_code) == false){
 			 		model.put("msg",validation.icd_codeMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				if( icd_description == null || icd_description.equals("")){  
 					model.put("msg", "Please enter ICD description");
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.IcdDescriptionLength(icd_description) == false){
 			 		model.put("msg",validation.icd_descriptionMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 		/*	  if(validation.DiseasemmrLength(disease_mmr) == false){
 			 		model.put("msg",validation.disease_mmrMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 			  if(validation.DiseasemmrLength(disease_aso) == false){
 			 		model.put("msg",validation.disease_asoMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 			  if(validation.IcdDescriptionLength(disease_principal) == false){
 			 		model.put("msg",validation.disease_principalMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.IcdDescriptionLength(disease_type) == false){
 			 		model.put("msg",validation.disease_typeMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				if(validation.IcdDescriptionLength(block_description) == false){
 			 		model.put("msg",validation.block_descriptionMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.IcdDescriptionLength(disease_subtype) == false){
 			 		model.put("msg",validation.disease_subtypeMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.DiseaseFamilyLength(disease_family) == false){
 			 		model.put("msg",validation.disease_familyMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.DiseaseFamilyLength(disease_children) == false){
 			 		model.put("msg",validation.disease_childrenMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.IcdDescriptionLength(disease_cr_type) == false){
 			 		model.put("msg",validation.disease_cr_typeMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.IcdDescriptionLength(disease_cr_block_description) == false){
 			 		model.put("msg",validation.disease_cr_block_descriptionMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.DiseasemmrLength(short_form) == false){
 			 		model.put("msg",validation.short_formMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 				 if(validation.MessageLength(short_desc) == false){
 			 		model.put("msg",validation.short_descMSG);
 					return new ModelAndView("redirect:mnh_icd_code");
 				}
 			*/
 			Session session1 = HibernateUtil.getSessionFactory().openSession();
 			Transaction tx = session1.beginTransaction();
 			
 			try {
 			
 				Long c= ic.checkExitstingIcdCode(icd_code,id);
 		   	  
 				if(id == 0 ) {
 				l.setCreated_on(new Date());
 				l.setRecord_valid_from(new Date());
 				l.setCreated_by(username);
 				l.setPrinciple_dc_flag("Y");
 				l.setNotifiable_dc_flag("Y");
 				l.setCurrent_record_flag("Y");
 				
 				if (c == 0) {
					session1.save(l);
					tx.commit();
					model.put("msg", "Data Saved Successfully.");
				} 
 				if (c > 0) 
 					
 				{
 					model.put("msg", "Data already Exist.");
				}
 				}
 				else {
 					l.setModified_by(username);
 					l.setModified_on(date);
 					if (c == 0) {
 					
 						model.put("msg", ic.updateicd_code(l));
 					} else {
 						model.put("msg", "Data already Exist.");
 					}
 				}
 			
 		 
 		   	 model.put("icd_code1", icd_code);
    		    
 			}catch(RuntimeException e){
 				try{
 					tx.rollback();
 					model.put("msg", "roll back transaction");
 				}catch(RuntimeException rbe){
 					model.put("msg", "Couldn’t roll back transaction " + rbe);
 				}
 				throw e;
 			}finally{
 				if(session1!=null){
 					session1.close();
 				}
 			}
 		   		
    		     return new ModelAndView("redirect:mnh_icd_code");
            }
	// *******************Note::END*****************************************//
	
	

	// *******************Note::delete icd code*****************************************//
		@RequestMapping(value = "/deleteicd_code" , method = RequestMethod.POST)
		public ModelAndView deleteicd_code(@ModelAttribute("id1") int id,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, ModelMap model, HttpSession session1) {
			
			Boolean val = roledao.ScreenRedirect("mnh_icd_code", session1.getAttribute("roleid").toString());
            if(val == false) {
                    return new ModelAndView("AccessTiles");
            }
          if(request.getHeader("Referer") == null ) {
                    msg = "";
                    return new ModelAndView("redirect:bodyParameterNotAllow");
            }
            
			List<String> liststr = new ArrayList<String>();
			try {
				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from Tb_Med_Disease_Cause where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				session.close();
				if (app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
				model.put("msg",liststr.get(0));
			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:mnh_icd_code");
		}
	
	
	
	
}
