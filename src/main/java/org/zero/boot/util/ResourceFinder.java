package org.zero.boot.util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 类路径下文件查找工具
 * 
 * @date 2018年6月14日 下午11:03:48
 * @author zero
 */
public abstract class ResourceFinder {
	
	/**
	 * 获取加载jar中指定相对项目路径的资源所在全部位置
	 * @param path
	 * @return
	 */
	public static Set<URL> findSourceByRelativeClassPath(String path) {
		if(path == null || path.isEmpty()) {
			return new HashSet<>();
		}
		Enumeration<URL> urls = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			if(classLoader == null) {
				urls = ClassLoader.getSystemResources(path);
			} else {
				urls = classLoader.getResources(path);
			}
		} catch (IOException e) {
			return new HashSet<>();
		}
		if(urls == null) {
			return new HashSet<>();
		}
		Set<URL> set = new HashSet<>();
		while(urls.hasMoreElements()) {
			URL url = urls.nextElement();
			set.add(url);
		}
		return set;
	}
	
	
}
