package languaje_processor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class Corpus {

	private TreeMap<String, Integer> corpus;
	private MessageScanner scanner;
	private Vocabulary vocabulary;
	private String name;
	private int numberOfWords = 0;
	
	public Corpus(String name, Vocabulary vocabulary, MessageScanner parser) {
		setCorpus(new TreeMap<String, Integer>());
		setScanner(parser);
		setVocabulary(vocabulary);
		parser.resetParser();
		addTokensToCorpus();
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

	private void addToCorpus(String token) {
		if (this.contains(token)) {
			int times = getCorpus().get(token);
			getCorpus().put(token, times + 1);
		} else {
			getCorpus().put(token, 1);
		}
		numberOfWords += 1;
	}

	public boolean contains(String word) {
		if (word != null) {
			return getCorpus().containsKey(word);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("Número de documentos del corpus: %d", getNumberOfMessages()));
		writer.println(String.format("Número de palabras del corpus: %d", getNumberOfWords()));
		for (Map.Entry<String, Integer> entry : getCorpus().entrySet()) {
			String word = entry.getKey();
			writer.println(
					String.format("Palabra:%s Frec:%d LogProb:%.8f", word, getFrecuency(word), getLogProb(word)));
		}
		writer.close();
	}

	public Double getLogProb(String word) {
		if (word != null) {
			return Math.log(((double) (getFrecuency(word) + 1))
					/ ((double) (getNumberOfWords() + getVocabulary().getNumberOfWords())));
		} else {
			throw new NullPointerException("word can't be null");
		}
	}

	public Integer getFrecuency(String word) {
		if (word != null) {
			return contains(word) ? getCorpus().get(word) : 0;
		} else {
			throw new NullPointerException("word can't be null");
		}
	}

	public Integer getNumberOfMessages() {
		return getScanner().getNumberOfMessages();
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}

	/** Getters and Setters **/

	private TreeMap<String, Integer> getCorpus() {
		return corpus;
	}

	private void setCorpus(TreeMap<String, Integer> corpus) {
		if (corpus != null) {
			this.corpus = corpus;
		} else {
			throw new NullPointerException("corpus can't be null");
		}
	}

	private MessageScanner getScanner() {
		return scanner;
	}

	private void setScanner(MessageScanner parser) {
		if (parser != null) {
			this.scanner = parser;
		} else {
			throw new NullPointerException("parser can't be null");
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

	private void addTokensToCorpus() {
		String token = getScanner().nextToken();
		while (token != null) {
			addToCorpus(token);
			token = getScanner().nextToken();
		}
	}
}
