package cn.tedu.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.ProdDAO;
import cn.tedu.entity.Prod;

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		ProdDAO dao = new ProdDAO();
		dao.showDataBases();
		dao.showTables();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chatset=utf-8");
		request.setAttribute("contextPath", request.getContextPath());
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		String Id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String Price =  request.getParameter("price");
		String Pnum = request.getParameter("pnum");
		String description = request.getParameter("description");
		int id = 0;
		if(Id!=null){
			id = Integer.parseInt(Id);
		}
		double price = 0;
		int pnum = 0;
		ProdDAO dao = new ProdDAO();
		if("/list".equals(path)){
			try {
				List<Prod> prods = dao.select();
				request.setAttribute("prods", prods);
				request.getRequestDispatcher("prod_list.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if("/delete".equals(path)){
			try {
				synchronized (this) {
					dao.delete(id);
				}
				response.sendRedirect(request.getContextPath()+"/list.prod");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if("/insert".equals(path)){
			System.out.println("insert方法开始执行");
			if("".equals(name) || name == null || Price == null || Pnum == null||"".equals(Price)||"".equals(Pnum)){
				request.getRequestDispatcher("prod_add.jsp").forward(request, response);
			}else{
				try {
					price = Double.parseDouble(Price);
					pnum = Integer.parseInt(Pnum);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.getRequestDispatcher("prod_add.jsp").forward(request, response);
				}	
				try {
					dao.insert(name, category, price, pnum, description);
					response.sendRedirect(request.getContextPath()+"/list.prod");
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		}else if("/truncate".equals(path)){
			try {
				dao.truncate();
				response.sendRedirect(request.getContextPath()+"/list.prod");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if("/update".equals(path)){
			if(name.equals("")||Price.equals("")||Pnum.equals("")){
				request.getRequestDispatcher("prod_upd.jsp").forward(request, response);
			}else{
				try {
					price = Double.parseDouble(Price);
					pnum = Integer.parseInt(Pnum);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.getRequestDispatcher("prod_upd.jsp").forward(request, response);
				}		
				try {
					synchronized (this) {
						dao.update(id, name, category, price, pnum, description);
					}
					response.sendRedirect(request.getContextPath()+"/list.prod");
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		}
	}
}
