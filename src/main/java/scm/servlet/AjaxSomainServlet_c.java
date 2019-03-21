package scm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import scm.dao.SoitemDao_c;
import scm.dao.SomainDTODao_c;
import scm.model.Soitem_c;
import scm.model.Somain_c;

/**
 * Servlet implementation class AjaxSomainServlet_c
 */
public class AjaxSomainServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get请求返回销售单信息
		String soid = request.getParameter("soid");
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		Somain_c somain = null;
		
		try {
			somain = somainDTODao.getSomain(soid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSON.toJSON(somain);
		
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
		//post请求返回销售单明细信息
		String soid = request.getParameter("soid");
		SoitemDao_c soitemDao = new SoitemDao_c();
		List<Soitem_c> soitemList = null;
		
		try {
			soitemList = soitemDao.listSoitem(soid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object json = JSONArray.toJSON(soitemList);
		
		PrintWriter out = response.getWriter();
		if(json != null) {
			out.print(json);
			out.flush();
		}
	}

}
