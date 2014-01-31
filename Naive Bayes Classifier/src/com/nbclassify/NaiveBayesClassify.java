package com.nbclassify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.nblearn.WareHouse;

public class NaiveBayesClassify {
	
	private WareHouse wareHouse;
	private HashMap<String,Double> category_priorProbMap;
	
	public NaiveBayesClassify(){
		category_priorProbMap = new HashMap<String, Double>();
	}

	public ArrayList<String> classify(String fileName, String modelName){
		//convert the json file into WareHouse object
		getWareHouseInstance(modelName);
		System.out.println("vocab size = " + wareHouse.vocabulary.size());
		double temp = 0;
		
		//get the no. of categories that were made from the warehouse object and create a category-prob map which will be reused
		HashMap<String,Double> category_probMap = new HashMap<String,Double>();
		for(String category : wareHouse.category_FrequencyMap.keySet()){
			category_probMap.put(category, (double)0);
			temp += wareHouse.category_FrequencyMap.get(category);
		}	
		
		//get the prior prob into a map
		double priorProb = 0;
		for(String category : wareHouse.category_FrequencyMap.keySet()){
		    priorProb = wareHouse.category_FrequencyMap.get(category)/temp;
		    priorProb = Math.log(priorProb);
			category_priorProbMap.put(category, priorProb);
		}	
		
		//create an arraList to store the classifications for each document(line)
		ArrayList<String> classification = new ArrayList<String>();
		
		//read the file line by line and for each line {
	    BufferedReader br = null;
	    try{
	    	br = new BufferedReader(new FileReader(fileName));
	    	String line;
	    	int documentNo = 0;
	    	while((line = br.readLine()) != null){
	    		// a new document starts
	    		String[] words = line.split(" ");
	    		// for each cateogory
	    			for(String category : category_probMap.keySet()){
	    				//find p(c|line) and store it in the map
	    				double condProbOfCategoryGivenDoc = findCondProbOfCategoryGivenDoc(words,category);
	    				category_probMap.put(category, condProbOfCategoryGivenDoc);
	    			}
	    			
	    			// get the max prob and put the category into the arrayList
	    			double max = Double.NEGATIVE_INFINITY;
	    			classification.add(documentNo, "");
	    			for(String category : category_probMap.keySet())
	    				if(category_probMap.get(category) > max ){
	    					max = category_probMap.get(category);
	    					classification.set(documentNo, category);
	    				}
	    			documentNo++;		
	    		}
	    }catch(Exception e){
	    	try {
				e.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    } finally{
	    	try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    return classification;
	
	}
	
	private double findCondProbOfCategoryGivenDoc(String[] words, String category){
		double condProbOfCategoryGivenDoc = 0;
		double condProbOfWordGivenCategory = 0;
		ArrayList<Double> countAndProb = new ArrayList<Double>();
		for(String word : words){
			//handling new words -
			if((countAndProb=wareHouse.category_CategoryMap.get(category).get(word)) == null ){
				condProbOfWordGivenCategory = Math.log(1/(wareHouse.vocabulary.size()+ wareHouse.category_NoOfWordsMap.get(category)));
				condProbOfCategoryGivenDoc += condProbOfWordGivenCategory;
				continue;
			}	
			condProbOfWordGivenCategory = countAndProb.get(1);
			condProbOfCategoryGivenDoc += condProbOfWordGivenCategory;
		}
		//finally add the prior prob of category 
		condProbOfCategoryGivenDoc += category_priorProbMap.get(category);
		return condProbOfCategoryGivenDoc;
		
	}
	
	private void getWareHouseInstance(String fileName){
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			wareHouse = gson.fromJson(br, WareHouse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
