package com.controller.psg.Commissioning;
import java.math.BigInteger;
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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Change_Of_Commision_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_CADET;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COURSE;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_GENDER;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_UNIT;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_UpdateComm_Controller {
	
	@Autowired
	Search_UpdateComm_Dao scldao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	//CommanController m = new CommanController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();
	Update_Comm_Controller ucc = new Update_Comm_Controller();
	Change_Of_Commision_Controller coc = new Change_Of_Commision_Controller();
	PsgDashboardController das = new PsgDashboardController();
	BigInteger u_id = BigInteger.ZERO;
	
	@RequestMapping(value = "/admin/Search_S_C_L_Url", method = RequestMethod.GET)
	public ModelAndView Search_S_C_L_Url(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		 Boolean val = roledao.ScreenRedirect("Search_S_C_L_Url", roleid);
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("list", scldao.Search_S_C_LData("", "0", "", "", "", "","",roleSusNo, roleType,roleAccess,false));
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		
		
		return new ModelAndView("Search_S_C_L_Tiles");
	}
	
	

	@RequestMapping(value = "/admin/GetS_C_LData", method = RequestMethod.POST)
	public ModelAndView GetS_C_LData(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
	        @RequestParam(value = "cr_date1", required = false) String cr_date
	       ){
		
		boolean IsMns=false;
		
	        unit_name = unit_name.replace("&#40;", "(");
	        unit_name = unit_name.replace("&#41;", ")");
	        String roleType = session.getAttribute("roleType").toString();
	        String roleSusNo = session.getAttribute("roleSusNo").toString();
	        String roleAccess = session.getAttribute("roleAccess").toString();
	        
	        if(unit_sus_no!="") {
		    	  if (!valid.SusNoLength(unit_sus_no)) {
						Mmap.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_S_C_L_Url");
					}
		    	  
		    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
						return new ModelAndView("redirect:Search_S_C_L_Url");
					}
		      }
			
	      if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
		        Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session) .get(0));
			}
	      
		 if(unit_name!="") {
			  if (!valid.isUnit(unit_name)) {
				  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:Search_S_C_L_Url");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_S_C_L_Url");
//				}
	      }
		 
		 if(personnel_no!="") {
			   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:Search_S_C_L_Url");
				}
			  
			      if (personnel_no.length() < 7 || personnel_no.length() > 9) {
						Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
						return new ModelAndView("redirect:Search_S_C_L_Url");
					}
		     } 
		
		 ArrayList<ArrayList<String>> list = scldao.Search_S_C_LData(personnel_no,status,rank,unit_sus_no,unit_name,cr_by,cr_date,roleSusNo,roleType,roleAccess,IsMns);
	        Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("personnel_no1", personnel_no);
			Mmap.put("rank1", rank);
			Mmap.put("status1", status);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("unit_sus_no1", unit_sus_no);
			Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
			Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
            Mmap.put("cr_date1", cr_date);
			Mmap.put("cr_by1", cr_by);
		return new ModelAndView("Search_S_C_L_Tiles","SearchS_C_LCMD",new TB_TRANS_PROPOSED_COMM_LETTER());
	}
	/*******************************************APPROVAL & Reject START****************************************************************/
	
	@RequestMapping(value = "/Approve_UpadteComm_DataUrl",method = RequestMethod.POST)
	public ModelAndView Approve_UpadteComm_DataUrl(@ModelAttribute("id2") BigInteger updateid,
			@ModelAttribute("personnel_no2") String personnel_no2,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_S_C_L_Url", roleid);
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData(updateid);
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData(updateid);
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData(updateid);
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData(updateid);
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData(updateid);
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData(updateid);
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData(updateid);
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData(updateid);
		List<TB_CHANGE_OF_RANK> ChangeOfrank = getraankData(updateid);
		
		Mmap.put("msg", msg);
		Mmap.put("id", updateid);
		Mmap.put("personnel_no2", personnel_no2);
		Mmap.put("getCourseNameList", mcommon.getCourseNameList());
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("ChangeOfpr", ChangeOfpr.size());
		Mmap.put("ChangeOfcadet", ChangeOfcadet.size());
		Mmap.put("ChangeOfcourse", ChangeOfcourse.size());
		Mmap.put("ChangeOfgender", ChangeOfgender.size());
		Mmap.put("ChangeOfcommision", ChangeOfcommision.size());
		Mmap.put("ChangeOfdob", ChangeOfdob.size());
		Mmap.put("ChangeOfarmservice", ChangeOfarmservice.size());
		Mmap.put("ChangeOfunit", ChangeOfunit.size());
		Mmap.put("ChangeOfrank", ChangeOfrank.size());
		
		
		return new ModelAndView("App_Update_Comm_Data_Tiles");
	}
	 /****************************************** Approval Action ************************************************/ 
	
	@RequestMapping(value = "/Approve_Update_Comm_DataAction", method = RequestMethod.POST)
	public ModelAndView Approve_Update_Comm_DataAction(@ModelAttribute("Approve_Update_Comm_DataCMD") TB_TRANS_PROPOSED_COMM_LETTER P, BindingResult result,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		
		String username = session.getAttribute("username").toString();
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData(new BigInteger(request.getParameter("comm_id")));
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData(new BigInteger(request.getParameter("comm_id")));
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData(new BigInteger(request.getParameter("comm_id")));
		List<TB_CHANGE_OF_RANK > ChangeOfrank = getraankData(new BigInteger(request.getParameter("comm_id")));
	 	
	 	
	 	String personnelNo=request.getParameter("personnelNo");
	 	String redirect = "Search_S_C_L_Url";
	 	if (personnelNo != null && personnelNo.length() >= 2) {
	 	    String firstTwoChars = personnelNo.substring(0, 2);
	 	    if (firstTwoChars.equals("NR") || firstTwoChars.equals("NS")) {
	 	        redirect = "Search_S_C_L_MnsUrl";
	 	    }
	 	}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date modified_date = new Date();
		
		try {
			
			TB_TRANS_PROPOSED_COMM_LETTER Comm = new TB_TRANS_PROPOSED_COMM_LETTER();
			Comm.setId(new BigInteger(request.getParameter("comm_id")));
			P.setId(new BigInteger(request.getParameter("comm_id")));
			P.setModified_date(modified_date);
			P.setUpdate_comm_status(1);
			
			Mmap.put("msg",Update_comm_latter_Status(P, username));
			
			if (ChangeOfpr.size() > 0) {
				P.setPersonnel_no(ChangeOfpr.get(0).getNew_personal_no());
				TB_PSG_CHANGE_OF_COMISSION ChangeOfpr1 =ChangeOfpr.get(0);
				
				ChangeOfpr1.setModified_date(modified_date);
				
				Mmap.put("msg", Update_Change_of_prNo(ChangeOfpr1, username));
				Mmap.put("msg", Update_comm_latter_Data(P, username));
				
			}
			if (ChangeOfcadet.size() > 0) {
				P.setCadet_no(ChangeOfcadet.get(0).getCadet_no());
				TB_PSG_UPDATE_COMM_CADET Changecadet = new TB_PSG_UPDATE_COMM_CADET();
				Changecadet.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changecadet.setModified_date(modified_date);
				Changecadet.setModified_by(username);
				Mmap.put("msg", Update_cadet_comm_latter_data(P, username));
				Mmap.put("msg", Update_CadetNo(Changecadet, username));
			}
			if (ChangeOfcourse.size() > 0) {
				P.setBatch_no(ChangeOfcourse.get(0).getBatch_no());
				P.setCourse(ChangeOfcourse.get(0).getCourse());
				TB_PSG_UPDATE_COMM_COURSE Changecourse = new TB_PSG_UPDATE_COMM_COURSE();
				Changecourse.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changecourse.setModified_date(modified_date);
				Mmap.put("msg", Update_course_comm_latter_data(P, username));
				Mmap.put("msg", Update_Course(Changecourse, username));
			}
			if (ChangeOfgender.size() > 0) {
				P.setGender(ChangeOfgender.get(0).getGender());
				TB_PSG_UPDATE_COMM_GENDER Changegender = new TB_PSG_UPDATE_COMM_GENDER();
				Changegender.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changegender.setModified_date(modified_date);
				Mmap.put("msg", Update_gender_comm_latter_data(P, username));
				Mmap.put("msg", Update_Gender(Changegender, username));
				
			}
			if (ChangeOfcommision.size() > 0) {
				P.setType_of_comm_granted(ChangeOfcommision.get(0).getType_of_comm_granted());
				P.setDate_of_commission(ChangeOfcommision.get(0).getDate_of_commission());
				TB_PSG_UPDATE_COMM_COMMISSION Changecomm = new TB_PSG_UPDATE_COMM_COMMISSION();
				Changecomm.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changecomm.setModified_date(modified_date);
				Mmap.put("msg", Update_commision_comm_latter_data(P, username));
				Mmap.put("msg", Update_Comm(Changecomm, username));
				
				AddRankDetailsAfterCommissionChange(new BigInteger(request.getParameter("comm_id")),ChangeOfcommision.get(0).getDate_of_commission(),username);
			}
			if (ChangeOfdob.size() > 0) {
				P.setDate_of_birth(ChangeOfdob.get(0).getDate_of_birth());
				TB_PSG_UPDATE_COMM_BIRTH Changebirth = new TB_PSG_UPDATE_COMM_BIRTH();
				Changebirth.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changebirth.setModified_date(modified_date);
				Mmap.put("msg", Update_birth_comm_latter_data(P, username));
				Mmap.put("msg", Update_Birth(Changebirth, username));
			}
			if (ChangeOfarmservice.size() > 0) {
				P.setParent_arm(ChangeOfarmservice.get(0).getParent_arm_service());
				P.setRegiment(ChangeOfarmservice.get(0).getRegt());
				TB_INTER_ARM_TRANSFER Changereg = new TB_INTER_ARM_TRANSFER();
				Changereg.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changereg.setModified_date(modified_date);
				Mmap.put("msg", Update_armservice_comm_latter_data(P, username));
				Mmap.put("msg", Update_Regiment(Changereg, username));
			}
			if (ChangeOfunit.size() > 0) {
				P.setUnit_sus_no(ChangeOfunit.get(0).getUnit_sus_no());
				P.setDate_of_tos(ChangeOfunit.get(0).getDate_of_tos());
				TB_PSG_UPDATE_COMM_UNIT Changeunit = new TB_PSG_UPDATE_COMM_UNIT();
				Changeunit.setComm_id(new BigInteger(request.getParameter("comm_id")));
				Changeunit.setModified_date(modified_date);
				Mmap.put("msg", Update_unit_comm_latter_data(P, username));
				Mmap.put("msg", Update_Unit(Changeunit, username));
			}
			
		  if (ChangeOfrank.size() > 0) {
				  P.setRank(ChangeOfrank.get(0).getRank());
				  P.setDate_of_rank(ChangeOfrank.get(0).getDate_of_rank());
				  TB_CHANGE_OF_RANK Changerank = new TB_CHANGE_OF_RANK();
					Changerank.setComm_id(new BigInteger(request.getParameter("comm_id")));
				  Changerank.setModified_date(modified_date);
				  Changerank.setModified_by(username); 
				  Mmap.put("msg",Update_rankcomm_latter_data(P, username)); 
				  Mmap.put("msg" ,  Update_rankNo(Changerank, username)); 
		   }
			
			   if(check_Update_Comm_DataStatus(new BigInteger(request.getParameter("comm_id")))) {

			       	  Mmap.put("msg",  Update_Commission_Data(P, username,3));

			         }

			         else {

			        	 Mmap.put("msg",  Update_Commission_Data(P, username,1));

			         }
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return new ModelAndView("redirect:"+redirect+"");
	}
	
	public List<TB_CHANGE_OF_RANK> getRankData(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK "
				+ " where status in ('1','2') and comm_id=:comm_id order by id ASC";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	/*public List<TB_PSG_UPDATE_RANK_COMMISION> getRankData_comm(BigInteger comm_id) {
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_RANK_COMMISION  "
				+ " where status in ('1','2') and comm_id=:comm_id order by id ASC";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_RANK_COMMISION > list = (List<TB_PSG_UPDATE_RANK_COMMISION >) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}*/
	public String AddRankDetailsAfterCommissionChange(BigInteger comm_id,Date date_of_comm,String username)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		String msg = "";
	
		try {

			List<TB_CHANGE_OF_RANK> list = getRankData(comm_id);
			
			TB_CHANGE_OF_RANK rank = new TB_CHANGE_OF_RANK();
				int rankid = list.get(0).getId();
			           
			          	rank.setAuthority(list.get(0).getAuthority());
			          	rank.setDate_of_authority(list.get(0).getDate_of_authority());
			          	rank.setCensus_id(list.get(0).getCensus_id());
			          	rank.setComm_id(list.get(0).getComm_id());
			          	rank.setCancel_status(-1);
			          	rank.setStatus(1);
			          	rank.setRank(list.get(0).getRank());
			          	rank.setRank_type(list.get(0).getRank_type());
			            rank.setDate_of_rank(date_of_comm);
			            rank.setModified_by(username);
			            rank.setModified_date(new Date());
			            
				int id = (int) sessionhql.save(rank);
				msg = Integer.toString(id);
				
			
				String hql = " update TB_CHANGE_OF_RANK set status = '-1',cancel_status = '1',cancel_by=:cancel_by,cancel_date=:cancel_date "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("cancel_by", username).setDate("cancel_date", new Date())
						.setInteger("id",rankid);
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
			tx.commit();
			}catch (RuntimeException e) {
				e.printStackTrace();
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}
		return msg;
	}
	
	
	
	/*public String Update_Commission_Data(TB_TRANS_PROPOSED_COMM_LETTER obj,String username,int update_offr_status){

	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		  try{
			  String hql1 = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
						+ "update_comm_status=:update_comm_status where id=:comm_id and status in ('1','5') and update_comm_status != '3' ";
	
				Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setInteger("update_comm_status", update_offr_status)
						.setBigInteger("comm_id", obj.getId());
		 
				   msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

		          tx.commit();
		 }
		  catch (Exception e) {
		          msg = "Data Not Updated.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }

		  return msg;

	}*/
	
	
	////change by yogesh
	
	public String Update_Commission_Data(TB_TRANS_PROPOSED_COMM_LETTER obj,String username,int update_offr_status){

	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		  try{
			  
			  String hql_xml = "update XML_FILE_UPLOAD set status=:status , modified_by=:modified_by,modified_date=:modified_date"

					+ " where  comm_id=:comm_id and status = '0' ";

			Query query_xml = sessionHQL.createQuery(hql_xml)
					.setInteger("status", update_offr_status).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query_xml.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			  
			  
			  
			  String hql1 = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
						+ "update_comm_status=:update_comm_status where id=:comm_id and status in ('1','5') and update_comm_status != '3' ";
	
				Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setInteger("update_comm_status", update_offr_status)
						.setBigInteger("comm_id", obj.getId());
		 
				   msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

		          tx.commit();
		 }
		  catch (Exception e) {
			 
		          msg = "Data Not Updated.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }

		  return msg;

	}
	
	
	
	
	
	public Boolean check_Update_Comm_DataStatus(BigInteger comm_id ) throws ParseException{
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData3(comm_id);
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData3(comm_id);
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData3(comm_id);
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData3(comm_id);
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData3(comm_id);
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData3(comm_id);
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData3(comm_id);
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData3(comm_id);

		 if(ChangeOfpr.size()>0) {
			 return true;
		 }

	    if(ChangeOfcadet.size()>0) {
			 return true;
		 }

	    if(ChangeOfcourse.size()>0) {
			 return true;
		 }

	    if(ChangeOfgender.size()>0) {
			 return true;
		 }

	    if(ChangeOfcommision.size()>0) {
			 return true;
		 }

	    if(ChangeOfdob.size()>0) {
			 return true;
		 }

	    if(ChangeOfarmservice.size()>0) {
			 return true;
		 }

	    if(ChangeOfunit.size()>0) {
			 return true;
		 }

	    return false;
}

	
/****************************************** Approval for Personal Number Start ************************************************/
	@RequestMapping(value = "/admin/getPersonalNOData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getPersonalNOData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_comm_latter_Data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set personnel_no=:personnel_no,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setString("personnel_no", obj.getPersonnel_no())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_comm_latter_Status(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set update_comm_status=:update_comm_status,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  and update_comm_status != 3  ";
			Query query = sessionHQL.createQuery(hql).setInteger("update_comm_status",1)
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_Change_of_prNo(TB_PSG_CHANGE_OF_COMISSION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hqlUpdate="select id from TB_PSG_CHANGE_OF_COMISSION where comm_id=:comm_id and census_id=0 and status in (1,2) order by id desc ";
			Query query1 = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", obj.getComm_id()).setMaxResults(1);
			BigInteger c = (BigInteger)  query1.uniqueResult();
			TB_PSG_CHANGE_OF_COMISSION preobj=null;
			if(c!=null ) {
				preobj = (TB_PSG_CHANGE_OF_COMISSION) sessionHQL.get(TB_PSG_CHANGE_OF_COMISSION.class, c);
			}
			
			String hql = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", preobj.getStatus())
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
		preobj.setStatus(-1);
		preobj.setCancel_status(1);
			sessionHQL.save(preobj);
			tx.commit();
		} catch (Exception e) {
		
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectpersonnelNo", method = RequestMethod.POST)
	public @ResponseBody String RejectpersonnelNo(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		TB_PSG_CHANGE_OF_COMISSION rejectperno = new TB_PSG_CHANGE_OF_COMISSION();
		rejectperno.setComm_id(new BigInteger(request.getParameter("comm_id")));
		rejectperno.setId(new  BigInteger(request.getParameter("prs_id")));
		rejectperno.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = PersonnelNo_Reject(rejectperno, username);

		return msg1;

	}
	public String PersonnelNo_Reject(TB_PSG_CHANGE_OF_COMISSION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		try {				
			String hql = "update TB_PSG_CHANGE_OF_COMISSION set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setBigInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	
/****************************************** Approval  for Personal Number End ************************************************/
	
/****************************************** Approval for Cadet Number Start ************************************************/
	
	@RequestMapping(value = "/admin/getcadetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_CADET> getcadetData(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_CADET where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_CADET> list = (List<TB_PSG_UPDATE_COMM_CADET>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public String Update_CadetNo(TB_PSG_UPDATE_COMM_CADET obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_CADET set status=:status where comm_id=:comm_id and status != '0' ";
			
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			
			String hql = "update TB_PSG_UPDATE_COMM_CADET set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql)
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("status", 1).setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectcadNo", method = RequestMethod.POST)
	public @ResponseBody String RejectcadNo(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_CADET rejectcadet = new TB_PSG_UPDATE_COMM_CADET();
		rejectcadet.setComm_id(new BigInteger(request.getParameter("comm_id")));
		rejectcadet.setId(Integer.parseInt(request.getParameter("c_id")));
		rejectcadet.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Cadet_Reject(rejectcadet, username);

		return msg1;

	}
	public String Cadet_Reject(TB_PSG_UPDATE_COMM_CADET obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
						
			String hql = "update TB_PSG_UPDATE_COMM_CADET set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_cadet_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set cadet_no=:cadet_no,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setString("cadet_no", obj.getCadet_no())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
/****************************************** Approval for Cadet Number End ************************************************/
	
/****************************************** Approval for Course Start ************************************************/
	@RequestMapping(value = "/admin/getCourseData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COURSE> getCourseData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COURSE where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COURSE> list = (List<TB_PSG_UPDATE_COMM_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Course(TB_PSG_UPDATE_COMM_COURSE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_COURSE set status=:status where comm_id=:comm_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_COMM_COURSE set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	@RequestMapping(value = "/admin/RejectCourse", method = RequestMethod.POST)
	public @ResponseBody String RejectCourse(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_COURSE co = new TB_PSG_UPDATE_COMM_COURSE();
		co.setComm_id(new BigInteger(request.getParameter("comm_id")));
		co.setId(Integer.parseInt(request.getParameter("co_id")));
		co.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Course_Reject(co, username);

		return msg1;
	}
	
	public String Course_Reject(TB_PSG_UPDATE_COMM_COURSE obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {						
			String hql = "update TB_PSG_UPDATE_COMM_COURSE set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_course_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set batch_no=:batch_no,course=:course,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setString("batch_no", obj.getBatch_no()).setInteger("course", obj.getCourse())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
/****************************************** Approval for Course End ************************************************/
	
/****************************************** Approval for Gender Start ************************************************/
	
	@RequestMapping(value = "/admin/getGenderData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_GENDER> getGenderData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_GENDER where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_GENDER> list = (List<TB_PSG_UPDATE_COMM_GENDER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Gender(TB_PSG_UPDATE_COMM_GENDER obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_GENDER set status=:status where comm_id=:comm_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_COMM_GENDER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectGENDER", method = RequestMethod.POST)
	public @ResponseBody String RejectGENDER(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_GENDER ge = new TB_PSG_UPDATE_COMM_GENDER();
		ge.setComm_id(new BigInteger(request.getParameter("comm_id")));
		ge.setId(Integer.parseInt(request.getParameter("g_id")));
		ge.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Gender_Reject(ge, username);

		return msg1;

	}
	public String Gender_Reject(TB_PSG_UPDATE_COMM_GENDER obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {					
			String hql = "update TB_PSG_UPDATE_COMM_GENDER set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_gender_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set gender=:gender,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setInteger("gender", obj.getGender())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
/****************************************** Approval for Gender End ************************************************/
	
/****************************************** Approval for Commission Start ************************************************/
	
	@RequestMapping(value = "/admin/getcommisonData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COMMISSION> getcommisonData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COMMISSION where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COMMISSION> list = (List<TB_PSG_UPDATE_COMM_COMMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Comm(TB_PSG_UPDATE_COMM_COMMISSION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_COMMISSION set status=:status where comm_id=:comm_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_COMM_COMMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectCommission", method = RequestMethod.POST)
	public @ResponseBody String RejectCommission(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_COMMISSION cc = new TB_PSG_UPDATE_COMM_COMMISSION();
		cc.setComm_id(new BigInteger(request.getParameter("comm_id")));
		cc.setId(Integer.parseInt(request.getParameter("com_id")));
		cc.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Comm_Reject(cc, username);

		return msg1;
	}
	
	public String Comm_Reject(TB_PSG_UPDATE_COMM_COMMISSION obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {			
			String hql = "update TB_PSG_UPDATE_COMM_COMMISSION set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";
			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_commision_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setInteger("type_of_comm_granted", obj.getType_of_comm_granted()).setParameter("date_of_commission", obj.getDate_of_commission())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
/****************************************** Approval for Commission End ************************************************/
	
/****************************************** Approval for Birth Start ************************************************/
	
	@RequestMapping(value = "/admin/getdobData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_BIRTH> getdobData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_BIRTH where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_BIRTH> list = (List<TB_PSG_UPDATE_COMM_BIRTH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Birth(TB_PSG_UPDATE_COMM_BIRTH obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_BIRTH set status=:status where comm_id=:comm_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_COMM_BIRTH set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectBirth", method = RequestMethod.POST)
	public @ResponseBody String RejectBirth(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_BIRTH cb = new TB_PSG_UPDATE_COMM_BIRTH();
		cb.setComm_id(new BigInteger(request.getParameter("comm_id")));
		cb.setId(Integer.parseInt(request.getParameter("birth_id")));
		cb.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Birth_Reject(cb, username);

		return msg1;
	}
	
	public String Birth_Reject(TB_PSG_UPDATE_COMM_BIRTH obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {			
			String hql = "update TB_PSG_UPDATE_COMM_BIRTH set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";
			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_birth_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set date_of_birth=:date_of_birth,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setParameter("date_of_birth", obj.getDate_of_birth())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
/****************************************** Approval for Birth End ************************************************/
	
/****************************************** Approval for Arm/Service Start ************************************************/
	
	@RequestMapping(value = "/admin/getarmsirviceData", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER> getarmsirviceData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public String Update_Regiment(TB_INTER_ARM_TRANSFER obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hqlUpdate="select id from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and census_id=0 and status in (1,2) order by id desc ";
			Query query1 = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", obj.getComm_id()).setMaxResults(1);
			Integer c = (Integer)  query1.uniqueResult();
			TB_INTER_ARM_TRANSFER preobj=null;
			if(c!=null && c>0) {
				preobj = (TB_INTER_ARM_TRANSFER) sessionHQL.get(TB_INTER_ARM_TRANSFER.class, c);
			}
			
			
			String hql = "update TB_INTER_ARM_TRANSFER set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", preobj.getStatus())
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			preobj.setStatus(-1);
			preobj.setCancel_status(1);
			sessionHQL.save(preobj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/RejectArmService", method = RequestMethod.POST)
	public @ResponseBody String RejectArmService(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_INTER_ARM_TRANSFER reg = new TB_INTER_ARM_TRANSFER();
		reg.setComm_id(new BigInteger(request.getParameter("comm_id")));
		reg.setId(Integer.parseInt(request.getParameter("reg_id")));
		reg.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = ArmService_Reject(reg, username);

		return msg1;

	}
	public String ArmService_Reject(TB_INTER_ARM_TRANSFER obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
					
			String hql = "update TB_INTER_ARM_TRANSFER set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";
			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	public String Update_armservice_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {// keval
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set parent_arm=:parent_arm,regiment=:regiment,modified_by=:modified_by,modified_date=:modified_date "
					+ "where id=:comm_id and status = '1'  ";
			Query query = sessionHQL.createQuery(hql).setParameter("regiment", obj.getRegiment()).setParameter("parent_arm", obj.getParent_arm())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setBigInteger("comm_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
/****************************************** Approval for Arm/Service End ************************************************/
	
/****************************************** Approval for Unit Start ************************************************/
	
	@RequestMapping(value = "/admin/getunitData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_UNIT> getunitData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_UNIT where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_UNIT> list = (List<TB_PSG_UPDATE_COMM_UNIT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	
	@RequestMapping(value = "/admin/getraankData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK> getraankData(BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public String Update_Unit(TB_PSG_UPDATE_COMM_UNIT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_COMM_UNIT set status=:status where comm_id=:comm_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_COMM_UNIT set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setBigInteger("comm_id", obj.getComm_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/Unitreject", method = RequestMethod.POST)
	public @ResponseBody String Unitreject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)  {

		String username = session.getAttribute("username").toString();
		TB_PSG_UPDATE_COMM_UNIT unit = new TB_PSG_UPDATE_COMM_UNIT();
		unit.setComm_id(new BigInteger(request.getParameter("comm_id")));
		unit.setId(Integer.parseInt(request.getParameter("u_id")));
		unit.setReject_remarks(request.getParameter("reject_remarks"));
		String msg1 = Unit_Reject(unit, username);

		return msg1;

	}
	public String Unit_Reject(TB_PSG_UPDATE_COMM_UNIT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {						
			String hql = "update TB_PSG_UPDATE_COMM_UNIT set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where  comm_id=:comm_id and status = '0' and id=:id ";
			Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks()).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setBigInteger("comm_id", obj.getComm_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	//-------------------------KEVAL-----------------------------------//
		public String Update_unit_comm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) { 
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
			try {
				
				
				String hql_post = "update TB_POSTING_IN_OUT set to_sus_no=:unit_sus_no,dt_of_tos=:date_of_tos,dt_of_sos=:date_of_tos,modified_by=:modified_by,modified_date=:modified_date "
						+ "where comm_id=:comm_id and from_sus_no is null ";
				Query query_post = sessionHQL.createQuery(hql_post).setParameter("unit_sus_no", obj.getUnit_sus_no()).setTimestamp("date_of_tos", obj.getDate_of_tos())
						.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
						.setBigInteger("comm_id", obj.getId());
				msg = query_post.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				
				
				String hql_post_count = "select count(*) from TB_POSTING_IN_OUT where comm_id=:comm_id";
				Query query_post_count = sessionHQL.createQuery(hql_post_count).setBigInteger("comm_id", obj.getId());
				Long count = (Long) query_post_count.list().get(0);

				if(count == 1)
				{
					String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no,date_of_tos=:date_of_tos,modified_by=:modified_by,modified_date=:modified_date "
							+ "where id=:comm_id and status = '1'  ";
					Query query = sessionHQL.createQuery(hql).setParameter("unit_sus_no", obj.getUnit_sus_no()).setTimestamp("date_of_tos", obj.getDate_of_tos())
							.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
							.setBigInteger("comm_id", obj.getId());
					msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
					
				}
				if(count > 1)
				{
					String hql_post_data = " from TB_POSTING_IN_OUT where comm_id=:comm_id and from_sus_no is not null order by id ";
					Query query_post_data = sessionHQL.createQuery(hql_post_data).setBigInteger("comm_id", obj.getId()).setMaxResults(1);
					@SuppressWarnings("unchecked")
					List<TB_POSTING_IN_OUT> list = (List<TB_POSTING_IN_OUT>) query_post_data.list();
					
					String hql_post_data_u = "update TB_POSTING_IN_OUT set from_sus_no=:unit_sus_no,modified_by=:modified_by,modified_date=:modified_date "
							+ "where id=:id";
					Query query_post_data_u = sessionHQL.createQuery(hql_post_data_u).setParameter("unit_sus_no", obj.getUnit_sus_no())
							.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
							.setInteger("id", list.get(0).getId());
					msg = query_post_data_u.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				
				}
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Data Not Updated.";
				tx.rollback();
			} finally {
				sessionHQL.close();
			}
			return msg;
		}
	
/****************************************** Approval for Unit End ************************************************/
	
/******************************************* APPROVAL & Reject END ****************************************************************/
	
	/********************************************** Edit Start****************************************************************/
	@RequestMapping(value = "/Edit_UpadteComm_DataUrl",method = RequestMethod.POST)
	public ModelAndView Edit_UpadteComm_DataUrl(@ModelAttribute("eid2") BigInteger updateid,
			@ModelAttribute("epersonnel_no2") String personnel_no2,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_S_C_L_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData(updateid);
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData(updateid);
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData(updateid);
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData(updateid);
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData(updateid);
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData(updateid);
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData(updateid);
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData(updateid);
		
		List<TB_CHANGE_OF_RANK> ChangeOfrank= getrankData(updateid);
		
		Mmap.put("msg", msg);
		Mmap.put("id", updateid);
		Mmap.put("personnel_no2", personnel_no2);
		Mmap.put("getCourseNameList", mcommon.getCourseNameList());
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("ChangeOfpr", ChangeOfpr.size());
		Mmap.put("ChangeOfcadet", ChangeOfcadet.size());
		Mmap.put("ChangeOfcourse", ChangeOfcourse.size());
		Mmap.put("ChangeOfgender", ChangeOfgender.size());
		Mmap.put("ChangeOfcommision", ChangeOfcommision.size());
		Mmap.put("ChangeOfdob", ChangeOfdob.size());
		Mmap.put("ChangeOfarmservice", ChangeOfarmservice.size());
		Mmap.put("ChangeOfunit", ChangeOfunit.size());
		Mmap.put("ChangeOfrank", ChangeOfrank.size());
		return new ModelAndView("Edit_Update_Comm_Letter_Tiles");
		
	}
	/********************************************** Edit End****************************************************************/
	
	/********************************************** APPROVE VIEW START ****************************************************************/
	
	@RequestMapping(value = "/view_ApproveUpadteCommDataUrl",method = RequestMethod.POST)
	public ModelAndView view_ApproveUpadteCommDataUrl(@ModelAttribute("ap_id") BigInteger updateid,
			@ModelAttribute("ap_personnel_no") String personnel_no2,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_S_C_L_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Date modifiedDate = getParentModifiedDate_Update_co(updateid);
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = ViewperonelkData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = ViewCadetData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = ViewCourseData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = ViewgenderData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = ViewcommisonData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = ViewBirthData(updateid,modifiedDate);
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = ViewarmserviceData(updateid,modifiedDate);
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = ViewUnitData(updateid,modifiedDate);
		List<TB_CHANGE_OF_RANK > ChangeOfrank = ViewRankData(updateid,modifiedDate);
		
		Mmap.put("msg", msg);
		Mmap.put("id", updateid);
		Mmap.put("personnel_no2", personnel_no2);
		Mmap.put("getCourseNameList", mcommon.getCourseNameList());
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("ChangeOfpr", ChangeOfpr);
		Mmap.put("ChangeOfcadet", ChangeOfcadet);
		Mmap.put("ChangeOfcourse", ChangeOfcourse);
		Mmap.put("ChangeOfgender", ChangeOfgender);
		Mmap.put("ChangeOfcommision", ChangeOfcommision);
		Mmap.put("ChangeOfdob", ChangeOfdob);
		Mmap.put("ChangeOfarmservice", ChangeOfarmservice);
		Mmap.put("ChangeOfunit", ChangeOfunit);
		Mmap.put("ChangeOfrank", ChangeOfrank);
		return new ModelAndView("View_App_Update_Comm_Data_Tiles");
		
	}
	
	@RequestMapping(value = "/admin/getrankData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_RANK > getrankData(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	
		String hqlUpdate = " from TB_CHANGE_OF_RANK  where  status = '0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK > list = (List<TB_CHANGE_OF_RANK >) query.list();
		
		tx.commit();
		sessionHQL.close();
		return list;
	}
    public List<TB_CHANGE_OF_RANK> ViewRankData( BigInteger comm_id,Date modifiedDate) {
					
					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionHQL.beginTransaction();
					String hqlUpdate = " from TB_CHANGE_OF_RANK  where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
					Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
					@SuppressWarnings("unchecked")
					List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
					tx.commit();
					
					sessionHQL.close();
					return list;
		}
	
	public List<TB_PSG_CHANGE_OF_COMISSION> ViewperonelkData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_PSG_UPDATE_COMM_CADET> ViewCadetData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_CADET where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_CADET> list = (List<TB_PSG_UPDATE_COMM_CADET>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_PSG_UPDATE_COMM_COURSE> ViewCourseData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COURSE where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COURSE> list = (List<TB_PSG_UPDATE_COMM_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_PSG_UPDATE_COMM_GENDER> ViewgenderData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_GENDER where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_GENDER> list = (List<TB_PSG_UPDATE_COMM_GENDER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_PSG_UPDATE_COMM_COMMISSION> ViewcommisonData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COMMISSION where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COMMISSION> list = (List<TB_PSG_UPDATE_COMM_COMMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_PSG_UPDATE_COMM_BIRTH> ViewBirthData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_BIRTH where  status = '1' and comm_id=:comm_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_BIRTH> list = (List<TB_PSG_UPDATE_COMM_BIRTH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_INTER_ARM_TRANSFER> ViewarmserviceData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status = '1' and comm_id=:comm_id and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_PSG_UPDATE_COMM_UNIT> ViewUnitData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_UNIT where  status = '1' and comm_id=:comm_id and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_UNIT> list = (List<TB_PSG_UPDATE_COMM_UNIT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	/********************************************** APPROVE VIEW END ****************************************************************/
	

	public Date getParentModifiedDate_Update_co(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_TRANS_PROPOSED_COMM_LETTER where id=:comm_id and status = '1'  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_TRANS_PROPOSED_COMM_LETTER> list = (List<TB_TRANS_PROPOSED_COMM_LETTER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list.get(0).getModified_date();
	}
	
	/********************************************** Reject View Start****************************************************************/
	@RequestMapping(value = "/Reject_UpadteComm_DataUrl",method = RequestMethod.POST)
	public ModelAndView Reject_UpadteComm_DataUrl(@ModelAttribute("rid3") BigInteger updateid,
			@ModelAttribute("rpersonnel_no3") String personnel_no2,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_S_C_L_Url", roleid);
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData3(updateid);
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData3(updateid);
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData3(updateid);
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData3(updateid);
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData3(updateid);
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData3(updateid);
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData3(updateid);
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData3(updateid);
		
		u_id = updateid;
		
		Mmap.put("msg", msg);
		Mmap.put("id", updateid);
		Mmap.put("personnel_no2", personnel_no2);
		Mmap.put("getCourseNameList", mcommon.getCourseNameList());
		Mmap.put("getGenderList", mcommon.getGenderList());
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("ChangeOfpr", ChangeOfpr.size());
		Mmap.put("ChangeOfcadet", ChangeOfcadet.size());
		Mmap.put("ChangeOfcourse", ChangeOfcourse.size());
		Mmap.put("ChangeOfgender", ChangeOfgender.size());
		Mmap.put("ChangeOfcommision", ChangeOfcommision.size());
		Mmap.put("ChangeOfdob", ChangeOfdob.size());
		Mmap.put("ChangeOfarmservice", ChangeOfarmservice.size());
		Mmap.put("ChangeOfunit", ChangeOfunit.size());
		
		return new ModelAndView("Reject_Update_Comm_Letter_Tiles");
		
	}
	
	@RequestMapping(value = "/Submit_Reject_UpadteComm_LetterUrl",method = RequestMethod.POST)
	public ModelAndView Submit_Reject_UpadteComm_LetterUrl(
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();
		
		
		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfpr = getPersonalNOData3((u_id));
		List<TB_PSG_UPDATE_COMM_CADET> ChangeOfcadet = getcadetData3((u_id));
		List<TB_PSG_UPDATE_COMM_COURSE> ChangeOfcourse = getCourseData3((u_id));
		List<TB_PSG_UPDATE_COMM_GENDER> ChangeOfgender = getGenderData3((u_id));
		List<TB_PSG_UPDATE_COMM_COMMISSION> ChangeOfcommision = getcommisonData3((u_id));
		List<TB_PSG_UPDATE_COMM_BIRTH> ChangeOfdob = getdobData3((u_id));
		List<TB_INTER_ARM_TRANSFER> ChangeOfarmservice = getarmsirviceData3((u_id));
		List<TB_PSG_UPDATE_COMM_UNIT> ChangeOfunit = getunitData3((u_id));
		
		if(ChangeOfpr.size() > 0) {
			String hql = "update TB_PSG_CHANGE_OF_COMISSION set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfcadet.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_CADET set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfcourse.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_COURSE set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfgender.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_GENDER set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfcommision.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_COMMISSION set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfdob.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_BIRTH set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfarmservice.size() > 0) {
			String hql = "update TB_INTER_ARM_TRANSFER set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		if(ChangeOfunit.size() > 0) {
			String hql = "update TB_PSG_UPDATE_COMM_UNIT set status=:status,modified_by=:modified_by,modified_date=:modified_date "
					+ " where  comm_id=:id and status=3";
			Query query = sessionHQL.createQuery(hql)
					.setBigInteger("id", (u_id)).setString("modified_by", username)
					.setTimestamp("modified_date", new Date()).setInteger("status", 0);

			msg = query.executeUpdate() > 0 ? "update" : "0";
		}
		mcommon.update_comm_status(u_id, username);
		tx.commit();
		
		return new ModelAndView("Reject_Update_Comm_Letter_Tiles");
		
	}
	
	@RequestMapping(value = "/admin/getPersonalNOData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getPersonalNOData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getcadetData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_CADET> getcadetData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_CADET where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_CADET> list = (List<TB_PSG_UPDATE_COMM_CADET>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getCourseData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COURSE> getCourseData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COURSE where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COURSE> list = (List<TB_PSG_UPDATE_COMM_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getGenderData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_GENDER> getGenderData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_GENDER where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_GENDER> list = (List<TB_PSG_UPDATE_COMM_GENDER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/getcommisonData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_COMMISSION> getcommisonData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_COMMISSION where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_COMMISSION> list = (List<TB_PSG_UPDATE_COMM_COMMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getdobData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_BIRTH> getdobData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_BIRTH where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_BIRTH> list = (List<TB_PSG_UPDATE_COMM_BIRTH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getarmsirviceData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_INTER_ARM_TRANSFER> getarmsirviceData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getunitData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_COMM_UNIT> getunitData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_COMM_UNIT where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_COMM_UNIT> list = (List<TB_PSG_UPDATE_COMM_UNIT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


	/********************************************** Reject view End****************************************************************/
	
	@RequestMapping (value = "/admin/GETtenure_date", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GETtenure_date(ModelMap Mmap, HttpSession session,
			 BigInteger comm_id,HttpServletRequest request) {
		
		ArrayList<ArrayList<String>> list = scldao.gettenuredate(comm_id);
		return list;
	 }
	
	//Bisag 280323 v2(new enhancement  )
	
		public String Update_rankcomm_latter_data(TB_TRANS_PROPOSED_COMM_LETTER obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
			try {
				String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date "
						+ "where id=:comm_id and status = '1'  ";
				Query query = sessionHQL.createQuery(hql).setInteger("rank", obj.getRank()).setTimestamp("date_of_rank", obj.getDate_of_rank())
						.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
						.setBigInteger("comm_id", obj.getId());
				msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Data Not Updated.";
				tx.rollback();
			} finally {
				sessionHQL.close();
			}
			return msg;
		}
	
	
	
		
		public String Update_rankNo(TB_CHANGE_OF_RANK obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
			try {
				String hql1 = "update TB_CHANGE_OF_RANK set status=:status where comm_id=:comm_id and status != '0' ";
				
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", obj.getComm_id());
				
				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				
				String hql = "update TB_CHANGE_OF_RANK  set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  comm_id=:comm_id and status = '0'";
				Query query = sessionHQL.createQuery(hql)
						.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
						.setInteger("status", 1).setBigInteger("comm_id", obj.getComm_id());
				msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Data Not Approve Successfully.";
				tx.rollback();
			} finally {
				sessionHQL.close();
			}
			return msg;
		}
		
	
		@RequestMapping(value = "/admin/get_rank1", method = RequestMethod.POST)
       public @ResponseBody List<TB_CHANGE_OF_RANK> get_rank1(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
			String hqlUpdate = " from TB_CHANGE_OF_RANK where comm_id=:comm_id and status='0'";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
	
   
	
	
	
	
	
///////////////////////////////
	
	
	  @RequestMapping(value = "/admin/Rejectrank", method = RequestMethod.POST)
		public @ResponseBody String Rejectrank(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg)  {
			
			String username = session.getAttribute("username").toString();
			TB_CHANGE_OF_RANK rejectrank = new TB_CHANGE_OF_RANK();
			rejectrank.setComm_id(new BigInteger(request.getParameter("comm_id")));
			rejectrank.setId(Integer.parseInt(request.getParameter("rank_id")));
			
			rejectrank.setReject_remarks(request.getParameter("reject_remarks"));
			String msg1 = update_commrank_Reject(rejectrank, username);

			return msg1;

		}
	  
	    public String update_commrank_Reject(TB_CHANGE_OF_RANK  obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";
		try {
						
				String hql = "update TB_CHANGE_OF_RANK  set reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  comm_id=:comm_id and status = '0' and id=:id ";


				Query query = sessionHQL.createQuery(hql).setString("reject_remarks", obj.getReject_remarks())
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 3)
						.setBigInteger("comm_id", obj.getComm_id())
						.setInteger("id", obj.getId());
			
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				
				
				 } catch (Exception e) { e.printStackTrace(); msg = "Data Not Rejected.";
				  tx.rollback(); } finally { sessionHQL.close(); }
			
				
				
				
				
			return msg;

		}
  
	
	////////////////////////////
	
	
	
	/*@RequestMapping(value = "/admin/getrankData3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_RANK_COMMISION > getrankData3(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_RANK_COMMISION  where  status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_RANK_COMMISION > list = (List<TB_PSG_UPDATE_RANK_COMMISION >) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}*/
	
}
