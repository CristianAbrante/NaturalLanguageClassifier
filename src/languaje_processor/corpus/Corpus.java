package languaje_processor.corpus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import languaje_processor.parser.DocumentReader;
import languaje_processor.token.Token;
import languaje_processor.token.TokenType;
import languaje_processor.vocabulary.Vocabulary;

/**
 * The Class Corpus.
 */
public class Corpus {
	
	/** The unknown token. */
	private final Token UNKNOWN_TOKEN = new Token(TokenType.UNKNOWN.getValue(), 0, 0.0);
	
	/** The corpus. */
	private Set<Token> corpus;
	
	/** The vocabulary. */
	private Vocabulary vocabulary;
	
	/** The name. */
	private String name;
	
	/** The number of documents. */
	private int numberOfDocuments = 0;
	
	/** The number of words. */
	private int numberOfWords = 0;
	
	/**
	 * Instantiates a new corpus.
	 *
	 * @param name the name
	 * @param vocabulary the vocabulary
	 * @param reader the reader
	 */
	public Corpus(String name, Vocabulary vocabulary, DocumentReader reader) {
		setName(name);
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
	
	/**
	 * Gets the corpus.
	 *
	 * @return the corpus
	 */
	public Set<Token> getCorpus() {
		return corpus;
	}
	
	/**
	 * Gets the vocabulary.
	 *
	 * @return the vocabulary
	 */
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
	
	/**
	 * Gets the number of documents.
	 *
	 * @return the number of documents
	 */
	public Integer getNumberOfDocuments() {
		return numberOfDocuments;
	}

	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public Integer getNumberOfWords() {
		return numberOfWords;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			throw new NullPointerException("name can't be null");
		}
	}

	/**
	 * Adds the to corpus.
	 *
	 * @param word the word
	 */
	public void addToCorpus(String word) {
		if (word != null) {
			addToken(new Token(word));
			setAllTokensLogProb();
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	/**
	 * Contains.
	 *
	 * @param word the word
	 * @return true, if successful
	 */
	public boolean contains(String word) {
		if (word != null) {
			return getToken(word) != null;
		} else {
			throw new NullPointerException("token can't be null");
		}
	}
	
	/**
	 * Gets the frecuency.
	 *
	 * @param word the word
	 * @return the frecuency
	 */
	public int getFrecuency(String word) {
		if (word != null) {
			Token token = getToken(word);
			return token != null ? token.getFrecuency() : 0;
		} else {
			throw new NullPointerException("word can't be null");
		}
	}
	
	/**
	 * Gets the log prob.
	 *
	 * @param word the word
	 * @return the log prob
	 */
	public double getLogProb(String word) {
		if (word != null) {
			Token token = getToken(word);
			return token != null ? token.getLogProb() : UNKNOWN_TOKEN.getLogProb();
		} else {
			throw new NullPointerException("word can't be null");
		}
	}
	
	/**
	 * Export to file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("%d", getNumberOfDocuments()));
		writer.println(String.format("%d", getNumberOfWords()));
		writer.println(
		String.format("%s %d %f", UNKNOWN_TOKEN.getValue(), UNKNOWN_TOKEN.getFrecuency(), UNKNOWN_TOKEN.getLogProb()));
		for (Token token : getCorpus()) {
		  writer.println(
          String.format("%s %d %.8f", token.getValue(), token.getFrecuency(), token.getLogProb()));
		}
		writer.close();
	}
	
	
	/**
	 * Sets the corpus.
	 *
	 * @param corpus the new corpus
	 */
	private void setCorpus(Set<Token> corpus) {
		if (corpus != null) {
			this.corpus = corpus;
		} else {
			throw new NullPointerException("corpus can't be null");
		}
	}


	/**
	 * Sets the vocabulary.
	 *
	 * @param vocabulary the new vocabulary
	 */
	private void setVocabulary(Vocabulary vocabulary) {
		if (vocabulary != null) {
			this.vocabulary = vocabulary;
		} else {
			throw new NullPointerException("vocabulary can't be null");
		}
	}
	
	/**
	 * Gets the token.
	 *
	 * @param word the word
	 * @return the token
	 */
	private Token getToken(String word) {
		for (Token corpusToken : getCorpus()) {
			if (corpusToken.getValue().equals(word)) {
				return corpusToken;
			}
		}
		return null;
	}
	
	/**
	 * Adds the token.
	 *
	 * @param token the token
	 */
	private void addToken(Token token) {
		numberOfWords += 1;
		for (Token corpusToken : getCorpus()) {
			if (corpusToken.getValue().equals(token.getValue())) {
				corpusToken.incrementFrecuency();
				return;
			}
		}
		getCorpus().add(token);
	}
	
	/**
	 * Sets the all tokens log prob.
	 */
	private void setAllTokensLogProb() {
		setLogProb(UNKNOWN_TOKEN);
		for (Token token : getCorpus()) {
		    setLogProb(token);
		}
	}
	
	/**
	 * Sets the log prob.
	 *
	 * @param token the new log prob
	 */
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
