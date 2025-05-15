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
import com.persistance.util.HibernateUtil;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.mstr_Sho_Fho_Svl_Detail_DAO;
import com.models.mnh.Tb_Med_Surv_Master;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class mstr_Sho_Fho_Svl_Detail_Controller {

	@Autowired
	private mstr_Sho_Fho_Svl_Detail_DAO sfd;
	@Autowired
	private RoleBaseMenuDAO roledao;
	MNH_ValidationController validation = new MNH_ValidationController();
	
	@RequestMapping(value = "/admin/mnh_surv_det", method = RequestMethod.GET)
	public ModelAndView mnh_surv_det(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_surv_det", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("msg", msg);
		
		return new ModelAndView("mnh_surv_detTiles","srvlnc_details_MasterCMD",new Tb_Med_Surv_Master());
	}
	
	//********************Note::Save for Sho_Fho_Svl_Details*****************************************//
	
	@RequestMapping(value = "/srvlnc_details_MasterAct" , method = RequestMethod.POST)
	public ModelAndView srvlnc_details_MasterAct(@ModelAttribute("srvlnc_details_MasterCMD") Tb_Med_Surv_Master sf,
                HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
               	
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mnh_surv_det", roleid);
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg1 = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				if(request.getParameter("target_diseases") == null || request.getParameter("target_diseases").equals(""))
				{
					model.put("msg", "Please Enter Target Diseases.");
					return new ModelAndView("redirect:mnh_surv_det");
				}				
				
				if(validation.DiseasemmrLength(request.getParameter("target_diseases")) == false){
			 		model.put("msg",validation.target_diseasesMSG);
					return new ModelAndView("redirect:mnh_surv_det");
				}
				if(validation.DiseasemmrLength(request.getParameter("target_diseases_sub")) == false){
			 		model.put("msg",validation.target_diseases_subMSG);
					return new ModelAndView("redirect:mnh_surv_det");
				}
		/*		if(validation.DiseasemmrLength(request.getParameter("investigation")) == false){
			 		model.put("msg",validation.investigationMSG);
					return new ModelAndView("redirect:mnh_surv_det");
				}
				*/
				int id = sf.getId() > 0 ? sf.getId() : 0;					
                Date date = new Date();
                String username = session.getAttribute("username").toString();
                String target_diseases = request.getParameter("target_diseases");
                String target_diseases_sub = request.getParameter("target_diseases_sub");
                String investigation = request.getParameter("investigation");
             

       		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		 Transaction tx = sessionHQL.beginTransaction();
                
                try{
                	Query q0 = sessionHQL.createQuery("select count(id) from Tb_Med_Surv_Master where upper(target_diseases)=:target_diseases and upper(target_diseases_sub)=:target_diseases_sub and upper(investigation)=:investigation and id !=:id");   
                		q0.setParameter("target_diseases", target_diseases.toUpperCase());  
                		q0.setParameter("target_diseases_sub", target_diseases_sub.toUpperCase()); 
                        q0.setParameter("investigation", investigation.toUpperCase()); 
                        
                        q0.setParameter("id", id);  
                        Long c = (Long) q0.uniqueResult();
                        if (id == 0) {
                        	 sf.setCreated_by(username);
                        	 sf.setCreated_on(date);
                                if (c.equals(0) || c == 0) {
                                       
                                    	sessionHQL.save(sf);
                    					sessionHQL.flush();
                    					sessionHQL.clear();
                                        model.put("msg", "Data Saved Successfully.");

                                } else {
                                        model.put("msg", "Data already Exist.");
                                }
                        }
                         //UPDATION OF LOCATION MASTER 
                        else {
                        	 sf.setModify_by(username);
                        	 sf.setModify_on(date);
                                if (c.equals(0) || c == 0) {
                                        String msg = sfd.updateSho_Fho(sf);
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
        		return new ModelAndView("redirect:mnh_surv_det");
        	}
	
	//********************Note::Search for Sho_Fho_Svl_Details*****************************************//
	
	@RequestMapping(value = "/admin/surv_detaillist", method = RequestMethod.POST)
 	public ModelAndView surv_detaillist(ModelMap Mmap,HttpSession session, HttpServletRequest request,
               @RequestParam(value = "msg", required = false) String msg,
    			@RequestParam(value = "target_diseases1", required = false) String target_diseases,
    			@RequestParam(value = "target_diseases_sub1", required = false) String target_diseases_sub,
    			@RequestParam(value = "investigation1", required = false) String investigation){
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("mnh_surv_det", roleid);
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
            		if(!target_diseases.equals("")) {
            			Mmap.put("target_diseases1", target_diseases);
            		}
            		if(!target_diseases_sub.equals("")) {
            			Mmap.put("target_diseases_sub1", target_diseases_sub);
            		}
            		if(!investigation.equals("")) {
            			Mmap.put("investigation1", investigation);
            		}
            		           		
            		ArrayList<ArrayList<String>> list =sfd.search_surv_detaillist(target_diseases,target_diseases_sub,investigation);
            		if(list.size() == 0)
            		{
            			Mmap.put("msg", "Data Not Available.");
            		}
            		else
            		{
            			Mmap.put("list", list);
            		}
            		return new ModelAndView("mnh_surv_detTiles","srvlnc_details_MasterCMD",new Tb_Med_Surv_Master());
			}
	
	   //********************Note::Delete for Sho_Fho_Svl_Details*****************************************//
	
			@RequestMapping(value = "/deleteSho_Fho_Svl_Details" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView deleteSho_Fho_Svl_Details(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
				String roleid = sessionA.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mnh_surv_det", roleid);
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
							 
							String hqlUpdate = "delete from Tb_Med_Surv_Master where id=:id";
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
						return new ModelAndView("redirect:mnh_surv_det");
					}
			
}
