package application;

import datastorage.AccountDAO;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AccountManagerImpl.java
 * <p>
 * This class has methods that do things with accounts, like;
 * * Implementation for CRUD Operations
 * * Getting data for overviews
 * * Adding account objects to swing components.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountManagerImpl extends GeneralManager {
    // Initialize a new accountDAO object.
    private AccountDAO accountDAO = new AccountDAO();

    //Empty and fill txtAccountsWithOneProfile with accounts that have one and only one profile.
    public void initializeAccountComponents(GUI gui) throws SQLException, ClassNotFoundException {

        gui.getTxtAccountsWithOneProfile().setText(null);
        ArrayList<String> singleProfileAccounts = this.singleProfile();
        this.addToTextPane(gui.getTxtAccountsWithOneProfile(), singleProfileAccounts);
    }

    /*Empty all ComboBoxes should there be any data inside of them so that
    there won't be double data inside ComboBoxes when we re-fill them. Then, re-fill them.*/
    public void initializeAccountComboBoxes(GUI gui) throws SQLException, ClassNotFoundException {
        // Remove all data from the following comboBoxes to avoid double data
        gui.getCbAccountAvgWatchedBySerie().removeAllItems();
        gui.getCbWatchedMoviesByAccount().removeAllItems();
        gui.getCbDeleteAccount().removeAllItems();
        gui.getCbUpdateAccount().removeAllItems();
        gui.getCbAddProfileToSelectedAccount().removeAllItems();
        gui.getCbDeleteProfileFromSelectedAccount().removeAllItems();
        gui.getCbSelectAccountForProfileEdit().removeAllItems();
        gui.getCbSelectAccountForProfileEdit().removeAllItems();
        gui.getCbUpdateSelectedProfile().removeAllItems();
        gui.getCbEditWatchedMediaAccount().removeAllItems();
        gui.getCbAddWatchedMediaAccount().removeAllItems();
        gui.getCbDeleteWatchedMediaAccount().removeAllItems();
        gui.getCbWatchedProgramsBySelectedAccount().removeAllItems();
        gui.getCbRecommendedSerieForAccount().removeAllItems();
        gui.getCbAccountOverviewSelectAccount().removeAllItems();
        gui.getCbProfileOverviewSelectAccount().removeAllItems();

        initializeAccountComponents(gui);

        // Get all accounts that are present in the database.
        ArrayList<Account> accountArrayList = this.getAccounts();
        /// Add all those accounts to the following comboBoxes.
        this.addAccountsToComboBox(gui.getCbAccountAvgWatchedBySerie(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbWatchedMoviesByAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbDeleteAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbUpdateAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbAddProfileToSelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbDeleteProfileFromSelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbAddWatchedMediaAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbSelectAccountForProfileEdit(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbEditWatchedMediaAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbDeleteWatchedMediaAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbWatchedProgramsBySelectedAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbRecommendedSerieForAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbAccountOverviewSelectAccount(), accountArrayList);
        this.addAccountsToComboBox(gui.getCbProfileOverviewSelectAccount(), accountArrayList);

        gui.getTxtUpdateAccountName().setText(null);
        gui.getTxtUpdateAccountAdres().setText(null);
        gui.getTxtUpdateAccountResidence().setText(null);
        gui.getCbAccountAvgWatchedBySerie().setSelectedItem(null);
        gui.getCbProfileOverviewSelectAccount().setSelectedItem(null);
        gui.getCbWatchedMoviesByAccount().setSelectedIndex(-1);
        gui.getCbDeleteAccount().setSelectedItem(null);
        gui.getCbUpdateAccount().setSelectedItem(null);
        gui.getCbAddWatchedMediaAccount().setSelectedItem(null);
        gui.getCbEditWatchedMediaAccount().setSelectedItem(null);
        gui.getCbDeleteWatchedMediaAccount().setSelectedItem(null);
        gui.getCbWatchedProgramsBySelectedAccount().setSelectedItem(null);
        gui.getCbRecommendedSerieForAccount().setSelectedItem(null);
        gui.getCbAddProfileToSelectedAccount().setSelectedItem(null);
        gui.getCbSelectAccountForProfileEdit().setSelectedItem(null);
        gui.getCbDeleteProfileFromSelectedAccount().setSelectedItem(null);
        gui.getCbAccountOverviewSelectAccount().setSelectedItem(null);
        gui.getjDPnewDateOfBirth().setDate(null);
        gui.getTxtUpdateProfileName().setText(null);
        gui.getLblForOverviewAccountAddress().setVisible(false);
        gui.getLblForOverviewAccountName().setVisible(false);
        gui.getLblForOverviewAccountResidence().setVisible(false);
        gui.getLblOverviewAccountAddress().setVisible(false);
        gui.getLblOverviewAccountName().setVisible(false);
        gui.getLblOverviewAccountResidence().setVisible(false);
    }

    // Sends an object of type Account to the Account DAO that needs to be inserted into the database.
    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        boolean created = accountDAO.create(account);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

    // Sends an object of type Account to the Account DAO that needs to be updated in the database.
    public boolean update(int id, Account account) throws SQLException, ClassNotFoundException {
        boolean updated = accountDAO.update(id, account);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    // Sends an object of type Account to the Account DAO that needs to be deleted from the database.
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean deleted = accountDAO.delete(id);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList filled with all accounts that only have one profile assigned to them.
    public ArrayList<String> singleProfile() throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = accountDAO.getSingleAccounts();
        return arrayList;
    }

    // Returns an ArrayList filled with all accounts in the database.
    public ArrayList<Account> getAccounts() throws SQLException, ClassNotFoundException {
        ArrayList<Account> arrayList = accountDAO.getAccounts();
        return arrayList;
    }

    // For each Account in ArrayList, get the account name and add it to the parameter comboBox
    public void addAccountsToComboBox(JComboBox comboBox, ArrayList<Account> arrayList) {
        for (Account account : arrayList) {
            comboBox.addItem(account.getName());
        }
    }

    // Returns the requested Account by name and parses it to an object of Account type.
    public Account getAccountByName(String name) throws SQLException, ClassNotFoundException {
        Account account = accountDAO.getAccountByName(name);
        return  account;
    }
}
