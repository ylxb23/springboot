package org.zero.boot.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.zero.boot.dao.second.entity.AppInfo;
import org.zero.boot.dao.second.entity.AppInfoExample;
import org.zero.boot.dao.second.repository.AppInfoMapper;
import org.zero.boot.domain.service.AppInfoService;

/**
 * app_info schema operate service.
 * @date 2017年11月3日 下午4:04:36
 * @author zero
 */
@Service(value="appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	@Autowired
	private AppInfoMapper appInfoMapper;
	
	@Override
	@Transactional(transactionManager="secondDataSourceTransactionManager", readOnly=true)
	public List<AppInfo> queryAllAppInfo() {
		AppInfoExample example = new AppInfoExample();
		example.createCriteria().andIsDeletedEqualTo((byte) 0);
		return appInfoMapper.selectByExample(example);
	}
	
	
	@Override
	@Transactional(transactionManager="secondDataSourceTransactionManager")
	public int insertList(List<AppInfo> list) {
		if(CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return appInfoMapper.insertMulti(list);
	}
}
