package com.decisiontree.impl;

import com.decisiontree.Entroy;

public class Character  implements Entroy{
    private String name;
    private long count=0;
    private String nextAttribute;
    private boolean isLeaf;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public double entroy() {
		return 0;
	}
    
    
}
