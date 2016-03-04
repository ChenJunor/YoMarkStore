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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dzsw.dao.utils.Paging;
import com.dzsw.service.AdminService;
import com.dzsw.service.OrderService;
import com.dzsw.service.UserService;

public class OrderServlet extends HttpServlet {

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
			if (("updateorder").equals(action)) {
				String userid = request.getParameter("order_id");
				UpdateOrder(request, response, userid);
			} else if (("deleteorder").equals(action)) {
				String userid = request.getParameter("order_id");
				DeleteOrder(request, response, userid);
			} else if (("updateorderinfo").equals(action)) {
				String userid = request.getParameter("order_id");
				UpdateOrderInfo(request, response, userid);
			} else if (("selectorderbypage").equals(action)) {
				SelectOrderDispByPage(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void UpdateOrder(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		OrderService order = new OrderService();
		Map<String, String> omap = order.selectOrderInfoById(userid);
		if (omap.size() != 0) {
			UserService user = new UserService();
			Map<String, String> map = user.selectUserInfoById(omap.get("USER_ID"));
			omap.put("USER_NAME", map.get("USER_NAME"));
			request.setAttribute("orderinfo", omap);
			RequestDispatcher rd = request
					.getRequestDispatcher("/Admin/UpdateOrder.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateOrderInfo(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		OrderService order = new OrderService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("order_state") != null) {
			selVal.put("order_state", request.getParameter("order_state"));
		}
		selVal.put("order_id", userid);

		if (order.UpdateOrder(selVal)) {
			response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectOrder.jsp");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void DeleteOrder(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		OrderService order = new OrderService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_id", userid);
		if (order.DeleteOrder(map)) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/Admin/SelectOrder.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}
	public void SelectOrderDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_order");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectOrder.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
}
