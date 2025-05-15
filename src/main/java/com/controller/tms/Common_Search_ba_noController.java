package com.controller.tms;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Search_Ba_AllocDAO;
import com.dao.tms.Search_baDAO;
import com.dao.tms.Search_baDAOImpl;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Common_Search_ba_noController {  
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/Search_ba_no", method = RequestMethod.GET)
	public ModelAndView Search_ba_no(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_ba_no", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Mmap.put("getBaNoCurrentStatus",alist);
		Mmap.put("getFreestockDetails",alist);
		Mmap.put("getInitialIssueUnit",alist);
		Mmap.put("getConvertGsToSPLList",alist);
		Mmap.put("getTransferofVehicle",alist);
		Mmap.put("getBackLoadDetails",alist);
		Mmap.put("getAuctionedDetail",alist);
		return new ModelAndView("Search_ba_noTiles");
	}
		
	@RequestMapping(value = "/admin/Search_ba_no1", method = RequestMethod.POST)
	public ModelAndView Search_ba_no1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ba_no1", required = false) String ba_no,
			@RequestParam(value = "veh_cat1", required = false) String veh_cat)
	{	
		if(ba_no == "" || ba_no.equals(""))
		{
			Mmap.put("msg", "Please Enter BA No.");
		}
		if(validation.ba_noLength(ba_no) == false)
		{
			Mmap.put("msg",validation.ba_noMSG);
			return new ModelAndView("redirect:Search_ba_no");
		}
		if(validation.vehicle_class_codeLength(veh_cat) == false)
		{
			Mmap.put("msg",validation.veh_catMSG);
			return new ModelAndView("redirect:Search_ba_no");
		}
		else
		{				
			Mmap.put("getBaNoCurrentStatus",sba.getBaNoCurrentStatus(ba_no,veh_cat));
			Mmap.put("getFreestockDetails",sba.getFreestockDetails(ba_no,veh_cat));
			Mmap.put("getInitialIssueUnit",sba.getInitialIssueUnit(ba_no,veh_cat));
			if(veh_cat.equals("B"))
			{
				Mmap.put("getConvertGsToSPLList",sba.getConvertGsToSPLList(ba_no,veh_cat));
			}
			Mmap.put("getTransferofVehicle",sba.getTransferofVehicle(ba_no,veh_cat));
			Mmap.put("getBackLoadDetails",sba.getBackLoadDetails(ba_no,veh_cat));
			Mmap.put("getAuctionedDetail",sba.getAuctionedDetail(ba_no,veh_cat));
		}			
	
	Mmap.put("ba_no", ba_no);
	Mmap.put("veh_cat", veh_cat);
	Mmap.put("msg", msg);
	return new ModelAndView("Search_ba_noTiles");
}

	
	@Autowired
	Search_baDAO sba =  new Search_baDAOImpl();
	@Autowired
	Search_Ba_AllocDAO searchDao;
		
		 @RequestMapping(value = "/getSearchbano",method = RequestMethod.POST)
		 public @ResponseBody String getSearchbano(HttpServletRequest request,HttpSession session,String ba_no) {
			 if(sba.ifExistbano(ba_no) == true) {
					 return "Success dtl";
		     }
			 else {
				 return "Failure dtl";
	        }		 
		 }
				 
		@SuppressWarnings("null")
		@RequestMapping(value = "/kmlFileDownload5")
		public void kmlFileDownload5(@ModelAttribute("kmlFileId5") int id,@ModelAttribute("fildname") String fildname,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
				
		  	final int BUFFER_SIZE = 4096;
		    String filePath = searchDao.getImagePath(id,fildname);	
		    model.put("filePath",filePath);
		    ServletContext context = request.getSession().getServletContext();
	       try{
	        if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
	        {
	            @SuppressWarnings("deprecation")
				String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_Image.jpg";
		              File downloadFile = new File(fullPath);
		              FileInputStream inputStream = new FileInputStream(downloadFile);
		              String mimeType = context.getMimeType(fullPath);
		              if (mimeType == null) {
		                  mimeType = "application/octet-stream";
		              }
		              response.setContentType(mimeType);
		              response.setContentLength((int) downloadFile.length());
		              ServletOutputStream outStream = response.getOutputStream();
		              byte[] buffer = new byte[BUFFER_SIZE];
		              int bytesRead = -1;
		              while ((bytesRead = inputStream.read(buffer)) != -1) {
		                  outStream.write(buffer, 0, bytesRead);
		              }
		              inputStream.close();
		              outStream.close();
	        }
	        else
	        {
	        	
	        	    String fullPath =  filePath;      
			        File downloadFile = new File(fullPath);
			        FileInputStream inputStream = new FileInputStream(downloadFile);
			        String mimeType = context.getMimeType(fullPath);
			        if (mimeType == null) {
			            mimeType = "application/octet-stream";
			        }
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			        ServletOutputStream outStream = response.getOutputStream();
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        inputStream.close();
			        outStream.close();
	              }
	        }catch(Exception ex)
	        {
	        	@SuppressWarnings("deprecation")
				String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_Image.jpg";
	              File downloadFile = new File(fullPath);
	              FileInputStream inputStream = new FileInputStream(downloadFile);
	              String mimeType = context.getMimeType(fullPath);
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	              response.setContentType(mimeType);
	              response.setContentLength((int) downloadFile.length());
	              ServletOutputStream outStream = response.getOutputStream();
	              byte[] buffer = new byte[BUFFER_SIZE];
	              int bytesRead = -1;
	              while ((bytesRead = inputStream.read(buffer)) != -1) {
	                  outStream.write(buffer, 0, bytesRead);
	              }
	              inputStream.close();
	              outStream.close();
	       }		        	
	}   	

	@RequestMapping(value = "/getbanodetailsimage",method = RequestMethod.POST)
	public @ResponseBody List<String> getbanodetailsimage(String ba_no) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct engine_image,chasis_img,front_view_image,side_view_image,top_view_image,back_view_image,id from Tms_Banum_Req_Child where ba_no=:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}			
}