package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import scm.model.Product;
import scm.util.DBUtil_c;

public class ProductDao {
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private PreparedStatement pstat;
	
	public List<Product> selectAllProduct() throws Exception {
		List<Product> list = new ArrayList<Product>();
		String sql = "select productCode,name,poNum,soNum from product";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		Product p = null;
		while(rs.next()) {
			p = new Product();
			p.setProductCode(rs.getString("productCode"));
			p.setProductName(rs.getString("name"));
			p.setPoNum(rs.getInt("poNum"));
			p.setSoNum(rs.getInt("soNum"));
			list.add(p);
		}
		
		for(int i=0;i<list.size();i++) {
			list.get(i).setNowNum(getStock(list.get(i).getProductCode()));
		}
		close(conn, pstat, stat, rs);
		return list;
	}
	
	//根据productCode得到当前库存数
	public int getStock(String productCode) throws SQLException {
		String sql = "select num from stock where productCode = ? ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, productCode);
		rs = pstat.executeQuery();
		if(rs.next()) {
			return rs.getInt("num");
		}
		return 0;
	}
	
	//查询
	public List<Product> searchProduct(String productCode,String productName) throws SQLException{
		List<Product> list = new ArrayList<Product>();
		String sql = "select productCode,name,poNum,soNum from product where 1=1 ";
		if(productCode!=null && !"".equals(productCode)) {
			sql = sql+" and productCode like '%"+productCode+"%'";
		}
		if(productName!=null && !"".equals(productName)) {
			sql = sql+" and name like '%"+productName+"%'";
		}
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		Product p = null;
		while(rs.next()) {
			p = new Product();
			p.setProductCode(rs.getString("productCode"));
			p.setProductName(rs.getString("name"));
			p.setPoNum(rs.getInt("poNum"));
			p.setSoNum(rs.getInt("soNum"));
			list.add(p);
		}
		
		for(int i=0;i<list.size();i++) {
			list.get(i).setNowNum(getStock(list.get(i).getProductCode()));
		}
		close(conn, pstat, stat, rs);
		return list;
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
