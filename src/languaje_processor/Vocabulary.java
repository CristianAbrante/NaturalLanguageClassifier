package languaje_processor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class Vocabulary.
 */
public class Vocabulary {
	
  private final String UNKNOWN_TOKEN = "<unk>";
  private final int UNKNOWN_TOKEN_COUNTER = 1;
  
	private Set<String> words;
	private MessageScanner scanner;
	
	public Vocabulary(MessageScanner scanner) {
		words = new TreeSet<String>();
		setScanner(scanner);
		addTokensToVocabulary();
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
		writer.println(String.format("Number of words: %d", getNumberOfWords()));
		writer.println(UNKNOWN_TOKEN);
		for (String word : getWords()) {
		  writer.println(word);
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
	
	/** Getters and Setters **/

	private MessageScanner getScanner() {
		return scanner;
	} 

	private void setScanner(MessageScanner scanner) {
	  if (scanner != null) {
	    this.scanner = scanner;	    
	  } else {
	    throw new NullPointerException("scanner can't be null");
	  }
	}
	
	private void addTokensToVocabulary() {
	  String token = getScanner().nextToken();
    while (token != null) {
      addToVocabylary(token);
      token = getScanner().nextToken();
    }
  }
}
