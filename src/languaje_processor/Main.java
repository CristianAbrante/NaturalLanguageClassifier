package languaje_processor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import languaje_processor.corpus.Corpus;
import languaje_processor.parser.DocumentReader;
import languaje_processor.vocabulary.Vocabulary;
import languaje_processor.token.Token;

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
//	  DocumentReader reader = new DocumentReader(new FileReader("data/corpusTotal.txt"));
//	  System.out.println(reader.getNumberOfDocuments());
	  
	  
	  DocumentReader reader = new DocumentReader(new FileReader("data/corpusTotal.txt"));
	  Vocabulary voc = new Vocabulary(reader);
	  
	  DocumentReader c1 = new DocumentReader(new FileReader("data/corpusA.txt"));
	  
	  voc.export(new FileWriter("voc.out"));
	  Corpus c = new Corpus("a", voc, c1);
	  c.export(new FileWriter("adios.txt"));
//		if (args.length == 8) {
//			MessageScanner parserVocabulary = new MessageScanner(args[0]);
//			FileWriter outputVocabulary = new FileWriter(args[1]);
//			MessageScanner parserCorpusA = new MessageScanner(args[2]);
//			FileWriter outputCorpusA = new FileWriter(args[3]);
//			MessageScanner parserCorpusD = new MessageScanner(args[4]);
//			FileWriter outputCorpusD = new FileWriter(args[5]);
//			MessageScanner parserCorpusI = new MessageScanner(args[6]);
//			FileWriter outputCorpusI = new FileWriter(args[7]);
//
//			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
//			vocabulary.export(outputVocabulary);
//
//			Corpus corpusA = new Corpus("action", vocabulary, parserCorpusA);
//			corpusA.export(outputCorpusA);
//			Corpus corpusD = new Corpus("dialog", vocabulary, parserCorpusD);
//			corpusD.export(outputCorpusD);
//			Corpus corpusI = new Corpus("information", vocabulary, parserCorpusI);
//			corpusI.export(outputCorpusI);
//		} else {
//			String error = "Must specify the following files: \n" + " - inputVolcabulary.txt";
//			System.err.println(error);
//		}
	}

}
