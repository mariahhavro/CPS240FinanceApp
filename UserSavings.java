package app;

import java.util.*;


public class UserSavings { //extend User

	private double accountBalance; //Starting amount
	private double[] monthlyInterest; 
	private double[] monthlySavings;
	public static final double ANNUAL_INTEREST_RATE = 0.05;
	
	/**
	 * 
	 * @param userID		-	unique userID
	 * @param userAccount	-	user's account number (Ex: Savings vs checking)
	 */
	
	public UserSavings(String userID, String userAccount) {
	//	super(userID, " ", userAccount);    STILL NEED TO EXTEND CLASS WITH LIST OF USERS
	}
	
	/**
	 * 
	 * @param userID		-	unique userID
	 * @param userAccount	-	user's account number (Ex: Savings vs checking)
	 * @param d1			-	monthly savings of the user
	 * @param d2			-	monthly interest of the user
	 */
	public UserSavings(String userID, String userAccount, double[] d1, double[] d2) {
		//super(userID, " ", userAccount);	STILL NEED TO EXTEND CLASS WITH LIST OF USERS
		d1 = monthlySavings;
		d2 = monthlyInterest;
		
		System.out.println("Savings: ");
		System.out.print(Arrays.toString(monthlySavings));
		System.out.println("Interest: ");
		System.out.println(Arrays.toString(monthlyInterest));
		System.out.println("The total account balance is: ");
		System.out.println(accountBalance);
		System.out.println();
	}
	
	public static String getReport(UserSavings[] arr) {
		for (UserSavings arr1 : arr) {
			//System.out.println("UserID: " + arr1.getUserID()); 				STILL NEED TO EXTEND CLASS WITH LIST OF USERS
			//System.out.println("UserAccount: " + arr1.getUserAccount()); 		STILL NEED TO EXTEND CLASS WITH LIST OF USERS
			System.out.println("Account Value: " + arr1.getAccountValue());
		}
		return "User Savings Report: ";
	}
	
	public double getAccountValue() {
		for (int i = 0; i < monthlyInterest.length; i++) {
			accountBalance = (accountBalance + monthlyInterest[i] + monthlySavings[i]);
		}
		return accountBalance;
	}
	
	public double[] calculateInterest() {
		for (int i = 0; i < 13; i++) {
			monthlyInterest[i] = (monthlySavings[i] * (0.05 / 12));
		}
		return monthlyInterest;
	}
	
	//public double[] getMonthlySavings() {
		// Ask user how much they earn
		
	//}
	
	public double[] getMonthlyInterest() {
		return monthlyInterest;
	}
	
	public double[] getMonthlySavings() {
		return monthlySavings;
	}
	
	// Main will go in different class and call to UserSavings Class
	public static void main(String[] args) {
		
		double[] d1 = new double [12];
		double[] d2 = new double [12];
		
//		UserSavings [] users = UserSavings[10]; //length
//		users[0] = new UserSavings(userID, userAccount, monthlySavings AKA "d1", monthlyInterst AKA "d2");
		
//		UserSavings.getReport(users);
	}
}
