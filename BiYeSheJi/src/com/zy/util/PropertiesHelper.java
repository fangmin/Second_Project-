package com.zy.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesHelper {

	private static Logger log = Logger.getLogger(PropertiesHelper.class
			.getName());

	/**
	 * 读取配置信息
	 */
	public Properties readProperties(String fileName) {
		log.info("读取配置信息");
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(this.getClass().getClassLoader()
					.getResource(fileName).getPath());
			props.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return props;
	}
	
	/**
	 * 读取键值信息
	 * @param fileName
	 * @param key
	 * @return
	 */
	public String getByKey(String fileName,Object key){
		Properties props=this.readProperties(fileName);
		return this.convert2CN(props.get(key));
	}
	
	/**
	 * 中文转码
	 * @param o
	 * @return
	 */
	private String convert2CN(Object o) {
		try {
			return new String(o.toString().getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
