package scm.servlet.storage_manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.OutStockRecordDao;
import scm.dao.SellOrderDao;
import scm.dao.StockDao;
import scm.model.Scmuser;
import scm.model.SellOrderItem;
import scm.model.SellOrderMain;

@WebServlet("/OutStorageRecordServlet")
public class OutStorageRecordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String soid = request.getParameter("soid");
		String stockTime = request.getParameter("createDate");
		Scmuser user = (Scmuser)session.getAttribute("user");
		String createUser = user.getName();
		
		SellOrderDao sod = new SellOrderDao();
		List<SellOrderItem>  soitemList = null;
		
		OutStockRecordDao osd = new OutStockRecordDao();
		StockDao sd = new StockDao();
		
		List<SellOrderMain> sellOrderMainOutStorageList = new ArrayList<SellOrderMain>();
		try {
			soitemList = sod.selectSellOrderItem(soid);
			
			for(int i=0;i<soitemList.size();i++) {
				SellOrderItem soitem = soitemList.get(i);
				sd.reduceStock(soitem.getProductCode(), soitem.getNum());
				osd.addOutStockRecord(soitem.getProductCode(), soitem.getNum(), stockTime, createUser);  //添加出库记录
			}
			sod.outStorageRecord(soid);  //出库 状态改为2
			
			sellOrderMainOutStorageList = sod.selectOutStorageSellOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("sellOrderMainOutStorageList", sellOrderMainOutStorageList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}