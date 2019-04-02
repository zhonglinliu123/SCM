package scm.servlet.sell_manage.sell_order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.SellOrderDao;
import scm.model.SellOrderItem;

@WebServlet("/ShowSellOrderItemServlet")
public class ShowSellOrderItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String soid = request.getParameter("soid");
		SellOrderDao pod = new SellOrderDao();
		List<SellOrderItem> sellOrderItemList = new ArrayList<SellOrderItem>();
		try {
			sellOrderItemList = pod.selectSellOrderItem(soid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("sellOrderItemList", sellOrderItemList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}