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
import com.dao.psg.Master.Personnel_typeDAO;
import com.models.psg.Master.TB_PERSONNEL_TYPE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Personnel_typeController {
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private Personnel_typeDAO ptdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

			
	 @RequestMapping(value = "/admin/Personnel_type", method = RequestMethod.GET)
	 public ModelAndView Personnel_type(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> list = ptdao.search_Personnel_type_dtl("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("Personnel_typeTiles");
	 }
	

		@RequestMapping(value = "/Personnel_typeAction", method = RequestMethod.POST)
		public ModelAndView Personnel_typeAction(@ModelAttribute("Personnel_typeCMD") TB_PERSONNEL_TYPE PT, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			int id = PT.getId() > 0 ? PT.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			//String status = session.getAttribute("status");
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
	 		String personnel_name = request.getParameter("personnel_name").trim();
	 		/*if (request.getParameter("personnel_name") == "") {
				tx.rollback();
				Mmap.put("msg", "Please Enter Personnel Name");
				return new ModelAndView("redirect:Personnel_type");
			}
		*/
	 		
						if (personnel_name.equals("") || personnel_name.equals("null")
								|| personnel_name.equals(null)) {
							Mmap.put("msg", "Please Enter Personnel Name");
							return new ModelAndView("redirect:Personnel_type");
						}
						if (PT.getStatus() == "inactive" || PT.getStatus().equals("inactive")) {
							Mmap.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Personnel_type");
						}
			 
			 try{
				Query q0 = sessionHQL.createQuery("select count(id) from TB_PERSONNEL_TYPE where personnel_name=:personnel_name and status=:status  and id !=:id");
				q0.setParameter("personnel_name", personnel_name);  
				q0.setParameter("id", id); 
				q0.setParameter("status", PT.getStatus() );
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					PT.setCreated_by(username);
					PT.setCreated_date(date);
					PT.setPersonnel_name(personnel_name);
					if (c == 0) {
						PT.setPersonnel_name(personnel_name);
						
						
						
						sessionHQL.save(PT);
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
					Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:Personnel_type");
		}
		

		@RequestMapping(value = "/admin/getSearchPersonnel_typeMaster", method = RequestMethod.POST)
		public ModelAndView getSearchPersonnel_typeMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "personnel_name1", required = false) String personnel_name1,
				@RequestParam(value = "status1", required = false) String status){
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
				Mmap.put("personnel_name1", personnel_name1);
				ArrayList<ArrayList<String>> list = ptdao.search_Personnel_type_dtl(personnel_name1,status);
				Mmap.put("list", list);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			return new ModelAndView("Personnel_typeTiles","Personnel_typeCMD",new TB_PERSONNEL_TYPE());
		}

		
			@RequestMapping(value = "/Edit_Personnel_type",method=RequestMethod.POST)
			public ModelAndView Edit_Personnel_type(@ModelAttribute("id2") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
					HttpSession sessionEdit,HttpServletRequest request) {
		
				
				 String  roleid = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				 
				TB_PERSONNEL_TYPE authDetails = ptdao.getPersonnel_typeByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Personnel_typeCMD", authDetails);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
				return new ModelAndView("Edit_Personnel_typeTiles");
			}
		
		
		@RequestMapping(value = "/Edit_Personnel_typeAction", method = RequestMethod.POST)
		public ModelAndView Edit_Personnel_typeAction(@ModelAttribute("Edit_Personnel_typeCMD") TB_PERSONNEL_TYPE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 
		    String personnel_name = request.getParameter("personnel_name").trim();
	 		/*if (request.getParameter("personnel_name") == "") {
				model.put("msg", "Please Enter Personnel Name");
				model.put("id2", id);
				return new ModelAndView("redirect:Edit_Personnel_type");
			}*/
		    
		    if (rs.getPersonnel_name().trim().equals("") || rs.getPersonnel_name().equals("null")|| rs.getPersonnel_name().equals(null)) {
		    	TB_PERSONNEL_TYPE authDetails = ptdao.getPersonnel_typeByid((id));
		    	model.put("Edit_Personnel_typeCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Colour Name");
				//model.put("id2", id);
				return new ModelAndView("Edit_Personnel_typeTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PERSONNEL_TYPE authDetails = ptdao.getPersonnel_typeByid((id));
	    		model.put("Edit_Personnel_typeCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Personnel_typeTiles");
			}*/
				 try {
					  Query q0 = session1.createQuery("select count(id) from TB_PERSONNEL_TYPE where personnel_name=:personnel_name and status=:status and id !=:id");
						q0.setParameter("personnel_name", personnel_name);  
						q0.setParameter("id", id);
						q0.setParameter("status", status);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_PERSONNEL_TYPE set personnel_name=:personnel_name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("personnel_name",personnel_name)
											.setString("modified_by", username).setDate("modified_date", new Date())
											.setInteger("id",id)
						    	            .setString("status",status);
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
		                      model.put("msg", "Couldn’t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Personnel_type");
		}
		
	/*	@RequestMapping(value = "/deletepersonnel_typeMasterURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deletecourse_typeMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_PERSONNEL_TYPE where id=:id";
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
			return new ModelAndView("redirect:Personnel_type");
		}*/
		
	@RequestMapping(value = "/deletepersonnel_typeMasterURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deletepersonnel_typeMasterURL(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
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

			String hqlUpdate = "update TB_PERSONNEL_TYPE set modified_by=:modified_by,modified_date=:modified_date,status=:status"
					+ " where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
					.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id)
					.executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Personnel_type");
	}
}
