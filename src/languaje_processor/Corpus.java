package languaje_processor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Corpus {

	private Map<String, Integer> corpus;

	private MessageScanner parser;

	private Vocabulary vocabulary;

	public Corpus(MessageScanner parser, Vocabulary vocabulary) {
		this.corpus = new TreeMap<String, Integer>();
		setScanner(parser);
		setVocabulary(vocabulary);
		parser.resetParser();
		scanMessagesToCorpus();
	}

	private void scanMessagesToCorpus() {
		String token = getScanner().nextToken();
		while (token != null) {
			addTokenToCorpus(token);
			token = parser.nextToken();
		}
		// Check the tree and remove tokens with low appearances
		setNumberOfUnknownTokens();
	}

	private void addTokenToCorpus(String token) {
		if (tokenInCorpus(token)) {
			int times = getCorpus().get(token);
			getCorpus().put(token, times + 1);
		} else {
			getCorpus().put(token, 1);
		}
	}

	public boolean tokenInCorpus(String token) {
		if (token != null) {
			return getCorpus().containsKey(token);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	private void setNumberOfUnknownTokens() {
		Map<String, Integer> tempMap = new TreeMap<String, Integer>();
		Iterator<Entry<String, Integer>> it = getCorpus().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			Integer value = entry.getValue();
			if (value < 3) {
				String unknown = "unknown";
				if (tempMap.containsKey(unknown)) {
					int times = tempMap.get(unknown);
					tempMap.put(unknown, times + 1);
				} else {
					tempMap.put(unknown, 1);
				}
				it.remove();
			}
		}
		getCorpus().putAll(tempMap);
	}

	public void exportCorpusToFile() throws IOException {
		// TODO
	}

	public Double getLogProb() {
		// TODO
		return null;
	}

	public Integer getFrecuency(String token) {
		return getCorpus().get(token);
	}

	public Integer getNumberOfMessages() {
		return getScanner().getNumberOfMessages();
	}

	public Integer getNumberOfWords() {
		return getCorpus().size();
	}

	/** Getters and Setters **/

	public Map<String, Integer> getCorpus() {
		return corpus;
	}

	public void setCorpus(TreeMap<String, Integer> corpus) {
		this.corpus = corpus;
	}

	public MessageScanner getScanner() {
		return parser;
	}

	public void setScanner(MessageScanner parser) {
		this.parser = parser;
	}

	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}
}
