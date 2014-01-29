package com.nbclassify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.nblearn.WareHouse;

public class NaiveBayesClassify {
	
	private WareHouse wareHouse;

	public ArrayList<String> classify(File input, File model){
		//convert the json file into WareHouse object
		getWareHouseInstance(input);
		//get the no. of categories that were made from the warehouse object and create a category-prob map which will be reused
		HashMap<String,Double> category_probMap = new HashMap<String,Double>();
		
		//create an arraList to store the classifications for each document(line)
		ArrayList<String> classification = new ArrayList<String>();
		
		//read the file line by line and for each line {
	    BufferedReader br = null;
	    try{
	    	br = new BufferedReader(new FileReader(input));
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
	    			double max = 0;
	    			for(String category : category_probMap.keySet())
	    				if(category_probMap.get(category) > max ){
	    					max = category_probMap.get(category);
	    					classification.set(documentNo, category); 					
	    				}
	    			documentNo++;		
	    		}
	    		
	    		
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    } finally{
	    	br.close();
	    	
	    }

	      
		return classification;
	
	}
	
	private double findCondProbOfCategoryGivenDoc(String[] words, String category){
		double condProbOfCategoryGivenDoc = 0;
		double condProbOfWordGivenCategory = 0;
		for(String word : words){
			condProbOfWordGivenCategory = get from warehouse;
			condProbOfCategoryGivenDoc += condProbOfWordGivenCategory;
		}
		//finally add the prior prob of category 
		condProbOfCategoryGivenDoc += get from warehouse
		return condProbOfCategoryGivenDoc;
		
	}
	
	private void getWareHouseInstance(File file){
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test\\spam.nb"));
			wareHouse = gson.fromJson(br, WareHouse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
