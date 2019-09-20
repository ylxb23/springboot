package org.zero.boot.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.boot.dao.third.entity.BbsReply;
import org.zero.boot.dao.third.entity.BbsReplyExample;
import org.zero.boot.dao.third.repostory.BbsReplyMapper;
import org.zero.boot.domain.service.ThirdDsService;

/**
 * 
 * @date 2019年5月19日 下午2:17:20
 * @author zero
 */
@Service
public class ThirdDsServiceImpl implements ThirdDsService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BbsReplyMapper bbsReplyMapper;
	
	@Override
	public List<BbsReply> getReplies(Long tid) {
		if(tid == null) {
			return new ArrayList<>();
		}
 		BbsReplyExample example = new BbsReplyExample();
		example.createCriteria()
		.andTidEqualTo(tid)
		.andStatusEqualTo((byte) 0);
		List<BbsReply> replies = bbsReplyMapper.selectByExampleWithBLOBs(example);
		logger.debug("Get replies for tid:{}, result.size:{}", tid, replies.size());
		return replies;
	}
}
