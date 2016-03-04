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
import com.dzsw.dao.utils.Paging;
import com.dzsw.service.AdminService;
import com.dzsw.service.ShopService;
import com.dzsw.service.UserService;

public class ShopServlet extends HttpServlet {

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
			if ("insertshop".equals(action)) {
				InsertShop(request, response);
			} else if (("updateshop").equals(action)) {
				String userid = request.getParameter("shop_id");
				UpdateShop(request, response, userid);
			} else if (("deleteshop").equals(action)) {
				String userid = request.getParameter("shop_id");
				DeleteShop(request, response, userid);
			} else if (("updateshopinfo").equals(action)) {
				String userid = request.getParameter("shop_id");
				UpdateShopInfo(request, response, userid);
			} else if (("updateshop").equals(action)) {
				String userid = request.getParameter("shop_id");
				UpdateShop(request, response, userid);
			} else if (("selectshopbypage").equals(action)) {
				SelectShopDispByPage(request, response);
			} else if (("insertshop").equals(action)) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertShop(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException, ClassNotFoundException {
			
			Map<String, String> selVal = new HashMap<String, String>();
		
			ShopService shop = new ShopService();
			UserService user = new UserService();
			
			Map<String, String> user_info = user.selectUserInfoByName(request.getParameter("user_name"));
			
			if (user_info.size() != 0 && "1".equals(user_info.get("USER_TYPE")) && "1".equals(user_info.get("STATE"))) {
				selVal.put("shop_name", request.getParameter("shop_name"));
				selVal.put("user_id", user_info.get("USER_ID"));
				selVal.put("shop_level", request.getParameter("level"));
				selVal.put("shop_state", request.getParameter("status"));
				selVal.put("state", "1");
			
				if (shop.InsertShop(selVal)) {
					response.sendRedirect(request.getContextPath()+"/Admin/SelectShop.jsp");
				} else {
					response.sendRedirect(request.getContextPath()+"/Admin/error.html");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
	}
	
	public void ApplyShop (HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		Map<String, String> selVal = new HashMap<String, String>();
		String shopname = request.getParameter("shop");
	}
	
	public void UpdateShopInfo(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		ShopService shop = new ShopService();
		Map<String, String> selVal = new HashMap<String, String>();
		Map<String, String> umap = user.selectUserInfoByName("admin");

		
		if (request.getParameter("name") != null) {
			selVal.put("shop_name", request.getParameter("name"));
		}
		if (request.getParameter("user") != null) {
			if (umap.size() != 0) {
				selVal.put("user_id", umap.get("USER_ID"));
			}
		}
		if (request.getParameter("level") != null) {
			selVal.put("shop_level", request.getParameter("level"));
		}
		selVal.put("shop_id", userid);

		if (selVal.size() != 1 && shop.UpdateShop(selVal)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectShop.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	public void UpdateShop(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		UserService user = new UserService();
		ShopService shop = new ShopService();
		Map<String, String> smap = shop.selectShopInfoById(userid);
		Map<String, String> umap = new HashMap<String, String>();
		if (smap.size() != 0) {
			umap = user.selectUserInfoById(smap.get("USER_ID"));
			smap.put("USER_NAME", (umap.size() != 0) ? umap.get("USER_NAME"):"");
			request.setAttribute("shopinfo", smap);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/UpdateShop.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void DeleteShop(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		ShopService user = new ShopService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("shop_id", userid);
		if (user.DeleteShop(map)) {
			response.sendRedirect(request.getContextPath()+"/Admin/SelectShop.jsp");
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void SelectShopDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_shop");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectShop.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
}
