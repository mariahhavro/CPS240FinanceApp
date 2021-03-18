import java.util.ArrayList;
import java.util.Scanner;

public class DebtManagement {
	ArrayList<LoanCalc> loans = new ArrayList<LoanCalc>();

	// have user enter all loans (uses LoanCalc) creates a list of loans
	public void getLoans() {
		Scanner sc = new Scanner(System.in); // scanner

		boolean moreLoans = true;// used to determine whether to ask for another loan

		// values to ask for from user to create loanCalc for list of loans
		String name = "";
		double principle = 0.0;
		double monthlyPayment = 0.0;
		double presentValue = 0.0;
		double rate = 0.0;
		String anotherLoan = "";

		// loops until users replies "No" to last question, creates a new LoanCalc and
		// adds to list each iteration
		while (moreLoans) {
			// get constructor items of LoanCalc
			System.out.println("Please enter in loan information as prompted... ");
			System.out.println("Name of Loan: ");
			name = sc.nextLine();

			System.out.println("Principle: ");
			principle = sc.nextDouble();

			System.out.println("Monthly Payment: ");
			monthlyPayment = sc.nextDouble();

			System.out.println("Present Value: ");
			presentValue = sc.nextDouble();

			System.out.println("Rate: ");
			rate = sc.nextDouble();

			// Create LoanCalc and add to ArrayList
			loans.add(new LoanCalc(name, principle, monthlyPayment, presentValue, rate));

			// Check for more Loans, if not end loop by setting boolean moreLoans to false
			System.out.println("Do you have another loan/debt to enter? (Yes/No)");
			anotherLoan = sc.next();
			if (anotherLoan.equals("No") || anotherLoan.equals("no") || anotherLoan.equals("NO")) {
				moreLoans = false;
			}
		}
	}

	public void determineTotalDebtActions() {
		double totalDebt = 0.0;
		double sumRate = 0.0;
		double averageRate = 0.0;
		double extremeDebt = 500.00;
		double extremeRate = 40.0;

		// Add up total loan values
		for (int i = 0; i < loans.size(); i++) {
			totalDebt += loans.getPresentValue();
			sumRate += loans.getRate();
		}
		averageRate = totalRate / loans.size();

		if (totalDebt >= extremeDebt) {
			// recommend change i.e. bankruptcy, consolidation
		}

		if (averageRate >= extremeRate) {
			// recommend change i.e. bankruptcy, consolidation
		}
	}

	// determine two methods of paying off loans, smallest debts first, or highest
	// rate first. Returns list
	public ArrayList<LoanCalc> determineDebtPaymentPlan() {
		//intialize lists to be returned, will only return one.
		ArrayList<LoanCalc> loansByRate = loans;
		ArrayList<LoanCalc> loansByDebt = loans;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to pay off by highest rate or smallest debt? (Debt/Rate)?");
		String method = sc.next();
		
		//Sort by smallest debts using selection sort, return that ordered list
		if (method.equals("Debt") || method.equals("debt") || method.equals("DEBT")) {
			for(int i = 0; i < loansByDebt.size()-1; i++) {
				int minIndex = i;
				for(int j = i + 1; j < loansByDebt.size(); j++) {
					if((loansByDebt.get(j)).getPresentValue() < (loansByDebt.get(minIndex)).getPresentValue()) {
						minIndex = j;
					}
				}
				if(minIndex != i) {
					LoanCalc temp = new LoanCalc(); //temporary variable for swapping
					temp = loansByDebt.get(minIndex);
					loansByDebt.set(minIndex, loansByDebt.get(i));
					loansByDebt.set(i, temp);
				}
			}
			return loansByDebt;
		}
		//Sort by highest rates using selection sort, return that ordered list
		if (method.equals("Rate") || method.equals("rate") || method.equals("RATE")) {
			for(int i = 0; i < loansByRate.size()-1; i++) {
				int minIndex = i;
				for(int j = i + 1; j < loansByRate.size(); j++) {
					if((loansByRate.get(j)).getRate() < (loansByRate.get(minIndex)).getRate()) {
						minIndex = j;
					}
				}
				if(minIndex != i) {
					LoanCalc temp = new LoanCalc(); //temporary variable for swapping
					temp = loansByRate.get(minIndex);
					loansByRate.set(minIndex, loansByRate.get(i));
					loansByRate.set(i, temp);
				}
			}
			return loansByRate;
		}
	}

}
