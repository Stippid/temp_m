package com.controller.Jco_Master;

import java.text.ParseException;
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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.Jco_Master.Gorkha_Domisile_DAO_JCO;
import com.dao.login.RoleBaseMenuDAO;

import com.model.Jco_Master.TB_PSG_MSTR_GORKHA_DOMISILE_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Gorkha_Domisile_Controller_JCO {

	
Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Gorkha_Domisile_DAO_JCO GDDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Gorkha_Domicile_Url", method = RequestMethod.GET)
	 public ModelAndView Gorkha_Domicile_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("Gorkha_Domicile_Url", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		ArrayList<ArrayList<String>> list = GDDAO.search_Gorkha_Domisile("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("GorkhaDomisileJCOTiles");
	 }
	 
	 
	 @RequestMapping(value = "/Gorkha_DomisileAction",method=RequestMethod.POST)
		public ModelAndView Gorkha_DomisileAction(@ModelAttribute("Gorkha_DomisileCMD") TB_PSG_MSTR_GORKHA_DOMISILE_JCO com,HttpServletRequest request,ModelMap model,HttpSession session) {
			
					 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String domisile = request.getParameter("domisile").trim();
				 
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (domisile.equals("") || domisile.equals("null") || domisile.equals(null)) {
							model.put("msg", "Please Enter Gorkha Domicile");
							return new ModelAndView("redirect:Gorkha_Domicile_Url");
						}
					 if(domisile.length()>50) {
						 model.put("msg", "Gorkha Domicile Should be Maximum 50 Characters.");
							return new ModelAndView("redirect:Gorkha_Domicile_Url");
					 }
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status");
							return new ModelAndView("redirect:Gorkha_Domicile_Url");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_GORKHA_DOMISILE_JCO where domisile=:domisile and status=:status and id !=:id");
					q0.setParameter("domisile", com.getDomisile());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setDomisile(domisile);
						if (c == 0) {
							sessionHQL.save(com);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist");
						}
					}
				
					tx.commit();
				}catch(RuntimeException e){
					e.printStackTrace();
					try{
						tx.rollback();
						model.put("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						model.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Gorkha_Domicile_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/Search_Gorkha_Domisile_Master", method = RequestMethod.POST)
		public ModelAndView Search_Gorkha_Domisile_Master(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "domisile1", required = false) String domisile1 ,@ModelAttribute("status1") String status)  {
		
		 		ArrayList<ArrayList<String>> list = GDDAO.search_Gorkha_Domisile(domisile1, status);
				Mmap.put("domisile1", domisile1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("GorkhaDomisileJCOTiles");
			   
		}
	
				
	 @RequestMapping(value = "/Edit_Gorkha_Domisile")
		public ModelAndView Edit_Gorkha_Domisile(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			
		 TB_PSG_MSTR_GORKHA_DOMISILE_JCO C = GDDAO.getGorkhaDomisileByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Gorkha_DomisileCMD", C);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("EditGorkhaDomisileJCOTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_Gorkha_Domisile_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Gorkha_Domisile_Action(@ModelAttribute("Edit_Gorkha_DomisileCMD") TB_PSG_MSTR_GORKHA_DOMISILE_JCO rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String domisile = request.getParameter("domisile").trim();
			String status = request.getParameter("status");
			
			

			if (domisile.equals("") || domisile.equals("null")|| domisile.equals(null)) {
				TB_PSG_MSTR_GORKHA_DOMISILE_JCO authDetails = GDDAO.getGorkhaDomisileByid(id);
	        	model.put("Edit_Gorkha_DomisileCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Gorkha Domicile ");
				return new ModelAndView("GorkhaDomisileJCOTiles");
			}	
			 if(domisile.length()>50) {
				 TB_PSG_MSTR_GORKHA_DOMISILE_JCO authDetails = GDDAO.getGorkhaDomisileByid(id);
		        model.put("Edit_Gorkha_DomisileCMD", authDetails);
		        model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Gorkha Domicile Should be Maximum 50 Characters.");
				return new ModelAndView("GorkhaDomisileJCOTiles");
			 }
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_GORKHA_DOMISILE_JCO authDetails = GDDAO.getGorkhaDomisileByid(id);
	        	model.put("Edit_Gorkha_DomisileCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status");
				return new ModelAndView("GorkhaDomisileJCOTiles");
			}*/
	    	
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
				 try {
					
					 Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_GORKHA_DOMISILE_JCO where domisile=:domisile and status=:status and id !=:id");
						q0.setParameter("domisile", domisile);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_GORKHA_DOMISILE_JCO set domisile=:domisile,status=:status,modified_by=:modified_by,modified_date=:modified_date"
										+ " where id=:id";
					                                   
					    	  Query query = sessionHQL.createQuery(hql)
					    			  	.setString("domisile",domisile)
					    			  	.setString("status",status)
										.setString("modified_by", username)
										.setDate("modified_date", new Date())
										.setInteger("id",id);
					                    msg = query.executeUpdate() > 0 ? "1" :"0";
					                    tx.commit(); 
					                    
					                    if(msg == "1") {
					                    	 model.put("msg", "Data Updated Successfully.");
					                    }
					                    else {
					                    	model.put("msg", "Data Not Updated.");
					                    }
						}
						else {
							model.put("msg", "Data already Exist.");
						}
					
				  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      model.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		                      model.put("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(sessionHQL!=null){
						sessionHQL.close();
					}
				}
			return new ModelAndView("redirect:Gorkha_Domicile_Url");
		}

		
		@RequestMapping(value = "/delete_Gorkha_Domisile", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Gorkha_Domisile(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
		try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_PSG_MSTR_GORKHA_DOMISILE_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
				 
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
				tx.commit();
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UnSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:Gorkha_Domicile_Url");
		}
}
