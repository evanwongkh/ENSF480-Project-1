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
    private JButton showtimeButton;

    private Movies movieDB;
    private ArrayList<String> movieList;
    private ArrayList<String> showTimes;
    private ArrayList<JButton> buttonList;

    private String selectedMovie;
    private String selectedShowtime;
    private Seats seatsGUI;

    private Payment annualPayment;

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

    private class movieListener implements ActionListener {    // When a movie is chosen

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedMovie = e.getActionCommand();
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            frame.setSize(500, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(panel);
            panel.setLayout(new GridLayout(3, 2));
            frame.setTitle("Available Showtimes for " + selectedMovie);
            frame.setVisible(true);

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

    

    private class guestListener implements ActionListener {     // When proceed as guest button is pressed

        @Override
        public void actionPerformed(ActionEvent e) {
            searchGUI("");
        }

    }

    private class showtimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedShowtime = e.getActionCommand();
            seatsGUI = new Seats(selectedMovie, selectedShowtime);
        }
    }

    public void searchGUI(String title) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridLayout(3, 2));
        frame.setTitle(title);
        frame.setVisible(true);

        movieDB = new Movies();
        movieList = movieDB.getMovies();
        showTimes = movieDB.getShowtimes();

        buttonList = new ArrayList<JButton>();

        for(int i = 0; i < movieList.size(); i++) {
            
            JButton movieButton = new JButton(movieList.get(i));
            movieButton.setForeground(Color.BLACK);
            movieButton.setBackground(Color.WHITE);
            buttonList.add(movieButton);
            panel.add(buttonList.get(i));
            movieButton.addActionListener(new movieListener());

        }

        JButton cancelButton = new JButton("CANCEL ALL TICKETS");
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBackground(Color.RED);
        panel.add(cancelButton);
        

    }
                                
    public static void main(String[] args) {
        new GUI();
    }

}