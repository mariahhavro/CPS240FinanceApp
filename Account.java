
public class Account {

	/*
		Issues: 
			- Unsure of how to create a unique string for any account created within class, there needs to be data held somewhere with program
			of all the ID's that are taken to be checked against.
			- Double value for balance is currently unformatted, we can just always format the output to two decimal places, or I can try and create a method
			that will round the double down to two places whenever it's stored
			- Unsure which constructors we will need and which we won't
			- Deleting account would probably be easier to down in a main class which handles account objects
			- Reminders would most likely make sense to be done in another outside program that handles account objects. Objects can't send information without being
			called methods, and are program doesn't have event handlers and event streams. Reminders, both for a negative account balance, and for other reminders like
			transfers and withdrawals, will need to be done outside the class
			- For a negative account balance, I wrote it as a test method for whether or not the account balance is negative. This can be called
			from the main program whenever a check is needed. I overloaded the method as well so it can be called from within the other methods of the class for warnings
			about withdrawals and transfers. The alert window box will prompting the user with a warning and giving them the option to continue or not. 
			(Or just stop their action completely depending on what we want to do with it)
			
	*/
	
	// Variables to hold name of account, unique ID for account, and balance
	String name;
	private String accName;
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
	
	
	public String getName() {
		return name;
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
