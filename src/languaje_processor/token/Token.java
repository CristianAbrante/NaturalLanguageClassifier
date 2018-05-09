package languaje_processor.token;


/**
 * The Class Token.
 */
public class Token implements Comparable {

	/** The value. */
	private String value;
	
	/** The frecuency. */
	private int frecuency;
	
	/** The log prob. */
	private double logProb;

	/**
	 * Instantiates a new token.
	 */
	public Token() {
		value = new String();
	}

	
	/**
	 * Instantiates a new token.
	 *
	 * @param value the value
	 * @param frecuency the frecuency
	 * @param logProb the log prob
	 */
	public Token(String value, int frecuency, double logProb) {
		super();
		setValue(value);
		this.frecuency = frecuency;
		setLogProb(logProb);
	}

	
	/**
	 * Instantiates a new token.
	 *
	 * @param value the value
	 */
	public Token(String value) {
		this(value, 1, 0.0);
	}

	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		if (value != null) {
			this.value = value;
		} else {
			throw new NullPointerException("value can't be null");
		}
	}

	
	/**
	 * Gets the frecuency.
	 *
	 * @return the frecuency
	 */
	public int getFrecuency() {
		return frecuency;
	}

	
	/**
	 * Increment frecuency.
	 */
	public void incrementFrecuency() {
		this.frecuency += 1;
	}

	
	/**
	 * Gets the log prob.
	 *
	 * @return the log prob
	 */
	public double getLogProb() {
		return logProb;
	}

	
	/**
	 * Sets the log prob.
	 *
	 * @param logProb the new log prob
	 */
	public void setLogProb(double logProb) {
		this.logProb = logProb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		if (o != null) {
			return getValue().compareTo(((Token) o).getValue());
		} else {
			return -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frecuency;
		long temp;
		temp = Double.doubleToLongBits(logProb);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (frecuency != other.frecuency)
			return false;
		if (Double.doubleToLongBits(logProb) != Double.doubleToLongBits(other.logProb))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
