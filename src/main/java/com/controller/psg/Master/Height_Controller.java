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
import com.dao.psg.Master.HeightDAO;
import com.models.psg.Master.TB_Height;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Height_Controller {

	@Autowired
	private HeightDAO heightdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController mcommon = new Psg_CommonController();
	
	// -------------------------------Marital_status For page Open -------------------------------------
 
		@RequestMapping(value = "/admin/Height", method = RequestMethod.GET)
		 public ModelAndView Height(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			 	String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Height", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					
			ArrayList<ArrayList<String>> list = heightdao.search_height_Master("","","active");
			Mmap.put("list", list);
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 return new ModelAndView("heightTiles");
		 }
		
	// -------------------------------Marital_status  save -------------------------------------
	 @RequestMapping(value = "/heightAction",method=RequestMethod.POST)
		public ModelAndView heightAction(@ModelAttribute("heightCMD") TB_Height rm,@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 	String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Height", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				 
				int height_id  = rm.getHeight_id() > 0 ? rm.getHeight_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String centimeter = request.getParameter("centimeter").trim();
				String inch = request.getParameter("inch").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				 if (centimeter.equals("") || centimeter.equals("null")
							|| centimeter.equals(null)) {
						model.put("msg", "Please Enter Centimeter");
						return new ModelAndView("redirect:Height");
					}
				 if (inch.equals("") || inch.equals("null")
							|| inch.equals(null)) {
						model.put("msg", "Please Enter Inch");
						return new ModelAndView("redirect:Height");
					}
				 if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Height");
					}
				try{
					
					Query q0 = sessionHQL.createQuery("select count(height_id) from TB_Height where centimeter=:centimeter and status=:status and inch=:inch and height_id !=:height_id");
					q0.setParameter("centimeter", rm.getCentimeter());  
					q0.setParameter("status", rm.getStatus()); 
					q0.setParameter("inch", rm.getInch()); 
					q0.setParameter("height_id", height_id); 
					
					Long c = (Long) q0.uniqueResult();

					if (height_id == 0) {
						rm.setCreated_by(username);
						rm.setCreated_date(date);
						rm.setCentimeter(centimeter);
						rm.setInch(inch);
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
				return new ModelAndView("redirect:Height");
			}
	
	 	// -------------------------SEARCH Maritial Report  -------------------------------------

		@RequestMapping(value = "/getSearch_height_Master" , method = RequestMethod.POST)
		public ModelAndView getSearch_height_Master(ModelMap model, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, 
				@RequestParam(value = "centimeter1", required = false) String centimeter1,
				@RequestParam(value = "inch1", required = false) String inch1,
				@RequestParam(value = "status1", required = false) String status1){
			
		 	String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Height", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			
			ArrayList<ArrayList<String>> list = heightdao.search_height_Master(centimeter1,inch1,status1);
			model.put("list", list);
			model.put("size", list.size());
			model.put("centimeter1", centimeter1);
			model.put("inch1", inch1);
			model.put("list", list);
			model.put("status1", status1);
			model.put("getStatusMasterList", mcommon.getStatusMasterList());
			return new ModelAndView("heightTiles");
		}
	// -------------------------Edit Maritial For page Open -------------------------------------
			@RequestMapping(value = "/Edit_height",method=RequestMethod.POST)
			public ModelAndView Edit_height(@ModelAttribute("id2") String updateid, ModelMap Mmap,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
					HttpSession sessionEdit) {
			 	String  roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Height", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
				TB_Height authDetails = heightdao.getheightByid(Integer.parseInt(updateid));
				Mmap.put("Edit_heightCMD", authDetails);
				Mmap.put("msg", msg);
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				return new ModelAndView("EditheightTiles");
			}
	 
	// -------------------------Edit hight Action------------------------------------
		 @RequestMapping(value = "/Edit_height_Action", method = RequestMethod.POST)
		public ModelAndView Edit_height_Action(@ModelAttribute("Edit_heightCMD") TB_Height rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 	String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Height", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String centimeter = request.getParameter("centimeter").trim();
			String inch = request.getParameter("inch").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
						if (centimeter.equals("") || centimeter.equals("null") || centimeter.equals(null)) {
							TB_Height authDetails = heightdao.getheightByid((id));
							model.put("Edit_heightCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Enter Centimeter");
							return new ModelAndView("EditheightTiles");
						}
						if (inch.equals("") || inch.equals("null") || inch.equals(null)) {
							TB_Height authDetails = heightdao.getheightByid((id));
							model.put("Edit_heightCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Please Enter Inch");
							return new ModelAndView("EditheightTiles");
						}
						/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
							TB_Height authDetails = heightdao.getheightByid((id));
							model.put("Edit_heightCMD", authDetails);
							model.put("getStatusMasterList", mcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("EditheightTiles");
						}*/
				 try {
					
					 Query q0 = session1.createQuery("select count(height_id) from TB_Height where centimeter=:centimeter and inch=:inch and status=:status and height_id !=:height_id");
							 q0.setParameter("centimeter", centimeter);  
							 q0.setParameter("inch", inch);  
							 q0.setParameter("status", status); 
							 q0.setParameter("height_id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_Height set centimeter=:centimeter,inch=:inch,status=:status,modify_by=:modify_by,modify_date=:modify_date"
										+ " where height_id=:height_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("centimeter",centimeter)
					    			  	.setString("inch",inch)
					    			  	.setString("status",status)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("height_id",id);
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
			return new ModelAndView("redirect:Height");
		}
	 
	 
	// -------------------------Delet Maritial -------------------------------------
	 
	/* @RequestMapping(value = "/delete_height", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_height(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_Height where height_id=:height_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("height_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Height");
		}*/
		 
		 @RequestMapping(value = "/delete_height", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_height(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 	String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Height", roleid);	
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
					 
					 String hqlUpdate = "update TB_Height set modify_by=:modify_by,modify_date=:modify_date,status=:status"
											+ " where height_id=:height_id";
					
					 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
							.setString("modify_by", username)
							.setDate("modify_date", new Date()).setInteger("height_id", id).executeUpdate();
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
				return new ModelAndView("redirect:Height");
			}
}
