package scm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.dao.UserDao_c;
import scm.model.User_c;

/**
 * Servlet implementation class LoginServlet_c
 */
public class LoginServlet_c extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		if(account == null && password == null) {
			session.removeAttribute("user");
			response.sendRedirect("/SCM/login.jsp");
			return;
		}
		
		UserDao_c userDao = new UserDao_c();
		User_c user = null;
		
		try {
			user = userDao.getUser(account, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null) {
			request.setAttribute("error", "账号或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} else {
			if("1".equals(user.getStatus())) {
				request.setAttribute("error", "你的账号已被锁定，不能登入！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			} else {
				session.setAttribute("user", user);
				response.sendRedirect("/SCM/index.htm");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
