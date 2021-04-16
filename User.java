import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
/*
 *  
User Class 
String userName // have an error come up if not unique 
String password //encrypt  
Accounts userAccounts[] //to hold account IDs of user accounts, arrayLists 
LoanCalc loanCalc//have arraylist to hold past loan calculations, make function to 	display, edit and delete 
savingCalc loanCalc// have arraylist to hold past loan calculations, make function to 	display, edit and delete 


Constructor 
	automatically creates checking account 
	can add more later 
	use unique ID number to be realistic 8 digits, else just use 4 digits 
Function to add accounts 
//possibly add admit option later, use gui to send to different class log in 
can see lists of all users, accounts, balances, etc 
 */

/*

	Changes made by Patrick:
	
		- In username and password constructor, creating userAccounts as an empty list when called (Otherwise when adding accounts
		  to a user object after calling constructor fails, since accounts cannot be added to a null list
		- Added newUserFile method, called when create account is used in the GUI. Method creates a text file in the Users folder
		  and write the username and password information to it.
		- Added saveUserFile method. This method is not called anywhere yet, but is intended to be the way we will overwrite old files and
		  save the new information, when transfers are done and etc. This can be called in methods for those transactions.

*/
public class User {
	private String userName; // have an error come up if not unique
	private String password; //encrypt  
	private ArrayList<Account> userAccounts; //to hold account IDs of user accounts, arrayLists 
	private ArrayList<Double> LoanCalculations;
	private ArrayList<Double> SavingCalculations;

	User(String user, String pass){
		userName = user;
		password = pass;
		userAccounts = new ArrayList<Account>();
		LoanCalculations = new ArrayList<Double>();
		SavingCalculations = new ArrayList<Double>();

	}
	
	User(String user, String pass, ArrayList<Account> accs){
		userName = user;
		password = pass;
		userAccounts = accs;
		LoanCalculations = new ArrayList<Double>();
		SavingCalculations = new ArrayList<Double>();

	}

	private void LoanCalc(int monthlyPayment, int interestRate, int numberOfMonths){
		double loanCalc = (monthlyPayment / interestRate) * (1 - 1/(Math.pow((1+interestRate),numberOfMonths)));
		displayLoanCalc(loanCalc);
		LoanCalculations.add(loanCalc);
	}

	public void displayLoanCalc(double loanCalc){
		//JavaFX Here
	}
	public void editLoanCalc(double oldLoanCalc, double newLoanCalc) {
		int x = LoanCalculations.indexOf(oldLoanCalc);
		LoanCalculations.set(x, newLoanCalc);
	}
	public void deleteLoanCalc(double LoanCalc) {
		LoanCalculations.remove(LoanCalc);
	}
	public void addAccounts(String name, String id, double balance) {
		Account a = new Account(name, id, balance);
		userAccounts.add(a);
	}
	
	public String getUsername() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	
	// Method for saving user files
	public void saveUserFile() {
		
		// Defining a string as the current directory the project is in
		String currentDirectory = new File("").getAbsolutePath();
		
		// Creating a file in the Users directory, titled as username.txt
		File userFile = new File(currentDirectory + "\\Users",this.userName + ".txt");
		try {
			// Defining a writing object to write to the created file
			FileWriter myWriter = new FileWriter(userFile);
			// Write the username and password to the first line of the file
			myWriter.write(this.userName + "," + this.password);
			
			// Loop for the amount of accounts attached to this user
			for (int i = 0; i < this.userAccounts.size(); i++) {
				
				// Write the information for each account in each subsequent line of the file
				myWriter.write("\n" + this.userAccounts.get(i).name + "," + this.userAccounts.get(i).accName + "," + this.userAccounts.get(i).balance);
				
			}
			
			// Close the writing of the file
			myWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("User writing failed");
			
			e.printStackTrace();
		}
		
		
	}

	// Method for creating new user files
	public void newUserFile() {
		
		// Defining a string as the current directory the project is in
		String currentDirectory = new File("").getAbsolutePath();
		
		// Creating a file in the Users directory, titled as username.txt
		File userFile = new File(currentDirectory + "\\Users",this.userName + ".txt");
		try {
			// Defining a writing object to write to the created file
			FileWriter myWriter = new FileWriter(userFile);
			// Write the username and password to the first line of the file
			myWriter.write(this.userName + "," + this.password);
			
			// Close the writing of the file
			myWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("User writing failed");
			
			e.printStackTrace();
		}
		
	}

}
