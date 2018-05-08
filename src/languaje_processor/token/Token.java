/**
 * @author cristian
 * @date 7 may. 2018
 */
package languaje_processor.token;

/**
 * <h2>Token</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	7 may. 2018
 * @version 	1.0.0
 */
public class Token implements Comparable {
 
  private String value;
  private int frecuency;
  private double logProb;
  
  /**
   * Constructor of Token
   */
  public Token() {
    value = new String();
  }
  
  /**
   * Constructor of Token
   *
   * @param value
   * @param frecuency
   * @param logProb
   */
  public Token(String value, int frecuency, double logProb) {
    super();
    setValue(value);
    this.frecuency = frecuency;
    setLogProb(logProb);
  }
  
  
  /**
   * Constructor of Token
   *
   * @param value
   */
  public Token(String value) {
    this(value, 1, 0.0);
  }


  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }
  
  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    if (value != null) {
      this.value = value;      
    } else {
      throw new NullPointerException("value can't be null");
    }
  }
  
  /**
   * @return the frecuency
   */
  public int getFrecuency() {
    return frecuency;
  }
  
  /**
   * @param frecuency the frecuency to set
   */
  public void incrementFrecuency() {
    this.frecuency += 1;
  }
  
  /**
   * @return the logProb
   */
  public double getLogProb() {
    return logProb;
  }
  /**
   * @param logProb the logProb to set
   */
  public void setLogProb(double logProb) {
    this.logProb = logProb;
  }


  @Override
  public int compareTo(Object o) {
    if (o != null) {
      return getValue().compareTo(((Token) o).getValue());      
    } else {
      return -1;
    }
  }
  
  /* (non-Javadoc)
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


  /* (non-Javadoc)
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
