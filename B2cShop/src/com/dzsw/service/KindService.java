package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.KindDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.UserDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class KindService {
	
	public Paging selectKindInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		KindDao kind  = new KindDao();
		
		Paging p = kind.selectKindInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectKindInfoById(String kind_id) throws SQLException, ClassNotFoundException {
		KindDao temp = new KindDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("kind_id", kind_id);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectKind(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public Map<String, String> selectKindInfoByName(String kindname) throws SQLException, ClassNotFoundException {
		KindDao temp = new KindDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("kind_name", kindname);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectKind(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	
	public List<Map<String, String>> selectKind(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		KindDao kind = new KindDao();
		list = kind.selectKind(map);
		
		return list;
	}
	
	public boolean InsertKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		KindDao kind = new KindDao();
		if (kind.insertKind(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		KindDao kind = new KindDao();
		if (kind.deleteKind(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		KindDao kind = new KindDao();
		if (kind.updateKind(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		KindService user = new KindService();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("kind_id", "1");
		list.add(wmap);
		System.out.println(list);
		List<Map<String, String>> temp = user.selectKind(list);
		System.out.println(temp);
	}
}
