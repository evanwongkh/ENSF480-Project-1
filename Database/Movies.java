package Database;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public class Movies extends Database {

    private ResultSet result;
    private ArrayList<String> movieList = new ArrayList<>();
    private ArrayList<String> showtimeList = new ArrayList<>();


    public Movies() {
        super("theatre_app");
    }

    public ArrayList<String> getMovies() {

        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM movies");
            while(result.next()) {
                movieList.add(result.getString("title"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public ArrayList<String> getShowtimes() {

        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM movies");
            while(result.next()) {
                showtimeList.add(result.getString("movieTime"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return showtimeList;
    }


}