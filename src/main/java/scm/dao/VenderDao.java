package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import scm.model.Vender;
import scm.util.DBUtil_c;

public class VenderDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	public VenderDao() {}

	//查询所有供应商
	public List<Vender> selectAllVender() throws SQLException{
		List<Vender> venderlist= new ArrayList<Vender>();
		Vender vender = null;
		String sql = "select * from vender";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			String vendercode = rs.getString("vendercode");
			String name = rs.getString("name");
			String password = rs.getString("password");
			String contactor = rs.getString("contactor");
			String address = rs.getString("address");
			String postcode = rs.getString("postcode");
			String tel = rs.getString("tel");
			String fax = rs.getString("fax");
			String createdate = rs.getString("createdate");
			
			vender = new Vender(vendercode,name,password,contactor,address,postcode,tel,fax,createdate);
			venderlist.add(vender);
		}
		close(conn,pstat,stat,rs);
		return venderlist;
	}
	//校验供应商能否被删除，(有没有其他表)
	public boolean checkVenderCanDelete(String vendercode) throws SQLException {
		boolean flag = true;
		String sql = "select poid from pomain where vendercode = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, vendercode);
		rs = pstat.executeQuery();
		if(rs.next()) {
			flag = false;
		}
		close(conn,pstat,stat,rs);
		return flag;
	}
	//删除供应商
	public void venderDelete(String vendercode) throws SQLException {
		String sql = "delete from vender where vendercode = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, vendercode);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//修改供应商
	public void venderUpdate(String code,String password,String name,String contactor,String address,String postcode,
			String tel,String fax,String createDate) throws SQLException {
		String sql = "update vender set password=?,name=?,contactor=?,address=?,postcode=?,tel=?,fax=?,createDate=? where vendercode = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, password);
		pstat.setString(2, name);
		pstat.setString(3, contactor);
		pstat.setString(4, address);
		pstat.setString(5, postcode);
		pstat.setString(6, tel);
		pstat.setString(7, fax);
		pstat.setString(8, createDate);
		pstat.setString(9, code);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//新增供应商
	public void venderAdd(String code,String password,String name,String contactor,String address,String postcode,
			String tel,String fax,String createDate) throws SQLException {
		String sql = "insert into vender(vendercode,password,name,contactor,address,postcode,tel,fax,createDate) values(?,?,?,?,?,?,?,?,?)";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, code);
		pstat.setString(2, password);
		pstat.setString(3, name);
		pstat.setString(4, contactor);
		pstat.setString(5, address);
		pstat.setString(6, postcode);
		pstat.setString(7, tel);
		pstat.setString(8, fax);
		pstat.setString(9, createDate);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//查询供应商
	public List<Vender> selectSomeVender(String code,String name) throws SQLException{
		List<Vender> venderlist= new ArrayList<Vender>();
		Vender vender = null;
		String sql = "select * from vender where 1=1 ";
		if(code!=null && !"".equals(code)) {
			sql = sql+"and vendercode like '%"+code+"%'";
		}
		if(name!=null && !"".equals(name)) {
			sql = sql+"and name like '%"+name+"%'";
		}
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String vendercode = rs.getString("vendercode");
			String vendername = rs.getString("name");
			String password = rs.getString("password");
			String contactor = rs.getString("contactor");
			String address = rs.getString("address");
			String postcode = rs.getString("postcode");
			String tel = rs.getString("tel");
			String fax = rs.getString("fax");
			String createdate = rs.getString("createdate");
			
			vender = new Vender(vendercode,vendername,password,contactor,address,postcode,tel,fax,createdate);
			venderlist.add(vender);
		}
		close(conn,pstat,stat,rs);
		return venderlist;
	}
	//校验供应商编号是否存在
	public boolean checkVenderExist(String code) throws SQLException {
		boolean flag = true;
		String sql = "select password from vender where vendercode = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, code);
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
