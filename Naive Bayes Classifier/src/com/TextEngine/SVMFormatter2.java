package com.TextEngine;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class SVMFormatter2 {

	/**
	 * @param args
	 */
	private static  HashMap<String,Integer> INDEXMAP;
	private static  LinkedHashMap<String, HashMap<String, Double> > TFMAP;	
	private static  HashMap<String,Double > IDFMAP;
	private static  LinkedHashMap<String, HashMap<String, Double> > TFMAPFORTEST;
	private static  HashMap<String,Double > IDFMAPFORTEST;
	private static  int INDEX;
	
	private static  String TRAININGDIRNAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\SPAM_test"; // denis\\folder"; //SPAM_training";
	private static  String TESTDIRNAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\SPAM_test"; //denis\\folder2"; //SPAM_dev";
	private static  String TRAININGFILENAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\spamTraining2.dat";
	private static  String TESTFILENAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\spamTest2.dat";
	private static  String POSCATEGORY = "POS";
	
	public static void main(String[] args) throws IOException {
		initializeDataStrucures();
       
		createTFMAPforTraining();
        createIDFMAPforTraining();
        createTrainingFile();
       
        createTFMAPforTest();
        createIDFMAPforTest();
        createTestFile();
        

	}

	private static void createTestFile() throws IOException {
		System.out.println("Creating the SVM test file....This may take some time.....");
		BufferedReader br = null;
		BufferedWriter bw = null;
		File dir = new File(TESTDIRNAME);
    	File outPutFile = new File(TESTFILENAME);
    	if( outPutFile.exists()){
    		outPutFile.delete();
    		outPutFile.createNewFile();
    	}
    	outPutFile.createNewFile();
    	for(String fileName : dir.list()){    	
    		//check if the file being examined is the spam training file itself!!
			if( (dir.getAbsolutePath() + "\\"+ fileName).equals(TESTFILENAME))
			  continue;
			try{				
				br = new BufferedReader(new FileReader(dir.getAbsolutePath() + "\\"+ fileName) );
				bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true));
				String curLine = null;
				String[] array = fileName.split("\\.");
				if(array[0].equalsIgnoreCase(POSCATEGORY))
					bw.write("0 ");
				else
					bw.write("0 ");
				HashMap<String,Integer> unsortedIndexMap = new HashMap<String,Integer>();
				LinkedHashMap<String,Integer> sortedIndexMap = new LinkedHashMap<String,Integer>();
				while( (curLine = br.readLine()) != null ){
					String[] words = curLine.split(" ");	
					for(int i=0; i< words.length;i++){
						if(INDEXMAP.containsKey(words[i])){
							unsortedIndexMap.put(words[i],INDEXMAP.get(words[i]));
							//bw.write(words[i]+"->"+INDEXMAP.get(words[i])+ ":" + (TFMAP.get(words[i]).get(fileName)*IDFMAP.get(words[i])) + " ");
						}
						
					}
					//bw.write("\n");
				}
				if(unsortedIndexMap.size() > 0){
					sortedIndexMap = sortIndeces(unsortedIndexMap);
					for(String word : sortedIndexMap.keySet())
						bw.write(INDEXMAP.get(word)+ ":" + (TFMAPFORTEST.get(word).get(fileName)*IDFMAPFORTEST.get(word)) + " ");
				}
				bw.write("\n");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if( br != null )
						br.close();
					if( bw != null )
					    bw.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}	
		    }
		
    	}
    	System.out.println("Finished creating the SVM test file " + TESTFILENAME);
		
		
	}

	private static void createTrainingFile() throws IOException {
		System.out.println("Creating the SVM training file....This may take some time.....");
//		for(String word : TFMAP.keySet()){
//			System.out.print(word);
//			for(String fileName : TFMAP.get(word).keySet()){
//				System.out.print(" " + fileName + " " + TFMAP.get(word).get(fileName));
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//		System.out.println("---------------------");
//		for(String word : IDFMAP.keySet())
//			System.out.println(word+" "+IDFMAP.get(word));
//		
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		File dir = new File(TRAININGDIRNAME);
    	File outPutFile = new File(TRAININGFILENAME);
    	if( outPutFile.exists()){
    		outPutFile.delete();
    		outPutFile.createNewFile();
    	}
    	outPutFile.createNewFile();
    	for(String fileName : dir.list()){    	
    		//check if the file being examined is the spam training file itself!!
			if( (dir.getAbsolutePath() + "\\"+ fileName).equals(TRAININGFILENAME))
			  continue;
			try{				
				br = new BufferedReader(new FileReader(dir.getAbsolutePath() + "\\"+ fileName) );
				bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true));
				String curLine = null;
				String[] array = fileName.split("\\.");
				if(array[0].equalsIgnoreCase(POSCATEGORY))
					bw.write("0 ");
				else
					bw.write("0 ");
				HashMap<String,Integer> unsortedIndexMap = new HashMap<String,Integer>();
				LinkedHashMap<String,Integer> sortedIndexMap = new LinkedHashMap<String,Integer>();
				while( (curLine = br.readLine()) != null ){
					String[] words = curLine.split(" ");
					for(int i=0; i< words.length;i++){
						//bw.write(words[i]+"->"+INDEXMAP.get(words[i])+ ":" + (TFMAP.get(words[i]).get(fileName)*IDFMAP.get(words[i])) + " ");
						unsortedIndexMap.put(words[i],INDEXMAP.get(words[i]));
					}
					//bw.write("\n");
				}
				if(unsortedIndexMap.size() > 0){
					sortedIndexMap = sortIndeces(unsortedIndexMap);
					for(String word : sortedIndexMap.keySet())
						bw.write(INDEXMAP.get(word)+ ":" + (TFMAP.get(word).get(fileName)*IDFMAP.get(word)) + " ");
				}
				bw.write("\n");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if( br != null )
						br.close();
					if( bw != null )
					    bw.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}	
		    }
		
    	}
    	System.out.println("Finished creating the SVM training file " + TRAININGFILENAME);
		
	}

	private static LinkedHashMap<String, Integer> sortIndeces(HashMap<String, Integer> passedMap) {
		 		ArrayList<String> mapKeys = new ArrayList<String>(passedMap.keySet());
		 		ArrayList<Integer> mapValues = new ArrayList<Integer>(passedMap.values());
			   Collections.sort(mapValues);
			   Collections.sort(mapKeys);

			   LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

			   Iterator<Integer> valueIt = mapValues.iterator();
			   while (valueIt.hasNext()) {
			       Object val = valueIt.next();
			       Iterator<String> keyIt = mapKeys.iterator();

			       while (keyIt.hasNext()) {
			           Object key = keyIt.next();
			           String comp1 = passedMap.get(key).toString();
			           String comp2 = val.toString();

			           if (comp1.equals(comp2)){
			               passedMap.remove(key);
			               mapKeys.remove(key);
			               sortedMap.put((String)key, (Integer)val);
			               break;
			           }

			       }

			   }
			   return sortedMap;
	}
	

	private static void createIDFMAPforTraining() {
		System.out.println("Calculating the IDF for the training directory...");
		File dir = new File(TRAININGDIRNAME);
		double totalNoOfDocuments = dir.list().length;
		for(String word : TFMAP.keySet()){
			double NoOfDocumentsHavingTheWord = (double)TFMAP.get(word).keySet().size();
			double idf = totalNoOfDocuments/NoOfDocumentsHavingTheWord;
			idf = Math.log(idf);
			IDFMAP.put(word, idf);
		}		
	}

	private static void initializeDataStrucures() {
		System.out.println("Initializing data structures...");
		INDEXMAP = new HashMap<String,Integer > ();
		TFMAP = new  LinkedHashMap<String, HashMap<String, Double> >();
		IDFMAP = new HashMap<String,Double > ();		
		TFMAPFORTEST = new  LinkedHashMap<String, HashMap<String, Double> >();
		IDFMAPFORTEST = new HashMap<String,Double > ();
		INDEX = 0;
		
		INDEXMAP.put("", INDEX++);
	}

	private static void createTFMAPforTraining() {
		System.out.println("Calculating the term frequencies....This may take some time.....");
		BufferedReader br = null;
		File dir = new File(TRAININGDIRNAME);		
		for(String fileName : dir.list()){    		
			try{				
				br = new BufferedReader(new FileReader(dir.getAbsolutePath() + "\\"+ fileName) );
				String curLine = null;
				while( (curLine = br.readLine()) != null ){
					String[] words = curLine.split(" ");
					for(int i=0; i< words.length;i++){
						//if the word doesnt exist in the TFMAP
						if(TFMAP.get(words[i]) == null){
							HashMap<String, Double> tf = new HashMap<String, Double>();
							tf.put(fileName, (double)1);
							TFMAP.put(words[i], tf);
							INDEXMAP.put(words[i], INDEX++);
						}
						else{ // if the word exists in the dictionary
							//check if there is a document mapped to it
							HashMap<String, Double> tf = TFMAP.get(words[i]);
							if(tf.get(fileName) == null)
								tf.put(fileName, (double)1);
							else{
								double tfValue = tf.get(fileName);
								tfValue++;
								tf.put(fileName, tfValue);
							}								
						}
					}
				}		    	
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if( br != null )
						br.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}	
		    }
		
    	}

		
	}

	private static void createTFMAPforTest() {
		System.out.println("Calculating the term frequencies from the test directory....This may take some time.....");
		BufferedReader br = null;
		File dir = new File(TESTDIRNAME);		
		for(String fileName : dir.list()){    		
			try{				
				br = new BufferedReader(new FileReader(dir.getAbsolutePath() + "\\"+ fileName) );
				String curLine = null;
				while( (curLine = br.readLine()) != null ){
					String[] words = curLine.split(" ");
					for(int i=0; i< words.length;i++){
						//Caclulate the tf only if the word  exist in the training set else ignore the word
						if(IDFMAP.containsKey(words[i])){
							if(TFMAPFORTEST.get(words[i]) == null){
								HashMap<String, Double> tf = new HashMap<String, Double>();
								tf.put(fileName, (double)1);
								TFMAPFORTEST.put(words[i], tf);								
							}
							else{ // if the word exists in the dictionary
								//check if there is a document mapped to it
								HashMap<String, Double> tf = TFMAPFORTEST.get(words[i]);
								if(tf.get(fileName) == null)
									tf.put(fileName, (double)1);
								else{
									double tfValue = tf.get(fileName);
									tfValue++;
									tf.put(fileName, tfValue);
								}								
							}							
						}
						else
							;//ignore the word

					}
				}		    	
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if( br != null )
						br.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}	
		    }
		
    	}		
	}
	
	private static void createIDFMAPforTest() {
		System.out.println("Calculating the IDF for the test directory...");
		File dir = new File(TESTDIRNAME);
		double totalNoOfDocuments = dir.list().length;
		for(String word : TFMAPFORTEST.keySet()){
			double NoOfDocumentsHavingTheWord = (double)TFMAPFORTEST.get(word).keySet().size();
			double idf = totalNoOfDocuments/NoOfDocumentsHavingTheWord;
			idf = Math.log(idf);
			IDFMAPFORTEST.put(word, idf);
		}		
	}
}
