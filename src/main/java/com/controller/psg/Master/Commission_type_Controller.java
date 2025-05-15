package com.controller.psg.Master;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.commisionDao;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_COMMISSION_TYPE;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Commission_type_Controller {
	
	
	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	commisionDao cm_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	//////////page open..............
	 @RequestMapping(value = "/admin/Commission_type", method = RequestMethod.GET)
	 public ModelAndView Commission_type(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 

		 Mmap.put("msg", msg);			 
		 ArrayList<ArrayList<String>> list = cm_Dao.search_commision("","active");
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());

		 return new ModelAndView("Commission_typeTiles");
	 }
	 
	 //............save...................
	 
	 
	 @RequestMapping(value = "/Commission_typeAction",method=RequestMethod.POST)
		public ModelAndView Commission_typeAction(@ModelAttribute("Commission_typeCMD") TB_COMMISSION_TYPE com,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
					 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 
		 int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String comn_name = request.getParameter("comn_name").trim();
				
				
				if(comn_name.isEmpty() ||  comn_name.equals("")) {

					 model.put("msg", "Please Enter Commising Name.");

					 return new ModelAndView("redirect:Commission_type");

				 }
				 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
						
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Commission_type");
			
					}

				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_COMMISSION_TYPE where comn_name=:comn_name and status=:status and id !=:id");
					q0.setParameter("comn_name", com.getComn_name());  
					q0.setParameter("status", com.getStatus());
					
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setComn_name(comn_name);
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
				return new ModelAndView("redirect:Commission_type");
			}
		
	/////////?search///////////////
	 
	 

	 @RequestMapping(value = "/admin/getSearch_Commission_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Commission_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "comn_name1", required = false) String comn_name1,
		        @RequestParam(value = "status1", required = false) String status )

		
	 {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
				Mmap.put("comn_name1", comn_name1);
				List<ArrayList<String>> list = cm_Dao.search_commision(comn_name1,status);
				Mmap.put("list", list);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			return new ModelAndView("Commission_typeTiles","Commission_typeCMD",new TB_COMMISSION_TYPE());
		}
	 
	
	
	// =======================================Edit open //
		// page===========================//
		@RequestMapping(value = "/edit_commision",method = RequestMethod.POST)
		public ModelAndView edit_commision(@ModelAttribute("id2") String updateid, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Commission_type", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			TB_COMMISSION_TYPE invDetails = cm_Dao.updatecommision_Byid(Integer.parseInt(updateid));
			model.put("commission_editCMD", invDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("date", date1);
			model.put("msg", msg);

			return new ModelAndView("Edit_Commission_typeTiles");
		}
		
		@RequestMapping(value = "/commission_editAction", method = RequestMethod.POST)
		public ModelAndView commission_editAction(@ModelAttribute("commission_editCMD") TB_COMMISSION_TYPE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String comn_name = request.getParameter("comn_name").trim();
			String status = request.getParameter("status");
			
				if (rs.getComn_name().equals("") || rs.getComn_name().equals("null") || rs.getComn_name().equals(null)) {
					TB_COMMISSION_TYPE invDetails = cm_Dao.updatecommision_Byid((id));
					model.put("commission_editCMD", invDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Please Enter Commison Name");
					return new ModelAndView("Edit_Commission_typeTiles");
				}
				/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					TB_COMMISSION_TYPE invDetails = cm_Dao.updatecommision_Byid((id));
					model.put("commission_editCMD", invDetails);
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_Commission_typeTiles");
		
				}*/
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_COMMISSION_TYPE where comn_name=:comn_name and status=:status  and id !=:id");
						q0.setParameter("comn_name", comn_name);
						q0.setParameter("status", status);
						q0.setParameter("id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_COMMISSION_TYPE set comn_name=:comn_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("comn_name",comn_name)
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
			return new ModelAndView("redirect:Commission_type");
		}
		

	/*	@RequestMapping(value = "/delete_Commission", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Commission(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_COMMISSION_TYPE where id=:id";
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
			return new ModelAndView("redirect:Commission_type");
		}
		*/
		@RequestMapping(value = "/delete_Commission", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Commission(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
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
				 
				 String hqlUpdate = "update TB_COMMISSION_TYPE set modify_by=:modify_by,modify_date=:modify_date,status=:status"
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
			return new ModelAndView("redirect:Commission_type");
		}
	
	 
}
