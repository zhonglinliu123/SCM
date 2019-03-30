package scm.servlet.purchase_manage;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/VenderDeleteServlet")
public class VenderDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String vendercode = request.getParameter("vendercode");
		VenderDao vd = new VenderDao();
		List<Vender> allvenderlist = new ArrayList<Vender>();
		boolean flag = false;
		try {
			flag = vd.checkVenderCanDelete(vendercode);
			if(flag) {
				vd.venderDelete(vendercode);
				allvenderlist = vd.selectAllVender();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter(); 
		if(flag) {
			HttpSession session = request.getSession();
			session.setAttribute("allvenderlist", allvenderlist);
			out.print("删除成功");
		}else {
			out.print("有其他的依赖存在，不能删除");
		}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}