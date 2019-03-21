package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.CategoryDao_c;

/**
 * Servlet implementation class CategoryDeleteServlet_c
 */
public class CategoryDeleteServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * category表内容的删除
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryID = request.getParameter("categoryID");
		CategoryDao_c categoryDao = new CategoryDao_c();
		
		int ID = Integer.parseInt(categoryID);
		
		try {
			categoryDao.deleteCategory(ID);
		} catch (Exception e) {
			request.setAttribute("categoryerror", "删除失败，可能这个分类中还有产品！");
		}
		
		request.getRequestDispatcher("/storeCan/GoodsClassServlet_c").forward(request, response);
		//response.sendRedirect("/SCM/storeCan/GoodsClassServlet_c");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
