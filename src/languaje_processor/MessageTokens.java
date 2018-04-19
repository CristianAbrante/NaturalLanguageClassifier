package languaje_processor;

public enum MessageTokens {
	WORD("\\s*(\\w+)\\s+");
	
	private String pattern;
	
	private MessageTokens(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
