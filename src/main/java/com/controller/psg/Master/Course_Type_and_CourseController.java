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
import com.dao.psg.Master.Course_TypeDAO;
import com.models.psg.Master.TB_COURSE_TYPE;
import com.persistance.util.HibernateUtil;



@Controller

@RequestMapping(value = {"admin","/","user"})

public class Course_Type_and_CourseController {



	

	@Autowired

	private Course_TypeDAO ctdao;

	@Autowired

	private RoleBaseMenuDAO roledao;

	Psg_CommonController mcommon = new Psg_CommonController();

	

	// ------------------------------- For page Open -------------------------------------

		

	 @RequestMapping(value = "/admin/Course_Type_and_Course", method = RequestMethod.GET)

	 public ModelAndView Course_Type_and_Course(ModelMap Mmap, HttpSession session,

			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		 

		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");

			}

		 

		 ArrayList<ArrayList<String>> list = ctdao.search_Course_type("0","","","active");

		 Mmap.put("list", list);

		 //Mmap.put("getPersonnelNameList",getPersonnelNameList());
		 
		 Mmap.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

		// Mmap.put("getCourseTypeLists", mcommon.getCourseTypeLists());

		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());

		 Mmap.put("msg", msg);

		 return new ModelAndView("Course_Type_and_CourseTiles");

	 }

		 

		

	// ------------------------------- For save -------------------------------------

	 

	@RequestMapping(value = "/Course_typeAction", method = RequestMethod.POST)

	public ModelAndView Course_typeAction(@ModelAttribute("Course_typeCMD") TB_COURSE_TYPE CT, BindingResult result,

			HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

		int id = CT.getId() > 0 ? CT.getId() : 0;

		Date date = new Date();

		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

 		Transaction tx = sessionHQL.beginTransaction();

		

 		//String course_type = request.getParameter("course_type");

 		String course_name = request.getParameter("course_name").trim();

 		String rank_type = request.getParameter("rank_type");

 		String course_gp = request.getParameter("course_gp").trim();

 		String status = request.getParameter("status");

 	

 		/*if (Integer.parseInt(request.getParameter("rank_type")) == 0 || request.getParameter("rank_type") == "0") {

			tx.rollback();

			Mmap.put("msg", "Please Select Rank Type");

			return new ModelAndView("redirect:Course_Type_and_Course");

		}*/

 		

 		if (CT.getRank_type() == "0" || CT.getRank_type() == null || CT.getRank_type().equals(null)) {

 			Mmap.put("msg", "Please Select Category");

			return new ModelAndView("redirect:Course_Type_and_Course");

		}

 		

 		if (course_name.trim().equals("") || course_name.equals("null")|| course_name.equals(null)) {

 			Mmap.put("msg", "Please Enter Course Name");

			 return new ModelAndView("redirect:Course_Type_and_Course");

			}

 		

 		/*if (request.getParameter("course_name").trim() == "") {

			tx.rollback();

			Mmap.put("msg", "Please Enter Course Name");

			return new ModelAndView("redirect:Course_Type_and_Course");

		}*/

 			

	/*	if (request.getParameter("course_gp").trim() == "") {

			tx.rollback();

			Mmap.put("msg", "Please Enter Course Group");

			return new ModelAndView("redirect:Course_Type_and_Course");

		}*/

		if (course_gp.trim().equals("") || course_gp.equals("null")|| course_gp.equals(null)) {

			Mmap.put("msg", "Please Enter Abbreviation");

			 return new ModelAndView("redirect:Course_Type_and_Course");

			}

		

		/*if (Integer.parseInt(request.getParameter("course_type")) == 0 || request.getParameter("course_type") == "0") {

			tx.rollback();

			Mmap.put("msg", "Please Enter Course Type");

			return new ModelAndView("redirect:Course_Type_and_Course");

		}*/

		 if (CT.getStatus() == "inactive" || CT.getStatus().equals("inactive")) {

			 Mmap.put("msg", "Only Select Active Status.");

				return new ModelAndView("redirect:Course_Type_and_Course");

			}

		

		try{

			Query q0 = sessionHQL.createQuery("select count(id) from TB_COURSE_TYPE where course_name=:course_name and status=:status and id !=:id");

			q0.setParameter("course_name", course_name);  

			q0.setParameter("status", status);

			q0.setParameter("id", id); 

			Long c = (Long) q0.uniqueResult();



			if (id == 0) {

				CT.setCreated_by(username);

				CT.setCreated_date(date);

				if (c == 0) {

					//CT.setCourse_type(course_type);

					CT.setCourse_name(course_name);

					CT.setRank_type(rank_type);

					CT.setCourse_gp(course_gp);

					CT.setStatus(status);

					

					sessionHQL.save(CT);

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

				Mmap.put("msg", "Couldn?t roll back transaction " + rbe);

			}

			throw e;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

		return new ModelAndView("redirect:Course_Type_and_Course");

	}



	public List<String> getPersonnelNameList() {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL

					.createQuery("SELECT id,personnel_name FROM TB_PERSONNEL_TYPE order by personnel_name");

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}

	

	// -------------------------SEARCH  -------------------------------------

	

	@RequestMapping(value = "/admin/getSearchCourse_typeMaster", method = RequestMethod.POST)

	public ModelAndView getSearchCourse_typeMaster(ModelMap Mmap,HttpSession session,HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "rank_type1", required = false) String rank_type1,

			@RequestParam(value = "course_name1", required = false) String course_name1,

//			@RequestParam(value = "course_type1", required = false) String course_type1,

			@RequestParam(value = "course_gp1", required = false) String course_gp1,

			@RequestParam(value = "status1", required = false) String status1){

		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

		 ArrayList<ArrayList<String>> list = ctdao.search_Course_type(rank_type1,course_name1,course_gp1,status1);

			Mmap.put("rank_type1", rank_type1);

			Mmap.put("course_name1", course_name1);

//			Mmap.put("course_type1", course_type1);

			Mmap.put("course_gp1", course_gp1);

			Mmap.put("status1", status1);

			

			 Mmap.put("list", list);

			// Mmap.put("getPersonnelNameList",getPersonnelNameList());
			 
			 Mmap.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

//			 Mmap.put("getCourseTypeLists", mcommon.getCourseTypeLists());

			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());

		return new ModelAndView("Course_Type_and_CourseTiles");

	}



	// -------------------------Edit For page Open -------------------------------------

	

	@RequestMapping(value = "/Edit_Course_Type_and_Course",method=RequestMethod.POST)

	public ModelAndView Edit_Course_Type_and_Course(@ModelAttribute("id2") String updateid,ModelMap Mmap,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,

			HttpSession sessionEdit) {

		 String  roleid = sessionEdit.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

			TB_COURSE_TYPE authDetails = ctdao.getcourse_typeByid(Integer.parseInt(updateid));

			 Mmap.put("Edit_Course_typeCMD", authDetails);

			// Mmap.put("getPersonnelNameList",getPersonnelNameList());
			 Mmap.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());

			 Mmap.put("msg", msg);

//			 Mmap.put("getCourseTypeLists", mcommon.getCourseTypeLists());

		return new ModelAndView("Edit_Course_Type_and_CourseTiles");

	}

	

	// -------------------------Edit Action -------------------------------------

	

	@RequestMapping(value = "/Edit_Course_typeAction", method = RequestMethod.POST)

	public ModelAndView Edit_Course_typeAction(@ModelAttribute("Edit_Course_typeCMD") TB_COURSE_TYPE rs,

			HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

		String username = session.getAttribute("username").toString();



		int id = Integer.parseInt(request.getParameter("id"));

		

		String course_name = request.getParameter("course_name").trim();

 		String course_gp = request.getParameter("course_gp").trim();

		

		Session session1 = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session1.beginTransaction();

        

       /* if (Integer.parseInt(request.getParameter("rank_type")) == 0 || request.getParameter("rank_type") == "0") {

			model.put("msg", "Please Select Rank Type");

			model.put("id2", id);

			return new ModelAndView("redirect:Edit_Course_Type_and_Course");

		}	

 		if (request.getParameter("course_name") == "") {

			model.put("msg", "Please Enter Course Name");

			model.put("id2", id);

			return new ModelAndView("redirect:Edit_Course_Type_and_Course");

		}

 		

// 			if (Integer.parseInt(request.getParameter("course_type")) == 0 || request.getParameter("course_type") == "0") {

//			model.put("msg", "Please Enter Course Type");

//			model.put("id2", id);

//			return new ModelAndView("redirect:Edit_Course_Type_and_Course");

//		}	

		if (request.getParameter("course_gp") == "") {

			model.put("msg", "Please Enter Course Group");

			model.put("id2", id);

			return new ModelAndView("redirect:Edit_Course_Type_and_Course");

		}*/

        

					if (rs.getRank_type() == "0" || rs.getRank_type() == null || rs.getRank_type().equals(null)) {

						TB_COURSE_TYPE authDetails = ctdao.getcourse_typeByid((id));

						model.put("Edit_Course_typeCMD", authDetails);

						//model.put("getPersonnelNameList", getPersonnelNameList());
						
						model.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

						model.put("getStatusMasterList", mcommon.getStatusMasterList());

						model.put("msg", "Please Select Category");

						return new ModelAndView("Edit_Course_Type_and_CourseTiles");

					}

			

					if (course_name.trim().equals("") || course_name.equals("null")

							|| course_name.equals(null)) {

						TB_COURSE_TYPE authDetails = ctdao.getcourse_typeByid((id));

						model.put("Edit_Course_typeCMD", authDetails);

						//model.put("getPersonnelNameList", getPersonnelNameList());
						
						model.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

						model.put("getStatusMasterList", mcommon.getStatusMasterList());

						model.put("msg", "Please Enter Course Name");

						return new ModelAndView("Edit_Course_Type_and_CourseTiles");

					}

					if (course_gp.trim().equals("") || course_gp.equals("null") || course_gp.equals(null)) {

						TB_COURSE_TYPE authDetails = ctdao.getcourse_typeByid((id));

						model.put("Edit_Course_typeCMD", authDetails);

						//model.put("getPersonnelNameList", getPersonnelNameList());
						
						model.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

						model.put("getStatusMasterList", mcommon.getStatusMasterList());

						model.put("msg", "Please Enter Abbreviation");

						return new ModelAndView("Edit_Course_Type_and_CourseTiles");

					}

					/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {

						TB_COURSE_TYPE authDetails = ctdao.getcourse_typeByid((id));

						model.put("Edit_Course_typeCMD", authDetails);

						//model.put("getPersonnelNameList", getPersonnelNameList());
						
						model.put("getServiceCategoryList",mcommon.getServiceCategoryListJCO());

						model.put("getStatusMasterList", mcommon.getStatusMasterList());

						model.put("msg", "Only Select Active Status.");

						return new ModelAndView("Edit_Course_Type_and_CourseTiles");

			

					}*/

		String status = request.getParameter("status");

			 try {

					

		        	Query q0 = session1.createQuery("select count(id) from TB_COURSE_TYPE where course_name=:course_name and status=:status and id !=:id");

					q0.setParameter("course_name", course_name);

					q0.setParameter("status", status); 

					q0.setParameter("id", id); 

						Long c = (Long) q0.uniqueResult();

						

						if(c==0) {

							String hql = "update TB_COURSE_TYPE set rank_type=:rank_type,course_name=:course_name,course_gp=:course_gp,status=:status,modified_by=:modified_by,modified_date=:modified_date"

									+ " where id=:id";

				                                   

				    	  Query query = session1.createQuery(hql)

				    			  	.setString("rank_type",rs.getRank_type())

				    			  	.setString("course_name", course_name)

									//.setString("course_type", rs.getCourse_type())

									.setString("course_gp",course_gp)

									.setString("status",rs.getStatus())

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

	                      model.put("msg", "Couldn?t roll back transaction " + rbe);

	              }

	              throw e;

	             

			}finally{

				if(session1!=null){

					session1.close();

				}

			}

		return new ModelAndView("redirect:Course_Type_and_Course");

	}

	

	

/*	@RequestMapping(value = "/deletecourse_typeMasterURL", method = RequestMethod.POST)

	public @ResponseBody ModelAndView deletecourse_typeMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,

			HttpSession sessionA, ModelMap model,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		List<String> liststr = new ArrayList<String>();

		try {

			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			 Transaction tx = sessionHQL.beginTransaction();

			 

			String hqlUpdate = "delete from TB_COURSE_TYPE where id=:id";

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

		return new ModelAndView("redirect:Course_Type_and_Course");

	}*/

	

	@RequestMapping(value = "/deletecourse_typeMasterURL", method = RequestMethod.POST)

	public @ResponseBody ModelAndView deletecourse_typeMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,

			HttpSession sessionA, ModelMap model,

			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		
		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	

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

			 

			 String hqlUpdate = "update TB_COURSE_TYPE set modified_by=:modified_by,modified_date=:modified_date,status=:status"

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

		return new ModelAndView("redirect:Course_Type_and_Course");

	}

			

}

