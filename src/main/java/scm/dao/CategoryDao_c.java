package scm.dao;

import java.sql.*;
import java.util.ArrayList;

import scm.model.Category_c;
import scm.util.DBUtil_c;

public class CategoryDao_c {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	
	//新增分类
	public void addCategory(Category_c category) throws Exception {
		//分类序号系自动分配，不可用户输入；名称name不能为空
		String sql = "insert into category(Name, Remark) values(?,?);";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, category.getName());
		pstat.setString(2, category.getRemark());
		pstat.executeUpdate();
		//关闭数据库连接
		close(conn, pstat, stat, rs);;
	}
	
	
	//删除分类
	public void deleteCategory(int ID) throws Exception {
		String sql ="delete from category where CategoryID=?;";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setLong(1, ID);
		pstat.executeUpdate();
		
		close(conn, pstat, stat, rs);
	}
	
	
	//修改分类
	public void updateCategory(Category_c category) throws Exception {
		//id系统自动分配，name不能为空
		String sql = "update category set name=?,remark=? where categoryID=?;";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, category.getName());
		pstat.setString(2, category.getRemark());
		pstat.setInt(3, category.getCategoryID());
		pstat.executeUpdate();
		
		close(conn, pstat, stat, rs);
		
	}
	
	//查询分类
	public ArrayList<Category_c> listCategory() throws Exception {
		ArrayList<Category_c> categorylist = new ArrayList<>();
		String sql = "select * from category;";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			Category_c category = new Category_c();
			category.setCategoryID(rs.getInt("categoryID"));
			category.setName(rs.getString("name"));
			category.setRemark(rs.getString("remark"));
			categorylist.add(category);
		}
		
		close(conn, pstat, stat, rs);
		return categorylist;
		
	}
	
	//判断是否重复
	public Category_c getCategory(String name) throws Exception {
		Category_c category = null;
		
		String sql = "select * from category where name='"+name+"';";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		if(rs.next()) {
			category = new Category_c();
			category.setCategoryID(rs.getInt("categoryID"));
			category.setName(rs.getString("name"));
			category.setRemark(rs.getString("remark"));
		}
		
		
		close(conn, pstat, stat, rs);
		
		return category;
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
