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
import com.dao.psg.Master.Course_InstituteDAO;
import com.models.psg.Master.TB_PSG_MSTR_COURSE_INSTITUTE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Course_InstituteController {

Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Course_InstituteDAO CIdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Course_InstituteUrl", method = RequestMethod.GET)
	 public ModelAndView Course_InstituteUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		} 
		 
		
		 ArrayList<ArrayList<String>> list = CIdao.search_Course_Institute("0","","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 Mmap.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
		 
		 return new ModelAndView("Course_InstituteTiles");
	 }
	 
	 
	 @RequestMapping(value = "/Course_InstituteAction",method=RequestMethod.POST)
		public ModelAndView Course_InstituteAction(@ModelAttribute("Course_InstituteCMD") TB_PSG_MSTR_COURSE_INSTITUTE com,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}  
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String course_institute = request.getParameter("course_institute").trim();
				 String status = request.getParameter("status");
				 String category = request.getParameter("category");
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					
					if (com.getCategory() == "0" || com.getCategory() == null || com.getCategory().equals(null)) {
							model.put("msg", "Please Select Category");
							return new ModelAndView("redirect:Course_InstituteUrl");
						}
					 if (course_institute.equals("") || course_institute.equals("null") || course_institute.equals(null)) {
							model.put("msg", "Please Enter Course Institute");
							return new ModelAndView("redirect:Course_InstituteUrl");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Course_InstituteUrl");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_COURSE_INSTITUTE where course_institute=:course_institute and status=:status and id !=:id");
					q0.setParameter("course_institute", course_institute);  
					q0.setParameter("status",status);
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setCategory(category);
						com.setCourse_institute(course_institute);
						com.setStatus(status);
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
					e.printStackTrace();
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
				return new ModelAndView("redirect:Course_InstituteUrl");
			}
	 
	 
	 @RequestMapping(value = "/admin/Search_Course_Institute_Master", method = RequestMethod.POST)
		public ModelAndView Search_Course_Institute_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "category1", required = false) String category1 ,
				@RequestParam(value = "course_institute1", required = false) String course_institute1 ,@ModelAttribute("status1") String status)  {
		
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
		 
		 
		 		ArrayList<ArrayList<String>> list = CIdao.search_Course_Institute(category1,course_institute1,status);
		 		Mmap.put("category1", category1);
				Mmap.put("course_institute1", course_institute1);
				Mmap.put("status2", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
			    Mmap.put("list", list);
			   return new ModelAndView("Course_InstituteTiles","Course_InstituteCMD",new TB_PSG_MSTR_COURSE_INSTITUTE());
			   
		}
	
				
	 @RequestMapping(value = "/Edit_course_institute",method=RequestMethod.POST)
		public ModelAndView Edit_course_institute(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
		 String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
		 TB_PSG_MSTR_COURSE_INSTITUTE CI = CIdao.getCourse_InstituteByid(Integer.parseInt(updateid));
				Mmap.put("Edit_course_instituteCMD", CI);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				 Mmap.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Course_InstituteTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_course_institute_Action", method = RequestMethod.POST)
		public ModelAndView Edit_course_institute_Action(@ModelAttribute("Edit_course_instituteCMD") TB_PSG_MSTR_COURSE_INSTITUTE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			
			
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String course_institute = request.getParameter("course_institute").trim();
			String status = request.getParameter("status");
			String category = request.getParameter("category");
			
			
				if (category == "0" || category == null || category.equals(null)) {
					TB_PSG_MSTR_COURSE_INSTITUTE CI = CIdao.getCourse_InstituteByid(id);
					model.put("Edit_course_instituteCMD", CI);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
		        	model.put("msg", "Please Enter Course Institute");
					return new ModelAndView("Edit_Course_InstituteTiles");
				}	
			if (course_institute.equals("") || course_institute.equals("null")|| course_institute.equals(null)) {
				TB_PSG_MSTR_COURSE_INSTITUTE CI = CIdao.getCourse_InstituteByid(id);
				model.put("Edit_course_instituteCMD", CI);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
	        	model.put("msg", "Please Enter Course Institute");
				return new ModelAndView("Edit_Course_InstituteTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_COURSE_INSTITUTE CI = CIdao.getCourse_InstituteByid(id);
				model.put("Edit_course_instituteCMD", CI);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getServiceCategoryList",pcommon.getServiceCategoryListJCO());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Course_InstituteTiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_COURSE_INSTITUTE where course_institute=:course_institute and status=:status and id !=:id");
						q0.setParameter("course_institute", course_institute);  
						q0.setParameter("status", status);
						q0.setParameter("id", id); 
						
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_COURSE_INSTITUTE set category=:category,course_institute=:course_institute,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			    .setString("category",category)
					    			  	.setString("course_institute",course_institute)
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
			return new ModelAndView("redirect:Course_InstituteUrl");
		}

		
		@RequestMapping(value = "/delete_course_institute", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_course_institute(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_PSG_MSTR_COURSE_INSTITUTE set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Course_InstituteUrl");
		}
}
