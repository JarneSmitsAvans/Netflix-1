package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileLoadProfielsForRecommendedSeries implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;
    private JComboBox cbSelectedSerie;
    private JComboBox profileCbToFill;

    // Constructor
    public ProfileLoadProfielsForRecommendedSeries(GUI ui ,JComboBox cbSelectedSerie, JComboBox profileCbToFill) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
        this.cbSelectedSerie = cbSelectedSerie;
        this.profileCbToFill = profileCbToFill;
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (cbSelectedSerie.getSelectedItem() != null) {
                // Empty the comboBox to avoid double data
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = cbSelectedSerie.getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                ui.getCbDeleteProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(profileCbToFill, profileManager.getMatchingProfiles(id));
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
