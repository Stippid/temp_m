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
import com.dao.psg.Master.City_DAO;
import com.models.psg.Master.TB_CITY;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class City_Controller {
	
	
	@Autowired
	private City_DAO city_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	Psg_CommonController mcommon = new Psg_CommonController();
	
	//------------------------------for page open------------------------------//
	
	@RequestMapping(value = "/admin/City", method = RequestMethod.GET)
	 public ModelAndView City(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("City", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			 ArrayList<ArrayList<String>> list = city_dao.search_city_name(0,0,0,"","active");
		 Mmap.put("msg", msg);
		 Mmap.put("country_id", mcommon.getMedCountryName("", session));
		 Mmap.put("state_id", mcommon.getMedStateName("", session));
		 Mmap.put("district_id", mcommon.getMedDistrictName("", session));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		 Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
		
		 Mmap.put("list", list);
		 
		 return new ModelAndView("CityTiles");
	 }
	
	
	//-------------------------save-------------------------------//
	
	@RequestMapping(value = "/CityAction",method=RequestMethod.POST)
	public ModelAndView CityAction(@ModelAttribute("CityCMD") TB_CITY rm,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("City", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				 
			int city_id = rm.getCity_id() > 0 ? rm.getCity_id() : 0;
			
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String city_name = request.getParameter("city_name").trim(); 
			
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			 
				if (rm.getCountry_id() == 0 || rm.getCountry_id() == null || rm.getCountry_id().equals(null)) {
					model.put("msg", "Please Select Country");
					return new ModelAndView("redirect:City");
				}
		
				if (rm.getState_id() == 0 || rm.getState_id() == null || rm.getState_id().equals(null)) {
					model.put("msg", "Please Select State");
					return new ModelAndView("redirect:City");
				}
		
				if (rm.getDistrict_id() == 0 || rm.getDistrict_id() == null || rm.getDistrict_id().equals(null)) {
					model.put("msg", "Please Select District");
					return new ModelAndView("redirect:City");
				}
		
				if (city_name.equals("") || city_name.equals("null") || city_name.equals(null)) {
					model.put("msg", "Please Enter City");
					return new ModelAndView("redirect:City");
				}
		
				if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
					
					model.put("msg", "Only Select Active Status.");
		
					return new ModelAndView("redirect:Religion");
		
				}
					  
			  
			
			try{
				
				 Query q0 = sessionHQL.createQuery("select count(city_id) from TB_CITY where country_id=:country_id and state_id=:state_id and district_id=:district_id and city_name=:city_name and city_id !=:city_id and status=:status ");
					q0.setParameter("city_name", rm.getCity_name());  
					q0.setParameter("country_id", rm.getCountry_id());
					q0.setParameter("state_id", rm.getState_id());
					q0.setParameter("district_id", rm.getDistrict_id());
					q0.setParameter("city_id", rm.getCity_id()); 
					q0.setParameter("status", rm.getStatus());
				//	q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

				if (city_id == 0) {
					rm.setCreated_by(username);
					rm.setCreated_date(date);
					rm.setCity_name(city_name);
					if (c == 0) {
						sessionHQL.save(rm);
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
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:City");
		}
	
	
	//--------------------------------SEARCH---------------------------//
	
	 @RequestMapping(value = "/admin/search_city", method = RequestMethod.POST)
		public ModelAndView search_city(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				 @RequestParam(value = "country_id1", required = false) int country_id,
                 @RequestParam(value = "state_id1", required = false) int state_id,
                 @RequestParam(value = "district_id1", required = false) int district_id,
                 @RequestParam(value = "city_name1", required = false) String city_name,
                 @RequestParam(value = "status1", required = false) String status ) {
				 Mmap.put("country_id1", country_id);
				 Mmap.put("state_id1", state_id);
		         Mmap.put("district_id1", district_id);
				 Mmap.put("city_name1", city_name);
				 Mmap.put("status1", status);
				 Mmap.put("country_id", mcommon.getMedCountryName("", session));
				 Mmap.put("state_id", mcommon.getMedStateName("", session));
				 Mmap.put("district_id", mcommon.getMedDistrictName("", session));
				 Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
				 Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("City", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
		         ArrayList<ArrayList<String>> list = city_dao.search_city_name(country_id,state_id,district_id,city_name,status);
		         Mmap.put("list", list);
			return new ModelAndView("CityTiles","CityCMD",new TB_CITY());
		}
	 
	 //-----------------------------edit for open page-------------------//
	 
	  @RequestMapping(value = "/Edit_city" ,method = RequestMethod.POST)
		public ModelAndView Edit_city(@ModelAttribute("id2") String id,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession session) {
			
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("City", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}

		  
		       	 TB_CITY authDetails = city_dao.getTB_CITYByid(Integer.parseInt(id));
				 Mmap.put("Edit_City_CMD", authDetails);
				 Mmap.put("msg", msg);
				 Mmap.put("country_id", mcommon.getMedCountryName("", session));
				 Mmap.put("state_id", mcommon.getMedStateName("", session));
				 Mmap.put("district_id", mcommon.getMedDistrictName("", session));
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
				 Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
			return new ModelAndView("Edit_City_Tiles");
		}
	  
	  
	//----------------------------- edit action-----------------------------------//  
	  
	  @RequestMapping(value = "/Edit_City_Action", method = RequestMethod.POST)
		public ModelAndView Edit_City_Action(@ModelAttribute("Edit_City_CMD") TB_CITY rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		  String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("City", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
		  
		  String username = session.getAttribute("username").toString();
			
			//String status = request.getParameter("status");
			int id = Integer.parseInt(request.getParameter("id"));
			String city_name = request.getParameter("city_name").trim();
		
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
						if (rs.getCountry_id() == 0 || rs.getCountry_id() == null || rs.getCountry_id().equals(null)) {
							TB_CITY authDetails = city_dao.getTB_CITYByid((id));
							model.put("Edit_City_CMD", authDetails);
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("state_id", mcommon.getMedStateName("", session));
							model.put("district_id", mcommon.getMedDistrictName("", session));
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Select Country");
							//model.put("id2", id);
							return new ModelAndView("Edit_City_Tiles");
						}
				
						if (rs.getState_id() == 0 || rs.getState_id() == null || rs.getState_id().equals(null)) {
							TB_CITY authDetails = city_dao.getTB_CITYByid((id));
							model.put("Edit_City_CMD", authDetails);
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("state_id", mcommon.getMedStateName("", session));
							model.put("district_id", mcommon.getMedDistrictName("", session));
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Select State");
							//model.put("id2", id);
							return new ModelAndView("Edit_City_Tiles");
						}
				
						if (rs.getDistrict_id() == 0 || rs.getDistrict_id() == null || rs.getDistrict_id().equals(null)) {
							TB_CITY authDetails = city_dao.getTB_CITYByid((id));
							model.put("Edit_City_CMD", authDetails);
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("state_id", mcommon.getMedStateName("", session));
							model.put("district_id", mcommon.getMedDistrictName("", session));
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Select District");
							//model.put("id2", id);
							return new ModelAndView("Edit_City_Tiles");
						}
				
						if (city_name.equals("") || city_name.equals("null") || city_name.equals(null)) {
							TB_CITY authDetails = city_dao.getTB_CITYByid((id));
							model.put("Edit_City_CMD", authDetails);
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("state_id", mcommon.getMedStateName("", session));
							model.put("district_id", mcommon.getMedDistrictName("", session));
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Enter City");
							//model.put("id2", id);
							return new ModelAndView("Edit_City_Tiles");
						}
						/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
							TB_CITY authDetails = city_dao.getTB_CITYByid((id));
							model.put("Edit_City_CMD", authDetails);
							model.put("country_id", mcommon.getMedCountryName("", session));
							model.put("state_id", mcommon.getMedStateName("", session));
							model.put("district_id", mcommon.getMedDistrictName("", session));
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("Edit_City_Tiles");
				
						}*/
				 try {
					
					 Query q0 = session1.createQuery("select count(city_id) from TB_CITY where country_id=:country_id and state_id=:state_id and district_id=:district_id and city_name=:city_name and status=:status and city_id !=:city_id");
						q0.setParameter("city_name", rs.getCity_name());  
						q0.setParameter("country_id", rs.getCountry_id());
						q0.setParameter("state_id", rs.getState_id());
						q0.setParameter("district_id", rs.getDistrict_id());
						q0.setParameter("city_id", rs.getCity_id()); 
						q0.setParameter("status", rs.getStatus());
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_CITY set country_id=:country_id,state_id=:state_id,district_id=:district_id,city_name=:city_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where city_id=:city_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    				.setInteger("country_id",rs.getCountry_id())
					    				.setInteger("state_id",rs.getState_id())
					    				.setInteger("district_id",rs.getDistrict_id())
					    			  	.setString("city_name",city_name)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("city_id",id)
					    	             .setString("status",rs.getStatus()); 
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
		                      model.put("msg", "Couldn’t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:City");
		}
	  
//----------------------------------delete--------------------------------//
	  
	  
	 /* @RequestMapping(value = "/delete_city", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_city(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_CITY where city_id=:city_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("city_id", id).executeUpdate();
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
			return new ModelAndView("redirect:City");
		}*/
	    
	  @RequestMapping(value = "/delete_city", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_city(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		  String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("City", roleid);	
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
				 
				 String hqlUpdate = "update TB_CITY set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where city_id=:city_id";
				 
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("city_id", id).executeUpdate();
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
			return new ModelAndView("redirect:City");
		}
	 
}
