package com.controller.Civilian_Master;

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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Master.ExservicemenDAO;
import com.models.psg.Civilian_Master.TB_EX_SERVICEMEN;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Ex_ServicemenController {
	
	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private ExservicemenDAO exdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/ex-servicemen", method = RequestMethod.GET)
	 public ModelAndView ExServicemen(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);		 
		Mmap.put("list", exdao.search_exservicemen_details("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());

		 return new ModelAndView("ExservicemenTiles");
	 }
	 
		@RequestMapping(value = "/Exservicemen_Action", method = RequestMethod.POST)
		public ModelAndView Exservicemen_Action(@ModelAttribute("exservicemen_CMD") TB_EX_SERVICEMEN ser, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			int id = ser.getId() > 0 ? ser.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
	 		String ex_servicemen = request.getParameter("ex_servicemen").trim();
	 		String status = request.getParameter("status");
	 		
										
	 		 if (ex_servicemen.equals("") || ex_servicemen.equals("null")|| ex_servicemen.equals(null)) {
	 			Mmap.put("msg", "Please Enter Exservicemen");
				 return new ModelAndView("redirect:ex-servicemen");
				}
	 		 if(ex_servicemen.length() >30) {
	 			Mmap.put("msg", "Exservicemen Length Should Be Less Than 30 Characters");	
				 return new ModelAndView("redirect:ex-servicemen");
			 }
			 if (ser.getStatus() == "inactive" || ser.getStatus().equals("inactive")) {
				 Mmap.put("msg", "Only Select Active Status");
					return new ModelAndView("redirect:ex-servicemen");
				}
			try{
				Query q0 = sessionHQL.createQuery("select count(id) from TB_EX_SERVICEMEN where ex_servicemen=:ex_servicemen and status=:status and id !=:id");
				q0.setParameter("ex_servicemen", ex_servicemen);  
				q0.setParameter("status", status);
				q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					ser.setCreated_by(username);
					ser.setCreated_date(date);
					if (c == 0) {
						ser.setEx_servicemen(ex_servicemen);
						ser.setStatus(status);
						ser.setCreated_by(username);
						ser.setCreated_date(new Date());
				        sessionHQL.save(ser);
						sessionHQL.flush();
						sessionHQL.clear();
						Mmap.put("msg", "Data Saved Successfully.");

					} else {
						Mmap.put("msg", "Data already Exist.");
					}
				}
				tx.commit();
			}catch (RuntimeException e) {
				try {
					tx.rollback();
					Mmap.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:ex-servicemen");
		}
		
		//..............Search........................
				@RequestMapping(value = "/admin/getSearchexservicemenMaster", method = RequestMethod.POST)
				public ModelAndView getSearchexservicemenMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "ex_servicemen1", required = false) String ex_servicemen1,
		                @RequestParam(value = "status1", required = false) String status )
				{
					
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);	
					 	if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						if(request.getHeader("Referer") == null ) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
					ArrayList<ArrayList<String>> list = exdao.search_exservicemen_details(ex_servicemen1,status);
						Mmap.put("ex_servicemen1", ex_servicemen1);
						Mmap.put("status1", status);
						Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					    Mmap.put("list", list);
					return new ModelAndView("ExservicemenTiles","exservicemen_CMD",new TB_EX_SERVICEMEN());
				}
				
				//..............edit.............
				@RequestMapping(value = "/Edit_exservicemen",method = RequestMethod.POST)
				public ModelAndView Edit_exservicemen(@ModelAttribute("updateid") String updateid,ModelMap Mmap,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
						HttpSession sessionEdit) {
		
					String roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}

					
					TB_EX_SERVICEMEN exDetails = exdao.getexservicemendtlByid(Integer.parseInt(updateid));
					    Mmap.put("Edit_exservicemenCMD", exDetails);
					    Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
						Mmap.put("msg", msg);
					return new ModelAndView("Edit_ExservicemenTiles");
				}
				
				
				@RequestMapping(value = "/Edit_exservicemenAction", method = RequestMethod.POST)
				public ModelAndView Edit_exservicemenAction(@ModelAttribute("Edit_exservicemenCMD") TB_EX_SERVICEMEN rs,
						HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);	
					 	if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						if(request.getHeader("Referer") == null ) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					 String ex_servicemen = request.getParameter("ex_servicemen").trim();
					String status = request.getParameter("status");
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
			        
						        if (ex_servicemen.equals("") || ex_servicemen.equals("null")|| ex_servicemen.equals(null)) {
						        	TB_EX_SERVICEMEN exservicemenDetails = exdao.getexservicemendtlByid((id));
						        	model.put("Edit_exservicemenCMD", exservicemenDetails);
						        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
						        	model.put("msg", "Please Enter Exservicemen");
									//model.put("id2", id);
									return new ModelAndView("Edit_ExservicemenTiles");
								}
						        if(ex_servicemen.length() >30) {
						        	TB_EX_SERVICEMEN exservicemenDetails = exdao.getexservicemendtlByid((id));
						        	model.put("Edit_exservicemenCMD", exservicemenDetails);
						        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
						        	model.put("msg", "Exservicemen Length Should Be Less Than 30 Characters");	
									 return new ModelAndView("Edit_ExservicemenTiles");
								 }
						    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
						    		TB_EX_SERVICEMEN exservicemenDetails = exdao.getexservicemendtlByid((id));
						        	model.put("Edit_exservicemenCMD", exservicemenDetails);
						        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
									model.put("msg", "Only Select Active Status.");
									return new ModelAndView("Edit_ExservicemenTiles");
								}*/
						 try {
								
							 Query q0 = session1.createQuery("select count(id) from TB_EX_SERVICEMEN where ex_servicemen=:ex_servicemen and status=:status and  id !=:id");
								q0.setParameter("ex_servicemen", ex_servicemen);  
								q0.setParameter("status", status);  
								q0.setParameter("id", id);
									Long c = (Long) q0.uniqueResult();
									
									if(c==0) {
										 String hql = "update TB_EX_SERVICEMEN set ex_servicemen=:ex_servicemen,status=:status,modified_by=:modified_by,modified_date=:modified_date"
													+ " where id=:id";
								                                   
								    	  Query query = session1.createQuery(hql)
								    			  	.setString("ex_servicemen",ex_servicemen)
								    			  	.setString("status",rs.getStatus())
													.setString("modified_by", username).setDate("modified_date", new Date())
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
					return new ModelAndView("redirect:ex-servicemen");
				}
				
				
				@RequestMapping(value = "/deleteExservicemenMasterURL", method = RequestMethod.POST)
				public @ResponseBody ModelAndView deleteExservicemenMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("ex-servicemen", roleid);	
					 	if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						if(request.getHeader("Referer") == null ) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
					
					List<String> liststr = new ArrayList<String>();
					
					String username = session.getAttribute("username").toString();
					
					try {
						 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						 Transaction tx = sessionHQL.beginTransaction();
						 
						 String hqlUpdate = "update TB_EX_SERVICEMEN set modified_by=:modified_by,modified_date=:modified_date,status=:status"
												+ " where id=:id";
						
						 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
								.setString("modified_by", username)
								.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
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
					return new ModelAndView("redirect:ex-servicemen");
				}
}
