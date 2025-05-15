package com.controller.psg.update_3008;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_Officer_3008_mns {
	@Autowired

	Search_UpdateOffDataDao UOD;
		
	@Autowired
	CensusAprovedDAO censusAprovedDAO;

	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	Psg_CommonController common = new Psg_CommonController();
	ValidationController validation = new ValidationController();
	
    
	
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;
	
	
	
	@RequestMapping(value = "/admin/Update_officier_3008_mns", method = RequestMethod.POST)
	public ModelAndView Update_officier_3008_mns(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_id3", required = false) BigInteger comm_id, HttpServletRequest request) {
	
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getPersonalType", common.getPersonalType());
		Mmap.put("getPersonalRemainder", common.getPersonalRemainder());
		Mmap.put("comm_id", comm_id);
		Mmap.put("msg", msg);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			String hql6 = "select TO_CHAR(comm.date_of_commission, 'DD/MM/YYYY') AS date_of_commission,personnel_no,unit_sus_no,parent_arm,regiment   from TB_TRANS_PROPOSED_COMM_LETTER comm "
					+ "where  comm.id=:comm_id and comm.status in('1','5')";
			Query query6 = sessionhql.createQuery(hql6);
			query6.setBigInteger("comm_id", comm_id);
			List<Object[]> list6 = query6.list();
			if (!list6.isEmpty()) {
				Mmap.put("comm_date", list6.get(0)[0]);
				Mmap.put("personnel_no", list6.get(0)[1]);
				Mmap.put("parent_arm", list6.get(0)[3]);
				Mmap.put("regiment", list6.get(0)[4]);
				String hql7 = "	select  TO_CHAR(post.dt_of_tos, 'DD/MM/YYYY') as dt_of_tos  from TB_POSTING_IN_OUT post where post.comm_id =:comm_id  and post.to_sus_no = :unit_sus_no and post.status='1' order by post.id	";
				Query query7 = sessionhql.createQuery(hql7).setParameter("comm_id", comm_id)
						.setParameter("unit_sus_no", list6.get(0)[2]).setMaxResults(1);
				query7.setBigInteger("comm_id", comm_id);
				List<String> list7 = query7.list();
				if (!list7.isEmpty()) {
					Mmap.put("tos_date", list7.get(0));
				}
				Mmap.put("census_id", get_Censusid(comm_id));

			}

		} catch (RuntimeException e) {
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

		Mmap.put("getRelationList", common.getRelationList());
		Mmap.put("getReligionList", common.getReligionList());
		Mmap.put("getSeconded_To", common.getSeconded_To());
		Mmap.put("getTypeofRankList", common.getrank_list("mns"));
		Mmap.put("getNon_EffectiveList", common.getNon_EffectiveList());
		Mmap.put("getLanguageList", common.getLanguageList());
		Mmap.put("getMothertoungeList", common.getMothertoungeList());
		Mmap.put("getLanguageSTDList", common.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", common.getLanguagePROOFList());
		Mmap.put("getMedCountryName", common.getMedCountryName("", session));
		Mmap.put("getParentArmList", common.getParentArmList());
		Mmap.put("getRegtList", common.getRegtList(""));
		Mmap.put("getForiegnCountryList", common.getForeign_country());
		Mmap.put("getArmyCourseInstitute", common.getArmyCourseInstitute("1"));
		Mmap.put("getTypeOfCommissionList", common.getTypeOfCommissionList());
		Mmap.put("getSpecializationList", common.getSpecializationList());
		Mmap.put("getQualificationTypeList", common.getQualificationTypeList());
		Mmap.put("getInstituteNameList", common.getInstituteNameList());
		Mmap.put("getDivclass", common.getDivclass());
		Mmap.put("getSubject", common.getSubject());
		Mmap.put("getExamination", common.getExamination());
		Mmap.put("getMedalList", common.getMedalList());
		Mmap.put("getTypeofAppointementList", common.getTypeofAppointementListMns());
		Mmap.put("getMarital_eventList", common.getMarital_eventList());
		Mmap.put("getMarital_statusList", common.getMarital_statusList());
		Mmap.put("getNationalityList", common.getNationalityList());
		Mmap.put("getFiring_event_qe", common.getFiring_event_qe());
		Mmap.put("getBPET_event_qe", common.getBPET_event_qe());
		Mmap.put("getFiring_grade", common.getFiring_grade());
		Mmap.put("getBPET_result", common.getBPET_result());
		Mmap.put("getHair_ColourList", common.getHair_ColourList());
		Mmap.put("getEye_ColourList", common.getEye_ColourList());
		Mmap.put("getFamily_siblings", common.getFamily_siblings());
		Mmap.put("getIndianLanguageList", common.getIndianLanguageList());
		Mmap.put("getForiegnLangugeList", common.getForiegnLangugeList());
		Mmap.put("getOFFR_Non_EffectiveList", common.getOFFR_Non_EffectiveList(""));		
		Mmap.put("getPersonalRemainder", common.getPersonalRemainder());
		Mmap.put("getOFFTypeofRankList", common.getOFFTypeofRankList());
		Mmap.put("getChild_RelationshipList", common.getChild_RelationshipList());
		Mmap.put("getChild_TypeList", common.getChild_TypeList());
		Mmap.put("getShapeStatusList", common.getShapeStatusList());
		Mmap.put("getcCopeList", common.getCopeValueList("C"));
		Mmap.put("getoCopeList", common.getCopeValueList("O"));
		Mmap.put("getpCopeList", common.getCopeValueList("P"));
		Mmap.put("geteCopeList", common.getCopeValueList("E"));
		Mmap.put("getesubCopeList", common.getCopeValueList("E Sub Value"));
		Mmap.put("getOprationList", common.getOprationList());
		Mmap.put("getDivclass", common.getDivclass());
		Mmap.put("getQualificationTypeList", common.getQualificationTypeList());
		Mmap.put("getExaminationTypeList", common.getExaminationTypeList());
		Mmap.put("getCourseName", common.getCourseName());
		Mmap.put("getFdService", common.getFdService());
		Mmap.put("getCourseTypeList", common.getCourseTypeList());
		Mmap.put("getExamList", common.getExamList());
		Mmap.put("getdisciplinetypeentry", common.getdisciplinetypeentry());
		Mmap.put("getDclredRcvrdList", common.getDclredRcvrdList());
		Mmap.put("getCauseOfDeserter", common.getCauseOfDeserter());
		Mmap.put("getARM_TYPE", common.getARM_TYPE());
		Mmap.put("getExservicemenList", common.getExservicemenList());
		Mmap.put("getCSDCategoryList", common.getCSDCategoryList());
		Mmap.put("getMissingList", common.getMissingList());
		Mmap.put("getCausesOfCasuality", common.getCausesOfCasuality());
		Mmap.put("getDisc_Trialed", common.getDisc_Trialed());
		Mmap.put("getArmyAct_Sec", common.getArmyAct_Sec());
		Mmap.put("getSub_Clause", common.getSub_Clause());
		Mmap.put("gettastatusList", common.getstatusList());
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("getMarital_statusList", common.getMarital_statusList());
		 Mmap.put("getProfession", common.getProfession());
		return new ModelAndView("update_officier_3008MNS_Tiles");

	}
	 
	 
	 
		public int get_Censusid(BigInteger comm_id)
		{
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			String hql1 = "select id from TB_CENSUS_DETAIL_Parent where comm_id= :comm_id ";
			Query query1 = sessionhql.createQuery(hql1);
			query1.setParameter("comm_id",comm_id);
			List <Integer>list6 = query1.list();
			
			int census_id=0;
			if(!list6.isEmpty())
			{
				 census_id=list6.get(0);
			}
			
			return  census_id;
		}
		
		
		@RequestMapping(value = "/admin/coc_3008action_mns", method = RequestMethod.POST)
	    public @ResponseBody String coc_3008action_mns(ModelMap Mmap, HttpSession session, HttpServletRequest request)
	                    throws ParseException,Exception {
	            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	            Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionhql.beginTransaction();
	            Date date = new Date();
	            Date date_of_authority_dt = null;
	            Date date_of_seniority_dt = null;
	            Date eff_date_of_seniority_dt = null;
	            Date date_of_permanent_commission_dt = null;

	            String username = session.getAttribute("username").toString();
	    
	            String authority = request.getParameter("authority");
	           // String previous_commission = request.getParameter("previous_commission");       
	            //String old_personal_no = request.getParameter("old_personal_no");
	            String type_of_commission_granted = request.getParameter("type_of_commission_granted");
	            
	            String census_id = request.getParameter("cen_id");
	            if (census_id == null || census_id.trim().isEmpty() || census_id.equals("undefined")) {
	                census_id = "0"; 
	            }
	            
	            BigInteger comm_id = BigInteger.ZERO;
	            comm_id = new BigInteger(request.getParameter("comm_id"));
	            
	            String status = "0";
	            String persnl_noold = request.getParameter("old_personal_no");
	            String persnl_no1 = request.getParameter("persnl_no1");
			    String persnl_no2 = request.getParameter("persnl_no2");
			    String persnl_no3 = request.getParameter("persnl_no3");
			    Date comm_date = format.parse(request.getParameter("comm_date"));
			    String persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
	            
	            String date_of_authority = request.getParameter("date_of_authority");
	            
	            String date_of_permanent_commission = request.getParameter("date_of_permanent_commission");
	             
	            String date_of_seniority = request.getParameter("date_of_seniority");
	            String eff_date_of_seniority = request.getParameter("eff_date_of_seniority");
	                

	            BigInteger coc_ch_id = new BigInteger(request.getParameter("coc_ch_id"));

	            String msg = "";
	            if (authority.equals("")) {
	                return "Please Enter Authority";
	            } 
	            if (!validation.isValidAuth(authority)) {
	    			return validation.isValidAuthMSG + " Authority";	
	    		}
	     		if (!validation.isvalidLength(authority,validation.authorityMax,validation.authorityMin)) {
	     			return "Authority " + validation.isValidLengthMSG;	
	    		}	
	            if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
	                return "Please Select Date of Authority";
	            }
	            if (!validation.isValidDate(date_of_authority)) {
	    		  	return  validation.isValidDateMSG + " of Authority";	
	    		}
	            if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
	                date_of_authority_dt = format.parse(date_of_authority);
	            }
	            if (common.CompareDate(date_of_authority_dt,comm_date) == 0) {
	                return "Authority Date should be Greater than Commission Date";
	            }
	            if (request.getParameter("persnl_no1") == null || request.getParameter("persnl_no1").equals("-1")
	                            || request.getParameter("persnl_no1").equals("0")) {
	                return "Please Select Personal No";
	            }
	            if (request.getParameter("persnl_no2") == null || request.getParameter("persnl_no2").trim().equals("")) {
	                return "Please Enter Personal No";
	            }
	            if (request.getParameter("persnl_no2").length() != 5) {
	                return "Please Enter Valid Personal No";
	            }
	            String persuffix = common.getPersonnelNuSuffix(persnl_no2);
	            if (!persnl_no3.equals(persuffix)) {
	                return "Please Enter Valid Personal No";
	            }
	            if (type_of_commission_granted.equals("0")) {
	                return "Please Select Type of Commission Granted";
	            } 
	            if (date_of_permanent_commission == null || date_of_permanent_commission.equals("null") || date_of_permanent_commission.equals("DD/MM/YYYY") || date_of_permanent_commission.equals("")) {
	                return "Please Enter Permanent Commission Date";
	            } 
	            if (!validation.isValidDate(date_of_permanent_commission)) {
	    		  	return  validation.isValidDateMSG + " of  Permanent Commission";	
	    		}
	            if (!date_of_permanent_commission.equals("") && !date_of_permanent_commission.equals("DD/MM/YYYY")) {
	                date_of_permanent_commission_dt = format.parse(date_of_permanent_commission);
	            }
	            if (date_of_seniority == null || date_of_seniority.equals("null") || date_of_seniority.equals("DD/MM/YYYY") || date_of_seniority.equals("")) {
	                return "Please Enter Seniority Date";
	            } 
	            if (!validation.isValidDate(date_of_seniority)) {
	    		  	return  validation.isValidDateMSG + " of  Seniority";	
	    		}
	            if (!date_of_seniority.equals("") && !date_of_seniority.equals("DD/MM/YYYY")) {
	                date_of_seniority_dt = format.parse(date_of_seniority);
	            }
	            if (eff_date_of_seniority == null || eff_date_of_seniority.equals("null") || eff_date_of_seniority.equals("DD/MM/YYYY") || date_of_seniority.equals("")) {
	               return "Please Enter Effective Seniority Date";
	            }
	            if (!validation.isValidDate(eff_date_of_seniority)) {
	    		  	return  validation.isValidDateMSG + " of Effective Seniority";	
	    		}
	            if (!eff_date_of_seniority.equals("") && !eff_date_of_seniority.equals("DD/MM/YYYY")) {
	                eff_date_of_seniority_dt = format.parse(eff_date_of_seniority);
	            }

	            
	            String Count = "";
	            Query q1 = sessionhql.createQuery("select count(comm_id) from TB_PSG_CHANGE_OF_COMISSION where status = '0' and comm_id=:comm_id and census_id = 0 ");			
				q1.setParameter("comm_id", comm_id);
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();			
				if(list.size() > 0) {
					Count = String.valueOf(list.get(0));
				}
	        	if(Integer.parseInt(Count) > 0) {
	        		return "Data is Already Exists in Pending Status of Personnel No(Update Commissioning) Please Approve that Data First.";
	    		}
	        	Boolean d = SLDAO.getPersonnelNoAlreadyExist(persnl_no,coc_ch_id);
	            try {
	                    String hqlUpdate_s="select id from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status=0 ";
	                    Query query_s = sessionhql.createQuery(hqlUpdate_s).setBigInteger("comm_id", comm_id).setMaxResults(1);
	                    Integer c2 = (Integer)  query_s.uniqueResult();
	                    if(coc_ch_id == BigInteger.ZERO && c2!=null && c2>0) {
	                        return "Date of Seniority Is Already In Pending State. Please Approve That Record First ";
	                    }
	                    
	                    String hqlUpdate_s2="select count(id) from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status in (1,2) and applicablity_date=:eff_date_of_seniority";
	                    Query query_s2 = sessionhql.createQuery(hqlUpdate_s2).setBigInteger("comm_id", comm_id)
	                                    .setTimestamp("eff_date_of_seniority", eff_date_of_seniority_dt).setMaxResults(1);
	                    Long c22 = (Long)  query_s2.uniqueResult();
	                    if(c22!=null && c22>0) {
	                        return " Effective Date of Seniority Is Already Exist. ";
	                    }
	                            
	                    else {
	                    if (coc_ch_id == BigInteger.ZERO || request.getParameter("coc_ch_id").equals("0") ) {
	                    	
	                    	if(Integer.parseInt(Count) == 0) {
	                            TB_PSG_CHANGE_OF_COMISSION coc = new TB_PSG_CHANGE_OF_COMISSION();
	                            coc.setAuthority(authority);
	                            coc.setDate_of_authority(date_of_authority_dt);
	                            coc.setNew_personal_no(persnl_no);
	                            /*coc.setPrevious_commission(Integer.parseInt(previous_commission));
	                            coc.setOld_personal_no(old_personal_no);*/
	                            coc.setType_of_commission_granted(Integer.parseInt(type_of_commission_granted));
	                            coc.setCensus_id(Integer.parseInt(census_id));
	                            coc.setComm_id(comm_id);
	                            coc.setStatus(0);
	                            coc.setDate_of_permanent_commission(date_of_permanent_commission_dt);
	                            coc.setDate_of_seniority(date_of_seniority_dt);
	                            coc.setCreated_by(username);
	                            coc.setCreated_date(date);
	                            coc.setEff_date_of_seniority(eff_date_of_seniority_dt);
	                            
	                              TB_PSG_CHANGE_OF_SENIORITY sen = new TB_PSG_CHANGE_OF_SENIORITY();
	                               sen.setDate_of_seniority(date_of_seniority_dt);
	                               sen.setAuthority(authority);
	                               sen.setDate_of_authority(date_of_authority_dt);
	                               sen.setStatus(0);
	                               sen.setCreated_by(username);
	                               sen.setCreated_date(date);
	                               sen.setComm_id(comm_id);
	                               sen.setCensus_id(Integer.parseInt(census_id));
	                               sen.setApplicablity_date(eff_date_of_seniority_dt);
	                              
	                            
	                        	if(d == false) {
	     							return "Personal No Already Exist.";
	     						}
	     						if(d == true) {
	     							 sessionhql.save(sen);

	                                 BigInteger id = (BigInteger) sessionhql.save(coc);
	                                 msg = String.valueOf(id);
	     						}
	                            
	                    	}
	                    } else {
	                            
	                            String hql = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by ,modified_date=:modified_date, "
	                                            + "authority=:authority, date_of_authority=:date_of_authority,new_personal_no=:new_personal_no, "
	                                            + "date_of_permanent_commission=:date_of_permanent_commission,date_of_seniority=:date_of_seniority, "
	                                            + "type_of_commission_granted=:type_of_commission_granted,status=:status"
	                                            + ",eff_date_of_seniority=:eff_date_of_seniority where  id=:id";
	                            Query query = sessionhql.createQuery(hql).setString("authority", authority).setTimestamp("date_of_authority", date_of_authority_dt)
	                                            .setParameter("new_personal_no", persnl_no).setTimestamp("date_of_permanent_commission", date_of_permanent_commission_dt)
	                                            .setTimestamp("date_of_seniority", date_of_seniority_dt)
	                                            .setParameter("type_of_commission_granted",Integer.parseInt(type_of_commission_granted)).setParameter("status", 0)                                                
	                                            .setTimestamp("eff_date_of_seniority", eff_date_of_seniority_dt).setBigInteger("id", coc_ch_id).setString("modified_by", username).setTimestamp("modified_date", new Date());

	                           
	                            
	                            String hql1 = "update TB_PSG_CHANGE_OF_SENIORITY set authority=:authority,date_of_authority=:date_of_authority,applicablity_date=:applicablity_date,"
	                                            + "date_of_seniority=:date_of_seniority,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
	                                            + " where  comm_id=:comm_id and status = 0 ";
	                            Query query1 = sessionhql.createQuery(hql1).setString("authority", authority).setDate("date_of_authority",date_of_authority_dt)
	                                            .setDate("applicablity_date", eff_date_of_seniority_dt)
	                                            .setDate("date_of_seniority",date_of_seniority_dt).setBigInteger("comm_id", comm_id)
	                                            .setString("modified_by", username)
	                                            .setTimestamp("modified_date", new Date()).setInteger("status", 0);

	                            
	                            
	                            if(d == false) {
	        						return "Personal No Already Exist.";
	        					}
	        					if(d == true) {
	        						 msg = query.executeUpdate() > 0 ? "update" : "0";
	        						 msg = query1.executeUpdate() > 0 ? "update" : "0";
	        					}
	                    }
	                    }
	            		
	            		
	            		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:census_id and comm_id=:comm_id and status='0'";
	            		Query query = sessionhql.createQuery(hqlUpdate).setInteger("census_id", Integer.parseInt(census_id)).setBigInteger("comm_id", comm_id);
	            		@SuppressWarnings("unchecked")
	            		List<TB_PSG_CHANGE_OF_COMISSION> ChangeOfComm = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
	        			if (ChangeOfComm.size() > 0) {

	        				 String hql1 = "update TB_PSG_CHANGE_OF_COMISSION set status=:status,previous_commission=:previous_commission where comm_id=:comm_id and (status != '0' and status != '-1' and status = '1') ";
	        				   
	        					Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2)
	        							.setBigInteger("comm_id",comm_id).setInteger("previous_commission", Integer.parseInt(request.getParameter("previous_commission")));
	        							
	        					msg = query1.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
	        					
	        					 String hql2 = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status where comm_id=:comm_id and status != '0' ";
	        					   
	        						Query query2 = sessionhql.createQuery(hql2).setInteger("status", 2)
	        								.setBigInteger("comm_id",comm_id);
	        								
	        						msg = query2.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
	        						
	        					String hqlsen = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status where comm_id=:comm_id and status = '0' ";
	        					   
	        					Query querysen = sessionhql.createQuery(hqlsen).setInteger("status", 1)
	        							.setBigInteger("comm_id",comm_id);
	        							
	        					msg = querysen.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
	        					 
	        				    String hql5 = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status,old_personal_no=:old_personal_no,previous_commission=:previous_commission"
	        							+ " where census_id=:census_id and comm_id=:comm_id and status = 0";
	        				  String previous_commission=request.getParameter("previous_commission");
								Query query5 = sessionhql.createQuery(hql5)

										.setString("modified_by", username).setTimestamp("modified_date", new Date())
										.setInteger("status", 1).setInteger("census_id", Integer.parseInt(census_id))
										.setBigInteger("comm_id", comm_id)
										.setString("old_personal_no", persnl_noold)
										.setInteger("previous_commission",Integer.parseInt(previous_commission));
								msg = query5.executeUpdate() > 0 ? "Data Approved Successfully": "Data Not Approved Successfully";

	        				if (date_of_seniority != "") {

	        					date_of_seniority_dt = format.parse(date_of_seniority);

	        				}

	        				
	        				if (date_of_permanent_commission != "") {

	        					date_of_permanent_commission_dt = format.parse(date_of_permanent_commission);

	        				}

	        					String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set "
	        							+ "type_of_comm_granted=:type_of_comm_granted,date_of_seniority=:date_of_seniority,personnel_no=:personnel_no"
	        							+ " where id=:comm_id ";
	        				  
	        					 Query query6 = sessionhql.createQuery(hql)
	        							.setBigInteger("comm_id",comm_id).setInteger("type_of_comm_granted", Integer.parseInt(type_of_commission_granted))
	        							.setDate("date_of_seniority",date_of_seniority_dt).setString("personnel_no", persnl_no);
	        			
	        				
	        			          msg = query6.executeUpdate() > 0 ? "Data Approved Successfully." :"Data Approved Not Successfully.";


	        			

	        			}	        		
	  			    int censusid = Integer.parseInt(census_id);
	  				if(censusid>0) {
	  					  int count1 = censusAprovedDAO.checkdatapendingintable(comm_id,"change_of_comission");
	  				    
	  				      
	  				      if(count1 == 0) {

	  							 String hql1 = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
	  									+ " where comm_id=:comm_id ";
	  			 	        	 Query query1 =sessionhql.createQuery(hql1).setBigInteger("comm_id", comm_id).setInteger("update_ofr_status", 1);			    		
	  			 	           	 msg = query1.executeUpdate() > 0 ? "Data Approved Successfully." : "Data Not Approved Successfully";
	  				      }
	  				}

	                    
	                    tx.commit();
	            } catch (RuntimeException e) {
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
		
		
		
	


	
}
