package scm.servlet.purchase_manage.purchase_order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.PurchaseOrderDao;
import scm.model.PurchaseOrderItem;

@WebServlet("/ShowPurchaseOrderItemServlet")
public class ShowPurchaseOrderItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String poid = request.getParameter("poid");
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderItem> purchaseOrderItemList = new ArrayList<PurchaseOrderItem>();
		try {
			purchaseOrderItemList = pod.selectPurchaseOrderItem(poid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderItemList", purchaseOrderItemList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}