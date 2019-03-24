package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.SoitemDao_c;
import scm.dao.SomainDTODao_c;

/**
 * Servlet implementation class SomainDeleteServlet_c
 */
public class SomainDeleteServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String soid = request.getParameter("soid");
		
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		SoitemDao_c soitemDao = new SoitemDao_c();
		
		try {
			//先删除明细，再删除销售单
			soitemDao.deleteSoitem(soid);
			somainDTODao.deleteSomain(soid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		response.sendRedirect("/SCM/storeCan/SomainShowServlet_c");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
