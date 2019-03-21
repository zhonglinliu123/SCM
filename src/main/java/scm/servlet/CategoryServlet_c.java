package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scm.dao.CategoryDao_c;
import scm.model.Category_c;

/**
 * Servlet implementation class CategoryServlet_c
 * 
 * category表的增加和修改
 * 
 */
public class CategoryServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryID = request.getParameter("categoryID");
		String categoryName = request.getParameter("categoryName");
		String categoryRemark = request.getParameter("categoryRemark");
		//判断id是否有值
		String regID = "[0-9]{1,}";
		//服务器端判断名字是否为空，如果为空跳回操作界面
		if("".equals(categoryName)) {
			response.sendRedirect("/SCM/storeCan/GoodsClassServlet_c");
			return;
		}
		
		CategoryDao_c categoryDao = new CategoryDao_c();
		
		//id有值是修改，没有是新增
		if(categoryID.matches(regID)) {
			int ID = Integer.parseInt(categoryID);
			Category_c category = new Category_c(ID, categoryName, categoryRemark);
			try {
				categoryDao.updateCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Category_c category = new Category_c(categoryName, categoryRemark);
			try {
				categoryDao.addCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("/SCM/storeCan/GoodsClassServlet_c");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
