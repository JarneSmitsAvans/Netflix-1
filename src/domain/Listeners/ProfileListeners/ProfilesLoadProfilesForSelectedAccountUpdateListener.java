package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfilesLoadProfilesForSelectedAccountUpdateListener.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfilesLoadProfilesForSelectedAccountUpdateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public ProfilesLoadProfilesForSelectedAccountUpdateListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null) {
                // Empty the comboBox to avoid double data
                profileManager.initializeProfileComboBoxes(ui);
                this.ui.getTxtUpdateProfileName().setText(null);
                this.ui.getjDPnewDateOfBirth().setDate(null);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbUpdateSelectedProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbUpdateSelectedProfile(), profileManager.getMatchingProfiles(id));
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
