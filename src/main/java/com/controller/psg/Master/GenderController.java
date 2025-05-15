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
import com.dao.psg.Master.GenderDAO;
import com.models.psg.Master.TB_GENDER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class GenderController {
	       
	
	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private GenderDAO gddao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Gender", method = RequestMethod.GET)
	 public ModelAndView Gender(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);		 
		// ArrayList<ArrayList<String>> list = gddao.search_gender_details("","0");
		Mmap.put("list", gddao.search_gender_details("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());

		 return new ModelAndView("GenderTiles");
	 }
	 
		@RequestMapping(value = "/gender_Action", method = RequestMethod.POST)
		public ModelAndView gender_Action(@ModelAttribute("gender_CMD") TB_GENDER GN, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			int id = GN.getId() > 0 ? GN.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
	 		String gender_name = request.getParameter("gender_name").trim();
	 		String status = request.getParameter("status");
	 		
			/*if (request.getParameter("gender_name") == "") {
				tx.rollback();
				Mmap.put("msg", "Please Enter Gender Name");
				return new ModelAndView("redirect:Gender");
			}	*/									
	 		 if (gender_name.equals("") || gender_name.equals("null")|| gender_name.equals(null)) {
	 			Mmap.put("msg", "Please Enter Colour Name");
				 return new ModelAndView("redirect:Gender");
				}
			 if (GN.getStatus() == "inactive" || GN.getStatus().equals("inactive")) {
				 Mmap.put("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Gender");
				}
			try{
				Query q0 = sessionHQL.createQuery("select count(id) from TB_GENDER where gender_name=:gender_name and status=:status and id !=:id");
				q0.setParameter("gender_name", gender_name);  
				q0.setParameter("status", status);
				q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					GN.setCreated_by(username);
					GN.setCreated_date(date);
					if (c == 0) {
						GN.setGender_name(gender_name);
						GN.setStatus(status);
						GN.setCreated_by(username);
						GN.setCreated_date(new Date());
							
				        sessionHQL.save(GN);
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
			return new ModelAndView("redirect:Gender");
		}
	//..............Search........................
		@RequestMapping(value = "/admin/getSearchgenderMaster", method = RequestMethod.POST)
		public ModelAndView getSearchgenderMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "gender_name1", required = false) String gender_name1,
                @RequestParam(value = "status1", required = false) String status )

		{
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			ArrayList<ArrayList<String>> list = gddao.search_gender_details(gender_name1,status);
				Mmap.put("gender_name1", gender_name1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			
					Mmap.put("list", list);
			return new ModelAndView("GenderTiles","gender_CMD",new TB_GENDER());
		}

		//..............edit.............
		@RequestMapping(value = "/Edit_Gender",method=RequestMethod.POST)
		public ModelAndView Edit_Gender(@ModelAttribute("updateid") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			    TB_GENDER genDetails = gddao.getgenderdtlByid(Integer.parseInt(updateid));
			    Mmap.put("Edit_genderCMD", genDetails);
			    Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_GenderTiles");
		}
		
		
		@RequestMapping(value = "/Edit_genderAction", method = RequestMethod.POST)
		public ModelAndView Edit_genderAction(@ModelAttribute("Edit_genderCMD") TB_GENDER rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			 String gender_name = request.getParameter("gender_name").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
			/*if (request.getParameter("gender_name") == "") {
				model.put("msg", "Please Enter Gender Name");
				model.put("id", id);
				return new ModelAndView("redirect:Edit_Gender");
			}	*/	
	        
				        if (gender_name.equals("") || gender_name.equals("null")|| gender_name.equals(null)) {
				        	TB_GENDER genDetails = gddao.getgenderdtlByid((id));
				        	model.put("Edit_genderCMD", genDetails);
				        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				        	model.put("msg", "Please Enter Gender Name");
							//model.put("id2", id);
							return new ModelAndView("Edit_GenderTiles");
						}	
				    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
				    		TB_GENDER genDetails = gddao.getgenderdtlByid((id));
				        	model.put("Edit_genderCMD", genDetails);
				        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("Edit_GenderTiles");
						}*/
				 try {
						
					 Query q0 = session1.createQuery("select count(id) from TB_GENDER where gender_name=:gender_name and status=:status and  id !=:id");
						q0.setParameter("gender_name", gender_name);  
						q0.setParameter("status", status);  
						q0.setParameter("id", id);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_GENDER set gender_name=:gender_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("gender_name",gender_name)
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
			return new ModelAndView("redirect:Gender");
		}
		
	/*	@RequestMapping(value = "/deletegenderMasterURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deletecourse_typeMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_GENDER where id=:id";
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
			return new ModelAndView("redirect:Gender");
		}*/
		
		@RequestMapping(value = "/deletegenderMasterURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deletegenderMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
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
				 
				 String hqlUpdate = "update TB_GENDER set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
			return new ModelAndView("redirect:Gender");
		}
}
