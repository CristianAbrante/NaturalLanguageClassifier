package languaje_processor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class LanguageCorpus {
	TreeMap<String, Integer> corpus = new TreeMap<String, Integer>();
	MessageScanner scanner;
	
	public LanguageCorpus(MessageScanner scanner) {
		setScanner(scanner);
		scanMessages();
	}
	
	public TreeMap<String, Integer> getCorpus() {
		return corpus;
	}
	
	public int getNumberOfWords() {
		return getCorpus().size();
	}
	
	public boolean tokenInCorpus(String token) {
		if (token != null) {
			return getCorpus().containsKey(token);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}
	
	public int getNumberOfTimes(String token) {
		return getCorpus().get(token);
	}
	
	public MessageScanner getScanner() {
		return scanner;
	}
	
	public void exportCorpusToFile(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.print(String.format("Number of words: %d", getNumberOfWords()));
		 
	    Iterator<Entry<String, Integer>> it = getCorpus().entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry token = (Map.Entry) it.next();
	    	writer.println(((String) token.getKey()));
	    }
	    writer.close();
	}
	
	private void setScanner(MessageScanner scanner) {
		this.scanner = scanner;
	}
	
	private void addTokenToCorpus(String token) {
		if (tokenInCorpus(token)) {
			int times = getCorpus().get(token);
			getCorpus().put(token, times + 1);
		} else {
			getCorpus().put(token, 1);
		}
	}
	
	private void scanMessages() {
		String token = getScanner().nextToken();
		while (token != null) {
			addTokenToCorpus(token);
			token = scanner.nextToken();
		}
	}
}
