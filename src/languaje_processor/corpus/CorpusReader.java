package languaje_processor.corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import languaje_processor.token.Token;

/**
 * The Class CorpusReader.
 */
public class CorpusReader {

	/** The number of documents. */
	private Integer numberOfDocuments;
	
	/** The number of words. */
	private Integer numberOfWords;
	
	/** The reader. */
	private BufferedReader reader;
	
	
	/**
	 * Instantiates a new corpus reader.
	 *
	 * @param fileName the file name
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CorpusReader(FileReader fileName) throws NumberFormatException, IOException{
		setReader(new BufferedReader(fileName));
		initialize();
	}
	
	/**
	 * Initialize.
	 *
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void initialize() throws NumberFormatException, IOException{
		setNumberOfDocuments(Integer.parseInt(getReader().readLine()));
		setNumberOfWords(Integer.parseInt(getReader().readLine()));
	}

	/**
	 * Next token.
	 *
	 * @return the token
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Token nextToken() throws IOException{
		String line = getReader().readLine();
		if (line != null){
			String[] tokens = line.split("\\s+");
			return new Token(tokens[0], Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2].replace(',','.')));
		}else{
			return null;
		}
	}

	/**
	 * Gets the number of documents.
	 *
	 * @return the number of documents
	 */
	public Integer getNumberOfDocuments() {
		return numberOfDocuments;
	}


	/**
	 * Sets the number of documents.
	 *
	 * @param numberOfDocuments the new number of documents
	 */
	public void setNumberOfDocuments(Integer numberOfDocuments) {
		this.numberOfDocuments = numberOfDocuments;
	}


	/**
	 * Gets the number of words.
	 *
	 * @return the number of words
	 */
	public Integer getNumberOfWords() {
		return numberOfWords;
	}


	/**
	 * Sets the number of words.
	 *
	 * @param numberOfWords the new number of words
	 */
	public void setNumberOfWords(Integer numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	/**
	 * Gets the reader.
	 *
	 * @return the reader
	 */
	public BufferedReader getReader() {
		return reader;
	}

	/**
	 * Sets the reader.
	 *
	 * @param reader the new reader
	 */
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
