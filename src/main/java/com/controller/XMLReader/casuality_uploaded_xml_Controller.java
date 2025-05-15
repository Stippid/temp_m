package com.controller.XMLReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.psg.xml.XMLDao;
import com.model.psg.xml.XML_FILE_UPLOAD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class casuality_uploaded_xml_Controller {
	XMLController xmlcontroller=new XMLController();

	@Autowired
	private XMLDao xmldao;

	
	@RequestMapping(value = "/admin/casuality_xml_data_view_pop_up", method = RequestMethod.POST)
	public ModelAndView casuality_xml_data_view_pop_up(ModelMap Mmap, HttpSession sessionA,
		 HttpServletRequest request,
			@RequestParam(value = "comm_id_casualty", required = false) String comm_id_casualty,
			@RequestParam(value = "upload_id_casualty", required = false) String upload_id_casualty	
			) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	
		  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	     
		  if(upload_id_casualty!="")
	        {Long a=Long.parseLong(upload_id_casualty);
			  XML_FILE_UPLOAD   assetupd =(XML_FILE_UPLOAD)sessionHQL.get(XML_FILE_UPLOAD.class,a );
				Mmap.put("getCda_account_no",assetupd.getCda_account_no() );
				Mmap.put("getArmyNo", assetupd.getArmyNo());
				Mmap.put("getPresent_p2_date", assetupd.getPresent_p2_date());
				Mmap.put("getPresent_p2_no", assetupd.getPresent_p2_no());
				Mmap.put("getName", assetupd.getName());
				Mmap.put("getSus_no", assetupd.getSus_no());
				Mmap.put("getUnit_name", assetupd.getUnit_name());
				Mmap.put("getUploadedOn",assetupd.getUploadedOn());
				Mmap.put("getRank", assetupd.getRank());
				
	        }
		 

//			Mmap.put("columnname", xmldao.getcolumname());
		Mmap.put("comm_id_casualty", comm_id_casualty);
		Mmap.put("upload_id_casualty",upload_id_casualty );
		return new ModelAndView("casuality_xml_data_view_pop_upTiles");
	}
	
	
	@RequestMapping(value = "/getcasualitycount", method = RequestMethod.POST)
	public @ResponseBody long  getcasualitycount(String comm_id_casualty, String upload_id_casualty,String Search,String status,String orderColunm,String orderType,
		HttpSession sessionUserId
		) {

return xmldao.getcasualitycount(0,Search, orderColunm, orderType,comm_id_casualty,upload_id_casualty, sessionUserId);
	}
	

	@RequestMapping(value = "/getcasualitydata", method = RequestMethod.POST)
	public @ResponseBody  List<Map<String, Object>>  getcasualitydata(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String comm_id_casualty, String upload_id_casualty ) {
		return xmldao.getcasualitydata(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				 0,comm_id_casualty,upload_id_casualty);
	}
	
	
	
	@RequestMapping(value = "/delete_casualty", method = RequestMethod.POST)
	public @ResponseBody  String  delete_casualty(  HttpSession sessionUserId,String xml_file_upload_id, String casualty_no ) {
	String msg="";
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
	String hql = "delete from XML_CASUALTY_DETAILS" 
			+ " where  xml_file_upload_id=:xml_file_upload_id and  casualty_no=:casualty_no";						       					
	Query query = sessionhql.createQuery(hql).setInteger("xml_file_upload_id",Integer.parseInt(xml_file_upload_id))
			.setInteger("casualty_no",Integer.parseInt(casualty_no));						       					
	msg = query.executeUpdate() > 0 ? "Delete Successfully" : " Delete  Unsuccessfull";
    tx.commit();
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
		msg="Delete  Unsuccessfull";
}
	 finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
		}	
			return msg;
			
	}
	
	
	
	
	
	
}
