package scm.servlet.purchase_manage.purchase_order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONArray;

import scm.dao.VenderDao;
import scm.model.Vender;

@WebServlet("/ShowVenderServlet")
public class ShowVenderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		VenderDao vd = new VenderDao();
		List<Vender> venderList = new ArrayList<Vender>();
		try {
			venderList = vd.selectAllVender();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    PrintWriter out = response.getWriter();
	    Object jsonStr = JSONArray.toJSON(venderList);
		out.print(jsonStr.toString());
		out.flush();
	    out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}