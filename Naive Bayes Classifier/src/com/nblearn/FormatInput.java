package com.nblearn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatInput {
	
	public static void createTrainingDataFile() throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		File dir = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test");
    	File spamTrainingFile = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\test\\spam_training.txt");
		
    	if( spamTrainingFile.exists()){
    		spamTrainingFile.delete();
    		spamTrainingFile.createNewFile();
    	}
		
    	for(String fileName : dir.list()){    		
			try{
				
				//check if the file being examined is the spam training file itself!!
				if( (dir.getAbsolutePath() + "\\"+ fileName).equals(spamTrainingFile.getAbsolutePath()))
				  continue;
				
				System.out.println("Extracting text from " + dir.getAbsolutePath() + "\\"+ fileName + "...");
				
				br = new BufferedReader(new FileReader( dir.getAbsolutePath() + "\\"+ fileName) );
				bw = new BufferedWriter(new FileWriter(spamTrainingFile.getAbsoluteFile(), true)); //open file for appending
				String curLine = null;
				String label = "yo";
				if( fileName.toUpperCase().contains("SPAM"))
					label = "SPAM";
				if( fileName.toUpperCase().contains("HAM"))
					label = "HAM";
				
				bw.write(label + " ");
				while( (curLine = br.readLine()) != null )		    	
			    	bw.write(" " + curLine);
	
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
	}
	
	

}
