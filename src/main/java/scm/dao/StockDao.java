package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import scm.util.DBUtil_c;

public class StockDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	// 增加库存
	public void addStock(String productCode,String UnitName,int num) throws Exception {
		String sql = "select num from stock where productCode = ? ";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, productCode);
		rs = pstat.executeQuery();
		
		int nums = 0;
		if(rs.next()) {
			nums = rs.getInt("num");
		}
		
		if(nums == 0) {
			//新增
			sql = "insert into stock(productCode,name,UnitName,num) values(?,'lisi',?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, productCode);
			pstat.setString(2, UnitName);
			pstat.setInt(3, num);
			pstat.executeUpdate();
		}else {
			//修改  添加
			sql = "update stock set num = ? where productCode = ? ";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, nums+num);
			pstat.setString(2, productCode);
			pstat.executeUpdate();
		}
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