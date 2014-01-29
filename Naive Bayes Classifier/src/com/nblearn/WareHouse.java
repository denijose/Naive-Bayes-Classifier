package com.nblearn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class WareHouse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   HashMap<String, Integer> category_NoOfWordsMap;
	   @XmlJavaTypeAdapter(MapAdapter.class)
	   HashMap<String, HashMap<String, ArrayList<Double>> > category_CategoryMap;
	   Set<String> vocabulary;
	
	
	public HashMap<String, Integer> getCategory_NoOfWordsMap() {
		return category_NoOfWordsMap;
	}

	@XmlElement
	public void setCategory_NoOfWordsMap(HashMap<String, Integer> category_NoOfWordsMap) {
		this.category_NoOfWordsMap = category_NoOfWordsMap;
	}


	public HashMap<String, HashMap<String, ArrayList<Double>>> getCategory_CategoryMap() {
		return category_CategoryMap;
	}

	@XmlElement
	public void setCategory_CategoryMap(HashMap<String, HashMap<String, ArrayList<Double>>> category_CategoryMap) {
		this.category_CategoryMap = category_CategoryMap;
	}

	
	public Set<String> getVocabulary() {
		return vocabulary;
	}

	@XmlElement
	public void setVocabulary(Set<String> vocabulary) {
		this.vocabulary = vocabulary;
	}


	
	
	public WareHouse(){
		category_NoOfWordsMap = new HashMap<String, Integer>();
		category_CategoryMap = new HashMap<String, HashMap<String, ArrayList<Double>> >();
		//vocabulary = new Set<String>();
	}

	
	public  void createCategoryMap(String category){
		if(!category_CategoryMap.containsKey(category)){
			HashMap<String, ArrayList<Double>> map =  new HashMap<String, ArrayList<Double>>();
			category_CategoryMap.put(category, map);
		}	
	}


	

}
