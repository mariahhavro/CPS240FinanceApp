import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SwingLoanCalc extends JFrame implements ActionListener {

//Colors Icons and fonts
	Color gold = new Color(252, 205, 53);
	Color maroon = new Color(128, 0, 0);
	Color black = new Color(0, 0, 0);

	Font tnr = new Font("TimesNewRoman", 20, 20);

//icons downloaded from icons8.com
	Icon home = new ImageIcon("icons8-home-128.png");
	Icon pen = new ImageIcon("icons8-pencil-96.png");
	Icon budget = new ImageIcon("icons8-money-96.png");

// Calc menu JComponents
	CardLayout calcCard = new CardLayout();
	CardLayout vedCard = new CardLayout();

	GridLayout glCalcMenu = new GridLayout(3, 2, 10, 25);
	LineBorder bBorder = new LineBorder(gold, 5);
	LineBorder tBorder = new LineBorder(gold, 3);

	JPanel panelCont = new JPanel(); // Panel container for entire tab
	JPanel panelCalcMenu = new JPanel(); // Menu for calculators
	JPanel panelPrivLoan = new JPanel(new BorderLayout()); // Private Loan Calculator
	JPanel panelViewLoan = new JPanel(new BorderLayout()); // View saved loans
	JPanel panelBudget = new JPanel(new BorderLayout()); // Budget Planner
	JPanel panelViewCont = new JPanel(vedCard); // View/edit Card

	JButton bCalcMenu1 = new JButton("Return to loan menu");
	JButton bCalcMenu2 = new JButton("Return to loan menu");
	JButton bCalcMenu3 = new JButton("Return to loan menu");
	JButton bCalcMenu4 = new JButton("Return to loan menu");

//Buttons with icons
	JButton bPrivLoan = new JButton(home);
	JButton bViewLoan = new JButton(pen);
	JButton bBudget = new JButton(budget);

//Private Loan Calculator JComponents and Global Variables
	ArrayList<Loan> loans = new ArrayList<Loan>();
	JPanel bpanelPrivLoan, tpanelPrivLoan, spanelPrivLoan;
	JLabel loanLabel, yearsLabel, rateLabel, downPayLabel, downPayLabel2, monthlyPayLabel, accruedInterestLabel,
			errorLabel, saveLabel;
	JTextField loanField, termField, rateField, downPayField, monthlyPayField, accruedInterestField, saveField;
	JButton cButton, sButton;
	private double loan, years, rate, downPay;
	private String loanName;
	static User curr = new User();

//View/Edit Loans JComponents and Global Variables
	JPanel panelEditLoan, tpanelEditLoan, bpanelEditLoan, tpanelViewLoan, bpanelViewLoan;
	JLabel eloanLabel, eyearsLabel, erateLabel, edownPayLabel, emonthlyPayLabel, eaccruedInterestLabel, esaveLabel;
	JTextField eloanField, etermField, erateField, edownPayField, emonthlyPayField, eaccruedInterestField, esaveField;
	JTextArea jtaViewLoan;
	JButton eButton, delButton, nextButton, prevButton, ecButton, bViewMenu;
	int iter = 0;
	private double eloan, eyears, erate, edownPay;
	private String eloanName;

//Budget Planner JComponents and Global Variables
	JPanel mypanelBudget, tpanelBudget, bpanelBudget;
	JLabel rentLabel, electricLabel, waterLabel, incomeLabel, foodLabel, subscriptionLabel, expensesLabel;
	JTextField rentField, electricField, waterField, incomeField, foodField, subscriptionField, expensesField;
	JButton cButtonBudget;
	private double rent, electric, water, income, food, subscription;

	public JComponent LoanCalcTab() throws IndexOutOfBoundsException {
		panelCont.setLayout(calcCard);

		bCalcMenu1.setFont(tnr);
		bCalcMenu2.setFont(tnr);
		bCalcMenu3.setFont(tnr);
		bCalcMenu4.setFont(tnr);

		bPrivLoan.setBorder(bBorder);
		bPrivLoan.setForeground(maroon);
		bPrivLoan.setText("Personal Loan Calculator");
		bPrivLoan.setFont(tnr);

		bViewLoan.setBorder(bBorder);
		bViewLoan.setForeground(maroon);
		bViewLoan.setText("View/Edit Loans");
		bViewLoan.setFont(tnr);

		bBudget.setBorder(bBorder);
		bBudget.setForeground(maroon);
		bBudget.setText("Budget Planner");
		bBudget.setFont(tnr);

		panelCalcMenu.setBackground(maroon);
		panelCalcMenu.setLayout(glCalcMenu);
		panelCalcMenu.add(bPrivLoan);
		panelCalcMenu.add(bViewLoan);
		panelCalcMenu.add(bBudget);

		panelCont.add(panelCalcMenu, "1");
		panelCont.add(panelPrivLoan, "2");
		panelCont.add(panelViewCont, "3");
		panelCont.add(panelBudget, "4");
		calcCard.show(panelCont, "1");

		bPrivLoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "2");
			}
		});
		bViewLoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "3");
			}
		});
		bBudget.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "4");
			}
		});
		// END of cardMenu

		// PRIVATE LOAN CALC

		// Panel for private loan calculator
		// panelPrivLoan || JPanel || BorderLayout()
		JPanel tpanelPrivLoan = new JPanel(new GridLayout(0, 2, 30, 30));
		JPanel bpanelPrivLoan = new JPanel(new GridLayout(0, 2, 30, 10));
		JTextArea jtaViewLoan = new JTextArea();

		// Loan label and text field
		loanLabel = new JLabel("Loan Amount");
		loanLabel.setFont(tnr);
		loanLabel.setForeground(gold);
		tpanelPrivLoan.add(loanLabel);
		loanField = new JTextField();
		loanField.setFont(tnr);
		loanField.setBorder(tBorder);
		tpanelPrivLoan.add(loanField);
		loanField.addActionListener(this);

		// Down payment label and text field
		downPayLabel = new JLabel("Down Payment: ");
		downPayLabel.setFont(tnr);
		downPayLabel.setForeground(gold);
		tpanelPrivLoan.add(downPayLabel);
		downPayField = new JTextField();
		downPayField.setFont(tnr);
		downPayField.setBorder(tBorder);
		tpanelPrivLoan.add(downPayField);
		downPayField.addActionListener(this);

		// Term label and text field
		yearsLabel = new JLabel("Loan Term in Years: ");
		yearsLabel.setFont(tnr);
		yearsLabel.setForeground(gold);
		tpanelPrivLoan.add(yearsLabel);
		termField = new JTextField();
		termField.setFont(tnr);
		termField.setBorder(tBorder);
		tpanelPrivLoan.add(termField);
		termField.addActionListener(this);

		// Interest rate label and text field
		rateLabel = new JLabel("Interest Rate (Ex: 5 = 5%): ");
		rateLabel.setFont(tnr);
		rateLabel.setForeground(gold);
		tpanelPrivLoan.add(rateLabel);
		rateField = new JTextField();
		rateField.setFont(tnr);
		rateField.setBorder(tBorder);
		tpanelPrivLoan.add(rateField);
		rateField.addActionListener(this);

		// Monthly payment label and text field
		monthlyPayLabel = new JLabel("Monthly Payment: ");
		monthlyPayLabel.setFont(tnr);
		monthlyPayLabel.setForeground(gold);
		tpanelPrivLoan.add(monthlyPayLabel);
		monthlyPayField = new JTextField();
		monthlyPayField.setFont(tnr);
		monthlyPayField.setBorder(new LineBorder(black));
		tpanelPrivLoan.add(monthlyPayField);
		monthlyPayField.setBackground(Color.lightGray);
		monthlyPayField.setEditable(false);
		monthlyPayField.addActionListener(this);

		// Accrued interest label and text field
		accruedInterestLabel = new JLabel("Accrued Interest: ");
		accruedInterestLabel.setFont(tnr);
		accruedInterestLabel.setForeground(gold);
		tpanelPrivLoan.add(accruedInterestLabel);
		accruedInterestField = new JTextField();
		accruedInterestField.setFont(tnr);
		accruedInterestField.setBorder(new LineBorder(black));
		tpanelPrivLoan.add(accruedInterestField);
		accruedInterestField.setBackground(Color.lightGray);
		accruedInterestField.setEditable(false);
		accruedInterestField.addActionListener(this);

		saveLabel = new JLabel("Enter unique loan name");
		saveLabel.setFont(tnr);
		saveLabel.setForeground(gold);
		tpanelPrivLoan.add(saveLabel);
		saveField = new JTextField();
		saveField.setFont(tnr);
		saveField.setBorder(tBorder);
		tpanelPrivLoan.add(saveField);

		errorLabel = new JLabel("");
		errorLabel.setFont(tnr);
		errorLabel.setForeground(gold);

		jtaViewLoan.setText("");

		sButton = new JButton("Save Loan");
		sButton.addActionListener(e -> {
			if (monthlyPayField.getText().equals("") || accruedInterestField.getText().equals("")) {
				errorLabel.setText("Calculate your loan first.");
			} else if (saveLabel.getText().equals("")) { // If loan name is empty
				errorLabel.setText("Set a loan name first.");
			} else {
				loans = curr.getLoans();
				loanName = saveField.getText();
				loan = (Double.parseDouble(loanField.getText()));
				years = (Double.parseDouble(termField.getText()));
				rate = (Double.parseDouble(rateField.getText()));
				downPay = (Double.parseDouble(downPayField.getText()));
				loans.add(new Loan(loanName, loan, years, rate, downPay, Double.parseDouble(monthlyPayField.getText()),
						Double.parseDouble(accruedInterestField.getText())));
				jtaViewLoan.append(loans.get(loans.size() - 1) + "\n");
				saveField.setText("");
				loanField.setText("");
				rateField.setText("");
				downPayField.setText("");
				monthlyPayField.setText("");
				accruedInterestField.setText("");
				vedCard.show(panelViewCont, "View");
			}
		});
		tpanelPrivLoan.add(sButton);

		// calcMenu Button
		bCalcMenu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "1");
				panelCalcMenu.add(bPrivLoan, glCalcMenu);
				panelCalcMenu.add(bViewLoan, glCalcMenu);
				panelCalcMenu.add(bBudget, glCalcMenu);
			}
		});
		bpanelPrivLoan.add(bCalcMenu1);

		// Button to calculate
		cButton = new JButton("Calculate");
		cButton.setFont(tnr);
		cButton.addActionListener(this);
		bpanelPrivLoan.add(cButton);

		panelPrivLoan.add(tpanelPrivLoan, BorderLayout.NORTH);
		panelPrivLoan.add(bpanelPrivLoan, BorderLayout.SOUTH);

		bpanelPrivLoan.setBackground(maroon);
		tpanelPrivLoan.setBackground(maroon);

		TitledBorder privLoanTitle = new TitledBorder("Private Loan Calculator");
		privLoanTitle.setTitleColor(gold);
		panelPrivLoan.setBorder(privLoanTitle);

		panelPrivLoan.setBackground(maroon);
		panelPrivLoan.setOpaque(true);
		// END OF PRIVATE LOAN PANEL

		// VIEW/EDIT LOANS PANEL
		// panelViewCont || CardLayout

		JPanel panelViewLoan = new JPanel(new BorderLayout());
		JPanel tpanelViewLoan = new JPanel();
		JPanel bpanelViewLoan = new JPanel(new GridLayout(0, 2, 20, 10));
		JPanel panelEditLoan = new JPanel(new BorderLayout());
		JPanel tpanelEditLoan = new JPanel(new GridLayout(0, 2, 30, 30));
		JPanel bpanelEditLoan = new JPanel(new GridLayout(0, 2, 30, 10));

		panelViewLoan.setBackground(maroon);
		tpanelViewLoan.setBackground(maroon);
		bpanelViewLoan.setBackground(maroon);
		panelEditLoan.setBackground(maroon);
		tpanelEditLoan.setBackground(maroon);
		bpanelEditLoan.setBackground(maroon);

		panelViewCont.add(panelViewLoan, "View");
		panelViewCont.add(panelEditLoan, "Edit");
		vedCard.show(panelViewCont, "View");

		TitledBorder viewLoanTitle = new TitledBorder("View Saved Loans");
		viewLoanTitle.setTitleColor(gold);
		panelViewLoan.setBorder(viewLoanTitle);

		TitledBorder editLoanTitle = new TitledBorder("Edit Saved Loans");
		editLoanTitle.setTitleColor(gold);
		panelEditLoan.setBorder(editLoanTitle);

		// VIEW PANEL

		tpanelViewLoan.add(jtaViewLoan);

		bCalcMenu2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "1");
				panelCalcMenu.add(bPrivLoan, glCalcMenu);
				panelCalcMenu.add(bViewLoan, glCalcMenu);
				panelCalcMenu.add(bBudget, glCalcMenu);
			}
		});
		bpanelViewLoan.add(bCalcMenu2);

		JButton editButton = new JButton("Edit Loans");
		editButton.addActionListener(e -> {
			vedCard.show(panelViewCont, "Edit");

		});
		bpanelViewLoan.add(editButton);

		panelViewLoan.add(tpanelViewLoan, BorderLayout.NORTH);
		panelViewLoan.add(bpanelViewLoan, BorderLayout.SOUTH);

		// EDIT PANEL
		JLabel eloanLabel = new JLabel("Loan Amount");
		eloanLabel.setFont(tnr);
		eloanLabel.setForeground(gold);
		tpanelEditLoan.add(eloanLabel);
		JTextField eloanField = new JTextField();
		eloanField.setFont(tnr);
		eloanField.setBorder(tBorder);
		tpanelEditLoan.add(eloanField);
		eloanField.addActionListener(this);

		// Down payment label and text field
		edownPayLabel = new JLabel("Down Payment: ");
		edownPayLabel.setFont(tnr);
		edownPayLabel.setForeground(gold);
		tpanelEditLoan.add(edownPayLabel);
		JTextField edownPayField = new JTextField();
		edownPayField.setFont(tnr);
		edownPayField.setBorder(tBorder);
		tpanelEditLoan.add(edownPayField);
		edownPayField.addActionListener(this);

		// Term label and text field
		eyearsLabel = new JLabel("Loan Term in Years: ");
		eyearsLabel.setFont(tnr);
		eyearsLabel.setForeground(gold);
		tpanelEditLoan.add(eyearsLabel);
		JTextField etermField = new JTextField();
		etermField.setFont(tnr);
		etermField.setBorder(tBorder);
		tpanelEditLoan.add(etermField);
		etermField.addActionListener(this);

		// Interest rate label and text field
		erateLabel = new JLabel("Interest Rate (Ex: 5 = 5%): ");
		erateLabel.setFont(tnr);
		erateLabel.setForeground(gold);
		tpanelEditLoan.add(erateLabel);
		JTextField erateField = new JTextField();
		erateField.setFont(tnr);
		erateField.setBorder(tBorder);
		tpanelEditLoan.add(erateField);
		erateField.addActionListener(this);

		// Monthly payment label and text field
		emonthlyPayLabel = new JLabel("Monthly Payment: ");
		emonthlyPayLabel.setFont(tnr);
		emonthlyPayLabel.setForeground(gold);
		tpanelEditLoan.add(emonthlyPayLabel);
		JTextField emonthlyPayField = new JTextField();
		emonthlyPayField.setFont(tnr);
		emonthlyPayField.setBorder(new LineBorder(black));
		tpanelEditLoan.add(emonthlyPayField);
		emonthlyPayField.setBackground(Color.lightGray);
		emonthlyPayField.setEditable(false);
		emonthlyPayField.addActionListener(this);

		// Accrued interest label and text field
		eaccruedInterestLabel = new JLabel("Accrued Interest: ");
		eaccruedInterestLabel.setFont(tnr);
		eaccruedInterestLabel.setForeground(gold);
		tpanelEditLoan.add(eaccruedInterestLabel);
		JTextField eaccruedInterestField = new JTextField();
		eaccruedInterestField.setFont(tnr);
		eaccruedInterestField.setBorder(new LineBorder(black));
		tpanelEditLoan.add(eaccruedInterestField);
		eaccruedInterestField.setBackground(Color.lightGray);
		eaccruedInterestField.setEditable(false);
		eaccruedInterestField.addActionListener(this);

		JLabel esaveLabel = new JLabel("Enter unique loan name");
		esaveLabel.setFont(tnr);
		esaveLabel.setForeground(gold);
		tpanelEditLoan.add(esaveLabel);
		JTextField esaveField = new JTextField();
		esaveField.setFont(tnr);
		esaveField.setBorder(tBorder);
		tpanelEditLoan.add(esaveField);

		ecButton = new JButton("Calculate");
		ecButton.addActionListener(e -> {
			eloan = (Double.parseDouble(eloanField.getText()));
			eyears = (Double.parseDouble(etermField.getText()));
			erate = (Double.parseDouble(erateField.getText()));
			edownPay = (Double.parseDouble(edownPayField.getText()));

			double emonthlyRate = (erate / 100.0) / 12;
			double etermsInMonths = eyears * 12;
			eloan -= edownPay;
			double emonthlyPayment = (emonthlyRate * eloan) / (1 - Math.pow((1 + emonthlyRate), -etermsInMonths));
			DecimalFormat x = new DecimalFormat("#.##");
			double emonthlyPaymentF = Double.valueOf(x.format(emonthlyPayment));
			emonthlyPayField.setText("");
			emonthlyPayField.setText(emonthlyPaymentF + "");

			double etermInMonths = eyears * 12;
			double etotalCost = emonthlyPayment * etermInMonths;
			double etotalInterestAccrued = etotalCost - eloan;
			etotalInterestAccrued = Double.valueOf(x.format(etotalInterestAccrued));

			eaccruedInterestField.setText("");
			eaccruedInterestField.setText((int) Math.round(etotalInterestAccrued) + "");
		});
		bpanelEditLoan.add(ecButton);

		eButton = new JButton("Save New Loan");
		eButton.addActionListener(e -> {
			if (emonthlyPayField.getText().equals("") || eaccruedInterestField.getText().equals("")) {
				esaveLabel.setText("Calculate your loan first.");
			} else if (esaveField.getText().equals("")) { // If loan name is empty
				esaveLabel.setText("Set a loan name first.");
			} else {
				loans = curr.getLoans();
				eloanName = esaveField.getText();
				System.out.println(esaveField.getText());
				eloan = (Double.parseDouble(eloanField.getText()));
				eyears = (Double.parseDouble(etermField.getText()));
				erate = (Double.parseDouble(erateField.getText()));
				edownPay = (Double.parseDouble(edownPayField.getText()));
				loans.add(new Loan(eloanName, eloan, eyears, erate, edownPay,
						Double.parseDouble(emonthlyPayField.getText()),
						Double.parseDouble(eaccruedInterestField.getText())));
				jtaViewLoan.append(loans.get(loans.size() - 1) + "\n");
				esaveField.setText("");
				eloanField.setText("");
				etermField.setText("");
				erateField.setText("");
				edownPayField.setText("");
				emonthlyPayField.setText("");
				eaccruedInterestField.setText("");
			}
		});
		bpanelEditLoan.add(eButton);

		prevButton = new JButton("Previous Loan");
		prevButton.addActionListener(e -> {
			if (iter == 0) {
				iter = loans.size() - 1;
			} else {
				iter--;
			}
			esaveField.setText(loans.get(iter).getLoanName());
			eloanField.setText(Double.toString(loans.get(iter).getLoan()));
			etermField.setText(Double.toString(loans.get(iter).getYears()));
			erateField.setText(Double.toString(loans.get(iter).getRate()));
			edownPayField.setText(Double.toString(loans.get(iter).getDownPay()));
			emonthlyPayField.setText(Double.toString(loans.get(iter).getMonthlyPayment()));
			eaccruedInterestField.setText(Double.toString(loans.get(iter).getTotalInterestAccrued()));

		});
		bpanelEditLoan.add(prevButton);

		nextButton = new JButton("Next Loan");
		nextButton.addActionListener(e -> {
			if (iter == loans.size() - 1) {
				iter = 0;
			} else {
				iter++;
			}
			esaveField.setText(loans.get(iter).getLoanName());
			eloanField.setText(Double.toString(loans.get(iter).getLoan()));
			etermField.setText(Double.toString(loans.get(iter).getYears()));
			erateField.setText(Double.toString(loans.get(iter).getRate()));
			edownPayField.setText(Double.toString(loans.get(iter).getDownPay()));
			emonthlyPayField.setText(Double.toString(loans.get(iter).getMonthlyPayment()));
			eaccruedInterestField.setText(Double.toString(loans.get(iter).getTotalInterestAccrued()));
		});
		bpanelEditLoan.add(nextButton);

		// calcMenu Button
		JButton bViewMenu = new JButton("View Loans");
		bViewMenu.addActionListener(e -> {
			vedCard.show(panelViewCont, "View");
		});

		bpanelEditLoan.add(bViewMenu);

		delButton = new JButton("Delete Loan");
		delButton.addActionListener(e -> {
			loans.remove(iter);
			if (iter != 0) {
				iter--;
			} else if (loans.size() == 0) {
				jtaViewLoan.setText("");
				for (int i = 0; i < loans.size(); i++) {
					jtaViewLoan.append(loans.get(i) + "\n");
				}
				vedCard.show(panelViewCont, "View");
				return;
			}
			esaveField.setText(loans.get(iter).getLoanName());
			eloanField.setText(Double.toString(loans.get(iter).getLoan()));
			etermField.setText(Double.toString(loans.get(iter).getYears()));
			erateField.setText(Double.toString(loans.get(iter).getRate()));
			edownPayField.setText(Double.toString(loans.get(iter).getDownPay()));
			emonthlyPayField.setText(Double.toString(loans.get(iter).getMonthlyPayment()));
			eaccruedInterestField.setText(Double.toString(loans.get(iter).getTotalInterestAccrued()));
		});
		bpanelEditLoan.add(delButton);

		panelEditLoan.add(tpanelEditLoan, BorderLayout.NORTH);
		panelEditLoan.add(bpanelEditLoan, BorderLayout.SOUTH);

		// END OF VIEW/EDIT LOAN

		// BUDGET PLANNER PANEL
		panelBudget.setBackground(maroon);

		JPanel tpanelBudget = new JPanel(new GridLayout(0, 2, 30, 30));
		tpanelBudget.setBackground(maroon);

		JPanel bpanelBudget = new JPanel(new GridLayout(0, 2, 30, 10));
		bpanelBudget.setBackground(maroon);

		incomeLabel = new JLabel("Expected monthly income");
		incomeLabel.setFont(tnr);
		incomeLabel.setForeground(gold);
		tpanelBudget.add(incomeLabel);
		incomeField = new JTextField();
		incomeField.setFont(tnr);
		incomeField.setBorder(tBorder);
		tpanelBudget.add(incomeField);
		incomeField.addActionListener(this);

		rentLabel = new JLabel("Rent per month: ");
		rentLabel.setFont(tnr);
		rentLabel.setForeground(gold);
		tpanelBudget.add(rentLabel);
		rentField = new JTextField();
		rentField.setFont(tnr);
		rentField.setBorder(tBorder);
		tpanelBudget.add(rentField);
		rentField.addActionListener(this);

		electricLabel = new JLabel("Electric bill per month: ");
		electricLabel.setFont(tnr);
		electricLabel.setForeground(gold);
		tpanelBudget.add(electricLabel);
		electricField = new JTextField();
		electricField.setFont(tnr);
		electricField.setBorder(tBorder);
		tpanelBudget.add(electricField);
		electricField.addActionListener(this);

		waterLabel = new JLabel("Water bill per month: ");
		waterLabel.setFont(tnr);
		waterLabel.setForeground(gold);
		tpanelBudget.add(waterLabel);
		waterField = new JTextField();
		waterField.setFont(tnr);
		waterField.setBorder(tBorder);
		tpanelBudget.add(waterField);
		waterField.addActionListener(this);

		foodLabel = new JLabel("Food and Groceries: ");
		foodLabel.setFont(tnr);
		foodLabel.setForeground(gold);
		tpanelBudget.add(foodLabel);
		foodField = new JTextField();
		foodField.setFont(tnr);
		foodField.setBorder(tBorder);
		tpanelBudget.add(foodField);
		foodField.addActionListener(this);

		subscriptionLabel = new JLabel("Re-occuring Monthly Subscriptions: ");
		subscriptionLabel.setFont(tnr);
		subscriptionLabel.setForeground(gold);
		tpanelBudget.add(subscriptionLabel);
		subscriptionField = new JTextField();
		subscriptionField.setFont(tnr);
		subscriptionField.setBorder(tBorder);
		tpanelBudget.add(subscriptionField);
		subscriptionField.addActionListener(this);

		expensesLabel = new JLabel("Total expenses per month: ");
		expensesLabel.setFont(tnr);
		expensesLabel.setForeground(gold);
		tpanelBudget.add(expensesLabel);
		expensesField = new JTextField();
		expensesField.setFont(tnr);
		expensesField.setBorder(new LineBorder(black));
		tpanelBudget.add(expensesField);
		expensesField.setBackground(Color.lightGray);
		expensesField.setEditable(false);
		expensesField.addActionListener(this);

		// calcMenu Button
		bCalcMenu3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcCard.show(panelCont, "1");
				panelCalcMenu.add(bPrivLoan, glCalcMenu);
				panelCalcMenu.add(bViewLoan, glCalcMenu);
				panelCalcMenu.add(bBudget, glCalcMenu);
			}
		});
		bpanelBudget.add(bCalcMenu3);

		// Button to calculate
		cButtonBudget = new JButton("Calculate");
		cButtonBudget.addActionListener(e -> {

			income = (Double.parseDouble(incomeField.getText()));
			rent = (Double.parseDouble(rentField.getText()));
			electric = (Double.parseDouble(electricField.getText()));
			water = (Double.parseDouble(waterField.getText()));
			food = (Double.parseDouble(foodField.getText()));
			subscription = (Double.parseDouble(subscriptionField.getText()));

			double expenses = (rent + electric + water + food + subscription) - income;
			DecimalFormat x = new DecimalFormat("#.##");
			double expensesF = Double.valueOf(x.format(expenses));
			expensesField.setText("");
			expensesField.setText("$" + expensesF + "");
		});
		bpanelBudget.add(cButtonBudget);

		// END BUDGET PLANNER

		TitledBorder budgetTitle = new TitledBorder("Budget Planner");
		budgetTitle.setTitleColor(gold);
		panelBudget.setBorder(budgetTitle);

		panelBudget.add(tpanelBudget, BorderLayout.CENTER);
		panelBudget.add(bpanelBudget, BorderLayout.SOUTH);

		return panelCont;
	}

	@Override
	public void actionPerformed(ActionEvent privateLoan) {
		loan = (Double.parseDouble(loanField.getText()));
		years = (Double.parseDouble(termField.getText()));
		rate = (Double.parseDouble(rateField.getText()));
		downPay = (Double.parseDouble(downPayField.getText()));

		double monthlyRate = (rate / 100.0) / 12;
		double termsInMonths = years * 12;
		loan -= downPay;
		double monthlyPayment = (monthlyRate * loan) / (1 - Math.pow((1 + monthlyRate), -termsInMonths));
		DecimalFormat x = new DecimalFormat("#.##");
		double monthlyPaymentF = Double.valueOf(x.format(monthlyPayment));
		monthlyPayField.setText("");
		monthlyPayField.setText(monthlyPaymentF + "");

		double termInMonths = years * 12;
		double totalCost = monthlyPayment * termInMonths;
		double totalInterestAccrued = totalCost - loan;
		totalInterestAccrued = Double.valueOf(x.format(totalInterestAccrued));

		accruedInterestField.setText("");
		accruedInterestField.setText((int) Math.round(totalInterestAccrued) + "");

	}

	public static void main(String[] args) {
	}

}
