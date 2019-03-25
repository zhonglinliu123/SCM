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
		
		//��������·��������Դ
		String uri = req.getRequestURI();	///��Ŀ����/��Դ·��
		HttpSession session = req.getSession();
		Scmuser user = (Scmuser) session.getAttribute("user");
		
		if(uri.contains("/usermanage.jsp")) {
			if(user != null && "����Ա".equals(user.getStatus())) {
				//��ʾ��ǰ�û��ǹ���Ա
				chain.doFilter(request, response);
			}else {
				String path = req.getContextPath(); //��Ŀ����
				resp.sendRedirect(path+"/scm_wb/no_permission.html");
			}
		}else {
			chain.doFilter(request, response);
		}
//		}else if(uri.contains("/login/")) {
//			System.out.println("�����½�û����ܽ��еĲ���");
//			if(user == null) {
//				//û�� ��½�����������
//				String path = req.getContextPath(); //��Ŀ����
//				resp.sendRedirect(path+"/error.jsp");
//			}else {
//				chain.doFilter(request, response);
//			}
//		}else if(uri.contains("/you/")) {
//			System.out.println("�����οͿ��Խ��еĲ���");
//			chain.doFilter(request, response);
//		}
	}

	public void destroy() {}
}
