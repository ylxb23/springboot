package org.zero.test.dal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zero.boot.dao.third.entity.BbsReply;
import org.zero.boot.dao.third.repostory.BbsReplyMapper;
import org.zero.test.TestBasic;

/**
 * 
 * @date 2018年6月8日 下午10:56:22
 * @author zero
 */
public class BbsReplyMapperTest extends TestBasic {
	
	@Autowired
	private BbsReplyMapper bbsReplyMapper;
	
	@Test
	public void testInsert() {
		BbsReply bbsReply = new BbsReply();
		bbsReply.setFloor(1);
		bbsReply.setTid(100000L);
		bbsReply.setUserId(595748832);
		bbsReply.setContent("春花秋月何时了，往事知多少。小楼昨夜又东风，故国不堪回首月明中。雕栏玉砌应犹在，只是朱颜改。问君能有几多愁，恰是一江春水向东流。");
		int result = bbsReplyMapper.insertSelective(bbsReply);
		Assert.assertTrue(result == 1);
	}
	
}
