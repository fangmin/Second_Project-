package com.zy.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

//文件操作
public class FileHelper {

	private static Logger log = Logger.getLogger(FileHelper.class.getName());

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 *            String 如 c://1.txt 绝对路径
	 * @return boolean
	 */
	public static String readFile(String path) {
		StringBuilder sb = null;
		InputStreamReader read = null;
		BufferedReader buff = null;
		try {
			File f = new File(path);
			if (f.isFile() && f.exists()) {
				read = new InputStreamReader(new FileInputStream(f), "UTF-8");
				buff = new BufferedReader(read);
				String line = null;
				sb = new StringBuilder();
				while ((line = buff.readLine()) != null) {
					sb.append(line);
				}
			}
		} catch (Exception e) {
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
				}
				if (read != null) {
					read.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 写文件
	 * 
	 * @param path
	 *            文件路径及文件名
	 * @param fileContent
	 *            文件内容
	 */
	public static void writeFile(String path, String fileName,
			String fileContent) {
		OutputStreamWriter write = null;
		BufferedWriter buff = null;
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			f = new File(path + fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			buff = new BufferedWriter(write);
			buff.write(fileContent);
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
				}
				if (write != null) {
					write.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写文件
	 * 
	 * @param file
	 * @param fileName
	 *            全路径
	 */
	public static void writeFile(File file, String path, String fileName) {

		log.info(fileName);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}

			fos = new FileOutputStream(path + fileName);
			// 建立文件上传流
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			System.out.println("文件上传失败");
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				System.out.println("流关闭失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}
}
