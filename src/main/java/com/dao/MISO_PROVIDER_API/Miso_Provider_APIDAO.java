package com.dao.MISO_PROVIDER_API;

import java.util.List;
import java.util.Map;

public interface Miso_Provider_APIDAO {
/*	public List<Map<String, Object>> getVehiclesDtls(String sus_no);
	public List<Map<String, Object>> getManpowerOffrDtls(String sus_no);
	public List<Map<String, Object>> getManpowerJCO_ORDtls(String sus_no);
	public List<Map<String, Object>> getWeaponCategory(String sus_no);
	public List<Map<String, Object>> getWeaponState(String sus_no,String wpn_cat);
	public List<Map<String, Object>> getOrbatUnitDtls(String last_created_date);
*/
///////////////For New API Gateway
	public List<Map<String, Object>> getVehiclesDtlsNew(String sus_no);
	public List<Map<String, Object>> getManpowerOffrDtlsNew(String sus_no);
	public List<Map<String, Object>> getManpowerJCO_ORDtlsNew(String sus_no);
	public List<Map<String, Object>> getWeaponCategoryNew(String sus_no);
	public List<Map<String, Object>> getWeaponStateNew(String sus_no,String wpn_cat);
	public List<Map<String, Object>> getOrbatUnitDtlsNew(String last_created_date);
//	public List<Map<String, Object>> getOrbatUnitDtlsSus(String sus_no);
//	public List<Map<String, Object>> getOrbatUnitDtlsName(String unit_name);
}
