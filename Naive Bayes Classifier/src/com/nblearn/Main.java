package com.nblearn;

import java.io.IOException;

import com.nblearn.FormatInput;
import com.nblearn.NaiveBayes;

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
		if(DIRNAME!=null){
			try {
				FormatInput.createTrainingorTestDataFile(TRAININGORTESTFILE,"C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\SENTIMENT_dev",MODE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		NaiveBayes nblearn = new NaiveBayes();
		nblearn.learn(TRAININGORTESTFILE);
		System.out.println("Finished Learning.....");

	}
	
	private static void parseArgs(String[] args) {
		TRAININGORTESTFILE = args[0];
		MODELFILE = args[1];
		MODE = args[3];		
		DIRNAME = args[2];
		if(TRAININGORTESTFILE == null || MODELFILE == null){
			System.out.println("Insufficient arguments. Usage -");
			System.out.println("java -jar nblearn.jar TRAININGFILE MODELFILE");
			System.exit(1);
		}			
		if(MODE == null)
			MODE = "training";
	}
	
}

	