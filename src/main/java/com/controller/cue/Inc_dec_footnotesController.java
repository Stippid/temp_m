package com.controller.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import com.dao.cue.IncDecFootnotesDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.models.Miso_Orbat_Unt_Dtl;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Inc_dec_footnotesController {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	

	@Autowired
	private IncDecFootnotesDAO incdec;
	
	@Autowired
	private RoleBaseMenuDAO roledao ;

	@Autowired
	private Cue_wepe_conditionDAO vetting;	
	
	cueContoller M = new cueContoller();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/Inc_dec_footnotes", method = RequestMethod.GET)
	public ModelAndView Inc_dec_footnotes(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Inc_dec_footnotes", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("msg", msg);
		return new ModelAndView("Inc_dec_footnotesTiles");
	}

	@RequestMapping(value = "/admin/Inc_dec_footnotes1", method = RequestMethod.POST)
	public ModelAndView Inc_dec_footnotes1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "category_of_personnel1", required = false) String category_of_personnel,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "appt_trade1", required = false) String appt_trade,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,
			@RequestParam(value = "unit1", required = false) String unit,
			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "we_pe1", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("scenario1", scenario);
			Mmap.put("location1", location);
			Mmap.put("formation1", formation);
			Mmap.put("unit1", unit);
			Mmap.put("location1_hid", location_code);
			Mmap.put("formation1_hid", formation_code);
			Mmap.put("unit1_hid", unit_code);
			Mmap.put("status1", status);
			Mmap.put("category_of_personnel1", category_of_personnel);
			Mmap.put("rank_cat1", rank_cat);
			Mmap.put("appt_trade1", appt_trade);
			Mmap.put("rank1", rank);
			Mmap.put("we_pe_no2", we_pe_no);
			
			List<Map<String, Object>>  list = incdec.AttributeReportSearchFootnotes1(we_pe_no,category_of_personnel,rank_cat,appt_trade,rank,status,roleType,roleAccess );
			Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
			Mmap.put("list", list);
			Mmap.put("listsize", list.size());
			Mmap.put("roleType", roleType);
		 return new ModelAndView("Inc_dec_footnotesTiles");
	}
	@RequestMapping(value = "/Inc_dec_footnotesAction", method = RequestMethod.POST)
	public ModelAndView Inc_dec_footnotesAction(@ModelAttribute("Inc_dec_footnotesActionCMD") CUE_TB_MISO_WEPE_PERS_FOOTNOTES rs,HttpServletRequest request,ModelMap model,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String amt = request.getParameter("amt_inc_dec");	
		String radio_status = request.getParameter("radio_status");
		String we_pe = request.getParameter("we_pe");
		String cat_per = request.getParameter("category_of_personnel");
		String category_of_personnel = request.getParameter("category_of_personnel");
		String rank_cat = request.getParameter("rank_cat");
		String rank = request.getParameter("rank");
		String arm_code = request.getParameter("arm_code");
		String appt_trade_code = request.getParameter("appt_trade");
		String appt_trade = request.getParameter("appt_trade_name");
		String we_pe_no = request.getParameter("we_pe_no");
		String condition = request.getParameter("condition");
		String location = request.getParameter("location_name");
		String formation = request.getParameter("formation_name");
		String unit = request.getParameter("scenario_unit_name");
		String scenario1 = rs.getScenario();
		String location_code = request.getParameter("location");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("scenario_unit");
		int roleid = (Integer)session.getAttribute("roleid");	
		String roleType = session.getAttribute("roleType").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		
		if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:Inc_dec_footnotes");
		}
		 if(rs.getWe_pe_no() == "")
			{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
		 if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
				model.put("msg",validation.wepenoMSG);
				return new ModelAndView("redirect:pers_entitUrl");
			}
			if(rs.getScenario() == "")
			{
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
			
			if(scenario1 != "")
			{
				if(validation.checkScenarioLength(rs.getLocation())  == false){
					model.put("msg",validation.senarioMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
				if(scenario1.equals("Location"))
				{
					if(rs.getLocation().equals(",") || rs.getLocation().equals(""))
					{
						model.put("msg", "Please Enter Location");
						return new ModelAndView("redirect:Inc_dec_footnotes");
				
					}
					if(validation.checkLocationLength(rs.getLocation())  == false){
						model.put("msg",validation.locMSG);
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
					
				}
				if(scenario1.equals("Formation"))
				{
					if(rs.getFormation().equals(""))
					{
						model.put("msg", "Please Enter Formation");
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
					if(validation.checkFormationLength(rs.getLocation())  == false){
						model.put("msg",validation.formMSG);
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
				}
				if(scenario1.equals("Unit"))
				{
					if(rs.getScenario_unit().equals(""))
					{
						model.put("msg", "Please Enter Unit");
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
					
					if(validation.sus_noLength(rs.getScenario_unit())  == false){
						model.put("msg",validation.unitMSG);
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
				}
			}
			if(rs.getCategory_of_personnel().equals(""))
			{
				model.put("msg", "Please Select Category of Personnel");
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
			if(validation.checkCategory_of_personnelLength(rs.getCategory_of_personnel())  == false){
				model.put("msg",validation.catpersMSG);
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
			if(rs.getCategory_of_personnel() != "")
			{
				if(rs.getCategory_of_personnel().equals("1")) { 
					if(rs.getArm_code().equals(""))
					{
					model.put("msg", "Please Select Parent Arm");
					return new ModelAndView("redirect:Inc_dec_footnotes");
					
					}
					String arm_codevalid = String.format("%04d", Integer.parseInt(rs.getArm_code()));
					if(validation.checkArmCodeLength(arm_codevalid)  == false){
				 		model.put("msg",validation.arm_codeMSG);
						return new ModelAndView("redirect:Inc_dec_footnotes");
					}
				}
			}
			 if( rs.getRank_cat().equals(""))
				{
					model.put("msg", "Please Select Category");
					return new ModelAndView("redirect:Inc_dec_footnotes");
					
				}
				if(validation.checkMonthLength(rs.getRank_cat())  == false){
					model.put("msg",validation.rankcatMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  if(rs.getAppt_trade() == "" )
				{
					model.put("msg", "Please Enter Common Appt/Trade");
					return new ModelAndView("redirect:Inc_dec_footnotes");
					
				}
		/*	  if(validation.checkFormationLength(rs.getAppt_trade())  == false){
					model.put("msg",validation.apptradeMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  */
			  
			//sana280623bisag
			  if (validation.check20Length(rs.getAppt_trade()) == false) {
					model.put("msg", validation.apptradeMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  if(rs.getRank() =="" || rs.getRank() == null )
				 {
						model.put("msg", "Please Select Rank");
						return new ModelAndView("redirect:Inc_dec_footnotes");
						
					}
			  if(validation.checkFormationLength(rs.getRank())  == false){
					model.put("msg",validation.rankMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  
			  if(radio_status == ""  || radio_status==null || radio_status=="null" || radio_status.equals(null) )
				{
					model.put("msg", "Please Select Increment/Decrement");
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  if(rs.getAmt_inc_dec() == 0.0 )
				{
					model.put("msg", "Please Enter Amount of Increment/Decrement");
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			 
			  int lenval=0;
			  if(request.getParameter("radio_status").equals("Increment"))
				  lenval = 8;
			  else if(request.getParameter("radio_status").equals("Decrement"))
				  lenval = 9;
			  
			  String amt_inc_dec =  Double.toString(rs.getAmt_inc_dec());
				
			  if(validation.checkAmt_inc_decLength(amt_inc_dec,lenval)  == false){
					model.put("msg",validation.amt_inc_decMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  if(rs.getCondition() == "" )
				{
					model.put("msg", "Please Enter Condition");
					return new ModelAndView("redirect:Inc_dec_footnotes");
				}
			  if(validation.checkConditionLength(rs.getCondition())  == false)
				{
					model.put("msg",validation.conditionMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
					
				}
			  if(validation.checkRemarksLength(rs.getRemarks())  == false)
				{
					model.put("msg",validation.remarksMSG);
					return new ModelAndView("redirect:Inc_dec_footnotes");
					
				}
			  else {
		rs.setRoleid(roleid);
		String base_au = request.getParameter("base_auth_hidden");
			
		CUE_TB_MISO_WEPE_PERS_DET cue_per_det = new CUE_TB_MISO_WEPE_PERS_DET();
		if(base_au=="" || base_au == null || base_au == "null"  || base_au.equals("undefined"))
		{
			cue_per_det.setCategory_of_persn(cat_per);
			cue_per_det.setRank_cat(rank_cat);
			cue_per_det.setRank(rank);
			cue_per_det.setArm_code(arm_code);
			cue_per_det.setApp_trd_code(appt_trade_code);			
			cue_per_det.setAppt_trade(appt_trade);
			cue_per_det.setWe_pe_no(we_pe_no);
			cue_per_det.setStatus("1");
			cue_per_det.setAuth_amt(0.0);
			double auth_amt = Double.valueOf(0);
			cue_per_det.setCreated_by(username);
			cue_per_det.setCreated_on(creadtedate);
			
			Session sessionUD = HibernateUtil.getSessionFactory().openSession();
			sessionUD.beginTransaction();
			int uid = (Integer) sessionUD.save(cue_per_det);
			sessionUD.getTransaction().commit();
			sessionUD.close();
		}
		String r = request.getParameter("radio_status");
		if(r.equals("Decrement"))
		{
			amt ="-" + amt;
			double sum= Double.parseDouble(amt);
			rs.setAmt_inc_dec(sum);
		}
	
		Boolean e = isdetailWepe_incdec_footnotesexits(we_pe_no,rank,appt_trade_code,arm_code,rank_cat,cat_per,condition,amt);
		if(e.equals(false)) {
			String scenario = rs.getScenario();
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
		rs.setCreated_on(creadtedate);
		
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
		
		List<Map<String, Object>>  list = incdec.AttributeReportSearchFootnotes1(we_pe_no,"","","","","","","");
			model.put("list", list);
			model.put("listsize", list.size());
			model.put("roleType", roleType);
			model.put("we_pe1", we_pe);
			model.put("category_of_personnel1", category_of_personnel);
			model.put("rank_cat1", rank_cat);
			model.put("appt_trade1", appt_trade);
			model.put("rank1", rank);
			model.put("we_pe_no2", we_pe_no);
			model.put("scenario1", scenario1);
			model.put("location1", location);
			model.put("formation1", formation);
			model.put("unit1", unit);
			model.put("location1_hid", location_code);
			model.put("formation1_hid", formation_code);
			model.put("unit1_hid", unit_code);
			model.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			model.put("getPersonCatListFinal", M.getPersonCatListFinal());
		return new ModelAndView("Inc_dec_footnotesTiles");
	}
			 
		
	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_incdec_footnotesexits(String we_pe_no,String rank,String appt_trade_code,String arm_code, String rank_cat,String cat_per,String condition,String amt) {
		String hql=" FROM CUE_TB_MISO_WEPE_PERS_FOOTNOTES m where m.appt_trade=:appt_trade_code and m.we_pe_no=:we_pe_no and m.rank = :rank and arm_code = :arm_code and m.rank_cat = :rank_cat and m.category_of_personnel =:cat_per and m.amt_inc_dec=:amt_inc_dec";
		List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> users = null;
		Double amt_inc_dec = Double.parseDouble(amt);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("appt_trade_code", appt_trade_code);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("rank", rank);
			query.setParameter("arm_code", arm_code);
			query.setParameter("cat_per", cat_per);
			query.setParameter("rank_cat", rank_cat);
			//query.setParameter("condition", condition);
			query.setParameter("amt_inc_dec",amt_inc_dec);
			users = (List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES>) query.list();			
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
		
		/////////////////    search///////////////////
		
		@RequestMapping(value = "/admin/getIncDecFromCategory2", method = RequestMethod.POST)
		public ModelAndView getIncDecFromCategory2(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
				@RequestParam(value = "category_of_personnel1", required = false) String category_of_personnel,
				@RequestParam(value = "rank_cat1", required = false) String rank_cat,
				@RequestParam(value = "appt_trade1", required = false) String appt_trade,
				@RequestParam(value = "rank1", required = false) String rank,
				@RequestParam(value = "status1", required = false) String status,
				@RequestParam(value = "we_pe1", required = false) String we_pe){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				Mmap.put("we_pe1", we_pe);
				Mmap.put("status1", status);
				 Mmap.put("we_pe_no2", we_pe_no);
				 Mmap.put("category_of_personnel1", category_of_personnel);
				 Mmap.put("rank_cat1", rank_cat);
				 Mmap.put("appt_trade1", appt_trade);
				 Mmap.put("rank1", rank);
				List<Map<String, Object>>  list = incdec.AttributeReportSearchFootnotes(we_pe,status,we_pe_no,category_of_personnel,rank_cat,appt_trade,rank,roleType,roleAccess);
				Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
				Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
				Mmap.put("list", list);
				Mmap.put("roleType", roleType);
				Mmap.put("list.size()", list.size());
				return new ModelAndView("Search_Inc_dec_footnotesTiles");
			}
		
		@RequestMapping(value = "/admin /Search_Inc_dec_footnotes", method = RequestMethod.GET)
		public ModelAndView Search_Inc_dec_footnotes(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Inc_dec_footnotes", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
			Mmap.put("msg", msg);
			return new ModelAndView("Search_Inc_dec_footnotesTiles");
		}
				
///////////////////////////     Report     //////////////////////////  
		@RequestMapping(value = "/updaterejectactionfoot", method = RequestMethod.POST)	 
		public @ResponseBody List<String> updaterejectactionfoot(String remarks,String letter_no,int id) {
			List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wepe_pers_footnotes",remarks,id,letter_no);
			return list2;
		}

		@RequestMapping(value = "/ApprovedIncDecFootnotesUrl", method = RequestMethod.POST)
		public ModelAndView ApprovedIncDecFootnotesUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
				@RequestParam(value = "we_pe_no3", required = false) String we_pe_no,
				@RequestParam(value = "category_of_personnel2", required = false) String category_of_personnel,
				@RequestParam(value = "rank_cat2", required = false) String rank_cat,
				@RequestParam(value = "appt_trade2", required = false) String appt_trade,
				@RequestParam(value = "rank2", required = false) String rank,
				@RequestParam(value = "status2", required = false) String status,
				@RequestParam(value = "we_pe2", required = false) String we_pe){
			String username = session.getAttribute("username").toString();
			String mst = incdec.setApprovedINC(appid,username);
			if(mst.equals("Approved Successfully")) {
				vetting.updateVettingDtl( we_pe_no, "1");
			}
				Mmap.put("msg", mst);	
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				 Mmap.put("we_pe1", we_pe);
				 Mmap.put("status1", status);
				 Mmap.put("we_pe_no2", we_pe_no);
				 Mmap.put("category_of_personnel1", category_of_personnel);
				 Mmap.put("rank_cat1", rank_cat);
				 Mmap.put("appt_trade1", appt_trade);
				 Mmap.put("rank1", rank);
				List<Map<String, Object>>  list = incdec.AttributeReportSearchFootnotes(we_pe,status,we_pe_no,category_of_personnel,rank_cat,appt_trade,rank,roleType,roleAccess);
				Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
				Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
				Mmap.put("list", list);
				Mmap.put("roleType", roleType);
				Mmap.put("list.size()", list.size());
				return new ModelAndView("Search_Inc_dec_footnotesTiles");
			}
			
		@RequestMapping(value = "/deleteIncDecFootnotesUrlAdd", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteIncDecFootnotesUrlAdd(int deleteid) {			
			List<String> list2 = new ArrayList<String>();
			list2.add(incdec.setDeleteINC(deleteid));
			return list2;
		}	
		
		/////////////////////////////////////////////////////////////		
		@RequestMapping(value = "/getappt_trade_code", method = RequestMethod.POST)
		public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getappt_trade_code(String a,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct description from CUE_TB_PSG_RANK_APP_MASTER where code=:a order by description");
			q.setParameter("a", a);		
			@SuppressWarnings("unchecked")
			List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
			tx.commit();
			session.close();
			return list;		
		}

		@RequestMapping(value = "/editgetUnitlist",method = RequestMethod.POST)
		public @ResponseBody List<Miso_Orbat_Unt_Dtl> editgetUnitlist(String unit_code,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no order by unit_name");
			q.setParameter("sus_no", unit_code);
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			return list;
		}

		
		//////////////////////////   update  ///////////////////////////////		
		@RequestMapping(value = "/admin /updateIncDecFootnoteUrl")
		public ModelAndView Edit_Inc_dec_footnotes(ModelMap Mmap,@ModelAttribute ("updateid") int updateid,@RequestParam(value = "msg", required = false) String msg,Authentication authentication
				,HttpSession sessionEdit) {
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("edit_Inc_dec_footnotesActionCMD", incdec.getCUE_TB_MISO_WEPE_PERS_FOOTNOTESbyid(updateid));
			Mmap.put("msg",msg);
			return new ModelAndView("Edit_Inc_dec_footnotesTiles");
		}
		
		@RequestMapping(value="/admin/Edit_Inc_dec_footnotesAction", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView editIncDecAction(ModelMap model, @ModelAttribute("edit_Inc_dec_footnotesActionCMD")CUE_TB_MISO_WEPE_PERS_FOOTNOTES updateid,HttpServletRequest request,@RequestParam(value="msg", required=false) String msg,HttpSession session11,HttpSession sessionEdit )
		{	
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String username = session11.getAttribute("username").toString();
			String r = request.getParameter("radio_status");
			String parent_arm1 = request.getParameter("arm_code");
			String amt_inc_dec1 = request.getParameter("amt_inc_dec1");
			String amt = request.getParameter("amt_inc_dec1");
			String we_pe_no = request.getParameter("we_pe_no");
			String category_of_personnel = request.getParameter("category_of_personnel");
			String rank_cat = request.getParameter("rank_cat");
			String rank = request.getParameter("rank");
			String arm_code = request.getParameter("arm_code");
			String appt_trade = request.getParameter("appt_trade");
			String location= request.getParameter("location");
			String formation = request.getParameter("formation");
			
			String scenario_unit = request.getParameter("scenario_unit");
			String condition = request.getParameter("condition");
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
			
			try {

			if(amt_inc_dec1.equals("")) {
				amt_inc_dec1 = "0.0";
				amt = "0.0";
			}
			Double amt_inc_dec =  Double.parseDouble(amt_inc_dec1);
			if(r.equals("Decrement"))
			{
				amt ="-" + amt;
				amt_inc_dec =  Double.valueOf(Double.parseDouble(amt));
			}
			else {
				amt_inc_dec =  Double.parseDouble(amt_inc_dec1);
			}
			String scenario1 = request.getParameter("scenario");
			if(updateid.getScenario() == "" || updateid.getScenario()==null ||  updateid.getScenario().equals(null) ||  updateid.getScenario().isEmpty())
			{
				model.put("updateid",updateid.getId());
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:updateIncDecFootnoteUrl");
			}
			if(validation.checkScenarioLength(updateid.getScenario())  == false){
				model.put("msg",validation.senarioMSG);
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
			if(scenario1 != "")
			{
				
				if(scenario1.equals("Location") )
				{
					if(updateid.getLocation().equals(",") || updateid.getLocation().equals("") || updateid.getLocation() ==null ||  updateid.getLocation().equals(null) ||  updateid.getLocation().isEmpty())
					{
						model.put("updateid",updateid.getId() );
						model.put("msg", "Please Enter Location");
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
				
					}
					if(validation.checkLocationLength(updateid.getLocation())  == false){
						model.put("msg",validation.locMSG);
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
					}
				
				}
				if(scenario1.equals("Formation"))
				{
					if(updateid.getFormation().equals("") || updateid.getFormation() ==null ||  updateid.getFormation().equals(null) || updateid.getFormation().isEmpty())
					{
						model.put("updateid",updateid.getId() );
						model.put("msg", "Please Enter Formation");
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
				
					}
					
					if(validation.checkFormationLength(updateid.getLocation())  == false){
						model.put("msg",validation.formMSG);
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
					}
				}
				if(scenario1.equals("Unit"))
				{
					if(updateid.getScenario_unit().equals(""))
					{
						model.put("updateid",updateid.getId() );
						model.put("msg", "Please Enter Unit");
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
				
					}
					if(validation.sus_noLength(updateid.getScenario_unit())  == false){
						model.put("msg",validation.unitMSG);
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
					}
				
				}
			}	
		
			if(r == ""  || r==null || r=="null" || r.equals(null) )
			{
			  model.put("updateid",updateid.getId() );
				 model.put("msg", "Please Select Increment/Decrement");
				return new ModelAndView("redirect:updateIncDecFootnoteUrl");
			}
			
		  if(amt_inc_dec.equals("") ||amt_inc_dec==null || amt_inc_dec.equals("null")|| amt_inc_dec.equals("0.0") || amt_inc_dec==0.0 ||amt_inc_dec.equals("undefined"))
			{
			  model.put("updateid",updateid.getId() );
			  model.put("msg", validation.incdecMSG);
			  return new ModelAndView("redirect:updateIncDecFootnoteUrl");
			}
			else {
				String base = request.getParameter("base_autho");
		        float f_base = Float.parseFloat(base);
		        float f_amt_inc_dec = Float.parseFloat(amt_inc_dec1);
				if(r == "Decrement" || r.equals("Decrement")) {
					if(f_amt_inc_dec > f_base) {
						model.put("updateid",updateid.getId());
						model.put("msg", "Please Check Amount of Inc/Dec");
						return new ModelAndView("redirect:updateIncDecFootnoteUrl");
					}
				}
			}
		  int lenval=0;
		  if(request.getParameter("radio_status").equals("Increment"))
			  lenval = 8;
		  else if(request.getParameter("radio_status").equals("Decrement"))
			  lenval = 9;
		  
		  String amt_inc_decvalid =  Double.toString(updateid.getAmt_inc_dec());
		  if(validation.checkAmt_inc_decLength(amt_inc_decvalid,lenval)  == false){
				model.put("msg",validation.amt_inc_decMSG);
				return new ModelAndView("redirect:updateIncDecFootnoteUrl");
			}
		    if(validation.checkConditionLength(updateid.getCondition())  == false)
			{
				model.put("msg",validation.conditionMSG);
				return new ModelAndView("redirect:Inc_dec_footnotes");
				
			}
			if(updateid.getCondition().equals("") ||updateid.getCondition() ==null ||  updateid.getCondition().equals(null) ||  updateid.getCondition().isEmpty())
			{
				model.put("updateid",updateid.getId() );
				model.put("msg", "Please Enter Conidtion");
				return new ModelAndView("redirect:updateIncDecFootnoteUrl");		
			}
						
			CUE_TB_MISO_WEPE_PERS_FOOTNOTES ctm= new CUE_TB_MISO_WEPE_PERS_FOOTNOTES();
			
			
			if(parent_arm1.equals("ERE"))
			{				
				ctm.setArm_code("arm_code");
			}			
			 Session sessioncount = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx1 = sessioncount.beginTransaction();
				
				Query q = sessioncount.createQuery("select count(*) FROM CUE_TB_MISO_WEPE_PERS_FOOTNOTES where appt_trade=:appt_trade and we_pe_no=:we_pe_no and rank = :rank and arm_code = :arm_code and rank_cat = :rank_cat and category_of_personnel =:category_of_personnel and condition =:condition and amt_inc_dec=:amt_inc_dec and id !=:id ") ;
				q.setParameter("appt_trade", appt_trade);
				q.setParameter("we_pe_no", we_pe_no);
				q.setParameter("rank", rank);
				q.setParameter("arm_code", arm_code);
				q.setParameter("category_of_personnel", category_of_personnel);
				q.setParameter("rank_cat", rank_cat);
				q.setParameter("condition", condition);
				q.setParameter("amt_inc_dec",amt_inc_dec);
				q.setParameter("id", updateid.getId());
				@SuppressWarnings("unchecked")
				Long count1 = (Long)q.uniqueResult();
				model.put("count", count1);
				tx1.commit();
				sessioncount.close();
			
			if(count1 == 0) {
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
				
				updateid.setAmt_inc_dec(Double.parseDouble(amt_inc_dec1));
				Session session = HibernateUtilNA.getSessionFactory().openSession();
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				Transaction tx = session.beginTransaction();
				String hql = "update CUE_TB_MISO_WEPE_PERS_FOOTNOTES  set location=:location,formation=:formation,scenario_unit=:scenario_unit,amt_inc_dec=:amt_inc_dec, condition=:condition,scenario=:scenario1,modified_by=:modified_by,modified_on=:modified_on,status='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
			    Query query = session.createQuery(hql).setString("scenario1", scenario1).setString("condition", condition).setString("location", location).setString("formation", formation).setString("scenario_unit", scenario_unit).setDouble("amt_inc_dec", amt_inc_dec).setString("modified_by", username).setString("modified_on", modifydate).setString("vettedby_dte1", "").setString("vettedby_dte2", "").setInteger("id",updateid.getId());
			   	int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if(rowCount > 0) {
					model.put("msg", "Updated Successfully");
				}else {
					model.put("msg", "Updated not Successfully");
				}
				
			}else {
				 model.put("msg", "Data Already Exists!");
				 model.put("edit_Inc_dec_footnotesActionCMD", incdec.getCUE_TB_MISO_WEPE_PERS_FOOTNOTESbyid(updateid.getId()));
				 return new ModelAndView("Edit_Inc_dec_footnotesTiles");				
			}
			
			}	
			catch (Exception e) {
				
			}
			
				return new ModelAndView("redirect:Inc_dec_footnotes");
			}
		
}