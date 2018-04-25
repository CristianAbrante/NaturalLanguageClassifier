package languaje_processor;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageScanner {
	private String  messageList;
	private String messagePatternBuffer;
	private Pattern messagePattern;
	Matcher matcher;
	
	public MessageScanner(String filePath) throws FileNotFoundException {
		messageList = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
		initializeMessagePatternBuffer();
		messagePattern = Pattern.compile(messagePatternBuffer);
		matcher = messagePattern.matcher(messageList);
	}
	
	public String getMessageList() {
		return messageList;
	}
	
	public String nextToken() {
		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return null;
		}		
	}
	
	private void initializeMessagePatternBuffer() {
		messagePatternBuffer = "";
		for (MessageTokens token : MessageTokens.values()) {
			messagePatternBuffer += token.getPattern() + "|";
		}
	}
}
