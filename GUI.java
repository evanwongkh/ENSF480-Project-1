package All;
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class GUI {        // Opens a GUI that prompts the user for the number of each client type until user has completed its inputs.

    private JFrame frame;
    private JPanel panel;
    private JLabel emailLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createButton;
    private JButton guestButton;

    public GUI() {
        
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 50, 25);
        panel.add(emailLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        frame.setVisible(true);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(70, 90, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(new loginListener());
        panel.add(loginButton);

        createButton = new JButton("Create Account");
        createButton.setBounds(170, 90, 125, 25);
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(Color.BLACK);
        createButton.addActionListener(new createListener());
        panel.add(createButton);

        guestButton = new JButton("Proceed as Guest");
        guestButton.setBounds(110, 130, 150, 25);
        guestButton.setForeground(Color.BLACK);
        guestButton.setBackground(Color.WHITE);
        guestButton.addActionListener(new guestListener());
        panel.add(guestButton);

    }

    private class loginListener implements ActionListener {     // When login button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = userText.getText();
            String password = passwordField.getText();
            Registration newAccount = new Registration();
            boolean validUser = newAccount.validate(email, password);
            if(validUser == true) {
                searchGUI(email);
            }
            else {
                System.out.println("no");
                // Login false
            }
            
        }

    } 

    private class createListener implements ActionListener {    // When create account button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = userText.getText();
            String password = passwordField.getText();
            System.out.println(email + ", " + password);
            Registration newAccount = new Registration();
            newAccount.addUser(email, password);
            // NEED TO ADD
            // After account is created
        }

    } 

    private class searchListener implements ActionListener {    // When search button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SEARCH");
        }
    }

    private class guestListener implements ActionListener {     // When proceed as guest button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            searchGUI("");
        }

    }

    public void searchGUI(String title) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);


        frame.setTitle(title);
        frame.setVisible(true);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(110, 130, 150, 25);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(Color.WHITE);
        searchButton.addActionListener(new searchListener());
        panel.add(searchButton);

    }
                                
    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}