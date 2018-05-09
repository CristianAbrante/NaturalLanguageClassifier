package languaje_processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import languaje_processor.classifier.LanguageClassifier;
import languaje_processor.corpus.Corpus;
import languaje_processor.parser.DocumentReader;
import languaje_processor.vocabulary.Vocabulary;

/**
 * The Class ClassifierMain.
 */
public class ClassifierMain {
	
	/*
	 * Params:
	 * data/corpusTotal.txt
	 * data/corpusA.txt data/corpusD.txt data/corpusI.txt
	 *  FILE TO CLASSIFY OUTPUT CLASSIFY
	 */
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args.length == 6) {
			DocumentReader parserVocabulary = new DocumentReader(new FileReader(args[0]));
			DocumentReader parserCorpusA = new DocumentReader(new FileReader(args[1]));
			DocumentReader parserCorpusD = new DocumentReader(new FileReader(args[2]));
			DocumentReader parserCorpusI = new DocumentReader(new FileReader(args[3]));
			

			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
			
			
			Corpus corpusA = new Corpus("A", vocabulary, parserCorpusA);
			Corpus corpusD = new Corpus("D", vocabulary, parserCorpusD);
			Corpus corpusI = new Corpus("I", vocabulary, parserCorpusI);
			
			ArrayList<Corpus> corpuses = new ArrayList<Corpus>(Arrays.asList(corpusA, corpusD, corpusI));
			
			LanguageClassifier classifier = new LanguageClassifier(corpuses);
			
			FileReader inputClassification = new FileReader(args[4]);
			FileWriter outputClassification = new FileWriter(args[5]);
			PrintWriter writer = new PrintWriter(outputClassification);
			
			BufferedReader reader = new BufferedReader(inputClassification);
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(classifier.getMaxProbabilityCorpusName(line));
			}
			writer.close();
			reader.close();
		} else {
			String error = "Must specify the following files: \n" + " - inputVolcabulary.txt";
			System.err.println(error);
		}
	}
}
