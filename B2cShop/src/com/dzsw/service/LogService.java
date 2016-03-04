package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.AdminDao;
import com.dzsw.dao.LogDao;
import com.dzsw.dao.OrderDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class LogService {
	
	public Map<String, String> selectLogInfoById(String order_id) throws SQLException, ClassNotFoundException {
		LogDao temp = new LogDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("log_id", order_id);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectLog(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectLog(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		LogDao log = new LogDao();
		log.selectLog(map);
		
		return list;
	}
	
	public Paging selectLogInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		LogDao admin  = new LogDao();
		
		Paging p = admin.selectLogInfoByPage(map, page);
		
		return  p;
	}
	
	public boolean InsertLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		LogDao log = new LogDao();
		if (log.insertLog(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		LogDao log = new LogDao();
		if (log.deleteLog(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		LogDao log = new LogDao();
		if (log.updateLog(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
