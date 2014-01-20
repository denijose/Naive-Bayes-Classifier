package com.nblearn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatInput {
	
	public static void createTrainingDataFile(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			File file = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\SPAM_training\\spam_training.txt");
	    	if( file.exists()){
	    		file.delete();
	    		file.createNewFile();
	    	}
				
			
			br = new BufferedReader(new FileReader("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\SPAM_training\\HAM.3.txt"));
			String curLine = null;
			
			// add a space delimeter for every new line except the first one
			if( (curLine = br.readLine()) != null ){
				bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true)); //open file for appending
		    	bw.write(curLine);	
		    	bw.close();
		    }		
			while( (curLine = br.readLine()) != null ){
		    	bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true)); //open file for appending
		    	bw.write(" " + curLine);
		    	bw.close();
		    }	
		    
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
	
	

}
