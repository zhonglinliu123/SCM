package scm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import scm.dao.SomainDTODao_c;
import scm.model.SomainDTO_c;

/**
 * Servlet implementation class AjaxSomainSelectServlet_c
 */
public class AjaxSomainSelectServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String soid = request.getParameter("soid");
		String dateStart = request.getParameter("dateStart");
		String dateEnd = request.getParameter("dateEnd");
		String customerCode = request.getParameter("customerCode");
		String payType = request.getParameter("payType");
		String status = request.getParameter("status");
		
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		
		List<SomainDTO_c> somainList = null;
		
		try {
			somainList = somainDTODao.selectSomain(soid, dateStart, dateEnd, customerCode, payType, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSONArray.toJSON(somainList);
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
