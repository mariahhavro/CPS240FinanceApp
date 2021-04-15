import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class DebtManagementSwing extends JFrame {
	ArrayList<Debt> debts = new ArrayList<Debt>();
	ArrayList<Debt> orderedDebts = new ArrayList<Debt>();
	CardLayout card;
	JPanel cardPanel;
	JTextField jtfName = new JTextField();
	JTextField jtfValue = new JTextField();
	JTextField jtfRate = new JTextField();
	JTextField jtfName2 = new JTextField();
	JTextField jtfValue2 = new JTextField();
	JTextField jtfRate2 = new JTextField();
	JRadioButton jrbRate = new JRadioButton("Sort by rate", false);
	JRadioButton jrbDebt = new JRadioButton("Sort by Debt", false);
	JLabel jlbAvgRate = new JLabel();
	JLabel jlbTotalDebt = new JLabel();
	JTextArea jtadebtAction = new JTextArea();
	int iter = 0;
	double income = 0.0;
	double totalDebt = 0.0;
	double averageRate = 0.0;

	public DebtManagementSwing() {

		// Create a welcome panel (shown first on default)
		JPanel welcomeMenuPanel = new JPanel(new BorderLayout(5, 10));
		JLabel jlbWelcomeInstr = new JLabel("Please choose a debt management option:");
		welcomeMenuPanel.add(jlbWelcomeInstr, BorderLayout.NORTH);

		JButton jbtViewEdit = new JButton("View/Manage Debts");
		jbtViewEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "View/Edit");
			}
		});

		JButton jbtDebtActions = new JButton("Overall Debt Actions");
		jbtDebtActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Debt Action");
			}
		});

		JButton jbtDebtPayment = new JButton("Debt Payment Plans");
		jbtDebtPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Debt Payments");
			}
		});
		// welcome grid to hold main 3 buttons
		JPanel welcomeBtnGrid = new JPanel(new GridLayout(3, 1));
		welcomeBtnGrid.add(jbtViewEdit);
		welcomeBtnGrid.add(jbtDebtActions);
		welcomeBtnGrid.add(jbtDebtPayment);
		welcomeMenuPanel.add(welcomeBtnGrid, BorderLayout.CENTER);

		JButton jbtMainMenu = new JButton("Main Menu");
		welcomeMenuPanel.add(jbtMainMenu, BorderLayout.SOUTH);

		// navigation panel to be used on all panels but main
		JButton jbtDebtMenu1 = new JButton("Debt Manager Menu");
		JButton jbtMainMenu1 = new JButton("Main Menu");
		JPanel menuBtnGrid1 = new JPanel(new GridLayout(1, 2));
		menuBtnGrid1.add(jbtDebtMenu1);
		menuBtnGrid1.add(jbtMainMenu1);
		jbtDebtMenu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});

		// navigation panel to be used on all panels but main
		JButton jbtDebtMenu2 = new JButton("Debt Manager Menu");
		JButton jbtMainMenu2 = new JButton("Main Menu");
		JPanel menuBtnGrid2 = new JPanel(new GridLayout(1, 2));
		menuBtnGrid2.add(jbtDebtMenu2);
		menuBtnGrid2.add(jbtMainMenu2);
		jbtDebtMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});
		// navigation panel to be used on all panels but main
		JButton jbtDebtMenu3 = new JButton("Debt Manager Menu");
		JButton jbtMainMenu3 = new JButton("Main Menu");
		JPanel menuBtnGrid3 = new JPanel(new GridLayout(1, 2));
		menuBtnGrid3.add(jbtDebtMenu3);
		menuBtnGrid3.add(jbtMainMenu3);
		jbtDebtMenu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Welcome");
			}
		});


		// Create a panel to edit/view/add debts
		JPanel viewEditDebtsPanel = new JPanel(new BorderLayout());
		JLabel jlbViewEditInstr = new JLabel("Please select from list to edit, or choose add option");
		JButton jbtAdd = new JButton("Add");
		jbtAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Add Debt");
			}
		});
		JButton jbtEditDelete = new JButton("Edit/Delete");
		jbtEditDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "Edit/Delete Debt");
				iter = 0;
				jtfName2.setText(debts.get(iter).getName());
				jtfValue2.setText(Double.toString(debts.get(iter).getPresentValue()));
				jtfRate2.setText(Double.toString(debts.get(iter).getRate()));
			}
		});
		JTextArea jtaDebts = new JTextArea();
		jtaDebts.setEditable(false);
		viewEditDebtsPanel.add(jlbViewEditInstr, BorderLayout.NORTH);
		viewEditDebtsPanel.add(jbtAdd, BorderLayout.WEST);
		viewEditDebtsPanel.add(menuBtnGrid1, BorderLayout.SOUTH);
		viewEditDebtsPanel.add(jbtEditDelete, BorderLayout.EAST);
		viewEditDebtsPanel.add(jtaDebts, BorderLayout.CENTER);
		
		//Create a panel to add debts
		JPanel addDebtPanel = new JPanel(new BorderLayout());
		JLabel jlbAddDebtInstr = new JLabel("Please fill in the information below, then hit submit");
		JButton jbtCancel = new JButton("Cancel");
		JButton jbtSubmit = new JButton("Submit");
		addDebtPanel.add(jlbAddDebtInstr, BorderLayout.NORTH);
		JPanel canSubGrid = new JPanel(new GridLayout(1,2));
		canSubGrid.add(jbtCancel);
		jbtCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfName.setText("");
				jtfValue.setText("");
				jtfRate.setText("");
				card.show(cardPanel, "View/Edit");
			}
		});
		canSubGrid.add(jbtSubmit);
		jbtSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debts.add(new Debt(jtfName.getText(), Double.parseDouble(jtfValue.getText()), Double.parseDouble(jtfRate.getText())));
				jtaDebts.append(debts.get(debts.size() - 1) + "\n");
				jtfName.setText("");
				jtfValue.setText("");
				jtfRate.setText("");
				card.show(cardPanel, "View/Edit");

			}
		});
		addDebtPanel.add(canSubGrid, BorderLayout.SOUTH);
		JPanel addGrid = new JPanel(new GridLayout(3,2));
		JLabel jlbName = new JLabel("Name: ");
		JLabel jlbValue = new JLabel("Present Value Owed: ");
		JLabel jlbRate = new JLabel("Interest Rate: ");
		addGrid.add(jlbName);
		addGrid.add(jtfName);
		addGrid.add(jlbValue);
		addGrid.add(jtfValue);
		addGrid.add(jlbRate);
		addGrid.add(jtfRate);
		addDebtPanel.add(addGrid,BorderLayout.CENTER);

		
		//Create a panel to delete and edit debts
		JPanel editDeleteDebtPanel = new JPanel(new BorderLayout());
		JLabel jlbEditDeleteDebtInstr = new JLabel("Please fill in the information below, then hit submit");
		JButton jbtDone = new JButton("Done");
		JButton jbtEdit = new JButton("SaveChanges");
		JButton jbtDelete = new JButton("Delete");
		JButton jbtPrev = new JButton("<");
		jbtPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(iter ==0) {
					iter = debts.size() - 1;
				}else {
					iter--;
				}
				jtfName2.setText(debts.get(iter).getName());
				jtfValue2.setText(Double.toString(debts.get(iter).getPresentValue()));
				jtfRate2.setText(Double.toString(debts.get(iter).getRate()));
			}
		});
		JButton jbtNext = new JButton(">");
		jbtNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(iter == debts.size() -1) {
					iter = 0;
				}else {
					iter++;
				}
				jtfName2.setText(debts.get(iter).getName());
				jtfValue2.setText(Double.toString(debts.get(iter).getPresentValue()));
				jtfRate2.setText(Double.toString(debts.get(iter).getRate()));
			}
		});
		editDeleteDebtPanel.add(jlbEditDeleteDebtInstr, BorderLayout.NORTH);
		editDeleteDebtPanel.add(jbtPrev, BorderLayout.WEST);
		editDeleteDebtPanel.add(jbtNext, BorderLayout.EAST);
		JPanel canSubGrid2 = new JPanel(new GridLayout(1,3));
		canSubGrid2.add(jbtDone);
		jbtDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update the textArea, then return to View/Edit Card
				jtaDebts.setText("");
				for(int i = 0; i < debts.size(); i++) {
					jtaDebts.append(debts.get(i) + "\n");
				}
				card.show(cardPanel, "View/Edit");
			}
		});
		canSubGrid2.add(jbtEdit);
		canSubGrid2.add(jbtDelete);
		jbtEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debts.get(iter).setName(jtfName2.getText());
				debts.get(iter).setPresentValue(Double.parseDouble(jtfValue2.getText()));
				debts.get(iter).setRate(Double.parseDouble(jtfRate2.getText()));
			}
		});
		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove from ArrayList Debts, then perform "Next" action
				debts.remove(iter);
				if(iter == debts.size() -1) {
					iter = 0;
				}else {
					iter++;
				}
				jtfName2.setText(debts.get(iter).getName());
				jtfValue2.setText(Double.toString(debts.get(iter).getPresentValue()));
				jtfRate2.setText(Double.toString(debts.get(iter).getRate()));
			}
		});
		editDeleteDebtPanel.add(canSubGrid2, BorderLayout.SOUTH);
		JPanel editDeleteGrid = new JPanel(new GridLayout(3,2));
		JLabel jlbName2 = new JLabel("Name: ");
		JLabel jlbValue2 = new JLabel("Present Value Owed: ");
		JLabel jlbRate2 = new JLabel("Interest Rate: ");
		editDeleteGrid.add(jlbName2);
		editDeleteGrid.add(jtfName2);
		editDeleteGrid.add(jlbValue2);
		editDeleteGrid.add(jtfValue2);
		editDeleteGrid.add(jlbRate2);
		editDeleteGrid.add(jtfRate2);
		editDeleteDebtPanel.add(editDeleteGrid,BorderLayout.CENTER);
		
		// Create a panel to determine debt actions
		JPanel debtActionPanel = new JPanel(new BorderLayout());
		JLabel jlbIncome = new JLabel("Please enter income and submit to recieve advise on extreme debt actions:");
		JButton jbtIncomeSubmit = new JButton("Submit");
		JTextField jtfIncome = new JTextField();
		jbtIncomeSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtadebtAction.setText(determineTotalDebtActions());
				jlbTotalDebt.setText("Total Debt: " + Double.toString(totalDebt));
				jlbAvgRate.setText("Average Interest Rate from Debts: " + Double.toString(averageRate));
			}
		});
		JTextArea jtadebtActionInstr = new JTextArea(
				"Below, your total debt and average interest rate will be displayed. This is calculated"
						+ " from the debts you have added to the debt management module.");
		jtadebtActionInstr.setLineWrap(true);
		jtadebtActionInstr.setEditable(false);
		jtadebtAction.setLineWrap(true);
		jtadebtAction.setEditable(false);
		JPanel incomeGrid = new JPanel(new GridLayout(3,1));
		incomeGrid.add(jlbIncome);
		incomeGrid.add(jtfIncome);
		incomeGrid.add(jbtIncomeSubmit);
		JPanel totalsGrid = new JPanel(new GridLayout(2,1));
		totalsGrid.add(jlbTotalDebt);
		totalsGrid.add(jlbAvgRate);
		debtActionPanel.add(jtadebtActionInstr, BorderLayout.NORTH);
		debtActionPanel.add(totalsGrid, BorderLayout.EAST);
		debtActionPanel.add(incomeGrid, BorderLayout.WEST);
		debtActionPanel.add(jtadebtAction, BorderLayout.CENTER);
		debtActionPanel.add(menuBtnGrid2, BorderLayout.SOUTH);

		// Create a panel to show the order to pay off debts
		JPanel debtPaymentsPanel = new JPanel(new BorderLayout());
		JLabel jlbdebtPaymentInstr = new JLabel(
				"Please select a debt payment priority, then your debts will be ordered in which to focus on paying first");
		debtPaymentsPanel.add(jlbdebtPaymentInstr, BorderLayout.NORTH);
		JTextArea jtaOrderedDebts = new JTextArea();
		debtPaymentsPanel.add(jtaOrderedDebts, BorderLayout.EAST);
		debtPaymentsPanel.add(menuBtnGrid3, BorderLayout.SOUTH);
		JButton jbtSort = new JButton("Sort");
		JPanel jrbGrid = new JPanel(new GridLayout(3, 1));
		jrbGrid.add(jrbRate);
		jrbGrid.add(jrbDebt);
		jrbGrid.add(jbtSort);
		jbtSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderedDebts = determineDebtPaymentPlan();
				jtaOrderedDebts.setText("");
				for(int i = 0; i < orderedDebts.size(); i++) {
					jtaOrderedDebts.append(orderedDebts.get(i) + "\n");
				}
				
			}
		});
		debtPaymentsPanel.add(jrbGrid, BorderLayout.WEST);

		// Create card layout pane and add panels
		card = new CardLayout();
		cardPanel = new JPanel(card);
		cardPanel.add(welcomeMenuPanel, "Welcome");
		cardPanel.add(viewEditDebtsPanel, "View/Edit");
		cardPanel.add(debtActionPanel, "Debt Action");
		cardPanel.add(debtPaymentsPanel, "Debt Payments");
		cardPanel.add(addDebtPanel, "Add Debt");
		cardPanel.add(editDeleteDebtPanel, "Edit/Delete Debt");

		// add to frame
		add(cardPanel);
		setTitle("Debt Manager");
		setLocationRelativeTo(null); // center the frame
		setSize(320, 250);
		setVisible(true);
	}

	public class Debt {
		String name;
		double presentValue;
		double rate;

		public Debt() {
			name = "";
			presentValue = 0.0;
			rate = 0.0;
		}

		public Debt(String name, double presentValue, double rate) {
			super();
			this.name = name;
			this.presentValue = presentValue;
			this.rate = rate;
		}

		public double getPresentValue() {
			return presentValue;
		}

		public void setPresentValue(Double presentValue) {
			this.presentValue = presentValue;
		}
		
		public double getRate() {
			return rate;
		}
		
		public void setRate(Double rate) {
			this.rate = rate;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "Name: " + name + " | Amount Owed: " + presentValue + " | Interest Rate: " + rate;
		}
	}
	
	// determine two methods of paying off loans, smallest debts first, or highest
		// rate first. Returns list
		public ArrayList<Debt> determineDebtPaymentPlan() {
			// initialize lists to be returned, will only return one.
			ArrayList<Debt> loansByRate = debts;
			ArrayList<Debt> loansByDebt = debts;

			

			// Sort by smallest debts using selection sort, return that ordered list
			if (jrbRate.isSelected()) {
				for (int i = 0; i < loansByDebt.size() - 1; i++) {
					int minIndex = i;
					for (int j = i + 1; j < loansByDebt.size(); j++) {
						if ((loansByDebt.get(j)).getPresentValue() < (loansByDebt.get(minIndex)).getPresentValue()) {
							minIndex = j;
						}
					}
					if (minIndex != i) {
						Debt temp = new Debt(); // temporary variable for swapping
						temp = loansByDebt.get(minIndex);
						loansByDebt.set(minIndex, loansByDebt.get(i));
						loansByDebt.set(i, temp);
					}
				}
				return loansByDebt;
			}
			// Sort by highest rates using selection sort, return that ordered list
			else if(jrbDebt.isSelected()) {
				for (int i = 0; i < loansByRate.size() - 1; i++) {
					int minIndex = i;
					for (int j = i + 1; j < loansByRate.size(); j++) {
						if ((loansByRate.get(j)).getRate() < (loansByRate.get(minIndex)).getRate()) {
							minIndex = j;
						}
					}
					if (minIndex != i) {
						Debt temp = new Debt(); // temporary variable for swapping
						temp = loansByRate.get(minIndex);
						loansByRate.set(minIndex, loansByRate.get(i));
						loansByRate.set(i, temp);
					}
				}
				return loansByRate;
			}
			else {
				return loansByRate; //have an error message to choose one
			}
		}
		
		public String determineTotalDebtActions() {
			double sumRate = 0.0;
			double extremeDebtPerc = 0.46;
			double extremeRate = 40.0;
			

			// Add up total loan values
			for (int i = 0; i < debts.size(); i++) {
				totalDebt += debts.get(i).getPresentValue();
				sumRate += debts.get(i).getRate();
			}
			averageRate = sumRate / debts.size();

			if (totalDebt >= income*extremeDebtPerc) {
				return "Due to the high amount of total debt, it is recommended to look into filing for"
						+ " Bankruptcy. This is recommended even further if you are in danger of foreclosure"
						+ " or are begin harassed by bill collectors.";
			}else if (averageRate >= extremeRate) {
				return "Due to the high average rate of interest from all of your debt, it is recommended to look into getting"
						+ " a consolidation loan. This is recommended even further if you are have very high monthly payments"
						+ " on your current loans.";
			}else {
				return "With the current debts you have entered, it is not recommeneded for you to file for Bankruptcy"
						+ " or get a consolidation loan.";
			}
		}
	
	public static void main(String[] args) {
		JFrame frame = new DebtManagementSwing(); 

	}

}
