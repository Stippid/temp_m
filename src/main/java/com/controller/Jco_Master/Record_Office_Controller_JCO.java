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
import com.dao.Jco_Master.Record_OfficeDAO_JCO;
import com.model.Jco_Master.TB_PSG_MSTR_RECORD_OFFICE_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Record_Office_Controller_JCO {

	
	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Record_OfficeDAO_JCO rec_dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Record_office_Url", method = RequestMethod.GET)
	 public ModelAndView Record_office_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		
		 ArrayList<ArrayList<String>> list = rec_dao.search_record_office("","","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("Record_office_Tiles");
	 }
	 
	 
	 @RequestMapping(value = "/record_officeAction",method=RequestMethod.POST)
		public ModelAndView record_officeAction(@ModelAttribute("record_officeCMD") TB_PSG_MSTR_RECORD_OFFICE_JCO com,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 	 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String record_office = request.getParameter("record_office").trim();
				 String sus_no = request.getParameter("sus_no").trim();
				 
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (record_office.equals("") || record_office.equals("null") || record_office.equals(null)) {
							model.put("msg", "Please Enter Record Office");
							return new ModelAndView("redirect:Record_office_Url");
						}
					 if (sus_no.equals("") || sus_no.equals("null") || sus_no.equals(null)) {
						 model.put("msg", "Please Enter Sus No");
						 return new ModelAndView("redirect:Record_office_Url");
					 }
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Record_office_Url");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_RECORD_OFFICE_JCO where record_office=:record_office and sus_no=:sus_no and status=:status and id !=:id");
					q0.setParameter("record_office", com.getRecord_office());  
					q0.setParameter("sus_no", com.getSus_no());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setRecord_office(record_office);
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
				return new ModelAndView("redirect:Record_office_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/getSearch_record_office_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_record_office_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "record_office1", required = false) String record_office1,
				@RequestParam(value = "sus_no1", required = false) String sus_no1 ,@ModelAttribute("status1") String status)  {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 		ArrayList<ArrayList<String>> list = rec_dao.search_record_office(record_office1,sus_no1,status);
				Mmap.put("record_office1", record_office1);
				Mmap.put("sus_no1", sus_no1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("Record_office_Tiles","record_officeCMD",new TB_PSG_MSTR_RECORD_OFFICE_JCO());
			   
		}
	
				
	 @RequestMapping(value = "/Edit_record_office", method = RequestMethod.POST)
		public ModelAndView Edit_record_office(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 TB_PSG_MSTR_RECORD_OFFICE_JCO FR = rec_dao.getrecordoffByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Record_officeCMD", FR);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Record_office_Tiles");
		}
		
	 
		@RequestMapping(value = "/Edit_Record_office_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Record_office_Action(@ModelAttribute("Edit_Record_officeCMD") TB_PSG_MSTR_RECORD_OFFICE_JCO rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String record_office = request.getParameter("record_office").trim();
			String sus_no = request.getParameter("sus_no").trim();
			String status = request.getParameter("status");
			
			

			if (record_office.equals("") || record_office.equals("null")|| record_office.equals(null)) {
				TB_PSG_MSTR_RECORD_OFFICE_JCO authDetails = rec_dao.getrecordoffByid(id);
	        	model.put("Edit_Record_officeCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Record Office");
				return new ModelAndView("Edit_Record_office_Tiles");
			}	
			
			if (sus_no.equals("") || sus_no.equals("null")|| sus_no.equals(null)) {
				TB_PSG_MSTR_RECORD_OFFICE_JCO authDetails = rec_dao.getrecordoffByid(id);
				model.put("Edit_Record_officeCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Please Enter Sus No");
				return new ModelAndView("Edit_Record_office_Tiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_RECORD_OFFICE_JCO authDetails = rec_dao.getrecordoffByid(id);
	        	model.put("Edit_FieldAreaCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Record_office_Tiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_RECORD_OFFICE_JCO where record_office=:record_office and sus_no=:sus_no and status=:status and id !=:id");
						q0.setParameter("record_office", record_office);  
						q0.setParameter("sus_no", sus_no);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_RECORD_OFFICE_JCO set record_office=:record_office,sus_no=:sus_no ,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("record_office",record_office)
					    			  	.setString("sus_no",sus_no)
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
			return new ModelAndView("redirect:Record_office_Url");
		}

		
		@RequestMapping(value = "/delete_record_office", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_record_office(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_RECORD_OFFICE_JCO set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Record_office_Url");
		}
}
