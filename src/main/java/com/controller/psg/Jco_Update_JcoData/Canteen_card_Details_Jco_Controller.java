package com.controller.psg.Jco_Update_JcoData;

import java.math.BigInteger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.models.psg.Jco_Update_JcoData.TB_CANTEEN_CARD_DETAILS1_JCO;

import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Canteen_card_Details_Jco_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController valid = new ValidationController();
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;

	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	

     /******************************Save For Canteen Details***********************************/
	 
	 
	 
	 
	 
	 //**********************************************Update*********************************//
	 	 
		 @RequestMapping(value = "/admin/CSD_action_jco", method = RequestMethod.POST)
			public @ResponseBody String CSD_action_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request)
					throws ParseException {
			 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionhql = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionhql.beginTransaction();
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String name = request.getParameter("name");
				String Sname = request.getParameter("Sname");
				String date_of_birth = request.getParameter("date_of_birth");
				String relation =   request.getParameter("relation");
				String type_of_card = request.getParameter("type_of_card");
				String card_no = request.getParameter("card_no");
				String CSD_Card_ch_id = request.getParameter("CSD_Card_ch_id");
				String jco_id = request.getParameter("jco_id");
				String rvalue = "";
				TB_CANTEEN_CARD_DETAILS1_JCO foregin = new TB_CANTEEN_CARD_DETAILS1_JCO();
				
			
				if (relation == null || relation.equals("0") ) {
					return "Please Select Category";
				}				
				if ((name == null || name.equals("") ) && (Sname.equals("0") )) {
					return "Please Enter Name";
				}
				if (!valid.isOnlyAlphabet(name)) {
			 		return valid.isOnlyAlphabetMSG + " Name";	
				}
				if (date_of_birth == null || date_of_birth.equals("") ) {
					return "Please Select Date of Birth";
				}
				if (!valid.isValidDate(date_of_birth)) {
		 			return valid.isValidDateMSG + " of Birth";	
				}
				
				if (card_no == null || card_no.equals("") ) {
					return "Please Enter Card No";
				}

				try {
					Query q0 = sessionhql.createQuery("select count(id) from TB_CANTEEN_CARD_DETAILS1_JCO where card_no=:card_no  and id !=:id");
					q0.setParameter("card_no", card_no);  
					q0.setParameter("id", Integer.parseInt(CSD_Card_ch_id)); 
						Long c = (Long) q0.uniqueResult();
						
						Query q01 = sessionhql.createQuery("select count(id) from TB_CANTEEN_CARD_DETAILS1_JCO where jco_id=:jco_id   and upper(type_of_card)=:type_of_card and upper(name)=:name and relation=:relation  and id !=:id");
						
						q01.setParameter("id", Integer.parseInt(CSD_Card_ch_id)); 
						q01.setParameter("jco_id", Integer.parseInt(jco_id)); 
						if(name != null && !name.equals("")) {
							q01.setParameter("name", name.toUpperCase()); 
							}
							else if((Sname != null && !Sname.equals("0") &&  !Sname.equals(""))) {
								q01.setParameter("name",Sname.toUpperCase() ); 
							}
						q01.setParameter("relation", Integer.parseInt(relation)); 
						q01.setParameter("type_of_card", type_of_card.toUpperCase()); 
						q01.setParameter("id", Integer.parseInt(CSD_Card_ch_id)); 
						
						Long c1 = (Long) q01.uniqueResult();
						if(c==0 && c1==0) {
					if (Integer.parseInt(CSD_Card_ch_id) == 0) {
						//save
						foregin.setRelation(Integer.parseInt(relation));
						foregin.setJco_id(Integer.parseInt(jco_id));
						if(name != null && !name.equals("")) {
						foregin.setName(name);
						}
						else if(Sname!=null && !Sname.equals("0") && !Sname.equals("")) {
							foregin.setName(Sname);
						}
						foregin.setType_of_card(type_of_card);
						foregin.setDate_of_birth(format.parse(date_of_birth));
						foregin.setCard_no(card_no);
						foregin.setStatus(0);
						foregin.setCreated_by(username);
						foregin.setCreated_date(date);
						foregin.setInitiated_from("u");
						int id = (int) sessionhql.save(foregin);
						rvalue = Integer.toString(id);
						
					} else {
						//update
						
						String hql = " update TB_CANTEEN_CARD_DETAILS1_JCO set name=:name,date_of_birth=:date_of_birth,relation=:relation,type_of_card=:type_of_card,"
								+ "card_no=:card_no,modified_by=:modified_by,modified_date=:modified_date,status='0'"
								+ " where  id=:id";
						Query query = sessionhql.createQuery(hql)
								
								.setInteger("relation",Integer.parseInt(request.getParameter("relation")));
								if(name != null && !name.equals("")) {
									query.setString("name", name);
									}
								else if(Sname!=null && !Sname.equals("0") && !Sname.equals("") ) {
										query.setString("name", Sname);
									}
								query.setString("card_no",card_no)
								.setTimestamp("date_of_birth", format.parse(date_of_birth))
								.setString("type_of_card", type_of_card)
								.setString("modified_by", username)
								.setTimestamp("modified_date", new Date())
								
								.setInteger("id",Integer.parseInt(CSD_Card_ch_id));
											
						rvalue = query.executeUpdate() > 0 ? "update" : "0";
					}
					String approoval_status = request.getParameter("app_status");
					if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
						
					}
					else
					{
						pjc.update_JcoOr_status(Integer.parseInt(jco_id),username);
					}
					
					tx.commit();
				}
				else {rvalue="Data Alredy Exist";}}
				catch (RuntimeException e) {
					e.printStackTrace();
					try {
						tx.rollback();
						rvalue = "0";
					} catch (RuntimeException rbe) {
						rvalue = "0";
					}
				} finally {
					if (sessionhql != null) {
						sessionhql.close();
					}
				}
				return rvalue;
			}
			@RequestMapping(value = "/admin/CSDCard_delete_action_jco", method = RequestMethod.POST)
			public @ResponseBody String CSDCard_delete_action_jco(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("CSD_Card_ch_id"));
				try {
					String hqlUpdate = "delete from TB_CANTEEN_CARD_DETAILS1_JCO where id=:id";
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
			
			
			@RequestMapping(value = "/admin/updateCSD_getData_jco", method = RequestMethod.POST)
			public @ResponseBody List<TB_CANTEEN_CARD_DETAILS1_JCO> updateCSD_getData_jco(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String jco_id = request.getParameter("jco_id");
				String hqlUpdate = " from TB_CANTEEN_CARD_DETAILS1_JCO where jco_id=:jco_id and  status= '0' order by id";
				Query query = sessionHQL.createQuery(hqlUpdate).setParameter("jco_id",Integer.parseInt(jco_id));
				List<TB_CANTEEN_CARD_DETAILS1_JCO> list = (List<TB_CANTEEN_CARD_DETAILS1_JCO>) query.list();
				tx.commit();
				sessionHQL.close();
				
				return list;
			}
			
			public @ResponseBody List<TB_CANTEEN_CARD_DETAILS1_JCO> CSD_GetData1( int jco_id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = "from TB_CANTEEN_CARD_DETAILS1_JCO where  jco_id=:jco_id and status = '0'";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
				@SuppressWarnings("unchecked")
				List<TB_CANTEEN_CARD_DETAILS1_JCO> list = (List<TB_CANTEEN_CARD_DETAILS1_JCO>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}
			
			public String Update_CSD_details(TB_CANTEEN_CARD_DETAILS1_JCO obj, String username) {

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();

				String msg = "";
				String msg1 = "";
				try {

					String hql1 = "update TB_CANTEEN_CARD_DETAILS1_JCO set status=:status where  jco_id=:jco_id and status != '0' and status != '-1' ";

					Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
							.setInteger("jco_id", obj.getJco_id());

					msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

					String hql = "update TB_CANTEEN_CARD_DETAILS1_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
							+ " where  jco_id=:jco_id and status = '0'";

					Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
							.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
							.setInteger("jco_id", obj.getJco_id());

					msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

					tx.commit();

				} catch (Exception e) {
					msg = "Data Not Approve Successfully.";
					tx.rollback();
				} finally {
					sessionHQL.close();
				}
				return msg;

			}
			/*--------------------- For REJECT ----------------------------------*/

			@RequestMapping(value = "/admin/getCSD_Reject_jco", method = RequestMethod.POST)
			public @ResponseBody String getCSD_Reject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) throws ParseException {

				String reject_remarks = request.getParameter("reject_remarks");

				
				String username = session.getAttribute("username").toString();
				TB_CANTEEN_CARD_DETAILS1_JCO csdr = new TB_CANTEEN_CARD_DETAILS1_JCO();
				csdr.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
				csdr.setReject_remarks(reject_remarks);

				String msg1 = CSDr_Reject(csdr, username);

				return msg1;

			}

			public String CSDr_Reject(TB_CANTEEN_CARD_DETAILS1_JCO obj, String username) {

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();

				String msg = "";
				String msg1 = "";
				try {
					

					String hql = "update TB_CANTEEN_CARD_DETAILS1_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
							+ " where  jco_id=:jco_id and status = '0' ";

					Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 3)
							.setString("reject_remarks", obj.getReject_remarks())

							.setInteger("jco_id", obj.getJco_id());

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

			public @ResponseBody List<TB_CANTEEN_CARD_DETAILS1_JCO> getCSD_detailsData2(int jco_id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = " from TB_CANTEEN_CARD_DETAILS1_JCO where  status='3' and jco_id=:jco_id order by id";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
				@SuppressWarnings("unchecked")
				List<TB_CANTEEN_CARD_DETAILS1_JCO> list = (List<TB_CANTEEN_CARD_DETAILS1_JCO>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}

			@RequestMapping(value = "/admin/getCSD_Data3_jco", method = RequestMethod.POST)
			public @ResponseBody List<TB_CANTEEN_CARD_DETAILS1_JCO> getCSD_Data3_jco(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int jco_id = Integer.parseInt(request.getParameter("jco_id"));
				String hqlUpdate = " from TB_CANTEEN_CARD_DETAILS1_JCO where  status = '3' and jco_id=:jco_id order by id ";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
				@SuppressWarnings("unchecked")
				List<TB_CANTEEN_CARD_DETAILS1_JCO> list = (List<TB_CANTEEN_CARD_DETAILS1_JCO>) query.list();
				tx.commit();
				sessionHQL.close();
				
				return list;
			}
			
			
			@RequestMapping(value = "/getSelfMotFatName_jco", method = RequestMethod.POST) //For Encryption with & without Disease Name Fetch
			public @ResponseBody ArrayList<ArrayList<String>> getSelfMotFatName_jco(String jco_id,HttpSession s1) {

				return UJD.getSelfMotFatName(jco_id);

			}
			
			@RequestMapping(value = "/getSpouseName_jco", method = RequestMethod.POST) //For Encryption with & without Disease Name Fetch
			public @ResponseBody ArrayList<ArrayList<String>> getSpouseName_jco(String jco_id,HttpSession s1) {

				return UJD.getSpouseName(jco_id);

			}
			
			@RequestMapping(value = "/getChildName_jco", method = RequestMethod.POST) //For Encryption with & without Disease Name Fetch
			public @ResponseBody ArrayList<ArrayList<String>> getChildName_jco(String jco_id,String rela, HttpSession s1) {

				return UJD.getChildName(jco_id,rela);

			}
			
			@RequestMapping(value = "/getChilddob_jco", method = RequestMethod.POST) //For Encryption with & without Disease Name Fetch
			public @ResponseBody ArrayList<ArrayList<String>> getChilddob_jco(String id,String jco_id, HttpSession s1) {

				return UJD.getChilddob(id,jco_id);

			}

			
			
}
