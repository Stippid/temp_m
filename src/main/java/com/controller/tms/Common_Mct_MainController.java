package com.controller.tms;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MctSlotDAO;
import com.models.TB_TMS_MCT_DISCARD_CON;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_MAIN_OH_MR;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Common_Mct_MainController {

	@Autowired
	MctSlotDAO makeMasretDAO;
	
	@Autowired
	private MctSlotDAO mari_dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	Common_Mct_slotController M = new Common_Mct_slotController();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value="/mctmain")
	public ModelAndView mctmain(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){	
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctmain", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("getGroupNamelist", M.getGroupNamelist(session));
		model.put("getLine_DteList",roledao.getLine_DteListsus());
		model.put("getForceTypeList", getForceTypeList());
		model.put("msg",msg);
		return new ModelAndView("mctmainTiles","tms_main_masterCmd", new TB_TMS_MCT_MAIN_MASTER());
	}
	
	@RequestMapping(value = "/mctMainAction", method = RequestMethod.POST)
	public ModelAndView mctMainAction(@ModelAttribute("tms_main_masterCmd") TB_TMS_MCT_MAIN_MASTER mctmain,HttpServletRequest request,ModelMap model,HttpSession session) {	
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctmain", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		String sus_no = request.getParameter("sus_no").toString();
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
		
		String mct = request.getParameter("mct_main_id");
		String mct_nomen = request.getParameter("mct_nomen");
//		String sus_no = request.getParameter("sus_no");
		String prf_code=request.getParameter("prf_code"); 	
		String veh_class_code=request.getParameter("vehicle_class_code"); 	
		String type_of_veh=request.getParameter("type_of_veh"); 
		System.out.println(prf_code);
		
		
		mctmain.setCreated_by(username);
		mctmain.setCreated_on(date);
		if(request.getParameter("mct_slot").equals("0"))
		{
			model.put("msg", "Please Select PRF Nomenclature.");
			return new ModelAndView("redirect:mctmain");
		}
		else if(mctmain.getMct_nomen().equals(""))
		{
			model.put("msg", "Please Enter MCT Nomenclature.");
			return new ModelAndView("redirect:mctmain");
		}
		else if(validation.mct_nomenLength(mctmain.getMct_nomen()) == false){
	 		model.put("msg",validation.mct_nomenMSG);
			return new ModelAndView("redirect:mctmain");
		}
		else if(mctmain.getMct_main_id().equals(""))
		{
			model.put("msg", "Please Enter MCT Main.");
			return new ModelAndView("redirect:mctmain");
		}
		else if(validation.mct_main_idLength(mctmain.getMct_main_id()) == false){
	 		model.put("msg",validation.mct_main_idMSG);
			return new ModelAndView("redirect:mctmain");
		}
		else if(validation.type_of_vehLength(mctmain.getType_of_veh()) == false){
	 		model.put("msg",validation.type_of_vehMSG);
			return new ModelAndView("redirect:mctmain");
		}
		else
		{
			Session session1=HibernateUtil.getSessionFactory().openSession();
			try
			{
				String main_id = mctmain.getMct_main_id();
				String slot = request.getParameter("mct_slot").toString();
				String[] array = slot.split(",");
				int from =Integer.parseInt(array[0]);
				int to = Integer.parseInt(array[1]);
				if(from > Integer.parseInt(main_id) || to < Integer.parseInt(main_id))
				{
					model.put("msg", "Please enter MCT Main Id within " + from +" - " + to + ".");
					return new ModelAndView("redirect:mctmain");
				}
				if(makeMasretDAO.checkIfExistMainID(mctmain.getMct_main_id()) != false) {
					
					model.put("msg", "MCT Main Id Already exists");
					return new ModelAndView("redirect:mctmain");
				}
				session1.beginTransaction();
				mctmain.setSus_no(sus_no);
				mctmain.setPrf_code(prf_code);
				mctmain.setVehicle_class_code(veh_class_code);
				mctmain.setType_of_veh(type_of_veh);
			
				
				 String hql = "update TB_TMS_MCT_DISCARD_CON set status=:status where  mct_main_id=:main_mct";
				 	Query query = session1.createQuery(hql).setString("main_mct", mct)
	                         .setString("status","1");
				 	query.executeUpdate();
				 	String hql1 = "update TB_TMS_MCT_MAIN_OH_MR set status=:status where  mct_main_id=:main_mct";
			         Query query1 = session1.createQuery(hql1).setString("main_mct", mct)
			                         .setString("status","1");
			        query1.executeUpdate();
			        
				session1.save(mctmain);
				session1.getTransaction().commit();
				model.put("msg", "MCT Successfully Created.");
				return new ModelAndView("redirect:mctmain");
			}
			catch(Exception e)
			{
				session1.getTransaction().rollback();
				return null;
			}
			finally
			{
				session1.close();
			}
		}
	}






	@RequestMapping(value = "/getSearch_mctdtl" , method = RequestMethod.POST)
	public ModelAndView getSearch_mctdtl(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "mct_slot1", required = false) String mct_slot,
			@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "type_of_veh1", required = false) String type_of_veh,
			@RequestParam(value = "sus_no1", required = false) String sus_no){
	
		ArrayList<ArrayList<String>> list = mari_dao.search_mct_main( mct_slot, prf_code, type_of_veh, sus_no);
		model.put("list", list);
		model.put("size", list.size());
		model.put("mct_slot1", mct_slot);
		model.put("prf_code1", prf_code);
		model.put("type_of_veh1", type_of_veh);
		model.put("sus_no1", sus_no);
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String select="<option value='0'>-- Select --</option>";
		if(roleAccess.equals("Line_dte")) {
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			model.put("selectLine_dte", select);
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		model.put("getGroupNamelist", M.getGroupNamelist(session));
		model.put("getLine_DteList",roledao.getLine_DteListsus());
		model.put("getForceTypeList", getForceTypeList());
		
		return new ModelAndView("mctmainTiles");
	}
	
	
	@RequestMapping(value = "/getmaxMCTMainID", method = RequestMethod.POST)
	public @ResponseBody String getmaxMCTMainID(String mctSlotId,HttpSession sessionUserId) {			
		String[] list = mctSlotId.split(",");
		String from = list[0];
		String to = list[1];
		return makeMasretDAO.getmaxMCTMainID(from,to);
	}
	
	public List<String> getForceTypeList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select codevalue,label from T_Domain_Value where domainid = 'TYPEOFFORCE'");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();

		tx1.commit();

		session1.close();

		return list;

	}
	
	@RequestMapping(value = "/admin/discard_conditionAction", method = RequestMethod.POST)
    public @ResponseBody String discard_conditionAction(ModelMap Mmap, HttpSession session,
                    HttpServletRequest request) throws ParseException {

            Session sessionhql = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionhql.beginTransaction();
            Date date = new Date();
            Date lan_doa = null;
            String username = session.getAttribute("username").toString();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String force_type = request.getParameter("force_type");
            String mct_main_id = request.getParameter("mct_main_id");
            String vintage = request.getParameter("vintage");
            String km = request.getParameter("km");
//            int vintage= Integer.parseInt(request.getParameter("vintage"));
//            int km= Integer.parseInt(request.getParameter("km"));
            String dis_con_ch_id = request.getParameter("dis_con_ch_id");
           
            String msg = "";
            
            if (force_type == null || force_type.equals("-1")) {
	            return "Please Select the Force Type";
	        }
	        if (vintage == "" || vintage.equals("")) {
	            return "Please Enter Vintage";
	        }

	        if (km == "" || km.equals("")) {
	            return "Please Enter  KM";
	        }

	        if (mct_main_id == "" || mct_main_id.equals("")) {
	            return "Please Select the PRF Nomenclature ";
	        }
	        

            try {
                    if (Integer.parseInt(dis_con_ch_id) == 0) {
                    	TB_TMS_MCT_DISCARD_CON dis_con = new TB_TMS_MCT_DISCARD_CON();
                    	
                    	dis_con.setForce_type(force_type);
                    	dis_con.setVintage(Integer.parseInt(vintage));
                    	dis_con.setKm(Integer.parseInt(km));
                    	dis_con.setCreated_by(username);
                        dis_con.setCreated_on(date);
                        dis_con.setMct_main_id(mct_main_id);
                        dis_con.setStatus("0");

                        int id = (int) sessionhql.save(dis_con);
                        msg = Integer.toString(id);
                    } else {
                        
                            String hql = "update TB_TMS_MCT_DISCARD_CON set modified_by=:modified_by ,modified_date=:modified_date ,force_type=:force_type,vintage=:vintage, "
                                            + " km=:km, mct_main_id=:mct_main_id where  id=:id";
                            Query query = sessionhql.createQuery(hql).setString("force_type", force_type)
                                            .setInteger("vintage",Integer.parseInt(vintage))
                                            .setInteger("km", Integer.parseInt(km))
                                            .setString("modified_by", username)
                                            .setDate("modified_date", date)
                                            .setString("mct_main_id", mct_main_id)
                                            .setInteger("id", Integer.parseInt(dis_con_ch_id));

                            msg = query.executeUpdate() > 0 ? "update" : "0";

                    }
                    

                    
                    
                    tx.commit();
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

            return msg;
    }
	
	@RequestMapping(value = "/admin/discard_condition_delete_action", method = RequestMethod.POST)
	public @ResponseBody String discard_condition_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("dis_con_ch_id"));
		try {
			String hqlUpdate = "delete from TB_TMS_MCT_DISCARD_CON where id=:id";
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
	
	
	@RequestMapping(value = "/admin/discard_condition_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_TMS_MCT_DISCARD_CON> discard_condition_getData(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String id = request.getParameter("mct_main_id");
//		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_TMS_MCT_DISCARD_CON where mct_main_id=:mct_main_id  order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("mct_main_id", id);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_DISCARD_CON> list = (List<TB_TMS_MCT_DISCARD_CON>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
// oh_mr start
	@RequestMapping(value = "/admin/discard_condition_oh_mrAction", method = RequestMethod.POST)
    public @ResponseBody String discard_condition_oh_mrAction(ModelMap Mmap, HttpSession session,
                    HttpServletRequest request) throws ParseException {

            Session sessionhql = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = sessionhql.beginTransaction();
            Date date = new Date();
            Date lan_doa = null;
            String username = session.getAttribute("username").toString();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String oh_mr = request.getParameter("oh_mr");
            String vintage_oh_mr = request.getParameter("vintage_oh_mr");
            String km_oh_mr = request.getParameter("km_oh_mr");
//            int vintage_oh_mr= Integer.parseInt(request.getParameter("vintage_oh_mr"));
//            int km_oh_mr= Integer.parseInt(request.getParameter("km_oh_mr"));
            String dis_con_oh_mr_ch_id = request.getParameter("dis_con_oh_mr_ch_id");
            String mct_main_id = request.getParameter("mct_main_id");
            
            String msg = "";
            
            
	        if (oh_mr == null || oh_mr.equals("0")) {
	            return "Please Select the OH/MR";
	        }
	        if (vintage_oh_mr == "" || vintage_oh_mr.equals("")) {
	            return "Please Enter Vintage";
	        }

	        if (km_oh_mr == "" || km_oh_mr.equals("")) {
	            return "Please Enter  KM";
	        }

	        if (mct_main_id == "" || mct_main_id.equals("")) {
	            return "Please Select the PRF Nomenclature ";
	        }
	        

            try {
                    if (Integer.parseInt(dis_con_oh_mr_ch_id) == 0) {
                    	TB_TMS_MCT_MAIN_OH_MR dis_con_oh_mr = new TB_TMS_MCT_MAIN_OH_MR();
                    	
                    	dis_con_oh_mr.setOh_mr(oh_mr);
                    	dis_con_oh_mr.setVintage(Integer.parseInt(vintage_oh_mr));
                    	dis_con_oh_mr.setKm(Integer.parseInt(km_oh_mr));
                    	dis_con_oh_mr.setCreated_by(username);
                    	dis_con_oh_mr.setCreated_on(date);
                    	dis_con_oh_mr.setMct_main_id(mct_main_id);
                    	dis_con_oh_mr.setStatus("0");
                    	
                        int id = (int) sessionhql.save(dis_con_oh_mr);
                        msg = Integer.toString(id);
                    } else {
                        
                            String hql = "update TB_TMS_MCT_MAIN_OH_MR set modified_by=:modified_by ,modified_date=:modified_date ,oh_mr=:oh_mr,vintage=:vintage, "
                                            + " km=:km, mct_main_id=:mct_main_id where  id=:id";
                            Query query = sessionhql.createQuery(hql).setString("oh_mr", oh_mr)
                                            .setInteger("vintage",Integer.parseInt(vintage_oh_mr))
                                            .setInteger("km", Integer.parseInt(km_oh_mr))
                                            .setString("modified_by", username)
                                            .setString("mct_main_id", mct_main_id)
                                            .setDate("modified_date", date)
                            				.setInteger("id", Integer.parseInt(dis_con_oh_mr_ch_id));

                            msg = query.executeUpdate() > 0 ? "update" : "0";
                            Mmap.put("msg", "Data Updated Successfully");
                    }
                    

                    
                    
                    tx.commit();
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

            return msg;
    }
	
	@RequestMapping(value = "/admin/discard_condition_delete_oh_mr_action", method = RequestMethod.POST)
	public @ResponseBody String discard_condition_delete_oh_mr_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("dis_con_oh_mr_ch_id"));
		try {
			String hqlUpdate = "delete from TB_TMS_MCT_MAIN_OH_MR where id=:id";
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
	
	@RequestMapping(value = "/admin/discard_condition_oh_mr_getData", method = RequestMethod.POST)
	public @ResponseBody List<TB_TMS_MCT_MAIN_OH_MR> discard_condition_oh_mr_getData(ModelMap Mmap,
			HttpSession session, HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String id = request.getParameter("mct_main_id");
//		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_TMS_MCT_MAIN_OH_MR where mct_main_id=:mct_main_id  order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("mct_main_id", id);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MAIN_OH_MR> list = (List<TB_TMS_MCT_MAIN_OH_MR>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}