package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import domain.ErrorHandling;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AccountDeleteListener.java
 * This ActionListener handles the "Delete" functionality of the Account CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountDeleteListener implements ActionListener  {
    private GUI ui;
    private AccountManagerImpl accountManager;

    // Constructor
    public AccountDeleteListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (this.ui.getCbDeleteAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbDeleteAccount().getSelectedItem().toString();
                // Check if selected account wasn't null/empty
                if (!accountManager.empty(strSelectedAccount)) {
                    // Get the selected account as an object
                    Account account = accountManager.getAccountByName(strSelectedAccount);
                    // Delete the selected account.
                    boolean deleted = accountManager.delete(account.getId());
                    if (deleted) {
                         /* If the delete was successful, empty the fields and display a success message.
                        reinitialize the account components in the application. */
                        this.accountManager.initializeAccountComboBoxes(ui);
                        this.ui.getCbDeleteAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is verwijderd.", "Account verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Something went wrong, throw an error message.
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Account niet verwijderd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // Some fields were left empty or have invalid values, throw an error if that is the case.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Account niet verwijderd", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
