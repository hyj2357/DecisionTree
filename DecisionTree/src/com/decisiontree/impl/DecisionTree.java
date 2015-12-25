package com.decisiontree.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import com.decisiontree.Entroy;

public class DecisionTree implements Entroy{
    private Attribute[] attrs;
    private double entroy;
    private static String PREFIX_FILE = System.getProperty("user.dir")+File.separator+"data"+File.separator+"train"+File.separator;
	@Override
	public double entroy() {
		return 0;
	}
	
    public void trainTree(String classFile,String fileName) throws FileNotFoundException{
    	File tn = new File(PREFIX_FILE + classFile);
    	File df = new File(PREFIX_FILE + fileName);
    	Scanner sc_tn = new Scanner(tn);
    	Scanner sc_df = new Scanner(df); 
    	readAttrs(sc_tn);
    	while()
    }
    
    private void readAttrs(Scanner sc){
        String content = "";
    	while(sc.hasNext())
    		content += sc.nextLine();
    	String[] classes = content.split(",");
    	attrs = new Attribute[classes.length];
    	for(int i=0;i<attrs.length;i++){
    		attrs[i] = new Attribute();
    		attrs[i].setName(classes[i]);
    	}
    	System.out.println("Read classes finished. "+(new Date()));
    }
    
    
    private void writeOutTree(){
    	
    }
}
