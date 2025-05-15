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
import com.dao.psg.Master.Medal_TypeDAO;
import com.models.psg.Master.TB_MEDAL_TYPE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Medal_Type_Controller {

	@Autowired
	private Medal_TypeDAO Mdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	 @RequestMapping(value = "/admin/Medal_TypeUrl", method = RequestMethod.GET)
	 public ModelAndView Medal_TypeUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);
		 Mmap.put("getMedalTypeList1", mcommon.getMedalList());
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());

		 Mmap.put("list", Mdao.search_relation("0","","","active"));
		 return new ModelAndView("Medal_TypeTiles");
	 }
	 
	 /******************************Save For Medal***********************************/
	 
	 @RequestMapping(value = "/Medal_TypeAction",method=RequestMethod.POST)
		public ModelAndView Medal_TypeAction(@ModelAttribute("Medal_TypeCMD") TB_MEDAL_TYPE MT,
				HttpServletRequest request,ModelMap model,HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}				 
				int id = MT.getId() > 0 ? MT.getId() : 0;				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String medal_name = request.getParameter("medal_name").trim();
				String medal_abberivation = request.getParameter("medal_abberivation").trim();
				
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();

				 if (MT.getMedal_type().equals("0") || MT.getMedal_type().equals("null")|| MT.getMedal_type().equals(null)) {
					  	model.put("msg", "Please Enter Medal Type");
					  	return new ModelAndView("redirect:Medal_TypeUrl");
					}
				 if (medal_name.equals("") || medal_name.equals("null")|| medal_name.equals(null)) {
					 	model.put("msg", "Please Enter Medal Name");
						return new ModelAndView("redirect:Medal_TypeUrl");
					}
				 if (medal_abberivation.equals("") || medal_abberivation.equals("null")|| medal_abberivation.equals(null)) {
					 	model.put("msg", "Please Enter Medal Abbreviation");
						return new ModelAndView("redirect:Medal_TypeUrl");
					}
				 if (MT.getStatus() == "inactive" || MT.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Medal_TypeUrl");
					}
				 				
				try{					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_MEDAL_TYPE where medal_type=:medal_type and medal_name=:medal_name and medal_abberivation=:medal_abberivation and status=:status  and id !=:id");
					q0.setParameter("medal_type", MT.getMedal_type());
					q0.setParameter("medal_name", MT.getMedal_name());  		
					q0.setParameter("medal_abberivation", MT.getMedal_abberivation());
					q0.setParameter("status", MT.getStatus());
					q0.setParameter("id", id); 
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						MT.setCreated_by(username);
						MT.setCreated_date(date);
						MT.setMedal_name(medal_name);
						MT.setMedal_abberivation(medal_abberivation);
						
						if (c == 0) {
							sessionHQL.save(MT);
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
				return new ModelAndView("redirect:Medal_TypeUrl");
			}
	 
	 /******************************Search For Medal***********************************/
		
		@RequestMapping(value = "/admin/GetSearch_Medal_Type", method = RequestMethod.POST)
		public ModelAndView GetSearch_Medal_Type(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "medal_type1", required = false) String medal_type,
				@RequestParam(value = "medal_name1", required = false) String medal_name,
				@RequestParam(value = "medal_abberivation1", required = false) String medal_abberivation,
	        	@RequestParam(value = "status1", required = false) String status){
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				if(!medal_type.equals("0")) {
					Mmap.put("medal_type1", medal_type);
				}
				if(!medal_name.equals("")) {
					Mmap.put("medal_name1", medal_name);
				}
				if(!medal_abberivation.equals("")) {
					Mmap.put("medal_abberivation1", medal_abberivation);
				}
				if(!status.equals("")) {
					Mmap.put("status1", status);
				}
				ArrayList<ArrayList<String>> list = Mdao.search_relation(medal_type,medal_name,medal_abberivation,status);
					Mmap.put("list", list);
					 Mmap.put("getMedalTypeList1", mcommon.getMedalList());
					 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			return new ModelAndView("Medal_TypeTiles","Medal_TypeCMD",new TB_MEDAL_TYPE());
		}

		
		 /******************************Delete For Medal***********************************/
	    
		/*	@RequestMapping(value = "/Delete_Medal_Type" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Delete_Medal_Type(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_MEDAL_TYPE where id=:id";
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
				return new ModelAndView("redirect:Medal_TypeUrl");
			}*/
		
		@RequestMapping(value = "/Delete_Medal_Type", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Medal_Type(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
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
				 
				 String hqlUpdate = "update TB_MEDAL_TYPE set modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Medal_TypeUrl");
		}
			
			/******************************Update For Medal***********************************/ 

			
			@RequestMapping(value = "/Edit_Medal_TypeUrl",method=RequestMethod.POST)
			public ModelAndView Edit_Medal_TypeUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit) {	
				 String  roleid = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}	
				TB_MEDAL_TYPE authDetails = Mdao.getRelationByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Medal_TypeCMD", authDetails);
				Mmap.put("msg", msg);
				Mmap.put("getMedalTypeList1", mcommon.getMedalList());
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				return new ModelAndView("Edit_Medal_TypeTiles");
			}
			
			@RequestMapping(value = "/Edit_Medal_TypeAction", method = RequestMethod.POST)
			public ModelAndView Edit_Medal_TypeAction(@ModelAttribute("Edit_Medal_TypeCMD") TB_MEDAL_TYPE rs,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
				 	if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String medal_type = request.getParameter("medal_type");
				String medal_name = request.getParameter("medal_name").trim();
				String medal_abberivation = request.getParameter("medal_abberivation").trim();
				String status = request.getParameter("status");
				//String decoration = request.getParameter("decoration");
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
				
			      if (rs.getMedal_type().equals("0") || rs.getMedal_type().equals("null")|| rs.getMedal_type().equals(null)) {
						TB_MEDAL_TYPE authDetails = Mdao.getRelationByid((id));
						model.put("Edit_Medal_TypeCMD", authDetails);
						model.put("getMedalTypeList1", mcommon.getMedalList());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Please Enter Medal Type");
						//model.put("id2", id);
					  	return new ModelAndView("Edit_Medal_TypeTiles");
					}
				 if (medal_name.equals("") || medal_name.equals("null")|| medal_name.equals(null)) {
					    TB_MEDAL_TYPE authDetails = Mdao.getRelationByid((id));
						model.put("Edit_Medal_TypeCMD", authDetails);
						model.put("getMedalTypeList1", mcommon.getMedalList());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
					 	model.put("msg", "Please Enter Medal Name");
					 	//model.put("id2", id);
						return new ModelAndView("Edit_Medal_TypeTiles");
					}
				 if (medal_abberivation.equals("") || medal_abberivation.equals("null")|| medal_abberivation.equals(null)) {
					 	TB_MEDAL_TYPE authDetails = Mdao.getRelationByid((id));
						model.put("Edit_Medal_TypeCMD", authDetails);
						model.put("getMedalTypeList1", mcommon.getMedalList());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
					 	model.put("msg", "Please Enter Medal Abbreviation");
					 	//model.put("id2", id);
						return new ModelAndView("Edit_Medal_TypeTiles");
					}
				/* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					 TB_MEDAL_TYPE authDetails = Mdao.getRelationByid((id));
						model.put("Edit_Medal_TypeCMD", authDetails);
						model.put("getMedalTypeList1", mcommon.getMedalList());
						model.put("getStatusMasterList", mcommon.getStatusMasterList());
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("Edit_Medal_TypeTiles");
					}*/
//				 if (rs.getDecoration().equals("") || rs.getDecoration().equals("null")|| rs.getDecoration().equals(null)) {
//					 	model.put("msg", "Please Enter Decoration");
//					 	model.put("id2", id);
//						return new ModelAndView("redirect:Edit_Medal_TypeUrl");
//					}
				 
				 	try {
						 Query q0 = session1.createQuery("select count(id) from TB_MEDAL_TYPE where medal_type=:medal_type and medal_name=:medal_name and medal_abberivation=:medal_abberivation and status=:status and id !=:id");
							q0.setParameter("medal_type", medal_type); 
							q0.setParameter("medal_name", medal_name);  		
							q0.setParameter("medal_abberivation", medal_abberivation); 
							q0.setParameter("status", status); 
							//q0.setParameter("decoration", decoration);  
							q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_MEDAL_TYPE set medal_type=:medal_type,medal_name=:medal_name,"
								 		+ "medal_abberivation=:medal_abberivation,modified_by=:modified_by,modified_date=:modified_date,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("medal_type",medal_type)
						    			  	.setString("status",status)
						    				.setString("medal_name",medal_name)
						    				.setString("medal_abberivation",medal_abberivation)
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
				return new ModelAndView("redirect:Medal_TypeUrl");
			}
	}
