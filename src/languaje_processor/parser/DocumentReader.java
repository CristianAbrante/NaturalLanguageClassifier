package languaje_processor.parser;

import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import languaje_processor.token.TokenType;
import languaje_processor.utility.Constants;

/**
 * The Class DocumentReader.
 */
public class DocumentReader {

	/** The number of documents. */
	private int numberOfDocuments;

	/** The corpus. */
	private String corpus;

	/** The tokens pattern buffer. */
	private String tokensPatternBuffer;

	/** The tokens pattern. */
	private Pattern tokensPattern;

	/** The matcher. */
	private Matcher matcher;

	/**
	 * Instantiates a new document reader.
	 */
	public DocumentReader() {
		initializeMessagePatternBuffer();
		tokensPattern = Pattern.compile(tokensPatternBuffer);
	}

	/**
	 * Instantiates a new document reader.
	 *
	 * @param corpus the corpus
	 */
	public DocumentReader(String corpus) {
		this();
		setCorpus(corpus);
	}

	/**
	 * Instantiates a new document reader.
	 *
	 * @param file the file
	 */
	@SuppressWarnings("resource")
	public DocumentReader(FileReader file) {
		this(new Scanner(file).useDelimiter("\\Z").next());
	}

	/**
	 * Gets the number of documents.
	 *
	 * @return the number of documents
	 */
	public int getNumberOfDocuments() {
		if (getCorpus() != null) {
			return numberOfDocuments;
		} else {
			throw new IllegalAccessError("trying to access number of documents with no given corpus.");
		}
	}

	/**
	 * Gets the corpus.
	 *
	 * @return the corpus
	 */
	public String getCorpus() {
		return corpus;
	}

	/**
	 * Sets the corpus.
	 *
	 * @param corpus the new corpus
	 */
	public void setCorpus(String corpus) {
		if (corpus != null) {
			this.corpus = corpus;
			initializeNumberOfDocuments();
			initializeMatcher();
		} else {
			throw new IllegalArgumentException("Corpus can't be null.");
		}
	}

	/**
	 * Next token.
	 *
	 * @return the string
	 */
	public String nextToken() {
		if (getCorpus() != null) {
			if (matcher.find()) {
				if (matcher.group(TokenType.WORD.name()) != null) {
					//if(!Constants.WORDS.contains(matcher.group(TokenType.WORD.name()).toLowerCase())) 
						return matcher.group(TokenType.WORD.name()).toLowerCase();
					//else
						//return null;
				} else if (matcher.group(TokenType.URL.name()) != null) {
					return TokenType.URL.getValue();
				} else if (matcher.group(TokenType.HASHTAG.name()) != null) {
					//return TokenType.HASHTAG.getValue();
				  return matcher.group(TokenType.HASHTAG.name()).toLowerCase();
				} else if (matcher.group(TokenType.NUMBER.name()) != null) {
					return TokenType.NUMBER.getValue();
				} else if (matcher.group(TokenType.MENTION.name()) != null) {
					//return TokenType.MENTION.getValue();
				  return matcher.group(TokenType.MENTION.name()).toLowerCase();
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			throw new IllegalAccessError("trying to access next token with no given corpus.");
		}
	}

	// PRIVATE METHODS

	/**
	 * Initialize message pattern buffer.
	 */
	private void initializeMessagePatternBuffer() {
		tokensPatternBuffer = new String();
		for (TokenType token : TokenType.values()) {
			if (!token.equals(TokenType.UNKNOWN)) {
				tokensPatternBuffer += String.format("|(?<%s>%s)", token.name(), token.getPattern());
			}
		}
		// this deletes the first or at regex.
		tokensPatternBuffer = tokensPatternBuffer.substring(1);
	}

	/**
	 * Initialize number of documents.
	 */
	private void initializeNumberOfDocuments() {
		numberOfDocuments = getCorpus().split("\\n").length;
	}

	/**
	 * Initialize matcher.
	 */
	private void initializeMatcher() {
		matcher = tokensPattern.matcher(getCorpus());
	}
}
