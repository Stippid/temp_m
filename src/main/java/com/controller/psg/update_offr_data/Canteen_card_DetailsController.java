package com.controller.psg.update_offr_data;

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

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.Search_Canteen_DAOImpl;
import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Canteen_card_DetailsController {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	
	ValidationController valid = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired	
	private Search_Canteen_DAOImpl card_dao;
	 @RequestMapping(value = "/Canteen_DetailsURL", method = RequestMethod.GET)
	 public ModelAndView Canteen_DetailsURL(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Canteen_DetailsURL", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
		 Mmap.put("msg", msg);
		 Mmap.put("getCSDCategoryList", pcommon.getCSDCategoryList());
		// Mmap.put("getpersonnel_noList", pcommon.getpersonnel_noListApproved_JCOS(personel_no, sessionUserId));
		 //Mmap.put("getExservicemenList", pcommon.getExservicemenList());
		 //Mmap.put("getServiceCategoryList", pcommon.getServiceCategoryList());
		
		 return new ModelAndView("canteen_DetailsTiles");
		
	 }

     /******************************Save For Canteen Details***********************************/
	 
	 @RequestMapping(value = "/Canteen_Detail_Action", method = RequestMethod.POST)
		public ModelAndView Canteen_Detail_Action(@ModelAttribute("Canteen_DetailCMD") TB_PSG_CANTEEN_CARD_DETAIL1 card, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session,String msg) throws ParseException {
		   
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		    DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			int id  = card.getId() > 0 ? card.getId() : 0;
			
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			Date birth_dt = null;

			String birth_date = request.getParameter("date_of_birth");
			
			if (birth_date != "") {
				birth_dt = format1.parse(birth_date);
			}
			String name = request.getParameter("name");
			String relation = request.getParameter("relation");
			String type_of_card = request.getParameter("type_of_card");
			String card_no = request.getParameter("card_no");
			String service = request.getParameter("service");
			String service_category = request.getParameter("service_category");
			
			try{
				
				Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_CANTEEN_CARD_DETAIL1 where card_no=:card_no   and id !=:id");
				q0.setParameter("card_no", card.getCard_no());  
				
				q0.setParameter("id",id); 
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					card.setName(name);
					card.setRelation(Integer.parseInt(relation));
					card.setType_of_card(type_of_card);
					card.setCard_no(card_no);
					card.setDate_of_birth(birth_dt);
					card.setCreated_by(username);
					card.setCreated_date(date);
				    card.setStatus(0);
					      
					if (c == 0) {
						sessionHQL.save(card);
						sessionHQL.flush();
						sessionHQL.clear();
						Mmap.put("msg", "Data Saved Successfully.");

					} else {
						Mmap.put("msg", "Data already Exist.");
					}
				}
				tx.commit();
			}catch(RuntimeException e){
				try{
					tx.rollback();
					Mmap.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					Mmap.put("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			
		
		return new ModelAndView("redirect:Canteen_DetailsURL");
		}
	 
	 
	 
	 
	 //**********************************************Update*********************************//
	 
		@RequestMapping(value = "/Edit_Canteen_card_Details", method = RequestMethod.POST)
		public ModelAndView Edit_Canteen_card_Details(@ModelAttribute("updateid") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			
			TB_PSG_CANTEEN_CARD_DETAIL1 cardDetails = card_dao.getcanteen_detailsByid(Integer.parseInt(updateid));
				 Mmap.put("Edit_Canteen_DetailCMD", cardDetails);
				 Mmap.put("msg", msg);
				 Mmap.put("getExservicemenList", pcommon.getExservicemenList());
				 Mmap.put("getRelationList", pcommon.getRelationList());
				 Mmap.put("getServiceCategoryList", pcommon.getServiceCategoryList());
			return new ModelAndView("Edit_canteen_DetailsTiles");
		}
		
	
		
		 
		@RequestMapping(value = "/Edit_Canteen_Detail_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Canteen_Detail_Action(@ModelAttribute("Edit_Canteen_DetailCMD") TB_PSG_CANTEEN_CARD_DETAIL1 card,
				HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String username = session.getAttribute("username").toString();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			int id = Integer.parseInt(request.getParameter("id"));

			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	       /* Date birth_dt = null;
	        String birth_date = request.getParameter("date_of_birth");
			
			if (birth_date != "") {
				birth_dt = format.parse(birth_date);
			}*/
			String card_no = request.getParameter("card_no");
			
	        try {
				
	        	Query q0 = session1.createQuery("select count(id) from TB_PSG_CANTEEN_CARD_DETAIL1 where card_no=:card_no  and id !=:id");
				q0.setParameter("card_no", card_no);  
				q0.setParameter("id", id); 
					Long c = (Long) q0.uniqueResult();
					
					if(c==0) {
						 String hql = "update TB_PSG_CANTEEN_CARD_DETAIL1 set card_no=:card_no,name=:name,"
						 		+ "type_of_card=:type_of_card,relation=:relation,modified_by=:modified_by,"
						 		+ "modified_date=:modified_date"
									+ " where id=:id";
				                                   
				    	  Query query = session1.createQuery(hql)
				    			  	.setString("card_no",card.getCard_no())
				    			  	.setString("name", card.getName())
									/*.setTimestamp("date_of_birth", card.getDate_of_birth())	*/								
									.setString("type_of_card", card.getType_of_card())
								 	.setInteger("relation",card.getRelation())
									.setString("modified_by", username)
									.setTimestamp("modified_date", new Date())
									.setInteger("id",id);
				                    msg = query.executeUpdate() > 0 ? "1" :"0";
				                    tx.commit(); 
				                    
				                    if(msg == "1") {
				                    	Mmap.put("msg", "Data Updated Successfully.");
				                    }
				                    else {
				                    	Mmap.put("msg", "Data Not Updated.");
				                    }
					}
					else {
						Mmap.put("msg", "Data already Exist.");
					}
			  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      Mmap.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
		    return new ModelAndView("redirect:Search_Canteen_DetailUrl");
		}
		
		
			@RequestMapping(value = "/admin/Delete_Canteen_card_details" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Delete_Canteen_card_details(@ModelAttribute("id3") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_PSG_CANTEEN_CARD_DETAIL where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					tx.commit();
					sessionHQL.close();

					if (app > 0) {
						liststr.add("Delete Successfully.");
					} else {
						liststr.add("Delete UNSuccessfully.");
					}
					model.put("msg",liststr.get(0));

				} catch (Exception e) {
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					model.put("msg",liststr.get(0));
				}
				return new ModelAndView("redirect:Search_Canteen_DetailUrl");
			}
		 @RequestMapping(value = "/Approve_Canteen_card_details" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Approve_Canteen_card_details(@ModelAttribute("idA") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg,
					Authentication authentication) {
				     List<String> liststr = new ArrayList<String>();
			
				     String username = sessionA.getAttribute("username").toString();
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					
					String hqlUpdate = "update TB_PSG_CANTEEN_CARD_DETAIL set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
					
					int app = sessionHQL.createQuery(hqlUpdate)
							.setInteger("status", 1)
							.setString("modified_by", username)
							.setDate("modified_date", new Date())
							.setInteger("id", id).executeUpdate();
					
					
					tx.commit();
					sessionHQL.close();
					

					if (app > 0) {
						liststr.add("Approved Successfully.");
					} else {
						liststr.add("Approved Not Successfully.");
					}
					model.put("msg",liststr.get(0));

					
				return new ModelAndView("redirect:Search_Canteen_DetailUrl");
			}
		 
		 @RequestMapping(value = "/Reject_Canteen_card_details" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView Reject_Canteen_card_details(@ModelAttribute("idR") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg,
					
					Authentication authentication) {
				     List<String> liststr = new ArrayList<String>();
			
				     String username = sessionA.getAttribute("username").toString();
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					
					String hqlUpdate = "update TB_PSG_CANTEEN_CARD_DETAIL set status=:status  where id=:id";
					
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)
							.setInteger("id", id).executeUpdate();
									
					tx.commit();
					sessionHQL.close();
					

					if (app > 0) {
						liststr.add("Rejected Successfully.");
					} else {
						liststr.add("Rejected Not Successfully.");
					}
					model.put("msg",liststr.get(0));

					
				return new ModelAndView("redirect:Search_Canteen_DetailUrl");
			}
		 
		 //DIPAL  260194
		 @RequestMapping(value = "/admin/CSD_action", method = RequestMethod.POST)
			public @ResponseBody String CSD_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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
				String census_id = request.getParameter("census_id"); 
				BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
				String rvalue = "";
				TB_PSG_CANTEEN_CARD_DETAIL1 foregin = new TB_PSG_CANTEEN_CARD_DETAIL1();
				
			
				if (relation == null || relation.equals("0")) {
					return "Please Select Category";
				}
				if (type_of_card.equals("") || type_of_card == null) {
					return "Please Select Type of Card";
				}
				if ((name == null || name.equals("")) && (Sname.equals("0"))) {
					return "Please Enter Name";
				}
				/*if (date_of_birth == null || date_of_birth.equals("")) {
					return "Please Select Date of Birth";
				}*/
				if (card_no == null || card_no.equals("")) {
					return "Please Enter Card No";
				}
				if (!valid.isOnlyAlphabetNumeric(card_no)) {
					return " Card No " + valid.isOnlyAlphabetNumericMSG;
				}
				if (!valid.isvalidLength(card_no, valid.card_no_max, valid.card_no_min)) {
					return " Card No " + valid.isValidLengthMSG;
				}

				try {
					Query q0 = sessionhql.createQuery("select count(id) from TB_PSG_CANTEEN_CARD_DETAIL1 where card_no=:card_no  and id !=:id");
					q0.setParameter("card_no", card_no);  
					q0.setParameter("id", Integer.parseInt(CSD_Card_ch_id)); 
						Long c = (Long) q0.uniqueResult();
						
					Query q01 = sessionhql.createQuery("select count(id) from TB_PSG_CANTEEN_CARD_DETAIL1 where comm_id=:comm_id   and upper(type_of_card)=:type_of_card and upper(name)=:name and relation=:relation  and id !=:id");
				
					q01.setParameter("id", Integer.parseInt(CSD_Card_ch_id)); 
					q01.setParameter("comm_id", comm_id); 
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
						foregin.setCensus_id(Integer.parseInt(census_id));
						foregin.setComm_id(comm_id);
						if(name != null && !name.equals("")) {
						foregin.setName(name);
						}
						else if((Sname != null && !Sname.equals("0") &&  !Sname.equals(""))) {
							foregin.setName(Sname);
						}
						foregin.setType_of_card(type_of_card);
						if(date_of_birth != null && !date_of_birth.equals(""))
						{
							foregin.setDate_of_birth(format.parse(date_of_birth));
						}
						
						if(date_of_birth == null)
						{
							foregin.setDate_of_birth(format.parse(date_of_birth));
						}
						
						foregin.setCard_no(card_no);
						foregin.setStatus(0);
						foregin.setCreated_by(username);
						foregin.setCreated_date(date);
						int id = (int) sessionhql.save(foregin);
						rvalue = Integer.toString(id);
						
					} else {
						//update
						String dob=null;
						Query query =null;
						if(date_of_birth !=null && !date_of_birth.equals(""))
						{
							String hql = " update TB_PSG_CANTEEN_CARD_DETAIL1 set name=:name,date_of_birth=:date_of_birth,relation=:relation,type_of_card=:type_of_card,"
									+ "card_no=:card_no,modified_by=:modified_by,modified_date=:modified_date,status='0'"
									+ " where  id=:id";
							query = sessionhql.createQuery(hql)
									
									.setInteger("relation",Integer.parseInt(request.getParameter("relation")));
									if(name != null && !name.equals("")) {
										query.setString("name", name);
										}
									else if((Sname != null && !Sname.equals("0") &&  !Sname.equals(""))) {
											query.setString("name", Sname);
										}
									query.setString("card_no",card_no)
									.setTimestamp("date_of_birth", format.parse(date_of_birth))
									.setString("type_of_card", type_of_card)
									.setString("modified_by", username)
									.setTimestamp("modified_date", new Date())
									
									.setInteger("id",Integer.parseInt(CSD_Card_ch_id));
						}
						
						if(date_of_birth ==null ||  date_of_birth.equals(""))
						{
							String hql = " update TB_PSG_CANTEEN_CARD_DETAIL1 set name=:name,date_of_birth=:date_of_birth,relation=:relation,type_of_card=:type_of_card,"
									+ "card_no=:card_no,modified_by=:modified_by,modified_date=:modified_date,status='0'"
									+ " where  id=:id";
							query = sessionhql.createQuery(hql)
									
									.setInteger("relation",Integer.parseInt(request.getParameter("relation")));
									if(name != null && !name.equals("")) {
										query.setString("name", name);
										}
									else if((Sname != null && !Sname.equals("0") &&  !Sname.equals(""))) {
											query.setString("name", Sname);
										}
									query.setString("card_no",card_no)
									.setTimestamp("date_of_birth", null)
									.setString("type_of_card", type_of_card)
									.setString("modified_by", username)
									.setTimestamp("modified_date", new Date())
									
									.setInteger("id",Integer.parseInt(CSD_Card_ch_id));
						}
						
						
											
						rvalue = query.executeUpdate() > 0 ? "update" : "0";
					}
					String approoval_status = request.getParameter("app_status");
					if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
						
					}
					else
					{
						pcommon.update_offr_status(Integer.parseInt(census_id),username);
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
			@RequestMapping(value = "/admin/CSDCard_delete_action", method = RequestMethod.POST)
			public @ResponseBody String CSDCard_delete_action(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("CSD_Card_ch_id"));
				try {
					String hqlUpdate = "delete from TB_PSG_CANTEEN_CARD_DETAIL1 where id=:id";
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
			
			
			@RequestMapping(value = "/admin/updateCSD_getData", method = RequestMethod.POST)
			public @ResponseBody List<TB_PSG_CANTEEN_CARD_DETAIL1> updateCSD_getData(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
				String census_id = request.getParameter("census_id");
				String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where comm_id=:comm_id and census_id=:census_id and status= '0' order by id";
				Query query = sessionHQL.createQuery(hqlUpdate).setParameter("comm_id",comm_id)
						.setParameter("census_id",Integer.parseInt(census_id));
				List<TB_PSG_CANTEEN_CARD_DETAIL1> list = (List<TB_PSG_CANTEEN_CARD_DETAIL1>) query.list();
				tx.commit();
				sessionHQL.close();
			
				return list;
			}
			
			public @ResponseBody List<TB_PSG_CANTEEN_CARD_DETAIL1> CSD_GetData1(int id, BigInteger comm_id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = "from TB_PSG_CANTEEN_CARD_DETAIL1 where census_id=:census_id and comm_id=:comm_id and status = '0'";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
				@SuppressWarnings("unchecked")
				List<TB_PSG_CANTEEN_CARD_DETAIL1> list = (List<TB_PSG_CANTEEN_CARD_DETAIL1>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}
			
			public String Update_CSD_details(TB_PSG_CANTEEN_CARD_DETAIL1 obj, String username) {

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();

				String msg = "";
				String msg1 = "";
				try {

					String hql1 = "update TB_PSG_CANTEEN_CARD_DETAIL1 set status=:status where census_id=:census_id and comm_id=:comm_id and status != '0' and status != '-1' ";

					Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
							.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

					msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

					String hql = "update TB_PSG_CANTEEN_CARD_DETAIL1 set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
							+ " where census_id=:census_id and comm_id=:comm_id and status = '0'";

					Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
							.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
							.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

			@RequestMapping(value = "/admin/getCSD_Reject", method = RequestMethod.POST)
			public @ResponseBody String getCSD_Reject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) throws ParseException {

				String username = session.getAttribute("username").toString();
				TB_PSG_CANTEEN_CARD_DETAIL1 csdr = new TB_PSG_CANTEEN_CARD_DETAIL1();
				csdr.setCensus_id(Integer.parseInt(request.getParameter("census_id")));
				csdr.setComm_id(new BigInteger(request.getParameter("comm_id")));
				csdr.setReject_remarks(request.getParameter("reject_remarks"));
				String msg1 = CSDr_Reject(csdr, username);

				return msg1;

			}

			public String CSDr_Reject(TB_PSG_CANTEEN_CARD_DETAIL1 obj, String username) {

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();

				String msg = "";
				String msg1 = "";
				try {
					

					String hql = "update TB_PSG_CANTEEN_CARD_DETAIL1 set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
							+ " where census_id=:census_id and comm_id=:comm_id and status = '0' ";

					Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())
							.setInteger("census_id", obj.getCensus_id()).setBigInteger("comm_id", obj.getComm_id());

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

			public @ResponseBody List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_detailsData2(int id, BigInteger comm_id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where census_id=:census_id and status='3' and comm_id=:comm_id order by id";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
				@SuppressWarnings("unchecked")
				List<TB_PSG_CANTEEN_CARD_DETAIL1> list = (List<TB_PSG_CANTEEN_CARD_DETAIL1>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}

			@RequestMapping(value = "/admin/getCSD_Data3", method = RequestMethod.POST)
			public @ResponseBody List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_Data3(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("census_id"));
				BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
				String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where census_id=:census_id and status = '3' and comm_id=:comm_id order by id ";
				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
				@SuppressWarnings("unchecked")
				List<TB_PSG_CANTEEN_CARD_DETAIL1> list = (List<TB_PSG_CANTEEN_CARD_DETAIL1>) query.list();
				tx.commit();
				sessionHQL.close();
				
				return list;
			}

			
			
}
