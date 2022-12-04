package Database;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public class Ticket extends Database {

    private ResultSet result;
    private ArrayList<String> seatList = new ArrayList<>();
    private ArrayList<String> movieList = new ArrayList<>();
    private ArrayList<String> showTimeList = new ArrayList<>();
    private ArrayList<String> priceList = new ArrayList<>();
    private ArrayList<Integer> ticketIDList = new ArrayList<>();

    private String ticketIDPrice;

    public Ticket() {
        super("theatre_app");
    }

    // Returns ArrayList<String> of seats from table ticketinfo
    public ArrayList<String> getTicketSeats() {         

        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                seatList.add(result.getString("seat"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

    // Returns ArrayList<String> of movie names from table ticketinfo
    public ArrayList<String> getTicketMovie() {

        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                movieList.add(result.getString("movieBought"));            
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return movieList;


    }

    // Returns ArrayList<String> of showtime from table ticketinfo
    public ArrayList<String> getTicketShowTime() {
        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                showTimeList.add(result.getString("showTime"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return showTimeList;

    }

    // Returns ArrayList<String> of price from table ticketinfo
    public ArrayList<String> getTicketPrice() {
        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                priceList.add(result.getString("price"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return priceList;

    }

    // Returns ArrayList<Integer> of ticketID from table ticketinfo
    public ArrayList<Integer> getTicketID() {
        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                ticketIDList.add(result.getInt("ticketID"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return ticketIDList;

    }

    // Returns ArrayList<String> of price from table ticketinfo
    public String getRefundPrice(String id) {

        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM ticketinfo");
            while(result.next()) {
                if(result.getString("ticketID").equals(id)) {
                    ticketIDPrice = result.getString("price");
                }
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return ticketIDPrice;
    }

    // Removes ticket information for the instance in ticketinfo that matches ticketID
    public void removeTicket(String id) {

        String sql = "DELETE FROM ticketinfo WHERE ticketID=" + id;
        
        try {
            myStmt = jdbc_connection.prepareStatement(sql);

            myStmt.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }
 

}