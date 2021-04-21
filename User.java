//Swing Stuff
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

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

//maroon - 128,0,0
//gold - 252, 205, 53
public class User extends JFrame{
	private String userName; // have an error come up if not unique
	private String password; //encrypt  
	//private ArrayList<Account> userAccounts; //to hold account IDs of user accounts, arrayLists 
	private ArrayList<Calculation> LoanCalculations = new ArrayList<Calculation>();
	private ArrayList<Double> SavingCalculations = new ArrayList<Double>();
	
	//Editable Labels for Loan Calculations
	public JTextField lblMonthlyPayment = new JTextField(15);
	public JTextField lblInterestRate = new JTextField(5);
	public JTextField lblNumberOfMonths = new JTextField(4);
	CardLayout card;
	JPanel cardPanel;
	JButton jbtEditLoanCalc = new JButton("Edit Loan Calc");
	JButton jbtCreateLoanCalc = new JButton("New Loan Calc");
	JButton jbtAddAccount = new JButton ("Add Account");
	JPanel deleteLoanCalcPanel;
	JPanel addLoanCalcPanel;
	JPanel editLoanCalc;
	JPanel editLoanCalc2;
	JPanel addAccount;
	JButton jbtDeleteLoanCalc = new JButton("Delete Loan Calc");
	
	public JTextField loanCalc = new JTextField(15);
	private ArrayList<Account> userAccounts;	
	public User() {
		JPanel welcomeMenuPanel =  new JPanel(new GridLayout(5,5,5,5));
		
		//DeleteLoanCalc section
		
		//deleteLoanCalcPanel = new JPanel(new GridLayout(1,3));
		deleteLoanCalcPanel = new JPanel(new FlowLayout());
		JTextField Entered = new JTextField(30);
		JTextField Directions = new JTextField(50);
		Directions.setFont(new Font("TimesRoman",Font.PLAIN,30));
		Directions.setEditable(false);
		Directions.setText("Please enter the # of the loan you would like to delete. Press the 'ENTER' button to confirm your selection.");
		JButton jbtnEnter = new JButton("ENTER");
		jbtnEnter.setFont(new Font("TimesRoman",Font.PLAIN,30));
		jbtnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deleteLoanCalc(Integer.valueOf(Entered.getText()));
				//Put Exception for if not num here or if it isn't an index
			}
		});
		deleteLoanCalcPanel.add(jbtnEnter);
		
		deleteLoanCalcPanel.add(Directions);
		Entered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		deleteLoanCalcPanel.add(Entered);
		for(int x = 0; x<LoanCalculations.size(); x++) {
			String loanCalc = LoanCalculations.get(x).toString();
			JTextField loanCalcDisplay = new JTextField(15);
			loanCalcDisplay.setFont(new Font("TimesRoman",Font.PLAIN,20));
			loanCalcDisplay.setText("Loan Calculation #"+x+": "+loanCalc);
			deleteLoanCalcPanel.add(loanCalcDisplay);
		}
		
		JButton jbtnDeleteBack = new JButton("BACK");
		jbtnDeleteBack.setBackground(new Color(252, 205, 53));
		jbtnDeleteBack.setFont(new Font("TimesRoman",Font.PLAIN,30));
		deleteLoanCalcPanel.add(jbtnDeleteBack, BorderLayout.WEST);
		jbtnDeleteBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		Entered.setBackground(new Color(252, 205, 53));
		Directions.setBackground(new Color(252, 205, 53));
		jbtnEnter.setBackground(new Color(252, 205, 53));
		
		//AddLoanCalc Section
		addLoanCalcPanel = new JPanel(new FlowLayout());
		JTextField monthlyPayment = new JTextField(15);
		JTextField interestRate = new JTextField(15);
		JTextField numberOfMonths = new JTextField(15);
		for(int x = 0; x<LoanCalculations.size();x++) {
			JTextField temp = new JTextField(15);
			Calculation calc = LoanCalculations.get(x);
			temp.setText(calc.toString());
		}
		
		monthlyPayment.setText("Monthly Payment Amount");
		interestRate.setText("Interest Rate Amount (in decimal form");
		numberOfMonths.setText("Number of Months");
		monthlyPayment.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    monthlyPayment.setText("");
			  }
			});
		
		interestRate.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    interestRate.setText("");
			  }
			});
		
		numberOfMonths.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    numberOfMonths.setText("");
			  }
			});
		monthlyPayment.setFont(new Font("TimesRoman",Font.PLAIN,30));
		interestRate.setFont(new Font("TimesRoman",Font.PLAIN,30));
		numberOfMonths.setFont(new Font("TimesRoman",Font.PLAIN,30));
		JTextField addCalcDirections = new JTextField(20);
		addCalcDirections.setEditable(false);
		addCalcDirections.setText("Please Enter the Loan Information And Then Push The ENTER Button.");
		JButton jbtnAddCalcEnter = new JButton("ENTER");
		jbtnAddCalcEnter.setFont(new Font("TimesRoman",Font.PLAIN,30));
		jbtnAddCalcEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Calculation calc = new Calculation(Integer.valueOf(monthlyPayment.getText()),Double.parseDouble(interestRate.getText()),Integer.valueOf(numberOfMonths.getText()));
				monthlyPayment.setText("");
				interestRate.setText("");
				numberOfMonths.setText("");
				LoanCalculations.add(calc);
				
				//Put Exception for if not num here or if it isn't an index
			}
		});
		JButton jbtnAddBack = new JButton("BACK");
		jbtnAddBack.setBackground(new Color(252, 205, 53));
		jbtnAddBack.setFont(new Font("TimesRoman",Font.PLAIN,30));
		addLoanCalcPanel.add(jbtnAddBack, BorderLayout.WEST);
		jbtnAddBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		addLoanCalcPanel.add(addCalcDirections);
		addLoanCalcPanel.add(monthlyPayment);
		addLoanCalcPanel.add(interestRate);
		addLoanCalcPanel.add(numberOfMonths);
		addLoanCalcPanel.add(jbtnAddCalcEnter);
		
		addCalcDirections.setBackground(new Color(252, 205, 53));
		monthlyPayment.setBackground(new Color(252, 205, 53));
		interestRate.setBackground(new Color(252, 205, 53));
		numberOfMonths.setBackground(new Color(252, 205, 53));
		jbtnAddCalcEnter.setBackground(new Color(252, 205, 53));
		
		
		//Edit Loan Calc Section
		
		JTextField editEntered = new JTextField(15);
		editLoanCalc = new JPanel(new FlowLayout());
		JTextField editDirections = new JTextField(15);
		editDirections.setFont(new Font("TimesRoman",Font.PLAIN,30));
		editDirections.setEditable(false);
		editDirections.setText("Please enter the # of the loan you would like to edit. Press the 'ENTER' button to confirm your selection.");
		JButton jbtnEditEnter = new JButton("ENTER");
		jbtnEditEnter.setFont(new Font("TimesRoman",Font.PLAIN,30));
		jbtnEditEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Edit Calculations 2");
			}
		});
		editLoanCalc.add(jbtnEditEnter);
		editLoanCalc.add(editDirections);
		for(int x = 0; x<LoanCalculations.size(); x++) {
			String loanCalc = LoanCalculations.get(x).toString();
			JTextField loanCalcDisplay = new JTextField(30);
			loanCalcDisplay.setFont(new Font("TimesRoman",Font.PLAIN,20));
			loanCalcDisplay.setText("Loan Calculation #"+x+": "+loanCalc);
			editLoanCalc.add(loanCalcDisplay);
		}
		editEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		editEntered.setEditable(true);
		editLoanCalc.add(editEntered);
		
		editEntered.setBackground(new Color(252, 205, 53));
		editDirections.setBackground(new Color(252, 205, 53));
		jbtnEditEnter.setBackground(new Color(252, 205, 53));
		
		JButton jbtnEditBack = new JButton("BACK");
		jbtnEditBack.setBackground(new Color(252, 205, 53));
		jbtnEditBack.setFont(new Font("TimesRoman",Font.PLAIN,30));
		editLoanCalc.add(jbtnEditBack, BorderLayout.WEST);
		jbtnEditBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		
		//Edit Loan Calc (Second Part) Section
		
		editLoanCalc2 = new JPanel(new FlowLayout());
		JTextField edit2Instructions = new JTextField(40);
		edit2Instructions.setEditable(false);
		edit2Instructions.setText("Check which value you would like to edit. Enter the new value into the textbox and then press the ENTER button.");
		ButtonGroup editGroup = new ButtonGroup();    
		JRadioButton monthlyPaymentChoice = new JRadioButton("Monthly Payment");
		JRadioButton interestRateChoice = new JRadioButton("Interest Rate");
		JRadioButton numberOfMonthsChoice = new JRadioButton("Number of Months");
		
		editGroup.add(monthlyPaymentChoice);
		editGroup.add(interestRateChoice);
		editGroup.add(numberOfMonthsChoice);
		
		JButton jbtnEdit2Enter = new JButton("ENTER");
		JTextField newValue = new JTextField(15);
		jbtnEdit2Enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int x = Integer.valueOf(newValue.getText());
				Calculation temp = LoanCalculations.get(Integer.valueOf(editEntered.getText()));
				if(monthlyPaymentChoice.isSelected()) {
					temp.editMonthlyPayment(x);
					LoanCalculations.set(x,temp);
				}
				if(interestRateChoice.isSelected()) {
					temp.editInterestRate(x);
					LoanCalculations.set(x,temp);
				}
				if(numberOfMonthsChoice.isSelected()) {
					temp.editNumberOfMonths(x);
					LoanCalculations.set(x,temp);
				}
				
			}
		});
		JButton jbtnEdit2Back = new JButton("BACK");
		jbtnEdit2Back.setBackground(new Color(252, 205, 53));
		jbtnEdit2Back.setFont(new Font("TimesRoman",Font.PLAIN,30));
		editLoanCalc2.add(jbtnEdit2Back, BorderLayout.WEST);
		jbtnEdit2Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Edit Calculations");
			}
		});
		editLoanCalc2.add(edit2Instructions); 
		editLoanCalc2.add(monthlyPaymentChoice);
		editLoanCalc2.add(interestRateChoice);
		editLoanCalc2.add(numberOfMonthsChoice);
		editLoanCalc2.add(newValue);
		editLoanCalc2.add(jbtnEdit2Enter);
		
		monthlyPaymentChoice.setBackground(new Color(252, 205, 53));
		interestRateChoice.setBackground(new Color(252, 205, 53));
		numberOfMonthsChoice.setBackground(new Color(252, 205, 53));
		edit2Instructions.setBackground(new Color(252, 205, 53));
		jbtnEdit2Enter.setBackground(new Color(252, 205, 53));
		newValue.setBackground(new Color(252, 205, 53));
		
		//Add Account Section
		addAccount = new JPanel(new FlowLayout());
		JTextField accountInstructions = new JTextField(30);
		accountInstructions.setEditable(false);
		accountInstructions.setText("Please enter the specicied fields before clicking the ENTER button.");
		
		JTextField name = new JTextField(15);
		JTextField id = new JTextField(15);
		JTextField balance = new JTextField(15);
		name.setText("Name");
		id.setText("ID");
		balance.setText("Balance");
		name.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    name.setText("");
			  }
			});
		
		id.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    id.setText("");
			  }
			});
		
		balance.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    balance.setText("");
			  }
			});
		name.setFont(new Font("TimesRoman",Font.PLAIN,30));
		balance.setFont(new Font("TimesRoman",Font.PLAIN,30));
		id.setFont(new Font("TimesRoman",Font.PLAIN,30));
		
		JButton jbtnAccountEnter = new JButton("ENTER");
		jbtnAccountEnter.setFont(new Font("TimesRoman",Font.PLAIN,30));
		jbtnAccountEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				addAccounts(name.getText(),id.getText(), Integer.valueOf(balance.getText()));
			}
		});
		JButton jbtnAddAccountBack = new JButton("BACK");
		jbtnAddAccountBack.setBackground(new Color(252, 205, 53));
		jbtnAddAccountBack.setFont(new Font("TimesRoman",Font.PLAIN,30));
		addAccount.add(jbtnAddAccountBack, BorderLayout.WEST);
		jbtnAddAccountBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		
		addAccount.add(accountInstructions); 
		addAccount.add(name);
		addAccount.add(balance);
		addAccount.add(id);
		addAccount.add(jbtnAccountEnter);
		
		accountInstructions.setBackground(new Color(252, 205, 53));
		name.setBackground(new Color(252, 205, 53));
		balance.setBackground(new Color(252, 205, 53));
		id.setBackground(new Color(252, 205, 53));
		jbtnAccountEnter.setBackground(new Color(252, 205, 53));
		
		
		
		// Create card layout pane and add panels
				card = new CardLayout();
				cardPanel = new JPanel(card);
				cardPanel.add(welcomeMenuPanel, "Welcome");
				cardPanel.add(deleteLoanCalcPanel, "Delete Calculations");
				cardPanel.add(addLoanCalcPanel, "Add Calculations");
				cardPanel.add(editLoanCalc, "Edit Calculations");
				cardPanel.add(editLoanCalc2, "Edit Calculations 2");
				cardPanel.add(addAccount, "Add Account");
		card.show(cardPanel, "Welcome");
		deleteLoanCalcPanel.setBackground(new Color(128,0,0));  
		addLoanCalcPanel.setBackground(new Color(128,0,0));  
		editLoanCalc.setBackground(new Color(128,0,0));  
		editLoanCalc2.setBackground(new Color(128,0,0));  
		addAccount.setBackground(new Color(128,0,0));  
		setLocationRelativeTo(null); // center the frame
		loanCalc.setEditable(false);
		jbtEditLoanCalc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Edit Calculations");
			}
		});
		jbtCreateLoanCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Add Calculations");
				monthlyPayment.setText("Monthly Payment Amount");
				interestRate.setText("Interest Rate Amount (in decimal form");
				numberOfMonths.setText("Number of Months");
			}
		});
		jbtAddAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Add Account");
				for(int x = 0; x<LoanCalculations.size();x++) {
					JTextField temp = new JTextField(15);
					Calculation calc = LoanCalculations.get(x);
					temp.setText(calc.toString());
				}
			}
		});
		
		
		jbtDeleteLoanCalc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Delete Calculations");
			}
		});
		
		
		jbtEditLoanCalc.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtCreateLoanCalc.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtAddAccount.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtDeleteLoanCalc.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		loanCalc.setFont(new Font("TimesRoman",Font.BOLD, 20));
		
		lblMonthlyPayment.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		lblInterestRate.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		lblNumberOfMonths.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		JTextField WelcomeText = new JTextField();
		WelcomeText.setEditable(false);
		WelcomeText.setFont(new Font("TimesRoman", Font.BOLD, 30));
		WelcomeText.setHorizontalAlignment(JTextField.CENTER);
		WelcomeText.setBackground(new Color(252, 205, 53));
		WelcomeText.setText("Please Select The Option You Wish To Use");
		jbtEditLoanCalc.setBackground(new Color(252, 205, 53));
		jbtCreateLoanCalc.setBackground(new Color(252, 205, 53));
		jbtAddAccount.setBackground(new Color(252, 205, 53));
		jbtDeleteLoanCalc.setBackground(new Color(252, 205, 53));
		
		
		welcomeMenuPanel.add(WelcomeText);
		welcomeMenuPanel.add(jbtEditLoanCalc);
		welcomeMenuPanel.add(jbtCreateLoanCalc);
		welcomeMenuPanel.add(jbtAddAccount);
		welcomeMenuPanel.add(jbtDeleteLoanCalc);
		add(cardPanel);
		setTitle("Cup O'Java Financial Institute");
		setLocationRelativeTo(null); // center the frame
		welcomeMenuPanel.setBackground(new Color(128,0,0));  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
	}
	
	
	User(String user, String pass){
		userName = user;
		password = pass;
		LoanCalculations = new ArrayList<Calculation>();
		SavingCalculations = new ArrayList<Double>();
	}
	
	

	
	/*This method is likely unneeded
	public void displayLoanCalc(double loanCalcNUM){
		loanCalc.setVisible(true);
		loanCalc.setText("Loan Calculation: "+loanCalcNUM);
	}
	*/
	public void deleteLoanCalc(int index) {
		LoanCalculations.remove(index);
	}
	public void addAccounts(String name, String id, double balance) {
		//Account a = new Account(name, id, balance);
		//userAccounts.add(a);
		//commented out so I don't get an error thrown at me
	}
	
	public String getUsername() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	
	public static void main(String[] args) {
		JFrame frame = new User();

	}




}
