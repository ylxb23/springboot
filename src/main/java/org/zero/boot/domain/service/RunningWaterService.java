package org.zero.boot.domain.service;

import java.util.List;

import org.zero.boot.domain.model.RunningWaterEntry;
import org.zero.boot.domain.request.RunningWaterPostRequest;

/**
 * 
 * @date 2017年11月20日 下午9:41:14
 * @author zero
 */
public interface RunningWaterService {

	List<RunningWaterEntry> reviewAll();

	Boolean putOutcome(RunningWaterPostRequest outcome);

	Boolean putIncome(RunningWaterPostRequest income);
	
	
}
