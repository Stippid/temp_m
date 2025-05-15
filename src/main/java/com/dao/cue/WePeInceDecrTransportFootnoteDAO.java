package com.dao.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;

public interface WePeInceDecrTransportFootnoteDAO {
	
	public  List<Map<String, Object>> getFormation(HttpSession sessionUserId,String formation) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public  List<Map<String, Object>> getUnit(HttpSession sessionUserId,String unit) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	 public String setApprovedItem(int appid,String username);
	public String setRejectItem(int rejectid);
	public String setDeleteItem(int deleteid);
	
	public CUE_TB_MISO_WEPE_TRANS_FOOTNOTES getCUE_TB_MISO_MMS_ITEM_MSTRid(int id);
	public  List<Map<String, Object>> getAttributeFromFootnoteMaster(String status,String mct_no,String we_pe_no,String roleType, String roleAccess, String roleSusNo);
	public  List<Map<String, Object>> getAttributeFromFootnoteMaster1(String mct_no,String we_pe_no, String status, String roleType,String roleAccess);
	public  List<Map<String, Object>> getAttributeFromFootnoteMaster23(String we_pe_no,String mct_no,String status);
}
