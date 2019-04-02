package scm.servlet.storage_manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.CheckStockDao;
import scm.dao.ProductDao;
import scm.dao.StockDao;
import scm.model.Product;
import scm.model.Scmuser;

@WebServlet("/StockUpdateServlet")
public class StockUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Scmuser user = (Scmuser)session.getAttribute("user");
		String createUser = user.getName();
		String productCode = request.getParameter("productCode");
		int originNum = Integer.parseInt(request.getParameter("originNum"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		int realNum = Integer.parseInt(request.getParameter("realNum"));
		String createDate = request.getParameter("createDate");
		
		StockDao sd = new StockDao();
		CheckStockDao csd = new CheckStockDao();
		ProductDao pd = new ProductDao();
		List<Product> productList = new ArrayList<Product>();
		try {
			csd.addStock(productCode, originNum, realNum, createDate, createUser, description, type); //添加库存记录
			sd.changeStock(productCode, realNum);     //修改库存数量
			productList = pd.selectAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("productList", productList);
		response.sendRedirect("/SCM/scm_wb/storage_manage/stock_update.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}