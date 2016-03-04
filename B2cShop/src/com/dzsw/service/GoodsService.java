package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.GoodsDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class GoodsService {
	
	public Paging  selectGoodsInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		GoodsDao goods  = new GoodsDao();
		
		Paging p = goods.selectGoodsInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectGoodsInfoByName(String goodsname) throws SQLException, ClassNotFoundException {
		GoodsDao temp = new GoodsDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("goods_name", goodsname);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectGoods(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	public Map<String, String> selectGoodsInfoById(String goodsid) throws SQLException, ClassNotFoundException {
		GoodsDao temp = new GoodsDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("goods_id", goodsid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectGoods(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectGoods(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		GoodsDao goods = new GoodsDao();
		goods.selectGoods(map);

		return list;
	}
	
	public boolean InsertGoods(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		GoodsDao goods = new GoodsDao();
		if (goods.insertGoods(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteGoods(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		GoodsDao goods = new GoodsDao();
		if (goods.deleteGoods(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateGoods(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		GoodsDao goods = new GoodsDao();
		if (goods.updateGoods(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
