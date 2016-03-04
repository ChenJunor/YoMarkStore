package com.dzsw.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class LogDao {

	public List<Map<String, String>> selectLog(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_log");
		
		list = DButils.selectRecords(table, map);
		
		return list;
	}
	
	public Paging selectLogInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_log");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		list.add(new HashMap<String, String>());
		list.add(map);
		
		Paging p = new Paging(table, list, page);
		
		return p;
	}
	
	public boolean insertLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
		
		if (DButils.insertRecord("b2c_log", map, "log_id")) {
			result = true;
		}
		
		return result;
	}
	
	public boolean deleteLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.deleteRecord("b2c_log", map)) {
			result = true;
		}
		
		return result;
	}
	

	public boolean updateLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.updateRecord("b2c_log", map, "log_id")) {
			result = true;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
	}
}
