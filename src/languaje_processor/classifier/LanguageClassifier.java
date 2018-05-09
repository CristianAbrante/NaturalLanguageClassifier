package languaje_processor.classifier;

import java.util.ArrayList;

import languaje_processor.corpus.Corpus;
import languaje_processor.parser.DocumentReader;

/**
 * The Class LanguageClassifier.
 */
public class LanguageClassifier {
	
	/** The corpuses. */
	ArrayList<Corpus> corpuses;
	
	/** The reader. */
	DocumentReader reader;
	
	/**
	 * Instantiates a new language classifier.
	 *
	 * @param corpuses the corpuses
	 */
	public LanguageClassifier(ArrayList<Corpus> corpuses) {
		setCorpuses(corpuses);
		setReader(new DocumentReader());
	}
	
	/**
	 * Gets the corpuses.
	 *
	 * @return the corpuses
	 */
	public ArrayList<Corpus> getCorpuses() {
		return corpuses;
	}
	
	/**
	 * Sets the corpuses.
	 *
	 * @param corpus the new corpuses
	 */
	public void setCorpuses(ArrayList<Corpus> corpus) {
		if (corpus != null && !corpus.isEmpty()) {
			this.corpuses = corpus;
		} else {
			throw new NullPointerException("corpuses can't be null.");
		}
	}
	
	/**
	 * Gets the max probability corpus name.
	 *
	 * @param tokens the tokens
	 * @return the max probability corpus name
	 */
	public String getMaxProbabilityCorpusName(String tokens) {
		if (tokens.isEmpty()) {
			return null;
		}
		getReader().setCorpus(tokens);
		double[] probabilities = new double[getNumberOfCorpuses()];

		String token = getReader().nextToken();
		
		while (token != null) {
			for (int i = 0; i < getNumberOfCorpuses(); ++i) {
				probabilities[i] += getCorpuses().get(i).getLogProb(token);
			}
			token = getReader().nextToken();
		}
		
		for (int i = 0; i < getNumberOfCorpuses(); ++i) {
			probabilities[i] += getCorpusLogProb(getCorpuses().get(i));
		}
		
		int max = 0;
		for (int i = 0; i < probabilities.length; ++i) {
			if (Double.compare(probabilities[max], probabilities[i]) < 0) {
				max = i;
			}
		}
		return getCorpuses().get(max).getName();
	}
	
	/**
	 * Gets the number of corpuses.
	 *
	 * @return the number of corpuses
	 */
	public int getNumberOfCorpuses() {
		return getCorpuses().size();
	}
	
	/**
	 * Gets the corpus log prob.
	 *
	 * @param corpus the corpus
	 * @return the corpus log prob
	 */
	private double getCorpusLogProb(Corpus corpus) {
		int totalDocuments = 0;
		for (Corpus currentCorpus : getCorpuses()) {
			totalDocuments += currentCorpus.getNumberOfDocuments();
		}
		return Math.log((double) corpus.getNumberOfDocuments() / (double) totalDocuments);
	}

	/**
	 * Gets the reader.
	 *
	 * @return the reader
	 */
	private DocumentReader getReader() {
		return reader;
	}

	/**
	 * Sets the reader.
	 *
	 * @param reader the new reader
	 */
	private void setReader(DocumentReader reader) {
		if (reader != null) {
			this.reader = reader;			
		} else {
			throw new NullPointerException("reader can't be null.");
		}
	}
}
