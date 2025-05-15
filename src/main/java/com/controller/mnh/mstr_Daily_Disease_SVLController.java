
package com.controller.mnh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.MNH_ValidationController;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.mstr_Daily_Disease_SVLDao;
import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_Daily_Disease_SVLController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private mstr_Daily_Disease_SVLDao DDS;

	MNH_ValidationController validation = new MNH_ValidationController();
	
	
	@RequestMapping(value = "/admin/mnh_daily_dSurve", method = RequestMethod.GET)
	public ModelAndView mnh_daily_dSurve(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
	
		return new ModelAndView("mnh_DDSurveTiles");
	}
	
	@RequestMapping(value = "/D_Surve_MasterAction", method = RequestMethod.POST)
	public ModelAndView D_Surve_MasterAction(@ModelAttribute("D_Surve_MasterCMD") Tb_Med_Daily_Surv_Disease_Mstr Ds,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg1) {
		
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 if(request.getParameter("disease_name").equals("") ){ 
	        	model.put("msg", "Please Enter Disease Name");
				return new ModelAndView("redirect:mnh_daily_dSurve");
			} 
	     if(validation.DiseaseNameLength(request.getParameter("disease_name")) == false){
		 		model.put("msg",validation.disease_nameMSG);
				return new ModelAndView("redirect:mnh_daily_dSurve");
			}
	     
	     if(request.getParameter("disease_type").equals("") ){ 
	        	model.put("msg", "Please Enter Disease Type");
				return new ModelAndView("redirect:mnh_daily_dSurve");
			} 
	      if(validation.DiseaseFamilyLength(request.getParameter("disease_type")) == false){
		 		model.put("msg",validation.disease_type1MSG);
				return new ModelAndView("redirect:mnh_daily_dSurve");
			}
		 
			int id = Ds.getId() > 0 ? Ds.getId() : 0;			
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			
			 try{
				
				Query q0 = sessionHQL.createQuery("select count(id) from Tb_Med_Daily_Surv_Disease_Mstr where upper(disease_name)=:disease_name and upper(disease_type)=:disease_type and id !=:id");
				q0.setParameter("disease_name", request.getParameter("disease_name").toUpperCase());  
				q0.setParameter("disease_type", request.getParameter("disease_type").toUpperCase());  
				q0.setParameter("id", id);  
				Long c = (Long) q0.uniqueResult();
				
		   	    if (id == 0) {
					Ds.setCreated_by(username);
					Ds.setCreated_on(date);
					if ( c == 0) {
					
						sessionHQL.save(Ds);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				// UPDATION OF 
				else {
					Ds.setModified_by(username);
					Ds.setModified_on(date);
					if ( c == 0) {
						String msg = DDS.update_daily_dSurve(Ds);
						model.put("msg", msg);
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
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:mnh_daily_dSurve");
		}
	

	@RequestMapping(value = "/admin/SearchDailyDiseaseSvl", method = RequestMethod.POST)
 	public ModelAndView SearchDailyDiseaseSvl(ModelMap Mmap,HttpSession session,HttpServletRequest request,
               @RequestParam(value = "msg", required = false) String msg,
    			@RequestParam(value = "disease_name1", required = false) String disease_name,
    			@RequestParam(value = "disease_type1", required = false) String disease_type,
    			@RequestParam(value = "status1", required = false) String status){
		
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("disease_name1", disease_name);
		Mmap.put("disease_type1", disease_type);
		Mmap.put("status1", status);
            		ArrayList<ArrayList<String>> list =DDS.SearchDailyDiseaseSvl(disease_name,disease_type,status);
            		if(list.size() == 0)
            		{
            			Mmap.put("msg", "Data Not Available.");
            		}
            		else
            		{
            			Mmap.put("list", list);
            		}
            	return new ModelAndView("mnh_DDSurveTiles","D_Surve_MasterCMD",new Tb_Med_Daily_Surv_Disease_Mstr());
}
	
	@RequestMapping(value = "/deleteDailyDiseaseSvl" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteDailyDiseaseSvl(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", sessionA.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from Tb_Med_Daily_Surv_Disease_Mstr where id = :id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_daily_dSurve");
	}
}
