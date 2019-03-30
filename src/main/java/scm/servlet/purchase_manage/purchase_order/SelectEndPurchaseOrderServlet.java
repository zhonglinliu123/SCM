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

@WebServlet("/SelectEndPurchaseOrderServlet")
public class SelectEndPurchaseOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderMain> purchaseOrderMainEndList = new ArrayList<PurchaseOrderMain>();
		try {
			purchaseOrderMainEndList = pod.selectEndPurchaseOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderMainEndList", purchaseOrderMainEndList);
		response.sendRedirect("/SCM/scm_wb/purchase_manage/purchase_order_end.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}