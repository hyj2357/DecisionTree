package com.decisiontree.impl;

import java.util.ArrayList;
import java.util.List;

import com.decisiontree.Entroy;
import com.decisiontree.ResultType;

public class Character  implements Entroy,ResultType{
    private String name;
    private long count=1;
    private Attribute nextAttribute;
    private boolean isLeaf;
    private List<Character> result = new ArrayList<Character>();

    
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
		Math.
		return 0;
	}
    
    public boolean equals(Object o){
    	return this.name.equals(((Character)o).getName());
    }
	@Override
	public List<Character> getResult() {
		return this.result;
	}

	@Override
	public void setResult(Character result) {
        if(this.result.contains(result)){
        	int index = this.result.indexOf(result);
        	Character R = (Character)this.result.get(index);
        	R.setCount(R.getCount()+1);
        }else{
        	this.result.add(result.copy());
        }
	}
	
	public Character copy(){
		Character cp = new Character();
		cp.setName(name);
		return cp;
	}
	
	/**
	 * 当最后熵为0或者已经到达树最大高度时.设置结果叶子节点.<br/>
	 * 计算该属性值下,最大的结果概率,即为该结果叶子节点.
	 */
	public void setLeaf(){
		
	}
	
	public void setNextAttribute(Attribute attribute){
		this.nextAttribute = attribute;
	}
}
