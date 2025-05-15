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
import com.dao.psg.Master.Link_DegreeDao;
import com.models.psg.Master.TB_LINK_DEGREE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_Degree_Controller {
	
	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired
	Link_DegreeDao ld_Dao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	
	
	// -------------------------------Link Degree For page Open// -------------------------------------
		@RequestMapping(value = "/admin/LinkUrl", method = RequestMethod.GET)
		public ModelAndView LinkUrl(ModelMap mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
			//ArrayList<ArrayList<String>> list = COM.search_Company("", 0,"0");
			//Mmap.put("list", list);
			List<ArrayList<String>> list = ld_Dao.search_linkdegree(0,0,"0");
			mmap.put("list", list);
			mmap.put("msg", msg);
			//Mmap.put("getBattalionList", mcommon.getLinkList());
			mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			mmap.put("getSpecializationList", mcommon.getSpecializationList());
			mmap.put("getDegreeList", mcommon.getDegreeList());
			
			return new ModelAndView("linkTiles");
		}
		
	
		// -------------------------------Link Degree save -------------------------------------
		
		
		@RequestMapping(value = "/link_Action", method = RequestMethod.POST)
		public ModelAndView link_Action(@ModelAttribute("link_CMD") TB_LINK_DEGREE co, HttpServletRequest request,
				ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			int id = co.getId() > 0 ? co.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			 if (co.getDegree() == 0) {
	  				model.put("msg", "Please Select Degree");		  				
	  				return new ModelAndView("redirect:LinkUrl");
	  			}
			 else if(co.getSpecialization() == 0) {
	  				model.put("msg", "Please Select Specialization");		  				
	  				return new ModelAndView("redirect:LinkUrl");
	  			}
			
			
			

			try {
				int specialization = Integer.parseInt(request.getParameter("specialization"));
				int degree = Integer.parseInt(request.getParameter("degree"));
				Query q0 = sessionHQL.createQuery("select count(id) from TB_LINK_DEGREE where Degree=:Degree and  specialization=:specialization and status=:status and id !=:id");
				q0.setParameter("Degree", co.getDegree());
				q0.setParameter("status", co.getStatus());
				q0.setParameter("specialization", co.getSpecialization());
				q0.setParameter("id", co.getId());

				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					co.setCreated_by(username);
					co.setCreated_date(date);
					co.setDegree(degree);
					co.setSpecialization(specialization);
					if (c == 0) {
						sessionHQL.save(co);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				else {
					
					if (c == 0) {
						
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:LinkUrl");
		}

		// -------------------------------Link Degree Search -------------------------------------

   @RequestMapping(value = "/admin/getLink_Degree_Master", method = RequestMethod.POST)
public ModelAndView getLink_Degree_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "degree1", required = false) int degree1,
		@RequestParam(value = "specialization1", required = false) int specialization1,
        @RequestParam(value = "status1", required = false) String status1 )

   {
	
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<ArrayList<String>> list = ld_Dao.search_linkdegree(degree1,specialization1,status1);
		Mmap.put("list", list);
		Mmap.put("status1", status1);
		Mmap.put("degree1", degree1);
		Mmap.put("specialization1", specialization1);
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		Mmap.put("getSpecializationList", mcommon.getSpecializationList());
		Mmap.put("getDegreeList", mcommon.getDegreeList());
		
	return new ModelAndView("linkTiles","link_CMD",new TB_LINK_DEGREE());
   }

//-------------------------------Link Delete -------------------------------------


@RequestMapping(value = "/delete_Link_Degree", method = RequestMethod.POST)
public @ResponseBody ModelAndView delete_Link_Degree(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
		HttpSession sessionA, ModelMap model,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
	
	String roleid = sessionA.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
	}
	
	List<String> liststr = new ArrayList<String>();
	try {
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		 
		String hqlUpdate = "delete from TB_LINK_DEGREE where id=:id";
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
	return new ModelAndView("redirect:LinkUrl");
}

//-------------------------------Link Degree edit -------------------------------------

@RequestMapping(value = "/Edit_Link_Degree",method=RequestMethod.POST)
public ModelAndView Edit_Link_Degree(@ModelAttribute("id2") String updateid,ModelMap Mmap,HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
		HttpSession sessionEdit) {
	String roleid = sessionEdit.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
	}
	
		TB_LINK_DEGREE linkdegreeDetails = ld_Dao.getlinkdegreeByid(Integer.parseInt(updateid));
		Mmap.put("Edit_LinkDegreeCMD", linkdegreeDetails);
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		Mmap.put("getSpecializationList", mcommon.getSpecializationList());
		Mmap.put("getDegreeList", mcommon.getDegreeList());
		Mmap.put("msg", msg);
		return new ModelAndView("EditLinkDegreeTiles");
		
}



@RequestMapping(value = "/Edit_Link_Degree_Action", method = RequestMethod.POST)
public ModelAndView Edit_Link_Degree_Action(@ModelAttribute("Edit_LinkDegreeCMD") TB_LINK_DEGREE rs,
		HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("LinkUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
	}
	
	String username = session.getAttribute("username").toString();

	int id = Integer.parseInt(request.getParameter("id"));
	//String state_name = request.getParameter("state_name");
	String status = request.getParameter("status");
	

	Session session1 = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session1.beginTransaction();
		 
    if (rs.getDegree() == 0) {
			model.put("msg", "Please Select Degree");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_Link_Degree");
		}
 else if(rs.getSpecialization() == 0) {
			model.put("msg", "Please Select Specialization");
			model.put("id2", id);
			return new ModelAndView("redirect:Edit_Link_Degree");
		}

    
    
    try {
			
			 Query q0 = session1.createQuery("select count(id) from TB_LINK_DEGREE where degree=:degree and specialization=:specialization and status=:status and id !=:id");
				q0.setParameter("degree", rs.getDegree()); 
				q0.setParameter("status", status); 
				q0.setParameter("specialization", rs.getSpecialization());
				q0.setParameter("id", rs.getId()); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					String hql = "update TB_LINK_DEGREE set degree=:degree,specialization=:specialization,status=:status,modified_by=:modified_by,modified_date=:modified_date"
							+ " where id=:id";
			    	  Query query = session1.createQuery(hql)
			    			  .setInteger("specialization",rs.getSpecialization())
			    			  	.setInteger("degree",rs.getDegree())
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
	return new ModelAndView("redirect:LinkUrl");
}

}


