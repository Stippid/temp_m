package com.dao.scheduleLock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class scheduleLockDAOImpl implements scheduleLockDAO {

    private final int LOCK_TIMEOUT_SECONDS = 600; // 10 minutes

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

   @Override
    public String getServerIpAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e);
            return "Unknown Host";
        }
    }

    @Override
    public  boolean acquireLock(String lockName) {
        String serverIp = getServerIpAddress();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO distributed_locks (lock_name, locked_by, locked_at) VALUES (?, ?, ?)");
            stmt.setString(1, lockName);
            stmt.setString(2, serverIp);
            stmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (DataIntegrityViolationException e) {
            // Lock already exists, check if it's expired
            if (isLockExpired(lockName,conn)) {
                // update record if lock expired
                if (updateExpiredLock(lockName, serverIp,conn)) {
                   return true;
                }
            }
            System.out.println("Server " + serverIp + " can't acquire lock for " + lockName + " : " + e.getMessage());
            return false; 

        }catch (SQLException e){
            System.out.println("Error while getting connection from data source : "+ e.getMessage());
            return false;
        } finally {
                closeResources(conn, stmt, null);
        }
        return  false;
    }


    private boolean updateExpiredLock(String lockName, String serverIp, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "UPDATE distributed_locks SET locked_by = ?, locked_at = ? WHERE lock_name = ?");
            stmt.setString(1, serverIp);
            stmt.setTimestamp(2,new Timestamp(new Date().getTime()));
            stmt.setString(3, lockName);
           int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Server " + serverIp + " acquired expired lock for " + lockName);
                return true;
            } else {
                 System.out.println("Server "+ serverIp + " could not acquired expired lock for "+ lockName);
                return false;
            }
        }catch (SQLException e) {
             System.out.println("Error while updating lock  "+ e.getMessage());
              return false;
        }finally {
            closeResources(null, stmt, null);
        }
    }

    private boolean isLockExpired(String lockName, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           stmt = conn.prepareStatement("SELECT locked_at FROM distributed_locks WHERE lock_name = ?");
            stmt.setString(1, lockName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Timestamp lockedAt = rs.getTimestamp("locked_at");
                if (lockedAt == null) {
                  return true;
                }
                long timeDiffMillis = System.currentTimeMillis() - lockedAt.getTime();
                long timeDiffSeconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffMillis);
                return timeDiffSeconds > LOCK_TIMEOUT_SECONDS;
            }else{
                return  true; // This means no lock found
            }


        } catch (SQLException e) {
            System.out.println("Error while getting lock time from database "+ e.getMessage());
              return true; // Treat it as expired to acquire it
        } finally {
           closeResources(null, stmt, rs);
        }
    }

    @Override
    public  void releaseLock(String lockName) {
        Connection conn = null;
        PreparedStatement stmt = null;
       try {
           conn = dataSource.getConnection();
            stmt = conn.prepareStatement("DELETE FROM distributed_locks WHERE lock_name = ?");
            stmt.setString(1, lockName);
            stmt.executeUpdate();
             System.out.println("Server " + getServerIpAddress() + " release lock for " + lockName);
        } catch (SQLException e) {
           System.out.println("Error while releasing lock from database "+ e.getMessage());
        } finally {
          closeResources(conn,stmt,null);
        }
    }

    @Override
    public void resetLock() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
             stmt = conn.prepareStatement("DELETE FROM distributed_locks ");
            stmt.executeUpdate();
        }catch (SQLException e){
             System.out.println("Error while resetting lock data from database "+ e.getMessage());
        }finally {
            closeResources(conn,stmt,null);
        }

    }

    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
       try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Error while closing resources "+ e.getMessage());
        }
    }

}