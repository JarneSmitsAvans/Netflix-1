package datastorage;

import domain.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AccountDAO.java
 * This class creates SQL PreparedStatement for Account CRUD operations, and sends them to the DatabaseConnection for execution.
 * The DatabaseConnection class then returns true if it was executed successfully, false is it wasn't.
 * If a select statement was created, the requested ResultSet is then returned as the return type of that called method.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Generate the SQL Statement that will create a new Account.
    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Account (name, address, residence) VALUES (?, ?, ?)");
        preparedStatement.setString(1, account.getName());
        preparedStatement.setString(2, account.getAddress());
        preparedStatement.setString(3, account.getResidence());
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    // Generate the SQL Statement that will update an existing account.
    public boolean update(int id, Account account) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Account SET name = ?, address = ?, residence = ? WHERE id = ?" );
        preparedStatement.setString(1,account.getName());
        preparedStatement.setString(2,account.getAddress());
        preparedStatement.setString(3,account.getResidence());
        preparedStatement.setInt(4, id);
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    // Generate the SQL Statement that will delete an existing account.
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Account WHERE id = ?");
        preparedStatement.setInt(1, id);
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    // Generate the SQL Statement that will return all accounts in the database.
    public ArrayList<Account> getAccounts() throws SQLException, ClassNotFoundException {
        ArrayList<Account> accountArrayList = new ArrayList<Account>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Account");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Account account = new Account();
            account.setName(resultSet.getString("name"));
            account.setAddress(resultSet.getString("address"));
            account.setResidence(resultSet.getString("residence"));
            accountArrayList.add(account);
        }
        databaseConnection.CloseConnection();
        return accountArrayList;
    }

    // Generate the SQL Statement that will return the requested account that matches the parameter name.
    public Account getAccountByName(String accountName) throws SQLException, ClassNotFoundException {
        Account account = new Account();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Account WHERE name = ?");
        preparedStatement.setString(1, accountName);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setAddress(resultSet.getString("address"));
            account.setResidence(resultSet.getString("residence"));
            return account;
        }
        databaseConnection.CloseConnection();
        return account;
    }

    // Generate the SQL Statement that will return the accounts that have one and only one profile.
    public ArrayList<String> getSingleAccounts() throws SQLException, ClassNotFoundException {
        ArrayList<String> accountArrayList = new ArrayList<String>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT Account.name FROM Account JOIN Profile ON Profile.fk_account = Account.id GROUP BY Account.name HAVING COUNT(*) = 1");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()){
            String account = (resultSet.getString("name"));
            accountArrayList.add(account);
        }
        databaseConnection.CloseConnection();
        return accountArrayList;
    }
}
