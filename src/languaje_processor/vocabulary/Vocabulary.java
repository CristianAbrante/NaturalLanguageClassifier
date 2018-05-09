package languaje_processor.vocabulary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import languaje_processor.parser.DocumentReader;
import languaje_processor.token.Token;
import languaje_processor.token.TokenType;

/**
 * The Class Vocabulary.
 */
public class Vocabulary {
	
  private final int UNKNOWN_TOKEN_COUNTER = 1;
  
	private Set<String> words;
	
	public Vocabulary(DocumentReader reader) {
	  words = new TreeSet<String>();
	  if (reader != null) {
	    String token = reader.nextToken();
	    while (token != null) {
	      addToVocabylary(token);
	      token = reader.nextToken();
	    }
	  } else {
	    throw new IllegalArgumentException("reader can't be null");
	  }
	}
	
	public Vocabulary(VocabularyReader reader) {
	  words = new TreeSet<String>();
	  if (reader != null) {
	      String token = reader.nextToken();
	      while (token != null) {
	        addToVocabylary(token);
	        token = reader.nextToken();
	      }
	    } else {
	      throw new IllegalArgumentException("reader can't be null");
	    }
	}
	
	public Set<String> getWords() {
		return words;
	}
	
	public void addToVocabylary(String token) {
	  if (token != null) {
	    getWords().add(token);	    
	  } else {
	    throw new NullPointerException("token can't be null");
	  }
	}
	
	public int getNumberOfWords() {
		return getWords().size() + UNKNOWN_TOKEN_COUNTER;
	}
	
	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("%d", getNumberOfWords()));
		writer.println(TokenType.UNKNOWN.getValue());
		for (String token : getWords()) {
		  writer.println(token);
		}
		writer.close();
	}
	
	public boolean contains(String word) {
	  Iterator<String> it = getWords().iterator();
	  String token = new String();
	  while(it.hasNext()) {
	    token = it.next();
	    if (word.equals(token)) {
	      return true;
	    }
	  }
	  return false;
	}
}