package Database;
import java.sql.*;

public abstract class Database {

    protected Connection jdbc_connection;
    protected PreparedStatement myStmt;
	protected String databaseName;
	protected String connectionInfo = "jdbc:mysql://localhost:3306/";  
	protected String login = "root";
	protected String password = "password";
    
    // Initializes connection to theatre_app database
    public Database(String databaseName) {

        try {
            this.databaseName = databaseName;
            connectionInfo += databaseName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
            myStmt = null;
        } 

        catch (SQLException e) {
            System.out.println("Error: document database cannot be connected to!");
        } 

        catch (ClassNotFoundException e) {
            System.out.println("Error: the jdbc is not properly installed");
        }

    }

}
