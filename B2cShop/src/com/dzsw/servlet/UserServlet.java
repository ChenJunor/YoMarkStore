package com.dzsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;
import com.dzsw.service.UserService;

public class UserServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		try {
			if ("checklogin".equals(action)) {
				
				CheckLogin(request, response);
			} else if ("insertuser".equals(action)) {
				InsertUser(request, response);
			} else if ("usercontent".equals(action)) {
				String userid = request.getParameter("user_id");
				UserContent(request, response, userid);
			} else if (("updateuser").equals(action)) {
				String userid = request.getParameter("user_id");
				UpdateUser(request, response, userid);
			} else if (("deleteuser").equals(action)) {
				String userid = request.getParameter("user_id");
				DeleteUser(request, response, userid);
			} else if (("updateuserinfo").equals(action)) {
				String userid = request.getParameter("user_id");
				UpdateUserInfo(request, response, userid);
			} else if (("selectuserbypage").equals(action)) {
				SelectUserDispByPage(request, response);
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
	
	public void CheckLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
			
			Map<String, String> connVal = new HashMap<String, String>();
			Map<String, String> selVal = new HashMap<String, String>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>(5);
			
			UserService user = new UserService();

			list.add(connVal);
			selVal.put("user_name", request.getParameter("username"));
			selVal.put("password", request.getParameter("password"));
			list.add(selVal);

			List<Map<String, String>> result = user.selectUser(list);
			
			HttpSession session = request.getSession();
			
			if (result.size() != 0) {
				session.setAttribute("user_info", result);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/ShowStates.jsp");
			}
	}
	
	public void InsertUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException, ClassNotFoundException {
			Map<String, String> selVal = new HashMap<String, String>();
		
			UserService user = new UserService();
			
			selVal.put("user_name", request.getParameter("username"));
			selVal.put("password", request.getParameter("password"));
			selVal.put("user_email", request.getParameter("email"));
			selVal.put("user_sex", request.getParameter("sex"));
			selVal.put("user_birth", request.getParameter("birth"));
			selVal.put("user_phone", request.getParameter("phone"));
			selVal.put("user_addr", request.getParameter("address"));
			selVal.put("user_type", request.getParameter("type"));
			selVal.put("user_state", request.getParameter("status"));
			selVal.put("user_level", request.getParameter("level"));
			
//			System.out.println(selVal);
			
			if (user.InsertUser(selVal)) {
				response.sendRedirect(request.getContextPath()+"/Admin/SelectUser.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
	}
	
	
	public void SelectUserDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_user");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectUser.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void UserContent(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		Map<String, String> map = user.selectUserInfoById(userid);
		if (map.size() != 0) {
			request.setAttribute("userinfo", map);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/UserContent.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void UpdateUser(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		Map<String, String> map = user.selectUserInfoById(userid);
		if (map.size() != 0) {
			request.setAttribute("userinfo", map);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/UpdateUser.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	public void UpdateUserInfo(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("name") != null) {
			selVal.put("user_name", request.getParameter("name"));
		}
		if (request.getParameter("email") != null) {
			selVal.put("user_email", request.getParameter("email"));
		}
		if (request.getParameter("sex") != null) {
			selVal.put("user_sex", request.getParameter("sex"));
		}
		if (request.getParameter("birth") != null) {
			selVal.put("user_birth", request.getParameter("birth"));
		}
		if (request.getParameter("phone") != null) {
			selVal.put("user_phone", request.getParameter("phone"));
		}
		if (request.getParameter("address") != null) {
			selVal.put("user_addr", request.getParameter("address").trim());
		}
		selVal.put("user_id", userid);
		
		System.out.println(selVal);
		
		if (user.UpdateUser(selVal)) {
			Map<String, String> map = user.selectUserInfoById(userid);
			System.out.println(map);
			if (map.size() != 0) {
				request.setAttribute("userinfo", map);
				RequestDispatcher rd = request.getRequestDispatcher("/Admin/UserContent.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void DeleteUser(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		if (user.DeleteUser(Integer.valueOf(userid))) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectUser.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void LogOut(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		HttpSession session = request.getSession();
		session.removeAttribute("user_info");
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
}
