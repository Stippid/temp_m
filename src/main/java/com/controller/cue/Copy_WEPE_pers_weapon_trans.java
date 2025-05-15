package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.CopyWE_PE_DAO;
import com.dao.cue.Copy_WE_PE_DAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Copy_WEPE_pers_weapon_trans {
	
	CopyWE_PE_DAO copyWePeDAO = new Copy_WE_PE_DAOImpl();
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	private cueContoller M = new cueContoller();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/getWEPENODetailsList", method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getWEPENODetailsList(String we_pe_no) {		
		return copyWePeDAO.getWEPENODetailsList(we_pe_no);
	}
	
	@RequestMapping(value = "/copy_we_pe_trans", method = RequestMethod.GET)
		public ModelAndView search_we_pe_trans(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("copy_we_pe_trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("copy_we_pe_transTiles");
	}
	
	@RequestMapping(value = "/copy_we_pe_weap", method = RequestMethod.GET)
		public ModelAndView search_we_pe_weap(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("copy_we_pe_weap", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("copy_we_pe_weapTiles");
	}
	
	@RequestMapping(value = "/copy_we_pe_pers", method = RequestMethod.GET)
		public ModelAndView search_we_pe_pers(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("copy_we_pe_pers", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		Mmap.put("msg", msg);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("copy_we_pe_persTiles");
	}
	
	/////////////////////////////-------COPY WE/PE Transport----------/////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/copy_we_pe_transAction", method=RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("copy_we_pe_transCMD") CUE_TB_MISO_WEPECONDITIONS cp_we_pe,			 
	HttpServletRequest request,ModelMap model,HttpSession session) {
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		cp_we_pe.setCreated_by(username);
		cp_we_pe.setCreated_on(date);
		cp_we_pe.setStatus("1");
		
		int roleid = (Integer)session.getAttribute("roleid");	
		

		String we_pe = request.getParameter("we_pe");
		String we_pe_no1 = request.getParameter("we_pe_no");
		
		
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:copy_we_pe_trans");
		}
		 String we_pe_no_from = request.getParameter("we_pe_no_from");
		 if(we_pe_no_from == "")
		{
			model.put("msg", "Please Enter Existing WE/PE No");
			return new ModelAndView("redirect:copy_we_pe_trans");
		}
		if(validation.checkWepeLength(we_pe_no_from)  == false){
		 	model.put("msg",validation.exwepenoMSG);
			return new ModelAndView("redirect:copy_we_pe_trans");
		}
		 if(cp_we_pe.getWe_pe_no() == "")
			{
				model.put("msg", "Please Enter New WE/PE No");
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
		 if(validation.checkWepeLength(we_pe_no1)  == false){
		 		model.put("msg",validation.newwepenoMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
			if(validation.checkWepeLength(cp_we_pe.getUploaded_wepe())  == false){
		 		model.put("msg",validation.upwepenoMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
			if(validation.checkWepetabletittleLength(cp_we_pe.getTable_title())  == false){
		 		model.put("msg",validation.wepetitleMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}	
			if(validation.checkModificationLength(cp_we_pe.getDoc_type())  == false){
		 		model.put("msg",validation.secclassMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
			if(validation.checkSponsorDireLength(cp_we_pe.getSponsor_dire())  == false){
		 		model.put("msg",validation.spodireMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
			String arm_code = String.format("%04d", Integer.parseInt(cp_we_pe.getArm()));
			if(validation.checkArmCodeLength(arm_code)  == false){
		 		model.put("msg",validation.arm_codeMSG);
				return new ModelAndView("redirect:copy_we_pe_trans");
			}
				cp_we_pe.setRoleid(157);
		cp_we_pe.setEff_frm_date(request.getParameter("eff_frm_date"));
		cp_we_pe.setEff_to_date(request.getParameter("eff_to_date"));
		cp_we_pe.setWe_pe(we_pe);
		cp_we_pe.setArm(request.getParameter("arm"));
		cp_we_pe.setSuprcdd_we_pe_no(request.getParameter("suprcdd_we_pe_no"));
		cp_we_pe.setTable_title(request.getParameter("table_title"));
		cp_we_pe.setWe_pe_no(we_pe_no1);
		cp_we_pe.setDoc_type(request.getParameter("doc_type"));
		cp_we_pe.setSponsor_dire(request.getParameter("sponsor_dire"));
		cp_we_pe.setUploaded_wepe(request.getParameter("uploaded_wepe"));
		cp_we_pe.setLetter_date_copy(request.getParameter("letter_date"));
		cp_we_pe.setType("2"); // for TRANSPORT value is 2
	
			cp_we_pe.setTraining_capacity(0);
			
			Session sessionw = HibernateUtil.getSessionFactory().openSession();
			Transaction txw = sessionw.beginTransaction();
			Query qw = sessionw.createQuery("select count(*) from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no ");
			qw.setParameter("we_pe_no", we_pe_no1);	
			Long count_we_pe_now = (Long)qw.uniqueResult();			
			txw.commit();
			sessionw.close();
				
		if(count_we_pe_now==0) {
		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		sessionHQL1.beginTransaction();
		sessionHQL1.save(cp_we_pe);
		sessionHQL1.getTransaction().commit();
		sessionHQL1.close();		
		
		String we_pe_no_old = request.getParameter("we_pe_no_from");		
		

		///---------------- TRANSPORT DETAILS -----------------------//////////////////////		
		CUE_TB_MISO_WEPE_TRANSPORT_DET transdtmodel = new CUE_TB_MISO_WEPE_TRANSPORT_DET();
		
		List<CUE_TB_MISO_WEPE_TRANSPORT_DET> listMod = copyWePeDAO.getWEPENODetailsListFromTransDet(we_pe_no_old);
		if(listMod.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {
			
			String we_pe_no =  request.getParameter("we_pe_no");			
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_TRANSPORT_DET> list1 = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q2.list();
			tx2.commit();
			session2.close();
					
				for(int i=0; i<count_we_pe_no; i++) {
					
					String mct_no = listMod.get(i).getMct_no();
					
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_DET where mct_no=:mct_no and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("mct_no", mct_no);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						transdtmodel.setCreated_by(listMod.get(i).getCreated_by());
						transdtmodel.setCreated_on(listMod.get(i).getCreated_on());
						transdtmodel.setEntity(listMod.get(i).getEntity());
						//transdtmodel.setStatus(listMod.get(i).getStatus());	
						transdtmodel.setStatus("0");
						transdtmodel.setWe_pe_no(we_pe_no);
						transdtmodel.setMct_no(listMod.get(i).getMct_no());
						transdtmodel.setRemarks(listMod.get(i).getRemarks());
						transdtmodel.setAuth_amt(listMod.get(i).getAuth_amt());
						transdtmodel.setAprv_rejc_by(listMod.get(i).getAprv_rejc_by());
						transdtmodel.setCes_cces(listMod.get(i).getCes_cces());
						transdtmodel.setDate_of_apprv_rejc(listMod.get(i).getDate_of_apprv_rejc());
						transdtmodel.setFootnote_no(listMod.get(i).getFootnote_no());
						transdtmodel.setModified_by(listMod.get(i).getModified_by());
						transdtmodel.setModified_on(listMod.get(i).getModified_on());
						transdtmodel.setVersion_no(listMod.get(i).getVersion_no());
						transdtmodel.setRoleid(roleid);						
								
						Session session02 = HibernateUtil.getSessionFactory().openSession();
						session02.beginTransaction();
						session02.save(transdtmodel);
						model.put("msg", "Data saved Successfully");
						session02.getTransaction().commit();
						session02.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
					
		}
		///---------------- END TRANSPORT DETAILS -----------------------//////////////////////	
		
		///---------------- TRANSPORT FOOTNOTE -----------------------//////////////////////		
		CUE_TB_MISO_WEPE_TRANS_FOOTNOTES ftmodel = new CUE_TB_MISO_WEPE_TRANS_FOOTNOTES();
		List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> listFoot = copyWePeDAO.getWEPENODetailsListFromTransFootnote(we_pe_no_old);
		if(listFoot.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> list1 = (List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES>) q2.list();
			tx2.commit();
			session2.close();
			

				for(int i=0; i<count_we_pe_no; i++) {				
	
					String mct_no = listFoot.get(i).getMct_no();
					String condition = listFoot.get(i).getCondition();
									
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where mct_no=:mct_no and we_pe_no=:we_pe_no and condition=:condition");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("mct_no", mct_no);
					q1.setParameter("condition", condition);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						ftmodel.setAmt_inc_dec(listFoot.get(i).getAmt_inc_dec());
						ftmodel.setWe_pe_no(we_pe_no);
						ftmodel.setCondition(listFoot.get(i).getCondition());
						ftmodel.setCreated_by(listFoot.get(i).getCreated_by());
						ftmodel.setCreated_on(listFoot.get(i).getCreated_on());
						ftmodel.setModified_by(listFoot.get(i).getModified_by());
						ftmodel.setModified_on(listFoot.get(i).getModified_on());						
						ftmodel.setStatus("0");
						ftmodel.setType_of_footnote("1");
						ftmodel.setVersion_no(listFoot.get(i).getVersion_no());
						ftmodel.setIn_lieu_mct(listFoot.get(i).getIn_lieu_mct());
						ftmodel.setMct_no(listFoot.get(i).getMct_no());
						ftmodel.setRemarks(listFoot.get(i).getRemarks());
						ftmodel.setFormation(listFoot.get(i).getFormation());
						ftmodel.setLocation(listFoot.get(i).getLocation());
						ftmodel.setScenario(listFoot.get(i).getScenario());
						ftmodel.setActual_inlieu_auth(listFoot.get(i).getActual_inlieu_auth());
						ftmodel.setSoftdelete(listFoot.get(i).getSoftdelete());
						ftmodel.setAprv_rejc_by(listFoot.get(i).getAprv_rejc_by());
						ftmodel.setDate_of_apprv_rejc(listFoot.get(i).getDate_of_apprv_rejc());
						ftmodel.setType_of_footnote(listFoot.get(i).getType_of_footnote());
						ftmodel.setRoleid(roleid);
						ftmodel.setScenario_unit(listFoot.get(i).getScenario_unit());
								
						Session session1 = HibernateUtil.getSessionFactory().openSession();
						session1.beginTransaction();
						session1.save(ftmodel);
						model.put("msg", "Data saved Successfully");
						session1.getTransaction().commit();
						session1.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}	
				}
		}
		///---------------- END TRANSPORT FOOTNOTE -----------------------//////////////////////		
		
		//////----------------------------- TRANSPORT MODIFICATIONS ----------------------------/////////////////////////////		
		CUE_TB_MISO_WEPE_TRANSPORT_MDFS transmdfsmodel = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
		List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> listTransMdfs = copyWePeDAO.getWEPENODetailsListFromTransMDFS(we_pe_no_old);
		if(listTransMdfs.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {		
			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> list1 = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q2.list();
			tx2.commit();
			session2.close();
			
			
//			if(count_we_pe_no != 0) {
				for(int i=0; i<count_we_pe_no; i++) {
					
					String mct_no = listTransMdfs.get(i).getMct_no();
					String modification = listTransMdfs.get(i).getModification();
					
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where mct_no=:mct_no and we_pe_no=:we_pe_no and modification=:modification");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("mct_no", mct_no);
					q1.setParameter("modification", modification);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						transmdfsmodel.setAprv_rejc_by(listTransMdfs.get(i).getAprv_rejc_by());
						transmdfsmodel.setCreated_by(listTransMdfs.get(i).getCreated_by());
						transmdfsmodel.setCreated_on(listTransMdfs.get(i).getCreated_on());
						transmdfsmodel.setDate_of_apprv_rejc(listTransMdfs.get(i).getDate_of_apprv_rejc());
						transmdfsmodel.setModification(listTransMdfs.get(i).getModification());
						transmdfsmodel.setModified_by(listTransMdfs.get(i).getModified_by());
						transmdfsmodel.setModified_on(listTransMdfs.get(i).getModified_on());
						transmdfsmodel.setRemarks(listTransMdfs.get(i).getRemarks());						
						transmdfsmodel.setStatus("0");
						transmdfsmodel.setWe_pe_no(we_pe_no);
						transmdfsmodel.setVersion_no(listTransMdfs.get(i).getVersion_no());
						transmdfsmodel.setMct_no(listTransMdfs.get(i).getMct_no());
						transmdfsmodel.setFormation(listTransMdfs.get(i).getFormation());
						transmdfsmodel.setLocation(listTransMdfs.get(i).getLocation());
						transmdfsmodel.setScenario(listTransMdfs.get(i).getScenario());
						transmdfsmodel.setAmt_inc_dec(listTransMdfs.get(i).getAmt_inc_dec());
						transmdfsmodel.setRoleid(roleid);
						transmdfsmodel.setScenario_unit(listTransMdfs.get(i).getScenario_unit());
						
						Session session3 = HibernateUtil.getSessionFactory().openSession();
						session3.beginTransaction();
						session3.save(transmdfsmodel);
						model.put("msg", "Data saved Successfully");
						session3.getTransaction().commit();
						session3.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
		
		}
		//////----------------------------- END TRANSPORT MODIFICATIONS ----------------------------/////////////////////////////		
		model.put("msg", "Data saved Successfully");
		}
		else
		{
			model.put("msg", "WE/PE No already Exist!");
		}
	
		return new ModelAndView("redirect:copy_we_pe_trans");			
	}
	/////////////////////////////// end COPY WE/PE Transport----------------------------/////////////////////////////
	
	//////----------------------------- PERSONNEL--------------------///////////////////	
	@RequestMapping(value = "/copy_we_pe_persAction", method=RequestMethod.POST) 
	public ModelAndView addItemEntryForm1(@ModelAttribute("copy_we_pe_persCMD") CUE_TB_MISO_WEPECONDITIONS cp_we_pe,			 
	HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		cp_we_pe.setCreated_by(username);
		cp_we_pe.setCreated_on(date);
		cp_we_pe.setStatus("1");
		
		int roleid = (Integer)session.getAttribute("roleid");			
		
		String we_pe = request.getParameter("we_pe");
		String we_pe_no1 = request.getParameter("we_pe_no");
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx0 = sessionHQL.beginTransaction();
			
			try
			{
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:copy_we_pe_pers");
		}
		 String we_pe_no_from = request.getParameter("we_pe_no_from");
		 if(we_pe_no_from == "")
			{
				model.put("msg", "Please Enter Existing WE/PE No");
				return new ModelAndView("redirect:copy_we_pe_pers");
			}
		 if(cp_we_pe.getWe_pe_no() == "")
			{
				model.put("msg", "Please Enter New WE/PE No");
				return new ModelAndView("redirect:copy_we_pe_pers");
			}
		 if(validation.checkWepeLength(cp_we_pe.getWe_pe_no())  == false || validation.checkWepeLength(cp_we_pe.getUploaded_wepe())  == false
				 || validation.checkWepeLength(cp_we_pe.getSuprcdd_we_pe_no())  == false){
				model.put("msg",validation.wepenoMSG);
				return new ModelAndView("redirect:copy_we_pe_pers");
			}
		 if(validation.checkWepetabletittleLength(cp_we_pe.getTable_title())  == false ){
				model.put("msg",validation.tbltitileMSG);
				return new ModelAndView("redirect:copy_we_pe_pers");
			}
			
		String arm_codevalid = String.format("%04d", Integer.parseInt(cp_we_pe.getArm()));
		if(validation.checkArmCodeLength(arm_codevalid)  == false){
		 	model.put("msg",validation.arm_codeMSG);
			return new ModelAndView("redirect:copy_we_pe_pers");
		}
		 if(validation.checkSponsorDireLength(cp_we_pe.getSponsor_dire())  == false ){
				model.put("msg",validation.spodireMSG);
				return new ModelAndView("redirect:copy_we_pe_pers");
			}
		String training_capacity =  Double.toString(cp_we_pe.getTraining_capacity());
		if(validation.checkAuth_amtLength(training_capacity)  == false){
			model.put("msg",validation.auth_amtMSG);
			return new ModelAndView("redirect:copy_we_pe_pers");
		}
		 if(validation.checkLocationLength(cp_we_pe.getUnit_category())  == false ){
			model.put("msg",validation.unitcatMSG);
			return new ModelAndView("redirect:copy_we_pe_pers");
		}
		if(validation.checkRemarksLength(cp_we_pe.getRemarks())  == false)
		{
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:copy_we_pe_pers");
		}
		cp_we_pe.setRoleid(155);
		cp_we_pe.setEff_frm_date(request.getParameter("eff_frm_date"));
		cp_we_pe.setEff_to_date(request.getParameter("eff_to_date"));
		cp_we_pe.setWe_pe(we_pe);
		cp_we_pe.setArm(request.getParameter("arm"));
		cp_we_pe.setSuprcdd_we_pe_no(request.getParameter("suprcdd_we_pe_no"));
		cp_we_pe.setTable_title(request.getParameter("table_title"));
		cp_we_pe.setWe_pe_no(we_pe_no1);
		cp_we_pe.setDoc_type(request.getParameter("doc_type"));
		cp_we_pe.setSponsor_dire(request.getParameter("sponsor_dire"));
		cp_we_pe.setUnit_category(request.getParameter("unit_category"));
		cp_we_pe.setUploaded_wepe(request.getParameter("uploaded_wepe"));
		cp_we_pe.setLetter_date_copy(request.getParameter("letter_date"));
		cp_we_pe.setType("1"); // for PERSONNEL value is 1
		int tr_cap=0;
		if(request.getParameter("training_capacity") != "")
			cp_we_pe.setTraining_capacity(Integer.parseInt(request.getParameter("training_capacity")));
		else
			cp_we_pe.setTraining_capacity(tr_cap);		
		
		Session sessionw = HibernateUtil.getSessionFactory().openSession();
		Transaction txw = sessionw.beginTransaction();
		Query qw = sessionw.createQuery("select count(*) from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no ");
		qw.setParameter("we_pe_no", we_pe_no1);	
		Long count_we_pe_now = (Long)qw.uniqueResult();			
		txw.commit();
		sessionw.close();
			
	if(count_we_pe_now==0) {
		Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
		sessionHQL2.beginTransaction();
		sessionHQL2.save(cp_we_pe);
		sessionHQL2.getTransaction().commit();
		sessionHQL2.close();		
		String we_pe_no_old = request.getParameter("we_pe_no_from");		
		///---------------- PERSONNEL DETAILS -----------------------//////////////////////
		
		CUE_TB_MISO_WEPE_PERS_DET prsdtmodel = new CUE_TB_MISO_WEPE_PERS_DET();
		List<CUE_TB_MISO_WEPE_PERS_DET> listprsdt = copyWePeDAO.getWEPENODetailsListFromPersDet(we_pe_no_old);
		if(listprsdt.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {			
			String we_pe_no =  request.getParameter("we_pe_no");
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_DET where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_PERS_DET> list1 = (List<CUE_TB_MISO_WEPE_PERS_DET>) q2.list();
			tx2.commit();
			session2.close();
			

				for(int i=0; i<count_we_pe_no; i++) {					
					String app_trd_code = listprsdt.get(i).getApp_trd_code();
					String category_of_persn = listprsdt.get(i).getCategory_of_persn();
					String rank = listprsdt.get(i).getRank();
					String rank_cat = listprsdt.get(i).getRank_cat();
					String arm_code = listprsdt.get(i).getArm_code();
											
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_DET where app_trd_code=:app_trd_code and category_of_persn=:category_of_persn and rank=:rank and rank_cat=:rank_cat and arm_code=:arm_code and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("app_trd_code", app_trd_code);
					q1.setParameter("category_of_persn", category_of_persn);
					q1.setParameter("rank", rank);
					q1.setParameter("rank_cat", rank_cat);
					q1.setParameter("arm_code", arm_code);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						prsdtmodel.setAppt_trade(listprsdt.get(i).getAppt_trade());
						prsdtmodel.setApp_trd_code(app_trd_code);
						prsdtmodel.setArm_code(listprsdt.get(i).getArm_code());
						prsdtmodel.setAuth_amt(listprsdt.get(i).getAuth_amt());
						prsdtmodel.setCategory_of_persn(listprsdt.get(i).getCategory_of_persn());
						prsdtmodel.setCreated_by(listprsdt.get(i).getCreated_by());
						prsdtmodel.setCreated_on(listprsdt.get(i).getCreated_on());
						prsdtmodel.setRank(listprsdt.get(i).getRank());
						prsdtmodel.setRank_cat(listprsdt.get(i).getRank_cat());
						prsdtmodel.setStatus("0");
						prsdtmodel.setWe_pe_no(we_pe_no);
						prsdtmodel.setRoleid(roleid);
						
						Session session5 = HibernateUtil.getSessionFactory().openSession();
						session5.beginTransaction();
						session5.save(prsdtmodel);
						model.put("msg", "Data saved Successfully");
						session5.getTransaction().commit();
						session5.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
	
		}
		///---------------- END PERSONNEL DETAILS -----------------------//////////////////////
		
	//////----------------------------- PERSONNEL MODIFICATIONS ----------------------------/////////////////////////////		
			CUE_TB_MISO_WEPE_PERS_MDFS prsmdfsmodel = new CUE_TB_MISO_WEPE_PERS_MDFS();
			List<CUE_TB_MISO_WEPE_PERS_MDFS> listprsMdfs = copyWePeDAO.getWEPENODetailsListFromPersMDFS(we_pe_no_old);
			if(listprsMdfs.isEmpty())
			{
				model.put("msg", "WE/PE No does not exist!");
			}
			else {		
						
				String we_pe_no =  request.getParameter("we_pe_no");
				
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_MDFS where we_pe_no=:we_pe_no ");
				q2.setParameter("we_pe_no", we_pe_no_old);	
				Long count_we_pe_no = (Long)q2.uniqueResult();
				@SuppressWarnings("unchecked")
				List<CUE_TB_MISO_WEPE_PERS_MDFS> list1 = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) q2.list();
				tx2.commit();
				session2.close();
				
					for(int i=0; i<count_we_pe_no; i++) {
						
						String appt_trade = listprsMdfs.get(i).getAppt_trade();
						String cat_per = listprsMdfs.get(i).getCat_per();
						String rank = listprsMdfs.get(i).getRank();
						String rank_cat = listprsMdfs.get(i).getRank_cat();
						String arm_code = listprsMdfs.get(i).getArm_code();
						String modification = listprsMdfs.get(i).getModification();
						
						Query q1 = sessionHQL.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_MDFS where appt_trade=:appt_trade and cat_per=:cat_per and rank=:rank and rank_cat=:rank_cat and arm_code=:arm_code and we_pe_no=:we_pe_no and modification=:modification");
						q1.setParameter("we_pe_no", we_pe_no);
						q1.setParameter("appt_trade", appt_trade);
						q1.setParameter("cat_per", cat_per);
						q1.setParameter("rank", rank);
						q1.setParameter("rank_cat", rank_cat);
						q1.setParameter("arm_code", arm_code);
						q1.setParameter("modification", modification);
						Long count1 = (Long)q1.uniqueResult();
						@SuppressWarnings("unchecked")
						List<Integer> list = (List<Integer>) q1.list();
						
						if(count1 == 0) {
							double amt_inc_dec = new Double(listprsMdfs.get(i).getAmt_inc_dec());
							
							prsmdfsmodel.setAmt_inc_dec(amt_inc_dec);
							prsmdfsmodel.setAppt_trade(listprsMdfs.get(i).getAppt_trade());
							prsmdfsmodel.setArm_code(listprsMdfs.get(i).getArm_code());
							prsmdfsmodel.setCat_per(listprsMdfs.get(i).getCat_per());
							prsmdfsmodel.setCreated_by(listprsMdfs.get(i).getCreated_by());
							prsmdfsmodel.setCreated_on(listprsMdfs.get(i).getCreated_on());
							prsmdfsmodel.setModification(listprsMdfs.get(i).getModification());
							prsmdfsmodel.setRank(listprsMdfs.get(i).getRank());
							prsmdfsmodel.setRank_cat(listprsMdfs.get(i).getRank_cat());
							prsmdfsmodel.setRemarks(listprsMdfs.get(i).getRemarks());
							prsmdfsmodel.setStatus("0");
							prsmdfsmodel.setWe_pe_no(we_pe_no);
							prsmdfsmodel.setFormation(listprsMdfs.get(i).getFormation());
							prsmdfsmodel.setLocation(listprsMdfs.get(i).getLocation());
							prsmdfsmodel.setScenario(listprsMdfs.get(i).getScenario());
							prsmdfsmodel.setScenario_unit(listprsMdfs.get(i).getScenario_unit());
							prsmdfsmodel.setRoleid(roleid);							
							
							Session session4 = HibernateUtil.getSessionFactory().openSession();
							session4.beginTransaction();
							session4.save(prsmdfsmodel);
							model.put("msg", "Data saved Successfully");
							session4.getTransaction().commit();
							session4.close();
						}
						else {
							model.put("msg", "Data already Exist!");
						}
					}
						
			}
			//////-----------------------------END PERSONNEL MODIFICATIONS ----------------------------/////////////////////////////	
		
		///---------------- PERSONNEL FOOTNOTE -----------------------//////////////////////		
		CUE_TB_MISO_WEPE_PERS_FOOTNOTES prsftmodel = new CUE_TB_MISO_WEPE_PERS_FOOTNOTES();		
		List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> listPrsFt = copyWePeDAO.getWEPENODetailsListFromPersFootnote(we_pe_no_old);
		if(listPrsFt.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {
			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> list1 = (List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES>) q2.list();
			tx2.commit();
			session2.close();
			
				for(int i=0; i<count_we_pe_no; i++) {
					
					String appt_trade = listPrsFt.get(i).getAppt_trade();
					String category_of_personnel = listPrsFt.get(i).getCategory_of_personnel();
					String rank = listPrsFt.get(i).getRank();
					String rank_cat = listPrsFt.get(i).getRank_cat();
					String arm_code = listPrsFt.get(i).getArm_code();
								
					Query q1 = sessionHQL.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where appt_trade=:appt_trade and category_of_personnel=:category_of_personnel and rank=:rank and rank_cat=:rank_cat and arm_code=:arm_code and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("appt_trade", appt_trade);
					q1.setParameter("category_of_personnel", category_of_personnel);
					q1.setParameter("rank", rank);
					q1.setParameter("rank_cat", rank_cat);
					q1.setParameter("arm_code", arm_code);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					
					if(count1 == 0) {
						prsftmodel.setAmt_inc_dec(listPrsFt.get(i).getAmt_inc_dec());
						prsftmodel.setAppt_trade(listPrsFt.get(i).getAppt_trade());
						prsftmodel.setArm_code(listPrsFt.get(i).getArm_code());
						prsftmodel.setCategory_of_personnel(listPrsFt.get(i).getCategory_of_personnel());
						prsftmodel.setCondition(listPrsFt.get(i).getCondition());
						prsftmodel.setCreated_by(listPrsFt.get(i).getCreated_by());
						prsftmodel.setCreated_on(listPrsFt.get(i).getCreated_on());
						prsftmodel.setRank(listPrsFt.get(i).getRank());
						prsftmodel.setRank_cat(listPrsFt.get(i).getRank_cat());
						prsftmodel.setStatus("0");
						prsftmodel.setWe_pe_no(we_pe_no);
						prsftmodel.setLocation(listPrsFt.get(i).getLocation());
						prsftmodel.setFormation(listPrsFt.get(i).getFormation());
						prsftmodel.setScenario(listPrsFt.get(i).getScenario());
						prsftmodel.setScenario_unit(listPrsFt.get(i).getScenario_unit());
						prsftmodel.setRoleid(roleid);
						
						Session session6 = HibernateUtil.getSessionFactory().openSession();
						session6.beginTransaction();
						session6.save(prsftmodel);
						model.put("msg", "Data saved Successfully");
						session6.getTransaction().commit();
						session6.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
		
		}
		///---------------- END PERSONNEL FOOTNOTE -----------------------//////////////////////
		
		model.put("msg", "Your data saved Successfully");
	}
	else
	{
		model.put("msg", "WE/PE No already Exist!");
	}
	}	
	catch (Exception e) {
		sessionHQL.getTransaction().rollback();
		//return null;
	}
	finally{
		
		tx0.commit();
		sessionHQL.close();
	}	
	return new ModelAndView("redirect:copy_we_pe_pers");
	}	
	///---------------- END PERSONNEL -----------------------//////////////////////
	
	/////-------------------------- WEAPON-----------------------------///////////////////////////////////
	
	@RequestMapping(value = "/copy_we_pe_weapAction", method=RequestMethod.POST)
	public ModelAndView addItemEntryForm2(@ModelAttribute("copy_we_pe_weapCMD") CUE_TB_MISO_WEPECONDITIONS cp_we_pe,			 
	HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		cp_we_pe.setCreated_by(username);
		cp_we_pe.setCreated_on(date);
		cp_we_pe.setStatus("1");
		
		int roleid = (Integer)session.getAttribute("roleid");			
		cp_we_pe.setRoleid(159);	
		 
			String we_pe = request.getParameter("we_pe");
			String we_pe_no1 = request.getParameter("we_pe_no");
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = sessionHQL.beginTransaction();
			
			try
			{
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
			{
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:copy_we_pe_weap");
			}
		 if(validation.checkWETypeLength(we_pe)  == false){
             model.put("msg",validation.wepenoMSG);
             return new ModelAndView("redirect:copy_we_pe_weap");
         }
		 String we_pe_no_from = request.getParameter("we_pe_no_from");
		 if(we_pe_no_from == "")
			{
				model.put("msg", "Please Enter Existing WE/PE No");
				return new ModelAndView("redirect:copy_we_pe_weap");
			}
			 if(validation.checkWepeLength(we_pe_no_from)  == false){
	             model.put("msg",validation.exwepenoMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(cp_we_pe.getWe_pe_no() == "")
				{
					model.put("msg", "Please Enter New WE/PE No");
					return new ModelAndView("redirect:copy_we_pe_weap");
				}
			 if(validation.checkWepeLength(cp_we_pe.getWe_pe_no())  == false){
	             model.put("msg",validation.newwepenoMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkWepeLength(cp_we_pe.getUploaded_wepe())  == false){
	             model.put("msg",validation.upwepenoMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkWepeLength(cp_we_pe.getSuprcdd_we_pe_no())  == false){
	             model.put("msg",validation.spodocnoMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkWepetabletittleLength(cp_we_pe.getTable_title())  == false){
			 		model.put("msg",validation.wepetitleMSG);
					return new ModelAndView("redirect:copy_we_pe_trans");
				}
			 if(validation.checkDateLength(request.getParameter("letter_date"))  == false){
	             model.put("msg",validation.dateMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkDateLength(cp_we_pe.getEff_frm_date())  == false){
	             model.put("msg",validation.dateMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkDateLength(cp_we_pe.getEff_to_date())  == false){
	             model.put("msg",validation.dateMSG);
	             return new ModelAndView("redirect:copy_we_pe_weap");
	         }
			 if(validation.checkModificationLength(cp_we_pe.getDoc_type())  == false){
			 		model.put("msg",validation.secclassMSG);
					return new ModelAndView("redirect:copy_we_pe_weap");
				}
				if(validation.checkSponsorDireLength(cp_we_pe.getSponsor_dire())  == false){
			 		model.put("msg",validation.spodireMSG);
					return new ModelAndView("redirect:copy_we_pe_weap");
				}
				String arm_code = String.format("%04d", Integer.parseInt(cp_we_pe.getArm()));
				if(validation.checkArmCodeLength(arm_code)  == false){
			 		model.put("msg",validation.arm_codeMSG);
					return new ModelAndView("redirect:copy_we_pe_trans");
				}
		cp_we_pe.setEff_frm_date(request.getParameter("eff_frm_date"));
		cp_we_pe.setEff_to_date(request.getParameter("eff_to_date"));
		cp_we_pe.setWe_pe(we_pe);
		cp_we_pe.setArm(request.getParameter("arm"));
		cp_we_pe.setSuprcdd_we_pe_no(request.getParameter("suprcdd_we_pe_no"));
		cp_we_pe.setTable_title(request.getParameter("table_title"));
		cp_we_pe.setWe_pe_no(we_pe_no1);
		cp_we_pe.setDoc_type(request.getParameter("doc_type"));
		cp_we_pe.setSponsor_dire(request.getParameter("sponsor_dire"));
		cp_we_pe.setUploaded_wepe(request.getParameter("uploaded_wepe"));
		cp_we_pe.setLetter_date_copy(request.getParameter("letter_date"));
		cp_we_pe.setType("3"); // for WEAPON value is 3
		cp_we_pe.setTraining_capacity(0);		
		
			Session sessionw = HibernateUtil.getSessionFactory().openSession();
			Transaction txw = sessionw.beginTransaction();
			Query qw = sessionw.createQuery("select count(*) from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no ");
			qw.setParameter("we_pe_no", we_pe_no1);	
			Long count_we_pe_now = (Long)qw.uniqueResult();			
			txw.commit();
			sessionw.close();
				
		if(count_we_pe_now==0) {
		Session sessionHQL3 = HibernateUtil.getSessionFactory().openSession();
		sessionHQL3.beginTransaction();
		sessionHQL3.save(cp_we_pe);
		sessionHQL3.getTransaction().commit();
		sessionHQL3.close();
		
		String we_pe_no_old = request.getParameter("we_pe_no_from");		

		/////-------------------------- WEAPON DETAILS -----------------------------///////////////////////////////////
		
		CUE_TB_MISO_WEPE_WEAPON_DET weapDtModel = new CUE_TB_MISO_WEPE_WEAPON_DET();
		List<CUE_TB_MISO_WEPE_WEAPON_DET> listWeapDt = copyWePeDAO.getWEPENODetailsListFromWeapDet(we_pe_no_old);
		
		if(listWeapDt.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {
			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_WEAPON_DET> list1 = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) q2.list();
			tx2.commit();
			session2.close();
			
				for(int i=0; i<count_we_pe_no; i++) {
					
					String item_seq_no = listWeapDt.get(i).getItem_seq_no();
					
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_DET where item_seq_no=:item_seq_no and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("item_seq_no", item_seq_no);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0){
						double auth_weapon = new Double(listWeapDt.get(i).getAuth_weapon());
						weapDtModel.setAuth_weapon(auth_weapon);
						weapDtModel.setCreated_by(listWeapDt.get(i).getCreated_by());
						weapDtModel.setCreated_on(listWeapDt.get(i).getCreated_on());
						weapDtModel.setRemarks(listWeapDt.get(i).getRemarks());
						weapDtModel.setStatus("0");
						weapDtModel.setWe_pe_no(we_pe_no);
						weapDtModel.setItem_seq_no(listWeapDt.get(i).getItem_seq_no());
						weapDtModel.setRoleid(roleid);						
						
						Session session7 = HibernateUtil.getSessionFactory().openSession();
						session7.beginTransaction();
						session7.save(weapDtModel);
						model.put("msg", "Data saved Successfully");
						session7.getTransaction().commit();
						session7.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
		}
		
		/////-------------------------- END WEAPON DETAILS -----------------------------///////////////////////////////////		
		
		/////-------------------------- WEAPON MODIFICATIOS -----------------------------///////////////////////////////////
		
		CUE_TB_MISO_WEPE_WEAPONS_MDFS weapMdfsModel = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> listWeapMdfs = copyWePeDAO.getWEPENODetailsListFromWeapMDFS(we_pe_no_old);
		if(listWeapMdfs.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {
			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> list1 = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) q2.list();
			tx2.commit();
			session2.close();
			
				for(int i=0; i<count_we_pe_no; i++) {
					String item_seq_no = listWeapMdfs.get(i).getItem_seq_no();
					String modification = listWeapMdfs.get(i).getModification();
					
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPONS_MDFS where item_seq_no=:item_seq_no and modification=:modification and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("item_seq_no", item_seq_no);
					q1.setParameter("modification", modification);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						weapMdfsModel.setAmt_inc_dec(listWeapMdfs.get(i).getAmt_inc_dec());
						weapMdfsModel.setModification(listWeapMdfs.get(i).getModification());
						weapMdfsModel.setStatus("0");
						weapMdfsModel.setCreated_by(listWeapMdfs.get(i).getCreated_by());
						weapMdfsModel.setCreated_on(listWeapMdfs.get(i).getCreated_on());
						weapMdfsModel.setWe_pe_no(we_pe_no);
						weapMdfsModel.setItem_seq_no(listWeapMdfs.get(i).getItem_seq_no());
						weapMdfsModel.setLocation(listWeapMdfs.get(i).getLocation());
						weapMdfsModel.setFormation(listWeapMdfs.get(i).getFormation());
						weapMdfsModel.setRemarks(listWeapMdfs.get(i).getRemarks());
						weapMdfsModel.setScenario(listWeapMdfs.get(i).getScenario());
						weapMdfsModel.setScenario_unit(listWeapMdfs.get(i).getScenario_unit());
						weapMdfsModel.setRoleid(roleid);						
						
						Session session8 = HibernateUtil.getSessionFactory().openSession();
						session8.beginTransaction();
						session8.save(weapMdfsModel);
						model.put("msg", "Data saved Successfully");
						session8.getTransaction().commit();
						session8.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
		
		}
		/////-------------------------- END WEAPON MODIFICATIOS -----------------------------///////////////////////////////////
		
		/////-------------------------- WEAPON FOOTNOTE -----------------------------///////////////////////////////////
		
		CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES weapFtModel = new CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES();
		List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> listWeapFt = copyWePeDAO.getWEPENODetailsListFromWeapFootnote(we_pe_no_old);
		if(listWeapFt.isEmpty())
		{
			model.put("msg", "WE/PE No does not exist!");
		}
		else {
			
			String we_pe_no =  request.getParameter("we_pe_no");
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx2 = session2.beginTransaction();
			Query q2 = session2.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where we_pe_no=:we_pe_no ");
			q2.setParameter("we_pe_no", we_pe_no_old);	
			Long count_we_pe_no = (Long)q2.uniqueResult();
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> list1 = (List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES>) q2.list();
			tx2.commit();
			session2.close();
			
				for(int i=0; i<count_we_pe_no; i++) {
					String item_seq_no = listWeapFt.get(i).getItem_seq_no();
					String condition = listWeapFt.get(i).getCondition();
					
					Session session0 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session0.beginTransaction();
					Query q1 = session0.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where item_seq_no=:item_seq_no and condition=:condition and we_pe_no=:we_pe_no");
					q1.setParameter("we_pe_no", we_pe_no);
					q1.setParameter("item_seq_no", item_seq_no);
					q1.setParameter("condition", condition);
					Long count1 = (Long)q1.uniqueResult();
					@SuppressWarnings("unchecked")
					List<Integer> list = (List<Integer>) q1.list();
					tx.commit();
					session0.close();
					
					if(count1 == 0) {
						weapFtModel.setAmt_inc_dec(listWeapFt.get(i).getAmt_inc_dec());
						weapFtModel.setCondition(listWeapFt.get(i).getCondition());
						weapFtModel.setCreated_by(listWeapFt.get(i).getCreated_by());
						weapFtModel.setCreated_on(listWeapFt.get(i).getCreated_on());
						weapFtModel.setRemarks(listWeapFt.get(i).getRemarks());
						weapFtModel.setStatus("0");
						weapFtModel.setWe_pe_no(we_pe_no);
						weapFtModel.setItem_seq_no(listWeapFt.get(i).getItem_seq_no());
						weapFtModel.setLocation(listWeapFt.get(i).getLocation());
						weapFtModel.setFormation(listWeapFt.get(i).getFormation());
						weapFtModel.setScenario(listWeapFt.get(i).getScenario());
						weapFtModel.setScenario_unit(listWeapFt.get(i).getScenario_unit());
						weapFtModel.setRoleid(roleid);
						
						Session session9 = HibernateUtil.getSessionFactory().openSession();
						session9.beginTransaction();
						session9.save(weapFtModel);
						model.put("msg", "Data saved Successfully");
						session9.getTransaction().commit();
						session9.close();
					}
					else {
						model.put("msg", "Data already Exist!");
					}
				}
		}
		/////-------------------------- END WEAPON FOOTNOTE -----------------------------///////////////////////////////////
		
		}
		else
		{
			model.put("msg", "WE/PE No already Exist!");
		}
			}	
			catch (Exception e) {
				sessionHQL.getTransaction().rollback();
			}
			finally{
				
				tx0.commit();
				sessionHQL.close();
			}
		return new ModelAndView("redirect:copy_we_pe_weap");
	}

	/////-------------------------- END WEAPON -----------------------------///////////////////////////////////
	
	
}
