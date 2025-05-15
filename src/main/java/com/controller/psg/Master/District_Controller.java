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
import com.dao.psg.Master.DistrictDao;
import com.models.psg.Master.TB_DISTRICT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class District_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired
	private DistrictDao District_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// -------------------------------District For page Open -------------------------------------

	 @RequestMapping(value = "/admin/District", method = RequestMethod.GET)
	 public ModelAndView District(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 Mmap.put("msg", msg);
		 Mmap.put("country_id", mcommon.getMedCountryName("", session));
		 Mmap.put("state_id", mcommon.getMedStateName("", session));
		 ArrayList<ArrayList<String>> list = District_dao.search_District_name(0,0,"","active");
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		 return new ModelAndView("DistrictTiles");
	 }
	 
		// -------------------------------District save -------------------------------------

	 @RequestMapping(value = "/DistrictAction",method=RequestMethod.POST)
		public ModelAndView DistrictAction(@ModelAttribute("DistrictCMD") TB_DISTRICT rm, @RequestParam(value = "msg", required = false) String msg,
				BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}		 
				int district_id = rm.getDistrict_id() > 0 ? rm.getDistrict_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String district_name = request.getParameter("district_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				 
							if (rm.getCountry_id() == 0 || rm.getCountry_id() == null || rm.getCountry_id().equals(null)) {
								model.put("msg", "Please Select Country.");
					
								return new ModelAndView("redirect:District");
							}
					
							if (rm.getState_id() == 0 || rm.getState_id() == null || rm.getState_id().equals(null)) {
								model.put("msg", "Please Select State");
								return new ModelAndView("redirect:District");
							}
					
							if (district_name.equals("") || district_name.equals("null")
									|| district_name.equals(null)) {
								model.put("msg", "Please Enter District");
					
								return new ModelAndView("redirect:District");
							}
							if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
								
								model.put("msg", "Only Select Active Status.");
					
								return new ModelAndView("redirect:District");
					
							}

					try{
					
					Query q0 = sessionHQL.createQuery("select count(district_id) from TB_DISTRICT where district_name=:district_name  and status=:status  and district_id !=:district_id");
					q0.setParameter("district_name", rm.getDistrict_name());  
					q0.setParameter("status", rm.getStatus());
					
					q0.setParameter("district_id", district_id); 
					Long c = (Long) q0.uniqueResult();

					if (district_id == 0) {
						rm.setCreated_by(username);
						rm.setCreated_date(date);
						rm.setDistrict_name(district_name);
						if (c == 0) {
							sessionHQL.save(rm);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					else {
						
						if (c == 0) {
							//String msg = rankdao.updaterankcode(rm);
							//model.put("msg", msg);
						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					tx.commit();
				}catch(RuntimeException e){
					e.printStackTrace();
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
				return new ModelAndView("redirect:District");
			}
	 
		// -------------------------SEARCH District  -------------------------------------

	 @RequestMapping(value = "/admin/getSearch_District_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_District_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "country_id1", required = false) int country_id1,
				@RequestParam(value = "state_id1", required = false) int state_id1,
				@RequestParam(value = "District_name1", required = false) String District_name1 ,
		        @RequestParam(value = "status1", required = false) String status1) 
	 {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 ArrayList<ArrayList<String>> list = District_dao.search_District_name(country_id1,state_id1,District_name1,status1);
		        Mmap.put("country_id1", country_id1);
			    Mmap.put("state_id1", state_id1);
				Mmap.put("District_name1", District_name1);
				Mmap.put("status1", status1);
				 Mmap.put("country_id", mcommon.getMedCountryName("", session));
				 Mmap.put("state_id", mcommon.getMedStateName("", session));
				 Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				
					Mmap.put("list", list);
			return new ModelAndView("DistrictTiles","DistrictCMD",new TB_DISTRICT());
		}
	 
		// -------------------------Edit District For page Open -------------------------------------

	 @RequestMapping(value = "/Edit_District",method=RequestMethod.POST)
		public ModelAndView Edit_District(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		
		 
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				TB_DISTRICT disDetails = District_dao.getDistrictByid(Integer.parseInt(updateid));
				Mmap.put("Edit_DistrictCMD", disDetails);
				Mmap.put("country_id", mcommon.getMedCountryName("", sessionEdit));
				Mmap.put("state_id", mcommon.getMedStateName("", sessionEdit));
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("getMedStateName", mcommon.getMedStateName("", sessionEdit));
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_DistrictTiles");
		}
		
		// -------------------------Edit District_Action -------------------------------------

		
		@RequestMapping(value = "/Edit_District_Action", method = RequestMethod.POST)
		public ModelAndView Edit_District_Action(@ModelAttribute("Edit_DistrictCMD") TB_DISTRICT rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("District", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			int state_id = Integer.parseInt(request.getParameter("state_id"));
			String district_name = request.getParameter("district_name").trim();
			String status = request.getParameter("status");
			
					if (rs.getCountry_id() == 0 || rs.getCountry_id() == null || rs.getCountry_id().equals(null)) {
						TB_DISTRICT disDetails = District_dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", mcommon.getMedCountryName("", session));
						model.put("state_id", mcommon.getMedStateName("", session));
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Select Country");
						//model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
			
				  if (rs.getState_id() == 0 || rs.getState_id() == null || rs.getState_id().equals(null)) {
					  	TB_DISTRICT disDetails = District_dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", mcommon.getMedCountryName("", session));
						model.put("state_id", mcommon.getMedStateName("", session));
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Select State");
//						model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
			
				 if (district_name.equals("") || district_name.equals("null")
							|| district_name.equals(null)) {
						TB_DISTRICT disDetails = District_dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", mcommon.getMedCountryName("", session));
						model.put("state_id", mcommon.getMedStateName("", session));
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Enter District");
						//model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
				/* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					 	TB_DISTRICT disDetails = District_dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", mcommon.getMedCountryName("", session));
						model.put("state_id", mcommon.getMedStateName("", session));
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("Edit_DistrictTiles");
			
					}*/
			
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(district_id) from TB_DISTRICT where country_id=:country_id and "
					 		+ "state_id=:state_id and district_name=:district_name  and status=:status and district_id !=:district_id");
					 
						q0.setParameter("district_name", district_name); 
						q0.setParameter("country_id", rs.getCountry_id());
						q0.setParameter("state_id", state_id);
						q0.setParameter("status", status); 
						q0.setParameter("district_id", rs.getDistrict_id()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_DISTRICT set country_id=:country_id,state_id=:state_id,district_name=:district_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where district_id=:district_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  .setInteger("country_id",rs.getCountry_id())
					    			  .setInteger("state_id",state_id)
					    			  .setString("status",status)
					    			  	.setString("district_name",district_name)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("district_id",id);
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
			return new ModelAndView("redirect:District");
		}

		
		
		// -------------------------Delete District  -------------------------------------

	/*	@RequestMapping(value = "/delete_District", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_District(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_DISTRICT where district_id=:district_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("district_id", id).executeUpdate();
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
			return new ModelAndView("redirect:District");
		}*/
		
		@RequestMapping(value = "/delete_District", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_District(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("District", roleid);	
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
				 
				 String hqlUpdate = "update TB_DISTRICT set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where district_id=:district_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("district_id", id).executeUpdate();
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
			return new ModelAndView("redirect:District");
		}

}
