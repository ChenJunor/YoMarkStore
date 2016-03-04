package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.AdminDao;
import com.dzsw.dao.CommentsDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class CommentsService {
	public Paging selectCommentsInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		CommentsDao comments  = new CommentsDao();
		
		Paging p = comments.selectCommentsInfoByPage(map, page);
		
		return  p;
	}
	
	public Map<String, String> selectCommentsInfoById(String userid) throws SQLException, ClassNotFoundException {
		CommentsDao temp = new CommentsDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("comm_id", userid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectComments(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public List<Map<String, String>> selectComments(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		CommentsDao comments = new CommentsDao();
		list = comments.selectComments(map);

		return list;
	}
	
	public boolean InsertComments(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		CommentsDao comments = new CommentsDao();
		if (comments.insertComments(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteComments(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		CommentsDao comments = new CommentsDao();
		if (comments.deleteComments(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateComments(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		CommentsDao comments = new CommentsDao();
		if (comments.updateComments(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		CommentsService user = new CommentsService();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		list.add(new HashMap<String, String>());
		Map<String, String> wmap = new HashMap<String, String>();
		
		wmap.put("goods_id", "1");
		list.add(wmap);
		System.out.println(list);
		System.out.println(user.selectComments(list));
	}
}
