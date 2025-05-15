package com.dao.orbat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tb_Miso_Orbat_Cii_Unt_Dtl;
import com.models.Tb_Miso_Orbat_Mast_Fmn;
import com.models.Tbl_CodesForm;

public interface UnitProfileDAO {
	
	public   List<Tbl_CodesForm> getFormation();
	public DataSet<Map<String, Object>> findUnitProfileReportWithDatatablesCriterias(DatatablesCriterias criterias,String status12,String unit_name12,String sus_no12,String parent_arm12,String fmn12,String arm_name12,String loc12,String type_force12,String ct_part_i_ii12,String comm_depart_date12,String compltn_arrv_date12);
	
	public Miso_Orbat_Unt_Dtl getMiso_Orbat_Unt_DtlByid(int id);
	public List<Miso_Orbat_Shdul_Detl> getMiso_Orbat_Shdul_DetlByid(int id);
	public List<Tbl_CodesForm> getFormationDetailsFromSusNo(String sus_no);
	
	//28.10.2024
	
	public String setApprovedCII(int appid,String username);
	public String setRejectCII(int appid,String username);
	public String setDeleteCII(int appid);
	public Tb_Miso_Orbat_Cii_Unt_Dtl getTb_Miso_Orbat_Cii_Unt_DtlByid(int id);
	public String UpdateCII(Tb_Miso_Orbat_Cii_Unt_Dtl artAttValues,String username,String sus_no,String unit_name,
	String arm_code,String fmn_code,String loc_code,String nrs_code,String fmn_name,Date from_date,Date to_date,String authority);

	//CII VIEW
	
	public DataSet<Map<String, Object>> findUnitProfileReportWithDatatablesCriteriasCii(DatatablesCriterias criterias,String status12,String unit_name12,String sus_no12,String parent_arm12,String fmn12,String arm_name12,String loc12,String type_force12,String ct_part_i_ii12,String comm_depart_date12,String compltn_arrv_date12,String fmn_name2);
	//public Tb_Miso_Orbat_Mast_Fmn getTb_Miso_Orbat_Mast_Fmn_DtlByFmn_code(String fmn_code);
	public Tb_Miso_Orbat_Mast_Fmn getTb_Miso_Orbat_Cii_Unt_DtlByFmn_code(String fmn_code);
	
	

//NEW 14.10.2024
	List<String> findAllLevel1Options();
    List<String> findLevel2OptionsByLevel1(String level1);
    List<String> findLevel3OptionsByLevel2(String level2);
    List<String> findLevel4OptionsByLevel3(String level3);
    List<String> findLevel5OptionsByLevel4(String level4);
    List<String> findLevel6OptionsByLevel5(String level5);
    List<String> findLevel7OptionsByLevel6(String level6);
    List<String> findLevel8OptionsByLevel7(String level7);
    List<String> findLevel9OptionsByLevel8(String level8);
    List<String> findLevel10OptionsByLevel9(String level9);
    
    
    public List<Map<String, Object>> GetSearch_comd_and_cont_list(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
   		 String unit_sus_no,String status ) ;
    public long GetSearch_comd_and_cont_count(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status);

}
