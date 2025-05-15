package com.dao.itasset.WorkOrder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.assets.TB_IT_ASSET_GENR_SANCTION_FORM;

public interface genr_sanctionformDAO {

	public ArrayList<ArrayList<String>> genrItAssetSanctionfomList(String roleSusNo);

	public ArrayList<ArrayList<String>> personalGenrSanctionData(String batchId);

	public boolean saveItAssetGenerList(List<TB_IT_ASSET_GENR_SANCTION_FORM> itAssetGenerList, String username);

	public List<TB_IT_ASSET_GENR_SANCTION_FORM> searchItAssetSanctionfomList(String batchId);

	public boolean updateConvDetails(String batchId, String convNo, Date convDate);

	public boolean deleteItAssetSanctionForm(String personnelNo, String batchId);

	public String getPdfPath(String batch_id);


	public long GenrItAssetsSearchCount(String Search, String orderColunm, String orderType, String unit_sus_no,
			String unit_name, String unit_status, String batch_id, HttpSession session) throws SQLException;

	public boolean updatetypesStatus(String batchId);

	public boolean deleteStatus(String batchId, String u_file);

	public String generateBatchId(String roleSusNo);

	public List<Map<String, Object>> getBatchIdWisePdfData(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String roleSusNo, String status, String batch_id,
			HttpSession session);

	public long getBatchIdWisePdfCount(String Search, String orderColunm, String orderType, String roleSusNo,
			String status, String batch_id, HttpSession session) throws SQLException;

	public List<Map<String, Object>> getSactionDetails(int id);
	public ArrayList<ArrayList<String>> getFinalSanctionList(String batchId);
	public List<List<String>> getSanctionAuthorityData(String batchId);
	public ArrayList<ArrayList<String>> GenrItAssetsExcelData(String unit_sus_no, String unit_name, String unit_status,
			String batch_id, HttpSession session);
	public String genrApprovedataAction(String batchId, HttpSession session);
	public List<Map<String, Object>> GenrItAssetsSearchData(int startPage,int pageLength,String Search,String orderColunm,String orderType,String unit_sus_no,
			String unit_name, String unit_status, String batch_id, HttpSession session);
	
	public ArrayList<ArrayList<String>> CountTableData(String role, HttpSession session);
	
	
}
