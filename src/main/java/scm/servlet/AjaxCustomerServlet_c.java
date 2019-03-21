package scm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import scm.dao.CustomerDao_c;
import scm.model.Customer_c;

/**
 * Servlet implementation class AjaxCustomerServlet_c
 */
public class AjaxCustomerServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String se = request.getParameter("se");
		
		CustomerDao_c customerDao = new CustomerDao_c();
		List<Customer_c> customerList = null;
		
		try {
			customerList = customerDao.selCustomer(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSONArray.toJSON(customerList);
		
		PrintWriter out = response.getWriter();
		if(json != null) {
			out.print(json);
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
