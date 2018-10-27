package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WatchBehaviorLoadProfilesForSelectedAccountEditListener.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviorLoadProfilesForSelectedAccountEditListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public WatchBehaviorLoadProfilesForSelectedAccountEditListener(GUI ui) {
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
            if (this.ui.getCbEditWatchedMediaAccount().getSelectedItem() != null) {
                // Empty the comboBox to avoid double data.
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbEditWatchedMediaAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbEditWatchedMediaProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbEditWatchedMediaProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbEditWatchedMediaProfile().setSelectedItem(null);
                this.ui.getCbEditWatchedMediaTitle().removeAllItems();
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
