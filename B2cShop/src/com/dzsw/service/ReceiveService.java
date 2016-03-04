package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.AdminDao;
import com.dzsw.dao.OrderDao;
import com.dzsw.dao.ReceiveDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class ReceiveService {
	public Paging selectReceiveInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		ReceiveDao receive  = new ReceiveDao();
		
		Paging p = receive.selectReceiveInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectReceiveInfoById(String userid) throws SQLException, ClassNotFoundException {
		ReceiveDao temp = new ReceiveDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("rece_id", userid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectReceive(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectReceive(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		ReceiveDao receive = new ReceiveDao();
		receive.selectReceive(map);
		
		return list;
	}
	
	public boolean InsertReceive(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReceiveDao receive = new ReceiveDao();
		if (receive.insertReceive(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteReceive(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReceiveDao receive = new ReceiveDao();
		if (receive.deleteReceive(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateReceive(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReceiveDao receive = new ReceiveDao();
		if (receive.updateReceive(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
