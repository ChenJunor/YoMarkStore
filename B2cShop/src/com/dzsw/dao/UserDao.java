package com.dzsw.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.utils.DButils;
//import com.dzsw.domain.User;
import com.dzsw.dao.utils.Paging;

public class UserDao {

	public Paging selectUserInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_user");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		list.add(new HashMap<String, String>());
		map.put("state", "1");
		list.add(map);
		
		Paging p = new Paging(table, list, page);
		
		return p;
	}
	
	public List<Map<String, String>> selectUser(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		List<String> table = new ArrayList<String>(DButils.MAXSIZE);
		
		table.add("b2c_user");
		
		list = DButils.selectRecords(table, map);
		
		return list;
	}
	

	public boolean insertUser(Map<String, String> map) throws SQLException, ClassNotFoundException {
		
		boolean result = false;
		
		if (DButils.insertRecord("b2c_user", map, "user_id")) {
			result = true;
		}
		
		return result;
	}
	
	public boolean deleteUser(int user_id) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", String.valueOf(user_id));
		
		if (DButils.deleteRecord("b2c_user", map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean updateUser(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		if (DButils.updateRecord("b2c_user", map, "user_id")) {
			result = true;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		UserDao dao = new UserDao();
		List<Map<String, String>> list = DButils.funcTests();
		System.out.println(dao.selectUser(list));
	}
	
}
