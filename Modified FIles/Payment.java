package All;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class Payment {

    private String payment;
    private int totalCost;
    private JButton payButton;

    private JFrame frame;
    private JFrame paymentFrame;

    private JLabel emailLabel;
    private JLabel nameLabel;
    private JLabel cardNumberLabel;
    private JLabel expiryDateLabel;
    private JLabel cvvLabel;

    private JTextField emailText;
    private JTextField nameText;
    private JTextField cardNumberText;
    private JTextField cvvText;

    private JComboBox dropDownMonth;
    private JComboBox dropDownYear;


    public Payment(String p, int cost) {

        payment = p;
        totalCost = cost;

        paymentFrame = new JFrame();
        JPanel panel = new JPanel();
        paymentFrame.setSize(320, 300);
        //panel.setLayout(new FlowLayout());
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLayout(new FlowLayout());
        paymentFrame.setTitle(payment);
        paymentFrame.add(panel);
        paymentFrame.setResizable(false);
        paymentFrame.setVisible(true);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 50, 40);
        paymentFrame.add(emailLabel);

        emailText = new JTextField(20);
        emailText.setBounds(200, 20, 165, 40);
        paymentFrame.add(emailText);

        nameLabel = new JLabel("Cardholder Name");
        nameLabel.setBounds(10, 60, 100, 40);
        paymentFrame.add(nameLabel);

        nameText = new JTextField(17);
        nameText.setBounds(200, 60, 165, 40);
        paymentFrame.add(nameText);

        cardNumberLabel = new JLabel("Card Number");
        cardNumberLabel.setBounds(10, 90, 100, 40);
        paymentFrame.add(cardNumberLabel);

        cardNumberText = new JTextField(18);
        cardNumberText.setBounds(200, 90, 165, 40);
        paymentFrame.add(cardNumberText);

        cvvLabel = new JLabel("CVV");
        cvvLabel.setBounds(10, 120, 100, 40);
        paymentFrame.add(cvvLabel);

        cvvText = new JTextField(5);
        cvvText.setBounds(200, 120, 200, 40);
        paymentFrame.add(cvvText);

        expiryDateLabel = new JLabel("Expiry Date");
        expiryDateLabel.setBounds(10, 150, 100, 40);
        paymentFrame.add(expiryDateLabel);

        String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] years = {"22","23","24","25","26","27","28","29","30"};
        dropDownMonth = new JComboBox(months);
        dropDownYear = new JComboBox(years);
        dropDownMonth.setPreferredSize(new Dimension(50, 30));
        dropDownYear.setPreferredSize(new Dimension(50, 30));


        dropDownMonth.addActionListener(new dropDownListener());
        dropDownYear.addActionListener(new dropDownListener());
        paymentFrame.add(dropDownMonth, BorderLayout.CENTER);
        paymentFrame.add(dropDownYear, BorderLayout.CENTER);
        paymentFrame.setVisible(true);

        payButton = new JButton("Pay $" + totalCost);
        payButton.addActionListener(new payListener());
        paymentFrame.add(payButton, BorderLayout.SOUTH);

    }

    private class dropDownListener implements ActionListener {      // Drop down box listener

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == dropDownMonth){
                String cardMonth = dropDownMonth.getSelectedItem().toString();
                System.out.println("Month: " + cardMonth);
            }

            if(e.getSource() == dropDownYear){
                String cardYear = dropDownYear.getSelectedItem().toString();
                System.out.println("Year: " + cardYear);

            }

        }

    }

    private class payListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            frame = new JFrame();
            frame.setSize(200, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            JLabel errorLabel = new JLabel("Payment completed for $" + totalCost + ". \n");
            errorLabel.setBounds(10, 20, 50, 40);
            frame.add(errorLabel);
            frame.setVisible(true);
            JButton returnButton = new JButton("Return");
            returnButton.setForeground(Color.BLACK);
            returnButton.setBackground(Color.WHITE);
            returnButton.addActionListener(new returnListener());
            frame.add(returnButton);
            // GUI that shows payment complete
            // 

        }
    }

    private class returnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            paymentFrame.dispose();
        }
    }
    
}
