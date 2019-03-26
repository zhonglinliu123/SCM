package scm.servlet.purchase_manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.VenderDao;
import scm.model.Vender;

@WebServlet("/VenderSearchServlet")
public class VenderSearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		VenderDao vd = new VenderDao();
		List<Vender> allvenderlist = new ArrayList<Vender>();
		try {
			allvenderlist = vd.selectSomeVender(code,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("allvenderlist", allvenderlist);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
