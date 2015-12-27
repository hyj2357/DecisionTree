package com.decisiontree;

import java.io.FileNotFoundException;

import com.decisiontree.impl.DecisionTree;

public class Main {
   public static void main(String args[]) throws FileNotFoundException{
	   DecisionTree dt = new DecisionTree();
	   long t1 = System.currentTimeMillis();
	   dt.trainTree("agaricus-lepiota-names.txt", "agaricus-lepiota-train.data");
	   dt.judge("agaricus-lepiota-test.data");
	   System.out.println("Running time:"+(System.currentTimeMillis()-t1)+"ms");
   }
}
