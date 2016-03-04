package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.AdminDao;
import com.dzsw.dao.UserDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class AdminService {

	public Paging selectAdminInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		AdminDao admin  = new AdminDao();
		
		Paging p = admin.selectAdminInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectAdminInfoById(String userid) throws SQLException, ClassNotFoundException {
		AdminDao temp = new AdminDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("admin_id", userid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectAdmin(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectAdmin(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		AdminDao temp = new AdminDao();
		
		List<Map<String, String>> person = temp.selectAdmin(map);
		
		return person;
	}
	

	public boolean InsertAdmin(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		AdminDao admin = new AdminDao();
		if (admin.insertAdmin(map)) {
			result = true;
		}
		
		return result;
	}


	public boolean DeleteAdmin(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		AdminDao admin = new AdminDao();
		if (admin.deleteAdmin(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateAdmin(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		AdminDao admin = new AdminDao();
		if (admin.updateAdmin(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
	}
}
