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
import com.dao.psg.Master.Type_of_EmploymentDAO;
import com.models.psg.Master.TB_TYPE_OF_EMPLOYMENT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Type_of_EmploymentController {
	
	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private Type_of_EmploymentDAO Emdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Type_of_Employment", method = RequestMethod.GET)
	 public ModelAndView Type_of_Employment(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 
		 Mmap.put("msg", msg);
		 Mmap.put("list",  Emdao.search_type_of_employment("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("Type_of_EmploymentTiles");
	 }
	 
	 /******************************Save For Employment***********************************/
	
	 @RequestMapping(value = "/Type_of_empAction",method=RequestMethod.POST)
		public ModelAndView Type_of_empAction(@ModelAttribute("Type_of_empCMD") TB_TYPE_OF_EMPLOYMENT EP,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
					
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
				int id = EP.getId() > 0 ? EP.getId() : 0;				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String name = request.getParameter("name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();

				 if (name.equals("") || name.equals("null")|| name.equals(null)) {
					 model.put("msg", "Please Enter Name");
					 return new ModelAndView("redirect:Type_of_Employment");
					}
				 if (EP.getStatus() == "inactive" || EP.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Type_of_Employment");
					}
				 				
				try{					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_TYPE_OF_EMPLOYMENT where name=:name and status=:status  and id !=:id");
					q0.setParameter("name", EP.getName());  					
					q0.setParameter("id", id); 
					q0.setParameter("status", EP.getStatus());
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						EP.setCreated_by(username);
						EP.setCreated_date(date);
						EP.setName(name);
						if (c == 0) {
							sessionHQL.save(EP);
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
				return new ModelAndView("redirect:Type_of_Employment");
			}
	
	 /******************************Search For Employment***********************************/
		
		@RequestMapping(value = "/admin/GetSearch_Type_of_Employment", method = RequestMethod.POST)
		public ModelAndView GetSearch_Type_of_Employment(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "name1", required = false) String name,
				@RequestParam(value ="status1",required = false) String status){
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
				if(!name.equals("")) {
					Mmap.put("name1", name);
				}
				ArrayList<ArrayList<String>> list = Emdao.search_type_of_employment(name,status);
					Mmap.put("list", list);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					
			return new ModelAndView("Type_of_EmploymentTiles","Type_of_empCMD",new TB_TYPE_OF_EMPLOYMENT());
		}

		
		 /******************************Delete For Employment***********************************/
	    
			/*@RequestMapping(value = "/Delete_Type_of_Employment" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Delete_Type_of_Employment(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_TYPE_OF_EMPLOYMENT where id=:id";
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
				return new ModelAndView("redirect:Type_of_Employment");
			}*/
		@RequestMapping(value = "/Delete_Type_of_Employment", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Type_of_Employment(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
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
				 
				 String hqlUpdate = "update TB_TYPE_OF_EMPLOYMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
			return new ModelAndView("redirect:Type_of_Employment");
		}
			/******************************Update For Employment***********************************/ 

			
			@RequestMapping(value = "/Edit_Type_of_EmploymentUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Type_of_EmploymentUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {	
				 String  roleid = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				 	
				TB_TYPE_OF_EMPLOYMENT authDetails = Emdao.getTypeofemploymentByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Type_of_empCMD", authDetails);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
				return new ModelAndView("Edit_Type_of_EmploymentTiles");
			}
			
			
			
			@RequestMapping(value = "/Edit_Type_of_empAction", method = RequestMethod.POST)
			public ModelAndView Edit_Type_of_empAction(@ModelAttribute("Edit_Type_of_empCMD") TB_TYPE_OF_EMPLOYMENT rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name").trim();
				String status = request.getParameter("status");
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
				
		        if (name.equals("") || name.equals("null")|| name.equals(null)) {
		        	TB_TYPE_OF_EMPLOYMENT authDetails = Emdao.getTypeofemploymentByid((id));
		        	model.put("Edit_Type_of_empCMD", authDetails);
		        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Enter Name");
					//model.put("id2", id);
					return new ModelAndView("Edit_Type_of_EmploymentTiles");
				}
		       /* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
		        	TB_TYPE_OF_EMPLOYMENT authDetails = Emdao.getTypeofemploymentByid((id));
		        	model.put("Edit_Type_of_empCMD", authDetails);
		        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_Type_of_EmploymentTiles");
				}*/
				 	try {
						 Query q0 = session1.createQuery("select count(id) from TB_TYPE_OF_EMPLOYMENT where name=:name and status=:status and id !=:id");
							q0.setParameter("name", name);  
							q0.setParameter("id", id); 
							q0.setParameter("status", status);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_TYPE_OF_EMPLOYMENT set name=:name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("name",name)
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
				return new ModelAndView("redirect:Type_of_Employment");
			}	
	}

