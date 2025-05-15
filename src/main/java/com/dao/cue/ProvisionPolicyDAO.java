package com.dao.cue;

import java.util.List;
import java.util.Map;

import com.models.CUE_TB_MISO_PROVISION_MASTER;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_MASTER;

public interface ProvisionPolicyDAO {
	
	public String setApprovedProwep(int appid);
	public String setRejectProwep(int appid);
	
	public String setDeleteProwep(int appid);
	public CUE_TB_MISO_PROVISION_MASTER getEditProWep(int id);
	
	public String setApprovedProtra(int appid);
	public String setRejectProtra(int appid);
	
	public String setDeleteProtra(int appid);
	public  List<Map<String, Object>> AttributeReportProvision(String we_pe_no,String letter_no,String force_type,String status,String year_cal,String month_cal,String letter_date);
	public  List<Map<String, Object>> AttributeReportSearchProvisionMaster(String status,String year_cal,String month_cal,String we_pe_no,String letter_no,String force_type,String roleType) ;
	public  List<Map<String, Object>> AttributeReportProvisionMaster(String we_pe_no,String letter_no,String force_type,String status,String year_cal ,String month_cal,String roleType) ;
	public  List<Map<String, Object>> AttributeReportSearchProvisionTrans(String status,String we_pe_no,String letter_no,String force_type,String year_cal,String month_cal,String roleType) ;
	public CUE_TB_MISO_PROVISION_TRANSPORT_MASTER getEditProTrans(int id);

}
