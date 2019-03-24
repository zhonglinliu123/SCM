package scm.servlet.system_manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.ScmUserDao;
import scm.model.Scmuser;

@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account = request.getParameter("account");
		ScmUserDao sud = new ScmUserDao();
		List<Scmuser> alluserlist = new ArrayList<Scmuser>();
		try {
			sud.userDelete(account);
			alluserlist = sud.selectAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("alluserlist", alluserlist);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
