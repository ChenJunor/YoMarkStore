package com.dzsw.dao.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Paging {
	//当前页码
	private int page;
	
	//总页数
	private int pageCount;
	
	//每页显示个数。
	private int pageRow;
	
	//记录总数
	private int count;
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	private List<String> tables;
	
	private List<Map<String, String>> values;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	//获得记录总数
	public int getListCount(List<String> table_name,
			List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		
		count = DButils.selectRecordsCounts(table_name, map);
		
		return count;
	}
	
	//构造函数，赋值
	public Paging(List<String> table_name,
			List<Map<String, String>> map, int page) throws SQLException, ClassNotFoundException {
		pageRow = 10;
		count = getListCount(table_name, map);
		pageCount = (int)Math.ceil(count / (pageRow * 1.0));
		tables = table_name;
		values = map;
		this.page = page;
		if (page <= 0) {
			this.page = 1;
		} else if (page > pageCount) {
			this.page = pageCount;
		}
		
	}
	
	//查询数据表中，第page页数据。
	public List<Map<String, String>> selectListPage() throws SQLException, ClassNotFoundException{
		List<Map<String, String>> list = DButils.selectRecordsByPage(tables, values, pageRow, page);
		return list;
	}
}
