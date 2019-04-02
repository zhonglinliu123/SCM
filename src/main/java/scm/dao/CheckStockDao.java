package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import scm.util.DBUtil_c;

public class CheckStockDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 添加库存记录
	public void addStock(String productCode,int originNum,int realNum,String stockTime,String createUser,String description,String type) throws Exception {
		conn = DBUtil_c.getConnection();
		int stockId = getMaxStockid()+1;
		String sql = "insert into checkstock(stockID,productCode,OriginNum,RealNum,StockTime,createUser,Description,type) values(?,?,?,?,?,?,?,?)";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1, stockId);
		pstat.setString(2, productCode);
		pstat.setInt(3, originNum);
		pstat.setInt(4, realNum);
		pstat.setString(5, stockTime);
		pstat.setString(6, createUser);
		pstat.setString(7, description);
		pstat.setString(8, type);
		
		pstat.executeUpdate();
		close(conn, pstat, stat, rs);
	}
	
	// 查询最大的id
	public int getMaxStockid() throws SQLException {
		String sql = "select max(stockID) from checkstock";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		if(rs.next()) {
			return rs.getInt("max(stockID)");
		}
		return 0;
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
