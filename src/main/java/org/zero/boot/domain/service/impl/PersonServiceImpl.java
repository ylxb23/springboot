package org.zero.boot.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.boot.dao.first.entity.Person;
import org.zero.boot.dao.first.entity.PersonExample;
import org.zero.boot.dao.first.repository.PersonMapper;
import org.zero.boot.domain.service.PersonService;

/**
 * person schema operate service.
 * @date 2017年11月2日 下午7:09:44
 * @author zero
 */
@Service
public class PersonServiceImpl implements PersonService {
	
	private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public List<Person> queryAll() {
		PersonExample example = new PersonExample();
		example.createCriteria().andIdGreaterThan(0L);
		return personMapper.selectByExample(example);
	}
	
	@Override
	public long create(Person person) {
		long id = personMapper.insertSelective(person);
		logger.info("create person item: {}, result: {}", person.toString(), id>0 ? "success":"failure");
		return id;
	}
}
