package languaje_processor;

/**
 * The Enum MessageTokens.
 */
public enum MessageTokens {
	
	/** The url regexp. */
	URL("(https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-z]{2,6}\\b[-a-zA-Z0-9@:%_\\+.~#?&//=]*)"),
	
	/** The word regexp. */
	WORD("([@#]?[\\w-_]+(?:'(?:m|s|re|ve|ll|d|t))?)");
	
	/** The pattern. */
	private String pattern;
	
	/**
	 * Instantiates a new message tokens.
	 *
	 * @param pattern the pattern
	 */
	private MessageTokens(String pattern) {
		this.pattern = pattern;
	}

	/** Getters and Setters **/
	
	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
}
