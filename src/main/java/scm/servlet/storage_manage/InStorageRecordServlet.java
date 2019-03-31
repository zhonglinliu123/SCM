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

import scm.dao.InStockRecordDao;
import scm.dao.PoitemDao;
import scm.dao.PurchaseOrderDao;
import scm.dao.StockDao;
import scm.model.PurchaseOrderItem;
import scm.model.PurchaseOrderMain;
import scm.model.Scmuser;

@WebServlet("/InStorageRecordServlet")
public class InStorageRecordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String poid = request.getParameter("poid");
		String stockTime = request.getParameter("createDate");
		Scmuser user = (Scmuser)session.getAttribute("user");
		String createUser = user.getName();
		
		PoitemDao pd = new PoitemDao();
		List<PurchaseOrderItem>  poitemList = null;
		
		InStockRecordDao isd = new InStockRecordDao();
		StockDao sd = new StockDao();
		
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderMain> purchaseOrderMainInStorageList = new ArrayList<PurchaseOrderMain>();
		try {
			poitemList = pd.listPoitem(poid);
			
			for(int i=0;i<poitemList.size();i++) {
				PurchaseOrderItem poitem = poitemList.get(i);
				sd.addStock(poitem.getProductCode(), poitem.getUnitName(), poitem.getNum());
				isd.addInStockRecord(poitem.getProductCode(), poitem.getNum(), stockTime, createUser);  //添加入库记录
			}
			pod.inStorageRecord(poid);  //入库 状态改为2
			
			purchaseOrderMainInStorageList = pod.selectInStoragePurchaseOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("purchaseOrderMainInStorageList", purchaseOrderMainInStorageList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}