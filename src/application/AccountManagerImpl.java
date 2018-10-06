package application;

import datastorage.AccountDAO;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountManagerImpl extends GeneralManager{
    private AccountDAO accountDAO = new AccountDAO();

    public void initializeAccountComponents(GUI gui) throws SQLException, ClassNotFoundException {
        //Fill txtAccountsWithOneProfile with Accounts that have one and only one profile.
        ArrayList<String> singleProfileAccounts = this.singleProfile();
        this.addToTextPane(gui.getTxtAccountsWithOneProfile(), singleProfileAccounts);
    }

    public void initializeAccountComboBoxes(GUI gui) throws SQLException, ClassNotFoundException {
        // Empty All ComboBoxes should there be any data inside of them.
        gui.getCbAvgWatchedAccount().removeAllItems();
        gui.getCbWatchedByAccount().removeAllItems();
        gui.getCbDeleteSelectedAccount().removeAllItems();
        gui.getCbUpdateSelectedAccount().removeAllItems();
        gui.getCbAddProfileToSelectedAccount().removeAllItems();
        gui.getCbDeleteProfileFromSelectedAccount().removeAllItems();
        //gui.getComboBox1().removeAllItems();
        gui.getCbSelectAccountForProfileEdit().removeAllItems();
        gui.getCbUpdateSelectedProfile().removeAllItems();

        // Fill the following JComboBoxes with accounts.
        ArrayList<Account> accountArrayList = this.getAccounts();
        this.addAccountsToComboBox(gui.getCbAvgWatchedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbWatchedByAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbDeleteSelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbUpdateSelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbAddProfileToSelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbDeleteProfileFromSelectedAccount(), accountArrayList);
        //this.addAccountsToComboBox(gui.getComboBox1(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbSelectAccountForProfileEdit(), accountArrayList);

    }
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

    public void addAccountsToComboBox(JComboBox comboBox, ArrayList<Account> arrayList)
    {
        // For each Account in ArrayList, get the account name and add it to the parameter comboBox
        for (Account account : arrayList)
        {
            comboBox.addItem(account.getName());
        }
    }
    public Account getAccountByName(String name) throws SQLException, ClassNotFoundException {
        Account account = accountDAO.getAccountId(name);
        return  account;
    }
}
