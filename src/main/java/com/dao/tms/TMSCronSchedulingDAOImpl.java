package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
public class TMSCronSchedulingDAOImpl implements TMSCronSchedulingDAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Map<String, Object>> get_TRANSCTION_TMS_BVEH_UE_UH_DATA() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select v.comd,\r\n" + 
					"	v.corps,\r\n" + 
					"	v.div,\r\n" + 
					"	v.bde,\r\n" + 
					"	v.sus_no,\r\n"+
					" 	v.unit_name,\r\n" + 
					"    v.prf_code,\r\n" + 
					"	v.group_name,\r\n" + 
					"	v.mct_main_id,\r\n" + 
					"	v.mct_nomen,\r\n" + 
					"    v.ue,\r\n" + 
					"    v.against_ue,\r\n" + 
					"    v.over_ue,\r\n" + 
					"    v.loan,\r\n" + 
					"    v.sec_store,\r\n" + 
					"    v.acsfp,\r\n" + 
					"    v.fresh_release,\r\n" + 
					"    v.total_uh,\r\n" + 
					"    v.gift,\r\n" + 
					"	case when (v.total_uh-v.ue) >= 0 then (v.total_uh-v.ue) else 0 end as surplus,\r\n" + 
					"	case when (v.total_uh-v.ue) < 0 then ABS(v.total_uh-v.ue) else 0 end as defi,\r\n" + 
					"	TO_CHAR(now(),'mm-yyyy') as mnt_yr\r\n" + 
					"	from\r\n" + 
					"	(SELECT \r\n" + 
					" 	ab.short_form as comd,\r\n" + 
					"	ac.coprs_name as corps,\r\n" + 
					"	ad.div_name as div,\r\n" + 
					"	ae.bde_name as bde,\r\n" + 
					" 	u.unit_name,\r\n" + 
					"	ms.sus_no,"+
					"   ms.prf_code,\r\n" + 
					"	prf.group_name,\r\n" + 
					"	ms.mct_main_id,\r\n" + 
					"	main.mct_nomen,\r\n" + 
					"    COALESCE(sum(ue.total), 0::numeric) AS ue,\r\n" + 
					"    COALESCE(sum(uh.against_ue), 0::numeric) AS against_ue,\r\n" + 
					"    COALESCE(sum(uh.over_ue), 0::numeric) AS over_ue,\r\n" + 
					"    COALESCE(sum(uh.loan), 0::numeric) AS loan,\r\n" + 
					"    COALESCE(sum(uh.sec_store), 0::numeric) AS sec_store,\r\n" + 
					"    COALESCE(sum(uh.acsfp), 0::numeric) AS acsfp,\r\n" + 
					"    COALESCE(sum(uh.fresh_release), 0::numeric) AS fresh_release,\r\n" + 
					"    COALESCE(sum(uh.total_uh), 0::numeric) AS total_uh,\r\n" + 
					"    COALESCE(sum(uh.gift), 0::numeric) AS gift\r\n" + 
					"   FROM ( SELECT p.sus_no,\r\n" + 
					"            p.mct_main_id,\r\n" + 
					"            m.prf_code,\r\n" + 
					"            u.form_code_control\r\n" + 
					"           	FROM ( 	SELECT part.sus_no,m_1.mct_main_id\r\n" + 
					"                   		FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					"                     		JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					"                     		JOIN tb_miso_orbat_unt_dtl u_1 ON u_1.status_sus_no::text = 'Active'::text AND part.sus_no::text = u_1.sus_no::text\r\n" + 
					"                     		JOIN tb_tms_mct_main_master m_1 ON m_1.mct_main_id = \"substring\"(d.mct::character varying::text, 1, 4) AND m_1.type_of_veh::text = 'B'::text\r\n" + 
					"                  		GROUP BY part.sus_no, m_1.mct_main_id, m_1.prf_code, u_1.form_code_control\r\n" + 
					"                	UNION ALL\r\n" + 
					"                 	SELECT ue_1.sus_no,ue_1.mct_no AS mct_main_id\r\n" + 
					"                   		FROM cue_transport_ue_final ue_1\r\n" + 
					"                     		JOIN tb_miso_orbat_unt_dtl u_1 ON u_1.status_sus_no::text = 'Active'::text AND ue_1.sus_no = u_1.sus_no::text\r\n" + 
					"                     		JOIN tb_tms_mct_main_master m_1 ON m_1.mct_main_id = ue_1.mct_no::text AND m_1.type_of_veh::text = 'B'::text\r\n" + 
					"                  		GROUP BY ue_1.sus_no, ue_1.mct_no, m_1.prf_code, u_1.form_code_control\r\n" + 
					"          			ORDER BY 1, 2) p\r\n" + 
					"             		JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND p.sus_no::text = u.sus_no::text\r\n" + 
					"             	LEFT JOIN tb_tms_mct_main_master m ON m.mct_main_id = p.mct_main_id\r\n" + 
					"          GROUP BY p.sus_no, p.mct_main_id, m.prf_code, u.form_code_control) ms\r\n" + 
					"     LEFT JOIN ( SELECT part.sus_no,\r\n" +
					"            m.mct_main_id,\r\n" +
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '5'::text) AS against_ue,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '3'::text) AS over_ue,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '0'::text) AS loan,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '9'::text) AS sec_store,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '10'::text) AS acsfp,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = 'F'::text) AS fresh_release,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '5'::text OR part.issue_type::text = '3'::text OR part.issue_type::text = '0'::text OR part.issue_type::text = '9'::text OR part.issue_type::text = '10'::text OR part.issue_type::text = 'F'::text) AS total_uh,\r\n" + 
					"            count(part.ba_no) FILTER (WHERE part.issue_type::text = '4'::text) AS gift\r\n" + 
					"           FROM tb_tms_mvcr_parta_dtl part\r\n" + 
					"             JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n" + 
					"             JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND part.sus_no::text = u.sus_no::text\r\n" + 
					"             JOIN tb_tms_mct_main_master m ON m.mct_main_id = \"substring\"(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'B'::text\r\n" + 
					"          GROUP BY part.sus_no, m.mct_main_id\r\n" + 
					"          ORDER BY part.sus_no) uh ON ms.sus_no::text = uh.sus_no::text AND ms.mct_main_id = uh.mct_main_id\r\n" + 
					"     LEFT JOIN ( SELECT ue_1.sus_no,\r\n" + 
					"            ue_1.mct_no,\r\n" + 
					"            sum(ue_1.total) AS total\r\n" + 
					"           FROM cue_transport_ue_final ue_1\r\n" + 
					"             JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND ue_1.sus_no = u.sus_no::text\r\n" + 
					"             JOIN tb_tms_mct_main_master m ON m.mct_main_id = ue_1.mct_no::text AND m.type_of_veh::text = 'B'::text\r\n" + 
					"          GROUP BY ue_1.sus_no, ue_1.mct_no\r\n" + 
					"          ORDER BY ue_1.sus_no) ue ON ms.sus_no::text = ue.sus_no AND ms.mct_main_id = ue.mct_no::text\r\n" + 
					"	JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND ms.sus_no::text = u.sus_no::text\r\n" + 
					"	LEFT JOIN orbat_view_cmd ab ON substr(ms.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
					"	LEFT JOIN orbat_view_corps ac ON substr(ms.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
					"	LEFT JOIN orbat_view_div ad ON substr(ms.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
					"	LEFT JOIN orbat_view_bde ae ON substr(ms.form_code_control::text, 7, 4)  = ae.bde_code::text\r\n" + 
					"	LEFT JOIN tb_tms_mct_slot_master prf ON prf.prf_code::text = ms.prf_code::text\r\n" + 
					"	LEFT JOIN tb_tms_mct_main_master main ON main.mct_main_id = ms.mct_main_id::text AND main.type_of_veh::text = 'B'::text\r\n" + 
					"  GROUP BY 1,2,3,4,5,6,7,8,9,10) v\r\n" + 
					"  WHERE v.total_uh != '0'::numeric OR v.ue != '0'::numeric";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
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
				}catch (SQLException e) {}
			}
		}
		return list;
	}
}