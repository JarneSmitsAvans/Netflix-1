package datastorage;

import java.sql.*;

public class DatabaseConnection
{
    private Connection conn = null;
    private ResultSet rs = null;
    public boolean OpenConnection() throws ClassNotFoundException, SQLException {
        // Connection string. Replace if needed.
        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=Netflix;integratedSecurity=true;";
        // 'Import' the driver.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Create the connection to the database
        conn = DriverManager.getConnection(connectionUrl);
        // If the connection was created, return true, else return false;
        if (conn != null  && conn.isValid(0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Connection getConnection() {
        // Get the open connection.
        if(this.conn != null)
        {
            return this.conn;
        }
        else
        {
           return null;
        }
    }
    public boolean CloseConnection() throws SQLException {
        // Close any existing connections, return true if closed, else return false;
        conn.close();
        if (conn.isClosed())
        {
            return true;
        }
        else
        {
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
            /* Execute the prepared statement */
            boolean inserted = preparedStatement.execute();
            // Return true if succeeded, false if failed.
            if (inserted = true) {
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
            /* Execute the prepared statement */
            boolean deleted = preparedStatement.execute();
            // Return true if succeeded, false if failed.
            if (deleted = true) {
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
            /* Execute the prepared statement */
            boolean updated = preparedStatement.execute();
            // Return true if succeeded, false if failed.
            if (updated = true) {
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
