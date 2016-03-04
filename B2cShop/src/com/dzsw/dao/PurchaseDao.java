package com.dzsw.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.utils.DButils;

public class PurchaseDao {

	public List<Map<String, String>> selectPurchase(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_purchase");
		
		list = DButils.selectRecords(table, map);
		
		return list;
	}
	

	public boolean insertPurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
		
		if (DButils.insertRecord("b2c_purchase", map, "purch_id")) {
			result = true;
		}
		
		return result;
	}
	

	public boolean deletePurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.deleteRecord("b2c_purchase", map)) {
			result = true;
		}
		
		return result;
	}
	

	public boolean updatePurchase(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.updateRecord("b2c_purchase", map, "purch_id")) {
			result = true;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
	}
}
