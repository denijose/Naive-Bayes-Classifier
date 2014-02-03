package com.TextEngine;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.LockObtainFailedException;


public class TestIndex {

	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException
	{
		Indexer indexer = new Indexer("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\denis\\folder", "C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut");
		indexer.index();		
		TermProcessor processor = new TermProcessor();
		processor.processDocument("C:\\D Drive\\KNOWLEDGE IS POWER\\NLP\\HW1 Spam Filter and Sentiment Analysis\\indexOut");
		
		
	}
}
