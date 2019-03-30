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

@WebServlet("/SelectAllPurchaseOrderServlet")
public class SelectAllPurchaseOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tp = request.getParameter("tp");
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderMain> purchaseOrderMainList = new ArrayList<PurchaseOrderMain>();
		try {
			purchaseOrderMainList = pod.selectAllPurchaseOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderMainList", purchaseOrderMainList);
		if(tp != null) {
			response.sendRedirect("/SCM/scm_wb/purchase_manage/add_purchase_order.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
