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
import com.dao.Jco_Master.ApptDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.model.Jco_Master.TB_PSG_MSTR_APPOINTMENT_JCO;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/","user"})
public class Appointment_Controller_Jco {
	

	Psg_CommonController pcommon = new Psg_CommonController();
	
	
	@Autowired
	ApptDAO aaptdao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	// -------------------------------Rank For page Open -------------------------------------
		@RequestMapping(value = "/admin/Appointment_Url", method = RequestMethod.GET)
		 public ModelAndView Appointment_Url(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

			
			 ArrayList<ArrayList<String>> list = aaptdao.search_Appt("", "","active");
			 Mmap.put("list", list);
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			 
			 return new ModelAndView("AppointmentJCOTiles");
		 
		}
		
		
		// -------------------------------Rank save -------------------------------------
		
		 @RequestMapping(value = "/ApptAction",method=RequestMethod.POST)
				public ModelAndView ApptAction(@ModelAttribute("ApptCmd") TB_PSG_MSTR_APPOINTMENT_JCO com,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request,ModelMap model,HttpSession session) {
					
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				} 
						int id = com.getId() > 0 ? com.getId() : 0;
						
						Date date = new Date();
						String username = session.getAttribute("username").toString();
						String appointment = request.getParameter("appointment").trim();
						String category = request.getParameter("category").trim();
						
						 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						 Transaction	tx = sessionHQL.beginTransaction();
							
						 if (appointment.equals("") || appointment.equals("null")
									|| appointment.equals(null)) {
								model.put("msg", "Please Enter appointment");
								return new ModelAndView("redirect:Blood");
							}
						 if (category.equals("") || category.equals("null")
									|| category.equals(null)) {
								model.put("msg", "Please Enter Category");
								return new ModelAndView("redirect:Blood");
							}
						 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
								
								model.put("msg", "Only Select Active Status.");
					
								return new ModelAndView("redirect:Appointment_Url");
					
							}
						 
						try{
							
							Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_APPOINTMENT_JCO where appointment=:appointment and category=:category and status=:status and id !=:id");
							q0.setParameter("appointment", com.getAppointment());  
							q0.setParameter("category", com.getCategory());  
							q0.setParameter("status", com.getStatus());						
							q0.setParameter("id", id);  
							Long c = (Long) q0.uniqueResult();

							if (id == 0) {
								
								com.setCreated_by(username);
								com.setCreated_dt(date);
								com.setAppointment(appointment);
								com.setCategory(category);
							
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
						return new ModelAndView("redirect:Appointment_Url");
					}
			 
		 
		 @RequestMapping(value = "/admin/search_Appt", method = RequestMethod.POST)
			public ModelAndView search_Appt(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "appointment1", required = false) String appointment1 ,@RequestParam(value = "category1", required = false) String category1,@ModelAttribute("status1") String status)  {
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 		ArrayList<ArrayList<String>> list = aaptdao.search_Appt(appointment1,category1, status);
					Mmap.put("appointment1", appointment1);
					Mmap.put("category1", category1);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					
				    Mmap.put("list", list);
				   return new ModelAndView("AppointmentJCOTiles");
				   
			}
		 
		 @RequestMapping(value = "/edit_Appt", method = RequestMethod.POST)
			public ModelAndView edit_Appt(@ModelAttribute("updateid") String updateid,ModelMap Mmap,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
					HttpSession sessionEdit) {
			 
			 String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
			 TB_PSG_MSTR_APPOINTMENT_JCO DT = aaptdao.getApptByid(Integer.parseInt(updateid));
					Mmap.put("Edit_ApptCMD", DT);	
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					Mmap.put("msg", msg);
				return new ModelAndView("EditAppointmentTiles");
			}
		 
		 
		 @RequestMapping(value = "/Edit_Appt_Action", method = RequestMethod.POST)
			public ModelAndView Edit_Appt_Action(@ModelAttribute("Edit_ApptCMD") TB_PSG_MSTR_APPOINTMENT_JCO rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			 
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("id"));
				String appointment = request.getParameter("appointment").trim();
				String category = request.getParameter("category").trim();
				String status = request.getParameter("status");
				
				

				if (appointment.equals("") || appointment.equals("null")|| appointment.equals(null)) {
					TB_PSG_MSTR_APPOINTMENT_JCO DT = aaptdao.getApptByid(id);
					model.put("Edit_Disc_TrialedCMD", DT);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Enter appointment");
					return new ModelAndView("EditAppointmentTiles");
				}	
				if (category.equals("") || category.equals("null")|| category.equals(null)) {
					TB_PSG_MSTR_APPOINTMENT_JCO DT = aaptdao.getApptByid(id);
					model.put("Edit_Disc_TrialedCMD", DT);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Enter category");
					return new ModelAndView("EditAppointmentTiles");
				}	
		    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
		    		TB_PSG_MSTR_APPOINTMENT_JCO DT = aaptdao.getApptByid(id);
					model.put("Edit_Army_ActCMD", DT);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_Army_ActTiles");
				}*/
		    	
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
					 try {
						
						 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_APPOINTMENT_JCO where appointment=:appointment and category=:category and status=:status and id !=:id");
							q0.setParameter("appointment", appointment);
							q0.setParameter("category", category);
							q0.setParameter("id", id); 
							q0.setParameter("status", status);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PSG_MSTR_APPOINTMENT_JCO set  appointment=:appointment,category=:category,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("appointment",appointment)
						    			  	.setString("category", category)
						    			  	.setString("status",status)
											.setString("modified_by", username)
											.setDate("modified_dt", new Date())
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
				return new ModelAndView("redirect:Appointment_Url");
			}
		 
		 @RequestMapping(value = "/Appt_delete", method = RequestMethod.POST)
			public @ResponseBody ModelAndView Appt_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
			 
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 List<String> liststr = new ArrayList<String>();
				
				String username = session.getAttribute("username").toString();
				
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					 String hqlUpdate = "update TB_PSG_MSTR_APPOINTMENT_JCO set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
											+ " where id=:id";
					 
					
					 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
							.setString("modified_by", username)
							.setDate("modified_dt", new Date()).setInteger("id", id).executeUpdate();
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
				return new ModelAndView("redirect:Appointment_Url");
			}
		 
 
}
