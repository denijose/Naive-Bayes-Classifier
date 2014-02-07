package com.nbclassify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.nblearn.FormatInput;

public class Main {

	/**
	 * @param args
	 */
	
	private static String TRAININGORTESTFILE;
	private static String MODELFILE;
	private static String MODE;
	private static String DIRNAME;
	
	public static void main(String[] args) {
		
		parseArgs(args);		
		
		ArrayList<String> correctClassification = new ArrayList<String>();
		correctClassification =  null;
		if(DIRNAME!=null){
			try {
				correctClassification = FormatInput.createTrainingorTestDataFile(TRAININGORTESTFILE,DIRNAME,MODE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Classifying ...");
		NaiveBayesClassify  nbClassify = new NaiveBayesClassify();
		ArrayList<String> classification = nbClassify.classify(TRAININGORTESTFILE, MODELFILE);
		System.out.println("My classification -");
        //writeToFile(classification);
		for(int i=0; i<classification.size();i++)
			System.out.println(classification.get(i));
		System.out.println("correct classification -\n" + classification);
		if(correctClassification != null){
		    System.out.println("The correct classification has been detected from the test files. Please enter the category for which the FScore must be found");
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    String category = null;
			try {
				category = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("The F Score = " + calculateFScore(correctClassification,classification, category));
		}
	}

	private static void writeToFile(ArrayList<String> classification) {
		BufferedWriter bw = null;
		File outPutFile = new File(TRAININGORTESTFILE+"\\sentiment.out");
    	if( outPutFile.exists()){
    		outPutFile.delete();
    		try {
				outPutFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	try {
			outPutFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(int i=0; i<classification.size();i++){
    		try {
				bw.write(classification.get(i));
				 bw.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   
    	}    
    	try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void parseArgs(String[] args) {
		if(args.length < 2){
			System.out.println("Insufficient arguments. Usage -");
			System.out.println("java -jar nblearn.jar MODELFILE TESFILE");
			System.exit(1);
		}
		MODELFILE = args[0];
		TRAININGORTESTFILE = args[1];
		MODE = "training";
        if(args.length > 2)
        	DIRNAME = args[2];
        if(args.length > 3)
        	MODE = args[3];
	}

	private static double calculateFScore(ArrayList<String> correctClassification, ArrayList<String> classification, String category) {
		if(correctClassification.size()!=classification.size()){
			System.out.println("There was error in finding out the classification");
		    return 	-1;
		}
		
		double Fscore = 0, precision = 0, recall = 0;
		int correctlyClassifiedAsC = 0, belongsInC = 0, classifiedAsC = 0;
		
		for(int i=0; i<classification.size();i++){
			if(classification.get(i).equalsIgnoreCase(category)){
				classifiedAsC++;
				if(correctClassification.get(i).equalsIgnoreCase(classification.get(i)))
					correctlyClassifiedAsC++;
			}
			if(correctClassification.get(i).equalsIgnoreCase(category))  
				belongsInC++;		
		}
		precision = (double)correctlyClassifiedAsC/(double)classifiedAsC;
		recall = (double)correctlyClassifiedAsC/(double)belongsInC;
		Fscore = (2*precision*recall)/(precision+recall);
//		System.out.println(classifiedAsC);
//		System.out.println(correctlyClassifiedAsC);
//		System.out.println(belongsInC);
		System.out.println(precision);
		System.out.println(recall);
//		System.out.println(Fscore);
		return Fscore;
	}

}
