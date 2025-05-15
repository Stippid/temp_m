package com.dao.Emoluments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.persistance.util.HibernateUtil;

public class emolumentDAOImpl implements emolumentDAO{
	
private static final String Date = null;
private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public Boolean getPersonnelNoAlreadyExistemolu(String personnel_no) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select case when count(cv.*) > 0 then  false else true  end as result from \r\n" + 
					"\r\n" + 
					"tb_psg_emolument_parent_offcr cv  inner join \r\n" + 
					"tb_psg_trans_proposed_comm_letter pc on cv.comm_id=pc.id\r\n" + 
					"where pc.personnel_no = ? ";

			int id =0;
			
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
		
			stmt.setString(1, personnel_no);
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				msg = rs.getBoolean("result");
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
			return msg;
	}
		
}
