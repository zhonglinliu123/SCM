package scm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scm.model.Scmuser;

@WebFilter("/*")
public class PermissionCheckFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//根据请求路径区分资源
		String uri = req.getRequestURI();	///项目名称/资源路径
		HttpSession session = req.getSession();
		Scmuser user = (Scmuser) session.getAttribute("user");
		
		if(uri.contains("/usermanage.jsp")) {
			if(user != null && "管理员".equals(user.getStatus())) {
				//表示当前用户是管理员
				chain.doFilter(request, response);
			}else {
				String path = req.getContextPath(); //项目名称
				resp.sendRedirect(path+"/scm_wb/no_permission.html");
			}
		}else if(uri.contains("/purchase_manage/")) {
			if(user != null && "采购员".equals(user.getStatus())) {
				//表示当前用户是管理员
				chain.doFilter(request, response);
			}else {
				String path = req.getContextPath(); //项目名称
				resp.sendRedirect(path+"/scm_wb/no_permission.html");
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {}
}
