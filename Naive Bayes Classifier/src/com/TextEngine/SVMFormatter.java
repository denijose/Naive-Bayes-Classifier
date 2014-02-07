package com.TextEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

public class SVMFormatter {

	/**
	 * @param args
	 */
	
	private static  HashMap<String,Double> VOCAB;
	private static  String DIRNAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\denis\\folder";
	private static  String INDEXOUT = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut";
	private static  String OUTPUTFILENAME = "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\denis\\folder\\output";
	
	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException {
		// TODO Auto-generated method stub		
		//create the vocab
		//createVocab();  
		
		getTFIDF();
		
		//createOutputFile();
	}

	private static void createOutputFile() throws CorruptIndexException, IOException {
		
		TermProcessor processor = new TermProcessor();
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut")));
		TermEnum termEnum = reader.terms();
		ArrayList<String> termList = new ArrayList<String>();
		while (termEnum.next())
		{
			String term = termEnum.term().text(); 
			termList.add(term);
		}
		
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		File dir = new File(DIRNAME);
    	File outPutFile = new File(OUTPUTFILENAME);
    	int docNo = 0;
    	
    	for(String fileName : dir.list()){   
    		
			try{				
			br = new BufferedReader(new FileReader( dir.getAbsolutePath() + "\\"+ fileName) );
			//bw = new BufferedWriter(new FileWriter(outPutFile.getAbsoluteFile(), true)); //open file for appending
			String curLine = null;
			while( (curLine = br.readLine()) != null ){
				String[] words = curLine.split(" ");
				for(int i=0; i< words.length;i++){
					System.out.println(termList.indexOf(words[i])+":"+processor.getTFIDF(reader, words[i], "contents", docNo));
					
				}
					
			}
			docNo++;
			//bw.write("\n");
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

	private static void getTFIDF() throws CorruptIndexException, LockObtainFailedException, IOException {
		//Indexer indexer = new Indexer("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\denis\\folder", "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut");
		//indexer.index();
		TermProcessor processor = new TermProcessor();
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut")));
		TermEnum termEnum = reader.terms();
		ArrayList<String> termList = new ArrayList<String>();
		while (termEnum.next())
		{
			String term = termEnum.term().text(); 
			termList.add(term);
		}
		
		for (int i = 0; i < termList.size(); i++)
		{
			String term = termList.get(i);
			System.out.print(term+"-"+termList.indexOf(term));
			for (int j = 0; j < reader.numDocs(); j++)
				System.out.printf("%4.2f\t",processor.getTFIDF(reader, term, "contents", j));
	
		}
		
		Indexer indexer = new Indexer("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\denis\\folder2", "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut2");
		indexer.index();
		termEnum = reader.terms();
		termList = new ArrayList<String>();
		while (termEnum.next())
		{
			String term = termEnum.term().text(); 
			termList.add(term);
		}
		
		for (int i = 0; i < termList.size(); i++)
		{
			String term = termList.get(i);
			System.out.print(term+"-"+termList.indexOf(term));
			for (int j = 0; j < reader.numDocs(); j++){
				System.out.print(reader.document(j).getFields().size());
				System.out.printf("%4.2f\t",processor.getTFIDF(reader, term, "contents", j));
			}
	
		}
		
	}

	private static void createVocab() {
		VOCAB = new HashMap<String, Double>();
		File dir = new File(DIRNAME);
		BufferedReader br = null;
    	for(String fileName : dir.list()){    		
			try{	
				br = new BufferedReader(new FileReader(dir.getAbsolutePath() + "\\"+ fileName) );
				String curLine = null;
				while( (curLine = br.readLine()) != null ){
					String[] words = curLine.split(" ");
					for(int i=1; i<words.length; i++ )
						VOCAB.put(words[i], (double)0);
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if( br != null )
						br.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}	
		    }		
    	}
		
	}

}
