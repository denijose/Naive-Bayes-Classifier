package com.nblearn;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		try {
//			FormatInput.createTrainingDataFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		NaiveBayes nb = new NaiveBayes();
		nb.categorize("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test\\spam_training.txt");
		
		

	}

}
