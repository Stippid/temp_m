package com.controller.mnh;
import java.math.BigInteger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mnh_input_strengthDetailsDAO;
import com.models.Tbl_CodesForm;
import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;
import com.models.mnh.Tb_Med_Strength;
import com.persistance.util.HibernateUtil;




@Controller
@RequestMapping(value = {"admin","/" ,"user"})

public class input_strength_detailsController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private mnh_input_strengthDetailsDAO sd; 
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;

	MNH_CommonController mcommon = new MNH_CommonController();
	
	ValidationController validation = new ValidationController();
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/mnh_input_strengthDetails", method = RequestMethod.GET)
	public ModelAndView mnh_input_strengthDetails(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_strengthDetails", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		

		//ArrayList<ArrayList<String>> list1 = sd.GetAlljco_Details();
		//Mmap.put("list1", list1);
		
		//ArrayList<ArrayList<String>> list2 = sd.GetAllor_Details();
		//Mmap.put("list2", list2);
      
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("getCommandList", getCommandDetailsList_strength());
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_strengthTiles");
	}
	public List<Tbl_CodesForm> getCommandDetailsList_strength() {
		Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionComnd.beginTransaction();
		Query q = sessionComnd.createQuery("select form_code_control,unit_name,sus_no from Miso_Orbat_Unt_Dtl "
				+ "where sus_no in(select sus_no as col_0_0_ from "
				+ "Tbl_CodesForm where level_in_hierarchy='Command') and status_sus_no='Active'");
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionComnd.close();
		return list;
	}
	
	/* 
	 @RequestMapping(value = "/GetAllorDetails", method = RequestMethod.POST)
	 public @ResponseBody List<String> GetAllorDetails(String ef) {
	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction tx = session.beginTransaction();

	Query q = session.createQuery("select count(army_no) as total_jco from TB_CENSUS_JCO_OR_PARENT  where unit_sus_no=:unit_sus_no and category='OR'");

	q.setParameter("unit_sus_no", ef);


	@SuppressWarnings("unchecked")

	List<String> list = (List<String>) q.list();
	
	tx.commit();

	session.close();

	return list;
           
}*/
	 
	 @RequestMapping(value = "/GetAlloffDetails", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> GetAlloffDetails(String ef) {
			List<ArrayList<String>> list = sd.GetAlloffDetails(ef);
			return list;
		}
	 @RequestMapping(value = "/GetAlljcoDetails", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> GetAlljcoDetails(String ef) {
			List<ArrayList<String>> list = sd.GetAlljcoDetails_j(ef);
			return list;
		}
	 @RequestMapping(value = "/GetAllorDetails", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> GetAllorDetails(String ef) {
			List<ArrayList<String>> list = sd.GetAll_or_Details_o(ef);
			return list;
		}
	@RequestMapping(value = "/strength_details_inputAction", method = RequestMethod.POST)
    public ModelAndView strength_details_inputAction(@ModelAttribute("strength_details_inputCMD") Tb_Med_Strength rs,
                    HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		   Boolean val = roledao.ScreenRedirect("mnh_input_strengthDetails", session.getAttribute("roleid").toString());
		       if(val == false) {
		         return new ModelAndView("AccessTiles");
		        }
		      if(request.getHeader("Referer") == null ) {
		          msg = "";
		          return new ModelAndView("redirect:bodyParameterNotAllow");
		        }
            
		    String username = session.getAttribute("username").toString();
            int id = rs.getId() > 0 ? rs.getId() : 0;	
            String cmd = request.getParameter("cmd");
            String qtr = request.getParameter("qtr");
            int year = Integer.parseInt(request.getParameter("year"));
            String off_male = request.getParameter("off_male1");
            String off_female = request.getParameter("off_female1");
            String cadet = request.getParameter("cadet1");
            String lady_cadet = request.getParameter("lady_cadet1");
            String mns = request.getParameter("mns1");
            String mns_cadet = request.getParameter("mns_cadet1");
            String jco = request.getParameter("jco1");
            String ort = request.getParameter("ort1");
            String dsc_jco = request.getParameter("dsc_jco1");
            String dsc_or = request.getParameter("dsc_or1");
            String rect = request.getParameter("rect1");
            String high_offr = request.getParameter("high_offr1");
            String high_jco = request.getParameter("high_jco1");
            String high_or = request.getParameter("high_or1");
            String total = request.getParameter("total1");
            
           String unit_name1 = request.getParameter("unit_name1");
           String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no1");
			if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
			Integer off_male1 = 0;
			if (off_male != "" && !off_male.equals("")) {
				off_male1 = Integer.parseInt(off_male);
			} 
			Integer off_female1 = 0;
			if (off_female != "" && !off_female.equals("")) {
				off_female1 = Integer.parseInt(off_female);
			}
			Integer cadet1 = 0;
			if (cadet != "" && !cadet.equals("")) {
				cadet1 = Integer.parseInt(cadet);
			}
			Integer lady_cadet1 = 0;
			if (lady_cadet != "" && !lady_cadet.equals("")) {
				lady_cadet1 = Integer.parseInt(lady_cadet);
			}
			Integer mns1 = 0;
			if (mns != "" && !off_female.equals("")) {
				mns1 = Integer.parseInt(mns);
			}
			Integer mns_cadet1 = 0;
			if (mns_cadet != "" && !off_female.equals("")) {
				mns_cadet1 = Integer.parseInt(mns_cadet);
			}
			Integer jco1 = 0;
			if (jco != "" && !jco.equals("")) {
				jco1 = Integer.parseInt(jco);
			}
			Integer ort1 = 0;
			if (ort != "" && !ort.equals("")) {
				ort1 = Integer.parseInt(ort);
			}
			Integer dsc_jco1 = 0;
			if (dsc_jco != "" && !dsc_jco.equals("")) {
				dsc_jco1 = Integer.parseInt(dsc_jco);
			}
			Integer dsc_or1 = 0;
			if (dsc_or != "" && !dsc_or.equals("")) {
				dsc_or1 = Integer.parseInt(dsc_or);
			}
			Integer rect1 = 0;
			if (rect != "" && !rect.equals("")) {
				rect1 = Integer.parseInt(rect);
			}
			Integer high_offr1 = 0;
			if (high_offr != "" && !high_offr.equals("")) {
				high_offr1 = Integer.parseInt(high_offr);
			}
			
			Integer high_jco1 = 0;
			if (high_jco != "" && !high_jco.equals("")) {
				high_jco1 = Integer.parseInt(high_jco);
			}
			Integer high_or1 = 0;
			if (high_or != "" && !high_or.equals("")) {
				high_or1 = Integer.parseInt(high_or);
			}
			Integer total1 = 0;
			if (total != "" && !total.equals("")) {
				total1 = Integer.parseInt(total);
			}
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {
				Long d = sd.checkstrengthExistlNo(cmd, qtr, year, id);
	
				if (id == 0) {
					rs.setCreated_by(username);
					rs.setCreated_on(new Date());
					rs.setOff_male(off_male1);
					rs.setOff_female(off_female1);
					rs.setCadet(cadet1);
					rs.setLady_cadet(lady_cadet1);
					rs.setMns(mns1);
					rs.setMns_cadet(mns_cadet1);
                     rs.setJco(jco1);
                    rs.setOrt(ort1);
                    rs.setDsc_jco(dsc_jco1);
                    rs.setDsc_or(dsc_or1);
                    rs.setRect(rect1);
                    rs.setHigh_offr(high_offr1);
                    rs.setHigh_jco(high_jco1);
                    rs.setHigh_or(high_or1);
                    rs.setTotal(total1);
					if (d == 0) {
						session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					}
					if (d > 0)
	
					{
						model.put("msg", "Data already Exist.");
					}
				}
	
					} catch (RuntimeException e) {
						try {
							tx.rollback();
							model.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							model.put("msg", "Couldn’t roll back transaction " + rbe);
						}
						throw e;
			
					} finally {
						if (session1 != null) {
							session1.close();
						}
					}
	
			return new ModelAndView("redirect:mnh_input_strengthDetails");
		  } 

	@RequestMapping(value = "/admin/mnh_input_strengthSearch", method = RequestMethod.GET)
	public ModelAndView mnh_input_strengthSearch(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_strengthSearch", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		

		Mmap.put("getCommandList",getCommandDetailsList_strength());
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_strength_searchTiles");
	}
	
	@RequestMapping(value = "/search_strength_details_input",method = RequestMethod.POST)
	public ModelAndView search_strength_details_input(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("cmd1") String cmd1, String qtr1,
			String yr1) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("mnh_input_strengthSearch",session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);

		model.put("r_1", l1);

		cmd1 = cmd1.replace("&#40;", "(");
		cmd1 = cmd1.replace("&#41;", ")");

		List<Map<String, Object>> list = sd.search_strength_details_input(cmd1, qtr1, yr1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		model.put("getCommandList",getCommandDetailsList_strength());
		model.put("cmd1", cmd1);
		model.put("qtr1", qtr1);
		model.put("yr1", yr1);
		return new ModelAndView("mnh_input_strength_searchTiles");
	}
	
	@RequestMapping(value = "/deletestrength", method = RequestMethod.POST)
	public ModelAndView deletestrength(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,@RequestParam(value = "msg", required = false) String msg) {
		List<String> liststr = new ArrayList<String>();

		String roleid = session1.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_input_strengthSearch", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			
			String hqlUpdate = "delete from Tb_Med_Strength where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_input_strengthSearch");
	}
	@RequestMapping(value = "/edit_strength_details" ,method = RequestMethod.POST)
	public ModelAndView edit_strength_details(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_strengthSearch", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}




		
		
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("date", date1);
		
		Tb_Med_Strength dailyDetails = sd.updatestrength_detailsByid(Integer.parseInt(updateid));
		
		model.put("edit_strength_details_inputCMD", dailyDetails);
		model.put("msg", msg);
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("ml_2", mcommon.getMedSystemCodeQua("QUARTER", "", sessionEdit));
		model.put("getCommandList",getCommandDetailsList_strength());
		return new ModelAndView("editmnh_input_strengthTiles");
	}
	
	@RequestMapping(value = "/edit_strength_details_inputAction", method = RequestMethod.POST)
	public ModelAndView edit_strength_details_inputAction(@ModelAttribute("edit_strength_details_inputCMD") Tb_Med_Strength rs,
			HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,
			ModelMap Mmap, HttpSession session) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_input_strengthSearch", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		 String username = session.getAttribute("username").toString();
         int id = rs.getId() > 0 ? rs.getId() : 0;	
         String cmd = request.getParameter("cmd");
         String qtr = request.getParameter("qtr");
         int year = Integer.parseInt(request.getParameter("year"));
         String off_male = request.getParameter("off_male1");
         String off_female = request.getParameter("off_female1");
         String cadet = request.getParameter("cadet1");
         String lady_cadet = request.getParameter("lady_cadet1");
         String mns = request.getParameter("mns1");
         String mns_cadet = request.getParameter("mns_cadet1");
         String jco = request.getParameter("jco1");
         String ort = request.getParameter("ort1");
         String dsc_jco = request.getParameter("dsc_jco1");
         String dsc_or = request.getParameter("dsc_or1");
         String rect = request.getParameter("rect1");
         String high_offr = request.getParameter("high_offr1");
         String high_jco = request.getParameter("high_jco1");
         String high_or = request.getParameter("high_or1");
         String total = request.getParameter("total1");
         
        String unit_name1 = request.getParameter("unit_name1");
        String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			String sus_no1=request.getParameter("sus_no1");
			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				sus_no1 = roleSusNo;
				
			}
			Integer off_male1 = 0;
			if (off_male != "" && !off_male.equals("")) {
				off_male1 = Integer.parseInt(off_male);
			} 
			Integer off_female1 = 0;
			if (off_female != "" && !off_female.equals("")) {
				off_female1 = Integer.parseInt(off_female);
			}
			Integer cadet1 = 0;
			if (cadet != "" && !cadet.equals("")) {
				cadet1 = Integer.parseInt(cadet);
			}
			Integer lady_cadet1 = 0;
			if (lady_cadet != "" && !lady_cadet.equals("")) {
				lady_cadet1 = Integer.parseInt(lady_cadet);
			}
			Integer mns1 = 0;
			if (mns != "" && !off_female.equals("")) {
				mns1 = Integer.parseInt(mns);
			}
			Integer mns_cadet1 = 0;
			if (mns_cadet != "" && !off_female.equals("")) {
				mns_cadet1 = Integer.parseInt(mns_cadet);
			}
			Integer jco1 = 0;
			if (jco != "" && !jco.equals("")) {
				jco1 = Integer.parseInt(jco);
			}
			Integer ort1 = 0;
			if (ort != "" && !ort.equals("")) {
				ort1 = Integer.parseInt(ort);
			}
			Integer dsc_jco1 = 0;
			if (dsc_jco != "" && !dsc_jco.equals("")) {
				dsc_jco1 = Integer.parseInt(dsc_jco);
			}
			Integer dsc_or1 = 0;
			if (dsc_or != "" && !dsc_or.equals("")) {
				dsc_or1 = Integer.parseInt(dsc_or);
			}
			Integer rect1 = 0;
			if (rect != "" && !rect.equals("")) {
				rect1 = Integer.parseInt(rect);
			}
			Integer high_offr1 = 0;
			if (high_offr != "" && !high_offr.equals("")) {
				high_offr1 = Integer.parseInt(high_offr);
			}
			
			Integer high_jco1 = 0;
			if (high_jco != "" && !high_jco.equals("")) {
				high_jco1 = Integer.parseInt(high_jco);
			}
			Integer high_or1 = 0;
			if (high_or != "" && !high_or.equals("")) {
				high_or1 = Integer.parseInt(high_or);
			}
			Integer total1 = 0;
			if (total != "" && !total.equals("")) {
				total1 = Integer.parseInt(total);
			}
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {
				Long d = sd.checkstrengthExistlNo(cmd, qtr, year,rs.getId());
				rs.setCreated_by(username);
				rs.setCreated_on(new Date());
				rs.setOff_male(off_male1);
				rs.setOff_female(off_female1);
				rs.setCadet(cadet1);
				rs.setLady_cadet(lady_cadet1);
				rs.setMns(mns1);
				rs.setMns_cadet(mns_cadet1);
                rs.setJco(jco1);
               rs.setOrt(ort1);
               rs.setDsc_jco(dsc_jco1);
               rs.setDsc_or(dsc_or1);
               rs.setRect(rect1);
               rs.setHigh_offr(high_offr1);
               rs.setHigh_jco(high_jco1);
               rs.setHigh_or(high_or1);
               rs.setTotal(total1);
				
					
							
                 if (d == 0) 
  			   {  					
  					  Mmap.put("msg", sd.updatestrength(rs, username));
    				} 
     				if (d > 0) 
     				{
     					Mmap.put("msg", "Data already Exist.");
    				}				
  				
  			} catch (RuntimeException e) {
  				try {
  					
  					Mmap.put("msg", "roll back transaction");
  				} catch (RuntimeException rbe) {
  					Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
  				}
  				throw e;
  			} finally {
  				
  			}
  			return new ModelAndView("redirect:mnh_input_strengthSearch");
  		}
  			
		
	
	
	
	
}
