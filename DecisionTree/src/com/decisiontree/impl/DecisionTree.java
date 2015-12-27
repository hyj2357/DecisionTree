package com.decisiontree.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.decisiontree.Entroy;
import com.decisiontree.ResultType;
import com.decisiontree.Tools;

public class DecisionTree implements Entroy,ResultType{
    private Attribute[] attrs;
    private double entroy;
    private long count;
    private List<Character> result = new ArrayList<Character>();
    private static String PREFIX_FILE = System.getProperty("user.dir")+File.separator+"data"+File.separator+"train"+File.separator;
    private static String PREFIX_TESTFILE = System.getProperty("user.dir")+File.separator+"data"+File.separator+"test"+File.separator;    
    private long _cout = 0;
    private Attribute root;
    
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
	
    public void trainTree(String classFile,String fileName) throws FileNotFoundException{
    	readAndProcessTrainData( classFile, fileName);
    	this.createDecisionTree();
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
    	System.out.println("Read classes finished... "+(new Date()));
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
    	this.entroy = entroy();
        for(int i=1;i<this.attrs.length;i++){
        	attrs[i].setEntropy(attrs[i].entroy());
            attrs[i].setChildEntroy();
    	}
    }
    
    private void createDecisionTree(){
        List<Attribute> attrs_lst = new ArrayList<Attribute>();
        for(int i=1;i<this.attrs.length;i++)
        	attrs_lst.add(this.attrs[i]);
        Collections.sort(attrs_lst);
        root = attrs_lst.get(0);
	    List lstn = new ArrayList<Attribute>();
	    lstn.addAll(attrs_lst.subList(1, attrs_lst.size()));
	    List c = new ArrayList<Attribute>();
	    c.add(root);
	    createInterf(c,lstn);
	    System.out.println("create tree finished... "+(new Date()));
    }
    
    /**
     * 递归创建决策树
     * @param c
     * @param lst
     */
    private void createInterf(List<Attribute> c,List<Attribute> lst){
    	List<Attribute> _lst = new ArrayList<Attribute>();
    	for(Attribute k:c){
    		for(Character cd:k.getChs()){
    			if(cd.result_1()!=null)
    				cd.setLeaf();
    			else{
    				if(lst.size()==0)
    					cd.setLeaf();
    				else{
    				    cd.setNextAttribute(lst.get(0));
    				    _lst.add(lst.get(0));
    				    lst.remove(0);
    				}
    			}
    		}
    	}
    	if(_lst.size()==0)
    		return;
    	Collections.sort(_lst);
    	createInterf(_lst,lst);
    }
    /**
    private void createInterf(Character c,List<Attribute> lst){
    	Character r = null;
    	if((r=c.result_1())!=null){
    		c.setLeaf();
    	}
    		else{
    			Attribute _c = lst.get(0);
    		    c.setNextAttribute(_c);
    		    if(lst.size()==1){
        		    for(Character ec:_c.getChs())
        		    	ec.setLeaf();
        		    System.out.println(_cout++);
        		    return;
    		    }
    		    List lstn = new ArrayList<Attribute>();
    		    lstn.addAll(lst.subList(1, lst.size()));
    		    for(Character ec:_c.getChs())
    		    	createInterf(ec,lstn);
    		}   			
   		
    }**/
    
    /**
     * 将决策树写入到文件当中
     */
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
	
	/**
	 * 使用训练好的决策树进行测试集的预测
	 * @param testFile 测试集文件名,默认放在  工作目录/data/test 下.
	 * @throws FileNotFoundException
	 */
	public void judge(String testFile) throws FileNotFoundException{
		double right=0,this_count=0;
		File fl = new File(PREFIX_TESTFILE+testFile);
		Scanner sc = new Scanner(fl);
		while(sc.hasNext()){
			String e[] = sc.nextLine().split(",");				
			Map<String,String> ed = new HashMap<String,String>();
			for(int i=1;i<e.length;i++)
				ed.put(attrs[i].getName(), e[i]);
			Attribute itr = root;
			Character c = itr.getCharacterByName(ed.get(itr.getName()));
			while(!c.isLeaf()){
				itr = c.getNextAttribute();
				c = itr.getCharacterByName(ed.get(itr.getName()));
			}
			if(c.result_max().getName().equals(e[0]))
				right++;
			this_count++;
			System.out.println("correct percent: "+ ((right/this_count)*100) + "%");
		}
	}
}
