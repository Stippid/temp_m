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

import com.dao.psg.Master.DivDAO;
import com.dao.psg.Master.Subject_Dao;
import com.models.psg.Master.TB_DIV_GRADE;
import com.models.psg.Transaction.TB_PSG_CENSUS_SUBJECT;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Subject_Controller {
	
	
	
	@Autowired
	private Subject_Dao sbdao;
	
	
	 @RequestMapping(value = "/admin/Subject", method = RequestMethod.GET)
	 public ModelAndView Subject(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//		 	if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//				return new ModelAndView("redirect:bodyParameterNotAllow");
//			}
		
		 Mmap.put("msg", msg);		 
		 ArrayList<ArrayList<String>> list = sbdao.search_sub_details("");
		 Mmap.put("list", list);
		 //Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("SubjectTiles");
	 }
		@RequestMapping(value = "/Sub_Action", method = RequestMethod.POST)
		public ModelAndView div_Action(@ModelAttribute("Sub_CMD") TB_PSG_CENSUS_SUBJECT dv, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//			 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//				if(request.getHeader("Referer") == null ) {
//					msg = "";
//				}
			int id = dv.getId() > 0 ? dv.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
	 		String subject = request.getParameter("subject").trim();
	 		String status = request.getParameter("status");
	 		
						
	 		 if (subject.equals("") || subject.equals("null")|| subject.equals(null)) {
	 			Mmap.put("msg", "Please Enter DivGrade Name");
				 return new ModelAndView("redirect:DivGrade");
				}
			
			try{
				Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_CENSUS_SUBJECT where description=:description  and id !=:id");
				q0.setParameter("description", subject);  
				
				q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					dv.setCreated_by(username);
					dv.setCreated_on(date);
					if (c == 0) {
						dv.setDescription(subject);
						dv.setCreated_by(username);
						dv.setCreated_on(new Date());
							
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
			return new ModelAndView("redirect:Subject");
		}
		
		//..............Search........................
		@RequestMapping(value = "/admin/getSearchSubjectMaster", method = RequestMethod.POST)
		public ModelAndView getSearchSubjectMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "subject1", required = false) String subject1)

		{
			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//			 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//				if(request.getHeader("Referer") == null ) {
//					msg = "";
//				}
			ArrayList<ArrayList<String>> list = sbdao.search_sub_details(subject1);
				Mmap.put("subject1", subject1);
				Mmap.put("list", list);
			return new ModelAndView("SubjectTiles","Sub_CMD",new TB_PSG_CENSUS_SUBJECT());
		}
		//..............edit.............
		@RequestMapping(value = "/Edit_Subject",method=RequestMethod.POST)
		public ModelAndView Edit_Subject(@ModelAttribute("updateid") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//			 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//				if(request.getHeader("Referer") == null ) {
//					msg = "";
//				}
			
			
			
			 TB_PSG_CENSUS_SUBJECT divDetails = sbdao.getsubdtlByid(Integer.parseInt(updateid));
			    Mmap.put("Edit_subCMD", divDetails);
			    Mmap.put("subject1", divDetails.getDescription());
			  
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_SubTiles");
		}
		
		
		@RequestMapping(value = "/Edit_subAction", method = RequestMethod.POST)
		public ModelAndView Edit_subAction(@ModelAttribute("Edit_subCMD") TB_PSG_CENSUS_SUBJECT ds,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//			 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//				if(request.getHeader("Referer") == null ) {
//					msg = "";
//				}
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			 String subject = request.getParameter("subject").trim();
			
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
		
	        
//				        if (div.equals("") || div.equals("null")|| div.equals(null)) {
//				        	TB_DIV_GRADE divDetails = sbdao.getdivdtlByid((id));
//				        	model.put("Edit_divCMD", divDetails);
//				        	model.put("msg", "Please Enter DivGrade");
//						return new ModelAndView("Edit_DivTiles");
//						}	
				    	
				 try {
						
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_CENSUS_SUBJECT where description=:description  and id !=:id");
					 q0.setParameter("description", subject);  
						q0.setParameter("id", id);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PSG_CENSUS_SUBJECT set description=:description"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("description",subject)
						    			 
											//.setString("modify_by", username).setDate("modify_date", new Date())
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
			return new ModelAndView("redirect:Subject");
		}
		
		@RequestMapping(value = "/deletesubMasterURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deletedivMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//			 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//				if(request.getHeader("Referer") == null ) {
//					msg = "";
//				}
			
			
			
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "delete  from  TB_PSG_CENSUS_SUBJECT "
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate)
						
						.setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Subject");
		}

}
