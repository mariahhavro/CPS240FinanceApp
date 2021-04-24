import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 * This is GUI for the User class
 * @author Patrick Vietor
 *
 */
public class UserSwing extends JComponent{

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> selectAcc = new ArrayList<Account>();
	
	int[] selectedAcc = new int[2];
	
	CardLayout card;
	JPanel cardPanel;
	JPanel accGrid;
	
	JScrollPane scroll = new JScrollPane();
	
	JPanel homePage = new JPanel(new BorderLayout(0, 0));
	
	Color maroon = new Color(128, 0, 0);
	Color gold = new Color(252,205,53);
	
	int buttonsPressed;
	
	static User curr = new User();
	
	/**
	 * Method to set accounts
	 */
	public void setAccounts() {
		
		int y = 0;
		
		buttonsPressed = 0;
		
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        
		        Account button = new Account();
		        
		        for (int u = 0; u < curr.getAccounts().size(); u++) {
        			
        			if (abstractButton.getName().equalsIgnoreCase(curr.getAccounts().get(u).name)) {
        				button = curr.getAccounts().get(u);
        				
        			}
        			
        		}
        		
		        if (selected == true) {
		        	buttonsPressed++;
		        	
		        	if (buttonsPressed < 3) {

		        		selectAcc.add(button);
		        		
		        	}
		        	
		        	
		        }
		        else {
		        	buttonsPressed--;
		        	selectAcc.remove(button);
		        }
		        
		        if (buttonsPressed > 2) {
		        	abstractButton.setSelected(false);
		        	buttonsPressed--;
		        }
		        
		      }
		    };
		
		homePage.remove(scroll);
		
		if(curr.getAccounts().size() < 4) {
			y = 4 - curr.getAccounts().size();
		}
		
		accGrid = new JPanel(new GridLayout(curr.getAccounts().size() + y, 1));
		
		for (int i = 0; i < curr.getAccounts().size(); i++) {
			
			String label = String.format("%-60s$%-10.2f", curr.getAccounts().get(i).name, curr.getAccounts().get(i).balance);
			JToggleButton jtb = new JToggleButton(label);
			jtb.setName(curr.getAccounts().get(i).name);
			jtb.setPreferredSize(new Dimension(400, 85));
			jtb.addActionListener(actionListener);
			
			accGrid.add(jtb);
			
		}
		
		scroll = new JScrollPane(accGrid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		homePage.add(scroll, BorderLayout.CENTER);
	}
	
	/**
	 * JComponent for the tabbed pane used in the menu class
	 * @return UserSwing tab
	 */
	public JComponent UserSwing() {
		
		accounts = curr.getAccounts();

		JButton transfer = new JButton("Transfer");
		JButton deposit = new JButton("Deposit");
		JButton withd = new JButton("Withdrawal");
		JButton create = new JButton("Create Account");
		
		JPanel transPan = new JPanel(new BorderLayout());
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectAcc.size() != 2) {
					JOptionPane.showMessageDialog(homePage, "Please select two accounts to make a transfer between them.");
				}
				else {
					Object[] options = {selectAcc.get(0).name, selectAcc.get(1).name};
					int sel = JOptionPane.showOptionDialog(homePage,
							"Which account would you like to transfer money into?",
							"Transfer",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,     //do not use a custom Icon
							options,  //the titles of buttons
							options[0]); //default button title
					
					
					String q = String.format("How much would you like to deposit into %s?", selectAcc.get(sel).name);
					
					double depo = Double.parseDouble(JOptionPane.showInputDialog(homePage,
	                        q, null));
			
					if (sel == 1) {
						selectAcc.get(0).Withdraw(depo);
					}
					else {
						selectAcc.get(1).Withdraw(depo);
					}
					
					selectAcc.get(sel).Deposit(depo);
					
					
					selectAcc.clear();
					
					setAccounts();
				}
				
			}
		});
		
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (selectAcc.size() != 1) {
					
					JOptionPane.showMessageDialog(homePage, "Please select one account to make a deposit to.");
					
				}
				else {
					
					String q = String.format("How much would you like to deposit into %s?", selectAcc.get(0).name);
					
					double depo = Double.parseDouble(JOptionPane.showInputDialog(homePage,
	                        q, null));
					
					selectAcc.get(0).Deposit(depo);
					
					selectAcc.clear();
					
					setAccounts();
				}
				
			}
		});
		
		withd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (selectAcc.size() != 1) {
					
					JOptionPane.showMessageDialog(homePage, "Please select one account to make a withdrawal from.");
					
				}
				else {
					String q = String.format("How much would you like to withdraw from %s?", selectAcc.get(0).name);
					
					double depo = Double.parseDouble(JOptionPane.showInputDialog(homePage,
	                        q, null));
					
					selectAcc.get(0).Withdraw(depo);
					
					selectAcc.clear();
					
					setAccounts();
				}
				
			}
		});
		
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = JOptionPane.showInputDialog(homePage,
                        "What is the name of your new account?", null);
				curr.addAccounts(name, 0);
				
				setAccounts();
				
			}
			
			
			
			
		});
		
		JPanel optionGrid = new JPanel(new GridLayout(4, 1));
		
		optionGrid.add(transfer);
		optionGrid.add(deposit);
		optionGrid.add(withd);
		optionGrid.add(create);
		
		optionGrid.setPreferredSize(new Dimension(150, 20));
		
		homePage.add(optionGrid, BorderLayout.EAST);

		accGrid = new JPanel(new GridLayout());
		
		homePage.add(scroll, BorderLayout.WEST);
		
		card = new CardLayout();
		cardPanel = new JPanel(card);
		cardPanel.add(homePage, "Home");

		return cardPanel;
	}
	
	
}