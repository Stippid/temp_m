package com.controller.XMLReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.BigIntegerType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Appointment_Controller;
import com.controller.psg.update_offr_data.Change_Of_Rank_controller;
import com.controller.psg.update_offr_data.Marraige_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.dao.psg.xml.XMLDao;
import com.model.psg.xml.XML_CASUALTY_DETAILS;
import com.model.psg.xml.XML_FILE_UPLOAD;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.models.psg.Master.TB_MEDAL_TYPE;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_MEDICAL_CATEGORY_HISTORY;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_MISO_ROLE_HDR_STATUS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.models.psg.update_census_data.TB_REEMP_EXTENTION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class XMLController {
	@Autowired
	public DataSource dataSource;

	@Autowired
	private Search_PostOutDao pod;

	@Autowired
	private XMLDao xmldao;

	@Autowired
	Search_UpdateOffDataDao UOD;
	Marraige_Controller Mrg = new Marraige_Controller();
	Appointment_Controller Ap = new Appointment_Controller();
	Psg_CommonController com = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private Report_3008DAO report_3008DAO;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	

	@RequestMapping(value = "/admin/Xml_reader", method = RequestMethod.GET)
	public ModelAndView Xml_reader(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("xml_reader_Tiles");
	}

	@RequestMapping(value = "/upload_xml", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadXml(@RequestParam(value = "doc1", required = false) MultipartFile[] upload, ModelMap mp
			, HttpSession session, HttpSession sessionUserId,HttpServletRequest request) throws IOException, ParseException, ParserConfigurationException, 
	SAXException {
		String msg="";
		int count =0;
		int count_fail=0;
		int count_already =0;
		List<String> result=new ArrayList<String>();
		StringBuilder savedIdsStringBuilder = new StringBuilder();
		   try {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

	      for (int file_uploded = 0; file_uploded< upload.length; file_uploded++) {
	    	  Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	  		int status_xml=0;
	  		Transaction tx = sessionhql.beginTransaction();
	  		int tranaction=0;
	  	      String username = session.getAttribute("username").toString();
	  	      
	try {
		BigInteger comm_id_xml=BigInteger.ZERO;
		  int cen_id=0;
			String fileName = upload[file_uploded].getOriginalFilename();
			File file = new File(fileName);
			byte[] fileData = upload[file_uploded].getBytes();
			
			File tempFile = File.createTempFile("uploaded-", ".xml");
          upload[file_uploded].transferTo(tempFile);
          
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(tempFile); 
	            Element root = document.getDocumentElement();
	            String present_p2_no=""; 
	            String Upload_msg="";
	            String personnel_no="";
	            String rank="";
	            String auth ="";
	            String authdate="";
	            String tos="";
	            String from_sus_no="";
	            String name="";
	            String Rank_Code="";
	            String Desccription_not_found="";
	            String CDAACNo="";
	            BigInteger comm_id_1=null;
	            NodeList part2OrderOfficersList = document.getElementsByTagName("Part2OrderOfficers");
	            int entityId=0;
	            int child_entity_id=0;
	          
	       
	            for (int i = 0; i < part2OrderOfficersList.getLength(); i++) {
	                Element part2OrderOfficers = (Element) part2OrderOfficersList.item(i);
	                String PresentP2No = part2OrderOfficers.getElementsByTagName("PresentP2No").item(0).getTextContent();	              
	                String unitName = part2OrderOfficers.getElementsByTagName("UnitName").item(0).getTextContent();
	                String PresentP2Date = part2OrderOfficers.getElementsByTagName("PresentP2Date").item(0).getTextContent();
	                String FmDate = part2OrderOfficers.getElementsByTagName("FmDate").item(0).getTextContent();
	                String Officer = part2OrderOfficers.getElementsByTagName("Officer").item(0).getTextContent();
	                String ArmyNo = part2OrderOfficers.getElementsByTagName("ArmyNo").item(0).getTextContent();
	                String Rank = part2OrderOfficers.getElementsByTagName("Rank").item(0).getTextContent();
	                String RankCode = part2OrderOfficers.getElementsByTagName("RankCode").item(0).getTextContent();
	                String Name = part2OrderOfficers.getElementsByTagName("Name").item(0).getTextContent();
	                CDAACNo=part2OrderOfficers.getElementsByTagName("CDAACNo").item(0).getTextContent();
	                tos= FmDate;
	                auth =PresentP2No;
	                authdate=PresentP2Date;
	                personnel_no=ArmyNo;
	                rank=Rank;
	                name=Name;
	                Rank_Code =RankCode;
	                present_p2_no=PresentP2No;
	            
	            }
	            
	            String susno="";
	            String UnitName="";
	            NodeList  unitDetilslist= root.getElementsByTagName("UnitDtls");
	            for (int k = 0; k< unitDetilslist.getLength(); k++) {
	                Element unitDetilsElement = (Element) unitDetilslist.item(k);
	             susno = unitDetilsElement.getElementsByTagName("SUSNo").item(0).getTextContent();
	                 UnitName = unitDetilsElement.getElementsByTagName("UnitName").item(0).getTextContent();
	            }   
	            
	            
//	            String hql1 = "SELECT sus_no, status_sus_no, unit_name "
//                        + " FROM Miso_Orbat_Unt_Dtl "
//                        + " WHERE (sus_no LIKE :susno OR unit_name = :unitname) AND status_sus_no = 'Active' ";
//            Query query1 = sessionhql.createQuery(hql1);
//            query1.setParameter("susno", susno + "%").setParameter("unitname", UnitName);
//
//    			List <Object[]>list1 = query1.list();
//    			if(list1.isEmpty())
//    			{
//    				msg=" SUS No not Found";
//    				Upload_msg="Failure";
//    				   tx.rollback();
//    		      	   tranaction=1;
//    		      	   break;
//    			}
	            
	            
	 		   String hql_xml = "select id from XML_FILE_UPLOAD where present_p2_no=:present_p2_no  and status!=-1 ";
				Query query_xml = sessionhql.createQuery(hql_xml);
				query_xml.setParameter("present_p2_no",present_p2_no);
				List <Long>list_xml = query_xml.list();
				if(!list_xml.isEmpty())
				{
					count_already++;
					continue;
				}
				
	    		String hql6 = "select comm.id from TB_TRANS_PROPOSED_COMM_LETTER comm \r\n"			
				+ " where   comm.personnel_no =:personnel_no and comm.status in('1','5')";
		Query query6 = sessionhql.createQuery(hql6);
		query6.setParameter("personnel_no",personnel_no);
		
		List <BigInteger>list6 = query6.list();
	
       if(list6.size()!=0)
       {
    	   comm_id_xml=(BigInteger) list6.get(0);
    	   cen_id = getCensusid((BigInteger) list6.get(0));
       }
 
	            Session sessionhql2 = HibernateUtil.getSessionFactory().openSession();
	    		Transaction tx2 = sessionhql2.beginTransaction();
	    		try {
	           XML_FILE_UPLOAD xmlFile = new XML_FILE_UPLOAD();	       
	           xmlFile.setFileName(fileName);
               xmlFile.setArmyNo(personnel_no);
               xmlFile.setErrorMsg(msg);
               xmlFile.setName(name);
               xmlFile.setFileData(fileData);
               xmlFile.setUploadedStatus(Upload_msg);
               xmlFile.setUploadedOn(new Timestamp(System.currentTimeMillis()));         
               xmlFile.setStatus(status_xml);
               xmlFile.setComm_id(comm_id_xml);
               xmlFile.setPresent_p2_no(present_p2_no);
               xmlFile.setUnit_name(UnitName);
               xmlFile.setSus_no(susno);
               xmlFile.setCda_account_no(CDAACNo);
               xmlFile.setCensus_id(cen_id);
               xmlFile.setRank(rank);
               if(authdate!="")
               {
            	   xmlFile.setPresent_p2_date(format.parse(authdate));
               }

//              Serializable identifier = sessionhql2.save(xmlFile);
//              String savedId = String.valueOf(identifier);
             
//              Long a = (Long) identifier;      
            
              if (savedIdsStringBuilder.length() > 0) {
              savedIdsStringBuilder.append(",");
            }
    
              NodeList casualtyNodes = root.getElementsByTagName("CasualtyDetails");
          String insert_boolean=    xmldao.insertCasualty(casualtyNodes,status_xml,personnel_no,xmlFile); 
   if(insert_boolean!="")       {
	           tx2.commit();	        
	            	  count++;	           
	           savedIdsStringBuilder.append(insert_boolean);        
   }
   if(insert_boolean.equals(""))
   {
	   count_fail++;
   }
	           tempFile.delete();

	    		}
	    		catch (Exception e){
	    			e.printStackTrace();
	    			tx2.rollback();
	    			count_fail++;
	    		}
	          
	           
	           
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			count_fail++;
			mp.put("msg", "Error processing the file.");			
	}
		 finally {
				if (sessionhql != null) {
					sessionhql.close();
				}
			}	
	}
		
		   }	
		   
	   catch(Exception e){
		   mp.put("msg", "Error processing the file.");   
		   e.printStackTrace();
		   count_fail++;
	   }
		   String savedIdsString = savedIdsStringBuilder.toString();

		   	   msg="";
		   	     if(count!=0)
		   	     {
		   	    	msg+= count+" Files Uploaded  Successfully \n";
		   	     }
		   	     if (count_fail!=0)
		   	     {
		   	    	 msg+=count_fail+ " Files Not Uploaded \n";	    	
		   	     }
		   	     if(count_already!=0) {
		   	    	 msg+=count_already+ " Files Already Exist \n";	    	
		   		    }
		   	     if(savedIdsString!="0")
		   	     {
		   	    	 msg+= " Change Status To View The Failure File";
		   	     }
		    
		   
		    result.add(savedIdsString);
		   result.add(msg);		   
		   return result;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value = "/upload_xml", method = RequestMethod.POST)
//	public @ResponseBody List<String> uploadXml(@RequestParam(value = "doc1", required = false) MultipartFile[] upload, ModelMap mp
//			, HttpSession session, HttpSession sessionUserId,HttpServletRequest request) throws IOException, ParseException, ParserConfigurationException, 
//	SAXException {
//		String msg="";
//		int count =0;
//		int failure=0;
//		int count_already =0;
//		List<String> result=new ArrayList<String>();
//		StringBuilder savedIdsStringBuilder = new StringBuilder();
//		   try {
//			   
//			   
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//
////		   String msg="";
//	      for (int file_uploded = 0; file_uploded< upload.length; file_uploded++) {
//	    	  Session sessionhql = HibernateUtil.getSessionFactory().openSession();
//	  		int status_xml=-1;
//	  		Transaction tx = sessionhql.beginTransaction();
//	  		int tranaction=0;
//	  	      String username = session.getAttribute("username").toString();
//	  	      
//	try {
//		BigInteger comm_id_xml=BigInteger.ZERO;
//			String fileName = upload[file_uploded].getOriginalFilename();
//			File file = new File(fileName);
//			byte[] fileData = upload[file_uploded].getBytes();
//			
//			File tempFile = File.createTempFile("uploaded-", ".xml");
//          upload[file_uploded].transferTo(tempFile);
//          
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(tempFile);
//            int update_casuality=0;
//	            Element root = document.getDocumentElement();
//	            String present_p2_no=""; 
//	            String Upload_msg="";
//	            String personnel_no="";
//	            String rank="";
//	            String auth ="";
//	            String authdate="";
//	            String tos="";
//	            String from_sus_no="";
//	            String name="";
//	            String Rank_Code="";
//	            String Desccription_not_found="";
//	            String CDAACNo="";
//	            BigInteger comm_id_1=null;
//	            NodeList part2OrderOfficersList = document.getElementsByTagName("Part2OrderOfficers");
//	            int entityId=0;
//	            int child_entity_id=0;
//	            int cen_id=0;
//	       
//	            for (int i = 0; i < part2OrderOfficersList.getLength(); i++) {
//	                Element part2OrderOfficers = (Element) part2OrderOfficersList.item(i);
//	                String PresentP2No = part2OrderOfficers.getElementsByTagName("PresentP2No").item(0).getTextContent();	              
//	                String unitName = part2OrderOfficers.getElementsByTagName("UnitName").item(0).getTextContent();
//	                String PresentP2Date = part2OrderOfficers.getElementsByTagName("PresentP2Date").item(0).getTextContent();
//	                String FmDate = part2OrderOfficers.getElementsByTagName("FmDate").item(0).getTextContent();
//	                String Officer = part2OrderOfficers.getElementsByTagName("Officer").item(0).getTextContent();
//	                String ArmyNo = part2OrderOfficers.getElementsByTagName("ArmyNo").item(0).getTextContent();
//	                String Rank = part2OrderOfficers.getElementsByTagName("Rank").item(0).getTextContent();
//	                String RankCode = part2OrderOfficers.getElementsByTagName("RankCode").item(0).getTextContent();
//	                String Name = part2OrderOfficers.getElementsByTagName("Name").item(0).getTextContent();
//	                CDAACNo=part2OrderOfficers.getElementsByTagName("CDAACNo").item(0).getTextContent();
//	                tos= FmDate;
//	                auth =PresentP2No;
//	                authdate=PresentP2Date;
//	                personnel_no=ArmyNo;
//	                rank=Rank;
//	                name=Name;
//	                Rank_Code =RankCode;
//	                present_p2_no=PresentP2No;
//	            
//	            }
//	 		   String hql_xml = "select id from XML_FILE_UPLOAD where present_p2_no=:present_p2_no  and status!=-1 ";
//				Query query_xml = sessionhql.createQuery(hql_xml);
//				query_xml.setParameter("present_p2_no",present_p2_no);
//				List <Long>list_xml = query_xml.list();
//				if(!list_xml.isEmpty())
//				{
//					count_already++;
//					continue;
//				}
//	            
//	            
//	            NodeList  unitDetilslist= root.getElementsByTagName("UnitDtls");
//	            for (int k = 0; k< unitDetilslist.getLength(); k++) {
//	                Element unitDetilsElement = (Element) unitDetilslist.item(k);
//	                String susno = unitDetilsElement.getElementsByTagName("SUSNo").item(0).getTextContent();
//	                String UnitName = unitDetilsElement.getElementsByTagName("UnitName").item(0).getTextContent();
//	if(susno=="")
//	{
//		
//		msg="SUS No is Blank";
//		Upload_msg="Failure";
//		   tx.rollback();
//      	   tranaction=1;
//      	   break;
//		
////		   String hql1 = "select sus_no,status_sus_no,unit_name from Miso_Orbat_Unt_Dtl where sus_no= :susno  and status_sus_no='Active' and unit_name=:unitname ";
////			Query query1 = sessionhql.createQuery(hql1);
////			query1.setParameter("susno",susno).setParameter("unitname", UnitName);
////			List <Object[]>list1 = query1.list();
////			if(list1.isEmpty())
////			{
////				msg="To SUS No not Found";
////				Upload_msg="Failure";
////				   tx.rollback();
////		      	   tranaction=1;
////		      	   break;
////			}
//			
////			else 
////			{
////				if(!list1.get(0)[1].equals("Active")) {
////				msg="SUS No not active  in ORBAT.";
////				Upload_msg="Failure";
////				   tx.rollback();
////		      	   tranaction=1;
////		      	   break;
////			}
//	            }
//	            else {
//	            	String hql1 = "SELECT sus_no, status_sus_no, unit_name "
//	                        + " FROM Miso_Orbat_Unt_Dtl "
//	                        + " WHERE (sus_no LIKE :susno OR unit_name = :unitname) AND status_sus_no = 'Active' ";
//	            Query query1 = sessionhql.createQuery(hql1);
//	            query1.setParameter("susno", susno + "%").setParameter("unitname", UnitName);
//
//	    			List <Object[]>list1 = query1.list();
//	    			if(list1.isEmpty())
//	    			{
//	    				msg=" SUS No not Found";
//	    				Upload_msg="Failure";
//	    				   tx.rollback();
//	    		      	   tranaction=1;
//	    		      	   break;
//	    			}
//	    			else {
//				 if(auth.equals(""))
//				{
//					msg="Present Part II order No blank.";
//					Upload_msg="Failure";
//					   tx.rollback();
//			      	   tranaction=1;
//			      	   break;
//				}
//					
//				else if(authdate.equals(""))
//				{
//					msg="Present Part II order Date blank";
//					Upload_msg="Failure";
//					   tx.rollback();
//			      	   tranaction=1;
//			      	   break;
//				}
//				
//				else {	
//	    		String hql6 = "select comm.id,m.description, TO_CHAR(comm.date_of_commission, 'DD/MM/YYYY') AS date_of_commission,comm.unit_sus_no ,TO_CHAR(comm.date_of_tos, 'DD/MM/YYYY') AS date_of_tos  from TB_TRANS_PROPOSED_COMM_LETTER comm \r\n"
//	    				+ ", CUE_TB_PSG_RANK_APP_MASTER m \r\n"
//	    				+ "where   m.id=comm.rank and comm.personnel_no = :personnel_no and comm.status in('1','5')";
//				Query query6 = sessionhql.createQuery(hql6);
//				query6.setParameter("personnel_no",personnel_no);
//				
//				List <Object[]>list6 = query6.list();
//			
//	           if(list6.size()==0)
//	           {
//	        	   msg=  "Army No Does Not Exist ";
//	        	   Upload_msg="Failure";
//      	   tx.rollback();
//      	   tranaction=1;
//      	   break;
//	           }
//	           else {
//	        	   comm_id_xml=(BigInteger) list6.get(0)[0];
//	        	   cen_id = getCensusid((BigInteger) list6.get(0)[0]);
//	        	   comm_id_1=(BigInteger) list6.get(0)[0];	  
//	        	   from_sus_no=(String) list6.get(0)[3];
//               	
//	        	   
//	        	   if(cen_id!=0)
//	        	   {
//						String hql11 = "  from  TB_CENSUS_DETAIL_Parent cen "
//								+ "where  cen.comm_id=:comm_id  and   cen.update_ofr_status !='0' ";
//						Query query11 = sessionhql.createQuery(hql11);
//						query11.setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//						List<Object[]> list11 = query11.list();
//						if (list11.isEmpty()) {
//							msg = "Update Details  Is Pending For Approval ";
//							Upload_msg = "Failure";
//							tx.rollback();
//							tranaction=1;  
//							break;
//						}	
//	        	   }
//	        	  
//	        	   else {	         
//	        		   
//	        		   String hql11 = "  from  TB_TRANS_PROPOSED_COMM_LETTER comm "
//								+ "where  id=:comm_id  and   comm.update_comm_status !='0' ";
//						Query query11 = sessionhql.createQuery(hql11);
//						query11.setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//						List<Object[]> list11 = query11.list();
//						if (list11.isEmpty()) {
//							msg = "Update Details  Is Pending For Approval ";
//							Upload_msg = "Failure";
//							tx.rollback();
//							tranaction=1;  
//							break;
//						}	
//	        	   }
//	        	   
////	        		String comm_date2=(String) list6.get(0)[2];
////	        	 	if(format.parse(authdate).compareTo(format.parse(comm_date2))<0)
////                	{
////                		msg="Authority Date should be Greater than Commission Date ";		
////                		Upload_msg="Failure";
////						tx.rollback();
////            			tranaction=1;
////            			break;
////                	}
//	        	   String comm_date2 = (String) list6.get(0)[2];
//
//	     
//	        	Date authDate = format.parse(authdate);
//	        	Date commDate2 = format.parse(comm_date2);
//
//	        	if (authDate.compareTo(commDate2) < 0) {
//	        	        msg = "Authority Date should be Greater than Commission Date";
//	        	        Upload_msg = "Failure";
//	        	        tx.rollback();
//	        	        tranaction = 1;
//	        	        break;
//	        	}
//	        	   
//	        	   
//	       		  int count_subpro=0;
//	       		  int count_actpro =0;
//	       		 int count_locpro =0;
//	       		  
//		            NodeList casualtiesList = root.getElementsByTagName("CasualtyDetails");				          
//		            for (int j = 0; j< casualtiesList.getLength(); j++) {
//		                Element casualtyElement = (Element) casualtiesList.item(j);
//		              int casuality=0;
//		          
//		                String casSeqNo = casualtyElement.getElementsByTagName("CasSeqNo").item(0).getTextContent();
//		                String description = casualtyElement.getElementsByTagName("Description").item(0).getTextContent();
//		                String fromDate = casualtyElement.getElementsByTagName("FmDate").item(0).getTextContent();
////		                if(description.equals("MARRIAGE"))
////			              {		casuality++;
////			              update_casuality++;
////		                	 Element medelement = (Element) casualtiesList.item(j);				          
////				                String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
////				                String toDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
////				                String data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
////				                String data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
////				                String data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   
////				                String Rmk3 = medelement.getElementsByTagName("Rmk3").item(0).getTextContent();   
////				                 int census_id = getCensusid((BigInteger)list6.get(0)[0]);
////				                 Date date = new Date();
////				                 int  final_mId=0;
////				       		try {
////				       			if (census_id != 0) {
////
////				       				String hqlUpdate8 = "from TB_CENSUS_DETAIL_Parent  where id = :census_id and comm_id = :comm_id and status = 1 ";
////
////				       				Query query8 = sessionhql.createQuery(hqlUpdate8)
////				       				        .setParameter("census_id", census_id) 
////				       				        .setParameter("comm_id", (BigInteger)list6.get(0)[0]) ;   
////				       				List<TB_CENSUS_DETAIL_Parent> list8 =  query8.list();
////				       				if(!list8.isEmpty())
////				       				{
////				       					if (list8.get(0).getMarital_status()==10) {
////
////						       				
////						       				String hqlUpdate4 = "from TB_CENSUS_FAMILY_MRG where cen_id = :census_id and comm_id = :comm_id and status = 0 and   type_of_event=1  order by id desc";
////
////						       				Query query3 = sessionhql.createQuery(hqlUpdate4)
////						       				        .setParameter("census_id", census_id, IntegerType.INSTANCE) 
////						       				        .setParameter("comm_id", (BigInteger)list6.get(0)[0], BigIntegerType.INSTANCE) ;   
////						       				List<TB_CENSUS_FAMILY_MRG> list3 =  query3.list();
////						       				int nationality=0;
////						       				if(data2.equals("BHUTANESE"))
////						       				{
////						       					nationality=41;
////						       				}
////						       				else if(data2.equals("INDIAN"))
////						       				{
////						       					nationality=22;
////						       				}
////						       				else if(data2.equals("NEPALESE"))
////						       				{
////						       					nationality=10;
////						       				}
////						       				else {
////						       					nationality=14;		
////						       				}
////						       				if (!list3.isEmpty()) {						       					
////						       					String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,maiden_name=:name,"
////						       							+  " authority=:auth,date_of_authority=:authdate,date_of_birth=:date_of_birth,marriage_date=:marriage_date,place_of_birth=:place_of_birth,nationality=:nationality  "
////						       							+ " where  id=:id";						       					
////						       					Query query2 = sessionhql.createQuery(hql).setInteger("status", 0).setString("name", data1)
////						       							.setString("modified_by", username).setTimestamp("modified_date", date).setString("auth", auth).setTimestamp("authdate",format.parse(authdate) )
////						       							.setTimestamp("date_of_birth", format.parse(toDate)).setTimestamp("marriage_date", format.parse(fmDate)).setString("place_of_birth",Rmk3 ).setInteger("nationality",nationality).setInteger("id", list3.get(0).getId());						       					
////						       					msg = query2.executeUpdate() > 0 ? "updated successfully" : "0";
////						       											       				
////						       				}
////						       				else {				       									       									       
////						       					String hqlUpdate5 = "from TB_CENSUS_FAMILY_MRG where comm_id =:comm_id and status = 0  and   type_of_event !=1  ";
////						       					Query query5 = sessionhql.createQuery(hqlUpdate5)						       					      
////						       					        .setParameter("comm_id", (BigInteger)list6.get(0)[0], BigIntegerType.INSTANCE) ;   
////						       					List<TB_CENSUS_FAMILY_MRG> list5 =  query5.list();
////						       					
////						       					if(list5.isEmpty())
////						       					{			       												       					
////						       					TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();
////						       					fam_marr.setMaiden_name(data1);
////						       						fam_marr.setDate_of_birth(format.parse(toDate));
////						       						fam_marr.setMarriage_date(format.parse(fmDate));
////						       						fam_marr.setPlace_of_birth(Rmk3);
////						       						fam_marr.setNationality(nationality);
////						       						fam_marr.setAdhar_number("");
////						       						fam_marr.setAuthority(auth);
////						       						fam_marr.setDate_of_authority(format.parse(authdate));
////						       						fam_marr.setPan_card("");
////						       						fam_marr.setIf_spouse_ser("");
////						       					fam_marr.setCen_id(census_id);
////						       					fam_marr.setCreated_by(username);
////						       					fam_marr.setCreated_date(date);
////						       					fam_marr.setComm_id((BigInteger)list6.get(0)[0]);
////						       					fam_marr.setType_of_event(1);
////						       					fam_marr.setMarital_status(8);
////						       					fam_marr.setStatus(0);
////						       					final_mId = (int) sessionhql.save(fam_marr);
////						       					msg="Data Uploaded SuccessFully  ";
////						       					entityId = fam_marr.getId(); 						       			
////						       					}
////						       					else {
////						       						msg="Data for Divorce is pending ";
////						       						tx.rollback();
////						       						tranaction=1;
////						       						break;
////						       					}
////			
////						       				}
////						       				
////						       				
////						       				
//////						       				try {
//////
//////
//////						       					String hql = "update TB_CENSUS_DETAIL_Parent set marital_status=:marital_status"
//////
//////						       							+ " where id=:census_id and comm_id=:comm_id and status = '1' ";
//////
//////						       					Query query = sessionhql.createQuery(hql).setInteger("census_id", census_id)
//////
//////						       							.setBigInteger("comm_id",(BigInteger)list6.get(0)[0] ).setInteger("marital_status",8 );
//////
//////						       					msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
//////						       					
//////
//////						       				} catch (Exception e) {
//////
//////						       					msg = "Data Not Updated.";
//////
//////						       					tx.rollback();
//////
//////						       				}
////						       				
////										}
////				       					else {
////				       						msg="Marriage Details Not Uploaded";				       					
////											tx.rollback();
////											tranaction=1;
////											break;
////				       					}
////				       				}				       							       				
////				       			}
////				       			else {
////				       				msg="Census Not Filled";
////									tx.rollback();
////									tranaction=1;
////									break;
////				       			}
////				       		} catch (RuntimeException e) {
////				       			try {
////				       				tx.rollback();
////				       				msg = "0";
////				       			} catch (RuntimeException rbe) {
////				       				msg = "0";
////				       			}
////				       		}
////			              }
////		                
////		                if(description.equals("BTHSPOUS")) {		 
////		                	casuality++;
////		                	update_casuality++;
////			            	  Element medelement = (Element) casualtiesList.item(j);			              
////				                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
////				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
////				                String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
////				                String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
////				                String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   			                
////				               	int census_id=getCensusid((BigInteger)list6.get(0)[0]);
////				        	Date date = new Date();
////	    					Date date_of_birth = null;
////	    					date_of_birth = format.parse(FmDate);
////				    		try {
////				    		if(census_id!=0)
////				    		{
////				    			
////				    			if(Data3.equals("Husband" )||Data3.equals("Wife"))
////				    			{					
////				    				
////				    				if(entityId==0)
////				    				{				    									    										    									    								    				
////					    				String hqlUpdate4 = "from TB_CENSUS_FAMILY_MRG where  comm_id = :comm_id and upper(maiden_name) = :maiden_name order by id desc";
////
////					    				Query query3 = sessionhql.createQuery(hqlUpdate4)
//////					    				        .setParameter("census_id", census_id, IntegerType.INSTANCE) // Assuming census_id is an integer
////					    				        .setParameter("comm_id", (BigInteger)list6.get(0)[0], BigIntegerType.INSTANCE) // Assuming comm_id is a BigInteger
////					    				        .setParameter("maiden_name", Data2, StringType.INSTANCE); // Assuming maiden_name is a string
////					    				List<TB_CENSUS_FAMILY_MRG> list3 =  query3.list();					    									    								
////					    				if (!list3.isEmpty()) {					    					
////					    					String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,date_of_birth=:date_of_birth,authority=:auth,date_of_authority=:authdate "
////					    							+ "where  id=:id";
////					    					Query query2 = sessionhql.createQuery(hql).setInteger("status", 0).setTimestamp("date_of_birth", date_of_birth)
////					    							.setString("modified_by", username).setTimestamp("modified_date", date).setString("auth", auth).setTimestamp("authdate",format.parse(authdate) )
////					    							.setInteger("id", list3.get(0).getId());
////					    					msg = query2.executeUpdate() > 0 ? "Data Uploaded SuccessFully" : "0";
////					    				}
////					    				else {
////					    					msg="Details of Spouse  is Not Filled ";
////					    					tx.rollback();			    		
////					    					tranaction=1;
////					    					break;
////					    				}
////				    				}		
////				    				else {
////				    				TB_CENSUS_FAMILY_MRG retrievedEntity = (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class, entityId);
////				    				if(retrievedEntity.getMaiden_name().equals(Data2))
////				    				{
////				    					retrievedEntity.setDate_of_birth(date_of_birth);
////				    					retrievedEntity.setStatus(0);
////				    					sessionhql.save(retrievedEntity);
////				    				}
////				    				else {
////				    					msg="Details of Spouse  is Not Filled ";
////				    					tx.rollback();			    		
////				    					tranaction=1;
////				    					break;
////				    				}				    					
////				    				}
////				    			}
////				    		}
////				    		else {
////				    			msg="Census Is not filled";
////				    			tx.rollback();
////				    			tranaction=1;
////		    					break;
////				    		}
////				    		
////				    	} catch (RuntimeException e) {
////				    		try {
////				    			tx.rollback();
////				    			msg = "0";
////				    			tranaction=1;
////				    		} catch (RuntimeException rbe) {
////				    			msg = "0";
////				    			tranaction=1;
////				    		}
////				    	} 		                          	              			              		                	
////		                }  
//		                
//		                if(description.equals("AADHAAR"))		                		                	
//		                {	   casuality++;
//		                	update_casuality++;
//		                	Element medelement = (Element) casualtiesList.item(j);			              
//			                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//			                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//			                String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//			                String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//			                String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   			                
//			              // msg= SaveName_change((BigInteger)list6.get(0)[0],FmDate,ToDate,Data1,Data2,Data3,username,auth,authdate);
//			                if (Data1 != "" || Data1 != null ) {
//			        			if (!valid.isValidAadhar(Data1)) {
//			        				msg="INVALID Aadhar  No ";
//			    					tx.rollback();			    		
//			    					tranaction=1;
//			    					break;
//			        			}
//			        		}
//			           	int census_id=getCensusid((BigInteger)list6.get(0)[0]);
//			    		try {
////			    		if(census_id!=0)
////			    		{
//			    			
//			    			if(Data3.equals("Husband" )||Data3.equals("Wife"))
//			    			{			
//			    				if(entityId==0)
//			    				{
//			    				String hqlUpdate4 = "from TB_CENSUS_FAMILY_MRG where  comm_id =:comm_id and upper(maiden_name)=:maiden_name order by id desc";
//
//			    				Query query3 = sessionhql.createQuery(hqlUpdate4)
//			    				     // Assuming census_id is an integer
//			    				        .setParameter("comm_id", (BigInteger)list6.get(0)[0], BigIntegerType.INSTANCE) // Assuming comm_id is a BigInteger
//			    				        .setParameter("maiden_name", Data2.toUpperCase(), StringType.INSTANCE); // Assuming maiden_name is a string
//			    				List<TB_CENSUS_FAMILY_MRG> list3 =  query3.list();			
//			    				if (!list3.isEmpty()) {			    				
//			    					Date date = new Date();
//			    					String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,adhar_number=pgp_sym_encrypt(:adhar_number,current_setting('miso.version')),authority=:auth,date_of_authority=:authdate "
//			    							+ "where  id=:id";
//			    					Query query2 = sessionhql.createQuery(hql).setInteger("status", 0).setString("adhar_number",Data1 )
//			    							.setString("modified_by", username).setTimestamp("modified_date", date).setString("auth", auth).setTimestamp("authdate",format.parse(authdate) )
//			    							.setInteger("id", list3.get(0).getId());
//			    					msg = query2.executeUpdate() > 0 ? "Data Uploaded SuccessFully" : "0";	
//			    				}
//			    				else {
//			    					msg="Details of Spouse  is Not Filled ";
//			    					tx.rollback();			    		
//			    					tranaction=1;
//			    					break;
//			    				}
//			    				
//			    				
//			    			}
//			    				else {
//				    				TB_CENSUS_FAMILY_MRG retrievedEntity = (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class, entityId);
//				    				if(retrievedEntity.getMaiden_name().equals(Data2))
//				    				{
//				    					retrievedEntity.setAdhar_number(Data1);
//				    					retrievedEntity.setStatus(0);
//				    					sessionhql.save(retrievedEntity);
//				    				}
//				    				else {
//				    					msg="Details of Spouse  is Not Filled ";
//				    					tx.rollback();			    		
//				    					tranaction=1;
//				    					break;
//				    				}				    			    						    					    				
//			    			}
//			    			}
//			    			if(Data3.equals("Daughter")||Data3.equals("Son") )
//			    			{
//			    				
//			    				if(child_entity_id==0)
//			    				{
//			    				
//			    				String hqlUpdate3 = " from TB_CENSUS_CHILDREN where comm_id=:comm_id and upper(name)=:name order by id desc ";
//			    				Query query4 = sessionhql.createQuery(hqlUpdate3).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]).setParameter("name", Data2.toUpperCase());
//			    				List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query4.list();			    				
//			    				if(!list.isEmpty())
//			    				{
//			    					Date date=new Date();
//			    					String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')) " 
//			    							+ "where  id=:id";
//			    					Query query2 = sessionhql.createQuery(hql)
//			    							.setInteger("status", 0).setString("aadhar_no", Data1)
//			    							.setString("modified_by", username).setTimestamp("modified_date", date).setInteger("id", list.get(0).getId());
//			    					msg = query2.executeUpdate() > 0 ? "Data Uploaded SuccessFully" : "0";			    				
//			    				}
//			    				else {
//			    					msg="Details of childeren is Not Filled ";
//			    					tx.rollback();
//			    					tranaction=1;
//			    					break;
//			    				}
//			    			}
//			    			
//			    				else{
//			    					TB_CENSUS_CHILDREN retrievedEntity2 = (TB_CENSUS_CHILDREN) sessionhql.get(TB_CENSUS_CHILDREN.class, child_entity_id);
//				    				if(retrievedEntity2.getName().equals(Data2))
//				    				{
//				    					retrievedEntity2.setAadhar_no(Data1);
//				    					retrievedEntity2.setStatus(0);
//				    					sessionhql.save(retrievedEntity2);
//				    				}
//				    				else {
//				    					msg="Details of Spouse  is Not Filled ";
//				    					tx.rollback();			    		
//				    					tranaction=1;
//				    					break;
//				    				}				    	
//			    			}			    			
//			    			}
////			    		}
////			    		else {
////			    			msg="Census Is not filled";
////			    			tx.rollback();
////			    			tranaction=1;
////	    					break;
////			    		}
//			    		
//			    	} catch (RuntimeException e) {
//			    		try {
//			    			tx.rollback();
//			    			msg = "0";
//			    			tranaction=1;
//			    		} catch (RuntimeException rbe) {
//			    			msg = "0";
//			    			tranaction=1;
//			    		}
//			    	} 		                          	              
//		                
//		                }
//		                		              
//		                if(description.equals("EMAILID"))
//		                {casuality++;
//		                	 Element medelement = (Element) casualtiesList.item(j);			              
//				                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String gmail = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//				                String nic_mail = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//				                if (gmail == null || gmail.equals("")) {
//				        			msg= " The Gmail is not found";
//				        			  Upload_msg="Failure";
//				        			  break;
//				        		}
//				        		if (!valid.isValidEmail(gmail)) {
//				        			msg="Invalid Gmail";
//				        			 Upload_msg="Failure";
//				        			 break;
//				        		}
//				        		if (!valid.isvalidLength(gmail,valid.gmail_Max,valid.gmail_Min)) {
//				        			msg= "Invalid Gmail " 	;
//				        			 Upload_msg="Failure";
//				        			  break;
//				        		}
//				        		if (nic_mail == null || nic_mail.equals("")) {
//				        			msg= "Please Enter the Nic Mail ";
//				        			  Upload_msg="Failure";
//				        			  break;
//				        		}
//				        		if (!valid.isValidEmail(nic_mail)) {
//				        			msg = "Invalid  Nic Mail";	
//				        			  Upload_msg="Failure";
//				        			  break;
//				        		}
//				        		if (!valid.isvalidLength(gmail,valid.gmail_Max,valid.gmail_Min)) {
//				         	msg= "Invalid Length Nic Mail ";
//				        			  Upload_msg="Failure";
//				        			  break;
//				        		}
//				        		try {
//				        			int census_id=getCensusid((BigInteger) list6.get(0)[0]);
//				     				 				        			
//				        			String querry="Select count(id)  from TB_CENSUS_CDA_ACCOUNT_NO where status=0 and  comm_id=:comm_id ";
//				        			Query q = sessionhql.createQuery(querry).setBigInteger("comm_id",(BigInteger) list6.get(0)[0] );
//				        			List<Long> li=q.list();
//				        			if(!li.isEmpty()) {
//				        			if (li.get(0) == 0) {
//				        				TB_CENSUS_CDA_ACCOUNT_NO census_p = new TB_CENSUS_CDA_ACCOUNT_NO();
//				        				    //census_p.setCda_account_no(cda_account_no);
//				                            census_p.setGmail(gmail);
//				        					census_p.setNic_mail(nic_mail);
//				        					census_p.setMobile_no("0");
//				        					census_p.setComm_id( (BigInteger) list6.get(0)[0]);
//				        					census_p.setCensus_id(census_id);
//				        					census_p.setCreated_by(username);
//				        					census_p.setModified_by(username);
//				        					census_p.setModified_date(new Date());
//				        					census_p.setCreated_date(new Date());
//				        					census_p.setStatus(0);
//				        				int id = (int) sessionhql.save(census_p);
//				        				if(id!=0)
//				        				{
//				        					msg = "Data Uploaded SuccessFully ";
//				        				}
//				        				
//				        			} else {
//				        				
//				        				String hql = " update TB_CENSUS_CDA_ACCOUNT_NO set gmail=pgp_sym_encrypt(:gmail,current_setting('miso.version')),nic_mail=pgp_sym_encrypt(:nic_mail,current_setting('miso.version')),"
//				        										+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
//				        										+ " where comm_id=:id";
//				        				Query query = sessionhql.createQuery(hql).setString("gmail",gmail)
//				        						.setString("nic_mail", nic_mail)
////				        						.setString("mobile_no","0")
//				        						.setString("modified_by", username)
//				        						.setTimestamp("modified_date", new Date())
//				        						.setInteger("status", 0)
//				        						.setBigInteger("id", (BigInteger) list6.get(0)[0]);				        									
//				        				msg = query.executeUpdate() > 0 ? "Data Uploaded SuccessFully" : "Failure";
//
//				        			}
//
//				        			}
//
//				        		}catch (RuntimeException e) {
//				        			try {
//				        				tx.rollback();
//				        				msg = "0";
//				        				 Upload_msg="Failure";
//				        			} catch (RuntimeException rbe) {
//				        				msg = "0";
//				        				 Upload_msg="Failure";
//				        			}
//
//				        		} 
//
//		                }
//		                
//		                
//		                
//		                
//		                
//		                
//		                
//		                
//		                if(description.equals("MOBILE"))
//		                {casuality++;
//		                update_casuality++;
//		                	 Element medelement = (Element) casualtiesList.item(j);			              
//				                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String mobile_no = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//				                if (mobile_no == null && mobile_no.trim().equals("")) {
//				                	msg= " Mobile_no Missing";
//				        			 Upload_msg="Failure";
//				        			  break;
//				                }
//				        			if (valid.isOnlyNumer(mobile_no) == true) {
//				        				msg= "Invalid Mobile No";
//				        				  tx.rollback();
//				                      	   tranaction=1;
//				                      	   break;
//				        		
//				        			}
//				        			
//				        		    if (!valid.isValidMobileNo(mobile_no)) {
//				        	msg= "Invalid Mobile Number";
//				        	  tx.rollback();
//	                      	   tranaction=1;
//	                      	   break;
//				      
//				        			}
//				        			try {
//					        			int census_id=getCensusid((BigInteger) list6.get(0)[0]);
////					        			 String hqlupdate = "update TB_CENSUS_CDA_ACCOUNT_NO set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1')";					      			
////						     			Query qupdate = sessionhql.createQuery(hqlupdate).setInteger("status", 2).setInteger("census_id", census_id)
////						     					.setBigInteger("comm_id",(BigInteger) list6.get(0)[0]);					     						
////						     				msg = qupdate.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";					     				 				        			
//					        			String querry="Select count(id)  from TB_CENSUS_CDA_ACCOUNT_NO where status=0 and  comm_id=:comm_id ";
//					        			Query q = sessionhql.createQuery(querry).setBigInteger("comm_id",(BigInteger) list6.get(0)[0] );
//					        			List<Long> li=q.list();
//					        			if(!li.isEmpty()) {
//					        			if (li.get(0) == 0) {
//					        				TB_CENSUS_CDA_ACCOUNT_NO census_p = new TB_CENSUS_CDA_ACCOUNT_NO();
//					        				  //census_p.setCda_account_no(cda_account_no);
//					        				//	census_p.setGmail(gmail);
//					        				//census_p.setNic_mail(nic_mail);
//					        					census_p.setMobile_no(mobile_no);
//					        					census_p.setComm_id( (BigInteger) list6.get(0)[0]);
//					        					census_p.setCensus_id(census_id);
//					        					census_p.setCreated_by(username);
//					        					census_p.setModified_by(username);
//					        					census_p.setModified_date(new Date());
//					        					census_p.setCreated_date(new Date());
//					        					census_p.setStatus(0);
//					        				int id = (int) sessionhql.save(census_p);
//					        				if(id!=0)
//					        				{
//					        					msg = "Data Uploaded SuccessFully";
//					        				}else {
//					        					msg="failure";			        				
//					        				}
//					        				
//					        			} else {
//					        				
//					        				String hql = " update TB_CENSUS_CDA_ACCOUNT_NO set  "
//					        						+ " mobile_no=pgp_sym_encrypt(:mobile_no,current_setting('miso.version')),modified_by=:modified_by,modified_date=:modified_date,status=:status"
//					        						+ " where comm_id=:id";
//					        				Query query = sessionhql.createQuery(hql)
//					        						//.setString("gmail",gmail)
//					        						//	.setString("nic_mail", nic_mail)
//					        						.setString("mobile_no",mobile_no)
//					        						.setString("modified_by", username)
//					        						.setTimestamp("modified_date", new Date())
//					        						.setInteger("status", 0)
//					        						.setBigInteger("id", (BigInteger) list6.get(0)[0]);					        									
//					        				msg = query.executeUpdate() > 0 ? "Data Uploaded SuccessFully" : "0";
//					        			}
//					        			}
//					        		}catch (RuntimeException e) {
//					        			try {
//					        				tx.rollback();
//					        				msg = "0";
//					        				 Upload_msg="Failure";
//					        			} catch (RuntimeException rbe) {
//					        				msg = "0";
//					        				 Upload_msg="Failure";
//					        			}
//
//					        		} 
//				        		    
//				             
//		                }
//		                
//		                if(description.equals("PAN"))
//		                {
//		                	casuality++;
//		                	update_casuality++;
//		                	try {
//		                	    Element data1Element = (Element) casualtyElement.getElementsByTagName("Data1").item(0);
//		                	    String pan_no = data1Element.getTextContent().trim(); // Trim to remove leading/trailing spaces
//
//		                	    if (!pan_no.isEmpty()) {
//		                	        if (valid.PanNoLength(pan_no)) {
//		                	     
//		                	            Query q = sessionhql.createQuery("update TB_CENSUS_DETAIL_Parent set pancard_number = pgp_sym_encrypt(:pancard_number, current_setting('miso.version')) where comm_id = :id");
//		                	            q.setParameter("pancard_number", pan_no);
//		                	            q.setParameter("id", (BigInteger) list6.get(0)[0]);
//		                	            int updatedRows = q.executeUpdate();
//		                	            //transaction.commit(); // Commit the transaction
//		                	            if (updatedRows > 0) {
//		                	                msg = " Data Uploaded SuccessFully";
//		                	                Upload_msg = "Success";
//		                	            } else {
//		                	                msg = "Pan card not updated";
//		                	                Upload_msg = "Failure";
//		                	                tx.rollback();
//					                      	  tranaction=1;
//					                      	  break;
//		                	            }
//		                	        } else {
//		                	            msg = "Invalid PAN card number";
//		                	            Upload_msg = "Failure";
//		                	            tx.rollback();
//				                      	   tranaction=1;
//				                      	   break;
//		                	        }
//		                	    } else {
//		                	        msg = "PAN card not provided";
//		                	        Upload_msg = "Failure";
//		                	        tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//		                	    }
//		                	} catch (Exception e) {
//		                	    e.printStackTrace(); // Handle exceptions properly, log or rethrow as needed
//		                	    msg = "An error occurred while updating PAN card";
//		                	    Upload_msg = "Failure";
//		                	}
//		                	
//		                }
//		         
//		                
//		                if (description.equals("NOKCHNG")) {
//		                	casuality++;
//		                	update_casuality++;
//		                    Element medelement = (Element) casualtiesList.item(j);
//		                    String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//		                    String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
//		                    String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//		                    String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//		                    String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();
//		                    Data3 = Data3.toUpperCase();
//		                    int relation = 0;
//		                    try {
//		                        int census_id = getCensusid((BigInteger) list6.get(0)[0]);
//		                        if (!Data3.isEmpty()) {
//		                            if (Data3.equals("WIFE") || Data3.equals("HUSBAND")) {
//		                                Data3 = "SPOUSE";
//		                            }
//		                            Query q1 = sessionhql.createQuery(
//		                                    "select id from TB_RELATION  where status='active' and upper(relation_name)=:relation_name ").setParameter("relation_name", Data3);
//		                            List<Integer> list = (List<Integer>) q1.list();
//		                            if (list.isEmpty()) {
//		                                msg = "Relation Not Found";
//		                                tx.rollback();
//		                                tranaction = 1;
//		                                break;
//		                            } else {
//		                                relation = list.get(0);
//		                            }
////		                            if (census_id != 0) {
//		                                String checkUpdate = " select id from TB_CENSUS_NOK where census_id=:census_id and comm_id=:comm_id and  status = '0'";
//		                                Query query2 = sessionhql.createQuery(checkUpdate)
//		                                        .setInteger("census_id", census_id)
//		                                        .setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//		                                List<Integer> list_update_nok = (List<Integer>) query2.list();
//		                                if (list_update_nok.isEmpty()) {
//		                                    TB_CENSUS_NOK addr = new TB_CENSUS_NOK();
//		                                    addr.setAuthority(auth);
//		                                    addr.setDate_authority(format.parse(authdate));
//		                                    addr.setNok_name(Data2);
//		                                    addr.setNok_relation(relation);
//		                                    addr.setNok_village("NA");
//		                                    addr.setNok_country(Integer.parseInt("15"));
//		                                    addr.setNok_tehsil(Integer.parseInt("5939"));
//		                                    addr.setNok_district(Integer.parseInt("679"));
//		                                    addr.setNok_state(Integer.parseInt("41"));
//		                                    addr.setNok_pin(000000);
//		                                    addr.setNok_near_railway_station("NA");
//		                                    addr.setNok_rural_urban_semi("semi_urban");
//		                                    addr.setCtry_other("NA");
//		                                    addr.setSt_other("NA");
//		                                    addr.setDist_other("NA");
//		                                    addr.setTehsil_other("NA");
//		                                    addr.setNok_mobile_no("0000000000");
//		                                    addr.setComm_id((BigInteger) list6.get(0)[0]);
//		                                    addr.setCreated_by(username);
//		                                    addr.setCreated_date(new Date());
//		                                    addr.setCensus_id(census_id);
//		                                    addr.setStatus(0);
//
//		                                    int id = (int) sessionhql.save(addr);
//		                                    if(id!=0)
//		                                    	{
//		                                    		msg ="Data Uploaded SuccessFully";
//		                                    	}
//		                              
//
//		                                } else {
//
//		                                    String hql = " update TB_CENSUS_NOK set authority=:authority ,comm_id=:comm_id ,date_authority=:date_authority,"
//
//		                                            + "nok_name=:nok_name,nok_relation=:nok_relation,nok_village=:nok_village,nok_country=:nok_country,"
//
//		                                            + "nok_tehsil=:nok_tehsil ,nok_district=:nok_district ,nok_state=:nok_state,"
//
//		                                            + "ctry_other=:ctry_other,"
//
//		                                            + "st_other=:st_other ,dist_other=:dist_other ,tehsil_other=:tehsil_other,"
//
//		                                            + "nok_pin=:nok_pin,nok_near_railway_station=:nok_near_railway_station,"
//
//		                                            + "nok_rural_urban_semi=:nok_rural_urban_semi,nok_mobile_no=pgp_sym_encrypt(:nok_mobile_no,current_setting('miso.version')),"
//
//		                                            + "modified_by=:modified_by,modified_date=:modified_date,status=:status" + " where  id=:id";
//
//		                                    Query query = sessionhql.createQuery(hql).setTimestamp("date_authority", format.parse(authdate))
//
//		                                            .setString("authority", auth)
//
//		                                            .setBigInteger("comm_id", (BigInteger) list6.get(0)[0])
//
//		                                            .setString("nok_name", Data2).setInteger("nok_relation", relation)
//
//		                                            .setInteger("nok_country", Integer.parseInt("15"))
//
//		                                            .setInteger("nok_state", Integer.parseInt("41"))
//
//		                                            .setString("ctry_other", "NA")
//
//		                                            .setString("st_other", "NA")
//
//		                                            .setString("dist_other", "NA")
//
//		                                            .setString("tehsil_other", "NA")
//
//		                                            .setInteger("nok_district", Integer.parseInt("679"))
//
//		                                            .setString("nok_village", "NA")
//		                                            .setInteger("nok_tehsil", Integer.parseInt("5939"))
//
//		                                            .setString("nok_mobile_no", "0000000000")
//		                                            .setInteger("nok_pin", Integer.parseInt("000000"))
//
//		                                            .setString("nok_near_railway_station", "NA")
//		                                            .setString("nok_rural_urban_semi", "semi_urban")
//
//		                                            .setString("modified_by", username)
//
//		                                            .setTimestamp("modified_date", new Date()).setInteger("status", 0)
//
//		                                            .setInteger("id", list_update_nok.get(0));
//
//		                                    msg = query.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";
//
//		                                    // Mmap.put("msg", "Data Updated Successfully");
//
//		                                }		                               
////		                            }
////		                            else {
////	                                    msg = "Census Is not filled";
////	                                    tx.rollback();
////	                                    tranaction = 1;
////	                                    break;
////	                                } 
//          }
//		                        else{
//		                        	msg="Incorrect Data ";
//		                        	 tx.rollback();
//			                            msg = "0";
//			                            tranaction = 1;
//		                        }
//		                    } catch (RuntimeException e) {
//
//		                        try {
//
//		                            tx.rollback();
//		                            msg = "0";
//		                            tranaction = 1;
//
//		                        } catch (RuntimeException rbe) {
//
//		                            tx.rollback();
//		                            msg = "0";
//		                            tranaction = 1;
//
//		                        }
//		                    }
//		                }
//
//		                if(description.equals("SUBPROM"))
//			              {
//		                	casuality++;
//		                	update_casuality++;
//		                	
//		                	  Element medelement = (Element) casualtiesList.item(j);
//		                	
//		                	   String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String toDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();				          
//				                String data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   
//				                String data4 = medelement.getElementsByTagName("Data4").item(0).getTextContent();
//				                String rmk1 = medelement.getElementsByTagName("Rmk1").item(0).getTextContent();   
//				                int type_of_rank=1;
//				               
//		                	if (check_future(fmDate)) {		
//								int census_id = getCensusid((BigInteger) list6.get(0)[0]);							
//								Date fmDate2 = format.parse(fmDate);
//								Date date = new Date();
//								int final_mId = 0;
//						try {
////							if (census_id != 0) {
//
//				        	Date fmDate_1 = format.parse(fmDate);
//				        	if (fmDate_1.compareTo(commDate2) < 0) {
//				        	        msg = "Date of Rank should be greater Or equal to Commissioning Date";
//				        	        Upload_msg = "Failure";
//				        	        tx.rollback();
//				        	        tranaction = 1;
//				        	        break;
//				        	}
//						
//						
//								int rank12=get_rank_value(rmk1);
//								if(rank12!=0)
//								{
//									Query q11 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK where to_char(date_of_rank, 'DD/MM/YYYY') = :date_of_rank and comm_id = :comm_id and status != -1")
//							                .setString("date_of_rank", format.format(fmDate2))
//							                .setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//									Long c = (Long) q11.uniqueResult();
//									
//									if(c>0) {										
//										msg= "Date Of Rank Already Exists";
//										 Upload_msg="Failure";
//										tx.rollback();
//										tranaction=1;
//										break;
//									}
//
//									else {
//										
//										if(count_subpro!=0)
//										{
//											String hqlApprove = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
//											Query queryApp = sessionhql.createQuery(hqlApprove).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//											@SuppressWarnings("unchecked")
//											List<TB_CHANGE_OF_RANK> listapp = (List<TB_CHANGE_OF_RANK>) queryApp.list();
//											if(!listapp.isEmpty())
//											{
//												String hql = "update TB_CHANGE_OF_RANK set "
//														+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//														+ " where  id=:id";
//												Query Update_query = sessionhql.createQuery(hql)
//														.setInteger("id", listapp.get(0).getId()).setString("modified_by", username)
//														.setTimestamp("modified_date", new Date()).setInteger("status", 1);
//
//												msg = Update_query.executeUpdate() > 0 ? "1" : "0";
//												if(msg.equals("1")) {
//												
//													String hqlcomm = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank  "
//															+ " where id=:comm_id ";
//
//													Query querycomm = sessionhql.createQuery(hqlcomm).setString("modified_by", username)
//															.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", (BigInteger) list6.get(0)[0])
//															.setInteger("rank", listapp.get(0).getRank()).setDate("date_of_rank", listapp.get(0).getDate_of_rank());
//													msg = querycomm.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
//												}
//											}
//											
//										}
//										
//										
//										
//										String hqlUpdate = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
//										Query queryup = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//										@SuppressWarnings("unchecked")
//										List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) queryup.list();
//										if (list.isEmpty()) {
//											TB_CHANGE_OF_RANK cr = new TB_CHANGE_OF_RANK();
//											cr.setAuthority(auth);
//											cr.setDate_of_authority(format.parse(authdate));
//											cr.setRank_type(type_of_rank);
//											cr.setRank(rank12);
//											cr.setDate_of_rank(format.parse(fmDate));
//											cr.setCensus_id(census_id);
//											cr.setComm_id((BigInteger) list6.get(0)[0]);
//											cr.setCreated_by(username);
//											cr.setCreated_date(date);
//											cr.setStatus(0);
//											int id = (int) sessionhql.save(cr);
//											msg = Integer.toString(id);										
//											if(id!=0)
//											{
//												msg="Data Uploaded SuccessFully ";
//											}
//											else {
//												msg="Data not Saved";
//												 Upload_msg="Failure";
//												tx.rollback();
//												tranaction=1;
//												break;
//												
//											}
//											
//										} 
//										else {
//											String hql = "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
//													+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//													+ " where  id=:id";
//											Query Update_query = sessionhql.createQuery(hql).setString("authority", auth)
//													.setDate("date_of_authority", format.parse(authdate)).setInteger("rank_type",type_of_rank)
//													.setInteger("rank", rank12).setDate("date_of_rank", format.parse(fmDate))
//													.setInteger("id", list.get(0).getId()).setString("modified_by", username)
//													.setTimestamp("modified_date", new Date()).setInteger("status", 0);
//
//											msg = Update_query.executeUpdate() > 0 ? "Update Successfully" : "Failure";
//										}	
//										count_subpro++;
//									}
//								}
//								else {
//									msg="Rank does not exist in  database ";
//									 Upload_msg="Failure";
//									tx.rollback();
//									tranaction=1;
//									break;
//								}
//		
//						} catch (RuntimeException e) {
//							try {
//								tx.rollback();
//								msg = "Failure";
//							} catch (RuntimeException rbe) {
//								msg = "Failure";
//							}
//						} 
//							} else {
//								msg = "Date of Rank is future date";
//								tx.rollback();
//								tranaction=1;
//								break;
//							}
//		                	
//			              }
//		                
//		                
//		                if(description.equals("ACTPROM"))
//			              {
//		                	casuality++;
//		                	update_casuality++;
//		                	  Element medelement = (Element) casualtiesList.item(j);
//		                	
//		                	   String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String toDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();				          
//				                String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   
//				                String Data4 = medelement.getElementsByTagName("Data4").item(0).getTextContent();
//				                String rmk1 = medelement.getElementsByTagName("Rmk1").item(0).getTextContent();   
//				                
//		                	int type_of_rank=2;
//							if (check_future(fmDate)) {
//								int census_id = getCensusid((BigInteger) list6.get(0)[0]);							
//								Date fmDate2 = format.parse(fmDate);
//								Date date = new Date();
//								int final_mId = 0;
//						try {
//
//					        	Date fmDate_1 = format.parse(fmDate);
//					        	if (fmDate_1.compareTo(commDate2) < 0) {
//					        	        msg = "Date of Rank should be greater Or equal to Commissioning Date";
//					        	        Upload_msg = "Failure";
//					        	        tx.rollback();
//					        	        tranaction = 1;
//					        	        break;
//					        	}
//							
//							
//							
//							
//							//if (census_id != 0) {
//								int rank12=get_rank_value(rmk1);
//								
//								if(rank12!=0)
//								{
//									Query q11 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK where to_char(date_of_rank, 'DD/MM/YYYY') = :date_of_rank and comm_id = :comm_id and status != -1")
//							                .setString("date_of_rank", format.format(fmDate2))
//							                .setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//									Long c = (Long) q11.uniqueResult();								
//									if(c>0) {										
//										msg= "Date Of Rank Already Exists";
//										tx.rollback();
//										tranaction=1;
//										break;
//									}
//
//									else {
//										if(count_actpro!=0)
//										{
//											String hqlApprove = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
//											Query queryApp = sessionhql.createQuery(hqlApprove).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//											@SuppressWarnings("unchecked")
//											List<TB_CHANGE_OF_RANK> listapp = (List<TB_CHANGE_OF_RANK>) queryApp.list();
//											if(!listapp.isEmpty())
//											{
//												String hql = "update TB_CHANGE_OF_RANK set "
//														+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//														+ " where  id=:id";
//												Query Update_query = sessionhql.createQuery(hql)
//														.setInteger("id", listapp.get(0).getId()).setString("modified_by", username)
//														.setTimestamp("modified_date", new Date()).setInteger("status", 1);
//
//												msg = Update_query.executeUpdate() > 0 ? "1" : "0";
//												if(msg.equals("1")) {
//												
//													String hqlcomm = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank  "
//															+ " where id=:comm_id ";
//
//													Query querycomm = sessionhql.createQuery(hqlcomm).setString("modified_by", username)
//															.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", (BigInteger) list6.get(0)[0])
//															.setInteger("rank", listapp.get(0).getRank()).setDate("date_of_rank", listapp.get(0).getDate_of_rank());
//													msg = querycomm.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
//												}
//											}
//											
//										}
//										String hqlUpdate = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
//										Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//										@SuppressWarnings("unchecked")
//										List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
//										if (list.isEmpty()) {
//											TB_CHANGE_OF_RANK cr = new TB_CHANGE_OF_RANK();
//											cr.setAuthority(auth);
//											cr.setDate_of_authority(format.parse(authdate));
//											cr.setRank_type(type_of_rank);
//											cr.setRank(rank12);
//											cr.setDate_of_rank(format.parse(fmDate));
//											cr.setCensus_id(census_id);
//											cr.setComm_id((BigInteger) list6.get(0)[0]);
//											cr.setCreated_by(username);
//											cr.setCreated_date(date);
//											cr.setStatus(0);
//											int id = (int) sessionhql.save(cr);
//											msg = Integer.toString(id);										
//											if(id!=0)
//											{
//												msg="Data Uploaded Successfully";
//											}
//											else {
//												msg="Data not Saved";
//												tx.rollback();
//												tranaction=1;
//												break;
//											}
//											
//										} 
//										else {
//											String hql = "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
//													+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//													+ " where  id=:id";
//											Query Update_query = sessionhql.createQuery(hql).setString("authority", auth)
//													.setDate("date_of_authority", format.parse(authdate)).setInteger("rank_type",type_of_rank)
//													.setInteger("rank", rank12).setDate("date_of_rank", format.parse(fmDate))
//													.setInteger("id", list.get(0).getId()).setString("modified_by", username)
//													.setTimestamp("modified_date", new Date()).setInteger("status", 0);
//
//											msg = Update_query.executeUpdate() > 0 ? "Update Successfully" : "Failure";
//										}		
//										count_actpro++;
//									}
//								}
//								else {
//									msg="Rank does not exist in  database ";
//									 Upload_msg="Failure";
//									tx.rollback();
//									tranaction=1;
//									break;
//								}
//	
//						} catch (RuntimeException e) {
//							try {
//								tx.rollback();
//								msg = "Failure";
//							} catch (RuntimeException rbe) {
//								msg = "Failure";
//							}
//						} 
//							
//							} else {
//								msg = "Date of Rank is future date";
//								tx.rollback();
//								tranaction=1;
//								break;
//							}
//		                	
//		                	
//			              }
//		                 if(description.equals("LOCPROM"))		                		                	
//		                {		
//		                	 casuality++;
//		                	 update_casuality++;
//		                	 Element medelement = (Element) casualtiesList.item(j);		             
//		                	 String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				             String toDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				             String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();				          
//				             String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   
//				             String Data4 = medelement.getElementsByTagName("Data4").item(0).getTextContent();
//				             String rmk1 = medelement.getElementsByTagName("Rmk1").item(0).getTextContent();   
//				                int type_of_rank=3;		                	
//				                if (check_future(fmDate)) {
//				                	int census_id = getCensusid((BigInteger) list6.get(0)[0]);							
//									Date fmDate2 = format.parse(fmDate);
//									Date date = new Date();
//									int final_mId = 0;
//							try {
//
//					        	Date fmDate_1 = format.parse(fmDate);
//					        	if (fmDate_1.compareTo(commDate2) < 0) {
//					        	        msg = "Date of Rank should be greater Or equal to Commissioning Date";
//					        	        Upload_msg = "Failure";
//					        	        tx.rollback();
//					        	        tranaction = 1;
//					        	        break;
//					        	}
//							
//							
//									int rank12=get_rank_value(rmk1);
//									if(rank12!=0)
//									{
//										Query q11 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK where to_char(date_of_rank, 'DD/MM/YYYY') = :date_of_rank and comm_id = :comm_id and status != -1")
//								                .setString("date_of_rank", format.format(fmDate2))
//								                .setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//										Long c = (Long) q11.uniqueResult();
//									
//										if(c>0) {										
//											msg= "Date Of Rank Already Exists";
//											tx.rollback();
//											tranaction=1;
//											break;
//										}
//
//										else {
//											
//											if(count_locpro!=0)
//											{
//												String hqlApprove = " from TB_CHANGE_OF_RANK where  status = '0' and comm_id=:comm_id ";
//												Query queryApp = sessionhql.createQuery(hqlApprove).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//												@SuppressWarnings("unchecked")
//												List<TB_CHANGE_OF_RANK> listapp = (List<TB_CHANGE_OF_RANK>) queryApp.list();
//												if(!listapp.isEmpty())
//												{
//													String hql = "update TB_CHANGE_OF_RANK set "
//															+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//															+ " where  id=:id";
//													Query Update_query = sessionhql.createQuery(hql)
//															.setInteger("id", listapp.get(0).getId()).setString("modified_by", username)
//															.setTimestamp("modified_date", new Date()).setInteger("status", 1);
//
//													msg = Update_query.executeUpdate() > 0 ? "1" : "0";
//													if(msg.equals("1")) {
//													
//														String hqlcomm = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,rank=:rank,date_of_rank=:date_of_rank  "
//																+ " where id=:comm_id ";
//
//														Query querycomm = sessionhql.createQuery(hqlcomm).setString("modified_by", username)
//																.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", (BigInteger) list6.get(0)[0])
//																.setInteger("rank", listapp.get(0).getRank()).setDate("date_of_rank", listapp.get(0).getDate_of_rank());
//														msg = querycomm.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
//													}
//												}
//												
//											}
//											
//											
//											String hqlUpdate = " from TB_CHANGE_OF_RANK where   status = '0' and comm_id=:comm_id ";
//											Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//											@SuppressWarnings("unchecked")
//											List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
//											if (list.isEmpty()) {
//												TB_CHANGE_OF_RANK cr = new TB_CHANGE_OF_RANK();
//												cr.setAuthority(auth);
//												cr.setDate_of_authority(format.parse(authdate));
//												cr.setRank_type(type_of_rank);
//												cr.setRank(rank12);
//												cr.setDate_of_rank(format.parse(fmDate));
//												cr.setCensus_id(census_id);
//												cr.setComm_id((BigInteger) list6.get(0)[0]);
//												cr.setCreated_by(username);
//												cr.setCreated_date(date);
//												cr.setStatus(0);
//												int id = (int) sessionhql.save(cr);
//												msg = Integer.toString(id);										
//												if(id!=0)
//												{
//													msg="Data Uploaded Successfully";
//												}
//												else {
//													msg="Data not Saved";
//													tx.rollback();
//													tranaction=1;
//													break;
//													
//												}
//												
//											} 
//											else {
//												String hql = "update TB_CHANGE_OF_RANK set authority=:authority,date_of_authority=:date_of_authority,rank_type=:rank_type,"
//														+ "rank=:rank,date_of_rank=:date_of_rank,modified_by=:modified_by,modified_date=:modified_date,status=:status  "
//														+ " where  id=:id";
//												Query Update_query = sessionhql.createQuery(hql).setString("authority", auth)
//														.setDate("date_of_authority", format.parse(authdate)).setInteger("rank_type",type_of_rank)
//														.setInteger("rank", rank12).setDate("date_of_rank", format.parse(fmDate))
//														.setInteger("id", list.get(0).getId()).setString("modified_by", username)
//														.setTimestamp("modified_date", new Date()).setInteger("status", 0);
//
//												msg = Update_query.executeUpdate() > 0 ? "Update Successfully" : "Failure";
//											}	
//											count_locpro++;
//										}
//									}
//									else {
//										msg="Rank does not exist in  database ";
//										 Upload_msg="Failure";
//										tx.rollback();
//										tranaction=1;
//										break;
//									}		
//							} catch (RuntimeException e) {
//								try {
//									tx.rollback();
//									msg = "Failure";
//									 Upload_msg="Failure";
//								} catch (RuntimeException rbe) {
//									msg = "Failure";
//									 Upload_msg="Failure";
//								}
//							} 
//								
//								} else {
//									msg = "Date of Rank is future date";
//									tx.rollback();
//									tranaction=1;
//									break;
//								}
//				                }
//		                		                		             
//		                if(description.equals("NAMCHNG"))
//		              {
//		                	casuality++;
//		                	update_casuality++;
//		            	  Element medelement = (Element) casualtiesList.item(j);			              
//			                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//			                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//			                String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//			                String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//			                String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   			                			             
//			           	int census_id=getCensusid((BigInteger)list6.get(0)[0]);
//			    		try {
//			    	
//			    			
//			    			if(Data3.equals("Husband" )||Data3.equals("Wife"))
//			    			{								
//			    				String hqlUpdate4 = "from TB_CENSUS_FAMILY_MRG where  comm_id = :comm_id and maiden_name = :maiden_name order by id desc";
//
//			    				Query query3 = sessionhql.createQuery(hqlUpdate4)
//			    				    
//			    				        .setParameter("comm_id", (BigInteger)list6.get(0)[0], BigIntegerType.INSTANCE) 
//			    				        .setParameter("maiden_name", Data2, StringType.INSTANCE); 
//			    				List<TB_CENSUS_FAMILY_MRG> list3 =  query3.list();				
//			    				if (!list3.isEmpty()) {
//			    				Date date = new Date();
//			    				String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,maiden_name=:name,authority=:auth,date_of_authority=:authdate "
//			    										+ "where  id=:id";
//			    					Query query2 = sessionhql.createQuery(hql).setInteger("status",0 ).setString("name", Data2)
//			    							.setString("modified_by", username).setTimestamp("modified_date", date).setString("auth", auth).setTimestamp("authdate",format.parse(authdate) )
//			    							.setInteger("id", list3.get(0).getId());
//			    					msg = query2.executeUpdate() > 0 ? "Updated successfully" : "Failure";			    				
//			    				}
//			    				else {
//			    					msg="Details of Spouse  is Not Filled ";
//			    					 Upload_msg="Failure";
//			    					tx.rollback();			    		
//			    					tranaction=1;
//			    					break;
//			    				}
//			    
//			    			if(Data3.equals("Daughter")||Data3.equals("Son") )
//			    			{
//			    				String hqlUpdate3 = " from TB_CENSUS_CHILDREN where comm_id=:comm_id and name=:name order by id desc ";
//
//			    				Query query4 = sessionhql.createQuery(hqlUpdate3).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]).setParameter("name", Data1);
//
//			    				List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query4.list();
//			    				
//			    				if(!list.isEmpty())
//			    				{
//			    					Date date=new Date();
//			    					String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,name=:name " 
//			    												+ "where  id=:id";
//
//			    					Query query2 = sessionhql.createQuery(hql)
//			    							.setInteger("status", 0).setString("name", Data2)
//			    							.setString("modified_by", username).setTimestamp("modified_date", date).setInteger("id", list.get(0).getId());
//			    					msg = query2.executeUpdate() > 0 ? "updated successfully" : "Failure";
//			    				}
//			    				else {
//			    					msg="Details of Childeren is Not Filled ";
//			    					 Upload_msg="Failure";
//			    					tx.rollback();
//			    					tranaction=1;
//			    					break;
//			    				}
//			    			}
//			    		}
//			    			if(Data3.equals("Self") )
//			    			{
//
//			    				String hqlUpdate5 = " from TB_CHANGE_NAME where comm_id=:comm_id and status='0' order by id desc ";
//
//			    				Query query5 = sessionhql.createQuery(hqlUpdate5).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);
//
//			    				List<TB_CHANGE_NAME> list5 = (List<TB_CHANGE_NAME>) query5.list();
//			    				
////			    				if(census_id==0)
////			    				{
////			    					String hql15 = "update TB_CHANGE_NAME set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";
////
////				    				Query query15 = sessionhql.createQuery(hql15).setInteger("status", 2)
////				    						.setBigInteger("comm_id",(BigInteger)list6.get(0)[0] );
////
////				    				msg = query15.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
////
////			    				}
////			    				else{
////			    					
////			    				}
//			    				
//			    				if (list5.isEmpty()) {
//			    					TB_CHANGE_NAME n = new TB_CHANGE_NAME();
//			    					n.setCreated_by(username);
//			    					n.setCreated_date(new Date());
//			    					n.setAuthority(auth);
//			    					n.setName(Data2);
//			    					n.setDate_of_authority(format.parse(authdate) );
//			    					n.setComm_id((BigInteger)list6.get(0)[0]);
//			    					n.setCensus_id(census_id);
//			    					n.setStatus(0);			    				
//			    					n.setCreated_by(username);			
//			    					n.setChange_in_name_date(format.parse(FmDate));			  
//			    					int id = (int) sessionhql.save(n);
//			    					if(id!=0)
//			    					{
//			    						msg="Data Uploaded SuccessFully";
//			    					}
//			    					
//			    				} else {
//			    				
//			    					String hql = " update TB_CHANGE_NAME set authority=:authority ,date_of_authority=:date_of_authority"
//			    							+ " ,name=:name,status=:status,modified_by=:modified_by,modified_date=:modified_date,"
//			    							+ " change_in_name_date=:change_in_name_date"
//			    							+ " where  id=:id";
//			    					Query query = sessionhql.createQuery(hql)
//
//			    							.setString("authority", auth)
//			    							.setTimestamp("date_of_authority", format.parse(authdate)).setString("name", Data2)
//			    						.setString("modified_by", username)
//			    							.setTimestamp("modified_date", new Date())
//			    	                        .setTimestamp("change_in_name_date",format.parse(FmDate)).setInteger("id", list5.get(0).getId());
//			    					query	.setInteger("status", 0);
//		    					
//			    					msg = query.executeUpdate() > 0 ? "Data Updated Successfully" : "0";
//			    					
//			    				}
//			    			}	    		
//			    	} catch (RuntimeException e) {
//			    		try {
//			    			tx.rollback();
//			    			msg = "Failure";
//			    			tranaction=1;
//			    		} catch (RuntimeException rbe) {
//			    			msg = "Failure";
//			    			tranaction=1;
//			    		}
//			    	} 		                          	              
//		              }
//		             
//		            
//		                if(description.equals("POSOUT"))
//		                {		             
//		                	casuality++;
////		                	Element data1Element = (Element) casualtyElement.getElementsByTagName("Data1").item(0);
////	                        String data1 = data1Element.getTextContent();		   
////	                        
//	                        Element medelement = (Element) casualtiesList.item(j);			              
//			              
//	                        
//	                        String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//			                String data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//			                String data4	 = medelement.getElementsByTagName("Data4").item(0).getTextContent();
//			                String hql8 = "SELECT sus_no, status_sus_no, unit_name "
//			                        + " FROM Miso_Orbat_Unt_Dtl "
//			                        + " WHERE (sus_no LIKE :susno OR unit_name LIKE :unitname) AND status_sus_no != 'INVALID'";
//
//			            Query query8 = sessionhql.createQuery(hql8);
//			            query8.setParameter("susno", data1 + "%");
//			            query8.setParameter("unitname", data4 + "%");
//
//			    			List <Object[]>list8 = query8.list();
//			    			if(list8.isEmpty())
//			    			{
//			    				msg="To SUS No not Found";
//			    				Upload_msg="Failure";
//			    				   tx.rollback();
//			    		      	   tranaction=1;
//			    		      	   break;
//			    			}
//			    			else {
//			    				
//			    			
//		                 	 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
//		  	               if (roleSusNo == null || roleSusNo.equals("")) {		  	          
//		  	                       roleSusNo =(String)list8.get(0)[0];
//		  	               }
//		  	              
//		                if(list6.get(0)[3].equals(roleSusNo))
//		                {
//		                	msg="From And To Sus Can't Be Same ";
//		                	 Upload_msg="Failure";
//	    					tx.rollback();
//	    					tranaction=1;
//	    					break;
//		                }
//		               
//		    			
//		    			if(! check_future(fmDate))
//                		{
//		    				msg = "Date of TOS  is Future Date";
//							tx.rollback();
//							tranaction=1;
//							break;
//                		}
//		                	TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
//		                	  ArrayList<ArrayList<String>> list = pod.GetCommDataApprove((BigInteger)list6.get(0)[0]);
//		               	  if( list.get(0).get(18).equals("1"))
//		                	  {
//		                		  if(rank.toUpperCase().equals( list6.get(0)[1]))
//		                		  {
//		                			     if (list.size() > 0)
//		                		            {
//		                		                    if(tos!=null && list.get(0).get(6)!=null) {
//		                		                            String regex = "^0+(?!$)";
//		                		                            String newTos=fmDate;
//		                		                            String preTos=list.get(0).get(6).toString().substring(0,10);
//		                		                          
//		                		                            String newTosArr[]=newTos.split("/");
//		                		                            String preTosArr[]=preTos.split("-");
//		                		                            int newTosM=Integer.parseInt(newTosArr[1].replaceAll(regex, ""));
//		                		                            int newTosY=Integer.parseInt(newTosArr[2]);
//		                		                            int preTosM=Integer.parseInt(preTosArr[1].replaceAll(regex, ""));
//		                		                            int preTosY=Integer.parseInt(preTosArr[0]);
//		                		                            
//		                		                            if(newTosY==preTosY){
//		                		                                    if(newTosM<=preTosM){
//		                		                                            msg= "Invalid Date of TOS";
//		                		                                            Upload_msg="Failure";
//		                		                                            tx.rollback();
//		                		     			                      	   tranaction=1;
//		                		     			                      	   break;
//		                		                                    }
//		                		                            }
//		                		                            else if(newTosY<preTosY){
//		                		                                    msg= "Invalid Date of TOS";
//		                		                                    Upload_msg="Failure";
//		                		                                    tx.rollback();
//		                					                      	   tranaction=1;
//		                					                      	   break;
//		                		                            }
//		                		                    }
//		                		                    po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(2))));
//		                		                    if (list.get(0).get(14) != null && !list.get(0).get(14).equals("null"))
//		                		                    {
//		                		                            po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(14))));
//		                		                    }
//		                		            }
//		                			     
////		                			     int id1 = i_id > 0 ? po.getId() : 0;
//		                		            
//		                		                    Boolean v = pod.getpernoAlreadyExits((BigInteger) list6.get(0)[0]);
//		                		                    if (v == true)
//		                		                    {
//		                		                            msg = "Data already exists.";
//		                		                            Upload_msg="Failure";
//		                		                            tx.rollback();
//		             			                      	   tranaction=1;
//		             			                      	   break;
//		                		                            
//		                		                    }
//		                		                    if (v == false) {
//		                		                          //  if (id1 == 0) {
//		                		                                    String roleAccess = session.getAttribute("roleAccess").toString();
//		                		                                    ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
//		                		                                    ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
//		                		                                    po.setCreated_by(username);
//		                		                                    po.setCreated_date(new Date());
//		                		                                    //po.setCensus_id(census_id);
//		                		                                    po.setComm_id((BigInteger) list6.get(0)[0]);
//		                		                                    if(orbatlist.size()>0) {
//		                		                                    po.setCmd_sus(orbatlist.get(0).get(1));
//		                		                                    po.setCorps_sus(orbatlist.get(0).get(2));
//		                		                                    po.setDiv_sus(orbatlist.get(0).get(3));
//		                		                                    po.setBde_sus(orbatlist.get(0).get(4));
//		                		                                    }
//		                		                                    if(Location_Trnlist.size()>0) {
//		                		                                    po.setLocation(Location_Trnlist.get(0).get(0));
//		                		                                    po.setTrn_type(Location_Trnlist.get(0).get(1));
//		                		                                    }
//		                		                                  
//		                		                                	Date date_of_converstion1 = format.parse(tos);
//		                		                            		Timestamp ts = new Timestamp(date_of_converstion1.getTime());
//		                		                                    
//		                		                            		Date auth_date1 = format.parse(authdate);
//		                		                            		Timestamp ts2 = new Timestamp(date_of_converstion1.getTime());
//		                		                            		
//		                		                                    po.setFrom_sus_no((String)list6.get(0)[3]);
//		                		                                    po.setTo_sus_no(roleSusNo);
//		                		                                    po.setDt_of_sos(ts);		                		                                   
//		                		                                    po.setOut_auth(auth);
//		                		                                    po.setOut_auth_dt(ts2);
//		                		                                    po.setDt_of_tos(ts);
//		                		                                    po.setStatus(0);
//		                		                                   
//		                		                                    po.setT_status(0);
//		                		                                    //po.setNotification_status(0);
//		                		                                    sessionhql.save(po);
//		                		                                  //  int id12 = (int) sessionhql.save(po);
//		                		                              
//		                		                                    Upload_msg="Successfull";
//		                		                                    msg=" Data Uploaded Successfully";
//		                		                          //  }
//		                		                    }		                			     		                			     
//		                		  }
//		                		  else {
//		                			  msg="Change in Rank ";
//			                		  Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//		                		  }
//		                		 
//		                	  }
//		                	  else {
//		                		  msg="Individual Record is still in Pending for Approval";
//		                		  Upload_msg="Failure";
//		                		  tx.rollback();
//		                      	   tranaction=1;
//		                      	   break;
//		                	  }
//		                }
//		                }
////		                if (description.equals("RELAPPT")) {
////		                	casuality++;
////		                    Element medelement = (Element) casualtiesList.item(j);
////		                    String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
////		                    String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
////		                    String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
////		                    String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
////		                    String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();
////		                    Data3 = Data3.toUpperCase();		                	
////		                    Date date = new Date();
////		            		Date date_of_appointment = null;
////		            		date_of_appointment = format.parse(fromDate);
////		            		int census_id=getCensusid( (BigInteger)list6.get(0)[0]);
////		            		if(census_id!=0)
////		            		{
////		            			if (com.CompareDate(format.parse(authdate),format.parse((String)list6.get(0)[2])) == 0) {
////		            				msg= "Authority Date should be Greater than Commission Date";
////		            			}
////		            			else {
////		            			try {		            				
////		            				Query q1 = sessionhql.createQuery(
////		            						"select count(id) from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' and status_active = 'Active' and id =:id  ").setParameter("id", Integer.parseInt(Data2));
////		            				@SuppressWarnings("unchecked")
////		            				List<Long> list2 = (List<Long>) q1.list();
////		            				if(!list2.isEmpty())
////		            				{
////		            					if(list2.get(0)==0)
////			            				{
////			            					msg="Appt Code does not exist in  database";
////			            					tx.rollback();
////			    			    			tranaction=1;
////			    			    			break;
////			            				}	  
////		            				}
////		            				          				
////		            				String hqlUpdate1 = " select id from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='0'";
////		            				Query query3 = sessionhql.createQuery(hqlUpdate1);		            		
////		            				query3.setInteger("census_id", census_id);
////		            				query3.setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);		            	
////		            				List<Integer> list = query3.list();
////		            				int appoint_id;
////		            				if(list.size()!=0)
////		            				{
////		            					appoint_id =list.get(0);
////		            				}
////		            				else {
////		            					 appoint_id=0;
////		            				}
////		            				Query q10 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_APPOINTMENT where date_of_appointment=:date_of_appointment and comm_id=:comm_id  and status!=-1").setTimestamp("date_of_appointment", date_of_appointment)
////		            					.setParameter("comm_id", (BigInteger)list6.get(0)[0]);
////		            					Long c = (Long) q10.uniqueResult();
////		            					if(c>0) {
////		            					msg= "Date of Appointment Already Exists";
////		            					}
////		            					else {
////		            					if (appoint_id == 0) {
////		            					TB_CHANGE_OF_APPOINTMENT appt = new TB_CHANGE_OF_APPOINTMENT();
////		            					appt.setCreated_by(username);
////		            					appt.setCreated_date(date);
////		            					appt.setAppt_authority(auth);
////		            					appt.setAppt_date_of_authority(format.parse(authdate));
////		            					appt.setAppointment(Integer.parseInt(Data2));
////		            					appt.setDate_of_appointment(date_of_appointment);
//////		            					appt.setX_list_sus_no(x_sus_no);
//////		            					appt.setX_list_loc(x_list_loc);
////		            					appt.setComm_id((BigInteger)list6.get(0)[0]);
////		            					appt.setCensus_id(census_id);
////		            					appt.setStatus(0);
////		            					appt.setCreated_by(username);
////		            					appt.setCreated_date(date);
//////		            					appt.setParent_sus_no(parent_sus_no);
//////		            					appt.setParent_unit(parent_unit);
//////		            					appt.setX_list_loc(x_list_loc);
////		            					
////		            					int id = (int) sessionhql.save(appt);
////		            					msg = "Inserted Successfully";
////		            				} else {
////		            					
////		            					String hql = "update TB_CHANGE_OF_APPOINTMENT set appt_authority=:appt_authority, "
////		            							+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
////		            							+ "date_of_appointment=:date_of_appointment,status=:status,"
//////		            							+ "x_list_sus_no=:x_list_sus_no,x_list_loc=:x_list_loc, "
////		            	                        + "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,x_list_loc=:x_list_loc, "
////		            							+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
////		            					Query query = sessionhql.createQuery(hql).setInteger("status", 0)
////		            							.setString("appt_authority", auth)
////		            							.setTimestamp("appt_date_of_authority", format.parse(authdate))
////		            							//.setString("x_list_sus_no", x_sus_no).setString("x_list_loc", x_list_loc)
////		            						.setString("parent_sus_no", "")
////		            							.setString("parent_unit", "")
////		            							.setString("x_list_loc", "")
////		            							.setInteger("appointment", Integer.parseInt(Data2))
////		            							.setTimestamp("date_of_appointment", date_of_appointment).setString("modified_by", username)
////		            							.setTimestamp("modified_date", new Date()).setInteger("id", appoint_id);
////
////		            					msg = query.executeUpdate() > 0 ? "Updated SuccessFully" : "0";	
////		            				}
////
////		            					}
////		            			} catch (RuntimeException e) {
////		            				try {
////		            					tx.rollback();
////		            					msg = "Data Not Updated Or Saved";
////		            				} catch (RuntimeException rbe) {
////		            					msg = "Data Not Updated Or Saved";
////		            				}
////		            			} 
////		            			}
////		            		}
////		            		else {
////		            			msg="Census Is Not Fillled";
////		            		}		                	
////		                }
//		                
//		                if(description.equals("ASMAPPT"))
//		                {
//		                	casuality++;		                	
//		                	 Element medelement = (Element) casualtiesList.item(j);		             
//		                	 String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				             String RMK = medelement.getElementsByTagName("Rmk1").item(0).getTextContent();			
//	                        Session session1 = HibernateUtil.getSessionFactory().openSession();
//	                		Query q2 = session1.createQuery(
//	                				" from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' and status_active = 'Active' and UPPER(description)=:rmk order by description ");
//	        				q2.setParameter("rmk",RMK.trim().toUpperCase());
//	                		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q2.list();
//	                		if(!list.isEmpty())
//	                		{
//	                		String comm_date=(String) list6.get(0)[2];
//	                		String tos_date=(String) list6.get(0)[4];
//	                		
//	                		 
//	                		 
//	                	if( check_future(fmDate))
//	                		{
//	                		Date date = new Date();
//	                		
//	                		Date date_of_appointment = null;
//	                		date_of_appointment = format.parse(fmDate);
//	                
//	                		int census_id=getCensusid( (BigInteger) list6.get(0)[0]);
//	                			
//	                			if (com.CompareDate(format.parse(authdate),format.parse(comm_date)) == 0) {
//	                				msg= "Authority Date should be Greater than Commission Date";
//	                				 Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//	                			}
//	                			
//	                			 if(com.CompareDate(date_of_appointment,format.parse(tos_date)) == 0){
//	                				msg="Date of Appointment Date should be Greater than TOS Date";
//	                				Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//	                			}
//	                			else {
//	                			try {
//	                			
//
//	                				String hqlUpdate1 = " select id from TB_CHANGE_OF_APPOINTMENT where   comm_id=:comm_id and status='0'";
//	                				Query query3 = sessionhql.createQuery(hqlUpdate1);	                		
//	                				query3.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//	                	
//	                				List<Integer> list2 = query3.list();
//	                				int appoint_id;
//	                				if(list2.size()!=0)
//	                				{
//	                					appoint_id =list2.get(0);
//	                				}
//	                				else {
//	                					 appoint_id=0;
//	                				}	                				
//	                				Query q10 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_APPOINTMENT where date_of_appointment=:date_of_appointment and comm_id=:comm_id  and status!=-1").setTimestamp("date_of_appointment", date_of_appointment)
//	                					.setParameter("comm_id", (BigInteger) list6.get(0)[0]);
//	                					Long c = (Long) q10.uniqueResult();
//	                					if(c>0) {
//	                					msg= "Date of Appointment Already Exists";
//	                					 Upload_msg="Failure";
//		                					tx.rollback();
//		     	                      	   tranaction=1;
//		     	                      	   break;
//	                					}
//	                					else {
////	                						if(census_id==0)
////	                    					{
////	                    						String hql13 = "update TB_CHANGE_OF_APPOINTMENT set status=:status where comm_id=:comm_id and (status != '0' and status != '-1') ";
////
////	                    						Query query13 = sessionhql.createQuery(hql13).setInteger("status", 2)
////	                    								.setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);
////
////	                    						msg = query13.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
////	                    						
////	                    					}
//
//	                					if (appoint_id == 0) {
//	                					TB_CHANGE_OF_APPOINTMENT appt = new TB_CHANGE_OF_APPOINTMENT();
//	                					appt.setCreated_by(username);
//	                					appt.setCreated_date(date);
//	                					appt.setAppt_authority(auth);
//	                					appt.setAppt_date_of_authority(format.parse(authdate));
//	                					appt.setAppointment(list.get(0).getId());
//	                					appt.setDate_of_appointment(date_of_appointment);
////	                					appt.setX_list_sus_no(x_sus_no);
////	                					appt.setX_list_loc(x_list_loc);
//	                					appt.setComm_id((BigInteger) list6.get(0)[0]);
//	                					appt.setCensus_id(census_id);
////	                					if(census_id==0)
////	                					{
////	                						appt.setStatus(1);
////	                					}
////	                					else {
////	                				
////	                						appt.setStatus(0);
////	                					}
//	                					appt.setStatus(0);
//	                					appt.setCreated_by(username);
//	                					appt.setCreated_date(date);
////	                					appt.setParent_sus_no(parent_sus_no);
////	                					appt.setParent_unit(parent_unit);
////	                					appt.setX_list_loc(x_list_loc);
//	                					
//	                					int id = (int) sessionhql.save(appt);
//	                					msg = "Data Uploaded Successfully";
//	                					//tx.commit();
//	                				//	msg=approve_appointment(msg,(BigInteger) list6.get(0)[0],census_id,username);
//	                				} else {
//	                					
//	                					String hql = "update TB_CHANGE_OF_APPOINTMENT set appt_authority=:appt_authority, "
//	                							+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
//	                							+ "date_of_appointment=:date_of_appointment,status=:status,"
////	                							+ "x_list_sus_no=:x_list_sus_no,x_list_loc=:x_list_loc, "
//	                	                        + "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,x_list_loc=:x_list_loc, "
//	                							+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
//	                					Query query = sessionhql.createQuery(hql)
//	                							.setString("appt_authority", auth)
//	                							.setTimestamp("appt_date_of_authority", format.parse(authdate))
//	                							//.setString("x_list_sus_no", x_sus_no).setString("x_list_loc", x_list_loc)
//	                						.setString("parent_sus_no", "")
//	                							.setString("parent_unit", "")
//	                							.setString("x_list_loc", "")
//	                							.setInteger("appointment",list.get(0).getId())
//	                							.setTimestamp("date_of_appointment", date_of_appointment).setString("modified_by", username)
//	                							.setTimestamp("modified_date", new Date()).setInteger("id", appoint_id);
//
//	                					if(census_id==0)
//	                					{
//	                						query.setInteger("status", 1);
//	                					}
//	                					else {	                				
//	                						query.setInteger("status", 0);
//	                						update_casuality++;
//	                					}
//	                					msg = query.executeUpdate() > 0 ? "Data Uploaded Successfully" : "Failure";
//	
//	                				}				
//	                					}
//	                			} catch (RuntimeException e) {
//	                				try {	                	
//	                					msg = "Data Not Updated Or Saved";
//	                					 Upload_msg="Failure";
//	                					tx.rollback();
//	     	                      	   tranaction=1;
//	     	                      	   break;
//	                				} catch (RuntimeException rbe) {
//	                					msg = "Data Not Updated Or Saved";
//	                					 Upload_msg="Failure";
//	                					tx.rollback();
//	     	                      	   tranaction=1;
//	     	                      	   break;
//	                				}
//	                			} 
//	                			}               		
//	                		}
//	                	else {
//	                		msg="Date of Rank is future date";
//	                		 Upload_msg="Failure";
//	                		tx.rollback();
//	                      	   tranaction=1;
//	                      	   break;
//	                	}	
//	                		}
//
//	                		else {
//	                			  msg="Appt Code does not exist  ";
//		                		  Upload_msg="Failure";
//		                		  tx.rollback();
//		                      	   tranaction=1;
//		                      	   break;
//	                		}
//		                }
//		                if (description.equals("PRETIRE")) {
//							casuality++;
//							Element medelement = (Element) casualtiesList.item(j);
//							String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//							String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
//							String Authority = medelement.getElementsByTagName("Authority").item(0).getTextContent();
//							String date_non_effective = medelement.getElementsByTagName("Rmk2").item(0).getTextContent();
//							Date date_of_ne = format.parse(date_non_effective);
//							Timestamp ts = new Timestamp(date_of_ne.getTime());
//							Date auth_date = format.parse(FmDate);
//							Timestamp ts2 = new Timestamp(auth_date.getTime());
//							String comm_date=(String) list6.get(0)[2];
//							int census_id = getCensusid((BigInteger) list6.get(0)[0]);						
//							if (com.CompareDate(auth_date,format.parse(comm_date)) == 0) {
//                				msg= "Authority Date should be Greater than Commission Date";
//                				 Upload_msg="Failure";
//		                		  tx.rollback();
//		                      	   tranaction=1;
//		                      	   break;
//                			}
//
//							ArrayList<ArrayList<String>> list = xmldao.GetServingStatus_xml((BigInteger) list6.get(0)[0]);
//							if (!list.isEmpty()) {
//								String firstValue = list.get(0).get(2);
//								if (firstValue.equals("SERVING")) {
//									
//									List<TB_CENSUS_ADDRESS> add_approved=getPerAddressDataStatus_XML((BigInteger) list6.get(0)[0],"1");
//									
//									if(add_approved.isEmpty())
//									{																				
//										List<TB_CENSUS_ADDRESS> add_pending=getPerAddressDataStatus_XML((BigInteger) list6.get(0)[0],"0");
//										if (add_pending.isEmpty()) {
//											TB_CENSUS_ADDRESS addr = new TB_CENSUS_ADDRESS();
//											addr.setAuthority(auth);
//											addr.setDate_authority(authDate);
//											addr.setPre_state(41);
//											addr.setPre_tesil(5939);
//											addr.setPre_country(15);
//											addr.setPre_district(679);
//											addr.setPresent_state(41);
//											addr.setPresent_country(15);
//											addr.setPresent_district(679);
//											addr.setPresent_village("NA");
//											addr.setPresent_pin_code(000000);
//											addr.setPresent_near_railway_station("NA");
//											addr.setPresent_rural_urban_semi("semi_urban");
//											addr.setPresent_tehsil(5939);
//											addr.setPermanent_country(15);
//											addr.setPermanent_district(679);
//											addr.setPermanent_state(41);
//											addr.setPermanent_tehsil(5939);
//											addr.setPermanent_village("NA");
//											addr.setPermanent_pin_code(0);
//											addr.setPermanent_near_railway_station("NA");
////											addr.setPermanent_rural_urban_semi("semi_urban");
////											addr.setPermanent_border_area(border_area);
//											addr.setComm_id((BigInteger) list6.get(0)[0]);
//											addr.setCen_id(census_id);
//											addr.setStatus(0);
//											
//											addr.setPers_addr_country_other("NA");
//											addr.setPers_addr_district_other("NA");
//											addr.setPers_addr_state_other("NA");
//											addr.setPers_addr_tehsil_other("NA");
//											
//											addr.setSpouse_addr_country_other("NA");
//											addr.setSpouse_addr_district_other("NA");
//											addr.setSpouse_addr_state_other("NA");
//											addr.setSpouse_addr_tehsil_other("NA");
//											addr.setSpouse_rural_urban_semi("NA");
//											addr.setSpouse_country(15);
//											addr.setSpouse_state(41);
//											addr.setSpouse_district(679);
//											addr.setSpouse_tehsil(5939);
//											addr.setSpouse_village("NA");
//											addr.setSpouse_near_railway_station("NA");
//											addr.setSpouse_pin_code(0);
////											addr.setStn_hq_sus_no(addr2.get(0).getStn_hq_sus_no());
//											
//											addr.setPer_home_addr_country_other("NA");
//											addr.setPer_home_addr_state_other("NA");
//											addr.setPer_home_addr_district_other("NA");
//											addr.setPer_home_addr_tehsil_other("NA");
//											
//											addr.setPre_country_other("NA");
//											addr.setPre_domicile_state_other("NA");
//											addr.setPre_domicile_district_other("NA");
//											addr.setPre_domicile_tesil_other("NA");
//											 addr.setCreated_by(username);
//											 addr.setCreated_date(new Date());
//											int id1 = (int) sessionhql.save(addr);
//										if(id1==0)
//										{msg="Address Not Found";
//										 Upload_msg="Failure";
//				                		  tx.rollback();
//				                      	   tranaction=1;
//											break;
//										}
//										}
//									}
//
//							 else {
//									String hql_Upadte_add = "update TB_CENSUS_ADDRESS set  "										
//											+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  id=:id";
//									Query query3add = sessionhql.createQuery(hql_Upadte_add)										
//											.setString("modified_by", username)
//											.setTimestamp("modified_date", new Date())
//											.setInteger("status", 0)
//											.setInteger("id", add_approved.get(0).getId());
//									msg = query3add.executeUpdate() > 0 ? "Address Uploaded Successfully" :"0";
//							 }
//										String checkUpdate = " select id from TB_NON_EFFECTIVE where comm_id=:comm_id and  status = '0'";
//										Query query2 = sessionhql.createQuery(checkUpdate)							
//												.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//										List<Integer> list_update = (List<Integer>) query2.list();
//										if (list_update.isEmpty()) {
//											
//												TB_NON_EFFECTIVE non = new TB_NON_EFFECTIVE();
//												non.setCreated_by(username);
//												non.setCreated_date(new Date());
//												non.setNon_ef_authority(auth);
//												non.setDate_of_authority_non_ef(ts2);
//												non.setCause_of_non_effective("25");
//												non.setDate_of_non_effective(ts);
//												non.setComm_id((BigInteger) list6.get(0)[0]);
//												non.setCensus_id(census_id);
//												non.setStatus(0);
//												non.setCreated_by(username);
//												non.setCreated_date(new Date());
//												int id = (int) sessionhql.save(non);
//												if(id!=0)
//												{
//													msg = "Data Uploaded Successfully";
//												
//												}
//												else
//												{
//													msg = "Data Not Uploaded ";
//													  Upload_msg="Failure";
//							                		  tx.rollback();
//							                      	   tranaction=1;
//							                      	   break;
//												}
//												
//										
//										}
//											
//											else {
//												String hql_Upadte = "update TB_NON_EFFECTIVE set non_ef_authority=:non_ef_authority, date_of_authority_non_ef=:date_of_authority_non_ef, cause_of_non_effective=:cause_of_non_effective, "
//														+ "date_of_non_effective=:date_of_non_effective, "
//														+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  status='0' and id=:id";
//												Query query3 = sessionhql.createQuery(hql_Upadte)
//														.setString("non_ef_authority", auth)
//														.setTimestamp("date_of_authority_non_ef", ts2)
//														.setString("cause_of_non_effective", "25")
//														.setTimestamp("date_of_non_effective", ts)
//														.setString("modified_by", username)
//														.setTimestamp("modified_date", new Date())
//														.setInteger("status", 0)
//														.setInteger("id", list_update.get(0))
//														;
//												
//												msg = query3.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";
//												
//												
//												if (!msg.equals("Data Uploaded Successfully")) {
//													msg = "Data Not Uploaded";
//													  Upload_msg="Failure";
//							                		  tx.rollback();
//							                      	   tranaction=1;
//							                      	   break;
//												
//												}
//												
//											}
//
//										
//										//}
////									}
//								}
//								else {
//									msg="Personel No is not in Serving Status";
//									  Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      
//									break;
//								}
//							}
//						}   
//		                
//		                
//		                
//		                if (description.equals("RETIRE")) {
//							casuality++;
//							Element medelement = (Element) casualtiesList.item(j);
//							String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//							String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
//							String Authority = medelement.getElementsByTagName("Authority").item(0).getTextContent();
//							String date_non_effective = medelement.getElementsByTagName("Rmk2").item(0).getTextContent();
//							Date date_of_ne = format.parse(date_non_effective);
//							Timestamp ts = new Timestamp(date_of_ne.getTime());
//							Date auth_date = format.parse(FmDate);
//							Timestamp ts2 = new Timestamp(auth_date.getTime());
//							String comm_date=(String) list6.get(0)[2];
//							int census_id = getCensusid((BigInteger) list6.get(0)[0]);						
//							if (com.CompareDate(auth_date,format.parse(comm_date)) == 0) {
//                				msg= "Authority Date should be Greater than Commission Date";
//                				 Upload_msg="Failure";
//		                		  tx.rollback();
//		                      	   tranaction=1;
//		                      	   break;
//                			}
//
//							ArrayList<ArrayList<String>> list = xmldao.GetServingStatus_xml((BigInteger) list6.get(0)[0]);
//							if (!list.isEmpty()) {
//								String firstValue = list.get(0).get(2);
//								if (firstValue.equals("SERVING")) {
//									
//									List<TB_CENSUS_ADDRESS> add_approved=getPerAddressDataStatus_XML((BigInteger) list6.get(0)[0],"1");
//									
//									if(add_approved.isEmpty())
//									{																				
//										List<TB_CENSUS_ADDRESS> add_pending=getPerAddressDataStatus_XML((BigInteger) list6.get(0)[0],"0");
//										if (add_pending.isEmpty()) {
//											TB_CENSUS_ADDRESS addr = new TB_CENSUS_ADDRESS();
//											addr.setAuthority(auth);
//											addr.setDate_authority(authDate);
//											addr.setPre_state(41);
//											addr.setPre_tesil(5939);
//											addr.setPre_country(15);
//											addr.setPre_district(679);
//											addr.setPresent_state(41);
//											addr.setPresent_country(15);
//											addr.setPresent_district(679);
//											addr.setPresent_village("NA");
//											addr.setPresent_pin_code(000000);
//											addr.setPresent_near_railway_station("NA");
//											addr.setPresent_rural_urban_semi("semi_urban");
//											addr.setPresent_tehsil(5939);
//											addr.setPermanent_country(15);
//											addr.setPermanent_district(679);
//											addr.setPermanent_state(41);
//											addr.setPermanent_tehsil(5939);
//											addr.setPermanent_village("NA");
//											addr.setPermanent_pin_code(0);
//											addr.setPermanent_near_railway_station("NA");
////											addr.setPermanent_rural_urban_semi("semi_urban");
////											addr.setPermanent_border_area(border_area);
//											addr.setComm_id((BigInteger) list6.get(0)[0]);
//											addr.setCen_id(census_id);
//											addr.setStatus(0);
//											
//											addr.setPers_addr_country_other("NA");
//											addr.setPers_addr_district_other("NA");
//											addr.setPers_addr_state_other("NA");
//											addr.setPers_addr_tehsil_other("NA");
//											
//											addr.setSpouse_addr_country_other("NA");
//											addr.setSpouse_addr_district_other("NA");
//											addr.setSpouse_addr_state_other("NA");
//											addr.setSpouse_addr_tehsil_other("NA");
//											addr.setSpouse_rural_urban_semi("NA");
//											addr.setSpouse_country(15);
//											addr.setSpouse_state(41);
//											addr.setSpouse_district(679);
//											addr.setSpouse_tehsil(5939);
//											addr.setSpouse_village("NA");
//											addr.setSpouse_near_railway_station("NA");
//											addr.setSpouse_pin_code(0);
////											addr.setStn_hq_sus_no(addr2.get(0).getStn_hq_sus_no());
//											
//											addr.setPer_home_addr_country_other("NA");
//											addr.setPer_home_addr_state_other("NA");
//											addr.setPer_home_addr_district_other("NA");
//											addr.setPer_home_addr_tehsil_other("NA");
//											
//											addr.setPre_country_other("NA");
//											addr.setPre_domicile_state_other("NA");
//											addr.setPre_domicile_district_other("NA");
//											addr.setPre_domicile_tesil_other("NA");
//											 addr.setCreated_by(username);
//											 addr.setCreated_date(new Date());
//											int id1 = (int) sessionhql.save(addr);
//										if(id1==0)
//										{msg="Address Not Found";
//										 Upload_msg="Failure";
//				                		  tx.rollback();
//				                      	   tranaction=1;
//											break;
//										}
//										}
//									}
//
//							 else {
//									String hql_Upadte_add = "update TB_CENSUS_ADDRESS set  "										
//											+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  id=:id";
//									Query query3add = sessionhql.createQuery(hql_Upadte_add)										
//											.setString("modified_by", username)
//											.setTimestamp("modified_date", new Date())
//											.setInteger("status", 0)
//											.setInteger("id", add_approved.get(0).getId());
//									msg = query3add.executeUpdate() > 0 ? "Address Uploaded Successfully" :"0";
//							 }
//										String checkUpdate = " select id from TB_NON_EFFECTIVE where comm_id=:comm_id and  status = '0'";
//										Query query2 = sessionhql.createQuery(checkUpdate)							
//												.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//										List<Integer> list_update = (List<Integer>) query2.list();
//										if (list_update.isEmpty()) {
//											
//												TB_NON_EFFECTIVE non = new TB_NON_EFFECTIVE();
//												non.setCreated_by(username);
//												non.setCreated_date(new Date());
//												non.setNon_ef_authority(auth);
//												non.setDate_of_authority_non_ef(ts2);
//												non.setCause_of_non_effective("17");
//												non.setDate_of_non_effective(ts);
//												non.setComm_id((BigInteger) list6.get(0)[0]);
//												non.setCensus_id(census_id);
//												non.setStatus(0);
//												non.setCreated_by(username);
//												non.setCreated_date(new Date());
//												int id = (int) sessionhql.save(non);
//												if(id!=0)
//												{
//													msg = "Data Uploaded Successfully";
//												
//												}
//												else
//												{
//													msg = "Data Not Uploaded ";
//													  Upload_msg="Failure";
//							                		  tx.rollback();
//							                      	   tranaction=1;
//							                      	   break;
//												}
//												
//										
//										}
//											
//											else {
//												String hql_Upadte = "update TB_NON_EFFECTIVE set non_ef_authority=:non_ef_authority, date_of_authority_non_ef=:date_of_authority_non_ef, cause_of_non_effective=:cause_of_non_effective, "
//														+ "date_of_non_effective=:date_of_non_effective, "
//														+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  status='0' and id=:id";
//												Query query3 = sessionhql.createQuery(hql_Upadte)
//														.setString("non_ef_authority", auth)
//														.setTimestamp("date_of_authority_non_ef", ts2)
//														.setString("cause_of_non_effective", "17")
//														.setTimestamp("date_of_non_effective", ts)
//														.setString("modified_by", username)
//														.setTimestamp("modified_date", new Date())
//														.setInteger("status", 0)
//														.setInteger("id", list_update.get(0))
//														;
//												
//												msg = query3.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";
//												
//												
//												if (!msg.equals("Data Uploaded Successfully")) {
//													msg = "Data Not Uploaded";
//													  Upload_msg="Failure";
//							                		  tx.rollback();
//							                      	   tranaction=1;
//							                      	   break;
//												
//												}
//												
//											}
//
//										
//										//}
////									}
//								}
//								else {
//									msg="Personel No is not in Serving Status";
//									  Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      
//									break;
//								}
//							}
//						}  
//		                
//		                
//		                
////		            	if(description.equals("RETIRE"))
////						{
////							casuality++;
////							Element medelement = (Element) casualtiesList.item(j);
////							String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
////							String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
////							String Authority = medelement.getElementsByTagName("Authority").item(0).getTextContent();
////							String date_non_effective = medelement.getElementsByTagName("Rmk2").item(0)
////									.getTextContent();
////							Date date_of_ne = format.parse(date_non_effective);
////							Timestamp ts = new Timestamp(date_of_ne.getTime());
////							Date auth_date = format.parse(FmDate);
////							Timestamp ts2 = new Timestamp(auth_date.getTime());
////							String comm_date=(String) list6.get(0)[2];
////							if (com.CompareDate(auth_date,format.parse(comm_date)) == 0) {
////                				msg= "Authority Date should be Greater than Commission Date";
////                				 Upload_msg="Failure";
////		                		  tx.rollback();
////		                      	   tranaction=1;
////		                      	   break;
////                			}
////							ArrayList<ArrayList<String>> list = UOD.GetServingStatus((BigInteger) list6.get(0)[0]);
////							if (!list.isEmpty()) {
////								String firstValue = list.get(0).get(2);
////								if (firstValue.equals("SERVING")) {
////									String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '0'";
////									int census_id = getCensusid((BigInteger) list6.get(0)[0]);
////									Query query = sessionhql.createQuery(hqlUpdate).setInteger("census_id", census_id)
////											.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
////									List<TB_CENSUS_ADDRESS> add_pen = (List<TB_CENSUS_ADDRESS>) query.list();
////									if (add_pen.size() > 0) {
////										msg = "Please, First Approve The Pending Data of Address";
////										break;
////									} else {
////										String checkUpdate = " select count(id) from TB_NON_EFFECTIVE where census_id=:census_id and comm_id=:comm_id and  status = '0'";
////										Query query2 = sessionhql.createQuery(checkUpdate)
////												.setInteger("census_id", census_id)
////												.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
////										List<Long> list_update = (List<Long>) query2.list();
////										if (!list_update.isEmpty()) {
////											if (list_update.get(0) == 0) {
////												TB_NON_EFFECTIVE non = new TB_NON_EFFECTIVE();
////												non.setCreated_by(username);
////												non.setCreated_date(new Date());
////												non.setNon_ef_authority(auth);
////												non.setDate_of_authority_non_ef(ts2);
////												non.setCause_of_non_effective("20");
////												non.setDate_of_non_effective(ts);
////												non.setComm_id((BigInteger) list6.get(0)[0]);
////												non.setCensus_id(census_id);
////												non.setStatus(0);
////												non.setCreated_by(username);
////												non.setCreated_date(new Date());
////												int id = (int) sessionhql.save(non);
////												if(id!=0)
////						        				{
////						        					msg = "Data Uploaded Succeffully";
////						        				}else {
////						        					msg = "Data Not Uploaded ";
////						        					Upload_msg="Failure";
////							                		  tx.rollback();
////							                      	   tranaction=1;
////							                      	   break;    				
////						        				}
////						        				
////												
////											} else {
////												String hql_Upadte = "update TB_NON_EFFECTIVE set non_ef_authority=:non_ef_authority, date_of_authority_non_ef=:date_of_authority_non_ef, cause_of_non_effective=:cause_of_non_effective, "
////														+ "date_of_non_effective=:date_of_non_effective, "
////														+ "modified_by=:modified_by, modified_date=:modified_date,status=:status where  status='0' ";
////												Query query3 = sessionhql.createQuery(hql_Upadte)
////														.setString("non_ef_authority", auth)
////														.setTimestamp("date_of_authority_non_ef", ts2)
////														.setString("cause_of_non_effective", "20")
////														.setTimestamp("date_of_non_effective", ts)
////														.setString("modified_by", username)
////														.setTimestamp("modified_date", new Date())
////														.setInteger("status", 0);
////
////												msg = query3.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";
////												if (!msg.equals("Data Uploaded Successfully")) {
////													msg = "Data Not Updated Successfully";
////													  Upload_msg="Failure";
////							                		  tx.rollback();
////							                      	   tranaction=1;
////							                      	   break;
////												}
////												
////											}
////
////											/*
////											 * try { String hql4 =
////											 * "update TB_NON_EFFECTIVE set status=:status where census_id=:census_id and comm_id=:comm_id and (status != '0' and status != '-1') "
////											 * ;
////											 * 
////											 * Query query4 = sessionhql.createQuery(hql4).setInteger("status", 2)
////											 * .setInteger("census_id", census_id) .setBigInteger("comm_id",
////											 * (BigInteger) list6.get(0)[0]);
////											 * 
////											 * msg = query4.executeUpdate() > 0 ? "Data Approve Successfully." :
////											 * "Data Not Approve Successfully.";
////											 * 
////											 * String hql5 =
////											 * "update TB_NON_EFFECTIVE set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
////											 * + " where census_id=:census_id and comm_id=:comm_id and status = '0' ";
////											 * 
////											 * Query query5 = sessionhql.createQuery(hql5) .setString("modified_by",
////											 * username) .setTimestamp("modified_date", new Date())
////											 * .setInteger("status", 1).setInteger("census_id", census_id)
////											 * .setBigInteger("comm_id", (BigInteger) list6.get(0)[0]); msg =
////											 * query5.executeUpdate() > 0 ? "Data Approve Successfully." :
////											 * "Data Not Approve Successfully."; sessionhql.flush(); sessionhql.clear();
////											 * String hqlUpdate_s =
////											 * "from TB_NON_EFFECTIVE where comm_id=:comm_id and status=1 order by id desc "
////											 * ; Query query_s = sessionhql.createQuery(hqlUpdate_s)
////											 * .setBigInteger("comm_id", (BigInteger) list6.get(0)[0])
////											 * .setMaxResults(1); List<TB_NON_EFFECTIVE> cget = (List<TB_NON_EFFECTIVE>)
////											 * query_s.list();
////											 * 
////											 * if (cget.size() == 0) { tx.rollback(); msg =
////											 * "Data Not Approve Successfully."; break; }
////											 * 
////											 * TB_TRANS_PROPOSED_COMM_LETTER cl = (TB_TRANS_PROPOSED_COMM_LETTER)
////											 * sessionhql .get(TB_TRANS_PROPOSED_COMM_LETTER.class, (BigInteger)
////											 * list6.get(0)[0]); String hqlUpdate3 =
////											 * "select id from TB_POSTING_IN_OUT where comm_id=:comm_id and status=1 and to_sus_no=:to_sus_no order by id desc "
////											 * ; Query query3 = sessionhql.createQuery(hqlUpdate3)
////											 * .setBigInteger("comm_id", cl.getId()) .setString("to_sus_no",
////											 * cl.getUnit_sus_no()).setMaxResults(1); Integer c = (Integer)
////											 * query3.uniqueResult();
////											 * 
////											 * if (c != null && c > 0) { int chang_id = c.intValue(); String hql2 =
////											 * "update TB_POSTING_IN_OUT set modified_by=:modified_by,modified_date=:modified_date,tenure_date=:tenure_date "
////											 * + " where id=:id ";
////											 * 
////											 * Query query7 = sessionhql.createQuery(hql2) .setString("modified_by",
////											 * username) .setTimestamp("modified_date", new Date())
////											 * .setTimestamp("tenure_date", cget.get(0).getDate_of_non_effective())
////											 * .setInteger("id", chang_id); msg = query7.executeUpdate() > 0 ?
////											 * "Data Approve Successfully." : "Data Not Approve Successfully."; }
////											 * 
////											 * String hql8 =
////											 * "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
////											 * + "status=:status where id=:id ";
////											 * 
////											 * Query query8 = sessionhql.createQuery(hql8) .setString("modified_by",
////											 * username) .setTimestamp("modified_date", new Date())
////											 * .setInteger("status", 4) .setBigInteger("id", (BigInteger)
////											 * list6.get(0)[0]);
////											 * 
////											 * msg = query8.executeUpdate() > 0 ? "Data Updated Successfully." :
////											 * "Data Not Updated.";
////											 * 
////											 * 
////											 * } catch (Exception e) { msg = "Data Not Approve Successfully.";
////											 * tx.rollback(); }
////											 */
////										}
////									}
////								}
////								else {
////									msg="Personel No is not in Serving Status";
////									 Upload_msg="Failure";
////			                		  tx.rollback();
////			                      	   tranaction=1;
////			                      	   break;
////								}
////							}		
////						}
////		            	
//		            	
//		            	
//		                if(description.equals("EXTNREMP"))		                		                	
//		                {
//		                	casuality++;
//		                	 Element medelement = (Element) casualtiesList.item(j);			              
//				                String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String rank_code = medelement.getElementsByTagName("Data1").item(0).getTextContent();
////				                String data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
////				                String data3 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
////				                String data4 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//				                String Authority = medelement.getElementsByTagName("Authority").item(0).getTextContent();
//				                Date fdate = format.parse(FmDate);
//								Timestamp from_date = new Timestamp(fdate.getTime());
//								Date tdate = format.parse(ToDate);
//								Timestamp to_date = new Timestamp(tdate.getTime());
//								Date adate = format.parse(authdate);
//								Timestamp auth_date = new Timestamp(adate.getTime());
//								if(!rank_code.equals(Rank_Code))
//								{msg="Change of Rank Is Required";
//								 tx.rollback();
//		                      	 tranaction=1;		             
//								break;
//								}
//								String comm_date=(String) list6.get(0)[2];
//								if (com.CompareDate(auth_date,format.parse(comm_date)) == 0) {
//	                				msg= "Authority Date should be Greater than Commission Date";
//	                				 Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//	                			}
//								
//								
//								
//				                try { 
//				                	  int census_id=getCensusid((BigInteger) list6.get(0)[0]);						                				              
//				                 	String checkremployment = " select id from TB_REEMPLOYMENT where comm_id=:comm_id and  status = '1' order by id desc";
//									Query query9 = sessionhql.createQuery(checkremployment)
//										
//											.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//									List<Integer> list_update = (List<Integer>) query9.setMaxResults(1).list();
//				                if(list_update.isEmpty())
//				                {
//				                	msg="Re-employment is Not Found"; 
//				                	 tx.rollback();
//			                      	 tranaction=1;
//			                      	  break;
//		
//				                }
//				                
//				                
//				                	String checkUpdate = " select count(id) from TB_REEMP_EXTENTION where comm_id=:comm_id and  status = '1'";
//									Query query2 = sessionhql.createQuery(checkUpdate)
//										
//											.setBigInteger("comm_id", (BigInteger) list6.get(0)[0]);
//									List<Long> list_update_remp = (List<Long>) query2.list();
//									if (!list_update_remp.isEmpty()) {
//								if (list_update_remp.get(0) == 0) {				    					
//				    					TB_REEMP_EXTENTION ext = new TB_REEMP_EXTENTION();
//				    						 
//				    						ext.setFrom_dt(from_date);
//				    						ext.setTo_dt(to_date);
//				    						ext.setAuth_dt(auth_date);
//				    						ext.setStatus(0);
//				    						ext.setAuth_no(auth);
//				    						ext.setRe_emp_id(list_update.get(0));
//				    						ext.setCreated_date(new Date() );
//				    						ext.setCreated_by(username);
//				    						ext.setComm_id((BigInteger) list6.get(0)[0]);
//				    						ext.setCensus_id(census_id);
//				    						//ext.setRe_emp_select(3);
//				    						int id = (int) sessionhql.save(ext);
//				    						if(id!=0)
//					        				{
//					        					msg = "Data Uploaded Succeffully";
//					        				}else {
//					        					msg = "Data Not Uploaded ";
//					        					Upload_msg="Failure";
//						                		  tx.rollback();
//						                      	   tranaction=1;
//						                      	   break;    				
//					        				}
//					        				
////				    				
//			    				}else {
//				    					String hql = "update TB_REEMP_EXTENTION set "
//				    							+ "from_dt=:E_fr_dt,to_dt=:E_t_dt,auth_dt=:E_auth_dt,auth_no=:auth_no,"
//				    							+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  where comm_id=:comm_id ";
//				    		
//				    					Query query = sessionhql.createQuery(hql)
//				    							 .setDate("E_fr_dt", from_date)
//				    							 .setDate("E_t_dt", to_date)
//				    							 .setDate("E_auth_dt", auth_date)
//				    							 .setString("auth_no", auth)
//				    							 .setString("modified_by", username)
//				    							 .setTimestamp("modified_date", new Date())
//				    							 .setInteger("status", 0) 				    
//				    							 .setBigInteger("comm_id",(BigInteger) list6.get(0)[0] );
//				    			 
//				    			          msg = query.executeUpdate() > 0 ? "Data Uploaded Succeffully" : "0";
//				    				//}
////				    				com.update_miso_role_hdr_status( (BigInteger) list6.get(0)[0],0,username,"re_emp_status");
////				    				 String hqlUpdate1 = "update TB_REEMP_EXTENTION set status=:status,modified_by=:modified_by,modified_date=:modified_date  where comm_id=:id and status='0'";
////				 					int app = sessionhql.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
////				 							.setDate("modified_date", new Date())
////				 							.setBigInteger("id", (BigInteger) list6.get(0)[0]).executeUpdate();
//////				 					TB_REEMP_EXTENTION tb_rem = (TB_REEMP_EXTENTION)sessionhql.get(TB_REEMP_EXTENTION.class, id);   
////				 					com.update_miso_role_hdr_status((BigInteger) list6.get(0)[0],1,username,"re_emp_status");
////				 			 
////				 				if (app > 0) {
////				 					msg="Approved Successfully.";
////				 				} else {
////				 					tx.rollback();
////				 				msg="Approved Not Successfully.";
////				 				}
//				    		
//				    		} 
//									}
//									
//				                }
//									catch (RuntimeException e) {
//				    			try {
//				    				tx.rollback();
//				    				msg = "0";
//				    			} catch (RuntimeException rbe) {
//				    				msg = "0";
//				    			}
//
//				    		}  
//  
//		                }      	
//		            	
//		            	
//		            	
//		            	
//		                if(description.equals("MEDAL")) 
//		                {		                	
//		                	casuality++;
//		                    Element medelement = (Element) casualtiesList.item(j);
//		                    String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//		                    String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
//		                    String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//		                    String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//		                    String Rmk1 = medelement.getElementsByTagName("Rmk1").item(0).getTextContent();
//		                    Rmk1 = Rmk1.toUpperCase();
//		                	
//		                	
//
//		                	 ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(from_sus_no);
//		                			                		                                   
//		                	 TB_CENSUS_AWARDSNMEDAL[] instances = new TB_CENSUS_AWARDSNMEDAL[10];
//
//		            		try {
//		            			Query q1 = sessionhql.createQuery(
//	            						"  from TB_MEDAL_TYPE where  status = 'active'  and upper(medal_name)=:medal ").setParameter("medal", Rmk1);
//	            				@SuppressWarnings("unchecked")
//	            				List<TB_MEDAL_TYPE> list2 = (List<TB_MEDAL_TYPE>) q1.list();
//	            				if(list2.isEmpty())
//	            				{
//	            					
//	            					msg="Award/Medal Not Found ";
//	            					tx.rollback();
//	    			    			tranaction=1;
//	    			    			break;
//	            				}
//	            				else {
//	            					int census_id=getCensusid( (BigInteger)list6.get(0)[0]);
//	            					update_casuality++;
////	    		            		if(census_id==0)
////	    		            		{
////	    		            			msg="Census Details Not Found";
////	    		            			tx.rollback();
////	    		            			tranaction=1;
////	    		            			break;
////	    		            			}
////	    		            		else {
////	    		            			update_casuality++;
////	    		            		}
//	    		            		
//	    		            		Query q3 = sessionhql.createQuery(
//		            						"  from TB_CENSUS_AWARDSNMEDAL where   select_desc=:medal and comm_id=:comm_id ").setParameter("medal", String.valueOf(list2.get(0).getId())).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);
//		            				@SuppressWarnings("unchecked")
//		            				List<TB_CENSUS_AWARDSNMEDAL> list3 = (List<TB_CENSUS_AWARDSNMEDAL>) q3.list();
//	    		            		if(list3.isEmpty())
//	    		            		{
//	    		            			instances[k] = new TB_CENSUS_AWARDSNMEDAL();
//		            					instances[k].setCategory_8(list2.get(0).getMedal_type());
//		            					instances[k].setSelect_desc(String.valueOf(list2.get(0).getId()));
//		            					instances[k].setDate_of_award(format.parse(FmDate));
//		            					instances[k].setUnit(UnitName);
//		            					 if(orbatlist.size()>0) {
//			                                  
//			                                	instances[k].setBde(orbatlist.get(0).get(4));
//				            					instances[k].setDiv_subarea(orbatlist.get(0).get(3));
//				            					instances[k].setCorps_area(orbatlist.get(0).get(2));
//				            					instances[k].setCommand(orbatlist.get(0).get(1));
//			                                    }
//		            				
//		            					instances[k].setCensus_id(census_id);
//		            					instances[k].setComm_id( (BigInteger)list6.get(0)[0]);
//		            					instances[k].setAuthority(auth);
//		            					instances[k].setDate_of_authority(format.parse(authdate));
//		            					instances[k].setCreated_by(username);
//		            					instances[k].setCreated_on(new Date());
//
//			            				int id = (int) sessionhql.save(instances[k]);
//			            				if(id!=0)
//			            				{
//			            					msg ="Data Uploaded Successfully ";
//			            				}
//			            			
//	    		            		}
//	    		            		else{
//	    		            			String add_unit="";
//	    		            			if(orbatlist.size()>0){
//	    		            				
//	    		            				add_unit	+= "unit=:unit, bde=:bde,div_subarea=:div_subarea,corps_area=:corps_area,command=:command,";
//	    		            			}
//	    		            				    		            		
//			            				String hql = "update TB_CENSUS_AWARDSNMEDAL set modify_by=:modify_by ,modify_on=:modify_on ,category_8=:category_8, select_desc=:select_desc ,date_of_award=:date_of_award,"
//			            					+add_unit+" status=:status, authority=:authority,date_of_authority=:date_of_authority where  id=:id";
//			            				Query query = sessionhql.createQuery(hql).setString("category_8", list2.get(0).getMedal_type()).setInteger("status", 0)
//			            						.setString("select_desc", String.valueOf(list2.get(0).getId()))
//			            						.setTimestamp("date_of_award", format.parse(FmDate))			            						
//			            						.setInteger("id", list3.get(0).getId()).setString("modify_by", username)
//			            						.setTimestamp("modify_on", new Date())
//			            						.setTimestamp("date_of_authority", format.parse(authdate))
//			            						.setString("authority", auth);
//			            				if(orbatlist.size()>0) {
//			            					query.setString("unit", UnitName);
//			            					query.setString("bde", orbatlist.get(0).get(4));
//			            					query.setString("div_subarea",orbatlist.get(0).get(3));
//			            					query.setString("corps_area", orbatlist.get(0).get(2));
//			            					query.setString("command", orbatlist.get(0).get(1));
//			            					}
//			
//			            				msg = query.executeUpdate() > 0 ? "Data Uploaded Successfully  " : "0";
//	    		            		}
//	    		            		
//	            					
//	            					
//	            				}
//		            			
//} catch (RuntimeException e) {
//		            			try {
//		            				tx.rollback();
//		            				msg = "0";
//		            			} catch (RuntimeException rbe) {
//		            				msg = "0";
//		            			}
//		            		}
//
//		                }
//		                
//		                if(description.equals("POSIN"))
//		                {		   
//		                	casuality++;
//		                	Element data1Element = (Element) casualtyElement.getElementsByTagName("Data1").item(0);
//	                        String data1 = data1Element.getTextContent();		          
//	                  	  Element medelement = (Element) casualtiesList.item(j);			              
//			                String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//		                	 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
//		  	               if (roleSusNo == null || roleSusNo.equals("")) {	  	          
//		  	                       roleSusNo =(String) list1.get(0)[0];
//		  	               }
//		  	  
//		  	             if(list6.get(0)[3].equals(roleSusNo))
//			                {
//			                	msg="From And To Sus Can't Be Same ";
//			                	Upload_msg="Failure";
//		    					tx.rollback();
//		    					tranaction=1;
//		    					break;
//			                }
////		  	           String hql8 = "select sus_no,status_sus_no,unit_name from Miso_Orbat_Unt_Dtl where sus_no= :susno  and status_sus_no='Active' ";
////		    			Query query8 = sessionhql.createQuery(hql8);
////		    			query8.setParameter("susno",list1.get(0)[0]);
////		    			List <Object[]>list8 = query8.list();
////		    			if(list8.isEmpty())
////		    			{
////		    				msg="To SUS No not Found";
////		    				Upload_msg="Failure";
////		    				tx.rollback();
////		    		      	tranaction=1;
////		    		      	 break;
////		    			}
//		    			if(!check_future(fmDate))
//		    			{
//		    				msg = "Date of TOS  is Future Date";
//							tx.rollback();
//							tranaction=1;
//							break;
//		    			}
//		    			
//		                	TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
//		                	  ArrayList<ArrayList<String>> list = pod.GetCommDataApprove((BigInteger)list6.get(0)[0]);
//		               	  if( list.get(0).get(18).equals("1"))
//		                	  {
//		                		  if(rank.toUpperCase().equals( list6.get(0)[1]))
//		                		  {
//		                			     if (list.size() > 0)
//		                		            {
//		                		                    if(tos!=null && list.get(0).get(6)!=null) {
//		                		                            String regex = "^0+(?!$)";
//		                		                            String newTos=fmDate;
//		                		                            String preTos=list.get(0).get(6).toString().substring(0,10);
//		                		                          
//		                		                            String newTosArr[]=newTos.split("/");
//		                		                            String preTosArr[]=preTos.split("-");
//		                		                            int newTosM=Integer.parseInt(newTosArr[1].replaceAll(regex, ""));
//		                		                            int newTosY=Integer.parseInt(newTosArr[2]);
//		                		                            int preTosM=Integer.parseInt(preTosArr[1].replaceAll(regex, ""));
//		                		                            int preTosY=Integer.parseInt(preTosArr[0]);
//		                		                            
//		                		                            if(newTosY==preTosY){
//		                		                                    if(newTosM<=preTosM){
//		                		                                            msg= "Invalid Date of TOS";
//		                		                                            Upload_msg="Failure";
//		                		             		    				   tx.rollback();
//		                		             		    		      	   tranaction=1;
//		                		             		    		      	   break;
//		                		                                    }
//		                		                            }
//		                		                            else if(newTosY<preTosY){
//		                		                                    msg= "Invalid Date of TOS";
//		                		                                    Upload_msg="Failure";
//		                		     		    				   tx.rollback();
//		                		     		    		      	   tranaction=1;
//		                		     		    		      	   break;
//		                		                            }
//		                		                    }
//		                		                    po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(2))));
//		                		                    if (list.get(0).get(14) != null && !list.get(0).get(14).equals("null"))
//		                		                    {
//		                		                            po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(14))));
//		                		                    }
//		                		            }
//		                			     	                		            
//		                		                    Boolean v = pod.getpernoAlreadyExits((BigInteger) list6.get(0)[0]);
//		                		                    if (v == true)
//		                		                    {
//		                		                            msg = "Data already exists.";
//		                		                            Upload_msg="Failure";
//		                				    				   tx.rollback();
//		                				    		      	   tranaction=1;
//		                				    		      	   break;
//		                		                            
//		                		                    }
//		                		                    if (v == false) {
//		                		                          //  if (id1 == 0) {
//		                		                                    String roleAccess = session.getAttribute("roleAccess").toString();
//		                		                                    ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
//		                		                                    ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
//		                		                                    po.setCreated_by(username);
//		                		                                    po.setCreated_date(new Date());
//		                		                                    //po.setCensus_id(census_id);
//		                		                                    po.setComm_id((BigInteger) list6.get(0)[0]);
//		                		                                    if(orbatlist.size()>0) {
//		                		                                    po.setCmd_sus(orbatlist.get(0).get(1));
//		                		                                    po.setCorps_sus(orbatlist.get(0).get(2));
//		                		                                    po.setDiv_sus(orbatlist.get(0).get(3));
//		                		                                    po.setBde_sus(orbatlist.get(0).get(4));
//		                		                                    }
//		                		                                    if(Location_Trnlist.size()>0) {
//		                		                                    po.setLocation(Location_Trnlist.get(0).get(0));
//		                		                                    po.setTrn_type(Location_Trnlist.get(0).get(1));
//		                		                                    }
//		                		                                  
//		                		                                	Date date_of_converstion1 = format.parse(tos);
//		                		                            		Timestamp ts = new Timestamp(date_of_converstion1.getTime());		                		                                    
//		                		                            		Date auth_date1 = format.parse(authdate);
//		                		                            		Timestamp ts2 = new Timestamp(auth_date1.getTime());		                		                            		
//		                		                                    po.setFrom_sus_no((String)list6.get(0)[3]);
//		                		                                    po.setTo_sus_no(roleSusNo);
//		                		                                    po.setDt_of_sos(ts);
//		                		                                    po.setIn_auth(auth);
//		                		                                    po.setIn_auth_dt(ts2);
//		                		                                    po.setDt_of_tos(ts);
//		                		                                    po.setStatus(0);		                		                                   
//		                		                                    po.setT_status(0);
//		                		                                    //po.setNotification_status(0);
//		                		                                    //sessionhql.save(po);
//		                		                                   int id12 = (int) sessionhql.save(po);		                	
//		                		                                   if(id12!=0)
//		                		                                   {
//		                		                                	   msg="Data Uploaded Successfully";
//			                		                                    Upload_msg=" Success";
//		                		                                   }
//		                		                                   else {
//		                		                                	   msg="Data Not Uploaded ";
//			                		                                    Upload_msg="Failure";
//		                		                                   }
//		                		                          //  }
//		                		                    }	                			     
//		                		  }
//		                		  else {
//		                			  msg="Change in Rank ";
//			                		  Upload_msg="Failure";
//			                		  tx.rollback();
//			                      	   tranaction=1;
//			                      	   break;
//		                		  }		                		 
//		                	  }
//		                	  else {
//		                		  msg="Individual Record is still in Pending for Approval";
//		                		  Upload_msg="Failure";
//		                		  tx.rollback();
//		                      	   tranaction=1;
//		                      	   break;
//		                	  }		                	  
//		                }		 
//		                
//			              if(description.equals("BTHCHL"))
//			              {
//			            	  casuality++;
//			            	  update_casuality++;
//			            	  Element medelement = (Element) casualtiesList.item(j);			              
//				                String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//				                String data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//				                String data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   			                
//				                Date date = new Date();			               
//				               try {
//				            	   int census_id=getCensusid((BigInteger)list6.get(0)[0]);
////				           		if(census_id!=0)
////				           		{
//				            	   String hqlUpdate12 = " from TB_CENSUS_CHILDREN where   comm_id=:comm_id and name=:name and status!=0 order by id desc ";
//			           				Query query12 = sessionhql.createQuery(hqlUpdate12).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]).setString("name", data2);
//			           				List<TB_CENSUS_CHILDREN> list12 = (List<TB_CENSUS_CHILDREN>) query12.list();			
//			           				if(list12.isEmpty())
//			           				{
//
//				           			if(data3.equals("Daughter")||data3.equals("Son") )
//				           			{
//				           				String hqlUpdate3 = " from TB_CENSUS_CHILDREN where   comm_id=:comm_id and status=0 order by id desc ";
//				           				Query query4 = sessionhql.createQuery(hqlUpdate3).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);
//				           				List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query4.list();			           				
//				           				if(!list.isEmpty())
//				           				{			           							
////				           					String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,name=:name,date_of_birth=:date_of_birth " 
////				           											+ "where  id=:id";
////				           					Query query2 = sessionhql.createQuery(hql)
////				           													.setInteger("status", 0).setString("name", data2)
////				           													.setString("modified_by", username).setTimestamp("modified_date", date).setTimestamp("date_of_birth",format.parse(fmDate)).setInteger("id", list.get(0).getId());
////				           					msg = query2.executeUpdate() > 0 ? "updated successfully" : "0";		
//				           					
//				           					String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,name=:name, date_of_birth=:date_of_birth,"
//				           							+ "type=:type,adoted=:adoted,date_of_adpoted=:date_of_adpoted,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version'))" + 
//				           							",pan_no=pgp_sym_encrypt(:pan_no,current_setting('miso.version')),"
//				           							+ " relationship=:relationship,child_service=:child_ser,child_personal_no=:child_per_no,if_child_ser=:child_ex,other_child_ser=:other_child_ser where  id=:id";
//
//				           					Query query2 = sessionhql.createQuery(hql).setInteger("status", 0)
//
//				           							.setTimestamp("date_of_birth", format.parse(fmDate)).setString("name", data2)
//
//				           							.setString("type", "").setTimestamp("date_of_adpoted", null)
//
//				           							.setString("adoted", "")
//
//				           							.setString("aadhar_no", "").setString("pan_no", "")
//
//				           							.setInteger("id", list.get(0).getId()).setString("modified_by", username)
//
//				           							.setTimestamp("modified_date", date).setInteger("child_ser",0).setString("child_per_no","")
//				           					
//				           							.setString("child_ex","").setString("other_child_ser","");
//				           					if (data3.equals("Daughter")) {
//				           						query2	.setInteger("relationship", 1);
//				           					
//				           					}
//				           					if (data3.equals("Son")) {
//				           						query2	.setInteger("relationship", 2);
//				           					}			
//				           					msg = query2.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";		
//				           				}
//				           				else {
//				           					TB_CENSUS_CHILDREN chil = new TB_CENSUS_CHILDREN();
//				           					chil.setName(data2);
//				           					chil.setDate_of_birth(format.parse(fmDate));
//				           					chil.setType("");
//				           					if (data3.equals("Daughter")) {
//				           						chil.setRelationship(1);
//				           					}
//				           					if (data3.equals("Son")) {
//				           						chil.setRelationship(2);
//				           					}			           			
//				           					chil.setAdoted("");
//				           					// chil.setDate_of_adpoted("");
//				           					chil.setAadhar_no("");
//				           					chil.setPan_no("");
//				           					// chil.setChild_service();
//				           					chil.setChild_personal_no("");
//				           					chil.setIf_child_ser("");
//				           					chil.setOther_child_ser("");
//				           					chil.setCen_id(census_id);
//				           					chil.setComm_id((BigInteger)list6.get(0)[0]);
//				           					chil.setCreated_by(username);
//				           					chil.setCreated_date(date);
//				           					chil.setStatus(0);
//				           					int id = (int) sessionhql.save(chil);
//				           					child_entity_id=id;
//				           				  msg="Data Uploaded Successfully";
//		                                    Upload_msg=" Success";
//				           				}
//				           				
//				           			}
//				           			else {
//				           				msg="Relation Not Found";
//				           			  Upload_msg="Failure";
//					           			tx.rollback();
//					           			tranaction=1;
//					           			break;
//				           			}
//				           			
//				               }
//			           				else {
//			           					msg="Childeren Details Already Exist";
//					           			  Upload_msg="Failure";
//						           			tx.rollback();
//						           			tranaction=1;
//						           			break;
//			           				}
//				           			
//				           			
//				           			
//				           			
////				           		}
////				           		else {
////				           			msg="Census Not Filled";
////				           			tx.rollback();
////				           			tranaction=1;
////				           			break;
////				           		}
//				           		} catch (RuntimeException e) {
//				           			try {
//				           				tx.rollback();
//				           				msg = "0";
//				           			} catch (RuntimeException rbe) {
//				           				msg = "0";
//				           			}
//				           		}
//				               
//			              }		          
//			              
//			                if(description.equals("DOBCHNGF")) {		
//			                	casuality++;
//			                	update_casuality++;
//			              	  Element medelement = (Element) casualtiesList.item(j);			              
//				                String fmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//				                String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();			           
//				                String data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//				                String data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//				                String data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();   			                
//				               Date date = new Date();			               
//				               try {
//				            	   int census_id=getCensusid((BigInteger)list6.get(0)[0]);
//				           		if(census_id!=0)
//				           		{
//				           			if(data3.equals("Daughter")||data3.equals("Son") )
//				           			{
//				           				String hqlUpdate3 = " from TB_CENSUS_CHILDREN where  comm_id=:comm_id and name=:name order by id desc ";
//
//				           				Query query4 = sessionhql.createQuery(hqlUpdate3).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]).setParameter("name", data2);
//
//				           				List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query4.list();
//				           				
//				           				if(!list.isEmpty())
//				           				{
//				           							
//				           					String hql = "update TB_CENSUS_CHILDREN set modified_by=:modified_by ,modified_date=:modified_date ,date_of_birth=:date_of_birth,status=:status " 
//				           							+ "where  id=:id";
//
//				           					Query query2 = sessionhql.createQuery(hql).setParameter("status", 0)
//				           							.setString("modified_by", username).setTimestamp("modified_date", date).setTimestamp("date_of_birth",format.parse(fmDate)).setInteger("id", list.get(0).getId());
//				           					msg = query2.executeUpdate() > 0 ? "Data Uploaded Successfully" : "0";					           				
//				           				}
//				           				else {
//				           					msg="Details of Child  Not Filled ";
//					    					tx.rollback();			    		
//					    					tranaction=1;
//					    					break;
//				           				}
//				           			}
//				           			         			
//				           			else {
//				           				msg="Description not found";
//				    					tx.rollback();			    		
//				    					tranaction=1;
//				    					break;
//				           			}
//				           			
//				           		}
//				           		else {
//				           			msg="Census Not Filled";
//				           			tx.rollback();
//				           			tranaction=1;
//				           			break;
//				           		}
//				           		} catch (RuntimeException e) {
//				           			try {
//				           				tx.rollback();
//				           				msg = "0";
//				           			} catch (RuntimeException rbe) {
//				           				msg = "0";
//				           			}
//				           		}
//
//			                }     
//			              
//			              
//		                if (description.equals("MEDCAT")) {
//		                	casuality++;
//		                    Element medelement = (Element) casualtiesList.item(j);		          		        
//		                    String FmDate = medelement.getElementsByTagName("FmDate").item(0).getTextContent();
//		                    String ToDate = medelement.getElementsByTagName("ToDate").item(0).getTextContent();
//		                    String Data1 = medelement.getElementsByTagName("Data1").item(0).getTextContent();
//		                    String Data2 = medelement.getElementsByTagName("Data2").item(0).getTextContent();
//		                    String Data3 = medelement.getElementsByTagName("Data3").item(0).getTextContent();
//		                    String Data4 = medelement.getElementsByTagName("Data4").item(0).getTextContent();
//		                    if(!Data3.isEmpty())
//		                    {
//		                    	Data3 = Data3.toUpperCase();
//		                    }
//		                   
//		                	String comm_date=(String) list6.get(0)[2];
//		                	if(format.parse(authdate).compareTo(format.parse(comm_date))<0)
//		                	{
//		                		msg="Authority Date should be Greater than Commission Date ";		
//		                		Upload_msg="Failure";
//								tx.rollback();
//		            			tranaction=1;
//		            			break;
//		                	}
//		                	int census_id=getCensusid((BigInteger) list6.get(0)[0]);
//		                	
//		                	if(Data1==""&&Data4=="")
//		                	{
//		                		msg="Shape and Cope value not found ";		
//		                		Upload_msg="Failure";
//								tx.rollback();
//		            			tranaction=1;
//		            			break;
//		                	}
//		                	
//							int next_Shape_s = Data1.indexOf('S');
//							int next_Shape_h = Data1.indexOf('H');
//							int next_Shape_a = Data1.indexOf('A');
//							int next_Shape_p = Data1.indexOf('P');
//							int next_Shape_e = Data1.indexOf('E');
//							int next_Cope_c = Data4.indexOf('C');
//							int next_Cope_o = Data4.indexOf('O');
//							int next_Cope_p = Data4.indexOf('P');
//							int next_Cope_e = Data4.indexOf('E');
//		                	
//		                	 if (next_Shape_s != -1 &&next_Shape_h != -1&&next_Shape_a != -1&&next_Shape_p != -1&&next_Shape_e!=-1&& next_Shape_h + 1 < Data1.length()) {
//		                	//	 Character r=Data1.charAt(next_s+1);
//		                		 
//							char shape_status_s = Data1.charAt(next_Shape_s + 1);
//							char shape_status_h = Data1.charAt(next_Shape_h + 1);
//							char shape_status_a = Data1.charAt(next_Shape_a + 1);
//							char shape_status_p = Data1.charAt(next_Shape_p + 1);
//							char shape_status_e = Data1.charAt(next_Shape_e + 1);
//							char cope_value_c = Data4.charAt(next_Cope_c + 1);
//							char cope_value_o = Data4.charAt(next_Cope_o + 1);
//							char cope_value_p = Data4.charAt(next_Cope_p + 1);
//							
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_s = new TB_CENSUS_MEDICAL_CATEGORY();		               
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_h= new TB_CENSUS_MEDICAL_CATEGORY();
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_a = new TB_CENSUS_MEDICAL_CATEGORY();
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_p = new TB_CENSUS_MEDICAL_CATEGORY();
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_e = new TB_CENSUS_MEDICAL_CATEGORY();
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_c_c = new TB_CENSUS_MEDICAL_CATEGORY();		               
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_c_o = new TB_CENSUS_MEDICAL_CATEGORY();		               
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_c_p = new TB_CENSUS_MEDICAL_CATEGORY();		               			                		
//		                		 TB_CENSUS_MEDICAL_CATEGORY Mad_cat_c_e = new TB_CENSUS_MEDICAL_CATEGORY();		               
//			                		
//		                		 String diagnosis = check_diagnosis(Data2);
//		                		 
//		                		 Query q01 = sessionhql.createQuery(
//		     							"select count(id) from TB_CENSUS_MEDICAL_CATEGORY where comm_id=:comm_id and status=0")
//		     					         .setParameter("comm_id", (BigInteger)list6.get(0)[0]);
//		     					Long c = (Long) q01.uniqueResult();
//		     					if (c > 0) {
//		     						msg= "Medical data already exists, please approve it in the pending list";
//		     						Upload_msg="Failure";
//		     						tx.rollback();
//			            			tranaction=1;
//			            			break;
//		     					}
//		     				int status=0;
//		     					
//		     					if(census_id!=0)
//			                	{
//			                		update_casuality++;
//			                	}
////		     					else {
////		     						
////		     						
////		     						 String hql12 = "update TB_MEDICAL_CATEGORY_HISTORY set status=:status where  comm_id=:comm_id and status=1";
////		     						   
////			     						Query query12 = sessionhql.createQuery(hql12).setInteger("status", 2)
////			     								.setBigInteger("comm_id",(BigInteger)list6.get(0)[0]);
////			     								
////			     						msg = query12.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
////		     						
////		     						String hql11 = "update TB_CENSUS_MEDICAL_CATEGORY set status=:status where comm_id=:comm_id and (status != '0' and status != '-1') ";
////		     					   
////		     						Query query11 = sessionhql.createQuery(hql11).setInteger("status", 2)
////		     								.setBigInteger("comm_id",(BigInteger)list6.get(0)[0]);
////		     								
////		     						msg = query11.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
////
////		     						status=0;
////		     					}
//		     					
//		     					
//		                		 //////SHAPE_S
//		                		 if(Character.getNumericValue(shape_status_s)==1)
//		                   		 {
//		                   			Mad_cat_s.setShape_value("1");
//		                   			Mad_cat_s.setShape_status(1);			
//		                   		 }
//		                		 else
//		                   		 {
//		                			 if(Character.getNumericValue(shape_status_s)==3||Character.getNumericValue(shape_status_s)==2)
//		                			 {
//		                		      		
//		                		      	
//		                		      	String value=getmedicaltype(Data1,next_Shape_s+1);
//		                		      	
//		                		      		if(!diagnosis.equals("False"))
//		                		      		{
//		                		      			
//		                		      			if(value.contains("T"))
//		                		      			{
//		                		      				Mad_cat_s.setShape_status(3);			
//		                		      				Mad_cat_s.setDiagnosis(diagnosis);
//		                		      				Mad_cat_s.setShape_value(getmedicaltype(Data1,next_Shape_s+1));
//		                		      				Mad_cat_s.setShape_value(value);
//		                		      			}
//		                		      			else {
//		                		      				Mad_cat_s.setShape_status(2);		
//		                		      				String targetSubstring = value.substring(value.indexOf('[')+1, value.indexOf(']'));
//		                		      				Mad_cat_s.setDiagnosis(diagnosis);
//		                		      				Mad_cat_s.setShape_value(targetSubstring);
//		                		      			}		
//		                		      			 if(!ToDate.isEmpty())
//		             		                    {
//		             		                	if (format.parse(ToDate).compareTo(format.parse(FmDate)) < 0) {
//		             								msg="To Date Should Be Greater Than Or Equal To From Date In Row ";		
//		             								Upload_msg="Failure";
//		             								tx.rollback();
//		             		            			tranaction=1;
//		             		            			break;
//		             							}
//		             		                	else {
//		             		                		Mad_cat_s.setTo_date(format.parse(ToDate));
//		             		                		Mad_cat_s.setFrom_date(format.parse(FmDate));
//		             		                	}
//		             		                    }
//		                		      			 else{
//		                		      				msg="To Date Not Found ";		
//		             								Upload_msg="Failure";
//		             								tx.rollback();
//		             		            			tranaction=1;
//		             		            			break;
//		                		      			 }		                		     
//		                		      		}
//		                		      		else {	                		      			
//		                		      			msg="Diagnosis not found";
//		                		      			Upload_msg="Failure";
//		        								tx.rollback();
//		        		            			tranaction=1;
//		        		            			break;
//		                		      		}		                		      		
//		                			 }		             		                   			
//		                   		 }		                		 		                		 
//		                		 	Mad_cat_s.setShape("S");								                   					
//		                   			Mad_cat_s.setAuthority(auth);
//		                   			Mad_cat_s.setDate_of_authority(format.parse(authdate));
//									Mad_cat_s.setClasification("NIL");
//									Mad_cat_s.setCen_id(census_id);
//									Mad_cat_s.setComm_id((BigInteger)list6.get(0)[0]);
//									Mad_cat_s.setCreated_by(username);
//									Mad_cat_s.setCreated_on(new Date());
//									Mad_cat_s.setStatus(status);
//									sessionhql.save(Mad_cat_s);
//									
//									///////////Shape_H
//		                		 if(Character.getNumericValue(shape_status_h)==1)
//		                   		 {										
//										Mad_cat_h.setShape_value("1");
//										Mad_cat_h.setShape_status(1);												                  
//		                   		 }
//		                		 else {
//		                			 if(Character.getNumericValue(shape_status_h)==3||Character.getNumericValue(shape_status_h)==2)
//		                			 {
//		                		      		
//		                		      	
//		                		      		
//		                		      		String value=getmedicaltype(Data1,next_Shape_h+1);
//		                		      	
//		                		      		if(!diagnosis.equals("False"))
//		                		      		{		                		      			
//		                		      			if(value.contains("T"))
//		                		      			{
//		                		      				Mad_cat_h.setShape_status(3);			
//		                		      				Mad_cat_h.setDiagnosis(diagnosis);
//		                		      				Mad_cat_h.setShape_value(value);
//		                		      			}
//		                		      			else {
//		                		      				Mad_cat_h.setShape_status(2);		
//		                		      				String targetSubstring = value.substring(value.indexOf('[')+1, value.indexOf(']'));
//		                		      				Mad_cat_h.setDiagnosis(diagnosis);
//		                		      				Mad_cat_h.setShape_value(targetSubstring);
//		                		      			}		
//		                		      			 if(!ToDate.isEmpty())
//		             		                    {
//		             		                	if (format.parse(ToDate).compareTo(format.parse(FmDate)) < 0) {
//		             								msg="To Date Should Be Greater Than Or Equal To From Date In Row ";		
//		             								Upload_msg="Failure";
//		             								tx.rollback();
//		             		            			tranaction=1;
//		             		            			break;
//		             							}
//		             		                	else {
//		             		                		Mad_cat_h.setTo_date(format.parse(ToDate));
//		             		                		Mad_cat_h.setFrom_date(format.parse(FmDate));
//		             		                	}
//		             		                    }
//		                		      			 else{
//		                		      				msg="To Date Not Found ";		
//		             								Upload_msg="Failure";
//		             								tx.rollback();
//		             		            			tranaction=1;
//		             		            			break;
//		                		      			 }		                		     
//		                		      		}
//		                		      		else {	                		      			
//		                		      			msg="Diagnosis not found";
//		                		      			Upload_msg="Failure";
//		        								tx.rollback();
//		        		            			tranaction=1;
//		        		            			break;
//		                		      		}		                		      		
//		                			 }		             		                   			
//		                		 }
//		                		 	Mad_cat_h.setShape("H");
//									Mad_cat_h.setAuthority(auth);
//									Mad_cat_h.setDate_of_authority(format.parse(authdate));
//									Mad_cat_h.setClasification("NIL");
//									Mad_cat_h.setCen_id(census_id);
//									Mad_cat_h.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_h.setCreated_by(username);
//									Mad_cat_h.setCreated_on(new Date());
//									Mad_cat_h.setStatus(status);
//									sessionhql.save(Mad_cat_h);
//		                		 	
//                		 
//		                		 //////Shape_A		                		 
//									if (Character.getNumericValue(shape_status_a) == 1) {
//										Mad_cat_a.setShape_value("1");
//										Mad_cat_a.setShape_status(1);
//									} else {
//										if (Character.getNumericValue(shape_status_a) == 3
//												|| Character.getNumericValue(shape_status_a) == 2) {
//											
//								
//											String value=getmedicaltype(Data1, next_Shape_a + 1);
//										
//											if (!diagnosis.equals("False")) {
//												if (value.contains("T")) {
//													Mad_cat_a.setShape_status(3);
//													Mad_cat_a.setDiagnosis(diagnosis);
//													Mad_cat_a.setShape_value(value);
//												} else {
//													Mad_cat_a.setShape_status(2);
//													String targetSubstring = value.substring(
//															value.indexOf('[') + 1, value.indexOf(']'));
//													Mad_cat_a.setDiagnosis(diagnosis);
//													Mad_cat_a.setShape_value(targetSubstring);
//												}
//												if (!ToDate.isEmpty()) {
//													if (format.parse(ToDate).compareTo(format.parse(FmDate)) < 0) {
//														msg = "To Date Should Be Greater Than Or Equal To From Date In Row ";
//														Upload_msg = "Failure";
//														tx.rollback();
//														tranaction = 1;
//														break;
//													} else {
//														Mad_cat_a.setTo_date(format.parse(ToDate));
//														Mad_cat_a.setFrom_date(format.parse(FmDate));
//													}
//												} else {
//													msg = "To Date Not Found ";
//													Upload_msg = "Failure";
//													tx.rollback();
//													tranaction = 1;
//													break;
//												}
//											} else {
//												msg = "Diagnosis not found";
//												Upload_msg = "Failure";
//												tx.rollback();
//												tranaction = 1;
//												break;
//											}
//										}
//									}
//									Mad_cat_a.setShape("A");
//									Mad_cat_a.setAuthority(auth);
//									Mad_cat_a.setDate_of_authority(format.parse(authdate));
//									Mad_cat_a.setClasification("NIL");
//									Mad_cat_a.setCen_id(census_id);
//									Mad_cat_a.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_a.setCreated_by(username);
//									Mad_cat_a.setCreated_on(new Date());
//									Mad_cat_a.setStatus(status);
//									sessionhql.save(Mad_cat_a);
//		                		 
//		                		 //////////////Shape_P
//		                		 
//									if (Character.getNumericValue(shape_status_p) == 1) {
//										Mad_cat_p.setShape_value("1");
//										Mad_cat_p.setShape_status(1);
//									} else {
//										if (Character.getNumericValue(shape_status_p) == 3
//												|| Character.getNumericValue(shape_status_p) == 2) {
//											String value=getmedicaltype(Data1, next_Shape_p + 1);										
//											if (!diagnosis.equals("False")) {
//
//												if (value.contains("T")) {
//													Mad_cat_p.setShape_status(3);
//													Mad_cat_p.setDiagnosis(diagnosis);
//													Mad_cat_p.setShape_value(value);
//												} else {
//													Mad_cat_p.setShape_status(2);
//													String targetSubstring = value.substring(
//															value.indexOf('[') + 1, value.indexOf(']'));
//													Mad_cat_p.setDiagnosis(diagnosis);
//													Mad_cat_p.setShape_value(targetSubstring);
//												}
//												if (!ToDate.isEmpty()) {
//													if (format.parse(ToDate).compareTo(format.parse(FmDate)) < 0) {
//														msg = "To Date Should Be Greater Than Or Equal To From Date In Row ";
//														Upload_msg = "Failure";
//														tx.rollback();
//														tranaction = 1;
//														break;
//													} else {
//														Mad_cat_p.setTo_date(format.parse(ToDate));
//														Mad_cat_p.setFrom_date(format.parse(FmDate));
//													}
//												} else {
//													msg = "To Date Not Found ";
//													Upload_msg = "Failure";
//													tx.rollback();
//													tranaction = 1;
//													break;
//												}
//											} else {
//												msg = "Diagnosis not found";
//												Upload_msg = "Failure";
//												tx.rollback();
//												tranaction = 1;
//												break;
//											}
//										}
//									}
//									Mad_cat_p.setShape("P");
//									Mad_cat_p.setAuthority(auth);
//									Mad_cat_p.setDate_of_authority(format.parse(authdate));
//									Mad_cat_p.setClasification("NIL");
//									Mad_cat_p.setCen_id(census_id);
//									Mad_cat_p.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_p.setCreated_by(username);
//									Mad_cat_p.setCreated_on(new Date());
//									Mad_cat_p.setStatus(status);
//									sessionhql.save(Mad_cat_p);
//
//									////////Shape_E
//									if (Character.getNumericValue(shape_status_e) == 1) {
//										Mad_cat_e.setShape_value("1");
//										Mad_cat_e.setShape_status(1);
//
//									} else {
//										if (Character.getNumericValue(shape_status_e) == 3
//												|| Character.getNumericValue(shape_status_e) == 2) {
//										
//
//											String value=getmedicaltype(Data1, next_Shape_e + 1);
//											if (!diagnosis.equals("False")) {
//
//												if (value.contains("T")) {
//													Mad_cat_e.setShape_status(3);
//													Mad_cat_e.setDiagnosis(diagnosis);
//													Mad_cat_e.setShape_value(value);
//												} else {
//													Mad_cat_e.setShape_status(2);
//													String targetSubstring = value.substring(
//															value.indexOf('[') + 1, value.indexOf(']'));
//													Mad_cat_e.setDiagnosis(diagnosis);
//													Mad_cat_e.setShape_value(targetSubstring);
//												}
//												if (!ToDate.isEmpty()) {
//													if (format.parse(ToDate).compareTo(format.parse(FmDate)) < 0) {
//														msg = "To Date Should Be Greater Than Or Equal To From Date In Row ";
//														Upload_msg = "Failure";
//														tx.rollback();
//														tranaction = 1;
//														break;
//													} else {
//														Mad_cat_e.setTo_date(format.parse(ToDate));
//														Mad_cat_e.setFrom_date(format.parse(FmDate));
//													}
//												} else {
//													msg = "To Date Not Found ";
//													Upload_msg = "Failure";
//													tx.rollback();
//													tranaction = 1;
//													break;
//												}
//											} else {
//												msg = "Diagnosis not found";
//												Upload_msg = "Failure";
//												tx.rollback();
//												tranaction = 1;
//												break;
//											}
//										}
//									}
//
//									Mad_cat_e.setShape("E");
//									Mad_cat_e.setAuthority(auth);
//									Mad_cat_e.setDate_of_authority(format.parse(authdate));
//									Mad_cat_e.setClasification("NIL");
//									Mad_cat_e.setCen_id(census_id);
//									Mad_cat_e.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_e.setCreated_by(username);
//									Mad_cat_e.setCreated_on(new Date());
//									Mad_cat_e.setStatus(status);
//									sessionhql.save(Mad_cat_e);
//		                		 
//		                		 
//
//		                		 ////C_C
//									if (cope_value_c == 'O') {
//										Mad_cat_c_c.setShape_value("0");
//									} else if (cope_value_c == '1') {
//										Mad_cat_c_c.setShape_value("1");
//									} else {
//										Mad_cat_c_c.setShape_value("2[a]");
//									}
//									Mad_cat_c_c.setShape("C_C");
//									Mad_cat_c_c.setShape_status(0);
//									Mad_cat_c_c.setAuthority(auth);
//									Mad_cat_c_c.setDate_of_authority(format.parse(authdate));
//									Mad_cat_c_c.setClasification("NIL");
//									Mad_cat_c_c.setCen_id(census_id);
//									Mad_cat_c_c.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_c_c.setCreated_by(username);
//									Mad_cat_c_c.setCreated_on(new Date());
//									Mad_cat_c_c.setStatus(status);
//									sessionhql.save(Mad_cat_c_c);
//
//									///// C_O
//									if (cope_value_o == 'P') {
//										Mad_cat_c_o.setShape_value("0");
//									} else if (cope_value_o == '1') {
//										Mad_cat_c_o.setShape_value("1");
//									} else {
//										Mad_cat_c_o.setShape_value("2[a]");
//									}
//									Mad_cat_c_o.setShape("C_O");
//									Mad_cat_c_o.setShape_status(0);
//									Mad_cat_c_o.setAuthority(auth);
//									Mad_cat_c_o.setDate_of_authority(format.parse(authdate));
//									Mad_cat_c_o.setClasification("NIL");
//									Mad_cat_c_o.setCen_id(census_id);
//									Mad_cat_c_o.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_c_o.setCreated_by(username);
//									Mad_cat_c_o.setCreated_on(new Date());
//									Mad_cat_c_o.setStatus(status);
//									sessionhql.save(Mad_cat_c_o);
//
//		                		 ////C_P
//
//									if (cope_value_p == 'E') {
//										Mad_cat_c_p.setShape_value("0");
//									} else if (cope_value_p == '1') {
//										Mad_cat_c_p.setShape_value("1");
//									} else {
//										Mad_cat_c_p.setShape_value("2[a]");
//
//									}
//
//									Mad_cat_c_p.setShape("C_P");
//									Mad_cat_c_p.setShape_status(0);
//									Mad_cat_c_p.setAuthority(auth);
//									Mad_cat_c_p.setDate_of_authority(format.parse(authdate));
//									Mad_cat_c_p.setClasification("NIL");
//									Mad_cat_c_p.setCen_id(census_id);
//									Mad_cat_c_p.setComm_id((BigInteger) list6.get(0)[0]);
//									Mad_cat_c_p.setCreated_by(username);
//									Mad_cat_c_p.setCreated_on(new Date());
//									Mad_cat_c_p.setStatus(status);
//									sessionhql.save(Mad_cat_c_p);
//
//		                		////////C_E
//								if (next_Cope_e + 1 == Data4.length()) {
//									Mad_cat_c_e.setShape_value("0");
//								} else {
//									char cope_value_e = Data4.charAt(next_Cope_e + 1);
//									if (cope_value_e == '1') {
//										Mad_cat_c_e.setShape_value("1");
//									} else {
//										Mad_cat_c_e.setShape_value("2");
//									}
//								}
//								Mad_cat_c_e.setShape("C_E");
//								Mad_cat_c_e.setShape_status(0);
//								Mad_cat_c_e.setAuthority(auth);
//								Mad_cat_c_e.setDate_of_authority(format.parse(authdate));
//								Mad_cat_c_e.setClasification("NIL");
//								Mad_cat_c_e.setCen_id(census_id);
//								Mad_cat_c_e.setComm_id((BigInteger) list6.get(0)[0]);
//								Mad_cat_c_e.setCreated_by(username);
//								Mad_cat_c_e.setCreated_on(new Date());
//								Mad_cat_c_e.setStatus(status);
//								sessionhql.save(Mad_cat_c_e);
//								
//								if(census_id==0)
//								{
//									
//		     						String sShape = "S ";
//
//		     						String hShape = "H ";
//
//		     						String aShape = "A ";
//
//		     						String pShape = "P ";
//
//		     						String eShape = "E ";
//
//		     						String cCope = "C ";
//
//		     						String oCope = "O ";
//
//		     						String pCope = "P ";
//
//		     						String eCope = "E ";
//
//		     						int lmc = 0;
//		     						String hqlUpdate = " from TB_CENSUS_MEDICAL_CATEGORY where  comm_id=:comm_id and status='1'";
//		     						Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", (BigInteger)list6.get(0)[0]);
//		     						@SuppressWarnings("unchecked")
//		     						List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = (List<TB_CENSUS_MEDICAL_CATEGORY>) query.list();
//		     					if(MedDetails.size()>0)
//		     					{
//		     						Date med_auth_date = MedDetails.get(0).getDate_of_authority();
//
//		     						for (int i = 0; i < MedDetails.size(); i++) {
//
//		     							int newLmc = MedDetails.get(i).getShape_status();
//
//		     							if (newLmc > lmc) {
//
//		     								lmc = newLmc;
//
//		     							}
//
//		     							String shape = MedDetails.get(i).getShape();
//
//		     							if (shape.equals("S")) {
//
//		     								if (!sShape.equals("S "))
//
//		     									sShape += ",";
//
//		     								sShape += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("H")) {
//
//		     								if (!hShape.equals("H "))
//
//		     									hShape += ",";
//
//		     								hShape += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("A")) {
//
//		     								if (!aShape.equals("A "))
//
//		     									aShape += ",";
//
//		     								aShape += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("P")) {
//
//		     								if (!pShape.equals("P "))
//
//		     									pShape += ",";
//
//		     								pShape += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("E")) {
//
//		     								if (!eShape.equals("E "))
//
//		     									eShape += ",";
//
//		     								eShape += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("C_C")) {
//
//		     								if (!cCope.equals("C "))
//
//		     									cCope += ",";
//
//		     								cCope += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("C_O")) {
//
//		     								if (!oCope.equals("O "))
//
//		     									oCope += ",";
//
//		     								oCope += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("C_P")) {
//
//		     								if (!pCope.equals("P "))
//
//		     									pCope += ",";
//
//		     								pCope += MedDetails.get(i).getShape_value();
//
//		     							} else if (shape.equals("C_E")) {
//
//		     								if (!eCope.equals("E "))
//
//		     									eCope += ",";
//
//		     								eCope += MedDetails.get(i).getShape_value();
//
//		     							}
//
//		     						}
//
//		     						String Fshape = sShape + ";" + hShape + ";" + aShape + ";" + pShape + ";" + eShape;
//
//		     						String Fcope = cCope + ";" + oCope + ";" + pCope + ";" + eCope;
//
//		     						TB_MEDICAL_CATEGORY_HISTORY Mobj = new TB_MEDICAL_CATEGORY_HISTORY();
//
//		     						Mobj.setComm_id((BigInteger)list6.get(0)[0]);
//
//		     						Mobj.setShape(Fshape);
//
//		     						Mobj.setCope(Fcope);
//
//		     						Mobj.setStatus(1);
//
//		     						Mobj.setDate_of_authority(med_auth_date);
//
//		     						if (lmc == 1) {
//
//		     							Mobj.setMed_classification_lmc("FIT");
//
//		     						} else if (lmc == 2) {
//
//		     							Mobj.setMed_classification_lmc("PERMANENT");
//
//		     						} else if (lmc == 3) {
//
//		     							Mobj.setMed_classification_lmc("TEMPORARY");
//
//		     						}
//		     						sessionhql.save(Mobj);
//		     						 msg = "Data Approve Successfully.";
//								}
//								}
//								
//								
//								
//									msg="Data Uploaded  Successfully";
//									Upload_msg="Success";
//		                		 
//		                	 }		                	
//		                }
//		                if(casuality==0) {		               
//		                	Desccription_not_found+=" "+description;	
//		                }
//	           }
//	          // }				
//			}
//			} 			 
//		}
//   }//else for sus_no! null
//	            }///for loop
//	            if (tranaction!=1) {
//	            	count++;
//	            	String s=update_cda_Account(CDAACNo,comm_id_1,cen_id,username);
//	            
//	            	tx.commit(); 	    	            	
////	            	if(cen_id!=0&&update_casuality!=0)
////	            	{
////	            		com.update_offr_status(cen_id,username);
////	            		
////	            	}
//	            	if(update_casuality!=0)
//	            	{	            		
//	            		com.update_comm_status(comm_id_1, username);
//	            	}
//	            	status_xml=0;
//	            } 
//	            else {
//	            	failure++;
//	            }
//	
//	            Session sessionhql2 = HibernateUtil.getSessionFactory().openSession();
//	    		Transaction tx2 = sessionhql2.beginTransaction();
//	           XML_FILE_UPLOAD xmlFile = new XML_FILE_UPLOAD();
//	           if(!Desccription_not_found.isEmpty())
//	           {
//	        	   Upload_msg= Desccription_not_found +" Caualities  Are Not Handled";
//	           }
//	           xmlFile.setFileName(fileName);
//               xmlFile.setArmyNo(personnel_no);
//               xmlFile.setErrorMsg(msg);
//               xmlFile.setName(name);
//               xmlFile.setFileData(fileData);
//               xmlFile.setUploadedStatus(Upload_msg);
//               xmlFile.setUploadedOn(new Timestamp(System.currentTimeMillis()));         
//               xmlFile.setStatus(status_xml);
//               xmlFile.setComm_id(comm_id_xml);
//               xmlFile.setPresent_p2_no(present_p2_no);
//              Serializable identifier = sessionhql2.save(xmlFile);
//              String savedId = String.valueOf(identifier);
//
//              if (savedIdsStringBuilder.length() > 0) {
//                  savedIdsStringBuilder.append(",");
//              }
//              savedIdsStringBuilder.append(savedId);
//              Long a = (Long) identifier;      
//	           tx2.commit();
//	           
//	           tempFile.delete();
//		} catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//			mp.put("msg", "Error processing the file.");			
//	}
//		 finally {
//				if (sessionhql != null) {
//					sessionhql.close();
//				}
//			}	
//	}
//		   String savedIdsString = savedIdsStringBuilder.toString();
//
//		
//		
//	   msg="";
//	     if(count!=0)
//	     {
//	    	msg+= count+" Files Uploaded  Successfully \n";
//	     }
//	     if (failure!=0)
//	     {
//	    	 msg+=failure+ " Files Not Uploaded \n";	    	
//	     }
//	     if(count_already!=0) {
//	    	 msg+=count_already+ " Files Already Exist \n";	    	
//		    }
//	     if(savedIdsString!="0")
//	     {
//	    	 msg+= " Change Status To View The Failure File";
//	     }
// 
//
// result.add(savedIdsString);
// 
//		   }
//	
//
//	   catch(Exception e){
//		   mp.put("msg", "Error processing the file.");   
//		   e.printStackTrace();
//	   }
//	//return new ModelAndView("xml_reader_Tiles");
//		   result.add(msg);
//		   
//		   return result;
//			//return new ModelAndView("xml_upload_logs_Tiles");
//	}
	
	private String check_diagnosis(String data2) {
		String diagnosis= "False";
		if(!data2.isEmpty())
		{
			String[] splitStrings = data2.split("\\|");
			String icd_code = splitStrings[0].trim();
			String icd_description = splitStrings[1].trim();
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Query q01 = sessionhql.createQuery(
					"select  icd_code from Tb_Med_Disease_Cause where icd_description=:icd_description or icd_code=:icd_code")
			         .setParameter("icd_description", icd_description).setParameter("icd_code", icd_code);
		List<String> li=(List<String>)q01.list();
		if(!li.isEmpty())
		{
			diagnosis=li.get(0);
		}
		}
		return diagnosis;
	}

	public String  getmedicaltype(String value,int startIndex)
	{
	        int endIndex = value.indexOf(')', startIndex);
	        String targetSubstring = value.substring(startIndex, endIndex + 1);
	        targetSubstring= targetSubstring.replace("(", "[");
	        targetSubstring = targetSubstring.replace(")", "]").replace("-", "");
	     
	        return targetSubstring;
	}
	
	private String  update_cda_Account(String cda_acc_no, BigInteger comm_id, int cen_id,String username) {	
		Session sessionhql2 = HibernateUtil.getSessionFactory().openSession();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Transaction tx2 = sessionhql2.beginTransaction();
		int final_mId = 0;
		String msg = "";
		if (cda_acc_no.length() > 20) {
			return "CDA Account No Should Contain Maximum 20 Character";
		}
		
		try {
				Query q0 = sessionhql2.createQuery(
						"select count(id) from TB_CDA_ACC_NO where comm_id=:comm_id and cda_acc_no=:cda_acc_no");
				q0.setParameter("comm_id", comm_id);
				q0.setParameter("cda_acc_no", cda_acc_no);
				Long c1 = (Long) q0.uniqueResult();
				if (c1 > 0) {
					return "Data already Exist.";
				}
				else {
					Query q01 = sessionhql2.createQuery(
							"select count(id) from TB_CDA_ACC_NO where comm_id=:comm_id and status='0'")
					         .setParameter("comm_id", comm_id);
					Long c = (Long) q01.uniqueResult();
					if (c > 0) {
						String hql = "update TB_CDA_ACC_NO set cda_acc_no=:cda_acc_no,"
								+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  " + " where  comm_id=:comm_id  and status='0'";
						Query query = sessionhql2.createQuery(hql).setString("cda_acc_no", cda_acc_no)
								 .setParameter("comm_id", comm_id).setString("modified_by", username)
								.setTimestamp("modified_date", new Date()).setInteger("status", 0);
						msg = query.executeUpdate() > 0 ? "update" : "0";
					}
					else {
						TB_CDA_ACC_NO cd = new TB_CDA_ACC_NO();
						cd.setCda_acc_no(cda_acc_no);					
						cd.setCensus_id(cen_id);
						cd.setComm_id(comm_id);
						cd.setCreated_by(username);
						cd.setCreated_date(date);
						cd.setStatus(0);
						int id = (int) sessionhql2.save(cd);
						msg = Integer.toString(id);
					}
					
				}
				tx2.commit();
				com.update_miso_role_hdr_status(comm_id, 0, username, "cda_status");
				
		} catch (RuntimeException e) {
			e.printStackTrace();
			tx2.rollback();
		} finally {
 			if (sessionhql2 != null) {
				sessionhql2.close();
			}
		}
		return msg;
	}

	private Boolean check_future(String fmDate) {
		// TODO Auto-generated method stub
		boolean r=false;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		try {
			Date fmDate2 = format.parse(fmDate);
			 Date currentDate = new Date();

			    if (fmDate2.after(currentDate)) {
			    
			 return false;
			    } else {
			        // fmDate2 is not a future date
			       return true;
			    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
		 
	}
	@RequestMapping(value = "/admin/xml_upload_logs", method = RequestMethod.GET)
	public ModelAndView xml_upload_logs(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		return new ModelAndView("xml_upload_logs_Tiles");
	}
	
	@RequestMapping(value = "/getUploadedFiles_count", method = RequestMethod.POST)
	public @ResponseBody long  getUploadedFiles_count(String army, String name,String frm_dt1,String to_dt1,String Search,String status,String orderColunm,String orderType,
			String present_p2_no,String selected_id,	String susno,String unitname, HttpSession sessionUserId
		) {
		String roleType = sessionUserId.getAttribute("roleType").toString();
return xmldao.getUploadedFiles_count(Integer.parseInt(status),army, name, frm_dt1, to_dt1,Search, orderColunm, orderType, present_p2_no, selected_id,susno,unitname,sessionUserId);
	}
	

	@RequestMapping(value = "/getUploadedFiles", method = RequestMethod.POST)
	public @ResponseBody  List<Map<String, Object>>  getUploadedFiles(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String name, String army ,String frm_dt1,
			String to_dt1,String status,String present_p2_no,String selected_id,String susno,String unitname ) {
//		if (frm_dt1 != "" && to_dt1 != "") {
//			if (mcommon.CompareDate(frm_dt1, to_dt1) == 0) {
//				Mmap.put("msg", "To Date should not be less than From Date");
//				return new ModelAndView("redirect:imb_report");
//			}
//		}
		return xmldao.getUploadedFiles(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				name, army, frm_dt1, to_dt1,Integer.parseInt(status), present_p2_no, selected_id,susno,unitname);
	}
	
	
//	@RequestMapping(value = "/getUploadedFiles_count", method = RequestMethod.POST)
//	public @ResponseBody long  getUploadedFiles_count(String army, String name,String frm_dt1,String to_dt1,String Search,String orderColunm,String orderType,
//			HttpSession sessionUserId
//		) {
//		String roleType = sessionUserId.getAttribute("roleType").toString();
//return xmldao.getUploadedFiles_count(army, name, frm_dt1, to_dt1,Search, orderColunm, orderType, sessionUserId);
//	}
//	
//
//	@RequestMapping(value = "/getUploadedFiles", method = RequestMethod.POST)
//	public @ResponseBody  List<Map<String, Object>>  getUploadedFiles(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType, HttpSession sessionUserId, String name, String army ,String frm_dt1,String to_dt1) {
////		if (frm_dt1 != "" && to_dt1 != "") {
////			if (mcommon.CompareDate(frm_dt1, to_dt1) == 0) {
////				Mmap.put("msg", "To Date should not be less than From Date");
////				return new ModelAndView("redirect:imb_report");
////			}
////		}
//		return xmldao.getUploadedFiles(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
//				name, army, frm_dt1, to_dt1);
//	}
	
	
	@RequestMapping(value = "/get_comm_personnelnoList", method = RequestMethod.POST)
	public @ResponseBody List<String> get_comm_personnelnoList(String personel_no, HttpSession sessionUserId) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// try{
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Query q = null;

		if (roleAccess.equals("Unit")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1' and unit_sus_no=:roleSusNo and  upper(personnel_no)  like :personnel_no "
							+ " order by personnel_no")
					.setMaxResults(10);

			q.setParameter("roleSusNo", roleSusNo);
		}
		if (roleAccess.equals("MISO")) {

			q = sessionHQL.createQuery(
					"select distinct personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  status='1'  "
							+ " and upper(personnel_no)  like :personnel_no  order by personnel_no")
					.setMaxResults(10);

		}
		q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private String Save_Change_in_rank(BigInteger comm_id, String Rmk1,String fmDate, String toDate, String data1, String data3,
//			String data4, String username, String auth, String authdate,int type_of_rank) throws ParseException {
//		String msg = "";
//		int census_id = getCensusid(comm_id);
//		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);	
//		Date fmDate2 = format.parse(fmDate);
//
//		Date date = new Date();
//		Transaction tx = sessionhql.beginTransaction();
//		int final_mId = 0;
//try {
//	if (census_id != 0) {
//		int rank=get_rank_value(Rmk1);
//		if(rank!=0)
//		{
//			
//			Query q11 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_RANK where to_char(date_of_rank, 'DD/MM/YYYY') = :date_of_rank and comm_id = :comm_id and status != -1")
//	                .setString("date_of_rank", format.format(fmDate2))
//	                .setParameter("comm_id", comm_id);
//
//
//			Long c = (Long) q11.uniqueResult();
//			System.out.println("-------"+fmDate);
//			if(c>0) {
//				tx.commit();
//				return "Date Of Rank Already Exists";
//			}
//
//			else {
//				String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '0' and comm_id=:comm_id ";
//				Query query = sessionhql.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id);
//				@SuppressWarnings("unchecked")
//				List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
//				if (list.isEmpty()) {
//					TB_CHANGE_OF_RANK cr = new TB_CHANGE_OF_RANK();
//
//					cr.setAuthority(auth);
//					cr.setDate_of_authority(format.parse(authdate));
//					cr.setRank_type(type_of_rank);
//					cr.setRank(rank);
//					cr.setDate_of_rank(format.parse(fmDate));
//					cr.setCensus_id(census_id);
//					cr.setComm_id(comm_id);
//					cr.setCreated_by(username);
//					cr.setCreated_date(date);
//					cr.setStatus(0);
//
//					int id = (int) sessionhql.save(cr);
//					tx.commit();
//					msg = Integer.toString(id);
//				
//					if(id!=0)
//					{
//						msg="SuccessFull Saved";
//					}
//					else {
//						msg="Data not Saved";
//					}
//					
//				} 
//				else {
//					msg="Data Pending For Approval";
//				}				
//			}
//		}
//		else {
//			msg="Rank does not exist in  database ";
//		}
//		}
//	else {
//		msg="Census Not Filled";
//	}
//	//tx.commit();
//	
//} catch (RuntimeException e) {
//	try {
//		tx.rollback();
//		msg = "Failure";
//	} catch (RuntimeException rbe) {
//		msg = "Failure";
//	}
//} finally {
//	if (sessionhql != null) {
//		sessionhql.close();
//	}
//}
//return msg;
//	}
//

	Change_Of_Rank_controller ChngRnk = new Change_Of_Rank_controller();

//	private  String approve_rank(BigInteger comm_id, int census_id, int rank, String fmDate, String username) throws ParseException {
//		String msg="";
//		Date modified_date = new Date();
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		TB_TRANS_PROPOSED_COMM_LETTER Comm = new TB_TRANS_PROPOSED_COMM_LETTER();
//
//		Comm.setId(comm_id);
//
//		TB_CHANGE_OF_RANK ChangeRank = new TB_CHANGE_OF_RANK();
//
//		ChangeRank.setCensus_id(census_id);
//
//		ChangeRank.setComm_id(comm_id);
//
//		ChangeRank.setModified_date(modified_date);
//
//	msg= ChngRnk.Update_Change_of_Rank(ChangeRank, username);
//if(msg.equals("Data Approve Successfully.")) {
//		Date dt_rank = null;
//
//		Comm.setRank(rank);
//
//
//		Comm.setDate_of_rank(format.parse(fmDate));
//
//		Comm.setModified_date(modified_date);
//
//		msg=ChngRnk.Update_Comm_Rank(Comm, username);
//}
//
//return msg;
//	}

	public int  get_rank_value(String rmk1) {

		Session session1 = HibernateUtil.getSessionFactory().openSession();
int code=0;
		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery(
				"select distinct id from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = 'RANK' " + 
				"and parent_code ='0' and code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') and status_active = 'Active' and description=:description  order by id ").setParameter("description", rmk1.trim().toUpperCase());

		@SuppressWarnings("unchecked")

		List<Integer> list = (List<Integer>) q1.list();
		if(!list.isEmpty())
		{
			code=list.get(0);
		}
		tx1.commit();

		session1.close();

		return code ;

	}
//	private int get_rank_value(String rmk1) {
//		List<String> rank_list=com.getOFFTypeofRankList();
//		if(!rank_list.isEmpty())
//		
//			{
//			System.out.println(rank_list.get(0));
//			for(String s: rank_list)
//			{
//				System.out.println("SSSSSS -----------------"+s);
//			}
//			}
//		return 0;
//	}

//	private String SaveMarraiage(BigInteger comm_id, String fmDate, String toDate, String data1, String data2,
//			String data3, String username, String auth, String authdate,String Rmk3) throws ParseException {
//		String msg = "";
//		int census_id = getCensusid(comm_id);
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//
//		Date date = new Date();
//		Transaction tx = sessionHQL.beginTransaction();
//		int  final_mId=0;
//		try {
//			if (census_id != 0) {
//				String hqlUpdate4 = "from TB_CENSUS_FAMILY_MRG where cen_id = :census_id and comm_id = :comm_id and status = 0 and   type_of_event=1  order by id desc";
//
//				Query query3 = sessionHQL.createQuery(hqlUpdate4)
//				        .setParameter("census_id", census_id, IntegerType.INSTANCE) 
//				        .setParameter("comm_id", comm_id, BigIntegerType.INSTANCE) ;   
//				List<TB_CENSUS_FAMILY_MRG> list3 =  query3.list();
//				int nationality=0;
//				if(data2.equals("BHUTANESE"))
//				{
//					nationality=41;
//				}
//				else if(data2.equals("INDIAN"))
//				{
//					nationality=22;
//				}
//				else if(data2.equals("NEPALESE"))
//				{
//					nationality=10;
//				}
//				else {
//					nationality=14;		
//				}
//				if (!list3.isEmpty()) {
//					
//					String hql = "update TB_CENSUS_FAMILY_MRG set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,maiden_name=:name,"
//							+  " authority=:auth,date_of_authority=:authdate,date_of_birth=:date_of_birth,marriage_date=:marriage_date,place_of_birth=:place_of_birth,nationality=:nationality  "
//							+ " where  id=:id";
//					
//					Query query2 = sessionHQL.createQuery(hql).setInteger("status", 0).setString("name", data1)
//							.setString("modified_by", username).setTimestamp("modified_date", date).setString("auth", auth).setTimestamp("authdate",format.parse(authdate) )
//							.setTimestamp("date_of_birth", format.parse(toDate)).setTimestamp("marriage_date", format.parse(fmDate)).setString("place_of_birth",Rmk3 ).setInteger("nationality",nationality).setInteger("id", list3.get(0).getId());
//					
//					msg = query2.executeUpdate() > 0 ? "updated successfully" : "Failure";
//					
//				//	com.update_offr_status(census_id, username);
//				
//				}
//				else {
//
//					String hqlUpdate5 = "from TB_CENSUS_FAMILY_MRG where cen_id =:census_id and comm_id =:comm_id and status = 0  and   type_of_event !=1  ";
//
//					Query query5 = sessionHQL.createQuery(hqlUpdate5)
//					        .setParameter("census_id", census_id, IntegerType.INSTANCE) 
//					        .setParameter("comm_id", comm_id, BigIntegerType.INSTANCE) ;   
//					List<TB_CENSUS_FAMILY_MRG> list5 =  query5.list();
//					
//					if(list5.isEmpty())
//					{
////						
//					
//					TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();
//					fam_marr.setMaiden_name(data1);
//						fam_marr.setDate_of_birth(format.parse(toDate));
//						fam_marr.setMarriage_date(format.parse(fmDate));
//						fam_marr.setPlace_of_birth(Rmk3);
//						fam_marr.setNationality(nationality);
//						fam_marr.setAdhar_number("");
//						fam_marr.setAuthority(auth);
//						fam_marr.setDate_of_authority(format.parse(authdate));
//						fam_marr.setPan_card("");
//						fam_marr.setIf_spouse_ser("");
//
//					fam_marr.setCen_id(census_id);
//					fam_marr.setCreated_by(username);
//					fam_marr.setCreated_date(date);
//					fam_marr.setComm_id(comm_id);
//					fam_marr.setType_of_event(1);
//					fam_marr.setMarital_status(8);
//					fam_marr.setStatus(0);
//					final_mId = (int) sessionHQL.save(fam_marr);
//					//com.update_offr_status(census_id, username);
//					}
//					else {
//						msg="Data for Divorce is pending ";
//					}
//				}
//			}
//			else {
//				msg="Census Not Filled";
//			}
//			tx.commit();
//		} catch (RuntimeException e) {
//			try {
//				tx.rollback();
//				msg = "Failure";
//			} catch (RuntimeException rbe) {
//				msg = "Failure";
//			}
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return msg;
//	}
//
//	private String Birth_child_new(BigInteger comm_id, String fmDate, String toDate, String data1, String data2,
//			String data3, String username, String auth, String authdate) throws ParseException {
//		String msg="";
//		int census_id=getCensusid(comm_id);
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		
//		Date date = new Date();
//		Transaction tx = sessionHQL.beginTransaction();
//		try {
//		if(census_id!=0)
//		{
//			if(data3.equals("Daughter")||data3.equals("Son") )
//			{
//				String hqlUpdate3 = " from TB_CENSUS_CHILDREN where cen_id=:census_id and comm_id=:comm_id and name=:name order by id desc ";
//
//				Query query4 = sessionHQL.createQuery(hqlUpdate3).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setParameter("name", data2);
//
//				List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query4.list();
//				
//				if(!list.isEmpty())
//				{
//							
//					String hql = "update TB_CENSUS_CHILDREN set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,name=:name,date_of_birth=:date_of_birth " 
//							+ "where  id=:id";
//
//					Query query2 = sessionHQL.createQuery(hql)
//							.setInteger("status", 1).setString("name", data2)
//							.setString("modified_by", username).setTimestamp("modified_date", date).setTimestamp("date_of_birth",format.parse(fmDate)).setInteger("id", list.get(0).getId());
//
//					msg = query2.executeUpdate() > 0 ? "updated successfully" : "Failure";
//					
//					//com.update_offr_status(census_id,username);
//					
//				
//				}
//				else {
//					TB_CENSUS_CHILDREN chil = new TB_CENSUS_CHILDREN();
//
//					chil.setName(data2);
//
//					chil.setDate_of_birth(format.parse(fmDate));
//
//					chil.setType("");
//					if (data3.equals("Daughter")) {
//						chil.setRelationship(1);
//					}
//					if (data3.equals("Son")) {
//						chil.setRelationship(2);
//					}
//
//			
//					chil.setAdoted("");
//
//					// chil.setDate_of_adpoted("");
//
//					chil.setAadhar_no("");
//
//					chil.setPan_no("");
//
//					// chil.setChild_service();
//
//					chil.setChild_personal_no("");
//
//					chil.setIf_child_ser("");
//
//					chil.setOther_child_ser("");
//
//					chil.setCen_id(census_id);
//
//					chil.setComm_id(comm_id);
//
//					chil.setCreated_by(username);
//
//					chil.setCreated_date(date);
//
//					chil.setStatus(1);
//
//					int id = (int) sessionHQL.save(chil);
//
//					//msg = Integer.toString(id);
//					//com.update_offr_status(census_id,username);
//					msg="Successfully uploaded";
//				}
//				
//			}
//		}
//		else {
//			msg="Census Not Filled";
//		}
//		tx.commit();
//		} catch (RuntimeException e) {
//			try {
//				tx.rollback();
//				msg = "Failure";
//			} catch (RuntimeException rbe) {
//				msg = "Failure";
//			}
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return msg;
//	}
//
//	
//


	
	@RequestMapping(value = "/getcensus_id", method = RequestMethod.POST)
	public @ResponseBody Integer getcensus_id(String comm_id, HttpSession sessionUserId) {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String hql1 = "select id from TB_CENSUS_DETAIL_Parent where comm_id= :comm_id ";
		Query query1 = sessionhql.createQuery(hql1);
		query1.setParameter("comm_id",new BigInteger(comm_id ));
		List <Integer>list6 = query1.list();
		
		int census_id=0;
		if(!list6.isEmpty())
		{
			 census_id=list6.get(0);
		}
		
		return  census_id;
	}

		
		
		
		
	public int getCensusid(BigInteger comm_id)
	{
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String hql1 = "select id from TB_CENSUS_DETAIL_Parent where comm_id= :comm_id ";
		Query query1 = sessionhql.createQuery(hql1);
		query1.setParameter("comm_id",comm_id);
		List <Integer>list6 = query1.list();
		
		int census_id=0;
		if(!list6.isEmpty())
		{
			 census_id=list6.get(0);
		}
		
		return  census_id;
	}
	
//	@RequestMapping(value = "/admin/getPerAddressDataStatus_XML", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_ADDRESS> getPerAddressDataStatus_XML(BigInteger comm_id,String status) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ADDRESS where  comm_id=:comm_id and  status = :status order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setMaxResults(1)
				.setBigInteger("comm_id", comm_id)
				.setInteger("status",Integer.parseInt(status));
		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	
//	private String  saveappointment(BigInteger comm_id, String data2, String auth, String authdate,String fromDate,String username,String comm_date) throws ParseException {
//		String msg="";
//		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = sessionhql.beginTransaction();
//		Date date = new Date();
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		Date date_of_appointment = null;
//		date_of_appointment = format.parse(fromDate);
//
//		int census_id=getCensusid( comm_id);
//		if(census_id!=0)
//		{
//			
//			if (com.CompareDate(format.parse(authdate),format.parse(comm_date)) == 0) {
//				msg= "Authority Date should be Greater than Commission Date";
//			}
//			else {
//			try {
//				String hqlUpdate1 = " select id from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='0'";
//				Query query3 = sessionhql.createQuery(hqlUpdate1);
//		
//				query3.setInteger("census_id", census_id);
//				query3.setBigInteger("comm_id", comm_id);
//	
//				List<Integer> list = query3.list();
//				int appoint_id;
//				if(list.size()!=0)
//				{
//					appoint_id =list.get(0);
//				}
//				else {
//					 appoint_id=0;
//				}
//		
//				Query q10 = sessionhql.createQuery("select count(id) from TB_CHANGE_OF_APPOINTMENT where date_of_appointment=:date_of_appointment and comm_id=:comm_id  and status!=-1").setTimestamp("date_of_appointment", date_of_appointment)
//					.setParameter("comm_id", comm_id);
//					Long c = (Long) q10.uniqueResult();
//					if(c>0) {
//					msg= "Date of Appointment Already Exists";
//					}
//					else {
//					if (appoint_id == 0) {
//					
//						
//					TB_CHANGE_OF_APPOINTMENT appt = new TB_CHANGE_OF_APPOINTMENT();
//					appt.setCreated_by(username);
//					appt.setCreated_date(date);
//					appt.setAppt_authority(auth);
//					appt.setAppt_date_of_authority(format.parse(authdate));
//					appt.setAppointment(Integer.parseInt(data2));
//					appt.setDate_of_appointment(date_of_appointment);
////					appt.setX_list_sus_no(x_sus_no);
////					appt.setX_list_loc(x_list_loc);
//					appt.setComm_id(comm_id);
//					appt.setCensus_id(census_id);
//					appt.setStatus(0);
//					appt.setCreated_by(username);
//					appt.setCreated_date(date);
////					appt.setParent_sus_no(parent_sus_no);
////					appt.setParent_unit(parent_unit);
////					appt.setX_list_loc(x_list_loc);
//					
//					int id = (int) sessionhql.save(appt);
//					msg = "Inserted Successfully";
//					tx.commit();
//					//msg=approve_appointment(msg,comm_id,census_id,username);
//				} else {
//					
//					String hql = "update TB_CHANGE_OF_APPOINTMENT set appt_authority=:appt_authority, "
//							+ "appt_date_of_authority=:appt_date_of_authority, appointment=:appointment, "
//							+ "date_of_appointment=:date_of_appointment,status=:status,"
////							+ "x_list_sus_no=:x_list_sus_no,x_list_loc=:x_list_loc, "
//	                        + "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,x_list_loc=:x_list_loc, "
//							+ "modified_by=:modified_by, modified_date=:modified_date where  id=:id";
//					Query query = sessionhql.createQuery(hql).setInteger("status", 0)
//							.setString("appt_authority", auth)
//							.setTimestamp("appt_date_of_authority", format.parse(authdate))
//							//.setString("x_list_sus_no", x_sus_no).setString("x_list_loc", x_list_loc)
//						.setString("parent_sus_no", "")
//							.setString("parent_unit", "")
//							.setString("x_list_loc", "")
//							.setInteger("appointment", Integer.parseInt(data2))
//							.setTimestamp("date_of_appointment", date_of_appointment).setString("modified_by", username)
//							.setTimestamp("modified_date", new Date()).setInteger("id", appoint_id);
//
//					msg = query.executeUpdate() > 0 ? "Updated SuccessFully" : "Failure";
////					tx.commit();
////					msg=approve_appointment(msg,comm_id,census_id,username);
//				//	List<TB_CHANGE_OF_APPOINTMENT> ChngAppointment = Ap.get_Appointment1(Integer.parseInt(updateid),
//
//							
//					
//				}
//
//
////				String approoval_status = request.getParameter("app_status");
////				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
////					
////				}
////				else
////				{
////					com.update_offr_status(census_id,username);
////				}
//					//com.update_offr_status(census_id,username);
//				//tx.commit();
//					}
//			} catch (RuntimeException e) {
//				try {
//					tx.rollback();
//					msg = "Data Not Updated Or Saved";
//				} catch (RuntimeException rbe) {
//					msg = "Data Not Updated Or Saved";
//				}
//			} finally {
//				if (sessionhql != null) {
//					sessionhql.close();
//				}
//			}
//			}
//		}
//		else {
//			msg="Census Is Not Fillled";
//		}
//		
//		return msg;
//	}
//
//	
//
//	private String approve_appointment(String msg, BigInteger comm_id, int census_id, String username) {
//	
//		String result="";
//		if (msg.equals("Inserted Successfully")||msg.equals("Updated SuccessFully")) {
//
//			TB_CHANGE_OF_APPOINTMENT ChngAppo = new TB_CHANGE_OF_APPOINTMENT();
//
//			ChngAppo.setCensus_id(census_id);
//
//			ChngAppo.setComm_id(comm_id);
//
//			ChngAppo.setModified_date(new Date());
//
//	return	Ap.Update_Change_of_Appointment(ChngAppo, username);
//
//		}
//		return "Not Approved";
//	}


	
	
	
	
//	public static String getRankDescription(String  rankCode) {
//        String rankDesc;
//        switch (rankCode) {
//            case "01":
//                rankDesc = "Lt";
//                break;
//            case "02":
//                rankDesc = "Capt";
//                break;
//            case "03":
//                rankDesc = "Maj";
//                break;
//            case 4:
//                rankDesc = "Lt Col";
//                break;
//            case 5:
//                rankDesc = "Lt Col";
//                break;
//            case 6:
//                rankDesc = "Col";
//                break;
//            case 7:
//                rankDesc = "Col";
//                break;
//            case 8:
//                rankDesc = "Brig";
//                break;
//            case 9:
//                rankDesc = "Maj Gen";
//                break;
//            case 10:
//                rankDesc = "Lt Gen";
//                break;
//            case 11:
//                rankDesc = "Lt Gen";
//                break;
//            case 12:
//                rankDesc = "Lt Gen";
//                break;
//            case 13:
//                rankDesc = "Lt Gen";
//                break;
//            case 14:
//                rankDesc = "Gen";
//                break;
//            case 15:
//                rankDesc = "Field Marshal";
//                break;
//            default:
//                rankDesc = "Unknown Rank";
//                break;
//        }
//        return rankDesc;
//    }
//	
	
	
	
	
	
	
	
	
	
	
	
//	
//	public String savepost_in()
//	{
//		String msg="";
//	     TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
//		ArrayList<ArrayList<String>> list = pod.GetCommDataApprove(comm_id);
//        if (list.size() > 0)
//        {
//                if(tos!=null && list.get(0).get(6)!=null) {
//                        String regex = "^0+(?!$)";
//                        String newTos=request.getParameter("dt_of_tos");
//                        String preTos=list.get(0).get(6).toString().substring(0,10);
//                        System.out.println(preTos+"====preTos");
//                        System.out.println(newTos+"====newTos");
//                        String newTosArr[]=newTos.split("/");
//                        String preTosArr[]=preTos.split("-");
//                        int newTosM=Integer.parseInt(newTosArr[1].replaceAll(regex, ""));
//                        int newTosY=Integer.parseInt(newTosArr[2]);
//                        int preTosM=Integer.parseInt(preTosArr[1].replaceAll(regex, ""));
//                        int preTosY=Integer.parseInt(preTosArr[0]);
//                        
//                        if(newTosY==preTosY){
//                                if(newTosM<=preTosM){
//                                        return "Invalid Date of TOS";
//                                }
//                        }
//                        else if(newTosY<preTosY){
//                                return "Invalid Date of TOS";
//                        }
//                }
//                po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(2))));
//                if (list.get(0).get(14) != null && !list.get(0).get(14).equals("null"))
//                {
//                        po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(14))));
//                }
//        }
//        int id1 = i_id > 0 ? po.getId() : 0;
//        try {
//                Boolean v = pod.getpernoAlreadyExits(comm_id);
//                if (v == true)
//                {
//                        rvalue = "Data already exists.";
//                        return rvalue;
//                }
//                if (v == false) {
//                        if (id1 == 0) {
//                                String roleAccess = session.getAttribute("roleAccess").toString();
//
//                                ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
//                                ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
//                                po.setCreated_by(username);
//                                po.setCreated_date(new Date());
//                                //po.setCensus_id(census_id);
//                                po.setComm_id(comm_id);
//                                if(orbatlist.size()>0) {
//                                po.setCmd_sus(orbatlist.get(0).get(1));
//                                po.setCorps_sus(orbatlist.get(0).get(2));
//                                po.setDiv_sus(orbatlist.get(0).get(3));
//                                po.setBde_sus(orbatlist.get(0).get(4));
//                                }
//                                if(Location_Trnlist.size()>0) {
//                                po.setLocation(Location_Trnlist.get(0).get(0));
//                                po.setTrn_type(Location_Trnlist.get(0).get(1));
//                                }
//                                po.setFrom_sus_no(from_sus_no);
//                                po.setTo_sus_no(roleSusNo);
//                                po.setDt_of_sos(tos);
//                                po.setIn_auth(in_auth);
//                                po.setIn_auth_dt(in_auth_dt1);
//                                po.setDt_of_tos(tos);
//                                po.setStatus(0);
//                               
//                                po.setT_status(t_stus);
//                                //po.setNotification_status(0);
//                                sessionHQL.save(po);
//                                int id12 = (int) sessionHQL.save(po);
//                                tx.commit();
//                                rvalue = String.valueOf(po.getId());
//                        }
//                }
//        }
//		
//		
//		return msg;
//	}

}



//conn = dataSource.getConnection();
//SAXBuilder builder = new SAXBuilder();
//Document document = builder.build(file);
//List<String> dataArray = new ArrayList<>();
//String[] columnNames = { "SUSNo", "UnitName", "Address", "ServingIn", "BrigadeSubArea", "DivArea", "Corps",
//		"Command", "Observation", "PresentP2No", "PresentP2Date", "LastP2No", "LastP2Date", "OffrLastP2No",
//		"OffrLastP2Date", "TotalCasualties", "Observation", "ArmyNo", "Rank", "Name", "ArmService",
//		"RankCode", "CDAACNo", "PAN", "Observation", "CasSeqNo", "Description", "FmDate", "ToDate", "Data1",
//		"Data2", "Data3", "Data4", "Authority", "Enclosure", "Certificate", "Rmk1", "Rmk2", "Rmk3", "Rmk4",
//		"Rmk5", "Rmk6", "Rmk7", "Rmk8", "Rmk9", "Rmk10", "Remark", "Observation", "Attachment" };
//Element rootElement = document.getRootElement();
//String[] rootNames = { "UnitDtls", "Part2Details", "OfficerDetils", "Casualties" };
//String[] subRootNames = { "UnitDetails", "Part2OrderDetails", "Officer", "CasualtyDetails" };
//int[] min = { 0, 9, 17, 25 };
//int[] max = { 8, 16, 24, 48 };
//for (int k = 0; k <= 3; k++) {
//	List<Element> elements = rootElement.getChildren(rootNames[k]);
//	for (Element unitDtlsElement : elements) {
//		List<Element> unitDetailsElements = unitDtlsElement.getChildren(subRootNames[k]);
//
//		for (Element element : unitDetailsElements) {
//
//			for (int i = min[k]; i <= max[k]; i++) {
//				System.out.println(
//						"element.getChildText(columnNames[i]) = " + element.getChildText(columnNames[i]));
//				dataArray.add(element.getChildText(columnNames[i]) == null ? ""
//						: element.getChildText(columnNames[i]));
//			}
//			System.out.println("File uploaded and processed successfully!");
//		}
//	}
//}