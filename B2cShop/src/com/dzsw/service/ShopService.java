package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.AdminDao;
import com.dzsw.dao.KindDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.UserDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class ShopService {
	
	public Paging selectShopInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		ShopDao shop  = new ShopDao();
		
		Paging p = shop.selectShopInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectShopInfoByName(String shopname) throws SQLException, ClassNotFoundException {
		ShopDao temp = new ShopDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("shop_name", shopname);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectShop(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public Map<String, String> selectShopInfoById(String shopid) throws SQLException, ClassNotFoundException {
		ShopDao temp = new ShopDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("shop_id", shopid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectShop(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	

	public List<Map<String, String>> selectShop(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		ShopDao shop = new ShopDao();
		shop.selectShop(map);
		
		return list;
	}
	
	public boolean InsertShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ShopDao shop = new ShopDao();
		if (shop.insertShop(map)) {
			result = true;
		}
		
		return result;
	}


	public boolean DeleteShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ShopDao shop = new ShopDao();
		if (shop.deleteShop(map)) {
			result = true;
		}
		
		return result;
	}
	

	public boolean UpdateShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ShopDao shop = new ShopDao();
		if (shop.updateShop(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ShopDao user = new ShopDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("shop_id", "28");
		list.add(wmap);
		System.out.println(list);
		System.out.println(user.deleteShop(wmap));
	}
}
