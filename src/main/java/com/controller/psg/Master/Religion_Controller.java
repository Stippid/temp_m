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
import com.dao.psg.Master.Religion_DAO;
import com.models.psg.Master.TB_RELIGION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Religion_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private Religion_DAO reli_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Religion", method = RequestMethod.GET)
	 public ModelAndView Religion(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);
		 ArrayList<ArrayList<String>> list = reli_dao.search_religion_name("","active");
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("ReligionTiles");
	 }
	 
	 @RequestMapping(value = "/ReligionAction",method=RequestMethod.POST)
		public ModelAndView ReligionAction(@ModelAttribute("ReligionCMD") TB_RELIGION rm,
				 @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
					 
				int religion_id = rm.getReligion_id() > 0 ? rm.getReligion_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String religion_name = request.getParameter("religion_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				 if (religion_name.equals("") || religion_name.equals("null")
							|| religion_name.equals(null)) {
						model.put("msg", "Please Enter Religion");
						return new ModelAndView("redirect:Religion");
					}
				 
				 
					if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
			
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Religion");
			
					}

				try{
					
					Query q0 = sessionHQL.createQuery("select count(religion_id) from TB_RELIGION where religion_name=:religion_name  and status=:status and religion_id !=:religion_id");
					q0.setParameter("religion_name", rm.getReligion_name());  
					q0.setParameter("status", rm.getStatus());
					
					q0.setParameter("religion_id", religion_id); 
					Long c = (Long) q0.uniqueResult();

					if (religion_id == 0) {
						rm.setCreated_by(username);
						rm.setCreated_date(date);
						rm.setReligion_name(religion_name);
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
				return new ModelAndView("redirect:Religion");
			}
	 
	 
		@RequestMapping(value = "/admin/getSearch_Religion_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Religion_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "religion_name1", required = false) String religion_name1,
				 @RequestParam(value = "status1", required = false) String status ){
			
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				Mmap.put("religion_name1", religion_name1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				ArrayList<ArrayList<String>> list = reli_dao.search_religion_name(religion_name1,status);
					Mmap.put("list", list);
			return new ModelAndView("ReligionTiles","ReligionCMD",new TB_RELIGION());
		}
		
		
		@RequestMapping(value = "/Edit_Religion", method = RequestMethod.POST)
		public ModelAndView Edit_Religion(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			    TB_RELIGION authDetails = reli_dao.getreligionByid(Integer.parseInt(updateid));
				Mmap.put("Edit_ReligionCMD", authDetails);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_ReligionCMD_Tiles");
		}
		
		
		@RequestMapping(value = "/Edit_Religion_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Religion_Action(@ModelAttribute("Edit_ReligionCMD") TB_RELIGION rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String religion_name = request.getParameter("religion_name").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
	        if (religion_name.equals("") || religion_name.equals("null")
					|| religion_name.equals(null)) {
	        	TB_RELIGION authDetails = reli_dao.getreligionByid((id));
	        	model.put("Edit_ReligionCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Please Enter Religion");
				return new ModelAndView("Edit_ReligionCMD_Tiles");
			}
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_RELIGION authDetails = reli_dao.getreligionByid((id));
	        	model.put("Edit_ReligionCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_ReligionCMD_Tiles");
			}*/
		 
				 try {
					
					 Query q0 = session1.createQuery("select count(religion_id) from TB_RELIGION where religion_name=:religion_name  and status=:status and religion_id !=:religion_id");
						q0.setParameter("religion_name", religion_name); 
						q0.setParameter("status", status);
						q0.setParameter("religion_id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_RELIGION set religion_name=:religion_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where religion_id=:religion_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("religion_name",religion_name)
					    			  	.setString("status",status)
										.setString("modify_by", username)
										.setDate("modify_date", new Date())
										.setInteger("religion_id",id);
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
			return new ModelAndView("redirect:Religion");
		}

		
		@RequestMapping(value = "/delete_Religion", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Religion(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			

			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
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
				 
				 String hqlUpdate = "update TB_RELIGION set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where religion_id=:religion_id";
				 
				//String hqlUpdate = "delete from TB_RELIGION where religion_id=:religion_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("religion_id", id).executeUpdate();
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
			return new ModelAndView("redirect:Religion");
		}
}
