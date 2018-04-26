package languaje_processor;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	 * Instantiates a new message scanner.
	 *
	 * @param filePath the file path
	 * @throws FileNotFoundException the file not found exception
	 */
	@SuppressWarnings("resource")
	public MessageScanner(String filePath) throws FileNotFoundException {
		messageList = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
		initializeMessagePatternBuffer();
		messagePattern = Pattern.compile(messagePatternBuffer);
		matcher = messagePattern.matcher(messageList);
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
	 * Next token.
	 *
	 * @return the string
	 */
	public String nextToken() {
		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return null;
		}		
	}
	
	/**
	 * Initialize message pattern buffer.
	 */
	private void initializeMessagePatternBuffer() {
		messagePatternBuffer = "";
		for (MessageTokens token : MessageTokens.values()) {
			messagePatternBuffer += token.getPattern() + "|";
		}
	}
}
