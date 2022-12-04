package Presentation;
import javax.swing.*;

import Database.Movies;
import Database.Registration;
import Database.Ticket;
import Theatre.Payment;
import Theatre.Seats;

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
    private JButton showtimeButton;
    private JFrame showtimeFrame;

    private JFrame cancelFrame;
    private JFrame movieFrame;

    private boolean validUser;

    private JButton noButton;
    private JLabel confirmLabel;
    private JLabel cancelLabel;
    private JButton yesButton;
    private JButton okButton;

    // Movie Database variables
    private Movies movieDB;
    private ArrayList<String> movieList;
    private ArrayList<String> showTimes;
    private ArrayList<JButton> buttonList;

    // Ticket Database variables
    private Ticket ticketDB;
    private ArrayList<String> ticketSeatsList;
    private ArrayList<String> ticketMovieList;
    private ArrayList<String> ticketshowTimeList;
    private ArrayList<String> ticketPriceList;
    private ArrayList<Integer> ticketIDList;

    private String selectedMovie;
    private String selectedShowtime;
    private Seats seatsGUI;

    private Payment annualPayment;

    public GUI() {      // Initializes login GUI
        
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
        guestButton.setBounds(100, 125, 165, 25);
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
            validUser = newAccount.validate(email, password);
            if(validUser == true) {
                searchGUI(email);
            }
            else {
                JFrame frame = new JFrame();
                frame.setSize(350, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                JLabel errorLabel = new JLabel("ERROR: Incorrect email or password");
                errorLabel.setBounds(10, 20, 50, 40);
                frame.add(errorLabel);
                frame.setVisible(true);
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

            if(newAccount.flag != 1) {

                annualPayment = new Payment("Annual fee payment", 20);

            }
            else {
                JFrame frame = new JFrame();
                frame.setSize(350, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                JLabel errorLabel = new JLabel("ERROR: User already exists");
                errorLabel.setBounds(10, 20, 50, 40);
                frame.add(errorLabel);
                frame.setVisible(true);

            }
            // NEED TO ADD
            // After account is created
        }

    } 
    
    private class guestListener implements ActionListener {     // When proceed as guest button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            searchGUI("");
        }

    }

    public void searchGUI(String title) {       // Displays the movies in movies table in the database

        movieFrame = new JFrame();
        JPanel panel = new JPanel();
        movieFrame.setSize(500, 300);
        movieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieFrame.add(panel);
        panel.setLayout(new GridLayout(3, 2));
        movieFrame.setTitle(title);
        movieFrame.setVisible(true);

        // Retrieve list of movies and showtimes from database
        movieDB = new Movies();
        movieList = movieDB.getMovies();
        showTimes = movieDB.getShowtimes();

        buttonList = new ArrayList<JButton>();

        // Loop to display movies in GUI to user
        for(int i = 0; i < movieList.size(); i++) {             
            
            JButton movieButton = new JButton(movieList.get(i));
            movieButton.setForeground(Color.BLACK);
            movieButton.setBackground(Color.WHITE);
            buttonList.add(movieButton);
            panel.add(buttonList.get(i));
            movieButton.addActionListener(new movieListener());

        }

        JButton cancelButton = new JButton("Cancel Tickets");
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(new cancelTicketListener());
        panel.add(cancelButton);
    }

    private class movieListener implements ActionListener {    // When a movie is chosen

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedMovie = e.getActionCommand();
            showtimeFrame = new JFrame();
            JPanel panel = new JPanel();
            showtimeFrame.setSize(500, 300);
            showtimeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            showtimeFrame.add(panel);
            panel.setLayout(new GridLayout(3, 2));
            showtimeFrame.setTitle("Available Showtimes for " + selectedMovie);
            showtimeFrame.setVisible(true);

            // Displays buttons with all showtimes on them
            for(int i = 0; i < movieList.size(); i++) {
                if(movieList.get(i).equals(e.getActionCommand())) {
                    showtimeButton = new JButton(showTimes.get(i));
                }
            }
            showtimeButton.setForeground(Color.BLACK);
            showtimeButton.setBackground(Color.WHITE);
            showtimeButton.addActionListener(new showtimeListener());
            panel.add(showtimeButton);
        }
    }

    private class showtimeListener implements ActionListener {      // When a showtime is chosen

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedShowtime = e.getActionCommand();
            showtimeFrame.dispose();
            seatsGUI = new Seats(selectedMovie, selectedShowtime);  // displays seats window
        }
    }

    private class cancelTicketListener implements ActionListener {      // When Cancel Tickets button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            cancelFrame = new JFrame();
            JPanel panel = new JPanel();
            cancelFrame.setSize(500, 300);
            cancelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            panel.setLayout(new GridLayout(3, 2));
            cancelFrame.add(panel);
            cancelFrame.setTitle("Cancel Tickets");
            cancelFrame.setResizable(false);
            cancelFrame.setVisible(true);

            // Obtains all ticket information from the database
            ticketDB = new Ticket();
            ticketSeatsList = ticketDB.getTicketSeats();
            ticketMovieList = ticketDB.getTicketMovie();
            ticketshowTimeList = ticketDB.getTicketShowTime();
            ticketPriceList = ticketDB.getTicketPrice();
            ticketIDList = ticketDB.getTicketID();

            // Creates new window that displays all existing tickets as buttons
            buttonList = new ArrayList<JButton>();
            for(int i = 0; i < ticketSeatsList.size(); i++) {
                
                String buttonLabel = "Seats " + ticketSeatsList.get(i) + " for " + ticketMovieList.get(i) + " at "  + ticketshowTimeList.get(i) + " that cost $" + ticketPriceList.get(i) + "\n Ticket ID: " + ticketIDList.get(i);
                JButton cancelButton = new JButton(buttonLabel);
                cancelButton.setForeground(Color.BLACK);
                cancelButton.setBackground(Color.WHITE);
                buttonList.add(cancelButton);
                panel.add(buttonList.get(i));
                cancelButton.addActionListener(new cancelListener());

            }
    
            // confirmLabel = new JLabel("Cancel your tickets?");
            // confirmLabel.setBounds(10, 20, 50, 40);
            // frame.add(confirmLabel);
            // frame.add(yesButton);
            // frame.add(noButton);
            // yesButton.addActionListener(new confirmListener());
            // noButton.addActionListener(new confirmListener());
        }
    }

    private class cancelListener implements ActionListener {        // When a cancel button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {

            String cancelledTicket = e.getActionCommand().substring(e.getActionCommand().length() - 1);
            String refundAmount = ticketDB.getRefundPrice(cancelledTicket);
            ticketDB.removeTicket(cancelledTicket);

            if(validUser == true) { 
                JOptionPane.showMessageDialog(null, "Refund of $" + refundAmount + " given.","Refund Notification", JOptionPane.INFORMATION_MESSAGE);
                cancelFrame.dispose();
            }

            else {
                int unregistered = Integer.valueOf(refundAmount);
                JOptionPane.showMessageDialog(null, "Refund of $" + unregistered * 0.85 + " given.","Refund Notification", JOptionPane.INFORMATION_MESSAGE);
                cancelFrame.dispose();

            }

        }

    }
                                
    public static void main(String[] args) {
        new GUI();
    }

}