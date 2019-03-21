package scm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import scm.model.Customer_c;
import scm.util.DBUtil_c;

public class CustomerDao_c {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	//查询所有获得显示数据
	public ArrayList<Customer_c> listCustomer() throws Exception {
		ArrayList<Customer_c> list = new ArrayList<>();
		String sql = "select * from customer;";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while(rs.next()) {
			Customer_c customer = new Customer_c();
			customer.setAddress(rs.getString("address"));
			customer.setContactor(rs.getString("contactor"));
			customer.setCreateDate(rs.getString("createDate"));
			customer.setCustomerCode(rs.getString("customerCode"));
			customer.setCustomerName(rs.getString("name"));
			customer.setFax(rs.getString("fax"));
			customer.setPassword(rs.getString("password"));
			customer.setPostcode(rs.getString("postcode"));
			customer.setTel(rs.getString("tel"));
			list.add(customer);
		}
		
		close(conn, pstat, pstat, rs);
		return list;
	}
	
	//删除
	public void deleteCustomer(String customerCode) throws Exception {
		String sql ="delete from customer where customerCode='"+customerCode+"';";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		close(conn, pstat, pstat, rs);
	}
	
	//修改
	public void updateCustomer(Customer_c customer) throws Exception {
		String sql = "update customer set name='"+customer.getCustomerName()+"',password='"+customer.getPassword()
				+"',contactor='"+customer.getContactor()+",address='"+customer.getAddress()+
				"',postcode='"+customer.getPostcode()+"',tel='"+customer.getTel()+"',fax='"+customer.getFax()+
				"',createDate='"+customer.getCreateDate()+"' "
				+ "where customerCode='"+customer.getCustomerCode()+"'";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		close(conn, pstat, pstat, rs);
	}
	
	//新增
	public void addCustomer(Customer_c customer) throws Exception {
		String sql = "insert into customer(name,password,contactor,address,postcode,tel,fax,createDate,customerCode) "
				+ "values('"+customer.getCustomerName()+"','"+customer.getPassword()+"','"+customer.getContactor()
				+"','"+customer.getAddress()+"','"+customer.getPostcode()+"','"+customer.getTel()+"','"
				+ customer.getFax()+"','"+customer.getCreateDate()+"','"+customer.getCustomerCode()+"');";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		close(conn, pstat, pstat, rs);
	}
	
	//模糊查询
	public List<Customer_c> selCustomer(String se) throws Exception {
		ArrayList<Customer_c> list = new ArrayList<>();
		String sql = "select distinct * from customer "
				+ "where customerCode like '%"+se+"%' or name like '%"+se+"%';";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			Customer_c customer = new Customer_c();
			customer.setCustomerCode(rs.getString("customerCode"));
			customer.setCustomerName(rs.getString("name"));
			list.add(customer);
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
