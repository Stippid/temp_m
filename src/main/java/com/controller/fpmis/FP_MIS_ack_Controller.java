package com.controller.fpmis;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_CES;
import com.models.fpmis.FP_MIS_Master_Model;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FP_MIS_ack_Controller {

	@Autowired           
	private RoleBaseMenuDAO roledao; 
	
	@RequestMapping(value = "/admin/fp_ack", method = RequestMethod.GET)
	public ModelAndView fp_ack(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_ack", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("fp_ackTiles");
	}
	
	
	@RequestMapping(value = "/admin/fp_ack_Action", method = RequestMethod.POST)
	public ModelAndView fp_ack_Action(@ModelAttribute("createHeadCMD") ModelMap Mma,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
 
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_ack", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String Susip = session.getAttribute("ip").toString();
		String Susarmyno = session.getAttribute("army_no").toString();
		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();	
		Query qry0 = session0.createSQLQuery("insert into fp.fp_ack_user (sus_no,userid,army_no,ip_addr,data_upd_date) values ('"+roleSusNo+"','"+username+"','"+Susarmyno+"','"+Susip+"',localtimestamp)");
		int a=qry0.executeUpdate();
	    tx0.commit();
	    session0.close();
	    String nmsg;
	    if (a>0) {
	    	nmsg= "Thank You for prompt Ack.";
	    } else {
	    	nmsg= "Sorry! Ack registration failed. Try Again ...";
	    }
	    Mmap.put("nmsg",nmsg);
		return new ModelAndView("fp_ackTiles");
	}
	
	@RequestMapping(value = "/admin/fp_ack_list", method = RequestMethod.GET)
	public ModelAndView fp_ack_list(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_ack", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String Susip = session.getAttribute("ip").toString();
		String Susarmyno = session.getAttribute("army_no").toString();
		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();
		String nSql;
		nSql="select p.form_code_control,p.user_sus_no,p.unit_name,p.username,p.login_name,p.army_no,p.t,to_char(p.lastupd,'dd-mm-yyyy hh24:mm:ss') as lastupd,o.unit_name as fmnhq from (";
		nSql= nSql + " select '2' as tr,c.hq_type,c.form_code_control,a.user_sus_no,c.unit_name,a.username,a.login_name,";
		nSql= nSql + " b.army_no,b.t,b.lastupd from logininformation a  left join (select army_no,count(*) as t,";
		nSql= nSql + " max(data_upd_date) as lastupd from fp.fp_ack_user group by army_no  ) b on a.army_no=b.army_no ";
		nSql= nSql + " left join fp.fp_tv_orbat_dtl c on a.user_sus_no=c.sus_no where userid in ( ";
		nSql= nSql + " select user_id from userroleinformation where role_id in ( ";
		nSql= nSql + " select distinct roleid from tb_ldap_rolemaster where moduleid=( ";
		nSql= nSql + " select distinct id from tb_ldap_module_master where module_name='FP'))) and c.hq_type in ('1','2','3','4')"; 
		nSql= nSql + " union all ";
		nSql= nSql + " select '1' as tr,c.hq_type,c.form_code_control,a.user_sus_no,c.unit_name,a.username,a.login_name,";
		nSql= nSql + " b.army_no,b.t,b.lastupd from logininformation a  left join (select army_no,count(*) as t,";
		nSql= nSql + " max(data_upd_date) as lastupd from fp.fp_ack_user group by army_no) b on a.army_no=b.army_no ";
		nSql= nSql + " left join fp.fp_tv_orbat_dtl c on a.user_sus_no=c.sus_no where userid in ( ";
		nSql= nSql + " select user_id from userroleinformation where role_id in ( ";
		nSql= nSql + " select distinct roleid from tb_ldap_rolemaster where moduleid=( ";
		nSql= nSql + " select distinct id from tb_ldap_module_master where module_name='FP') )) and c.hq_type in ('-1','0')"; 
		nSql= nSql + " ) p ";
		nSql= nSql + " inner join fp.fp_tv_orbat_dtl o on p.form_code_control=o.form_code_control and o.hq_type in ('-1','0','1','2','3','4')";
		nSql= nSql + " order by p.tr,form_code_control,p.user_sus_no";
		Query qry0 = session0.createSQLQuery(nSql);
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) qry0.list();
		Mmap.put("n_1", list);
	    tx0.commit();
	    session0.close();	    
		return new ModelAndView("fp_ack_listTiles");
	}
	
	@RequestMapping(value = "/admin/fp_ack_List_1", method = RequestMethod.POST)
	public ModelAndView fp_ack_List_1(@ModelAttribute("createHeadCMD") ModelMap Mma,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
 
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_ack", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String Susip = session.getAttribute("ip").toString();
		String Susarmyno = session.getAttribute("army_no").toString();
		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();	
		Query qry0 = session0.createQuery("select c.form_code_control,a.user_sus_no,c.unit_name,a.username,a.login_name,a.army_no,b.army_no,b.t,b.lastupd from logininformation a \r\n" + 
				"left join (select army_no,count(*) as t,max(data_upd_date) as lastupd from fp.fp_ack_user \r\n" + 
				"group by army_no\r\n" + 
				") b on a.army_no=b.army_no \r\n" + 
				"left join fp.fp_tv_orbat_dtl c on a.user_sus_no=c.sus_no\r\n" + 
				"where userid in (\r\n" + 
				"select user_id from userroleinformation where role_id in (\r\n" + 
				"select distinct roleid from tb_ldap_rolemaster where moduleid=(\r\n" + 
				"select distinct id from tb_ldap_module_master where module_name='FP')\r\n" + 
				")\r\n" + 
				") \r\n" + 
				"order by c.hq_type,c.form_code_control,c.sus_no");
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) qry0.list();
		Mmap.put("n_1", qry0.list());
	    tx0.commit();
	    session0.close();
	    	    
		return new ModelAndView("fp_ack_listTiles");
	}
}