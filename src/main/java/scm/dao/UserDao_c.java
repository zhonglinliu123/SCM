package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import scm.model.User_c;
import scm.util.DBUtil_c;

public class UserDao_c {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	//查询
	public User_c getUser(String account, String password) throws Exception {
		User_c user = null;
		String sql = "select * from scmuser u,usermodel um,systemmodel sm "
				+ "where u.account=um.account and um.modelCode=sm.modelCode "
				+ "and u.account='"+account+"' and u.password='"+password+"';";
		
		String usersql = "select * from scmuser where account='"+account+"' and password='"+password+"';";
		
		//System.out.println(sql);
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		if(rs.next()) {
			user = new User_c();
			user.setAccount(rs.getString("u.account"));
			user.setName(rs.getString("u.name"));
			user.setPassword(rs.getString("u.password"));
			user.setStatus(rs.getString("u.status"));
			user.setFilter(rs.getString("sm.modelname"));
		}
		
		if(user == null) {
			rs = stat.executeQuery(usersql);
			if(rs.next()) {
				user = new User_c();
				user.setAccount(rs.getString("account"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		}
		
		close(conn, pstat, stat, rs);
		return user;
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
