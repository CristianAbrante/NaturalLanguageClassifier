package languaje_processor;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import languaje_processor.token.TokenType;

/**
 * The Class MessageScanner.
 */
public class MessageScanner {
	
	/** The message list. */
	private String  messageList;
	
	/** The message pattern buffer. */
	private String messagePatternBuffer;
	
	/** The message pattern. */
	private Pattern messagePattern;
	
	/** The matcher. */
	private Matcher matcher;
	
	private Integer numberOfMessages;
	/**
	 * Instantiates a new message scanner.
	 *
	 * @param filePath the file path
	 * @throws FileNotFoundException the file not found exception
	 */
	@SuppressWarnings("resource")
	public MessageScanner(String filePath) throws FileNotFoundException {
		messageList = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
		setNumberOfMessages(messageList.split("\\n").length);
		initializeMessagePatternBuffer();
		messagePattern = Pattern.compile(messagePatternBuffer);
		matcher = messagePattern.matcher(messageList);
	}
	
	/**
	 * Next token.
	 *
	 * @return the string
	 */
	public String nextToken() {
		if (matcher.find()) {
			if (Character.isUpperCase(matcher.group().charAt(0))) { // Si empieza por mayus
				String input = matcher.group();
				return input.substring(0, 1).toLowerCase() + input.substring(1);
			}else {
				return matcher.group();
			}
		} else {
			return null;
		}		
	}
	
	/**
	 * Initialize message pattern buffer.
	 */
	private void initializeMessagePatternBuffer() {
		messagePatternBuffer = new String();
		for (int i = 0; i < TokenType.values().length; i++) {
			if (i == TokenType.values().length - 1) {
				messagePatternBuffer += TokenType.values()[i].getPattern();
			}else {
				messagePatternBuffer += TokenType.values()[i].getPattern() + "|";
			}
		}
	}
	
	public void resetParser() {
		matcher = messagePattern.matcher(messageList);
	}
	
	/** Getters and Setters **/

	public String getMessagePatternBuffer() {
		return messagePatternBuffer;
	}

	public void setMessagePatternBuffer(String messagePatternBuffer) {
		this.messagePatternBuffer = messagePatternBuffer;
	}

	public Pattern getMessagePattern() {
		return messagePattern;
	}

	public void setMessagePattern(Pattern messagePattern) {
		this.messagePattern = messagePattern;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	public Integer getNumberOfMessages() {
		return numberOfMessages;
	}

	public void setNumberOfMessages(Integer numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	public String getMessageList() {
		return messageList;
	}
	
	public void setMessageList(String messageList) {
		this.messageList = messageList;
	}
}
