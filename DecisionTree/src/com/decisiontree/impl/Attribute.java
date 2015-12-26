package com.decisiontree.impl;

import java.util.ArrayList;
import java.util.List;

import com.decisiontree.Entroy;
import com.decisiontree.ResultType;

public class Attribute  implements Entroy,ResultType,Comparable{
    private String name;
    private Character[] chs;
    private long count=0;
    private double entropy;
    private List<Character> result = new ArrayList<Character>();

    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Character[] getChs() {
		return chs;
	}
	public void setChs(Character[] chs) {
		this.chs = chs;
	}
	public double getEntropy() {
		return entropy;
	}
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}
	@Override
	public double entroy() {
		double _r = 0;
		for(Character e:chs)
			_r += ((((double)e.getCount())/((double)count))*e.entroy());
		return _r;
	}
	
	public int addCharacter(String chName){
		boolean h = false;
		int index = -1;
		if(chs==null){
			chs = new Character[1];
			chs[0] = new Character();
			chs[0].setName(chName);
			count++;
			return 0;
		}
		for(int i=0;i<chs.length;i++)
			if(chs[i].getName().equals(chName)){
				chs[i].setCount(chs[i].getCount()+1);
				h = true;
				index = i;
				break;
			}
		if(!h){
			Character chs_new[] = new Character[chs.length+1];
			for(int i=0;i<chs.length;i++)
				chs_new[i] = chs[i];
			Character ch = new Character();
			ch.setName(chName);
			chs_new[chs_new.length-1] = ch;
			this.chs = chs_new;
			index = this.chs.length-1;
		}
		count++;
		return index;
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
	@Override
	public int compareTo(Object o) {
		double _entroy = ((Attribute)o).entroy();
		double entroy  = this.entroy();
        if(_entroy==entroy)
        	return 0;
        else if(_entroy<entropy)
        	return 1;
        else
        	return -1;
	}  
	
	public void setChildEntroy(){
		for(Character c:chs)
			c.setEntroy(c.entroy());
	}
	
	public Character getCharacterByName(String name){
		for(int i=0;i<chs.length;i++)
			if(chs[i].getName().equals(name))
				return chs[i];
		return null;
	}
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public void setResult(List<Character> result) {
		this.result = result;
	}
}
