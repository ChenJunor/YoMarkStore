package com.dzsw.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dzsw.dao.CommentsDao;
import com.dzsw.dao.ReportDao;
import com.dzsw.dao.ShopDao;
import com.dzsw.dao.utils.DButils;
import com.dzsw.dao.utils.Paging;

public class ReportService {

	public List<Map<String, String>> selectReport(List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		ReportDao report = new ReportDao();
		report.selectReport(map);
		
		return list;
	}
	public Map<String, String> selectReportInfoById(String userid) throws SQLException, ClassNotFoundException {
		ReportDao temp = new ReportDao();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(10);
		Map<String, String> connMap = new HashMap<String, String>();
		Map<String, String> selMap = new HashMap<String, String>();
		list.add(connMap);
		selMap.put("repo_id", userid);
		list.add(selMap);
		List<Map<String, String>> result = temp.selectReport(list);
		
		return  (result.size() != 0) ? result.get(0) : new HashMap<String, String>();
	}
	
	public Paging selectReportInfoByPage(Map<String, String> map, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(DButils.MAXSIZE);
		
		ReportDao shop  = new ReportDao();
		
		Paging p = shop.selectReportInfoByPage(map, page);
		
		return  p;
	}
	

	public boolean InsertReport(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReportDao report = new ReportDao();
		if (report.insertReport(map)) {
			result = true;
		}
		
		return result;
	}

	public boolean DeleteReport(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReportDao report = new ReportDao();
		if (report.deleteReport(map)) {
			result = true;
		}
		
		return result;
	}
	
	public boolean UpdateReport(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		ReportDao report = new ReportDao();
		if (report.updateReport(map)) {
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ReportService repo = new ReportService();
		System.out.println(repo.selectReportInfoById("1"));
	}
}
