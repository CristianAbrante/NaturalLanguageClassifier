/**
 * @author cristian
 * @date 8 may. 2018
 */
package languaje_processor.vocabulary;

import java.io.FileReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

import languaje_processor.token.Token;

/**
 * <h2>VocabularyReader</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	8 may. 2018
 * @version 	1.0.0
 */
public class VocabularyReader {
  Scanner vocabularyScanner;
  int numberOfWords;
  
  public VocabularyReader(FileReader file) throws IllegalClassFormatException {
    vocabularyScanner = new Scanner(file);
    if (vocabularyScanner.hasNextInt()) {
      numberOfWords = vocabularyScanner.nextInt();
    } else {
      throw new IllegalClassFormatException("vocabulary files should have the number of words at the begining.");
    }
  }
  
  public Token nextToken() {
    if (vocabularyScanner.hasNext()) {
      return new Token(vocabularyScanner.next());
    } else {
      return null;
    }
  }
  
  public int getNumberOfWords() {
    return numberOfWords;
  }
}
