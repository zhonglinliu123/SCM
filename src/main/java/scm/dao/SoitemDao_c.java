package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import scm.model.Soitem_c;
import scm.util.DBUtil_c;

public class SoitemDao_c {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	//查询明细
	public List<Soitem_c> listSoitem(String soid) throws Exception {
		List<Soitem_c> list = new ArrayList<>();
		String sql = "select * from soitem s,product p "
				+ "where p.productCode=s.productCode and s.soid='"+soid+"';";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while(rs.next()) {
			Soitem_c soitem = new Soitem_c();
			soitem.setItemPrice(rs.getInt("s.itemPrice"));
			soitem.setProductCode(rs.getString("s.productCode"));
			soitem.setProductNumber(rs.getInt("s.num"));
			soitem.setSoid(rs.getString("s.soid"));
			soitem.setUnitName(rs.getString("s.unitName"));
			soitem.setUnitPrice(rs.getShort("s.unitPrice"));
			soitem.setProductName(rs.getString("p.Name"));
			list.add(soitem);
		}
		
		close(conn, pstat, stat, rs);
		
		return list;
	}
	
	//删除明细
	public void deleteSoitem(String soid) throws Exception {
		String sql = "delete from soitem where soid='"+soid+"'";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
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
