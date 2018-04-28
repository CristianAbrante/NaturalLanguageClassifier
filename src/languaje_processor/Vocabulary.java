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
	
	private Set<String> words;
	private MessageScanner scanner;
	
	public Vocabulary(MessageScanner scanner) {
		words = new TreeSet<String>();
		setScanner(scanner);
		scanMessagesToVocabulary();
	}
	
	private void scanMessagesToVocabulary() {
		String token = getScanner().nextToken();
		while (token != null) {
			addToVocabylary(token);
			token = scanner.nextToken();
		}
	}
	
	public void addToVocabylary(String token) {
		getWords().add(token);
	}
	
	public int getNumberOfWords() {
		return getWords().size();
	}
	
	public void exportVocabularyToFile() throws IOException {
		FileWriter file = new FileWriter("data/vocabulario.txt");
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("Number of words: %d", getNumberOfWords()));
		 
	    Iterator<String> it = getWords().iterator();
	    while (it.hasNext()) {
			String token = it.next();
	    	writer.println(token);
	    }
	    writer.close();
	}

	public String getWord(String word) throws Exception {
		Iterator<String> it = getWords().iterator();
		String token = new String();
		while (it.hasNext()) {
			token = it.next();
			if (word.equals(token))
				return token;			
		}
		throw new Exception("Can not find this word in the set of words");
	}
	
	/** Getters and Setters **/

	public Set<String> getWords() {
		return words;
	}

	public void setWords(Set<String> words) {
		this.words = words;
	}

	public MessageScanner getScanner() {
		return scanner;
	} 

	public void setScanner(MessageScanner scanner) {
		this.scanner = scanner;
	}
}
