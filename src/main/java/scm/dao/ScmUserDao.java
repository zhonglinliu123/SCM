package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	//查询所有用户
	public List<Scmuser> selectAllUser() throws SQLException{
		List<Scmuser> userlist= new ArrayList<Scmuser>();
		Scmuser user = null;
		String sql = "select * from scmuser ";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String account = rs.getString("account");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String createDate = rs.getString("createDate");
			String status = rs.getString("status");
			user = new Scmuser(account,password,name,createDate,status);
			userlist.add(user);
		}
		close(conn,pstat,stat,rs);
		return userlist;
	}
	//删除用户
	public void userDelete(String account) throws SQLException {
		String sql = "delete from scmuser where account = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//修改用户
	public void userUpdate(String account,String password,String name,String createDate,String status) throws SQLException {
		String sql = "update scmuser set password=?,name=?,createDate=?,status=? where account = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, password);
		pstat.setString(2, name);
		pstat.setString(3, createDate);
		pstat.setString(4, status);
		pstat.setString(5, account);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//新增用户
	public void userAdd(String account,String password,String name,String createDate,String status) throws SQLException {
		String sql = "insert into scmuser(account,password,name,createDate,status) values(?,?,?,?,?)";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.setString(2, password);
		pstat.setString(3, name);
		pstat.setString(4, createDate);
		pstat.setString(5, status);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//查询用户
	public List<Scmuser> selectSomeUser(String account,String password,String name,String createDate,String status) throws SQLException{
		List<Scmuser> userlist= new ArrayList<Scmuser>();
		Scmuser user = null;
		String sql = "select * from scmuser where 1=1 ";
		if(account!=null && !"".equals(account)) {
			sql = sql+"and account like '%"+account+"%'";
		}
		if(password!=null && !"".equals(password)) {
			sql = sql+"and password like '%"+password+"%'";
		}
		if(name!=null && !"".equals(name)) {
			sql = sql+"and name like '%"+name+"%'";
		}
		if(createDate!=null && !"".equals(createDate)) {
			sql = sql+"and createDate like '%"+createDate+"%'";
		}
		if(status!=null && !"".equals(status)) {
			sql = sql+"and status like '%"+status+"%'";
		}
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String newaccount = rs.getString("account");
			String newpassword = rs.getString("password");
			String newname = rs.getString("name");
			String newcreateDate = rs.getString("createDate");
			String newstatus = rs.getString("status");
			user = new Scmuser(newaccount,newpassword,newname,newcreateDate,newstatus);
			userlist.add(user);
		}
		close(conn,pstat,stat,rs);
		return userlist;
	}
	//校验账号是否存在
	public boolean checkUserExist(String account) throws SQLException {
		boolean flag = true;
		String sql = "select password from scmuser where account = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		rs = pstat.executeQuery();
		if(rs.next()) {
			flag = false;
		}else {
			flag = true;
		}
		close(conn,pstat,stat,rs);
		return flag;
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
