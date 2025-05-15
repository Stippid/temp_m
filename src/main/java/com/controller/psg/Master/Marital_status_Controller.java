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
import com.dao.psg.Master.MaritialDAO;
import com.models.psg.Master.TB_MARITAL_STATUS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Marital_status_Controller {

	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	private MaritialDAO mari_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	// -------------------------------Marital_status For page Open -------------------------------------
 
		@RequestMapping(value = "/admin/Marital_status", method = RequestMethod.GET)
		 public ModelAndView Marital_status(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			
			//List<ArrayList<String>> list = mari_dao.search_maritial_name("","","0");
			Mmap.put("list", mari_dao.search_maritial_name("","","active"));
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			 return new ModelAndView("Marital_statusTiles");
		 }
		
	// -------------------------------Marital_status  save -------------------------------------
	 @RequestMapping(value = "/Marital_statusAction",method=RequestMethod.POST)
		public ModelAndView Marital_statusAction(@ModelAttribute("Marital_statusCMD") TB_MARITAL_STATUS rm,
		HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}	 
				int marital_id = rm.getMarital_id() > 0 ? rm.getMarital_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String marital_code = request.getParameter("marital_code").trim();
				String marital_name = request.getParameter("marital_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				 if (marital_code.equals("") || marital_code.equals("null")
							|| marital_code.equals(null)) {
						model.put("msg", "Please Enter Marital Code");
						return new ModelAndView("redirect:Marital_status");
					}
				 if (marital_name.equals("") || marital_name.equals("null")
							|| marital_name.equals(null)) {
						model.put("msg", "Please Enter Marital Name");
						return new ModelAndView("redirect:Marital_status");
					}
				 if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
						
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Marital_status");
			
					}
				try{
					
					Query q0 = sessionHQL.createQuery("select count(marital_id) from TB_MARITAL_STATUS where marital_name=:marital_name  and status=:status  and marital_id !=:marital_id");
					q0.setParameter("marital_name", rm.getMarital_name());  
					q0.setParameter("status", rm.getStatus());
					q0.setParameter("marital_id", marital_id); 
					Long c = (Long) q0.uniqueResult();

					if (marital_id == 0) {
						rm.setCreated_by(username);
						rm.setCreated_date(date);
						rm.setMarital_code(marital_code);
						rm.setMarital_name(marital_name);
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
				return new ModelAndView("redirect:Marital_status");
			}
	
		// -------------------------SEARCH Maritial Report  -------------------------------------

		@RequestMapping(value = "/getSearch_Marital_Master" , method = RequestMethod.POST)
		public ModelAndView getSearch_Marital_Master(ModelMap model, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, 
				@RequestParam(value = "marital_code1", required = false) String marital_code1,
				@RequestParam(value = "marital_name1", required = false) String marital_name1,
				@RequestParam("status1") String status){
			String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			ArrayList<ArrayList<String>> list = mari_dao.search_maritial_name(marital_code1,marital_name1,status);
			model.put("list", list);
			model.put("size", list.size());
			model.put("marital_code1", marital_code1);
			model.put("marital_name1", marital_name1);
			model.put("status1", status);
			model.put("list", list);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			return new ModelAndView("Marital_statusTiles");
		}
	// -------------------------Edit Maritial For page Open -------------------------------------
	 @RequestMapping(value = "/Edit_Maritial",method=RequestMethod.POST)
		public ModelAndView Edit_Maritial(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			    TB_MARITAL_STATUS authDetails = mari_dao.getmaritialByid(Integer.parseInt(updateid));
				Mmap.put("Edit_MaritialCMD", authDetails);
				Mmap.put("msg", msg);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			return new ModelAndView("EditMarital_CMD_Tiles");
		}
	 
	// -------------------------Edit Maritial Action------------------------------------
	 @RequestMapping(value = "/Edit_Maritial_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Maritial_Action(@ModelAttribute("Edit_MaritialCMD") TB_MARITAL_STATUS rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String marital_code = request.getParameter("marital_code").trim();
			String marital_name = request.getParameter("marital_name").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
						
	        			if (marital_code.equals("") || marital_code.equals("null")
								|| marital_code.equals(null)) {
	        				TB_MARITAL_STATUS authDetails = mari_dao.getmaritialByid((id));
	        				model.put("Edit_MaritialCMD", authDetails);
	        				model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Please Enter Marital Code");
							return new ModelAndView("EditMarital_CMD_Tiles");
						}
						if (marital_name.equals("") || marital_name.equals("null")
								|| marital_name.equals(null)) {
							TB_MARITAL_STATUS authDetails = mari_dao.getmaritialByid((id));
	        				model.put("Edit_MaritialCMD", authDetails);
	        				model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Please Enter Marital Name");
							return new ModelAndView("EditMarital_CMD_Tiles");
						}
						/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
							TB_MARITAL_STATUS authDetails = mari_dao.getmaritialByid((id));
	        				model.put("Edit_MaritialCMD", authDetails);
	        				model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("EditMarital_CMD_Tiles");
				
						}*/
						
				 try {
					
					 Query q0 = session1.createQuery("select count(marital_id) from TB_MARITAL_STATUS where marital_code=:marital_code and marital_name=:marital_name and status=:status and marital_id !=:marital_id");
							 q0.setParameter("marital_code", marital_code);  
							 q0.setParameter("marital_name", marital_name); 
							 q0.setParameter("status", status); 
							 q0.setParameter("marital_id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_MARITAL_STATUS set marital_code=:marital_code,marital_name=:marital_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where marital_id=:marital_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("marital_code",marital_code)
					    			  	.setString("marital_name",marital_name)
										.setString("modify_by", username)
										.setString("status",status)
										.setDate("modify_date", new Date())
										.setInteger("marital_id",id);
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
			return new ModelAndView("redirect:Marital_status");
		}
	 
	 
	// -------------------------Delet Maritial -------------------------------------
	 
	/* @RequestMapping(value = "/delete_Maritial", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Maritial(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_MARITAL_STATUS where marital_id=:marital_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("marital_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Marital_status");
		}*/
	 @RequestMapping(value = "/delete_Maritial", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Maritial(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
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
				 
				 String hqlUpdate = "update TB_MARITAL_STATUS set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where marital_id=:marital_id";
				 
				//String hqlUpdate = "delete from TB_RELIGION where religion_id=:religion_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("marital_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Marital_status");
		}
}
