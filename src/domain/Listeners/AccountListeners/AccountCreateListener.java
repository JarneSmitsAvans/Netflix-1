package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import domain.ErrorHandling;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * AccountCreateListener.java
 * This ActionListener handles the "Create" functionality of the Account CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class AccountCreateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public AccountCreateListener(GUI ui) {
        this.ui = ui;
        this.account = new Account();
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
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
                if (existingAccount.getName() == null) {
                    // Create the account
                    boolean created = this.accountManager.create(account);
                    if (created) {
                        /* If the create was successful, empty the fields and display a success message.
                        reinitialize the account components in the application. After that, create the first profile for this account. */
                        this.ui.getTxtAccountName().setText(null);
                        this.ui.getTxtAccountAddress().setText(null);
                        this.ui.getTxtAccountResidence().setText(null);
                        this.accountManager.initializeAccountComboBoxes(ui);

                        // Create the first profile for the created account
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        // Retrieve the ID of the created Account
                        Account testedAccount = accountManager.getAccountByName(account.getName());
                        String dateOfBirth = "01-01-2000";
                        java.util.Date date = sdf.parse(dateOfBirth);
                        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
                        Profile profile = new Profile();
                        profile.setProfileName(this.account.getName());
                        profile.setDateOfBirth(convertedDateOfBirth);
                        profile.setAccountNumber(testedAccount.getId());
                        boolean profileCreated = profileManager.create(profile);
                        if (profileCreated) {
                            this.profileManager.initializeProfileComboBoxes(ui);
                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is aangemaakt. Er is ook een profiel aangemaakt voor dit account.", "Account aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                        }
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
