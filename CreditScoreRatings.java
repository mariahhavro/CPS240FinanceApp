/**
 * This class will give various credit score ratings based on credit score factors 
 * @author Jacob Mackowiak
 *
 */

public class CreditScoreRatings  {


	/**
	 * This method accepts a user credit score and shows how good their score is
	 * @param creditScore
	 * @return
	 * @throws CreditScoreException
	 */
	public static String showCreditScoreRating(int creditScore){


		if(creditScore <560 && creditScore >=300) {
			return (": Very Bad");
		}
		else if(creditScore >=560 && creditScore < 650) {
			return (": Bad");
		}
		else if(creditScore >=650 && creditScore < 700) {
			return(": Fair");
		}
		else if(creditScore >=700 && creditScore < 750) {
			return(": Good");
		}
		else if(creditScore >=750 && creditScore <= 850) {
			return(": Great");
		}
		else {
			return "";
		}
	}

	public static String creditAccountRating(int creditAccounts) {
		String msg = "";

		//improve creditAccounts
		if(creditAccounts >=0 && creditAccounts <=2) {
			msg += ": Excellent\n";
		}
		else if(creditAccounts >=3 && creditAccounts <=4) {
			msg += ": Good\n";
		}
		else if(creditAccounts >=5 && creditAccounts <=6) {
			msg += ": Average\n";
		}
		else{
			msg += ": Below Average\n";
		}
		return msg;
	}

	public static String availableCreditRating(int availableCredit) {
		String msg = "";

		// improve available credit
		if(availableCredit >=0 && availableCredit <=2500) {
			msg += ": Below Average\n";
		}
		else if(availableCredit >=2500 && availableCredit <=15000) {
			msg += ": Average\n";
		}
		else if(availableCredit >=15000 && availableCredit <=50000) {
			msg += ": Good\n";
		}
		else{
			msg += ": Excellent\n";
		}

		String num= String.format("%,2d", availableCredit);
		String newmsg = num + msg;
		return newmsg;
	}
	public static String creditLengthRating(int creditLength) {

		String msg = "";

		// credit length
		if(creditLength >=0 && creditLength <=2) {
			msg += ": Below Average\n";
		}
		else if(creditLength >=3 && creditLength <=7) {
			msg += ": Average\n";
		}
		else if(creditLength >=8 && creditLength <=24) {
			msg += ": Good\n";
		}
		else{
			msg += ": Excellent\n";
		}

		return msg;
	}

	public static String percentCreditUsedRating(int percentCreditUsed) {

		String msg = "";

		// percentage of credit used
		if(percentCreditUsed >=61 && percentCreditUsed <=100) {
			msg += "%: Below Average\n";
		}
		else if(percentCreditUsed >=31 && percentCreditUsed <=60) {
			msg += "%: Average\n";
		}
		else if(percentCreditUsed >=11 && percentCreditUsed <=30) {
			msg += "%: Good\n";
		}
		else if(percentCreditUsed >=0 && percentCreditUsed <=10) {
			msg += "%: Excellent\n";
		}

		return msg;
	}

	public static String recentInquiriesRating(int recentInquiries) {

		String msg = "";
		// inquiries
		if(recentInquiries == 0) {
			msg += ": Excellent\n";
		}
		else if(recentInquiries >=1 && recentInquiries <=2) {
			msg += ": Good\n";
		}
		else if(recentInquiries >=3 && recentInquiries <=5) {
			msg += ": Average \n";
		}
		else{
			msg += ": Below Average\n";
		}

		return msg;

	}

	public static String percentOnTimePaymentsRating(int percentOnTimePayments) {

		String msg = "";

		// on time payments
		if(percentOnTimePayments == 100) {
			msg += "%: Excellent\n";
		}
		else if(percentOnTimePayments >=98 && percentOnTimePayments <=99) {
			msg += "%: Good\n";
		}
		else if(percentOnTimePayments >=90 && percentOnTimePayments <=97) {
			msg += "%: Average \n";
		}
		else{
			msg += "%: Below Average\n";
		}

		return msg;
	}

	public static void main(String [] args) {

		//JFrame frame = new CreditScoreRatings();

	}
}
