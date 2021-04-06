import java.util.ArrayList;
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
public class User {
	private String userName; // have an error come up if not unique
	private String password; //encrypt  
	private Accounts userAccounts[]; //to hold account IDs of user accounts, arrayLists 
	private ArrayList<Double> LoanCalculations;
	private ArrayList<Double> SavingCalculations;
			
	User(String user, String pass){
		userName = user;
		password = pass;
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
	
	
	
}
