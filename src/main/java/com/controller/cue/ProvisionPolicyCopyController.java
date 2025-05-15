package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.models.CUE_TB_MISO_PROVISION_MASTER;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_DET;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_MASTER;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_PROVISION_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_PROVISION_WEAPON_DET;
import com.models.CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES;
import com.models.CUE_TB_MISO_PROVISION_WET_EQUIP;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ProvisionPolicyCopyController {

	ValidationController validation = new ValidationController();
	/////////////////////////////////////// Weapon Copy Start //////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/copy_provision_policyAct",method=RequestMethod.POST)
	public ModelAndView copy_provision_policyAct(@ModelAttribute("copy_provision_policyCmd") CUE_TB_MISO_PROVISION_MASTER rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String month_cal_old = request.getParameter("month_cal_old");
		String year_cal_old = request.getParameter("year_cal_old");
		String month_cal = request.getParameter("month_cal_new");
		String year_cal = request.getParameter("year_cal_new");
		String letter_no = request.getParameter("letter_no_new");
		String letter_date = request.getParameter("letter_date_new");
		
		if(validation.checkWetPetLength(letter_no)  == false ){
			model.put("msg",validation.authLtrnoMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkDateLength(letter_date)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkWETypeLength(year_cal)  == false){
			model.put("msg",validation.yearCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkMonthLength(month_cal)  == false){
	 		model.put("msg",validation.monthCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		/////////////////////////// start save CUE_TB_MISO_PROVISION_MASTER 
		Session sessionw = HibernateUtil.getSessionFactory().openSession();
		Transaction txw = sessionw.beginTransaction();
		Query qw = sessionw.createQuery("from CUE_TB_MISO_PROVISION_MASTER where month_cal=:month_cal_old and year_cal=:year_cal_old and (status_delete is  null or status_delete ='')");		
		qw.setParameter("month_cal_old", month_cal_old);
		qw.setParameter("year_cal_old", year_cal_old);		
		List<CUE_TB_MISO_PROVISION_MASTER> list = (List<CUE_TB_MISO_PROVISION_MASTER>) qw.list();
		txw.commit();
		sessionw.close();

		List<Integer> oldproid = new ArrayList<Integer>();	
		List<Integer> newproid = new ArrayList<Integer>();		
		if(list.isEmpty()) 
		{
			model.put("msg", "Selected Month and Year wise Data does not exist!");
		}
		else {			
			for(int i=0; i<list.size(); i++) {
				Session sessionw1 = HibernateUtil.getSessionFactory().openSession();
				Transaction txw1 = sessionw1.beginTransaction();
				Query qw1 = sessionw1.createQuery("from CUE_TB_MISO_PROVISION_MASTER where month_cal=:month_cal and year_cal=:year_cal and we_pe_no=:we_pe_no and (status_delete is  null or status_delete ='')");		
				qw1.setParameter("month_cal", month_cal);
				qw1.setParameter("year_cal", year_cal);	
				qw1.setParameter("we_pe_no", list.get(i).getWe_pe_no());	
				List<CUE_TB_MISO_PROVISION_MASTER> list1 = (List<CUE_TB_MISO_PROVISION_MASTER>) qw1.list();
				txw1.commit();
				sessionw1.close();
				
				if(list1.size() == 0) 
				{						
					oldproid.add(list.get(i).getId());				
					rs.setForce_type(list.get(i).getForce_type());
					rs.setStatus(list.get(i).getStatus());
					rs.setSponsor_dire(list.get(i).getSponsor_dire());
					rs.setModified_by(list.get(i).getModified_by());
					rs.setModified_on(list.get(i).getModified_on());
					rs.setMonth_cal(month_cal);
					rs.setNo_of_units(list.get(i).getNo_of_units());
					rs.setUnit_type(list.get(i).getUnit_type());
					rs.setWe_pe_no(list.get(i).getWe_pe_no());
					rs.setWet_pet_no(list.get(i).getWet_pet_no());
					rs.setWe_type(list.get(i).getWe_type());
					rs.setYear_cal(year_cal);
					rs.setLetter_no(letter_no);
					rs.setLetter_date(letter_date);
					rs.setCreated_by(username);
					rs.setCreated_on(creadtedate);
					
					Session session02 = HibernateUtil.getSessionFactory().openSession();
					session02.beginTransaction();
					
					int proid = (Integer) session02.save(rs);
					session02.getTransaction().commit();
					session02.close();
					
					newproid.add(proid);
					model.put("msg", "Data saved Successfully");
				}
			
			}
		}
		/////////////////////////// end save CUE_TB_MISO_PROVISION_MASTER 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_WEAPON_DET 
		CUE_TB_MISO_PROVISION_WEAPON_DET det=new CUE_TB_MISO_PROVISION_WEAPON_DET();
		if(oldproid.size() > 0)
		{
			for(int d=0; d < oldproid.size(); d++) {
				Session sessiond = HibernateUtil.getSessionFactory().openSession();
				Transaction txd = sessiond.beginTransaction();
				Query qwd = sessiond.createQuery("from CUE_TB_MISO_PROVISION_WEAPON_DET where provision_id=:provision_id");		
				qwd.setParameter("provision_id", oldproid.get(d));	
				List<CUE_TB_MISO_PROVISION_WEAPON_DET> listd = (List<CUE_TB_MISO_PROVISION_WEAPON_DET>) qwd.list();
				txd.commit();
				sessiond.close();
				if(listd.size() > 0) 
				{
					det.setProvision_id(newproid.get(d));
					for(int d1=0; d1<listd.size(); d1++) {						
						det.setWe_pe_no(listd.get(d1).getWe_pe_no());
						det.setCreated_by(username);
						det.setCreated_on(creadtedate);
						det.setModified_by(listd.get(d1).getModified_by());
						det.setModified_on(listd.get(d1).getModified_on());						
						det.setAuth_weapon(listd.get(d1).getAuth_weapon());
						det.setItem_code(listd.get(d1).getItem_code());
						
						Session sessiond2 = HibernateUtil.getSessionFactory().openSession();
						sessiond2.beginTransaction();
						sessiond2.save(det);
						sessiond2.getTransaction().commit();
						sessiond2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}
		/////////////////////////// end save CUE_TB_MISO_PROVISION_WEAPON_DET 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_WEAPONS_MDFS 		
		CUE_TB_MISO_PROVISION_WEAPONS_MDFS mdfs=new CUE_TB_MISO_PROVISION_WEAPONS_MDFS();
		if(oldproid.size() > 0)
		{
			for(int m=0; m < oldproid.size(); m++) {
				Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
				Transaction txmdfs = sessionmdfs.beginTransaction();
				Query qwmdfs = sessionmdfs.createQuery("from CUE_TB_MISO_PROVISION_WEAPONS_MDFS where provision_id=:provision_id");		
				qwmdfs.setParameter("provision_id", oldproid.get(m));	
				List<CUE_TB_MISO_PROVISION_WEAPONS_MDFS> listmdfs = (List<CUE_TB_MISO_PROVISION_WEAPONS_MDFS>) qwmdfs.list();
				txmdfs.commit();
				sessionmdfs.close();
				if(listmdfs.size() > 0) 
				{
					mdfs.setProvision_id(newproid.get(m));
					for(int m1=0; m1<listmdfs.size(); m1++) {						
						mdfs.setWe_pe_no(listmdfs.get(m1).getWe_pe_no());
						mdfs.setCreated_by(username);
						mdfs.setCreated_on(creadtedate);
						mdfs.setModified_by(listmdfs.get(m1).getModified_by());
						mdfs.setModified_on(listmdfs.get(m1).getModified_on());						
						mdfs.setModification(listmdfs.get(m1).getModification());
						mdfs.setItem_code(listmdfs.get(m1).getItem_code());
						mdfs.setNo_of_units(listmdfs.get(m1).getNo_of_units());
						mdfs.setStatus(listmdfs.get(m1).getStatus());
						mdfs.setAmt_inc_dec(listmdfs.get(m1).getAmt_inc_dec());
						
						Session sessionmdfs2 = HibernateUtil.getSessionFactory().openSession();
						sessionmdfs2.beginTransaction();
						sessionmdfs2.save(mdfs);
						sessionmdfs2.getTransaction().commit();
						sessionmdfs2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}		
		/////////////////////////// end save CUE_TB_MISO_PROVISION_WEAPONS_MDFS 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES 		
		CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES foot=new CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES();
		if(oldproid.size() > 0)
		{
			for(int f=0; f < oldproid.size(); f++) {
				Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
				Transaction txfoot = sessionfoot.beginTransaction();
				Query qwfoot = sessionfoot.createQuery("from CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES where provision_id=:provision_id");		
				qwfoot.setParameter("provision_id", oldproid.get(f));	
				List<CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES> listfoot = (List<CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES>) qwfoot.list();
				txfoot.commit();
				sessionfoot.close();
				if(listfoot.size() > 0) 
				{
					foot.setProvision_id(newproid.get(f));
					for(int f1=0; f1<listfoot.size(); f1++) {						
						foot.setWe_pe_no(listfoot.get(f1).getWe_pe_no());
						foot.setCreated_by(username);
						foot.setCreated_on(creadtedate);
						foot.setModified_by(listfoot.get(f1).getModified_by());
						foot.setModified_on(listfoot.get(f1).getModified_on());						
						foot.setCondition(listfoot.get(f1).getCondition());
						foot.setItem_code(listfoot.get(f1).getItem_code());
						foot.setNo_of_units(listfoot.get(f1).getNo_of_units());
						foot.setStatus(listfoot.get(f1).getStatus());
						foot.setAmt_inc_dec(listfoot.get(f1).getAmt_inc_dec());
						foot.setFoot_id(listfoot.get(f1).getFoot_id());
						foot.setScenario(listfoot.get(f1).getScenario());
						foot.setLoc_for_unit(listfoot.get(f1).getLoc_for_unit());
						
						Session sessionfoot2 = HibernateUtil.getSessionFactory().openSession();
						sessionfoot2.beginTransaction();
						sessionfoot2.save(foot);
						sessionfoot2.getTransaction().commit();
						sessionfoot2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}			
		/////////////////////////// end save CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES 
		
		
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_WET_EQUIP 		
		CUE_TB_MISO_PROVISION_WET_EQUIP eqpt=new CUE_TB_MISO_PROVISION_WET_EQUIP();
		if(oldproid.size() > 0)
		{
		for(int f=0; f < oldproid.size(); f++) {
			
		Session sessioneqpt = HibernateUtil.getSessionFactory().openSession();
		Transaction txeqpt = sessioneqpt.beginTransaction();
		Query eqptqry = sessioneqpt.createQuery("from CUE_TB_MISO_PROVISION_WET_EQUIP where provision_id=:provision_id");		
		eqptqry.setParameter("provision_id", oldproid.get(f));	
		List<CUE_TB_MISO_PROVISION_WET_EQUIP> listeqpt = (List<CUE_TB_MISO_PROVISION_WET_EQUIP>) eqptqry.list();
		txeqpt.commit();
		sessioneqpt.close();
		if(listeqpt.size() > 0) 
		{
			eqpt.setProvision_id(newproid.get(f));
			for(int f1=0; f1<listeqpt.size(); f1++) {	
				eqpt.setWet_pet_no(listeqpt.get(f1).getWet_pet_no());
				eqpt.setCreated_by(username);
				eqpt.setCreated_on(creadtedate);
				eqpt.setModified_by(listeqpt.get(f1).getModified_by());
				eqpt.setModified_on(listeqpt.get(f1).getModified_on());						
				eqpt.setItem_seq_no(listeqpt.get(f1).getItem_seq_no());
				eqpt.setAuth_weapon(listeqpt.get(f1).getAuth_weapon());
			
				
				Session sessioneqpt2 = HibernateUtil.getSessionFactory().openSession();
				sessioneqpt2.beginTransaction();
				sessioneqpt2.save(eqpt);
				sessioneqpt2.getTransaction().commit();
				sessioneqpt2.close();
				model.put("msg", "Data saved Successfully");
				}
			}
		}
		}			
		/////////////////////////// end save CUE_TB_MISO_PROVISION_WET_EQUIP 		
		return new ModelAndView("redirect:provisionMstUrl");
	}
	
	/////////////////////////////////////// Weapon Copy End //////////////////////////////////////////////
	
	/////////////////////////////////////// Transport Copy //////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/copy_provision_policy_transAct",method=RequestMethod.POST)
	public ModelAndView copy_provision_policy_transAct(@ModelAttribute("copy_provision_policy_transCmd") CUE_TB_MISO_PROVISION_TRANSPORT_MASTER rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		String month_cal_old = request.getParameter("month_cal_old");
		String year_cal_old = request.getParameter("year_cal_old");
		String month_cal = request.getParameter("month_cal_new");
		String year_cal = request.getParameter("year_cal_new");
		String letter_no = request.getParameter("letter_no_new");
		String letter_date = request.getParameter("letter_date_new");
		
		if(validation.checkWetPetLength(letter_no)  == false ){
	 		model.put("msg",validation.authLtrnoMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkDateLength(letter_date)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkWETypeLength(year_cal)  == false){
			model.put("msg",validation.yearCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if(validation.checkMonthLength(month_cal)  == false){
			model.put("msg",validation.monthCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		/////////////////////////// start save CUE_TB_MISO_PROVISION_TRANSPORT_MASTER 
		Session sessionw = HibernateUtil.getSessionFactory().openSession();
		Transaction txw = sessionw.beginTransaction();
		Query qw = sessionw.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_MASTER where month_cal=:month_cal_old and year_cal=:year_cal_old and (status_delete is  null or status_delete ='')");		
		qw.setParameter("month_cal_old", month_cal_old);
		qw.setParameter("year_cal_old", year_cal_old);		
		List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER> list = (List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER>) qw.list();
		txw.commit();
		sessionw.close();

		List<Integer> oldproid = new ArrayList<Integer>();	
		List<Integer> newproid = new ArrayList<Integer>();		
		if(list.isEmpty()) 
		{
			model.put("msg", "Selected Month and Year wise Data does not exist!");
		}
		else {			
			for(int i=0; i<list.size(); i++) {
				Session sessionw1 = HibernateUtil.getSessionFactory().openSession();
				Transaction txw1 = sessionw1.beginTransaction();
				Query qw1 = sessionw1.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_MASTER where month_cal=:month_cal and year_cal=:year_cal and we_pe_no=:we_pe_no and (status_delete is  null or status_delete ='')");		
				qw1.setParameter("month_cal", month_cal);
				qw1.setParameter("year_cal", year_cal);	
				qw1.setParameter("we_pe_no", list.get(i).getWe_pe_no());	
				List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER> list1 = (List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER>) qw1.list();
				txw1.commit();
				sessionw1.close();
				
				if(list1.size() == 0) 
				{						
					oldproid.add(list.get(i).getId());				
					rs.setForce_type(list.get(i).getForce_type());
					rs.setStatus(list.get(i).getStatus());
					rs.setSponsor_dire(list.get(i).getSponsor_dire());
					rs.setModified_by(list.get(i).getModified_by());
					rs.setModified_on(list.get(i).getModified_on());
					rs.setMonth_cal(month_cal);
					rs.setNo_of_units(list.get(i).getNo_of_units());
					rs.setUnit_type(list.get(i).getUnit_type());
					rs.setWe_pe_no(list.get(i).getWe_pe_no());
					rs.setWet_pet_no(list.get(i).getWet_pet_no());
					rs.setWe_type(list.get(i).getWe_type());
					rs.setYear_cal(year_cal);
					rs.setLetter_no(letter_no);
					rs.setLetter_date(letter_date);
					rs.setCreated_by(username);
					rs.setCreated_on(creadtedate);
					
					Session session02 = HibernateUtil.getSessionFactory().openSession();
					session02.beginTransaction();
					int proid = (Integer) session02.save(rs);
					session02.getTransaction().commit();
					session02.close();
					
					newproid.add(proid);
					model.put("msg", "Data saved Successfully");
				}
			
			}
		}
		/////////////////////////// end save CUE_TB_MISO_PROVISION_TRANSPORT_MASTER 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_TRANSPORT_DET 
		CUE_TB_MISO_PROVISION_TRANSPORT_DET det=new CUE_TB_MISO_PROVISION_TRANSPORT_DET();
		if(oldproid.size() > 0)
		{
			for(int d=0; d < oldproid.size(); d++) {
				Session sessiond = HibernateUtil.getSessionFactory().openSession();
				Transaction txd = sessiond.beginTransaction();
				Query qwd = sessiond.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_DET where provision_id=:provision_id");		
				qwd.setParameter("provision_id", oldproid.get(d));	
				List<CUE_TB_MISO_PROVISION_TRANSPORT_DET> listd = (List<CUE_TB_MISO_PROVISION_TRANSPORT_DET>) qwd.list();
				txd.commit();
				sessiond.close();
				if(listd.size() > 0) 
				{
					det.setProvision_id(newproid.get(d));
					for(int d1=0; d1<listd.size(); d1++) {						
						det.setWe_pe_no(listd.get(d1).getWe_pe_no());
						det.setCreated_by(username);
						det.setCreated_on(creadtedate);
						det.setModified_by(listd.get(d1).getModified_by());
						det.setModified_on(listd.get(d1).getModified_on());						
						det.setMct_no(listd.get(d1).getMct_no());
						det.setAuth_amt(listd.get(d1).getAuth_amt());
						
						Session sessiond2 = HibernateUtil.getSessionFactory().openSession();
						sessiond2.beginTransaction();
						sessiond2.save(det);
						sessiond2.getTransaction().commit();
						sessiond2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}
		/////////////////////////// end save CUE_TB_MISO_PROVISION_TRANSPORT_DET 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_TRANSPORT_MDFS 		
		CUE_TB_MISO_PROVISION_TRANSPORT_MDFS mdfs=new CUE_TB_MISO_PROVISION_TRANSPORT_MDFS();
		if(oldproid.size() > 0)
		{
			for(int m=0; m < oldproid.size(); m++) {
				Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
				Transaction txmdfs = sessionmdfs.beginTransaction();
				Query qwmdfs = sessionmdfs.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_MDFS where provision_id=:provision_id");		
				qwmdfs.setParameter("provision_id", oldproid.get(m));	
				List<CUE_TB_MISO_PROVISION_TRANSPORT_MDFS> listmdfs = (List<CUE_TB_MISO_PROVISION_TRANSPORT_MDFS>) qwmdfs.list();
				txmdfs.commit();
				sessionmdfs.close();
				if(listmdfs.size() > 0) 
				{
					mdfs.setProvision_id(newproid.get(m));
					for(int m1=0; m1<listmdfs.size(); m1++) {						
						mdfs.setWe_pe_no(listmdfs.get(m1).getWe_pe_no());
						mdfs.setCreated_by(username);
						mdfs.setCreated_on(creadtedate);
						mdfs.setModified_by(listmdfs.get(m1).getModified_by());
						mdfs.setModified_on(listmdfs.get(m1).getModified_on());						
						mdfs.setModification(listmdfs.get(m1).getModification());
						mdfs.setMct_no(listmdfs.get(m1).getMct_no());
						mdfs.setNo_of_units(listmdfs.get(m1).getNo_of_units());
						mdfs.setStatus(listmdfs.get(m1).getStatus());
						mdfs.setAmt_inc_dec(listmdfs.get(m1).getAmt_inc_dec());
						
						Session sessionmdfs2 = HibernateUtil.getSessionFactory().openSession();
						sessionmdfs2.beginTransaction();
						sessionmdfs2.save(mdfs);
						sessionmdfs2.getTransaction().commit();
						sessionmdfs2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}		
		/////////////////////////// end save CUE_TB_MISO_PROVISION_TRANSPORT_MDFS 
		
		/////////////////////////// start save CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES 		
		CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES foot=new CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES();
		if(oldproid.size() > 0)
		{
			for(int f=0; f < oldproid.size(); f++) {
				Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
				Transaction txfoot = sessionfoot.beginTransaction();
				Query qwfoot = sessionfoot.createQuery("from CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES where provision_id=:provision_id");		
				qwfoot.setParameter("provision_id", oldproid.get(f));	
				List<CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES> listfoot = (List<CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES>) qwfoot.list();
				txfoot.commit();
				sessionfoot.close();
				if(listfoot.size() > 0) 
				{
					foot.setProvision_id(newproid.get(f));
					for(int f1=0; f1<listfoot.size(); f1++) {						
						foot.setWe_pe_no(listfoot.get(f1).getWe_pe_no());
						foot.setCreated_by(username);
						foot.setCreated_on(creadtedate);
						foot.setModified_by(listfoot.get(f1).getModified_by());
						foot.setModified_on(listfoot.get(f1).getModified_on());						
						foot.setCondition(listfoot.get(f1).getCondition());
						foot.setMct_no(listfoot.get(f1).getMct_no());
						foot.setNo_of_units(listfoot.get(f1).getNo_of_units());
						foot.setStatus(listfoot.get(f1).getStatus());
						foot.setAmt_inc_dec(listfoot.get(f1).getAmt_inc_dec());
						foot.setFoot_id(listfoot.get(f1).getFoot_id());
						foot.setScenario(listfoot.get(f1).getScenario());
						foot.setLoc_for_unit(listfoot.get(f1).getLoc_for_unit());
						
						Session sessionfoot2 = HibernateUtil.getSessionFactory().openSession();
						sessionfoot2.beginTransaction();
						sessionfoot2.save(foot);
						sessionfoot2.getTransaction().commit();
						sessionfoot2.close();
						model.put("msg", "Data saved Successfully");
					}
				}
			}
		}			
		/////////////////////////// end save CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES 
		
		return new ModelAndView("redirect:provisionMstTransUrl");
	}
}
