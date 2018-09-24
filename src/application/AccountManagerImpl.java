package application;

import datastorage.AccountDAO;
import domain.Account;

import java.sql.SQLException;
import java.util.HashSet;

public class AccountManagerImpl {
    private AccountDAO accountDAO = new AccountDAO();

    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        boolean created = accountDAO.create(account);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(int id, Account account) throws SQLException, ClassNotFoundException {
        boolean updated = accountDAO.update(id, account);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean deleted = accountDAO.delete(id);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    public HashSet<Account> singleProfile() throws SQLException, ClassNotFoundException {
        // needs revision
        HashSet<Account> hs = accountDAO.singleProfile();
        return hs;
    }
}
