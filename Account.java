/**
 * This class represents a bank account.
 * @author Patrick Vietor
 *
 */


public class Account {

	// Variables to hold name of account, unique ID for account, and balance
	String name;
	String accName;
	double balance;

	/**
	 * Default Constructor
	 */
	public Account() {
		name = "";
		accName = "";
		balance = 0;
	}

	/**
	 * Parameterized Constructor
	 * @param n - name
	 * @param b - balance
	 */
	public Account(String n, double b) {
		name = n;
		accName = "";
		balance = b;
	}

	/**
	 * Parameterized Constructor
	 * @param n - name
	 * @param an - account name
	 * @param b - balance
	 */
	public Account(String n, String an, double b) {
		name = n;
		accName = an;
		balance = b;
	}

	/**
	 *  Random balance method, with a minimum of 0 and maximum of $50,000
	 */
	public void RandBalance() {
		balance = Math.random() * 50000;
	}

	/**
	 * Deposit Method
	 * @param deposit
	 */
	public void Deposit(double deposit) {
		balance += deposit;
	}

	/**
	 * Withdrawal Method
	 * @param withdrawal
	 */
	public void Withdraw(double withdrawal) {

		Negative(withdrawal);
		balance -= withdrawal;

	}

	/**
	 * Transfer from current account method
	 * @param transfer amount
	 * @param account
	 */
	public void TransferFrom(double transfer, Account x) {

		Negative(transfer);
		balance -= transfer;
		x.balance += transfer;

	}

	/**
	 * Transfer to current account method
	 * @param transfer amount
	 * @param account
	 */
	public void TransferTo(double transfer, Account x) {

		x.Negative(transfer);
		x.balance -= transfer;
		balance += transfer;

	}

	/**
	 * Negative balance method
	 */
	public void Negative() {

		if (balance < 0) {

			// Alert window telling them their account is negative

		}

	}

	/**
	 * Overloaded negative balance method
	 * @param loss
	 */
	public void Negative(double loss) {

		if (balance - loss < 0) {

			// Alert window telling them that their account will be negative if they continue this action

		}

	}

}