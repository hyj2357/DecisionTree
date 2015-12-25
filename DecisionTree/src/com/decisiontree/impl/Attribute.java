package com.decisiontree.impl;

import com.decisiontree.Entroy;

public class Attribute  implements Entroy{
    private String name;
    private Character[] chs;
    private long count=0;
    private double entropy;
    
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
		return 0;
	}
	public void addCharacter(String chName){
		boolean h = false;
		for(int i=0;i<chs.length;i++)
			if(chs[i].getName().equals(chName)){
				chs[i].setCount(chs[i].getCount()+1);
				h = true;
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
		}		
	}
    
}
