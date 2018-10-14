package datastorage;

import java.sql.*;

/**
 * DatabaseConnection.java
 * This class handles all the SQL related methods such as;
 * * Opening/closing a database connection
 * * Executing an prepared select statement
 * * Executing an prepared insert statement
 * * Executing an prepared update statement
 * * Executing an prepared delete statement
 * <p>
 * Author: Dylan ten Böhmer
 */

public class DatabaseConnection
{
    private Connection conn = null;
    private ResultSet rs = null;

    // Open a database connection.
    public boolean OpenConnection() throws ClassNotFoundException, SQLException {
        // Connection string. Replace if needed.
        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=Netflix;integratedSecurity=true;";
        // 'Import' the driver.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Create the connection to the database
        conn = DriverManager.getConnection(connectionUrl);
        // If the connection was created, return true, else return false;
        if (conn != null  && conn.isValid(0)) {
            return true;
        } else {
            return false;
        }
    }

    // Get the open connection, returns if true, otherwise return null.
    public Connection getConnection() {
        if(this.conn != null) {
            return this.conn;
        } else {
            return null;
        }
    }

    // Close any existing connections, return true if closed, else return false;
    public boolean CloseConnection() throws SQLException {
        conn.close();
        if (conn.isClosed()) {
            return true;
        } else {
            return false;
        }
    }

    public ResultSet ExecuteSelectStatement(PreparedStatement preparedStatement) {
        try {
            // Empty any existing ResultSets.
            rs = null;
            /* Execute the prepared statement */
            rs = preparedStatement.executeQuery();
            // Return the ResultSet of the query.
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return rs = null;
        }
    }

    public boolean ExecuteInsertStatement(PreparedStatement preparedStatement) {
        try {
            // Execute the prepared statement
            int inserted = preparedStatement.executeUpdate();
            // Return true if succeeded, false if failed.
            if(inserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ExecuteDeleteStatement(PreparedStatement preparedStatement) {
        try {
            // Execute the prepared statement //
            int deleted = preparedStatement.executeUpdate();
            // Return true if succeeded, false if failed.
            if (deleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ExecuteUpdateStatement(PreparedStatement preparedStatement) {
        try {
            // Execute the prepared statement
            int updated = preparedStatement.executeUpdate();
            // Return true if succeeded, false if failed.
            if (updated > 0) {
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
