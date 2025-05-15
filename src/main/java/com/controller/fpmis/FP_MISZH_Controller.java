package com.controller.fpmis;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.UserLoginDAO;
import com.dao.login.UserLoginDAOImpl;
      
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class FP_MISZH_Controller {  
	@Autowired
	private UserLoginDAO userLoginDAO = new UserLoginDAOImpl();  
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t1", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		nPara="FPT1";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	  
	@RequestMapping(value = "/admin/fpSecDashboardFp_t2", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t2(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT2";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}

	@RequestMapping(value = "/admin/fpSecDashboardFp_t3", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t3(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT3";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t4", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t4(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT4";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t5", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t5(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT5";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t6", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t6(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT6";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t7", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t7(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT7";
		String roleSusNo= session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t8", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t8(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT8";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t9", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t9(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT9";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t10", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t10(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT10";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t11", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t11(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT11";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t12", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t12(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT12";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t13", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t13(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT13";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t14", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t14(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT14";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t15", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t15(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT15";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t16", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t16(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		System.out.println("inside 16");
		//
		nPara="FPT16";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t17", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t17(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT17";
		String roleSusNo =session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t18", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t18(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT18";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t19", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t19(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT19";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t20", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t20(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		System.out.println("inside 20");
		//
		nPara="FPT20";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t21", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t21(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT21";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t22", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t22(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT22";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		System.out.println("screen_id in nPara---"+nPara);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t23", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t23(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT23";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t24", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t24(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		
		nPara="FPT24";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t25", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t25(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		
		nPara="FPT25";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t26", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t26(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT26";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t27", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t27(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT27";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t28", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t28(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT28";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		System.out.println("screen_id in nPara---"+nPara);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t29", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t29(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT29";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	}
	@RequestMapping(value = "/admin/fpSecDashboardFp_t30", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t30(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT30";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t31", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t31(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT31";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t32", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t32(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT32";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t33", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t33(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT33";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t34", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t34(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT34";
		
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	
		
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t35", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t35(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT35";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t36", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t36(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT36";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t37", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t37(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT37";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t38", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t38(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT38";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t39", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t39(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT39";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t40", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t40(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT40";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t41", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t41(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT41";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t42", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t42(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT42";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t43", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t43(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT43";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t44", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t44(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT44";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t45", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t45(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT45";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t46", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t46(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT46";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t47", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t47(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT47";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t48", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t48(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT48";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t49", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t49(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT49";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 

	@RequestMapping(value = "/admin/fpSecDashboardFp_t50", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t50(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT50";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t51", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t51(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT51";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t52", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t52(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT52";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t53", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t53(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT53";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t54", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t54(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT54";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t55", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t55(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT55";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t56", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t56(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT56";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");	
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t57", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t57(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT57";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t58", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t58(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT58";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t59", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t59(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT59";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t60", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t60(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT60";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t61", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t61(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT61";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t62", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t62(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT62";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t63", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t63(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT63";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t64", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t64(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT64";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	}      
	@RequestMapping(value = "/admin/fpSecDashboardFp_t65", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t65(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT65";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		  
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t66", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t66(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT66";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t67", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t67(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT67";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t68", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t68(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT68";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t69", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t69(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT69";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t70", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t70(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT70";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t71", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t71(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT71";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t72", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t72(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT72";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	}
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t73", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t73(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT73";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t74", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t74(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT74";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t75", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t75(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT75";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t76", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t76(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT76";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t77", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t77(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT77";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t78", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t78(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT78";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t79", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t79(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT79";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t80", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t80(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT80";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t81", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t81(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT81";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t82", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t82(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT82";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t83", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t83(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT83";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t84", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t84(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT84";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t85", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t85(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT85";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t86", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t86(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT86";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");	
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t87", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t87(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT87";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t88", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t88(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT88";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t89", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t89(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT89";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t90", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t90(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT90";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t91", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t91(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT91";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t92", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t92(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT92";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t93", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t93(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT93";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	
	@RequestMapping(value = "/admin/fpSecDashboardFp_t94", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t94(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT94";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t95", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t95(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT95";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t96", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t96(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT96";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t97", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t97(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT97";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");
		
		
	
		
	} 
	@RequestMapping(value = "/admin/fpSecDashboardFp_t98", method = RequestMethod.GET)
	public ModelAndView fpSecDashboardFp_t98(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {
		//
		nPara="FPT98";
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int roleid =Integer.parseInt(session.getAttribute("roleid").toString());
		int userid = Integer.parseInt(session.getAttribute("userId").toString());		
		int screen_id =Integer.parseInt(request.getSession().getAttribute("screen_id_analytics").toString());
		System.out.println("screen_id in ---"+screen_id);
		String screenurl=userLoginDAO.getScreenUrlFromAnalytics(roleid,userid,screen_id);
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("nPara", nPara);
		Mmap.put("screenurl", screenurl);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_SecDashboardTiles");

	}
	
   
}
