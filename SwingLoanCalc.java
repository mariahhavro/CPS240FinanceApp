package app;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SwingLoanCalc implements ActionListener{

    JPanel textPanel, panelForTextFields, completionPanel;
    JLabel titleLabel, loanLabel, yearsLabel, rateLabel, rate2Label, downPayLabel, downPayLabel2, monthlyPayLabel, accruedInterestLabel;
    JTextField loanField, termField, rateField, downPayField, monthlyPayField, accruedInterestField;
    JButton cButton;
    private double loan, years, rate, downPay, monthly;

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titleLabel = new JLabel("Cup O' Java | Loan Calculator");
        titleLabel.setLocation(110,0);
        titleLabel.setSize(180, 30);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);

        // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(10, 35);
        textPanel.setSize(180, 250);
        totalGUI.add(textPanel);

        // Loan Label
        loanLabel = new JLabel("Loan Amount");
        loanLabel.setLocation(0, 10);
        loanLabel.setSize(100, 30);
        loanLabel.setHorizontalAlignment(4);
        textPanel.add(loanLabel);
        
        downPayLabel = new JLabel("DownPayment: ");
        downPayLabel.setLocation(0,50);
        downPayLabel.setSize(100,30);
        downPayLabel.setHorizontalAlignment(4);
        textPanel.add(downPayLabel);

        // Term Label
        yearsLabel = new JLabel("# of Years");
        yearsLabel.setLocation(0, 130);
        yearsLabel.setSize(100, 30);
        yearsLabel.setHorizontalAlignment(4);
        textPanel.add(yearsLabel);
        
        rateLabel = new JLabel("Interest Rate");
        rateLabel.setLocation(0,90);
        rateLabel.setSize(100, 30);
        rateLabel.setHorizontalAlignment(4);
        textPanel.add(rateLabel);
        
        rate2Label = new JLabel("    Ex: (5 = 5%)");
        rate2Label.setLocation(0,105);
        rate2Label.setSize(100,30);
        rate2Label.setHorizontalAlignment(4);
        textPanel.add(rate2Label);
        
        monthlyPayLabel = new JLabel("Monthly Payment");
        monthlyPayLabel.setLocation(0,180);
        monthlyPayLabel.setSize(100,30);
        monthlyPayLabel.setHorizontalAlignment(4);
        textPanel.add(monthlyPayLabel);

        accruedInterestLabel = new JLabel("Accrued Interest");
        accruedInterestLabel.setLocation(0,220);
        accruedInterestLabel.setSize(100,30);
        accruedInterestLabel.setHorizontalAlignment(4);
        textPanel.add(accruedInterestLabel);
        
        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(250, 40);
        panelForTextFields.setSize(100, 240);
        totalGUI.add(panelForTextFields);

        // Loan amount Textfield
        loanField = new JTextField(8);
        loanField.setLocation(0, 0);
        loanField.setSize(100, 30);
        panelForTextFields.add(loanField);
        loanField.addActionListener(this);

        //Down Payment Textfield
        downPayField = new JTextField(8);
        downPayField.setLocation(0,40);
        downPayField.setSize(100,30);
        panelForTextFields.add(downPayField);
        downPayField.addActionListener(this);
        
        // Term in years Textfield
        termField = new JTextField(8);
        termField.setLocation(0, 120);
        termField.setSize(100, 30);
        panelForTextFields.add(termField);
        termField.addActionListener(this);
        
        //Rate Textfield
        rateField = new JTextField(8);
        rateField.setLocation(0, 80);
        rateField.setSize(100, 30);
        panelForTextFields.add(rateField);
        rateField.addActionListener(this);
        
        //monthlyPayField
        
        monthlyPayField = new JTextField(8);
        monthlyPayField.setEditable(false);
        monthlyPayField.setLocation(0,170);
        monthlyPayField.setSize(100,30);
        panelForTextFields.add(monthlyPayField);
        monthlyPayField.addActionListener(this);
        
        //accruedInterestField
        accruedInterestField = new JTextField(8);
        accruedInterestField.setEditable(false);
        accruedInterestField.setLocation(0,210);
        accruedInterestField.setSize(100,30);
        panelForTextFields.add(accruedInterestField);
        accruedInterestField.addActionListener(this);

        // Creation of a Panel to contain the completion JLabels
        completionPanel = new JPanel();
        completionPanel.setLayout(null);
        completionPanel.setLocation(40, 35);
        completionPanel.setSize(170, 230);
        totalGUI.add(completionPanel);

    
        // Button to calculate
        cButton = new JButton("Calculate");
        cButton.setLocation(130, 310);
        cButton.setSize(150, 30);
        cButton.addActionListener(this);
        totalGUI.add(cButton);

        totalGUI.setOpaque(true);    
        return totalGUI;
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
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
    	monthlyPayField.setText(monthlyPaymentF + "");
    	
    	double termInMonths = years * 12;
        double totalCost = monthlyPayment * termInMonths; 
        System.out.print("MP: " + monthlyPayment + " TIM: " + termInMonths + " TC: " + totalCost);
        double totalInterestAccrued = totalCost - loan;
        totalInterestAccrued = Double.valueOf(x.format(totalInterestAccrued));
    	
    	accruedInterestField.setText("");
    	accruedInterestField.setText((int) Math.round(totalInterestAccrued) + "");       
        
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Loan Calculator");

        SwingLoanCalc demo = new SwingLoanCalc();
        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}