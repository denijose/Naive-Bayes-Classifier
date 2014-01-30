package com.nblearn;

import java.io.IOException;
import java.util.ArrayList;

import com.nbclassify.NaiveBayesClassify;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
//		try {
//			FormatInput.createTrainingDataFile("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test.txt","C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\SPAM_dev","test");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		NaiveBayes nblearn = new NaiveBayes();
//		nblearn.categorize("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\spam_training.txt");
//		

		
		
		NaiveBayesClassify  nbClassify = new NaiveBayesClassify();
		String modelName = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\spam.nb";
		String fileName = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test.txt";
		ArrayList<String> classification = nbClassify.classify(fileName, modelName);
		System.out.println(classification);

	}

}
