package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

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
            if (this.ui.getCbAddWatchedMediaAccount().getSelectedItem() != null) {
                // Set some components enabled and empty the comboBox to avoid double data
                ui.getJSpinWatchedDate().setEnabled(true);
                ui.getRbMovie().setEnabled(true);
                ui.getRbSerie().setEnabled(true);
                ui.getTxtAddWatchedMediaDuration().setEnabled(true);
                ui.getCbAddWatchedMediaProfile().setEnabled(true);
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbAddWatchedMediaAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbAddWatchedMediaProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbAddWatchedMediaProfile(), profileManager.getMatchingProfiles(id));
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
