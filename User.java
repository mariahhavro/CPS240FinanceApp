import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents User of the app and allows them to create different accounts.
 * @author Patrick Vietor/Mackenzie Pollock
 *
 */
public class User {
	private String userName; // have an error come up if not unique
	private String password; //encrypt  
	private ArrayList<Account> userAccounts; //to hold account IDs of user accounts, arrayLists 
	private ArrayList<Loan> userLoans;
	private ArrayList<Double> SavingCalculations;
	
	private ArrayList<Debt> debts; // to hold account debts that users have added

	/**
	 * Default Constructor
	 */
	User(){
		userName = "";
		password = "";
		userAccounts = new ArrayList<Account>();
		userLoans = new ArrayList<Loan>();
		SavingCalculations = new ArrayList<Double>();
		debts = new ArrayList<Debt>();
		userLoans = new ArrayList<Loan>();
	}
	
	/**
	 * Parameterized Constructor
	 * @param user
	 * @param pass
	 */
	User(String user, String pass){
		userName = user;
		password = pass;
		userAccounts = new ArrayList<Account>();
		userLoans = new ArrayList<Loan>();
		SavingCalculations = new ArrayList<Double>();
		debts = new ArrayList<Debt>();
		userLoans = new ArrayList<Loan>();
	}
	
	/**
	 * Parameterized Constructor
	 * @param user
	 * @param pass
	 * @param accs
	 */
	User(String user, String pass, ArrayList<Account> accs){
		userName = user;
		password = pass;
		userAccounts = accs;
		userLoans = new ArrayList<Loan>();
		SavingCalculations = new ArrayList<Double>();
		debts = new ArrayList<Debt>();
		userLoans = new ArrayList<Loan>();
	}
	
	public void addLoans(Loan l) {
		userLoans.add(l);
	}
	
	public void addLoans(String loanName, double loan, double years, double rate, double downPay, double monthlyPayment, double totalInterestAccrued) {
		Loan l = new Loan(loanName, loan, years, rate, downPay, monthlyPayment, totalInterestAccrued);
		userLoans.add(l);
	}
	
	public ArrayList<Loan> getLoans(){
		return userLoans;
	}
	
	public void setLoans(ArrayList<Loan> l) {
		userLoans = l;
	}
	
	public void addAccounts(String name, double balance) {
		Integer id = (int)(Math.random() * 10000000);
		String accId =id.toString();
		Account a = new Account(name, accId, balance);
		userAccounts.add(a);
	}
	public void addAccounts(String name, String id, double balance) {
		Account a = new Account(name, id, balance);
		userAccounts.add(a);
	}
	public ArrayList<Account> getAccounts() {
		return userAccounts;
	}
	
	
	public void addDebts(Debt d) {
		debts.add(d);
	}
	
	public void addDebts(String name, double owed, double inter) {
		Debt d = new Debt(name, owed, inter);
		debts.add(d);
	}
	
	public ArrayList<Debt> getDebts(){
		return debts;
	}
	
	public void setDebts(ArrayList<Debt> d) {
		debts = d;
	}
	
	public String getUsername() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Method for saving user files
	 */
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
				myWriter.write("\na," + this.userAccounts.get(i).name + "," + this.userAccounts.get(i).accName + "," + this.userAccounts.get(i).balance);
				
			}
			for (int i = 0; i < this.debts.size(); i++) {
				
				// Write the information for each account in each subsequent line of the file
				myWriter.write("\nd," + this.debts.get(i).name + "," + this.debts.get(i).presentValue + "," + this.debts.get(i).rate);
				
			}
			for (int i = 0; i < this.userLoans.size(); i++) {
				
				// Write the information for each account in each subsequent line of the file
				myWriter.write("\nl," + this.userLoans.get(i).getLoanName() + "," + this.userLoans.get(i).getLoan() + "," + this.userLoans.get(i).getYears() + "," + 
						this.userLoans.get(i).getRate() + "," + this.userLoans.get(i).getDownPay() + "," + this.userLoans.get(i).getMonthlyPayment() + "," +
						this.userLoans.get(i).getTotalInterestAccrued());
				
			}
			
			
			// Close the writing of the file
			myWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("User writing failed");
			
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Method for creating new user files
	 */
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