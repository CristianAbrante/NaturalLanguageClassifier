package languaje_processor.token;

/**
 * The Enum TokenType.
 */
public enum TokenType {

	/** The url. */
	URL("https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-z]{2,6}\\b[-a-zA-Z0-9@:%_\\+.~#?&//=]*", "_URL_"),

	/** The number. */
	NUMBER("[-+]?\\d+(?:(?:\\.\\d+)?(?:[Ee][-+]?\\d+)?)?", "_NUMBER_"),

	/** The hashtag. */
	HASHTAG("#[\\w-_]+", "_HASHTAG_"),

	/** The mention. */
	MENTION("@[\\w-_]+", "_MENTION_"),

	/** The word. */
	//WORD("[\\w-_]+(?:'(?:m|s|re|ve|ll|d|t))?", new String()),
	WORD("[\\w-_]+(?:'(?:m|s|re|ve|ll|d|t))?", new String()),

	/** The unknown. */
	UNKNOWN(new String(), "_UNK_");

	/** The pattern. */
	private String pattern;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new message tokens.
	 *
	 * @param pattern the pattern
	 * @param value the value
	 */
	private TokenType(String pattern, String value) {
		this.pattern = pattern;
		this.value = value;
	}

	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * Returns the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
