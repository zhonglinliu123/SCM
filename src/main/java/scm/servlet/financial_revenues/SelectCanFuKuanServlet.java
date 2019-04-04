package scm.servlet.financial_revenues;

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

@WebServlet("/SelectCanFuKuanServlet")
public class SelectCanFuKuanServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PurchaseOrderDao pod = new PurchaseOrderDao();
		List<PurchaseOrderMain> purchaseOrderMainFuKuanList = new ArrayList<PurchaseOrderMain>();
		try {
			purchaseOrderMainFuKuanList = pod.selectFuKuanPurchaseOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderMainFuKuanList", purchaseOrderMainFuKuanList);
		response.sendRedirect("/SCM/scm_wb/financial_revenues/pay_register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}