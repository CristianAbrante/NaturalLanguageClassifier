package languaje_processor.parser;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import languaje_processor.token.Token;
import languaje_processor.token.TokenType;

/**
 * <h2>CorpusReader</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	7 may. 2018
 * @version 	1.0.0
 */
public class DocumentReader {
  
  private int numberOfDocuments;
  
  private String corpus;
  
  /** The message pattern buffer. */
  private String tokensPatternBuffer;
  
  /** The message pattern. */
  private Pattern tokensPattern;
  
  /** The matcher. */
  private Matcher matcher;
  
  private DocumentReader() {
    initializeMessagePatternBuffer();
    tokensPattern = Pattern.compile(tokensPatternBuffer);
  }
  
  public DocumentReader(String corpus) {
    this();
    setCorpus(corpus);
  }
  
  public DocumentReader(FileReader file) {
    this(new Scanner(file).useDelimiter("\\Z").next());
  }

  /**
   * @return the numberOfDocuments
   */
  public int getNumberOfDocuments() {
    if (getCorpus() != null) {
      return numberOfDocuments;      
    } else {
      throw new IllegalAccessError("trying to access number of documents with no given corpus.");
    }
  }

  /**
   * @return the corpus
   */
  public String getCorpus() {
    return corpus;
  }

  /**
   * @param corpus the corpus to set
   */
  public void setCorpus(String corpus) {
    if (corpus != null) {
      this.corpus = corpus;
      initializeNumberOfDocuments();
      initializeMatcher();
    } else {
      throw new IllegalArgumentException("Corpus can't be null.");
    }
  }
  
  public Token nextToken() {
    if (getCorpus() != null) {
      if (matcher.find()) {
        if (matcher.group(TokenType.WORD.name()) != null) {
          return new Token(matcher.group(TokenType.WORD.name()).toLowerCase());
        } else if (matcher.group(TokenType.URL.name()) != null) {
          return new Token(TokenType.URL.getValue());
        } else if (matcher.group(TokenType.HASHTAG.name()) != null) {
          return new Token(TokenType.HASHTAG.getValue());
        } else if (matcher.group(TokenType.NUMBER.name()) != null) {
          return new Token(TokenType.NUMBER.getValue());
        } else if (matcher.group(TokenType.MENTION.name()) != null) {
          return new Token(TokenType.MENTION.getValue());
        } else {
          return null;
        }
      } else {
        return null;
      }
    } else {
      throw new IllegalAccessError("trying to access next token with no given corpus.");
    }
  }
  
  // PRIVATE METHODS
  
  private void initializeMessagePatternBuffer() {
    tokensPatternBuffer = new String();
    for (TokenType token : TokenType.values()) {
      if (!token.equals(TokenType.UNKNOWN)) {
        tokensPatternBuffer += String.format("|(?<%s>%s)", token.name(), token.getPattern());        
      }
    }
    // this deletes the first or at regex.
    tokensPatternBuffer = tokensPatternBuffer.substring(1);
  }
  
  private void initializeNumberOfDocuments() {
    numberOfDocuments = getCorpus().split("\\n").length;
  }
  
  private void initializeMatcher() {
    matcher = tokensPattern.matcher(getCorpus());
  }
}
