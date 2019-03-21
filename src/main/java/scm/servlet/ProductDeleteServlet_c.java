package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.ProductDao_c;

/**
 * Servlet implementation class ProductDeleteServlet_c
 * 删除product
 */
public class ProductDeleteServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productCode = request.getParameter("productCode");
		
		//System.out.println(productCode);
		
		ProductDao_c productDao = new ProductDao_c();
		
		try {
			productDao.deleteProduct(productCode);
		} catch (Exception e) {
			request.setAttribute("error", "该商品有销售明细信息不能删除");
		}
		
		request.getRequestDispatcher("/storeCan/GoodsServlet_c").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
