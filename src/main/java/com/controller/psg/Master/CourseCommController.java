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
import com.dao.psg.Master.CourseDAO;
import com.models.psg.Master.TB_BANK;
import com.models.psg.Master.TB_COURSE;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class CourseCommController {
			
	@Autowired
	private CourseDAO bndao;
	
	 @Autowired
	 RoleBaseMenuDAO roledao;
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	 @RequestMapping(value = "/admin/CourseCommUrl", method = RequestMethod.GET)
	 public ModelAndView CourseCommUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 Mmap.put("list", bndao.search_course_dtl("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("CourseCommTiles");
	 }
			 

			 @RequestMapping(value = "/CourseAction", method = RequestMethod.POST)
				public ModelAndView CourseAction(@ModelAttribute("CourseCMD") TB_COURSE com, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				 String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					int Id = com.getId() > 0 ? com.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String courseName = request.getParameter("course_name").trim();
					String status = request.getParameter("status");
					
					if (courseName.equals("")  || courseName.equals(null)  || courseName.equals("null")) {
		  				model.put("msg", "Please Enter Course Name");
		  				return new ModelAndView("redirect:CourseCommUrl");
		  			}
					
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:CourseCommUrl");
						}
					
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					
					try{
						
						Query q0 = sessionHQL.createQuery("select count(Id) from TB_COURSE where course_name=:name and status=:status and Id !=:Id");
						q0.setParameter("name", com.getCourse_name());  
						q0.setParameter("status", com.getStatus());
						q0.setParameter("Id", Id);  
						Long c = (Long) q0.uniqueResult();

						if (Id == 0) {
							com.setCreated_by(username);
							com.setCreated_dt(date);
							com.setCourse_name(courseName);
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
					return new ModelAndView("redirect:CourseCommUrl");
				}
			 
				@RequestMapping(value = "/admin/getSearchbankMaster", method = RequestMethod.POST)
				public ModelAndView getSearchbankMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "bank_name1", required = false) String bank_name1,
						@RequestParam(value = "bank_abb1", required = false) String bank_abb1,
						@RequestParam(value = "ifsc1", required = false) String ifsc1,
						@RequestParam(value = "bank_address1", required = false) String bank_address1,
						@RequestParam(value = "bank_phone1", required = false) String bank_phone1){
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Bank", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
						Mmap.put("bank_name1", bank_name1);
						Mmap.put("bank_abb1", bank_abb1);
						Mmap.put("ifsc1", ifsc1);
						Mmap.put("bank_address1", bank_address1);
						Mmap.put("bank_phone1", bank_phone1);
						//ArrayList<ArrayList<String>> list = bndao.search_bnk_dtl(bank_name1,bank_abb1,ifsc1,bank_address1,bank_phone1);
					    //Mmap.put("list", list);
					return new ModelAndView("BankTiles","BankCMD",new TB_BANK());
				}
				
				@RequestMapping(value = "/admin/getSearchCourseMaster", method = RequestMethod.POST)
				public ModelAndView getSearchCourseMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "course_name1", required = false) String course_name1,
						@RequestParam(value = "status1", required = false) String status1){
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
						Mmap.put("course_name2", course_name1);
						Mmap.put("status1", status1);
						Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						ArrayList<ArrayList<String>> list = bndao.search_course_dtl(course_name1,status1);
					    Mmap.put("list", list);
					return new ModelAndView("CourseCommTiles","CourseCMD",new TB_COURSE());
				}
		
				
				@RequestMapping(value = "/Edit_Bank",method=RequestMethod.POST)
				public ModelAndView Edit_Bank(@ModelAttribute("id2") String updateid,ModelMap Mmap,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
						HttpSession sessionEdit,HttpServletRequest request) {
					
					String roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Bank", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					     TB_BANK authDetails = bndao.getbankByid(Integer.parseInt(updateid));
						 Mmap.put("Edit_Bank_CMD", authDetails);
						 Mmap.put("msg", msg);
					return new ModelAndView("Edit_BankTiles");
				}
				@RequestMapping(value = "/Edit_Course",method=RequestMethod.POST)
				public ModelAndView Edit_Course(@ModelAttribute("id2") String updateid,ModelMap Mmap,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
						HttpSession sessionEdit) {
					
					String roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					TB_COURSE authDetails = bndao.getCourseByid(Integer.parseInt(updateid));
						 Mmap.put("Edit_Course_CMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", msg);
					return new ModelAndView("Edit_CourseTiles");
				}
				
				@RequestMapping(value = "/Edit_Bank_Action", method = RequestMethod.POST)
				public ModelAndView Edit_Bank_Action(@ModelAttribute("Edit_Bank_CMD") TB_BANK rs,
						HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Bank", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
			        
			        String bank_name = request.getParameter("bank_name");

					if (rs.getBank_name().equals("") || rs.getBank_name().equals("null") || rs.getBank_name().equals(null)) {
						Mmap.put("msg", "Please Enter Bank Name");
						Mmap.put("id2", id);
						return new ModelAndView("redirect:Edit_Bank");
					}	
					if (rs.getBank_abb().equals("") || rs.getBank_abb().equals("null")
							|| rs.getBank_abb().equals(null)){
						Mmap.put("msg", "Please Enter Bank Abbreviation");
						Mmap.put("id2", id);
						return new ModelAndView("redirect:Edit_Bank");
					}
					if (rs.getIfsc().equals("") || rs.getIfsc().equals("null") || rs.getIfsc().equals(null)) {
						Mmap.put("msg", "Please Enter IFSC");
						Mmap.put("id2", id);
						return new ModelAndView("redirect:Edit_Bank");
					}	
					if (rs.getBank_address().equals("") || rs.getBank_address().equals("null") || rs.getBank_address().equals(null)) {
						Mmap.put("msg", "Please Enter Bank Address");
						Mmap.put("id2", id);
						return new ModelAndView("redirect:Edit_Bank");
					}				
					
					
			        try {
						
			        	Query q0 = session1.createQuery("select count(id) from TB_BANK where bank_name=:bank_name  and id !=:id");
						q0.setParameter("bank_name", bank_name);  
						q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_BANK set bank_name=:bank_name,bank_abb=:bank_abb,ifsc=:ifsc,bank_address=:bank_address,bank_phone=:bank_phone,modified_by=:modified_by,modified_date=:modified_date"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("bank_name",rs.getBank_name())
						    			  	.setString("bank_abb", rs.getBank_abb())
											.setString("ifsc", rs.getIfsc())
											.setString("bank_address",rs.getBank_address())
											.setBigInteger("bank_phone", rs.getBank_phone())
											.setString("modified_by", username).setDate("modified_date", new Date())
											.setInteger("id",id);
						                    msg = query.executeUpdate() > 0 ? "1" :"0";
						                    tx.commit(); 
						                    
						                    if(msg == "1") {
						                    	Mmap.put("msg", "Data Updated Successfully.");
						                    }
						                    else {
						                    	Mmap.put("msg", "Data Not Updated.");
						                    }
							}
							else {
								Mmap.put("msg", "Data already Exist.");
							}
						
					  }catch(RuntimeException e){
				              try{
				                      tx.rollback();
				                      Mmap.put("msg", "roll back transaction");
				              }catch(RuntimeException rbe){
				            	  Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
				              }
				              throw e;
				             
						}finally{
							if(session1!=null){
								session1.close();
							}
						}
					return new ModelAndView("redirect:Bank");
				}
				@RequestMapping(value = "/Edit_Course_Action", method = RequestMethod.POST)
				public ModelAndView Edit_Course_Action(@ModelAttribute("Edit_Course_CMD") TB_COURSE rs,
						HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
					
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					
					
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
			        
			        String course_name = request.getParameter("course_name").trim();

					if (course_name.equals("") || course_name.equals("null") || course_name.equals(null)) {
						TB_COURSE authDetails = bndao.getCourseByid(id);
						 Mmap.put("Edit_Course_CMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", "Please Enter Course Name");
						return new ModelAndView("Edit_CourseTiles");
					}	
					/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
						TB_COURSE authDetails = bndao.getCourseByid(id);
						 Mmap.put("Edit_Course_CMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", "Only Select Active Status");
						return new ModelAndView("Edit_CourseTiles");
					}*/
					
			        try {
						
			        	Query q0 = session1.createQuery("select count(id) from TB_COURSE where course_name=:course_name  and id !=:id");
						q0.setParameter("course_name", course_name);  
						q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_COURSE set course_name=:course_name,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("course_name",rs.getCourse_name())
						    			  	.setString("status", rs.getStatus())
											.setString("modified_by", username).setDate("modified_dt", new Date())
											.setInteger("id",id);
						                    msg = query.executeUpdate() > 0 ? "1" :"0";
						                    tx.commit(); 
						                    
						                    if(msg == "1") {
						                    	Mmap.put("msg", "Data Updated Successfully.");
						                    }
						                    else {
						                    	Mmap.put("msg", "Data Not Updated.");
						                    }
							}
							else {
								Mmap.put("msg", "Data already Exist.");
							}
						
					  }catch(RuntimeException e){
				              try{
				                      tx.rollback();
				                      Mmap.put("msg", "roll back transaction");
				              }catch(RuntimeException rbe){
				            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
				              }
				              throw e;
				             
						}finally{
							if(session1!=null){
								session1.close();
							}
						}
					return new ModelAndView("redirect:CourseCommUrl");
				}
				
				@RequestMapping(value = "/deletebankMasterURL", method = RequestMethod.POST)
				public @ResponseBody ModelAndView deletebankMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					String roleid = sessionA.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Bank", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					List<String> liststr = new ArrayList<String>();
					try {
						 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						 Transaction tx = sessionHQL.beginTransaction();
						 
						String hqlUpdate = "delete from TB_BANK where id=:id";
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
					return new ModelAndView("redirect:Bank");
				}
				
			
				@RequestMapping(value = "/deleteCourseMasterURL", method = RequestMethod.POST)
				public @ResponseBody ModelAndView deleteCourseMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					

					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);
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
						 
						 String hqlUpdate = "update TB_COURSE set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
					return new ModelAndView("redirect:CourseCommUrl");
				}
}
