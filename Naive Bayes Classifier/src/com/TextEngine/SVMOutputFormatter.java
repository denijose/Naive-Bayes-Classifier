package com.TextEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SVMOutputFormatter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		File inputFile = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\New Folder\\spam.part2.unformatterout");
    	File outPutFile = new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\Final Submission\\New Folder\\spam.part2.out");
    	if( outPutFile.exists()){
    		outPutFile.delete();
    		outPutFile.createNewFile();
    	}
    	outPutFile.createNewFile();
		try{				
			br = new BufferedReader(new FileReader(inputFile.getAbsoluteFile()));
			bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true)); //open file for appending
			String curLine = null;
			while( (curLine = br.readLine()) != null ){
				String[] words = curLine.split(" ");	
                if(Double.parseDouble(words[0]) > 0)
                	bw.write("SPAM\n");
                else
                	bw.write("HAM\n");
			
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
