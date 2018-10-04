package application;

import datastorage.AccountDAO;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountManagerImpl extends GeneralManager{
    private AccountDAO accountDAO = new AccountDAO();

    public void initializeAccountComponents(GUI gui) throws SQLException, ClassNotFoundException {
        //Fill txtAccountsWithOneProfile with Accounts that have one and only one profile.
        ArrayList<String> singleProfileAccounts = this.singleProfile();
        this.addToTextPane(gui.getTxtAccountsWithOneProfile(), singleProfileAccounts);

        // Fill the following JComboBoxes with accounts.
        ArrayList<Account> accountArrayList = this.getAccounts();
        this.appendComboBox(gui.getCbAvgWatchedAccount(), accountArrayList);
        this.appendComboBox(gui.getCbWatchedByAccount(), accountArrayList);
        this.appendComboBox(gui.getCbDeleteSelectedAccount(), accountArrayList);
        this.appendComboBox(gui.getCbUpdateSelectedAccount(), accountArrayList );
    }

    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        boolean created = accountDAO.create(account);
        if (created = true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean update(int id, Account account) throws SQLException, ClassNotFoundException {
        boolean updated = accountDAO.update(id, account);
        if (updated = true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean deleted = accountDAO.delete(id);
        if (deleted = true) {
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
        // For each Account in arraylist, get the account name and add it to the parameter combobox
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
    public Account getAccountByName(String name) throws SQLException, ClassNotFoundException {
        Account account = accountDAO.getAccountId(name);
        return  account;
    }
}
