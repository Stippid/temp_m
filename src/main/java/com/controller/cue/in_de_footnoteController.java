package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.IncrementDecrementDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class in_de_footnoteController {
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	     
	
	@Autowired
	private IncrementDecrementDAO incdecDAO;
	
	@Autowired
	private Cue_wepe_conditionDAO vetting;
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	public String getwe_pe_no_new="";
	ValidationController validation = new ValidationController();
	@RequestMapping(value = "/admin/in_de_footnote", method = RequestMethod.GET)
	public ModelAndView in_de_footnote(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("in_de_footnote", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("in_de_footnoteTiles","in_de_footnoteCMD", new CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES() );
	   }	

	@RequestMapping(value = "/admin/in_de_footnote1", method = RequestMethod.POST)
	public ModelAndView in_de_footnote1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,
			@RequestParam(value = "unit1", required = false) String unit,
			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "amt_inc_dec1", required = false) String amt_inc_dec){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("scenario1", scenario);
			Mmap.put("location1", location);
			Mmap.put("formation1", formation);
			Mmap.put("unit1", unit);
			Mmap.put("location1_hid", location_code);
			Mmap.put("formation1_hid", formation_code);
			Mmap.put("unit1_hid", unit_code);
			
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("item_seq_no1", item_seq_no);
			Mmap.put("amt_inc_dec1", amt_inc_dec);
			
			 List<Map<String, Object>> list =incdecDAO.AttributeReportSearchfootnote1(we_pe_no,amt_inc_dec,item_seq_no,status,roleType,roleAccess);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
		
		return new ModelAndView("in_de_footnoteTiles");
	}
	
	@RequestMapping(value = "/AttributeReportSearchfootnote24",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> AttributeReportSearchfootnote24(String we_pe_no,String status) {
		 List<Map<String, Object>> list =incdecDAO.AttributeReportSearchfootnote24(we_pe_no,status);
		return list;
	}


	@RequestMapping(value = "/admin/in_de_footnoteAction", method = RequestMethod.POST)
	   public ModelAndView in_de_footnoteAction(@ModelAttribute("in_de_footnoteCMD") CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES rs,HttpServletRequest request,ModelMap model,HttpSession session) {

		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String amt;
	    String radio1 = request.getParameter("ct-radio1");	
		String amt_inc_dec = request.getParameter("amt_inc_dec");
		String we_pe_no = request.getParameter("we_pe_no");
		String item_seq_no = request.getParameter("item_seq_no");
		String condition = request.getParameter("condition");
		String base_au = request.getParameter("base_auth");
		String location_code = request.getParameter("location");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("scenario_unit");
		int roleid = (Integer)session.getAttribute("roleid");	
		
		
		String r = request.getParameter("ct-radio1");
		String we_pe = request.getParameter("we_pe");
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		String scenario = rs.getScenario();
		try
		{
			if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) || we_pe.equals("undefined"))
			{
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:in_de_footnote");
			}
			  if(validation.checkWETypeLength(we_pe)  == false){
	                model.put("msg",validation.wetypeMSG);
	                return new ModelAndView("redirect:in_de_footnote");
	            }
				 if(rs.getWe_pe_no() == "" || rs.getWe_pe_no() ==null ||  rs.getWe_pe_no().equals(null) ||  rs.getWe_pe_no().isEmpty())
					{
						model.put("msg", "Please Enter WE/PE No");
						return new ModelAndView("redirect:in_de_footnote");
					}
				  if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
		                model.put("msg",validation.wepenoMSG);
		                return new ModelAndView("redirect:in_de_footnote");
		            }
					if(rs.getScenario() == "" || rs.getScenario() ==null ||  rs.getScenario().equals(null) || rs.getScenario().isEmpty())
					{
						model.put("msg", "Please Select Scenario");
						return new ModelAndView("redirect:in_de_footnote");
					}
					if(validation.checkScenarioLength(rs.getScenario())  == false){
				 		model.put("msg",validation.senarioMSG);
						return new ModelAndView("redirect:in_de_footnote");
					}
					String scenario2 = rs.getScenario();
					if(scenario2.equals("Location"))
					{
						if(rs.getLocation().equals("") ||rs.getLocation() == "" || rs.getLocation() ==null ||  rs.getLocation().equals(null) || rs.getLocation().isEmpty())
						{
							model.put("msg", "Please Enter Location");
							return new ModelAndView("redirect:in_de_footnote");
						
						}
						if(validation.checkLocationLength(rs.getLocation())  == false){
							model.put("msg",validation.locMSG);
							return new ModelAndView("redirect:in_de_footnote");
						}
					}
					if(scenario2.equals("Formation"))
					{
						if(rs.getFormation().equals("") ||rs.getFormation() == "" || rs.getFormation() ==null ||  rs.getFormation().equals(null) || rs.getFormation().isEmpty())
						{
							model.put("msg", "Please Enter Formation");
							return new ModelAndView("redirect:in_de_footnote");
						
						}
						if(validation.checkFormationLength(rs.getFormation())  == false){
							model.put("msg",validation.formMSG);
							return new ModelAndView("redirect:in_de_footnote");
						}
					}
					if(scenario2.equals("Unit"))
					{
						if(rs.getScenario_unit().equals("") ||rs.getScenario_unit() == "" || rs.getScenario_unit() ==null ||  rs.getScenario_unit().equals(null) || rs.getScenario_unit().isEmpty())
						{
							model.put("msg", "Please Enter Unit");
							return new ModelAndView("redirect:in_de_footnote");
						}
						if(validation.sus_noLength(rs.getScenario_unit())  == false){
							model.put("msg",validation.unitMSG);
							return new ModelAndView("redirect:in_de_footnote");
						}
					}
					if(rs.getItem_seq_no().equals("") || rs.getItem_seq_no() == "" || rs.getItem_seq_no() ==null || rs.getItem_seq_no().equals(null) || rs.getItem_seq_no().isEmpty())
					{
						model.put("msg", "Please Enter Item Code");
						return new ModelAndView("redirect:in_de_footnote");
					}
					if(validation.sus_noLength(rs.getItem_seq_no())  == false){
						model.put("msg",validation.itemcodeMSG);
						return new ModelAndView("redirect:in_de_footnote");
					}
					if(r == ""  || r==null || r=="null" || r.equals(null)|| r.equals("undefined"))
					{
						model.put("msg", "Please Select Increment/Decrement");
						return new ModelAndView("redirect:in_de_footnote");
					}
				  if(rs.getAmt_inc_dec() == 0.0 )
					{
						model.put("msg", "Please Enter Amount of Increment/Decrement");
						return new ModelAndView("redirect:in_de_footnote");
					}
				  int lenval=0;
				  if(request.getParameter("ct-radio1").equals("Increase"))
					  lenval = 8;
				  else if(request.getParameter("ct-radio1").equals("Decrease"))
					  lenval =9;
				  
				  String amt_inc_decvalid =  Double.toString(rs.getAmt_inc_dec()); 
				  if(validation.checkAmt_inc_decLength(amt_inc_decvalid,lenval)  == false){
						model.put("msg",validation.amt_inc_decMSG);
						return new ModelAndView("redirect:in_de_footnote");
					}
				  if(rs.getCondition().equals("") || rs.getCondition() == "" || rs.getCondition() ==null || rs.getCondition().equals(null) ||rs.getCondition().isEmpty())
					{
						model.put("msg", "Please Enter Condition");
						return new ModelAndView("redirect:in_de_footnote");
					}
				  if(validation.checkConditionLength(rs.getCondition())  == false){
						model.put("msg",validation.conditionMSG);
						return new ModelAndView("redirect:in_de_footnote");
					} 
					if(validation.checkRemarksLength(rs.getRemarks())  == false){
						model.put("msg",validation.remarksMSG);
						return new ModelAndView("redirect:in_de_footnote");
					}
			else {		
		rs.setRoleid(roleid);	
		
		CUE_TB_MISO_WEPE_WEAPON_DET cue_weapon_det = new CUE_TB_MISO_WEPE_WEAPON_DET();

		if(base_au.equals("") || base_au.equals("undefined") || base_au.equals("0"))
		{
			
			cue_weapon_det.setWe_pe_no(we_pe_no);
			cue_weapon_det.setItem_seq_no(item_seq_no);
			double	auth_amt = Double.valueOf(0);
			
			cue_weapon_det.setAuth_weapon(auth_amt);
			cue_weapon_det.setCreated_by(username);
			cue_weapon_det.setCreated_on(date);
			cue_weapon_det.setRoleid(roleid);
			cue_weapon_det.setStatus("1");
			Session sessionUD = HibernateUtil.getSessionFactory().openSession();
			sessionUD.beginTransaction();
			
			int uid = (Integer) sessionUD.save(cue_weapon_det);
			sessionUD.getTransaction().commit();
			sessionUD.close();
			model.put("msg", "Data saved Successfully");
		}
		//vishakha_v1
	
		/*if(base_au.equals("") || base_au.equals("undefined") || base_au.equals("0"))
		{
			Boolean e = isdetailWepe_footnote_exits(we_pe_no,item_seq_no,"");
			
			if(e.equals(true)) {
				cue_weapon_det.setWe_pe_no(we_pe_no);
				cue_weapon_det.setItem_seq_no(item_seq_no);
				double	auth_amt = Double.valueOf(0);
				
				cue_weapon_det.setAuth_weapon(auth_amt);
				cue_weapon_det.setCreated_by(username);
				cue_weapon_det.setCreated_on(date);
				cue_weapon_det.setRoleid(roleid);
				cue_weapon_det.setStatus("1");
				Session sessionUD = HibernateUtil.getSessionFactory().openSession();
				sessionUD.beginTransaction();
				
				int uid = (Integer) sessionUD.save(cue_weapon_det);
				sessionUD.getTransaction().commit();
				sessionUD.close();
				model.put("msg", "Data saved Successfully");
			}
			else {
				model.put("msg", "Data Already Exists!");
			}
		}*/
		
		if(radio1.equals("Decrease"))
		{
			
			amt ="-"+amt_inc_dec;
			//double	sum =  Double.valueOf(Long.parseLong(amt));
			//double	sum =  Double.valueOf(Double.parseDouble(amt));
			
			double sum= Double.parseDouble(amt);
			rs.setAmt_inc_dec(sum);
		}
		Boolean e = isdetailWepe_footnote_exits(we_pe_no,item_seq_no,condition);
		
		if(e.equals(false)) {
			
			
			if(scenario.equals("Location")) {
				rs.setLocation(request.getParameter("location"));
				rs.setFormation(null);
				rs.setScenario_unit(null);
				rs.setScenario(scenario);
			}
			else if(scenario.equals("Formation")) {
				rs.setFormation(request.getParameter("formation"));
				rs.setLocation(null);
				rs.setScenario_unit(null);
				rs.setScenario(scenario);
			}
			else if(scenario.equals("Unit")) {
				rs.setFormation(null);
				rs.setLocation(null);
				rs.setScenario_unit(request.getParameter("scenario_unit"));
				rs.setScenario(scenario);
			}
			
			else if(scenario.equals("Others")) {
				rs.setFormation(null);
				rs.setLocation(null);
				rs.setScenario_unit(null);
				rs.setScenario("Others");
			}
			else{
				rs.setScenario(null);
				rs.setFormation(null);
				rs.setLocation(null);
				rs.setScenario_unit(null);
			}
			
		rs.setCreated_by(username);
		rs.setCreated_on(date);
		rs.setStatus("0");
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		sessionhql.beginTransaction();
		int did = (Integer) sessionhql.save(rs);
		sessionhql.getTransaction().commit();
		sessionhql.close();
		model.put("msg", "Data saved Successfully");
		}
		else {
		model.put("msg", "Data Already Exists!");
		}
		}
		}	
		catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}
		 List<Map<String, Object>> list =incdecDAO.AttributeReportSearchfootnote1(we_pe_no,"","","","","");
		 model.put("we_pe1", we_pe);
		 model.put("we_pe_no1", we_pe_no);
		 model.put("scenario1", scenario);
		 model.put("location1", request.getParameter("location_name"));
		 model.put("formation1", request.getParameter("formation_name"));
		 model.put("unit1", request.getParameter("scenario_unit_name"));
		 model.put("we_pe_no1", we_pe_no);
		 model.put("location1_hid", location_code);
		 model.put("formation1_hid", formation_code);
		 model.put("unit1_hid", unit_code);
		 model.put("list", list);
		 model.put("list.size()", list.size());
		return new ModelAndView("in_de_footnoteTiles");
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_footnote_exits(String we_pe_no,String item_seq_no,String condition) {
		String hql="FROM CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES m where m.we_pe_no=:we_pe_no and m.item_seq_no=:item_seq_no and m.condition=:condition";
		List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("item_seq_no", item_seq_no);
			query.setParameter("condition", condition);
			users = (List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES>) query.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		} 
		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	 @RequestMapping(value = "/getitemtype_code", method = RequestMethod.POST)
		public @ResponseBody List<CUE_TB_MISO_MMS_ITEM_MSTR> getitemtype_code(String val,HttpSession sessionUserId) 
		{
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code =:item_code") ;
			q.setString("item_code",val);
			
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	
	
	@RequestMapping(value = "/admin/search_in_de", method = RequestMethod.GET)
	public ModelAndView Search_in_de(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_in_de", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_increment_decrementTiles");
	}
	
//////////////////report///////////////////////
	
	@RequestMapping(value = "/admin/ApprovedIncrementUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedIncrementUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no2", required = false) String item_seq_no,
			@RequestParam(value = "item_type2", required = false) String item_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "we_pe2", required = false) String we_pe){
		String username = session.getAttribute("username").toString();
		String mst = incdecDAO.setApprovedIncrementDecrement(appid, username);
		if(mst.equals("Approved Successfully")) {
			vetting.updateVettingDtl( we_pe_no, "3");
		}
			Mmap.put("msg", mst);	
			
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
				
		 List<Map<String, Object>> list =incdecDAO.AttributeReportSearchfootnote( we_pe_no, item_seq_no, status, roleType, roleAccess);
			Mmap.put("we_pe01", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("item_seq_no1", item_seq_no);
			Mmap.put("item_type1", item_type);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		 return new ModelAndView("search_increment_decrementTiles");
		}
	
		@RequestMapping(value = "/deleteIncrementUrlAdd",method = RequestMethod.POST)
		public @ResponseBody List<String> deleteIncrementUrlAdd(int deleteid) {			
			List<String> list2 = new ArrayList<String>();
			list2.add(incdecDAO.setDeleteIncrement(deleteid));
			return list2;
		}
		//////////////////////////////Edit////////////////////////////////
		@RequestMapping(value = "/admin/updateIncrementUrl")
		public ModelAndView updateIncrementUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication
			  ,HttpSession sessionEdit){
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
			model.put("in_deEditCMD", incdecDAO.getCUE_TB_MISO_WEPE_WEAPON_FOOTNOTESByid(updateid));
			model.put("msg", msg);
		return new ModelAndView("create_increment_decrementEditTiles");
	   }

		@RequestMapping(value = "/admin/in_deEditAction",method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView in_deEditAction(@ModelAttribute("in_deEditCMD") CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session11
				,HttpSession sessionEdit){
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
	
			String username = session11.getAttribute("username").toString();  
			String radio1 = request.getParameter("ct-radio1");
			  String amt = request.getParameter("amt_inc_dec");
			  Double amt_inc_dec = 0.0;
			  
			  String location = request.getParameter("location");
				String formation = request.getParameter("formation");
				String scenario1 = request.getParameter("scenario");
				String scenario_unit = request.getParameter("scenario_unit");
				String remarks = request.getParameter("remarks");
				String item_seq_no = request.getParameter("item_seq_no");
				String condition = request.getParameter("condition");
				if(updateid.getScenario() == "" || updateid.getScenario()==null ||  updateid.getScenario().equals(null) ||  updateid.getScenario().isEmpty())
				{
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Select Scenario");
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				if(validation.checkScenarioLength(updateid.getScenario())  == false){
			 		model.put("msg",validation.senarioMSG);
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				String scenario2 = updateid.getScenario();
				if(scenario2.equals("Location"))
				{
					if(updateid.getLocation().equals("") || updateid.getLocation() ==null ||  updateid.getLocation().equals(null) ||  updateid.getLocation().isEmpty())
					{
						model.put("updateid",updateid.getId());
						model.put("msg", "Please Enter Location");
						return new ModelAndView("redirect:updateIncrementUrl");
					
					}
					if(validation.checkLocationLength(updateid.getLocation())  == false){
						model.put("msg",validation.locMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					}
				}
				if(scenario2.equals("Formation"))
				{
					if(updateid.getFormation().equals("") || updateid.getFormation() ==null ||  updateid.getFormation().equals(null) || updateid.getFormation().isEmpty())
					{
						model.put("updateid",updateid.getId());
						model.put("msg", "Please Enter Formation");
						return new ModelAndView("redirect:updateIncrementUrl");
					
					}
					if(validation.checkFormationLength(updateid.getFormation())  == false){
						model.put("msg",validation.formMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					}
				}
				if(scenario2.equals("Unit"))
				{
					if(updateid.getScenario_unit().equals(""))
					{
						model.put("updateid",updateid.getId());
						model.put("msg", "Please Enter Unit");
						return new ModelAndView("redirect:updateIncrementUrl");
					}
					if(validation.sus_noLength(updateid.getScenario_unit())  == false){
						model.put("msg",validation.unitMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					}
				}
				
				if(item_seq_no.equals("") || item_seq_no==null || item_seq_no=="null" || item_seq_no.equals(null))
				{
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Enter Nomenclature / Item Code");
					return new ModelAndView("redirect:updateIncrementUrl");
				
				}
				if(validation.checkFormationLength(updateid.getItem_seq_no())  == false){
					model.put("msg",validation.nomenMSG);
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				
				 if(radio1.equals("") || radio1==null || radio1=="null" || radio1.equals(null) || radio1.equals("undefined") )
					{
					 	model.put("updateid",updateid.getId());
						model.put("msg", "Please Select Increment or Decrement");
						return new ModelAndView("redirect:updateIncrementUrl");
					}
				 if(validation.checkFormationLength(radio1)  == false){
						model.put("msg",validation.incdecMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					}
				if(amt == ""  || amt==null || amt=="null" || amt.equals("0") || amt.equals("undefined") )
				{
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Enter Amount of Increment or Decrement");
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				else {
					String base = request.getParameter("base_autho_hidden");
			        float f_base = Float.parseFloat(base);
			        float f_amt_inc_dec = Float.parseFloat(amt);
					if(radio1 == "Decrease" || radio1.equals("Decrease")) {
						if(f_amt_inc_dec > f_base) {
							model.put("updateid",updateid.getId());
							model.put("msg", "Please Check Amount of Increment or Decrement");
							return new ModelAndView("redirect:updateIncrementUrl");
						}
					}
				}
			 int lenval=0;
			  if(request.getParameter("ct-radio1").equals("Increase"))
				  lenval = 8;
			  else if(request.getParameter("ct-radio1").equals("Decrease"))
				  lenval =9;
			  
			  String amt_inc_decvalid =  Double.toString(updateid.getAmt_inc_dec());
			  if(validation.checkAmt_inc_decLength(amt_inc_decvalid,lenval)  == false){
					model.put("msg",validation.amt_inc_decMSG);
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				if(updateid.getCondition() == "" ||updateid.getCondition() ==null ||  updateid.getCondition().equals(null) ||  updateid.getCondition().isEmpty())
				{
					model.put("updateid",updateid.getId());
					model.put("msg", "Please Enter Condition");
					return new ModelAndView("redirect:updateIncrementUrl");
				}
				if(validation.checkConditionLength(updateid.getCondition())  == false){
						model.put("msg",validation.conditionMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					} 
					if(validation.checkRemarksLength(updateid.getRemarks())  == false){
						model.put("msg",validation.remarksMSG);
						return new ModelAndView("redirect:updateIncrementUrl");
					}
			  if(radio1.equals("Decrease"))
				{
				  amt = "-" +amt;
				  amt_inc_dec =  Double.valueOf(Double.parseDouble(amt));
				}
			  
			  else {
				   
				  amt_inc_dec =  Double.parseDouble(request.getParameter("amt_inc_dec"));
			  }
			  
				Session sessioncount = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = sessioncount.beginTransaction();
				Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no and condition=:condition and id !=:id");
				q1.setParameter("we_pe_no", updateid.getWe_pe_no());
				q1.setParameter("item_seq_no", updateid.getItem_seq_no());
				q1.setParameter("condition", updateid.getCondition());
				q1.setParameter("id", updateid.getId());
				@SuppressWarnings("unchecked")
				Long count2 = (Long)q1.uniqueResult();
				model.put("count", count2);
				transaction.commit();
				sessioncount.close();					
				if(count2 == 0) {	
					String scenario = request.getParameter("scenario");
					if(scenario.equals("Location")) {
						scenario1=scenario;
						location =request.getParameter("location");
						formation="";
						scenario_unit="";
					}
					else if(scenario.equals("Formation")) {
						scenario1=scenario;
						location ="";
						formation=request.getParameter("formation");
						scenario_unit="";
					}
					else if(scenario.equals("Unit")) {
						scenario1=scenario;
						location ="";
						formation="";
						scenario_unit=request.getParameter("scenario_unit");
					}
					else if(scenario.equals("Others")){
						scenario1=scenario;
						location ="";
						formation="";
						scenario_unit="";
					}
					else {
						scenario1="";
						location ="";
						formation="";
						scenario_unit="";
					}
					Session session = HibernateUtilNA.getSessionFactory().openSession();
					String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					Transaction tx = session.beginTransaction();
					String hql = "update CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES  set item_seq_no=:item_seq_no,scenario_unit=:scenario_unit,scenario=:scenario,location=:location,formation=:formation,amt_inc_dec=:amt_inc_dec,condition=:condition,modified_by=:modified_by,modified_on=:modified_on,remarks=:remarks, status ='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
					 Query query = session.createQuery(hql).setString("item_seq_no", item_seq_no).setString("scenario", scenario1).setString("scenario_unit", scenario_unit).setString("location", location).setString("formation", formation).setDouble("amt_inc_dec", amt_inc_dec).setString("condition", condition).setString("modified_by", username).setString("modified_on", modifydate).setString("remarks", remarks)
							 .setString("vettedby_dte1", "")
					    		.setString("vettedby_dte2", "").setInteger("id",updateid.getId());
					    int rowCount = query.executeUpdate();
					tx.commit();
					session.close();
					if(rowCount > 0) {
						model.put("msg", "Updated Successfully");
					}else {
						model.put("msg", "Updated not Successfully");
					}
				}
					
				else {
				   model.put("msg", "Data already exist !");
				   model.put("weap_aut_modiCMD",incdecDAO.getCUE_TB_MISO_WEPE_WEAPON_FOOTNOTESByid(updateid.getId()));
				   return new ModelAndView("create_increment_decrementEditTiles");
				}
							  
				return new ModelAndView("redirect:in_de_footnote");
	      }

	@RequestMapping(value = "/admin/search_in_de1", method = RequestMethod.POST)
	public ModelAndView search_in_de1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "item_type1", required = false) String item_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe01", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
			Mmap.put("we_pe01", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("item_seq_no1", item_seq_no);
			Mmap.put("item_type1", item_type);
			List<Map<String, Object>> list =incdecDAO.AttributeReportSearchfootnote( we_pe_no,item_seq_no,status,roleType,roleAccess);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("search_increment_decrementTiles");
	}

	@RequestMapping(value = "/updaterejectactionfootnote", method = RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectactionfootnote(HttpSession session,String remarks,String letter_no,int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers2("cue_tb_miso_wepe_weapon_footnotes",remarks,id,letter_no,username);
		return list2;
	}
}