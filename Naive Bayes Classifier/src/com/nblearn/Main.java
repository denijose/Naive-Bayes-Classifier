package com.nblearn;

import java.io.IOException;
import java.util.ArrayList;

import com.nbclassify.NaiveBayesClassify;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		ArrayList<String> correctClassification = new ArrayList<String>();
		try {
			correctClassification = FormatInput.createTrainingorTestDataFile("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test.txt","C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\SPAM_dev","test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		NaiveBayes nblearn = new NaiveBayes();
//		nblearn.learn("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\spam_training.txt");
		
		NaiveBayesClassify  nbClassify = new NaiveBayesClassify();
		String modelName = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\spam.nb";
		String fileName = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test.txt";
		ArrayList<String> classification = nbClassify.classify(fileName, modelName);
		System.out.println("my classification - \n" +classification);
		System.out.println("correct classification -\n" + classification);
		System.out.println("The F Score = " + calculateFScore(correctClassification,classification, "SPAM"));

	}

	private static double calculateFScore(ArrayList<String> correctClassification, ArrayList<String> classification, String category) {
		if(correctClassification.size()!=classification.size()){
			System.out.println("There was error in finding out the classification");
		    return 	-1;
		}
		
		double Fscore = 0, precision = 0, recall = 0;
		int correctlyClassifiedAsC = 0, belongsInC = 0, classifiedAsC = 0;
		
		for(int i=0; i<classification.size();i++){
			
			System.out.println(correctClassification.get(i) + " ---- " + classification.get(i));
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
		System.out.println(classifiedAsC);
		System.out.println(correctlyClassifiedAsC);
		System.out.println(belongsInC);
		System.out.println(Fscore);
		return Fscore;
	}

}
