package languaje_processor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * The Class LanguageCorpus.
 */
public class LanguageCorpus {
	
	/** The corpus. */
	TreeMap<String, Integer> corpus = new TreeMap<String, Integer>();
	
	/** The scanner. */
	MessageScanner scanner;
	
	/**
	 * Instantiates a new language corpus.
	 *
	 * @param scanner the scanner
	 */
	public LanguageCorpus(MessageScanner scanner) {
		setScanner(scanner);
		scanMessages();
	}
	
	/**
	 * Gets the corpus.
	 *
	 * @return the corpus
	 */
	public TreeMap<String, Integer> getCorpus() {
		return corpus;
	}
	
	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public int getNumberOfWords() {
		return getCorpus().size();
	}
	
	/**
	 * Token in corpus.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	public boolean tokenInCorpus(String token) {
		if (token != null) {
			return getCorpus().containsKey(token);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}
	
	/**
	 * Gets the number of times.
	 *
	 * @param token the token
	 * @return the number of times
	 */
	public int getNumberOfTimes(String token) {
		return getCorpus().get(token);
	}
	
	/**
	 * Gets the scanner.
	 *
	 * @return the scanner
	 */
	public MessageScanner getScanner() {
		return scanner;
	}
	
	/**
	 * Export corpus to file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exportCorpusToFile(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.print(String.format("Number of words: %d", getNumberOfWords()));
		 
	    Iterator<Entry<String, Integer>> it = getCorpus().entrySet().iterator();
	    while (it.hasNext()) {
	    	@SuppressWarnings("rawtypes")
			Map.Entry token = (Map.Entry) it.next();
	    	writer.println(((String) token.getKey()));
	    }
	    writer.close();
	}
	
	/**
	 * Sets the scanner.
	 *
	 * @param scanner the new scanner
	 */
	private void setScanner(MessageScanner scanner) {
		this.scanner = scanner;
	}
	
	/**
	 * Adds the token to corpus.
	 *
	 * @param token the token
	 */
	private void addTokenToCorpus(String token) {
		if (tokenInCorpus(token)) {
			int times = getCorpus().get(token);
			getCorpus().put(token, times + 1);
		} else {
			getCorpus().put(token, 1);
		}
	}
	
	/**
	 * Scan messages.
	 */
	private void scanMessages() {
		String token = getScanner().nextToken();
		while (token != null) {
			addTokenToCorpus(token);
			token = scanner.nextToken();
		}
	}
}
