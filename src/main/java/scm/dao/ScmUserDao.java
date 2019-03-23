package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import scm.model.Scmuser;
import scm.util.DBUtil_c;

public class ScmUserDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	public ScmUserDao() {}

	// 校验账号密码是否正确	
	public Scmuser checkUser(String account,String password) throws SQLException {
		Scmuser user = null;
		String sql = "select * from scmuser where account = ? and password = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.setString(2, password);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String name = rs.getString("name");
			String createDate = rs.getString("createDate");
			String status = rs.getString("status");
			user = new Scmuser(account,password,name,createDate,status);
		}
		close(conn,pstat,stat,rs);
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
