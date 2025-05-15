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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Nationality_DAO;
import com.models.psg.Master.TB_NATIONALITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Nationality_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	private Nationality_DAO nat_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	 @RequestMapping(value = "/admin/Nationality", method = RequestMethod.GET)
	 public ModelAndView Nationality(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		 Mmap.put("msg", msg);
		 Mmap.put("country_id", mcommon.getMedCountryName("", session));
		 ArrayList<ArrayList<String>> list = nat_dao.search_nationlity(0,"","active");
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 return new ModelAndView("NationalityTiles");
	 }
	 // ..........save...............
	 @RequestMapping(value = "/NationalityAction",method=RequestMethod.POST)
		public ModelAndView NationalityAction(@ModelAttribute("NationalityCMD") TB_NATIONALITY com,@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				int nationality_id = com.getNationality_id() > 0 ? com.getNationality_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String nationality_name = request.getParameter("nationality_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
					if (com.getCountry_id() == 0 || com.getCountry_id() == null || com.getCountry_id().equals(null)) {
						model.put("msg", "Please Select Country");
						return new ModelAndView("redirect:Nationality");
					}
			
					if (nationality_name.equals("") || nationality_name.equals("null")
							|| nationality_name.equals(null)) {
						model.put("msg", "Please Enter Nationality");
						return new ModelAndView("redirect:Nationality");
					}
			
					if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
						
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Nationality");
			
					}
				
				try{
					
					Query q0 = sessionHQL.createQuery("select count(nationality_id) from TB_NATIONALITY where nationality_name=:nationality_name and status=:status and country_id=:country_id and nationality_id !=:nationality_id");
					q0.setParameter("nationality_name", com.getNationality_name()); 
					q0.setParameter("status", com.getStatus());
					q0.setParameter("country_id",Integer.parseInt(request.getParameter("country_id")));  
					q0.setParameter("nationality_id", nationality_id);  				
					
					Long c = (Long) q0.uniqueResult();

					if (nationality_id == 0) {
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setNationality_name(nationality_name);
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
				return new ModelAndView("redirect:Nationality");
			}
	 
	 //...........search...................
	    @RequestMapping(value = "/admin/getSearch_Nationality_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Nationality_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "country_id1", required = false) int country_id1,
				@RequestParam(value = "nationality_name1", required = false) String nationality_name1,
				@RequestParam(value = "status1", required = false) String status ){
	    	
	    	
	    	String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
	    	ArrayList<ArrayList<String>> list = nat_dao.search_nationlity(country_id1,nationality_name1,status);
			
		        Mmap.put("country_id1", country_id1);
				Mmap.put("nationality_name1", nationality_name1);
				Mmap.put("status1", status);
				Mmap.put("country_id", mcommon.getMedCountryName("", session));
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
					Mmap.put("list", list);
			return new ModelAndView("NationalityTiles","NationalityCMD",new TB_NATIONALITY());
		}
	 //.................edit........
	    @RequestMapping(value = "/Edit_Nationality",method=RequestMethod.POST)
		public ModelAndView Edit_Religion(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
	    	String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
	    	   	TB_NATIONALITY authDetails = nat_dao.getnationalityByid(Integer.parseInt(updateid));
				Mmap.put("Edit_NationalityCMD", authDetails);
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				Mmap.put("msg", msg);
				Mmap.put("country_id", mcommon.getMedCountryName("", sessionEdit));
			return new ModelAndView("Edit_Nationality_Tiles");
		}
	    
	    @RequestMapping(value = "/Edit_Nationality_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Nationality_Action(@ModelAttribute("Edit_NationalityCMD") TB_NATIONALITY rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
	    	
	    	String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			String nationality_name = request.getParameter("nationality_name").trim();
			
		
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
						if (rs.getCountry_id() == 0 || rs.getCountry_id() == null || rs.getCountry_id().equals(null)) {
							TB_NATIONALITY authDetails = nat_dao.getnationalityByid((id));
							model.put("Edit_NationalityCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("msg", "Please Select Country");
							//model.put("id2", id);
							return new ModelAndView("Edit_Nationality_Tiles");
						}
				
						if (nationality_name.equals("") || nationality_name.equals("null") || nationality_name.equals(null)) {
							TB_NATIONALITY authDetails = nat_dao.getnationalityByid((id));
							model.put("Edit_NationalityCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("msg", "Please Enter Nationality");
							//model.put("id2", id);
							return new ModelAndView("Edit_Nationality_Tiles");
						}
						/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
							TB_NATIONALITY authDetails = nat_dao.getnationalityByid((id));
							model.put("Edit_NationalityCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("msg", "Only Select Active Status.");
							model.put("id2", id);
							return new ModelAndView("Edit_Nationality_Tiles");
				
						}*/
		 
				 try {
					
					 Query q0 = session1.createQuery("select count(nationality_id) from TB_NATIONALITY where nationality_name=:nationality_name and status=:status and country_id=:country_id and nationality_id !=:nationality_id");
						q0.setParameter("nationality_name", rs.getNationality_name()); 
						q0.setParameter("status", status); 
						q0.setParameter("country_id", rs.getCountry_id());  
						q0.setParameter("nationality_id", rs.getNationality_id()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_NATIONALITY set country_id=:country_id,nationality_name=:nationality_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where nationality_id=:nationality_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    				.setInteger("country_id",rs.getCountry_id())
					    			  	.setString("nationality_name",nationality_name)
					    			  	.setString("status",status)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("nationality_id",id);
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
			return new ModelAndView("redirect:Nationality");
		}
	    
	/*	@RequestMapping(value = "/delete_Nationality", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Nationality(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_NATIONALITY where nationality_id=:nationality_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("nationality_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Nationality");
		}*/
	    
		@RequestMapping(value = "/delete_Nationality", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Nationality(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
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
				 
				 String hqlUpdate = "update TB_NATIONALITY set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where nationality_id=:nationality_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("nationality_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Nationality");
		}
}
