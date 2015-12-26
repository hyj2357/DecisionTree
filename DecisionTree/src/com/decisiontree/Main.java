package com.decisiontree;

import java.io.FileNotFoundException;

import com.decisiontree.impl.DecisionTree;

public class Main {
   public static void main(String args[]) throws FileNotFoundException{
	   DecisionTree dt = new DecisionTree();
	   dt.trainTree("agaricus-lepiota-names.txt", "agaricus-lepiota-train.data");
	   dt.judge("agaricus-lepiota-test.data");
   }
}
