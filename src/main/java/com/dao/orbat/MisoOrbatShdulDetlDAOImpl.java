package com.dao.orbat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.models.Miso_Orbat_Shdul_Detl;
import com.persistance.util.HibernateUtil;

public class MisoOrbatShdulDetlDAOImpl implements MisoOrbatShdulDetlDAO {
	
	public boolean checkPDFFileValidationOrbat(MultipartFile upload_goi_letter,MultipartFile upload_auth_letter) {
		if (!upload_goi_letter.isEmpty()) {
			try {
				byte[] bytes = upload_goi_letter.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					return true;
				}else {
					return false;
				}
			}catch (Exception e) {
				return false;
			}
		}
		if (!upload_auth_letter.isEmpty()) {
			try {
				byte[] bytes = upload_auth_letter.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					return true;
				}else {
					return false;
				}
			}catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	public boolean checkPDFFileValidationOrbat_AUTH_LETTER(MultipartFile upload_auth_letter) {
		if (!upload_auth_letter.isEmpty()) {
			try {
				byte[] bytes = upload_auth_letter.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					return true;
				}else {
					return false;
				}
			}catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	
	public Miso_Orbat_Shdul_Detl saveMiso_Orbat_Shdul_Detl(MultipartFile upload_goi_letter,MultipartFile upload_auth_letter,int userid,Miso_Orbat_Shdul_Detl shdul,String orbatFilePath) {
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		
		String fname = "";
		if (!upload_goi_letter.isEmpty()) {
			try {
				byte[] bytes = upload_goi_letter.getBytes();
				File dir = new File(orbatFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_GOI.PDF";
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
				shdul.setUpload_goi_letter(fname);
			}
			catch (Exception e) {
	       }
		} 
		
		if (!upload_auth_letter.isEmpty()) {
			try {
				byte[] bytes = upload_auth_letter.getBytes();
				File dir = new File(orbatFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_AUTH.PDF";
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
				shdul.setUpload_auth_letter(fname);
			}
			catch (Exception e) {
	       }
		} 
		return shdul;
	}	
	
	public String susNoGeneration(String parent_arm,String type_of_arm) {
		String sus_no = "";
		String type_of_arm1 = String.valueOf(type_of_arm.charAt(2)) + String.valueOf(type_of_arm.charAt(3));
		sus_no = parent_arm + type_of_arm1 ;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT max(sus_no) FROM Tb_Miso_Orbat_Sus_Dtl where sus_no like :sus_no");
		q.setParameter("sus_no", sus_no+"%");
		String list = (String) q.list().get(0);
		tx.commit();
		session.close();
		
		String serial = "";
		if(list == null) {
			serial = "000";
		}else {
			serial = String.valueOf(list.charAt(4))+String.valueOf(list.charAt(5))+String.valueOf(list.charAt(6));
		}
		
		int serialNo = Integer.parseInt(serial) + 1;
		sus_no += String.format("%03d", serialNo);
		
		int sum = 0;
		int length = sus_no.length() + 1;
		for(int i = 0; i<sus_no.length();i++) {
			String multi = String.valueOf(sus_no.charAt(i)); 
			int ans = Integer.parseInt(multi) * length;
			sum += ans;
			length--;
		}
		int mod = sum % 11;
		
		Session sessionT = HibernateUtil.getSessionFactory().openSession();
		Transaction txT = sessionT.beginTransaction();
		Query qT = sessionT.createQuery("select label FROM T_Domain_Value where domainid='CHECKDIGIT' and codevalue=:mod");
		qT.setParameter("mod", String.valueOf(mod));
		@SuppressWarnings("unchecked")
		List<String> list1 =  (List<String>)  qT.list();
		txT.commit();
		sessionT.close();
		
		sus_no +=list1.get(0);
		return sus_no;
	}
	

	public String  PrfCode() {
        String itemCode ="";
	    String prfcode = "B-01";
	    
	    char ch = prfcode.charAt(0);
	    int ascii = ch;
	    
	    // You can also cast char to int
	    int castAscii = (int) ch;
	        
	    //Prf First Two Degits 
	    String first = String.format("%02d", castAscii - 64);

	    //Prf second Two Degits 
	        
	    String second = String.valueOf(prfcode.charAt(2)) + String.valueOf(prfcode.charAt(3)); 
	    
	    itemCode += first + second;
	        
	    // Serial No
	    String serial = "001";
	        
	    itemCode += serial;
	        
	    
		int sum = 0;
		int length = itemCode.length()+1;
		for(int i = 0; i<itemCode.length();i++) {
			String multi = String.valueOf(itemCode.charAt(i)); 
			int ans = Integer.parseInt(multi) * length;
			sum += ans;
			length--;
			
		}
		int mod = sum % 11;
		Session sessionT = HibernateUtil.getSessionFactory().openSession();
		Transaction txT = sessionT.beginTransaction();
		Query qT = sessionT.createQuery("select label FROM T_Domain_Value where domainid='CHECKDIGIT' and codevalue=:mod");
		qT.setParameter("mod", String.valueOf(mod));
		@SuppressWarnings("unchecked")
		List<String> list1 =  (List<String>)  qT.list();
		txT.commit();
		sessionT.close();
		itemCode +=list1.get(0);
        return itemCode;
	}
}