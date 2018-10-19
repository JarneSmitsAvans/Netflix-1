package domain.Listeners.AccountListeners.AccountOverview;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * AccountOverviewLoadListener.java
 * This ActionListener gets the selected account information and displays that with labels.
 * <p>
 * Author: Dylan ten Böhmer
 */


public class AccountOverviewLoadListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;

    public AccountOverviewLoadListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (this.ui.getCbAccountOverviewSelectAccount().getSelectedItem() != null) {
            try {
                // Declare/initialize variables
                String selectedAccount = ui.getCbAccountOverviewSelectAccount().getSelectedItem().toString();
                // Get the account data that matches the selected account.
                Account requestedAccount = accountManager.getAccountByName(selectedAccount);
                // Set some components up to be visible to the user
                ui.getLblForOverviewAccountAddress().setVisible(true);
                ui.getLblForOverviewAccountName().setVisible(true);
                ui.getLblForOverviewAccountResidence().setVisible(true);
                ui.getLblOverviewAccountAddress().setVisible(true);
                ui.getLblOverviewAccountName().setVisible(true);
                ui.getLblOverviewAccountResidence().setVisible(true);
                // Place the requested account data as the value for labels that represent the data of the selected account.
                ui.getLblOverviewAccountName().setText(requestedAccount.getName());
                ui.getLblOverviewAccountAddress().setText(requestedAccount.getAddress());
                ui.getLblOverviewAccountResidence().setText(requestedAccount.getResidence());
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            return;
        }
    }
}
