package All;

//CODE FOR THE MOVIE THEATRE SEAT SELECTION

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;


/*
 * A grid of theatre seats made with buttons, once a button is pressed it's label will turn into taken,
 * and then the amount of seats selected with increment by 1.
 * Once all selected seats are taken, it will show the amount of payment that is needed, as well as ask for payment information.
 * 
 */

public class Seats extends Database{

    private int selectedSeats;
    public String selectedMovie;
    public String selectedShowtime;
    private int amountDue;
    private String amountPrice;
    private int ticketID;
    private JButton movieButton;
    private JButton resetButton;
    private JButton payButton;
    public String totalSeats;
    List<JButton> buttonList = new ArrayList<JButton>();
    List<String> selectedList = new ArrayList<String>();

    private Payment startPayment;

    public Seats(String m, String s){
        super("theatre_app");
        selectedMovie = m;
        selectedShowtime = s;

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        payButton = new JButton("PAY");
        resetButton = new JButton("RESET");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridLayout(5, 5));
        frame.setTitle("Available Seats for " + selectedMovie + " at " + selectedShowtime);
        frame.setResizable(false);
        frame.setVisible(true);

        for(int i = 1; i < 17; i++) {
            movieButton = new JButton(String.valueOf(i));
            movieButton.setForeground(Color.BLACK);
            movieButton.setBackground(Color.WHITE);
            buttonList.add(movieButton);
            panel.add(movieButton);
            movieButton.addActionListener(new movieListener());
        }
        panel.add(payButton, BorderLayout.PAGE_END);
        panel.add(resetButton, BorderLayout.PAGE_END);
        resetButton.addActionListener(new movieListener());
        payButton.addActionListener(new movieListener());

    }

    public void addTicketInfo(String seatNumber, String movieName, String showTime, String price, int ticketID) {

        String sql = "INSERT INTO ticketinfo (seat, movieBought, showTime, price, ticketID) VALUES (?,?,?,?,?)";
        
        try {
            myStmt = jdbc_connection.prepareStatement(sql);
            myStmt.setString(1, seatNumber);
            myStmt.setString(2, movieName);
            myStmt.setString(3, showTime);
            myStmt.setString(4, price);
            myStmt.setInt(5, ticketID);
            myStmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println("User already exists");

        }

    }


    private class movieListener implements ActionListener {    // When a movie is chosen
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int j = 1; j < 17; j++){
                if (e.getActionCommand().equals(String.valueOf(j))){
                    buttonList.get(j - 1).setForeground(Color.LIGHT_GRAY);
                    buttonList.get(j - 1).setBackground(Color.GRAY);
                    buttonList.get(j - 1).setText("TAKEN");
                    selectedSeats++;
                    selectedList.add(e.getActionCommand());
                    System.out.println("Selected:" + selectedSeats);
                    System.out.println("You selected seat: " + e.getActionCommand());
                }
            }
            
            if (e.getActionCommand().equals("RESET")){  // When RESET button is clicked
                for(int a = 1; a < 17; a++){
                buttonList.get(a - 1).setForeground(Color.BLACK);
                buttonList.get(a - 1).setBackground(Color.WHITE);
                buttonList.get(a - 1).setText(String.valueOf(a));
                }
                selectedSeats = 0;
                selectedList.clear();
                System.out.println("YOU RESET Selected SEATS now it's at " + selectedSeats);
            }

            if(e.getActionCommand().equals("PAY")){     // When PAY button is clicked
                // Call Payment() to initiate payment GUI
                String totalSeats = selectedList.get(0);
                for(int g = 1; g < selectedList.size(); g++){
                    totalSeats = totalSeats + ", " + selectedList.get(g);
                }
                amountDue = selectedSeats * 10;
                startPayment = new Payment("Pay for Tickets", amountDue);
                System.out.println("You selected these seats:");
                for(int k = 0; k < selectedList.size(); k++){
                    System.out.println(selectedList.get(k));
                }
                System.out.println("Your total price is: $" + amountDue);
                amountPrice = Integer.toString(amountDue);
                addTicketInfo(totalSeats, selectedMovie, selectedShowtime, amountPrice, ticketID);
            }
        }
    }


}