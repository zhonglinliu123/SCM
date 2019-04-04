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

import scm.dao.SellOrderDao;
import scm.model.SellOrderMain;

@WebServlet("/SelectCanShouKuanServlet")
public class SelectCanShouKuanServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SellOrderDao sod = new SellOrderDao();
		List<SellOrderMain> sellOrderMainShouKuanList = new ArrayList<SellOrderMain>();
		try {
			sellOrderMainShouKuanList = sod.selectShouKuanSellOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("sellOrderMainShouKuanList", sellOrderMainShouKuanList);
		response.sendRedirect("/SCM/scm_wb/financial_revenues/receipt_register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}