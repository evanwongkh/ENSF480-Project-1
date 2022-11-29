//CODE FOR THE MOVIE THEATRE SEAT SELECTION

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

/*
 * A grid of theatre seats made with buttons, once a button is pressed it's label will turn into taken,
 * and then the amount of seats selected with increment by 1.
 * Once all selected seats are taken, it will show the amount of payment that is needed, as well as ask for payment information.
 * 
 */

public class MainFrame {
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
    private JButton movieButton;
    private JButton resetButton;
    private int selectedSeats;
    private JButton payButton;
    private boolean seats;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    List<JButton> buttonList = new ArrayList<JButton>();

    public MainFrame(){
       
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
                    System.out.println("Selected:" + selectedSeats);
                }
            }
            
            if (e.getActionCommand().equals("RESET")){
                for(int a = 1; a < 17; a++){
                buttonList.get(a - 1).setForeground(Color.BLACK);
                buttonList.get(a - 1).setBackground(Color.WHITE);
                buttonList.get(a - 1).setText(String.valueOf(a));
                }
                selectedSeats = 0;
                System.out.println("Selected:" + selectedSeats);
            }

        }
    }
    private void initialize(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        payButton = new JButton("PAY");
        resetButton = new JButton("RESET");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridLayout(5, 5));
        frame.setTitle("joe");
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


    }

    public static void main(String args[]){
        MainFrame bro = new MainFrame();
        bro.initialize();
    }

}
