package scm.dao;

import java.sql.*;
import java.util.*;

import scm.model.Soitem_c;
import scm.model.SomainDTO_c;
import scm.model.Somain_c;
import scm.util.DBUtil_c;

public class SomainDTODao_c {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	//查询要显示的售货单信息
	public ArrayList<SomainDTO_c> listSomainDTO() throws Exception {
		ArrayList<SomainDTO_c> list = new ArrayList<>();
		String sql = "select * from somain s,customer c,scmuser u "
				+ "where s.customerCode=c.customerCode and u.account=s.account;";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			if(rs.getInt("status") != 4) {//查询没有了结的销售单
				SomainDTO_c somainDTO = new SomainDTO_c();
				somainDTO.setAccount(rs.getString("s.account"));
				somainDTO.setCreateTime(rs.getString("s.createTime"));
				somainDTO.setCustomerCode(rs.getString("s.customerCode"));
				somainDTO.setCustomerName(rs.getString("c.name"));
				somainDTO.setPayType(rs.getString("s.payType"));
				somainDTO.setPrePayFee(rs.getShort("s.prePayFee"));
				somainDTO.setProductTotal(rs.getFloat("s.productTotal"));
				somainDTO.setSoid(rs.getString("s.soid"));
				somainDTO.setSoTotal(rs.getFloat("s.soTotal"));
				somainDTO.setTipFee(rs.getFloat("s.tipFee"));
				somainDTO.setUserName(rs.getString("u.name"));
				somainDTO.setStatus(rs.getInt("status"));
				list.add(somainDTO);
			}
		}
		
		close(conn, pstat, stat, rs);
		
		
		return list;
	}
	
	
	//查询一个销售单
	public Somain_c getSomain(String soid) throws Exception {
		String sql = "select * from somain s,customer c,scmuser u "
				+ "where soid='"+soid+"' and s.customerCode=c.customerCode and u.account=s.account;";
		Somain_c somain = null;
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		if(rs.next()) {
			somain = new Somain_c();
			somain.setAccount(rs.getString("s.account"));
			somain.setCreateTime(rs.getString("s.createTime"));
			somain.setCustomerCode(rs.getString("s.customerCode"));
			somain.setCustomerName(rs.getString("c.name"));
			somain.setPayType(rs.getString("s.payType"));
			somain.setPrePayFee(rs.getShort("s.prePayFee"));
			somain.setProductTotal(rs.getFloat("s.productTotal"));
			somain.setSoid(rs.getString("s.soid"));
			somain.setSoTotal(rs.getFloat("s.soTotal"));
			somain.setTipFee(rs.getFloat("s.tipFee"));
			somain.setUserName(rs.getString("u.name"));
			somain.setEndTime(rs.getString("endTime"));
			somain.setEndUser(rs.getString("endUser"));
			somain.setPayTime(rs.getString("payTime"));
			somain.setPayUser(rs.getString("payUser"));
			somain.setPrePayTime(rs.getString("prePayTime"));
			somain.setPrePayUser(rs.getString("prePayUser"));
			somain.setStockTime(rs.getString("stockTime"));
			somain.setStockUser(rs.getString("stockUser"));
		}
		
		close(conn, pstat, stat, rs);
		
		return somain;
	}
	
	//新增售货单
	public void addSomain(SomainDTO_c somainDTO, List<Soitem_c> soitemList) throws Exception {
		//事物处理，销售单和明细一起添加
		try {
			conn = DBUtil_c.getConnection();
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			//somain是父表先添加数据
			String somainsql ="insert into somain(soid,customerCode,account,createTime,tipFee,productTotal,soTotal,payType,prepayFee,status) "
					+ "values('"+somainDTO.getSoid()+"','"+somainDTO.getCustomerCode()+"','"+somainDTO.getAccount()
					+"','"+somainDTO.getCreateTime()+"',"+somainDTO.getTipFee()+","+somainDTO.getProductTotal()
					+","+somainDTO.getSoTotal()+",'"+somainDTO.getPayType()+"',"+somainDTO.getPrePayFee()
					+","+somainDTO.getStatus()+");";
			//System.out.println(somainsql);
			stat.executeUpdate(somainsql);
			
			
			for(Soitem_c so:soitemList) {
				String soitemsql = "insert into soitem(soid,productCode,unitPrice,num,unitName,itemPrice) "
						+ "value('"+somainDTO.getSoid()+"','"+so.getProductCode()+"',"+so.getUnitPrice()
						+","+so.getProductNumber()+",'"+so.getUnitName()+"',"+so.getItemPrice()+");";
				
				stat.addBatch(soitemsql);
			
			}
			stat.executeBatch();
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
		}finally {
			close(conn, pstat, stat, rs);
		}
	}
	
	//删除销售单
	public void deleteSomain(String soid) throws Exception {
		String sql = "delete from somain where soid='"+soid+"'";
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		close(conn, pstat, stat, rs);
	}
	
	//模糊查询
	public List<SomainDTO_c> selectSomain(String soid, String dateStart, String dateEnd, String customerCode,
			String payType, String status) throws Exception {
		
		List<SomainDTO_c> list = new ArrayList<>();
		
		String sql = "select * from somain s,customer c,scmuser u "
				+ "where s.customerCode=c.customerCode and u.account=s.account "
				+ "and soid like '%"+soid+"%'";
				if(!("".equals(dateStart) || "".equals(dateEnd))) {
					sql +="and s.createTime between '"+dateStart+"' and '"+dateEnd+"'";
				}
				if(!("".equals(customerCode))) {
					sql += " and s.customerCode='"+customerCode+"'";
				}
				if(!("".equals(payType))) {
					sql += " and s.payType='"+payType+"'";
				}
				if(!("".equals(status))) {
					sql += " and s.status="+status;
				}
				
		
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			SomainDTO_c somainDTO = new SomainDTO_c();
			somainDTO.setAccount(rs.getString("s.account"));
			somainDTO.setCreateTime(rs.getString("s.createTime"));
			somainDTO.setCustomerCode(rs.getString("s.customerCode"));
			somainDTO.setCustomerName(rs.getString("c.name"));
			somainDTO.setPayType(rs.getString("s.payType"));
			somainDTO.setPrePayFee(rs.getShort("s.prePayFee"));
			somainDTO.setProductTotal(rs.getFloat("s.productTotal"));
			somainDTO.setSoid(rs.getString("s.soid"));
			somainDTO.setSoTotal(rs.getFloat("s.soTotal"));
			somainDTO.setTipFee(rs.getFloat("s.tipFee"));
			somainDTO.setUserName(rs.getString("u.name"));
			somainDTO.setStatus(rs.getInt("status"));
			list.add(somainDTO);
		}
		
		close(conn, pstat, stat, rs);
		
		return list;
	}
	
	
	//了结
	public void overSomain(String soid, String endTime, String endUser) throws Exception {
		String sql = "update somain set status=4,endTime='"+endTime+"',endUser='"+endUser+
				"' where soid='"+soid+"'";
		
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
