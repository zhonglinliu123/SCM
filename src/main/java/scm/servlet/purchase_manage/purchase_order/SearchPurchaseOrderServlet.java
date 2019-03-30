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
import scm.model.PurchaseOrderMain;

@WebServlet("/SearchPurchaseOrderServlet")
public class SearchPurchaseOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String poid = request.getParameter("poid");
		String venderCode = request.getParameter("venderCode");
		int payType = Integer.parseInt(request.getParameter("payType"));
		int status = Integer.parseInt(request.getParameter("status"));
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderMain> purchaseOrderMainList = new ArrayList<PurchaseOrderMain>();
		try {
			purchaseOrderMainList = pod.searchPurchaseOrder(poid,venderCode,payType,status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderMainList", purchaseOrderMainList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}