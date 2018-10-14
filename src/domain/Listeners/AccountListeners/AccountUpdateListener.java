package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import domain.ErrorHandling;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileUpdateListener.java
 * This ActionListener handles the "Update" functionality of the Account CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountUpdateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    // Constructor
    public AccountUpdateListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (!accountManager.empty(this.ui.getTxtUpdateAccountName().getText()) &&
                    !accountManager.empty(this.ui.getTxtUpdateAccountAdres().getText()) &&
                    !accountManager.empty(this.ui.getTxtUpdateAccountResidence().getText()) && this.ui.getCbUpdateAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbUpdateAccount().getSelectedItem().toString();
                // Get the selected account as an object
                account = accountManager.getAccountByName(strSelectedAccount);
                // Set values for the to-be updated account.
                account.setName(this.ui.getTxtUpdateAccountName().getText());
                account.setAddress(this.ui.getTxtUpdateAccountAdres().getText());
                account.setResidence(this.ui.getTxtUpdateAccountResidence().getText());
                // Update the account
                boolean updated = accountManager.update(this.account.getId(), account);
                if (updated) {
                    /* If the update was successful, empty the fields and display a success message.
                    reinitialize the account components in the application. */
                    this.accountManager.initializeAccountComboBoxes(ui);
                    this.ui.getCbUpdateAccount().setSelectedItem(null);
                    this.ui.getTxtUpdateAccountName().setText(null);
                    this.ui.getTxtUpdateAccountAdres().setText(null);
                    this.ui.getTxtUpdateAccountResidence().setText(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is gewijzigd.", "Account gewijzigd", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Something went wrong, throw an error message.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Account niet gewijzigd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Some fields were left empty or have invalid values, throw an error if that is the case.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
