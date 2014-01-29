package com.nblearn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class WareHouse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   HashMap<String, Integer> category_NoOfWordsMap;
	   HashMap<String, Integer> category_FrequencyMap;
	   HashMap<String, HashMap<String, ArrayList<Double>> > category_CategoryMap;
	   Set<String> vocabulary;
	
	
	
	public WareHouse(){
		category_NoOfWordsMap = new HashMap<String, Integer>();
		category_CategoryMap = new HashMap<String, HashMap<String, ArrayList<Double>> >();
		category_FrequencyMap = new HashMap<String, Integer>();
		//vocabulary = new Set<String>();
	}

	
	public  void createCategoryMap(String category){
		if(!category_CategoryMap.containsKey(category)){
			HashMap<String, ArrayList<Double>> map =  new HashMap<String, ArrayList<Double>>();
			category_CategoryMap.put(category, map);
		}	
	}


	

}
