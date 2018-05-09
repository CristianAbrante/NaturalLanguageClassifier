package languaje_processor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import languaje_processor.corpus.Corpus;
import languaje_processor.parser.DocumentReader;
import languaje_processor.vocabulary.Vocabulary;

/**
 * The Class Main.
 */
public class Main {
	/*
	 * Params:
	 * data/corpusTotal.txt data/vocabulary.txt data/corpusA.txt
	 * data/aprendizajeA.txt data/corpusD.txt data/aprendizajeD.txt
	 * data/corpusI.txt data/aprendizajeI.txt
	 */

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 8) {
			DocumentReader parserVocabulary = new DocumentReader(new FileReader(args[0]));
			FileWriter outputVocabulary = new FileWriter(args[1]);
			DocumentReader parserCorpusA = new DocumentReader(new FileReader(args[2]));
			FileWriter outputCorpusA = new FileWriter(args[3]);
			DocumentReader parserCorpusD = new DocumentReader(new FileReader(args[4]));
			FileWriter outputCorpusD = new FileWriter(args[5]);
			DocumentReader parserCorpusI = new DocumentReader(new FileReader(args[6]));
			FileWriter outputCorpusI = new FileWriter(args[7]);

			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
			vocabulary.export(outputVocabulary);

			Corpus corpusA = new Corpus("action", vocabulary, parserCorpusA);
			corpusA.export(outputCorpusA);
			Corpus corpusD = new Corpus("dialog", vocabulary, parserCorpusD);
			corpusD.export(outputCorpusD);
			Corpus corpusI = new Corpus("information", vocabulary, parserCorpusI);
			corpusI.export(outputCorpusI);
		} else {
			String error = "Must specify the following files: \n" + " - inputVolcabulary.txt";
			System.err.println(error);
		}
	}
}
