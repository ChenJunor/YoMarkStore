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
import com.dzsw.service.GoodsService;
import com.dzsw.service.ReceiveService;
import com.dzsw.service.ReportService;
import com.dzsw.service.UserService;

public class ReceiveServlet extends HttpServlet {

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

		doPost(request, response);
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
			if (("updatereceive").equals(action)) {
				String userid = request.getParameter("rece_id");
				UpdateReceive(request, response, userid);
			} else if (("deletereceive").equals(action)) {
				String userid = request.getParameter("rece_id");
				DeleteReceive(request, response, userid);
			} else if (("updatereceiveinfo").equals(action)) {
				String userid = request.getParameter("rece_id");
				UpdateReceiveInfo(request, response, userid);
			} else if (("selectreceivebypage").equals(action)) {
				SelectReceiveDispByPage(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UpdateReceiveInfo(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		ReceiveService order = new ReceiveService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("name") != null) {
			selVal.put("rece_name", request.getParameter("name"));
		}
		if (request.getParameter("address") != null) {
			selVal.put("rece_address", request.getParameter("address"));
		}
		if (request.getParameter("phone") != null) {
			selVal.put("rece_phone", request.getParameter("phone"));
		}
		if (request.getParameter("user") != null) {
			UserService user = new UserService();
			Map<String, String> umap = user.selectUserInfoByName(request.getParameter("user").toString());
			if (umap.size() != 0) {
				selVal.put("user_id", umap.get("USER_ID"));
			}
		}
		selVal.put("rece_id", userid);
		
		System.out.println(selVal);
		if (order.UpdateReceive(selVal)) {
			response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectReceive.jsp");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateReceive(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		ReceiveService comm = new ReceiveService();
		Map<String, String> smap = comm.selectReceiveInfoById(userid);
		
		if (smap.size() != 0) {
			UserService user = new UserService();
			Map<String, String> umap = user.selectUserInfoById(smap.get("USER_ID"));
			if (umap.size() != 0) {
				smap.put("USER_NAME", umap.get("USER_NAME"));
				request.setAttribute("receiveinfo", smap);
				RequestDispatcher rd = request
						.getRequestDispatcher("/Admin/UpdateReceive.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/error.html");
			}
			
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void DeleteReceive(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
		ReceiveService user = new ReceiveService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("rece_id", userid);
		if (user.DeleteReceive(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectReceive.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}	
	}
	
	public void SelectReceiveDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_receive");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectReceive.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}


}
