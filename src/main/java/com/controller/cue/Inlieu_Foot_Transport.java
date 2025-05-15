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
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/", "user"})
public class Inlieu_Foot_Transport {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	
	@Autowired
	private Cue_wepe_conditionDAO masterDAO;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	ValidationController validation = new ValidationController();
//===============================================CU MODULE - InLieu Footnotes Transport(19)========================================================================
    
    @RequestMapping(value = "/admin/inlieu_foot_transport", method = RequestMethod.GET)
	public ModelAndView Inlieu_foot_transport(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
    	
    	String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("inlieu_foot_transport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
    	Mmap.put("msg", msg);
		return new ModelAndView("inlieu_foot_transportTiles");
	}
    
    @RequestMapping(value = "/admin/inlieu_foot_transport1", method = RequestMethod.POST)
   	public ModelAndView inlieu_foot_transport1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
   			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
   			@RequestParam(value = "mct_no1", required = false) String mct_no,
   			@RequestParam(value = "in_lieu_mct1", required = false) String in_lieu_mct,
   			@RequestParam(value = "we_pe01", required = false) String we_pe,
   			@RequestParam(value = "status1", required = false) String status){
   			String roleType = session.getAttribute("roleType").toString();
   			Mmap.put("we_pe01", we_pe);
   			Mmap.put("status1", status);
   			Mmap.put("we_pe_no1", we_pe_no);
   			Mmap.put("mct_no1", mct_no);
   			Mmap.put("in_lieu_mct1", in_lieu_mct);
   			
   			List<Map<String, Object>> list =masterDAO.getSearch_inlieu_foot_transportReport1(we_pe_no,mct_no,in_lieu_mct,status,roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
   		return new ModelAndView("inlieu_foot_transportTiles");
   	}

	
    @RequestMapping(value = "/inlieu_foot_transportAction", method = RequestMethod.POST)	
    public ModelAndView inlieu_foot_transportAction(@ModelAttribute("inlieu_foot_transportCMD") CUE_TB_MISO_WEPE_TRANS_FOOTNOTES rs, HttpServletRequest request, ModelMap model, HttpSession session, String we_pe_no, String mct_no, String in_lieu_mct, String condition) {
  	   
  	  String std_nomclature = request.getParameter("std_nomclature");
  	  int auth_amt = Integer.parseInt(request.getParameter("auth_amt")) ;
  	  String in_std_nomclature = request.getParameter("in_std_nomclature");
  	   String we_pe = request.getParameter("we_pe");
  	  
  	  
  	  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  		Transaction tx0 = sessionHQL.beginTransaction();
  		
  		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
  		  sessionHQL1.beginTransaction();
  		  
  		try
  		{
  		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
  		{
  			model.put("msg", "Please Select Document");
  			return new ModelAndView("redirect:inlieu_foot_transport");
  		}
  		
  		 if(rs.getWe_pe_no().equals(""))
  			{
  				model.put("msg", "Please Enter WE/PE No");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(rs.getMct_no().equals(""))
  			{
  				model.put("msg", "Please Enter MCT No");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(std_nomclature.equals(""))
  			{
  				model.put("msg", "Please Enter Standard Nomenclature");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(rs.getActual_inlieu_auth()==0)
  			{
  				model.put("msg", "Please Enter the actual In Lieu Authorization");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(rs.getIn_lieu_mct().equals(""))
  			{
  				model.put("msg", "Please Enter the In Lieu MCT Number");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(in_std_nomclature.equals(""))
  			{
  				model.put("msg", "Please Enter the In lieu Nomenclature");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(rs.getCondition().equals(""))
  			{
  				model.put("msg", "Please Enter the Condition");
  				return new ModelAndView("redirect:inlieu_foot_transport");
  			}
  		 if(auth_amt < 1)
  		 {
  			 model.put("msg", "In Lieu Foot Transport Not Possible.");
  			 return new ModelAndView("redirect:inlieu_foot_transport");
  			 
  		 }
  		if(validation.checkWepeLength(rs.getWe_pe_no())  == false)
		{
			model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
  		if(validation.checkMctLength(rs.getMct_no()) == false)
		{
			model.put("msg",validation.mctnoMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
		if(validation.checkMctLength(rs.getIn_lieu_mct()) == false)
		{
			model.put("msg",validation.inlieuMCTMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
		String auth_amt1 =  Double.toString(rs.getActual_inlieu_auth());
		if(validation.checkAuth_amtLength(auth_amt1)  == false){
			model.put("msg",validation.auth_amtMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
  		if(validation.checkConditionLength(rs.getCondition())  == false)
		{
			model.put("msg",validation.condiMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
  		if(validation.checkRemarksLength(rs.getRemarks())  == false)
		{
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:inlieu_foot_transport");
		}
  	   Session sessioncount = HibernateUtil.getSessionFactory().openSession();
  		Transaction transaction = sessioncount.beginTransaction();		
  		Query q1 = sessionHQL.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no and in_lieu_mct=:in_lieu_mct and mct_no=:mct_no and  condition=:condition");
  		q1.setParameter("we_pe_no", request.getParameter("we_pe_no"));
  		q1.setParameter("mct_no", request.getParameter("mct_no"));
  		q1.setParameter("in_lieu_mct", request.getParameter("in_lieu_mct"));
  		q1.setParameter("condition", request.getParameter("condition"));
  		@SuppressWarnings("unchecked")
  		Long count2 = (Long)q1.uniqueResult();
  		//model.put("count", count2);
  		transaction.commit();
  		sessioncount.close();	
  		
  		if(count2 == 0) {  
  	  
  		  String username = session.getAttribute("username").toString();
  		  String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
  		  String we_pe_no1 = request.getParameter("we_pe_no");
  		  String mct_no1 = request.getParameter("mct_no");
  		  CUE_TB_MISO_WEPE_TRANSPORT_DET rs2 = new CUE_TB_MISO_WEPE_TRANSPORT_DET();
  	  
  		  
  		  int roleid = (Integer)session.getAttribute("roleid");			
  		  rs.setRoleid(roleid);
  		  
  		  rs.setCreated_by(username);
  		  rs.setCreated_on(date);
  		  rs.setStatus("0");
  		  rs.setType_of_footnote("0");
  		  
  		  if(ifExistMakeNo(we_pe_no, mct_no) == false) {
  			    rs2.setCreated_by(username);
  			    rs2.setCreated_on(date);
  		        rs2.setStatus("1");
  		        rs2.setAuth_amt(0);
  		        rs2.setWe_pe_no(we_pe_no);
  		        rs2.setMct_no(mct_no);
  		        rs2.setRoleid(roleid);
  		        sessionHQL1.save(rs2);
  		        //sessionHQL.save(rs);
  			    sessionHQL1.getTransaction().commit();
  			    sessionHQL1.close();
  			    model.put("msg", "Data saved Successfully");
  				List<Map<String, Object>> list =masterDAO.getSearch_inlieu_foot_transportReport1(we_pe_no,"","","","");
  		  		model.put("we_pe01", we_pe);
  		  		model.put("we_pe_no1", we_pe_no);
  		  		model.put("list", list);
  		  		model.put("list.size()", list.size());
  			    return new ModelAndView("inlieu_foot_transportTiles");
  		      }
  		     
  			  sessionHQL.save(rs);
  			  sessionHQL.getTransaction().commit();
  			  sessionHQL.close();
  			  model.put("msg", "Data saved Successfully");
  			List<Map<String, Object>> list =masterDAO.getSearch_inlieu_foot_transportReport1(we_pe_no,"","","","");
  	  		model.put("we_pe01", we_pe);
  	  		model.put("we_pe_no1", we_pe_no);
  	  		model.put("list", list);
  	  		model.put("list.size()", list.size());
  			  return new ModelAndView("inlieu_foot_transportTiles");	
  		}
  		else
  		{
  		    model.put("msg", "Data already exist !");
          }
  		}	
  		catch (Exception e) {
  			sessionHQL1.getTransaction().rollback();
  			sessionHQL.getTransaction().rollback();
  			
  		}
  		
  		return new ModelAndView("inlieu_foot_transportTiles");
    	}
    
    @RequestMapping(value = "/search_inlieu_foot_transport", method = RequestMethod.GET)
    public ModelAndView Search_inlieu_foot_transport(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
    	String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_inlieu_foot_transport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
    	Mmap.put("msg", msg);
    	return new ModelAndView("search_inlieu_foot_transportTiles");
    }

  @RequestMapping(value = "/getWePetoTableTitleList", method = RequestMethod.POST)
    public @ResponseBody List<String> getWePetoTableTitleList(String we_pe_no,HttpSession sessionUserId){
    	int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
    	Session session = HibernateUtil.getSessionFactory().openSession();
  	  Transaction tx = session.beginTransaction();
  	  Query q = session.createQuery("select distinct table_title from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no");
  	  q.setParameter("we_pe_no", we_pe_no);
  	  @SuppressWarnings("unchecked")
  	  List<String> list = (List<String>) q.list();
  	  tx.commit();
  	  session.close();
  	  return list;
    }

	@RequestMapping(value = "/getMctMaintoStdNomenclatureList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctMaintoStdNomenclatureList(String mct,HttpSession sessionUserId){
		  int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = session.beginTransaction();
		  Query q = session.createQuery("select distinct mct_nomen from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:mct order by mct_nomen");
		  q.setParameter("mct", mct);
		  @SuppressWarnings("unchecked")
		  List<String> list = (List<String>) q.list();
		  tx.commit();
		  session.close();
		  return list;
	}
  
	@SuppressWarnings("unchecked")
	public Boolean ifExistMakeNo(String we_pe_no, String mct_no) {
		  String q = "select we_pe_no from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no =:we_pe_no and mct_no =:mct_no";
	   	  List<CUE_TB_MISO_WEPE_TRANSPORT_DET> created_by = null;
	   	  Session session = HibernateUtil.getSessionFactory().openSession();
	   	  Transaction tx = session.beginTransaction();
	   	  try {
	   		  Query query = session.createQuery(q);
	   		  query.setParameter("we_pe_no", we_pe_no).setParameter("mct_no", mct_no);
	   		  created_by = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) query.list();
	   		  tx.commit();
	   		  session.close();
	   	  }catch(Exception e) {
	   		  session.getTransaction().rollback();
	   		
	   		  return null; 
	   	  }
	   	  if(created_by.size() > 0) {
	   		  return true;
	   	  }
	   	  else
	   		  return false;
	}

  
  @RequestMapping(value = "/admin/search_inlieu_foot_transport1", method = RequestMethod.POST)
	public ModelAndView search_inlieu_foot_transport1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "in_lieu_mct1", required = false) String in_lieu_mct,
			@RequestParam(value = "table_title1", required = false) String table_title,
			@RequestParam(value = "std_nomclature1", required = false) String std_nomclature,
			@RequestParam(value = "in_std_nomclature1", required = false) String in_std_nomclature,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			
			Mmap.put("table_title1", table_title);
			Mmap.put("we_pe1", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("in_std_nomclature1", in_std_nomclature);
			Mmap.put("std_nomclature1", std_nomclature);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("in_lieu_mct1", in_lieu_mct);			
			List<Map<String, Object>> list =masterDAO.getSearch_inlieu_foot_transportReport(status,we_pe_no,mct_no,in_lieu_mct,roleType);			 
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("search_inlieu_foot_transportTiles");
	}

 
	@RequestMapping(value = "/admin/ApprovedFootTransport", method = RequestMethod.POST)
	public ModelAndView setApprovedFootTransport(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "mct_no2", required = false) String mct_no,
			@RequestParam(value = "in_lieu_mct2", required = false) String in_lieu_mct,
			@RequestParam(value = "table_title2", required = false) String table_title,
			@RequestParam(value = "std_nomclature2", required = false) String std_nomclature,
			@RequestParam(value = "in_std_nomclature2", required = false) String in_std_nomclature,
			@RequestParam(value = "we_pe2", required = false) String we_pe,
			@RequestParam(value = "status2", required = false) String status){
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			String username = session.getAttribute("username").toString();
			String date_of_apprv_rejc = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

			String hqlUpdate = "update CUE_TB_MISO_WEPE_TRANS_FOOTNOTES c set c.status = :status,c.aprv_rejc_by=:aprv_rejc_by,c.date_of_apprv_rejc=:date_of_apprv_rejc where c.id = :id";
			int app = session1.createQuery( hqlUpdate ).setString( "status", "1" ).setString("aprv_rejc_by", username).setString("date_of_apprv_rejc", date_of_apprv_rejc).setInteger( "id", appid ).executeUpdate();
			tx.commit();
			session1.close();
			if(app > 0) {
				Mmap.put("msg", "Approved Successfully");	
			}else {
				Mmap.put("msg","Approved not Successfully");	
			}
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("table_title1", table_title);
			Mmap.put("we_pe1", we_pe);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("in_lieu_mct1", in_lieu_mct);
			Mmap.put("in_std_nomclature1", in_std_nomclature);
			Mmap.put("std_nomclature1", std_nomclature);
			List<Map<String, Object>> list =masterDAO.getSearch_inlieu_foot_transportReport(status,we_pe_no,mct_no,in_lieu_mct,roleType);
			
			Mmap.put("list", list);
		
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		
		return new ModelAndView("search_inlieu_foot_transportTiles");
	}

	@RequestMapping(value = "/DeleteFootTransportAdd",method=RequestMethod.POST)
	public @ResponseBody List<String> DeleteFootTransportAdd(int deleteid,ModelMap model) {			
		List<String> list2 = new ArrayList<String>();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where id = :id";
	    Query query = session.createQuery(hql);
	    query.setInteger("id",deleteid);
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			model.put("msg", "Deleted Successfully");
			list2.add("Deleted Successfully");
		}else {
			model.put("msg", "Deleted not Successfully");	
			list2.add("Deleted not Successfully");
		}			
		return list2;
	}

	@RequestMapping(value = "/admin/UpdateFootTransport")
	public ModelAndView UpdateFootTransport(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication
			,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where id=:id");
    	q.setParameter("id", updateid);
    	CUE_TB_MISO_WEPE_TRANS_FOOTNOTES list = (CUE_TB_MISO_WEPE_TRANS_FOOTNOTES) q.list().get(0);
    	tx.commit();
    	
    	model.put("edit_inlieu_foot_transportCMD", list);	
		return new ModelAndView("edit_inlieu_foot_transportTiles");
	}

	@RequestMapping(value = "/edit_inlieu_foot_transportAction",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView edit_inlieu_foot_transportAction(@ModelAttribute("edit_inlieu_foot_transportCMD") CUE_TB_MISO_WEPE_TRANS_FOOTNOTES updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session11
			,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
			
			if(updateid.getActual_inlieu_auth()==0)
			{
			 	model.put("updateid",updateid.getId() );
				model.put("msg", "Please Enter the actual In Lieu Authorization");
				return new ModelAndView("redirect:UpdateFootTransport");
			}
		 
			if(updateid.getCondition() == "" || updateid.getCondition() ==null ||  updateid.getCondition().equals(null) ||  updateid.getCondition().isEmpty())
			{
				model.put("updateid",updateid.getId() );
				model.put("msg", "Please Enter Condition");
				return new ModelAndView("redirect:UpdateFootTransport");
			}
			if(validation.checkConditionLength(updateid.getCondition()) == false)
			{
				model.put("msg",validation.condiMSG);
				return new ModelAndView("redirect:UpdateFootTransport");
			}
	  		
	  		if(validation.checkRemarksLength(updateid.getRemarks()) == false)
			{
				model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:UpdateFootTransport");
			}
	  		String auth_amt1 =  Double.toString(updateid.getActual_inlieu_auth());
			if(validation.checkAuth_amtLength(auth_amt1)  == false){
				model.put("msg",validation.auth_amtMSG);
				return new ModelAndView("redirect:UpdateFootTransport");
			}
			String username = session11.getAttribute("username").toString();
			Session sessioncount = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = sessioncount.beginTransaction();		
			Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no and mct_no=:mct_no and in_lieu_mct=:in_lieu_mct and condition=:condition and id !=:id");
			q1.setParameter("we_pe_no", updateid.getWe_pe_no());
			q1.setParameter("mct_no", updateid.getMct_no());
			q1.setParameter("in_lieu_mct", updateid.getIn_lieu_mct());
			q1.setParameter("condition", updateid.getCondition());
			q1.setParameter("id", updateid.getId());
			@SuppressWarnings("unchecked")
			Long count2 = (Long)q1.uniqueResult();
			model.put("count", count2);
			transaction.commit();
			sessioncount.close();	
			
			if(count2 == 0) {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Transaction tx = session.beginTransaction();
			String hql = "update CUE_TB_MISO_WEPE_TRANS_FOOTNOTES  set condition =:condition,actual_inlieu_auth =:actual_inlieu_auth,remarks=:remarks,modified_by=:modified_by,modified_on=:modified_on, status ='0' where id=:id";
		    Query query = session.createQuery(hql).setString("condition",updateid.getCondition()).setInteger("actual_inlieu_auth",updateid.getActual_inlieu_auth()).setString("remarks",updateid.getRemarks()).setString("modified_by", username).setString("modified_on", modifydate).setInteger("id",updateid.getId());
			int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				model.put("msg", "Updated Successfully");
			}else {
				model.put("msg", "Updated not Successfully");
			}			
			return new ModelAndView("redirect:inlieu_foot_transport");
			}		
			else {
			   model.put("msg", "Data already exist !");
			   Session session= HibernateUtilNA.getSessionFactory().openSession();
		    	Transaction tx = session.beginTransaction();	
		    	Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where id=:id");
		    	q.setParameter("id", updateid.getId());
		    	CUE_TB_MISO_WEPE_TRANS_FOOTNOTES list = (CUE_TB_MISO_WEPE_TRANS_FOOTNOTES) q.list().get(0);
		    	tx.commit();
			   
			   model.put("edit_inlieu_foot_transportCMD", list);	
			   return new ModelAndView("edit_inlieu_foot_transportTiles");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
			if(msg == "Data already exist !") {
				return new ModelAndView("edit_inlieu_foot_transportTiles");
			}
			else
			{
				return new ModelAndView("redirect:inlieu_foot_transport");
			}
		
		
	} 	
	
	@RequestMapping(value = "/updaterejectaction_foot_transport",method=RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectaction(String remarks,String letter_no,int id) {
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("CUE_TB_MISO_WEPE_TRANS_FOOTNOTES",remarks,id,letter_no);
		return list2;
	}
	

}
