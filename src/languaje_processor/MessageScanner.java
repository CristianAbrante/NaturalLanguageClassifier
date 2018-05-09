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
	
	/** The number of messages. */
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
	
	/**
	 * Reset parser.
	 */
	public void resetParser() {
		matcher = messagePattern.matcher(messageList);
	}
	
	/**
	 *
	 *
	 * @return the message pattern buffer
	 */
	public String getMessagePatternBuffer() {
		return messagePatternBuffer;
	}

	/**
	 * Sets the message pattern buffer.
	 *
	 * @param messagePatternBuffer the new message pattern buffer
	 */
	public void setMessagePatternBuffer(String messagePatternBuffer) {
		this.messagePatternBuffer = messagePatternBuffer;
	}

	/**
	 * Gets the message pattern.
	 *
	 * @return the message pattern
	 */
	public Pattern getMessagePattern() {
		return messagePattern;
	}

	/**
	 * Sets the message pattern.
	 *
	 * @param messagePattern the new message pattern
	 */
	public void setMessagePattern(Pattern messagePattern) {
		this.messagePattern = messagePattern;
	}

	/**
	 * Gets the matcher.
	 *
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * Sets the matcher.
	 *
	 * @param matcher the new matcher
	 */
	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	/**
	 * Gets the number of messages.
	 *
	 * @return the number of messages
	 */
	public Integer getNumberOfMessages() {
		return numberOfMessages;
	}

	/**
	 * Sets the number of messages.
	 *
	 * @param numberOfMessages the new number of messages
	 */
	public void setNumberOfMessages(Integer numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	/**
	 * Gets the message list.
	 *
	 * @return the message list
	 */
	public String getMessageList() {
		return messageList;
	}
	
	/**
	 * Sets the message list.
	 *
	 * @param messageList the new message list
	 */
	public void setMessageList(String messageList) {
		this.messageList = messageList;
	}
}
