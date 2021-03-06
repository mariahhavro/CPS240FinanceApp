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

import javax.swing.Timer;


import java.util.ArrayList;
import java.lang.Math;

//maroon - 128,0,0
//gold - 252, 205, 53
public class User extends JFrame{
	private String userName; // have an error come up if not unique
	private String password; //encrypt  
	CardLayout card;
	JPanel cardPanel;
	JButton jbtCreateAccount = new JButton("Create Account");
	JButton jbtDepositMoney = new JButton("Deposit Money");
	JButton jbtWithdrawMoney = new JButton ("Withdraw Money");
	JButton jbtTransferMoney = new JButton("Transfer Money Between Accounts");
	JPanel createAccountPanel;
	JPanel depositMoneyPanel;
	JPanel withdrawMoneyPanel;
	JPanel transferMoneyPanel;
	ArrayList<Account> Accounts = new ArrayList<Account>();
	
	public JTextField loanCalc = new JTextField(15);
	public User() {
		JPanel welcomeMenuPanel =  new JPanel(new GridLayout(5,5,5,5));
		
		//Deposit Money section
		depositMoneyPanel = new JPanel(new GridLayout(5,5,5,5));
		JTextField depositEntered = new JTextField(30);
		depositEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		depositEntered.setHorizontalAlignment(JTextField.CENTER);
		depositEntered.setText("Amount to Deposit");
		JTextField depositDirections = new JTextField(50);
		depositDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		depositDirections.setHorizontalAlignment(JTextField.CENTER);
		depositDirections.setEditable(false);
		depositDirections.setText("Please Enter The Amount of Money You Wish To Deposit");
		JTextField accountEntered = new JTextField(30);
		accountEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		accountEntered.setHorizontalAlignment(JTextField.CENTER);
		accountEntered.setText("Account Name");
		
		JButton depositEnter = new JButton("ENTER");
		depositEnter.setFont(new Font("TimesRoman",Font.BOLD,30));
		depositEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean notFound = true;
				for(int x = 0; x<Accounts.size(); x++){
				     Account temp = Accounts.get(x);
				  	  String tempName = temp.getName();
				     if(accountEntered.getText().equals(tempName)){
				    	 notFound = false;
				    	 try {
					         Accounts.get(x).Deposit(Double.parseDouble(depositEntered.getText()));
					         depositDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
					         depositDirections.setText("$"+depositEntered.getText()+" Deposited. Total Balance is $"+Accounts.get(x).getBalance());
				    	 }
				    	 catch(Exception E) {
				    		 depositDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
					         depositDirections.setText("Please enter a numeric value in the Amount Category");
				    	 }
				     }
				     else{
				    	 depositDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
				         depositDirections.setText("Account "+accountEntered.getText()+" not found");
				     }
				}
				if(notFound) {
					depositDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
					depositDirections.setText("Account "+accountEntered.getText()+" not found.");
				}
				if(Accounts.size()<=0) {
					depositDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
			         depositDirections.setText("No Accounts Available");
				}
				
				ActionListener taskPerformer = new ActionListener() {
		             public void actionPerformed(ActionEvent evt) {
		            	 depositDirections.setText("Please Enter The Amount of Money You Wish To Deposit");
		            	 depositDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		             }
		         };
		 		Timer timer = new Timer(3000 ,taskPerformer);
		 		timer.setRepeats(false);
	            timer.setCoalesce(true);
	            timer.start();
		 		accountEntered.setText("Account Name");
				depositEntered.setText("Balance");
				 
			}});
		depositEntered.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  depositEntered.setText("");
			  }
			});
		accountEntered.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  accountEntered.setText("");
			  }
			});
		depositMoneyPanel.add(depositDirections);
		depositMoneyPanel.add(accountEntered);
		depositMoneyPanel.add(depositEntered);
		depositMoneyPanel.add(depositEnter);
		
		JButton jbtnDepositDeleteBack = new JButton("BACK");
		jbtnDepositDeleteBack.setBackground(new Color(252, 205, 53));
		jbtnDepositDeleteBack.setFont(new Font("TimesRoman",Font.BOLD,30));
		depositMoneyPanel.add(jbtnDepositDeleteBack, BorderLayout.WEST);
		jbtnDepositDeleteBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		depositEntered.setBackground(new Color(252, 205, 53));
		depositDirections.setBackground(new Color(252, 205, 53));
		accountEntered.setBackground(new Color(252, 205, 53));
		depositEntered.setBackground(new Color(252, 205, 53));
		depositEnter.setBackground(new Color(252, 205, 53));
		
		
		
		//Withdraw Money Section
		
		withdrawMoneyPanel = new JPanel(new GridLayout(5,5,5,5));
		JTextField withdrawEntered = new JTextField(30);
		withdrawEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		withdrawEntered.setHorizontalAlignment(JTextField.CENTER);
		withdrawEntered.setText("Amount");
		withdrawEntered.setHorizontalAlignment(JTextField.CENTER);
		JTextField withdrawDirections = new JTextField(50);
		withdrawDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		withdrawDirections.setHorizontalAlignment(JTextField.CENTER);
		withdrawDirections.setEditable(false);
		withdrawDirections.setText("Please Enter The Amount of Money You Wish To Withdraw");
		JTextField accountChosen = new JTextField(30);
		accountChosen.setFont(new Font("TimesRoman",Font.PLAIN,20));
		accountChosen.setHorizontalAlignment(JTextField.CENTER);
		accountChosen.setText("Account Name");
		
		withdrawEntered.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    withdrawEntered.setText("");
			  }
			});
		accountChosen.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    accountChosen.setText("");
			  }
			});
		JButton withdrawEnter = new JButton("ENTER");
		withdrawEnter.setFont(new Font("TimesRoman",Font.BOLD,30));
		withdrawEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean notFound = true;
				for(int x = 0; x<Accounts.size(); x++){
				     Account temp = Accounts.get(x);
				  	 String tempName = temp.getName();
				     if(accountChosen.getText().equals(tempName)){
				    	 notFound = false;
				         try {
					    	 Accounts.get(x).Withdraw(Double.parseDouble(withdrawEntered.getText()));
					         withdrawDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
					         withdrawDirections.setText("$"+withdrawEntered.getText()+" Withdrawn. Total Balance is $"+Accounts.get(x).getBalance());
				         }
				         catch(Exception E) {
				        	 withdrawDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
					         withdrawDirections.setText("Please Enter A Numeric Number in the Amount Category");
				         }
				     } 
				}
				if(notFound) {
					withdrawDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
			        withdrawDirections.setText("Account "+accountChosen.getText()+" not found.");
				}
				if(Accounts.size()<=0) {
					withdrawDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
			        withdrawDirections.setText("No Accounts Created");
				}
				
				ActionListener taskPerformer = new ActionListener() {
		             public void actionPerformed(ActionEvent evt) {
		            	 withdrawDirections.setText("Please Enter The Amount of Money You Wish To Withdraw");
		            	 withdrawDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		             }
		         };
		 		Timer timer = new Timer(3000 ,taskPerformer);
		 		timer.setRepeats(false);
	            timer.setCoalesce(true);
	            timer.start();
		 		accountChosen.setText("Account Name");
				withdrawEntered.setText("Amount");
			}
		});
		withdrawMoneyPanel.add(withdrawDirections);
		
		withdrawEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		withdrawMoneyPanel.add(accountChosen);
		withdrawMoneyPanel.add(withdrawEntered);
		withdrawMoneyPanel.add(withdrawEnter);
		
		JButton jbtnWithdrawDeleteBack = new JButton("BACK");
		jbtnWithdrawDeleteBack.setBackground(new Color(252, 205, 53));
		jbtnWithdrawDeleteBack.setFont(new Font("TimesRoman",Font.BOLD,30));
		withdrawMoneyPanel.add(jbtnWithdrawDeleteBack, BorderLayout.WEST);
		jbtnWithdrawDeleteBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		withdrawEntered.setBackground(new Color(252, 205, 53));
		withdrawEnter.setBackground(new Color(252, 205, 53));
		accountChosen.setBackground(new Color(252, 205, 53));
		withdrawDirections.setBackground(new Color(252, 205, 53));
		withdrawEntered.setBackground(new Color(252, 205, 53));
		
		//Transfer Money Section
		transferMoneyPanel = new JPanel(new GridLayout(6,6,6,6));
		JTextField transferEntered = new JTextField(30);
		transferEntered.setHorizontalAlignment(JTextField.CENTER);
		transferEntered.setText("Amount to Transfer");
		JTextField transferDirections = new JTextField(50);
		transferDirections.setHorizontalAlignment(JTextField.CENTER);
		transferDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		transferDirections.setEditable(false);
		transferDirections.setText("Please Enter The Amount of Money You Wish To Transfer");
		JTextField accountOne = new JTextField(30);
		accountOne.setFont(new Font("TimesRoman",Font.PLAIN,20));
		accountOne.setHorizontalAlignment(JTextField.CENTER);
		accountOne.setText("Name of Account To Withdraw From");
		JTextField accountTwo = new JTextField(30);
		accountTwo.setFont(new Font("TimesRoman",Font.PLAIN,20));
		accountTwo.setHorizontalAlignment(JTextField.CENTER);
		accountTwo.setText("Name of Account To Deposit To");
		JButton transferEnter = new JButton("ENTER");
		transferEnter.setFont(new Font("TimesRoman",Font.BOLD,30));
		transferEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean notFound = true;
				for(int x = 0; x<Accounts.size(); x++){
				     Account temp = Accounts.get(x);
				  	 if(accountOne.getText().equals(temp.getName())){
				  		for(int y = 0; y<Accounts.size(); y++){
					  		Account temp2 = Accounts.get(y);
						  	if(accountTwo.getText().equals(temp2.getName())){
						  		notFound = false;
						  		try {
							  		temp.TransferFrom(Double.parseDouble(transferEntered.getText()),temp2);
							  		transferDirections.setText("Transfer Successful. "+temp.getName()+": $"+temp.getBalance()+" "+temp2.getName()+": $"+temp2.getBalance());
							  		transferDirections.setFont(new Font("TimesRoman",Font.BOLD,19));
						  		}
						  		catch(Exception E) {
							  		transferDirections.setText("Please Enter a Numeric Value For Amount");
							  		transferDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
						  		}
						  	}
					  	} 
				     }
				}
				if(notFound) {
					transferDirections.setText("Accounts Not Found");
					transferDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
				}
				ActionListener taskPerformer = new ActionListener() {
		             public void actionPerformed(ActionEvent evt) {
		            	 transferDirections.setText("Please Enter The Amount of Money You Wish To Transfer");
		            	 transferDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		             }
		         };
		 		Timer timer = new Timer(5000 ,taskPerformer);
		 		timer.setRepeats(false);
	            timer.setCoalesce(true);
	            timer.start();
	            accountOne.setText("Name of Account To Withdraw From");
	            accountTwo.setText("Name of Account To Deposit To");
	            transferEntered.setText("Amount to Transfer");
				 
			}});
		transferEntered.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  transferEntered.setText("");
			  }
			});
		accountOne.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  accountOne.setText("");
			  }
			});
		accountTwo.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  accountTwo.setText("");
			  }
			});
		
		JButton jbtnTransferDeleteBack = new JButton("BACK");
		jbtnTransferDeleteBack.setBackground(new Color(252, 205, 53));
		transferEnter.setBackground(new Color(252, 205, 53));
		jbtnTransferDeleteBack.setFont(new Font("TimesRoman",Font.BOLD,30));
		jbtnTransferDeleteBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
			
		});
		transferMoneyPanel.add(transferDirections);
		transferMoneyPanel.add(transferEntered);
		transferMoneyPanel.add(accountOne);
		transferMoneyPanel.add(accountTwo);
		transferMoneyPanel.add(transferEnter);
		transferEntered.setFont(new Font("TimesRoman",Font.PLAIN,20));
		transferMoneyPanel.add(jbtnTransferDeleteBack, BorderLayout.WEST);
		

		transferEntered.setBackground(new Color(252, 205, 53));
		transferDirections.setBackground(new Color(252, 205, 53));
		transferEntered.setBackground(new Color(252, 205, 53));
		accountOne.setBackground(new Color(252, 205, 53));
		accountTwo.setBackground(new Color(252, 205, 53));

		//Create Account Section
				//Needed Variables for account 
				/*String name;
				 *private String accName;
				 *double balance;
				 */
				
				
		createAccountPanel = new JPanel(new GridLayout(5,5,5,5));
		JTextField name = new JTextField(15);
		JTextField balance = new JTextField(15);
		
		JTextField createAccountDirections = new JTextField(30);
		createAccountDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		createAccountDirections.setHorizontalAlignment(JTextField.CENTER);
		createAccountDirections.setEditable(false);
		createAccountDirections.setText("Enter the Specified Fields Below to Create an Account");
		
		name.setText("Account Name");
		balance.setText("Balance");
		name.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			    name.setText("");
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
		JButton jbtncreateAccountEnter = new JButton("ENTER");
		jbtncreateAccountEnter.setFont(new Font("TimesRoman",Font.BOLD,30));
		jbtncreateAccountEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
				Account account = new Account(name.getText(),Double.parseDouble(balance.getText()));
				name.setText("");
				balance.setText("");
				Accounts.add(account);
				createAccountDirections.setFont(new Font("TimesRoman",Font.BOLD,30));
				createAccountDirections.setText("Account Created");
				}
				catch(Exception E) {
					createAccountDirections.setFont(new Font("TimesRoman",Font.BOLD,20));
					createAccountDirections.setText("Please Enter A Numeric Value for Balance");
				}
				
				ActionListener taskPerformer = new ActionListener() {
		             public void actionPerformed(ActionEvent evt) {
		            	 createAccountDirections.setText("Enter the Specified Fields Below to Create an Account");
		            	 createAccountDirections.setFont(new Font("TimesRoman",Font.PLAIN,20));
		             }
		         };
		 		Timer timer = new Timer(1000 ,taskPerformer);
		 		timer.setRepeats(false);
	            timer.setCoalesce(true);
	            timer.start();
		 		name.setText("Account Name");
				balance.setText("Balance");
		 		
		 		
				//Put Exception for if not balance isn't a double here or if it isn't an index
			}
		});
		JButton jbtnCreateAccountBack = new JButton("BACK");
		jbtnCreateAccountBack.setBackground(new Color(252, 205, 53));
		jbtnCreateAccountBack.setFont(new Font("TimesRoman",Font.BOLD,30));
		createAccountPanel.add(jbtnCreateAccountBack, BorderLayout.WEST);
		jbtnCreateAccountBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		createAccountPanel.add(createAccountDirections);
		createAccountPanel.add(name);
		createAccountPanel.add(balance);
		createAccountPanel.add(jbtncreateAccountEnter);
		createAccountPanel.add(jbtnCreateAccountBack);
		createAccountDirections.setBackground(new Color(252, 205, 53));
		name.setBackground(new Color(252, 205, 53));
		balance.setBackground(new Color(252, 205, 53));
		jbtncreateAccountEnter.setBackground(new Color(252, 205, 53));
		jbtnCreateAccountBack.setBackground(new Color(252, 205, 53));
		
		
		// Create card layout pane and add panels
				card = new CardLayout();
				cardPanel = new JPanel(card);
				cardPanel.add(welcomeMenuPanel, "Welcome");
				cardPanel.add(createAccountPanel, "Add Account");
				cardPanel.add(depositMoneyPanel, "Deposit Money");
				cardPanel.add(withdrawMoneyPanel, "Withdraw Money");
				cardPanel.add(transferMoneyPanel, "Transfer Money");
		card.show(cardPanel, "Welcome");
		depositMoneyPanel.setBackground(new Color(128,0,0));  
		createAccountPanel.setBackground(new Color(128,0,0));  
		withdrawMoneyPanel.setBackground(new Color(128,0,0));  
		transferMoneyPanel.setBackground(new Color(128,0,0));  
		setLocationRelativeTo(null); // center the frame
		loanCalc.setEditable(false);
		
		jbtDepositMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Deposit Money");
				accountEntered.setText("Account Name");
				depositEntered.setText("Balance");
			}
		});
		
		jbtCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Add Account");
				name.setText("Name of Account");
				balance.setText("Balance");
			}
		});
		
		jbtWithdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Withdraw Money");
				accountChosen.setText("Account Name");
				withdrawEntered.setText("Amount");
			}
		});
		
		jbtTransferMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"Transfer Money");
				accountOne.setText("Name of Account To Withdraw From");
	            accountTwo.setText("Name of Account To Deposit To");
	            transferEntered.setText("Amount to Transfer");
				
			}
		});
		jbtDepositMoney.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtCreateAccount.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtTransferMoney.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jbtWithdrawMoney.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		loanCalc.setFont(new Font("TimesRoman",Font.BOLD, 20));
		
		JTextField WelcomeText = new JTextField();
		WelcomeText.setEditable(false);
		WelcomeText.setFont(new Font("TimesRoman", Font.BOLD, 30));
		WelcomeText.setHorizontalAlignment(JTextField.CENTER);
		WelcomeText.setBackground(new Color(252, 205, 53));
		WelcomeText.setText("Please Select The Option You Wish To Use");
		jbtCreateAccount.setBackground(new Color(252, 205, 53));
		jbtDepositMoney.setBackground(new Color(252, 205, 53));
		jbtTransferMoney.setBackground(new Color(252, 205, 53));
		jbtWithdrawMoney.setBackground(new Color(252, 205, 53));
		
		
		
		welcomeMenuPanel.add(WelcomeText);
		welcomeMenuPanel.add(jbtCreateAccount);
		welcomeMenuPanel.add(jbtDepositMoney);
		welcomeMenuPanel.add(jbtWithdrawMoney);
		welcomeMenuPanel.add(jbtTransferMoney);
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
