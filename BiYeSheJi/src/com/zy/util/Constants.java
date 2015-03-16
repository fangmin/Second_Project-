package com.zy.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

public class Constants {

	public static String DB_ERROR = "数据库操作错误";
	public static String INFO_EXIST = "信息已经存在";
	public static String DO_NOTHING = "未做任何数据处理";
	public static String SUCCESS = "成功";
	public static String TABLE_NO_EXIST = "数据表不存在";
	public static String CHILDERN_EXIST = "无法删除，请先删除关联信息";
	public static String RANDOMCODEKEY = "RANDKEY";

	public static String Success() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 200);
		map.put("message", "操作成功!");
		return JSONObject.fromObject(map).toString();
	}

	public static String Success(boolean close) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 200);
		map.put("message", "操作成功!");
		if(close){
			map.put("callbackType", "closeCurrent");
		}
		return JSONObject.fromObject(map).toString();
	}

	public static String Success(String navTabId, boolean close) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 200);
		map.put("message", "操作成功!");
		map.put("navTabId", navTabId);
		if(close){
			map.put("callbackType", "closeCurrent");
		}
		return JSONObject.fromObject(map).toString();
	}

	public static String Success(String navTabId, String rel, boolean close) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 200);
		map.put("message", "操作成功!");
		map.put("navTabId", navTabId);
		map.put("rel", rel);
		if(close){
			map.put("callbackType", "closeCurrent");
		}
		return JSONObject.fromObject(map).toString();
	}

	public static String Failure() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 300);
		map.put("message", "操作失败!");
		return JSONObject.fromObject(map).toString();		
	}

	public static String Failure(String str) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 300);
		map.put("message", "操作失败!<br />"+str);
		return JSONObject.fromObject(map).toString();		
	}

	public static String Timeout() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 301);
		map.put("message", "请求超时，请重新登陆!");
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 文件保存路径
	 * 
	 * @return
	 */
	public static String FILE_PATH() {
		PropertiesHelper help = new PropertiesHelper();
		String path = help.getByKey("config.properties", "path");
		if (!path.endsWith("/")) {
			path += "/";
		}

		return path;
	}
	
	/**
	 * 文件保存路径
	 * 
	 * @return
	 */
	public static String KETTLE_PATH() {
		PropertiesHelper help = new PropertiesHelper();
		String path = help.getByKey("config.properties", "kettle");
		if (!path.endsWith("/")) {
			path += "/";
		}

		return path;
	}

	/**
	 * 打印结果
	 * 
	 * @param rs
	 */
	public static void out(Object rs) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			out.print(rs);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
