package com.controller.psg.Master;

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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Civilian_TradeDAO;
import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_TRADE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Civilian_TradeController {

Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Civilian_TradeDAO CTdao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Civilian_TradeUrl", method = RequestMethod.GET)
	 public ModelAndView Civilian_TradeUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		 ArrayList<ArrayList<String>> list = CTdao.search_Civilian_Trade("","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("Civilian_TradeTiles");
	 }
	 
	 
	 @RequestMapping(value = "/Civilian_TradeAction",method=RequestMethod.POST)
		public ModelAndView Civilian_TradeAction(@ModelAttribute("Civilian_TradeCMD") TB_PSG_MSTR_CIVILIAN_TRADE com,@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				
				
				 String civilian_trade = request.getParameter("civilian_trade").trim();
				 String status = request.getParameter("status");
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (civilian_trade.equals("") || civilian_trade.equals("null") || civilian_trade.equals(null)) {
							model.put("msg", "Please Enter Civilian Trade");
							return new ModelAndView("redirect:Civilian_TradeUrl");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Civilian_TradeUrl");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_CIVILIAN_TRADE where civilian_trade=:civilian_trade and status=:status and id !=:id");
					q0.setParameter("civilian_trade", com.getCivilian_trade());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setCivilian_trade(civilian_trade);
						com.setStatus(status);
						if (c == 0) {
							sessionHQL.save(com);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
				
					tx.commit();
				}catch(RuntimeException e){
					e.printStackTrace();
					try{
						tx.rollback();
						model.put("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						model.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Civilian_TradeUrl");
			}
	 
	 
	 @RequestMapping(value = "/admin/Search_civilian_trade_Master", method = RequestMethod.POST)
		public ModelAndView Search_civilian_trade_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "civilian_trade1", required = false) String civilian_trade1 ,@ModelAttribute("status1") String status)  {
		
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 		ArrayList<ArrayList<String>> list = CTdao.search_Civilian_Trade(civilian_trade1,status);
				Mmap.put("civilian_trade1", civilian_trade1);
				Mmap.put("status2", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("Civilian_TradeTiles","Civilian_TradeCMD",new TB_PSG_MSTR_CIVILIAN_TRADE());
			   
		}
	
				
	 @RequestMapping(value = "/Edit_civilian_trade",method = RequestMethod.POST)
		public ModelAndView Edit_civilian_trade(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {
		 
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

			
		 TB_PSG_MSTR_CIVILIAN_TRADE CT = CTdao.getCivilian_tradeByid(Integer.parseInt(updateid));
				Mmap.put("Edit_civilian_tradeCMD", CT);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Civilian_TradeTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_civilian_trade_Action", method = RequestMethod.POST)
		public ModelAndView Edit_civilian_trade_Action(@ModelAttribute("Edit_civilian_tradeCMD") TB_PSG_MSTR_CIVILIAN_TRADE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
			
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String civilian_trade = request.getParameter("civilian_trade").trim();
			String status = request.getParameter("status");
			
			

			if (civilian_trade.equals("") || civilian_trade.equals("null")|| civilian_trade.equals(null)) {
				TB_PSG_MSTR_CIVILIAN_TRADE CT = CTdao.getCivilian_tradeByid(id);
				model.put("Edit_civilian_tradeCMD", CT);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Civilian Trade");
				return new ModelAndView("Edit_Civilian_TradeTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_CIVILIAN_TRADE CT = CTdao.getCivilian_tradeByid(id);
	    		model.put("Edit_civilian_tradeCMD", CT);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Civilian_TradeTiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CIVILIAN_TRADE where civilian_trade=:civilian_trade and status=:status and id !=:id");
						q0.setParameter("civilian_trade", civilian_trade);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_CIVILIAN_TRADE set civilian_trade=:civilian_trade,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("civilian_trade",civilian_trade)
					    			  	.setString("status",status)
										.setString("modified_by", username)
										.setDate("modified_dt", new Date())
										.setInteger("id",id);
					                    msg = query.executeUpdate() > 0 ? "1" :"0";
					                    tx.commit(); 
					                    
					                    if(msg == "1") {
					                    	 model.put("msg", "Data Updated Successfully.");
					                    }
					                    else {
					                    	model.put("msg", "Data Not Updated.");
					                    }
						}
						else {
							model.put("msg", "Data already Exist.");
						}
					
				  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      model.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		                      model.put("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Civilian_TradeUrl");
		}

		
		@RequestMapping(value = "/delete_civilian_trade", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_civilian_trade(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_PSG_MSTR_CIVILIAN_TRADE set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
										+ " where id=:id";
				 
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modified_by", username)
						.setDate("modified_dt", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Civilian_TradeUrl");
		}
}
