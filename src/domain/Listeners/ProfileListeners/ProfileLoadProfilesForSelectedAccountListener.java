package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileLoadProfilesForSelectedAccountListener.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileLoadProfilesForSelectedAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public ProfileLoadProfilesForSelectedAccountListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem() != null) {
                // Empty the comboBox to avoid double data
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbDeleteProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbDeleteProfile(), profileManager.getMatchingProfiles(id));
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
