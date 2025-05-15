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
import com.dao.psg.Master.Casuality_Dao;

import com.models.psg.Master.TB_PSG_MSTR_CASUALITY1;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY2;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY3;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Casuality_Controller {
	
    Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	private Casuality_Dao C1;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Casuality1_Url", method = RequestMethod.GET)
	 public ModelAndView Casuality1_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		 Mmap.put("list", C1.search_Casuality("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("Casuality1Tiles");
	 }

	@RequestMapping(value = "/Casuality1_Action", method = RequestMethod.POST)
	public ModelAndView Casuality1_Action(@ModelAttribute("Casuality1_CMD") TB_PSG_MSTR_CASUALITY1 com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String casuality1 = request.getParameter("casuality1").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (casuality1.equals("") || casuality1.equals("null")|| casuality1.equals(null)) {
			 model.put("msg", "Please Enter Casuality.");
			 return new ModelAndView("redirect:awardCategoryUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:awardCategoryUrl");
			}
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY1 where casuality1=:casuality1");
			q0.setParameter("casuality1", casuality1);  
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setCasuality1(casuality1);
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
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
		return new ModelAndView("redirect:Casuality1_Url");
	}
	
	@RequestMapping(value = "/admin/getCasuality1Search", method = RequestMethod.POST)
	public ModelAndView getCasuality1Search(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "casuality_v1", required = false) String casuality_v1,
			@RequestParam(value = "status1", required = false) String status1){
		
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = C1.search_Casuality(casuality_v1,status1);
			Mmap.put("casuality_s1", casuality_v1);
			Mmap.put("status1", status1);
		    Mmap.put("list", list);
		return new ModelAndView("Casuality1Tiles","Casuality1_CMD",new TB_PSG_MSTR_CASUALITY1());
	}
	
	
	@RequestMapping(value = "/Edit_Casuality1",method = RequestMethod.POST)
	public ModelAndView Edit_Casuality1(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		TB_PSG_MSTR_CASUALITY1 authDetails = C1.getCasuality1Byid(Integer.parseInt(updateid));
			 Mmap.put("Edit_Casuality1_CMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_Casuality1_Tiles");
	}
	
	@RequestMapping(value = "/Edit_Casuality1_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Casuality1_Action(@ModelAttribute("Edit_Casuality1_CMD") TB_PSG_MSTR_CASUALITY1 rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        
       String casuality1 = request.getParameter("casuality1").trim();

		if (casuality1.equals("") || casuality1.equals("null") || casuality1.equals(null)) {
			TB_PSG_MSTR_CASUALITY1 authDetails = C1.getCasuality1Byid(id);
			 Mmap.put("Edit_Casuality1_CMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Please Enter Casuality1.");
			return new ModelAndView("Edit_Casuality1_Tiles");
		}	
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_CASUALITY1 authDetails = C1.getCasuality1Byid(id);
			 Mmap.put("Edit_Casuality1_CMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Casuality1_Tiles");
		}*/
		
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY1 where casuality1=:casuality1  and id !=:id");
			q0.setParameter("casuality1", casuality1);  
			q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_CASUALITY1 set casuality1=:casuality1,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("casuality1",casuality1)
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
		return new ModelAndView("redirect:Casuality1_Url");
	}
	 @RequestMapping(value = "/deleteCasuality1URL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deleteCasuality1URL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 

		 List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_PSG_MSTR_CASUALITY1 set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Casuality1_Url");
		}
	 
	 
	 ///---------------CASUALITY 2----------
	 
	 
		@RequestMapping(value = "/admin/Casuality2_Url", method = RequestMethod.GET)
		 public ModelAndView Casuality2_Url(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 
			 Mmap.put("list", C1.Search_Casuality2(0,"","active"));
			 Mmap.put("getCasuality1", mcommon.getCasuality1());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
			 return new ModelAndView("Casuality2Tiles");
		 }
		

		 @RequestMapping(value = "/Casuality2_Action",method=RequestMethod.POST)
			public ModelAndView Casuality2_Action(@ModelAttribute("Casuality2_CMD") TB_PSG_MSTR_CASUALITY2 st,HttpServletRequest request,
					
				ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
						 
					int id = st.getId() > 0 ? st.getId(): 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String casuality2 = request.getParameter("casuality2").trim();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					 

							if (st.getCasuality1_id() == 0 ) {
								model.put("msg", "Please Select Casuality1.");
								return new ModelAndView("redirect:Casuality2_Url");
							}
							if (casuality2.equals("") || casuality2.equals("null") || casuality2.equals(null)) {
								model.put("msg", "Please Enter Casuality2.");
								return new ModelAndView("redirect:Casuality2_Url");
							}
							if (st.getStatus() == "inactive" || st.getStatus().equals("inactive")) {
								model.put("msg", "Only Select Active Status.");
								return new ModelAndView("redirect:Casuality2_Url");
							}
					try{
						
						
						Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY2 where casuality2=:casuality2 and status=:status and id!=:id");
						q0.setParameter("casuality2", st.getCasuality2());  
						q0.setParameter("status", st.getStatus());
						q0.setParameter("id", id); 
						
						
						Long c = (Long) q0.uniqueResult();

						if (id == 0) {
							st.setCreated_by(username);
							st.setCreated_dt(date);
							st.setCasuality2(casuality2);
							if (c == 0) {
								sessionHQL.save(st);
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
							model.put("msg", "Couldn't roll back transaction " + rbe);
						}
						throw e;
					}finally{
						if(sessionHQL!=null){
						   sessionHQL.close();
						}
					}	
					return new ModelAndView("redirect:Casuality2_Url");
				}
		 
		 @RequestMapping(value = "/admin/getSearch_Casuality2" , method = RequestMethod.POST)
			public ModelAndView getSearch_Casuality2(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "casuality1_id1", required = false) int casuality1_id,
					@RequestParam(value = "casuality2_v2", required = false) String casuality2_v2,
					@RequestParam(value = "status1", required = false) String status1){
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 ArrayList<ArrayList<String>> list = C1.Search_Casuality2(casuality1_id,casuality2_v2,status1);
			 
			 		 Mmap.put("list", list);
			         Mmap.put("casuality1_id1", casuality1_id);
					 Mmap.put("casuality2_s2", casuality2_v2);
					 Mmap.put("status1", status1);
					 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
					 Mmap.put("getCasuality1", mcommon.getCasuality1());
				return new ModelAndView("Casuality2Tiles","Casuality2_CMD",new TB_PSG_MSTR_CASUALITY2());
			}
		 
		 
		    @RequestMapping(value = "/Edit_Casuality2",method = RequestMethod.POST)
			public ModelAndView Edit_Casuality2(@ModelAttribute("id2") String updateid,ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {
				
					String roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}

		    	
		    	TB_PSG_MSTR_CASUALITY2 CDetails = C1.getCasuality2Byid(Integer.parseInt(updateid));
					Mmap.put("Edit_Casuality2_CMD", CDetails);
					Mmap.put("getCasuality1", mcommon.getCasuality1());
					Mmap.put("getStatusMasterList",mcommon.getStatusMasterList());
					Mmap.put("msg", msg);
				return new ModelAndView("Edit_Casuality2_Tiles");
			}
		    
		    @RequestMapping(value = "/Edit_Casuality2_Action", method = RequestMethod.POST)
			public ModelAndView Edit_Casuality2_Action(@ModelAttribute("Edit_Casuality2_CMD") TB_PSG_MSTR_CASUALITY2 rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
		    	 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
		    	
		    	
		    	String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("id"));
				String casuality2 = request.getParameter("casuality2").trim();
				String status = request.getParameter("status");
				
					if (rs.getCasuality1_id() == 0 ) {
						TB_PSG_MSTR_CASUALITY2 CDetails = C1.getCasuality2Byid(id);
						model.put("Edit_Casuality2_CMD", CDetails);
						model.put("getCasuality1", mcommon.getCasuality1());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Select Casuality1.");
						//model.put("id2", id);
						return new ModelAndView("Edit_Casuality2_Tiles");
					}
			
					if (casuality2.equals("") || casuality2.equals("null") || casuality2.equals(null)) {
						TB_PSG_MSTR_CASUALITY2 CDetails = C1.getCasuality2Byid(id);
						model.put("Edit_Casuality2_CMD", CDetails);
						model.put("getCasuality1", mcommon.getCasuality1());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Enter Casuality2.");
//						model.put("id2", id);
						return new ModelAndView("Edit_Casuality2_Tiles");
					}
					
					/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
						TB_PSG_MSTR_CASUALITY2 CDetails = C1.getCasuality2Byid(id);
						model.put("Edit_Casuality2_CMD", CDetails);
						model.put("getCasuality1", mcommon.getCasuality1());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("Edit_Casuality2_Tiles");
			
					}*/
					
					
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
					 try {
						
						 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY2 where casuality1_id=:casuality1_id and casuality2=:casuality2"
						 		+ " and status=:status and id !=:id");
							q0.setParameter("casuality2", casuality2); 
							q0.setParameter("status", status); 
							q0.setParameter("casuality1_id", rs.getCasuality1_id());
							q0.setParameter("id", rs.getId()); 
							Long c = (Long) q0.uniqueResult();
							if(c==0) {
								
								 String hql = "update TB_PSG_MSTR_CASUALITY2 set casuality1_id=:casuality1_id,casuality2=:casuality2,"
								 		+ "modified_by=:modified_by,modified_dt=:modified_dt where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  .setInteger("casuality1_id",rs.getCasuality1_id())
						    			  	.setString("casuality2",casuality2)
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
				return new ModelAndView("redirect:Casuality2_Url");
			}
		    
			@RequestMapping(value = "/delete_Casuality2", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_Casuality2(@ModelAttribute("id1") int id,BindingResult result, 
					HttpServletRequest request, HttpSession session,HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
				
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					
				
				List<String> liststr = new ArrayList<String>();
				
				String username = session.getAttribute("username").toString();
				
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					 String hqlUpdate = "update TB_PSG_MSTR_CASUALITY2 set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
				return new ModelAndView("redirect:Casuality2_Url");
			}
		 ///---------------CASUALITY 3----------
		
		
		
		@RequestMapping(value = "/admin/Casuality3_Url", method = RequestMethod.GET)
		 public ModelAndView Casuality3_Url(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");

				}
		     Mmap.put("list", C1.Search_Casuality3(0,0,"","active"));
			 Mmap.put("getCasuality1", mcommon.getCasuality1());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
			 return new ModelAndView("Casuality3Tiles");
		 }
		
		 @RequestMapping(value = "/Casuality3_Action",method=RequestMethod.POST)
			public ModelAndView Casuality3_Action(@ModelAttribute("Casuality3_CMD") TB_PSG_MSTR_CASUALITY3 rm,
		BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");

				}	 
					int id = rm.getId() > 0 ? rm.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String casuality3 = request.getParameter("casuality3").trim();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					
					 
								if (rm.getCasuality1_id() == 0 ) {
									model.put("msg", "Please Select Casuality1.");
									return new ModelAndView("redirect:Casuality3_Url");
								}
						
								if (rm.getCasuality2_id() == 0 ) {
									model.put("msg", "Please Select Casuality2.");
									return new ModelAndView("redirect:Casuality3_Url");
								}
						
								if (casuality3.equals("") || casuality3.equals("null") || casuality3.equals(null)) {
									model.put("msg", "Please Enter Casuality3.");
						
									return new ModelAndView("redirect:Casuality3_Url");
								}
								if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
									model.put("msg", "Only Select Active Status.");
									return new ModelAndView("redirect:Casuality3_Url");
						
								}

						try{
						
						Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY3 where casuality3=:casuality3 and status=:status and id!=:id");
						q0.setParameter("casuality3", rm.getCasuality3());  
						q0.setParameter("status", rm.getStatus());
						q0.setParameter("id", id); 
						Long c = (Long) q0.uniqueResult();

						if (id == 0) {
							rm.setCreated_by(username);
							rm.setCreated_dt(date);
							rm.setCasuality3(casuality3);
							if (c == 0) {
								sessionHQL.save(rm);
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
							model.put("msg", "Couldn�t roll back transaction " + rbe);
						}
						throw e;
					}finally{
						if(sessionHQL!=null){
						   sessionHQL.close();
						}
					}	
					return new ModelAndView("redirect:Casuality3_Url");
				}
		 
		 
		 @RequestMapping(value = "/admin/getSearch_Casuality3", method = RequestMethod.POST)
			public ModelAndView getSearch_Casuality3(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "casuality1_id1", required = false) int casuality1_id,
					@RequestParam(value = "casuality2_id1", required = false) int casuality2_id,
					@RequestParam(value = "casuality3_v1", required = false) String casuality3_v1 ,
			        @RequestParam(value = "status1", required = false) String status1) {
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");

				}
			 
			 ArrayList<ArrayList<String>> list = C1.Search_Casuality3(casuality1_id,casuality2_id,casuality3_v1,status1);
			        Mmap.put("casuality1_id1", casuality1_id);
				    Mmap.put("casuality2_id1", casuality2_id);
					Mmap.put("casuality3_s1", casuality3_v1);
					Mmap.put("status1", status1);
					
					 Mmap.put("getCasuality1", mcommon.getCasuality1());
					 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
					 Mmap.put("list", list);
				return new ModelAndView("Casuality3Tiles","Casuality3_CMD",new TB_PSG_MSTR_CASUALITY3());
				
			}
		 
		 
			// -------------------------Edit District For page Open ------------------------------------- 

		 @RequestMapping(value = "/Edit_Casuality3", method = RequestMethod.POST)
			public ModelAndView Edit_Casuality3(@ModelAttribute("id2") String updateid,ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {
			
			 
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

			 
			 
			 TB_PSG_MSTR_CASUALITY3 C3Details = C1.getCasuality3Byid(Integer.parseInt(updateid));
					Mmap.put("Edit_DistrictCMD", C3Details);
					 Mmap.put("getCasuality1", mcommon.getCasuality1());
					Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
					Mmap.put("msg", msg);
				return new ModelAndView("Edit_Casuality3_Tiles");
			}
			
			// -------------------------Edit District_Action -------------------------------------

			
			@RequestMapping(value = "/Edit_Casuality3_Action", method = RequestMethod.POST)
			public ModelAndView Edit_Casuality3_Action(@ModelAttribute("Edit_Casuality3_CMD") TB_PSG_MSTR_CASUALITY3 rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
				String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");

					}
				
				
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("id"));
				int casuality2_id = Integer.parseInt(request.getParameter("casuality2_id"));
				String casuality3 = request.getParameter("casuality3").trim();
				String status = request.getParameter("status");
				
						if (rs.getCasuality1_id() == 0 ) {
							TB_PSG_MSTR_CASUALITY3 C3Details = C1.getCasuality3Byid(id);
							model.put("Edit_Casuality3_CMD", C3Details);
							model.put("getCasuality1", mcommon.getCasuality1());
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Select Country");
							return new ModelAndView("Edit_Casuality3_Tiles");
						}
				
					  if (rs.getCasuality2_id() == 0 ) {
						  TB_PSG_MSTR_CASUALITY3 C3Details = C1.getCasuality3Byid(id);
							model.put("Edit_Casuality3_CMD", C3Details);
							model.put("getCasuality1", mcommon.getCasuality1());
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Select State");
							return new ModelAndView("Edit_Casuality3_Tiles");
						}
				
					 if (casuality3.equals("") || casuality3.equals("null")
								|| casuality3.equals(null)) {
						 TB_PSG_MSTR_CASUALITY3 C3Details = C1.getCasuality3Byid(id);
							model.put("Edit_Casuality3_CMD", C3Details);
							model.put("getCasuality1", mcommon.getCasuality1());
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Enter District");
							return new ModelAndView("Edit_Casuality3_Tiles");
						}
					/* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
						 TB_PSG_MSTR_CASUALITY3 C3Details = C1.getCasuality3Byid(id);
							model.put("Edit_Casuality3_CMD", C3Details);
							model.put("getCasuality1", mcommon.getCasuality1());
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("Edit_Casuality3_Tiles");
				
						}*/
				
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
					 try {
						
						 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CASUALITY3 where casuality1_id=:casuality1_id and "
						 		+ "casuality2_id=:casuality2_id and casuality3=:casuality3  and status=:status and id!=:id");
						 
							q0.setParameter("casuality3", casuality3); 
							q0.setParameter("casuality1_id", rs.getCasuality1_id());
							q0.setParameter("casuality2_id", casuality2_id);
							q0.setParameter("status", status); 
							q0.setParameter("id", rs.getId()); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PSG_MSTR_CASUALITY3 set casuality1_id=:casuality1_id,casuality2_id=:casuality2_id,"
								 		+ "casuality3=:casuality3,modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			    .setInteger("casuality1_id",rs.getCasuality1_id())
						    			    .setInteger("casuality2_id",casuality2_id)
						    			    .setString("status",status)
						    			  	.setString("casuality3",casuality3)
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
				return new ModelAndView("redirect:Casuality3_Url");
			}
			@RequestMapping(value = "/delete_Casuality3", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_Casuality3(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
				String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");

					}
				
				
				List<String> liststr = new ArrayList<String>();
				
				String username = session.getAttribute("username").toString();
			
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					 String hqlUpdate = "update TB_PSG_MSTR_CASUALITY3 set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
				return new ModelAndView("redirect:Casuality3_Url");
			}
			
}
