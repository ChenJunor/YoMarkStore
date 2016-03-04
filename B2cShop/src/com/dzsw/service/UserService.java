package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.UserDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class UserService {

	public Map<String, String> selectUserInfoByName(String username) throws SQLException, ClassNotFoundException {
		UserDao temp = new UserDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("user_name", username);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectUser(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public Map<String, String> selectUserInfoById(String userid) throws SQLException, ClassNotFoundException {
		UserDao temp = new UserDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("user_id", userid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectUser(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public Paging selectUserInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		
		UserDao user  = new UserDao();
		
		Paging p = user.selectUserInfoByPage(map, page);
		
		return  p;
	}
	
	public List<Map<String, String>> selectUser(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		UserDao temp = new UserDao();
		
		List<Map<String, String>> person = temp.selectUser(map);
		
		return person;
	}
	
	public boolean InsertUser(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		UserDao user = new UserDao();
		if (user.insertUser(map)) {
			result = true;
		}
		
		return result;
	}


	public boolean DeleteUser(int user_id) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		UserDao user = new UserDao();
		if (user.deleteUser(user_id)) {
			result = true;
		}
		
		return result;
	}
	

	public boolean UpdateUser(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		UserDao user = new UserDao();
		if (user.updateUser(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println((int)Math.ceil(2.1));
	}
}


