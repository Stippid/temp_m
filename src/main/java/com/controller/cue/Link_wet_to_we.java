package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.ConversionDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/", "user"})
public class Link_wet_to_we {
	
	@Autowired
	private  ConversionDAO convdao;

	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	ValidationController validation = new ValidationController();
	
		@RequestMapping(value = "/admin/link_wet_to_we", method = RequestMethod.GET)
		public ModelAndView Link_wet_to_we_page(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("link_wet_to_we", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("link_wet_weTiles");
		}
		
		@RequestMapping(value = "/admin/link_wet_to_we1", method = RequestMethod.POST)
		public ModelAndView link_wet_to_we1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
				@RequestParam(value = "wet_link_status1", required = false) String wet_link_status,
				@RequestParam(value = "we_pe01", required = false) String we_pe){
				String roleType = session.getAttribute("roleType").toString();
				
				Mmap.put("we_pe01", we_pe);
				Mmap.put("wet_link_status1", wet_link_status);
				Mmap.put("we_pe_no1", we_pe_no);
				List<Map<String, Object>>  list = convdao.getSearchWetToWe(we_pe,we_pe_no,wet_link_status,roleType);
			
			Mmap.put("list", list);
			Mmap.put("list.siz()", list.size());
			Mmap.put("roleType", roleType);
			return new ModelAndView("link_wet_weTiles");
		}
		
		@RequestMapping(value = "/admin/search_link_wet_to_we1", method = RequestMethod.POST)
		public ModelAndView search_link_wet_to_we1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
				@RequestParam(value = "wet_link_status1", required = false) String wet_link_status,
				@RequestParam(value = "we_pe01", required = false) String we_pe){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleid = session.getAttribute("roleid").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				
				Mmap.put("we_pe01", we_pe);
				Mmap.put("wet_link_status1", wet_link_status);
				Mmap.put("we_pe_no1", we_pe_no);
				List<Map<String, Object>>  list =convdao.getSearchWetToWe1(we_pe,we_pe_no,wet_link_status,roleType,roleAccess,roleSusNo);
				
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			//Mmap.put("msg", msg);
			return new ModelAndView("search_link_wet_weTiles");
		}
		
		@RequestMapping(value = "/admin/search_link_wet_to_we", method = RequestMethod.GET)
		public ModelAndView Search_link_wet_to_we_page(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_link_wet_to_we", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}	
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("search_link_wet_weTiles");
		}
					
		@RequestMapping(value = "/getWetpetNoDetailsList",method=RequestMethod.POST)
		public @ResponseBody List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> getWetpetNoDetailsList(String wet_pet_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no=:wet_pet_no");
			q.setParameter("wet_pet_no", wet_pet_no);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_MMS_WET_PET_MAST_DET> list = (List<CUE_TB_MISO_MMS_WET_PET_MAST_DET>) q.list();
			tx.commit();
			session.close();
			return list;			
		}		

		@RequestMapping(value = "/getWetPetFetListValue",method = RequestMethod.POST)
        public @ResponseBody List<String> getWetPetFetListValue(String wet_pet_no,HttpSession sessionUserId){
        	int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	Transaction tx = session.beginTransaction();
        	Query q = session.createQuery("select distinct wet_pet from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet_no=:wet_pet_no");
        	q.setParameter("wet_pet_no", wet_pet_no);
        	@SuppressWarnings("unchecked")
        	List<String> list = (List<String>) q.list();
        	tx.commit();
        	session.close();
			return list;        	
        }
 		
		@RequestMapping(value = "/link_wet_weAction",method = RequestMethod.POST)
		public ModelAndView link_wet_weAction(@ModelAttribute("link_wet_weCMD") CUE_TB_MISO_WEPECONDITIONS rs,HttpServletRequest request,ModelMap model,HttpSession session) {
			
			String wet_pet_no =rs.getWet_pet_no();  // request.getParameter("wet_pet_no");
			String we_pe_no = rs.getWe_pe_no(); //request.getParameter("we_pe_no");
			String we_pe = request.getParameter("we_pe1");
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = sessionHQL.beginTransaction();
			
			try
			{
			 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
			{
				model.put("msg", "Please select Document (WE/PE)");
				return new ModelAndView("redirect:link_wet_to_we");
			}
			 if(validation.checkParent_codeLength(we_pe)  == false)
             {
                      model.put("msg",validation.wetypeMSG);
                     return new ModelAndView("redirect:link_wet_to_we");
             }

			 if(rs.getWe_pe_no() == "")
				{
					model.put("msg", "Please Enter WE/PE No");
					return new ModelAndView("redirect:link_wet_to_we");
				}
			 if(validation.checkWepeLength(rs.getWe_pe_no())  == false)
             {
                      model.put("msg",validation.wepenoMSG);
                     return new ModelAndView("redirect:link_wet_to_we");
             }

			 if(validation.checkWepetabletittleLength(rs.getTable_title())  == false)
             {
                      model.put("msg",validation.wepetitleMSG);
                     return new ModelAndView("redirect:link_wet_to_we");
             }  
			 String wet_pet = request.getParameter("wet_pet1");
			 if(wet_pet == ""  || wet_pet==null || wet_pet=="null" || wet_pet.equals(null) )
			{
				model.put("msg", "Please Select Document (WET/PET/FET)");
				return new ModelAndView("redirect:link_wet_to_we");
			}
			 if(validation.checkWETypeLength(wet_pet)  == false)
             {
				 	model.put("msg",validation.wetpetTypeMSG);
                     return new ModelAndView("redirect:link_wet_to_we");
             }
			 if(rs.getWet_pet_no() == "")
			{
				model.put("msg", "Please Enter WET/PET No");
				return new ModelAndView("redirect:link_wet_to_we");
			}
			 if(validation.checkWetPetLength(rs.getWet_pet_no())  == false)
             {
                      model.put("msg",validation.wetpetnoMSG);
                     return new ModelAndView("redirect:link_wet_to_we");
             }
			 if(validation.checkRemarksLength(rs.getRemarks())  == false)
			{
				model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:link_wet_to_we");					
			}
			Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionUpdate.beginTransaction();
			
			String hqlUpdate = "update CUE_TB_MISO_WEPECONDITIONS r set r.wet_pet_no =:wet_pet_no,wet_link_status=:wet_link_status  where  r.we_pe_no = :we_pe_no and type='3' "; // only for wpn
			sessionUpdate.createQuery( hqlUpdate ).setString( "wet_pet_no", wet_pet_no).setString( "we_pe_no", we_pe_no).setString("wet_link_status", "0").executeUpdate();
			tx.commit();
			sessionUpdate.close();
				
			model.put("msg", "Linked Successfully");
			}	
			catch (Exception e) {
				sessionHQL.getTransaction().rollback();
			}
			
			List<Map<String, Object>>  list =convdao.getSearchWetToWe(we_pe,we_pe_no,"","");
			model.put("we_pe01", we_pe);
			model.put("we_pe_no1", we_pe_no);
			model.put("list", list);
			model.put("list.siz()", list.size());
			return new ModelAndView("link_wet_weTiles");		
			
		}		

        @RequestMapping(value = "/admin/ApprovedWETToWE",method = RequestMethod.POST)
		public ModelAndView setApprovedWETToWE(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "wet_link_status2", required = false) String wet_link_status,
			@RequestParam(value = "we_pe02", required = false) String we_pe){
        	String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String username = session.getAttribute("username").toString();
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			
			String hqlUpdate = "update CUE_TB_MISO_WEPECONDITIONS c set c.wet_link_status = :wet_link_status, approved_rejected_by=:approved_rejected_by where c.id = :id";
			int app = session1.createQuery( hqlUpdate ).setString( "wet_link_status", "1" ).setInteger( "id", appid ).setString("approved_rejected_by", username).executeUpdate();
			tx.commit();
			session1.close();
			if(app > 0) {
				Mmap.put("msg", "Approved Successfully");	
			}else {
				Mmap.put("msg","Approved not Successfully");	
			}
			Mmap.put("we_pe01", we_pe);
			Mmap.put("wet_link_status1", wet_link_status);
			Mmap.put("we_pe_no1", we_pe_no);
			List<Map<String, Object>>  list =convdao.getSearchWetToWe1(we_pe,we_pe_no,wet_link_status,roleType,roleAccess,roleSusNo);

			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			return new ModelAndView("search_link_wet_weTiles");
		}
		
		@RequestMapping(value = "/admin/RejectWETToWE",method = RequestMethod.POST)
		public ModelAndView setRejectWETToWE(@ModelAttribute("rejectid") int rejectid,HttpSession session,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe_no3", required = false) String we_pe_no,
		@RequestParam(value = "wet_link_status3", required = false) String wet_link_status,
		@RequestParam(value = "we_pe03", required = false) String we_pe){
			
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			String hqlUpdate = "update CUE_TB_MISO_WEPECONDITIONS c set c.wet_link_status = :wet_link_status where c.id = :id";
			int app = session1.createQuery( hqlUpdate ).setString( "wet_link_status", "2" ).setInteger( "id", rejectid ).executeUpdate();
			tx.commit();
			session1.close();
			if(app > 0) {
				model.put("msg", "Rejected Successfully");	
			}else {
				model.put("msg", "Rejected not Successfully");	
			}
			model.put("we_pe01", we_pe);
			model.put("wet_link_status1", wet_link_status);
			model.put("we_pe_no1", we_pe_no);
			List<Map<String, Object>>  list =convdao.getSearchWetToWe1(we_pe,we_pe_no,wet_link_status,roleType,roleAccess,roleSusNo);

			model.put("list", list);
			model.put("list.size()", list.size());
			model.put("roleType", roleType);
			return new ModelAndView("search_link_wet_weTiles");
		}

		@RequestMapping(value = "/DeleteWETToWEUrlAdd",method = RequestMethod.POST)
		public @ResponseBody List<String> DeleteWETToWEUrlAdd(int deleteid) {			
			List<String> list2 = new ArrayList<String>();
			list2.add(convdao.DeleteWETToWEUrlAdd(deleteid));
			return list2;
		}
		
		@RequestMapping(value = "/admin/UpdateWETToWE")
		public ModelAndView UpdateWETToWE(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("from CUE_TB_MISO_WEPECONDITIONS where id=:id");
	    	q.setParameter("id", updateid);
	    	CUE_TB_MISO_WEPECONDITIONS upid = (CUE_TB_MISO_WEPECONDITIONS) q.list().get(0);
			session.getTransaction().commit();
			session.close();
			
			model.put("edit_link_wet_weCMD", upid);	
			return new ModelAndView("edit_link_wet_weTiles");
		}
		

		@RequestMapping(value = "/edit_link_wet_weAction",method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView edit_link_wet_weAction(@ModelAttribute("edit_link_wet_weCMD") CUE_TB_MISO_WEPECONDITIONS updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session11,HttpSession sessionEdit) {
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			String wet_pet_no = request.getParameter("wet_pet_no");
			if(validation.checkWetPetLength(wet_pet_no)  == false)
            {
					model.put("msg",validation.wetpetnoMSG);
                    return new ModelAndView("redirect:UpdateWETToWE");
            }
			String username = session11.getAttribute("username").toString();
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update CUE_TB_MISO_WEPECONDITIONS r set r.wet_pet_no =:wet_pet_no,modified_by=:modified_by,modified_on=:modified_on,wet_link_status=:wet_link_status where r.id = :id";   
		    Query query = session.createQuery(hqlUpdate).setString("wet_pet_no", updateid.getWet_pet_no()).setString("modified_by",username).setString("modified_on",  modifydate).setString( "wet_link_status", "0" ).setInteger("id", updateid.getId());
		   
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				model.put("msg", "Updated Successfully");
			}else {
				model.put("msg", "Updated not Successfully");
			}				
			return new ModelAndView("redirect:link_wet_to_we");
		} 
		
		
}
