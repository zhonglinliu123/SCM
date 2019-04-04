package scm.servlet.financial_revenues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.SellOrderDao;
import scm.model.SellOrderMain;

@WebServlet("/ShouKuanServlet")
public class ShouKuanServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String soid = request.getParameter("soid");
		String status = request.getParameter("status");
		
		SellOrderDao sod = new SellOrderDao();
		
		List<SellOrderMain> sellOrderMainShouKuanList = new ArrayList<SellOrderMain>();
			
		try {
			sod.shouKuan(soid,status);
			sellOrderMainShouKuanList = sod.selectShouKuanSellOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("sellOrderMainShouKuanList", sellOrderMainShouKuanList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}