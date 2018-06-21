package org.zero.test.dal.second;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zero.boot.dao.second.entity.AppInfo;
import org.zero.boot.domain.service.AppInfoService;
import org.zero.test.TestBasic;


/**
 * 
 * @date 2018年6月20日 下午11:46:31
 * @author zero
 */
@Transactional(transactionManager="secondDataSourceTransactionManager")
public class AppInfoServiceTest extends TestBasic {
	@Autowired
	private AppInfoService appInfoService;
	
	@Test
	@Rollback(true)
	public void testInsertMulti() {
		List<AppInfo> list = new ArrayList<>();
		AppInfo one = new AppInfo();
		one.setAppName("支付宝");
		one.setAppVersion("10.1.25");
		one.setDescription("蚂蚁金服");
		list.add(one);
		AppInfo two = new AppInfo();
		two.setAppName("微信");
		two.setAppVersion("6.6.7");
		two.setDescription("腾讯公司 版权所有");
		list.add(two);
		int result = appInfoService.insertList(list);
		Assert.assertTrue(result == list.size());
	}
}
