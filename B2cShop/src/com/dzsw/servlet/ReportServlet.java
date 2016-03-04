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
import com.dzsw.service.CommentsService;
import com.dzsw.service.GoodsService;
import com.dzsw.service.ReportService;
import com.dzsw.service.UserService;

public class ReportServlet extends HttpServlet {

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
			if (("updatereport").equals(action)) {
				String userid = request.getParameter("repo_id");
				UpdateReport(request, response, userid);
			} else if (("deletereport").equals(action)) {
				String userid = request.getParameter("repo_id");
				DeleteReport(request, response, userid);
			} else if (("updatereportinfo").equals(action)) {
				String userid = request.getParameter("repo_id");
				UpdateReportInfo(request, response, userid);
			} else if (("selectreportbypage").equals(action)) {
				SelectReportDispByPage(request, response);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateReportInfo(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		ReportService order = new ReportService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("report_state") != null) {
			selVal.put("repo_state", request.getParameter("report_state"));
		}
		selVal.put("repo_id", userid);
		
		System.out.println(selVal);
		
		if (order.UpdateReport(selVal)) {
			response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectReport.jsp");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateReport(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		ReportService comm = new ReportService();
		Map<String, String> smap = comm.selectReportInfoById(userid);
		
		if (smap.size() != 0) {
			GoodsService goods = new GoodsService();
			UserService user = new UserService();
			Map<String, String> gmap = goods.selectGoodsInfoById(smap.get("GOODS_ID"));
			Map<String, String> umap = user.selectUserInfoById(smap.get("USER_ID"));
			if (gmap.size() != 0 && umap.size() != 0) {
				smap.put("GOODS_NAME", gmap.get("GOODS_NAME"));
				smap.put("USER_NAME", umap.get("USER_NAME"));
				request.setAttribute("reportinfo", smap);
				RequestDispatcher rd = request
						.getRequestDispatcher("/Admin/UpdateReport.jsp");
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

	public void DeleteReport(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
		ReportService user = new ReportService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("repo_id", userid);
		if (user.DeleteReport(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectReport.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}	
	}
	
	public void SelectReportDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_report");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectReport.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}

}
