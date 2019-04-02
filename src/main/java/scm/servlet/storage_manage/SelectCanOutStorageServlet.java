package scm.servlet.storage_manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.SellOrderDao;
import scm.model.SellOrderMain;

@WebServlet("/SelectCanOutStorageServlet")
public class SelectCanOutStorageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SellOrderDao sod = new SellOrderDao();
		List<SellOrderMain> sellOrderMainOutStorageList = new ArrayList<SellOrderMain>();
		try {
			sellOrderMainOutStorageList = sod.selectOutStorageSellOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("sellOrderMainOutStorageList", sellOrderMainOutStorageList);
		response.sendRedirect("/SCM/scm_wb/storage_manage/out_storage_record.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}