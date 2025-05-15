package com.controller.mnh;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.dao.mnh.mstr_Search_Hospital_DAO;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_Search_Hospital_Controller {


	@Autowired
	private mstr_Search_Hospital_DAO SHD;
	@Autowired
	private RoleBaseMenuDAO roledao;
	MNH_CommonController mcommon = new MNH_CommonController();
	
	@RequestMapping(value = "/admin/mnh_hospital_search", method = RequestMethod.GET)
	public ModelAndView mnh_hospital_search(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_hospital_search", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("ml_1",mcommon.getMNHUserList("", session));
		Mmap.put("msg", msg);
		
		return new ModelAndView("mnh_hos_searchTiles");
	}

	//********************Note::Search for Search Hospital Master*****************************************//
	
	@RequestMapping(value = "/admin/HospitalSearchList", method = RequestMethod.POST)
 	public ModelAndView HospitalSearchList(ModelMap Mmap,HttpSession session,HttpServletRequest request,
                @RequestParam(value = "msg", required = false) String msg,
    			@RequestParam(value = "user1", required = false) String username,
    			@RequestParam(value = "sus1", required = false) String sus_no,
    			@RequestParam(value = "unit1", required = false) String unit_name){										
		
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("mnh_hospital_search", roleid);
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
            		if(!username.equals("")) {
            			Mmap.put("user1", username);
            		}
            		if(!sus_no.equals("")) {
            			Mmap.put("sus1", sus_no);
            		}            		
            		ArrayList<ArrayList<String>> list =SHD.gethospitalassignList(username, sus_no);	
            		if(list.size() == 0)
            		{
            			Mmap.put("msg", "Data Not Available.");
            		}
            		else
            		{
            			Mmap.put("list", list);
            		}
            		Mmap.put("unit1", unit_name);
            		Mmap.put("ml_1",mcommon.getMNHUserList("", session));
            		return new ModelAndView("mnh_hos_searchTiles");
		}
	
	
	//********************Note::Delete for Search Hospital Master*****************************************//
	
	@RequestMapping(value = "/deleteSearch_Hospital" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteSearch_Hospital(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				String roleid = sessionA.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("mnh_hospital_search", roleid);
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from Tb_Med_Hosp_Assign where id=:id";
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
				return new ModelAndView("redirect:mnh_hospital_search");
			}
}
