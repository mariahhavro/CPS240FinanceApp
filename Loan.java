/**
 * This class is used for the SwingLoanCalc class.
 * @author Dylan Bauders
 *
 */
public class Loan {
	String loanName;
	double loan, years, rate, downPay, monthlyPayment, totalInterestAccrued;

	/**
	 * Deafult Constructor
	 */
	public Loan() {
		loanName = "";
		loan = 0.0;
		years = 0.0;
		rate = 0.0;
		downPay = 0.0;
		monthlyPayment = 0.0;
		totalInterestAccrued = 0.0;
	}

	/**
	 * Parameterized Constructor
	 * @param loanName
	 * @param loan
	 * @param years
	 * @param rate
	 * @param downPay
	 * @param monthlyPayment
	 * @param totalInterestAccrued
	 */
	public Loan(String loanName, double loan, double years, double rate, double downPay, double monthlyPayment, double totalInterestAccrued) {
		super();
		this.loanName = loanName;
		this.loan = loan;
		this.years = years;
		this.rate = rate;
		this.downPay = downPay;
		this.monthlyPayment = monthlyPayment;
		this.totalInterestAccrued = totalInterestAccrued;
	}

	public double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}
	
	public double getYears() {
		return years;
	}
	
	public void setYears(Double years) {
		this.years = years;
	}
	
	public double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	public double getDownPay() {
		return downPay;
	}
	
	public void setDownPay(Double downPay) {
		this.downPay = downPay;
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	
	public double getTotalInterestAccrued() {
		return totalInterestAccrued;
	}
	
	public void setTotalInterestAccrued(Double totalInterestAccrued) {
		this.totalInterestAccrued = totalInterestAccrued;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String toString() {
		return "Loan Name: " + loanName + "\nLoan Amount: $" + loan + "\nTerm in Years: " + years + "\nInterest Rate: %" + rate + "\nDown Payment: $" + downPay + "\nMonthly Payment : $" + monthlyPayment + "\nTotal Interest Accrued: $" + totalInterestAccrued + "\n";
	}
}