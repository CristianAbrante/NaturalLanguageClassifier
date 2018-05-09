package languaje_processor.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import languaje_processor.classifier.LanguageClassifier;
import languaje_processor.corpus.Corpus;
import languaje_processor.parser.DocumentReader;
import languaje_processor.vocabulary.Vocabulary;

public class TestMain {
	public static void main(String[] args) throws IOException {
			DocumentReader parserVocabulary = new DocumentReader(new FileReader("test/corpusTotal.txt"));
			FileWriter outputVocabulary = new FileWriter("test/voc.txt");
			DocumentReader parserCorpusA = new DocumentReader(new FileReader("test/corpusE.txt"));
			FileWriter outputCorpusA = new FileWriter("test/apreE.txt");
			DocumentReader parserCorpusD = new DocumentReader(new FileReader("test/corpusI.txt"));
			FileWriter outputCorpusD = new FileWriter("test/apreI.txt");
			

			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
			vocabulary.export(outputVocabulary);

			Corpus corpusA = new Corpus("E", vocabulary, parserCorpusA);
			corpusA.export(outputCorpusA);
			Corpus corpusD = new Corpus("I", vocabulary, parserCorpusD);
			corpusD.export(outputCorpusD);
			
			ArrayList<Corpus> corpuses = new ArrayList<>(Arrays.asList(corpusA, corpusD));
			
			LanguageClassifier classifier = new LanguageClassifier(corpuses);
			System.out.println(classifier.getMaxProbabilityCorpusName("good actor"));
	}

}
