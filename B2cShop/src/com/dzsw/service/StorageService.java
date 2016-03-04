package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.ShopDao;
import com.dzsw.dao.StorageDao;
import com.dzsw.dao.utils.DButils;

public class StorageService {
	public List<Map<String, String>> selectStorageInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		StorageDao shop  = new StorageDao();
		
		result = shop.selectStorageInfoByPage(map, page);
		
		return  (result.size() != 0) ? result : new ArrayList<Map<String, String>>(100);
	}
	
	public Map<String, String> selectStorageInfoByName(String storname) throws SQLException, ClassNotFoundException {
		StorageDao temp = new StorageDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("stor_name", storname);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectStorage(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectStorage(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		StorageDao storage = new StorageDao();
		storage.selectStorage(map);
		
		return list;
	}
	
	public boolean InsertStorage(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		StorageDao storage = new StorageDao();
		if (storage.insertStorage(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteStorage(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		StorageDao storage = new StorageDao();
		if (storage.deleteStorage(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateStorage(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		StorageDao storage = new StorageDao();
		if (storage.updateStorage(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
