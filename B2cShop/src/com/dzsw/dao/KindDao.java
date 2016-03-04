package com.dzsw.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class KindDao {
	
	public Paging selectKindInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_kind");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		list.add(new HashMap<String, String>());
		list.add(map);
		
		Paging p = new Paging(table, list, page);
		
		return p;
	}
	
	public List<Map<String, String>> selectKind(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_kind");
		
		list = DButils.selectRecords(table, map);
		
		return list;
	}
	
	public boolean insertKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
		
		if (DButils.insertRecord("b2c_kind", map, "kind_id")) {
			result = true;
		}
		
		return result;
	}
	
	public boolean deleteKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;

		if (DButils.deleteRecord("b2c_kind", map)) {
			result = true;
		}
		
		return result;
	}

	public boolean updateKind(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.updateRecord("b2c_kind", map, "kind_id")) {
			result = true;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		KindDao user = new KindDao();
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
