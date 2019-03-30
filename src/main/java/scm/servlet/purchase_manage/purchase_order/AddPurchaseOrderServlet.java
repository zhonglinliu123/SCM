package scm.servlet.purchase_manage.purchase_order;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scm.dao.PurchaseOrderDao;
import scm.model.PayType;

@WebServlet("/AddPurchaseOrderServlet")
public class AddPurchaseOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String poid = request.getParameter("poid");
		String createTime = request.getParameter("createTime");
		String venderCode = request.getParameter("venderCode");
		String account = request.getParameter("account"); 
		int tipFee = Integer.parseInt(request.getParameter("tipFee") == null || "".equals(request.getParameter("tipFee")) ? "0" : request.getParameter("tipFee"));
		int productTotal = Integer.parseInt(request.getParameter("productTotal") == null || "".equals(request.getParameter("productTotal")) ? "0" : request.getParameter("productTotal"));
		String remark = request.getParameter("remark");
		int prePayFee = Integer.parseInt(request.getParameter("prePayFee") == null || "".equals(request.getParameter("prePayFee")) ? "0" : request.getParameter("prePayFee"));
		int paytype = PayType.getId(request.getParameter("paytype"));
		
		PurchaseOrderDao pod = new PurchaseOrderDao();
		
		try {
			 pod.addPurchaseOrder(poid,createTime,venderCode,account,tipFee,productTotal,remark,prePayFee,paytype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
