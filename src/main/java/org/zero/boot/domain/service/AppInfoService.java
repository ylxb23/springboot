package org.zero.boot.domain.service;

import java.util.List;

import org.zero.boot.dao.second.entity.AppInfo;

/**
 * 第二数据源数据测试
 * @date 2017年11月3日 下午4:03:05
 * @author zero
 */
public interface AppInfoService {

	/**
	 * query all app_info items in schema.
	 * @return
	 */
	List<AppInfo> queryAllAppInfo();

	/**
	 * insert multi
	 * @param list
	 * @return
	 */
	int insertList(List<AppInfo> list);
	
}
