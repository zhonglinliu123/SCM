package scm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.model.User_c;

public class ScmFilter_c implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//转成http的请求和响应
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		String uri = req.getRequestURI();
		if(uri.contains("/storeCan/")) {
			User_c user = (User_c) session.getAttribute("user");
			if(user != null) {
				if("storeCan".equals(user.getFilter())) {
					chain.doFilter(request, response);
				} else {
					req.setAttribute("hint", "你没有这个权限！");
					req.getRequestDispatcher("/error.jsp").forward(req, res);;
					return;
				}
			} else {
				req.setAttribute("hint", "你还没有登入！");
				req.getRequestDispatcher("/error.jsp").forward(req, res);;
				return;
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
