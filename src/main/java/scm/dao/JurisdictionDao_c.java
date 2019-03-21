package scm.dao;

import java.sql.*;
import java.util.ArrayList;

import scm.model.Jurisdiction_c;
import scm.util.DBUtil_c;

public class JurisdictionDao_c {

	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	
	//返回模块表中的信息
	public ArrayList<Jurisdiction_c> listJurisdiction() throws Exception {
		ArrayList<Jurisdiction_c> list = new ArrayList<>();
		String sql = "select * from systemmodel;";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			Jurisdiction_c jurisdiction = new Jurisdiction_c();
			jurisdiction.setModeCode(rs.getString("modecode"));
			jurisdiction.setModeName(rs.getString("modename"));
			list.add(jurisdiction);
		}
		
		close(conn, stat, rs);
		return list;
	}
	
	
	
	private void close(Connection conn, Statement stat, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
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
