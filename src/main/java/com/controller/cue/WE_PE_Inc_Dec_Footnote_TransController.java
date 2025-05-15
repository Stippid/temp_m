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
import com.dao.cue.WePeInceDecrTransportFootnoteDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class WE_PE_Inc_Dec_Footnote_TransController {
	@Autowired
	WePeInceDecrTransportFootnoteDAO footNoteTransDAO;

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;
	
	@Autowired
	private Cue_wepe_conditionDAO vetting;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/we_pe_inc_dec_footnote_trans", method = RequestMethod.GET)
	public ModelAndView we_pe_inc_dec_footnote_trans(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("we_pe_inc_dec_footnote_trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("we_pe_inc_dec_footnote_transTiles", "we_pe_inc_dec_footnote_transCMD", new CUE_TB_MISO_WEPE_TRANS_FOOTNOTES());
	}
	
	@RequestMapping(value = "/getAttributeFromFootnoteMaster23",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAttributeFromFootnoteMaster23(String we_pe_no,String mct_no,String status) {
	 List<Map<String, Object>> list =footNoteTransDAO.getAttributeFromFootnoteMaster23(we_pe_no,mct_no,status);
			return list;
	}
	
	@RequestMapping(value = "/admin/we_pe_inc_dec_footnote_trans1", method = RequestMethod.POST)
	public ModelAndView we_pe_inc_dec_footnote_trans1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,
			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "unit1", required = false) String unit){
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
			Mmap.put("mct_no1", mct_no);
			Mmap.put("we_pe_no1", we_pe_no);
			List<Map<String, Object>> list =footNoteTransDAO.getAttributeFromFootnoteMaster1(mct_no,we_pe_no,status,roleType,roleAccess);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
		return new ModelAndView("we_pe_inc_dec_footnote_transTiles");
	}
	
	@RequestMapping(value = "/WEPEIncDecFootnoteTransAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(
			@ModelAttribute("we_pe_inc_dec_footnote_transCMD") CUE_TB_MISO_WEPE_TRANS_FOOTNOTES ud,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		int roleid = (Integer) session.getAttribute("roleid");

		String we_pe_no = "";
		we_pe_no = request.getParameter("we_pe_no");
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String modifieddate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String r = request.getParameter("inc_dec");
		String mct_no = request.getParameter("mct_no");
		String condition = request.getParameter("condition");
		String we_pe = request.getParameter("we_pe");
		String location_code = request.getParameter("location");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("scenario_unit");
		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();
		String scenario2 = ud.getScenario();
		try {

			if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (ud.getWe_pe_no().equals("")) {
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (validation.checkWepeLength(ud.getWe_pe_no()) == false) {
				model.put("msg", validation.wepenoMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (ud.getScenario().equals("")) {
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (validation.checkScenarioLength(ud.getScenario()) == false) {
				model.put("msg", validation.senarioMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (scenario2.equals("Location")) {
				if (ud.getLocation().equals("")) {
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");

				}
				if (validation.checkLocationLength(ud.getLocation()) == false) {
					model.put("msg", validation.locMSG);
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
				}

			}
			if (scenario2.equals("Formation")) {
				if (ud.getFormation().equals("")) {
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");

				}
				if (validation.checkFormationLength(ud.getFormation()) == false) {
					model.put("msg", validation.formMSG);
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
				}
			}

			if (scenario2.equals("Unit")) {
				if (ud.getScenario_unit().equals("")) {
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");

				}
				if (validation.sus_noLength(ud.getScenario_unit()) == false) {
					model.put("msg", validation.unitMSG);
					return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
				}
			}
			if (ud.getMct_no().equals("")) {
				model.put("msg", "Please Enter MCT No");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (validation.checkMctLength(ud.getMct_no()) == false) {
				model.put("msg", validation.mctnoMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (r == "" || r == null || r == "null" || r.equals(null)) {
				model.put("msg", "Please Select Increment/Decrement");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (ud.getAmt_inc_dec() == 0.0 || ud.getAmt_inc_dec() == 0) {
				model.put("msg", "Please Enter Amount of Increment/Decrement");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			int lenval = 0;
			if (request.getParameter("inc_dec").equals("I"))
				lenval = 8;
			else if (request.getParameter("inc_dec").equals("D"))
				lenval = 9;
			String amt_inc_decvalid = Double.toString(ud.getAmt_inc_dec());
			if (validation.checkAmt_inc_decLength(amt_inc_decvalid, lenval) == false) {
				model.put("msg", validation.amt_inc_decMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (ud.getCondition().equals("")) {
				model.put("msg", "Please Enter Condition");
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (validation.checkConditionLength(ud.getCondition()) == false) {
				model.put("msg", validation.conditionMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}
			if (validation.checkRemarksLength(ud.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
			}

			else {
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session2.beginTransaction();
				Query q = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no and mct_no=:mct_no");
				q.setParameter("we_pe_no", we_pe_no);
				q.setParameter("mct_no", mct_no);
				Long count = (Long) q.uniqueResult();
				model.put("count", count);
				tx3.commit();
				session2.close();
				/// ------
				/// END------------------------------------------------------------------------------//

				if (count == 0) {
					CUE_TB_MISO_WEPE_TRANSPORT_DET rs = new CUE_TB_MISO_WEPE_TRANSPORT_DET();

					int auth_amt = rs.getAuth_amt();
					rs.setAuth_amt(0);
					rs.setWe_pe_no(request.getParameter("we_pe_no"));
					rs.setCreated_by(username);
					rs.setCreated_on(creadtedate);
					rs.setMct_no(request.getParameter("mct_no"));

					rs.setRemarks(request.getParameter("remarks"));
					rs.setAuth_amt(auth_amt);
					rs.setEntity("UNIT");
					rs.setStatus("1");
					rs.setRoleid(roleid);

					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					sessionHQL.beginTransaction();
					sessionHQL.save(rs);
					model.put("msg", "Data saved successfully");
					sessionHQL.getTransaction().commit();
					sessionHQL.close();
				}

				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				Query q1 = session1.createQuery("select count(mct_no) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where mct_no=:mct_no and we_pe_no=:we_pe_no and condition=:condition");
				q1.setParameter("we_pe_no", we_pe_no);
				q1.setParameter("mct_no", mct_no);
				q1.setParameter("condition", condition);
				Long count1 = (Long) q1.uniqueResult();
				@SuppressWarnings("unchecked")
				List<Integer> list = (List<Integer>) q1.list();
				tx.commit();
				session1.close();

				if (count1 == 0) {
					int amt = Integer.parseInt(request.getParameter("amt_inc_dec"));
					if (r.equals("D")) {
						String sum = "-" + amt;
						ud.setAmt_inc_dec(Integer.parseInt(sum));
					} else {
						ud.setAmt_inc_dec(amt);
					}
					ud.setMct_no(mct_no);
					ud.setActual_inlieu_auth(0);
					ud.setAprv_rejc_by(username);
					ud.setCondition(request.getParameter("condition"));
					ud.setCreated_by(username);
					ud.setCreated_on(creadtedate);
					ud.setDate_of_apprv_rejc(creadtedate);
					ud.setModified_by(username);
					ud.setModified_on(modifieddate);
					ud.setStatus("0");
					ud.setType_of_footnote("1");
					ud.setRemarks(request.getParameter("remarks"));
					ud.setRoleid(roleid);

					String scenario = ud.getScenario();
					if (scenario.equals("Location")) {
						ud.setLocation(request.getParameter("location"));
						ud.setFormation(null);
						ud.setScenario_unit(null);
						ud.setScenario(scenario);
					} else if (scenario.equals("Formation")) {
						ud.setFormation(request.getParameter("formation"));
						ud.setLocation(null);
						ud.setScenario_unit(null);
						ud.setScenario(scenario);
					} else if (scenario.equals("Unit")) {
						ud.setFormation(null);
						ud.setLocation(null);
						ud.setScenario_unit(request.getParameter("scenario_unit"));
						ud.setScenario(scenario);
					}

					else if (scenario.equals("Others")) {
						ud.setFormation(null);
						ud.setLocation(null);
						ud.setScenario_unit(null);
						ud.setScenario("Others");
					} else {
						ud.setScenario(null);
						ud.setFormation(null);
						ud.setLocation(null);
						ud.setScenario_unit(null);
					}

					ud.setVersion_no(String.valueOf("1"));
					Session session3 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx1 = (session3).beginTransaction();
					session3.save(ud);
					tx1.commit();
					session3.close();
					model.put("msg", "Data saved successfully");
				} else {
					model.put("msg", "Data already exist!");
				}
			}
		} catch (Exception e) {
			session0.getTransaction().rollback();
		} finally {

			tx0.commit();
			session0.close();
		}
		List<Map<String, Object>> list = footNoteTransDAO.getAttributeFromFootnoteMaster1("", we_pe_no, "", "","");
		model.put("list", list);
		model.put("we_pe1", we_pe);
		model.put("we_pe_no1", we_pe_no);
		model.put("scenario1", scenario2);
		model.put("location1", request.getParameter("location_name"));
		model.put("formation1", request.getParameter("formation_name"));
		model.put("unit1", request.getParameter("scenario_unit_name"));
		model.put("location1_hid", location_code);
		model.put("formation1_hid", formation_code);
		model.put("unit1_hid", unit_code);
		model.put("list.size()", list.size());
		return new ModelAndView("we_pe_inc_dec_footnote_transTiles");
	}		
	
	////---- search---------------------------------------------------------------------------------------------------////	
	@RequestMapping(value = "/search_WE_PE_footnote_for_trans", method = RequestMethod.GET)
	public ModelAndView search_WE_PE_footnote_for_trans(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_WE_PE_footnote_for_trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_WE_PE_footnote_for_transTiles");
	}
	
	@RequestMapping(value = "/admin/search_WE_PE_footnote_for_trans1", method = RequestMethod.POST)
	public ModelAndView search_WE_PE_footnote_for_trans1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "table_title1", required = false) String table_title,
			@RequestParam(value = "std_nomclature1", required = false) String std_nomclature,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("status1", status);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("table_title1", table_title);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("std_nomclature1", std_nomclature);
			
			List<Map<String, Object>> list =footNoteTransDAO.getAttributeFromFootnoteMaster(status,mct_no,we_pe_no,roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("search_WE_PE_footnote_for_transTiles");
	}

	
	@RequestMapping(value = "/admin/ApprovedFootnoteUrl",method=RequestMethod.POST)
	public ModelAndView ApprovedFootnoteUrl(@ModelAttribute("appid") int appid,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "we_pe2", required = false) String we_pe,
			@RequestParam(value = "table_title2", required = false) String table_title,
			@RequestParam(value = "mct_no2", required = false) String mct_no,
			@RequestParam(value = "std_nomclature2", required = false) String std_nomclature,
			@RequestParam(value = "status2", required = false) String status){
		
		  String username = session.getAttribute("username").toString();
		  
		  String mst = footNoteTransDAO.setApprovedItem(appid,username);
		  if(mst.equals("Approved Successfully")) {
				vetting.updateVettingDtl( we_pe_no, "2");
			}
		  
			Mmap.put("msg", mst);	
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("status1", status);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("std_nomclature1", std_nomclature);
			Mmap.put("table_title1", table_title);
			Mmap.put("we_pe_no1", we_pe_no);
			
			List<Map<String, Object>> list =footNoteTransDAO.getAttributeFromFootnoteMaster(status,mct_no,we_pe_no,roleType,roleAccess,roleid);
			 
			
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("search_WE_PE_footnote_for_transTiles");
	}
	
	@RequestMapping(value = "/deleteFootnoteUrlAdd",method=RequestMethod.POST)
	public @ResponseBody List<String> deleteFootnoteUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(footNoteTransDAO.setDeleteItem(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/updateFootnoteUrl")
	public ModelAndView updateFootnoteUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		model.put("msg", msg);
		
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("edit_details_of_inc_dec_footnote_for_transCmd", footNoteTransDAO.getCUE_TB_MISO_MMS_ITEM_MSTRid(updateid));	
		return new ModelAndView("edit_details_of_inc_dec_footnote_for_transTiles");
	}
	
	@RequestMapping(value = "/edit_details_of_inc_dec_footnote_for_transAction",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView edit_details_of_inc_dec_footnote_for_transAction(@ModelAttribute("edit_details_of_inc_dec_footnote_for_transCmd") CUE_TB_MISO_WEPE_TRANS_FOOTNOTES updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session11,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		String username = session11.getAttribute("username").toString();
		Session sessioncount = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = sessioncount.beginTransaction();
		Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no and mct_no=:mct_no and condition=:condition and id !=:id");
		q1.setParameter("we_pe_no", updateid.getWe_pe_no());
		q1.setParameter("mct_no", updateid.getMct_no());
		q1.setParameter("condition", updateid.getCondition());
		q1.setParameter("id", updateid.getId());
		@SuppressWarnings("unchecked")
		Long count1 = (Long)q1.uniqueResult();
		model.put("count", count1);
		tx.commit();
		sessioncount.close();		
		
		String location = request.getParameter("location");
		String formation = request.getParameter("formation");
		String scenario1 = request.getParameter("scenario");
		String scenario_unit = request.getParameter("scenario_unit");
		int amt_inc_dec =  Integer.parseInt(request.getParameter("amt_inc_dec"));
		String remarks = request.getParameter("remarks");
		String condition = request.getParameter("condition");
		String mct_no = request.getParameter("mct_no");
		
		
		if(scenario1.equals(""))
		{
			model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Scenario");
			return new ModelAndView("redirect:updateFootnoteUrl");
		}
		if(validation.checkScenarioLength(updateid.getScenario())  == false){
	 		model.put("updateid", updateid.getId());
			model.put("msg",validation.senarioMSG);
			return new ModelAndView("redirect:updateFootnoteUrl");
		}
		if(scenario1.equals("Location"))
		{
			if(location.equals(""))
			{
					model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:updateFootnoteUrl");
				
				}
				if(validation.checkLocationLength(location)  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.locMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
				
			}
			if(scenario1.equals("Formation"))
			{
				if(formation.equals(""))
				{
					model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:updateFootnoteUrl");
				
				}
				if(validation.checkFormationLength(formation)  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.formMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
			}
			
			if(scenario1.equals("Unit"))
			{
				if(scenario_unit.equals(""))
				{
					model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:updateFootnoteUrl");
				
				}
				if(validation.sus_noLength(scenario_unit)  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.unitMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
			}
			
			
			  if(updateid.getAmt_inc_dec() == 0.0 || updateid.getAmt_inc_dec() == 0 )
				{
				  	model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Amount of Increment/Decrement");
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
			  if(updateid.getCondition().equals(""))
				{
				  	model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Condition");
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
		
		
		if(validation.checkConditionLength(updateid.getCondition())  == false){
			model.put("updateid", updateid.getId());
			model.put("msg",validation.conditionMSG);
			return new ModelAndView("redirect:updateFootnoteUrl");
		} 
		
		if(validation.checkRemarksLength(updateid.getRemarks())  == false){
			model.put("updateid", updateid.getId());
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:updateFootnoteUrl");
		}
	 
		  int lenval=0;
		  if(request.getParameter("inc_dec").equals("I"))
			  lenval = 8;
		  else if(request.getParameter("inc_dec").equals("D"))
			  lenval = 9;
		  
		  String amt_inc_decvalid =  Double.toString(updateid.getAmt_inc_dec());
		  if(validation.checkAmt_inc_decLength(amt_inc_decvalid,lenval)  == false){
			  	model.put("updateid", updateid.getId());
				model.put("msg",validation.amt_inc_decMSG);
				return new ModelAndView("redirect:updateFootnoteUrl");
			}
		
		if(count1 == 0) {			
			String scenario = request.getParameter("scenario");
			if(scenario.equals("Location")) {
				scenario1=scenario;
				location =request.getParameter("location");
				formation="";
				scenario_unit="";
				if(validation.checkLocationLength(updateid.getLocation())  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.locMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
			}
			else if(scenario.equals("Formation")) {
				scenario1=scenario;
				location ="";
				formation=request.getParameter("formation");
				scenario_unit="";
				
				if(validation.checkFormationLength(updateid.getFormation())  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.formMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
			}
			else if(scenario.equals("Unit")) {
				scenario1=scenario;
				location ="";
				formation="";
				scenario_unit=request.getParameter("scenario_unit");
				if(validation.sus_noLength(updateid.getScenario_unit())  == false){
					model.put("updateid", updateid.getId());
					model.put("msg",validation.unitMSG);
					return new ModelAndView("redirect:updateFootnoteUrl");
				}
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
			
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx1 = session.beginTransaction();
			String r = request.getParameter("inc_dec");
			int amt = Integer.parseInt(request.getParameter("amt_inc_dec")) ;
			if(r.equals("D"))
			{
				String sum = "-" + amt;	
				amt_inc_dec=  Integer.parseInt(sum);
			}
			else {
				 amt_inc_dec =  Integer.parseInt(request.getParameter("amt_inc_dec"));
			}
			String hql = "update CUE_TB_MISO_WEPE_TRANS_FOOTNOTES set location=:location, formation=:formation ,scenario_unit=:scenario_unit,condition=:condition , mct_no=:mct_no , amt_inc_dec=:amt_inc_dec, remarks=:remarks, scenario=:scenario,modified_by=:modified_by,modified_on=:modified_on, status='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
		    Query query = session.createQuery(hql).setString("location", location).setString("formation", formation).setString("condition", condition).setString("scenario_unit", scenario_unit).setString("mct_no",mct_no).setInteger("amt_inc_dec",amt_inc_dec).setString("remarks",remarks).setString("scenario", scenario1).setString("modified_by", username).setString("modified_on", modifydate)
		    		.setString("vettedby_dte1", "").setString("vettedby_dte2", "").setInteger("id",updateid.getId());
		    int rowCount = query.executeUpdate();
			tx1.commit();
			session.close();
			if(rowCount > 0) {
				model.put("msg", "Updated Successfully");
			}else {
				model.put("msg", "Updated not Successfully");
			}
			return new ModelAndView("redirect:we_pe_inc_dec_footnote_trans");
		}
			else {
				   model.put("msg", "Data already exist !");
				   model.put("edit_details_of_inc_dec_footnote_for_transCmd", footNoteTransDAO.getCUE_TB_MISO_MMS_ITEM_MSTRid(updateid.getId()));	
					return new ModelAndView("edit_details_of_inc_dec_footnote_for_transTiles");
				}		
	}  
	
	@RequestMapping(value = "/updateRejectaction", method=RequestMethod.POST)	 
	public @ResponseBody List<String> updateRejectaction(String remarks,String letter_no,int id) {
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wepe_trans_footnotes",remarks,id,letter_no);
		return list2;
	}

}
