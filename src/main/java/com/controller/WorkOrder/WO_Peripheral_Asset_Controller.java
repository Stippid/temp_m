package com.controller.WorkOrder;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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

import com.controller.commonController.It_CommonController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.WorkOrder.WOComputing_Asset_Dao;
import com.dao.WorkOrder.WOPeripheral_Asset_Dao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.model.WorkOrder.COMPUTING_WORK_ORDER_P;
import com.model.WorkOrder.PERIPHERAL_WORK_ORDER_C;
import com.model.WorkOrder.TB_IT_ASSET_WORK_ORDER;
import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class WO_Peripheral_Asset_Controller {
	
	
	It_CommonController it_comm = new It_CommonController();
	
	WO_ComputingAsset_Controller WOC = new WO_ComputingAsset_Controller();
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired 
	private WOPeripheral_Asset_Dao PAD;
	
	@Autowired
	private WOComputing_Asset_Dao CPD;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Work_Order_Search_Peripheral_Asset_Url", method = RequestMethod.GET)
	public ModelAndView Work_Order_Search_Peripheral_Asset_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
		
		
		String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Work_Order_Search_Peripheral_Asset_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			if (roleAccess.equals("Unit")) {
				Mmap.put("sus_no", roleSusNo);
				Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			}
		 Mmap.put("getPeripheral", it_comm.getPeripheral());
		 Mmap.put("getStatusList", it_comm.getStatusList());
		return new ModelAndView("Work_Order_Search_Peripheral_Asset_Tiles");
	}
	
	
	@RequestMapping(value = "/Peripheral_Asset_List_ReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> Peripheral_Asset_List_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String asset_type, String make_name, String model_name,String status, HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			 InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 return PAD.Peripheral_Asset_ReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,asset_type,make_name,model_name,status,sessionUserId);
	}
	 
	 @RequestMapping(value = "/Peripheral_Asset_List_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long Peripheral_Asset_List_TotalCount(HttpSession sessionUserId,String Search,String asset_type, String make_name, String model_name,String status,HttpSession session){
	 	return PAD.Peripheral_Asset_TotalCountDtl(Search,asset_type,make_name,model_name,status,session);
	 }
	 
	 @RequestMapping(value = "/admin/Peripheral_Asset_WO_Url", method = RequestMethod.POST)
		public ModelAndView Peripheral_Asset_WO_Url(@ModelAttribute("idV") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
				HttpSession sessionEdit,HttpSession session) throws NumberFormatException, ParseException  {
			
		 Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String susno = session.getAttribute("roleSusNo").toString();
		 It_Asset_Peripherals Asset = PAD.getPeripheralAssesByid(Integer.parseInt(updateid));
		 ArrayList<ArrayList<String>> Last = CPD.GetLast_WO_no_dt(String.valueOf(Asset.getId()),"2");
		  
		   if(Last.size() > 0) {
			    Mmap.put("l_wo_no",Last.get(0).get(0));
				Mmap.put("l_wo_dt",Last.get(0).get(1));
		   }
		   
	    Mmap.put("Peripheral_Asset_WO_CMD", Asset);
		Mmap.put("date",date1);
		Mmap.put("WO_NO",WOC.generateWO_No(susno));
		Mmap.put("msg", msg);
		Mmap.put("Hid", updateid);
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		return new ModelAndView("Peripheral_Asset_WO_Tile");
	}
	 @RequestMapping(value = "/Peripheral_Asset_WO_Action", method = RequestMethod.POST)
		public ModelAndView Peripheral_Asset_WO_Action(@ModelAttribute("Peripheral_Asset_WO_CMD") TB_IT_ASSET_WORK_ORDER BAN,
				BindingResult result, HttpServletRequest request, ModelMap Mmap,HttpSession session) throws ParseException {
			 
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			 Date WO_dt = null;
				String wo_dt = request.getParameter("wo_dt");
				if (wo_dt!=null && !wo_dt.equals("") ) {
					WO_dt = format.parse(wo_dt);
				}
         Date fwd_dt = null;
			String dt_eqpt_reqd_fwd_wksp = request.getParameter("dt_eqpt_reqd_fwd_wksp");
			if (dt_eqpt_reqd_fwd_wksp!=null && !dt_eqpt_reqd_fwd_wksp.equals("")) {
				fwd_dt = format.parse(dt_eqpt_reqd_fwd_wksp);
			}
			try {
				    BAN.setP_id(Integer.parseInt(request.getParameter("Hid")));
			        BAN.setWo_dt(WO_dt);
				    BAN.setDt_eqpt_reqd_fwd_wksp(fwd_dt);
				    BAN.setCategory(2);
					BAN.setCreated_by(username);
					BAN.setCreated_date(date);
					BAN.setWk_generate_status(0);
					sessionHQL.save(BAN);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					Mmap.put("msg", "Data Saved Successfully.");
			}

			catch (RuntimeException e) {
				try {
					tx.rollback();
					Mmap.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					Mmap.put("msg", "Couldnï¿½t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();

				}
			}
			return new ModelAndView("redirect:Work_Order_Search_Peripheral_Asset_Url");
		}
	
	 
//	 public String generate_WO_No() {
//
//			String Wo_No = "";
//			Session sessionHQL = null;
//			Transaction tx = null;
//			try {
//				sessionHQL = HibernateUtil.getSessionFactory().openSession();
//				tx = sessionHQL.beginTransaction();
//
//				Query qMax = sessionHQL.createQuery("SELECT  cast(max(wo_no) as integer)+1 as wo_no  FROM COMPUTING_WORK_ORDER_P where wo_no is not null");
//				Wo_No = String.valueOf(qMax.list().get(0));
//				sessionHQL.flush();
//				sessionHQL.clear();
//				if (Wo_No == null || Wo_No.equals("") || Wo_No.equals("null")) {
//					Wo_No = "0000000001";
//				} else {
//
//					Wo_No = String.format("%010d",Integer.parseInt( Wo_No));
//				
//
//				}
//				
//				tx.commit();
//			} catch (RuntimeException e) {
//				tx.rollback();
//			} finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//				}
//			}
//			return Wo_No;
//		}
	 
		///BISAG V2 240822 ///
	 @RequestMapping(value = "/GenerateWorkOrderActionPeripheral_P" , method = RequestMethod.POST)
		public @ResponseBody String GenerateWorkOrderActionPeripheral_P(String a,String status,HttpSession session,HttpServletRequest request,ModelMap Mmap) throws ParseException, SQLException {	
			
			COMPUTING_WORK_ORDER_P p = new COMPUTING_WORK_ORDER_P();
			String susno = session.getAttribute("roleSusNo").toString();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = new Date();
			
			String username = session.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			 int id=0;
			 String comp;
			 Date dt_eqpt_reqd_fwd_wksp1 = null;
			String dt_eqpt_reqd_fwd_wksp = request.getParameter("dt_eqpt_reqd_fwd_wksp");
			if (dt_eqpt_reqd_fwd_wksp!=null && !dt_eqpt_reqd_fwd_wksp.equals("") ) {
				dt_eqpt_reqd_fwd_wksp1 = format.parse(dt_eqpt_reqd_fwd_wksp);
			}
			
      Date wo_dt1 = null;
			String wo_dt = request.getParameter("wo_dt");
			if (wo_dt!=null && !wo_dt.equals("")) {
				wo_dt1 = format.parse(wo_dt);
			}
			String wksp_unit_name = request.getParameter("wksp_unit_name");
			String unit_sus_no = request.getParameter("unit_sus_no");
			
		
//			try {
				 	
				 	p.setDt_eqpt_reqd_fwd_wksp(dt_eqpt_reqd_fwd_wksp1);
				 	p.setWo_dt(wo_dt1);
					p.setWksp_sus_no(unit_sus_no);
				 	p.setWksp_unit_name(wksp_unit_name);
				 	p.setWo_no(generate_WO_No(susno,username));
				 	p.setCreated_by(username);
				 	p.setCreated_date(date);
				 	p.setSus_no(susno);
				 	
				 	id=(int)sessionHQL.save(p);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					Mmap.put("msg", "Data Saved Successfully.");
//			 }	
//			catch (RuntimeException e) {
//				try {
//						tx.rollback();
//						Mmap.put("msg", "roll back transaction");
//					} catch (RuntimeException rbe) {
//						Mmap.put("msg", "CouldnÃ¯Â¿Â½t roll back transaction " + rbe);
//					}
//					throw e;
//				}
//			finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//
//				}
//			}
			
			comp = ""+id;
			return comp;
		}
	 
		///BISAG V2 240822 END///
	 
	 /////midel serial no///////
	 
//	 public String generate_WO_No(String susno) {
//
//			String Wo_No = "";
//			Session sessionHQL = null;
//			Transaction tx = null;
//			
//			 LocalDate currentdate = LocalDate.now();
//
//		      int currentMonth = currentdate.getMonthValue();
//
//		      int currentYear = currentdate.getYear();
//	    	
//			
////			try {
//		      
//		     
//		      
//				sessionHQL = HibernateUtil.getSessionFactory().openSession();
//				tx = sessionHQL.beginTransaction();
//
////				 Query qMax = sessionHQL.createQuery("SELECT max(wo_no) FROM COMPUTING_WORK_ORDER_P");
//				 
//				 Query qMax = sessionHQL.createQuery("SELECT max(wo_no) FROM COMPUTING_WORK_ORDER_P where SUBSTRING(wo_no, 1,8)=:susno and SUBSTRING(wo_no, 10,1) =:currentMonth  and SUBSTRING(wo_no, 11,4) =:currentYear  ");
//				 
////				 q.setParameter("peripheral_type", peripheral_type);
//				 qMax.setParameter("susno",susno);
//				 qMax.setParameter("currentMonth",currentMonth);
//				 qMax.setParameter("currentYear",currentYear);
//				 
//					Wo_No = (String) qMax.list().get(0);
//					sessionHQL.flush();
//					sessionHQL.clear();
//				if (Wo_No == null || Wo_No.equals("") || Wo_No.equals("null")) {
//					Wo_No = susno+"1"+currentMonth+currentYear;
//				} else {
//					
//					String Wo_No1=Wo_No;
//					
//					Wo_No1 = Wo_No.substring(8, 9);
//					String Wo_No02=Wo_No1;
//					int Wo_No03=Integer.parseInt(Wo_No02)+1;
//					Wo_No =susno+Wo_No03+currentMonth+currentYear;
//				}
//				
//				tx.commit();
////			} catch (RuntimeException e) {
////				tx.rollback();
////			} finally {
////				if (sessionHQL != null) {
////					sessionHQL.close();
////				}
////			}
//				sessionHQL.close();
//			return Wo_No;
//		}
	
	 
		///BISAG V2 240822///
	 public String generate_WO_No(String susno,String username) throws SQLException {

			String Wo_No = "";
			String Wo_No_c = "";
			
			String month_q = "";
			Session sessionHQL = null;
			Transaction tx = null;
			int serialNo = 0;
//			String list = null;
//			String username = session.getAttribute("username").toString();
			String month_q1= "";
			 LocalDate currentdate = LocalDate.now();
			 
			    SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
			 simpleformat = new SimpleDateFormat("dd/MMMM/yyyy");
			 simpleformat = new SimpleDateFormat("MM");
			 String strMonth = simpleformat.format(new Date());
			 
		      int currentMonth = Integer.parseInt(strMonth);

		      int currentYear = currentdate.getYear();
	    	
		      PAD.Update_Wo_Peripheral();  
				
			try {
		      
		      Session session = HibernateUtil.getSessionFactory().openSession();
				 tx = session.beginTransaction();
		      
//				sessionHQL = HibernateUtil.getSessionFactory().openSession();
//				tx = sessionHQL.beginTransaction();

				 
				 
				
				 
				 
				 Query qMax1 = session.createQuery("select  wo_no from COMPUTING_WORK_ORDER_P where sus_no=:sus_no order by id desc");
				 
				 qMax1.setParameter("sus_no",String.valueOf(susno));
				
				 
				 if(qMax1.list().isEmpty())
				 {
					 serialNo = 1;
					 String serial = String.format("%04d", serialNo);
					 Wo_No = susno +"/"+ strMonth +"/" + currentYear+"/"+ serial;
				 
				 }
				  else 
				 {
					  Wo_No_c = (String) qMax1.list().get(0);
				 }
				 boolean WWo_No1=Wo_No_c.contains("/");
				 
				 if(WWo_No1 == false  || Wo_No.equals(null))
				 {
					 serialNo = 1;
					 String serial = String.format("%04d", serialNo);
					 Wo_No = susno +"/"+ strMonth +"/" + currentYear+"/"+ serial;
				 }
				 
				 if(WWo_No1 == true)
				 {
				 
				 Query qMax = session.createQuery("select SUBSTRING(wo_no, 18,4) from COMPUTING_WORK_ORDER_P where sus_no=:sus_no order by id desc");
				 
//				Query qMax = sessionHQL.createQuery("SELECT max(wo_no) FROM COMPUTING_WORK_ORDER_P");
				 
//				 q.setParameter("peripheral_type", peripheral_type);
				 
				 
				 qMax.setParameter("sus_no",String.valueOf(susno));
				 
//				 qMax.setParameter("currentMonth",String.valueOf(strMonth));
//				 qMax.setParameter("currentYear",String.valueOf(currentYear));
//				 tx.commit();
				
				 Wo_No = (String) qMax.list().get(0);
				 
				 
				 
				 
				 
				 Query qMaxM = session.createQuery("select wo_no from COMPUTING_WORK_ORDER_P where sus_no=:sus_no and SUBSTRING(wo_no,10,2)=:currentMonth and SUBSTRING(wo_no,13,4)=:currentYear order by id desc");
				 
				 qMaxM.setParameter("sus_no",String.valueOf(susno));
				 
				 qMaxM.setParameter("currentMonth",String.valueOf(strMonth));
				 qMaxM.setParameter("currentYear",String.valueOf(currentYear));
				  
				 
				 //if month is change go to if condition
				 
				 
				 if(qMaxM.list().size() != 0)
				 {
					 month_q = (String) qMaxM.list().get(0);
						 month_q1 = month_q.substring(9, 11);
					 
					
					
				 }
				 if(month_q1.equals(strMonth) || month_q1.equals(null))
					 
				 {
						if (Wo_No != null) {	
							
							String list1 = Wo_No;
							String code = String.valueOf(list1.charAt(0)) + String.valueOf(list1.charAt(1))
									+ String.valueOf(list1.charAt(2)) + String.valueOf(list1.charAt(3));
								
							serialNo = Integer.parseInt(code) + 1;
							 String serial = String.format("%04d", serialNo);
							Wo_No = susno +"/"+ strMonth +"/" + currentYear+"/"+ serial;
							
						} 
				 }	 
				
			/////////	 if month is change logic is write bellow
				 
				 else
				 {
					 serialNo = 1;
					 String serial = String.format("%04d", serialNo);
					 Wo_No = susno +"/"+ strMonth +"/" + currentYear+"/"+ serial;
				 }
			
				
//				else {
					
//					String Wo_No1=Wo_No;
			
//					Query qMax1 = sessionHQL.createQuery("SELECT wo_no FROM COMPUTING_WORK_ORDER_P group by id  order by id desc");
////					qMax1.setFirstResult(1);
//
////					q.setMaxResults(1);
////					qMax1.setMaxResults(1);
//					
//					Wo_No = (String) qMax1.list().get(0);
//					
////					String Wo_No2=Wo_No;str.charAt(str.length()-1)
//					
//					int Wo_No4=Wo_No.length();
//					
//					if(Wo_No4 == 15)
//					{
//						String Wo_Nop=Wo_No;
//						String Wo_No02=Wo_Nop.substring(Wo_Nop.length() - 1);
////						Wo_Nop = Wo_No.substring(15,0);
//						
//						int Wo_No03=Integer.parseInt(Wo_No02)+1;
//						Wo_No =susno+strMonth+currentYear+Wo_No03;
//					}
//					if(Wo_No4 == 16)
//					{
//						String Wo_Nop=Wo_No;
//						String Wo_No02=Wo_Nop.substring(Wo_Nop.length() - 2);
//						int Wo_No03=Integer.parseInt(Wo_No02)+1;
//						Wo_No =susno+strMonth+currentYear+Wo_No03;
//					}
//					if(Wo_No4 == 17)
//					{
//						String Wo_Nop=Wo_No;
//						String Wo_No02=Wo_Nop.substring(Wo_Nop.length() - 3);
//						int Wo_No03=Integer.parseInt(Wo_No02)+1;
//						Wo_No =susno+strMonth+currentYear+Wo_No03;
//					}
//					
//					String Wo_No02=Wo_No1;
//					int Wo_No03=Wo_No2+1;
//					Wo_No =susno+currentMonth+currentYear+Wo_No03;
					
					
//					String serial = String.format("%04d", serialNo);
//					 Wo_No = susno + strMonth + currentYear + serial;
//					
//					
//					
//				}
				 }	
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			} 
				
			return Wo_No;
		}
	 
		///BISAG V2 240822 END///
	 
	 
	 
	 @RequestMapping(value = "/GenerateWorkOrderActionPeripheral_Ch" , method = RequestMethod.POST)
		public @ResponseBody String GenerateWorkOrderActionPeripheral_Ch(String a,String status,HttpSession session,HttpServletRequest request,ModelMap Mmap) throws ParseException {	

		 PERIPHERAL_WORK_ORDER_C c = new PERIPHERAL_WORK_ORDER_C();

			Date date = new Date();
			
			String username = session.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			 int id=0;
			 String comp;

			String a1 =request.getParameter("a1");

			String p_id1 = request.getParameter("p_id1");

			try{

					String defects_obs1= request.getParameter("Defects");

					String remarks= request.getParameter("remarks");
					c.setDefects_obs(defects_obs1);
				 	c.setRemarks(remarks);
				 	c.setCreated_by(username);
				 	c.setCreated_date(date);
				 	c.setP_id(Integer.parseInt(p_id1));
				 	c.setAsset_type(Integer.parseInt(a1));
				 	id=(int)sessionHQL.save(c);
					sessionHQL.flush();
					sessionHQL.clear();
					Mmap.put("msg", "Data Saved Successfully.");
			 }	
			catch (RuntimeException e) {
				try {
						tx.rollback();
						Mmap.put("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						Mmap.put("msg", "CouldnÃ¯Â¿Â½t roll back transaction " + rbe);
					}
					throw e;
				}
			finally {
				if (sessionHQL != null) {
					sessionHQL.close();

				}
			}
				tx.commit();
				
			comp = ""+id;
			return comp;
		}
	 
	 
	 
	//*         ---------------------------Pop up History----------------------       */
	 
		 @RequestMapping(value = "/admin/Pop_Up_WO_Peripheral_Asset_Url", method = RequestMethod.GET)
			public ModelAndView Pop_Up_WO_Peripheral_Asset_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
			 Mmap.put("getPeripheral", it_comm.getPeripheral());
				return new ModelAndView("PopUp_WO_History_Peripheral_Tiles");
			}
		 
		 
		 
			 @RequestMapping(value = "/Pop_UP_Peripheral_Asset_History_ReportDataList", method = RequestMethod.POST)
			 public @ResponseBody List<Map<String, Object>> Pop_UP_Computing_Asset_History_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
					 String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs, HttpSession sessionUserId) 
					 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
					 InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
				 return PAD.Pop_UP_Peripheral_Asset_History_ReportDataList(startPage,pageLength,Search,orderColunm,orderType,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs,sessionUserId);
			}
			 
			 @RequestMapping(value = "/Pop_UP_Peripheral_Asset_History_List_TotalCount", method = RequestMethod.POST)
			 public @ResponseBody long Pop_UP_Computing_Asset_History_List_TotalCount(HttpSession sessionUserId,String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs){
				 	return PAD.Pop_UP_Peripheral_Asset_History_List_TotalCount(Search,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs,sessionUserId);
	 }
			 
			 @RequestMapping(value = "/pop_up_detail_Peripheral_Asset_History_RecordDataList", method = RequestMethod.POST)
			 public @ResponseBody List<Map<String, Object>> pop_up_detail_Computing_Asset_History_RecordDataList(HttpSession sessionUserId, String id) 
					 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
					 InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
				 
				   String enckey = "commonPwdEncKeys";  
		           String DcryptedPk = hex_asciiDao.decrypt((String) id,enckey,sessionUserId); 
				 return PAD.pop_up_detail_Peripheral_Asset_History_RecordDataList(sessionUserId,Integer.parseInt(DcryptedPk));
			}
		 
		 /* ------------------------------END Pop up --------------------------------------------------------------------------------------*/
		 
		 
		 
		 @RequestMapping(value = "/Download_Peripheral_Asset_WO", method = RequestMethod.POST)
			public ModelAndView Download_Peripheral_Asset_WO(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Hid_id", required = false) String Hid_id,
					@RequestParam(value = "wksp_unit_name1", required = false) String wksp_unit_name, 
					@RequestParam(value = "wo_dt1", required = false) String wo_dt, 
					@RequestParam(value = "dt_eqpt_reqd_fwd_wksp1", required = false) String dt_eqpt_reqd_fwd_wksp,
					String typeReport) {
			 
					ArrayList<ArrayList<String>> list = PAD.getPeripheralDetails(Hid_id,wksp_unit_name,wo_dt,dt_eqpt_reqd_fwd_wksp);
					
					ArrayList<ArrayList<String>> list1 = PAD.getPeripheralDetails1(Hid_id,wksp_unit_name,wo_dt,dt_eqpt_reqd_fwd_wksp);
					
					List<String> TH = new ArrayList<String>();
					///BISAG V1 220822///
					String Heading =  session.getAttribute("roleloginName").toString();
						if (typeReport != null && typeReport.equals("pdfL")) {
							if (list.size() > 0) {

								TH.add("Ser No");
								TH.add("Make Name");
								TH.add("Assets Name");
								TH.add("Machine Number");
								TH.add("Qty");
								TH.add("Defects Observed");
								TH.add("Remarks");
							
								
							}
						}

				return new ModelAndView(new PDF_Peripheral_Asset_Download("P", TH,Heading, list1), "userList", list);

			}
}
