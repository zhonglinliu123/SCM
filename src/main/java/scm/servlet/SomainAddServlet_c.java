package scm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import scm.dao.SoitemDao_c;
import scm.dao.SomainDTODao_c;
import scm.model.Soitem_c;
import scm.model.SomainDTO_c;

/**
 * Servlet implementation class SomainAddServlet_c
 * 销售单新增
 */
public class SomainAddServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String soid = request.getParameter("soid");
		String customerCode = request.getParameter("customerCode");
		String account = request.getParameter("account");
		String createTime = request.getParameter("createTime");
		String tip = request.getParameter("tipFee");
		String pTotal = request.getParameter("productTotal");
		String sTotal =request.getParameter("soTotal");
		String payType = request.getParameter("payType");
		String preFee = request.getParameter("prePayFee");
		String remark = request.getParameter("remark");
		String json = request.getParameter("json");
		
		float tipFee = Float.parseFloat(tip);
		float productTotal = Float.parseFloat(pTotal);
		float soTotal = Float.parseFloat(sTotal);
		float prePayFee = Float.parseFloat(preFee);
		
		SomainDTO_c somainDTO = new SomainDTO_c(soid, customerCode, account, createTime, tipFee, productTotal, soTotal, payType, prePayFee, remark);
		
		SomainDTODao_c somainDTODao = new SomainDTODao_c();
		SoitemDao_c soitemDao = new SoitemDao_c();
		
		try {
			//先删除明细，再删除销售单
			soitemDao.deleteSoitem(soid);
			somainDTODao.deleteSomain(soid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		List<Soitem_c> soitemList = JSONArray.parseArray(json, Soitem_c.class);
		
		try {
			somainDTODao.addSomain(somainDTO, soitemList);
		} catch (Exception e) {
			e.printStackTrace();
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
