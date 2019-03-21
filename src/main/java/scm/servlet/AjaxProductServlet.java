package scm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import scm.dao.ProductDao_c;
import scm.model.Product_c;

/**
 * Servlet implementation class AjaxProductServlet
 */
public class AjaxProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productCode = request.getParameter("productCode");
		
		ProductDao_c productDao = new ProductDao_c();
		Product_c product = null;
		try {
			product = productDao.getProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object json = JSONObject.toJSON(product);
		PrintWriter out = response.getWriter();
		if(json != null) {
			out.print(json.toString());
			//System.out.println(json.toString());
			out.flush();
		} else {
			out.print("{\"soNum\":0,\"productCode\":\"0\",\"price\":0,\"poNum\":0,\"remark\":\"\",\"categoryName\":\"\",\"categoryID\":0}");
			out.flush();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
