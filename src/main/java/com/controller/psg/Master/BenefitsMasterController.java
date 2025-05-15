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
import com.dao.psg.Master.BenefitsDAO;
import com.dao.psg.Master.StateDao;
import com.models.psg.Master.TB_PSG_BENEFITS_MASTER;
import com.models.psg.Master.TB_STATE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class BenefitsMasterController {
	
	@Autowired
	private BenefitsDAO Bdao;
	@Autowired
	private RoleBaseMenuDAO roledao;	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	 @RequestMapping(value = "/admin/Benefits_Url", method = RequestMethod.GET)
	 public ModelAndView Benefits_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);		 
		ArrayList<ArrayList<String>> list = Bdao.search_Benefits_name(0,"","active");
	   Mmap.put("list", list);
		 Mmap.put("getAgencyList", mcommon.getAgencyList("", session));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 return new ModelAndView("BenefitsTiles");
	 }
	 
	 
	 /////------------save-----------------------------------------------------------
	 
	 @RequestMapping(value = "/BenefitsAction",method=RequestMethod.POST)
		public ModelAndView BenefitsAction(@ModelAttribute("BenefitsCMD") TB_PSG_BENEFITS_MASTER st,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			
					 
				int id = st.getId() > 0 ? st.getId() : 0;
				
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String benefits_name = request.getParameter("benefits_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 

						if (st.getAgency_id() == 0 || st.getAgency_id() == null || st.getAgency_id().equals(null)) {
							model.put("msg", "Please Select Agency");
							return new ModelAndView("redirect:Benefits_Url");
						}
				
						if (benefits_name.equals("") || benefits_name.equals("null") || benefits_name.equals(null)) {
							model.put("msg", "Please Enter Benefits Name");
							return new ModelAndView("redirect:Benefits_Url");
						}
				
						if (st.getStatus() == "inactive" || st.getStatus().equals("inactive")) {
				
							model.put("msg", "Only Select Active Status.");
				
							return new ModelAndView("redirect:Benefits_Url");
				
						}
				try{
					
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_BENEFITS_MASTER where benefits_name=:benefits_name and status=:status and id !=:id");
					q0.setParameter("benefits_name", st.getBenefits_name());  
					q0.setParameter("status", st.getStatus());
					
					q0.setParameter("id", id); 
					
					
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						st.setCreated_by(username);
						st.setCreated_date(date);
						st.setBenefits_name(benefits_name);
						if (c == 0) {
							sessionHQL.save(st);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					else {
						
						if (c == 0) {
							//String msg = rankdao.updaterankcode(st);
							//model.put("msg", msg);
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
						model.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Benefits_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/getSearch_Benefits_Master" , method = RequestMethod.POST)
		public ModelAndView getSearch_Benefits_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "agency_id1", required = false) int agency_id1,
				@RequestParam(value = "benefits_name1", required = false) String benefits_name1,
				@RequestParam(value = "status1", required = false) String status1){
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 ArrayList<ArrayList<String>> list = Bdao.search_Benefits_name(agency_id1,benefits_name1,status1);
		 
		 		 Mmap.put("list", list);
		         Mmap.put("agency_id1", agency_id1);
				 Mmap.put("benefits_name1", benefits_name1);
				 Mmap.put("status1", status1);
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("getAgencyList", mcommon.getAgencyList("", session));
					
					
			return new ModelAndView("BenefitsTiles","BenefitsCMD",new TB_PSG_BENEFITS_MASTER());
		}
	 
	 @RequestMapping(value = "/Edit_Benefits")
		public ModelAndView Edit_Benefits(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpSession session,
				HttpSession sessionEdit) {
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 TB_PSG_BENEFITS_MASTER BenefitsDetails = Bdao.getBenefitsByid(Integer.parseInt(updateid));
				Mmap.put("Edit_BenefitsCMD", BenefitsDetails);
				Mmap.put("getAgencyList", mcommon.getAgencyList("", sessionEdit));
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_BenefitsTiles");
		}
		
		
		@RequestMapping(value = "/Edit_Benefits_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Benefits_Action(@ModelAttribute("Edit_BenefitsCMD") TB_PSG_BENEFITS_MASTER rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			
			
			String agency_id = request.getParameter("agency_id").trim();
			String benefits_name = request.getParameter("benefits_name").trim();
			String status = request.getParameter("status");
			
			
				if (rs.getAgency_id() == 0 || rs.getAgency_id() == null || rs.getAgency_id().equals(null)) {
					TB_PSG_BENEFITS_MASTER BenefitsDetails = Bdao.getBenefitsByid((id));
					model.put("Edit_BenefitsCMD", BenefitsDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Select Country");
					//model.put("id2", id);
					return new ModelAndView("Edit_BenefitsTiles");
				}
		
				if (benefits_name.equals("") || benefits_name.equals("null") || benefits_name.equals(null)) {
					TB_PSG_BENEFITS_MASTER BenefitsDetails = Bdao.getBenefitsByid((id));
					model.put("Edit_BenefitsCMD", BenefitsDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Enter State");
//					model.put("id2", id);
					return new ModelAndView("Edit_BenefitsTiles");
				}
				
				/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
					TB_PSG_BENEFITS_MASTER BenefitsDetails = Bdao.getBenefitsByid((id));
					model.put("Edit_BenefitsCMD", BenefitsDetails);
					model.put("country_id", mcommon.getMedCountryName("", session));
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_BenefitsTiles");
		
				}*/
				
				
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_BENEFITS_MASTER where agency_id=:agency_id and benefits_name=:benefits_name and status=:status and id !=:id");
						q0.setParameter("benefits_name", benefits_name); 
						q0.setParameter("status", status); 
						q0.setParameter("agency_id", rs.getAgency_id());
						q0.setParameter("id", rs.getId()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_BENEFITS_MASTER set agency_id=:agency_id,benefits_name=:benefits_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  .setInteger("agency_id",rs.getAgency_id())
					    			  	.setString("benefits_name",benefits_name)
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
			return new ModelAndView("redirect:Benefits_Url");
		}
		
		///delete
		
		@RequestMapping(value = "/delete_Benefits", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Benefits(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Benefits_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_BENEFITS_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
				 
				//String hqlUpdate = "delete from TB_RELIGION where religion_id=:religion_id";
				
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
			return new ModelAndView("redirect:Benefits_Url");
		}
	 

}
