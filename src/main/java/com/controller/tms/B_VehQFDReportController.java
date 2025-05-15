package com.controller.tms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class B_VehQFDReportController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@RequestMapping(value = "/report_of_qfd", method = RequestMethod.GET)
	public ModelAndView type_of_stock(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("report_of_qfdTiles","report_of_qfdCMD", new TB_TMS_MVCR_PARTA_DTL());
	}
	@RequestMapping(value = "/report_of_qfdAction")
	public ModelAndView addItemEntryForm(@ModelAttribute("report_of_qfdCMD") TB_TMS_MVCR_PARTA_DTL rs,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		model.put("msg", "Your Data Saved Successfully");
		return new ModelAndView("redirect:report_of_qfd");	
	}
		
	@RequestMapping(value = "/getMCT")
	public @ResponseBody List<String> getMCT(BigInteger mct) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select mct from TB_TMS_BANUM_DIRCTRY where mct like :mct ").setMaxResults(10);
		q.setParameter("mct", mct+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		List<String> list1 = new ArrayList<String>();
		for(int i = 0;i <list.size();i++) {
			list1.add(hex_asciiDao.asciiToHex(list.get(i)));	
		}
		return list1;
	}  
}
