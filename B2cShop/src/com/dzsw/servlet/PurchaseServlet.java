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

import com.dzsw.dao.utils.DButils;
import com.dzsw.service.OrderService;
import com.dzsw.service.PurchaseService;

public class PurchaseServlet extends HttpServlet {

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
			if ("submitorder".equals(action)) {
				SubmitOrder(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SubmitOrder(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ClassNotFoundException, IOException, ServletException {
		String userid = (String) request.getParameter("userid");
		OrderService order = new OrderService();
		Map<String, String> selVal = new HashMap<String, String>();
		System.out.println(userid);
		selVal.put("user_id", userid);
		selVal.put("order_state", "1");
		selVal.put("state", "1");
		if (order.InsertOrder(selVal)) {
			PurchaseService purch = new PurchaseService();
			List<Map<String, String>> val = new ArrayList<Map<String, String>>();
			val.add(new HashMap<String, String>());
			Map<String, String> selval = new HashMap<String, String>();
			selval.put("user_id", userid);
			selval.put("purch_type", "0");
			val.add(selval);
			List<Map<String, String>> selRes = purch.selectPurchase(val);

			int flag = 1;
			for (Map<String, String> m : selRes) {
				Map<String, String> temp = new HashMap<String, String>();
				temp.put("purch_id", m.get("PURCH_ID"));
				temp.put("purch_type", "1");
				if (!purch.UpdatePurchase(temp)) {
					flag = 0;
				}
			}
			if (flag == 0) {
				response.sendRedirect(request.getContextPath()+"/ShowStates.jsp");
			} else {
				request.setAttribute("purchlist", selRes);
				RequestDispatcher rd = request.getRequestDispatcher("/SubmitOrder.jsp");
				rd.forward(request, response);
			}
			
		} else {
			response.sendRedirect(request.getContextPath()+"/ShowStates.jsp");
		}

	}
}
