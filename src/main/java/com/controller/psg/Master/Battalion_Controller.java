package com.controller.psg.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.dao.psg.Master.BattalionDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_BATTALION;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Battalion_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private BattalionDAO BAT;		
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// -------------------------------Battalion For page Open -------------------------------------
	
	@RequestMapping(value = "/admin/Battalion", method = RequestMethod.GET)
	 public ModelAndView Battalion(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Battalion", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		List<Map<String, Object>> list = BAT.search_Battalion("","active");
		Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("BattalionTiles");
	 }
		
	
				// -------------------------------Battalion save -------------------------------------
				
				
				 @RequestMapping(value = "/BattalionAction",method=RequestMethod.POST)
					public ModelAndView BattalionAction(@ModelAttribute("BattalionCmd") TB_BATTALION ba,
							@RequestParam(value = "msg", required = false) String msg,
							HttpServletRequest request,ModelMap model,HttpSession session) {
						
					 
					 String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Battalion", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
					 
								 
							int id = ba.getId() > 0 ? ba.getId() : 0;
							
							Date date = new Date();
							String username = session.getAttribute("username").toString();
							String battalion_name = request.getParameter("battalion_name").trim();
							
							 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
							 Transaction	tx = sessionHQL.beginTransaction();
							
							 if (battalion_name.equals("") || battalion_name.equals("null")|| battalion_name.equals(null)) {
								 model.put("msg", "Please Enter Battalion Name");
								 return new ModelAndView("redirect:Battalion");
								}
							 if (ba.getStatus() == "inactive" || ba.getStatus().equals("inactive")) {
									model.put("msg", "Only Select Active Status.");
									return new ModelAndView("redirect:Battalion");
								}
							try{
								
								Query q0 = sessionHQL.createQuery("select count(id) from TB_BATTALION where battalion_name=:battalion_name and status=:status  and id !=:id");
								q0.setParameter("battalion_name", ba.getBattalion_name());
								q0.setParameter("status", ba.getStatus());
								
								q0.setParameter("id", id);  
								Long c = (Long) q0.uniqueResult();

								if (id == 0) {
									
									ba.setCreated_by(username);
									ba.setCreated_date(date);
									ba.setBattalion_name(battalion_name);
									if (c == 0) {
										sessionHQL.save(ba);
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
							return new ModelAndView("redirect:Battalion");
						}
				 
		
	
				// -------------------------SEARCH Battalion  -------------------------------------
					
					@RequestMapping(value = "/search_Battalion" , method = RequestMethod.POST)
					public ModelAndView search_Battalion(ModelMap model, HttpSession session,HttpServletRequest request,
							@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("battalion_name1") String battalion_name,@ModelAttribute("status1") String status) {

						String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Battalion", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
						
						List<Map<String, Object>> list = BAT.search_Battalion(battalion_name,status);
						model.put("list", list);
						model.put("size", list.size());
						model.put("battalion_name1", battalion_name);
						model.put("status1", status);
						model.put("getStatusMasterList", pcommon.getStatusMasterList());
						
						
						return new ModelAndView("BattalionTiles");
					}
				 
				 
					// -------------------------Edit Battalion For page Open -------------------------------------
					@RequestMapping(value = "/edit_Battalion",method = RequestMethod.POST )
					public ModelAndView edit_Battalion(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
							@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession session) {
						String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Battalion", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						} 
						TB_BATTALION BatDetails = BAT.getBattalionByid(Integer.parseInt(updateid));
						model.put("EditBattalionCMD", BatDetails);
						model.put("getStatusMasterList", pcommon.getStatusMasterList());
						model.put("msg", msg);
				
						return new ModelAndView("EditBattalionTiles");
					}
				 
					
					
					// -------------------------Edit Battalion Action -------------------------------------
					
					
					@RequestMapping(value = "/EditBattalionAction", method = RequestMethod.POST)
					public ModelAndView EditBattalionAction(@ModelAttribute("EditBattalionCMD") TB_BATTALION BA,
							HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
					
						String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Battalion", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
						
						String username = session.getAttribute("username").toString();
						int id = Integer.parseInt(request.getParameter("id"));
						String battalion_name = request.getParameter("battalion_name").trim();
						String status = request.getParameter("status");
						
						Session session1 = HibernateUtil.getSessionFactory().openSession();
				        Transaction tx = session1.beginTransaction();
						
				        if (BA.getBattalion_name().trim().equals("") || BA.getBattalion_name().equals("null")|| BA.getBattalion_name().equals(null)) {
				        	TB_BATTALION BatDetails = BAT.getBattalionByid((id));
							model.put("EditBattalionCMD", BatDetails);
							model.put("getStatusMasterList", pcommon.getStatusMasterList());
				        	model.put("msg", "Please Enter Battalion Name");
				        	//model.put("id", id);
							
							return new ModelAndView("EditBattalionTiles");
						}	
				    	/*if (BA.getStatus() == "inactive" || BA.getStatus().equals("inactive")) {
				    		TB_BATTALION BatDetails = BAT.getBattalionByid((id));
							model.put("EditBattalionCMD", BatDetails);
							model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("EditBattalionTiles");
						}*/
						 	try {
								 Query q0 = session1.createQuery("select count(id) from TB_BATTALION where battalion_name=:battalion_name and status=:status and id !=:id");
									q0.setParameter("battalion_name", battalion_name); 
									q0.setParameter("status", status); 
									q0.setParameter("id", id); 
									Long c = (Long) q0.uniqueResult();
									
									if(c==0) {
										 String hql = "update TB_BATTALION set battalion_name=:battalion_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
													+ " where id=:id";
								                                   
								    	  Query query = session1.createQuery(hql)
								    			  	.setString("battalion_name",battalion_name)
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
						return new ModelAndView("redirect:Battalion");
					}

					
					
					
					// -------------------------Delete Battalion  -------------------------------------
			/*		@RequestMapping(value = "/Battalion_delete" , method = RequestMethod.POST)
					public @ResponseBody ModelAndView Battalion_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
							HttpSession sessionA, ModelMap model,
							@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
						List<String> liststr = new ArrayList<String>();
						try {
							 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
							 Transaction tx = sessionHQL.beginTransaction();
							 
							String hqlUpdate = "delete from TB_BATTALION where id=:id";
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
						return new ModelAndView("redirect:Battalion");
				}*/
					
					@RequestMapping(value = "/Battalion_delete", method = RequestMethod.POST)
					public @ResponseBody ModelAndView Battalion_delete(@ModelAttribute("id1") int id, BindingResult result,
							HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
							@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
						
						String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Battalion", roleid);
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

							String hqlUpdate = "update TB_BATTALION set modify_by=:modify_by,modify_date=:modify_date,status=:status"
									+ " where id=:id";

							int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
									.setString("modify_by", username).setDate("modify_date", new Date()).setInteger("id", id)
									.executeUpdate();
							tx.commit();
							sessionHQL.close();

							if (app > 0) {
								liststr.add("Delete Successfully.");
							} else {
								liststr.add("Delete UNSuccessfully.");
							}
							model.put("msg", liststr.get(0));

						} catch (Exception e) {
							liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
							model.put("msg", liststr.get(0));
						}
						return new ModelAndView("redirect:Battalion");
					}					
	
}
