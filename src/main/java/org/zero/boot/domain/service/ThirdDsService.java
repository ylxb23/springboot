package org.zero.boot.domain.service;

import java.util.List;

import org.zero.boot.dao.third.entity.BbsReply;

/**
 * 
 * @date 2019年5月19日 下午2:14:01
 * @author zero
 */
public interface ThirdDsService {
	
	List<BbsReply> getReplies(Long tid);
	
}
