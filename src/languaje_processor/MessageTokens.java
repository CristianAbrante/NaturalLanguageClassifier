package languaje_processor;

public enum MessageTokens {
	URL("(https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-z]{2,6}\\b[-a-zA-Z0-9@:%_\\+.~#?&//=]*)"),
	WORD("([@#]?[\\w-_]+(?:'(?:m|s|re|ve|ll|d|t))?)");
	
	private String pattern;
	
	private MessageTokens(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
