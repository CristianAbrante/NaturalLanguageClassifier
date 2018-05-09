package languaje_processor.vocabulary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import languaje_processor.parser.DocumentReader;
import languaje_processor.token.TokenType;

/**
 * The Class Vocabulary.
 */
public class Vocabulary {

	/** The unknown token counter. */
	private final int UNKNOWN_TOKEN_COUNTER = 1;

	/** The words. */
	private Set<String> words;

	/**
	 * Instantiates a new vocabulary.
	 *
	 * @param reader the reader
	 */
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

	/**
	 * Instantiates a new vocabulary.
	 *
	 * @param reader the reader
	 */
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

	/**
	 * Gets the words.
	 *
	 * @return the words
	 */
	public Set<String> getWords() {
		return words;
	}

	/**
	 * Adds the to vocabylary.
	 *
	 * @param token the token
	 */
	public void addToVocabylary(String token) {
		if (token != null) {
			getWords().add(token);
		} else {
			throw new NullPointerException("token can't be null");
		}
	}

	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public int getNumberOfWords() {
		return getWords().size() + UNKNOWN_TOKEN_COUNTER;
	}

	/**
	 * Export to file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(FileWriter file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.println(String.format("%d", getNumberOfWords()));
		writer.println(TokenType.UNKNOWN.getValue());
		for (String token : getWords()) {
			writer.println(token);
		}
		writer.close();
	}

	/**
	 * Contains a word.
	 *
	 * @param word the word
	 * @return true, if successful
	 */
	public boolean contains(String word) {
		Iterator<String> it = getWords().iterator();
		String token = new String();
		while (it.hasNext()) {
			token = it.next();
			if (word.equals(token)) {
				return true;
			}
		}
		return false;
	}
}
