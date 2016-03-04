package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.PurchaseDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.utils.DButils;

public class PurchaseService {
	public List<Map<String, String>> selectPurchase(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		PurchaseDao purchase = new PurchaseDao();
		list = purchase.selectPurchase(map);
		
		return list;
	}
	
	public boolean InsertPurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		PurchaseDao purchase = new PurchaseDao();
		if (purchase.insertPurchase(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeletePurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		PurchaseDao purchase = new PurchaseDao();
		if (purchase.deletePurchase(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdatePurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		PurchaseDao purchase = new PurchaseDao();
		if (purchase.updatePurchase(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		PurchaseDao user = new PurchaseDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("purch_type", "0");
		wmap.put("user_id", "1");
		list.add(wmap);
		System.out.println(list);
		System.out.println(user.selectPurchase(list));
	}
}
