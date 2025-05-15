package com.dao.orbat;

import org.springframework.web.multipart.MultipartFile;

import com.models.Miso_Orbat_Shdul_Detl;

public interface MisoOrbatShdulDetlDAO {
	public boolean checkPDFFileValidationOrbat(MultipartFile upload_goi_letter,MultipartFile upload_auth_letter);
	
	public boolean checkPDFFileValidationOrbat_AUTH_LETTER(MultipartFile upload_auth_letter);
	
	public Miso_Orbat_Shdul_Detl saveMiso_Orbat_Shdul_Detl(MultipartFile upload_goi_letter,MultipartFile upload_auth_letter,int userid,Miso_Orbat_Shdul_Detl shdul,String orbatFilePath);
	public String susNoGeneration(String parent_arm,String type_of_arm);
	//public Boolean checkifExistUnitName(String unitname);
}
