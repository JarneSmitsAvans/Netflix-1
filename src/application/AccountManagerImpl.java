package application;

import datastorage.AccountDAO;
import domain.Account;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<String> singleProfile() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all accounts that only have one profile assigned to them.
        ArrayList<String> arrayList = accountDAO.getSingleAccounts();
        return arrayList;
    }
    public ArrayList<Account> getAccounts() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all accounts in the database.
        ArrayList<Account> arrayList = accountDAO.getAccounts();
        return arrayList;
    }
    public void appendComboBox(JComboBox comboBox, ArrayList<Account> arrayList)
    {
        for (Account account : arrayList)
        {
            comboBox.addItem(account.getName());
        }
    }

    public void addToTextPane(JTextPane textPane, ArrayList<String> arrayList) {
        StyledDocument styledDocument = textPane.getStyledDocument();
        for (String account : arrayList) {
            try {
                styledDocument.insertString(0, account + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

}
