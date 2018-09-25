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
        //note: only update complete account objects!
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

    public ArrayList<Account> singleProfile() throws SQLException, ClassNotFoundException {
        Account account = new Account();
        ArrayList<Account> arrayList = new ArrayList<Account>();
        databaseConnection.OpenConnection();
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement("To DO: SQL Geef de accounts met slechts 1 profiel");
        while (resultSet.next()) {
            account.setName(resultSet.getString("name"));
            account.setAddress(resultSet.getString("address"));
            account.setResidence(resultSet.getString("residence"));
            arrayList.add(account);
        }
        databaseConnection.CloseConnection();
        return arrayList;
    }
}
