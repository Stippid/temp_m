package com.controller.orbat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.orbat.LOVDAO;
import com.dao.orbat.LOVDAOImpl;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Miso_Orbat_Unt_Dtl;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class LovController {

	LOVDAO lovDAO = new LOVDAOImpl();

//---- START LOCATION LOV
	@RequestMapping(value = "/admin/locationLOV", method = RequestMethod.GET)
	public ModelAndView locationLOV(ModelMap Mmap,HttpSession sessionUserId,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("locationLOVTiles");
	}
	
	@RequestMapping(value = "/admin/locationLOVList", method = RequestMethod.POST)
	public ModelAndView locationLOVList(ModelMap Mmap,HttpSession sessionUserId,@RequestParam(value = "loc1", required = false) String loc,
		@RequestParam(value = "nrs1", required = false) String nrs) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		
		Session sessionLov= HibernateUtil.getSessionFactory().openSession();
		sessionLov.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionLov.beginTransaction();
		String qry = "";
		if(loc != "") {
			qry += " and upper(a.code_value) like :loc ";
		}
		if(nrs != "") {
			qry += " and upper(b.code_value) like :nrs ";
		}
		Query q = null;
		q = sessionLov.createQuery("select distinct a.code_value as location,b.code_value as nrs,a.code as loc_code,a.mod_desc as trn_type,c.label as type_of_loc,a.nrs_code from Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value  c where a.code_type='Location' and b.code_type = 'Location' and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' "+ qry +" order by a.code_value");
		if(loc != "") {
			q.setParameter("loc", loc.toUpperCase()+"%");
			Mmap.put("loc1",loc);
		}
		if(nrs != "") {
			q.setParameter("nrs", nrs.toUpperCase()+"%");
			Mmap.put("nrs1",nrs);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionLov.close();
		
		Mmap.put("list", list);
		return new ModelAndView("locationLOVTiles");
	}	
//---- END LOCATION LOV

// START Sus No & Unit Name Lov
	String target_unit_name_lov1= "";
	String target_sus_no_lov1="";
	@RequestMapping(value = "/LovUnit_SusNo", method = RequestMethod.GET)
	public ModelAndView search_target_unit_name(ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg ,HttpSession sessionUserId,HttpServletRequest request) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Mmap.put("target_unit_name_lov",target_unit_name_lov1);
		Mmap.put("arm_code_lov",target_sus_no_lov1);
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("LovUnit_SusNoTile");
	}
	@RequestMapping(value="searchUnitListUrl",method = RequestMethod.POST)
	public ModelAndView searchUnitListUrl(ModelMap model,HttpServletRequest request,
		@RequestParam(value = "target_unit_name12", required = false) String target_unit_name_lov,
		@RequestParam(value = "target_sus_no12", required = false) String target_sus_no_lov ,HttpSession sessionUserId){
		
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		
		target_unit_name_lov = target_unit_name_lov.replace("&#40;","(");
		target_unit_name_lov = target_unit_name_lov.replace("&#41;",")");
		
		if(!target_unit_name_lov.equals("0") && !target_unit_name_lov.equals(""))
		{
			target_unit_name_lov1 = target_unit_name_lov;
		}
		if(!target_sus_no_lov.equals("0") && !target_sus_no_lov.equals("")) 
		{
	        target_sus_no_lov1 = target_sus_no_lov;
	    }
		return new ModelAndView("redirect:LovUnit_SusNo");
	}
  	
	@RequestMapping(value = "/admin/LovUnit_SusNoList")
    public @ResponseBody DatatablesResponse<Miso_Orbat_Unt_Dtl> LovUnit_SusNoList(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionUserId, HttpServletRequest request) {
    	int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
    	target_unit_name_lov1= "";
    	target_sus_no_lov1="";
    	DataSet<Miso_Orbat_Unt_Dtl> dataSet = lovDAO.findLovOfUnitNameDatatablesCriteriasWithSusNo(criterias, target_unit_name_lov1,target_sus_no_lov1);
    	return DatatablesResponse.build(dataSet, criterias);
    }
// END SUS NO & UNIT NAME
    
    
 // START User List Lov
 /*	@RequestMapping(value = "/LovUserDetails", method = RequestMethod.GET)
 	public ModelAndView LovUserListNo(ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg ,HttpSession sessionUserId) {
 		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
 		Mmap.put("target_unit_name_lov",target_unit_name_lov1);
 		Mmap.put("arm_code_lov",target_sus_no_lov1);
 		Mmap.put("msg", msg);
 		return new ModelAndView("LovUserListTile");
 	}
 	@RequestMapping(value="searchUserListUrl",method = RequestMethod.POST)
 	public ModelAndView searchUserListUrl(ModelMap model,HttpServletRequest request,
 		@RequestParam(value = "target_unit_name12", required = false) String target_unit_name_lov,
 		@RequestParam(value = "target_sus_no12", required = false) String target_sus_no_lov ,HttpSession sessionUserId){
 		
 		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
 		
 		target_unit_name_lov = target_unit_name_lov.replace("&#40;","(");
 		target_unit_name_lov = target_unit_name_lov.replace("&#41;",")");
 		
 		if(!target_unit_name_lov.equals("0") && !target_unit_name_lov.equals(""))
 		{
 			target_unit_name_lov1 = target_unit_name_lov;
 		}
 		if(!target_sus_no_lov.equals("0") && !target_sus_no_lov.equals("")) 
 		{
 	        target_sus_no_lov1 = target_sus_no_lov;
 	    }
 		return new ModelAndView("redirect:LovUnit_SusNo");
 	}
   	@RequestMapping(value = "/admin/LovUserList")
     public @ResponseBody DatatablesResponse<Miso_Orbat_Unt_Dtl> LovUserList(@DatatablesParams DatatablesCriterias criterias,HttpSession sessionUserId, HttpServletRequest request) {
     	int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
     	target_unit_name_lov1= "";
     	target_sus_no_lov1="";
     	DataSet<Miso_Orbat_Unt_Dtl> dataSet = lovDAO.findLovOfUnitNameDatatablesCriteriasWithSusNo(criterias, target_unit_name_lov1,target_sus_no_lov1);
     	return DatatablesResponse.build(dataSet, criterias);
     }*/
 // END SUS NO & UNIT NAME
}