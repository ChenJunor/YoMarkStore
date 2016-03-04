package com.dzsw.dao.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DButils {
	
	public final static int MAXSIZE = 1000;
	public static String DBdriver;
	public static String DBurl;
	public static String DBuser;
	public static String DBpassword;

	/*
	 * 获取数据库连接，底层调用函数，从配置文件里读取参数
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection conn = null;
//		Map<String, String> map = WebConfig.GetDbConfig();
		
//		DBdriver = map.get("DB_Driver");
//		DBurl = map.get("DB_Url");
//		DBuser = map.get("DB_User");
//		DBpassword = map.get("DB_Password");
		
		DBurl = "jdbc:oracle:thin:@localhost:1521:orcl";
		DBuser = "b2c";
		DBpassword = "shop";
		DBdriver = "oracle.jdbc.driver.OracleDriver";
		
		
		Class.forName(DBdriver);
		
		conn = DriverManager.getConnection(DBurl,DBuser,DBpassword);
		return conn; 
	}

	/*
	 * 拼接多表查询语句：table_name是列表类型的数据表名字列表。
	 * map中包括两部分，第一部分是sql语句的连接条件，类似{b2c_user.user_id,b2c_admin.damin_id}的形式
	 * 第二部分是sql语句的筛选条件。如{user_id,1} 1是状态字段，查询记录条数或者查询全部 2
	 * 1 查询全部, 2 查询数量
	 */
	public static String selectsSql(List<String> table_name,
			List<Map<String, String>> map, int state) {
		String sql = (state == 1) ? "SELECT * FROM " : "SELECT COUNT(*) FROM ";
		for(int i = 0; i < table_name.size(); i++) {
			if (i != 0) {
				sql += "," + table_name.get(i);
			} else {
				sql += table_name.get(i);
			}
		}
		if (map.size() == 2) {
			Map<String, String> connVal = map.get(0);
			Map<String, String> selVal = map.get(1);
			
			if (connVal.size() == 0 && selVal.size() == 0) {
				return sql + " WHERE STATE=1";
			}
			
			if (connVal.size() != 0) {
				Set<String> keys = connVal.keySet();
				Iterator<String> key_iter = keys.iterator();
			
				Collection<String> values = connVal.values();
				Iterator<String> values_iter = values.iterator();
			
				String content = "";
				
				sql += " WHERE ";
				
				if (connVal.size() == 1) {
					content += key_iter.next() + "=" + values_iter.next();
				} else {
					content += key_iter.next() + "=" + values_iter.next();
					while(key_iter.hasNext()){
						content += " AND " + key_iter.next() + "=" + values_iter.next();
					}
				}
				sql += content;
			}
			if (selVal.size() != 0) {
				if (connVal.size() != 0) {
					sql += " AND ";
				} else {
					sql += " WHERE ";
				}
				Set<String> keys = selVal.keySet();
				Iterator<String> key_iter = keys.iterator();
			
				String content = "";
				
				if (map.size() == 1) {
					content += key_iter.next() + "=" + "?";
				} else {
					content += key_iter.next() + "=" + "?";
					while(key_iter.hasNext()){
						content += " AND " + key_iter.next() + "=" + "?";
					}
				}
				sql += content;
			}
		}
		if (sql.indexOf("WHERE") == -1) {
			sql += " WHERE STATE=1";
		} else {
			sql += " AND STATE=1";
		}
		return sql;
	}

	/*
	 * 获得key_name的自增id，其中key_name是自增序列
	 */
	public static int getAutoId(String key_name) throws SQLException, ClassNotFoundException {
		int result = 0;
		
		Connection conn = DButils.getConnection();
		
		String sql = "SELECT " + key_name + ".nextval from dual";
		
		PreparedStatement stat = conn.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}
		DButils.close(conn, stat, rs);
		return result;
	}


	/*
	 * 多表联合查询，返回查询结果。 传入表名的列表和包括连接条件和查询条件的列表。
	 */
	public static List<Map<String, String>> selectRecords(List<String> table_name, List<Map<String, String>> map) throws SQLException, ClassNotFoundException{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(MAXSIZE);
		
		Connection conn = DButils.getConnection();
		
		String sql = selectsSql(table_name, map, 1);
		
		PreparedStatement stat = conn.prepareStatement(sql);
		
		if (map.size() == 2) {
			Map<String, String> selVal = map.get(1);
			
			if (selVal.size() != 0) {
				Collection<String> values = selVal.values();
				Iterator<String> values_iter = values.iterator();
				int i = 0;
				while(values_iter.hasNext()) {
					String temp = values_iter.next();
					if (temp.matches("[0-9]*[0-9/.][0-9]*")) {
						if (temp.contains(".")) {
							stat.setFloat(++i, Float.valueOf(temp));
						} else {
							stat.setInt(++i, Integer.valueOf(temp));
						}
					} else {
						stat.setString(++i, temp);
					}
				}
			}
		}
		ResultSet rs = stat.executeQuery();

		while (rs.next()) {
			Map<String, String> mtmp = new HashMap<String, String>();
			for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
				mtmp.put(rs.getMetaData().getColumnName(j), String.valueOf(rs.getObject(j)));
			}
			list.add(mtmp);
		}
		DButils.close(conn, stat);
		return list;
	}

	/*
	 * 查询符合条件的数量并返回结果
	 */
	public static int selectRecordsCounts(List<String> table_name,
			List<Map<String, String>> map) throws SQLException, ClassNotFoundException {
		int count = 0;
		
		Connection conn = DButils.getConnection();
		
		String sql = selectsSql(table_name, map, 0);
		
		PreparedStatement stat = conn.prepareStatement(sql);
		
		if (map.size() == 2) {
			Map<String, String> selVal = map.get(1);
			
			if (selVal.size() != 0) {
				Collection<String> values = selVal.values();
				Iterator<String> values_iter = values.iterator();
				int i = 0;
				while(values_iter.hasNext()) {
					String temp = values_iter.next();
					if (temp.matches("[0-9]*[0-9/.][0-9]*")) {
						if (temp.contains(".")) {
							stat.setFloat(++i, Float.valueOf(temp));
						} else {
							stat.setInt(++i, Integer.valueOf(temp));
						}
					} else {
						stat.setString(++i, temp);
					}
				}
			}
		}
		ResultSet rs = stat.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		DButils.close(conn, stat, rs);
		return count;
	}

	/*
	 * 模糊查询。 传入参数：关键字，以空格隔开。查的数据表的列表。连接条件与查询条件拼接成的list。
	 */
	public static List<Map<String, String>> fuzzySearchRecords(String key_words, List<String> table_name) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(MAXSIZE);
		
		Map<String, String> search_map = new HashMap<String, String>();
		List<Map<String, String>> search_list = new ArrayList<Map<String, String>>(MAXSIZE);
		search_list.add(search_map);
		String[] key_word = key_words.split(" ");
		
		for (String table : table_name) {
			List<String> tab = new ArrayList<String>(5);
			tab.add(table);
			List<Map<String, String>> list = DButils.selectRecords(tab, search_list);
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> temp = list.get(i);
				int flag = 0;
				for (int j = 0; j < key_word.length; j++) {
					if ("0".equals(key_word[j])) {
						break;
					}
					if (temp.values().toString().indexOf(key_word[j]) != -1) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					result.add(temp);
				}
			}
		}
		return result;
	}
	/*
	 * 分页底层函数
	 */
	public static List<Map<String, String>>selectRecordsByPage(List<String> table_name,
			List<Map<String, String>> map, int pageRow, int page) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(MAXSIZE);
		String sql = "SELECT * FROM (SELECT x.*,rownum rn FROM (";
		sql += DButils.selectsSql(table_name, map, 1) + ") x ";
		sql += "WHERE rownum<=" + pageRow*page + ") WHERE rn>=" + ((page-1)*pageRow+1);
		
		Connection conn = DButils.getConnection();
		PreparedStatement stat = conn.prepareStatement(sql);
		
		if (map.size() == 2) {
			Map<String, String> selVal = map.get(1);
		
			if (selVal.size() != 0) {
				Collection<String> values = selVal.values();
				Iterator<String> values_iter = values.iterator();
				int i = 0;
				while(values_iter.hasNext()) {
					String temp = values_iter.next();
					if (temp.matches("[0-9]*[0-9/.][0-9]*")) {
						if (temp.contains(".")) {
							stat.setFloat(++i, Float.valueOf(temp));
						} else {
							stat.setInt(++i, Integer.valueOf(temp));
						}
					} else {
						stat.setString(++i, temp);
					}
				}
			}
		}
		ResultSet rs = stat.executeQuery();

		while (rs.next()) {
			Map<String, String> mtmp = new HashMap<String, String>();
			for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
				mtmp.put(rs.getMetaData().getColumnName(j), String.valueOf(rs.getObject(j)));
			}
			result.add(mtmp);
		}
		DButils.close(conn, stat);
		
		return result;
	}

	/*
	 * 拼接插入语句的sql
	 */
	public static String insertSql(String table_name, Map<String, String> map, String key_name) {
		String sql = "INSERT INTO " + table_name;
		if (map.size() == 0) {
			return "";
		}
		sql += "(";

		Set<String> keys = map.keySet();
		Iterator<String> key_iter = keys.iterator();
		
		String key = key_name + "," + key_iter.next();
		String flag = "?,?";
		
		while(key_iter.hasNext()){
			key += "," + key_iter.next();
			flag += "," + "?";
		}	
		
		sql += key + ")" + " VALUES(" + flag + ")";
		
		return sql;
	}
	
	/*
	 * 插入记录
	 */
	public static boolean insertRecord(String table_name, Map<String, String> map, String key_name) throws SQLException, ClassNotFoundException {
		boolean result = false;

		Connection conn = DButils.getConnection();

		String sql = insertSql(table_name, map, key_name);
		if (sql == "") {
			return false;
		}
		
		int key = getAutoId(key_name);
		
		Collection<String> values = map.values();
		Iterator<String> values_iter = values.iterator();
		
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			int i = 1;
			stat.setInt(i, key);
			while(values_iter.hasNext()) {
				String temp = values_iter.next();
				/*空指針的問題*/
				if (temp != null && temp.matches("[0-9]*[0-9/.][0-9]*")) {
					if (temp.contains(".")) {
						stat.setFloat(++i, Float.valueOf(temp));
					} else {
						stat.setInt(++i, Integer.valueOf(temp));
					}
				} else {
					stat.setString(++i, temp);
				}
			}
			if (stat.executeUpdate() != 0) {
				result = true;
			} else {
				result =  false;
			}
			DButils.close(conn, stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 修改记录
	 */
	public static boolean updateRecord(String table_name, Map<String, String> map, String id) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		Connection conn = DButils.getConnection();

		String sql = "UPDATE " + table_name + " SET ";
		
		if (map.size() <= 1) {
			return false;
		}
		
		String id_value = map.get(id);
		map.remove(id);
		
		Set<String> keys = map.keySet();
		Iterator<String> key_iter = keys.iterator();
		
		Collection<String> values = map.values();
		Iterator<String> values_iter = values.iterator();
			
		String content = "";
		
		if (map.size() == 1) {
			content += key_iter.next() + "=" + "?";
		} else {
			content += key_iter.next() + "=" + "?";
			while(key_iter.hasNext()){
				content += "," + key_iter.next() + "=" + "?";
			}
		}

		sql += content + " WHERE " + id + "=" + id_value;
		
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			int i = 0;
			while(values_iter.hasNext()) {
				String temp = values_iter.next();
				if (temp.matches("[0-9]*[0-9/.][0-9]*")) {
					if (temp.contains(".")) {
						stat.setFloat(++i, Float.valueOf(temp));
					} else {
						stat.setInt(++i, Integer.valueOf(temp));
					}
				} else {
					stat.setString(++i, temp);
				}
			}
			if (stat.executeUpdate() != 0) {
				result = true;
			} else {
				result = false;
			}
			DButils.close(conn, stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 删除记录
	 */
	public static boolean deleteRecord(String table_name, Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		
		Connection conn = DButils.getConnection();
		String sql = "UPDATE " + table_name + " SET state=0 WHERE ";
		
		Set<String> keys = map.keySet();
		Iterator<String> key_iter = keys.iterator();
		
		Collection<String> values = map.values();
		Iterator<String> values_iter = values.iterator();
		String content = "";
		

		content += key_iter.next() + "=" + "?";
		while(key_iter.hasNext()){
			content += " AND " + key_iter.next() + "=" + "?";
		}
		
		sql += content;
//		System.out.println(sql);
		
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			int i = 0;
			while(values_iter.hasNext()) {
				String temp = values_iter.next();
				if (temp.matches("[0-9]*[0-9/.][0-9]*")) {
					if (temp.contains(".")) {
						stat.setFloat(++i, Float.valueOf(temp));
					} else {
						stat.setInt(++i, Integer.valueOf(temp));
					}
				} else {
					stat.setString(++i, temp);
				}
			}
			if (stat.executeUpdate() != 0) {
				result = true;
			} else {
				result = false;
			}
			DButils.close(conn, stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 添加日志
	 */
	public static boolean addLog(Map<String, String> map) throws SQLException, ClassNotFoundException {
		boolean result = false;
		if (insertRecord("b2c_log", map, "log_id")) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public static void close(Connection conn, PreparedStatement stat){
		try {
			stat.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("状态集关闭错误");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接关闭错误");
		}
	}
	
	public static void close(Connection conn, PreparedStatement stat, ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("资源集关闭错误");
		}
		try {
			stat.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("状态集关闭错误");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接关闭错误");
		}
	}
	
	public static Map<String, String> funcTest() {
		Map<String, String> map = new HashMap<String, String>();
//		map.put("admin_id", "2");
		map.put("user_name", "aaa");
//		map.put("password", "aaa");
//		map.put("user_type", "0");
//		map.put("user_phone", "12-255");
//		map.put("user_level", "1");
//		map.put("user_email", "ddd@local.com");
//		map.put("user_addr", "sfdaocal.com");
//		map.put("user_state", "1");
//		map.put("admin_id", "1");
//		map.put("user_id", "admin_id");
		return map;
	}
	
	public static List<Map<String, String>> funcTests() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(2);
		
		Map<String, String> map1 = new HashMap<String, String>();
		
//		map1.put("user_id", "user_id");
//		map1.put("admin_name", "user_name");
		
		Map<String, String> map2 = new HashMap<String, String>();
		
//		map2.put("user_id", "23");
//		map2.put("password", "aaa");
		map2.put("state", "1");
		list.add(map1);
		list.add(map2);
		return list;
	}
	
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		List<Map<String, String>> list = funcTests();
		List<String> table = new ArrayList<String>();
		table.add("b2c_user");
		System.out.println(selectRecordsByPage(table, list, 3, 3).size());
		System.out.println(selectRecordsByPage(table, list, 3, 3));
//		table.add("b2c_goods");
//////		System.out.println(map);
////		System.out.println(list.get(1));
////		System.out.println(selectsSql(table, list));
////		System.out.println(selectsRecords(table, list));
//		
////		String s = "aaa bbb ccc";
////		System.out.println(s.split(" ")[0]);
//		System.out.println(funcTest().values());
//		
//		System.out.println(fuzzySearchRecords("aaa bbb", table));
//		System.out.println(getConnection());
//		System.out.println(selectsSql(table, list, 0));
//		System.out.println(selectsSql(table, list, 1));
		
//		System.out.println(map.get("USER_NAME").getBytes("utf-8"));

//		Map<String, String> map = new HashMap<String, String>();
//		System.out.println(map.getClass());
//		map = selectRecords(table, list).get(0);
//		System.out.println(map.values());
//		System.out.println(map.get("USER_ID"));	
//		System.out.println(selectRecordsByPage(table, list, 2, 2));
//		System.out.println(selectsSql(table, list, 1));
//		System.out.println(getConnection());
		System.out.println((int)Math.ceil(0.8));
		System.out.println((int)Math.ceil(8/(10*1.0)));
	}
}
