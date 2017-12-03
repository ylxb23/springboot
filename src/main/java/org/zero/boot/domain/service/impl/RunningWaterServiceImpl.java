package org.zero.boot.domain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.boot.common.RunningWaterTypeEnum;
import org.zero.boot.dao.first.entity.AccountRunning;
import org.zero.boot.dao.first.entity.AccountRunningExample;
import org.zero.boot.dao.first.repository.AccountRunningMapper;
import org.zero.boot.domain.model.RunningWaterEntry;
import org.zero.boot.domain.request.RunningWaterPostRequest;
import org.zero.boot.domain.service.RunningWaterService;

/**
 * 
 * @date 2017年11月20日 下午9:42:46
 * @author zero
 */
@Service
public class RunningWaterServiceImpl implements RunningWaterService {
	private final Logger logger = LoggerFactory.getLogger(RunningWaterServiceImpl.class);
	
	@Autowired
	private AccountRunningMapper mapper;
	
	@Override
	public List<RunningWaterEntry> reviewAll() {
		AccountRunningExample e = new AccountRunningExample();
		e.createCriteria().andDeletedEqualTo((byte) 0);
		e.setOrderByClause(" datetime desc ");
		List<AccountRunning> src = mapper.selectByExample(e);
		List<RunningWaterEntry> res = new ArrayList<>(src.size());
		for(AccountRunning item : src) {
			RunningWaterEntry target = new RunningWaterEntry();
			BeanUtils.copyProperties(item, target);
			target.setTypeName(RunningWaterTypeEnum.parse(target.getType()).getName());
			res.add(target);
		}
		logger.info("review {}`s running water, size: {}", res.isEmpty()?"NO_ONE":res.get(0).getOwner(), res.size());
		return res;
	}

	@Transactional(transactionManager="firstDataSourceTransactionManager")
	@Override
	public Boolean putOutcome(RunningWaterPostRequest outcome) {
		AccountRunning top = queryTopOne();
		AccountRunning record = new AccountRunning();
		BeanUtils.copyProperties(outcome, record);
		record.setDatetime(new Date());
		record.setType(RunningWaterTypeEnum.OUTCOME.getType());
		if(top == null || top.getCurrentAmount().compareTo(outcome.getAmount()) < 1) {
			logger.warn("出账: [{}], 账户余额不够啊！", outcome.toString());
			return Boolean.FALSE;
		}
		record.setCurrentAmount(top.getCurrentAmount().subtract(outcome.getAmount()));
		return mapper.insertSelective(record) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Transactional(transactionManager="firstDataSourceTransactionManager")
	@Override
	public Boolean putIncome(RunningWaterPostRequest income) {
		AccountRunning top = queryTopOne();
		AccountRunning record = new AccountRunning();
		BeanUtils.copyProperties(income, record);
		record.setDatetime(new Date());
		record.setType(RunningWaterTypeEnum.INCOME.getType());
		if(top == null) {
			record.setCurrentAmount(income.getAmount());
		} else {
			record.setCurrentAmount(top.getCurrentAmount().add(income.getAmount()));
		}
		return mapper.insertSelective(record) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	private AccountRunning queryTopOne() {
		AccountRunningExample example = new AccountRunningExample();
		example.createCriteria().andDeletedEqualTo((byte) 0);
		example.setOrderByClause(" id desc limit 1 ");
		List<AccountRunning> res = mapper.selectByExample(example);
		return res.isEmpty() ? null : res.get(0);
	}
}
