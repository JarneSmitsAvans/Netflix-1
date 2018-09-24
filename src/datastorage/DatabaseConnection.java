package datastorage;

import java.sql.*;

public class DatabaseConnection
{
    private Statement statement = null;
    private Connection conn = null;
    private ResultSet rs = null;
    public boolean OpenConnection() throws ClassNotFoundException, SQLException {
        // Created a temporary test in Main.java
        // Connection string. Replace if needed.
        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=Netflix;integratedSecurity=true;";
        // 'Import' the driver.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Create the connection to the database
        conn = DriverManager.getConnection(connectionUrl);
        // If the connection was created, return true, else return false;
        if (conn != null  && conn.isValid(0))
        {
            System.out.println("Connected to the database.");
            return true;
        }
        else
        {
            System.out.println("Could not connect to the database.");
            return false;
        }
    }
    public boolean CloseConnection() throws SQLException {
        // Close any existing connections, return true if closed, else return false;
        conn.close();
        if (conn.isClosed())
        {
            System.out.println("Disconnected from the database.");
            return true;
        }
        else
        {
            System.out.println("Could not disconnect from the database.");
            return false;
        }
    }
    public ResultSet ExecuteSelectStatement(String query) throws SQLException {
        try {
            // Empty any existing resultsets.
            rs = null;
            // Create a new statement
            statement = conn.createStatement();
            // Execute the query
            rs = statement.executeQuery(query);
            // Return the ResultSet of the query.
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return rs = null;
        }
    }
    public boolean ExecuteInsertStatement(String query) {
        try {
            // Create a new statement
            statement = conn.createStatement();
            // Execute the query
            boolean inserted = statement.execute(query);

            // Return true if succeeded, false if failed.
            if (inserted) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean ExecuteDeleteStatement(String query) {
        try {
            // Create a new statement
            statement = conn.createStatement();
            // Execute the query
            boolean deleted = statement.execute(query);
            // Return true if succeeded, false if failed.
            if (deleted) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean ExecuteUpdateStatement(String query) {
        try {
            // Create a new statement
            statement = conn.createStatement();
            // Execute the query
            boolean updated = statement.execute(query);
            // Return true if succeeded, false if failed.
            if (updated) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
