package com.controller.Reports;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import org.springframework.web.servlet.ModelAndView;

import com.dao.Reports.Capture_critical_storeDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_CAPTURE_CRITITCAL_STORE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Capture_Crititcal_StoreController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Capture_critical_storeDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/capture_critical_strore", method = RequestMethod.GET)
	public ModelAndView capture_critical_strore(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("capture_critical_strore", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Capture_Critical_StoreTile");
	}

	@RequestMapping(value = "/admin/C_C_Saction", method = RequestMethod.POST)
	public @ResponseBody String C_C_Saction(ModelMap Mmap, HttpServletRequest request, HttpSession ses){
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = ses.getAttribute("username").toString();
		try {
			String c_type = request.getParameter("c_type");
			String category = request.getParameter("category");
			String code = request.getParameter("code");
			String nomenc = request.getParameter("nomenc");
			String[] x = code.split(":");
			String[] x1 = nomenc.split(":");

			int i = 0;
			int id = 0;
			CUE_TB_MISO_CAPTURE_CRITITCAL_STORE aon = new CUE_TB_MISO_CAPTURE_CRITITCAL_STORE();
			for (i = 0; i < x.length; i++) {
				String y = x[i];
				String y1 = x1[i];
				if(objDAO.getAllCapture_critical_storeItems(c_type,y).size() == 0) {
					aon.setC_type(c_type);
					aon.setCategory(category);
					aon.setCode(y);
					aon.setNomenc(y1);
					aon.setCreated_by(username);
					aon.setCreated_dt(new Date());
					id = (int) sessionHQL.save(aon);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx.commit();
			msg = String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "No Transction Made";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	@RequestMapping(value = "/getCapture_critical_storeReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage, String pageLength,String Search, String orderColunm, String orderType, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return objDAO.getReportListCapture_critical_store(startPage, pageLength, Search, orderColunm, orderType,sessionUserId);
	}
	@RequestMapping(value = "/getCapture_critical_storeTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getCapture_critical_storeTotalCount(HttpSession sessionUserId, String Search,String name){
		return objDAO.getReportListCapture_critical_storeTotalCount(Search);
	}
	@RequestMapping(value = "/getVEHICLELISTbyType", method = RequestMethod.POST)
	public @ResponseBody List<String> getVEHICLELISTbyType(HttpSession sessionA, String type_of_veh){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("select mct_nomen,mct_main_id from TB_TMS_MCT_MAIN_MASTER where type_of_veh=:type_of_veh");
		q.setParameter("type_of_veh", type_of_veh);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
	@RequestMapping(value = "/getWepone_Eqp", method = RequestMethod.POST)
	public @ResponseBody List<String> getWepone_Eqp(HttpSession sessionA, String type_of_veh){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("select item_type,item_code from CUE_TB_MISO_MMS_ITEM_MSTR where critical_equipment='Yes'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	@RequestMapping(value = "/deletecapture_critical_storeUrl", method = RequestMethod.POST)
	public ModelAndView deletecapture_critical_storeUrl(String deleteid, HttpSession session, ModelMap model){
		List<String> list = new ArrayList<String>();
		list.add(objDAO.Deletecapture_critical_store(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:capture_critical_strore");
	}
	
	@RequestMapping(value = "/critical_store_report", method = RequestMethod.GET)
	public ModelAndView critical_store_report(@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession session, ModelMap Mmap){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("critical_store_report", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Capture_Critical_Store_Report_tile");
	}
	@RequestMapping(value = "/getCaptureReportBYtype", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCaptureReportBYtype(int startPage, String pageLength,String Search, String orderColunm, String orderType, String c_type, HttpSession sessionUserId)
			throws InvalidKeyException,NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeySpecException,InvalidAlgorithmParameterException,IllegalBlockSizeException,BadPaddingException{
		return objDAO.getReport_by_typelist(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,c_type);
	}
	@RequestMapping(value = "/getCaptureby_typeTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getCaptureby_typeTotalCount(HttpSession sessionUserId, String Search,String c_type){
		return objDAO.getReportListBy_typeTotalCount(Search,c_type);
	}
}