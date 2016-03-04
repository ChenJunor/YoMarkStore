package com.dzsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzsw.dao.utils.Paging;
import com.dzsw.service.AdminService;
import com.dzsw.service.UserService;

public class AdminServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		
		String action = request.getParameter("action");
		try {
			if ("checklogin".equals(action)) {
				CheckLogin(request, response);
			} else if ("insertadmin".equals(action)){
				InsertAdmin(request, response);
			} else if (("updateadmin").equals(action)) {
				String adminid = request.getParameter("admin_id");
				UpdateAdmin(request, response, adminid);
			} else if (("deleteadmin").equals(action)) {
				String adminid = request.getParameter("admin_id");
				DeleteAdmin(request, response, adminid);
			} else if (("updateadmininfo").equals(action)) {
				String adminid = request.getParameter("admin_id");
				UpdateAdminInfo(request, response, adminid);
			} else if (("selectadminbypage").equals(action)) {
				SelectAdminDispByPage(request, response);
			} else if (("logout").equals(action)) {
				LogOut(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CheckLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ClassNotFoundException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Map<String, String> connVal = new HashMap<String, String>();
		Map<String, String> selVal = new HashMap<String, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(5);

		AdminService admin = new AdminService();

		list.add(connVal);
		selVal.put("admin_name", username);
		selVal.put("password", password);
		list.add(selVal);

		List<Map<String, String>> result = admin.selectAdmin(list);

		HttpSession session = request.getSession();

		if (result.size() != 0) {
			session.setAttribute("admin_info", result);
			response.sendRedirect(request.getContextPath() + "/Admin/index.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/Admin/error.html");
		}
	}
	public void InsertAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException, ClassNotFoundException {
			Map<String, String> selVal = new HashMap<String, String>();
		
			AdminService user = new AdminService();
			
			selVal.put("admin_name", request.getParameter("username"));
			selVal.put("password", request.getParameter("password"));
			selVal.put("admin_email", request.getParameter("email"));
			selVal.put("admin_state", request.getParameter("status"));
			selVal.put("admin_type", "0");
			
			System.out.println(selVal);
			
			if (user.InsertAdmin(selVal)) {
				response.sendRedirect(request.getContextPath()+"/Admin/SelectAdmin.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
	}

	public void AdminContent(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		AdminService user = new AdminService();
		Map<String, String> map = user.selectAdminInfoById(userid);
		if (map.size() != 0) {
			request.setAttribute("admininfo", map);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/AdminContent.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void UpdateAdmin(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		AdminService user = new AdminService();
		Map<String, String> map = user.selectAdminInfoById(userid);
		if (map.size() != 0) {
			request.setAttribute("admininfo", map);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/UpdateAdmin.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	public void UpdateAdminInfo(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		AdminService admin = new AdminService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("name") != null) {
			selVal.put("admin_name", request.getParameter("name"));
		}
		if (request.getParameter("email") != null) {
			selVal.put("admin_email", request.getParameter("email"));
		}
		if (request.getParameter("password") != null) {
			selVal.put("password", request.getParameter("password"));
		}
		selVal.put("admin_id", userid);
		
		
		if (admin.UpdateAdmin(selVal)) {
			Map<String, String> map = admin.selectAdminInfoById(userid);
			if (map.size() != 0) {
				request.setAttribute("admininfo", map);
				RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectAdmin.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void DeleteAdmin(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		AdminService user = new AdminService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("admin_id", userid);
		if (user.DeleteAdmin(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectAdmin.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void SelectAdminDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_admin");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectAdmin.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void LogOut(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		HttpSession session = request.getSession();
		session.removeAttribute("admin_info");
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/Admin/login.jsp");
	}

}
