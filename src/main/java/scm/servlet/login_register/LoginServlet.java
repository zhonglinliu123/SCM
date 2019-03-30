package scm.servlet.login_register;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import scm.dao.ScmUserDao;
import scm.model.Scmuser;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		ScmUserDao sud = new ScmUserDao();
		Scmuser user = null;
		try {
		    user = sud.checkUser(account, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		if(user == null) {
			out.print("’À∫≈ªÚ√‹¬Î ‰»Î¥ÌŒÛ£°");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user);
			out.print("µ«¬Ω≥…π¶");
		}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
