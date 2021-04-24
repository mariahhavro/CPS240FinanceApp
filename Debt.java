/**
 * This is an underlying class used for the Debt Management class. 
 * @author Patrick Vietor
 *
 */
public class Debt {
	String name;
	double presentValue;
	double rate;

	/**
	 * Default Constructor
	 */
	public Debt() {
		name = "";
		presentValue = 0.0;
		rate = 0.0;
	}

	/**
	 * Parameterized Constructor
	 * @param name
	 * @param presentValue
	 * @param rate
	 */
	public Debt(String name, double presentValue, double rate) {
		super();
		this.name = name;
		this.presentValue = presentValue;
		this.rate = rate;
	}

	
	public double getPresentValue() {
		return presentValue;
	}

	public void setPresentValue(Double presentValue) {
		this.presentValue = presentValue;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Name: " + name + " | Amount Owed: " + presentValue + " | Interest Rate: " + rate;
	}
}