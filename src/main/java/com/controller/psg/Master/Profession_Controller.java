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
import com.dao.psg.Master.ProfessionDAO;

import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_PROFESSION;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Profession_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private ProfessionDAO PRO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	

	// -------------------------------Profession For page Open -------------------------------------
		@RequestMapping(value = "/admin/Profession", method = RequestMethod.GET)
		 public ModelAndView Profession(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			
			List<Map<String, Object>> list = PRO.search_Profession("","active");
			Mmap.put("list", list);
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			 return new ModelAndView("ProfessionTiles");
		 }
	
		
		// -------------------------------Profession save -------------------------------------
		
		
		 @RequestMapping(value = "/ProfessionAction",method=RequestMethod.POST)
			public ModelAndView ProfessionAction(@ModelAttribute("ProfessionCmd") TB_PROFESSION pr,
					 @RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request,ModelMap model,HttpSession session) {
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				 
					int id = pr.getId() > 0 ? pr.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String profession_name = request.getParameter("profession_name").trim();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
//					 if (pr.getProfession_name().equals("") || pr.getProfession_name().equals("null")
//								|| pr.getProfession_name().equals(null)) {
//							model.put("msg", "Please Enter Profession Name");
//							return new ModelAndView("redirect:Profession");
//						}
					 if (profession_name.trim().equals("") || profession_name.equals("null")|| profession_name.equals(null)) {
						 model.put("msg", "Please Enter Profession Name");
						 return new ModelAndView("redirect:Profession");
						}
					 if (pr.getStatus() == "inactive" || pr.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Profession");
						}
					try{
						
						Query q0 = sessionHQL.createQuery("select count(id) from TB_PROFESSION where profession_name=:profession_name and status=:status and id !=:id");
						q0.setParameter("profession_name", pr.getProfession_name());  
						q0.setParameter("status", pr.getStatus());
						q0.setParameter("id", id);  
						Long c = (Long) q0.uniqueResult();

						if (id == 0) {
							
							pr.setCreated_by(username);
							pr.setCreated_date(date);
							pr.setProfession_name(profession_name);
							if (c == 0) {
								sessionHQL.save(pr);
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
					return new ModelAndView("redirect:Profession");
				}
		 
			// -------------------------SEARCH Profession  -------------------------------------
			
			@RequestMapping(value = "/search_Profession" , method = RequestMethod.POST)
			public ModelAndView search_Profession(ModelMap model, HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "profession_name1", required = false) String profession_name,
					@RequestParam(value="status1",required = false ) String status) {
			
				
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
				List<Map<String, Object>> list = PRO.search_Profession(profession_name,status);
				model.put("list", list);
				model.put("size", list.size());
				model.put("profession_name1", profession_name);
				model.put("list", list);
				model.put("status1", status);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				return new ModelAndView("ProfessionTiles");
			}
	
			
			// -------------------------Edit Profession For page Open -------------------------------------
			@RequestMapping(value = "/edit_Profession",method = RequestMethod.POST )
			public ModelAndView edit_Profession(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
			    
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Profession", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				
				
				TB_PROFESSION OpDetails = PRO.getProfessionByid(Integer.parseInt(updateid));
				model.put("EditProfessionCMD", OpDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", msg);
		
				return new ModelAndView("EditProfessionTiles");
			}
			
			
			// -------------------------Edit Profession Action -------------------------------------
			
			
			@RequestMapping(value = "/EditProfessionAction", method = RequestMethod.POST)
			public ModelAndView EditProfessionAction(@ModelAttribute("EditProfessionCMD") TB_PROFESSION P,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
				
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String profession_name = request.getParameter("profession_name").trim();
				String status = request.getParameter("status");
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
				
		        if (profession_name.equals("") || profession_name.equals("null")|| profession_name.equals(null)) {
		        	TB_PROFESSION OpDetails = PRO.getProfessionByid((id));
					model.put("EditProfessionCMD", OpDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Enter Profession Name");
		        	//model.put("id", id);
					return new ModelAndView("EditProfessionTiles");
				}	
		      /*  if (P.getStatus() == "inactive" || P.getStatus().equals("inactive")) {
		        	TB_PROFESSION OpDetails = PRO.getProfessionByid((id));
					model.put("EditProfessionCMD", OpDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("EditProfessionTiles");
				}*/
				 	try {
						 Query q0 = session1.createQuery("select count(id) from TB_PROFESSION where profession_name=:profession_name and status=:status and id !=:id");
							q0.setParameter("profession_name", profession_name);  
							q0.setParameter("id", id); 
							q0.setParameter("status", status);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PROFESSION set profession_name=:profession_name,status=:status,modify_by=:modify_by,modify_date=:modify_date"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("profession_name",profession_name)
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
				return new ModelAndView("redirect:Profession");
			}

			
			
			
			
			
			// -------------------------Delete Profession  -------------------------------------
			/*@RequestMapping(value = "/Profession_delete" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Profession_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_PROFESSION where id=:id";
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
				return new ModelAndView("redirect:Profession");
		}*/
			
			@RequestMapping(value = "/Profession_delete", method = RequestMethod.POST)
			public @ResponseBody ModelAndView Profession_delete(@ModelAttribute("id1") int id, BindingResult result,
					HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
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

					String hqlUpdate = "update TB_PROFESSION set modify_by=:modify_by,modify_date=:modify_date,status=:status"
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
				return new ModelAndView("redirect:Profession");
			}
}
