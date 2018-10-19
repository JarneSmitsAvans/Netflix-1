package domain.Listeners.ProfileListeners.ProfileOverview;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileOverviewLoadProfilesForSelectedAccount.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileOverviewLoadProfilesForSelectedAccount implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    public ProfileOverviewLoadProfilesForSelectedAccount(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (this.ui.getCbProfileOverviewSelectAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbProfileOverviewSelectAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbProfileOverviewSelectProfile().setEnabled(true);
                // Empty the comboBox to avoid double data
                profileManager.initializeProfileComboBoxes(ui);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbProfileOverviewSelectProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbProfileOverviewSelectProfile().setSelectedItem(null);
                // Making sure that the labels are still hidden
                this.ui.getLblForOverviewProfileName().setVisible(false);
                this.ui.getLblForOverviewProfileDateOfBirth().setVisible(false);
                this.ui.getLblOverviewProfileName().setVisible(false);
                this.ui.getLblOverviewProfileDateOfBirth().setVisible(false);
                this.ui.getLblOverviewProfileName().setText(null);
                this.ui.getLblOverviewProfileDateOfBirth().setText(null);
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

