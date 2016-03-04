package com.dzsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dzsw.service.AdminService;
import com.dzsw.service.GoodsService;
import com.dzsw.service.StorageService;

public class StorageServlet extends HttpServlet {

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
			if ("insertstorage".equals(action)) {
				InsertStorage(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertStorage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ClassNotFoundException {
		Map<String, String> selVal = new HashMap<String, String>();

		StorageService storage = new StorageService();
		GoodsService goods = new GoodsService();

		Map<String, String> goods_info = goods.selectGoodsInfoByName(request
				.getParameter("goods"));

		if (goods_info.size() != 0 && "1".equals(goods_info.get("STATE"))) {
			selVal.put("goods_id", goods_info.get("GOODS_ID"));
			selVal.put("stor_price", request.getParameter("price"));
			selVal.put("stor_type", request.getParameter("stor_type"));
			selVal.put("stor_num", request.getParameter("num"));

			System.out.println(selVal);

			if (storage.InsertStorage(selVal)) {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectStorage.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/error.html");
			}
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

}
