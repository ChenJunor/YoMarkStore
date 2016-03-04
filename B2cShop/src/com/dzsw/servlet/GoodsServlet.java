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
import com.dzsw.service.CommentsService;
import com.dzsw.service.GoodsService;
import com.dzsw.service.KindService;
import com.dzsw.service.OrderService;
import com.dzsw.service.PurchaseService;
import com.dzsw.service.ShopService;
import com.dzsw.service.UserService;

public class GoodsServlet extends HttpServlet {

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
			if ("insertgoods".equals(action)) {
				InsertGoods(request, response);
			} else if ("goodscontent".equals(action)) {
				//后台
				String goodsid = request.getParameter("goodsid");
				GoodsContent(request, response, goodsid, 0);
			} else if (("updategoods").equals(action)) {
				String adminid = request.getParameter("goodsid");
				UpdateGoods(request, response, adminid);
			} else if (("deletegoods").equals(action)) {
				String adminid = request.getParameter("goodsid");
				DeleteGoods(request, response, adminid);
			} else if (("updategoodsinfo").equals(action)) {
				String adminid = request.getParameter("goodsid");
				UpdateGoodsInfo(request, response, adminid);
			} else if (("selectgoodsbypage").equals(action)) {
				SelectGoodsDispByPage(request, response);
			} else if (("searchgoodsbykindid").equals(action)) {
				SelectGoodsDispByKind(request, response);
			} else if (("goodscontentforward").equals(action)) {
				String goodsid = request.getParameter("goodsid");
				GoodsContent(request, response, goodsid, 1);				
			} else if (("inserttocart").equals(action)) {
				InsertToCart(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ClassNotFoundException {

		Map<String, String> selVal = new HashMap<String, String>();

		ShopService shop = new ShopService();
		KindService kind = new KindService();
		GoodsService goods = new GoodsService();

		Map<String, String> shop_info = shop.selectShopInfoByName(request
				.getParameter("shop").toString());
		Map<String, String> kind_info = kind.selectKindInfoByName(request
				.getParameter("kind").toString());

		System.out.println(shop_info);
		System.out.println(kind_info);

		if (shop_info.size() != 0 && kind_info.size() != 0
				&& "1".equals(shop_info.get("STATE"))) {
			selVal.put("goods_name", request.getParameter("name"));
			selVal.put("shop_id", shop_info.get("SHOP_ID").toString());
			selVal.put("kind_id", kind_info.get("KIND_ID").toString());
			selVal.put("goods_orign", request.getParameter("orign"));
			selVal.put("ord_price", request.getParameter("price"));
			selVal.put("vip_price", request.getParameter("pricevip"));
			selVal.put("goods_onsale", request.getParameter("status"));
			selVal.put("goods_content", request.getParameter("content"));
			selVal.put("goods_inventory", request.getParameter("storage"));
			selVal.put("state", "1");

			if (goods.InsertGoods(selVal)) {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectGoods.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/error.html");
			}
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void GoodsContent(HttpServletRequest request,
			HttpServletResponse response, String goodsid, int state)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		GoodsService goods = new GoodsService();
		Map<String, String> map = goods.selectGoodsInfoById(goodsid);

		if (map.size() != 0) {
			KindService kind = new KindService();
			Map<String, String> kmap = kind.selectKindInfoById(map
					.get("KIND_ID"));

			ShopService shop = new ShopService();
			Map<String, String> smap = shop.selectShopInfoById(map
					.get("SHOP_ID"));

			map.put("KIND_NAME", kmap.get("KIND_NAME"));
			map.put("SHOP_NAME", smap.get("SHOP_NAME"));
			request.setAttribute("goodsinfo", map);
			RequestDispatcher rd;
			if (state == 0) {
				rd = request.getRequestDispatcher("/Admin/GoodsContent.jsp");
				rd.forward(request, response);
			} else {
				CommentsService comments = new CommentsService();
				List<Map<String, String>> searlist = new ArrayList<Map<String, String>>();
				searlist.add(new HashMap<String, String>());
				Map<String, String> m = new HashMap<String, String>();
				m.put("GOODS_ID", goodsid);
				searlist.add(m);
				List<Map<String, String>> res = comments.selectComments(searlist);

				request.setAttribute("commentsinfo", res);
				rd = request.getRequestDispatcher("/GoodsContent.jsp");
				rd.forward(request, response);				
			}
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateGoods(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		GoodsService user = new GoodsService();
		Map<String, String> map = user.selectGoodsInfoById(userid);
		if (map.size() != 0) {
			KindService kind = new KindService();
			Map<String, String> kmap = kind.selectKindInfoById(map
					.get("KIND_ID"));

			ShopService shop = new ShopService();
			Map<String, String> smap = shop.selectShopInfoById(map
					.get("SHOP_ID"));

			map.put("KIND_NAME", kmap.get("KIND_NAME"));
			map.put("SHOP_NAME", smap.get("SHOP_NAME"));
			request.setAttribute("goodsinfo", map);
			RequestDispatcher rd = request
					.getRequestDispatcher("/Admin/UpdateGoods.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/Admin/error.html");
		}
	}

	public void UpdateGoodsInfo(HttpServletRequest request,
			HttpServletResponse response, String userid)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {
		GoodsService admin = new GoodsService();
		Map<String, String> selVal = new HashMap<String, String>();
		if (request.getParameter("name") != null) {
			selVal.put("goods_name", request.getParameter("name"));
		}
		if (request.getParameter("email") != null) {
			selVal.put("goods_email", request.getParameter("email"));
		}
		if (request.getParameter("password") != null) {
			selVal.put("password", request.getParameter("password"));
		}
		selVal.put("goods_id", userid);

		if (admin.UpdateGoods(selVal)) {
			Map<String, String> map = admin.selectGoodsInfoById(userid);
			if (map.size() != 0) {
				request.setAttribute("goodsinfo", map);
				RequestDispatcher rd = request
						.getRequestDispatcher("/Admin/SelectGoods.jsp");
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
	
	public void DeleteGoods(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		GoodsService user = new GoodsService();
		Map<String, String> map = new HashMap<String, String>();
		map.put("goods_id", userid);
		if (user.DeleteGoods(map)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectGoods.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void SelectGoodsDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_goods");
		Paging ptools = new Paging(table, list, page);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectGoods.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}

	public void SelectGoodsDispByKind(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		map.put("kind_id", request.getParameter("kindid"));
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_goods");
		Paging ptools = new Paging(table, list, 1);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/ShopKindDisp.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/ShowState.jsp");
		}
	}
	public void InsertToCart(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		Map<String, String> map = new HashMap<String, String>();
		
		if (request.getParameter("goodsid") != null) {
			map.put("goods_id", request.getParameter("goodsid").toString());
		}
		if (request.getParameter("number") != null) {
			map.put("purch_num", request.getParameter("number").toString());
		}
		if (request.getParameter("price") != null) {
			map.put("purch_price", request.getParameter("price").toString());
		}
		if (request.getParameter("userid") != null) {
			map.put("user_id", request.getParameter("userid").toString());
		}
		map.put("purch_type", "0");
		map.put("state", "1");
		PurchaseService order = new PurchaseService();
		
		if (order.InsertPurchase(map)) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			response.sendRedirect(request.getContextPath()+"/ShowStates.jsp");
		}
	}
}
