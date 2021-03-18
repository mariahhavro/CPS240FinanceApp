/**
 * This class will give various credit score ratings based on credit score factors 
 * @author Jacob Mackowiak
 *
 */
public class CreditScoreRatings {

	/**
	 * This method gives suggestions to improve credit score using various factors
	 * @param creditAccounts
	 * @param availableCredit
	 * @param creditLength
	 * @param percentCreditUsed
	 * @param recentInquiries
	 * @param percentOnTimePayments
	 * @return
	 */
	public static String creditImprovements(int creditAccounts, int availableCredit, int creditLength, 
			int percentCreditUsed, int recentInquiries, int percentOnTimePayments) {

		String msg = "";

		//improve creditAccounts
		if(creditAccounts >=0 && creditAccounts <=2) {
			msg += "Credit Accounts: Excellent\n";
		}
		else if(creditAccounts >=3 && creditAccounts <=4) {
			msg += "Credit Accounts: Good\n";
		}
		else if(creditAccounts >=5 && creditAccounts <=6) {
			msg += "Credit Accounts: Average\n";
		}
		else{
			msg += "Credit Accounts: Below Average\n";
		}
		// improve available credit
		if(availableCredit >=0 && availableCredit <=2500) {
			msg += "Available Credit: Below Average\n";
		}
		else if(availableCredit >=2500 && availableCredit <=15000) {
			msg += "Available Credit: Average\n";
		}
		else if(availableCredit >=15000 && availableCredit <=50000) {
			msg += "Available Credit: Good\n";
		}
		else{
			msg += "Available Credit: Excellent\n";
		}
		// credit length
		if(creditLength >=0 && creditLength <=2) {
			msg += "Credit Length: Below Average\n";
		}
		else if(creditLength >=3 && creditLength <=7) {
			msg += "Credit Length: Average\n";
		}
		else if(creditLength >=8 && creditLength <=24) {
			msg += "Credit Length: Good\n";
		}
		else{
			msg += "Credit Length: Excellent\n";
		}
		// percentage of credit used
		if(percentCreditUsed >=61 && percentCreditUsed <=100) {
			msg += "Credit Used: Below Average\n";
		}
		else if(percentCreditUsed >=31 && percentCreditUsed <=60) {
			msg += "Credit Used: Average\n";
		}
		else if(percentCreditUsed >=11 && percentCreditUsed <=30) {
			msg += "Credit Used: Good\n";
		}
		else if(percentCreditUsed >=0 && percentCreditUsed <=10) {
			msg += "Credit Used: Excellent\n";
		}
		// inquiries
		if(recentInquiries == 0) {
			msg += "Inquiries: Excellent\n";
		}
		else if(recentInquiries >=1 && recentInquiries <=2) {
			msg += "Inquiries: Good\n";
		}
		else if(recentInquiries >=3 && recentInquiries <=5) {
			msg += "Inquiries: Average \n";
		}
		else{
			msg += "Inquiries: Below Average\n";
		}
		// on time payments
		if(percentOnTimePayments == 100) {
			msg += "On time payments: Excellent\n";
		}
		else if(percentOnTimePayments >=98 && percentOnTimePayments <=99) {
			msg += "On time payments: Good\n";
		}
		else if(percentOnTimePayments >=90 && percentOnTimePayments <=97) {
			msg += "On time payments: Average \n";
		}
		else{
			msg += "On time payments: Below Average\n";
		}


		return msg;
	}

	/**
	 * This method accepts a user credit score and shows how good their score is
	 * @param creditScore
	 * @return
	 * @throws CreditScoreException
	 */
	public static String showCreditScoreRating(int creditScore) throws CreditScoreException {

		if(creditScore <300) {
			throw new CreditScoreException("Invalid Credit Score. Credit Score must be at least 300.");
		}
		else if(creditScore <560 && creditScore >=300) {
			return ("Credit Score: " + creditScore + " --> Very Bad");
		}
		else if(creditScore >=560 && creditScore < 650) {
			return ("Credit Score: " + creditScore + " --> Bad");
		}
		else if(creditScore >=650 && creditScore < 700) {
			return("Credit Score: " + creditScore + " --> Fair");
		}
		else if(creditScore >=700 && creditScore < 750) {
			return("Credit Score: " + creditScore + " --> Good");
		}
		else if(creditScore >=750 && creditScore <= 850) {
			return("Credit Score: " + creditScore + " --> Great");
		}
		else {
			throw new CreditScoreException("Invalid Credit Score. Credit Score can be at most 850.");
		}

	}
// this main function is just for testing purposes
//	public static void main(String [] args) {
//
//		try{
//			String creditScore = showCreditScoreRating(500);
//			System.out.println(creditScore);
//		}
//		catch(CreditScoreException CSE) {
//			System.out.println(CSE);
//		}
//		System.out.println();
//		String credit = creditImprovements(7,2500,5,5,5,5);
//		System.out.print(credit);
//	}
}