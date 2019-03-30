package scm.servlet.purchase_manage.purchase_order;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scm.dao.PurchaseOrderDao;

@WebServlet("/EndPurchaseOrderServlet")
public class EndPurchaseOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String poid = request.getParameter("poid");
		PurchaseOrderDao pod = new PurchaseOrderDao();
		try {
			 pod.endPurchaseOrder(poid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/SCM/SelectEndPurchaseOrderServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
