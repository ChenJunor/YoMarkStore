package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.CommentsDao;
import com.dzsw.dao.KindDao;
import com.dzsw.dao.OrderDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class OrderService {
	public Paging selectOrderInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		OrderDao comments  = new OrderDao();
		
		Paging p = comments.selectOrderInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectOrderInfoById(String order_id) throws SQLException, ClassNotFoundException {
		OrderDao temp = new OrderDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("order_id", order_id);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectOrder(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectOrder(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		OrderDao order = new OrderDao();
		order.selectOrder(map);
		
		return list;
	}
	
	public boolean InsertOrder(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		OrderDao order = new OrderDao();
		if (order.insertOrder(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteOrder(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		OrderDao order = new OrderDao();
		if (order.deleteOrder(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateOrder(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		OrderDao order = new OrderDao();
		if (order.updateOrder(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
