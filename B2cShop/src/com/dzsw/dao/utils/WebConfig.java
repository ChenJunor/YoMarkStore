package com.dzsw.dao.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class WebConfig {
	public static String path = Class.class.getClass().getResource("/").getPath();
	public static String filePath = path.substring(1).substring(0, path.substring(1, path.length()-1).lastIndexOf("/")+1) + System.getProperty("file.separator") + "b2c.xml";

	public static Map<String, String> GetDbConfig() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			String dbPort = doc.getElementsByTagName("DB_Port").item(0).getFirstChild().getNodeValue();
			String dbHost = doc.getElementsByTagName("DB_Host").item(0).getFirstChild().getNodeValue();
			String dbSid = doc.getElementsByTagName("DB_Sid").item(0).getFirstChild().getNodeValue();
			String dbWay = doc.getElementsByTagName("DB_Way").item(0).getFirstChild().getNodeValue();
			
			
			map.put("DB_Url", dbWay + ":@" + dbHost + ":" + dbPort + ":" + dbSid);
			map.put("DB_Driver", doc.getElementsByTagName("DB_Driver").item(0).getFirstChild().getNodeValue());
			map.put("DB_User", doc.getElementsByTagName("DB_User").item(0).getFirstChild().getNodeValue());
			map.put("DB_Password", doc.getElementsByTagName("DB_Password").item(0).getFirstChild().getNodeValue());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public static Map<String, String> GetWebConfig() {
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			String pageCount = doc.getElementsByTagName("Page_Count").item(0).getFirstChild().getNodeValue();
			map.put("Page_Row", "10");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
//		System.out.println(GetWebConfig());
//		System.out.println(filePath);
		String path = Class.class.getClass().getResource("/").getPath();
		System.out.println(path.indexOf("/", 1));
		System.out.println(path.lastIndexOf("/", 2));
		System.out.println(path.substring(1).substring(0, path.substring(1, path.length()-1).lastIndexOf("/")+1));
	}
}
