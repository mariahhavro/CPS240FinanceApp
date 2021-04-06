/**
 * This class will give various credit score ratings based on credit score factors 
 * @author Jacob Mackowiak
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CreditScoreRatings extends JFrame {

	private JSlider jslcreditAccounts = new JSlider(0, 15, 0);
	private JSlider jslavailableCredit = new JSlider(0, 70, 0);
	private JSlider jslcreditLength = new JSlider(0,100,0);
	private JSlider jslpercentCreditUsed = new JSlider(0,100,0);
	private JSlider jslrecentInquiries = new JSlider(0,10,0);
	private JSlider jslpercentOnTimePayments = new JSlider(0,100,0);
	private JSlider jslcreditScore = new JSlider(300,850,300);

	private JTextField jtfcreditAccountsRating = new JTextField();
	private JTextField jtfavailableCreditRaing = new JTextField();
	private JTextField jtfcreditLengthRating = new JTextField();
	private JTextField jtfpercentCreditUsedRating = new JTextField();
	private JTextField jtfrecentInquiriesRating = new JTextField();
	private JTextField jtfpercentOnTimePaymentsRating = new JTextField();
	private JTextField jtfcreditScoreRating = new JTextField();


	public CreditScoreRatings() {

		jtfcreditAccountsRating.setEditable(false);
		jtfavailableCreditRaing.setEditable(false);
		jtfcreditLengthRating.setEditable(false);
		jtfpercentCreditUsedRating.setEditable(false);
		jtfrecentInquiriesRating.setEditable(false);
		jtfpercentOnTimePaymentsRating.setEditable(false);
		jtfcreditScoreRating.setEditable(false);

		setSliderTicMarks(jslcreditAccounts, jslavailableCredit, jslcreditLength, 
			jslpercentCreditUsed, jslrecentInquiries, jslpercentOnTimePayments, jslcreditScore);

		JPanel p1 = new JPanel(new GridLayout(7, 3));
		p1.add(new JLabel("Credit Accounts"));
		p1.add(jslcreditAccounts);
		p1.add(new JLabel("Rating:"));
		p1.add(jtfcreditAccountsRating);
		p1.add(new JLabel("Available Credit (x10^3)"));
		p1.add(jslavailableCredit);
		p1.add(new JLabel("Rating:"));
		p1.add(jtfavailableCreditRaing);
		p1.add(new JLabel("Credit Length"));
		p1.add(jslcreditLength);
		p1.add(new JLabel("Rating:"));
		p1.add(jtfcreditLengthRating);
		p1.add(new JLabel("Credit Used (%)"));
		p1.add(jslpercentCreditUsed );
		p1.add(new JLabel("Rating:"));
		p1.add(jtfpercentCreditUsedRating);
		p1.add(new JLabel("Recent Inquriries"));
		p1.add(jslrecentInquiries );
		p1.add(new JLabel("Rating:"));
		p1.add(jtfrecentInquiriesRating);
		p1.add(new JLabel("On time payments (%)"));
		p1.add(jslpercentOnTimePayments);
		p1.add(new JLabel("Rating:"));
		p1.add(jtfpercentOnTimePaymentsRating);
		p1.add(new JLabel("Credit Score"));
		p1.add(jslcreditScore);
		p1.add(new JLabel("Rating:"));
		p1.add(jtfcreditScoreRating);
		p1.setBorder(new TitledBorder("Enter credit score factors"));



		add(p1, BorderLayout.CENTER);

		// get the frame ready
		setTitle("Credit Score Ratings");
		setLocationRelativeTo(null); // center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);

	}


	public static void setSliderTicMarks(JSlider jslcreditAccounts, JSlider jslavailableCredit, JSlider jslcreditLength, 
			JSlider jslpercentCreditUsed, JSlider jslrecentInquiries, JSlider jslpercentOnTimePayments, JSlider jslcreditScore) {
		jslcreditAccounts.setMajorTickSpacing(5);
		jslcreditAccounts.setMinorTickSpacing(1);
		jslcreditAccounts.setPaintTicks(true);
		jslcreditAccounts.setPaintLabels(true);

		jslavailableCredit.setMajorTickSpacing(10);
		jslavailableCredit.setMinorTickSpacing(5);
		jslavailableCredit.setPaintTicks(true);
		jslavailableCredit.setPaintLabels(true);

		jslcreditLength.setMajorTickSpacing(50);
		jslcreditLength.setMinorTickSpacing(10);
		jslcreditLength.setPaintTicks(true);
		jslcreditLength.setPaintLabels(true);

		jslpercentCreditUsed.setMajorTickSpacing(50);
		jslpercentCreditUsed.setMinorTickSpacing(10);
		jslpercentCreditUsed.setPaintTicks(true);
		jslpercentCreditUsed.setPaintLabels(true);

		jslrecentInquiries.setMajorTickSpacing(5);
		jslrecentInquiries.setMinorTickSpacing(1);
		jslrecentInquiries.setPaintTicks(true);
		jslrecentInquiries.setPaintLabels(true);

		jslpercentOnTimePayments.setMajorTickSpacing(50);
		jslpercentOnTimePayments.setMinorTickSpacing(10);
		jslpercentOnTimePayments.setPaintTicks(true);
		jslpercentOnTimePayments.setPaintLabels(true);

		jslcreditScore.setMajorTickSpacing(125);
		jslcreditScore.setMinorTickSpacing(100);
		jslcreditScore.setPaintTicks(true);
		jslcreditScore.setPaintLabels(true);

	}
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
	
	public static void main(String [] args) {

		JFrame frame = new CreditScoreRatings();
		
	}
}
