package com.controller.Jco_Master;

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
import com.controller.psg.Master.Psg_CommonController;
import com.dao.Jco_Master.Class_Pay_DAO_JCO;
import com.model.Jco_Master.TB_PSG_MSTR_CLASS_PAY_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Class_Pay_Controller_JCO {

	
	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Class_Pay_DAO_JCO Class_payDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Class_Pay_Url", method = RequestMethod.GET)
	 public ModelAndView Class_Pay_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			   return new ModelAndView("redirect:bodyParameterNotAllow");
		} 
		 
		
		 ArrayList<ArrayList<String>> list = Class_payDao.search_Class_pay("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("class_payTiles");
	 }
	 
	 
	 @RequestMapping(value = "/class_payAction",method=RequestMethod.POST)
		public ModelAndView class_payAction(@ModelAttribute("class_payCMD") TB_PSG_MSTR_CLASS_PAY_JCO com,
				
				 @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
					 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String class_pay = request.getParameter("class_pay").trim();
				 
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (class_pay.equals("") || class_pay.equals("null") || class_pay.equals(null)) {
							model.put("msg", "Please Enter Class Pay");
							return new ModelAndView("redirect:Class_Pay_Url");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Class_Pay_Url");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_CLASS_PAY_JCO where class_pay=:class_pay and status=:status and id !=:id");
					q0.setParameter("class_pay", com.getClass_pay());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setClass_pay(class_pay);
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
				return new ModelAndView("redirect:Class_Pay_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/getSearch_Class_pay_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Class_pay_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "class_pay1", required = false) String class_pay,@RequestParam("status1") String status)  {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
		 
		 		ArrayList<ArrayList<String>> list = Class_payDao.search_Class_pay(class_pay,status);
				Mmap.put("class_pay1", class_pay);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("class_payTiles","class_payCMD",new TB_PSG_MSTR_CLASS_PAY_JCO());
			   
		}
	
				
	 @RequestMapping(value = "/Edit_class_pay", method = RequestMethod.POST)
		public ModelAndView Edit_class_pay(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
		 TB_PSG_MSTR_CLASS_PAY_JCO FR = Class_payDao.getFieldAreaByid(Integer.parseInt(updateid));
				Mmap.put("Edit_calss_payCMD", FR);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Class_payTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_class_pay_Action", method = RequestMethod.POST)
		public ModelAndView Edit_class_pay_Action(@ModelAttribute("Edit_calss_payCMD") TB_PSG_MSTR_CLASS_PAY_JCO rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			} 
			 
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String class_pay = request.getParameter("class_pay").trim();
			String status = request.getParameter("status");
			
			

			if (class_pay.equals("") || class_pay.equals("null")|| class_pay.equals(null)) {
				TB_PSG_MSTR_CLASS_PAY_JCO authDetails = Class_payDao.getFieldAreaByid(id);
	        	model.put("Edit_FieldAreaCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Class Pay");
				return new ModelAndView("Edit_Class_payTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_CLASS_PAY_JCO authDetails = Class_payDao.getFieldAreaByid(id);
	        	model.put("Edit_FieldAreaCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Class_payTiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CLASS_PAY_JCO where class_pay=:class_pay and status=:status and id !=:id");
						q0.setParameter("class_pay", class_pay);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_CLASS_PAY_JCO set class_pay=:class_pay,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("class_pay",class_pay)
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
			return new ModelAndView("redirect:Class_Pay_Url");
		}

		
		@RequestMapping(value = "/delete_class_pay", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_class_pay(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_CLASS_PAY_JCO set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Class_Pay_Url");
		}
}
