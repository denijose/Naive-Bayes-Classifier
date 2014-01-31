package com.nblearn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class WareHouse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   public HashMap<String, Double> category_NoOfWordsMap;
	   public HashMap<String, Double> category_FrequencyMap;
	   public HashMap<String, HashMap<String, ArrayList<Double>> > category_CategoryMap;
	   public Set<String> vocabulary;
	
	
	
	public WareHouse(){
		category_NoOfWordsMap = new HashMap<String, Double>();
		category_CategoryMap = new HashMap<String, HashMap<String, ArrayList<Double>> >();
		category_FrequencyMap = new HashMap<String, Double>();
		vocabulary = new HashSet<String>();
	}

	
	public  void createCategoryMap(String category){
		if(!category_CategoryMap.containsKey(category)){
			HashMap<String, ArrayList<Double>> map =  new HashMap<String, ArrayList<Double>>();
			category_CategoryMap.put(category, map);
		}	
	}


	

}
