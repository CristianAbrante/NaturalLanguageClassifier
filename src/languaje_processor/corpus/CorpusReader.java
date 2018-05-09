package languaje_processor.corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import languaje_processor.token.Token;

public class CorpusReader {

	private Integer numberOfDocuments;
	private Integer numberOfWords;
	private BufferedReader reader;
	
	
	public CorpusReader(FileReader fileName) throws NumberFormatException, IOException{
		setReader(new BufferedReader(fileName));
		initialize();
	}
	
	private void initialize() throws NumberFormatException, IOException{
		setNumberOfDocuments(Integer.parseInt(getReader().readLine()));
		setNumberOfWords(Integer.parseInt(getReader().readLine()));
	}

	public Token nextToken() throws IOException{
		String line = getReader().readLine();
		if (line != null){
			String[] tokens = line.split("\\s+");
			return new Token(tokens[0], Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2].replace(',','.')));
		}else{
			return null;
		}
	}

	public Integer getNumberOfDocuments() {
		return numberOfDocuments;
	}


	public void setNumberOfDocuments(Integer numberOfDocuments) {
		this.numberOfDocuments = numberOfDocuments;
	}


	public Integer getNumberOfWords() {
		return numberOfWords;
	}


	public void setNumberOfWords(Integer numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
