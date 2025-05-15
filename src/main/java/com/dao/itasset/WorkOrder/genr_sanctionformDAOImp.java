package com.dao.itasset.WorkOrder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.controller.ExportFile.PDF_Adhoc_mnh_qry;
import com.models.assets.TB_IT_ASSET_GENR_SANCTION_FORM;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.persistance.util.HibernateUtil;

public class genr_sanctionformDAOImp implements genr_sanctionformDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<ArrayList<String>> genrItAssetSanctionfomList(String roleSusNo) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			/*sql = "SELECT distinct   ro.id as rank_order, \r\n"
					+ "    t.id, t.unit_sus_no, \r\n"
					+ "    obt.unit_name,\r\n"
					+ "    t.personnel_no as ic_no,\r\n"
					+ "    t.name,\r\n"
					+ "    rk.description as rank,\r\n"
					+ "    CAST(t.date_of_birth as date) as dob,\r\n"
					+ "    CAST(t.date_of_commission as date) as doc,\r\n"
					+ "   CASE \r\n"
					+ "    WHEN SUBSTR(t.personnel_no, 1, 2) = 'SS' THEN  \r\n"
					+ "        TO_CHAR(LAST_DAY(CAST(t.date_of_commission as date) + INTERVAL '14 years'), 'YYYY-MM-DD')\r\n"
					+ "    ELSE\r\n"
					+ "        CASE \r\n"
					+ "            WHEN  t.rank = '12440' THEN  -- 'Maj'\r\n"
					+ "                TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '52 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN t.rank = '12439' THEN -- 'Lt Col'\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '52 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN  t.rank in ('12438','26243')  THEN --'Col'\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '54 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN  t.rank = '12437'  THEN --'Brig'\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '56 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN  t.rank = '12436'  THEN --'Maj Gen'\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '58 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN t.rank = '12435'  THEN --'Lt Gen'\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '60 years'), 'YYYY-MM-DD')\r\n"
					+ "            WHEN t.rank = '12434'  THEN --GEN\r\n"
					+ "                 TO_CHAR(LAST_DAY(CAST(t.date_of_birth as date) + INTERVAL '62 years'), 'YYYY-MM-DD')\r\n"
					+ "            ELSE NULL\r\n"
					+ "        END\r\n"
					+ "END as dor,\r\n"
					+ "    CASE WHEN age(CURRENT_DATE, date_of_commission) >= INTERVAL '8 years 5 months 15 days' THEN 'Yes' ELSE 'No' END AS eligibility , "
					+ "COALESCE( ( SELECT status FROM tb_it_asset_genr_sanction_form  WHERE ic_no = t.personnel_no and status !=3 ), -1) as genr_senction,"
					+ "COALESCE((SELECT upd.control_id FROM tb_it_asset_personal_upload upd\r\n"
					+ "		    inner join tb_it_asset_genr_sanction_form f on f.batch_id = upd.batch_id  WHERE f.ic_no = t.personnel_no  and f.status !=3), '0') as control_id   \r\n"
					+ "FROM\r\n"
					+ "    tb_psg_trans_proposed_comm_letter t\r\n"
					+ "    INNER JOIN tb_miso_orbat_unt_dtl obt \r\n"
					+ "        ON t.unit_sus_no = obt.sus_no\r\n"
					+ "        AND obt.status_sus_no = 'Active'\r\n"
					+ "     INNER JOIN cue_tb_psg_rank_app_master rk \r\n"
					+ "        ON rk.id = t.rank\r\n"
					+ "				and upper(rk.level_in_hierarchy) = 'RANK'\r\n"
					+ "	left join tb_psg_iaff_3008_rank_wise_data ro \r\n"
					+ "	   on ro.rank_code = rk.code \r\n"
					+ "WHERE  t.status = '1' and  \r\n"
					+ "    t.unit_sus_no = ?   and t.rank in ('12439','12440','12438','26243','12437','12436','12435','12434')  AND age(CURRENT_DATE, date_of_commission) >= INTERVAL '8 years 5 months 15 days'  	order by genr_senction,rank_order ,doc asc ";
*/
			sql = "SELECT distinct   genrit.rank_order,\r\n" + 
					"    genrit.id, genrit.sus_no, \r\n" + 
					"    genrit.unit_name,\r\n" + 
					"    genrit.ic_no,\r\n" + 
					"    genrit.name,\r\n" + 
					"     genrit.rank,\r\n" + 
					"     genrit.dob,\r\n" + 
					"     genrit.doc,\r\n" + 
					"	 genrit.dor,\r\n" + 
					"  genrit.eligibility , \r\n" + 
					"	genrit.genr_senction,\r\n" + 
					"	genrit.control_id from ((SELECT distinct   ro.id as rank_order,\r\n" + 
					"    t.id, t.sus_no, \r\n" + 
					"    obt.unit_name,\r\n" + 
					"    t.personal_no as ic_no,\r\n" + 
					"    t.name,\r\n" + 
					"    rk.description as rank,\r\n" + 
					"    CAST(t.date_of_birth as date) as dob,\r\n" + 
					"    CAST(t.date_of_commission as date) as doc,\r\n" + 
					"	CAST(re.date_of_retirement as date) as dor,\r\n" + 
					"    CASE WHEN age(CURRENT_DATE, t.date_of_commission) >= INTERVAL '8 years 5 months 15 days' THEN 'Yes' ELSE 'No' END AS eligibility , \r\n" + 
					"	COALESCE( ( SELECT status FROM tb_it_asset_genr_sanction_form  WHERE ic_no = t.personal_no and status !=3 ), -1) as genr_senction,\r\n" + 
					"	COALESCE((SELECT upd.control_id FROM tb_it_asset_personal_upload upd\r\n" + 
					"		    inner join tb_it_asset_genr_sanction_form f on f.batch_id = upd.batch_id  WHERE f.ic_no = t.personal_no  and f.status !=3), '0') as control_id   \r\n" + 
					"FROM\r\n" + 
					"     (select month,year,version,sus_no,status from tb_psg_iaff_3008_main_details where sus_no = ? and status = '1' order by id desc limit 1 ) m\r\n" + 
					"	 inner join tb_psg_iaff_3008_serving t on m.sus_no = t.sus_no and m.month=t.month and m.year=t.year and m.version=t.version\r\n" + 
					"   INNER JOIN tb_miso_orbat_unt_dtl obt \r\n" + 
					"        ON t.sus_no = obt.sus_no\r\n" + 
					"        AND obt.status_sus_no = 'Active'\r\n" + 
					"     INNER JOIN cue_tb_psg_rank_app_master rk \r\n" + 
					"        ON rk.description = t.rank\r\n" + 
					"				and upper(rk.level_in_hierarchy) = 'RANK'\r\n" + 
					"	left join tb_psg_iaff_3008_rank_wise_data ro \r\n" + 
					"	   on ro.rank_code = rk.code\r\n" + 
					"	   left join it_personel_assets_dor_view re on re.personnel_no = t.personal_no\r\n" + 
					"	   \r\n" + 
					"WHERE  t.status = '1' and  \r\n" + 
					"    t.sus_no = ?   and rk.id in ('12439','12440','12438','26243','12437','12436','12435','12434','26205','27180')  \r\n" + 
					"	AND age(CURRENT_DATE, t.date_of_commission) >= INTERVAL '8 years 5 months 15 days')\r\n" + 
					"	Union ALL\r\n" + 
					"	(SELECT distinct   ro.id as rank_order,\r\n" + 
					"    t.id, t.sus_no, \r\n" + 
					"    obt.unit_name,\r\n" + 
					"    t.personal_no as ic_no,\r\n" + 
					"    t.name,\r\n" + 
					"    rk.description as rank,\r\n" + 
					"    CAST(t.date_of_birth as date) as dob,\r\n" + 
					"    CAST(t.date_of_commission as date) as doc,\r\n" + 
					"	CAST(re.date_of_retirement as date) as dor,\r\n" + 
					"    CASE WHEN age(CURRENT_DATE, t.date_of_commission) >= INTERVAL '8 years 5 months 15 days' THEN 'Yes' ELSE 'No' END AS eligibility , \r\n" + 
					"	COALESCE( ( SELECT status FROM tb_it_asset_genr_sanction_form  WHERE ic_no = t.personal_no and status !=3 ), -1) as genr_senction,\r\n" + 
					"	COALESCE((SELECT upd.control_id FROM tb_it_asset_personal_upload upd\r\n" + 
					"		    inner join tb_it_asset_genr_sanction_form f on f.batch_id = upd.batch_id  WHERE f.ic_no = t.personal_no  and f.status !=3), '0') as control_id   \r\n" + 
					"FROM\r\n" + 
					"     (select month,year,version,sus_no,status from tb_psg_iaff_3008_main_details where sus_no = ? and status = '1' order by id desc limit 1 ) m\r\n" + 
					"	 inner join tb_psg_iaff_3008_supernumerary t on m.sus_no = t.sus_no and m.month=t.month and m.year=t.year and m.version=t.version\r\n" + 
					"   INNER JOIN tb_miso_orbat_unt_dtl obt \r\n" + 
					"        ON t.sus_no = obt.sus_no\r\n" + 
					"        AND obt.status_sus_no = 'Active'\r\n" + 
					"     INNER JOIN cue_tb_psg_rank_app_master rk \r\n" + 
					"        ON rk.description = t.rank\r\n" + 
					"				and upper(rk.level_in_hierarchy) = 'RANK'\r\n" + 
					"	left join tb_psg_iaff_3008_rank_wise_data ro \r\n" + 
					"	   on ro.rank_code = rk.code\r\n" + 
					"	   left join it_personel_assets_dor_view re on re.personnel_no = t.personal_no\r\n" + 
					"WHERE  t.status = '1' and  \r\n" + 
					"    t.sus_no = ?   and rk.id in ('12439','12440','12438','26243','12437','12436','12435','12434','26205','27180')  \r\n" + 
					"	AND age(CURRENT_DATE, t.date_of_commission) >= INTERVAL '8 years 5 months 15 days') ) AS genrit order by genr_senction,rank_order,doc asc";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, roleSusNo);
			stmt.setString(4, roleSusNo);
			System.err.println("genr it pers list : \n"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("ic_no"));// 2
				list.add(rs.getString("name"));// 3
				list.add(rs.getString("rank"));// 4
				list.add(rs.getString("dob"));// 5
				list.add(rs.getString("doc"));// 6
				list.add(rs.getString("dor"));// 7
				list.add(rs.getString("eligibility")); // 8
				list.add(rs.getString("sus_no")); // 9

				Integer genrsanctionform = rs.getInt("genr_senction");
				String control_id = rs.getString("control_id");
				if (genrsanctionform == 0) {
					list.add("Done");

				} else if (genrsanctionform == 1 && control_id.equals("0"))  {
					list.add("Under Process");
				}else if (!control_id.equals("0")) {
					list.add("Sanction Done");
				}else {
					String addButton = "<button type=\"button\" class=\"btn btn-primary btn-sm add-to-recommended\">Add</button>"; // 10
					list.add(addButton);
				}

				aList.add(list);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	@Override
	@Transactional
	public boolean saveItAssetGenerList(List<TB_IT_ASSET_GENR_SANCTION_FORM> itAssetGenerList, String username) {
		String sql = "INSERT INTO tb_it_asset_genr_sanction_form (unit_name, ic_no, name, rank, dob, doc, dor, eligibility, type_of_asset,sus_no,created_by,created_date,batch_id ,status ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,now()::timestamp,?,?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			for (TB_IT_ASSET_GENR_SANCTION_FORM itAsset : itAssetGenerList) {
				preparedStatement.setString(1, itAsset.getUnit_name());
				preparedStatement.setString(2, itAsset.getIc_no());
				preparedStatement.setString(3, itAsset.getName());
				preparedStatement.setString(4, itAsset.getRank());
				preparedStatement.setString(5, itAsset.getDob());
				preparedStatement.setString(6, itAsset.getDoc());
				preparedStatement.setString(7, itAsset.getDor());
				preparedStatement.setString(8, itAsset.getEligibility());
				preparedStatement.setString(9, itAsset.getType_of_asset());
				preparedStatement.setString(10, itAsset.getSus_no());
				preparedStatement.setString(11, username);
				preparedStatement.setString(12, itAsset.getBatch_id());
				preparedStatement.setInt(13, 0);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	

	@Override
	public List<TB_IT_ASSET_GENR_SANCTION_FORM> searchItAssetSanctionfomList(String batchId) {
		List<TB_IT_ASSET_GENR_SANCTION_FORM> itAssetGenerList = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "SELECT unit_name, ic_no, name, rank, dob, doc, dor, eligibility, type_of_asset,sus_no,batch_id,status "
					+ " FROM tb_it_asset_genr_sanction_form where batch_id=? order by status asc";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, batchId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TB_IT_ASSET_GENR_SANCTION_FORM itAsset = new TB_IT_ASSET_GENR_SANCTION_FORM();
				itAsset.setUnit_name(rs.getString("unit_name"));
				itAsset.setIc_no(rs.getString("ic_no"));
				itAsset.setName(rs.getString("name"));
				itAsset.setRank(rs.getString("rank"));
				itAsset.setDob(rs.getString("dob"));
				itAsset.setDoc(rs.getString("doc"));
				itAsset.setDor(rs.getString("dor"));
				itAsset.setEligibility(rs.getString("eligibility"));
				itAsset.setType_of_asset(rs.getString("type_of_asset"));
				itAsset.setSus_no(rs.getString("sus_no"));
				itAsset.setBatch_id(rs.getString("batch_id"));
				itAsset.setStatus(rs.getInt("status"));
				itAssetGenerList.add(itAsset);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return itAssetGenerList;
	}

	@Override
	public ArrayList<ArrayList<String>> personalGenrSanctionData(String batchId) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		String qry = "";

		int total = 0;
		String q = null;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			if(batchId != null && !batchId.isEmpty()){
				q="select ic_no,rank,name,dob,doc,dor,eligibility,conv_no,ltrim(TO_CHAR(conv_date,'YYYY-MM-DD'),'0') as conv_date,type_of_asset from tb_it_asset_genr_sanction_form  where batch_id=?" ;
			}else{
				q="select ic_no,rank,name,dob,doc,dor,eligibility,conv_no,ltrim(TO_CHAR(conv_date,'YYYY-MM-DD'),'0') as conv_date,type_of_asset from tb_it_asset_genr_sanction_form" ;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			if(batchId != null && !batchId.isEmpty()){
				stmt.setString(1,batchId);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("Personal Genr --> " + stmt);

			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i)); // 0
				list_Search.add(rs.getString("ic_no")); // 1
				list_Search.add(rs.getString("rank")); // 2
				list_Search.add(rs.getString("name")); // 3
				list_Search.add(rs.getString("dob")); // 4
				list_Search.add(rs.getString("doc")); // 5
				list_Search.add(rs.getString("dor")); // 6
				list_Search.add(rs.getString("eligibility")); // 7
				list_Search.add(rs.getString("conv_no")); // 8
				list_Search.add(rs.getString("conv_date")); // 9
				list_Search.add(rs.getString("type_of_asset")); // 10
				list_Search.add(""); // 11
				alist.add(list_Search);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.exit(0);
		}

		return alist;

	}


	@Override
	public boolean updateConvDetails(String batchId, String convNo, Date convDate) {
		String sql = "update tb_it_asset_genr_sanction_form set conv_no=?, conv_date=? where batch_id=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, convNo);
			preparedStatement.setDate(2, new Date(convDate.getTime()));
			preparedStatement.setString(3, batchId);

			int rowsUpdated = preparedStatement.executeUpdate();
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteItAssetSanctionForm(String personnelNo, String batchId ) {

		String sql = "delete from  tb_it_asset_genr_sanction_form  where ic_no=? and batch_id=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, personnelNo);
			preparedStatement.setString(2, batchId);
			int count = preparedStatement.executeUpdate();

			if(count >0 ) {
				return true;
			} else {
				return false;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


	}

	@Override
	public String getPdfPath(String batch_id) {
		String whr="";
		Connection conn = null;
		try {

			if(batch_id.equals("")) {
				batch_id = "";
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query="select senction_file,batch_id from tb_it_asset_personal_upload where batch_id=? ";
			stmt = conn.prepareStatement(query);

			stmt.setString(1,batch_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				whr=rs.getString("senction_file");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}



	



	@Override
	public boolean updatetypesStatus(String batchId) {
		String sql1 = "UPDATE tb_it_asset_genr_sanction_form SET status = 1 WHERE batch_id = ?";
		String sql2 = "UPDATE tb_it_asset_personal_upload\r\n"
				+ "SET\r\n"
				+ "    unit_deo_status = 1,\r\n"
				+ "    comd_status = sub.comd_status,\r\n"
				+ "    corps_status = sub.corps_status,\r\n"
				+ "		div_status = sub.div_status,\r\n"
				+ "    bde_status = sub.bde_status,\r\n"
				+ "		 unit_app_status = 0,\r\n"
				+ "		form_code_admin = sub.form_code_admin,\r\n"
				+ "		controlling_hq_type = sub.type_of_unit\r\n"
				+ "		 \r\n"
				+ "FROM\r\n"
				+ "    (\r\n"
				+ "\r\n"
				+ "SELECT\r\n"
				+ "    unit_sus,\r\n"
				+ "    form_code_admin,\r\n"
				+ "    type_of_unit,\r\n"
				+ "    CASE\r\n"
				+ "        WHEN applyhierarchy = 'No' THEN 'Not Applicable'\r\n"
				+ "        ELSE COALESCE(\r\n"
				+ "            CASE\r\n"
				+ "                 WHEN bde_sus IS NOT NULL THEN (-1)::TEXT\r\n"
				+ "            END,\r\n"
				+ "            'Not Applicable'\r\n"
				+ "        )\r\n"
				+ "    END AS bde_status,\r\n"
				+ "    CASE\r\n"
				+ "        WHEN applyhierarchy = 'No' THEN 'Not Applicable'\r\n"
				+ "        WHEN level_of_hierarchy = 'Brigade' THEN 'Not Applicable'\r\n"
				+ "        ELSE COALESCE(\r\n"
				+ "            CASE\r\n"
				+ "               WHEN div_sus IS NOT NULL THEN (-1)::TEXT\r\n"
				+ "            END,\r\n"
				+ "            'Not Applicable'\r\n"
				+ "        )\r\n"
				+ "    END AS div_status,\r\n"
				+ "    CASE\r\n"
				+ "        WHEN applyhierarchy = 'No' THEN 'Not Applicable'\r\n"
				+ "        WHEN level_of_hierarchy IN ('Brigade', 'Division') THEN 'Not Applicable'\r\n"
				+ "        ELSE COALESCE(\r\n"
				+ "            CASE\r\n"
				+ "                WHEN corps_sus IS NOT NULL THEN (-1)::TEXT\r\n"
				+ "            END,\r\n"
				+ "            'Not Applicable'\r\n"
				+ "        )\r\n"
				+ "    END AS corps_status,\r\n"
				+ "    CASE\r\n"
				+ "        WHEN applyhierarchy = 'No' THEN 'Not Applicable'\r\n"
				+ "        WHEN level_of_hierarchy IN ('Brigade', 'Division', 'Corps') THEN 'Not Applicable'\r\n"
				+ "        ELSE COALESCE(\r\n"
				+ "            CASE\r\n"
				+ "              WHEN comd_sus IS NOT NULL THEN (-1)::TEXT\r\n"
				+ "            END,\r\n"
				+ "            'Not Applicable'\r\n"
				+ "        )\r\n"
				+ "    END AS comd_status\r\n"
				+ "\r\n"
				+ "FROM (\r\n"
				+ "    SELECT\r\n"
				+ "        unit_sus,\r\n"
				+ "        form_code_admin,\r\n"
				+ "        type_of_unit,\r\n"
				+ "        (SELECT applyhierarchy FROM tb_it_asset_typewise_role_master WHERE unit_type = type_of_unit) AS applyhierarchy,\r\n"
				+ "        (SELECT level_of_hierarchy FROM tb_it_asset_typewise_role_master WHERE unit_type = type_of_unit) AS level_of_hierarchy,\r\n"
				+ "        bde_sus,\r\n"
				+ "        div_sus,\r\n"
				+ "        corps_sus,\r\n"
				+ "        comd_sus\r\n"
				+ "    FROM\r\n"
				+ "        it_personel_assets_unit_dtl_mat\r\n"
				+ ") AS a)sub\r\n"
				+ "WHERE tb_it_asset_personal_upload.unit_sus_no = sub.unit_sus  and batch_id = ?";

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
				ps1.setString(1, batchId);
				ps1.executeUpdate();
			}

			try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
				ps2.setString(1, batchId);
				ps2.executeUpdate();
			}

			connection.commit();
			return true;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public boolean deleteStatus(String batchId, String u_file) {
		String sql1 = "";
		String sql2 = "DELETE FROM tb_it_asset_genr_sanction_form WHERE batch_id = ?";

		if(u_file != null) {
			sql1 = "DELETE FROM tb_it_asset_personal_upload WHERE batch_id = ? ";
		}

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
				ps2.setString(1, batchId);
				ps2.executeUpdate();
			}

			if(u_file != null) {
				try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
					ps1.setString(1, batchId);
					ps1.executeUpdate();
				}
			}

			connection.commit();
			return true;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public String generateBatchId(String roleSusNo) {
		Connection conn = null;
		String batchId = "0";

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COALESCE(MAX(batch_id), '0') " +
					"FROM tb_it_asset_genr_sanction_form " +
					"WHERE sus_no = ? and LEFT(batch_id, 10) = TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY')";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				batchId = rs.getString(1);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return batchId;
	}




	@Override
	public List<Map<String, Object>> getBatchIdWisePdfData(int startPage,int pageLength,String Search,String orderColunm,String orderType,String roleSusNo,
			String status,String batch_id, HttpSession session) {

		String SearchValue= "";
		String qry = "left join tb_it_asset_personal_upload p";

		if(!batch_id.equals("")) {
			SearchValue += " and batch_id = ? ";
		}

		if(status.equals("0")) {
			qry="left join tb_it_asset_personal_upload p";
		}else if (status.equals("1")) {
			qry="inner join tb_it_asset_personal_upload p";
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";

		try{
			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;

			q = "select distinct p.id,g.batch_id,p.senction_file from tb_it_asset_genr_sanction_form g\r\n"
					+qry+"\r\n"
					+ "on p.batch_id = g.batch_id where  g.sus_no = ? and CAST(status as character varying) = ? " + SearchValue
					+ " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo);
			stmt.setString(2, status);

			int flag = 3;
			if (!batch_id.equals("")) {
				flag += 1;
				stmt.setString(flag, batch_id);
			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("Upload table Query --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int rowIndex = 0;
			while (rs.next()) {

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				}

				String action = "";
				String colUploadPdf = "";
				String colViewPdf = "";
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSubAccess = session.getAttribute("roleSubAccess").toString();

				if(status.equals("0")) {
					colUploadPdf = "<input type=\"file\" id=\"u_file_" + rowIndex + "\" style=\"width: 80%; display: inline-block;\" class=\"form-control file-upload-input\" accept=\".pdf\"/>\r\n"
							+ " <input type=\"hidden\" id=\"batchIdUpload_" + rowIndex + "\" value=\""+rs.getString("batch_id")+"\" class=\"batch-id-input\"/>\r\n"
							+ " <button type=\"button\" class=\"btn btn-success  uploadFileButton\" style=\"margin-left: 10px;\" data-index=\"" + rowIndex + "\">\r\n"
							+ " <i class=\"fa fa-upload\"></i></button> ";

					action = "<button type=\"button\" class=\"btn btn-primary btn-sm save_per_button\" data-index=\"" + rowIndex + "\">Save\r\n"
							+ " </button>\r\n"
							+ "<button type=\"button\" class=\"btn btn-danger btn-sm delete_per_button\" data-index=\"" + rowIndex + "\">Delete</button>";


				} else {
					int lastSlashIndex = rs.getString("senction_file").lastIndexOf('/');
					String TempPDF = rs.getString("senction_file").substring(lastSlashIndex + 1);
					colUploadPdf = "<span>"+TempPDF+"</span>";
				}

				if(rs.getString("senction_file") == null) {
					colViewPdf = "<span style=\"color: red;\">PDF not yet uploaded</span>";
					action = "<button type=\"button\" class=\"btn btn-primary btn-sm save_per_button\" data-index=\"" + rowIndex + "\">Save\r\n"
							+ " </button>\r\n"
							+ "<button type=\"button\" class=\"btn btn-danger btn-sm delete_per_button\" data-index=\"" + rowIndex + "\">Delete</button>";


				} else {
					String View = "onclick=\" viewData('" + rs.getString("batch_id") + "','" + rs.getString("id") + "')\"";
					colViewPdf = "<span class=\"view-icon\" id=\"viewPdf"+rowIndex+"\" title=\"View PDF\" style=\"font-size:20px;\" data-index=" + rowIndex + ">\r\n"
							+ "<i class=\"fa fa-file-pdf-o\" "+View+"></i>\r\n"
							+ "</span>";
				}

				columns.put("colUploadPdf", colUploadPdf);
				columns.put("colViewPdf", colViewPdf);
				columns.put("action", action);
				list.add(columns);
				rowIndex++;
			}

			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}




	@Override
	public long getBatchIdWisePdfCount(String Search,String orderColunm,String orderType, String roleSusNo, String status, String batch_id, HttpSession session)throws SQLException {

		String SearchValue= "";

		if(!batch_id.equals("")) {
			SearchValue += " and batch_id = ? ";
		}

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select \r\n" +
					" count(app.*)\r\n" +
					" from  (select distinct g.batch_id,p.senction_file from tb_it_asset_genr_sanction_form g\r\n"
					+ "left  join tb_it_asset_personal_upload p\r\n"
					+ "on p.batch_id = g.batch_id where  g.sus_no = ? and CAST(status as character varying) = ?  " + SearchValue + ") app " ;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo);
			stmt.setString(2, status);

			int flag = 3;
			if (!batch_id.equals("")) {
				flag += 1;
				stmt.setString(flag, batch_id);
			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("Upload table Count --> " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return total;
	}



	private void updateApprovalStatus(String batchId, String statusColumn, Session session, Transaction tx,String username) {
		String approve_by ="con_hq_approved_by" ;
		String approve_date ="con_hq_approved_date";


		if(statusColumn.equals("final_status")) {
			approve_by="con_hq_approved_by" ;
			approve_date ="con_hq_approved_date";
			
		}else if (statusColumn.equals("bde_status")) {
			approve_by="bde_approved_by" ;
			approve_date ="bde_approved_date";
		}else if (statusColumn.equals("div_status")) {
			approve_by="div_approved_by" ;
			approve_date ="div_approved_date";
		}else if (statusColumn.equals("corps_status")) {
			approve_by="corps_approved_by" ;
			approve_date ="corps_approved_date";
		}else if (statusColumn.equals("unit_app_status")) {
			approve_by="unit_approved_by" ;
			approve_date ="unit_approved_date";
		}


		try {

			if(session ==null && tx ==null) {
				Session newSession = HibernateUtil.getSessionFactory().openSession();
				Transaction newTx = newSession.beginTransaction();
				String hqlUpdate = "UPDATE TB_IT_ASEET_PERSONAL_UPLOAD SET " + statusColumn + " = :status,"+approve_by+" =:approve_by,"+approve_date+"=:approve_date WHERE batch_id = :id";
				Query query = newSession.createQuery(hqlUpdate);
				if (statusColumn.equals("bde_status") || statusColumn.equals("div_status") || statusColumn.equals("corps_status") || statusColumn.equals("comd_status")) {
					query.setParameter("status", "1");
				} else {
					query.setParameter("status", 1);
				}
				query.setParameter("id", batchId);
				query.setParameter("approve_by", username);
				query.setParameter("approve_date", new java.util.Date());
				int rowsAffected = query.executeUpdate();
				newTx.commit();
				if (rowsAffected <= 0) {
					throw new RuntimeException("Failed to update approval status for batch id " + batchId);
				}

				newSession.close();
			}else {
				String hqlUpdate = "UPDATE TB_IT_ASEET_PERSONAL_UPLOAD SET " + statusColumn + " = :status, "+approve_by+"=:approve_by, "+approve_date+"=:approve_date WHERE batch_id = :id";
				Query query = session.createQuery(hqlUpdate);
				if (statusColumn.equals("bde_status") || statusColumn.equals("div_status") || statusColumn.equals("corps_status") || statusColumn.equals("comd_status")) {
					query.setParameter("status", "1");
				} else {
					query.setParameter("status", 1);
				}

				query.setParameter("id", batchId);
				query.setParameter("approve_by", username);
				query.setParameter("approve_date", new java.util.Date());
				int rowsAffected = query.executeUpdate();


				if (rowsAffected <= 0) {
					throw new RuntimeException("Failed to update approval status for batch id " + batchId);
				}
			}

		} catch (Exception e) {

			throw new RuntimeException("Error updating approval status: " + e.getMessage(), e);
		}
	}



	@Override
	public List<Map<String, Object>> getSactionDetails(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct controlling_hq_type,ap.id,applyhierarchy,level_of_hierarchy,\r\n"
					+ "case when bde_status = 'Not Applicable' then FALSE\r\n"
					+ "else TRUE\r\n"
					+ "end as bde_view_status,\r\n"
					+ "case when div_status = 'Not Applicable' then FALSE\r\n"
					+ "else TRUE\r\n"
					+ "end as div_view_status,\r\n"
					+ "case when corps_status = 'Not Applicable' then FALSE\r\n"
					+ "else TRUE\r\n"
					+ "end as corps_view_status,\r\n"
					+ "case when comd_status = 'Not Applicable' then FALSE\r\n"
					+ "else TRUE\r\n"
					+ "end as comd_view_status,\r\n"
					+ "	CASE when ap.unit_app_status != 3 then ap.unit_app_status\r\n"
					+ "	ELSE\r\n"
					+ "	case when ap.unit_app_status = 3 and (ap.bde_status = '3' OR ap.div_status = '3' or ap.corps_status = '3' or ap.comd_status = '3' or ap.final_status = 3) then  1\r\n"
					+ "	  else ap.unit_app_status\r\n"
					+ "	END \r\n"
					+ "	END AS unit_app_status,\r\n"
					+ "	bde_status,\r\n"
					+ "	div_status,\r\n"
					+ "	corps_status,\r\n"
					+ "	final_status,\r\n"
					+ "	unit_deo_status,\r\n"
					+ "	 CASE \r\n"
					+ "        WHEN ap.created_by IS NULL OR ap.created_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.created_by \r\n"
					+ "    END AS created_by,\r\n"
					+ "		     CASE \r\n"
					+ "        WHEN ap.unit_approved_by IS NULL OR ap.unit_approved_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.unit_approved_by \r\n"
					+ "    END AS unit_approved_by,\r\n"
					+ "		 CASE \r\n"
					+ "        WHEN ap.bde_approved_by IS NULL OR ap.bde_approved_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.bde_approved_by \r\n"
					+ "    END AS bde_approved_by,\r\n"
					+ "		 CASE \r\n"
					+ "        WHEN ap.div_approved_by IS NULL OR ap.div_approved_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.div_approved_by \r\n"
					+ "    END AS div_approved_by,\r\n"
					+ "		 CASE \r\n"
					+ "        WHEN ap.corps_approved_by IS NULL OR ap.corps_approved_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.corps_approved_by \r\n"
					+ "    END AS corps_approved_by,\r\n"
					+ "			 CASE \r\n"
					+ "        WHEN ap.con_hq_approved_by IS NULL OR ap.con_hq_approved_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.con_hq_approved_by \r\n"
					+ "    END AS con_hq_approved_by,\r\n"
					+ "		 CASE \r\n"
					+ "        WHEN ap.rejected_by IS NULL OR ap.rejected_by = '' THEN ''\r\n"
					+ "        ELSE 'User : ' || ap.rejected_by \r\n"
					+ "    END AS rejected_by,\r\n"
					+ "		 CASE \r\n"
					+ "        WHEN ap.unit_approved_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.unit_approved_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS unit_approved_date,\r\n"
					+ "		CASE \r\n"
					+ "        WHEN ap.created_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.created_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS created_date,\r\n"
					+ "		CASE \r\n"
					+ "        WHEN ap.bde_approved_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.bde_approved_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS bde_approved_date,\r\n"
					+ "\r\n"
					+ " 		CASE \r\n"
					+ "        WHEN ap.div_approved_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.div_approved_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS div_approved_date,\r\n"
					+ "\r\n"
					+ "			CASE \r\n"
					+ "        WHEN ap.corps_approved_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.corps_approved_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS corps_approved_date,\r\n"
					+ "	\r\n"
					+ "			CASE \r\n"
					+ "        WHEN ap.con_hq_approved_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.con_hq_approved_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS con_hq_approved_date,\r\n"
					+ "			CASE \r\n"
					+ "        WHEN ap.rejected_date IS NULL THEN ''\r\n"
					+ "        ELSE 'On : ' || TO_CHAR(ap.rejected_date,'DD-Mon-YYYY' )\r\n"
					+ "    END AS rejected_date\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "from\r\n"
					+ "	tb_it_asset_personal_upload ap\r\n"
					+ "	inner join tb_it_asset_typewise_role_master ma\r\n"
					+ "	on ma.unit_type = controlling_hq_type\r\n"
					+ "	 where ap.id =?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Query-->" + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			return list;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}


	
	@Override
	public ArrayList<ArrayList<String>> getFinalSanctionList(String batchId) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();	
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();			
				q="select\r\n"
						+ "u.control_id,\r\n"
						+ "	u.batch_id,	\r\n"
						+ "	sf.unit_name,	sf.name,\r\n"
						+ "	TO_CHAR(u.con_hq_approved_date,'DD-Mon-YYYY') as sanction_date,\r\n"
						+ "	sf.ic_no,\r\n"
						+ "	sf.rank,\r\n"
						+ "	sf.type_of_asset,\r\n"
						+ "	sf.conv_no,\r\n"
						+ "		TO_CHAR(sf.conv_date,'DD-Mon-YYYY') as conv_date\r\n"
						+ "	\r\n"
						+ "from\r\n"
						+ "	tb_it_asset_genr_sanction_form sf\r\n"
						+ "	inner join tb_it_asset_personal_upload u on u.batch_id=sf.batch_id\r\n"
						+ "where\r\n"
						+ "	u.final_status=1\r\n"
						+ "	and sf.status=1 and u.batch_id = ?" ;
			

			PreparedStatement stmt = conn.prepareStatement(q);		
				stmt.setString(1,batchId);		
			ResultSet rs = stmt.executeQuery();		

			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i)); // 0
				list_Search.add(rs.getString("control_id")); // 1
				list_Search.add(rs.getString("batch_id")); // 2
				list_Search.add(rs.getString("unit_name")); // 3
				list_Search.add(rs.getString("sanction_date")); // 4
				list_Search.add(rs.getString("ic_no")); // 5
				list_Search.add(rs.getString("rank")); // 6
				list_Search.add(rs.getString("name")); // 6
				list_Search.add(rs.getString("type_of_asset")); // 7
				list_Search.add(rs.getString("conv_no")); // 8
				list_Search.add(rs.getString("conv_date")); // 9
				alist.add(list_Search);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.exit(0);
		}

		return alist;

	}

	
	  public List<List<String>> getSanctionAuthorityData(String batchId) {
	        List<List<String>> sanctionList = new ArrayList<>();
	        String sql = "select distinct\r\n"
	        		+ "	tr.personnel_no,\r\n"
	        		+ "	tr.name,\r\n"
	        		+ "	rk.description as rank,UPPER(\r\n"
	        		+ "		am.description\r\n"
	        		+ "	) as appointment\r\n"
	        		+ "from\r\n"
	        		+ "	logininformation l\r\n"
	        		+ "	inner join tb_psg_trans_proposed_comm_letter tr on tr.personnel_no=l.army_no\r\n"
	        		+ "	INNER JOIN cue_tb_psg_rank_app_master rk ON rk.id=tr.rank\r\n"
	        		+ "	and UPPER(\r\n"
	        		+ "		rk.level_in_hierarchy\r\n"
	        		+ "	)='RANK'\r\n"
	        		+ "	left join tb_psg_change_of_appointment ap\r\n"
	        		+ "	on ap.comm_id = tr.id\r\n"
	        		+ "	left join cue_tb_psg_rank_app_master am \r\n"
	        		+ "	on am.id = ap.appointment\r\n"
	        		+ "	and UPPER(\r\n"
	        		+ "		am.level_in_hierarchy\r\n"
	        		+ "	)='APPOINTMENT'\r\n"
	        		+ "	and am.parent_code='0'\r\n"
	        		+ "	and am.status_active='Active'\r\n"
	        		+ "where\r\n"
	        		+ " l.army_no in (select distinct final_sanctioning_icno from tb_it_asset_personal_upload where final_status = 1 and batch_id = ?)";

	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, batchId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    List<String> rowData = new ArrayList<>();
	                    rowData.add(rs.getString("personnel_no")); // 0
	                    rowData.add(rs.getString("name"));   // 1
	                    rowData.add(rs.getString("rank"));  // 2
	                    rowData.add(rs.getString("appointment")); // 3	                    
	                    sanctionList.add(rowData);
	                }
	            }

	        } catch (SQLException e) {  
	            e.printStackTrace();	           
	            return  new ArrayList<>();
	        }

	        return sanctionList;
	    }
	  
	  
	  
	  //manav
	  @Override
		public String genrApprovedataAction(String batchId, HttpSession session) {
			String msg = "";
			String roleAccess = (String) session.getAttribute("roleAccess");
			String roleType = (String) session.getAttribute("roleType");
			String role = (String) session.getAttribute("role");
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			String username = session.getAttribute("username").toString();
			String army_no = session.getAttribute("army_no").toString();

			String rolename = null;
			String  unit_type=null;
			String  applyhierarchy=null;
			String  level_of_hierarchy=null;
			String finalStatus = null;
			String statusColumn = null;

			Session session1 = null;
			Transaction tx = null;

			try {

				String getroleType= getRoleTypeInMaster(batchId);
				if (getroleType != null && !getroleType.isEmpty()) {				   
				    String[] roleTypes = getroleType.split(",");
				    rolename = roleTypes[0].trim();
				    unit_type = roleTypes[1].trim();
				    applyhierarchy=roleTypes[2].trim();
				    level_of_hierarchy=roleTypes[3].trim();
				    
				}
				
	

				if ("Unit".equals(roleAccess)) {
					statusColumn = "unit_app_status";
				} else if (rolename != null && rolename.equals(role) && applyhierarchy.equals("No") ) { 
					statusColumn = "final_status";
				} else if (roleAccess != null && roleAccess.equals("Formation")) { 
					if (roleSubAccess != null && roleSubAccess.equals("Command")) { 
						statusColumn = "comd_status";
					} else if (roleSubAccess != null && roleSubAccess.equals("Corps")) { 
						statusColumn = "corps_status";
					} else if (roleSubAccess != null && roleSubAccess.equals("Division")) { 
						statusColumn = "div_status";
					} else if (roleSubAccess != null && roleSubAccess.equals("Brigade")) { 
						statusColumn = "bde_status";
					}

				}

				if (statusColumn != null && !statusColumn.isEmpty()) { 
					session1 = HibernateUtil.getSessionFactory().openSession();
					tx = session1.beginTransaction();

					try {
						
						  updateApprovalStatus(batchId, statusColumn, session1, tx, username); 
						
						  if((applyhierarchy.equals("Yes") && level_of_hierarchy.equals(roleSubAccess))) {
							updateApprovalStatus(batchId, "final_status", session1, tx, username);
							}
						
						
						updateHierarchyStatus(batchId, session1, tx); 

						if((applyhierarchy.equals("Yes") && level_of_hierarchy.equals(roleSubAccess)) ||  (rolename != null && rolename.equals(role) && applyhierarchy.equals("No"))) {
							genrControlIdandUpdateAction(batchId, username, session1, tx); 
						}
						
					if (statusColumn.equals("final_status")||statusColumn.equals("comd_status")||statusColumn.equals("corps_status")) {

						String hqlUpdate = "update TB_IT_ASEET_PERSONAL_UPLOAD set final_sanctioning_icno=:army_no WHERE batch_id = :id";
						int app = session1.createQuery(hqlUpdate).setString("army_no", army_no).setString("id", batchId)
								.executeUpdate();

					}

						tx.commit();
					} catch (Exception e) {
						if (tx != null && tx.isActive()) {
							tx.rollback();
						}
						throw e; 
					}

					try (Connection conn1 = dataSource.getConnection();
							PreparedStatement stmt1 = conn1.prepareStatement(
									"SELECT final_status FROM tb_it_asset_personal_upload WHERE batch_id = ?")) {
						stmt1.setString(1, batchId);
						try (ResultSet rs = stmt1.executeQuery()) {
							if (rs.next()) {
								finalStatus = rs.getString("final_status");
							} else {
								msg = "Batch ID not found.";
								return msg;
							}
						}
					} catch (SQLException e) {
						msg = "SQL Exception when retrieving final_status: " + e.getMessage();
						return msg;
					}

					if (finalStatus != null && finalStatus.equals("0")) {  
						msg = "'R' to next app";
					} else {
						msg = "Approved Successfully";
					}

				} else {
					msg = "Not Approved Successfully";
				}

			} catch (RuntimeException e) { 
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				e.printStackTrace();
				msg = "Error during approval: " + e.getMessage();
			} finally {

				if (session1 != null && session1.isOpen()) {
					session1.close();
				}
			}
			return msg;
		}
		
		
		
		public String updateHierarchyStatus(String batchId,Session session, Transaction transaction) {

		    try {
		        String statusColumn = getStatus(batchId);

		        if (statusColumn == null || statusColumn.isEmpty()) {
		            return "tatus column is null or empty  ";
		        }

		    
		        try {		            

		            String hqlUpdate = "UPDATE TB_IT_ASEET_PERSONAL_UPLOAD SET " + statusColumn + " = :statusValue WHERE batch_id = :batchId";
		            Query query = session.createQuery(hqlUpdate);
		            query.setParameter("statusValue", "0");
		            query.setParameter("batchId", batchId);
		            query.executeUpdate();

		          //  transaction.commit(); 


		            return "Updated " + statusColumn + " for batch id " + batchId; 
		        } catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback(); 
		            }
		            return "Failed to update status: " + e.getMessage();
		        } finally {
		            session.close();
		        }

		    } catch (Exception e) {
		        return "Unexpected error: " + e.getMessage();
		    }
		}
		
		
		
		  public String getStatus(String batchId) {
			    String status = null;
			    String sql = "SELECT\r\n"
			        		+ "    CASE\r\n"
			        		+ "        WHEN bde_status = '-1' THEN 'bde_status'\r\n"
			        		+ "        WHEN div_status = '-1' THEN 'div_status' \r\n"
			        		+ "        WHEN corps_status ='-1' THEN 'corps_status'  \r\n"
			        		+ "        WHEN comd_status = '-1' THEN 'comd_status'  \r\n"
			        		+ "        ELSE NULL \r\n"
			        		+ "    END AS status_flag  \r\n"
			        		+ "FROM\r\n"
			        		+ "    tb_it_asset_personal_upload u\r\n"
			        		+ "WHERE\r\n"
			        		+ "    batch_id = ?";

			    try (Connection conn = dataSource.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {

			        stmt.setString(1, batchId);
			        try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			                status = rs.getString("status_flag");
			            }
			        }

			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null; 
			    }

			    return status;
			}
		  
		  
		  
		  public String getRoleTypeInMaster(String batchId) {
			    String roletype = null;
			    String sql = "select distinct a.role_name,a.unit_type,a.applyhierarchy,a.level_of_hierarchy from tb_it_asset_personal_upload u\r\n"
			    		+ "							inner join tb_it_asset_typewise_role_master a\r\n"
			    		+ "							on a.unit_type = u.controlling_hq_type \r\n"
			    		+ "							where u.batch_id =? ";

			    try (Connection conn = dataSource.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {

			        stmt.setString(1, batchId);
			        try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			            	String getRoleName = rs.getString("role_name");
			            	String getunitType= rs.getString("unit_type");
			            	String applyhierarchy= rs.getString("applyhierarchy");
			            	String level_of_hierarchy= rs.getString("level_of_hierarchy");
			            	roletype = getRoleName +","+getunitType + ","+applyhierarchy+","+level_of_hierarchy;
			            }
			        }

			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null; 
			    }

			    return roletype;
			}
		  
		  
			public void genrControlIdandUpdateAction(String batchId, String username, Session session, Transaction tx) {

				String icNo = "";
				String controlId = "";

				try {

					Query q = session.createQuery(
							"select distinct army_no from UserLogin where username = :username");
					q.setParameter("username", username);
					List<String> results = q.list();

					if (!results.isEmpty()) {
						icNo = results.get(0);
					}

					//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
					//			String currentDate = LocalDate.now().format(formatter);
					//			controlId = currentDate + "/" + username + "/" + icNo;

					controlId = batchId +"/"+icNo;



					String hqlUpdate = "UPDATE TB_IT_ASEET_PERSONAL_UPLOAD SET control_id = :controlId WHERE batch_id = :batchId";
					Query query = session.createQuery(hqlUpdate);
					query.setParameter("controlId", controlId);
					query.setParameter("batchId", batchId);

					int rowsAffected = query.executeUpdate();


					if (rowsAffected <= 0) {
						throw new RuntimeException("Failed to update control_id for batch id " + batchId);
					}
				} catch (Exception e) {

					throw new RuntimeException("Error updating control_id: " + e.getMessage(), e);
				}
			}
			
			
			
			@Override
			public List<Map<String, Object>> GenrItAssetsSearchData(int startPage,int pageLength,String Search,String orderColunm,String orderType,String unit_sus_no,
					String unit_name, String unit_status, String batch_id, HttpSession session) {

				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleType = session.getAttribute("roleType").toString();
				String roleSubAccess = session.getAttribute("roleSubAccess").toString();
				String role = session.getAttribute("role").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();

				String qry= "";

				if(!unit_sus_no.equals("")) {
					qry += " and u.unit_sus_no = ? ";
				}


				if(!batch_id.equals("")) {
					qry += " and u.batch_id = ? ";
				}

				if(roleAccess.equals("Unit")) {
					if(!unit_status.equals("")) {
						qry += " and CAST(u.unit_app_status as character varying) = ? and u.unit_deo_status = 1 ";
					}
				}
				
				
				Session ses1 = HibernateUtil.getSessionFactory().openSession();
				Transaction t2 = ses1.beginTransaction();		
				Query que = ses1.createQuery(
						"select distinct unit_type from TB_IT_ASSET_TYPEWISE_ROLE_MASTER where role_name=:role  and applyhierarchy='No'");
				que.setParameter("role", role);
				String roleTypeAssign = (String) que.uniqueResult();		
				    
				if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
				    	qry += "and  u.controlling_hq_type =? and u.unit_app_status =?  and  CAST(u.final_status as character varying) = ? ";	
				    }			
				
				
				String roleFormationNo = session.getAttribute("roleFormationNo").toString();
				String formation_code ="";
				if(roleAccess.equals("Formation")) {
					if(roleSubAccess.equals("Command")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0));
						qry += "  and u.unit_sus_no in (select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where  orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.comd_status=?";
					}

					if(roleSubAccess.equals("Corps")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =? and u.corps_status=?";
					
					}

					if(roleSubAccess.equals("Division")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.div_status=?";
				}

					if(roleSubAccess.equals("Brigade")) {
						formation_code = roleFormationNo;
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active')  and u.unit_app_status =? and u.bde_status=?";
				}

				}

			

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q="";

				try{
					String pageL = "";

					if(pageLength == -1){
						pageL = "ALL";
					}else {
						pageL = String.valueOf(pageLength);
					}
					conn = dataSource.getConnection();
					PreparedStatement stmt=null;

					q="SELECT DISTINCT \r\n"
							+ "    u.id,\r\n"
							+ "    u.batch_id,\r\n"
							+ "    s.unit_name,\r\n"
							+ "    s.conv_no,\r\n"
							+ "    s.conv_date,\r\n"
							+ "	   u.control_id,\r\n"
							+ "	   u.controlling_hq_type, u.final_status,\r\n"
							+ "  CASE\r\n"
							+ "    WHEN u.unit_deo_status = 0 THEN 'Pending at Unit DEO'\r\n"
							+ "    WHEN u.unit_deo_status = 3 THEN 'Rejected at Unit DEO'\r\n"
							+ "    WHEN u.unit_app_status = 0 THEN 'Pending at Unit App'\r\n"
							+ "      WHEN u.unit_app_status = 3 and u.bde_status != '3' and u.div_status != '3' and u.corps_status != '3' and u.comd_status != '3' and u.final_status != 3 THEN 'Rejected at Unit App'\r\n"
							+ "    WHEN u.bde_status = '0' THEN 'Pending at Brigade'\r\n"
							+ "    WHEN u.bde_status = '3' THEN 'Rejected at Brigade'\r\n"
							+ "    WHEN u.div_status = '0' THEN 'Pending at Division'\r\n"
							+ "    WHEN u.div_status = '3' THEN 'Rejected at Division'\r\n"
							+ "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Pending at Final Sanction'\r\n"
							+ "    WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Rejected at Final Sanction'\r\n"
							+ "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Pending at Corps'\r\n"
							+ "     WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Rejected at Corps'\r\n"
							+ "    WHEN u.comd_status = '0' THEN 'Pending at Final Sanction'\r\n"
							+ "     WHEN u.comd_status = '3' THEN 'Rejected at Final Sanction'\r\n"
							+ "    WHEN u.final_status = 0 THEN 'Pending at Final Sanction'\r\n"
							+ "    WHEN u.final_status = 3 THEN 'Rejected at Final Sanction'\r\n"
							+ "    ELSE 'Approved'\r\n"
							+ "END AS pending_status\r\n"
							+ "FROM \r\n"
							+ "    tb_it_asset_personal_upload u\r\n"
							+ "JOIN \r\n"
							+ "    tb_it_asset_genr_sanction_form s \r\n"
							+ "ON \r\n"
							+ "    u.batch_id = s.batch_id \r\n"
							+ "WHERE \r\n"
							+ "    u.id != 0 "+qry+
							" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
					stmt=conn.prepareStatement(q);

					int flag = 0;

					if (!unit_sus_no.equals("")) {
						flag += 1;
						stmt.setString(flag, unit_sus_no);
					}
					
					if(!batch_id.equals("")) {
						flag += 1;
						stmt.setString(flag, batch_id);
					}					

					if(roleAccess.equals("Unit")) {
						if (!unit_status.equals("")) {
							flag += 1;
							stmt.setString(flag, unit_status);
						}}
					if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
						flag += 1;
						stmt.setString(flag, roleTypeAssign);
				    	flag += 1;
						if(unit_status.equals("0") || unit_status.equals("1")) {
							stmt.setInt(flag, 1);
						}else if (unit_status.equals("3")) {
							stmt.setInt(flag, 3);
						}
				    	
				    	flag += 1;				    	
						stmt.setString(flag, unit_status);	
				    }


					if(roleAccess.equals("Formation")) {
						flag += 1;
						stmt.setString(flag, formation_code+"%");
					 	flag += 1;
						if(unit_status.equals("0") || unit_status.equals("1")) {
							stmt.setInt(flag, 1);
						}else if (unit_status.equals("3")) {
							stmt.setInt(flag, 3);
						}						
						flag += 1;
						stmt.setString(flag,unit_status);
					}

					ResultSet rs = stmt.executeQuery();
					System.out.println("Search Genr Data --> "+ stmt);
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
						for (int i = 1; i <= columnCount; i++) {
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

						}


						String f1 = "";
						String f2 = "";
						String f3 = "";
						String f4 = "";
						String f5 = "";
						String finalSanctionDownload="";
					
						String View = "onclick=\" viewData('" + rs.getString("batch_id") + "','" + rs.getString("id") + "')\"";
						f1 = "<span class=\"view-icon\" title=\"View PDF\" style=\"font-size:20px;\">\r\n"
								+ "<i class=\"fa fa-file-pdf-o\" "+View+"></i>\r\n"
								+ "</span>";
					
						String appText = "Are you sure, you want to \\'R\\' to next app?";
					

						if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
							appText = "Are you sure you want to Approve?";
						}

						String Approved = "onclick=\"  if (confirm('"+appText+"') ){approveData('"
								+ rs.getString("batch_id") + "')}else{ return false;}\"";
						f2 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

						String Reject = "onclick=\"  if (confirm('Are You Sure You Want To Reject This Entry ?') )"
								+ "{addRemarkModel('Reject', "+rs.getString("id")+")}else{ return false;}\"";
						f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

						String Download = "onclick=\"  if (confirm('Are You Sure You Want To Download This PDF?') ){downloadData("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						f4 = "<i class='fa fa-download action_icons action_download'  " + Download + " title='Download Data'></i>";
						String action="";

						String View1 = "onclick=\" viewDataPopUp('" + rs.getString("id")
						+ "')\"";
						f5 = "<span class=\"view-icon\" title=\"View Data\" style=\"font-size:20px;\">\r\n"
								+ "<i class=\"fa fa-eye\" " + View1 + "></i>\r\n" + "</span>";				
					
						if ((roleType.equals("APP")  || roleAccess.equals("Formation")) && unit_status.equals("0")) {
							action += f5 + f1 + f2 + f3 + f4;
						} else if (((roleType.equals("APP") || roleAccess.equals("Formation") ) && unit_status.equals("1")) || roleType.equals("DEO") ) {
							action += f5 + f1 + f4 ;
						} else {
							action += f5 + f1 + f4;
						}
						
						if( rs.getInt("final_status") == 1) {
							String downloadFinalSanction = "onclick=\"  if (confirm('Are You Sure You Want To Download Final Sanction PDF?') ){finalSanction('"
									+ rs.getString("batch_id") + "')}else{ return false;}\"";
						 finalSanctionDownload = "<i class='fa fa-download action_icons action_download'  " + downloadFinalSanction + " title='Download Final Sanction PDF'></i>";

						}
						columns.put("finalSanctionDownload", finalSanctionDownload);
						columns.put("action", action);
						list.add(columns);
					}

					rs.close();
					stmt.close();
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
				return list;
			}
			
			public long GenrItAssetsSearchCount(String Search, String orderColunm, String orderType, String unit_sus_no,
                    String unit_name, String unit_status, String batch_id, HttpSession session) {
            int total = 0;
            String roleAccess = session.getAttribute("roleAccess").toString();                        
            String roleSubAccess = session.getAttribute("roleSubAccess").toString();
            String role = session.getAttribute("role").toString();
            

            String qry= "";

            if(!unit_sus_no.equals("")) {
                    qry += " and u.unit_sus_no = ? ";
            }


            if(!batch_id.equals("")) {
                    qry += " and u.batch_id = ? ";
            }

            if(roleAccess.equals("Unit")) {
                    if(!unit_status.equals("")) {
                            qry += " and CAST(u.unit_app_status as character varying) = ? and u.unit_deo_status = 1 ";
                    }
            }
            
            
            Session ses1 = HibernateUtil.getSessionFactory().openSession();
            Transaction t2 = ses1.beginTransaction();                
            Query que = ses1.createQuery(
                            "select distinct unit_type from TB_IT_ASSET_TYPEWISE_ROLE_MASTER where role_name=:role  and applyhierarchy='No'");
            que.setParameter("role", role);
            String roleTypeAssign = (String) que.uniqueResult();                
                
            if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
                        qry += "and  u.controlling_hq_type =? and u.unit_app_status =?  and  CAST(u.final_status as character varying) = ? ";        
                }                        
            
            
            String roleFormationNo = session.getAttribute("roleFormationNo").toString();
            String formation_code ="";
            if(roleAccess.equals("Formation")) {
                    if(roleSubAccess.equals("Command")) {
                            formation_code = String.valueOf(roleFormationNo.charAt(0));
                            qry += "  and u.unit_sus_no in (select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
                                            + "        inner join tb_miso_orbat_codesform c\r\n"
                                            + "        on orb.sus_no = c.sus_no         AND orb.status_sus_no = 'Active'\r\n"
                                            + "        where  orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.comd_status=?";
                    }

                    if(roleSubAccess.equals("Corps")) {
                            formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                            qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
                                            + "        inner join tb_miso_orbat_codesform c\r\n"
                                            + "        on orb.sus_no = c.sus_no         AND orb.status_sus_no = 'Active'\r\n"
                                            + "        where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =? and u.corps_status=?";
                    
                    }

                    if(roleSubAccess.equals("Division")) {
                            formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                            qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
                                            + "        inner join tb_miso_orbat_codesform c\r\n"
                                            + "        on orb.sus_no = c.sus_no         AND orb.status_sus_no = 'Active'\r\n"
                                            + "        where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.div_status=?";
            }

                    if(roleSubAccess.equals("Brigade")) {
                            formation_code = roleFormationNo;
                            qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
                                            + "        inner join tb_miso_orbat_codesform c\r\n"
                                            + "        on orb.sus_no = c.sus_no         AND orb.status_sus_no = 'Active'\r\n"
                                            + "        where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active')  and u.unit_app_status =? and u.bde_status=?";
            }

            }

            Connection conn = null;
            String q="";

            try{                        
                    conn = dataSource.getConnection();
                    PreparedStatement stmt=null;

                    q="select count(a.*) from (  SELECT DISTINCT \r\n"
                                    + "    u.id,\r\n"
                                    + "    u.batch_id,\r\n"
                                    + "    s.unit_name,\r\n"
                                    + "    s.conv_no,\r\n"
                                    + "    s.conv_date,\r\n"
                                    + "           u.control_id,\r\n"
                                    + "           u.controlling_hq_type, u.final_status,\r\n"
                                    + "  CASE\r\n"
                                    + "    WHEN u.unit_deo_status = 0 THEN 'Pending at Unit DEO'\r\n"
                                    + "    WHEN u.unit_deo_status = 3 THEN 'Rejected at Unit DEO'\r\n"
                                    + "    WHEN u.unit_app_status = 0 THEN 'Pending at Unit App'\r\n"
                                    + "      WHEN u.unit_app_status = 3 and u.bde_status != '3' and u.div_status != '3' and u.corps_status != '3' and u.comd_status != '3' and u.final_status != 3 THEN 'Rejected at Unit App'\r\n"
                                    + "    WHEN u.bde_status = '0' THEN 'Pending at Brigade'\r\n"
                                    + "    WHEN u.bde_status = '3' THEN 'Rejected at Brigade'\r\n"
                                    + "    WHEN u.div_status = '0' THEN 'Pending at Division'\r\n"
                                    + "    WHEN u.div_status = '3' THEN 'Rejected at Division'\r\n"
                                    + "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Pending at Final Sanction'\r\n"
                                    + "    WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Rejected at Final Sanction'\r\n"
                                    + "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Pending at Corps'\r\n"
                                    + "     WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Rejected at Corps'\r\n"
                                    + "    WHEN u.comd_status = '0' THEN 'Pending at Final Sanction'\r\n"
                                    + "     WHEN u.comd_status = '3' THEN 'Rejected at Final Sanction'\r\n"
                                    + "    WHEN u.final_status = 0 THEN 'Pending at Final Sanction'\r\n"
                                    + "    WHEN u.final_status = 3 THEN 'Rejected at Final Sanction'\r\n"
                                    + "    ELSE 'Approved'\r\n"
                                    + "END AS pending_status\r\n"
                                    + "FROM \r\n"
                                    + "    tb_it_asset_personal_upload u\r\n"
                                    + "JOIN \r\n"
                                    + "    tb_it_asset_genr_sanction_form s \r\n"
                                    + "ON \r\n"
                                    + "    u.batch_id = s.batch_id \r\n"
                                    + "WHERE \r\n"
                                    + "    u.id != 0 "+qry+
                                    " )a ";
                    stmt=conn.prepareStatement(q);

                    int flag = 0;

                    if (!unit_sus_no.equals("")) {
                            flag += 1;
                            stmt.setString(flag, unit_sus_no);
                    }
                    
                    if(!batch_id.equals("")) {
                            flag += 1;
                            stmt.setString(flag, batch_id);
                    }                                        

                    if(roleAccess.equals("Unit")) {
                            if (!unit_status.equals("")) {
                                    flag += 1;
                                    stmt.setString(flag, unit_status);
                            }}
                    if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
                            flag += 1;
                            stmt.setString(flag, roleTypeAssign);
                        flag += 1;
                            if(unit_status.equals("0") || unit_status.equals("1")) {
                                    stmt.setInt(flag, 1);
                            }else if (unit_status.equals("3")) {
                                    stmt.setInt(flag, 3);
                            }
                        
                        flag += 1;                                            
                            stmt.setString(flag, unit_status);        
                }


                    if(roleAccess.equals("Formation")) {
                            flag += 1;
                            stmt.setString(flag, formation_code+"%");
                             flag += 1;
                            if(unit_status.equals("0") || unit_status.equals("1")) {
                                    stmt.setInt(flag, 1);
                            }else if (unit_status.equals("3")) {
                                    stmt.setInt(flag, 3);
                            }                                                
                            flag += 1;
                            stmt.setString(flag,unit_status);
                    }

                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                            total = rs.getInt(1);

                    
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
            }catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    if (conn != null) {
                            try {
                                    conn.close();
                            } catch (SQLException e) {
                            }
                    }
            }
            return total;
    }
    

			
			
			
			@Override
			public ArrayList<ArrayList<String>> CountTableData(String role, HttpSession session) {
				ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSubAccess = session.getAttribute("roleSubAccess").toString();
				String roleFormationNo = session.getAttribute("roleFormationNo").toString();
				String formation_code=null;
				String status ="";	
				String qry="";
				
		  		if(roleAccess.equals("Formation")) {
					if(roleSubAccess.equals("Command")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0));	
						status ="AND t.comd_status = '0'";
					}
					if(roleSubAccess.equals("Corps")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						status ="AND t.corps_status = '0'";
					}
					if(roleSubAccess.equals("Division")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						status ="AND t.div_status = '0'";
					}
	                   if(roleSubAccess.equals("Brigade")) {
						formation_code = roleFormationNo;
						status ="AND t.bde_status = '0'";
					}
	                   qry = "AND orb.form_code_admin LIKE ?";
				}					
			
				
				String q1 = "SELECT\r\n"
						+ "    COUNT(CASE WHEN t.id IS NOT NULL and (t.unit_app_status = 1 OR (t.unit_app_status = 3 AND t.final_status = 3 )  ) THEN 1 END) AS Received,\r\n"
						+ "    COUNT(CASE WHEN t.final_status = 1 THEN 1 END) AS Approved,\r\n"
						+ "    '' AS Recommendation,\r\n"
						+ "    SUM(CASE WHEN t.final_status = 0 and t.unit_app_status = 1 "+status+" THEN 1 ELSE 0 END) AS Pending,\r\n"
						+ "    COUNT(CASE WHEN t.final_status = 3 THEN 1 END) AS Rejected\r\n"
						+ "FROM tb_it_asset_personal_upload t\r\n"
						+ "INNER JOIN tb_it_asset_typewise_role_master m ON m.unit_type = t.controlling_hq_type\r\n"
						+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON t.unit_sus_no = orb.sus_no\r\n"
						+ "INNER JOIN tb_miso_orbat_codesform c ON orb.sus_no = c.sus_no\r\n"
						+ "WHERE m.role_name = ?\r\n"
						+ "  AND orb.status_sus_no = 'Active' "+qry+"";
						
					
				Connection conn = null;
				try{

					conn = dataSource.getConnection();
					PreparedStatement stmt=null;
					stmt=conn.prepareStatement(q1);
					stmt.setString(1,role);					
					if(roleAccess.equals("Formation")) {
						stmt.setString(2, formation_code+"%");
						}	
			
					System.err.println("data box count: \n\n"+stmt);
					ResultSet rs = stmt.executeQuery();		

					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("Received"));
						list.add(rs.getString("Approved"));
						list.add(rs.getString("Recommendation"));
						list.add(rs.getString("Pending"));
						list.add(rs.getString("Rejected"));

						aList.add(list);
					}

					rs.close();
					stmt.close();
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
				return aList;
			}
			
			@Override
			public  ArrayList<ArrayList<String>> GenrItAssetsExcelData(String unit_sus_no,
					String unit_name, String unit_status, String batch_id, HttpSession session){

				String roleAccess = session.getAttribute("roleAccess").toString();	
				String roleSubAccess = session.getAttribute("roleSubAccess").toString();
				String role = session.getAttribute("role").toString();

				String qry= "";

				if(!unit_sus_no.equals("")) {
					qry += " and u.unit_sus_no = ? ";
				}


				if(!batch_id.equals("")) {
					qry += " and u.batch_id = ? ";
				}

				if(roleAccess.equals("Unit")) {
					if(!unit_status.equals("")) {
						qry += " and CAST(u.unit_app_status as character varying) = ? and u.unit_deo_status = 1 ";
					}
				}
				
				
				Session ses1 = HibernateUtil.getSessionFactory().openSession();
				Transaction t2 = ses1.beginTransaction();		
				Query que = ses1.createQuery(
						"select distinct unit_type from TB_IT_ASSET_TYPEWISE_ROLE_MASTER where role_name=:role  and applyhierarchy='No'");
				que.setParameter("role", role);
				String roleTypeAssign = (String) que.uniqueResult();		
				    
				if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
				    	qry += "and  u.controlling_hq_type =? and u.unit_app_status =?  and  CAST(u.final_status as character varying) = ? ";	
				    }			
				
				
				String roleFormationNo = session.getAttribute("roleFormationNo").toString();
				String formation_code ="";
				if(roleAccess.equals("Formation")) {
					if(roleSubAccess.equals("Command")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0));
						qry += "  and u.unit_sus_no in (select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where  orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.comd_status=?";
					}

					if(roleSubAccess.equals("Corps")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =? and u.corps_status=?";
					
					}

					if(roleSubAccess.equals("Division")) {
						formation_code = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active') and u.unit_app_status =?  and u.div_status=?";
				}

					if(roleSubAccess.equals("Brigade")) {
						formation_code = roleFormationNo;
						qry += " and u.unit_sus_no in ( select orb.sus_no from tb_miso_orbat_unt_dtl  orb\r\n"
								+ "	inner join tb_miso_orbat_codesform c\r\n"
								+ "	on orb.sus_no = c.sus_no 	AND orb.status_sus_no = 'Active'\r\n"
								+ "	where orb.form_code_admin like ?  AND orb.status_sus_no = 'Active')  and u.unit_app_status =? and u.bde_status=?";
				}

				}

			

				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q="";

				try{			
				
					conn = dataSource.getConnection();
					PreparedStatement stmt=null;

					q="SELECT DISTINCT \r\n"
							+ "    u.id,\r\n"
							+ "    u.batch_id,\r\n"
							+ "    s.unit_name,\r\n"
							+ "    s.conv_no,\r\n"
							+ "    s.conv_date,\r\n"
							+ "	   u.control_id,\r\n"
							+ "	   u.controlling_hq_type, u.final_status,\r\n"
							+ "  CASE\r\n"
							+ "    WHEN u.unit_deo_status = 0 THEN 'Pending at Unit DEO'\r\n"
							+ "    WHEN u.unit_deo_status = 3 THEN 'Rejected at Unit DEO'\r\n"
							+ "    WHEN u.unit_app_status = 0 THEN 'Pending at Unit App'\r\n"
							+ "      WHEN u.unit_app_status = 3 and u.bde_status != '3' and u.div_status != '3' and u.corps_status != '3' and u.comd_status != '3' and u.final_status != 3 THEN 'Rejected at Unit App'\r\n"
							+ "    WHEN u.bde_status = '0' THEN 'Pending at Brigade'\r\n"
							+ "    WHEN u.bde_status = '3' THEN 'Rejected at Brigade'\r\n"
							+ "    WHEN u.div_status = '0' THEN 'Pending at Division'\r\n"
							+ "    WHEN u.div_status = '3' THEN 'Rejected at Division'\r\n"
							+ "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Pending at Final Sanction'\r\n"
							+ "    WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 3', 'Type 5A') THEN 'Rejected at Final Sanction'\r\n"
							+ "    WHEN u.corps_status = '0' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Pending at Corps'\r\n"
							+ "     WHEN u.corps_status = '3' AND u.controlling_hq_type IN ('Type 2', 'Type 5B') THEN 'Rejected at Corps'\r\n"
							+ "    WHEN u.comd_status = '0' THEN 'Pending at Final Sanction'\r\n"
							+ "     WHEN u.comd_status = '3' THEN 'Rejected at Final Sanction'\r\n"
							+ "    WHEN u.final_status = 0 THEN 'Pending at Final Sanction'\r\n"
							+ "    WHEN u.final_status = 3 THEN 'Rejected at Final Sanction'\r\n"
							+ "    ELSE 'Approved'\r\n"
							+ "END AS pending_status\r\n"
							+ "FROM \r\n"
							+ "    tb_it_asset_personal_upload u\r\n"
							+ "JOIN \r\n"
							+ "    tb_it_asset_genr_sanction_form s \r\n"
							+ "ON \r\n"
							+ "    u.batch_id = s.batch_id \r\n"
							+ "WHERE \r\n"
							+ "    u.id != 0 "+qry+ " ";
							
					stmt=conn.prepareStatement(q);

					int flag = 0;

					if (!unit_sus_no.equals("")) {
						flag += 1;
						stmt.setString(flag, unit_sus_no);
					}
					
					if(!batch_id.equals("")) {
						flag += 1;
						stmt.setString(flag, batch_id);
					}					

					if(roleAccess.equals("Unit")) {
						if (!unit_status.equals("")) {
							flag += 1;
							stmt.setString(flag, unit_status);
						}}
					if (roleTypeAssign != null && !roleTypeAssign.isEmpty()) {
						flag += 1;
						stmt.setString(flag, roleTypeAssign);
				    	flag += 1;
						if(unit_status.equals("0") || unit_status.equals("1")) {
							stmt.setInt(flag, 1);
						}else if (unit_status.equals("3")) {
							stmt.setInt(flag, 3);
						}
				    	
				    	flag += 1;				    	
						stmt.setString(flag, unit_status);	
				    }


					if(roleAccess.equals("Formation")) {
						flag += 1;
						stmt.setString(flag, formation_code+"%");
					 	flag += 1;
						if(unit_status.equals("0") || unit_status.equals("1")) {
							stmt.setInt(flag, 1);
						}else if (unit_status.equals("3")) {
							stmt.setInt(flag, 3);
						}						
						flag += 1;
						stmt.setString(flag,unit_status);
					}

					ResultSet rs = stmt.executeQuery();		

					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("batch_id"));//1
						list.add(rs.getString("control_id"));//2
						list.add(rs.getString("unit_name"));//3
						list.add(rs.getString("conv_no"));//4
						list.add(rs.getString("conv_date"));//5
						list.add(rs.getString("pending_status"));//6
						alist.add(list);
					}

					rs.close();
					stmt.close();
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
				return alist;
			}
			
			/*public List<List<String>> getSanctionAppPersNo(String batchId) {
		        List<List<String>> perslist = new ArrayList<>();
		        String sql = "select distinct\r\n"
		        		+ "	army_no\r\n"
		        		+ "	logininformation \r\n"
		        		+ "where\r\n"
		        		+ " username in (select distinct con_hq_approved_by from tb_it_asset_personal_upload where  batch_id = ?)";

		        try (Connection conn = dataSource.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setString(1, batchId);
		            try (ResultSet rs = stmt.executeQuery()) {
		                while (rs.next()) {
		                    List<String> rowData = new ArrayList<>();
		                    rowData.add(rs.getString("army_no")); // 0                   
		                    perslist.add(rowData);
		                }
		            }

		        } catch (SQLException e) {  
		            e.printStackTrace();	           
		            return  new ArrayList<>();
		        }

		        return perslist;
		    }*/


}