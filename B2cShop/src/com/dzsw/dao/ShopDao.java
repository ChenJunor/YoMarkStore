package com.dzsw.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class ShopDao {

	public List<Map<String, String>> selectShop(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_shop");
		
		list = DButils.selectRecords(table, map);
		
		return list;
	}
	
	public Paging selectShopInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_shop");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		list.add(new HashMap<String, String>());
		list.add(map);
		
		Paging p = new Paging(table, list, page);
		
		return p;
	}
	
	public boolean insertShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
		
		if (DButils.insertRecord("b2c_shop", map, "shop_id")) {
			result = true;
		}
		
		return result;
	}
	

	public boolean deleteShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		
		
		if (map.size() != 0) {
			if (map.keySet().toString().indexOf("shop_id") != -1) {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
				list.add(new HashMap<String, String>());
				Map<String, String> gmap = new HashMap<String, String>();
				gmap.put("shop_id", map.get("shop_id"));
				list.add(gmap);
				GoodsDao goods = new GoodsDao();
				
				List<Map<String, String>> res = goods.selectGoods(list);
				if (res.size() == 0) {
					if (DButils.deleteRecord("b2c_shop", map)) {
						result = true;
					}
				}
			}
		}

		return result;
	}
	

	public boolean updateShop(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.updateRecord("b2c_shop", map, "shop_id")) {
			result = true;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ShopDao user = new ShopDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("shop_id", "27");
		list.add(wmap);
		System.out.println(list);
		System.out.println(user.deleteShop(wmap));
	}
}
