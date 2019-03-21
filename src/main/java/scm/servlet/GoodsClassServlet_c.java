package scm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.CategoryDao_c;
import scm.model.Category_c;

/**
 * Servlet implementation class GoodsClass_c
 */
public class GoodsClassServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		CategoryDao_c categoryDao = new CategoryDao_c();
		ArrayList<Category_c> categorylist = null;
		try {
			categorylist = categoryDao.listCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.setAttribute("categorylist_c", categorylist);
		
		request.getRequestDispatcher("/storeCan/goodsClass.jsp").forward(request, response);
		
		//response.sendRedirect("/SCM/storeCan/goodsClass.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
