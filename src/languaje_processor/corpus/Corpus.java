package languaje_processor.corpus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import languaje_processor.parser.DocumentReader;
import languaje_processor.vocabulary.Vocabulary;
import languaje_processor.token.*;

public class Corpus {
	
	private final Token UNKNOWN_TOKEN = new Token(TokenType.UNKNOWN.getValue());
	private Set<Token> corpus;
	private Vocabulary vocabulary;
	private String name;
	private int numberOfDocuments = 0;
	private int numberOfWords = 0;
	
	public Corpus(String name, Vocabulary vocabulary, DocumentReader reader) {
		setCorpus(new TreeSet<Token>());
		setVocabulary(vocabulary);
		if (reader != null) {
			String strToken = reader.nextToken();
		    while (strToken != null) {
		    	Token token = new Token(strToken);
		    	addToken(token);
		    	strToken = reader.nextToken();
		    }
		    numberOfDocuments = reader.getNumberOfDocuments();
		    setAllTokensLogProb();
		}
	}
	
	public Set<Token> getCorpus() {
		return corpus;
	}
	
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
	
	public Integer getNumberOfDocuments() {
		return numberOfDocuments;
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			throw new NullPointerException("name can't be null");
		}
	}

	public void addToCorpus(String word) {
		if (word != null) {
			addToken(new Token(word));
			setAllTokensLogProb();
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	public boolean contains(String word) {
		if (word != null) {
			return getToken(word) != null;
		} else {
			throw new NullPointerException("token can't be null");
		}
	}
	
	public int getFrecuency(String word) {
		if (word != null) {
			Token token = getToken(word);
			return token != null ? token.getFrecuency() : 0;
		} else {
			throw new NullPointerException("word can't be null");
		}
	}
	
	public double getLogProb(String word) {
		if (word != null) {
			Token token = getToken(word);
			return token != null ? token.getLogProb() : UNKNOWN_TOKEN.getLogProb();
		} else {
			throw new NullPointerException("word can't be null");
		}
	}
	
	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("%d", getNumberOfDocuments()));
		writer.println(String.format("%d", getNumberOfWords()));
		writer.println(
		String.format("%s %d %.8f", UNKNOWN_TOKEN.getValue(), UNKNOWN_TOKEN.getFrecuency(), UNKNOWN_TOKEN.getLogProb()));
		for (Token token : getCorpus()) {
		  writer.println(
          String.format("%s %d %.8f", token.getValue(), token.getFrecuency(), token.getLogProb()));
		}
		writer.close();
	}
	
	/** Getters and Setters **/

	private void setCorpus(Set<Token> corpus) {
		if (corpus != null) {
			this.corpus = corpus;
		} else {
			throw new NullPointerException("corpus can't be null");
		}
	}


	private void setVocabulary(Vocabulary vocabulary) {
		if (vocabulary != null) {
			this.vocabulary = vocabulary;
		} else {
			throw new NullPointerException("vocabulary can't be null");
		}
	}
	
	private Token getToken(String word) {
		for (Token corpusToken : getCorpus()) {
			if (corpusToken.getValue().equals(word)) {
				return corpusToken;
			}
		}
		return null;
	}
	
	private void addToken(Token token) {
		numberOfWords += 1;
		for (Token corpusToken : getCorpus()) {
			if (token.getValue().equals(token.getValue())) {
				corpusToken.incrementFrecuency();
				return;
			}
		}
		getCorpus().add(token);
	}
	
	private void setAllTokensLogProb() {
		setLogProb(UNKNOWN_TOKEN);
		for (Token token : getCorpus()) {
		    setLogProb(token);
		}
	}
	
	private void setLogProb(Token token) {
	    if (token != null) {
	    	double logProb = Math.log(((double) (token.getFrecuency() + 1))
	  	          		   / ((double) (getNumberOfWords() + getVocabulary().getNumberOfWords())));
	    	token.setLogProb(logProb);
	    } else {
	      throw new NullPointerException("word can't be null");
	    }
	}
}
