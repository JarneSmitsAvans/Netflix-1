package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileLoadProfielsForRecommendedSeries.java
 * This ActionListener will fill the profile combobox based on the selected account.
 * Author: Marc Verwijmeren
 */

public class ProfileLoadProfielsForRecommendedSeries implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;
    private JComboBox cbSelectedAccount;
    private JComboBox profileCbToFill;

    // Constructor
    public ProfileLoadProfielsForRecommendedSeries(GUI ui ,JComboBox cbSelectedAccount, JComboBox profileCbToFill) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
        this.cbSelectedAccount = cbSelectedAccount;
        this.profileCbToFill = profileCbToFill;
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (cbSelectedAccount.getSelectedItem() != null) {
                // Empty the comboBox to avoid double data
                profileManager.initializeProfileComboBoxes(ui);
                profileCbToFill.removeAllItems();

                // Declare/initialize variables
                String strSelectedAccount = cbSelectedAccount.getSelectedItem().toString();

                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);

                // Get the ID of the account.
                int id = this.account.getId();

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
