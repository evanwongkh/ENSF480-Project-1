package All;
import java.sql.*;
import java.sql.SQLException;

public class Registration extends Database {

    private ResultSet result;

    public Registration() {
        super("theatre_app");
    }

    public void addUser(String email, String password) {

        String sql = "INSERT INTO registration (email, password) VALUES (?,?)";
        
        try {
            myStmt = jdbc_connection.prepareStatement(sql);
            myStmt.setString(1, email);
            myStmt.setString(2, password);
            myStmt.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean validate(String email, String password) {

        boolean validUser = false;
        try {
            Statement myStmt = jdbc_connection.createStatement();
            result = myStmt.executeQuery("SELECT * FROM registration");
            while (result.next()) {
                if(result.getString("email").equals(email)) {
                    
                    if(result.getString("password").equals(password)) {
                        validUser = true;
                    }

                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return validUser;

    }
    
}
