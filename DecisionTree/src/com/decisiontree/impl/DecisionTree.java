package com.decisiontree.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.decisiontree.Entroy;
import com.decisiontree.ResultType;

public class DecisionTree implements Entroy,ResultType{
    private Attribute[] attrs;
    private double entroy;
    private long count;
    private List<Character> result = new ArrayList<Character>();
    private static String PREFIX_FILE = System.getProperty("user.dir")+File.separator+"data"+File.separator+"train"+File.separator;
	@Override
	public double entroy() {
		return 0;
	}
	
    public void trainTree(String classFile,String fileName) throws FileNotFoundException{
    	readAndProcessTrainData( classFile, fileName);
    }
    
    private void readAttrs(Scanner sc){
        String content = "";
    	while(sc.hasNext())
    		content += sc.nextLine();
    	String[] classes = content.split(",");
    	attrs = new Attribute[classes.length];
    	for(int i=1;i<attrs.length;i++){
    		attrs[i] = new Attribute();
    		attrs[i].setName(classes[i]);
    	}
    	System.out.println("Read classes finished. "+(new Date()));
    }
    
    private void readAndProcessTrainData(String classFile,String fileName) throws FileNotFoundException{
    	File tn = new File(PREFIX_FILE + classFile);
    	File df = new File(PREFIX_FILE + fileName);
    	Scanner sc_tn = new Scanner(tn);
    	Scanner sc_df = new Scanner(df); 
    	readAttrs(sc_tn);
    	while(sc_df.hasNextLine()){
    		String values[] = sc_df.nextLine().split(",");
    		Character r = new Character();
    		r.setName(values[0]);
    		for(int i=1;i<values.length;i++){    			
    			int index = attrs[i].addCharacter(values[i]);
    			attrs[i].setResult(r);
    			attrs[i].getChs()[index].setResult(r);    			
    		}
			this.setResult(r);
    		count++;
    	}
        System.out.println("Read train data finished... "+(new Date()));
    }
    
    private void createDecisionTree(){
        	
    }
    
    private void writeOutTree(){
    	
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
}
