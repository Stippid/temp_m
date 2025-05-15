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

import com.dao.Reports.Capture_second_third_lineDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_CAPTURE_SECOND_THIRD_LINE_TPT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Capture_Second_Third_LineController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Capture_second_third_lineDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/capture_second_third_line_tpt", method = RequestMethod.GET)
	public ModelAndView capture_second_third_line_tpt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("capture_second_third_line_tpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Capture_second_third_line_TPT_tile");
	}

	@RequestMapping(value = "/admin/getVEHICLElistSecond_third_line", method = RequestMethod.POST)
	public @ResponseBody List<String> getVEHICLElistSecond_third_line(HttpSession sessionA, HttpServletRequest request) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("select mct_nomen,mct_main_id from TB_TMS_MCT_MAIN_MASTER order by 2");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	@RequestMapping(value = "/admin/Capture_second_thirdaction", method = RequestMethod.POST)
	public @ResponseBody String Capture_second_thirdaction(ModelMap Mmap, HttpServletRequest request, HttpSession ses) {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = ses.getAttribute("username").toString();
		try {
			String c_type = request.getParameter("c_type");
			String code = request.getParameter("code");
			String nomenc = request.getParameter("nomenc");
			String[] x = code.split(":");
			String[] x1 = nomenc.split(":");

			int i = 0;
			int id = 0;
			CUE_TB_MISO_CAPTURE_SECOND_THIRD_LINE_TPT aon = new CUE_TB_MISO_CAPTURE_SECOND_THIRD_LINE_TPT();
			for (i = 0; i < x.length; i++) {
				String y = x[i];
				String y1 = x1[i];
				aon.setC_type(c_type);
				aon.setCode(y);
				aon.setNomenc(y1);
				aon.setCreated_by(username);
				aon.setCreated_dt(new Date());
				id = (int) sessionHQL.save(aon);
				sessionHQL.flush();
				sessionHQL.clear();
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
	
	@RequestMapping(value = "/getCapture_second_third_lineList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCapture_second_third_lineList(int startPage, String pageLength,String Search, String orderColunm, String orderType, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return objDAO.getReportListCapture_critical_store(startPage, pageLength, Search, orderColunm, orderType,sessionUserId);
	}
	
	@RequestMapping(value = "/getCapture_second_third_lineCount", method = RequestMethod.POST)
	public @ResponseBody long getCapture_second_third_lineCount(HttpSession sessionUserId, String Search,String name) {
		return objDAO.getReportListCapture_critical_storeTotalCount(Search);
	}
	
	@RequestMapping(value = "/deletecapture_second_third_lineUrl", method = RequestMethod.POST)
	public ModelAndView deletecapture_second_third_lineUrl(String deleteid, HttpSession session, ModelMap model){
		List<String> list = new ArrayList<String>();
		list.add(objDAO.Deletecapture_second_third_store(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:capture_second_third_line_tpt");
	}
	
	
	@RequestMapping(value = "/second_third_line_tpt_report", method = RequestMethod.GET)
	public ModelAndView second_third_line_tpt_report(@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession session, ModelMap Mmap){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("critical_store_report", roleid);
		if (val == false){
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("second_third_line_tpt_report_tile");
	}
	
	@RequestMapping(value = "/second_third_line_tpt_reportData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> second_third_line_tpt_reportData(int startPage, String pageLength,String Search, String orderColunm, String orderType, String c_type, HttpSession sessionUserId)
			throws InvalidKeyException,NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeySpecException,InvalidAlgorithmParameterException,IllegalBlockSizeException,BadPaddingException{
		return objDAO.second_third_line_tpt_reportData(startPage, pageLength, Search, orderColunm, orderType,sessionUserId);
	}
	@RequestMapping(value = "/second_third_line_tpt_reportCount", method = RequestMethod.POST)
	public @ResponseBody long second_third_line_tpt_reportCount(HttpSession sessionUserId, String Search,String c_type){
		return objDAO.second_third_line_tpt_reportCount(Search);
	}
}
