package com.nblearn;

import java.io.IOException;

import com.nblearn.FormatInput;
import com.nblearn.NaiveBayesLearn;

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
		// if the outPutFile has the same name as the dirName then add .txt to the end of the file name else, it wont get created!! BUGFIX
		if(TRAININGORTESTFILE.equalsIgnoreCase(DIRNAME))
        	TRAININGORTESTFILE = TRAININGORTESTFILE.concat(".txt");
		if(DIRNAME!=null){
			try {
				FormatInput.createTrainingorTestDataFile(TRAININGORTESTFILE,DIRNAME,MODE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Starter Learning.....");
		NaiveBayesLearn nblearn = new NaiveBayesLearn();
		nblearn.learn(TRAININGORTESTFILE, MODELFILE);
		System.out.println("Finished Learning.....");

	}
	
	private static void parseArgs(String[] args) {
		if(args.length < 2){
			System.out.println("Insufficient arguments. Usage -");
			System.out.println("java -jar nblearn.jar TRAININGFILE MODELFILE");
			System.exit(1);
		}
		TRAININGORTESTFILE = args[0];
		MODELFILE = args[1];
		MODE = "training";
        if(args.length > 2)
        	DIRNAME = args[2];
        if(args.length > 3)
        	MODE = args[3];
			
	}
	
}

	