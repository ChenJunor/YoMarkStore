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
import com.dzsw.service.CommentsService;
import com.dzsw.service.GoodsService;
import com.dzsw.service.KindService;
import com.dzsw.service.OrderService;
import com.dzsw.service.UserService;

public class CommentsServlet extends HttpServlet {

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
			if (("updatecomments").equals(action)) {
				String userid = request.getParameter("comm_id");

				UpdateComments(request, response, userid);

			} else if (("deletecomments").equals(action)) {
				String userid = request.getParameter("comm_id");
				DeleteComments(request, response, userid);
			} else if (("updatecommentsinfo").equals(action)) {
				String userid = request.getParameter("comm_id");
				UpdateCommentsInfo(request, response, userid);
			} else if (("selectcommentsbypage").equals(action)) {
				SelectCommentsDispByPage(request, response);
			} else if (("insertcomments").equals(action)) {
				InsertComments(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void UpdateCommentsInfo(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		CommentsService order = new CommentsService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("comments_state") != null) {
			selVal.put("comm_state", request.getParameter("comments_state"));
		}
		selVal.put("comm_id", userid);

		if (order.UpdateComments(selVal)) {
			response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectComments.jsp");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateComments(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		CommentsService comm = new CommentsService();
		Map<String, String> smap = comm.selectCommentsInfoById(userid);
		
		if (smap.size() != 0) {
			GoodsService goods = new GoodsService();
			UserService user = new UserService();
			Map<String, String> gmap = goods.selectGoodsInfoById(smap.get("GOODS_ID"));
			Map<String, String> umap = user.selectUserInfoById(smap.get("BUY_USER"));
			if (gmap.size() != 0 && umap.size() != 0) {
				smap.put("GOODS_NAME", gmap.get("GOODS_NAME"));
				smap.put("USER_NAME", umap.get("USER_NAME"));
				request.setAttribute("commentsinfo", smap);
				RequestDispatcher rd = request
						.getRequestDispatcher("/Admin/UpdateComments.jsp");
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

	public void DeleteComments(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
		CommentsService user = new CommentsService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("comm_id", userid);
		if (user.DeleteComments(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectComments.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}	
	}
	
	public void SelectCommentsDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_comments");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectComments.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void InsertComments(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", request.getParameter("userid"));
		map.put("goods_id", request.getParameter("goodsid"));
		map.put("comm_review", request.getParameter("review"));
		CommentsService comm = new CommentsService();
		if (comm.InsertComments(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/B2cShop/servlet/GoodsServlet?action=goodscontentforward&goodsid=" + map.get("goods_id") + "#reviews");
		} else {
			response.sendRedirect(request.getContextPath()+"/ShowState.jsp");
		}
	}

}
