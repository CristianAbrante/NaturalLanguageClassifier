package languaje_processor.vocabulary;

import java.io.FileReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

/**
 * The Class VocabularyReader.
 */
public class VocabularyReader {
	
	/** The vocabulary scanner. */
	Scanner vocabularyScanner;
	
	/** The number of words. */
	int numberOfWords;

	/**
	 * Instantiates a new vocabulary reader.
	 *
	 * @param file the file
	 * @throws IllegalClassFormatException the illegal class format exception
	 */
	public VocabularyReader(FileReader file) throws IllegalClassFormatException {
		vocabularyScanner = new Scanner(file);
		if (vocabularyScanner.hasNextInt()) {
			numberOfWords = vocabularyScanner.nextInt();
		} else {
			throw new IllegalClassFormatException("vocabulary files should have the number of words at the begining.");
		}
	}

	/**
	 * Next token.
	 *
	 * @return the string
	 */
	public String nextToken() {
		if (vocabularyScanner.hasNext()) {
			return vocabularyScanner.next();
		} else {
			return null;
		}
	}

	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public int getNumberOfWords() {
		return numberOfWords;
	}
}
