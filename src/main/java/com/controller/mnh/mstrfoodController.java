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
import com.dao.mnh.mstr_Food_Detail_DAO;
import com.dao.mnh.mstr_Sho_Fho_Svl_Detail_DAO;
import com.models.mnh.Tb_Med_Food_Master;
import com.models.mnh.Tb_Med_Surv_Master;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class mstrfoodController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	MNH_ValidationController validation = new MNH_ValidationController();
	
	@Autowired
	private mstr_Food_Detail_DAO sfd;
	
	@RequestMapping(value = "/admin/mnh_Food_URL", method = RequestMethod.GET)
	public ModelAndView mnh_Food_URL(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
	
		return new ModelAndView("foodTiles");
	}
	@RequestMapping(value = "/foodMasterAct" , method = RequestMethod.POST)
	public ModelAndView foodMasterAct(@ModelAttribute("foodMasterCMD") Tb_Med_Food_Master sf,
                HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
               	
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg1 = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
		
				if(request.getParameter("food") == null || request.getParameter("food").equals(""))
				{
					model.put("msg", "Please Enter food.");
					return new ModelAndView("redirect:mnh_Food_URL");
				}				
				
				if(validation.DiseasemmrLength(request.getParameter("food")) == false){
			 		model.put("msg",validation.target_diseasesMSG);
					return new ModelAndView("redirect:mnh_Food_URL");
				}
				
				
				int id = sf.getId() > 0 ? sf.getId() : 0;					
                Date date = new Date();
                String username = session.getAttribute("username").toString();
                String food = request.getParameter("food");
               

       		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		 Transaction tx = sessionHQL.beginTransaction();
                
                try{
                	Query q0 = sessionHQL.createQuery("select count(id) from Tb_Med_Food_Master where upper(food)=:food  and id !=:id");   
                		q0.setParameter("food", food.toUpperCase());  
                		
                        
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
                                        String msg = sfd.updatefood(sf);
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
        		return new ModelAndView("redirect:mnh_Food_URL");
        	}
	
	//********************Note::Search for Sho_Fho_Svl_Details*****************************************//
	
	@RequestMapping(value = "/admin/fooddetaillist", method = RequestMethod.POST)
 	public ModelAndView fooddetaillist(ModelMap Mmap,HttpSession session,HttpServletRequest request,
               @RequestParam(value = "msg", required = false) String msg,
    			@RequestParam(value = "food1", required = false) String food){
		Boolean val = roledao.ScreenRedirect("mnh_daily_dSurve", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
            		if(!food.equals("")) {
            			Mmap.put("food1", food);
            		}
            		
            		           		
            		ArrayList<ArrayList<String>> list =sfd.search_food_detaillist(food);
            		if(list.size() == 0)
            		{
            			Mmap.put("msg", "Data Not Available.");
            		}
            		else
            		{
            			Mmap.put("list", list);
            		}
            		return new ModelAndView("foodTiles","foodMasterCMD",new Tb_Med_Food_Master());
			}
	
	   //********************Note::Delete for Sho_Fho_Svl_Details*****************************************//
	
			@RequestMapping(value = "/deletefood" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView deletefood(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
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
							 
							String hqlUpdate = "delete from Tb_Med_Food_Master where id=:id";
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
						return new ModelAndView("redirect:mnh_Food_URL");
					}
			
}


