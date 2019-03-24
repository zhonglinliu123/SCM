package scm.servlet.system_manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scm.dao.ScmUserDao;

@WebServlet("/UserCheckExistServlet")
public class UserCheckExistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account = request.getParameter("account");
		ScmUserDao sud = new ScmUserDao();
		boolean flag = true;
		try {
			flag = sud.checkUserExist(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		if(flag == true) {
			out.print("可以创建");
		}else {
			out.print("已存在");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
