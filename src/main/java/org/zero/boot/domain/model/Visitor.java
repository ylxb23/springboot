package org.zero.boot.domain.model;

/**
 * for test
 *
 * @author zero
 */
public class Visitor {
	private long counter;
    private String name;

    public Visitor(String name) {
        this.name = name;
    }
    
    public Visitor(long count, String name) {
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
