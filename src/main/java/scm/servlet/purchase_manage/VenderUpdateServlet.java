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

@WebServlet("/VenderUpdateServlet")
public class VenderUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String contactor = request.getParameter("contactor");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String tel = request.getParameter("tel");
		String fax = request.getParameter("fax");
		String createDate = request.getParameter("createDate");
		String up_or_add = request.getParameter("up_or_add");
		VenderDao vd = new VenderDao();
		List<Vender> allvenderlist = new ArrayList<Vender>();
		try {
			if(up_or_add.equals("修改")) {
				vd.venderUpdate(code,password,name,contactor,address,postcode,tel,fax,createDate);
			}else{
				vd.venderAdd(code,password,name,contactor,address,postcode,tel,fax,createDate);
			}
			allvenderlist = vd.selectAllVender();
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