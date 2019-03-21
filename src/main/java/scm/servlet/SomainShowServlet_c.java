package scm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.CustomerDao_c;
import scm.dao.ProductDao_c;
import scm.dao.SomainDTODao_c;
import scm.model.Customer_c;
import scm.model.Product_c;
import scm.model.SomainDTO_c;

/**
 * Servlet implementation class SomainShowServlet_c
 */
public class SomainShowServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		ProductDao_c productDao = new ProductDao_c();
		CustomerDao_c customerDao = new CustomerDao_c();
		HttpSession session = request.getSession();
		
		ArrayList<SomainDTO_c> somain = null;
		ArrayList<SomainDTO_c> somainList = new ArrayList<>();
		ArrayList<Product_c> productList = null;
		ArrayList<Customer_c> customerList = null;
		try {
			somain = somainDTODao.listSomainDTO();
			productList = productDao.listProduct();
			customerList = customerDao.listCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(SomainDTO_c som:somain) {
			if(som.getStatus() == 1) {
				somainList.add(som);
			}
		}
		
		session.setAttribute("somainList", somainList);
		session.setAttribute("productList", productList);
		session.setAttribute("customerList", customerList);
		
		response.sendRedirect("/SCM/storeCan/allSomain.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
