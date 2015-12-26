package com.decisiontree.impl;

import java.util.ArrayList;
import java.util.List;

import com.decisiontree.Entroy;
import com.decisiontree.ResultType;
import com.decisiontree.Tools;

public class Character  implements Entroy,ResultType{
    private String name;
    private long count=1;
    private Attribute nextAttribute;
    private boolean isLeaf=false;
    private double entroy;
    
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
		double _r = 0;
		for(Character e:result){
			double _p = 0;			
			_p = (((double)e.getCount())/((double)count));
			_r += ( (-1)*_p*Tools.log2(_p));
		}
		return _r;
	}
	
	/**
	 * �ҵ�����Ϊ1�Ľ��<br/>
	 * ���û��,����null.
	 * @return
	 */
	public Character result_1(){
		Character e = null;
		for(Character d:result)
			if((((double)d.getCount())/((double)count))==1)
				e = d;
		return e;
	}
    
	/**
	 * �ҵ��������Ľ��
	 * @return
	 */
	public Character result_max(){
		Character e = null;
		double p = 0;
		for(Character d:result){
			double _p = (((double)d.getCount())/((double)count));
			e = _p>p?d:e;
		}
		return e;
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
	 * �������Ϊ0�����Ѿ����������߶�ʱ.���ý��Ҷ�ӽڵ�.<br/>
	 * ���������ֵ��,���Ľ������,��Ϊ�ý��Ҷ�ӽڵ�.
	 */
	public void setLeaf(){
		this.isLeaf = true;
	}
	
	public void setNextAttribute(Attribute attribute){
		this.nextAttribute = attribute;
	}
	public double getEntroy() {
		return entroy;
	}
	public void setEntroy(double entroy) {
		this.entroy = entroy;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public Attribute getNextAttribute() {
		return nextAttribute;
	}
}
