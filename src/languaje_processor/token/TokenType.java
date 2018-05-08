package languaje_processor.token;

/**
 * The Enum MessageTokens.
 */
public enum TokenType {
  
  /** The url regexp. */
	URL("https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-z]{2,6}\\b[-a-zA-Z0-9@:%_\\+.~#?&//=]*", "_URL_"),
	
	NUMBER("[-+]?\\d+(?:(?:\\.\\d+)?(?:[Ee][-+]?\\d+)?)?", "_NUMBER_"),
	
	HASHTAG("#[\\w-_]+", "_HASHTAG_"),
	
	MENTION("@[\\w-_]+", "_MENTION_"),
	
  /** The word regexp. */
  WORD("[\\w-_]+(?:'(?:m|s|re|ve|ll|d|t))?", new String()),
  
  UNKNOWN(new String(), "_UNK_");
  
	/** The pattern. */
	private String pattern;
	
	private String value;
	
	/**
	 * Instantiates a new message tokens.
	 *
	 * @param pattern the pattern
	 */
	private TokenType(String pattern, String value) {
		this.pattern = pattern;
		this.value = value;
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
	
	public String getValue() {
	  return value;
	}
}
