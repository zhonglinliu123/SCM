package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import scm.util.DBUtil_c;

public class OutStockRecordDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	//添加出库记录
	public void addOutStockRecord(String productCode,int stockNum,String stockTime,String createUser) throws Exception {
		int stockID = 0;
		
		String sql = "select max(stockID) from outstockrecord";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		if(rs.next()) {
			stockID = rs.getInt("max(stockID)");
		}
		
		sql = "insert into outstockrecord(stockId,productCode,StockNum,StockTime,stocktype,CreateUser) values(?,?,?,?,2,?)";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1, stockID+1);
		pstat.setString(2, productCode);
		pstat.setInt(3, stockNum);
		pstat.setString(4, stockTime);
		pstat.setString(5, createUser);
		pstat.executeUpdate();
		
		close(conn, pstat, stat, rs);
		
	}



	private void close(Connection conn, PreparedStatement pstat, Statement stat, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstat != null) {
				pstat.close();
				pstat = null;
			}
			if(stat != null) {
				stat.close();
				stat = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
