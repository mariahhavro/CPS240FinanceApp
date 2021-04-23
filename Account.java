public class Account {

	/*

			
	*/
	
	// Variables to hold name of account, unique ID for account, and balance
	String name;
	String accName;
	double balance;
	
	// Default constructor
	public Account() {
		name = "";
		accName = "";
		balance = 0;
	}
	
	// Constructor for just name and balance
	public Account(String n, double b) {
		name = n;
		accName = "";
		balance = b;
	}
	
	// Full constructor
	public Account(String n, String an, double b) {
		name = n;
		accName = an;
		balance = b;
	}
	
	// Random balance method, with a minimum of 0 and maximum of $50,000
	public void RandBalance() {
		balance = Math.random() * 50000;
	}
	
	// Deposit method
		public void Deposit(double deposit) {
			balance += deposit;
		}
		
	// Withdrawal method
	public void Withdraw(double withdrawal) {
		
		Negative(withdrawal);
		balance -= withdrawal;
		
	}
	
	// Transfer from current account method
	public void TransferFrom(double transfer, Account x) {
		
		Negative(transfer);
		balance -= transfer;
		x.balance += transfer;
		
	}
	
	// Transfer to current account method
		public void TransferTo(double transfer, Account x) {
			
			x.Negative(transfer);
			x.balance -= transfer;
			balance += transfer;
			
		}
	
	// Negative balance method
	public void Negative() {
		
		if (balance < 0) {
			
			// Alert window telling them their account is negative
			
		}

	}
	
	// Overloaded negative balance method
	public void Negative(double loss) {
		
		if (balance - loss < 0) {
			
			// Alert window telling them that their account will be negative if they continue this action
			
		}
		
	}
	
}
