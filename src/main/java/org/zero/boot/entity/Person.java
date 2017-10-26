package org.zero.boot.entity;

/**
 * for test
 *
 * @author zero
 */
public class Person {
	private long counter;
    private String name;

    public Person(String name) {
        this.name = name;
    }
    
    public Person(long count, String name) {
    	this.counter = count;
    	this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public long getCounter() {
    	return counter;
    }
}
