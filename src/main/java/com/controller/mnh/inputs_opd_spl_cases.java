package com.controller.mnh;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;

import com.dao.mnh.mnh_input_opd_spl_casesDAO;
import com.models.mnh.Med_OPD_Investigation_Model;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class inputs_opd_spl_cases {
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	
	@Autowired
	private mnh_input_opd_spl_casesDAO opd_spl;

	MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();

	@RequestMapping(value = "/admin/getMedProSubProCode", method = RequestMethod.POST)
	public ModelAndView getMedProSubProCode(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus,
			@RequestParam(value = "quater1", required = false) String quater,
			@RequestParam(value = "dept_id123", required = true) String dept_id,
			@RequestParam(value = "yr1", required = false) String yr,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "cmd1", required = false) String cmd) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_input_opd_spl_cases", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		try {
			
			if (unit_name == " ") {

				Mmap.put("msg", "Please Enter the Unit Name");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			if (sus.equals("")) {
				Mmap.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			 if(validation.sus_noLength(sus) == false){
				 Mmap.put("msg",validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			    }
			if (quater.equals("")) {
				Mmap.put("msg", "Please Select Quarter");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			if (Integer.parseInt(dept_id) == 0) {
				Mmap.put("msg", "Please Select Department Name");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			if (yr.equals("")) {
				Mmap.put("msg", "Please Enter the Year");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			 if(validation.checkYearLength(yr) == false){
				 Mmap.put("msg",validation.yearMSG);
					return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			    }
		} catch (Exception e) {
			// TODO: handle exception
		}

		Mmap.put("list", opd_spl.getMedProSubProCode(sus, quater, Integer.parseInt(dept_id), yr));
		Mmap.put("msg", msg);
		Mmap.put("sus", sus);
		Mmap.put("quater", quater);
		Mmap.put("dept_id", dept_id);
		Mmap.put("yr", yr);
		Mmap.put("unit_name", unit_name);
		Mmap.put("cmd", cmd);
		Mmap.put("ml_1", mcommon.getMedDepCode("", session));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		return new ModelAndView("mnh_input_opd_spl_casesTiles");
	}

	
	@RequestMapping(value = "/admin/mnh_input_opd_spl_cases", method = RequestMethod.GET)
	public ModelAndView mnh_input_opd_spl_cases(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String sus, String quater, String dept_id,
			String yr, String unit_name) {
		
		Boolean val = roledao.ScreenRedirect("mnh_input_opd_spl_cases", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		
		Mmap.put("ml_1", mcommon.getMedDepCode("", session));
		Mmap.put("msg", msg);
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		return new ModelAndView("mnh_input_opd_spl_casesTiles");
	}

	////////////////////////////

	@RequestMapping(value = "/Capture_OPD_SPL_Action", method = RequestMethod.POST)
	public ModelAndView Capture_OPD_SPL_Action(@ModelAttribute("Capture_OPD_SPLCMD") Med_OPD_Investigation_Model lm,
			BindingResult result, HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_input_opd_spl_cases", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		
		
		String sus_no = request.getParameter("sus_no");
		String cmd = request.getParameter("cmd");
		String quater = request.getParameter("quater");
		String dept_id = request.getParameter("dept_id123");
	
		if (sus_no.equals("")) {
				Mmap.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
		 if(validation.sus_noLength(request.getParameter("sus_no")) == false){
			 Mmap.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
		    }
			if (cmd.equals("")) {
				Mmap.put("msg", "Please Enter the Command");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			if (quater.equals("-1")) {
				Mmap.put("msg", "Please Select Quarter");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
		 	if (request.getParameter("department_opd_spl_cases.id").equals("-1")) {
				Mmap.put("msg", "Please Select Department");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			
			
			if (request.getParameter("yr").equals("") || request.getParameter("yr") == null) {
				Mmap.put("msg", "Please Enter the Year");
				return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			}
			
			 if(validation.checkYearLength(request.getParameter("yr")) == false){
				 Mmap.put("msg",validation.yearMSG);
					return new ModelAndView("redirect:mnh_input_opd_spl_cases");
			    }
			 int year = Integer.parseInt(request.getParameter("yr"));
			 
			 Date date_year_i = null;
			 DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = new Date();
			 String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 date_year_i = formatter2.parse(date1);
			 SimpleDateFormat YY = new SimpleDateFormat("yyyy");
			 int year2 = Integer.parseInt(YY.format(date_year_i));  
			 
			 
			 if (year > year2) {
				 Mmap.put("msg", "Future Year cannot be allowed");
					return new ModelAndView("redirect:mnh_input_opd_spl_cases");
				}


		String username = session.getAttribute("username").toString();
		int count = 0;
		if (!request.getParameter("count").equals("")) {
			count = Integer.parseInt(request.getParameter("count"));
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		int flushcount = 0;
		int action = 0;
		if (request.getParameter("action1") != "" & !request.getParameter("action1").equals("")
				& request.getParameter("action1") != null) {
			action = Integer.parseInt(request.getParameter("action1"));
		}

	
		
		for (int i = 1; i <= count; i++) {
			int opd_count = Integer.parseInt(request.getParameter("opd_count" + i));
			int proc_id = Integer.parseInt(request.getParameter("proc_id" + i));
			int subproc_id = Integer.parseInt(request.getParameter("subproc_id" + i));
			
			int sum=0;
			int t1=0;
			

			
			
			for (int j = 0; j < opd_count; j++) {
				   sum = sum + (opd_count);
				
				   
				   //sum=sum + (int) (long) list.get(i).get("app");
				  int ab111 = (int) (sum + (long) opd_count(j));
				 
				}
			     Mmap.put("total1",sum);
			lm.setOpd_count(opd_count);
			lm.setCreated_by(username);
			lm.setCreated_on(new Date());
			lm.setSus_no(sus_no);
			lm.setQuater(quater);
			lm.setProc_id(proc_id);
			lm.setSubproc_id(subproc_id);

			sessionHQL.flush();
			sessionHQL.clear();
			if (action == 0) {
				lm.setCreated_by(username);
				lm.setCreated_on(new Date());
				sessionHQL.save(lm);
				sessionHQL.flush();
				sessionHQL.clear();
				if (i == count) {
					Mmap.put("msg", "Data saved Successfully");
				}

			} else {
				
				for (int j = 0; j < opd_count; j++) {
					   sum = sum + (opd_count);
					
					   
					   //sum=sum + (int) (long) list.get(i).get("app");
					  int ab111 = (int) (sum + (long) opd_count(j));
					
					}
				int id = Integer.parseInt(String.valueOf(request.getParameter("action" + i)));
				lm.setId(id);
				lm.setOpd_count(opd_count);
				lm.setModified_by(username);
				lm.setModified_on(new Date());
				lm.setSus_no(sus_no);
				lm.setProc_id(proc_id);
				lm.setSubproc_id(subproc_id);

				sessionHQL.update(lm);
				sessionHQL.flush();
				sessionHQL.clear();
				if (i == count) {
					Mmap.put("msg", "Data Updated Successfully");
				}

			}

		}
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		
		tx.commit();
		return new ModelAndView("redirect:mnh_input_opd_spl_cases");
	}


	private long opd_count(int j) {
		// TODO Auto-generated method stub
		return 0;
	}


	




}
