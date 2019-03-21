package scm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import scm.dao.ProductDao_c;
import scm.model.Product_c;

/**
 * Servlet implementation class AjaxProductSelectServlet
 */
public class AjaxProductSelectServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String categoryName = request.getParameter("categoryName");
		String page = request.getParameter("page");
		
		int num = Integer.parseInt(page);
		
		ProductDao_c productDao = new ProductDao_c();
		
		ArrayList<Product_c> productList = null;
		try {
			productList = productDao.typeProduct(code, name, categoryName, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSONArray.toJSON(productList);
		
		PrintWriter out = response.getWriter();
		if(json != null) {
			out.print(json.toString());
			//System.out.println(json.toString());
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
