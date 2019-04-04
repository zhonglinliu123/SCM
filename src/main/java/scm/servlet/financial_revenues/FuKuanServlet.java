package scm.servlet.financial_revenues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.PurchaseOrderDao;
import scm.model.PurchaseOrderMain;

@WebServlet("/FuKuanServlet")
public class FuKuanServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String soid = request.getParameter("soid");
		String status = request.getParameter("status");
		
		PurchaseOrderDao pod = new PurchaseOrderDao();
		
		List<PurchaseOrderMain> purchaseOrderMainFuKuanList = new ArrayList<PurchaseOrderMain>();
			
		try {
			pod.fuKuan(soid,status);
			purchaseOrderMainFuKuanList = pod.selectFuKuanPurchaseOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("purchaseOrderMainFuKuanList", purchaseOrderMainFuKuanList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}