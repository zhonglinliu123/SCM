package scm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.ProductDao_c;
import scm.model.Product_c;

/**
 * Servlet implementation class GoodsServlet_c
 * 
 * goods页面显示
 * 
 */
public class GoodsServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao_c productDao = new ProductDao_c();
		HttpSession session = request.getSession();
		
		ArrayList<Product_c> productList = null;
		int pages = 0;
		try {
			productList = productDao.typeProduct("","","",1);
			pages = productDao.getPages();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("productList", productList);
		session.setAttribute("pages", pages);
		
		request.getRequestDispatcher("/storeCan/goods.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
