package languaje_processor;

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
		if (args.length == 2) {
			MessageScanner parser = new MessageScanner(args[0]);
			Vocabulary vocabulary = new Vocabulary(parser);
			vocabulary.exportVocabularyToFile(); // Este se exporta por defecto a vocabulario.txt
			
			Corpus corpus = new Corpus(parser, vocabulary);
			//FileWriter file = new FileWriter(args[1]);
			//corpus.exportCorpusToFile(file);	// Hay que decidir como hacemos lo del fichero pues se exporta a ficheros tal que aprendizaje<...>.txt, donde los ... son el tipo de mensaje
		} else {
			System.err.println("You must specifie input file and output file");
		}
	}

}
