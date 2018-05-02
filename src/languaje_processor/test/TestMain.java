package languaje_processor.test;

import java.io.FileWriter;
import java.io.IOException;

import languaje_processor.Corpus;
import languaje_processor.MessageScanner;
import languaje_processor.Vocabulary;

public class TestMain {
	public static void main(String[] args) throws IOException {
			MessageScanner parserVocabulary = new MessageScanner("test/corpusTotal.txt");
			FileWriter outputVocabulary = new FileWriter("test/voc.txt");
			MessageScanner parserCorpusA = new MessageScanner("test/corpusE.txt");
			FileWriter outputCorpusA = new FileWriter("test/apreE.txt");
			MessageScanner parserCorpusD = new MessageScanner("test/corpusI.txt");
			FileWriter outputCorpusD = new FileWriter("test/apreI.txt");
			

			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
			vocabulary.export(outputVocabulary);

			Corpus corpusA = new Corpus("action", vocabulary, parserCorpusA);
			corpusA.export(outputCorpusA);
			Corpus corpusD = new Corpus("dialog", vocabulary, parserCorpusD);
			corpusD.export(outputCorpusD);
	}

}
