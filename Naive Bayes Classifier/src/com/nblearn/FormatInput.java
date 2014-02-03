package com.nblearn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FormatInput {
	
	public static ArrayList<String> createTrainingorTestDataFile(String outPutFileName, String dirName, String purpose) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		File dir = new File(dirName);
    	File outPutFile = new File(outPutFileName);
    	ArrayList<String> classification = new ArrayList<String>(); 	
    		
    	if( outPutFile.exists()){
    		outPutFile.delete();
    		outPutFile.createNewFile();
    	}
    	outPutFile.createNewFile();
    	for(String fileName : dir.list()){    		
			try{				
				//check if the file being examined is the spam training file itself!!
				if( (dir.getAbsolutePath() + "\\"+ fileName).equals(outPutFileName))
				  continue;				
				System.out.println("Extracting text from " + dir.getAbsolutePath() + "\\"+ fileName + "...");
				
				br = new BufferedReader(new FileReader( dir.getAbsolutePath() + "\\"+ fileName) );
				bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true)); //open file for appending
				String curLine = null;
				String[] array = fileName.split("\\.");
				String label = array[0];
				classification.add(label);
				if(purpose.contains("train"))
				   bw.write(label + " ");
				while( (curLine = br.readLine()) != null )		    	
			    	bw.write(curLine);
	
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
    	System.out.println("Finished extracting all the text in to the file - " +  outPutFileName);
    	return classification;
	}
	
	

}
