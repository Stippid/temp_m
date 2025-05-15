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
import com.dao.psg.Master.DivDAO;
import com.models.psg.Master.TB_DIV_GRADE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Div_GradeController {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private DivDAO dvdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	 @RequestMapping(value = "/admin/DivGrade", method = RequestMethod.GET)
	 public ModelAndView DivGrade(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
		 	if(val == false) {
			return new ModelAndView("AccessTiles");
		}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);		 
		 Mmap.put("list", dvdao.search_div_details("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("DivTiles");
	 }
	 
	 
		@RequestMapping(value = "/div_Action", method = RequestMethod.POST)
		public ModelAndView div_Action(@ModelAttribute("div_CMD") TB_DIV_GRADE dv, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
			 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			int id = dv.getId() > 0 ? dv.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
	 		String div = request.getParameter("div_grade").trim();
	 		String status = request.getParameter("status");
	 		
						
	 		 if (div.equals("") || div.equals("null")|| div.equals(null)) {
	 			Mmap.put("msg", "Please Enter DivGrade Name");
				 return new ModelAndView("redirect:DivGrade");
				}
			 if (dv.getStatus() == "inactive" || dv.getStatus().equals("inactive")) {
				 Mmap.put("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:DivGrade");
				}
			try{
				Query q0 = sessionHQL.createQuery("select count(id) from TB_DIV_GRADE where div=:div and status=:status and id !=:id");
				q0.setParameter("div", div);  
				q0.setParameter("status", status);
				q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					dv.setCreated_by(username);
					dv.setCreated_date(date);
					if (c == 0) {
						dv.setDiv(div);
						dv.setStatus(status);
						dv.setCreated_by(username);
						dv.setCreated_date(new Date());
							
				        sessionHQL.save(dv);
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
			return new ModelAndView("redirect:DivGrade");
		}
		
		//..............Search........................
				@RequestMapping(value = "/admin/getSearchdivGradeMaster", method = RequestMethod.POST)
				public ModelAndView getSearchdivGradeMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "div1", required = false) String div1,
		                @RequestParam(value = "status1", required = false) String status )

				{
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
					 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
					ArrayList<ArrayList<String>> list = dvdao.search_div_details(div1,status);
						Mmap.put("div1", div1);
						Mmap.put("status1", status);
						Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					
							Mmap.put("list", list);
					return new ModelAndView("DivTiles","div_CMD",new TB_DIV_GRADE());
				}
				
				//..............edit.............
				@RequestMapping(value = "/Edit_DivGrade",method=RequestMethod.POST)
				public ModelAndView Edit_DivGrade(@ModelAttribute("updateid") String updateid,ModelMap Mmap,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
						HttpSession sessionEdit,HttpServletRequest request) {
					 String  roleid = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
					 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
					
					
					
					TB_DIV_GRADE divDetails = dvdao.getdivdtlByid(Integer.parseInt(updateid));
					    Mmap.put("Edit_divCMD", divDetails);
					    Mmap.put("div1", divDetails.getDiv());
					    Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
						Mmap.put("msg", msg);
					return new ModelAndView("Edit_DivTiles");
				}
				
				
				@RequestMapping(value = "/Edit_divAction", method = RequestMethod.POST)
				public ModelAndView Edit_divAction(@ModelAttribute("Edit_divCMD") TB_DIV_GRADE ds,
						HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
					 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					 String div = request.getParameter("div_grade").trim();
					String status = request.getParameter("status");
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
			        
				
			        
						        if (div.equals("") || div.equals("null")|| div.equals(null)) {
						        	TB_DIV_GRADE divDetails = dvdao.getdivdtlByid((id));
						        	model.put("Edit_divCMD", divDetails);
						        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
						        	model.put("msg", "Please Enter DivGrade");
									//model.put("id2", id);
									return new ModelAndView("Edit_DivTiles");
								}	
						    	/*if (ds.getStatus() == "inactive" || ds.getStatus().equals("inactive")) {
						    		TB_DIV_GRADE divDetails = dvdao.getdivdtlByid((id));
						        	model.put("Edit_divCMD", divDetails);
						        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
									model.put("msg", "Only Select Active Status.");
									return new ModelAndView("Edit_DivTiles");
								}*/
						 try {
								
							 Query q0 = session1.createQuery("select count(id) from TB_DIV_GRADE where div=:div and status=:status and  id !=:id");
								q0.setParameter("div", div);  
								q0.setParameter("status", status);  
								q0.setParameter("id", id);
									Long c = (Long) q0.uniqueResult();
									
									if(c==0) {
										 String hql = "update TB_DIV_GRADE set div=:div,status=:status,modify_by=:modify_by,modify_date=:modify_date"
													+ " where id=:id";
								                                   
								    	  Query query = session1.createQuery(hql)
								    			  	.setString("div",div)
								    			  	.setString("status",ds.getStatus())
													.setString("modify_by", username).setDate("modify_date", new Date())
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
					return new ModelAndView("redirect:DivGrade");
				}
				
				
				@RequestMapping(value = "/deletedivMasterURL", method = RequestMethod.POST)
				public @ResponseBody ModelAndView deletedivMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
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
						 
						 String hqlUpdate = "update TB_DIV_GRADE set modify_by=:modify_by,modify_date=:modify_date,status=:status"
												+ " where id=:id";
						
						 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
								.setString("modify_by", username)
								.setDate("modify_date", new Date()).setInteger("id", id).executeUpdate();
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
					return new ModelAndView("redirect:DivGrade");
				}
}
