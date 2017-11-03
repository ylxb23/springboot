package org.zero.boot.domain.service;

import java.util.List;

import org.zero.boot.dao.first.entity.Person;

/**
 * 
 * @date 2017年11月2日 下午7:09:04
 * @author zero
 */
public interface PersonService {

	/**
	 * for test.
	 * query all person items from person schema.
	 * @return
	 */
	List<Person> queryAll();

	/**
	 * for test.
	 * create person item for person schema.
	 * @param person
	 * @return
	 */
	long create(Person person);

}
