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
import com.dao.psg.Master.Filed_AreaDAO;
import com.models.psg.Master.TB_PSG_MSTR_FIELD_AREA;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Field_Area_Controller {

	
Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Filed_AreaDAO FRdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Field_Area_Url", method = RequestMethod.GET)
	 public ModelAndView Field_Area_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		} 
		 
		
		 ArrayList<ArrayList<String>> list = FRdao.search_Field_Area("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("Field_AreaTiles");
	 }
	 
	 
	 @RequestMapping(value = "/fieldareaAction",method=RequestMethod.POST)
		public ModelAndView fieldareaAction(@ModelAttribute("fieldareaCMD") TB_PSG_MSTR_FIELD_AREA com,
				 @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
					 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String field_area = request.getParameter("field_area").trim();
				 
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (field_area.equals("") || field_area.equals("null") || field_area.equals(null)) {
							model.put("msg", "Please Enter Field Area");
							return new ModelAndView("redirect:Field_Area_Url");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Field_Area_Url");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_FIELD_AREA where field_area=:field_area and status=:status and id !=:id");
					q0.setParameter("field_area", com.getField_area());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setField_area(field_area);
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
				return new ModelAndView("redirect:Field_Area_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/getSearch_Field_Area_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Field_Area_Master(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				@RequestParam(value = "field_area1", required = false) String field_area1 ,@ModelAttribute("status1") String status)  {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
		 		ArrayList<ArrayList<String>> list = FRdao.search_Field_Area(field_area1,status);
				Mmap.put("field_area1", field_area1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("Field_AreaTiles","fieldareaCMD",new TB_PSG_MSTR_FIELD_AREA());
			   
		}
	
				
	 @RequestMapping(value = "/Edit_Fieldarea",method=RequestMethod.POST)
		public ModelAndView Edit_Fieldarea(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
		 TB_PSG_MSTR_FIELD_AREA FR = FRdao.getFieldAreaByid(Integer.parseInt(updateid));
				Mmap.put("Edit_FieldAreaCMD", FR);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Field_AreaTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_FieldArea_Action", method = RequestMethod.POST)
		public ModelAndView Edit_FieldArea_Action(@ModelAttribute("Edit_FieldAreaCMD") TB_PSG_MSTR_FIELD_AREA rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String field_area = request.getParameter("field_area").trim();
			String status = request.getParameter("status");
			
			

			if (field_area.equals("") || field_area.equals("null")|| field_area.equals(null)) {
				TB_PSG_MSTR_FIELD_AREA authDetails = FRdao.getFieldAreaByid(id);
	        	model.put("Edit_FieldAreaCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Field Area");
				return new ModelAndView("Edit_Field_AreaTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_FIELD_AREA authDetails = FRdao.getFieldAreaByid(id);
	        	model.put("Edit_FieldAreaCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Field_AreaTiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_FIELD_AREA where field_area=:field_area and status=:status and id !=:id");
						q0.setParameter("field_area", field_area);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_FIELD_AREA set field_area=:field_area,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("field_area",field_area)
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
			return new ModelAndView("redirect:Field_Area_Url");
		}

		
		@RequestMapping(value = "/delete_Fieldarea", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Fieldarea(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_FIELD_AREA set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Field_Area_Url");
		}
}
