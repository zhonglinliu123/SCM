package scm.dao;

import java.sql.*;
import java.util.ArrayList;

import scm.model.Product_c;
import scm.util.DBUtil_c;

public class ProductDao_c {

	
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private PreparedStatement pstat;
	
	//查询有多少页
	public int getPages() throws Exception {
		int pages = 1;
		String sql = "select count(*) num from product";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		rs.next();
		int num = rs.getInt("num");
		close(conn, pstat, stat, rs);
		
		if((num%10 == 0) && (num != 0)) {
			pages = num/10;
		} else {
			pages = num/10 + 1;
		}
		
		return pages;
	}
	
	//查询全部
	public ArrayList<Product_c> listProduct() throws Exception {
		ArrayList<Product_c> list = new ArrayList<>();
		//product查询
		String sql = "select * from category c,product p where c.CategoryID=p.CategoryID;";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		
		//存入product数据
		rs = stat.executeQuery(sql);;
		while(rs.next()) {
			Product_c product = new Product_c();
			product.setCategoryID(rs.getInt("categoryID"));
			product.setCategoryName(rs.getString("c.name"));
			product.setCreateDate(rs.getString("createDate"));
			product.setPoNum(rs.getInt("poNum"));
			product.setPrice(rs.getShort("price"));
			product.setProductCode(rs.getString("productCode"));
			product.setProductName(rs.getString("p.name"));
			product.setRemark(rs.getString("p.remark"));
			product.setSoNum(rs.getInt("soNum"));
			product.setUnitName(rs.getString("unitName"));
			list.add(product);
		}
		
		close(conn, pstat, stat, rs);
		return list;
	}
	
	//新增，新增的时候产品类别必选
	public void addProduct(Product_c product) throws Exception {
		String sql = "insert into product(categoryID,createDate,poNum,price,productCode,name,"
				+ "remark,soNum,unitName) values(?,?,?,?,?,?,?,?,?)";
		
		String stocksql = "insert into stock(productCode,name,unitName,num) values(?,?,?,?)";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		
		pstat.setInt(1, product.getCategoryID());
		pstat.setString(2, product.getCreateDate());
		pstat.setInt(3, product.getPoNum());
		pstat.setFloat(4, product.getPrice());
		pstat.setString(5, product.getProductCode());
		pstat.setString(6, product.getProductName());
		pstat.setString(7, product.getRemark());
		pstat.setInt(8, product.getSoNum());
		pstat.setString(9, product.getUnitName());
		
		pstat.executeUpdate();
		
		pstat = conn.prepareStatement(stocksql);
		pstat.setString(1, product.getProductCode());
		pstat.setString(2, product.getProductName());
		pstat.setString(3, product.getUnitName());
		pstat.setInt(4, 0);
		
		pstat.executeUpdate();
		
		close(conn, pstat, stat, rs);
	}
	
	
	//删除
	public void deleteProduct(String productCode) throws Exception {
		String stocksql = "delete from stock where productCode='"+productCode+"'";
		String sql = "delete from product where productCode='"+productCode+"'";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(stocksql);
		stat.executeUpdate(sql);
		
		close(conn, pstat, stat, rs);
	}
	
	//修改
	public void updateProduct(Product_c product) throws Exception {
		String sql = "update product set categoryID="+product.getCategoryID()+",createDate='"+product.getCreateDate()
		+"',poNum="+product.getPoNum()+",price="+product.getPrice()+",name='"+product.getProductName()+"',remark='"
		+product.getRemark()+"',soNum="+product.getSoNum()+",unitName='"+product.getUnitName()+
		"' where productCode='"+product.getProductCode()+"';";
		
		String stocksql = "update stock set name='"+product.getProductName()
			+"',unitname='"+product.getUnitName()+"' where productCode='"+product.getProductCode()+"';";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(stocksql);
		stat.executeUpdate(sql);
		
		close(conn, pstat, stat, rs);
	}
	
	//模糊查询，根据条件过滤o	产品编号（模糊匹配）、产品名称（模糊匹配）、所属分类（下拉选择）
	public ArrayList<Product_c> typeProduct(String code, String name, String categoryName, int page) throws Exception {
		ArrayList<Product_c> list = new ArrayList<>();
		String sql = "select * from product p,category c where p.categoryID=c.categoryID ";
		
		if((code !=null) && !("".equals(code))) {
			sql += "and productCode like '%"+code+"%' ";
		}
		if((name !=null) && !("".equals(name))) {
			sql += "and p.Name like '%"+name+"%' ";
		}
		if((categoryName !=null) && !("".equals(categoryName))) {
			sql += "and c.Name='"+categoryName+"' ";
		}
		if(page != 0) {
			sql += "limit "+((page-1)*10)+","+10;
		}
		
		
		//System.out.println(sql);
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while(rs.next()) {
			Product_c pro = new Product_c();
			pro.setCategoryID(rs.getInt("categoryID"));
			pro.setCategoryName(rs.getString("c.name"));
			pro.setCreateDate(rs.getString("createDate"));
			pro.setPoNum(rs.getInt("poNum"));
			pro.setPrice(rs.getShort("price"));
			pro.setProductCode(rs.getString("productCode"));
			pro.setProductName(rs.getString("p.name"));
			pro.setRemark(rs.getString("p.remark"));
			pro.setSoNum(rs.getInt("soNum"));
			pro.setUnitName(rs.getString("unitName"));
			list.add(pro);
		}
		close(conn, pstat, stat, rs);
		return list;
	}
	
	//ajax验证
	public Product_c getProduct(String productCode) throws Exception {
		Product_c product = null;
		String sql = "select * from product where productCode='"+productCode+"'";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		if(rs.next()) {
			product = new Product_c();
			product.setProductCode(rs.getString("productCode"));
		}
		
		close(conn, pstat, stat, rs);
		
		return product;
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
