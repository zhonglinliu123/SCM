package scm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import scm.model.PurchaseOrderItem;
import scm.util.DBUtil_c;

public class PoitemDao {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	//查询明细
	public List<PurchaseOrderItem> listPoitem(String poid) throws Exception {
		List<PurchaseOrderItem> list = new ArrayList<PurchaseOrderItem>();
		String sql = "select * from poitem where poid = ? ";
		
		conn = DBUtil_c.getConnection();
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, poid);
		rs = pstat.executeQuery();
		
		PurchaseOrderItem poitem = null;
		while(rs.next()) {
			poitem = new PurchaseOrderItem();
			poitem.setPoid(poid);
			poitem.setProductCode(rs.getString("productCode"));
			poitem.setUnitName(rs.getString("UnitName"));
			poitem.setNum(rs.getInt("num"));
			list.add(poitem);
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