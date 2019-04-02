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
import scm.dao.ProductDao;
import scm.model.Product;

@WebServlet("/StockSearchServlet")
public class StockSearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String productCode = request.getParameter("productCode");
		String productName = request.getParameter("productName");
		ProductDao pd = new ProductDao();
		List<Product> searchProductList = new ArrayList<Product>();
		try {
			searchProductList = pd.searchProduct(productCode,productName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("searchProductList", searchProductList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}