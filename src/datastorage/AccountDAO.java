package datastorage;

import domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean inserted = databaseConnection.ExecuteInsertStatement("INSERT INTO Account " +
                "VALUES (" + account.getName() + "," + account.getAddress() + "," + account.getResidence());
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(int id, Account account) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean updated = databaseConnection.ExecuteUpdateStatement("UPDATE Account " +
                "SET name = " + account.getName() + ",address = " + account.getAddress() + ",residence = " + account.getResidence() + "WHERE id = " + id);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean deleted = databaseConnection.ExecuteDeleteStatement("DELETE FROM Account WHERE id = " + id);
        databaseConnection.CloseConnection();
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Account> getAccounts() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all accounts in the database.
        ArrayList<Account> accountArrayList = new ArrayList<Account>();
        databaseConnection.OpenConnection();
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement("SELECT * FROM Account");
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
    public ArrayList<String> getSingleAccounts() throws SQLException, ClassNotFoundException
    {
        ArrayList<String> accountArrayList = new ArrayList<String>();
        databaseConnection.OpenConnection();
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement("SELECT Account.name FROM ACCOUNT JOIN Profile ON Profile.fk_profile = Account.id GROUP BY Account.name HAVING COUNT(*) = 1");
        while (resultSet.next()){
            String account = (resultSet.getString("name"));
            accountArrayList.add(account);
        }
        databaseConnection.CloseConnection();
        return accountArrayList;
    }
}
