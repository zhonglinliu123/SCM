package scm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.CustomerDao_c;
import scm.model.Customer_c;

/**
 * Servlet implementation class CustomerShowServlet
 * 
 */
public class CustomerShowServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDao_c customerDao = new CustomerDao_c();
		HttpSession session = request.getSession();
		ArrayList<Customer_c> customerList = null;
		try {
			customerList = customerDao.listCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.setAttribute("customerList", customerList);
		
		response.sendRedirect("/SCM/storeCan/customer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
