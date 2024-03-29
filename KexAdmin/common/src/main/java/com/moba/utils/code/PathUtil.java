package com.moba.utils.code;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 说明：路径工具类
 * 
 * @version
 */
public class PathUtil {

	/**
	 * 获取classpath1
	 * 
	 * @return
	 */
	public static String getClasspath() {
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../")
				.replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}

	/**
	 * 获取classpath2
	 * 
	 * @return
	 */
	public static String getClassResources() {
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
				.replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}

	public static String PathAddress() {
		String strResult = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		StringBuffer strBuf = new StringBuffer();
		strBuf.append(request.getScheme() + "://");
		strBuf.append(request.getServerName() + ":");
		strBuf.append(request.getServerPort() + "");
		strBuf.append(request.getContextPath() + "/");
		strResult = strBuf.toString();// +"ss/";//加入项目的名称
		return strResult;
	}

}
