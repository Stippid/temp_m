package com.dao.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.UploadAamendmentToDocument;
import com.models.cue_wepe;

public interface cueStandardEntitlementTransportDAO {	
	
	public List<Map<String, Object>> getMctNoList(HttpSession sessionUserId,String mct_main_id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getReportList();
	public String setApprovedtrans(int appid,String username, String we_pe_no);
	public String setDeletetrans(int appid);
	public CUE_TB_MISO_WEPE_TRANSPORT_DET getCUE_Trans_Authorizarion_id(int id);
	public String UpdateTransMasterValue(CUE_TB_MISO_WEPE_TRANSPORT_DET ItemMasterValue, String username);
	
	public List<Map<String, Object>> getStdNomenclatureList(HttpSession sessionUserId,String mct_nomen) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public int deleteAlreadyExistInFootModImp(int id,String we_pe_no);
	public int deleteAlreadyExistInModImp(int id,String we_pe_no) ;
	public List<Map<String, Object>> getAttributeFromTransEntitlementDtl(String we_pe_no, String mct_no, String we_pe,
			String status, String roleType, String roleAccess, String roleSusNo);
	 public CUE_TB_WEPE_link_sus_perstransweapon getSusnotrans(String we_pe_no);
	 //////////////////////
	 
	 public  List<Map<String, Object>> getViewTransEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkTransEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkTransModEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkTransfotnoteEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewWeaponEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkWeaponEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkWeaponModEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkWeaponfotnoteEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewUnitDtlPersReport(String sus_no, String we_pe_no);
	 public List<Map<String, Object>> getViewPersonEntitlementDtl(String sus_no, String we_pe_no);
	 public  List<Map<String, Object>> getViewStdlnkPersonEntitlementDtl(String sus_no);
	 public  List<Map<String, Object>> getViewStdlnkPersonModEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkPersonfotnoteEntitlementDtl(String sus_no);
	 public List<Map<String, Object>> getViewStdlnkTransInlieuEntitlementDtl(String sus_no);
	 
	 public List<Map<String, Object>> getViewWeaponCESEntitlementDtl(List<Map<String, Object>> list4);
	 public ArrayList<ArrayList<String>> ces_cess_popup(int id);
	 
	 public  List<String> getcue_wepeByid_trasnsent(String we_pe_no);
	 public  List<String> get_amdt_to_doc(String we_pe_no);
	 
	 public  List<String> get_wet_pet_doc(String wet_pet_no);
	 public  List<Map<String, Object>> getAttributeFromTransEntitlementDtl1(String mct_no,String we_pe_no,String status, String roleType);
	 public  List<String> get_wet_pet_amd_doc(String wet_pet_no);
	 
	 
	 public  List<Map<String, Object>> getViewTransEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkTransModEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkTransfotnoteEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewWeaponEntitlementDtladdMore(String we_pe_no, String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkWeaponModEntitlementDtladdMore(String we_pe_no, String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkWeaponfotnoteEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewPersonEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public  List<Map<String, Object>> getViewStdlnkPersonModEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkPersonfotnoteEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 public List<Map<String, Object>> getViewStdlnkTransInlieuEntitlementDtladdMore(String we_pe_no,String status,String roleType,String roleAccess);
	 
	 public List<Map<String, Object>> getViewUnitDtlTransReport(String roleSusNo,String roleAccess);
	 
		public List<Map<String, Object>> getPrfCodeList(HttpSession sessionUserId,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		public List<Map<String, Object>> getPrfGpNameList(HttpSession sessionUserId,String prf_gp_name) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		public List<Map<String, Object>> getMctNoListPrf(HttpSession sessionUserId,String mct_main_id,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		public List<Map<String, Object>> getMctNCListPrf(HttpSession sessionUserId,String mct_nomen,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		
}
