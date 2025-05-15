package com.controller.psg.Master;

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

import com.models.psg.Master.TB_COUNTRY;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.CountryDao;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Country_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	CountryDao Country_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// -------------------------------Country For page Open -------------------------------------//
	
	 @RequestMapping(value = "/admin/Country", method = RequestMethod.GET)
	 public ModelAndView Country(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		
		 ArrayList<ArrayList<String>> list = Country_dao.search_Country_name("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("CountryTiles");
	 }
	 
	// -------------------------------Country save -------------------------------------//
	 
	 @RequestMapping(value = "/CountryAction",method=RequestMethod.POST)
		public ModelAndView CountryAction(@ModelAttribute("CountryCMD") TB_COUNTRY com,
				HttpServletRequest request,ModelMap model,HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 	 
				int Id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String name = request.getParameter("name").trim();
				
				
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				 if (name.equals("") || name.equals("null")|| name.equals(null)) {
					 model.put("msg", "Please Enter Colour Name");
					 return new ModelAndView("redirect:Country");
					}
				 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Country");
					}
				
				try{
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_COUNTRY where name=:name and status=:status and Id !=:Id");
					q0.setParameter("name", com.getName());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("Id", Id);  
					Long c = (Long) q0.uniqueResult();

					if (Id == 0) {
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setName(name);
						if (c == 0) {
							sessionHQL.save(com);
							sessionHQL.flush();
							sessionHQL.clear();
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
						model.put("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Country");
			}
	 
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
	 @RequestMapping(value = "/admin/getSearch_Country_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Country_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "Country_name1", required = false) String Country_name1 ,String Country_name,@ModelAttribute("status1") String status)  {
		
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 		ArrayList<ArrayList<String>> list = Country_dao.search_Country_name(Country_name1,status);
				Mmap.put("Country_name1", Country_name1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("CountryTiles","CountryCMD",new TB_COUNTRY());
			   
		}
	
				// -------------------------Edit Country For page Open -------------------------------------	
				
	 @RequestMapping(value = "/Edit_Country",method=RequestMethod.POST)
		public ModelAndView Edit_Country(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			
		 
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 		TB_COUNTRY countryDetails = Country_dao.getCountryByid(Integer.parseInt(updateid));
				Mmap.put("Edit_CountryCMD", countryDetails);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_CountryTiles");
		}
		
	// -------------------------Edit Country Action -------------------------------------
	 
		@RequestMapping(value = "/Edit_Country_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Country_Action(@ModelAttribute("Edit_CountryCMD") TB_COUNTRY rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Country", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			
			
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name").trim();
			String status = request.getParameter("status");
			
			if (name.equals("") || name.equals("null")
					|| name.equals(null)) {
				TB_COUNTRY countryDetails = Country_dao.getCountryByid((id));
				model.put("Edit_CountryCMD", countryDetails);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Please Enter Country");
				//model.put("id2", id);
				return new ModelAndView("Edit_CountryTiles");
			}
			
			/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
				TB_COUNTRY countryDetails = Country_dao.getCountryByid((id));
				model.put("Edit_CountryCMD", countryDetails);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_CountryTiles");
			}*/
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_COUNTRY where name=:name and status=:status and id !=:id");
						q0.setParameter("name", name);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_COUNTRY set name=:name,status=:status,modify_by=:modify_by,modify_date=:modify_date"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("name",name)
					    			  	.setString("status",status)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
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
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Country");
		}

		// -------------------------Delete Country  -------------------------------------//
		
/*		@RequestMapping(value = "/delete_Country", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Country(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_COUNTRY where id=:id";
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
			return new ModelAndView("redirect:Country");
		}*/
		
		@RequestMapping(value = "/delete_Country", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Country(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Country", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_COUNTRY set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where Id=:Id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("Id", id).executeUpdate();
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
			return new ModelAndView("redirect:Country");
		}
	
}
