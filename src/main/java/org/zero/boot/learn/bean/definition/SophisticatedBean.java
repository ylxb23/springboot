package org.zero.boot.learn.bean.definition;

import java.util.List;

/**
 * 
 * @date 2019年3月27日 上午12:10:45
 * @author zero
 */
public class SophisticatedBean {
	private String name;
	private List<String> alias;
	private Object service;
	
	public SophisticatedBean(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAlias() {
		return alias;
	}
	public void setAlias(List<String> alias) {
		this.alias = alias;
	}
	public Object getService() {
		return service;
	}
	public void setService(Object service) {
		this.service = service;
	}
}
