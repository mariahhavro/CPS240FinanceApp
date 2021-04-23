
//I still plan to add visuals and an option to view saved loan, add this updated panel to see the current state!
//I'm also going to remove the Auto Loan Calculator, in its place I will have a panel that...
//	allows you to view and edit saved loans
//-Dylan

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SwingLoanCalc extends JFrame implements ActionListener{

//Colors Icons and fonts
Color gold = new Color(252,205,53);
Color maroon = new Color(128,0,0);
Color black = new Color(0,0,0);

Font tnr = new Font("TimesNewRoman",20, 20);

//icons downloaded from icons8.com
Icon home = new ImageIcon("icons8-home-128.png");
Icon auto = new ImageIcon("icons8-suv-100.png");		//REMOVE ICON
Icon budget = new ImageIcon("icons8-money-96.png");

// Calc menu JComponents
CardLayout calcCard = new CardLayout();
GridLayout glCalcMenu = new GridLayout(3,2,10,25);
LineBorder bBorder = new LineBorder(gold,5);
LineBorder tBorder = new LineBorder(gold,3);

JPanel panelCont = new JPanel(); 			// Panel container for entire tab
JPanel panelCalcMenu = new JPanel();		// Menu for calculators
JPanel panelPrivLoan = new JPanel(new BorderLayout());	// Private Loan Calculator
JPanel panelViewLoan = new JPanel(new BorderLayout());	// View saved loans
JPanel panelBudget = new JPanel(new BorderLayout());	// Budget Planner

JButton bCalcMenu1 = new JButton("Return to loan menu");
JButton bCalcMenu2 = new JButton("Return to loan menu");
JButton bCalcMenu3 = new JButton("Return to loan menu");

//Buttons with icons
JButton bPrivLoan = new JButton(home);
JButton bViewLoan = new JButton(auto); //Change icon!!!!!!
JButton bBudget = new JButton(budget);


//Private Loan Calculator JComponents and Global Variables
JPanel bpanelPrivLoan, tpanelPrivLoan;
JLabel titleLabel, loanLabel, yearsLabel, rateLabel, downPayLabel, downPayLabel2, monthlyPayLabel, accruedInterestLabel;
JTextField loanField, termField, rateField, downPayField, monthlyPayField, accruedInterestField;
JButton cButton;
private double loan, years, rate, downPay;

//Auto Loan Calculator JComponents and Global Variables


//Budget Planner JComponents and Global Variables
JPanel mypanelBudget, tpanelBudget, bpanelBudget;
JLabel rentLabel, electricLabel, waterLabel, incomeLabel, foodLabel, subscriptionLabel, expensesLabel;
JTextField rentField, electricField, waterField, incomeField, foodField, subscriptionField, expensesField;
JButton cButtonBudget;
private double rent, electric, water, income, food, subscription;



public JComponent LoanCalcTab() {
	panelCont.setLayout(calcCard);
	
	bCalcMenu1.setFont(tnr);
	bCalcMenu2.setFont(tnr);
	bCalcMenu3.setFont(tnr);
	
	bPrivLoan.setBorder(bBorder);
	bPrivLoan.setForeground(maroon);
	bPrivLoan.setText("Personal Loan Calculator");
	bPrivLoan.setFont(tnr);
	
	bViewLoan.setBorder(bBorder);
	bViewLoan.setForeground(maroon);
	bViewLoan.setText("Auto Loan Calculator");
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
	
	panelCont.add(panelCalcMenu,"1");
	panelCont.add(panelPrivLoan,"2");
	panelCont.add(panelViewLoan,"3");
	panelCont.add(panelBudget,"4");
	calcCard.show(panelCont, "1");
	
	
	bPrivLoan.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calcCard.show(panelCont,"2");
		}
	});
	bViewLoan.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calcCard.show(panelCont,"3");
			bCalcMenu2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					calcCard.show(panelCont,"1");
					panelCalcMenu.add(bPrivLoan, glCalcMenu);
					panelCalcMenu.add(bViewLoan, glCalcMenu);
					panelCalcMenu.add(bBudget, glCalcMenu);
				}
			});
		    panelViewLoan.add(bCalcMenu2);
		}
	});
	bBudget.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calcCard.show(panelCont,"4");
		}
	});
	//END of cardMenu
	
	//PRIVATE LOAN CALC
	
	//Panel for private loan calculator
	//panelPrivLoan || JPanel || BorderLayout()
    JPanel tpanelPrivLoan = new JPanel(new GridLayout(0,2,30,30));
    JPanel bpanelPrivLoan = new JPanel(new GridLayout(0,2,30,10));
    
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

    // calcMenu Button
    bCalcMenu1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calcCard.show(panelCont,"1");
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
    
    panelPrivLoan.add(tpanelPrivLoan,BorderLayout.CENTER);
    panelPrivLoan.add(bpanelPrivLoan,BorderLayout.SOUTH);
    
    bpanelPrivLoan.setBackground(maroon);
    tpanelPrivLoan.setBackground(maroon);
    
    TitledBorder privLoanTitle = new TitledBorder("Private Loan Calculator");
    privLoanTitle.setTitleColor(gold);
    panelPrivLoan.setBorder(privLoanTitle);
    
    panelPrivLoan.setBackground(maroon);
    panelPrivLoan.setOpaque(true);   
    //END OF PRIVATE LOAN PANEL
    
    //SAVED LOANS PANEL
	
    //Budget Panel
    panelBudget.setBackground(maroon);
	
	JPanel tpanelBudget = new JPanel(new GridLayout(0,2,30,30));
	tpanelBudget.setBackground(maroon);
	
	JPanel bpanelBudget = new JPanel(new GridLayout(0,2,30,10));
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
			calcCard.show(panelCont,"1");
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
	
	TitledBorder budgetTitle = new TitledBorder("Budget Planner");
    budgetTitle.setTitleColor(gold);
    panelBudget.setBorder(budgetTitle);
	
    panelBudget.add(tpanelBudget,BorderLayout.CENTER);
    panelBudget.add(bpanelBudget,BorderLayout.SOUTH);
    
	return panelCont;
}

@Override
public void actionPerformed(ActionEvent privateLoan) {
	loan = (Double.parseDouble(loanField.getText()));
	years = (Double.parseDouble(termField.getText()));
	rate = (Double.parseDouble(rateField.getText()));
	downPay = (Double.parseDouble(downPayField.getText()));
	
	double monthlyRate = (rate/100.0) / 12;
    double termsInMonths = years * 12;
    loan -= downPay;
    double monthlyPayment = (monthlyRate * loan) / (1 - Math.pow((1 + monthlyRate), -termsInMonths));
    DecimalFormat x = new DecimalFormat("#.##");
	double monthlyPaymentF = Double.valueOf(x.format(monthlyPayment));
	monthlyPayField.setText("");
	monthlyPayField.setText("$" + monthlyPaymentF + "");
	
	double termInMonths = years * 12;
    double totalCost = monthlyPayment * termInMonths; 
    System.out.print("MP: " + monthlyPayment + " TIM: " + termInMonths + " TC: " + totalCost);
    double totalInterestAccrued = totalCost - loan;
    totalInterestAccrued = Double.valueOf(x.format(totalInterestAccrued));
	
	accruedInterestField.setText("");
	accruedInterestField.setText("$" + (int) Math.round(totalInterestAccrued) + "");       
    
}

public static void main(String[] args) {

}

	
}
	

