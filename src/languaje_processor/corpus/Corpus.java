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
  
	private Set<Token> corpus;
	private Vocabulary vocabulary;
	private String name;
	private int numberOfDocuments = 0;
	private int numberOfWords = 0;
	
	public Corpus(String name, Vocabulary vocabulary, DocumentReader reader) {
		setCorpus(new TreeSet<Token>());
		setVocabulary(vocabulary);
		if (reader != null) {
		  Token token = reader.nextToken();
	    while (token != null) {
	      addToCorpus(token);
	      token = reader.nextToken();
	    }
	    numberOfDocuments = reader.getNumberOfDocuments();
	    setLogProb();
		}
	}

	public Set<Token> getCorpus() {
    return corpus;
  }
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			throw new NullPointerException("name can't be null");
		}
	}

	private void addToCorpus(Token token) {
		if (this.contains(token)) {
		  for (Token currentToken : getCorpus()) {
		    if (currentToken.getValue().equals(token.getValue())) {
          currentToken.incrementFrecuency();
        }
	    }
		} else {
			getCorpus().add(token);
		}
		numberOfWords += 1;
	}

	public boolean contains(Token token) {
		if (token != null) {
			return getCorpus().contains(token);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("%d", getNumberOfDocuments()));
		writer.println(String.format("%d", getNumberOfWords()));
		for (Token token : getCorpus()) {
		  writer.println(
          String.format("%s %d %.8f", token.getValue(), token.getFrecuency(), token.getLogProb()));
		}
		writer.close();
	}

	public Integer getNumberOfDocuments() {
		return numberOfDocuments;
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}

	/** Getters and Setters **/

	private void setCorpus(Set<Token> corpus) {
		if (corpus != null) {
			this.corpus = corpus;
		} else {
			throw new NullPointerException("corpus can't be null");
		}
	}

	private Vocabulary getVocabulary() {
		return vocabulary;
	}

	private void setVocabulary(Vocabulary vocabulary) {
		if (vocabulary != null) {
			this.vocabulary = vocabulary;
		} else {
			throw new NullPointerException("vocabulary can't be null");
		}
	}
	
	private Double getLogProb(Token word) {
    if (word != null) {
      return Math.log(((double) (word.getFrecuency() + 1))
          / ((double) (getNumberOfWords() + getVocabulary().getNumberOfWords())));
    } else {
      throw new NullPointerException("word can't be null");
    }
  }
	
	private void setLogProb() {
	  for (Token token : getCorpus()) {
	    token.setLogProb(getLogProb(token));
	  }
	}
}
