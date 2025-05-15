package com.controller.mnh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mstr_OpdSplDepartmentDAO;
import com.models.mnh.Tb_Med_Opd_Sp_Department;
import com.models.mnh.Tb_Med_Opd_Sp_Procedure_master;
import com.models.mnh.Tb_Med_Opd_Sp_Subprocedure;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_OpdSplProcedureController {
	@Autowired
	private mstr_OpdSplDepartmentDAO ct;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();

	
	
	@RequestMapping(value = "/admin/mnh_opd_dep", method = RequestMethod.GET)
	public ModelAndView mnh_opd_dep(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 Boolean val = roledao.ScreenRedirect("mnh_opd_dep", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		
		return new ModelAndView("mnh_opd_sp_depTiles","Capture_OPD_SP_Dept_MasterCMD",new Tb_Med_Opd_Sp_Department());
	}
	@RequestMapping(value = "/Capture_OPD_SP_Dept_MasterAction" ,method = RequestMethod.POST )
	public ModelAndView Capture_OPD_SP_Dept_MasterAction(@Valid @ModelAttribute("Capture_OPD_SP_Procedure_MasterCMD") Tb_Med_Opd_Sp_Department cm, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
		
		Boolean val = roledao.ScreenRedirect("mnh_opd_dep", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }
		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
			if(result.hasErrors()) {
				model.put("list", ct.getOPDSpDepartListJdbc("",""));
	        	return new ModelAndView("mnh_opd_sp_depTiles");
	        }
			
			int id = cm.getId() > 0 ? cm.getId() : 0;				
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			try{
			
				Query q0 = sessionHQL.createQuery("select count(id) from Tb_Med_Opd_Sp_Department where upper(dept_name)=:dept_name and upper(status)=:status and id !=:id");
				q0.setParameter("dept_name", cm.getDept_name().toUpperCase());  
				q0.setParameter("status", cm.getStatus().toUpperCase());  
				q0.setParameter("id", id);  
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					if(request.getParameter("dept_name")==null || request.getParameter("dept_name")=="")
					{
						model.put("msg", "Please Enter Department Name");
						return new ModelAndView("redirect:mnh_opd_dep");
					}
					if(request.getParameter("status")==null || request.getParameter("status")=="")
					{
						model.put("msg", "Please Enter Status");
						return new ModelAndView("redirect:mnh_opd_dep");
					}
					cm.setCreated_by(username);
					cm.setCreated_on(date);
					if (c == 0) {
						sessionHQL.save(cm);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");
						return new ModelAndView("redirect:mnh_opd_dep");

					} else {
						model.put("msg", "Data already Exist.");
						return new ModelAndView("redirect:mnh_opd_dep");
					}
				}
				else {
					if(request.getParameter("dept_name")==null || request.getParameter("dept_name")=="")
					{
						model.put("msg", "Please Enter Department Name");
						return new ModelAndView("redirect:mnh_opd_dep");
					}
					if(request.getParameter("status")==null || request.getParameter("status")=="")
					{
						model.put("msg", "Please Enter Status");
						return new ModelAndView("redirect:mnh_opd_dep");
					}
					cm.setModified_by(username);
					cm.setModified_on(date);
					if (c == 0) {
						String msg = ct.updatedept(cm,username);
						model.put("msg", msg);
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
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:mnh_opd_dep");
		}
	@RequestMapping(value = "/admin/OpdSpDepaetmentReport", method = RequestMethod.POST)
	public ModelAndView OpdSpDepaetmentReport(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("dept_name1") String dept_name1,String status1,HttpServletRequest request){
		Boolean val = roledao.ScreenRedirect("mnh_opd_dep", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>>  list =ct.getOPDSpDepartListJdbc(dept_name1,status1);
		if(list.size() == 0){
			Mmap.put("msg", "Data Not Available.");
		}else{
			Mmap.put("list", list);
		}
		Mmap.put("dept_name1", dept_name1);
	 	Mmap.put("status1", status1);
		return new ModelAndView("mnh_opd_sp_depTiles","Capture_OPD_SP_Dept_MasterCMD",new Tb_Med_Opd_Sp_Department());
	}
	@RequestMapping(value = "/deleteOpdDepart" , method = RequestMethod.POST)
	public ModelAndView deleteOpdDepart(@ModelAttribute("id1") int id,BindingResult result,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
		Boolean val = roledao.ScreenRedirect("mnh_opd_dep", session1.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		try {
			
			String hqlUpdate = "delete from Tb_Med_Opd_Sp_Department where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg",liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_opd_dep");
	}
	
	@RequestMapping(value = "/admin/mnh_opd_proce", method = RequestMethod.GET)
	public ModelAndView mnh_opd_proce(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_opd_proce", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
	
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		
		Mmap.put("ml_1", mcommon.getMedDepCode("", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_opd_sp_proceTiles");
	}
	
	@RequestMapping(value = "/Capture_OPD_SP_Procedure_MasterAction", method = RequestMethod.POST)
	public ModelAndView Capture_OPD_SP_Procedure_MasterAction(@ModelAttribute("Capture_OPD_SP_Procedure_MasterCMD") Tb_Med_Opd_Sp_Procedure_master rs1,
			HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
		
		Boolean val = roledao.ScreenRedirect("mnh_opd_proce", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(rs1.getDepartment().getId() == -1){ 
			model.put("msg", "Please Enter Department Name");
			return new ModelAndView("redirect:mnh_opd_proce");
		}
		
		if(rs1.getProc_name() == null || rs1.getProc_name().equals("")){ 
			model.put("msg", "Please Enter Procedure Name");
			return new ModelAndView("redirect:mnh_opd_proce");
		}
		else if(validation.DiseasemmrLength(rs1.getProc_name()) == false){
	 		model.put("msg",validation.proc_nameMSG);
			return new ModelAndView("redirect:mnh_opd_proce");
		}
		
	
		int id = rs1.getId() > 0 ? rs1.getId() : 0;
		
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		try{
			
			Long c= ct.checkExitstingSpProcedureMaster(rs1.getDepartment().getId(),rs1.getProc_name(),id);
	   	    if(c != (long)0) {
	   	    	model.put("msg", "Procedure Name already exists against Department");
	   	    	return new ModelAndView("redirect:mnh_opd_proce");
	   	    }

			if (id == 0) {
				rs1.setCreated_by(username);
				rs1.setCreated_on(date);
				if (c == 0) {
					sessionHQL.save(rs1);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			}
			else {
				rs1.setModified_by(username);
				rs1.setModified_on(date);
				if (c == 0) {
					String msg = ct.update_opd_sp_proc_name(rs1);
					model.put("msg", msg);
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
				model.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
		return new ModelAndView("redirect:mnh_opd_proce");
	}
	
	  @RequestMapping(value = "/spprocedureList",method = RequestMethod.POST)
		public ModelAndView spprocedureList(@ModelAttribute("dept_id1") Integer dept_id1,String proc_name1,String stat,
				ModelMap model,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg){
     	
    
			Boolean val = roledao.ScreenRedirect("mnh_opd_proce", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<Map<String, Object>> list= ct.getspprocedureListJdbc(dept_id1,proc_name1,stat);
			model.put("list", list);
			model.put("ml_1", mcommon.getMedDepCode("", session));
	        model.put("dept_id1", dept_id1);
			model.put("proc_name1", proc_name1);
			model.put("stat", stat);
			return new ModelAndView("mnh_opd_sp_proceTiles");	
		}
	  
	  
	  @RequestMapping(value = "/deletespprocedureURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deletespprocedureURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			Boolean val = roledao.ScreenRedirect("mnh_opd_proce", sessionA.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from Tb_Med_Opd_Sp_Procedure_master where id=:id";
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
			return new ModelAndView("redirect:mnh_opd_proce");
		}
	  
		@RequestMapping(value = "/admin/mnh_opd_sub_proce", method = RequestMethod.GET)
		public ModelAndView mnh_opd_sub_proce(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			String username=(String) session.getAttribute("username");
			int roleid = (Integer)session.getAttribute("roleid");
			int userid = (Integer)session.getAttribute("userId");
			String roleType = (String) session.getAttribute("roleType");
			String accsLvl = (String) session.getAttribute("roleAccess");
			String accssubLvl=(String) session.getAttribute("roleAccess");
			
	
			Boolean val = roledao.ScreenRedirect("mnh_opd_sub_proce", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
			accssubLvl = l1.get(0).get(1);
			accsLvl = l1.get(0).get(7);
			roleType = l1.get(0).get(8);
			

			Mmap.put("r_1", l1);
		
			Mmap.put("ml_1", mcommon.getMedDepCode("", session));
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_opd_sub_proceTiles");
		}
		
		
		 @RequestMapping(value = "/Capture_OPD_SUB_Procedure_MasterAction",method = RequestMethod.POST)
			public ModelAndView Capture_OPD_SUB_Procedure_MasterAction(@ModelAttribute("Capture_OPD_SUB_Procedure_MasterCMD") Tb_Med_Opd_Sp_Subprocedure rs1,
					HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {			
				
	        	Boolean val = roledao.ScreenRedirect("mnh_opd_sub_proce", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}

				if(request.getHeader("Referer") == null ) {
					msg1 = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				String proc_id = request.getParameter("proc_id1");
				
				Integer proc_id1 = 0;
				if (proc_id != "-1" && !proc_id.equals("-1")) {
					proc_id1 = Integer.parseInt(proc_id);
				}
				
				if(rs1.getDepartment_subproc().getId() == -1){ 
					model.put("msg", "Please Enter Department Name");
					return new ModelAndView("redirect:mnh_opd_sub_proce");
				}
				
				else if(validation.DiseasemmrLength(rs1.getSubproc_name()) == false){
			 		model.put("msg",validation.subproc_nameMSG);
					return new ModelAndView("redirect:mnh_opd_sub_proce");
				}
			
			
		
			int id = rs1.getId() > 0 ? rs1.getId() : 0;
			
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			try{
			 
		   	 Long c = ct.checkExitstingSpsubProcedureMaster(rs1.getDepartment_subproc().getId(),rs1.getSubproc_name(),id);
		   	    if(c != (long)0) {
		   	    	model.put("msg", "Sub Procedure Name already exists against Department");
		   	    	return new ModelAndView("redirect:mnh_opd_sub_proce");
		   	    }

				if (id == 0) {
					rs1.setCreated_by(username);
					rs1.setCreated_on(date);
					rs1.setProc_id(proc_id1);
					if (c == 0) {
						sessionHQL.save(rs1);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");

					} 
					if (c > 0)
					{
						model.put("msg", "Data already Exist.");
					}
				}
				else {
					rs1.setProc_id(proc_id1);
					rs1.setModified_by(username);
					rs1.setModified_on(date);
					if (c == 0) {
						String msg = ct.update_opd_sp_subproc_name(rs1);
						model.put("msg", msg);
					} 
					if (c > 0) {
						model.put("msg", "Data already Exist.");
					}
				}
				tx.commit();
			}catch(RuntimeException e){
				try{
					tx.rollback();
					model.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:mnh_opd_sub_proce");
		}
		 
		 
		  @RequestMapping(value = "/deletespsubprocedureURL", method = RequestMethod.POST)
			public @ResponseBody ModelAndView deletespsubprocedureURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				Boolean val = roledao.ScreenRedirect("mnh_opd_sub_proce", sessionA.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from Tb_Med_Opd_Sp_Subprocedure where id=:id";
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
				return new ModelAndView("redirect:mnh_opd_sub_proce");
			}
		  
		  @RequestMapping(value = "/spsubprocedureList",method = RequestMethod.POST)
			public ModelAndView spsubprocedureList(@ModelAttribute("dept_id1") Integer dept_id1,Integer proc_id1,String subproc_name1,String stat,
					ModelMap model,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg){
				
			
				Boolean val = roledao.ScreenRedirect("mnh_opd_sub_proce", session.getAttribute("roleid").toString());
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				List<Map<String, Object>> list= ct.getspsubprocedureListJdbc(dept_id1,proc_id1,subproc_name1,stat);
				model.put("list", list);
				model.put("ml_1", mcommon.getMedDepCode("", session));
				model.put("lsize", list.size());
				model.put("dept_id1", dept_id1);			
				model.put("proc_id1", proc_id1);
				model.put("subproc_name1", subproc_name1);
				model.put("stat", stat);
				return new ModelAndView("mnh_opd_sub_proceTiles");	
			}
	
	
}
