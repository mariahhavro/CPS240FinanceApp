import java.util.ArrayList;
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
import java.io.File;
import java.io.FileNotFoundException;

public class Menu extends JFrame {


	CardLayout menuCard;
	JPanel menuCardPanel;
	JButton loginButton = new JButton("Login");
	JButton jbtcreateAccount = new JButton("Create Account");
	JButton jbtNewAccount= new JButton("Create Account");
	JButton jbtlogOut = new JButton ("Log out");
	JTextField newUserName = new JTextField();
	JTextField newPassword = new JTextField();
	JTextField loginUserName = new JTextField();
	JTextField loginPassword = new JTextField();
	JTextField invalidLogin = new JTextField(7);
	private ArrayList<User> users = loadUsers();

	public Menu() {
		JTabbedPane menuTabbedPane = new JTabbedPane();
		invalidLogin.setEditable(false);
		
		//set colors
		

		//create login page
		JComponent loginPage = createLoginPage();
		JComponent createAccountPage = createAccountPage();

		//add different classes for app into the menu tabbed pane

		//add accounts tab
		JComponent bankAccounts = new JPanel();
		menuTabbedPane.addTab("Bank Accounts", bankAccounts);
		// add debt management tab
		DebtManagement dm = new DebtManagement();
		JComponent dmTab = dm.DebtManagement();
		menuTabbedPane.addTab("Debt Management", dmTab);
		//add loan calculator tab
		SwingLoanCalc slc = new SwingLoanCalc();
		JComponent loanCalc = slc.LoanCalcTab();
		menuTabbedPane.addTab("Loan Calculator", loanCalc);
		// add credit score tab
		JComponent creditScore = CreditScoreSwing.CreditScoreTab();
		menuTabbedPane.addTab("Credit Score Ratings", creditScore);
		// add Log out tab
		JComponent logOut = logOutPage(jbtlogOut);
		menuTabbedPane.addTab("Log Out", logOut);


		//Create CardLayout
		menuCard = new CardLayout();
		menuCardPanel = new JPanel(menuCard);
		menuCardPanel.add(loginPage, "Login");
		menuCardPanel.add(menuTabbedPane, "Welcome");
		menuCardPanel.add(createAccountPage, " ");
		


		//Switch card when login button is pressed
		loginButton.addActionListener(new ActionListener() { //change to name of button
			public void actionPerformed(ActionEvent e) {

				if(existingUser(users, loginUserName.getText(), loginPassword.getText())) {
					loginUserName.setText("");
					loginPassword.setText("");
					menuCard.show(menuCardPanel, "Welcome"); 
				}
				else {
					loginUserName.setText("");
					loginPassword.setText("");
					invalidLogin.setText("Invalid Login");
				}

			}
		});

		//Switch card to create account when create account button is pressed
		jbtcreateAccount.addActionListener(new ActionListener() { //change to name of button
			public void actionPerformed(ActionEvent e) {

				menuCard.show(menuCardPanel, " "); //change to set name of card/panel added
			}
		});

		// Go to tabbed pane after new account is created
		jbtNewAccount.addActionListener(new ActionListener() { //change to name of button
			public void actionPerformed(ActionEvent e) {
				
				// Boolean to hold if the username entered is already taken
				boolean userTaken = false;
				
				// Loop to iterate for every user we have
				for (int h = 0; h < users.size(); h++) {
					// If the username of the current user matches the entered username for new user, change the boolean to reflect that
					if (users.get(h).getUsername().compareToIgnoreCase(newUserName.getText()) == 0) {
						userTaken = true;
					}
					
				}

				// If the username is taken, do this
				// Add in error message to show via swing, right now it only erases entries
				if (userTaken == true) {
					
				}
				// Otherwise, create a new user and add them to the database
				else {
					//create new user and add new User to new user list
					User newUser = new User(newUserName.getText(), newPassword.getText());
					users.add(newUser);
					//create new user .txt file
					newUser.newUserFile();
					//change to set name of card/panel added
					menuCard.show(menuCardPanel, "Welcome");				
					
				}
				
				// Clear entries
				newUserName.setText("");
				newPassword.setText("");
				
			}
		});
		// Return to log in page when log out button is pressed
		jbtlogOut.addActionListener(new ActionListener() { //change to name of button
			public void actionPerformed(ActionEvent e) {
				invalidLogin.setText("");
				menuCard.show(menuCardPanel, "Login"); //change to set name of card/panel added
			}
		});

		// get the frame read
		add(menuCardPanel);
		setTitle("Cup O'Java Financial Institute");
		setLocationRelativeTo(null); // center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
	}

	//Load list of users
	public ArrayList<User> loadUsers(){
		
		// Define empty list of users
		ArrayList<User> users = new ArrayList<User>();
		
		// Define a string as the absolute path to get to the current directory
		String currentDirectory = new File("").getAbsolutePath();
		
		// Defining a file directory as the current directory + the Users extension
		File dataF = new File(currentDirectory + "\\Users");
		
		// Creating an array holding all the names of all files within directory
		File filesList[] = dataF.listFiles();
		
		// Creating a loop to run through once for every file found
		for (int i = 0; i < filesList.length; i++) {
			
			// Variable to hold the line count for each file
			int j = 0;
			
			// Try block to catch exceptions
			try {

				// Defining a new scanner object to read in file from it's absolute path
				Scanner sc = new Scanner(new File(filesList[i].getAbsolutePath()));
				//System.out.println(filesList[i].getAbsolutePath());
				
				// Continue looping as long as there is a line to read in file
				while (sc.hasNext()) {
					
					// Defining a string from the line of data from file
					String st = sc.nextLine();
					// Splitting that string by commas into a string array
					String [] tokens = st.split(",");
					//System.out.println(tokens[0] + "	" + tokens[1]);
					
					// If this is the first line of the file, add a new user with the read username and password
					if (j == 0) {
						users.add(new User(tokens[0], tokens[1]));
					}
					// Otherwise, read in the account data and add that account to the users account that was loaded in from the first line
					else {
						users.get(i).addAccounts(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
					}
					
					// Increase the line count in file by one
					j++;
					
				}
				
			}catch (FileNotFoundException e) {
					// Catch block to catch FileNotFound exception and end program
					System.out.println(e.toString());
					return null;
			}
			
		}
		
		// Return list of created user accounts
		return users;
		
	}
	

	public JComponent createAccountPage() {

		JPanel loginPage = new JPanel();
		loginPage.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel loginGrid = new JPanel(new GridLayout(2,2));


		JLabel createAccount = new JLabel("                     Create Account                   ");
		createAccount.setFont(new Font("TimesRoman", Font.BOLD, 28));
		createAccount.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel jlbUserName = new JLabel("Username:      ");
		jlbUserName.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jlbUserName.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel jlbPassword = new JLabel("Password:      ");
		jlbPassword.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jlbPassword.setHorizontalAlignment(SwingConstants.RIGHT);

		//add components
		loginPage.add(createAccount);
		loginGrid.add(jlbUserName);
		loginGrid.add(newUserName);
		loginGrid.add(jlbPassword);
		loginGrid.add(newPassword);
		loginPage.add(loginGrid);
		loginPage.add(jbtNewAccount);

		return loginPage;
	}

	public JComponent createLoginPage() {

		JPanel loginPage = new JPanel();
		loginPage.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel loginGrid = new JPanel(new GridLayout(2,2));


		JLabel welcomeLabel = new JLabel("Welcome to the Cup O'Java Finance App!");
		welcomeLabel.setFont(new Font("TimesRoman", Font.BOLD, 28));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel jlbUserName = new JLabel("Username:      ");
		jlbUserName.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jlbUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel jlbPassword = new JLabel("Password:      ");
		jlbPassword.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		jlbPassword.setHorizontalAlignment(SwingConstants.RIGHT);


		//add components
		loginPage.add(welcomeLabel);
		loginGrid.add(jlbUserName);
		loginGrid.add(loginUserName);
		loginGrid.add(jlbPassword);
		loginGrid.add(loginPassword);
		loginPage.add(loginGrid);
		loginPage.add(loginButton);
		loginPage.add(invalidLogin);

		JLabel jlbcreateAccount = new JLabel("            Don't have an account? Create one below!                  "); 
		jlbcreateAccount.setFont(new Font("TimesRoman", Font.PLAIN, 22));
		jlbcreateAccount.setHorizontalAlignment(SwingConstants.CENTER);

		loginPage.add(jlbcreateAccount);
		loginPage.add(jbtcreateAccount);

		loginPage.setBackground(new Color(252, 205, 53));
		
		return loginPage;
	}

	public JComponent logOutPage(JButton jbtlogOut) {

		JPanel logOut = new JPanel();

		JLabel question = new JLabel("Click here to log out:");

		logOut.add(question);
		logOut.add(jbtlogOut);
		return logOut;
	}

	public static boolean existingUser(ArrayList<User> users, String username, String password) {
		for(int i=0; i<users.size();i++) {
			if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
				return true;
			}
		}
		return false;

	}
	public static void main(String[] args) {
		
		
		JFrame frame = new Menu();

	}


}
