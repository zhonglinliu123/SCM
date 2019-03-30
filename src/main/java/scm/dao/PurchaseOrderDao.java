package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import scm.model.PayType;
import scm.model.PurchaseOrderItem;
import scm.model.PurchaseOrderMain;
import scm.model.Status;
import scm.util.DBUtil_c;

public class PurchaseOrderDao {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	public PurchaseOrderDao() {}

	//查询所有采购单
	public List<PurchaseOrderMain> selectAllPurchaseOrder() throws SQLException{
		List<PurchaseOrderMain> purchaseOrderMainList= new ArrayList<PurchaseOrderMain>();
		PurchaseOrderMain purchaseOrderMain = null;
		String sql = "select poid,vendercode,account,createTime,tipfee,producttotal,pototal,paytype,prepayfee from pomain ";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			String poid = rs.getString("poid");
			String venderCode = rs.getString("venderCode");
			String createDate = rs.getString("createTime");
			String account = rs.getString("account");
			int tipFee= rs.getInt("tipFee");
			int productTotal= rs.getInt("productTotal");
			int poTotal= rs.getInt("poTotal");
			int prePayFee= rs.getInt("prePayFee");
			String payType = PayType.getDesc(rs.getInt("payType"));
			purchaseOrderMain = new PurchaseOrderMain(poid, venderCode, createDate, account, tipFee,
					 productTotal, poTotal, payType, prePayFee);
			purchaseOrderMainList.add(purchaseOrderMain);
		}
		
		for(int i=0;i<purchaseOrderMainList.size();i++) {
			String venderCode = purchaseOrderMainList.get(i).getVenderName();
			String venderName = getVenderName(venderCode);
			purchaseOrderMainList.get(i).setVenderName(venderName);
		}
		close(conn,pstat,stat,rs);
		return purchaseOrderMainList;
	}
	// 查询出可以了结的采购单
	public List<PurchaseOrderMain> selectEndPurchaseOrder() throws SQLException{
		List<PurchaseOrderMain> purchaseOrderMainList= new ArrayList<PurchaseOrderMain>();
		PurchaseOrderMain purchaseOrderMain = null;
		String sql = "select poid,vendercode,account,createTime,tipfee,producttotal,pototal,paytype,prepayfee,status from pomain ";
		conn = DBUtil_c.getConnection();
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()) {
			String poid = rs.getString("poid");
			String venderCode = rs.getString("venderCode");
			String createDate = rs.getString("createTime");
			String account = rs.getString("account");
			int tipFee= rs.getInt("tipFee");
			int productTotal= rs.getInt("productTotal");
			int poTotal= rs.getInt("poTotal");
			int prePayFee= rs.getInt("prePayFee");
			int status = rs.getInt("status");
			int paytype = rs.getInt("payType");
			boolean flag = false;
			if(paytype==1 && status==3) {
				flag = true;
			}else if(paytype==2 && status==2){
				flag = true;
			}else if(paytype==3 && status==3) {
				flag = true;
			}
			if(flag) {
				String statusstr = Status.getDesc(status);
				String payType = PayType.getDesc(paytype);
				purchaseOrderMain = new PurchaseOrderMain(poid, venderCode, createDate, account, tipFee,
						 productTotal, poTotal, payType, prePayFee,statusstr);
				purchaseOrderMainList.add(purchaseOrderMain);
			}
		}
		
		for(int i=0;i<purchaseOrderMainList.size();i++) {
			String venderCode = purchaseOrderMainList.get(i).getVenderName();
			String venderName = getVenderName(venderCode);
			purchaseOrderMainList.get(i).setVenderName(venderName);
		}
		close(conn,pstat,stat,rs);
		return purchaseOrderMainList;
	}
	//根据供应商编号vendercode得到供应商名称vendername
	public String getVenderName(String venderCode) throws SQLException {
		String name = null;
		String sql = "select name from vender where venderCode = ?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, venderCode);
		rs = pstat.executeQuery();
		if(rs.next()) {
		  name = rs.getString("name");
		}
		return name;
	}
	//根据采购单号得到采购单明细
	public  List<PurchaseOrderItem> selectPurchaseOrderItem(String poid) throws SQLException{
		List<PurchaseOrderItem> purchaseOrderItemList= new ArrayList<PurchaseOrderItem>();
		PurchaseOrderItem purchaseOrderItem = null;
		String sql = "select * from poitem where poid = ? ";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, poid);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String productCode = rs.getString("productCode");
			String productName = productCode;
			int num = rs.getInt("num");
			String unitName = rs.getString("unitName");
			int itemPrice = rs.getInt("itemPrice");
			int purchaseDetailsTotal = num*itemPrice;
			purchaseOrderItem = new PurchaseOrderItem(productCode, productName,num, unitName, itemPrice,
					 purchaseDetailsTotal);
			purchaseOrderItemList.add(purchaseOrderItem);
		}
		
		for(int i=0;i<purchaseOrderItemList.size();i++) {
			String productCode = purchaseOrderItemList.get(i).getProductCode();
			String productName = getproductName(productCode);
			purchaseOrderItemList.get(i).setProductName(productName);
		}
		close(conn,pstat,stat,rs);
		return purchaseOrderItemList;
	}
	//根据productCode得到productName
	public String getproductName(String productCode) throws SQLException {
		String name = null;
		String sql = "select name from product where productCode = ?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, productCode);
		rs = pstat.executeQuery();
		if(rs.next()) {
		  name = rs.getString("name");
		}
		return name;
	}
	//添加采购单
	public void addPurchaseOrder(String poid,String createTime,String venderCode,String account,
			int tipFee,int productTotal,String remark,int prePayFee,int paytype) throws SQLException {
		String sql = "insert into pomain(poid,venderCode,account,createTime,tipFee,productTotal,remark,prePayFee,paytype,pototal,Status) values(?,?,?,?,?,?,?,?,?,0,0)";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, poid);
		pstat.setString(2, venderCode);
		pstat.setString(3, account);
		pstat.setString(4, createTime);
		pstat.setInt(5, tipFee);
		pstat.setInt(6, productTotal);
		pstat.setString(7, remark);
		pstat.setInt(8, prePayFee);
		pstat.setInt(9, paytype);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//采购单了结
	public void endPurchaseOrder(String poid) throws SQLException {
		String sql = "update pomain set status=4 where poid=?";
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, poid);
		pstat.executeUpdate();
		close(conn,pstat,stat,rs);
	}
	//按条件查询采购单
	public List<PurchaseOrderMain> searchPurchaseOrder(String poids,String venderCodes,int payTypes,int status) throws SQLException{
		List<PurchaseOrderMain> purchaseOrderMainList= new ArrayList<PurchaseOrderMain>();
		PurchaseOrderMain purchaseOrderMain = null;
		String sql = "select poid,vendercode,account,createTime,tipfee,producttotal,pototal,paytype,prepayfee from pomain where 1=1 and payType = ? and status = ?";
		if(poids!=null && !"".equals(poids)) {
			sql = sql+" and poid like '%"+poids+"%'";
		}else if(venderCodes!=null && !"".equals(venderCodes)) {
			sql = sql+" and venderCode like '%"+venderCodes+"%'";
		}
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1, payTypes);
		pstat.setInt(2, status);
		rs = pstat.executeQuery();
		while(rs.next()) {
			String poid = rs.getString("poid");
			String venderCode = rs.getString("venderCode");
			String createDate = rs.getString("createTime");
			String account = rs.getString("account");
			int tipFee= rs.getInt("tipFee");
			int productTotal= rs.getInt("productTotal");
			int poTotal= rs.getInt("poTotal");
			int prePayFee= rs.getInt("prePayFee");
			String payType = PayType.getDesc(rs.getInt("payType"));
			purchaseOrderMain = new PurchaseOrderMain(poid, venderCode, createDate, account, tipFee,
					 productTotal, poTotal, payType, prePayFee);
			purchaseOrderMainList.add(purchaseOrderMain);
		}
		
		for(int i=0;i<purchaseOrderMainList.size();i++) {
			String venderCode = purchaseOrderMainList.get(i).getVenderName();
			String venderName = getVenderName(venderCode);
			purchaseOrderMainList.get(i).setVenderName(venderName);
		}
		close(conn,pstat,stat,rs);
		return purchaseOrderMainList;
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
