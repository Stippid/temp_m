package com.controller.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
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
import com.dao.cue.ceoDAO;
import com.models.CUE_TB_MISO_CES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class cceController {
	
	cueContoller M = new cueContoller();
	@Autowired
	private ceoDAO cdao;
	ValidationController validation = new ValidationController();
	@RequestMapping(value = "/admin/miso_cue_comp_equip_schedule1", method = RequestMethod.POST)
	public ModelAndView miso_cue_comp_equip_schedule1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cce1", required = false) String cce,
			@RequestParam(value = "ces_namem1", required = false) String ces_namem,
			@RequestParam(value = "ces_cces1", required = false) String ces_cces,
			@RequestParam(value = "ces_cces_no1", required = false) String ces_cces_no,
			@RequestParam(value = "ccename1", required = false) String ccename){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("cce1", cce);
			Mmap.put("ces_cces1", ces_cces);
			Mmap.put("ccename1", ccename);
			Mmap.put("ces_namem1", ces_namem);
			Mmap.put("ces_cces_no1", ces_cces_no);
			List<Map<String, Object>> list =cdao.getcceBySearch(cce, ces_cces_no, ccename,roleType)  ;
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		    Mmap.put("roleType", roleType);
		    return new ModelAndView("miso_cue_comp_equip_schedule_Tiles");
	}

	@RequestMapping(value = "/admin/search_miso_cue_comp_equip_schedule1", method = RequestMethod.POST)
	public ModelAndView search_miso_cue_comp_equip_schedule1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ces_cces1", required = false) String ces_cces,
			@RequestParam(value = "ces_cces_no1", required = false) String ces_cces_no,
			@RequestParam(value = "ces_namem1", required = false) String ces_namem,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "ccename1", required = false) String ccename){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("ces_cces1", ces_cces);
			Mmap.put("ccename1", ccename);
			Mmap.put("ces_cces_no1", ces_cces_no);
			Mmap.put("ces_namem1", ces_namem);
			Mmap.put("item_seq_no1", item_seq_no);
			List<Map<String, Object>> list =cdao.getcceBySearch1(ces_cces, ces_cces_no, ccename,roleType)  ;
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		    Mmap.put("roleType", roleType);
		    return new ModelAndView("searchccenameTile");
	}

	@RequestMapping(value = "/deletecceUrlAdd",method = RequestMethod.POST)
	public @ResponseBody List<String> deletecceUrlAdd(int deleteid) {			
			List<String> list2 = new ArrayList<String>();
			list2.add(cdao.setDeletecce(deleteid));
			return list2;
	}
	
	//////////////////////////////edit////////////////////////////////		
	@RequestMapping(value = "/updatecceUrl")
	public ModelAndView updatecceUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			model.put("editcceCmd", cdao.getcceDetailsByid(updateid));
			model.put("msg", msg);
			return new ModelAndView("editupload_cceTiles");
	}	
	
	@RequestMapping(value = "/cceEditAct",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView CUE_TB_MISO_CES(@ModelAttribute("editcceCmd") CUE_TB_MISO_CES updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpSession sessionEdit) 
	{
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			try {
				String item_seq_no = request.getParameter("item_seq_no");			
				if(item_seq_no.equals("") || item_seq_no == "null" || item_seq_no.equals(null) || item_seq_no.isEmpty())
				{
					model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Nomenclature Stores");
					return new ModelAndView("redirect:updatecceUrl");
				}	
				if(validation.checkFormationLength(updateid.getItem_seq_no())  == false){
			 		model.put("msg",validation.nomenMSG);
					return new ModelAndView("redirect:updatecceUrl");
				}
				if(updateid.getAuth_proposed() == null || updateid.getAuth_proposed().equals("null") || updateid.getAuth_proposed().equals("") ||  updateid.getAuth_proposed().equals(null))
				{
					model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Proposed Auth");
					return new ModelAndView("redirect:updatecceUrl");
				}
				
				String auth_amt =  Double.toString(updateid.getAuth_proposed());
				if(validation.checkAuth_amtLength(auth_amt)  == false){
			 		model.put("msg",validation.auth_amtMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				} 
				
				 if(validation.checkRemarksLength(updateid.getRemarks())  == false){
						model.put("msg",validation.remarksMSG);
						return new ModelAndView("redirect:updatecceUrl");
				 }	
				String ces = request.getParameter("ces_ccestype");
				String cesno = request.getParameter("ces_cces_no");		
				String cesname = request.getParameter("ces_cces_name");
				String subitem = request.getParameter("item_seq_no");
				Boolean e = checksubitemnamewithexisting(ces,cesno,cesname,subitem,updateid.getId());
				if(e.equals(false)) {
					String username = session.getAttribute("username").toString();
					Session session1 = HibernateUtilNA.getSessionFactory().openSession();
					String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					Transaction tx = session1.beginTransaction();
					String hql = "UPDATE CUE_TB_MISO_CES SET item_seq_no=:item_seq_no, remarks =:remarks, auth_proposed=:auth_proposed,modified_by=:modified_by,modified_on=:modified_on, status='0'  WHERE id=:id";
					Query query = session1.createQuery(hql).setString("item_seq_no", subitem).setString("remarks",updateid.getRemarks()).setDouble("auth_proposed",updateid.getAuth_proposed()).setString("modified_by", username).setString("modified_on", modifydate).setInteger("id",updateid.getId());
				   int rowCount = query.executeUpdate();
					tx.commit();
					session1.close();
					if(rowCount > 0) {
						model.put("msg", "Updated Successfully");
					}else {
						model.put("msg", "Updated not Successfully");
					}
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");				
				}
				else {
					 model.put("msg", "Data already exist !");
					 model.put("editcceCmd", cdao.getcceDetailsByid(updateid.getId()));
					 return new ModelAndView("editupload_cceTiles");
				}
				}
				
			catch (Exception e) {
				// TODO: handle exception
			}
			return new ModelAndView("redirect:miso_cue_comp_equip_schedule");				
	} 
	
	@SuppressWarnings("unchecked")
	public Boolean checksubitemnamewithexisting(String ces,String cesno,String cesname,String subitem,int id) {
		String hql="";	
		hql=" from CUE_TB_MISO_CES where ces_cces=:ces_cces and ces_cces_no =:ces_cces_no and ces_cces_name =:ces_cces_name and item_seq_no =:item_seq_no and id!=:id ";	
		List<CUE_TB_MISO_WEPE_PERS_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q= session.createQuery(hql);
			q.setParameter("ces_cces", ces);
			q.setParameter("id", id);
			q.setParameter("ces_cces_no", cesno);
			q.setParameter("ces_cces_name", cesname);
			q.setParameter("item_seq_no", subitem);
			users = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) q.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 	
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
		
	@RequestMapping(value = "/getexistingces_cces_no",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getexistingces_cces_no(String val,HttpSession sessionA,String ces_cces_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException 
	{	
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q;		
			q = session.createQuery("select distinct ces_cces_no from CUE_TB_MISO_CES where ces_cces =:ces_cces and upper(ces_cces_no) like:ces_cces_no order by ces_cces_no").setMaxResults(10) ;
			q.setParameter("ces_cces",val);
			q.setParameter("ces_cces_no", ces_cces_no.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close(); 
			return M.getAutoCommonList(sessionA,list1);
	}

	
	@RequestMapping(value = "/getdetailexistingces_cces_no",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_CES> getdetailexistingces_cces_no(String val,String cestype) 
	{	
			Session session = HibernateUtil.getSessionFactory().openSession();		 
			Transaction tx = session.beginTransaction();
			Query q;		
		    q = session.createQuery(" from CUE_TB_MISO_CES where ces_cces_no =:ces_cces_no and ces_cces =:ces_cces ") ;
			q.setParameter("ces_cces_no",val);
			q.setParameter("ces_cces",cestype);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_CES> list = (List<CUE_TB_MISO_CES>) q.list();
			tx.commit();
			session.close();
			return list;
	}
	
	@RequestMapping(value = "/getdetailexistingces_cces_name",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_CES> getdetailexistingces_cces_name(String val) 
	{	
			Session session = HibernateUtil.getSessionFactory().openSession();		 
			Transaction tx = session.beginTransaction();
			Query q;		
			q = session.createQuery(" from CUE_TB_MISO_CES where ces_cces_name =:ces_cces_name ") ;
			q.setParameter("ces_cces_name",val);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_CES> list = (List<CUE_TB_MISO_CES>) q.list();
			tx.commit();
			session.close();
			return list;
	}
	
	@RequestMapping(value = "/ccenoforsearchcce",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_CES> ccenoforsearchcce(String val,String cestype) 
	{	
			Session session = HibernateUtil.getSessionFactory().openSession();		 
			Transaction tx = session.beginTransaction();
			Query q = null;		
			if(val!="")
			{
			  q = session.createQuery("select item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code in (select distinct ces_cces_name from  CUE_TB_MISO_CES where ces_cces_no =:ces_cces_no and ces_cces =:ces_cces) order by item_type") ;
			}
			else
			{
				q = session.createQuery("select item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code  in (select distinct ces_cces_name from  CUE_TB_MISO_CES where ces_cces =:ces_cces) order by item_type") ;
			}
			q.setParameter("ces_cces_no", val);
			q.setParameter("ces_cces", cestype);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_CES> list = (List<CUE_TB_MISO_CES>) q.list();
			tx.commit();
			session.close();
			return list;
	}
	
}
