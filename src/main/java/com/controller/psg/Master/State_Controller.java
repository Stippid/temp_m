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
import com.dao.psg.Master.StateDao;
import com.models.psg.Master.TB_STATE;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class State_Controller {
	
	@Autowired
	private StateDao State_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	 @RequestMapping(value = "/admin/State", method = RequestMethod.GET)
	 public ModelAndView State(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);		 
		 ArrayList<ArrayList<String>> list = State_dao.search_State_name(0,"","active");
		 Mmap.put("list", list);
		 Mmap.put("country_id", mcommon.getMedCountryName("", session));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 return new ModelAndView("StateTiles");
	 }
	 
	 @RequestMapping(value = "/StateAction",method=RequestMethod.POST)
		public ModelAndView StateAction(@ModelAttribute("StateCMD") TB_STATE st,
				 @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				int state_id = st.getState_id() > 0 ? st.getState_id() : 0;
				
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String state_name = request.getParameter("state_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 

						if (st.getCountry_id() == 0 || st.getCountry_id() == null || st.getCountry_id().equals(null)) {
							model.put("msg", "Please Select Country");
							return new ModelAndView("redirect:State");
						}
				
						if (state_name.equals("") || state_name.equals("null") || state_name.equals(null)) {
							model.put("msg", "Please Enter State");
							return new ModelAndView("redirect:State");
						}
				
						if (st.getStatus() == "inactive" || st.getStatus().equals("inactive")) {
				
							model.put("msg", "Only Select Active Status.");
				
							return new ModelAndView("redirect:State");
				
						}
				try{
					
					
					Query q0 = sessionHQL.createQuery("select count(state_id) from TB_STATE where state_name=:state_name and status=:status and state_id !=:state_id");
					q0.setParameter("state_name", st.getState_name());  
					q0.setParameter("status", st.getStatus());
					
					q0.setParameter("state_id", state_id); 
					
					
					Long c = (Long) q0.uniqueResult();

					if (state_id == 0) {
						st.setCreated_by(username);
						st.setCreated_date(date);
						st.setState_name(state_name);
						if (c == 0) {
							sessionHQL.save(st);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					else {
						
						if (c == 0) {
							//String msg = rankdao.updaterankcode(st);
							//model.put("msg", msg);
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
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:State");
			}
	 @RequestMapping(value = "/admin/getSearch_State_Master" , method = RequestMethod.POST)
		public ModelAndView getSearch_State_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "country_id1", required = false) int country_id1,
				@RequestParam(value = "State_name1", required = false) String State_name1,
				@RequestParam(value = "status1", required = false) String status1){
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 ArrayList<ArrayList<String>> list = State_dao.search_State_name(country_id1,State_name1,status1);
		 
		 		 Mmap.put("list", list);
		         Mmap.put("country_id1", country_id1);
				 Mmap.put("State_name1", State_name1);
				 Mmap.put("status1", status1);
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("country_id", mcommon.getMedCountryName("", session));
					
					
			return new ModelAndView("StateTiles","StateCMD",new TB_STATE());
		}
	 
	 @RequestMapping(value = "/Edit_State", method = RequestMethod.POST)
		public ModelAndView Edit_State(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 		TB_STATE stateDetails = State_dao.getstateByid(Integer.parseInt(updateid));
				Mmap.put("Edit_StateCMD", stateDetails);
				Mmap.put("country_id", mcommon.getMedCountryName("", sessionEdit));
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("EditStateTiles");
		}
		
		
		@RequestMapping(value = "/Edit_State_Action", method = RequestMethod.POST)
		public ModelAndView Edit_State_Action(@ModelAttribute("Edit_StateCMD") TB_STATE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("State", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String state_name = request.getParameter("state_name").trim();
			String status = request.getParameter("status");
			
			
				if (rs.getCountry_id() == 0 || rs.getCountry_id() == null || rs.getCountry_id().equals(null)) {
					TB_STATE stateDetails = State_dao.getstateByid((id));
					model.put("Edit_StateCMD", stateDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Select Country");
					//model.put("id2", id);
					return new ModelAndView("EditStateTiles");
				}
		
				if (state_name.equals("") || state_name.equals("null") || state_name.equals(null)) {
					TB_STATE stateDetails = State_dao.getstateByid((id));
					model.put("Edit_StateCMD", stateDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Enter State");
//					model.put("id2", id);
					return new ModelAndView("EditStateTiles");
				}
				
				/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					TB_STATE stateDetails = State_dao.getstateByid((id));
					model.put("Edit_StateCMD", stateDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("EditStateTiles");
		
				}*/
				
				
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(state_id) from TB_STATE where country_id=:country_id and state_name=:state_name and status=:status and state_id !=:state_id");
						q0.setParameter("state_name", state_name); 
						q0.setParameter("status", status); 
						q0.setParameter("country_id", rs.getCountry_id());
						q0.setParameter("state_id", rs.getState_id()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_STATE set country_id=:country_id,state_name=:state_name,status=:status,modify_by=:modify_by,modify_date=:modify_date"
										+ " where state_id=:state_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  .setInteger("country_id",rs.getCountry_id())
					    			  	.setString("state_name",state_name)
					    			  	.setString("status",status)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("state_id",id);
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
			return new ModelAndView("redirect:State");
		}

/*		@RequestMapping(value = "/delete_State", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_State(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_STATE where state_id=:state_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("state_id", id).executeUpdate();
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
			return new ModelAndView("redirect:State");
		}*/
		
		@RequestMapping(value = "/delete_State", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_State(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("State", roleid);	
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
				 
				 String hqlUpdate = "update TB_STATE set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where state_id=:state_id";
				 
				//String hqlUpdate = "delete from TB_RELIGION where religion_id=:religion_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("state_id", id).executeUpdate();
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
			return new ModelAndView("redirect:State");
		}

}
