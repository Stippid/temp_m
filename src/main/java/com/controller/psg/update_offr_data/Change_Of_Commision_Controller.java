package com.controller.psg.update_offr_data;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_Of_Commision_Controller {
	ValidationController validation = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;
	
	@RequestMapping(value = "/admin/getcocData", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getcocData(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("n_id"));
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:id and comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setBigInteger("comm_id", comm_id);
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}
	

	@RequestMapping(value = "/admin/coc_action", method = RequestMethod.POST)
    public @ResponseBody String coc_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
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
            
            BigInteger comm_id = BigInteger.ZERO;
            comm_id = new BigInteger(request.getParameter("comm_id"));
            
            String status = "0";
            
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
            if (mcommon.CompareDate(date_of_authority_dt,comm_date) == 0) {
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
            String persuffix = mcommon.getPersonnelNuSuffix(persnl_no2);
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

            
            String Count = CountOfPersonalNumberUpdateCommissioning(comm_id) ;
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

                    String approoval_status = request.getParameter("app_status");
                    if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
                          
                    }
                    else
                    {
                            mcommon.update_offr_status(Integer.parseInt(census_id),username);
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
	
	//----CHANDANI 16-7

	public String CountOfPersonalNumberUpdateCommissioning(BigInteger comm_id ) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String count = null;
		try {
			Query q1 = sessionHQL.createQuery("select count(comm_id) from TB_PSG_CHANGE_OF_COMISSION where status = '0' and comm_id=:comm_id and census_id = 0 ");			
			q1.setParameter("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();			
			if(list.size() > 0) {
				count = String.valueOf(list.get(0));
			}
			tx.commit();
		} catch (RuntimeException e) {			
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}
	
	@RequestMapping(value = "/admin/getcocDataStatus1", method = RequestMethod.POST)
	public @ResponseBody List<TB_TRANS_PROPOSED_COMM_LETTER> getcocDataStatus1(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		//int id = Integer.parseInt(request.getParameter("n_id"));
		//String id = request.getParameter("comm_id");
		BigInteger id = new BigInteger(request.getParameter("comm_id"));
	
		String hqlUpdate = "select d.comn_name,c.date_of_seniority,c.personnel_no,c.type_of_comm_granted from TB_TRANS_PROPOSED_COMM_LETTER c, TB_COMMISSION_TYPE d where c.type_of_comm_granted=d.id and c.id=:id and c.status='1' and d.status = 'active' order by c.id desc";
		
		Query query = sessionHQL.createQuery(hqlUpdate).setMaxResults(1)
				/*.setInteger("id", Integer.parseInt(id));*/
				.setBigInteger("id",id);
		List<TB_TRANS_PROPOSED_COMM_LETTER> list = (List<TB_TRANS_PROPOSED_COMM_LETTER>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	/*
	 * @RequestMapping(value = "/admin/coc_delete_action", method =
	 * RequestMethod.POST) public @ResponseBody String coc_delete_action(ModelMap
	 * Mmap, HttpSession session, HttpServletRequest request) throws ParseException
	 * { String msg = ""; Session sessionHQL =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx =
	 * sessionHQL.beginTransaction(); int id =
	 * Integer.parseInt(request.getParameter("coc_ch_id")); try { String hqlUpdate =
	 * "delete from TB_PSG_CHANGE_OF_COMISSION where id=:id"; int app =
	 * sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
	 * tx.commit(); sessionHQL.close(); if (app > 0) { msg = "1"; } else { msg =
	 * "0"; } } catch (Exception e) {
	 * 
	 * } return msg; }
	 */
	
	
	
	/*--------------------- For Approval ----------------------------------*/
	
	
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getcocData1(int id, BigInteger comm_id)  {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:census_id and comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_ChangeOfCommision(TB_PSG_CHANGE_OF_COMISSION obj,String username){
		  
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  //String msg1 = "";
		  try{
			   String hql1 = "update TB_PSG_CHANGE_OF_COMISSION set status=:status,previous_commission=:previous_commission where comm_id=:comm_id and (status != '0' and status != '-1' and status = '1') ";
			   
				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
						.setBigInteger("comm_id",obj.getComm_id()).setInteger("previous_commission", obj.getPrevious_commission());
						
				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				
				 String hql2 = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status where comm_id=:comm_id and status != '0' ";
				   
					Query query2 = sessionHQL.createQuery(hql2).setInteger("status", 2)
							.setBigInteger("comm_id",obj.getComm_id());
							
					msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
					
				String hqlsen = "update TB_PSG_CHANGE_OF_SENIORITY set status=:status where comm_id=:comm_id and status = '0' ";
				   
				Query querysen = sessionHQL.createQuery(hqlsen).setInteger("status", 1)
						.setBigInteger("comm_id",obj.getComm_id());
						
				msg = querysen.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
				 
			    String hql = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status,old_personal_no=:old_personal_no,previous_commission=:previous_commission"
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";
			  
				 Query query = sessionHQL.createQuery(hql)
						
						.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
						.setInteger("status", 1).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id()).setString("old_personal_no", obj.getOld_personal_no()).setInteger("previous_commission", obj.getPrevious_commission());
				 msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			   
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
	
    public String Update_Comm_Details(TB_TRANS_PROPOSED_COMM_LETTER obj,String username){
		
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		
		 try{
			 
			 //---------CHANGE ON 17-8-21- CHANDANI   
			 String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set "
						+ "type_of_comm_granted=:type_of_comm_granted,date_of_seniority=:date_of_seniority,personnel_no=:personnel_no"
						+ " where id=:comm_id ";
			  
				 Query query = sessionHQL.createQuery(hql)
						.setBigInteger("comm_id",obj.getId()).setInteger("type_of_comm_granted", obj.getType_of_comm_granted())
						.setDate("date_of_seniority", obj.getDate_of_seniority()).setString("personnel_no", obj.getPersonnel_no());
				 
		  	 /*
			    String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set "
						+ "type_of_comm_granted=:type_of_comm_granted,date_of_seniority=:date_of_seniority,personnel_no=:personnel_no,"
						+ "date_of_commission=:date_of_commission where id=:comm_id ";
			  
				 Query query = sessionHQL.createQuery(hql)
						.setInteger("comm_id",obj.getId()).setInteger("type_of_comm_granted", obj.getType_of_comm_granted())
						.setDate("date_of_seniority", obj.getDate_of_seniority()).setString("personnel_no", obj.getPersonnel_no())
						.setDate("date_of_commission", obj.getDate_of_commission());*/
			
		          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
		          
		    
		          tx.commit();
		  
		  }catch (Exception e) {
		          msg = "Data Not Updated.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;
	
	}
    
/*--------------------- For REJECT ----------------------------------*/
	
	@RequestMapping(value = "/admin/get_CocReject", method = RequestMethod.POST)
	public @ResponseBody String get_CocReject(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		     String username = session.getAttribute("username").toString();
		     TB_PSG_CHANGE_OF_COMISSION ChangeCoc = new TB_PSG_CHANGE_OF_COMISSION();
		     ChangeCoc.setCensus_id(Integer.parseInt(request.getParameter("ch_id")));
		     ChangeCoc.setComm_id(new BigInteger(request.getParameter("comm_id")));
		     ChangeCoc.setId(new BigInteger(request.getParameter("ch_r_id")));
		     ChangeCoc.setReject_remarks(request.getParameter("reject_remarks"));
			 String msg1 = Update_Change_of_CocReject(ChangeCoc, username);
				
			  return msg1;
	      
	}
	public String Update_Change_of_CocReject(TB_PSG_CHANGE_OF_COMISSION obj,String username){
		
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sessionHQL.beginTransaction();
	  
		  String msg = "";
		  //String msg1= "";
		 try{
//			      String hql1 = "update TB_CENSUS_DETAIL_Parent set modified_by=:modified_by,modified_date=:modified_date,"
//						+ "update_ofr_status=:update_ofr_status where id=:census_id and comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";
//			  
//				   Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username).setTimestamp("modified_date", new Date())
//						.setInteger("update_ofr_status", 3).setInteger("census_id", obj.getCensus_id()).setInteger("comm_id",obj.getComm_id());
//						
//				   msg = query1.executeUpdate() > 0 ? "Data Rejected Successfully." :"Data Not Rejected.11111";
		    
		  	 
			      String hql = "update TB_PSG_CHANGE_OF_COMISSION set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks   "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";
			  
				  Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setTimestamp("modified_date", new Date())
						  .setString("reject_remarks", obj.getReject_remarks())
						.setInteger("status", 3).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id())
						.setBigInteger("id", obj.getId());
				  

			      String hqlsen = "update TB_PSG_CHANGE_OF_SENIORITY set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where census_id=:census_id and comm_id=:comm_id and status = '0' and id=:id ";
			  
				  Query querysen = sessionHQL.createQuery(hqlsen).setString("modified_by", username).setTimestamp("modified_date", new Date())
						.setInteger("status", 3).setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id",obj.getComm_id())
						.setBigInteger("id", obj.getId());
				  querysen.executeUpdate();
		          
		          msg = query.executeUpdate() > 0 ? "1" :"0";
				
					 
		          tx.commit();
		  
		  }catch (Exception e) {
		          msg = "Data Not Rejected.";
		          tx.rollback();
		  }
		  finally {
		          sessionHQL.close();
		  }
		  return msg;

	}
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getChangeOfCoc2(int id,BigInteger comm_id){
		
		 
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:census_id and status = '3' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/get_Coc3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> get_Coc3(ModelMap Mmap, HttpSession session,
	                HttpServletRequest request) throws ParseException {
	        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
	        int id = Integer.parseInt(request.getParameter("p_id"));
	        BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
	        String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:census_id and status = '3' and comm_id=:comm_id ";
	        Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id",comm_id);
	        @SuppressWarnings("unchecked")
	        List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
	         tx.commit();
	        sessionHQL.close();
	        return list;
	}
	
	 @RequestMapping(value = "/admin/Coc_delete_action", method = RequestMethod.POST)
		public @ResponseBody String Coc_delete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_PSG_CHANGE_OF_COMISSION where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				sessionHQL.close();
				if (app > 0) {
					msg = "1";
				} else {
					msg = "0";
				}
			} catch (Exception e) {
			}
			return msg;
		}
	 
	 
	 public @ResponseBody List<TB_PSG_CHANGE_OF_COMISSION> getData(BigInteger comm_id)  {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where comm_id=:comm_id and status='1'";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
    
}
