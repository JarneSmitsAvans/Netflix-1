package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * AccountUpdateComboBoxListener.java
 * This ActionListener fires whenever the user selects an account that he/she would like to edit.
 * The current name, address and date of birth is then displayed in editable fields.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountUpdateComboBoxListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    // Constructor
    public AccountUpdateComboBoxListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbUpdateAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbUpdateAccount().getSelectedItem().toString();
                // Get all the data matching the profile id and initialize it as the selected account object.
                this.account = accountManager.getAccountByName(strSelectedAccount);
                // Set the current account name as the text of the update field.
                this.ui.getTxtUpdateAccountName().setText(this.account.getName());
                // Set the current address as the text of the update field.
                this.ui.getTxtUpdateAccountAdres().setText(this.account.getAddress());
                // Set the current residence as the text of the update field.
                this.ui.getTxtUpdateAccountResidence().setText(this.account.getResidence());
            } else {
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }
}
