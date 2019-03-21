package scm.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import scm.dao.CategoryDao_c;
import scm.model.Category_c;

/**
 * Servlet implementation class AjaxCategoryServlet
 * goods页面下拉列表获取数据
 */
public class AjaxCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDao_c categoryDao = new CategoryDao_c();
		List<Category_c> list = null;
		try {
			list = categoryDao.listCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object json = JSONArray.toJSON(list);
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		//System.out.println(json.toString());
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		CategoryDao_c categoryDao = new CategoryDao_c();
		
		Category_c category = null;
		try {
			category = categoryDao.getCategory(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSONObject.toJSON(category);
		PrintWriter out = response.getWriter();
		if(json != null) {
			out.print(json);
			out.flush();
		}
	}

}
