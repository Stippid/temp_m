package com.dao.itasset.WorkOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import com.models.assets.TB_IT_ASSET_GENR_SANCTION_FORM;
import com.models.assets.TB_IT_ASSET_TYPEWISE_ROLE_MASTER;

public class TypewiseRoleMaster_DAOImpl implements TypewiseRoleMaster_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<ArrayList<String>> getUnitType() {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = "select id, unit_type,role_name,level_of_hierarchy,applyhierarchy from tb_it_asset_typewise_role_master  order by id asc";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("unit_type"));// 1
				list.add(rs.getString("role_name"));// 2
				list.add(rs.getString("applyhierarchy"));// 3
				list.add(rs.getString("level_of_hierarchy"));// 4
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
    public boolean typeWiseMasterDataSave(List<TB_IT_ASSET_TYPEWISE_ROLE_MASTER> roleMasterList) {
        String sql = "UPDATE TB_IT_ASSET_TYPEWISE_ROLE_MASTER SET " +
                     "role_name = ?, " +
                     "applyhierarchy = ?, " +
                     "level_of_hierarchy = ?, " +
                     "modified_by = ?, " +
                     "modified_date = ?, " +
                     "status = ? " +
                     "WHERE unit_type = ?"; 

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (TB_IT_ASSET_TYPEWISE_ROLE_MASTER roleMaster : roleMasterList) {
                preparedStatement.setString(1, roleMaster.getRole_name());
                preparedStatement.setString(2, roleMaster.getApplyhierarchy());
                preparedStatement.setString(3, roleMaster.getLevel_of_hierarchy());
                preparedStatement.setString(4, roleMaster.getModified_by());
                preparedStatement.setDate(5, new java.sql.Date(roleMaster.getModified_date().getTime()));
                preparedStatement.setString(6, roleMaster.getStatus());
                preparedStatement.setString(7, roleMaster.getUnit_type());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	

}
