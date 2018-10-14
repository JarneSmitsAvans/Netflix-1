package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import domain.ErrorHandling;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AccountCreateListener.java
 * This ActionListener handles the "Create" functionality of the Account CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountCreateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    // Constructor
    public AccountCreateListener(GUI ui, Account account) {
        this.ui = ui;
        this.account = account;
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (!accountManager.empty(this.ui.getTxtAccountName().getText()) && !accountManager.empty(this.ui.getTxtAccountAddress().getText()) && !accountManager.empty(this.ui.getTxtAccountResidence().getText())) {
                // Set values for the to-be created account.
                this.account.setName(this.ui.getTxtAccountName().getText());
                this.account.setAddress(this.ui.getTxtAccountAddress().getText());
                this.account.setResidence(this.ui.getTxtAccountResidence().getText());
                // Check if the account already exists
                Account existingAccount = accountManager.getAccountByName(this.account.getName());
                if (existingAccount.getName() == this.account.getName()) {
                    // Create the account
                    boolean created = this.accountManager.create(account);
                    if (created) {
                        /* If the create was successful, empty the fields and display a success message.
                        reinitialize the account components in the application. */
                        this.ui.getTxtAccountName().setText(null);
                        this.ui.getTxtAccountAddress().setText(null);
                        this.ui.getTxtAccountResidence().setText(null);
                        this.accountManager.initializeAccountComboBoxes(ui);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is aangemaakt.", "Account aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Something went wrong, throw an error message.
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Account niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // The account already exists, throw that error message.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.ALREADYEXISTS.getError(), "Account is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Some fields were left empty or have invalid values, throw an error if that is the case.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Account is niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
