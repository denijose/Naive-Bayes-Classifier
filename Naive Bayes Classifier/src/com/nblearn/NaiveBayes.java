package com.nblearn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;

public class NaiveBayes {
	  
//      static HashMap<String, ArrayList<Double>> spamMap =  new HashMap<String, ArrayList<Double>>();
//      static HashMap<String, ArrayList<Double>> hamMap =  new HashMap<String, ArrayList<Double>>();
      
//      static private int totalNoOfWordsInSpam = 0;
//	  static private int totalNoOfWordsInHam = 0;
	
	private  WareHouse wareHouse = null;
      
      private  void createProbMaps(String trainingFileName){ 	  
    	    BufferedReader br = null;
	  		try {
				br = new BufferedReader(new FileReader( trainingFileName ));
				String curLine = null;
				while( (curLine = br.readLine()) != null ){
					//each line is a document
					String[] words = curLine.split(" ");
					String category = words[0];
					//create the category if the category is a new one
					if(!wareHouse.category_NoOfWordsMap.containsKey(category)){
						wareHouse.category_NoOfWordsMap.put(category, 0);
						wareHouse.category_FrequencyMap.put(category, 0);
						wareHouse.createCategoryMap(category);						
					}			
					// increment the frequency of the category occuring
					int categoryFrequency = wareHouse.category_FrequencyMap.get(category);
					categoryFrequency++;
					wareHouse.category_FrequencyMap.put(category, categoryFrequency);
					for(int i=1; i<words.length; i++ ){
							if(words[i].equals(" ") || words[i].equals("") || words[i].equals(".") )
								continue;
							//increment the no. of words
							int totalNoOfWordsinCategory = wareHouse.category_NoOfWordsMap.get(category);
							totalNoOfWordsinCategory++;
							wareHouse.category_NoOfWordsMap.put(category, totalNoOfWordsinCategory);
							UpdateCount(words[i], category);
					}
				}
				UpdateProb();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			} finally{
				try{
					if( br != null )
						br.close();
				} catch(IOException e){
					e.printStackTrace();
				}	
		    }
	  		
      }
      
    private void UpdateProb(){
    	for(String category : wareHouse.category_NoOfWordsMap.keySet()){
    		HashMap<String, ArrayList<Double>> map = wareHouse.category_CategoryMap.get(category); 
    		int totalNoOfWordsinCategory = wareHouse.category_NoOfWordsMap.get(category);
    		for(String word : map.keySet()){
    			ArrayList<Double> countAndProbability = map.get(word);
    			double wordFrequency =  countAndProbability.get(0);
    			double prob = Math.log10(wordFrequency/totalNoOfWordsinCategory);
    			countAndProbability.add(1, prob);
    			map.put(word, countAndProbability);
    		}
    	}
  	}  

	private  void UpdateCount(String word, String category) {
		HashMap<String, ArrayList<Double>> map = wareHouse.category_CategoryMap.get(category);
		if(!map.containsKey(word)){
			ArrayList<Double> countAndProbability =  new ArrayList<Double>();
			countAndProbability.add((double)1);
			map.put(word, countAndProbability);
			return;
		}		
		ArrayList<Double> countAndProbability = map.get(word);
		double wordCount = countAndProbability.get(0);
		wordCount++;		
		countAndProbability.set(0, wordCount);
		map.put(word,countAndProbability);
	} 
	
	public  void categorize(String trainingFileName) {
		wareHouse = new WareHouse();
		
		createProbMaps(trainingFileName);
		//System.out.println(spamMap.get("schedule").get(0));
		System.out.println(wareHouse.category_NoOfWordsMap.toString());
		String category = "SPAM";
		HashMap<String, ArrayList<Double>> map = wareHouse.category_CategoryMap.get(category);
		System.out.println(map.get("virtual").get(0));
		System.out.println(wareHouse.category_NoOfWordsMap.get(category));
		System.out.println(map.get("virtual").get(1));
		System.out.println(map.toString());
		
		category = "HAM";
		map = wareHouse.category_CategoryMap.get(category);
		System.out.println(map.get("hourahead").get(0));
		System.out.println(wareHouse.category_NoOfWordsMap.get(category));
		System.out.println(map.get("hourahead").get(1));
		System.out.println(map.toString());
		
		  try {
			  Gson gson = new Gson();
			  String json = gson.toJson(wareHouse);
			  FileWriter writer = new FileWriter("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test\\spam.nb");
			  writer.write(json);
				writer.close();
				System.out.println(json);
			      } catch (Exception e) {
				e.printStackTrace();
			      }
		  
	}
}
