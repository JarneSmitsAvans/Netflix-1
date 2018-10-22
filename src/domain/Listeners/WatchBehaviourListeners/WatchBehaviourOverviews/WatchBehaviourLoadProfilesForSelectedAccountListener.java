package domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WatchBehaviourLoadProfilesForSelectedAccountListener.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourLoadProfilesForSelectedAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public WatchBehaviourLoadProfilesForSelectedAccountListener(GUI ui) {
        this.ui = ui;
        accountManager = new AccountManagerImpl();
        profileManager = new ProfileManagerImpl();
        account = new Account();
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem() != null) {
                // Empty the comboBox to avoid double data.
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                // Get the ID of the account.
                this.ui.getCbWatchedProgramsBySelectedProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(ui.getCbWatchedProgramsBySelectedProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbWatchedProgramsBySelectedProfile().setSelectedItem(null);
                ui.getTxtWatchedProgramsBySelectedProfile().setText(null);
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
