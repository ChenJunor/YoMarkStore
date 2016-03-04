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
import com.dzsw.service.KindService;
import com.dzsw.service.KindService;
import com.dzsw.service.StorageService;
import com.dzsw.service.UserService;

public class KindServlet extends HttpServlet {

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
			if ("insertkind".equals(action)) {
				InsertKind(request, response);
			} else if (("updatekind").equals(action)) {
				String userid = request.getParameter("kind_id");
				System.out.println(userid);
				UpdateKind(request, response, userid);
			} else if (("deletekind").equals(action)) {
				String userid = request.getParameter("kind_id");
				DeleteKind(request, response, userid);
			} else if (("updatekindinfo").equals(action)) {
				String userid = request.getParameter("kind_id");
				UpdateKindInfo(request, response, userid);
			} else if (("selectkindbypage").equals(action)) {
				SelectKindDispByPage(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void InsertKind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ClassNotFoundException {
		Map<String, String> selVal = new HashMap<String, String>();
		String fatKindName = request.getParameter("fname");
		
		KindService kind = new KindService();
		if (fatKindName != "") {
			Map<String, String> kind_info = kind.selectKindInfoByName(request.getParameter("fname"));

			if (kind_info.size() != 0 && "1".equals(kind_info.get("STATE"))) {
				
				selVal.put("kind_fath", kind_info.get("KIND_ID"));
				selVal.put("kind_name", request.getParameter("name"));
				selVal.put("kind_remark", request.getParameter("other"));
				selVal.put("kind_hide", request.getParameter("status"));
			}
			if (kind.InsertKind(selVal)) {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectKind.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/error.html");
			}
		} else {
			selVal.put("kind_name", request.getParameter("name"));
			selVal.put("kind_remark", request.getParameter("other"));
			selVal.put("kind_hide", request.getParameter("status"));
			if (kind.InsertKind(selVal)) {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/SelectKind.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/Admin/error.html");
			}
		}
	}
	public void UpdateKindInfo(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		KindService kind = new KindService();
		Map<String, String> selVal = new HashMap<String, String>();
		Map<String, String> kmap = new HashMap<String, String>();
		
		if (request.getParameter("kind") != null) {
			selVal.put("kind_name", request.getParameter("kind"));
		}
		if (request.getParameter("fath") != null) {
			kmap = kind.selectKindInfoByName(request.getParameter("fath"));
			if (kmap.size() != 0) {
				selVal.put("KIND_NAME", (kmap.size() != 0) ? kmap.get("KIND_FATH") : "");
			}
		}
		if (request.getParameter("remark") != null) {
			selVal.put("kind_remark", request.getParameter("remark"));
		}
		selVal.put("kind_id", userid);

		if (selVal.size() != 1 && kind.UpdateKind(selVal)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectKind.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	public void UpdateKind(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		KindService Kind = new KindService();
		Map<String, String> smap = Kind.selectKindInfoById(userid);
		Map<String, String> umap = new HashMap<String, String>();
		if (smap.size() != 0) {
			if (smap.get("KIND_FATH") != "0" && smap.get("KIND_FATH") != "") {
				umap = Kind.selectKindInfoById(smap.get("KIND_FATH"));
				smap.put("FATH_NAME", (umap.size() != 0) ? umap.get("KIND_NAME"):"");
			} else {
				smap.put("FATH_NAME", "");
			}
			
			request.setAttribute("kindinfo", smap);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/UpdateKind.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
	
	public void DeleteKind(HttpServletRequest request, HttpServletResponse response, String userid) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		KindService user = new KindService();
		GoodsService goods = new GoodsService();
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("kind_fath", userid);
		list.add(wmap);
		List<Map<String, String>> temp = user.selectKind(list);
		
		List<Map<String, String>> gmap = new ArrayList<Map<String, String>>();
		gmap.add(new HashMap<String, String>());
		Map<String, String> gval = new HashMap<String, String>();
		gval.put("kind_id", userid);
		gmap.add(gval);
		List<Map<String, String>> result = goods.selectGoods(gmap);
		System.out.println(temp.size());
		System.out.println(temp.size());
		System.out.println("=============");
		if (temp.size() + result.size() > 0) {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		} else {
			if (user.DeleteKind(gval)) {
				response.sendRedirect(request.getContextPath()+"/Admin/SelectKind.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/Admin/error.html");
			}
		}

	}
	
	public void SelectKindDispByPage(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, SQLException, ClassNotFoundException {
		int page = Integer.valueOf(request.getParameter("page"));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> map = new HashMap<String, String>();
		list.add(map);
		List<String> table = new ArrayList<String>(10);
		table.add("b2c_kind");
		Paging ptools = new Paging(table, list, page);
		System.out.println(ptools);
		if (ptools != null) {
			request.setAttribute("flag", "1");
			request.setAttribute("result", ptools.selectListPage());
			request.setAttribute("pages", ptools);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/SelectKind.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/Admin/error.html");
		}
	}
}
