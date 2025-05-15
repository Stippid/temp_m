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
import com.dao.psg.Master.OperationDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_OPERATION;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/","user"})
public class Operation_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
		
	@Autowired
	private OperationDAO OPE;
	@Autowired
	private RoleBaseMenuDAO roledao;

			// -------------------------------Operation For page Open -------------------------------------
			@RequestMapping(value = "/admin/Operation", method = RequestMethod.GET)
			 public ModelAndView Operation(ModelMap Mmap, HttpSession session,
					 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
				
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
				
				List<Map<String, Object>> list = OPE.search_Operation("","active");
				Mmap.put("list", list);
				 Mmap.put("msg", msg);
				 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				 return new ModelAndView("OperationTiles");
			 }
	
	
			// -------------------------------Operation save -------------------------------------
			
			
			 @RequestMapping(value = "/OperationAction",method=RequestMethod.POST)
				public ModelAndView OperationAction(@ModelAttribute("OperationCmd") TB_OPERATION op,
						 @RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request,ModelMap model,HttpSession session) {
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
							 
						int id = op.getId() > 0 ? op.getId() : 0;
						
						Date date = new Date();
						String username = session.getAttribute("username").toString();
						String operation_name = request.getParameter("operation_name").trim();
						
						 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						 Transaction	tx = sessionHQL.beginTransaction();
						
						 if (operation_name.equals("") || operation_name.equals("null")|| operation_name.equals(null)) {
							 model.put("msg", "Please Enter Operation Name");
							 return new ModelAndView("redirect:Operation");
							}
						 if (op.getStatus() == "inactive" || op.getStatus().equals("inactive")) {
								model.put("msg", "Only Select Active Status.");
								return new ModelAndView("redirect:Operation");
							}
						try{
							
							Query q0 = sessionHQL.createQuery("select count(id) from TB_OPERATION where operation_name=:operation_name and status=:status  and id !=:id");
							q0.setParameter("operation_name", op.getOperation_name());  
							q0.setParameter("status", op.getStatus());
							q0.setParameter("id", id);  
							Long c = (Long) q0.uniqueResult();

							if (id == 0) {
								
								op.setCreated_by(username);
								op.setCreated_date(date);
								op.setOperation_name(operation_name);
								if (c == 0) {
									sessionHQL.save(op);
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
						return new ModelAndView("redirect:Operation");
					}
			 
	
			 
			// -------------------------SEARCH Operation  -------------------------------------
				
				@RequestMapping(value = "/search_Operation" , method = RequestMethod.POST)
				public ModelAndView search_Operation(ModelMap model, HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("operation_name1") String operation_name,
						@RequestParam(value="status1",required = false ) String status) {

					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
						if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
					List<Map<String, Object>> list = OPE.search_Operation(operation_name,status);
					model.put("list", list);
					model.put("size", list.size());
					model.put("operation_name1", operation_name);
					model.put("list", list);
					model.put("status1", status);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					return new ModelAndView("OperationTiles");
				}
			 
				// -------------------------Edit Operation For page Open -------------------------------------
				@RequestMapping(value = "/edit_Operation",method = RequestMethod.POST )
				public ModelAndView edit_Operation(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
				    
						String roleid = sessionEdit.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Operation", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}

					
					
					TB_OPERATION OpeDetails = OPE.getOperationByid(Integer.parseInt(updateid));
					model.put("EditOperationCMD", OpeDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", msg);
			
					return new ModelAndView("EditOperationTiles");
				}
			 
				// -------------------------Edit Operation Action -------------------------------------
				
				
				@RequestMapping(value = "/EditOperationAction", method = RequestMethod.POST)
				public ModelAndView EditOperationAction(@ModelAttribute("EditOperationCMD") TB_OPERATION OP,
						HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
						if(val == false) {
							return new ModelAndView("AccessTiles");
						}
						if(request.getHeader("Referer") == null ) {
							msg = "";
						}
			
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					String operation_name = request.getParameter("operation_name").trim();
					String status = request.getParameter("status");
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
					
			        if (operation_name.equals("") || operation_name.equals("null")|| operation_name.equals(null)) {
			        	TB_OPERATION OpeDetails = OPE.getOperationByid((id));
						model.put("EditOperationCMD", OpeDetails);
						model.put("getStatusMasterList", pcommon.getStatusMasterList());
			        	model.put("msg", "Please Enter Operation Name");
						return new ModelAndView("EditOperationTiles");
					}	
			    
			    	/*if (OP.getStatus() == "inactive" || OP.getStatus().equals("inactive")) {
			    		TB_OPERATION OpeDetails = OPE.getOperationByid((id));
						model.put("EditOperationCMD", OpeDetails);
						model.put("getStatusMasterList", pcommon.getStatusMasterList());
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("EditOperationTiles");
					}*/
					 	try {
							 Query q0 = session1.createQuery("select count(id) from TB_OPERATION where operation_name=:operation_name and status=:status and id !=:id");
								q0.setParameter("operation_name", operation_name);  
								q0.setParameter("id", id); 
								q0.setParameter("status", status); 
								Long c = (Long) q0.uniqueResult();
								
								if(c==0) {
									 String hql = "update TB_OPERATION set operation_name=:operation_name,status=:status,modify_by=:modify_by,modify_date=:modify_date"
												+ " where id=:id";
							                                   
							    	  Query query = session1.createQuery(hql)
							    			  	.setString("operation_name",operation_name)
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
					return new ModelAndView("redirect:Operation");
				}

				
				
				// -------------------------Delete Operation  -------------------------------------
				/*@RequestMapping(value = "/Operation_delete" , method = RequestMethod.POST)
				public @ResponseBody ModelAndView Operation_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					List<String> liststr = new ArrayList<String>();
					try {
						 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						 Transaction tx = sessionHQL.beginTransaction();
						 
						String hqlUpdate = "delete from TB_OPERATION where id=:id";
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
					return new ModelAndView("redirect:Operation");
			}*/
				
				@RequestMapping(value = "/Operation_delete", method = RequestMethod.POST)
				public @ResponseBody ModelAndView Operation_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					 String  roleid = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
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
						 
						 String hqlUpdate = "update TB_OPERATION set modify_by=:modify_by,modify_date=:modify_date,status=:status"
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
					return new ModelAndView("redirect:Operation");
				}
			
		
			 
}
