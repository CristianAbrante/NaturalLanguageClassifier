package languaje_processor;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		//if (args.length == 2) {
			MessageScanner parserVocabulary = new MessageScanner("data/corpusTotal.txt");
			Vocabulary vocabulary = new Vocabulary(parserVocabulary);
			vocabulary.export(new FileWriter("vocabulary.txt")); 
			
			MessageScanner parserCorpus= new MessageScanner("data/corpusA.txt");
			Corpus corpusAction = new Corpus("action", vocabulary, parserCorpus);
			corpusAction.export(new FileWriter("data/corpusA.out"));
			//FileWriter file = new FileWriter(args[1]);
			//corpus.exportCorpusToFile(file);	// Hay que decidir como hacemos lo del fichero pues se exporta a ficheros tal que aprendizaje<...>.txt, donde los ... son el tipo de mensaje
//		} else {
//			System.err.println("You must specifie input file and output file");
//		}
	}

}
