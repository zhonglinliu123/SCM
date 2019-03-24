package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.SomainDTODao_c;

/**
 * Servlet implementation class SomainOverServlet_c
 */
public class SomainOverServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String soid = request.getParameter("soid");
		String endTime = request.getParameter("endTime");
		String endUser = request.getParameter("endUser");
		
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		try {
			somainDTODao.overSomain(soid, endTime, endUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/SCM/storeCan/somainOver.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
