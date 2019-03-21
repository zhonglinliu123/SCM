package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.ProductDao_c;
import scm.model.Product_c;

/**
 * Servlet implementation class ProductServlet_c
 * 增加product
 */
public class ProductAddServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productCode = request.getParameter("productCode");
		String ID = request.getParameter("categoryID");
		String productName = request.getParameter("productName");
		String unitName = request.getParameter("unitName");
		String pri = request.getParameter("price");
		String createDate = request.getParameter("createDate");
		String remark = request.getParameter("remark");
		
		//如果有值则是修改
		String update = request.getParameter("update");
		
		float price = Float.parseFloat(pri);
		int categoryID = Integer.parseInt(ID);;
		
		
		ProductDao_c productDao = new ProductDao_c();
		
		Product_c product = new Product_c(productCode, categoryID, productName, unitName, price, createDate, remark);
		
		try {
			if("true".equals(update)) {//更新
				productDao.updateProduct(product);
			} else {//添加
				productDao.addProduct(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/SCM/storeCan/GoodsServlet_c");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
