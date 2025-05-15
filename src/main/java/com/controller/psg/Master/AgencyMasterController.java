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
import com.dao.psg.Master.AgencyDAOImpl;
import com.models.psg.Master.TB_PSG_AGENCY_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class AgencyMasterController {
	

	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	AgencyDAOImpl ADAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// -------------------------------agency For page Open -------------------------------------//
	
	 @RequestMapping(value = "/admin/agency_url", method = RequestMethod.GET)
	 public ModelAndView agency_url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
            return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		 ArrayList<ArrayList<String>> list = ADAO.search_Agency_name("","active");
	     Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("AgencyTiles");
	 }
	 
	 
	// -------------------------------agency save -------------------------------------//
	 
		 @RequestMapping(value = "/AgencyAction",method=RequestMethod.POST)
			public ModelAndView AgencyAction(@ModelAttribute("AgencyCMD") TB_PSG_AGENCY_MASTER com,
					@RequestParam(value = "msg", required = false) String msg,
               HttpServletRequest request,ModelMap model,HttpSession session) {
				
			 
			 String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
	return new ModelAndView("redirect:bodyParameterNotAllow");
				}

						 
					int Id = com.getId() > 0 ? com.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String agency_name = request.getParameter("agency_name").trim();
					
					
					
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					 
					 if (agency_name.equals("") || agency_name.equals("null")|| agency_name.equals(null)) {
						 model.put("msg", "Please Enter Agency Name");
						 return new ModelAndView("redirect:agency_url");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:agency_url");
						}
					
					try{
						
						Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_AGENCY_MASTER where agency_name=:agency_name and status=:status and Id !=:Id");
						q0.setParameter("agency_name", com.getAgency_name());  
						q0.setParameter("status", com.getStatus());
						q0.setParameter("Id", Id);  
						Long c = (Long) q0.uniqueResult();

						if (Id == 0) {
							com.setCreated_by(username);
							com.setCreated_date(date);
							com.setAgency_name(agency_name);
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
					return new ModelAndView("redirect:agency_url");
				}
		 
		// -------------------------SEARCH Battalion  -------------------------------------//
		 
		 @RequestMapping(value = "/admin/getSearch_Agency_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_Agency_Master(ModelMap Mmap,HttpSession session,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Agency_name1", required = false) String Agency_name1 ,String Agency_name,@ModelAttribute("status1") String status,HttpServletRequest request)  {
			
			 
			 String  roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
	return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 
			 		ArrayList<ArrayList<String>> list = ADAO.search_Agency_name(Agency_name1,status);
					Mmap.put("Agency_name1", Agency_name1);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					
				    Mmap.put("list", list);
				   return new ModelAndView("AgencyTiles","AgencyCMD",new TB_PSG_AGENCY_MASTER());
				   
			}
		 
		 
		// -------------------------Edit agency For page Open -------------------------------------	
			
		 @RequestMapping(value = "/Edit_Agency")
			public ModelAndView Edit_Agency(@ModelAttribute("id2") String updateid,ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {
			 
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
	return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
			 TB_PSG_AGENCY_MASTER AgencyDetails = ADAO.getAgencyByid(Integer.parseInt(updateid));
					Mmap.put("Edit_agencyCMD", AgencyDetails);	
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					Mmap.put("msg", msg);
				return new ModelAndView("Edit_AgencyTiles");
			}
		 
		 
		// -------------------------Edit Country Action -------------------------------------
		 
			@RequestMapping(value = "/Edit_Agency_Action", method = RequestMethod.POST)
			public ModelAndView Edit_Agency_Action(@ModelAttribute("Edit_AgencyCMD") TB_PSG_AGENCY_MASTER rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
				 String  roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
					}
				
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("id"));
				String agency_name = request.getParameter("agency_name").trim();
				String status = request.getParameter("status");
				
				if (agency_name.equals("") || agency_name.equals("null")
						|| agency_name.equals(null)) {
					TB_PSG_AGENCY_MASTER AgencyDetails = ADAO.getAgencyByid((id));
					model.put("Edit_AgencyCMD", AgencyDetails);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Please Enter Agency");
					//model.put("id2", id);
					return new ModelAndView("Edit_AgencyTiles");
				}
				
				/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					TB_PSG_AGENCY_MASTER AgencyDetails = ADAO.getAgencyByid((id));
					model.put("Edit_AgencyCMD", AgencyDetails);	
					model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_AgencyTiles");
				}*/
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
					 try {
						
						 Query q0 = session1.createQuery("select count(id) from TB_PSG_AGENCY_MASTER where agency_name=:agency_name and status=:status and id !=:id");
							q0.setParameter("agency_name", agency_name);  
							q0.setParameter("id", id); 
							q0.setParameter("status", status);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PSG_AGENCY_MASTER set agency_name=:agency_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("agency_name",agency_name)
						    			  	.setString("status",status)
											.setString("modified_by", username)
											.setDate("modified_date", new Date())
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
				return new ModelAndView("redirect:agency_url");
			}
			
			
			///delete
			
			@RequestMapping(value = "/delete_Agency", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_Agency(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
				
				 String  roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("agency_url", roleid);	
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
					 
					 String hqlUpdate = "update TB_PSG_AGENCY_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status"
											+ " where Id=:Id";
					
					 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
							.setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("Id", id).executeUpdate();
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
				return new ModelAndView("redirect:agency_url");
			}
			

}
