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
import com.dao.psg.Master.RelationDAO;
import com.models.psg.Master.TB_RELATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Relation_Controller {
    
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private RelationDAO Rdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/RelationUrl", method = RequestMethod.GET)
	 public ModelAndView RelationUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "status1", required = false) String status ) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);
		 ArrayList<ArrayList<String>> list = Rdao.search_relation("","active");
		
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("RelationTiles");
	 }
	 
	 /******************************Save For Relation***********************************/
	 
	 @RequestMapping(value = "/RelationAction",method=RequestMethod.POST)
		public ModelAndView RelationAction(@ModelAttribute("RelationCMD") TB_RELATION RT,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}			 
				int id = RT.getId() > 0 ? RT.getId() : 0;				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String relation_name = request.getParameter("relation_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				 if (relation_name.equals("") || relation_name.equals("null")|| relation_name.equals(null)) {
					 model.put("msg", "Please Enter Relation Name");
						return new ModelAndView("redirect:RelationUrl");
					}
				 if (RT.getStatus() == "inactive" || RT.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:RelationUrl");
					}
													
				try{					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_RELATION where relation_name=:relation_name and status=:status and id !=:id");
					q0.setParameter("relation_name", RT.getRelation_name());
					q0.setParameter("status", RT.getStatus());
					q0.setParameter("id", id); 
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						RT.setCreated_by(username);
						RT.setCreated_date(date);
						RT.setRelation_name(relation_name);
						if (c == 0) {
							sessionHQL.save(RT);
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
				return new ModelAndView("redirect:RelationUrl");
			} 

		/******************************Search For Relation***********************************/
		
		@RequestMapping(value = "/admin/GetSearch_Relation", method = RequestMethod.POST)
		public ModelAndView GetSearch_Relation(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "relation_name2", required = false) String relation_name,
				@RequestParam(value = "status2", required = false) String status ){
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			ArrayList<ArrayList<String>> list = Rdao.search_relation(relation_name,status);
			
//				if(!relation_name.equals("")) {
//					
//					
//				}
				Mmap.put("relation_name2", relation_name);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("list", list);
			return new ModelAndView("RelationTiles","RelationCMD",new TB_RELATION());
		}

		
		 /******************************Delete For Relation***********************************/
	    /*
			@RequestMapping(value = "/Delete_Relation" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Delete_Relation(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_RELATION where id=:id";
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
				return new ModelAndView("redirect:RelationUrl");
			}*/
		
		@RequestMapping(value = "/Delete_Relation", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Relation(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
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
				 
				 String hqlUpdate = "update TB_RELATION set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
			return new ModelAndView("redirect:RelationUrl");
		}
	 		
			/******************************Update For Relation***********************************/ 

			
			@RequestMapping(value = "/Edit_RelationUrl" , method = RequestMethod.POST)
			public ModelAndView Edit_RelationUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {	
						
				 String  roleid = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				TB_RELATION authDetails = Rdao.getRelationByid(Integer.parseInt(updateid));
				Mmap.put("Edit_RelationCMD", authDetails);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
				Mmap.put("msg", msg);
				return new ModelAndView("Edit_RelationTiles");
			}
			
			@RequestMapping(value = "/Edit_RelationAction", method = RequestMethod.POST)
			public ModelAndView Edit_RelationAction(@ModelAttribute("Edit_RelationCMD") TB_RELATION rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String relation_name = request.getParameter("relation_name").trim();
				String status = request.getParameter("status");
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
		        
		        if (relation_name.equals("") || relation_name.equals("null")|| relation_name.equals(null)) {
		        	TB_RELATION authDetails = Rdao.getRelationByid((id));
					model.put("Edit_RelationCMD", authDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Enter Relation Name");
					//model.put("id2", id);
					return new ModelAndView("Edit_RelationTiles");
				}
		       /* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
		        	TB_RELATION authDetails = Rdao.getRelationByid((id));
					model.put("Edit_RelationCMD", authDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_RelationTiles");
				}*/
		        
					 try {
						
						 Query q0 = session1.createQuery("select count(id) from TB_RELATION where relation_name=:relation_name and status=:status  and id !=:id");
							q0.setParameter("relation_name", relation_name);
							q0.setParameter("status", status);
							q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_RELATION set relation_name=:relation_name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("relation_name",relation_name)
						    			  	.setString("status",status)
											.setString("modified_by", username)
											.setDate("modified_date", new Date())
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
			                      model.put("msg", "Couldn’t roll back transaction " + rbe);
			              }
			              throw e;
			             
					}finally{
						if(session1!=null){
							session1.close();
						}
					}
				return new ModelAndView("redirect:RelationUrl");
			}

	}
